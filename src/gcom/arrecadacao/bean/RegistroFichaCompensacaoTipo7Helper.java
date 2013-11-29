package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Date;

import gcom.util.ControladorException;
import gcom.util.Util;

public class RegistroFichaCompensacaoTipo7Helper {

	private String id;
	private String prefixoAgencia;
	private String digitoVerificador;
	private String numeroContaCorrenteCedente;
	private String digitoVerificadorContaCorrenteCedente;
	private String numeroConvenioCobrancaCedente;
	private String numeroControleParticipante;
	private String nossoNumero;
	private String tipoCobranca;
	private String tipoCobrancaoComando72;
	private String diasCalculo;
	private String naturezaRecebimento;
	private String prefixoTitulo;
	private String variacaoCarteira;
	private String contaCaucao;
	private String taxaDesconto;
	private String taxaIof;
	private String carteira;
	private String comando;
	private String dataLiquidacao;
	private String numeroTituloCedente;
	private String dataVencimento;
	private String valorTitulo;
	private String codigoBancoRecebedor;
	private String prefixoAgenciaRecebedora;
	private String digitoVerificadorPrefixoRecebedora;
	private String especieTitulo;
	private String dataCredito;
	private String valorTarifa;
	private String outrasDespesas;
	private String jurosDesconto;
	private String iofDesconto;
	private String valorAbatimento;
	private String descontoConcedido;
	private String valorRecebido;
	private String jurosMora;
	private String outrosRecebimentos;
	private String abatimentoNaoAproveitadoSacado;
	private String valorLancamento;
	private String indicativoDebitoCredito;
	private String indicadorValor;
	private String valorAjuste;
	private String canalPagamento;
	private String sequencialRegistro;
	private String tipoDocumento;
	private String idDocumentoEmitido;
	
	public RegistroFichaCompensacaoTipo7Helper(String linha) throws ControladorException {
		buildId(linha);
		buildPrefixoAgencia(linha);
		buildDigitoVerificador(linha);
		buildNumeroContaCorrenteCedente(linha);
		buildDigitoVerificadorContaCorrenteCedente(linha);
		buildNumeroConvenioCobrancaCedente(linha);
		buildNumeroControleParticipante(linha);
		buildNossoNumero(linha);
		buildTipoCobranca(linha);
		buildTipoCobrancaoComando72(linha);
		buildDiasCalculo(linha);
		buildNaturezaRecebimento(linha);
		buildPrefixoTitulo(linha);
		buildVariacaoCarteira(linha);
		buildContaCaucao(linha);
		buildTaxaDesconto(linha);
		buildTaxaIof(linha);
		buildCarteira(linha);
		buildComando(linha);
		buildDataLiquidacao(linha);
		buildNumeroTituloCedente(linha);
		buildDataVencimento(linha);
		buildValorTitulo(linha);
		buildCodigoBancoRecebedor(linha);
		buildPrefixoAgenciaRecebedora(linha);
		buildDigitoVerificadorPrefixoRecebedora(linha);
		buildEspecieTitulo(linha);
		buildDataCredito(linha);
		buildValorTarifa(linha);
		buildOutrasDespesas(linha);
		buildJurosDesconto(linha);
		buildIofDesconto(linha);
		buildValorAbatimento(linha);
		buildDescontoConcedido(linha);
		buildValorRecebido(linha);
		buildJurosMora(linha);
		buildOutrosRecebimentos(linha);
		buildAbatimentoNaoAproveitadoSacado(linha);
		buildValorLancamento(linha);
		buildIndicativoDebitoCredito(linha);
		buildIndicadorValor(linha);
		buildValorAjuste(linha);
		buildCanalPagamento(linha);
		buildSequencialRegistro(linha);
	}
	
