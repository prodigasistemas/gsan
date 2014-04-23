package gcom.cadastro.tarifasocial;

import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialMotivoCarta extends ObjetoGcom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricaoMotivoCarta;
	private String descricaoAbreviada;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getDescricaoMotivoCarta() {
		return descricaoMotivoCarta;
	}

	public void setDescricaoMotivoCarta(String descricaoMotivoCarta) {
		this.descricaoMotivoCarta = descricaoMotivoCarta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

    
}
