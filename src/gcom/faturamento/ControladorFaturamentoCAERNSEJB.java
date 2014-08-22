package gcom.faturamento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixaHistorico;
import gcom.faturamento.conta.ContaCategoriaHistorico;
import gcom.faturamento.conta.ContaImpressao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.FiltroContaImpressao;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.relatorio.faturamento.conta.ContaLinhasDescricaoServicosTarifasTotalHelper;
import gcom.seguranca.acesso.usuario.Usuario;
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
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;

import br.com.danhil.BarCode.Interleaved2of5;

/**
 * Controlador Faturamento CAERN
 * 
 * @author Raphael Rossiter
 * @date 20/12/2006
 */
public class ControladorFaturamentoCAERNSEJB extends ControladorFaturamento
		implements SessionBean {
	private static final long serialVersionUID = 1L;

	// ==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAERN
	// ==============================================================================================================
	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 08/01/2007
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
				colectionConta = this.repositorioFaturamento
						.pesquisarContaERota(idContaEP);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) colectionConta
					.iterator().next();

			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			String nomeCliente = "";
			if (emitirContaHelper.getNomeImovel() != null
					&& !emitirContaHelper.getNomeImovel().equals("")) {

				nomeCliente = emitirContaHelper.getNomeImovel();
				emitirContaHelper.setNomeCliente(nomeCliente);
			}

			// Linha 5
			// --------------------------------------------------------------
			// recupera endereco do imóvel
			String enderecoImovel = "";
			try {
				enderecoImovel = getControladorEndereco()
						.pesquisarEnderecoFormatado(
								emitirContaHelper.getIdImovel());
			} catch (ControladorException e1) {
				e1.printStackTrace();
			}
			emitirContaHelper.setEnderecoImovel(enderecoImovel);

			// Linha 6
			// --------------------------------------------------------------
			// instância um imovel com os dados da conta para recuperar a
			// inscrição que está no objeto imovel
			Imovel imovel = new Imovel();
			Localidade localidade = new Localidade();
			localidade.setId(emitirContaHelper.getIdLocalidade());
			imovel.setLocalidade(localidade);
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setCodigo(emitirContaHelper
					.getCodigoSetorComercialConta());
			imovel.setSetorComercial(setorComercial);
			Quadra quadra = new Quadra();
			quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());
			imovel.setQuadra(quadra);
			imovel.setLote(emitirContaHelper.getLoteConta());
			imovel.setSubLote(emitirContaHelper.getSubLoteConta());
			// Inscrição do imóvel
			emitirContaHelper
					.setInscricaoImovel(imovel.getInscricaoFormatada());

			// Linha 7
			// --------------------------------------------------------------
			String idClienteResponsavel = "";
			String enderecoClienteResponsavel = "";
			Integer idImovelContaEnvio = emitirContaHelper
					.getIdImovelContaEnvio();
			// caso a coleção de contas seja de entrega para o cliente
			// responsável
			if (idImovelContaEnvio != null
					&& (idImovelContaEnvio
							.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) || idImovelContaEnvio
							.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {
				Integer idClienteResponsavelInteger = null;
				idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(
						emitirContaHelper.getIdConta(), false);

				if (idClienteResponsavel != null
						&& !idClienteResponsavel.equals("")) {
					idClienteResponsavel = idClienteResponsavelInteger
							.toString();
					// [UC0085]Obter Endereco
					enderecoClienteResponsavel = getControladorEndereco()
							.pesquisarEnderecoClienteAbreviado(
									idClienteResponsavelInteger);
				}

			}

			emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
			emitirContaHelper
					.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

			// Linha 8
			// --------------------------------------------------------------

			// [SB0002] - Determinar tipo de ligação e tipo de Medição
			Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
			Integer tipoLigacao = parmSituacao[0];
			Integer tipoMedicao = parmSituacao[1];

			// Linha 9
			// --------------------------------------------------------------
			// cria uma stringBuilder para recuperar o resultado do [SB0003]
			// o tamanho da string que vem como resultado é de 20 posições
			StringBuilder obterDadosConsumoMedicaoAnterior = null;

			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 1
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 1, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes1(obterDadosConsumoMedicaoAnterior
							.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 4
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 4, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes4(obterDadosConsumoMedicaoAnterior
							.toString());

			// Linha 10
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 2
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 2, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes2(obterDadosConsumoMedicaoAnterior
							.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 5
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 5, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes5(obterDadosConsumoMedicaoAnterior
							.toString());
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros da medição historico do
			// [SB0004] - Obter Dados da Medição da Conta
			Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
					emitirContaHelper, tipoMedicao);
			// Leitura Anterior
			String leituraAnterior = "";
			// Leitura Atual
			String leituraAtual = "";
			// Data Leitura Anterior
			String dataLeituraAnterior = "";
			// Leitura Anterior
			String dataLeituraAtual = "";
			// Leitura Situação Atual
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
					dataLeituraAnterior = Util
							.formatarData((Date) parmsMedicaoHistorico[3]);
				}

				if (parmsMedicaoHistorico[2] != null) {
					dataLeituraAtual = Util
							.formatarData((Date) parmsMedicaoHistorico[2]);
				}

				if (parmsMedicaoHistorico[4] != null) {
					// leituraSituacaoAtual = ""
					// + (Integer) parmsMedicaoHistorico[4];
				}

				if (parmsMedicaoHistorico[5] != null) {
					leituraAnormalidadeFaturamento = ""
							+ (Integer) parmsMedicaoHistorico[5];
				}
			}
			emitirContaHelper.setDataLeituraAnterior(dataLeituraAnterior);
			emitirContaHelper.setDataLeituraAtual(dataLeituraAtual);
			String diasConsumo = "";
			if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
				// calcula a quantidade de dias de consumo que é a
				// quantidade de dias
				// entre a data de leitura
				// anterior(parmsMedicaoHistorico[2]) e a data de leitura
				// atual(parmsMedicaoHistorico[3])
				diasConsumo = ""
						+ Util.obterQuantidadeDiasEntreDuasDatas(
								(Date) parmsMedicaoHistorico[3],
								(Date) parmsMedicaoHistorico[2]);
			}
			// recupera os parametros de consumo faturamento e consumo médio
			// diário
			// [SB0005] - Obter Consumo Faturado e Consumo Médio Diário
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
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 3
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 3, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes3(obterDadosConsumoMedicaoAnterior
							.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 6
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 6, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes6(obterDadosConsumoMedicaoAnterior
							.toString());

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
					parmsConsumoHistorico = getControladorMicromedicao()
							.obterDadosConsumoConta(
									emitirContaHelper.getIdImovel(),
									emitirContaHelper.getAmReferencia(),
									tipoLigacao);
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				if (parmsConsumoHistorico != null) {
					// descrição abreviada tipo de consumo
					if (parmsConsumoHistorico[0] != null) {
						descricaoAbreviadaTipoConsumo = (String) parmsConsumoHistorico[0];
					}
					// descrição tipo de consumo
					if (parmsConsumoHistorico[1] != null) {
						descricaoTipoConsumo = (String) parmsConsumoHistorico[1];
					}
					// Consumo médio
					if (parmsConsumoHistorico[2] != null) {
						consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
					}
					// descrição abreviada anormalidade de consumo
					if (parmsConsumoHistorico[3] != null) {
						descricaoAbreviadaAnormalidadeConsumo = (String) parmsConsumoHistorico[3];
					}
					// descrição anormalidade de consumo
					if (parmsConsumoHistorico[4] != null) {
						descricaoAnormalidadeConsumo = (String) parmsConsumoHistorico[4];
					}
					// Consumo médio
					if (parmsConsumoHistorico[5] != null) {
						consumoRateio = "" + (Integer) parmsConsumoHistorico[5];
					}
				}
			}

			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper
					.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);

			// Fim Chamar Sub-Fluxo

			// Linha 13
			// --------------------------------------------------------------

			// Inicio Chamar Sub-Fluxo
			// soma a quantidades de economias da tabela contaCategoria
			// [SB0007] - Obter Dados da Medição da Conta
			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(
					emitirContaHelper.getIdConta(), false);
			emitirContaHelper.setQuantidadeEconomiaConta(""
					+ quantidadeEconomiaConta);
			// Fim Chamar Sub-Fluxo

			// Consumo por Economia
			// transforma o consumoFaturamento para um bigDecimal
			BigDecimal consumoFaturadoBigDecimal = null;
			if (consumoFaturamento != null && !consumoFaturamento.equals("")) {
				consumoFaturadoBigDecimal = Util
						.formatarMoedaRealparaBigDecimal(consumoFaturamento);

			}
			// transforma a quantidade de economias da conta para um
			// bigDecimal
			BigDecimal qtdEconomiasBigDecimal = null;
			if (quantidadeEconomiaConta != null
					&& !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util
						.formatarMoedaRealparaBigDecimal(""
								+ quantidadeEconomiaConta);
			}
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null
					&& qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal
						.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util
						.formatarMoedaReal(consumoEconomiaBigDecimal);
				emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(
						0, (consumoEconomia.length() - 3)));
			}

			// Inicio Chamar Sub-Fluxo
			// concatena os campos dos sub-fluxos anteriores
			// [SB0008] - Obter Dados da Medição da Conta
			StringBuilder codigoAuxiliar = new StringBuilder();
			// leitura situação atual
			// tipo de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaTipoConsumo, 1));
			// tipo de contrato
			codigoAuxiliar.append(Util.completaString("", 1));
			// anormalidade de leitura
			codigoAuxiliar.append(Util.completaString(
					leituraAnormalidadeFaturamento, 2));
			// anormalidade de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaAnormalidadeConsumo, 2));

			// perfil do imóvel
			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString(""
						+ emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}
			// dias do consumo
			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			// Consumo medio do imóvel
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			// Fim Chamar Sub-Fluxo
			emitirContaHelper
					.setCodigoAuxiliarString(codigoAuxiliar.toString());

			// chama o [SB0009] - Obter Mensagem de Rateio de Consumo Fixo
			// de Esgoto
			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao);
			// mensagem de rateio de consumo ou consumo fixo de esgoto
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo
					.toString());

			// Linha 16
			// --------------------------------------------------------------
			// chama o [SB0010] - Gerar Linhas da Descrição dos Serviços e
			// Tarifas

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = gerarLinhasDescricaoServicoTarifasRelatorio(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao, false);
			emitirContaHelper
					.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

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

			emitirContaHelper.setValorContaString(Util
					.formatarMoedaReal(valorConta));
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
			int anoMesReferenciaSubtraido = Util.subtrairMesDoAnoMes(
					emitirContaHelper.getAmReferencia(), 1);
			emitirContaHelper.setMesAnoFormatado(Util
					.formatarAnoMesParaMesAno(anoMesReferenciaSubtraido));

			// Linha 22
			// --------------------------------------------------------------
			Object[] parmsQualidadeAgua = null;
			parmsQualidadeAgua = pesquisarParmsQualidadeAgua(emitirContaHelper);

			// numero indice turbidez da qualidade agua
			String numeroIndiceTurbidez = "";
			// numero cloro residual da qualidade agua
			String numeroCloroResidual = "";
			// numero nitrato da qualidade agua
			String numeroNitrato = "";
			// numero qtd coliformes totais da qualidade agua
			String numeroColiformesTotais = "";
			// numero PH da qualidade agua
			String numeroPH = "";			
			
			if (parmsQualidadeAgua != null) {
				if (parmsQualidadeAgua[0] != null) {
					numeroIndiceTurbidez = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]);
				}

				if (parmsQualidadeAgua[1] != null) {
					numeroCloroResidual = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]);
				}
				
				if (parmsQualidadeAgua[2] != null) {
					numeroNitrato = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[2]);
				}
				
				if (parmsQualidadeAgua[3] != null) {
					numeroColiformesTotais = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[3]);
				}
				
				if (parmsQualidadeAgua[4] != null) {
					numeroPH = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[4]);
				}
				
			}
			emitirContaHelper.setNumeroIndiceTurbidez(numeroIndiceTurbidez);
			emitirContaHelper.setNumeroCloroResidual(numeroCloroResidual);
			emitirContaHelper.setNumeroNitrato(numeroNitrato);
			emitirContaHelper.setValorMedioColiformesTotais(numeroColiformesTotais);
			emitirContaHelper.setValorMedioPh(numeroPH);

			
			//Se valor da conta maior que o valor limite
			//emite uma Ficha de Compensação(Boleto bancario)
			if (sistemaParametro.getValorContaFichaComp() != null 
				&& !sistemaParametro.getValorContaFichaComp().equals(new BigDecimal("0.00"))
				&& valorConta!= null  && valorConta.compareTo(sistemaParametro.getValorContaFichaComp()) == 1){
				//representação numérica do código de barras
				//[SB0030 - Obter representação numérica do código de barras da Ficha de Compensação]
				StringBuilder nossoNumero = obterNossoNumeroFichaCompensacao("1",emitirContaHelper.getIdConta().toString()) ;
				String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
				
				emitirContaHelper.setNossoNumero(nossoNumero.toString());
	            
                //RM100 - Colocar fixo o fator de vencimento 0000
                //Date dataVencimentoMais90 = Util.adicionarNumeroDiasDeUmaData(new Date(),90);
                //String fatorVencimento = obterFatorVencimento(dataVencimentoMais90);
                String fatorVencimento = null;

				
				String especificacaoCodigoBarra = getControladorArrecadacao().
					obterEspecificacaoCodigoBarraFichaCompensacao(
				    ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, 
				    ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
					emitirContaHelper.getValorConta(), nossoNumeroSemDV.toString(),
					ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, fatorVencimento);
				                                
				emitirContaHelper.setRepresentacaoNumericaCodBarraSemDigito(especificacaoCodigoBarra);
				
				String representacaoNumericaCodigoBarraFichaCompensacao = 
				getControladorArrecadacao().
				obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);
				                     
				emitirContaHelper.setRepresentacaoNumericaCodBarraFormatada(
						representacaoNumericaCodigoBarraFichaCompensacao);
			}else{
				
				// Linha 23
				// --------------------------------------------------------------
				// Considerar as contas do tipo débito automático como tipo de conta normal
				// [SB0018 - Gerar Linhas das DemaisContas]
				Integer digitoVerificadorConta = new Integer(""
						+ emitirContaHelper.getDigitoVerificadorConta());
				// formata ano mes para mes ano
				String anoMes = "" + emitirContaHelper.getAmReferencia();
				String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);
	
				String representacaoNumericaCodBarra = "";
	
				representacaoNumericaCodBarra = this.getControladorArrecadacao()
						.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
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
				emitirContaHelper.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
				
			}
			
			
			// Linha28
			if (emitirContaHelper.getDataValidadeConta().compareTo(new Date()) == 1) {
				emitirContaHelper.setDataValidade(Util.formatarData(emitirContaHelper.getDataValidadeConta()));

			} else {
				// soma 60 dias a data atual
				Date dataValidadeConta = Util.adicionarNumeroDiasDeUmaData(new Date(), 60);

				int ano = Util.getAno(dataValidadeConta);
				int mes = Util.getMes(dataValidadeConta);
				Calendar calendar = new GregorianCalendar();
				calendar.set(Calendar.MONTH, mes - 1);
				calendar.set(Calendar.YEAR, ano);

				Collection colecaoNacionalFeriado = getControladorUtil().pesquisarFeriadosNacionais();

				Collection colecaoDatasFeriados = new ArrayList();
				Iterator iterNacionalFeriado = colecaoNacionalFeriado.iterator();
				while (iterNacionalFeriado.hasNext()) {
					NacionalFeriado nacionalFeriado = (NacionalFeriado) iterNacionalFeriado.next();
					colecaoDatasFeriados.add(nacionalFeriado.getData());
				}

				calendar.set(Calendar.DAY_OF_MONTH, Util.obterUltimoDiaUtilMes(mes, ano, colecaoDatasFeriados));

				dataValidadeConta = calendar.getTime();

				emitirContaHelper.setDataValidade(Util.formatarData(dataValidadeConta));

			}

			Pagamento pagamento = getControladorArrecadacao().pesquisarPagamentoDeConta(idContaEP);
			if (pagamento != null && pagamento.getValorPagamento().compareTo(valorConta) >= 0) {
				emitirContaHelper.setContaPaga("1");
			}

			colecaoEmitirContaHelper.add(emitirContaHelper);

			if (cobrarTaxaEmissaoConta) {
				this.gerarDebitoACobrarTaxaEmissaoConta(emitirContaHelper
						.getIdImovel(), emitirContaHelper.getAmReferencia());
			}

		}

		return colecaoEmitirContaHelper;
	}


	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Sávio Luiz, Pedro Alexandre, Tiago Moreno
	 * @date 15/05/2006, 19/09/2006
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

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						(idEmpresa == null ? 0 : idEmpresa));
		try {

			int quantidadeContas = 0;

			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;

			try {

				// recebe todos as contas da lista
				StringBuilder contasTxtLista = null;
				Map<Integer, Integer> mapAtualizaSequencial = null;

				// i=0;Coleção de contas com estouros de consumo
				// i=1;Coleção de contas com baixo consumo
				// i=2;Coleção de contas com débito automático
				// i=3;Coleção de contas com entrega para o cliente
				// responsável
				// i=4;Coleção de contas com entrega para o cliente
				// responsável
				// i=5;Coleção de contas normais

				try {
					
					System.out.println("###########################################");
					System.out.println("###########################################");
					System.out.println("###### ENTROU NO EMITIR CONTAS CAERN ######");
					System.out.println("###########################################");
					System.out.println("###########################################");

					boolean flagTerminou = false;
					numeroIndice = 0;
					Integer sequencialImpressao = 0;
					Integer sequencialCarta = 0;
					Collection colecaoConta = null;
					Collection colecaoContaFichaCompensacao = null;
					boolean flagPesquisarFichaCompensacao = false;

					contasTxtLista = new StringBuilder();
					// cartasTxtListaConta = new StringBuilder();
					
					SistemaParametro sistemaParametro = getControladorUtil()
						.pesquisarParametrosDoSistema();

					if(sistemaParametro.getValorContaFichaComp() != null && 
							!sistemaParametro.getValorContaFichaComp().equals(new BigDecimal("0.00"))){
						flagPesquisarFichaCompensacao = true;
					}
					
					int contadorTeste = 0;
					
					while (!flagTerminou) {
						// map que armazena o sequencial e o numero da
						// conta para no final atualizar todos os
						// sequencias
						mapAtualizaSequencial = new HashMap();
						Collection colecaoContaParms = null;
						Collection colecaoContaParmsFichaCompensacao = null;
						
						if(flagPesquisarFichaCompensacao){
	                          flagPesquisarFichaCompensacao = false;
	                          colecaoContaParmsFichaCompensacao = repositorioFaturamento
	                          		.pesquisarContasFichaCompensacaoEmitirCAERN(
	                          				ContaTipo.CONTA_NORMAL, 
	                          				idEmpresa, anoMesReferenciaFaturamento,
	                          				faturamentoGrupo.getId(),
	                          				sistemaParametro.getValorContaFichaComp());
	                          
	                          colecaoContaFichaCompensacao = formatarEmitirContasHelper(
	                        		  colecaoContaParmsFichaCompensacao, ContaTipo.CONTA_NORMAL); 
//	                          formatarEmitirContasHelperOuFichaCompensacao(colecaoContaParmsFichaCompensacao, tipoConta);
	                          colecaoContaParmsFichaCompensacao = null;
	                          
	                          if (colecaoContaFichaCompensacao != null && !colecaoContaFichaCompensacao.isEmpty()) {
	                              
	                            emitirFichaCompensacao(colecaoContaFichaCompensacao,ContaTipo.CONTA_NORMAL,
	                                      faturamentoGrupo,idEmpresa,anoMesReferenciaFaturamento);
	                              
	                            colecaoContaFichaCompensacao = null;
	                              
	                          }
	                    }
						
						colecaoContaParms = repositorioFaturamento
								.pesquisarContasEmitirCAERN(
										ContaTipo.CONTA_NORMAL, idEmpresa,
										numeroIndice,
										anoMesReferenciaFaturamento,
										faturamentoGrupo.getId(),
										sistemaParametro.getValorContaFichaComp());
						
						colecaoConta = formatarEmitirContasHelper(
								colecaoContaParms, ContaTipo.CONTA_NORMAL);

						if (colecaoConta != null && !colecaoConta.isEmpty()) {

							if (colecaoConta.size() < quantidadeRegistros) {
								flagTerminou = true;
							}

							Map<Integer, Map<EmitirContaHelper, EmitirContaHelper>> 
								mapContasDivididasOrdenada = dividirColecao(colecaoConta);

							colecaoConta = null;

							EmitirContaHelper emitirContaHelper = null;

							if (mapContasDivididasOrdenada != null) {
								int countOrdem = 0;
								while (countOrdem < mapContasDivididasOrdenada
										.size()) {
									Map<EmitirContaHelper, EmitirContaHelper> mapContasDivididas = mapContasDivididasOrdenada
											.get(countOrdem);

									Iterator iteratorConta = mapContasDivididas
											.keySet().iterator();

									// int count = 0;
									while (iteratorConta.hasNext()) {

										emitirContaHelper = null;

										int situacao = 0;

										emitirContaHelper = (EmitirContaHelper) iteratorConta
												.next();
										while (situacao < 2) {
											if (situacao == 0) {
												situacao = 1;
												// [SB0020] - Gerar
												// Arquivo
												// TXT
												// das
												// Cartas
												if (tipoConta == 0
														|| tipoConta == 1) {
													sequencialCarta += 1;
												}
												sequencialImpressao += 1;
											} else {
												emitirContaHelper = mapContasDivididas
														.get(emitirContaHelper);
												situacao = 2;
											}

											quantidadeContas++;
											// Só para exibir no console
											// a
											// quantidade de
											// contas

											StringBuilder contaTxt = new StringBuilder();

											if (emitirContaHelper != null) {
												
												
												
												String descricaoLocalidade = emitirContaHelper.getDescricaoLocalidade();
												contaTxt.append(Util.completaString(descricaoLocalidade,30));
												contaTxt.append(Util.adicionarZerosEsquedaNumero(3,
														emitirContaHelper.getCodigoSetorComercialConta().toString()));

												Imovel imovelEmitido = getControladorImovel()
														.pesquisarImovel(emitirContaHelper.getIdImovel());

												contaTxt.append(Util.adicionarZerosEsquedaNumero(
														2,imovelEmitido.getQuadra().getRota().getCodigo().toString()));

												contaTxt.append(".");

												contaTxt.append(Util.adicionarZerosEsquedaNumero(4,
														imovelEmitido.getNumeroSequencialRota().toString()));

												contaTxt.append(Util.adicionarZerosEsquedaNumero(8,
														emitirContaHelper.getIdImovel().toString()));

												// caso a coleção de contas seja de entrega
												// para o cliente responsável
												if (tipoConta == 3 || tipoConta == 4) {
													String nomeClienteUsuario = null;
													if (emitirContaHelper.getNomeImovel() != null
															&& !emitirContaHelper.getNomeImovel().equals("")) {
														nomeClienteUsuario = emitirContaHelper.getNomeImovel();

													} else {
														try {
															
															nomeClienteUsuario = repositorioFaturamento
																	.pesquisarNomeClienteUsuarioConta(
																	emitirContaHelper.getIdConta());
															
														} catch (ErroRepositorioException e) {
															throw new ControladorException("erro.sistema",e);
														}
													}
													contaTxt.append(Util.completaString(nomeClienteUsuario,30));
												} else {
													contaTxt.append(Util.completaString(
															emitirContaHelper.getNomeCliente(),30));
												}

												String[] enderecoImovel = getControladorEndereco()
														.pesquisarEnderecoFormatadoDividido(
																emitirContaHelper.getIdImovel());

												// endereço
												contaTxt.append(Util.completaString(enderecoImovel[0],60));

												// bairro
												contaTxt.append(Util.completaString(enderecoImovel[3],30));

												// numero indice turbidez da qualidade agua

												// numero cloro residual da qualidade agua

												FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

												filtroLocalidade.adicionarParametro(new ParametroSimples(
														FiltroLocalidade.ID,emitirContaHelper.getIdLocalidade()));

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

												
												Collection cLocalidade = (Collection) getControladorUtil().pesquisar(
														filtroLocalidade,Localidade.class.getName());
												
												Localidade localidade = (Localidade) cLocalidade.iterator().next();

												FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

												Collection colecaoQualidadeAgua = null;

												filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
														FiltroQualidadeAgua.LOCALIDADE_ID,localidade.getId()));
												
												filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
														FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
														emitirContaHelper.getIdSetorComercial().toString()));
												
												filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
														FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
												
												filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
												
												colecaoQualidadeAgua = getControladorUtil().pesquisar(
														filtroQualidadeAgua,QualidadeAgua.class.getName());

												if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
													
													filtroQualidadeAgua.limparListaParametros();
													
													colecaoQualidadeAgua = null;
													filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
															FiltroQualidadeAgua.LOCALIDADE_ID,localidade.getId()));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
															FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
													
													filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
													
													colecaoQualidadeAgua = getControladorUtil().pesquisar(
															filtroQualidadeAgua, QualidadeAgua.class.getName());
												}
												
												if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
													
													filtroQualidadeAgua.limparListaParametros();
													
													colecaoQualidadeAgua = null;
													filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.LOCALIDADE_ID));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
															FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
													
													filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
													
													colecaoQualidadeAgua = getControladorUtil().pesquisar(
															filtroQualidadeAgua, QualidadeAgua.class.getName());
												}

												if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {
													
													QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();

													// fonte
													if (qualidadeAgua.getFonteCaptacao() != null) {
														contaTxt.append(Util.completaString(qualidadeAgua.getFonteCaptacao().getDescricao(),30));
													} else {
														contaTxt.append(Util.completaString(" ",30));
													}
													
													// cloro
													if (qualidadeAgua.getNumeroCloroResidual() != null
															&& !qualidadeAgua.getNumeroCloroResidual().equals(0)) {
														contaTxt.append(Util.completaString(
																qualidadeAgua.getNumeroCloroResidual().toString(),3));
													} else {
														contaTxt.append(Util.completaString(" ",3));
													}

													// coliformes
													if (qualidadeAgua.getNumeroIndiceColiformesTotais() != null
															&& !qualidadeAgua.getNumeroIndiceColiformesTotais().equals(0)) {
														contaTxt.append(Util.completaString(
															qualidadeAgua.getNumeroIndiceColiformesTotais().toString(),8));
													} else {
														contaTxt.append(Util.completaString(" ", 8));
													}

													// nitrato
													if (qualidadeAgua.getNumeroNitrato() != null
															&& !qualidadeAgua.getNumeroNitrato().equals(0)) {
														contaTxt.append(Util.completaString(
																qualidadeAgua.getNumeroNitrato().toString(),4));
													} else {
														contaTxt.append(Util.completaString(" ",4));
													}

													// //ph
													if (qualidadeAgua.getNumeroIndicePh() != null
															&& !qualidadeAgua.getNumeroIndicePh().equals(0)) {
														contaTxt.append(Util.completaString(
																qualidadeAgua.getNumeroIndicePh().toString(),4));
													} else {
														contaTxt.append(Util.completaString(" ",4));
													}

													// //turbidez
													if (qualidadeAgua.getNumeroIndiceTurbidez() != null
															&& !qualidadeAgua.getNumeroIndiceTurbidez().equals(0)) {
														contaTxt.append(Util.completaString(
														qualidadeAgua.getNumeroIndiceTurbidez().toString(),4));
													} else {
														contaTxt.append(Util.completaString(" ",4));
													}

												} else {
													contaTxt.append(Util.completaString(" ", 53));
												}

												Collection colecaoSubCategoria = getControladorImovel()
														.obterQuantidadeEconomiasSubCategoria(imovelEmitido.getId());

												String economias = "";

												for (Iterator iter = colecaoSubCategoria.iterator(); iter.hasNext();) {
													Subcategoria subcategoria = (Subcategoria) iter.next();
													
													//agora a subcategoria ja tem o id da categoria no codigo.
													economias = economias + Util.adicionarZerosEsquedaNumero(
														3, subcategoria.getCodigo()+ "") + "/"
														+ Util.adicionarZerosEsquedaNumero(3,
												        subcategoria.getQuantidadeEconomias().toString()) + " ";

												}


												contaTxt.append(Util.adicionarZerosEsquedaNumero(
													7,quantidadeContas + ""));

												contaTxt.append(Util.completaString(localidade.getDescricao(),30));

												contaTxt.append(Util.completaString(localidade
													.getEnderecoFormatadoTituloAbreviado(),35));

												contaTxt.append(Util.completaString(localidade.getFone(),20));

												contaTxt.append(Util.completaString("0800-84-0195",15));

												// cria um objeto conta para calcular o valor da conta
												Conta conta = new Conta();
												conta.setValorAgua(emitirContaHelper.getValorAgua());
												conta.setValorEsgoto(emitirContaHelper.getValorEsgoto());
												conta.setValorCreditos(emitirContaHelper.getValorCreditos());
												conta.setDebitos(emitirContaHelper.getDebitos());
												conta.setValorImposto(emitirContaHelper.getValorImpostos());

												BigDecimal valorConta = conta.getValorTotalContaBigDecimal();

												// [SB0018 - Gerar Linhas das Demais Contas]
												String anoMesString = "" + emitirContaHelper.getAmReferencia();
												// formata ano mes para mes ano

												String mesNumero = anoMesString.substring(4, 6);

												String mesExtenso = Util.retornaDescricaoMes(
													new Integer(mesNumero).intValue()).toUpperCase();
												String dataExtensa = mesExtenso	+ "/"
														+ anoMesString.substring(0, 4);

												String mesAnoFormatado = anoMesString.substring(4, 6)+ anoMesString														.substring(0, 4);
												Integer digitoVerificadorConta = new Integer(""
														+ emitirContaHelper.getDigitoVerificadorConta());
												String representacaoNumericaCodBarra = null;

												representacaoNumericaCodBarra = this.getControladorArrecadacao()
														.obterRepresentacaoNumericaCodigoBarra(
																3,
																valorConta,
																emitirContaHelper.getIdLocalidade(),
																emitirContaHelper.getIdImovel(),
																mesAnoFormatado,
																digitoVerificadorConta,
																null, null,
																null, null,
																null, null,
																null);

												contaTxt.append(Util.completaString(
														representacaoNumericaCodBarra,48));

												// determinar Mensagem
												String[] parmsPartesConta = obterMensagemConta3Partes(
														emitirContaHelper,sistemaParametro);

												String primeiraParte = parmsPartesConta[0];
												String segundaParte = parmsPartesConta[1];

												contaTxt.append(Util.completaString(primeiraParte,65));
												contaTxt.append(Util.completaString(segundaParte,65));

												contaTxt.append(System.getProperty("line.separator"));

												contaTxt.append(Util.completaString(descricaoLocalidade,30));

												contaTxt.append(Util.adicionarZerosEsquedaNumero(2,
													imovelEmitido.getQuadra().getRota().getCodigo().toString()));

												contaTxt.append(".");

												contaTxt.append(Util.adicionarZerosEsquedaNumero(4,
													imovelEmitido.getNumeroSequencialRota().toString()));

												Imovel imovel = new Imovel();
												Localidade localidade2 = new Localidade();
												localidade2.setId(emitirContaHelper.getIdLocalidade());
												imovel.setLocalidade(localidade2);
												SetorComercial setorComercial = new SetorComercial();
												setorComercial.setCodigo(emitirContaHelper.getCodigoSetorComercialConta());
												imovel.setSetorComercial(setorComercial);
												Quadra quadra = new Quadra();
												quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());
												imovel.setQuadra(quadra);
												imovel.setLote(emitirContaHelper.getLoteConta());
												imovel.setSubLote(emitirContaHelper.getSubLoteConta());

												String inscricao = imovel.getInscricaoFormatada();

												imovel = null;

												setorComercial = null;
												quadra = null;

												contaTxt.append(Util.completaString(inscricao, 20));
												contaTxt.append(Util.completaString(" ", 12));

												String mesAnoReferencia = Util.formatarAnoMesParaMesAno(
														emitirContaHelper.getAmReferencia());

												contaTxt.append(Util.completaString(mesAnoReferencia,9));

												// data de vencimento da conta
												String dataVencimento = Util.formatarData(
														emitirContaHelper.getDataVencimentoConta());

												contaTxt.append(Util.completaString(dataVencimento,10));

												String valorContaString = Util.formatarMoedaReal(valorConta);

												// valor da conta
												/*FiltroContaImpressao filtroContaImpressao = new FiltroContaImpressao();
												filtroContaImpressao.adicionarParametro(new ParametroSimples(
																FiltroContaImpressao.ID,
																emitirContaHelper.getIdConta().toString()));
												filtroContaImpressao.adicionarCaminhoParaCarregamentoEntidade("contaTipo");*/

												//System.out.println("Filtro conta Impressao");
												/*Collection<ContaImpressao> cContaIm = getControladorUtil()
													.pesquisar(filtroContaImpressao,ContaImpressao.class.getName());*/

												//System.out.println("Saiu conta Impressao");
												/*ContaImpressao contaImpressao = cContaIm.iterator().next();*/
												
												Integer contaTipo = repositorioFaturamento.consultarContaTipodeContaImpressao(emitirContaHelper.getIdConta()); /*contaImpressao.getContaTipo().getId();*/

												contaTxt.append(Util.completaStringComEspacoAEsquerda(
																		valorContaString,15));

												if (contaTipo.equals(ContaTipo.CONTA_DEBITO_AUTOMATICO)
													|| contaTipo.equals(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP.intValue())) {

													contaTxt.append(Util.completaString("NÃO PODE SER PAGO EM BANCO",65));
													contaTxt.append(Util.completaString("DÉBITO AUTOMÁTICO EM CONTA CORRENTE",65));

												} else {

													contaTxt.append(Util.completaString(" ", 65));
													contaTxt.append(Util.completaString(" ", 65));
												}

												// Mês/Ano referência da conta digito verificador

												contaTxt.append(Util.completaString(dataExtensa,14));

												contaTxt.append(Util.adicionarZerosEsquedaNumero(8,
														imovelEmitido.getId().toString()));

												contaTxt.append(Util.completaString(
													emitirContaHelper.getIdLocalidade().toString(),3));

												contaTxt.append(Util.adicionarZerosEsquedaNumero(3,
													emitirContaHelper.getCodigoSetorComercialConta().toString()));

												contaTxt.append(Util.adicionarZerosEsquedaNumero(3,
													emitirContaHelper.getIdQuadraConta().toString()));

												contaTxt.append(Util.adicionarZerosEsquedaNumero(4,
													emitirContaHelper.getLoteConta().toString()));

												contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(2,
																emitirContaHelper.getSubLoteConta().toString()), 2));
												
												// DIGITO ?????
												contaTxt.append(Util.adicionarZerosEsquedaNumero(1, "0"));

												Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
												Integer tipoLigacao = parmSituacao[0];
												Integer tipoMedicao = parmSituacao[1];

												Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
														emitirContaHelper,tipoMedicao);
												// Leitura Anterior
												String leituraAnterior = "";
												// Leitura Atual
												String leituraAtual = "";
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
														dataLeituraAnterior = Util.formatarData((Date) parmsMedicaoHistorico[3]);
													}

													if (parmsMedicaoHistorico[2] != null) {
														dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
													}

												}
												
												Object[] parmsConsumoHistorico = null;
												
												String consumoMedio = "";
												if (tipoLigacao != null) {
													try {
														
														parmsConsumoHistorico = repositorioMicromedicao
															.obterDadosConsumoConta(emitirContaHelper.getIdImovel(),
															emitirContaHelper.getAmReferencia(),tipoLigacao);

													} catch (ErroRepositorioException e) {
														sessionContext.setRollbackOnly();
														throw new ControladorException("erro.sistema",e);
													}

													if (parmsConsumoHistorico != null) {
														// Consumo médio
														if (parmsConsumoHistorico[2] != null) {
															consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
														}
													}
												}

												// Data Leitura Atual
												contaTxt.append(Util.completaString(dataLeituraAtual,5));
												contaTxt.append(Util.completaString(leituraAnterior,6));

												// Leitura Atual
												contaTxt.append(Util.completaString(leituraAtual,6));

												String diasConsumo = "";

												if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
													diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas(
														(Date) parmsMedicaoHistorico[3],(Date) parmsMedicaoHistorico[2]);
												}

												String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
														emitirContaHelper,tipoMedicao,diasConsumo);
												String consumoFaturamento = parmsConsumo[0];

												// Consumo faturado
												contaTxt.append(Util.completaString(consumoFaturamento,5));
												contaTxt.append(Util.completaString(consumoMedio,5));

												contaTxt.append(Util.completaString(this.obterConsumoAnterior(
													emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
													6, tipoLigacao, tipoMedicao).toString(),12));
												contaTxt.append(Util.completaString(this.obterConsumoAnterior(
													emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
													5, tipoLigacao, tipoMedicao).toString(),12));
												contaTxt.append(Util.completaString(this.obterConsumoAnterior(
                                                    emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
													4,tipoLigacao,tipoMedicao).toString(),12));
												contaTxt.append(Util.completaString(this.obterConsumoAnterior(
													emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
													3,tipoLigacao,tipoMedicao).toString(),12));
												contaTxt.append(Util.completaString(this.obterConsumoAnterior(
													emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
													2,tipoLigacao,tipoMedicao).toString(),12));
												contaTxt.append(Util.completaString(this.obterConsumoAnterior(
													emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
													1,tipoLigacao,tipoMedicao).toString(),12));

												contaTxt.append(Util.completaString(economias, 24));

												ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this
														.obterDebitoImovelOuClienteHelper(emitirContaHelper,sistemaParametro);

												if (obterDebitoImovelOuClienteHelper != null
													&& ((obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null 
													&& !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()) 
													|| (obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null 
													&& !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()))) {
													Collection colecaoContasValores = obterDebitoImovelOuClienteHelper
															.getColecaoContasValores();

													if (colecaoContasValores != null && !colecaoContasValores.isEmpty()) {
														if (colecaoContasValores.size() > 5) {
															contaTxt.append(Util.completaString("HÁ MAIS DE CINCO CONTAS EM ATRASO",40));
														} else {
															String contasAtraso = "";
															for (Iterator iter = colecaoContasValores.iterator(); iter.hasNext();) {
																ContaValoresHelper contasValores = (ContaValoresHelper) iter.next();
																contasAtraso = contasAtraso+ contasValores.
																	getConta().getFormatarAnoMesParaMesAno()+ " ";
															}
															contaTxt.append(Util.completaString(contasAtraso,40));
														}
													} else {
														contaTxt.append(Util.completaString("",40));
													}
												} else {
													contaTxt.append(Util.completaString("",40));
												}

												if (imovelEmitido.getLigacaoAgua() != null) {

													if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {

														if (imovelEmitido.getLigacaoAgua()
															.getHidrometroInstalacaoHistorico().getHidrometro() != null) {

															contaTxt.append(Util.completaString(imovelEmitido.getLigacaoAgua()
															.getHidrometroInstalacaoHistorico().getHidrometro().getNumero(),10));
														} else {
															contaTxt.append(Util.completaString(" ",10));
														}

													} else {
														contaTxt.append(Util.completaString(" ",10));
													}

												} else {
													contaTxt.append(Util.completaString(" ", 10));
												}

												Collection colecaoContaCategoriaConsumoFaixa = null;
												try {

													colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
													.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper.getIdConta());

												} catch (ErroRepositorioException e) {
													throw new ControladorException("erro.sistema", e);
												}

												Integer consumoExcesso = 0;
												Integer consumoMinimo = 0;
												BigDecimal valorExcesso = new BigDecimal("0.0");
												BigDecimal valorMinimo = new BigDecimal("0.0");

												if (colecaoContaCategoriaConsumoFaixa == null
														|| colecaoContaCategoriaConsumoFaixa.isEmpty()) {

													consumoMinimo = emitirContaHelper.getConsumoAgua();
													valorMinimo = emitirContaHelper.getValorAgua();
												} else {
													if (!emitirContaHelper.getConsumoAgua().equals(0)) {
														for (Iterator iter = colecaoContaCategoriaConsumoFaixa
																.iterator(); iter.hasNext();) {

															ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iter.next();
															if (contaCategoriaConsumoFaixa.getConsumoAgua() != null) {
																for (Iterator iteration = colecaoSubCategoria.iterator(); iteration.hasNext();) {
																	Subcategoria subCategoriaEmitir = (Subcategoria) iteration.next();

																	if (contaCategoriaConsumoFaixa.getSubcategoria().getId()
																			.equals(subCategoriaEmitir.getId())) {
																		consumoExcesso = consumoExcesso
																		+ contaCategoriaConsumoFaixa.getConsumoAgua()
																		* subCategoriaEmitir.getQuantidadeEconomias();

																		valorExcesso = valorExcesso.add(contaCategoriaConsumoFaixa.getValorAgua()
																		.multiply(new BigDecimal(subCategoriaEmitir.getQuantidadeEconomias())));
																	}

																}
															}
														}
													}

													valorMinimo = emitirContaHelper.getValorAgua().subtract(valorExcesso);
													consumoMinimo = emitirContaHelper.getConsumoAgua()- consumoExcesso;

												}

												int i = 0;
												BigDecimal valorNullo = new BigDecimal("0.00");
												Integer consumoNullo = new Integer(0);

												if (!valorMinimo.equals(valorNullo)) {
													if (!consumoMinimo.equals(consumoNullo)) {
														contaTxt.append("TARIFA MÍNIMA ÁGUA            "); // 30
														contaTxt.append(Util.completaString(consumoMinimo+ " M3",24));
														contaTxt.append(Util.completaStringComEspacoAEsquerda(
																Util.formatarMoedaReal(valorMinimo),12));
													} else {
														contaTxt.append("TARIFA MÍNIMA ÁGUA            "); // 30
														contaTxt.append(Util.completaString(consumoMinimo+ "   ",24));
														contaTxt.append(Util.completaStringComEspacoAEsquerda(
																Util.formatarMoedaReal(valorMinimo),12));
													}
													i++;
												}

												if (!consumoExcesso.equals(consumoNullo)) {
													contaTxt.append("TARIFA EXCESSO ÁGUA           "); // 30
													contaTxt.append(Util.completaString(consumoExcesso + " M3",24));
													contaTxt.append(Util.completaStringComEspacoAEsquerda(
															Util.formatarMoedaReal(valorExcesso),12));
													i++;
												}

												if (!emitirContaHelper.getPercentualEsgotoConta().equals(valorNullo)) {
													contaTxt.append("TARIFA ESGOTO                 "); // 30
													contaTxt.append(Util.completaString(
														emitirContaHelper.getPercentualEsgotoConta()+ "%",24));
													contaTxt.append(Util.completaStringComEspacoAEsquerda(
														Util.formatarMoedaReal(emitirContaHelper.getValorEsgoto()),12));
													i++;
												}

												if (!emitirContaHelper.getValorCreditos().equals(valorNullo)) {
													contaTxt.append("CRÉDITOS E DESCONTOS          "); // 30
													contaTxt.append(Util.completaString(" ", 24));
													contaTxt.append(Util.completaStringComEspacoAEsquerda(
														Util.formatarMoedaReal(emitirContaHelper.getValorCreditos()),12));
													i++;
												}

												if (!emitirContaHelper.getValorImpostos().equals(valorNullo)) {
													contaTxt.append("IMPOSTOS DEDUZIDOS            "); // 30
													contaTxt.append(Util.completaString(" ", 24));
													contaTxt.append(Util.completaStringComEspacoAEsquerda(
														Util.formatarMoedaReal(emitirContaHelper.getValorImpostos()),12));
													i++;
												}

												// setando os servicos

												Conta contaId = new Conta();
												contaId.setId(emitirContaHelper.getIdConta());

												Collection<DebitoCobradoAgrupadoHelper> cDebitoCobrado = this
														.obterDebitosCobradosContaCAERN(contaId);

												int quantidadeLinhasSobrando = 10 - i;

												if (cDebitoCobrado != null && !cDebitoCobrado.isEmpty()) {

													int quantidadeDebitos = cDebitoCobrado.size();

													if (quantidadeLinhasSobrando >= quantidadeDebitos) {

														for (Iterator iter = cDebitoCobrado.iterator(); iter.hasNext();) {
															DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter.next();

															contaTxt.append(Util.completaString(debitoCobrado.getDescricaoDebitoTipo(),30)); // 30
															contaTxt.append(Util.completaString(debitoCobrado.getNumeroPrestacaoDebito() 
																+ "/" + debitoCobrado.getNumeroPrestacao(),24));
															contaTxt.append(Util.completaStringComEspacoAEsquerda(
																Util.formatarMoedaReal(debitoCobrado.getValorDebito()),12));

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
																contaTxt.append(Util.completaString(debitoCobrado.getDescricaoDebitoTipo(),30)); // 30
																contaTxt.append(Util.completaString(debitoCobrado.getNumeroPrestacaoDebito()
																	+ "/" + debitoCobrado.getNumeroPrestacao(),24));
																contaTxt.append(Util.completaStringComEspacoAEsquerda(
																	Util.formatarMoedaReal(debitoCobrado.getValorDebito()),12));
																i++;
															} else {

																valorAcumulado = valorAcumulado.add(debitoCobrado.getValorDebito());
																temOutros = true;
															}

															contador++;
														}
														if (temOutros) {
															contaTxt.append("OUTROS SERVIÇOS               "); // 30
															contaTxt.append(Util.completaString(" ",24));
															contaTxt.append(Util.completaStringComEspacoAEsquerda(
																Util.formatarMoedaReal(valorAcumulado),12));
															i++;
														}
													}
												}

												int quantidadeLinhasServicosSobraram = 10 - i;
												contaTxt.append(Util.completaString(" ",quantidadeLinhasServicosSobraram * 66));

												// [SB0018 - Gerar Linhas das DemaisContas]
												anoMesString = "" + emitirContaHelper.getAmReferencia();
												// formata ano mes para mes ano
												mesAnoFormatado = anoMesString.substring(4, 6)
														+ anoMesString.substring(0, 4);
												digitoVerificadorConta = new Integer(""
														+ emitirContaHelper.getDigitoVerificadorConta());
												representacaoNumericaCodBarra = null;

												representacaoNumericaCodBarra = this
														.getControladorArrecadacao()
														.obterRepresentacaoNumericaCodigoBarra(
																3,
																valorConta,
																emitirContaHelper.getIdLocalidade(),
																emitirContaHelper.getIdImovel(),
																mesAnoFormatado,
																digitoVerificadorConta,
																null, null,
																null, null,
																null, null,
																null);

												contaTxt.append(Util.completaString(representacaoNumericaCodBarra,48));

												contaTxt.append(Util.completaString(" ", 66)); // Rodapé,
												
												//Alteração por Tiago Moreno - 23/01/2009 - Determinacao judicial (Nitrato)
												
												Conta contaEmitida = new Conta();
												
												contaEmitida.setId(emitirContaHelper.getIdConta());
												
												CreditoRealizado creditoRealizado = repositorioFaturamento.pesquisarCreditoRealizadoNitrato(contaEmitida);

												if (creditoRealizado != null){
													contaTxt.append(Util.completaString("Por decisão judicial de 15/05/08 - proc. 001.07.200202-7, esta conta inclui um desconto de 50% no valor da água. R$" 
															+ Util.completaString(Util.formatarMoedaReal(creditoRealizado.getValorCredito()), 13), 160));
												} else {
													contaTxt.append(Util.completaString(" ", 160));
												}
												
												// rodapé
												// I,
												// rodapé
												// II,
												// rodapé
												// III,

												contasTxtLista.append(contaTxt.toString());

												conta = null;

												StringBuilder teste = new StringBuilder();
												teste.append(contaTxt);

												// PEDRO 19/10/2006
												contaTxt = null;
												// enquanto estiver
												// proximo
												// if
												// (iteratorConta.hasNext())
												// {
												contasTxtLista.append(System.getProperty("line.separator"));
												contadorTeste = contadorTeste + 1;
												System.out.println("===Contador Emitir===>" + contadorTeste);

												// adiciona o id da conta e o sequencial
												// no para serem atualizados
												mapAtualizaSequencial.put(emitirContaHelper.getIdConta(),sequencialImpressao);
											
												
											}// fim do laço que verifica se o
											// helper é diferente de nulo

										}// fim do laço que verifica as 2 contas
									}// fim laço while do iterator do objeto helper
									countOrdem++;
									mapContasDivididas = null;
								}// fim do while que pega os Map ordenados
							}// fim do laço que verifica se o Map está nula
							// fim do laço que verifica se a coleção é nula
						} else {
							flagTerminou = true;
						}

						numeroIndice = numeroIndice + 1000;

						
						repositorioFaturamento.atualizarSequencialContaImpressao(mapAtualizaSequencial);
						mapAtualizaSequencial = null;

					}// fim laço if da paginação

					String idGrupoFaturamento = "G" + faturamentoGrupo.getId();
					String mesReferencia = "_Fat" + anoMesReferenciaFaturamento.toString().substring(4, 6);
					String nomeZip = null;

					nomeZip = "CONTA_N" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";

					BufferedWriter out = null;
					ZipOutputStream zos = null;

					File compactadoTipo = new File(nomeZip + ".zip");
					File leituraTipo = new File(nomeZip + ".txt");

					if (contasTxtLista != null && contasTxtLista.length() != 0) {
						// fim de arquivo
						// ************ TIPO E *************
						zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
						out = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(leituraTipo.getAbsolutePath())));
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
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido

			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new ControladorException("erro.sistema", e);
		}

	}

	public void emitirContasOrgaoPublico(Integer anoMesReferenciaFaturamento,
			FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada,
			int tipoConta, Integer idEmpresa,
			Short indicadorEmissaoExtratoFaturamento)
			throws ControladorException {

		int idUnidadeIniciada = 0;
		// emitir contas

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						(idEmpresa == null ? 0 : idEmpresa));
		try {

			int quantidadeContas = 0;

			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;

			try {

				// recebe todos as contas da lista
				StringBuilder contasTxtLista = null;

				Map<Integer, Integer> mapAtualizaSequencial = null;

				// i=0;Coleção de contas com estouros de consumo
				// i=1;Coleção de contas com baixo consumo
				// i=2;Coleção de contas com débito automático
				// i=3;Coleção de contas com entrega para o cliente
				// responsável
				// i=4;Coleção de contas com entrega para o cliente
				// responsável
				// i=5;Coleção de contas normais

				try {

					boolean flagTerminou = false;
					numeroIndice = 0;
					Integer sequencialImpressao = 0;
					Integer sequencialCarta = 0;
					Collection colecaoConta = null;
					Collection colecaoContaFichaCompensacao = null;
					boolean flagPesquisarFichaCompensacao = false;
					
					contasTxtLista = new StringBuilder();
					// cartasTxtListaConta = new StringBuilder();
					
					SistemaParametro sistemaParametro = getControladorUtil()
						.pesquisarParametrosDoSistema();

					if(sistemaParametro.getValorContaFichaComp() != null && 
						!sistemaParametro.getValorContaFichaComp().equals(new BigDecimal("0.00"))){
						flagPesquisarFichaCompensacao = true;
					}

					while (!flagTerminou) {
						// map que armazena o sequencial e o numero da
						// conta para no final atualizar todos os
						// sequencias
						mapAtualizaSequencial = new HashMap();
						Collection colecaoContaParms = null;
						Collection colecaoContaParmsFichaCompensacao = null;
						
						if(flagPesquisarFichaCompensacao){
	                          flagPesquisarFichaCompensacao = false;
	                          
	                          colecaoContaParmsFichaCompensacao = repositorioFaturamento
	                          		.pesquisarContasFichaCompensacaoEmitirOrgaoPublicoCAERN(
	                          				ContaTipo.CONTA_NORMAL, 
	                          				idEmpresa, anoMesReferenciaFaturamento,
	                          				faturamentoGrupo.getId(),
	                          				sistemaParametro.getValorContaFichaComp());
	                          
	                          colecaoContaFichaCompensacao = formatarEmitirContasHelper(
	                        		  colecaoContaParmsFichaCompensacao, ContaTipo.CONTA_NORMAL); 
//	                          formatarEmitirContasHelperOuFichaCompensacao(colecaoContaParmsFichaCompensacao, tipoConta);
	                          colecaoContaParmsFichaCompensacao = null;
	                          
	                          if (colecaoContaFichaCompensacao != null && !colecaoContaFichaCompensacao.isEmpty()) {
	                              
	                            emitirFichaCompensacao(colecaoContaFichaCompensacao,ContaTipo.CONTA_NORMAL,
	                                      faturamentoGrupo,idEmpresa,anoMesReferenciaFaturamento);
	                              
	                            colecaoContaFichaCompensacao = null;
	                              
	                          }
	                    }
						
						repositorioFaturamento
								.atualizaClienteResponsavelOrgaoPublicoCAERN(anoMesReferenciaFaturamento);

						colecaoContaParms = repositorioFaturamento
								.pesquisarContasEmitirOrgaoPublicoCAERN(
										ContaTipo.CONTA_NORMAL, idEmpresa,
										numeroIndice,
										anoMesReferenciaFaturamento, 1,
										sistemaParametro.getValorContaFichaComp());
						colecaoConta = formatarEmitirContasHelper(
								colecaoContaParms, ContaTipo.CONTA_NORMAL);

						// Inicio do Loop

						if (colecaoConta != null && !colecaoConta.isEmpty()) {

							if (colecaoConta.size() < quantidadeRegistros) {
								flagTerminou = true;
							}

							Map<Integer, Map<EmitirContaHelper, EmitirContaHelper>> 
								mapContasDivididasOrdenada = dividirColecao(colecaoConta);

							colecaoConta = null;

							EmitirContaHelper emitirContaHelper = null;

							if (mapContasDivididasOrdenada != null) {
								int countOrdem = 0;
								while (countOrdem < mapContasDivididasOrdenada
										.size()) {
									Map<EmitirContaHelper, EmitirContaHelper> mapContasDivididas = mapContasDivididasOrdenada
											.get(countOrdem);

									Iterator iteratorConta = mapContasDivididas
											.keySet().iterator();

									// int count = 0;
									while (iteratorConta.hasNext()) {

										emitirContaHelper = null;

										int situacao = 0;

										emitirContaHelper = (EmitirContaHelper) iteratorConta
												.next();
										while (situacao < 2) {
											if (situacao == 0) {
												situacao = 1;
												// [SB0020] - Gerar
												// Arquivo
												// TXT
												// das
												// Cartas
												if (tipoConta == 0
														|| tipoConta == 1) {
													sequencialCarta += 1;
												}
												sequencialImpressao += 1;
											} else {
												emitirContaHelper = mapContasDivididas
														.get(emitirContaHelper);
												situacao = 2;
											}

											quantidadeContas++;
											// Só para exibir no console
											// a
											// quantidade de
											// contas

											StringBuilder contaTxt = new StringBuilder();

											if (emitirContaHelper != null) {

												// }
												// count = count + 1;
												// caso o [FS0006 -
												// Verificar
												// espaços
												// para
												// descrição
												// dos
												// serviços e
												// tarifas totalmente
												// preenchido]
												// --Linha 1-- //
												// nome da localidade
												// Inicia a Construcao da String
												String descricaoLocalidade = emitirContaHelper
														.getDescricaoLocalidade();
												contaTxt
														.append(Util
																.completaString(
																		descricaoLocalidade,
																		30));
												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		3,
																		emitirContaHelper
																				.getCodigoSetorComercialConta()
																				.toString()));

												Imovel imovelEmitido = getControladorImovel()
														.pesquisarImovel(
																emitirContaHelper
																		.getIdImovel());

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		2,
																		imovelEmitido
																				.getQuadra()
																				.getRota()
																				.getCodigo()
																				.toString()));

												contaTxt.append(".");

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		4,
																		imovelEmitido
																				.getNumeroSequencialRota()
																				.toString()));

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		8,
																		emitirContaHelper
																				.getIdImovel()
																				.toString()));

												// caso a coleção de
												// contas
												// seja
												// de
												// entrega
												// para
												// o
												// cliente
												// responsável
												if (tipoConta == 3
														|| tipoConta == 4) {
													String nomeClienteUsuario = null;
													if (emitirContaHelper
															.getNomeImovel() != null
															&& !emitirContaHelper
																	.getNomeImovel()
																	.equals("")) {
														nomeClienteUsuario = emitirContaHelper
																.getNomeImovel();

													} else {
														try {
															nomeClienteUsuario = repositorioFaturamento
																	.pesquisarNomeClienteUsuarioConta(emitirContaHelper
																			.getIdConta());

														} catch (ErroRepositorioException e) {
															throw new ControladorException(
																	"erro.sistema",
																	e);
														}
													}
													contaTxt
															.append(Util
																	.completaString(
																			nomeClienteUsuario,
																			30));
												} else {
													contaTxt
															.append(Util
																	.completaString(
																			emitirContaHelper
																					.getNomeCliente(),
																			30));
												}

												String[] enderecoImovel = getControladorEndereco()
														.pesquisarEnderecoFormatadoDividido(
																emitirContaHelper
																		.getIdImovel());

												// endereço
												contaTxt
														.append(Util
																.completaString(
																		enderecoImovel[0],
																		60));

												// bairro
												contaTxt
														.append(Util
																.completaString(
																		enderecoImovel[3],
																		30));



												FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

												filtroLocalidade
														.adicionarParametro(new ParametroSimples(
																FiltroLocalidade.ID,
																emitirContaHelper
																		.getIdLocalidade()));

												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
												filtroLocalidade
														.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");

												Collection cLocalidade = (Collection) getControladorUtil()
														.pesquisar(
																filtroLocalidade,
																Localidade.class
																		.getName());
												Localidade localidade = (Localidade) cLocalidade
														.iterator().next();

												FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

												Collection colecaoQualidadeAgua = null;

												filtroQualidadeAgua
														.adicionarParametro(new ParametroSimples(
																FiltroQualidadeAgua.LOCALIDADE_ID,
																localidade
																		.getId()));
												filtroQualidadeAgua
														.adicionarParametro(new ParametroSimples(
																FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
																emitirContaHelper
																		.getIdSetorComercial()
																		.toString()));
												filtroQualidadeAgua
														.adicionarParametro(new ParametroSimples(
																FiltroQualidadeAgua.ANO_MES_REFERENCIA,
																emitirContaHelper
																		.getAmReferencia()));
												
												filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");

												colecaoQualidadeAgua = getControladorUtil()
														.pesquisar(
																filtroQualidadeAgua,
																QualidadeAgua.class
																		.getName());

												if (colecaoQualidadeAgua == null
														|| colecaoQualidadeAgua
																.isEmpty()) {
													filtroQualidadeAgua
															.limparListaParametros();
													colecaoQualidadeAgua = null;
													filtroQualidadeAgua
															.adicionarParametro(new ParametroSimples(
																	FiltroQualidadeAgua.LOCALIDADE_ID,
																	localidade
																			.getId()));
													
													filtroQualidadeAgua
													.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
													
													filtroQualidadeAgua
															.adicionarParametro(new ParametroSimples(
																	FiltroQualidadeAgua.ANO_MES_REFERENCIA,
																	emitirContaHelper
																			.getAmReferencia()));
													filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
													
													colecaoQualidadeAgua = getControladorUtil()
															.pesquisar(
																	filtroQualidadeAgua,
																	QualidadeAgua.class
																			.getName());
												}
												
												if (colecaoQualidadeAgua == null
														|| colecaoQualidadeAgua
																.isEmpty()) {
													filtroQualidadeAgua
															.limparListaParametros();
													colecaoQualidadeAgua = null;
													
													filtroQualidadeAgua
															.adicionarParametro(new ParametroNulo(
																	FiltroQualidadeAgua.LOCALIDADE_ID));
													
													filtroQualidadeAgua
													.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
													
													filtroQualidadeAgua
															.adicionarParametro(new ParametroSimples(
																	FiltroQualidadeAgua.ANO_MES_REFERENCIA,
																	emitirContaHelper
																			.getAmReferencia()));
													filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
													
													colecaoQualidadeAgua = getControladorUtil()
															.pesquisar(
																	filtroQualidadeAgua,
																	QualidadeAgua.class
																			.getName());
												}												

												if (colecaoQualidadeAgua != null
														&& !colecaoQualidadeAgua
																.isEmpty()) {
													QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua
															.iterator().next();

													// fonte
													if (qualidadeAgua.getFonteCaptacao() != null) {
														contaTxt.append(
															Util.completaString(qualidadeAgua.getFonteCaptacao().getDescricao(),30));
													} else {
														contaTxt.append(Util.completaString(" ",30));
													}
													
													// cloro
													if (qualidadeAgua
															.getNumeroCloroResidual() != null
															&& !qualidadeAgua
																	.getNumeroCloroResidual()
																	.equals(0)) {
														contaTxt
																.append(Util
																		.completaString(
																				qualidadeAgua
																						.getNumeroCloroResidual()
																						.toString(),
																				3));
													} else {
														contaTxt
																.append(Util
																		.completaString(
																				" ",
																				3));
													}

													// coliformes
													if (qualidadeAgua
															.getNumeroIndiceColiformesTotais() != null
															&& !qualidadeAgua
																	.getNumeroIndiceColiformesTotais()
																	.equals(0)) {
														contaTxt
																.append(Util
																		.completaString(
																				qualidadeAgua
																						.getNumeroIndiceColiformesTotais()
																						.toString(),
																				8));
													} else {
														contaTxt
																.append(Util
																		.completaString(
																				" ",
																				8));
													}

													// nitrato
													if (qualidadeAgua
															.getNumeroNitrato() != null
															&& !qualidadeAgua
																	.getNumeroNitrato()
																	.equals(0)) {
														contaTxt
																.append(Util
																		.completaString(
																				qualidadeAgua
																						.getNumeroNitrato()
																						.toString(),
																				4));
													} else {
														contaTxt
																.append(Util
																		.completaString(
																				" ",
																				4));
													}

													// //ph
													if (qualidadeAgua
															.getNumeroIndicePh() != null
															&& !qualidadeAgua
																	.getNumeroIndicePh()
																	.equals(0)) {
														contaTxt
																.append(Util
																		.completaString(
																				qualidadeAgua
																						.getNumeroIndicePh()
																						.toString(),
																				4));
													} else {
														contaTxt
																.append(Util
																		.completaString(
																				" ",
																				4));
													}

													// //turbidez
													if (qualidadeAgua
															.getNumeroIndiceTurbidez() != null
															&& !qualidadeAgua
																	.getNumeroIndiceTurbidez()
																	.equals(0)) {
														contaTxt
																.append(Util
																		.completaString(
																				qualidadeAgua
																						.getNumeroIndiceTurbidez()
																						.toString(),
																				4));
													} else {
														contaTxt
																.append(Util
																		.completaString(
																				" ",
																				4));
													}

												} else {
													contaTxt.append(Util
															.completaString(
																	" ", 53));
												}

												Collection colecaoSubCategoria = getControladorImovel()
														.obterQuantidadeEconomiasSubCategoria(
																imovelEmitido
																		.getId());

												String economias = "";

												for (Iterator iter = colecaoSubCategoria
														.iterator(); iter
														.hasNext();) {
													Subcategoria subcategoria = (Subcategoria) iter
															.next();

													economias = economias
															+ Util
																	.adicionarZerosEsquedaNumero(
																			3,
																			subcategoria
																					.getCodigo()
																					+ "")
															+ "/"
															+ Util
																	.adicionarZerosEsquedaNumero(
																			3,
																			subcategoria
																					.getQuantidadeEconomias()
																					.toString())
															+ " ";

												}

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		7,
																		quantidadeContas
																				+ ""));

												contaTxt
														.append(Util
																.completaString(
																		localidade
																				.getDescricao(),
																		30));

												contaTxt
														.append(Util
																.completaString(
																		localidade
																				.getEnderecoFormatadoTituloAbreviado(),
																		35));

												contaTxt
														.append(Util
																.completaString(
																		localidade
																				.getFone(),
																		20));

												contaTxt.append(Util
														.completaString(
																"0800-84-0195",
																15));

												// cria um objeto conta
												// para
												// calcular o
												// valor da
												// conta
												Conta conta = new Conta();
												conta
														.setValorAgua(emitirContaHelper
																.getValorAgua());
												conta
														.setValorEsgoto(emitirContaHelper
																.getValorEsgoto());
												conta
														.setValorCreditos(emitirContaHelper
																.getValorCreditos());
												conta
														.setDebitos(emitirContaHelper
																.getDebitos());
												conta
														.setValorImposto(emitirContaHelper
																.getValorImpostos());

												BigDecimal valorConta = conta
														.getValorTotalContaBigDecimal();
												emitirContaHelper.setValorConta(valorConta);

												// [SB0018 - Gerar
												// Linhas
												// das
												// DemaisContas]
												String anoMesString = ""
														+ emitirContaHelper
																.getAmReferencia();
												// formata ano mes para mes ano

												String mesNumero = anoMesString
														.substring(4, 6);

												String mesExtenso = Util
														.retornaDescricaoMes(
																new Integer(
																		mesNumero)
																		.intValue())
														.toUpperCase();
												String dataExtensa = mesExtenso
														+ "/"
														+ anoMesString
																.substring(0, 4);

												String mesAnoFormatado = anoMesString
														.substring(4, 6)
														+ anoMesString
																.substring(0, 4);
												Integer digitoVerificadorConta = new Integer(
														""
																+ emitirContaHelper
																		.getDigitoVerificadorConta());
												String representacaoNumericaCodBarra = null;

												representacaoNumericaCodBarra = getControladorArrecadacao()
														.obterRepresentacaoNumericaCodigoBarra(
																3,
																valorConta,
																emitirContaHelper
																		.getIdLocalidade(),
																emitirContaHelper
																		.getIdImovel(),
																mesAnoFormatado,
																digitoVerificadorConta,
																null, null,
																null, null,
																null, null,
																null);

												contaTxt
														.append(Util
																.completaString(
																		representacaoNumericaCodBarra,
																		48));

												// determinar Mensagem
												String[] parmsPartesConta = obterMensagemConta3Partes(
														emitirContaHelper,
														sistemaParametro);

												String primeiraParte = parmsPartesConta[0];
												String segundaParte = parmsPartesConta[1];

												contaTxt.append(Util
														.completaString(
																primeiraParte,
																65));
												contaTxt.append(Util
														.completaString(
																segundaParte,
																65));

												contaTxt
														.append(System
																.getProperty("line.separator"));

												contaTxt
														.append(Util
																.completaString(
																		descricaoLocalidade,
																		30));

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		2,
																		imovelEmitido
																				.getQuadra()
																				.getRota()
																				.getCodigo()
																				.toString()));

												contaTxt.append(".");

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		4,
																		imovelEmitido
																				.getNumeroSequencialRota()
																				.toString()));

												Imovel imovel = new Imovel();
												Localidade localidade2 = new Localidade();
												localidade2
														.setId(emitirContaHelper
																.getIdLocalidade());
												imovel
														.setLocalidade(localidade2);
												SetorComercial setorComercial = new SetorComercial();
												setorComercial
														.setCodigo(emitirContaHelper
																.getCodigoSetorComercialConta());
												imovel
														.setSetorComercial(setorComercial);
												Quadra quadra = new Quadra();
												quadra
														.setNumeroQuadra(emitirContaHelper
																.getIdQuadraConta());
												imovel.setQuadra(quadra);
												imovel
														.setLote(emitirContaHelper
																.getLoteConta());
												imovel
														.setSubLote(emitirContaHelper
																.getSubLoteConta());

												String inscricao = imovel
														.getInscricaoFormatada();

												imovel = null;

												setorComercial = null;
												quadra = null;

												contaTxt.append(Util
														.completaString(
																inscricao, 20));
												contaTxt
														.append(Util
																.completaString(
																		" ", 12));

												String mesAnoReferencia = Util
														.formatarAnoMesParaMesAno(emitirContaHelper
																.getAmReferencia());

												contaTxt
														.append(Util
																.completaString(
																		mesAnoReferencia,
																		9));

												// data de vencimento da
												// conta
												String dataVencimento = Util
														.formatarData(emitirContaHelper
																.getDataVencimentoConta());

												contaTxt.append(Util
														.completaString(
																dataVencimento,
																10));

												String valorContaString = Util
														.formatarMoedaReal(valorConta);

												// valor da conta

												FiltroContaImpressao filtroContaImpressao = new FiltroContaImpressao();
												filtroContaImpressao
														.adicionarParametro(new ParametroSimples(
																FiltroContaImpressao.ID,
																emitirContaHelper
																		.getIdConta()
																		.toString()));
												filtroContaImpressao
														.adicionarCaminhoParaCarregamentoEntidade("contaTipo");

												Collection<ContaImpressao> cContaIm = getControladorUtil()
														.pesquisar(
																filtroContaImpressao,
																ContaImpressao.class
																		.getName());

												ContaImpressao contaImpressao = cContaIm
														.iterator().next();
												Integer contaTipo = contaImpressao
														.getContaTipo().getId();

												contaTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		valorContaString,
																		15));

												if (contaTipo
														.equals(ContaTipo.CONTA_DEBITO_AUTOMATICO)
														|| contaTipo
																.equals(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP
																		.intValue())) {

													contaTxt
															.append(Util
																	.completaString(
																			"NÃO PODE SER PAGO EM BANCO",
																			65));
													contaTxt
															.append(Util
																	.completaString(
																			"DÉBITO AUTOMÁTICO EM CONTA CORRENTE",
																			65));

												} else {

													contaTxt.append(Util
															.completaString(
																	" ", 65));
													contaTxt.append(Util
															.completaString(
																	" ", 65));
												}

												// Mês/Ano referência da
												// conta
												// digito
												// verificador

												contaTxt
														.append(Util
																.completaString(
																		dataExtensa,
																		14));

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		8,
																		imovelEmitido
																				.getId()
																				.toString()));

												contaTxt
														.append(Util
																.completaString(
																		emitirContaHelper
																				.getIdLocalidade()
																				.toString(),
																		3));

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		3,
																		emitirContaHelper
																				.getCodigoSetorComercialConta()
																				.toString()));

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		3,
																		emitirContaHelper
																				.getIdQuadraConta()
																				.toString()));

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		4,
																		emitirContaHelper
																				.getLoteConta()
																				.toString()));

												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		2,
																		emitirContaHelper
																				.getSubLoteConta()
																				.toString()));
												// DIGITO ?????
												contaTxt
														.append(Util
																.adicionarZerosEsquedaNumero(
																		1, "0"));

												Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
												Integer tipoLigacao = parmSituacao[0];
												Integer tipoMedicao = parmSituacao[1];

												Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
														emitirContaHelper,
														tipoMedicao);
												// Leitura Anterior
												String leituraAnterior = "";
												// Leitura Atual
												String leituraAtual = "";
												// Data Leitura Anterior
												String dataLeituraAnterior = "";
												// Leitura Anterior
												String dataLeituraAtual = "";

												if (parmsMedicaoHistorico != null) {

													if (parmsMedicaoHistorico[0] != null) {
														leituraAnterior = ""
																+ (Integer) parmsMedicaoHistorico[0];
													}

													if (parmsMedicaoHistorico[1] != null) {
														leituraAtual = ""
																+ (Integer) parmsMedicaoHistorico[1];
													}

													if (parmsMedicaoHistorico[3] != null) {
														dataLeituraAnterior = Util
																.formatarData((Date) parmsMedicaoHistorico[3]);
													}

													if (parmsMedicaoHistorico[2] != null) {
														dataLeituraAtual = Util
																.formatarData((Date) parmsMedicaoHistorico[2]);
													}

												}
												Object[] parmsConsumoHistorico = null;
												String consumoMedio = "";
												if (tipoLigacao != null) {
													try {
														parmsConsumoHistorico = repositorioMicromedicao
																.obterDadosConsumoConta(
																		emitirContaHelper
																				.getIdImovel(),
																		emitirContaHelper
																				.getAmReferencia(),
																		tipoLigacao);

													} catch (ErroRepositorioException e) {
														sessionContext
																.setRollbackOnly();
														throw new ControladorException(
																"erro.sistema",
																e);
													}

													if (parmsConsumoHistorico != null) {
														// Consumo médio
														if (parmsConsumoHistorico[2] != null) {
															consumoMedio = ""
																	+ (Integer) parmsConsumoHistorico[2];
														}
													}
												}

												// Data Leitura Atual
												contaTxt
														.append(Util
																.completaString(
																		dataLeituraAtual,
																		5));
												contaTxt
														.append(Util
																.completaString(
																		leituraAnterior,
																		6));

												// Leitura Atual
												contaTxt
														.append(Util
																.completaString(
																		leituraAtual,
																		6));

												String diasConsumo = "";

												if (!dataLeituraAnterior
														.equals("")
														&& !dataLeituraAtual
																.equals("")) {
													diasConsumo = ""
															+ Util
																	.obterQuantidadeDiasEntreDuasDatas(
																			(Date) parmsMedicaoHistorico[3],
																			(Date) parmsMedicaoHistorico[2]);
												}

												String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
														emitirContaHelper,
														tipoMedicao,
														diasConsumo);
												String consumoFaturamento = parmsConsumo[0];

												// Consumo faturado
												contaTxt
														.append(Util
																.completaString(
																		consumoFaturamento,
																		5));
												contaTxt
														.append(Util
																.completaString(
																		consumoMedio,
																		5));

												contaTxt
														.append(Util
																.completaString(
																		this
																				.obterConsumoAnterior(
																						emitirContaHelper
																								.getIdImovel(),
																						emitirContaHelper
																								.getAmReferencia(),
																						6,
																						tipoLigacao,
																						tipoMedicao)
																				.toString(),
																		12));
												contaTxt
														.append(Util
																.completaString(
																		this
																				.obterConsumoAnterior(
																						emitirContaHelper
																								.getIdImovel(),
																						emitirContaHelper
																								.getAmReferencia(),
																						5,
																						tipoLigacao,
																						tipoMedicao)
																				.toString(),
																		12));
												contaTxt
														.append(Util
																.completaString(
																		this
																				.obterConsumoAnterior(
																						emitirContaHelper
																								.getIdImovel(),
																						emitirContaHelper
																								.getAmReferencia(),
																						4,
																						tipoLigacao,
																						tipoMedicao)
																				.toString(),
																		12));
												contaTxt
														.append(Util
																.completaString(
																		this
																				.obterConsumoAnterior(
																						emitirContaHelper
																								.getIdImovel(),
																						emitirContaHelper
																								.getAmReferencia(),
																						3,
																						tipoLigacao,
																						tipoMedicao)
																				.toString(),
																		12));
												contaTxt
														.append(Util
																.completaString(
																		this
																				.obterConsumoAnterior(
																						emitirContaHelper
																								.getIdImovel(),
																						emitirContaHelper
																								.getAmReferencia(),
																						2,
																						tipoLigacao,
																						tipoMedicao)
																				.toString(),
																		12));
												contaTxt
														.append(Util
																.completaString(
																		this
																				.obterConsumoAnterior(
																						emitirContaHelper
																								.getIdImovel(),
																						emitirContaHelper
																								.getAmReferencia(),
																						1,
																						tipoLigacao,
																						tipoMedicao)
																				.toString(),
																		12));

												contaTxt.append(Util
														.completaString(
																economias, 24));

												ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this
														.obterDebitoImovelOuClienteHelper(
																emitirContaHelper,
																sistemaParametro);

												if (obterDebitoImovelOuClienteHelper != null
														&& ((obterDebitoImovelOuClienteHelper
																.getColecaoGuiasPagamentoValores() != null && !obterDebitoImovelOuClienteHelper
																.getColecaoGuiasPagamentoValores()
																.isEmpty()) || (obterDebitoImovelOuClienteHelper
																.getColecaoContasValores() != null && !obterDebitoImovelOuClienteHelper
																.getColecaoContasValores()
																.isEmpty()))) {
													Collection colecaoContasValores = obterDebitoImovelOuClienteHelper
															.getColecaoContasValores();

													if (colecaoContasValores != null
															&& !colecaoContasValores
																	.isEmpty()) {
														if (colecaoContasValores
																.size() > 5) {
															contaTxt
																	.append(Util
																			.completaString(
																					"HÁ MAIS DE CINCO CONTAS EM ATRASO",
																					40));
														} else {
															String contasAtraso = "";
															for (Iterator iter = colecaoContasValores
																	.iterator(); iter
																	.hasNext();) {
																ContaValoresHelper contasValores = (ContaValoresHelper) iter
																		.next();
																contasAtraso = contasAtraso
																		+ contasValores
																				.getConta()
																				.getFormatarAnoMesParaMesAno()
																		+ " ";
															}
															contaTxt
																	.append(Util
																			.completaString(
																					contasAtraso,
																					40));
														}
													} else {
														contaTxt
																.append(Util
																		.completaString(
																				"",
																				40));
														/*contaTxt
														.append(Util
																.completaString(
																		"PARABÉNS... NÃO CONSTA DÉBITOS!",
																		40));*/
													}
												} else {
													contaTxt
													.append(Util
															.completaString(
																	"",
																	40));
												}

												if (imovelEmitido
														.getLigacaoAgua() != null) {

													if (imovelEmitido
															.getLigacaoAgua()
															.getHidrometroInstalacaoHistorico() != null) {

														if (imovelEmitido
																.getLigacaoAgua()
																.getHidrometroInstalacaoHistorico()
																.getHidrometro() != null) {

															contaTxt
																	.append(Util
																			.completaString(
																					imovelEmitido
																							.getLigacaoAgua()
																							.getHidrometroInstalacaoHistorico()
																							.getHidrometro()
																							.getNumero(),
																					10));
														} else {
															contaTxt
																	.append(Util
																			.completaString(
																					" ",
																					10));
														}

													} else {
														contaTxt
																.append(Util
																		.completaString(
																				" ",
																				10));
													}

												} else {
													contaTxt.append(Util
															.completaString(
																	" ", 10));
												}

												Collection colecaoContaCategoriaConsumoFaixa = null;
												try {
													colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
															.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
																	.getIdConta());

												} catch (ErroRepositorioException e) {
													throw new ControladorException(
															"erro.sistema", e);
												}

												Integer consumoExcesso = 0;
												Integer consumoMinimo = 0;
												BigDecimal valorExcesso = new BigDecimal(
														"0.0");
												BigDecimal valorMinimo = new BigDecimal(
														"0.0");

												if (colecaoContaCategoriaConsumoFaixa == null
														|| colecaoContaCategoriaConsumoFaixa
																.isEmpty()) {

													consumoMinimo = emitirContaHelper
															.getConsumoAgua();
													valorMinimo = emitirContaHelper
															.getValorAgua();
												} else {
													if (!emitirContaHelper
															.getConsumoAgua()
															.equals(0)) {
														for (Iterator iter = colecaoContaCategoriaConsumoFaixa
																.iterator(); iter
																.hasNext();) {

															ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iter
																	.next();
															if (contaCategoriaConsumoFaixa
																	.getConsumoAgua() != null) {
																for (Iterator iteration = colecaoSubCategoria
																		.iterator(); iteration
																		.hasNext();) {
																	Subcategoria subCategoriaEmitir = (Subcategoria) iteration
																			.next();

																	if (contaCategoriaConsumoFaixa
																			.getSubcategoria()
																			.getId()
																			.equals(
																					subCategoriaEmitir
																							.getId())) {
																		consumoExcesso = consumoExcesso
																				+ contaCategoriaConsumoFaixa
																						.getConsumoAgua()
																				* subCategoriaEmitir
																						.getQuantidadeEconomias();

																		valorExcesso = valorExcesso
																				.add(contaCategoriaConsumoFaixa
																						.getValorAgua()
																						.multiply(
																								new BigDecimal(
																										subCategoriaEmitir
																												.getQuantidadeEconomias())));
																	}

																}
															}
														}
													}

													valorMinimo = emitirContaHelper
															.getValorAgua()
															.subtract(
																	valorExcesso);
													consumoMinimo = emitirContaHelper
															.getConsumoAgua()
															- consumoExcesso;

												}

												int i = 0;
												BigDecimal valorNullo = new BigDecimal(
														"0.00");
												Integer consumoNullo = new Integer(
														0);

												if (!valorMinimo
														.equals(valorNullo)) {
													if (!consumoMinimo
															.equals(consumoNullo)) {
														contaTxt
																.append("TARIFA MÍNIMA ÁGUA            "); // 30
														contaTxt
																.append(Util
																		.completaString(
																				consumoMinimo
																						+ " M3",
																				24));
														contaTxt
																.append(Util
																		.completaStringComEspacoAEsquerda(
																				Util
																						.formatarMoedaReal(valorMinimo),
																				12));
													} else {
														contaTxt
																.append("TARIFA MÍNIMA ÁGUA            "); // 30
														contaTxt
																.append(Util
																		.completaString(
																				consumoMinimo
																						+ "   ",
																				24));
														contaTxt
																.append(Util
																		.completaStringComEspacoAEsquerda(
																				Util
																						.formatarMoedaReal(valorMinimo),
																				12));
													}
													i++;
												}

												if (!consumoExcesso
														.equals(consumoNullo)) {
													contaTxt
															.append("TARIFA EXCESSO ÁGUA           "); // 30
													contaTxt
															.append(Util
																	.completaString(
																			consumoExcesso
																					+ " M3",
																			24));
													contaTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(valorExcesso),
																			12));
													i++;
												}

												if (!emitirContaHelper
														.getPercentualEsgotoConta()
														.equals(valorNullo)) {
													contaTxt
															.append("TARIFA ESGOTO                 "); // 30
													contaTxt
															.append(Util
																	.completaString(
																			emitirContaHelper
																					.getPercentualEsgotoConta()
																					+ "%",
																			24));
													contaTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(emitirContaHelper
																							.getValorEsgoto()),
																			12));
													i++;
												}

												if (!emitirContaHelper
														.getValorCreditos()
														.equals(valorNullo)) {
													contaTxt
														.append("CRÉDITOS E DESCONTOS          "); // 30
													contaTxt.append(Util
															.completaString(
																	" ", 24));
													contaTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(emitirContaHelper
																							.getValorCreditos()),
																			12));
													i++;
												}

												if (!emitirContaHelper
														.getValorImpostos()
														.equals(valorNullo)) {
													contaTxt
															.append("IMPOSTOS DEDUZIDOS            "); // 30
													contaTxt.append(Util
															.completaString(
																	" ", 24));
													contaTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(emitirContaHelper
																							.getValorImpostos()),
																			12));
													i++;
												}

												// setando os servicos

												Conta contaId = new Conta();
												contaId.setId(emitirContaHelper
														.getIdConta());

												Collection<DebitoCobradoAgrupadoHelper> cDebitoCobrado = this
														.obterDebitosCobradosContaCAERN(contaId);

												int quantidadeLinhasSobrando = 10 - i;

												if (cDebitoCobrado != null
														&& !cDebitoCobrado
																.isEmpty()) {

													int quantidadeDebitos = cDebitoCobrado
															.size();

													if (quantidadeLinhasSobrando >= quantidadeDebitos) {

														for (Iterator iter = cDebitoCobrado
																.iterator(); iter
																.hasNext();) {
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
																					24));
															contaTxt
																	.append(Util
																			.completaStringComEspacoAEsquerda(
																					Util
																							.formatarMoedaReal(debitoCobrado
																									.getValorDebito()),
																					12));

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
																						24));
																contaTxt
																		.append(Util
																				.completaStringComEspacoAEsquerda(
																						Util
																								.formatarMoedaReal(debitoCobrado
																										.getValorDebito()),
																						12));
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
																	.append("OUTROS SERVIÇOS               "); // 30
															contaTxt
																	.append(Util
																			.completaString(
																					" ",
																					24));
															contaTxt
																	.append(Util
																			.completaStringComEspacoAEsquerda(
																					Util
																							.formatarMoedaReal(valorAcumulado),
																					12));
															i++;
														}
													}
												}

												int quantidadeLinhasServicosSobraram = 10 - i;
												contaTxt
														.append(Util
																.completaString(
																		" ",
																		quantidadeLinhasServicosSobraram * 66));

												// [SB0018 - Gerar
												// Linhas
												// das
												// DemaisContas]
												anoMesString = ""
														+ emitirContaHelper
																.getAmReferencia();
												// formata ano mes para mes ano
												mesAnoFormatado = anoMesString
														.substring(4, 6)
														+ anoMesString
																.substring(0, 4);
												digitoVerificadorConta = new Integer(
														""
																+ emitirContaHelper
																		.getDigitoVerificadorConta());
												representacaoNumericaCodBarra = null;

												representacaoNumericaCodBarra = getControladorArrecadacao()
														.obterRepresentacaoNumericaCodigoBarra(
																3,
																valorConta,
																emitirContaHelper
																		.getIdLocalidade(),
																emitirContaHelper
																		.getIdImovel(),
																mesAnoFormatado,
																digitoVerificadorConta,
																null, null,
																null, null,
																null, null,
																null);

												contaTxt
														.append(Util
																.completaString(
																		representacaoNumericaCodBarra,
																		48));

												contaTxt
														.append(Util
																.completaString(
																		" ", 66)); // Rodapé,
												
												//Alteração por Tiago Moreno - 23/01/2009 - Determinacao judicial (Nitrato)
												
												Conta contaEmitida = new Conta();
												
												contaEmitida.setId(emitirContaHelper.getIdConta());
												
												CreditoRealizado creditoRealizado = repositorioFaturamento.pesquisarCreditoRealizadoNitrato(contaEmitida);
												
												if (creditoRealizado != null){
													contaTxt.append(Util.completaString("Por decisão judicial de 15/05/08 - proc. 001.07.200202-7, esta conta inclui um desconto de 50% no valor da água. R$" 
															+ Util.completaString(Util.formatarMoedaReal(creditoRealizado.getValorCredito()), 13), 160));
												} else {
													contaTxt.append(Util.completaString(" ", 160));
												}
												
												
												// rodapé
												// I,
												// rodapé
												// II,
												// rodapé
												// III,

												contasTxtLista.append(contaTxt
														.toString());

												conta = null;

												StringBuilder teste = new StringBuilder();
												teste.append(contaTxt);

												// PEDRO 19/10/2006
												contaTxt = null;
												// enquanto estiver
												// proximo
												// if
												// (iteratorConta.hasNext())
												// {
												contasTxtLista
														.append(System
																.getProperty("line.separator"));

												// adiciona o id da
												// conta e o sequencial
												// no para serem
												// atualizados
												mapAtualizaSequencial.put(
														emitirContaHelper
																.getIdConta(),
														sequencialImpressao);
											}// fim do laço que
											// verifica
											// se o
											// helper é diferente de
											// nulo

										}// fim do laço que verifica
										// as 2
										// contas
									}// fim laço while do iterator do
									// objeto
									// helper
									countOrdem++;
									mapContasDivididas = null;
								}// fim do while que pega os Map
								// ordenados
							}// fim do laço que verifica se o Map
							// está
							// nula
							// fim do laço que verifica se a coleção é
							// nula
						} else {
							flagTerminou = true;
						}

						// Fim do Loop Principal

						numeroIndice = numeroIndice + 1000;

						repositorioFaturamento
								.atualizarSequencialContaImpressao(mapAtualizaSequencial);
						mapAtualizaSequencial = null;

					}// fim laço if da paginação

					String nomeZip = null;
					nomeZip = "CONTA_ClientesResponsavel_Emp" + idEmpresa + "-";

					BufferedWriter out = null;
					ZipOutputStream zos = null;

					File compactadoTipo = new File(nomeZip + ".zip");
					File leituraTipo = new File(nomeZip + ".txt");

					if (contasTxtLista != null && contasTxtLista.length() != 0) {
						// fim de arquivo
						// ************ TIPO E *************
						System.out.println("CRIANDO ZIP");
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
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido

			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}
	}

	public String[] obterMensagemConta3Partes(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {

		String[] mensagemContaDivididas = new String[3];
		// Integer anoMesReferenciaFinal =
		// sistemaParametro.getAnoMesFaturamento();
		// int anoMesSubtraido = Util
		// .subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
		
		//	mensagem da conta para a anormalidade de consumo (Baixo Consumo,Auto Consumo e Estouro de Consumo)
		mensagemContaDivididas = obterMensagemAnormalidadeConsumo(emitirContaHelper);
		
		if(mensagemContaDivididas == null || mensagemContaDivididas.equals("")){
			mensagemContaDivididas = new String[3];
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
	
			// Date dataFinalDate = dataVencimentoFinal.getTime();
	
			// converte String em data
			// Date dataVencimento = Util.converteStringParaDate("01/01/1900");
	
			Object[] mensagensConta = null;
			// recupera o id do grupo de faturamento da conta
			Integer idFaturamentoGrupo = emitirContaHelper.getIdFaturamentoGrupo();
			// recupera o id da gerencia regional da conta
			Integer idGerenciaRegional = emitirContaHelper.getIdGerenciaRegional();
			// recupera o id da localidade da conta
			Integer idLocalidade = emitirContaHelper.getIdLocalidade();
			// recupera o id do setor comercial da conta
			Integer idSetorComercial = emitirContaHelper.getIdSetorComercial();
			// caso entre em alguma condição então não entra mais nas outras
			boolean achou = false;
			try {
				// o sistema obtem a mensagem para a conta
				// Caso seja a condição 1
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
	
					// Caso seja a condição 2
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
					// Caso seja a condição 3
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
					// Caso seja a condição 4
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
					// Caso seja a condição 4
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
					// Caso seja a condição 4
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
					// Caso seja a condição 5
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
				// caso não tenha entrado em nenhuma das opções acima
				// então completa a string com espaçõs em branco
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
		String mesAnoFormatado = Util.formatarAnoMesParaMesAno(anoMesSubtraido)
				+ " -";

		// adiciona o mes/ano formatado com o traço

		// caso o tipo de ligação e medição seja diferente de nulo
		if (tipoLigacao != null && tipoMedicao != null) {
			dadosConsumoAnterior
					.append(Util.completaString(mesAnoFormatado, 7));
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
						+ numeroConsumoFaturadoMes, 5));
			} else {
				dadosConsumoAnterior.append(Util.completaString(" ", 5));
			}
			// caso o id dos dados do consumo anterior for diferente de nulo

		} else {
			// senão completa com espaços em branco
			dadosConsumoAnterior.append(Util.completaString("", 12));
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
			// [SB0011] - Gerar Linhas da Tarifa de Água
			StringBuilder linhasTarifaAgua = gerarLinhasTarifaAgua(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao);
			linhasDescricaoServicosTarifasTotal.append(linhasTarifaAgua);
		}
		// caso o valor de água de esgoto seja maior que zero
		if (emitirContaHelper.getValorEsgoto() != null
				&& emitirContaHelper.getValorEsgoto().compareTo(
						new BigDecimal("0.00")) == 1) {

			// [SB0012] - Gerar Linhas da tarifa de Esgoto
			StringBuilder linhasTarifaEsgoto = gerarLinhasTarifaEsgoto(emitirContaHelper);
			// caso a stringBuilder já esteja preenchida
			if (linhasDescricaoServicosTarifasTotal != null
					&& linhasDescricaoServicosTarifasTotal.length() != 0) {
				// caso a stringBuilder já esteja preenchida
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
			// [SB0013] - Gerar Linhas de Débitos Cobrados
			StringBuilder linhasDebitoCobrados = gerarLinhasDebitoCobrados(emitirContaHelper);
			// caso a stringBuilder já esteja preenchida
			if (linhasDescricaoServicosTarifasTotal != null
					&& linhasDescricaoServicosTarifasTotal.length() != 0) {
				// caso a stringBuilder já esteja preenchida
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
		// caso o valor de créditos realizados seja maior que zero
		if (emitirContaHelper.getValorCreditos() != null
				&& emitirContaHelper.getValorCreditos().compareTo(
						new BigDecimal("0.00")) == 1) {
			// [SB0014] - Gerar Linhas de Crédito Realizado
			StringBuilder linhasCreditoRealizados = gerarLinhasCreditosRealizados(emitirContaHelper);

			// caso a stringBuilder já esteja preenchida
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

			// caso a stringBuilder já esteja preenchida
			if (linhasDescricaoServicosTarifasTotal != null
					&& linhasDescricaoServicosTarifasTotal.length() != 0) {
				// caso a stringBuilder já esteja preenchida
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
				// completa com espaços em branco
				linhasTarifaAgua.append(Util.completaString("", 2));
				if (contaCategoria.getQuantidadeEconomia() == 1) {
					linhasTarifaAgua.append("UNIDADE ");
				} else {
					linhasTarifaAgua.append("UNIDADES");
				}
				// completa com espaços em branco
				linhasTarifaAgua.append(Util.completaString("", 102));

				// -- Linha 3 --//
				// Canal
				linhasTarifaAgua.append(" ");
				// Fonte
				linhasTarifaAgua.append("1");
				// completa com espaços em branco
				linhasTarifaAgua.append(" ");
				// caso não existam dados de medição
				if (parmsMedicaoHistorico == null) {
					// Constante
					linhasTarifaAgua.append("TARIFA MÍNIMA");

					// Valor da tarifa mínima de água para a categoria por
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
					// completa com espaços em branco
					linhasTarifaAgua.append(" ");
					// Constante
					linhasTarifaAgua.append("POR UNIDADE");
					// completa com espaços em branco
					linhasTarifaAgua.append(Util.completaString("", 18));
					// Constante
					linhasTarifaAgua.append("MINIMO");
					// completa com espaços em branco
					linhasTarifaAgua.append(Util.completaString("", 11));
					// valor da água para categoria
					String valorAgua = Util.formatarMoedaReal(emitirContaHelper
							.getValorAgua());
					linhasTarifaAgua.append(Util
							.completaStringComEspacoAEsquerda(valorAgua, 17));
					// completa com espaços em branco
					linhasTarifaAgua.append(Util.completaString("", 45));
				} else {
					// recupera a coleção de conta categoria consumo faixa
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
						// Consumo mínimo de água para a categoria por
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
						// completa com espaços em branco
						linhasTarifaAgua.append(" ");
						// Constante
						linhasTarifaAgua.append("M3");
						// completa com espaços em branco
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
						// completa com espaços em branco
						linhasTarifaAgua.append(" ");
						// Constante
						linhasTarifaAgua.append(" (POR UNIDADE)");
						// completa com espaços em branco
						linhasTarifaAgua.append(Util.completaString("", 8));
						// Consumo mínimo de água
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
						// completa com espaços em branco
						linhasTarifaAgua.append(" ");
						// Constante
						linhasTarifaAgua.append("M3");
						// completa com espaços em branco
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
						// completa com espaços em branco
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
							// completa com espaços em branco
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
								// completa com espaços em branco
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
								// completa com espaços em branco
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
								// completa com espaços em branco
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
								// completa com espaços em branco
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
								// completa com espaços em branco
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
								// completa com espaços em branco
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
								// completa com espaços em branco
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



	/**
	 * [UC0120] - Calcular Valores de Água e/ou Esgoto
	 *
	 * Totalizando os valores de água e esgoto por categoria ou subcategoria
	 *
	 * @author Raphael Rossiter
	 * @date 28/05/2008
	 *
	 * @param colecaoCalcularValoresAguaEsgotoHelper
	 * @return Collection<CalcularValoresAguaEsgotoHelper>
	 * @throws ControladorException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoTotalizando(
			Collection colecaoCalcularValoresAguaEsgotoHelper)
			throws ControladorException {

		Collection<CalcularValoresAguaEsgotoHelper> colecaoRetorno = new ArrayList();

		Iterator colecaoCalcularValoresAguaEsgotoHelperIt = 
			colecaoCalcularValoresAguaEsgotoHelper.iterator();
		
		CalcularValoresAguaEsgotoHelper objetoTotalizacao;
		CalcularValoresAguaEsgotoHelper objetoColecao;

		Integer qtdSubcategoria = 
			(Integer) this.getControladorImovel().pesquisarObterQuantidadeSubcategoria();

		Object[][] categoriasTotalizacao = new Object[qtdSubcategoria.intValue()][12];
		
		Integer idCategoria;
		BigDecimal valorAgua, valorEsgoto;
		boolean categoriaDentroArray = false;
		int controleCategoria = 0;

		while (colecaoCalcularValoresAguaEsgotoHelperIt.hasNext()) {

			objetoColecao = 
				(CalcularValoresAguaEsgotoHelper) colecaoCalcularValoresAguaEsgotoHelperIt.next();

			if (categoriasTotalizacao[0][0] == null) {
				
				categoriasTotalizacao[0][0] = objetoColecao.getIdCategoria();
				categoriasTotalizacao[0][1] = objetoColecao.getDescricaoCategoria();
				categoriasTotalizacao[0][2] = objetoColecao.getValorFaturadoAguaCategoria();
				categoriasTotalizacao[0][3] = objetoColecao.getValorFaturadoEsgotoCategoria();
				categoriasTotalizacao[0][4] = objetoColecao.getQuantidadeEconomiasCategoria();
				categoriasTotalizacao[0][5] = objetoColecao.getConsumoFaturadoAguaCategoria();
				categoriasTotalizacao[0][6] = objetoColecao.getConsumoFaturadoEsgotoCategoria();
				categoriasTotalizacao[0][7] = objetoColecao.getFaixaTarifaConsumo();

				categoriasTotalizacao[0][8] = objetoColecao.getValorTarifaMinimaAguaCategoria();
				categoriasTotalizacao[0][9] = objetoColecao.getConsumoMinimoAguaCategoria();
				categoriasTotalizacao[0][10] = objetoColecao.getValorTarifaMinimaEsgotoCategoria();
				categoriasTotalizacao[0][11] = objetoColecao.getConsumoMinimoEsgotoCategoria();

				controleCategoria++;
			} else {

				categoriaDentroArray = false;

				for (int index = 0; index < categoriasTotalizacao.length; index++) {

					if (categoriasTotalizacao[index][0] != null) {

						idCategoria = (Integer) categoriasTotalizacao[index][0];

						if (idCategoria.intValue() == objetoColecao.getIdCategoria().intValue()) {

							valorAgua = (BigDecimal) categoriasTotalizacao[index][2];
							if (valorAgua != null) {
								valorAgua = 
									valorAgua.add(objetoColecao.getValorFaturadoAguaCategoria());
							}
							valorEsgoto = (BigDecimal) categoriasTotalizacao[index][3];

							if (valorEsgoto != null) {
								valorEsgoto = 
									valorEsgoto.add(objetoColecao.getValorFaturadoEsgotoCategoria());
							}
							
							categoriasTotalizacao[index][2] = valorAgua;
							categoriasTotalizacao[index][3] = valorEsgoto;
							categoriaDentroArray = true;
							break;
						}
					} else {
						break;
					}
				}

				if (!categoriaDentroArray) {
					
					categoriasTotalizacao[controleCategoria][0] = objetoColecao.getIdCategoria();
					categoriasTotalizacao[controleCategoria][1] = objetoColecao.getDescricaoCategoria();
					categoriasTotalizacao[controleCategoria][2] = objetoColecao.getValorFaturadoAguaCategoria();
					categoriasTotalizacao[controleCategoria][3] = objetoColecao.getValorFaturadoEsgotoCategoria();
					categoriasTotalizacao[controleCategoria][4] = objetoColecao.getQuantidadeEconomiasCategoria();
					categoriasTotalizacao[controleCategoria][5] = objetoColecao.getConsumoFaturadoAguaCategoria();
					categoriasTotalizacao[controleCategoria][6] = objetoColecao.getConsumoFaturadoEsgotoCategoria();
					categoriasTotalizacao[controleCategoria][7] = objetoColecao.getFaixaTarifaConsumo();

					categoriasTotalizacao[controleCategoria][8] = objetoColecao.getValorTarifaMinimaAguaCategoria();
					categoriasTotalizacao[controleCategoria][9] = objetoColecao.getConsumoMinimoAguaCategoria();
					categoriasTotalizacao[controleCategoria][10] = objetoColecao.getValorTarifaMinimaEsgotoCategoria();
					categoriasTotalizacao[controleCategoria][11] = objetoColecao.getConsumoMinimoEsgotoCategoria();

					controleCategoria++;
				}
			}
		}

		for (int indexArray = 0; indexArray < categoriasTotalizacao.length; indexArray++) {

			if (categoriasTotalizacao[indexArray][0] != null) {

				objetoTotalizacao = new CalcularValoresAguaEsgotoHelper();
				objetoTotalizacao.setIdCategoria((Integer) categoriasTotalizacao[indexArray][0]);
				objetoTotalizacao.setDescricaoCategoria((String) categoriasTotalizacao[indexArray][1]);
				objetoTotalizacao.setValorFaturadoAguaCategoria((BigDecimal) categoriasTotalizacao[indexArray][2]);
				objetoTotalizacao.setValorFaturadoEsgotoCategoria((BigDecimal) categoriasTotalizacao[indexArray][3]);
				objetoTotalizacao.setQuantidadeEconomiasCategoria((Integer) categoriasTotalizacao[indexArray][4]);
				objetoTotalizacao.setConsumoFaturadoAguaCategoria((Integer) categoriasTotalizacao[indexArray][5]);
				objetoTotalizacao.setConsumoFaturadoEsgotoCategoria((Integer) categoriasTotalizacao[indexArray][6]);
				objetoTotalizacao.setFaixaTarifaConsumo((Collection) categoriasTotalizacao[indexArray][7]);

				if (categoriasTotalizacao[indexArray][8] != null) {
					objetoTotalizacao.setValorTarifaMinimaAguaCategoria(
						(BigDecimal) categoriasTotalizacao[indexArray][8]);
				}

				if (categoriasTotalizacao[indexArray][9] != null) {
					objetoTotalizacao.setConsumoMinimoAguaCategoria(
						(Integer) categoriasTotalizacao[indexArray][9]);
				}

				if (categoriasTotalizacao[indexArray][10] != null) {
					objetoTotalizacao.setValorTarifaMinimaEsgotoCategoria(
						(BigDecimal) categoriasTotalizacao[indexArray][10]);
				}

				if (categoriasTotalizacao[indexArray][11] != null) {
					objetoTotalizacao.setConsumoMinimoEsgotoCategoria(
						(Integer) categoriasTotalizacao[indexArray][11]);
				}

				colecaoRetorno.add(objetoTotalizacao);
			}

		}

		return colecaoRetorno;
	}

	/**
	 * Este caso de uso gera os débitos a cobrar referentes aos acréscimos por
	 * impontualidade (multa, juros de mora e atualização monetária)
	 * 
	 * [UC0302] - Gerar Débitos a Cobrar de Acréscimos por Impontualidade Autor:
	 * 
	 * @author Fernanda Paiva, Pedro Alexandre, Raphael Rossiter
	 * @date 20/04/2006, 31/08/2006, 23/04/2007
	 * 
	 * @param rotas
	 * @param indicadorGeracaoMulta
	 * @param indicadorGeracaoJuros
	 * @param indicadorGeracaoAtualizacao
	 * @return
	 * @throws ControladorException
	 */
	/*public Collection gerarDebitosACobrarDeAcrescimosPorImpontualidade(
			Collection rotas, 
			Short indicadorGeracaoMulta,
			Short indicadorGeracaoJuros, 
			Short indicadorGeracaoAtualizacao,
			int idFuncionalidadeIniciada, 
			boolean indicadorEncerrandoArrecadacao) throws ControladorException {

		int idUnidadeIniciada = 0;

		System.out.println("INICIO " + new Date());
		try {

			// -------------------------
			//
			// Registrar o início do processamento da Unidade de
			// Processamento
			// do Batch
			//
			// -------------------------
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA,
							((Rota) Util.retonarObjetoDeColecao(rotas)).getId());

			// cria uma coleção de imóvel por rota
			Collection imoveisPorRota = null;
			Collection colecaoDebitoACobrarInserir = new ArrayList();
			Collection colecaoDebitoACobrarCategoriaInserir = new ArrayList();

			// recupera todos os imóveis da coleção de rotas
			imoveisPorRota = repositorioFaturamento
					.pesquisarImoveisDasQuadrasPorRota(rotas);

			SistemaParametro sistemaParametros = getControladorUtil()
					.pesquisarParametrosDoSistema();

			Integer anoMesReferenciaArrecadacao = sistemaParametros
					.getAnoMesArrecadacao();

			Iterator imovelPorRotaIterator = imoveisPorRota.iterator();

			while (imovelPorRotaIterator.hasNext()) {
				// cria um array de objetos para pegar os parametros de
				// retorno da pesquisa
				Object[] arrayImoveisPorRota = (Object[]) imovelPorRotaIterator
						.next();

				// instancia um imóvel
				Imovel imovel = new Imovel();
				if (arrayImoveisPorRota[0] != null) {
					// seta o id no imovel
					imovel.setId((Integer) arrayImoveisPorRota[0]);
				}


				if (arrayImoveisPorRota[4] != null) {
					// seta o lote no imovel
					imovel.setLote((Short) arrayImoveisPorRota[4]);
				}

				if (arrayImoveisPorRota[5] != null) {
					// seta o sublote no imovel
					imovel.setSubLote((Short) arrayImoveisPorRota[5]);
				}

				Localidade localidade = new Localidade();
				if (arrayImoveisPorRota[1] != null) {
					// instancia uma localidade para ser setado no imóvel
					localidade.setId((Integer) arrayImoveisPorRota[1]);
					imovel.setLocalidade(localidade);
				}

				Quadra quadra = new Quadra();
				if (arrayImoveisPorRota[3] != null) {
					// instancia uma quadra para ser setado no imóvel
					Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
					Integer idQuadra = (Integer) arrayImoveisPorRota[7];
					quadra.setId(idQuadra);
					quadra.setNumeroQuadra(numeroQuadra);
					imovel.setQuadra(quadra);
				}

				Integer setorComercial = null;
				if (arrayImoveisPorRota[2] != null) {
					// instancia um setor comercial para ser setado no imóvel
					setorComercial = (Integer) arrayImoveisPorRota[2];
				}*/

				/*
				 * Colocado por Raphael Rossiter em 31/05/2007
				 */
				/*if (arrayImoveisPorRota[8] != null) {
					imovel
							.setIndicadorDebitoConta((Short) arrayImoveisPorRota[8]);
				}*/

				/**
				 * Item 5.1 [UC0306] - Obter Principal Categoria do Imóvel
				 */
				/*Categoria principalCategoria = getControladorImovel()
						.obterPrincipalCategoriaImovel(imovel.getId());

				boolean flagProximoImovel = false;*/

				/**
				 * Item 5.2 Caso a principal categoria do imóvel esteja
				 * indicando que não deve ser gerado acréscimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=ENCERRAMENTO_ARRECADACAO) da
				 * principal categoria do imóvel e esteja indicando que não está
				 * sendo encerrada a arrecadação , passa para o próximo imóvel.
				 */
				/*if (principalCategoria.getIndicadorCobrancaAcrescimos().equals(
						ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}*/
				/**
				 * Item 5.3 Caso a principal categoria do imóvel esteja
				 * indicando que somente deve ser gerado acréscimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=NAO) da principal categoria do
				 * imóvel, passa para o próximo imóvel.
				 */
				/*if ((principalCategoria.getIndicadorCobrancaAcrescimos()
						.equals(ConstantesSistema.ENCERRAMENTO_ARRECADACAO))
						&& !indicadorEncerrandoArrecadacao) {
					flagProximoImovel = true;
				}*/
				/**
				 * Item 5.4 Caso o imóvel possua cliente responsável, recupera o
				 * indicador de cobrança de acrécimos do cliente responsável
				 * (CLIE_ICCOBRANCAACRESCIMOS)
				 */
				/*Short indicadorCobrancaAcrescimos = getControladorImovel()
						.obterIndicadorGeracaoAcrescimosClienteImovel(
								imovel.getId());*/

				/**
				 * Item 5.4.1 Caso esteja indicado que não de ve ser gerado
				 * acrécimos por impontualidade para o cliente
				 * (CLIE_ICCOBRANCAACRESCIMOS=NAO) , passar para o próximo
				 * imóvel
				 */
				/*if (indicadorCobrancaAcrescimos != null
						&& indicadorCobrancaAcrescimos
								.equals(ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				if (indicadorCobrancaAcrescimos != null
						&& (indicadorCobrancaAcrescimos
								.equals(ConstantesSistema.NAO) && !indicadorEncerrandoArrecadacao)) {
					flagProximoImovel = true;
				}

				if (!flagProximoImovel) {

					Date dataAnoMesReferenciaUltimoDia = Util
							.gerarDataApartirAnoMesRefencia(anoMesReferenciaArrecadacao);

					Collection<Integer> colecaoIdsContasAtualizarIndicadorMulta = new ArrayList();

					// cria uma coleção de contas do imovel
					Collection colecaoContaImovel = null;

					// recupera todas as contas dos imóveis da coleção
					// de rotas
					colecaoContaImovel = repositorioFaturamento
							.obterContasImovel(imovel.getId(),
									DebitoCreditoSituacao.NORMAL,
									DebitoCreditoSituacao.INCLUIDA,
									DebitoCreditoSituacao.RETIFICADA,
									dataAnoMesReferenciaUltimoDia);

					// DEBITO A COBRAR GERAL
					DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
					debitoACobrarGeral
							.setIndicadorHistorico(ConstantesSistema.NAO);
					debitoACobrarGeral.setUltimaAlteracao(new Date());
					// ************************************************************

					DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
					debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);
					CobrancaForma cobrancaForma = new CobrancaForma();
					cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);

					Short numeroPrestacaoDebito = 1;
					Short numeroPrestacaoCobradas = 0;

					if (colecaoContaImovel != null
							|| !colecaoContaImovel.isEmpty()) {

						Iterator contasIterator = colecaoContaImovel.iterator();

						while (contasIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosConta = (Object[]) contasIterator
									.next();

							Integer anoMes = Util
									.recuperaAnoMesDaData((Date) dadosConta[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								Integer idConta = (Integer) dadosConta[0];
								Conta conta = new Conta();
								if (dadosConta[0] != null) {
									// seta o id da conta
									conta.setId((Integer) dadosConta[0]);
								}
								if (dadosConta[1] != null) {
									// seta o ano/mes referencia da
									// conta
									conta
											.setReferencia((Integer) dadosConta[1]);
								}
								if (dadosConta[2] != null) {
									// seta a data de vencimento da
									// conta
									conta
											.setDataVencimentoConta((Date) dadosConta[2]);
								}
								if (dadosConta[3] != null) {
									// seta o valor da água
									conta
											.setValorAgua((BigDecimal) dadosConta[3]);
								}
								if (dadosConta[4] != null) {
									// seta o valor do esgoto
									conta
											.setValorEsgoto((BigDecimal) dadosConta[4]);
								}
								if (dadosConta[5] != null) {
									// seta o valor dos debitos
									conta
											.setDebitos((BigDecimal) dadosConta[5]);
								}
								if (dadosConta[6] != null) {
									// seta o valor dos creditos
									conta
											.setValorCreditos((BigDecimal) dadosConta[6]);
								}
								if (dadosConta[7] != null) {
									// seta o indicador de cobranca da
									// multa
									conta
											.setIndicadorCobrancaMulta((Short) dadosConta[7]);
								}

								// cria uma coleção dos pagamentos da
								// conta
								// com
								// menor
								// data de pagamento
								Date pagamentoContasMenorData = null;
								Integer idArrecadacaoForma = null;

								// recupera todos os pagamentos da conta
								// com
								// menor
								// data de pagamento
								Object[] arrayPagamentoContasMenorData = repositorioFaturamento
										.obterArrecadacaoFormaPagamentoContasMenorData(
												idConta, imovel.getId(), conta
														.getReferencia());

								if (arrayPagamentoContasMenorData != null) {
									idArrecadacaoForma = (Integer) arrayPagamentoContasMenorData[0];
									pagamentoContasMenorData = (Date) arrayPagamentoContasMenorData[1];
								}*/

								/*
								 * Colocado por Raphael Rossiter em 31/05/2007
								 * Só irá calcular o acréscimo caso o imovel e o
								 * pagamento não sejam débito automático
								 */
								/*if (idArrecadacaoForma == null
										|| (idArrecadacaoForma != null && !idArrecadacaoForma
												.equals(ArrecadacaoForma.DEBITO_AUTOMATICO))) {

									boolean indicadorExistePagamentoClassificadoConta = repositorioFaturamento
											.obterIndicadorPagamentosClassificadosContaReferenciaMenorIgualAtual(
													idConta, imovel.getId(),
													conta.getReferencia(),
													anoMesReferenciaArrecadacao);

									CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();

									BigDecimal valorConta = conta
											.getValorAgua().add(
													conta.getValorEsgoto())
											.add(conta.getDebitos()).subtract(
													conta.getValorCreditos());

									// Calcula o valor das multas cobradas
									// para
									// a conta
									BigDecimal valorMultasCobradas = repositorioFaturamento
											.pesquisarValorMultasCobradas(idConta);*/

									/**
									 * Item 5.6.2 Calcular os acrescimos por
									 * impontualidade
									 */
									/*calcularAcrescimoPorImpontualidade = this
											.getControladorCobranca()
											.calcularAcrescimoPorImpontualidade(
													conta.getReferencia(),
													conta
															.getDataVencimentoConta(),
													pagamentoContasMenorData,
													valorConta,
													valorMultasCobradas,
													conta
															.getIndicadorCobrancaMulta(),
													""
															+ sistemaParametros
																	.getAnoMesArrecadacao(),
													conta.getId(),
													ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO);

									DebitoTipo debitoTipo = null;

									Object[] obterDebitoTipo = null;*/

									/**
									 * Item 5.6.3 Caso o indicador de geração de
									 * multa corresponda a sim(1) e o valor da
									 * multa seja maior que que zero. Gera o
									 * débito a cobrar referente a multa.
									 */
									/*if (indicadorGeracaoMulta.intValue() == ConstantesSistema.SIM
											.intValue()
											&& calcularAcrescimoPorImpontualidade
													.getValorMulta().compareTo(
															new BigDecimal(
																	"0.00")) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo
												.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

										obterDebitoTipo = repositorioFaturamento
												.obterDebitoTipo(debitoTipo
														.getId());

										FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
										LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
										if (obterDebitoTipo[0] != null) {
											financiamentoTipo
													.setId((Integer) obterDebitoTipo[0]);
										}

										if (obterDebitoTipo[1] != null) {
											lancamentoItemContabil
													.setId((Integer) obterDebitoTipo[1]);
										}

										DebitoACobrar debitoACobrar = new DebitoACobrar();
										debitoACobrar.setImovel(imovel);
										debitoACobrar
												.setAnoMesCobrancaDebito(sistemaParametros
														.getAnoMesArrecadacao());
										debitoACobrar
												.setAnoMesReferenciaContabil(sistemaParametros
														.getAnoMesFaturamento());
										debitoACobrar
												.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
										debitoACobrar
												.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
										debitoACobrar.setLocalidade(localidade);
										debitoACobrar.setQuadra(quadra);
										debitoACobrar
												.setCodigoSetorComercial(setorComercial);
										debitoACobrar.setNumeroQuadra(quadra
												.getNumeroQuadra());
										debitoACobrar.setNumeroLote(imovel
												.getLote());
										debitoACobrar.setNumeroSubLote(imovel
												.getSubLote());
										debitoACobrar
												.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
										debitoACobrar
												.setRegistroAtendimento(null);
										debitoACobrar.setOrdemServico(null);
										debitoACobrar
												.setDebitoCreditoSituacaoAnterior(null);
										debitoACobrar
												.setParcelamentoGrupo(null);
										debitoACobrar
												.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
										debitoACobrar
												.setCobrancaForma(cobrancaForma);
										debitoACobrar.setDebitoTipo(debitoTipo);
										debitoACobrar
												.setUltimaAlteracao(new Date());
										debitoACobrar
												.setGeracaoDebito(new Date());
										debitoACobrar
												.setAnoMesReferenciaDebito(conta
														.getReferencia());
										debitoACobrar
												.setFinanciamentoTipo(financiamentoTipo);
										debitoACobrar
												.setLancamentoItemContabil(lancamentoItemContabil);
										debitoACobrar
												.setValorDebito(calcularAcrescimoPorImpontualidade
														.getValorMulta());

										// Inseri o débito a cobra geral e
										// recupera o id
										Integer idDebitoACobrarGeral = (Integer) getControladorUtil()
												.inserir(debitoACobrarGeral);
										debitoACobrarGeral
												.setId(idDebitoACobrarGeral);

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										debitoACobrar
												.setId(idDebitoACobrarGeral);
										debitoACobrar
												.setDebitoACobrarGeral(debitoACobrarGeral);
										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(inserirDebitoACobrarCategoriaBatch(
														debitoACobrar,
														debitoACobrar
																.getImovel()));
									}*/// if indicador de geração de multa

									/**
									 * Item 5.6.4 Caso o indicador de geração
									 * dos juros de mora corresponda a sim(1) e
									 * o valor dos juros de mora seja maior que
									 * zero Gera o débito a cobrar referente a
									 * juros de mora e exista pagamento para a
									 * conta com data de pagamento diferente de
									 * nulo e ano/mês referência da arrecadação
									 * do pagamento seja menor ou igual ao
									 * ano/mês de arrecadação corente.
									 */
									/*if (indicadorGeracaoJuros.intValue() == ConstantesSistema.SIM
											.intValue()
											&& calcularAcrescimoPorImpontualidade
													.getValorJurosMora()
													.compareTo(
															new BigDecimal(
																	"0.00")) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.JUROS_MORA);

										obterDebitoTipo = repositorioFaturamento
												.obterDebitoTipo(debitoTipo
														.getId());

										FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
										LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
										if (obterDebitoTipo[0] != null) {
											financiamentoTipo
													.setId((Integer) obterDebitoTipo[0]);
										}
										if (obterDebitoTipo[1] != null) {
											lancamentoItemContabil
													.setId((Integer) obterDebitoTipo[1]);
										}

										DebitoACobrar debitoACobrar = new DebitoACobrar();
										debitoACobrar.setImovel(imovel);
										debitoACobrar
												.setAnoMesCobrancaDebito(sistemaParametros
														.getAnoMesArrecadacao());
										debitoACobrar
												.setAnoMesReferenciaContabil(sistemaParametros
														.getAnoMesFaturamento());
										debitoACobrar
												.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
										debitoACobrar
												.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
										debitoACobrar.setLocalidade(localidade);
										debitoACobrar.setQuadra(quadra);
										debitoACobrar
												.setCodigoSetorComercial(setorComercial);
										debitoACobrar.setNumeroQuadra(quadra
												.getNumeroQuadra());
										debitoACobrar.setNumeroLote(imovel
												.getLote());
										debitoACobrar.setNumeroSubLote(imovel
												.getSubLote());
										debitoACobrar
												.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
										debitoACobrar
												.setRegistroAtendimento(null);
										debitoACobrar.setOrdemServico(null);
										debitoACobrar
												.setDebitoCreditoSituacaoAnterior(null);
										debitoACobrar
												.setParcelamentoGrupo(null);
										debitoACobrar
												.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
										debitoACobrar
												.setCobrancaForma(cobrancaForma);
										debitoACobrar.setDebitoTipo(debitoTipo);
										debitoACobrar
												.setUltimaAlteracao(new Date());
										debitoACobrar
												.setGeracaoDebito(new Date());
										debitoACobrar
												.setAnoMesReferenciaDebito(conta
														.getReferencia());
										debitoACobrar
												.setFinanciamentoTipo(financiamentoTipo);
										debitoACobrar
												.setLancamentoItemContabil(lancamentoItemContabil);
										debitoACobrar
												.setValorDebito(calcularAcrescimoPorImpontualidade
														.getValorJurosMora());

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										// Inseri o débito a cobra geral e
										// recupera o id
										Integer idDebitoACobrarGeral = (Integer) getControladorUtil()
												.inserir(debitoACobrarGeral);
										debitoACobrarGeral
												.setId(idDebitoACobrarGeral);

										debitoACobrar
												.setId(idDebitoACobrarGeral);
										debitoACobrar
												.setDebitoACobrarGeral(debitoACobrarGeral);
										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(inserirDebitoACobrarCategoriaBatch(
														debitoACobrar,
														debitoACobrar
																.getImovel()));
									}*/

									/*
									 * 5.6.5 Caso o indicador de geração de
									 * atualização monetária corresponda a
									 * sim(1) e o valor da atualização monetária
									 * seja maior que zero Gera o débito a
									 * cobrar referente a atualização monetária
									 * e
									 */
									/*if (indicadorGeracaoAtualizacao.intValue() == ConstantesSistema.SIM
											.intValue()
											&& calcularAcrescimoPorImpontualidade
													.getValorAtualizacaoMonetaria()
													.compareTo(
															new BigDecimal(
																	"0.00")) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo
												.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

										obterDebitoTipo = repositorioFaturamento
												.obterDebitoTipo(debitoTipo
														.getId());

										FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
										LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
										if (obterDebitoTipo[0] != null) {
											financiamentoTipo
													.setId((Integer) obterDebitoTipo[0]);
										}
										if (obterDebitoTipo[1] != null) {
											lancamentoItemContabil
													.setId((Integer) obterDebitoTipo[1]);
										}

										DebitoACobrar debitoACobrar = new DebitoACobrar();
										debitoACobrar.setImovel(imovel);
										debitoACobrar
												.setAnoMesCobrancaDebito(sistemaParametros
														.getAnoMesArrecadacao());
										debitoACobrar
												.setAnoMesReferenciaContabil(sistemaParametros
														.getAnoMesFaturamento());
										debitoACobrar
												.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
										debitoACobrar
												.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
										debitoACobrar.setLocalidade(localidade);
										debitoACobrar.setQuadra(quadra);
										debitoACobrar
												.setCodigoSetorComercial(setorComercial);
										debitoACobrar.setNumeroQuadra(quadra
												.getNumeroQuadra());
										debitoACobrar.setNumeroLote(imovel
												.getLote());
										debitoACobrar.setNumeroSubLote(imovel
												.getSubLote());
										debitoACobrar
												.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
										debitoACobrar
												.setRegistroAtendimento(null);
										debitoACobrar.setOrdemServico(null);
										debitoACobrar
												.setDebitoCreditoSituacaoAnterior(null);
										debitoACobrar
												.setParcelamentoGrupo(null);
										debitoACobrar
												.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
										debitoACobrar
												.setCobrancaForma(cobrancaForma);
										debitoACobrar.setDebitoTipo(debitoTipo);
										debitoACobrar
												.setUltimaAlteracao(new Date());
										debitoACobrar
												.setGeracaoDebito(new Date());
										debitoACobrar
												.setAnoMesReferenciaDebito(conta
														.getReferencia());
										debitoACobrar
												.setFinanciamentoTipo(financiamentoTipo);
										debitoACobrar
												.setLancamentoItemContabil(lancamentoItemContabil);
										debitoACobrar
												.setValorDebito(calcularAcrescimoPorImpontualidade
														.getValorAtualizacaoMonetaria());

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										// Inseri o débito a cobra geral e
										// recupera o id
										Integer idDebitoACobrarGeral = (Integer) getControladorUtil()
												.inserir(debitoACobrarGeral);
										debitoACobrarGeral
												.setId(idDebitoACobrarGeral);

										debitoACobrar
												.setId(idDebitoACobrarGeral);
										debitoACobrar
												.setDebitoACobrarGeral(debitoACobrarGeral);
										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(inserirDebitoACobrarCategoriaBatch(
														debitoACobrar,
														debitoACobrar
																.getImovel()));
									}

								} // fim comparacao debito automatico

							} // fim if da comparacao da data de
							// pagamento
						} // fim while contasiterator
					}*/ // fim if colecaoconta

					/*
					 * Item 5.6.3.2 Atualiza o indicador de que já cobrou multa
					 * da conta com o valor igual a SIM (CNTA_ICCOBRANCAMULTA=1)
					 */
					/*if (colecaoIdsContasAtualizarIndicadorMulta != null
							&& !colecaoIdsContasAtualizarIndicadorMulta
									.isEmpty()) {
						repositorioFaturamento
								.atualizarIndicadorMultaDeConta(colecaoIdsContasAtualizarIndicadorMulta);
					}

					// cria uma coleção de guias do imovel
					Collection colecaoGuiasPagamentoImovel = null;

					Collection<Integer> colecaoIdsGuiasPagamentosAtualizarIndicadorMulta = new ArrayList();

					// recupera todas as guias dos imóveis da coleção de
					// rotas
					colecaoGuiasPagamentoImovel = repositorioFaturamento
							.obterGuiasPagamentoImovel(imovel.getId(),
									DebitoCreditoSituacao.NORMAL,
									DebitoCreditoSituacao.INCLUIDA,
									DebitoCreditoSituacao.RETIFICADA,
									anoMesReferenciaArrecadacao);

					if (colecaoGuiasPagamentoImovel != null
							|| !colecaoGuiasPagamentoImovel.isEmpty()) {

						Iterator guiasPagamentoIterator = colecaoGuiasPagamentoImovel
								.iterator();

						while (guiasPagamentoIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosGuiasPagamento = (Object[]) guiasPagamentoIterator
									.next();

							Integer anoMes = Util
									.recuperaAnoMesDaData((Date) dadosGuiasPagamento[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								GuiaPagamento guiaPagamento = new GuiaPagamento();
								if (dadosGuiasPagamento[0] != null) {
									// seta o id da guia
									guiaPagamento
											.setId((Integer) dadosGuiasPagamento[0]);
								}
								if (dadosGuiasPagamento[1] != null) {
									// seta o ano/mes referencia da guia
									guiaPagamento
											.setAnoMesReferenciaContabil((Integer) dadosGuiasPagamento[1]);
								}
								if (dadosGuiasPagamento[2] != null) {
									// seta a data de vencimento da
									// conta
									guiaPagamento
											.setDataVencimento((Date) dadosGuiasPagamento[2]);
								}
								if (dadosGuiasPagamento[3] != null) {
									// seta o valor dos debitos
									guiaPagamento
											.setValorDebito((BigDecimal) dadosGuiasPagamento[3]);
								}
								if (dadosGuiasPagamento[4] != null) {
									// seta o indicador de cobranca da
									// multa
									guiaPagamento
											.setIndicadoCobrancaMulta((Short) dadosGuiasPagamento[4]);
								}

								DebitoTipo debitoTipoGuiaPagamento = new DebitoTipo();
								if (dadosGuiasPagamento[5] != null) {
									debitoTipoGuiaPagamento
											.setId((Integer) dadosGuiasPagamento[5]);
									guiaPagamento
											.setDebitoTipo(debitoTipoGuiaPagamento);
								}

								Date menorDataPagamento = repositorioCobranca
										.pesquisarMenorDataPagamentoGuiaPagamento(
												guiaPagamento.getId(), imovel
														.getId(), guiaPagamento
														.getDebitoTipo()
														.getId());

								boolean indicadorExistePagamentoClassificadoGuiaPagamento = repositorioFaturamento
										.obterIndicadorPagamentosClassificadosGuiaPagamentoReferenciaMenorIgualAtual(
												guiaPagamento.getId(), imovel
														.getId(), guiaPagamento
														.getDebitoTipo()
														.getId(),
												anoMesReferenciaArrecadacao);

								// [UC0216] Calcular Acréscimos por
								// Impontualidade
								CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();
								calcularAcrescimoPorImpontualidade = this
										.getControladorCobranca()
										.calcularAcrescimoPorImpontualidade(
												guiaPagamento
														.getAnoMesReferenciaContabil(),
												guiaPagamento
														.getDataVencimento(),
												menorDataPagamento,
												guiaPagamento.getValorDebito(),
												BigDecimal.ZERO,
												guiaPagamento
														.getIndicadoCobrancaMulta(),
												""
														+ sistemaParametros
																.getAnoMesArrecadacao(),
												null,
												ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO);

								DebitoTipo debitoTipo = null;

								Object[] obterDebitoTipo = null;*/

								/*
								 * Item 5.8.3 Caso o indicador de geração de
								 * multa corresponda a sim(1) e o valor da multa
								 * seja maior que que zero. Gera o débito a
								 * cobrar referente a multa.
								 */
								/*if (indicadorGeracaoMulta.intValue() == ConstantesSistema.SIM
										.intValue()
										&& calcularAcrescimoPorImpontualidade
												.getValorMulta().compareTo(
														new BigDecimal("0.00")) == 1) {

									debitoTipo = new DebitoTipo();

									debitoTipo
											.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

									obterDebitoTipo = repositorioFaturamento
											.obterDebitoTipo(debitoTipo.getId());

									FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
									LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
									if (obterDebitoTipo[0] != null) {
										financiamentoTipo
												.setId((Integer) obterDebitoTipo[0]);
									}
									if (obterDebitoTipo[1] != null) {
										lancamentoItemContabil
												.setId((Integer) obterDebitoTipo[1]);
									}

									DebitoACobrar debitoACobrar = new DebitoACobrar();
									debitoACobrar.setImovel(imovel);
									debitoACobrar
											.setAnoMesCobrancaDebito(sistemaParametros
													.getAnoMesArrecadacao());
									debitoACobrar
											.setAnoMesReferenciaContabil(sistemaParametros
													.getAnoMesFaturamento());
									debitoACobrar
											.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
									debitoACobrar
											.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
									debitoACobrar.setLocalidade(localidade);
									debitoACobrar.setQuadra(quadra);
									debitoACobrar
											.setCodigoSetorComercial(setorComercial);
									debitoACobrar.setNumeroQuadra(quadra
											.getNumeroQuadra());
									debitoACobrar.setNumeroLote(imovel
											.getLote());
									debitoACobrar.setNumeroSubLote(imovel
											.getSubLote());
									debitoACobrar
											.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
									debitoACobrar.setRegistroAtendimento(null);
									debitoACobrar.setOrdemServico(null);
									debitoACobrar
											.setDebitoCreditoSituacaoAnterior(null);
									debitoACobrar.setParcelamentoGrupo(null);
									debitoACobrar
											.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
									debitoACobrar
											.setCobrancaForma(cobrancaForma);
									debitoACobrar.setDebitoTipo(debitoTipo);
									debitoACobrar
											.setUltimaAlteracao(new Date());
									debitoACobrar.setGeracaoDebito(new Date());
									debitoACobrar
											.setAnoMesReferenciaDebito(guiaPagamento
													.getAnoMesReferenciaContabil());
									debitoACobrar
											.setFinanciamentoTipo(financiamentoTipo);
									debitoACobrar
											.setLancamentoItemContabil(lancamentoItemContabil);
									debitoACobrar
											.setValorDebito(calcularAcrescimoPorImpontualidade
													.getValorMulta());

									// repositorioFaturamento.atualizarIndicadorMultaDeGuiaPagamento(guiaPagamento.getId());
									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									// Inseri o débito a cobra geral e
									// recupera o id
									Integer idDebitoACobrarGeral = (Integer) getControladorUtil()
											.inserir(debitoACobrarGeral);
									debitoACobrarGeral
											.setId(idDebitoACobrarGeral);

									debitoACobrar.setId(idDebitoACobrarGeral);
									debitoACobrar
											.setDebitoACobrarGeral(debitoACobrarGeral);
									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(inserirDebitoACobrarCategoriaBatch(
													debitoACobrar,
													debitoACobrar.getImovel()));
								}*/

								/*
								 * Item 5.8.4 Caso o indicador de geração dos
								 * juros de mora corresponda a sim(1) e o valor
								 * dos juros de mora seja maior que zero e
								 * exista pagamento para a guia de pagamento com
								 * situação atual igual a classificado. Gera o
								 * débito a cobrar referente a juros de mora.
								 */
								/*if (indicadorGeracaoJuros.intValue() == ConstantesSistema.SIM
										.intValue()
										&& calcularAcrescimoPorImpontualidade
												.getValorJurosMora().compareTo(
														new BigDecimal("0.00")) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.JUROS_MORA);

									obterDebitoTipo = repositorioFaturamento
											.obterDebitoTipo(debitoTipo.getId());

									FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
									LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
									if (obterDebitoTipo[0] != null) {
										financiamentoTipo
												.setId((Integer) obterDebitoTipo[0]);
									}
									if (obterDebitoTipo[1] != null) {
										lancamentoItemContabil
												.setId((Integer) obterDebitoTipo[1]);
									}

									DebitoACobrar debitoACobrar = new DebitoACobrar();
									debitoACobrar.setImovel(imovel);
									debitoACobrar
											.setAnoMesCobrancaDebito(sistemaParametros
													.getAnoMesArrecadacao());
									debitoACobrar
											.setAnoMesReferenciaContabil(sistemaParametros
													.getAnoMesFaturamento());
									debitoACobrar
											.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
									debitoACobrar
											.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
									debitoACobrar.setLocalidade(localidade);
									debitoACobrar.setQuadra(quadra);
									debitoACobrar
											.setCodigoSetorComercial(setorComercial);
									debitoACobrar.setNumeroQuadra(quadra
											.getNumeroQuadra());
									debitoACobrar.setNumeroLote(imovel
											.getLote());
									debitoACobrar.setNumeroSubLote(imovel
											.getSubLote());
									debitoACobrar
											.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
									debitoACobrar.setRegistroAtendimento(null);
									debitoACobrar.setOrdemServico(null);
									debitoACobrar
											.setDebitoCreditoSituacaoAnterior(null);
									debitoACobrar.setParcelamentoGrupo(null);
									debitoACobrar
											.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
									debitoACobrar
											.setCobrancaForma(cobrancaForma);
									debitoACobrar.setDebitoTipo(debitoTipo);
									debitoACobrar
											.setUltimaAlteracao(new Date());
									debitoACobrar.setGeracaoDebito(new Date());
									debitoACobrar
											.setAnoMesReferenciaDebito(guiaPagamento
													.getAnoMesReferenciaContabil());
									debitoACobrar
											.setFinanciamentoTipo(financiamentoTipo);
									debitoACobrar
											.setLancamentoItemContabil(lancamentoItemContabil);
									debitoTipo.setId(DebitoTipo.JUROS_MORA);
									debitoACobrar
											.setValorDebito(calcularAcrescimoPorImpontualidade
													.getValorJurosMora());

									// repositorioFaturamento.atualizarIndicadorMultaDeGuiaPagamento(guiaPagamento.getId());
									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									// Inseri o débito a cobra geral e
									// recupera o id
									Integer idDebitoACobrarGeral = (Integer) getControladorUtil()
											.inserir(debitoACobrarGeral);
									debitoACobrarGeral
											.setId(idDebitoACobrarGeral);

									// debitoACobrar.setId(idDebitoACobrar);
									debitoACobrar.setId(idDebitoACobrarGeral);
									debitoACobrar
											.setDebitoACobrarGeral(debitoACobrarGeral);
									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(inserirDebitoACobrarCategoriaBatch(
													debitoACobrar,
													debitoACobrar.getImovel()));
								}*/

								/*
								 * Item 5.8.5 Caso o indicador de geração de
								 * atualização monetária corresponda a sim(1) e
								 * o valor da atualização monetária seja maior
								 * que zero e exista pagamento para a guia de
								 * pagamento com situação atual igual a
								 * classificado. Gera o débito a cobrar
								 * referente a atualização monetária.
								 */
								/*if (indicadorGeracaoAtualizacao.intValue() == ConstantesSistema.SIM
										.intValue()
										&& calcularAcrescimoPorImpontualidade
												.getValorAtualizacaoMonetaria()
												.compareTo(
														new BigDecimal("0.00")) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo
											.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

									obterDebitoTipo = repositorioFaturamento
											.obterDebitoTipo(debitoTipo.getId());

									FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
									LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
									if (obterDebitoTipo[0] != null) {
										financiamentoTipo
												.setId((Integer) obterDebitoTipo[0]);
									}
									if (obterDebitoTipo[1] != null) {
										lancamentoItemContabil
												.setId((Integer) obterDebitoTipo[1]);
									}

									DebitoACobrar debitoACobrar = new DebitoACobrar();
									debitoACobrar.setImovel(imovel);
									debitoACobrar
											.setAnoMesCobrancaDebito(sistemaParametros
													.getAnoMesArrecadacao());
									debitoACobrar
											.setAnoMesReferenciaContabil(sistemaParametros
													.getAnoMesFaturamento());
									debitoACobrar
											.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
									debitoACobrar
											.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
									debitoACobrar.setLocalidade(localidade);
									debitoACobrar.setQuadra(quadra);
									debitoACobrar
											.setCodigoSetorComercial(setorComercial);
									debitoACobrar.setNumeroQuadra(quadra
											.getNumeroQuadra());
									debitoACobrar.setNumeroLote(imovel
											.getLote());
									debitoACobrar.setNumeroSubLote(imovel
											.getSubLote());
									debitoACobrar
											.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
									debitoACobrar.setRegistroAtendimento(null);
									debitoACobrar.setOrdemServico(null);
									debitoACobrar
											.setDebitoCreditoSituacaoAnterior(null);
									debitoACobrar.setParcelamentoGrupo(null);
									debitoACobrar
											.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
									debitoACobrar
											.setCobrancaForma(cobrancaForma);
									debitoACobrar.setDebitoTipo(debitoTipo);
									debitoACobrar
											.setUltimaAlteracao(new Date());
									debitoACobrar.setGeracaoDebito(new Date());
									debitoACobrar
											.setAnoMesReferenciaDebito(guiaPagamento
													.getAnoMesReferenciaContabil());
									debitoACobrar
											.setFinanciamentoTipo(financiamentoTipo);
									debitoACobrar
											.setLancamentoItemContabil(lancamentoItemContabil);

									// gerar debito a cobrar
									debitoACobrar
											.setValorDebito(calcularAcrescimoPorImpontualidade
													.getValorAtualizacaoMonetaria());

									// repositorioFaturamento.atualizarIndicadorMultaDeGuiaPagamento(guiaPagamento.getId());
									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									// Inseri o débito a cobra geral e
									// recupera o id
									Integer idDebitoACobrarGeral = (Integer) getControladorUtil()
											.inserir(debitoACobrarGeral);
									debitoACobrarGeral
											.setId(idDebitoACobrarGeral);

									debitoACobrar.setId(idDebitoACobrarGeral);
									debitoACobrar
											.setDebitoACobrarGeral(debitoACobrarGeral);
									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(inserirDebitoACobrarCategoriaBatch(
													debitoACobrar,
													debitoACobrar.getImovel()));
								}
							} // fim if da comparacao da data de
							// pagamento
						} // fim while contasiterator
					} // fim if colecaoguia

					if (colecaoIdsGuiasPagamentosAtualizarIndicadorMulta != null
							&& !colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
									.isEmpty()) {
						repositorioFaturamento
								.atualizarIndicadorMultaDeGuiaPagamento(colecaoIdsGuiasPagamentosAtualizarIndicadorMulta);
					}
				}
			}// fim while imovelporrotaiterator

			// Inserir os débitos a cobrar
			if (colecaoDebitoACobrarInserir != null
					&& !colecaoDebitoACobrarInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(
						colecaoDebitoACobrarInserir);
			}

			// Inseri os débitos a cobrar por categoria
			if (colecaoDebitoACobrarCategoriaInserir != null
					&& !colecaoDebitoACobrarCategoriaInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(
						colecaoDebitoACobrarCategoriaInserir);
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			System.out.println("FIM " + new Date());
			return null;

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}

	}*/

	/**
	 * [UC0482] Emitir 2 Via de Contas
	 * 
	 * [SB00011] Gerar Linhas da Tarifa de Água
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	protected Collection gerarLinhasTarifaAguaRelatorio(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao,
			Collection colecaoLinhasDescricaoServicosTarifasTotal)
			throws ControladorException {

		Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = colecaoLinhasDescricaoServicosTarifasTotal;
		ContaLinhasDescricaoServicosTarifasTotalHelper contaLinhasDescricaoServicosTarifasTotalHelper = null;

		String descricaoServicosTarifas1 = "";

		// -- Linha 1 --//
		descricaoServicosTarifas1 = "AGUA";
		contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
		contaLinhasDescricaoServicosTarifasTotalHelper
				.setDescricaoServicosTarifas(descricaoServicosTarifas1);
		contaLinhasDescricaoServicosTarifasTotalHelper.setConsumoFaixa("");
		contaLinhasDescricaoServicosTarifasTotalHelper.setValor("");
		colecaoContaLinhasDescricaoServicosTarifasTotalHelper
				.add(contaLinhasDescricaoServicosTarifasTotalHelper);

		Collection colecaoContaCategoriaComFaixas = null;
		try {
			colecaoContaCategoriaComFaixas = repositorioFaturamento
					.pesquisarContaCategoriaSubCategoria(emitirContaHelper
							.getIdConta());
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

				String descricaoServicosTarifas2 = "";

				// -- Linha 2 --//
				descricaoServicosTarifas2 = " ";
				// descricao da categoria
				descricaoServicosTarifas2 = descricaoServicosTarifas2
						+ Util.completaString(contaCategoria.getComp_id()
								.getSubcategoria().getDescricaoAbreviada(), 21);
				// quantidade de economias
				descricaoServicosTarifas2 = descricaoServicosTarifas2
						+ Util.adicionarZerosEsquedaNumero(3, ""
								+ contaCategoria.getQuantidadeEconomia());

				if (contaCategoria.getQuantidadeEconomia() == 1) {
					descricaoServicosTarifas2 = descricaoServicosTarifas2
							+ "  UNIDADE ";
				} else {
					descricaoServicosTarifas2 = descricaoServicosTarifas2
							+ "  UNIDADES ";
				}

				contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
				contaLinhasDescricaoServicosTarifasTotalHelper
						.setDescricaoServicosTarifas(descricaoServicosTarifas2);
				contaLinhasDescricaoServicosTarifasTotalHelper
						.setConsumoFaixa("");
				contaLinhasDescricaoServicosTarifasTotalHelper.setValor("");
				colecaoContaLinhasDescricaoServicosTarifasTotalHelper
						.add(contaLinhasDescricaoServicosTarifasTotalHelper);

				// -- Linha 3 --//
				contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
				String descricaoServicosTarifas3 = "";
				String consumoFaixa3 = "";
				String valor3 = "";

				Integer debitoCreditoSituacaoAtualConta = null;

				try {
					debitoCreditoSituacaoAtualConta = repositorioFaturamento
							.pesquisarDebitoCreditoSituacaoAtualConta(emitirContaHelper
									.getIdConta());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				if (parmsMedicaoHistorico == null
						&& debitoCreditoSituacaoAtualConta
								.equals(DebitoCreditoSituacao.NORMAL)) {
					descricaoServicosTarifas3 = " TARIFA MÍNIMA";

					// Valor da tarifa mínima de água para a categoria por
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

					descricaoServicosTarifas3 = descricaoServicosTarifas3
							+ Util.completaStringComEspacoAEsquerda(
									valorTarifaMinima, 6);
					descricaoServicosTarifas3 = descricaoServicosTarifas3
							+ " POR UNIDADE";

					consumoFaixa3 = "MINIMO";
					consumoFaixa3 = consumoFaixa3 + Util.completaString("", 11);

					// valor da água para categoria
					String valorAgua = Util.formatarMoedaReal(emitirContaHelper
							.getValorAgua());
					valor3 = valorAgua;

					contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
					contaLinhasDescricaoServicosTarifasTotalHelper
							.setDescricaoServicosTarifas(descricaoServicosTarifas3);
					contaLinhasDescricaoServicosTarifasTotalHelper
							.setConsumoFaixa(consumoFaixa3);
					contaLinhasDescricaoServicosTarifasTotalHelper
							.setValor(valor3);
					colecaoContaLinhasDescricaoServicosTarifasTotalHelper
							.add(contaLinhasDescricaoServicosTarifasTotalHelper);

				} else {

					// recupera a coleção de conta categoria consumo faixa
					Collection colecaoContaCategoriaConsumoFaixa = null;
					try {
						colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
								.pesquisarContaCategoriaFaixas(
										emitirContaHelper.getIdConta(),
										contaCategoria.getComp_id()
												.getCategoria().getId(),
										contaCategoria.getComp_id()
												.getSubcategoria().getId());
					} catch (ErroRepositorioException e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);
					}

					// 2.3.1 caso existam faixas de consumo para conta/categoria
					if (colecaoContaCategoriaConsumoFaixa != null
							&& !colecaoContaCategoriaConsumoFaixa.isEmpty()) {

						descricaoServicosTarifas3 = " ATE ";

						// Consumo mínimo de água para a categoria por economia
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

						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ Util.completaStringComEspacoAEsquerda(
										consumoMinima, 2);

						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ " M3" + Util.completaString("", 7);
						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ "- R$";

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
								.completaStringComEspacoAEsquerda(
										""
												+ Util
														.formatarMoedaReal(valorTarifaMinimaBigDecimal),
										6);

						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ valorTarifaMinima + " (POR UNIDADE)";

						// Consumo mínimo de água para a categoria
						if (contaCategoria.getConsumoMinimoAgua() != null
								&& !contaCategoria.getConsumoMinimoAgua()
										.equals("")) {
							consumoFaixa3 = Util
									.completaStringComEspacoAEsquerda(""
											+ contaCategoria
													.getConsumoMinimoAgua(), 4);
						} else {
							consumoFaixa3 = Util
									.completaStringComEspacoAEsquerda("", 4);
						}
						consumoFaixa3 = consumoFaixa3 + " M3";

						// valor da tarifa mínima de agua para categoria
						String valorAguaCategoria = "";
						if (contaCategoria.getValorTarifaMinimaAgua() != null) {
							valorAguaCategoria = Util
									.formatarMoedaReal(contaCategoria
											.getValorTarifaMinimaAgua());
						}

						valor3 = valorAguaCategoria;

						contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setDescricaoServicosTarifas(descricaoServicosTarifas3);
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setConsumoFaixa(consumoFaixa3.trim());
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setValor(valor3);
						colecaoContaLinhasDescricaoServicosTarifasTotalHelper
								.add(contaLinhasDescricaoServicosTarifasTotalHelper);

						Iterator iteratorContaCategoriaConsumoFaixa = colecaoContaCategoriaConsumoFaixa
								.iterator();
						while (iteratorContaCategoriaConsumoFaixa.hasNext()) {
							ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iteratorContaCategoriaConsumoFaixa
									.next();

							contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
							String descricaoServicosTarifas4 = "";
							String consumoFaixa4 = "";
							String valor4 = "";

							// -- Linha 4 --//
							descricaoServicosTarifas4 = " ";

							// caso a faixa seja a ultima da tarifa de consumo
							if (contaCategoriaConsumoFaixa.getConsumoFaixaFim()
									.equals(999999)) {
								// Consumo inicial da faixa menos 1 m3
								String consumoInicialFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaInicio() - 1);

								// valor da tarifa na faixa
								String valorTarifaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorTarifaFaixa());

								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ "ACIMA DE";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														consumoInicialFaixa, 3);
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ " M3  - R$";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														valorTarifaFaixa, 6);
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ " POR M3";

								// consumo da agua na faixa

								// consumoFaixa4 = Util
								// .completaStringComEspacoAEsquerda(""
								// + contaCategoriaConsumoFaixa
								// .getConsumoAgua(), 6)
								consumoFaixa4 = Util
										.completaStringComEspacoAEsquerda(
												""
														+ contaCategoriaConsumoFaixa
																.getConsumoAgua()
														* contaCategoria
																.getQuantidadeEconomia(),
												6)
										+ " M3";

								// valor da agua na faixa
								BigDecimal valorAguaFaixa = contaCategoriaConsumoFaixa
										.getValorAgua().multiply(qtdEconomia)
										.setScale(Parcelamento.CASAS_DECIMAIS);

								valor4 = ""
										+ Util
												.formatarMoedaReal(valorAguaFaixa);

								contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setDescricaoServicosTarifas(descricaoServicosTarifas4);
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setConsumoFaixa(consumoFaixa4.trim());
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setValor(valor4);
								colecaoContaLinhasDescricaoServicosTarifasTotalHelper
										.add(contaLinhasDescricaoServicosTarifasTotalHelper);

							} else {
								// Consumo inicial da faixa
								String consumoInicialFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaInicio());
								// consumo final da faixa
								String consumoFinalFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaFim());
								// valor da tarifa na faixa
								String valorTarifaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorTarifaFaixa());

								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														consumoInicialFaixa, 2)
										+ " M3 A";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														consumoFinalFaixa, 3)
										+ " M3";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ "   - R$";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														valorTarifaFaixa, 6)
										+ " POR M3";

								// consumo de Agua na faixa
								// consumoFaixa4 = Util
								// .completaStringComEspacoAEsquerda(""
								// + contaCategoriaConsumoFaixa
								// .getConsumoAgua(), 6)
								consumoFaixa4 = Util
										.completaStringComEspacoAEsquerda(
												""
														+ contaCategoriaConsumoFaixa
																.getConsumoAgua()
														* contaCategoria
																.getQuantidadeEconomia(),
												6)
										+ " M3";

								// valor da agua na faixa

								BigDecimal valorAguaFaixa = contaCategoriaConsumoFaixa
										.getValorAgua().multiply(qtdEconomia)
										.setScale(Parcelamento.CASAS_DECIMAIS);

								valor4 = ""
										+ Util
												.formatarMoedaReal(valorAguaFaixa);

								// String valorAguaFaixa = Util
								// .formatarMoedaReal(contaCategoriaConsumoFaixa
								// .getValorAgua());
								//
								// valor4 = valorAguaFaixa;

								contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setDescricaoServicosTarifas(descricaoServicosTarifas4);
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setConsumoFaixa(consumoFaixa4.trim());
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setValor(valor4);
								colecaoContaLinhasDescricaoServicosTarifasTotalHelper
										.add(contaLinhasDescricaoServicosTarifasTotalHelper);
							}
						}

					} else {
						// 2.3.2.

						descricaoServicosTarifas3 = " CONSUMO DE ÁGUA";
						consumoFaixa3 = Util.completaStringComEspacoAEsquerda(
								"" + contaCategoria.getConsumoAgua(), 6)
								+ " M3";
						valor3 = Util.formatarMoedaReal(contaCategoria
								.getValorAgua());

						contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setDescricaoServicosTarifas(descricaoServicosTarifas3);
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setConsumoFaixa(consumoFaixa3.trim());
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setValor(valor3);
						colecaoContaLinhasDescricaoServicosTarifasTotalHelper
								.add(contaLinhasDescricaoServicosTarifasTotalHelper);
					}

				}
			}
		}

		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
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
	 * [UC0144] Inserir Comando Atividade Faturamento
	 * 
	 * A data de vencimento do grupo será formatada com o mês de referência do
	 * grupo
	 * 
	 * @author Raphael Rossiter
	 * @date 05/05/2007
	 * 
	 * @param diaVencimento,
	 *            mesVencimento, anoVencimento
	 * @throws ControladorException
	 */
	public Date obterDataVencimentoFaturamentoGrupo(int diaVencimento,
			int mesVencimento, int anoVencimento) throws ControladorException {

		mesVencimento = mesVencimento - 1;

		Calendar dataVencimento = new GregorianCalendar(new Integer(
				anoVencimento).intValue(), new Integer(mesVencimento)
				.intValue(), diaVencimento);

		return dataVencimento.getTime();
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

			// Instância a forma de cobrança para cobrança em conta
			CobrancaForma cobrancaForma = new CobrancaForma();
			cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);

			// Instância a situação do débito para normal
			DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
			debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);

			// Recupera o tipo de débito referente a despesa postal
			DebitoTipo debitoTipo = this
					.getDebitoTipoHql(DebitoTipo.TAXA_COBRANCA);

			// Recupera a data atual
			Date dataAtual = new Date(System.currentTimeMillis());

			// Verifica se já existe débito para este imóvel
			// Object[] dadoDebitoACobrar = this.repositorioFaturamento
			// .pesquisarDebitoACobrar(imovel.getId(), debitoTipo.getId(),
			// sistema.getAnoMesFaturamento());
			//
			// // Cria a variável que vai armazenar o valor do débito
			// BigDecimal valor = new BigDecimal("0");

			/*
			 * Caso o perfil do imóvel seja tarifa social o valor vai ser o
			 * valor da tarifa social Caso contrário o valor da tarifa vai ser o
			 * normal.
			 */
			if (!imovel.getImovelPerfil().getId().equals(
					ImovelPerfil.TARIFA_SOCIAL)) {
				// caso o imóvel não seja enquadrado em tarifa social

				// Obtém o valor da Tarifa Normal
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
				// caso o imóvel seja enquadrado em tarifa social

				// Obtém o valor da Tarifa Social
				BigDecimal valorMinimaTarifaSocial = this.repositorioFaturamento
						.obterValorTarifa(ConsumoTarifa.CONSUMO_SOCIAL);

				// Caso o valor da tarifa social esteja nulo seta o valor para
				// zero
				if (valorMinimaTarifaSocial == null) {
					valorMinimaTarifaSocial = new BigDecimal("0");
				}

				// valor = valorMinimaTarifaSocial;
			}

			// inclui Débito A Cobrar Geral
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral
					.setIndicadorHistorico(DebitoACobrarGeral.INDICADOR_NAO_POSSUI_HISTORICO);
			debitoACobrarGeral.setUltimaAlteracao(new Date());
			Integer idDebitoACobrarGeral = (Integer) this.getControladorUtil()
					.inserir(debitoACobrarGeral);
			debitoACobrarGeral.setId(idDebitoACobrarGeral);

			// Cria uma instância de débito a cobrar
			DebitoACobrar debitoACobrar = new DebitoACobrar();
			debitoACobrar.setId(debitoACobrarGeral.getId());
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

			// Seta o Imóvel
			debitoACobrar.setImovel(imovel);

			// Seta o Débito Tipo
			debitoACobrar.setDebitoTipo(debitoTipo);

			// Seta Data e Hora Atual
			debitoACobrar.setGeracaoDebito(dataAtual);

			// Seta ano/mês da conta emitida como 2 via
			debitoACobrar.setAnoMesReferenciaDebito(anoMesReferencia);

			// Seta Ano/Mês de Cobrança
			debitoACobrar.setAnoMesCobrancaDebito(sistema
					.getAnoMesArrecadacao());

			
			// Seta Ano/Mês Referência do Faturamento
			//Alteracao CRC1389 Data:09/03/2009 
			//Author: Rômulo Aurélio 
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
			
			
			

			// Seta Valor do Débito
			BigDecimal valorFinal = sistema.getValorSegundaVia();

			debitoACobrar.setValorDebito(valorFinal);

			// Seta Número de Prestações do Débito
			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));

			// Seta Número de Prestações Cobradas
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));

			// Seta Localidade
			debitoACobrar.setLocalidade(imovel.getLocalidade());

			// Seta Quadra
			debitoACobrar.setQuadra(imovel.getQuadra());

			// Seta Código do Setor Comercial
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial()
					.getCodigo());

			// Seta Número Quadra
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

			// Seta Lançamento Item Contábil
			debitoACobrar.setLancamentoItemContabil(debitoTipo
					.getLancamentoItemContabil());

			// Seta Débito Crédito Situação
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);

			// Seta Cobrança Forma
			debitoACobrar.setCobrancaForma(cobrancaForma);

			// Seta a data de ultima alteração
			debitoACobrar.setUltimaAlteracao(new Date());

			Integer idDebitoACobrar = (Integer) this.getControladorUtil()
					.inserir(debitoACobrar);

			debitoACobrar.setId(idDebitoACobrar);

			// Recupera Categorias por Imóvel
			Collection<Categoria> colecaoCategoria = this
					.getControladorImovel().obterQuantidadeEconomiasCategoria(
							imovel);
			// Recupera Valores por Categorias
			Collection<BigDecimal> colecaoValoresCategorias = this
					.getControladorImovel().obterValorPorCategoria(
							colecaoCategoria, valorFinal);
			// Insere débito a cobrar por categoria
			inserirDebitoACobrarCategoria(colecaoCategoria,
					colecaoValoresCategorias, debitoACobrar);

		} catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * Verifica se o imóvel gera conta ou não.
	 * 
	 * [SF0003] - Verificar Não Geração da Conta
	 * 
	 * @author Leonardo Vieira, Pedro Alexandre
	 * @date 17/01/2006,13/09/2006
	 * 
	 * @param imovel
	 * @param valorTotalAgua
	 * @param valorTotalEsgoto
	 * @return <true> se for para gerar conta, <false> caso contrário
	 * @throws ControladorException
	 */
	public boolean verificarNaoGeracaoConta(Imovel imovel,
			BigDecimal valorTotalAgua, BigDecimal valorTotalEsgoto,
			int anoMesFaturamento) throws ControladorException {

		boolean retorno = true;
		boolean primeiraCondicao = false;
		boolean segundaCondicao = true;
		boolean primeiraSubCondicao = true;
		boolean segundaSubCondicao  = true;
		BigDecimal valorTotalDebitos = new BigDecimal(0.0);

		/*
		 * Caso o valor total da água e o valor total do esgoto seja igual a
		 * zero. Satisfaz a primeira condição.
		 */
		if (valorTotalAgua.compareTo(ConstantesSistema.VALOR_ZERO) == 0 && 
			valorTotalEsgoto.compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
				
			primeiraCondicao = true;
		}

		Collection colecaoDebitosACobrar = null;
		Collection colecaoCreditosARealizar = null;

		/*
		 * Colocado por Raphael Rossiter em 20/03/2007
		 */
		colecaoDebitosACobrar = this.obterDebitoACobrarImovel(imovel.getId(),
				DebitoCreditoSituacao.NORMAL, anoMesFaturamento);

		/*
		 * Não existam débitos a cobrar (não existem débitos na tabela
		 * DEBITO_A_COBRAR com IMOV_ID=IMOV_ID e DCST_IDATUAL com o valor
		 * correspondente a normal (valor=0) e DBAC_NNPRESTACAOCOBRADAS menor
		 * que DBAC_NNPRESTACAODEBITO)
		 * 
		 * [FS0005] - Verificar Débitos a cobrar de parcelamento
		 * 
		 * OBS - O [FS0005] já está incorporado no método
		 * obterDebitoACobrarImovel
		 */
		if (colecaoDebitosACobrar == null || colecaoDebitosACobrar.isEmpty()) {
			// segundaCondicao = true;
			primeiraSubCondicao = true;
		} else {

			try {
				colecaoCreditosARealizar = repositorioFaturamento
						.pesquisarCreditoARealizar(imovel.getId(),
								DebitoCreditoSituacao.NORMAL, anoMesFaturamento);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			/*
			 * ... ou, caso existam, não existam créditos a realizar (não
			 * existem créditos na tabela CREDITO_A_REALIZAR com IMOV_ID=IMOV_ID
			 * do imóvel e DCST_IDATUAL com o valor correspondente a normal
			 * (valor=0)e (CRAR_NNPRESTACAOREALIZADAS menor que
			 * CRAR_NNPRESTACAOCREDITO ou CRAR_VLRESIDUALMESANTERIOR maior que
			 * zero))
			 * 
			 * [FS0006] - Verificar Créditos a realizar de parcelamento
			 * 
			 * OBS - O [FS0006] já está incorporado no método
			 * repositorioFaturamento.pesquisarCreditoARealizar
			 */
			if (colecaoCreditosARealizar == null
					|| colecaoCreditosARealizar.isEmpty()) {

				/*
				 * e os débitos a cobrar sejam todos correspondentes a tipo de
				 * debito com indicador de geração de conta igual a NÃO
				 * (DBTP_ICGERACAOCONTA=2 da tabela DEBITO_TIPO com DBTP_ID da
				 * tabela DEBITO_A_COBRAR)
				 */
				Iterator iteratorColecaoDebitosACobrar = colecaoDebitosACobrar
						.iterator();
				
				BigDecimal valorPrestacao = new BigDecimal(0.0);
				valorTotalDebitos = new BigDecimal(0.0); 

				DebitoACobrar debitoACobrar = null;
				DebitoTipo debitoTipo = null;

				while (iteratorColecaoDebitosACobrar.hasNext()) {

					debitoACobrar = (DebitoACobrar) iteratorColecaoDebitosACobrar
							.next();

					

					// Adicionado por Romulo Aurelio CRC 399
					// Calcula o valor da prestação
					valorPrestacao = debitoACobrar.getValorDebito().divide(new BigDecimal(debitoACobrar
									.getNumeroPrestacaoDebito()), 2, BigDecimal.ROUND_DOWN);

					
					/*
	                 * Alterado por Vivianne Sousa em 20/12/2007 - Analista: Adriano
	                 * criação do bonus para parcelamento com RD especial
	                 */
	                Short numeroParcelaBonus = 0;
	                if(debitoACobrar.getNumeroParcelaBonus() != null){
	                    numeroParcelaBonus = debitoACobrar.getNumeroParcelaBonus();
	                }

					// Caso seja a última prestação
	                if (debitoACobrar.getNumeroPrestacaoCobradas() == 
	                    ((debitoACobrar.getNumeroPrestacaoDebito()- numeroParcelaBonus) - 1)) {

						// Obtém o número de prestação débito
						BigDecimal numeroPrestacaoDebito = new BigDecimal(
						debitoACobrar.getNumeroPrestacaoDebito());
						
						// Mutiplica o (valor da prestação * número da prestação debito) - numeroParcelaBonus
						
						BigDecimal multiplicacao = valorPrestacao.multiply(numeroPrestacaoDebito).setScale(2);
	                    
						// Subtrai o valor do débito pelo resultado da multiplicação 
						BigDecimal parte1 = debitoACobrar.getValorDebito()
						.subtract(multiplicacao).setScale(2);

						// Calcula o valor da prestação
						valorPrestacao = valorPrestacao.add(parte1).setScale(2);
					}
					// Acumula o valor da prestação no valor total dos debitos
					valorTotalDebitos = valorTotalDebitos.add(valorPrestacao);

					// Adicionado por Romulo Aurelio CRC 399
					
					try {
						debitoTipo = repositorioFaturamento
								.getDebitoTipo(debitoACobrar.getDebitoTipo()
										.getId());
					} catch (ErroRepositorioException ex) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", ex);
					}

					if (debitoTipo.getIndicadorGeracaoConta().shortValue() != 2) {
						// segundaCondicao = false;
						segundaSubCondicao = true;
					}else{
						segundaSubCondicao = false;
					}
				}
				// Adicionado por Romulo Aurelio CRC 399
				SistemaParametro sistemaParametro = getControladorUtil()
						.pesquisarParametrosDoSistema();

				if (sistemaParametro.getValorMinimoEmissaoConta().compareTo(
						valorTotalDebitos) >= 0) {
					segundaCondicao = true;
				} else {
					segundaCondicao = false;
				}
				// Adicionado por Romulo Aurelio CRC 399
			} else {
				primeiraSubCondicao = false;
			}
		}

		if (colecaoDebitosACobrar != null) {
			colecaoDebitosACobrar.clear();
			colecaoDebitosACobrar = null;
		}

		if (colecaoCreditosARealizar != null) {
			colecaoCreditosARealizar.clear();
			colecaoCreditosARealizar = null;
		}

		/*
		 * Caso as duas condições sejam verdadeiras não gera a conta.
		 */
		if (primeiraCondicao && segundaCondicao && primeiraSubCondicao && segundaSubCondicao) {
			retorno = false;
		}
		
		/*
		 * Caso a situação do imovel seja SUPRIMIDO não gera conta.
		 */
		else if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPRIMIDO
				|| imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPR_PARC
				|| imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPR_PARC_PEDIDO) {

			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();

			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAgua.ID, imovel.getId()));

			Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(
					filtroLigacaoAgua, LigacaoAgua.class.getName());

			if (colecaoLigacaoAgua != null && !colecaoLigacaoAgua.isEmpty()) {

				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util
						.retonarObjetoDeColecao(colecaoLigacaoAgua);

				if (ligacaoAgua.getDataCorte() != null) {

					Date dataAnoMesFaturamento = Util.criarData(1, Util
							.obterMes(anoMesFaturamento), Util
							.obterAno(anoMesFaturamento));

					Date dataAnoMesFaturamentoMenos2Anos = Util
							.subtrairNumeroAnosDeUmaData(dataAnoMesFaturamento,
									-2);

					if (ligacaoAgua.getDataCorte().compareTo(
							dataAnoMesFaturamentoMenos2Anos) == -1) {
						retorno = false;
					} else {
						retorno = true;
					}
				} else {
					retorno = false;
				}

			} else {
				retorno = false;
			}

		}

		return retorno;
	}


	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 08/01/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContasHistorico(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,
			Short contaSemCodigoBarras) throws ControladorException {

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = new ArrayList();

		Iterator iter = idsContaEP.iterator();

		while (iter.hasNext()) {
			Integer idContaEP = (Integer) iter.next();

			Collection colectionConta;
			try {
				colectionConta = this.repositorioFaturamento
						.pesquisarContaHistoricoERota(idContaEP);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) colectionConta
					.iterator().next();

			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			String nomeCliente = "";
			if (emitirContaHelper.getNomeImovel() != null
					&& !emitirContaHelper.getNomeImovel().equals("")) {

				nomeCliente = emitirContaHelper.getNomeImovel();
				emitirContaHelper.setNomeCliente(nomeCliente);
			}

			// Linha 5
			// --------------------------------------------------------------
			// recupera endereco do imóvel
			String enderecoImovel = "";
			try {
				enderecoImovel = getControladorEndereco()
						.pesquisarEnderecoFormatado(
								emitirContaHelper.getIdImovel());
			} catch (ControladorException e1) {
				e1.printStackTrace();
			}
			emitirContaHelper.setEnderecoImovel(enderecoImovel);

			// Linha 6
			// --------------------------------------------------------------
			// instância um imovel com os dados da conta para recuperar a
			// inscrição que está no objeto imovel
			Imovel imovel = new Imovel();
			Localidade localidade = new Localidade();
			localidade.setId(emitirContaHelper.getIdLocalidade());
			imovel.setLocalidade(localidade);
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setCodigo(emitirContaHelper
					.getCodigoSetorComercialConta());
			imovel.setSetorComercial(setorComercial);
			Quadra quadra = new Quadra();
			quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());
			imovel.setQuadra(quadra);
			imovel.setLote(emitirContaHelper.getLoteConta());
			imovel.setSubLote(emitirContaHelper.getSubLoteConta());
			// Inscrição do imóvel
			emitirContaHelper
					.setInscricaoImovel(imovel.getInscricaoFormatada());

			// Linha 7
			// --------------------------------------------------------------
			String idClienteResponsavel = "";
			String enderecoClienteResponsavel = "";
			Integer idImovelContaEnvio = emitirContaHelper
					.getIdImovelContaEnvio();
			// caso a coleção de contas seja de entrega para o cliente
			// responsável
			if (idImovelContaEnvio != null
					&& (idImovelContaEnvio
							.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) || idImovelContaEnvio
							.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {
				Integer idClienteResponsavelInteger = null;
				idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(
						emitirContaHelper.getIdConta(), true);

				if (idClienteResponsavel != null
						&& !idClienteResponsavel.equals("")) {
					idClienteResponsavel = idClienteResponsavelInteger
							.toString();
					// [UC0085]Obter Endereco
					enderecoClienteResponsavel = getControladorEndereco()
							.pesquisarEnderecoClienteAbreviado(
									idClienteResponsavelInteger);
				}

			}

			emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
			emitirContaHelper
					.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

			// Linha 8
			// --------------------------------------------------------------

			// [SB0002] - Determinar tipo de ligação e tipo de Medição
			Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
			Integer tipoLigacao = parmSituacao[0];
			Integer tipoMedicao = parmSituacao[1];

			// Linha 9
			// --------------------------------------------------------------
			// cria uma stringBuilder para recuperar o resultado do [SB0003]
			// o tamanho da string que vem como resultado é de 20 posições
			StringBuilder obterDadosConsumoMedicaoAnterior = null;

			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 1
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 1, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes1(obterDadosConsumoMedicaoAnterior
							.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 4
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 4, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes4(obterDadosConsumoMedicaoAnterior
							.toString());

			// Linha 10
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 2
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 2, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes2(obterDadosConsumoMedicaoAnterior
							.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 5
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 5, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes5(obterDadosConsumoMedicaoAnterior
							.toString());
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros da medição historico do
			// [SB0004] - Obter Dados da Medição da Conta
			Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
					emitirContaHelper, tipoMedicao);
			// Leitura Anterior
			String leituraAnterior = "";
			// Leitura Atual
			String leituraAtual = "";
			// Data Leitura Anterior
			String dataLeituraAnterior = "";
			// Leitura Anterior
			String dataLeituraAtual = "";
			// Leitura Situação Atual
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
					dataLeituraAnterior = Util
							.formatarData((Date) parmsMedicaoHistorico[3]);
				}

				if (parmsMedicaoHistorico[2] != null) {
					dataLeituraAtual = Util
							.formatarData((Date) parmsMedicaoHistorico[2]);
				}

				if (parmsMedicaoHistorico[4] != null) {
					// leituraSituacaoAtual = ""
					// + (Integer) parmsMedicaoHistorico[4];
				}

				if (parmsMedicaoHistorico[5] != null) {
					leituraAnormalidadeFaturamento = ""
							+ (Integer) parmsMedicaoHistorico[5];
				}
			}
			emitirContaHelper.setDataLeituraAnterior(dataLeituraAnterior);
			emitirContaHelper.setDataLeituraAtual(dataLeituraAtual);
			String diasConsumo = "";
			if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
				// calcula a quantidade de dias de consumo que é a
				// quantidade de dias
				// entre a data de leitura
				// anterior(parmsMedicaoHistorico[2]) e a data de leitura
				// atual(parmsMedicaoHistorico[3])
				diasConsumo = ""
						+ Util.obterQuantidadeDiasEntreDuasDatas(
								(Date) parmsMedicaoHistorico[3],
								(Date) parmsMedicaoHistorico[2]);
			}
			// recupera os parametros de consumo faturamento e consumo médio
			// diário
			// [SB0005] - Obter Consumo Faturado e Consumo Médio Diário
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
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 3
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 3, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes3(obterDadosConsumoMedicaoAnterior
							.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 6
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper, 6, tipoLigacao, tipoMedicao);
			emitirContaHelper
					.setDadosConsumoMes6(obterDadosConsumoMedicaoAnterior
							.toString());

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
					parmsConsumoHistorico = getControladorMicromedicao()
							.obterDadosConsumoConta(
									emitirContaHelper.getIdImovel(),
									emitirContaHelper.getAmReferencia(),
									tipoLigacao);
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				if (parmsConsumoHistorico != null) {
					// descrição abreviada tipo de consumo
					if (parmsConsumoHistorico[0] != null) {
						descricaoAbreviadaTipoConsumo = (String) parmsConsumoHistorico[0];
					}
					// descrição tipo de consumo
					if (parmsConsumoHistorico[1] != null) {
						descricaoTipoConsumo = (String) parmsConsumoHistorico[1];
					}
					// Consumo médio
					if (parmsConsumoHistorico[2] != null) {
						consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
					}
					// descrição abreviada anormalidade de consumo
					if (parmsConsumoHistorico[3] != null) {
						descricaoAbreviadaAnormalidadeConsumo = (String) parmsConsumoHistorico[3];
					}
					// descrição anormalidade de consumo
					if (parmsConsumoHistorico[4] != null) {
						descricaoAnormalidadeConsumo = (String) parmsConsumoHistorico[4];
					}
					// Consumo médio
					if (parmsConsumoHistorico[5] != null) {
						consumoRateio = "" + (Integer) parmsConsumoHistorico[5];
					}
				}
			}

			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper
					.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);

			// Fim Chamar Sub-Fluxo

			// Linha 13
			// --------------------------------------------------------------

			// Inicio Chamar Sub-Fluxo
			// soma a quantidades de economias da tabela contaCategoria
			// [SB0007] - Obter Dados da Medição da Conta
			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(
					emitirContaHelper.getIdConta(), true);
			emitirContaHelper.setQuantidadeEconomiaConta(""
					+ quantidadeEconomiaConta);
			// Fim Chamar Sub-Fluxo

			// Consumo por Economia
			// transforma o consumoFaturamento para um bigDecimal
			BigDecimal consumoFaturadoBigDecimal = null;
			if (consumoFaturamento != null && !consumoFaturamento.equals("")) {
				consumoFaturadoBigDecimal = Util
						.formatarMoedaRealparaBigDecimal(consumoFaturamento);

			}
			// transforma a quantidade de economias da conta para um
			// bigDecimal
			BigDecimal qtdEconomiasBigDecimal = null;
			if (quantidadeEconomiaConta != null
					&& !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util
						.formatarMoedaRealparaBigDecimal(""
								+ quantidadeEconomiaConta);
			}
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null
					&& qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal
						.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util
						.formatarMoedaReal(consumoEconomiaBigDecimal);
				emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(
						0, (consumoEconomia.length() - 3)));
			}

			// Inicio Chamar Sub-Fluxo
			// concatena os campos dos sub-fluxos anteriores
			// [SB0008] - Obter Dados da Medição da Conta
			StringBuilder codigoAuxiliar = new StringBuilder();
			// leitura situação atual
			// tipo de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaTipoConsumo, 1));
			// tipo de contrato
			codigoAuxiliar.append(Util.completaString("", 1));
			// anormalidade de leitura
			codigoAuxiliar.append(Util.completaString(
					leituraAnormalidadeFaturamento, 2));
			// anormalidade de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaAnormalidadeConsumo, 2));

			// perfil do imóvel
			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString(""
						+ emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}
			// dias do consumo
			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			// Consumo medio do imóvel
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			// Fim Chamar Sub-Fluxo
			emitirContaHelper
					.setCodigoAuxiliarString(codigoAuxiliar.toString());

			// chama o [SB0009] - Obter Mensagem de Rateio de Consumo Fixo
			// de Esgoto
			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao);
			// mensagem de rateio de consumo ou consumo fixo de esgoto
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo
					.toString());

			// Linha 16
			// --------------------------------------------------------------
			// chama o [SB0010] - Gerar Linhas da Descrição dos Serviços e
			// Tarifas

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = gerarLinhasDescricaoServicoTarifasRelatorio(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao, true);

			emitirContaHelper
					.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

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

			emitirContaHelper.setValorContaString(Util
					.formatarMoedaReal(valorConta));

			if (contaSemCodigoBarras.equals(ConstantesSistema.SIM)
					|| valorConta.compareTo(new BigDecimal("0.00")) == 0) {
				emitirContaHelper.setContaSemCodigoBarras("1");
			} else {
				emitirContaHelper.setContaSemCodigoBarras("2");
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
			int anoMesReferenciaSubtraido = Util.subtrairMesDoAnoMes(
					emitirContaHelper.getAmReferencia(), 1);
			emitirContaHelper.setMesAnoFormatado(Util
					.formatarAnoMesParaMesAno(anoMesReferenciaSubtraido));

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
					numeroIndiceTurbidez = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]);
				}

				if (parmsQualidadeAgua[1] != null) {
					numeroCloroResidual = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]);
				}
			}
			emitirContaHelper.setNumeroIndiceTurbidez(numeroIndiceTurbidez);
			emitirContaHelper.setNumeroCloroResidual(numeroCloroResidual);

			// Linha 23
			// --------------------------------------------------------------
			// Considerar as contas do tipo débito automático como tipo de conta
			// normal
			// [SB0018 - Gerar Linhas das DemaisContas]
			Integer digitoVerificadorConta = new Integer(""
					+ emitirContaHelper.getDigitoVerificadorConta());
			// formata ano mes para mes ano
			String anoMes = "" + emitirContaHelper.getAmReferencia();
			String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

			String representacaoNumericaCodBarra = "";

			representacaoNumericaCodBarra = this.getControladorArrecadacao()
					.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
							emitirContaHelper.getIdLocalidade(),
							emitirContaHelper.getIdImovel(), mesAno,
							digitoVerificadorConta, null, null, null, null,
							null, null,null);

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
			// Linha28
			if (emitirContaHelper.getDataValidadeConta().compareTo(new Date()) == 1) {
				emitirContaHelper
						.setDataValidade(Util.formatarData(emitirContaHelper
								.getDataValidadeConta()));

			} else {
				// soma 60 dias a data atual
				Date dataValidadeConta = Util.adicionarNumeroDiasDeUmaData(
						new Date(), 60);

				int ano = Util.getAno(dataValidadeConta);
				int mes = Util.getMes(dataValidadeConta);
				Calendar calendar = new GregorianCalendar();
				calendar.set(Calendar.MONTH, mes - 1);
				calendar.set(Calendar.YEAR, ano);

				Collection colecaoNacionalFeriado = getControladorUtil()
						.pesquisarFeriadosNacionais();

				Collection colecaoDatasFeriados = new ArrayList();
				Iterator iterNacionalFeriado = colecaoNacionalFeriado
						.iterator();
				while (iterNacionalFeriado.hasNext()) {
					NacionalFeriado nacionalFeriado = (NacionalFeriado) iterNacionalFeriado
							.next();
					colecaoDatasFeriados.add(nacionalFeriado.getData());
				}

				calendar.set(Calendar.DAY_OF_MONTH, Util.obterUltimoDiaUtilMes(
						mes, ano, colecaoDatasFeriados));

				dataValidadeConta = calendar.getTime();

				emitirContaHelper.setDataValidade(Util
						.formatarData(dataValidadeConta));

			}

			if (emitirContaHelper.getDebitoCreditoSituacaoAtualConta().equals(
					DebitoCreditoSituacao.NORMAL)
					|| emitirContaHelper.getDebitoCreditoSituacaoAtualConta()
							.equals(DebitoCreditoSituacao.RETIFICADA)
					|| emitirContaHelper.getDebitoCreditoSituacaoAtualConta()
							.equals(DebitoCreditoSituacao.INCLUIDA)) {

				emitirContaHelper.setContaPaga("1");

			}

			//Se valor da conta maior que o valor limite
			//emite uma Ficha de Compensação(Boleto bancario)
			if (sistemaParametro.getValorContaFichaComp() != null 
				&& !sistemaParametro.getValorContaFichaComp().equals(new BigDecimal("0.00"))
				&& valorConta!= null  && valorConta.compareTo(sistemaParametro.getValorContaFichaComp()) == 1){
				StringBuilder nossoNumero = obterNossoNumeroFichaCompensacao("1",emitirContaHelper.getIdConta().toString()) ;
				emitirContaHelper.setNossoNumero(nossoNumero.toString());
				
			}
			
			colecaoEmitirContaHelper.add(emitirContaHelper);

			if (cobrarTaxaEmissaoConta) {
				this.gerarDebitoACobrarTaxaEmissaoConta(emitirContaHelper
						.getIdImovel(), emitirContaHelper.getAmReferencia());
			}

		}

		return colecaoEmitirContaHelper;
	}

	/**
	 * [UC0482] Emitir 2 Via de Contas
	 * 
	 * [SB00011] Gerar Linhas da Tarifa de Água
	 * 
	 * @author Vivianne Sousa
	 * @date 25/04/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	protected Collection gerarLinhasTarifaAguaRelatorioContaHistorico(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao,
			Collection colecaoLinhasDescricaoServicosTarifasTotal)
			throws ControladorException {

		Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = colecaoLinhasDescricaoServicosTarifasTotal;
		ContaLinhasDescricaoServicosTarifasTotalHelper contaLinhasDescricaoServicosTarifasTotalHelper = null;

		String descricaoServicosTarifas1 = "";

		// -- Linha 1 --//
		descricaoServicosTarifas1 = "AGUA";
		contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
		contaLinhasDescricaoServicosTarifasTotalHelper
				.setDescricaoServicosTarifas(descricaoServicosTarifas1);
		contaLinhasDescricaoServicosTarifasTotalHelper.setConsumoFaixa("");
		contaLinhasDescricaoServicosTarifasTotalHelper.setValor("");
		colecaoContaLinhasDescricaoServicosTarifasTotalHelper
				.add(contaLinhasDescricaoServicosTarifasTotalHelper);

		Collection colecaoContaCategoriaComFaixas = null;
		try {
			colecaoContaCategoriaComFaixas = repositorioFaturamento
					.pesquisarContaHistoricoCategoriaSubCategoria(emitirContaHelper
							.getIdConta());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoContaCategoriaComFaixas != null
				&& !colecaoContaCategoriaComFaixas.isEmpty()) {
			Iterator iteratorContaCategoriaComFaixas = colecaoContaCategoriaComFaixas
					.iterator();
			while (iteratorContaCategoriaComFaixas.hasNext()) {
				ContaCategoriaHistorico contaCategoriaHistorico = (ContaCategoriaHistorico) iteratorContaCategoriaComFaixas
						.next();

				String descricaoServicosTarifas2 = "";

				// -- Linha 2 --//
				descricaoServicosTarifas2 = " ";
				// descricao da categoria
				descricaoServicosTarifas2 = descricaoServicosTarifas2
						+ Util.completaString(contaCategoriaHistorico
								.getComp_id().getSubcategoria()
								.getDescricaoAbreviada(), 21);
				// quantidade de economias
				descricaoServicosTarifas2 = descricaoServicosTarifas2
						+ Util.adicionarZerosEsquedaNumero(3, ""
								+ contaCategoriaHistorico
										.getQuantidadeEconomia());

				if (contaCategoriaHistorico.getQuantidadeEconomia() == 1) {
					descricaoServicosTarifas2 = descricaoServicosTarifas2
							+ "  UNIDADE ";
				} else {
					descricaoServicosTarifas2 = descricaoServicosTarifas2
							+ "  UNIDADES ";
				}

				contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
				contaLinhasDescricaoServicosTarifasTotalHelper
						.setDescricaoServicosTarifas(descricaoServicosTarifas2);
				contaLinhasDescricaoServicosTarifasTotalHelper
						.setConsumoFaixa("");
				contaLinhasDescricaoServicosTarifasTotalHelper.setValor("");
				colecaoContaLinhasDescricaoServicosTarifasTotalHelper
						.add(contaLinhasDescricaoServicosTarifasTotalHelper);

				// -- Linha 3 --//
				contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
				String descricaoServicosTarifas3 = "";
				String consumoFaixa3 = "";
				String valor3 = "";

				Integer debitoCreditoSituacaoAtualConta = null;

				try {
					debitoCreditoSituacaoAtualConta = repositorioFaturamento
							.pesquisarDebitoCreditoSituacaoAtualContaHistorico(emitirContaHelper
									.getIdConta());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				if (parmsMedicaoHistorico == null
						&& debitoCreditoSituacaoAtualConta
								.equals(DebitoCreditoSituacao.NORMAL)) {
					descricaoServicosTarifas3 = " TARIFA MÍNIMA";

					// Valor da tarifa mínima de água para a categoria por
					// economia
					BigDecimal qtdEconomia = Util
							.formatarMoedaRealparaBigDecimal(""
									+ contaCategoriaHistorico
											.getQuantidadeEconomia());
					String valorTarifaMinima = "";
					if (contaCategoriaHistorico.getValorTarifaMinimaAgua() != null
							&& qtdEconomia != null) {
						BigDecimal valorTarifaMinimaBigDecimal = contaCategoriaHistorico
								.getValorTarifaMinimaAgua().divide(qtdEconomia,
										2, RoundingMode.UP);
						valorTarifaMinima = Util
								.formatarMoedaReal(valorTarifaMinimaBigDecimal);
					}

					descricaoServicosTarifas3 = descricaoServicosTarifas3
							+ Util.completaStringComEspacoAEsquerda(
									valorTarifaMinima, 6);
					descricaoServicosTarifas3 = descricaoServicosTarifas3
							+ " POR UNIDADE";

					consumoFaixa3 = "MINIMO";
					consumoFaixa3 = consumoFaixa3 + Util.completaString("", 11);

//					 valor da água para categoria
					 String valorAgua = Util.formatarMoedaReal(emitirContaHelper
					 .getValorAgua());
//					String valorAgua = Util.formatarMoedaReal(contaCategoriaHistorico.getValorAgua());
					valor3 = valorAgua;
				
					contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
					contaLinhasDescricaoServicosTarifasTotalHelper
							.setDescricaoServicosTarifas(descricaoServicosTarifas3);
					contaLinhasDescricaoServicosTarifasTotalHelper
							.setConsumoFaixa(consumoFaixa3);
					contaLinhasDescricaoServicosTarifasTotalHelper
							.setValor(valor3);
					colecaoContaLinhasDescricaoServicosTarifasTotalHelper
							.add(contaLinhasDescricaoServicosTarifasTotalHelper);

				} else {

					// recupera a coleção de conta categoria consumo faixa
					Collection colecaoContaCategoriaConsumoFaixa = null;
					try {
						colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
								.pesquisarContaCategoriaHistoricoFaixas(
										emitirContaHelper.getIdConta(),
										contaCategoriaHistorico.getComp_id()
												.getCategoria().getId(),
										contaCategoriaHistorico.getComp_id()
												.getSubcategoria().getId());
					} catch (ErroRepositorioException e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);
					}

					// 2.3.1 caso existam faixas de consumo para conta/categoria
					if (colecaoContaCategoriaConsumoFaixa != null
							&& !colecaoContaCategoriaConsumoFaixa.isEmpty()) {

						descricaoServicosTarifas3 = " ATE ";

						// Consumo mínimo de água para a categoria por economia
						BigDecimal qtdEconomia = Util
								.formatarMoedaRealparaBigDecimal(""
										+ contaCategoriaHistorico
												.getQuantidadeEconomia());

						BigDecimal consumoMinimoAgua = null;
						if (contaCategoriaHistorico.getConsumoMinimoAgua() != null) {
							consumoMinimoAgua = Util
									.formatarMoedaRealparaBigDecimal(""
											+ contaCategoriaHistorico
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

						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ Util.completaStringComEspacoAEsquerda(
										consumoMinima, 2);

						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ " M3" + Util.completaString("", 7);
						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ "- R$";

						// valor da tarifa minima de agua para a categoria por
						// economia
						BigDecimal valorTarifaMinimaBigDecimal = null;
						if (contaCategoriaHistorico.getValorTarifaMinimaAgua() != null
								&& qtdEconomia != null) {
							valorTarifaMinimaBigDecimal = contaCategoriaHistorico
									.getValorTarifaMinimaAgua().divide(
											qtdEconomia, 2, RoundingMode.UP);
						}

						String valorTarifaMinima = Util
								.completaStringComEspacoAEsquerda(
										""
												+ Util
														.formatarMoedaReal(valorTarifaMinimaBigDecimal),
										6);

						descricaoServicosTarifas3 = descricaoServicosTarifas3
								+ valorTarifaMinima + " (POR UNIDADE)";

						// Consumo mínimo de água para a categoria
						if (contaCategoriaHistorico.getConsumoMinimoAgua() != null
								&& !contaCategoriaHistorico
										.getConsumoMinimoAgua().equals("")) {
							consumoFaixa3 = Util
									.completaStringComEspacoAEsquerda(""
											+ contaCategoriaHistorico
													.getConsumoMinimoAgua(), 4);
						} else {
							consumoFaixa3 = Util
									.completaStringComEspacoAEsquerda("", 4);
						}
						consumoFaixa3 = consumoFaixa3 + " M3";

						// valor da tarifa mínima de agua para categoria
						String valorAguaCategoria = "";
						if (contaCategoriaHistorico.getValorTarifaMinimaAgua() != null) {
							valorAguaCategoria = Util
									.formatarMoedaReal(contaCategoriaHistorico
											.getValorTarifaMinimaAgua());
						}

						valor3 = valorAguaCategoria;

						contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setDescricaoServicosTarifas(descricaoServicosTarifas3);
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setConsumoFaixa(consumoFaixa3.trim());
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setValor(valor3);
						colecaoContaLinhasDescricaoServicosTarifasTotalHelper
								.add(contaLinhasDescricaoServicosTarifasTotalHelper);

						Iterator iteratorContaCategoriaConsumoFaixa = colecaoContaCategoriaConsumoFaixa
								.iterator();
						while (iteratorContaCategoriaConsumoFaixa.hasNext()) {
							ContaCategoriaConsumoFaixaHistorico contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixaHistorico) iteratorContaCategoriaConsumoFaixa
									.next();

							contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
							String descricaoServicosTarifas4 = "";
							String consumoFaixa4 = "";
							String valor4 = "";

							// -- Linha 4 --//
							descricaoServicosTarifas4 = " ";

							// caso a faixa seja a ultima da tarifa de consumo
							if (contaCategoriaConsumoFaixa.getConsumoFaixaFim()
									.equals(999999)) {
								// Consumo inicial da faixa menos 1 m3
								String consumoInicialFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaInicio() - 1);

								// valor da tarifa na faixa
								String valorTarifaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorTarifaFaixa());

								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ "ACIMA DE";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														consumoInicialFaixa, 3);
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ " M3  - R$";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														valorTarifaFaixa, 6);
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ " POR M3";

								// consumo da agua na faixa

								// consumoFaixa4 = Util
								// .completaStringComEspacoAEsquerda(""
								// + contaCategoriaConsumoFaixa
								// .getConsumoAgua(), 6)
								consumoFaixa4 = Util
										.completaStringComEspacoAEsquerda(
												""
														+ contaCategoriaConsumoFaixa
																.getConsumoAgua()
														* contaCategoriaHistorico
																.getQuantidadeEconomia(),
												6)
										+ " M3";

								// valor da agua na faixa
								BigDecimal valorAguaFaixa = contaCategoriaConsumoFaixa
										.getValorAgua().multiply(qtdEconomia)
										.setScale(Parcelamento.CASAS_DECIMAIS);

								valor4 = ""
										+ Util
												.formatarMoedaReal(valorAguaFaixa);

								contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setDescricaoServicosTarifas(descricaoServicosTarifas4);
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setConsumoFaixa(consumoFaixa4.trim());
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setValor(valor4);
								colecaoContaLinhasDescricaoServicosTarifasTotalHelper
										.add(contaLinhasDescricaoServicosTarifasTotalHelper);

							} else {
								// Consumo inicial da faixa
								String consumoInicialFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaInicio());
								// consumo final da faixa
								String consumoFinalFaixa = ""
										+ (contaCategoriaConsumoFaixa
												.getConsumoFaixaFim());
								// valor da tarifa na faixa
								String valorTarifaFaixa = Util
										.formatarMoedaReal(contaCategoriaConsumoFaixa
												.getValorTarifaFaixa());

								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														consumoInicialFaixa, 2)
										+ " M3 A";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														consumoFinalFaixa, 3)
										+ " M3";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ "   - R$";
								descricaoServicosTarifas4 = descricaoServicosTarifas4
										+ Util
												.completaStringComEspacoAEsquerda(
														valorTarifaFaixa, 6)
										+ " POR M3";

								// consumo de Agua na faixa
								// consumoFaixa4 = Util
								// .completaStringComEspacoAEsquerda(""
								// + contaCategoriaConsumoFaixa
								// .getConsumoAgua(), 6)
								consumoFaixa4 = Util
										.completaStringComEspacoAEsquerda(
												""
														+ contaCategoriaConsumoFaixa
																.getConsumoAgua()
														* contaCategoriaHistorico
																.getQuantidadeEconomia(),
												6)
										+ " M3";

								// valor da agua na faixa

								BigDecimal valorAguaFaixa = contaCategoriaConsumoFaixa
										.getValorAgua().multiply(qtdEconomia)
										.setScale(Parcelamento.CASAS_DECIMAIS);

								valor4 = ""
										+ Util
												.formatarMoedaReal(valorAguaFaixa);

								// String valorAguaFaixa = Util
								// .formatarMoedaReal(contaCategoriaConsumoFaixa
								// .getValorAgua());
								//
								// valor4 = valorAguaFaixa;

								contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setDescricaoServicosTarifas(descricaoServicosTarifas4);
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setConsumoFaixa(consumoFaixa4.trim());
								contaLinhasDescricaoServicosTarifasTotalHelper
										.setValor(valor4);
								colecaoContaLinhasDescricaoServicosTarifasTotalHelper
										.add(contaLinhasDescricaoServicosTarifasTotalHelper);
							}
						}

					} else {
						// 2.3.2.

						descricaoServicosTarifas3 = " CONSUMO DE ÁGUA";
						consumoFaixa3 = Util.completaStringComEspacoAEsquerda(
								"" + contaCategoriaHistorico.getConsumoAgua(),
								6)
								+ " M3";
						valor3 = Util.formatarMoedaReal(contaCategoriaHistorico
								.getValorAgua());

						contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setDescricaoServicosTarifas(descricaoServicosTarifas3);
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setConsumoFaixa(consumoFaixa3.trim());
						contaLinhasDescricaoServicosTarifasTotalHelper
								.setValor(valor3);
						colecaoContaLinhasDescricaoServicosTarifasTotalHelper
								.add(contaLinhasDescricaoServicosTarifasTotalHelper);
					}

				}
			}
		}

		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}

	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 27/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoContaCliente(Integer codigoCliente,
		Short relacaoTipo, Integer anoMes,ContaMotivoRetificacao contaMotivoRetificacao,
		Collection debitosTipoRetirar, Usuario usuarioLogado,Date dataVencimentoContaInicio, 
		Date dataVencimentoContaFim,Integer anoMesFim) 
		throws ControladorException {

		Collection colecaoContasManutencao = new ArrayList();

		try {
			colecaoContasManutencao = 
				repositorioFaturamento.obterContasCliente(codigoCliente, 
					relacaoTipo, 
					anoMes,
					dataVencimentoContaInicio, 
					dataVencimentoContaFim,
					anoMesFim);

			if (colecaoContasManutencao != null && !colecaoContasManutencao.isEmpty()) {

				Iterator colecaoContasManutencaoIterator = colecaoContasManutencao.iterator();

				while (colecaoContasManutencaoIterator.hasNext()) {

					// Obtém os dados do crédito realizado
					Object[] contaArray = (Object[]) colecaoContasManutencaoIterator.next();

					Conta conta = (Conta) contaArray[0];
					conta.setUltimaAlteracao(new Date());

					Imovel imovel = (Imovel) contaArray[1];

					Collection colecaoSubCategoria = 
						getControladorImovel().obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta);

					Collection colecaoCreditoRealizado = obterCreditosRealizadosConta(conta);
					Collection colecaoDebitoCobrado = obterDebitosCobradosConta(conta);

					Collection<CalcularValoresAguaEsgotoHelper> valoresConta = 
						calcularValoresConta(
							Util.formatarAnoMesParaMesAno(conta.getReferencia()), 
							imovel.getId().toString(), 
							conta.getLigacaoAguaSituacao().getId(), 
							conta.getLigacaoEsgotoSituacao().getId(), 
							colecaoSubCategoria, 
							conta.getConsumoAgua().toString(), 
							conta.getConsumoEsgoto().toString(), 
							conta.getPercentualEsgoto().toString(), 
							conta.getConsumoTarifa().getId(), 
							usuarioLogado);

					boolean achouDebitoRetirar = false;
					if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()) {
						
						Iterator colecaoDebitoCobradoIterator = colecaoDebitoCobrado.iterator();
						
						while (colecaoDebitoCobradoIterator.hasNext()) {
							DebitoCobrado debitoCobrado = 
								(DebitoCobrado) colecaoDebitoCobradoIterator.next();
							
							DebitoTipo debitoTipo = debitoCobrado.getDebitoTipo();
							if (debitosTipoRetirar.contains(debitoTipo)) {
								achouDebitoRetirar = true;
								colecaoDebitoCobradoIterator.remove();
							}
						}
						if (achouDebitoRetirar) {
							retificarConta(
								new Integer(conta.getReferencia()),
								conta, 
								imovel, 
								colecaoDebitoCobrado,
								colecaoCreditoRealizado, 
								conta.getLigacaoAguaSituacao(), 
								conta.getLigacaoEsgotoSituacao(),
								colecaoSubCategoria, 
								conta.getConsumoAgua().toString(), 
								conta.getConsumoEsgoto().toString(),
								conta.getPercentualEsgoto().toString(),
								conta.getDataVencimentoConta(),
								valoresConta, 
								contaMotivoRetificacao, 
								null,
								usuarioLogado, 
								conta.getConsumoTarifa().getId()+"", false,
								null,null,false , null,null,null,null,null);
						}

					}
				}
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 27/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoConta(Collection colecaoImovel,
			Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim, String indicadorContaPaga) throws ControladorException {

		Collection colecaoContasManutencao = new ArrayList();
		List colecaoAuxiliar = new ArrayList();

		colecaoAuxiliar.addAll(colecaoImovel);

		int i = 0;
		int cont = 500;

		Collection colecao = new ArrayList();
		while (i <= colecaoImovel.size()) {

			if (colecaoImovel.size() - i >= cont) {
				colecao = colecaoAuxiliar.subList(i, i + cont);
			} else {
				colecao = colecaoAuxiliar.subList(i, colecaoImovel.size());
			}
			i = i + cont;
			try {
				colecaoContasManutencao = repositorioFaturamento
						.obterContasImoveis(anoMes, colecao,
								dataVencimentoContaInicio,
								dataVencimentoContaFim, anoMesFim, indicadorContaPaga);

				if (colecaoContasManutencao != null
						&& !colecaoContasManutencao.isEmpty()) {

					Iterator colecaoContasManutencaoIterator = colecaoContasManutencao
							.iterator();

					while (colecaoContasManutencaoIterator.hasNext()) {

						// Obtém os dados do crédito realizado
						Object[] contaArray = (Object[]) colecaoContasManutencaoIterator
								.next();

						Conta conta = (Conta) contaArray[0];

						conta.setUltimaAlteracao(new Date());

						Imovel imovel = (Imovel) contaArray[1];

						Collection colecaoSubCategoria = getControladorImovel()
								.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
										conta);

						Collection colecaoCreditoRealizado = obterCreditosRealizadosConta(conta);

						Collection colecaoDebitoCobrado = obterDebitosCobradosConta(conta);

						Collection<CalcularValoresAguaEsgotoHelper> valoresConta = calcularValoresConta(
								Util.formatarAnoMesParaMesAno(conta
										.getReferencia()), imovel.getId()
										.toString(), conta
										.getLigacaoAguaSituacao().getId(),
								conta.getLigacaoEsgotoSituacao().getId(),
								colecaoSubCategoria, conta.getConsumoAgua()
										.toString(), conta.getConsumoEsgoto()
										.toString(), conta
										.getPercentualEsgoto().toString(),
								conta.getConsumoTarifa().getId(), usuarioLogado);

						boolean achouDebitoRetirar = false;
						if (colecaoDebitoCobrado != null
								&& !colecaoDebitoCobrado.isEmpty()) {
							Iterator colecaoDebitoCobradoIterator = colecaoDebitoCobrado
									.iterator();
							while (colecaoDebitoCobradoIterator.hasNext()) {
								DebitoCobrado debitoCobrado = (DebitoCobrado) colecaoDebitoCobradoIterator
										.next();
								DebitoTipo debitoTipo = debitoCobrado
										.getDebitoTipo();
								if (debitosTipoRetirar.contains(debitoTipo)) {
									achouDebitoRetirar = true;
									colecaoDebitoCobradoIterator.remove();
								}
							}
							if (achouDebitoRetirar) {
								retificarConta(new Integer(conta
										.getReferencia()), conta, imovel,
										colecaoDebitoCobrado,
										colecaoCreditoRealizado, conta
												.getLigacaoAguaSituacao(),
										conta.getLigacaoEsgotoSituacao(),
										colecaoSubCategoria, conta
												.getConsumoAgua().toString(),
										conta.getConsumoEsgoto().toString(),
										conta.getPercentualEsgoto().toString(),
										conta.getDataVencimentoConta(),
										valoresConta, contaMotivoRetificacao,
										null, usuarioLogado, 
										conta.getConsumoTarifa().getId()+"",
										false,null,null,false, null,null,null,null,null );
							}

						}
					}
				}
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				new ControladorException("erro.sistema", ex);
			}
		}
	}

	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoConta(Integer idGrupoFaturamento,
			Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim) throws ControladorException {

		Collection colecaoContasManutencao = new ArrayList();

		try {

			colecaoContasManutencao = repositorioFaturamento
					.obterContasGrupoFaturamento(anoMes, idGrupoFaturamento,
							dataVencimentoContaInicio, dataVencimentoContaFim,
							anoMesFim);

			if (colecaoContasManutencao != null
					&& !colecaoContasManutencao.isEmpty()) {

				Iterator colecaoContasManutencaoIterator = colecaoContasManutencao
						.iterator();

				while (colecaoContasManutencaoIterator.hasNext()) {

					// Obtém os dados do crédito realizado
					Object[] contaArray = (Object[]) colecaoContasManutencaoIterator
							.next();

					Conta conta = (Conta) contaArray[0];

					conta.setUltimaAlteracao(new Date());

					Imovel imovel = (Imovel) contaArray[1];

					Collection colecaoSubCategoria = getControladorImovel()
							.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
									conta);

					Collection colecaoCreditoRealizado = obterCreditosRealizadosConta(conta);

					Collection colecaoDebitoCobrado = obterDebitosCobradosConta(conta);

					Collection<CalcularValoresAguaEsgotoHelper> valoresConta = calcularValoresConta(
							Util
									.formatarAnoMesParaMesAno(conta
											.getReferencia()), imovel.getId()
									.toString(), conta.getLigacaoAguaSituacao()
									.getId(), conta.getLigacaoEsgotoSituacao()
									.getId(), colecaoSubCategoria, conta
									.getConsumoAgua().toString(), conta
									.getConsumoEsgoto().toString(), conta
									.getPercentualEsgoto().toString(), conta
									.getConsumoTarifa().getId(), usuarioLogado);

					boolean achouDebitoRetirar = false;
					if (colecaoDebitoCobrado != null
							&& !colecaoDebitoCobrado.isEmpty()) {
						Iterator colecaoDebitoCobradoIterator = colecaoDebitoCobrado
								.iterator();
						while (colecaoDebitoCobradoIterator.hasNext()) {
							DebitoCobrado debitoCobrado = (DebitoCobrado) colecaoDebitoCobradoIterator
									.next();
							DebitoTipo debitoTipo = debitoCobrado
									.getDebitoTipo();
							if (debitosTipoRetirar.contains(debitoTipo)) {
								achouDebitoRetirar = true;
								colecaoDebitoCobradoIterator.remove();
							}
						}
						if (achouDebitoRetirar) {
							retificarConta(new Integer(conta.getReferencia()),
									conta, imovel, colecaoDebitoCobrado,
									colecaoCreditoRealizado, conta
											.getLigacaoAguaSituacao(), conta
											.getLigacaoEsgotoSituacao(),
									colecaoSubCategoria, conta.getConsumoAgua()
											.toString(), conta
											.getConsumoEsgoto().toString(),
									conta.getPercentualEsgoto().toString(),
									conta.getDataVencimentoConta(),
									valoresConta, contaMotivoRetificacao, null,
									usuarioLogado, conta.getConsumoTarifa().getId()+"",
									false,null,null,false, null,null,null,null,null );
						}

					}
				}
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * Determina o tipo de conta que será associado na impressão da conta, para
	 * caern não tem o tipo de conta retida por BC ou retida por EC.
	 * 
	 * [SB0006 - ]
	 * 
	 * @author Raphael Rossiter,Sávio Luiz
	 * @date 09/12/2005,14/11/2007
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	protected ContaTipo obterContaTipoParaContaImpressao(Conta conta,
			Integer idClienteResponsavel, Imovel imovel) {

		ContaTipo contaTipo = new ContaTipo();

		contaTipo.setId(ContaTipo.CONTA_NORMAL);

		// Comentado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)

		/*
		 * else if (idClienteResponsavel != null) {
		 * 
		 * contaTipo.setId(ContaTipo.CONTA_CLIENTE_RESPONSAVEL); }
		 */

		// Colocado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)
		if (idClienteResponsavel != null
				&& imovel.getIndicadorDebitoConta().shortValue() == ConstantesSistema.NAO
						.shortValue()) {

			contaTipo.setId(ContaTipo.CONTA_CLIENTE_RESPONSAVEL);
		}

		else if (idClienteResponsavel != null
				&& imovel.getIndicadorDebitoConta().shortValue() == ConstantesSistema.SIM
						.shortValue()) {

			contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP);
		}

		// Comentado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)

		/*
		 * else if (imovel.getIndicadorDebitoConta() != null &&
		 * imovel.getIndicadorDebitoConta().equals( ConstantesSistema.SIM)) {
		 * 
		 * contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTOMATICO); }
		 */

		// Colocado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)
		else if (idClienteResponsavel == null
				&& imovel.getIndicadorDebitoConta().shortValue() == ConstantesSistema.SIM
						.shortValue()) {

			contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTOMATICO);
		}

		return contaTipo;
	}
	
	
