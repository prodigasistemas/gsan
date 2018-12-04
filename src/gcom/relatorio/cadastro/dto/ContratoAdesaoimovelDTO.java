package gcom.relatorio.cadastro.dto;

import gcom.util.Util;

import java.util.Date;

public class ContratoAdesaoimovelDTO {

	private String nomeRelatorio;
	private String nomeCliente;
	private String matricula;
	private String numeroContrato;
	private String nomeCidade;
	private String enderecoContratoAdesao;
	private String dataGeracao;
	
	public ContratoAdesaoimovelDTO() {}
	
	public ContratoAdesaoimovelDTO(String nomeRelatorio, String nomeCliente, String matricula, String nomeCidade, String endereco, String dataGeracao) {
		super();
		
		this.nomeRelatorio = nomeRelatorio;
		this.nomeCliente = nomeCliente;
		this.matricula = matricula;
		this.numeroContrato = getNumeroContrato();
		this.nomeCidade = nomeCidade;
		this.enderecoContratoAdesao = endereco;
		this.dataGeracao = dataGeracao;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getMatricula() {
		return matricula;
	}
	
	public String getNomeCidade() {
		return nomeCidade;
	}

	public String getEnderecoContratoAdesao() {
		return enderecoContratoAdesao;
	}
	
	public String getDataGeracao() {
		return dataGeracao;
	}

	public String getNumeroContrato() {
		this.numeroContrato = matricula + Util.formatarDataAAAAMMDD(new Date()); 
		return numeroContrato;
	}
	
	
}
