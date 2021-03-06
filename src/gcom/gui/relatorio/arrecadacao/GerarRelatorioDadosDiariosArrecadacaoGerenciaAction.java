package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosArrecadacaoGerencia;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0339] Consultar Dados Di�rios da Arrecada��o
 * 
 * Gerar Relat�rio Dados Di�rios da Arrecada��o por Ger�ncia
 * 
 * @author Mariana Victor
 * @date 01/02/2011
 */
public class GerarRelatorioDadosDiariosArrecadacaoGerenciaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> mapDadosDiariosAnoMes = new TreeMap(); 
		Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento = new TreeMap();
		Collection<BigDecimal> colecaoValorTotal = null;
		BigDecimal valorTotalPeriodo = null;
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		String arrecadador = "";

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
		if(sessao.getAttribute("arrecadador") != null) {
			arrecadador = (String)sessao.getAttribute("arrecadador");
		}
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		RelatorioDadosDiariosArrecadacaoGerencia relatorioDadosDiariosArrecadacaoGerencia = new RelatorioDadosDiariosArrecadacaoGerencia(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosArrecadacaoGerencia.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosArrecadacaoGerencia.addParametro("mapDadosDiariosAnoMes", 
				mapDadosDiariosAnoMes);
		relatorioDadosDiariosArrecadacaoGerencia.addParametro("mapDadosProcessamento", 
				mapDadosProcessamento);
		relatorioDadosDiariosArrecadacaoGerencia.addParametro("colecaoValorTotal", 
				colecaoValorTotal);
		relatorioDadosDiariosArrecadacaoGerencia.addParametro("valorTotalPeriodo", 
				valorTotalPeriodo);
		relatorioDadosDiariosArrecadacaoGerencia.addParametro("arrecadador",
				arrecadador);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosArrecadacaoGerencia, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
