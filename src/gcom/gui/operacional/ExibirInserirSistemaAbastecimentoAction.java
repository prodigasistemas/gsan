package gcom.gui.operacional;

import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroTipoCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.TipoCaptacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirSistemaAbastecimentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("exibirInserirSistemaAbastecimentoAction");

        InserirSistemaAbastecimentoActionForm form = 
        	(InserirSistemaAbastecimentoActionForm) actionForm;

        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
        
       if(httpServletRequest.getParameter("menu")!= null && httpServletRequest.getParameter("menu").equals("sim")){
    	   form.setTipoCaptacao("");
       }
        
        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

            //Collection colecaoPesquisa = null;

            switch (Integer.parseInt(objetoConsulta)) {

            case 1:
            	//TIpo Captacao
            	TipoCaptacao objetoTipoCaptacao = 
            		this.pesquisarTipoCaptacao(form.getTipoCaptacao());

                if (objetoTipoCaptacao == null) {

                	form.setTipoCaptacao("");
                    form.setDescricaoTipoCaptacao("Tipo de Captação inexistente.");
                    //Limpa a Fonte de Captacao
                    form.setFonteCaptacao("");
                    form.setDescricaoFonteCaptacao("");
                }else{

                    form.setTipoCaptacao(String.valueOf(objetoTipoCaptacao.getId()));
                    form.setDescricaoTipoCaptacao(objetoTipoCaptacao.getDescricao());
                    
                	httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
                }
            	
                break;
                
            case 2:
            	
            	//Fonte Captacao
            	FonteCaptacao objetoFonteCaptacao = 
            		this.pesquisarFonteCaptacao(form.getFonteCaptacao());

                if (objetoFonteCaptacao == null) {

                	form.setFonteCaptacao("");
                    form.setDescricaoFonteCaptacao("Fonte de Captação inexistente.");
                }else{

                    form.setFonteCaptacao(String.valueOf(objetoFonteCaptacao.getId()));
                    form.setDescricaoFonteCaptacao(objetoFonteCaptacao.getDescricao());
                    
                	httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
                }
            	
                break;
            default:

                break;
            }
        } else {

            //Limpando o formulário
            form.setFonteCaptacao("");
            form.setDescricaoFonteCaptacao("");

        }
        
        //devolve o mapeamento de retorno
        this.setaRequest(httpServletRequest,form);
        
        return retorno;
    }
    
    private FonteCaptacao pesquisarFonteCaptacao(String fonte){
    	
    	Collection colecaoPesquisa = null;
    	FonteCaptacao objetoFonteCaptacao = null;
    		
    	FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();

        filtroFonteCaptacao.adicionarParametro(
        	new ParametroSimples(
        		FiltroFonteCaptacao.ID, 
        		fonte));

        filtroFonteCaptacao.adicionarParametro(
        	new ParametroSimples(
        			FiltroFonteCaptacao.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna a fonte de captacao
        colecaoPesquisa = 
        	this.getFachada().pesquisar(
        		filtroFonteCaptacao,
                FonteCaptacao.class.getName());
        
        if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
        	objetoFonteCaptacao = 
            	(FonteCaptacao) Util.retonarObjetoDeColecao(colecaoPesquisa);
        	
        }
        
        return objetoFonteCaptacao;
    }
    
    private TipoCaptacao pesquisarTipoCaptacao(String tipo){
    	
    	Collection colecaoPesquisa = null;
    	TipoCaptacao objetoTipoCaptacao = null;
    		
    	FiltroTipoCaptacao filtroTipoCaptacao = new FiltroTipoCaptacao();

    	filtroTipoCaptacao.adicionarParametro(
        	new ParametroSimples(
        		FiltroTipoCaptacao.ID, 
        		tipo));

    	filtroTipoCaptacao.adicionarParametro(
        	new ParametroSimples(
        			FiltroTipoCaptacao.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna a tipo de captacao
        colecaoPesquisa = 
        	this.getFachada().pesquisar(
        		filtroTipoCaptacao,
                TipoCaptacao.class.getName());
        
        if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
        	objetoTipoCaptacao = 
            	(TipoCaptacao) Util.retonarObjetoDeColecao(colecaoPesquisa);
        	
        }
        
        return objetoTipoCaptacao;
    }
    
	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Fernando Fontelles
	 * @date 28/10/2009
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			InserirSistemaAbastecimentoActionForm form) {

		// Fonte de Captacao
		if (form.getFonteCaptacao() != null && 
			!form.getFonteCaptacao().equals("") && 
			form.getDescricaoFonteCaptacao() != null && 
			!form.getDescricaoFonteCaptacao().equals("")) {

			httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
		}
		
		//Tipo de Captacao
		if (form.getTipoCaptacao() != null && 
			!form.getTipoCaptacao().equals("") && 
			form.getDescricaoTipoCaptacao() != null && 
			!form.getDescricaoTipoCaptacao().equals("")) {

			httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
		}
		
	}
    
    
}
