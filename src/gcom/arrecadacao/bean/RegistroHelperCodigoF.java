package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 
 * [UC0262] - Distribuir Dados do Registro de
 *          Movimento do Arrecadador
 */
public class RegistroHelperCodigoF implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoF() {
	}

	private String codigoRegistro;

	private String idClienteEmpresa;

	private String agenciaDebito;

	private String idClienteBanco;

	private String dataDebito;

	private String valorDebito;

	private String codigoRetorno;

	private String anoMesReferenciaConta;

	private String digitoVerificadoAnoMesConta;

	private String gruposFaturamento;

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

	public String getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}

	public void setAnoMesReferenciaConta(String anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getDataDebito() {
		return dataDebito;
	}

	public void setDataDebito(String dataDebito) {
		this.dataDebito = dataDebito;
	}

	public String getDigitoVerificadoAnoMesConta() {
		return digitoVerificadoAnoMesConta;
	}

	public void setDigitoVerificadoAnoMesConta(
			String digitoVerificadoAnoMesConta) {
		this.digitoVerificadoAnoMesConta = digitoVerificadoAnoMesConta;
	}

	public String getGruposFaturamento() {
		return gruposFaturamento;
	}

	public void setGruposFaturamento(String gruposFaturamento) {
		this.gruposFaturamento = gruposFaturamento;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

}
