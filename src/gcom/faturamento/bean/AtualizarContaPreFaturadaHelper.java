package gcom.faturamento.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AtualizarContaPreFaturadaHelper {

	public static final Integer REGISTRO_TIPO_1 = 1;
	public static final Integer REGISTRO_TIPO_2 = 2;
	public static final Integer REGISTRO_TIPO_3 = 3;
	public static final Integer REGISTRO_TIPO_4 = 4;
	public static final Integer REGISTRO_TIPO_5 = 5;

	// Constantes de campos comuns a todos os tipos
	private static final int REGISTRO_TIPO = 1;
	private static final int MATRICULA_IMOVEL = 9;

	// Constantes do registro tipo 1
	private static final int REGISTRO_TIPO_1_TIPO_MEDICAO = 1;
	private static final int REGISTRO_TIPO_1_ANO_MES_FATURAMENTO = 6;
	private static final int REGISTRO_TIPO_1_NUMERO_CONTA = 9;
	private static final int REGISTRO_TIPO_1_CODIGO_GRUPO_FATURAMENTO = 3;
	private static final int REGISTRO_TIPO_1_CODIGO_ROTA = 7;
	private static final int REGISTRO_TIPO_1_LEITURA_HIDROMETRO = 7;
	private static final int REGISTRO_TIPO_1_ANORMALIDADE_LEITURA = 2;
	private static final int REGISTRO_TIPO_1_DATA_HORA_LEITURA = 26;
	private static final int REGISTRO_TIPO_1_INDICADOR_CONFIRMACAO_LEITURA = 1;
	private static final int REGISTRO_TIPO_1_LEITURA_FATURAMENTO = 7;
	private static final int REGISTRO_TIPO_1_CONSUMO_MEDIDO = 6;
	private static final int REGISTRO_TIPO_1_CONSUMO_A_SER_COBRADO_MES = 6;
	private static final int REGISTRO_TIPO_1_CONSUMO_RATEIO_AGUA = 6;
	private static final int REGISTRO_TIPO_1_VALOR_RATEIO_AGUA = 8;
	private static final int REGISTRO_TIPO_1_CONSUMO_RATEIO_ESGOTO = 6;
	private static final int REGISTRO_TIPO_1_VALOR_RATEIO_ESGOTO = 8;
	private static final int REGISTRO_TIPO_1_TIPO_CONSUMO = 2;
	private static final int REGISTRO_TIPO_1_ANORMALIDADE_CONSUMO = 2;
	private static final int REGISTRO_TIPO_1_INDICACAO_EMISSAO_CONTA = 1;
	private static final int REGISTRO_TIPO_1_INDICADOR_GERACAO_CONTA = 1;
	private static final int REGISTRO_TIPO_1_CONSUMO_IMOVEIS_VINCULADOS = 6;
	private static final int REGISTRO_TIPO_1_ANORMALIDADE_FATURAMENTO = 2;
	private static final int REGISTRO_TIPO_1_COBRANCA_DOCUMENTO = 9;
	private static final int REGISTRO_TIPO_1_LEITURA_HIDROMETRO_ANTERIOR = 7;
	private static final int REGISTRO_TIPO_1_INSCRICAO = 17;
	private static final int REGISTRO_TIPO_1_LATITUDE = 20;
	private static final int REGISTRO_TIPO_1_LONGITUDE = 20;
	private static final int REGISTRO_TIPO_1_NUMERO_VERSAO = 12;

	// Constantes do registro tipo 2
	private static final int REGISTRO_TIPO_2_CODIGO_CATEGORIA = 1;
	private static final int REGISTRO_TIPO_2_CODIGO_SUBCATEGORIA = 3;
	private static final int REGISTRO_TIPO_2_VALOR_FATURADO_AGUA = 16;
	private static final int REGISTRO_TIPO_2_CONSUMO_FATURADO_AGUA = 6;
	private static final int REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_AGUA = 16;
	private static final int REGISTRO_TIPO_2_CONSUMO_MINIMO_AGUA = 6;
	private static final int REGISTRO_TIPO_2_VALOR_FATURADO_ESGOTO = 16;
	private static final int REGISTRO_TIPO_2_CONSUMO_FATURADO_ESGOTO = 6;
	private static final int REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_ESGOTO = 16;
	private static final int REGISTRO_TIPO_2_CONSUMO_MINIMO_ESGOTO = 6;

	// Constantes do registro tipo 3
	private static final int REGISTRO_TIPO_3_CODIGO_CATEGORIA = 1;
	private static final int REGISTRO_TIPO_3_CODIGO_SUBCATEGORIA = 3;
	private static final int REGISTRO_TIPO_3_CONSUMO_FATURADO_AGUA_FAIXA = 6;
	private static final int REGISTRO_TIPO_3_VALOR_FATURADO_AGUA_FAIXA = 16;
	private static final int REGISTRO_TIPO_3_LIMITE_INICIAL_CONSUMO_FAIXA = 6;
	private static final int REGISTRO_TIPO_3_LIMITE_FINAL_CONSUMO_FAIXA = 6;
	private static final int REGISTRO_TIPO_3_VALOR_TARIFA_AGUA_FAIXA = 16;
	private static final int REGISTRO_TIPO_3_VALOR_TARIFA_ESGOTO_FAIXA = 16;
	private static final int REGISTRO_TIPO_3_CONSUMO_FATURADO_ESGOTO_FAIXA = 6;
	private static final int REGISTRO_TIPO_3_VALOR_FATURADO_ESGOTO_FAIXA = 16;

	// Constantes do registro tipo 4
	private static final int REGISTRO_TIPO_4_TIPO_IMPOSTO = 1;
	private static final int REGISTRO_TIPO_4_DESCRICAO_IMPOSTO = 15;
	private static final int REGISTRO_TIPO_4_PERCENTUAL_ALIQUOTA = 6;
	private static final int REGISTRO_TIPO_4_VALOR_IMPOSTO = 16;

	// Constantes do registro tipo 5
	private static final int REGISTRO_TIPO_5_SEQUENCIAL_IMOVEL = 4;

	// Campos comuns
	private String tipoRegistro;
	private String matriculaImovel;

	// Tipo de registro 1
	private String tipoMedicao;
	private String anoMesFaturamento;
	private String numeroConta;
	private String codigoGrupoFaturamento;
	private String codigoRota;
	private String leituraHidrometro;
	private String anormalidadeLeitura;
	private String dataHoraLeituraHidrometro;
	private String indicadorConfirmacaoLeitura;
	private String leituraFaturamento;
	private String consumoMedido;
	private String consumoASerCobradoMes;
	private String consumoRateioAgua;
	private String valorRateioAgua;
	private String consumoRateioEsgoto;
	private String valorRateioEsgoto;
	private String tipoConsumo;
	private String anormalidadeConsumo;
	private String indicacaoEmissaoConta;
	private String localidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String numeroLote;
	private String numeroSubLote;
	private String indicadorGeracaoConta;
	private String consumoImoveisVinculados;
	private String anormalidadeFaturamento;
	private String idCobrancaDocumento;
	private String leituraHidrometroAnterior;
	private String latitude;
	private String longitude;
	private String numeroVersao;

	// Tipo de registro 2
	private String codigoCategoria;
	private String codigoSubCategoria;
	private String valorFaturadoAgua;
	private String consumoFaturadoAgua;
	private String valorTarifaMinimaAgua;
	private String consumoMinimoAgua;
	private String valorFaturadoEsgoto;
	private String consumoFaturadoEsgoto;
	private String valorTarifaMinimaEsgoto;
	private String consumoMinimoEsgoto;

	// Tipo de registro 3
	private String consumoFaturadoAguaFaixa;
	private String valorFaturadoAguaFaixa;
	private String limiteInicialConsumoFaixa;
	private String limiteFinalConsumoFaixa;
	private String valorTarifaAguaFaixa;
	private String valorTarifaEsgotoFaixa;
	private String consumoFaturadoEsgotoFaixa;
	private String valorFaturadoEsgotoFaixa;

	// Tipo de registro 4
	private String tipoImposto;
	private String descricaoImposto;
	private String percentualAliquota;
	private String valorImposto;

	// Tipo de registro 4
	private String sequencialRotaMarcacao;

	private StringBuilder arquivoImovel;

	/**
	 * 
	 * [UC0840] - Atualizar Conta Pre-Faturada
	 * 
	 * Converte o arquivo numa coleção de helpers
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param linha
	 * @return
	 */
	public Collection<AtualizarContaPreFaturadaHelper> parseHelper(
			BufferedReader buffer) throws IOException {

		Collection<AtualizarContaPreFaturadaHelper> retorno = new ArrayList();
		String linha = "";
		AtualizarContaPreFaturadaHelper helper = null;

		StringBuilder arquivoImovel = new StringBuilder();

		Map<Integer, StringBuilder> mapAtualizaConta = new HashMap<Integer, StringBuilder>();
		do {

			helper = null;
			linha = buffer.readLine();

			if (linha != null) {
				this.tipoRegistro = linha.charAt(0) + "";
			} else {
				this.tipoRegistro = "-1";
			}

			/**
			 *  Pamela Gatinho - 20/03/2012 Alterações para salvar
			 * o arquivo de retorno do IS
			 */
			if (Integer.parseInt(this.tipoRegistro) == REGISTRO_TIPO_1) {
				arquivoImovel = new StringBuilder();
				helper = this.parserRegistroTipo1(linha);
				arquivoImovel.append(linha);
				arquivoImovel.append(System.getProperty("line.separator"));

			} else if (Integer.parseInt(this.tipoRegistro) == REGISTRO_TIPO_2) {
				helper = this.parserRegistroTipo2(linha);
				arquivoImovel.append(linha);
				arquivoImovel.append(System.getProperty("line.separator"));

			} else if (Integer.parseInt(this.tipoRegistro) == REGISTRO_TIPO_3) {
				helper = this.parserRegistroTipo3(linha);
				arquivoImovel.append(linha);
				arquivoImovel.append(System.getProperty("line.separator"));

			} else if (Integer.parseInt(this.tipoRegistro) == REGISTRO_TIPO_4) {
				helper = this.parserRegistroTipo4(linha);
				arquivoImovel.append(linha);
				arquivoImovel.append(System.getProperty("line.separator"));

			} else if (Integer.parseInt(this.tipoRegistro) == REGISTRO_TIPO_5) {
				helper = this.parserRegistroTipo5(linha);
				arquivoImovel.append(linha);
				arquivoImovel.append(System.getProperty("line.separator"));
			}

			if (helper != null) {
				helper.arquivoImovel = arquivoImovel;
				retorno.add(helper);
			}

		} while (linha != null && linha.length() > 0);

		return retorno;
	}

	/**
	 * 
	 * [UC0840] - Atualizar Conta Pre-Faturada
	 * 
	 * Le o registro informado, TIPO 1.
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param linha
	 * @return
	 */
	private AtualizarContaPreFaturadaHelper parserRegistroTipo1(String linha) {
		AtualizarContaPreFaturadaHelper retorno = new AtualizarContaPreFaturadaHelper();

		Integer index = 0;

		// Tipo de registro
		retorno.tipoRegistro = linha.substring(index, index + REGISTRO_TIPO);
		index += REGISTRO_TIPO;

		// Matricula do imovel
		retorno.matriculaImovel = linha.substring(index, index
				+ MATRICULA_IMOVEL);
		index += MATRICULA_IMOVEL;

		// Tipo de medição
		retorno.tipoMedicao = linha.substring(index, index
				+ REGISTRO_TIPO_1_TIPO_MEDICAO);
		index += REGISTRO_TIPO_1_TIPO_MEDICAO;

		// Ano e mes do faturamento
		retorno.anoMesFaturamento = Util.formatarMesAnoParaAnoMes(linha
				.substring(index, index + REGISTRO_TIPO_1_ANO_MES_FATURAMENTO));
		index += REGISTRO_TIPO_1_ANO_MES_FATURAMENTO;

		// Numero da conta
		retorno.numeroConta = linha.substring(index, index
				+ REGISTRO_TIPO_1_NUMERO_CONTA);
		index += REGISTRO_TIPO_1_NUMERO_CONTA;

		// Codigo do Grupo de faturamento
		retorno.codigoGrupoFaturamento = linha.substring(index, index
				+ REGISTRO_TIPO_1_CODIGO_GRUPO_FATURAMENTO);
		index += REGISTRO_TIPO_1_CODIGO_GRUPO_FATURAMENTO;

		// Codigo da rota
		retorno.codigoRota = linha.substring(index, index
				+ REGISTRO_TIPO_1_CODIGO_ROTA);
		index += REGISTRO_TIPO_1_CODIGO_ROTA;

		// Codigo da leitura do hidrometro
		retorno.leituraHidrometro = linha.substring(index, index
				+ REGISTRO_TIPO_1_LEITURA_HIDROMETRO);
		index += REGISTRO_TIPO_1_LEITURA_HIDROMETRO;

		// Anormalidade de Leitura
		retorno.anormalidadeLeitura = linha.substring(index, index
				+ REGISTRO_TIPO_1_ANORMALIDADE_LEITURA);
		index += REGISTRO_TIPO_1_ANORMALIDADE_LEITURA;

		// Data e Hora Leitura
		retorno.dataHoraLeituraHidrometro = linha.substring(index, index
				+ REGISTRO_TIPO_1_DATA_HORA_LEITURA);
		index += REGISTRO_TIPO_1_DATA_HORA_LEITURA;

		// Indicador de Confirmacao
		retorno.indicadorConfirmacaoLeitura = linha.substring(index, index
				+ REGISTRO_TIPO_1_INDICADOR_CONFIRMACAO_LEITURA);
		index += REGISTRO_TIPO_1_INDICADOR_CONFIRMACAO_LEITURA;

		// Leitura do Faturamento
		retorno.leituraFaturamento = linha.substring(index, index
				+ REGISTRO_TIPO_1_LEITURA_FATURAMENTO);
		index += REGISTRO_TIPO_1_LEITURA_FATURAMENTO;

		// Consumo Medido no mes
		retorno.consumoMedido = linha.substring(index, index
				+ REGISTRO_TIPO_1_CONSUMO_MEDIDO);
		index += REGISTRO_TIPO_1_CONSUMO_MEDIDO;

		// Consumo a ser cobrado
		retorno.consumoASerCobradoMes = linha.substring(index, index
				+ REGISTRO_TIPO_1_CONSUMO_A_SER_COBRADO_MES);
		index += REGISTRO_TIPO_1_CONSUMO_A_SER_COBRADO_MES;

		// Consumo rateio agua
		retorno.consumoRateioAgua = linha.substring(index, index
				+ REGISTRO_TIPO_1_CONSUMO_RATEIO_AGUA);
		index += REGISTRO_TIPO_1_CONSUMO_RATEIO_AGUA;

		// Valor rateio agua
		retorno.valorRateioAgua = linha.substring(index, index
				+ REGISTRO_TIPO_1_VALOR_RATEIO_AGUA);
		index += REGISTRO_TIPO_1_VALOR_RATEIO_AGUA;

		// Consumo rateio esgoto
		retorno.consumoRateioEsgoto = linha.substring(index, index
				+ REGISTRO_TIPO_1_CONSUMO_RATEIO_ESGOTO);
		index += REGISTRO_TIPO_1_CONSUMO_RATEIO_ESGOTO;

		// Valor rateio esgoto
		retorno.valorRateioEsgoto = linha.substring(index, index
				+ REGISTRO_TIPO_1_VALOR_RATEIO_ESGOTO);
		index += REGISTRO_TIPO_1_VALOR_RATEIO_ESGOTO;

		// Tipo de consumo
		retorno.tipoConsumo = linha.substring(index, index
				+ REGISTRO_TIPO_1_TIPO_CONSUMO);
		index += REGISTRO_TIPO_1_TIPO_CONSUMO;

		// Anormalidade de consumo
		retorno.anormalidadeConsumo = linha.substring(index, index
				+ REGISTRO_TIPO_1_ANORMALIDADE_CONSUMO);
		index += REGISTRO_TIPO_1_ANORMALIDADE_CONSUMO;

		// Indicador de emissao de conta
		retorno.indicacaoEmissaoConta = linha.substring(index, index
				+ REGISTRO_TIPO_1_INDICACAO_EMISSAO_CONTA);
		index += REGISTRO_TIPO_1_INDICACAO_EMISSAO_CONTA;

		// Inscricao
		String inscricao = "";
		inscricao = linha.substring(index, index + REGISTRO_TIPO_1_INSCRICAO);
		formatarInscricao(retorno, inscricao);
		index += REGISTRO_TIPO_1_INSCRICAO;

		// Indicador Geração da conta
		retorno.indicadorGeracaoConta = linha.substring(index, index
				+ REGISTRO_TIPO_1_INDICADOR_GERACAO_CONTA);
		index += REGISTRO_TIPO_1_INDICADOR_GERACAO_CONTA;

		// consumo imóveis vinculados
		retorno.consumoImoveisVinculados = linha.substring(index, index
				+ REGISTRO_TIPO_1_CONSUMO_IMOVEIS_VINCULADOS);
		index += REGISTRO_TIPO_1_CONSUMO_IMOVEIS_VINCULADOS;

		// anormalidade de faturamento
		retorno.anormalidadeFaturamento = linha.substring(index, index
				+ REGISTRO_TIPO_1_ANORMALIDADE_FATURAMENTO);
		index += REGISTRO_TIPO_1_ANORMALIDADE_FATURAMENTO;

		// Id Cobrança Documento
		retorno.idCobrancaDocumento = linha.substring(index, index
				+ REGISTRO_TIPO_1_COBRANCA_DOCUMENTO);
		index += REGISTRO_TIPO_1_NUMERO_CONTA;

		// Codigo da leitura do hidrometro anterior
		retorno.leituraHidrometroAnterior = linha.substring(index, index
				+ REGISTRO_TIPO_1_LEITURA_HIDROMETRO_ANTERIOR);
		index += REGISTRO_TIPO_1_LEITURA_HIDROMETRO_ANTERIOR;

		
		if (linha.length() > 200) {
			// Latitude
			retorno.latitude = linha.substring( index, index + REGISTRO_TIPO_1_LATITUDE );
			index += REGISTRO_TIPO_1_LATITUDE;

			// Longitude
			retorno.longitude = linha.substring( index, index + REGISTRO_TIPO_1_LONGITUDE );
			 index += REGISTRO_TIPO_1_LONGITUDE;

			// Versão do IS
			retorno.numeroVersao = linha.substring(index, index	+ REGISTRO_TIPO_1_NUMERO_VERSAO);
			index += REGISTRO_TIPO_1_NUMERO_VERSAO;

		} else {
			// Latitude
			retorno.latitude = "0";

			// Longitude
			 retorno.longitude = "0";

			// Versão do IS
			retorno.numeroVersao = linha.substring(index, index	+ REGISTRO_TIPO_1_NUMERO_VERSAO);
			index += REGISTRO_TIPO_1_NUMERO_VERSAO;

		}

		return retorno;
	}

	/**
	 * 
	 * [UC0840] - Atualizar Conta Pre-Faturada
	 * 
	 * Le o registro informado, TIPO 2.
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param linha
	 * @return
	 */
	private AtualizarContaPreFaturadaHelper parserRegistroTipo2(String linha) {
		AtualizarContaPreFaturadaHelper retorno = new AtualizarContaPreFaturadaHelper();

		Integer index = 0;

		// Tipo de registro
		retorno.tipoRegistro = linha.substring(index, index + REGISTRO_TIPO);
		index += REGISTRO_TIPO;
		System.out.println("Tipo de Retorno: " + retorno.tipoRegistro);

		// Matricula do imovel
		retorno.matriculaImovel = linha.substring(index, index
				+ MATRICULA_IMOVEL);
		index += MATRICULA_IMOVEL;
		System.out.println("Matricula do Imovel: " + retorno.matriculaImovel);

		// Codigo da Categoria
		retorno.codigoCategoria = linha.substring(index, index
				+ REGISTRO_TIPO_2_CODIGO_CATEGORIA);
		index += REGISTRO_TIPO_2_CODIGO_CATEGORIA;

		// Codigo da Subcategoria
		retorno.codigoSubCategoria = linha.substring(index, index
				+ REGISTRO_TIPO_2_CODIGO_SUBCATEGORIA);
		index += REGISTRO_TIPO_2_CODIGO_SUBCATEGORIA;

		// Valor faturado agua
		retorno.valorFaturadoAgua = linha.substring(index, index
				+ REGISTRO_TIPO_2_VALOR_FATURADO_AGUA);
		index += REGISTRO_TIPO_2_VALOR_FATURADO_AGUA;

		// Consumo faturado de agua
		retorno.consumoFaturadoAgua = linha.substring(index, index
				+ REGISTRO_TIPO_2_CONSUMO_FATURADO_AGUA);
		index += REGISTRO_TIPO_2_CONSUMO_FATURADO_AGUA;

		// Valor tarifa minima de agua
		retorno.valorTarifaMinimaAgua = linha.substring(index, index
				+ REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_AGUA);
		index += REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_AGUA;

		// Consumo Minimo de Agua
		retorno.consumoMinimoAgua = linha.substring(index, index
				+ REGISTRO_TIPO_2_CONSUMO_MINIMO_AGUA);
		index += REGISTRO_TIPO_2_CONSUMO_MINIMO_AGUA;

		// Valor faturado esgoto
		retorno.valorFaturadoEsgoto = linha.substring(index, index
				+ REGISTRO_TIPO_2_VALOR_FATURADO_ESGOTO);
		index += REGISTRO_TIPO_2_VALOR_FATURADO_ESGOTO;

		// Consumo faturado de esgoto
		retorno.consumoFaturadoEsgoto = linha.substring(index, index
				+ REGISTRO_TIPO_2_CONSUMO_FATURADO_ESGOTO);
		index += REGISTRO_TIPO_2_CONSUMO_FATURADO_ESGOTO;

		// Valor tarifa minima de esgoto
		retorno.valorTarifaMinimaEsgoto = linha.substring(index, index
				+ REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_ESGOTO);
		index += REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_ESGOTO;

		// Consumo Minimo de esgoto
		retorno.consumoMinimoEsgoto = linha.substring(index, index
				+ REGISTRO_TIPO_2_CONSUMO_MINIMO_ESGOTO);
		index += REGISTRO_TIPO_2_CONSUMO_MINIMO_ESGOTO;

		return retorno;
	}

	/**
	 * 
	 * [UC0840] - Atualizar Conta Pre-Faturada
	 * 
	 * Le o registro informado, TIPO 3.
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param linha
	 * @return
	 */
	private AtualizarContaPreFaturadaHelper parserRegistroTipo3(String linha) {
		AtualizarContaPreFaturadaHelper retorno = new AtualizarContaPreFaturadaHelper();

		Integer index = 0;

		// Tipo de registro
		retorno.tipoRegistro = linha.substring(index, index + REGISTRO_TIPO);
		index += REGISTRO_TIPO;

		// Matricula do imovel
		retorno.matriculaImovel = linha.substring(index, index
				+ MATRICULA_IMOVEL);
		index += MATRICULA_IMOVEL;

		// Codigo da Categoria
		retorno.codigoCategoria = linha.substring(index, index
				+ REGISTRO_TIPO_3_CODIGO_CATEGORIA);
		index += REGISTRO_TIPO_3_CODIGO_CATEGORIA;

		// Codigo da Subcategoria
		retorno.codigoSubCategoria = linha.substring(index, index
				+ REGISTRO_TIPO_3_CODIGO_SUBCATEGORIA);
		index += REGISTRO_TIPO_3_CODIGO_SUBCATEGORIA;

		// Consumo faturado de agua
		retorno.consumoFaturadoAguaFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_CONSUMO_FATURADO_AGUA_FAIXA);
		index += REGISTRO_TIPO_3_CONSUMO_FATURADO_AGUA_FAIXA;

		// Valor tarifa minima de agua
		retorno.valorFaturadoAguaFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_VALOR_FATURADO_AGUA_FAIXA);
		index += REGISTRO_TIPO_3_VALOR_FATURADO_AGUA_FAIXA;

		// Limite Inicial do Consumo na Faixa
		retorno.limiteInicialConsumoFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_LIMITE_INICIAL_CONSUMO_FAIXA);
		index += REGISTRO_TIPO_3_LIMITE_INICIAL_CONSUMO_FAIXA;

		// Limite Final do consumo na Faixa
		retorno.limiteFinalConsumoFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_LIMITE_FINAL_CONSUMO_FAIXA);
		index += REGISTRO_TIPO_3_LIMITE_FINAL_CONSUMO_FAIXA;

		// Valor da Tarifa Agua na Faixa
		retorno.valorTarifaAguaFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_VALOR_TARIFA_AGUA_FAIXA);
		index += REGISTRO_TIPO_3_VALOR_TARIFA_AGUA_FAIXA;

		// Valor da Tarifa Esgoto na Faixa
		retorno.valorTarifaEsgotoFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_VALOR_TARIFA_ESGOTO_FAIXA);
		index += REGISTRO_TIPO_3_VALOR_TARIFA_ESGOTO_FAIXA;

		// Consumo faturado de esgoto
		retorno.consumoFaturadoEsgotoFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_CONSUMO_FATURADO_ESGOTO_FAIXA);
		index += REGISTRO_TIPO_3_CONSUMO_FATURADO_ESGOTO_FAIXA;

		// Valor tarifa minima de agua
		retorno.valorFaturadoEsgotoFaixa = linha.substring(index, index
				+ REGISTRO_TIPO_3_VALOR_FATURADO_ESGOTO_FAIXA);
		index += REGISTRO_TIPO_3_VALOR_FATURADO_ESGOTO_FAIXA;

		return retorno;
	}

	/**
	 * 
	 * [UC0840] - Atualizar Conta Pre-Faturada
	 * 
	 * Le o registro informado, TIPO 3.
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param linha
	 * @return
	 */
	private AtualizarContaPreFaturadaHelper parserRegistroTipo4(String linha) {
		AtualizarContaPreFaturadaHelper retorno = new AtualizarContaPreFaturadaHelper();

		Integer index = 0;

		// Tipo de registro
		retorno.tipoRegistro = linha.substring(index, index + REGISTRO_TIPO);
		index += REGISTRO_TIPO;

		// Matricula do imovel
		retorno.matriculaImovel = linha.substring(index, index
				+ MATRICULA_IMOVEL);
		index += MATRICULA_IMOVEL;

		// Tipo do imposto
		retorno.tipoImposto = linha.substring(index, index
				+ REGISTRO_TIPO_4_TIPO_IMPOSTO);
		index += REGISTRO_TIPO_4_TIPO_IMPOSTO;

		// Descrição do imposto
		retorno.descricaoImposto = linha.substring(index, index
				+ REGISTRO_TIPO_4_DESCRICAO_IMPOSTO);
		index += REGISTRO_TIPO_4_DESCRICAO_IMPOSTO;

		// Percentual da aliquota
		retorno.percentualAliquota = linha.substring(index, index
				+ REGISTRO_TIPO_4_PERCENTUAL_ALIQUOTA);
		index += REGISTRO_TIPO_4_PERCENTUAL_ALIQUOTA;

		// Valor do imposoto
		retorno.valorImposto = linha.substring(index, index
				+ REGISTRO_TIPO_4_VALOR_IMPOSTO);
		index += REGISTRO_TIPO_4_VALOR_IMPOSTO;

		return retorno;
	}

	/**
	 * 
	 * [UC0840] - Atualizar Conta Pre-Faturada
	 * 
	 * Le o registro informado, TIPO 5.
	 * 
	 * @author bruno
	 * @date 09/08/2010
	 * 
	 * @param linha
	 * @return
	 */
	private AtualizarContaPreFaturadaHelper parserRegistroTipo5(String linha) {
		AtualizarContaPreFaturadaHelper retorno = new AtualizarContaPreFaturadaHelper();

		Integer index = 0;

		// Tipo de registro
		retorno.tipoRegistro = linha.substring(index, index + REGISTRO_TIPO);
		index += REGISTRO_TIPO;

		// Matricula do imovel
		retorno.matriculaImovel = linha.substring(index, index
				+ MATRICULA_IMOVEL);
		index += MATRICULA_IMOVEL;

		// Valor do imposoto
		retorno.sequencialRotaMarcacao = linha.substring(index, index
				+ REGISTRO_TIPO_5_SEQUENCIAL_IMOVEL);
		index += REGISTRO_TIPO_5_SEQUENCIAL_IMOVEL;

		return retorno;
	}

	public Integer getAnoMesFaturamento() {

		return verificarInteger(anoMesFaturamento);
	}

	public Integer getAnormalidadeLeitura() {
		return verificarInteger(anormalidadeLeitura);
	}

	public Integer getCodigoCategoria() {
		return verificarInteger(codigoCategoria);
	}

	public Integer getCodigoGrupoFaturamento() {
		return verificarInteger(codigoGrupoFaturamento);
	}

	public Integer getCodigoRota() {
		return verificarInteger(codigoRota);
	}

	public Integer getCodigoSubCategoria() {
		return verificarInteger(codigoSubCategoria);
	}

	public Integer getConsumoASerCobradoMes() {
		return verificarInteger(consumoASerCobradoMes);
	}

	public Integer getConsumoFaturadoAgua() {
		return verificarInteger(consumoFaturadoAgua);
	}

	public Integer getConsumoFaturadoAguaFaixa() {
		return verificarInteger(consumoFaturadoAguaFaixa);
	}

	public Integer getConsumoFaturadoEsgoto() {
		return verificarInteger(consumoFaturadoEsgoto);
	}

	public Integer getConsumoFaturadoEsgotoFaixa() {
		return verificarInteger(consumoFaturadoEsgotoFaixa);
	}

	public Integer getConsumoMedido() {
		return verificarInteger(consumoMedido);
	}

	public Integer getConsumoMinimoAgua() {
		return verificarInteger(consumoMinimoAgua);
	}

	public Integer getConsumoMinimoEsgoto() {
		return verificarInteger(consumoMinimoEsgoto);
	}

	public Integer getConsumoRateioAgua() {
		return verificarInteger(consumoRateioAgua);
	}

	public Integer getConsumoRateioEsgoto() {
		return verificarInteger(consumoRateioEsgoto);
	}

	public Date getDataHoraLeituraHidrometro() {
		return Util.converteStringParaDateHora(dataHoraLeituraHidrometro,
				"yyyy-MM-dd HH:mm:ss");
	}

	public String getDescricaoImposto() {
		return descricaoImposto;
	}

	public Short getIndicacaoEmissaoConta() {
		return verificarShort(indicacaoEmissaoConta);
	}

	public Short getIndicadorConfirmacaoLeitura() {
		return verificarShort(indicadorConfirmacaoLeitura);
	}

	public Integer getLeituraFaturamento() {
		return verificarInteger(leituraFaturamento);
	}

	public Integer getLeituraHidrometro() {
		return verificarInteger(leituraHidrometro);
	}

	public Integer getLeituraHidrometroAnterior() {
		return verificarInteger(leituraHidrometroAnterior);
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public Integer getLimiteFinalConsumoFaixa() {
		return verificarInteger(limiteFinalConsumoFaixa);
	}

	public Integer getLimiteInicialConsumoFaixa() {
		return verificarInteger(limiteInicialConsumoFaixa);
	}

	public Integer getMatriculaImovel() {
		return verificarInteger(matriculaImovel);
	}

	public Integer getNumeroConta() {
		return verificarInteger(numeroConta);
	}

	public BigDecimal getPercentualAliquota() {
		return verificarBigDecimal(percentualAliquota);
	}

	public Integer getTipoConsumo() {
		return verificarInteger(tipoConsumo);
	}

	public Integer getTipoImposto() {
		return verificarInteger(tipoImposto);
	}

	public Integer getTipoMedicao() {
		return verificarInteger(tipoMedicao);
	}

	public Integer getTipoRegistro() {
		return verificarInteger(tipoRegistro);
	}

	public BigDecimal getValorFaturadoAgua() {
		return verificarBigDecimal(valorFaturadoAgua);
	}

	public BigDecimal getValorFaturadoAguaFaixa() {
		return verificarBigDecimal(valorFaturadoAguaFaixa);
	}

	public BigDecimal getValorFaturadoEsgotoFaixa() {
		return verificarBigDecimal(valorFaturadoEsgotoFaixa);
	}

	public BigDecimal getValorFaturadoEsgoto() {
		return verificarBigDecimal(valorFaturadoEsgoto);
	}

	public BigDecimal getValorImposto() {
		return verificarBigDecimal(valorImposto);
	}

	public BigDecimal getValorTarifaAguaFaixa() {
		return verificarBigDecimal(valorTarifaAguaFaixa);
	}

	public BigDecimal getValorTarifaMinimaAgua() {
		return verificarBigDecimal(valorTarifaMinimaAgua);
	}

	public BigDecimal getValorTarifaMinimaEsgoto() {
		return verificarBigDecimal(valorTarifaMinimaEsgoto);
	}

	public Integer getAnormalidadeConsumo() {
		return verificarInteger(anormalidadeConsumo);
	}

	public Integer getCodigoSetorComercial() {
		return verificarInteger(codigoSetorComercial);
	}

	public Integer getLocalidade() {
		return verificarInteger(localidade);
	}

	public Integer getNumeroLote() {
		return verificarInteger(numeroLote);
	}

	public Integer getNumeroQuadra() {
		return verificarInteger(numeroQuadra);
	}

	public Integer getNumeroSubLote() {
		return verificarInteger(numeroSubLote);
	}

	public BigDecimal getValorTarifaEsgotoFaixa() {
		return verificarBigDecimal(valorTarifaEsgotoFaixa);
	}

	private Integer verificarInteger(String string) {
		if (string == null || string.trim().equals("")) {
			return null;
		} else {
			return Integer.parseInt(string.trim());
		}
	}

	private Short verificarShort(String string) {
		if (string == null || string.trim().equals("")) {
			return null;
		} else {
			return Short.parseShort(string.trim());
		}
	}

	private BigDecimal verificarBigDecimal(String string) {
		if (string == null || string.trim().equals("")) {
			return null;
		} else {
			return Util.formatarMoedaRealparaBigDecimal(string.trim());
		}
	}

	public Short getIndicadorGeracaoConta() {
		return verificarShort(indicadorGeracaoConta);
	}

	public void setIndicadorGeracaoConta(String indicadorGeracaoConta) {
		this.indicadorGeracaoConta = indicadorGeracaoConta;
	}

	public Integer getConsumoImoveisVinculados() {
		return verificarInteger(consumoImoveisVinculados);
	}

	public Integer getAnormalidadeFaturamento() {
		return verificarInteger(anormalidadeFaturamento);
	}

	public String getSequencialRotaMarcacao() {
		return sequencialRotaMarcacao;
	}

	public Integer getIdCobrancaDocumento() {
		return verificarInteger(idCobrancaDocumento);
	}

	public String getNumeroVersao() {
		return numeroVersao;
	}

	public StringBuilder getArquivoImovel() {
		return arquivoImovel;
	}

	public BigDecimal getValorRateioAgua() {
		return verificarBigDecimal(valorRateioAgua);
	}

	public BigDecimal getValorRateioEsgoto() {
		return verificarBigDecimal(valorRateioEsgoto);
	}

	/**
	 *  Método criado para fazer o parser de inscrições com 16 e
	 * 17 caracteres.
	 */

	/**
	 * @author Pamela Gatinho
	 * @date 24/01/2011
	 * 
	 * @param inscricao
	 *            String
	 * @param retorno
	 *            AtualizarContaPreFaturadaHelper
	 */
	private void formatarInscricao(AtualizarContaPreFaturadaHelper retorno,
			String inscricao) {

		inscricao = inscricao.trim();

		if (inscricao.length() == 16) {
			retorno.localidade = inscricao.substring(0, 3);
			retorno.codigoSetorComercial = inscricao.substring(3, 6);
			retorno.numeroQuadra = inscricao.substring(6, 9);
			retorno.numeroLote = inscricao.substring(9, 13);
			retorno.numeroSubLote = inscricao.substring(13, 16);

		} else {
			retorno.localidade = inscricao.substring(0, 3);
			retorno.codigoSetorComercial = inscricao.substring(3, 6);
			retorno.numeroQuadra = inscricao.substring(6, 10);
			retorno.numeroLote = inscricao.substring(10, 14);
			retorno.numeroSubLote = inscricao.substring(14, 17);

		}
	}

}