//	/**
//	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
//	 * 
//	 * 	Este caso de uso permite a geração do TXT da declaração de quitação de débitos.
//	 * 
//	 * @author Hugo Amorim
//	 * @date 23/03/2010
//	 */
//	public void gerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
//			Integer idFuncionalidadeIniciada, Integer idGrupoFaturamento, Empresa empresa)
//			throws ControladorException{
//		
//
//		int idUnidadeIniciada = 0;
//		
//		// -------------------------
//		// Registrar o início do processamento da Unidade de
//		// Processamento do Batch
//		// -------------------------
//		
//		idUnidadeIniciada = getControladorBatch()
//			.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
//				UnidadeProcessamento.EMPRESA, empresa.getId());
//		
//		try {
//			
//			
//		Collection<Integer> anosParaGeracaoArquivoTexto = 
//			this.pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos();
//			
//			for (Integer ano : anosParaGeracaoArquivoTexto) {
//			
//			
//				// Variáveis para controle das partes dos arquivos de 3000 em 3000 registros
//				// ========================================================================
//				int parte = 1;
//				boolean flagTerminouParte = false;
//				int contadorDosTresMil = 0;
//				// ========================================================================	
//				
//				
//				// Variáveis para a paginação da pesquisa
//				// ========================================================================
//				boolean flagTerminou = false;
//				final int quantidadeMaxima = 500;
//				// ========================================================================			
//				
//				Integer sequencial = 0;
//				
//				StringBuilder linha = null;
//				
//				while(!flagTerminou){
//						
//						// Criação do Arquivo
//						// ========================================================================
//						Date dataAtual = new Date();
//						String nomeZip = null;
//						nomeZip = "DECLARACAO_DE_QUITACAO_ANUAL_DEBITOS_G"+idGrupoFaturamento+"_"+ano+"_Emp"
//								+empresa.getId()+"_PARTE_"+parte+"_"
//								+ Util.formatarData(dataAtual) +"_"+ Util.formatarHoraSemDataSemDoisPontos(dataAtual);
//						nomeZip = nomeZip.replace("/", "_");
//						File compactado = new File(nomeZip + ".zip");
//						File leitura = new File(nomeZip + ".txt");
//						ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compactado));
//						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
//						// ========================================================================
//						
//					
//						flagTerminouParte = false;
//						parte++;
//																	
//					
//						while(!flagTerminouParte){
//						
//							//[SB0001  Verificação para geração do TXT];
//							Collection colecaoExtratos = this.repositorioFaturamento
//								.pesquisarExtratoQuitacaoParaGeracaoArquivoTextoCAERN(
//										ano,empresa.getId(),quantidadeMaxima,idGrupoFaturamento);
//						
//							if(colecaoExtratos!=null && !colecaoExtratos.isEmpty()){
//								
//	
//								Iterator<Object[]> itera = colecaoExtratos.iterator(); 
//								
//								while(itera.hasNext()){
//									
//									Object[] dados = itera.next();
//									
//									DeclaracaoQuitacaoAnualDebitosHelper helper = new DeclaracaoQuitacaoAnualDebitosHelper();
//									
//									ExtratoQuitacao extratoQuitacao = new ExtratoQuitacao();
//									extratoQuitacao.setId(new Integer(dados[0].toString()));
//									extratoQuitacao.setImovel(new Imovel((Integer) dados[1]));
//									extratoQuitacao.setAnoReferencia(new Integer(dados[2].toString()));
//									extratoQuitacao.setIndicadorImpressao(new Integer(dados[5].toString()));
//									extratoQuitacao.setUltimaAlteracao((Date) dados[6]);
//									extratoQuitacao.setValorTotalDasContas(new BigDecimal(dados[3].toString()));
//									extratoQuitacao.setAnoMesMensagemConta(new Integer(dados[7].toString()));
//									
//									
//									Integer idImovel = new Integer(dados[1].toString());
//									Integer anoReferencia = new Integer(dados[2].toString());
//									String matriculaFormatada = Util.retornaMatriculaImovelFormatada(idImovel);
//									String inscricaoImovel = this.getControladorImovel().pesquisarInscricaoImovel(idImovel);
//									String nomeCliente = this.getControladorImovel().consultarClienteUsuarioImovel(idImovel);
//									String endereco = this.getControladorEndereco().pesquisarEndereco(idImovel);
//									FaturamentoGrupo faturamentoGrupo = this.getControladorImovel().pesquisarGrupoImovel(idImovel);
//									String[] enderecoDividido = this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(idImovel);
//									Object[] rotaESequencialRotaDoImovel = 
//										this.getControladorMicromedicao().obterRotaESequencialRotaDoImovelSeparados(idImovel);
//									
//									Short codigoRota = (Short) rotaESequencialRotaDoImovel[0];
//									Integer sequencialRota = (Integer) rotaESequencialRotaDoImovel[1];
//									
//									helper.setAnoMesArrecadacao(anoReferencia);
//									helper.setMatriculaFormatada(matriculaFormatada);
//									helper.setInscricaoImovel(inscricaoImovel);
//									helper.setNomeClienteUsuario(nomeCliente);
//									helper.setEndereco(endereco);
//									helper.setFirma(dados[4].toString());
//									helper.setIdGrupo(faturamentoGrupo.getId().toString());
//									helper.setEnderecoDestinatario(enderecoDividido[0]);
//									helper.setBairro(enderecoDividido[3]);
//									helper.setMunicipio(enderecoDividido[1]);
//									helper.setUf(enderecoDividido[2]);				
//									helper.setCep(enderecoDividido[4]);
//									helper.setExtratoQuitacaoParaAtualizacao(extratoQuitacao);
//									helper.setCodigoRota(codigoRota.toString());
//									helper.setSeguencialRota(sequencialRota.toString());
//									
//									Collection<ExtratoQuitacaoItem> colecaoExtratosItens = this.repositorioFaturamento
//										.pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(extratoQuitacao.getId());
//									
//									helper.setFaturas(colecaoExtratosItens);
//									
//									sequencial++;
//									
//								    linha = this.gerarlinhaArquivoExtratoQuitacao(helper,sequencial);
//									
//									out.write(linha.toString());
//									out.flush();
//									
//									//	O sistema atualiza o campo EXTRATO_QUITACAO.EXQT_ICIMPRESSAO,  
//									// para os registros em que foram gerados o TXT, para o valor 1;
//									ExtratoQuitacao extratoQuitacaoParaAtualizacao = 
//										helper.getExtratoQuitacaoParaAtualizacao();
//									
//									extratoQuitacaoParaAtualizacao.setIndicadorImpressao(ConstantesSistema.SIM.intValue());
//														
//									getControladorBatch().atualizarObjetoParaBatch(extratoQuitacaoParaAtualizacao);
//									
//									linha = null;
//	
//									
//								}		
//								
//							}
//							
//							contadorDosTresMil++;
//				
//							/**
//							 * Caso a coleção de dados retornados for menor que a
//							 * quantidade de registros seta a flag indicando que a
//							 * paginação terminou.
//							 */
//							if (colecaoExtratos == null || 
//									colecaoExtratos.size() < quantidadeMaxima) {
//				
//								flagTerminou = true;
//								flagTerminouParte = true;
//							}
//				
//							if (colecaoExtratos != null) {
//								colecaoExtratos.clear();
//								colecaoExtratos = null;
//							}
//							
//							if(contadorDosTresMil==4 || flagTerminou){
//							
//								try{
//									out.close();
//									ZipUtil.adicionarArquivo(zos, leitura);
//							
//									// close the stream
//									zos.close();
//									leitura.delete();
//								} catch (IOException e) {
//									getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
//						            throw new EJBException(e);
//								}
//								contadorDosTresMil = 0;
//								flagTerminouParte = true;
//							}
//		
//					}//Terminou Parte
//			
//				}//Terminou	  		
//			}
//		
//		getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
//				idUnidadeIniciada, false);
//
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			
//			// Este catch serve para interceptar
//			// qualquer exceção que o processo batch
//			// venha a lançar e garantir que a unidade
//			// de processamento do batch será atualizada
//			// com o erro ocorrido
//			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
//					idUnidadeIniciada, true);
//		}
//		
//	}
	
