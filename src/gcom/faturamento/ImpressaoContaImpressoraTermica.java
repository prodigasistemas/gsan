package gcom.faturamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.IContaCategoria;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.FiltroDebitoCobrado;
import gcom.financeiro.FinanciamentoTipo;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

public class ImpressaoContaImpressoraTermica {

	private IRepositorioFaturamento repositorioFaturamento;
	private IRepositorioClienteImovel repositorioClienteImovel;
	private SessionContext sessionContext;
	private static ImpressaoContaImpressoraTermica instancia;

	private ImpressaoContaImpressoraTermica(IRepositorioFaturamento repositorioFaturamento,
			IRepositorioClienteImovel repositorioClienteImovel, SessionContext sessionContext) {

		this.repositorioClienteImovel = repositorioClienteImovel;
		this.repositorioFaturamento = repositorioFaturamento;
		this.sessionContext = sessionContext;
	}

	public static ImpressaoContaImpressoraTermica getInstancia(IRepositorioFaturamento repositorioFaturamento,
			IRepositorioClienteImovel repositorioClienteImovel, SessionContext sessionContext) {

		if (instancia == null) {
			instancia = new ImpressaoContaImpressoraTermica(repositorioFaturamento, repositorioClienteImovel,
					sessionContext);
		}
		return instancia;
	}

