package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corrêa
 * @since 16/01/2007
 */
public class RemoverTarifaSocialAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("remover");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		RemoverTarifaSocialActionForm removerTarifaSocialActionForm = (RemoverTarifaSocialActionForm) actionForm;

		String idTarifaSocialDadoEconomia = (String) sessao.getAttribute("idTarifaSocial");
		
		String idMotivoExclusao = removerTarifaSocialActionForm.getIdMotivoExclusao();
		
		TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();
		
		tarifaSocialExclusaoMotivo.setId(new Integer(idMotivoExclusao));
		
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = null;
		
		Imovel imovel = (Imovel) sessao.getAttribute("imovelTarifa");
		
		ArrayList colecaoTarifaSocialHelper = (ArrayList) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		if (colecaoTarifaSocialHelper != null && !colecaoTarifaSocialHelper.isEmpty()) {
			Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
			
			int i = 0;
			
			while (colecaoTarifaSocialHelperIterator.hasNext()) {
				TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator.next();
				
				if (tarifaSocialHelper.getTarifaSocialDadoEconomia().getId().toString().equals(idTarifaSocialDadoEconomia)) {
					tarifaSocialDadoEconomia = tarifaSocialHelper.getTarifaSocialDadoEconomia();
					if (tarifaSocialHelper.getClienteImovelEconomia() != null) {
						tarifaSocialDadoEconomia.setImovelEconomia(tarifaSocialHelper.getClienteImovelEconomia().getImovelEconomia());
					}
					tarifaSocialDadoEconomia.setImovel(imovel);
					tarifaSocialDadoEconomia.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
					tarifaSocialDadoEconomia.setDataExclusao(new Date());
					tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(null);
					tarifaSocialDadoEconomia.setDataRevisao(null);
					tarifaSocialHelper.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
					colecaoTarifaSocialHelper.set(i, tarifaSocialHelper);
					sessao.setAttribute("atualizar", true);
					break;
				}
				
				i++;
			}
		}
		
		Collection colecaoTarifaSocialExcluida = null;
		
		if (sessao.getAttribute("colecaoTarifaSocialExcluida") != null
				&& !(((Collection) sessao
						.getAttribute("colecaoTarifaSocialExcluida")).isEmpty())) {
			colecaoTarifaSocialExcluida = (Collection) sessao.getAttribute("colecaoTarifaSocialExcluida"); 
		} else {
			colecaoTarifaSocialExcluida = new ArrayList();
		}
		
		colecaoTarifaSocialExcluida.add(tarifaSocialDadoEconomia);
		
		sessao.setAttribute("colecaoTarifaSocialExcluida", colecaoTarifaSocialExcluida);
		
		sessao.removeAttribute("idTarifaSocial");
		
		httpServletRequest.setAttribute("fecharPopup", true);

		return retorno;

	}

}
