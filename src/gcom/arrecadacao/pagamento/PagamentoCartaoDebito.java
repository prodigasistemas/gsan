package gcom.arrecadacao.pagamento;

import gcom.arrecadacao.Arrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PagamentoCartaoDebito implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String documentoCartaoDebito;

	private String numeroAutorizacao;

	private String numeroCartaoDebito;

	private Integer anoMesValidade;

	private Cliente cliente;

	private Arrecadador arrecadador;

	private Usuario usuarioConfirmacao;

	private String identificacaoTransacao;

	private BigDecimal valorPagamento;

	private Date ultimaAlteracao;
	
	private Date dataConfirmacao;
	
	private Short indicadorConfirmadoOperadora;
	
	private Date dataConfirmadoOperadora;
	
	private BigDecimal valorConfirmadoOperadora;
	
	public PagamentoCartaoDebito(){}

	public Integer getAnoMesValidade() {
		return anoMesValidade;
	}

	public void setAnoMesValidade(Integer anoMesValidade) {
		this.anoMesValidade = anoMesValidade;
	}

	public Arrecadador getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public String getDocumentoCartaoDebito() {
		return documentoCartaoDebito;
	}

	public void setDocumentoCartaoDebito(String documentoCartaoDebito) {
		this.documentoCartaoDebito = documentoCartaoDebito;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificacaoTransacao() {
		return identificacaoTransacao;
	}

	public void setIdentificacaoTransacao(String identificacaoTransacao) {
		this.identificacaoTransacao = identificacaoTransacao;
	}

	public String getNumeroAutorizacao() {
		return numeroAutorizacao;
	}

	public void setNumeroAutorizacao(String numeroAutorizacao) {
		this.numeroAutorizacao = numeroAutorizacao;
	}

	public String getNumeroCartaoDebito() {
		return numeroCartaoDebito;
	}

	public void setNumeroCartaoDebito(String numeroCartaoDebito) {
		this.numeroCartaoDebito = numeroCartaoDebito;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuarioConfirmacao() {
		return usuarioConfirmacao;
	}

	public void setUsuarioConfirmacao(Usuario usuarioConfirmacao) {
		this.usuarioConfirmacao = usuarioConfirmacao;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public Date getDataConfirmadoOperadora() {
		return dataConfirmadoOperadora;
	}

	public void setDataConfirmadoOperadora(Date dataConfirmadoOperadora) {
		this.dataConfirmadoOperadora = dataConfirmadoOperadora;
	}

	public Short getIndicadorConfirmadoOperadora() {
		return indicadorConfirmadoOperadora;
	}

	public void setIndicadorConfirmadoOperadora(Short indicadorConfirmadoOperadora) {
		this.indicadorConfirmadoOperadora = indicadorConfirmadoOperadora;
	}

	public BigDecimal getValorConfirmadoOperadora() {
		return valorConfirmadoOperadora;
	}

	public void setValorConfirmadoOperadora(BigDecimal valorConfirmadoOperadora) {
		this.valorConfirmadoOperadora = valorConfirmadoOperadora;
	}
}
