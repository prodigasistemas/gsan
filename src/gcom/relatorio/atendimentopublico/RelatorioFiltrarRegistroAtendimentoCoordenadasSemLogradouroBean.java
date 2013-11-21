package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouroBean implements RelatorioBean {
	
	private Integer numeroRA;
	private String especificacao;
	private Date dataAtendimento;
	private Date dataEncerramento;
	private String situacao;
	private String unidadeAtual;
	private String Endereco;
	private BigDecimal coordenadaNorte;
	private BigDecimal coordenadaLeste;
	
	
	public RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouroBean() { }

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public Integer getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(Integer numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public BigDecimal getCoordenadaLeste() {
		return coordenadaLeste;
	}

	public void setCoordenadaLeste(BigDecimal coordenadaLeste) {
		this.coordenadaLeste = coordenadaLeste;
	}

	public BigDecimal getCoordenadaNorte() {
		return coordenadaNorte;
	}

	public void setCoordenadaNorte(BigDecimal coordenadaNorte) {
		this.coordenadaNorte = coordenadaNorte;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
}
