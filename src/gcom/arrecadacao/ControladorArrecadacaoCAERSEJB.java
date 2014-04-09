package gcom.arrecadacao;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.DadosConteudoCodigoBarrasHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoA;
import gcom.arrecadacao.bean.RegistroHelperCodigoB;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gcom.arrecadacao.bean.RegistroHelperCodigoC;
import gcom.arrecadacao.bean.RegistroHelperCodigoE;
import gcom.arrecadacao.bean.RegistroHelperCodigoF;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.bean.RegistroHelperCodigoX;
import gcom.arrecadacao.bean.RegistroHelperCodigoZ;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FiltroFatura;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.SessionBean;

/**
 * Controlador Faturamento CAER
 * 
 * @author Raphael Rossiter
 * @date 30/04/2007
 */
public class ControladorArrecadacaoCAERSEJB extends ControladorArrecadacao
		implements SessionBean {

	private static final long serialVersionUID = 1L;

	// ==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAER
	// ==============================================================================================================

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * Autor: Sávio Luiz, Rafael Pinto, Raphael Rossiter Data: 01/02/2006, ,
	 * 30/04/2007
	 */
	public PagamentoHelperCodigoBarras processarPagamentosCodigoBarras(
			String codigoBarras, Date dataPagamento, Integer idFormaArrecadacao,
			SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();
		
		Collection colecaoDevolucoes = new ArrayList();
		
		/*
		 * Recupera o objeto registroHelperCodigoBarras passando a string do
		 * código de barras
		 */
		RegistroHelperCodigoBarras registroHelperCodigoBarras = distribuirDadosCodigoBarras(codigoBarras);

		BigDecimal valorPagamento = Util
				.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(registroHelperCodigoBarras
						.getValorPagamento());

		// Recupera o ano e o mês da data de pagamento
		Integer anoMesPagamento = Util.recuperaAnoMesDaData(dataPagamento);

		Short identificadorEmpresaCodigoBarras = new Short(
				registroHelperCodigoBarras.getIdEmpresa());

		int anoMes = 0;

		/*
		 * Caso o identificador da empresa no txt não seja igual ao código da
		 * empresa no sistemas parametro.
		 */
		if (!identificadorEmpresaCodigoBarras.equals(sistemaParametro
				.getCodigoEmpresaFebraban())) {

			// Atribui o valor 2(NÃO) ao indicador aceitação registro
			indicadorAceitacaoRegistro = "2";

			descricaoOcorrencia = "CÓDIGO DE BARRAS NÃO PERTENCE A "
					+ sistemaParametro.getNomeEmpresa();

		} else {

			boolean matriculaImovelInvalida = false;

			Integer idImovelNaBase = null;
			Integer matriculaImovel = null;

			boolean anoMesReferencia = false;

			// [SB0011] - Processar Pagamento Legado

			// ** Caso o tipo do documento (igual 01) - Conta e Segunda Via
			RegistroHelperCodigoBarrasTipoPagamento registro = registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento();

			if (registro.getIdPagamento1().equalsIgnoreCase("01")) {

				matriculaImovelInvalida = Util.validarValorNaoNumerico(registro
						.getIdPagamento2());

				if (matriculaImovelInvalida) {
					descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
				} else {

					// Verifica se existe a matricula do imóvel na base
					matriculaImovel = new Integer(registro.getIdPagamento2());

					// [FS0008] - Calcular Digito Verificador da Matricula
					if (matriculaImovel != null) {
						int digitoModulo11 = Util
								.obterDigitoVerificadorModulo11(""
										+ matriculaImovel);

						matriculaImovel = new Integer(matriculaImovel
								.toString()
								+ digitoModulo11);
					}

					idImovelNaBase = null;

					try {

						idImovelNaBase = repositorioImovel
								.recuperarMatriculaImovel(matriculaImovel);

					} catch (ErroRepositorioException e) {
						e.printStackTrace();
						throw new ControladorException("erro.sistema", e);
					}

					// Se o id do imovel pesquisado na base for diferente de
					// nulo
					if (idImovelNaBase == null) {
						descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
					}
				}

				// Valida mes/ano referencia
				anoMesReferencia = Util.validarValorNaoNumerico(registro
						.getIdPagamento3());

				if (!anoMesReferencia) {
					anoMes = Integer.parseInt(registro.getIdPagamento3());
				} else {
					descricaoOcorrencia = "ANO/MÊS DE REFERÊNCIA DA CONTA INVÁLIDA";
				}

				if (descricaoOcorrencia.equals("OK")) {

					Integer idLocalidade = null;

					Integer idConta = null;

					Imovel imovel = new Imovel();
					imovel.setId(idImovelNaBase);

					try {

						idConta = repositorioFaturamento
								.pesquisarExistenciaContaComSituacaoAtual(
										imovel, anoMes);

					} catch (ErroRepositorioException e) {
						e.printStackTrace();
						throw new ControladorException("erro.sistema", e);
					}
					
					/*
                     * Alterado por Raphael Rossiter em 10/01/2008 - Analistas: Eduardo e Aryed
                     * OBJ: Gerar os pagamentos associados com a localidade da conta e NÃO com
                     * a localidade do imóvel.
                     */
                    if (idConta != null) {
                    	
                    	try {
                            idLocalidade = repositorioLocalidade
                            .pesquisarIdLocalidadePorConta(idConta);

                        } catch (ErroRepositorioException e) {
                            throw new ControladorException("erro.sistema", e);
                        }
                    }
                    else{
                    	
                    	try {
                            idLocalidade = repositorioLocalidade
                            .pesquisarIdLocalidade(idImovelNaBase);

                        } catch (ErroRepositorioException e) {
                            throw new ControladorException("erro.sistema", e);
                        }
                    }

					/*
					 * if (idConta == null || idConta.equals("")) {
					 * descricaoOcorrencia = "CONTA INEXISTENTE"; }
					 */

					Pagamento pagamento = this
							.processarPagamentosCodigoBarrasTipoPagamento(
									anoMes, anoMesPagamento, sistemaParametro,
									valorPagamento, dataPagamento, idConta,
									idLocalidade, idFormaArrecadacao,
									idImovelNaBase, imovel);

					colecaoPagamentos.add(pagamento);
					
				} else {

					// Atribui o valor 2(NÃO) ao indicador aceitacao registro
					indicadorAceitacaoRegistro = "2";
				}
				
				//Seta os parametros que serão retornados
				pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
				pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
				pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

				// ** Caso o tipo do documento (igual 02) - Fatura
			} else if (registro.getIdPagamento1().equalsIgnoreCase("02")) {

				// Valida o codigo do qualificador
				boolean codigoQualificadorInvalido = Util
						.validarValorNaoNumerico(registroHelperCodigoBarras
								.getRegistroHelperCodigoBarrasTipoPagamento()
								.getIdPagamento2());

				Integer qtdFaturaPorQualificador = null;
				Short codigoQualificador = null;

				if (codigoQualificadorInvalido) {
					descricaoOcorrencia = "CÓDIGO DO QUALIFICADOR NÃO NUMÉRICO";
				} else {

					codigoQualificador = new Short(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());

					// Verificando o qualificador
					try {

						qtdFaturaPorQualificador = repositorioFaturamento
								.pesquisarQuantidadeFaturaPorQualificador(codigoQualificador);

					} catch (ErroRepositorioException e) {
						e.printStackTrace();
						throw new ControladorException("erro.sistema", e);
					}

					if (qtdFaturaPorQualificador < 1) {
						descricaoOcorrencia = "QUALIFICADOR NÃO CADASTRADO";
					}
				}

				// Valida mes/ano referencia
				anoMesReferencia = Util.validarValorNaoNumerico(registro
						.getIdPagamento3());

				if (!anoMesReferencia) {
					anoMes = Integer.parseInt(registro.getIdPagamento3());
				} else {
					descricaoOcorrencia = "ANO/MÊS DE REFERÊNCIA DA CONTA INVÁLIDA";
				}

				if (descricaoOcorrencia.equals("OK")) {

					// Verificando fatura do qualificador
					Fatura faturaQualificador = null;

					faturaQualificador = this.getControladorFaturamento()
							.pesquisarFaturaPorQualificador(codigoQualificador,
									anoMes, valorPagamento);

					if (faturaQualificador != null) {

						Collection faturaItens = null;

						try {
							faturaItens = repositorioFaturamento
									.pesquisarFaturaItem(faturaQualificador
											.getId());

						} catch (ErroRepositorioException e) {
							e.printStackTrace();
							throw new ControladorException("erro.sistema", e);
						}

						// Verifica se a coleção é diferente de nula
						if (faturaItens != null && !faturaItens.isEmpty()) {

							Iterator faturaItensIterator = faturaItens
									.iterator();

							while (faturaItensIterator.hasNext()) {

								Object[] faturaItem = (Object[]) faturaItensIterator
										.next();

								Pagamento pagamento = this
										.processarPagamentosCodigoBarrasTipoFatura(
												faturaItem, anoMes,
												anoMesPagamento,
												sistemaParametro,
												dataPagamento, idFormaArrecadacao);

								colecaoPagamentos.add(pagamento);
							}
						}
					} else {

						descricaoOcorrencia = "QUALIFICADOR INEXISTENTE";

						// Atribui o valor 2(NÃO) ao indicador aceitação
						// registro
						indicadorAceitacaoRegistro = "2";
					}

				} else {

					// Atribui o valor 2(NÃO) ao indicador aceitação registro
					indicadorAceitacaoRegistro = "2";
				}
				
				//Seta os parametros que serão retornados
				pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
				pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
				pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

				// ** Caso o tipo do documento - Reaviso de débito
			} else if (registro.getIdPagamento1().equalsIgnoreCase("04")) {

				matriculaImovelInvalida = Util.validarValorNaoNumerico(registro
						.getIdPagamento2());

				if (matriculaImovelInvalida) {
					descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
				} else {

					// Verifica se existe a matricula do imóvel na base
					matriculaImovel = new Integer(registro.getIdPagamento2());

					// [FS0008] - Calcular Digito Verificador da Matricula
					if (matriculaImovel != null) {

						int digitoModulo11 = Util
								.obterDigitoVerificadorModulo11(""
										+ matriculaImovel);

						matriculaImovel = new Integer(matriculaImovel
								.toString()
								+ digitoModulo11);

					}

					idImovelNaBase = null;

					try {

						idImovelNaBase = repositorioImovel
								.recuperarMatriculaImovel(matriculaImovel);

					} catch (ErroRepositorioException e) {
						e.printStackTrace();
						throw new ControladorException("erro.sistema", e);
					}

					// se o id do imovel pesquisado na base for diferente de
					// nulo
					if (idImovelNaBase == null) {
						descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
					}
				}

				if (descricaoOcorrencia.equals("OK")) {

					Integer idLocalidade = null;

					Collection cobrancaDocumentoItens = null;

					Object[] parmsDocumentoCobranca = null;

					// int numeroSequencialDocumento = 999999999;

					int numeroSequencialDocumento = Integer.parseInt(registro
							.getIdPagamento5());

					try {

						cobrancaDocumentoItens = repositorioCobranca
								.pesquisarCobrancaDocumentoItem(idImovelNaBase,
										numeroSequencialDocumento);

						parmsDocumentoCobranca = repositorioCobranca
								.pesquisarParmsCobrancaDocumento(
										idImovelNaBase,
										numeroSequencialDocumento);

					} catch (ErroRepositorioException e) {
						e.printStackTrace();
						throw new ControladorException("erro.sistema", e);
					}

					// Caso exista documento de cobrança
					if (parmsDocumentoCobranca != null) {

						Integer idCobrancaDocumento = null;
						BigDecimal valorAcrescimo = new BigDecimal("0.00");
						BigDecimal valorDesconto = new BigDecimal("0.00");
						Date dataEmissao = null;
						BigDecimal valorTaxa = new BigDecimal("0.00");
						Integer idDocumentoTipo = null;

						if (parmsDocumentoCobranca[0] != null) {
							valorAcrescimo = ((BigDecimal) parmsDocumentoCobranca[0]);
						}

						if (parmsDocumentoCobranca[1] != null) {
							valorDesconto = ((BigDecimal) parmsDocumentoCobranca[1]);
						}

						if (parmsDocumentoCobranca[2] != null) {
							dataEmissao = ((Date) parmsDocumentoCobranca[2]);
						}

						if (parmsDocumentoCobranca[3] != null) {
							idCobrancaDocumento = ((Integer) parmsDocumentoCobranca[3]);
						}

						if (parmsDocumentoCobranca[4] != null) {

							valorTaxa = ((BigDecimal) parmsDocumentoCobranca[4]);
						}
						
						
						/*
	                     * Alterado por Raphael Rossiter em 10/01/2008 - Analistas: Eduardo e Aryed
	                     * OBJ: Gerar os pagamentos associados com a localidade do document de cobrança e NÃO com
	                     * a localidade do imóvel.
	                     */
						if (parmsDocumentoCobranca[5] != null) {

							idLocalidade = ((Integer) parmsDocumentoCobranca[5]);
						}
						else{
							
							try {
	                            idLocalidade = repositorioLocalidade
	                            .pesquisarIdLocalidade(idImovelNaBase);

	                        } catch (ErroRepositorioException e) {
	                            throw new ControladorException("erro.sistema", e);
	                        }
	                        
						}
						if (parmsDocumentoCobranca[6] != null) {
							idDocumentoTipo = ((Integer) parmsDocumentoCobranca[6]);
						}
						
						// Caso o valor de acrescimo for maior que zero
						if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {

							/*
							 * [SB0008 - Alterar vencimento dos itens do
							 * documento de cobrança]
							 */
							alterarVencimentoItensDocumentoCobranca(
									idCobrancaDocumento, dataEmissao);

						}

						/*
						 * caso o valor de acrescimos seja maior que o valor de
						 * descontos
						 */
						if (valorAcrescimo.compareTo(valorDesconto) == 1) {

							valorAcrescimo = valorAcrescimo
									.subtract(valorDesconto);

							valorDesconto = new BigDecimal("0.00");
						} else {
							valorDesconto = valorDesconto
									.subtract(valorAcrescimo);

							valorAcrescimo = new BigDecimal("0.00");
						}

						// Caso o valor de acrescimo for maior que zero
						if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {

							/*
							 * [SB0005 - Processar Recebimento de Acrescimos por
							 * impontualidade
							 */
							Pagamento pagamento = processarRecebimentoAcrescimosImpontualidade(
									idCobrancaDocumento, dataPagamento,
									valorAcrescimo, idImovelNaBase,
									idLocalidade, sistemaParametro,
									idFormaArrecadacao, idDocumentoTipo);

							colecaoPagamentos.add(pagamento);

						}

						// Caso o valor de desconto for maior que zero
						if (valorDesconto.compareTo(new BigDecimal("0.00")) == 1) {

							// [SB0006 - Processar Desconto concedido no
							// documento de cobrança]
							Devolucao devolucao = processarDescontoConcedidoDocumentoCobranca(
								idCobrancaDocumento, dataPagamento,
								valorDesconto, idImovelNaBase,
								idLocalidade, sistemaParametro,
								idFormaArrecadacao, idDocumentoTipo);
								
								colecaoDevolucoes.add(devolucao);

						}

						// Caso o valor de desconto for maior que zero
						if (valorTaxa.compareTo(new BigDecimal("0.00")) == 1) {

							// [SB0007 - Processar Taxa documento cobrança]
							Pagamento pagamento = processarTaxaDocumentoCobranca(
									idCobrancaDocumento, dataPagamento,
									valorTaxa, idImovelNaBase, idLocalidade,
									sistemaParametro, idFormaArrecadacao, idDocumentoTipo);

							colecaoPagamentos.add(pagamento);

						}

						// verifica se a coleção é diferente de nula
						if (cobrancaDocumentoItens != null
								&& !cobrancaDocumentoItens.isEmpty()) {

							Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens
									.iterator();

							while (cobrancaDocumentoItensIterator.hasNext()) {

								Object[] cobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator
										.next();

								Integer idContaPesquisa = null;
								Integer idContaGeralPesquisa = null;
								Integer idGuiaPagamento = null;
								Integer idDebitoACobrar = null;
								BigDecimal valorItemCobrado = null;
								int contaReferencia = 0;
								Integer idDebitoTipo = null;
								Integer idGuiaPagamentoGeralPesquisa = null;
								Integer idDebitoACobrarGeralPesquisa = null;

								// verifica o id da conta
								if (cobrancaDocumentoItem[0] != null) {

									idContaPesquisa = (Integer) cobrancaDocumentoItem[0];
									idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[0];

									// Referência conta
									if (cobrancaDocumentoItem[4] != null) {
										contaReferencia = (Integer) cobrancaDocumentoItem[4];
									}
								} else {

									/*
									 * caso não exista na conta então pesquisa
									 * na conta histórico.
									 */

									if (cobrancaDocumentoItem[10] != null) {
										idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[10];
									}

									// Referência conta histórico
									if (cobrancaDocumentoItem[5] != null) {
										contaReferencia = (Integer) cobrancaDocumentoItem[5];
									}
								}

								// Verifica o id da guia pagamento
								if (cobrancaDocumentoItem[1] != null) {

									idGuiaPagamento = (Integer) cobrancaDocumentoItem[1];
									idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[1];

								} else {

									/*
									 * caso não exista no guia pagamento então
									 * pesquisa no guia pagamento histórico
									 */
									if (cobrancaDocumentoItem[11] != null) {
										idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[11];
									}
								}

								// Verifica o id do debito a cobrar
								if (cobrancaDocumentoItem[2] != null) {

									idDebitoACobrar = (Integer) cobrancaDocumentoItem[2];
									idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[2];

								} else {

									/*
									 * caso não exista no debito a cobrar então
									 * pesquisa no guia pagamento histórico
									 */
									if (cobrancaDocumentoItem[12] != null) {
										idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[12];
									}
								}

								// verifica o valor do item cobrado da cobranca
								// documento item
								if (cobrancaDocumentoItem[3] != null) {
									valorItemCobrado = (BigDecimal) cobrancaDocumentoItem[3];
								}

								// Se o id da conta for igual a null
								if (idContaGeralPesquisa == null) {

									// Caso exista guia de pagamento
									if (idGuiaPagamentoGeralPesquisa != null) {

										// verifica o id do debito tipo se é da
										// guia
										if (cobrancaDocumentoItem[6] != null) {
											idDebitoTipo = (Integer) cobrancaDocumentoItem[6];
										} else {

											/*
											 * Caso não exista no guia pagamento
											 * então pesquisa no guia pagamento
											 * histórico
											 */
											if (cobrancaDocumentoItem[7] != null) {
												idDebitoTipo = (Integer) cobrancaDocumentoItem[7];
											}
										}
									}

									// Caso exista debito a cobrar
									if (idDebitoACobrarGeralPesquisa != null) {

										// verifica o id do debito tipo no
										// debito a cobrar
										if (cobrancaDocumentoItem[8] != null) {

											idDebitoTipo = (Integer) cobrancaDocumentoItem[8];

										} else {

											/*
											 * Caso não exista no debito a
											 * cobrar então pesquisa no debito a
											 * cobrar histórico
											 */
											if (cobrancaDocumentoItem[9] != null) {
												idDebitoTipo = (Integer) cobrancaDocumentoItem[9];
											}
										}
									}
								}

								// Cria o objeto pagamento para setar os
								Pagamento pagamento = new Pagamento();

								if (contaReferencia != 0) {

									pagamento
											.setAnoMesReferenciaPagamento(contaReferencia);

								} else {

									pagamento
											.setAnoMesReferenciaPagamento(null);
								}

								/*
								 * Caso o ano mes da data de dedito seja maior
								 * que o ano mes de arrecadação da tabela
								 * sistema parametro então seta o ano mes da
								 * data de debito
								 */
								if (anoMesPagamento > sistemaParametro
										.getAnoMesArrecadacao()) {

									pagamento
											.setAnoMesReferenciaArrecadacao(anoMesPagamento);

								} else {

									/*
									 * caso contrario seta o o ano mes
									 * arrecadação da tabela sistema parametro
									 */
									pagamento
											.setAnoMesReferenciaArrecadacao(sistemaParametro
													.getAnoMesArrecadacao());
								}

								pagamento.setValorPagamento(valorItemCobrado);
								pagamento.setDataPagamento(dataPagamento);
								pagamento.setPagamentoSituacaoAtual(null);
								pagamento.setPagamentoSituacaoAnterior(null);

								if (idDebitoTipo != null) {

									DebitoTipo debitoTipo = new DebitoTipo();
									debitoTipo.setId(idDebitoTipo);
									pagamento.setDebitoTipo(debitoTipo);

								} else {
									pagamento.setDebitoTipo(null);
								}

								// Verifica se o id da conta é diferente de nulo
								if (idContaGeralPesquisa != null) {
									
									if (idContaPesquisa != null) {
										
										ContaGeral conta = new ContaGeral();
										conta.setId(idContaPesquisa);
										
										pagamento.setContaGeral(conta);
										
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade da própria conta e não
										 * com a localidade do documento de cobrança
										 */
										try {
						                    idLocalidade = repositorioLocalidade
						                    .pesquisarIdLocalidadePorConta(idContaPesquisa);

						                } catch (ErroRepositorioException e) {
						                    throw new ControladorException("erro.sistema", e);
						                }
									} 
									else {
										
										pagamento.setContaGeral(null);
										
									}

									DocumentoTipo documentoTipo = new DocumentoTipo();
									documentoTipo.setId(DocumentoTipo.CONTA);
									pagamento.setDocumentoTipo(documentoTipo);
								} 
								else {
									
									pagamento.setContaGeral(null);
									
								}
								
								// Verifica se o id da guia de pagamento é diferente de nulo
								if (idGuiaPagamentoGeralPesquisa != null) {
									
									if (idGuiaPagamento != null) {
										
										GuiaPagamento guiaPagamento = new GuiaPagamento();
										guiaPagamento.setId(idGuiaPagamento);
										pagamento.setGuiaPagamento(guiaPagamento);
										
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade da própria guia e não
										 * com a localidade do documento de cobrança
										 */
										try {
						                    idLocalidade = repositorioLocalidade
						                    .pesquisarIdLocalidadePorGuiaPagamento(idGuiaPagamento);

						                } catch (ErroRepositorioException e) {
						                    throw new ControladorException("erro.sistema", e);
						                }
									} 
									else {
										
										pagamento.setGuiaPagamento(null);
										
									}
									
									DocumentoTipo documentoTipo = new DocumentoTipo();
									documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);	
									pagamento.setDocumentoTipo(documentoTipo);

								} 
								else {
									
									pagamento.setGuiaPagamento(null);
									
								}

								// Verifica se o id do debito a cobrar é diferente de nulo
								if (idDebitoACobrarGeralPesquisa != null) {
									
									if (idDebitoACobrar != null) {
										
										DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
										debitoACobrarGeral.setId(idDebitoACobrar);
										
										pagamento.setDebitoACobrarGeral(debitoACobrarGeral);
										
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade do próprio debito a cobrar e não
										 * com a localidade do documento de cobrança
										 */
										try {
											
											idLocalidade = repositorioLocalidade
						                    .pesquisarIdLocalidadePorDebitoACobrar(idDebitoACobrar);
											
											// atualiza a situação atual para cancelada
											repositorioFaturamento
											.atualizarSituacaoAtualDebitoACobrar(idDebitoACobrar);
											
										} catch (ErroRepositorioException e) {
											throw new ControladorException("erro.sistema", e);
										}

									} 
									else {
										
										pagamento.setDebitoACobrarGeral(null);
										
									}
									
									DocumentoTipo documentoTipo = new DocumentoTipo();
									documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
									pagamento.setDocumentoTipo(documentoTipo);

								} 
								else {
									
									pagamento.setDebitoACobrarGeral(null);
									
								}

								// Verifica se o id da conta é diferente de nulo
								if (idLocalidade != null) {

									Localidade localidade = new Localidade();
									localidade.setId(idLocalidade);
									pagamento.setLocalidade(localidade);

								} else {
									pagamento.setLocalidade(null);
								}

								pagamento.setAvisoBancario(null);

								if (idImovelNaBase != null) {
									Imovel imovel = new Imovel();
									imovel.setId(idImovelNaBase);
									pagamento.setImovel(imovel);

								} else {
									pagamento.setImovel(null);
								}

								pagamento.setArrecadadorMovimentoItem(null);

								ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
								arrecadacaoForma.setId(idFormaArrecadacao);
								pagamento.setArrecadacaoForma(arrecadacaoForma);
								pagamento.setCliente(null);
								pagamento.setUltimaAlteracao(new Date());

								pagamento.setFatura(null);
								CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
								cobrancaDocumento.setId(idCobrancaDocumento);
								pagamento.setCobrancaDocumento(cobrancaDocumento);
								
								DocumentoTipo documentoTipoAgregador = new DocumentoTipo();
								documentoTipoAgregador.setId(idDocumentoTipo);
								pagamento.setDocumentoTipoAgregador(documentoTipoAgregador);
								
								colecaoPagamentos.add(pagamento);

							}
						}

					} else {

						descricaoOcorrencia = "DOCUMENTO DE COBRANÇA INEXISTENTE";
						indicadorAceitacaoRegistro = "2";
					}

				} else {

					// atribui o valor 2(NÃO) ao indicador aceitacao registro
					indicadorAceitacaoRegistro = "2";
				}
				
				//Seta os parametros que serão retornados
				pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
				pagamentoHelperCodigoBarras.setColecaoDevolucao(colecaoDevolucoes);
				pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
				pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

			}

			// ** Caso o tipo do documento - Documento de Cobranca
			else if (registro.getIdPagamento1().equalsIgnoreCase("10")) {
				/*********************************************************
				* CRC5063
				* autor: Ivan Sergio
				* analista: Rosana
				* data: 01/10/2010
				*********************************************************/
				pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasDocumentoCobrancaTipo10(
						registroHelperCodigoBarras, sistemaParametro,
						dataPagamento, anoMesPagamento,
						valorPagamento, idFormaArrecadacao,
						usuarioLogado);
				/********************************************************/
			}
			/*
			 * GUIA PAGAMENTO - CLIENTE RESPONSÁVEL
			 */
			else if (registro.getIdPagamento1().equalsIgnoreCase("06")) {

				pagamentoHelperCodigoBarras = this
						.processarPagamentosCodigoBarrasGuiaPagamentoCliente(
								registroHelperCodigoBarras, sistemaParametro,
								dataPagamento, anoMesPagamento, valorPagamento,
								idFormaArrecadacao);
			}

			/*
			 * FATURA DE CLIENTE RESPONSÁVEL
			 */
			else if (registro.getIdPagamento1().equalsIgnoreCase("07")) {

				pagamentoHelperCodigoBarras = this
						.processarPagamentosCodigoBarrasClienteResponsavel(
								registroHelperCodigoBarras, sistemaParametro,
								dataPagamento, anoMesPagamento, valorPagamento,
								idFormaArrecadacao);
			}

			/*
			 * DOCUMENTO DE COBRANÇA (EXTRATO DE CLIENTE RESPONSÁVEL)
			 */
			else if (registro.getIdPagamento1().equalsIgnoreCase("08")) {

				pagamentoHelperCodigoBarras = this
						.processarPagamentosCodigoBarrasDocumentoCobrancaTipo8(
								registroHelperCodigoBarras, sistemaParametro,
								dataPagamento, anoMesPagamento, valorPagamento,
								idFormaArrecadacao);
			}

			/*
			 * GUIA PAGAMENTO - IMÓVEL
			 */
			else if (registro.getIdPagamento1().equalsIgnoreCase("09")) {

				pagamentoHelperCodigoBarras = this
						.processarPagamentosCodigoBarrasGuiaPagamento(
								registroHelperCodigoBarras, sistemaParametro,
								dataPagamento, anoMesPagamento, valorPagamento,
								idFormaArrecadacao);
			}
			else{
			
				/*
				 * CONTA - RECEBIMENTO DOS DOCUMENTOS QUE FORAM GERADOS COM O CODIGO DE BARRAS DA COMPESA
				 */
				registroHelperCodigoBarras = distribuirDadosCodigoBarrasGeral(codigoBarras);
				
				registroHelperCodigoBarras.setTipoPagamento(codigoBarras.substring(43, 44).trim());
				
				String idPagamento = codigoBarras.substring(19, 43);
				
				RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = super.distribuirDadosCodigoBarrasPorTipoPagamento(
				idPagamento, registroHelperCodigoBarras.getTipoPagamento().trim(), registroHelperCodigoBarras.getIdEmpresa());

				registroHelperCodigoBarras.setRegistroHelperCodigoBarrasTipoPagamento(registroHelperCodigoBarrasTipoPagamento);
				
				if (registroHelperCodigoBarras.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA.toString())){
					
					pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta(registroHelperCodigoBarras, sistemaParametro,
							dataPagamento, anoMesPagamento, valorPagamento, idFormaArrecadacao);
				}
				else{
					
					// atribui o valor 2(NÃO) ao indicador aceitacao registro
					colecaoPagamentos = new ArrayList();
					pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
					
					pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
					pagamentoHelperCodigoBarras.setDescricaoOcorrencia("CÓDIGO DE BARRAS COM TIPO DE PAGAMENTO INVÁLIDO");
					pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro("2");
					
				}
			
			}

		}

		return pagamentoHelperCodigoBarras;
	}

	/**
	 * retorna o objeto distribuido de acordo comj o tipo de pagamento
	 * 
	 * [UC0264] - Distribuir Dados do Código de Barras
	 * 
	 * [SF0001] - Distribuir Pagamento de Conta [SF0002] - Distribuir Pagamento
	 * de Guia de Pagamento [SF0003] - Distribuir Pagamento de Documento de
	 * Cobramça [SF0004] - Distribuir Pagamento de Fatura do Cliente Responsável
	 * 
	 * Autor: Sávio Luiz, Raphael Rossiter Data: 15/02/2006, 03/05/2007
	 */

	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();

		// [SB0006] - Distribuir Pagamento Legado CAER

		// Seta o tipo do pagamento
		/*if (tipoPagamento <= 9) {
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1("0"
					+ tipoPagamento);
		} else {
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(String
					.valueOf(tipoPagamento));
		}*/
		
		//Seta o tipo do pagamento
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(tipoPagamento);
		
		int tipoPagamentoParaComparacao = Integer.parseInt(tipoPagamento.trim());

		// seta a identificação
		String identificacao = idPagamento;

		// Caso o tipo do pagamento (igual 01) - Conta e Segunda Via
		if (tipoPagamentoParaComparacao == 1) {

			// seta a matricula do imovel
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento2(identificacao.substring(0, 6).trim());
			// seta ano e mes
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento3(identificacao.substring(6, 12).trim());
			// seta o codigo origem da conta
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento4(identificacao.substring(12, 14).trim());
			// seta o numero do documento + campo (G05.8)
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento5(identificacao.substring(14, 23).trim());
		}

		// Caso o tipo do pagamento (igual 02) - Fatura
		else if (tipoPagamentoParaComparacao == 2) {

			// seta a qualificação
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento2(identificacao.substring(0, 3).trim());
			// seta ano e mes
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento3(identificacao.substring(3, 9).trim());
			// seta o codigo origem da conta
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento4(identificacao.substring(9, 23).trim());

		}

		// Caso o tipo do pagamento (igual 10) - documento de cobrança NOVO
		else if (tipoPagamentoParaComparacao == 10) {
			// seta a matricula do imovel
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento2(identificacao.substring(0, 6).trim());
			// seta o numero do documento
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento5(identificacao.substring(6, 22).trim());

		}

		// Caso o tipo do pagamento (igual 04) - documento de cobrança LEGADO
		else if (tipoPagamentoParaComparacao == 4) {
			// seta a matricula do imovel
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento2(identificacao.substring(0, 6).trim());
			// seta o numero do documento
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento5(identificacao.substring(6, 23).trim());

		}

		// Caso o tipo do pagamento (igual 06) OU tipo do pagamento (igual 09) -
		// Guia de Pagamento
		else if (tipoPagamentoParaComparacao == 6 || tipoPagamentoParaComparacao == 9) {

			// Código da Localidade
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento2(identificacao.substring(0, 3).trim());

			// Matrícula do imóvel
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento3(identificacao.substring(3, 11).trim());

			// Não Utilizado (3)
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento4(identificacao.substring(11, 13).trim());

			// Código do Tipo do Débito
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento5(identificacao.substring(13, 17).trim());

			// Ano da emissão da Guia de Pagamento
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento6(identificacao.substring(17, 21).trim());

			// Não Utilizado (2)

		}

		// Caso o tipo do pagamento (igual 07) - Fatura do Cliente Responsável
		else if (tipoPagamentoParaComparacao == 7) {

			// Código do Cliente
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento2(identificacao.substring(0, 9).trim());

			// Não Utilizado (1)
			/*
			 * registroHelperCodigoBarrasTipoPagamento
			 * .setIdPagamento3(identificacao.substring(9, 10).trim());
			 */

			// Mes e Ano de referência da conta
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento4(identificacao.substring(9, 15).trim());

			// Dígito verificador da conta no módulo 10
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento5(identificacao.substring(15, 16).trim());

			// Sequencial da fatura do cliente responsável
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento6(identificacao.substring(16, 22).trim());

		}

		// Caso o tipo do pagamento (igual 08) - Doc. de Cobrança - Cliente
		// Responsável
		else if (tipoPagamentoParaComparacao == 8) {

			// Não Utilizado (3)
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento2(identificacao.substring(0, 3).trim());

			// Código do Cliente
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento3(identificacao.substring(3, 11).trim());

			// Sequencial do documento de cobrança
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento4(identificacao.substring(11, 20).trim());

			// Código do tipo de documento
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento5(identificacao.substring(20, 22).trim());

			// Não Utilizado (1)
			registroHelperCodigoBarrasTipoPagamento
					.setIdPagamento6(identificacao.substring(22, 23).trim());

		}

		return registroHelperCodigoBarrasTipoPagamento;
	}

	/**
	 * [UC0264] - Distribuir Dados do Código de Barras 
	 *
	 * @author Sávio Luiz, Rafael Corrêa
	 * @date 15/02/2006, 12/05/2008
	 *
	 * @param codigoBarras
	 * @return RegistroHelperCodigoBarras
	 */
	public RegistroHelperCodigoBarras distribuirDadosCodigoBarras(
			String codigoBarras) {

		// instancia o objeto de código de barras, setando os valores que são iguais para todas as empresas
		RegistroHelperCodigoBarras registroHelperCodigoBarras = distribuirDadosCodigoBarrasGeral(codigoBarras);
		
		registroHelperCodigoBarras.setTipoPagamento(codigoBarras.substring(19,
				21).trim());

		// recupera o id pagamento da string
		String idPagamento = codigoBarras.substring(21, 44);
		
		// recupera o tipo pagamento e passa para int
		/*int tipoPagamento = Integer.parseInt(registroHelperCodigoBarras
		.getTipoPagamento().trim());*/
		
		// chama o método distribuirDadosCodigoBarrasPorTipoPagamento para
		// distribuir os dados de acordo com o tipo de pagamento
		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = distribuirDadosCodigoBarrasPorTipoPagamento(
		idPagamento, registroHelperCodigoBarras.getTipoPagamento().trim(), registroHelperCodigoBarras.getIdEmpresa());

		registroHelperCodigoBarras
				.setRegistroHelperCodigoBarrasTipoPagamento(registroHelperCodigoBarrasTipoPagamento);

		return registroHelperCodigoBarras;
	}
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador com os seguintes
	 * dados: 1 - Código do Registro 2 - Identificação do Imóvel/Cliente 3 -
	 * Ocorrência 4 - Indicador de Aceitação 5 - Descrição do Indicador de
	 * Aceitação
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter
	 * @data 20/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItemHelper>
	 */
	public Collection<ArrecadadorMovimentoItemHelper> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia)
			throws ControladorException {

		Collection<ArrecadadorMovimentoItemHelper> retorno = new ArrayList();
		Collection<ArrecadadorMovimentoItem> colecaoArrecadadorMovimentoItens = null;
		ArrecadadorMovimentoItem arrecadadorMovimentoItem = null;
		ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper = null;

		/*
		 * Seleciona os itens de um determinado movimento
		 */
		try {

			colecaoArrecadadorMovimentoItens = repositorioArrecadacao
					.consultarItensMovimentoArrecadador(arrecadadorMovimento,
							idImovel, indicadorAceitacao, descricaoOcorrencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoArrecadadorMovimentoItens != null
				&& !colecaoArrecadadorMovimentoItens.isEmpty()) {

			Iterator iteratorColecaoArrecadadorMovimentoItens = colecaoArrecadadorMovimentoItens
					.iterator();

			while (iteratorColecaoArrecadadorMovimentoItens.hasNext()) {

				arrecadadorMovimentoItem = (ArrecadadorMovimentoItem) iteratorColecaoArrecadadorMovimentoItens
						.next();

				arrecadadorMovimentoItemHelper = new ArrecadadorMovimentoItemHelper();

				arrecadadorMovimentoItemHelper.setId(arrecadadorMovimentoItem
						.getId());

				/*
				 * (RGCD_CDREGISTROCODIGO da tabela REGISTRO_CODIGO com RGCD_ID =
				 * RGCD_ID da tabela ARRECADADOR_MOVIMENTO_ITEM)
				 */
				if (arrecadadorMovimentoItem.getRegistroCodigo() != null) {
					arrecadadorMovimentoItemHelper
							.setCodigoRegistro(arrecadadorMovimentoItem
									.getRegistroCodigo().getCodigo());
				}

				/*
				 * Caso o código do registro corresponda a "B", "C", "E" ou "F",
				 * exibir a identificação do cliente na empresa retornada pelo
				 * [UC0262]
				 */
				if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_B)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoB registroHelperCodigoB = (RegistroHelperCodigoB) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoB
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoB(registroHelperCodigoB);
				} else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_C)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoC registroHelperCodigoC = (RegistroHelperCodigoC) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoC
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoC(registroHelperCodigoC);

				} else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_E)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoE registroHelperCodigoE = (RegistroHelperCodigoE) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoE
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoE(registroHelperCodigoE);

				} else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_F)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoF registroHelperCodigoF = (RegistroHelperCodigoF) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoF
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoF(registroHelperCodigoF);

					int tamanho = registroHelperCodigoF.getValorDebito()
							.length();

					String valorDebito = registroHelperCodigoF.getValorDebito()
							.substring(0, (tamanho - 2))
							+ "."
							+ registroHelperCodigoF.getValorDebito().substring(
									(tamanho - 2), tamanho);

					arrecadadorMovimentoItemHelper.setVlMovimento(Util
							.formatarMoedaReal(new BigDecimal(valorDebito)));
				}

				/*
				 * Caso o código do registro corresponda a "G"
				 * 
				 * Distribui os dados do código de barras [UC0264] - Distribuir
				 * Dados co Código de Barras passando o código de barras
				 * retornado pelo [UC0262]- Distribuir Dados do Registro de
				 * Movimento do Arrecadador.
				 * 
				 * Exibir o tipo de pagamento retornado pelo [UC0262] -
				 * Distribuir Dados co Código de Barras
				 */
				else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_G)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoG registroHelperCodigoG = (RegistroHelperCodigoG) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoG(registroHelperCodigoG);

					int tamanho = registroHelperCodigoG.getValorRecebido()
							.length();

					String valorRecebido = registroHelperCodigoG
							.getValorRecebido().substring(0, (tamanho - 2))
							+ "."
							+ registroHelperCodigoG.getValorRecebido()
									.substring((tamanho - 2), tamanho);

					arrecadadorMovimentoItemHelper.setVlMovimento(Util
							.formatarMoedaReal(new BigDecimal(valorRecebido)));

					/*
					 * CONTA = "01" FATURA = "02" REAVISO DE DÉBITOS = "04" GUIA
					 * PAGAMENTO CLIENTE = "06" GUIA PAGAMENTO IMOVEL = "09"
					 * DOCUMENTO COBRANÇA CLIENTE RESPONSAVEL = "08" FATURA
					 * CLIENTE RESPONSAVEL = "07" DOCUMENTO COBRANÇA IMOVEL =
					 * "10"
					 */
					if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CAER))) {
						
						

						// Alterado por Sávio Luiz Data:09/01/2008 (Analista:Rosana).
						// Recupera o o cliente da fatura
						Short codigoQualifica = new Short(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());
						
						Integer anoMesReferencia = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());
						
						
						FiltroFatura filtroFatura = new FiltroFatura();
						filtroFatura.adicionarParametro(new ParametroSimples(FiltroFatura.ANO_MES_REFERENCIA,anoMesReferencia));
						filtroFatura.adicionarParametro(new ParametroSimples(FiltroFatura.CODIGO_QUALIFICA,codigoQualifica));
						Collection colecaoFatura = getControladorUtil().pesquisar(filtroFatura,Fatura.class.getName());
						Fatura fatura = (Fatura)Util.retonarObjetoDeColecao(colecaoFatura);
						
						if (fatura != null) {
							arrecadadorMovimentoItemHelper
							.setIdentificacao(""+fatura.getCliente().getId());
						}

						

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_REAVISO_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_REAVISO_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER))) {

						arrecadadorMovimentoItemHelper
								.setIdentificacao(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER))) {

						arrecadadorMovimentoItemHelper
								.setIdentificacao(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER))) {

						arrecadadorMovimentoItemHelper
								.setIdentificacao(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER);

					}

				}

				// (AMIT_DSOCORRENCIA)
				if (arrecadadorMovimentoItem.getDescricaoOcorrencia() != null) {
					arrecadadorMovimentoItemHelper
							.setOcorrencia(arrecadadorMovimentoItem
									.getDescricaoOcorrencia());
				}

				if (arrecadadorMovimentoItem.getIndicadorAceitacao() != null
						&& arrecadadorMovimentoItem
								.getIndicadorAceitacao()
								.equals(
										ArrecadadorMovimentoItem.INDICADOR_ACEITO)) {

					arrecadadorMovimentoItemHelper
							.setIndicadorAceitacao(arrecadadorMovimentoItem
									.getIndicadorAceitacao());

					// Colocado a pedido de Rosana em 08/04/2006
					if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
							&& !arrecadadorMovimentoItemHelper
									.getCodigoRegistro().equals(
											RegistroCodigo.CODIGO_C)) {

						arrecadadorMovimentoItemHelper
								.setDescricaoIndicadorAceitacao(ArrecadadorMovimentoItem.DESCRICAO_INDICADOR_ACEITO);
					}

				} else {

					arrecadadorMovimentoItemHelper
							.setIndicadorAceitacao(arrecadadorMovimentoItem
									.getIndicadorAceitacao());

					// Colocado a pedido de Rosana em 08/04/2006
					if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
							&& !arrecadadorMovimentoItemHelper
									.getCodigoRegistro().equals(
											RegistroCodigo.CODIGO_C)) {

						arrecadadorMovimentoItemHelper
								.setDescricaoIndicadorAceitacao(ArrecadadorMovimentoItem.DESCRICAO_INDICADOR_NAO_ACEITO);
					}

				}

				BigDecimal valorPagamento = null;
				try {
					valorPagamento = repositorioArrecadacao
							.recuperaValorPagamentoArrecadadorMovimentoItem(arrecadadorMovimentoItem
									.getId());
				} catch (ErroRepositorioException e) {
					e.printStackTrace();
				}

				arrecadadorMovimentoItemHelper.setVlPagamento(Util
						.formatarMoedaReal(valorPagamento));
				
				retorno.add(arrecadadorMovimentoItemHelper);
				
		
			}
		}

		if (retorno == null || retorno.isEmpty()) {
			throw new ControladorException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}

	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador com os seguintes
	 * dados: 1 - Código do Registro 2 - Identificação do Imóvel/Cliente 3 -
	 * Ocorrência 4 - Indicador de Aceitação 5 - Descrição do Indicador de
	 * Aceitação
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter
	 * @data 20/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItemHelper>
	 */
	public Collection<ArrecadadorMovimentoItemHelper> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia,
			String codigoArrecadacaoForma) throws ControladorException {

		Collection<ArrecadadorMovimentoItemHelper> retorno = new ArrayList();
		Collection<ArrecadadorMovimentoItem> colecaoArrecadadorMovimentoItens = null;
		ArrecadadorMovimentoItem arrecadadorMovimentoItem = null;
		ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper = null;

		/*
		 * Seleciona os itens de um determinado movimento
		 */
		try {

			colecaoArrecadadorMovimentoItens = repositorioArrecadacao
					.consultarItensMovimentoArrecadador(arrecadadorMovimento,
							idImovel, indicadorAceitacao, descricaoOcorrencia,
							codigoArrecadacaoForma);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoArrecadadorMovimentoItens != null
				&& !colecaoArrecadadorMovimentoItens.isEmpty()) {

			Iterator iteratorColecaoArrecadadorMovimentoItens = colecaoArrecadadorMovimentoItens
					.iterator();

			while (iteratorColecaoArrecadadorMovimentoItens.hasNext()) {

				arrecadadorMovimentoItem = (ArrecadadorMovimentoItem) iteratorColecaoArrecadadorMovimentoItens
						.next();

				arrecadadorMovimentoItemHelper = new ArrecadadorMovimentoItemHelper();

				arrecadadorMovimentoItemHelper.setId(arrecadadorMovimentoItem
						.getId());

				/*
				 * (RGCD_CDREGISTROCODIGO da tabela REGISTRO_CODIGO com RGCD_ID =
				 * RGCD_ID da tabela ARRECADADOR_MOVIMENTO_ITEM)
				 */
				if (arrecadadorMovimentoItem.getRegistroCodigo() != null) {
					arrecadadorMovimentoItemHelper
							.setCodigoRegistro(arrecadadorMovimentoItem
									.getRegistroCodigo().getCodigo());
				}

				/*
				 * Caso o código do registro corresponda a "B", "C", "E" ou "F",
				 * exibir a identificação do cliente na empresa retornada pelo
				 * [UC0262]
				 */
				if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_B)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoB registroHelperCodigoB = (RegistroHelperCodigoB) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoB
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoB(registroHelperCodigoB);
				} else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_C)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoC registroHelperCodigoC = (RegistroHelperCodigoC) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoC
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoC(registroHelperCodigoC);

				} else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_E)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoE registroHelperCodigoE = (RegistroHelperCodigoE) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoE
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoE(registroHelperCodigoE);

				} else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_F)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoF registroHelperCodigoF = (RegistroHelperCodigoF) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setIdentificacao(registroHelperCodigoF
									.getIdClienteEmpresa());
					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoF(registroHelperCodigoF);

					int tamanho = registroHelperCodigoF.getValorDebito()
							.length();

					String valorDebito = registroHelperCodigoF.getValorDebito()
							.substring(0, (tamanho - 2))
							+ "."
							+ registroHelperCodigoF.getValorDebito().substring(
									(tamanho - 2), tamanho);

					arrecadadorMovimentoItemHelper.setVlMovimento(Util
							.formatarMoedaReal(new BigDecimal(valorDebito)));
				}

				/*
				 * Caso o código do registro corresponda a "G"
				 * 
				 * Distribui os dados do código de barras [UC0264] - Distribuir
				 * Dados co Código de Barras passando o código de barras
				 * retornado pelo [UC0262]- Distribuir Dados do Registro de
				 * Movimento do Arrecadador.
				 * 
				 * Exibir o tipo de pagamento retornado pelo [UC0262] -
				 * Distribuir Dados co Código de Barras
				 */
				else if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
						&& arrecadadorMovimentoItemHelper.getCodigoRegistro()
								.equals(RegistroCodigo.CODIGO_G)) {

					// [UC0262] - Distribuir Dados do Registro de Movimento do
					// Arrecadador
					RegistroHelperCodigoG registroHelperCodigoG = (RegistroHelperCodigoG) this
							.distribuirdadosRegistroMovimentoArrecadador(
									arrecadadorMovimentoItem
											.getConteudoRegistro(), null);

					arrecadadorMovimentoItemHelper
							.setRegistroHelperCodigoG(registroHelperCodigoG);

					int tamanho = registroHelperCodigoG.getValorRecebido()
							.length();

					String valorRecebido = registroHelperCodigoG
							.getValorRecebido().substring(0, (tamanho - 2))
							+ "."
							+ registroHelperCodigoG.getValorRecebido()
									.substring((tamanho - 2), tamanho);

					arrecadadorMovimentoItemHelper.setVlMovimento(Util
							.formatarMoedaReal(new BigDecimal(valorRecebido)));

					/*
					 * CONTA = "01" FATURA = "02" REAVISO DE DÉBITOS = "04" GUIA
					 * PAGAMENTO CLIENTE = "06" GUIA PAGAMENTO IMOVEL = "09"
					 * DOCUMENTO COBRANÇA CLIENTE RESPONSAVEL = "08" FATURA
					 * CLIENTE RESPONSAVEL = "07" DOCUMENTO COBRANÇA IMOVEL =
					 * "10"
					 */
					if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CAER))) {
						
						

						// Alterado por Sávio Luiz Data:09/01/2008 (Analista:Rosana).
						// Recupera o o cliente da fatura
						Short codigoQualifica = new Short(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());
						
						Integer anoMesReferencia = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());
						
						
						FiltroFatura filtroFatura = new FiltroFatura();
						filtroFatura.adicionarParametro(new ParametroSimples(FiltroFatura.ANO_MES_REFERENCIA,anoMesReferencia));
						filtroFatura.adicionarParametro(new ParametroSimples(FiltroFatura.CODIGO_QUALIFICA,codigoQualifica));
						Collection colecaoFatura = getControladorUtil().pesquisar(filtroFatura,Fatura.class.getName());
						Fatura fatura = (Fatura)Util.retonarObjetoDeColecao(colecaoFatura);
						
						if (fatura != null) {
							arrecadadorMovimentoItemHelper
							.setIdentificacao(""+fatura.getCliente().getId());
						}

						

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_REAVISO_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_REAVISO_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER))) {

						arrecadadorMovimentoItemHelper
								.setIdentificacao(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER))) {

						arrecadadorMovimentoItemHelper
								.setIdentificacao(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento3());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER))) {

						arrecadadorMovimentoItemHelper
								.setIdentificacao(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER);

					} else if (registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getTipoPagamento()
							.equals(
									String
											.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER))) {

						Integer matriculaImovel = new Integer(
								registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getRegistroHelperCodigoBarrasTipoPagamento()
										.getIdPagamento2());

						// [FS0008] - Calcular Digito Verificador da Matricula
						if (matriculaImovel != null) {
							int digitoModulo11 = Util
									.obterDigitoVerificadorModulo11(""
											+ matriculaImovel);

							matriculaImovel = new Integer(matriculaImovel
									.toString()
									+ digitoModulo11);
						}

						arrecadadorMovimentoItemHelper
								.setIdentificacao(matriculaImovel.toString());

						arrecadadorMovimentoItemHelper
								.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER);

					}

				}

				// (AMIT_DSOCORRENCIA)
				if (arrecadadorMovimentoItem.getDescricaoOcorrencia() != null) {
					arrecadadorMovimentoItemHelper
							.setOcorrencia(arrecadadorMovimentoItem
									.getDescricaoOcorrencia());
				}

				if (arrecadadorMovimentoItem.getIndicadorAceitacao() != null
						&& arrecadadorMovimentoItem
								.getIndicadorAceitacao()
								.equals(
										ArrecadadorMovimentoItem.INDICADOR_ACEITO)) {

					arrecadadorMovimentoItemHelper
							.setIndicadorAceitacao(arrecadadorMovimentoItem
									.getIndicadorAceitacao());

					// Colocado a pedido de Rosana em 08/04/2006
					if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
							&& !arrecadadorMovimentoItemHelper
									.getCodigoRegistro().equals(
											RegistroCodigo.CODIGO_C)) {

						arrecadadorMovimentoItemHelper
								.setDescricaoIndicadorAceitacao(ArrecadadorMovimentoItem.DESCRICAO_INDICADOR_ACEITO);
					}

				} else {

					arrecadadorMovimentoItemHelper
							.setIndicadorAceitacao(arrecadadorMovimentoItem
									.getIndicadorAceitacao());

					// Colocado a pedido de Rosana em 08/04/2006
					if (arrecadadorMovimentoItemHelper.getCodigoRegistro() != null
							&& !arrecadadorMovimentoItemHelper
									.getCodigoRegistro().equals(
											RegistroCodigo.CODIGO_C)) {

						arrecadadorMovimentoItemHelper
								.setDescricaoIndicadorAceitacao(ArrecadadorMovimentoItem.DESCRICAO_INDICADOR_NAO_ACEITO);
					}

				}

				BigDecimal valorPagamento = null;
				try {
					valorPagamento = repositorioArrecadacao
							.recuperaValorPagamentoArrecadadorMovimentoItem(arrecadadorMovimentoItem
									.getId());
				} catch (ErroRepositorioException e) {
					e.printStackTrace();
				}

				arrecadadorMovimentoItemHelper.setVlPagamento(Util
						.formatarMoedaReal(valorPagamento));

				retorno.add(arrecadadorMovimentoItemHelper);
			}
		}

		if (retorno == null || retorno.isEmpty()) {
			throw new ControladorException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}

	/**
	 * Obtém a representação númerica do código de barras de um pagamento de
	 * acordo com os parâmetros informados
	 * 
	 * [UC0229] Obter Representação Numérica do Código de Barras
	 * 
	 * Formata a identificação do pagamento de acordo com o tipo de pagamento
	 * informado
	 * 
	 * [SB0001] Obter Identificação do Pagamento
	 * 
	 * @author Pedro Alexandre
	 * @date 20/04/2006
	 * 
	 * @param tipoPagamento
	 * @param idLocalidade
	 * @param matriculaImovel
	 * @param anoMesReferenciaConta
	 * @param digitoVerificadorRefContaModulo10
	 * @param idTipoDebito
	 * @param anoEmissaoGuiaPagamento
	 * @param sequencialDocumentoCobranca
	 * @param idTipoDocumento
	 * @param idCliente
	 * @param seqFaturaClienteResponsavel
	 * @param idDebitoCreditoSituacaoAtual
	 * @return
	 */
	public String obterIdentificacaoPagamento(Integer tipoPagamento,
			Integer idLocalidade, Integer matriculaImovel,
			String mesAnoReferenciaConta,
			Integer digitoVerificadorRefContaModulo10, Integer idTipoDebito,
			String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca,
			Integer idTipoDocumento, Integer idCliente,
			Integer seqFaturaClienteResponsavel,String idGuiaPagamento) throws ControladorException {

		// Cria a variável que vai armazenar o identificador do pagamento
		// formatado
		String identificacaoPagamento = "";
		String matriculaImovelString = null;
		String matriculaImovelSemDigito = null;

		if (matriculaImovel != null) {
			matriculaImovelString = matriculaImovel.toString();
			matriculaImovelSemDigito = matriculaImovelString.substring(0,
					matriculaImovelString.length() - 1);
		}

		if (tipoPagamento.intValue() == 3) {

			// Mover "01" para G05.7.1
			identificacaoPagamento = "01";

			// Matricula do imovel sem o digito
			identificacaoPagamento = identificacaoPagamento
					+ Util.adicionarZerosEsquedaNumero(6, ""
							+ matriculaImovelSemDigito);

			// MesAnoReferencia
			identificacaoPagamento = identificacaoPagamento
					+ Util.formatarMesAnoParaAnoMes(mesAnoReferenciaConta);

			/*
			 * Integer idDebitoCreditoSituacaoAtual =
			 * this.getControladorFaturamento()
			 * .pesquisarDebitoCreditoSituacaoAtualConta(matriculaImovel, new
			 * Integer(Util.formatarMesAnoParaAnoMes(mesAnoReferenciaConta)));
			 * 
			 * //Codigo Origem da Conta if
			 * (idDebitoCreditoSituacaoAtual.equals(DebitoCreditoSituacao.NORMAL)){
			 * identificacaoPagamento = identificacaoPagamento + "19"; } else if
			 * (idDebitoCreditoSituacaoAtual.equals(DebitoCreditoSituacao.RETIFICADA)){
			 * identificacaoPagamento = identificacaoPagamento + "27"; } else{
			 * identificacaoPagamento = identificacaoPagamento + "35"; }
			 */

			// Codigo Origem da Conta
			identificacaoPagamento = identificacaoPagamento + "00";

			// Numero do Documento
			// identificacaoPagamento = identificacaoPagamento + "000000000";
			identificacaoPagamento = identificacaoPagamento + "00000000";

		} else if (tipoPagamento.intValue() == 4) {

			// Mover "09" para G05.7.1
			identificacaoPagamento = "09";

			// Localidade
			identificacaoPagamento = identificacaoPagamento
					+ Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);

			// Matricula do imovel sem o digito
			identificacaoPagamento = identificacaoPagamento
					+ Util.adicionarZerosEsquedaNumero(8, ""
							+ matriculaImovelSemDigito);

			// Nao utilizado preencher com zeros (3)
			identificacaoPagamento = identificacaoPagamento + "00";

			// Tipo do debito
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(4, idTipoDebito
							.toString()));

			// Ano da emissao da guia de pagamento
			identificacaoPagamento = identificacaoPagamento
					+ anoEmissaoGuiaPagamento;

			// Nao utilizado preencher com zeros (2)
			// identificacaoPagamento = identificacaoPagamento + "00";
			identificacaoPagamento = identificacaoPagamento + "0";

		} else if (tipoPagamento.intValue() == 5) {

			/*
			 * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Rosana
			 * Carvalho) OBJ: Mover 10 e não 04 para o campo G05.7.1
			 */

			// Mover "10" para G05.7.1
			identificacaoPagamento = "10";

			// Matricula do imovel sem o digito
			identificacaoPagamento = identificacaoPagamento
					+ Util.adicionarZerosEsquedaNumero(6, ""
							+ matriculaImovelSemDigito);

			// Sequencial do documento de cobrança
			/*
			 * identificacaoPagamento = identificacaoPagamento +
			 * (Util.adicionarZerosEsquedaNumero(17,
			 * sequencialDocumentoCobranca.toString()));
			 */

			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(16,
							sequencialDocumentoCobranca.toString()));

		} else if (tipoPagamento.intValue() == 6) {

			// Tipo de Pagamento
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(2, tipoPagamento
							.toString()));

			// Localidade
			identificacaoPagamento = identificacaoPagamento
					+ Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);

			// Cliente
			identificacaoPagamento = identificacaoPagamento
					+ (Util
							.adicionarZerosEsquedaNumero(8, idCliente
									.toString()));

			// Nao utilizado preencher com zeros (3)
			identificacaoPagamento = identificacaoPagamento + "00";

			// Tipo do debito
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(4, idTipoDebito
							.toString()));

			// Ano da emissao da guia de pagamento
			identificacaoPagamento = identificacaoPagamento
					+ anoEmissaoGuiaPagamento;

			// Nao utilizado preencher com zeros (2)
			// identificacaoPagamento = identificacaoPagamento + "00";
			identificacaoPagamento = identificacaoPagamento + "0";

		} else if (tipoPagamento.intValue() == 7) {

			// Tipo de Pagamento
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(2, tipoPagamento
							.toString()));

			// Cliente
			identificacaoPagamento = identificacaoPagamento
					+ (Util
							.adicionarZerosEsquedaNumero(9, idCliente
									.toString()));

			// Nao utilizado preencher com zeros (1)
			// identificacaoPagamento = identificacaoPagamento + "0";

			// MesAnoReferencia
			identificacaoPagamento = identificacaoPagamento
					+ mesAnoReferenciaConta;

			// Digito verificador da conta no modulo 10
			identificacaoPagamento = identificacaoPagamento
					+ digitoVerificadorRefContaModulo10;

			// Sequencial da fatura do cliente responsavel
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(6,
							seqFaturaClienteResponsavel.toString()));

		} else if (tipoPagamento.intValue() == 8) {

			// Tipo de Pagamento
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(2, tipoPagamento
							.toString()));

			// Nao utilizado preencher com zeros (3)
			identificacaoPagamento = identificacaoPagamento + "000";

			// Cliente
			identificacaoPagamento = identificacaoPagamento
					+ (Util
							.adicionarZerosEsquedaNumero(8, idCliente
									.toString()));

			// Sequencial do documento de cobrança
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(9,
							sequencialDocumentoCobranca.toString()));

			// Tipo de documento
			identificacaoPagamento = identificacaoPagamento
					+ (Util.adicionarZerosEsquedaNumero(2, idTipoDocumento
							.toString()));

			// Nao utilizado preencher com zeros (3)
			// identificacaoPagamento = identificacaoPagamento + "0";
		} else if (tipoPagamento.intValue() == 1 || tipoPagamento.intValue() == 9){
			
			identificacaoPagamento = Util.adicionarZerosEsquedaNumeroTruncando(3,idLocalidade.toString());
			
			if(tipoPagamento.intValue() == 1){
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,matriculaImovel.toString());
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idGuiaPagamento);
				
				//FIXO
				identificacaoPagamento = identificacaoPagamento + "00";
				
				//Identifica o tamanho da matrícula do imóvel
				identificacaoPagamento = identificacaoPagamento + "1";
			}
			else if(tipoPagamento.intValue() == 9){
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idCliente.toString());
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idGuiaPagamento);
				
				//FIXO
				identificacaoPagamento = identificacaoPagamento + "00";
				
				//Identifica o tamanho da matrícula do imóvel
				identificacaoPagamento = identificacaoPagamento + "1";
			}
				
		}

		// Retorna o identificador do pagamento formatado
		return identificacaoPagamento;
	}

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * Cria uma linha de 150 posições com o registro tipo E.
	 * 
	 * 
	 * [SB0001] - Gerar Movimento para Debito Automático
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2006
	 * 
	 * @param banco,debitoAutomaticoMovimento
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	protected StringBuilder criarRegistroTipoE(Banco banco,
			DebitoAutomaticoMovimento debitoAutomaticoMovimento)
			throws ControladorException {

		StringBuilder registroTipoE = new StringBuilder();
		Conta conta = debitoAutomaticoMovimento.getContaGeral().getConta();

		registroTipoE.append("E");
		// identificação do cliente na empresa

		// String matriculaImovel = conta.getImovel().getId().toString();
		// String matriculaImovelSemDigito = matriculaImovel.substring(0,
		// matriculaImovel.length() - 1);
		
		FiltroImovel filtroImovel = new FiltroImovel();
		
		filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID,conta.getImovel().getId()));
		
		Collection colecaoImoveis = 
			this.getControladorUtil()
				.pesquisar(filtroImovel, Imovel.class.getName());
		
		Imovel imovel = (Imovel) colecaoImoveis.iterator().next();

		String identificacaoCliente = Util.adicionarZerosEsquedaNumero(7,
				imovel.getCodigoDebitoAutomatico().toString());

		registroTipoE.append(Util.completaString(identificacaoCliente, 25));

		String codigoAgencia = debitoAutomaticoMovimento.getDebitoAutomatico()
				.getAgencia().getCodigoAgencia();
		// agencia para débito
		registroTipoE
				.append(Util.adicionarZerosEsquedaNumero(4, codigoAgencia));

		// Identificação do cliente no Banco
		registroTipoE.append(Util.completaString(debitoAutomaticoMovimento
				.getDebitoAutomatico().getIdentificacaoClienteBanco(), 14));
		// data de vencimento(AAAAMMDD)
		String dataVencimento = Util.recuperaAnoMesDiaDaData(conta
				.getDataVencimentoConta());
		registroTipoE.append(dataVencimento);
		// Valor do débito
		BigDecimal valorDebito = new BigDecimal("0.00");
		valorDebito = valorDebito.add(conta.getValorAgua());
		valorDebito = valorDebito.add(conta.getValorEsgoto());
		valorDebito = valorDebito.add(conta.getDebitos());
		valorDebito = valorDebito.subtract(conta.getValorCreditos());

		/*
		 * Colocado por Raphael Rossiter em 24/10/2007 (Analista: Rosana
		 * Carvalho) OBJETIVO: Retirar o valor dos impostos do valor total da
		 * conta
		 */
		if (conta.getValorImposto() != null) {
			valorDebito = valorDebito.subtract(conta.getValorImposto());
		}

		String valorDebitoString = ("" + valorDebito).replace(".", "");
		registroTipoE.append(Util.adicionarZerosEsquedaNumero(15,
				valorDebitoString));
		// Código da moeda
		registroTipoE.append("03");
		// inicio preenchido conforme segue abaixo(E.07)
		// Ano/Mês de referência da conta no formato AAAAMM
		registroTipoE.append(conta.getReferencia());
		// Digito verificador da conta
		registroTipoE.append(conta.getDigitoVerificadorConta());
		// Id da conta
		registroTipoE.append(Util.completaString("" + conta.getId(), 9));
		// id do grupo de faturamento
		registroTipoE.append(Util.completaString(""
				+ debitoAutomaticoMovimento.getFaturamentoGrupo().getId(), 2));
		// reservado para o futuro
		registroTipoE.append(Util.completaString("", 42));
		// fim preenchido conforme segue abaixo(E.07)

		// reservado para o futuro
		registroTipoE.append(Util.completaString("", 20));
		// Tipo do Movimento
		registroTipoE.append("0");

		return registroTipoE;
	}

	/**
	 * [UC0262] - Distribuir dados do Registro de Movimento do Arrecadador
	 * 
	 * Autor: Sávio Luiz, Raphael Rossiter Data: 30/01/2006, 14/08/2007
	 * 
	 * Caso a descrição de Ocorrencia venha nula então recupera o código
	 * registro da linha senão então seta o valor de código registro para 'C'
	 */
	public Object distribuirdadosRegistroMovimentoArrecadador(String linha,
			String descricaoOcorrencia) throws ControladorException {
		Object registroHelperCodigo = null;

		// inicializa a variavel como 0
		char codigoRegistro = '0';
		// se a descrição da ocorrencia for diferente de null
		// então é para setar o objeto registroHelperCodigoC
		if (descricaoOcorrencia != null) {
			codigoRegistro = 'C';
		} else {
			codigoRegistro = linha.substring(0, 1).toUpperCase().charAt(0);
		}

		String matriculaImovel = null;

		switch (codigoRegistro) {

		case 'A':
			RegistroHelperCodigoA registroHelperCodigoA = new RegistroHelperCodigoA();
			// recupera o codigo do registro
			registroHelperCodigoA.setCodigoRegistro("" + codigoRegistro);
			// recupera o codigo da remessa
			registroHelperCodigoA
					.setCodigoRemessa(linha.substring(1, 2).trim());
			// recupera o código do convênio
			registroHelperCodigoA.setCodigoConvenio(linha.substring(2, 22)
					.trim());
			// recupera o nome da empresa
			registroHelperCodigoA
					.setNomeEmpresa(linha.substring(22, 42).trim());
			// recupera o codigo do banco
			registroHelperCodigoA
					.setCodigoBanco(linha.substring(42, 45).trim());
			// recupera o nome do banco
			registroHelperCodigoA.setNomeBanco(linha.substring(45, 65).trim());
			// recupera a data de geração do arquivo
			registroHelperCodigoA.setDataGeracaoArquivo(linha.substring(65, 73)
					.trim());
			// recupera o numero sequencial do arquivo(NSA)
			registroHelperCodigoA.setNumeroSequencialArquivo(linha.substring(
					73, 79).trim());
			// recupera a versão do layout
			registroHelperCodigoA.setVersaoLayout(linha.substring(79, 81)
					.trim());
			// recupera o tipo de movimento
			registroHelperCodigoA.setTipoMovimento(linha.substring(81, 98)
					.trim());
			if (linha.substring(149, 150).trim().equals("*")) {
				// recupera o reservado para o futuro
				registroHelperCodigoA.setReservadoFuturo(linha.substring(98,
						149).trim());
			} else {
				// recupera o reservado para o futuro
				registroHelperCodigoA.setReservadoFuturo(linha.substring(98,
						150).trim());
			}

			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoA;
			break;

		case 'B':
			RegistroHelperCodigoB registroHelperCodigoB = new RegistroHelperCodigoB();
			// recupera o codigo do registro
			registroHelperCodigoB.setCodigoRegistro("" + codigoRegistro);

			// recupera a identificação do cliente na empresa
			matriculaImovel = linha.substring(1, 26).trim();
			
			//Comentado por Raphael Rossiter em 01/11/2007 - Analista: Rosana Carvalho
			//Calcular Digito Verificador da Matricula
			/*if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);

				matriculaImovel = matriculaImovel + digitoModulo11;
			}*/
			
			if (matriculaImovel.length() < 8){
				matriculaImovel = Util.adicionarZerosEsquedaNumero(8, matriculaImovel);
			}

			registroHelperCodigoB.setIdClienteEmpresa(matriculaImovel);

			// recupera a agencia para debito
			registroHelperCodigoB.setAgenciaDebito(linha.substring(26, 30)
					.trim());
			// recupera o identificação do cliente no banco
			registroHelperCodigoB.setIdClienteBanco(linha.substring(30, 44)
					.trim());
			// recupera a data de Opção/Exclusão
			registroHelperCodigoB.setDataOpcaoExclusao(linha.substring(44, 52)
					.trim());
			// recupera o reservado para o futuro
			registroHelperCodigoB.setReservadoFuturo(linha.substring(52, 149)
					.trim());
			// recupera a codigo movimento
			if (!linha.substring(149, 150).trim().equals("*")) {
				registroHelperCodigoB.setCodigoMovimento(linha.substring(149,
						150).trim());
			}
			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoB;
			break;

		case 'C':
			RegistroHelperCodigoC registroHelperCodigoC = new RegistroHelperCodigoC();
			// recupera o codigo do registro
			registroHelperCodigoC.setCodigoRegistro("" + codigoRegistro);
			// recupera a identificação do cliente na empresa
			registroHelperCodigoC.setIdClienteEmpresa(linha.substring(1, 26)
					.trim());
			// recupera a agencia para debito
			registroHelperCodigoC.setAgenciaDebito(linha.substring(26, 30)
					.trim());
			// recupera o identificação do cliente no banco
			registroHelperCodigoC.setIdClienteBanco(linha.substring(30, 44)
					.trim());
			// recupera a descrição da ocorrencia do movimento
			registroHelperCodigoC
					.setDescricaoOcorrenciaMovimento(descricaoOcorrencia);
			// recupera os brancos
			registroHelperCodigoC.setBrancos(linha.substring(84, 124).trim());
			// recupera o reservado para o futuro
			registroHelperCodigoC.setReservadoFuturo(linha.substring(124, 149)
					.trim());
			if (!linha.substring(149, 150).trim().equals("*")) {
				// recupera a codigo movimento
				registroHelperCodigoC.setCodigoMovimento(linha.substring(149,
						150).trim());
			}
			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoC;
			break;

		case 'E':
			RegistroHelperCodigoE registroHelperCodigoE = new RegistroHelperCodigoE();
			// recupera o codigo do registro
			registroHelperCodigoE.setCodigoRegistro("" + codigoRegistro);
			// recupera a identificação do cliente na empresa
			registroHelperCodigoE.setIdClienteEmpresa(linha.substring(1, 26)
					.trim());
			// recupera a agencia para debito
			registroHelperCodigoE.setAgenciaDebito(linha.substring(26, 30)
					.trim());
			// recupera o identificação do cliente no banco
			registroHelperCodigoE.setIdClienteBanco(linha.substring(30, 44)
					.trim());
			// recupera a data do debito
			registroHelperCodigoE.setDataDebito(linha.substring(44, 52).trim());
			// recupera o valor debitado
			registroHelperCodigoE
					.setValorDebito(linha.substring(52, 67).trim());
			// recupera o codigo da moeda
			registroHelperCodigoE
					.setCodigoMoeda(linha.substring(67, 69).trim());
			// recupera o código de movimento
			registroHelperCodigoE.setAnoMesReferenciaConta(linha.substring(69,
					75).trim());
			// recupera o digito verificado no modulo dez(10) do ano e mes
			// da
			// contadebitada
			registroHelperCodigoE.setDigitoVerificadoAnoMesConta(linha
					.substring(75, 76).trim());
			// grupo de faturamento
			registroHelperCodigoE.setGruposFaturamento(linha.substring(76, 129)
					.trim());
			// recupera o reservado para o futuro
			registroHelperCodigoE.setReservadoFuturo(linha.substring(129, 149)
					.trim());
			if (!linha.substring(149, 150).trim().equals("*")) {
				// recupera o codigo do movimento enviado no registro de
				// código
				// E
				registroHelperCodigoE.setCodigoMovimento(linha.substring(149,
						150).trim());
			}
			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoE;
			break;

		case 'F':
			RegistroHelperCodigoF registroHelperCodigoF = new RegistroHelperCodigoF();
			// recupera o codigo do registro
			registroHelperCodigoF.setCodigoRegistro("" + codigoRegistro);

			// recupera a identificação do cliente na empresa
			matriculaImovel = linha.substring(1, 26).trim();

			// Calcular Digito Verificador da Matricula
			/*
			 * if (matriculaImovel != null) {
			 * 
			 * int digitoModulo11 =
			 * Util.obterDigitoVerificadorModulo11(matriculaImovel);
			 * 
			 * matriculaImovel = matriculaImovel + digitoModulo11; }
			 */

			if (matriculaImovel.length() < 8) {

				matriculaImovel = Util.adicionarZerosEsquedaNumero(8,
						matriculaImovel);
			}

			registroHelperCodigoF.setIdClienteEmpresa(matriculaImovel);

			// recupera a agencia para debito
			registroHelperCodigoF.setAgenciaDebito(linha.substring(26, 30)
					.trim());
			// recupera o identificação do cliente no banco
			registroHelperCodigoF.setIdClienteBanco(linha.substring(30, 44)
					.trim());
			// recupera a data do debito
			registroHelperCodigoF.setDataDebito(linha.substring(44, 52).trim());
			// recupera o valor debitado
			registroHelperCodigoF
					.setValorDebito(linha.substring(52, 67).trim());
			// recupera o codigo do retorno
			registroHelperCodigoF.setCodigoRetorno(linha.substring(67, 69)
					.trim());
			// recupera o ano mes de referencia da conta
			registroHelperCodigoF.setAnoMesReferenciaConta(linha.substring(69,
					75).trim());
			// recupera o digito verificado no modulo dez(10) do ano e mes
			// da
			// contadebitada
			registroHelperCodigoF.setDigitoVerificadoAnoMesConta(linha
					.substring(75, 76).trim());
			// grupo de faturamento
			registroHelperCodigoF.setGruposFaturamento(linha.substring(76, 129)
					.trim());
			// recupera o reservado para o futuro
			registroHelperCodigoF.setReservadoFuturo(linha.substring(129, 149)
					.trim());
			if (!linha.substring(149, 150).trim().equals("*")) {
				// recupera o codigo do movimento enviado no registro de
				// código
				// F
				registroHelperCodigoF.setCodigoMovimento(linha.substring(149,
						150).trim());
			}
			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoF;
			break;

		case 'G':
			RegistroHelperCodigoG registroHelperCodigoG = new RegistroHelperCodigoG();
			// recupera o codigo do registro
			registroHelperCodigoG.setCodigoRegistro("" + codigoRegistro);
			// recupera a identificação da agencia /conta/digito creditada
			registroHelperCodigoG.setIdAgenciaContaDigito(linha
					.substring(1, 21).trim());
			// recupera a data de pagamento(AAAAMMDD)
			registroHelperCodigoG.setDataPagamento(linha.substring(21, 29)
					.trim());
			// recupera a data prevista para o credito(AAAAMMDD)
			registroHelperCodigoG.setDataPrevistaCredito(linha
					.substring(29, 37).trim());
			// recupera O CÓDIGO DE BARRAS
			//String codigoBarras = linha.substring(37, 81).trim();
			String codigoBarras = linha.substring(37, 93).trim();
			
			RegistroHelperCodigoBarras registroHelperCodigoBarras = distribuirDadosCodigoBarras(codigoBarras);
			// seta também o código de barra como string
			registroHelperCodigoG.setCodigoBarras(codigoBarras);
			registroHelperCodigoG
					.setRegistroHelperCodigoBarras(registroHelperCodigoBarras);
			// recupera o valor recebido
			registroHelperCodigoG.setValorRecebido(linha.substring(81, 93)
					.trim());
			// recupera o valor da tarifa
			registroHelperCodigoG.setValorTarifa(linha.substring(93, 100)
					.trim());
			// recupera o numero sequencial do registro(NRS)
			registroHelperCodigoG.setNumeroSeqRegistro(linha
					.substring(100, 108).trim());
			// recupera o codigo da agencia arrecadadora
			registroHelperCodigoG.setCodigoAgenciaArrecadadora(linha.substring(
					108, 116).trim());
			// recupera a foma de arrecadação/captura
			String codigoArrecadacaoForma = linha.substring(116, 117).trim();
			registroHelperCodigoG
					.setCodigoFormaArrecadacao(codigoArrecadacaoForma);
			String descricaoArrecadacaoForma = "";
			try {
				descricaoArrecadacaoForma = this.repositorioArrecadacao
						.recuperaDescricaoArrecadacaoForma(codigoArrecadacaoForma);
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}

			registroHelperCodigoG
					.setFormaArrecadacao(descricaoArrecadacaoForma);

			// recupera o numero de autenticação caixa ou código de
			// transação
			registroHelperCodigoG.setNumeroAutenticacao(linha.substring(117,
					140).trim());
			if (!linha.substring(140, 141).trim().equals("")) {
				// recupera a forma de pagamento
				registroHelperCodigoG.setFormaPagamento(linha.substring(140,
						141).trim());
			} else {
				// recupera a forma de pagamento
				registroHelperCodigoG.setFormaPagamento("1");
			}
			if (linha.substring(149, 150).trim().equals("*")) {
				// recupera o reservado para o futuro
				registroHelperCodigoG.setReservadoFuturo(linha.substring(141,
						149).trim());
			} else {
				// recupera o reservado para o futuro
				registroHelperCodigoG.setReservadoFuturo(linha.substring(141,
						150).trim());
			}

			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoG;
			break;

		case 'X':
			RegistroHelperCodigoX registroHelperCodigoX = new RegistroHelperCodigoX();
			// recupera o codigo do registro
			registroHelperCodigoX.setCodigoRegistro("" + codigoRegistro);
			// recupera o código agencia
			registroHelperCodigoX.setCodigoAgencia(linha.substring(1, 5));
			// recupera o nome da agencia
			registroHelperCodigoX.setNomeAgencia(linha.substring(5, 35));
			// recupera o nome do logradouro
			registroHelperCodigoX.setNomelogradouro(linha.substring(35, 65));
			// recupera o numero
			registroHelperCodigoX.setNumero(linha.substring(65, 70));
			// recupera o código do cep
			registroHelperCodigoX.setCodigoCep(linha.substring(70, 75));
			// recupera o sufixo do cep
			registroHelperCodigoX.setSufixoCep(linha.substring(75, 78));
			// recupera o nome da cidade
			registroHelperCodigoX.setNomeCidade(linha.substring(78, 98));
			// recupera a sigla da unidade federação
			registroHelperCodigoX.setSiglaUnidadeFederacao(linha.substring(98,
					100));
			// recupera a situação da agencia
			registroHelperCodigoX.setSituacaoAgencia(linha.substring(100, 101));
			if (linha.substring(149, 150).trim().equals("*")) {
				// recupera o reservado para o futuro
				registroHelperCodigoX.setReservadoFuturo(linha.substring(101,
						149));
			} else {
				// recupera o reservado para o futuro
				registroHelperCodigoX.setReservadoFuturo(linha.substring(101,
						150));
			}
			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoX;
			break;

		case 'Z':
			RegistroHelperCodigoZ registroHelperCodigoZ = new RegistroHelperCodigoZ();
			// recupera o codigo do registro
			registroHelperCodigoZ.setCodigoRegistro("" + codigoRegistro);
			// recupera o total de registros do arquivo
			registroHelperCodigoZ.setTotalRegistrosArquivo(linha
					.substring(1, 7));
			// recupera o valor total recebido dos registrosdo arquivo
			registroHelperCodigoZ.setValorTotalRegistrosArquivo(linha
					.substring(7, 24));
			if (linha.substring(149, 150).trim().equals("*")) {
				// recupera o reservado para o futuro
				registroHelperCodigoZ.setReservadoFuturo(linha.substring(24,
						149));
			} else {
				// recupera o reservado para o futuro
				registroHelperCodigoZ.setReservadoFuturo(linha.substring(24,
						150));
			}
			// faz um cast para o object
			registroHelperCodigo = (Object) registroHelperCodigoZ;
			break;

		}
		return registroHelperCodigo;

	}

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * GUIA DE PAGAMENTO
	 * 
	 * Autor: Raphael Rossiter Data: 31/08/2007
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamento(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean idLocalidadeInvalida = false;
		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		idLocalidadeInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (idLocalidadeInvalida) {
			descricaoOcorrencia = "CÓDIGO DA LOCALIDADE NÃO NUMÉRICA";
		}

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {

			// [FS0008] - Calcular Digito Verificador da Matricula CAER
			matriculaImovel = registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3();

			if (matriculaImovel != null) {
				int digitoModulo11 = Util
						.obterDigitoVerificadorModulo11(matriculaImovel);

				matriculaImovelValidada = new Integer(matriculaImovel
						+ digitoModulo11);
			}

			// Verifica se existe a matricula do imóvel na base
			try {
				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(matriculaImovelValidada);
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		// Valida o tipo do débito
		boolean codigoTipoDebito = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento5());

		if (codigoTipoDebito) {
			descricaoOcorrencia = "TIPO DO DÉBITO NÃO NUMÉRICO";
		} else {

			Integer idDebitoTipoNaBase = getControladorFaturamento()
					.verificarExistenciaDebitoTipo(
							Util
									.converterStringParaInteger(registroHelperCodigoBarras
											.getRegistroHelperCodigoBarrasTipoPagamento()
											.getIdPagamento5()));

			if (idDebitoTipoNaBase == null) {
				descricaoOcorrencia = "TIPO DO DÉBITO INEXISTENTE";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer idGuiaPagamento = null;

			Integer idDebitoTipo = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {
				/*
	             * Alterado por Ana Maria em 11/08/2008 - Analistas: Denys e Aryed
	             * Colocar o valor do pagamento na pesquisa de guia. Caso retorne mais
	             * de uma guia de pagamento, selecionar a que tiver o menor vencimento.
	             */
				
				idGuiaPagamento = repositorioArrecadacao
						.pesquisarExistenciaGuiaPagamento(imovel, idDebitoTipo, valorPagamento);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idGuiaPagamento == null || idGuiaPagamento.equals("")) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}
			
			/**
			 * Alterado por Arthur Carvalho em 29/12/2009 - Analista Rafael Pinto
			 * Gera Guia de Pagamento. Caso a Guia de Pagamento seja NUlA e o Debito tipo = DOACAO.
			 */
			if ( idGuiaPagamento == null ) {
				
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo.adicionarParametro( 
					new ParametroSimples( FiltroDebitoTipo.ID, idDebitoTipo) );

				Collection colecaoDebitoTipo = 
					getControladorUtil().pesquisar(filtroDebitoTipo, 
						DebitoTipo.class.getName());
				
				if ( colecaoDebitoTipo != null && !colecaoDebitoTipo.equals("")) {
					
					DebitoTipo debitoTipo = 
						(DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
					
					if (debitoTipo.getCodigoConstante() != null && DebitoTipo.DOACAO == debitoTipo.getCodigoConstante().intValue() ) {
						
						//Gera Guia de Pagamento
						idGuiaPagamento = this.inserirGuiaPagamentoReferenteDebitoTipoDoacao(
								idImovelNaBase, 
								debitoTipo, 
								dataPagamento, 
								valorPagamento);
						
					}
				}
			}
			
			/*
             * Alterado por Raphael Rossiter em 11/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da guia de pagamento e NÃO com
             * a localidade do imóvel.
             */
            if (idGuiaPagamento != null) {
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorGuiaPagamento(idGuiaPagamento);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
                
            }
            else{
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
            }
            

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecadação da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(idDebitoTipo);
			pagamento.setDebitoTipo(debitoTipo);

			pagamento.setContaGeral(null);

			// Verifica se o id da conta é diferente de nulo
			if (idGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(idGuiaPagamento);
				pagamento.setGuiaPagamento(guiaPagamento);

			} else {
				pagamento.setGuiaPagamento(null);
			}

			// verifica se o id da conta é diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);

			} else {
				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
				pagamento.setImovel(imovel);
			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());
			
			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			colecaoPagamnetos.add(pagamento);

		} else {

			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * GUIA DE PAGAMENTO CLIENTE(Tipo 6)
	 * 
	 * Autor: Ana Maria Data: 06/08/2007
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamentoCliente(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean idLocalidadeInvalida = false;
		boolean idClienteInvalido = false;

		Integer idClienteNaBase = null;

		idLocalidadeInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (idLocalidadeInvalida) {
			descricaoOcorrencia = "CÓDIGO DA LOCALIDADE NÃO NUMÉRICA";
		}

		idClienteInvalido = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		Integer idCliente = null;
		if (idClienteInvalido) {
			descricaoOcorrencia = "CÓDIGO DO CLIENTE NÃO NUMÉRICO";
		} else {
			// verifica se existe o id do cliente na
			// base
			idCliente = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3());

			try {
				idClienteNaBase = repositorioCliente
						.verificarExistenciaCliente(new Integer(idCliente));
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idClienteNaBase == null) {
				descricaoOcorrencia = "CLIENTE RESPONSÁVEL NÂO CADASTRADO";
			}
		}

		// Valida o namo mes de referencia da conta
		boolean codigoTipoDebito = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento5());

		if (codigoTipoDebito) {
			descricaoOcorrencia = "TIPO DO DÉBITO NÃO NUMÉRICO";
		} else {

			Integer idDebitoTipoNaBase = getControladorFaturamento()
					.verificarExistenciaDebitoTipo(
							Util
									.converterStringParaInteger(registroHelperCodigoBarras
											.getRegistroHelperCodigoBarrasTipoPagamento()
											.getIdPagamento5()));

			if (idDebitoTipoNaBase == null) {
				descricaoOcorrencia = "TIPO DO DÉBITO INEXISTENTE";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2());

			Integer idGuiaPagamento = null;

			Integer idDebitoTipo = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());

			// Imovel imovel = new Imovel();
			// imovel.setId(idImovelNaBase);

			try {
				// idLocalidade =
				// repositorioLocalidade.pesquisarIdLocalidade(idImovelNaBase);

				idGuiaPagamento = repositorioArrecadacao
						.pesquisarExistenciaGuiaPagamentoCliente(idCliente,
								idDebitoTipo);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idGuiaPagamento == null || idGuiaPagamento.equals("")) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecadação da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(idDebitoTipo);
			pagamento.setDebitoTipo(debitoTipo);

			pagamento.setContaGeral(null);

			// Verifica se o id da conta é diferente de nulo
			if (idGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(idGuiaPagamento);
				pagamento.setGuiaPagamento(guiaPagamento);

			} else {
				pagamento.setGuiaPagamento(null);
			}

			// verifica se o id da conta é diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);

			} else {
				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			if (idCliente != null) {
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				pagamento.setCliente(cliente);
			} else {
				pagamento.setCliente(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setImovel(null);
			pagamento.setUltimaAlteracao(new Date());

			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);			

			colecaoPagamnetos.add(pagamento);

		} else {

			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * DOCUMENTO COBRANÇA TIPO 08
	 * 
	 * Autor: Raphael Rossiter Data: 02/05/2007
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasDocumentoCobrancaTipo8(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		// valida o cliente
		boolean idClienteInvalido = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		Integer idClienteNaBase = null;

		if (idClienteInvalido) {
			descricaoOcorrencia = "CÓDIGO DO CLIENTE NÃO NUMÉRICO";
		} else {
			// verifica se existe o id do cliente na
			// base
			Integer idCliente = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3());

			try {
				idClienteNaBase = repositorioCliente
						.verificarExistenciaCliente(new Integer(idCliente));
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idClienteNaBase == null) {
				descricaoOcorrencia = "CLIENTE RESPONSÁVEL NÂO CADASTRADO";
			}
		}

		// valida o namo mes de referencia da conta
		boolean tipoDocumentoInvalido = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento5());

		if (tipoDocumentoInvalido) {
			descricaoOcorrencia = "TIPO DO DOCUMENTO NÃO NUMÉRICO";
		}

		if (descricaoOcorrencia.equals("OK")) {
			// inicializa o id da localidade
			// Integer idLocalidade = null;

			// inicializa a coleção de cobranca documento item
			Collection cobrancaDocumentoItens = null;
			// inicializa a coleção de cobranca documento item
			// Object[] parmsDocumentoCobranca = null;

			int numeroSequencialDocumento = Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento4());

			try {

				cobrancaDocumentoItens = repositorioCobranca
						.pesquisarCobrancaDocumentoItemCliente(idClienteNaBase,
								numeroSequencialDocumento);
				/*
				 * parmsDocumentoCobranca = repositorioCobranca
				 * .pesquisarParmsCobrancaDocumentoCliente( idClienteNaBase,
				 * numeroSequencialDocumento);
				 */
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// caso exista documento de cobrança
			// verifica se a coleção é diferente de nula
			if (cobrancaDocumentoItens != null
					&& !cobrancaDocumentoItens.isEmpty()) {
				Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens
						.iterator();
				while (cobrancaDocumentoItensIterator.hasNext()) {
					Object[] cobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator
							.next();
					// inicializa as variaveis que veio da
					// pesquisa
					Integer idContaPesquisa = null;
					Integer idContaGeralPesquisa = null;
					Integer idGuiaPagamento = null;
					Integer idDebitoACobrar = null;
					BigDecimal valorItemCobrado = null;
					int contaReferencia = 0;
					Integer idDebitoTipo = null;
					Integer idGuiaPagamentoGeralPesquisa = null;
					Integer idDebitoACobrarGeralPesquisa = null;
					Integer idCobrancaDocumento = null;
					Integer idDocumentoTipo = null;
					// verifica o id da conta
					if (cobrancaDocumentoItem[0] != null) {
						idContaPesquisa = (Integer) cobrancaDocumentoItem[0];
						idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[0];
						// referencia conta
						if (cobrancaDocumentoItem[4] != null) {
							contaReferencia = (Integer) cobrancaDocumentoItem[4];
						}
					} else {
						// caso não exista na conta então pesquisa
						// na conta histórico
						if (cobrancaDocumentoItem[10] != null) {
							idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[10];
						}

						// referencia conta histórico
						if (cobrancaDocumentoItem[5] != null) {
							contaReferencia = (Integer) cobrancaDocumentoItem[5];
						}
					}

					// verifica o id da guia pagamento
					if (cobrancaDocumentoItem[1] != null) {
						idGuiaPagamento = (Integer) cobrancaDocumentoItem[1];
						idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[1];
					} else {
						// caso não exista no guia pagamento então
						// pesquisa no guia pagamento histórico
						if (cobrancaDocumentoItem[11] != null) {
							idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[11];
						}
					}
					// verifica o id do debito a cobrar
					if (cobrancaDocumentoItem[2] != null) {
						idDebitoACobrar = (Integer) cobrancaDocumentoItem[2];
						idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[2];

						// [SB0012]- Verifica Pagamento de Débito a Cobrar de
						// Parcelamento
						verificaPagamentoDebitoACobrarParcelamento(idDebitoACobrar, null);

					} else {
						// caso não exista no debito a cobrar então
						// pesquisa no guia pagamento histórico
						if (cobrancaDocumentoItem[12] != null) {
							idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[12];
						}
					}
					// verifica o valor do item cobrado da
					// cobranca
					// documento item
					if (cobrancaDocumentoItem[3] != null) {
						valorItemCobrado = (BigDecimal) cobrancaDocumentoItem[3];
					}

					// se o id da conta for igual a null
					if (idContaGeralPesquisa == null) {
						// caso exista guia de pagamento
						if (idGuiaPagamentoGeralPesquisa != null) {
							// verifica o id do debito tipo se é da
							// guia
							if (cobrancaDocumentoItem[6] != null) {
								idDebitoTipo = (Integer) cobrancaDocumentoItem[6];
							} else {
								// caso não exista no guia pagamento
								// então
								// pesquisa no guia pagamento
								// histórico
								if (cobrancaDocumentoItem[7] != null) {
									idDebitoTipo = (Integer) cobrancaDocumentoItem[7];
								}
							}
						}
						// caso exista debito a cobrar
						if (idDebitoACobrarGeralPesquisa != null) {
							// verifica o id do debito tipo no
							// debito a cobrar
							if (cobrancaDocumentoItem[8] != null) {
								idDebitoTipo = (Integer) cobrancaDocumentoItem[8];

							} else {
								// caso não exista no debito a
								// cobrar
								// então
								// pesquisa no debito a cobrar
								// histórico
								if (cobrancaDocumentoItem[9] != null) {
									idDebitoTipo = (Integer) cobrancaDocumentoItem[9];
								}
							}
						}
					}

					// cria o objeto pagamento para setar os
					// dados
					Pagamento pagamento = new Pagamento();
					if (contaReferencia != 0) {
						pagamento.setAnoMesReferenciaPagamento(contaReferencia);
					} else {
						pagamento.setAnoMesReferenciaPagamento(null);
					}

					// caso o ano mes da data de dedito seja
					// maior que o ano mes de arrecadação da
					// tabela sistema parametro então seta o ano
					// mes da data de debito
					if (anoMesPagamento > sistemaParametro
							.getAnoMesArrecadacao()) {
						pagamento
								.setAnoMesReferenciaArrecadacao(anoMesPagamento);
					} else {
						// caso contrario seta o o ano mes
						// arrecadação da tabela sistema
						// parametro
						pagamento
								.setAnoMesReferenciaArrecadacao(sistemaParametro
										.getAnoMesArrecadacao());
					}
					pagamento.setValorPagamento(valorItemCobrado);
					pagamento.setDataPagamento(dataPagamento);
					pagamento.setPagamentoSituacaoAtual(null);
					pagamento.setPagamentoSituacaoAnterior(null);
					if (idDebitoTipo != null) {
						DebitoTipo debitoTipo = new DebitoTipo();
						debitoTipo.setId(idDebitoTipo);
						pagamento.setDebitoTipo(debitoTipo);
					} else {
						pagamento.setDebitoTipo(null);
					}

					// verifica se o id da conta é diferente de
					// nulo
					if (idContaGeralPesquisa != null) {
						if (idContaPesquisa != null) {
							ContaGeral conta = new ContaGeral();
							conta.setId(idContaPesquisa);
							pagamento.setContaGeral(conta);
						} else {
							pagamento.setContaGeral(null);
						}

						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.CONTA);
						pagamento.setDocumentoTipo(documentoTipo);
					} else {
						pagamento.setContaGeral(null);
					}
					// verifica se o id da guia de pagamento é
					// diferente de nulo
					if (idGuiaPagamentoGeralPesquisa != null) {
						if (idGuiaPagamento != null) {
							GuiaPagamento guiaPagamento = new GuiaPagamento();
							guiaPagamento.setId(idGuiaPagamento);
							pagamento.setGuiaPagamento(guiaPagamento);

						} else {
							pagamento.setGuiaPagamento(null);
						}
						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
						pagamento.setDocumentoTipo(documentoTipo);

					} else {
						pagamento.setGuiaPagamento(null);
					}

					// verifica se o id do debito a cobrar é
					// diferente de nulo
					if (idDebitoACobrarGeralPesquisa != null) {
						if (idDebitoACobrar != null) {

							DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
							debitoACobrarGeral.setId(idDebitoACobrar);
							
							pagamento.setDebitoACobrarGeral(debitoACobrarGeral);

							try {
								// atualiza a situação atual para
								// cancelada
								repositorioFaturamento
										.atualizarSituacaoAtualDebitoACobrar(idDebitoACobrar);
							} catch (ErroRepositorioException e) {
								throw new ControladorException("erro.sistema",
										e);
							}

						} else {
							pagamento.setDebitoACobrarGeral(null);
						}
						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
						pagamento.setDocumentoTipo(documentoTipo);

					} else {
						pagamento.setGuiaPagamento(null);
					}

					// seta o id do aviso bancario
					pagamento.setAvisoBancario(null);

					//SETANDO A LOCALIDADE E O IMÓVEL DO PAGAMENTO 
					Imovel imovel = new Imovel();
					Localidade localidade = new Localidade();
					
					//CONTA
					if (idContaGeralPesquisa != null) {
						
						if (cobrancaDocumentoItem[16] != null){
							
							//Imovel na tabela CONTA
							imovel.setId((Integer) cobrancaDocumentoItem[13]);
							
							//Localidade na tabela CONTA
							localidade.setId((Integer) cobrancaDocumentoItem[16]);
						}
						else{
							
							//Imovel na tabela CONTA_HISTORICO
							imovel.setId((Integer) cobrancaDocumentoItem[19]);
							
							//Localidade na tabela CONTA_HISTORICO
							localidade.setId((Integer) cobrancaDocumentoItem[20]);
						}
						
						pagamento.setImovel(imovel);
						pagamento.setLocalidade(localidade);
						
					} 
					
					//GUIA_PAGAMENTO
					else if (idGuiaPagamentoGeralPesquisa != null) {
						
						if (cobrancaDocumentoItem[17] != null){
							
							//Imovel na tabela GUIA_PAGAMENTO
							imovel.setId((Integer) cobrancaDocumentoItem[14]);
							
							//Localidade na tabela GUIA_PAGAMENTO
							localidade.setId((Integer) cobrancaDocumentoItem[17]);
						}
						else{
							
							//Imovel na tabela GUIA_PAGAMENTO_HISTORICO
							imovel.setId((Integer) cobrancaDocumentoItem[21]);
							
							//Localidade na tabela GUIA_PAGAMENTO_HISTORICO
							localidade.setId((Integer) cobrancaDocumentoItem[22]);
						}
						
						pagamento.setImovel(imovel);
						pagamento.setLocalidade(localidade);
						
					} 
					
					//DEBITO_A_COBRAR
					else if (idDebitoACobrarGeralPesquisa != null) {
						
						if (cobrancaDocumentoItem[18] != null){
							
							//Imovel na tabela DEBITO_A_COBRAR
							imovel.setId((Integer) cobrancaDocumentoItem[15]);
							
							//Localidade na tabela DEBITO_A_COBRAR
							localidade.setId((Integer) cobrancaDocumentoItem[18]);
						}
						else{
							
							//Imovel na tabela DEBITO_A_COBRAR_HISTORICO
							imovel.setId((Integer) cobrancaDocumentoItem[23]);
							
							//Localidade na tabela DEBITO_A_COBRAR_HISTORICO
							localidade.setId((Integer) cobrancaDocumentoItem[24]);
						}
						
						pagamento.setImovel(imovel);
						pagamento.setLocalidade(localidade);
					}

					/*
					 * Adicao dos campos 'id do Documento de cobranca' e 'id do tipo de documento' usados no
					 * relatório do Float
					 * Francisco 18/07/08
					 */
					if (cobrancaDocumentoItem[25] != null){
						idCobrancaDocumento = (Integer) cobrancaDocumentoItem[25]; 
					}
					if (cobrancaDocumentoItem[26] != null){
						idDocumentoTipo = (Integer) cobrancaDocumentoItem[26]; 
					}
					
					/*
					 * if (idImovelNaBase != null) { Imovel imovel = new
					 * Imovel(); imovel.setId(idImovelNaBase);
					 * pagamento.setImovel(imovel); } else {
					 * pagamento.setImovel(null); }
					 */

					// ArrecadadorMovimentoItem
					// arrecadadorMovimentoItem
					// = new ArrecadadorMovimentoItem();
					pagamento.setArrecadadorMovimentoItem(null);

					ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
					arrecadacaoForma.setId(idFormaPagamento);
					pagamento.setArrecadacaoForma(arrecadacaoForma);
					pagamento.setCliente(null);
					pagamento.setUltimaAlteracao(new Date());

					pagamento.setFatura(null);
					
					if (cobrancaDocumentoItem[25] != null){
						idCobrancaDocumento = (Integer) cobrancaDocumentoItem[25];	
					}
					if (cobrancaDocumentoItem[26] != null){
						idDocumentoTipo  = (Integer) cobrancaDocumentoItem[26];	
					}
					
					CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
					cobrancaDocumento.setId(idCobrancaDocumento);
					pagamento.setCobrancaDocumento(cobrancaDocumento);
					
					DocumentoTipo documentoAgregador = new DocumentoTipo();
					documentoAgregador.setId(idDocumentoTipo);
					pagamento.setDocumentoTipoAgregador(documentoAgregador);
					
					colecaoPagamnetos.add(pagamento);

				}
			} else {
				descricaoOcorrencia = "DOCUMENTO DE COBRANÇA INEXISTENTE";
				indicadorAceitacaoRegistro = "2";
			}

		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}

	protected void mandaArquivoLeituraEmail(StringBuilder arquivo,
			String emailReceptor, String emailRemetente, String tituloMensagem,
			String corpoMensagem) throws ControladorException {
		/*
		 * try { File leitura = new File("arquivo_leitura", ".txt");
		 * BufferedWriter out = new BufferedWriter(new OutputStreamWriter( new
		 * FileOutputStream(leitura.getAbsolutePath())));
		 * out.write(arquivo.toString()); out.close();
		 * 
		 * ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor,
		 * emailRemetente, tituloMensagem, corpoMensagem, leitura);
		 * 
		 * leitura.delete(); } catch (IOException e) { throw new
		 * ControladorException("erro.sistema", e); } catch (Exception e) {
		 * throw new ControladorException("erro.sistema", e); }
		 */
	}

	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conteúdo do do código de barras
	 * 
	 * [SF0003] Apresentar Dados do Conteúdo do Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @data 22/03/2006
	 * 
	 * @param registroHelperCodigoG
	 * @return DadosConteudoCodigoBarrasHelper
	 */
	public DadosConteudoCodigoBarrasHelper apresentarDadosConteudoCodigoBarras(
			RegistroHelperCodigoG registroHelperCodigoG)
			throws ControladorException {

		DadosConteudoCodigoBarrasHelper retorno = new DadosConteudoCodigoBarrasHelper();

		retorno.setIdentificacaoProduto(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdProduto());
		retorno.setIdentificacaoSegmento(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdSegmento());
		retorno.setIdentificacaoValorRealOUReferencia(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdValorReal());
		retorno.setDigitoVerificadorGeral(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getDigitoVerificadorGeral());
		retorno
				.setValorPagamento(Util
						.formatarMoedaReal(Util
								.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getValorPagamento())));

		String tipoPagamento = registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getTipoPagamento();

		String matriculaImovel = null;

		if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER);

			matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString();

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11).toString();
			}

			retorno.setMatriculaImovel(matriculaImovel);

			retorno.setMesAnoReferenciaConta(Util
					.formatarAnoMesParaMesAno(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento3()));

			retorno.setCodigoOrigemConta(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4().toString());

			retorno.setNumeroDocumento(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5().toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CAER);

			retorno.setQualificacao(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			retorno.setMesAno(Util
					.formatarAnoMesParaMesAno(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento3()));

			retorno.setNumeroDocumento(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_REAVISO_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_REAVISO_CAER);

			matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString();

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11).toString();
			}

			retorno.setMatriculaImovel(matriculaImovel);

			retorno.setSequencialDocumentoCobranca(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3()).toString());

			retorno.setCodigoTipoDebito(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5()).toString());

			retorno.setAnoEmissaoGuiaPagamento(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento6()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3()).toString();

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11).toString();
			}

			retorno.setMatriculaImovel(matriculaImovel);

			retorno.setCodigoTipoDebito(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5()).toString());

			retorno.setAnoEmissaoGuiaPagamento(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento6()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER);

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3()).toString());

			retorno.setSequencialDocumentoCobranca(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4());

			retorno.setCodigoTipoDocumento(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER);

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			retorno.setMesAno(Util
					.formatarMesAnoSemBarraParaMesAnoComBarra(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento4()));

			retorno.setDigitoVerificadorContaModulo10(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

			retorno.setSequencialFaturaClienteResponsavel(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento6());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER);

			matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString();

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11).toString();
			}

			retorno.setMatriculaImovel(matriculaImovel);

			retorno.setNumeroDocumento(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());

		}

		return retorno;

	}



	
	/**
	 * [UC0259] - Processar Pagamneto com Código de Barras
	 * 
	 * Autor: Rafael Pinto
	 * 
	 * Data: 19/04/2007
	 */
	protected Pagamento processarPagamentosCodigoBarrasTipoPagamento(
			int anoMes, Integer anoMesPagamento,
			SistemaParametro sistemaParametro, BigDecimal valorPagamento,
			Date dataPagamento, Integer idConta, Integer idLocalidade,
			Integer idFormaPagamento, Integer idImovelNaBase, Imovel imovel)
			throws ControladorException {

		Pagamento pagamento = new Pagamento();
		pagamento.setAnoMesReferenciaPagamento(anoMes);

		// caso o ano mes da data de dedito seja
		// maior que o ano mes de arrecadação da
		// tabela sistema parametro então seta o ano
		// mes da data de debito
		if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {
			pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);
		} else {
			// caso contrario seta o o ano mes
			// arrecadação da tabela sistema
			// parametro
			pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
					.getAnoMesArrecadacao());
		}

		pagamento.setValorPagamento(valorPagamento);
		pagamento.setDataPagamento(dataPagamento);
		pagamento.setPagamentoSituacaoAtual(null);
		pagamento.setPagamentoSituacaoAnterior(null);
		pagamento.setDebitoTipo(null);
		
		// Verifica se o id da conta é diferente de nulo
		if (idConta != null) {
			
			ContaGeral conta = new ContaGeral();
			conta.setId(idConta);
			pagamento.setContaGeral(conta);
			
			/*
			 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
			 * OBJ: Inserir o pagamento com a localidade da própria conta e não
			 * com a localidade do documento de cobrança
			 */
			try {
				idLocalidade = repositorioLocalidade
				.pesquisarIdLocalidadePorConta(idConta);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
		} 
		else {
				
			pagamento.setContaGeral(null);
				
		}
		
		//Seta a guia de pagamento pra NULL
		pagamento.setGuiaPagamento(null);

		// Verifica se o id da conta é diferente de nulo
		if (idLocalidade != null) {
			Localidade localidade = new Localidade();
			localidade.setId(idLocalidade);
			pagamento.setLocalidade(localidade);
		} 
		else {
			pagamento.setLocalidade(null);
		}
		
		DocumentoTipo documentoTipo = new DocumentoTipo();
		documentoTipo.setId(DocumentoTipo.CONTA);
		pagamento.setDocumentoTipo(documentoTipo);

		// seta o id do aviso bancario
		pagamento.setAvisoBancario(null);

		// seta o imovel
		if (idImovelNaBase != null) {
			pagamento.setImovel(imovel);
		} else {
			pagamento.setImovel(null);
		}

		pagamento.setArrecadadorMovimentoItem(null);

		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		arrecadacaoForma.setId(idFormaPagamento);
		pagamento.setArrecadacaoForma(arrecadacaoForma);
		pagamento.setCliente(null);
		pagamento.setUltimaAlteracao(new Date());

		pagamento.setFatura(null);
		pagamento.setCobrancaDocumento(null);
		
		DocumentoTipo documentoAgregador = new DocumentoTipo();
		documentoAgregador.setId(DocumentoTipo.CONTA);
		pagamento.setDocumentoTipoAgregador(documentoAgregador);
		
		pagamento.setDataProcessamento(new Date());

		return pagamento;
	}
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 *
	 * @author Rômulo Aurélio
	 * @date 05/03/2009
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
	
		if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());

			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CAER);
		} 
		else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_REAVISO_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_REAVISO_CAER);
		}
		else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_REAVISO_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_REAVISO_CAER);
		}
		else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER);
		}
		else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER);
		}
		else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER);
		}
		else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER);
		}
		else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER);
		}
		
	}
	
	/**
	 * Pesquisa os dados da Guia de Pagamento necessários para o relatório
	 * através do id da Guia de Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/10/06
	 * 
	 * @return Collection<GuiaPagamentoRelatorioHelper>
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */

	public Collection<GuiaPagamentoRelatorioHelper> pesquisarGuiaPagamentoRelatorio(
			String[] ids) throws ControladorException {

		Collection<GuiaPagamentoRelatorioHelper> colecaoGuiaPagamentoRelatorioHelper = new ArrayList();

		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {

				int idGuiaPagamento = Integer.parseInt(ids[i]);
				GuiaPagamentoRelatorioHelper guiaPagamentoRelatorioHelper = new GuiaPagamentoRelatorioHelper();

				try {
					guiaPagamentoRelatorioHelper = repositorioArrecadacao
							.pesquisarGuiaPagamentoRelatorio(idGuiaPagamento);
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}

				Integer idCliente = null;
				Integer idImovel = null;
				String matricula = "";
				String nomeCliente = "";
				String inscricao = "";
				String cpfCliente = "";
				String cnpjCliente = "";
				String sacadoParte01 = "";
				String sacadoParte02 = "";
				String nossoNumero = "";
				// Pesquisar Cliente
				// recupera cliente atraves de ClienteGuiaPagamento
				// se vier null
				// recupera atraves de Imovel.imovelCliente

				Object[] dadosCliente = null;

				try {
					dadosCliente = repositorioArrecadacao
							.pesquisarClienteDeGuiaPagamento(idGuiaPagamento);
				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				if (dadosCliente == null) {
					try {
						dadosCliente = repositorioArrecadacao
								.pesquisarImovelDeClienteGuiaPagamento(idGuiaPagamento);
					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

				}

				if (dadosCliente == null) {
					try {
						dadosCliente = repositorioArrecadacao
								.pesquisarClienteDeClienteImovel(idGuiaPagamento);
					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

				}

				if (dadosCliente != null) {
					if (dadosCliente[0] != null) {
						idCliente = (Integer) dadosCliente[0];
					}
					if (dadosCliente[1] != null) {
						nomeCliente = (String) dadosCliente[1];
					}
					if ( dadosCliente[2] != null ) {
						cpfCliente = (String) dadosCliente[2];
					}
					if ( dadosCliente[3] != null ) {
						cnpjCliente = (String) dadosCliente[3];
					}
				}

				if (guiaPagamentoRelatorioHelper.getIdImovel() == null) {
					// código do cliente
					matricula = "" + idCliente;

					// Inscrição do imóvel
					// caso imov_id = null, imprimir código da localidade
					// (loca_id)
					inscricao = ""
							+ guiaPagamentoRelatorioHelper.getIdLocalidade();

					// recupera endereço de correspondencia do cliente
					// [UC0085]Obter Endereco
					String enderecoClienteResponsavel = "";
					enderecoClienteResponsavel = this.getControladorEndereco()
							.pesquisarEnderecoClienteAbreviado(idCliente);
					guiaPagamentoRelatorioHelper
							.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

				} else {
					idImovel = guiaPagamentoRelatorioHelper.getIdImovel();
					// matrícula do imóvel
					matricula = "" + idImovel;
					// try {
					// nomeCliente = repositorioArrecadacao
					// .pesquisarNomeClienteGuiaPagamentoRelatorio(idGuiaPagamento);
					// } catch (ErroRepositorioException e) {
					// e.printStackTrace();
					// }

					// Inscrição do imóvel
					inscricao = getControladorImovel()
							.pesquisarInscricaoImovel(idImovel);

					// recupera endereco do imóvel
					String enderecoImovel = "";
					try {
						enderecoImovel = this.getControladorEndereco()
								.pesquisarEnderecoFormatado(idImovel);
					} catch (ControladorException e1) {
						e1.printStackTrace();
					}
					guiaPagamentoRelatorioHelper
							.setEnderecoImovel(enderecoImovel);
				}

				guiaPagamentoRelatorioHelper.setIdCliente(idCliente);
				guiaPagamentoRelatorioHelper.setIdImovel(idImovel);
				guiaPagamentoRelatorioHelper.setMatricula(matricula);
				guiaPagamentoRelatorioHelper.setNomeCliente(nomeCliente);
				guiaPagamentoRelatorioHelper.setInscricao(inscricao);
				guiaPagamentoRelatorioHelper.setIdGuiaPagamento(""
						+ idGuiaPagamento);
				guiaPagamentoRelatorioHelper.setCpfCliente(cpfCliente);
				guiaPagamentoRelatorioHelper.setCnpjCliente(cnpjCliente);

				String representacaoNumericaCodBarra = "";
				String anoEmissaoGuia = ""
						+ Util.getAno(guiaPagamentoRelatorioHelper
								.getDataEmissao());

				// caso imov_id da guia de pagamento esteja preenchido,
				// atribuir o valor 4 , caso contrário atribuir o valor 6
				Integer tipoPagamento = 4;
				if (guiaPagamentoRelatorioHelper.getIdImovel() == null
						|| guiaPagamentoRelatorioHelper.getIdImovel()
								.equals("")) {
					tipoPagamento = 6;
				}

				// [UC0229] - Obter Representação Numérica do Código de
				// Barras

				representacaoNumericaCodBarra = obterRepresentacaoNumericaCodigoBarra(
						tipoPagamento, // tipo
						// de
						// pagamento
						guiaPagamentoRelatorioHelper.getValorDebito(), // valor
						// do
						// código de
						// barras
						guiaPagamentoRelatorioHelper.getIdLocalidade(), // código
						// da
						// localidade
						guiaPagamentoRelatorioHelper.getIdImovel() == null ? null
								: guiaPagamentoRelatorioHelper.getIdImovel(), // matrícula
						// do imóvel
						null, // mês e ano de referência
						null, // digito verificador da referência
						guiaPagamentoRelatorioHelper.getIdTipoDebito(), // código
						// do
						// tipo do
						// débito
						anoEmissaoGuia, // ano da emissão da guia
						null, // sequencial do documento de
						// cobrança
						null, // código do tipo de documento
						guiaPagamentoRelatorioHelper.getIdCliente() == null ? null
								: guiaPagamentoRelatorioHelper.getIdCliente(), // código
						// do
						// cliente
						null,// sequencial da fatura do cliente
						null); 

				// Formata a representação númerica do código de barras
				String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
						.substring(0, 11)
						+ "-"
						+ representacaoNumericaCodBarra.substring(11, 12)
						+ " "
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ "-"
						+ representacaoNumericaCodBarra.substring(23, 24)
						+ " "
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ "-"
						+ representacaoNumericaCodBarra.substring(35, 36)
						+ " "
						+ representacaoNumericaCodBarra.substring(36, 47)
						+ "-" + representacaoNumericaCodBarra.substring(47, 48);

				guiaPagamentoRelatorioHelper
						.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

				String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
						.substring(0, 11)
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ representacaoNumericaCodBarra.substring(36, 47);

				guiaPagamentoRelatorioHelper
						.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
				
				guiaPagamentoRelatorioHelper.setSacadoParte01(sacadoParte01);
				guiaPagamentoRelatorioHelper.setSacadoParte02(sacadoParte02);
				guiaPagamentoRelatorioHelper.setNossoNumero(nossoNumero);
				guiaPagamentoRelatorioHelper.setSubRelatorio("relatorioEmitirGuiaPagamentoEmissaoPadrao.jasper");

				colecaoGuiaPagamentoRelatorioHelper
						.add(guiaPagamentoRelatorioHelper);
				
			}
		}

		return colecaoGuiaPagamentoRelatorioHelper;
	}
	
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * DOCUMENTO COBRANÇA TIPO 10
	 * 
	 * @author Ivan Sergio
	 * @data 18/10/2010
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasDocumentoCobrancaTipo10(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao, Usuario usuarioLogado) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
		String descricaoOcorrencia = "OK";
		String indicadorAceitacaoRegistro = "1";
		Collection colecaoPagamentos = new ArrayList();
		Collection colecaoDevolucoes = new ArrayList();
		Collection colecaoDebitosACobrarJurosParcelamento = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;

		// valida a matricula do imóvel
		matriculaImovelInvalida = Util.validarValorNaoNumerico(registroHelperCodigoBarras
				.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
		
		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {
			// verifica se existe a matricula do imóvel na base
			matriculaImovel = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			// [FS0008] - Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11("" + matriculaImovel);
				matriculaImovel = new Integer(matriculaImovel.toString() + digitoModulo11);
			}
			
			idImovelNaBase = null;
			
			try {
				idImovelNaBase = repositorioImovel.recuperarMatriculaImovel(new Integer(matriculaImovel));
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {
			// inicializa o id da localidade
			Integer idLocalidade = null;

			// inicializa a coleção de cobranca documento item
			Collection cobrancaDocumentoItens = null;
			// inicializa a coleção de cobranca documento item
			Object[] parmsDocumentoCobranca = null;

			int numeroSequencialDocumento = Integer.parseInt(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento5());

			try {
				cobrancaDocumentoItens = repositorioCobranca.pesquisarCobrancaDocumentoItem(
						idImovelNaBase, numeroSequencialDocumento);
				parmsDocumentoCobranca = repositorioCobranca.pesquisarParmsCobrancaDocumento(
						idImovelNaBase, numeroSequencialDocumento);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// caso exista documento de cobrança
			if (parmsDocumentoCobranca != null) {
				Integer idCobrancaDocumento = null;
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				BigDecimal valorDesconto = new BigDecimal("0.00");
				Date dataEmissao = null;
				BigDecimal valorTaxa = new BigDecimal("0.00");
				Integer idDocumentoTipo = null;
				
				if (parmsDocumentoCobranca[0] != null) {
					valorAcrescimo = ((BigDecimal) parmsDocumentoCobranca[0]);
				}
				if (parmsDocumentoCobranca[1] != null) {
					valorDesconto = ((BigDecimal) parmsDocumentoCobranca[1]);
				}
				if (parmsDocumentoCobranca[2] != null) {
					dataEmissao = ((Date) parmsDocumentoCobranca[2]);
				}
				if (parmsDocumentoCobranca[3] != null) {
					idCobrancaDocumento = ((Integer) parmsDocumentoCobranca[3]);
				}
				if (parmsDocumentoCobranca[4] != null) {
					valorTaxa = ((BigDecimal) parmsDocumentoCobranca[4]);
				}
				
				/*
                 * Alterado por Raphael Rossiter em 10/01/2008 - Analistas: Eduardo e Aryed
                 * OBJ: Gerar os pagamentos associados com a localidade do document de cobrança e NÃO com
                 * a localidade do imóvel.
                 */
				if (parmsDocumentoCobranca[5] != null) {
					idLocalidade = ((Integer) parmsDocumentoCobranca[5]);
				}
				else{
					try {
                        idLocalidade = repositorioLocalidade.pesquisarIdLocalidade(idImovelNaBase);
                    } catch (ErroRepositorioException e) {
                        throw new ControladorException("erro.sistema", e);
                    }
				}

				if (parmsDocumentoCobranca[6] != null) {
					idDocumentoTipo = ((Integer) parmsDocumentoCobranca[6]);
				}
				
				// caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0008 - Alterar vencimento dos itens do
					// documento de cobrança]
					alterarVencimentoItensDocumentoCobranca(idCobrancaDocumento, dataEmissao);
				}

				// caso o valor de acrescimos seja maior que o valor
				// de
				// descontos
				if (valorAcrescimo.compareTo(valorDesconto) == 1) {
					valorAcrescimo = valorAcrescimo.subtract(valorDesconto);
					valorDesconto = new BigDecimal("0.00");
				} else {
					valorDesconto = valorDesconto.subtract(valorAcrescimo);
					valorAcrescimo = new BigDecimal("0.00");
				}

				// caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0005 - Processar Recebimento de Acrescimos
					// por
					// Impontualidade]

					Pagamento pagamento = processarRecebimentoAcrescimosImpontualidade(
							idCobrancaDocumento, dataPagamento, valorAcrescimo,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaArrecadacao, idDocumentoTipo);

					colecaoPagamentos.add(pagamento);

				}

				// caso o valor de desconto for maior que zero
				if (valorDesconto.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0006 - Processar Desconto concedido no
					// documento de cobrança]
					Devolucao devolucao = processarDescontoConcedidoDocumentoCobranca(
							idCobrancaDocumento, dataPagamento, valorDesconto,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaArrecadacao, idDocumentoTipo);

					colecaoDevolucoes.add(devolucao);

				}

				// caso o valor da taxa referente ao documento de cobrança for
				// maior que zero
				if (valorTaxa.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0006 - Processar Desconto concedido no
					// documento de cobrança]
					Pagamento pagamento = processarTaxaDocumentoCobranca(
							idCobrancaDocumento, dataPagamento, valorTaxa,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaArrecadacao, idDocumentoTipo);

					colecaoPagamentos.add(pagamento);

				}

				// verifica se a coleção é diferente de nula
				if (cobrancaDocumentoItens != null && !cobrancaDocumentoItens.isEmpty()) {
					Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens.iterator();

					while (cobrancaDocumentoItensIterator.hasNext()) {
						Object[] arrayCobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator.next();
						CobrancaDocumentoItem cobrancaDocumentoItem = new CobrancaDocumentoItem();
						
						//VALOR DO ITEM COBRADO
						cobrancaDocumentoItem.setValorItemCobrado((BigDecimal) arrayCobrancaDocumentoItem[3]);
						
						//NUMERO DE PARCELAS ANTECIPADAS
						cobrancaDocumentoItem.setNumeroParcelasAntecipadas((Integer) arrayCobrancaDocumentoItem[18]);
						
						/*
						 * Colocado por Raphael Rossiter em 31/10/2007 OBJ:
						 * Apenas gerar os pagamentos referentes aos itens que
						 * NAO tenham CreditoARealizar
						 */
						if (arrayCobrancaDocumentoItem[13] == null) {
							ContaGeral contaGeral = null;
							Conta conta = null;
							
							//CONTA
							if (arrayCobrancaDocumentoItem[0] != null) {
								conta = new Conta();
								conta.setId((Integer) arrayCobrancaDocumentoItem[0]);
								
								//REFERENCIA DA CONTA
								if (arrayCobrancaDocumentoItem[4] != null) {
									conta.setReferencia((Integer) arrayCobrancaDocumentoItem[4]);
								}
								else{
									conta.setReferencia(0);
								}
								
								contaGeral = new ContaGeral();
								contaGeral.setConta(conta);
								contaGeral.setId(conta.getId());
								
								cobrancaDocumentoItem.setContaGeral(contaGeral);
							} 
							
							//CONTA HISTORICO
							else if (arrayCobrancaDocumentoItem[10] != null){
								conta = new Conta();
								conta.setId((Integer) arrayCobrancaDocumentoItem[10]);
								
								//REFERENCIA DA CONTA
								if (arrayCobrancaDocumentoItem[5] != null) {
									conta.setReferencia((Integer) arrayCobrancaDocumentoItem[5]);
								}
								else{
									conta.setReferencia(0);
								}
								
								contaGeral = new ContaGeral();
								contaGeral.setConta(conta);
								contaGeral.setId(conta.getId());
								
								cobrancaDocumentoItem.setContaGeral(contaGeral);
							}
							
							GuiaPagamentoGeral guiaPagamentoGeral = null;
							GuiaPagamento guiaPagamento = null;
							
							//GUIA DE PAGAMENTO
							if (arrayCobrancaDocumentoItem[1] != null) {
								guiaPagamentoGeral = new GuiaPagamentoGeral();
								guiaPagamento = new GuiaPagamento();
								
								guiaPagamento.setId((Integer) arrayCobrancaDocumentoItem[1]);
								guiaPagamentoGeral.setGuiaPagamento(guiaPagamento);
								guiaPagamentoGeral.setId(guiaPagamento.getId());
								
								cobrancaDocumentoItem.setGuiaPagamentoGeral(guiaPagamentoGeral);
							}
							
							//GUIA DE PAGAMENTO HISTORICO
							else if (arrayCobrancaDocumentoItem[11] != null){
								guiaPagamentoGeral = new GuiaPagamentoGeral();
								guiaPagamento = new GuiaPagamento();
								
								guiaPagamento.setId((Integer) arrayCobrancaDocumentoItem[11]);
								guiaPagamentoGeral.setGuiaPagamento(guiaPagamento);
								guiaPagamentoGeral.setId(guiaPagamento.getId());
								
								cobrancaDocumentoItem.setGuiaPagamentoGeral(guiaPagamentoGeral);
							}
							
							DebitoACobrarGeral debitoACobrarGeral = null;
							DebitoACobrar debitoACobrar = null;
							
							//DEBITO A COBRAR
							if (arrayCobrancaDocumentoItem[2] != null) {
								debitoACobrarGeral = new DebitoACobrarGeral();
								debitoACobrar = new DebitoACobrar();
								
								debitoACobrar.setId((Integer) arrayCobrancaDocumentoItem[2]);
								debitoACobrar.setNumeroPrestacaoDebito((Short) arrayCobrancaDocumentoItem[16]);
								debitoACobrar.setNumeroPrestacaoCobradas((Short) arrayCobrancaDocumentoItem[17]);
								
								debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
								debitoACobrarGeral.setId(debitoACobrar.getId());
								
								cobrancaDocumentoItem.setDebitoACobrarGeral(debitoACobrarGeral);
								
								// [SB0012]- Verifica Pagamento de Débito a Cobrar de Parcelamento
								this.verificaPagamentoDebitoACobrarParcelamento(cobrancaDocumentoItem
										.getDebitoACobrarGeral().getDebitoACobrar().getId(),
										cobrancaDocumentoItem.getNumeroParcelasAntecipadas());
							}
							
							//DEBITO A COBRAR HISTORICO
							else if (arrayCobrancaDocumentoItem[12] != null){
								debitoACobrarGeral = new DebitoACobrarGeral();
								debitoACobrar = new DebitoACobrar();
								
								debitoACobrar.setId((Integer) arrayCobrancaDocumentoItem[12]);
								
								debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
								debitoACobrarGeral.setId(debitoACobrar.getId());
								
								cobrancaDocumentoItem.setDebitoACobrarGeral(debitoACobrarGeral);
							}
							
							//CRÉDITO A REALIZAR (UTILIZADO PARA PAGAMENTO ANTECIPADO)
							
							//IDENTIFICANDO O TIPO DE DEBITO QUE SERA ASSOCIADO AO PAGAMENTO
							Integer idDebitoTipo = null;
							
							if (cobrancaDocumentoItem.getContaGeral() == null) {
								//CASO SEJA PARA GUIA DE PAGAMENTO
								if (cobrancaDocumentoItem.getGuiaPagamentoGeral() != null) {
									//GUIA DE PAGAMENTO
									if (arrayCobrancaDocumentoItem[6] != null) {
										idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[6];
									} 
									//GUIA DE PAGAMENTO HISTORICO
									else if (arrayCobrancaDocumentoItem[7] != null){
										 idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[7];
									}
								}
								
								//CASO SEJA PARA DEBITO A COBRAR
								if (cobrancaDocumentoItem.getDebitoACobrarGeral() != null) {
									//DEBITO A COBRAR
									if (arrayCobrancaDocumentoItem[8] != null) {
										idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[8];
									} 
									//DEBITO A COBRAR HISTORICO
									else if (arrayCobrancaDocumentoItem[9] != null) {
										idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[9];
									}
								}
							}
							
							//[SB0019] – Gerar Débitos/Créditos Parcelas Antecipadas
							DebitoACobrar debitoACobrarAntecipacao = null;
							if (cobrancaDocumentoItem.getNumeroParcelasAntecipadas() != null){
								debitoACobrarAntecipacao = (DebitoACobrar) 
								this.gerarDebitoCreditoParcelasAntecipadas(
										idImovelNaBase, cobrancaDocumentoItem, usuarioLogado);
								
								/*
								* Caso o débito a cobrar com parcelas antecipadas tenha juros de parcelamento (FNTP_ID = “Juros de Parcelamento” da
								* tabela DEBITO_A_COBRAR com PARC_ID = PARC_ID do débito com parcelas antecipadas). O sistema deverá
								* atualizar a quantidade de parcela bônus do débito a cobrar de juros (DBAC_NNPARCELABONUS =
								* DBAC_NNPARCELABONUS + quantidade de parcelas antecipadas e DBAC_TMULTIMAALTERACAO = Data e Hora Correntes)
								*/
								DebitoACobrar debitoACobrarJurosParcelamento = this.pesquisarDebitoACobrarJurosParcelamento(
								debitoACobrarAntecipacao.getParcelamento().getId());

								if (debitoACobrarJurosParcelamento != null){
									if (!colecaoDebitosACobrarJurosParcelamento.contains(debitoACobrarJurosParcelamento)){
										Short numeroParcelaBonus = debitoACobrarAntecipacao.getNumeroPrestacaoDebito();
										if (debitoACobrarJurosParcelamento.getNumeroParcelaBonus() != null){
											numeroParcelaBonus = Short.valueOf(String.valueOf(
													debitoACobrarJurosParcelamento.getNumeroParcelaBonus()
													.shortValue() + debitoACobrarAntecipacao
													.getNumeroPrestacaoDebito()));
										}

										debitoACobrarJurosParcelamento.setNumeroParcelaBonus(numeroParcelaBonus);
										colecaoDebitosACobrarJurosParcelamento.add(debitoACobrarJurosParcelamento);
									}
								}
							}
							
							//GERANDO O PAGAMENTO
							Pagamento pagamento = new Pagamento();
							
							//REFERENCIA DO PAGAMENTO
							if (cobrancaDocumentoItem.getContaGeral() != null &&
								cobrancaDocumentoItem.getContaGeral().getConta().getReferencia() != 0) {
								
								pagamento.setAnoMesReferenciaPagamento(cobrancaDocumentoItem.getContaGeral().getConta().getReferencia());
							} 
							else {
								pagamento.setAnoMesReferenciaPagamento(null);
							}

							/*
							 * Caso o ano mes da data de debito seja maior que o ano mes de arrecadação da
							 * tabela sistema parametro então seta o ano mes da data de debito
							 */
							if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {
								pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);
							} 
							//Caso contrario seta o o ano mes arrecadação da tabela sistema parametro
							else {
								pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro.getAnoMesArrecadacao());
							}
							
							//VALOR DO PAGAMENTO
							pagamento.setValorPagamento(cobrancaDocumentoItem.getValorItemCobrado());
							
							//DATA DO PAGAMENTO
							pagamento.setDataPagamento(dataPagamento);
							
							//SITUAÇÃO ATUAL
							pagamento.setPagamentoSituacaoAtual(null);
							
							//SITUAÇÃO ANTERIOR
							pagamento.setPagamentoSituacaoAnterior(null);
							
							if (idDebitoTipo != null) {
								DebitoTipo debitoTipo = new DebitoTipo();
								debitoTipo.setId(idDebitoTipo);
								pagamento.setDebitoTipo(debitoTipo);
							} else {
								pagamento.setDebitoTipo(null);
							}

							//VERIFICA SE O PAGAMENTO SERÁ RELACIONADO COM UMA CONTA
							if (cobrancaDocumentoItem.getContaGeral() != null) {
								/*
								 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
								 * OBJ: Inserir o pagamento com a localidade da própria conta e não
								 * com a localidade do documento de cobrança
								 */
								Integer idLocalidadeConta = null;
								
								try {
									idLocalidadeConta = repositorioLocalidade.pesquisarIdLocalidadePorConta(
											cobrancaDocumentoItem.getContaGeral().getConta().getId());
					            } catch (ErroRepositorioException e) {
					            	throw new ControladorException("erro.sistema", e);
					            }
					            
					            if (idLocalidadeConta != null){
					            	pagamento.setContaGeral(cobrancaDocumentoItem.getContaGeral());
					            }
					            else{
					            	try {
										idLocalidadeConta = repositorioLocalidade
											.pesquisarIdLocalidadePorContaHistorico(
													cobrancaDocumentoItem.getContaGeral().getConta().getId());
						            } catch (ErroRepositorioException e) {
						            	throw new ControladorException("erro.sistema", e);
						            }
					            }
					            
					            idLocalidade = idLocalidadeConta;
								 
								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.CONTA);
								pagamento.setDocumentoTipo(documentoTipo);
							} 
							else {
								
								pagamento.setContaGeral(null);
							}
							
							//VERIFICA SE O PAGAMENTO SERÁ RELACIONADO COM UMA GUIA DE PAGAMENTO
							if (cobrancaDocumentoItem.getGuiaPagamentoGeral() != null) {
								/*
								 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
								 * OBJ: Inserir o pagamento com a localidade da própria guia e não
								 * com a localidade do documento de cobrança
								 */
								Integer idLocalidadeGuiaPagamento = null;
								
								try {
									idLocalidadeGuiaPagamento = repositorioLocalidade
										.pesquisarIdLocalidadePorGuiaPagamento(
													cobrancaDocumentoItem.getGuiaPagamentoGeral()
													.getGuiaPagamento().getId());

					            } catch (ErroRepositorioException e) {
					            	throw new ControladorException("erro.sistema", e);
					            }
					            
					            if (idLocalidadeGuiaPagamento != null){
					            	pagamento.setGuiaPagamento(cobrancaDocumentoItem.getGuiaPagamentoGeral().getGuiaPagamento());
					            }
					            else{
					            	try {
					            		idLocalidadeGuiaPagamento = repositorioLocalidade
					            			.pesquisarIdLocalidadePorGuiaPagamentoHistorico(
					            					cobrancaDocumentoItem.getGuiaPagamentoGeral()
					            					.getGuiaPagamento().getId());

						            } catch (ErroRepositorioException e) {
						            	throw new ControladorException("erro.sistema", e);
						            }
					            }
					            
					            idLocalidade = idLocalidadeGuiaPagamento;
								
								DocumentoTipo documentoTipo = new DocumentoTipo();
								
								/*
								 * verificar se o tipo de debito eh 'entrada de parcelamento', e preencher o documentotipo
								 * com o 'entrada de parcelamento'
								 */
								
								// Alterado por Rômulo Aurélio, Analista Rosana/Aryed
								// quando o tipo de debito for Entrada de Guia é pra inserir 
								// o tipo de documento como guia de Parcelamento
								documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);	
								documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA);
								pagamento.setDocumentoTipo(documentoTipo);
							} 
							else {
								pagamento.setGuiaPagamento(null);
							}

							//VERIFICA SE O PAGAMENTO SERÁ RELACIONADO COM UM DEBITO A COBRAR
							if (cobrancaDocumentoItem.getDebitoACobrarGeral() != null) {
								try {
									if (debitoACobrarAntecipacao != null){
										debitoACobrarGeral.setDebitoACobrar(debitoACobrarAntecipacao);
										debitoACobrarGeral.setId(debitoACobrarAntecipacao.getId());
										
										pagamento.setDebitoACobrarGeral(debitoACobrarGeral);
										
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade do próprio debito a cobrar e não
										 * com a localidade do documento de cobrança
										 */
										idLocalidade = repositorioLocalidade
											.pesquisarIdLocalidadePorDebitoACobrar(
													debitoACobrarGeral.getDebitoACobrar().getId());
									}
									else if (cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar().getNumeroPrestacaoCobradas() !=
										cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar().getNumeroPrestacaoDebito()) {
											
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade do próprio debito a cobrar e não
										 * com a localidade do documento de cobrança
										 */
										Integer idLocalidadeDebitoACobrar = null;
										
										try {
											idLocalidadeDebitoACobrar = repositorioLocalidade
												.pesquisarIdLocalidadePorDebitoACobrar(
														cobrancaDocumentoItem.getDebitoACobrarGeral()
														.getDebitoACobrar().getId());

							            } catch (ErroRepositorioException e) {
							            	throw new ControladorException("erro.sistema", e);
							            }
							            
							            if (idLocalidadeDebitoACobrar != null){
							            	pagamento.setDebitoACobrarGeral(cobrancaDocumentoItem.getDebitoACobrarGeral());
							            }
							            else{
							            	try {
							            		idLocalidadeDebitoACobrar = repositorioLocalidade
							            			.pesquisarIdLocalidadePorDebitoACobrarHistorico(
							            					cobrancaDocumentoItem.getDebitoACobrarGeral()
							            					.getDebitoACobrar().getId());

								            } catch (ErroRepositorioException e) {
								            	throw new ControladorException("erro.sistema", e);
								            }
							            }
							            
							            idLocalidade = idLocalidadeDebitoACobrar;
									}
										
								} catch (ErroRepositorioException e) {
									throw new ControladorException("erro.sistema", e);
								}

								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
								pagamento.setDocumentoTipo(documentoTipo);
							} 
							else {
								pagamento.setDebitoACobrarGeral(null);
							}

							//LOCALIDADE
							if (idLocalidade != null) {
								Localidade localidade = new Localidade();
								localidade.setId(idLocalidade);
								pagamento.setLocalidade(localidade);
							} else {
								pagamento.setLocalidade(null);
							}

							//AVISO BANCARIO
							pagamento.setAvisoBancario(null);

							//IMOVEL
							if (idImovelNaBase != null) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelNaBase);
								pagamento.setImovel(imovel);
							} else {
								pagamento.setImovel(null);
							}

							pagamento.setArrecadadorMovimentoItem(null);

							ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
							arrecadacaoForma.setId(idFormaArrecadacao);
							pagamento.setArrecadacaoForma(arrecadacaoForma);
							pagamento.setCliente(null);
							pagamento.setUltimaAlteracao(new Date());
							
							pagamento.setFatura(null);
							
							CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
							cobrancaDocumento.setId(idCobrancaDocumento);
							pagamento.setCobrancaDocumento(cobrancaDocumento);							
							
							// documento tipo do documento de cobranca
							if(idDocumentoTipo != null){
								DocumentoTipo documentoAgregador = new DocumentoTipo();
								documentoAgregador.setId(idDocumentoTipo);
								pagamento.setDocumentoTipoAgregador(documentoAgregador);
							}
							
							pagamento.setDataProcessamento(new Date());
							
							if (pagamento.getDocumentoTipo() != null) {
								colecaoPagamentos.add(pagamento);
							}
						} 
						else {

							CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
							creditoARealizarGeral.setId((Integer) arrayCobrancaDocumentoItem[13]);
							
							cobrancaDocumentoItem.setCreditoARealizarGeral(creditoARealizarGeral);
							
							//[SB0019] – Gerar Débitos/Créditos Parcelas Antecipadas
							CreditoARealizar creditoARealizarAntecipacao = null;
							if (cobrancaDocumentoItem.getNumeroParcelasAntecipadas() != null){
								
								creditoARealizarAntecipacao = (CreditoARealizar) this.gerarDebitoCreditoParcelasAntecipadas(idImovelNaBase, 
								cobrancaDocumentoItem, usuarioLogado);
								
								creditoARealizarGeral.setId(creditoARealizarAntecipacao.getId());
								creditoARealizarGeral.setCreditoARealizar(creditoARealizarAntecipacao);
							}
							
							// Para os itens que tenham CreditoARealizar gerar
							// suas respectivas devoluções

							Devolucao devolucao = new Devolucao();

							// DataDevolucao = DataPagamento
							devolucao.setDataDevolucao(dataPagamento);

							/*
							 * AnoMesReferenciaDevolucao Caso o anoMes da data
							 * de devolução seja MAIOR que a
							 * PARM_AMREFERENCIAARRECADACAO da tabela
							 * SISTEMA_PARAMETROS atribuir o anoMes da data da
							 * devolução, caso contrário atribuir o
							 * PARM_AMREFERENCIAARRECADACAO.
							 */
							Integer anoMesDataDevolucao = Util.getAnoMesComoInteger(devolucao.getDataDevolucao());

							if (anoMesDataDevolucao > sistemaParametro.getAnoMesArrecadacao()) {
								
								devolucao.setAnoMesReferenciaArrecadacao(anoMesDataDevolucao);
							} 
							else {
								
								devolucao.setAnoMesReferenciaArrecadacao(sistemaParametro.getAnoMesArrecadacao());
							}

							// ValorDevolucao = ValorItemCobrado
							devolucao.setValorDevolucao(cobrancaDocumentoItem.getValorItemCobrado());

							// Localidade = Localidade da tabela
							// COBRANCA_DOCUMENTO
							if (arrayCobrancaDocumentoItem[14] != null) {
								Localidade localidade = new Localidade();
								localidade
										.setId((Integer) arrayCobrancaDocumentoItem[14]);
								devolucao.setLocalidade(localidade);
							}

							// Imovel = Imovel da tabela COBRANCA_DOCUMENTO
							if (arrayCobrancaDocumentoItem[15] != null) {
								Imovel imovel = new Imovel();
								imovel
										.setId((Integer) arrayCobrancaDocumentoItem[15]);
								devolucao.setImovel(imovel);
							}

							// DebitoTipo = DebitoTipo com o valor
							// correspondente a outros
							DebitoTipo debitoTipo = new DebitoTipo();
							debitoTipo.setId(DebitoTipo.OUTROS);
							devolucao.setDebitoTipo(debitoTipo);

							// CreditoARealizarGeral = CreditoARealizarGeral da
							// tabela COBRANCA_DOCUMENTO_ITEM
							devolucao.setCreditoARealizarGeral(creditoARealizarGeral);

							// Ultima Alteração
							devolucao.setUltimaAlteracao(new Date());

							CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
							cobrancaDocumento.setId(idCobrancaDocumento);
							devolucao.setCobrancaDocumento(cobrancaDocumento);							
							
							// documento tipo do documento de cobranca
							if(idDocumentoTipo != null){
								DocumentoTipo documentoAgregador = new DocumentoTipo();
								documentoAgregador.setId(idDocumentoTipo);
								devolucao.setDocumentoTipoAgregador(documentoAgregador);
							}
							
							// ADICIONANDO A DEVOLUCAO GERADA NA COLECAO DE
							// RETORNO
							colecaoDevolucoes.add(devolucao);
						}
					}
					
					/*
					 * Caso o débito a cobrar com parcelas antecipadas tenha juros de parcelamento (FNTP_ID = “Juros de Parcelamento” da 
					 * tabela DEBITO_A_COBRAR com PARC_ID = PARC_ID do débito com parcelas antecipadas). O sistema deverá 
					 * atualizar a quantidade de parcela bônus do débito a cobrar de juros (DBAC_NNPARCELABONUS = 
					 * DBAC_NNPARCELABONUS + quantidade de parcelas antecipadas e DBAC_TMULTIMAALTERACAO = Data e Hora Correntes)
					 */
					if (colecaoDebitosACobrarJurosParcelamento != null &&
						!colecaoDebitosACobrarJurosParcelamento.isEmpty()){
						
						Iterator itDebitosACobrarJurosParcelamento = colecaoDebitosACobrarJurosParcelamento.iterator();
						
						while(itDebitosACobrarJurosParcelamento.hasNext()){
							
							this.atualizarNumeroParcelasBonus((DebitoACobrar) itDebitosACobrarJurosParcelamento.next());
						}
					}
				}

			} else {
				descricaoOcorrencia = "DOCUMENTO DE COBRANÇA INEXISTENTE";
				indicadorAceitacaoRegistro = "2";
			}
		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setColecaoDevolucao(colecaoDevolucoes);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
}
