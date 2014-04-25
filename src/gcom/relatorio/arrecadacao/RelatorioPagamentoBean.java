package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioPagamentoBean implements RelatorioBean {

	private String gerenciaRegional;

	private String localidade;

	private String matricula;

	private String inscricao;

	private String arrecadador;

	private String dataPagamento;

	private String mesAno;

	private String tipoDebito;

	private String valorGuia;

	private String valorPagamento;

	private String situacaoAtual;
	
	private String situacaoAnterior;
	
	private String tipoDocumento;
	
	private String cliente;
	
	private String referenciaPagamento;
	
	private String debito;
	
	private boolean indicadorHistorico;
	
	private String valorAgua;
	
	private String valorEsgoto;
	
	private String valorCredito;
	
	private String valorDebito;

	public String getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioPagamentoBean(String gerenciaRegional, String localidade,
			String matricula, String inscricao, String arrecadador,
			String dataPagamento, String mesAno, String tipoDebito,
			String valorGuia, 
			String valorAgua, 
			String valorEsgoto, 
			String valorPagamento, 
			String situacaoAtual ) {
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.arrecadador = arrecadador;
		this.dataPagamento = dataPagamento;
		this.mesAno = mesAno;
		this.tipoDebito = tipoDebito;
		this.valorGuia = valorGuia;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.valorPagamento = valorPagamento;
		this.situacaoAtual = situacaoAtual;
	}
	
	public RelatorioPagamentoBean(String matricula, String inscricao,
			String dataPagamento, String mesAno, String tipoDebito,
			String valorGuia, String valorAgua, String valorEsgoto, 
			String valorPagamento, String situacaoAtual,
			String situacaoAnterior, String tipoDocumento,
			String valorDebito, 
			String valorCredito	) {
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.dataPagamento = dataPagamento;
		this.mesAno = mesAno;
		this.tipoDebito = tipoDebito;
		this.valorGuia = valorGuia;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.valorPagamento = valorPagamento;
		this.situacaoAtual = situacaoAtual;
		this.situacaoAnterior = situacaoAnterior;
		this.tipoDocumento = tipoDocumento;
		this.valorCredito = valorCredito;
		this.valorDebito = valorDebito;	
	}
	
	public RelatorioPagamentoBean(String tipoDocumento, String dataPagamento, String localidade, String matricula,
			String cliente, String referenciaPagamento,
			String debito, String valorPagamento, String situacaoAtual, boolean indicadorHistorico) {
		this.tipoDocumento = tipoDocumento;
		this.dataPagamento = dataPagamento;
		this.localidade = localidade;
		this.matricula = matricula;
		this.cliente = cliente;
		this.referenciaPagamento = referenciaPagamento;
		this.debito = debito;
		this.valorPagamento = valorPagamento;
		this.situacaoAtual = situacaoAtual;
		this.indicadorHistorico = indicadorHistorico;
	}

	/**
	 * @return Retorna o campo indicadorHistorico.
	 */
	public boolean getIndicadorHistorico() {
		return indicadorHistorico;
	}

	/**
	 * @param indicadorHistorico O indicadorHistorico a ser setado.
	 */
	public void setIndicadorHistorico(boolean indicadorHistorico) {
		this.indicadorHistorico = indicadorHistorico;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getValorGuia() {
		return valorGuia;
	}

	public void setValorGuia(String valorGuia) {
		this.valorGuia = valorGuia;
	}

	/**
	 * @return Retorna o campo situacaoAnterior.
	 */
	public String getSituacaoAnterior() {
		return situacaoAnterior;
	}

	/**
	 * @param situacaoAnterior O situacaoAnterior a ser setado.
	 */
	public void setSituacaoAnterior(String situacaoAnterior) {
		this.situacaoAnterior = situacaoAnterior;
	}

	/**
	 * @return Retorna o campo tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento O tipoDocumento a ser setado.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return Retorna o campo cliente.
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Retorna o campo debito.
	 */
	public String getDebito() {
		return debito;
	}

	/**
	 * @param debito O debito a ser setado.
	 */
	public void setDebito(String debito) {
		this.debito = debito;
	}

	/**
	 * @return Retorna o campo referenciaPagamento.
	 */
	public String getReferenciaPagamento() {
		return referenciaPagamento;
	}

	/**
	 * @param referenciaPagamento O referenciaPagamento a ser setado.
	 */
	public void setReferenciaPagamento(String referenciaPagamento) {
		this.referenciaPagamento = referenciaPagamento;
	}

	public String getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(String valorAgua) {
		this.valorAgua = valorAgua;
	}

	public String getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(String valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

}
