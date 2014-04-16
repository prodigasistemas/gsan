package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterMovimentoArrecadadorBean implements RelatorioBean {

	private String idMovimento;

	private String codigoArrecadador;

	private String nomeArrecadador;

	private String remessa;

	private String servico;

	private String sequencial;

	private String dataGeracao;

	private String dataProcessamento;

	private String situacao;

	private String valorMovimento;

	private String registro;

	private String imovelCliente;

	private String tipoPagamento;

	private String identificacaoClienteBanco;

	private String dataPagamento;

	private String dataPrevista;

	private String codigoBarras;

	private String forma;

	private String agencia;

	private String data;

	private String anoMes;

	private String valor;
	
	private String codigoRetorno;

	private String codigoMovimento;

	private String ocorrencia;

	private String aceitacao;

	private String totalRegistros;

	private String qtdeEmOcorrencia;

	private String qtdeNaoAceitos;

	/**
	 * Construtor da classe RelatorioManterCronogramaFaturamentoBean
	 * 
	 * @param grupo
	 *            Descrição do parâmetro
	 * @param mesAno
	 *            Descrição do parâmetro
	 * @param atividade
	 *            Descrição do parâmetro
	 * @param predecessora
	 *            Descrição do parâmetro
	 * @param obrigatoria
	 *            Descrição do parâmetro
	 * @param dataPrevista
	 *            Descrição do parâmetro
	 * @param dataRealizacao
	 *            Descrição do parâmetro
	 */

	public RelatorioManterMovimentoArrecadadorBean(String idMovimento,
			String codigoArrecadador, String nomeArrecadador, String remessa,
			String servico, String sequencial, String dataGeracao,
			String dataProcessamento, String situacao, String valorMovimento,
			String registro, String imovelCliente, String tipoPagamento,
			String identificacaoClienteBanco, String dataPagamento,
			String dataPrevista, String codigoBarras, String forma,
			String agencia, String data, String anoMes, String valor, String codigoRetorno,
			String codigoMovimento, String ocorrencia, String aceitacao,
			String totalRegistros, String qtdeEmOcorrencia,
			String qtdeNaoAceitos) {
		this.idMovimento = idMovimento;
		this.codigoArrecadador = codigoArrecadador;
		this.nomeArrecadador = nomeArrecadador;
		this.remessa = remessa;
		this.servico = servico;
		this.sequencial = sequencial;
		this.dataGeracao = dataGeracao;
		this.dataProcessamento = dataProcessamento;
		this.situacao = situacao;
		this.valorMovimento = valorMovimento;
		this.registro = registro;
		this.imovelCliente = imovelCliente;
		this.tipoPagamento = tipoPagamento;
		this.identificacaoClienteBanco = identificacaoClienteBanco;
		this.dataPagamento = dataPagamento;
		this.dataPrevista = dataPrevista;
		this.codigoBarras = codigoBarras;
		this.forma = forma;
		this.agencia = agencia;
		this.data = data;
		this.anoMes = anoMes;
		this.valor = valor;
		this.codigoRetorno = codigoRetorno;
		this.codigoMovimento = codigoMovimento;
		this.ocorrencia = ocorrencia;
		this.aceitacao = aceitacao;
		this.totalRegistros = totalRegistros;
		this.qtdeEmOcorrencia = qtdeEmOcorrencia;
		this.qtdeNaoAceitos = qtdeNaoAceitos;
	}

	public String getAceitacao() {
		return aceitacao;
	}

	public void setAceitacao(String aceitacao) {
		this.aceitacao = aceitacao;
	}

	public String getCodigoArrecadador() {
		return codigoArrecadador;
	}

	public void setCodigoArrecadador(String codigoArrecadador) {
		this.codigoArrecadador = codigoArrecadador;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public String getImovelCliente() {
		return imovelCliente;
	}

	public void setImovelCliente(String imovelCliente) {
		this.imovelCliente = imovelCliente;
	}

	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getQtdeEmOcorrencia() {
		return qtdeEmOcorrencia;
	}

	public void setQtdeEmOcorrencia(String qtdeEmOcorrencia) {
		this.qtdeEmOcorrencia = qtdeEmOcorrencia;
	}

	public String getQtdeNaoAceitos() {
		return qtdeNaoAceitos;
	}

	public void setQtdeNaoAceitos(String qtdeNaoAceitos) {
		this.qtdeNaoAceitos = qtdeNaoAceitos;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getRemessa() {
		return remessa;
	}

	public void setRemessa(String remessa) {
		this.remessa = remessa;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public String getValorMovimento() {
		return valorMovimento;
	}

	public void setValorMovimento(String valorMovimento) {
		this.valorMovimento = valorMovimento;
	}

	public String getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(String idMovimento) {
		this.idMovimento = idMovimento;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoMovimento() {
		return codigoMovimento;
	}

	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public String getIdentificacaoClienteBanco() {
		return identificacaoClienteBanco;
	}

	public void setIdentificacaoClienteBanco(String identificacaoClienteBanco) {
		this.identificacaoClienteBanco = identificacaoClienteBanco;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

}
