package gcom.relatorio.arrecadacao.pagamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0379] Emitir Guia de Pagamento
 * @author Vivianne Sousa
 * @date 22/09/2006
 */
public class RelatorioEmitirGuiaPagamentoDetailBean implements RelatorioBean {
	
	private String descricaoServicosTarifas; 
	private String valor; 
	
	public RelatorioEmitirGuiaPagamentoDetailBean(
			String descricaoServicosTarifas,
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
