package gcom.operacional;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ZonaPressao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoZonaPressao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.operacional.DistritoOperacional distritoOperacional;

    /** full constructor */
    public ZonaPressao(Integer id, String descricaoZonaPressao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, gcom.operacional.DistritoOperacional distritoOperacional) {
        this.id = id;
        this.descricaoZonaPressao = descricaoZonaPressao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.distritoOperacional = distritoOperacional;
    }

    /** default constructor */
    public ZonaPressao() {
    }

    /** minimal constructor */
    public ZonaPressao(Integer id, String descricaoZonaPressao, short indicadorUso, Date ultimaAlteracao, gcom.operacional.DistritoOperacional distritoOperacional) {
        this.id = id;
        this.descricaoZonaPressao = descricaoZonaPressao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.distritoOperacional = distritoOperacional;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoZonaPressao() {
        return this.descricaoZonaPressao;
    }

    public void setDescricaoZonaPressao(String descricaoZonaPressao) {
        this.descricaoZonaPressao = descricaoZonaPressao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
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

    public gcom.operacional.DistritoOperacional getDistritoOperacional() {
        return this.distritoOperacional;
    }

    public void setDistritoOperacional(gcom.operacional.DistritoOperacional distritoOperacional) {
        this.distritoOperacional = distritoOperacional;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
