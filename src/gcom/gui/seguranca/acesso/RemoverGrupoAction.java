package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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
 * Action responsável por remover um grupo do sistema e 
 * suas permissões se existir. 
 *
 * @author Pedro Alexandre
 * @date 29/06/2006
 */
public class RemoverGrupoAction extends GcomAction {
	
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0279] - Manter Grupo
     *
     * @author Pedro Alexandre
     * @date 29/06/2006
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

        //Recupera o form de manutenção de registros
        ManutencaoRegistroActionForm manterGrupoActionForm = (ManutencaoRegistroActionForm) actionForm;

        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Recupera os id's de grupo selecionados para remoção.
        String[] idsRegistrosRemocao = manterGrupoActionForm.getIdRegistrosRemocao();

        /* Caso nenhum grupo tenha sido selecionado para remoção
         * levanta uma exceçaõ indicando que nenhum registro foi selecionado
         * caso contrário remove todos os grupos selecionados e suas permições.
         */         
        if (idsRegistrosRemocao == null || idsRegistrosRemocao.length < 1) {
            //Nenhum Grupo foi escolhido
            throw new ActionServletException("atencao.registros.nao_selecionados");
        } else {
        	//Remove Grupo(s) selecionado(s)
            //REGISTRAR OPERAÇÃO
        	//Cria uma instância da sessão
        	HttpSession sessao = httpServletRequest.getSession(false);
        	
        	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        	        	
        	fachada.removerGrupo(idsRegistrosRemocao, usuarioLogado);
        }
        
        //Monta a página de sucesso
        montarPaginaSucesso(httpServletRequest,
        		idsRegistrosRemocao.length +
				" Grupo(s) removido(s) com sucesso.",
				"Realizar outra manutenção de grupo",
				"exibirManterGrupoAction.do?menu=sim");
        
        //Retorna o mapeamento contido na variável retorno 
        return retorno;
    }
}
