package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;

import java.io.Serializable;
import java.util.Date;

public class CobrancaAcaoAtividadeComandoFiscalizacaoSituacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	private Date ultimaAlteracao;
	
	public CobrancaAcaoAtividadeComandoFiscalizacaoSituacao() {
	}

	public CobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			FiscalizacaoSituacao fiscalizacaoSituacao, Date ultimaAlteracao) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}
	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	

}
