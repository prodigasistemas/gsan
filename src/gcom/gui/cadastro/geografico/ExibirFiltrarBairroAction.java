package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 30 de Junho de 2004
 */
public class ExibirFiltrarBairroAction extends GcomAction {
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
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarBairro");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (sessao.getAttribute("consulta") != null) {
			sessao.removeAttribute("consulta");
		}

		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) pesquisarActionForm
				.get("idMunicipio");

		// Verifica se o código foi digitado
		if (idDigitadoEnterMunicipio != null
				&& !idDigitadoEnterMunicipio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idDigitadoEnterMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				pesquisarActionForm.set("idMunicipio",
						(((Municipio) ((List) municipioEncontrado).get(0))
								.getId().toString()));
				pesquisarActionForm.set("nomeMunicipio",
						(((Municipio) ((List) municipioEncontrado).get(0))
								.getNome()));
				httpServletRequest.setAttribute("nomeCampo", "codigoBairro");

			} else {
				pesquisarActionForm.set("idMunicipio", "");
				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
						"true");
				pesquisarActionForm.set("nomeMunicipio",
						"Município inexistente");

				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");

			}

		}

		// -------Fim da Parte que trata do código quando o usuário tecla enter

		if (pesquisarActionForm.get("indicadorUso") == null) {

			pesquisarActionForm.set("indicadorUso", "");

		}

		if (pesquisarActionForm.get("tipoPesquisa") == null
				|| pesquisarActionForm.get("tipoPesquisa").equals("")) {

			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}

		httpServletRequest.removeAttribute("i");

		String atualizar = httpServletRequest.getParameter("atualizar");
		String menu = httpServletRequest.getParameter("menu");

		if (atualizar == null && menu == null) {
			boolean i = true;
			httpServletRequest.setAttribute("i", i);
		}

		if (httpServletRequest.getAttribute("nomeCampo") == null) {
			httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
		}

		return retorno;
	}
}
