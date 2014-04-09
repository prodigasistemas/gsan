package gcom.cobranca.bean;

import java.io.Serializable;

/**
 * [UC0214] Efetuar Parcelamento de Débitos 
 *
 * @author Vivianne Sousa
 * @date 21/09/2007
 */
public class IndicadoresParcelamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer indicadorContasRevisao;
	private Integer indicadorDebitosACobrar;
	private Integer indicadorCreditoARealizar;
	private Integer indicadorGuiasPagamento;
	private Integer indicadorAcrescimosImpotualidade;
	private Integer indicadorNotasPromissorias;
	private Integer indicadorDividaAtiva;
	
	/**
	 * Construtor de IndicadoresParcelamentoHelper 
	 */
	public IndicadoresParcelamentoHelper() {
	}

	public Integer getIndicadorDividaAtiva() {
		return indicadorDividaAtiva;
	}

	public void setIndicadorDividaAtiva(Integer indicadorDividaAtiva) {
		this.indicadorDividaAtiva = indicadorDividaAtiva;
	}
	
	/**
	 * @return Retorna o campo indicadorAcrescimosImpotualidade.
	 */
	public Integer getIndicadorAcrescimosImpotualidade() {
		return indicadorAcrescimosImpotualidade;
	}

	/**
	 * @param indicadorAcrescimosImpotualidade O indicadorAcrescimosImpotualidade a ser setado.
	 */
	public void setIndicadorAcrescimosImpotualidade(
			Integer indicadorAcrescimosImpotualidade) {
		this.indicadorAcrescimosImpotualidade = indicadorAcrescimosImpotualidade;
	}

	/**
	 * @return Retorna o campo indicadorContasRevisao.
	 */
	public Integer getIndicadorContasRevisao() {
		return indicadorContasRevisao;
	}

	/**
	 * @param indicadorContasRevisao O indicadorContasRevisao a ser setado.
	 */
	public void setIndicadorContasRevisao(Integer indicadorContasRevisao) {
		this.indicadorContasRevisao = indicadorContasRevisao;
	}

	/**
	 * @return Retorna o campo indicadorCreditoARealizar.
	 */
	public Integer getIndicadorCreditoARealizar() {
		return indicadorCreditoARealizar;
	}

	/**
	 * @param indicadorCreditoARealizar O indicadorCreditoARealizar a ser setado.
	 */
	public void setIndicadorCreditoARealizar(Integer indicadorCreditoARealizar) {
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
	}

	/**
	 * @return Retorna o campo indicadorDebitosACobrar.
	 */
	public Integer getIndicadorDebitosACobrar() {
		return indicadorDebitosACobrar;
	}

	/**
	 * @param indicadorDebitosACobrar O indicadorDebitosACobrar a ser setado.
	 */
	public void setIndicadorDebitosACobrar(Integer indicadorDebitosACobrar) {
		this.indicadorDebitosACobrar = indicadorDebitosACobrar;
	}

	/**
	 * @return Retorna o campo indicadorGuiasPagamento.
	 */
	public Integer getIndicadorGuiasPagamento() {
		return indicadorGuiasPagamento;
	}

	/**
	 * @param indicadorGuiasPagamento O indicadorGuiasPagamento a ser setado.
	 */
	public void setIndicadorGuiasPagamento(Integer indicadorGuiasPagamento) {
		this.indicadorGuiasPagamento = indicadorGuiasPagamento;
	}

	/**
	 * @return Retorna o campo indicadorNotasPromissorias.
	 */
	public Integer getIndicadorNotasPromissorias() {
		return indicadorNotasPromissorias;
	}

	/**
	 * @param indicadorNotasPromissorias O indicadorNotasPromissorias a ser setado.
	 */
	public void setIndicadorNotasPromissorias(Integer indicadorNotasPromissorias) {
		this.indicadorNotasPromissorias = indicadorNotasPromissorias;
	}

	
	
}
