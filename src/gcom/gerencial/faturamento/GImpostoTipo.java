package gcom.gerencial.faturamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GImpostoTipo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoImposto;

    /** persistent field */
    private String descricaoAbreviadaImposto;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GImpostoTipo(Integer id, String descricaoImposto, String descricaoAbreviadaImposto, short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoImposto = descricaoImposto;
        this.descricaoAbreviadaImposto = descricaoAbreviadaImposto;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GImpostoTipo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoImposto() {
        return this.descricaoImposto;
    }

    public void setDescricaoImposto(String descricaoImposto) {
        this.descricaoImposto = descricaoImposto;
    }

    public String getDescricaoAbreviadaImposto() {
        return this.descricaoAbreviadaImposto;
    }

    public void setDescricaoAbreviadaImposto(String descricaoAbreviadaImposto) {
        this.descricaoAbreviadaImposto = descricaoAbreviadaImposto;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
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
