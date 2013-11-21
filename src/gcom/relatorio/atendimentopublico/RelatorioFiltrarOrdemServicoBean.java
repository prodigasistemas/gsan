package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.util.Date;

public class RelatorioFiltrarOrdemServicoBean implements RelatorioBean {
	
	private Integer numeroOS;
	private String tipoServico;
	private Integer numeroRA;
	private Integer matriculaImovel;
	private String situacao;
	private Date dataGeracao;
	private Date dataEmissao;
	private String unidadeAtual;
	private String imovelPerfil;
	
	public RelatorioFiltrarOrdemServicoBean() { }

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Integer getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
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

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	
}
