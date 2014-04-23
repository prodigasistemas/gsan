package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * @author Vivianne Sousa
 * @date 03/03/2007
 */
public class Relatorio2ViaContaTipo2DetailBean implements RelatorioBean {
	
	private String descricaoServicosTarifas; 
	private String valor; 
	
	public Relatorio2ViaContaTipo2DetailBean(String descricaoServicosTarifas, String valor) {
		this.descricaoServicosTarifas = descricaoServicosTarifas;
		this.valor = valor;
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
