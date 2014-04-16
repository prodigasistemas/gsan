package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.Date;

public class AvisoBancarioRelatorioHelper {
	
	private Integer idAvisoBancario;
	private String nomeArrecadador;
	private Date dataLancamento;
	private Short sequencial;
	private Short tipo;
	private Integer numeroDocumento;
	private String banco;
	private String agencia;
	private String numeroConta;
	private Date dataRealizacao;
	private BigDecimal totalArrecadacao;
	private BigDecimal totalDevolucao;
	private BigDecimal valorAviso;
	
	

	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public Date getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public String getNomeArrecadador() {
		return nomeArrecadador;
	}
	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Short getSequencial() {
		return sequencial;
	}
	public void setSequencial(Short sequencial) {
		this.sequencial = sequencial;
	}
	public Short getTipo() {
		return tipo;
	}
	public void setTipo(Short tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getTotalArrecadacao() {
		return totalArrecadacao;
	}
	public void setTotalArrecadacao(BigDecimal totalArrecadacao) {
		this.totalArrecadacao = totalArrecadacao;
	}
	public BigDecimal getTotalDevolucao() {
		return totalDevolucao;
	}
	public void setTotalDevolucao(BigDecimal totalDevolucao) {
		this.totalDevolucao = totalDevolucao;
	}
	public BigDecimal getValorAviso() {
		return valorAviso;
	}
	public void setValorAviso(BigDecimal valorAviso) {
		this.valorAviso = valorAviso;
	}
	public Integer getIdAvisoBancario() {
		return idAvisoBancario;
	}
	public void setIdAvisoBancario(Integer idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}
	

}
