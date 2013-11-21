package gcom.gui.micromedicao;

import gcom.gui.GcomAction;
import gcom.micromedicao.TelemetriaMovReg;
import gcom.micromedicao.bean.FiltrarLeiturasTelemetriaHelper;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1069] Consultar Leituras Telemetria
 * 
 * @author Hugo Amorim
 * @date 28/09/2010
 *
 */
public class ExibirManterLeiturasTelemetriaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterLeiturasTemeletriaAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarLeiturasTelemetriaHelper helper = (FiltrarLeiturasTelemetriaHelper) sessao.getAttribute("filtro");
		
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		Integer qtdTotalRegistros = (Integer) sessao.getAttribute("qtdTotalRegistros");
		
		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno, qtdTotalRegistros);
		helper.setNumeroPagina((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		
		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
		Collection<TelemetriaMovReg> colecaoTelemetriaMovReg =
			this.getFachada().filtrarLeiturasTelemetria(helper);
		
		sessao.setAttribute("colecao",colecaoTelemetriaMovReg);

		return retorno;
	}
}
