package gcom.relatorio.seguranca;

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
 * @author Rodrigo Cabral
 * @version 1.0
 */

public class RelatorioResultadoPesquisaConsultaCadastroCdlBean implements RelatorioBean {
	
	private String periodoAcessoInicial;
	
	private String periodoAcessoFinal;
	
	private String loginUsuario;
	
	private String codigoCliente;
	
	private String cpfCliente; 
	
	private String cnpjCliente;
	
	private String nomeCliente;
	
	private String acaoOperador;
	
	private String logradouroCliente;
	
	private String numeroImovelCliente;
	
	private String bairroCliente;
	
	private String complementoEnderecoCliente;
	
	private String cidadeCliente;
	
	private String uf;
	
	private String telefone;

	public RelatorioResultadoPesquisaConsultaCadastroCdlBean(
			String loginUsuario, String codigoCliente, String cpfCliente,
			String cnpjCliente, String nomeCliente, String acaoOperador,
			String logradouroCliente, String numeroImovelCliente,
			String bairroCliente, String complementoEnderecoCliente,
			String cidadeCliente, String uf, String telefone) {
		super();
		this.loginUsuario = loginUsuario;
		this.codigoCliente = codigoCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
		this.nomeCliente = nomeCliente;
		this.acaoOperador = acaoOperador;
		this.logradouroCliente = logradouroCliente;
		this.numeroImovelCliente = numeroImovelCliente;
		this.bairroCliente = bairroCliente;
		this.complementoEnderecoCliente = complementoEnderecoCliente;
		this.cidadeCliente = cidadeCliente;
		this.uf = uf;
		this.telefone = telefone;
	}
	
	
	public RelatorioResultadoPesquisaConsultaCadastroCdlBean(
			String periodoAcessoInicial, String periodoAcessoFinal,
			String loginUsuario, String codigoCliente, String cpfCliente,
			String cnpjCliente, String nomeCliente, String acaoOperador,
			String logradouroCliente, String numeroImovelCliente,
			String bairroCliente, String complementoEnderecoCliente,
			String cidadeCliente, String uf, String telefone) {
		super();
		this.periodoAcessoInicial = periodoAcessoInicial;
		this.periodoAcessoFinal = periodoAcessoFinal;
		this.loginUsuario = loginUsuario;
		this.codigoCliente = codigoCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
		this.nomeCliente = nomeCliente;
		this.acaoOperador = acaoOperador;
		this.logradouroCliente = logradouroCliente;
		this.numeroImovelCliente = numeroImovelCliente;
		this.bairroCliente = bairroCliente;
		this.complementoEnderecoCliente = complementoEnderecoCliente;
		this.cidadeCliente = cidadeCliente;
		this.uf = uf;
		this.telefone = telefone;
	}

	public String getPeriodoAcessoInicial() {
		return periodoAcessoInicial;
	}

	public void setPeriodoAcessoInicial(String periodoAcessoInicial) {
		this.periodoAcessoInicial = periodoAcessoInicial;
	}

	public String getPeriodoAcessoFinal() {
		return periodoAcessoFinal;
	}

	public void setPeriodoAcessoFinal(String periodoAcessoFinal) {
		this.periodoAcessoFinal = periodoAcessoFinal;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getAcaoOperador() {
		return acaoOperador;
	}

	public void setAcaoOperador(String acaoOperador) {
		this.acaoOperador = acaoOperador;
	}

	public String getLogradouroCliente() {
		return logradouroCliente;
	}

	public void setLogradouroCliente(String logradouroCliente) {
		this.logradouroCliente = logradouroCliente;
	}

	public String getNumeroImovelCliente() {
		return numeroImovelCliente;
	}

	public void setNumeroImovelCliente(String numeroImovelCliente) {
		this.numeroImovelCliente = numeroImovelCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getComplementoEnderecoCliente() {
		return complementoEnderecoCliente;
	}

	public void setComplementoEnderecoCliente(String complementoEnderecoCliente) {
		this.complementoEnderecoCliente = complementoEnderecoCliente;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	


}
