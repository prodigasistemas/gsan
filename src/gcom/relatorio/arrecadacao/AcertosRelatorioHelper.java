package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Date;

public class AcertosRelatorioHelper {
	
	private Integer numeroDocumento;
	private String banco;
	private Integer agencia;
	private String numeroConta;
	private Date dataAcerto;
	private BigDecimal valorAcerto;
	private Short tipo;
	
	public Integer getAgencia() {
		return agencia;
	}
	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public Date getDataAcerto() {
		return dataAcerto;
	}
	public void setDataAcerto(Date dataAcerto) {
		this.dataAcerto = dataAcerto;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Short getTipo() {
		return tipo;
	}
	public void setTipo(Short tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getValorAcerto() {
		return valorAcerto;
	}
	public void setValorAcerto(BigDecimal valorAcerto) {
		this.valorAcerto = valorAcerto;
	}

}
