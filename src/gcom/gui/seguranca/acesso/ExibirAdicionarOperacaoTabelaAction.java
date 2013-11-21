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
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável por exibir o popup de adicionar tabela no inserir operação e atualizar operação
 *
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class ExibirAdicionarOperacaoTabelaAction extends GcomAction {

	/**
	 * Inseri uma operação de uma funcionalida de no sistema
	 *
	 * [UC0284] Inserir Operação
	 *
	 * @author Pedro Alexandre
	 * @date 05/05/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {
		
		//Seta o mapeamento para a o popup de adicionar tabela
		ActionForward retorno = actionMapping.findForward("exibirAdicionarOperacaoTabela");
		
		//Recupera o form de adicionar tabela 
		AdicionarOperacaoTabelaActionForm adicionarOperacaoTabelaActionForm = (AdicionarOperacaoTabelaActionForm) actionForm;
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o códigoda tabela se ele foi digitado
		String idTabela = adicionarOperacaoTabelaActionForm.getIdTabela();
		
		//Recupera para onde a tela de popup deve retornar
		//e seta o valor na sessão
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		if(retornarTela != null){
			sessao.setAttribute("retornarTela",retornarTela);
		}
		
		//Recupera a flag que indica se é para limpar o form de adicionar tabela
		String limpaForm = httpServletRequest.getParameter("limpaForm");
		
		if (idTabela != null
				&& !idTabela.trim().equalsIgnoreCase("")
				&& httpServletRequest.getParameter("exibirPesquisarTabela") == null
				&& httpServletRequest.getParameter("limparForm") == null) {

			sessao.setAttribute("tabelaRecebida", idTabela);
		}
		
		//Caso a flag de limpar o form seja true 
		//Limpaos dados dos campos da tabela
		if(limpaForm != null && limpaForm.equalsIgnoreCase("true")){
			adicionarOperacaoTabelaActionForm.setIdTabela("");
			adicionarOperacaoTabelaActionForm.setDescricaoTabela("");
		}else{
			//Caso a flag de limpar o form não for true
			//e caso o código da tabela tenha sido digitado 
			//pesquisa a tabela informada na base de dados
			if (idTabela != null && !idTabela.equals("")){
				
				//Cria o filtro e setao código da tabela informado no filtro
				FiltroTabela filtroTabela = new FiltroTabela();
				filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.ID,idTabela));
				
				//Pesquisa a tabela na base de dados
				Collection<Tabela> colecaoTabela = fachada.pesquisar(filtroTabela, Tabela.class.getName());
				
				//Caso a tabela tenha sido encontrada 
				//Recupera a tabela e seta as informações no form de adicionar
				//Caso contrário indica que a tabela não existe 
				if (colecaoTabela != null && !colecaoTabela.isEmpty()) {
					Tabela tabela = (Tabela) Util.retonarObjetoDeColecao(colecaoTabela);
					adicionarOperacaoTabelaActionForm.setIdTabela(String.valueOf(tabela.getId()));
					adicionarOperacaoTabelaActionForm.setDescricaoTabela(tabela.getDescricao());
					httpServletRequest.setAttribute("operacaoTabelaEncontrada", "true");
	
				} else {
					adicionarOperacaoTabelaActionForm.setIdTabela("");
					adicionarOperacaoTabelaActionForm.setDescricaoTabela("TABELA INEXISTENTE");
					httpServletRequest.setAttribute("operacaoTabelaNaoEncontrada","exception");
				}
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			adicionarOperacaoTabelaActionForm.setIdTabela(id);
			adicionarOperacaoTabelaActionForm.setDescricaoTabela(descricao);

		}
		
		//Seta a flag para indicar que o popupvai ser fechado
		httpServletRequest.setAttribute("fechaPopup", "false");

		//Retorna o mapeamento contido na variável retorno 
		return retorno;

	}

	
}
