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
package gcom.gui.micromedicao.hidrometro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltrarHidrometroHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.util.Util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 8 de Setembro de 2005
 */
public class ExibirManterHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("manterHidrometro");

        Collection hidrometros = null;

        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        
       if (httpServletRequest.getParameter("limpaSessao") != null && 
    	!httpServletRequest.getParameter("limpaSessao").equals("")) {

            sessao.removeAttribute("faixaInicial");
            sessao.removeAttribute("faixaFinal");
            sessao.removeAttribute("fixo");
        }
        
        if (sessao.getAttribute("i") != null){
			sessao.removeAttribute("i");
		}
        
        String atualizar = httpServletRequest.getParameter("atualizar");
        
        //Parte da verificação do filtro
        FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
        
        //Verifica se o filtro foi informado pela página de filtragem de
        // hidrometro
        if (sessao.getAttribute("filtroHidrometro") != null && 
        	sessao.getAttribute("voltarFiltrar") != null ) {
        	
        	filtroHidrometro = (FiltroHidrometro) sessao.getAttribute("filtroHidrometro");
        	sessao.removeAttribute("voltarFiltrar");
        	
        } else {
            if (sessao.getAttribute("fixo") == null && sessao.getAttribute("instalado") == null) {

            	//Caso o exibirManterHidrometro não tenha passado por algum esquema
                // de filtro, a quantidade de registros é verificada para avaliar a
                // necessidade de filtragem
                filtroHidrometro = new FiltroHidrometro();

                //Se o limite de registros foi atingido, a página de
                // filtragem é chamada
                retorno = actionMapping.findForward("filtrarHidrometro");
            }else if (httpServletRequest.getParameter("voltarFiltro") != null){
            	retorno = actionMapping.findForward("filtrarHidrometro");
            }
        }

        //A pesquisa de hidrometros só será feita se o forward estiver
        // direcionado para a página de manterHidrometro
        if (retorno.getName().equalsIgnoreCase("manterHidrometro")) {

        	//caso venha do FiltrarHidrometro e foi escolhido como parametros o
            // fixo e a faixa da numeração do hidrometro então faz outra pesquisa sem o filtro.
            //Como é obrigatório a faixa caso coloque como parametro o fixo ,
            // então não é necessario fazer a verificação da faixa só do fixo.
            if (sessao.getAttribute("fixo") != null && 
            	!sessao.getAttribute("fixo").equals("")) {
                
            	String fixo = (String) sessao.getAttribute("fixo");
                String faixaInicial = (String) sessao.getAttribute("faixaInicial");
                String faixaFinal = (String) sessao.getAttribute("faixaFinal");
                
                // 1º Passo - Pegar o total de registros através de um count da consulta
        		// que aparecerá na tela
                String numeroFormatadoInicial = "";
    			String numeroFormatadoFinal = "";

    			numeroFormatadoInicial = Util.adicionarZerosEsquedaNumero(6,faixaInicial);
    			numeroFormatadoFinal = Util.adicionarZerosEsquedaNumero(6,faixaFinal);

                Integer totalRegistros = 
                	this.getFachada().pesquisarNumeroHidrometroFaixaCount(fixo,
                		fixo + numeroFormatadoInicial, 
                		fixo + numeroFormatadoFinal);
                
                // 2º Passo - Chamar a função de Paginação passando o total de registros
        		retorno = 
        			this.controlarPaginacao(httpServletRequest, retorno,totalRegistros);
                
        		hidrometros = 
        			this.getFachada().pesquisarNumeroHidrometroFaixaPaginacao(fixo,
        				faixaInicial, 
        				faixaFinal, 
        				((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
        		
        		if (hidrometros == null || hidrometros.isEmpty()) {

                    //Nenhum hidrometro cadastrado
        			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

                }
//        		else
//                {
//                	hidrometros2 = fachada.pesquisarNumeroHidrometroFaixa(fixo,
//                            faixaInicial, faixaFinal);
//                }

                //seta um valor no form para que o botão
                // atualizarConjuntoHidrometro possa atualizar um
                //conjunto de hidrometro
                //hidrometroActionForm.setConjuntoHidrometro("1");
                httpServletRequest.setAttribute("conjuntoHidrometro",new Boolean(true));
                
            }else if(sessao.getAttribute("instalado") != null){
            	
            	FiltrarHidrometroHelper helper = (FiltrarHidrometroHelper) sessao.getAttribute("helper");
            
            	Integer totalRegistros = 
            		this.getFachada().pesquisarNumeroHidrometroSituacaoInstaladoPaginacaoCount(helper);
                
                // 2º Passo - Chamar a função de Paginação passando o total de registros
        		retorno = 
        			this.controlarPaginacao(httpServletRequest, retorno,totalRegistros);
                
        		hidrometros = 
        			this.getFachada().pesquisarNumeroHidrometroSituacaoInstaladoPaginacao(helper,
        				((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
        		
        		if (hidrometros == null || hidrometros.isEmpty()) {
                    //Nenhum hidrometro cadastrado
        			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
                }
            	
            }else {
                //Seta a ordenação desejada do filtro
            	filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
            	filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
            	filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
                filtroHidrometro.setCampoOrderBy(FiltroHidrometro.NUMERO_HIDROMETRO);

                // Aciona o controle de paginação para que sejam pesquisados apenas
    			// os registros que aparecem na página
    			Map resultado = 
    				controlarPaginacao(httpServletRequest, 
    					retorno,
    					filtroHidrometro, 
    					Hidrometro.class.getName());
    			
    			hidrometros = (Collection) resultado.get("colecaoRetorno");
    			retorno = (ActionForward) resultado.get("destinoActionForward");

                if (hidrometros == null || hidrometros.isEmpty()) {
                    throw new ActionServletException("atencao.pesquisa.nenhumresultado");
                } else if (atualizar != null && hidrometros.size() == 1) {
    	        	
                	Hidrometro hidrometro = (Hidrometro) hidrometros.iterator().next();
    	        	httpServletRequest.setAttribute("idRegistroAtualizacao",hidrometro.getId());
    	        	
    	        	retorno = actionMapping.findForward("exibirAtualizarHidrometro");
    	        } 
            }


//            if(hidrometros2 != null && !hidrometros2.isEmpty()){
//            	sessao.setAttribute("hidrometros2", hidrometros2);
//            }

            sessao.setAttribute("hidrometros", hidrometros);
            sessao.setAttribute("parametroInformado", "sim");
        }
        
        httpServletRequest.setAttribute("nomeCampo","numeroHidrometro");
        
        
        return retorno;
    }
}