	public String getId() {
		return id;
	}
	public String getPrefixoAgencia() {
		return prefixoAgencia;
	}
	public String getDigitoVerificador() {
		return digitoVerificador;
	}
	public String getNumeroContaCorrenteCedente() {
		return numeroContaCorrenteCedente;
	}
	public String getDigitoVerificadorContaCorrenteCedente() {
		return digitoVerificadorContaCorrenteCedente;
	}
	public String getNumeroConvenioCobrancaCedente() {
		return numeroConvenioCobrancaCedente;
	}
	public String getNumeroControleParticipante() {
		return numeroControleParticipante;
	}
	public String getNossoNumero() {
		return nossoNumero;
	}
	public String getTipoCobranca() {
		return tipoCobranca;
	}
	public String getTipoCobrancaoComando72() {
		return tipoCobrancaoComando72;
	}
	public String getDiasCalculo() {
		return diasCalculo;
	}
	public String getNaturezaRecebimento() {
		return naturezaRecebimento;
	}
	public String getPrefixoTitulo() {
		return prefixoTitulo;
	}
	public String getVariacaoCarteira() {
		return variacaoCarteira;
	}
	public String getContaCalcao() {
		return contaCaucao;
	}
	public String getFaixaDesconto() {
		return taxaDesconto;
	}
	public String getTaxaIof() {
		return taxaIof;
	}
	public String getCarteira() {
		return carteira;
	}
	public String getComando() {
		return comando;
	}
	public String getDataLiquidacao() {
		return dataLiquidacao;
	}
	public String getNumeroTituloCedente() {
		return numeroTituloCedente;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public String getValorTitulo() {
		return valorTitulo;
	}
	public String getCodigoBancoRecebedor() {
		return codigoBancoRecebedor;
	}
	public String getPrefixoAgenciaRecebedora() {
		return prefixoAgenciaRecebedora;
	}
	public String getDeverPrefixoRecebedora() {
		return digitoVerificadorPrefixoRecebedora;
	}
	public String getEspecieTitulo() {
		return especieTitulo;
	}
	public String getDataCredito() {
		return dataCredito;
	}
	public String getValorTarifa() {
		return valorTarifa;
	}
	public String getOutrasDespesas() {
		return outrasDespesas;
	}
	public String getJurosDesconto() {
		return jurosDesconto;
	}
	public String getIofDesconto() {
		return iofDesconto;
	}
	public String getValorAbatimento() {
		return valorAbatimento;
	}
	public String getDescontoConcedido() {
		return descontoConcedido;
	}
	public String getValorRecebido() {
		return valorRecebido;
	}
	public String getJurosMora() {
		return jurosMora;
	}
	public String getOutrosRecebimentos() {
		return outrosRecebimentos;
	}
	public String getAbatimentoNaoAproveitadoSacado() {
		return abatimentoNaoAproveitadoSacado;
	}
	public String getValorLancamento() {
		return valorLancamento;
	}
	public String getIndicativoDebitoCredito() {
		return indicativoDebitoCredito;
	}
	public String getIndicadorValor() {
		return indicadorValor;
	}
	public String getValorAjuste() {
		return valorAjuste;
	}
	public String getCanalPagamento() {
		return canalPagamento;
	}
	public String getSequencialRegistro() {
		return sequencialRegistro;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public Integer getIdDocumentoEmitido() {
		return Integer.valueOf(idDocumentoEmitido);
	}
	public Date getDataLiquidacaoFormatado() {
		return Util.converteStringSemBarraParaDateAnoSimples(dataLiquidacao);
	}
	public BigDecimal getValorRecebidoFormatado() {
		return Util.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(valorRecebido);
	}
	public Integer getTipoDocumentoInteger() {
		return Integer.valueOf(getTipoDocumento());
	}
	private void buildSequencialRegistro(String linha) {
		this.sequencialRegistro = linha.substring(395,400);		
	}
	private void buildCanalPagamento(String linha) {
		this.canalPagamento = linha.substring(392,394);		
	}
	private void buildValorAjuste(String linha) {
		this.valorAjuste = linha.substring(320,332);	
	}
	private void buildIndicadorValor(String linha) {
		this.indicadorValor = linha.substring(319,320);		
	}
	private void buildIndicativoDebitoCredito(String linha) {
		this.indicativoDebitoCredito = linha.substring(318,319);		
	}
	private void buildValorLancamento(String linha) {
		this.valorLancamento = linha.substring(305,318);		
	}
	private void buildAbatimentoNaoAproveitadoSacado(String linha) {
		this.abatimentoNaoAproveitadoSacado = linha.substring(292,305);		
	}
	private void buildOutrosRecebimentos(String linha) {
		this.outrosRecebimentos = linha.substring(279,292);		
	}
	private void buildJurosMora(String linha) {
		this.jurosMora = linha.substring(266,279);		
	}
	private void buildValorRecebido(String linha) {
		this.valorRecebido = linha.substring(253,266);		
	}
	private void buildDescontoConcedido(String linha) {
		this.descontoConcedido = linha.substring(240,253);		
	}
	private void buildValorAbatimento(String linha) {
		this.valorAbatimento = linha.substring(227,240);		
	}
	private void buildIofDesconto(String linha) {
		this.iofDesconto = linha.substring(214,227);	
	}
	private void buildJurosDesconto(String linha) {
		this.jurosDesconto = linha.substring(201,214);
	}
	private void buildOutrasDespesas(String linha) {
		this.outrosRecebimentos = linha.substring(188,201);		
	}
	private void buildValorTarifa(String linha) {
		this.valorTarifa = linha.substring(181,188);		
	}
	private void buildDataCredito(String linha) {
		this.dataCredito = linha.substring(175,181);	
	}
	private void buildEspecieTitulo(String linha) {
		this.especieTitulo = linha.substring(173,175);
	}
	private void buildDigitoVerificadorPrefixoRecebedora(String linha) {
		this.digitoVerificadorPrefixoRecebedora = linha.substring(172,173);		
	}
	private void buildPrefixoAgenciaRecebedora(String linha) {
		this.prefixoAgenciaRecebedora = linha.substring(168,172);		
	}
	private void buildCodigoBancoRecebedor(String linha) {
		this.codigoBancoRecebedor = linha.substring(165,168);		
	}
	private void buildValorTitulo(String linha) {
		this.valorTitulo = linha.substring(152,165);	
	}
	private void buildDataVencimento(String linha) {
		this.dataVencimento = linha.substring(146,152);
	}
	private void buildNumeroTituloCedente(String linha) {
		this.numeroTituloCedente = linha.substring(116,126);		
	}
	private void buildDataLiquidacao(String linha) {
		this.dataLiquidacao = linha.substring(110,116);		
	}
	private void buildComando(String linha) {
		this.comando = linha.substring(108,110);	
	}
	private void buildCarteira(String linha) {
		this.carteira = linha.substring(106,108);		
	}
	private void buildTaxaIof(String linha) {
		this.taxaIof = linha.substring(100,105);		
	}
	private void buildTaxaDesconto(String linha) {
		this.taxaDesconto = linha.substring(95,100);		
	}
	private void buildContaCaucao(String linha) {
		this.contaCaucao = linha.substring(94,95);	
	}
	private void buildVariacaoCarteira(String linha) {
		this.variacaoCarteira = linha.substring(91,94);		
	}
	private void buildPrefixoTitulo(String linha) {
		this.prefixoTitulo = linha.substring(88,91);
	}
	private void buildNaturezaRecebimento(String linha) {
		this.naturezaRecebimento = linha.substring(86,88);		
	}
	private void buildDiasCalculo(String linha) {
		this.diasCalculo = linha.substring(82,86);
	}
	private void buildTipoCobrancaoComando72(String linha) {
		this.tipoCobrancaoComando72 = linha.substring(81,82);		
	}
	private void buildTipoCobranca(String linha) {
		this.tipoCobranca = linha.substring(80,81);		
	}
	private void buildNossoNumero(String linha) {
		this.nossoNumero = linha.substring(63,80);
		this.tipoDocumento = nossoNumero.substring(7,9);
		this.idDocumentoEmitido = nossoNumero.substring(9, 17);
	}
	private void buildNumeroControleParticipante(String linha) {
		this.numeroControleParticipante = linha.substring(38,63);		
	}
	private void buildNumeroConvenioCobrancaCedente(String linha) {
		this.numeroConvenioCobrancaCedente = linha.substring(31,38);
	}
	private void buildDigitoVerificadorContaCorrenteCedente(String linha) {
		this.digitoVerificadorContaCorrenteCedente = linha.substring(30,31);		
	}
	private void buildNumeroContaCorrenteCedente(String linha) {
		this.numeroContaCorrenteCedente = linha.substring(22,30);		
	}
	private void buildDigitoVerificador(String linha) {
		this.digitoVerificador = linha.substring(21,22);		
	}
	private void buildPrefixoAgencia(String linha) {
		this.prefixoAgencia = linha.substring(17,21);		
	}
	private void buildId(String linha) throws ControladorException {
		this.id = linha.substring(0,1);
		if(!id.equals("7")) {
			throw new ControladorException("Identificação do registro 7 inválida");
		}
	}
}
