package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 
 * [UC0262] - Distribuir Dados do Registro de
 *          Movimento do Arrecadador
 */
public class RegistroHelperCodigoZ implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoZ() {
	}

	private String codigoRegistro;

	private String totalRegistrosArquivo;

	private String valorTotalRegistrosArquivo;

	private String reservadoFuturo;

	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getReservadoFuturo() {
		return reservadoFuturo;
	}

	public void setReservadoFuturo(String reservadoFuturo) {
		this.reservadoFuturo = reservadoFuturo;
	}

	public String getTotalRegistrosArquivo() {
		return totalRegistrosArquivo;
	}

	public void setTotalRegistrosArquivo(String totalRegistrosArquivo) {
		this.totalRegistrosArquivo = totalRegistrosArquivo;
	}

	public String getValorTotalRegistrosArquivo() {
		return valorTotalRegistrosArquivo;
	}

	public void setValorTotalRegistrosArquivo(String valorTotalRegistrosArquivo) {
		this.valorTotalRegistrosArquivo = valorTotalRegistrosArquivo;
	}

}
