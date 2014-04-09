package gcom.gerencial.cobranca;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GDocumentoTipo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoDocumentoTipo;

    /** nullable persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Short indicadorPagavel;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set unResumoArrecadacao;

    /** full constructor */
    public GDocumentoTipo(String descricaoDocumentoTipo, String descricaoAbreviado, Short indicadorPagavel, Short indicadorUso, Date ultimaAlteracao, Set unResumoArrecadacao) {
        this.descricaoDocumentoTipo = descricaoDocumentoTipo;
        this.descricaoAbreviado = descricaoAbreviado;
        this.indicadorPagavel = indicadorPagavel;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoArrecadacao = unResumoArrecadacao;
    }

    /** default constructor */
    public GDocumentoTipo() {
    }

    /** minimal constructor */
    public GDocumentoTipo(Set unResumoArrecadacao) {
        this.unResumoArrecadacao = unResumoArrecadacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoDocumentoTipo() {
        return this.descricaoDocumentoTipo;
    }

    public void setDescricaoDocumentoTipo(String descricaoDocumentoTipo) {
        this.descricaoDocumentoTipo = descricaoDocumentoTipo;
    }

    public String getDescricaoAbreviado() {
        return this.descricaoAbreviado;
    }

    public void setDescricaoAbreviado(String descricaoAbreviado) {
        this.descricaoAbreviado = descricaoAbreviado;
    }

    public Short getIndicadorPagavel() {
        return this.indicadorPagavel;
    }

    public void setIndicadorPagavel(Short indicadorPagavel) {
        this.indicadorPagavel = indicadorPagavel;
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

   

    public Set getUnResumoArrecadacao() {
		return unResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		this.unResumoArrecadacao = unResumoArrecadacao;
	}


    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
