package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.DeterminarValorDescontoAcrescimosImpontualidadeHelper;
import gcom.cobranca.bean.DeterminarValorDescontoPagamentoAVistaHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import javax.ejb.SessionBean;

import br.com.danhil.BarCode.Interleaved2of5;

/**
 * Controlador Cobranca CAEMA
 *
 * @author Sávio Luiz
 * @date 28/04/2008
 */
public class ControladorCobrancaCAEMASEJB extends ControladorCobranca implements SessionBean {

	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAEMA
	//==============================================================================================================
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 * Calcula o valor total dos descontos para pagamento à vista
	 *
	 * @author Raphael Rossiter
	 * @date 23/09/2008
	 *
	 * @param DeterminarValorDescontoPagamentoAVistaHelper
	 * @param DeterminarValorDescontoAcrescimosImpontualidadeHelper
	 * @return BigDecimal
	 * @throws ControladorException
	 */
	public BigDecimal determinarValorDescontoPagamentoAVista(
		DeterminarValorDescontoPagamentoAVistaHelper valorDescontoPagamentoAVista,
		DeterminarValorDescontoAcrescimosImpontualidadeHelper valorDescontoAcrescimos) throws ControladorException {
		
		BigDecimal retorno = BigDecimal.ZERO;
		
		if (valorDescontoPagamentoAVista.getParcelamentoPerfil().getPercentualDescontoPagamentoAVista() != null && 
			!valorDescontoPagamentoAVista.getParcelamentoPerfil().getPercentualDescontoPagamentoAVista()
			.equals(BigDecimal.ZERO)) {
			
			BigDecimal valorDescontos = BigDecimal.ZERO;
			BigDecimal percentualDesconto = BigDecimal.ZERO;
			BigDecimal valorTotalAcrescimosImpontualidade = BigDecimal.ZERO;

			//CALCULANDO O PERCENTUAL DO DESCONTO
			percentualDesconto = Util.dividirArredondando(
			valorDescontoPagamentoAVista.getParcelamentoPerfil().getPercentualDescontoPagamentoAVista().setScale(
			Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), ConstantesSistema.CEM);
			
			//TOTALIZANDO O VALOR DOS ACRÉSCIMOS POR IMPONTUALIDADE (ANTIGUIDADE + GUIA_PAGAMENTO)
			/*
			 * TODO - COSANPA
			 */
			valorTotalAcrescimosImpontualidade = 
			valorDescontoAcrescimos.getValorTotalAcrescimosImpontualidadePorAntiguidadeMulta().setScale(
			Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)
			.add(valorDescontoAcrescimos.getValorTotalAcrescimosImpontualidadeGuiaPagamentoMulta()
			.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
			
			//CALCULANDO O DESCONTO
			valorDescontos = valorTotalAcrescimosImpontualidade.setScale(
			Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)
			.multiply(percentualDesconto.setScale(Parcelamento.CASAS_DECIMAIS,
			Parcelamento.TIPO_ARREDONDAMENTO));
			
			//DESCONTO ANTIGUIDADE
			valorDescontos = valorDescontos
			.add(valorDescontoPagamentoAVista.getValorDescontoAntiguidade());
			
			//VALORES DOS DEBITOS A COBRAR E COBRADOS
			valorDescontos = valorDescontos
			.add(valorDescontoAcrescimos.getValorDescontoAcrescimosImpontualidadeRDEspecial());
			
			retorno = valorDescontos;

		}
		
		return retorno;
	}
	
	/**
	 * 
	 * Este caso de uso gera os avisos de cobrança dos documentos de cobrança
	 * 
	 * [UC0575] Emitir Aviso de Cobrança
	 * 
	 * 
	 * @author Sávio Luiz, Raphael Rossiter
	 * @data 02/04/2007, 03/01/2007
	 * 
	 * @param
	 * @return void
	 */
	public void emitirAvisoCobrancaFormatado(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {

		boolean flagFimPesquisa = false;
		final int quantidadeCobrancaDocumento = 1000;
		int quantidadeCobrancaDocumentoInicio = 0;

		StringBuilder cobrancaDocumentoTxt = new StringBuilder();
		int sequencialImpressao = 0;

		Collection colecaoCobrancaDocumento = null;

		Integer idCronogramaAtividadeAcaoCobranca = null;
		Integer idComandoAtividadeAcaoCobranca = null;
		Integer idAcaoCobranca = null;
		if (cobrancaAcaoAtividadeCronograma != null
				&& cobrancaAcaoAtividadeCronograma.getId() != null) {
			idCronogramaAtividadeAcaoCobranca = cobrancaAcaoAtividadeCronograma
					.getId();
		}
		if (cobrancaAcaoAtividadeComando != null
				&& cobrancaAcaoAtividadeComando.getId() != null) {
			idComandoAtividadeAcaoCobranca = cobrancaAcaoAtividadeComando
					.getId();
		}
		if (acaoCobranca != null && acaoCobranca.getId() != null) {
			idAcaoCobranca = acaoCobranca.getId();
		}

		while (!flagFimPesquisa) {

			try {

				System.out.println("***************************************");
				System.out.println("ENTROU NO AVISO DE CORTE");
				System.out.println("***************************************");
				colecaoCobrancaDocumento = repositorioCobranca
						.pesquisarCobrancaDocumentoParaEmitirCAER(
								idCronogramaAtividadeAcaoCobranca,
								idComandoAtividadeAcaoCobranca,
								dataAtualPesquisa, idAcaoCobranca,
								quantidadeCobrancaDocumentoInicio);
				System.out.println("***************************************");
				System.out.println("QTD DE COBRANCA DOCUMENTO:"
						+ colecaoCobrancaDocumento.size());
				System.out.println("***************************************");
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}

			if (colecaoCobrancaDocumento != null
					&& !colecaoCobrancaDocumento.isEmpty()) {

				System.out.println("***************************************");
				System.out.println("QUANTIDADE COBRANÇA:"
						+ colecaoCobrancaDocumento.size());
				System.out.println("***************************************");

				if (colecaoCobrancaDocumento.size() < quantidadeCobrancaDocumento) {
					flagFimPesquisa = true;
				} else {
					quantidadeCobrancaDocumentoInicio = quantidadeCobrancaDocumentoInicio + 1000;
				}
				// ***********************************************************************
				// ****PARTE COMENTADA DA DIVISÃO PARA IMPRESSÃO DE DOCUMENTO
				// COBRANÇA****
				// ***********************************************************************

				// int metadeColecao = 0;
				// if (colecaoCobrancaDocumento.size() % 2 == 0) {
				// metadeColecao = colecaoCobrancaDocumento.size() / 2;
				// } else {
				// metadeColecao = (colecaoCobrancaDocumento.size() / 2) + 1;
				// }

				// Map<Integer, Map<Object, Object>>
				// mapCobrancaDocumentoOrdenada =
				// dividirColecao(colecaoCobrancaDocumento);

				/*
				 * if (mapCobrancaDocumentoOrdenada != null) { int countOrdem =
				 * 0;
				 * 
				 * while (countOrdem < mapCobrancaDocumentoOrdenada.size()) {
				 * Map<Object, Object> mapCobrancaoDocumentoDivididas =
				 * mapCobrancaDocumentoOrdenada .get(countOrdem);
				 */

				/*
				 * Iterator iteratorCobrancaDocumento =
				 * mapCobrancaoDocumentoDivididas .keySet().iterator();
				 */
				Iterator iteratorCobrancaDocumento = colecaoCobrancaDocumento
						.iterator();
				while (iteratorCobrancaDocumento.hasNext()) {

					CobrancaDocumento cobrancaDocumento = null;
					/*
					 * if(quantidadeContas == 48){ System.out.println(""); }
					 */

					// int situacao = 0;
					cobrancaDocumento = (CobrancaDocumento) iteratorCobrancaDocumento
							.next();

					String nomeClienteUsuario = null;
					Collection colecaoCobrancaDocumentoItemConta = null;
					Integer idClienteResponsavel = null;
					// Collection colecaoCobrancaDocumentoItemGuiaPagamento =
					// null;
					/*
					 * Estes objetos auxiliarão na formatação da inscrição que
					 * será composta por informações do documento de cobrança e
					 * do imóvel a ele associado
					 */

					/*
					 * Objeto que será utilizado para armazenar as informações
					 * do documento de cobrança de acordo com o layout definido
					 * no caso de uso
					 */

					/*
					 * while (situacao < 2) { if (situacao == 0) { situacao = 1;
					 * sequencialImpressao = atualizaSequencial(
					 * sequencialImpressao, situacao, metadeColecao); } else {
					 * cobrancaDocumento = (CobrancaDocumento)
					 * mapCobrancaoDocumentoDivididas .get(cobrancaDocumento);
					 * situacao = 2; sequencialImpressao = atualizaSequencial(
					 * sequencialImpressao, situacao, metadeColecao); }
					 */

					if (cobrancaDocumento != null) {
						sequencialImpressao++;

						try {

							nomeClienteUsuario = this.repositorioClienteImovel
									.pesquisarNomeClientePorImovel(cobrancaDocumento
											.getImovel().getId());
							idClienteResponsavel = this.repositorioClienteImovel
									.retornaIdClienteResponsavelIndicadorEnvioConta(cobrancaDocumento
											.getImovel().getId());

							colecaoCobrancaDocumentoItemConta = this.repositorioCobranca
									.selecionarCobrancaDocumentoItemReferenteConta(cobrancaDocumento);

						} catch (ErroRepositorioException ex) {
							ex.printStackTrace();
							throw new ControladorException("erro.sistema", ex);
						}

						if (colecaoCobrancaDocumentoItemConta != null
								&& !colecaoCobrancaDocumentoItemConta.isEmpty()) {

							// ITEM 1
							// sequencial do documento de cobranca
//							cobrancaDocumentoTxt
//									.append(Util
//											.retornaSequencialFormatado(cobrancaDocumento
//													.getNumeroSequenciaDocumento()));
							
							cobrancaDocumentoTxt.append(
								Util.adicionarZerosEsquedaNumero(9, 
									"" + cobrancaDocumento.getNumeroSequenciaDocumento()));
							
							// ITEM 2
							// Formatar sequencial de documento gerado
							cobrancaDocumentoTxt
									.append(Util
											.retornaSequencialFormatado(sequencialImpressao));
							// ITEM 3
							// id do grupo
							if (idCronogramaAtividadeAcaoCobranca != null) {
								cobrancaDocumentoTxt.append(Util
										.adicionarZerosEsquedaNumero(3, ""
												+ grupoCobranca.getId()));
							} else {
								cobrancaDocumentoTxt.append(Util
										.adicionarZerosEsquedaNumero(3, ""
												+ cobrancaDocumento.getImovel()
														.getQuadra().getRota()
														.getCobrancaGrupo()
														.getId()));
							}
							// Codigo Rota
							cobrancaDocumentoTxt.append(Util
									.adicionarZerosEsquedaNumero(6, ""
											+ cobrancaDocumento.getImovel()
													.getQuadra().getRota()
													.getCodigo()));

							// ITEM 4
							// Codigo Rota
							cobrancaDocumentoTxt
									.append(Util
											.adicionarZerosEsquedaNumero(
													6,
													""
															+ cobrancaDocumento
																	.getImovel()
																	.getNumeroSequencialRota()));

							// ITEM 5
							// código da firma
							if (cobrancaDocumento.getEmpresa() != null) {
								cobrancaDocumentoTxt.append(Util
										.adicionarZerosEsquedaNumero(2,
												cobrancaDocumento.getEmpresa()
														.getId().toString()));
							}

							// ITEM 6
							if (cobrancaDocumento.getEmpresa() != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(cobrancaDocumento
												.getEmpresa()
												.getDescricaoAbreviada(), 10));
							}

							// ITEM 7
							// Matrícula do imóvel
							cobrancaDocumentoTxt
									.append(Util
											.adicionarZerosEsquedaNumero(
													9,
													Util
															.retornaMatriculaImovelFormatada(cobrancaDocumento
																	.getImovel()
																	.getId())));
							// ITEM 8
							// Inscrição
							String idLocalidade = Util
									.adicionarZerosEsquedaNumero(3, ""
											+ cobrancaDocumento.getLocalidade()
													.getId());
							String codigoSetorComercial = Util
									.adicionarZerosEsquedaNumero(3, ""
											+ cobrancaDocumento
													.getCodigoSetorComercial());
							String numeroQuadra = Util
									.adicionarZerosEsquedaNumero(3, ""
											+ cobrancaDocumento
													.getNumeroQuadra());
							String lote = Util.adicionarZerosEsquedaNumero(4,
									""
											+ cobrancaDocumento.getImovel()
													.getLote());
							String subLote = Util.adicionarZerosEsquedaNumero(
									3, ""
											+ cobrancaDocumento.getImovel()
													.getSubLote());

							cobrancaDocumentoTxt.append(Util.completaString(
									idLocalidade + "." + codigoSetorComercial
											+ "." + numeroQuadra + "." + lote
											+ "." + subLote, 20));

							// ITEM 9,10
							String enderecoImovel = "";
							String nomeBairro = "";
							String nomeMunicipio = "";
							String siglaUnidadeFederecao = "";
							String cepFormatado = "";

							String[] parmsEnderecoImovel = getControladorEndereco()
									.pesquisarEnderecoFormatadoDividido(
											cobrancaDocumento.getImovel()
													.getId());
							if (parmsEnderecoImovel != null) {
								// endereço sem municipio e unidade federação
								cobrancaDocumentoTxt.append(Util
										.completaString(parmsEnderecoImovel[0],
												100));
								enderecoImovel = parmsEnderecoImovel[0];
								// nome do bairro
								nomeBairro = "" + parmsEnderecoImovel[3];
								// nome do municipio
								nomeMunicipio = "" + parmsEnderecoImovel[1];
								// sigla da unidade federação
								siglaUnidadeFederecao = parmsEnderecoImovel[2];
								cepFormatado = parmsEnderecoImovel[4];
							}

							// nome Bairro
							cobrancaDocumentoTxt.append(Util.completaString(
									nomeBairro, 30));
							// nome municipio
							cobrancaDocumentoTxt.append(Util.completaString(
									nomeMunicipio, 30));
							// sigla unidade federacao
							cobrancaDocumentoTxt.append(Util.completaString(
									siglaUnidadeFederecao, 2));

							if (cepFormatado != null) {
								cepFormatado = Util
										.adicionarZerosEsquedaNumero(8,
												cepFormatado);

								cobrancaDocumentoTxt.append(cepFormatado
										.substring(0, 5)
										+ "-" + cepFormatado.substring(5, 8));
							}

							// ITEM 11,12
							// endereço do cliente com opção de recebimento via
							// correio
							// ITEM 9,10
							String nomeBairroResponsavel = "";
							String nomeMunicipioResponsavel = "";
							String siglaUnidadeFederecaoResponsavel = "";
							String cepFormatadoResponsavel = "";
							if (idClienteResponsavel != null) {
								String[] parmsEndereco = getControladorEndereco()
										.pesquisarEnderecoClienteAbreviadoDividido(
												idClienteResponsavel);
								// endereço sem municipio e unidade federação
								cobrancaDocumentoTxt.append(Util
										.completaString(parmsEndereco[0], 100));
								// nome do bairro
								nomeBairroResponsavel = "" + parmsEndereco[3];
								// nome do municipio
								nomeMunicipioResponsavel = ""
										+ parmsEndereco[1];
								// sigla da unidade federação
								siglaUnidadeFederecaoResponsavel = parmsEndereco[2];
								cepFormatadoResponsavel = parmsEndereco[4];

								// nome Bairro
								cobrancaDocumentoTxt.append(Util
										.completaString(nomeBairroResponsavel,
												30));
								// nome municipio
								cobrancaDocumentoTxt.append(Util
										.completaString(
												nomeMunicipioResponsavel, 30));
								// sigla unidade federacao
								cobrancaDocumentoTxt
										.append(Util
												.completaString(
														siglaUnidadeFederecaoResponsavel,
														2));

								if (cepFormatadoResponsavel != null) {
									cepFormatadoResponsavel = Util
											.adicionarZerosEsquedaNumero(8,
													cepFormatadoResponsavel);

									cobrancaDocumentoTxt
											.append(cepFormatadoResponsavel
													.substring(0, 5)
													+ "-"
													+ cepFormatado.substring(5,
															8));
								}

							} else {
								// endereço sem municipio e unidade federação
								cobrancaDocumentoTxt.append(Util
										.completaString(enderecoImovel, 100));

								// nome Bairro
								cobrancaDocumentoTxt.append(Util
										.completaString(nomeBairro, 30));
								// nome municipio
								cobrancaDocumentoTxt.append(Util
										.completaString(nomeMunicipio, 30));
								// sigla unidade federacao
								cobrancaDocumentoTxt.append(Util
										.completaString(siglaUnidadeFederecao,
												2));

								if (cepFormatado != null) {
									cepFormatado = Util
											.adicionarZerosEsquedaNumero(8,
													cepFormatado);

									cobrancaDocumentoTxt.append(cepFormatado
											.substring(0, 5)
											+ "-"
											+ cepFormatado.substring(5, 8));
								}

							}

							// ITEM 13
							// nome cliente
							cobrancaDocumentoTxt.append(Util.completaString(
									nomeClienteUsuario, 50));

							// ITEM 14
							// Quant. contas em debito
							cobrancaDocumentoTxt.append(Util
									.adicionarZerosEsquedaNumero(3, ""
											+ colecaoCobrancaDocumentoItemConta
													.size()));

							// ITEM 15,18
							// Indicador Estouro
							// cobrancaDocumentoTxt.append(Util.completaString(""
							// + indicadorEstouro, 1));
							// em caso de ser carta de tarifa social não
							// formatar o txt

							int quantidadesContas = 60;

							// retorna o indicador de estouro e formata o
							// cobrançaDocumentoTxt com os dados
							Object[] dadosValores = formatarCobrancaDocumentoItemParaContaComFormatacao(
									cobrancaDocumentoTxt,
									colecaoCobrancaDocumentoItemConta,
									quantidadesContas, idAcaoCobranca);

							BigDecimal valorItemCobrado = (BigDecimal) dadosValores[0];
							BigDecimal valorAcrescimos = (BigDecimal) dadosValores[1];
							BigDecimal valorItemAcrescimos = (BigDecimal) dadosValores[2];

							// somatorio do valor do item da conta
							cobrancaDocumentoTxt.append(Util.completaString(
									Util.formataBigDecimal(valorItemCobrado, 2,
											true), 14));
							// somatorio do valor dos encargos
							cobrancaDocumentoTxt.append(Util.completaString(
									Util.formataBigDecimal(valorAcrescimos, 2,
											true), 14));
							// somatorio do valor total das contas
							cobrancaDocumentoTxt.append(Util.completaString(
									Util.formataBigDecimal(valorItemAcrescimos,
											2, true), 14));

							// String
							// quantidadeItensDocumentoGuiaPagamentoString =
							// null;
							// // em caso de ser carta de tarifa social não
							// // formatar o txt
							// if (idAcaoCobranca != null
							// && (!idAcaoCobranca
							// .equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_LIGADO)
							// && !idAcaoCobranca
							// .equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_CORTADO)))
							// {
							// // retorna o quantidade de documento item com
							// // guia
							// // pagamento e formata o cobrançaDocumentoTxt
							// // com os
							// // dados
							// int quantidadeItensDocumentoGuiaPagamento =
							// somatorioValoresAcrescimosDocumentoItem(
							// cobrancaDocumentoTxt,
							// colecaoCobrancaDocumentoItemGuiaPagamento);
							// quantidadeItensDocumentoGuiaPagamentoString = ""
							// + quantidadeItensDocumentoGuiaPagamento;
							// }

							// ITEM 19
							// em caso de ser carta de tarifa social não
							// formatar o txt
							// Sigla da regional
							cobrancaDocumentoTxt.append(Util.completaString(""
									+ cobrancaDocumento.getImovel()
											.getLocalidade()
											.getGerenciaRegional()
											.getNomeAbreviado(), 3));

							// ITEM 20
							// Nome da Localidade
							cobrancaDocumentoTxt.append(Util
									.completaString(""
											+ cobrancaDocumento.getImovel()
													.getLocalidade()
													.getDescricao(), 25));
							// em caso de ser carta de tarifa social não
							// formatar o txt

							// ITEM 21
							cobrancaDocumentoTxt.append(Util
									.formatarData(cobrancaDocumento
											.getEmissao()));

							// data de vencimento AAAAMMDD
							// Object[] dadosFaturamentoGrupo =
							// getControladorFaturamento()
							// .pesquisarAnoMesEDiaVencimentoFaturamentoGrupo(
							// cobrancaDocumento.getImovel()
							// .getId());
							// Integer anoMesFaturamento = null;
							// Integer diaVencimento = null;
							// if (dadosFaturamentoGrupo != null) {
							// if (dadosFaturamentoGrupo[0] != null) {
							// anoMesFaturamento = (Integer)
							// dadosFaturamentoGrupo[0];
							// }
							// if (dadosFaturamentoGrupo[1] != null) {
							// diaVencimento = ((Short)
							// dadosFaturamentoGrupo[1])
							// .intValue();
							// }
							// }

							// ITEM 22
							String dataVencimento = "";
							if (cobrancaDocumento.getEmissao() != null
									&& acaoCobranca.getNumeroDiasValidade() != null) {
								dataVencimento = Util
										.formatarData(Util
												.adicionarNumeroDiasDeUmaData(
														cobrancaDocumento
																.getEmissao(),
														acaoCobranca
																.getNumeroDiasVencimento()));
							}

							cobrancaDocumentoTxt.append(Util
									.completaStringComEspacoAEsquerda(
											dataVencimento, 10));

							// ITEM 23
							if (cobrancaDocumento.getImovel() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoAgua() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico() != null) {
								// numero do hidometro
								if (cobrancaDocumento.getImovel()
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() != null) {
									cobrancaDocumentoTxt
											.append(Util
													.completaString(
															""
																	+ cobrancaDocumento
																			.getImovel()
																			.getLigacaoAgua()
																			.getHidrometroInstalacaoHistorico()
																			.getHidrometro()
																			.getNumero(),
															10));

									// Local de instalação descricao abreviada
									cobrancaDocumentoTxt
											.append(Util
													.completaString(
															""
																	+ cobrancaDocumento
																			.getImovel()
																			.getLigacaoAgua()
																			.getHidrometroInstalacaoHistorico()
																			.getHidrometroLocalInstalacao()
																			.getDescricaoAbreviada(),
															5));
								} else {
									cobrancaDocumentoTxt.append(Util
											.completaString("", 10));
									cobrancaDocumentoTxt.append(Util
											.completaString("", 5));
								}

							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 10));
								cobrancaDocumentoTxt.append(Util
										.completaString("", 5));
							}

							// ITEM 24,25,26
							String representacaoNumericaCodBarra = "";

							// Obtém a representação numérica do
							// códigode
							// barra
							representacaoNumericaCodBarra = this
									.getControladorArrecadacao()
									.obterRepresentacaoNumericaCodigoBarra(
											5,
											cobrancaDocumento
													.getValorDocumento(),
											cobrancaDocumento.getLocalidade()
													.getId(),
											cobrancaDocumento.getImovel()
													.getId(),
											null,
											null,
											null,
											null,
											String
													.valueOf(cobrancaDocumento
															.getNumeroSequenciaDocumento()),
											cobrancaDocumento
													.getDocumentoTipo().getId(),
											null, null, null);

							// Formata a representação númerica do
							// código de
							// barras
							String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
									.substring(0, 11)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											11, 12)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											12, 23)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											23, 24)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											24, 35)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											35, 36)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											36, 47)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											47, 48);

							cobrancaDocumentoTxt
									.append(representacaoNumericaCodBarraFormatada);

							// Cria o objeto para gerar o código de
							// barras
							// no
							// padrão
							// intercalado 2 de 5
							Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();

							// Recupera a representação númerica do
							// código
							// de
							// barras
							// sem
							// os dígitos verificadores
							String representacaoCodigoBarrasSemDigitoVerificador = representacaoNumericaCodBarra
									.substring(0, 11)
									+ representacaoNumericaCodBarra.substring(
											12, 23)
									+ representacaoNumericaCodBarra.substring(
											24, 35)
									+ representacaoNumericaCodBarra.substring(
											36, 47);

							cobrancaDocumentoTxt
									.append(codigoBarraIntercalado2de5
											.encodeValue(representacaoCodigoBarrasSemDigitoVerificador));

							Object[] dadosOS = pesquisarDadosOrdemServicoDocumentoCobranca(cobrancaDocumento
									.getId());
							if (dadosOS != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString("" + dadosOS[0], 9));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 9));
							}
							// situação ligação de agua
							if (cobrancaDocumento.getImovel() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoAguaSituacao() != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(cobrancaDocumento
												.getImovel()
												.getLigacaoAguaSituacao()
												.getDescricao(), 20));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 20));
							}

							// situação ligação de esgoto
							if (cobrancaDocumento.getImovel() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoEsgotoSituacao() != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(cobrancaDocumento
												.getImovel()
												.getLigacaoEsgotoSituacao()
												.getDescricao(), 20));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 20));
							}

							Categoria categoria = getControladorImovel()
									.obterPrincipalCategoriaImovel(
											cobrancaDocumento.getImovel()
													.getId());
							if (categoria != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(categoria
												.getDescricao(), 15));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 15));
							}
							
							
							/*
							 * COLOCADO POR RAPHAEL ROSSITER EM 03/01/2007 =============================================
							 * -----------------------------------------------------------------------------------------
							 */ 
							
							//ITEM 31 - Consumo Médio
							Integer consumoMedio = getControladorMicromedicao().pesquisarConsumoMedioImovel(
							cobrancaDocumento.getImovel().getId());

							if (consumoMedio != null) {
								cobrancaDocumentoTxt.append(Util
								.completaString("" + consumoMedio, 10));
							} else {
								cobrancaDocumentoTxt.append(Util
								.completaString("", 10));
							}

							
							//ITEM 32 - Consumo Fixo
							Integer consumoMinimoEsgoto = getControladorLigacaoEsgoto().recuperarConsumoMinimoEsgoto(
							cobrancaDocumento.getImovel().getId());

							if (consumoMinimoEsgoto != null) {
								cobrancaDocumentoTxt.append(Util
								.completaString("" + consumoMinimoEsgoto, 10));
							} else {
								cobrancaDocumentoTxt.append(Util
								.completaString("", 10));
							}
							
							
							// Categoria(s) e Economia(s)
							Collection colecaoCategorias = getControladorImovel()
							.obterQuantidadeEconomiasCategoria(cobrancaDocumento.getImovel());
							
							String qtdResidencial = "";
							String qtdComercial = "";
							String qtdIndustrial = "";
							String qtdPublico = "";

							Integer totalCategoria = 0;

							if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
								
								Iterator iteratorColecaoCategorias = colecaoCategorias
								.iterator();
								
								while (iteratorColecaoCategorias.hasNext()) {
									
									categoria = (Categoria) iteratorColecaoCategorias.next();

									if (categoria.getId().equals(Categoria.RESIDENCIAL)) {
										
										qtdResidencial = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
										
									} else if (categoria.getId().equals(Categoria.COMERCIAL)) {
										
										qtdComercial = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
										
									} else if (categoria.getId().equals(Categoria.INDUSTRIAL)) {
										
										qtdIndustrial = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
									
									} else if (categoria.getId().equals(Categoria.PUBLICO)) {
										
										qtdPublico = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
									}
								}
							}
							
							//ITEM 33 - Residêncial
							if (!qtdResidencial.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdResidencial));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}

							
							//ITEM 34 - Comercial
							if (!qtdComercial.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdComercial));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}
							
							
							//ITEM 35 - Industrial
							if (!qtdIndustrial.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdIndustrial));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}
							
							
							//ITEM 36 - Público
							if (!qtdPublico.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdPublico));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}
							
							
							//ITEM 37 - Soma Total das economias
							if (totalCategoria != null && !totalCategoria.equals("")) {
								
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(4, ""
								+ totalCategoria));
							} else {
								cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda("", 4));
							}
							
							
							//ITEM 38 - Data da Posição do Débito
							SistemaParametro sistemaParametro = this.getControladorUtil()
							.pesquisarParametrosDoSistema();
							
							String anoMesValidade = sistemaParametro.getAnoMesArrecadacao().toString();
					
							Calendar calendario = new GregorianCalendar();

							if (anoMesValidade != null && !anoMesValidade.equals("")) {
						
								calendario.set(Calendar.YEAR, new Integer(
								anoMesValidade.substring(0, 4)).intValue());
						
								calendario.set(Calendar.MONTH, new Integer(
								anoMesValidade.substring(4, 6)).intValue() - 1);
						
								calendario.set(Calendar.DAY_OF_MONTH,
								calendario.getActualMaximum(Calendar.DAY_OF_MONTH));

								cobrancaDocumentoTxt.append(Util
								.formatarData(calendario.getTime()));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 10));
							}
							
							
							/*
							 * As datas de corte e supressão serão repassadas de acordo com
							 * a situação da ligação do imóvel.
							 * 
							 * ITEM 39 - Data do Corte
							 * ITEM 40 - Data da Supressão
							 */
							if (cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
								.equals(LigacaoAguaSituacao.CORTADO) ||
								cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
								.equals(LigacaoAguaSituacao.SUPRIMIDO)){
							
								/*
								 * Dados da Ligação de Água(a partir da tabela LIGACAO_AGUA
								 * lagu_id=imov_id da tabela IMOVEL)
								 */
								Object[] dadosLigacaoAgua = getControladorAtendimentoPublico()
								.pesquisarDadosLigacaoAgua(cobrancaDocumento.getImovel().getId());
								
								if (dadosLigacaoAgua != null) {
									
									//Data do Corte
									if (cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
										.equals(LigacaoAguaSituacao.CORTADO)){
										
										if (dadosLigacaoAgua[3] != null) {
											
											cobrancaDocumentoTxt.append(Util.completaString(
											Util.formatarData((Date) dadosLigacaoAgua[3]),10));
											
											cobrancaDocumentoTxt.append(Util.completaString("", 10));
										} 
										else {
											cobrancaDocumentoTxt.append(Util.completaString("", 20));
										}
									}
									
									//Data da Supressão
									else if (cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
											.equals(LigacaoAguaSituacao.SUPRIMIDO)){
										

										if (dadosLigacaoAgua[4] != null) {
									
											cobrancaDocumentoTxt.append(Util.completaString("", 10));
											
											cobrancaDocumentoTxt.append(Util.completaString(
											Util.formatarData((Date) dadosLigacaoAgua[4]),10));
											
										} 
										else {
											cobrancaDocumentoTxt.append(Util.completaString("", 20));
										}
									}
									else{
										
										cobrancaDocumentoTxt.append(Util.completaString("", 20));
									}
									
								} else {
									cobrancaDocumentoTxt.append(Util.completaString("", 20));
								}
							}
							else{
								cobrancaDocumentoTxt.append(Util.completaString("", 20));
							}
							
							//ITEM 41 - Origem
							LeituraAnormalidade leituraAnormalidade = cobrancaDocumento.getImovel()
							.getLeituraAnormalidade();
							
							if (leituraAnormalidade == null) {
								cobrancaDocumentoTxt.append("AUTOMATICO");
								cobrancaDocumentoTxt.append(Util.completaString("", 5));
							} 
							else if (leituraAnormalidade.getId().equals(LeituraAnormalidade.INDICADOR_LIGADO_CLANDESTINO_AGUA) 
									|| leituraAnormalidade.getId().equals(LeituraAnormalidade.INDICADOR_LIGADO_CLANDESTINO_ESGOTO)
									|| leituraAnormalidade.getId().equals(LeituraAnormalidade.INDICADOR_LIGADO_CLANDESTINO_AGUA_ESGOTO)) {
								
								cobrancaDocumentoTxt.append("RECADASTRAMENTO");
							} 
							else {
								cobrancaDocumentoTxt.append("LEITURA");
								cobrancaDocumentoTxt.append(Util.completaString("", 8));
							}

							//ITEM 42 - Ocorrência
							if (leituraAnormalidade != null){
								
								cobrancaDocumentoTxt.append(Util.completaString(
								leituraAnormalidade.getDescricao(), 34));
							}
							else{
								
								cobrancaDocumentoTxt.append(Util.completaString("", 34));
							}
							
							//ITEM 43 - Data Última Alteração
							if (cobrancaDocumento.getImovel().getUltimaAlteracao() != null) {
								
								cobrancaDocumentoTxt.append(Util.formatarData(
								cobrancaDocumento.getImovel().getUltimaAlteracao()));
								
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 10));
							}
							
							//ITEM 44 - Ordem de Serviço
