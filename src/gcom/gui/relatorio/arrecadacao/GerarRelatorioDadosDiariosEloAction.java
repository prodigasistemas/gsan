package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosElo;
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
 * Gerar Relatório Dados Diários da Arrecadação - Elo
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class GerarRelatorioDadosDiariosEloAction extends
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
		
		BigDecimal valorTotalUnidadeNegocio = null;
		String referencia = null;
		String idUnidadeNegocio = null;
		String nomeUnidadeNegocio = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String idGerencia = null;
		String nomeGerencia = null;
		BigDecimal valorTotalGerencia = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;
		
		String arrecadador = "";
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (sessao.getAttribute("valorTotalUnidadeNegocio") != null) {
			valorTotalUnidadeNegocio = (BigDecimal) sessao.getAttribute("valorTotalUnidadeNegocio");
		}
		if (sessao.getAttribute("referencia") != null) {
			referencia = (String) sessao.getAttribute("referencia");
		}
		if (sessao.getAttribute("idUnidadeNegocio") != null) {
			idUnidadeNegocio = (String) sessao.getAttribute("idUnidadeNegocio");
		}
		if (sessao.getAttribute("nomeUnidadeNegocio") != null) {
			nomeUnidadeNegocio = (String) sessao.getAttribute("nomeUnidadeNegocio");
		}
		if (sessao.getAttribute("dadosMesInformado") != null) {
			dadosMesInformado = (String) sessao.getAttribute("dadosMesInformado");
		}
		if (sessao.getAttribute("dadosAtual") != null) {
			dadosAtual = (String) sessao.getAttribute("dadosAtual");
		}
		if (sessao.getAttribute("idGerencia") != null) {
			idGerencia = (String) sessao.getAttribute("idGerencia");
		}
		if (sessao.getAttribute("nomeGerencia") != null) {
			nomeGerencia = (String) sessao.getAttribute("nomeGerencia");
		}
		if (sessao.getAttribute("valorTotalGerencia") != null) {
			valorTotalGerencia = (BigDecimal) sessao.getAttribute("valorTotalGerencia");
		}
		if (sessao.getAttribute("faturamentoCobradoEmConta") != null) {
			faturamentoCobradoEmConta = (String) sessao.getAttribute("faturamentoCobradoEmConta");
		}
		if (sessao.getAttribute("colecaoDadosDiarios") != null) {
			colecaoDadosDiarios = (Collection<FiltrarDadosDiariosArrecadacaoHelper>) sessao.getAttribute("colecaoDadosDiarios");
		}
		if (sessao.getAttribute("arrecadador") != null) {
			arrecadador = (String) sessao.getAttribute("arrecadador");
		}
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		RelatorioDadosDiariosElo relatorioDadosDiariosElo = new RelatorioDadosDiariosElo(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosElo.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosElo.addParametro("valorTotalUnidadeNegocio", 
				valorTotalUnidadeNegocio);
		relatorioDadosDiariosElo.addParametro("referencia", 
				referencia);
		relatorioDadosDiariosElo.addParametro("idUnidadeNegocio", 
				idUnidadeNegocio);
		relatorioDadosDiariosElo.addParametro("nomeUnidadeNegocio", 
				nomeUnidadeNegocio);
		relatorioDadosDiariosElo.addParametro("dadosMesInformado", 
				dadosMesInformado);
		relatorioDadosDiariosElo.addParametro("dadosAtual", 
				dadosAtual);
		relatorioDadosDiariosElo.addParametro("idGerencia", 
				idGerencia);
		relatorioDadosDiariosElo.addParametro("nomeGerencia", 
				nomeGerencia);
		relatorioDadosDiariosElo.addParametro("valorTotalGerencia", 
				valorTotalGerencia);
		relatorioDadosDiariosElo.addParametro("colecaoDadosDiarios", 
				colecaoDadosDiarios);
		relatorioDadosDiariosElo.addParametro("faturamentoCobradoEmConta",
				faturamentoCobradoEmConta);
		relatorioDadosDiariosElo.addParametro("arrecadador",
				arrecadador);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosElo, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
