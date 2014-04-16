package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ControladorException;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 08/06/2006
 */
public class ExibirBloquearDesbloquearAcessoUsuarioAction extends GcomAction {

	/**
	 * Este caso de uso permite bloquear ou desbloquear o acesso do usuário ao
	 * sistema.
	 * 
	 * [UC0291] Bloquear/Desbloquear Acesso
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/06/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws ControladorException
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws ControladorException {

		ActionForward retorno = actionMapping
				.findForward("bloquearDesbloquearAcessoUsuario");

		Fachada fachada = Fachada.getInstancia();

		BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm = (BloquearDesbloquearAcessoUsuarioActionForm) actionForm;

		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
		
		filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(FiltroUsuarioSituacao.INDICADOR_DE_USO_SISTEMA, "2"));

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<UsuarioSituacao> colecaoUsuarioSituacao = fachada.pesquisar(
				filtroUsuarioSituacao, UsuarioSituacao.class.getName());

		if (colecaoUsuarioSituacao == null || colecaoUsuarioSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Usuário Situação");
		}

		httpServletRequest.setAttribute("colecaoUsuarioSituacao",
				colecaoUsuarioSituacao);

		Usuario usuario = new Usuario();

		
		
		String login =  bloquearDesbloquearAcessoUsuarioActionForm
		.getLogin();

		if (login != null && !login.equals("")) {
			

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(
					FiltroUsuario.LOGIN, login));

			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			
			if (colecaoUsuario == null || colecaoUsuario.isEmpty()) {
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + login + "");
			} else {
				Iterator colecaoUsuarioIterator = colecaoUsuario.iterator(); 
					while( colecaoUsuarioIterator.hasNext() ){
						usuario = (Usuario) colecaoUsuarioIterator.next();
					
						if ( usuario.getLogin().equalsIgnoreCase(login) ) {
							bloquearDesbloquearAcessoUsuarioActionForm
								.setUsuarioSituacao(usuario.getUsuarioSituacao()
										.getId().toString());
						}
					}
				}
			}

		return retorno;

	}

}
