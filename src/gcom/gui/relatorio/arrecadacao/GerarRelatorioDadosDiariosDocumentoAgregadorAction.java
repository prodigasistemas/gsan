package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosDocumentoAgregador;
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
 * Gerar Relatório Dados Diários da Arrecadação - Documento Agregador
 * 
 * @author Mariana Victor
 * @date 04/02/2011
 */
public class GerarRelatorioDadosDiariosDocumentoAgregadorAction extends
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
		String dadosMesInformado = null;
		String dadosAtual = null;
		String descricaoDocumentoTipoAgregador = null;
		String faturamentoCobradoEmConta = null;
		Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = null;
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		String arrecadador = "";

		if (sessao.getAttribute("valorTotal") != null) {
			valorTotal = (BigDecimal) sessao.getAttribute("valorTotal");
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
		if (sessao.getAttribute("descricaoDocumentoTipoAgregador") != null) {
			descricaoDocumentoTipoAgregador = (String) sessao.getAttribute("descricaoDocumentoTipoAgregador");
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
		
		RelatorioDadosDiariosDocumentoAgregador relatorioDadosDiariosDocumentoAgregador = new RelatorioDadosDiariosDocumentoAgregador(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosDocumentoAgregador.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosDocumentoAgregador.addParametro("valorTotal", 
				valorTotal);
		relatorioDadosDiariosDocumentoAgregador.addParametro("referencia", 
				referencia);
		relatorioDadosDiariosDocumentoAgregador.addParametro("dadosMesInformado", 
				dadosMesInformado);
		relatorioDadosDiariosDocumentoAgregador.addParametro("dadosAtual", 
				dadosAtual);
		relatorioDadosDiariosDocumentoAgregador.addParametro("descricaoDocumentoTipoAgregador", 
				descricaoDocumentoTipoAgregador);
		relatorioDadosDiariosDocumentoAgregador.addParametro("faturamentoCobradoEmConta",
				faturamentoCobradoEmConta);
		relatorioDadosDiariosDocumentoAgregador.addParametro("colecaoDadosDiarios",
				colecaoDadosDiarios);
		relatorioDadosDiariosDocumentoAgregador.addParametro("arrecadador",
				arrecadador);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosDocumentoAgregador, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
