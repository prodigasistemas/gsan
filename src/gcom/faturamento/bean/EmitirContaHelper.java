package gcom.faturamento.bean;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class EmitirContaHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static BigDecimal VALOR_LIMITE_FICHA_COMPENSACAO = new BigDecimal("1000");

	private Integer idConta;

	private Integer idCliente;
	private String nomeCliente;
	private String cpf;
	private String cnpj;
	private Date dataVencimentoConta;
	private int amReferencia;
	private short digitoVerificadorConta;
	private Integer codigoSetorComercialConta;
	private Integer idQuadraConta;
	private Short loteConta;
	private Short subLoteConta;
	private Integer consumoAgua;
	private Integer consumoEsgoto;
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private BigDecimal valorRateioAgua;
	private BigDecimal valorRateioEsgoto;
	private BigDecimal debitos;
	private BigDecimal valorCreditos;
	private BigDecimal valorImpostos;
	private Date dataValidadeConta;
	private Integer idImovel;
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private Integer idGerenciaRegional;
	private String nomeGerenciaRegional;
	private Integer idLigacaoAguaSituacao;
	private Integer idLigacaoEsgotoSituacao;
	private String descricaoLigacaoAguaSituacao;
	private String descricaoLigacaoEsgotoSituacao;
	private Integer idImovelPerfil;
	private Integer idSetorComercial;
	private Integer idFaturamentoGrupo;
	private Integer idEmpresa;
	private BigDecimal percentualEsgotoConta;
	private BigDecimal valorConta;
	private String enderecoImovel;
	private String inscricaoImovel;
	private String idClienteResponsavel;
	private String enderecoClienteResponsavel;
	private String dadosConsumoMes1;
	private String dadosConsumoMes2;
	private String dadosConsumoMes3;
	private String dadosConsumoMes4;
	private String dadosConsumoMes5;
	private String dadosConsumoMes6;
	private String consumoFaturamento;
	private String consumoMedioDiario;
	private String leituraAnterior;
	private String leituraAtual;
	private String diasConsumo;
	private String quantidadeEconomiaConta;
	private String consumoEconomia;
	private String codigoAuxiliarString;
	private String mensagemConsumoString;
	@SuppressWarnings("rawtypes")
	private Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	private String valorContaString;
	private String primeiraParte;
	private String segundaParte;
	private String terceiraParte;
	private String mesAnoFormatado;
	private String numeroIndiceTurbidez;
	private String numeroCloroResidual;
	private String numeroNitrato;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String dataValidade;
	private String dataLeituraAnterior;
	private String dataLeituraAtual;
	private Short codigoRota;
	private Integer numeroSequencialRota;
	private Integer idImovelContaEnvio;
	private String consumoMedio;
	private String categoriaImovel;
	private String descricaoAnormalidadeConsumo;
	private String descricaoTipoConsumo;
	private String numeroHidrometro;
	private String nomeImovel;
	private BigDecimal valorCreditoBolsaAgua;
	private String contaSemCodigoBarras;
	private Integer debitoCreditoSituacaoAtualConta;
	private String contaPaga;
	private Integer idRota;
	private Integer idOrigem;
	private Integer idFuncionario;
	private String nomeFuncionario;
	private Integer contaTipo;
	private Integer numeroQuadraEntrega;
	private Integer idRotaEntrega;
	private Integer numeroSequencialRotaEntrega;
	private Integer idConsumoAnormalidade;
	

	// ---------------------------------------------------------
	// Utilizado no Emitir Segunda Via de Conta Tipo 2 (CAER)
	private String msgConta;
	private String msgLinha1Conta;
	private String msgLinha2Conta;
	private String msgLinha3Conta;
	private String msgLinha4Conta;
	private String msgLinha5Conta;
	private String valorMedioTurbidez;
	private String padraoTurbidez;
	private String valorMedioPh;
	private String valorMedioCor;
	private String valorMedioCloro;
	private String valorMedioFluor;
	private String valorMedioFerro;
	private String valorMedioColiformesTotais;
	private String valorMedioColiformesfecais;
	private String padraoPh;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoFerro;
	private String padraoColiformesTotais;
	private String padraoColiformesfecais;
	private String valorExigidoCor;
	private String valorExigidoTurbidez;
	private String valorExigidoCloro;
	private String valorExigidoFluor;
	private String valorExigidoColiformesTotais;
	private String valorExigidoColiformesTermotolerantes;
	private String valorConformeCor;
	private String valorConformeTurbidez;
	private String valorConformeFluor;
	private String valorConformeCloro;
	private String valorConformeColiformesTotais;
	private String valorConformeColiformesTermotolerantes;
	private String leituraAnormalidade;
	private String consumoAnormalidade;
	private String leituraAnteriorInformada;
	private String leituraAtualInformada;
	private String dataLeituraAtualInformada;
	private String dataLeituraAnteriorInformada;
	private String descricaoAbreviadaLeituraAnormalidade;
	private String dataPagamentoConta;
	private Short clienteComFaturaAgrupada;

	private Integer quantidadeImoveisMicro;
	private Integer somaConsumosImoveisMicro;
	private Integer consumoMacro;
	private BigDecimal valorTotalASerrateado;

	private boolean informarImpostos;

	private String descricaoImpostosEAliquotas;
	private BigDecimal percentualImpostosEAliquotas;
	private BigDecimal valorBaseCalculoImpostos;
	private BigDecimal valorImpostosEAliquotas;
	
	private String descricaoAgenciaReguladora;
	private BigDecimal percentualAgenciaReguladora;
	private BigDecimal valorAgenciaReguladora;
		

	private String agenciaReguladora;
	private String telefoneAgenciaReguladora;
	private String emailAgenciaReguladora;

	private Object[] mensagensFixas;
	private String mensagemAnormalidade;
	private String mensagemDebitos;
	private String mensagemQuitacao;
	private String mensagemBolsaAgua;

	public Short getClienteComFaturaAgrupada() {
		return clienteComFaturaAgrupada;
	}

	public void setClienteComFaturaAgrupada(Short clienteComFaturaAgrupada) {
		this.clienteComFaturaAgrupada = clienteComFaturaAgrupada;
	}

	public String getDescricaoAbreviadaLeituraAnormalidade() {
		return descricaoAbreviadaLeituraAnormalidade;
	}

	public void setDescricaoAbreviadaLeituraAnormalidade(String descricaoAbreviadaLeituraAnormalidade) {
		this.descricaoAbreviadaLeituraAnormalidade = descricaoAbreviadaLeituraAnormalidade;
	}

	public String getLeituraAnormalidade() {
		return leituraAnormalidade;
	}

	public void setLeituraAnormalidade(String leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public String getLeituraAnteriorInformada() {
		return leituraAnteriorInformada;
	}

	public void setLeituraAnteriorInformada(String leituraAnteriorInformada) {
		this.leituraAnteriorInformada = leituraAnteriorInformada;
	}

	public String getLeituraAtualInformada() {
		return leituraAtualInformada;
	}

	public void setLeituraAtualInformada(String leituraAtualInformada) {
		this.leituraAtualInformada = leituraAtualInformada;
	}

	public String getDataLeituraAtualInformada() {
		return dataLeituraAtualInformada;
	}

	public void setDataLeituraAtualInformada(String dataLeituraAtualInformada) {
		this.dataLeituraAtualInformada = dataLeituraAtualInformada;
	}

	public String getDataLeituraAnteriorInformada() {
		return dataLeituraAnteriorInformada;
	}

	public void setDataLeituraAnteriorInformada(String dataLeituraAnteriorInformada) {
		this.dataLeituraAnteriorInformada = dataLeituraAnteriorInformada;
	}

	public String getValorExigidoCor() {
		return valorExigidoCor;
	}

	public void setValorExigidoCor(String valorExigidoCor) {
		this.valorExigidoCor = valorExigidoCor;
	}

	public String getValorExigidoTurbidez() {
		return valorExigidoTurbidez;
	}

	public void setValorExigidoTurbidez(String valorExigidoTurbidez) {
		this.valorExigidoTurbidez = valorExigidoTurbidez;
	}

	public String getValorExigidoCloro() {
		return valorExigidoCloro;
	}

	public void setValorExigidoCloro(String valorExigidoCloro) {
		this.valorExigidoCloro = valorExigidoCloro;
	}

	public String getValorExigidoFluor() {
		return valorExigidoFluor;
	}

	public void setValorExigidoFluor(String valorExigidoFluor) {
		this.valorExigidoFluor = valorExigidoFluor;
	}

	public String getValorExigidoColiformesTotais() {
		return valorExigidoColiformesTotais;
	}

	public void setValorExigidoColiformesTotais(String valorExigidoColiformesTotais) {
		this.valorExigidoColiformesTotais = valorExigidoColiformesTotais;
	}

	public String getValorExigidoColiformesTermotolerantes() {
		return valorExigidoColiformesTermotolerantes;
	}

	public void setValorExigidoColiformesTermotolerantes(String valorExigidoColiformesTermotolerantes) {
		this.valorExigidoColiformesTermotolerantes = valorExigidoColiformesTermotolerantes;
	}

	public String getValorConformeCor() {
		return valorConformeCor;
	}

	public void setValorConformeCor(String valorConformeCor) {
		this.valorConformeCor = valorConformeCor;
	}

	public String getValorConformeTurbidez() {
		return valorConformeTurbidez;
	}

	public void setValorConformeTurbidez(String valorConformeTurbidez) {
		this.valorConformeTurbidez = valorConformeTurbidez;
	}

	public String getValorConformeFluor() {
		return valorConformeFluor;
	}

	public void setValorConformeFluor(String valorConformeFluor) {
		this.valorConformeFluor = valorConformeFluor;
	}

	public String getValorConformeCloro() {
		return valorConformeCloro;
	}

	public void setValorConformeCloro(String valorConformeCloro) {
		this.valorConformeCloro = valorConformeCloro;
	}

	public String getValorConformeColiformesTotais() {
		return valorConformeColiformesTotais;
	}

	public void setValorConformeColiformesTotais(String valorConformeColiformesTotais) {
		this.valorConformeColiformesTotais = valorConformeColiformesTotais;
	}

	public String getValorConformeColiformesTermotolerantes() {
		return valorConformeColiformesTermotolerantes;
	}

	public void setValorConformeColiformesTermotolerantes(String valorConformeColiformesTermotolerantes) {
		this.valorConformeColiformesTermotolerantes = valorConformeColiformesTermotolerantes;
	}

	private String enderecoLinha2;
	private String enderecoLinha3;
	private String datasVencimento;
	// ---------------------------------------------------------

	// ---------------------------------------------------------
	// utilizado quando for boleto bancario
	private String nossoNumero;
	// ---------------------------------------------------------

	private Integer codigoDebitoAutomatico;

	// ---------------------------------------------------------
	// Utilizado para pesquisar mensagem de quitação
	private Integer anoMesFaturamentoGrupo;

	// ---------------------------------------------------------

	public EmitirContaHelper() {
	}

	public EmitirContaHelper(Integer idImovel, Integer amReferencia) {
		this.idImovel = idImovel;
		this.amReferencia = amReferencia;
	}

	// utilizado no Emitir Segunda Via de Conta Compesa
	public EmitirContaHelper(Integer idConta, String nomeCliente, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta, Integer idQuadraConta, Short loteConta,
			Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos, BigDecimal valorImpostos, Date dataValidadeConta,
			Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil,
			Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao, String descricaoLigacaoEsgotoSituacao, Integer idImovelContaEnvio,
			BigDecimal percentualEsgotoConta, String nomeImovel, Integer codDebitoAutomatico, Integer anoMesFaturamentoGrupo, BigDecimal valorRateioAgua, BigDecimal valorRateioEsgoto) {

		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
		this.codigoDebitoAutomatico = codDebitoAutomatico;
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
		this.valorRateioAgua = valorRateioAgua;
		this.valorRateioEsgoto = valorRateioEsgoto;
	}

	// utilizado no Emitir Segunda Via de Conta Tipo 2 (CAER e CAERN)
	public EmitirContaHelper(Integer idConta, String nomeCliente, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta, Integer idQuadraConta, Short loteConta,
			Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos, BigDecimal valorImpostos, Date dataValidadeConta,
			Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil,
			Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao, String descricaoLigacaoEsgotoSituacao,
			BigDecimal percentualEsgotoConta, Short codigoRota, Integer numeroSequencialRota, String numeroHidrometro, Integer debitoCreditoSituacaoAtualConta, String nomeImovel) {
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.codigoRota = codigoRota;
		this.numeroSequencialRota = numeroSequencialRota;
		this.numeroHidrometro = numeroHidrometro;
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
		this.nomeImovel = nomeImovel;
	}

	// utilizado no Emitir Segunda Via de Conta Tipo 2 (CAER e CAERN)
	public EmitirContaHelper(Integer idConta, String nomeCliente, String cpf, String cnpj, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta,
			Integer idQuadraConta, Short loteConta, Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos,
			BigDecimal valorImpostos, Date dataValidadeConta, Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil, Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao, BigDecimal percentualEsgotoConta, Short codigoRota, Integer numeroSequencialRota, String numeroHidrometro, Integer debitoCreditoSituacaoAtualConta, String nomeImovel) {

		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.codigoRota = codigoRota;
		this.numeroSequencialRota = numeroSequencialRota;
		this.numeroHidrometro = numeroHidrometro;
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
		this.nomeImovel = nomeImovel;
	}

	public EmitirContaHelper(Integer idConta, String nomeCliente, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta, Integer idQuadraConta, Short loteConta,
			Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos, BigDecimal valorImpostos, Date dataValidadeConta,
			Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil,
			Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao, String descricaoLigacaoEsgotoSituacao,
			BigDecimal percentualEsgotoConta) {
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
	}

	// utilizado no Emitir Segunda Via de Conta Compesa
	public EmitirContaHelper(Integer idConta, String nomeCliente, String cpf, String cnpj, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta,
			Integer idQuadraConta, Short loteConta, Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos,
			BigDecimal valorImpostos, Date dataValidadeConta, Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil, Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao, Integer idImovelContaEnvio, BigDecimal percentualEsgotoConta, String nomeImovel) {

		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;

	}

	// utilizado no Emitir Segunda Via de Conta Compesa
	public EmitirContaHelper(Integer idConta, String nomeCliente, String cpf, String cnpj, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta,
			Integer idQuadraConta, Short loteConta, Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos,
			BigDecimal valorImpostos, Date dataValidadeConta, Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil, Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao, Integer idImovelContaEnvio, BigDecimal percentualEsgotoConta, String nomeImovel, Integer codDebitoAutomatico, int referencia, BigDecimal valorRateioAgua,
			BigDecimal valorRateioEsgoto) {

		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
		this.codigoDebitoAutomatico = codDebitoAutomatico;
		this.valorRateioAgua = valorRateioAgua;
		this.valorRateioEsgoto = valorRateioEsgoto;

	}

	// utilizado no Emitir Segunda Via de Conta Compesa
	public EmitirContaHelper(Integer idConta, String nomeCliente, String cpf, String cnpj, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta,
			Integer idQuadraConta, Short loteConta, Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos,
			BigDecimal valorImpostos, Date dataValidadeConta, Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil, Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao, Integer idImovelContaEnvio, BigDecimal percentualEsgotoConta, String nomeImovel, Integer codDebitoAutomatico, Integer anoMesFaturamentoGrupo) {
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
		this.codigoDebitoAutomatico = codDebitoAutomatico;
	}

	public EmitirContaHelper(Integer idContaHistorico, String nomeCliente, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta, Integer idQuadraConta,
			Short loteConta, Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos, BigDecimal valorImpostos,
			Date dataValidadeConta, Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional, Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade, String descricaoLigacaoAguaSituacao, String descricaoLigacaoEsgotoSituacao,
			Integer idImovelContaEnvio, BigDecimal percentualEsgotoConta, String nomeImovel, Integer codDebitoAutomatico, Integer anoMesFaturamentoGrupo) {
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
		this.idConta = idContaHistorico;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
		this.codigoDebitoAutomatico = codDebitoAutomatico;
	}

	public EmitirContaHelper(Integer idContaHistorico, String nomeCliente, Date dataVencimentoConta, int amReferencia, short digitoVerificadorConta, Integer codigoSetorComercialConta, Integer idQuadraConta,
			Short loteConta, Short subLoteConta, Integer consumoAgua, Integer consumoEsgoto, BigDecimal valorAgua, BigDecimal valorEsgoto, BigDecimal debitos, BigDecimal valorCreditos, BigDecimal valorImpostos,
			Date dataValidadeConta, BigDecimal valorRateioAgua, BigDecimal valorRateioEsgoto, Integer idImovel, Integer idLocalidade, Integer idGerenciaRegional, String nomeGerenciaRegional,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil, Integer idSetorComercial, Integer idFaturamentoGrupo, Integer idEmpresa, String descricaoLocalidade,
			String descricaoLigacaoAguaSituacao, String descricaoLigacaoEsgotoSituacao, Integer idImovelContaEnvio, BigDecimal percentualEsgotoConta, String nomeImovel, Integer codDebitoAutomatico,
			Integer anoMesFaturamentoGrupo) {
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
		this.idConta = idContaHistorico;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.valorRateioAgua = valorRateioAgua;
		this.valorRateioEsgoto = valorRateioEsgoto;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
		this.codigoDebitoAutomatico = codDebitoAutomatico;
	}

	public Integer getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Integer idOrigem) {
		this.idOrigem = idOrigem;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public int getAmReferencia() {
		return amReferencia;
	}

	public void setAmReferencia(int amReferencia) {
		this.amReferencia = amReferencia;
	}

	public Integer getCodigoSetorComercialConta() {
		return codigoSetorComercialConta;
	}

	public void setCodigoSetorComercialConta(Integer codigoSetorComercialConta) {
		this.codigoSetorComercialConta = codigoSetorComercialConta;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public Date getDataValidadeConta() {
		return dataValidadeConta;
	}

	public void setDataValidadeConta(Date dataValidadeConta) {
		this.dataValidadeConta = dataValidadeConta;
	}

	public Date getDataVencimentoConta() {
		return dataVencimentoConta;
	}

	public void setDataVencimentoConta(Date dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	public BigDecimal getDebitos() {
		return debitos;
	}

	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}

	public short getDigitoVerificadorConta() {
		return digitoVerificadorConta;
	}

	public void setDigitoVerificadorConta(short digitoVerificadorConta) {
		this.digitoVerificadorConta = digitoVerificadorConta;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdQuadraConta() {
		return idQuadraConta;
	}

	public void setIdQuadraConta(Integer idQuadraConta) {
		this.idQuadraConta = idQuadraConta;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Short getLoteConta() {
		return loteConta;
	}

	public void setLoteConta(Short loteConta) {
		this.loteConta = loteConta;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public Short getSubLoteConta() {
		return subLoteConta;
	}

	public void setSubLoteConta(Short subLoteConta) {
		this.subLoteConta = subLoteConta;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

//	public BigDecimal getValorCreditos() {
//		if (valorCreditoBolsaAgua == null) {
//			valorCreditoBolsaAgua = BigDecimal.ZERO;
//		}
//		
//		if (valorCreditos == null) {
//			valorCreditos = BigDecimal.ZERO;
//		}
//		
//		BigDecimal valorCreditoFinal = valorCreditos.subtract(valorCreditoBolsaAgua);
//		
//		if (valorCreditoFinal.doubleValue() < 0) {
//			valorCreditoFinal = valorCreditoFinal.multiply(new BigDecimal(-1));
//		} 
//		
//		return valorCreditoFinal;
//	}
	
	public BigDecimal getValorCreditos() {
		return this.valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoLigacaoAguaSituacao() {
		return descricaoLigacaoAguaSituacao;
	}

	public void setDescricaoLigacaoAguaSituacao(String descricaoLigacaoAguaSituacao) {
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
	}

	public String getDescricaoLigacaoEsgotoSituacao() {
		return descricaoLigacaoEsgotoSituacao;
	}

	public void setDescricaoLigacaoEsgotoSituacao(String descricaoLigacaoEsgotoSituacao) {
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
	}

	public BigDecimal getPercentualEsgotoConta() {
		return percentualEsgotoConta;
	}

	public void setPercentualEsgotoConta(BigDecimal percentualEsgotoConta) {
		this.percentualEsgotoConta = percentualEsgotoConta;
	}

	public String getFatura() {
		String mesAnoReferencia = Util.formatarAnoMesParaMesAno(getAmReferencia());
		String digitoVerificador = "" + getDigitoVerificadorConta();
		return mesAnoReferencia + "-" + digitoVerificador;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}

	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getDadosConsumoMes1() {
		return dadosConsumoMes1;
	}

	public void setDadosConsumoMes1(String dadosConsumoMes1) {
		this.dadosConsumoMes1 = dadosConsumoMes1;
	}

	public String getDadosConsumoMes2() {
		return dadosConsumoMes2;
	}

	public void setDadosConsumoMes2(String dadosConsumoMes2) {
		this.dadosConsumoMes2 = dadosConsumoMes2;
	}

	public String getDadosConsumoMes3() {
		return dadosConsumoMes3;
	}

	public void setDadosConsumoMes3(String dadosConsumoMes3) {
		this.dadosConsumoMes3 = dadosConsumoMes3;
	}

	public String getDadosConsumoMes4() {
		return dadosConsumoMes4;
	}

	public void setDadosConsumoMes4(String dadosConsumoMes4) {
		this.dadosConsumoMes4 = dadosConsumoMes4;
	}

	public String getDadosConsumoMes5() {
		return dadosConsumoMes5;
	}

	public void setDadosConsumoMes5(String dadosConsumoMes5) {
		this.dadosConsumoMes5 = dadosConsumoMes5;
	}

	public String getDadosConsumoMes6() {
		return dadosConsumoMes6;
	}

	public void setDadosConsumoMes6(String dadosConsumoMes6) {
		this.dadosConsumoMes6 = dadosConsumoMes6;
	}

	public String getConsumoFaturamento() {
		return consumoFaturamento;
	}

	public void setConsumoFaturamento(String consumoFaturamento) {
		this.consumoFaturamento = consumoFaturamento;
	}

	public String getConsumoMedioDiario() {
		return consumoMedioDiario;
	}

	public void setConsumoMedioDiario(String consumoMedioDiario) {
		this.consumoMedioDiario = consumoMedioDiario;
	}

	public String getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}

	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}

	public String getDescricaoTipoConsumo() {
		return descricaoTipoConsumo;
	}

	public void setDescricaoTipoConsumo(String descricaoTipoConsumo) {
		this.descricaoTipoConsumo = descricaoTipoConsumo;
	}

	public String getCodigoAuxiliarString() {
		return codigoAuxiliarString;
	}

	public void setCodigoAuxiliarString(String codigoAuxiliarString) {
		this.codigoAuxiliarString = codigoAuxiliarString;
	}

	public String getConsumoEconomia() {
		return consumoEconomia;
	}

	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	@SuppressWarnings("rawtypes")
	public Collection getColecaoContaLinhasDescricaoServicosTarifasTotalHelper() {
		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}

	@SuppressWarnings("rawtypes")
	public void setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper) {
		this.colecaoContaLinhasDescricaoServicosTarifasTotalHelper = colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}

	public String getMensagemConsumoString() {
		return mensagemConsumoString;
	}

	public void setMensagemConsumoString(String mensagemConsumoString) {
		this.mensagemConsumoString = mensagemConsumoString;
	}

	public String getMesAnoFormatado() {
		return mesAnoFormatado;
	}

	public void setMesAnoFormatado(String mesAnoFormatado) {
		this.mesAnoFormatado = mesAnoFormatado;
	}

	public String getNumeroCloroResidual() {
		return numeroCloroResidual;
	}

	public void setNumeroCloroResidual(String numeroCloroResidual) {
		this.numeroCloroResidual = numeroCloroResidual;
	}

	public String getNumeroIndiceTurbidez() {
		return numeroIndiceTurbidez;
	}

	public void setNumeroIndiceTurbidez(String numeroIndiceTurbidez) {
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	}

	public String getPrimeiraParte() {
		return primeiraParte;
	}

	public void setPrimeiraParte(String primeiraParte) {
		this.primeiraParte = primeiraParte;
	}

	public String getQuantidadeEconomiaConta() {
		return quantidadeEconomiaConta;
	}

	public void setQuantidadeEconomiaConta(String quantidadeEconomiaConta) {
		this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getSegundaParte() {
		return segundaParte;
	}

	public void setSegundaParte(String segundaParte) {
		this.segundaParte = segundaParte;
	}

	public String getTerceiraParte() {
		return terceiraParte;
	}

	public void setTerceiraParte(String terceiraParte) {
		this.terceiraParte = terceiraParte;
	}

	public String getValorContaString() {
		return valorContaString;
	}

	public void setValorContaString(String valorContaString) {
		this.valorContaString = valorContaString;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}

	public String getRotaEntrega() {
		String rotaEntrega = "";

		if (getCodigoRota() != null) {
			rotaEntrega = Util.adicionarZerosEsquedaNumero(2, getCodigoRota().toString());
		}

		if (getNumeroSequencialRota() != null) {
			rotaEntrega = rotaEntrega + "." + Util.adicionarZerosEsquedaNumero(4, getNumeroSequencialRota().toString());
		}

		return rotaEntrega;
	}

	public Integer getIdImovelContaEnvio() {
		return idImovelContaEnvio;
	}

	public void setIdImovelContaEnvio(Integer idImovelContaEnvio) {
		this.idImovelContaEnvio = idImovelContaEnvio;
	}

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}
	
	public BigDecimal getValorCreditoBolsaAgua() {
		BigDecimal valorAguaEsgoto = BigDecimal.ZERO;

		if (valorAgua != null && valorAgua.doubleValue() > 0) {
			valorAguaEsgoto.add(valorAgua);
		}
				
		if (valorEsgoto != null && valorEsgoto.doubleValue() > 0) {
				valorAguaEsgoto.add(valorEsgoto);
		}
		
		if (valorCreditoBolsaAgua != null && valorAguaEsgoto.doubleValue() > valorCreditoBolsaAgua.doubleValue()) {
			return valorCreditoBolsaAgua;
		} else {
			return valorAguaEsgoto;
		}
	}

	public void setValorCreditoBolsaAgua(BigDecimal valorCreditoBolsaAgua) {
		this.valorCreditoBolsaAgua = valorCreditoBolsaAgua;
	}

	public String getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public String get1DataVencimento() {
		Integer mesAnoReferencia = getAmReferencia();
		mesAnoReferencia = Util.somaUmMesAnoMesReferencia(mesAnoReferencia);

		String mesAnoFormatado = Util.formatarAnoMesParaMesAno(mesAnoReferencia);

		return "04/" + mesAnoFormatado;
	}

	public String getNumeroHidrometro() {

		if (numeroHidrometro != null) {
			return numeroHidrometro;
		} else {
			return "";
		}
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}

	public String getContaSemCodigoBarras() {
		return contaSemCodigoBarras;
	}

	public void setContaSemCodigoBarras(String contaSemCodigoBarras) {
		this.contaSemCodigoBarras = contaSemCodigoBarras;
	}

	public Integer getDebitoCreditoSituacaoAtualConta() {
		return debitoCreditoSituacaoAtualConta;
	}

	public void setDebitoCreditoSituacaoAtualConta(Integer debitoCreditoSituacaoAtualConta) {
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	}

	public String getContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(String contaPaga) {
		this.contaPaga = contaPaga;
	}

	public String getMsgConta() {
		return msgConta;
	}

	public void setMsgConta(String msgConta) {
		this.msgConta = msgConta;
	}

	public String getMsgLinha1Conta() {
		return msgLinha1Conta;
	}

	public void setMsgLinha1Conta(String msgLinha1Conta) {
		this.msgLinha1Conta = msgLinha1Conta;
	}

	public String getMsgLinha2Conta() {
		return msgLinha2Conta;
	}

	public void setMsgLinha2Conta(String msgLinha2Conta) {
		this.msgLinha2Conta = msgLinha2Conta;
	}

	public String getMsgLinha3Conta() {
		return msgLinha3Conta;
	}

	public void setMsgLinha3Conta(String msgLinha3Conta) {
		this.msgLinha3Conta = msgLinha3Conta;
	}

	public String getMsgLinha4Conta() {
		return msgLinha4Conta;
	}

	public void setMsgLinha4Conta(String msgLinha4Conta) {
		this.msgLinha4Conta = msgLinha4Conta;
	}

	public String getMsgLinha5Conta() {
		return msgLinha5Conta;
	}

	public void setMsgLinha5Conta(String msgLinha5Conta) {
		this.msgLinha5Conta = msgLinha5Conta;
	}

	public String getPadraoCloro() {
		return padraoCloro;
	}

	public void setPadraoCloro(String padraoCloro) {
		this.padraoCloro = padraoCloro;
	}

	public String getPadraoColiformesfecais() {
		return padraoColiformesfecais;
	}

	public void setPadraoColiformesfecais(String padraoColiformesfecais) {
		this.padraoColiformesfecais = padraoColiformesfecais;
	}

	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}

	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}

	public String getPadraoCor() {
		return padraoCor;
	}

	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}

	public String getPadraoFerro() {
		return padraoFerro;
	}

	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}

	public String getPadraoFluor() {
		return padraoFluor;
	}

	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}

	public String getPadraoPh() {
		return padraoPh;
	}

	public void setPadraoPh(String padraoPh) {
		this.padraoPh = padraoPh;
	}

	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}

	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}

	public String getValorMedioCloro() {
		return valorMedioCloro;
	}

	public void setValorMedioCloro(String valorMedioCloro) {
		this.valorMedioCloro = valorMedioCloro;
	}

	public String getValorMedioColiformesfecais() {
		return valorMedioColiformesfecais;
	}

	public void setValorMedioColiformesfecais(String valorMedioColiformesfecais) {
		this.valorMedioColiformesfecais = valorMedioColiformesfecais;
	}

	public String getValorMedioColiformesTotais() {
		return valorMedioColiformesTotais;
	}

	public void setValorMedioColiformesTotais(String valorMedioColiformesTotais) {
		this.valorMedioColiformesTotais = valorMedioColiformesTotais;
	}

	public String getValorMedioCor() {
		return valorMedioCor;
	}

	public void setValorMedioCor(String valorMedioCor) {
		this.valorMedioCor = valorMedioCor;
	}

	public String getValorMedioFerro() {
		return valorMedioFerro;
	}

	public void setValorMedioFerro(String valorMedioFerro) {
		this.valorMedioFerro = valorMedioFerro;
	}

	public String getValorMedioFluor() {
		return valorMedioFluor;
	}

	public void setValorMedioFluor(String valorMedioFluor) {
		this.valorMedioFluor = valorMedioFluor;
	}

	public String getValorMedioPh() {
		return valorMedioPh;
	}

	public void setValorMedioPh(String valorMedioPh) {
		this.valorMedioPh = valorMedioPh;
	}

	public String getValorMedioTurbidez() {
		return valorMedioTurbidez;
	}

	public void setValorMedioTurbidez(String valorMedioTurbidez) {
		this.valorMedioTurbidez = valorMedioTurbidez;
	}

	public String getEnderecoLinha2() {
		return enderecoLinha2;
	}

	public void setEnderecoLinha2(String enderecoLinha2) {
		this.enderecoLinha2 = enderecoLinha2;
	}

	public String getEnderecoLinha3() {
		return enderecoLinha3;
	}

	public void setEnderecoLinha3(String enderecoLinha3) {
		this.enderecoLinha3 = enderecoLinha3;
	}

	public String getDatasVencimento() {
		return datasVencimento;
	}

	public void setDatasVencimento(String datasVencimento) {
		this.datasVencimento = datasVencimento;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public Integer getContaTipo() {
		return contaTipo;
	}

	public void setContaTipo(Integer contaTipo) {
		this.contaTipo = contaTipo;
	}

	public Integer getIdRotaEntrega() {
		return idRotaEntrega;
	}

	public void setIdRotaEntrega(Integer idRotaEntrega) {
		this.idRotaEntrega = idRotaEntrega;
	}

	public Integer getNumeroSequencialRotaEntrega() {
		return numeroSequencialRotaEntrega;
	}

	public void setNumeroSequencialRotaEntrega(Integer numeroSequencialRotaEntrega) {
		this.numeroSequencialRotaEntrega = numeroSequencialRotaEntrega;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}

	public void setCodigoDebitoAutomatico(Integer codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}

	public Integer getAnoMesFaturamentoGrupo() {
		return anoMesFaturamentoGrupo;
	}

	public void setAnoMesFaturamentoGrupo(Integer anoMesFaturamentoGrupo) {
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
	}

	public String getNumeroNitrato() {
		return numeroNitrato;
	}

	public void setNumeroNitrato(String numeroNitrato) {
		this.numeroNitrato = numeroNitrato;
	}

	public String getDataPagamentoConta() {
		return dataPagamentoConta;
	}

	public void setDataPagamentoConta(String dataPagamentoConta) {
		this.dataPagamentoConta = dataPagamentoConta;
	}

	public BigDecimal getValorRateioAgua() {
		return valorRateioAgua;
	}

	public void setValorRateioAgua(BigDecimal valorRateioAgua) {
		this.valorRateioAgua = valorRateioAgua;
	}

	public BigDecimal getValorRateioEsgoto() {
		return valorRateioEsgoto;
	}

	public void setValorRateioEsgoto(BigDecimal valorRateioEsgoto) {
		this.valorRateioEsgoto = valorRateioEsgoto;
	}

	public Integer getNumeroQuadraEntrega() {
		return numeroQuadraEntrega;
	}

	public void setNumeroQuadraEntrega(Integer numeroQuadraEntrega) {
		this.numeroQuadraEntrega = numeroQuadraEntrega;
	}

	public String getValorTotalConta() {
		BigDecimal valorTotalConta = new BigDecimal("0.00");

		if (this.getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAgua());
		}

		if (this.getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorEsgoto());
		}

		if (this.getValorRateioAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorRateioAgua());
		}

		if (this.getValorRateioEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorRateioEsgoto());
		}

		if (this.getDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getDebitos());
		}

		if (this.getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorCreditos());
		}
		
