package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0444] Gerar e Emitir Extrato de Débito
 * @author Vivianne Sousa
 * @date 23/04/2010
 */
public class RelatorioExtratoDebitoContasDetailBean implements RelatorioBean {

	
	private String faturaAtrasada1 ;
	
	private String vencimentoFatura1;
	
	private String valorFatura1;
	
	private String faturaAtrasada2 ;
	
	private String vencimentoFatura2;
	
	private String valorFatura2;

	public RelatorioExtratoDebitoContasDetailBean(String faturaAtrasada1, 
			String vencimentoFatura1, String valorFatura1, String faturaAtrasada2, String vencimentoFatura2, String valorFatura2) {
		super();
		// TODO Auto-generated constructor stub
		this.faturaAtrasada1 = faturaAtrasada1;
		this.vencimentoFatura1 = vencimentoFatura1;
		this.valorFatura1 = valorFatura1;
		this.faturaAtrasada2 = faturaAtrasada2;
		this.vencimentoFatura2 = vencimentoFatura2;
		this.valorFatura2 = valorFatura2;
	}

	public String getFaturaAtrasada1() {
		return faturaAtrasada1;
	}

	public void setFaturaAtrasada1(String faturaAtrasada1) {
		this.faturaAtrasada1 = faturaAtrasada1;
	}

	public String getFaturaAtrasada2() {
		return faturaAtrasada2;
	}

	public void setFaturaAtrasada2(String faturaAtrasada2) {
		this.faturaAtrasada2 = faturaAtrasada2;
	}

	public String getValorFatura1() {
		return valorFatura1;
	}

	public void setValorFatura1(String valorFatura1) {
		this.valorFatura1 = valorFatura1;
	}

	public String getValorFatura2() {
		return valorFatura2;
	}

	public void setValorFatura2(String valorFatura2) {
		this.valorFatura2 = valorFatura2;
	}

	public String getVencimentoFatura1() {
		return vencimentoFatura1;
	}

	public void setVencimentoFatura1(String vencimentoFatura1) {
		this.vencimentoFatura1 = vencimentoFatura1;
	}

	public String getVencimentoFatura2() {
		return vencimentoFatura2;
	}

	public void setVencimentoFatura2(String vencimentoFatura2) {
		this.vencimentoFatura2 = vencimentoFatura2;
	}
	
	
	
	
}
