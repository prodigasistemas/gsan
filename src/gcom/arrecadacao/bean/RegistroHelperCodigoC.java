package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 
 * [UC0262] - Distribuir Dados do Registro de
 *          Movimento do Arrecadador
 */
public class RegistroHelperCodigoC implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoC() {
	}

	private String codigoRegistro;

	private String idClienteEmpresa;

	private String agenciaDebito;

	private String idClienteBanco;

	private String descricaoOcorrenciaMovimento;

	private String brancos;

	private String reservadoFuturo;

	private String codigoMovimento;

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

	public String getBrancos() {
		return brancos;
	}

	public void setBrancos(String brancos) {
		this.brancos = brancos;
	}

	public String getDescricaoOcorrenciaMovimento() {
		return descricaoOcorrenciaMovimento;
	}

	public void setDescricaoOcorrenciaMovimento(
			String descricaoOcorrenciaMovimento) {
		this.descricaoOcorrenciaMovimento = descricaoOcorrenciaMovimento;
	}

}
