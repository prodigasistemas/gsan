package gcom.gui.operacional;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Vinícius Medeiros
 * @date 20/05/2008
 */

public class ExibirFiltrarZonaPressaoAction extends GcomAction {

	private String distritoOperacionalID;

	private Collection colecaoPesquisa;

	/*
	 * @param actionMapping @param actionForm @param httpServletRequest @param
	 * httpServletResponse @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("filtrarZonaPressao");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm = (FiltrarZonaPressaoActionForm) actionForm;

		String primeiraVez = httpServletRequest.getParameter("menu");
		
		if (primeiraVez != null && !primeiraVez.equals("")) {
			
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarZonaPressaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		
		}			
		
		if(filtrarZonaPressaoActionForm.getIndicadorAtualizar()==null){
		
			filtrarZonaPressaoActionForm.setIndicadorAtualizar("1");
		
		}
		
		if (sessao.getAttribute("consulta") != null) {
		
			sessao.removeAttribute("consulta");
		
		}
		
        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
        
        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            //Recebe o valor do campo distritoOperacionalID do formulário.
            distritoOperacionalID = filtrarZonaPressaoActionForm.getDistritoOperacionalID();


            switch (Integer.parseInt(objetoConsulta)) {
            	// Distrito Operacional
            	case 1:
                    pesquisarDistritoOperacional(
                    		filtrarZonaPressaoActionForm,fachada, httpServletRequest);
                    
                    break;
                
            	default:
                    break;
            
            }
        }
	    

        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	filtrarZonaPressaoActionForm.setId("");
        	filtrarZonaPressaoActionForm.setDescricao("");
        	filtrarZonaPressaoActionForm.setDescricaoAbreviada("");
        	filtrarZonaPressaoActionForm.setDistritoOperacionalID("");
        	filtrarZonaPressaoActionForm.setIndicadorUso("");
        
        }
	
        return retorno;
	}
	
    private void pesquisarDistritoOperacional(
    		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm, Fachada fachada, 
    		HttpServletRequest httpServletRequest) {
        
    	if (distritoOperacionalID == null || distritoOperacionalID.trim().equalsIgnoreCase("")) {
            //Limpa o campo distritoOperacionalID do formulario
        	filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao("Informe Distrito Operacional");
            httpServletRequest.setAttribute("corDistritoOperacional", "exception");
        
    	} else {
        
    		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
            filtroDistritoOperacional.adicionarParametro(
            		new ParametroSimples(FiltroDistritoOperacional.ID, distritoOperacionalID));

            //Retorna Distrito Operacional
            colecaoPesquisa = fachada.pesquisar(
            		filtroDistritoOperacional,DistritoOperacional.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Distrito Operacional nao encontrada
                //Limpa o campo DistritoOperacionalID do formulário
            	filtrarZonaPressaoActionForm.setDistritoOperacionalID("");
            	filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao("Distrito Operacional inexistente.");
                httpServletRequest.setAttribute("corDistritoOperacional", "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
            
            } else {
            
            	DistritoOperacional objetoDistritoOperacional = 
            		(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoPesquisa);
                filtrarZonaPressaoActionForm.setDistritoOperacionalID(
                		String.valueOf(objetoDistritoOperacional.getId()));
                filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao(
                		objetoDistritoOperacional.getDescricao());
                httpServletRequest.setAttribute("corDistritoOperacional", "valor");
            
            }
        }
    	
    }
}
