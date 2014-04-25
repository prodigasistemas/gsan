package gcom.relatorio.faturamento;

import java.math.BigDecimal;


public class RelatorioAnaliticoFaturamentoHelper {
	
	private Integer idImovel;
	private Integer consumoAgua;
	private Integer consumoRateioAgua;
	private BigDecimal valorAgua;
	private Integer consumoEsgoto;
	private Integer consumoRateioEsgoto;
	private BigDecimal valorEsgoto;
	private BigDecimal debitos;
	private BigDecimal valorCreditos;
	private BigDecimal totalConta;
	private BigDecimal totalGeral;
	private Integer codigoSetorComercial;
	private String inscricao;
	private Integer idLocalidade;
	private String mesAnoFaturamento;
	private short digitoVerificador;
	private BigDecimal valorImposto;
	
	private String nomeCliente;
	
	private String idDescricaoLocalidade;
	private String idDescricaoUnidadeNegocio;
	
	
	public BigDecimal getValorImposto() {
		return valorImposto;
	}
	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}
	public short getDigitoVerificador() {
		return digitoVerificador;
	}
	public void setDigitoVerificador(short digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}
	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public BigDecimal getTotalConta() {
		return totalConta;
	}
	public void setTotalConta(BigDecimal totalConta) {
		this.totalConta = totalConta;
	}
	public BigDecimal getTotalGeral() {
		return totalGeral;
	}
	public void setTotalGeral(BigDecimal totalGeral) {
		this.totalGeral = totalGeral;
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
	public Integer getConsumoRateioAgua() {
		return consumoRateioAgua;
	}
	public void setConsumoRateioAgua(Integer consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}
	public Integer getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}
	public void setConsumoRateioEsgoto(Integer consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}
	public BigDecimal getDebitos() {
		return debitos;
	}
	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public BigDecimal getValorAgua() {
		return valorAgua;
	}
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
	public BigDecimal getValorCreditos() {
		return valorCreditos;
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
	public String getIdDescricaoLocalidade() {
		return idDescricaoLocalidade;
	}
	public void setIdDescricaoLocalidade(String idDescricaoLocalidade) {
		this.idDescricaoLocalidade = idDescricaoLocalidade;
	}
	public String getIdDescricaoUnidadeNegocio() {
		return idDescricaoUnidadeNegocio;
	}
	public void setIdDescricaoUnidadeNegocio(String idDescricaoUnidadeNegocio) {
		this.idDescricaoUnidadeNegocio = idDescricaoUnidadeNegocio;
	}
	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
}
