package gcom.cobranca.contratoparcelamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.gui.cobranca.contratoparcelamento.InformarPagamentoContratoParcelamentoPorClienteActionForm;

import java.io.Serializable;

public class InformarPagamentoContratoParcelamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numeroContrato;
	
	private String idCliente;

	private String nomeCliente;
	
	private String idRegistro;
	
	private String totalSelecionado;
	
	private String idArrecadador;
	
	private String nomeArrecadador;
	
	private String numeroParcela;
	
	private String valorParcelado;
	
	private String dataPagamento;
	
	private ArrecadacaoForma arrecadacaoForma;

	public InformarPagamentoContratoParcelamentoHelper() {
		super();
	}

	public InformarPagamentoContratoParcelamentoHelper(String numeroContrato, String idCliente, String nomeCliente, String idRegistro, String totalSelecionado, String idArrecadador, String nomeArrecadador, String numeroParcela, String valorParcelado, String dataPagamento) {
		super();
		this.numeroContrato = numeroContrato;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idRegistro = idRegistro;
		this.totalSelecionado = totalSelecionado;
		this.idArrecadador = idArrecadador;
		this.nomeArrecadador = nomeArrecadador;
		this.numeroParcela = numeroParcela;
		this.valorParcelado = valorParcelado;
		this.dataPagamento = dataPagamento;
	}
	
	public InformarPagamentoContratoParcelamentoHelper(InformarPagamentoContratoParcelamentoPorClienteActionForm form) {
		super();
		this.numeroContrato = form.getNumeroContrato();
		this.idCliente = form.getIdCliente();
		this.nomeCliente = form.getNomeCliente();
		this.idRegistro = form.getIdRegistro();
		this.totalSelecionado = form.getTotalSelecionado();
		this.idArrecadador = form.getIdArrecadador();
		this.nomeArrecadador = form.getNomeArrecadador();
		this.numeroParcela = form.getNumeroParcela();
		this.valorParcelado = form.getValorParcelado();
		this.dataPagamento = form.getDataPagamento();
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getTotalSelecionado() {
		return totalSelecionado;
	}

	public void setTotalSelecionado(String totalSelecionado) {
		this.totalSelecionado = totalSelecionado;
	}

	public String getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(String valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}
	
}
