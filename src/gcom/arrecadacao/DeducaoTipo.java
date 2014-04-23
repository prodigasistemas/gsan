package gcom.arrecadacao;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DeducaoTipo extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Método que retorna os campos das chaves primarias
	 *
	 * @return
	 */
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"id"};
		return retorno;
	}

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoDeducaoTipo;

    /** nullable persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    public final static Integer CPMF = new Integer(1);
    
    public final static Integer TARIFA = new Integer(2);
    
    public final static Integer ENCONTRO_CONTAS = new Integer(3);

    /** full constructor */
    public DeducaoTipo(String descricaoDeducaoTipo, String descricaoAbreviado, Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoDeducaoTipo = descricaoDeducaoTipo;
        this.descricaoAbreviado = descricaoAbreviado;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public DeducaoTipo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoDeducaoTipo() {
        return this.descricaoDeducaoTipo;
    }

    public void setDescricaoDeducaoTipo(String descricaoDeducaoTipo) {
        this.descricaoDeducaoTipo = descricaoDeducaoTipo;
    }

    public String getDescricaoAbreviado() {
        return this.descricaoAbreviado;
    }

    public void setDescricaoAbreviado(String descricaoAbreviado) {
        this.descricaoAbreviado = descricaoAbreviado;
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
