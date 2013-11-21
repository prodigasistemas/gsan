package gcom.gui.cobranca.parcelamento;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class ParcelamentoCartaoConfirmarForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14203768171311214L;
	
	private String modalidadeCartao;
	
	private String matriculaImovel;
	
	private String inscricaoImovel;
	
	private String enderecoImovel;
	
	private Short numeroPrestacoes;
	
	private BigDecimal valorPrestacao;
	
	private BigDecimal valorTotal;
	
	private Date parcelamento;
	
	private String idParcelamento;
	
	private String cartaoCredito;
	
	private String idCliente;
	
 	private String nomeCliente;
 	
 	private String validadeCartao;
 	
 	private String validadeCartaoInvalida;
 	
 	private String numeroCartao;
 	
 	private String documentoCartao;

 	private String autorizacaoCartao;
 	
 	private String numeroIdentificacaoTransacao;
 	
 	private String usuarioConfirmacao;
 	
 	private String valorTransacao;
 	
 	private String qtdParcelas;
 	
 	private String dataOperadora;
 	
 	private String[] conta;
 	
 	private String[] guiaPagamento;
 	
 	private String[] debito;
 	
 	private String[] parcelamentoDebito;


 	public String getUsuarioConfirmacao() {
		return usuarioConfirmacao;
	}

	public void setUsuarioConfirmacao(String usuarioConfirmacao) {
		this.usuarioConfirmacao = usuarioConfirmacao;
	}

	public String getNumeroIdentificacaoTransacao() {
		return numeroIdentificacaoTransacao;
	}

	public void setNumeroIdentificacaoTransacao(String numeroIdentificacaoTransacao) {
		this.numeroIdentificacaoTransacao = numeroIdentificacaoTransacao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getValidadeCartao() {
		return validadeCartao;
	}

	public void setValidadeCartao(String validadeCartao) {
		this.validadeCartao = validadeCartao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public String getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(String idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public Short getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Short numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public Date getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Date parcelamento) {
		this.parcelamento = parcelamento;
	}

	public BigDecimal getValorPrestacao() {
		return valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getValidadeCartaoInvalida() {
		return validadeCartaoInvalida;
	}

	public void setValidadeCartaoInvalida(String validadeCartaoInvalida) {
		this.validadeCartaoInvalida = validadeCartaoInvalida;
	}

	public String getAutorizacaoCartao() {
		return autorizacaoCartao;
	}

	public void setAutorizacaoCartao(String autorizacaoCartao) {
		this.autorizacaoCartao = autorizacaoCartao;
	}

	public String getDocumentoCartao() {
		return documentoCartao;
	}

	public void setDocumentoCartao(String documentoCartao) {
		this.documentoCartao = documentoCartao;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public String getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getModalidadeCartao() {
		return modalidadeCartao;
	}

	public void setModalidadeCartao(String modalidadeCartao) {
		this.modalidadeCartao = modalidadeCartao;
	}

	public String getDataOperadora() {
		return dataOperadora;
	}

	public void setDataOperadora(String dataOperadora) {
		this.dataOperadora = dataOperadora;
	}
	
	public String[] getConta() {
		return conta;
	}

	public void setConta(String[] conta) {
		this.conta = conta;
	}

	public String[] getDebito() {
		return debito;
	}

	public void setDebito(String[] debito) {
		this.debito = debito;
	}

	public String[] getGuiaPagamento() {
		return guiaPagamento;
	}

	public void setGuiaPagamento(String[] guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public String[] getParcelamentoDebito() {
		return parcelamentoDebito;
	}

	public void setParcelamentoDebito(String[] parcelamentoDebito) {
		this.parcelamentoDebito = parcelamentoDebito;
	}
}