//							Integer idOrdemServico = this.getControladorOrdemServico()
//							.pesquisarOrdemServicoPorCobrancaDocumento(cobrancaDocumento.getId());
//							
//							if (idOrdemServico != null){
//								
//								cobrancaDocumentoTxt.append(Util.completaString(
//								idOrdemServico.toString(), 15));
//							}
//							else{
//								cobrancaDocumentoTxt.append(Util.completaString("", 15));
//							}
							
							//ITEM 45 - Tipo de Consumidor (ImovelPerfil da tabela CobrancaDocumento)
							if (cobrancaDocumento.getImovelPerfil() != null) {
								
								cobrancaDocumentoTxt.append(Util.completaString(
								cobrancaDocumento.getImovelPerfil().getDescricao() , 20));
								
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 20));
							}
							
							//ITEM 46 - Área do Imóvel
							if (cobrancaDocumento.getImovel().getAreaConstruida() != null) {
								cobrancaDocumentoTxt.append(Util.completaString(
								Util.formatarMoedaReal(cobrancaDocumento.getImovel().getAreaConstruida()) , 9));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 9));
							}
							
							//ITEM 47 - Código da Unidade de Negócio / ITEM 48 - CNPJ da Unidade de Negócio 
							if (cobrancaDocumento.getLocalidade() != null && cobrancaDocumento.getLocalidade().getUnidadeNegocio() != null) {
								cobrancaDocumentoTxt.append(Util.completaString(cobrancaDocumento.getLocalidade().getUnidadeNegocio().getId().toString(), 3));
								if (cobrancaDocumento.getLocalidade().getUnidadeNegocio().getCnpj() != null) {
									cobrancaDocumentoTxt.append(Util.completaString(cobrancaDocumento.getLocalidade().getUnidadeNegocio().getCnpjFormatado(), 18));
								} else {
									cobrancaDocumentoTxt.append(Util.completaString("", 18));
								}
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 17));
							}
							
							if (cobrancaDocumento.getLocalidade() != null) {
								cobrancaDocumentoTxt.append(Util.completaString(cobrancaDocumento.getLocalidade().getEnderecoFormatadoTituloAbreviado(), 120));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 120));
							}
							
							
							//ITEM 46 - Hidrômetro
							/*Collection dadosHidrometro = null;
							
							try {

								dadosHidrometro = this.repositorioMicromedicao
								.pesquisarDadosHidrometroTipoLigacaoAgua(cobrancaDocumento.getImovel().getId());
								
							} catch (ErroRepositorioException ex) {
								ex.printStackTrace();
								throw new ControladorException("erro.sistema", ex);
							}
							
							if (dadosHidrometro != null && !dadosHidrometro.isEmpty()){
								
								Object[] objetoDados = (Object[]) Util.retonarObjetoDeColecao(dadosHidrometro);
								String numeroHidrometro = String.valueOf(objetoDados[1]);
								
								if (numeroHidrometro != null && !numeroHidrometro.equalsIgnoreCase("")){
									
									cobrancaDocumentoTxt.append(Util.completaString(numeroHidrometro, 10));
								}
								else{
									
									cobrancaDocumentoTxt.append(Util.completaString("", 10));
								}
							}
							else{
								
								cobrancaDocumentoTxt.append(Util.completaString("", 10));
							}*/
				
							//==========================================================================================

							
							cobrancaDocumentoTxt.append(System
									.getProperty("line.separator"));

						}

						colecaoCobrancaDocumentoItemConta = null;
					}

					// }// fim do laço que verifica
					// as 2
					// contas

				}// fim laço while do iterator do
				// objeto
				// helper
				// countOrdem++;
				// mapCobrancaoDocumentoDivididas = null;
				// // }
			} else {
				flagFimPesquisa = true;
			}
			// } else {
			// flagFimPesquisa = true;
			// }
			// colecaoCobrancaDocumento = null;
		}

		Date dataAtual = new Date();

		String nomeZip = null;

		System.out.println("ID AÇÃO COBRANÇA:" + idAcaoCobranca);

		if (idCronogramaAtividadeAcaoCobranca != null) {
			nomeZip = "EMITIR_" + acaoCobranca.getDescricaoCobrancaAcao()
					+ "_GRUPO_" + grupoCobranca.getId() + "_"
					+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
		} else {
			String descricaoAbrevDocumentoTipo = "";
			if (acaoCobranca != null && acaoCobranca.getDocumentoTipo() != null) {
				descricaoAbrevDocumentoTipo = acaoCobranca.getDocumentoTipo()
						.getDescricaoAbreviado();
			}
			String tituloComandoEventual = cobrancaAcaoAtividadeComando
					.getDescricaoTitulo();

			nomeZip = descricaoAbrevDocumentoTipo + " " + tituloComandoEventual
					+ " " + Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
		}
		nomeZip = nomeZip.replace("/", "_");
		nomeZip = nomeZip.replace(" ", "_");

		// pegar o arquivo, zipar pasta e arquivo e escrever no stream
		try {

			System.out.println("***************************************");
			System.out.println("INICO DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

			if (cobrancaDocumentoTxt != null
					&& cobrancaDocumentoTxt.length() != 0) {

				// criar o arquivo zip
				File compactado = new File(nomeZip + ".zip"); // nomeZip
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
						compactado));

				File leitura = new File(nomeZip + ".txt");
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(cobrancaDocumentoTxt.toString());
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			}
			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			e.printStackTrace();

			throw new ControladorException("erro.sistema", e);
		}

	}
}
