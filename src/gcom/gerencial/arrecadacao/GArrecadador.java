package gcom.gerencial.arrecadacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GArrecadador implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String numeroInscricaoEstadual;

    /** persistent field */
    private short codigoAgente;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short indicadorUso;

    /** full constructor */
    public GArrecadador(Integer id, String numeroInscricaoEstadual, short codigoAgente, Date ultimaAlteracao, short indicadorUso) {
        this.id = id;
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
        this.codigoAgente = codigoAgente;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
    }

    /** default constructor */
    public GArrecadador() {
    }

    /** minimal constructor */
    public GArrecadador(Integer id, short codigoAgente, Date ultimaAlteracao, short indicadorUso) {
        this.id = id;
        this.codigoAgente = codigoAgente;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroInscricaoEstadual() {
        return this.numeroInscricaoEstadual;
    }

    public void setNumeroInscricaoEstadual(String numeroInscricaoEstadual) {
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
    }

    public short getCodigoAgente() {
        return this.codigoAgente;
    }

    public void setCodigoAgente(short codigoAgente) {
        this.codigoAgente = codigoAgente;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
