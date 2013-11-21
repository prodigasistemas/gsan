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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 1 de Julho de 2004
 */
public class AtualizarConsumosResumoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarConsumoResumo");

		LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		/*
		 * [UC0107] Registrar Transação
		 */

		/*
		 * RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		 * Operacao.OPERACAO_BAIRRO_ATUALIZAR, new
		 * UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
		 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		 */

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação

		String idImovel = (String) sessao.getAttribute("idImovel");

		// String dataLeituraAnteriorInformada =
		// httpServletRequest.getParameter("dataLeituraAnteriorFaturamento");
		String dataLeituraAnteriorInformada = leituraConsumoActionForm
				.getDataLeituraAnteriorFaturamento();

		// String leituraAnterior =
		// httpServletRequest.getParameter("leituraAnteriorFaturamento");
		String leituraAnterior = leituraConsumoActionForm
				.getLeituraAnteriorFaturamento();

		// String dataLeituraAtualInformada =
		// httpServletRequest.getParameter("dataLeituraAtualInformada");
		String dataLeituraAtualInformada = leituraConsumoActionForm
				.getDataLeituraAtualInformada();

		// String leituraAtual =
		// httpServletRequest.getParameter("leituraAtualInformada");
		String leituraAtual = leituraConsumoActionForm
				.getLeituraAtualInformada();

		// String consumoInformado =
		// httpServletRequest.getParameter("consumoInformado");
		String consumoInformado = leituraConsumoActionForm
				.getConsumoInformado();

		// String indicadorConfirmacao =
		// httpServletRequest.getParameter("confirmacao");
		String indicadorConfirmacao = leituraConsumoActionForm.getConfirmacao();

		Integer idMotivoInterferenciaTipo = leituraConsumoActionForm
				.getMotivoInterferenciaTipo();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) sessao
				.getAttribute("faturamentoGrupo");

		if (indicadorConfirmacao == null
				|| indicadorConfirmacao.trim().equals("")) {
			indicadorConfirmacao = "0";
		}
		String tipoMedicao = (String) sessao.getAttribute("tipoMedicao");

		String idAnormalidadeLeitura = leituraConsumoActionForm
				.getIdAnormalidade();

		String tipoMedicaoSelecionada = (String) sessao
				.getAttribute("tipoMedicao");

		ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) sessao
				.getAttribute("imovelMicromedicaoDadosResumo");

		Imovel imovel = imovelMicromedicao.getImovel();

		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) sessao
				.getAttribute("medicaoHistoricoAnoMesAtual");

		// Colocado por Rômulo Aurélio Data:22/10/2008 Solicitado por: Henrique
		// Pinheiro
		// Verifica se o usuario alterou a anormalidade

		boolean alterouAnormalidade = false;

		if (medicaoHistorico.getLeituraAnormalidadeFaturamento() != null) {

			if (medicaoHistorico.getLeituraAnormalidadeFaturamento().getId() != null
					&& idAnormalidadeLeitura != null && !idAnormalidadeLeitura.equals("")) {
				if (!medicaoHistorico.getLeituraAnormalidadeFaturamento()
						.getId().toString()
						.equalsIgnoreCase(idAnormalidadeLeitura)) {

					alterouAnormalidade = true;
				}
			} else if (medicaoHistorico.getLeituraAnormalidadeFaturamento()
					.getId() != null && idAnormalidadeLeitura == null) {

				alterouAnormalidade = true;

			} else if (medicaoHistorico.getLeituraAnormalidadeFaturamento().getId() == null 
					&& idAnormalidadeLeitura != null && !idAnormalidadeLeitura.equals("")) {

				alterouAnormalidade = true;
			}
		} else if (medicaoHistorico.getLeituraAnormalidadeFaturamento() == null
				&& idAnormalidadeLeitura != null && !idAnormalidadeLeitura.equals("")) {

			alterouAnormalidade = true;
		}

		// ---------------------------------------

		LeituraSituacao leituraSituacao = new LeituraSituacao();

		// testa para ver se o tipo de medicao foi alterado
		if (tipoMedicao != null && tipoMedicaoSelecionada != null) {
			FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
			filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
					FiltroMedicaoTipo.ID, tipoMedicao));
			// Collection colecaoTipomedicao = fachada.pesquisar(
			// filtroMedicaoTipo, MedicaoTipo.class.getName());
			// MedicaoTipo medicaoTipo = (MedicaoTipo) Util
			// .retonarObjetoDeColecao(colecaoTipomedicao);
		}

		if (imovel != null) {

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			Date dataLeituraAtual = null;
			Date dataLeituraAnterior = null;

			String anoMesReferencia = ""
					+ medicaoHistorico.getAnoMesReferencia();

			if (imovel.getHidrometroInstalacaoHistorico() != null
					|| (imovel.getLigacaoAgua() != null && imovel
							.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null)) {

				if (dataLeituraAtualInformada != null
						&& !dataLeituraAtualInformada.equals("")) {

					try {
						dataLeituraAtual = dataFormatada
								.parse(dataLeituraAtualInformada);
					} catch (ParseException ex) {
						throw new ActionServletException("erro.sistema");
					}
					Calendar dataAtual = new GregorianCalendar();
					dataAtual.setTime(dataLeituraAtual);

					String anoMesAtual = "" + dataAtual.get(Calendar.YEAR);
					String mes = "" + (dataAtual.get(Calendar.MONTH) + 1);

					if (!(mes.length() == 2)) {
						mes = "0" + mes;
					}

					anoMesAtual += mes;

					/*
					 * String anoMesReferenciaMaisUmMes = "" +
					 * Util.somarData(medicaoHistorico .getAnoMesReferencia());
					 */

					// Comparando a data atual informada no form com o ano
					// mês
					// referência e com o ano mês seguinte
					/*
					 * if (!((Util.compararAnoMesReferencia(new Integer(
					 * anoMesReferencia), new Integer(anoMesAtual), "=")) ||
					 * (Util.compararAnoMesReferencia( new
					 * Integer(anoMesReferenciaMaisUmMes), new
					 * Integer(anoMesAtual), "=")))) { sessao
					 * .setAttribute("nomeCampo", "dataLeituraAtual"); throw new
					 * ActionServletException(
					 * "atencao.faturamento_data_incompativel", null,
					 * "Data de Leitura Atual Informada"); }
					 */
					boolean mesAnoValido = fachada
							.validaDataFaturamentoIncompativel(
									anoMesReferencia, anoMesAtual);
					if (!mesAnoValido) {
						sessao.setAttribute("nomeCampo", "dataLeituraAtual");
						throw new ActionServletException(
								"atencao.faturamento_data_incompativel", null,
								"Data de Leitura Atual Informada");
					}

					if (dataLeituraAnteriorInformada != null
							&& !dataLeituraAnteriorInformada.equals("")) {
						try {
							if (Util.validarDiaMesAno(dataLeituraAnteriorInformada)) {
								throw new ActionServletException(
										"atencao.data.invalida", null,
										"Data Anterior");
							}
							dataLeituraAnterior = dataFormatada
									.parse(dataLeituraAnteriorInformada);
						} catch (ParseException ex) {
							throw new ActionServletException("erro.sistema");
						}
						Calendar dataAnterior = new GregorianCalendar();
						dataAnterior.setTime(dataLeituraAnterior);

						// Comparando a data atual informada com a data
						// anterior
						// informada
						// Garantindo que a data atual seja posterior a data
						// anterior
						if (dataAtual.compareTo(dataAnterior) < 0) {
							sessao.setAttribute("nomeCampo", "dataLeituraAtual");
							throw new ActionServletException(
									"atencao.faturamento_data_atual_inferior_data_anterior");
						}
					}
					medicaoHistorico
							.setDataLeituraAtualFaturamento(dataLeituraAtual);
					medicaoHistorico
							.setDataLeituraAtualInformada(dataLeituraAtual);
				}

				if (dataLeituraAnteriorInformada != null
						&& !dataLeituraAnteriorInformada.equals("")) {
					try {
						if (Util.validarDiaMesAno(dataLeituraAnteriorInformada)) {
							throw new ActionServletException(
									"atencao.data.invalida", null,
									"Data Anterior");
						}
						dataLeituraAnterior = dataFormatada
								.parse(dataLeituraAnteriorInformada);
					} catch (ParseException ex) {
						throw new ActionServletException("erro.sistema");
					}
					Calendar dataAnterior = new GregorianCalendar();
					dataAnterior.setTime(dataLeituraAnterior);
					String anoMesAnterior = ""
							+ dataAnterior.get(Calendar.YEAR);
					String mesAnterior = ""
							+ (dataAnterior.get(Calendar.MONTH) + 1);

					if (!(mesAnterior.length() == 2)) {
						mesAnterior = "0" + mesAnterior;
					}

					anoMesAnterior += mesAnterior;

					boolean anoMesInferiorValido = fachada
							.validaDataFaturamentoIncompativelInferior(
									anoMesReferencia, anoMesAnterior);
					if (!anoMesInferiorValido) {
						sessao.setAttribute("nomeCampo", "dataLeituraAnterior");

						throw new ActionServletException(
								"atencao.faturamento_data_incompativel", null,
								"Data de Leitura Anterior de Faturamento");
					}
					/*
					 * String anoMesReferenciaMenosUmMes = "" +
					 * Util.subtrairData(medicaoHistorico
					 * .getAnoMesReferencia());
					 * 
					 * // Comparando a data anterior faturada no form com o ano
					 * // mês // referência e com o ano mês anterior if
					 * (!((Util.compararAnoMesReferencia(new Integer(
					 * anoMesReferencia), new Integer(anoMesAnterior), "=")) ||
					 * (Util.compararAnoMesReferencia( new
					 * Integer(anoMesReferenciaMenosUmMes), new
					 * Integer(anoMesAnterior), "=")))) {
					 * 
					 * sessao.setAttribute("nomeCampo", "dataLeituraAnterior");
					 * 
					 * throw new ActionServletException(
					 * "atencao.faturamento_data_incompativel", null,
					 * "Data de Leitura Anterior de Faturamento"); }
					 */

					medicaoHistorico
							.setDataLeituraAnteriorFaturamento(dataLeituraAnterior);

				}

				if (((imovel.getHidrometroInstalacaoHistorico() == null ? imovel
						.getLigacaoAgua().getHidrometroInstalacaoHistorico()
						.getHidrometro().getNumeroDigitosLeitura().intValue()
						: imovel.getHidrometroInstalacaoHistorico()
								.getHidrometro().getNumeroDigitosLeitura()
								.intValue()) >= leituraAtual.length())
						&& ((imovel.getHidrometroInstalacaoHistorico() == null ? imovel
								.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico()
								.getHidrometro().getNumeroDigitosLeitura()
								.intValue()
								: imovel.getHidrometroInstalacaoHistorico()
										.getHidrometro()
										.getNumeroDigitosLeitura().intValue()) >= leituraAnterior
								.length())) {

					if (!("" + medicaoHistorico.getLeituraAnteriorFaturamento())
							.equals(leituraAnterior)) {
						LeituraSituacao leituraSituacaoAnterior = new LeituraSituacao();
						leituraSituacaoAnterior
								.setId(LeituraSituacao.LEITURA_ALTERADA);
						medicaoHistorico
								.setLeituraSituacaoAnterior(leituraSituacaoAnterior);
					}

					medicaoHistorico.setLeituraAnteriorFaturamento(new Integer(
							leituraAnterior));

				} else {

					sessao.setAttribute("nomeCampo", "leituraAnterior");

					throw new ActionServletException(
							"atencao.digitos.leitura.maior.hidrometro");
				}

				if (idAnormalidadeLeitura != null
						&& !idAnormalidadeLeitura.equals("")) {
					FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
					filtroLeituraAnormalidade
							.adicionarParametro(new ParametroSimples(
									FiltroLeituraAnormalidade.ID,
									idAnormalidadeLeitura));

					Collection anormalidadeLeituraEncontrada = fachada
							.pesquisar(filtroLeituraAnormalidade,
									LeituraAnormalidade.class.getName());
					if (anormalidadeLeituraEncontrada != null
							&& !anormalidadeLeituraEncontrada.isEmpty()) {

						medicaoHistorico
								.setLeituraAnormalidadeInformada(((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
										.get(0)));
						medicaoHistorico
								.setLeituraAnormalidadeFaturamento(((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
										.get(0)));

					} else {

						sessao.setAttribute("nomeCampo", "idAnormalidade");

						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Anormalidade de Leitura");
					}

				} else {
					medicaoHistorico.setLeituraAnormalidadeInformada(null);
					medicaoHistorico.setLeituraAnormalidadeFaturamento(null);
				}

				// Testando se o número de dígitos do hidrometro é menor que
				// o
				// número de dígitos da leitura
				if ((imovel.getHidrometroInstalacaoHistorico() == null ? imovel
						.getLigacaoAgua().getHidrometroInstalacaoHistorico()
						.getHidrometro().getNumeroDigitosLeitura().intValue()
						: imovel.getHidrometroInstalacaoHistorico()
								.getHidrometro().getNumeroDigitosLeitura()
								.intValue()) >= leituraAtual.length()) {

					medicaoHistorico.setLeituraAtualInformada(leituraAtual
							.equals("") ? null : new Integer(leituraAtual));

				} else {

					sessao.setAttribute("nomeCampo", "leituraAtual");

					throw new ActionServletException(
							"atencao.digitos.leitura.maior.hidrometro");
				}

				if (!leituraAtual.equals("")
						&& new Integer(leituraAtual).intValue() == 0) {
					leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);

				} else {

					if (indicadorConfirmacao
							.equals(ConstantesSistema.CONFIRMADA)) {

						leituraSituacao.setId(LeituraSituacao.CONFIRMADA);

					} else {

						leituraSituacao.setId(LeituraSituacao.REALIZADA);

					}

				}

				medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);
				if (consumoInformado != null
						&& !consumoInformado.equalsIgnoreCase("")) {
					medicaoHistorico.setNumeroConsumoInformado(new Integer(
							consumoInformado));

				}

			} else {
				if (idAnormalidadeLeitura != null
						&& !idAnormalidadeLeitura.equals("")) {
					FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
					filtroLeituraAnormalidade
							.adicionarParametro(new ParametroSimples(
									FiltroLeituraAnormalidade.ID,
									idAnormalidadeLeitura));

					Collection anormalidadeLeituraEncontrada = fachada
							.pesquisar(filtroLeituraAnormalidade,
									LeituraAnormalidade.class.getName());

					if (anormalidadeLeituraEncontrada != null
							&& !anormalidadeLeituraEncontrada.isEmpty()) {

						// Comparar a anormalidade
						if (((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
								.get(0)).getIndicadorImovelSemHidrometro()
								.equals(new Short("2"))) {

							sessao.setAttribute("nomeCampo", "idAnormalidade");

							throw new ActionServletException(
									"atencao.leitura.anormalidade.nao.permitida");

						}
						imovel.setLeituraAnormalidade((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
								.get(0));
					} else {

						sessao.setAttribute("nomeCampo", "idAnormalidade");

						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Anormalidade de Leitura");
					}

				} else {
					imovel.setLeituraAnormalidade(null);
					imovel.setUltimaAlteracao(new Date());
				}

			}
			// Atualizar
			boolean ligacaoAgua = (Boolean) sessao.getAttribute("ligacaoAgua");

			Integer idLeituraAnormalidade = null;
			if (idAnormalidadeLeitura != null
					&& !idAnormalidadeLeitura.trim().equals("")) {
				idLeituraAnormalidade = new Integer(idAnormalidadeLeitura);
			}

			// /////////////////////////////////////////////////////////////////////////////////
			// [FS0007] Validar Consumo Informado do Mês
			String confirmado = httpServletRequest.getParameter("confirmado");

			if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {

				int consumo = 0;

				int consumoMedioMes = 0;
				if (leituraConsumoActionForm.getConsumoMedido() != null
						&& !leituraConsumoActionForm.getConsumoMedido().equals(
								"")) {
					consumoMedioMes = new Integer(
							leituraConsumoActionForm.getConsumoMedido());
				}

				imovel.setId(new Integer(idImovel));

				int qtdeEconomias = fachada.obterQuantidadeEconomias(imovel);

				/**
				 * TODO : COSANPA Alterando o cálculo da média
				 */
				boolean houveIntslacaoHidrometro = false;
				try {
					MedicaoTipo medicao = new MedicaoTipo();

					if (tipoMedicao != null) {
						medicao.setId(new Integer(tipoMedicao));
						houveIntslacaoHidrometro = fachada
								.verificarInstalacaoSubstituicaoHidrometro(
										imovel.getId(), medicao);
					}
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				int idLigacaoAgua = fachada.verificarTipoLigacao(imovel);
				int[] consumoMedioImovel = fachada.obterVolumeMedioAguaEsgoto(
						imovel.getId(),
						sistemaParametro.getAnoMesFaturamento(), idLigacaoAgua,
						houveIntslacaoHidrometro);

				if (consumoMedioImovel != null && consumoMedioImovel.length > 0) {

					if (consumoInformado != null
							&& !consumoInformado.equals("")) {
						consumo = new Integer(consumoInformado);
					}

					int consumoMedio = consumoMedioImovel[0];
					int consumoMedio5 = consumoMedio * 5;
					int qtdeEconomias30 = qtdeEconomias * 30;
					if (consumo > consumoMedio5 && consumo > qtdeEconomias30
							&& consumo > consumoMedioMes) {
						httpServletRequest.setAttribute(
								"caminhoActionConclusao",
								"/gsan/atualizarConsumoResumoAction.do");
						// Monta a página de confirmação para perguntar se o
						// usuário
						// quer aceitar o consumo informado superior a cinco
						// vezes ao
						// consumo médio do imóvel e superior a trinta vezes a
						// quantidade de economias
						// e superior ao consumo médio

						return montarPaginaConfirmacao(
								"atencao.invalido.consumo", httpServletRequest,
								actionMapping, null, null);
					}
				}

			}
			// //////////////////////////////////////////////////////////////////////////////
			Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

			/*
			 * Alteração de acordo com a solicitacao da cosanpa. Realizada por
			 * Tiago Moreno - 22/10/10
			 */
			MotivoInterferenciaTipo motivoInterferenciaTipo = new MotivoInterferenciaTipo();

			if (idMotivoInterferenciaTipo != null
					&& idMotivoInterferenciaTipo > 0) {
				motivoInterferenciaTipo.setId(idMotivoInterferenciaTipo);
			}

			// CARREGANDO OS DADOS DO USUÁRIO QUE EFETUOU A ALTERAÇÃO
			Integer index = null;

			if (sessao.getAttribute("index") != null) {
				index = (Integer) sessao.getAttribute("index");
			}

			Collection colecaoIdsImovel = (Collection) sessao
					.getAttribute("colecaoIdsImovelTotal");

			if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()
					&& index != null) {

				((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index))
						.getMedicaoHistorico().setUsuarioAlteracao(
								usuarioLogado);
			}

			if(leituraSituacao.getId().equals(LeituraSituacao.CONFIRMADA)){
				fachada.calcularLeituraConfirmada(new Integer(leituraAnterior), new Integer(leituraAtual), leituraSituacao, 
						new Integer(idImovel), medicaoHistorico.getAnoMesReferencia(), faturamentoGrupo, sistemaParametro,
						dataLeituraAtualInformada, idLeituraAnormalidade, alterouAnormalidade);
			} else {

				fachada.atualizarLeituraConsumoResumido(new Integer(idImovel),
						medicaoHistorico.getMesAno().toString(),
						dataLeituraAnteriorInformada, leituraAnterior,
						dataLeituraAtualInformada, leituraAtual, consumoInformado,
						ligacaoAgua, idLeituraAnormalidade, /*
															 * new Integer(
															 * indicadorConfirmacao)
															 */leituraSituacao
								.getId(), faturamentoGrupo, usuarioLogado,
						alterouAnormalidade, motivoInterferenciaTipo, new Integer(
								tipoMedicao));
			}
			

			httpServletRequest.setAttribute("sucesso", true);

		}

		return retorno;
	}

}
