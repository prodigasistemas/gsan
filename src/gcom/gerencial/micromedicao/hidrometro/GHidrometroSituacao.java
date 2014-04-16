package gcom.gerencial.micromedicao.hidrometro;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GHidrometroSituacao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoHidrometroSituacao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set resumoHidrometros;

    /** full constructor */
    public GHidrometroSituacao(Integer id, String descricaoHidrometroSituacao, Short indicadorUso, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroSituacao = descricaoHidrometroSituacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    /** default constructor */
    public GHidrometroSituacao() {
    }

    /** minimal constructor */
    public GHidrometroSituacao(Integer id, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoHidrometroSituacao() {
        return this.descricaoHidrometroSituacao;
    }

    public void setDescricaoHidrometroSituacao(String descricaoHidrometroSituacao) {
        this.descricaoHidrometroSituacao = descricaoHidrometroSituacao;
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

    public Set getResumoHidrometros() {
        return this.resumoHidrometros;
    }

    public void setResumoHidrometros(Set resumoHidrometros) {
        this.resumoHidrometros = resumoHidrometros;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
