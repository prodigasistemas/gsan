package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o processamento da página de pesquisa de Setor Comercial
 * 
 * @author Flávio
 */
public class PesquisarFonteCaptacaoAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("listaFonteCaptacao");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		String codigo = (String) pesquisarActionForm.get("codigoFonteCaptacao");
		String descricao = (String) pesquisarActionForm
				.get("descricaoFonteCaptacao");
		String tipoPesquisaDescricao = (String) pesquisarActionForm
				.get("tipoPesquisaDescricao");
		String descricaoAbreviada = (String) pesquisarActionForm
				.get("descricaoAbreviadaFonteCaptacao");

		boolean peloMenosUmParametroInformado = false;

		String idTipoCaptacao = null;
		String tipoPesquisa = null;

		FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();

		// Objetos que serão retornados pelo Hibernate
		filtroFonteCaptacao
				.adicionarCaminhoParaCarregamentoEntidade("tipoCaptacao");

		filtroFonteCaptacao.setCampoOrderBy(FiltroFonteCaptacao.ID);

		Fachada fachada = Fachada.getInstancia();

		if (sessao.getAttribute("idTipoCaptacao") != null) {
			idTipoCaptacao = sessao.getAttribute("idTipoCaptacao").toString();
		}

		if (sessao.getAttribute("tipoPesquisa") != null) {
			tipoPesquisa = sessao.getAttribute("tipoPesquisa").toString();
		}
		if (sessao.getAttribute("indicadorUsoTodos") == null) {
			filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
					FiltroFonteCaptacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
		}

		if (idTipoCaptacao != null && !idTipoCaptacao.equals("")) {

			filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
					FiltroFonteCaptacao.ID_TIPO_CAPTACAO, idTipoCaptacao));
			peloMenosUmParametroInformado = true;

		}

		if (codigo != null && !codigo.equals("")) {
			filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
					FiltroFonteCaptacao.ID, new Integer(codigo)));
			peloMenosUmParametroInformado = true;
		}
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			if (tipoPesquisaDescricao != null
					&& tipoPesquisaDescricao
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroFonteCaptacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFonteCaptacao.DESCRICAO, descricao));
			} else {
				filtroFonteCaptacao.adicionarParametro(new ComparacaoTexto(
						FiltroFonteCaptacao.DESCRICAO, descricao));
			}
			peloMenosUmParametroInformado = true;
		}

		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equals("")) {

			filtroFonteCaptacao
					.adicionarParametro(new ParametroSimples(
							FiltroFonteCaptacao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));

		}

		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection fonteCaptacao = fachada.pesquisar(filtroFonteCaptacao,
				FonteCaptacao.class.getName());

		if (fonteCaptacao != null && !fonteCaptacao.isEmpty()) {
			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroFonteCaptacao, FonteCaptacao.class.getName());
			fonteCaptacao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			sessao.setAttribute("fonteCaptacao", fonteCaptacao);

		} else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "fonte captacao");
		}

		// Passa o parametro para o tipo de resultado e o tira da sessão
		httpServletRequest.setAttribute("tipoPesquisa", tipoPesquisa);

		return retorno;
	}

}
