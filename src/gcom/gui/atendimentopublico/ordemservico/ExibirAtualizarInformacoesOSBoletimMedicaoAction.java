package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1116] Atualizar Informações da OS para Boletim de Medição
 * 
 * @author Vivianne Sousa
 * @since 01/02/2011
 */
public class ExibirAtualizarInformacoesOSBoletimMedicaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno

		ActionForward retorno = actionMapping.findForward("exibirAtualizarInformacoesOSBoletimMedicao");

		AtualizarInformacoesOSBoletimMedicaoActionForm form = (AtualizarInformacoesOSBoletimMedicaoActionForm) actionForm;

		// Parte que trata do código quando o usuário tecla enter
		String idOrdemServico = form.getIdOrdemServico();
		
		//ORDEM_SERVICO
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			
			 this.pesquisarOrdemServico(idOrdemServico,form,httpServletRequest);
		}else{
			form.setIdOrdemServico("");
			form.setNomeOrdemServico("");
			form.setIdImovel("");
			form.setInscricaoImovel("");
			form.setSituacaoLigAgua("");
			form.setSituacaoLigEsgoto("");
			form.setNomeCliente("");
			form.setCpfCnpjCliente(""); 
			form.setIndicadorExistePavimento(null);
			form.setQtdeReposicaoAsfalto(null);
			form.setQtdeReposicaoParalelo(null);
			form.setQtdeReposicaoCalcada(null);
			form.setExibeIndicadorExistePavimento("2");
			form.setExibeQtdeReposicaoAsfalto("2");
			form.setExibeQtdeReposicaoParalelo("2");
			form.setExibeQtdeReposicaoCalcada("2");
		}

