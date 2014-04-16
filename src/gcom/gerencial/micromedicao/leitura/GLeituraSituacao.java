package gcom.gerencial.micromedicao.leitura;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GLeituraSituacao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoLeituraSituacao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GLeituraSituacao(Integer id, String descricaoLeituraSituacao, Short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoLeituraSituacao = descricaoLeituraSituacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GLeituraSituacao() {
    }

    /** minimal constructor */
    public GLeituraSituacao(Integer id, Date ultimaAlteracao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoLeituraSituacao() {
        return this.descricaoLeituraSituacao;
    }

    public void setDescricaoLeituraSituacao(String descricaoLeituraSituacao) {
        this.descricaoLeituraSituacao = descricaoLeituraSituacao;
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
