package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade receber os parâmetros informados pelo usuário e realizar uma 
 * pesquisa de CEPs a partir dos mesmos 
 *
 * @author Raphael Rossiter
 * @date 06/05/2006
 */
public class PesquisarCepAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("pesquisarCep");

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarCepActionForm pesquisarCepActionForm = (PesquisarCepActionForm) actionForm;
        Municipio municipio = null;

        if (pesquisarCepActionForm.getIdMunicipio() == null || 
        	pesquisarCepActionForm.getIdMunicipio().equals("")){
        	
        	throw new ActionServletException("errors.required", null, "município");	
        }
        else{
        	
        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
        	
        	filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, 
            pesquisarCepActionForm.getIdMunicipio()));
        	if( sessao.getAttribute("indicadorUsoTodos") == null ){
        		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO, 
        				ConstantesSistema.INDICADOR_USO_ATIVO));
        	}    	
            Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
                	
            if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
            	municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
            }
            else{
            	throw new ActionServletException("atencao.municipio.inexistente");
            }
        }
        
        FiltroCep filtroCep = new FiltroCep(FiltroCep.CODIGO);

        filtroCep.adicionarParametro(new ComparacaoTexto(FiltroCep.MUNICIPIO, municipio.getNome()));
        
        if( sessao.getAttribute("indicadorUsoTodos") == null ){
    		filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.INDICADORUSO, 
    				ConstantesSistema.INDICADOR_USO_ATIVO));
    	}
        
        if (pesquisarCepActionForm.getNomeLogradouro() != null &&
        	!pesquisarCepActionForm.getNomeLogradouro().equals("")){
        	
        	if (pesquisarCepActionForm.getTipoPesquisaLogradouro() != null && 
        			pesquisarCepActionForm.getTipoPesquisaLogradouro().equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
							.toString())) {
        		
        		filtroCep.adicionarParametro(new ComparacaoTextoCompleto(FiltroCep.LOGRADOURO,
        	        	pesquisarCepActionForm.getNomeLogradouro()));        		
        		
        	}else {
        	filtroCep.adicionarParametro(new ComparacaoTexto(FiltroCep.LOGRADOURO,
        	pesquisarCepActionForm.getNomeLogradouro()));
        }
        }
        if(pesquisarCepActionForm.getIdCepInicial() != null && pesquisarCepActionForm.getIdCepInicial() != 0){
        	filtroCep.adicionarParametro(new MaiorQue(FiltroCep.CODIGO, pesquisarCepActionForm.getIdCepInicial()));        	
        	
        }
        if(pesquisarCepActionForm.getIdCepFinal() != null && pesquisarCepActionForm.getIdCepFinal() != 0){
    		filtroCep.adicionarParametro(new MenorQue(FiltroCep.CODIGO, pesquisarCepActionForm.getIdCepFinal()));
    	}
        
        
        /*
         * Código responsável pela implementação da nova paginação
         */
        Map resultado = controlarPaginacao(httpServletRequest, retorno,
		filtroCep, Cep.class.getName());
 
        Collection colecaoCep = (Collection) resultado.get("colecaoRetorno");
        
        retorno = (ActionForward) resultado.get("destinoActionForward");
        if(colecaoCep == null || colecaoCep.isEmpty())
        {
            throw new ActionServletException(
                  "atencao.pesquisa.nenhumresultado", null, "cep");
        }
        sessao.setAttribute("colecaoCep", colecaoCep);

        
        return retorno;
    }

}
