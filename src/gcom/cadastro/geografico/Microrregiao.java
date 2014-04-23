package gcom.cadastro.geografico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Microrregiao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String nome;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cadastro.geografico.Regiao regiao;

    /** full constructor */
    public Microrregiao(String nome, Short indicadorUso, Date ultimaAlteracao,
            gcom.cadastro.geografico.Regiao regiao) {
        this.nome = nome;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.regiao = regiao;
    }

    /** default constructor */
    public Microrregiao() {
    }

    /** minimal constructor */
    public Microrregiao(gcom.cadastro.geografico.Regiao regiao) {
        this.regiao = regiao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public gcom.cadastro.geografico.Regiao getRegiao() {
        return this.regiao;
    }

    public void setRegiao(gcom.cadastro.geografico.Regiao regiao) {
        this.regiao = regiao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
