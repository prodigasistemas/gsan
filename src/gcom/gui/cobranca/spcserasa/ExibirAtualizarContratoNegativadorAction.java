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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cobranca.NegativadorContrato;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoMotivoCancelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorContrato;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Atualizar Contrato Negativador
 * 
 * @author Yara Taciane 
 * @created 27/12/2007
 */

public class ExibirAtualizarContratoNegativadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping
				.findForward("atualizarContratoNegativador");
		AtualizarContratoNegativadorActionForm atualizarContratoNegativadorActionForm = (AtualizarContratoNegativadorActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// volta da msg de Contrato do Negativador já utilizado, não pode ser
		// alterado nem excluído.
		String confirmado = httpServletRequest.getParameter("confirmado");

		String idContratoNegativador = null;
		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {
			// Recupera o id do PerfilParcelamento que vai ser atualizado

			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idContratoNegativador = httpServletRequest
						.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do botão Voltar p Filtrar Perfil de
				// Parcelamento
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao",
						idContratoNegativador);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idContratoNegativador = (String) sessao
						.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Filtrar Perfil de
				// Parcelamento
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idContratoNegativador = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Manter Perfil de
				// Parcelamento
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao",
						idContratoNegativador);
			}
		} else {
			idContratoNegativador = (String) sessao
					.getAttribute("idRegistroAtualizacao");
		}

		Collection colecaoContratoMotivoCancelamento = (Collection) sessao
				.getAttribute("colecaoContratoMotivoCancelamento");

		if (colecaoContratoMotivoCancelamento == null) {

			FiltroContratoMotivoCancelamento filtroContratoMotivoCancelamento = new FiltroContratoMotivoCancelamento();
			filtroContratoMotivoCancelamento.setConsultaSemLimites(true);

			colecaoContratoMotivoCancelamento = fachada.pesquisar(
					filtroContratoMotivoCancelamento,
					ContratoMotivoCancelamento.class.getName());

			if (colecaoContratoMotivoCancelamento == null
					|| colecaoContratoMotivoCancelamento.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"CONTRATO_MOTIVO_CANCELAMENTO");
			} else {
				sessao.setAttribute("colecaoContratoMotivoCancelamento",
						colecaoContratoMotivoCancelamento);
			}
		}

		// Verifica se o usuário está selecionando o Perfil de Parcelamento da
		// página de manter
		// Caso contrário o usuário está teclando enter na página de atualizar
		if ((idContratoNegativador != null && !idContratoNegativador.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {
			exibirContratoNegativador(idContratoNegativador,
					atualizarContratoNegativadorActionForm, fachada, sessao,
					httpServletRequest);

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			exibirContratoNegativador(idContratoNegativador,
					atualizarContratoNegativadorActionForm, fachada, sessao,
					httpServletRequest);

		}

		return retorno;

	}

	private void exibirContratoNegativador(
			String idNegativadorContrato,
			AtualizarContratoNegativadorActionForm atualizarContratoNegativadorActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// Cria a variável que vai armazenar o ParcelamentoPerfil para ser
		// atualizado
		NegativadorContrato negativadorContrato = null;

		// Cria o filtro de NegativadorContrato e seta o id do
		// NegativadorContrato para ser atualizado no filtro
		// e indica quais objetos devem ser retornados pela pesquisa
		FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		filtroNegativadorContrato.adicionarParametro(new ParametroSimples(
				FiltroNegativadorContrato.ID, idNegativadorContrato));

		filtroNegativadorContrato
				.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");

		Collection<NegativadorContrato> collectionNegativadorContrato = fachada
				.pesquisar(filtroNegativadorContrato, NegativadorContrato.class
						.getName());

		// Caso a pesquisa tenha retornado o NegativadorContrato
		if (collectionNegativadorContrato != null
				&& !collectionNegativadorContrato.isEmpty()) {

			// Recupera da coleção o NegativadorContrato que vai ser atualizado
			negativadorContrato = (NegativadorContrato) Util
					.retonarObjetoDeColecao(collectionNegativadorContrato);

			// dataFim menor que data atual
			Date dataAtual = new Date();
			if (Util.compararData(negativadorContrato.getDataContratoFim(),
					dataAtual) == -1
					|| negativadorContrato.getDataContratoEncerramento() != null) {

				atualizarContratoNegativadorActionForm.setVigente("false");

			} else {
				atualizarContratoNegativadorActionForm.setVigente("true");
			}

			// Seta no form os dados de NegativadorContrato
			if (negativadorContrato.getNegativador() != null
					&& !negativadorContrato.getNegativador().equals("")) {

				atualizarContratoNegativadorActionForm.setIdNegativador(""
						+ negativadorContrato.getNegativador());

				atualizarContratoNegativadorActionForm.setNegativadorCliente(""
						+ negativadorContrato.getNegativador().getCliente()
								.getNome());
			} else {
				atualizarContratoNegativadorActionForm
						.setNegativadorCliente("");
			}

			if (negativadorContrato.getNumeroInclusoesEnviadas() != null
					&& !negativadorContrato.getNumeroInclusoesEnviadas()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesEnviadas(""
								+ negativadorContrato
										.getNumeroInclusoesEnviadas());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesEnviadas("");
			}

			if (negativadorContrato.getNumeroInclusoesContratadas() != null
					&& !negativadorContrato.getNumeroInclusoesContratadas()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesContratadas(""
								+ negativadorContrato
										.getNumeroInclusoesContratadas());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesContratadas("");
			}

			if (negativadorContrato.getNumeroExclusoesEnviadas() != null
					&& !negativadorContrato.getNumeroExclusoesEnviadas()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setNumeroExclusoesEnviadas(""
								+ negativadorContrato
										.getNumeroExclusoesEnviadas());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroExclusoesEnviadas("");
			}

			if (negativadorContrato.getNumeroContrato() != null
					&& !negativadorContrato.getNumeroContrato().equals("")) {

				atualizarContratoNegativadorActionForm.setNumeroContrato(""
						+ negativadorContrato.getNumeroContrato());
			} else {
				atualizarContratoNegativadorActionForm.setNumeroContrato("");
			}

			if (negativadorContrato.getDescricaoEmailEnvioArquivo() != null
					&& !negativadorContrato.getDescricaoEmailEnvioArquivo()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setDescricaoEmailEnvioArquivo(""
								+ negativadorContrato
										.getDescricaoEmailEnvioArquivo());
			} else {
				atualizarContratoNegativadorActionForm
						.setDescricaoEmailEnvioArquivo("");
			}

			if (negativadorContrato.getCodigoConvenio() != null
					&& !negativadorContrato.getCodigoConvenio().equals("")) {

				atualizarContratoNegativadorActionForm.setCodigoConvenio(""
						+ negativadorContrato.getCodigoConvenio());
			} else {
				atualizarContratoNegativadorActionForm.setCodigoConvenio("");
			}

			if (negativadorContrato.getValorContrato() != null
					&& !negativadorContrato.getValorContrato().equals("")) {

				atualizarContratoNegativadorActionForm.setValorContrato(""
						+ negativadorContrato.getValorContrato());
			} else {
				atualizarContratoNegativadorActionForm.setValorContrato("");
			}

			if (negativadorContrato.getValorTarifaInclusao() != null
					&& !negativadorContrato.getValorTarifaInclusao().equals("")) {

				atualizarContratoNegativadorActionForm
						.setValorTarifaInclusao(""
								+ negativadorContrato.getValorTarifaInclusao());
			} else {
				atualizarContratoNegativadorActionForm
						.setValorTarifaInclusao("");
			}

			if (negativadorContrato.getNumeroPrazoInclusao() != 0) {
				atualizarContratoNegativadorActionForm
						.setNumeroPrazoInclusao(""
								+ negativadorContrato.getNumeroPrazoInclusao());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroPrazoInclusao("");
			}

			// Carregar a data corrente do sistema
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			if (negativadorContrato.getDataContratoInicio() != null
					&& !negativadorContrato.getDataContratoInicio().equals("")) {

				atualizarContratoNegativadorActionForm.setDataContratoInicio(""
						+ formatoData.format(negativadorContrato
								.getDataContratoInicio().getTime()));

			} else {
				atualizarContratoNegativadorActionForm
						.setDataContratoInicio("");
			}

			if (negativadorContrato.getDataContratoFim() != null
					&& !negativadorContrato.getDataContratoFim().equals("")) {

				atualizarContratoNegativadorActionForm.setDataContratoFim(""
						+ formatoData.format(negativadorContrato
								.getDataContratoFim().getTime()));

			} else {
				atualizarContratoNegativadorActionForm.setDataContratoFim("");
			}

			if (negativadorContrato.getDataContratoEncerramento() != null
					&& !negativadorContrato.getDataContratoEncerramento()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setDataContratoEncerramento(""
								+ formatoData.format(negativadorContrato
										.getDataContratoEncerramento()
										.getTime()));
			} else {
				atualizarContratoNegativadorActionForm
						.setDataContratoEncerramento("");
			}

			if (negativadorContrato.getContratoMotivoCancelamento() != null
					&& !negativadorContrato.getContratoMotivoCancelamento()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setIdContratoMotivoCancelamento(""
								+ negativadorContrato
										.getContratoMotivoCancelamento()
										.getId());
			} else {
				atualizarContratoNegativadorActionForm
						.setIdContratoMotivoCancelamento("");
			}

			atualizarContratoNegativadorActionForm.setTime(Long
					.toString(negativadorContrato.getUltimaAlteracao()
							.getTime()));

			sessao.setAttribute("negativadorContrato", negativadorContrato);

		}

	}

}