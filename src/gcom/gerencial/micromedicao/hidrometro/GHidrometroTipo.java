package gcom.gerencial.micromedicao.hidrometro;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GHidrometroTipo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoHidrometroTipo;

    /** persistent field */
    private String descricaoAbreviadaHidrometroTipo;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set resumoHidrometros;

    /** full constructor */
    public GHidrometroTipo(Integer id, String descricaoHidrometroTipo, String descricaoAbreviadaHidrometroTipo, Short indicadorUso, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroTipo = descricaoHidrometroTipo;
        this.descricaoAbreviadaHidrometroTipo = descricaoAbreviadaHidrometroTipo;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    /** default constructor */
    public GHidrometroTipo() {
    }

    /** minimal constructor */
    public GHidrometroTipo(Integer id, String descricaoHidrometroTipo, String descricaoAbreviadaHidrometroTipo, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroTipo = descricaoHidrometroTipo;
        this.descricaoAbreviadaHidrometroTipo = descricaoAbreviadaHidrometroTipo;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoHidrometroTipo() {
        return this.descricaoHidrometroTipo;
    }

    public void setDescricaoHidrometroTipo(String descricaoHidrometroTipo) {
        this.descricaoHidrometroTipo = descricaoHidrometroTipo;
    }

    public String getDescricaoAbreviadaHidrometroTipo() {
        return this.descricaoAbreviadaHidrometroTipo;
    }

    public void setDescricaoAbreviadaHidrometroTipo(String descricaoAbreviadaHidrometroTipo) {
        this.descricaoAbreviadaHidrometroTipo = descricaoAbreviadaHidrometroTipo;
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
