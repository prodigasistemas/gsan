package gcom.gerencial.faturamento.debito;

import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GDebitoTipo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoDebitoTipo;

    /** nullable persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal Valorlimite;

    /** persistent field */
    private short indicadorGeracaoAutomatica;

    /** persistent field */
    private short indicadorGeracaoConta;

    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;

    /** persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipo;

    /** full constructor */
    public GDebitoTipo(Integer id, String descricaoDebitoTipo, String descricaoAbreviado, Short indicadorUso, Date ultimaAlteracao, BigDecimal Valorlimite, short indicadorGeracaoAutomatica, short indicadorGeracaoConta, GLancamentoItemContabil gerLancamentoItemContabil, GFinanciamentoTipo gerFinanciamentoTipo) {
        this.id = id;
        this.descricaoDebitoTipo = descricaoDebitoTipo;
        this.descricaoAbreviado = descricaoAbreviado;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.Valorlimite = Valorlimite;
        this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
        this.indicadorGeracaoConta = indicadorGeracaoConta;
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
    }

    /** default constructor */
    public GDebitoTipo() {
    }

    /** minimal constructor */
    public GDebitoTipo(Integer id, Date ultimaAlteracao, short indicadorGeracaoAutomatica, short indicadorGeracaoConta, GLancamentoItemContabil gerLancamentoItemContabil, GFinanciamentoTipo gerFinanciamentoTipo) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
        this.indicadorGeracaoConta = indicadorGeracaoConta;
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoDebitoTipo() {
        return this.descricaoDebitoTipo;
    }

    public void setDescricaoDebitoTipo(String descricaoDebitoTipo) {
        this.descricaoDebitoTipo = descricaoDebitoTipo;
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

    public BigDecimal getValorlimite() {
        return this.Valorlimite;
    }

    public void setValorlimite(BigDecimal Valorlimite) {
        this.Valorlimite = Valorlimite;
    }

    public short getIndicadorGeracaoAutomatica() {
        return this.indicadorGeracaoAutomatica;
    }

    public void setIndicadorGeracaoAutomatica(short indicadorGeracaoAutomatica) {
        this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
    }

    public short getIndicadorGeracaoConta() {
        return this.indicadorGeracaoConta;
    }

    public void setIndicadorGeracaoConta(short indicadorGeracaoConta) {
        this.indicadorGeracaoConta = indicadorGeracaoConta;
    }

    public GLancamentoItemContabil getGerLancamentoItemContabil() {
        return this.gerLancamentoItemContabil;
    }

    public void setGerLancamentoItemContabil(GLancamentoItemContabil gerLancamentoItemContabil) {
        this.gerLancamentoItemContabil = gerLancamentoItemContabil;
    }

    public GFinanciamentoTipo getGerFinanciamentoTipo() {
        return this.gerFinanciamentoTipo;
    }

    public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
