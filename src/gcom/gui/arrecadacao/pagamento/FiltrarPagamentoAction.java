package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadadorMovimento;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtra os pagamentos de com os parametros passados
 * 
 * [UC0255] Filtrar Pagamentos
 * 
 * @author Rafael Santos
 * @date 07/10/2006
 */
public class FiltrarPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o formulário
		ConsultarPagamentoActionForm consultarPagamentoActionForm = (ConsultarPagamentoActionForm) actionForm;

		Collection<Pagamento> colecaoImoveisPagamentos = null;
		Collection<PagamentoHistorico> colecaoImoveisPagamentosHistorico = null;
		Collection<Pagamento> colecaoPagamentosClienteConta = null;
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoClienteConta = null;
		Collection<Pagamento> colecaoPagamentosClienteGuiaPagamento = null;
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoClienteGuiaPagamento = null;
		Collection<Pagamento> colecaoPagamentosClienteDebitoACobrar = null;
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoClienteDebitoACobrar = null;
		Collection<Pagamento> colecaoPagamentosLocalidade = null;
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoLocalidade = null;
		Collection<Pagamento> colecaoPagamentosAvisoBancario = null;
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoAvisoBancario = null;
		Collection<Pagamento> colecaoPagamentosMovimentoArrecadador = null;
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoMovimentoArrecadador = null;
		Collection<Pagamento> colecaoPagamentosClientes = null;
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoClientes = null;

		// Recupera os parâmetros do form
		String idImovel =  consultarPagamentoActionForm.getIdImovel();
		String idCliente =  consultarPagamentoActionForm.getIdCliente();
		if (idCliente == null || idCliente.trim().equals("")) {
			consultarPagamentoActionForm.setClienteRelacaoTipo("");
		}
		String idTipoRelacao =  consultarPagamentoActionForm
				.getClienteRelacaoTipo();
		String localidadeInicial =  consultarPagamentoActionForm
				.getLocalidadeInicial();
		String localidadeFinal =  consultarPagamentoActionForm
				.getLocalidadeFinal();
		String idAvisoBancario =  consultarPagamentoActionForm
				.getIdAvisoBancario();
		String idArrecadador =  consultarPagamentoActionForm
				.getIdArrecadador();
		String periodoArrecadacaoInicial =  consultarPagamentoActionForm
				.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFinal =  consultarPagamentoActionForm
				.getPeriodoArrecadacaoFim();
		String periodoPagamentoInicio = consultarPagamentoActionForm
				.getPeriodoPagamentoInicio();
		String periodoPagamentoFim = consultarPagamentoActionForm
				.getPeriodoPagamentoFim();
		
		Date dataPagamentoInicial = null;
		
		if (consultarPagamentoActionForm.getDataPagamentoInicio() != null
				&& !consultarPagamentoActionForm.getDataPagamentoInicio()
						.equals("")) {

			dataPagamentoInicial = Util
					.converteStringParaDate( consultarPagamentoActionForm
							.getDataPagamentoInicio());
		}
		Date dataPagamentoFinal = null;
		if (consultarPagamentoActionForm.getDataPagamentoFim() != null
				&& !consultarPagamentoActionForm.getDataPagamentoFim().equals(
						"")) {
			dataPagamentoFinal = Util
					.converteStringParaDate( consultarPagamentoActionForm
							.getDataPagamentoFim());
		}
		String[] idsPagamentosSituacoes =  consultarPagamentoActionForm
				.getPagamentoSituacao();
		String[] idsdebitosTipos =  consultarPagamentoActionForm
				.getDebitoTipoSelecionados();
		String[] idsArrecadacaoForma =  consultarPagamentoActionForm
				.getArrecadacaoForma();
		String[] idsDocumentosTipos =  consultarPagamentoActionForm
				.getDocumentoTipo();
		String opcaoPagamento = consultarPagamentoActionForm
				.getOpcaoPagamento();
        String valorPagamentoInicial = 
            consultarPagamentoActionForm.getValorPagamentoInicial();
        
        String valorPagamentoFinal = 
            consultarPagamentoActionForm.getValorPagamentoFinal();
        
        
		// caso venha da tela de manter pagamento o padrão será consultar o
		// atual
		if (opcaoPagamento == null) {
			opcaoPagamento = "atual";
		}

		// 1 check --- null uncheck
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		String tela = (String) sessao.getAttribute("tela");
		if (tela != null && !tela.equals("")) {
			if (tela.equals("manterPagamento")) {
				retorno = actionMapping.findForward("exibirManterPagamento");
			}
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			retorno = actionMapping.findForward("consultarPagamentos");
		}

		// [FS0003] Verificar se um dos campos obrigátorios foi informado
		if ((idImovel == null || idImovel.equalsIgnoreCase(""))
				&& (localidadeInicial == null || localidadeInicial
						.equalsIgnoreCase(""))
				&& (localidadeFinal == null || localidadeFinal
						.equalsIgnoreCase(""))
				&& (idCliente == null || idCliente.equalsIgnoreCase(""))
				&& (idAvisoBancario == null || idAvisoBancario
						.equalsIgnoreCase(""))
				&& (idArrecadador == null || idArrecadador.equalsIgnoreCase(""))) {
			throw new ActionServletException(
					"atencao.nenhum_campo_selecionado_consultar_pagamento");
		}

		// Verifica se a localidade final é maior que a inicial
		if (localidadeInicial != null && !localidadeInicial.equals("")
				&& localidadeFinal != null && !localidadeFinal.equals("")) {
			if (localidadeInicial.compareTo(localidadeFinal) == 1) {
				throw new ActionServletException(
						"atencao.localidade_inicial_maior_final");
			}
		}

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		Integer anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();
		String mesAnoArrecadacao = Util
				.formatarAnoMesParaMesAno(anoMesArrecadacao);

		if (periodoArrecadacaoInicial != null
				&& !periodoArrecadacaoInicial.equalsIgnoreCase("")) {

			Integer anoMesArrecadacaoInicial = new Integer(
					Util
							.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicial));

			if (Util.compararAnoMesReferencia(anoMesArrecadacaoInicial,
					anoMesArrecadacao, ">")) {
				// Mês/Ano Inicial do Período Refer. Arrecadação informado não
				// deve ser posterior a << >>, mês/ano de arracadação corrente.
				throw new ActionServletException(
						"atencao.mes_ano_inicial.posterior.arrecadacao_corrente",
						null, "" + mesAnoArrecadacao);
			}

		}

		if (periodoArrecadacaoFinal != null
				&& !periodoArrecadacaoFinal.equalsIgnoreCase("")) {
			Integer anoMesArrecadacaoFinal = new Integer(Util
					.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFinal));

			if (Util.compararAnoMesReferencia(anoMesArrecadacaoFinal,
					anoMesArrecadacao, ">")) {
				// //Mês/Ano Final do Período Refer. Arrecadação informado não
				// deve ser posterior a << >>, mês/ano do arracadação corrente.
				throw new ActionServletException(
						"atencao.mes_ano_final.posterior.arrecadacao_corrente",
						null, "" + mesAnoArrecadacao);
			}
		}

		if (periodoPagamentoInicio != null
				&& !periodoPagamentoInicio.equalsIgnoreCase("")) {

			Integer anoMesPagamentoInicial = new Integer(Util
					.formatarMesAnoParaAnoMesSemBarra(periodoPagamentoInicio));

			if (Util.compararAnoMesReferencia(anoMesPagamentoInicial,
					anoMesArrecadacao, ">")) {
				// Mês/Ano Inicial do Período Refer. Pagamento informado não
				// deve ser posterior a << >>, mês/ano da arrecadação corrente.
				throw new ActionServletException(
						"atencao.mes_ano_inicial.posterior.pagamento_corrente",
						null, "" + mesAnoArrecadacao);
			}

		}

		if (periodoPagamentoFim != null
				&& !periodoPagamentoFim.equalsIgnoreCase("")) {
			Integer anoMesPagamentoFinal = new Integer(Util
					.formatarMesAnoParaAnoMesSemBarra(periodoPagamentoFim));

			if (Util.compararAnoMesReferencia(anoMesPagamentoFinal,
					anoMesArrecadacao, ">")) {
				// //Mês/Ano Final do Período Refer. Pagamento informado não
				// deve ser posterior a << >>, mês/ano da arrecadação corrente.
				throw new ActionServletException(
						"atencao.mes_ano_final.posterior.pagamento_corrente",
						null, "" + mesAnoArrecadacao);
			}
		}

		if (dataPagamentoInicial != null && !dataPagamentoInicial.equals("")) {
			if (dataPagamentoInicial.after(dataPagamentoFinal)) {
				throw new ActionServletException(
						"atencao.data_final.anterior.data_inicial");
			}
		}

		if (dataPagamentoInicial != null && !dataPagamentoInicial.equals("")) {
			Integer anoMesDataPagamentoInicial = new Integer(Util
					.getAnoMesComoInt(dataPagamentoInicial));

			if (Util.compararAnoMesReferencia(anoMesDataPagamentoInicial,
					anoMesArrecadacao, ">")) {
				// Data Inicial do Período de Pagamento informado não pode ser
				// posterior a << >> ,mês/ano da arrecadação corrente.
				throw new ActionServletException(
						"atencao.data_inicial.posterior.arrecadacao_corrente",
						null, "" + mesAnoArrecadacao);
			}
		}

		if (dataPagamentoFinal != null && !dataPagamentoFinal.equals("")) {
			Integer anoMesDataPagamentoFinal = new Integer(Util
					.getAnoMesComoInt(dataPagamentoFinal));

			if (Util.compararAnoMesReferencia(anoMesDataPagamentoFinal,
					anoMesArrecadacao, ">")) {
				// Data Final do Período de Pagamento informado não pode ser
				// posterior a << >> ,mês/ano da arrecadação corrente.
				throw new ActionServletException(
						"atencao.data_final.posterior.arrecadacao_corrente",
						null, "" + mesAnoArrecadacao);
			}
		}

		boolean peloMenosUmParametroInformado = false;

		// 2.1. Caso tenha sido informado o Imóvel, seleciona os pagamentos do
		// Imóvel
		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
			// [SB0001] Selecionar Pagamentos do Imóvel
			peloMenosUmParametroInformado = true;

			// pesquisa utilizada a partir do Consultar Pagamento
			if (tela == null || tela.equals("")) {
				// Pesquisa o imóvel fazendo os caregamentos necessários e caso
				// ele exista seta-o na sessão
				Imovel imovel = fachada.pesquisarImovelPagamento(new Integer(
						idImovel));

				if (imovel != null) {
					sessao.setAttribute("imovel", imovel);
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "imovel");
				}

			}

			// 1. O sistema seleciona os pagamentos do imóvel
			// (a partir da tabela PAGAMENTO com IMOV_ID = Id do imóvel
			// informado
			// e demais parâmetros de seleção informada)

			// pesquisa utilizada pelo do Consultar Pagamento
			if (tela == null || tela.equals("")) {

				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoImoveisPagamentos = fachada
							.pesquisarPagamentoImovel(idImovel, idCliente,
									idTipoRelacao, localidadeInicial,
									localidadeFinal, idAvisoBancario,
									idArrecadador, periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos,valorPagamentoInicial,
                                    valorPagamentoFinal);

					if (colecaoImoveisPagamentos != null
							&& !colecaoImoveisPagamentos.isEmpty()) {
						sessao.setAttribute("colecaoImoveisPagamentos",
								colecaoImoveisPagamentos);
					}

				}
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
								.equals("ambos")))) {

					colecaoImoveisPagamentosHistorico = fachada
							.pesquisarPagamentoHistoricoImovel(idImovel,
									idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos);

					if (colecaoImoveisPagamentosHistorico != null
							&& !colecaoImoveisPagamentosHistorico.isEmpty()) {
						sessao.setAttribute(
								"colecaoImoveisPagamentosHistorico",
								colecaoImoveisPagamentosHistorico);
					}
				}

			} else {
				// pesquisa utilizada pelo do Manter Pagamento
				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
//				if (opcaoPagamento != null
//						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
//								.equals("ambos")))) {
					// 1º Passo - Pegar o total de registros através de um count
					// da
					// consulta que aparecerá na tela
					Integer totalRegistros = fachada
							.pesquisarPagamentoCount(idImovel, idCliente,
									idTipoRelacao, localidadeInicial,
									localidadeFinal, idAvisoBancario,
									idArrecadador, periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos,valorPagamentoInicial,
                                    valorPagamentoFinal);

					// 2º Passo - Chamar a função de Paginação passando o total
					// de registros
					retorno = this.controlarPaginacao(httpServletRequest,
							retorno, totalRegistros);

					// 3º Passo - Obter a coleção da consulta que aparecerá na
					// tela
					// passando o numero de paginas da pesquisa que está no
					// request
					colecaoImoveisPagamentos = fachada
							.pesquisarPagamentoImovelParaPaginacao(
									idImovel,
									idCliente,
									idTipoRelacao,
									localidadeInicial,
									localidadeFinal,
									idAvisoBancario,
									idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim,
									dataPagamentoInicial,
									dataPagamentoFinal,
									idsPagamentosSituacoes,
									idsdebitosTipos,
									idsArrecadacaoForma,
									idsDocumentosTipos,
									(Integer) httpServletRequest
											.getAttribute("numeroPaginasPesquisa"),
                                    valorPagamentoInicial, 
                                    valorPagamentoFinal );

					if (colecaoImoveisPagamentos != null
							&& !colecaoImoveisPagamentos.isEmpty()) {
						sessao.setAttribute("colecaoImoveisPagamentos",
								colecaoImoveisPagamentos);
					}

//				}
//				if (opcaoPagamento != null
//						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
//								.equals("ambos")))) {
//					// 1º Passo - Pegar o total de registros através de um count
//					// da
//					// consulta que aparecerá na tela
//					Integer totalRegistros = fachada
//							.pesquisarPagamentoHistoricoImovelCount(idImovel,
//									idCliente, idTipoRelacao,
//									localidadeInicial, localidadeFinal,
//									idAvisoBancario, idArrecadador,
//									periodoArrecadacaoInicial,
//									periodoArrecadacaoFinal,
//									periodoPagamentoInicio,
//									periodoPagamentoFim, dataPagamentoInicial,
//									dataPagamentoFinal, idsPagamentosSituacoes,
//									idsdebitosTipos, idsArrecadacaoForma,
//									idsDocumentosTipos);
//
//					// 2º Passo - Chamar a função de Paginação passando o total
//					// de registros
//					retorno = this.controlarPaginacao(httpServletRequest,
//							retorno, totalRegistros);
//
//					// 3º Passo - Obter a coleção da consulta que aparecerá na
//					// tela
//					// passando o numero de paginas da pesquisa que está no
//					// request
//					colecaoImoveisPagamentosHistorico = fachada
//							.pesquisarPagamentoHistoricoImovelParaPaginacao(
//									idImovel,
//									idCliente,
//									idTipoRelacao,
//									localidadeInicial,
//									localidadeFinal,
//									idAvisoBancario,
//									idArrecadador,
//									periodoArrecadacaoInicial,
//									periodoArrecadacaoFinal,
//									periodoPagamentoInicio,
//									periodoPagamentoFim,
//									dataPagamentoInicial,
//									dataPagamentoFinal,
//									idsPagamentosSituacoes,
//									idsdebitosTipos,
//									idsArrecadacaoForma,
//									idsDocumentosTipos,
//									(Integer) httpServletRequest
//											.getAttribute("numeroPaginasPesquisa"));
//
//					if (colecaoImoveisPagamentosHistorico != null
//							&& !colecaoImoveisPagamentosHistorico.isEmpty()) {
//						sessao.setAttribute(
//								"colecaoImoveisPagamentosHistorico",
//								colecaoImoveisPagamentosHistorico);
//					}
//
//				}
			}

		}

		// 2.2. Caso tenha sido informado o Cliente, seleciona os pagamentos do
		// Cliente
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			// pesquisa utilizada pelo do Consultar Pagamento
			if (tela == null || tela.equals("")) {

				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosClienteConta = fachada
							.pesquisarPagamentoClienteConta(idImovel,
									idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);
					if (colecaoPagamentosClienteConta != null
							&& !colecaoPagamentosClienteConta.isEmpty()) {
						sessao.setAttribute("colecaoPagamentosClienteConta",
								colecaoPagamentosClienteConta);
					}
				}
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosHistoricoClienteConta = fachada
							.pesquisarPagamentoHistoricoClienteConta(idImovel,
									idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos);
					if (colecaoPagamentosHistoricoClienteConta != null
							&& !colecaoPagamentosHistoricoClienteConta
									.isEmpty()) {
						sessao.setAttribute(
								"colecaoPagamentosHistoricoClienteConta",
								colecaoPagamentosHistoricoClienteConta);
					}

				}

				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosClienteGuiaPagamento = fachada
							.pesquisarPagamentoClienteGuiaPagamento(idImovel,
									idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);

					if (colecaoPagamentosClienteGuiaPagamento != null
							&& !colecaoPagamentosClienteGuiaPagamento.isEmpty()) {
						sessao.setAttribute(
								"colecaoPagamentosClienteGuiaPagamento",
								colecaoPagamentosClienteGuiaPagamento);
					}
				}
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosHistoricoClienteGuiaPagamento = fachada
							.pesquisarPagamentoHistoricoClienteGuiaPagamento(
									idImovel, idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos);

					if (colecaoPagamentosHistoricoClienteGuiaPagamento != null
							&& !colecaoPagamentosHistoricoClienteGuiaPagamento
									.isEmpty()) {
						sessao
								.setAttribute(
										"colecaoPagamentosHistoricoClienteGuiaPagamento",
										colecaoPagamentosHistoricoClienteGuiaPagamento);
					}
				}

				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosClienteDebitoACobrar = fachada
							.pesquisarPagamentoClienteDebitoACobrar(idImovel,
									idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);

					if (colecaoPagamentosClienteDebitoACobrar != null
							&& !colecaoPagamentosClienteDebitoACobrar.isEmpty()) {
						sessao.setAttribute(
								"colecaoPagamentosClienteDebitoACobrar",
								colecaoPagamentosClienteDebitoACobrar);
					}
				}
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosHistoricoClienteDebitoACobrar = fachada
							.pesquisarPagamentoHistoricoClienteDebitoACobrar(
									idImovel, idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos);

					if (colecaoPagamentosHistoricoClienteDebitoACobrar != null
							&& !colecaoPagamentosHistoricoClienteDebitoACobrar
									.isEmpty()) {
						sessao
								.setAttribute(
										"colecaoPagamentosHistoricoClienteDebitoACobrar",
										colecaoPagamentosHistoricoClienteDebitoACobrar);
					}
				}

			} else {
				// pesquisa utilizada pelo do Manter Pagamento
				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
//				if (opcaoPagamento != null
//						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
//								.equals("ambos")))) {
					// 1º Passo - Pegar o total de registros através de um count
					// da
					// consulta que aparecerá na tela
					Integer totalRegistros = fachada
							.pesquisarPagamentoClienteCount(idImovel,
									idCliente, idTipoRelacao,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentosSituacoes,
									idsdebitosTipos, idsArrecadacaoForma,
									idsDocumentosTipos,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);

					// 2º Passo - Chamar a função de Paginação passando o total
					// de registros
					retorno = this.controlarPaginacao(httpServletRequest,
							retorno, totalRegistros);

					// 3º Passo - Obter a coleção da consulta que aparecerá na
					// tela
					// passando o numero de paginas da pesquisa que está no
					// request
					colecaoPagamentosClientes = fachada
							.pesquisarPagamentoCliente(
									idImovel,
									idCliente,
									idTipoRelacao,
									localidadeInicial,
									localidadeFinal,
									idAvisoBancario,
									idArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicio,
									periodoPagamentoFim,
									dataPagamentoInicial,
									dataPagamentoFinal,
									idsPagamentosSituacoes,
									idsdebitosTipos,
									idsArrecadacaoForma,
									idsDocumentosTipos,
									(Integer) httpServletRequest
											.getAttribute("numeroPaginasPesquisa"),
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);

					if (colecaoPagamentosClientes != null
							&& !colecaoPagamentosClientes.isEmpty()) {
						sessao.setAttribute("colecaoClientesPagamentos",
								colecaoPagamentosClientes);
					}
//				}
//				if (opcaoPagamento != null
//						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
//								.equals("ambos")))) {
//
//					// 1º Passo - Pegar o total de registros através de um count
//					// da
//					// consulta que aparecerá na tela
//					Integer totalRegistros = fachada
//							.pesquisarPagamentoHistoricoClienteCount(idImovel,
//									idCliente, idTipoRelacao,
//									localidadeInicial, localidadeFinal,
//									idAvisoBancario, idArrecadador,
//									periodoArrecadacaoInicial,
//									periodoArrecadacaoFinal,
//									periodoPagamentoInicio,
//									periodoPagamentoFim, dataPagamentoInicial,
//									dataPagamentoFinal, idsPagamentosSituacoes,
//									idsdebitosTipos, idsArrecadacaoForma,
//									idsDocumentosTipos);
//
//					// 2º Passo - Chamar a função de Paginação passando o total
//					// de registros
//					retorno = this.controlarPaginacao(httpServletRequest,
//							retorno, totalRegistros);
//
//					// 3º Passo - Obter a coleção da consulta que aparecerá na
//					// tela
//					// passando o numero de paginas da pesquisa que está no
//					// request
//					colecaoPagamentosHistoricoClientes = fachada
//							.pesquisarPagamentoHistoricoCliente(
//									idImovel,
//									idCliente,
//									idTipoRelacao,
//									localidadeInicial,
//									localidadeFinal,
//									idAvisoBancario,
//									idArrecadador,
//									periodoArrecadacaoInicial,
//									periodoArrecadacaoFinal,
//									periodoPagamentoInicio,
//									periodoPagamentoFim,
//									dataPagamentoInicial,
//									dataPagamentoFinal,
//									idsPagamentosSituacoes,
//									idsdebitosTipos,
//									idsArrecadacaoForma,
//									idsDocumentosTipos,
//									(Integer) httpServletRequest
//											.getAttribute("numeroPaginasPesquisa"));
//
//					if (colecaoPagamentosHistoricoClientes != null
//							&& !colecaoPagamentosHistoricoClientes.isEmpty()) {
//						sessao.setAttribute(
//								"colecaoClientesPagamentosHistorico",
//								colecaoPagamentosHistoricoClientes);
//					}
//
//				}
			}

		}

		// 2.3. Caso tenha sido informado a Localidade, seleciona os pagamentos
		// da Localidade
		if (localidadeInicial != null
				&& !localidadeInicial.trim().equalsIgnoreCase("")
				&& localidadeFinal != null
				&& !localidadeFinal.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			// pesquisa utilizada pelo do Manter Pagamento
			// verifica se é para pesquisar no atual, no historico ou em ambos
//			if (opcaoPagamento != null
//					&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
//							.equals("ambos")))) {
				// 1º Passo - Pegar o total de registros através de um count da
				// consulta que aparecerá na tela
			
			Integer totalRegistros = new Integer(0);
			
			if (tela == null || tela.equals("")) {
			
			if (opcaoPagamento != null
					&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
							.equals("ambos")))) {
			
				totalRegistros = totalRegistros + fachada
						.pesquisarPagamentoLocalidadeCount(idImovel, idCliente,
								idTipoRelacao, localidadeInicial,
								localidadeFinal, idAvisoBancario,
								idArrecadador, periodoArrecadacaoInicial,
								periodoArrecadacaoFinal,
								periodoPagamentoInicio, periodoPagamentoFim,
								dataPagamentoInicial, dataPagamentoFinal,
								idsPagamentosSituacoes, idsdebitosTipos,
								idsArrecadacaoForma, idsDocumentosTipos,valorPagamentoInicial,
                                valorPagamentoFinal);
			}
				
			if (opcaoPagamento != null
					&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
							.equals("ambos")))) {
				totalRegistros = totalRegistros + fachada
					.pesquisarPagamentoHistoricoLocalidadeCount(idImovel, idCliente,
						idTipoRelacao, localidadeInicial,
						localidadeFinal, idAvisoBancario,
						idArrecadador, periodoArrecadacaoInicial,
						periodoArrecadacaoFinal,
						periodoPagamentoInicio, periodoPagamentoFim,
						dataPagamentoInicial, dataPagamentoFinal,
						idsPagamentosSituacoes, idsdebitosTipos,
						idsArrecadacaoForma, idsDocumentosTipos);
			}
				
				
					if (totalRegistros == null || totalRegistros.intValue() == 0) {
						throw new ActionServletException(
								"atencao.pesquisa.nenhumresultado");
					}
					
					retorno = actionMapping
							.findForward("gerarRelatorioPagamento");
					
					return retorno;
					
				}
			totalRegistros = fachada
			.pesquisarPagamentoLocalidadeCount(idImovel, idCliente,
					idTipoRelacao, localidadeInicial,
					localidadeFinal, idAvisoBancario,
					idArrecadador, periodoArrecadacaoInicial,
					periodoArrecadacaoFinal,
					periodoPagamentoInicio, periodoPagamentoFim,
					dataPagamentoInicial, dataPagamentoFinal,
					idsPagamentosSituacoes, idsdebitosTipos,
					idsArrecadacaoForma, idsDocumentosTipos,valorPagamentoInicial,
					valorPagamentoFinal);

			// 2º Passo - Chamar a função de Paginação passando o total de
			// registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			// 3º Passo - Obter a coleção da consulta que aparecerá na tela
			// passando o numero de paginas da pesquisa que está no request
			colecaoPagamentosLocalidade = fachada
			.pesquisarPagamentoLocalidade(idImovel, idCliente,
					idTipoRelacao, localidadeInicial,
					localidadeFinal, idAvisoBancario,
					idArrecadador, periodoArrecadacaoInicial,
					periodoArrecadacaoFinal,
					periodoPagamentoInicio, periodoPagamentoFim,
					dataPagamentoInicial, dataPagamentoFinal,
					idsPagamentosSituacoes, idsdebitosTipos,
					idsArrecadacaoForma, idsDocumentosTipos,
					(Integer) httpServletRequest
					.getAttribute("numeroPaginasPesquisa"),
					valorPagamentoInicial,
					valorPagamentoFinal);

			if (colecaoPagamentosLocalidade != null
					&& !colecaoPagamentosLocalidade.isEmpty()) {
				sessao.setAttribute("colecaoPagamentosLocalidade",
						colecaoPagamentosLocalidade);
				// pesquisa utilizada pelo do Consultar Pagamento

			}
		}

		// 2.4. Caso tenha sido informado o Aviso Bancário, seleciona os
		// pagamentos do Aviso Bancário
		if (idAvisoBancario != null
				&& !idAvisoBancario.trim().equalsIgnoreCase("")) {
			// [SB0004] Selecionar Pagamentos do Aviso Bancário
			peloMenosUmParametroInformado = true;

			FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.ID, idAvisoBancario));

			filtroAvisoBancario
			.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");

			Collection<AvisoBancario> colecaoAvisoBancarios = fachada.pesquisar(
					filtroAvisoBancario, AvisoBancario.class.getName());

			if (colecaoAvisoBancarios != null
					&& !colecaoAvisoBancarios.isEmpty()) {
				AvisoBancario avisoBancario = ((List<AvisoBancario>) colecaoAvisoBancarios).get(0);
				sessao.setAttribute("avisoBancario", avisoBancario);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "aviso bancario");
			}

			// 1. O sistema seleciona os pagamentos do avisobancário informado
			// (a partir
			// da tabela PAGAMENTO com AMIT_ID = AMIT_ID da tabela
			// ARRECADADOR_MOVIMENTO_ITEM
			// com ARMV_ID = Id do movimento informado e demais parâmetros de
			// seleção informados)
			// peloMenosUmParametroInformado = true;

			// pesquisa utilizada pelo do Consultar Pagamento

			if (tela == null || tela.equals("")) {
				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosAvisoBancario = fachada
					.pesquisarPagamentoAvisoBancario(idImovel,
							idCliente, idTipoRelacao,
							localidadeInicial, localidadeFinal,
							idAvisoBancario, idArrecadador,
							periodoArrecadacaoInicial,
							periodoArrecadacaoFinal,
							periodoPagamentoInicio,
							periodoPagamentoFim, dataPagamentoInicial,
							dataPagamentoFinal, idsPagamentosSituacoes,
							idsdebitosTipos, idsArrecadacaoForma,
							idsDocumentosTipos,valorPagamentoInicial,
							valorPagamentoFinal);

					if (colecaoPagamentosAvisoBancario != null
							&& !colecaoPagamentosAvisoBancario.isEmpty()) {
						sessao.setAttribute("colecaoPagamentosAvisoBancario",
								colecaoPagamentosAvisoBancario);
					}

				}
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosHistoricoAvisoBancario = fachada
					.pesquisarPagamentoHistoricoAvisoBancario(idImovel,
							idCliente, idTipoRelacao,
							localidadeInicial, localidadeFinal,
							idAvisoBancario, idArrecadador,
							periodoArrecadacaoInicial,
							periodoArrecadacaoFinal,
							periodoPagamentoInicio,
							periodoPagamentoFim, dataPagamentoInicial,
							dataPagamentoFinal, idsPagamentosSituacoes,
							idsdebitosTipos, idsArrecadacaoForma,
							idsDocumentosTipos);

					if (colecaoPagamentosHistoricoAvisoBancario != null
							&& !colecaoPagamentosHistoricoAvisoBancario
							.isEmpty()) {
						sessao.setAttribute(
								"colecaoPagamentosHistoricoAvisoBancario",
								colecaoPagamentosHistoricoAvisoBancario);
					}
				}

			} else {
				// pesquisa utilizada pelo do Manter Pagamento
				// verifica se é para pesquisar no atual, no historico ou em
				// ambos
				//				if (opcaoPagamento != null
				//						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
				//								.equals("ambos")))) {
				// 1º Passo - Pegar o total de registros através de um count
				// da
				// consulta que aparecerá na tela
				Integer totalRegistros = fachada
				.pesquisarPagamentoAvisoBancarioCount(idImovel,
						idCliente, idTipoRelacao,
						localidadeInicial, localidadeFinal,
						idAvisoBancario, idArrecadador,
						periodoArrecadacaoInicial,
						periodoArrecadacaoFinal,
						periodoPagamentoInicio,
						periodoPagamentoFim, dataPagamentoInicial,
						dataPagamentoFinal, idsPagamentosSituacoes,
						idsdebitosTipos, idsArrecadacaoForma,
						idsDocumentosTipos,valorPagamentoInicial,
						valorPagamentoFinal);

				// 2º Passo - Chamar a função de Paginação passando o total
				// de registros
				retorno = this.controlarPaginacao(httpServletRequest,
						retorno, totalRegistros);

				// 3º Passo - Obter a coleção da consulta que aparecerá na
				// tela
				// passando o numero de paginas da pesquisa que está no
				// request
				colecaoPagamentosAvisoBancario = fachada
				.pesquisarPagamentoAvisoBancarioParaPaginacao(
						idImovel,
						idCliente,
						idTipoRelacao,
						localidadeInicial,
						localidadeFinal,
						idAvisoBancario,
						idArrecadador,
						periodoArrecadacaoInicial,
						periodoArrecadacaoFinal,
						periodoPagamentoInicio,
						periodoPagamentoFim,
						dataPagamentoInicial,
						dataPagamentoFinal,
						idsPagamentosSituacoes,
						idsdebitosTipos,
						idsArrecadacaoForma,
						idsDocumentosTipos,
						(Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"),
						valorPagamentoInicial,
						valorPagamentoFinal );

				if (colecaoPagamentosAvisoBancario != null
						&& !colecaoPagamentosAvisoBancario.isEmpty()) {
					sessao.setAttribute("colecaoPagamentosAvisoBancario",
							colecaoPagamentosAvisoBancario);
				}

			}
			//				if (opcaoPagamento != null
			//						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
			//								.equals("ambos")))) {
			//					// 1º Passo - Pegar o total de registros através de um count
			//					// da
			//					// consulta que aparecerá na tela
			//					Integer totalRegistros = fachada
			//							.pesquisarPagamentoHistoricoAvisoBancarioCount(
			//									idImovel, idCliente, idTipoRelacao,
			//									localidadeInicial, localidadeFinal,
			//									idAvisoBancario, idArrecadador,
			//									periodoArrecadacaoInicial,
			//									periodoArrecadacaoFinal,
			//									periodoPagamentoInicio,
			//									periodoPagamentoFim, dataPagamentoInicial,
			//									dataPagamentoFinal, idsPagamentosSituacoes,
			//									idsdebitosTipos, idsArrecadacaoForma,
			//									idsDocumentosTipos);
			//
			//					// 2º Passo - Chamar a função de Paginação passando o total
			//					// de registros
			//					retorno = this.controlarPaginacao(httpServletRequest,
			//							retorno, totalRegistros);
			//
			//					// 3º Passo - Obter a coleção da consulta que aparecerá na
			//					// tela
			//					// passando o numero de paginas da pesquisa que está no
			//					// request
			//					colecaoPagamentosHistoricoAvisoBancario = fachada
			//							.pesquisarPagamentoHistoricoAvisoBancarioParaPaginacao(
			//									idImovel,
			//									idCliente,
			//									idTipoRelacao,
			//									localidadeInicial,
			//									localidadeFinal,
			//									idAvisoBancario,
			//									idArrecadador,
			//									periodoArrecadacaoInicial,
			//									periodoArrecadacaoFinal,
			//									periodoPagamentoInicio,
			//									periodoPagamentoFim,
			//									dataPagamentoInicial,
			//									dataPagamentoFinal,
			//									idsPagamentosSituacoes,
			//									idsdebitosTipos,
			//									idsArrecadacaoForma,
			//									idsDocumentosTipos,
			//									(Integer) httpServletRequest
			//											.getAttribute("numeroPaginasPesquisa"));
			//
			//					if (colecaoPagamentosHistoricoAvisoBancario != null
			//							&& !colecaoPagamentosHistoricoAvisoBancario
			//									.isEmpty()) {
			//						sessao.setAttribute(
			//								"colecaoPagamentosHistoricoAvisoBancario",
			//								colecaoPagamentosHistoricoAvisoBancario);
			//					}
			//				}

			//			}

		}

		// 2.5. Caso tenha sido informado o Movimento de Arrecadador, seleciona
		// os pagamentos do Movimento de Arrecadador
		if (idArrecadador != null && !idArrecadador.trim().equalsIgnoreCase("")) {
			// [SB0004] Selecionar Pagamentos do Movimento Arrecadador
			peloMenosUmParametroInformado = true;

			FiltroArrecadadorMovimento filtroArrecadadorMovimento = new FiltroArrecadadorMovimento();
			filtroArrecadadorMovimento.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorMovimento.ID, idArrecadador));

			Collection<ArrecadadorMovimento> colecaoArrecadadorMovimento = fachada.pesquisar(
					filtroArrecadadorMovimento, ArrecadadorMovimento.class.getName());

			ArrecadadorMovimento arrecadadorMovimento = new ArrecadadorMovimento();

			if (colecaoArrecadadorMovimento != null
					&& !colecaoArrecadadorMovimento.isEmpty()) {
				arrecadadorMovimento = ((List<ArrecadadorMovimento>) colecaoArrecadadorMovimento).get(0);
				sessao.setAttribute("arrecadadorMovimento",
						arrecadadorMovimento);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
				"Movimento de Arrecadador");
			}
			if (tela == null || tela.equals("")) {
				// verifica se é para pesquisar no atual, no historico ou em ambos
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosMovimentoArrecadador = fachada
					.pesquisarPagamentoMovimentoArrecadador(null, null,
							null, null, null, null, idArrecadador,
							periodoArrecadacaoInicial,
							periodoArrecadacaoFinal,
							periodoPagamentoInicio, periodoPagamentoFim,
							dataPagamentoInicial, dataPagamentoFinal,
							idsPagamentosSituacoes, idsdebitosTipos,
							idsArrecadacaoForma, idsDocumentosTipos,
							valorPagamentoInicial,
							valorPagamentoFinal);
					if (colecaoPagamentosMovimentoArrecadador != null
							&& !colecaoPagamentosMovimentoArrecadador.isEmpty()) {
						sessao.setAttribute(
								"colecaoPagamentosMovimentoArrecadador",
								colecaoPagamentosMovimentoArrecadador);
					}
				}
				if (opcaoPagamento != null
						&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
								.equals("ambos")))) {
					colecaoPagamentosHistoricoMovimentoArrecadador = fachada
					.pesquisarPagamentoHistoricoMovimentoArrecadador(null,
							null, null, null, null, null, idArrecadador,
							periodoArrecadacaoInicial,
							periodoArrecadacaoFinal,
							periodoPagamentoInicio, periodoPagamentoFim,
							dataPagamentoInicial, dataPagamentoFinal,
							idsPagamentosSituacoes, idsdebitosTipos,
							idsArrecadacaoForma, idsDocumentosTipos);
					if (colecaoPagamentosHistoricoMovimentoArrecadador != null
							&& !colecaoPagamentosHistoricoMovimentoArrecadador
							.isEmpty()) {
						sessao.setAttribute(
								"colecaoPagamentosHistoricoMovimentoArrecadador",
								colecaoPagamentosHistoricoMovimentoArrecadador);
					}
				}
			} else {
				// 1º Passo - Pegar o total de registros através de um count
				// da
				// consulta que aparecerá na tela
				Integer totalRegistros = fachada
				.pesquisarPagamentoMovimentoArrecadadorCount(idImovel, idCliente,
						idTipoRelacao, localidadeInicial,
						localidadeFinal, idAvisoBancario,
						idArrecadador, periodoArrecadacaoInicial,
						periodoArrecadacaoFinal,
						periodoPagamentoInicio,
						periodoPagamentoFim, dataPagamentoInicial,
						dataPagamentoFinal, idsPagamentosSituacoes,
						idsdebitosTipos, idsArrecadacaoForma,
						idsDocumentosTipos,valorPagamentoInicial,
						valorPagamentoFinal);

				// 2º Passo - Chamar a função de Paginação passando o total
				// de registros
				retorno = this.controlarPaginacao(httpServletRequest,
						retorno, totalRegistros);

				// 3º Passo - Obter a coleção da consulta que aparecerá na
				// tela
				// passando o numero de paginas da pesquisa que está no
				// request
				colecaoPagamentosMovimentoArrecadador = fachada
				.pesquisarPagamentoMovimentoArrecadadorParaPaginacao(
						null,
						null, null, null, null, null,
						idArrecadador,
						periodoArrecadacaoInicial,
						periodoArrecadacaoFinal,
						periodoPagamentoInicio,
						periodoPagamentoFim,
						dataPagamentoInicial,
						dataPagamentoFinal,
						idsPagamentosSituacoes,
						idsdebitosTipos,
						idsArrecadacaoForma,
						idsDocumentosTipos,
						(Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"),
						valorPagamentoInicial,
						valorPagamentoFinal);

				if (colecaoPagamentosMovimentoArrecadador != null
						&& !colecaoPagamentosMovimentoArrecadador.isEmpty()) {
					sessao.setAttribute("colecaoPagamentosMovimentoArrecadador",
							colecaoPagamentosMovimentoArrecadador);
				}
			}

		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
			"atencao.filtro.nenhum_parametro_informado");
		}

		// [FS0011] Verifica a existência de Pagamentos
		if (((colecaoImoveisPagamentos == null || colecaoImoveisPagamentos
				.isEmpty()) && (colecaoImoveisPagamentosHistorico == null || colecaoImoveisPagamentosHistorico
						.isEmpty()))
						&& (((colecaoPagamentosClienteConta == null || colecaoPagamentosClienteConta
								.isEmpty()) && (colecaoPagamentosHistoricoClienteConta == null || colecaoPagamentosHistoricoClienteConta
										.isEmpty()))
										&& ((colecaoPagamentosClienteGuiaPagamento == null || colecaoPagamentosClienteGuiaPagamento
												.isEmpty()) && (colecaoPagamentosHistoricoClienteGuiaPagamento == null || colecaoPagamentosHistoricoClienteGuiaPagamento
														.isEmpty())) && ((colecaoPagamentosClienteDebitoACobrar == null || colecaoPagamentosClienteDebitoACobrar
																.isEmpty()) && (colecaoPagamentosHistoricoClienteDebitoACobrar == null || colecaoPagamentosHistoricoClienteDebitoACobrar
																		.isEmpty())))
																		&& ((colecaoPagamentosLocalidade == null || colecaoPagamentosLocalidade
																				.isEmpty()) && (colecaoPagamentosHistoricoLocalidade == null || colecaoPagamentosHistoricoLocalidade
																						.isEmpty()))
																						&& ((colecaoPagamentosAvisoBancario == null || colecaoPagamentosAvisoBancario
																								.isEmpty()) && (colecaoPagamentosHistoricoAvisoBancario == null || colecaoPagamentosHistoricoAvisoBancario
																										.isEmpty()))
																										&& ((colecaoPagamentosMovimentoArrecadador == null || colecaoPagamentosMovimentoArrecadador
																												.isEmpty()) && (colecaoPagamentosHistoricoMovimentoArrecadador == null || colecaoPagamentosHistoricoMovimentoArrecadador
																														.isEmpty()))
																														&& ((colecaoPagamentosClientes == null || colecaoPagamentosClientes
																																.isEmpty()) && (colecaoPagamentosHistoricoClientes == null || colecaoPagamentosHistoricoClientes
																																		.isEmpty()))

		) {
			// Nenhum pagamento cadastrado
			throw new ActionServletException(
			"atencao.pesquisa.nenhumresultado"); // atencao.nao_pagamentos_parametros_informados
		}

		// sessao.removeAttribute("tela");

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
