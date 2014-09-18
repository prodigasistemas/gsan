package gcom.batch.auxiliarbatch;

import gcom.batch.ProcessoIniciado;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class CobrancaDocumentoControleGeracao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer quantidadeCobrancaDocumento;
    
    private Integer quantidadeCobrancaDocumentoItem;

    private BigDecimal valorTotalCobrancaDocumentos;

    private Date ultimaAlteracao;

    private ProcessoIniciado processoIniciado;
    
    private CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;
    
    private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
    
    public CobrancaDocumentoControleGeracao() {
		super();
	}

	public CobrancaDocumentoControleGeracao(Integer quantidadeCobrancaDocumento, Integer quantidadeCobrancaDocumentoItem, BigDecimal valorTotalCobrancaDocumentos, Date ultimaAlteracao, ProcessoIniciado processoIniciado, CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		super();
		
		this.quantidadeCobrancaDocumento = quantidadeCobrancaDocumento;
		this.quantidadeCobrancaDocumentoItem = quantidadeCobrancaDocumentoItem;
		this.valorTotalCobrancaDocumentos = valorTotalCobrancaDocumentos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.processoIniciado = processoIniciado;
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProcessoIniciado getProcessoIniciado() {
		return processoIniciado;
	}
	public void setProcessoIniciado(ProcessoIniciado processoIniciado) {
		this.processoIniciado = processoIniciado;
	}
	public Integer getQuantidadeCobrancaDocumento() {
		return quantidadeCobrancaDocumento;
	}
	public void setQuantidadeCobrancaDocumento(Integer quantidadeCobrancaDocumento) {
		this.quantidadeCobrancaDocumento = quantidadeCobrancaDocumento;
	}
	public Integer getQuantidadeCobrancaDocumentoItem() {
		return quantidadeCobrancaDocumentoItem;
	}
	public void setQuantidadeCobrancaDocumentoItem(
			Integer quantidadeCobrancaDocumentoItem) {
		this.quantidadeCobrancaDocumentoItem = quantidadeCobrancaDocumentoItem;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public BigDecimal getValorTotalCobrancaDocumentos() {
		return valorTotalCobrancaDocumentos;
	}
	public void setValorTotalCobrancaDocumentos(
			BigDecimal valorTotalCobrancaDocumentos) {
		this.valorTotalCobrancaDocumentos = valorTotalCobrancaDocumentos;
	}
	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}
	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}
	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}
	public void setCobrancaAcaoAtividadeCronograma(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}
}
