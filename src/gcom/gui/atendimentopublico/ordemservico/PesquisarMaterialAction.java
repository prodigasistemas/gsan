package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0384] Pesquisar Material
 * 
 * @author Rômulo Aurélio
 * @date 28/07/2006
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class PesquisarMaterialAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirResultadoPesquisaMaterial");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarMaterialActionForm pesquisarMaterialActionForm = (PesquisarMaterialActionForm) actionForm;

		FiltroMaterial filtroMaterial = new FiltroMaterial();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = pesquisarMaterialActionForm.getDescricao();

		String descricaoAbreviada = pesquisarMaterialActionForm
				.getDescricaoAbreviada();

		String unidadeMaterial = pesquisarMaterialActionForm
				.getUnidadeMaterial();

		// Verifica se o campo codigo foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroMaterial.adicionarParametro(new ComparacaoTexto(
					FiltroMaterialUnidade.DESCRICAO, descricao));

		}

		// Verifica se o campo descricao foi informado

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroMaterial.adicionarParametro(new ComparacaoTexto(
					FiltroMaterialUnidade.DESCRICAO_ABREVIADA,
					descricaoAbreviada));

		}

		// Verifica se o campo unidadeMaterial foi informado

		if (unidadeMaterial != null
				&& !unidadeMaterial.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroMaterial.adicionarParametro(new ParametroSimples(
					FiltroMaterial.MATERIAL_UNIDADE_ID, unidadeMaterial));

		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroMaterial
				.adicionarCaminhoParaCarregamentoEntidade("materialUnidade");

		Collection colecaoMaterial = (Collection) fachada.pesquisar(
				filtroMaterial, Material.class.getName());

		if (colecaoMaterial == null || colecaoMaterial.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Material");
		}

		sessao.setAttribute("colecaoMaterial", colecaoMaterial);

		return retorno;

	}

}
