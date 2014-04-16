package gcom.gui.cadastro.localidade;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirSetorComercialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("exibirInserirSetorComercial");

        PesquisarAtualizarSetorComercialActionForm form = 
        	(PesquisarAtualizarSetorComercialActionForm) actionForm;

        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
        String acao = (String) httpServletRequest.getParameter("acao");
        

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.setIndicadorSetorAlternativo( "" + ConstantesSistema.INDICADOR_USO_DESATIVO ) ;
		}
        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

            Collection colecaoPesquisa = null;

            switch (Integer.parseInt(objetoConsulta)) {

            //Localidade
            case 1:
                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                //Recebe o valor do campo localidadeID do formulário.
                String localidadeID = form.getLocalidadeID();

                filtroLocalidade.adicionarParametro(
                	new ParametroSimples(
                		FiltroLocalidade.ID, 
                		localidadeID));

                filtroLocalidade.adicionarParametro(
                	new ParametroSimples(
                        FiltroLocalidade.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna localidade
                colecaoPesquisa = 
                	this.getFachada().pesquisar(
                		filtroLocalidade,
                        Localidade.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

                	//Localidade nao encontrada
                    //Limpa os campos localidadeID e localidadeNome do
                    // formulário
                    httpServletRequest.setAttribute("corLocalidade","exception");
                    form.setLocalidadeID("");
                    form.setLocalidadeNome("Localidade inexistente");
                    
                    httpServletRequest.setAttribute("nomeCampo","localidadeID");
                } else {
                    Localidade objetoLocalidade = 
                    	(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    
                    form.setLocalidadeID(String.valueOf(objetoLocalidade.getId()));
                    form.setLocalidadeNome(objetoLocalidade.getDescricao());
                    
                    httpServletRequest.setAttribute("corLocalidade", "valor");
                    
                    int codigoSetorComercial = 
                    	this.getFachada().pesquisarMaximoCodigoSetorComercial(objetoLocalidade.getId());
                    
                    codigoSetorComercial = codigoSetorComercial + 1;
                    
                    form.setSetorComercialCD("" + codigoSetorComercial);
                    
                    httpServletRequest.setAttribute("nomeCampo","setorComercialCD");
                }

                break;

            //Municipio
            case 2:
                
            	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

                //Recebe o valor do campo municipioID do formulário.
                String municipioID = form.getMunicipioID();

                filtroMunicipio.adicionarParametro(
                	new ParametroSimples(
                        FiltroMunicipio.ID, 
                        municipioID));

                filtroMunicipio.adicionarParametro(
                	new ParametroSimples(
                        FiltroMunicipio.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna municipio
                colecaoPesquisa = 
                	this.getFachada().pesquisar(
                		filtroMunicipio,
                        Municipio.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

                	//Municipio nao encontrado
                    //Limpa os campos municipioID e municipioNome do formulário
                    httpServletRequest.setAttribute("corMunicipio", "exception");
                    form.setMunicipioID("");
                    form.setMunicipioNome("Município inexistente.");
                    
                    httpServletRequest.setAttribute("nomeCampo","municipioID");
                } else {
                    Municipio objetoMunicipio = 
                    	(Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    
                    form.setMunicipioID(String.valueOf(objetoMunicipio.getId()));
                    form.setMunicipioNome(objetoMunicipio.getNome());
                    
                    httpServletRequest.setAttribute("corMunicipio", "valor");
                    httpServletRequest.setAttribute("nomeCampo","botaoInserir");
                }

                break;
                
            case 3:
            	
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
        } else if(acao != null && !acao.trim().equalsIgnoreCase("") && acao.equals("A")){
        	
        	this.montarColecaoFonte(httpServletRequest,form.getFonteCaptacao());

        	form.setFonteCaptacao("");
            form.setDescricaoFonteCaptacao("");
        
        } else if(acao != null && !acao.trim().equalsIgnoreCase("") && acao.equals("R")){
        	
        	String idRemover = (String) httpServletRequest.getParameter("idRemover");
        	
        	this.removerColecaoFonte(httpServletRequest,idRemover);

        } else {

            //Limpando o formulário
            form.setLocalidadeID("");
            form.setLocalidadeNome("");
            form.setMunicipioID("");
            form.setMunicipioNome("");
            form.setSetorComercialCD("");
            form.setSetorComercialNome("");
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
    
    private void montarColecaoFonte(HttpServletRequest httpServletRequest,String fonte){
    	
    	Collection colecaoFonte = (Collection) 
			this.getSessao(httpServletRequest).getAttribute("colecaoFonteCaptacao");
    	
    	if(colecaoFonte != null && !colecaoFonte.isEmpty()){
    		
    		Iterator itera = colecaoFonte.iterator();
    		boolean jaExisteFonte = false;
    		while (itera.hasNext()) {
				FonteCaptacao element = (FonteCaptacao) itera.next();
				
				if(fonte.equals(""+element.getId())){
					jaExisteFonte = true;
					break;
				}
			}
    		
    		if(!jaExisteFonte){
            	
    			FonteCaptacao objetoFonteCaptacao = 
            		this.pesquisarFonteCaptacao(fonte);

    			colecaoFonte.add(objetoFonteCaptacao);
    		}
    	}else{
    		colecaoFonte = new ArrayList();

    		FonteCaptacao objetoFonteCaptacao = 
        		this.pesquisarFonteCaptacao(fonte);
    		
    		colecaoFonte.add(objetoFonteCaptacao);
    		
    		this.getSessao(httpServletRequest).setAttribute("colecaoFonteCaptacao",colecaoFonte);
    	}

    }
    
    private void removerColecaoFonte(HttpServletRequest httpServletRequest,String fonte){
    	
    	Collection colecaoFonte = (Collection) 
			this.getSessao(httpServletRequest).getAttribute("colecaoFonteCaptacao");
    	
   		Iterator itera = colecaoFonte.iterator();

   		while (itera.hasNext()) {
			FonteCaptacao element = (FonteCaptacao) itera.next();
				
			if(fonte.equals(""+element.getId())){
				itera.remove();
				break;
			}
		}
    }
    
	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Rafael Pinto
	 * @date 16/10/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarAtualizarSetorComercialActionForm form) {

		// Fonte de Captacao
		if (form.getFonteCaptacao() != null && 
			!form.getFonteCaptacao().equals("") && 
			form.getDescricaoFonteCaptacao() != null && 
			!form.getDescricaoFonteCaptacao().equals("")) {

			httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
		}
	}
    
    
}
