package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioAnaliseAvisosBancariosBean implements RelatorioBean {
	
	private Date dataRealizada;
	private BigDecimal valorArrecadacaoCalculado;
	private BigDecimal valorArrecadacaoInformado;
	private BigDecimal valorDevolucaoCalculado;
	private BigDecimal valorDevolucaoInformado;
	private BigDecimal valorDeducoes;
	private BigDecimal valorAcertos;
	
	private BigDecimal valorRealizado;
	private BigDecimal diferencaAcumulada;
	private BigDecimal valorAcertosAplicadosDiferenca;
	private BigDecimal valorAcertosAplicadosAvisos;
	
	private Integer idArrecadador;
	private String descricaoArrecadador;
	private Integer idFormaArrecadacao;
	private String descricaoFormaArrecadacao;
	
    private Integer diasFloat;
    private BigDecimal valorTarifaUnitaria;
    
    public RelatorioAnaliseAvisosBancariosBean() {
    	this.valorArrecadacaoCalculado = new BigDecimal(0.0);
    	this.valorArrecadacaoInformado = new BigDecimal(0.0);
    	this.valorDevolucaoCalculado = new BigDecimal(0.0);
    	this.valorDevolucaoInformado = new BigDecimal(0.0);
    	this.valorDeducoes = new BigDecimal(0.0);
    	this.valorAcertos = new BigDecimal(0.0);
    	
    	this.valorRealizado = new BigDecimal(0.0);
    	this.diferencaAcumulada = new BigDecimal(0.0);
    	this.valorAcertosAplicadosDiferenca = new BigDecimal(0.0);
    	this.valorAcertosAplicadosAvisos = new BigDecimal(0.0);
    }
    
    public BigDecimal getValorDevolucaoInformado() {
		return valorDevolucaoInformado;
	}

	public void setValorDevolucaoInformado(BigDecimal valorDevolucaoInformado) {
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}

	public String toString() {
    	return dataRealizada + " => " + valorArrecadacaoCalculado + " , " + valorArrecadacaoInformado + 
    		" , " + valorDevolucaoCalculado + " [" + idArrecadador + "-" + descricaoArrecadador + "] [" +
    		idFormaArrecadacao + "-" + descricaoFormaArrecadacao + "]";
    }
    
    public BigDecimal getDiferenca() {
    	return (valorArrecadacaoCalculado.subtract(valorArrecadacaoInformado)).subtract(valorDevolucaoCalculado.subtract(valorDevolucaoInformado)).add(valorAcertosAplicadosAvisos);
    }

	/**
	 * @return Retorna o campo dataRealizada.
	 */
	public Date getDataRealizada() {
		return dataRealizada;
	}

	/**
	 * @param dataRealizada O dataRealizada a ser setado.
	 */
	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	/**
	 * @return Retorna o campo descricaoArrecadador.
	 */
	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}

	/**
	 * @param descricaoArrecadador O descricaoArrecadador a ser setado.
	 */
	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}

	/**
	 * @return Retorna o campo descricaoFormaArrecadacao.
	 */
	public String getDescricaoFormaArrecadacao() {
		return descricaoFormaArrecadacao;
	}

	/**
	 * @param descricaoFormaArrecadacao O descricaoFormaArrecadacao a ser setado.
	 */
	public void setDescricaoFormaArrecadacao(String descricaoFormaArrecadacao) {
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
	}

	/**
	 * @return Retorna o campo diasFloat.
	 */
	public Integer getDiasFloat() {
		return diasFloat;
	}

	/**
	 * @param diasFloat O diasFloat a ser setado.
	 */
	public void setDiasFloat(Integer diasFloat) {
		this.diasFloat = diasFloat;
	}

	/**
	 * @return Retorna o campo idArrecadador.
	 */
	public Integer getIdArrecadador() {
		return idArrecadador;
	}

	/**
	 * @param idArrecadador O idArrecadador a ser setado.
	 */
	public void setIdArrecadador(Integer idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	/**
	 * @return Retorna o campo idFormaArrecadacao.
	 */
	public Integer getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	/**
	 * @param idFormaArrecadacao O idFormaArrecadacao a ser setado.
	 */
	public void setIdFormaArrecadacao(Integer idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoCalculado.
	 */
	public BigDecimal getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}

	/**
	 * @param valorArrecadacaoCalculado O valorArrecadacaoCalculado a ser setado.
	 */
	public void setValorArrecadacaoCalculado(BigDecimal valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoInformado.
	 */
	public BigDecimal getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}

	/**
	 * @param valorArrecadacaoInformado O valorArrecadacaoInformado a ser setado.
	 */
	public void setValorArrecadacaoInformado(BigDecimal valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}

	/**
	 * @return Retorna o campo valorDevolucaoCalculado.
	 */
	public BigDecimal getValorDevolucaoCalculado() {
		return valorDevolucaoCalculado;
	}

	/**
	 * @param valorDevolucaoCalculado O valorDevolucaoCalculado a ser setado.
	 */
	public void setValorDevolucaoCalculado(BigDecimal valorDevolucaoCalculado) {
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}

	/**
	 * @return Retorna o campo valorTarifaUnitaria.
	 */
	public BigDecimal getValorTarifaUnitaria() {
		return valorTarifaUnitaria;
	}

	/**
	 * @param valorTarifaUnitaria O valorTarifaUnitaria a ser setado.
	 */
	public void setValorTarifaUnitaria(BigDecimal valorTarifaUnitaria) {
		this.valorTarifaUnitaria = valorTarifaUnitaria;
	}

	/**
	 * @return Retorna o campo valorAcertos.
	 */
	public BigDecimal getValorAcertos() {
		return valorAcertos;
	}

	/**
	 * @param valorAcertos O valorAcertos a ser setado.
	 */
	public void setValorAcertos(BigDecimal valorAcertos) {
		this.valorAcertos = valorAcertos;
	}

	/**
	 * @return Retorna o campo valorDeducoes.
	 */
	public BigDecimal getValorDeducoes() {
		return valorDeducoes;
	}

	/**
	 * @param valorDeducoes O valorDeducoes a ser setado.
	 */
	public void setValorDeducoes(BigDecimal valorDeducoes) {
		this.valorDeducoes = valorDeducoes;
	}

	/**
	 * @return Retorna o campo diferencaAcumulada.
	 */
	public BigDecimal getDiferencaAcumulada() {
		return diferencaAcumulada;
	}

	/**
	 * @param diferencaAcumulada O diferencaAcumulada a ser setado.
	 */
	public void setDiferencaAcumulada(BigDecimal diferencaAcumulada) {
		this.diferencaAcumulada = diferencaAcumulada;
	}

	/**
	 * @return Retorna o campo valorAcertosAplicadosAvisos.
	 */
	public BigDecimal getValorAcertosAplicadosAvisos() {
		return valorAcertosAplicadosAvisos;
	}

	/**
	 * @param valorAcertosAplicadosAvisos O valorAcertosAplicadosAvisos a ser setado.
	 */
	public void setValorAcertosAplicadosAvisos(
			BigDecimal valorAcertosAplicadosAvisos) {
		this.valorAcertosAplicadosAvisos = valorAcertosAplicadosAvisos;
	}

	/**
	 * @return Retorna o campo valorAcertosAplicadosDiferenca.
	 */
	public BigDecimal getValorAcertosAplicadosDiferenca() {
		return valorAcertosAplicadosDiferenca;
	}

	/**
	 * @param valorAcertosAplicadosDiferenca O valorAcertosAplicadosDiferenca a ser setado.
	 */
	public void setValorAcertosAplicadosDiferenca(
			BigDecimal valorAcertosAplicadosDiferenca) {
		this.valorAcertosAplicadosDiferenca = valorAcertosAplicadosDiferenca;
	}

	/**
	 * @return Retorna o campo valorRealizado.
	 */
	public BigDecimal getValorRealizado() {
		return valorRealizado;
	}

	/**
	 * @param valorRealizado O valorRealizado a ser setado.
	 */
	public void setValorRealizado(BigDecimal valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

}
