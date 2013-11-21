package gcom.cobranca.bean;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class ParcelamentoCartaoCreditoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroCartao;
 	
 	private String documentoCartao;

 	private String autorizacaoCartao;
 	
 	private String numeroIdentificacaoTransacao;
 	
 	private String valorTransacao;
 	
 	private String qtdParcelas;
 	
	private String validadeCartao;
	
	private String idCliente;
	
 	private String nomeCliente;
 	
 	private Usuario usuario;
 	
 	private String tempoInclusao;
 	
 	private String IdArrecadador;

 	private Date dataConfirmacaoOperadora;

	public String getIdArrecadador() {
		return IdArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		IdArrecadador = idArrecadador;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroIdentificacaoTransacao() {
		return numeroIdentificacaoTransacao;
	}

	public void setNumeroIdentificacaoTransacao(String numeroIdentificacaoTransacao) {
		this.numeroIdentificacaoTransacao = numeroIdentificacaoTransacao;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public String getValidadeCartao() {
		return validadeCartao;
	}

	public void setValidadeCartao(String validadeCartao) {
		this.validadeCartao = validadeCartao;
	}

	public String getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTempoInclusao() {
		return tempoInclusao;
	}

	public void setTempoInclusao(String tempoInclusao) {
		this.tempoInclusao = tempoInclusao;
	}

	public Date getDataConfirmacaoOperadora() {
		return dataConfirmacaoOperadora;
	}

	public void setDataConfirmacaoOperadora(Date dataConfirmacaoOperadora) {
		this.dataConfirmacaoOperadora = dataConfirmacaoOperadora;
	}

}
