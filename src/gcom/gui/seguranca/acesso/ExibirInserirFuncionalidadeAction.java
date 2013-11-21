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
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Modulo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
public class ExibirInserirFuncionalidadeAction extends GcomAction {

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

		ActionForward retorno = actionMapping
				.findForward("funcionalidadeInserir");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirFuncionalidadeActionForm inserirFuncionalidadeActionForm = (InserirFuncionalidadeActionForm) actionForm;

		this.pesquisarCampoEnter(httpServletRequest,
				inserirFuncionalidadeActionForm, fachada);

		if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada() == null
				|| inserirFuncionalidadeActionForm.equals("")) {
			inserirFuncionalidadeActionForm
					.setIndicadorPontoEntrada(ConstantesSistema.INDICADOR_USO_ATIVO
							.toString());
		}

		if (inserirFuncionalidadeActionForm.getIndicadorOlap() == null
				|| !inserirFuncionalidadeActionForm.equals("")) {
			inserirFuncionalidadeActionForm
					.setIndicadorOlap(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());
		}

		if (inserirFuncionalidadeActionForm.getIndicadorNovaJanela() == null
				|| !inserirFuncionalidadeActionForm.equals("")) {
			inserirFuncionalidadeActionForm
					.setIndicadorNovaJanela(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());
		}

		// Parte do adicionar

		Collection<Funcionalidade> colecaoFuncionalidade = null;

		if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
			colecaoFuncionalidade = (Collection) sessao
					.getAttribute("colecaoFuncionalidadeTela");
		} else {
			colecaoFuncionalidade = new ArrayList();
		}

		if (sessao.getAttribute("colecaoFuncionalidade") != null) {
			Collection colecaoPopUp = (Collection) sessao
					.getAttribute("colecaoFuncionalidade");
			colecaoFuncionalidade.addAll(colecaoPopUp);
			sessao.removeAttribute("colecaoFuncionalidade");
			sessao.setAttribute("colecaoFuncionalidadeTela",
					colecaoFuncionalidade);
		}

		if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada() != null
				&& inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()
						.equalsIgnoreCase(
								ConstantesSistema.INDICADOR_USO_ATIVO
										.toString())) {

			httpServletRequest.setAttribute("funcionalidadeCategoria", "sim");

		} else {
			httpServletRequest.removeAttribute("funcionalidadeCategoria");

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			inserirFuncionalidadeActionForm.setDescricao("");
			inserirFuncionalidadeActionForm.setDescricaoAbreviada("");
			inserirFuncionalidadeActionForm.setCaminhoMenu("");
			inserirFuncionalidadeActionForm.setCaminhoUrl("");
			inserirFuncionalidadeActionForm
					.setIndicadorPontoEntrada(ConstantesSistema.INDICADOR_USO_ATIVO
							.toString());
			inserirFuncionalidadeActionForm.setModulo("-1");

			inserirFuncionalidadeActionForm
					.setIndicadorNovaJanela(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());

			inserirFuncionalidadeActionForm
					.setIndicadorOlap(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());

			inserirFuncionalidadeActionForm.setNumeroOrdemMenu("");

			if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()
					.equals(ConstantesSistema.INDICADOR_USO_ATIVO.toString())) {

				inserirFuncionalidadeActionForm
						.setIdFuncionalidadeCategoria("");

				inserirFuncionalidadeActionForm
						.setNomeFuncionalidadeCategoria("");

				httpServletRequest.setAttribute("funcionalidadeCategoria",
						"sim");

			} else {
				httpServletRequest.removeAttribute("funcionalidadeCategoria");

			}

			sessao.removeAttribute("colecaoFuncionalidadeTela");

		}

		return retorno;

	}

	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			InserirFuncionalidadeActionForm form, Fachada fachada) {

		String idFuncionalidadeCategoria = form.getIdFuncionalidadeCategoria();

		// Pesquisa a empresa
		if (idFuncionalidadeCategoria != null
				&& !idFuncionalidadeCategoria.trim().equals("")) {

			FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();
			filtroFuncionalidadeCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeCategoria.ID,
							idFuncionalidadeCategoria));

			Collection colecaoFuncionalidadeCategoria = fachada.pesquisar(
					filtroFuncionalidadeCategoria,
					FuncionalidadeCategoria.class.getName());

			if (colecaoFuncionalidadeCategoria != null
					&& !colecaoFuncionalidadeCategoria.isEmpty()) {
				FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeCategoria);
				
				httpServletRequest.setAttribute("idFuncionalidadeCategoria",
						"true");
				
				form.setIdFuncionalidadeCategoria(funcionalidadeCategoria
						.getId().toString());
				form.setNomeFuncionalidadeCategoria(funcionalidadeCategoria
						.getNome());
			} else {
				form.setIdFuncionalidadeCategoria("");
				form
						.setNomeFuncionalidadeCategoria("FUNCIONALIDADE CATEGORIA INEXISTENTE");
				//httpServletRequest.setAttribute(
				//		"funcionalidadeCategoriaInexistente", true);
				//httpServletRequest.setAttribute("nomeCampo", "idFuncionalidadeCategoria");
			}

		} else {
			form.setNomeFuncionalidadeCategoria("");
		}

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		FiltroModulo filtroModulo = new FiltroModulo();

		filtroModulo.setCampoOrderBy(FiltroModulo.DESCRICAO_MODULO);
		Collection<Modulo> colecaoModulo = fachada.pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}

		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);

	}
}