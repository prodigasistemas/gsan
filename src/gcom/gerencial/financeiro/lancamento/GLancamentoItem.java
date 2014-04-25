package gcom.gerencial.financeiro.lancamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GLancamentoItem implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short indicadorItemContabil;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GLancamentoItem(Integer id, String descricao, String descricaoAbreviada, Short indicadorItemContabil, Date ultimaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorItemContabil = indicadorItemContabil;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GLancamentoItem() {
    }

    /** minimal constructor */
    public GLancamentoItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Short getIndicadorItemContabil() {
        return this.indicadorItemContabil;
    }

    public void setIndicadorItemContabil(Short indicadorItemContabil) {
        this.indicadorItemContabil = indicadorItemContabil;
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
