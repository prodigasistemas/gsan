package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

public class VersaoMobile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private byte[] arquivoJad;
	private byte[] arquivoJar;
	private String numeroVersao;
	private Date dataEnvio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getArquivoJad() {
		return arquivoJad;
	}
	public void setArquivoJad(byte[] arquivoJad) {
		this.arquivoJad = arquivoJad;
	}
	public byte[] getArquivoJar() {
		return arquivoJar;
	}
	public void setArquivoJar(byte[] arquivoJar) {
		this.arquivoJar = arquivoJar;
	}
	public String getNumeroVersao() {
		return numeroVersao;
	}
	public void setNumeroVersao(String numeroVersao) {
		this.numeroVersao = numeroVersao;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

}
