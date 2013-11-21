package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.EspecificacaoPavimentacaoServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeterminarTipoServicoEspecificacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoEspServTip = (Collection) sessao.getAttribute("colecaoEspServTipo");
		
		if (colecaoEspServTip != null && !colecaoEspServTip.isEmpty()){
			
			Iterator iterator = colecaoEspServTip.iterator();
			
			while (iterator.hasNext()){
				
				EspecificacaoPavimentacaoServicoTipo espServTip = 
							(EspecificacaoPavimentacaoServicoTipo) iterator.next();
				
				fachada.inserirOuAtualizar(espServTip);
				
			}
		}
		
		Collection colecaoRemoverEspServTip = (Collection) sessao.getAttribute("colecaoRemoverEspServTipo");
		
		if (colecaoRemoverEspServTip != null && !colecaoRemoverEspServTip.isEmpty()){
			
			Iterator iterator = colecaoRemoverEspServTip.iterator();
			
			while (iterator.hasNext()){
				
				EspecificacaoPavimentacaoServicoTipo espServTip = 
							(EspecificacaoPavimentacaoServicoTipo) iterator.next();
				
				if (espServTip.getId() != null){
					fachada.remover(espServTip);
				}
			}
		}
		
		montarPaginaSucesso(httpServletRequest, "Especificação de Pavimentação do tipo de serviço informada com sucesso.",
		"Inserir outra Especificação de Pavimentação",
		"exibirDeterminarTipoServicoEspecificacaoAction.do?menu=sim",
		"","");
		
		sessao.removeAttribute("DeterminarTipoServicoEspecificacaoActionForm");
		
		return retorno;
	}
	
}