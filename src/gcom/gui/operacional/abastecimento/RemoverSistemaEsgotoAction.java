package gcom.gui.operacional.abastecimento;

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
 * [UC0525]	MANTER SISTEMA DE ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 13/03/2007
 */



public class RemoverSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
	
	        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();
	
	        ActionForward retorno = actionMapping.findForward("telaSucesso");
	
	        Fachada fachada = Fachada.getInstancia();
	        
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	
	        // mensagem de erro quando o usuário tenta excluir sem ter selecionado
	        // nenhum registro
	        if (ids == null || ids.length == 0) {
	            throw new ActionServletException("atencao.registros.nao_selecionados");
	        }
	
	       fachada.removerSistemaEsgoto(ids,usuarioLogado);
	        
	        // [FS0004] Verificar Sucesso de Transação
	        //Monta a página de sucesso
	        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
	            montarPaginaSucesso(httpServletRequest,ids.length + " Sistema(s) de Esgoto removido(s) com sucesso.",
	                    "Realizar outra Manutenção de Sistema de Esgoto",
	                    "exibirFiltrarSistemaEsgotoAction.do?menu=sim");
	        }
	
	        return retorno;

	}
}
