package gcom.gerencial.bean;

import java.math.BigDecimal;
import java.util.Collection;

public class CobrancaAcaoSituacaoHelper {

	private Integer id;

	private String descricao;

	private Collection colecaoCobrancaAcaoDebito;

	private Integer quantidadeDocumento;

	private BigDecimal valorDoumento;

	private BigDecimal porcentagemQuantidade;

	private BigDecimal porcentagemValor;

	public CobrancaAcaoSituacaoHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDoumento) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDoumento = valorDoumento;
	}

	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}

	/**
	 * @param quantidadeDocumento
	 *            O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaAcaoDebito.
	 */
	public Collection getColecaoCobrancaAcaoDebito() {
		return colecaoCobrancaAcaoDebito;
	}

	/**
	 * @param colecaoCobrancaAcaoDebito
	 *            O colecaoCobrancaAcaoDebito a ser setado.
	 */
	public void setColecaoCobrancaAcaoDebito(
			Collection colecaoCobrancaAcaoDebito) {
		this.colecaoCobrancaAcaoDebito = colecaoCobrancaAcaoDebito;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo valorDoumento.
	 */
	public BigDecimal getValorDoumento() {
		return valorDoumento;
	}

	/**
	 * @param valorDoumento
	 *            O valorDoumento a ser setado.
	 */
	public void setValorDoumento(BigDecimal valorDoumento) {
		this.valorDoumento = valorDoumento;
	}

	public BigDecimal getPorcentagemQuantidade() {
		return porcentagemQuantidade;
	}

	public void setPorcentagemQuantidade(BigDecimal porcentagemQuantidade) {
		this.porcentagemQuantidade = porcentagemQuantidade;
	}

	public BigDecimal getPorcentagemValor() {
		return porcentagemValor;
	}

	public void setPorcentagemValor(BigDecimal porcentagemValor) {
		this.porcentagemValor = porcentagemValor;
	}

}
