package gcom.gui.cadastro.localidade;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarSetorComercialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("exibirAtualizarSetorComercial");

        //Obtém a sessão
        HttpSession sessao = this.getSessao(httpServletRequest);

        PesquisarAtualizarSetorComercialActionForm form = 
        	(PesquisarAtualizarSetorComercialActionForm) actionForm;

        String setorComercialID = (String) httpServletRequest.getParameter("setorComercialID");
        
        if (setorComercialID == null){
        	if (httpServletRequest.getAttribute("setorComercialID") != null){
        		setorComercialID = (String) httpServletRequest.getAttribute("setorComercialID").toString();
        	}
	    }
        
        //PERMISSÃO PARA BLOQUEIO ALTERAÇÃO DE IMÓVEIS
        boolean permissaoEspecialBloqueio = this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.BLOQUEAR_ALTERACAO_IMOVEIS,(Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO));
        
        if (permissaoEspecialBloqueio){
       	 	httpServletRequest.setAttribute("pemissaoIndicadorBloqueio", SetorComercial.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue());
        }else{
        	httpServletRequest.setAttribute("pemissaoIndicadorBloqueio", SetorComercial.BLOQUEIO_INSERIR_IMOVEL_NAO.intValue());
        	if (form != null){
        		if (form.getIndicadorBloqueio() != null 
        				&& form.getIndicadorBloqueio().equals("1")){
        			httpServletRequest.setAttribute("bloqueio", true);
        		}else{
        			httpServletRequest.setAttribute("bloqueio", false);
        		}
        	}
        }
        
        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
        String acao = (String) httpServletRequest.getParameter("acao");
        
        Collection colecaoPesquisa = null;

        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

            String localidadeID = null;

            switch (Integer.parseInt(objetoConsulta)) {
            
            //Localidade
            case 1:
                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                //Recebe o valor do campo localidadeID do formulário.
                localidadeID = form.getLocalidadeID();

                filtroLocalidade.adicionarParametro(
                	new ParametroSimples(
                        FiltroLocalidade.ID, localidadeID));

                filtroLocalidade.adicionarParametro(
                	new ParametroSimples(
                		FiltroLocalidade.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna localidade
                colecaoPesquisa = 
                	this.getFachada().pesquisar(filtroLocalidade,
                        Localidade.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

                	//Localidade nao encontrada
                    //Limpa os campos localidadeID e localidadeNome do
                    // formulário
                    httpServletRequest.setAttribute("corLocalidade","exception");
                    form.setLocalidadeID("");
                    form.setLocalidadeNome("Localidade inexistente.");
                } else {
                    httpServletRequest.setAttribute("corLocalidade", "valor");
                    Localidade objetoLocalidade = 
                    	(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    
                    form.setLocalidadeID(String.valueOf(objetoLocalidade.getId()));
                    form.setLocalidadeNome(objetoLocalidade.getDescricao());
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
                	this.getFachada().pesquisar(filtroMunicipio,
                        Municipio.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

                	//Municipio nao encontrado
                    //Limpa os campos municipioID e municipioNome do formulário
                    httpServletRequest.setAttribute("corMunicipio", "exception");
                    form.setMunicipioID("");
                    form.setMunicipioNome("Município inexistente.");
                } else {
                    httpServletRequest.setAttribute("corMunicipio", "valor");
                    
                    Municipio objetoMunicipio = 
                    	(Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    form.setMunicipioID(String.valueOf(objetoMunicipio.getId()));
                    form.setMunicipioNome(objetoMunicipio.getNome());
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
            if (setorComercialID == null || setorComercialID.equalsIgnoreCase("")) {
                //ID do setor comercial não informado
                throw new ActionServletException("atencao.codigo_setor_comercial_nao_informado");
            } else {

                FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
                
                //Objetos que serão retornados pelo hibernate
                filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
                filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("municipio");

                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.ID, setorComercialID));

                //Retorna setor comercial
                colecaoPesquisa = 
                	this.getFachada().pesquisar(filtroSetorComercial,
                        SetorComercial.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Setor comercial não cadastrado
                    throw new ActionServletException("atencao.processo.setorComercialNaoCadastrada");
                } else {
                    SetorComercial setorComercial = (SetorComercial) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    
                    //Colocando o objeto na sessão
                    sessao.setAttribute("setorComercialManter", setorComercial);

                    form.setSetorComercialID(setorComercialID);
                    form.setLocalidadeID(String.valueOf(setorComercial.getLocalidade().getId()));
                    form.setLocalidadeNome(setorComercial.getLocalidade().getDescricao());
                    form.setSetorComercialCD(String.valueOf(setorComercial.getCodigo()));
                    form.setSetorComercialNome(setorComercial.getDescricao());
                    form.setMunicipioID(String.valueOf(setorComercial.getMunicipio().getId()));
                    form.setMunicipioNome(setorComercial.getMunicipio().getNome());
                    form.setIndicadorUso(String.valueOf(setorComercial.getIndicadorUso()));
                   
                    form.setIndicadorBloqueio(String.valueOf(setorComercial.getIndicadorBloqueio()));
                    if (setorComercial.getIndicadorBloqueio().intValue() == SetorComercial.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue()){
        				httpServletRequest.setAttribute("bloqueio", true);
        			}else{
        				httpServletRequest.setAttribute("bloqueio", false);
        			}
                    
                    form.setIndicadorSetorAlternativo(String.valueOf(setorComercial.getIndicadorSetorAlternativo()));
                    
    				Collection colecaoSetor = new ArrayList();
    				colecaoSetor.add(setorComercial);
    				
    				Collection colecaoFonteCaptacao = 
    					this.getFachada().pesquisarFonteCaptacao(colecaoSetor);
    				
    				if(colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty()){
    					this.getSessao(httpServletRequest).setAttribute("colecaoFonteCaptacao",colecaoFonteCaptacao);
    				}else{
    					this.getSessao(httpServletRequest).removeAttribute("colecaoFonteCaptacao");
    				}

                }
            }
        }
        
        if (httpServletRequest.getAttribute("voltar") == null){
        	if(sessao.getAttribute("manter") == null)
        	{
        		httpServletRequest.setAttribute("voltar", "filtrarNovo");
        		sessao.setAttribute("indicadorAtualizar","1");
        	}else
        	{
        		httpServletRequest.setAttribute("voltar", "manter");
        	}
        }
        
        httpServletRequest.setAttribute("setorComercialID", setorComercialID);
        
        this.setaRequest(httpServletRequest,form);
        
        //devolve o mapeamento de retorno
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
