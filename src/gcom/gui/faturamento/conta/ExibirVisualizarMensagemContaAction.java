package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirVisualizarMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("visualizarMensagemConta");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		VisualizarMensagemContaActionForm visualizarMensagemContaActionForm = (VisualizarMensagemContaActionForm) actionForm;
		
		visualizarMensagemContaActionForm.setMensagemConta01("");
		visualizarMensagemContaActionForm.setMensagemConta02("");
		visualizarMensagemContaActionForm.setMensagemConta03("");
		
		String idMensagem = httpServletRequest.getParameter("id");
		
		FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();
		
		filtroContaMensagem.adicionarParametro(new ParametroSimples(FiltroContaMensagem.ID, idMensagem));
		
		Collection colecaoMensagem = fachada.pesquisar(filtroContaMensagem, ContaMensagem.class.getName());
		
		if (colecaoMensagem != null && !colecaoMensagem.isEmpty()){
			//sessao.setAttribute("colecaoMensagem", colecaoMensagem);
			for (Iterator iter = colecaoMensagem.iterator(); iter.hasNext();) {
				ContaMensagem contaMensagem = (ContaMensagem) iter.next();
				
				visualizarMensagemContaActionForm.setMensagemConta01(contaMensagem.getDescricaoContaMensagem01());
				visualizarMensagemContaActionForm.setMensagemConta02(contaMensagem.getDescricaoContaMensagem02());
				visualizarMensagemContaActionForm.setMensagemConta03(contaMensagem.getDescricaoContaMensagem03());
			}
			
		} else {
			throw new ActionServletException(
				"atencao.mensagem_nao_encontrada");
		}

		return retorno;

	}

}
