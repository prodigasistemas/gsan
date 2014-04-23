package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informações da 5° aba do processo de inserção
 * de um Comando de Negativação
 *
 * @author Vivianne Sousa
 * @date 05/07/2010
 */
public class InserirComandoNegativacaoExclusaoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");
        
        Fachada fachada = Fachada.getInstancia();
		
		InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm) actionForm;		
		
        //Pesquisa Localidade Incial
        String idLocalidadeInicial= form.getIdLocalidadeInicial();
        if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
        	
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();              
        	filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));         
            Collection colecaoLocalidade = fachada.pesquisar(
            		filtroLocalidade,Localidade.class.getName());
            
            if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");         	
			} 
        }
        
        //Pesquisa Localidade Final
        String idLocalidadeFinal= form.getIdLocalidadeFinal();
        if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
        	
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();              
        	filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeFinal));            
            Collection colecaoLocalidade = fachada.pesquisar(
            		filtroLocalidade,Localidade.class.getName());
            
            if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.localidade_final_inexistent");               	
			}
        } 
        
      	//Pesquisa Setor Comercial inicial
        String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();
        if ((codigoSetorComercialInicial != null && !codigoSetorComercialInicial.toString().trim().equalsIgnoreCase(""))
    		&& (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase(""))) {
    	
    		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();		
    		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));	
    		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(codigoSetorComercialInicial)));
    		
    		Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
    				
    		if (setorComerciais == null || setorComerciais.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");  			
    		} 
    	}
        
      	//Pesquisa Setor Comercial final
        String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
        if ((codigoSetorComercialFinal != null && !codigoSetorComercialFinal.toString().trim().equalsIgnoreCase(""))
        		&& (idLocalidadeFinal != null && !idLocalidadeFinal.toString().trim().equalsIgnoreCase(""))) {
        	
        		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();		
        		filtroSetorComercial.adicionarParametro(new ParametroSimples(
        				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));	
        		filtroSetorComercial.adicionarParametro(new ParametroSimples(
        				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(codigoSetorComercialFinal)));
        		
        		Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
        				
        		if (setorComerciais == null || setorComerciais.isEmpty()) {
                	throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");  			
        		} 
        }	
	
        return retorno;
	}

}
