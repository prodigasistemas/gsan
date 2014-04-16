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
public class UnResumoRefaturamentoCredito implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.gerencial.faturamento.UnResumoRefaturamentoCreditoPK comp_id;

    /** persistent field */
    private BigDecimal valorDocumentosRefaturados;

    /** persistent field */
    private short quantidadeDocumentosRefaturados;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;

    /** nullable persistent field */
    private GCreditoOrigem gerCreditoOrigem;

    /** nullable persistent field */
    private gcom.gerencial.faturamento.UnResumoRefaturamentoAguaEsgoto unResumoRefaturamentoAguaEsgoto;

    /** full constructor */
    public UnResumoRefaturamentoCredito(gcom.gerencial.faturamento.UnResumoRefaturamentoCreditoPK comp_id, BigDecimal valorDocumentosRefaturados, short quantidadeDocumentosRefaturados, Date ultimaAlteracao, GLancamentoItemContabil gerLancamentoItemContabil, GCreditoOrigem gerCreditoOrigem, gcom.gerencial.faturamento.UnResumoRefaturamentoAguaEsgoto unResumoRefaturamentoAguaEsgoto) {
        this.comp_id = comp_id;
        this.valorDocumentosRefaturados = valorDocumentosRefaturados;
        this.quantidadeDocumentosRefaturados = quantidadeDocumentosRefaturados;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
        this.gerCreditoOrigem = gerCreditoOrigem;
        this.unResumoRefaturamentoAguaEsgoto = unResumoRefaturamentoAguaEsgoto;
    }

    /** default constructor */
    public UnResumoRefaturamentoCredito() {
    }

    /** minimal constructor */
    public UnResumoRefaturamentoCredito(gcom.gerencial.faturamento.UnResumoRefaturamentoCreditoPK comp_id, BigDecimal valorDocumentosRefaturados, short quantidadeDocumentosRefaturados, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.valorDocumentosRefaturados = valorDocumentosRefaturados;
        this.quantidadeDocumentosRefaturados = quantidadeDocumentosRefaturados;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.gerencial.faturamento.UnResumoRefaturamentoCreditoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.gerencial.faturamento.UnResumoRefaturamentoCreditoPK comp_id) {
        this.comp_id = comp_id;
    }

    public BigDecimal getValorDocumentosRefaturados() {
        return this.valorDocumentosRefaturados;
    }

    public void setValorDocumentosRefaturados(BigDecimal valorDocumentosRefaturados) {
        this.valorDocumentosRefaturados = valorDocumentosRefaturados;
    }

    public short getQuantidadeDocumentosRefaturados() {
        return this.quantidadeDocumentosRefaturados;
    }

    public void setQuantidadeDocumentosRefaturados(short quantidadeDocumentosRefaturados) {
        this.quantidadeDocumentosRefaturados = quantidadeDocumentosRefaturados;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GLancamentoItemContabil getGerLancamentoItemContabil() {
        return this.gerLancamentoItemContabil;
    }

    public void setGerLancamentoItemContabil(GLancamentoItemContabil gerLancamentoItemContabil) {
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
    }

    public GCreditoOrigem getGerCreditoOrigem() {
        return this.gerCreditoOrigem;
    }

    public void setGerCreditoOrigem(GCreditoOrigem gerCreditoOrigem) {
        this.gerCreditoOrigem = gerCreditoOrigem;
    }

    public gcom.gerencial.faturamento.UnResumoRefaturamentoAguaEsgoto getUnResumoRefaturamentoAguaEsgoto() {
        return this.unResumoRefaturamentoAguaEsgoto;
    }

    public void setUnResumoRefaturamentoAguaEsgoto(gcom.gerencial.faturamento.UnResumoRefaturamentoAguaEsgoto unResumoRefaturamentoAguaEsgoto) {
        this.unResumoRefaturamentoAguaEsgoto = unResumoRefaturamentoAguaEsgoto;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoRefaturamentoCredito) ) return false;
        UnResumoRefaturamentoCredito castOther = (UnResumoRefaturamentoCredito) other;
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
