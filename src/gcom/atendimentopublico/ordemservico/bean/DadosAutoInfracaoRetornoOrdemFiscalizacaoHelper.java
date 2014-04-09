package gcom.atendimentopublico.ordemservico.bean;

import java.util.Date;

public class DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper {

	private Integer idResponsavel;
	
	private Integer idAutoInfracaoSituacao;
	
	private Date dataEmissao;
	
	private Date dataInicioRecurso;
	
	private Date dataTerminoRecurso;
	
	private String observacao;
	
	private Integer idDebitoTipo;
	
	private String indicadorGerarDebito;
	
	private Integer quantidadeParcelas;
	
	/**
	 * @return Retorna o campo quantidadeParcelas.
	 */
	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	/**
	 * @param quantidadeParcelas O quantidadeParcelas a ser setado.
	 */
	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getIndicadorGerarDebito() {
		return indicadorGerarDebito;
	}

	public void setIndicadorGerarDebito(String indicadorGerarDebito) {
		this.indicadorGerarDebito = indicadorGerarDebito;
	}

	public DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper(){}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataInicioRecurso() {
		return dataInicioRecurso;
	}

	public void setDataInicioRecurso(Date dataInicioRecurso) {
		this.dataInicioRecurso = dataInicioRecurso;
	}

	public Date getDataTerminoRecurso() {
		return dataTerminoRecurso;
	}

	public void setDataTerminoRecurso(Date dataTerminoRecurso) {
		this.dataTerminoRecurso = dataTerminoRecurso;
	}

	public Integer getIdAutoInfracaoSituacao() {
		return idAutoInfracaoSituacao;
	}

	public void setIdAutoInfracaoSituacao(Integer idAutoInfracaoSituacao) {
		this.idAutoInfracaoSituacao = idAutoInfracaoSituacao;
	}

	public Integer getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Integer idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdDebitoTipo() {
		return idDebitoTipo;
	}

	public void setIdDebitoTipo(Integer idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
	}
}
