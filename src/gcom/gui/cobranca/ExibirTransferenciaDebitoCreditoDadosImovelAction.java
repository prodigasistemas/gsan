package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da transferência de débitos e créditos entre imóveis
 * 
 * @author Raphael Rossiter
 * @date 06/06/2007
 */
public class ExibirTransferenciaDebitoCreditoDadosImovelAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
		.findForward("transferenciaDebitoCreditoDadosImovel");

		TransferenciaDebitoCreditoDadosImovelActionForm form = 
		(TransferenciaDebitoCreditoDadosImovelActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		// REGISTRO ATENDIMENTO
		String pesquisarRA = httpServletRequest.getParameter("pesquisarRA");
		if (pesquisarRA != null && !pesquisarRA.equals("")){
			
			// [FS0004] Validar Registro Atendimento
			Object[] dadosRA = fachada.validarRegistroAtendimentoTransferenciaDebitoCredito(
			new Integer(form.getIdRegistroAtendimento()), false);
			
			String descricaoRA = (String) dadosRA[0];
			Short ocorreuErro = (Short) dadosRA[1];
			Integer idImovel = (Integer) dadosRA[2];
			
			 if (ocorreuErro.equals(ConstantesSistema.SIM)){
				 limparDadosRA(form);
				 httpServletRequest.setAttribute("corRA", "exception");
				 httpServletRequest.setAttribute("nomeCampo", "idRegistroAtendimento");
			 }
			 else{
				 
				 //MATRICULA IMOVEL
				 form.setIdImovelOrigem(idImovel.toString());
				 
				 //INSCRICAO IMOVEL
				 String inscricaoImovel = fachada.pesquisarInscricaoImovel(idImovel);
				 form.setInscricaoImovelOrigem(inscricaoImovel);
				 
				 //CLIENTE USUARIO DO IMOVEL
				 Cliente cliente = fachada.pesquisarClienteUsuarioImovel(idImovel);
				 form.setNomeClienteUsuarioImovelOrigem(cliente.getNome());
				 
				 //LIGACAO AGUA SITUACAO
				 LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(idImovel);
				 form.setDescricaoLigacaoAguaSituacaoImovelOrigem(ligacaoAguaSituacao.getDescricao());
				 
				 //LIGACAO ESOTO SITUACAO
				 LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(idImovel);
				 form.setDescricaoLigacaoEsgotoSituacaoImovelOrigem(ligacaoEsgotoSituacao.getDescricao());
				 
				 httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
				
			 }
			 
			 //DESCRICAO RA
			 form.setDescricaoEspecificacaoRA(descricaoRA);
		}
		
		
		// IMOVEL DESTINO
		String pesquisarImovelDestino = httpServletRequest.getParameter("pesquisarImovelDestino");
		if (pesquisarImovelDestino != null && !pesquisarImovelDestino.equals("")){
			
			Integer idImovelDestino = new Integer(form.getIdImovelDestino());
			String inscricaoImovel = fachada.pesquisarInscricaoImovel(idImovelDestino);
			
			if (inscricaoImovel == null){
				 limparDadosImovelDestino(form);
				 form.setInscricaoImovelDestino("IMOVEL INEXISTENTE");
				 httpServletRequest.setAttribute("corImovelDestino", "exception");
				 httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			}
			else{
				 
				// [FS0006] - Verificar Imóvel Destino
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				if (sistemaParametro.getIndicadorTransferenciaComDebito().equals(ConstantesSistema.NAO)) {
					
					String anoMesInicial = "000101";
					String anoMesFinal = "999912";
					
					String dataVencimentoInicialFormatada = "01/01/0001";
					
					Date dataVencimentoInicial = Util.converteStringParaDate(dataVencimentoInicialFormatada);
					Date dataVencimentoFinal = Util.adicionarNumeroDiasDeUmaData(new Date(), 3); 
					
					
					ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = fachada.obterDebitoImovelOuCliente(
							// Indicador de débito do imóvel
							1,
							
							// Matrícula do Imóvel
							idImovelDestino.toString(),
							
							// Código do Cliente
							null,
							
							// Tipo da Relação
							null, 
							
							// Mês/Ano Inicial de Referência do Débito
							anoMesInicial,
							
							// Mês/Ano Final de Referência do Débito
							anoMesFinal,
							
							// Data Vencimento Inicial
							dataVencimentoInicial,
							
							// Data Vencimento Final
							dataVencimentoFinal,
							
							// Indicador de Pagamento com Valor
							1,
							
							// Indicador de Conta em Revisão com Valor
							1,
							
							// Indicador de Débito a Cobrar com Valor
							1,
							
							// Indicador de Crédito a Realizar com Valor
							1,
							
							// Indicador de Notas Promissórias com Valor
							1,
							
							// Indicador de Guias de Pagamento com Valor
							1,
							
							// Indicador Calcular Acréscimos por Impontualidade
							1,
							
							// Indicador de Atualizar Débito com Valor
							true);
					
					if ((obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel() != null && !obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null && !obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null && !obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoDebitoCreditoParcelamentoHelper() != null && !obterDebitoImovelOuClienteHelper.getColecaoDebitoCreditoParcelamentoHelper().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty())) {
						
						throw new ActionServletException("atencao.imovel_destino_com_debito", null, idImovelDestino.toString());
						
					}
				}
				
				 //INSCRICAO IMOVEL
				 form.setInscricaoImovelDestino(inscricaoImovel);
				 
				 //CLIENTE USUARIO DO IMOVEL
				 Cliente cliente = fachada.pesquisarClienteUsuarioImovel(idImovelDestino);
				 form.setNomeClienteUsuarioImovelDestino(cliente.getNome());
				 
				 //LIGACAO AGUA SITUACAO
				 LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(idImovelDestino);
				 form.setDescricaoLigacaoAguaSituacaoImovelDestino(ligacaoAguaSituacao.getDescricao());
				 
				 //LIGACAO ESOTO SITUACAO
				 LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(idImovelDestino);
				 form.setDescricaoLigacaoEsgotoSituacaoImovelDestino(ligacaoEsgotoSituacao.getDescricao());
				 
				 httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
				
			}
		}
	
		return retorno;
	}
	
	
	private void limparDadosRA(TransferenciaDebitoCreditoDadosImovelActionForm form){
		
		form.setIdRegistroAtendimento("");
		form.setDescricaoEspecificacaoRA("");
		form.setIdImovelOrigem("");
		form.setInscricaoImovelOrigem("");
		form.setNomeClienteUsuarioImovelOrigem("");
		form.setDescricaoLigacaoAguaSituacaoImovelOrigem("");
		form.setDescricaoLigacaoEsgotoSituacaoImovelOrigem("");
		
	}
	
	
	private void limparDadosImovelDestino(TransferenciaDebitoCreditoDadosImovelActionForm form){
		
		form.setIdImovelDestino("");
		form.setInscricaoImovelDestino("");
		form.setNomeClienteUsuarioImovelDestino("");
		form.setDescricaoLigacaoAguaSituacaoImovelDestino("");
		form.setDescricaoLigacaoEsgotoSituacaoImovelDestino("");
		
	}

}
