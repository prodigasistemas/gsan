package gcom.relatorio.faturamento.conta;

import java.io.Serializable;

public class ContaLinhasDescricaoServicosTarifasTotalHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoServicosTarifas;
	private String consumoFaixa;
	private String valor;
	
	/** minimal constructor */
    public ContaLinhasDescricaoServicosTarifasTotalHelper(
    		String descricaoServicosTarifas,
    		String consumoFaixa,
    		String valor) {
    	this.descricaoServicosTarifas = descricaoServicosTarifas;
    	this.consumoFaixa = consumoFaixa;
    	this.valor = valor;
    	
    }

    public ContaLinhasDescricaoServicosTarifasTotalHelper() {
    }

	public String getConsumoFaixa() {
		return consumoFaixa;
	}

	public void setConsumoFaixa(String consumoFaixa) {
		this.consumoFaixa = consumoFaixa;
	}

	public String getDescricaoServicosTarifas() {
		return descricaoServicosTarifas;
	}

	public void setDescricaoServicosTarifas(String descricaoServicosTarifas) {
		this.descricaoServicosTarifas = descricaoServicosTarifas;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	

}
