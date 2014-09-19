package gcom.gerencial.cobranca;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper {

	private Collection<ResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper> resumoCobrancaSituacaoEspecialConsultaSetorComercialHelper;

	private Integer idLocalidade;

	private String localidadeDescricao;

	private Integer totalLocalidade;
	
	private BigDecimal totalFatEstimadoLocalidade;
	private Integer totalQtLigacoesLocalidade;
	private BigDecimal totalPercentualLocalidade;

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

	public ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper() {}
	
	public ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper(
			Integer idLocalidade, String localidadeDescricao,
			Integer totalLocalidade) {
		super();
		
		this.idLocalidade = idLocalidade;
		this.localidadeDescricao = localidadeDescricao;
		this.totalLocalidade = totalLocalidade;
	}

	public Collection<ResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper> getResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper() {
		return resumoCobrancaSituacaoEspecialConsultaSetorComercialHelper;
	}

	public void setResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper(
			Collection<ResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper> resumoCobrancaSituacaoEspecialConsultaSetorComercialHelper) {
		this.resumoCobrancaSituacaoEspecialConsultaSetorComercialHelper = resumoCobrancaSituacaoEspecialConsultaSetorComercialHelper;
	}

	public BigDecimal getTotalFatEstimadoLocalidade() {
		return totalFatEstimadoLocalidade;
	}

	public void setTotalFatEstimadoLocalidade(BigDecimal totalFatEstimadoLocalidade) {
		this.totalFatEstimadoLocalidade = totalFatEstimadoLocalidade;
	}

	public BigDecimal getTotalPercentualLocalidade() {
		return totalPercentualLocalidade;
	}

	public void setTotalPercentualLocalidade(BigDecimal totalPercentualLocalidade) {
		this.totalPercentualLocalidade = totalPercentualLocalidade;
	}

	public Integer getTotalQtLigacoesLocalidade() {
		return totalQtLigacoesLocalidade;
	}

	public void setTotalQtLigacoesLocalidade(Integer totalQtLigacoesLocalidade) {
		this.totalQtLigacoesLocalidade = totalQtLigacoesLocalidade;
	}

}
