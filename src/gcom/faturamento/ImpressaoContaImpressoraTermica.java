package gcom.faturamento;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;



public class ImpressaoContaImpressoraTermica {
	
	private IRepositorioFaturamento repositorioFaturamento;
	private IRepositorioClienteImovel repositorioClienteImovel;
	private SessionContext sessionContext;
	private static ImpressaoContaImpressoraTermica instancia;
	
	private ImpressaoContaImpressoraTermica(IRepositorioFaturamento repositorioFaturamento, IRepositorioClienteImovel repositorioClienteImovel, 
			SessionContext sessionContext) {

		this.repositorioClienteImovel = repositorioClienteImovel;
		this.repositorioFaturamento = repositorioFaturamento;
		this.sessionContext = sessionContext;
	}

	public static ImpressaoContaImpressoraTermica getInstancia(IRepositorioFaturamento repositorioFaturamento, IRepositorioClienteImovel repositorioClienteImovel,
			SessionContext sessionContext) {
		
		if (instancia == null) {
			instancia = new ImpressaoContaImpressoraTermica(repositorioFaturamento,repositorioClienteImovel, sessionContext);
		}
		return instancia;
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

		// pega a instância do ServiceLocator.

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

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
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

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
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
	
	private String formarLinha(int font, int tamanhoFonte, int x, int y,
			String texto, int adicionarColuna, int adicionarLinha) {
		return "T " + font + " " + tamanhoFonte + " " + (x + adicionarColuna)
				+ " " + (y + adicionarLinha) + " " + texto + "\n";
	}

	private static String dividirLinha(int fonte, int tamanhoFonte, int x,
			int y, String texto, int tamanhoLinha, int deslocarPorLinha) {
		String retorno = "";
		int contador = 0;
		int i;
		for (i = 0; i < texto.length(); i += tamanhoLinha) {
			contador += tamanhoLinha;
			if (contador > texto.length()) {
				retorno += "T " + fonte + " " + tamanhoFonte + " " + x + " "
						+ y + " " + texto.substring(i, texto.length()).trim()
						+ "\n";
			} else {
				retorno += "T " + fonte + " " + tamanhoFonte + " " + x + " "
						+ y + " " + texto.substring(i, contador).trim() + "\n";
			}
			y += deslocarPorLinha;
		}
		return retorno;
	}

	private Vector retornarDescricaoCategoriasQuantidadeEconomias(
			Imovel imovelEmitido) {
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
			imec = getControladorImovel().consultaSubcategorias(
					imovelEmitido.getId());

			for (ImovelSubcategoriaHelper imovelSubcategoriaHelper : imec) {
				String descricaoCategoria = imovelSubcategoriaHelper
						.getCategoria();
				if (descricaoCategoria.length() > 8) {
					descricaoCategoria = descricaoCategoria.substring(0, 8);
				}
				if (descricaoCategoria.toUpperCase().trim().equals(
						"RESIDENCIAL")) {
					quantidadeEconomiasResidencial += imovelSubcategoriaHelper
							.getQuantidadeEconomias();
					descricaoResidencial = descricaoCategoria;
				} else if (descricaoCategoria.toUpperCase().trim().equals(
						"COMERCIAL")) {
					quantidadeEconomiasComercial += imovelSubcategoriaHelper
							.getQuantidadeEconomias();
					descricaoComercial = descricaoCategoria;
				} else if (descricaoCategoria.toUpperCase().trim().equals(
						"INDUSTRIAL")) {
					quantidadeEconomiasIndustrial += imovelSubcategoriaHelper
							.getQuantidadeEconomias();
					descricaoIndustrial = descricaoCategoria;
				} else if (descricaoCategoria.toUpperCase().trim().equals(
						"PUBLICO")) {
					quantidadeEconomiasPublico += imovelSubcategoriaHelper
							.getQuantidadeEconomias();
					descricaoPublico = descricaoCategoria;
				}

			}

			if (quantidadeEconomiasResidencial > 0) {
				Object[] dadosResidencial = new Object[2];
				dadosResidencial[0] = descricaoResidencial;
				dadosResidencial[1] = new Integer(
						quantidadeEconomiasResidencial);
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

	private Vector gerarLinhasImpostosRetidosIS(
			EmitirContaHelper emitirContaHelper) {
		Vector retorno = new Vector();
		String[] dados = new String[3];

		Collection colecaoParmsImpostosDeduzidos = null;

		try {
			colecaoParmsImpostosDeduzidos = repositorioFaturamento
					.pesquisarParmsContaImpostosDeduzidos(emitirContaHelper
							.getIdConta());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
		}
		String dadosImposto = "";
		if (colecaoParmsImpostosDeduzidos != null
				&& !colecaoParmsImpostosDeduzidos.isEmpty()) {
			Iterator iteratorParmsImpostosDeduzidos = colecaoParmsImpostosDeduzidos
					.iterator();
			while (iteratorParmsImpostosDeduzidos.hasNext()) {
				Object[] parmsImpostoDeduzido = (Object[]) iteratorParmsImpostosDeduzidos
						.next();
				String descricaoTipoImposto = "";
				if (parmsImpostoDeduzido[0] != null) {
					descricaoTipoImposto = (String) parmsImpostoDeduzido[0];
				}
				String percentualAliquota = "";
				if (parmsImpostoDeduzido[1] != null) {
					percentualAliquota = Util
							.formatarMoedaReal((BigDecimal) parmsImpostoDeduzido[1]);
				}
				BigDecimal valorImpostos = null;
				if (parmsImpostoDeduzido[2] != null) {
					valorImpostos = (BigDecimal) parmsImpostoDeduzido[2];
				}

				String descricaoImposto = descricaoTipoImposto;
				dadosImposto += descricaoImposto + "-" + percentualAliquota
						+ "% ";
			}
			dados = new String[3];
			dados[0] = "DED. IMPOSTOS LEI FEDERAL N.9430 DE 27/12/1996";
			dados[2] = Util.formatarMoedaReal(emitirContaHelper
					.getValorImpostos());
			retorno.addElement(dados);
			dados = new String[3];
			dados[0] = dadosImposto;
			retorno.addElement(dados);
		}
		return retorno;
	}

	private Vector gerarLinhasCreditosRealizados(
			int indicadorDiscriminarDescricao,
			EmitirContaHelper emitirContaHelper) {
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
						dados = new String[3];
						dados[0] = creditoRealizado.getDescricao();
						dados[2] = Util.formatarMoedaReal(creditoRealizado
								.getValorCredito());
						retorno.addElement(dados);
					}

				} else {
					BigDecimal soma = emitirContaHelper.getValorCreditos();
					
					dados = new String[3];
					dados[0] = "CREDITOS";
					dados[2] = Util.formatarMoedaReal(soma);
					retorno.addElement(dados);
				}
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	private int retornaIndicadorDiscriminar(int quantidadeMaximaLinhas,
			int quantidadeLinhasAtual, char servicos,
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
			cnid = repositorioFaturamento
					.pesquisarParmsContaImpostosDeduzidos(conta.getId());

			switch (servicos) {
			case 'd':
				if (crrz != null) {
					linhasRestantesDescricao = linhasRestantesDescricao + 1;
				}
				if (cnid != null) {
					linhasRestantesDescricao = linhasRestantesDescricao + 2;
				}
				if (dbcb != null) {
					int limiteDescriminar = quantidadeMaximaLinhas
							- quantidadeLinhasAtual - linhasRestantesDescricao;
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
					int limiteDescriminar = quantidadeMaximaLinhas
							- quantidadeLinhasAtual - linhasRestantesDescricao;
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

	private Vector gerarLinhasTarifaAguaIS(ConsumoHistorico consumoAgua,
			SistemaParametro sistemaParametro, Imovel imovelEmitido,
			EmitirContaHelper emitirContaHelper) {
		String linhas = "";
		boolean tipoTarifaPorCategoria = false;
		
		if (sistemaParametro.getIndicadorTarifaCategoria() == SistemaParametro.INDICADOR_TARIFA_CATEGORIA){
			tipoTarifaPorCategoria = true;
		}
		int qtdLinhas = 0;
		
		Collection<ContaCategoria> cContaCategoria;
		try {
			cContaCategoria = repositorioFaturamento
			.pesquisarContaCategoria(emitirContaHelper
					.getIdConta());
		

		if (cContaCategoria != null) {
			for (ContaCategoria contaCategoria : cContaCategoria) {
				/**TODO: COSANPA
				 * Mantis 876 - Contas impressas sem a descrição da tarifa de água
				 * 
				 * @author Wellington Rocha
				 * @date 17/09/2013*/
				if (contaCategoria.getConsumoAgua() == null || (contaCategoria.getConsumoAgua()== 0 
						&& (contaCategoria.getValorAgua() == null || contaCategoria.getValorAgua().equals(new BigDecimal("0.00"))))) {
				    continue;
				}
				qtdLinhas++;
				
				if (linhas.equals("")) {
				    linhas += formarLinha(7, 0, 53, 733, "AGUA", 0, 0);
				}
				
				int quantidaEconomias = 0;
				
				quantidaEconomias = contaCategoria.getQuantidadeEconomia();
				
				String descricao = "";
				
				if (tipoTarifaPorCategoria) {
				    descricao = contaCategoria.getDescricao() + " " + quantidaEconomias + " " + "UNIDADE(S)";
				    if (descricao.length() > 40) {
					linhas += formarLinha(7, 0, 63, 733, descricao.substring(0, 40), 0, qtdLinhas * 34);
				    } else {
					linhas += formarLinha(7, 0, 63, 733, descricao, 0, qtdLinhas * 34);
				    }
				} else {
				    descricao = contaCategoria.getDescricao() + " " + quantidaEconomias + " " + "UNIDADE(S)";
				    if (descricao.length() > 40) {
					linhas += formarLinha(7, 0, 63, 733, descricao.substring(0, 40), 0, qtdLinhas * 34);
				    } else {
					linhas += formarLinha(7, 0, 63, 733, descricao, 0, qtdLinhas * 34);
				    }
				}
				
				int consumoMinimo = 0;
				if (consumoAgua != null && consumoAgua.getConsumoMinimo()!= null && contaCategoria.getConsumoMinimoAgua()!= null && consumoAgua.getConsumoMinimo()  > contaCategoria.getConsumoMinimoAgua()) {
				    consumoMinimo = consumoAgua.getConsumoMinimo();
				} else if (contaCategoria.getConsumoMinimoAgua()!= null) {
				    consumoMinimo = contaCategoria.getConsumoMinimoAgua();
				}
				
				if (consumoAgua == null && contaCategoria.getConsumoAgua() != null && contaCategoria.getConsumoAgua() <= consumoMinimo) {
				    qtdLinhas++;
				    descricao = "";
				    descricao = "TARIFA MINIMA " + Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua().divide(new BigDecimal(quantidaEconomias))) + " POR UNIDADE ";
				    linhas += formarLinha(7, 0, 63, 733, descricao, 0, qtdLinhas * 34);
				    descricao = consumoMinimo + " m3";
				    linhas += formarLinha(7, 0, 571, 733, descricao, 0, qtdLinhas * 34);
				    linhas += formarLinha(7, 0, 697, 733, Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua()), 0, qtdLinhas * 34);
				}else {
					
					Collection<ContaCategoriaConsumoFaixa> cContaCategoriaConsumoFaixa = repositorioFaturamento
					.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
							.getIdConta());

					if (contaCategoria.getConsumoAgua() != null &&  cContaCategoriaConsumoFaixa != null && cContaCategoriaConsumoFaixa.size() > 0) {
					qtdLinhas++;
					    descricao = "ATE " + ((int) contaCategoria.getConsumoMinimoAgua() / quantidaEconomias) + " m3 - " + Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua().divide(new BigDecimal(quantidaEconomias)))
						    + " POR UNIDADE";
					    linhas += formarLinha(7, 0, 73, 733, descricao, 0, qtdLinhas * 34);
					    linhas += formarLinha(7, 0, 571, 733, contaCategoria.getConsumoMinimoAgua() + " m3", 0, qtdLinhas * 34);
					    linhas += formarLinha(7, 0, 697, 733, Util.formatarMoedaReal(contaCategoria.getValorTarifaMinimaAgua()), 0, qtdLinhas * 34);
					    
					    for (ContaCategoriaConsumoFaixa faixa : cContaCategoriaConsumoFaixa) {
					    	qtdLinhas++;
					    	
					    	if (faixa.getConsumoFaixaFim() == 51) {
							    descricao = "ACIMA DE " + (faixa.getConsumoFaixaInicio() - 1) + " m3 - R$ " + Util.formatarMoedaReal(faixa.getValorTarifaFaixa()) + " POR m3";
							    linhas += formarLinha(7, 0, 73, 733, descricao, 0, qtdLinhas * 34);
							    linhas += formarLinha(7, 0, 571, 733, faixa.getConsumoAgua() * quantidaEconomias + " m3 ", 0, qtdLinhas * 34);
							    linhas += formarLinha(7, 0, 697, 733, Util.formatarMoedaReal(faixa.getValorAgua().multiply(new BigDecimal(quantidaEconomias))), 0, qtdLinhas * 34);
							}else {
							    descricao = faixa.getConsumoFaixaInicio() + " m3 A " + faixa.getConsumoFaixaFim() + " m3 - R$ " + Util.formatarMoedaReal(faixa.getValorTarifaFaixa()) + " POR M3 ";
							    linhas += formarLinha(7, 0, 73, 733, descricao, 0, qtdLinhas * 34);
							    linhas += formarLinha(7, 0, 571, 733, faixa.getConsumoAgua() * quantidaEconomias + " m3 ", 0, qtdLinhas * 34);
							    linhas += formarLinha(7, 0, 697, 733, Util.formatarMoedaReal(faixa.getValorAgua().multiply(new BigDecimal(quantidaEconomias))), 0, qtdLinhas * 34);
							}
						}
				    } else {
					if (contaCategoria.getConsumoAgua() != null) {
					    qtdLinhas++;
					    descricao = "CONSUMO DE AGUA";
					    linhas += formarLinha(7, 0, 73, 733, descricao, 0, qtdLinhas * 34);
					    linhas += formarLinha(7, 0, 571, 733, ((int) contaCategoria.getConsumoAgua()) + " m3", 0, qtdLinhas * 34);
					    linhas += formarLinha(7, 0, 697, 733, Util.formatarMoedaReal(contaCategoria.getValorAgua()), 0, qtdLinhas * 34);
					}
				    }
				}
				
				
				
			}
		   
		}
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector retornar = new Vector();
		retornar.addElement(linhas);
		retornar.addElement(new Integer(qtdLinhas));
		return retornar;
		 
		
	}

	private Vector gerarLinhasDebitosCobrados(
			int indicadorDiscriminarDescricao,
			EmitirContaHelper emitirContaHelper) {
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
						dados[0] = dados[0]+" "+debitoCobrado.getNumeroPrestacaoDebito()+"/"+debitoCobrado.getNumeroPrestacao();
						dados[2] = Util.formatarMoedaReal(debitoCobrado
								.getValorPrestacao());
						retorno.addElement(dados);
					}
				} else {
					BigDecimal soma = emitirContaHelper.getDebitos();

					dados = new String[3];
					dados[0] = "DEBITOS";
					dados[2] = Util.formatarMoedaReal(soma);
					/**TODO: COSANPA
					 * Mantis 756 - Contas de rotas do interior que não possuiam a descrição dos débitos
					 * 
					 * @author Wellington Rocha
					 * @date 11/03/2013
					 */
					retorno.addElement(dados);
				}
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return retorno;
	}

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
		if (consumAgua == consumEsgoto
				&& valorAgua.compareTo(new BigDecimal("0.00")) != 0) {
			if (valorEsgoto.compareTo(new BigDecimal("0.00")) != 0) {
				dados[0] = "ESGOTO ";
				dados[0] += Util.formatarMoedaReal(emitirContaHelper
						.getPercentualEsgotoConta());
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

	public String gerarArquivoFormatadoImpressaoTermica(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro,
			String[] qualidadeAgua,
			int contador) {

		StringBuilder retorno = new StringBuilder("");
		Imovel imovelEmitido;
		try {
			imovelEmitido = getControladorImovel().pesquisarImovel(
					emitirContaHelper.getIdImovel());

			LigacaoTipo lta = new LigacaoTipo();
			lta.setId(LigacaoTipo.LIGACAO_AGUA);
			LigacaoTipo lte = new LigacaoTipo();
			lte.setId(LigacaoTipo.LIGACAO_ESGOTO);

			MedicaoHistorico medicaoHistoricoAgua = getControladorMicromedicao()
					.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(imovelEmitido.getId(),
							emitirContaHelper.getAmReferencia());
			MedicaoHistorico medicaoHistoricoAguaMesAnterior = getControladorMicromedicao()
					.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(imovelEmitido.getId(), Util
							.subtrairMesDoAnoMes(emitirContaHelper
									.getAmReferencia(), 1));
			MedicaoHistorico medicaoHistoricoPocoMesAnterior = getControladorMicromedicao()
			.pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(imovelEmitido.getId(), Util
					.subtrairMesDoAnoMes(emitirContaHelper
							.getAmReferencia(), 1));
			MedicaoHistorico medicaoHistoricoPoco = getControladorMicromedicao()
					.pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(imovelEmitido.getId(),
							emitirContaHelper.getAmReferencia());
			
			Collection colecaoClienteImovel;
			try {
				colecaoClienteImovel = repositorioClienteImovel
				.pesquisarClienteImovelResponsavelConta(emitirContaHelper
						.getIdImovel());
			

	String endereco = "";
	String municipioEntrega = "";
	String bairroEntrega = "";
	String cepEntrega = "";
	String ufEntrega = "";
	String logCepClie = "";
	String logBairroClie = "";
	String cpfCnpjFormatado = "";

	if (colecaoClienteImovel != null
			&& !colecaoClienteImovel.isEmpty()) {
		ClienteImovel clienteImovelRespConta = (ClienteImovel) colecaoClienteImovel
				.iterator().next();

		if (clienteImovelRespConta != null) {
			Cliente cliente = clienteImovelRespConta
					.getCliente();
			
			if (cliente != null){
			if(cliente.getCnpjFormatado()!= null){
				cpfCnpjFormatado = cliente.getCnpjFormatado();
			}else if(cliente.getCpfFormatado() != null){
				cpfCnpjFormatado = cliente.getCpfFormatado();
			}
			}

			if (cliente != null
					&& imovelEmitido.getImovelContaEnvio().getId()
					.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)) {
				
				String[] enderecoCliente = getControladorEndereco()
						.pesquisarEnderecoClienteAbreviadoDivididoCosanpa(
								cliente.getId());
				bairroEntrega = enderecoCliente[3];
				municipioEntrega = enderecoCliente[1];
				ufEntrega = enderecoCliente[2];
				cepEntrega = enderecoCliente[4];
				logCepClie = enderecoCliente[5];
				logBairroClie = enderecoCliente[6];
				
				endereco = enderecoCliente[0]
						+ " - " + bairroEntrega
						+ " "
						+ municipioEntrega
						+ " " + ufEntrega + " "
						+ cepEntrega;
				
			}
		}
	}

	String[] enderecoImovel2 = getControladorEndereco()
			.pesquisarEnderecoFormatadoDivididoCosanpa(
					emitirContaHelper
							.getIdImovel());

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

	if (logBairroClie.trim().equalsIgnoreCase(
			"")) {
		logBairroClie = logBairroImovel;
	}

	if (endereco == null
			|| endereco.trim()
					.equalsIgnoreCase("")
			|| endereco.trim()
					.equalsIgnoreCase("-")) {
		endereco = getControladorEndereco()
				.pesquisarEnderecoFormatado(
						imovelEmitido.getId());
	}

			try {
				retorno.append("! 0 816 0 1720 1\n");
				retorno.append("BOX 32 435 802 482 1\n"
						+ "LINE 720 415 720 455 1\n"
						+ "LINE 403 415 403 477 1\n"
						+ "BOX 32 411 802 435 1\n" + "LINE 278 415 278 477 1\n"
						+ "BOX 283 518 802 545 1\n"
						+ "BOX 283 545 802 692 1\n" + "LINE 656 518 656 692 1\n"
						+ "LINE 425 518 425 692 1\n"
						+ "LINE 535 518 535 692 1\n"
						);

				retorno.append("T 0 2 138 121 " + "Data de Emissao: "
						+ Util.formatarDataComHora(new Date()) + "\n");

				retorno.append("T 7 1 464 90 "
						+ emitirContaHelper.getIdImovel() + "\n");

				retorno.append("T 7 1 669 90 "
						+ Util.retornaDescricaoAnoMesCompleto(emitirContaHelper
								.getAmReferencia()
								+ "") + "\n");

				retorno.append("T 0 0 201 47 "
						+ Util.formatarCnpj(sistemaParametro.getCnpjEmpresa()
								.trim()) + "\n");

				retorno
						.append("T 0 0 285 64 "
								+ sistemaParametro.getInscricaoEstadual()
										.trim() + "\n");

				retorno.append(formarLinha(0, 0, 222, 81, emitirContaHelper
						.getIdFaturamentoGrupo()
						+ "", 0, 0));

				/*retorno.append(formarLinha(0, 0, 140, 108, (imovelEmitido
						.getLocalidade().getEnderecoFormatado() != null
						&& !imovelEmitido.getLocalidade()
								.getEnderecoFormatadoTituloAbreviado().equals(
										"") ? imovelEmitido.getLocalidade()
						.getEnderecoFormatado()
						+ " - " : "")
						+ (imovelEmitido.getLocalidade().getFone() != null
								&& !imovelEmitido.getLocalidade().getFone()
										.equals("") ? imovelEmitido
								.getLocalidade().getFone().trim() : ""), 0, 0));*/


				
					retorno
							.append(formarLinha(0, 2, 52, 172,
									emitirContaHelper.getNomeCliente().trim(),
									0, 0)
									+ formarLinha(0, 2, 52, 199,
											cpfCnpjFormatado, 0, 0)
									+ dividirLinha(
											0,
											2,
											434,
											169,
											endereco.trim(), 40, 27));
				
				// Inscricao
				retorno.append(formarLinha(7, 0, 15, 250, imovelEmitido.getInscricaoFormatada(), 0, 0));

				// Codigo da Rota
				retorno.append(formarLinha(7, 0, 315, 250, imovelEmitido.getQuadra().getRota().getCodigo()
						+ "", 0, 0));
				// Sequencial da Rota
				retorno.append(formarLinha(7, 0, 415, 250, imovelEmitido.getNumeroSequencialRota()
						+ "", 0, 0));
				Vector quantidadeEconomias = retornarDescricaoCategoriasQuantidadeEconomias(imovelEmitido);
				for (int i = 0; i < quantidadeEconomias.size(); i++) {
					Object[] dadosCategoria = (Object[]) quantidadeEconomias
							.elementAt(i);
					retorno.append(formarLinha(0, 0, 470, 254,
							dadosCategoria[0] + "", i * 85, 0));
					retorno.append(formarLinha(7, 0, 539, 250,
							dadosCategoria[1] + "", i * 85, 0));
				}
				String hidrometro = "NAO MEDIDO";
				String dataInstalacao = "";
				String situacaoAgua = emitirContaHelper
						.getDescricaoLigacaoAguaSituacao();
				String situacaoEsgoto = emitirContaHelper
						.getDescricaoLigacaoEsgotoSituacao();
				;
				String leituraAnteriorInformada = "";
				String leituraAtualInformada = "";
				String leituraAnteriorFaturada = "";
				String leituraAtualFaturada = "";
				String consumo = "";
				String diasConsumo = "";
				ConsumoHistorico consumoAgua = getControladorMicromedicao()
						.obterConsumoHistorico(imovelEmitido, lta,
								emitirContaHelper.getAmReferencia());
				ConsumoHistorico consumoEsgoto = getControladorMicromedicao()
						.obterConsumoHistorico(imovelEmitido, lte,
								emitirContaHelper.getAmReferencia());
				
				String dataLeituraAnteriorInformada = "";
				String dataLeituraAtualInformada = "";
				String dataLeituraAnteriorFaturada = "";
				String dataLeituraAtualFaturada = "";
				String media = "0";
				int tipoConsumo = 0;

				if (imovelEmitido.getLigacaoAgua()!=null){
				if(imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico()!= null){
					dataInstalacao = Util.formatarData(imovelEmitido
							.getLigacaoAgua().getHidrometroInstalacaoHistorico().getDataInstalacao());
					hidrometro = imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero();
				}
				}
				if (medicaoHistoricoAgua != null) {

					if (medicaoHistoricoAgua.getLeituraAnteriorInformada() != null) {
						leituraAnteriorInformada = medicaoHistoricoAgua
								.getLeituraAnteriorInformada()
								+ "";
					}
					if (medicaoHistoricoAgua.getLeituraAnteriorFaturamento()!= 0){
						leituraAnteriorFaturada = medicaoHistoricoAgua.getLeituraAnteriorFaturamento()+"";
					}
					if(medicaoHistoricoAgua.getLeituraAtualFaturamento() != 0){
						leituraAtualFaturada = medicaoHistoricoAgua.getLeituraAtualFaturamento()+"";
					}
					if(medicaoHistoricoAgua.getLeituraAtualInformada() != null){
						leituraAtualInformada = medicaoHistoricoAgua.getLeituraAtualInformada()+"";
					}
					
					if (medicaoHistoricoAgua
									.getDataLeituraAnteriorFaturamento() != null){
						dataLeituraAnteriorFaturada = Util
						.formatarData(medicaoHistoricoAgua
								.getDataLeituraAnteriorFaturamento());
					}
					if(medicaoHistoricoAgua
							.getDataLeituraAtualInformada() != null){
						dataLeituraAtualInformada = Util.formatarData(medicaoHistoricoAgua
								.getDataLeituraAtualInformada());
					}
					
					if (medicaoHistoricoAgua.getDataLeituraAtualFaturamento() != null){
						dataLeituraAtualFaturada = Util.formatarData(medicaoHistoricoAgua.getDataLeituraAtualFaturamento());
					}
					
					if (medicaoHistoricoAguaMesAnterior != null){
						if(medicaoHistoricoAguaMesAnterior.getDataLeituraAtualInformada()!= null){
							dataLeituraAnteriorInformada = Util.formatarData(medicaoHistoricoAguaMesAnterior.getDataLeituraAtualInformada());
						}
					}
					
					

					if (consumoAgua != null) {
						media = String.valueOf(consumoAgua.getConsumoMedio());
						tipoConsumo = consumoAgua.getConsumoTipo().getId();
						if (medicaoHistoricoAgua.getLeituraAtualFaturamento() != 0) {

							consumo = consumoAgua.getNumeroConsumoFaturadoMes()
									+ "";
							diasConsumo = Util
									.obterQuantidadeDiasEntreDuasDatas(medicaoHistoricoAgua
											.getDataLeituraAnteriorFaturamento(),
											medicaoHistoricoAgua
													.getDataLeituraAtualFaturamento()
											)
									+ "";
							leituraAtualFaturada = medicaoHistoricoAgua
									.getLeituraAtualFaturamento()
									+ "";
						} else {

							consumo = consumoAgua.getNumeroConsumoFaturadoMes()
									+ "";
							diasConsumo = Util
									.obterQuantidadeDiasEntreDuasDatas(
											medicaoHistoricoAgua
													.getDataLeituraAtualFaturamento(),
											medicaoHistoricoAgua
													.getDataLeituraAnteriorFaturamento())
									+ "";
						}
					} 

				} else if (medicaoHistoricoPoco != null) {

					if (medicaoHistoricoPoco.getLeituraAnteriorInformada() != null) {
						leituraAnteriorInformada = medicaoHistoricoPoco
								.getLeituraAnteriorInformada()
								+ "";
					}
					if (medicaoHistoricoPoco.getLeituraAnteriorFaturamento()!= 0){
						leituraAnteriorFaturada = medicaoHistoricoPoco.getLeituraAnteriorFaturamento()+"";
					}
					if (medicaoHistoricoPoco.getLeituraAtualFaturamento()!= 0){
						leituraAtualFaturada = medicaoHistoricoPoco.getLeituraAtualFaturamento()+"";
					}
					if(medicaoHistoricoPoco.getLeituraAtualInformada()!= null){
						leituraAtualInformada = medicaoHistoricoPoco.getLeituraAtualInformada()+"";
					}
					
					if(medicaoHistoricoPoco
									.getDataLeituraAnteriorFaturamento()!=null){
						dataLeituraAnteriorFaturada = Util
						.formatarData(medicaoHistoricoPoco
								.getDataLeituraAnteriorFaturamento());
					}
					
					if(medicaoHistoricoPoco
							.getDataLeituraAtualInformada()!= null){
						dataLeituraAtualInformada = Util.formatarData(medicaoHistoricoPoco
								.getDataLeituraAtualInformada());
					}
					
					if(medicaoHistoricoPoco.getDataLeituraAtualFaturamento()!= null){
						dataLeituraAtualFaturada = Util.formatarData(medicaoHistoricoPoco.getDataLeituraAtualFaturamento());
					}
					
					if(medicaoHistoricoPocoMesAnterior.getDataLeituraAtualInformada() != null){
						dataLeituraAnteriorInformada = Util.formatarData(medicaoHistoricoPocoMesAnterior.getDataLeituraAtualInformada());
					}
					
					

					if (consumoEsgoto != null) {
						media = String.valueOf(consumoEsgoto
								.getConsumoMedio());
						tipoConsumo = consumoEsgoto.getConsumoTipo().getId();

						if (medicaoHistoricoPoco.getLeituraAtualFaturamento() != 0) {

							consumo = consumoEsgoto
									.getNumeroConsumoFaturadoMes()
									+ "";
							diasConsumo = Util
									.obterQuantidadeDiasEntreDuasDatas(
											medicaoHistoricoPoco
													.getDataLeituraAtualFaturamento(),medicaoHistoricoPoco
													.getDataLeituraAnteriorFaturamento()
											)
									+ "";
							

						} else {
							consumo = consumoEsgoto
									.getNumeroConsumoFaturadoMes()
									+ "";
							diasConsumo = Util
									.obterQuantidadeDiasEntreDuasDatas(
											medicaoHistoricoPoco
													.getDataLeituraAtualFaturamento(),
											medicaoHistoricoPoco
													.getDataLeituraAnteriorFaturamento())
									+ "";							
						}

					}

				}

				else if (medicaoHistoricoAgua == null
						&& medicaoHistoricoPoco == null) {

					if (consumoAgua != null) {


						consumo = consumoAgua.getNumeroConsumoFaturadoMes()
								+ "";
						int mes = Util.obterMes(emitirContaHelper.getAmReferencia());
						int ano = Util.obterAno(emitirContaHelper.getAmReferencia());
						diasConsumo = Util.obterUltimoDiaMes(mes, ano)
								+ "";
					}
				}

				// Hidrometro
				retorno.append(formarLinha(7, 0, 48, 301, hidrometro, 0, 0));

				// Data da InstalaÃ§Ã£o
				retorno
						.append(formarLinha(7, 0, 248, 301, dataInstalacao, 0,
								0));
				// Situacao da ligacao de Agua

				if (situacaoAgua.length() > 13) {
					retorno.append(formarLinha(7, 0, 446, 301, situacaoAgua
							.substring(0, 13), 0, 0));
				} else {
					retorno.append(formarLinha(7, 0, 446, 301, situacaoAgua, 0,
							0));
				}
				// Situacao da ligacao de esgoto
				retorno
						.append(formarLinha(7, 0, 627, 301, situacaoEsgoto, 0,
								0));

				// Leitura Anterior
				retorno.append(formarLinha(7, 0, 188, 330, "LEITURA", 0, 0) + formarLinha(7, 0, 190, 354, leituraAnteriorInformada, 0, 0) + formarLinha(7, 0, 190, 378, leituraAtualInformada, 0, 0));
				retorno.append(formarLinha(7, 0, 320, 330, "DATA", 0, 0) + formarLinha(7, 0, 298, 354, dataLeituraAnteriorInformada, 0, 0) + formarLinha(7, 0, 298, 378, dataLeituraAtualInformada, 0, 0));

				// Leitura Atual
				retorno.append(formarLinha(7, 0, 37, 354, "ANTERIOR", 0, 0) + formarLinha(7, 0, 37, 378, "ATUAL", 0, 0));

				if (medicaoHistoricoAgua != null){
					if (medicaoHistoricoAgua.getLeituraAnormalidadeFaturamento() != null){
							retorno.append(formarLinha(0, 2, 460, 374, "ANORM. LEITURA: " + medicaoHistoricoAgua.getLeituraAnormalidadeFaturamento().getDescricao(), 0, 0));
					}						
				}
				
				
				 // Leitura Anterior
			    retorno.append(formarLinha(7, 0, 163, 412, "FATURADO", 0, 0) + formarLinha(7, 0, 190, 436, leituraAnteriorFaturada, 0, 0) + formarLinha(7, 0, 190, 460, leituraAtualFaturada, 0, 0));
			    // Leitura Atual
			    retorno.append(formarLinha(7, 0, 313, 412, "DATA", 0, 0) + formarLinha(7, 0, 285, 436, dataLeituraAnteriorFaturada, 0, 0) + formarLinha(7, 0, 285, 460, dataLeituraAtualFaturada, 0, 0));

				// Consumo
				if ((imovelEmitido.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.LIGADO || imovelEmitido
						.getLigacaoEsgotoSituacao().getId() == LigacaoEsgotoSituacao.LIGADO)
						&& imovelEmitido.getImovelCondominio() != null) {

					if (imovelEmitido.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.LIGADO) {
						consumo += "/" + consumoAgua.getConsumoRateio();
					} else if (imovelEmitido.getLigacaoEsgotoSituacao().getId() == LigacaoEsgotoSituacao.LIGADO) {
						consumo += "/" + consumoEsgoto.getConsumoRateio();
					}

					retorno.append(formarLinha(7, 0, 414, 412,
							"CONSUMO/RATEIO (m3)", 0, 0)
							+ formarLinha(7, 0, 511, 436, consumo, 0, 0));
				} else if(consumoAgua != null){
					ConsumoTipo consumoTipoAgua = getControladorMicromedicao().consultarDadosConsumoTipoConsumoHistorico(consumoAgua);
					if (consumoTipoAgua != null){
						if(consumoTipoAgua.getId()!= null){
							retorno.append(formarLinha(7, 0, 412, 412, getTipoConsumoToPrint(consumoTipoAgua.getId()), 0, 0));
						}
					}
					retorno.append(formarLinha(7, 0, 511, 436, consumo, 0, 0));
				}else if (consumoEsgoto != null){
					ConsumoTipo consumoTipoEsgoto = getControladorMicromedicao().consultarDadosConsumoTipoConsumoHistorico(consumoEsgoto);
					if (consumoTipoEsgoto != null){
						if(consumoTipoEsgoto.getDescricao()!= null){
							retorno.append(formarLinha(7, 0, 412, 412, consumoTipoEsgoto.getDescricao(), 0, 0));
						}
					}
					retorno.append(formarLinha(7, 0, 511, 436, consumo, 0, 0));
				}
				// Numero de dias
				retorno.append(formarLinha(7, 0, 745, 412, "DIAS", 0, 0)
						+ formarLinha(7, 0, 760, 436, diasConsumo, 0, 0));
				String anormalidadeConsumo = null;
				if (consumoAgua != null){
					if(consumoAgua.getConsumoAnormalidade()!= null){
						if (consumoAgua.getConsumoAnormalidade().getDescricao()!= null){
							anormalidadeConsumo = consumoAgua.getConsumoAnormalidade().getDescricao();
						}
					}					
					if( anormalidadeConsumo != null){
						retorno.append(formarLinha(0, 2, 460, 460, "ANORM. CONSUMO: " + anormalidadeConsumo, 0, 0));
					}
				}
				
				retorno.append(formarLinha(7, 0, 37, 436, "ANTERIOR", 0, 0) + formarLinha(7, 0, 37, 460, "ATUAL", 0, 0));
				
				
				retorno
						.append(formarLinha(7, 0, 50, 499, "ULTIMOS CONSUMOS",
								0, 0));
				retorno.append("LINE 115 525 115 665 1\n");
								
				// Ultimos Consumos

				int flag = 0;
				int soma = 0;
				int mesesDeConsumo = 0;
				for (int i = 0; i < 6; i++) {
					
					Object[] consumoAnterior = obterConsumoAnteriorIS(
							imovelEmitido.getId(), emitirContaHelper
									.getAmReferencia(), i + 1, 1, 1);
					MedicaoHistorico medicaoAnterior = getControladorMicromedicao()
					.pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(imovelEmitido.getId(), Util
							.subtrairMesDoAnoMes(emitirContaHelper
									.getAmReferencia(), i+1));
					retorno.append(formarLinha(0, 2, 44, 520,
							Util.formatarAnoMesParaMesAno(Util
									.subtrairMesDoAnoMes(emitirContaHelper
											.getAmReferencia(), i + 1))
									+ "", 0, i * 25));
					
					String anormalidade = "";
					String consumoMesAnterior = "";
					
					if (medicaoAnterior != null){
						if (medicaoAnterior.getLeituraAnormalidadeFaturamento()!= null){
								anormalidade = "A. Leit.:" + medicaoAnterior.getLeituraAnormalidadeFaturamento().getId() + "";
								flag++;
						}
					}
						
					if (consumoAnterior != null) {
						if(consumoAnterior[0]!= null){
							if(medicaoHistoricoAgua == null && medicaoHistoricoPoco == null){
								soma = soma + Integer.parseInt(consumoAnterior[0]+"");
								mesesDeConsumo++;
							}
							consumoMesAnterior = consumoAnterior[0]+ " m3 ";
						}
						 
						if(consumoAnterior[1]!= null && anormalidade.equals("")){
						anormalidade = "A. Cons.:" + consumoAnterior[2] + "";
						}
						
						flag++;
					}
					retorno.append(formarLinha(0, 2, 127, 520,
							consumoMesAnterior + anormalidade, 0,
							i * 25));
				}
				if (flag == 0){
					retorno.append(formarLinha(7, 0, 50, 499, "HISTORICO DE CONSUMO", 0, 0) + formarLinha(7, 0, 50, 520, "INEXISTENTE", 0, 0));
				}
				if(medicaoHistoricoAgua == null && medicaoHistoricoPoco == null && mesesDeConsumo != 0){
					media = (soma/mesesDeConsumo)+"";
				}
				//String[] qualidadeAgua = getControladorFaturamento().obterDadosQualidadeAguaCosanpa(imovelEmitido.getLocalidade().getId(), imovelEmitido.getSetorComercial().getId(), new Integer(emitirContaHelper.getAmReferencia()), imovelEmitido.getQuadraFace().getId());

				retorno.append(formarLinha(7, 0, 448, 496, "QUALIDADE DA AGUA", 0, 0));
				retorno.append(formarLinha(0, 0, 672, 505, "Ref: ", 0, 0) + formarLinha(0, 0, 705, 505, Util.retornaDescricaoAnoMesCompleto(emitirContaHelper.getAmReferencia()+""), 0, 0));
				
				retorno.append(formarLinha(7, 0, 287, 520, "PARAMETROS", 0, 0) + formarLinha(7, 0, 439, 520, "Port. 518", 0, 0) + formarLinha(7, 0, 540, 520, "ANALISADO", 0, 0) + formarLinha(7, 0, 672, 520, "CONFORME", 0, 0));
			    
				// Daniel - Parametro
					    retorno.append(formarLinha(0, 0, 287, 552, "COR(uH)", 0, 0));
					    retorno.append(formarLinha(0, 0, 287, 571, "TURBIDEZ(UT)", 0, 0));
					    retorno.append(formarLinha(0, 0, 287, 590, "CLORO(mg/L)", 0, 0));
					    retorno.append(formarLinha(0, 0, 287, 609, "FLUOR(mg/L)", 0, 0));
					    retorno.append(formarLinha(0, 0, 287, 628, "COLIFORME TOTAL", 0, 0) + formarLinha(0, 0, 287, 640, "Pres/Aus)", 0, 0));
					    retorno.append(formarLinha(0, 0, 287, 657, "COLIFORME TERMO", 0, 0) + formarLinha(0, 0, 287, 671, "TOLER.(Pres/Aus)", 0, 0));
				// Qualidade da agua
					 // Daniel - Exigido !=Alterar
					    retorno.append(formarLinha(0, 0, 440, 552, qualidadeAgua[6], 0, 0));
					    retorno.append(formarLinha(0, 0, 440, 571, qualidadeAgua[7], 0, 0));
					    retorno.append(formarLinha(0, 0, 440, 590, qualidadeAgua[9], 0, 0));
					    retorno.append(formarLinha(0, 0, 440, 609, qualidadeAgua[8], 0, 0));
					    retorno.append(formarLinha(0, 0, 440, 628, qualidadeAgua[10], 0, 0));
					    retorno.append(formarLinha(0, 0, 440, 657, qualidadeAgua[11], 0, 0));
					    
				// Daniel - Padrão
//					    retorno += formarLinha(0, 0, 556, 546, qualidadeAgua[0], 0, 0);
//					    retorno += formarLinha(0, 0, 556, 565, qualidadeAgua[1], 0, 0);
//					    retorno += formarLinha(0, 0, 556, 584, qualidadeAgua[3], 0, 0);
//					    retorno += formarLinha(0, 0, 556, 603, qualidadeAgua[2], 0, 0);
//					    retorno += formarLinha(0, 0, 556, 622, qualidadeAgua[4], 0, 0);
//					    retorno += formarLinha(0, 0, 556, 653, qualidadeAgua[5], 0, 0);
					    
				// Daniel - Analisado
					    retorno.append(formarLinha(0, 0, 582, 552, qualidadeAgua[12], 0, 0));
					    retorno.append(formarLinha(0, 0, 582, 571, qualidadeAgua[13], 0, 0));
					    retorno.append(formarLinha(0, 0, 582, 590, qualidadeAgua[15], 0, 0));
					    retorno.append(formarLinha(0, 0, 582, 609, qualidadeAgua[14], 0, 0));
					    retorno.append(formarLinha(0, 0, 582, 628, qualidadeAgua[16], 0, 0));
					    retorno.append(formarLinha(0, 0, 582, 657, qualidadeAgua[17], 0, 0));
					    
				// daniel - Conforme
					    retorno.append(formarLinha(0, 0, 726, 552, qualidadeAgua[18], 0, 0));
					    retorno.append(formarLinha(0, 0, 726, 571, qualidadeAgua[19], 0, 0));
					    retorno.append(formarLinha(0, 0, 726, 590, qualidadeAgua[21], 0, 0));
					    retorno.append(formarLinha(0, 0, 726, 609, qualidadeAgua[20], 0, 0));
					    retorno.append(formarLinha(0, 0, 726, 628, qualidadeAgua[22], 0, 0));
					    retorno.append(formarLinha(0, 0, 726, 657, qualidadeAgua[23], 0, 0));
				
					    retorno.append(formarLinha(7, 0, 75, 672, "MEDIA(m3):", 0, 0) + formarLinha(7, 0, 195, 672, media, 0, 0));
					    retorno.append(formarLinha(7, 0, 53, 708, "DESCRICAO", 0, 0) + formarLinha(7, 0, 571, 708, "CONSUMO", 0, 0) + formarLinha(7, 0, 687, 708, "TOTAL(R$)", 0, 0));

				int ultimaLinhaAgua = 0;
				int ultimaLinhaPoco = 0;
				int quantidadeLinhasAtual = 0;
				int quantidadeMaximaLinhas = 18;

				Vector linhaAgua = this.gerarLinhasTarifaAguaIS(consumoAgua,
						sistemaParametro, imovelEmitido, emitirContaHelper);
				retorno.append(linhaAgua.elementAt(0));
				ultimaLinhaAgua = (((Integer) linhaAgua.elementAt(1))
						.intValue());
				if (ultimaLinhaAgua != 0) {
					quantidadeLinhasAtual = quantidadeLinhasAtual
							+ ultimaLinhaAgua + 1;
				}
				ultimaLinhaAgua *= 34;
				Vector tarifasPoco = this
						.gerarLinhasTarifaPocoIS(emitirContaHelper);
				ultimaLinhaPoco = ultimaLinhaAgua;
				for (int i = 0; i < tarifasPoco.size(); i++) {
					String[] tarifaPoco = (String[]) tarifasPoco.elementAt(i);
					ultimaLinhaPoco = ultimaLinhaAgua + ((i + 1) * 34);
					quantidadeLinhasAtual++;
					int deslocaDireitaColuna;
					if (i == 0 || i == 1 || i == 2) {
						deslocaDireitaColuna = i;
					} else {
						deslocaDireitaColuna = 2;
					}
					if (tarifaPoco[0] != null) {
						retorno.append(formarLinha(7, 0, 53, 733,
								tarifaPoco[0], deslocaDireitaColuna * 10,
								(i + 1) * 34 + ultimaLinhaAgua));
					}
					if (tarifaPoco[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 733,
								tarifaPoco[1], 0, (i + 1) * 34
										+ ultimaLinhaAgua));
					}
					if (tarifaPoco[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 733,
								tarifaPoco[2], 0, (i + 1) * 34
										+ ultimaLinhaAgua));
					}
				}
				int indicadorDiscriminarDescricao = retornaIndicadorDiscriminar(
						quantidadeMaximaLinhas, quantidadeLinhasAtual, 'd',
						emitirContaHelper);
				Vector debitos = this.gerarLinhasDebitosCobrados(
						indicadorDiscriminarDescricao, emitirContaHelper);
				int ultimaLinhaDebito = ultimaLinhaPoco;
				for (int i = 0; i < debitos.size(); i++) {
					String[] debito = (String[]) debitos.elementAt(i);
					ultimaLinhaDebito = ultimaLinhaPoco + ((i + 1) * 34);
					quantidadeLinhasAtual++;
					if (debito[0] != null) {
						retorno.append(formarLinha(7, 0, 53, 733, debito[0], 0,
								(i + 1) * 34 + ultimaLinhaPoco));
					}
					if (debito[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 733, debito[1],
								0, (i + 1) * 34 + ultimaLinhaPoco));
					}
					if (debito[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 733, debito[2],
								0, (i + 1) * 34 + ultimaLinhaPoco));
					}
				}

				indicadorDiscriminarDescricao = retornaIndicadorDiscriminar(
						quantidadeMaximaLinhas, quantidadeLinhasAtual, 'c',
						emitirContaHelper);
				Vector creditos = this.gerarLinhasCreditosRealizados(
						indicadorDiscriminarDescricao, emitirContaHelper);
				int ultimaLinhaCredito = ultimaLinhaDebito;
				for (int i = 0; i < creditos.size(); i++) {
					String[] credito = (String[]) creditos.elementAt(i);
					ultimaLinhaCredito = ultimaLinhaDebito + ((i + 1) * 34);
					if (credito[0] != null) {
						retorno.append(formarLinha(7, 0, 53, 733, credito[0],
								0, (i + 1) * 34 + ultimaLinhaDebito));
					}
					if (credito[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 733, credito[1],
								0, (i + 1) * 34 + ultimaLinhaDebito));
					}
					if (credito[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 733, credito[2],
								0, (i + 1) * 34 + ultimaLinhaDebito));
					}
				}
				Vector impostos = this
						.gerarLinhasImpostosRetidosIS(emitirContaHelper);
				for (int i = 0; i < impostos.size(); i++) {
					String[] imposto = (String[]) impostos.elementAt(i);
					int deslocaDireitaColuna;
					if (i == 0 || i == 1) {
						deslocaDireitaColuna = i;
					} else {
						deslocaDireitaColuna = 1;
					}
					if (imposto[0] != null) {
						retorno.append(formarLinha(7, 0, 53, 733, imposto[0],
								deslocaDireitaColuna * 10, (i + 1) * 34
										+ ultimaLinhaCredito));
					}
					if (imposto[1] != null) {
						retorno.append(formarLinha(7, 0, 571, 733, imposto[1],
								0, (i + 1) * 34 + ultimaLinhaCredito));
					}
					if (imposto[2] != null) {
						retorno.append(formarLinha(7, 0, 697, 733, imposto[2],
								0, (i + 1) * 34 + ultimaLinhaCredito));
					}
				}
				retorno.append(formarLinha(7, 1, 160, 1210, Util
								.formatarData(emitirContaHelper
										.getDataVencimentoConta()), 0, 0));
				BigDecimal valorTotalConta = emitirContaHelper.getValorConta();
				retorno.append(formarLinha(4, 0, 640, 1210, Util
						.formatarMoedaReal(valorTotalConta), 0, 0));
				retorno
						.append(formarLinha(0, 2, 424, 1265,
								"OPCAO PELO DEB. AUTOMATICO: ", 0, 0)
								+ formarLinha(5, 0, 649, 1266, (imovelEmitido
										.getIndicadorDebitoConta() == null ? ""
										: imovelEmitido
												.getId()
												+ ""), 0, 0));
				/*if (emitirContaHelper.getMensagemConsumoString() != null
						&& !emitirContaHelper.getMensagemConsumoString()
								.equals("")) {
					retorno
							.append(formarLinha(
									0,
									3,
									35,
									1300,
									emitirContaHelper
											.getMensagemConsumoString() != null
											&& emitirContaHelper
													.getMensagemConsumoString()
													.length() > 45 ? emitirContaHelper
											.getMensagemConsumoString()
											.substring(0, 45)
											: emitirContaHelper
													.getMensagemConsumoString(),
									0, 0)
									);
				} else {
					retorno.append(formarLinha(0, 3, 35, 1300,
							emitirContaHelper.getMsgLinha1Conta() != null
									&& emitirContaHelper.getMsgLinha1Conta()
											.length() > 45 ? emitirContaHelper
									.getMsgLinha1Conta().substring(0, 45)
									: emitirContaHelper.getMsgLinha1Conta(), 0,
							0)
							+ formarLinha(0, 3, 35, 1330, emitirContaHelper
									.getMsgLinha2Conta() != null
									&& emitirContaHelper.getMsgLinha2Conta()
											.length() > 45 ? emitirContaHelper
									.getMsgLinha2Conta().substring(0, 45)
									: emitirContaHelper.getMsgLinha2Conta(), 0,
									0)
							+ formarLinha(0, 3, 35, 1360, emitirContaHelper
									.getMsgLinha3Conta() != null
									&& emitirContaHelper.getMsgLinha3Conta()
											.length() > 45 ? emitirContaHelper
									.getMsgLinha3Conta().substring(0, 45)
									: emitirContaHelper.getMsgLinha3Conta(), 0,
									0));
				}*/
				
				String msgConta = "";
				
				/**TODO: COSANPA
				 * 
				 * Mantis 775 - Alteração na emissão do extrato de quitação anual de débitos
				 * como mensagem nas contas impressas via impressão térmica
				 * 
				 * @author Wellington Rocha
				 * @date 04/04/2013*/
				if(emitirContaHelper.getMsgLinha1Conta() != null){
					msgConta = emitirContaHelper.getMsgLinha1Conta();
					if(emitirContaHelper.getMsgLinha2Conta() != null){
						msgConta = msgConta + " " + emitirContaHelper.getMsgLinha2Conta();
						if(emitirContaHelper.getMsgLinha3Conta() != null){
							msgConta = msgConta + " " + emitirContaHelper.getMsgLinha3Conta();
						}
					}
					
					msgConta = msgConta.replace('!', '.');
				}
				
				if(msgConta.length() > 60){
					retorno.append(formarLinha(7, 0, 35, 1300,msgConta.substring(0, 60),0,0));
					if (msgConta.length() > 120){
						retorno.append(formarLinha(7, 0, 35, 1320,msgConta.substring(60, 120),0,0));
						if(msgConta.length()> 180){
							retorno.append(formarLinha(7, 0, 35, 1340,msgConta.substring(120, 180),0,0));
							if(msgConta.length()> 240){
								retorno.append(formarLinha(7, 0, 35, 1360,msgConta.substring(180, 240),0,0));
							}else{
								retorno.append(formarLinha(7, 0, 35, 1360,msgConta.substring(180),0,0));
							}
						}else{
							retorno.append(formarLinha(7, 0, 35, 1340,msgConta.substring(120),0,0));
						}
					}else{
						retorno.append(formarLinha(7, 0, 35, 1320,msgConta.substring(60),0,0));
					}
				}else{
					retorno.append(formarLinha(7, 0, 35, 1300,msgConta,0,0));
				}
				
				retorno.append(formarLinha(0, 2, 344, 1456, emitirContaHelper
						.getIdImovel()
						+ "", 0, 0)
						+ formarLinha(0, 2, 443, 1456, Util
								.formatarAnoMesParaMesAno(emitirContaHelper
										.getAmReferencia()), 0, 0)
						+ formarLinha(0, 2, 558, 1456, Util
								.formatarData(emitirContaHelper
										.getDataVencimentoConta()), 0, 0)
						+ formarLinha(0, 2, 694, 1456, Util
								.formatarMoedaReal(valorTotalConta), 0, 0));
				if (!imovelEmitido.getIndicadorDebitoConta().equals(Imovel.INDICADOR_DEBITO_AUTOMATICO)){
					/*if (emitirContaHelper
							.getRepresentacaoNumericaCodBarraFormatada().equals(null)){*/
						Integer digitoVerificadorConta = new Integer(""
								+ emitirContaHelper.getDigitoVerificadorConta());
						// formata ano mes para mes ano
						String anoMes = "" + emitirContaHelper.getAmReferencia();
						String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

						String representacaoNumericaCodBarra = "";

						// Linha28
						Date dataValidade = emitirContaHelper.getDataValidadeConta();

						if(emitirContaHelper.getValorConta() != null){
						if (emitirContaHelper.getValorConta().compareTo(new BigDecimal("0.00")) != 0){
							representacaoNumericaCodBarra = this
									.getControladorArrecadacao()
									.obterRepresentacaoNumericaCodigoBarra(3, emitirContaHelper.getValorConta(),
											emitirContaHelper.getIdLocalidade(),
											emitirContaHelper.getIdImovel(), mesAno,
											digitoVerificadorConta, null, null, null, null,
											null, null, null);

							// Linha 24
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
							emitirContaHelper
									.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

							// Linha 25
							String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
									.substring(0, 11)
									+ representacaoNumericaCodBarra.substring(12, 23)
									+ representacaoNumericaCodBarra.substring(24, 35)
									+ representacaoNumericaCodBarra.substring(36, 47);
							emitirContaHelper
									.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
						}
						
					
					String representacaoNumericaCodBarraFormatada = emitirContaHelper
							.getRepresentacaoNumericaCodBarraFormatada();
					if (representacaoNumericaCodBarraFormatada != null){
					retorno.append(formarLinha(5, 0, 66, 1515,
							representacaoNumericaCodBarraFormatada, 0, 0));
					String representacaoCodigoBarrasSemDigitoVerificador = emitirContaHelper
							.getRepresentacaoNumericaCodBarraSemDigito();
					retorno.append("B I2OF5 1 2 90 35 1538 "
							+ representacaoCodigoBarrasSemDigitoVerificador
							+ "\n");
						}
						}
						} else {
					retorno.append(formarLinha(4, 0, 182, 1538,
							"DEBITO AUTOMATICO", 0, 0));
					// "\n";
				}
				retorno.append(formarLinha(5, 0, 109, 1661, emitirContaHelper
						.getIdFaturamentoGrupo()
						+ "", 0, 0));

				
				retorno.append(formarLinha(5, 0, 352, 1661, "4", 0, 0));
				
				/**TODO: COSANPA
				 * Mantis 680 - Incluir informação de sequencial de conta a ser impressa.
				 * 
				 * @author Wellington Rocha
				 * @date 06/12/2012*/
				retorno.append(formarLinha(5, 0, 615, 1661, ""+contador, 0, 0));
				
				retorno.append("FORM\n" + "PRINT\n");
				
				return retorno.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return retorno.toString();
		
	}
	
	   public String getTipoConsumoToPrint(int tipoConsumo){
	       
	        String resultado = "";
	       
	        if (tipoConsumo == ConsumoTipo.MEDIA_HIDROMETRO){
	            resultado =    "CONSUMO MEDIO(m3)";
	       
	        }else if (tipoConsumo == ConsumoTipo.CONSUMO_MINIMO_FIXADO){
	            resultado =    "CONSUMO MINIMO(m3)";

	        }else if (tipoConsumo == ConsumoTipo.NAO_MEDIDO){
	            resultado =    "CONSUMO NAO MEDIDO(m3)";

	        }else if (tipoConsumo == ConsumoTipo.REAL){
	            resultado =    "CONSUMO REAL(m3)";

	        }else if (tipoConsumo == ConsumoTipo.CONSUMO_MEDIO_AJUSTADO){
	            resultado =    "CONSUMO PROPOR. DIAS(m3)";

	        }else if (tipoConsumo == ConsumoTipo.FIXO_SITUACAO_ESPECIAL){
	            resultado =    "CONSUMO SIT. ESPECIAL(m3)";

	        }
	        return resultado;
	    }
	   
	   public Object[] obterConsumoAnteriorIS(Integer idImovel, int anoMes,
				int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
				throws ControladorException {

			int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);

			Object[] parmsConsumoHistorico = null;
			if (tipoLigacao != null && tipoMedicao != null) {

				parmsConsumoHistorico = getControladorMicromedicao()
						.obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
								anoMesSubtraido, tipoLigacao);
			}
			return parmsConsumoHistorico;
		}
	
//	public void Imprimir(String stringImpressao) {
//		StreamConnection conn = null;
//		DataOutputStream out = null;
//		String printerURL = "btspp://00225807b684:1;authenticate=true;encrypt=true;master=true;";
//
//		try {
//
//			/*
//			 * StreamConnection printerConnection = (StreamConnection)
//			 * Connector.open(printerURL, Connector.WRITE); DataOutputStream
//			 * writer = null; writer = printerConnection.openDataOutputStream();
//			 * writer.write(stringImpressao.getBytes()); writer.flush();
//			 * writer.close(); printerConnection.close();
//			 */
//			conn = (StreamConnection) Connector.open(printerURL,
//					Connector.WRITE);
//			out = conn.openDataOutputStream();
//			out.writeBytes(stringImpressao);
//			/*
//			 * out.writeBytes("! 0 200 200 203 1" + '\n' + "CENTER" + '\n');
//			 * out.writeBytes("TEXT 0 3 10 50 JAVA TEST" + '\n' + "PRINT" +
//			 * '\n');
//			 */
//			System.out.println("Impressao Enviada!!!!!!");
//			out.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
