package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 28/06/2007
 */
public class RelatorioContaTipo2DetailConta2Bean implements RelatorioBean {
	
	private String descricaoServicosTarifas; 
	private String valor; 
	
	public RelatorioContaTipo2DetailConta2Bean(String descricaoServicosTarifas,
			 String valor) {
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
