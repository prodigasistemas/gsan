package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Ana Maria
 * @date 06/10/2006
 */
public class RelatorioGuiaDevolucaoBean implements RelatorioBean {

	private String sequencialAno;

	private String valor;

	private String registroServicoAtendimento;

	private String matriculaImovel;

	private String cliente;
	
	private String nomeCliente;
	
	private String cpfCnpj;

	private String endereco;

	private String identidade;

	private String valorExtenso;
	
	private String observacao;

	private String dataDigitacao;
	
	private String dataAnalise;
	
	private String dataAutorizacao;
	
	private String nomeUsuario;
	
	private String nomeAnalista;
	
	private String nomeAutorizador;
	
	private String via;
	
	private String contaDebito;

	/**
	 * @return Retorna o campo contaDebito.
	 */
	public String getContaDebito() {
		return contaDebito;
	}

	/**
	 * @param contaDebito O contaDebito a ser setado.
	 */
	public void setContaDebito(String contaDebito) {
		this.contaDebito = contaDebito;
	}

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioGuiaDevolucaoBean(String sequencialAno, String valor,
			String registroServicoAtendimento, String matriculaImovel, String cliente,
			String nomeCliente, String cpfCnpj, 
			String endereco, String identidade, String valorExtenso,
			String observacao, String dataDigitacao, String dataAnalise,
			String dataAutorizacao, String nomeUsuario, String nomeAnalista,
			String nomeAutorizador, String via, String contaDebito) {
		this.sequencialAno = sequencialAno;
		this.valor = valor;
		this.registroServicoAtendimento = registroServicoAtendimento;
		this.matriculaImovel = matriculaImovel;
		this.cliente = cliente;
		this.nomeCliente = nomeCliente;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.identidade = identidade;
		this.valorExtenso = valorExtenso;
		this.observacao = observacao;
		this.dataDigitacao = dataDigitacao;
		this.dataAnalise = dataAnalise;
		this.dataAutorizacao = dataAutorizacao;
		this.nomeUsuario = nomeUsuario;
		this.nomeAnalista = nomeAnalista;
		this.nomeAutorizador = nomeAutorizador;
		this.via = via;
		this.contaDebito = contaDebito;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(String dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	public String getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(String dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getDataDigitacao() {
		return dataDigitacao;
	}

	public void setDataDigitacao(String dataDigitacao) {
		this.dataDigitacao = dataDigitacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeAnalista() {
		return nomeAnalista;
	}

	public void setNomeAnalista(String nomeAnalista) {
		this.nomeAnalista = nomeAnalista;
	}

	public String getNomeAutorizador() {
		return nomeAutorizador;
	}

	public void setNomeAutorizador(String nomeAutorizador) {
		this.nomeAutorizador = nomeAutorizador;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getRegistroServicoAtendimento() {
		return registroServicoAtendimento;
	}

	public void setRegistroServicoAtendimento(String registroServicoAtendimento) {
		this.registroServicoAtendimento = registroServicoAtendimento;
	}

	public String getSequencialAno() {
		return sequencialAno;
	}

	public void setSequencialAno(String sequencialAno) {
		this.sequencialAno = sequencialAno;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorExtenso() {
		return valorExtenso;
	}

	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


}
