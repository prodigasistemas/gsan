package gcom.gerencial.operacional;

import java.io.Serializable;
import java.util.Date;

public class GDistritoOperacional implements Serializable {


	private static final long serialVersionUID = 1L;

    /** identifier field */

    private Integer id;
    
    private String descricao;
    
    private String descricaoAbreviada;
    
    private Short indicadorUso;
    
    /** persistent field */
    private Date ultimaAlteracao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
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

	/**
	 * Construtor de GDistritoOperacional 
	 * 
	 * @param id
	 * @param descricao
	 * @param descricaoAbreviada
	 * @param indicadorUso
	 * @param ultimaAlteracao
	 */
	public GDistritoOperacional(Integer id, String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		this.id = id;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Construtor de GDistritoOperacional 
	 * 
	 */
	public GDistritoOperacional() {
		
	}
	

}
