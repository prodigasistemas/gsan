package gcom.gui.atendimentopublico.ordemservico;


import java.util.Collection;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de exibir consultar motivo aceite repavimentacao Popup
 * 
 * @author Arthur Carvalho
 * @created 26/07/2010
 */
public class ExibirConsultarDescricaoMotivoAceiteRepavimentacaoPopupAction extends GcomAction {
	/**
	 * Execute do Consultar OS Popup
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("motivoPopup");
		
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarDescricaoMotivoAceiteRepavimentacaoPopupActionForm form = 
							(ConsultarDescricaoMotivoAceiteRepavimentacaoPopupActionForm) actionForm;
		
		if ( httpServletRequest.getParameter("idOSPav") != null &&
				!httpServletRequest.getParameter("idOSPav").equals("") ) {
			
			Integer id = new Integer(httpServletRequest.getParameter("idOSPav"));
				
			FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
			filtroOrdemServicoPavimento.adicionarParametro(new ParametroSimples( FiltroOrdemServicoPavimento.ID,
						id));
	
			Collection colecaoOrdemServicoPavimento = fachada.pesquisar(filtroOrdemServicoPavimento, 
					OrdemServicoPavimento.class.getName());
			OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) 
					Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
			
			form.setMotivo(ordemServicoPavimento.getDescricaoMotivoAceite());
		}
		
        
        
		return retorno;
	}
	
}
