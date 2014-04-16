package gcom.faturamento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
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
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

/**
 * Controlador Faturamento CAEMA
 *
 * @author Raphael Rossiter
 * 
 * @date 28/04/2008
 */
public class ControladorFaturamentoCAEMASEJB extends ControladorFaturamento implements SessionBean{
	
	private static final long serialVersionUID = 1L;
	
	//===================================================================
	// METODOS EXCLUSIVOS DA CAEMA
	//===================================================================
	/**
	 * Metodo responsovel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author  Tiago Moreno
	 * @date 30/06/2008
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param faturamentoGrupo
	 * @throws ControladorException
	 */
	public void emitirContas(Integer anoMesReferenciaFaturamento,
			FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada,
			int tipoConta, Integer idEmpresa,
			Short indicadorEmissaoExtratoFaturamento)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						(idEmpresa == null ? 0 : idEmpresa));
		try {
			SistemaParametro sistemaParametro = null;
			int quantidadeContas = 0;

			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;
			int numeroIndiceAntecipado = 0;

			try {

				// recebe todos as contas da lista
				StringBuilder contasTxtLista = null;
				Map<Integer, Integer> mapAtualizaSequencial = null;
				
				Integer anoMesReferenciaFaturamentoQualidadeAgua = anoMesReferenciaFaturamento;
				
				String mesReferencia = 
					"_Fat"+ anoMesReferenciaFaturamento.toString().substring(4, 6);
				
				sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
				
				boolean ehFaturamentoAntecipado = false;
				
				Integer anoMesReferenciaFaturamentoAntecipado = null;

				if (Util.obterMes(anoMesReferenciaFaturamento) == 11) {
					if(sistemaParametro.getIndicadorFaturamentoAntecipado().equals(ConstantesSistema.SIM)){
						
						ehFaturamentoAntecipado = true;
						anoMesReferenciaFaturamentoAntecipado = 
							Util.somarData(anoMesReferenciaFaturamento);
					}
				}

				try {

					boolean flagTerminou = false;
					
					numeroIndice = 0;
					numeroIndiceAntecipado = 0;
					Integer sequencialImpressao = 0;
					Collection colecaoConta = null;
					long cont = 1;

					contasTxtLista = new StringBuilder();
					// cartasTxtListaConta = new StringBuilder();

					while (!flagTerminou) {
						// map que armazena o sequencial e o numero da
						// conta para no final atualizar todos os
						// sequencias
						mapAtualizaSequencial = new HashMap();
						Collection colecaoContaParms = null;
						
						if(anoMesReferenciaFaturamentoAntecipado != null &&
							anoMesReferenciaFaturamento.intValue() == anoMesReferenciaFaturamentoAntecipado.intValue()){
							
							System.out.println("INDICE_ANTECIPADO_PESQUISA:"+numeroIndiceAntecipado);
							
							numeroIndice = numeroIndiceAntecipado;
						}

						colecaoContaParms = 
							repositorioFaturamento.pesquisarContasEmitirCAEMA(
								numeroIndice,
								anoMesReferenciaFaturamento,
								faturamentoGrupo.getId());
						
						colecaoConta = formatarEmitirContasHelper(
							colecaoContaParms, 
							ContaTipo.CONTA_NORMAL);

						if (colecaoConta != null && !colecaoConta.isEmpty()) {

							if (colecaoConta.size() < quantidadeRegistros) {
								flagTerminou = true;
							}else if(ehFaturamentoAntecipado){
								numeroIndiceAntecipado = numeroIndiceAntecipado + 1000;	
							}

							EmitirContaHelper emitirContaHelper = null;
							Iterator iteratorConta = colecaoConta.iterator();

							// int count = 0;
							while (iteratorConta.hasNext()) {

								emitirContaHelper = null;

								emitirContaHelper = (EmitirContaHelper) iteratorConta.next();
								sequencialImpressao += 1;

								quantidadeContas++;
								// So para exibir no console a quantidade de
								// contas

								StringBuilder contaTxt = new StringBuilder();

								if (emitirContaHelper != null) {

									// Item 1 - Numero Fatura
									contaTxt.append(
										Util.completaString(
											emitirContaHelper.getIdConta()+ "/" + 
											Util.formatarAnoMesParaMesAnoSemBarra(emitirContaHelper.getAmReferencia()),16));

									// Item 2 - Data de Emissao da Fatura
									contaTxt.append(Util.completaString(Util.formatarData(new Date()), 10));

									FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

									filtroLocalidade.adicionarParametro(
										new ParametroSimples(
											FiltroLocalidade.ID,
											emitirContaHelper.getIdLocalidade()));

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

									Collection cLocalidade = 
										(Collection) getControladorUtil().pesquisar(
											filtroLocalidade,Localidade.class.getName());
									
									Localidade localidade = (Localidade) cLocalidade.iterator().next();

									// Item 3 - Endereco do Escritorio
									contaTxt.append(
										Util.completaString(
											localidade.getEnderecoFormatadoTituloAbreviado(),120));

									// Item 4 - Telefone do Escritorio
									contaTxt.append(Util.completaString(localidade.getFone(), 9));
									contaTxt.append(Util.completaString("06.274.757/0001-50", 18));
									contaTxt.append(Util.completaString("12.050.537-1", 12));

									if (localidade.getUnidadeNegocio().getId() != null && 
										!localidade.getUnidadeNegocio().getId().equals("") ) {

										contaTxt.append(Util.adicionarZerosEsquedaNumero(2,localidade.getUnidadeNegocio().getId().toString()));
									} else {
										contaTxt.append(Util.adicionarZerosEsquedaNumero(2,"00"));
									}

									// Item 100
									contaTxt.append(
										Util.adicionarZerosEsquedaNumero(
											4,emitirContaHelper.getCodigoSetorComercialConta().toString()));

									// Item 2
									contaTxt.append(
										Util.adicionarZerosEsquedaNumero(3,
											emitirContaHelper.getIdLocalidade().toString()));

									Imovel imovelEmitido = 
										getControladorImovel().pesquisarImovel(emitirContaHelper.getIdImovel());

									// Item 5
									contaTxt.append(
										Util.adicionarZerosEsquedaNumero(
											4,new Integer(imovelEmitido.getQuadra().getNumeroQuadra()).toString()));

									// Item 6
									contaTxt.append(
										Util.adicionarZerosEsquedaNumero(
											9,imovelEmitido.getNumeroSequencialRota().toString()));

									// Item 7
									contaTxt.append(
										Util.adicionarZerosEsquedaNumero(
											1,imovelEmitido.getIndicadorDebitoConta().toString()));

									// Item 9
									contaTxt.append(
										Util.completaString(
											emitirContaHelper.getIdImovel().toString(), 9));

									// Item 11
									// Caso a colecao de contas seja de entrega
									// para o cliente responsavel
									if (tipoConta == 3 || tipoConta == 4) {
										String nomeCliente = null;
										if (emitirContaHelper.getNomeImovel() != null && 
											!emitirContaHelper.getNomeImovel().equals("")) {

											nomeCliente = emitirContaHelper.getNomeImovel();

										} else {
											try {
												nomeCliente = 
													repositorioFaturamento.pesquisarNomeClienteUsuarioConta(
														emitirContaHelper.getIdConta());

											} catch (ErroRepositorioException e) {
												throw new ControladorException("erro.sistema", e);
											}
										}
										contaTxt.append(Util.completaString(nomeCliente, 40));
									} else {
										contaTxt.append(Util.completaString(
											emitirContaHelper.getNomeCliente(), 40));
									}

									// Item 12
									contaTxt.append(
										Util.completaString(
											getControladorEndereco().pesquisarEnderecoFormatado(
												imovelEmitido.getId()),120));

									// Item 13
									Categoria categoriaImovel = 
										(Categoria) getControladorImovel().obterPrincipalCategoriaImovel(
											emitirContaHelper.getIdImovel());

									contaTxt.append(
										Util.completaString(categoriaImovel.getDescricao(),30));

									Collection colecaoSubcategoria = 
										getControladorImovel()
											.obterQuantidadeEconomiasSubCategoria(
													imovelEmitido.getId());

									Subcategoria subcategoria = (Subcategoria) colecaoSubcategoria.iterator().next();

									contaTxt.append(Util.completaString(subcategoria.getDescricao(), 30));

									// Item 14
									Collection cIS = 
										getControladorImovel().pesquisarImovelSubcategoria(imovelEmitido);

									boolean residencial = false;
									boolean comercial = false;
									boolean industrial = false;
									boolean publico = false;

									int economiaResidencial = 0;
									int economiaComercial = 0;
									int economiaIndustrial = 0;
									int economiaPublica = 0;
									int economiaTotal = 0;

									// acumulando as economias por categoria
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
										economiaPorCategoria = 
											"R"+ Util.adicionarZerosEsquedaNumero(
												3,economiaResidencial+ "")+ " ";
									}

									if (comercial) {
										economiaPorCategoria = 
											"C" + Util.adicionarZerosEsquedaNumero(
												3,economiaComercial+ "")+ " ";
									}

									if (industrial) {
										economiaPorCategoria = 
											"I"+ Util.adicionarZerosEsquedaNumero(
												3,economiaIndustrial+ "")+ " ";
									}

									if (publico) {
										economiaPorCategoria = 
											"P"+ Util.adicionarZerosEsquedaNumero(
												3,economiaPublica+ "")+ " ";
									}

									contaTxt.append(Util.completaString(economiaPorCategoria, 50));

									// Item 15
									contaTxt.append(Util.adicionarZerosEsquedaNumero(4,economiaTotal + ""));

									// Item 32
									String dataVencimento = 
										Util.formatarData(emitirContaHelper.getDataVencimentoConta());
									
									contaTxt.append(Util.completaString(dataVencimento, 10));

									// Item 33
									if (imovelEmitido.getLigacaoAgua() != null) {

										if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {

											if (imovelEmitido
													.getLigacaoAgua()
													.getHidrometroInstalacaoHistorico()
													.getHidrometro() != null) {

												contaTxt.append(
													Util.completaString(
														imovelEmitido.getLigacaoAgua().
														getHidrometroInstalacaoHistorico().
														getHidrometro().getNumero(),12));
											} else {
												contaTxt.append(Util.completaString(" ", 12));
											}

										} else {
											contaTxt.append(Util.completaString(" ", 12));
										}

									} else {
										contaTxt.append(Util.completaString(" ", 12));
									}

									Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
									Integer tipoLigacao = parmSituacao[0];
									Integer tipoMedicao = parmSituacao[1];

									Object[] parmsMedicaoHistorico = 
										obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
									
									// Leitura Anterior
									String leituraAnterior = "0";
									// Leitura Atual
									String leituraAtual = "0";
									// Data Leitura Anterior
									String dataLeituraAnterior = "";
									// Leitura Anterior
									String dataLeituraAtual = "";

									if (parmsMedicaoHistorico != null) {

										if (parmsMedicaoHistorico[0] != null) {
											leituraAnterior = "" + (Integer) parmsMedicaoHistorico[0];
										}

										if (parmsMedicaoHistorico[1] != null) {
											leituraAtual = "" + (Integer) parmsMedicaoHistorico[1];
										}

										if (parmsMedicaoHistorico[3] != null) {
											dataLeituraAnterior = 
												Util.formatarData((Date) parmsMedicaoHistorico[3]);
										}

										if (parmsMedicaoHistorico[2] != null) {
											dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
										}
									}
									Object[] parmsConsumoHistorico = null;
									String consumoMedio = "0";
									String mensagemContaAnormalidade = "";
									
									if (tipoLigacao != null) {
										
										try {
											
											parmsConsumoHistorico = 
												repositorioMicromedicao
													.obterDadosConsumoConta(
															emitirContaHelper.getIdImovel(),
															emitirContaHelper.getAmReferencia(),
															tipoLigacao);

										} catch (ErroRepositorioException e) {
											sessionContext.setRollbackOnly();
											throw new ControladorException("erro.sistema", e);
										}

										if (parmsConsumoHistorico != null) {

											// Consumo medio
											if (parmsConsumoHistorico[2] != null) {
												consumoMedio = ""+ (Integer) parmsConsumoHistorico[2];
											}

											if (parmsConsumoHistorico[6] != null) {
												mensagemContaAnormalidade = (String) parmsConsumoHistorico[6];
											}
										}
									}
									// Item 18
									contaTxt.append(Util.completaString(dataLeituraAnterior, 10));

									// Item 16
									contaTxt.append(Util.completaString(dataLeituraAtual, 10));

									// Item 25
									contaTxt.append(Util.adicionarZerosEsquedaNumero(6,leituraAnterior));

									// Item 22
									contaTxt.append(Util.adicionarZerosEsquedaNumero(6,leituraAtual));

									// Item 23
									Integer consumoMedido = 
										(new Integer(leituraAtual)).intValue() - (new Integer(leituraAnterior)).intValue();
									
									if (consumoMedido.intValue() < 0) {
										consumoMedido = 0;
									}

									contaTxt.append(Util.adicionarZerosEsquedaNumero(7,consumoMedido.toString()));

									String diasConsumo = "";

									if (!dataLeituraAnterior.equals("") && 
										!dataLeituraAtual.equals("")) {
										
										diasConsumo = 
											""+ Util.obterQuantidadeDiasEntreDuasDatas(
												(Date) parmsMedicaoHistorico[3],
												(Date) parmsMedicaoHistorico[2]);
									}

									// Item 78 a 82 - Correa
									Collection colecaoContaCategoriaConsumoFaixa = null;
									Collection colecaoContaCategoriaConsumoFaixa2 = new ArrayList();

									try {
										colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
												.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
														.getIdConta());
										colecaoContaCategoriaConsumoFaixa2 = colecaoContaCategoriaConsumoFaixa;

									} catch (ErroRepositorioException e) {
										throw new ControladorException(
												"erro.sistema", e);
									}

									// Item 77
									Collection colecaoSubCategoria = 
										getControladorImovel().obterQuantidadeEconomiasSubCategoria(imovelEmitido.getId());

									Integer consumoExcesso = 0;
									Integer consumoMinimo = 0;
									BigDecimal valorExcesso = new BigDecimal("0.0");
									BigDecimal valorMinimo = new BigDecimal("0.0");

									// Colocado por Rafael Correa em 14/11/2008
									StringBuilder dadosContaCategoria = null;
									
									Collection<ContaCategoria> cContaCategoria = 
										repositorioFaturamento.pesquisarContaCategoria(
											emitirContaHelper.getIdConta());
									
									// Caso tenha mais de uma categoria (misto)
									if (cContaCategoria.size() > 1) {
										dadosContaCategoria = obterDadosContaCategoriaMisto(cContaCategoria);
									} else {

									if (colecaoContaCategoriaConsumoFaixa == null || 
										colecaoContaCategoriaConsumoFaixa.isEmpty()) {

											ContaCategoria contaCategoria = 
												(ContaCategoria) cContaCategoria.iterator().next();

											consumoMinimo = contaCategoria.getConsumoMinimoAgua();
											
											if(consumoMinimo == null){
												consumoMinimo = contaCategoria.getConsumoMinimoEsgoto();
											}

											valorMinimo = emitirContaHelper.getValorAgua();

									} else {
										if (!emitirContaHelper.getConsumoAgua().equals(0)) {
											
											for (Iterator iter = colecaoContaCategoriaConsumoFaixa.iterator(); iter.hasNext();) {

												ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = 
													(ContaCategoriaConsumoFaixa) iter.next();
												
												if (contaCategoriaConsumoFaixa.getConsumoAgua() != null) {
													
													for (Iterator iteration = colecaoSubCategoria.iterator(); iteration.hasNext();) {
														
														Subcategoria subCategoriaEmitir = (Subcategoria) iteration.next();
														
														Integer fatorEconomias = null;
														if(subCategoriaEmitir.getCategoria() != null){
															if(subCategoriaEmitir.getCategoria().getFatorEconomias() != null &&
																	!subCategoriaEmitir.getCategoria().getFatorEconomias().equals("")){
															
																fatorEconomias = subCategoriaEmitir.getCategoria().getFatorEconomias().intValue();
															}
														}

														if (contaCategoriaConsumoFaixa
																.getSubcategoria()
																.getId()
																.equals(subCategoriaEmitir.getId())) {
															
															if(fatorEconomias != null && !fatorEconomias.equals("")){
																
																consumoExcesso = 
																	consumoExcesso + contaCategoriaConsumoFaixa.getConsumoAgua() * fatorEconomias;

																valorExcesso = 
																	valorExcesso.add(
																		contaCategoriaConsumoFaixa.getValorAgua().multiply(new BigDecimal(fatorEconomias)));	
															}else{
																consumoExcesso = 
																	consumoExcesso + 
																	contaCategoriaConsumoFaixa.getConsumoAgua() * 
																	subCategoriaEmitir.getQuantidadeEconomias();

																valorExcesso = 
																	valorExcesso.add(
																		contaCategoriaConsumoFaixa.getValorAgua().multiply(
																			new BigDecimal(subCategoriaEmitir.getQuantidadeEconomias())));
															}
														}
													}
												}
											}
										}

										valorMinimo = 
											emitirContaHelper.getValorAgua().subtract(valorExcesso);
										consumoMinimo = emitirContaHelper.getConsumoAgua() - consumoExcesso;
									}
									
									if (consumoMinimo != null && consumoMinimo == 0) {

										ContaCategoria contaCategoria = 
											(ContaCategoria) cContaCategoria.iterator().next();

										consumoMinimo = contaCategoria.getConsumoMinimoAgua();
										
										if(consumoMinimo == null){
											consumoMinimo = contaCategoria.getConsumoMinimoEsgoto();
										}
									}
									
									}

									

									// Item 24
									String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
											emitirContaHelper, tipoMedicao,
											diasConsumo);

									String consumoFaturamento = parmsConsumo[0];

									if (consumoFaturamento == null || consumoFaturamento.trim().equals("") || new Integer(consumoFaturamento).intValue() >= consumoMinimo.intValue()) {

										contaTxt.append(Util
												.adicionarZerosEsquedaNumero(6,
														consumoFaturamento));
									} else {
										contaTxt.append(Util
												.adicionarZerosEsquedaNumero(6,
														consumoMinimo
																.toString()));
									}

									// Item 17
									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(6,
													consumoMedio));

									// Item 19
									contaTxt.append(Util.completaString(
											diasConsumo, 2));

									// Item 20
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(emitirContaHelper
																			.getAmReferencia()),
															7));

									if (dadosContaCategoria != null) {
										contaTxt.append(Util.completaString(dadosContaCategoria
												.toString(), 315));
									} else {

										if (emitirContaHelper.getValorAgua() != null
												&& !emitirContaHelper
														.getValorAgua()
														.equals(
																new BigDecimal(
																		"0.00"))) {
											contaTxt
													.append(Util
															.completaString(
																	"AGUA ", 31));
											contaTxt.append(Util
													.completaString(
															consumoMinimo + "",
															6));
											contaTxt
													.append(Util
															.completaString(
																	valorMinimo
																			.divide(
																					new BigDecimal(
																							consumoMinimo
																									.toString()),
																					2,
																					BigDecimal.ROUND_DOWN)
																			+ "",
																	13));
											contaTxt.append(Util
													.completaString(valorMinimo
															+ "", 13));
										} else {
											contaTxt.append(Util
													.completaString(" ", 63));
										}

										/*try {
											colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
													.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
															.getIdConta());

										} catch (ErroRepositorioException e) {
											throw new ControladorException(
													"erro.sistema", e);
										}*/

										int quantidadesFaixas = colecaoContaCategoriaConsumoFaixa2
												.size();
										int quantidadesFaixasRestantes = 4 - quantidadesFaixas;
										int contadorFaixas = 0;
										for (Iterator iter = colecaoContaCategoriaConsumoFaixa2
												.iterator(); iter.hasNext();) {

											ContaCategoriaConsumoFaixa cccf = (ContaCategoriaConsumoFaixa) iter
													.next();
											
											Short fatorEconomias = null;
											if (cccf.getCategoria() != null){
												fatorEconomias = getControladorImovel().pesquisarFatorEconomiasCategoria(cccf.getCategoria().getId());
											}

											if (cccf.getConsumoAgua() != null
													&& !cccf.getConsumoAgua()
															.equals(0)) {
												contaTxt.append(Util
														.completaString(
																"AGUA ", 31));
												if(fatorEconomias != null && !fatorEconomias.equals("")){
													 contaTxt
														.append(Util
																.completaString(new BigDecimal(
																		cccf.getConsumoAgua()).multiply(
																			new BigDecimal(fatorEconomias))
																				+ "", 6));
												}else{
												     contaTxt
														.append(Util
																.completaString(new BigDecimal(
																		cccf.getConsumoAgua()).multiply(
																			new BigDecimal(cccf.getContaCategoria().getQuantidadeEconomia()))
																				+ "", 6));
												}
											} else {
												contaTxt
														.append(Util
																.completaString(
																		"", 37));
											}

											if (cccf.getValorTarifaFaixa() != null
													&& !cccf
															.getValorTarifaFaixa()
															.equals("0.00")) {
												contaTxt
														.append(Util
																.completaString(
																		cccf
																				.getValorTarifaFaixa()
																				+ "",
																		13));
											} else {
												contaTxt
														.append(Util
																.completaString(
																		"", 13));
											}

											if (cccf.getValorAgua() != null
													&& !cccf.getValorAgua()
															.equals("0.00")) {
												
												
												if(fatorEconomias != null && !fatorEconomias.equals("")){
													contaTxt.append(Util
															.completaString(cccf
																	.getValorAgua().multiply(
																			new BigDecimal(fatorEconomias))
																	+ "", 13));
												}else{
												   contaTxt.append(Util
														.completaString(cccf
																.getValorAgua().multiply(
																		new BigDecimal(cccf.getContaCategoria().getQuantidadeEconomia()))
																+ "", 13));
												}
											} else {
												contaTxt
														.append(Util
																.completaString(
																		"", 13));
											}

											contadorFaixas++;
											if (contadorFaixas >= 4) {
												break;
											}
										}

										contaTxt
												.append(Util
														.completaString(
																"",
																63 * quantidadesFaixasRestantes));

									}

									// Item 26
									if (emitirContaHelper.getValorAgua() != null) {
										contaTxt.append(Util.completaString(
												"TOTAL ÁGUA ", 50));
										contaTxt.append(Util.completaString(
												emitirContaHelper
														.getValorAgua()
														+ "", 13));
									} else {
										contaTxt.append(Util.completaString(
												" ", 63));
									}

									// Item 27
									if (emitirContaHelper.getValorEsgoto() != null
											&& !emitirContaHelper
													.getValorEsgoto().equals(
															new BigDecimal(
																	"0.00"))) {
										
										contaTxt
												.append(Util
														.completaString(
																"ESGOTO ",
																50));
										contaTxt.append(Util
												.completaString(
														emitirContaHelper.getValorEsgoto()
																+ "", 13));
										contaTxt
												.append(Util
														.completaString(
																" ",
																63));
										contaTxt
												.append(Util
														.completaString(
																" ",
																50));
										contaTxt
												.append(Util.completaString(" ", 13));
									} else {
										contaTxt.append(Util.completaString(
												" ", 189));
									}

									// Item 30
									if (emitirContaHelper.getValorCreditos() != null
											&& !emitirContaHelper
													.getValorCreditos().equals(
															new BigDecimal(
																	"0.00"))) {
										contaTxt.append(Util.completaString(
												"TOTAL DE CREDITOS ", 50));
										contaTxt.append(Util.completaString(
												emitirContaHelper
														.getValorCreditos()
														+ "", 13));
									} else {
										contaTxt.append(Util.completaString(
												" ", 63));
									}
									

									// Item 57 ao Item 76
									Conta contaId = new Conta();
									contaId.setId(emitirContaHelper
											.getIdConta());

									Collection<DebitoCobradoAgrupadoHelper> cDebitoCobrado = this
											.obterDebitosCobradosContaCAERN(contaId);

									int quantidadeLinhasSobrando = 9;
									int i = 0;

									if (cDebitoCobrado != null
											&& !cDebitoCobrado.isEmpty()) {

										int quantidadeDebitos = cDebitoCobrado
												.size();

										if (quantidadeLinhasSobrando >= quantidadeDebitos) {

											for (Iterator iter = cDebitoCobrado
													.iterator(); iter.hasNext();) {
												DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter
														.next();

												contaTxt
														.append(Util
																.completaString(
																		debitoCobrado
																				.getDescricaoDebitoTipo(),
																		30)); // 30
												contaTxt
														.append(Util
																.completaString(
																		debitoCobrado
																				.getNumeroPrestacaoDebito()
																				+ "/"
																				+ debitoCobrado
																						.getNumeroPrestacao(),
																		20));
												contaTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		Util
																				.formatarMoedaReal(debitoCobrado
																						.getValorDebito()),
																		13));

												i++;
											}

										} else {
											Iterator iter = cDebitoCobrado
													.iterator();
											int contador = 1;
											BigDecimal valorAcumulado = new BigDecimal(
													"0.00");
											boolean temOutros = false;
											while (iter.hasNext()) {
												DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter
														.next();

												if (quantidadeLinhasSobrando > contador) {
													contaTxt
															.append(Util
																	.completaString(
																			debitoCobrado
																					.getDescricaoDebitoTipo(),
																			30)); // 30
													contaTxt
															.append(Util
																	.completaString(
																			debitoCobrado
																					.getNumeroPrestacaoDebito()
																					+ "/"
																					+ debitoCobrado
																							.getNumeroPrestacao(),
																			20));
													contaTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(debitoCobrado
																							.getValorDebito()),
																			13));
													i++;
												} else {

													valorAcumulado = valorAcumulado
															.add(debitoCobrado
																	.getValorDebito());
													temOutros = true;
												}

												contador++;
											}
											if (temOutros) {
												contaTxt
														.append("OUTROS SERVICOS               "); // 30
												contaTxt
														.append(Util
																.completaString(
																		" ", 20));
												contaTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		Util
																				.formatarMoedaReal(valorAcumulado),
																		13));
												i++;
											}
										}
									}
									
									if (emitirContaHelper
											.getValorImpostos()!= null && !emitirContaHelper.getValorImpostos()
											.equals(new BigDecimal("0.00"))) {
										contaTxt
												.append("TOTAL DE IMPOSTOS FEDERAIS "); // 27
										contaTxt.append(Util
												.completaString(
														" ", 23));
										contaTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.formatarMoedaReal(emitirContaHelper
																				.getValorImpostos()),
																13));
										
									} else {
										contaTxt
										.append(Util
												.completaString(" ",63));
									}

									int quantidadeLinhasServicosSobraram = 9 - i;
									contaTxt
											.append(Util
													.completaString(
															" ",
															quantidadeLinhasServicosSobraram * 63));

									// Item 28

									/*FiltroConta filtroConta = new FiltroConta();

									filtroConta
											.adicionarParametro(new ParametroSimples(
													FiltroConta.ID,
													emitirContaHelper
															.getIdConta()
															+ ""));

									Collection colecaoContaEmitida = (Collection) getControladorUtil()
											.pesquisar(filtroConta,
													Conta.class.getName());

									Conta contaEmitida = (Conta) colecaoContaEmitida
											.iterator().next();
												
									contaTxt.append(Util.completaString(
											contaEmitida.getValorTotalConta(),
											13));
									*/
									Conta conta = new Conta();
									conta.setValorAgua(emitirContaHelper
											.getValorAgua());
									conta.setValorEsgoto(emitirContaHelper
											.getValorEsgoto());
									conta.setValorCreditos(emitirContaHelper
											.getValorCreditos());
									conta.setDebitos(emitirContaHelper
											.getDebitos());
									conta.setValorImposto(emitirContaHelper
											.getValorImpostos());

									BigDecimal valorConta = conta
											.getValorTotalContaBigDecimal();

									/*Conta contaValor = new Conta();
									
									contaValor.setId(emitirContaHelper.getIdConta());
									
									BigDecimal valorTotalConta = repositorioFaturamento.obterValorConta(contaValor);*/
									
									contaTxt.append(Util
										.completaStringComEspacoAEsquerda(
													Util.formatarMoedaReal(valorConta),13));
											

									// Item 36
									int mes1 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													1);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes1),
															7));

									// Item 37
									int mes2 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													2);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes2),
															7));

									// Item 38
									int mes3 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													3);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes3),
															7));

									// Item 39
									int mes4 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													4);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes4),
															7));

									// Item 40
									int mes5 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													5);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes5),
															7));

									// Item 41
									int mes6 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													6);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes6),
															7));

									// Item 48
									StringBuilder consumo1 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													1, tipoLigacao, tipoMedicao);

									if (consumo1 != null) {
										contaTxt.append(Util.completaString(
												consumo1 + "", 7));
									} else {
										contaTxt.append(Util.completaString("",
												7));
									}

									// Item 49
									StringBuilder consumo2 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													2, tipoLigacao, tipoMedicao);

									if (consumo2 != null) {
										contaTxt.append(Util.completaString(
												consumo2 + "", 7));
									} else {
										contaTxt.append(Util.completaString("",
												7));
									}

									// Item 50
									StringBuilder consumo3 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													3, tipoLigacao, tipoMedicao);

									if (consumo3 != null) {
										contaTxt.append(Util.completaString(
												consumo3 + "", 7));
									} else {
										contaTxt.append(Util.completaString("",
												7));
									}

									// Item 51
									StringBuilder consumo4 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													4, tipoLigacao, tipoMedicao);

									if (consumo4 != null) {
										contaTxt.append(Util.completaString(
												consumo4 + "", 7));
									} else {
										contaTxt.append(Util.completaString("",
												7));
									}

									// Item 52
									StringBuilder consumo5 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													5, tipoLigacao, tipoMedicao);

									if (consumo5 != null) {
										contaTxt.append(Util.completaString(
												consumo5 + "", 7));
									} else {
										contaTxt.append(Util.completaString("",
												7));
									}

									// Item 53
									StringBuilder consumo6 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													6, tipoLigacao, tipoMedicao);

									if (consumo6 != null) {
										contaTxt.append(Util.completaString(
												consumo6 + "", 7));
									} else {
										contaTxt.append(Util.completaString("",
												7));
									}
									
									String[] parmsPartesConta = 
										(String[]) repositorioFaturamento.obterContaMensagemImovel(imovelEmitido.getId(), emitirContaHelper.getAmReferencia());
									
									if (parmsPartesConta == null || parmsPartesConta[0] == null || parmsPartesConta[0].equalsIgnoreCase("")){
										parmsPartesConta = obterMensagemConta3Partes(emitirContaHelper, sistemaParametro);
									}

									contaTxt.append(Util.completaString(
											parmsPartesConta[0], 100));

									// Item 55
									contaTxt.append(Util.completaString(
											parmsPartesConta[1], 100));

									// Item 56
									contaTxt.append(Util.completaString(
											parmsPartesConta[2], 100));

									// Item 99
									if (mensagemContaAnormalidade != null
											&& !mensagemContaAnormalidade
													.equalsIgnoreCase("")) {
										contaTxt
												.append(Util
														.completaString(
																mensagemContaAnormalidade,
																100));
									} else {
										contaTxt.append(Util.completaString(
												" ", 100));
									}

									// qt de faturas abertas 6 posicoes - total
									// do debito 13 posicoes
									
									if (imovelEmitido.getImovelPerfil() != null && 
											imovelEmitido.getImovelPerfil().getId().equals(ImovelPerfil.VIVA_AGUA)) {
										contaTxt.append(Util.completaString(" ", 19));
									} else{
										ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this
											.obterDebitoImovelOuClienteHelper(
													emitirContaHelper,
													sistemaParametro);

										Collection colecaoContasValores = obterDebitoImovelOuClienteHelper
												.getColecaoContasValores();
		
										if (colecaoContasValores != null
												&& !colecaoContasValores.isEmpty()) {
											Integer qtContas = colecaoContasValores
													.size();
											contaTxt.append(Util.completaString(
													qtContas + "", 6));
		
											BigDecimal valorDebitoTotal = new BigDecimal(
													"0.00");
		
											for (Iterator iter = colecaoContasValores
													.iterator(); iter.hasNext();) {
												ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter
														.next();
		
												valorDebitoTotal = valorDebitoTotal
														.add(contaValoresHelper
																.getValorTotalConta());
											}
		
											contaTxt
													.append(Util
															.completaString(
																	Util
																			.formatarMoedaReal(valorDebitoTotal),
																	13));
										} else {
											contaTxt.append(Util.completaString(
													" ", 19));
										}
									}
									

									// Item 83 e 84

									

									String anoMesString = ""
											+ emitirContaHelper
													.getAmReferencia();

									String mesAnoFormatado = anoMesString
											.substring(4, 6)
											+ anoMesString.substring(0, 4);

									Integer digitoVerificadorConta = new Integer(
											""
													+ emitirContaHelper
															.getDigitoVerificadorConta());

									String representacaoNumericaCodBarra = this
											.getControladorArrecadacao()
											.obterRepresentacaoNumericaCodigoBarra(
													3,
													valorConta,
													emitirContaHelper
															.getIdLocalidade(),
													emitirContaHelper
															.getIdImovel(),
													mesAnoFormatado,
													digitoVerificadorConta,
													null, null, null, null,
													null, null, null);

									contaTxt.append(Util.completaString(
											representacaoNumericaCodBarra, 50));

									// Item 129
									contaTxt.append(Util.completaString(cont
											+ "", 8));

									// #############################################
									// PROXIMA PAGINA
									// ################################################################
									// Qualidade Agua
									
									String [] qualidade = this.obterDadosQualidadeAguaCAEMA(
											localidade.getId(), emitirContaHelper.getIdSetorComercial(), anoMesReferenciaFaturamentoQualidadeAgua);
									
									contaTxt.append(Util.completaString(qualidade[0],10));
									
									contaTxt.append(Util.completaString(qualidade[1],10));
									
									contaTxt.append(Util.completaString(qualidade[2],10));
									
									contaTxt.append(Util.completaString(qualidade[3],10));
									
									contaTxt.append(Util.completaString(qualidade[4],10));
									
									contaTxt.append(Util.completaString(qualidade[5],10));
									
									
									contaTxt.append(Util.completaString(qualidade[6],10));
									
									contaTxt.append(Util.completaString(qualidade[7],10));
									
									contaTxt.append(Util.completaString(qualidade[8],10));
									
									contaTxt.append(Util.completaString(qualidade[9],10));
									
									contaTxt.append(Util.completaString(qualidade[10],10));
									
									contaTxt.append(Util.completaString(qualidade[11],10));
									
									
									contaTxt.append(Util.completaString(qualidade[12],10));
									
									contaTxt.append(Util.completaString(qualidade[13],10));
									
									contaTxt.append(Util.completaString(qualidade[14],10));
									
									contaTxt.append(Util.completaString(qualidade[15],10));
									
									contaTxt.append(Util.completaString(qualidade[16],10));
									
									contaTxt.append(Util.completaString(qualidade[17],10));
									
									
									contaTxt.append(Util.completaString(qualidade[18],10));
									
									contaTxt.append(Util.completaString(qualidade[19],10));
									
									contaTxt.append(Util.completaString(qualidade[20],10));
									
									contaTxt.append(Util.completaString(qualidade[21],10));
									
									contaTxt.append(Util.completaString(qualidade[22],10));
									
									contaTxt.append(Util.completaString(qualidade[23],10));
									

									// Item 87
									String[] enderecoImovel = getControladorEndereco()
											.pesquisarEnderecoFormatadoDividido(
													emitirContaHelper
															.getIdImovel());

									contaTxt.append(Util.completaString(
											enderecoImovel[1], 30));

									// Item 88 a Item 90
									contaTxt.append(Util.completaString(
											enderecoImovel[0], 78));

									// Item 92
									contaTxt.append(Util.completaString(
											enderecoImovel[3], 20));

									// Item 93
									contaTxt.append(Util.completaString(
											enderecoImovel[4], 9));

									// Item 94
									contaTxt.append(Util.completaString(
											enderecoImovel[2], 2));
									
									/*Cliente cliente = getControladorCliente().pesquisarCliente(emitirContaHelper.getIdCliente());*/
									
									String cnpjCpf = "";
									
									Collection colecaoClienteImovel = repositorioClienteImovel.
										pesquisarClienteImovelResponsavelConta(emitirContaHelper.getIdImovel());
									
									if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
										ClienteImovel clienteImovelRespConta = (ClienteImovel) colecaoClienteImovel.iterator().next();
										
										if (clienteImovelRespConta != null){
											Cliente cliente = clienteImovelRespConta.getCliente();
										
											if (cliente.getCnpjFormatado() != null && !cliente.getCnpjFormatado().equalsIgnoreCase("")){
												cnpjCpf = cliente.getCnpjFormatado();
											} else if (cliente.getCpfFormatado() != null && !cliente.getCpfFormatado().equalsIgnoreCase("")){
												cnpjCpf = cliente.getCpfFormatado();
											}
											
										}
									}
																		
									contaTxt.append(Util.completaString(cnpjCpf, 20));
									
									FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
									
									Collection colecaoConsumoTarifa = 
										getControladorUtil().pesquisar(filtroConsumoTarifa, 
											ConsumoTarifa.class.getName());
									
									ConsumoTarifa consumoTarifa = (ConsumoTarifa) colecaoConsumoTarifa.iterator().next();
									contaTxt.append(Util.completaString(consumoTarifa.getDescricao(), 25));
									
									Object[] dadosRota = 
										getControladorMicromedicao().obterRotaESequencialRotaDoImovelSeparados(
											imovelEmitido.getId());
									
									contaTxt.append(Util.completaString(
										Util.adicionarZerosEsquedaNumero(6, 
											dadosRota[0].toString())+"",6));
									
									contaTxt.append(Util.completaString(
										Util.adicionarZerosEsquedaNumero(3, 
											faturamentoGrupo.getId().toString())+"",3));
									
									contaTxt.append(Util.completaString(
										Util.adicionarZerosEsquedaNumero(4, 
											imovelEmitido.getLote()+"")+"",4));
									
									contaTxt.append(Util.completaString(
										Util.adicionarZerosEsquedaNumero(3, 
											imovelEmitido.getSubLote()+"")+"",3));
									
									if (imovelEmitido.getAreaConstruida() != null){
										int area = imovelEmitido.getAreaConstruida().intValue();
										contaTxt.append(Util.completaString(area+"",5));
									
									} else {
										contaTxt.append(Util.completaString("",5));
									}
									
									if(emitirContaHelper.getDescricaoLigacaoAguaSituacao() != null 
										&& !emitirContaHelper.getDescricaoLigacaoAguaSituacao().equals("")){
										
										contaTxt.append(Util.completaString(
											emitirContaHelper.getDescricaoLigacaoAguaSituacao(),20));
									}else{
										contaTxt.append(Util.completaString("",20));
									}
									// Perfil do Imovel CRC 3151 Analista: Claudio Lira
									// Alterado por Rômulo Aurélio Data: 24/11/2009
									if (imovelEmitido.getImovelPerfil() != null) {
										contaTxt.append(Util.adicionarZerosEsquedaNumeroTruncando(2,
												imovelEmitido.getImovelPerfil().getId() + ""));

									} else {
										contaTxt.append(Util.adicionarZerosEsquedaNumeroTruncando(2,""));
									}									
									// Fim da alteracao
									
									// Indicador Imovel saiu do programa especial CRC3639 Analista: Rafael Pinto
									// Alterado por Hugo Amorim Data: 01/02/2009								
									ImovelPerfil perfilProgramaEspecial = sistemaParametro.getPerfilProgramaEspecial();						
									Integer idProgramaEspecial = null;				
									
									if(perfilProgramaEspecial!=null){
										idProgramaEspecial = perfilProgramaEspecial.getId();
									}
									
									Integer mesAnoAnteriorConta = 
										Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), 1);
									
									FiltroContaHistorico filtroContaHistorico =
										new FiltroContaHistorico();
									filtroContaHistorico.adicionarParametro(
											new ParametroSimples(
													FiltroContaHistorico.ANO_MES_REFERENCIA, mesAnoAnteriorConta));
									filtroContaHistorico.adicionarParametro(
											new ParametroSimples(
													FiltroContaHistorico.IMOVEL, emitirContaHelper.getIdImovel()));
									filtroContaHistorico
										.adicionarCaminhoParaCarregamentoEntidade(FiltroContaHistorico.IMOVEL_PERFIL);
									
									Collection contasHistoricos = 
										this.getControladorUtil()
											.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
									
									ContaHistorico contaHistorico =
										(ContaHistorico) Util.retonarObjetoDeColecao(contasHistoricos);						
									
									Conta contaPrograma = null;
									
									if(contaHistorico==null){
										
										FiltroConta filtroContaPrograma = new FiltroConta();

										filtroContaPrograma.adicionarParametro(
												new ParametroSimples(
														FiltroConta.REFERENCIA, mesAnoAnteriorConta));
										filtroContaPrograma.adicionarParametro(
												new ParametroSimples(
														FiltroConta.IMOVEL, emitirContaHelper.getIdImovel()));
										filtroContaPrograma
											.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_PERFIL);
										
										Collection collecaoContas = 
											this.getControladorUtil()
												.pesquisar(filtroContaPrograma, Conta.class.getName());
										
										contaPrograma =
											(Conta) Util.retonarObjetoDeColecao(collecaoContas);	
																
									}
									
									if(contaHistorico !=null 
											&&contaHistorico.getImovelPerfil().getId()
												.compareTo(idProgramaEspecial)==0
													&& emitirContaHelper.getIdImovelPerfil()
														.compareTo(idProgramaEspecial)!=0){
										
										contaTxt.append(ConstantesSistema.SIM.toString());
										
									}else if(contaPrograma !=null 
											&&contaPrograma.getImovelPerfil().getId()
											.compareTo(idProgramaEspecial)==0
												&& emitirContaHelper.getIdImovelPerfil()
													.compareTo(idProgramaEspecial)!=0){
										
										contaTxt.append(ConstantesSistema.SIM.toString());	
										
								    }else{
								    	
										contaTxt.append(ConstantesSistema.NAO.toString());
										
									}									
									// Fim da alteracao

									
									//Adicionando a Linha ao StringBuilder Completo
									contasTxtLista.append(contaTxt.toString());
									conta = null;
									contaTxt = null;

									contasTxtLista.append(System
											.getProperty("line.separator"));

									// Adiciona o id da conta e o sequencial no para serem atualizados
									mapAtualizaSequencial.put(emitirContaHelper
											.getIdConta(), sequencialImpressao);
									cont++;
									
									System.out.println("ID_CONTA:"+emitirContaHelper.getIdConta()+" SEQUENCIAL:"+sequencialImpressao+" CONT:"+cont);
									
									
									
									if(flagTerminou && ehFaturamentoAntecipado){
										if(anoMesReferenciaFaturamentoAntecipado != null && 
											anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()){
											
											anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
											flagTerminou = false;
											numeroIndiceAntecipado = 0;
										}
									}
								}
							}
							

						}else{
							flagTerminou = true;
							if(ehFaturamentoAntecipado){
								if(anoMesReferenciaFaturamentoAntecipado != null && 
									anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()){
									
									anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
									flagTerminou = false;
									numeroIndiceAntecipado = 0;
								}
							}
						}
						

						numeroIndice = numeroIndice + 1000;	
						
						if(mapAtualizaSequencial != null){
							
							System.out.println("NUMERO_INDICE_ANTECIPADO:"+numeroIndiceAntecipado);
							System.out.println("NUMERO_INDICE:"+numeroIndice);
							System.out.println("QTD_CONTAS:"+quantidadeContas);
							
							repositorioFaturamento
									.atualizarSequencialContaImpressao(mapAtualizaSequencial);
						}
						mapAtualizaSequencial = null;

						
					}// fim laco if da paginacao

					String idGrupoFaturamento = "G" + faturamentoGrupo.getId();
					String nomeZip = null;

					nomeZip = "CONTA_CAEMA" + "_GRUPO" + idGrupoFaturamento	+ mesReferencia;

					BufferedWriter out = null;
					ZipOutputStream zos = null;

					File compactadoTipo = new File(nomeZip + ".zip");
					File leituraTipo = new File(nomeZip + ".txt");

					if (contasTxtLista != null && contasTxtLista.length() != 0) {
						// fim de arquivo
						// ************ TIPO E *************
						zos = new ZipOutputStream(new FileOutputStream(
								compactadoTipo));
						out = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(leituraTipo
										.getAbsolutePath())));
						out.write(contasTxtLista.toString());
						out.flush();
						ZipUtil.adicionarArquivo(zos, leituraTipo);
						zos.close();
						leituraTipo.delete();
						out.close();
					}

					// limpa todos os campos
					nomeZip = null;
					out = null;
					zos = null;
					compactadoTipo = null;
					leituraTipo = null;
					contasTxtLista = null;

					tipoConta++;
				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

			} catch (IOException e) {
				String mensagem = e.getMessage();
				String[] inicioMensagem = mensagem.split("\\.");
				if (inicioMensagem != null
						&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
								.equals("atencao"))) {
					throw new ControladorException(mensagem);
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String mensagem = e.getMessage();
				if (mensagem != null) {
					String[] inicioMensagem = mensagem.split("\\.");
					if (inicioMensagem != null
							&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
									.equals("atencao"))) {
						throw new ControladorException(mensagem);
					} else {
						throw new ControladorException("erro.sistema", e);
					}
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execucao da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer excecao que o processo
			// batch venha a lancar e garantir que a unidade de processamento do
			// batch sera atualizada com o erro ocorrido

			e.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}
		
		

	}


	/**
	 * Metodo utilizado para adicionar os dados das contas categorias no txt no caso do imovel ser misto 
	 *
	 * @author Rafael Correa
	 * @date 14/11/2008
	 *
	 * @param cContaCategoria
	 * @return
	 * @throws ControladorException
	 */
	private StringBuilder obterDadosContaCategoriaMisto(Collection<ContaCategoria> cContaCategoria) {
		
		StringBuilder retorno = new StringBuilder();
		
		for (ContaCategoria contaCategoria : cContaCategoria) {
			
			Integer consumoAgua = contaCategoria.getConsumoAgua();
			BigDecimal valorAgua = contaCategoria.getValorAgua();
			
			if (valorAgua != null && !valorAgua.equals(new BigDecimal("0.00"))) {
			
				String subcategoriaFormatada = Util.completaString(contaCategoria.getComp_id().getSubcategoria().getDescricaoAbreviada(), 15);
				String quantidadeEconomias = contaCategoria.getQuantidadeEconomia() + " UNIDADE(S)";
			
				retorno.append(Util.completaString(subcategoriaFormatada + " " + quantidadeEconomias, 31));
			
				if (consumoAgua != null && consumoAgua != 0) {
				retorno.append(Util.completaString(consumoAgua.toString(), 6));
				retorno.append(Util.completaString(valorAgua.divide(
						new BigDecimal(consumoAgua.toString()), 2,
						BigDecimal.ROUND_DOWN)
						+ "", 13));
				} else {
					retorno.append(Util.completaString("0", 6));
					retorno.append(Util.completaString(valorAgua.toString(), 13));
				}

				retorno.append(Util.completaString(valorAgua.toString(), 13));
				
			} else {
				retorno.append(Util.completaString("", 63));
			}
			
			
		}
		
		return retorno;
	}
	
	public String[] obterDadosQualidadeAguaCAEMA (Integer idLocalidade, Integer idSetorComercial, Integer amReferencia) 
		throws ControladorException{
		
		String [] retornoQualidade = new String [24];
		
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
		
		
		// Qualidade da Agua Padrao
		FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
		
		Collection colecaoQualidAguaPadrao = getControladorUtil().pesquisar(filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class.getName());
		
		if (colecaoQualidAguaPadrao != null && !colecaoQualidAguaPadrao.isEmpty()){

			QualidadeAguaPadrao qualidadePadrao = (QualidadeAguaPadrao) colecaoQualidAguaPadrao.iterator().next();
		
			padraoCor = qualidadePadrao.getDescricaoPadraoCor();
			padraoTurbidez = qualidadePadrao.getDescricaoPadraoTurbidez();
			padraoFluor = qualidadePadrao.getDescricaoPadraoFluor();
			padraoCloro = qualidadePadrao.getDescricaoPadraoCloro();
			padraoColiformesTotais = qualidadePadrao.getDescricaoPadraoColiformesTotais();
			padraoColiformesTermotolerantes = qualidadePadrao.getDescricaoPadraoColiformesTermotolerantes();
		
		}
		
		// Qualidade da Agua em 3 Niveis
		
		// Com Localidade e Setor
		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

		Collection colecaoQualidadeAgua = null;

		filtroQualidadeAgua.adicionarParametro(
			new ParametroSimples(
				FiltroQualidadeAgua.LOCALIDADE_ID,
				idLocalidade));
		
		filtroQualidadeAgua.adicionarParametro(
			new ParametroSimples(
				FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
				idSetorComercial));
		
		filtroQualidadeAgua.adicionarParametro(
			new ParametroSimples(
				FiltroQualidadeAgua.ANO_MES_REFERENCIA,
				amReferencia));
		
		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
		
		colecaoQualidadeAgua = 
			getControladorUtil().pesquisar(
				filtroQualidadeAgua,
				QualidadeAgua.class.getName());
		
		//Com Localidade
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
			
			filtroQualidadeAgua.limparListaParametros();
			
			colecaoQualidadeAgua = null;
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID,
					idLocalidade));
			
			filtroQualidadeAgua.adicionarParametro(
					new ParametroNulo(
						FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA,
					amReferencia));
			
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			
			colecaoQualidadeAgua = 
				getControladorUtil().pesquisar(
					filtroQualidadeAgua,
					QualidadeAgua.class.getName());
		}
		
		// Sem Localidade e sem Setor
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
			
			filtroQualidadeAgua.limparListaParametros();
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA,
					amReferencia));
			
			filtroQualidadeAgua.adicionarParametro(
					new ParametroNulo(
						FiltroQualidadeAgua.LOCALIDADE_ID));
			
			filtroQualidadeAgua.adicionarParametro(
					new ParametroNulo(
						FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			
			colecaoQualidadeAgua = 
				getControladorUtil().pesquisar(
					filtroQualidadeAgua,
					QualidadeAgua.class.getName());
		}
		
		if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()){
			
			QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();
			
			// Exigidas
			if (qualidadeAgua.getQuantidadeCorExigidas() != null) {
				exigidaCor = qualidadeAgua.getQuantidadeCorExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeTurbidezExigidas() != null) {
				exigidaTurbidez = qualidadeAgua.getQuantidadeTurbidezExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeFluorExigidas() != null) {
				exigidaFluor = qualidadeAgua.getQuantidadeFluorExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeCloroExigidas() != null) {
				exigidaCloro = qualidadeAgua.getQuantidadeCloroExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTotaisExigidas() != null) {
				exigidaColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() != null) {
				exigidaColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas()+"";
			}

			// Analisadas
			if (qualidadeAgua.getQuantidadeCorAnalisadas() != null) {
				analisadaCor = qualidadeAgua.getQuantidadeCorAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeTurbidezAnalisadas() != null) {
				analisadaTurbidez = qualidadeAgua.getQuantidadeTurbidezAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeFluorAnalisadas() != null) {
				analisadaFluor = qualidadeAgua.getQuantidadeFluorAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeCloroAnalisadas() != null) {
				analisadaCloro = qualidadeAgua.getQuantidadeCloroAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas() != null) {
				analisadaColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas() != null) {
				analisadaColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas()+"";
			}
			 
			// Em Conformidade
			if (qualidadeAgua.getQuantidadeCorConforme() != null) {
				emConformidadeCor = qualidadeAgua.getQuantidadeCorConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeTurbidezConforme() != null) {
				emConformidadeTurbidez = qualidadeAgua.getQuantidadeTurbidezConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeFluorConforme() != null) {
				emConformidadeFluor = qualidadeAgua.getQuantidadeFluorConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeCloroConforme() != null) {
				emConformidadeCloro = qualidadeAgua.getQuantidadeCloroConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTotaisConforme() != null) {
				emConformidadeColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() != null) {
				emConformidadeColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme()+"";
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
		
		
		return retornoQualidade;
	}
	
	
	public String[] obterMensagemConta3Partes(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {

		String[] mensagemContaDivididas = new String[3];
		
		// mensagem da conta para a anormalidade de consumo (Baixo Consumo,Auto Consumo e Estouro de Consumo)

		mensagemContaDivididas = obterMensagemAnormalidadeConsumo(emitirContaHelper);
		
		if(mensagemContaDivididas == null || mensagemContaDivididas.equals("")){
			mensagemContaDivididas = new String[3];
			// Integer anoMesReferenciaFinal =
			// sistemaParametro.getAnoMesFaturamento();
			// int anoMesSubtraido = Util
			// .subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
			Integer dataVencimentoFinalInteger = sistemaParametro
					.getAnoMesArrecadacao();
			String anoMesSubtraidoString = ""
					+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);
			int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
			int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));
	
			// recupera o ultimo dia do anomes e passa a data como parametro
			Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
			dataVencimentoFinal.set(Calendar.YEAR, ano);
			dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
			dataVencimentoFinal.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
					.getActualMaximum(Calendar.DAY_OF_MONTH));
	
			Object[] mensagensConta = null;
			// recupera o id do grupo de faturamento da conta
			Integer idFaturamentoGrupo = emitirContaHelper.getIdFaturamentoGrupo();
			// recupera o id da gerencia regional da conta
			Integer idGerenciaRegional = emitirContaHelper.getIdGerenciaRegional();
			// recupera o id da localidade da conta
			Integer idLocalidade = emitirContaHelper.getIdLocalidade();
			// recupera o id do setor comercial da conta
			Integer idSetorComercial = emitirContaHelper.getIdSetorComercial();
			// caso entre em alguma condicao entao nao entra mais nas outras
			boolean achou = false;
			try {
				// o sistema obtem a mensagem para a conta
				// Caso seja a condicao 1
				// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
				// Localidade=parmConta, SetorComercial=parmConta)
				mensagensConta = repositorioFaturamento
						.pesquisarParmsContaMensagem(emitirContaHelper, null,
								idGerenciaRegional, idLocalidade, idSetorComercial);
				if (mensagensConta != null) {
					// Conta Mensagem 1
					if (mensagensConta[0] != null) {
						mensagemContaDivididas[0] = (String) mensagensConta[0];
					} else {
						mensagemContaDivididas[0] = "";
					}
					// Conta Mensagem 2
					if (mensagensConta[1] != null) {
						mensagemContaDivididas[1] = (String) mensagensConta[1];
					} else {
						mensagemContaDivididas[1] = "";
					}
					// Conta Mensagem 3
					if (mensagensConta[2] != null) {
						mensagemContaDivididas[2] = (String) mensagensConta[2];
					} else {
						mensagemContaDivididas[2] = "";
					}
					achou = true;
				}
	
				if (!achou) {
	
					// Caso seja a condicao 2
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper, null,
									idGerenciaRegional, idLocalidade, null);
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							mensagemContaDivididas[0] = (String) mensagensConta[0];
						} else {
							mensagemContaDivididas[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							mensagemContaDivididas[1] = (String) mensagensConta[1];
						} else {
							mensagemContaDivididas[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							mensagemContaDivididas[2] = (String) mensagensConta[2];
						} else {
							mensagemContaDivididas[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condicao 3
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper, null,
									idGerenciaRegional, null, null);
	
					if (mensagensConta != null) {
	
						if (mensagensConta[0] != null) {
							mensagemContaDivididas[0] = (String) mensagensConta[0];
						} else {
							mensagemContaDivididas[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							mensagemContaDivididas[1] = (String) mensagensConta[1];
						} else {
							mensagemContaDivididas[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							mensagemContaDivididas[2] = (String) mensagensConta[2];
						} else {
							mensagemContaDivididas[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condicao 4
					// (FaturamentoGrupo =parmConta, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									idFaturamentoGrupo, null, null, null);
	
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							mensagemContaDivididas[0] = (String) mensagensConta[0];
						} else {
							mensagemContaDivididas[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							mensagemContaDivididas[1] = (String) mensagensConta[1];
						} else {
							mensagemContaDivididas[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							mensagemContaDivididas[2] = (String) mensagensConta[2];
						} else {
							mensagemContaDivididas[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condicao 4
					// (FaturamentoGrupo =parmConta, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper, null,
									null, idLocalidade, idSetorComercial);
	
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							mensagemContaDivididas[0] = (String) mensagensConta[0];
						} else {
							mensagemContaDivididas[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							mensagemContaDivididas[1] = (String) mensagensConta[1];
						} else {
							mensagemContaDivididas[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							mensagemContaDivididas[2] = (String) mensagensConta[2];
						} else {
							mensagemContaDivididas[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condicao 4
					// (FaturamentoGrupo =parmConta, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper, null,
									null, idLocalidade, null);
	
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							mensagemContaDivididas[0] = (String) mensagensConta[0];
						} else {
							mensagemContaDivididas[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							mensagemContaDivididas[1] = (String) mensagensConta[1];
						} else {
							mensagemContaDivididas[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							mensagemContaDivididas[2] = (String) mensagensConta[2];
						} else {
							mensagemContaDivididas[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condicao 5
					// (FaturamentoGrupo =null, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper, null,
									null, null, null);
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							mensagemContaDivididas[0] = (String) mensagensConta[0];
						} else {
							mensagemContaDivididas[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							mensagemContaDivididas[1] = (String) mensagensConta[1];
						} else {
							mensagemContaDivididas[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							mensagemContaDivididas[2] = (String) mensagensConta[2];
						} else {
							mensagemContaDivididas[2] = "";
						}
						achou = true;
					}
				}
				// caso nao tenha entrado em nenhuma das opcoes acima
				// entao completa a string com espacos em branco
				if (!achou) {
					mensagemContaDivididas[0] = "";
					mensagemContaDivididas[1] = "";
					mensagemContaDivididas[2] = "";
				}
			 
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}

		return mensagemContaDivididas;
	}

	public StringBuilder obterConsumoAnterior(Integer idImovel, int anoMes,
			int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException {

		StringBuilder dadosConsumoAnterior = new StringBuilder();

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);

		// adiciona o mes/ano formatado com o traco

		// caso o tipo de ligacao e medicao seja diferente de nulo
		if (tipoLigacao != null && tipoMedicao != null) {
			//dadosConsumoAnterior
			//		.append(Util.completaString(mesAnoFormatado, 7));
			Object[] parmsConsumoHistorico = null;
			parmsConsumoHistorico = getControladorMicromedicao()
					.obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
							anoMesSubtraido, tipoLigacao);
			Integer numeroConsumoFaturadoMes = null;
			if (parmsConsumoHistorico != null) {
				if (parmsConsumoHistorico[0] != null) {
					numeroConsumoFaturadoMes = (Integer) parmsConsumoHistorico[0];
				}
			}
			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				dadosConsumoAnterior.append(Util.completaString(""
						+ numeroConsumoFaturadoMes, 7));
			} else {
				dadosConsumoAnterior.append(Util.completaString(" ", 7));
			}
			// caso o id dos dados do consumo anterior for diferente de nulo

		} else {
			// senao completa com espacos em branco
			dadosConsumoAnterior.append(Util.completaString("", 7));
		}

		return dadosConsumoAnterior;
	}

	public StringBuilder gerarLinhasDescricaoServicoTarifas(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao)
			throws ControladorException {

		StringBuilder linhasDescricaoServicosTarifasTotal = new StringBuilder();
		// caso o valor da agua da conta seja maior que zero
		if (emitirContaHelper.getValorAgua() != null
				&& emitirContaHelper.getValorAgua().compareTo(
						new BigDecimal("0.00")) == 1) {
			// [SB0011] - Gerar Linhas da Tarifa de agua
			StringBuilder linhasTarifaAgua = gerarLinhasTarifaAgua(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao);
			linhasDescricaoServicosTarifasTotal.append(linhasTarifaAgua);
		}
		// caso o valor de agua de esgoto seja maior que zero
		if (emitirContaHelper.getValorEsgoto() != null
				&& emitirContaHelper.getValorEsgoto().compareTo(
						new BigDecimal("0.00")) == 1) {

			// [SB0012] - Gerar Linhas da tarifa de Esgoto
			StringBuilder linhasTarifaEsgoto = gerarLinhasTarifaEsgoto(emitirContaHelper);
			// caso a stringBuilder ja esteja preenchida
			if (linhasDescricaoServicosTarifasTotal != null
					&& linhasDescricaoServicosTarifasTotal.length() != 0) {
				// caso a stringBuilder ja esteja preenchida
				if (linhasTarifaEsgoto != null
						&& linhasTarifaEsgoto.length() != 0) {
					linhasDescricaoServicosTarifasTotal
							.append(linhasTarifaEsgoto);
				}
			} else {
				linhasDescricaoServicosTarifasTotal.append(linhasTarifaEsgoto);
			}

		}
		// caso o valor de debitos cobrados da conta seja maior que zero
		if (emitirContaHelper.getDebitos() != null
				&& emitirContaHelper.getDebitos().compareTo(
						new BigDecimal("0.00")) == 1) {
			// [SB0013] - Gerar Linhas de Debitos Cobrados
			StringBuilder linhasDebitoCobrados = gerarLinhasDebitoCobrados(emitirContaHelper);
			// caso a stringBuilder ja esteja preenchida
			if (linhasDescricaoServicosTarifasTotal != null
					&& linhasDescricaoServicosTarifasTotal.length() != 0) {
				// caso a stringBuilder ja esteja preenchida
				if (linhasDebitoCobrados != null
						&& linhasDebitoCobrados.length() != 0) {
					linhasDescricaoServicosTarifasTotal
							.append(linhasDebitoCobrados);
				}
			} else {
				linhasDescricaoServicosTarifasTotal
						.append(linhasDebitoCobrados);
			}

		}
		// caso o valor de creditos realizados seja maior que zero
		if (emitirContaHelper.getValorCreditos() != null
				&& emitirContaHelper.getValorCreditos().compareTo(
						new BigDecimal("0.00")) == 1) {
			// [SB0014] - Gerar Linhas de Credito Realizado
			StringBuilder linhasCreditoRealizados = gerarLinhasCreditosRealizados(emitirContaHelper);

			// caso a stringBuilder ja esteja preenchida
			if (linhasDescricaoServicosTarifasTotal != null
					&& linhasDescricaoServicosTarifasTotal.length() != 0) {
				if (linhasCreditoRealizados != null
						&& linhasCreditoRealizados.length() != 0) {
					linhasDescricaoServicosTarifasTotal
							.append(linhasCreditoRealizados);
				}
			} else {
				linhasDescricaoServicosTarifasTotal
						.append(linhasCreditoRealizados);
			}

		}
		// caso o valor dos impostos retidos seja maior que zero
		if (emitirContaHelper.getValorImpostos() != null
				&& emitirContaHelper.getValorImpostos().compareTo(
						new BigDecimal("0.00")) == 1) {
			// [SB0015] - Gerar Linhas dos Impostos Retidos
			StringBuilder linhasImpostosRetidos = gerarLinhasImpostosRetidos(emitirContaHelper);

			// caso a stringBuilder ja esteja preenchida
			if (linhasDescricaoServicosTarifasTotal != null
					&& linhasDescricaoServicosTarifasTotal.length() != 0) {
				// caso a stringBuilder ja esteja preenchida
				if (linhasImpostosRetidos != null
						&& linhasImpostosRetidos.length() != 0) {
					linhasDescricaoServicosTarifasTotal
							.append(linhasImpostosRetidos);
				}
			} else {
				linhasDescricaoServicosTarifasTotal
						.append(linhasImpostosRetidos);
			}

		}

		return linhasDescricaoServicosTarifasTotal;

	}

	protected StringBuilder gerarLinhasTarifaAgua(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao)
			throws ControladorException {
		StringBuilder linhasTarifaAgua = new StringBuilder();
		// -- Linha 1 --//
		// Canal
		// Fonte
		linhasTarifaAgua.append("1");
		linhasTarifaAgua.append("AGUA");
		linhasTarifaAgua.append(Util.completaString("", 125));
		Collection colecaoContaCategoriaComFaixas = null;
		try {
			colecaoContaCategoriaComFaixas = repositorioFaturamento
					.pesquisarContaCategoria(emitirContaHelper.getIdConta());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoContaCategoriaComFaixas != null
				&& !colecaoContaCategoriaComFaixas.isEmpty()) {
			Iterator iteratorContaCategoriaComFaixas = colecaoContaCategoriaComFaixas
					.iterator();
			while (iteratorContaCategoriaComFaixas.hasNext()) {
				ContaCategoria contaCategoria = (ContaCategoria) iteratorContaCategoriaComFaixas
						.next();

				// -- Linha 2 --//
				// Canal
				linhasTarifaAgua.append(" ");
				// Fonte
				linhasTarifaAgua.append("1");
				linhasTarifaAgua.append(" ");
				// descricao da categoria
				linhasTarifaAgua.append(Util.completaString(contaCategoria
						.getComp_id().getCategoria().getDescricao(), 13));
				// quantidade de economias
				linhasTarifaAgua.append(Util.adicionarZerosEsquedaNumero(3, ""
						+ contaCategoria.getQuantidadeEconomia()));
				// completa com espacos em branco
				linhasTarifaAgua.append(Util.completaString("", 2));
				if (contaCategoria.getQuantidadeEconomia() == 1) {
					linhasTarifaAgua.append("UNIDADE ");
				} else {
					linhasTarifaAgua.append("UNIDADES");
				}
				// completa com espacos em branco
				linhasTarifaAgua.append(Util.completaString("", 102));

				// -- Linha 3 --//
				// Canal
				linhasTarifaAgua.append(" ");
				// Fonte
				linhasTarifaAgua.append("1");
				// completa com espacos em branco
				linhasTarifaAgua.append(" ");
				// caso nao existam dados de medicao
				if (parmsMedicaoHistorico == null) {
					// Constante
					linhasTarifaAgua.append("TARIFA MÍNIMA");

					// Valor da tarifa mInima de Agua para a categoria por
					// economia
					BigDecimal qtdEconomia = Util
							.formatarMoedaRealparaBigDecimal(""
									+ contaCategoria.getQuantidadeEconomia());
					String valorTarifaMinima = "";
					if (contaCategoria.getValorTarifaMinimaAgua() != null
							&& qtdEconomia != null) {
						BigDecimal valorTarifaMinimaBigDecimal = contaCategoria
								.getValorTarifaMinimaAgua().divide(qtdEconomia,
										2, RoundingMode.UP);
						valorTarifaMinima = Util
								.formatarMoedaReal(valorTarifaMinimaBigDecimal);
					}

					linhasTarifaAgua.append(Util
							.completaStringComEspacoAEsquerda(
									valorTarifaMinima, 6));
					// completa com espacos em branco
					linhasTarifaAgua.append(" ");
					// Constante
					linhasTarifaAgua.append("POR UNIDADE");
					// completa com espacos em branco
					linhasTarifaAgua.append(Util.completaString("", 18));
					// Constante
					linhasTarifaAgua.append("MINIMO");
					// completa com espacos em branco
					linhasTarifaAgua.append(Util.completaString("", 11));
					// valor da agua para categoria
					String valorAgua = Util.formatarMoedaReal(emitirContaHelper
							.getValorAgua());
					linhasTarifaAgua.append(Util
							.completaStringComEspacoAEsquerda(valorAgua, 17));
					// completa com espacos em branco
					linhasTarifaAgua.append(Util.completaString("", 45));
				} else {
					// recupera a colecao de conta categoria consumo faixa
					Collection colecaoContaCategoriaConsumoFaixa = null;
					try {
						colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
								.pesquisarContaCategoriaFaixas(
										emitirContaHelper.getIdConta(),
										contaCategoria.getComp_id()
												.getCategoria().getId());
					} catch (ErroRepositorioException e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);
					}

					if (colecaoContaCategoriaConsumoFaixa != null
							&& !colecaoContaCategoriaConsumoFaixa.isEmpty()) {

						// Constante
						linhasTarifaAgua.append("ATE");
						// Constante
						linhasTarifaAgua.append(" ");
						// Consumo minimo de agua para a categoria por
						// economia

						BigDecimal qtdEconomia = Util
								.formatarMoedaRealparaBigDecimal(""
										+ contaCategoria
												.getQuantidadeEconomia());

						BigDecimal consumoMinimoAgua = null;
						if (contaCategoria.getConsumoMinimoAgua() != null) {
							consumoMinimoAgua = Util
									.formatarMoedaRealparaBigDecimal(""
											+ contaCategoria
													.getConsumoMinimoAgua());
						}

						BigDecimal consumoMinimaBigDecimal = new BigDecimal(
								"0.00");
						if (consumoMinimoAgua != null && qtdEconomia != null) {
							consumoMinimaBigDecimal = consumoMinimoAgua.divide(
									qtdEconomia, 2, RoundingMode.UP);
						}

						String consumoMinima = ""
								+ consumoMinimaBigDecimal.intValue();

						linhasTarifaAgua.append(Util
								.completaStringComEspacoAEsquerda(
										consumoMinima, 2));
						// completa com espacos em branco
						linhasTarifaAgua.append(" ");
						// Constante
						linhasTarifaAgua.append("M3");
						// completa com espacos em branco
						linhasTarifaAgua.append(Util.completaString("", 7));
						// Constante
						linhasTarifaAgua.append("- R$");
						// valor da tarifa minima de agua para a categoria por
						// economia
						BigDecimal valorTarifaMinimaBigDecimal = null;
						if (contaCategoria.getValorTarifaMinimaAgua() != null
								&& qtdEconomia != null) {
							valorTarifaMinimaBigDecimal = contaCategoria
									.getValorTarifaMinimaAgua().divide(
											qtdEconomia, 2, RoundingMode.UP);
						}
						String valorTarifaMinima = Util
								.formatarMoedaReal(valorTarifaMinimaBigDecimal);
						linhasTarifaAgua.append(Util
								.completaStringComEspacoAEsquerda(
										valorTarifaMinima, 6));
						// completa com espacos em branco
						linhasTarifaAgua.append(" ");
						// Constante
						linhasTarifaAgua.append(" (POR UNIDADE)");
						// completa com espados em branco
						linhasTarifaAgua.append(Util.completaString("", 8));
						// Consumo minimo de agua
						if (contaCategoria.getConsumoMinimoAgua() != null
								&& !contaCategoria.getConsumoMinimoAgua()
										.equals("")) {
							linhasTarifaAgua
									.append(Util
											.completaStringComEspacoAEsquerda(
													""
															+ contaCategoria
																	.getConsumoMinimoAgua(),
													4));
						} else {
							linhasTarifaAgua.append(Util
									.completaStringComEspacoAEsquerda("", 4));
						}
						// completa com espacos em branco
						linhasTarifaAgua.append(" ");
						// Constante
						linhasTarifaAgua.append("M3");
						// completa com espacos em branco
						linhasTarifaAgua.append(Util.completaString("", 10));
						// valor agua para categoria
						String valorAguaCategoria = "";
						if (contaCategoria.getValorTarifaMinimaAgua() != null) {
							valorAguaCategoria = Util
									.formatarMoedaReal(contaCategoria
											.getValorTarifaMinimaAgua());
						}
						linhasTarifaAgua.append(Util
								.completaStringComEspacoAEsquerda(
										valorAguaCategoria, 17));
						// completa com espacos em branco
						linhasTarifaAgua.append(Util.completaString("", 45));

						Iterator iteratorContaCategoriaConsumoFaixa = colecaoContaCategoriaConsumoFaixa
								.iterator();
						while (iteratorContaCategoriaConsumoFaixa.hasNext()) {
							ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iteratorContaCategoriaConsumoFaixa
									.next();

							// -- Linha 4 --//
							// Canal
							linhasTarifaAgua.append(" ");
							// Fonte
							linhasTarifaAgua.append("1");
							// completa com espacos em branco
							linhasTarifaAgua.append(" ");
							// caso a faixa seja a ultima ta tarifa de consumo
							if (contaCategoriaConsumoFaixa.getConsumoFaixaFim()
									.equals(999999)) {
								// Constante
								linhasTarifaAgua.append("ACIMA DE");
								// Consumo inicial da faixa menos 1 m3
								String consumoInicialFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaInicio() - 1);
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(
												consumoInicialFaixa, 3));
								// Constante
								linhasTarifaAgua.append(" M3  - R$");
								// valor da tarifa na faixa
								String valorTarifaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorTarifaFaixa());
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(
												valorTarifaFaixa, 6));
								// Constante
								linhasTarifaAgua.append(" POR M3");
								// completa com espacos em branco
								linhasTarifaAgua.append(Util.completaString("",
										14));

								// consumo da agua
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(""
												+ contaCategoriaConsumoFaixa
														.getConsumoAgua()
												* qtdEconomia.intValue(), 6));
								// Constante
								linhasTarifaAgua.append(" M3");
								// completa com espacos em branco
								linhasTarifaAgua.append(Util.completaString("",
										10));
								// valor da agua na faixa
								String valorAguaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorAgua().multiply(
														qtdEconomia));
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(
												valorAguaFaixa, 17));
								// completa com espacos em branco
								linhasTarifaAgua.append(Util.completaString("",
										45));

							} else {
								// Consumo inicial da faixa
								String consumoInicialFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaInicio());
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(
												consumoInicialFaixa, 2));
								// Constante
								linhasTarifaAgua.append(" M3 A");
								// consumo final da faixa
								String consumoFinalFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaFim());
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(
												consumoFinalFaixa, 3));
								// Constante
								linhasTarifaAgua.append(" M3");
								// completa com espacos em branco
								linhasTarifaAgua.append(Util.completaString("",
										3));
								// Constante
								linhasTarifaAgua.append("- R$");
								// valor da tarifa na faixa
								String valorTarifaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorTarifaFaixa());
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(
												valorTarifaFaixa, 6));
								// Constante
								linhasTarifaAgua.append(" POR M3");
								// completa com espacos em branco
								linhasTarifaAgua.append(Util.completaString("",
										14));
								// consumo de Agua na faixa
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(""
												+ contaCategoriaConsumoFaixa
														.getConsumoAgua()
												* qtdEconomia.intValue(), 6));
								// Constante
								linhasTarifaAgua.append(" M3");
								// completa com espacos em branco
								linhasTarifaAgua.append(Util.completaString("",
										10));
								// valor da agua na faixa
								String valorAguaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorAgua().multiply(
														qtdEconomia));
								linhasTarifaAgua.append(Util
										.completaStringComEspacoAEsquerda(
												valorAguaFaixa, 17));
								// completa com espacos em branco
								linhasTarifaAgua.append(Util.completaString("",
										45));

							}
						}
					} else {
						linhasTarifaAgua.append("CONSUMO DE ÁGUA");
						linhasTarifaAgua.append(Util
								.completaStringComEspacoAEsquerda(""
										+ contaCategoria.getConsumoAgua(), 6)
								+ " M3");
						linhasTarifaAgua.append(Util
								.completaStringComEspacoAEsquerda(Util
										.formatarMoedaReal(contaCategoria
												.getValorAgua()), 59));

						linhasTarifaAgua.append(Util.completaString("", 45));

					}

				}
			}
		}

		return linhasTarifaAgua;
	}

	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {


		Integer anoMesReferenciaFinal = sistemaParametro.getAnoMesFaturamento();
		int anoMesSubtraido = Util
				.subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
		Integer dataVencimentoFinalInteger = sistemaParametro
				.getAnoMesArrecadacao();
		String anoMesSubtraidoString = ""
				+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);
		int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
		int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

		// recupera o ultimo dia do anomes e passa a data como parametro
		Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
		dataVencimentoFinal.set(Calendar.YEAR, ano);
		dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
		dataVencimentoFinal.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
				.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date dataFinalDate = dataVencimentoFinal.getTime();

		// converte String em data
		Date dataVencimento = Util.converteStringParaDate("01/01/1900");

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
				.obterDebitoImovelOuCliente(1,
						"" + emitirContaHelper.getIdImovel(), null, null,
						"190001", "" + anoMesSubtraido, dataVencimento,
						dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null);

		return debitoImovelClienteHelper;
	}
	
	/**
	 * Obter Os servicos cobrados na conta 
	 *
	 * @author Tiago Moreno
	 * @date 01/07/2008
	 *
	 * @param conta
	 * @return
	 * @throws ControladorException
	 */
	public Collection<DebitoCobradoAgrupadoHelper> obterServicoesCobradosConta(
			Conta conta) throws ControladorException {
		Collection retorno = new ArrayList();

		// Criacao das colecoes
		Collection colecaoDebitoCobradoArray = null;

		try {
			colecaoDebitoCobradoArray = repositorioFaturamento
					.buscarDebitosCobradosEmitirContaCaern(conta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		if (colecaoDebitoCobradoArray != null
				&& !colecaoDebitoCobradoArray.isEmpty()) {

			Iterator colecaoDebitoCobradoArrayIterator = colecaoDebitoCobradoArray
					.iterator();

			while (colecaoDebitoCobradoArrayIterator.hasNext()) {

				// Obtem os dados do debito cobrado
				Object[] debitoCobradoArray = (Object[]) colecaoDebitoCobradoArrayIterator
						.next();

				DebitoCobradoAgrupadoHelper debitoCobrado = new DebitoCobradoAgrupadoHelper();

				// ID do debito
				debitoCobrado.setIdDebitoTipo((Integer) debitoCobradoArray[0]);

				debitoCobrado
						.setDescricaoDebitoTipo((String) debitoCobradoArray[1]);
				debitoCobrado.setNumeroPrestacao(new Integer(
						(Short) debitoCobradoArray[2]));
				debitoCobrado.setNumeroPrestacaoDebito(new Integer(
						(Short) debitoCobradoArray[3]));
				debitoCobrado
						.setValorDebito((BigDecimal) debitoCobradoArray[4]);

				retorno.add(debitoCobrado);
			}
		}

		return retorno;
	}
	
	/**
	 * [UC0482]Emitir 2 Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContas(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,
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

			String nomeCliente = "";
			if (emitirContaHelper.getNomeImovel() != null && !emitirContaHelper.getNomeImovel().trim().equals("")) {

				nomeCliente = emitirContaHelper.getNomeImovel();
				emitirContaHelper.setNomeCliente(nomeCliente);
				
				emitirContaHelper.setCpf("");
				emitirContaHelper.setCnpj("");
			}
		

			// Linha 5
			// --------------------------------------------------------------
			// recupera endereco do imovel
			String enderecoImovel = "";
			try {
				enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatado(emitirContaHelper.getIdImovel());
			} catch (ControladorException e1) {
				e1.printStackTrace();
			}
			emitirContaHelper.setEnderecoImovel(enderecoImovel);

			// Linha 6
			// --------------------------------------------------------------
			// instancia um imovel com os dados da conta para recuperar a
			// inscricao que esta no objeto imovel
			Imovel imovel = new Imovel();
			Localidade localidade = new Localidade();
			localidade.setId(emitirContaHelper.getIdLocalidade());
			imovel.setLocalidade(localidade);
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setCodigo(emitirContaHelper.getCodigoSetorComercialConta());
			imovel.setSetorComercial(setorComercial);
			Quadra quadra = new Quadra();
			quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());
			imovel.setQuadra(quadra);
			imovel.setLote(emitirContaHelper.getLoteConta());
			imovel.setSubLote(emitirContaHelper.getSubLoteConta());
			// Inscricao do imovel
			emitirContaHelper.setInscricaoImovel(imovel.getInscricaoFormatada());

			// Linha 7
			// --------------------------------------------------------------
			String idClienteResponsavel = "";
			String enderecoClienteResponsavel = "";
			Integer idImovelContaEnvio = emitirContaHelper.getIdImovelContaEnvio();
			// caso a colecao de contas seja de entrega para o cliente responsavel
			if (idImovelContaEnvio != null
				&& (idImovelContaEnvio.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) 
					|| idImovelContaEnvio.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {

				Integer idClienteResponsavelInteger = null;
				idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(
						emitirContaHelper.getIdConta(), false);

				if (idClienteResponsavelInteger != null && !idClienteResponsavelInteger.equals("")) {
					idClienteResponsavel = idClienteResponsavelInteger.toString();
					// [UC0085]Obter Endereco
					enderecoClienteResponsavel = getControladorEndereco()
						.pesquisarEnderecoClienteAbreviado(idClienteResponsavelInteger);
				}

			}
			emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
			emitirContaHelper.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

			// Linha 8
			// --------------------------------------------------------------

			// [SB0002] - Determinar tipo de ligacao e tipo de Medicao
			Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
			Integer tipoLigacao = parmSituacao[0];
			Integer tipoMedicao = parmSituacao[1];

			// Linha 9
			// --------------------------------------------------------------
			// cria uma stringBuilder para recuperar o resultado do [SB0003]
			// o tamanho da string que vem como resultado e de 20 posicoes
			StringBuilder obterDadosConsumoMedicaoAnterior = null;

			// chama o [SB0003] -Obter Dados do Consumo e Medicao Anterior
			// passando a quantidade de Meses Igual a 1
			// e o tipo de ligacao e medicao recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), 
				emitirContaHelper.getAmReferencia(), 1, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes1(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medicao Anterior
			// passando a quantidade de Meses Igual a 4
			// e o tipo de ligacao e medicao recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 4, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes4(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 10
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medicao Anterior
			// passando a quantidade de Meses Igual a 2
			// e o tipo de ligacao e medicao recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 2, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes2(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medicao Anterior
			// passando a quantidade de Meses Igual a 5
			// e o tipo de ligaca e medicao recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 5, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes5(obterDadosConsumoMedicaoAnterior.toString());
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros da medicao historico do
			// [SB0004] - Obter Dados da Medicao da Conta
			Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
			// Leitura Anterior
			String leituraAnterior = "";
			// Leitura Atual
			String leituraAtual = "";
			// Data Leitura Anterior
			String dataLeituraAnterior = "";
			// Leitura Anterior
			String dataLeituraAtual = "";
			// Leitura Situacao Atual
			// String leituraSituacaoAtual = "";
			// Leitura Anormalidade Faturamento
			String leituraAnormalidadeFaturamento = "";
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
					// leituraSituacaoAtual = ""
					// + (Integer) parmsMedicaoHistorico[4];
				}

				if (parmsMedicaoHistorico[5] != null) {
					leituraAnormalidadeFaturamento = ""	+ (Integer) parmsMedicaoHistorico[5];
				}
			}
			emitirContaHelper.setDataLeituraAnterior(dataLeituraAnterior);
			emitirContaHelper.setDataLeituraAtual(dataLeituraAtual);
			String diasConsumo = "";
			if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
				// calcula a quantidade de dias de consumo que  a
				// quantidade de dias
				// entre a data de leitura
				// anterior(parmsMedicaoHistorico[2]) e a data de leitura
				// atual(parmsMedicaoHistorico[3])
				diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas(
						(Date) parmsMedicaoHistorico[3],(Date) parmsMedicaoHistorico[2]);
			}
			// recupera os parametros de consumo faturamento e consumo medio diario
			// [SB0005] - Obter Consumo Faturado e Consumo Medio Diario
			String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
					emitirContaHelper, tipoMedicao, diasConsumo);
			String consumoFaturamento = parmsConsumo[0];
			emitirContaHelper.setConsumoFaturamento(consumoFaturamento);

			String consumoMedioDiario = parmsConsumo[1];
			emitirContaHelper.setConsumoMedioDiario(consumoMedioDiario);
			// Fim Chamar Sub-Fluxo
			// Leitura Anterior
			leituraAnterior = Util.completaString(leituraAnterior, 7);
			emitirContaHelper.setLeituraAnterior(leituraAnterior);
			// Leitura Atual
			leituraAtual = Util.completaString(leituraAtual, 7);
			emitirContaHelper.setLeituraAtual(leituraAtual);
			// Dias de consumo
			diasConsumo = Util.completaString(diasConsumo, 2);
			emitirContaHelper.setDiasConsumo(diasConsumo);

			// Linha 11
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medicao Anterior
			// passando a quantidade de Meses Igual a 3
			// e o tipo de ligacao e medicao recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
					emitirContaHelper.getIdImovel(), emitirContaHelper
							.getAmReferencia(), 3, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes3(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medicao Anterior
			// passando a quantidade de Meses Igual a 6
			// e o tipo de ligacao e medicao recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
					emitirContaHelper.getIdImovel(), emitirContaHelper
							.getAmReferencia(), 6, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes6(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 12
			// --------------------------------------------------------------
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros do consumo historico da conta
			// [SB0006] - Obter Dados de Consumo da Conta
			Object[] parmsConsumoHistorico = null;
			String descricaoAbreviadaTipoConsumo = "";
			String descricaoTipoConsumo = "";
			String consumoMedio = "";
			String descricaoAbreviadaAnormalidadeConsumo = "";
			String descricaoAnormalidadeConsumo = "";
			String consumoRateio = "";
			// caso o tipo de ligacao for diferente de nulo
			if (tipoLigacao != null) {
				try {
					parmsConsumoHistorico = getControladorMicromedicao().obterDadosConsumoConta(
						emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),tipoLigacao);
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				if (parmsConsumoHistorico != null) {
					// descricao abreviada tipo de consumo
					if (parmsConsumoHistorico[0] != null) {
						descricaoAbreviadaTipoConsumo = (String) parmsConsumoHistorico[0];
					}
					// descricao tipo de consumo
					if (parmsConsumoHistorico[1] != null) {
						descricaoTipoConsumo = (String) parmsConsumoHistorico[1];
					}
					// Consumo medio
					if (parmsConsumoHistorico[2] != null) {
						consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
					}
					// descricao abreviada anormalidade de consumo
					if (parmsConsumoHistorico[3] != null) {
						descricaoAbreviadaAnormalidadeConsumo = (String) parmsConsumoHistorico[3];
					}
					// descricao anormalidade de consumo
					if (parmsConsumoHistorico[4] != null) {
						descricaoAnormalidadeConsumo = (String) parmsConsumoHistorico[4];
					}
					// Consumo medio
					if (parmsConsumoHistorico[5] != null) {
						consumoRateio = "" + (Integer) parmsConsumoHistorico[5];
					}
				}
			}

			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);

			// Fim Chamar Sub-Fluxo

			// Linha 13
			// --------------------------------------------------------------

			// Inicio Chamar Sub-Fluxo
			// soma a quantidades de economias da tabela contaCategoria
			// [SB0007] - Obter Dados da Medicao da Conta
			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(emitirContaHelper.getIdConta(), false);
			emitirContaHelper.setQuantidadeEconomiaConta(""	+ quantidadeEconomiaConta);
			// Fim Chamar Sub-Fluxo

			// Consumo por Economia
			// transforma o consumoFaturamento para um bigDecimal
			BigDecimal consumoFaturadoBigDecimal = null;
			if (consumoFaturamento != null && !consumoFaturamento.equals("")) {
				consumoFaturadoBigDecimal = Util.formatarMoedaRealparaBigDecimal(consumoFaturamento);

			}
			// transforma a quantidade de economias da conta para um
			// bigDecimal
			BigDecimal qtdEconomiasBigDecimal = null;
			if (quantidadeEconomiaConta != null && !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util.formatarMoedaRealparaBigDecimal("" + quantidadeEconomiaConta);
			}
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null && qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util.formatarMoedaReal(consumoEconomiaBigDecimal);
				emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(0, (consumoEconomia.length() - 3)));
			}

			// Inicio Chamar Sub-Fluxo
			// concatena os campos dos sub-fluxos anteriores
			// [SB0008] - Obter Dados da Medicao da Conta
			StringBuilder codigoAuxiliar = new StringBuilder();
			// leitura situacao atual
			// tipo de consumo
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaTipoConsumo, 1));
			// tipo de contrato
			codigoAuxiliar.append(Util.completaString("", 1));
			// anormalidade de leitura
			codigoAuxiliar.append(Util.completaString(leituraAnormalidadeFaturamento, 2));
			// anormalidade de consumo
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaAnormalidadeConsumo, 2));

			// perfil do imovel
			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString("" + emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}
			// dias do consumo
			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			// Consumo medio do imovel
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			// Fim Chamar Sub-Fluxo
			emitirContaHelper.setCodigoAuxiliarString(codigoAuxiliar.toString());

			// chama o [SB0009] - Obter Mensagem de Rateio de Consumo Fixo
			// de Esgoto
			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao);
			// mensagem de rateio de consumo ou consumo fixo de esgoto
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo.toString());

			// Linha 16
			// --------------------------------------------------------------
			// chama o [SB0010] - Gerar Linhas da Descricao dos Servicos e Tarifas

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = 
				gerarLinhasDescricaoServicoTarifasRelatorio(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao, false);
			emitirContaHelper.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

			// Linha 17
			// --------------------------------------------------------------
			// cria um objeto conta para calcular o valor da conta
			Conta conta = new Conta();
			conta.setValorAgua(emitirContaHelper.getValorAgua());
			conta.setValorEsgoto(emitirContaHelper.getValorEsgoto());
			conta.setValorCreditos(emitirContaHelper.getValorCreditos());
			conta.setDebitos(emitirContaHelper.getDebitos());
			conta.setValorImposto(emitirContaHelper.getValorImpostos());
			BigDecimal valorConta = conta.getValorTotalContaBigDecimal();

			emitirContaHelper.setValorContaString(Util.formatarMoedaReal(valorConta));
			emitirContaHelper.setValorConta(valorConta);

			if (contaSemCodigoBarras.equals(ConstantesSistema.SIM)
					|| valorConta.compareTo(new BigDecimal("0.00")) == 0) {
				emitirContaHelper.setContaSemCodigoBarras("1");
			} else {
				emitirContaHelper.setContaSemCodigoBarras("2");
			}
			
			if (contaSemCodigoBarras.equals(ConstantesSistema.NAO)){
				
				FiltroPagamento filtroPagamento = new FiltroPagamento();
				filtroPagamento.adicionarParametro(new ParametroSimples(
						FiltroPagamento.CONTA_ID, idContaEP));
				Collection colecaoPagamentos = Fachada.getInstancia().pesquisar(filtroPagamento, Pagamento.class.getName());
				
				if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()){
					
					emitirContaHelper.setContaSemCodigoBarras("1");
				}
				
			}

			// chama o [SB0016] - Obter Mensagem da Conta em 3 Partes
			String[] parmsPartesConta = obterMensagemConta3Partes(
					emitirContaHelper, sistemaParametro);

			// Linha 18
			// --------------------------------------------------------------
			emitirContaHelper.setPrimeiraParte(parmsPartesConta[0]);

			// Linha 19
			// --------------------------------------------------------------
			emitirContaHelper.setSegundaParte(parmsPartesConta[1]);

			// Linha 20
			// --------------------------------------------------------------
			emitirContaHelper.setTerceiraParte(parmsPartesConta[2]);

			// Linha 21
			// --------------------------------------------------------------
			int anoMesReferenciaSubtraido = Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), 1);
			emitirContaHelper.setMesAnoFormatado(Util.formatarAnoMesParaMesAno(anoMesReferenciaSubtraido));

			// Linha 22
			// --------------------------------------------------------------
			Object[] parmsQualidadeAgua = null;
			parmsQualidadeAgua = pesquisarParmsQualidadeAgua(emitirContaHelper);

			// numero indice turbidez da qualidade agua
			String numeroIndiceTurbidez = "";
			// numero cloro residual da qualidade agua
			String numeroCloroResidual = "";
			if (parmsQualidadeAgua != null) {
				if (parmsQualidadeAgua[0] != null) {
					numeroIndiceTurbidez = Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]);
				}

				if (parmsQualidadeAgua[1] != null) {
					numeroCloroResidual = Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]);
				}
			}
			emitirContaHelper.setNumeroIndiceTurbidez(numeroIndiceTurbidez);
			emitirContaHelper.setNumeroCloroResidual(numeroCloroResidual);

			// Linha 23
			// --------------------------------------------------------------
			// Considerar as contas do tipo debito automatico como tipo de conta normal
			// [SB0018 - Gerar Linhas das DemaisContas]
			Integer digitoVerificadorConta = new Integer(""	+ emitirContaHelper.getDigitoVerificadorConta());
			// formata ano mes para mes ano
			String anoMes = "" + emitirContaHelper.getAmReferencia();
			String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

			String representacaoNumericaCodBarra = "";
			
			// Linha28
			Date dataValidade = obterDataValidade2ViaConta(emitirContaHelper);
			emitirContaHelper.setDataValidade(Util.formatarData(dataValidade));
			
			//Se valor da conta maior que o valor limite
			//emite uma Ficha de Compensacao(Boleto bancario)
			
				if (contaSemCodigoBarras.equals(ConstantesSistema.NAO)
						|| valorConta.compareTo(new BigDecimal("0.00")) != 0) {

					representacaoNumericaCodBarra = this.getControladorArrecadacao()
							.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
									emitirContaHelper.getIdLocalidade(),
									emitirContaHelper.getIdImovel(), mesAno,
									digitoVerificadorConta, null, null, null, null,
									null, null, null);

					// Linha 24
					// Formata a representacao numerica do codigo de barras
					String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra.substring(0, 11)
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
					emitirContaHelper.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

					// Linha 25
					String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
							.substring(0, 11)
							+ representacaoNumericaCodBarra.substring(12, 23)
							+ representacaoNumericaCodBarra.substring(24, 35)
							+ representacaoNumericaCodBarra.substring(36, 47);
					emitirContaHelper.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
					
			}
			
			colecaoEmitirContaHelper.add(emitirContaHelper);

			
			if (sistemaParametro.getValorSegundaVia().compareTo(new BigDecimal("0.00")) > 0) {
				if (cobrarTaxaEmissaoConta) {
					this.gerarDebitoACobrarTaxaEmissaoConta(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia());
				}
			}

		}

		return colecaoEmitirContaHelper;
	}
	
	/**
	 * [UC0482] Emitir Segunda Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/02/2007
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarTaxaEmissaoConta(Integer idImovel,
			int anoMesReferencia) throws ControladorException {

		try {

			Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

			// Recupera os parametros do sistema
			SistemaParametro sistema = getControladorUtil()
					.pesquisarParametrosDoSistema();

			// Instancia a forma de cobranca para cobranca em conta
			CobrancaForma cobrancaForma = new CobrancaForma();
			cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);

			// Instancia a situacao do debito para normal
			DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
			debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);

			// Recupera o tipo de debito referente a despesa postal
			DebitoTipo debitoTipo = this
					.getDebitoTipoHql(DebitoTipo.TAXA_COBRANCA);

			// Recupera a data atual
			Date dataAtual = new Date(System.currentTimeMillis());

			/*
			 * Caso o perfil do imovel seja tarifa social o valor vai ser o
			 * valor da tarifa social Caso contrario o valor da tarifa vai ser o
			 * normal.
			 */
			if (!imovel.getImovelPerfil().getId().equals(
					ImovelPerfil.TARIFA_SOCIAL)) {
				// caso o imovel nao seja enquadrado em tarifa social

				// Obtem o valor da Tarifa Normal
				BigDecimal valorMinimaTarifaNormal = this.repositorioFaturamento
						.obterValorTarifa(ConsumoTarifa.CONSUMO_NORMAL);

				// Caso o valor da tarifa normal esteja nulo seta o valor para
				// zero
				if (valorMinimaTarifaNormal == null) {
					valorMinimaTarifaNormal = new BigDecimal("0");
				}

				// valor = valorMinimaTarifaNormal;

			} else if (imovel.getImovelPerfil().getId().equals(
					ImovelPerfil.TARIFA_SOCIAL)) {
				// caso o imovel seja enquadrado em tarifa social

				// Obtem o valor da Tarifa Social
				BigDecimal valorMinimaTarifaSocial = this.repositorioFaturamento
						.obterValorTarifa(ConsumoTarifa.CONSUMO_SOCIAL);

				// Caso o valor da tarifa social esteja nulo seta o valor para
				// zero
				if (valorMinimaTarifaSocial == null) {
					valorMinimaTarifaSocial = new BigDecimal("0");
				}

				// valor = valorMinimaTarifaSocial;
			}

			// inclui Debito A Cobrar Geral
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral
					.setIndicadorHistorico(DebitoACobrarGeral.INDICADOR_NAO_POSSUI_HISTORICO);
			debitoACobrarGeral.setUltimaAlteracao(new Date());
			Integer idDebitoACobrarGeral = (Integer) this.getControladorUtil()
					.inserir(debitoACobrarGeral);
			debitoACobrarGeral.setId(idDebitoACobrarGeral);

			// Cria uma instancia de debito a cobrar
			DebitoACobrar debitoACobrar = new DebitoACobrar();
			debitoACobrar.setId(debitoACobrarGeral.getId());
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

			// Seta o Imovel
			debitoACobrar.setImovel(imovel);

			// Seta o Debito Tipo
			debitoACobrar.setDebitoTipo(debitoTipo);

			// Seta Data e Hora Atual
			debitoACobrar.setGeracaoDebito(dataAtual);

			// Seta ano/mes da conta emitida como 2 via
			debitoACobrar.setAnoMesReferenciaDebito(anoMesReferencia);

			// Seta Ano/Mes de Cobranca
			debitoACobrar.setAnoMesCobrancaDebito(sistema
					.getAnoMesArrecadacao());

			
			
			// Seta Ano/Mes Referencia do Faturamento
			//Alteracao CRC1389 Data:09/03/2009 
			//Author: Romulo Aurelio 
			//Analista: Rosana Carvalho

			int anoMesAtual =  Util.getAnoMesComoInt(new Date());
			
			if(sistema
					.getAnoMesFaturamento().compareTo(anoMesAtual) < 0){
			
				debitoACobrar.setAnoMesReferenciaContabil(anoMesAtual);
			
			}else{
				debitoACobrar.setAnoMesReferenciaContabil(sistema
						.getAnoMesFaturamento());
			}
			//Fim Alteracao CRC1389 Data:09/03/2009
			

			// Seta Valor do Debito
			BigDecimal valorFinal = sistema.getValorSegundaVia();

			debitoACobrar.setValorDebito(valorFinal);

			// Seta Numero de Prestacoes do Debito
			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));

			// Seta Numero de Prestacoes Cobradas
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));

			// Seta Localidade
			debitoACobrar.setLocalidade(imovel.getLocalidade());

			// Seta Quadra
			debitoACobrar.setQuadra(imovel.getQuadra());

			// Seta Codigo do Setor Comercial
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial()
					.getCodigo());

			// Seta Numero Quadra
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());

			// Seta Lote
			debitoACobrar.setNumeroLote(imovel.getLote());

			// Seta SubLote
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());

			// Seta Taxa de Juros do Financiamento
			debitoACobrar.setPercentualTaxaJurosFinanciamento(new BigDecimal(
					"0"));

			// Seta Financiamento Tipo
			debitoACobrar.setFinanciamentoTipo(debitoTipo
					.getFinanciamentoTipo());

			// Seta Lancamento Item Contabil
			debitoACobrar.setLancamentoItemContabil(debitoTipo
					.getLancamentoItemContabil());

			// Seta Debito Credito Situacao
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);

			// Seta Cobranca Forma
			debitoACobrar.setCobrancaForma(cobrancaForma);

			// Seta a data de ultima alteracao
			debitoACobrar.setUltimaAlteracao(new Date());

			Integer idDebitoACobrar = (Integer) this.getControladorUtil()
					.inserir(debitoACobrar);

			debitoACobrar.setId(idDebitoACobrar);

			// Recupera Categorias por Imovel
			Collection<Categoria> colecaoCategoria = this
					.getControladorImovel().obterQuantidadeEconomiasCategoria(
							imovel);
			// Recupera Valores por Categorias
			Collection<BigDecimal> colecaoValoresCategorias = this
					.getControladorImovel().obterValorPorCategoria(
							colecaoCategoria, valorFinal);
			// Insere debito a cobrar por categoria
			inserirDebitoACobrarCategoria(colecaoCategoria,
					colecaoValoresCategorias, debitoACobrar);

		} catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
}
