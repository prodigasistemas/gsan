package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action responsável por alterar a senha do usuário 
 *
 * @author Pedro Alexandre
 * @date 12/07/2006
 */
public class EfetuarAlteracaoSenhaSimplificadaAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
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

		// Prepara o retorno da ação para a tela principal
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		//Recupera uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usuário que está solicitando o lembrete da senha
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		// Recupera o ActionForm
		EfetuarAlteracaoSenhaSimplificadaActionForm efetuarAlteracaoSenhaSimplificadaActionForm = (EfetuarAlteracaoSenhaSimplificadaActionForm) actionForm;

		//Recupera todos os dados necessários para verificar se o usuário 
		//pode ser lembrada sua senha
		String dataNascimentoString = Util.formatarData(usuarioLogado.getDataNascimento());
		String lembreteSenha = efetuarAlteracaoSenhaSimplificadaActionForm.getLembreteSenha();
		String novaSenha = efetuarAlteracaoSenhaSimplificadaActionForm.getNovaSenha();
		String confirmacaoNovaSenha = efetuarAlteracaoSenhaSimplificadaActionForm.getConfirmacaoNovaSenha();
		String senha;
		try {
			senha = Criptografia.encriptarSenha(efetuarAlteracaoSenhaSimplificadaActionForm.getSenha());
			
			if(senha.trim().equals(usuarioLogado.getSenha())){
				fachada.efetuarAlteracaoSenha(usuarioLogado, dataNascimentoString, usuarioLogado.getCpf(), lembreteSenha, novaSenha, confirmacaoNovaSenha);				
			}else{
				 throw new ActionServletException(
		            		"atencao.senha.invalida", null, "Senha Inexistente");
			}
		} catch (ErroCriptografiaException e) {
			
			e.printStackTrace();
		}
		
			
		//Caso o mapeamento seja para a tela de sucesso do popup
		//monta a tela de sucesso indicando que o email foi enviado 
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
		  montarPaginaSucesso(httpServletRequest,"Senha alterada com sucesso.", "", "");
	    }
		 
		return retorno;
	}
}
