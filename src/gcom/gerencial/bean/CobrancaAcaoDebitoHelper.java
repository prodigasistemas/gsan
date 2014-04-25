package gcom.gerencial.bean;

import gcom.util.Util;

import java.math.BigDecimal;

public class CobrancaAcaoDebitoHelper {

	private Integer id;

	private String descricao;

	private Integer quantidadeDocumento;

	private BigDecimal valorDocumento;

	private Short indicadorAntesApos;

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
	 * @return Retorna o campo somatorioQuantidade.
	 */

	public CobrancaAcaoDebitoHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
	}
	
	/**
	 * @return Retorna o campo somatorioQuantidade.
	 */

	public CobrancaAcaoDebitoHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDocumento,
			Short indicadorAntesApos) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public String getPercentualValor(String valorTotal) {
		if (valorTotal != null && 
				new BigDecimal(valorTotal).compareTo(new BigDecimal("0.0")) != 0){
			String valorPercentual = Util.calcularPercentual(getValorDocumento()
					.toString(), valorTotal);
			valorPercentual = Util
					.formatarMoedaReal(new BigDecimal(valorPercentual));
			return valorPercentual;			
		}
		return Util.formatarMoedaReal(new BigDecimal("0.0"));
	}

	public String getPercentualQuantidade(String quantidadeTotal) {
		String quantidadePercentual = Util.calcularPercentual(
				getQuantidadeDocumento().toString(), quantidadeTotal);
		quantidadePercentual = Util.formatarMoedaReal(new BigDecimal(
				quantidadePercentual));
		return quantidadePercentual;
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
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	/**
	 * @param valorDocumento
	 *            O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	/**
	 * @return Retorna o campo indicadorAntesApos.
	 */
	public Short getIndicadorAntesApos() {
		return indicadorAntesApos;
	}

	/**
	 * @param indicadorAntesApos
	 *            O indicadorAntesApos a ser setado.
	 */
	public void setIndicadorAntesApos(Short indicadorAntesApos) {
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public String getDescricaoIndicador() {

		// Alterado por Sávio Luiz data:28/03/2007
		/*
		 * if(getIndicadorAntesApos() != null &&
		 * !getIndicadorAntesApos().equals("")){ if(getIndicadorAntesApos() ==
		 * ResumoCobrancaAcao.INDICADOR_ANTES){ descricao = getDescricao() + "
		 * ANTES"; }else if(getIndicadorAntesApos() ==
		 * ResumoCobrancaAcao.INDICADOR_APOS){ descricao = getDescricao() + "
		 * APOS"; } }else{ descricao = getDescricao(); }
		 */
		return descricao;
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
}
