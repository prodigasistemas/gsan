package gcom.gui.seguranca.acesso;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirPesquisarUnidadeEmpresaAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("pesquisarUnidadeEmpresa");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Parte que passa as coleções necessárias no jsp
		//FiltroUnidadeNivel filtroUnidadeNivel = new FiltroUnidadeNivel();
		/*--<merge>--Collection colecaoUnidadesNiveis = fachada.pesquisar(
				filtroUnidadeNivel, UnidadeNivel.class.getName());*/
		Collection colecaoUnidadesNiveis = null;

		if (colecaoUnidadesNiveis != null && !colecaoUnidadesNiveis.isEmpty()) {
			sessao.setAttribute("colecaoUnidadesNiveis", colecaoUnidadesNiveis);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Unidade Nível");
		}
		
		UnidadeEmpresaActionForm unidadeEmpresaActionForm = (UnidadeEmpresaActionForm) actionForm;
		
		String idUnidadeSuperior = unidadeEmpresaActionForm.getIdUnidadeSuperior();
		
		if (idUnidadeSuperior != null && !idUnidadeSuperior.equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idUnidadeSuperior));
			Collection colecaoUnidadeSuperior = fachada.pesquisar(
					filtroUnidadeEmpresa, UnidadeOrganizacional.class.getName());
			if (colecaoUnidadeSuperior != null && !colecaoUnidadeSuperior.isEmpty()){
				UnidadeOrganizacional unidadeEmpresa = (UnidadeOrganizacional) colecaoUnidadeSuperior.iterator().next();				
				unidadeEmpresaActionForm.setDescricaoUnidadeSuperior(unidadeEmpresa.getDescricao());
			} else {
				httpServletRequest.setAttribute("unidadeSuperiorInexistente", true);
				unidadeEmpresaActionForm.setIdUnidadeSuperior("");
				unidadeEmpresaActionForm.setDescricaoUnidadeSuperior("UNIDADE INEXISTENTE");
			}
		}
		
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaUnidadeEmpresa") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaUnidadeEmpresa",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaUnidadeEmpresa"));

		}

		if (httpServletRequest.getParameter("limpaForm") != null){
			unidadeEmpresaActionForm.setCodigoUnidade("");
			unidadeEmpresaActionForm.setIdUnidadeSuperior("");
			unidadeEmpresaActionForm.setNivelHiearquia(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString());
			unidadeEmpresaActionForm.setNomeUnidade("");
			unidadeEmpresaActionForm.setSiglaUnidade("");

		}
		
		if (httpServletRequest.getAttribute("popup") != null){
			sessao.setAttribute("popup", httpServletRequest.getAttribute("popup"));
		}

		httpServletRequest.setAttribute("nomeCampo", "codigoUnidade");
		
		return retorno;
	}
}
