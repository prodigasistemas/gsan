package gcom.cadastro.endereco.bean;

import gcom.cadastro.endereco.Logradouro;

import java.io.Serializable;

public class ManterLogradouroHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logradouro logradouro;
	private String bairro;
	private int grauImportancia;

	public int getGrauImportancia() {
		return grauImportancia;
	}

	public void setGrauImportancia(int grauImportancia) {
		this.grauImportancia = grauImportancia;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}
