package gcom.gerencial.faturamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GFaturamentoGrupo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoFaturamentoGrupo;

    /** persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Short diaVencimento;

    /** persistent field */
    private short indicadorVencimentoMesFatura;

    /** full constructor */
    public GFaturamentoGrupo(Integer id, String descricaoFaturamentoGrupo, String descricaoAbreviado, Short indicadorUso, Date ultimaAlteracao, Integer anoMesReferencia, Short diaVencimento, short indicadorVencimentoMesFatura) {
        this.id = id;
        this.descricaoFaturamentoGrupo = descricaoFaturamentoGrupo;
        this.descricaoAbreviado = descricaoAbreviado;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.anoMesReferencia = anoMesReferencia;
        this.diaVencimento = diaVencimento;
        this.indicadorVencimentoMesFatura = indicadorVencimentoMesFatura;
    }

    /** default constructor */
    public GFaturamentoGrupo() {
    }

    /** minimal constructor */
    public GFaturamentoGrupo(Integer id, String descricaoFaturamentoGrupo, String descricaoAbreviado, Date ultimaAlteracao, short indicadorVencimentoMesFatura) {
        this.id = id;
        this.descricaoFaturamentoGrupo = descricaoFaturamentoGrupo;
        this.descricaoAbreviado = descricaoAbreviado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorVencimentoMesFatura = indicadorVencimentoMesFatura;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoFaturamentoGrupo() {
        return this.descricaoFaturamentoGrupo;
    }

    public void setDescricaoFaturamentoGrupo(String descricaoFaturamentoGrupo) {
        this.descricaoFaturamentoGrupo = descricaoFaturamentoGrupo;
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

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Short getDiaVencimento() {
        return this.diaVencimento;
    }

    public void setDiaVencimento(Short diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public short getIndicadorVencimentoMesFatura() {
        return this.indicadorVencimentoMesFatura;
    }

    public void setIndicadorVencimentoMesFatura(short indicadorVencimentoMesFatura) {
        this.indicadorVencimentoMesFatura = indicadorVencimentoMesFatura;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
