package gcom.gui.seguranca.acesso;

import java.util.Date;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action responsável pela pré-exibição da tela de alterar a senha do usuário
 *
 * @author Pedro Alexandre
 * @date 17/07/2006
 */
public class ExibirEfetuarAlteracaoSenhaAction extends GcomAction {

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

		// Prepara o retorno da ação para a tela de lembrar senha
		ActionForward retorno = actionMapping.findForward("efetuarAlteracaoSenha");
		
		
		//Recupera uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usuário que está logado
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		/*================================================================================
		Alteração: CRC1959 - Remocao do link de Alterar
		Senha e Combo de Ultimos Acessos caso o usuario logado ainda não tenha realizado
		a alteracao da senha padrao recebida no cadastro do mesmo no sistema
		autor: Anderson Italo
		data:19/06/2009*/
		Date dataExpiracaoAcesso = usuarioLogado.getDataExpiracaoAcesso();
		if (dataExpiracaoAcesso != null) {
			if (dataExpiracaoAcesso.before(new Date())) {
				sessao.setAttribute("indicadorSenhaPendente", 1);
			}
		}
		
		UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();
		if (usuarioSituacao.getId().equals(UsuarioSituacao.PENDENTE_SENHA)) {
			sessao.setAttribute("indicadorSenhaPendente", 1);
		}
		/*========================Fim da Alteração=======================================*/
		
		//Recupera o lembrete da senha do usuário
		String lembreteSenha = usuarioLogado.getLembreteSenha();
		
		//Caso o lembrete esteja nulo, seta o lembrete para uma string vazia 
		if(lembreteSenha == null){
			lembreteSenha = "";
		}
		
		String mensagem = "A nova senha deve conter de seis a oito caracteres, " +
		"dentre as quais pelo menos uma letra(A, B, C,...,a,b,c,...), " +
	    "pelo menos um número(0,1,2...) ," +
	    "não possuir seguencia de 3 caracteres iguais(aaa,111,...). exemplo: Gsan10";
		
		httpServletRequest.setAttribute("mensagem",mensagem);
		
		//Seta o lembrete da senha do usuário no request
		httpServletRequest.setAttribute("lembreteSenha",lembreteSenha);
		
		//Retornao mapeamento contido na variável retorno
		return retorno;
	}
}
