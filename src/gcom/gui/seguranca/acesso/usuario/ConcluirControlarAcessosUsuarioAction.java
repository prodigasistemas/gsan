package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action conclui o Manter Usuario
 * 
 * @author Sávio Luiz
 * @date 09/05/2006
 */
public class ConcluirControlarAcessosUsuarioAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;

		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");

		Integer[] idsGrupos = (Integer[]) sessao.getAttribute("grupo");

		// Recupera os acessos do grupo da sessão
		Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap = (Map<Integer, Map<Integer, Collection<Operacao>>>) sessao
				.getAttribute("funcionalidadesMap");

		String[] idsPermissoesEspeciais = controlarAcessoUsuarioActionForm
				.getPermissoesEspeciais();
		
		String permissoesCheckBoxVazias = controlarAcessoUsuarioActionForm.getPermissoesCheckBoxVazias();

		//Passa o usuário logado para registrar operação.
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	
		Fachada.getInstancia().atualizarControleAcessoUsuario(
				idsPermissoesEspeciais, funcionalidadesMap,
				usuarioParaAtualizar, idsGrupos,permissoesCheckBoxVazias, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Usuário de Login "
				+ usuarioParaAtualizar.getLogin() + " atualizado com sucesso!",
				"Realizar outra Manutenção de Usuário",
				"exibirFiltrarUsuarioAction.do?menu=sim");

		sessao.removeAttribute("usuario");
		sessao.removeAttribute("grupo");
		sessao.removeAttribute("usuarioParaAtualizar");

		return retorno;
	}
}
