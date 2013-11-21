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
package gcom.gui.operacional;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import gcom.operacional.DivisaoEsgoto;

import gcom.operacional.FiltroDivisaoEsgoto;

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
 * 
 * @author Arthur Carvalho
 * @date 06/2008
 */
public class ExibirAtualizarDivisaoEsgotoAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */

	private String unidadeOrganizacionalId;

	private Collection colecaoPesquisa;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("divisaoEsgotoAtualizar");

		AtualizarDivisaoEsgotoActionForm atualizarDivisaoEsgotoActionForm = (AtualizarDivisaoEsgotoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		DivisaoEsgoto divisaoEsgoto= new DivisaoEsgoto();

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();
		
		filtroDivisaoEsgoto.adicionarCaminhoParaCarregamentoEntidade(FiltroDivisaoEsgoto.UNIDADE_ORGANIZACIONAL);	
		
		
		if ((objetoConsulta == null || objetoConsulta.equalsIgnoreCase(""))
				&& (httpServletRequest.getParameter("desfazer") == null)) {
			// Recupera o id da Divisao de esgoto que vai ser atualizada

			String divisaoEsgotoId = null;
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				divisaoEsgotoId = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Filtrar Divisao Esgoto
				sessao.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao", divisaoEsgotoId);
				sessao.setAttribute("indicadorUso", "3");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				divisaoEsgotoId = (String) sessao.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Filtrar Divisao Esgoto
				sessao.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {
				divisaoEsgotoId = (String)httpServletRequest
						.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Manter Divisao Esgoto
				sessao.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao", divisaoEsgotoId);
			}

		} else {
			if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equals("")){
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			}
		}

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			// Recebe o valor do campo unidadeOrganizacionalId do formulário.
			unidadeOrganizacionalId = atualizarDivisaoEsgotoActionForm
					.getUnidadeOrganizacionalId();

			switch (Integer.parseInt(objetoConsulta)) {

			// Unidade Organizacional
			case 1:

				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional= new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID,
								unidadeOrganizacionalId));

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Unidade Organizacional
				colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Unidade Organizacional nao encontrado
					// Limpa o campo unidadeOrganizacionalId do formulário
					atualizarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
					atualizarDivisaoEsgotoActionForm
							.setUnidadeOrganizacionalDescricao("Unidade organizacional inexistente.");
					httpServletRequest.setAttribute("corUnidadeOrganizacional",
							"exception");

					httpServletRequest.setAttribute("nomeCampo",
							"unidadeOrganizacionalId");
				} else {
					UnidadeOrganizacional objetoUnidadeOrganizacional= (UnidadeOrganizacional) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					atualizarDivisaoEsgotoActionForm
							.setUnidadeOrganizacionalId(String
									.valueOf(objetoUnidadeOrganizacional.getId()));
					atualizarDivisaoEsgotoActionForm
							.setUnidadeOrganizacionalDescricao(objetoUnidadeOrganizacional
									.getDescricao());
					httpServletRequest.setAttribute("corUnidadeOrganizacional",
							"valor");

					httpServletRequest.setAttribute("nomeCampo",
							"unidadeOrganizacionalId");
				}

				break;

			}
		}else{

			if (httpServletRequest.getParameter("manter") != null) {
				sessao.setAttribute("manter", true);
			} else if (httpServletRequest.getParameter("filtrar") != null) {
				sessao.removeAttribute("manter");
			}
	
			if (id == null) {
				if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
					id = (String) sessao.getAttribute("idRegistroAtualizacao");
				} else {
					id = (String) httpServletRequest.getAttribute(
							"idRegistroAtualizacao").toString();
				}
			} else {
				sessao.setAttribute("i", true);
			}
			
			if (divisaoEsgoto.getUnidadeOrganizacional() != null) {
				atualizarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId(divisaoEsgoto
						.getUnidadeOrganizacional().getId().toString());
				atualizarDivisaoEsgotoActionForm.setUnidadeOrganizacionalDescricao(divisaoEsgoto
						.getUnidadeOrganizacional().getDescricao());
	
			}
	
			if (divisaoEsgoto == null) {
	
				if (id != null && !id.equals("")) {
	
					FiltroUnidadeOrganizacional filtroUnidadeOrganizacional= new FiltroUnidadeOrganizacional();
	
					filtroUnidadeOrganizacional
							.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
					filtroUnidadeOrganizacional
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.ID, id));
	
					Collection colecaoDivisaoEsgoto = fachada.pesquisar(
							filtroUnidadeOrganizacional, UnidadeOrganizacional.class
									.getName());
	
					if (colecaoDivisaoEsgoto != null && !colecaoDivisaoEsgoto.isEmpty()) {
						divisaoEsgoto = (DivisaoEsgoto) Util
								.retonarObjetoDeColecao(colecaoDivisaoEsgoto);
						
						atualizarDivisaoEsgotoActionForm.setId(divisaoEsgoto.getId()
								.toString());
	
						atualizarDivisaoEsgotoActionForm.setDescricao(divisaoEsgoto
								.getDescricao());
	
	
						atualizarDivisaoEsgotoActionForm
								.setUnidadeOrganizacionalId(divisaoEsgoto
										.getUnidadeOrganizacional().getId().toString());
	
						atualizarDivisaoEsgotoActionForm
								.setUnidadeOrganizacionalDescricao(divisaoEsgoto
										.getUnidadeOrganizacional().getDescricao());
						atualizarDivisaoEsgotoActionForm
						.setIndicadorUso(divisaoEsgoto
								.getIndicadorUso());
					}
				}
			}
	
			if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {
	
				filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
						FiltroDivisaoEsgoto.ID, id));
				Collection colecaoDivisaoEsgoto= fachada.pesquisar(
						filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());
				if (colecaoDivisaoEsgoto != null && !colecaoDivisaoEsgoto.isEmpty()) {
					divisaoEsgoto= (DivisaoEsgoto) Util
							.retonarObjetoDeColecao(colecaoDivisaoEsgoto);
					
					atualizarDivisaoEsgotoActionForm.setId(divisaoEsgoto.getId()
							.toString());
	
					atualizarDivisaoEsgotoActionForm.setDescricao(divisaoEsgoto
							.getDescricao());
	
	
					atualizarDivisaoEsgotoActionForm
							.setUnidadeOrganizacionalId(divisaoEsgoto
									.getUnidadeOrganizacional().getId().toString());
	
					atualizarDivisaoEsgotoActionForm
							.setUnidadeOrganizacionalDescricao(divisaoEsgoto
									.getUnidadeOrganizacional().getDescricao());
					
					atualizarDivisaoEsgotoActionForm
					.setIndicadorUso(divisaoEsgoto
							.getIndicadorUso());
				}
	
	
				
	
				sessao.setAttribute("atualizarDivisaoEsgoto", divisaoEsgoto);
	
				if (sessao.getAttribute("colecaoDivisaoEsgoto") != null) {
					sessao.setAttribute("caminhoRetornoVoltar",
							"/gsan/filtrarDivisaoEsgotoAction.do");
				} else {
					sessao.setAttribute("caminhoRetornoVoltar",
							"/gsan/exibirFiltrarDivisaoEsgotoAction.do");
				}
	
			}
		}

		return retorno;
	}
}