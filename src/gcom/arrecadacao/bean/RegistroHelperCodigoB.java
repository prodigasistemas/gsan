package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 
 * [UC0262] - Distribuir Dados do Registro de
 *          Movimento do Arrecadador
 */
public class RegistroHelperCodigoB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoB() {
	}

	private String codigoRegistro;

	private String idClienteEmpresa;

	private String agenciaDebito;

	private String idClienteBanco;

	private String dataOpcaoExclusao;

	private String reservadoFuturo;

	private String codigoMovimento;
	
	public static final String EXCLUSAO_DEBITO_AUTOMATICO = "1";
	
	public static final String INCLUSAO_DEBITO_AUTOMATICO = "2";
	
	public String getAgenciaDebito() {
		return agenciaDebito;
	}

	public void setAgenciaDebito(String agenciaDebito) {
		this.agenciaDebito = agenciaDebito;
	}

	public String getCodigoMovimento() {
		return codigoMovimento;
	}

	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}

	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getDataOpcaoExclusao() {
		return dataOpcaoExclusao;
	}

	public void setDataOpcaoExclusao(String dataOpcaoExclusao) {
		this.dataOpcaoExclusao = dataOpcaoExclusao;
	}

	public String getIdClienteBanco() {
		return idClienteBanco;
	}

	public void setIdClienteBanco(String idClienteBanco) {
		this.idClienteBanco = idClienteBanco;
	}

	public String getIdClienteEmpresa() {
		return idClienteEmpresa;
	}

	public void setIdClienteEmpresa(String idClienteEmpresa) {
		this.idClienteEmpresa = idClienteEmpresa;
	}

	public String getReservadoFuturo() {
		return reservadoFuturo;
	}

	public void setReservadoFuturo(String reservadoFuturo) {
		this.reservadoFuturo = reservadoFuturo;
	}

}
