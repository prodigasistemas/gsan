package gcom.faturamento;

import java.util.Date;

public class ImpostoTipo {

	public final static Integer IR = new Integer(1);
	public final static Integer CSLL = new Integer(2);
	public final static Integer COFINS = new Integer(3);
	public final static Integer PIS_PASEP = new Integer(4);
	
    private Integer id;
    private String descricaoImposto;
    private String descricaoAbreviada;
    private Short indicadorUso;
    private Date ultimaAlteracao;

    public ImpostoTipo() {
    }
    
    public ImpostoTipo(Integer id) {
    	this.id = id;
    }
    
    public ImpostoTipo(Integer id, String descricaoImposto, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
    	super();
    	this.id = id;
    	this.descricaoImposto = descricaoImposto;
    	this.descricaoAbreviada = descricaoAbreviada;
    	this.indicadorUso = indicadorUso;
    	this.ultimaAlteracao = ultimaAlteracao;
    }
    
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getDescricaoImposto() {
		return descricaoImposto;
	}

	public void setDescricaoImposto(String descricaoImposto) {
		this.descricaoImposto = descricaoImposto;
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