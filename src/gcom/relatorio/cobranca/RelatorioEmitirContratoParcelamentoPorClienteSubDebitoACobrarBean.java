package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1141] Emitir Contrato de Parcelamento por Cliente
 * 
 * Classe responsável por emitir o relatório com os dados de um Contrato de Parcelamento por Cliente
 * 
 * @author Paulo DIniz
 *
 * @date 28/06/2011
 */
public class RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean
		implements RelatorioBean {

	private static final long serialVersionUID = 1L;
	
	private String matricula;
	
	private String tipoDebito;
	
	private String mesAnoReferencia;
	
	private String mesAnoCobranca;
	
	private String parcelasACobrar;
	
	private String valorACobrar;
	
	public RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean() {
		super();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getMesAnoCobranca() {
		return mesAnoCobranca;
	}

	public void setMesAnoCobranca(String mesAnoCobranca) {
		this.mesAnoCobranca = mesAnoCobranca;
	}

	public String getParcelasACobrar() {
		return parcelasACobrar;
	}

	public void setParcelasACobrar(String parcelasACobrar) {
		this.parcelasACobrar = parcelasACobrar;
	}

	public String getValorACobrar() {
		return valorACobrar;
	}

	public void setValorACobrar(String valorACobrar) {
		this.valorACobrar = valorACobrar;
	}

}
