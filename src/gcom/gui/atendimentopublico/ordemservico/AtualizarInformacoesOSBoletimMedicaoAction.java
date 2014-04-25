package gcom.gui.atendimentopublico.ordemservico;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class AtualizarInformacoesOSBoletimMedicaoAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		AtualizarInformacoesOSBoletimMedicaoActionForm form = (AtualizarInformacoesOSBoletimMedicaoActionForm) actionForm;

		String idOrdemServico = form.getIdOrdemServico();

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")
			&& (form.getNomeOrdemServico() == null || form.getNomeOrdemServico().equals("")) ) {
			OrdemServico ordemServico = pesquisarOrdemServico(idOrdemServico,form,httpServletRequest);

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (ordemServico == null) {
				throw new ActionServletException("atencao.naocadastrado", null,"Ordem de Serviço");
			}
		}

	    String valorConfirmacaoBoletimValorZero = ( String ) httpServletRequest.getParameter( "confirmado" ); 
		Map validacao = validarInformacoesBoletimMedicao(new Integer(form.getIdOrdemServico()),form);
		
		OrdemServicoBoletim ordemServicoBoletim = (OrdemServicoBoletim)validacao.get("ordemServicoBoletim");
		Boolean exibirMsgConfirmacao = (Boolean)validacao.get("exibirMsgConfirmacao");				
		
		if(exibirMsgConfirmacao && (valorConfirmacaoBoletimValorZero == null || 
				!valorConfirmacaoBoletimValorZero.equalsIgnoreCase("ok"))){
			
			httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/atualizarInformacoesOSBoletimMedicaoAction.do");
			httpServletRequest.setAttribute("nomeBotao1", "Sim");
			httpServletRequest.setAttribute("tipoRelatorio", "confirmaBoletimValorZero");
			
			return montarPaginaConfirmacao("atencao.encerrar_OS_boletim",httpServletRequest, actionMapping);
		}else{
			
			fachada.inserirOuAtualizar(ordemServicoBoletim);
			
			montarPaginaSucesso(httpServletRequest, 
				"Informações da OS para Boletim de Medição atualizada com sucesso.",
				"Realizar outra atualização de Informações da OS para Boletim de Medição",
		        "exibirAtualizarInformacoesOSBoletimMedicaoActionAction.do?menu=sim");
			
		}
	
		return retorno;

	}
	
	
	/**
     * [UC0457] Encerrar Ordem de Serviço
     * [SB0007]- Gerar Informações para Boletim de Medição.
     * 
     * @author Vivianne Sousa
     * @created 28/01/2011
     */
    private Map validarInformacoesBoletimMedicao(
    		Integer idOrdemServico,AtualizarInformacoesOSBoletimMedicaoActionForm form) {
		
    	OrdemServicoBoletim ordemServicoBoletim = null;
    	Boolean exibirMsgConfirmacao = false;
    	
    	if(form.getExibeIndicadorExistePavimento().equals("1")
		   || form.getExibeQtdeReposicaoAsfalto().equals("1")
		   || form.getExibeQtdeReposicaoCalcada().equals("1")	
		   || form.getExibeQtdeReposicaoParalelo().equals("1")){
    		
    		ordemServicoBoletim = new OrdemServicoBoletim();
    		ordemServicoBoletim.setId(idOrdemServico);
    		OrdemServico os = new OrdemServico();
    		os.setId(idOrdemServico);
    		ordemServicoBoletim.setOrdemServico(os);
    		
    		if(form.getExibeIndicadorExistePavimento().equals("1")){
    			
    			if(form.getIndicadorExistePavimento() == null){
    				
    				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",
    	 					null,"Existe Pavimento");
    			}else{
    				ordemServicoBoletim.setIndicadorPavimento(
    					new Short(form.getIndicadorExistePavimento()));
    			}
    			
    		}
    		
    		if(form.getExibeQtdeReposicaoAsfalto().equals("1")){
    			
    			if(form.getQtdeReposicaoAsfalto() == null || form.getQtdeReposicaoAsfalto().equals("")){
    				
    				form.setQtdeReposicaoAsfalto("0");
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else if(form.getQtdeReposicaoAsfalto().equals("0") ||
    					form.getQtdeReposicaoAsfalto().equals("00") ||
    					form.getQtdeReposicaoAsfalto().equals("0,00")){
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(Util.
    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoAsfalto()));
    			}
    			
    			
    		}else{
    			ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
    		}
    		
    		if(form.getExibeQtdeReposicaoParalelo().equals("1")){
    			
    			if(form.getQtdeReposicaoParalelo() == null || form.getQtdeReposicaoParalelo().equals("") ){
    				
    				form.setQtdeReposicaoParalelo("0");
    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else if(form.getQtdeReposicaoParalelo().equals("0") ||
    					form.getQtdeReposicaoParalelo().equals("00")||
    					form.getQtdeReposicaoParalelo().equals("0,00")){
    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoParalelo(Util.
    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoParalelo()));
    			}
    			
    			
    		}else{
    			ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    		}
    		
    		if(form.getExibeQtdeReposicaoCalcada().equals("1")){
    			
    			if(form.getQtdeReposicaoCalcada() == null || form.getQtdeReposicaoCalcada().equals("")){
    				
    				form.setQtdeReposicaoCalcada("0");
    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else if(form.getQtdeReposicaoCalcada().equals("0")||
    					form.getQtdeReposicaoCalcada().equals("00")||
    					form.getQtdeReposicaoCalcada().equals("0,00")){
    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoCalcada(Util.
    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoCalcada()));
    			}
    			
    			
    		}else{
    			ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    		}
    	
    		ordemServicoBoletim.setUltimaAlteracao(new Date());
    	}
    	
    	Map retorno = new HashMap();
    	retorno.put("ordemServicoBoletim",ordemServicoBoletim);
    	retorno.put("exibirMsgConfirmacao",exibirMsgConfirmacao);
    	
		return retorno;
	}

    /**
	 * Pesquisa Ordem Serviço 
	 */
	private OrdemServico pesquisarOrdemServico(String idOrdemServico,
			AtualizarInformacoesOSBoletimMedicaoActionForm form,
			HttpServletRequest httpServletRequest) {
		
		OrdemServico ordemServico = Fachada.getInstancia().
			recuperaOSEDadosImovel(new Integer(idOrdemServico));

		//[FS0001] - Validar Ordem de Serviço

		//Caso o serviço associado à Ordem de Serviço não corresponda a um serviço com informações de boletim 
		//(SVTP_ICBOLETIM da tabela SERVICO_TIPO com SVTP_ID=SVTP_ID da tabela ORDEM_SERVICO 
		//com valor correspondente a (Não)–2), exibir a mensagem
		//“O serviço associado a esta ordem de serviço solicitam informações para boletim”.
		if(ordemServico.getServicoTipo().getIndicadorBoletim().equals(ConstantesSistema.NAO)){
			throw new ActionServletException("atencao.os.solicitam.informacoes.boletim");
		}
		
		//Caso a Ordem de Serviço já tenha sido contabilizada em boletim
		//(ORSE_ID da tabela ORDEM_SERVICO existente na tabela BOLETIM_MEDICAO_OS 
		//exibir a mensagem “Esta ordem de serviço já foi contabilizada em boletim de medição”
		
		
		
		//Caso a Ordem de Serviço não esteja na situação de encerrada 
		//(ORSE_CDSITUAÇÃO na tabela ORDEM_SERVICO com o valor igual a “Pendente” (1)),
		//exibir a mensagem “Esta Ordem de Serviço não está encerrada”
		if(ordemServico.getSituacao() == 1){
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
		
		return ordemServico;
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
