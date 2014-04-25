package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 
 * [UC0262] - Distribuir Dados do Registro de
 *          Movimento do Arrecadador
 */
public class RegistroHelperCodigoX implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoX() {
	}

	private String codigoRegistro;

	private String codigoAgencia;

	private String nomeAgencia;

	private String nomelogradouro;

	private String numero;

	private String codigoCep;

	private String sufixoCep;

	private String nomeCidade;

	private String siglaUnidadeFederacao;

	private String situacaoAgencia;

	private String reservadoFuturo;

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public String getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(String codigoCep) {
		this.codigoCep = codigoCep;
	}

	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getNomelogradouro() {
		return nomelogradouro;
	}

	public void setNomelogradouro(String nomelogradouro) {
		this.nomelogradouro = nomelogradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getReservadoFuturo() {
		return reservadoFuturo;
	}

	public void setReservadoFuturo(String reservadoFuturo) {
		this.reservadoFuturo = reservadoFuturo;
	}

	public String getSiglaUnidadeFederacao() {
		return siglaUnidadeFederacao;
	}

	public void setSiglaUnidadeFederacao(String siglaUnidadeFederacao) {
		this.siglaUnidadeFederacao = siglaUnidadeFederacao;
	}

	public String getSituacaoAgencia() {
		return situacaoAgencia;
	}

	public void setSituacaoAgencia(String situacaoAgencia) {
		this.situacaoAgencia = situacaoAgencia;
	}

	public String getSufixoCep() {
		return sufixoCep;
	}

	public void setSufixoCep(String sufixoCep) {
		this.sufixoCep = sufixoCep;
	}

}
