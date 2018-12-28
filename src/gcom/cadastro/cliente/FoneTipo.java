package gcom.cadastro.cliente;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FoneTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer RESIDENCIAL = 1;
	public static final Integer COMERCIAL = 2;
	public static final Integer CELULAR = 3;
	public static final Integer FAX = 4;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private String descricaoComId;

    public FoneTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public FoneTipo() {
    }

    public FoneTipo(Integer idFoneTipo) {
    	this.id = idFoneTipo;
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

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
}
