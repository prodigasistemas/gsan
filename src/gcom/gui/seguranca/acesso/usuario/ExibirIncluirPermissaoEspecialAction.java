package gcom.gui.seguranca.acesso.usuario;

import java.util.Collection;
import java.util.List;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroPemissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1115] Incluir Permissão Especial por Unidade Organizacional
 *
 * @author Mariana Victor
 * @date 29/12/2010
 * 
 */
public class ExibirIncluirPermissaoEspecialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("incluirPermissaoEspecial");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		IncluirPermissaoEspecialActionForm form = (IncluirPermissaoEspecialActionForm) actionForm;
		
		// Pesquisar Unidade Organizacional
		if((httpServletRequest.getParameter("menu") == null || 
	        	!httpServletRequest.getParameter("menu").equals(""))
	        	&& form.getIdUnidade() != null && !form.getIdUnidade().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

        	filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
        			FiltroUnidadeOrganizacional.ID, form.getIdUnidade()));
			
			Collection<UnidadeOrganizacional> unidadeEmpresaEncontrada = fachada.pesquisar(filtroUnidadeEmpresa,
					UnidadeOrganizacional.class.getName());

			if (unidadeEmpresaEncontrada != null && !unidadeEmpresaEncontrada.isEmpty()) {
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada).get(0);
				//a unidade de Unidade Empresa foi encontrado
				form.setIdUnidade(""
						+ unidadeOrganizacional.getId());
				form
						.setNomeUnidade(unidadeOrganizacional.getDescricao());
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "idUnidade");
				
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(
						FiltroUsuario.UNIDADE_ORGANIZACIONAL_ID, unidadeOrganizacional.getId()));
				filtroUsuario.adicionarParametro(new ParametroNaoNulo(
						FiltroUsuario.FUNCIONARIO));
				filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(
						FiltroUsuario.FUNCIONARIO);
				
				Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
				
				if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
					sessao.setAttribute("colecaoUsuario", colecaoUsuario);
				} else {
					sessao.removeAttribute("colecaoUsuario");
				}

				FiltroPemissaoEspecial filtroPemissaoEspecial = new FiltroPemissaoEspecial();
				filtroPemissaoEspecial.setCampoOrderBy(
						FiltroPemissaoEspecial.DESCRICAO);
				
				Collection colecaoPermissaoEspecial = fachada.pesquisar(
						filtroPemissaoEspecial, PermissaoEspecial.class.getName());;
						
				if (colecaoPermissaoEspecial != null && !colecaoPermissaoEspecial.isEmpty()) {
					sessao.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
				} else {
					sessao.removeAttribute("colecaoPermissaoEspecial");
				}
				
			} else {

				form.setIdUnidade("");
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada",
						"exception");
				form.setNomeUnidade("Unidade Organizacional inexistente");
				httpServletRequest.setAttribute("nomeCampo", "idUnidade");
				sessao.removeAttribute("colecaoUsuario");
				sessao.removeAttribute("colecaoPermissaoEspecial");

			}
		} else {
			sessao.removeAttribute("colecaoUsuario");
			sessao.removeAttribute("colecaoPermissaoEspecial");
		}
		
		return retorno;
	}

}
