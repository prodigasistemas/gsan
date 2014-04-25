package gcom.operacional;


import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SetorAbastecimento extends TabelaAuxiliarAbreviadaTipo {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.operacional.SistemaAbastecimento sistemaAbastecimento;

    /** full constructor */
    public SetorAbastecimento(String descricao, String descricaoAbreviada, short indicadorUso, 
    	Date ultimaAlteracao, gcom.operacional.SistemaAbastecimento sistemaAbastecimento) {
        
    	this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sistemaAbastecimento = sistemaAbastecimento;
    }

    /** default constructor */
    public SetorAbastecimento() {
    }

    /** minimal constructor */
    public SetorAbastecimento(short indicadorUso, Date ultimaAlteracao, 
    	gcom.operacional.SistemaAbastecimento sistemaAbastecimento) {
    	
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sistemaAbastecimento = sistemaAbastecimento;
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

    public gcom.operacional.SistemaAbastecimento getSistemaAbastecimento() {
        return this.sistemaAbastecimento;
    }

    public void setSistemaAbastecimento(gcom.operacional.SistemaAbastecimento sistemaAbastecimento) {
        this.sistemaAbastecimento = sistemaAbastecimento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
   

}
