package gcom.gerencial.micromedicao.medicao;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GMedicaoTipo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set unResumoLeituraAnormalidades;

    /** full constructor */
    public GMedicaoTipo(Integer id, String descricao, Short indicadorUso, Date ultimaAlteracao, Set unResumoLeituraAnormalidades) {
        this.id = id;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoLeituraAnormalidades = unResumoLeituraAnormalidades;
    }

    /** default constructor */
    public GMedicaoTipo() {
    }

    /** minimal constructor */
    public GMedicaoTipo(Integer id, Date ultimaAlteracao, Set unResumoLeituraAnormalidades) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoLeituraAnormalidades = unResumoLeituraAnormalidades;
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

    public Set getUnResumoLeituraAnormalidades() {
        return this.unResumoLeituraAnormalidades;
    }

    public void setUnResumoLeituraAnormalidades(Set unResumoLeituraAnormalidades) {
        this.unResumoLeituraAnormalidades = unResumoLeituraAnormalidades;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
