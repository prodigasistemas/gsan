package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1141] Emitir Contrato de Parcelamento por Cliente
 * 
 * Classe responsável por emitir o relatório com os dados de um Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 *
 * @date 28/04/2011
 */
public class RelatorioEmitirContratoParcelamentoPorClienteSubCliBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String idClienteVinculado;
	
	private String nomeClienteVinculado;
	
	private String cnpjClienteVinculado;
	
	
	public RelatorioEmitirContratoParcelamentoPorClienteSubCliBean() {
		super();
	}

	public RelatorioEmitirContratoParcelamentoPorClienteSubCliBean(String idClienteVinculado, String nomeClienteVinculado, String cnpjClienteVinculado) {
		super();
		this.idClienteVinculado = idClienteVinculado;
		this.nomeClienteVinculado = nomeClienteVinculado;
		this.cnpjClienteVinculado = cnpjClienteVinculado;
	}

	public String getCnpjClienteVinculado() {
		return cnpjClienteVinculado;
	}

	public void setCnpjClienteVinculado(String cnpjClienteVinculado) {
		this.cnpjClienteVinculado = cnpjClienteVinculado;
	}

	public String getIdClienteVinculado() {
		return idClienteVinculado;
	}

	public void setIdClienteVinculado(String idClienteVinculado) {
		this.idClienteVinculado = idClienteVinculado;
	}

	public String getNomeClienteVinculado() {
		return nomeClienteVinculado;
	}

	public void setNomeClienteVinculado(String nomeClienteVinculado) {
		this.nomeClienteVinculado = nomeClienteVinculado;
	}
	
}
