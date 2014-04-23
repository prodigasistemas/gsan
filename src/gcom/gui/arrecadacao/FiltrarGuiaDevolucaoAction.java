package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesColecao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterGuiaDevolucao");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarGuiaDevolucaoActionForm filtrarGuiaDevolucaoActionForm = (FiltrarGuiaDevolucaoActionForm) actionForm;

		// Recupera os parâmetros do form
		String idImovel =  filtrarGuiaDevolucaoActionForm.getIdImovelHidden();
		String idCliente =  filtrarGuiaDevolucaoActionForm
				.getIdClienteHidden();
		String idTipoRelacao =  filtrarGuiaDevolucaoActionForm
				.getClienteRelacaoTipo();
		String periodoArrecadacaoInicial =  filtrarGuiaDevolucaoActionForm
				.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFinal =  filtrarGuiaDevolucaoActionForm
				.getPeriodoArrecadacaoFim();
		String periodoGuiaInicial =  filtrarGuiaDevolucaoActionForm
				.getPeriodoGuiaInicio();
		String periodoGuiaFinal =  filtrarGuiaDevolucaoActionForm
				.getPeriodoGuiaFim();
		String dataEmissaoInicial =  filtrarGuiaDevolucaoActionForm
				.getDataEmissaoInicio();
		String dataEmissaoFinal =  filtrarGuiaDevolucaoActionForm
				.getDataEmissaoFim();
		String dataValidadeInicial =  filtrarGuiaDevolucaoActionForm
				.getDataValidadeInicio();
		String dataValidadeFinal =  filtrarGuiaDevolucaoActionForm
				.getDataValidadeFim();
		String[] idsCreditosTipos =  filtrarGuiaDevolucaoActionForm
				.getCreditoTipo();
		String[] idsDocumentosTipos =  filtrarGuiaDevolucaoActionForm
				.getDocumentoTipo();
		String[] idsTipoDebito =  filtrarGuiaDevolucaoActionForm
				.getIdTipoDebitoSelecionados();
