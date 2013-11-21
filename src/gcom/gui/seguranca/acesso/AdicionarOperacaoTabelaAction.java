
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

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class AdicionarOperacaoTabelaAction extends GcomAction{
	
	
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		//Seta o mapeamento para a página de adicionar tabela 
		ActionForward retorno = actionMapping.findForward("adicionarOperacaoTabela");
	
		//Recupera o form de adicionar tabela 
		AdicionarOperacaoTabelaActionForm adicionarOperacaoTabelaActionForm = (AdicionarOperacaoTabelaActionForm) actionForm;
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o código da tabela 
		String idTabela = adicionarOperacaoTabelaActionForm.getIdTabela();
		
		//Cria a variável que vai armazenar a tabela 
		Tabela tabela = null;
		
		//Cria o filtro para pesquisar a tabela e seta o código da tabela informada no filtro
		FiltroTabela filtroTabela = new FiltroTabela();
		filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.ID, idTabela));
		
		//Pesquisa a tabela de acordo com o código informado
		Collection colecaoTabela = fachada.pesquisar(filtroTabela,Tabela.class.getName());
		
		//Caso a tabela não esteja cadastrada levanta uma exceção para o usuário
		//caso contrário recupera a tabela pesquisada 
		if(colecaoTabela==null || colecaoTabela.isEmpty()){
			throw new ActionServletException("atencao.tabela.inexistente");
		}
		tabela = (Tabela) Util.retonarObjetoDeColecao(colecaoTabela);
		
		//Cria a variável que vai armazenar as tabelas adicionadas
 		Collection<Tabela> colecaoOperacaoTabela = null;
		
 		//Caso já exista a coleção na sessão recupera a coleção
 		//caso contrário cria uma instância nova  
		if (sessao.getAttribute("colecaoOperacaoTabela") != null) {
			colecaoOperacaoTabela = (Collection<Tabela>) sessao.getAttribute("colecaoOperacaoTabela");
        } else {
        	colecaoOperacaoTabela = new ArrayList();
        }
		
		//Caso a coleção não contenha ainda a tabela informada
		//adiciona a tabela a coleção 
		//caso contrário levanta uma exceção para o usuário
		if(!colecaoOperacaoTabela.contains(tabela)){
			colecaoOperacaoTabela.add(tabela);
		}else{
			throw new ActionServletException("atencao.tabela.ja.informada");
		}
		
		//Seta a coleção de tabelas na sessão 
		sessao.setAttribute("colecaoOperacaoTabela",colecaoOperacaoTabela);
		
		//Seta a flag que indica para fechar o popup
		httpServletRequest.setAttribute("fechaPopup", "true");
		
		//Retorna o mapeamento contido na variável retorno 
		return retorno;
	}
}
