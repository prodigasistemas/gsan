package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

public class RelatorioAnalisePerdasCreditosBean implements RelatorioBean {

	
	private String primeiraFaixaVencidosSuperiorSeisMeses;
	private String primeiraFaixaVencidosAteSeisMeses;	
	private String primeiraFaixaTotal;	
	private String primeiraFaixaVencer;
	private String primeiraFaixaTexto;
	private String segundaFaixaVencidosAteDoseMeses;	
	private String segundaFaixaVencidosSuperiorDoseMeses;	
	private String segundaFaixaVencer;	
	private String segundaFaixaTotal;
	private String segundaFaixaTexto;
	private String terceiraFaixaVencidosAteDoseMeses;	
	private String terceiraFaixaVencidosSuperiorDoseMeses;	
	private String terceiraFaixaVencer;	
	private String terceiraFaixaTotal;
	private String terceiraFaixaTexto;
	private String mesAnoReferencia;
	
	
	public String getPrimeiraFaixaTexto() {
		return primeiraFaixaTexto;
	}


	public void setPrimeiraFaixaTexto(String primeiraFaixaTexto) {
		this.primeiraFaixaTexto = primeiraFaixaTexto;
	}


	public String getSegundaFaixaTexto() {
		return segundaFaixaTexto;
	}


	public void setSegundaFaixaTexto(String segundaFaixaTexto) {
		this.segundaFaixaTexto = segundaFaixaTexto;
	}


	public String getTerceiraFaixaTexto() {
		return terceiraFaixaTexto;
	}


	public void setTerceiraFaixaTexto(String terceiraFaixaTexto) {
		this.terceiraFaixaTexto = terceiraFaixaTexto;
	}


	public RelatorioAnalisePerdasCreditosBean() {
		super();
	}


	public String getPrimeiraFaixaTotal() {
		return primeiraFaixaTotal;
	}


	public void setPrimeiraFaixaTotal(String ateCincoMilTotal) {
		this.primeiraFaixaTotal = ateCincoMilTotal;
	}


	public String getPrimeiraFaixaVencer() {
		return primeiraFaixaVencer;
	}


	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}


	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}


	public void setPrimeiraFaixaVencer(String ateCincoMilVencer) {
		this.primeiraFaixaVencer = ateCincoMilVencer;
	}


	public String getPrimeiraFaixaVencidosAteSeisMeses() {
		return primeiraFaixaVencidosAteSeisMeses;
	}


	public void setPrimeiraFaixaVencidosAteSeisMeses(
			String ateCincoMilVencidosAteSeisMeses) {
		this.primeiraFaixaVencidosAteSeisMeses = ateCincoMilVencidosAteSeisMeses;
	}


	public String getPrimeiraFaixaVencidosSuperiorSeisMeses() {
		return primeiraFaixaVencidosSuperiorSeisMeses;
	}


	public void setPrimeiraFaixaVencidosSuperiorSeisMeses(
			String ateCincoMilVencidosSuperiorSeisMeses) {
		this.primeiraFaixaVencidosSuperiorSeisMeses = ateCincoMilVencidosSuperiorSeisMeses;
	}


	public String getSegundaFaixaTotal() {
		return segundaFaixaTotal;
	}


	public void setSegundaFaixaTotal(String ateTrintaMilTotal) {
		this.segundaFaixaTotal = ateTrintaMilTotal;
	}


	public String getSegundaFaixaVencer() {
		return segundaFaixaVencer;
	}


	public void setSegundaFaixaVencer(String ateTrintaMilVencer) {
		this.segundaFaixaVencer = ateTrintaMilVencer;
	}


	public String getSegundaFaixaVencidosAteDoseMeses() {
		return segundaFaixaVencidosAteDoseMeses;
	}


	public void setSegundaFaixaVencidosAteDoseMeses(
			String ateTrintaMilVencidosAteDoseMeses) {
		this.segundaFaixaVencidosAteDoseMeses = ateTrintaMilVencidosAteDoseMeses;
	}


	public String getSegundaFaixaVencidosSuperiorDoseMeses() {
		return segundaFaixaVencidosSuperiorDoseMeses;
	}


	public void setSegundaFaixaVencidosSuperiorDoseMeses(
			String ateTrintaMilVencidosSuperiorDoseMeses) {
		this.segundaFaixaVencidosSuperiorDoseMeses = ateTrintaMilVencidosSuperiorDoseMeses;
	}


	public String getTerceiraFaixaTotal() {
		return terceiraFaixaTotal;
	}


	public void setTerceiraFaixaTotal(String superiorTrintaMilTotal) {
		this.terceiraFaixaTotal = superiorTrintaMilTotal;
	}


	public String getTerceiraFaixaVencer() {
		return terceiraFaixaVencer;
	}


	public void setTerceiraFaixaVencer(String superiorTrintaMilVencer) {
		this.terceiraFaixaVencer = superiorTrintaMilVencer;
	}


	public String getTerceiraFaixaVencidosAteDoseMeses() {
		return terceiraFaixaVencidosAteDoseMeses;
	}


	public void setTerceiraFaixaVencidosAteDoseMeses(
			String superiorTrintaMilVencidosAteDoseMeses) {
		this.terceiraFaixaVencidosAteDoseMeses = superiorTrintaMilVencidosAteDoseMeses;
	}


	public String getTerceiraFaixaVencidosSuperiorDoseMeses() {
		return terceiraFaixaVencidosSuperiorDoseMeses;
	}


	public void setTerceiraFaixaVencidosSuperiorDoseMeses(
			String superiorTrintaMilVencidosSuperiorDoseMeses) {
		this.terceiraFaixaVencidosSuperiorDoseMeses = superiorTrintaMilVencidosSuperiorDoseMeses;
	}
}
