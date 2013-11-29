package gcom.arrecadacao.bean;

public class RegistroFichaCompensacaoTrailerHelper {

	private String idTrailer;
	private String id;
	
	private String cobrancaSimplesQuantidadeTitulos;
	private String cobrancaSimplesValorTotal;
	private String cobrancaSimplesNumeroAviso;
	
	private String cobrancaVinculadaQuantidadeTitulos;
	private String cobrancaVinculadaValorTotal;
	private String cobrancaVinculadaNumeroAviso;
	
	private String cobrancaCaucionadaQuantidadeTitulos;
	private String cobrancaCaucionadaValorTotal;
	private String cobrancaCaucionadaNumeroAviso;
	
	private String cobrancaDescontadaQuantidadeTitulos;
	private String cobrancaDescontadaValorTotal;
	private String cobrancaDescontadaNumeroAviso;
	
	private String cobrancaVendorQuantidadeTitulos;
	private String cobrancaVendorValorTotal;
	private String cobrancaVendorNumeroAviso;
	
	private String sequencialRegistro;

	public RegistroFichaCompensacaoTrailerHelper(String linha) {
		buildIdTrailer(linha);
		buildId(linha);
		buildCobrancaSimplesQuantidadeTitulos(linha);
		buildCobrancaSimplesValorTotal(linha);
		buildCobrancaSimplesNumeroAviso(linha);
		buildCobrancaVinculadaQuantidadeTitulos(linha);
		buildCobrancaVinculadaValorTotal(linha);
		buildCobrancaVinculadaNumeroAviso(linha);
		buildCobrancaCaucionadaQuantidadeTitulos(linha);
		buildCobrancaCaucionadaValorTotal(linha);
		buildCobrancaCaucionadaNumeroAviso(linha);
		buildCobrancaDescontadaQuantidadeTitulos(linha);
		buildCobrancaDescontadaValorTotal(linha);
		buildCobrancaDescontadaNumeroAviso(linha);
		buildCobrancaVendorQuantidadeTitulos(linha);
		buildCobrancaVendorValorTotal(linha);
		buildCobrancaVendorNumeroAviso(linha);
		buildSequencialRegistro(linha);
	}

	public String getIdTrailer() {
		return idTrailer;
	}

	public String getId() {
		return id;
	}

	public String getCobrancaSimplesQuantidadeTitulos() {
		return cobrancaSimplesQuantidadeTitulos;
	}

	public String getCobrancaSimplesValorTotal() {
		return cobrancaSimplesValorTotal;
	}

	public String getCobrancaSimplesNumeroAviso() {
		return cobrancaSimplesNumeroAviso;
	}

	public String getCobrancaVinculadaQuantidadeTitulos() {
		return cobrancaVinculadaQuantidadeTitulos;
	}

	public String getCobrancaVinculadaValorTotal() {
		return cobrancaVinculadaValorTotal;
	}

	public String getCobrancaVinculadaNumeroAviso() {
		return cobrancaVinculadaNumeroAviso;
	}

	public String getCobrancaCaucionadaQuantidadeTitulos() {
		return cobrancaCaucionadaQuantidadeTitulos;
	}

	public String getCobrancaCaucionadaValorTotal() {
		return cobrancaCaucionadaValorTotal;
	}

	public String getCobrancaCaucionadaNumeroAviso() {
		return cobrancaCaucionadaNumeroAviso;
	}

	public String getCobrancaDescontadaQuantidadeTitulos() {
		return cobrancaDescontadaQuantidadeTitulos;
	}

	public String getCobrancaDescontadaValorTotal() {
		return cobrancaDescontadaValorTotal;
	}

	public String getCobrancaDescontadaNumeroAviso() {
		return cobrancaDescontadaNumeroAviso;
	}

	public String getCobrancaVendorQuantidadeTitulos() {
		return cobrancaVendorQuantidadeTitulos;
	}

	public String getCobrancaVendorValorTotal() {
		return cobrancaVendorValorTotal;
	}

	public String getCobrancaVendorNumeroAviso() {
		return cobrancaVendorNumeroAviso;
	}
	
	public String getSequencialRegistro() {
		return sequencialRegistro;
	}
	
	public int getQuantidadeRegistros(){
		/*
		 *  O valor do sequencial de registros menos o trailer e o header, 
		 *  retorna a quantidade de registros no arquivo.
		 */
		return Integer.valueOf(getSequencialRegistro()) - 2;
	}
	
	public int getTotalLinhas(){
		return Integer.valueOf(getSequencialRegistro());
	}
	
	private void buildSequencialRegistro(String linha) {
		this.sequencialRegistro = linha.substring(394, 400);
	}

	private void buildCobrancaVendorNumeroAviso(String linha) {
		this.cobrancaVendorNumeroAviso = linha.substring(239, 247);
	}

	private void buildCobrancaVendorValorTotal(String linha) {
		this.cobrancaVendorValorTotal = linha.substring(225,239);
	}

	private void buildCobrancaVendorQuantidadeTitulos(String linha) {
		this.cobrancaVendorQuantidadeTitulos = linha.substring(217, 225);
	}

	private void buildCobrancaDescontadaNumeroAviso(String linha) {
		this.cobrancaDescontadaNumeroAviso = linha.substring(159,167);
	}

	private void buildCobrancaDescontadaValorTotal(String linha) {
		this.cobrancaDescontadaValorTotal = linha.substring(145,159);
	}

	private void buildCobrancaDescontadaQuantidadeTitulos(String linha) {
		this.cobrancaDescontadaQuantidadeTitulos = linha.substring(137,145);
	}

	private void buildCobrancaCaucionadaNumeroAviso(String linha) {
		this.cobrancaCaucionadaNumeroAviso = linha.substring(119,127);
	}

	private void buildCobrancaCaucionadaValorTotal(String linha) {
		this.cobrancaCaucionadaValorTotal = linha.substring(105,119);
	}

	private void buildCobrancaCaucionadaQuantidadeTitulos(String linha) {
		this.cobrancaCaucionadaQuantidadeTitulos = linha.substring(97,105);
	}

	private void buildCobrancaVinculadaNumeroAviso(String linha) {
		this.cobrancaVinculadaNumeroAviso = linha.substring(79,87);
	}

	private void buildCobrancaVinculadaValorTotal(String linha) {
		this.cobrancaVinculadaValorTotal = linha.substring(65,79);
	}

	private void buildCobrancaVinculadaQuantidadeTitulos(String linha) {
		this.cobrancaVinculadaQuantidadeTitulos = linha.substring(57,65);
	}

	private void buildCobrancaSimplesNumeroAviso(String linha) {
		this.cobrancaSimplesNumeroAviso = linha.substring(39,47);
	}

	private void buildCobrancaSimplesValorTotal(String linha) {
		this.cobrancaSimplesValorTotal = linha.substring(25,39);
	}

	private void buildCobrancaSimplesQuantidadeTitulos(String linha) {
		this.cobrancaSimplesQuantidadeTitulos = linha.substring(17,25);
	}

	private void buildId(String linha) {
		this.id = linha.substring(1,7);
	}

	private void buildIdTrailer(String linha) {
		this.idTrailer = linha.substring(0,1);
	}
}
