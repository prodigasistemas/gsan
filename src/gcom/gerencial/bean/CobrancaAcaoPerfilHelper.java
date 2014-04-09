package gcom.gerencial.bean;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class CobrancaAcaoPerfilHelper implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private Integer quantidadeDocumento;
	private BigDecimal valorDocumento;
	private Collection colecaoCobrancaAcaoPerfilIndicador;
	
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


	public CobrancaAcaoPerfilHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		
	}

	public String getPercentualValor(String valorTotal) {
		String valorPercentual = Util.calcularPercentual(getValorDocumento().toString() , valorTotal);
		valorPercentual = Util.formatarMoedaReal(new BigDecimal(valorPercentual));
		return valorPercentual;
	}
	
	public String getPercentualQuantidade(String quantidadeTotal) {
		String quantidadePercentual = Util.calcularPercentual(getQuantidadeDocumento().toString(), quantidadeTotal);
		quantidadePercentual = Util.formatarMoedaReal(new BigDecimal(quantidadePercentual));
		return quantidadePercentual;
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
	/**
	 * @return Retorna o campo colecaoCobrancaAcaoPerfilIndicador.
	 */
	public Collection getColecaoCobrancaAcaoPerfilIndicador() {
		return colecaoCobrancaAcaoPerfilIndicador;
	}
	/**
	 * @param colecaoCobrancaAcaoPerfilIndicador O colecaoCobrancaAcaoPerfilIndicador a ser setado.
	 */
	public void setColecaoCobrancaAcaoPerfilIndicador(
			Collection colecaoCobrancaAcaoPerfilIndicador) {
		this.colecaoCobrancaAcaoPerfilIndicador = colecaoCobrancaAcaoPerfilIndicador;
	}
}
