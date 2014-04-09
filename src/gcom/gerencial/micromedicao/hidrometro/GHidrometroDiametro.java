package gcom.gerencial.micromedicao.hidrometro;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GHidrometroDiametro implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoHidrometroDiametro;

    /** persistent field */
    private String descricaoAbreviadaHidrometroDiametro;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short numeroOrdem;

    /** persistent field */
    private Set resumoHidrometros;

    /** full constructor */
    public GHidrometroDiametro(Integer id, String descricaoHidrometroDiametro, String descricaoAbreviadaHidrometroDiametro, Short indicadorUso, Date ultimaAlteracao, Short numeroOrdem, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroDiametro = descricaoHidrometroDiametro;
        this.descricaoAbreviadaHidrometroDiametro = descricaoAbreviadaHidrometroDiametro;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.numeroOrdem = numeroOrdem;
        this.resumoHidrometros = resumoHidrometros;
    }

    /** default constructor */
    public GHidrometroDiametro() {
    }

    /** minimal constructor */
    public GHidrometroDiametro(Integer id, String descricaoHidrometroDiametro, String descricaoAbreviadaHidrometroDiametro, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroDiametro = descricaoHidrometroDiametro;
        this.descricaoAbreviadaHidrometroDiametro = descricaoAbreviadaHidrometroDiametro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoHidrometroDiametro() {
        return this.descricaoHidrometroDiametro;
    }

    public void setDescricaoHidrometroDiametro(String descricaoHidrometroDiametro) {
        this.descricaoHidrometroDiametro = descricaoHidrometroDiametro;
    }

    public String getDescricaoAbreviadaHidrometroDiametro() {
        return this.descricaoAbreviadaHidrometroDiametro;
    }

    public void setDescricaoAbreviadaHidrometroDiametro(String descricaoAbreviadaHidrometroDiametro) {
        this.descricaoAbreviadaHidrometroDiametro = descricaoAbreviadaHidrometroDiametro;
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

    public Short getNumeroOrdem() {
        return this.numeroOrdem;
    }

    public void setNumeroOrdem(Short numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
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
