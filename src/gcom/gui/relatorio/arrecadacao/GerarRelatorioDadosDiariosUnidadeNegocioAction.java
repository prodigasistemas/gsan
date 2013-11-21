package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosUnidadeNegocio;
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
 * Gerar Relatório Dados Diários da Arrecadação - Unidade de Negócio
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class GerarRelatorioDadosDiariosUnidadeNegocioAction extends
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
		
		BigDecimal valorTotal = null;
		String referencia = null;
		String idGerencia = null;
		String nomeGerencia = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		String arrecadador = "";

		if (sessao.getAttribute("valorTotalGerencia") != null) {
			valorTotal = (BigDecimal) sessao.getAttribute("valorTotalGerencia");
		}
		if (sessao.getAttribute("referencia") != null) {
			referencia = (String) sessao.getAttribute("referencia");
		}
		if (sessao.getAttribute("idGerencia") != null) {
			idGerencia = (String) sessao.getAttribute("idGerencia");
		}
		if (sessao.getAttribute("nomeGerencia") != null) {
			nomeGerencia = (String) sessao.getAttribute("nomeGerencia");
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
		
		RelatorioDadosDiariosUnidadeNegocio relatorioDadosDiariosUnidadeNegocio = new RelatorioDadosDiariosUnidadeNegocio(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosUnidadeNegocio.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosUnidadeNegocio.addParametro("valorTotal", 
				valorTotal);
		relatorioDadosDiariosUnidadeNegocio.addParametro("referencia", 
				referencia);
		relatorioDadosDiariosUnidadeNegocio.addParametro("idGerencia", 
				idGerencia);
		relatorioDadosDiariosUnidadeNegocio.addParametro("nomeGerencia", 
				nomeGerencia);
		relatorioDadosDiariosUnidadeNegocio.addParametro("dadosMesInformado", 
				dadosMesInformado);
		relatorioDadosDiariosUnidadeNegocio.addParametro("dadosAtual", 
				dadosAtual);
		relatorioDadosDiariosUnidadeNegocio.addParametro("faturamentoCobradoEmConta",
				faturamentoCobradoEmConta);
		relatorioDadosDiariosUnidadeNegocio.addParametro("colecaoDadosDiarios",
				colecaoDadosDiarios);
		relatorioDadosDiariosUnidadeNegocio.addParametro("arrecadador",
				arrecadador);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosUnidadeNegocio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
