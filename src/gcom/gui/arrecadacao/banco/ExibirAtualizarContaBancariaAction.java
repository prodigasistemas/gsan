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
package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
public class ExibirAtualizarContaBancariaAction extends GcomAction {
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
				.findForward("atualizarContaBancaria");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarContaBancariaActionForm atualizarContaBancariaActionForm = (AtualizarContaBancariaActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			atualizarContaBancariaActionForm.setBanco("");
			atualizarContaBancariaActionForm.setAgenciaBancaria("");
		}
		
		Fachada fachada = Fachada.getInstancia();

		String id = null;

		String idContaBancaria = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao")
						.equals("")) {

			sessao.removeAttribute("objetoContaBancaria");
			sessao.removeAttribute("colecaoContaBancariaTela");

		}

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Verifica se o ContaBancaria já está na sessão, em caso
		// afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez
		if (sessao.getAttribute("contaBancariaAtualizar") == null) {

			ContaBancaria contaBancaria = null;

			if (sessao.getAttribute("contaBancaria") != null) {

				contaBancaria = (ContaBancaria) sessao
						.getAttribute("contaBancaria");

				sessao.setAttribute("idContaBancaria", contaBancaria.getId()
						.toString());
				
				sessao.setAttribute("filtrar", true);

				id = contaBancaria.getId().toString();

			} else {

				idContaBancaria = null;
				
				if (httpServletRequest.getParameter("inserir") != null) {
					sessao.setAttribute("inserir", true);
					sessao.setAttribute("filtrar", true);
				} else {
					sessao.removeAttribute("filtrar");
					sessao.removeAttribute("inserir");
				}

				if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
						|| httpServletRequest.getParameter(
								"idRegistroAtualizacao").equals("")) {
					contaBancaria = (ContaBancaria) sessao
							.getAttribute("objetoContaBancaria");
				} else {
					idContaBancaria = (String) httpServletRequest
							.getParameter("idRegistroAtualizacao");
					sessao.setAttribute("idRegistroAtualizacao",
							idContaBancaria);
				}

				if (idContaBancaria != null) {

					id = idContaBancaria;

					FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();

					filtroContaBancaria
							.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

					filtroContaBancaria
							.adicionarCaminhoParaCarregamentoEntidade("agencia");

					filtroContaBancaria
							.adicionarParametro(new ParametroSimples(
									FiltroContaBancaria.ID, idContaBancaria));

					Collection colecaoContaBancaria = fachada
							.pesquisar(filtroContaBancaria, ContaBancaria.class
									.getName());

					if (colecaoContaBancaria == null
							|| colecaoContaBancaria.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute("colecaoContaBancaria",
							colecaoContaBancaria);

					contaBancaria = (ContaBancaria) colecaoContaBancaria
							.iterator().next();

				}

				if (idContaBancaria == null) {
					if (sessao.getAttribute("idRegistroAtualizacao") != null) {
						idContaBancaria = (String) sessao
								.getAttribute("idRegistroAtualizacao");
					} else {
						contaBancaria = (ContaBancaria) sessao
								.getAttribute("contaBancaria");
						idContaBancaria = contaBancaria.getId().toString();
					}
				}

				FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();

				filtroContaBancaria.adicionarParametro(new ParametroSimples(
						FiltroContaBancaria.ID, idContaBancaria));

				filtroContaBancaria
						.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
				filtroContaBancaria
						.adicionarCaminhoParaCarregamentoEntidade("agencia");

				Collection colecaoContaBancaria = (Collection) fachada
						.pesquisar(filtroContaBancaria, ContaBancaria.class
								.getName());

				contaBancaria = (ContaBancaria) colecaoContaBancaria.iterator()
						.next();

			}

			atualizarContaBancariaActionForm.setContaBanco(contaBancaria
					.getNumeroConta());

			if (contaBancaria
					.getNumeroContaContabil() != null) {
				atualizarContaBancariaActionForm.setContaContabil(contaBancaria
						.getNumeroContaContabil().toString());
			} else {
				atualizarContaBancariaActionForm.setContaContabil("");
			}
			
			atualizarContaBancariaActionForm.setBanco(contaBancaria
					.getAgencia().getBanco().getId().toString());

			atualizarContaBancariaActionForm.setAgenciaBancaria(contaBancaria
					.getAgencia().getId().toString());

			if (contaBancaria.getAgencia().getBanco() != null) {
				atualizarContaBancariaActionForm.setBanco(contaBancaria
						.getAgencia().getBanco().getId().toString());
			} else {
				atualizarContaBancariaActionForm.setBanco("");
			}

			if (contaBancaria.getAgencia() != null) {
				atualizarContaBancariaActionForm
						.setAgenciaBancaria(contaBancaria.getAgencia().getId()
								.toString());
			} else {
				atualizarContaBancariaActionForm.setAgenciaBancaria("");
			}

			sessao.setAttribute("contaBancariaAtualizar", contaBancaria);

		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoContaBancariaTela");

			String contaBancariaID = null;

			if (sessao.getAttribute("idRegistroAtualizacao") != null
					&& !sessao.getAttribute("idRegistroAtualizacao").equals("")) {
				contaBancariaID = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if ((contaBancariaID == null) && (id == null)) {

				ContaBancaria contaBancaria = (ContaBancaria) sessao
						.getAttribute("contaBancaria");

				// atualizarAgenciaBancariaActionForm
				// .setCodigo(agencia.getId()
				// .toString());

				atualizarContaBancariaActionForm.setContaBanco(contaBancaria
						.getNumeroConta());

				if (contaBancaria
						.getNumeroContaContabil() != null) {
					atualizarContaBancariaActionForm.setContaContabil(contaBancaria
							.getNumeroContaContabil().toString());
				} else {
					atualizarContaBancariaActionForm.setContaContabil("");
				}

				atualizarContaBancariaActionForm.setBanco(contaBancaria
						.getAgencia().getBanco().getId().toString());

				sessao.setAttribute("contaBancariaAtualizar", contaBancaria);
				sessao.removeAttribute("contaBancaria");
			}

			if ((idContaBancaria == null) && (id != null)) {

				idContaBancaria = id;
			}

			if (idContaBancaria != null) {

				FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();

				filtroContaBancaria
						.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

				filtroContaBancaria.adicionarParametro(new ParametroSimples(
						FiltroContaBancaria.ID, idContaBancaria));

				Collection colecaoContaBancaria = fachada
						.pesquisar(filtroContaBancaria, ContaBancaria.class
								.getName());

				if (colecaoContaBancaria == null
						|| colecaoContaBancaria.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoContaBancaria",
						colecaoContaBancaria);

				ContaBancaria contaBancaria = (ContaBancaria) colecaoContaBancaria
						.iterator().next();

				atualizarContaBancariaActionForm.setContaBanco(contaBancaria
						.getNumeroConta());

				if (contaBancaria
						.getNumeroContaContabil() != null) {
					atualizarContaBancariaActionForm.setContaContabil(contaBancaria
							.getNumeroContaContabil().toString());
				} else {
					atualizarContaBancariaActionForm.setContaContabil("");
				}

				atualizarContaBancariaActionForm.setBanco(contaBancaria
						.getAgencia().getBanco().getId().toString());

				atualizarContaBancariaActionForm
						.setAgenciaBancaria(contaBancaria.getAgencia().getId()
								.toString());

				httpServletRequest.setAttribute("idContaBancaria", idContaBancaria);
				sessao.setAttribute("contaBancariaAtualizar", contaBancaria);

			}
		} else {
			String idBanco = atualizarContaBancariaActionForm.getBanco();
			
			if (idBanco != null && !idBanco.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
				FiltroAgencia filtroAgencia = new FiltroAgencia();
				filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.BANCO_ID, idBanco));
				
				Collection colecaoAgencia = fachada.pesquisar(filtroAgencia, Agencia.class.getName());
				
				sessao.setAttribute("colecaoAgencia", colecaoAgencia);
			
			} else {
				sessao.removeAttribute("colecaoAgencia");
			}
		}
		// -------------- bt DESFAZER ---------------

		httpServletRequest.setAttribute("colecaoContaBancariaTela", sessao
				.getAttribute("colecaoContaBancariaTipoValorTela"));

		return retorno;

	}

}
