package gcom.cadastro.projeto;

import gcom.cadastro.cliente.Cliente;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Projeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String nomeAbreviado;

	private Cliente orgaoFinanciador;

	private Date dataInicio;

	private Date dataFim;

	private BigDecimal valorFinanciamento;

	private String observacao;

	private Date ultimaAlteracao;

	public Projeto(String nome, Date dataInicio) {
		this.nome = nome;
		this.dataInicio = dataInicio;
	}
	/** default constructor */
	public Projeto() {
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Cliente getOrgaoFinanciador() {
		return orgaoFinanciador;
	}

	public void setOrgaoFinanciador(Cliente orgaoFinanciador) {
		this.orgaoFinanciador = orgaoFinanciador;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorFinanciamento() {
		return valorFinanciamento;
	}

	public void setValorFinanciamento(BigDecimal valorFinanciamento) {
		this.valorFinanciamento = valorFinanciamento;
	}

}
