package gcom.gui.cadastro;

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
 * @date 31/03/2011
 */
public class ExibirResultadoSelecionarComandoRetirarImovelTarifaSocialAction  extends GcomAction {
	
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

        ActionForward retorno = actionMapping.findForward("exibirResultadoSelecionarComandoRetirarImovelTarifaSocial");

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
  
    	SelecionarComandoRetirarImovelTarifaSocialActionForm form = (SelecionarComandoRetirarImovelTarifaSocialActionForm) actionForm;
        
		Collection colacaoTarifaSocialComandoCarta  = null;
		
        Integer totalRegistros = fachada.pesquisarQtdeTarifaSocialComandoCarta(new Integer(form.getIndicadorTipoCarta()),form.getIndicadorSituacao());
        sessao.setAttribute("totalRegistros",totalRegistros);
        
        if (totalRegistros == null || totalRegistros.equals(new Integer(0))) {
			// Nenhum registro encontrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Comando");
		}else{
			retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);

			colacaoTarifaSocialComandoCarta =  fachada.pesquisarTarifaSocialComandoCarta(
					new Integer(form.getIndicadorTipoCarta()),form.getIndicadorSituacao(),
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		}
        
    	sessao.setAttribute("colacaoTarifaSocialComandoCarta",colacaoTarifaSocialComandoCarta);

		if (colacaoTarifaSocialComandoCarta == null || colacaoTarifaSocialComandoCarta.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		sessao.setAttribute("totalRegistros", totalRegistros);
		sessao.setAttribute("numeroPaginasPesquisa",httpServletRequest.getAttribute("numeroPaginasPesquisa"));
			
        
		//Habilita Botões 
		if(form.getIndicadorSituacao().equals("1")){
			httpServletRequest.setAttribute("remover",1);
			httpServletRequest.setAttribute("gerar",1);
			httpServletRequest.setAttribute("marcar",1);
		}else if(form.getIndicadorSituacao().equals("2")){
			httpServletRequest.setAttribute("executar",1);
			httpServletRequest.setAttribute("marcar",1);
			httpServletRequest.setAttribute("remover",1);
		}
		
		
        return retorno;
   }
    
}