//		//Seta os request´s encontrados
//		this.setaRequest(httpServletRequest,form);
//		
		return retorno;
	}
	
	
	/**
	 * Pesquisa Ordem Serviço 
	 */
	private void pesquisarOrdemServico(String idOrdemServico,
			AtualizarInformacoesOSBoletimMedicaoActionForm form,
			HttpServletRequest httpServletRequest) {
		
		OrdemServico ordemServico = Fachada.getInstancia().
			recuperaOSEDadosImovel(new Integer(idOrdemServico));

		//[FS0001] - Validar Ordem de Serviço
		if (ordemServico == null) {
			form.setNomeOrdemServico("");
			throw new ActionServletException("atencao.naocadastrado", null,"Ordem de Serviço");
		}

		//Caso o serviço associado à Ordem de Serviço não corresponda a um serviço com informações de boletim 
		//(SVTP_ICBOLETIM da tabela SERVICO_TIPO com SVTP_ID=SVTP_ID da tabela ORDEM_SERVICO 
		//com valor correspondente a (Não)–2), exibir a mensagem
		//“O serviço associado a esta ordem de serviço solicitam informações para boletim”.
		if(ordemServico.getServicoTipo().getIndicadorBoletim().equals(ConstantesSistema.NAO)){
			form.setNomeOrdemServico("");
			throw new ActionServletException("atencao.os.solicitam.informacoes.boletim");
		}
		
		//Caso a Ordem de Serviço já tenha sido contabilizada em boletim
		//(ORSE_ID da tabela ORDEM_SERVICO existente na tabela BOLETIM_MEDICAO_OS 
		//exibir a mensagem “Esta ordem de serviço já foi contabilizada em boletim de medição”
		
		
		
		//Caso a Ordem de Serviço não esteja na situação de encerrada 
		//(ORSE_CDSITUAÇÃO na tabela ORDEM_SERVICO com o valor igual a “Pendente” (1)),
		//exibir a mensagem “Esta Ordem de Serviço não está encerrada”
		if(ordemServico.getSituacao() == 1){
			form.setNomeOrdemServico("");
			throw new ActionServletException("atencao.ordem_servico_situacao",
				null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO);
		}
		
		if (ordemServico != null) {

			form.setIdOrdemServico(ordemServico.getId().toString());
			form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
			
			if(ordemServico.getImovel() != null){

				form.setIdImovel(ordemServico.getImovel().getId().toString());
				form.setInscricaoImovel(ordemServico.getImovel().getInscricaoFormatada());
				form.setSituacaoLigAgua(ordemServico.getImovel().getLigacaoAguaSituacao().getDescricao());
				form.setSituacaoLigEsgoto(ordemServico.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				
				 //CLIENTE USUARIO DO IMOVEL
				 Cliente cliente = pesquisarClienteUsuarioImovel(ordemServico.getImovel().getId());
				 form.setNomeCliente(cliente.getNome());
				 
				 //CPF ou CNPJ do Cliente
				 if ( cliente.getCpf() != null ) {
					 form.setCpfCnpjCliente( cliente.getCpfFormatado() );
				 } else if ( cliente.getCnpj() != null ) {
					 form.setCpfCnpjCliente( cliente.getCnpjFormatado() );
				 }
				
			}
		
			ServicoTipo servicoTipo = ordemServico.getServicoTipo();
			//4.Dados das Informações para o boletim:
			verificarInformacoesBoletimMedicao(ordemServico.getId(),servicoTipo,form);
			
			httpServletRequest.setAttribute("ordemServicoEncontrada","true");
		} else {
			
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			
			form.setIdOrdemServico("");
			form.setNomeOrdemServico("Ordem de Serviço inexistente");
			
			form.setIdImovel("");
			form.setInscricaoImovel("");
			form.setSituacaoLigAgua("");
			form.setSituacaoLigEsgoto("");
			form.setNomeCliente("");
			form.setCpfCnpjCliente(""); 
		}
		
//		return ordemServico;
	}
	
	/**
	 * Pesquisa Cliente Usuário
	 */
	private Cliente pesquisarClienteUsuarioImovel(Integer idImovel) {
		
		 Cliente cliente = Fachada.getInstancia().
		 	pesquisarClienteUsuarioImovel(idImovel);

		return cliente;
	}
	
	
	/**
	 * Seta os request com os id encontrados 
	 */
//	private void setaRequest(HttpServletRequest httpServletRequest,AtualizarInformacoesOSBoletimMedicaoActionForm form){
//		
//		//Ordem de Servico
//		if(form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("") && 
//			form.getNomeOrdemServico() != null && !form.getNomeOrdemServico().equals("")){
//
//			httpServletRequest.setAttribute("ordemServicoEncontrada","true");
//		}
//
//	}
	
	 /**
     * [UC1116] Atualizar Informações da OS para Boletim de Medição
     * 
     * @author Vivianne Sousa
     * @created 02/02/2011
     */
    private void verificarInformacoesBoletimMedicao(
    		Integer idOrdemServico,ServicoTipo servicoTipo,
    		AtualizarInformacoesOSBoletimMedicaoActionForm form) {
		
    	String exibeIndicadorExistePavimento = "2";
    	String exibeQtdeReposicaoAsfalto = "2";
    	String exibeQtdeReposicaoCalcada = "2";
    	String exibeQtdeReposicaoParalelo = "2";
    	
//		ServicoTipo servicoTipo = getFachada().
//		 	recuperaServicoTipoDaOrdemServico(idOrdemServico);
		
		if(servicoTipo.getIndicadorBoletim().equals(ConstantesSistema.SIM)){
			//Caso o indicador do serviço da ordem de serviço 
			//que está sendo encerrada tenha indicador para obter 
			//as informações para geração do boletim de medição 
			
			ServicoTipoBoletim servicoTipoBoletim = getFachada().
				recuperaServicoTipoBoletimDoServicoTipo(servicoTipo.getId());
			
			if(servicoTipoBoletim != null){
				
				//1.1.Caso o indicador de pavimento esteja solicitando
				//a informação da existência de pavimento 
				if(servicoTipoBoletim.getIndicadorPavimento().equals(ConstantesSistema.SIM)){
					//1.1.1.O sistema deverá solicitar a informação 
					//de existência do pavimento (Sim ou Não, obrigatoriamente)
					exibeIndicadorExistePavimento = "1";
				}
				
				//1.2.Caso o indicador de quantidade de reposição em m² 
				//de asfalto esteja solicitando a informação do valor 
				if(servicoTipoBoletim.getIndicadorReposicaoAsfalto().equals(ConstantesSistema.SIM)){
					//1.2.1.O sistema deverá solicitar a informação da quantidade de reposição em m² de asfalto.
					//[FS0011 – Validar a quantidade m²]
					exibeQtdeReposicaoAsfalto = "1";
				}

				//1.3.Caso o indicador de quantidade de reposição em m² 
				//de paralelo esteja solicitando a informação do valor 
				if(servicoTipoBoletim.getIndicadorReposicaoParalelo().equals(ConstantesSistema.SIM)){
					//1.3.1.O sistema deverá solicitar a informação da quantidade de reposição em m² de asfalto.
					//[FS0011 – Validar a quantidade m²]
					exibeQtdeReposicaoParalelo = "1";
					
				}
				
				//1.4.Caso o indicador de quantidade de reposição em m² 
				//de calçada esteja solicitando a informação do valor 
				if(servicoTipoBoletim.getIndicadorReposicaoCalcada().equals(ConstantesSistema.SIM)){
					//1.4.1.O sistema deverá solicitar a informação da quantidade de reposição em m² de calçada.
					//[FS0011 – Validar a quantidade m²]
					exibeQtdeReposicaoCalcada = "1";
				}
				
				
				OrdemServicoBoletim ordemServicoBoletim = getFachada().
				recuperaOrdemServicoBoletimDaOS(idOrdemServico);
			
				if(ordemServicoBoletim != null){
					
					form.setIndicadorExistePavimento(ordemServicoBoletim.getIndicadorPavimento().toString());
					
					if(ordemServicoBoletim.getNumeroReposicaoAsfalto() != null){
						
						form.setQtdeReposicaoAsfalto(Util.formatarBigDecimalParaStringComVirgula(
								ordemServicoBoletim.getNumeroReposicaoAsfalto()));
					}else{
						form.setQtdeReposicaoAsfalto(null);
					}
					
					if(ordemServicoBoletim.getNumeroReposicaoParalelo() != null){
						
						form.setQtdeReposicaoParalelo(Util.formatarBigDecimalParaStringComVirgula(
								ordemServicoBoletim.getNumeroReposicaoParalelo()));
					}else{
						form.setQtdeReposicaoParalelo(null);
					}
					
					if(ordemServicoBoletim.getNumeroReposicaoCalcada() != null){
						
						form.setQtdeReposicaoCalcada(Util.formatarBigDecimalParaStringComVirgula(
								ordemServicoBoletim.getNumeroReposicaoCalcada()));
					}else{
						form.setQtdeReposicaoCalcada(null);
					}
					
				}else{
					form.setIndicadorExistePavimento(null);
					form.setQtdeReposicaoAsfalto(null);
					form.setQtdeReposicaoParalelo(null);
					form.setQtdeReposicaoCalcada(null);
				}
					
				
				
			}else{
				//Caso a Ordem de Serviço não tenha informações cadastradas para o boletim
				//(ORSE_ID da tabela ORDEM_SERVICO não existente na tabela ORDEM_SERVICO_BOLETIM
				//exibir a mensagem “Não existem informações desta ordem de serviço para boletim”
				throw new ActionServletException("atencao.nao.existe.informacoes.boletim");
			}
		}
		
		form.setExibeIndicadorExistePavimento(exibeIndicadorExistePavimento);
		form.setExibeQtdeReposicaoAsfalto(exibeQtdeReposicaoAsfalto);
		form.setExibeQtdeReposicaoParalelo(exibeQtdeReposicaoParalelo);
		form.setExibeQtdeReposicaoCalcada(exibeQtdeReposicaoCalcada);
		
	}
	
}
