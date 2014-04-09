package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

public class RelatorioSupressoesReligacoesReestabelecimentosBean implements RelatorioBean {
	private static final long serialVersionUID = 1L;
	
	private String estado;
	private String gerenciaRegionalNomeAbreviado;
	private String gerenciaRegionalNome;
	private String unidadeNegocio;
	private String localidade;
	private Integer religacoesAntesDiasEstado;
	private Integer religacoesAposDiasEstado;
	private Integer supressoesEstado;	
	private Integer reestabelecimentosEstado;
	private Integer cortadosEstado;
	private Integer religacoesAntesDiasGerencia;
	private Integer religacoesAposDiasGerencia;
	private Integer supressoesGerencia;
	private Integer reestabelecimentosGerencia;
	private Integer cortadosGerencia;
	private Integer religacoesAntesDiasUnidade;
	private Integer religacoesAposDiasUnidade;
	private Integer supressoesUnidade;
	private Integer reestabelecimentosUnidade;
	private Integer cortadosUnidade;
	private Integer religacoesAntesDiasLocalidade;
	private Integer religacoesAposDiasLocalidade;
	private Integer supressoesLocalidade;
	private Integer reestabelecimentosLocalidade;
	private Integer cortadosLocalidade;

	
	public Integer getReestabelecimentosLocalidade() {
		return reestabelecimentosLocalidade;
	}
	public void setReestabelecimentosLocalidade(Integer reestabelecimentosLocalidade) {
		this.reestabelecimentosLocalidade = reestabelecimentosLocalidade;
	}
	public Integer getReestabelecimentosUnidade() {
		return reestabelecimentosUnidade;
	}
	public void setReestabelecimentosUnidade(Integer reestabelecimentosUnidade) {
		this.reestabelecimentosUnidade = reestabelecimentosUnidade;
	}
	public Integer getReligacoesAntesDiasLocalidade() {
		return religacoesAntesDiasLocalidade;
	}
	public void setReligacoesAntesDiasLocalidade(
			Integer religacoesAntesDiasLocalidade) {
		this.religacoesAntesDiasLocalidade = religacoesAntesDiasLocalidade;
	}
	public Integer getReligacoesAntesDiasUnidade() {
		return religacoesAntesDiasUnidade;
	}
	public void setReligacoesAntesDiasUnidade(Integer religacoesAntesDiasUnidade) {
		this.religacoesAntesDiasUnidade = religacoesAntesDiasUnidade;
	}
	public Integer getReligacoesAposDiasLocalidade() {
		return religacoesAposDiasLocalidade;
	}
	public void setReligacoesAposDiasLocalidade(Integer religacoesAposDiasLocalidade) {
		this.religacoesAposDiasLocalidade = religacoesAposDiasLocalidade;
	}
	public Integer getReligacoesAposDiasUnidade() {
		return religacoesAposDiasUnidade;
	}
	public void setReligacoesAposDiasUnidade(Integer religacoesAposDiasUnidade) {
		this.religacoesAposDiasUnidade = religacoesAposDiasUnidade;
	}
	public Integer getSupressoesLocalidade() {
		return supressoesLocalidade;
	}
	public void setSupressoesLocalidade(Integer supressoesLocalidade) {
		this.supressoesLocalidade = supressoesLocalidade;
	}
	public Integer getSupressoesUnidade() {
		return supressoesUnidade;
	}
	public void setSupressoesUnidade(Integer supressoesUnidade) {
		this.supressoesUnidade = supressoesUnidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getGerenciaRegionalNome() {
		return gerenciaRegionalNome;
	}
	public void setGerenciaRegionalNome(String gerenciaRegionalNome) {
		this.gerenciaRegionalNome = gerenciaRegionalNome;
	}
	public String getGerenciaRegionalNomeAbreviado() {
		return gerenciaRegionalNomeAbreviado;
	}
	public void setGerenciaRegionalNomeAbreviado(
			String gerenciaRegionalNomeAbreviado) {
		this.gerenciaRegionalNomeAbreviado = gerenciaRegionalNomeAbreviado;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public Integer getReestabelecimentosEstado() {
		return reestabelecimentosEstado;
	}
	public void setReestabelecimentosEstado(Integer reestabelecimentosEstado) {
		this.reestabelecimentosEstado = reestabelecimentosEstado;
	}
	public Integer getReligacoesAntesDiasEstado() {
		return religacoesAntesDiasEstado;
	}
	public void setReligacoesAntesDiasEstado(Integer religacoesAntesDiasEstado) {
		this.religacoesAntesDiasEstado = religacoesAntesDiasEstado;
	}
	public Integer getReligacoesAposDiasEstado() {
		return religacoesAposDiasEstado;
	}
	public void setReligacoesAposDiasEstado(Integer religacoesAposDiasEstado) {
		this.religacoesAposDiasEstado = religacoesAposDiasEstado;
	}
	public Integer getSupressoesEstado() {
		return supressoesEstado;
	}
	public void setSupressoesEstado(Integer supressoesEstado) {
		this.supressoesEstado = supressoesEstado;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public Integer getReestabelecimentosGerencia() {
		return reestabelecimentosGerencia;
	}
	public void setReestabelecimentosGerencia(Integer reestabelecimentosGerencia) {
		this.reestabelecimentosGerencia = reestabelecimentosGerencia;
	}
	public Integer getReligacoesAntesDiasGerencia() {
		return religacoesAntesDiasGerencia;
	}
	public void setReligacoesAntesDiasGerencia(Integer religacoesAntesDiasGerencia) {
		this.religacoesAntesDiasGerencia = religacoesAntesDiasGerencia;
	}
	public Integer getReligacoesAposDiasGerencia() {
		return religacoesAposDiasGerencia;
	}
	public void setReligacoesAposDiasGerencia(Integer religacoesAposDiasGerencia) {
		this.religacoesAposDiasGerencia = religacoesAposDiasGerencia;
	}
	public Integer getSupressoesGerencia() {
		return supressoesGerencia;
	}
	public void setSupressoesGerencia(Integer supressoesGerencia) {
		this.supressoesGerencia = supressoesGerencia;
	}
	public Integer getCortadosEstado() {
		return cortadosEstado;
	}
	public void setCortadosEstado(Integer cortadosEstado) {
		this.cortadosEstado = cortadosEstado;
	}
	public Integer getCortadosGerencia() {
		return cortadosGerencia;
	}
	public void setCortadosGerencia(Integer cortadosGerencia) {
		this.cortadosGerencia = cortadosGerencia;
	}
	public Integer getCortadosUnidade() {
		return cortadosUnidade;
	}
	public void setCortadosUnidade(Integer cortadosUnidade) {
		this.cortadosUnidade = cortadosUnidade;
	}
	public Integer getCortadosLocalidade() {
		return cortadosLocalidade;
	}
	public void setCortadosLocalidade(Integer cortadosLocalidade) {
		this.cortadosLocalidade = cortadosLocalidade;
	}
	
	
}
