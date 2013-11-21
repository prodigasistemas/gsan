package gcom.seguranca;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Atributo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nomeAtributo;

    /** persistent field */
    private Short indicadorVisita;

    /** persistent field */
    private Short numeroOrdemEmissao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.seguranca.AtributoGrupo atributoGrupo;

    /** persistent field */
    private Set funcionalidadeAtributos;

    public final static Integer NOTIFICACAO_VISITA = new Integer(24);

    
    /** full constructor */
    public Atributo(Integer id, String nomeAtributo, Short indicadorVisita, Short numeroOrdemEmissao, Date ultimaAlteracao, gcom.seguranca.AtributoGrupo atributoGrupo, Set funcionalidadeAtributos) {
        this.id = id;
        this.nomeAtributo = nomeAtributo;
        this.indicadorVisita = indicadorVisita;
        this.numeroOrdemEmissao = numeroOrdemEmissao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.atributoGrupo = atributoGrupo;
        this.funcionalidadeAtributos = funcionalidadeAtributos;
    }

    /** default constructor */
    public Atributo() {
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public gcom.seguranca.AtributoGrupo getAtributoGrupo() {
		return atributoGrupo;
	}

	public void setAtributoGrupo(gcom.seguranca.AtributoGrupo atributoGrupo) {
		this.atributoGrupo = atributoGrupo;
	}

	public Set getFuncionalidadeAtributos() {
		return funcionalidadeAtributos;
	}

	public void setFuncionalidadeAtributos(Set funcionalidadeAtributos) {
		this.funcionalidadeAtributos = funcionalidadeAtributos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorVisita() {
		return indicadorVisita;
	}

	public void setIndicadorVisita(Short indicadorVisita) {
		this.indicadorVisita = indicadorVisita;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public Short getNumeroOrdemEmissao() {
		return numeroOrdemEmissao;
	}

	public void setNumeroOrdemEmissao(Short numeroOrdemEmissao) {
		this.numeroOrdemEmissao = numeroOrdemEmissao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
