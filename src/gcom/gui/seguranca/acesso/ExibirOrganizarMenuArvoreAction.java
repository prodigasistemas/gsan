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

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirOrganizarMenuArvoreAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirOrganizarMenuArvore");
		
		OrganizarMenuActionForm form = 
			(OrganizarMenuActionForm) actionForm;

		String ehParaSalvar = httpServletRequest.getParameter("ehParaSalvar");
		
		if(ehParaSalvar != null && !ehParaSalvar.equals("")){

			if(httpServletRequest.getParameter("idFuncionalidade") != null && 
				!httpServletRequest.getParameter("idFuncionalidade").equals("")){

				String idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidade");

				Funcionalidade funcionalidade = 
					this.pesquisarFuncionalidade(idFuncionalidade);
				
				funcionalidade.setNumeroOrdemMenu(new Long(form.getNumeroOrdemFuncionalidade()));

				this.getFachada().atualizar(funcionalidade);
				
			}else{

				String idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidadeCategoria");

				FuncionalidadeCategoria funcionalidadeCategoria = 
					this.pesquisarFuncionalidadeCategoria(idFuncionalidade);
				
				funcionalidadeCategoria.setNumeroOrdemMenu(new Long(form.getNumeroOrdemPasta()));

				this.getFachada().atualizar(funcionalidadeCategoria);
				
			}
		} else {
			
			String idFuncionalidade = null;
			String descricao = null;
			boolean ordemFuncionalidade = false;
			
			if(httpServletRequest.getParameter("idFuncionalidade") != null && 
				!httpServletRequest.getParameter("idFuncionalidade").equals("")){

				idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidade");
				
				Funcionalidade funcionalidade = 
					this.pesquisarFuncionalidade(idFuncionalidade);
				
				if(funcionalidade.getNumeroOrdemMenu() != null){
					form.setNumeroOrdemFuncionalidade(funcionalidade.getNumeroOrdemMenu().toString());	
				}
				
				descricao = funcionalidade.getDescricao();
				ordemFuncionalidade = true;
				form.setTipoOrdem("F");
				
				httpServletRequest.setAttribute("ordemFuncionalidade",ordemFuncionalidade);
				
			} else if(httpServletRequest.getParameter("idFuncionalidadeCategoria") != null && 
					!httpServletRequest.getParameter("idFuncionalidadeCategoria").equals("")){
				
				idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidadeCategoria");

				FuncionalidadeCategoria funcionalidade = 
					this.pesquisarFuncionalidadeCategoria(idFuncionalidade);
				
				if(funcionalidade.getNumeroOrdemMenu() != null){
					form.setNumeroOrdemPasta(funcionalidade.getNumeroOrdemMenu().toString());	
				}
				
				descricao = funcionalidade.getNome();
				form.setTipoOrdem("P");
				
				httpServletRequest.setAttribute("ordemFuncionalidade",ordemFuncionalidade);
			}
			
			
			httpServletRequest.setAttribute("descricaoFuncionalidade",descricao);
			httpServletRequest.setAttribute("idFuncionalidade",idFuncionalidade);
			
		}
		
		FuncionalidadeCategoria arvoreFuncionalidades = 
			this.getFachada().pesquisarArvoreFuncionalidades(new Integer(form.getModulo()));
		
		MenuGCOM menu = new MenuGCOM();
		String menuGerado = menu.gerarMenuOrganizarMenu(arvoreFuncionalidades);
		
		
		httpServletRequest.setAttribute("arvoreFuncionalidades",menuGerado);
		
		return retorno;
	}
	
	private Funcionalidade pesquisarFuncionalidade(String idFuncionalidade){
		Funcionalidade funcionalidade = null;
		
		FiltroFuncionalidade filtro = new FiltroFuncionalidade();
		filtro.adicionarParametro(
			new ParametroSimples(
				FiltroFuncionalidade.ID,idFuncionalidade));

		Collection colecaoFuncionalidade =
			this.getFachada().pesquisar(filtro,Funcionalidade.class.getName());
		
		funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
		
		return funcionalidade;
	}
	
	private FuncionalidadeCategoria pesquisarFuncionalidadeCategoria(String idFuncionalidade){
		FuncionalidadeCategoria funcionalidade = null;
		
		FiltroFuncionalidadeCategoria filtro = new FiltroFuncionalidadeCategoria();
		filtro.adicionarParametro(
			new ParametroSimples(
				FiltroFuncionalidadeCategoria.ID,idFuncionalidade));

		Collection colecaoFuncionalidade =
			this.getFachada().pesquisar(filtro,FuncionalidadeCategoria.class.getName());
		
		funcionalidade = (FuncionalidadeCategoria) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
		
		return funcionalidade;
	}
	
	
}