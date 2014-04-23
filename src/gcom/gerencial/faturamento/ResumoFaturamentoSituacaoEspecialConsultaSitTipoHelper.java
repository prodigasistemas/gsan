package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;


/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper> resumoFaturamentoSituacaoEspecialConsultaMotivoHelper;
	private Integer idSituacaoTipo;
	private String situacaoTipoDescricao;
	private Integer totalSituacaoTipo;
	private BigDecimal totalPercentualSitTipo;
	private BigDecimal totalFatEstimadoSitTipo;
	private Integer totalQtLigacoesSitTipo;
	
	public BigDecimal getTotalPercentualSitTipo() {
		return totalPercentualSitTipo;
	}

	public void setTotalPercentualSitTipo(BigDecimal totalPercentualSitTipo) {
		this.totalPercentualSitTipo = totalPercentualSitTipo;
	}

	public ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(Integer idSituacaoTipo, String situacaoTipoDescricao, Integer totalSituacaoTipo) {
		super();
		this.idSituacaoTipo = idSituacaoTipo;
		this.situacaoTipoDescricao = situacaoTipoDescricao;
		this.totalSituacaoTipo = totalSituacaoTipo;
	}
	
	public Integer getIdSituacaoTipo() {
		return idSituacaoTipo;
	}
	public void setIdSituacaoTipo(Integer idSituacaoTipo) {
		this.idSituacaoTipo = idSituacaoTipo;
	}
	public String getSituacaoTipoDescricao() {
		return situacaoTipoDescricao;
	}
	public void setSituacaoTipoDescricao(String situacaoTipoDescricao) {
		this.situacaoTipoDescricao = situacaoTipoDescricao;
	}
	public Integer getTotalSituacaoTipo() {
		return totalSituacaoTipo;
	}
	public void setTotalSituacaoTipo(Integer totalSituacaoTipo) {
		this.totalSituacaoTipo = totalSituacaoTipo;
	}

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper> getResumoFaturamentoSituacaoEspecialConsultaMotivoHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaMotivoHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper> resumoFaturamentoSituacaoEspecialConsultaMotivoHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaMotivoHelper = resumoFaturamentoSituacaoEspecialConsultaMotivoHelper;
	}

	public BigDecimal getTotalFatEstimadoSitTipo() {
		return totalFatEstimadoSitTipo;
	}

	public void setTotalFatEstimadoSitTipo(BigDecimal totalFatEstimadoSitTipo) {
		this.totalFatEstimadoSitTipo = totalFatEstimadoSitTipo;
	}

	public Integer getTotalQtLigacoesSitTipo() {
		return totalQtLigacoesSitTipo;
	}

	public void setTotalQtLigacoesSitTipo(Integer totalQtLigacoesSitTipo) {
		this.totalQtLigacoesSitTipo = totalQtLigacoesSitTipo;
	}

		
}
