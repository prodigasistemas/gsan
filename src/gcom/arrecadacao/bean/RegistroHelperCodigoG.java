package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 [UC0262] - Distribuir Dados do Registro de
 *          Movimento do Arrecadador
 */
public class RegistroHelperCodigoG implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoG() {
	}

	private String codigoRegistro;

	private String idAgenciaContaDigito;

	private String dataPagamento;

	private String dataPrevistaCredito;

	private RegistroHelperCodigoBarras registroHelperCodigoBarras;

	private String codigoBarras;

	private String valorRecebido;

	private String valorTarifa;

	private String numeroSeqRegistro;

	private String codigoAgenciaArrecadadora;

	private String formaArrecadacao;
	
	private String codigoFormaArrecadacao;

	private String numeroAutenticacao;

	private String formaPagamento;

	private String reservadoFuturo;

	public String getCodigoAgenciaArrecadadora() {
		return codigoAgenciaArrecadadora;
	}

	public void setCodigoAgenciaArrecadadora(String codigoAgenciaArrecadadora) {
		this.codigoAgenciaArrecadadora = codigoAgenciaArrecadadora;
	}

	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDataPrevistaCredito() {
		return dataPrevistaCredito;
	}

	public void setDataPrevistaCredito(String dataPrevistaCredito) {
		this.dataPrevistaCredito = dataPrevistaCredito;
	}

	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getIdAgenciaContaDigito() {
		return idAgenciaContaDigito;
	}

	public void setIdAgenciaContaDigito(String idAgenciaContaDigito) {
		this.idAgenciaContaDigito = idAgenciaContaDigito;
	}

	public String getNumeroAutenticacao() {
		return numeroAutenticacao;
	}

	public void setNumeroAutenticacao(String numeroAutenticacao) {
		this.numeroAutenticacao = numeroAutenticacao;
	}

	public String getNumeroSeqRegistro() {
		return numeroSeqRegistro;
	}

	public void setNumeroSeqRegistro(String numeroSeqRegistro) {
		this.numeroSeqRegistro = numeroSeqRegistro;
	}

	public String getReservadoFuturo() {
		return reservadoFuturo;
	}

	public void setReservadoFuturo(String reservadoFuturo) {
		this.reservadoFuturo = reservadoFuturo;
	}

	public String getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(String valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public String getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public RegistroHelperCodigoBarras getRegistroHelperCodigoBarras() {
		return registroHelperCodigoBarras;
	}

	public void setRegistroHelperCodigoBarras(
			RegistroHelperCodigoBarras registroHelperCodigoBarras) {
		this.registroHelperCodigoBarras = registroHelperCodigoBarras;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoFormaArrecadacao() {
		return codigoFormaArrecadacao;
	}

	public void setCodigoFormaArrecadacao(String codigoFormaArrecadacao) {
		this.codigoFormaArrecadacao = codigoFormaArrecadacao;
	}
	
	

}
