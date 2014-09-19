package gcom.gerencial.cobranca;

import java.math.BigDecimal;
import java.util.Collection;

/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaFinalHelper {

	private Collection<ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper> resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper;
	private Integer totalGeral;
	private BigDecimal totalPercentualGeral;
	private BigDecimal totalFatEstimadoGeral;
	private Integer totalQtLigacoesGeral;
	
	public ResumoCobrancaSituacaoEspecialConsultaFinalHelper(Collection<ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper> resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper, Integer totalGeral, BigDecimal totalPercentualGeral, BigDecimal totalFatEstimadoGeral, Integer totalQtLigacoesGeral) {
		super();
		
		this.resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper = resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper;
		this.totalGeral = totalGeral;
		this.totalPercentualGeral = totalPercentualGeral;
		this.totalFatEstimadoGeral = totalFatEstimadoGeral;
		this.totalQtLigacoesGeral = totalQtLigacoesGeral;
	}
	public Collection<ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper> getResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper() {
		return resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper;
	}
	public void setResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper(
			Collection<ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper> resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper) {
		this.resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper = resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper;
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
	
	
	
}
