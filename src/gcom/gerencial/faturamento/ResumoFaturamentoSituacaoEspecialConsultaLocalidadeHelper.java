package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper> resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper;

	private Integer idLocalidade;

	private String localidadeDescricao;

	private Integer totalLocalidade;
	
	private BigDecimal totalPercentualLocalidade;
	private BigDecimal totalFatEstimadoLocalidade;
	private Integer totalQtLigacoesLocalidade;

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public Integer getTotalLocalidade() {
		return totalLocalidade;
	}

	public void setTotalLocalidade(Integer totalLocalidade) {
		this.totalLocalidade = totalLocalidade;
	}

	public ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(
			Integer idLocalidade, String localidadeDescricao,
			Integer totalLocalidade) {
		super();
		
		this.idLocalidade = idLocalidade;
		this.localidadeDescricao = localidadeDescricao;
		this.totalLocalidade = totalLocalidade;
	}

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper> getResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper> resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper = resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper;
	}

	public BigDecimal getTotalPercentualLocalidade() {
		return totalPercentualLocalidade;
	}

	public void setTotalPercentualLocalidade(BigDecimal totalPercentualLocalidade) {
		this.totalPercentualLocalidade = totalPercentualLocalidade;
	}

	public BigDecimal getTotalFatEstimadoLocalidade() {
		return totalFatEstimadoLocalidade;
	}

	public void setTotalFatEstimadoLocalidade(BigDecimal totalFatEstimadoLocalidade) {
		this.totalFatEstimadoLocalidade = totalFatEstimadoLocalidade;
	}

	public Integer getTotalQtLigacoesLocalidade() {
		return totalQtLigacoesLocalidade;
	}

	public void setTotalQtLigacoesLocalidade(Integer totalQtLigacoesLocalidade) {
		this.totalQtLigacoesLocalidade = totalQtLigacoesLocalidade;
	}

}
