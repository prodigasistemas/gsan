package gcom.seguranca.transacao;


import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaAtualizacaoCadastralSituacao {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Short indicadorUso;
	
    private Date ultimaAlteracao;
	

	public TabelaAtualizacaoCadastralSituacao(Integer id,String descricao, Short indicadorUso, Date ultimaAlteracao) {
		this.id = id;
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
    public TabelaAtualizacaoCadastralSituacao() {
    }

 
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof TabelaAtualizacaoCadastral))
			return false;
		TabelaAtualizacaoCadastralSituacao castOther = (TabelaAtualizacaoCadastralSituacao) other;
		return new EqualsBuilder().append(this.getId(),
				castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
	
	 public String[] retornaCamposChavePrimaria(){
			String[] retorno = new String[1];
			retorno[0] = "id";
			return retorno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
