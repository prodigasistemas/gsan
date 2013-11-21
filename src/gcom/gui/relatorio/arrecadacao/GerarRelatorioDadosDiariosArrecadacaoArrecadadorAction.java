package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosArrecadacaoArrecadador;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação por Arrecadador
 * 
 * @author Mariana Victor
 * @date 02/02/2011
 */
public class GerarRelatorioDadosDiariosArrecadacaoArrecadadorAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> mapDadosDiariosAnoMes = new TreeMap(); 
		Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento = new TreeMap();
		Collection<BigDecimal> colecaoValorTotal = null;
		BigDecimal valorTotalPeriodo = null;
		String dataAtual = null;
		
		String arrecadador = "";

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		if (sessao.getAttribute("mapDadosDiariosAnoMes") != null) {
			mapDadosDiariosAnoMes = (Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>>)sessao.getAttribute("mapDadosDiariosAnoMes");
		}
		if (sessao.getAttribute("mapDadosProcessamento") != null) {
			mapDadosProcessamento = (Map<Integer, FiltrarDadosDiariosArrecadacaoHelper>)sessao.getAttribute("mapDadosProcessamento");
		}
		if (sessao.getAttribute("colecaoValorTotal") != null) {
			colecaoValorTotal = (Collection<BigDecimal>)sessao.getAttribute("colecaoValorTotal");
		}
		if (sessao.getAttribute("valorTotalPeriodo") != null) {
			valorTotalPeriodo = (BigDecimal)sessao.getAttribute("valorTotalPeriodo");
		}
		if (sessao.getAttribute("dataAtual") != null) {
			dataAtual = (String)sessao.getAttribute("dataAtual");
		}
		if(sessao.getAttribute("arrecadador") != null) {
			arrecadador = (String)sessao.getAttribute("arrecadador");
		}
		
		RelatorioDadosDiariosArrecadacaoArrecadador relatorioDadosDiariosArrecadacaoArrecadador = new RelatorioDadosDiariosArrecadacaoArrecadador(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosArrecadacaoArrecadador.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosArrecadacaoArrecadador.addParametro("mapDadosDiariosAnoMes", 
				mapDadosDiariosAnoMes);
		relatorioDadosDiariosArrecadacaoArrecadador.addParametro("mapDadosProcessamento", 
				mapDadosProcessamento);
		relatorioDadosDiariosArrecadacaoArrecadador.addParametro("colecaoValorTotal", 
				colecaoValorTotal);
		relatorioDadosDiariosArrecadacaoArrecadador.addParametro("valorTotalPeriodo", 
				valorTotalPeriodo);
		relatorioDadosDiariosArrecadacaoArrecadador.addParametro("dataAtual", 
				dataAtual);
		relatorioDadosDiariosArrecadacaoArrecadador.addParametro("arrecadador",
				arrecadador);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosArrecadacaoArrecadador, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
