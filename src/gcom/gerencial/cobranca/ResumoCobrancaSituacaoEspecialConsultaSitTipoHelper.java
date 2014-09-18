package gcom.gerencial.cobranca;

import java.math.BigDecimal;
import java.util.Collection;


/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper {

	private Collection<ResumoCobrancaSituacaoEspecialConsultaMotivoHelper> resumoCobrancaSituacaoEspecialConsultaMotivoHelper;
	private Integer idSituacaoTipo;
	private String situacaoTipoDescricao;
	private Integer totalSituacaoTipo;
	private BigDecimal totalFatEstimadoSitTipo;
	private Integer totalQtLigacoesSitTipo;
	private BigDecimal totalPercentualSitTipo;
	
	public ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper() {}
	
	public ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper(Integer idSituacaoTipo, String situacaoTipoDescricao, Integer totalSituacaoTipo) {
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

	public Collection<ResumoCobrancaSituacaoEspecialConsultaMotivoHelper> getResumoCobrancaSituacaoEspecialConsultaMotivoHelper() {
		return resumoCobrancaSituacaoEspecialConsultaMotivoHelper;
	}

	public void setResumoCobrancaSituacaoEspecialConsultaMotivoHelper(
			Collection<ResumoCobrancaSituacaoEspecialConsultaMotivoHelper> resumoCobrancaSituacaoEspecialConsultaMotivoHelper) {
		this.resumoCobrancaSituacaoEspecialConsultaMotivoHelper = resumoCobrancaSituacaoEspecialConsultaMotivoHelper;
	}

	public BigDecimal getTotalFatEstimadoSitTipo() {
		return totalFatEstimadoSitTipo;
	}

	public void setTotalFatEstimadoSitTipo(BigDecimal totalFatEstimadoSitTipo) {
		this.totalFatEstimadoSitTipo = totalFatEstimadoSitTipo;
	}

	public BigDecimal getTotalPercentualSitTipo() {
		return totalPercentualSitTipo;
	}

	public void setTotalPercentualSitTipo(BigDecimal totalPercentualSitTipo) {
		this.totalPercentualSitTipo = totalPercentualSitTipo;
	}

	public Integer getTotalQtLigacoesSitTipo() {
		return totalQtLigacoesSitTipo;
	}

	public void setTotalQtLigacoesSitTipo(Integer totalQtLigacoesSitTipo) {
		this.totalQtLigacoesSitTipo = totalQtLigacoesSitTipo;
	}

		
}
