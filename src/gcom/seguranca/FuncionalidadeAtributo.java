package gcom.seguranca;

import gcom.seguranca.acesso.Funcionalidade;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FuncionalidadeAtributo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */  
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.seguranca.Atributo atributo;
    
    private Funcionalidade funcionalidade;

    /** full constructor */
    public FuncionalidadeAtributo(Integer id, Date ultimaAlteracao, gcom.seguranca.Atributo atributo,Funcionalidade funcionalidade) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.atributo = atributo;
        this.funcionalidade = funcionalidade;
    }

    /** default constructor */
    public FuncionalidadeAtributo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.seguranca.Atributo getAtributo() {
        return this.atributo;
    }

    public void setAtributo(gcom.seguranca.Atributo atributo) {
        this.atributo = atributo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

}
