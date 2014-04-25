package gcom.gerencial.arrecadacao;

import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoArrecadacaoOutro implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id;

    /** persistent field */
    private BigDecimal volumeDocumentosRecebidos;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;

    /** nullable persistent field */
    private gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto unResumoArrecadacaoAguaEsgoto;

    /** nullable persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipo;

    /** full constructor */
    public UnResumoArrecadacaoOutro(gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id, BigDecimal volumeDocumentosRecebidos, Date ultimaAlteracao, GLancamentoItemContabil gLancamentoItemContabil, gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto unResumoArrecadacaoAguaEsgoto, GFinanciamentoTipo gFinanciamentoTipo) {
        this.comp_id = comp_id;
        this.volumeDocumentosRecebidos = volumeDocumentosRecebidos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerLancamentoItemContabil = gLancamentoItemContabil;
        this.unResumoArrecadacaoAguaEsgoto = unResumoArrecadacaoAguaEsgoto;
        this.gerFinanciamentoTipo = gFinanciamentoTipo;
    }

    /** default constructor */
    public UnResumoArrecadacaoOutro() {
    }

    /** minimal constructor */
    public UnResumoArrecadacaoOutro(gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id, BigDecimal volumeDocumentosRecebidos, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.volumeDocumentosRecebidos = volumeDocumentosRecebidos;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutroPK comp_id) {
        this.comp_id = comp_id;
    }

    public BigDecimal getVolumeDocumentosRecebidos() {
        return this.volumeDocumentosRecebidos;
    }

    public void setVolumeDocumentosRecebidos(BigDecimal volumeDocumentosRecebidos) {
        this.volumeDocumentosRecebidos = volumeDocumentosRecebidos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GLancamentoItemContabil getGLancamentoItemContabil() {
        return this.gerLancamentoItemContabil;
    }

 
    public gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto getUnResumoArrecadacaoAguaEsgoto() {
        return this.unResumoArrecadacaoAguaEsgoto;
    }

    public void setUnResumoArrecadacaoAguaEsgoto(gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto unResumoArrecadacaoAguaEsgoto) {
        this.unResumoArrecadacaoAguaEsgoto = unResumoArrecadacaoAguaEsgoto;
    }

 
    
    public GFinanciamentoTipo getGerFinanciamentoTipo() {
		return gerFinanciamentoTipo;
	}

	public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {
		this.gerFinanciamentoTipo = gerFinanciamentoTipo;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabil() {
		return gerLancamentoItemContabil;
	}

	public void setGerLancamentoItemContabil(
			GLancamentoItemContabil gerLancamentoItemContabil) {
		this.gerLancamentoItemContabil = gerLancamentoItemContabil;
	}

	public void setGFinanciamentoTipo(GFinanciamentoTipo gFinanciamentoTipo) {
        this.gerFinanciamentoTipo = gFinanciamentoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoArrecadacaoOutro) ) return false;
        UnResumoArrecadacaoOutro castOther = (UnResumoArrecadacaoOutro) other;
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
