package gcom.arrecadacao.bean;

public class PesquisarAvisoBancarioPorContaCorrenteHelper {
	
	private Integer mesAno;
	private Integer idBanco;
	private Integer idContaBancaria;
	
	public PesquisarAvisoBancarioPorContaCorrenteHelper() { }

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public Integer getIdContaBancaria() {
		return idContaBancaria;
	}

	public void setIdContaBancaria(Integer idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}

	public Integer getMesAno() {
		return mesAno;
	}

	public void setMesAno(Integer mesAno) {
		this.mesAno = mesAno;
	}

}
