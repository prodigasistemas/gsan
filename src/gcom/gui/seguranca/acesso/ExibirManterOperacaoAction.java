/**
 * 
 */
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

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável pela exibição da página de manter operação 
 *
 * @author Pedro Alexandre
 * @date 01/08/2006
 */
public class ExibirManterOperacaoAction extends GcomAction {

	
	/**
	 * [UC0281] - Manter Operação 
	 *
	 * @author Pedro Alexandre
	 * @date 05/08/2006
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

		//Seta o mapeamento de retorno para a tela de manter operação
        ActionForward retorno = actionMapping.findForward("exibirManterOperacao");
        
        //Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.setAttribute("AtualizarOperacaoActionForm",null);
		
		//Cria a variável que vai armazenar a coleção de operações filtradas
        Collection colecaoOperacao = null;

        //Recupera o filtro da operação caso tenha na sessão
        FiltroOperacao filtroOperacao = null;
		if (sessao.getAttribute("filtroOperacao") != null) {
			filtroOperacao = (FiltroOperacao) sessao.getAttribute("filtroOperacao");
		}
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);
		
		/*
		 * Pesquisa a coleção de operações para o esquema de paginação
		 * e recupera o mapeamnento de retorno 
		 */
		Map resultado = controlarPaginacao(httpServletRequest, retorno,	filtroOperacao, Operacao.class.getName());
		colecaoOperacao = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
			
		/*
		 * Caso a coleção de pesquisa esteja vazia 
		 * levanta a exceção para o usuário indicando que a pesquisa 
		 * não retornou nenhum registro
		 */
		if (colecaoOperacao == null || colecaoOperacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		//Recupera a flag que indica que se o usuário quer ir direto para a tela do atualizar
		String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
		/*
		 * Caso a cloeção de pesquisa tenha um único registro e a a
		 * flag do atualizar esteja marcada, recupera a operação 
		 * da coleção e seta o código da operação para ser atualizada 
		 * na sessão. 
		 * Caso contrário manda a coleção de operações para a 
		 * página do manter.
		 */
		if (colecaoOperacao.size()== 1 && identificadorAtualizar != null){
			retorno = actionMapping.findForward("atualizarOperacao");
			Operacao operacao = (Operacao)colecaoOperacao.iterator().next();
			sessao.setAttribute("idRegistroAtualizar", new Integer (operacao.getId()).toString());
		}else{
			sessao.setAttribute("colecaoOperacao", colecaoOperacao);
		}

		//Seta o tipo da pesquisa na sessão
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		//Retorna o mapeamento contido na variável retorno 
        return retorno;
    }
}
