package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;

	private Integer codigoSetorComercial;

	private String setorComercialDescricao;

	private Integer totalSetorComercial;
	
	private BigDecimal totalPercentualSetorComercial;
	private BigDecimal totalFatEstimadoSetorComercial;
	private Integer totalQtLigacoesSetorComercial;

	public ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(
			Integer codigoSetorComercial, String setorComercialDescricao,
			Integer totalSetorComercial) {
		super();
		// TODO Auto-generated constructor stub
		this.codigoSetorComercial = codigoSetorComercial;
		this.setorComercialDescricao = setorComercialDescricao;
		this.totalSetorComercial = totalSetorComercial;
	}

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> getResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper = resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo setorComercialDescricao.
	 */
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	/**
	 * @param setorComercialDescricao O setorComercialDescricao a ser setado.
	 */
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	/**
	 * @return Retorna o campo totalFatEstimadoSetorComercial.
	 */
	public BigDecimal getTotalFatEstimadoSetorComercial() {
		return totalFatEstimadoSetorComercial;
	}

	/**
	 * @param totalFatEstimadoSetorComercial O totalFatEstimadoSetorComercial a ser setado.
	 */
	public void setTotalFatEstimadoSetorComercial(
			BigDecimal totalFatEstimadoSetorComercial) {
		this.totalFatEstimadoSetorComercial = totalFatEstimadoSetorComercial;
	}

	/**
	 * @return Retorna o campo totalPercentualSetorComercial.
	 */
	public BigDecimal getTotalPercentualSetorComercial() {
		return totalPercentualSetorComercial;
	}

	/**
	 * @param totalPercentualSetorComercial O totalPercentualSetorComercial a ser setado.
	 */
	public void setTotalPercentualSetorComercial(
			BigDecimal totalPercentualSetorComercial) {
		this.totalPercentualSetorComercial = totalPercentualSetorComercial;
	}

	/**
	 * @return Retorna o campo totalQtLigacoesSetorComercial.
	 */
	public Integer getTotalQtLigacoesSetorComercial() {
		return totalQtLigacoesSetorComercial;
	}

	/**
	 * @param totalQtLigacoesSetorComercial O totalQtLigacoesSetorComercial a ser setado.
	 */
	public void setTotalQtLigacoesSetorComercial(
			Integer totalQtLigacoesSetorComercial) {
		this.totalQtLigacoesSetorComercial = totalQtLigacoesSetorComercial;
	}

	/**
	 * @return Retorna o campo totalSetorComercial.
	 */
	public Integer getTotalSetorComercial() {
		return totalSetorComercial;
	}

	/**
	 * @param totalSetorComercial O totalSetorComercial a ser setado.
	 */
	public void setTotalSetorComercial(Integer totalSetorComercial) {
		this.totalSetorComercial = totalSetorComercial;
	}

	

}
