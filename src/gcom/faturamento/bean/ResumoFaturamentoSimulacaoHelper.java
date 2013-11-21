package gcom.faturamento.bean;

import gcom.faturamento.ResumoFaturamentoSimulacao;
import gcom.faturamento.ResumoFaturamentoSimulacaoCredito;
import gcom.faturamento.ResumoFaturamentoSimulacaoDebito;

import java.util.Collection;

public class ResumoFaturamentoSimulacaoHelper {
	
	private ResumoFaturamentoSimulacao resumoFaturamentoSimulacao;
	private Collection<ResumoFaturamentoSimulacaoCredito> resumoFaturamentoSimulacaoCredito;
	private Collection<ResumoFaturamentoSimulacaoDebito> resumoFaturamentoSimulacaoDebito;
	
	public ResumoFaturamentoSimulacao getResumoFaturamentoSimulacao() {
		return resumoFaturamentoSimulacao;
	}
	public void setResumoFaturamentoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao) {
		this.resumoFaturamentoSimulacao = resumoFaturamentoSimulacao;
	}
	public Collection<ResumoFaturamentoSimulacaoCredito> getResumoFaturamentoSimulacaoCredito() {
		return resumoFaturamentoSimulacaoCredito;
	}
	public void setResumoFaturamentoSimulacaoCredito(
			Collection<ResumoFaturamentoSimulacaoCredito> resumoFaturamentoSimulacaoCredito) {
		this.resumoFaturamentoSimulacaoCredito = resumoFaturamentoSimulacaoCredito;
	}
	public Collection<ResumoFaturamentoSimulacaoDebito> getResumoFaturamentoSimulacaoDebito() {
		return resumoFaturamentoSimulacaoDebito;
	}
	public void setResumoFaturamentoSimulacaoDebito(
			Collection<ResumoFaturamentoSimulacaoDebito> resumoFaturamentoSimulacaoDebito) {
		this.resumoFaturamentoSimulacaoDebito = resumoFaturamentoSimulacaoDebito;
	}	
}
