package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action responsável pela pré-exibição da tela de alterar a senha do usuário
 *
 * @author Flávio Cordeiro
 * @date 24/02/2006
 */
public class EfetuarAlteracaoSenhaPorMatriculaAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		//Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
		
		EfetuarAlteracaoSenhaPorMatriculaActionForm form = (EfetuarAlteracaoSenhaPorMatriculaActionForm) actionForm;
		
		//Recupera uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usuário que está solicitando o lembrete da senha
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		if(form.getLogin() != null){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLogin()));
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(!colecaoUsuario.isEmpty()){
				Usuario usuario = (Usuario)colecaoUsuario.iterator().next();
				try {
					//if(usuario.getDataNascimento() == null || form.getDataNascimento().trim().equals(Util.formatarData(usuario.getDataNascimento()))){
						usuario.setSenha(Criptografia.encriptarSenha("gcom"));
					// }else{
					// throw new
					// ActionServletException("atencao.data_nascimento.incorreta.login",
					// null, form.getLogin());
					//					}
				} catch (ErroCriptografiaException e) {
					
					e.printStackTrace();
				}
//				//------------ REGISTRAR TRANSAÇÃO ----------------
//		        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//					Operacao.OPERACAO_USUARIO_ALTERAR_SENHA,
//					new UsuarioAcaoUsuarioHelper(usuarioLogado,
//				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//			        
//			    Operacao operacao = new Operacao();
//			    operacao.setId(Operacao.OPERACAO_USUARIO_ALTERAR_SENHA);
//			
//			    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//			    operacaoEfetuada.setOperacao(operacao);
//			    registradorOperacao.registrarOperacao(usuarioLogado);
//		    	//------------ REGISTRAR TRANSAÇÃO ----------------
				
				// ------------ REGISTRAR TRANSAÇÃO ----------------
				
				usuario.setUltimaAlteracao(new Date());
				
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				    Operacao.OPERACAO_USUARIO_ALTERAR_SENHA_LOGIN,
				    usuario.getId(), usuario.getId(),
				    new UsuarioAcaoUsuarioHelper(usuarioLogado,
				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(usuario);

				// ------------ REGISTRAR TRANSAÇÃO ----------------

				fachada.atualizar(usuario);
			}else{
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + form.getLogin() + "");
			}
		}
		
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Senha padrão para o login: "
				+ form.getLogin() + " gerada com sucesso.",
				"Alterar outra senha", "exibirEfetuarAlteracaoSenhaPorMatriculaAction.do?limparForm=ok");
		
		
		//Retornao mapeamento contido na variável retorno
		return retorno;
	}
}
