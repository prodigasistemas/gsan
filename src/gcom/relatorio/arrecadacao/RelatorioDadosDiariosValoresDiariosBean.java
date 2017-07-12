package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação - Valores Diários
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class RelatorioDadosDiariosValoresDiariosBean implements RelatorioBean {

	private String processamentoDefinitivo;
	
	private String mesAno;
	
	private String ultimoProcessamentoAtual;
	
	private String faturamentoCobradoEmConta;
	
	private String nomeGerencia;
	
	private String nomeUnidadeNegocio;
	
	private String nomeLocalidadePolo;
	
	private String nomeLocalidade;
	
	private String nomeAgente;
	
	private String nomeCategoria;
	
	private String nomePerfil;
	
	private String nomeDocumento;
	
	private String nomeArrecadacaoForma;
	
	private String valor;
	
	private String data;
	
	private String quantDoc;
	
	private String quantPag;
	
	private String debitos;
	
	private String descontos;
	
	private String valorArrecadado;
	
	private String devolucao;
	
	private String arrecadacaoLiquida;
	
	private String percentual;
	
	private String valorAteDia;
	
	private String percentualDoDia;
	
	private String arrecadador;
	
	public RelatorioDadosDiariosValoresDiariosBean() {
		super();
	}

	public RelatorioDadosDiariosValoresDiariosBean(String processamentoDefinitivo, String mesAno, String ultimoProcessamentoAtual, String faturamentoCobradoEmConta, String nomeGerencia, String nomeUnidadeNegocio, String nomeLocalidadePolo, String nomeLocalidade, String nomeAgente, String nomeCategoria, String nomePerfil, String nomeDocumento, String nomeArrecadacaoForma, String valor, String data, String quantDoc, String quantPag, String debitos, String descontos, String valorArrecadado, String devolucao, String arrecadacaoLiquida, String percentual, String valorAteDia, String percentualDoDia) {
		super();
		this.processamentoDefinitivo = processamentoDefinitivo;
		this.mesAno = mesAno;
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
		this.nomeGerencia = nomeGerencia;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.nomeLocalidadePolo = nomeLocalidadePolo;
		this.nomeLocalidade = nomeLocalidade;
		this.nomeAgente = nomeAgente;
		this.nomeCategoria = nomeCategoria;
		this.nomePerfil = nomePerfil;
		this.nomeDocumento = nomeDocumento;
		this.nomeArrecadacaoForma = nomeArrecadacaoForma;
		this.valor = valor;
		this.data = data;
		this.quantDoc = quantDoc;
		this.quantPag = quantPag;
		this.debitos = debitos;
		this.descontos = descontos;
		this.valorArrecadado = valorArrecadado;
		this.devolucao = devolucao;
		this.arrecadacaoLiquida = arrecadacaoLiquida;
		this.percentual = percentual;
		this.valorAteDia = valorAteDia;
		this.percentualDoDia = percentualDoDia;
	}

	public String getArrecadacaoLiquida() {
		return arrecadacaoLiquida;
	}

	public void setArrecadacaoLiquida(String arrecadacaoLiquida) {
		this.arrecadacaoLiquida = arrecadacaoLiquida;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDebitos() {
		return debitos;
	}

	public void setDebitos(String debitos) {
		this.debitos = debitos;
	}

	public String getDescontos() {
		return descontos;
	}

	public void setDescontos(String descontos) {
		this.descontos = descontos;
	}

	public String getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(String devolucao) {
		this.devolucao = devolucao;
	}

	public String getFaturamentoCobradoEmConta() {
		return faturamentoCobradoEmConta;
	}

	public void setFaturamentoCobradoEmConta(String faturamentoCobradoEmConta) {
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}

	public String getNomeArrecadacaoForma() {
		return nomeArrecadacaoForma;
	}

	public void setNomeArrecadacaoForma(String nomeArrecadacaoForma) {
		this.nomeArrecadacaoForma = nomeArrecadacaoForma;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeLocalidadePolo() {
		return nomeLocalidadePolo;
	}

	public void setNomeLocalidadePolo(String nomeLocalidadePolo) {
		this.nomeLocalidadePolo = nomeLocalidadePolo;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	public String getPercentualDoDia() {
		return percentualDoDia;
	}

	public void setPercentualDoDia(String percentualDoDia) {
		this.percentualDoDia = percentualDoDia;
	}

	public String getProcessamentoDefinitivo() {
		return processamentoDefinitivo;
	}

	public void setProcessamentoDefinitivo(String processamentoDefinitivo) {
		this.processamentoDefinitivo = processamentoDefinitivo;
	}

	public String getQuantDoc() {
		return quantDoc;
	}

	public void setQuantDoc(String quantDoc) {
		this.quantDoc = quantDoc;
	}

	public String getQuantPag() {
		return quantPag;
	}

	public void setQuantPag(String quantPag) {
		this.quantPag = quantPag;
	}

	public String getUltimoProcessamentoAtual() {
		return ultimoProcessamentoAtual;
	}

	public void setUltimoProcessamentoAtual(String ultimoProcessamentoAtual) {
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(String valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}

	public String getValorAteDia() {
		return valorAteDia;
	}

	public void setValorAteDia(String valorAteDia) {
		this.valorAteDia = valorAteDia;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

}
