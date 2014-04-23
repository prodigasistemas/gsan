package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;

	private Integer idGerenciaRegional;
	
	private String gerenciaRegionalDescricaoAbreviada;
	
	private String gerenciaRegionalDescricao;

	private Integer totalGerenciaRegional;
	
	private BigDecimal totalPercentualGerencia;
	private BigDecimal totalFatEstimadoGerencia;
	private Integer totalQtLigacoesGerencia;

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

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> getResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper = resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;
	}

	public Integer getTotalGerenciaRegional() {
		return totalGerenciaRegional;
	}

	public void setTotalGerenciaRegional(Integer totalGerenciaRegional) {
		this.totalGerenciaRegional = totalGerenciaRegional;
	}

	public ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper(Integer idGerenciaRegional, String gerenciaRegionalDescricaoAbreviada, String gerenciaRegionalDescricao, Integer totalGerenciaRegional) {
		super();
		// TODO Auto-generated constructor stub
		this.idGerenciaRegional = idGerenciaRegional;
		this.gerenciaRegionalDescricaoAbreviada = gerenciaRegionalDescricaoAbreviada;
		this.gerenciaRegionalDescricao = gerenciaRegionalDescricao;
		this.totalGerenciaRegional = totalGerenciaRegional;
	}

	public BigDecimal getTotalPercentualGerencia() {
		return totalPercentualGerencia;
	}

	public void setTotalPercentualGerencia(BigDecimal totalPercentualGerencia) {
		this.totalPercentualGerencia = totalPercentualGerencia;
	}

	public BigDecimal getTotalFatEstimadoGerencia() {
		return totalFatEstimadoGerencia;
	}

	public void setTotalFatEstimadoGerencia(BigDecimal totalFatEstimadoGerencia) {
		this.totalFatEstimadoGerencia = totalFatEstimadoGerencia;
	}

	public Integer getTotalQtLigacoesGerencia() {
		return totalQtLigacoesGerencia;
	}

	public void setTotalQtLigacoesGerencia(Integer totalQtLigacoesGerencia) {
		this.totalQtLigacoesGerencia = totalQtLigacoesGerencia;
	}

	
}
