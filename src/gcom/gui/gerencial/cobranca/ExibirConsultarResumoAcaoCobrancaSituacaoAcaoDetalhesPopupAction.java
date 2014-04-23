package gcom.gui.gerencial.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoSituacao;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Francisco do Nascimento
 * @date 13/06/2008
 * 
 */
public class ExibirConsultarResumoAcaoCobrancaSituacaoAcaoDetalhesPopupAction extends
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
				.findForward("consultarResumoAcaoCobrancaSituacaoAcaoDetalhesPopup");

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = getSessao(httpServletRequest);

		InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper = (InformarDadosGeracaoResumoAcaoConsultaHelper) sessao
				.getAttribute("informarDadosGeracaoResumoAcaoConsultaHelper");
		
		InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper = 
			(InformarDadosGeracaoResumoAcaoConsultaEventualHelper) sessao.getAttribute("informarDadosGeracaoResumoAcaoConsultaEventualHelper");

		// Integer anoMesReferencia =
		// Util.formatarMesAnoComBarraParaAnoMes(sessao.getAttribute("mesAnoReferencia").toString());
		Integer idCobrancaAcao = new Integer(httpServletRequest.getParameter(
				"idCobrancaAcao").trim());
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.ID, idCobrancaAcao));
		Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,
				CobrancaAcao.class.getName());

		if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
			Iterator iteratorCobrancaAcao = colecaoCobrancaAcao.iterator();
			CobrancaAcao cobrancaAcao = (CobrancaAcao) iteratorCobrancaAcao
					.next();
			httpServletRequest.setAttribute("cobrancaAcao", cobrancaAcao
					.getDescricaoCobrancaAcao());
		}
		Integer idCobrancaAcaoSituacao = new Integer(httpServletRequest
				.getParameter("idCobrancaAcaoSituacao").trim());
		httpServletRequest.setAttribute("cobrancaAcaoSituacao",
				httpServletRequest.getParameter("cobrancaAcaoSituacao"));
		httpServletRequest.setAttribute("quantidadeTotal", httpServletRequest
				.getParameter("quantidadeTotal"));
		httpServletRequest.setAttribute("valorTotal", httpServletRequest
				.getParameter("valorTotal").trim());
		String valorTotalFormatado = Util.formatarMoedaReal(new BigDecimal(
				httpServletRequest.getParameter("valorTotal").trim()));
		httpServletRequest.setAttribute("valorTotalFormatado",
				valorTotalFormatado);
		
		boolean exibeApenasMotivoEncerramentoDeExecucao = false;
		
		if (idCobrancaAcaoSituacao != null && idCobrancaAcaoSituacao.intValue() == 
			CobrancaAcaoSituacao.EXECUTADA.intValue()){
			exibeApenasMotivoEncerramentoDeExecucao = true;
		}
		
		String tipoDetalhe = httpServletRequest.getParameter("tipoDetalhe");
		Collection colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes = new ArrayList();
		if (tipoDetalhe.equals("E")){
			colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes = fachada
				.consultarResumoCobrancaAcaoMotivoEncerramento(
					idCobrancaAcao, idCobrancaAcaoSituacao, informarDadosGeracaoResumoAcaoConsultaHelper,
					exibeApenasMotivoEncerramentoDeExecucao);
		} else if (tipoDetalhe.equals("F")){
			colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes = fachada
			.consultarResumoCobrancaAcaoRetornoFiscalizacao(
				idCobrancaAcao, idCobrancaAcaoSituacao, informarDadosGeracaoResumoAcaoConsultaHelper);
		} else if (tipoDetalhe.equals("eE")){
			colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes = fachada
			.consultarResumoCobrancaAcaoMotivoEncerramentoEventual(
				idCobrancaAcao, idCobrancaAcaoSituacao, informarDadosGeracaoResumoAcaoConsultaEventualHelper,
				exibeApenasMotivoEncerramentoDeExecucao);
		} else if (tipoDetalhe.equals("eF")){
			colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes = fachada
			.consultarResumoCobrancaAcaoRetornoFiscalizacaoEventual(
				idCobrancaAcao, idCobrancaAcaoSituacao, informarDadosGeracaoResumoAcaoConsultaEventualHelper);
		} else if (tipoDetalhe.equals("C")){
			colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes = fachada
			.consultarResumoCobrancaAcaoTipoCorte(
				idCobrancaAcao, idCobrancaAcaoSituacao, informarDadosGeracaoResumoAcaoConsultaHelper,
				exibeApenasMotivoEncerramentoDeExecucao);
		}


		sessao.setAttribute("tipoDetalhe", tipoDetalhe);			

		sessao.setAttribute("colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes",
				colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes);			
		
		return retorno;
	}
}
