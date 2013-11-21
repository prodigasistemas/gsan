package gcom.gui.arrecadacao;

import org.apache.struts.action.ActionForm;

public class InformarAcertoDocumentosNaoAceitosActionForm  extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	/* 1ª aba */
	private String periodoPagamentoInicial;
	
	private String periodoPagamentoFinal;
	
	private String idAvisoBancario;
	
	private String codigoAgenteArrecadador;
	
	private String dataLancamentoAviso;
	
	private String numeroSequencialAviso;
	
	private String idFormaArrecadacao;
	
	private String idArrecadador;
	
	private String nomeArrecadador;

	private String idArrecadadorMov;

	private String codigoBanco;

	private String codigoRemessa;

	private String identificacaoServico;
	
	private String numeroSequencial;
	
	private String idPagamentoSelecionado;

	/* 2ª aba */
	private String idImovel;
	
	private String descricaoImovel;
	
	private String totalPagamento;
	
	private String totalDebitoSelecionado;
	
	private String totalDebitos;
	
	private String[] contasSelecao;
	
	private String[] debitosACobrarSelecao;
	
	private String[] guiasSelecao;
	
	private String idsConta;
	
	private String idsDebito;
	
	private String idsGuia;
	

	public String getPeriodoPagamentoInicial() {
		return periodoPagamentoInicial;
	}

	public void setPeriodoPagamentoInicial(String periodoPagamentoInicial) {
		this.periodoPagamentoInicial = periodoPagamentoInicial;
	}

	public String getPeriodoPagamentoFinal() {
		return periodoPagamentoFinal;
	}

	public void setPeriodoPagamentoFinal(String periodoPagamentoFinal) {
		this.periodoPagamentoFinal = periodoPagamentoFinal;
	}


	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}

	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}

	public String getDataLancamentoAviso() {
		return dataLancamentoAviso;
	}

	public void setDataLancamentoAviso(String dataLancamentoAviso) {
		this.dataLancamentoAviso = dataLancamentoAviso;
	}

	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}

	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}

	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoRemessa() {
		return codigoRemessa;
	}

	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}

	public String getIdArrecadadorMov() {
		return idArrecadadorMov;
	}

	public void setIdArrecadadorMov(String idArrecadadorMov) {
		this.idArrecadadorMov = idArrecadadorMov;
	}

	public String getIdentificacaoServico() {
		return identificacaoServico;
	}

	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}

	public String getNumeroSequencial() {
		return numeroSequencial;
	}

	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public String getIdPagamentoSelecionado() {
		return idPagamentoSelecionado;
	}

	public void setIdPagamentoSelecionado(String idPagamentoSelecionado) {
		this.idPagamentoSelecionado = idPagamentoSelecionado;
	}

	public String getTotalPagamento() {
		return totalPagamento;
	}

	public void setTotalPagamento(String totalPagamento) {
		this.totalPagamento = totalPagamento;
	}

	public String getTotalDebitoSelecionado() {
		return totalDebitoSelecionado;
	}

	public void setTotalDebitoSelecionado(String totalDebitoSelecionado) {
		this.totalDebitoSelecionado = totalDebitoSelecionado;
	}

	public String getTotalDebitos() {
		return totalDebitos;
	}

	public void setTotalDebitos(String totalDebitos) {
		this.totalDebitos = totalDebitos;
	}

	public String[] getContasSelecao() {
		return contasSelecao;
	}

	public void setContasSelecao(String[] contasSelecao) {
		this.contasSelecao = contasSelecao;
	}

	public String[] getDebitosACobrarSelecao() {
		return debitosACobrarSelecao;
	}

	public void setDebitosACobrarSelecao(String[] debitosACobrarSelecao) {
		this.debitosACobrarSelecao = debitosACobrarSelecao;
	}

	public String[] getGuiasSelecao() {
		return guiasSelecao;
	}

	public void setGuiasSelecao(String[] guiasSelecao) {
		this.guiasSelecao = guiasSelecao;
	}

	public String getIdsConta() {
		return idsConta;
	}

	public void setIdsConta(String idsConta) {
		this.idsConta = idsConta;
	}

	public String getIdsDebito() {
		return idsDebito;
	}

	public void setIdsDebito(String idsDebito) {
		this.idsDebito = idsDebito;
	}

	public String getIdsGuia() {
		return idsGuia;
	}

	public void setIdsGuia(String idsGuia) {
		this.idsGuia = idsGuia;
	}

}
