package gcom.gerencial.micromedicao.hidrometro;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GHidrometroClasseMetrologica implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoHidrometroClasseMetrologica;

    /** nullable persistent field */
    private String descricaoAbreviadaHidrometroClasseMetrologica;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GHidrometroClasseMetrologica(Integer id, String descricaoHidrometroClasseMetrologica, String descricaoAbreviadaHidrometroClasseMetrologica, Short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoHidrometroClasseMetrologica = descricaoHidrometroClasseMetrologica;
        this.descricaoAbreviadaHidrometroClasseMetrologica = descricaoAbreviadaHidrometroClasseMetrologica;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GHidrometroClasseMetrologica() {
    }

    /** minimal constructor */
    public GHidrometroClasseMetrologica(Integer id, Date ultimaAlteracao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoHidrometroClasseMetrologica() {
        return this.descricaoHidrometroClasseMetrologica;
    }

    public void setDescricaoHidrometroClasseMetrologica(String descricaoHidrometroClasseMetrologica) {
        this.descricaoHidrometroClasseMetrologica = descricaoHidrometroClasseMetrologica;
    }

    public String getDescricaoAbreviadaHidrometroClasseMetrologica() {
        return this.descricaoAbreviadaHidrometroClasseMetrologica;
    }

    public void setDescricaoAbreviadaHidrometroClasseMetrologica(String descricaoAbreviadaHidrometroClasseMetrologica) {
        this.descricaoAbreviadaHidrometroClasseMetrologica = descricaoAbreviadaHidrometroClasseMetrologica;
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
