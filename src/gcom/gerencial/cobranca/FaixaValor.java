package gcom.gerencial.cobranca;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaixaValor implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;
    
        /** persistent field */
    private Date ultimaAlteracao;
    
    public FaixaValor(){}
    
	public FaixaValor(String descricao, Date ultimaAlteracao) {
		super();
		
		this.descricao = descricao;
		this.ultimaAlteracao = ultimaAlteracao;
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
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
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
