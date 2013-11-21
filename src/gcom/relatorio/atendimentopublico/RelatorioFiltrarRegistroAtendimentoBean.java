package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.util.Date;

public class RelatorioFiltrarRegistroAtendimentoBean implements RelatorioBean {
	
	private Integer numeroRA;
	private String especificacao;
	private Date dataAtendimento;
	private Date dataEncerramento;
	private String situacao;
	private String unidadeAtual;
	private String observacao;
	private String matriculaImovel;
	private Integer idCliente;
	private String perfilImovel;
	
	public RelatorioFiltrarRegistroAtendimentoBean() { }

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

	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

}
