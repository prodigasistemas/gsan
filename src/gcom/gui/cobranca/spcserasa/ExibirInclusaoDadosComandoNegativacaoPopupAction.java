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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.DadosInclusoesComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 31/10/2007
 */


public class ExibirInclusaoDadosComandoNegativacaoPopupAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("inclusaoDadosComandoNegativacaoPopup");
			
			InclusaoDadosComandoNegativacaoPopupActionForm form = (InclusaoDadosComandoNegativacaoPopupActionForm)actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			Integer idComandoNegativacao = null;
			
			
			if (httpServletRequest.getParameter("idComandoNegativacao") != null) {
				
				idComandoNegativacao = new Integer(httpServletRequest.getParameter("idComandoNegativacao"));
				sessao.setAttribute("idComandoNegativacao",idComandoNegativacao);
			} else if(sessao.getAttribute("idComandoNegativacao") != null){
				idComandoNegativacao = (Integer)sessao.getAttribute("idComandoNegativacao");
			}
			
			
			// PAGINACAO DA TABELA
	       
        	Integer totalRegistrosSegundaPaginacao = 0;
//        	if(sessao.getAttribute("totalRegistrosSegundaPaginacao") == null ){
        		
        		totalRegistrosSegundaPaginacao = (Integer)fachada.pesquisarDadosInclusoesComandoNegativacao(idComandoNegativacao);

        		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
        		 
        		
//        	}else{
//        		totalRegistrosSegundaPaginacao = (Integer)sessao.getAttribute("totalRegistrosSegundaPaginacao");
//        	}
        	
			// 2º Passo - Chamar a função de Paginação passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistrosSegundaPaginacao, false);
			
			// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
			// da pesquisa que está no request
			Collection collectionDadosInclusoesComandoNegativacao = fachada.pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(idComandoNegativacao,
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
			//[FS0006 NENHUM REGISTRO ENCONTRADO]
			if (collectionDadosInclusoesComandoNegativacao == null || collectionDadosInclusoesComandoNegativacao.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			// SETANDO OS DADOS GERAIS DA TELA
			
			DadosInclusoesComandoNegativacaoHelper dadosInclusoesComandoNegativacaoHelper = (DadosInclusoesComandoNegativacaoHelper)
			collectionDadosInclusoesComandoNegativacao.iterator().next();
			
			if (dadosInclusoesComandoNegativacaoHelper.getNomeCliente() != null && 
					!dadosInclusoesComandoNegativacaoHelper.getNomeCliente().equals("")){
				
				form.setNegativador(dadosInclusoesComandoNegativacaoHelper.getNomeCliente());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes()!= null &&
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().equals("")){
				
				form.setQtdInclusoes(dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().toString());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito().equals("")){
				
				form.setValorTotalDebito(dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito().toString());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().equals("")){
				
				form.setQtdItensIncluidos(dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().toString());
			}
			
			
			sessao.setAttribute("collectionDadosInclusoesComandoNegativacao", collectionDadosInclusoesComandoNegativacao);
			sessao.setAttribute("totalRegistrosSegundaPaginacao", totalRegistrosSegundaPaginacao);
			sessao.setAttribute("numeroPaginasPesquisaSegundaPaginacao",httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
			
			return retorno;
		
		
	
	}
}