package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @date 26/04/2006
 */
public class RelatorioMovimentoDebitoAutomaticoBancoBean implements RelatorioBean {

	private String idBanco;

	private String nomeBanco;
	
	private String nsa;

	private String qtdRegistros;

	private String valorDebitar;

	private String descricaoEmail;
	
	private String situacaoEmail;

	
	public RelatorioMovimentoDebitoAutomaticoBancoBean(String idBanco, String nomeBanco,String nsa,
			String qtdRegistros, String valorDebitar, String descricaoEmail,String situacaoEmail) {
		this.idBanco = idBanco;
		this.nomeBanco = nomeBanco;
		this.nsa = nsa;
		this.qtdRegistros = qtdRegistros;
		this.valorDebitar = valorDebitar;
		this.descricaoEmail = descricaoEmail;
		this.situacaoEmail = situacaoEmail;

	}


	public String getDescricaoEmail() {
		return descricaoEmail;
	}


	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
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


	public String getSituacaoEmail() {
		return situacaoEmail;
	}


	public void setSituacaoEmail(String situacaoEmail) {
		this.situacaoEmail = situacaoEmail;
	}


	public String getValorDebitar() {
		return valorDebitar;
	}


	public void setValorDebitar(String valorDebitar) {
		this.valorDebitar = valorDebitar;
	}


	public String getNsa() {
		return nsa;
	}


	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	

	
}
