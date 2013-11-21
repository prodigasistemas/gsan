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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
 * @date 05/09/2006
 */
public class ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction
		extends GcomAction {
	/**
	 * Este caso de uso permite a programaçao de abastecimento e manutencao de
	 * uma determinada área de bairro
	 * 
	 * [UC0440] Consultar Programação de Abastecimento e Manutenção
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 05/09/2006
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

		ActionForward retorno = actionMapping.findForward("resultadoDetalhado");

		HttpSession sessao = httpServletRequest.getSession(false);

		ResultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm = (ResultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		/*
		 * Setando os dados do sistema de abastecimento no form, dados vindo da
		 * sessao
		 */

		String sistemaAbastecimento = (String) sessao
				.getAttribute("sistemaAbastecimento");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setSistemaAbastecimento(sistemaAbastecimento);

		String zonaAbastecimento = (String) sessao
				.getAttribute("zonaAbastecimento");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setZonaAbastecimento(zonaAbastecimento);

		String municipio = (String) sessao.getAttribute("municipio");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setMunicipio(municipio);

		String bairro = (String) sessao.getAttribute("bairro");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setBairro(bairro);

		String bairroArea = (String) sessao.getAttribute("bairroArea");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setAreaBairro(bairroArea);

		String codigoBairro = (String) sessao.getAttribute("idBairro");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoBairro(codigoBairro);

		String codigoMunicipio = (String) sessao.getAttribute("idMunicipio");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoMunicipio(codigoMunicipio);

		String codigoSistemaAbastecimento = (String) sessao
				.getAttribute("codigoSistemaAbastecimento");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoSistemaAbastecimento(codigoSistemaAbastecimento);

		String codigoZonaAbastecimento = (String) sessao
				.getAttribute("codigoZonaAbastecimento");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoZonaAbastecimento(codigoZonaAbastecimento);

		String codigoAreaBairro = (String) sessao.getAttribute("areaBairro");

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoAreaBairro(codigoAreaBairro);

		// Recebe a colecao de Abastecimento Programacao pela sessao

		Collection colecaoProgramacaoAbastecimento = null;
		Collection colecaoProgramacaoManutencao = null;
		/*
		 * Recebe o parametro pesquisa caso o usuario tenha voltado sem ter
		 * clicado no botão voltar e executa uma nova pesquisa, caso contrario,
		 * recebe a colecao que vem da action
		 * exibirResultadoConsultarProgramacaoAbastecimentoManutencaoAction
		 */
		if (sessao.getAttribute("pesquisa") != null) {
			sessao.removeAttribute("pesquisa");
			colecaoProgramacaoAbastecimento = (Collection) sessao
					.getAttribute("colecaoProgramacaoAbastecimento");
			colecaoProgramacaoManutencao = (Collection) sessao
					.getAttribute("colecaoProgramacaoManutencao");
		} else {
			String mesAnoReferencia = (String) sessao
					.getAttribute("mesAnoReferencia");
			colecaoProgramacaoAbastecimento = fachada
					.consultarProgramacaoAbastecimento(codigoMunicipio,
							codigoBairro, codigoAreaBairro, mesAnoReferencia);
			colecaoProgramacaoManutencao = fachada
					.consultarProgramacaoManutencao(codigoMunicipio,
							codigoBairro, codigoAreaBairro, mesAnoReferencia);
		}

		// Dados do calendario

		String diaCalendario = httpServletRequest.getParameter("dia");

		// vem mes anterior ao atual
		String mesCalendario = httpServletRequest.getParameter("mes");

		String anoCalendario = httpServletRequest.getParameter("ano");

		Integer dia = new Integer(diaCalendario);

		// Colocando o mes para o atual
		Integer mes = new Integer(mesCalendario) + 1;

		String dataReferencia = null;
		// String dataReferenciaBusca = null;
		Date dataReferenciaFormatada = null;

		// Acrescenta 0 antes do dia se menor que 10

		if (dia < 10) {

			diaCalendario = "0" + dia.toString();

		}

		// acrescenta 0 antes do mes se a data do calendário for menor que 10
		if (mes < 10) {

			dataReferencia = diaCalendario + "/0" + mes.toString() + "/"
					+ anoCalendario;

			String teste = anoCalendario + "0" + mes.toString() + diaCalendario;

			dataReferenciaFormatada = Util
					.converteStringInvertidaSemBarraParaDate(teste);

		} else {
			dataReferencia = diaCalendario + "/" + mes.toString() + "/"
					+ anoCalendario;

			dataReferenciaFormatada = Util
					.converteStringParaDate(dataReferencia);
		}

		resultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm
				.setDataReferencia(dataReferencia);

		if (colecaoProgramacaoAbastecimento != null
				&& !colecaoProgramacaoAbastecimento.isEmpty()) {

			Iterator colecaoProgramacaoAbastecimentoIterator = colecaoProgramacaoAbastecimento
					.iterator();

			AbastecimentoProgramacao abastecimentoProgramacao = null;

			Date dataInicioAbastecimento = null;

			Date dataFimAbastecimento = null;
			/*
			 * Roda a coleçao para pegar o abastecimento programacao
			 * correto(cuja a data selecinada no formulario corresponda a ao
			 * intervalo datainicio e dataFim da coleção), caso nenhum seka o
			 * correto, assume nulo
			 */
			while (colecaoProgramacaoAbastecimentoIterator.hasNext()) {

				abastecimentoProgramacao = (AbastecimentoProgramacao) colecaoProgramacaoAbastecimentoIterator
						.next();

				dataInicioAbastecimento = abastecimentoProgramacao
						.getDataInicio();
				dataFimAbastecimento = abastecimentoProgramacao.getDataFim();

				if (dataReferenciaFormatada.compareTo(dataInicioAbastecimento) >= 0
						&& dataReferenciaFormatada
								.compareTo(dataFimAbastecimento) <= 0) {
					break;
				} else {
					abastecimentoProgramacao = null;
				}

			}

			/*
			 * Caso a data informada esteja no intervalo entre a data de Início
			 * e a data do Fim do abastecimento
			 * 
			 */
			if (abastecimentoProgramacao != null) {

				if ((dataInicioAbastecimento.compareTo(dataReferenciaFormatada) < 0)
						&& ((dataFimAbastecimento
								.compareTo(dataReferenciaFormatada) > 0))) {

					/*
					 * Adiciona na colecaoProgramacaoAbastecimento o intervalo
					 * de horas 00:00 ate 23:59 para os dia no intervalo entre o
					 * dia de inicio e do fim Abastecimento
					 * 
					 */

					abastecimentoProgramacao
							.setDataInicio(dataReferenciaFormatada);
					abastecimentoProgramacao
							.setDataFim(dataReferenciaFormatada);

					Calendar horaInicio = Calendar.getInstance();
					horaInicio.setTime(dataReferenciaFormatada);
					horaInicio.set(Calendar.HOUR_OF_DAY, 00);
					horaInicio.set(Calendar.MINUTE, 0);
					horaInicio.set(Calendar.SECOND, 00);
					horaInicio.set(Calendar.MILLISECOND, 01);

					abastecimentoProgramacao
							.setHoraInicio(horaInicio.getTime());

					Calendar horaFim = Calendar.getInstance();
					horaFim.setTime(dataReferenciaFormatada);
					horaFim.set(Calendar.HOUR_OF_DAY, 23);
					horaFim.set(Calendar.MINUTE, 59);
					horaFim.set(Calendar.SECOND, 59);
					horaFim.set(Calendar.MILLISECOND, 59);

					abastecimentoProgramacao.setHoraFim(horaFim.getTime());

					colecaoProgramacaoAbastecimento
							.removeAll(colecaoProgramacaoAbastecimento);

					colecaoProgramacaoAbastecimento
							.add(abastecimentoProgramacao);

					httpServletRequest.setAttribute(
							"colecaoProgramacaoAbastecimento",
							colecaoProgramacaoAbastecimento);

				}
				/*
				 * Se o dia do início do abastecimento for igual ao dia do fim
				 * do abastecimento
				 */

				if ((dataInicioAbastecimento.compareTo(dataFimAbastecimento) == 0)
						&& (dataInicioAbastecimento
								.compareTo(dataReferenciaFormatada) == 0)
						&& (dataInicioAbastecimento == dataFimAbastecimento)) {
					httpServletRequest.setAttribute(
							"colecaoProgramacaoAbastecimento",
							colecaoProgramacaoAbastecimento);

					sessao.removeAttribute("colecaoProgramacaoAbastecimento");

				}

				/*
				 * Caso o dia de Início seja diferente do fim, verifica as
				 * seguintes condicoes: 1- Caso o dia informado seja o dia de
				 * início, altera a hora do fim para 23:59:59. 2- Caso o dia
				 * informado seja o dia do fim, altera a hora de início para
				 * 24:00:00
				 */

				if (dataInicioAbastecimento.compareTo(dataFimAbastecimento) != 0) {

					if ((dataInicioAbastecimento
							.compareTo(dataReferenciaFormatada) == 0)) {
						Calendar horaFim = Calendar.getInstance();
						horaFim.setTime(dataReferenciaFormatada);
						horaFim.set(Calendar.HOUR_OF_DAY, 23);
						horaFim.set(Calendar.MINUTE, 59);
						horaFim.set(Calendar.SECOND, 59);
						horaFim.set(Calendar.MILLISECOND, 59);

						abastecimentoProgramacao.setHoraFim(horaFim.getTime());

						colecaoProgramacaoAbastecimento
								.removeAll(colecaoProgramacaoAbastecimento);

						colecaoProgramacaoAbastecimento
								.add(abastecimentoProgramacao);

						httpServletRequest.setAttribute(
								"colecaoProgramacaoAbastecimento",
								colecaoProgramacaoAbastecimento);

						sessao
								.removeAttribute("colecaoProgramacaoAbastecimento");

					} else if (dataFimAbastecimento
							.compareTo(dataReferenciaFormatada) == 0) {

						Calendar horaInicio = Calendar.getInstance();
						horaInicio.setTime(dataReferenciaFormatada);
						horaInicio.set(Calendar.HOUR_OF_DAY, 00);
						horaInicio.set(Calendar.MINUTE, 0);
						horaInicio.set(Calendar.SECOND, 00);
						horaInicio.set(Calendar.MILLISECOND, 01);

						abastecimentoProgramacao.setHoraInicio(horaInicio
								.getTime());

						colecaoProgramacaoAbastecimento
								.removeAll(colecaoProgramacaoAbastecimento);

						colecaoProgramacaoAbastecimento
								.add(abastecimentoProgramacao);

						httpServletRequest.setAttribute(
								"colecaoProgramacaoAbastecimento",
								colecaoProgramacaoAbastecimento);

						sessao
								.removeAttribute("colecaoProgramacaoAbastecimento");

					}
				}
				if ((dataInicioAbastecimento.compareTo(dataReferenciaFormatada) > 0)
						&& (dataFimAbastecimento
								.compareTo(dataReferenciaFormatada) > 0)
						&& (dataInicioAbastecimento != dataFimAbastecimento)) {

					httpServletRequest.setAttribute(
							"colecaoProgramacaoAbastecimento",
							colecaoProgramacaoAbastecimento);
				}
				if ((dataInicioAbastecimento.compareTo(dataReferenciaFormatada) < 0)
						&& (dataFimAbastecimento
								.compareTo(dataReferenciaFormatada) < 0)
						&& (dataInicioAbastecimento != dataFimAbastecimento)) {

					httpServletRequest.setAttribute(
							"colecaoProgramacaoAbastecimento",
							colecaoProgramacaoAbastecimento);
				}

			} else {
				colecaoProgramacaoAbastecimento = new ArrayList();

				httpServletRequest.setAttribute(
						"colecaoProgramacaoAbastecimento",
						colecaoProgramacaoAbastecimento);

			}
		} else {
			colecaoProgramacaoAbastecimento = new ArrayList();

			httpServletRequest.setAttribute("colecaoProgramacaoAbastecimento",
					colecaoProgramacaoAbastecimento);
		}

		if (colecaoProgramacaoManutencao != null
				&& !colecaoProgramacaoManutencao.isEmpty()) {
			// Manutenção

			Iterator colecaoProgramacaoManutencaoIterator = colecaoProgramacaoManutencao
					.iterator();

			ManutencaoProgramacao manutencaoProgramacao = null;

			Date dataInicioManutencao = null;

			Date dataFimManutencao = null;

			/*
			 * Roda a coleçao para pegar o manutenção programacao correto(cuja a
			 * data selecinada no formulario corresponda a ao intervalo
			 * datainicio e dataFim da coleção), caso nenhum seka o correto,
			 * assume nulo
			 */

			while (colecaoProgramacaoManutencaoIterator.hasNext()) {

				manutencaoProgramacao = (ManutencaoProgramacao) colecaoProgramacaoManutencaoIterator
						.next();

				dataInicioManutencao = manutencaoProgramacao.getDataInicio();
				dataFimManutencao = manutencaoProgramacao.getDataFim();

				if (dataReferenciaFormatada.compareTo(dataInicioManutencao) >= 0
						&& dataReferenciaFormatada.compareTo(dataFimManutencao) <= 0) {
					break;
				} else {
					manutencaoProgramacao = null;
				}

			}

			if (manutencaoProgramacao != null) {

				if ((dataInicioManutencao.compareTo(dataReferenciaFormatada) < 0)
						&& ((dataFimManutencao
								.compareTo(dataReferenciaFormatada) > 0))) {

					/*
					 * Adiciona na colecaoProgramacaoAbastecimento o intervalo
					 * de horas 00:00 ate 23:59 para os dia no intervalo entre o
					 * dia de inicio e do fim Abastecimento
					 * 
					 */

					manutencaoProgramacao
							.setDataInicio(dataReferenciaFormatada);
					manutencaoProgramacao.setDataFim(dataReferenciaFormatada);

					Calendar horaInicio = Calendar.getInstance();
					horaInicio.setTime(dataReferenciaFormatada);
					horaInicio.set(Calendar.HOUR_OF_DAY, 00);
					horaInicio.set(Calendar.MINUTE, 0);
					horaInicio.set(Calendar.SECOND, 00);
					horaInicio.set(Calendar.MILLISECOND, 01);

					manutencaoProgramacao.setHoraInicio(horaInicio.getTime());

					Calendar horaFim = Calendar.getInstance();
					horaFim.setTime(dataReferenciaFormatada);
					horaFim.set(Calendar.HOUR_OF_DAY, 23);
					horaFim.set(Calendar.MINUTE, 59);
					horaFim.set(Calendar.SECOND, 59);
					horaFim.set(Calendar.MILLISECOND, 59);

					manutencaoProgramacao.setHoraFim(horaFim.getTime());

					colecaoProgramacaoManutencao
							.removeAll(colecaoProgramacaoManutencao);

					colecaoProgramacaoManutencao.add(manutencaoProgramacao);

					httpServletRequest.setAttribute(
							"colecaoProgramacaoManutencao",
							colecaoProgramacaoManutencao);

				}
				/*
				 * Se o dia do início da manutencao for igual ao dia do fim do
				 * manutencao
				 */

				if ((dataInicioManutencao.compareTo(dataFimManutencao) == 0)) {
					httpServletRequest.setAttribute(
							"colecaoProgramacaoManutencao",
							colecaoProgramacaoManutencao);

					sessao.removeAttribute("colecaoProgramacaoManutencao");
				}

				/*
				 * Caso o dia de Início seja diferente do fim, verifica as
				 * seguintes condicoes: 1- Caso o dia informado seja o dia de
				 * início, altera a hora do fim para 23:59:59. 2- Caso o dia
				 * informado seja o dia do fim, altera a hora de início para
				 * 24:00:00
				 */

				if (dataInicioManutencao.compareTo(dataFimManutencao) != 0) {

					if ((dataInicioManutencao
							.compareTo(dataReferenciaFormatada) == 0)) {
						Calendar horaFim = Calendar.getInstance();
						horaFim.setTime(dataReferenciaFormatada);
						horaFim.set(Calendar.HOUR_OF_DAY, 23);
						horaFim.set(Calendar.MINUTE, 59);
						horaFim.set(Calendar.SECOND, 59);
						horaFim.set(Calendar.MILLISECOND, 59);

						manutencaoProgramacao.setHoraFim(horaFim.getTime());

						colecaoProgramacaoManutencao
								.removeAll(colecaoProgramacaoManutencao);

						colecaoProgramacaoManutencao.add(manutencaoProgramacao);

						httpServletRequest.setAttribute(
								"colecaoProgramacaoManutencao",
								colecaoProgramacaoManutencao);

						sessao.removeAttribute("colecaoProgramacaoManutencao");

					}

					if (dataFimManutencao.compareTo(dataReferenciaFormatada) == 0) {

						Calendar horaInicio = Calendar.getInstance();
						horaInicio.setTime(dataReferenciaFormatada);
						horaInicio.set(Calendar.HOUR_OF_DAY, 00);
						horaInicio.set(Calendar.MINUTE, 0);
						horaInicio.set(Calendar.SECOND, 00);
						horaInicio.set(Calendar.MILLISECOND, 01);

						manutencaoProgramacao.setHoraInicio(horaInicio
								.getTime());

						colecaoProgramacaoManutencao
								.removeAll(colecaoProgramacaoManutencao);

						colecaoProgramacaoManutencao.add(manutencaoProgramacao);

						httpServletRequest.setAttribute(
								"colecaoProgramacaoManutencao",
								colecaoProgramacaoManutencao);

						sessao.removeAttribute("colecaoProgramacaoManutencao");

					}

					if ((dataInicioManutencao
							.compareTo(dataReferenciaFormatada) < 0)
							&& (dataFimManutencao
									.compareTo(dataReferenciaFormatada) < 0)
							&& (dataInicioManutencao != dataFimManutencao)) {

						httpServletRequest.setAttribute(
								"colecaoProgramacaoManutencao",
								colecaoProgramacaoManutencao);
					}

					if ((dataInicioManutencao
							.compareTo(dataReferenciaFormatada) > 0)
							&& (dataFimManutencao
									.compareTo(dataReferenciaFormatada) > 0)
							&& (dataInicioManutencao != dataFimManutencao)) {

						httpServletRequest.setAttribute(
								"colecaoProgramacaoManutencao",
								colecaoProgramacaoManutencao);
					}

				}

			} else {
				colecaoProgramacaoManutencao = new ArrayList();

				httpServletRequest.setAttribute("colecaoProgramacaoManutencao",
						colecaoProgramacaoManutencao);

			}
		} else {
			colecaoProgramacaoManutencao = new ArrayList();

			httpServletRequest.setAttribute("colecaoProgramacaoManutencao",
					colecaoProgramacaoManutencao);

		}

		return retorno;
	}
}
