package gcom.cobranca.contratoparcelamento;

import java.io.Serializable;

public class PrestacaoContratoParcelamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idParcela;
	
	private String numeroParcela;
	
	private String numeroPrestacoes;
	
	private String dataVencimento;
	
	private String valorParcela;
	
	private String situacao;

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(String numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
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

	public Integer getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(Integer idParcela) {
		this.idParcela = idParcela;
	}
	
}
