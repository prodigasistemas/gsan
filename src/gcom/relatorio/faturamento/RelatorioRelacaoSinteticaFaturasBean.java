package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Pinto
 * @created 19/11/2007
 */
public class RelatorioRelacaoSinteticaFaturasBean implements RelatorioBean {
		
	private String codigoCliente;
	private String nomeCliente;
	
	private String totalOrgaoPagador;
	
	public RelatorioRelacaoSinteticaFaturasBean(String codigoCliente, 
			String nomeCliente, 
			String totalOrgaoPagador) {
		
		this.codigoCliente = codigoCliente;
		this.nomeCliente = nomeCliente;
		this.totalOrgaoPagador = totalOrgaoPagador;
	}
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getTotalOrgaoPagador() {
		return totalOrgaoPagador;
	}
	
	public void setTotalOrgaoPagador(String totalOrgaoPagador) {
		this.totalOrgaoPagador = totalOrgaoPagador;
	}
	
}