//	private StringBuilder gerarlinhaArquivoExtratoQuitacao(
//			DeclaracaoQuitacaoAnualDebitosHelper helper,Integer sequencial) {
//		
//		StringBuilder linha = new StringBuilder();
//		
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getAnoMesArrecadacao().toString(),4));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getNomeClienteUsuario(),50));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getEndereco(),120));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getMatriculaFormatada(),9));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						sequencial.toString(),50));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getInscricaoImovel(),20));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getFirma(),10));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getIdGrupo().toString(),2));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getNomeClienteUsuario(),50));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getEnderecoDestinatario(),70));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getBairro(),30));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getMunicipio(),30));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getUf(),2));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						Util.formatarCEP(helper.getCep()),10));
//		
//		for (ExtratoQuitacaoItem item : helper.getFaturas()) {
//							
//			linha.append(
//					Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//							Util.formatarAnoMesParaMesAno(item.getAnoMesReferenciaConta()),7));
//			linha.append(
//					Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//							item.getDescricaoSituacao(),30));
//			linha.append(
//					Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//							Util.formatarData(item.getDataSituacao()),10));
//			linha.append(
//					Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//							Util.formatarMoedaReal(item.getValorConta()),14));
//			
//		}
//		
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						Util.formatarMoedaReal(helper.getExtratoQuitacaoParaAtualizacao().getValorTotalDasContas()),14));
//		
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getAnoMesArrecadacao().toString(),4));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getCodigoRota().toString(),5));
//		linha.append(
//				Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
//						helper.getSeguencialRota().toString(),5));
//		
//		linha.append(System.getProperty("line.separator"));
//		
//		return linha;
//		
//	}
	
	
	 /**
     * [UC0352] Emitir Contas e Cartas
     * 
     * [SB0029] Gerar Arquivo TXT das fichas de Compensção
     * 
     * @author Vivianne Sousa
     * @date 12/11/2007
     * 
     * @param emitirContaHelper
     * @throws ControladorException
     */
    public void emitirFichaCompensacao(Collection colecaoEmitirContaHelper, int tipoConta , 
            FaturamentoGrupo faturamentoGrupo, Integer idEmpresa,
            Integer anoMesReferenciaFaturamento) throws ControladorException {

            SistemaParametro sistemaParametro = null;

            int quantidadeContas = 0;

            try {

                sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
                
                // recebe todos as contas da lista
                StringBuilder contasTxtLista = null;
                Map<Integer, Integer> mapAtualizaSequencial = null;

                try {

                    Integer sequencialImpressao = 0;
                    contasTxtLista = new StringBuilder();

                    mapAtualizaSequencial = new HashMap();
                    
                    if (colecaoEmitirContaHelper != null && !colecaoEmitirContaHelper.isEmpty()) {

                        EmitirContaHelper emitirContaHelper = null;
                        int countOrdem = 0;
                        Iterator iteratorConta = colecaoEmitirContaHelper.iterator();

                        while (iteratorConta.hasNext()) {

                            emitirContaHelper = null;
                            emitirContaHelper = (EmitirContaHelper) iteratorConta.next();
                            
                            sequencialImpressao += 1;
                            quantidadeContas++;
                            // Só para exibir no console a quantidade de contas

                            if (emitirContaHelper != null) {

                                StringBuilder contaTxt = new StringBuilder();
                                
                                String descricaoLocalidade = emitirContaHelper.getDescricaoLocalidade();
								contaTxt.append(Util.completaString(descricaoLocalidade,30));
								contaTxt.append(Util.adicionarZerosEsquedaNumero(3,
										emitirContaHelper.getCodigoSetorComercialConta().toString()));

								Imovel imovelEmitido = getControladorImovel()
										.pesquisarImovel(emitirContaHelper.getIdImovel());

								contaTxt.append(Util.adicionarZerosEsquedaNumero(
										2,imovelEmitido.getQuadra().getRota().getCodigo().toString()));

								contaTxt.append(".");

								contaTxt.append(Util.adicionarZerosEsquedaNumero(4,
										imovelEmitido.getNumeroSequencialRota().toString()));

								contaTxt.append(Util.adicionarZerosEsquedaNumero(8,
										emitirContaHelper.getIdImovel().toString()));

								// caso a coleção de contas seja de entrega
								// para o cliente responsável
								String nomeClienteUsuario = null;
								if (tipoConta == 3 || tipoConta == 4) {
									if (emitirContaHelper.getNomeImovel() != null
											&& !emitirContaHelper.getNomeImovel().equals("")) {
										nomeClienteUsuario = emitirContaHelper.getNomeImovel();

									} else {
										try {
											nomeClienteUsuario = repositorioFaturamento
													.pesquisarNomeClienteUsuarioConta(
													emitirContaHelper.getIdConta());

										} catch (ErroRepositorioException e) {
											throw new ControladorException("erro.sistema",e);
										}
									}
									contaTxt.append(Util.completaString(nomeClienteUsuario,30));
								} else {
									contaTxt.append(Util.completaString(
											emitirContaHelper.getNomeCliente(),30));
								}

								String[] enderecoImovel = getControladorEndereco()
										.pesquisarEnderecoFormatadoDividido(
												emitirContaHelper.getIdImovel());

								// endereço
								contaTxt.append(Util.completaString(enderecoImovel[0],60));

								// bairro
								contaTxt.append(Util.completaString(enderecoImovel[3],30));

								// numero indice turbidez da qualidade agua

								// numero cloro residual da qualidade agua

								FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

								filtroLocalidade.adicionarParametro(new ParametroSimples(
										FiltroLocalidade.ID,emitirContaHelper.getIdLocalidade()));

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

								Collection cLocalidade = (Collection) getControladorUtil().pesquisar(
										filtroLocalidade,Localidade.class.getName());
								
								Localidade localidade = (Localidade) cLocalidade.iterator().next();

								FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

								Collection colecaoQualidadeAgua = null;

								filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
										FiltroQualidadeAgua.LOCALIDADE_ID,localidade.getId()));
								
								filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
										FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
										emitirContaHelper.getIdSetorComercial().toString()));
								
								filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
										FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
								
								filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
								
								colecaoQualidadeAgua = getControladorUtil().pesquisar(
										filtroQualidadeAgua,QualidadeAgua.class.getName());

								if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
									
									filtroQualidadeAgua.limparListaParametros();
									
									colecaoQualidadeAgua = null;
									filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
											FiltroQualidadeAgua.LOCALIDADE_ID,localidade.getId()));
									
									filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
											FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
									
									filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
									
									colecaoQualidadeAgua = getControladorUtil().pesquisar(
											filtroQualidadeAgua, QualidadeAgua.class.getName());
								}

								if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {
									
									QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();

									// fonte
									if (qualidadeAgua.getFonteCaptacao() != null) {
										contaTxt.append(Util.completaString(qualidadeAgua.getFonteCaptacao().getDescricao(),30));
									} else {
										contaTxt.append(Util.completaString(" ",30));
									}
									
									// cloro
									if (qualidadeAgua.getNumeroCloroResidual() != null
											&& !qualidadeAgua.getNumeroCloroResidual().equals(0)) {
										contaTxt.append(Util.completaString(
												qualidadeAgua.getNumeroCloroResidual().toString(),3));
									} else {
										contaTxt.append(Util.completaString(" ",3));
									}

									// coliformes
									if (qualidadeAgua.getNumeroIndiceColiformesTotais() != null
											&& !qualidadeAgua.getNumeroIndiceColiformesTotais().equals(0)) {
										contaTxt.append(Util.completaString(
											qualidadeAgua.getNumeroIndiceColiformesTotais().toString(),8));
									} else {
										contaTxt.append(Util.completaString(" ", 8));
									}

									// nitrato
									if (qualidadeAgua.getNumeroNitrato() != null
											&& !qualidadeAgua.getNumeroNitrato().equals(0)) {
										contaTxt.append(Util.completaString(
												qualidadeAgua.getNumeroNitrato().toString(),4));
									} else {
										contaTxt.append(Util.completaString(" ",4));
									}

									// //ph
									if (qualidadeAgua.getNumeroIndicePh() != null
											&& !qualidadeAgua.getNumeroIndicePh().equals(0)) {
										contaTxt.append(Util.completaString(
												qualidadeAgua.getNumeroIndicePh().toString(),4));
									} else {
										contaTxt.append(Util.completaString(" ",4));
									}

									// //turbidez
									if (qualidadeAgua.getNumeroIndiceTurbidez() != null
											&& !qualidadeAgua.getNumeroIndiceTurbidez().equals(0)) {
										contaTxt.append(Util.completaString(
										qualidadeAgua.getNumeroIndiceTurbidez().toString(),4));
									} else {
										contaTxt.append(Util.completaString(" ",4));
									}

								} else {
									contaTxt.append(Util.completaString(" ", 53));
								}

								Collection colecaoSubCategoria = getControladorImovel()
										.obterQuantidadeEconomiasSubCategoria(imovelEmitido.getId());

								String economias = "";

								for (Iterator iter = colecaoSubCategoria.iterator(); iter.hasNext();) {
									Subcategoria subcategoria = (Subcategoria) iter.next();
									
									//agora a subcategoria ja tem o id da categoria no codigo.
									economias = economias + Util.adicionarZerosEsquedaNumero(
										3, subcategoria.getCodigo()+ "") + "/"
										+ Util.adicionarZerosEsquedaNumero(3,
								        subcategoria.getQuantidadeEconomias().toString()) + " ";

								}


								contaTxt.append(Util.adicionarZerosEsquedaNumero(
									7,quantidadeContas + ""));

								contaTxt.append(Util.completaString(localidade.getDescricao(),30));

								contaTxt.append(Util.completaString(localidade
									.getEnderecoFormatadoTituloAbreviado(),35));

								contaTxt.append(Util.completaString(localidade.getFone(),20));

								contaTxt.append(Util.completaString("0800-84-0195",15));

								// cria um objeto conta para calcular o valor da conta
								Conta conta = new Conta();
								conta.setValorAgua(emitirContaHelper.getValorAgua());
								conta.setValorEsgoto(emitirContaHelper.getValorEsgoto());
								conta.setValorCreditos(emitirContaHelper.getValorCreditos());
								conta.setDebitos(emitirContaHelper.getDebitos());
								conta.setValorImposto(emitirContaHelper.getValorImpostos());

								BigDecimal valorConta = conta.getValorTotalContaBigDecimal();
								emitirContaHelper.setValorConta(valorConta);

								// [SB0018 - Gerar Linhas das Demais Contas]
								String anoMesString = "" + emitirContaHelper.getAmReferencia();
								// formata ano mes para mes ano

								String mesNumero = anoMesString.substring(4, 6);

								String mesExtenso = Util.retornaDescricaoMes(
									new Integer(mesNumero).intValue()).toUpperCase();
								String dataExtensa = mesExtenso	+ "/"
										+ anoMesString.substring(0, 4);

