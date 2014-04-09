package gcom.arrecadacao.aviso.bean;


public class AcertosAvisoBancarioHelper {
	
	private String idbanco;
	private String codigoAgencia;
	private String numeroConta;
	private String indicadorCreditoDebito;
	private String dataAcerto; 
	private String valorAcerto;
	
	public String getCodigoAgencia() {
		return codigoAgencia;
	}
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	public String getDataAcerto() {
		return dataAcerto;
	}
	public void setDataAcerto(String dataAcerto) {
		this.dataAcerto = dataAcerto;
	}
	public String getIdbanco() {
		return idbanco;
	}
	public void setIdbanco(String idbanco) {
		this.idbanco = idbanco;
	}
	public String getIndicadorCreditoDebito() {
		return indicadorCreditoDebito;
	}
	public void setIndicadorCreditoDebito(String indicadorCreditoDebito) {
		this.indicadorCreditoDebito = indicadorCreditoDebito;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getValorAcerto() {
		return valorAcerto;
	}
	public void setValorAcerto(String valorAcerto) {
		this.valorAcerto = valorAcerto;
	}
	
}
