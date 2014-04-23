package gcom.gerencial.faturamento;

import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoFaturamentoCredito implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.gerencial.faturamento.UnResumoFaturamentoCreditoPK comp_id;

    /** persistent field */
    private BigDecimal volumeDocumentosFaturados;

    /** persistent field */
    private Integer quantidadeDocumentosFaturados;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;

    /** nullable persistent field */
    private GCreditoOrigem gerCreditoOrigem;

    /** nullable persistent field */
    private gcom.gerencial.faturamento.UnResumoFaturamento unResumoFaturamento;

    /** full constructor */
    public UnResumoFaturamentoCredito(gcom.gerencial.faturamento.UnResumoFaturamentoCreditoPK comp_id, BigDecimal volumeDocumentosFaturados, Integer quantidadeDocumentosFaturados, Date ultimaAlteracao, GLancamentoItemContabil gLancamentoItemContabil, GCreditoOrigem gCreditoOrigem, gcom.gerencial.faturamento.UnResumoFaturamento unResumoFaturamento) {        this.comp_id = comp_id;        this.volumeDocumentosFaturados = volumeDocumentosFaturados;        this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;        this.ultimaAlteracao = ultimaAlteracao;        this.gerLancamentoItemContabil = gLancamentoItemContabil;        this.gerCreditoOrigem = gCreditoOrigem;        this.unResumoFaturamento = unResumoFaturamento;    }

    /** default constructor */
    public UnResumoFaturamentoCredito() {
    }

    /** minimal constructor */
    public UnResumoFaturamentoCredito(gcom.gerencial.faturamento.UnResumoFaturamentoCreditoPK comp_id, BigDecimal volumeDocumentosFaturados, Integer quantidadeDocumentosFaturados, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.volumeDocumentosFaturados = volumeDocumentosFaturados;
        this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.gerencial.faturamento.UnResumoFaturamentoCreditoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.gerencial.faturamento.UnResumoFaturamentoCreditoPK comp_id) {
        this.comp_id = comp_id;
    }

    public BigDecimal getVolumeDocumentosFaturados() {
        return this.volumeDocumentosFaturados;
    }

    public void setVolumeDocumentosFaturados(BigDecimal volumeDocumentosFaturados) {
        this.volumeDocumentosFaturados = volumeDocumentosFaturados;
    }

    public Integer getQuantidadeDocumentosFaturados() {
        return this.quantidadeDocumentosFaturados;
    }

    public void setQuantidadeDocumentosFaturados(Integer quantidadeDocumentosFaturados) {
        this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

 

    public GCreditoOrigem getGerCreditoOrigem() {
		return gerCreditoOrigem;
	}

	public void setGerCreditoOrigem(GCreditoOrigem gerCreditoOrigem) {
		this.gerCreditoOrigem = gerCreditoOrigem;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabil() {
		return gerLancamentoItemContabil;
	}

	public void setGerLancamentoItemContabil(
			GLancamentoItemContabil gerLancamentoItemContabil) {
		this.gerLancamentoItemContabil = gerLancamentoItemContabil;
	}

	public gcom.gerencial.faturamento.UnResumoFaturamento getUnResumoFaturamento() {
        return this.unResumoFaturamento;
    }

    public void setUnResumoFaturamento(gcom.gerencial.faturamento.UnResumoFaturamento unResumoFaturamento) {
        this.unResumoFaturamento = unResumoFaturamento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoFaturamentoCredito) ) return false;
        UnResumoFaturamentoCredito castOther = (UnResumoFaturamentoCredito) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
