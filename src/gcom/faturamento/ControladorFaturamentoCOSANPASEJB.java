package gcom.faturamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.IContaCategoria;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.micromedicao.FiltroLeituraSituacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.exception.BaseRuntimeException;
import gcom.util.exception.EmitirContaException;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;

import org.jboss.logging.Logger;

public class ControladorFaturamentoCOSANPASEJB extends ControladorFaturamento
		implements SessionBean {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ControladorFaturamentoCOSANPASEJB.class);


	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void emitirContas(Integer anoMesReferenciaFaturamento, FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada, int tipoConta,
			Integer idEmpresa, Short indicadorEmissaoExtratoFaturamento) throws ControladorException {

		int idUnidadeIniciada = 0;

		Integer anoMesReferenciaFaturamentoSemAntecipacao = new Integer(anoMesReferenciaFaturamento);
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE,
				(idEmpresa == null ? 0 : idEmpresa));
		ImpressaoContaImpressoraTermica impressaoContaImpressoraTermica = null;
		Collection<Object[]> stringFormatadaImpressaoTermica = new ArrayList<Object[]>();
		Collection<String> colecaoLocalidadesArquivo = new ArrayList<String>();
		int qntArquivoLocalidadeImpressaoTermica = 1;
		int qtdImovelArquivoImpressaoTermica = 0;
		int qtdContasLocalidade = 0;

		try {
			SistemaParametro sistemaParametro = null;

			int quantidadeContas = 0;

			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;
			int numeroIndiceAntecipado = 0;

			sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			boolean ehFaturamentoAntecipado = false;

			Integer anoMesReferenciaFaturamentoAntecipado = null;
			if (Util.obterMes(anoMesReferenciaFaturamento) == 11) {
				if (sistemaParametro.getIndicadorFaturamentoAntecipado().equals(ConstantesSistema.SIM)) {

					ehFaturamentoAntecipado = true;
					anoMesReferenciaFaturamentoAntecipado = Util.somarData(anoMesReferenciaFaturamento);
				}
			}

			StringBuilder contasTxtLista = null;
			Map<Integer, Integer> mapAtualizaSequencial = null;

			boolean flagTerminou = false;
			numeroIndice = 0;
			numeroIndiceAntecipado = 0;
			Integer sequencialImpressao = 0;

			Collection<EmitirContaHelper> colecaoConta = null;
			int cont = 1;

			contasTxtLista = new StringBuilder();

			while (!flagTerminou) {
				mapAtualizaSequencial = new HashMap();
				Collection colecaoContaParms = null;

				if (anoMesReferenciaFaturamentoAntecipado != null && anoMesReferenciaFaturamento.intValue() == anoMesReferenciaFaturamentoAntecipado.intValue()) {
					logger.info("INDICE_ANTECIPADO_PESQUISA:" + numeroIndiceAntecipado);
					numeroIndice = numeroIndiceAntecipado;
				}

				this.alterarVencimentoContasFaturarGrupo(ContaTipo.CONTA_NORMAL, idEmpresa, numeroIndice, faturamentoGrupo);
				
				colecaoContaParms = repositorioFaturamento.pesquisarContasEmitirCOSANPA(ContaTipo.CONTA_NORMAL, idEmpresa, numeroIndice,
						anoMesReferenciaFaturamento, faturamentoGrupo.getId());
				colecaoConta = formatarEmitirContasHelper(colecaoContaParms, ContaTipo.CONTA_NORMAL);

				if (colecaoConta != null && !colecaoConta.isEmpty()) {

					if (colecaoConta.size() < quantidadeRegistros) {
						flagTerminou = true;
					}

					EmitirContaHelper emitirContaHelper = null;
					Iterator<EmitirContaHelper> iteratorConta = colecaoConta.iterator();

					while (iteratorConta.hasNext()) {

						emitirContaHelper = (EmitirContaHelper) iteratorConta.next();
						
						sequencialImpressao += 1;

						quantidadeContas++;

						try {
							StringBuilder contaTxt = new StringBuilder();

							if (emitirContaHelper != null) {
								Localidade localidade = obterLocalidade(emitirContaHelper);
								Imovel imovelEmitido = getControladorImovel().pesquisarImovel(emitirContaHelper.getIdImovel());

								contaTxt.append(Util.completaString(obterNumeroNota(emitirContaHelper), 16));
								contaTxt.append(Util.completaString(Util.formatarData(new Date()), 10));
								contaTxt.append(Util.completaString(localidade.getEnderecoFormatadoTituloAbreviado(), 120));
								contaTxt.append(Util.completaString(localidade.getFone(), 9));
								contaTxt.append(Util.completaString("06.274.757/0001-50", 18));
								contaTxt.append(Util.completaString("12.050.537-1", 12));
								contaTxt = preencherUnidadeNegocio(contaTxt, localidade);
								contaTxt.append(Util.adicionarZerosEsquedaNumero(4, emitirContaHelper.getCodigoSetorComercialConta().toString()));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(3, emitirContaHelper.getIdLocalidade().toString()));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(4, new Integer(imovelEmitido.getQuadra().getNumeroQuadra()).toString()));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(9, imovelEmitido.getNumeroSequencialRota().toString()));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(1, imovelEmitido.getIndicadorDebitoConta().toString()));
								contaTxt.append(Util.completaString(emitirContaHelper.getIdImovel().toString(), 9));
								contaTxt = preencherNomeCliente(tipoConta, emitirContaHelper, contaTxt);

								Collection colecaoClienteImovel = repositorioClienteImovel.pesquisarClienteImovelResponsavelConta(emitirContaHelper.getIdImovel());

								String endereco = "";
								String municipioEntrega = "";
								String bairroEntrega = "";
								String cepEntrega = "";
								String ufEntrega = "";
								String logCepClie = "";
								String logBairroClie = "";
								boolean enderecoAlternativo = false;

								if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
									ClienteImovel clienteImovelRespConta = (ClienteImovel) colecaoClienteImovel.iterator().next();

									if (clienteImovelRespConta != null) {
										Cliente cliente = clienteImovelRespConta.getCliente();

										if (cliente != null && imovelEmitido.getImovelContaEnvio().getId().equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)) {
											String[] enderecoCliente = getControladorEndereco().pesquisarEnderecoClienteAbreviadoDivididoCosanpa(cliente.getId());
											bairroEntrega = enderecoCliente[3];
											municipioEntrega = enderecoCliente[1];
											ufEntrega = enderecoCliente[2];
											cepEntrega = enderecoCliente[4];
											logCepClie = enderecoCliente[5];
											logBairroClie = enderecoCliente[6];
											enderecoAlternativo = true;

											endereco = enderecoCliente[0] + " - " + bairroEntrega + " " + municipioEntrega + " " + ufEntrega + " " + cepEntrega;

										}
									}
								}

								String[] enderecoImovel2 = getControladorEndereco().pesquisarEnderecoFormatadoDivididoCosanpa(emitirContaHelper.getIdImovel());

								String municipioImovel = enderecoImovel2[1];
								String logCepImovel = "";
								String logBairroImovel = "";

								logCepImovel = enderecoImovel2[5];
								logBairroImovel = enderecoImovel2[6];

								if (municipioEntrega.equalsIgnoreCase("")) {
									municipioEntrega = municipioImovel;
								}

								if (logCepClie.trim().equalsIgnoreCase("")) {
									logCepClie = logCepImovel;
								}

								if (logBairroClie.trim().equalsIgnoreCase("")) {
									logBairroClie = logBairroImovel;
								}

								if (endereco == null || endereco.trim().equalsIgnoreCase("") || endereco.trim().equalsIgnoreCase("-")) {
									endereco = getControladorEndereco().pesquisarEnderecoFormatado(imovelEmitido.getId());
								}

								contaTxt.append(Util.completaString(endereco, 120));

								Categoria categoriaImovel = (Categoria) getControladorImovel().obterPrincipalCategoriaImovel(emitirContaHelper.getIdImovel());
								contaTxt.append(Util.completaString(categoriaImovel.getDescricao(), 30));

								contaTxt = preencherSubcategoriaImovel(contaTxt, imovelEmitido);
								contaTxt = preencherQuantidadeEconomias(contaTxt, imovelEmitido);

								String dataVencimentoConta = Util.formatarData(emitirContaHelper.getDataVencimentoConta());
								contaTxt.append(Util.completaString(dataVencimentoConta, 10));

								contaTxt = preencherNumeroHidrometro(contaTxt, imovelEmitido);

								Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
								Integer tipoLigacao = parmSituacao[0];
								Integer tipoMedicao = parmSituacao[1];

								Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);

								String leituraAnterior = "0";
								String leituraAtual = "0";
								String dataLeituraAnterior = "";
								String dataLeituraAtual = "";
								String idSituacaoLeituraAtual = "0";

								Integer idLeiturista = null;

								if (parmsMedicaoHistorico != null) {

									if (parmsMedicaoHistorico[0] != null) {
										leituraAnterior = "" + (Integer) parmsMedicaoHistorico[0];
									}

									if (parmsMedicaoHistorico[1] != null) {
										leituraAtual = "" + (Integer) parmsMedicaoHistorico[1];
									}

									if (parmsMedicaoHistorico[3] != null) {
										dataLeituraAnterior = Util.formatarData((Date) parmsMedicaoHistorico[3]);
									}

									if (parmsMedicaoHistorico[2] != null) {
										dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
									}
									if (parmsMedicaoHistorico[4] != null) {
										idSituacaoLeituraAtual = "" + (Integer) parmsMedicaoHistorico[4];
									}
									if (parmsMedicaoHistorico[8] != null) {
										idLeiturista = (Integer) parmsMedicaoHistorico[8];
									}
								}
								Object[] parmsConsumoHistorico = null;
								String consumoMedio = "0";
								String mensagemContaAnormalidade = "";
								if (tipoLigacao != null) {
									parmsConsumoHistorico = repositorioMicromedicao.obterDadosConsumoConta(emitirContaHelper.getIdImovel(),
											emitirContaHelper.getAmReferencia(), tipoLigacao);

									if (parmsConsumoHistorico != null) {
										if (parmsConsumoHistorico[2] != null) {
											consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
										}

										if (parmsConsumoHistorico[6] != null) {
											mensagemContaAnormalidade = (String) parmsConsumoHistorico[6];
										}
									}
								}

								contaTxt.append(Util.completaString(dataLeituraAnterior, 10));
								contaTxt.append(Util.completaString(dataLeituraAtual, 10));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(6, leituraAnterior));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(6, leituraAtual));
								contaTxt = preencherConsumoMedido(contaTxt, leituraAnterior, leituraAtual);

								Collection colecaoContaCategoriaConsumoFaixa = null;

								colecaoContaCategoriaConsumoFaixa = repositorioFaturamento.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper.getIdConta());

								Collection colecaoSubCategoria = getControladorImovel().obterQuantidadeEconomiasSubCategoria(imovelEmitido.getId());

								Integer consumoExcesso = 0;
								Integer consumoMinimo = 0;
								BigDecimal valorExcesso = new BigDecimal("0.0");
								BigDecimal valorMinimo = new BigDecimal("0.0");

								StringBuilder dadosContaCategoria = null;

								Collection<IContaCategoria> cContaCategoria = repositorioFaturamento.pesquisarContaCategoria(emitirContaHelper.getIdConta());
								
								// Caso tenha mais de uma categoria (misto)
								if (cContaCategoria.size() > 1) {
									dadosContaCategoria = obterDadosContaCategoriaMisto(cContaCategoria);
								} else {

									if (colecaoContaCategoriaConsumoFaixa == null || colecaoContaCategoriaConsumoFaixa.isEmpty()) {

										ContaCategoria contaCategoria = (ContaCategoria) cContaCategoria.iterator().next();

										consumoMinimo = contaCategoria.getConsumoMinimoAgua();

										if (consumoMinimo == null || consumoMinimo == 0) {
											consumoMinimo = contaCategoria.getConsumoMinimoEsgoto();
										}

										valorMinimo = emitirContaHelper.getValorAgua();

									} else {
										if (!emitirContaHelper.getConsumoAgua().equals(0)) {
											for (Iterator iter = colecaoContaCategoriaConsumoFaixa.iterator(); iter.hasNext();) {

												ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iter.next();
												if (contaCategoriaConsumoFaixa.getConsumoAgua() != null) {
													for (Iterator iteration = colecaoSubCategoria.iterator(); iteration.hasNext();) {
														Subcategoria subCategoriaEmitir = (Subcategoria) iteration.next();

														Integer fatorEconomias = null;
														if (subCategoriaEmitir.getCategoria() != null) {
															if (subCategoriaEmitir.getCategoria().getFatorEconomias() != null
																	&& !subCategoriaEmitir.getCategoria().getFatorEconomias().equals("")) {

																fatorEconomias = subCategoriaEmitir.getCategoria().getFatorEconomias().intValue();
															}
														}

														if (contaCategoriaConsumoFaixa.getSubcategoria().getId().equals(subCategoriaEmitir.getId())) {
															if (fatorEconomias != null && !fatorEconomias.equals("")) {
																consumoExcesso = consumoExcesso + contaCategoriaConsumoFaixa.getConsumoAgua() * fatorEconomias;

																valorExcesso = valorExcesso.add(contaCategoriaConsumoFaixa.getValorAgua().multiply(
																		new BigDecimal(fatorEconomias)));
															} else {
																consumoExcesso = consumoExcesso + contaCategoriaConsumoFaixa.getConsumoAgua()
																		* subCategoriaEmitir.getQuantidadeEconomias();

																valorExcesso = valorExcesso.add(contaCategoriaConsumoFaixa.getValorAgua().multiply(
																		new BigDecimal(subCategoriaEmitir.getQuantidadeEconomias())));
															}
														}

													}
												}
											}
										}

										valorMinimo = emitirContaHelper.getValorAgua().subtract(valorExcesso);
										consumoMinimo = emitirContaHelper.getConsumoAgua() - consumoExcesso;

									}

									if (consumoMinimo != null && consumoMinimo == 0) {
										ContaCategoria contaCategoria = (ContaCategoria) cContaCategoria.iterator().next();
										
										consumoMinimo = contaCategoria.getConsumoMinimoAgua();

										if (consumoMinimo == null || consumoMinimo == 0) {
											consumoMinimo = contaCategoria.getConsumoMinimoEsgoto();
										}
									}

								}

								if (emitirContaHelper.getIdConta().equals(new Integer("65264622"))) {
									System.out.println("Conta!!");
								}
								String diasConsumo = obterDiasConsumo(parmsMedicaoHistorico, dataLeituraAnterior, dataLeituraAtual);
								String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(emitirContaHelper, tipoMedicao, diasConsumo);
								String consumoFaturamento = parmsConsumo[0];

								String consumo = obterConsumo(consumoMinimo, consumoFaturamento);
								
								contaTxt.append(Util.adicionarZerosEsquedaNumero(6, consumo));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(6, consumoMedio));
								contaTxt.append(Util.completaString(diasConsumo, 2));
								contaTxt.append(Util.completaString(Util.formatarAnoMesParaMesAno(emitirContaHelper.getAmReferencia()), 7));
								
								contaTxt = preencherDadosConsumoFaixa(emitirContaHelper, contaTxt, consumoMinimo, valorMinimo, dadosContaCategoria);
								contaTxt = preencherDescricaoValorTotalAgua(emitirContaHelper, contaTxt);
								contaTxt = preencherDescricaoValorEsgoto(emitirContaHelper, contaTxt);
								contaTxt = preencherDescricaoTotalCreditos(emitirContaHelper, contaTxt);
								contaTxt = preencherValorDescricaoServicos(emitirContaHelper, contaTxt);
								
								contaTxt.append(Util.completaString(emitirContaHelper.getValorTotalConta(), 13));
								contaTxt.append(Util.completaString(obterMesConsumoAnteriorFormatado(emitirContaHelper, 1), 7));
								contaTxt.append(Util.completaString(obterMesConsumoAnteriorFormatado(emitirContaHelper, 2), 7));
								contaTxt.append(Util.completaString(obterMesConsumoAnteriorFormatado(emitirContaHelper, 3), 7));
								contaTxt.append(Util.completaString(obterMesConsumoAnteriorFormatado(emitirContaHelper, 4), 7));
								contaTxt.append(Util.completaString(obterMesConsumoAnteriorFormatado(emitirContaHelper, 5), 7));
								contaTxt.append(Util.completaString(obterMesConsumoAnteriorFormatado(emitirContaHelper, 6), 7));
								

								String consumoMesAnterior1 = obterConsumoMesAnterior(emitirContaHelper, tipoLigacao, tipoMedicao, consumo, 1);
								String consumoMesAnterior2 = obterConsumoMesAnterior(emitirContaHelper, tipoLigacao, tipoMedicao, consumo, 2);
								String consumoMesAnterior3 = obterConsumoMesAnterior(emitirContaHelper, tipoLigacao, tipoMedicao, consumo, 3);
								String consumoMesAnterior4 = obterConsumoMesAnterior(emitirContaHelper, tipoLigacao, tipoMedicao, consumo, 4);
								String consumoMesAnterior5 = obterConsumoMesAnterior(emitirContaHelper, tipoLigacao, tipoMedicao, consumo, 5);
								String consumoMesAnterior6 = obterConsumoMesAnterior(emitirContaHelper, tipoLigacao, tipoMedicao, consumo, 6);

								contaTxt.append(Util.completaString(consumoMesAnterior1 + "", 7));
								contaTxt.append(Util.completaString(consumoMesAnterior2 + "", 7));
								contaTxt.append(Util.completaString(consumoMesAnterior3  + "", 7));
								contaTxt.append(Util.completaString(consumoMesAnterior4  + "", 7));
								contaTxt.append(Util.completaString(consumoMesAnterior5  + "", 7));
								contaTxt.append(Util.completaString(consumoMesAnterior6  + "", 7));
								
								contaTxt = preencherMensagensConta(sistemaParametro, emitirContaHelper, contaTxt);
								contaTxt = preencherMensagemAnormalidadeConsumo(contaTxt, mensagemContaAnormalidade);
								contaTxt = preencherQuantidadeValorDebitos(sistemaParametro, emitirContaHelper, contaTxt);
								contaTxt = preencherCodigoBarrasConta(emitirContaHelper, contaTxt);
								contaTxt.append(Util.completaString(cont + "", 8));

								String[] qualidade = this.obterDadosQualidadeAguaCosanpa(emitirContaHelper, imovelEmitido.getQuadraFace().getId());
								contaTxt = preencherQualidadedaAgua(qualidade, anoMesReferenciaFaturamento, emitirContaHelper, contaTxt, localidade, imovelEmitido);
								
								contaTxt = preencherEnderecoImovel(emitirContaHelper, contaTxt);
								contaTxt = preencherCpfCnpjCliente(emitirContaHelper, contaTxt);
								contaTxt = preencherDescricaoTipoTarifaConsumo(contaTxt);
								contaTxt = preencherDadosInscricaoImovel(faturamentoGrupo, contaTxt, imovelEmitido);
								contaTxt = preencherAreaConstruidaImovel(contaTxt, imovelEmitido);
								contaTxt.append(Util.completaString(emitirContaHelper.getIdLocalidade() + "", 10));
								contaTxt.append(Util.completaString(endereco, 120));
								contaTxt.append(Util.completaString(imovelEmitido.getQuadraFace().getNumeroQuadraFace() + "", 2));
								contaTxt.append(Util.completaString(localidade.getUnidadeNegocio().getNomeAbreviado(), 6));
								contaTxt = preencherDataPrevistaCronograma(faturamentoGrupo, emitirContaHelper, contaTxt);
								contaTxt = preencherDescricaoLeituraSituacao(contaTxt, idSituacaoLeituraAtual);
								contaTxt = preencherTipoCaptacao(contaTxt, qualidade);
								contaTxt = preencherMensagemTarifaSocial(contaTxt, imovelEmitido);
								contaTxt = preencherEnderecoAlternativo(contaTxt, municipioEntrega, enderecoAlternativo);
								contaTxt.append(Util.completaString(localidade.getUnidadeNegocio().getNome(), 30));
								contaTxt = preencherMensagensInstitucionais(sistemaParametro, emitirContaHelper, contaTxt);

								String diasConsumoMes1 = obterDiasConsumoMesAnterior(emitirContaHelper, 1);
								String diasConsumoMes2 = obterDiasConsumoMesAnterior(emitirContaHelper, 2);
								String diasConsumoMes3 = obterDiasConsumoMesAnterior(emitirContaHelper, 3);
								String diasConsumoMes4 = obterDiasConsumoMesAnterior(emitirContaHelper, 4);
								String diasConsumoMes5 = obterDiasConsumoMesAnterior(emitirContaHelper, 5);
								String diasConsumoMes6 = obterDiasConsumoMesAnterior(emitirContaHelper, 6);

								contaTxt.append(Util.completaString(obterDiasConsumoMesAnterior(emitirContaHelper, 1), 2));
								contaTxt.append(Util.completaString(obterDiasConsumoMesAnterior(emitirContaHelper, 2), 2));
								contaTxt.append(Util.completaString(obterDiasConsumoMesAnterior(emitirContaHelper, 3), 2));
								contaTxt.append(Util.completaString(obterDiasConsumoMesAnterior(emitirContaHelper, 4), 2));
								contaTxt.append(Util.completaString(obterDiasConsumoMesAnterior(emitirContaHelper, 5), 2));
								contaTxt.append(Util.completaString(obterDiasConsumoMesAnterior(emitirContaHelper, 6), 2));

								contaTxt = preencherConsumoTipoMesAnterior(emitirContaHelper, contaTxt, imovelEmitido, 1);
								contaTxt = preencherConsumoTipoMesAnterior(emitirContaHelper, contaTxt, imovelEmitido, 2);
								contaTxt = preencherConsumoTipoMesAnterior(emitirContaHelper, contaTxt, imovelEmitido, 3);
								contaTxt = preencherConsumoTipoMesAnterior(emitirContaHelper, contaTxt, imovelEmitido, 4);
								contaTxt = preencherConsumoTipoMesAnterior(emitirContaHelper, contaTxt, imovelEmitido, 5);
								contaTxt = preencherConsumoTipoMesAnterior(emitirContaHelper, contaTxt, imovelEmitido, 6);

								contaTxt = preencherMediaConsumoMesAnterior(contaTxt, consumoMesAnterior1, diasConsumoMes1);
								contaTxt = preencherMediaConsumoMesAnterior(contaTxt, consumoMesAnterior2, diasConsumoMes2);
								contaTxt = preencherMediaConsumoMesAnterior(contaTxt, consumoMesAnterior3, diasConsumoMes3);
								contaTxt = preencherMediaConsumoMesAnterior(contaTxt, consumoMesAnterior4, diasConsumoMes4);
								contaTxt = preencherMediaConsumoMesAnterior(contaTxt, consumoMesAnterior5, diasConsumoMes5);
								contaTxt = preencherMediaConsumoMesAnterior(contaTxt, consumoMesAnterior6, diasConsumoMes6);

								contaTxt = preencherDadosRateioAguaEsgoto(emitirContaHelper, contaTxt);
								
								if (imovelEmitido.getQuadra().getRota().getIndicadorImpressaoTermicaFinalGrupo().equals(ConstantesSistema.SIM)
										&& municipioEntrega.equals(municipioImovel)) {

									String localidadeArquivo = imovelEmitido.getLocalidade().getId() + "parte" + qntArquivoLocalidadeImpressaoTermica;
									if (!colecaoLocalidadesArquivo.contains(localidadeArquivo)) {
										if (qtdImovelArquivoImpressaoTermica != 1000) {
											qntArquivoLocalidadeImpressaoTermica = 1;
											qtdContasLocalidade = 0;
											localidadeArquivo = imovelEmitido.getLocalidade().getId() + "parte" + qntArquivoLocalidadeImpressaoTermica;
										}
										colecaoLocalidadesArquivo.add(localidadeArquivo);
										qtdImovelArquivoImpressaoTermica = 1;

									} else if (qtdImovelArquivoImpressaoTermica < 1000) {
										qtdImovelArquivoImpressaoTermica++;
									}
									if (qtdImovelArquivoImpressaoTermica == 1000) {
										qntArquivoLocalidadeImpressaoTermica++;
									}
									qtdContasLocalidade++;
									stringFormatadaImpressaoTermica.add(obterDadosImpressaoTermica(qtdContasLocalidade, sistemaParametro, 
											emitirContaHelper, qualidade, localidadeArquivo));

								} else {
									contasTxtLista.append(contaTxt.toString());
									contasTxtLista.append(System.getProperty("line.separator"));
									mapAtualizaSequencial.put(emitirContaHelper.getIdConta(), sequencialImpressao);
									cont++;
								}

								contaTxt = null;

								logger.info("ID_CONTA:" + emitirContaHelper.getIdConta() + " SEQUENCIAL:" + sequencialImpressao + " CONT:" + cont);
								
								if (flagTerminou && ehFaturamentoAntecipado) {
									if (anoMesReferenciaFaturamentoAntecipado != null && anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()) {
										anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
										flagTerminou = false;
										numeroIndiceAntecipado = 0;
									}
								}
							}
						} catch (Exception e) {
							throw new EmitirContaException("Erro ao emitir conta com id = " + emitirContaHelper.getIdConta(), e);
						}
					}

				} else {
					flagTerminou = true;
					if (ehFaturamentoAntecipado) {
						if (anoMesReferenciaFaturamentoAntecipado != null
								&& anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()) {

							anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
							flagTerminou = false;
							numeroIndiceAntecipado = 0;
						}
					}
				}

				numeroIndice = numeroIndice + 1000;

				if (mapAtualizaSequencial != null) {

					logger.info("NUMERO_INDICE_ANTECIPADO:" + numeroIndiceAntecipado);
					logger.info("NUMERO_INDICE:" + numeroIndice);
					logger.info("QTD_CONTAS:" + quantidadeContas);

					repositorioFaturamento.atualizarSequencialContaImpressao(mapAtualizaSequencial);
				}
				mapAtualizaSequencial = null;


			}

			String idGrupoFaturamento = "_G" + faturamentoGrupo.getId();
			String mesReferencia = "_REF" + Util.formatarAnoMesParaMesAnoSemBarra(anoMesReferenciaFaturamentoSemAntecipacao);

			String nomeZip = "CONTA" + idGrupoFaturamento + mesReferencia;
			String nomeArquivoImpressaoFormatada = "ArquivoImpressaoTermica" + idGrupoFaturamento + mesReferencia;

			formatarArquivoContas(contasTxtLista, nomeZip);
			formatarArquivoImpressoraTermica(stringFormatadaImpressaoTermica, colecaoLocalidadesArquivo, nomeArquivoImpressaoFormatada);

			tipoConta++;

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (BaseRuntimeException e) {
			logger.error(e.getMessage(), e);
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
			
		} catch (Exception e) {
			logger.error(e);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	private Object[] obterDadosImpressaoTermica(int qtdContasLocalidade,
			SistemaParametro sistemaParametro,
			EmitirContaHelper emitirContaHelper, String[] qualidade,
			String localidadeArquivo) {
		ImpressaoContaImpressoraTermica impressaoContaImpressoraTermica;
		Object[] impressaoTermica = new Object[2];
		impressaoContaImpressoraTermica = ImpressaoContaImpressoraTermica.getInstancia(repositorioFaturamento,
				repositorioClienteImovel, sessionContext);
		impressaoTermica[0] = impressaoContaImpressoraTermica.gerarArquivoFormatadoImpressaoTermica(emitirContaHelper,
				sistemaParametro, qualidade, qtdContasLocalidade);
		impressaoTermica[1] = localidadeArquivo;
		return impressaoTermica;
	}

	private String obterConsumo(Integer consumoMinimo, String consumoFaturamento) {
		String consumo = "";

		if (consumoFaturamento == null || consumoFaturamento.trim().equals("") || new Integer(consumoFaturamento).intValue() >= consumoMinimo.intValue()) {

			consumo = consumoFaturamento;
		} else {
			consumo = consumoMinimo.toString();
		}
		return consumo;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private StringBuilder preencherDadosConsumoFaixa(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt, Integer consumoMinimo, 
			BigDecimal valorMinimo, StringBuilder dadosContaCategoria) throws ErroRepositorioException {
		
		Collection colecaoContaCategoriaConsumoFaixa;
		if (dadosContaCategoria != null) {
			contaTxt.append(Util.completaString(dadosContaCategoria.toString(), 315));
		} else {

			if (emitirContaHelper.getValorAgua() != null && !emitirContaHelper.getValorAgua().equals(new BigDecimal("0.00"))) {
				contaTxt.append(Util.completaString("AGUA ", 31));
				contaTxt.append(Util.completaString(consumoMinimo + "", 6));
				contaTxt.append(Util.completaString(
						valorMinimo.divide(new BigDecimal(consumoMinimo.toString()), 2, BigDecimal.ROUND_DOWN) + "", 13));
				contaTxt.append(Util.completaString(valorMinimo + "", 13));
			} else {
				contaTxt.append(Util.completaString(" ", 63));
			}

			colecaoContaCategoriaConsumoFaixa = repositorioFaturamento.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
					.getIdConta());

			contaTxt.append(Util.completaString("", 63 * 4));
		}
		
		return contaTxt;
	}

	private String obterDiasConsumo(Object[] parmsMedicaoHistorico, String dataLeituraAnterior, String dataLeituraAtual) {
		String diasConsumo = "30";

		if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
			diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parmsMedicaoHistorico[3], (Date) parmsMedicaoHistorico[2]);
		}
		return diasConsumo;
	}

	private StringBuilder preencherConsumoMedido(StringBuilder contaTxt, String leituraAnterior, String leituraAtual) {
		Integer consumoMedido = (new Integer(leituraAtual)).intValue() - (new Integer(leituraAnterior)).intValue();

		if (consumoMedido.intValue() < 0) {
			consumoMedido = 0;
		}

		contaTxt.append(Util.adicionarZerosEsquedaNumero(7, consumoMedido.toString()));
		
		return contaTxt;
	}

	private void formatarArquivoContas(StringBuilder contasTxtLista, String nomeZip)
			throws FileNotFoundException, IOException {
		
		File compactadoTipo = new File(nomeZip + ".zip");
		File leituraTipo = new File(nomeZip + ".txt");
		BufferedWriter out = null;
		ZipOutputStream zos = null;

		System.out.println("Gerando arquivo " + leituraTipo.getAbsolutePath().toString());
		if (contasTxtLista != null && contasTxtLista.length() != 0) {
			zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
			out.write(contasTxtLista.toString());
			out.flush();
			ZipUtil.adicionarArquivo(zos, leituraTipo);
			zos.close();
			leituraTipo.delete();
			out.close();
		}
		
		out = null;
		zos = null;
		nomeZip = null;
		compactadoTipo = null;
		leituraTipo = null;
		contasTxtLista = null;
	}

	private void formatarArquivoImpressoraTermica(
			Collection<Object[]> stringFormatadaImpressaoTermica,
			Collection<String> localidadesArquivo,
			String nomeArquivoImpressaoFormatada) throws FileNotFoundException,
			IOException {
		ZipOutputStream zos;
		if (stringFormatadaImpressaoTermica != null && stringFormatadaImpressaoTermica.size() > 0) {

			File arquivoImpressaoTermicaCompactado = new File(nomeArquivoImpressaoFormatada + ".zip");

			zos = new ZipOutputStream(new FileOutputStream(arquivoImpressaoTermicaCompactado));
			BufferedWriter[] buf = new BufferedWriter[localidadesArquivo.size()];
			File[] arquivoFormatadoImpressaoTermica = new File[localidadesArquivo.size()];
			int i = 0;

			for (String localidade : localidadesArquivo) {
				arquivoFormatadoImpressaoTermica[i] = new File(nomeArquivoImpressaoFormatada + "_L" + localidade + ".txt");
				buf[i] = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoFormatadoImpressaoTermica[i].getAbsolutePath())));

				for (Object[] impressaoTermica : stringFormatadaImpressaoTermica) {
					if (impressaoTermica != null) {
						String localidadeArquivo = (String) impressaoTermica[1];
						if (localidadeArquivo.equals(localidade) && impressaoTermica[0] != null) {
							buf[i].write((String) impressaoTermica[0]);
							buf[i].flush();
						}
					}
				}
				ZipUtil.adicionarArquivo(zos, arquivoFormatadoImpressaoTermica[i]);
				i++;
			}
			zos.close();
			for (int j = 0; j < localidadesArquivo.size(); j++) {
				arquivoFormatadoImpressaoTermica[j].delete();
				buf[j].close();
				buf[j] = null;
			}
		}
	}

	private StringBuilder preencherMediaConsumoMesAnterior(StringBuilder contaTxt, String consumoMesAnterior, String diasConsumoMes) {
		BigDecimal consumoDB = Util.formatarMoedaRealparaBigDecimal(consumoMesAnterior);
		BigDecimal diasDB = Util.formatarMoedaRealparaBigDecimal(diasConsumoMes);
		String mediaConsumo = Util.formatarMoedaReal(consumoDB.divide(diasDB, 2, BigDecimal.ROUND_DOWN));
		contaTxt.append(Util.completaString(mediaConsumo, 6));
		
		return contaTxt;
	}
	
	private StringBuilder preencherDadosRateioAguaEsgoto(EmitirContaHelper emitirConta, StringBuilder contaTxt) {
		
		if (emitirConta.getValorRateioAgua() != null && emitirConta.getValorRateioAgua().compareTo(BigDecimal.ZERO) == 1) {
			contaTxt.append(Util.completaString("VALOR RATEIO �GUA", 50));
			contaTxt.append(Util.completaString(emitirConta.getValorRateioAgua() + "", 13));
		} else {
			contaTxt.append(Util.completaString(" ", 63));
		}
		
		if (emitirConta.getValorRateioEsgoto() != null && emitirConta.getValorRateioEsgoto().compareTo(BigDecimal.ZERO) == 1) {
			contaTxt.append(Util.completaString("VALOR RATEIO ESGOTO", 50));
			contaTxt.append(Util.completaString(emitirConta.getValorRateioEsgoto() + "", 13));
		} else {
			contaTxt.append(Util.completaString(" ", 63));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherConsumoTipoMesAnterior(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt, 
			Imovel imovelEmitido, Integer qtdMeses) throws ControladorException {
		String consumoTipo = getControladorMicromedicao().obterConsumoTipoImovel(imovelEmitido.getId(), obterMesConsumoAnterior(emitirContaHelper, qtdMeses), 1);
		contaTxt.append(Util.completaString(consumoTipo, 2));
		
		return contaTxt;
	}

	private String obterDiasConsumoMesAnterior(EmitirContaHelper emitirContaHelper, Integer qtdMeses) throws ControladorException {
		
		String dataLeituraAnterior = "";
		String dataLeituraAtual = "";
		String diasConsumo = "30";
		
		Object[] parms1 = this.obterLeituraAnteriorEAtual(emitirContaHelper.getIdImovel(), obterMesConsumoAnterior(emitirContaHelper, qtdMeses));

		if (parms1 != null) {

			if (parms1[0] != null) {
				dataLeituraAnterior = Util.formatarData((Date) parms1[0]);
			}
			if (parms1[1] != null) {
				dataLeituraAtual = Util.formatarData((Date) parms1[1]);
			}

			if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
				diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parms1[0], (Date) parms1[1]);
				if (diasConsumo.equalsIgnoreCase("0")) {
					diasConsumo = "30";
				}
			}
		}
		return diasConsumo;
	}

	private StringBuilder preencherEnderecoAlternativo(StringBuilder contaTxt, String municipioEntrega, boolean enderecoAlternativo) {
		if (enderecoAlternativo) {
			contaTxt.append(Util.completaString(municipioEntrega, 40));
		} else {
			contaTxt.append(Util.completaString("", 40));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherTipoCaptacao(StringBuilder contaTxt, String[] qualidade) {
		if (!qualidade[24].trim().equalsIgnoreCase("")) {
			contaTxt.append(Util.completaString(qualidade[24].toString(), 1));
		} else {
			contaTxt.append(Util.completaString("1", 1));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherCodigoBarrasConta(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt) throws ControladorException {
		Conta conta = new Conta(emitirContaHelper.getValorAgua(), emitirContaHelper.getValorEsgoto(), emitirContaHelper.getValorCreditos(),
				emitirContaHelper.getDebitos(), emitirContaHelper.getValorImpostos());

		if (emitirContaHelper.getValorRateioAgua() != null)
			conta.setValorRateioAgua(emitirContaHelper.getValorRateioAgua());
		if (emitirContaHelper.getValorRateioEsgoto() != null)
			conta.setValorRateioEsgoto(emitirContaHelper.getValorRateioEsgoto());

		BigDecimal valorConta = conta.getValorTotalContaComRateioBigDecimal();

		emitirContaHelper.setValorConta(valorConta);

		String anoMesString = "" + emitirContaHelper.getAmReferencia();
		String mesAnoFormatado = anoMesString.substring(4, 6) + anoMesString.substring(0, 4);
		Integer digitoVerificadorConta = new Integer("" + emitirContaHelper.getDigitoVerificadorConta());

		String representacaoNumericaCodBarra = this.getControladorArrecadacao().obterRepresentacaoNumericaCodigoBarra(3, valorConta,
				emitirContaHelper.getIdLocalidade(), emitirContaHelper.getIdImovel(), mesAnoFormatado, digitoVerificadorConta, null, null, null, null, null,
				null, null);

		contaTxt.append(Util.completaString(representacaoNumericaCodBarra, 50));
		return contaTxt;
	}

	@SuppressWarnings("rawtypes")
	private StringBuilder preencherQuantidadeValorDebitos(SistemaParametro sistemaParametro, EmitirContaHelper emitirContaHelper, StringBuilder contaTxt)
			throws ControladorException {
		ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this.obterDebitoImovelOuClienteHelper(emitirContaHelper,
				sistemaParametro);

		Collection colecaoContasValores = obterDebitoImovelOuClienteHelper.getColecaoContasValores();

		if (colecaoContasValores != null && !colecaoContasValores.isEmpty()) {
			Integer qtContas = colecaoContasValores.size();
			contaTxt.append(Util.completaString(qtContas + "", 6));

			BigDecimal valorDebitoTotal = new BigDecimal("0.00");

			for (Iterator iter = colecaoContasValores.iterator(); iter.hasNext();) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter.next();

				valorDebitoTotal = valorDebitoTotal.add(contaValoresHelper.getValorTotalConta());
			}

			contaTxt.append(Util.completaString(Util.formatarMoedaReal(valorDebitoTotal), 13));
		} else {
			contaTxt.append(Util.completaString(" ", 19));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherMensagemAnormalidadeConsumo(StringBuilder contaTxt, String mensagemContaAnormalidade) {
		if (mensagemContaAnormalidade != null && !mensagemContaAnormalidade.equalsIgnoreCase("")) {
			contaTxt.append(Util.completaString(mensagemContaAnormalidade, 100));
		} else {
			contaTxt.append(Util.completaString(" ", 100));
		}
		
		return contaTxt;
	}

	private String obterMesConsumoAnteriorFormatado(EmitirContaHelper emitirContaHelper, Integer qtdMeses) {
		int mesConsumoAnterior = Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), qtdMeses);
		return Util.formatarAnoMesParaMesAno(mesConsumoAnterior);
	}
	
	private int obterMesConsumoAnterior(EmitirContaHelper emitirContaHelper, Integer qtdMeses) {
		return Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), qtdMeses);
	}

	@SuppressWarnings("rawtypes")
	private StringBuilder preencherValorDescricaoServicos(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt)
			throws ControladorException {
		Conta contaId = new Conta();
		contaId.setId(emitirContaHelper.getIdConta());

		Collection<DebitoCobradoAgrupadoHelper> cDebitoCobrado = this.obterDebitosCobradosContaCAERN(contaId);

		int quantidadeLinhasSobrando = 9;
		int i = 0;

		if (cDebitoCobrado != null && !cDebitoCobrado.isEmpty()) {

			int quantidadeDebitos = cDebitoCobrado.size();

			if (quantidadeLinhasSobrando >= quantidadeDebitos) {

				for (Iterator iter = cDebitoCobrado.iterator(); iter.hasNext();) {
					DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter.next();

					contaTxt.append(Util.completaString(debitoCobrado.getDescricaoDebitoTipo(), 30)); // 30
					contaTxt.append(Util.completaString(
							debitoCobrado.getNumeroPrestacaoDebito() + "/" + debitoCobrado.getNumeroPrestacao(), 20));
					contaTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(debitoCobrado.getValorDebito()), 13));

					i++;
				}

			} else {
				Iterator iter = cDebitoCobrado.iterator();
				int contador = 1;
				BigDecimal valorAcumulado = new BigDecimal("0.00");
				boolean temOutros = false;
				while (iter.hasNext()) {
					DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter.next();

					if (quantidadeLinhasSobrando > contador) {
						contaTxt.append(Util.completaString(debitoCobrado.getDescricaoDebitoTipo(), 30)); // 30
						contaTxt.append(Util.completaString(
								debitoCobrado.getNumeroPrestacaoDebito() + "/" + debitoCobrado.getNumeroPrestacao(), 20));
						contaTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(debitoCobrado.getValorDebito()),
								13));
						i++;
					} else {

						valorAcumulado = valorAcumulado.add(debitoCobrado.getValorDebito());
						temOutros = true;
					}

					contador++;
				}
				if (temOutros) {
					contaTxt.append("OUTROS SERVICOS               "); // 30
					contaTxt.append(Util.completaString(" ", 20));
					contaTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(valorAcumulado), 13));
					i++;
				}
			}
		}

		if (emitirContaHelper.getValorImpostos() != null && !emitirContaHelper.getValorImpostos().equals(new BigDecimal("0.00"))) {
			contaTxt.append("TOTAL DE IMPOSTOS FEDERAIS "); // 27
			contaTxt.append(Util.completaString(" ", 23));
			contaTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(emitirContaHelper.getValorImpostos()), 13));

		} else {
			contaTxt.append(Util.completaString(" ", 63));
		}

		int quantidadeLinhasServicosSobraram = 9 - i;
		contaTxt.append(Util.completaString(" ", quantidadeLinhasServicosSobraram * 63));
		
		return contaTxt;
	}

	private StringBuilder preencherDescricaoTotalCreditos(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt) {
		if (emitirContaHelper.getValorCreditos() != null && !emitirContaHelper.getValorCreditos().equals(new BigDecimal("0.00"))) {
			contaTxt.append(Util.completaString("TOTAL DE CREDITOS ", 50));
			contaTxt.append(Util.completaString(emitirContaHelper.getValorCreditos() + "", 13));
		} else {
			contaTxt.append(Util.completaString(" ", 63));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherDescricaoValorEsgoto(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt) {
		if (emitirContaHelper.getValorEsgoto() != null && !emitirContaHelper.getValorEsgoto().equals(new BigDecimal("0.00"))) {
			contaTxt.append(Util.completaString("ESGOTO ", 50));
			contaTxt.append(Util.completaString(emitirContaHelper.getValorEsgoto() + "", 13));

			contaTxt.append(Util.completaString(" ", 63));
			
			contaTxt.append(Util.completaString(" ", 50));
			contaTxt.append(Util.completaString(" ", 13));
		} else {
			contaTxt.append(Util.completaString(" ", 189));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherDescricaoValorTotalAgua(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt) {
		if (emitirContaHelper.getValorAgua() != null) {
			contaTxt.append(Util.completaString("TOTAL �GUA ", 50));
			contaTxt.append(Util.completaString(emitirContaHelper.getValorAgua() + "", 13));
		} else {
			contaTxt.append(Util.completaString(" ", 63));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherNumeroHidrometro(StringBuilder contaTxt, Imovel imovelEmitido) {
		if (imovelEmitido.getLigacaoAgua() != null) {

			if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {

				if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() != null) {

					contaTxt.append(Util.completaString(imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico()
							.getHidrometro().getNumero(), 12));
				} else {
					contaTxt.append(Util.completaString(" ", 12));
				}

			} else {
				contaTxt.append(Util.completaString(" ", 12));
			}

		} else {
			contaTxt.append(Util.completaString(" ", 12));
		}
		
		return contaTxt;
	}

	@SuppressWarnings("rawtypes")
	private StringBuilder preencherQuantidadeEconomias(StringBuilder contaTxt, Imovel imovelEmitido) throws ControladorException {
		Collection cIS = getControladorImovel().pesquisarImovelSubcategoria(imovelEmitido);

		boolean residencial = false;
		boolean comercial = false;
		boolean industrial = false;
		boolean publico = false;

		int economiaResidencial = 0;
		int economiaComercial = 0;
		int economiaIndustrial = 0;
		int economiaPublica = 0;
		int economiaTotal = 0;

		for (Iterator iter = cIS.iterator(); iter.hasNext();) {
			ImovelSubcategoria iS = (ImovelSubcategoria) iter.next();

			if (iS.getComp_id().getSubcategoria().getCategoria().getId().equals(1)) {
				residencial = true;
				economiaResidencial = economiaResidencial + iS.getQuantidadeEconomias();
				economiaTotal = economiaTotal + iS.getQuantidadeEconomias();
			}

			if (iS.getComp_id().getSubcategoria().getCategoria().getId().equals(2)) {
				comercial = true;
				economiaComercial = economiaComercial + iS.getQuantidadeEconomias();
				economiaTotal = economiaTotal + iS.getQuantidadeEconomias();
			}

			if (iS.getComp_id().getSubcategoria().getCategoria().getId().equals(3)) {
				comercial = true;
				economiaIndustrial = economiaIndustrial + iS.getQuantidadeEconomias();
				economiaTotal = economiaTotal + iS.getQuantidadeEconomias();
			}

			if (iS.getComp_id().getSubcategoria().getCategoria().getId().equals(4)) {
				publico = true;
				economiaPublica = economiaPublica + iS.getQuantidadeEconomias();
				economiaTotal = economiaTotal + iS.getQuantidadeEconomias();
			}
		}

		String economiaPorCategoria = "";

		if (residencial) {
			economiaPorCategoria = "R" + Util.adicionarZerosEsquedaNumero(3, economiaResidencial + "") + " ";
		}

		if (comercial) {
			economiaPorCategoria = "C" + Util.adicionarZerosEsquedaNumero(3, economiaComercial + "") + " ";
		}

		if (industrial) {
			economiaPorCategoria = "I" + Util.adicionarZerosEsquedaNumero(3, economiaIndustrial + "") + " ";
		}

		if (publico) {
			economiaPorCategoria = "P" + Util.adicionarZerosEsquedaNumero(3, economiaPublica + "") + " ";
		}

		contaTxt.append(Util.completaString(economiaPorCategoria, 50));
		contaTxt.append(Util.adicionarZerosEsquedaNumero(4, economiaTotal + ""));
		
		return contaTxt;
	}

	@SuppressWarnings("rawtypes")
	private StringBuilder preencherSubcategoriaImovel(StringBuilder contaTxt, Imovel imovelEmitido) throws ControladorException {
		Collection colecaoSubcategoria = getControladorImovel().obterQuantidadeEconomiasSubCategoria(imovelEmitido.getId());

		String subCat = "";

		for (Iterator iter = colecaoSubcategoria.iterator(); iter.hasNext();) {
			Subcategoria subCategoria = (Subcategoria) iter.next();

			subCat = subCat + subCategoria.getDescricaoAbreviada() + " ";
		}

		contaTxt.append(Util.completaString(subCat, 30));
		
		return contaTxt;
	}

	private StringBuilder preencherUnidadeNegocio(StringBuilder contaTxt, Localidade localidade) {
		if (localidade.getUnidadeNegocio().getId() != null && !localidade.getUnidadeNegocio().getId().equals("")) {
			contaTxt.append(Util.adicionarZerosEsquedaNumero(2, localidade.getUnidadeNegocio().getId().toString()));
		} else {
			contaTxt.append(Util.adicionarZerosEsquedaNumero(2, "00"));
		}
		
		return contaTxt;
	}

	private String obterNumeroNota(EmitirContaHelper emitirContaHelper) {
		return emitirContaHelper.getIdConta() + "/" + Util.formatarAnoMesParaMesAnoSemBarra(emitirContaHelper.getAmReferencia());
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private String obterEnderecoClienteResponsavel(Collection colecaoClienteImovel, Imovel imovel) {
		
		String endereco = "";
		String municipioEntrega = "";
		String bairroEntrega = "";
		String cepEntrega = "";
		String ufEntrega = "";
		String logCepClie = "";
		String logBairroClie = "";
		boolean enderecoAlternativo = false;

		try {
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
			ClienteImovel clienteImovelRespConta = (ClienteImovel) colecaoClienteImovel.iterator().next();

			if (clienteImovelRespConta != null) {
				Cliente cliente = clienteImovelRespConta.getCliente();

				if (cliente != null && imovel.getImovelContaEnvio().getId().equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)) {
					String[] enderecoCliente;
						enderecoCliente = getControladorEndereco().pesquisarEnderecoClienteAbreviadoDivididoCosanpa(
								cliente.getId());
					bairroEntrega = enderecoCliente[3];
					municipioEntrega = enderecoCliente[1];
					ufEntrega = enderecoCliente[2];
					cepEntrega = enderecoCliente[4];
					logCepClie = enderecoCliente[5];
					logBairroClie = enderecoCliente[6];
					enderecoAlternativo = true;

					endereco = enderecoCliente[0] + " - " + bairroEntrega + " " + municipioEntrega + " " + ufEntrega + " " + cepEntrega;

				}
			}
		}
		} catch (ControladorException e) {
			logger.error(e);
		}

		return endereco;
	}

	private StringBuilder preencherNomeCliente(int tipoConta, EmitirContaHelper emitirContaHelper, StringBuilder contaTxt)
			throws ErroRepositorioException {
		if (tipoConta == 3 || tipoConta == 4) {
			String nomeCliente = null;
			if (emitirContaHelper.getNomeImovel() != null && !emitirContaHelper.getNomeImovel().equals("")) {

				nomeCliente = emitirContaHelper.getNomeImovel();

			} else {
				nomeCliente = repositorioFaturamento.pesquisarNomeClienteUsuarioConta(emitirContaHelper.getIdConta());
			}
			contaTxt.append(Util.completaString(nomeCliente, 40));
		} else {
			contaTxt.append(Util.completaString(emitirContaHelper.getNomeCliente(), 40));
		}
		
		return contaTxt;
	}

	@SuppressWarnings("rawtypes")
	private Localidade obterLocalidade(EmitirContaHelper emitirContaHelper)
			throws ControladorException {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, emitirContaHelper.getIdLocalidade()));

		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");

		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

		Collection cLocalidade = (Collection) getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
		Localidade localidade = (Localidade) cLocalidade.iterator().next();
		return localidade;
	}

	private StringBuilder preencherMensagensInstitucionais(SistemaParametro sistemaParametro,
			EmitirContaHelper emitirContaHelper, StringBuilder contaTxt)
			throws ControladorException {
		String[] parmsPartesContaMensagem = this.obterMensagemConta3Partes(emitirContaHelper, sistemaParametro);

		contaTxt.append(Util.completaString(parmsPartesContaMensagem[0], 100));
		emitirContaHelper.setMsgLinha1Conta(parmsPartesContaMensagem[0]);

		contaTxt.append(Util.completaString(parmsPartesContaMensagem[1], 100));
		emitirContaHelper.setMsgLinha2Conta(parmsPartesContaMensagem[1]);

		contaTxt.append(Util.completaString(parmsPartesContaMensagem[2], 100));
		emitirContaHelper.setMsgLinha3Conta(parmsPartesContaMensagem[2]);
		
		return contaTxt;
	}

	private StringBuilder preencherMensagemTarifaSocial(StringBuilder contaTxt, Imovel imovelEmitido) {
		String mensagemBonusSocial = "Para usufruir dos beneficios do Bonus Social e necessario ".toUpperCase()
				+ "efetuar o pagamento desta conta ate a data de vencimento".toUpperCase();

		if (imovelEmitido.getImovelPerfil().getId().equals(ImovelPerfil.TARIFA_SOCIAL)) {
			contaTxt.append(Util.completaString(mensagemBonusSocial, 150));
		} else {
			contaTxt.append(Util.completaString("", 150));
		}
		
		return contaTxt;
	}

	@SuppressWarnings("unchecked")
	private StringBuilder preencherDescricaoLeituraSituacao(StringBuilder contaTxt, String idSituacaoLeituraAtual) throws ControladorException {
		String leituraSituacaoDescricao = "";

		FiltroLeituraSituacao filtroLeituraSituacao = new FiltroLeituraSituacao();
		filtroLeituraSituacao.adicionarParametro(new ParametroSimples(FiltroLeituraSituacao.ID, idSituacaoLeituraAtual));

		Collection<LeituraSituacao> colecaoLeituraSituacao = getControladorUtil().pesquisar(filtroLeituraSituacao, LeituraSituacao.class.getName());
		if (colecaoLeituraSituacao != null && !colecaoLeituraSituacao.isEmpty()) {
			LeituraSituacao LeituraSituacao = (LeituraSituacao) Util.retonarObjetoDeColecao(colecaoLeituraSituacao);
			if (LeituraSituacao != null) {
				leituraSituacaoDescricao = LeituraSituacao.getDescricao();
			}
		}

		contaTxt.append(Util.completaString(leituraSituacaoDescricao, 20));
		
		return contaTxt;
	}

	private StringBuilder preencherMensagensConta(SistemaParametro sistemaParametro,
			EmitirContaHelper emitirContaHelper, StringBuilder contaTxt)
			throws ControladorException {
		String[] parmsPartesConta = obterMensagemDebitoConta3Partes(emitirContaHelper, sistemaParametro);

		contaTxt.append(Util.completaString(parmsPartesConta[0], 100));
		contaTxt.append(Util.completaString(parmsPartesConta[1], 100));
		contaTxt.append(Util.completaString(parmsPartesConta[2], 100));
		
		return contaTxt;
	}

	@SuppressWarnings("unchecked")
	private StringBuilder preencherDataPrevistaCronograma(FaturamentoGrupo faturamentoGrupo, EmitirContaHelper emitirContaHelper, 
			StringBuilder contaTxt) throws ControladorException {
		Integer amRefMaisUm = Util.somaMesAnoMesReferencia(emitirContaHelper.getAmReferencia(), 1);

		FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();

		filtroFaturamentoAtividadeCronograma.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID, "2"));
		filtroFaturamentoAtividadeCronograma.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA, amRefMaisUm + ""));
		filtroFaturamentoAtividadeCronograma.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID, faturamentoGrupo.getId()
						+ ""));

		filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade");
		filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal");
		filtroFaturamentoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");

		Collection<FaturamentoAtividadeCronograma> cFaturamentoAtividadeCronograma = getControladorUtil().pesquisar(
				filtroFaturamentoAtividadeCronograma, FaturamentoAtividadeCronograma.class.getName());
		String dataPrevista = "";
		if (cFaturamentoAtividadeCronograma != null && !cFaturamentoAtividadeCronograma.isEmpty()) {
			FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
					.retonarObjetoDeColecao(cFaturamentoAtividadeCronograma);
			if (faturamentoAtividadeCronograma.getDataPrevista() != null) {
				dataPrevista = Util.formatarData(faturamentoAtividadeCronograma.getDataPrevista());
			}
		}

		contaTxt.append(Util.completaString(dataPrevista, 10));
		
		return contaTxt;
	}

	private StringBuilder preencherAreaConstruidaImovel(StringBuilder contaTxt,
			Imovel imovelEmitido) {
		if (imovelEmitido.getAreaConstruida() != null) {
			int area = imovelEmitido.getAreaConstruida().intValue();
			contaTxt.append(Util.completaString(area + "", 5));

		} else {
			contaTxt.append(Util.completaString("", 5));
		}
		
		return contaTxt;
	}

	private StringBuilder preencherDadosInscricaoImovel(
			FaturamentoGrupo faturamentoGrupo, StringBuilder contaTxt,
			Imovel imovelEmitido) throws ControladorException {
		Object[] dadosRota = getControladorMicromedicao().obterRotaESequencialRotaDoImovelSeparados(imovelEmitido.getId());

		contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(6, dadosRota[0].toString()) + "", 6));
		contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(3, faturamentoGrupo.getId().toString()) + "", 3));
		contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(4, imovelEmitido.getLote() + "") + "", 4));
		contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(3, imovelEmitido.getSubLote() + "") + "", 3));
		
		return contaTxt;
	}

	@SuppressWarnings("rawtypes")
	private StringBuilder preencherDescricaoTipoTarifaConsumo(StringBuilder contaTxt) throws ControladorException {
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();

		Collection colecaoConsumoTarifa = getControladorUtil().pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
		ConsumoTarifa consumoTarifa = (ConsumoTarifa) colecaoConsumoTarifa.iterator().next();
		contaTxt.append(Util.completaString(consumoTarifa.getDescricao(), 25));
		
		return contaTxt;
	}

	@SuppressWarnings("rawtypes")
	private StringBuilder preencherCpfCnpjCliente(EmitirContaHelper emitirContaHelper, StringBuilder contaTxt)
			throws ErroRepositorioException {
		String cnpjCpf = "";

		Collection colecaoClienteImovel2 = repositorioClienteImovel.pesquisarClienteImovelResponsavelConta(emitirContaHelper
				.getIdImovel());

		if (colecaoClienteImovel2 != null && !colecaoClienteImovel2.isEmpty()) {
			ClienteImovel clienteImovelRespConta2 = (ClienteImovel) colecaoClienteImovel2.iterator().next();

			if (clienteImovelRespConta2 != null) {
				Cliente cliente2 = clienteImovelRespConta2.getCliente();

				if (cliente2.getCnpjFormatado() != null && !cliente2.getCnpjFormatado().equalsIgnoreCase("")) {
					cnpjCpf = cliente2.getCnpjFormatado();
				} else if (cliente2.getCpfFormatado() != null && !cliente2.getCpfFormatado().equalsIgnoreCase("")) {
					cnpjCpf = cliente2.getCpfFormatado();
				}

			}
		}
		
		contaTxt.append(Util.completaString(cnpjCpf, 20));
		
		return contaTxt;
	}

	private StringBuilder preencherEnderecoImovel(EmitirContaHelper emitirContaHelper,
			StringBuilder contaTxt) throws ControladorException {
		String[] enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatadoDividido(emitirContaHelper.getIdImovel());

		contaTxt.append(Util.completaString(enderecoImovel[1], 30));
		contaTxt.append(Util.completaString(enderecoImovel[0], 78));
		contaTxt.append(Util.completaString(enderecoImovel[3], 20));
		contaTxt.append(Util.completaString(enderecoImovel[4], 9));
		contaTxt.append(Util.completaString(enderecoImovel[2], 2));
		
		return contaTxt;
	}

	private StringBuilder preencherQualidadedaAgua(String[] qualidade, Integer anoMesReferenciaFaturamento, EmitirContaHelper emitirContaHelper, 
			StringBuilder contaTxt, Localidade localidade, Imovel imovelEmitido) throws ControladorException {
		
		contaTxt.append(Util.completaString(qualidade[0], 10));
		contaTxt.append(Util.completaString(qualidade[1], 10));
		contaTxt.append(Util.completaString(qualidade[2], 10));
		contaTxt.append(Util.completaString(qualidade[3], 10));
		contaTxt.append(Util.completaString(qualidade[4], 10));
		contaTxt.append(Util.completaString(qualidade[5], 10));
		contaTxt.append(Util.completaString(qualidade[6], 10));
		contaTxt.append(Util.completaString(qualidade[7], 10));
		contaTxt.append(Util.completaString(qualidade[8], 10));
		contaTxt.append(Util.completaString(qualidade[9], 10));
		contaTxt.append(Util.completaString(qualidade[10], 10));
		contaTxt.append(Util.completaString(qualidade[11], 10));
		contaTxt.append(Util.completaString(qualidade[12], 10));
		contaTxt.append(Util.completaString(qualidade[13], 10));
		contaTxt.append(Util.completaString(qualidade[14], 10));
		contaTxt.append(Util.completaString(qualidade[15], 10));
		contaTxt.append(Util.completaString(qualidade[16], 10));
		contaTxt.append(Util.completaString(qualidade[17], 10));
		contaTxt.append(Util.completaString(qualidade[18], 10));
		contaTxt.append(Util.completaString(qualidade[19], 10));
		contaTxt.append(Util.completaString(qualidade[20], 10));
		contaTxt.append(Util.completaString(qualidade[21], 10));
		contaTxt.append(Util.completaString(qualidade[22], 10));
		contaTxt.append(Util.completaString(qualidade[23], 10));
		
		return contaTxt;
	}

	private String obterConsumoMesAnterior(EmitirContaHelper emitirContaHelper, Integer tipoLigacao, Integer tipoMedicao,
			String consumo, Integer qtdMeses) throws ControladorException {
		StringBuilder consumoAnterior = this.obterConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), qtdMeses, tipoLigacao, tipoMedicao);

		String consumoMesAnterior = consumoAnterior.toString();

		if (consumo.equalsIgnoreCase("")) {
			consumo = consumo + "0";
		}
		
		if (consumoMesAnterior == null || consumoMesAnterior.trim().equalsIgnoreCase("")) {
			consumoMesAnterior = consumo;
		}

		return consumoMesAnterior;
	}
	
	private void alterarVencimentoContasFaturarGrupo(Integer contaTipo, Integer idEmpresa, Integer numeroIndice, FaturamentoGrupo faturamentoGrupo) {
		Collection<Conta> colecaoContasNovoVencimento = new ArrayList<Conta>();
		try {
			
			Collection colecaoContaParms = repositorioFaturamento.pesquisarContasEmitirCOSANPA(contaTipo, idEmpresa, numeroIndice, faturamentoGrupo.getAnoMesReferencia(), faturamentoGrupo.getId());
			Collection<EmitirContaHelper> colecaoConta = formatarEmitirContasHelper(colecaoContaParms, ContaTipo.CONTA_NORMAL);

			Date novaDataVencimento = repositorioFaturamento.obterDataVencimentoContasFaturarGrupo(faturamentoGrupo);
			
			for (EmitirContaHelper emitirConta : colecaoConta) {
				
				int comparacaoData = Util.compararData(emitirConta.getDataVencimentoConta(), novaDataVencimento);

				System.out.println("Im�vel " + emitirConta.getIdImovel() + ": [" + emitirConta.getDataVencimentoConta() + " < " + novaDataVencimento + "? " + comparacaoData);
				
				if (comparacaoData == -1) {
					Conta conta = (Conta) repositorioFaturamento.obterConta(emitirConta.getIdConta()).iterator().next();
					conta.setDataVencimentoConta(novaDataVencimento);
					conta.setUltimaAlteracao(new Date());
					
					colecaoContasNovoVencimento.add(conta);
				}
				
			}
			
			getControladorBatch().atualizarColecaoObjetoParaBatch(colecaoContasNovoVencimento);
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao atualizar nova data de vencimento para contas.");
		} catch (ControladorException e) {
			logger.error("Erro ao atualizar nova data de vencimento para contas.");
		}
		
	}
	

	/**
	 * Metodo utilizado para adicionar os dados das contas categorias no txt no
	 * caso do imovel ser misto
	 * 
	 * @author Rafael Correa
	 * @date 14/11/2008
	 * 
	 * @param cContaCategoria
	 * @return
	 * @throws ControladorException
	 */
	private StringBuilder obterDadosContaCategoriaMisto(Collection<IContaCategoria> cContaCategoria) {

		StringBuilder retorno = new StringBuilder();

		for (IContaCategoria contaCategoria : cContaCategoria) {

			Integer consumoAgua = contaCategoria.getConsumoAgua();
			BigDecimal valorAgua = contaCategoria.getValorAgua();

			if (valorAgua != null && !valorAgua.equals(new BigDecimal("0.00"))) {

				String subcategoriaFormatada = Util.completaString(contaCategoria.getDescricao(), 15);
				String quantidadeEconomias = contaCategoria.getQuantidadeEconomia() + " UNIDADE(S)";

				retorno.append(Util.completaString(subcategoriaFormatada + " " + quantidadeEconomias, 31));

				if (consumoAgua != null && consumoAgua != 0) {
					retorno.append(Util.completaString(consumoAgua.toString(),
							6));
					retorno.append(Util.completaString(
							valorAgua.divide(
									new BigDecimal(consumoAgua.toString()), 2,
									BigDecimal.ROUND_DOWN) + "", 13));
				} else {
					retorno.append(Util.completaString("0", 6));
					retorno.append(Util.completaString(valorAgua.toString(), 13));
				}

				retorno.append(Util.completaString(valorAgua.toString(), 13));

			} else {
				retorno.append(Util.completaString("", 63));
				// Integer consumoEsgoto = contaCategoria.getConsumoEsgoto();
				// BigDecimal valorEsgoto = contaCategoria.getValorEsgoto();
				//
				// retorno.append(Util.completaString(consumoEsgoto.toString(),
				// 6));
				//
				// retorno.append(Util.completaString(valorEsgoto.divide(
				// new BigDecimal(consumoEsgoto.toString()), 2,
				// BigDecimal.ROUND_DOWN)
				// + "", 13));
				//
				// retorno.append(Util.completaString(valorEsgoto.toString(),
				// 13));
			}

		}

		return retorno;
	}

	public StringBuilder obterConsumoAnterior(Integer idImovel, int anoMes, int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException {

		StringBuilder dadosConsumoAnterior = new StringBuilder();

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);

		if (tipoLigacao != null && tipoMedicao != null) {
			Object[] parmsConsumoHistorico = null;
			parmsConsumoHistorico = getControladorMicromedicao().obterConsumoAnteriorAnormalidadeDoImovel(idImovel, anoMesSubtraido, tipoLigacao);
			Integer numeroConsumoFaturadoMes = null;
			if (parmsConsumoHistorico != null) {
				if (parmsConsumoHistorico[0] != null) {
					numeroConsumoFaturadoMes = (Integer) parmsConsumoHistorico[0];
				}
			}
			if (numeroConsumoFaturadoMes != null) {
				dadosConsumoAnterior.append(Util.completaString(""
						+ numeroConsumoFaturadoMes, 7));
			} else {
				dadosConsumoAnterior.append(Util.completaString("", 7));
			}

		} else {
			dadosConsumoAnterior.append(Util.completaString("", 7));
		}

		return dadosConsumoAnterior;
	}

	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper(EmitirContaHelper emitirContaHelper, SistemaParametro sistemaParametro) throws ControladorException {

		Integer anoMesReferenciaFinal = sistemaParametro.getAnoMesFaturamento();
		Integer dataVencimentoFinalInteger = sistemaParametro.getAnoMesArrecadacao();

		String anoMesSubtraidoString = ""+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
		int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
		int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

		Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
		dataVencimentoFinal.set(Calendar.YEAR, ano);
		dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
		dataVencimentoFinal.set(Calendar.DAY_OF_MONTH,dataVencimentoFinal.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date dataFinalDate = dataVencimentoFinal.getTime();

		Date dataVencimento = Util.converteStringParaDate("01/01/1900");

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
				.obterDebitoImovelOuCliente(1,
						"" + emitirContaHelper.getIdImovel(), null, null,
						"190001", "" + anoMesSubtraido, dataVencimento,
						dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null);

		return debitoImovelClienteHelper;
	}

	@SuppressWarnings({ "rawtypes" })
	public String[] obterDadosQualidadeAguaCosanpa(EmitirContaHelper emitirConta, Integer idQuadraFace)
			throws ControladorException {

		String[] retornoQualidade = new String[25];

		String padraoCor = "";
		String padraoTurbidez = "";
		String padraoFluor = "";
		String padraoCloro = "";
		String padraoColiformesTotais = "";
		String padraoColiformesTermotolerantes = "";

		String exigidaCor = "";
		String exigidaTurbidez = "";
		String exigidaFluor = "";
		String exigidaCloro = "";
		String exigidaColiformesTotais = "";
		String exigidaColiformesTermotolerantes = "";

		String analisadaCor = "";
		String analisadaTurbidez = "";
		String analisadaFluor = "";
		String analisadaCloro = "";
		String analisadaColiformesTotais = "";
		String analisadaColiformesTermotolerantes = "";

		String emConformidadeCor = "";
		String emConformidadeTurbidez = "";
		String emConformidadeFluor = "";
		String emConformidadeCloro = "";
		String emConformidadeColiformesTotais = "";
		String emConformidadeColiformesTermotolerantes = "";

		String tipoCaptacao = "";

		FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();

		Collection colecaoQualidAguaPadrao = getControladorUtil().pesquisar(filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class.getName());

		if (colecaoQualidAguaPadrao != null && !colecaoQualidAguaPadrao.isEmpty()) {

			QualidadeAguaPadrao qualidadePadrao = (QualidadeAguaPadrao) colecaoQualidAguaPadrao.iterator().next();

			padraoCor = qualidadePadrao.getDescricaoPadraoCor();
			padraoTurbidez = qualidadePadrao.getDescricaoPadraoTurbidez();
			padraoFluor = qualidadePadrao.getDescricaoPadraoFluor();
			padraoCloro = qualidadePadrao.getDescricaoPadraoCloro();
			padraoColiformesTotais = qualidadePadrao.getDescricaoPadraoColiformesTotais();
			padraoColiformesTermotolerantes = qualidadePadrao.getDescricaoPadraoColiformesTermotolerantes();

		}

		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

		Collection colecaoQualidadeAgua = null;

		QuadraFace quadraFace = obterQuadraFace(idQuadraFace);

		if (quadraFace.getDistritoOperacional() != null
				&& quadraFace.getDistritoOperacional().getSetorAbastecimento() != null
				&& quadraFace.getDistritoOperacional().getSetorAbastecimento()
						.getSistemaAbastecimento() != null) {

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.SISTEMA_ABASTECIMENTO, quadraFace
							.getDistritoOperacional().getSetorAbastecimento().getSistemaAbastecimento().getId()));

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA, emitirConta.getAmReferencia()));

			if (quadraFace.getDistritoOperacional().getSetorAbastecimento()	.getSistemaAbastecimento().getFonteCaptacao() != null
					&& quadraFace.getDistritoOperacional().getSetorAbastecimento().getSistemaAbastecimento()
							.getFonteCaptacao().getTipoCaptacao() != null) {

				tipoCaptacao = quadraFace.getDistritoOperacional().getSetorAbastecimento().getSistemaAbastecimento()
						.getFonteCaptacao().getTipoCaptacao().getId()+ "";
			}

			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua, QualidadeAgua.class.getName());

		}

		// Com Localidade e Setor
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {

			filtroQualidadeAgua.limparListaParametros();

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.LOCALIDADE_ID, emitirConta.getIdLocalidade()));
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.SETOR_COMERCIAL_ID, emitirConta.getIdSetorComercial()));
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA, emitirConta.getAmReferencia()));
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua, QualidadeAgua.class.getName());
		}

		// Com Localidade
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {

			filtroQualidadeAgua.limparListaParametros();

			colecaoQualidadeAgua = null;
			
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.LOCALIDADE_ID, emitirConta.getIdLocalidade()));
			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA, emitirConta.getAmReferencia()));
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua, QualidadeAgua.class.getName());
		}

		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
			filtroQualidadeAgua.limparListaParametros();
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA, emitirConta.getAmReferencia()));
			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(FiltroQualidadeAgua.LOCALIDADE_ID));
			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua, QualidadeAgua.class.getName());
		}

		if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {

			QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();

			if (qualidadeAgua.getQuantidadeCorExigidas() != null) {
				exigidaCor = qualidadeAgua.getQuantidadeCorExigidas() + "";
			}

			if (qualidadeAgua.getQuantidadeTurbidezExigidas() != null) {
				exigidaTurbidez = qualidadeAgua.getQuantidadeTurbidezExigidas()	+ "";
			}

			if (qualidadeAgua.getQuantidadeFluorExigidas() != null) {
				exigidaFluor = qualidadeAgua.getQuantidadeFluorExigidas() + "";
			}

			if (qualidadeAgua.getQuantidadeCloroExigidas() != null) {
				exigidaCloro = qualidadeAgua.getQuantidadeCloroExigidas() + "";
			}

			if (qualidadeAgua.getQuantidadeColiformesTotaisExigidas() != null) {
				exigidaColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisExigidas() + "";
			}

			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() != null) {
				exigidaColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() + "";
			}

			if (qualidadeAgua.getQuantidadeCorAnalisadas() != null) {
				analisadaCor = qualidadeAgua.getQuantidadeCorAnalisadas() + "";
			}

			if (qualidadeAgua.getQuantidadeTurbidezAnalisadas() != null) {
				analisadaTurbidez = qualidadeAgua.getQuantidadeTurbidezAnalisadas() + "";
			}

			if (qualidadeAgua.getQuantidadeFluorAnalisadas() != null) {
				analisadaFluor = qualidadeAgua.getQuantidadeFluorAnalisadas()+ "";
			}

			if (qualidadeAgua.getQuantidadeCloroAnalisadas() != null) {
				analisadaCloro = qualidadeAgua.getQuantidadeCloroAnalisadas()+ "";
			}

			if (qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas() != null) {
				analisadaColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas() + "";
			}

			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas() != null) {
				analisadaColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas()+ "";
			}

			if (qualidadeAgua.getQuantidadeCorConforme() != null) {
				emConformidadeCor = qualidadeAgua.getQuantidadeCorConforme()+ "";
			}

			if (qualidadeAgua.getQuantidadeTurbidezConforme() != null) {
				emConformidadeTurbidez = qualidadeAgua.getQuantidadeTurbidezConforme() + "";
			}

			if (qualidadeAgua.getQuantidadeFluorConforme() != null) {
				emConformidadeFluor = qualidadeAgua.getQuantidadeFluorConforme() + "";
			}

			if (qualidadeAgua.getQuantidadeCloroConforme() != null) {
				emConformidadeCloro = qualidadeAgua.getQuantidadeCloroConforme() + "";
			}

			if (qualidadeAgua.getQuantidadeColiformesTotaisConforme() != null) {
				emConformidadeColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisConforme() + "";
			}

			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() != null) {
				emConformidadeColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() + "";
			}

			if (qualidadeAgua.getFonteCaptacao() != null && tipoCaptacao.trim().equalsIgnoreCase("")) {
				if (qualidadeAgua.getFonteCaptacao().getTipoCaptacao() != null) {
					tipoCaptacao = qualidadeAgua.getFonteCaptacao().getTipoCaptacao().getId()+ "";
				}
			}

		}

		retornoQualidade[0] = padraoCor;
		retornoQualidade[1] = padraoTurbidez;
		retornoQualidade[2] = padraoFluor;
		retornoQualidade[3] = padraoCloro;
		retornoQualidade[4] = padraoColiformesTotais;
		retornoQualidade[5] = padraoColiformesTermotolerantes;

		retornoQualidade[6] = exigidaCor;
		retornoQualidade[7] = exigidaTurbidez;
		retornoQualidade[8] = exigidaFluor;
		retornoQualidade[9] = exigidaCloro;
		retornoQualidade[10] = exigidaColiformesTotais;
		retornoQualidade[11] = exigidaColiformesTermotolerantes;

		retornoQualidade[12] = analisadaCor;
		retornoQualidade[13] = analisadaTurbidez;
		retornoQualidade[14] = analisadaFluor;
		retornoQualidade[15] = analisadaCloro;
		retornoQualidade[16] = analisadaColiformesTotais;
		retornoQualidade[17] = analisadaColiformesTermotolerantes;

		retornoQualidade[18] = emConformidadeCor;
		retornoQualidade[19] = emConformidadeTurbidez;
		retornoQualidade[20] = emConformidadeFluor;
		retornoQualidade[21] = emConformidadeCloro;
		retornoQualidade[22] = emConformidadeColiformesTotais;
		retornoQualidade[23] = emConformidadeColiformesTermotolerantes;

		retornoQualidade[24] = tipoCaptacao;

		return retornoQualidade;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private QuadraFace obterQuadraFace(Integer idQuadraFace) throws ControladorException {
		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
		filtroQuadraFace.adicionarParametro(new ParametroSimples(FiltroQuadraFace.ID, idQuadraFace));

		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento.fonteCaptacao");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento.fonteCaptacao.tipoCaptacao");

		Collection colecaoQudraFace = getControladorUtil().pesquisar(filtroQuadraFace, QuadraFace.class.getName());

		QuadraFace quadraFace = (QuadraFace) Util.retonarObjetoDeColecao(colecaoQudraFace);
		return quadraFace;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection gerarDebitosACobrarDeAcrescimosPorImpontualidade(
			Collection rotas, Short indicadorGeracaoMulta,
			Short indicadorGeracaoJuros, Short indicadorGeracaoAtualizacao,
			int idFuncionalidadeIniciada, boolean indicadorEncerrandoArrecadacao)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			Rota rota = (Rota) Util.retonarObjetoDeColecao(rotas);

			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.ROTA, rota.getId());

			Collection imoveisPorRota = null;
			Collection colecaoDebitoACobrarInserir = new ArrayList();
			Collection colecaoDebitoACobrarCategoriaInserir = new ArrayList();

			imoveisPorRota = this.pesquisarImovelGerarAcrescimosImpontualidade(rota);

			SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();

			Integer anoMesReferenciaArrecadacao = sistemaParametros.getAnoMesArrecadacao();
			Integer anoMesReferenciaFaturamento = sistemaParametros.getAnoMesFaturamento();
			Short codigoEmpresaFebraban = sistemaParametros.getCodigoEmpresaFebraban();

			Iterator imovelPorRotaIterator = imoveisPorRota.iterator();

			Map<Integer, Categoria> mapImovelPrincipalCategoria = this.pesquisarPrincipalCategoriaImovelPorRota(codigoEmpresaFebraban, rota);

			Map<Integer, Short> mapIndicadorAcrescimoCliente = this.obterIndicadorGeracaoAcrescimosClienteImovel(rota);

			while (imovelPorRotaIterator.hasNext()) {
				Object[] arrayImoveisPorRota = (Object[]) imovelPorRotaIterator.next();

				Imovel imovel = new Imovel();
				if (arrayImoveisPorRota[0] != null) {
					imovel.setId((Integer) arrayImoveisPorRota[0]);
				}
				if (arrayImoveisPorRota[4] != null) {
					imovel.setLote((Short) arrayImoveisPorRota[4]);
				}
				if (arrayImoveisPorRota[5] != null) {
					imovel.setSubLote((Short) arrayImoveisPorRota[5]);
				}

				Localidade localidade = new Localidade();
				if (arrayImoveisPorRota[1] != null) {
					localidade.setId((Integer) arrayImoveisPorRota[1]);
					imovel.setLocalidade(localidade);
				}

				Quadra quadra = new Quadra();
				if (arrayImoveisPorRota[3] != null) {
					Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
					Integer idQuadra = (Integer) arrayImoveisPorRota[7];
					quadra.setId(idQuadra);
					quadra.setNumeroQuadra(numeroQuadra);
					imovel.setQuadra(quadra);
				}

				Integer setorComercial = null;
				if (arrayImoveisPorRota[2] != null) {
					setorComercial = (Integer) arrayImoveisPorRota[2];
				}

				if (arrayImoveisPorRota[8] != null) {
					imovel.setIndicadorDebitoConta((Short) arrayImoveisPorRota[8]);
				}

				Categoria principalCategoria = mapImovelPrincipalCategoria.get(imovel.getId());

				boolean flagProximoImovel = false;

				if (principalCategoria.getIndicadorCobrancaAcrescimos().equals(ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				if ((principalCategoria != null && principalCategoria.getIndicadorCobrancaAcrescimos().equals(ConstantesSistema.ENCERRAMENTO_ARRECADACAO))
						&& !indicadorEncerrandoArrecadacao) {
					flagProximoImovel = true;
				}

				Short indicadorCobrancaAcrescimos = mapIndicadorAcrescimoCliente.get(imovel.getId());
				if (indicadorCobrancaAcrescimos != null	&& indicadorCobrancaAcrescimos.equals(ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				if (indicadorCobrancaAcrescimos != null && (indicadorCobrancaAcrescimos.equals(ConstantesSistema.NAO) && !indicadorEncerrandoArrecadacao)) {
					flagProximoImovel = true;
				}

				if (!flagProximoImovel) {
					Date dataAnoMesReferenciaUltimoDia = Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaArrecadacao);

					Collection<Integer> colecaoIdsContasAtualizarIndicadorMulta = new ArrayList();

					Collection colecaoContaImovel = null;

					if (!indicadorEncerrandoArrecadacao) {
						colecaoContaImovel = repositorioFaturamento
								.obterContasImovel(imovel.getId(),
										DebitoCreditoSituacao.NORMAL,
										DebitoCreditoSituacao.INCLUIDA,
										DebitoCreditoSituacao.RETIFICADA,
										dataAnoMesReferenciaUltimoDia);
					} else {
						colecaoContaImovel = repositorioFaturamento
								.obterContasImovelComPagamento(imovel.getId(),
										DebitoCreditoSituacao.NORMAL,
										DebitoCreditoSituacao.INCLUIDA,
										DebitoCreditoSituacao.RETIFICADA,
										dataAnoMesReferenciaUltimoDia,
										anoMesReferenciaArrecadacao);
					}

					Map<Integer, Boolean> mapIndicadorExistePagamentoConta = this.pesquisarIndicadorPagamentoConta(
									colecaoContaImovel,	anoMesReferenciaArrecadacao);

					Short numeroPrestacaoDebito = 1;
					Short numeroPrestacaoCobradas = 0;

					if (!Util.isVazioOrNulo(colecaoContaImovel)) {

						Iterator contasIterator = colecaoContaImovel.iterator();

						while (contasIterator.hasNext()) {
							Object[] dadosConta = (Object[]) contasIterator.next();

							Integer anoMes = Util.recuperaAnoMesDaData((Date) dadosConta[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								Integer idConta = (Integer) dadosConta[0];
								Conta conta = new Conta();
								if (dadosConta[0] != null) {
									conta.setId((Integer) dadosConta[0]);
								}
								if (dadosConta[1] != null) {
									conta.setReferencia((Integer) dadosConta[1]);
								}
								if (dadosConta[2] != null) {
									conta.setDataVencimentoConta((Date) dadosConta[2]);
								}
								if (dadosConta[3] != null) {
									conta.setValorAgua((BigDecimal) dadosConta[3]);
								}
								if (dadosConta[4] != null) {
									conta.setValorEsgoto((BigDecimal) dadosConta[4]);
								}
								if (dadosConta[5] != null) {
									conta.setDebitos((BigDecimal) dadosConta[5]);
								}
								if (dadosConta[6] != null) {
									conta.setValorCreditos((BigDecimal) dadosConta[6]);
								}
								if (dadosConta[7] != null) {
									conta.setIndicadorCobrancaMulta((Short) dadosConta[7]);
								}

								Date pagamentoContasMenorData = null;
								Integer idArrecadacaoForma = null;

								Object[] arrayPagamentoContasMenorData = repositorioFaturamento
										.obterArrecadacaoFormaPagamentoContasMenorData(
												idConta, imovel.getId(), conta.getReferencia());

								if (arrayPagamentoContasMenorData != null) {
									idArrecadacaoForma = (Integer) arrayPagamentoContasMenorData[0];
									pagamentoContasMenorData = (Date) arrayPagamentoContasMenorData[1];
								}

								if (idArrecadacaoForma == null
										|| (idArrecadacaoForma != null && !idArrecadacaoForma.equals(ArrecadacaoForma.DEBITO_AUTOMATICO))) {

									boolean indicadorExistePagamentoClassificadoConta;
									if (mapIndicadorExistePagamentoConta.containsKey(idConta)) {
										indicadorExistePagamentoClassificadoConta = true;
									} else {
										indicadorExistePagamentoClassificadoConta = false;
									}

									CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();

									BigDecimal valorConta = conta
											.getValorAgua()
											.add(conta.getValorEsgoto())
											.add(conta.getDebitos())
											.subtract(conta.getValorCreditos());

									BigDecimal valorMultasCobradas = repositorioFaturamento.pesquisarValorMultasCobradas(idConta);

									calcularAcrescimoPorImpontualidade = this.getControladorCobranca()
											.calcularAcrescimoPorImpontualidade(
													conta.getReferencia(),
													conta.getDataVencimentoConta(),
													pagamentoContasMenorData,
													valorConta,
													valorMultasCobradas,
													conta.getIndicadorCobrancaMulta(),
													"" + anoMesReferenciaArrecadacao,
													conta.getId(),
													ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO);

									DebitoTipo debitoTipo = null;

									if (indicadorGeracaoMulta.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade.getValorMulta().compareTo(BigDecimal.ZERO) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														anoMesReferenciaFaturamento,
														imovel,
														localidade,
														quadra,
														setorComercial,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade.getValorMulta(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta.add(conta.getId());

										colecaoDebitoACobrarInserir.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir.addAll(
												this.inserirDebitoACobrarCategoriaBatch(
														debitoACobrar,debitoACobrar.getImovel()));
									}

									if (indicadorGeracaoJuros.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade.getValorJurosMora().compareTo(BigDecimal.ZERO) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.JUROS_MORA);

										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														anoMesReferenciaFaturamento,
														imovel,
														localidade,
														quadra,
														setorComercial,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade.getValorJurosMora(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta.add(conta.getId());

										colecaoDebitoACobrarInserir.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir.addAll(
												this.inserirDebitoACobrarCategoriaBatch(
														debitoACobrar,debitoACobrar.getImovel()));
									}

									if (indicadorGeracaoAtualizacao.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria().compareTo(BigDecimal.ZERO) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														anoMesReferenciaFaturamento,
														imovel,
														localidade,
														quadra,
														setorComercial,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta.add(conta.getId());

										colecaoDebitoACobrarInserir.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir.addAll(this
												.inserirDebitoACobrarCategoriaBatch(
														debitoACobrar,debitoACobrar.getImovel()));
									}
								}
							} 
						} 
					} 

					if (colecaoIdsContasAtualizarIndicadorMulta != null && !colecaoIdsContasAtualizarIndicadorMulta.isEmpty()) {
						repositorioFaturamento.atualizarIndicadorMultaDeConta(colecaoIdsContasAtualizarIndicadorMulta);
					}

					Collection colecaoGuiasPagamentoImovel = null;
					Collection<Integer> colecaoIdsGuiasPagamentosAtualizarIndicadorMulta = new ArrayList();

					colecaoGuiasPagamentoImovel = repositorioFaturamento
							.obterGuiasPagamentoImovel(imovel.getId(),
									DebitoCreditoSituacao.NORMAL,
									DebitoCreditoSituacao.INCLUIDA,
									DebitoCreditoSituacao.RETIFICADA,
									anoMesReferenciaArrecadacao);

					if (!Util.isVazioOrNulo(colecaoGuiasPagamentoImovel)) {

						Iterator guiasPagamentoIterator = colecaoGuiasPagamentoImovel.iterator();

						while (guiasPagamentoIterator.hasNext()) {
							Object[] dadosGuiasPagamento = (Object[]) guiasPagamentoIterator.next();

							Integer anoMes = Util.recuperaAnoMesDaData((Date) dadosGuiasPagamento[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								GuiaPagamento guiaPagamento = new GuiaPagamento();
								if (dadosGuiasPagamento[0] != null) {
									guiaPagamento.setId((Integer) dadosGuiasPagamento[0]);
								}
								if (dadosGuiasPagamento[1] != null) {
									guiaPagamento.setAnoMesReferenciaContabil((Integer) dadosGuiasPagamento[1]);
								}
								if (dadosGuiasPagamento[2] != null) {
									guiaPagamento.setDataVencimento((Date) dadosGuiasPagamento[2]);
								}
								if (dadosGuiasPagamento[3] != null) {
									guiaPagamento.setValorDebito((BigDecimal) dadosGuiasPagamento[3]);
								}
								if (dadosGuiasPagamento[4] != null) {
									guiaPagamento.setIndicadoCobrancaMulta((Short) dadosGuiasPagamento[4]);
								}

								DebitoTipo debitoTipoGuiaPagamento = new DebitoTipo();
								if (dadosGuiasPagamento[5] != null) {
									debitoTipoGuiaPagamento.setId((Integer) dadosGuiasPagamento[5]);
									guiaPagamento.setDebitoTipo(debitoTipoGuiaPagamento);
								}

								Date menorDataPagamento = repositorioCobranca
										.pesquisarMenorDataPagamentoGuiaPagamento(
												guiaPagamento.getId(), imovel
												.getId(), guiaPagamento.getDebitoTipo().getId());

								boolean indicadorExistePagamentoClassificadoGuiaPagamento = repositorioFaturamento
										.obterIndicadorPagamentosClassificadosGuiaPagamentoReferenciaMenorIgualAtual(
												guiaPagamento.getId(), imovel
														.getId(), guiaPagamento
														.getDebitoTipo()
														.getId(),
												anoMesReferenciaArrecadacao);

								CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();
								calcularAcrescimoPorImpontualidade = this
										.getControladorCobranca()
										.calcularAcrescimoPorImpontualidade(
												guiaPagamento.getAnoMesReferenciaContabil(),
												guiaPagamento.getDataVencimento(),
												menorDataPagamento,
												guiaPagamento.getValorDebito(),
												BigDecimal.ZERO,
												guiaPagamento.getIndicadoCobrancaMulta(),
												""+ anoMesReferenciaArrecadacao,
												null,
												ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO);

								DebitoTipo debitoTipo = null;

								if (indicadorGeracaoMulta.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade.getValorMulta().compareTo(BigDecimal.ZERO) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel, localidade, quadra,
											setorComercial,
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade.getValorMulta(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir.addAll(this
											.inserirDebitoACobrarCategoriaBatch(
													debitoACobrar,debitoACobrar.getImovel()));
								}

								if (indicadorGeracaoJuros.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade.getValorJurosMora().compareTo(BigDecimal.ZERO) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.JUROS_MORA);

									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel, localidade, quadra,
											setorComercial,
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade.getValorJurosMora(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir.addAll(this
											.inserirDebitoACobrarCategoriaBatch(
												debitoACobrar,debitoACobrar.getImovel()));
								}

								if (indicadorGeracaoAtualizacao.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria().compareTo(BigDecimal.ZERO) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel,
											localidade,
											quadra,
											setorComercial,
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir.addAll(this
											.inserirDebitoACobrarCategoriaBatch(
												debitoACobrar,debitoACobrar.getImovel()));
								}
							}
						}
					}

					if (colecaoIdsGuiasPagamentosAtualizarIndicadorMulta != null
							&& !colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.isEmpty()) {
						repositorioFaturamento.atualizarIndicadorMultaDeGuiaPagamento(colecaoIdsGuiasPagamentosAtualizarIndicadorMulta);
					}
				}
			}

			if (colecaoDebitoACobrarInserir != null && !colecaoDebitoACobrarInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(colecaoDebitoACobrarInserir);
			}

			if (colecaoDebitoACobrarCategoriaInserir != null && !colecaoDebitoACobrarCategoriaInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(colecaoDebitoACobrarCategoriaInserir);
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

			return null;

		} catch (Exception e) {
			logger.error(e);
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<EmitirContaHelper> emitir2ViaContas(Collection idsContaEP, boolean cobrarTaxaEmissaoConta,
			Short contaSemCodigoBarras) throws ControladorException {

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = new ArrayList();

		Iterator iter = idsContaEP.iterator();

		while (iter.hasNext()) {
			Integer idContaEP = (Integer) iter.next();

			Collection colectionConta;
			try {
				colectionConta = this.repositorioFaturamento.pesquisarConta(idContaEP);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) colectionConta.iterator().next();

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			emitirContaHelper = preencherNomeCliente2Via(emitirContaHelper);
			emitirContaHelper = preencherDadosEnderecoImovel2Via(emitirContaHelper);
			emitirContaHelper= preencherInscricaoImovel2Via(emitirContaHelper);
			emitirContaHelper = preencherDadosClienteResponsavel2Via(emitirContaHelper);

			if (emitirContaHelper.getIdImovelContaEnvio() != null && emitirContaHelper.getIdImovelContaEnvio().equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO)) {
				emitirContaHelper.setClienteComFaturaAgrupada(new Short("1"));
			} else {
				emitirContaHelper.setClienteComFaturaAgrupada(new Short("2"));
			}

			Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
			Integer tipoLigacao = parmSituacao[0];
			Integer tipoMedicao = parmSituacao[1];

			emitirContaHelper.setDadosConsumoMes1(obterDadosConsumoAnterior(emitirContaHelper, 1, tipoLigacao, tipoMedicao).toString());
			emitirContaHelper.setDadosConsumoMes2(obterDadosConsumoAnterior(emitirContaHelper, 2, tipoLigacao, tipoMedicao).toString());
			emitirContaHelper.setDadosConsumoMes3(obterDadosConsumoAnterior(emitirContaHelper, 3, tipoLigacao, tipoMedicao).toString());
			emitirContaHelper.setDadosConsumoMes4(obterDadosConsumoAnterior(emitirContaHelper, 4, tipoLigacao, tipoMedicao).toString());
			emitirContaHelper.setDadosConsumoMes5(obterDadosConsumoAnterior(emitirContaHelper, 5, tipoLigacao, tipoMedicao).toString());
			emitirContaHelper.setDadosConsumoMes6(obterDadosConsumoAnterior(emitirContaHelper, 6, tipoLigacao, tipoMedicao).toString());

			Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
			
			String leituraAnterior = "";
			String leituraAtual = "";
			String dataLeituraAnterior = "";
			String dataLeituraAtual = "";
			String leituraAnormalidadeFaturamento = "";
			String descricaoAbreviadaLeituraAnormalidade = "";
			if (parmsMedicaoHistorico != null) {

				if (parmsMedicaoHistorico[0] != null) {
					leituraAnterior = "" + (Integer) parmsMedicaoHistorico[0];
				}
				if (parmsMedicaoHistorico[1] != null) {
					leituraAtual = "" + (Integer) parmsMedicaoHistorico[1];
				}
				if (parmsMedicaoHistorico[3] != null) {
					dataLeituraAnterior = Util.formatarData((Date) parmsMedicaoHistorico[3]);
				}
				if (parmsMedicaoHistorico[2] != null) {
					dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
				}
				if (parmsMedicaoHistorico[5] != null) {
					leituraAnormalidadeFaturamento = "" + (Integer) parmsMedicaoHistorico[5];
				}
				if (parmsMedicaoHistorico[7] != null) {
					descricaoAbreviadaLeituraAnormalidade = "" 	+ (String) parmsMedicaoHistorico[7];
				}
			}
			
			emitirContaHelper.setDataLeituraAnterior(dataLeituraAnterior);
			emitirContaHelper.setDataLeituraAtual(dataLeituraAtual);
			emitirContaHelper.setDescricaoAbreviadaLeituraAnormalidade(descricaoAbreviadaLeituraAnormalidade);
			emitirContaHelper.setLeituraAnormalidade(leituraAnormalidadeFaturamento);

			String diasConsumo = "";
			if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
				diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parmsMedicaoHistorico[3], (Date) parmsMedicaoHistorico[2]);
			}

			String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(emitirContaHelper, tipoMedicao, diasConsumo);
			String consumoFaturamento = parmsConsumo[0];
			
			emitirContaHelper.setConsumoFaturamento(parmsConsumo[0]);
			emitirContaHelper.setConsumoMedioDiario(parmsConsumo[1]);
			emitirContaHelper.setLeituraAnterior(Util.completaString(leituraAnterior, 7));
			emitirContaHelper.setLeituraAtual(Util.completaString(leituraAtual, 7));
			emitirContaHelper.setDiasConsumo(Util.completaString(diasConsumo, 2));

			Object[] parmsConsumoHistorico = null;
			String descricaoAbreviadaTipoConsumo = "";
			String descricaoTipoConsumo = "";
			String consumoMedio = "";
			String descricaoAbreviadaAnormalidadeConsumo = "";
			String descricaoAnormalidadeConsumo = "";
			String consumoRateio = "";
			if (tipoLigacao != null) {
				try {
					parmsConsumoHistorico = getControladorMicromedicao()
							.obterDadosConsumoConta(
									emitirContaHelper.getIdImovel(),
									emitirContaHelper.getAmReferencia(),
									tipoLigacao);
				} catch (ControladorException e) {
					logger.error(e);
				}

				if (parmsConsumoHistorico != null) {
					if (parmsConsumoHistorico[0] != null) {
						descricaoAbreviadaTipoConsumo = (String) parmsConsumoHistorico[0];
					}
					if (parmsConsumoHistorico[1] != null) {
						descricaoTipoConsumo = (String) parmsConsumoHistorico[1];
					}
					if (parmsConsumoHistorico[2] != null) {
						consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
					}
					if (parmsConsumoHistorico[3] != null) {
						descricaoAbreviadaAnormalidadeConsumo = (String) parmsConsumoHistorico[3];
					}
					if (parmsConsumoHistorico[4] != null) {
						descricaoAnormalidadeConsumo = (String) parmsConsumoHistorico[4];
					}
					if (parmsConsumoHistorico[5] != null) {
						consumoRateio = "" + (Integer) parmsConsumoHistorico[5];
					}
				}
			}

			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);
			emitirContaHelper.setConsumoAnormalidade(descricaoAbreviadaAnormalidadeConsumo);

			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(emitirContaHelper.getIdConta(), false);
			emitirContaHelper.setQuantidadeEconomiaConta(""+ quantidadeEconomiaConta);
			
			BigDecimal consumoFaturadoBigDecimal = null;
			if (consumoFaturamento != null && !consumoFaturamento.equals("")) {
				consumoFaturadoBigDecimal = Util.formatarMoedaRealparaBigDecimal(consumoFaturamento);

			}
			BigDecimal qtdEconomiasBigDecimal = null;
			if (quantidadeEconomiaConta != null && !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util.formatarMoedaRealparaBigDecimal(""+ quantidadeEconomiaConta);
			}
			
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null && qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util.formatarMoedaReal(consumoEconomiaBigDecimal);
				emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(0, (consumoEconomia.length() - 3)));
			}

			StringBuilder codigoAuxiliar = new StringBuilder();
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaTipoConsumo, 1));
			codigoAuxiliar.append(Util.completaString("", 1));
			codigoAuxiliar.append(Util.completaString(leituraAnormalidadeFaturamento, 2));
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaAnormalidadeConsumo, 2));

			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString(""+ emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}

			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			
			emitirContaHelper.setCodigoAuxiliarString(codigoAuxiliar.toString());

			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao);
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo.toString());

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = gerarLinhasDescricaoServicoTarifasRelatorio(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao, false);
			emitirContaHelper.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

			BigDecimal valorConta = obterValorConta2Via(emitirContaHelper);

			emitirContaHelper.setValorContaString(Util.formatarMoedaReal(valorConta));
			emitirContaHelper.setValorConta(valorConta);
			emitirContaHelper = preencherDadosPagamento2Via(idContaEP, emitirContaHelper, valorConta);
			emitirContaHelper = preencherInfoCodigoBarras2Via(emitirContaHelper, valorConta);
			emitirContaHelper = preencherMensagensConta2Via(emitirContaHelper, sistemaParametro);
			emitirContaHelper.setMesAnoFormatado(Util.formatarAnoMesParaMesAno(obterMesConsumoAnteriorFormatado(emitirContaHelper, 1)));
			emitirContaHelper = preencherDadosQualidadeAgua2Via(emitirContaHelper);
			emitirContaHelper = preencherRepresentacaoNumericaCodBarras2Via(emitirContaHelper, valorConta);

			colecaoEmitirContaHelper.add(emitirContaHelper);

			if (cobrarTaxaEmissaoConta) {
				this.gerarDebitoACobrarTaxaEmissaoConta(emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia());
			}

			String leituraAnteriorInformada = "";
			String leituraAtualInformada = "";
			String dataLeituraAnteriorInformada = "";
			String dataLeituraAtualInformada = "";

			MedicaoHistorico medicaoHistoricoAgua = getControladorMicromedicao()
					.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(
							emitirContaHelper.getIdImovel(),
							emitirContaHelper.getAmReferencia());
			
			MedicaoHistorico medicaoHistoricoPoco = getControladorMicromedicao()
					.pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(
							emitirContaHelper.getIdImovel(),
							emitirContaHelper.getAmReferencia());


			if (medicaoHistoricoAgua != null) {

				MedicaoHistorico medicaoHistoricoAguaMesAnterior = getControladorMicromedicao()
						.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(
								emitirContaHelper.getIdImovel(),
								Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), 1));
				
				if (medicaoHistoricoAgua.getLeituraAnteriorInformada() != null) {
					leituraAnteriorInformada = medicaoHistoricoAgua.getLeituraAnteriorInformada() + "";
				}
				if (medicaoHistoricoAgua.getLeituraAtualInformada() != null) {
					leituraAtualInformada = medicaoHistoricoAgua.getLeituraAtualInformada() + "";
				}
				if (medicaoHistoricoAgua.getDataLeituraAtualInformada() != null) {
					dataLeituraAtualInformada = Util.formatarData(medicaoHistoricoAgua.getDataLeituraAtualInformada());
				}
				if (medicaoHistoricoAguaMesAnterior != null) {
					if (medicaoHistoricoAguaMesAnterior.getDataLeituraAtualInformada() != null) {
						dataLeituraAnteriorInformada = Util.formatarData(medicaoHistoricoAguaMesAnterior.getDataLeituraAtualInformada());
					}
				}
				
			} else if (medicaoHistoricoPoco != null) {
				
				MedicaoHistorico medicaoHistoricoPocoMesAnterior = getControladorMicromedicao()
						.pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(
								emitirContaHelper.getIdImovel(),
								Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), 1));
				

				if (medicaoHistoricoPoco.getLeituraAnteriorInformada() != null) {
					leituraAnteriorInformada = medicaoHistoricoPoco.getLeituraAnteriorInformada() + "";
				}
				if (medicaoHistoricoPoco.getLeituraAtualInformada() != null) {
					leituraAtualInformada = medicaoHistoricoPoco.getLeituraAtualInformada() + "";
				}
				if (medicaoHistoricoPoco.getDataLeituraAtualInformada() != null) {
					dataLeituraAtualInformada = Util.formatarData(medicaoHistoricoPoco.getDataLeituraAtualInformada());
				}
				if (medicaoHistoricoPocoMesAnterior.getDataLeituraAtualInformada() != null) {
					dataLeituraAnteriorInformada = Util.formatarData(medicaoHistoricoPocoMesAnterior.getDataLeituraAtualInformada());
				}
			}
			emitirContaHelper.setLeituraAnteriorInformada(leituraAnteriorInformada);
			emitirContaHelper.setLeituraAtualInformada(leituraAtualInformada);
			emitirContaHelper.setDataLeituraAnteriorInformada(dataLeituraAnteriorInformada);
			emitirContaHelper.setDataLeituraAtualInformada(dataLeituraAtualInformada);

		}

		return colecaoEmitirContaHelper;
	}

	private EmitirContaHelper preencherDadosEnderecoImovel2Via(EmitirContaHelper emitirContaHelper) {
		String enderecoImovel = "";
		try {
			enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatado(emitirContaHelper.getIdImovel());
		} catch (ControladorException e1) {
			logger.error(e1);
		}
		emitirContaHelper.setEnderecoImovel(enderecoImovel);
		return emitirContaHelper;
	}

	private EmitirContaHelper preencherDadosClienteResponsavel2Via(EmitirContaHelper emitirContaHelper) throws ControladorException {
		String idClienteResponsavel = "";
		String enderecoClienteResponsavel = "";
		Integer idImovelContaEnvio = emitirContaHelper.getIdImovelContaEnvio();
		
		if (idImovelContaEnvio != null
				&& (idImovelContaEnvio
						.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) || idImovelContaEnvio
						.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {

			Integer idClienteResponsavelInteger = null;
			idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(emitirContaHelper.getIdConta(), false);

			if (idClienteResponsavelInteger != null && !idClienteResponsavelInteger.equals("")) {
				idClienteResponsavel = idClienteResponsavelInteger.toString();
				enderecoClienteResponsavel = getControladorEndereco().pesquisarEnderecoClienteAbreviado(idClienteResponsavelInteger);
			}

		}
		emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
		emitirContaHelper.setEnderecoClienteResponsavel(enderecoClienteResponsavel);
		
		return emitirContaHelper;
	}

	private EmitirContaHelper preencherInscricaoImovel2Via(EmitirContaHelper emitirContaHelper) {
		Localidade localidade = new Localidade(emitirContaHelper.getIdLocalidade());
		
		SetorComercial setorComercial = new SetorComercial();
		setorComercial.setCodigo(emitirContaHelper.getCodigoSetorComercialConta());
		
		Quadra quadra = new Quadra();
		quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());

		Imovel imovel = new Imovel(localidade, setorComercial, quadra, emitirContaHelper.getLoteConta(), emitirContaHelper.getSubLoteConta());
		
		emitirContaHelper.setInscricaoImovel(imovel.getInscricaoFormatada());
		return emitirContaHelper;
	}

	private EmitirContaHelper preencherNomeCliente2Via(EmitirContaHelper emitirContaHelper) {
		String nomeCliente = "";
		if (emitirContaHelper.getNomeCliente() == null || emitirContaHelper.getNomeCliente().trim().equals("")) {

			if (emitirContaHelper.getNomeImovel() != null && !emitirContaHelper.getNomeImovel().trim().equals("")) {
				nomeCliente = emitirContaHelper.getNomeImovel();
			}

			emitirContaHelper.setNomeCliente(nomeCliente);
		}
		return emitirContaHelper;
	}

	private BigDecimal obterValorConta2Via(EmitirContaHelper emitirContaHelper) {
		Conta conta = new Conta(emitirContaHelper.getValorAgua(), emitirContaHelper.getValorEsgoto(), emitirContaHelper.getValorCreditos(),
				emitirContaHelper.getDebitos(), emitirContaHelper.getValorImpostos());

		BigDecimal valorConta = conta.getValorTotal();
		return valorConta;
	}

	private EmitirContaHelper preencherMensagensConta2Via(EmitirContaHelper emitirContaHelper, SistemaParametro sistemaParametro) throws ControladorException {
		String[] parmsPartesConta = obterMensagemConta3Partes(emitirContaHelper, sistemaParametro);
		emitirContaHelper.setPrimeiraParte(parmsPartesConta[0]);
		emitirContaHelper.setSegundaParte(parmsPartesConta[1]);
		emitirContaHelper.setTerceiraParte(parmsPartesConta[2]);
		return emitirContaHelper;
	}

	private EmitirContaHelper preencherRepresentacaoNumericaCodBarras2Via(EmitirContaHelper emitirContaHelper, BigDecimal valorConta) throws ControladorException {
		Integer digitoVerificadorConta = new Integer(""+ emitirContaHelper.getDigitoVerificadorConta());

		String anoMes = "" + emitirContaHelper.getAmReferencia();
		String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

		String representacaoNumericaCodBarra = "";
		
		Date dataValidade = obterDataValidade2ViaConta(emitirContaHelper);
		emitirContaHelper.setDataValidade(Util.formatarData(dataValidade));

		if (emitirContaHelper.getContaSemCodigoBarras().equals("2")) {

			representacaoNumericaCodBarra = this
					.getControladorArrecadacao()
					.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
							emitirContaHelper.getIdLocalidade(),
							emitirContaHelper.getIdImovel(), mesAno,
							digitoVerificadorConta, null, null, null, null,
							null, null, null);

			String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra.substring(0, 11)
					+ "-" + representacaoNumericaCodBarra.substring(11, 12)
					+ " " + representacaoNumericaCodBarra.substring(12, 23)
					+ "-" + representacaoNumericaCodBarra.substring(23, 24)
					+ " " + representacaoNumericaCodBarra.substring(24, 35)
					+ "-" + representacaoNumericaCodBarra.substring(35, 36)
					+ " " + representacaoNumericaCodBarra.substring(36, 47)
					+ "-" + representacaoNumericaCodBarra.substring(47, 48);
			emitirContaHelper.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

			String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
					.substring(0, 11)
					+ representacaoNumericaCodBarra.substring(12, 23)
					+ representacaoNumericaCodBarra.substring(24, 35)
					+ representacaoNumericaCodBarra.substring(36, 47);
			emitirContaHelper.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);

		}
		
		return emitirContaHelper;
	}

	private EmitirContaHelper preencherDadosQualidadeAgua2Via(EmitirContaHelper emitirContaHelper) throws ControladorException {
		Object[] parmsQualidadeAgua = null;
		parmsQualidadeAgua = pesquisarParmsQualidadeAgua(emitirContaHelper);
		Imovel imovelQuadraFace = getControladorImovel().pesquisarImovel(emitirContaHelper.getIdImovel());
		String[] qualidadeAgua = this.obterDadosQualidadeAguaCosanpa(emitirContaHelper, imovelQuadraFace.getQuadraFace().getId());

		if (parmsQualidadeAgua != null) {
			if (parmsQualidadeAgua[0] != null) {
				emitirContaHelper.setNumeroIndiceTurbidez(Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]));
			}

			if (parmsQualidadeAgua[1] != null) {
				emitirContaHelper.setNumeroCloroResidual(Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]));
			}
		}
		// Padr�o
		emitirContaHelper.setPadraoCor(qualidadeAgua[0]);
		emitirContaHelper.setPadraoTurbidez(qualidadeAgua[1]);
		emitirContaHelper.setPadraoCloro(qualidadeAgua[3]);
		emitirContaHelper.setPadraoFluor(qualidadeAgua[2]);
		emitirContaHelper.setPadraoColiformesTotais(qualidadeAgua[4]);
		emitirContaHelper.setPadraoColiformesfecais(qualidadeAgua[5]);

		// Exigido
		emitirContaHelper.setValorExigidoCor(qualidadeAgua[6]);
		emitirContaHelper.setValorExigidoTurbidez(qualidadeAgua[7]);
		emitirContaHelper.setValorExigidoCloro(qualidadeAgua[9]);
		emitirContaHelper.setValorExigidoFluor(qualidadeAgua[8]);
		emitirContaHelper.setValorExigidoColiformesTotais(qualidadeAgua[10]);
		emitirContaHelper.setValorExigidoColiformesTermotolerantes(qualidadeAgua[11]);

		// Analisado
		emitirContaHelper.setValorMedioCor(qualidadeAgua[12]);
		emitirContaHelper.setValorMedioTurbidez(qualidadeAgua[13]);
		emitirContaHelper.setValorMedioCloro(qualidadeAgua[15]);
		emitirContaHelper.setValorMedioFluor(qualidadeAgua[14]);
		emitirContaHelper.setValorMedioColiformesTotais(qualidadeAgua[16]);
		emitirContaHelper.setValorMedioColiformesfecais(qualidadeAgua[17]);

		// Conforme
		emitirContaHelper.setValorConformeCor(qualidadeAgua[18]);
		emitirContaHelper.setValorConformeTurbidez(qualidadeAgua[19]);
		emitirContaHelper.setValorConformeCloro(qualidadeAgua[21]);
		emitirContaHelper.setValorConformeFluor(qualidadeAgua[20]);
		emitirContaHelper.setValorConformeColiformesTotais(qualidadeAgua[22]);
		emitirContaHelper.setValorConformeColiformesTermotolerantes(qualidadeAgua[23]);

		return emitirContaHelper;
	}

	private EmitirContaHelper preencherInfoCodigoBarras2Via(EmitirContaHelper emitirContaHelper, BigDecimal valorConta) {
		if (valorConta.compareTo(new BigDecimal("0.00")) == 0
				|| emitirContaHelper.getClienteComFaturaAgrupada().equals(new Short("1"))
				|| emitirContaHelper.getContaPaga().equals("1")) {
			emitirContaHelper.setContaSemCodigoBarras("1");
		} else {
			emitirContaHelper.setContaSemCodigoBarras("2");
		}
		
		return emitirContaHelper;
	}

	private EmitirContaHelper preencherDadosPagamento2Via(Integer idContaEP, EmitirContaHelper emitirContaHelper, BigDecimal valorConta)
			throws ControladorException {
		Pagamento pagamento = getControladorArrecadacao().pesquisarPagamentoDeConta(idContaEP);
		
		if (pagamento != null && pagamento.getValorPagamento().compareTo(valorConta) >= 0) {
			emitirContaHelper.setContaPaga("1");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			emitirContaHelper.setDataPagamentoConta(sdf.format(pagamento.getDataPagamento()));
		} else {
			emitirContaHelper.setContaPaga("2");
			emitirContaHelper.setDataPagamentoConta("");
		}
		
		return emitirContaHelper;
	}

	public String[] obterMensagemDebitoConta3Partes(EmitirContaHelper emitirContaHelper, SistemaParametro sistemaParametro) throws ControladorException {

		String[] linhasImpostosRetidos = new String[3];
		Integer anoMesReferenciaFinal = sistemaParametro.getAnoMesFaturamento();
		Integer dataVencimentoFinalInteger = sistemaParametro.getAnoMesArrecadacao();
		String anoMesSubtraidoString = ""+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
		int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
		int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

		Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
		dataVencimentoFinal.set(Calendar.YEAR, ano);
		dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
		dataVencimentoFinal.set(Calendar.DAY_OF_MONTH,dataVencimentoFinal.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date dataFinalDate = dataVencimentoFinal.getTime();

		Date dataVencimento = Util.converteStringParaDate("01/01/1900");

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
				.obterDebitoImovelOuCliente(1,
						"" + emitirContaHelper.getIdImovel(), null, null,
						"190001", "" + anoMesSubtraido, dataVencimento,
						dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null);

		if (debitoImovelClienteHelper != null
				&& ((debitoImovelClienteHelper.getColecaoGuiasPagamentoValores() != null && !debitoImovelClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()) 
					|| (debitoImovelClienteHelper.getColecaoContasValores() != null && !debitoImovelClienteHelper.getColecaoContasValores().isEmpty()))) {
			String dataVencimentoFinalString = Util.formatarData(dataFinalDate);
			linhasImpostosRetidos[0] = "SR. USU�RIO: EM  " + dataVencimentoFinalString
					+ ",    REGISTRAMOS QUE V.SA. ESTAVA EM D�BITO COM A "
					+ sistemaParametro.getNomeAbreviadoEmpresa().toUpperCase() + ".";
			linhasImpostosRetidos[1] = "COMPARE�A A UM DOS NOSSOS POSTOS DE ATENDIMENTO PARA REGULARIZAR SUA SITUACAO.EVITE O CORTE.";
			linhasImpostosRetidos[2] = "CASO O SEU D�BITO TENHA SIDO PAGO AP�S A DATA INDICADA,DESCONSIDERE ESTE AVISO.";

		} else {
			linhasImpostosRetidos[0] = "A COSANPA AGRADECE SUA PONTUALIDADE.";
			linhasImpostosRetidos[1] = "MUITO OBRIGADO.";
			linhasImpostosRetidos[2] = "";
		}

		return linhasImpostosRetidos;
	}

	public String[] obterMensagemConta3Partes(EmitirContaHelper emitirConta, SistemaParametro sistemaParametro) throws ControladorException {

		String[] linhasImpostosRetidos = new String[3];
		linhasImpostosRetidos = obterMensagemAnormalidadeConsumo(emitirConta);

		Imovel imovel = new Imovel();
		imovel.setId(emitirConta.getIdImovel());

		String msgQuitacaoAnualDebitos = this.obterMsgQuitacaoDebitos(imovel, sistemaParametro.getAnoMesFaturamento());

		if (msgQuitacaoAnualDebitos != null
				&& !msgQuitacaoAnualDebitos.equals("")) {
			linhasImpostosRetidos = new String[3];

			linhasImpostosRetidos[0] = msgQuitacaoAnualDebitos.substring(0, 100);
			linhasImpostosRetidos[1] = msgQuitacaoAnualDebitos.substring(100,msgQuitacaoAnualDebitos.length());
			linhasImpostosRetidos[2] = "";
		} else if (linhasImpostosRetidos == null || linhasImpostosRetidos.equals("")) {
			linhasImpostosRetidos = new String[3];
			Object[] mensagensConta = null;

			boolean existeMensagem = false;
			try {
				//Pesquisa mensagens por gerencia regional, localidade e setor comercial
				mensagensConta = repositorioFaturamento.pesquisarParmsContaMensagem(emitirConta, null,
						emitirConta.getIdGerenciaRegional(), emitirConta.getIdLocalidade(), emitirConta.getIdSetorComercial());
				if (mensagensConta != null) {
					linhasImpostosRetidos = obterMensagensImpostosRetidos(mensagensConta);
					existeMensagem = true;
				}

				if (!existeMensagem) {
					//Pesquisa mensagens por gerencia regional e localidade
					mensagensConta = repositorioFaturamento.pesquisarParmsContaMensagem(emitirConta,
									null, emitirConta.getIdGerenciaRegional(), emitirConta.getIdLocalidade(), null);
					if (mensagensConta != null) {
						linhasImpostosRetidos = obterMensagensImpostosRetidos(mensagensConta);
						existeMensagem = true;
					}
				}
				if (!existeMensagem) {
					//Pesquisa mensagens por gerencia regional
					mensagensConta = repositorioFaturamento.pesquisarParmsContaMensagem(emitirConta,
									null, emitirConta.getIdGerenciaRegional(), null, null);

					if (mensagensConta != null) {
						linhasImpostosRetidos = obterMensagensImpostosRetidos(mensagensConta);
						existeMensagem = true;
					}
				}
				if (!existeMensagem) {
					//Pesquisa mensagens por grupo de faturamento
					mensagensConta = repositorioFaturamento.pesquisarParmsContaMensagem(emitirConta,
							emitirConta.getIdFaturamentoGrupo(), null, null, null);

					if (mensagensConta != null) {
						linhasImpostosRetidos = obterMensagensImpostosRetidos(mensagensConta);
						existeMensagem = true;
					}
				}
				if (!existeMensagem) {
					//Pesquisa mensagens sem parametro
					mensagensConta = repositorioFaturamento.pesquisarParmsContaMensagem(emitirConta,null, null, null, null);
					if (mensagensConta != null) {
						linhasImpostosRetidos = obterMensagensImpostosRetidos(mensagensConta);
						existeMensagem = true;
					}
				}
				if (!existeMensagem) {
					linhasImpostosRetidos[0] = "";
					linhasImpostosRetidos[1] = "";
					linhasImpostosRetidos[2] = "";
				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}
		return linhasImpostosRetidos;
	}
	
	private String[] obterMensagensImpostosRetidos(Object[] mensagensImpostosRetidos) {
		String[] linhasImpostosRetidos = new String[3];
		
		if (mensagensImpostosRetidos[0] != null) {
			linhasImpostosRetidos[0] = (String) mensagensImpostosRetidos[0];
		} else {
			linhasImpostosRetidos[0] = "";
		}

		if (mensagensImpostosRetidos[1] != null) {
			linhasImpostosRetidos[1] = (String) mensagensImpostosRetidos[1];
		} else {
			linhasImpostosRetidos[1] = "";
		}

		if (mensagensImpostosRetidos[2] != null) {
			linhasImpostosRetidos[2] = (String) mensagensImpostosRetidos[2];
		} else {
			linhasImpostosRetidos[2] = "";
		}
		return linhasImpostosRetidos;
	}
	
}