//								String mesAnoFormatado = anoMesString.substring(4, 6)+ anoMesString														.substring(0, 4);
//								Integer digitoVerificadorConta = new Integer(""
//										+ emitirContaHelper.getDigitoVerificadorConta());
//								String representacaoNumericaCodBarra = null;
//
//								representacaoNumericaCodBarra = this.getControladorArrecadacao()
//										.obterRepresentacaoNumericaCodigoBarra(
//												3,
//												valorConta,
//												emitirContaHelper.getIdLocalidade(),
//												emitirContaHelper.getIdImovel(),
//												mesAnoFormatado,
//												digitoVerificadorConta,
//												null, null,
//												null, null,
//												null, null,
//												null);
//
//								contaTxt.append(Util.completaString(
//										representacaoNumericaCodBarra,48));
								contaTxt.append(Util.completaString("",48));

								// determinar Mensagem
								String[] parmsPartesConta = obterMensagemConta3Partes(
										emitirContaHelper,sistemaParametro);

								String primeiraParte = parmsPartesConta[0];
								String segundaParte = parmsPartesConta[1];

								contaTxt.append(Util.completaString(primeiraParte,65));
								contaTxt.append(Util.completaString(segundaParte,65));

								contaTxt.append(System.getProperty("line.separator"));

								contaTxt.append(Util.completaString(descricaoLocalidade,30));

								contaTxt.append(Util.adicionarZerosEsquedaNumero(2,
									imovelEmitido.getQuadra().getRota().getCodigo().toString()));

								contaTxt.append(".");

								contaTxt.append(Util.adicionarZerosEsquedaNumero(4,
									imovelEmitido.getNumeroSequencialRota().toString()));

								Imovel imovel = new Imovel();
								Localidade localidade2 = new Localidade();
								localidade2.setId(emitirContaHelper.getIdLocalidade());
								imovel.setLocalidade(localidade2);
								SetorComercial setorComercial = new SetorComercial();
								setorComercial.setCodigo(emitirContaHelper.getCodigoSetorComercialConta());
								imovel.setSetorComercial(setorComercial);
								Quadra quadra = new Quadra();
								quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());
								imovel.setQuadra(quadra);
								imovel.setLote(emitirContaHelper.getLoteConta());
								imovel.setSubLote(emitirContaHelper.getSubLoteConta());

								String inscricao = imovel.getInscricaoFormatada();

								imovel = null;

								setorComercial = null;
								quadra = null;

								contaTxt.append(Util.completaString(inscricao, 20));
								contaTxt.append(Util.completaString(" ", 12));

								String mesAnoReferencia = Util.formatarAnoMesParaMesAno(
										emitirContaHelper.getAmReferencia());

								contaTxt.append(Util.completaString(mesAnoReferencia,9));

								// data de vencimento da conta
								String dataVencimento = Util.formatarData(
										emitirContaHelper.getDataVencimentoConta());

								contaTxt.append(Util.completaString(dataVencimento,10));

								String valorContaString = Util.formatarMoedaReal(valorConta);

								// valor da conta
								FiltroContaImpressao filtroContaImpressao = new FiltroContaImpressao();
								filtroContaImpressao.adicionarParametro(new ParametroSimples(
												FiltroContaImpressao.ID,
												emitirContaHelper.getIdConta().toString()));
								filtroContaImpressao.adicionarCaminhoParaCarregamentoEntidade("contaTipo");

								Collection<ContaImpressao> cContaIm = getControladorUtil()
									.pesquisar(filtroContaImpressao,ContaImpressao.class.getName());

								ContaImpressao contaImpressao = cContaIm.iterator().next();
								Integer contaTipo = contaImpressao.getContaTipo().getId();

								contaTxt.append(Util.completaStringComEspacoAEsquerda(
														valorContaString,15));

								if (contaTipo.equals(ContaTipo.CONTA_DEBITO_AUTOMATICO)
									|| contaTipo.equals(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP.intValue())) {

									contaTxt.append(Util.completaString("NÃO PODE SER PAGO EM BANCO",65));
									contaTxt.append(Util.completaString("DÉBITO AUTOMÁTICO EM CONTA CORRENTE",65));

								} else {

									contaTxt.append(Util.completaString(" ", 65));
									contaTxt.append(Util.completaString(" ", 65));
								}

								// Mês/Ano referência da conta digito verificador

								contaTxt.append(Util.completaString(dataExtensa,14));

								contaTxt.append(Util.adicionarZerosEsquedaNumero(8,
										imovelEmitido.getId().toString()));

								contaTxt.append(Util.completaString(
									emitirContaHelper.getIdLocalidade().toString(),3));

								contaTxt.append(Util.adicionarZerosEsquedaNumero(3,
									emitirContaHelper.getCodigoSetorComercialConta().toString()));

								contaTxt.append(Util.adicionarZerosEsquedaNumero(3,
									emitirContaHelper.getIdQuadraConta().toString()));

								contaTxt.append(Util.adicionarZerosEsquedaNumero(4,
									emitirContaHelper.getLoteConta().toString()));

								contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(2,
												emitirContaHelper.getSubLoteConta().toString()), 2));
								
								// DIGITO ?????
								contaTxt.append(Util.adicionarZerosEsquedaNumero(1, "0"));

								Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
								Integer tipoLigacao = parmSituacao[0];
								Integer tipoMedicao = parmSituacao[1];

								Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
										emitirContaHelper,tipoMedicao);
								// Leitura Anterior
								String leituraAnterior = "";
								// Leitura Atual
								String leituraAtual = "";
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
										dataLeituraAnterior = Util.formatarData((Date) parmsMedicaoHistorico[3]);
									}

									if (parmsMedicaoHistorico[2] != null) {
										dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
									}

								}
								
								Object[] parmsConsumoHistorico = null;
								
								String consumoMedio = "";
								if (tipoLigacao != null) {
									try {
										parmsConsumoHistorico = repositorioMicromedicao
											.obterDadosConsumoConta(emitirContaHelper.getIdImovel(),
											emitirContaHelper.getAmReferencia(),tipoLigacao);

									} catch (ErroRepositorioException e) {
										sessionContext.setRollbackOnly();
										throw new ControladorException("erro.sistema",e);
									}

									if (parmsConsumoHistorico != null) {
										// Consumo médio
										if (parmsConsumoHistorico[2] != null) {
											consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
										}
									}
								}

								// Data Leitura Atual
								contaTxt.append(Util.completaString(dataLeituraAtual,5));
								contaTxt.append(Util.completaString(leituraAnterior,6));

								// Leitura Atual
								contaTxt.append(Util.completaString(leituraAtual,6));

								String diasConsumo = "";

								if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
									diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas(
										(Date) parmsMedicaoHistorico[3],(Date) parmsMedicaoHistorico[2]);
								}

								String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
										emitirContaHelper,tipoMedicao,diasConsumo);
								String consumoFaturamento = parmsConsumo[0];

								// Consumo faturado
								contaTxt.append(Util.completaString(consumoFaturamento,5));
								contaTxt.append(Util.completaString(consumoMedio,5));

								contaTxt.append(Util.completaString(this.obterConsumoAnterior(
									emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
									6, tipoLigacao, tipoMedicao).toString(),12));
								contaTxt.append(Util.completaString(this.obterConsumoAnterior(
									emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
									5, tipoLigacao, tipoMedicao).toString(),12));
								contaTxt.append(Util.completaString(this.obterConsumoAnterior(
                                    emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
									4,tipoLigacao,tipoMedicao).toString(),12));
								contaTxt.append(Util.completaString(this.obterConsumoAnterior(
									emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
									3,tipoLigacao,tipoMedicao).toString(),12));
								contaTxt.append(Util.completaString(this.obterConsumoAnterior(
									emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
									2,tipoLigacao,tipoMedicao).toString(),12));
								contaTxt.append(Util.completaString(this.obterConsumoAnterior(
									emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),
									1,tipoLigacao,tipoMedicao).toString(),12));

								contaTxt.append(Util.completaString(economias, 24));

								ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this
										.obterDebitoImovelOuClienteHelper(emitirContaHelper,sistemaParametro);

								if (obterDebitoImovelOuClienteHelper != null
									&& ((obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null 
									&& !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()) 
									|| (obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null 
									&& !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()))) {
									Collection colecaoContasValores = obterDebitoImovelOuClienteHelper
											.getColecaoContasValores();

									if (colecaoContasValores != null && !colecaoContasValores.isEmpty()) {
										if (colecaoContasValores.size() > 5) {
											contaTxt.append(Util.completaString("HÁ MAIS DE CINCO CONTAS EM ATRASO",40));
										} else {
											String contasAtraso = "";
											for (Iterator iter = colecaoContasValores.iterator(); iter.hasNext();) {
												ContaValoresHelper contasValores = (ContaValoresHelper) iter.next();
												contasAtraso = contasAtraso+ contasValores.
													getConta().getFormatarAnoMesParaMesAno()+ " ";
											}
											contaTxt.append(Util.completaString(contasAtraso,40));
										}
									} else {
										contaTxt.append(Util.completaString("",40));
									}
								} else {
									contaTxt.append(Util.completaString("",40));
								}

								if (imovelEmitido.getLigacaoAgua() != null) {

									if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {

										if (imovelEmitido.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico().getHidrometro() != null) {

											contaTxt.append(Util.completaString(imovelEmitido.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico().getHidrometro().getNumero(),10));
										} else {
											contaTxt.append(Util.completaString(" ",10));
										}

									} else {
										contaTxt.append(Util.completaString(" ",10));
									}

								} else {
									contaTxt.append(Util.completaString(" ", 10));
								}

								Collection colecaoContaCategoriaConsumoFaixa = null;
								try {
									colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
									.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper.getIdConta());

								} catch (ErroRepositorioException e) {
									throw new ControladorException("erro.sistema", e);
								}

								Integer consumoExcesso = 0;
								Integer consumoMinimo = 0;
								BigDecimal valorExcesso = new BigDecimal("0.0");
								BigDecimal valorMinimo = new BigDecimal("0.0");

								if (colecaoContaCategoriaConsumoFaixa == null
										|| colecaoContaCategoriaConsumoFaixa.isEmpty()) {

									consumoMinimo = emitirContaHelper.getConsumoAgua();
									valorMinimo = emitirContaHelper.getValorAgua();
								} else {
									if (!emitirContaHelper.getConsumoAgua().equals(0)) {
										for (Iterator iter = colecaoContaCategoriaConsumoFaixa
												.iterator(); iter.hasNext();) {

											ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iter.next();
											if (contaCategoriaConsumoFaixa.getConsumoAgua() != null) {
												for (Iterator iteration = colecaoSubCategoria.iterator(); iteration.hasNext();) {
													Subcategoria subCategoriaEmitir = (Subcategoria) iteration.next();

													if (contaCategoriaConsumoFaixa.getSubcategoria().getId()
															.equals(subCategoriaEmitir.getId())) {
														consumoExcesso = consumoExcesso
														+ contaCategoriaConsumoFaixa.getConsumoAgua()
														* subCategoriaEmitir.getQuantidadeEconomias();

														valorExcesso = valorExcesso.add(contaCategoriaConsumoFaixa.getValorAgua()
														.multiply(new BigDecimal(subCategoriaEmitir.getQuantidadeEconomias())));
													}

												}
											}
										}
									}

									valorMinimo = emitirContaHelper.getValorAgua().subtract(valorExcesso);
									consumoMinimo = emitirContaHelper.getConsumoAgua()- consumoExcesso;

								}

								int i = 0;
								BigDecimal valorNullo = new BigDecimal("0.00");
								Integer consumoNullo = new Integer(0);

								if (!valorMinimo.equals(valorNullo)) {
									if (!consumoMinimo.equals(consumoNullo)) {
										contaTxt.append("TARIFA MÍNIMA ÁGUA            "); // 30
										contaTxt.append(Util.completaString(consumoMinimo+ " M3",24));
										contaTxt.append(Util.completaStringComEspacoAEsquerda(
												Util.formatarMoedaReal(valorMinimo),12));
									} else {
										contaTxt.append("TARIFA MÍNIMA ÁGUA            "); // 30
										contaTxt.append(Util.completaString(consumoMinimo+ "   ",24));
										contaTxt.append(Util.completaStringComEspacoAEsquerda(
												Util.formatarMoedaReal(valorMinimo),12));
									}
									i++;
								}

								if (!consumoExcesso.equals(consumoNullo)) {
									contaTxt.append("TARIFA EXCESSO ÁGUA           "); // 30
									contaTxt.append(Util.completaString(consumoExcesso + " M3",24));
									contaTxt.append(Util.completaStringComEspacoAEsquerda(
											Util.formatarMoedaReal(valorExcesso),12));
									i++;
								}

								if (!emitirContaHelper.getPercentualEsgotoConta().equals(valorNullo)) {
									contaTxt.append("TARIFA ESGOTO                 "); // 30
									contaTxt.append(Util.completaString(
										emitirContaHelper.getPercentualEsgotoConta()+ "%",24));
									contaTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.formatarMoedaReal(emitirContaHelper.getValorEsgoto()),12));
									i++;
								}

								if (!emitirContaHelper.getValorCreditos().equals(valorNullo)) {
									contaTxt.append("CRÉDITOS E DESCONTOS          "); // 30
									contaTxt.append(Util.completaString(" ", 24));
									contaTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.formatarMoedaReal(emitirContaHelper.getValorCreditos()),12));
									i++;
								}

								if (!emitirContaHelper.getValorImpostos().equals(valorNullo)) {
									contaTxt.append("IMPOSTOS DEDUZIDOS            "); // 30
									contaTxt.append(Util.completaString(" ", 24));
									contaTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.formatarMoedaReal(emitirContaHelper.getValorImpostos()),12));
									i++;
								}

								// setando os servicos

								Conta contaId = new Conta();
								contaId.setId(emitirContaHelper.getIdConta());

								Collection<DebitoCobradoAgrupadoHelper> cDebitoCobrado = this
										.obterDebitosCobradosContaCAERN(contaId);

								int quantidadeLinhasSobrando = 10 - i;

								if (cDebitoCobrado != null && !cDebitoCobrado.isEmpty()) {

									int quantidadeDebitos = cDebitoCobrado.size();

									if (quantidadeLinhasSobrando >= quantidadeDebitos) {

										for (Iterator iter = cDebitoCobrado.iterator(); iter.hasNext();) {
											DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter.next();

											contaTxt.append(Util.completaString(debitoCobrado.getDescricaoDebitoTipo(),30)); // 30
											contaTxt.append(Util.completaString(debitoCobrado.getNumeroPrestacaoDebito() 
												+ "/" + debitoCobrado.getNumeroPrestacao(),24));
											contaTxt.append(Util.completaStringComEspacoAEsquerda(
												Util.formatarMoedaReal(debitoCobrado.getValorDebito()),12));

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
												contaTxt.append(Util.completaString(debitoCobrado.getDescricaoDebitoTipo(),30)); // 30
												contaTxt.append(Util.completaString(debitoCobrado.getNumeroPrestacaoDebito()
													+ "/" + debitoCobrado.getNumeroPrestacao(),24));
												contaTxt.append(Util.completaStringComEspacoAEsquerda(
													Util.formatarMoedaReal(debitoCobrado.getValorDebito()),12));
												i++;
											} else {

												valorAcumulado = valorAcumulado.add(debitoCobrado.getValorDebito());
												temOutros = true;
											}

											contador++;
										}
										if (temOutros) {
											contaTxt.append("OUTROS SERVIÇOS               "); // 30
											contaTxt.append(Util.completaString(" ",24));
											contaTxt.append(Util.completaStringComEspacoAEsquerda(
												Util.formatarMoedaReal(valorAcumulado),12));
											i++;
										}
									}
								}

								int quantidadeLinhasServicosSobraram = 10 - i;
								contaTxt.append(Util.completaString(" ",quantidadeLinhasServicosSobraram * 66));

								// [SB0018 - Gerar Linhas das DemaisContas]
