package gcom.seguranca;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtributoGrupo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoAtributoGrupo;

    /** persistent field */
    private Short numeroOrdemEmissao;

    /** persistent field */
    private Short indicadorImovel;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set atributos;

    public final static Integer ATRIBUTOS_DO_PROPRIETARIO = new Integer(1);
    public final static Integer ATRIBUTOS_DO_USUARIO = new Integer(2);
    public final static Integer ATRIBUTOS_DO_IMOVEL_E_DA_LIGACAO = new Integer(3);
    public final static Integer VISITA_AO_IMOVEL = new Integer(4);
    
    /** full constructor */
    public AtributoGrupo(Integer id, String descricaoAtributoGrupo, Short numeroOrdemEmissao, Short indicadorImovel, Date ultimaAlteracao, Set atributos) {
        this.id = id;
        this.descricaoAtributoGrupo = descricaoAtributoGrupo;
        this.numeroOrdemEmissao = numeroOrdemEmissao;
        this.indicadorImovel = indicadorImovel;
        this.ultimaAlteracao = ultimaAlteracao;
        this.atributos = atributos;
    }

    /** default constructor */
    public AtributoGrupo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoAtributoGrupo() {
        return this.descricaoAtributoGrupo;
    }

    public void setDescricaoAtributoGrupo(String descricaoAtributoGrupo) {
        this.descricaoAtributoGrupo = descricaoAtributoGrupo;
    }

    public Short getIndicadorImovel() {
		return indicadorImovel;
	}

	public void setIndicadorImovel(Short indicadorImovel) {
		this.indicadorImovel = indicadorImovel;
	}

	public Short getNumeroOrdemEmissao() {
		return numeroOrdemEmissao;
	}

	public void setNumeroOrdemEmissao(Short numeroOrdemEmissao) {
		this.numeroOrdemEmissao = numeroOrdemEmissao;
	}

	public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Set getAtributos() {
        return this.atributos;
    }

    public void setAtributos(Set atributos) {
        this.atributos = atributos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
