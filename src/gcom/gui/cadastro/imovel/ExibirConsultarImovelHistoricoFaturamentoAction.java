package gcom.gui.cadastro.imovel;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0473] Consultar Imovel
 * Metodo da 4° Aba - Histórico de  Faturamento
 * 
 * @author Rafael Santos
 * @since 13/09/2006
 * 
 */
public class ExibirConsultarImovelHistoricoFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = getSessao(httpServletRequest);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

		if(isLimparDadosTela(httpServletRequest)){

			httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

			limparFormSessao(consultarImovelActionForm, sessao);

		}else if( isImovelInformadoTelaHistoricoFaturamento(consultarImovelActionForm)
				|| isImovelInformadoOutraTela(sessao) ){

			consultarImovelActionForm.setIdImovelHistoricoFaturamento(
					definirIdImovelASerPesquisado(consultarImovelActionForm, sessao, httpServletRequest));

			Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm, sessao);

	        //deve ser chamado antes dos novos valores da sessão serem setados
			boolean imovelNovoPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);

			if (imovel != null) {
				

				sessao.setAttribute("imovelDadosHistoricoFaturamento", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());

				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

				consultarImovelActionForm.setIdImovelHistoricoFaturamento(imovel.getId().toString());

				if(imovelNovoPesquisado){

					httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

					setarDadosImovelNoFormESessao(consultarImovelActionForm, imovel, sessao);

				}

			} else {
				limparFormSessao(consultarImovelActionForm, sessao);
				
				httpServletRequest.setAttribute("idImovelHistoricoFaturamentoNaoEncontrado", "true");

				consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento("IMÓVEL INEXISTENTE");
			}

		}else{
        	String idImovelAux = consultarImovelActionForm.getIdImovelHistoricoFaturamento();

         	httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

            limparFormSessao(consultarImovelActionForm, sessao);

        	consultarImovelActionForm.setIdImovelHistoricoFaturamento(idImovelAux);
		}

		return actionMapping.findForward("consultarImovelHistoricoFaturamento");
	}

	/**
	 * Esse método seta os dados necessários do Imovel
	 * no form e alguns na sessão (coleções).
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosImovelNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao) {

		consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento(Fachada.getInstancia()
				.pesquisarInscricaoImovelExcluidoOuNao(new Integer(
						consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim())));

		consultarImovelActionForm.setSituacaoAguaHistoricoFaturamento(
				imovel.getLigacaoAguaSituacao().getDescricao());

		consultarImovelActionForm
		.setSituacaoEsgotoHistoricoFaturamento(imovel
				.getLigacaoEsgotoSituacao().getDescricao());

		sessao.setAttribute("colecaoContaImovel", Fachada.getInstancia().consultarContasImovel(imovel.getId()));
		sessao.setAttribute("colecaoContaHistoricoImovel", Fachada.getInstancia().consultarContasHistoricosImovel(imovel.getId()));

		sessao.setAttribute("colecaoDebitoACobrarImovel", obterListaDebitoACobrarOrdenadaPorReferencia(imovel)); 
		sessao.setAttribute("colecaoDebitoACobrarHistoricoImovel", obterListaDebitoACobrarHistoricoOrdenadaPorReferencia(imovel)); 

		sessao.setAttribute("colecaoCreditoARealizarImovel", obterListaCreditoARealizarOrdenadaPorReferencia(imovel)); 
		sessao.setAttribute("colecaoCreditoARealizarHistoricoImovel", obterListaCreditoARealizarHistoricoOrdenadaPorReferencia(imovel)); 

		sessao.setAttribute("colecaoGuiaPagamentoImovel", Fachada.getInstancia().obterGuiaPagamentoImovel(imovel.getId())); 
		sessao.setAttribute("colecaoGuiaPagamentoHistoricoImovel", Fachada.getInstancia().obterGuiaPagamentoHistoricoImovel(imovel.getId())); 

		setarTamanhoColacoesSessao(sessao);
	}

	/**
	 *Esse método limpa todos os atributos do form
	 *e os atributos na sessão 
	 *que são usados pelo action e/ou jsp.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("imovelDadosHistoricoFaturamento");

		sessao.removeAttribute("colecaoContaImovel");
		sessao.removeAttribute("colecaoContaHistoricoImovel");
		sessao.removeAttribute("colecaoDebitoACobrarImovel"); 
		sessao.removeAttribute("colecaoDebitoACobrarHistoricoImovel"); 
		sessao.removeAttribute("colecaoCreditoARealizarImovel"); 
		sessao.removeAttribute("colecaoCreditoARealizarHistoricoImovel");
		sessao.removeAttribute("colecaoGuiaPagamentoImovel"); 
		sessao.removeAttribute("colecaoGuiaPagamentoHistoricoImovel");
		sessao.removeAttribute("tamanhoColecaoDebitos"); 
		sessao.removeAttribute("tamanhoColecaoCreditos"); 
		sessao.removeAttribute("tamanhoColecaoGuias"); 

		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setSituacaoAguaHistoricoFaturamento(null);
		consultarImovelActionForm.setSituacaoEsgotoHistoricoFaturamento(null);
		consultarImovelActionForm.setConta(null);
		consultarImovelActionForm.setDescricaoImovel(null);

		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);
	}

	/**
	 *Esse método seta na sessão os tamanhos das coleções de 
	 *débito(debito a cobrar + debito historico) ,
	 *crédito(credito a realizar + credito historico)  e 
	 *guias de pagamento(guias + guias historico).
	 *
	 *@since --
	 *@author --
	 */
	private void setarTamanhoColacoesSessao(HttpSession sessao) {

		Collection<DebitoACobrarHistorico> colecaoDebitoACobrarHistoricoImovel = (Collection<DebitoACobrarHistorico>)sessao.getAttribute("colecaoDebitoACobrarHistoricoImovel");
		Collection<DebitoACobrar> colecaoDebitoACobrarImovel = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrarImovel");
		Collection<CreditoARealizarHistorico> colecaoCreditoARealizarHistoricoImovel = (Collection<CreditoARealizarHistorico>)sessao.getAttribute("colecaoCreditoARealizarHistoricoImovel");
		Collection<CreditoARealizar> colecaoCreditoARealizarImovel = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizarImovel");
		Collection<GuiaPagamentoHistorico> colecaoGuiaPagamentoHistoricoImovel =(Collection<GuiaPagamentoHistorico>)sessao.getAttribute("colecaoGuiaPagamentoHistoricoImovel");
		Collection<GuiaPagamento> colecaoGuiaPagamentoImovel =(Collection<GuiaPagamento>)sessao.getAttribute("colecaoGuiaPagamentoImovel");

		Integer tamanhoColecaoDebitos = 0;
		Integer tamanhoColecaoCreditos = 0;
		Integer tamanhoColecaoGuias = 0;

		if(colecaoDebitoACobrarImovel != null){
			tamanhoColecaoDebitos = colecaoDebitoACobrarImovel.size();
		}
		if(colecaoDebitoACobrarHistoricoImovel != null){
			tamanhoColecaoDebitos = tamanhoColecaoDebitos + colecaoDebitoACobrarHistoricoImovel.size();
		}

		if(colecaoCreditoARealizarImovel != null){
			tamanhoColecaoCreditos = colecaoCreditoARealizarImovel.size();
		}
		if(colecaoCreditoARealizarHistoricoImovel != null){
			tamanhoColecaoCreditos = tamanhoColecaoCreditos + colecaoCreditoARealizarHistoricoImovel.size();
		}

		if(colecaoGuiaPagamentoImovel != null){
			tamanhoColecaoGuias = colecaoGuiaPagamentoImovel.size();
		}
		if(colecaoGuiaPagamentoHistoricoImovel != null){
			tamanhoColecaoGuias = tamanhoColecaoGuias + colecaoGuiaPagamentoHistoricoImovel.size();
		}

		sessao.setAttribute("tamanhoColecaoDebitos", tamanhoColecaoDebitos); 
		sessao.setAttribute("tamanhoColecaoCreditos", tamanhoColecaoCreditos); 
		sessao.setAttribute("tamanhoColecaoGuias", tamanhoColecaoGuias); 

	}

	/**
	 * Caso o usuário tenha clicado no botão de limpar
	 * esse método retornará true.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 * Esse método verifica se já foi informado um imóvel em outra tela.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));		
	}

	/**
	 * Esse método verifica se o imóvel foi informado na tela
	 * de Historico de Faturamento
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaHistoricoFaturamento(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelHistoricoFaturamento());
	}

	/**
	 * Esse método retorna o id do imóvel a ser pesquisado,
	 * verificando se é o id possivelmente informado pelo usuário na tela 
	 * de Historico Faturamento ou se o id já informado em uma outra tela.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {

		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");

		if( isImovelInformadoTelaHistoricoFaturamento(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){

			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
				return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
			return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelHistoricoFaturamento();
	}

	/**
	 * Consulta o Imovel com todas as informações necessárias,
	 * ou simplesmetne pega o Imovel da sessão caso o mesmo já
	 * tenha sido pesquisado.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosHistoricoFaturamento") == null){
			imovel = Fachada.getInstancia().consultarImovelHistoricoFaturamento(new Integer(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosHistoricoFaturamento");

			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelHistoricoFaturamento(new Integer(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()));
			}
		}
		return imovel;
	}


	/**
	 * Esse método retorna true se foi necessário consultar um novo imovel.
	 * Caso o imóvel seja o mesmo já consultado anteriormente ele retorna false.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		if(sessao.getAttribute("imovelDadosHistoricoFaturamento") == null){
			return true;
		}

		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosHistoricoFaturamento");

		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()) ){
			return true;
		}

		return false;
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	private Collection obterListaDebitoACobrarOrdenadaPorReferencia(Imovel imovel) {
		
		List list = new ArrayList();
		list.addAll(Fachada.getInstancia().obterDebitoACobrarImovel(imovel.getId()));
		Collections.sort(list, new Comparator<DebitoACobrar>() {

			public int compare(DebitoACobrar o1, DebitoACobrar o2) {
				return o2.getAnoMesCobrancaDebito().compareTo(o1.getAnoMesCobrancaDebito());
			}
		});
		
		return list;
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	private Collection obterListaDebitoACobrarHistoricoOrdenadaPorReferencia(Imovel imovel) {
		
		List list = new ArrayList();
		list.addAll(Fachada.getInstancia().obterDebitoACobrarHistoricoImovel(imovel.getId()));
		Collections.sort(list, new Comparator<DebitoACobrarHistorico>() {

			public int compare(DebitoACobrarHistorico o1, DebitoACobrarHistorico o2) {
				return o2.getAnoMesCobrancaDebito().compareTo(o1.getAnoMesCobrancaDebito());
			}
		});
		
		return list;
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	private Collection obterListaCreditoARealizarOrdenadaPorReferencia(Imovel imovel) {
		
		List list = new ArrayList();
		list.addAll(Fachada.getInstancia().obterCreditoARealizarImovel(imovel.getId()));
		Collections.sort(list, new Comparator<CreditoARealizar>() {

			public int compare(CreditoARealizar o1, CreditoARealizar o2) {
				return o2.getAnoMesCobrancaCredito().compareTo(o1.getAnoMesCobrancaCredito());
			}
		});
		
		return list;
	}

	@SuppressWarnings({"rawtypes","unchecked"})
	private Collection obterListaCreditoARealizarHistoricoOrdenadaPorReferencia(Imovel imovel) {
		
		List list = new ArrayList();
		list.addAll(Fachada.getInstancia().obterCreditoARealizarHistoricoImovel(imovel.getId()));
		Collections.sort(list, new Comparator<CreditoARealizarHistorico>() {

			public int compare(CreditoARealizarHistorico o1, CreditoARealizarHistorico o2) {
				return o2.getAnoMesCobrancaCredito().compareTo(o1.getAnoMesCobrancaCredito());
			}
		});
		
		return list;
	}

}
