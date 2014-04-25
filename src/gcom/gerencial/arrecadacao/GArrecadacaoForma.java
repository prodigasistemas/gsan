package gcom.gerencial.arrecadacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GArrecadacaoForma implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String codigoArrecadacaoForma;

    /** nullable persistent field */
    private String descricaoArrecadacaoForma;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GArrecadacaoForma(Integer id, String codigoArrecadacaoForma, String descricaoArrecadacaoForma, Date ultimaAlteracao) {
        this.id = id;
        this.codigoArrecadacaoForma = codigoArrecadacaoForma;
        this.descricaoArrecadacaoForma = descricaoArrecadacaoForma;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GArrecadacaoForma() {
    }

    /** minimal constructor */
    public GArrecadacaoForma(Integer id, Date ultimaAlteracao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoArrecadacaoForma() {
        return this.codigoArrecadacaoForma;
    }

    public void setCodigoArrecadacaoForma(String codigoArrecadacaoForma) {
        this.codigoArrecadacaoForma = codigoArrecadacaoForma;
    }

    public String getDescricaoArrecadacaoForma() {
        return this.descricaoArrecadacaoForma;
    }

    public void setDescricaoArrecadacaoForma(String descricaoArrecadacaoForma) {
        this.descricaoArrecadacaoForma = descricaoArrecadacaoForma;
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
