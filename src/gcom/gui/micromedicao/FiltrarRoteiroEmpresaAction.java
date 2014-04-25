package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRoteiroEmpresa;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 22/07/2007
 */
public class FiltrarRoteiroEmpresaAction extends GcomAction {

	/**
	 * Este caso de uso permite Filtrar um RoteiroEmpresa
	 * 
	 * [UC0588] Filtrar RoteiroEmpresa
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 22/07/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterRoteiroEmpresa");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRoteiroEmpresaActionForm filtrarRoteiroEmpresaActionForm = (FiltrarRoteiroEmpresaActionForm) actionForm;

		FiltroRoteiroEmpresa filtroRoteiroEmpresa = new FiltroRoteiroEmpresa();

//		// Ordena a pesquisa por um parâmetro pré-definido
//		filtroRoteiroEmpresa.setCampoOrderBy("equipe.id");
		
		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String empresa = filtrarRoteiroEmpresaActionForm.getEmpresa();
		String idLocalidade = filtrarRoteiroEmpresaActionForm.getIdLocalidade();
		String idLeiturista = filtrarRoteiroEmpresaActionForm.getIdLeiturista();
		String idSetorComercial = filtrarRoteiroEmpresaActionForm
				.getIdSetorComercial();
		String indicadorUso = filtrarRoteiroEmpresaActionForm.getIndicadorUso();

		// Verifica se o campo Empresa foi informado

		if (empresa != null
				&& !empresa.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroRoteiroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroRoteiroEmpresa.EMPRESA, empresa));

		}

		// Verifica se o campo Códigodo localidade foi informado
		if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroRoteiroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroRoteiroEmpresa.LOCALIDADE_ID, idLocalidade));
		}

		// Verifica se o campo Códigodo leiturista foi informado
		if (idLeiturista != null && !idLeiturista.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroRoteiroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroRoteiroEmpresa.LEITURISTA_ID, idLeiturista));
		}

		// Verifica se o setor comercial foi informado

		if (idSetorComercial != null
				&& !idSetorComercial.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroRoteiroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroRoteiroEmpresa.SETOR_COMERCIAL_ID, idSetorComercial));

		}

		// Verifica se o campo Indicador de Uso foi informado
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroRoteiroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroRoteiroEmpresa.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroRoteiroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroRoteiroEmpresa.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Manda os parâmetros da pesquisa pela sessao para o
		// ExibirManterRoteiroEmpresaAction
		sessao.setAttribute("empresa", empresa);
		sessao.setAttribute("idLocalidade", idLocalidade);
		sessao.setAttribute("idLeiturista", idLeiturista);
		sessao.setAttribute("idSetorComercial", idSetorComercial);

		return retorno;
	}
}
