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
