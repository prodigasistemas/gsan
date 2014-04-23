package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável por remover uma operação do sistema 
 *
 * @author Pedro Alexandre
 * @date 02/08/2006
 */
public class RemoverOperacaoAction extends GcomAction {
	
	/**
	 *
	 * [UC0281] - Manter Operação
	 *
	 * @author Pedro Alexandre
	 * @date 02/08/2006
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

		//Seta o mapeamento de retorno para a tela de sucesso
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usuário logado no sistema
		Usuario usuarioLogado =(Usuario) sessao.getAttribute("usuarioLogado");
		
		//Recupera o form de manutenção 
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		//Recupera os ids das operações para remoção 
		String[] idsRegistrosRemocao = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		//Chama o metódo de remover operação da fachada
		fachada.removerOperacao(idsRegistrosRemocao, usuarioLogado);

		//Monta a tela de sucesso 
		montarPaginaSucesso(httpServletRequest, idsRegistrosRemocao.length
				+ " Operação(ões) excluída(s) com sucesso.",
				"Manter outra Operação",
				"exibirFiltrarOperacaoAction.do?menu=sim");

		//Retorna o mapeamento contido na variável retorno 
		return retorno;
	}
}
