package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

public class RelatorioOrdemCorteContasDetailBean implements RelatorioBean {
	private String faturaAtrasada;
	
	private String vencimentoFatura;
	
	private String valorFatura;
	
	
	public RelatorioOrdemCorteContasDetailBean(String faturaAtrasada,
			String vencimentoFatura, String valorFatura) {
		this.faturaAtrasada = faturaAtrasada;
		this.vencimentoFatura = vencimentoFatura;
		this.valorFatura = valorFatura;
	}

	public String getFaturaAtrasada() {
		return faturaAtrasada;
	}

	public void setFaturaAtrasada(String faturaAtrasada) {
		this.faturaAtrasada = faturaAtrasada;
	}

	public String getVencimentoFatura() {
		return vencimentoFatura;
	}

	public void setVencimentoFatura(String vencimentoFatura) {
		this.vencimentoFatura = vencimentoFatura;
	}

	public String getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(String valorFatura) {
		this.valorFatura = valorFatura;
	}
	
	
}
