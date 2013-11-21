package gcom.faturamento;

import gcom.faturamento.conta.ContaGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExtratoQuitacaoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private ContaGeral contaGeral;
	private ExtratoQuitacao extratoQuitacao;
	private Date dataSituacao;
	private Integer anoMesReferenciaConta;
	private String descricaoSituacao;
	private Date ultimaAlteracao;
	private BigDecimal valorConta;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ContaGeral getContaGeral() {
		return contaGeral;
	}
	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}
	public ExtratoQuitacao getExtratoQuitacao() {
		return extratoQuitacao;
	}
	public void setExtratoQuitacao(ExtratoQuitacao extratoQuitacao) {
		this.extratoQuitacao = extratoQuitacao;
	}
	public Date getDataSituacao() {
		return dataSituacao;
	}
	public void setDataSituacao(Date dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	public Integer getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}
	public void setAnoMesReferenciaConta(Integer anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}
	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}
	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public BigDecimal getValorConta() {
		return valorConta;
	}
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}
	
	
	
	

	
}
