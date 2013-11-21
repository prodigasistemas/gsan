package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioAnaliseArrecadacaoBean implements RelatorioBean {
	
	private Date dataPagamento;
	private Integer quantidadeDocumentos;
	private BigDecimal valorTarifa;
	private Integer quantidadePagamentos;
	private BigDecimal valorArrecadadoBruto;
	private BigDecimal valorArrecadadoDesconto;
	
	private Integer idArrecadador;
	private String descricaoArrecadador;
	private Integer idFormaArrecadacao;
	private String descricaoFormaArrecadacao;
	
    private Integer diasFloat;
    private BigDecimal valorTarifaUnitaria;
	
	public RelatorioAnaliseArrecadacaoBean() {
		this.valorTarifa = new BigDecimal(0.0);
		this.quantidadePagamentos = new Integer(0);
		this.quantidadeDocumentos = new Integer(0);
		this.valorArrecadadoBruto = new BigDecimal(0);
		this.valorArrecadadoDesconto = new BigDecimal(0);
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
	
	public String toString() {
		return dataPagamento + " " + idArrecadador + " " + 
			descricaoArrecadador + " | " + idFormaArrecadacao + " " + 
			descricaoFormaArrecadacao + " => " + quantidadeDocumentos + " , " + 
			valorTarifa + " , " + quantidadePagamentos + " :: " + 
			valorArrecadadoBruto + " , " + valorArrecadadoDesconto;
	}

	/**
	 * @return Retorna o campo quantidadeDocumentos.
	 */
	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	/**
	 * @param quantidadeDocumentos O quantidadeDocumentos a ser setado.
	 */
	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	/**
	 * @return Retorna o campo quantidadePagamentos.
	 */
	public Integer getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	/**
	 * @param quantidadePagamentos O quantidadePagamentos a ser setado.
	 */
	public void setQuantidadePagamentos(Integer quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
	}

	/**
	 * @return Retorna o campo valorArrecadadoBruto.
	 */
	public BigDecimal getValorArrecadadoBruto() {
		return valorArrecadadoBruto;
	}

	/**
	 * @param valorArrecadadoBruto O valorArrecadadoBruto a ser setado.
	 */
	public void setValorArrecadadoBruto(BigDecimal valorArrecadadoBruto) {
		this.valorArrecadadoBruto = valorArrecadadoBruto;
	}

	/**
	 * @return Retorna o campo valorArrecadadoDesconto.
	 */
	public BigDecimal getValorArrecadadoDesconto() {
		return valorArrecadadoDesconto;
	}

	/**
	 * @param valorArrecadadoDesconto O valorArrecadadoDesconto a ser setado.
	 */
	public void setValorArrecadadoDesconto(BigDecimal valorArrecadadoDesconto) {
		this.valorArrecadadoDesconto = valorArrecadadoDesconto;
	}

	/**
	 * @return Retorna o campo valorTarifa.
	 */
	public BigDecimal getValorTarifa() {
		return valorTarifa;
	}

	/**
	 * @param valorTarifa O valorTarifa a ser setado.
	 */
	public void setValorTarifa(BigDecimal valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
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

}
