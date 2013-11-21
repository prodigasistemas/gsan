package gcom.gui.relatorio.cadastro;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1170] - Gerar Relatório Acesso ao SPC
 * 
 * @author Diogo Peixoto
 * @since 06/05/2011
 * 
 */
public class ExibirGerarRelatorioAcessoSPCAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAcessoSPC");
		GerarRelatorioAcessoSPCActionForm form = (GerarRelatorioAcessoSPCActionForm) actionForm;

		String unidadeOrganizacional = httpServletRequest.getParameter("pesquisarUnidade");
		if(unidadeOrganizacional != null && unidadeOrganizacional.equalsIgnoreCase("SIM")){
			this.pesquisarUnidadeOrganizacional(httpServletRequest, form);
		}else{
			if(form.getIdUnidadeOrganizacional() == null || form.getIdUnidadeOrganizacional().equals("")){
				form.setDescricaoUnidadeOrganizacional("");
			}
		}
		
		String usuarioResponsavel = httpServletRequest.getParameter("pesquisarUsuarioResponsavel");
		if(usuarioResponsavel != null && usuarioResponsavel.equalsIgnoreCase("SIM")){
			this.pesquisarUsuarioResponsavel(httpServletRequest, form);
		}else{
			if(form.getLoginUsuarioResponsavel() == null || form.getLoginUsuarioResponsavel().equals("")){
				form.setNomeUsuarioResponsavel("");
			}
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa Unidade Organizacional
	 *
	 * @author Diogo Peixoto
	 * @date 06/05/2011
	 * 
	 */
	private void pesquisarUnidadeOrganizacional(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessoSPCActionForm form){
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getIdUnidadeOrganizacional()));
		Collection colecaoUnidadeOrganizacional = 
			Fachada.getInstancia().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getSimpleName());
		
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) colecaoUnidadeOrganizacional.iterator().next();
			form.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
			form.setDescricaoUnidadeOrganizacional(unidadeOrganizacional.getDescricao());
		} else {
			form.setIdUnidadeOrganizacional("");
			form.setDescricaoUnidadeOrganizacional("UNIDADE DE NEGOCIO INEXISTENTE.");
			httpServletRequest.setAttribute("unidadeOrganizacionalInexistente",true);
		}
	}
	
	/**
	 * Pesquisa Usuário Responsável
	 *
	 * @author Diogo Peixoto
	 * @date 06/05/2011
	 */
	private void pesquisarUsuarioResponsavel(HttpServletRequest httpServletRequest, GerarRelatorioAcessoSPCActionForm form){

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuarioResponsavel()));
		Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = (Usuario) colecaoUsuario.iterator().next();
			form.setLoginUsuarioResponsavel(usuario.getLogin());
			form.setNomeUsuarioResponsavel(usuario.getNomeUsuario());
		} else {
			form.setLoginUsuarioResponsavel("");
			form.setNomeUsuarioResponsavel("USUÁRIO INEXISTENTE.");
			httpServletRequest.setAttribute("usuarioResponsavelInexistente",true);
		}
	}
}