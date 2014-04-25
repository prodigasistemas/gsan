package gcom.operacional;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Bacia extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.operacional.SistemaEsgoto sistemaEsgoto;

    /** full constructor */
    public Bacia(String descricao, Short indicadorUso, Date ultimaAlteracao,
            gcom.operacional.SistemaEsgoto sistemaEsgoto) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sistemaEsgoto = sistemaEsgoto;
    }

    /** default constructor */
    public Bacia() {
    }

    /** minimal constructor */
    public Bacia(gcom.operacional.SistemaEsgoto sistemaEsgoto) {
        this.sistemaEsgoto = sistemaEsgoto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public gcom.operacional.SistemaEsgoto getSistemaEsgoto() {
        return this.sistemaEsgoto;
    }

    public void setSistemaEsgoto(gcom.operacional.SistemaEsgoto sistemaEsgoto) {
        this.sistemaEsgoto = sistemaEsgoto;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
