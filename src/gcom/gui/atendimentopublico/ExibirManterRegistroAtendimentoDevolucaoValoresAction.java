package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
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
 * @author Vivianne Sousa
 * @date 14/03/2011
 */
public class ExibirManterRegistroAtendimentoDevolucaoValoresAction  extends GcomAction {
	
    /**
     * Método responsavel por responder a requisicao
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirManterRegistroAtendimentoDevolucaoValores");

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
  
        if (sessao.getAttribute("i") != null){
			sessao.removeAttribute("i");
		}
        
        RegistroAtendimentoDevolucaoValoresHelper helper = (RegistroAtendimentoDevolucaoValoresHelper)
        	sessao.getAttribute("registroAtendimentoDevolucaoValoresHelper");
        
		Integer totalRegistros = fachada.obterQtdeRegistroAtendimento(helper);
		sessao.setAttribute("totalRegistros",totalRegistros);
		
		Collection colecaoRegistroAtendimento = null;
		
		if (totalRegistros == null || totalRegistros.equals(new Integer(0))) {
			// Nenhum registro encontrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Registro de Atendimento");
		}else{
			
			retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);

			colecaoRegistroAtendimento = fachada.obterRegistroAtendimento(helper, 
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
			
		}
		
		sessao.setAttribute("colecaoRegistroAtendimento",colecaoRegistroAtendimento);
        
		if (colecaoRegistroAtendimento == null || colecaoRegistroAtendimento.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		sessao.setAttribute("totalRegistros", totalRegistros);
		sessao.setAttribute("numeroPaginasPesquisa",httpServletRequest.getAttribute("numeroPaginasPesquisa"));
			
        
        return retorno;
   }
    
}
