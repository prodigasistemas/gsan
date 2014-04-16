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

public class RelatorioManterDebitoAutomaticoBean implements RelatorioBean {
	
	private String matricula;
	
	private String nomeCliente;

	private String codigoAgencia;

	private String nomeBanco;

	public RelatorioManterDebitoAutomaticoBean(String matricula, String banco, String codigoAgencia,String nomeCliente) {
		
		this.matricula = matricula;
		this.nomeBanco = banco;
		this.codigoAgencia = codigoAgencia;
		this.nomeCliente = nomeCliente;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String id) {
		this.matricula = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String codigo) {
		this.nomeCliente = codigo;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codAgencia) {
		this.codigoAgencia = codAgencia;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
}
