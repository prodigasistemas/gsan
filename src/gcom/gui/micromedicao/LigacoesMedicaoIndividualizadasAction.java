package gcom.gui.micromedicao;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Flávio Cordeiro
 * @date 12/12/2006
 * 
 */

public class LigacoesMedicaoIndividualizadasAction extends GcomAction {

	   public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("ligacoesMedicaoIndividualizadas");
	        
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
			// Obtém o action form
//	        LigacoesMedicaoIndividualizadaActionForm ligacoesMedicaoIndividualizadaActionForm = (LigacoesMedicaoIndividualizadaActionForm) actionForm;

			// Recupera os parâmetros da url
	        String idImovel = httpServletRequest.getParameter("codigoImovel");
	        FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) sessao.getAttribute("faturamentoGrupo");
	        if(idImovel == null || idImovel.trim().equals("")){
	        	idImovel = (String)sessao.getAttribute("codigoImovel");
	        }
	        
	        sessao.setAttribute("codigoImovel", idImovel);
	        
	        Collection colecaoLigacoesMedicaoIndividualizada = fachada.pesquisarLigacoesMedicaoIndividualizada(new Integer(idImovel), faturamentoGrupo.getAnoMesReferencia().toString());
	        
	        sessao.setAttribute("colecaoLigacoesMedicaoIndividualizada", colecaoLigacoesMedicaoIndividualizada);
	        
	        return retorno;
	    }

	}
