package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
 * 
 * @author Victor Cisneiros
 * @date 21/08/2008
 */
public class GerarRelatorioAvisoBancarioPorContaCorrenteActionForm  extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	private String idBanco;
	
	private String idContaBancaria;
	private String idBancoDaConta;
	private String codigoAgencia;
	private String numeroConta;
	
	public String getCodigoAgencia() {
		return codigoAgencia;
	}
	
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	
	public String getIdBanco() {
		return idBanco;
	}
	
	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}
	
	public String getIdContaBancaria() {
		return idContaBancaria;
	}
	
	public void setIdContaBancaria(String idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getIdBancoDaConta() {
		return idBancoDaConta;
	}

	public void setIdBancoDaConta(String idBancoDaConta) {
		this.idBancoDaConta = idBancoDaConta;
	}

}
