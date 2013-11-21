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
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * @author Thiago Tenório
 * @date 30/10/2006
 */
public class ExibirAtualizarAgenciaBancariaAction extends GcomAction {
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
				.findForward("atualizarAgenciaBancaria");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAgenciaBancariaActionForm atualizarAgenciaBancariaActionForm = (AtualizarAgenciaBancariaActionForm) actionForm;

		String removerEndereco = (String) httpServletRequest
				.getParameter("removerEndereco");

		if (httpServletRequest.getParameter("menu") != null) {
			atualizarAgenciaBancariaActionForm.setBancoID("");
		}
		Fachada fachada = Fachada.getInstancia();

		// this.getAtendimentoMotivoEncerramentoCollection(sessao);
		//
		// this.getServicoTipoReferenciaCollection(sessao, fachada);

		String id = null;

		String idAgencia = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao")
						.equals("")) {

			sessao.removeAttribute("agencia");
			sessao.removeAttribute("colecaoAgenciaTela");

		}

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		FiltroBanco filtroBanco = new FiltroBanco();
		
		Collection colecaoBancos = fachada.pesquisar(filtroBanco, Banco.class.getName());
		
		sessao.setAttribute("colecaoBanco", colecaoBancos);

		// Verifica se o servicoCobrancaValor já está na sessão, em caso
		// afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez
		if (sessao.getAttribute("agenciaAtualizar") == null) {
			
			// Limpa o endereço da sessão
			if (sessao.getAttribute("colecaoEnderecos") != null) {
				sessao.removeAttribute("colecaoEnderecos");
			}

			Agencia agencia = null;

			if (sessao.getAttribute("agencia") != null) {

				agencia = (Agencia) sessao.getAttribute("agencia");

				sessao.setAttribute("idAgencia", agencia.getId().toString());

				sessao.setAttribute("filtrar", true);

			} else {

				agencia = null;

				if (httpServletRequest.getParameter("inserir") != null) {
					sessao.setAttribute("inserir", true);
					sessao.setAttribute("filtrar", true);
				} else {
					sessao.removeAttribute("filtrar");
					sessao.removeAttribute("inserir");
				}

				idAgencia = null;

				if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
						|| httpServletRequest.getParameter(
								"idRegistroAtualizacao").equals("")) {
					agencia = (Agencia) sessao.getAttribute("agencia");
				} else {
					idAgencia = (String) httpServletRequest
							.getParameter("idRegistroAtualizacao");
					sessao.setAttribute("idRegistroAtualizacao", idAgencia);
				}

				if (idAgencia != null) {

					id = idAgencia;

					FiltroAgencia filtroAgencia = new FiltroAgencia();
					filtroAgencia
							.adicionarCaminhoParaCarregamentoEntidade("banco");
					filtroAgencia
							.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroAgencia
							.adicionarCaminhoParaCarregamentoEntidade("cep");
					filtroAgencia
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroAgencia
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroAgencia
							.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroAgencia
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");

					filtroAgencia.adicionarParametro(new ParametroSimples(
							FiltroAgencia.ID, idAgencia));

					Collection<Agencia> colecaoAgencia = fachada.pesquisar(
							filtroAgencia, Agencia.class.getName());

					if (colecaoAgencia == null || colecaoAgencia.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute("colecaoAgencia",
							colecaoAgencia);

					agencia = (Agencia) colecaoAgencia.iterator().next();

				}

				if (idAgencia == null) {
					if (sessao.getAttribute("idRegistroAtualizacao") != null) {
						idAgencia = (String) sessao
								.getAttribute("idRegistroAtualizacao");
					} else {
						agencia = (Agencia) sessao
								.getAttribute("osReferidaRetornoTipo");
						idAgencia = agencia.getId().toString();
					}
				}

				sessao.setAttribute("agenciaAtualizar", agencia);

			}

			if (agencia != null) {
				atualizarAgenciaBancariaActionForm.setCodigo(agencia
						.getCodigoAgencia().toString());

				atualizarAgenciaBancariaActionForm.setNome(agencia
						.getNomeAgencia());

				atualizarAgenciaBancariaActionForm.setTelefone(agencia
						.getNumeroTelefone());

				atualizarAgenciaBancariaActionForm.setRamal(agencia
						.getNumeroRamal());

				atualizarAgenciaBancariaActionForm.setFax(agencia
						.getNumeroFax());

				atualizarAgenciaBancariaActionForm.setEmail(agencia.getEmail());

				atualizarAgenciaBancariaActionForm.setBancoID(agencia
						.getBanco().getId().toString());

				Collection colecaoEnderecos = null;

				if (agencia.getEnderecoFormatado() != null) {
					colecaoEnderecos = new ArrayList();
					colecaoEnderecos.add(agencia);
					sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
				}

				id = agencia.getId().toString();

				sessao.setAttribute("agenciaAtualizar", agencia);
			}

		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoAgenciaTela");

			String agenciaID = null;

			if (sessao.getAttribute("idRegistroAtualizacao") != null
					&& !sessao.getAttribute("idRegistroAtualizacao").equals("")) {
				agenciaID = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if ((agenciaID == null) && (id == null)) {

				Agencia agencia = (Agencia) sessao.getAttribute("agencia");

				atualizarAgenciaBancariaActionForm.setNome(agencia
						.getNomeAgencia());

				atualizarAgenciaBancariaActionForm.setTelefone(agencia
						.getNumeroTelefone());

				atualizarAgenciaBancariaActionForm.setRamal(agencia
						.getNumeroRamal());

				atualizarAgenciaBancariaActionForm.setFax(agencia
						.getNumeroFax());

				atualizarAgenciaBancariaActionForm.setEmail(agencia.getEmail());

				atualizarAgenciaBancariaActionForm.setBancoID(agencia
						.getBanco().getId().toString());

				Collection colecaoEnderecos = null;

				if (agencia.getEnderecoFormatado() != null) {
					colecaoEnderecos = new ArrayList();
					colecaoEnderecos.add(agencia);
					sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
				}

				sessao.setAttribute("agenciaAtualizar", agencia);
			}

			if ((idAgencia == null) && (id != null)) {

				idAgencia = id;
			}

			if (idAgencia != null) {

				FiltroAgencia filtroAgencia = new FiltroAgencia();
				filtroAgencia.adicionarCaminhoParaCarregamentoEntidade("banco");

				filtroAgencia.adicionarParametro(new ParametroSimples(
						FiltroAgencia.ID, idAgencia));

				Collection<Agencia> colecaoAgencia = fachada.pesquisar(
						filtroAgencia, Agencia.class.getName());

				if (colecaoAgencia == null || colecaoAgencia.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoAgencia",
						colecaoAgencia);

				Agencia agencia = (Agencia) colecaoAgencia.iterator().next();

				atualizarAgenciaBancariaActionForm.setCodigo(agencia
						.getCodigoAgencia().toString());

				atualizarAgenciaBancariaActionForm.setNome(agencia
						.getNomeAgencia());

				atualizarAgenciaBancariaActionForm.setTelefone(agencia
						.getNumeroTelefone());

				atualizarAgenciaBancariaActionForm.setRamal(agencia
						.getNumeroRamal());

				atualizarAgenciaBancariaActionForm.setFax(agencia
						.getNumeroFax());

				atualizarAgenciaBancariaActionForm.setEmail(agencia.getEmail());

				atualizarAgenciaBancariaActionForm.setBancoID(agencia
						.getBanco().getId().toString());

				httpServletRequest.setAttribute("idAgencia", idAgencia);
				sessao.setAttribute("agenciaAtualizar", agencia);

			}
		}
		// -------------- bt DESFAZER ---------------

		httpServletRequest.setAttribute("colecaoAgenciaTela", sessao
				.getAttribute("colecaoAgenciaTipoValorTela"));

		sessao.removeAttribute("tipoPesquisaRetorno");

		// Remove o endereco informado.
		if (removerEndereco != null
				&& !removerEndereco.trim().equalsIgnoreCase("")) {

			if (sessao.getAttribute("colecaoEnderecos") != null) {

				Collection enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");
				if (!enderecos.isEmpty()) {
					enderecos.remove(enderecos.iterator().next());
				}
			}
		}

		return retorno;

	}

}