//								anoMesString = "" + emitirContaHelper.getAmReferencia();
//								// formata ano mes para mes ano
//								mesAnoFormatado = anoMesString.substring(4, 6)
//										+ anoMesString.substring(0, 4);
//								digitoVerificadorConta = new Integer(""
//										+ emitirContaHelper.getDigitoVerificadorConta());
//								representacaoNumericaCodBarra = null;
//
//								representacaoNumericaCodBarra = this
//										.getControladorArrecadacao()
//										.obterRepresentacaoNumericaCodigoBarra(
//												3,
//												valorConta,
//												emitirContaHelper.getIdLocalidade(),
//												emitirContaHelper.getIdImovel(),
//												mesAnoFormatado,
//												digitoVerificadorConta,
//												null, null,
//												null, null,
//												null, null,
//												null);
//
//								contaTxt.append(Util.completaString(representacaoNumericaCodBarra,48));
								
								contaTxt.append(Util.completaString(" ",48));

								contaTxt.append(Util.completaString(" ", 66)); // Rodapé,
								
								//Alteração por Tiago Moreno - 23/01/2009 - Determinacao judicial (Nitrato)
								
								Conta contaEmitida = new Conta();
								
								contaEmitida.setId(emitirContaHelper.getIdConta());
								
								CreditoRealizado creditoRealizado = repositorioFaturamento.pesquisarCreditoRealizadoNitrato(contaEmitida);
								
								if (creditoRealizado != null){
									contaTxt.append(Util.completaString("Por decisão judicial de 15/05/08 - proc. 001.07.200202-7, esta conta inclui um desconto de 50% no valor da água. R$" 
											+ Util.completaString(Util.formatarMoedaReal(creditoRealizado.getValorCredito()), 13), 160));
								} else {
									contaTxt.append(Util.completaString(" ", 160));
								}
								
								//*****************************************************
                                // código do banco
                                contaTxt.append("001-9");
                                
                                // representação numérica do código de barras
                                // [SB0030 - Obter representação numérica do código de barras 
                                // da Ficha de Compensação]
                                StringBuilder nossoNumero = obterNossoNumeroFichaCompensacao("1",emitirContaHelper.getIdConta().toString()) ;
                                String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
                                
                                //RM100 - Colocar fixo o fator de vencimento 0000
                                //Date dataVencimentoMais90 = Util.adicionarNumeroDiasDeUmaData(new Date(),90);
                                //String fatorVencimento = obterFatorVencimento(dataVencimentoMais90);
                                String fatorVencimento = null;
                                
                                String especificacaoCodigoBarra = 
                                    getControladorArrecadacao().obterEspecificacaoCodigoBarraFichaCompensacao(
                                    ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
                                    emitirContaHelper.getValorConta(), nossoNumeroSemDV.toString(),
                                    ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, fatorVencimento);
                                
                                String representacaoNumericaCodigoBarraFichaCompensacao = 
                                    getControladorArrecadacao().
                                    obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);
                                
                                contaTxt.append(representacaoNumericaCodigoBarraFichaCompensacao); 
                                
                                // local de pagamento
                                contaTxt.append(Util.completaString("PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO",45));
                                
                                // vencimento
                                contaTxt.append(Util.completaString("Contra-apresentação",20));
                                
                                // cedente
                                contaTxt.append(Util.completaString("CAERN-Companhia de Águas e Esgotos do RN",50));
                                
                                // agência/código cedente
                                contaTxt.append("3795-8/9121-9");
                                
                                // data do documento
                                contaTxt.append(Util.formatarData(new Date()));
                                
                                String matriculaImovelFormatada = Util.retornaMatriculaImovelFormatada(emitirContaHelper.getIdImovel());
                                // número do documento
                                contaTxt.append(Util.completaString(matriculaImovelFormatada,10));
                                
                                // espécie do documento
                                contaTxt.append("FAT");
                                
                                // aceite
                                contaTxt.append("N");
                                
                                // data do processamento
                                contaTxt.append(Util.formatarData(new Date()));
                                
                                // nosso número com DV
                                contaTxt.append(nossoNumero);
                                
                                // carteira
                                contaTxt.append(ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO);
                                
                                // valor do documento
                                contaTxt.append(Util.completaStringComEspacoAEsquerda(valorContaString,14));
                                
                                // Sacado - linha 1.a
                                if (nomeClienteUsuario != null && 
                                        !nomeClienteUsuario.equalsIgnoreCase("")){
                                    contaTxt.append(Util.completaString(nomeClienteUsuario,30));
                                }else {
                                    contaTxt.append(Util.completaString(emitirContaHelper.getNomeCliente(), 30));
                                }
                                
                                // Sacado - linha 1.b
                                contaTxt.append(Util.completaString("   Matrícula: ",16));
                                
                                // Sacado - linha 1.c
                                contaTxt.append(Util.completaString(matriculaImovelFormatada,9));
                                
                                // Sacado - linha 1.d
                                contaTxt.append(Util.completaString("   Fatura: ",14));
                                
                                // Sacado - linha 1.d
                                // Dígito verificador da conta
                                String digitoVerificador = "" + emitirContaHelper.getDigitoVerificadorConta();
                                contaTxt.append(Util.completaString(mesAnoReferencia+ "-"+ digitoVerificador,9));

                                // Sacado - linha 2.a
