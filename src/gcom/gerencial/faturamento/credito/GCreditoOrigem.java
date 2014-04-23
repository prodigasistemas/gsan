package gcom.gerencial.faturamento.credito;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GCreditoOrigem implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoCreditoOrigem;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    @SuppressWarnings("unused")
	private Set unResumoArrecadacaoCreditos;

    /** persistent field */
    @SuppressWarnings("unused")
	private Set unResumoFaturamentoCreditos;

    /** full constructor */
    public GCreditoOrigem(String descricaoCreditoOrigem, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao, Set unResumoArrecadacaoCreditos, Set unResumoFaturamentoCreditos) {
        this.descricaoCreditoOrigem = descricaoCreditoOrigem;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GCreditoOrigem() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoCreditoOrigem() {
        return this.descricaoCreditoOrigem;
    }

    public void setDescricaoCreditoOrigem(String descricaoCreditoOrigem) {
        this.descricaoCreditoOrigem = descricaoCreditoOrigem;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
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


    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
