package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

public class PesquisarGerenciaRegionalAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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

		// Inicializacoes de variaveis
		ActionForward retorno = actionMapping.findForward("retornoPesquisa");
		HttpSession sessao = httpServletRequest.getSession(false);
		// Fachada fachada = Fachada.getInstancia();
		boolean peloMenosUmParametroInformado = false;
		PesquisarGerenciaRegionalActionForm form = (PesquisarGerenciaRegionalActionForm) actionForm;

		String id = form.getId();
		String nome = form.getNome();

		String nomeAbreviado = form.getNomeAbreviado();

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional
				.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroGerenciaRegional
				.adicionarCaminhoParaCarregamentoEntidade("bairro");

		// Pesquisa id
		if (id != null && !id.equals("")) {
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, id));
			peloMenosUmParametroInformado = true;
		}

		// Pesquisa nome
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (form.getTipoPesquisa() != null
					&& form.getTipoPesquisa()
							.equals(
									ConstantesSistema.TIPO_PESQUISA_COMPLETA
											.toString())) {

				filtroGerenciaRegional
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroGerenciaRegional.NOME, nome));
			} else {

				filtroGerenciaRegional.adicionarParametro(new ComparacaoTexto(
						FiltroGerenciaRegional.NOME, nome));
			}
		}
		// Pesquisa nome Abreviado
		if (nomeAbreviado != null && !nomeAbreviado.equals("")) {
			filtroGerenciaRegional.adicionarParametro(new ComparacaoTexto(
					FiltroGerenciaRegional.NOME_ABREVIADO, nomeAbreviado));
			peloMenosUmParametroInformado = true;
		}

		// [FS0002] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME,
				FiltroGerenciaRegional.ID);

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		Collection collectionGerenciaRegional = (Collection) resultado
				.get("colecaoRetorno");

		retorno = (ActionForward) resultado.get("destinoActionForward");

		// Validacoes
		if (collectionGerenciaRegional == null
				|| collectionGerenciaRegional.isEmpty()) {
			// [FS0004] Nenhum registro encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"gerenciaRegional");
		} else if (collectionGerenciaRegional.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// [FS0003] Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			sessao.setAttribute("collectionGerenciaRegional",
					collectionGerenciaRegional);
		}

		return retorno;

	}

}
