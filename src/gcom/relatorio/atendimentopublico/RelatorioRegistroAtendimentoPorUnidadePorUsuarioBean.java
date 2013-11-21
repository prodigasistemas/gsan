package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean implements RelatorioBean {
	
	private String unidade;
	private String usuario;
	private String especificacao;
	private Integer quantidadeSolicitada;
	
	
	public RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean() {
		this.quantidadeSolicitada = new Integer(0);
	}
	
	public RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean(String unidade, String especificacao, 
			Integer quantidadeSolicitada, Integer quantidadeAtendida, Boolean superior) {
		this.quantidadeSolicitada = new Integer(0);
	}
	
	public void setNull() {
		this.quantidadeSolicitada = null;
	}
	
	public boolean isEmpty() {
		if (quantidadeSolicitada != 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void sum(RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean other) {
		this.quantidadeSolicitada += other.quantidadeSolicitada;
	}
	
	public RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean copy() {
		RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean copy = new RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean();
		copy.setUnidade(this.getUnidade());
		copy.setEspecificacao(this.getEspecificacao());
		copy.setQuantidadeSolicitada(this.getQuantidadeSolicitada());
		return copy;
	}
	
	/*	
	public Integer getResidual() {
		if (quantidadeSolicitada == null) return null;
		if (quantidadeAtendida == null) return quantidadeSolicitada;
		return quantidadeSolicitada - quantidadeAtendida;
	}
	*/

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public Integer getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
}
