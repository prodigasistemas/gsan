package gcom.gui.faturamento.consumotarifa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Mater Tarifa de Consumo
 * 
 * @author Tiago Moreno
 */
public class InserirReajusteConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucessoPopup");


		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String[] idsRecuperados = (String[]) sessao.getAttribute("ids");
				
		// Variavel para testar se o campo naum obrigatorio esta vazio
		Enumeration parametros = httpServletRequest.getParameterNames();

		Date dataNovaVigencia = Util.converteStringParaDate(httpServletRequest
				.getParameter("dataVigencia"));
		
		Map listaParametrosValoresCategoria = new HashMap();

		while (parametros.hasMoreElements()) {
			String parametro = (String) parametros.nextElement();
			int codigoCategoria = 0;

			try {
				codigoCategoria = Integer.parseInt(parametro);
			} catch (NumberFormatException e) {
				continue;
			}

			String valorPercentual = httpServletRequest.getParameter(parametro);

			listaParametrosValoresCategoria.put(codigoCategoria,
					new BigDecimal(valorPercentual.replace(',','.')));

		}		

		//inicializa o processo de reajustar e logo apos chama o metodo para reajustar as tarifas
		fachada.iniciarProcessoReajustarTarifaConsumo(listaParametrosValoresCategoria, dataNovaVigencia,
				idsRecuperados);
		
		montarPaginaSucesso(httpServletRequest,
				"Tarifa(s) de Consumo Reajustada(s) com sucesso.", "", "");
		
		return retorno;
	}
}
