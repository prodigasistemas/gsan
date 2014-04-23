package gcom.gui.cadastro;

import gcom.cadastro.ImovelInscricaoAlteradaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1162] AUTORIZAR ALTERACAO INSCRICAO IMOVEL
 * 
 * @author Rodrigo Cabral
 * @date 05/04/2011
 */

public class ExibirAutorizarAlteracaoInscricaoImovelAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
        ActionForward retorno = actionMapping.findForward("exibirAutorizarAlteracaoInscricaoImovel");

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
  
        if (sessao.getAttribute("i") != null){
			sessao.removeAttribute("i");
		}
        
        ImovelInscricaoAlteradaHelper helper = (ImovelInscricaoAlteradaHelper) 
			sessao.getAttribute("imovelInscricaoAlterada");
        
		Collection colecaoImoveisAgrupadosQuadra = fachada.pesquisaImovelInscricaoAlterada(helper);
		

		if (colecaoImoveisAgrupadosQuadra == null || colecaoImoveisAgrupadosQuadra.isEmpty()) {
			// Nenhum registro encontrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Imoveis com inscricao alterada");
		}
		
		
		sessao.setAttribute("colecaoImoveisAgrupadosQuadra", colecaoImoveisAgrupadosQuadra);

        
        return retorno;
		
	} 
	
}
