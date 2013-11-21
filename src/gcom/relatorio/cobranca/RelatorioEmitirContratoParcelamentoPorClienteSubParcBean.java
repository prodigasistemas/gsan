package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1141] Emitir Contrato de Parcelamento por Cliente
 * 
 * Classe responsável por emitir o relatório com os dados de um Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 *
 * @date 28/04/2011
 */
public class RelatorioEmitirContratoParcelamentoPorClienteSubParcBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String parcelaPrestacoes;
	
	private String dataVencimento;
	
	private String valorParcela;
	
	private String situacao;
	
	
	public RelatorioEmitirContratoParcelamentoPorClienteSubParcBean() {
		super();
	}

	public RelatorioEmitirContratoParcelamentoPorClienteSubParcBean(String parcelaPrestacoes, String dataVencimento, String valorParcela, String situacao) {
		super();
		this.parcelaPrestacoes = parcelaPrestacoes;
		this.dataVencimento = dataVencimento;
		this.valorParcela = valorParcela;
		this.situacao = situacao;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getParcelaPrestacoes() {
		return parcelaPrestacoes;
	}

	public void setParcelaPrestacoes(String parcelaPrestacoes) {
		this.parcelaPrestacoes = parcelaPrestacoes;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}
	
}
