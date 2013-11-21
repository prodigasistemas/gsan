package gcom.gerencial.bean;

import java.io.Serializable;

public class ResumoFaturamentoSimulacaoDetalheHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String valorCredito;
	
	private String valorDebito;
	
	public ResumoFaturamentoSimulacaoDetalheHelper(String descricao,
			String valorCredito, String valorDebito) {
		this.descricao = descricao;
		this.valorCredito = valorCredito;
		this.valorDebito = valorDebito;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	
	

}
