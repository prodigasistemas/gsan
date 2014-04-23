package gcom.gerencial.micromedicao.hidrometro;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GHidrometroMarca implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoHidrometroMarca;

    /** persistent field */
    private String descricaoAbreviadaHidrometroMarca;

    /** persistent field */
    private short numeroDiaRevisao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set resumoHidrometros;

    /** full constructor */
    public GHidrometroMarca(Integer id, String descricaoHidrometroMarca, String descricaoAbreviadaHidrometroMarca, short numeroDiaRevisao, Short indicadorUso, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroMarca = descricaoHidrometroMarca;
        this.descricaoAbreviadaHidrometroMarca = descricaoAbreviadaHidrometroMarca;
        this.numeroDiaRevisao = numeroDiaRevisao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    /** default constructor */
    public GHidrometroMarca() {
    }

    /** minimal constructor */
    public GHidrometroMarca(Integer id, String descricaoHidrometroMarca, String descricaoAbreviadaHidrometroMarca, short numeroDiaRevisao, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroMarca = descricaoHidrometroMarca;
        this.descricaoAbreviadaHidrometroMarca = descricaoAbreviadaHidrometroMarca;
        this.numeroDiaRevisao = numeroDiaRevisao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoHidrometroMarca() {
        return this.descricaoHidrometroMarca;
    }

    public void setDescricaoHidrometroMarca(String descricaoHidrometroMarca) {
        this.descricaoHidrometroMarca = descricaoHidrometroMarca;
    }

    public String getDescricaoAbreviadaHidrometroMarca() {
        return this.descricaoAbreviadaHidrometroMarca;
    }

    public void setDescricaoAbreviadaHidrometroMarca(String descricaoAbreviadaHidrometroMarca) {
        this.descricaoAbreviadaHidrometroMarca = descricaoAbreviadaHidrometroMarca;
    }

    public short getNumeroDiaRevisao() {
        return this.numeroDiaRevisao;
    }

    public void setNumeroDiaRevisao(short numeroDiaRevisao) {
        this.numeroDiaRevisao = numeroDiaRevisao;
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
