package gcom.gerencial.faturamento.credito;

import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GCreditoTipo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoCreditoTipo;

    /** nullable persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal valorLimite;

    /** nullable persistent field */
    private Short indicadorGeracaoAutomatica;

    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;

    /** full constructor */
    public GCreditoTipo(Integer id, String descricaoCreditoTipo, String descricaoAbreviado, Short indicadorUso, Date ultimaAlteracao, BigDecimal valorLimite, Short indicadorGeracaoAutomatica, GLancamentoItemContabil gerLancamentoItemContabil) {
        this.id = id;
        this.descricaoCreditoTipo = descricaoCreditoTipo;
        this.descricaoAbreviado = descricaoAbreviado;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorLimite = valorLimite;
        this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
    }

    /** default constructor */
    public GCreditoTipo() {
    }

    /** minimal constructor */
    public GCreditoTipo(Integer id, Date ultimaAlteracao, GLancamentoItemContabil gerLancamentoItemContabil) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoCreditoTipo() {
        return this.descricaoCreditoTipo;
    }

    public void setDescricaoCreditoTipo(String descricaoCreditoTipo) {
        this.descricaoCreditoTipo = descricaoCreditoTipo;
    }

    public String getDescricaoAbreviado() {
        return this.descricaoAbreviado;
    }

    public void setDescricaoAbreviado(String descricaoAbreviado) {
        this.descricaoAbreviado = descricaoAbreviado;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorLimite() {
        return this.valorLimite;
    }

    public void setValorLimite(BigDecimal valorLimite) {
        this.valorLimite = valorLimite;
    }

    public Short getIndicadorGeracaoAutomatica() {
        return this.indicadorGeracaoAutomatica;
    }

    public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAutomatica) {
        this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
    }

    public GLancamentoItemContabil getGerLancamentoItemContabil() {
        return this.gerLancamentoItemContabil;
    }

    public void setGerLancamentoItemContabil(GLancamentoItemContabil gerLancamentoItemContabil) {
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
