package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;

	private Integer idUnidadeNegocio;
	
	private String unidadeNegocioDescricaoAbreviada;
	
	private String unidadeNegocioDescricao;

	private Integer totalUnidadeNegocio;
	
	private BigDecimal totalPercentualUnidadeNegocio;
	private BigDecimal totalFatEstimadoUnidadeNegocio;
	private Integer totalQtLigacoesUnidadeNegocio;

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> getResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper = resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;
	}

	public ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper(Integer idUnidadeNegocio, String unidadeNegocioDescricaoAbreviada, String unidadeNegocioDescricao, Integer totalUnidadeNegocio) {
		super();
		
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.unidadeNegocioDescricaoAbreviada = unidadeNegocioDescricaoAbreviada;
		this.unidadeNegocioDescricao = unidadeNegocioDescricao;
		this.totalUnidadeNegocio = totalUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalFatEstimadoUnidadeNegocio.
	 */
	public BigDecimal getTotalFatEstimadoUnidadeNegocio() {
		return totalFatEstimadoUnidadeNegocio;
	}

	/**
	 * @param totalFatEstimadoUnidadeNegocio O totalFatEstimadoUnidadeNegocio a ser setado.
	 */
	public void setTotalFatEstimadoUnidadeNegocio(
			BigDecimal totalFatEstimadoUnidadeNegocio) {
		this.totalFatEstimadoUnidadeNegocio = totalFatEstimadoUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalPercentualUnidadeNegocio.
	 */
	public BigDecimal getTotalPercentualUnidadeNegocio() {
		return totalPercentualUnidadeNegocio;
	}

	/**
	 * @param totalPercentualUnidadeNegocio O totalPercentualUnidadeNegocio a ser setado.
	 */
	public void setTotalPercentualUnidadeNegocio(
			BigDecimal totalPercentualUnidadeNegocio) {
		this.totalPercentualUnidadeNegocio = totalPercentualUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalQtLigacoesUnidadeNegocio.
	 */
	public Integer getTotalQtLigacoesUnidadeNegocio() {
		return totalQtLigacoesUnidadeNegocio;
	}

	/**
	 * @param totalQtLigacoesUnidadeNegocio O totalQtLigacoesUnidadeNegocio a ser setado.
	 */
	public void setTotalQtLigacoesUnidadeNegocio(
			Integer totalQtLigacoesUnidadeNegocio) {
		this.totalQtLigacoesUnidadeNegocio = totalQtLigacoesUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalUnidadeNegocio.
	 */
	public Integer getTotalUnidadeNegocio() {
		return totalUnidadeNegocio;
	}

	/**
	 * @param totalUnidadeNegocio O totalUnidadeNegocio a ser setado.
	 */
	public void setTotalUnidadeNegocio(Integer totalUnidadeNegocio) {
		this.totalUnidadeNegocio = totalUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo unidadeNegocioDescricao.
	 */
	public String getUnidadeNegocioDescricao() {
		return unidadeNegocioDescricao;
	}

	/**
	 * @param unidadeNegocioDescricao O unidadeNegocioDescricao a ser setado.
	 */
	public void setUnidadeNegocioDescricao(String unidadeNegocioDescricao) {
		this.unidadeNegocioDescricao = unidadeNegocioDescricao;
	}

	/**
	 * @return Retorna o campo unidadeNegocioDescricaoAbreviada.
	 */
	public String getUnidadeNegocioDescricaoAbreviada() {
		return unidadeNegocioDescricaoAbreviada;
	}

	/**
	 * @param unidadeNegocioDescricaoAbreviada O unidadeNegocioDescricaoAbreviada a ser setado.
	 */
	public void setUnidadeNegocioDescricaoAbreviada(
			String unidadeNegocioDescricaoAbreviada) {
		this.unidadeNegocioDescricaoAbreviada = unidadeNegocioDescricaoAbreviada;
	}

}
