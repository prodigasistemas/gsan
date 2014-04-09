package gcom.gerencial.financeiro.lancamento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GLancamentoItemContabil implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String grupocontabil;

    /** persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Short sequenciaImpressao;

    /** persistent field */
    private Date ultimaAlteracao;


    /** full constructor */
    public GLancamentoItemContabil(String grupocontabil, String descricaoAbreviado, Short sequenciaImpressao, Date ultimaAlteracao) {
        this.grupocontabil = grupocontabil;
        this.descricaoAbreviado = descricaoAbreviado;
        this.sequenciaImpressao = sequenciaImpressao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GLancamentoItemContabil() {
    }

    /** minimal constructor */
    public GLancamentoItemContabil(String grupocontabil, String descricaoAbreviado, Date ultimaAlteracao) {
        this.grupocontabil = grupocontabil;
        this.descricaoAbreviado = descricaoAbreviado;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrupocontabil() {
        return this.grupocontabil;
    }

    public void setGrupocontabil(String grupocontabil) {
        this.grupocontabil = grupocontabil;
    }

    public String getDescricaoAbreviado() {
        return this.descricaoAbreviado;
    }

    public void setDescricaoAbreviado(String descricaoAbreviado) {
        this.descricaoAbreviado = descricaoAbreviado;
    }

    public Short getSequenciaImpressao() {
        return this.sequenciaImpressao;
    }

    public void setSequenciaImpressao(Short sequenciaImpressao) {
        this.sequenciaImpressao = sequenciaImpressao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
