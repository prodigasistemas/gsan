package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosArrecadacaoForma;
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
 * Gerar Relatório Dados Diários da Arrecadação - Forma de Arrecadação
 * 
 * @author Mariana Victor
 * @date 04/02/2011
 */
public class GerarRelatorioDadosDiariosArrecadacaoFormaAction extends
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
		
		BigDecimal valorTotalArrecadador = null;
		String referencia = null;
		String dadosMesInformado = null;
		String dadosAtual = null;
		String nomeAgente = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (sessao.getAttribute("valorTotalArrecadador") != null) {
			valorTotalArrecadador = (BigDecimal) sessao.getAttribute("valorTotalArrecadador");
		}
		if (sessao.getAttribute("referencia") != null) {
			referencia = (String) sessao.getAttribute("referencia");
		}
		if (sessao.getAttribute("dadosMesInformado") != null) {
			dadosMesInformado = (String) sessao.getAttribute("dadosMesInformado");
		}
		if (sessao.getAttribute("dadosAtual") != null) {
			dadosAtual = (String) sessao.getAttribute("dadosAtual");
		}
		if (sessao.getAttribute("nomeAgente") != null) {
			nomeAgente = (String) sessao.getAttribute("nomeAgente");
		}
		if (sessao.getAttribute("faturamentoCobradoEmConta") != null) {
			faturamentoCobradoEmConta = (String) sessao.getAttribute("faturamentoCobradoEmConta");
		}
		if (sessao.getAttribute("colecaoDadosDiarios") != null) {
			colecaoDadosDiarios = (Collection<FiltrarDadosDiariosArrecadacaoHelper>) sessao.getAttribute("colecaoDadosDiarios");
		}
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		RelatorioDadosDiariosArrecadacaoForma relatorioDadosDiariosArrecadacaoForma = new RelatorioDadosDiariosArrecadacaoForma(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosArrecadacaoForma.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosArrecadacaoForma.addParametro("valorTotalArrecadador", 
				valorTotalArrecadador);
		relatorioDadosDiariosArrecadacaoForma.addParametro("referencia", 
				referencia);
		relatorioDadosDiariosArrecadacaoForma.addParametro("dadosMesInformado", 
				dadosMesInformado);
		relatorioDadosDiariosArrecadacaoForma.addParametro("dadosAtual", 
				dadosAtual);
		relatorioDadosDiariosArrecadacaoForma.addParametro("nomeAgente", 
				nomeAgente);
		relatorioDadosDiariosArrecadacaoForma.addParametro("faturamentoCobradoEmConta",
				faturamentoCobradoEmConta);
		relatorioDadosDiariosArrecadacaoForma.addParametro("colecaoDadosDiarios",
				colecaoDadosDiarios);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosArrecadacaoForma, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
