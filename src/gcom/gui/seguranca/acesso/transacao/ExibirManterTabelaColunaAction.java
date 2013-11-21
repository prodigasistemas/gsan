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
package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.FiltroOperacaoTabela;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com as resoluções de diretoria retornadas do
 * FiltrarManterTipoRetornoOrdemServicoReferidaAction ou ir para o
 * ExibirManterTipoRetornoOrdemServicoReferidaAction
 * 
 * @author Thiago Tenório
 * @since 31/10/2006
 */
public class ExibirManterTabelaColunaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterTabelaColuna");

		FiltrarTabelaColunaActionForm filtrarForm = (FiltrarTabelaColunaActionForm) actionForm;
		
		String idTabela = filtrarForm.getIdTabela();
		
		String[] operacoes = filtrarForm.getOperacoes();
		
		Vector<Tabela> tabelas = new Vector<Tabela>();
		
		if (operacoes != null && operacoes.length > 0){
			FiltroOperacaoTabela filtro = new FiltroOperacaoTabela();
			for (int i = 0; i < operacoes.length; i++) {
				filtro.adicionarParametro(new ParametroSimples(
						FiltroOperacaoTabela.OPERACAO_ID, operacoes[i]));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoTabela.TABELA_COLUNAS);
				Collection colOperTabelas = Fachada.getInstancia().pesquisar(filtro,
						OperacaoTabela.class.getSimpleName());
				
				Iterator iterOperTab = colOperTabelas.iterator();
				while (iterOperTab.hasNext()) {
					OperacaoTabela operTabela = (OperacaoTabela) iterOperTab.next();
					tabelas.add(operTabela.getTabela());										
				}				
			}			
		} else if (idTabela != null && !idTabela.equals("")){
			FiltroTabela filtro = new FiltroTabela();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroTabela.ID, idTabela));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroTabela.TABELA_COLUNAS);
		
			Collection colTabelas = Fachada.getInstancia().pesquisar(filtro,
					Tabela.class.getSimpleName());
		
			Tabela tabela =  (Tabela) Util.retonarObjetoDeColecao(colTabelas);
			
			if (tabela == null){
				throw new ActionServletException("atencao.tabela.inexistente");
			}
			
			// A partir do nome da tabela, precisa-se buscar os atributos da classe
			// desta tabela que estao definidos para ser registrados
			String nomeClasse = HibernateUtil.getClassName(tabela.getNomeTabela());
			
			String nomesColunaParaPesquisa = "";
			try {
				Class classe = Class.forName(nomeClasse);
				Object instancia = classe.newInstance();
				if (instancia instanceof ObjetoTransacao){
					ObjetoTransacao objTransacao = (ObjetoTransacao) instancia;
					String[] atributosSelecionados = objTransacao.retornarAtributosSelecionadosRegistro();
					for (int i = 0; i < atributosSelecionados.length; i++) {
						String nomeColuna = HibernateUtil.getNameColumn(classe, atributosSelecionados[i]);
						nomesColunaParaPesquisa += "$" + nomeColuna + "$";
					}					
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HttpSession sessao = httpServletRequest.getSession(false);
			sessao.setAttribute("tabela", tabela);
			sessao.setAttribute("nomesColunasSelecionadas", nomesColunaParaPesquisa);	
			
			tabelas.add(tabela);

			// consultar operacao_tabela das tabelas selecionadas			
			FiltroOperacaoTabela filtroOperTab = new FiltroOperacaoTabela();
			filtroOperTab.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, idTabela));
			filtroOperTab.adicionarCaminhoParaCarregamentoEntidade(
					FiltroOperacaoTabela.OPERACAO);
			filtroOperTab.setCampoOrderBy(new String[]{FiltroOperacaoTabela.OPERACAO_ARGUMENTO_ID,
				FiltroOperacaoTabela.OPERACAO_DESCRICAO});

			Collection colOperTabelas = Fachada.getInstancia().pesquisar(filtroOperTab,
					OperacaoTabela.class.getSimpleName());
			
			httpServletRequest.setAttribute("colecaoOperTabela", colOperTabelas);	
			
		}		

		httpServletRequest.setAttribute("colecaoTabela", tabelas);

		return retorno;

	}

}
