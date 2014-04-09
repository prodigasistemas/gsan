package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @date 26/04/2006
 */
public class RelatorioMovimentoArrecadadorBean implements RelatorioBean {

	private String idBanco;

	private String nomeBanco;
	
	private String nsa;

	private String qtdRegistros;

	private String valorMovimento;

	private String tipoMovimento;
	
	private String horaProcessamento;

	
	public RelatorioMovimentoArrecadadorBean(String idBanco, String nomeBanco,String nsa,
			String qtdRegistros, String valorMovimento, String tipoMovimento,String horaProcessamento) {
		this.idBanco = idBanco;
		this.nomeBanco = nomeBanco;
		this.nsa = nsa;
		this.qtdRegistros = qtdRegistros;
		this.valorMovimento = valorMovimento;
		this.tipoMovimento = tipoMovimento;
		this.horaProcessamento = horaProcessamento;

	}





	public String getTipoMovimento() {
		return tipoMovimento;
	}





	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}





	public String getIdBanco() {
		return idBanco;
	}


	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}


	public String getNomeBanco() {
		return nomeBanco;
	}


	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}


	public String getQtdRegistros() {
		return qtdRegistros;
	}


	public void setQtdRegistros(String qtdRegistros) {
		this.qtdRegistros = qtdRegistros;
	}



	


	public String getHoraProcessamento() {
		return horaProcessamento;
	}





	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}





	public String getValorMovimento() {
		return valorMovimento;
	}


	public void setValorMovimento(String valorMovimento) {
		this.valorMovimento = valorMovimento;
	}


	public String getNsa() {
		return nsa;
	}


	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	

	
}
