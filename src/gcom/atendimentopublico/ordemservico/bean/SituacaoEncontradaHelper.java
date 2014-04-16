package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;

import java.util.Date;


/**
 * @author Raphael Rossiter
 * @date 03/03/2007
 */
public class SituacaoEncontradaHelper {
	
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	private short geracaoDebito;
	private Date dataFiscalizacao;
	private Short indicadorDebitoOrdemServicoFiscSit;
	
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}
	public short getGeracaoDebito() {
		return geracaoDebito;
	}
	public void setGeracaoDebito(short geracaoDebito) {
		this.geracaoDebito = geracaoDebito;
	}
	public Date getDataFiscalizacao() {
		return dataFiscalizacao;
	}
	public void setDataFiscalizacao(Date dataFiscalizacao) {
		this.dataFiscalizacao = dataFiscalizacao;
	}
	public SituacaoEncontradaHelper(){}
	
	public Short getIndicadorDebitoOrdemServicoFiscSit() {
		return indicadorDebitoOrdemServicoFiscSit;
	}
	public void setIndicadorDebitoOrdemServicoFiscSit(
			Short indicadorDebitoOrdemServicoFiscSit) {
		this.indicadorDebitoOrdemServicoFiscSit = indicadorDebitoOrdemServicoFiscSit;
	}
	
	
}
