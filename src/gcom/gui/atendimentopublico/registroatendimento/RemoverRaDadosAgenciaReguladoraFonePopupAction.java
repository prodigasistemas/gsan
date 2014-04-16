package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0459] Adicionar Ra Dados Agencia Reguladora Fone Popup 
 * 
 * @author Kassia Albuquerque
 * @created 18/04/2006
 */


public class RemoverRaDadosAgenciaReguladoraFonePopupAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("removerRaDadosAgenciaReguladoraFonePopup");
	
			HttpSession sessao = httpServletRequest.getSession(false);
			
			RaDadosAgenciaReguladoraFone raDadosAgenciaReguladoraFone = new RaDadosAgenciaReguladoraFone();
			
			
			
			//	 REMOVENDO TELEFONE 
				
				if (httpServletRequest.getParameter("timestamp") != null && 
														!httpServletRequest.getParameter("timestamp").equals("")) {

						Collection colecaoFoneRemocao = (Collection) sessao.getAttribute("collectionRaDadosAgenciaReguladoraFone");
						
						Iterator iterator = colecaoFoneRemocao.iterator();
						
						long timestamp = new Long(httpServletRequest.getParameter("timestamp")).longValue();

						while (iterator.hasNext()) {
							
							raDadosAgenciaReguladoraFone = (RaDadosAgenciaReguladoraFone) iterator.next();
							if (GcomAction.obterTimestampIdObjeto(raDadosAgenciaReguladoraFone) == timestamp) {
								iterator.remove();
							}
						}
						sessao.setAttribute("collectionRaDadosAgenciaReguladoraFone",colecaoFoneRemocao);
						}
				
				return retorno;
			}
}
