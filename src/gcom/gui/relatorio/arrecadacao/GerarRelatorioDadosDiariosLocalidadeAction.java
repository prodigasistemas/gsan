package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosLocalidade;
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
 * Gerar Relatório Dados Diários da Arrecadação - Localidade
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class GerarRelatorioDadosDiariosLocalidadeAction extends
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
		BigDecimal valorTotalElo = null;
		String referencia = null;
		String nomeGerencia = null;
		String nomeElo = null;
		BigDecimal valorTotalGerencia = null;
		String idUnidadeNegocio = null;
		String nomeUnidadeNegocio = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		String arrecadador = "";

		if (sessao.getAttribute("valorTotalUnidadeNegocio") != null) {
			valorTotalUnidadeNegocio = (BigDecimal) sessao.getAttribute("valorTotalUnidadeNegocio");
		}
		if (sessao.getAttribute("valorTotalElo") != null) {
			valorTotalElo = (BigDecimal) sessao.getAttribute("valorTotalElo");
		}
		if (sessao.getAttribute("referencia") != null) {
			referencia = (String) sessao.getAttribute("referencia");
		}
		if (sessao.getAttribute("nomeGerencia") != null) {
			nomeGerencia = (String) sessao.getAttribute("nomeGerencia");
		}
		if (sessao.getAttribute("nomeElo") != null) {
			nomeElo = (String) sessao.getAttribute("nomeElo");
		}
		if (sessao.getAttribute("valorTotalGerencia") != null) {
			valorTotalGerencia = (BigDecimal) sessao.getAttribute("valorTotalGerencia");
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
		
		RelatorioDadosDiariosLocalidade relatorioDadosDiariosLocalidade = new RelatorioDadosDiariosLocalidade(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosLocalidade.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosLocalidade.addParametro("valorTotalUnidadeNegocio", 
				valorTotalUnidadeNegocio);
		relatorioDadosDiariosLocalidade.addParametro("valorTotalElo", 
				valorTotalElo);
		relatorioDadosDiariosLocalidade.addParametro("referencia", 
				referencia);
		relatorioDadosDiariosLocalidade.addParametro("nomeGerencia", 
				nomeGerencia);
		relatorioDadosDiariosLocalidade.addParametro("nomeElo", 
				nomeElo);
		relatorioDadosDiariosLocalidade.addParametro("valorTotalGerencia", 
				valorTotalGerencia);
		relatorioDadosDiariosLocalidade.addParametro("idUnidadeNegocio", 
				idUnidadeNegocio);
		relatorioDadosDiariosLocalidade.addParametro("nomeUnidadeNegocio", 
				nomeUnidadeNegocio);
		relatorioDadosDiariosLocalidade.addParametro("dadosMesInformado", 
				dadosMesInformado);
		relatorioDadosDiariosLocalidade.addParametro("dadosAtual", 
				dadosAtual);
		relatorioDadosDiariosLocalidade.addParametro("colecaoDadosDiarios", 
				colecaoDadosDiarios);
		relatorioDadosDiariosLocalidade.addParametro("faturamentoCobradoEmConta",
				faturamentoCobradoEmConta);
		relatorioDadosDiariosLocalidade.addParametro("arrecadador",
				arrecadador);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosLocalidade, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
