package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action responsável pela pré-exibição da tela de alterar a senha do usuário
 *
 * @author Flávio Cordeiro
 * @date 24/02/2006
 */
public class ExibirEfetuarAlteracaoSenhaPorMatriculaAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Flavio Cordeiro
	 * @date 24/02/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		// Prepara o retorno da ação para a tela de lembrar senha
		ActionForward retorno = actionMapping.findForward("efetuarAlteracaoSenhaPorMatricula");
		//Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
		
		EfetuarAlteracaoSenhaPorMatriculaActionForm form = (EfetuarAlteracaoSenhaPorMatriculaActionForm) actionForm;
		
		if(httpServletRequest.getParameter("limparForm") != null){
			form.setLogin("");
			form.setNomeUsuario("");
			form.setDataNascimento("");
		}
		
		if(form.getLogin() != null && !form.getLogin().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLogin()));
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(!colecaoUsuario.isEmpty()){
				Usuario usuario = (Usuario)colecaoUsuario.iterator().next();
				form.setNomeUsuario(usuario.getNomeUsuario());
				form.setDataNascimento(Util.formatarData(usuario.getDataNascimento()));
			}else{
				form.setNomeUsuario("LOGIN INEXISTENTE");
				httpServletRequest.setAttribute("corLabel","Sim");
			}
		}
		
		
		//Retornao mapeamento contido na variável retorno
		return retorno;
	}
}
