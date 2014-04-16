package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class MotivoRejeicao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    private String descricao;

    /** persistent field */
    private Date ultimaAlteracao;
   
    public MotivoRejeicao(String descricao, Date ultimaAlteracao) {
    	
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public MotivoRejeicao() {
    	
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
    
}
