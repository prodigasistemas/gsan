package gcom.gui.arrecadacao;

import gcom.arrecadacao.GuiaDevolucao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManterGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ManterGuiaDevolucaoActionForm manterGuiaDevolucaoActionForm = (ManterGuiaDevolucaoActionForm) actionForm;

        String idImovel = manterGuiaDevolucaoActionForm.getIdImovel();
        
		String[] idsRegistrosRemocao = manterGuiaDevolucaoActionForm
				.getIdRegistrosRemocao();

        /*
         * Caso o usuário tenha informado o id do imóvel
         * verifica o controle de abrangência 
         * Caso contrario não tem controle de abrangência.
         */
        if(idImovel != null && !idImovel.trim().equals("")){
            /** alterado por pedro alexandre dia 24/11/2006 
             * Recupera o usuário logado para passar no metódo de remover guia de devolução 
             * para verificar se o usuário tem abrangência para remover a guia de devolução
             * para o imóvel informado.
             */
            HttpSession sessao = httpServletRequest.getSession(false);
            Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
            fachada.removerGuiaDevolucao(idImovel, usuarioLogado, idsRegistrosRemocao, GuiaDevolucao.class.getName(), null, null);
        }else{
            fachada.remover(idsRegistrosRemocao, GuiaDevolucao.class.getName(), null, null);
        }

		montarPaginaSucesso(httpServletRequest, idsRegistrosRemocao.length
				+ " Guia(s) de Devolução removida(s) com sucesso.",
				"Realizar outra Manutenção de Guia de Devolução",
				"exibirFiltrarGuiaDevolucaoAction.do?menu=sim");

		return retorno;

	}
}
