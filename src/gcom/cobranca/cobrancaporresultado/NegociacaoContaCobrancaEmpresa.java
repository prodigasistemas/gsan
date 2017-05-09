package gcom.cobranca.cobrancaporresultado;

import gcom.faturamento.conta.ContaGeral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class NegociacaoContaCobrancaEmpresa extends ObjetoTransacao {

	private static final long serialVersionUID = 6632512839675311778L;

	private Integer id;
	private NegociacaoCobrancaEmpresa negociacao;
	private ContaGeral contaGeral;
	private Date ultimaAlteracao;
	
	public NegociacaoContaCobrancaEmpresa(NegociacaoCobrancaEmpresa negociacao, ContaGeral conta, Date ultimaAlteracao) {
		this.negociacao = negociacao;
		this.contaGeral = conta;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public NegociacaoContaCobrancaEmpresa(){
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NegociacaoCobrancaEmpresa getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(NegociacaoCobrancaEmpresa negociacao) {
		this.negociacao = negociacao;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral conta) {
		this.contaGeral = conta;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

}
