package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

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
public class InserirMotivoRevisaoTarifaSocialAction extends GcomAction {

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
				.findForward("inserir");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirMotivoRevisaoTarifaSocialActionForm inserirMotivoRevisaoTarifaSocialActionForm = (InserirMotivoRevisaoTarifaSocialActionForm) actionForm;
		
		Imovel imovel = (Imovel) sessao.getAttribute("imovelTarifa");

		TarifaSocialHelper tarifaSocialHelperMotivoRevisao = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperMotivoRevisao");

		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = tarifaSocialHelperMotivoRevisao.getTarifaSocialDadoEconomia();
		
		String idMotivoRevisao = inserirMotivoRevisaoTarifaSocialActionForm.getIdMotivoRevisao();
		
		if (idMotivoRevisao != null && !idMotivoRevisao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
		
			TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo = new TarifaSocialRevisaoMotivo();
		
			tarifaSocialRevisaoMotivo.setId(new Integer(idMotivoRevisao));
		
			tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(tarifaSocialRevisaoMotivo);
		
			tarifaSocialDadoEconomia.setDataRevisao(new Date());
			tarifaSocialDadoEconomia.setTarifaSocialExclusaoMotivo(null);
			tarifaSocialDadoEconomia.setDataExclusao(null);
			
		} else {
			tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(null);
			tarifaSocialDadoEconomia.setDataRevisao(null);
		}
		
		tarifaSocialDadoEconomia.setImovel(imovel);
		
		Collection colecaoTarifaSocialHelperAtualizar = null;
		
		if (sessao.getAttribute("colecaoTarifaSocialHelperAtualizar") != null) {
			colecaoTarifaSocialHelperAtualizar = (Collection) sessao.getAttribute("colecaoTarifaSocialHelperAtualizar"); 
		} else {
			colecaoTarifaSocialHelperAtualizar = new ArrayList();
		}
		
		colecaoTarifaSocialHelperAtualizar.add(tarifaSocialHelperMotivoRevisao);
		
		ArrayList colecaoTarifaSocialHelper = (ArrayList) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
		
		int i = 0;
		
		while (colecaoTarifaSocialHelperIterator.hasNext()) {

			TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
					.next();

			// Uma Economia
			if (tarifaSocialHelperMotivoRevisao.getClienteImovel() != null) {
				colecaoTarifaSocialHelper.set(i, tarifaSocialHelperMotivoRevisao);
				break;
			} 
			
			// Múltiplas Economias
			else if (tarifaSocialHelperMotivoRevisao.getClienteImovelEconomia() != null) {
				if (tarifaSocialHelper.getClienteImovelEconomia()
						.getImovelEconomia().getId().equals(
								tarifaSocialHelperMotivoRevisao
										.getClienteImovelEconomia()
										.getImovelEconomia().getId())) {
					colecaoTarifaSocialHelper.set(i, tarifaSocialHelperMotivoRevisao);
					break;
				}
					
			}
			
			i++;

		}
		
		sessao.setAttribute("colecaoTarifaSocialHelperAtualizar", colecaoTarifaSocialHelperAtualizar);
		sessao.setAttribute("colecaoTarifaSocialHelper", colecaoTarifaSocialHelper);
		sessao.setAttribute("atualizar", true);
		
		sessao.removeAttribute("tarifaSocialHelperMotivoRevisao");
		
		httpServletRequest.setAttribute("fecharPopup", true);

		return retorno;

	}

}
