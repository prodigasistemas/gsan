package gcom.gerencial.cadastro;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Indicador implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoIndicador;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public Indicador(Integer id, String descricaoIndicador, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoIndicador = descricaoIndicador;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public Indicador() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoIndicador() {
        return this.descricaoIndicador;
    }

    public void setDescricaoIndicador(String descricaoIndicador) {
        this.descricaoIndicador = descricaoIndicador;
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
