package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * 
 * Bean responsável por auxiliar na montagem do relatório OS por Situação.
 * 
 * @author Diogo Peixoto
 * @since 09/06/2011
 * 
 */
public class RelatorioOSSituacaoBean implements RelatorioBean{

	private String numeroOS;
	private String matriculaImovel;
	private String endImovel;
	private String tipoServico;
	private Date dataEncerramento;
	private String naoCobrada;
	private BigDecimal valorConsumoFraudado;
	private BigDecimal valorMulta;
	private String motivoEncerramento;
	private String retornoFiscalizacao;
	private String parecerEncerramento;
	private String situacaoOS;
	private Integer quantidade;
	
	/**
	 * 
	 * Construtor para o relatório de ordem de serviço situação Analítico
	 * 
	 * @param os
	 * @param matricula
	 * @param endereco
	 * @param servico
	 * @param dataEncerramento
	 * @param cobrada
	 * @param fraudado
	 * @param multa
	 * @param encerramento
	 * @param fiscalizacao
	 * @param parecerEncerramento
	 * @param situacaoOS
	 */
	public RelatorioOSSituacaoBean(String os, String matricula, String endereco, String servico, Date dataEncerramento, String cobrada, 
			BigDecimal fraudado, BigDecimal multa, String encerramento, String fiscalizacao, String parecerEncerramento, String situacaoOS){
		this.numeroOS = os;
		this.matriculaImovel = matricula;
		this.endImovel = endereco;
		this.tipoServico = servico;
		this.dataEncerramento = dataEncerramento;
		this.naoCobrada = cobrada;
		this.valorConsumoFraudado = fraudado;
		this.valorMulta = multa;
		this.motivoEncerramento = encerramento;
		this.retornoFiscalizacao = fiscalizacao;
		this.parecerEncerramento = parecerEncerramento;
		this.situacaoOS = situacaoOS;
	}

	/**
	 * Construtor para o relatório de ordem de serviço situação Sintético
	 * 
	 * @param servico
	 * @param encerramento
	 * @param fiscalizacao
	 * @param situacaoOS
	 * @param quantidade
	 */
	public RelatorioOSSituacaoBean(String servico, String encerramento, String fiscalizacao, String situacaoOS, Integer quantidade){
		this.tipoServico = servico;
		this.motivoEncerramento = encerramento;
		this.retornoFiscalizacao = fiscalizacao;
		this.situacaoOS = situacaoOS;
		this.quantidade = quantidade;
	}
	
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getEndImovel() {
		return endImovel;
	}
	public void setEndImovel(String endImovel) {
		this.endImovel = endImovel;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getCobradaAutomaticamente() {
		return naoCobrada;
	}
	public void setCobradaAutomaticamente(String cobradaAutomaticamente) {
		this.naoCobrada = cobradaAutomaticamente;
	}
	public BigDecimal getValorConsumoFraudado() {
		return valorConsumoFraudado;
	}
	public void setValorConsumoFraudado(BigDecimal valorConsumoFraudado) {
		this.valorConsumoFraudado = valorConsumoFraudado;
	}
	public BigDecimal getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getRetornoFiscalizacao() {
		return retornoFiscalizacao;
	}
	public void setRetornoFiscalizacao(String retornoFiscalizacao) {
		this.retornoFiscalizacao = retornoFiscalizacao;
	}
	public String getSituacaoOS() {
		return situacaoOS;
	}
	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}
	public String getParecerEncerramento() {
		return parecerEncerramento;
	}
	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}
	public Date getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getNaoCobrada() {
		return naoCobrada;
	}
	public void setNaoCobrada(String naoCobrada) {
		this.naoCobrada = naoCobrada;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
