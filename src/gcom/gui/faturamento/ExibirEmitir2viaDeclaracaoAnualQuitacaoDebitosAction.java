package gcom.gui.faturamento;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ExtratoQuitacao;
import gcom.faturamento.FiltroExtratoQuitacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction extends
		GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm 
			emitir2ViaDeclaracaoAnualQuitacaoDebitosActionForm = 
				(Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm) actionForm;
		
		// Verifica se entrou apartir
		// do menu
		//
		if(httpServletRequest.getParameter("menu")!= null){
			sessao.setAttribute("veioMenu", "sim");
			this.limpar(emitir2ViaDeclaracaoAnualQuitacaoDebitosActionForm, sessao);
			
		}
		
		// Pesquisa Imovel
		// Enter ou Reload PopUp
		//
		if(httpServletRequest.getParameter("pesquisarImovel")!=null
				&& httpServletRequest.getParameter("pesquisarImovel").toString().equalsIgnoreCase("sim")){
			
			if(httpServletRequest.getParameter("idImovel")!=null
					&& !httpServletRequest.getParameter("idImovel").toString().equals("")){
				emitir2ViaDeclaracaoAnualQuitacaoDebitosActionForm.setMatriculaImovel(httpServletRequest.getParameter("idImovel"));	
			}
			
			if(emitir2ViaDeclaracaoAnualQuitacaoDebitosActionForm.getMatriculaImovel()!=null && 
				!emitir2ViaDeclaracaoAnualQuitacaoDebitosActionForm.getMatriculaImovel().equals("")){
								
				getImovel(emitir2ViaDeclaracaoAnualQuitacaoDebitosActionForm,httpServletRequest);
			}
			
		}		
		
		return retorno;
	}
	
	/**
	 * Recupera Imóvel 
	 *
	 *
	 * @param ParcelamentoCartaoConfirmarForm
	 * @param fachada
	 */
	private void getImovel(Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm form,
			HttpServletRequest httpServletRequest) {
		
			Fachada fachada = Fachada.getInstancia();
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			HttpSession sessao = httpServletRequest.getSession(false);

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 
					form.getMatriculaImovel()));
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			
			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {	
				
				
				for (Iterator iterator = colecaoImovel.iterator(); iterator.hasNext();) {
					Imovel imovelTeste = (Imovel) iterator.next();
					
					FiltroExtratoQuitacao filtro = new FiltroExtratoQuitacao();
					
					filtro.adicionarParametro(new ParametroSimples(
							FiltroExtratoQuitacao.ID_IMOVEL, imovelTeste.getId()));
					
					this.pesquisarAnosImovel(imovelTeste.getId().toString(), httpServletRequest);
					
					//se o ano do imovel for selecionado.
					if(form.getAno() != null && !form.getAno().equals("-1")){	
					
						filtro.adicionarParametro(new ParametroSimples(
								FiltroExtratoQuitacao.ANO_REFERENCIA,form.getAno()));
						
						
						Collection colecaoExtratoQuitacao =
							fachada.pesquisar(filtro, ExtratoQuitacao.class.getName());
						
						if(colecaoExtratoQuitacao==null || (colecaoExtratoQuitacao!=null && colecaoExtratoQuitacao.isEmpty())){
							
							if(fachada.verificarQuitacaoContas(new Integer(form.getAno()),imovelTeste.getId())){
								throw new ActionServletException("atencao.imovel_nao_possui_extrato", imovelTeste.getId().toString());
							}
							
							
							ObterDebitoImovelOuClienteHelper colecaoDebitoCliente = null;	
							/*
							1.1.	Indicador de débito do imóvel ou cliente com o valor 1 (imóvel);
							1.2.	Matrícula do Imóvel (IMOV_ID);
							1.3.	Código do cliente com o valor nulo;
							1.4.	Tipo de relação do cliente com o imóvel com o valor nulo;
							1.5.	Período de referência do débito (01/ano informado a 12/ano informado);
							1.6.	Período de vencimento do débito com valor nulo;
							1.7.	Indicador de pagamento com o valor 1 (sim); 
							1.8.	Indicador de conta em revisão com o valor 1 (sim, se SISTEMA_PARAMETROS.PARM_ICCOBRANCAJUDICIAL=1);
							1.9.	Indicador de débito a cobrar com o valor 2 (não);
							1.10.	Indicador de crédito a realizar com o valor 2 (não);
							1.11.	Indicador de notas promissórias com o valor 2 (não);
							1.12.	Indicador de guias de com o valor 2 (não);
							1.13.	Indicador de atualizar débito com o valor 2 (não);
							*/
							String anoMesInicial = form.getAno()+"01";
							String anoMesFinal = form.getAno()+"12";
							
							String dataVencimentoInicial = "01/01/0001";
							String dataVencimentoFinal = "31/12/9999";
							
							SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	
							Date dataVencimentoDebitoI;
							Date dataVencimentoDebitoF;
	
							try {
								dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
							} catch (ParseException ex) {
								dataVencimentoDebitoI = null;
							}
							try {
								dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
							} catch (ParseException ex) {
								dataVencimentoDebitoF = null;
							}
							
							
							colecaoDebitoCliente = fachada.obterDebitoImovelOuCliente(
										1, form.getMatriculaImovel().toString(), null, null,
										anoMesInicial, anoMesFinal, dataVencimentoDebitoI,
										dataVencimentoDebitoF, 1,
										sistemaParametro.getIndicadorCobrancaJudical().intValue(), ConstantesSistema.NAO.intValue(),
										ConstantesSistema.NAO.intValue(), ConstantesSistema.NAO.intValue(),
										ConstantesSistema.NAO.intValue(), ConstantesSistema.NAO.intValue(),
										null);
							
							if(colecaoDebitoCliente!=null && (colecaoDebitoCliente.getColecaoContasValores()!=null &&
									!colecaoDebitoCliente.getColecaoContasValores().isEmpty())){
								
								
	
								for (Iterator itera = colecaoDebitoCliente.getColecaoContasValores().iterator(); itera.hasNext();) {
									ContaValoresHelper helper = (ContaValoresHelper) itera.next();
									
									if(helper.getConta().getContaMotivoRevisao()!=null){
										itera.remove();
									}
									
								}
								
								if(!colecaoDebitoCliente.getColecaoContasValores().isEmpty()){
									throw new ActionServletException("atencao.imovel_possui_debitos");
								}
							}
							
							throw new ActionServletException("atencao.imovel_nao_possui_extrato",imovelTeste.getId().toString());
							
						}else{
													
							ExtratoQuitacao extrato = (ExtratoQuitacao) Util.retonarObjetoDeColecao(colecaoExtratoQuitacao);
							
							if(extrato.getIndicadorImpressao().compareTo(ConstantesSistema.NAO.intValue())==0){
								throw new ActionServletException("atencao.imovel_nao_possui_extrato", imovelTeste.getId().toString());
							}
						}
					}
				}	
				
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);				
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				form.setInscricaoImovel(imovel.getInscricaoFormatada());

			} else {			
				this.limpar(form, sessao);
				sessao.removeAttribute("inscricaoImovelEncontrada");
				form.setMatriculaImovel("");
				form.setInscricaoImovel("Matrícula inexistente");
				
			}
	}
	
	private void limpar(Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm form,HttpSession sessao){

		form.setInscricaoImovel(null);
		form.setMatriculaImovel(null);
		form.setAno(null);
		
	}
	
   /**
    * @author Daniel Alves
    * @date 16/09/2010
    * @param httpServletRequest
    */
   private void pesquisarAnosImovel(String idImovel, HttpServletRequest httpServletRequest){
		
		Collection colecaoAnosImovel = 
			this.getFachada().pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(idImovel);
		if (colecaoAnosImovel == null || colecaoAnosImovel.isEmpty()) {
			throw new ActionServletException("atencao.imovel_nao_possui_extrato", idImovel);
		} else {
			httpServletRequest.setAttribute("colecaoAnosImovel",colecaoAnosImovel);
			
		}
	}

}
