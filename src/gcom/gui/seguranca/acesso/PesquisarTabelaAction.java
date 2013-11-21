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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarTabelaAction extends GcomAction {
	/**
	 * Pesquisar Tabela
	 * 
	 * @author Vinicius Medeiros
	 * @date 12/05/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	   public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("pesquisarTabela");
	        
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
	        //HttpSession sessao = httpServletRequest.getSession(false);
	        
			// Obtém o action form
	        PesquisarTabelaActionForm pesquisarTabelaActionForm = (PesquisarTabelaActionForm) actionForm;

			// Recupera os parâmetros do form
	        String id = (String) pesquisarTabelaActionForm.getId();
	        String descricao = (String) pesquisarTabelaActionForm.getDescricao();
	        String nomeTabela = (String) pesquisarTabelaActionForm.getNomeTabela();	  
	        String tipoPesquisa = (String) pesquisarTabelaActionForm.getTipoPesquisa();

	        boolean peloMenosUmParametroInformado = false;

	         FiltroTabela filtroTabela = new FiltroTabela(FiltroTabela.DESCRICAO);	        

	        if (id != null && !id.trim().equalsIgnoreCase("")) {
	        	filtroTabela.adicionarParametro(new ParametroSimples(
	        			FiltroTabela.ID, new Integer(id)));
                peloMenosUmParametroInformado = true;
	        }
	        
	        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true; 
    			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
    				filtroTabela.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroTabela.DESCRICAO, descricao));
    			} else {
    				filtroTabela.adicionarParametro(new ComparacaoTexto(
    						FiltroTabela.DESCRICAO, descricao));
    			}
	        }
	        
	        if (nomeTabela != null && !nomeTabela.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            filtroTabela.adicionarParametro(new ComparacaoTextoCompleto(
	            		FiltroTabela.NOME, nomeTabela));
	        }	      
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
	        }
	        
			// Faz a pesquisa baseada no filtro
	        Collection tabelas = fachada.pesquisar(filtroTabela, Tabela.class.getName());
	        
			// Verificar se a pesquisa de atividade não está vazia
	        if (tabelas != null && !tabelas.isEmpty()) {
                 // Aciona o controle de paginação para que sejam pesquisados apenas
				// os registros que aparecem na página
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroTabela, Tabela.class.getName());
				tabelas = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				//sessao.setAttribute("atividades", atividades);
				// Manda a coleção das Atividade pesquisadas para o request
				httpServletRequest.getSession(false).setAttribute("tabelas",
						tabelas);
				
	        } else {
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "tabela");
	        }
	        
	        return retorno;
	    }

	}
