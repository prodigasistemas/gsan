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
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por inserir uma operação de uma funcionalidade específica no sistema
 * o action inseri também o relacionamento entre a operação e as tabelas 
 * 
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class InserirOperacaoAction extends GcomAction {

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

		//Cria a variável de retorno, seta para a página de sucesso 
		ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
		//Recupera o form de inserir operação
		InserirOperacaoActionForm inserirOperacaoActionForm = (InserirOperacaoActionForm) actionForm;
		
		//Recupera as informações do form 
		String descricao = inserirOperacaoActionForm.getDescricao();
		String descricaoAbreviada = inserirOperacaoActionForm.getDescricaoAbreviada();
		String caminhoURL = inserirOperacaoActionForm.getCaminhoUrl();
		String idFuncionalidade = inserirOperacaoActionForm.getIdFuncionalidade();
		Funcionalidade funcionalidade = new Funcionalidade();
		funcionalidade.setId(new Integer(idFuncionalidade));
        
		String idTipoOperacao = inserirOperacaoActionForm.getIdTipoOperacao();
		OperacaoTipo operacaoTipo = new OperacaoTipo();
		operacaoTipo.setId(new Integer(idTipoOperacao));
		
		//Recupera o argumento de pesquisa 
		//Caso tenha sido informado cria o objeto
		String idArgumentoPesquisa = inserirOperacaoActionForm.getIdArgumentoPesquisa();
		TabelaColuna argumentoPesquisa = null;
		if(idArgumentoPesquisa != null && !idArgumentoPesquisa.equalsIgnoreCase("")){
			argumentoPesquisa = new TabelaColuna();
			argumentoPesquisa.setId(new Integer(idArgumentoPesquisa));
		}
		
		//Recupera o código da operação de pesquisa 
		//Caso tenha sido informado cria o objeto
		String idOperacaoPesquisa = inserirOperacaoActionForm.getIdOperacaoPesquisa();
		Operacao operacaoPesquisa = null;
		if(idOperacaoPesquisa != null && !idOperacaoPesquisa.equalsIgnoreCase("")){
			operacaoPesquisa = new Operacao();
			operacaoPesquisa.setId(new Integer(idOperacaoPesquisa));
		}
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Recupera a  coleção de tabelas da sessão  
		Collection<Tabela> colecaoTabela = (Collection<Tabela>)sessao.getAttribute("colecaoOperacaoTabela");

		//Cria o objeto operação que vai ser inserido
		Operacao operacao = new Operacao();
		operacao.setDescricao(descricao);
		operacao.setDescricaoAbreviada(descricaoAbreviada);
		operacao.setCaminhoUrl(caminhoURL);
		operacao.setFuncionalidade(funcionalidade);
		operacao.setOperacaoTipo(operacaoTipo);
		operacao.setIdOperacaoPesquisa(operacaoPesquisa);
		operacao.setTabelaColuna(argumentoPesquisa);
		operacao.setUltimaAlteracao(new Date());
		
		//Chamao metódo de inserir operação da fachada
		fachada.inserirOperacao(operacao, colecaoTabela, usuarioLogado);
		
		//Limpa a sessão depois da inserção 
		sessao.removeAttribute("habilitarArgumentoPesquisa");
		sessao.removeAttribute("habilitarOperacaoPesquisa");
		sessao.removeAttribute("colecaoOperacaoTabela");
		sessao.removeAttribute("InserirOperacaoActionForm");
		sessao.removeAttribute("retornarTela");
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Operação inserida com sucesso.",
				"Inserir outra operação",
				"exibirInserirOperacaoAction.do?menu=sim");


		//Retorna o mapeamento contido na variável retorno 
		return retorno;
	}
}