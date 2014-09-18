package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.bean.ClienteImovelEconomiaHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelEconomiaHelper;
import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioDadosEconomiaImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe RelatorioDadosEconomiaImovel
	 */
	public RelatorioDadosEconomiaImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DADOS_ECONOMIA_IMOVEL);
	}

	@Deprecated
	public RelatorioDadosEconomiaImovel() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Imovel imovelParametrosInicial = (Imovel) getParametro("imovelParametrosInicial");
		Imovel imovelParametrosFinal = (Imovel) getParametro("imovelParametrosFinal");
		ClienteImovel clienteImovelParametros = (ClienteImovel) getParametro("clienteImovelParametros");
		Municipio municipio = (Municipio) getParametro("municipio");
		Bairro bairro = (Bairro) getParametro("bairro");
		MedicaoHistorico medicaoHistoricoParametrosInicial = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosInicial");
		MedicaoHistorico medicaoHistoricoParametrosFinal = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosFinal");
		ConsumoHistorico consumoHistoricoParametrosInicial = (ConsumoHistorico) getParametro("consumoHistoricoParametrosInicial");
		ConsumoHistorico consumoHistoricoParametrosFinal = (ConsumoHistorico) getParametro("consumoHistoricoParametrosFinal");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegionalParametro");
		Categoria categoria = (Categoria) getParametro("categoria");
		Subcategoria subcategoria = (Subcategoria) getParametro("subcategoria");
		CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) getParametro("cobrancaSituacao");
		String indicadorMedicao = (String) getParametro("indicadorMedicaoParametro");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// Recupera os parâmetros utilizados na formação da query

		// id da genrencia regional
		String gerenciaRegionalPesquisa = (String) getParametro("gerenciaRegional");
		// id da unidade negocio
		String idUnidadeNegocio = (String) getParametro("unidadeNegocio");

		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicaoPesquisa = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");

		Fachada fachada = Fachada.getInstancia();

		Collection imoveisRelatoriosHelper = fachada
				.gerarRelatorioDadosEconomiaImovel(imovelCondominioID,
						imovelPrincipalID, situacaoAgua, consumoMinimoInicial,
						consumoMinimoFinal, situacaoLigacaoEsgoto,
						consumoMinimoFixadoEsgotoInicial,
						consumoMinimoFixadoEsgotoFinal,
						intervaloPercentualEsgotoInicial,
						intervaloPercentualEsgotoFinal,
						intervaloMediaMinimaImovelInicial,
						intervaloMediaMinimaImoveFinal,
						intervaloMediaMinimaHidrometroInicial,
						intervaloMediaMinimaHidrometroFinal, perfilImovelID,
						pocoTipoID, tipoSituacaoFaturamentoID,
						situacaoCobrancaID, tipoSituacaoEspecialCobrancaID,
						anormalidadeElo, areaConstruidaInicial,
						areaConstruidaFinal, ocorrenciaCadastro, tarifaConsumo,
						gerenciaRegionalPesquisa, localidadeOrigem,
						localidadeDestino, setorComercialOrigemCD,
						setorComercialDestinoCD, qudraOrigem, quadraDestino,
						loteOrigem, loteDestino, cep, logradouroID, bairroID,
						municipioID, tipoMedicaoID, indicadorMedicaoPesquisa,
						subCategoriaID, categoriaImovelID,
						quantidadeEconomiasInicial, quantidadeEconomiasFinal,
						diaVencimentoAlternativo, clienteID, clienteTipoID,
						clienteRelacaoTipoID, numeroPontosInicial,
						numeroPontosFinal, numeroMoradoresInicial,
						numeroMoradoresFinal, areaConstruidaFaixa,
						idUnidadeNegocio);

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioDadosEconomiaImovelBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (imoveisRelatoriosHelper != null
				&& !imoveisRelatoriosHelper.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator imovelRelatorioIterator = imoveisRelatoriosHelper
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (imovelRelatorioIterator.hasNext()) {

				ImovelRelatorioHelper imovelRelatorioHelper = (ImovelRelatorioHelper) imovelRelatorioIterator
						.next();

				Iterator subcategoriaIterator = imovelRelatorioHelper
						.getSubcategorias().iterator();

				while (subcategoriaIterator.hasNext()) {

					ImovelSubcategoriaHelper imovelSubcategoriaHelper = (ImovelSubcategoriaHelper) subcategoriaIterator
							.next();

					if (imovelSubcategoriaHelper.getColecaoImovelEconomia() == null
							|| imovelSubcategoriaHelper
									.getColecaoImovelEconomia().isEmpty()) {

						relatorioBean = new RelatorioDadosEconomiaImovelBean(

								// Código da Gerência Regional
								imovelRelatorioHelper.getIdGerenciaRegional() == null ? ""
										: ""
												+ imovelRelatorioHelper
														.getIdGerenciaRegional(),

								// Descrição da Gerência Regional
								imovelRelatorioHelper
										.getDescricaoGerenciaRegional() == null ? ""
										: imovelRelatorioHelper
												.getDescricaoGerenciaRegional(),

								// Código da Localidade
								imovelRelatorioHelper.getIdLocalidade() == null ? ""
										: ""
												+ imovelRelatorioHelper
														.getIdLocalidade(),

								// Descrição da Localidade
								imovelRelatorioHelper.getDescricaoLocalidade() == null ? ""
										: imovelRelatorioHelper
												.getDescricaoLocalidade(),

								// Código do Setor Comercial
								imovelRelatorioHelper.getCodigoSetorComercial() == null ? ""
										: ""
												+ imovelRelatorioHelper
														.getCodigoSetorComercial(),

								// Descrição do Setor Comercial
								imovelRelatorioHelper
										.getDescricaoSetorComercial() == null ? ""
										: imovelRelatorioHelper
												.getDescricaoSetorComercial(),

								// Matrícula do Imóvel
								"" + imovelRelatorioHelper.getMatriculaImovel(),

								// Inscrição Formatada
								imovelRelatorioHelper.getInscricaoImovel(),

								// Endereço
								imovelRelatorioHelper.getEndereco(),

								// Subcategoria
								imovelSubcategoriaHelper.getSubcategoria(),

								// Categoria

								imovelSubcategoriaHelper.getCategoria(),

								// Quantidade de Economias

								""
										+ imovelSubcategoriaHelper
												.getQuantidadeEconomias(),

								// Nome do Cliente Usuário
								null,

								// Complemento Endereço
								null,

								// Pontos de Utilização
								null,

								// Número de Moradores
								null,

								// Número IPTU
								null,

								// Número Contrato Celpe
								null,

								// Área Construída
								null,

								// Nome Cliente
								null,

								// Tipo Relação
								null,

								// CPF / CNPJ
								null,

								// Data Início Relação
								null,

								// Data Fim Relação
								null,

								// Motivo Fim Relação
								null

						);

						// Fim da Construção do objeto
						// RelatorioDadosEconomiaImovelBean
						// para ser colocado no relatório

						// adiciona o bean a coleção
						
						relatorioBean.setNomeClienteUsuario(imovelRelatorioHelper.getClienteUsuarioNome());
						
						relatorioBeans.add(relatorioBean);

					} else {

						Iterator imovelEconomiaIterator = imovelSubcategoriaHelper
								.getColecaoImovelEconomia().iterator();

						while (imovelEconomiaIterator.hasNext()) {

							ImovelEconomiaHelper imovelEconomiaHelper = (ImovelEconomiaHelper) imovelEconomiaIterator
									.next();

							if (imovelEconomiaHelper
									.getClienteImovelEconomiaHelper() == null) {
								// Início da Construção do objeto
								// RelatorioDadosEconomiaImovelBean
								// para ser colocado no relatório
								relatorioBean = new RelatorioDadosEconomiaImovelBean(

										// Código da Gerência Regional
										imovelRelatorioHelper
												.getIdGerenciaRegional() == null ? ""
												: ""
														+ imovelRelatorioHelper
																.getIdGerenciaRegional(),

										// Descrição da Gerência Regional
										imovelRelatorioHelper
												.getDescricaoGerenciaRegional() == null ? ""
												: imovelRelatorioHelper
														.getDescricaoGerenciaRegional(),

										// Código da Localidade
										imovelRelatorioHelper.getIdLocalidade() == null ? ""
												: ""
														+ imovelRelatorioHelper
																.getIdLocalidade(),

										// Descrição da Localidade
										imovelRelatorioHelper
												.getDescricaoLocalidade() == null ? ""
												: imovelRelatorioHelper
														.getDescricaoLocalidade(),

										// Código do Setor Comercial
										imovelRelatorioHelper
												.getCodigoSetorComercial() == null ? ""
												: ""
														+ imovelRelatorioHelper
																.getCodigoSetorComercial(),

										// Descrição do Setor Comercial
										imovelRelatorioHelper
												.getDescricaoSetorComercial() == null ? ""
												: imovelRelatorioHelper
														.getDescricaoSetorComercial(),

										// Matrícula do Imóvel
										""
												+ imovelRelatorioHelper
														.getMatriculaImovel(),

										// Inscrição Formatada
										imovelRelatorioHelper
												.getInscricaoImovel(),

										// Endereço
										imovelRelatorioHelper.getEndereco(),

										// Subcategoria
										imovelSubcategoriaHelper
												.getSubcategoria(),

										// Categoria
										imovelSubcategoriaHelper.getCategoria(),

										// Quantidade de Economias
										""
												+ imovelSubcategoriaHelper
														.getQuantidadeEconomias(),

										// Nome do Cliente Usuário
										imovelEconomiaHelper.getClienteNome(),

										// Complemento Endereço
										imovelEconomiaHelper
												.getComplementoEndereco() == null ? ""
												: imovelEconomiaHelper
														.getComplementoEndereco(),

										// Pontos de Utilização
										imovelEconomiaHelper
												.getNumeroPontosUtilizacao() == 0 ? ""
												: ""
														+ imovelEconomiaHelper
																.getNumeroPontosUtilizacao(),

										// Número de Moradores
										imovelEconomiaHelper
												.getNumeroMoradores() == 0 ? ""
												: ""
														+ imovelEconomiaHelper
																.getNumeroMoradores(),

										// Número IPTU
										imovelEconomiaHelper.getNumeroIptu() == null ? ""
												: imovelEconomiaHelper
														.getNumeroIptu()
														.toString(),

										// Número Contrato Celpe
										imovelEconomiaHelper
												.getNumeroContratoCelpe() == null ? ""
												: imovelEconomiaHelper
														.getNumeroContratoCelpe()
														.toString(),

										// Área Construída
										imovelEconomiaHelper
												.getAreaConstruidaImovelEconomia() == null ? ""
												: Util
														.formatarMoedaReal(imovelEconomiaHelper
																.getAreaConstruidaImovelEconomia()),

										// Nome Cliente
										imovelEconomiaHelper.getClienteNome(),

										// Tipo Relação
										null,

										// CPF / CNPJ
										null,

										// Data Início Relação
										null,

										// Data Fim Relação
										null,

										// Motivo Fim Relação
										null

								);
								
								relatorioBean.setNomeClienteUsuario(imovelRelatorioHelper.getClienteUsuarioNome());
				
								// adiciona o bean a coleção
								relatorioBeans.add(relatorioBean);
							} else {

								Iterator clienteImovelEconomiaIterator = imovelEconomiaHelper
										.getClienteImovelEconomiaHelper()
										.iterator();

								// Iterator clienteImovelIterator =
								// imovelEconomiaHelper
								// .getClienteImovelEconomiaHelper()
								// .iterator();
								//
								// while (clienteImovelIterator.hasNext()) {
								//
								// ClienteImovelEconomiaHelper clienteImovel =
								// (ClienteImovelEconomiaHelper)
								// clienteImovelIterator
								// .next();
								//
								// if (clienteImovel.getRelacaoTipo()
								// .equalsIgnoreCase("usuario")) {
								// imovelEconomiaHelper
								// .setClienteNome(clienteImovel
								// .getClienteNome());
								// break;
								// }
								// }

								while (clienteImovelEconomiaIterator.hasNext()) {

									ClienteImovelEconomiaHelper clienteImovelEconomiaHelper = (ClienteImovelEconomiaHelper) clienteImovelEconomiaIterator
											.next();

									// Início da Construção do objeto
									// RelatorioDadosEconomiaImovelBean
									// para ser colocado no relatório
									relatorioBean = new RelatorioDadosEconomiaImovelBean(

											// Código da Gerência Regional
											imovelRelatorioHelper
													.getIdGerenciaRegional() == null ? ""
													: ""
															+ imovelRelatorioHelper
																	.getIdGerenciaRegional(),

											// Descrição da Gerência Regional
											imovelRelatorioHelper
													.getDescricaoGerenciaRegional() == null ? ""
													: imovelRelatorioHelper
															.getDescricaoGerenciaRegional(),

											// Código da Localidade
											imovelRelatorioHelper
													.getIdLocalidade() == null ? ""
													: ""
															+ imovelRelatorioHelper
																	.getIdLocalidade(),

											// Descrição da Localidade
											imovelRelatorioHelper
													.getDescricaoLocalidade() == null ? ""
													: imovelRelatorioHelper
															.getDescricaoLocalidade(),

											// Código do Setor Comercial
											imovelRelatorioHelper
													.getCodigoSetorComercial() == null ? ""
													: ""
															+ imovelRelatorioHelper
																	.getCodigoSetorComercial(),

											// Descrição do Setor Comercial
											imovelRelatorioHelper
													.getDescricaoSetorComercial() == null ? ""
													: imovelRelatorioHelper
															.getDescricaoSetorComercial(),

											// Matrícula do Imóvel
											""
													+ imovelRelatorioHelper
															.getMatriculaImovel(),

											// Inscrição Formatada
											imovelRelatorioHelper
													.getInscricaoImovel(),

											// Endereço
											imovelRelatorioHelper.getEndereco(),

											// Subcategoria
											imovelSubcategoriaHelper
													.getSubcategoria(),

											// Categoria
											imovelSubcategoriaHelper
													.getCategoria(),

											// Quantidade de Economias
											""
													+ imovelSubcategoriaHelper
															.getQuantidadeEconomias(),

											// Nome do Cliente Usuário
											imovelEconomiaHelper
													.getClienteNome(),

											// Complemento Endereço
											imovelEconomiaHelper
													.getComplementoEndereco() == null ? ""
													: imovelEconomiaHelper
															.getComplementoEndereco(),

											// Pontos de Utilização
											imovelEconomiaHelper
													.getNumeroPontosUtilizacao() != null ? imovelEconomiaHelper
													.getNumeroPontosUtilizacao() == 0 ? ""
													: ""
															+ imovelEconomiaHelper
																	.getNumeroPontosUtilizacao()
													: "",

											// Número de Moradores
											imovelEconomiaHelper
													.getNumeroMoradores() != null ? imovelEconomiaHelper
													.getNumeroMoradores() == 0 ? ""
													: ""
															+ imovelEconomiaHelper
																	.getNumeroMoradores()
													: "",

											// Número IPTU
											imovelEconomiaHelper
													.getNumeroIptu() == null ? ""
													: imovelEconomiaHelper
															.getNumeroIptu()
															.toString(),

											// Número Contrato Celpe
											imovelEconomiaHelper
													.getNumeroContratoCelpe() == null ? ""
													: imovelEconomiaHelper
															.getNumeroContratoCelpe()
															.toString(),

											// Área Construída
											imovelEconomiaHelper
													.getAreaConstruidaImovelEconomia() == null ? ""
													: Util
															.formatarMoedaReal(imovelEconomiaHelper
																	.getAreaConstruidaImovelEconomia()),

											// Nome Cliente
											clienteImovelEconomiaHelper
													.getClienteNome(),

											// Tipo Relação
											clienteImovelEconomiaHelper
													.getRelacaoTipo(),

											// CPF / CNPJ
											clienteImovelEconomiaHelper
													.getCpf() == null ? clienteImovelEconomiaHelper
													.getCnpj() == null ? ""
													: clienteImovelEconomiaHelper
															.getCnpj()
													: clienteImovelEconomiaHelper
															.getCpf(),

											// Data Início Relação
											clienteImovelEconomiaHelper
													.getRelacaoDataInicio(),

											// Data Fim Relação
											clienteImovelEconomiaHelper
													.getRelacaoDataFim(),

											// Motivo Fim Relação
											clienteImovelEconomiaHelper
													.getMotivoFimRelacao()

									);

									// Fim da Construção do objeto
									// RelatorioDadosEconomiaImovelBean
									// para ser colocado no relatório
									relatorioBean.setNomeClienteUsuario(imovelRelatorioHelper.getClienteUsuarioNome());
									// adiciona o bean a coleção
									relatorioBeans.add(relatorioBean);

								}
							}
						}
					}
				}
			}
		}

		// Organizar a coleção

		Collections.sort((List) relatorioBeans, new Comparator() {
			public int compare(Object a, Object b) {
				String chaveRegistro1 = ((RelatorioDadosEconomiaImovelBean) a)
						.getNomeClienteUsuario() == null ? ((RelatorioDadosEconomiaImovelBean) a)
						.getMatricula()
						+ ((RelatorioDadosEconomiaImovelBean) a)
								.getSubcategoria() + " "
						: ((RelatorioDadosEconomiaImovelBean) a)
								.getIdSetorComercial()
								+ ((RelatorioDadosEconomiaImovelBean) a)
										.getIdLocalidade()
								+ ((RelatorioDadosEconomiaImovelBean) a)
										.getIdGerenciaRegional()
								+ ((RelatorioDadosEconomiaImovelBean) a)
										.getMatricula()
								+ ((RelatorioDadosEconomiaImovelBean) a)
										.getSubcategoria()
								+ ((RelatorioDadosEconomiaImovelBean) a)
										.getNomeClienteUsuario()
								+ ((RelatorioDadosEconomiaImovelBean) a)
										.getNomeCliente()
								+ ((RelatorioDadosEconomiaImovelBean) a)
										.getTipoRelacao();
				String chaveRegistro2 = ((RelatorioDadosEconomiaImovelBean) b)
						.getNomeClienteUsuario() == null ? ((RelatorioDadosEconomiaImovelBean) b)
						.getMatricula()
						+ ((RelatorioDadosEconomiaImovelBean) b)
								.getSubcategoria() + " "
						: ((RelatorioDadosEconomiaImovelBean) b)
								.getIdSetorComercial()
								+ ((RelatorioDadosEconomiaImovelBean) b)
										.getIdLocalidade()
								+ ((RelatorioDadosEconomiaImovelBean) b)
										.getIdGerenciaRegional()
								+ ((RelatorioDadosEconomiaImovelBean) b)
										.getMatricula()
								+ ((RelatorioDadosEconomiaImovelBean) b)
										.getSubcategoria()
								+ ((RelatorioDadosEconomiaImovelBean) b)
										.getNomeClienteUsuario()
								+ ((RelatorioDadosEconomiaImovelBean) b)
										.getNomeCliente()
								+ ((RelatorioDadosEconomiaImovelBean) b)
										.getTipoRelacao();

				return chaveRegistro1.compareTo(chaveRegistro2);

			}
		});

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("gerenciaRegional", gerenciaRegional.getNomeAbreviado());
		parametros.put("idLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosInicial.getLocalidade().getId());
		parametros.put("idLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosFinal.getLocalidade().getId());
		parametros.put("nomeLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getDescricao());
		parametros.put("nomeLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getDescricao());
		parametros.put("idSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getCodigo() == 0 ? "" : ""
				+ imovelParametrosInicial.getSetorComercial().getCodigo());
		parametros.put("idSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getCodigo() == 0 ? "" : ""
				+ imovelParametrosFinal.getSetorComercial().getCodigo());
		parametros.put("nomeSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getDescricao());
		parametros.put("nomeSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getDescricao());
		parametros.put("numeroQuadraOrigem", imovelParametrosInicial
				.getQuadra().getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosInicial.getQuadra().getNumeroQuadra());
		parametros.put("numeroQuadraDestino", imovelParametrosFinal.getQuadra()
				.getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosFinal.getQuadra().getNumeroQuadra());
		parametros.put("loteOrigem",
				imovelParametrosInicial.getLote() == 0 ? "" : ""
						+ imovelParametrosInicial.getLote());
		parametros.put("loteDestino", imovelParametrosFinal.getLote() == 0 ? ""
				: "" + imovelParametrosFinal.getLote());
		parametros.put("idMunicipio", municipio.getId() == null ? "" : ""
				+ municipio.getId());
		parametros.put("nomeMunicipio", municipio.getNome());
		parametros.put("idBairro", bairro.getCodigo() == 0 ? "" : ""
				+ bairro.getCodigo());
		parametros.put("nomeBairro", bairro.getNome());
		parametros.put("cep", imovelParametrosInicial.getLogradouroCep()
				.getCep() == null ? "" : imovelParametrosInicial
				.getLogradouroCep().getCep().getCodigo() == null ? "" : ""
				+ imovelParametrosInicial.getLogradouroCep().getCep()
						.getCodigo());
		// rafael
		parametros.put("idLogradouro", imovelParametrosInicial
				.getLogradouroCep().getLogradouro() == null ? ""
				: imovelParametrosInicial.getLogradouroCep().getLogradouro()
						.getId() == null ? "" : ""
						+ imovelParametrosInicial.getLogradouroCep()
								.getLogradouro().getId());
		parametros.put("nomeLogradouro", imovelParametrosInicial
				.getLogradouroCep().getLogradouro() == null ? ""
				: imovelParametrosInicial.getLogradouroCep().getLogradouro()
						.getNome());
		parametros
				.put(
						"idCliente",
						clienteImovelParametros.getCliente() == null ? ""
								: clienteImovelParametros.getCliente().getId() == null ? ""
										: ""
												+ clienteImovelParametros
														.getCliente().getId());

		parametros.put("nomeCliente",
				clienteImovelParametros.getCliente() == null ? ""
						: clienteImovelParametros.getCliente().getNome());
		parametros.put("tipoRelacao", clienteImovelParametros
				.getClienteRelacaoTipo() == null ? "" : clienteImovelParametros
				.getClienteRelacaoTipo().getDescricao());

		parametros.put("tipoCliente", clienteImovelParametros.getCliente()
				.getClienteTipo() == null ? "" : clienteImovelParametros
				.getCliente().getClienteTipo().getDescricao());

		parametros.put("imovelCondominio", imovelParametrosInicial
				.getImovelCondominio().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelCondominio().getId());
		parametros.put("imovelPrincipal", imovelParametrosInicial
				.getImovelPrincipal().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelPrincipal().getId());
		// parametros
		// .put("nomeConta",
		// imovelParametrosInicial.getNomeConta() == null ? ""
		// : imovelParametrosInicial.getNomeConta()
		// .getNomeConta());
		parametros.put("situacaoLigacaoAgua", imovelParametrosInicial
				.getLigacaoAguaSituacao() == null ? ""
				: imovelParametrosInicial.getLigacaoAguaSituacao()
						.getDescricao());
		parametros.put("situacaoLigacaoEsgoto", imovelParametrosInicial
				.getLigacaoEsgotoSituacao() == null ? ""
				: imovelParametrosInicial.getLigacaoEsgotoSituacao()
						.getDescricao());
		parametros.put("consumoMinimoFixadoAguaInicial",
				imovelParametrosInicial.getLigacaoAgua()
						.getNumeroConsumoMinimoAgua() == null ? null : ""
						+ imovelParametrosInicial.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("consumoMinimoFixadoAguaFinal", imovelParametrosFinal
				.getLigacaoAgua().getNumeroConsumoMinimoAgua() == null ? null
				: ""
						+ imovelParametrosFinal.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("percentualEsgotoInicial", imovelParametrosInicial
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosInicial.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros.put("percentualEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosFinal.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros
				.put("consumoMinimoFixadoEsgotoInicial",
						imovelParametrosInicial.getLigacaoEsgoto()
								.getConsumoMinimo() == null ? null : ""
								+ imovelParametrosInicial.getLigacaoEsgoto()
										.getConsumoMinimo());
		parametros.put("consumoMinimoFixadoEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getConsumoMinimo() == null ? null : ""
				+ imovelParametrosFinal.getLigacaoEsgoto().getConsumoMinimo());
		parametros.put("indicadorMedicao", indicadorMedicao == null ? ""
				: indicadorMedicao.equals("comMedicao") ? "COM MEDIÇÃO"
						: "SEM MEDIÇÃO");
		parametros.put("tipoMedicao", medicaoHistoricoParametrosInicial
				.getMedicaoTipo().getDescricao());
		parametros
				.put(
						"mediaMinimaImovelInicial",
						consumoHistoricoParametrosInicial.getConsumoMedio() == null ? null
								: ""
										+ consumoHistoricoParametrosInicial
												.getConsumoMedio());
		parametros
				.put("mediaMinimaImovelFinal", consumoHistoricoParametrosFinal
						.getConsumoMedio() == null ? null : ""
						+ consumoHistoricoParametrosFinal.getConsumoMedio());
		parametros
				.put("mediaMinimaHidrometroInicial",
						medicaoHistoricoParametrosInicial
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosInicial
												.getConsumoMedioHidrometro());
		parametros
				.put("mediaMinimaHidrometroFinal",
						medicaoHistoricoParametrosFinal
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosFinal
												.getConsumoMedioHidrometro());
		parametros.put("perfilImovel", imovelParametrosInicial
				.getImovelPerfil().getDescricao());
		parametros.put("categoria", categoria.getDescricao());
		parametros.put("subCategoria", subcategoria.getDescricao());
		parametros.put("qtdeEconomiaInicial", imovelParametrosInicial
				.getQuantidadeEconomias() == null ? null : ""
				+ imovelParametrosInicial.getQuantidadeEconomias());
		parametros.put("qtdeEconomiaFinal", imovelParametrosFinal
				.getQuantidadeEconomias() == null ? null : ""
				+ imovelParametrosFinal.getQuantidadeEconomias());
		parametros.put("numeroPontosInicial", imovelParametrosInicial
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroPontosUtilizacao());
		parametros.put("numeroPontosFinal", imovelParametrosFinal
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroPontosUtilizacao());
		parametros.put("numeroMoradoresInicial", imovelParametrosInicial
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroMorador());
		parametros.put("numeroMoradoresFinal", imovelParametrosFinal
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroMorador());
		parametros.put("areaConstruidaInicial", imovelParametrosInicial
				.getAreaConstruida().equals(new Short("0")) ? null : ""
				+ imovelParametrosInicial.getAreaConstruida());
		parametros.put("areaConstruidaFinal", imovelParametrosFinal
				.getAreaConstruida().equals(new Short("0")) ? null : ""
				+ imovelParametrosFinal.getAreaConstruida());
		parametros
				.put(
						"tipoPoco",
						imovelParametrosInicial.getPocoTipo() != null ? imovelParametrosInicial
								.getPocoTipo().getDescricao()
								: "");
		parametros
				.put(
						"tipoSituacaoEspecialFaturamento",
						imovelParametrosInicial.getFaturamentoSituacaoTipo() != null ? imovelParametrosInicial
								.getFaturamentoSituacaoTipo().getDescricao()
								: "");
		parametros.put("tipoSituacaoEspecialCobranca", imovelParametrosInicial
				.getCobrancaSituacaoTipo() != null ? imovelParametrosInicial
				.getCobrancaSituacaoTipo().getDescricao() : "");
		parametros.put("situacaoCobranca", cobrancaSituacao == null ? ""
				: cobrancaSituacao.getDescricao());
		parametros.put("diaVencimentoAlternativo", imovelParametrosInicial
				.getDiaVencimento() == null ? "" : ""
				+ imovelParametrosInicial.getDiaVencimento());
		parametros.put("anormalidadeElo", imovelParametrosInicial
				.getEloAnormalidade() == null ? "" : imovelParametrosInicial
				.getEloAnormalidade().getDescricao());
		
		if ( imovelParametrosInicial.getCadastroOcorrencia() != null && 
				!imovelParametrosInicial.getCadastroOcorrencia().equals("") &&
				imovelParametrosInicial.getCadastroOcorrencia().getDescricao() != null ) {
			
			parametros.put("ocorrenciaCadastro", 
					imovelParametrosInicial.getCadastroOcorrencia().getDescricao());
		} else {
			parametros.put("ocorrenciaCadastro", "");
		}
		
		if ( imovelParametrosInicial.getConsumoTarifa() != null && 
				!imovelParametrosInicial.getConsumoTarifa().equals("") &&
				imovelParametrosInicial.getConsumoTarifa().getDescricao() != null ) {
		
			parametros.put("tarifaConsumo", imovelParametrosInicial.getConsumoTarifa().getDescricao());
		} else {
			parametros.put("tarifaConsumo", "");
		}
		parametros.put("tipoFormatoRelatorio", "R0161");

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_DADOS_ECONOMIA_IMOVEL,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.DADOS_ECONOMIA_IMOVEL, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		// id da genrencia regional
		String gerenciaRegional = (String) getParametro("gerenciaRegional");
		// id da genrencia regional
		String idUnidadeNegocio = (String) getParametro("unidadeNegocio");

		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicao = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");

		Fachada fachada = Fachada.getInstancia();

		Integer quantidade = fachada.obterQuantidadaeRelacaoImoveisDebitos(
				imovelCondominioID, imovelPrincipalID, situacaoAgua,
				consumoMinimoInicial, consumoMinimoFinal,
				situacaoLigacaoEsgoto, consumoMinimoFixadoEsgotoInicial,
				consumoMinimoFixadoEsgotoFinal,
				intervaloPercentualEsgotoInicial,
				intervaloPercentualEsgotoFinal,
				intervaloMediaMinimaImovelInicial,
				intervaloMediaMinimaImoveFinal,
				intervaloMediaMinimaHidrometroInicial,
				intervaloMediaMinimaHidrometroFinal, perfilImovelID,
				pocoTipoID, tipoSituacaoFaturamentoID, situacaoCobrancaID,
				tipoSituacaoEspecialCobrancaID, anormalidadeElo,
				areaConstruidaInicial, areaConstruidaFinal, ocorrenciaCadastro,
				tarifaConsumo, gerenciaRegional, localidadeOrigem,
				localidadeDestino, setorComercialOrigemCD,
				setorComercialDestinoCD, qudraOrigem, quadraDestino,
				loteOrigem, loteDestino, cep, logradouroID, bairroID,
				municipioID, tipoMedicaoID, indicadorMedicao, subCategoriaID,
				categoriaImovelID, quantidadeEconomiasInicial,
				quantidadeEconomiasFinal, diaVencimentoAlternativo, clienteID,
				clienteTipoID, clienteRelacaoTipoID, numeroPontosInicial,
				numeroPontosFinal, numeroMoradoresInicial,
				numeroMoradoresFinal, areaConstruidaFaixa, idUnidadeNegocio,
				ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL, null,
				null, null, null);

		return quantidade.intValue();
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosEconomiaImovel", this);
	}
}
