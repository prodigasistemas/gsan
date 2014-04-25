package gcom.gerencial.cobranca;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper {

	private Collection<ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper> resumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper;

	private Integer idGerenciaRegional;
	
	private String gerenciaRegionalDescricaoAbreviada;
	
	private String gerenciaRegionalDescricao;

	private Integer totalGerenciaRegional;
	
	private BigDecimal totalFatEstimadoGerencia;
	
	private Integer totalQtLigacoesGerencia;
	
	private BigDecimal totalPercentualGerencia;

	public String getGerenciaRegionalDescricao() {
		return gerenciaRegionalDescricao;
	}

	public void setGerenciaRegionalDescricao(String gerenciaRegionalDescricao) {
		this.gerenciaRegionalDescricao = gerenciaRegionalDescricao;
	}

	public String getGerenciaRegionalDescricaoAbreviada() {
		return gerenciaRegionalDescricaoAbreviada;
	}

	public void setGerenciaRegionalDescricaoAbreviada(
			String gerenciaRegionalDescricaoAbreviada) {
		this.gerenciaRegionalDescricaoAbreviada = gerenciaRegionalDescricaoAbreviada;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Collection<ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper> getResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper() {
		return resumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper;
	}

	public void setResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper(
			Collection<ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper> resumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper) {
		this.resumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper = resumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper;
	}

	public Integer getTotalGerenciaRegional() {
		return totalGerenciaRegional;
	}

	public void setTotalGerenciaRegional(Integer totalGerenciaRegional) {
		this.totalGerenciaRegional = totalGerenciaRegional;
	}

	public ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper() {}
	
	public ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper(Integer idGerenciaRegional, String gerenciaRegionalDescricaoAbreviada, String gerenciaRegionalDescricao, Integer totalGerenciaRegional) {
		super();
		// TODO Auto-generated constructor stub
		this.idGerenciaRegional = idGerenciaRegional;
		this.gerenciaRegionalDescricaoAbreviada = gerenciaRegionalDescricaoAbreviada;
		this.gerenciaRegionalDescricao = gerenciaRegionalDescricao;
		this.totalGerenciaRegional = totalGerenciaRegional;
	}

	public BigDecimal getTotalFatEstimadoGerencia() {
		return totalFatEstimadoGerencia;
	}

	public void setTotalFatEstimadoGerencia(BigDecimal totalFatEstimadoGerencia) {
		this.totalFatEstimadoGerencia = totalFatEstimadoGerencia;
	}

	public BigDecimal getTotalPercentualGerencia() {
		return totalPercentualGerencia;
	}

	public void setTotalPercentualGerencia(BigDecimal totalPercentualGerencia) {
		this.totalPercentualGerencia = totalPercentualGerencia;
	}

	public Integer getTotalQtLigacoesGerencia() {
		return totalQtLigacoesGerencia;
	}

	public void setTotalQtLigacoesGerencia(Integer totalQtLigacoesGerencia) {
		this.totalQtLigacoesGerencia = totalQtLigacoesGerencia;
	}

	
}