	protected ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorClienteLocal getControladorCliente() {
		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorClienteLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorEnderecoLocal getControladorEndereco() {
		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private String formarLinha(int font, int tamanhoFonte, int x, int y, String texto, int adicionarColuna,
			int adicionarLinha) {
		return "T " + font + " " + tamanhoFonte + " " + (x + adicionarColuna) + " " + (y + adicionarLinha) + " "
				+ texto + "\n";
	}

	private static String dividirLinha(int fonte, int tamanhoFonte, int x, int y, String texto, int tamanhoLinha,
			int deslocarPorLinha) {
		String retorno = "";
		int contador = 0;
		int i;
		for (i = 0; i < texto.length(); i += tamanhoLinha) {
			contador += tamanhoLinha;
			if (contador > texto.length()) {
				retorno += "T " + fonte + " " + tamanhoFonte + " " + x + " " + y + " "
						+ texto.substring(i, texto.length()).trim() + "\n";
			} else {
				retorno += "T " + fonte + " " + tamanhoFonte + " " + x + " " + y + " "
						+ texto.substring(i, contador).trim() + "\n";
			}
			y += deslocarPorLinha;
		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector retornarDescricaoCategoriasQuantidadeEconomias(Imovel imovelEmitido) {
		Vector retorno = new Vector();
		int quantidadeEconomiasResidencial = 0;
		int quantidadeEconomiasComercial = 0;
		int quantidadeEconomiasIndustrial = 0;
		int quantidadeEconomiasPublico = 0;
		String descricaoResidencial = "";
		String descricaoComercial = "";
		String descricaoIndustrial = "";
		String descricaoPublico = "";

		Collection<ImovelSubcategoriaHelper> imec;
		try {
			imec = getControladorImovel().consultaSubcategorias(imovelEmitido.getId());

			for (ImovelSubcategoriaHelper imovelSubcategoriaHelper : imec) {
				String descricaoCategoria = imovelSubcategoriaHelper.getCategoria();
				if (descricaoCategoria.length() > 8) {
					descricaoCategoria = descricaoCategoria.substring(0, 8);
				}
				if (descricaoCategoria.toUpperCase().trim().equals("RESIDENCIAL")) {
					quantidadeEconomiasResidencial += imovelSubcategoriaHelper.getQuantidadeEconomias();
					descricaoResidencial = descricaoCategoria;
				} else if (descricaoCategoria.toUpperCase().trim().equals("COMERCIAL")) {
					quantidadeEconomiasComercial += imovelSubcategoriaHelper.getQuantidadeEconomias();
					descricaoComercial = descricaoCategoria;
				} else if (descricaoCategoria.toUpperCase().trim().equals("INDUSTRIAL")) {
					quantidadeEconomiasIndustrial += imovelSubcategoriaHelper.getQuantidadeEconomias();
					descricaoIndustrial = descricaoCategoria;
				} else if (descricaoCategoria.toUpperCase().trim().equals("PUBLICO")) {
					quantidadeEconomiasPublico += imovelSubcategoriaHelper.getQuantidadeEconomias();
					descricaoPublico = descricaoCategoria;
				}

			}

			if (quantidadeEconomiasResidencial > 0) {
				Object[] dadosResidencial = new Object[2];
				dadosResidencial[0] = descricaoResidencial;
				dadosResidencial[1] = new Integer(quantidadeEconomiasResidencial);
				retorno.addElement(dadosResidencial);
			}
			if (quantidadeEconomiasComercial > 0) {
				Object[] dadosComercial = new Object[2];
				dadosComercial[0] = descricaoComercial;
				dadosComercial[1] = new Integer(quantidadeEconomiasComercial);
				retorno.addElement(dadosComercial);
			}
			if (quantidadeEconomiasIndustrial > 0) {
				Object[] dadosIndustrial = new Object[2];
				dadosIndustrial[0] = descricaoIndustrial;
				dadosIndustrial[1] = new Integer(quantidadeEconomiasIndustrial);
				retorno.addElement(dadosIndustrial);
			}
			if (quantidadeEconomiasPublico > 0) {
				Object[] dadosPublico = new Object[2];
				dadosPublico[0] = descricaoPublico;
				dadosPublico[1] = new Integer(quantidadeEconomiasPublico);
				retorno.addElement(dadosPublico);
			}
			return retorno;
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector gerarLinhasImpostosRetidosIS(EmitirContaHelper emitirContaHelper) {
		Vector retorno = new Vector();
		String[] dados = new String[3];

		Collection colecaoParmsImpostosDeduzidos = null;

		try {
			colecaoParmsImpostosDeduzidos = repositorioFaturamento
					.pesquisarParmsContaImpostosDeduzidos(emitirContaHelper.getIdConta());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
		}
		String dadosImposto = "";
		if (colecaoParmsImpostosDeduzidos != null && !colecaoParmsImpostosDeduzidos.isEmpty()) {
			Iterator iteratorParmsImpostosDeduzidos = colecaoParmsImpostosDeduzidos.iterator();
			while (iteratorParmsImpostosDeduzidos.hasNext()) {
				Object[] parmsImpostoDeduzido = (Object[]) iteratorParmsImpostosDeduzidos.next();
				String descricaoTipoImposto = "";
				if (parmsImpostoDeduzido[0] != null) {
					descricaoTipoImposto = (String) parmsImpostoDeduzido[0];
				}
				String percentualAliquota = "";
				if (parmsImpostoDeduzido[1] != null) {
					percentualAliquota = Util.formatarMoedaReal((BigDecimal) parmsImpostoDeduzido[1]);
				}
				BigDecimal valorImpostos = null;
				if (parmsImpostoDeduzido[2] != null) {
					valorImpostos = (BigDecimal) parmsImpostoDeduzido[2];
				}

				String descricaoImposto = descricaoTipoImposto;
				dadosImposto += descricaoImposto + "-" + percentualAliquota + "% ";
			}
			dados = new String[3];
			dados[0] = "DED. IMPOSTOS LEI FEDERAL N.9430 DE 27/12/1996";
			dados[2] = Util.formatarMoedaReal(emitirContaHelper.getValorImpostos());
			retorno.addElement(dados);
			dados = new String[3];
			dados[0] = dadosImposto;
			retorno.addElement(dados);
		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector gerarLinhasCreditosRealizados(int indicadorDiscriminarDescricao, EmitirContaHelper emitirContaHelper) {
		Vector retorno = new Vector();
		String[] dados = new String[3];
		Conta conta = new Conta();
		conta.setId(emitirContaHelper.getIdConta());
		Collection<CreditoRealizado> crrz;
		try {
			crrz = getControladorFaturamento().obterCreditosRealizadosConta(conta);

			if (crrz != null) {
				if (indicadorDiscriminarDescricao == 1) {

					for (CreditoRealizado creditoRealizado : crrz) {
						
						if(!creditoRealizado.getCreditoTipo().getId().equals(CreditoTipo.CREDITO_BOLSA_AGUA)){
							dados = new String[3];
							dados[0] = creditoRealizado.getDescricao();
							dados[2] = Util.formatarMoedaReal(creditoRealizado.getValorCredito());
							retorno.addElement(dados);
						}
					}

				} else {
					BigDecimal soma = emitirContaHelper.getValorCreditos();

					dados = new String[3];
					dados[0] = "CREDITOS";
					dados[2] = Util.formatarMoedaReal(soma);
					retorno.addElement(dados);
				}
				
				for (CreditoRealizado creditoRealizado : crrz) {
					
					if(creditoRealizado.getCreditoTipo().getId().equals(CreditoTipo.CREDITO_BOLSA_AGUA)){
						dados = new String[3];
						dados[0] = creditoRealizado.getDescricao();
						dados[2] = Util.formatarMoedaReal(retornaCreditoBolsaAgua(creditoRealizado, emitirContaHelper));
						retorno.addElement(dados);
					}
				}
		
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int retornaIndicadorDiscriminar(int quantidadeMaximaLinhas, int quantidadeLinhasAtual, char servicos,
			EmitirContaHelper emitirContaHelper) {
		int indicadorDiscriminarDescricao = 1;
		int linhasRestantesDescricao = 0;

		Conta conta = new Conta();
		conta.setId(emitirContaHelper.getIdConta());
		Collection<CreditoRealizado> crrz = null;
		Collection<DebitoCobrado> dbcb = null;
		Collection cnid = null;

		try {
			crrz = getControladorFaturamento().obterCreditosRealizadosConta(conta);

			dbcb = getControladorFaturamento().obterDebitosCobradosConta(conta);
			cnid = repositorioFaturamento.pesquisarParmsContaImpostosDeduzidos(conta.getId());

			switch (servicos) {
			case 'd':
				if (crrz != null) {
					linhasRestantesDescricao = linhasRestantesDescricao + 1;
				}
				if (cnid != null) {
					linhasRestantesDescricao = linhasRestantesDescricao + 2;
				}
				if (dbcb != null) {
					int limiteDescriminar = quantidadeMaximaLinhas - quantidadeLinhasAtual - linhasRestantesDescricao;
					int quantidadeDebitos = dbcb.size();
					if (quantidadeDebitos > limiteDescriminar) {
						indicadorDiscriminarDescricao = 2;
					}
				}
				break;
			case 'c':
				if (cnid != null) {
					linhasRestantesDescricao = linhasRestantesDescricao + 2;
				}
				if (crrz != null) {
					int limiteDescriminar = quantidadeMaximaLinhas - quantidadeLinhasAtual - linhasRestantesDescricao;
					int quantidadeCreditos = crrz.size();
					if (quantidadeCreditos > limiteDescriminar) {
						indicadorDiscriminarDescricao = 2;
					}
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indicadorDiscriminarDescricao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector gerarLinhasTarifaAguaIS(ConsumoHistorico consumoAgua, SistemaParametro sistemaParametro,
			Imovel imovelEmitido, EmitirContaHelper emitirContaHelper) {
		String linhas = "";
		boolean tipoTarifaPorCategoria = false;

		if (sistemaParametro.getIndicadorTarifaCategoria() == SistemaParametro.INDICADOR_TARIFA_CATEGORIA) {
			tipoTarifaPorCategoria = true;
		}
		int qtdLinhas = 0;

		Collection<IContaCategoria> cContaCategoria;
		try {
			cContaCategoria = repositorioFaturamento.pesquisarContaCategoria(emitirContaHelper.getIdConta());

			if (cContaCategoria != null) {
				for (IContaCategoria contaCategoria : cContaCategoria) {
					if (contaCategoria.getConsumoAgua() == null
							|| (contaCategoria.getConsumoAgua() == 0 && (contaCategoria.getValorAgua() == null || contaCategoria
									.getValorAgua().equals(new BigDecimal("0.00"))))) {
						continue;
					}
					qtdLinhas++;

					if (linhas.equals("")) {

						linhas += formarLinha(7, 0, 200, 788, "AGUA", 0, 0);

					}

					int quantidaEconomias = 0;

					quantidaEconomias = contaCategoria.getQuantidadeEconomia();

					String descricao = "";

					if (tipoTarifaPorCategoria) {
						descricao = contaCategoria.getDescricao() + " " + quantidaEconomias + " " + "UNIDADE(S)";
						if (descricao.length() > 40) {

							linhas += formarLinha(7, 0, 53, 813, descricao.substring(0, 40), 0, qtdLinhas * 25);
						} else {
							linhas += formarLinha(7, 0, 53, 813, descricao, 0, qtdLinhas * 25);
						}
					} else {
						descricao = contaCategoria.getDescricao() + " " + quantidaEconomias + " " + "UNIDADE(S)";
						if (descricao.length() > 40) {

							linhas += formarLinha(7, 0, 53, 813, descricao.substring(0, 40), 0, qtdLinhas * 25);
						} else {
							linhas += formarLinha(7, 0, 53, 813, descricao, 0, qtdLinhas * 25);

						}
					}

					int consumoMinimo = 0;
					if (consumoAgua != null && consumoAgua.getConsumoMinimo() != null
							&& contaCategoria.getConsumoMinimoAgua() != null
							&& consumoAgua.getConsumoMinimo() > contaCategoria.getConsumoMinimoAgua()) {
						consumoMinimo = consumoAgua.getConsumoMinimo();
					} else if (contaCategoria.getConsumoMinimoAgua() != null) {
						consumoMinimo = contaCategoria.getConsumoMinimoAgua();
					}

					if (consumoAgua == null && contaCategoria.getConsumoAgua() != null
							&& contaCategoria.getConsumoAgua() <= consumoMinimo) {
						qtdLinhas++;
						descricao = "";
						descricao = "TARIFA MINIMA "
								+ Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua().divide(
										new BigDecimal(quantidaEconomias), 2, BigDecimal.ROUND_DOWN)) + " POR UNIDADE ";

						linhas += formarLinha(7, 0, 53, 813, descricao, 0, qtdLinhas * 25);
						descricao = consumoMinimo + " m3";
						linhas += formarLinha(7, 0, 571, 813, descricao, 0, qtdLinhas * 25);
						linhas += formarLinha(7, 0, 697, 813,
								Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua()), 0, qtdLinhas * 25);

					} else {

						Collection<ContaCategoriaConsumoFaixa> cContaCategoriaConsumoFaixa = repositorioFaturamento
								.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper.getIdConta());

						System.out.println("---> " + emitirContaHelper.getIdConta());
						if (contaCategoria.getConsumoAgua() != null && cContaCategoriaConsumoFaixa != null
								&& cContaCategoriaConsumoFaixa.size() > 0) {
							qtdLinhas++;
							descricao = "ATE "
									+ ((int) contaCategoria.getConsumoMinimoAgua() / quantidaEconomias)
									+ " m3 - "
									+ Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua().divide(
											new BigDecimal(quantidaEconomias), 2, BigDecimal.ROUND_DOWN))
									+ " POR UNIDADE";

							linhas += formarLinha(7, 0, 73, 813, descricao, 0, qtdLinhas * 25);
							linhas += formarLinha(7, 0, 571, 813, contaCategoria.getConsumoMinimoAgua() + " m3", 0,
									qtdLinhas * 25);
							linhas += formarLinha(7, 0, 697, 813,
									Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua()), 0,
									qtdLinhas * 25);

							for (ContaCategoriaConsumoFaixa faixa : cContaCategoriaConsumoFaixa) {
								qtdLinhas++;

								if (faixa.getConsumoFaixaFim() == 51) {
									descricao = "ACIMA DE " + (faixa.getConsumoFaixaInicio() - 1) + " m3 - R$ "
											+ Util.formatarMoedaReal(faixa.getValorTarifaFaixa()) + " POR m3";

									linhas += formarLinha(7, 0, 73, 813, descricao, 0, qtdLinhas * 25);
									linhas += formarLinha(7, 0, 571, 813, faixa.getConsumoAgua() * quantidaEconomias
											+ " m3 ", 0, qtdLinhas * 25);
									linhas += formarLinha(
											7,
											0,
											697,
											813,
											Util.formatarMoedaReal(faixa.getValorAgua().multiply(
													new BigDecimal(quantidaEconomias))), 0, qtdLinhas * 25);
								} else {
									descricao = faixa.getConsumoFaixaInicio() + " m3 A " + faixa.getConsumoFaixaFim()
											+ " m3 - R$ " + Util.formatarMoedaReal(faixa.getValorTarifaFaixa())
											+ " POR M3 ";
									linhas += formarLinha(7, 0, 73, 813, descricao, 0, qtdLinhas * 25);
									linhas += formarLinha(7, 0, 571, 813, faixa.getConsumoAgua() * quantidaEconomias
											+ " m3 ", 0, qtdLinhas * 25);
									linhas += formarLinha(
											7,
											0,
											697,
											813,
											Util.formatarMoedaReal(faixa.getValorAgua().multiply(
													new BigDecimal(quantidaEconomias))), 0, qtdLinhas * 25);

								}
							}
						} else {
							if (contaCategoria.getConsumoAgua() != null) {
								qtdLinhas++;
								descricao = "CONSUMO DE AGUA";

								linhas += formarLinha(7, 0, 73, 813, descricao, 0, qtdLinhas * 25);
								linhas += formarLinha(7, 0, 571, 813, ((int) contaCategoria.getConsumoAgua()) + " m3",
										0, qtdLinhas * 25);
								linhas += formarLinha(7, 0, 697, 813,
										Util.formatarMoedaReal(contaCategoria.getValorAgua()), 0, qtdLinhas * 25);

							}
						}
					}

				}
			}

		} catch (ErroRepositorioException e) {

			e.printStackTrace();
		}
		Vector retornar = new Vector();
		retornar.addElement(linhas);
		retornar.addElement(new Integer(qtdLinhas));
		return retornar;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector gerarLinhasRateioAguaEsgotoCobrados(EmitirContaHelper emitirContaHelper) {
		Vector retorno = new Vector();
		String[] dados = new String[2];

		// Valor do rateio de Água
		if (emitirContaHelper.getConsumoAgua() != null && emitirContaHelper.getValorRateioAgua() != null
				&& emitirContaHelper.getValorRateioAgua().compareTo(BigDecimal.ZERO) > 0) {
			dados = new String[2];
			dados[0] = "RATEIO DE AGUA DO CONDOMINIO";
			dados[1] = Util.formatarMoedaReal(emitirContaHelper.getValorRateioAgua());
			retorno.add(dados);
		}
		// Valor do rateio de Esgoto
		if (emitirContaHelper.getConsumoEsgoto() != null && emitirContaHelper.getValorRateioEsgoto() != null
				&& emitirContaHelper.getValorRateioEsgoto().compareTo(BigDecimal.ZERO) > 0) {
			dados = new String[2];
			dados[0] = "RATEIO DE ESGOTO DO CONDOMINIO";
			dados[1] = Util.formatarMoedaReal(emitirContaHelper.getValorRateioEsgoto());
			retorno.add(dados);
		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector gerarLinhasDebitosCobrados(int indicadorDiscriminarDescricao, EmitirContaHelper emitirContaHelper) {
		Vector retorno = new Vector();
		String[] dados = new String[3];

		Conta conta = new Conta();
		conta.setId(emitirContaHelper.getIdConta());
		Collection<DebitoCobrado> dbcb;
		try {
			dbcb = getControladorFaturamento().obterDebitosCobradosConta(conta);

			if (dbcb != null) {
				if (indicadorDiscriminarDescricao == 1) {

					for (DebitoCobrado debitoCobrado : dbcb) {
						dados = new String[3];
						dados[0] = debitoCobrado.getDescricao();
						dados[0] = dados[0] + " " + debitoCobrado.getNumeroPrestacaoDebito() + "/"
								+ debitoCobrado.getNumeroPrestacao();
						dados[2] = Util.formatarMoedaReal(debitoCobrado.getValorPrestacao());
						retorno.addElement(dados);
					}
				} else {
					BigDecimal soma = emitirContaHelper.getDebitos();

					dados = new String[3];
					dados[0] = "DEBITOS";
					dados[2] = Util.formatarMoedaReal(soma);
					retorno.addElement(dados);
				}
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector gerarLinhasTarifaPocoIS(EmitirContaHelper emitirContaHelper) {

		Vector retorno = new Vector();
		String[] dados = new String[3];
		BigDecimal valorEsgoto = null;
		int consumAgua = 0;
		int consumEsgoto = 0;
		BigDecimal valorAgua = null;

		consumAgua = emitirContaHelper.getConsumoAgua();
		consumEsgoto = emitirContaHelper.getConsumoEsgoto();
		valorAgua = emitirContaHelper.getValorAgua();
		valorEsgoto = emitirContaHelper.getValorEsgoto();
		dados = new String[3];
		if (consumAgua == consumEsgoto && valorAgua.compareTo(new BigDecimal("0.00")) != 0) {
			if (valorEsgoto.compareTo(new BigDecimal("0.00")) != 0) {
				dados[0] = "ESGOTO ";
				dados[0] += Util.formatarMoedaReal(emitirContaHelper.getPercentualEsgotoConta());
				dados[0] += " % DO VALOR DE AGUA";
				dados[2] = Util.formatarMoedaReal(valorEsgoto);
				retorno.addElement(dados);
			}
		} else {
			if (valorEsgoto.compareTo(new BigDecimal("0.00")) != 0) {
				dados[0] = "ESGOTO ";
				dados[1] = consumEsgoto + "";
				dados[1] += " M3";
				dados[2] = Util.formatarMoedaReal(valorEsgoto);
				retorno.addElement(dados);
			}
		}

		return retorno;
	}

	public String gerarArquivoFormatadoImpressaoTermica(EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro, String[] qualidadeAgua, int contador, List<Integer> idsCondominios) {

		StringBuilder retorno = new StringBuilder("");
		Imovel imovelEmitido;
		try {
			imovelEmitido = getControladorImovel().pesquisarImovel(emitirContaHelper.getIdImovel());

			LigacaoTipo lta = new LigacaoTipo();
			lta.setId(LigacaoTipo.LIGACAO_AGUA);
			LigacaoTipo lte = new LigacaoTipo();
			lte.setId(LigacaoTipo.LIGACAO_ESGOTO);

			try {
				ConsumoHistorico consumoAgua;
				ConsumoHistorico consumoEsgoto;

				if (imovelEmitido.pertenceACondominio()
						&& !idsCondominios.contains(imovelEmitido.getImovelCondominio().getId())) {
					Imovel imovelCondominio = getControladorImovel().pesquisarImovel(
							imovelEmitido.getImovelCondominio().getId());

					consumoAgua = getControladorMicromedicao().obterConsumoHistorico(imovelCondominio, lta,
							emitirContaHelper.getAmReferencia());
					consumoEsgoto = getControladorMicromedicao().obterConsumoHistorico(imovelCondominio, lte,
							emitirContaHelper.getAmReferencia());
					System.out.println("Imovel condominio: " + imovelCondominio.getId());
					gerarDadosComuns(emitirContaHelper, sistemaParametro, retorno, imovelCondominio, consumoAgua,
							consumoEsgoto);
					gerarDadosQualidadeAgua(emitirContaHelper, qualidadeAgua, retorno);
					gerarInformativoMacroCondominio(emitirContaHelper, retorno, imovelEmitido);
					idsCondominios.add(imovelEmitido.getImovelCondominio().getId());
				}

				consumoAgua = getControladorMicromedicao().obterConsumoHistorico(imovelEmitido, lta,
						emitirContaHelper.getAmReferencia());
				consumoEsgoto = getControladorMicromedicao().obterConsumoHistorico(imovelEmitido, lte,
						emitirContaHelper.getAmReferencia());

				gerarDadosComuns(emitirContaHelper, sistemaParametro, retorno, imovelEmitido, consumoAgua,
						consumoEsgoto);

				gerarDadosQualidadeAgua(emitirContaHelper, qualidadeAgua, retorno);

				retorno.append(formarLinha(7, 0, 53, 788, "DESCRICAO", 0, 0)
						+ formarLinha(7, 0, 571, 788, "CONSUMO", 0, 0) + formarLinha(7, 0, 687, 788, "TOTAL(R$)", 0, 0));

				int ultimaLinhaAgua = 0;
				int ultimaLinhaPoco = 0;
				int quantidadeLinhasAtual = 0;
				int quantidadeMaximaLinhas = 18;

				Vector linhaAgua = this.gerarLinhasTarifaAguaIS(consumoAgua, sistemaParametro, imovelEmitido,
						emitirContaHelper);
				retorno.append(linhaAgua.elementAt(0));
				ultimaLinhaAgua = (((Integer) linhaAgua.elementAt(1)).intValue());
				if (ultimaLinhaAgua != 0) {
					quantidadeLinhasAtual = quantidadeLinhasAtual + ultimaLinhaAgua + 1;
				}
				ultimaLinhaAgua *= 25;
				Vector tarifasPoco = this.gerarLinhasTarifaPocoIS(emitirContaHelper);
				ultimaLinhaPoco = ultimaLinhaAgua;
				for (int i = 0; i < tarifasPoco.size(); i++) {
					String[] tarifaPoco = (String[]) tarifasPoco.elementAt(i);
					ultimaLinhaPoco = ultimaLinhaAgua + ((i + 1) * 25);
					quantidadeLinhasAtual++;
					int deslocaDireitaColuna;
					if (i == 0 || i == 1 || i == 2) {
						deslocaDireitaColuna = i;
					} else {
						deslocaDireitaColuna = 2;
					}
					if (tarifaPoco[0] != null) {

						retorno.append(formarLinha(7, 0, 53, 813, tarifaPoco[0], deslocaDireitaColuna * 10, (i + 1)
								* 25 + ultimaLinhaAgua));
					}
					if (tarifaPoco[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 813, tarifaPoco[1], 0, (i + 1) * 25 + ultimaLinhaAgua));
					}
					if (tarifaPoco[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 813, tarifaPoco[2], 0, (i + 1) * 25 + ultimaLinhaAgua));

					}
				}

				// Dados dos Valores de Rateio de Água e Esgoto
				Vector rateios = gerarLinhasRateioAguaEsgotoCobrados(emitirContaHelper);
				int ultimaLinhaRateio = ultimaLinhaPoco;
				for (int i = 0; i < rateios.size(); i++) {
					String[] debito = (String[]) rateios.get(i);
					ultimaLinhaRateio = ultimaLinhaPoco + ((i + 1) * 25);
					quantidadeLinhasAtual++;
					if (debito[0] != null) {

						retorno.append(formarLinha(7, 0, 53, 813, debito[0], 0, (i + 1) * 25 + ultimaLinhaPoco));
					}
					if (debito[1] != null) {
						retorno.append(formarLinha(7, 0, 697, 813, debito[1], 0, (i + 1) * 25 + ultimaLinhaPoco));

					}
				}

				int indicadorDiscriminarDescricao = retornaIndicadorDiscriminar(quantidadeMaximaLinhas,
						quantidadeLinhasAtual, 'd', emitirContaHelper);
				Vector debitos = this.gerarLinhasDebitosCobrados(indicadorDiscriminarDescricao, emitirContaHelper);
				int ultimaLinhaDebito = ultimaLinhaRateio;
				for (int i = 0; i < debitos.size(); i++) {
					String[] debito = (String[]) debitos.elementAt(i);
					ultimaLinhaDebito = ultimaLinhaRateio + ((i + 1) * 25);
					quantidadeLinhasAtual++;
					if (debito[0] != null) {

						retorno.append(formarLinha(7, 0, 53, 813, debito[0], 0, (i + 1) * 25 + ultimaLinhaRateio));
					}
					if (debito[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 813, debito[1], 0, (i + 1) * 25 + ultimaLinhaRateio));
					}
					if (debito[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 813, debito[2], 0, (i + 1) * 25 + ultimaLinhaRateio));

					}
				}

				indicadorDiscriminarDescricao = retornaIndicadorDiscriminar(quantidadeMaximaLinhas,
						quantidadeLinhasAtual, 'c', emitirContaHelper);
				Vector creditos = this.gerarLinhasCreditosRealizados(indicadorDiscriminarDescricao, emitirContaHelper);
				int ultimaLinhaCredito = ultimaLinhaDebito;
				for (int i = 0; i < creditos.size(); i++) {
					String[] credito = (String[]) creditos.elementAt(i);
					ultimaLinhaCredito = ultimaLinhaDebito + ((i + 1) * 25);
					if (credito[0] != null) {

						retorno.append(formarLinha(7, 0, 53, 813, credito[0], 0, (i + 1) * 25 + ultimaLinhaDebito));
					}
					if (credito[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 813, credito[1], 0, (i + 1) * 25 + ultimaLinhaDebito));
					}
					if (credito[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 813, credito[2], 0, (i + 1) * 25 + ultimaLinhaDebito));

					}
				}
				Vector impostos = this.gerarLinhasImpostosRetidosIS(emitirContaHelper);
				for (int i = 0; i < impostos.size(); i++) {
					String[] imposto = (String[]) impostos.elementAt(i);
					int deslocaDireitaColuna;
					if (i == 0 || i == 1) {
						deslocaDireitaColuna = i;
					} else {
						deslocaDireitaColuna = 1;
					}
					if (imposto[0] != null) {

						retorno.append(formarLinha(7, 0, 53, 813, imposto[0], deslocaDireitaColuna * 10, (i + 1) * 25
								+ ultimaLinhaCredito));
					}
					if (imposto[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 813, imposto[1], 0, (i + 1) * 25 + ultimaLinhaCredito));
					}
					if (imposto[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 813, imposto[2], 0, (i + 1) * 25 + ultimaLinhaCredito));

					}
				}
				retorno.append(formarLinha(7, 1, 20, 1115,
						Util.formatarData(emitirContaHelper.getDataVencimentoConta()), 0, 0));

				retorno.append(formarLinha(4, 0, 640, 1115, Util.formatarMoedaReal(emitirContaHelper.getValorConta()),
						0, 0));
				
				if (emitirContaHelper.possuiCreditoBolsaAgua()) {
					if (Double.valueOf(emitirContaHelper.getValorTotalConta()) <= 0.00) {
						retorno.append(dividirLinha(7, 0, 30, 1270, "PROGRAMA AGUA PARA QUITADO PELO GOVERNO DO ESTADO DO PARA", 28, 20));
					} else if (Double.valueOf(emitirContaHelper.getValorTotalConta()) > 0.00) {
						retorno.append(dividirLinha(7, 0, 30, 1270, "PROGRAMA AGUA PARA 20.000 LITROS QUITADOS PELO GOVERNO DO ESTADO DO PARA", 28, 20));
					}
				}
				
				retorno.append(formarLinha(0, 2, 424, 1270, "OPCAO PELO DEB. AUTOMATICO: ", 0, 0)
						+ formarLinha(5, 0, 649, 1270, (imovelEmitido.getIndicadorDebitoConta() == null ? ""
								: imovelEmitido.getId() + ""), 0, 0));

				gerarDadosMensagensConta(emitirContaHelper, retorno);

				retorno.append(formarLinha(7, 0, 404, 2606, emitirContaHelper.getIdImovel() + "", 0, 0)
						+ formarLinha(7, 0, 505, 2606,
								Util.formatarAnoMesParaMesAno(emitirContaHelper.getAmReferencia()), 0, 0)
						+ formarLinha(7, 0, 598, 2606, Util.formatarData(emitirContaHelper.getDataVencimentoConta()),
								0, 0)
						+ formarLinha(7, 0, 730, 2606, Util.formatarMoedaReal(emitirContaHelper.getValorConta()), 0, 0));

				gerarDadosCodigoDeBarras(emitirContaHelper, retorno, imovelEmitido);

				retorno.append(formarLinha(5, 0, 79, 3010, "GRUPO", 0, 0));
				retorno.append(formarLinha(5, 0, 109, 3035, emitirContaHelper.getIdFaturamentoGrupo() + "", 0, 0));
				retorno.append(formarLinha(5, 0, 352, 3035, "4", 0, 0));
				retorno.append(formarLinha(5, 0, 615, 3035, "" + contador, 0, 0));
				retorno.append(formarLinha(5, 0, 615, 1661, "" + contador, 0, 0));
				
				Object[] dadosAgenciaReguladora = getControladorFaturamento().obterDadosAgenciaReguladora();

				this.gerarLinhasAliquotasImpostos(emitirContaHelper, sistemaParametro, dadosAgenciaReguladora, retorno);

				this.gerarLinhaDadosAgenciaReguladora(emitirContaHelper, retorno);

				retorno.append("FORM\n" + "PRINT\n");

				return retorno.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return retorno.toString();

	}

	private void gerarDadosComuns(EmitirContaHelper emitirContaHelper, SistemaParametro sistemaParametro,
			StringBuilder retorno, Imovel imovelEmitido, ConsumoHistorico consumoAgua, ConsumoHistorico consumoEsgoto)
			throws ControladorException {

		MedicaoHistorico medicaoHistoricoAgua = getControladorMicromedicao()
				.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(imovelEmitido.getId(),
						emitirContaHelper.getAmReferencia());
		MedicaoHistorico medicaoHistoricoPoco = getControladorMicromedicao()
				.pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(imovelEmitido.getId(),
						emitirContaHelper.getAmReferencia());

		String endereco = "";
		String municipioEntrega = "";
		String bairroEntrega = "";
		String cepEntrega = "";
		String ufEntrega = "";
		String logCepClie = "";
		String logBairroClie = "";
		String cpfCnpjFormatado = "";

		Collection colecaoClienteImovel;
		try {
			colecaoClienteImovel = repositorioClienteImovel.pesquisarClienteImovelResponsavelConta(imovelEmitido
					.getId());
			if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
				ClienteImovel clienteImovelRespConta = (ClienteImovel) colecaoClienteImovel.iterator().next();

				if (clienteImovelRespConta != null) {
					Cliente cliente = clienteImovelRespConta.getCliente();

					if (cliente != null) {
						if (cliente.getCnpjFormatado() != null) {
							cpfCnpjFormatado = cliente.getCnpjFormatado();
						} else if (cliente.getCpfFormatado() != null) {
							cpfCnpjFormatado = cliente.getCpfFormatado();
						}
					}

					if (cliente != null
							&& imovelEmitido.getImovelContaEnvio().getId()
									.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)) {

						String[] enderecoCliente = getControladorEndereco()
								.pesquisarEnderecoClienteAbreviadoDivididoCosanpa(cliente.getId());
						bairroEntrega = enderecoCliente[3];
						municipioEntrega = enderecoCliente[1];
						ufEntrega = enderecoCliente[2];
						cepEntrega = enderecoCliente[4];
						logCepClie = enderecoCliente[5];
						logBairroClie = enderecoCliente[6];

						endereco = enderecoCliente[0] + " - " + bairroEntrega + " " + municipioEntrega + " "
								+ ufEntrega + " " + cepEntrega;

					}
				}
			}

			String[] enderecoImovel2 = getControladorEndereco().pesquisarEnderecoFormatadoDivididoCosanpa(
					imovelEmitido.getId());

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

			gerarInformacoesBasicas(emitirContaHelper, sistemaParametro, retorno, imovelEmitido, endereco,
					cpfCnpjFormatado);

			String diasConsumo = obterDiasConsumo(emitirContaHelper, medicaoHistoricoAgua, medicaoHistoricoPoco,
					consumoAgua, consumoEsgoto);

			retorno.append(formarLinha(7, 0, 20, 310, obterNumeroHidrometro(imovelEmitido), 0, 0));
			retorno.append(formarLinha(7, 0, 255, 310, obterDataInstalacaoHidroemtro(imovelEmitido), 0, 0));
			retorno.append(formarLinha(7, 0, 500, 310,
					obterSituacaoAgua(emitirContaHelper.getDescricaoLigacaoAguaSituacao()), 0, 0));
			retorno.append(formarLinha(7, 0, 688, 310, emitirContaHelper.getDescricaoLigacaoEsgotoSituacao(), 0, 0));

			// Leitura Anterior
			String leituraAnteriorInformada = obterLeituraAnteriorInformada(medicaoHistoricoAgua, medicaoHistoricoPoco);
			String leituraAtualInformada = obterLeituraAtualInformada(medicaoHistoricoAgua, medicaoHistoricoPoco);
			// retorno.append(formarLinha(7, 0, 188, 330, "LEITURA", 0, 0) +
			// formarLinha(7, 0, 190, 354, leituraAnteriorInformada, 0, 0) +
			// formarLinha(7, 0, 190, 378, leituraAtualInformada, 0, 0));

			String dataLeituraAnteriorInformada = obterDataLeituraAnteriorInformada(medicaoHistoricoAgua,
					medicaoHistoricoPoco, emitirContaHelper, imovelEmitido.getId());
			String dataLeituraAtualInformada = obterDataLeituraAtualInformada(medicaoHistoricoAgua,
					medicaoHistoricoPoco);
			// retorno.append(formarLinha(7, 0, 320, 330, "DATA", 0, 0) +
			// formarLinha(7, 0, 298, 354, dataLeituraAnteriorInformada, 0, 0) +
			// formarLinha(7, 0, 298, 378, dataLeituraAtualInformada, 0, 0));

			// Leitura Atual
			// retorno.append(formarLinha(7, 0, 37, 354, "ANTERIOR", 0, 0) +
			// formarLinha(7, 0, 37, 378, "ATUAL", 0, 0));

			if (medicaoHistoricoAgua != null) {
				if (medicaoHistoricoAgua.getLeituraAnormalidadeFaturamento() != null) {
					retorno.append(formarLinha(0, 2, 430, 374, "ANORM. LEITURA: "
							+ medicaoHistoricoAgua.getLeituraAnormalidadeFaturamento().getDescricao(), 0, 0));
				}
			}

			// Leitura Anterior
			String leituraAnteriorFaturada = obterLeituraAnteriorFaturada(medicaoHistoricoAgua, medicaoHistoricoPoco);
			String leituraAtualFaturada = obterLeituraAtualFaturada(medicaoHistoricoAgua, medicaoHistoricoPoco,
					consumoAgua);
			retorno.append(formarLinha(7, 0, 163, 362, "LEITURA", 0, 0) + formarLinha(7, 0, 163, 382, "FATURADA", 0, 0)
					+ formarLinha(7, 0, 190, 406, leituraAnteriorFaturada, 0, 0)
					+ formarLinha(7, 0, 190, 430, leituraAtualFaturada, 0, 0));

			// Leitura Atual
			String dataLeituraAnteriorFaturada = obterDataLeituraAnteriorFaturada(medicaoHistoricoAgua,
					medicaoHistoricoPoco);
			String dataLeituraAtualFaturada = obterDataLeituraAtualFaturada(medicaoHistoricoAgua, medicaoHistoricoPoco);

			retorno.append(formarLinha(7, 0, 313, 382, "DATA", 0, 0)
					+ formarLinha(7, 0, 285, 406, dataLeituraAnteriorFaturada, 0, 0)

					+ formarLinha(7, 0, 285, 430, dataLeituraAtualFaturada, 0, 0));

			gerarDadosConsumoTotal(retorno, imovelEmitido, consumoAgua, consumoEsgoto, medicaoHistoricoAgua,
					medicaoHistoricoPoco);

			// Numero de dias
			retorno.append(formarLinha(7, 0, 745, 382, "DIAS", 0, 0) + formarLinha(7, 0, 760, 406, diasConsumo, 0, 0));

			String anormalidadeConsumo = null;
			if (consumoAgua != null) {
				if (consumoAgua.getConsumoAnormalidade() != null) {
					if (consumoAgua.getConsumoAnormalidade().getDescricao() != null) {
						anormalidadeConsumo = consumoAgua.getConsumoAnormalidade().getDescricao();
					}
				}
				if (anormalidadeConsumo != null) {
					retorno.append(formarLinha(0, 2, 430, 460, "ANORM. CONSUMO: " + anormalidadeConsumo, 0, 0));
				}
			}

			retorno.append(formarLinha(7, 0, 37, 406, "ANTERIOR", 0, 0) + formarLinha(7, 0, 37, 430, "ATUAL", 0, 0));

			retorno.append(formarLinha(7, 0, 50, 579, "ULTIMOS CONSUMOS", 0, 0));
			retorno.append("LINE 115 605 115 745 1\n");

			// Ultimos Consumos

			int flag = 0;
			int soma = 0;
			int mesesDeConsumo = 0;

			for (int i = 0; i < 6; i++) {

				Object[] consumoAnterior = obterConsumoAnteriorIS(imovelEmitido.getId(),
						emitirContaHelper.getAmReferencia(), i + 1, 1, 1);
				MedicaoHistorico medicaoAnterior = getControladorMicromedicao()
						.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(imovelEmitido.getId(),
								Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), i + 1));

				retorno.append(formarLinha(0, 2, 44, 602,

				Util.formatarAnoMesParaMesAno(Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), i + 1))
						+ "", 0, i * 25));

				String anormalidade = "";
				String consumoMesAnterior = "";

				if (medicaoAnterior != null) {
					if (medicaoAnterior.getLeituraAnormalidadeFaturamento() != null) {
						anormalidade = "A. Leit.:" + medicaoAnterior.getLeituraAnormalidadeFaturamento().getId() + "";
						flag++;
					}
				}

				if (consumoAnterior != null) {
					if (consumoAnterior[0] != null) {
						if (medicaoHistoricoAgua == null && medicaoHistoricoPoco == null) {
							soma = soma + Integer.parseInt(consumoAnterior[0] + "");
							mesesDeConsumo++;
						}
						consumoMesAnterior = consumoAnterior[0] + " m3 ";
					}

					if (consumoAnterior[1] != null && anormalidade.equals("")) {
						anormalidade = "A. Cons.:" + consumoAnterior[2] + "";
					}

					flag++;
				}

				retorno.append(formarLinha(0, 2, 127, 602, consumoMesAnterior + anormalidade, 0, i * 25));

			}
			if (flag == 0) {
				retorno.append(formarLinha(7, 0, 50, 484, "HISTORICO DE CONSUMO", 0, 0)
						+ formarLinha(7, 0, 50, 505, "INEXISTENTE", 0, 0));
			}

			gerarDadosMedia(retorno, medicaoHistoricoAgua, medicaoHistoricoPoco, consumoAgua, consumoEsgoto, soma,
					mesesDeConsumo);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}

	}

	private void gerarDadosMensagensConta(EmitirContaHelper emitirContaHelper, StringBuilder retorno) {
		String msgConta = "";

		if (emitirContaHelper.getMsgLinha1Conta() != null) {
			msgConta = emitirContaHelper.getMsgLinha1Conta();
			if (emitirContaHelper.getMsgLinha2Conta() != null) {
				msgConta = msgConta + " " + emitirContaHelper.getMsgLinha2Conta();
				if (emitirContaHelper.getMsgLinha3Conta() != null) {
					msgConta = msgConta + " " + emitirContaHelper.getMsgLinha3Conta();
				}
			}

			msgConta = msgConta.replace('!', '.');
		}

		if (msgConta.length() > 60) {
			retorno.append(formarLinha(7, 0, 35, 1815, msgConta.substring(0, 60), 0, 0));
			if (msgConta.length() > 120) {
				retorno.append(formarLinha(7, 0, 35, 1835, msgConta.substring(60, 120), 0, 0));
				if (msgConta.length() > 180) {
					retorno.append(formarLinha(7, 0, 35, 1855, msgConta.substring(120, 180), 0, 0));
					if (msgConta.length() > 240) {
						retorno.append(formarLinha(7, 0, 35, 1875, msgConta.substring(180, 240), 0, 0));
					} else {
						retorno.append(formarLinha(7, 0, 35, 1875, msgConta.substring(180), 0, 0));
					}
				} else {
					retorno.append(formarLinha(7, 0, 35, 1855, msgConta.substring(120), 0, 0));
				}
			} else {
				retorno.append(formarLinha(7, 0, 35, 1835, msgConta.substring(60), 0, 0));
			}
		} else {
			retorno.append(formarLinha(7, 0, 35, 1815, msgConta, 0, 0));
		}
	}

	private void gerarDadosCodigoDeBarras(EmitirContaHelper emitirContaHelper, StringBuilder retorno,
			Imovel imovelEmitido) throws ControladorException {
		if (!imovelEmitido.getIndicadorDebitoConta().equals(Imovel.INDICADOR_DEBITO_AUTOMATICO)) {
			Integer digitoVerificadorConta = new Integer("" + emitirContaHelper.getDigitoVerificadorConta());
			// formata ano mes para mes ano
			String anoMes = "" + emitirContaHelper.getAmReferencia();
			String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

			String representacaoNumericaCodBarra = "";

			// Linha28
			Date dataValidade = emitirContaHelper.getDataValidadeConta();

			if (emitirContaHelper.getValorConta() != null) {
				if (emitirContaHelper.getValorConta().compareTo(new BigDecimal("0.00")) != 0) {
					representacaoNumericaCodBarra = this.getControladorArrecadacao()
							.obterRepresentacaoNumericaCodigoBarra(3, emitirContaHelper.getValorConta(),
									emitirContaHelper.getIdLocalidade(), emitirContaHelper.getIdImovel(), mesAno,
									digitoVerificadorConta, null, null, null, null, null, null, null);

					// Linha 24
					// Formata a representação númerica do código de
					// barras
					String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra.substring(0, 11)
							+ "-" + representacaoNumericaCodBarra.substring(11, 12) + " "
							+ representacaoNumericaCodBarra.substring(12, 23) + "-"
							+ representacaoNumericaCodBarra.substring(23, 24) + " "
							+ representacaoNumericaCodBarra.substring(24, 35) + "-"
							+ representacaoNumericaCodBarra.substring(35, 36) + " "
							+ representacaoNumericaCodBarra.substring(36, 47) + "-"
							+ representacaoNumericaCodBarra.substring(47, 48);
					emitirContaHelper.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

					// Linha 25
					String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra.substring(0, 11)
							+ representacaoNumericaCodBarra.substring(12, 23)
							+ representacaoNumericaCodBarra.substring(24, 35)
							+ representacaoNumericaCodBarra.substring(36, 47);
					emitirContaHelper.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
				}

				String representacaoNumericaCodBarraFormatada = emitirContaHelper
						.getRepresentacaoNumericaCodBarraFormatada();
				if (representacaoNumericaCodBarraFormatada != null) {

					retorno.append(formarLinha(5, 0, 66, 2840, representacaoNumericaCodBarraFormatada, 0, 0));
					String representacaoCodigoBarrasSemDigitoVerificador = emitirContaHelper
							.getRepresentacaoNumericaCodBarraSemDigito();
					retorno.append("B I2OF5 1 2 120 35 2863 " + representacaoCodigoBarrasSemDigitoVerificador + "\n");
				}
			}
		} else {
			retorno.append(formarLinha(4, 0, 182, 2863, "DEBITO AUTOMATICO", 0, 0));

		}
	}

	private void gerarDadosMedia(StringBuilder retorno, MedicaoHistorico medicaoHistoricoAgua,
			MedicaoHistorico medicaoHistoricoPoco, ConsumoHistorico consumoAgua, ConsumoHistorico consumoEsgoto,
			int soma, int mesesDeConsumo) {
		String media = obterMedia(medicaoHistoricoAgua, medicaoHistoricoPoco, consumoAgua, consumoEsgoto);
		if (medicaoHistoricoAgua == null && medicaoHistoricoPoco == null && mesesDeConsumo != 0) {
			media = (soma / mesesDeConsumo) + "";
		}

		retorno.append(formarLinha(7, 0, 75, 742, "MEDIA(m3):", 0, 0) + formarLinha(7, 0, 195, 742, media, 0, 0));

	}

	private void gerarDadosQualidadeAgua(EmitirContaHelper emitirContaHelper, String[] qualidadeAgua,
			StringBuilder retorno) {

		retorno.append(formarLinha(7, 0, 425, 565, "QUALIDADE DA AGUA", 0, 0));
		retorno.append(formarLinha(7, 0, 645, 565, "Ref: ", 0, 0)
				+ formarLinha(7, 0, 690, 565,
						Util.retornaDescricaoAnoMesCompleto(emitirContaHelper.getAmReferencia() + ""), 0, 0));
		retorno.append(formarLinha(7, 0, 287, 590, "PARAMETROS", 0, 0) + formarLinha(7, 0, 418, 590, "Port. 518", 0, 0)
				+ formarLinha(7, 0, 540, 590, "ANALISADO", 0, 0) + formarLinha(7, 0, 672, 590, "CONFORME", 0, 0));

		// Daniel - Parametro
		retorno.append(formarLinha(7, 0, 287, 620, "COR(uH)", 0, 0));
		retorno.append(formarLinha(7, 0, 287, 640, "TURBIDEZ(UT)", 0, 0));
		retorno.append(formarLinha(7, 0, 287, 660, "CLORO(mg/L)", 0, 0));
		retorno.append(formarLinha(7, 0, 287, 680, "FLUOR(mg/L)", 0, 0));
		retorno.append(formarLinha(7, 0, 287, 700, "COLIFORME TOTAL", 0, 0)
				+ formarLinha(7, 0, 287, 720, "Pres/Aus)", 0, 0));
		retorno.append(formarLinha(7, 0, 287, 740, "COLIFORME TERMO", 0, 0)
				+ formarLinha(7, 0, 287, 760, "TOLER.(Pres/Aus)", 0, 0));
		// Qualidade da agua
		// Daniel - Exigido !=Alterar
		retorno.append(formarLinha(7, 0, 469, 620, qualidadeAgua[6], 0, 0));
		retorno.append(formarLinha(7, 0, 469, 640, qualidadeAgua[7], 0, 0));
		retorno.append(formarLinha(7, 0, 469, 660, qualidadeAgua[9], 0, 0));
		retorno.append(formarLinha(7, 0, 469, 680, qualidadeAgua[8], 0, 0));
		retorno.append(formarLinha(7, 0, 469, 700, qualidadeAgua[10], 0, 0));
		retorno.append(formarLinha(7, 0, 469, 740, qualidadeAgua[11], 0, 0));

		// Daniel - Analisado
		retorno.append(formarLinha(7, 0, 582, 620, qualidadeAgua[12], 0, 0));
		retorno.append(formarLinha(7, 0, 582, 640, qualidadeAgua[13], 0, 0));
		retorno.append(formarLinha(7, 0, 582, 660, qualidadeAgua[15], 0, 0));
		retorno.append(formarLinha(7, 0, 582, 680, qualidadeAgua[14], 0, 0));
		retorno.append(formarLinha(7, 0, 582, 700, qualidadeAgua[16], 0, 0));
		retorno.append(formarLinha(7, 0, 582, 740, qualidadeAgua[17], 0, 0));

		// daniel - Conforme
		retorno.append(formarLinha(7, 0, 726, 620, qualidadeAgua[18], 0, 0));
		retorno.append(formarLinha(7, 0, 726, 640, qualidadeAgua[19], 0, 0));
		retorno.append(formarLinha(7, 0, 726, 660, qualidadeAgua[21], 0, 0));
		retorno.append(formarLinha(7, 0, 726, 680, qualidadeAgua[20], 0, 0));
		retorno.append(formarLinha(7, 0, 726, 700, qualidadeAgua[22], 0, 0));
		retorno.append(formarLinha(7, 0, 726, 740, qualidadeAgua[23], 0, 0));

	}

	private void gerarDadosConsumoTotal(StringBuilder retorno, Imovel imovelEmitido, ConsumoHistorico consumoAgua,
			ConsumoHistorico consumoEsgoto, MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco)
			throws ControladorException {
		String consumo = obterConsumo(medicaoAgua, medicaoPoco, consumoAgua, consumoEsgoto);

		// Consumo
		if (imovelPossuiConsumo(imovelEmitido)) {
			if (imovelEmitido.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.LIGADO) {
				consumo += "/" + consumoAgua.getConsumoRateio();
			} else if (imovelEmitido.getLigacaoEsgotoSituacao().getId() == LigacaoEsgotoSituacao.LIGADO) {
				consumo += "/" + consumoEsgoto.getConsumoRateio();
			}

			retorno.append(formarLinha(7, 0, 412, 372, "CONSUMO/RATEIO (m3)", 0, 0)
					+ formarLinha(7, 0, 511, 396, consumo, 0, 0));
		} else if (consumoAgua != null) {
			ConsumoTipo consumoTipoAgua = getControladorMicromedicao().consultarDadosConsumoTipoConsumoHistorico(
					consumoAgua);
			if (consumoTipoAgua != null) {
				if (consumoTipoAgua.getId() != null) {
					retorno.append(formarLinha(7, 0, 412, 372, getTipoConsumoToPrint(consumoTipoAgua.getId()), 0, 0));
				}
			}
			retorno.append(formarLinha(7, 0, 511, 396, consumo, 0, 0));
		} else if (consumoEsgoto != null) {
			ConsumoTipo consumoTipoEsgoto = getControladorMicromedicao().consultarDadosConsumoTipoConsumoHistorico(
					consumoEsgoto);
			if (consumoTipoEsgoto != null) {
				if (consumoTipoEsgoto.getDescricao() != null) {
					retorno.append(formarLinha(7, 0, 412, 372, consumoTipoEsgoto.getDescricao(), 0, 0));
				}
			}
			retorno.append(formarLinha(7, 0, 511, 396, consumo, 0, 0));
		}
	}

	private boolean imovelPossuiConsumo(Imovel imovelEmitido) {
		return (imovelEmitido.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.LIGADO || imovelEmitido
				.getLigacaoEsgotoSituacao().getId() == LigacaoEsgotoSituacao.LIGADO)
				&& imovelEmitido.getImovelCondominio() != null;
	}

	private String obterDiasConsumo(EmitirContaHelper emitirContaHelper, MedicaoHistorico medicaoAgua,
			MedicaoHistorico medicaoPoco, ConsumoHistorico consumoAgua, ConsumoHistorico consumoEsgoto) {
		String diasConsumo = "";

		if (medicaoAgua != null) {

			if (consumoAgua != null) {
				if (medicaoAgua.getLeituraAtualFaturamento() != 0) {
					diasConsumo = Util.obterQuantidadeDiasEntreDuasDatas(
							medicaoAgua.getDataLeituraAnteriorFaturamento(),
							medicaoAgua.getDataLeituraAtualFaturamento())
							+ "";
				} else {
					diasConsumo = Util.obterQuantidadeDiasEntreDuasDatas(medicaoAgua.getDataLeituraAtualFaturamento(),
							medicaoAgua.getDataLeituraAnteriorFaturamento()) + "";
				}
			}

		} else if (medicaoPoco != null) {
			if (consumoEsgoto != null) {

				if (medicaoPoco.getLeituraAtualFaturamento() != 0) {
					diasConsumo = Util.obterQuantidadeDiasEntreDuasDatas(medicaoPoco.getDataLeituraAtualFaturamento(),
							medicaoPoco.getDataLeituraAnteriorFaturamento()) + "";
				} else {
					diasConsumo = Util.obterQuantidadeDiasEntreDuasDatas(medicaoPoco.getDataLeituraAtualFaturamento(),
							medicaoPoco.getDataLeituraAnteriorFaturamento()) + "";
				}
			}
		} else if (medicaoAgua == null && medicaoPoco == null) {
			if (consumoAgua != null) {
				int mes = Util.obterMes(emitirContaHelper.getAmReferencia());
				int ano = Util.obterAno(emitirContaHelper.getAmReferencia());
				diasConsumo = Util.obterUltimoDiaMes(mes, ano) + "";
			}
		}
		return diasConsumo;
	}

	private String obterSituacaoAgua(String situacaoAgua) {
		String situacao = "";
		if (situacaoAgua.length() > 13) {
			situacao = situacaoAgua.substring(0, 13);
		} else {
			situacao = situacaoAgua;
		}

		return situacao;
	}

	private String obterDataInstalacaoHidroemtro(Imovel imovelEmitido) {
		String dataInstalacao = "";

		if (imovelEmitido.getLigacaoAgua() != null) {
			if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
				dataInstalacao = Util.formatarData(imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico()
						.getDataInstalacao());
			}
		}
		return dataInstalacao;
	}

	private String obterNumeroHidrometro(Imovel imovelEmitido) {
		String hidrometro = "NAO MEDIDO";

		if (imovelEmitido.getLigacaoAgua() != null) {
			if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
				hidrometro = imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro()
						.getNumero();
			}
		}
		return hidrometro;
	}

	private String obterLeituraAnteriorInformada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco) {
		String leituraAnteriorInformada = "";

		if (medicaoAgua != null) {
			if (medicaoAgua.getLeituraAnteriorInformada() != null) {
				leituraAnteriorInformada = medicaoAgua.getLeituraAnteriorInformada() + "";
			}
		} else if (medicaoPoco != null) {

			if (medicaoPoco.getLeituraAnteriorInformada() != null) {
				leituraAnteriorInformada = medicaoPoco.getLeituraAnteriorInformada() + "";
			}
		}
		return leituraAnteriorInformada;
	}

	private String obterLeituraAnteriorFaturada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco) {
		String leituraAnteriorFaturada = "";

		if (medicaoAgua != null) {
			if (medicaoAgua.getLeituraAnteriorFaturamento() != 0) {
				leituraAnteriorFaturada = medicaoAgua.getLeituraAnteriorFaturamento() + "";
			}
		} else if (medicaoPoco != null) {

			if (medicaoPoco.getLeituraAnteriorFaturamento() != 0) {
				leituraAnteriorFaturada = medicaoPoco.getLeituraAnteriorFaturamento() + "";
			}
		}
		return leituraAnteriorFaturada;
	}

