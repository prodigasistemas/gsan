package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Date;


public class MovimentoArrecadadoresRelatorioHelper {
	
	private Integer anoMesReferencia;
	private String arrecadador;
	private String descricaoArrecadacaoForma;
	private Date dataPagamento;
	private Integer qtdeDocumentos;
	private Integer qtdePagamentos;
	private BigDecimal valorPagamento;
	
	/**
	 * @return Retorna o campo valorPagamento.
	 */
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	/**
	 * @param valorPagamento O valorPagamento a ser setado.
	 */
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	/**
	 * @return Retorna o campo arrecadador.
	 */
	public String getArrecadador() {
		return arrecadador;
	}
	/**
	 * @param arrecadador O arrecadador a ser setado.
	 */
	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}
	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}
	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	/**
	 * @return Retorna o campo descricaoArrecadacaoForma.
	 */
	public String getDescricaoArrecadacaoForma() {
		return descricaoArrecadacaoForma;
	}
	/**
	 * @param descricaoArrecadacaoForma O descricaoArrecadacaoForma a ser setado.
	 */
	public void setDescricaoArrecadacaoForma(String descricaoArrecadacaoForma) {
		this.descricaoArrecadacaoForma = descricaoArrecadacaoForma;
	}
	/**
	 * @return Retorna o campo qtdeDocumentos.
	 */
	public Integer getQtdeDocumentos() {
		return qtdeDocumentos;
	}
	/**
	 * @param qtdeDocumentos O qtdeDocumentos a ser setado.
	 */
	public void setQtdeDocumentos(Integer qtdeDocumentos) {
		this.qtdeDocumentos = qtdeDocumentos;
	}
	/**
	 * @return Retorna o campo qtdePagamentos.
	 */
	public Integer getQtdePagamentos() {
		return qtdePagamentos;
	}
	/**
	 * @param qtdePagamentos O qtdePagamentos a ser setado.
	 */
	public void setQtdePagamentos(Integer qtdePagamentos) {
		this.qtdePagamentos = qtdePagamentos;
	} 
	
	
	
}
