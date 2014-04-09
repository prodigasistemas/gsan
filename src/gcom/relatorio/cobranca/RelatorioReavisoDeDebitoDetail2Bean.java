package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 26/07/2007
 */
public class RelatorioReavisoDeDebitoDetail2Bean implements RelatorioBean {
	
	private String mesAno;
	private String origem;
	private String vencimento;
	private String valor;
	
	public RelatorioReavisoDeDebitoDetail2Bean(
			String mesAno,
    		String origem,
    		String vencimento,
    		String valor) {
    	
    	this.mesAno = mesAno;
    	this.origem = origem;
    	this.vencimento = vencimento;
    	this.valor = valor;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

}
