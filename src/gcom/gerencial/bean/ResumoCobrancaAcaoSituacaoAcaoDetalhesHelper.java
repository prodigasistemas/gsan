package gcom.gerencial.bean;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private Integer quantidadeDocumento;
	private BigDecimal valorDocumento;
	
	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Retorna o campo somatorioQuantidade.
	 */


	public ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		
	}

	public String getPercentualValor(String valorTotal) {
		String valorPercentual = "";
		if (valorTotal != null && !valorTotal.equals("") && 
			new BigDecimal(valorTotal).compareTo(new BigDecimal("0.0")) != 0){
			valorPercentual = Util.calcularPercentual(getValorDocumento().toString() , valorTotal);			
		} else {
			valorPercentual = "0.0";
		} 
		return Util.formatarMoedaReal(new BigDecimal(valorPercentual));
	}
	
	public String getPercentualQuantidade(String quantidadeTotal) {
		String quantidadePercentual = "";
		if (quantidadeTotal != null && !quantidadeTotal.equals("") && 
			Integer.parseInt(quantidadeTotal) > 0){
			quantidadePercentual = Util.calcularPercentual(getQuantidadeDocumento().toString(), quantidadeTotal);		
		} else {
			quantidadePercentual = "0.0";
		}
		return Util.formatarMoedaReal(new BigDecimal(quantidadePercentual));
	}
	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}
	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}
	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	/**
	 * @param valorDocumento O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
}