//		if (this.getValorCreditoBolsaAgua() != null) {
//			valorTotalConta = valorTotalConta.subtract(this.getValorCreditoBolsaAgua());
//		}

		if (this.getValorImpostos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorImpostos());
		}		

		return valorTotalConta.toString();
	}
	
	public Integer getQuantidadeImoveisMicro() {
		return quantidadeImoveisMicro;
	}

	public void setQuantidadeImoveisMicro(Integer quantidadeImoveisMicro) {
		this.quantidadeImoveisMicro = quantidadeImoveisMicro;
	}

	public Integer getSomaConsumosImoveisMicro() {
		return somaConsumosImoveisMicro;
	}

	public void setSomaConsumosImoveisMicro(Integer somaConsumosImoveisMicro) {
		this.somaConsumosImoveisMicro = somaConsumosImoveisMicro;
	}

	public BigDecimal getValorTotalASerrateado() {
		return valorTotalASerrateado;
	}

	public void setValorTotalASerrateado(BigDecimal valorTotalASerrateado) {
		this.valorTotalASerrateado = valorTotalASerrateado;
	}

	public Integer getConsumoMacro() {
		return consumoMacro;
	}

	public void setConsumoMacro(Integer consumoMacro) {
		this.consumoMacro = consumoMacro;
	}

	public String getDescricaoImpostosEAliquotas() {
		return descricaoImpostosEAliquotas;
	}

	public void setDescricaoImpostosEAliquotas(String descricaoImpostosEAliquotas) {
		this.descricaoImpostosEAliquotas = descricaoImpostosEAliquotas;
	}

	public BigDecimal getPercentualImpostosEAliquotas() {
		return percentualImpostosEAliquotas;
	}

	public void setPercentualImpostosEAliquotas(BigDecimal percentualImpostosEAliquotas) {
		this.percentualImpostosEAliquotas = percentualImpostosEAliquotas;
	}

	public BigDecimal getValorBaseCalculoImpostos() {
		return valorBaseCalculoImpostos;
	}

	public void setValorBaseCalculoImpostos(BigDecimal valorBaseCalculoImpostos) {
		this.valorBaseCalculoImpostos = valorBaseCalculoImpostos;
	}

	public BigDecimal getValorImpostosEAliquotas() {
		return valorImpostosEAliquotas;
	}

	public void setValorImpostosEAliquotas(BigDecimal valorImpostosEAliquotas) {
		this.valorImpostosEAliquotas = valorImpostosEAliquotas;
	}
	
	public String getDescricaoAgenciaReguladora() {
		return descricaoAgenciaReguladora;
	}

	public void setDescricaoAgenciaReguladora(String descricaoAgenciaReguladora) {
		this.descricaoAgenciaReguladora = descricaoAgenciaReguladora;
	}

	public BigDecimal getPercentualAgenciaReguladora() {
		return percentualAgenciaReguladora;
	}

	public void setPercentualAgenciaReguladora(BigDecimal percentualAgenciaReguladora) {
		this.percentualAgenciaReguladora = percentualAgenciaReguladora;
	}

	public BigDecimal getValorAgenciaReguladora() {
		return valorAgenciaReguladora;
	}

	public void setValorAgenciaReguladora(BigDecimal valorAgenciaReguladora) {
		this.valorAgenciaReguladora = valorAgenciaReguladora;
	}
	
	public boolean isInformarImpostos() {
		return informarImpostos;
	}

	public void setInformarImpostos(boolean informarImpostos) {
		this.informarImpostos = informarImpostos;
	}

	public String getAgenciaReguladora() {
		return agenciaReguladora;
	}

	public void setAgenciaReguladora(String agenciaReguladora) {
		this.agenciaReguladora = agenciaReguladora;
	}

	public String getTelefoneAgenciaReguladora() {
		return telefoneAgenciaReguladora;
	}

	public void setTelefoneAgenciaReguladora(String telefoneAgenciaReguladora) {
		this.telefoneAgenciaReguladora = telefoneAgenciaReguladora;
	}

	public String getEmailAgenciaReguladora() {
		return emailAgenciaReguladora;
	}

	public void setEmailAgenciaReguladora(String emailAgenciaReguladora) {
		this.emailAgenciaReguladora = emailAgenciaReguladora;
	}

	public Integer getIdConsumoAnormalidade() {
		return idConsumoAnormalidade;
	}

	public void setIdConsumoAnormalidade(Integer idConsumoAnormalidade) {
		this.idConsumoAnormalidade = idConsumoAnormalidade;
	}

	public Object[] getMensagensFixas() {
		return mensagensFixas;
	}

	public void setMensagensFixas(Object[] mensagensFixas) {
		this.mensagensFixas = mensagensFixas;
	}

	public String getMensagemAnormalidade() {
		return mensagemAnormalidade;
	}

	public void setMensagemAnormalidade(String mensagemAnormalidade) {
		this.mensagemAnormalidade = mensagemAnormalidade;
	}

	public String getMensagemDebitos() {
		return mensagemDebitos;
	}

	public void setMensagemDebitos(String mensagemDebitos) {
		this.mensagemDebitos = mensagemDebitos;
	}

	public String getMensagemQuitacao() {
		return mensagemQuitacao;
	}

	public void setMensagemQuitacao(String mensagemQuitacao) {
		this.mensagemQuitacao = mensagemQuitacao;
	}

	public String getMensagemBolsaAgua() {
		return mensagemBolsaAgua;
	}

	public void setMensagemBolsaAgua(String mensagemBolsaAgua) {
		this.mensagemBolsaAgua = mensagemBolsaAgua;
	}	
}
