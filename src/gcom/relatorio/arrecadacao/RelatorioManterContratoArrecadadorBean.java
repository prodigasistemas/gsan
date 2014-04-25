package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterContratoArrecadadorBean implements RelatorioBean {
//	Nº Contrato
//	
//	Arrecadador
//		
//	Cliente
//		
//	Nome do Cliente
	
	private String numeroContrato;
	
	private String nomeCliente;

	private String idArrecadador;

	private String idCliente;

	public RelatorioManterContratoArrecadadorBean(String numeroContrato, String idArrecadador,String idCliente,String nomeCliente) {
		
		this.numeroContrato = numeroContrato;
		this.idCliente = idCliente;
		this.idArrecadador = idArrecadador;
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String id) {
		this.numeroContrato = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String codigo) {
		this.nomeCliente = codigo;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String codAgencia) {
		this.idArrecadador = codAgencia;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String nomeBanco) {
		this.idCliente = nomeBanco;
	}
}
