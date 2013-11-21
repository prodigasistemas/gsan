/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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
