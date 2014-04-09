package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * @author Vivianne Sousa
 * @date 15/09/2006
 */
public class Relatorio2ViaContaDetailBean implements RelatorioBean {
	
	private String descricaoServicosTarifas; 
	private String consumoFaixa; 
	private String valor; 
	
	public Relatorio2ViaContaDetailBean(String descricaoServicosTarifas,
			String consumoFaixa, String valor) {
		this.descricaoServicosTarifas = descricaoServicosTarifas;
		this.consumoFaixa = consumoFaixa;
		this.valor = valor;
	}

	public Relatorio2ViaContaDetailBean(ContaLinhasDescricaoServicosTarifasTotalHelper linhasHelper){
		this.descricaoServicosTarifas = linhasHelper.getDescricaoServicosTarifas();
		this.consumoFaixa = linhasHelper.getConsumoFaixa();
		this.valor = linhasHelper.getValor();
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