//		String atualizar = filtrarGuiaDevolucaoActionForm.getAtualizar();
//
//		if (atualizar != null && atualizar.equals("1")) {
//			httpServletRequest.setAttribute("atualizar", atualizar);
//		}

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		Integer anoMesFaturamento = sistemaParametro.getAnoMesFaturamento();

		// cria o filtro para Tabela Auxiliar
		FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();

		boolean peloMenosUmParametroInformado = false;

		// Período Arrecadação

		if ((periodoArrecadacaoInicial != null && !periodoArrecadacaoInicial
				.equals(""))
				|| ((periodoArrecadacaoFinal != null && !periodoArrecadacaoFinal
						.equals("")))) {
			peloMenosUmParametroInformado = true;

			if ((periodoArrecadacaoInicial != null && !periodoArrecadacaoInicial
					.trim().equals(""))
					&& Util.validarAnoMes(periodoArrecadacaoInicial)) {
				throw new ActionServletException(
						"atencao.periodo.arrecadacao.invalido", null, "Inicial");
			}

			if ((periodoArrecadacaoFinal != null && !periodoArrecadacaoFinal
					.trim().equals(""))
					&& Util.validarAnoMes(periodoArrecadacaoFinal)) {
				throw new ActionServletException(
						"atencao.periodo.arrecadacao.invalido", null, "Final");
			}

			String periodoArrecadacaoInicialFormatado = "";
			if (periodoArrecadacaoInicial != null
					&& !periodoArrecadacaoInicial.trim().equals("")) {
				periodoArrecadacaoInicialFormatado = Util
						.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicial);
			}

			String periodoArrecadacaoFinalFormatado = "";
			if (periodoArrecadacaoFinal != null
					&& !periodoArrecadacaoFinal.trim().equals("")) {
				periodoArrecadacaoFinalFormatado = Util
						.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFinal);
			}

			if ((!periodoArrecadacaoFinalFormatado.equals(""))
					&& Util.compararAnoMesReferencia(new Integer(
							periodoArrecadacaoFinalFormatado),
							anoMesFaturamento, ">")) {
				throw new ActionServletException(
						"atencao.ano.mes.referencia.posterior.ano.mes.faturamento",
						"Arrecadação", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if ((!periodoArrecadacaoInicialFormatado.equals(""))
					&& Util.compararAnoMesReferencia(new Integer(
							periodoArrecadacaoInicialFormatado),
							anoMesFaturamento, ">")) {
				throw new ActionServletException(
						"atencao.ano.mes.referencia.posterior.ano.mes.faturamento",
						"Arrecadação", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if ((periodoArrecadacaoInicial != null && !periodoArrecadacaoInicial
					.equals(""))
					&& ((periodoArrecadacaoFinal != null && !periodoArrecadacaoFinal
							.equals("")))) {
				filtroGuiaDevolucao.adicionarParametro(new Intervalo(
						FiltroGuiaDevolucao.ANO_MES_REFERENCIA_ARRECADACAO,
						periodoArrecadacaoInicialFormatado,
						periodoArrecadacaoFinalFormatado));
			} else if ((periodoArrecadacaoInicial != null && !periodoArrecadacaoInicial
					.equals(""))) {
				filtroGuiaDevolucao.adicionarParametro(new Intervalo(
						FiltroGuiaDevolucao.ANO_MES_REFERENCIA_ARRECADACAO,
						periodoArrecadacaoInicialFormatado, anoMesFaturamento));
			} else {
				filtroGuiaDevolucao.adicionarParametro(new MenorQue(
						FiltroGuiaDevolucao.ANO_MES_REFERENCIA_ARRECADACAO,
						periodoArrecadacaoFinalFormatado));
			}

		}

		// Período Guia

		if ((periodoGuiaInicial != null && !periodoGuiaInicial.equals(""))
				|| (periodoGuiaFinal != null && !periodoGuiaFinal.equals(""))) {
			peloMenosUmParametroInformado = true;

			if ((periodoGuiaInicial != null && !periodoGuiaInicial.trim()
					.equals(""))
					&& Util.validarAnoMes(periodoGuiaInicial)) {
				throw new ActionServletException(
						"atencao.periodo.guia.invalido", null, "Inicial");
			}

			if ((periodoGuiaFinal != null && !periodoGuiaFinal.trim()
					.equals(""))
					&& Util.validarAnoMes(periodoGuiaFinal)) {
				throw new ActionServletException(
						"atencao.periodo.guia.invalido", null, "Final");
			}

			String periodoGuiaInicialFormatado = "";
			if (periodoGuiaInicial != null
					&& !periodoGuiaInicial.trim().equals("")) {
				periodoGuiaInicialFormatado = Util
						.formatarMesAnoParaAnoMesSemBarra(periodoGuiaInicial);
			}

			String periodoGuiaFinalFormatado = "";
			if (periodoGuiaFinal != null && !periodoGuiaFinal.trim().equals("")) {
				periodoGuiaFinalFormatado = Util
						.formatarMesAnoParaAnoMesSemBarra(periodoGuiaFinal);
			}

			if ((!periodoGuiaFinalFormatado.equals(""))
					&& Util.compararAnoMesReferencia(new Integer(
							periodoGuiaFinalFormatado), anoMesFaturamento, ">")) {
				throw new ActionServletException(
						"atencao.ano.mes.referencia.posterior.ano.mes.faturamento",
						"Guia", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if ((!periodoGuiaInicialFormatado.equals(""))
					&& Util.compararAnoMesReferencia(new Integer(
							periodoGuiaInicialFormatado), anoMesFaturamento,
							">")) {
				throw new ActionServletException(
						"atencao.ano.mes.referencia.posterior.ano.mes.faturamento",
						"Guia", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if ((periodoGuiaInicial != null && !periodoGuiaInicial.equals(""))
					&& ((periodoGuiaFinal != null && !periodoGuiaFinal
							.equals("")))) {
				filtroGuiaDevolucao
						.adicionarParametro(new Intervalo(
								FiltroGuiaDevolucao.ANO_MES_REFERENCIA_GUIA_DEVOLUCAO,
								periodoGuiaInicialFormatado,
								periodoGuiaFinalFormatado));
			} else if ((periodoGuiaInicial != null && !periodoGuiaInicial
					.equals(""))) {
				filtroGuiaDevolucao.adicionarParametro(new Intervalo(
						FiltroGuiaDevolucao.ANO_MES_REFERENCIA_GUIA_DEVOLUCAO,
						periodoGuiaInicialFormatado, anoMesFaturamento));
			} else {
				filtroGuiaDevolucao.adicionarParametro(new MenorQue(
						FiltroGuiaDevolucao.ANO_MES_REFERENCIA_GUIA_DEVOLUCAO,
						periodoGuiaFinalFormatado));
			}

		}

		// Período Data Emissão

		if ((dataEmissaoInicial != null && !dataEmissaoInicial.equals(""))
				|| (dataEmissaoFinal != null && !dataEmissaoFinal.equals(""))) {
			peloMenosUmParametroInformado = true;

			Date dataEmissaoInicialFormatada = null;

			Integer anoMesDataEmissaoInicial = null;

			if (dataEmissaoInicial != null
					&& !dataEmissaoInicial.trim().equals("")) {

				dataEmissaoInicialFormatada = Util
						.converteStringParaDate(dataEmissaoInicial);
				anoMesDataEmissaoInicial = Util
						.recuperaAnoMesDaData(dataEmissaoInicialFormatada);

			}

			Date dataEmissaoFinalFormatada = null;

			Integer anoMesDataEmissaoFinal = null;

			if (dataEmissaoFinal != null && !dataEmissaoFinal.trim().equals("")) {

				dataEmissaoFinalFormatada = Util
						.converteStringParaDate(dataEmissaoFinal);
				anoMesDataEmissaoFinal = Util
						.recuperaAnoMesDaData(dataEmissaoFinalFormatada);

			}

			if (dataEmissaoFinalFormatada != null
					&& Util.compararAnoMesReferencia(anoMesDataEmissaoFinal,
							anoMesFaturamento, ">")) {
				throw new ActionServletException(
						"atencao.ano.mes.data.posterior.ano.mes.faturamento",
						"Emissão", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if (dataEmissaoInicialFormatada != null
					&& Util.compararAnoMesReferencia(anoMesDataEmissaoInicial,
							anoMesFaturamento, ">")) {
				throw new ActionServletException(
						"atencao.ano.mes.data.posterior.ano.mes.faturamento",
						"Emissão", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if ((dataEmissaoInicial != null && !dataEmissaoInicial.equals(""))
					&& ((dataEmissaoFinal != null && !dataEmissaoFinal
							.equals("")))) {
				filtroGuiaDevolucao
						.adicionarParametro(new Intervalo(
								FiltroGuiaDevolucao.DATA_EMISSAO,
								dataEmissaoInicialFormatada,
								dataEmissaoFinalFormatada));
			} else if ((dataEmissaoInicial != null && !dataEmissaoInicial
					.equals(""))) {

				String anoMesPesquisa = Util.somaMesAnoMesReferencia(
						anoMesFaturamento, 1).toString();

				String ano = anoMesPesquisa.substring(0, 4);
				String mes = anoMesPesquisa.substring(4, 6);

				Date dataPesquisa = Util.converteStringParaDate("01/" + mes
						+ "/" + ano);

				filtroGuiaDevolucao.adicionarParametro(new MaiorQue(
						FiltroGuiaDevolucao.DATA_EMISSAO,
						dataEmissaoInicialFormatada));

				filtroGuiaDevolucao.adicionarParametro(new MenorQue(
						FiltroGuiaDevolucao.DATA_EMISSAO, dataPesquisa));

			} else {
				filtroGuiaDevolucao.adicionarParametro(new MenorQue(
						FiltroGuiaDevolucao.DATA_EMISSAO,
						dataEmissaoFinalFormatada));
			}

		}

		// Período Data Validade

		if ((dataValidadeInicial != null && !dataValidadeInicial.equals(""))
				|| (dataValidadeFinal != null && !dataValidadeFinal.equals(""))) {
			peloMenosUmParametroInformado = true;

			Date dataValidadeInicialFormatada = null;

			Integer anoMesDataValidadeInicial = null;

			if (dataValidadeInicial != null
					&& !dataValidadeInicial.trim().equals("")) {

				dataValidadeInicialFormatada = Util
						.converteStringParaDate(dataValidadeInicial);
				anoMesDataValidadeInicial = Util
						.recuperaAnoMesDaData(dataValidadeInicialFormatada);

			}

			Date dataValidadeFinalFormatada = null;

			Integer anoMesDataValidadeFinal = null;

			if (dataValidadeFinal != null
					&& !dataValidadeFinal.trim().equals("")) {

				dataValidadeFinalFormatada = Util
						.converteStringParaDate(dataValidadeFinal);
				anoMesDataValidadeFinal = Util
						.recuperaAnoMesDaData(dataValidadeFinalFormatada);

			}

			if (dataValidadeFinalFormatada != null
					&& Util.compararAnoMesReferencia(anoMesDataValidadeFinal,
							anoMesFaturamento, ">")) {
				throw new ActionServletException(
						"atencao.ano.mes.data.posterior.ano.mes.faturamento",
						"Validade", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if (dataValidadeInicialFormatada != null
					&& Util.compararAnoMesReferencia(anoMesDataValidadeInicial,
							anoMesFaturamento, ">")) {
				throw new ActionServletException(
						"atencao.ano.mes.data.posterior.ano.mes.faturamento",
						"Validade", Util.formatarAnoMesParaMesAno(anoMesFaturamento));
			}

			if ((dataValidadeInicial != null && !dataValidadeInicial.equals(""))
					&& ((dataValidadeFinal != null && !dataValidadeFinal
							.equals("")))) {
				filtroGuiaDevolucao.adicionarParametro(new Intervalo(
						FiltroGuiaDevolucao.DATA_VALIDADE,
						dataValidadeInicialFormatada,
						dataValidadeFinalFormatada));
			} else if ((dataValidadeInicial != null && !dataValidadeInicial
					.equals(""))) {

				String anoMesPesquisa = Util.somaMesAnoMesReferencia(
						anoMesFaturamento, 1).toString();

				String ano = anoMesPesquisa.substring(0, 4);
				String mes = anoMesPesquisa.substring(4, 6);

				Date dataPesquisa = Util.converteStringParaDate("01/" + mes
						+ "/" + ano);

				filtroGuiaDevolucao.adicionarParametro(new MaiorQue(
						FiltroGuiaDevolucao.DATA_VALIDADE,
						dataValidadeInicialFormatada));

				filtroGuiaDevolucao.adicionarParametro(new MenorQue(
						FiltroGuiaDevolucao.DATA_VALIDADE, dataPesquisa));

			} else {
				filtroGuiaDevolucao.adicionarParametro(new MenorQue(
						FiltroGuiaDevolucao.DATA_VALIDADE,
						dataValidadeFinalFormatada));
			}
		}

		// Tipo de Crédito

		int i = 0;

		if (idsCreditosTipos != null) {

			while (i < idsCreditosTipos.length) {

				if (!idsCreditosTipos[i].equals("")
						&& !idsCreditosTipos[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					peloMenosUmParametroInformado = true;

					if (i + 1 < idsCreditosTipos.length) {

						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.CREDITO_TIPO_ID,
										idsCreditosTipos[i],
										ConectorOr.CONECTOR_OR,
										(idsCreditosTipos.length)));

					} else {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.CREDITO_TIPO_ID,
										idsCreditosTipos[i]));
					}
				}

				i++;
			}

		}

		i = 0;

		if (idsTipoDebito != null) {

			while (i < idsTipoDebito.length) {

				if (!idsTipoDebito[i].equals("")
						&& !idsTipoDebito[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					peloMenosUmParametroInformado = true;

					if (i + 1 < idsTipoDebito.length) {

						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.DEBITO_TIPO_ID,
										idsTipoDebito[i],
										ConectorOr.CONECTOR_OR,
										(idsTipoDebito.length)));

					} else {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.CREDITO_TIPO_ID,
										idsTipoDebito[i]));
					}
				}

				i++;
			}

		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");

			Collection<Cliente> colecaoClientes = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
				Cliente cliente = ((List<Cliente>) colecaoClientes).get(0);
				sessao.setAttribute("cliente", cliente);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Cliente");
			}

			peloMenosUmParametroInformado = true;

			filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
					FiltroGuiaDevolucao.CLIENTE_ID, idCliente));

			i = 0;

			if (idsDocumentosTipos != null) {

				while (i < idsDocumentosTipos.length) {

					if (!idsDocumentosTipos[i].equals("")
							&& !idsDocumentosTipos[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						if (i + 1 < idsDocumentosTipos.length) {

							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i],
											ConectorOr.CONECTOR_OR,
											(idsDocumentosTipos.length)));

						} else {
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i]));
						}

						// Cliente com Documento Tipo = Conta
						if (idsDocumentosTipos[i].equals(DocumentoTipo.CONTA)) {
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimplesColecao(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_ID,
											idCliente));
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));

							if (idTipoRelacao != null
									&& !idTipoRelacao.trim().equalsIgnoreCase(
											"")) {
								filtroGuiaDevolucao
										.adicionarParametro(new ParametroSimplesColecao(
												FiltroGuiaDevolucao.GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID,
												idTipoRelacao));

							}

						}

						// Cliente com Documento Tipo = Guia de Pagamento
						else if (idsDocumentosTipos[i]
								.equals(DocumentoTipo.GUIA_PAGAMENTO)) {
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimplesColecao(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_ID,
											idCliente));
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_GUIA_PAGAMENTO_DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));

							if (idTipoRelacao != null
									&& !idTipoRelacao.trim().equalsIgnoreCase(
											"")) {
								filtroGuiaDevolucao
										.adicionarParametro(new ParametroSimplesColecao(
												FiltroGuiaDevolucao.GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID,
												idTipoRelacao));

							} else {
								filtroGuiaDevolucao
										.adicionarParametro(new ParametroSimples(
												FiltroGuiaDevolucao.GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_ID,
												idCliente));
							}

						}

						// Cliente com Documento Tipo = Débito a Cobrar
						else if (idsDocumentosTipos[i]
								.equals(DocumentoTipo.DEBITO_A_COBRAR)) {
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimplesColecao(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_DEBITO_A_COBRAR_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_ID,
											idCliente));
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_DEBITO_A_COBRAR_DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));

							if (idTipoRelacao != null
									&& !idTipoRelacao.trim().equalsIgnoreCase(
											"")) {
								filtroGuiaDevolucao
										.adicionarParametro(new ParametroSimplesColecao(
												FiltroGuiaDevolucao.GUIA_DEVOLUCAO_DEBITO_A_COBRAR_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID,
												idTipoRelacao));

							}

						} else if (idsDocumentosTipos[i]
								.equals(DocumentoTipo.DEVOLUCAO_VALOR)) {

							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));

						}

					}

					i++;
				}
			} else {

				if (idTipoRelacao != null
						&& !idTipoRelacao.equals("")
						&& !idTipoRelacao.equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					filtroGuiaDevolucao
							.adicionarParametro(new ParametroSimplesColecao(
									FiltroGuiaDevolucao.GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID,
									idTipoRelacao, ConectorOr.CONECTOR_OR));
					filtroGuiaDevolucao
							.adicionarParametro(new ParametroSimplesColecao(
									FiltroGuiaDevolucao.GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID,
									idTipoRelacao, ConectorOr.CONECTOR_OR));
					filtroGuiaDevolucao
							.adicionarParametro(new ParametroSimplesColecao(
									FiltroGuiaDevolucao.GUIA_DEVOLUCAO_DEBITO_A_COBRAR_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID,
									idTipoRelacao));

				}
			}

			sessao.removeAttribute("filtroGuiaDevolucaoImovel");
			sessao.setAttribute("filtroGuiaDevolucaoCliente",
					filtroGuiaDevolucao);

		}

		// Imóvel
		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

			Collection<Imovel> colecaoImoveis = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				Imovel imovel = ((List<Imovel>) colecaoImoveis).get(0);
				sessao.setAttribute("imovel", imovel);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Matrícula");
			}

			filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
					FiltroGuiaDevolucao.IMOVEL_ID, idImovel));

			i = 0;

			if (idsDocumentosTipos != null) {

				while (i < idsDocumentosTipos.length) {

					if (!idsDocumentosTipos[i].equals("")
							&& !idsDocumentosTipos[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						if (i + 1 < idsDocumentosTipos.length) {

							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i],
											ConectorOr.CONECTOR_OR,
											(idsDocumentosTipos.length)));

						} else {
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i]));
						}

						// Imóvel com Documento Tipo = Conta
						if (idsDocumentosTipos[i].equals(DocumentoTipo.CONTA)) {

							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));

						}

						// Imóvel com Documento Tipo = Guia de Pagamento
						else if (idsDocumentosTipos[i]
								.equals(DocumentoTipo.GUIA_PAGAMENTO)) {

							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_GUIA_PAGAMENTO_DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));

						}

						// Imóvel com Documento Tipo = Débito a Cobrar
						else if (idsDocumentosTipos[i]
								.equals(DocumentoTipo.DEBITO_A_COBRAR)) {

							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.GUIA_DEVOLUCAO_DEBITO_A_COBRAR_DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));

							// Imóvel com Documento Tipo = Devolução de Valor
						} else if (idsDocumentosTipos[i]
								.equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
							filtroGuiaDevolucao
									.adicionarParametro(new ParametroSimples(
											FiltroGuiaDevolucao.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
											DebitoCreditoSituacao.NORMAL));
						}

					}

					i++;
				}
			}

			sessao.removeAttribute("filtroGuiaDevolucaoCliente");
			sessao.setAttribute("filtroGuiaDevolucaoImovel",
					filtroGuiaDevolucao);

		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		if ((idCliente == null || idCliente.equals(""))
				&& (idImovel == null || idImovel.equals(""))) {
			throw new ActionServletException("atencao.informe.imovel.cliente");
		}

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