//                                contaTxt.append(Util.completaString(enderecoImovel,50));
                        		// endereço
								contaTxt.append(Util.completaString(enderecoImovel[0],60));
								// bairro
								contaTxt.append(Util.completaString(enderecoImovel[3],30));
                                
                                
                                
                                // código de barras
                                if(especificacaoCodigoBarra != null && !especificacaoCodigoBarra.equals("")){
                                    // Cria o objeto para gerar o códigode barras no
                                    // padrão intercalado 2 de 5
                                    Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();
                                    
                                    contaTxt.append(Util.completaString(codigoBarraIntercalado2de5
                                                        .encodeValue(especificacaoCodigoBarra), 112));
                                    
                                }else{
                                	 contaTxt.append(Util.completaString(" ", 112));
                                }
                                
                                if(imovelEmitido.getCodigoDebitoAutomatico() != null){
                                	contaTxt.append(Util.completaString(imovelEmitido.getCodigoDebitoAutomatico().toString(),9));	
                                }

								//*****************************************************
								
								// rodapé
								// I,
								// rodapé
								// II,
								// rodapé
								// III,

								contasTxtLista.append(contaTxt.toString());

								conta = null;

								StringBuilder teste = new StringBuilder();
								teste.append(contaTxt);

								// PEDRO 19/10/2006
								contaTxt = null;
								// enquanto estiver
								// proximo
								// if
								// (iteratorConta.hasNext())
								// {
								contasTxtLista.append(System.getProperty("line.separator"));

								// adiciona o id da conta e o sequencial
								// no para serem atualizados
								mapAtualizaSequencial.put(emitirContaHelper.getIdConta(),sequencialImpressao);


                            }// fim do laço que verifica se o
                            // helper é diferente de nulo
                        }// fim laço while do iterator do objeto helper
                        countOrdem++;
                        
                        // fim do laço que verifica se a coleção é nula
                    } 

                    repositorioFaturamento.atualizarSequencialContaImpressaoFichaCompensacao(mapAtualizaSequencial);
                    mapAtualizaSequencial = null;

                    String idGrupoFaturamento = null;

                    if (faturamentoGrupo == null) {
                        idGrupoFaturamento = "G";
                    } else {
                        idGrupoFaturamento = "G" + faturamentoGrupo.getId();
                    }

                    String mesReferencia = "_Fat"
                            + anoMesReferenciaFaturamento.toString().substring(4, 6);
                    String nomeZip = null;

                    switch (tipoConta) {
                    case 0:
                        nomeZip = "BOLETO_E" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    case 1:
                        nomeZip = "BOLETO_A" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    case 2:
                        nomeZip = "BOLETO_D" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    case 3:
                        nomeZip = "BOLETO_N_R" + "_" + idGrupoFaturamento + mesReferencia + "-";
                        break;
                    case 4:
                        nomeZip = "BOLETO_D_R" + "_" + idGrupoFaturamento + mesReferencia + "-";
                        break;
                    case 5:
                        nomeZip = "BOLETO_N" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    }

                    BufferedWriter out = null;
                    ZipOutputStream zos = null;
                    File compactadoTipo = new File(nomeZip + ".zip");
                    File leituraTipo = new File(nomeZip + ".txt");

                    if (contasTxtLista != null && contasTxtLista.length() != 0) {
                        // fim de arquivo
                        contasTxtLista.append("\u0004");
                        // ************ TIPO E *************

                        zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
                        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
                        out.write(contasTxtLista.toString());
                        out.flush();
                        ZipUtil.adicionarArquivo(zos, leituraTipo);
                        zos.close();

                        out.close();
                        leituraTipo.delete();
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
                        && (inicioMensagem[0].equals("erro") || inicioMensagem[0].equals("atencao"))) {
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
                            && (inicioMensagem[0].equals("erro") || inicioMensagem[0].equals("atencao"))) {
                        throw new ControladorException(mensagem);
                    } else {
                        throw new ControladorException("erro.sistema", e);
                    }
                } else {
                    throw new ControladorException("erro.sistema", e);
                }
            }
    }

    /**
     * [UC0352] Emitir Contas e Cartas
     * 
     * [SB0029] Gerar Arquivo TXT das fichas de Compensção
     * 
     * @author Vivianne Sousa
     * @date 12/11/2007
     * 
     * @param emitirContaHelper
     * @throws ControladorException
     */
    public void emitirFichaCompensacaoOrgaoPublico(Collection colecaoEmitirContaHelper, int tipoConta , 
            FaturamentoGrupo faturamentoGrupo, Integer idEmpresa,
            Integer anoMesReferenciaFaturamento) throws ControladorException {

            SistemaParametro sistemaParametro = null;

            int quantidadeContas = 0;

            try {

                sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
                
                // recebe todos as contas da lista
                StringBuilder contasTxtLista = null;
                Map<Integer, Integer> mapAtualizaSequencial = null;

                try {

                    Integer sequencialImpressao = 0;

                    contasTxtLista = new StringBuilder();

                    mapAtualizaSequencial = new HashMap();
                    
                    if (colecaoEmitirContaHelper != null && !colecaoEmitirContaHelper.isEmpty()) {

                        EmitirContaHelper emitirContaHelper = null;
                        int countOrdem = 0;
                        Iterator iteratorConta = colecaoEmitirContaHelper.iterator();

                        while (iteratorConta.hasNext()) {

                            emitirContaHelper = null;

                            emitirContaHelper = (EmitirContaHelper) iteratorConta.next();
                            
                            sequencialImpressao += 1;
                            quantidadeContas++;

                            if (emitirContaHelper != null) {

                                StringBuilder contaTxt = new StringBuilder();
                                
								String descricaoLocalidade = emitirContaHelper
										.getDescricaoLocalidade();
								contaTxt
										.append(Util
												.completaString(
														descricaoLocalidade,
														30));
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														3,
														emitirContaHelper
																.getCodigoSetorComercialConta()
																.toString()));
		
								Imovel imovelEmitido = getControladorImovel()
										.pesquisarImovel(
												emitirContaHelper
														.getIdImovel());
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														2,
														imovelEmitido
																.getQuadra()
																.getRota()
																.getCodigo()
																.toString()));
		
								contaTxt.append(".");
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														4,
														imovelEmitido
																.getNumeroSequencialRota()
																.toString()));
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														8,
														emitirContaHelper
																.getIdImovel()
																.toString()));
		
								// caso a coleção de
								// contas
								// seja
								// de
								// entrega
								// para
								// o
								// cliente
								// responsável
								String nomeClienteUsuario = null;
								if (tipoConta == 3
										|| tipoConta == 4) {
									
									if (emitirContaHelper
											.getNomeImovel() != null
											&& !emitirContaHelper
													.getNomeImovel()
													.equals("")) {
										nomeClienteUsuario = emitirContaHelper
												.getNomeImovel();
		
									} else {
										try {
											nomeClienteUsuario = repositorioFaturamento
													.pesquisarNomeClienteUsuarioConta(emitirContaHelper
															.getIdConta());
		
										} catch (ErroRepositorioException e) {
											throw new ControladorException(
													"erro.sistema",
													e);
										}
									}
									contaTxt
											.append(Util
													.completaString(
															nomeClienteUsuario,
															30));
								} else {
									contaTxt
											.append(Util
													.completaString(
															emitirContaHelper
																	.getNomeCliente(),
															30));
								}
		
								String[] enderecoImovel = getControladorEndereco()
										.pesquisarEnderecoFormatadoDividido(
												emitirContaHelper
														.getIdImovel());
		
								// endereço
								contaTxt
										.append(Util
												.completaString(
														enderecoImovel[0],
														60));
		
								// bairro
								contaTxt
										.append(Util
												.completaString(
														enderecoImovel[3],
														30));
		
		
		
								FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		
								filtroLocalidade
										.adicionarParametro(new ParametroSimples(
												FiltroLocalidade.ID,
												emitirContaHelper
														.getIdLocalidade()));
		
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
								filtroLocalidade
										.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		
								Collection cLocalidade = (Collection) getControladorUtil()
										.pesquisar(
												filtroLocalidade,
												Localidade.class
														.getName());
								Localidade localidade = (Localidade) cLocalidade
										.iterator().next();
		
								FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
		
								Collection colecaoQualidadeAgua = null;
		
								filtroQualidadeAgua
										.adicionarParametro(new ParametroSimples(
												FiltroQualidadeAgua.LOCALIDADE_ID,
												localidade
														.getId()));
								filtroQualidadeAgua
										.adicionarParametro(new ParametroSimples(
												FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
												emitirContaHelper
														.getIdSetorComercial()
														.toString()));
								filtroQualidadeAgua
										.adicionarParametro(new ParametroSimples(
												FiltroQualidadeAgua.ANO_MES_REFERENCIA,
												emitirContaHelper
														.getAmReferencia()));
								
								filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
		
								colecaoQualidadeAgua = getControladorUtil()
										.pesquisar(
												filtroQualidadeAgua,
												QualidadeAgua.class
														.getName());
		
								if (colecaoQualidadeAgua == null
										|| colecaoQualidadeAgua
												.isEmpty()) {
									filtroQualidadeAgua
											.limparListaParametros();
									colecaoQualidadeAgua = null;
									filtroQualidadeAgua
											.adicionarParametro(new ParametroSimples(
													FiltroQualidadeAgua.LOCALIDADE_ID,
													localidade
															.getId()));
									filtroQualidadeAgua
											.adicionarParametro(new ParametroSimples(
													FiltroQualidadeAgua.ANO_MES_REFERENCIA,
													emitirContaHelper
															.getAmReferencia()));
									filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
									
									colecaoQualidadeAgua = getControladorUtil()
											.pesquisar(
													filtroQualidadeAgua,
													QualidadeAgua.class
															.getName());
								}
		
								if (colecaoQualidadeAgua != null
										&& !colecaoQualidadeAgua
												.isEmpty()) {
									QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua
											.iterator().next();
		
									// fonte
									if (qualidadeAgua.getFonteCaptacao() != null) {
										contaTxt.append(
											Util.completaString(qualidadeAgua.getFonteCaptacao().getDescricao(),30));
									} else {
										contaTxt.append(Util.completaString(" ",30));
									}
									
									// cloro
									if (qualidadeAgua
											.getNumeroCloroResidual() != null
											&& !qualidadeAgua
													.getNumeroCloroResidual()
													.equals(0)) {
										contaTxt
												.append(Util
														.completaString(
																qualidadeAgua
																		.getNumeroCloroResidual()
																		.toString(),
																3));
									} else {
										contaTxt
												.append(Util
														.completaString(
																" ",
																3));
									}
		
									// coliformes
									if (qualidadeAgua
											.getNumeroIndiceColiformesTotais() != null
											&& !qualidadeAgua
													.getNumeroIndiceColiformesTotais()
													.equals(0)) {
										contaTxt
												.append(Util
														.completaString(
																qualidadeAgua
																		.getNumeroIndiceColiformesTotais()
																		.toString(),
																8));
									} else {
										contaTxt
												.append(Util
														.completaString(
																" ",
																8));
									}
		
									// nitrato
									if (qualidadeAgua
											.getNumeroNitrato() != null
											&& !qualidadeAgua
													.getNumeroNitrato()
													.equals(0)) {
										contaTxt
												.append(Util
														.completaString(
																qualidadeAgua
																		.getNumeroNitrato()
																		.toString(),
																4));
									} else {
										contaTxt
												.append(Util
														.completaString(
																" ",
																4));
									}
		
									// //ph
									if (qualidadeAgua
											.getNumeroIndicePh() != null
											&& !qualidadeAgua
													.getNumeroIndicePh()
													.equals(0)) {
										contaTxt
												.append(Util
														.completaString(
																qualidadeAgua
																		.getNumeroIndicePh()
																		.toString(),
																4));
									} else {
										contaTxt
												.append(Util
														.completaString(
																" ",
																4));
									}
		
									// //turbidez
									if (qualidadeAgua
											.getNumeroIndiceTurbidez() != null
											&& !qualidadeAgua
													.getNumeroIndiceTurbidez()
													.equals(0)) {
										contaTxt
												.append(Util
														.completaString(
																qualidadeAgua
																		.getNumeroIndiceTurbidez()
																		.toString(),
																4));
									} else {
										contaTxt
												.append(Util
														.completaString(
																" ",
																4));
									}
		
								} else {
									contaTxt.append(Util
											.completaString(
													" ", 53));
								}
		
								Collection colecaoSubCategoria = getControladorImovel()
										.obterQuantidadeEconomiasSubCategoria(
												imovelEmitido
														.getId());
		
								String economias = "";
		
								for (Iterator iter = colecaoSubCategoria
										.iterator(); iter
										.hasNext();) {
									Subcategoria subcategoria = (Subcategoria) iter
											.next();
		
									economias = economias
											+ Util
													.adicionarZerosEsquedaNumero(
															3,
															subcategoria
																	.getCodigo()
																	+ "")
											+ "/"
											+ Util
													.adicionarZerosEsquedaNumero(
															3,
															subcategoria
																	.getQuantidadeEconomias()
																	.toString())
											+ " ";
		
								}
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														7,
														quantidadeContas
																+ ""));
		
								contaTxt
										.append(Util
												.completaString(
														localidade
																.getDescricao(),
														30));
		
								contaTxt
										.append(Util
												.completaString(
														localidade
																.getEnderecoFormatadoTituloAbreviado(),
														35));
		
								contaTxt
										.append(Util
												.completaString(
														localidade
																.getFone(),
														20));
		
								contaTxt.append(Util
										.completaString(
												"0800-84-0195",
												15));
		
								// cria um objeto conta
								// para
								// calcular o
								// valor da
								// conta
								Conta conta = new Conta();
								conta
										.setValorAgua(emitirContaHelper
												.getValorAgua());
								conta
										.setValorEsgoto(emitirContaHelper
												.getValorEsgoto());
								conta
										.setValorCreditos(emitirContaHelper
												.getValorCreditos());
								conta
										.setDebitos(emitirContaHelper
												.getDebitos());
								conta
										.setValorImposto(emitirContaHelper
												.getValorImpostos());
		
								BigDecimal valorConta = conta
										.getValorTotalContaBigDecimal();
								emitirContaHelper.setValorConta(valorConta);
		
								// [SB0018 - Gerar
								// Linhas
								// das
								// DemaisContas]
								String anoMesString = ""
										+ emitirContaHelper
												.getAmReferencia();
								// formata ano mes para mes ano
		
								String mesNumero = anoMesString
										.substring(4, 6);
		
								String mesExtenso = Util
										.retornaDescricaoMes(
												new Integer(
														mesNumero)
														.intValue())
										.toUpperCase();
								String dataExtensa = mesExtenso
										+ "/"
										+ anoMesString
												.substring(0, 4);
		
