package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioResumoSolicitacoesRAPorUnidadeBean implements RelatorioBean {
	
	private String unidade;
	private String especificacao;
	private Integer quantidadeSolicitada;
	private Integer quantidadeExecutada;
	private Integer quantidadeAtendida;
	private Integer residual;
	private Boolean superior;
	
	public RelatorioResumoSolicitacoesRAPorUnidadeBean() {
		this.quantidadeSolicitada = new Integer(0);
		this.quantidadeExecutada = new Integer(0);
		this.quantidadeAtendida = new Integer(0);
		this.residual = new Integer(0);
	}
	
	public RelatorioResumoSolicitacoesRAPorUnidadeBean(String unidade, String especificacao, 
			Integer quantidadeSolicitada, Integer quantidadeAtendida, Boolean superior) {
		this.quantidadeSolicitada = new Integer(0);
		this.quantidadeExecutada = new Integer(0);
		this.quantidadeAtendida = new Integer(0);
		this.residual = new Integer(0);
	}
	
	public void setNull() {
		this.quantidadeExecutada = null;
		this.quantidadeSolicitada = null;
		this.quantidadeAtendida = null;
		this.residual = null;
	}
	
	public boolean isEmpty() {
		if (quantidadeSolicitada != 0 || quantidadeAtendida != 0 || quantidadeAtendida != 0 || residual != 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void sum(RelatorioResumoSolicitacoesRAPorUnidadeBean other) {
		this.quantidadeSolicitada += other.quantidadeSolicitada;
		this.quantidadeExecutada += other.quantidadeExecutada;
		this.quantidadeAtendida += other.quantidadeAtendida;
		this.residual += other.residual;
	}
	
	public RelatorioResumoSolicitacoesRAPorUnidadeBean copy() {
		RelatorioResumoSolicitacoesRAPorUnidadeBean copy = new RelatorioResumoSolicitacoesRAPorUnidadeBean();
		copy.setUnidade(this.getUnidade());
		copy.setEspecificacao(this.getEspecificacao());
		copy.setQuantidadeExecutada(this.getQuantidadeExecutada());
		copy.setQuantidadeAtendida(this.getQuantidadeAtendida());
		copy.setQuantidadeSolicitada(this.getQuantidadeSolicitada());
		copy.setResidual(this.getResidual());
		copy.setSuperior(this.getSuperior());
		return copy;
	}
	
	/*	
	public Integer getResidual() {
		if (quantidadeSolicitada == null) return null;
		if (quantidadeAtendida == null) return quantidadeSolicitada;
		return quantidadeSolicitada - quantidadeAtendida;
	}
	*/

	public BigDecimal getPercentualAtendida() {
		if (quantidadeSolicitada == null) return null;
		if (quantidadeSolicitada == 0) return new BigDecimal("0.0");
		
		double percentagem = (double) quantidadeAtendida / (double) quantidadeSolicitada;
		BigDecimal retorno = new BigDecimal(percentagem);
		return retorno.setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}
	
	public Integer getResidual() {
		return residual;
	}

	public void setResidual(Integer residual) {
		this.residual = residual;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public Integer getQuantidadeAtendida() {
		return quantidadeAtendida;
	}

	public void setQuantidadeAtendida(Integer quantidadeAtendida) {
		this.quantidadeAtendida = quantidadeAtendida;
	}

	public Integer getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public Boolean getSuperior() {
		return superior;
	}

	public void setSuperior(Boolean superior) {
		this.superior = superior;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Integer getQuantidadeExecutada() {
		return quantidadeExecutada;
	}

	public void setQuantidadeExecutada(Integer quantidadeExecutada) {
		this.quantidadeExecutada = quantidadeExecutada;
	}

}
