package gcom.gerencial.faturamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GConsumoTarifa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoConsumoTarifa;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GConsumoTarifa(Integer id, String descricaoConsumoTarifa, Short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoConsumoTarifa = descricaoConsumoTarifa;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GConsumoTarifa() {
    }

    /** minimal constructor */
    public GConsumoTarifa(Integer id, Date ultimaAlteracao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoConsumoTarifa() {
        return this.descricaoConsumoTarifa;
    }

    public void setDescricaoConsumoTarifa(String descricaoConsumoTarifa) {
        this.descricaoConsumoTarifa = descricaoConsumoTarifa;
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
