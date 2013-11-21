package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioGestaoSolicitacoesRAPorChefiaBean implements RelatorioBean {
	
	private String unidade;
	private String descricaoUnidade;
	private Boolean superior;
	private boolean flag0800;
	
	private Integer quantidadeSolicitada;
	
	private Integer quantidadeExecutadaTotal;
	private Integer quantidadeExecutadaNoPrazo;
	private Integer quantidadeExecutadaForaPrazo;
	
	private Integer quantidadeAtendidaTotal;
	private Integer quantidadeAtendidaNoPrazo;
	private Integer quantidadeAtendidaForaPrazo;
	
	private Integer residualNoPrazo;
	private Integer residualForaPrazo;
	
	private Integer quantidadeSolicitada0800;
	
	private Integer quantidadeExecutadaTotal0800;
	private Integer quantidadeExecutadaNoPrazo0800;
	private Integer quantidadeExecutadaForaPrazo0800;
	
	private Integer quantidadeAtendidaTotal0800;
	private Integer quantidadeAtendidaNoPrazo0800;
	private Integer quantidadeAtendidaForaPrazo0800;
	
	private Integer residualNoPrazo0800;
	private Integer residualForaPrazo0800;
	
	public RelatorioGestaoSolicitacoesRAPorChefiaBean() {
		this.quantidadeSolicitada = new Integer(0);
		
		this.quantidadeExecutadaTotal = new Integer(0);
		this.quantidadeExecutadaNoPrazo = new Integer(0);
		this.quantidadeExecutadaForaPrazo = new Integer(0);
		
		this.quantidadeAtendidaTotal = new Integer(0);
		this.quantidadeAtendidaNoPrazo = new Integer(0);
		this.quantidadeAtendidaForaPrazo = new Integer(0);
		
		this.residualNoPrazo = new Integer(0);
		this.residualForaPrazo = new Integer(0);
		
		this.quantidadeSolicitada0800 = new Integer(0);
		
		this.quantidadeExecutadaTotal0800 = new Integer(0);
		this.quantidadeExecutadaNoPrazo0800 = new Integer(0);
		this.quantidadeExecutadaForaPrazo0800 = new Integer(0);
		
		this.quantidadeAtendidaTotal0800 = new Integer(0);
		this.quantidadeAtendidaNoPrazo0800 = new Integer(0);
		this.quantidadeAtendidaForaPrazo0800 = new Integer(0);
		
		this.residualNoPrazo0800 = new Integer(0);
		this.residualForaPrazo0800 = new Integer(0);
	}
	
	public void sum(RelatorioGestaoSolicitacoesRAPorChefiaBean other) {
		this.quantidadeSolicitada += other.quantidadeSolicitada;
		
		this.quantidadeExecutadaTotal += other.quantidadeExecutadaTotal;
		this.quantidadeExecutadaNoPrazo += other.quantidadeExecutadaNoPrazo;
		this.quantidadeExecutadaForaPrazo += other.quantidadeExecutadaForaPrazo;
		
		this.quantidadeAtendidaTotal += other.quantidadeAtendidaTotal;
		this.quantidadeAtendidaNoPrazo += other.quantidadeAtendidaNoPrazo;
		this.quantidadeAtendidaForaPrazo += other.quantidadeAtendidaForaPrazo;
		
		this.residualNoPrazo += other.residualNoPrazo;
		this.residualForaPrazo += other.residualForaPrazo;
		
		this.quantidadeSolicitada0800 += other.quantidadeSolicitada0800;
		
		this.quantidadeExecutadaTotal0800 += other.quantidadeExecutadaTotal0800;
		this.quantidadeExecutadaNoPrazo0800 += other.quantidadeExecutadaNoPrazo0800;
		this.quantidadeExecutadaForaPrazo0800 += other.quantidadeExecutadaForaPrazo0800;
		
		this.quantidadeAtendidaTotal0800 += other.quantidadeAtendidaTotal0800;
		this.quantidadeAtendidaNoPrazo0800 += other.quantidadeAtendidaNoPrazo0800;
		this.quantidadeAtendidaForaPrazo0800 += other.quantidadeAtendidaForaPrazo0800;
		
		this.residualNoPrazo0800 += other.residualNoPrazo0800;
		this.residualForaPrazo0800 += other.residualForaPrazo0800;
	}
	
	public RelatorioGestaoSolicitacoesRAPorChefiaBean copy() {
		RelatorioGestaoSolicitacoesRAPorChefiaBean copy = new RelatorioGestaoSolicitacoesRAPorChefiaBean();
		copy.setDescricaoUnidade(this.getDescricaoUnidade());
		copy.setQuantidadeSolicitada(this.getQuantidadeSolicitada());
		copy.setQuantidadeExecutadaTotal(this.getQuantidadeExecutadaTotal());
		copy.setQuantidadeExecutadaNoPrazo(this.getQuantidadeExecutadaNoPrazo());
		copy.setQuantidadeExecutadaForaPrazo(this.getQuantidadeExecutadaForaPrazo());
		copy.setQuantidadeAtendidaTotal(this.getQuantidadeAtendidaTotal());
		copy.setQuantidadeAtendidaNoPrazo(this.getQuantidadeAtendidaNoPrazo());
		copy.setQuantidadeAtendidaForaPrazo(this.getQuantidadeAtendidaForaPrazo());
		copy.setResidualNoPrazo(this.getResidualNoPrazo());
		copy.setResidualForaPrazo(this.getResidualForaPrazo());
		copy.setUnidade(this.getUnidade());
		copy.setSuperior(this.getSuperior());
		
		copy.setQuantidadeSolicitada0800(this.getQuantidadeSolicitada0800());
		copy.setQuantidadeExecutadaTotal0800(this.getQuantidadeExecutadaTotal0800());
		copy.setQuantidadeExecutadaNoPrazo0800(this.getQuantidadeExecutadaNoPrazo0800());
		copy.setQuantidadeExecutadaForaPrazo0800(this.getQuantidadeExecutadaForaPrazo0800());
		copy.setQuantidadeAtendidaTotal0800(this.getQuantidadeAtendidaTotal0800());
		copy.setQuantidadeAtendidaNoPrazo0800(this.getQuantidadeAtendidaNoPrazo0800());
		copy.setQuantidadeAtendidaForaPrazo0800(this.getQuantidadeAtendidaForaPrazo0800());
		copy.setResidualNoPrazo0800(this.getResidualNoPrazo0800());
		copy.setResidualForaPrazo0800(this.getResidualForaPrazo0800());
		return copy;
	}
	
	public void setNull() {
		this.quantidadeSolicitada = null;
		
		this.quantidadeExecutadaTotal = null;
		this.quantidadeExecutadaNoPrazo = null;
		this.quantidadeExecutadaForaPrazo = null;
		
		this.quantidadeAtendidaTotal = null;
		this.quantidadeAtendidaNoPrazo = null;
		this.quantidadeAtendidaForaPrazo = null;
		
		this.residualNoPrazo = null;
		this.residualForaPrazo = null;
		
		this.quantidadeSolicitada0800 = null;
		
		this.quantidadeExecutadaTotal0800 = null;
		this.quantidadeExecutadaNoPrazo0800 = null;
		this.quantidadeExecutadaForaPrazo0800 = null;
		
		this.quantidadeAtendidaTotal0800 = null;
		this.quantidadeAtendidaNoPrazo0800 = null;
		this.quantidadeAtendidaForaPrazo0800 = null;
		
		this.residualNoPrazo0800 = null;
		this.residualForaPrazo0800 = null;
	}
	
	public boolean isEmpty() {
		if (quantidadeSolicitada != 0 || 
			quantidadeExecutadaTotal != 0 || 
			quantidadeExecutadaNoPrazo != 0 ||
			quantidadeExecutadaForaPrazo != 0 ||
			quantidadeAtendidaTotal != 0 || 
			quantidadeAtendidaNoPrazo != 0 || 
			quantidadeAtendidaForaPrazo != 0 || 
			residualNoPrazo != 0 || 
			residualForaPrazo != 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public BigDecimal getPercentualAtendida() { 
		if (quantidadeSolicitada == null) return null;
		if (quantidadeSolicitada == 0) return new BigDecimal("0.0");
		
		double percentagem = (double) quantidadeAtendidaTotal / (double) quantidadeSolicitada;
		BigDecimal retorno = new BigDecimal(percentagem);
		return retorno.setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}
	
	public BigDecimal getPercentualNoPrazo() { 
		if (quantidadeAtendidaTotal == null) return null;
		if (quantidadeAtendidaTotal == 0) return new BigDecimal("0.0");
		
		double percentagem = (double) quantidadeAtendidaNoPrazo / (double) quantidadeSolicitada;
		BigDecimal retorno = new BigDecimal(percentagem);
		return retorno.setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}
	
	public BigDecimal getPercentualForaPrazo() { 
		if (quantidadeAtendidaTotal == null) return null;
		if (quantidadeAtendidaTotal == 0) return new BigDecimal("0.0");
		
		double percentagem = (double) quantidadeAtendidaForaPrazo / (double) quantidadeSolicitada;
		BigDecimal retorno = new BigDecimal(percentagem);
		return retorno.setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}
	
	//
	
	public BigDecimal getPercentualAtendida0800() { 
		if (quantidadeSolicitada0800 == null) return null;
		if (quantidadeSolicitada0800 == 0) return new BigDecimal("0.0");
		
		double percentagem = (double) quantidadeAtendidaTotal0800 / (double) quantidadeSolicitada0800;
		BigDecimal retorno = new BigDecimal(percentagem);
		return retorno.setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}
	
	public BigDecimal getPercentualNoPrazo0800() { 
		if (quantidadeAtendidaTotal0800 == null) return null;
		if (quantidadeAtendidaTotal0800 == 0) return new BigDecimal("0.0");
		
		double percentagem = (double) quantidadeAtendidaNoPrazo0800 / (double) quantidadeSolicitada0800;
		BigDecimal retorno = new BigDecimal(percentagem);
		return retorno.setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}
	
	public BigDecimal getPercentualForaPrazo0800() { 
		if (quantidadeAtendidaTotal0800 == null) return null;
		if (quantidadeAtendidaTotal0800 == 0) return new BigDecimal("0.0");
		
		double percentagem = (double) quantidadeAtendidaForaPrazo0800 / (double) quantidadeSolicitada0800;
		BigDecimal retorno = new BigDecimal(percentagem);
		return retorno.setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}

	public Integer getQuantidadeAtendidaForaPrazo() {
		return quantidadeAtendidaForaPrazo;
	}

	public void setQuantidadeAtendidaForaPrazo(Integer quantidadeAtendidaForaPrazo) {
		this.quantidadeAtendidaForaPrazo = quantidadeAtendidaForaPrazo;
	}

	public Integer getQuantidadeAtendidaNoPrazo() {
		return quantidadeAtendidaNoPrazo;
	}

	public void setQuantidadeAtendidaNoPrazo(Integer quantidadeAtendidaNoPrazo) {
		this.quantidadeAtendidaNoPrazo = quantidadeAtendidaNoPrazo;
	}

	public Integer getQuantidadeAtendidaTotal() {
		return quantidadeAtendidaTotal;
	}

	public void setQuantidadeAtendidaTotal(Integer quantidadeAtendidaTotal) {
		this.quantidadeAtendidaTotal = quantidadeAtendidaTotal;
	}

	public Integer getQuantidadeExecutadaForaPrazo() {
		return quantidadeExecutadaForaPrazo;
	}

	public void setQuantidadeExecutadaForaPrazo(Integer quantidadeExecutadaForaPrazo) {
		this.quantidadeExecutadaForaPrazo = quantidadeExecutadaForaPrazo;
	}

	public Integer getQuantidadeExecutadaNoPrazo() {
		return quantidadeExecutadaNoPrazo;
	}

	public void setQuantidadeExecutadaNoPrazo(Integer quantidadeExecutadaNoPrazo) {
		this.quantidadeExecutadaNoPrazo = quantidadeExecutadaNoPrazo;
	}

	public Integer getQuantidadeExecutadaTotal() {
		return quantidadeExecutadaTotal;
	}

	public void setQuantidadeExecutadaTotal(Integer quantidadeExecutadaTotal) {
		this.quantidadeExecutadaTotal = quantidadeExecutadaTotal;
	}

	public Integer getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public Integer getResidualForaPrazo() {
		return residualForaPrazo;
	}

	public void setResidualForaPrazo(Integer residualForaPrazo) {
		this.residualForaPrazo = residualForaPrazo;
	}

	public Integer getResidualNoPrazo() {
		return residualNoPrazo;
	}

	public void setResidualNoPrazo(Integer residualNoPrazo) {
		this.residualNoPrazo = residualNoPrazo;
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

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public Boolean getFlag0800() {
		return flag0800;
	}

	public void setFlag0800(Boolean flag0800) {
		this.flag0800 = flag0800;
	}

	public Integer getQuantidadeAtendidaForaPrazo0800() {
		return quantidadeAtendidaForaPrazo0800;
	}

	public void setQuantidadeAtendidaForaPrazo0800(
			Integer quantidadeAtendidaForaPrazo0800) {
		this.quantidadeAtendidaForaPrazo0800 = quantidadeAtendidaForaPrazo0800;
	}

	public Integer getQuantidadeAtendidaNoPrazo0800() {
		return quantidadeAtendidaNoPrazo0800;
	}

	public void setQuantidadeAtendidaNoPrazo0800(
			Integer quantidadeAtendidaNoPrazo0800) {
		this.quantidadeAtendidaNoPrazo0800 = quantidadeAtendidaNoPrazo0800;
	}

	public Integer getQuantidadeAtendidaTotal0800() {
		return quantidadeAtendidaTotal0800;
	}

	public void setQuantidadeAtendidaTotal0800(Integer quantidadeAtendidaTotal0800) {
		this.quantidadeAtendidaTotal0800 = quantidadeAtendidaTotal0800;
	}

	public Integer getQuantidadeExecutadaForaPrazo0800() {
		return quantidadeExecutadaForaPrazo0800;
	}

	public void setQuantidadeExecutadaForaPrazo0800(
			Integer quantidadeExecutadaForaPrazo0800) {
		this.quantidadeExecutadaForaPrazo0800 = quantidadeExecutadaForaPrazo0800;
	}

	public Integer getQuantidadeExecutadaNoPrazo0800() {
		return quantidadeExecutadaNoPrazo0800;
	}

	public void setQuantidadeExecutadaNoPrazo0800(
			Integer quantidadeExecutadaNoPrazo0800) {
		this.quantidadeExecutadaNoPrazo0800 = quantidadeExecutadaNoPrazo0800;
	}

	public Integer getQuantidadeExecutadaTotal0800() {
		return quantidadeExecutadaTotal0800;
	}

	public void setQuantidadeExecutadaTotal0800(Integer quantidadeExecutadaTotal0800) {
		this.quantidadeExecutadaTotal0800 = quantidadeExecutadaTotal0800;
	}

	public Integer getQuantidadeSolicitada0800() {
		return quantidadeSolicitada0800;
	}

	public void setQuantidadeSolicitada0800(Integer quantidadeSolicitada0800) {
		this.quantidadeSolicitada0800 = quantidadeSolicitada0800;
	}

	public Integer getResidualForaPrazo0800() {
		return residualForaPrazo0800;
	}

	public void setResidualForaPrazo0800(Integer residualForaPrazo0800) {
		this.residualForaPrazo0800 = residualForaPrazo0800;
	}

	public Integer getResidualNoPrazo0800() {
		return residualNoPrazo0800;
	}

	public void setResidualNoPrazo0800(Integer residualNoPrazo0800) {
		this.residualNoPrazo0800 = residualNoPrazo0800;
	}

	public void setFlag0800(boolean flag0800) {
		this.flag0800 = flag0800;
	}

}
