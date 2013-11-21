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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
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
 * @author Thiago Tenório
 * @date 30/10/2006
 */
public class ExibirAtualizarCapacidadeHidrometroAction extends GcomAction {
	/**
	 * [UC0393] Atualizar Agência Bancária
	 * 
	 * Este caso de uso permite alterar um valor de Agência Bancária
	 * 
	 * @author Thiago Tenório
	 * @date 31/10/2006
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
				.findForward("atualizarCapacidadeHidrometro");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCapacidadeHidrometroActionForm atualizarCapacidadeHidrometroActionForm = (AtualizarCapacidadeHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// this.getAtendimentoMotivoEncerramentoCollection(sessao);
		//
		// this.getServicoTipoReferenciaCollection(sessao, fachada);

		String id = null;

		String idCapacidadeHidrometro = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao")
						.equals("")) {

			sessao.removeAttribute("hidrometroCapacidade");
			sessao.removeAttribute("colecaoHidrometroCapacidadeTipoTela");

		}

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Verifica se o servicoCobrancaValor já está na sessão, em caso
		// afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez
		if (sessao.getAttribute("capacidadeHidromtroAtualizar") == null) {

			// // Limpa o endereço da sessão
			// if (sessao.getAttribute("colecaoEnderecos") != null) {
			// sessao.removeAttribute("colecaoEnderecos");
			// }

			HidrometroCapacidade hidrometroCapacidade = null;

			if (sessao.getAttribute("hidrometroCapacidade") != null) {

				hidrometroCapacidade = (HidrometroCapacidade) sessao
						.getAttribute("hidrometroCapacidade");

				sessao.setAttribute("idCapacidadeHidrometro",
						hidrometroCapacidade.getId().toString());

				sessao.setAttribute("filtrar", true);

			} else {

				hidrometroCapacidade = null;

				if (httpServletRequest.getParameter("inserir") != null) {
					sessao.setAttribute("inserir", true);
					sessao.setAttribute("filtrar", true);
				} else {
					sessao.removeAttribute("filtrar");
					sessao.removeAttribute("inserir");
				}

				idCapacidadeHidrometro = null;

				if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
						|| httpServletRequest.getParameter(
								"idRegistroAtualizacao").equals("")) {
					hidrometroCapacidade = (HidrometroCapacidade) sessao
							.getAttribute("hidrometroCapacidade");
				} else {
					idCapacidadeHidrometro = (String) httpServletRequest
							.getParameter("idRegistroAtualizacao");
					sessao.setAttribute("idRegistroAtualizacao",
							idCapacidadeHidrometro);
				}

				if (idCapacidadeHidrometro != null) {

					id = idCapacidadeHidrometro;

					FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
					filtroHidrometroCapacidade
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroCapacidade.ID,
									idCapacidadeHidrometro));

					Collection<HidrometroCapacidade> colecaoCapacidadeHidrometro = fachada
							.pesquisar(filtroHidrometroCapacidade,
									HidrometroCapacidade.class.getName());

					if (colecaoCapacidadeHidrometro == null
							|| colecaoCapacidadeHidrometro.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute(
							"colecaoCapacidadeHidrometro",
							colecaoCapacidadeHidrometro);

					hidrometroCapacidade = (HidrometroCapacidade) colecaoCapacidadeHidrometro
							.iterator().next();

				}

				if (idCapacidadeHidrometro == null) {
					if (sessao.getAttribute("idRegistroAtualizacao") != null) {
						idCapacidadeHidrometro = (String) sessao
								.getAttribute("idRegistroAtualizacao");
					} else {
						hidrometroCapacidade = (HidrometroCapacidade) sessao
								.getAttribute("hidrometroCapacidade");
						idCapacidadeHidrometro = hidrometroCapacidade.getId()
								.toString();
					}
				}

				sessao.setAttribute("hidrometroCapacidadeAtualizar",
						hidrometroCapacidade);

			}

			// if (clienteTipo != null) {
			// atualizarClienteTipoActionForm.setDescricao(clienteTipo
			// .getDescricao().toString());

			atualizarCapacidadeHidrometroActionForm.setIdentificador(""+ hidrometroCapacidade.getId());

			atualizarCapacidadeHidrometroActionForm.setDescricao(hidrometroCapacidade.getDescricao());
			
			atualizarCapacidadeHidrometroActionForm.setAbreviatura(hidrometroCapacidade.getDescricaoAbreviada());

			
			if(hidrometroCapacidade.getLeituraMinimo() != null){
				atualizarCapacidadeHidrometroActionForm.setNumMinimo(""+hidrometroCapacidade.getLeituraMinimo());
			}
			
			if(hidrometroCapacidade.getLeituraMaximo() != null){
				atualizarCapacidadeHidrometroActionForm.setNumMaximo(""+hidrometroCapacidade.getLeituraMaximo());
				
			}
			if(hidrometroCapacidade.getIndicadorUso() != null){
				atualizarCapacidadeHidrometroActionForm.setIndicadoruso(hidrometroCapacidade.getIndicadorUso());
			}
			
			if(hidrometroCapacidade.getNumeroOrdem() != null){
				atualizarCapacidadeHidrometroActionForm.setNumOrdem(""+hidrometroCapacidade.getNumeroOrdem());

			}

			atualizarCapacidadeHidrometroActionForm
					.setCodigo(hidrometroCapacidade
							.getCodigoHidrometroCapacidade());

			// if(atualizarClienteTipoActionForm.setEsferaPoder(clienteTipo
			// .getEsferaPoder().getId().toString());

			id = hidrometroCapacidade.getId().toString();

			sessao.setAttribute("hidrometroCapacidadeAtualizar",
					hidrometroCapacidade);
		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoHidrometroCapacidadeTela");

			String hidrometroCapacidadeID = null;

			if (sessao.getAttribute("idRegistroAtualizacao") != null
					&& !sessao.getAttribute("idRegistroAtualizacao").equals("")) {
				hidrometroCapacidadeID = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if ((hidrometroCapacidadeID == null) && (id == null)) {

				HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) sessao
						.getAttribute("hidrometroCapacidade");

				atualizarCapacidadeHidrometroActionForm.setIdentificador(""
						+ hidrometroCapacidade.getId());

				atualizarCapacidadeHidrometroActionForm
						.setDescricao(hidrometroCapacidade.getDescricao());
				
				atualizarCapacidadeHidrometroActionForm
				.setAbreviatura(hidrometroCapacidade.getDescricaoAbreviada());

				if(hidrometroCapacidade.getLeituraMinimo() != null){
					atualizarCapacidadeHidrometroActionForm.setNumMinimo(""+hidrometroCapacidade.getLeituraMinimo());
				}
				
				if(hidrometroCapacidade.getLeituraMaximo() != null){
					atualizarCapacidadeHidrometroActionForm.setNumMaximo(""+hidrometroCapacidade.getLeituraMaximo());
					
				}
				if(hidrometroCapacidade.getIndicadorUso() != null){
					atualizarCapacidadeHidrometroActionForm.setIndicadoruso(hidrometroCapacidade.getIndicadorUso());
				}
				
				if(hidrometroCapacidade.getNumeroOrdem() != null){
					atualizarCapacidadeHidrometroActionForm.setNumOrdem(hidrometroCapacidade.getNumeroOrdem().toString());
				}
				
				atualizarCapacidadeHidrometroActionForm
						.setCodigo(hidrometroCapacidade
								.getCodigoHidrometroCapacidade());

				sessao.setAttribute("hidrometroCapacidadeAtualizar",
						hidrometroCapacidade);
			}

			if ((idCapacidadeHidrometro == null) && (id != null)) {

				idCapacidadeHidrometro = id;
			}

			if (idCapacidadeHidrometro != null) {

				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

				filtroHidrometroCapacidade
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroCapacidade.ID,
								idCapacidadeHidrometro));

				Collection<HidrometroCapacidade> colecaoHidrometroCapacidade = fachada
						.pesquisar(filtroHidrometroCapacidade,
								HidrometroCapacidade.class.getName());

				if (colecaoHidrometroCapacidade == null
						|| colecaoHidrometroCapacidade.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoHidrometroCapacidade",
						colecaoHidrometroCapacidade);

				HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) colecaoHidrometroCapacidade
						.iterator().next();

				atualizarCapacidadeHidrometroActionForm.setIdentificador(""
						+ hidrometroCapacidade.getId());

				atualizarCapacidadeHidrometroActionForm
						.setDescricao(hidrometroCapacidade.getDescricao());
				
				atualizarCapacidadeHidrometroActionForm
				.setAbreviatura(hidrometroCapacidade.getDescricaoAbreviada());

				atualizarCapacidadeHidrometroActionForm
						.setNumMinimo(hidrometroCapacidade.getLeituraMinimo().toString());

				atualizarCapacidadeHidrometroActionForm
						.setNumMaximo(hidrometroCapacidade.getLeituraMaximo().toString());

				atualizarCapacidadeHidrometroActionForm
						.setIndicadoruso(hidrometroCapacidade.getIndicadorUso());
				
				atualizarCapacidadeHidrometroActionForm
				.setNumOrdem(hidrometroCapacidade.getNumeroOrdem().toString());

				atualizarCapacidadeHidrometroActionForm
						.setCodigo(hidrometroCapacidade
								.getCodigoHidrometroCapacidade());
				
				httpServletRequest.setAttribute("idCapacidadeHidrometro", idCapacidadeHidrometro);
				sessao.setAttribute("hidrometroCapacidadeAtualizar", hidrometroCapacidade);

			}
		}
		// -------------- bt DESFAZER ---------------

		// httpServletRequest.setAttribute("colecaoClienteTipoTela", sessao
		// .getAttribute("colecaoClienteTipoTipoValorTela"));
		//
		// sessao.removeAttribute("tipoPesquisaRetorno");

		return retorno;

	}

}
