package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

import java.util.Date;

public class RelatorioDadosEconomiaImovelBean implements RelatorioBean {
	
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idSetorComercial;
	private String nomeSetorComercial;
	private String matricula;
	private String endereco;
	private String subcategoria;
	private String categoria;
	private String qtdeEconomias;
	private String nomeClienteUsuario;
	private String complementoEndereco;
	private String numeroPontos;
	private String numeroMoradores;
	private String numeroIptu;
	private String numeroCelpe;
	private String areaConstruida;
	private String nomeCliente;
	private String tipoRelacao;
	private String cpf;
	private Date dataInicioRelacao;
	private Date dataFimRelacao;
	private String motivoFimRelacao;
	private String inscricao;
	private String nomeUsuario;
	
	public RelatorioDadosEconomiaImovelBean (String idGerenciaRegional, String nomeGerenciaRegional, String idLocalidade,
			String nomeLocalidade, String idSetorComercial, String nomeSetorComercial, String matricula, String inscricao, String endereco,
			String subcategoria, String categoria, String qtdeEconomias, String nomeClienteUsuario, String complementoEndereco,
			String numeroPontos, String numeroMoradores, String numeroIptu, String numeroCelpe, String areaConstruida,
			String nomeCliente, String tipoRelacao, String cpf, Date dataInicioRelacao, Date dataFimRelacao, String motivoFimRelacao) {
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.nomeSetorComercial = nomeSetorComercial;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.endereco = endereco;
		this.subcategoria = subcategoria;
		this.categoria = categoria;
		this.qtdeEconomias = qtdeEconomias;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.complementoEndereco = complementoEndereco;
		this.numeroPontos = numeroPontos;
		this.numeroMoradores = numeroMoradores;
		this.numeroIptu = numeroIptu;
		this.numeroCelpe = numeroCelpe;
		this.areaConstruida = areaConstruida;
		this.nomeCliente = nomeCliente;
		this.tipoRelacao = tipoRelacao;
		this.cpf = cpf;
		this.dataInicioRelacao = dataInicioRelacao;
		this.dataFimRelacao = dataFimRelacao;
		this.motivoFimRelacao = motivoFimRelacao;
		
	}
	
	
	public String getAreaConstruida() {
		return areaConstruida;
	}
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}
	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}
	public Date getDataInicioRelacao() {
		return dataInicioRelacao;
	}
	public void setDataInicioRelacao(Date dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMotivoFimRelacao() {
		return motivoFimRelacao;
	}
	public void setMotivoFimRelacao(String motivoFimRelacao) {
		this.motivoFimRelacao = motivoFimRelacao;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getNumeroCelpe() {
		return numeroCelpe;
	}
	public void setNumeroCelpe(String numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}
	public String getNumeroIptu() {
		return numeroIptu;
	}
	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	public String getNumeroPontos() {
		return numeroPontos;
	}
	public void setNumeroPontos(String numeroPontos) {
		this.numeroPontos = numeroPontos;
	}
	public String getQtdeEconomias() {
		return qtdeEconomias;
	}
	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getTipoRelacao() {
		return tipoRelacao;
	}
	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}


	public String getInscricao() {
		return inscricao;
	}


	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	

}