//								String mesAnoFormatado = anoMesString
//										.substring(4, 6)
//										+ anoMesString
//												.substring(0, 4);
//								Integer digitoVerificadorConta = new Integer(
//										""
//												+ emitirContaHelper
//														.getDigitoVerificadorConta());
//								String representacaoNumericaCodBarra = null;
//		
//								representacaoNumericaCodBarra = getControladorArrecadacao()
//										.obterRepresentacaoNumericaCodigoBarra(
//												3,
//												valorConta,
//												emitirContaHelper
//														.getIdLocalidade(),
//												emitirContaHelper
//														.getIdImovel(),
//												mesAnoFormatado,
//												digitoVerificadorConta,
//												null, null,
//												null, null,
//												null, null,
//												null);
//		
//								contaTxt
//										.append(Util
//												.completaString(
//														representacaoNumericaCodBarra,
//														48));
								
								contaTxt.append(Util.completaString("",	48));
		
								// determinar Mensagem
								String[] parmsPartesConta = obterMensagemConta3Partes(
										emitirContaHelper,
										sistemaParametro);
		
								String primeiraParte = parmsPartesConta[0];
								String segundaParte = parmsPartesConta[1];
		
								contaTxt.append(Util
										.completaString(
												primeiraParte,
												65));
								contaTxt.append(Util
										.completaString(
												segundaParte,
												65));
		
								contaTxt
										.append(System
												.getProperty("line.separator"));
		
								contaTxt
										.append(Util
												.completaString(
														descricaoLocalidade,
														30));
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														2,
														imovelEmitido
																.getQuadra()
																.getRota()
																.getCodigo()
																.toString()));
		
								contaTxt.append(".");
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														4,
														imovelEmitido
																.getNumeroSequencialRota()
																.toString()));
		
								Imovel imovel = new Imovel();
								Localidade localidade2 = new Localidade();
								localidade2
										.setId(emitirContaHelper
												.getIdLocalidade());
								imovel
										.setLocalidade(localidade2);
								SetorComercial setorComercial = new SetorComercial();
								setorComercial
										.setCodigo(emitirContaHelper
												.getCodigoSetorComercialConta());
								imovel
										.setSetorComercial(setorComercial);
								Quadra quadra = new Quadra();
								quadra
										.setNumeroQuadra(emitirContaHelper
												.getIdQuadraConta());
								imovel.setQuadra(quadra);
								imovel
										.setLote(emitirContaHelper
												.getLoteConta());
								imovel
										.setSubLote(emitirContaHelper
												.getSubLoteConta());
		
								String inscricao = imovel
										.getInscricaoFormatada();
		
								imovel = null;
		
								setorComercial = null;
								quadra = null;
		
								contaTxt.append(Util
										.completaString(
												inscricao, 20));
								contaTxt
										.append(Util
												.completaString(
														" ", 12));
		
								String mesAnoReferencia = Util
										.formatarAnoMesParaMesAno(emitirContaHelper
												.getAmReferencia());
		
								contaTxt
										.append(Util
												.completaString(
														mesAnoReferencia,
														9));
		
								// data de vencimento da
								// conta
								String dataVencimento = Util
										.formatarData(emitirContaHelper
												.getDataVencimentoConta());
		
								contaTxt.append(Util
										.completaString(
												dataVencimento,
												10));
		
								String valorContaString = Util
										.formatarMoedaReal(valorConta);
		
								// valor da conta
		
								FiltroContaImpressao filtroContaImpressao = new FiltroContaImpressao();
								filtroContaImpressao
										.adicionarParametro(new ParametroSimples(
												FiltroContaImpressao.ID,
												emitirContaHelper
														.getIdConta()
														.toString()));
								filtroContaImpressao
										.adicionarCaminhoParaCarregamentoEntidade("contaTipo");
		
								Collection<ContaImpressao> cContaIm = getControladorUtil()
										.pesquisar(
												filtroContaImpressao,
												ContaImpressao.class
														.getName());
		
								ContaImpressao contaImpressao = cContaIm
										.iterator().next();
								Integer contaTipo = contaImpressao
										.getContaTipo().getId();
		
								contaTxt
										.append(Util
												.completaStringComEspacoAEsquerda(
														valorContaString,
														15));
		
								if (contaTipo
										.equals(ContaTipo.CONTA_DEBITO_AUTOMATICO)
										|| contaTipo
												.equals(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP
														.intValue())) {
		
									contaTxt
											.append(Util
													.completaString(
															"NÃO PODE SER PAGO EM BANCO",
															65));
									contaTxt
											.append(Util
													.completaString(
															"DÉBITO AUTOMÁTICO EM CONTA CORRENTE",
															65));
		
								} else {
		
									contaTxt.append(Util
											.completaString(
													" ", 65));
									contaTxt.append(Util
											.completaString(
													" ", 65));
								}
		
								// Mês/Ano referência da
								// conta
								// digito
								// verificador
		
								contaTxt
										.append(Util
												.completaString(
														dataExtensa,
														14));
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														8,
														imovelEmitido
																.getId()
																.toString()));
		
								contaTxt
										.append(Util
												.completaString(
														emitirContaHelper
																.getIdLocalidade()
																.toString(),
														3));
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														3,
														emitirContaHelper
																.getCodigoSetorComercialConta()
																.toString()));
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														3,
														emitirContaHelper
																.getIdQuadraConta()
																.toString()));
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														4,
														emitirContaHelper
																.getLoteConta()
																.toString()));
		
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														2,
														emitirContaHelper
																.getSubLoteConta()
																.toString()));
								// DIGITO ?????
								contaTxt
										.append(Util
												.adicionarZerosEsquedaNumero(
														1, "0"));
		
								Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
								Integer tipoLigacao = parmSituacao[0];
								Integer tipoMedicao = parmSituacao[1];
		
								Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
										emitirContaHelper,
										tipoMedicao);
								// Leitura Anterior
								String leituraAnterior = "";
								// Leitura Atual
								String leituraAtual = "";
								// Data Leitura Anterior
								String dataLeituraAnterior = "";
								// Leitura Anterior
								String dataLeituraAtual = "";
		
								if (parmsMedicaoHistorico != null) {
		
									if (parmsMedicaoHistorico[0] != null) {
										leituraAnterior = ""
												+ (Integer) parmsMedicaoHistorico[0];
									}
		
									if (parmsMedicaoHistorico[1] != null) {
										leituraAtual = ""
												+ (Integer) parmsMedicaoHistorico[1];
									}
		
									if (parmsMedicaoHistorico[3] != null) {
										dataLeituraAnterior = Util
												.formatarData((Date) parmsMedicaoHistorico[3]);
									}
		
									if (parmsMedicaoHistorico[2] != null) {
										dataLeituraAtual = Util
												.formatarData((Date) parmsMedicaoHistorico[2]);
									}
		
								}
								Object[] parmsConsumoHistorico = null;
								String consumoMedio = "";
								if (tipoLigacao != null) {
									try {
										parmsConsumoHistorico = repositorioMicromedicao
												.obterDadosConsumoConta(
														emitirContaHelper
																.getIdImovel(),
														emitirContaHelper
																.getAmReferencia(),
														tipoLigacao);
		
									} catch (ErroRepositorioException e) {
										sessionContext
												.setRollbackOnly();
										throw new ControladorException(
												"erro.sistema",
												e);
									}
		
									if (parmsConsumoHistorico != null) {
										// Consumo médio
										if (parmsConsumoHistorico[2] != null) {
											consumoMedio = ""
													+ (Integer) parmsConsumoHistorico[2];
										}
									}
								}
		
								// Data Leitura Atual
								contaTxt
										.append(Util
												.completaString(
														dataLeituraAtual,
														5));
								contaTxt
										.append(Util
												.completaString(
														leituraAnterior,
														6));
		
								// Leitura Atual
								contaTxt
										.append(Util
												.completaString(
														leituraAtual,
														6));
		
								String diasConsumo = "";
		
								if (!dataLeituraAnterior
										.equals("")
										&& !dataLeituraAtual
												.equals("")) {
									diasConsumo = ""
											+ Util
													.obterQuantidadeDiasEntreDuasDatas(
															(Date) parmsMedicaoHistorico[3],
															(Date) parmsMedicaoHistorico[2]);
								}
		
								String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
										emitirContaHelper,
										tipoMedicao,
										diasConsumo);
								String consumoFaturamento = parmsConsumo[0];
		
								// Consumo faturado
								contaTxt
										.append(Util
												.completaString(
														consumoFaturamento,
														5));
								contaTxt
										.append(Util
												.completaString(
														consumoMedio,
														5));
		
								contaTxt
										.append(Util
												.completaString(
														this
																.obterConsumoAnterior(
																		emitirContaHelper
																				.getIdImovel(),
																		emitirContaHelper
																				.getAmReferencia(),
																		6,
																		tipoLigacao,
																		tipoMedicao)
																.toString(),
														12));
								contaTxt
										.append(Util
												.completaString(
														this
																.obterConsumoAnterior(
																		emitirContaHelper
																				.getIdImovel(),
																		emitirContaHelper
																				.getAmReferencia(),
																		5,
																		tipoLigacao,
																		tipoMedicao)
																.toString(),
														12));
								contaTxt
										.append(Util
												.completaString(
														this
																.obterConsumoAnterior(
																		emitirContaHelper
																				.getIdImovel(),
																		emitirContaHelper
																				.getAmReferencia(),
																		4,
																		tipoLigacao,
																		tipoMedicao)
																.toString(),
														12));
								contaTxt
										.append(Util
												.completaString(
														this
																.obterConsumoAnterior(
																		emitirContaHelper
																				.getIdImovel(),
																		emitirContaHelper
																				.getAmReferencia(),
																		3,
																		tipoLigacao,
																		tipoMedicao)
																.toString(),
														12));
								contaTxt
										.append(Util
												.completaString(
														this
																.obterConsumoAnterior(
																		emitirContaHelper
																				.getIdImovel(),
																		emitirContaHelper
																				.getAmReferencia(),
																		2,
																		tipoLigacao,
																		tipoMedicao)
																.toString(),
														12));
								contaTxt
										.append(Util
												.completaString(
														this
																.obterConsumoAnterior(
																		emitirContaHelper
																				.getIdImovel(),
																		emitirContaHelper
																				.getAmReferencia(),
																		1,
																		tipoLigacao,
																		tipoMedicao)
																.toString(),
														12));
		
								contaTxt.append(Util
										.completaString(
												economias, 24));
		
								ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this
										.obterDebitoImovelOuClienteHelper(
												emitirContaHelper,
												sistemaParametro);
		
								if (obterDebitoImovelOuClienteHelper != null
										&& ((obterDebitoImovelOuClienteHelper
												.getColecaoGuiasPagamentoValores() != null && !obterDebitoImovelOuClienteHelper
												.getColecaoGuiasPagamentoValores()
												.isEmpty()) || (obterDebitoImovelOuClienteHelper
												.getColecaoContasValores() != null && !obterDebitoImovelOuClienteHelper
												.getColecaoContasValores()
												.isEmpty()))) {
									Collection colecaoContasValores = obterDebitoImovelOuClienteHelper
											.getColecaoContasValores();
		
									if (colecaoContasValores != null
											&& !colecaoContasValores
													.isEmpty()) {
										if (colecaoContasValores
												.size() > 5) {
											contaTxt
													.append(Util
															.completaString(
																	"HÁ MAIS DE CINCO CONTAS EM ATRASO",
																	40));
										} else {
											String contasAtraso = "";
											for (Iterator iter = colecaoContasValores
													.iterator(); iter
													.hasNext();) {
												ContaValoresHelper contasValores = (ContaValoresHelper) iter
														.next();
												contasAtraso = contasAtraso
														+ contasValores
																.getConta()
																.getFormatarAnoMesParaMesAno()
														+ " ";
											}
											contaTxt
													.append(Util
															.completaString(
																	contasAtraso,
																	40));
										}
									} else {
										contaTxt
												.append(Util
														.completaString(
																"",
																40));
										/*contaTxt
										.append(Util
												.completaString(
														"PARABÉNS... NÃO CONSTA DÉBITOS!",
														40));*/
									}
								} else {
									contaTxt
									.append(Util
											.completaString(
													"",
													40));
								}
		
								if (imovelEmitido
										.getLigacaoAgua() != null) {
		
									if (imovelEmitido
											.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico() != null) {
		
										if (imovelEmitido
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometro() != null) {
		
											contaTxt
													.append(Util
															.completaString(
																	imovelEmitido
																			.getLigacaoAgua()
																			.getHidrometroInstalacaoHistorico()
																			.getHidrometro()
																			.getNumero(),
																	10));
										} else {
											contaTxt
													.append(Util
															.completaString(
																	" ",
																	10));
										}
		
									} else {
										contaTxt
												.append(Util
														.completaString(
																" ",
																10));
									}
		
								} else {
									contaTxt.append(Util
											.completaString(
													" ", 10));
								}
		
								Collection colecaoContaCategoriaConsumoFaixa = null;
								try {
									colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
											.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
													.getIdConta());
		
								} catch (ErroRepositorioException e) {
									throw new ControladorException(
											"erro.sistema", e);
								}
		
								Integer consumoExcesso = 0;
								Integer consumoMinimo = 0;
								BigDecimal valorExcesso = new BigDecimal(
										"0.0");
								BigDecimal valorMinimo = new BigDecimal(
										"0.0");
		
								if (colecaoContaCategoriaConsumoFaixa == null
										|| colecaoContaCategoriaConsumoFaixa
												.isEmpty()) {
		
									consumoMinimo = emitirContaHelper
											.getConsumoAgua();
									valorMinimo = emitirContaHelper
											.getValorAgua();
								} else {
									if (!emitirContaHelper
											.getConsumoAgua()
											.equals(0)) {
										for (Iterator iter = colecaoContaCategoriaConsumoFaixa
												.iterator(); iter
												.hasNext();) {
		
											ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iter
													.next();
											if (contaCategoriaConsumoFaixa
													.getConsumoAgua() != null) {
												for (Iterator iteration = colecaoSubCategoria
														.iterator(); iteration
														.hasNext();) {
													Subcategoria subCategoriaEmitir = (Subcategoria) iteration
															.next();
		
													if (contaCategoriaConsumoFaixa
															.getSubcategoria()
															.getId()
															.equals(
																	subCategoriaEmitir
																			.getId())) {
														consumoExcesso = consumoExcesso
																+ contaCategoriaConsumoFaixa
																		.getConsumoAgua()
																* subCategoriaEmitir
																		.getQuantidadeEconomias();
		
														valorExcesso = valorExcesso
																.add(contaCategoriaConsumoFaixa
																		.getValorAgua()
																		.multiply(
																				new BigDecimal(
																						subCategoriaEmitir
																								.getQuantidadeEconomias())));
													}
		
												}
											}
										}
									}
		
									valorMinimo = emitirContaHelper
											.getValorAgua()
											.subtract(
													valorExcesso);
									consumoMinimo = emitirContaHelper
											.getConsumoAgua()
											- consumoExcesso;
		
								}
		
								int i = 0;
								BigDecimal valorNullo = new BigDecimal(
										"0.00");
								Integer consumoNullo = new Integer(
										0);
		
								if (!valorMinimo
										.equals(valorNullo)) {
									if (!consumoMinimo
											.equals(consumoNullo)) {
										contaTxt
												.append("TARIFA MÍNIMA ÁGUA            "); // 30
										contaTxt
												.append(Util
														.completaString(
																consumoMinimo
																		+ " M3",
																24));
										contaTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.formatarMoedaReal(valorMinimo),
																12));
									} else {
										contaTxt
												.append("TARIFA MÍNIMA ÁGUA            "); // 30
										contaTxt
												.append(Util
														.completaString(
																consumoMinimo
																		+ "   ",
																24));
										contaTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.formatarMoedaReal(valorMinimo),
																12));
									}
									i++;
								}
		
								if (!consumoExcesso
										.equals(consumoNullo)) {
									contaTxt
											.append("TARIFA EXCESSO ÁGUA           "); // 30
									contaTxt
											.append(Util
													.completaString(
															consumoExcesso
																	+ " M3",
															24));
									contaTxt
											.append(Util
													.completaStringComEspacoAEsquerda(
															Util
																	.formatarMoedaReal(valorExcesso),
															12));
									i++;
								}
		
								if (!emitirContaHelper
										.getPercentualEsgotoConta()
										.equals(valorNullo)) {
									contaTxt
											.append("TARIFA ESGOTO                 "); // 30
									contaTxt
											.append(Util
													.completaString(
															emitirContaHelper
																	.getPercentualEsgotoConta()
																	+ "%",
															24));
									contaTxt
											.append(Util
													.completaStringComEspacoAEsquerda(
															Util
																	.formatarMoedaReal(emitirContaHelper
																			.getValorEsgoto()),
															12));
									i++;
								}
		
								if (!emitirContaHelper
										.getValorCreditos()
										.equals(valorNullo)) {
									contaTxt
										.append("CRÉDITOS E DESCONTOS          "); // 30
									contaTxt.append(Util
											.completaString(
													" ", 24));
									contaTxt
											.append(Util
													.completaStringComEspacoAEsquerda(
															Util
																	.formatarMoedaReal(emitirContaHelper
																			.getValorCreditos()),
															12));
									i++;
								}
		
								if (!emitirContaHelper
										.getValorImpostos()
										.equals(valorNullo)) {
									contaTxt
											.append("IMPOSTOS DEDUZIDOS            "); // 30
									contaTxt.append(Util
											.completaString(
													" ", 24));
									contaTxt
											.append(Util
													.completaStringComEspacoAEsquerda(
															Util
																	.formatarMoedaReal(emitirContaHelper
																			.getValorImpostos()),
															12));
									i++;
								}
		
								// setando os servicos
		
								Conta contaId = new Conta();
								contaId.setId(emitirContaHelper
										.getIdConta());
		
								Collection<DebitoCobradoAgrupadoHelper> cDebitoCobrado = this
										.obterDebitosCobradosContaCAERN(contaId);
		
								int quantidadeLinhasSobrando = 10 - i;
		
								if (cDebitoCobrado != null
										&& !cDebitoCobrado
												.isEmpty()) {
		
									int quantidadeDebitos = cDebitoCobrado
											.size();
		
									if (quantidadeLinhasSobrando >= quantidadeDebitos) {
		
										for (Iterator iter = cDebitoCobrado
												.iterator(); iter
												.hasNext();) {
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
																	24));
											contaTxt
													.append(Util
															.completaStringComEspacoAEsquerda(
																	Util
																			.formatarMoedaReal(debitoCobrado
																					.getValorDebito()),
																	12));
		
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
																		24));
												contaTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		Util
																				.formatarMoedaReal(debitoCobrado
																						.getValorDebito()),
																		12));
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
													.append("OUTROS SERVIÇOS               "); // 30
											contaTxt
													.append(Util
															.completaString(
																	" ",
																	24));
											contaTxt
													.append(Util
															.completaStringComEspacoAEsquerda(
																	Util
																			.formatarMoedaReal(valorAcumulado),
																	12));
											i++;
										}
									}
								}
		
								int quantidadeLinhasServicosSobraram = 10 - i;
								contaTxt
										.append(Util
												.completaString(
														" ",
														quantidadeLinhasServicosSobraram * 66));
		
								// [SB0018 - Gerar
								// Linhas
								// das
								// DemaisContas]
								anoMesString = ""
										+ emitirContaHelper
												.getAmReferencia();
								// formata ano mes para mes ano
