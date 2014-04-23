package gcom.arrecadacao.bean;

import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRetificacao;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Raphael Rossiter
 * 
 * [UC0259] - Processar Pagamento com Código de Barras
 * [SB0016] - Processar Pagamento Antecipado de Conta
 *
 */
public class ProcessarPagamentoParcialContaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ContaMotivoRetificacao contaMotivoRetificacao;
	
	private Conta conta;
	
	private Collection colecaoDebitoCobrado;
	
	private Collection colecaoCreditoRealizado;
	
	private Collection colecaoCategoriaOUSubcategoria;
	
	private Collection<CalcularValoresAguaEsgotoHelper> valoresConta;
	
	public ProcessarPagamentoParcialContaHelper(){}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public ContaMotivoRetificacao getContaMotivoRetificacao() {
		return contaMotivoRetificacao;
	}

	public void setContaMotivoRetificacao(
			ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	public Collection getColecaoCategoriaOUSubcategoria() {
		return colecaoCategoriaOUSubcategoria;
	}

	public void setColecaoCategoriaOUSubcategoria(
			Collection colecaoCategoriaOUSubcategoria) {
		this.colecaoCategoriaOUSubcategoria = colecaoCategoriaOUSubcategoria;
	}

	public Collection getColecaoCreditoRealizado() {
		return colecaoCreditoRealizado;
	}

	public void setColecaoCreditoRealizado(Collection colecaoCreditoRealizado) {
		this.colecaoCreditoRealizado = colecaoCreditoRealizado;
	}

	public Collection getColecaoDebitoCobrado() {
		return colecaoDebitoCobrado;
	}

	public void setColecaoDebitoCobrado(Collection colecaoDebitoCobrado) {
		this.colecaoDebitoCobrado = colecaoDebitoCobrado;
	}

	public Collection<CalcularValoresAguaEsgotoHelper> getValoresConta() {
		return valoresConta;
	}

	public void setValoresConta(
			Collection<CalcularValoresAguaEsgotoHelper> valoresConta) {
		this.valoresConta = valoresConta;
	}
}
