package gcom.gui.gerencial.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Sávio Luiz
 * @date 26/06/2007
 * 
 */
public class ExibirConsultarResumoAcaoCobrancaEventualPopupAction extends
		GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarResumoAcaoCobrancaEventualPopup");

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = getSessao(httpServletRequest);
		
		InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper = 
        	(InformarDadosGeracaoResumoAcaoConsultaEventualHelper)sessao.getAttribute("informarDadosGeracaoResumoAcaoConsultaEventualHelper");
		
		Integer idCobrancaAcao =  new Integer(httpServletRequest.getParameter("idCobrancaAcao").trim());
		FiltroCobrancaAcao filtroCobrancaAcao =  new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, idCobrancaAcao));
		filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
		Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());

		if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
			Iterator iteratorCobrancaAcao = colecaoCobrancaAcao.iterator();
			CobrancaAcao cobrancaAcao = (CobrancaAcao) iteratorCobrancaAcao.next();
			httpServletRequest.setAttribute("cobrancaAcao", cobrancaAcao.getDescricaoCobrancaAcao());
			if(cobrancaAcao.getCobrancaCriterio() != null && !cobrancaAcao.getCobrancaCriterio().equals("")){
				httpServletRequest.setAttribute("valorLimitePrioridade", Util.formatarMoedaReal(cobrancaAcao.getCobrancaCriterio().getValorLimitePrioridade()));
			}
		}
		Integer idCobrancaAcaoSituacao = null;
		if(httpServletRequest.getParameter("idCobrancaAcaoSituacao") != null){
			idCobrancaAcaoSituacao =  new Integer(httpServletRequest.getParameter("idCobrancaAcaoSituacao").trim());
			httpServletRequest.setAttribute("cobrancaAcaoSituacao", httpServletRequest.getParameter("cobrancaAcaoSituacao"));
		}else{
			httpServletRequest.setAttribute("cobrancaAcaoSituacao", "EMITIDOS");
		}
		
		httpServletRequest.setAttribute("quantidadeTotal",httpServletRequest.getParameter("quantidadeTotal"));
		httpServletRequest.setAttribute("valorTotal",httpServletRequest.getParameter("valorTotal").trim());
		String valorTotalFormatado = Util.formatarMoedaReal(new BigDecimal(httpServletRequest.getParameter("valorTotal").trim()));
		httpServletRequest.setAttribute("valorTotalFormatado",valorTotalFormatado);
		Integer idCobrancaAcaoDebito = null;
		Short idIndicador = null;
		if(httpServletRequest.getParameter("idIndicador") != null && !httpServletRequest.getParameter("idIndicador").equals("")){
		  idIndicador =  new Short(httpServletRequest.getParameter("idIndicador"));  
		}
		if(httpServletRequest.getParameter("idCobrancaAcaoDebito") != null){
		  idCobrancaAcaoDebito =  new Integer(httpServletRequest.getParameter("idCobrancaAcaoDebito").trim());
		  httpServletRequest.setAttribute("cobrancaAcaoDebito", httpServletRequest.getParameter("cobrancaAcaoDebito"));	
		  httpServletRequest.setAttribute("idCobrancaAcaoDebito", idCobrancaAcaoDebito); 
		}
		
		Collection colecaoResumoCobrancaAcaoPerfil = fachada.consultarResumoCobrancaAcaoEventualPerfil(idCobrancaAcao, 
				idCobrancaAcaoSituacao, idCobrancaAcaoDebito, idIndicador,informarDadosGeracaoResumoAcaoConsultaEventualHelper);
		
		sessao.setAttribute("colecaoResumoCobrancaAcaoPerfil", colecaoResumoCobrancaAcaoPerfil);

		return retorno;
	}
}