	private String obterLeituraAtualFaturada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco,
			ConsumoHistorico consumo) {
		String leituraAtualFaturada = "";

		if (medicaoAgua != null) {
			if (medicaoAgua.getLeituraAtualFaturamento() != 0) {
				leituraAtualFaturada = medicaoAgua.getLeituraAtualFaturamento() + "";
			}

			if (consumo != null) {
				if (medicaoAgua.getLeituraAtualFaturamento() != 0) {
					leituraAtualFaturada = medicaoAgua.getLeituraAtualFaturamento() + "";
				}
			}
		} else if (medicaoPoco != null) {

			if (medicaoPoco.getLeituraAtualFaturamento() != 0) {
				leituraAtualFaturada = medicaoPoco.getLeituraAtualFaturamento() + "";
			}
		}
		return leituraAtualFaturada;
	}

	private String obterLeituraAtualInformada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco) {
		String leituraAtualInformada = "";

		if (medicaoAgua != null) {
			if (medicaoAgua.getLeituraAtualInformada() != null) {
				leituraAtualInformada = medicaoAgua.getLeituraAtualInformada() + "";
			}
		} else if (medicaoPoco != null) {
			if (medicaoPoco.getLeituraAtualInformada() != null) {
				leituraAtualInformada = medicaoPoco.getLeituraAtualInformada() + "";
			}
		}
		return leituraAtualInformada;
	}

	private String obterDataLeituraAnteriorFaturada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco) {
		String dataLeituraAnteriorFaturada = "";

		if (medicaoAgua != null) {
			if (medicaoAgua.getDataLeituraAnteriorFaturamento() != null) {
				dataLeituraAnteriorFaturada = Util.formatarData(medicaoAgua.getDataLeituraAnteriorFaturamento());
			}
		} else if (medicaoPoco != null) {
			if (medicaoPoco.getDataLeituraAnteriorFaturamento() != null) {
				dataLeituraAnteriorFaturada = Util.formatarData(medicaoPoco.getDataLeituraAnteriorFaturamento());
			}
		}
		return dataLeituraAnteriorFaturada;
	}

	private String obterDataLeituraAtualInformada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco) {
		String dataLeituraAtualInformada = "";

		if (medicaoAgua != null) {
			if (medicaoAgua.getDataLeituraAtualInformada() != null) {
				dataLeituraAtualInformada = Util.formatarData(medicaoAgua.getDataLeituraAtualInformada());
			}
		} else if (medicaoPoco != null) {
			if (medicaoPoco.getDataLeituraAtualInformada() != null) {
				dataLeituraAtualInformada = Util.formatarData(medicaoPoco.getDataLeituraAtualInformada());
			}
		}
		return dataLeituraAtualInformada;
	}

	private String obterDataLeituraAtualFaturada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco) {
		String dataLeituraAtualFaturada = "";

		if (medicaoAgua != null) {
			if (medicaoAgua.getDataLeituraAtualFaturamento() != null) {
				dataLeituraAtualFaturada = Util.formatarData(medicaoAgua.getDataLeituraAtualFaturamento());
			}
		} else if (medicaoPoco != null) {
			if (medicaoPoco.getDataLeituraAtualFaturamento() != null) {
				dataLeituraAtualFaturada = Util.formatarData(medicaoPoco.getDataLeituraAtualFaturamento());
			}
		}
		return dataLeituraAtualFaturada;
	}

	private String obterMedia(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco, ConsumoHistorico consumoAgua,
			ConsumoHistorico consumoEsgoto) {
		String media = "0";

		if (medicaoAgua != null) {
			if (consumoAgua != null) {
				media = String.valueOf(consumoAgua.getConsumoMedio());
			}
		} else if (medicaoPoco != null) {
			if (consumoEsgoto != null) {
				media = String.valueOf(consumoEsgoto.getConsumoMedio());
			}
		}
		return media;
	}

	private int obterTipoConsumo(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco,
			ConsumoHistorico consumoAgua, ConsumoHistorico consumoEsgoto) {
		int tipoConsumo = 0;

		if (medicaoAgua != null) {
			if (consumoAgua != null) {
				tipoConsumo = consumoAgua.getConsumoTipo().getId();
			}
		} else if (medicaoPoco != null) {
			if (consumoEsgoto != null) {
				tipoConsumo = consumoEsgoto.getConsumoTipo().getId();
			}
		}
		return tipoConsumo;
	}

	private String obterConsumo(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco,
			ConsumoHistorico consumoAgua, ConsumoHistorico consumoEsgoto) {
		String consumo = "";

		if (medicaoAgua != null) {
			if (consumoAgua != null) {
				if (medicaoAgua.getLeituraAtualFaturamento() != 0) {
					consumo = consumoAgua.getNumeroConsumoFaturadoMes() + "";
				} else {
					consumo = consumoAgua.getNumeroConsumoFaturadoMes() + "";
				}
			}
		} else if (medicaoPoco != null) {
			if (consumoEsgoto != null) {
				if (medicaoPoco.getLeituraAtualFaturamento() != 0) {
					consumo = consumoEsgoto.getNumeroConsumoFaturadoMes() + "";
				} else {
					consumo = consumoEsgoto.getNumeroConsumoFaturadoMes() + "";
				}
			}
		} else if (medicaoAgua == null && medicaoPoco == null) {
			if (consumoAgua != null) {
				consumo = consumoAgua.getNumeroConsumoFaturadoMes() + "";
			}
		}
		return consumo;
	}

	@SuppressWarnings("unused")
	private String obterDataLeituraAnteriorInformada(MedicaoHistorico medicaoAgua, MedicaoHistorico medicaoPoco,
			EmitirContaHelper helper, Integer idImovelEmitido) throws ControladorException {
		String dataLeituraAnteriorInformada = "";

		int mesAnterior = Util.subtrairMesDoAnoMes(helper.getAmReferencia(), 1);
		MedicaoHistorico medicaoAguaAnterior = getControladorMicromedicao()
				.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(idImovelEmitido, mesAnterior);
		MedicaoHistorico medicaoPocoAnterior = getControladorMicromedicao()
				.pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(idImovelEmitido, mesAnterior);

		if (medicaoAgua != null) {
			if (medicaoAguaAnterior != null) {
				if (medicaoAguaAnterior.getDataLeituraAtualInformada() != null) {
					dataLeituraAnteriorInformada = Util
							.formatarData(medicaoAguaAnterior.getDataLeituraAtualInformada());
				}
			}

		} else if (medicaoPoco != null) {
			if (medicaoPocoAnterior.getDataLeituraAtualInformada() != null) {
				dataLeituraAnteriorInformada = Util.formatarData(medicaoPocoAnterior.getDataLeituraAtualInformada());
			}
		}
		return dataLeituraAnteriorInformada;
	}

	private void gerarInformacoesBasicas(EmitirContaHelper emitirContaHelper, SistemaParametro sistemaParametro,
			StringBuilder retorno, Imovel imovelEmitido, String endereco, String cpfCnpjFormatado) {
		retorno.append("! 0 200 200 3100 1\n");

		retorno.append("BOX 32 405 802 452 1\n" + 
					   "LINE 720 385 720 425 1\n" + 
					   "LINE 403 385 403 447 1\n" +

					   "BOX 32 361 802 405 1\n" + 
					   "LINE 278 385 278 447 1\n" +

					   "BOX 283 588 802 615 1\n" + 
					   "BOX 283 615 802 782 1\n" + 
					   "LINE 656 588 656 782 1\n" + 
					   "LINE 415 588 415 615 1\n" + 
					   "LINE 535 588 535 782 1\n");

		retorno.append("T 7 0 50 51 " + "Data de Emissao: " + Util.formatarDataComHora(new Date()) + "\n");
		retorno.append("T 7 1 70 115 " + imovelEmitido.getId() + "\n");
		retorno.append("T 7 1 640 115 " + Util.retornaDescricaoAnoMesCompleto(emitirContaHelper.getAmReferencia() + "")
				+ "\n");
		// retorno.append("T 0 0 201 47 " +
		// Util.formatarCnpj(sistemaParametro.getCnpjEmpresa().trim()) + "\n");
		// retorno.append("T 0 0 285 64 " +
		// sistemaParametro.getInscricaoEstadual().trim() + "\n");
		// retorno.append(formarLinha(0, 0, 222, 81,
		// emitirContaHelper.getIdFaturamentoGrupo() + "", 0, 0));

		retorno.append(formarLinha(0, 2, 20, 200, obterNomeCliente(emitirContaHelper, imovelEmitido), 0, 0)
				+ formarLinha(0, 2, 20, 222, cpfCnpjFormatado, 0, 0)
				+ dividirLinha(0, 2, 350, 200, endereco.trim(), 55, 22));
		retorno.append(formarLinha(7, 0, 20, 260, imovelEmitido.getInscricaoFormatada(), 0, 0));
		retorno.append(formarLinha(7, 0, 350, 260, imovelEmitido.getQuadra().getRota().getCodigo() + "", 0, 0));
		retorno.append(formarLinha(7, 0, 445, 260, imovelEmitido.getNumeroSequencialRota() + "", 0, 0));

		gerarDadosQtdEconomias(retorno, imovelEmitido);
	}

	private String obterNomeCliente(EmitirContaHelper emitirContaHelper, Imovel imovel) {
		String nome = "";
		try {
			if (imovel.isCondominio()) {
				nome = getControladorCliente().obterNomeCliente(imovel.getId()).trim();
			} else {
				nome = emitirContaHelper.getNomeCliente().trim();
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}

		return nome;
	}

	private void gerarDadosQtdEconomias(StringBuilder retorno, Imovel imovelEmitido) {
		Vector quantidadeEconomias = retornarDescricaoCategoriasQuantidadeEconomias(imovelEmitido);

		for (int i = 0; i < quantidadeEconomias.size(); i++) {
			Object[] dadosCategoria = (Object[]) quantidadeEconomias.elementAt(i);
			retorno.append(formarLinha(0, 0, 464, 260, dadosCategoria[0] + "", i * 85, 0));
			retorno.append(formarLinha(7, 0, 710, 260, dadosCategoria[1] + "", i * 85, 0));
		}
	}

	private void gerarInformativoMacroCondominio(EmitirContaHelper emitirContaHelper, StringBuilder retorno,
			Imovel imovelEmitido) {
		retorno.append("T 7 0 200 700 EXTRATO DE CONSUMO DO MACROMEDIDOR \n");
		retorno.append("T 7 0 53 725 CONSUMO DO IMOVEL CONDOMINIO \n");
		retorno.append("T 7 0 571 725 " + emitirContaHelper.getConsumoMacro() + "\n");
		retorno.append("T 7 0 53 750 SOMA DOS CONSUMOS DOS IMOVEIS VINCULADOS \n");
		retorno.append("T 7 0 571 750 " + emitirContaHelper.getSomaConsumosImoveisMicro() + "\n");
		retorno.append("T 7 0 53 775 QUANTIDADE IMOVEIS VINCULADOS \n");
		retorno.append("T 7 0 571 775 " + emitirContaHelper.getQuantidadeImoveisMicro() + "\n");
		retorno.append("T 7 0 53 800 VALOR RATEADO \n");
		retorno.append("T 7 0 571 800  R$ " + Util.formatarMoedaReal(emitirContaHelper.getValorTotalASerrateado())
				+ "\n");
		retorno.append("T 7 0 53 825 VALOR RATEADO POR UNIDADE \n");
		retorno.append("T 7 0 571 825 R$ " + Util.formatarMoedaReal(emitirContaHelper.getValorRateioAgua()) + "\n");

		retorno.append("T 7 0 367 850 IMPORTANTE \n");
		retorno.append("T 7 0 53 900 CASO O VALOR DO RATEIO ESTEJA ELEVADO \n");
		retorno.append("T 7 0 63 925 1. Confirme a leitura do macro \n");
		retorno.append("T 7 0 63 950 2. Verifique os reservatorios \n");
		retorno.append("T 7 0 63 975 3. Verifique se ha apartamento ligado clandestinamente\n");
		retorno.append("T 7 0 53 1025 QUALQUER IRREGULARIDADE COMUNIQUE A COSANPA ATRAVES DO \n");
		retorno.append("T 7 0 53 1050 SETOR DE ATENDIMENTO \n");
		retorno.append("T 7 0 53 1075 RATEIO: Obtido atraves da diferenca do consumo entre \n");
		retorno.append("T 7 0 53 1100 o macromedidor e os consumos dos apartamentos \n");
		retorno.append("T 0 2 344 1456 " + imovelEmitido.getMatriculaFormatada() + "\n");
		retorno.append("T 5 0 109 1661 " + emitirContaHelper.getIdFaturamentoGrupo() + "\n");
		retorno.append("T 5 0 352 1661 4\n");

		retorno.append("FORM\n" + "PRINT\n");
	}

	public String getTipoConsumoToPrint(int tipoConsumo) {

		String resultado = "";

		if (tipoConsumo == ConsumoTipo.MEDIA_HIDROMETRO) {
			resultado = "CONSUMO MEDIO(m3)";

		} else if (tipoConsumo == ConsumoTipo.CONSUMO_MINIMO_FIXADO) {
			resultado = "CONSUMO MINIMO(m3)";

		} else if (tipoConsumo == ConsumoTipo.NAO_MEDIDO) {
			resultado = "CONSUMO NAO MEDIDO(m3)";

		} else if (tipoConsumo == ConsumoTipo.REAL) {
			resultado = "CONSUMO REAL(m3)";

		} else if (tipoConsumo == ConsumoTipo.CONSUMO_MEDIO_AJUSTADO) {
			resultado = "CONSUMO PROPOR. DIAS(m3)";

		} else if (tipoConsumo == ConsumoTipo.FIXO_SITUACAO_ESPECIAL) {
			resultado = "CONSUMO SIT. ESPECIAL(m3)";

		}
		return resultado;
	}

	public Object[] obterConsumoAnteriorIS(Integer idImovel, int anoMes, int qtdMeses, Integer tipoLigacao,
			Integer tipoMedicao) throws ControladorException {

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);

		Object[] parmsConsumoHistorico = null;
		if (tipoLigacao != null && tipoMedicao != null) {

			parmsConsumoHistorico = getControladorMicromedicao().obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
					anoMesSubtraido, tipoLigacao);
		}
		return parmsConsumoHistorico;
	}

	private void gerarLinhasAliquotasImpostos(EmitirContaHelper helper, SistemaParametro sistemaParametro,
			Object[] dadosAgenciaReguladora, StringBuilder retorno) {
		try {

			String linha = "";
			int coluna = 30;

			BigDecimal valorBaseCalculo = helper.getValorAgua().add(helper.getValorEsgoto()).add(helper.getDebitos())
					.subtract(obterValorPrestacao(helper));

			// Tributo PIS/Pasep
			BigDecimal percentualAliquota = sistemaParametro.getValorAliquotaImposto().divide(new BigDecimal(100));
			BigDecimal valorImposto = valorBaseCalculo.multiply(percentualAliquota);

			// Tributo Agencia Reguladora
			String nomeAgenciaReguladora = (String) dadosAgenciaReguladora[0];

			BigDecimal aliquotaAgenciaReguladora = (BigDecimal) dadosAgenciaReguladora[1];
			BigDecimal percentualAliquotaAgenciaReguladora = aliquotaAgenciaReguladora.divide(new BigDecimal(100));
			BigDecimal valorImpostoAgenciaReguladora = valorBaseCalculo.multiply(percentualAliquotaAgenciaReguladora);

			int municipioAgenciaReguladora = (Integer) dadosAgenciaReguladora[2];

			Localidade localidade = getControladorFaturamento().pesquisarLocalidadeConta(helper.getIdLocalidade());

			int municipio = localidade != null ? localidade.getMunicipio().getId() : -1;

			// Linha do cabecalho

			linha += formarLinha(0, 2, 140, 1100, "Tributos", coluna, 0);
			linha += formarLinha(0, 2, 340, 1100, "(%)", coluna, 0);
			linha += formarLinha(0, 2, 400, 1100, "Base calculo", coluna, 0);
			linha += formarLinha(0, 2, 520, 1100, "Valor (R$)", coluna, 0);
			// Linha dos valores
			linha += formarLinha(0, 2, 140, 1125, sistemaParametro.getDescricaoAliquotaImposto(), coluna, 0);
			linha += formarLinha(0, 2, 340, 1125, String.format("%.2f", sistemaParametro.getValorAliquotaImposto()),
					coluna, 0);
			linha += formarLinha(0, 2, 400, 1125, String.format("R$%.2f", valorBaseCalculo.doubleValue()), coluna, 0);
			linha += formarLinha(0, 2, 520, 1125, String.format("R$%.2f", valorImposto.doubleValue()), coluna, 0);

			if (municipio == municipioAgenciaReguladora) {
				// Linha dos valores da Agencia Reguladora
				linha += formarLinha(0, 2, 140, 1145, nomeAgenciaReguladora, coluna, 0);
				linha += formarLinha(0, 2, 340, 1145, String.format("%.2f", aliquotaAgenciaReguladora), coluna, 0);
				linha += formarLinha(0, 2, 400, 1145, String.format("R$%.2f", valorBaseCalculo.doubleValue()), coluna,
						0);
				linha += formarLinha(0, 2, 520, 1145,
						String.format("R$%.2f", valorImpostoAgenciaReguladora.doubleValue()), coluna, 0);

			}

			retorno.append(linha);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private BigDecimal obterValorPrestacao(EmitirContaHelper helper) throws ControladorException {
		// Tipos de financiamento que nao sao utilizados para calculo de
		// demonstrativo de imposto
		Collection<Integer> tipos = new ArrayList<Integer>();
		tipos.add(FinanciamentoTipo.PARCELAMENTO_AGUA);
		tipos.add(FinanciamentoTipo.PARCELAMENTO_ESGOTO);
		tipos.add(FinanciamentoTipo.PARCELAMENTO_SERVICO);
		tipos.add(FinanciamentoTipo.JUROS_PARCELAMENTO);

		Filtro filtro = new FiltroDebitoCobrado();
		filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCobrado.CONTA_ID, helper.getIdConta()));
		filtro.adicionarParametro(new ParametroSimplesIn(FiltroDebitoCobrado.FINANCIAMENTO_TIPO_ID, tipos));

		Collection<DebitoCobrado> debitosParcelamento = getControladorUtil().pesquisar(filtro,
				DebitoCobrado.class.getName());

		BigDecimal valorPrestacao = new BigDecimal(0.00);
		for (Iterator<DebitoCobrado> iterator = debitosParcelamento.iterator(); iterator.hasNext();) {
			DebitoCobrado debito = (DebitoCobrado) iterator.next();
			valorPrestacao = valorPrestacao.add(debito.getValorPrestacao());
		}

		return valorPrestacao;
	}

	private void gerarLinhaDadosAgenciaReguladora(EmitirContaHelper emitirContaHelper, StringBuilder retorno) throws ErroRepositorioException, ControladorException {
		Object[] dados = getControladorFaturamento().pesquisarContatosAgenciaReguladora(emitirContaHelper);

		if (dados != null && dados.length > 0) {
			String linha = formarLinha(7, 0, 243, 100, String.format("Ag. reguladora (%s)", (String) dados[0]), 0, 0);
			linha += formarLinha(7, 0, 243, 120, String.format("Telefone: %s", (String) dados[1]), 0, 0);
			linha += formarLinha(7, 0, 243, 140, String.format("Email: %s", (String) dados[2]), 0, 0);
			
			retorno.append(linha);
		}
	}
	
	private BigDecimal retornaCreditoBolsaAgua (CreditoRealizado cr, EmitirContaHelper helper) {
		BigDecimal valorAguaEsgoto = new BigDecimal("0.00");
		BigDecimal valorCredito = cr.getValorCredito();
		
		valorAguaEsgoto.add(helper.getValorAgua()); 
				
		if (helper.getValorEsgoto() != null){
				valorAguaEsgoto.add(helper.getValorEsgoto());
		}
				
		if (valorAguaEsgoto.compareTo(valorCredito) > 0) {
			return valorCredito;
		} else {
			return valorAguaEsgoto;
		}
			
	}
	
}
