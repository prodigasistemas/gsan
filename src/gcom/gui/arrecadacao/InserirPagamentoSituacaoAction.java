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
package gcom.gui.arrecadacao;


import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirPagamentoSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Situacao de Pagamento
	 * 
	 * [UC0767] Inserir Situação de  pagamento
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 28/04/2008
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirPagamentoSituacaoActionForm inserirPagamentoSituacaoActionForm = (InserirPagamentoSituacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirPagamentoSituacaoActionForm.getDescricao();
		String descricaoAbreviada = inserirPagamentoSituacaoActionForm
				.getDescricaoAbreviada();
		
		
		PagamentoSituacao pagamentoSituacao = new PagamentoSituacao();
		Collection colecaoPesquisa = null;

		// Descrição
		if (!"".equals(inserirPagamentoSituacaoActionForm.getDescricao())) {
			pagamentoSituacao.setDescricao(inserirPagamentoSituacaoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		// Descrição Abreviada
		if (!"".equals(inserirPagamentoSituacaoActionForm.getDescricaoAbreviada())) {
			pagamentoSituacao.setDescricaoAbreviada(inserirPagamentoSituacaoActionForm
					.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição Abreviada");
		}
		pagamentoSituacao.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		// Ultima alteração
		pagamentoSituacao.setUltimaAlteracao(new Date());

		FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();

		filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(
				FiltroPagamentoSituacao.DESCRICAO, pagamentoSituacao.getDescricao()));

		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroPagamentoSituacao, PagamentoSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.pagamentosituacao_ja_cadastrada", null,
					pagamentoSituacao.getDescricao());
			
		
		} else {
			pagamentoSituacao.setDescricao(descricao);
			pagamentoSituacao.setDescricaoAbreviada(descricaoAbreviada);

			Integer idPagamentoSituacao = (Integer) fachada
					.inserir(pagamentoSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situacao Pagamento " + descricao
							+ " inserido com sucesso.",
					"Inserir outra Situacao de Pagamento",
					"exibirInserirPagamentoSituacaoAction.do?menu=sim",
					"exibirAtualizarPagamentoSituacaoAction.do?idRegistroAtualizacao="
							+ idPagamentoSituacao,
					"Atualizar Situacao de Pagamento Inserida");

			sessao.removeAttribute("InserirPagamentoSituacaoActionForm");

			return retorno;
		}

	}
}