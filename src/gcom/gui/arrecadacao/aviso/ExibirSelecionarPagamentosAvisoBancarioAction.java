package gcom.gui.arrecadacao.aviso;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0611] Movimentar Pagamentos/Devoluções entre Avisos Bancários 
 * 
 * @author Ana Maria
 *
 * @date 07/06/2007
 */
public class ExibirSelecionarPagamentosAvisoBancarioAction extends
		GcomAction {
  
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSelecionarPagamentosAvisoBancario");
		
		//Pesquisando forma de arrecadação
		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
	    
		Collection colecaoArrecadacaoForma = 
	    	Fachada.getInstancia().pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());       
		
		if (colecaoArrecadacaoForma == null || colecaoArrecadacaoForma.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
					null, "Situação do Parcelamento");
		}
		
		httpServletRequest.setAttribute("colecaoArrecadacaoForma", colecaoArrecadacaoForma);
		
        return retorno;
      
	}
}
