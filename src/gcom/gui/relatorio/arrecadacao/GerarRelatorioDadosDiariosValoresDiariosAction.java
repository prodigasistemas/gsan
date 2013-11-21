package gcom.gui.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Collection;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosValoresDiarios;
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
public class GerarRelatorioDadosDiariosValoresDiariosAction extends
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
		String nomeGerencia = null;
		String descricaoLocalidade = null;
		String descricaoElo = null;
		String nomeAgente = null;
		String nomeCategoria = null;
		String nomePerfil = null;
		String nomeDocumento = null;
		String nomeArrecadacaoForma = null;
		String nomeUnidadeNegocio = null;
		String mostraUnidadeGerencia = null;
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
		if (sessao.getAttribute("nomeGerencia") != null) {
			nomeGerencia = (String) sessao.getAttribute("nomeGerencia");
		}
		if (sessao.getAttribute("descricaoLocalidade") != null) {
			descricaoLocalidade = (String) sessao.getAttribute("descricaoLocalidade");
		}
		if (sessao.getAttribute("descricaoElo") != null) {
			descricaoElo = (String) sessao.getAttribute("descricaoElo");
		}
		if (sessao.getAttribute("nomeAgente") != null) {
			nomeAgente = (String) sessao.getAttribute("nomeAgente");
		}
		if (sessao.getAttribute("nomeCategoria") != null) {
			nomeCategoria = (String) sessao.getAttribute("nomeCategoria");
		}
		if (sessao.getAttribute("nomePerfil") != null) {
			nomePerfil = (String) sessao.getAttribute("nomePerfil");
		}
		if (sessao.getAttribute("nomeDocumento") != null) {
			nomeDocumento = (String) sessao.getAttribute("nomeDocumento");
		}
		if (sessao.getAttribute("nomeArrecadacaoForma") != null) {
			nomeArrecadacaoForma = (String) sessao.getAttribute("nomeArrecadacaoForma");
		}
		if (sessao.getAttribute("nomeUnidadeNegocio") != null) {
			nomeUnidadeNegocio = (String) sessao.getAttribute("nomeUnidadeNegocio");
		}
		if (sessao.getAttribute("mostraUnidadeGerencia") != null) {
			mostraUnidadeGerencia = (String) sessao.getAttribute("mostraUnidadeGerencia");
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
		
		RelatorioDadosDiariosValoresDiarios relatorioDadosDiariosValoresDiarios = new RelatorioDadosDiariosValoresDiarios(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosValoresDiarios.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosValoresDiarios.addParametro("valorTotal", 
				valorTotal);
		relatorioDadosDiariosValoresDiarios.addParametro("referencia", 
				referencia);
		relatorioDadosDiariosValoresDiarios.addParametro("dadosMesInformado", 
				dadosMesInformado);
		relatorioDadosDiariosValoresDiarios.addParametro("dadosAtual", 
				dadosAtual);
		relatorioDadosDiariosValoresDiarios.addParametro("nomeGerencia", 
				nomeGerencia);
		relatorioDadosDiariosValoresDiarios.addParametro("descricaoLocalidade", 
				descricaoLocalidade);
		relatorioDadosDiariosValoresDiarios.addParametro("descricaoElo", 
				descricaoElo);
		relatorioDadosDiariosValoresDiarios.addParametro("nomeAgente",
				nomeAgente);
		relatorioDadosDiariosValoresDiarios.addParametro("nomeCategoria",
				nomeCategoria);
		relatorioDadosDiariosValoresDiarios.addParametro("nomePerfil",
				nomePerfil);
		relatorioDadosDiariosValoresDiarios.addParametro("nomeDocumento",
				nomeDocumento);
		relatorioDadosDiariosValoresDiarios.addParametro("nomeArrecadacaoForma",
				nomeArrecadacaoForma);
		relatorioDadosDiariosValoresDiarios.addParametro("nomeUnidadeNegocio",
				nomeUnidadeNegocio);
		relatorioDadosDiariosValoresDiarios.addParametro("mostraUnidadeGerencia",
				mostraUnidadeGerencia);
		relatorioDadosDiariosValoresDiarios.addParametro("faturamentoCobradoEmConta",
				faturamentoCobradoEmConta);
		relatorioDadosDiariosValoresDiarios.addParametro("colecaoDadosDiarios",
				colecaoDadosDiarios);
		relatorioDadosDiariosValoresDiarios.addParametro("arrecadador",
				arrecadador);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosValoresDiarios, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
