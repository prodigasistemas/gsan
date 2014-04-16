package gcom.gerencial.faturamento;

import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.financeiro.GFinanciamentoTipo;import gcom.gerencial.faturamento.UnResumoFaturamento;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoFaturamentoOutro implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */    private gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK comp_id;    /** persistent field */    private BigDecimal valorDocumentosFaturados;    /** persistent field */    private int quantidadeDocumentosFaturados;    /** persistent field */    private Date ultimaAlteracao;    /** nullable persistent field */    private GLancamentoItemContabil gerLancamentoItemContabil;    /** nullable persistent field */    private gcom.gerencial.faturamento.UnResumoFaturamento unResumoFaturamento;    /** nullable persistent field */    private GDocumentoTipo gerDocumentoTipo;    /** nullable persistent field */    private GFinanciamentoTipo gerFinanciamentoTipo;    public BigDecimal getValorDocumentosFaturados() {		return valorDocumentosFaturados;	}	public void setValorDocumentosFaturados(BigDecimal valorDocumentosFaturados) {		this.valorDocumentosFaturados = valorDocumentosFaturados;	}	public UnResumoFaturamentoOutro(UnResumoFaturamento unResumoFaturamento,     		GDocumentoTipo gDocumentoTipo, GFinanciamentoTipo gFinanciamentoTipo,    		GLancamentoItemContabil gLancamentoItemContabil, BigDecimal valorDocumentosFaturados,     		int quantidadeDocumentosFaturados, Date ultimaAlteracao) {        this.unResumoFaturamento = unResumoFaturamento;        this.gerDocumentoTipo = gDocumentoTipo;        this.gerFinanciamentoTipo = gFinanciamentoTipo;        this.gerLancamentoItemContabil = gLancamentoItemContabil;        this.valorDocumentosFaturados = valorDocumentosFaturados;        this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;        this.ultimaAlteracao = ultimaAlteracao;    }    
    /** full constructor */    public UnResumoFaturamentoOutro(gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK comp_id, BigDecimal valorDocumentosFaturados, int quantidadeDocumentosFaturados, Date ultimaAlteracao, GLancamentoItemContabil gLancamentoItemContabil, gcom.gerencial.faturamento.UnResumoFaturamento unResumoFaturamento, GDocumentoTipo gDocumentoTipo, GFinanciamentoTipo gFinanciamentoTipo) {        this.comp_id = comp_id;        this.valorDocumentosFaturados = valorDocumentosFaturados;        this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;        this.ultimaAlteracao = ultimaAlteracao;        this.gerLancamentoItemContabil = gLancamentoItemContabil;        this.unResumoFaturamento = unResumoFaturamento;        this.gerDocumentoTipo = gDocumentoTipo;        this.gerFinanciamentoTipo = gFinanciamentoTipo;    }

    /** default constructor */
    public UnResumoFaturamentoOutro() {
    }

    /** minimal constructor */
    public UnResumoFaturamentoOutro(gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK comp_id, BigDecimal valorDocumentosFaturados, short quantidadeDocumentosFaturados, Date ultimaAlteracao) {        this.comp_id = comp_id;        this.valorDocumentosFaturados = valorDocumentosFaturados;        this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;        this.ultimaAlteracao = ultimaAlteracao;    }

    public gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK getComp_id() {        return this.comp_id;    }
    public void setComp_id(gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK comp_id) {        this.comp_id = comp_id;    }    public int getQuantidadeDocumentosFaturados() {        return this.quantidadeDocumentosFaturados;    }
    public void setQuantidadeDocumentosFaturados(short quantidadeDocumentosFaturados) {        this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;    }
    public Date getUltimaAlteracao() {        return this.ultimaAlteracao;    }
    public void setUltimaAlteracao(Date ultimaAlteracao) {        this.ultimaAlteracao = ultimaAlteracao;    }
    public gcom.gerencial.faturamento.UnResumoFaturamento getUnResumoFaturamento() {        return this.unResumoFaturamento;    }
    public void setUnResumoFaturamento(gcom.gerencial.faturamento.UnResumoFaturamento unResumoFaturamento) {        this.unResumoFaturamento = unResumoFaturamento;    }
    public GDocumentoTipo getGerDocumentoTipo() {		return gerDocumentoTipo;	}
	public void setGerDocumentoTipo(GDocumentoTipo gerDocumentoTipo) {		this.gerDocumentoTipo = gerDocumentoTipo;	}
	public GFinanciamentoTipo getGerFinanciamentoTipo() {		return gerFinanciamentoTipo;	}
	public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {		this.gerFinanciamentoTipo = gerFinanciamentoTipo;	}
	public GLancamentoItemContabil getGerLancamentoItemContabil() {		return gerLancamentoItemContabil;	}
	public void setGerLancamentoItemContabil(			GLancamentoItemContabil gerLancamentoItemContabil) {		this.gerLancamentoItemContabil = gerLancamentoItemContabil;	}
	public String toString() {        return new ToStringBuilder(this)            .append("comp_id", getComp_id())            .toString();    }
    public boolean equals(Object other) {        if ( (this == other ) ) return true;        if ( !(other instanceof UnResumoFaturamentoOutro) ) return false;        UnResumoFaturamentoOutro castOther = (UnResumoFaturamentoOutro) other;        return new EqualsBuilder()            .append(this.getComp_id(), castOther.getComp_id())            .isEquals();    }
    public int hashCode() {        return new HashCodeBuilder()            .append(getComp_id())            .toHashCode();    }}
