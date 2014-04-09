package gcom.relatorio.faturamento;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */
public class RelatorioAnaliticoFaturamentoBean implements RelatorioBean {

	private String idImovel;
	private BigDecimal consumoAgua;
	private BigDecimal consumoRateioAgua;
	private BigDecimal valorAgua;
	private BigDecimal consumoEsgoto;
	private BigDecimal consumoRateioEsgoto;
	private BigDecimal valorEsgoto;
	private BigDecimal debitos;
	private BigDecimal valorCreditos;
	private BigDecimal totalConta;
	private String totalGeral;
	private String codigoSetorComercial;
	private String inscricao;
	private String idLocalidade;
	private String codigoBarra;
	
	private String idDescricaoLocalidade;
	private String idDescricaoUnidadeNegocio;
	
	private String nomeCliente;
	
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public RelatorioAnaliticoFaturamentoBean(
			 String idImovel,
			 BigDecimal consumoAgua,
			 BigDecimal consumoRateioAgua,
			 BigDecimal valorAgua,
			 BigDecimal consumoEsgoto,
			 BigDecimal consumoRateioEsgoto,
			 BigDecimal valorEsgoto,
			 BigDecimal debitos,
			 BigDecimal valorCreditos,
			 BigDecimal totalConta,
			 String totalGeral,
			 String codigoSetorComercial,
			 String inscricao,
			 String idLocalidade,
			 String codigoBarra,
			 String idDescricaoLocalidade,
			 String idDescricaoUnidadeNegocio,
			 String nomeCliente) {
		
		this.idImovel = idImovel;
		this.consumoAgua = consumoAgua;
		this.consumoRateioAgua = consumoRateioAgua;
		this.valorAgua = valorAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.consumoRateioEsgoto = consumoRateioEsgoto;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.totalConta = totalConta;
		this.totalGeral = totalGeral;
		this.codigoSetorComercial = codigoSetorComercial;
		this.inscricao = inscricao;
		this.idLocalidade = idLocalidade;
		this.codigoBarra = codigoBarra;
		
		this.idDescricaoLocalidade = idDescricaoLocalidade;
		this.idDescricaoUnidadeNegocio = idDescricaoUnidadeNegocio;
		this.nomeCliente = nomeCliente;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public BigDecimal getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(BigDecimal consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public BigDecimal getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(BigDecimal consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public BigDecimal getConsumoRateioAgua() {
		return consumoRateioAgua;
	}

	public void setConsumoRateioAgua(BigDecimal consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	public BigDecimal getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}

	public void setConsumoRateioEsgoto(BigDecimal consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}

	public BigDecimal getDebitos() {
		return debitos;
	}

	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public BigDecimal getTotalConta() {
		return totalConta;
	}

	public void setTotalConta(BigDecimal totalConta) {
		this.totalConta = totalConta;
	}

	public String getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(String totalGeral) {
		this.totalGeral = totalGeral;
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