//								mesAnoFormatado = anoMesString
//										.substring(4, 6)
//										+ anoMesString
//												.substring(0, 4);
//								digitoVerificadorConta = new Integer(
//										""
//												+ emitirContaHelper
//														.getDigitoVerificadorConta());
//								representacaoNumericaCodBarra = null;
//		
//								representacaoNumericaCodBarra = getControladorArrecadacao()
//										.obterRepresentacaoNumericaCodigoBarra(
//												3,
//												valorConta,
//												emitirContaHelper
//														.getIdLocalidade(),
//												emitirContaHelper
//														.getIdImovel(),
//												mesAnoFormatado,
//												digitoVerificadorConta,
//												null, null,
//												null, null,
//												null, null,
//												null);
//		
//								contaTxt.append(Util.completaString(representacaoNumericaCodBarra,48));
								contaTxt.append(Util.completaString(" ",48));
								
								contaTxt
										.append(Util
												.completaString(
														" ", 66)); // Rodapé,
								
								//Alteração por Tiago Moreno - 23/01/2009 - Determinacao judicial (Nitrato)
								
								Conta contaEmitida = new Conta();
								
								contaEmitida.setId(emitirContaHelper.getIdConta());
								
								CreditoRealizado creditoRealizado = repositorioFaturamento.pesquisarCreditoRealizadoNitrato(contaEmitida);
								
								if (creditoRealizado != null){
									contaTxt.append(Util.completaString("Por decisão judicial de 15/05/08 - proc. 001.07.200202-7, esta conta inclui um desconto de 50% no valor da água. R$" 
											+ Util.completaString(Util.formatarMoedaReal(creditoRealizado.getValorCredito()), 13), 160));
								} else {
									contaTxt.append(Util.completaString(" ", 160));
								}
								
								
								//*****************************************************
                                // código do banco
                                contaTxt.append("001-9");
                                
                                // representação numérica do código de barras
                                // [SB0030 - Obter representação numérica do código de barras 
                                // da Ficha de Compensação]
                                StringBuilder nossoNumero = obterNossoNumeroFichaCompensacao("1",emitirContaHelper.getIdConta().toString()) ;
                                String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
                                
                                //RM100 - Colocar fixo o fator de vencimento 0000
                                //Date dataVencimentoMais90 = Util.adicionarNumeroDiasDeUmaData(new Date(),90);
                                //String fatorVencimento = obterFatorVencimento(dataVencimentoMais90);
                                String fatorVencimento = null;


                                String especificacaoCodigoBarra = 
                                    getControladorArrecadacao().obterEspecificacaoCodigoBarraFichaCompensacao(
                                    ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
                                    emitirContaHelper.getValorConta(), nossoNumeroSemDV.toString(),
                                    ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, fatorVencimento);
                                
                                String representacaoNumericaCodigoBarraFichaCompensacao = 
                                    getControladorArrecadacao().
                                    obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);
                                
                                contaTxt.append(representacaoNumericaCodigoBarraFichaCompensacao); 
                                
                                // local de pagamento
                                contaTxt.append(Util.completaString("PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO",45));
                                
                                // vencimento
                                contaTxt.append(Util.completaString("Contra-apresentação",20));
                                
                                // cedente
                                contaTxt.append(Util.completaString("CAERN-Companhia de Águas e Esgotos do RN",50));
                                
                                // agência/código cedente
                                contaTxt.append("3795-8/6961-2");
                                
                                // data do documento
                                contaTxt.append(Util.formatarData(new Date()));
                                
                                String matriculaImovelFormatada = Util.retornaMatriculaImovelFormatada(emitirContaHelper.getIdImovel());
                                // número do documento
                                contaTxt.append(Util.completaString(matriculaImovelFormatada,10));
                                
                                // espécie do documento
                                contaTxt.append("FAT");
                                
                                // aceite
                                contaTxt.append("N");
                                
                                // data do processamento
                                contaTxt.append(Util.formatarData(new Date()));
                                
                                // nosso número com DV
                                contaTxt.append(nossoNumero);
                                
                                // carteira
                                contaTxt.append(ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO);
                                
                                // valor do documento
                                contaTxt.append(Util.completaStringComEspacoAEsquerda(valorContaString,14));
                                
                                // Sacado - linha 1.a
                                if (nomeClienteUsuario != null && 
                                        !nomeClienteUsuario.equalsIgnoreCase("")){
                                    contaTxt.append(Util.completaString(nomeClienteUsuario,30));
                                }else {
                                    contaTxt.append(Util.completaString(emitirContaHelper.getNomeCliente(), 30));
                                }
                                
                                // Sacado - linha 1.b
                                contaTxt.append(Util.completaString("   Matrícula: ",16));
                                
                                // Sacado - linha 1.c
                                contaTxt.append(Util.completaString(matriculaImovelFormatada,9));
                                
                                // Sacado - linha 1.d
                                contaTxt.append(Util.completaString("   Fatura: ",14));
                                
                                // Sacado - linha 1.d
                                // Dígito verificador da conta
                                String digitoVerificador = "" + emitirContaHelper.getDigitoVerificadorConta();
                                contaTxt.append(Util.completaString(mesAnoReferencia+ "-"+ digitoVerificador,9));

                                // Sacado - linha 2.a
//                                contaTxt.append(Util.completaString(enderecoImovel,50));
                        		// endereço
								contaTxt.append(Util.completaString(enderecoImovel[0],60));
								// bairro
								contaTxt.append(Util.completaString(enderecoImovel[3],30));
                                
                                // código de barras
                                if(especificacaoCodigoBarra != null && !especificacaoCodigoBarra.equals("")){
                                    // Cria o objeto para gerar o códigode barras no
                                    // padrão intercalado 2 de 5
                                    Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();
                                    
                                    contaTxt.append(Util.completaString(codigoBarraIntercalado2de5
                                                        .encodeValue(especificacaoCodigoBarra), 112));
                                    
                                }else{
                                	 contaTxt.append(Util.completaString(" ", 112));
                                }

                                if(imovelEmitido.getCodigoDebitoAutomatico() != null){
                                	contaTxt.append(Util.completaString(imovelEmitido.getCodigoDebitoAutomatico().toString(),9));	
                                }
                                
                                
								//*****************************************************
								
								// rodapé
								// I,
								// rodapé
								// II,
								// rodapé
								// III,
		
								contasTxtLista.append(contaTxt
										.toString());
		
								conta = null;
		
								StringBuilder teste = new StringBuilder();
								teste.append(contaTxt);
		
								// PEDRO 19/10/2006
								contaTxt = null;
								// enquanto estiver
								// proximo
								// if
								// (iteratorConta.hasNext())
								// {
								contasTxtLista
										.append(System
												.getProperty("line.separator"));
		
								// adiciona o id da
								// conta e o sequencial
								// no para serem
								// atualizados
								mapAtualizaSequencial.put(
										emitirContaHelper
												.getIdConta(),
										sequencialImpressao);

                            }// fim do laço que verifica se o
                            // helper é diferente de nulo
                        }// fim laço while do iterator do objeto helper
                        countOrdem++;
                        
                        // fim do laço que verifica se a coleção é nula
                    } 

                    repositorioFaturamento.atualizarSequencialContaImpressaoFichaCompensacao(mapAtualizaSequencial);
                    mapAtualizaSequencial = null;

                    String idGrupoFaturamento = null;

                    if (faturamentoGrupo == null) {
                        idGrupoFaturamento = "G";
                    } else {
                        idGrupoFaturamento = "G" + faturamentoGrupo.getId();
                    }

                    String mesReferencia = "_Fat"
                            + anoMesReferenciaFaturamento.toString().substring(4, 6);
                    String nomeZip = null;

                    switch (tipoConta) {
                    case 0:
                        nomeZip = "BOLETO_E" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    case 1:
                        nomeZip = "BOLETO_A" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    case 2:
                        nomeZip = "BOLETO_D" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    case 3:
                        nomeZip = "BOLETO_N_R" + "_" + idGrupoFaturamento + mesReferencia + "-";
                        break;
                    case 4:
                        nomeZip = "BOLETO_D_R" + "_" + idGrupoFaturamento + mesReferencia + "-";
                        break;
                    case 5:
                        nomeZip = "BOLETO_N" + "_" + idGrupoFaturamento + mesReferencia + "_Emp" + idEmpresa + "-";
                        break;
                    }

                    BufferedWriter out = null;
                    ZipOutputStream zos = null;
                    File compactadoTipo = new File(nomeZip + ".zip");
                    File leituraTipo = new File(nomeZip + ".txt");

                    if (contasTxtLista != null && contasTxtLista.length() != 0) {
                        // fim de arquivo
                        contasTxtLista.append("\u0004");
                        // ************ TIPO E *************

                        zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
                        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
                        out.write(contasTxtLista.toString());
                        out.flush();
                        ZipUtil.adicionarArquivo(zos, leituraTipo);
                        zos.close();

                        out.close();
                        leituraTipo.delete();
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
                        && (inicioMensagem[0].equals("erro") || inicioMensagem[0].equals("atencao"))) {
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
                            && (inicioMensagem[0].equals("erro") || inicioMensagem[0].equals("atencao"))) {
                        throw new ControladorException(mensagem);
                    } else {
                        throw new ControladorException("erro.sistema", e);
                    }
                } else {
                    throw new ControladorException("erro.sistema", e);
                }
            }
    }
    /**
     * [UC0352] Emitir Contas e Cartas
     * 
     * [SB0031] Obter Representação numérica do Nosso Número da Ficha de Compensação
     * 
     * @author Vivianne Sousa
     * @date 13/11/2007
     * 
     * @param colecaoConta
     * @throws ControladorException
     */
    public StringBuilder obterNossoNumeroFichaCompensacao(String idDocumentoTipo, String idDocumentoEmitido)
            throws ControladorException {
        StringBuilder nossoNumero = new StringBuilder();
        
        // é o numero do convênio fornecido pelo Banco
        // número fixo e não pode ser alterado
        nossoNumero.append("1682402");
        
        // id do documento tipo de acordo com o tipo de documento q esta sendo emitido
        nossoNumero.append(Util.adicionarZerosEsquedaNumero(2,idDocumentoTipo));
        
        // id do documento q esta sendo emitido
        nossoNumero.append(Util.adicionarZerosEsquedaNumero(8,idDocumentoEmitido));
        
        Integer digito = Util.obterDigitoVerificadorModulo11(nossoNumero.toString());
        
        nossoNumero.append("-");
        
        nossoNumero.append(digito);
        
        return nossoNumero;
    }
    

}
