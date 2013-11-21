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
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Modulo;
import gcom.util.ConstantesSistema;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 25/04/2006
 */
public class InserirFuncionalidadeAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma nova Funcionalidade
	 * 
	 * [UC0280] Inserir Funcionalidade
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 25/04/2006
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

		InserirFuncionalidadeActionForm inserirFuncionalidadeActionForm = (InserirFuncionalidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtendo dados do form e setando no Objeto Funcionalidade

		Funcionalidade funcionalidade = new Funcionalidade();

		String idModulo = inserirFuncionalidadeActionForm.getModulo();

		funcionalidade.setDescricao(inserirFuncionalidadeActionForm
				.getDescricao());

		funcionalidade.setDescricaoAbreviada(inserirFuncionalidadeActionForm
				.getDescricaoAbreviada());

		funcionalidade.setCaminhoMenu(inserirFuncionalidadeActionForm
				.getCaminhoMenu());

		funcionalidade.setCaminhoUrl(inserirFuncionalidadeActionForm
				.getCaminhoUrl());

		funcionalidade.setIndicadorPontoEntrada(new Short(
				inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()));

		funcionalidade.setNumeroOrdemMenu(new Long(
				inserirFuncionalidadeActionForm.getNumeroOrdemMenu()));

		if (idModulo != null
				&& !idModulo.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Modulo modulo = new Modulo();

			modulo.setId(new Integer(idModulo));

			funcionalidade.setModulo(modulo);

		}

		funcionalidade.setIndicadorNovaJanela(new Short(
				inserirFuncionalidadeActionForm.getIndicadorNovaJanela()));

		funcionalidade.setIndicadorOlap(new Short(
				inserirFuncionalidadeActionForm.getIndicadorOlap()));

		if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada() != null
				&& inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()
						.equalsIgnoreCase(
								ConstantesSistema.INDICADOR_USO_ATIVO
										.toString())
				&& inserirFuncionalidadeActionForm
						.getIdFuncionalidadeCategoria() != null
				&& !inserirFuncionalidadeActionForm
						.getIdFuncionalidadeCategoria().equalsIgnoreCase("")) {

			FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();
			filtroFuncionalidadeCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeCategoria.ID,
							inserirFuncionalidadeActionForm
									.getIdFuncionalidadeCategoria()));

			Collection colecaoFuncionalidadeCategoria = fachada.pesquisar(
					filtroFuncionalidadeCategoria,
					FuncionalidadeCategoria.class.getName());

			if (colecaoFuncionalidadeCategoria != null
					&& !colecaoFuncionalidadeCategoria.isEmpty()) {
				FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeCategoria);
				funcionalidade
						.setFuncionalidadeCategoria(funcionalidadeCategoria);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Funcionalidade Categoria");
			}

		}

		Collection<Funcionalidade> colecaoFuncionalidade = null;

		if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
			colecaoFuncionalidade = (Collection) sessao
					.getAttribute("colecaoFuncionalidadeTela");
		}

		Integer idFuncionalidade = (Integer) fachada.inserirFuncionalidade(
				funcionalidade, colecaoFuncionalidade);

		sessao.removeAttribute("colecaoFuncionalidadeTela");

		montarPaginaSucesso(httpServletRequest, "Funcionalidade "
				+ idFuncionalidade + " inserida com sucesso.",
				"Inserir outra Funcionalidade",
				"exibirInserirFuncionalidadeAction.do?menu=sim",
				"exibirAtualizarFuncionalidadeAction.do?idFuncionalidade="
						+ idFuncionalidade, "Atualizar Funcionalidade inserida");

		return retorno;
	}
}