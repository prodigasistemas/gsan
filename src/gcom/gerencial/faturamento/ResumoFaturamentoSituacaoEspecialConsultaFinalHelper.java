package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaFinalHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
	private Integer totalGeral;
	private BigDecimal totalPercentualGeral;
	private BigDecimal totalFatEstimadoGeral;
	private Integer totalQtLigacoesGeral;
	
	public Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> getResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
	}
	public void setResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
	}
	public BigDecimal getTotalFatEstimadoGeral() {
		return totalFatEstimadoGeral;
	}
	public void setTotalFatEstimadoGeral(BigDecimal totalFatEstimadoGeral) {
		this.totalFatEstimadoGeral = totalFatEstimadoGeral;
	}
	public Integer getTotalGeral() {
		return totalGeral;
	}
	public void setTotalGeral(Integer totalGeral) {
		this.totalGeral = totalGeral;
	}
	public BigDecimal getTotalPercentualGeral() {
		return totalPercentualGeral;
	}
	public void setTotalPercentualGeral(BigDecimal totalPercentualGeral) {
		this.totalPercentualGeral = totalPercentualGeral;
	}
	public Integer getTotalQtLigacoesGeral() {
		return totalQtLigacoesGeral;
	}
	public void setTotalQtLigacoesGeral(Integer totalQtLigacoesGeral) {
		this.totalQtLigacoesGeral = totalQtLigacoesGeral;
	}
	public ResumoFaturamentoSituacaoEspecialConsultaFinalHelper(Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper, Integer totalGeral, BigDecimal totalPercentualGeral, BigDecimal totalFatEstimadoGeral, Integer totalQtLigacoesGeral) {
		super();
		// TODO Auto-generated constructor stub
		this.resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
		this.totalGeral = totalGeral;
		this.totalPercentualGeral = totalPercentualGeral;
		this.totalFatEstimadoGeral = totalFatEstimadoGeral;
		this.totalQtLigacoesGeral = totalQtLigacoesGeral;
	}

	

}
