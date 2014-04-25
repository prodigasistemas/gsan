package gcom.arrecadacao.pagamento;

import gcom.arrecadacao.Arrecadador;

import java.io.Serializable;
import java.util.Date;

public class SequenciaCartao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer numeroNsa;
	
	private Date ultimaAlteracao;
	
	private Arrecadador arrecadador;
	
	private String codigoOpcaoExtrato;
	
	
	public SequenciaCartao(){}


	public Arrecadador getArrecadador() {
		return arrecadador;
	}


	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNumeroNsa() {
		return numeroNsa;
	}


	public void setNumeroNsa(Integer numeroNsa) {
		this.numeroNsa = numeroNsa;
	}


	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public String getCodigoOpcaoExtrato() {
		return codigoOpcaoExtrato;
	}


	public void setCodigoOpcaoExtrato(String codigoOpcaoExtrato) {
		this.codigoOpcaoExtrato = codigoOpcaoExtrato;
	}
	
	

}
