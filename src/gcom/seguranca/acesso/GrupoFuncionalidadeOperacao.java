package gcom.seguranca.acesso;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class GrupoFuncionalidadeOperacao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    
	private static final int ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL = 100;
	
	/** identifier field */
    private gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK comp_id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL)
    private gcom.seguranca.acesso.Funcionalidade funcionalidade;

    /** nullable persistent field */
    private gcom.seguranca.acesso.Operacao operacao;

    /** nullable persistent field */
    private gcom.seguranca.acesso.Grupo grupo;

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
    
    /** full constructor */
    public GrupoFuncionalidadeOperacao(gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK comp_id, gcom.seguranca.acesso.Funcionalidade funcionalidade, gcom.seguranca.acesso.Operacao operacao, gcom.seguranca.acesso.Grupo grupo) {
        this.comp_id = comp_id;
        this.funcionalidade = funcionalidade;
        this.operacao = operacao;
        this.grupo = grupo;
    }

    /** default constructor */
    public GrupoFuncionalidadeOperacao() {
    }

    /** minimal constructor */
    public GrupoFuncionalidadeOperacao(gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.Funcionalidade getFuncionalidade() {
        return this.funcionalidade;
    }

    public void setFuncionalidade(gcom.seguranca.acesso.Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public gcom.seguranca.acesso.Operacao getOperacao() {
        return this.operacao;
    }

    public void setOperacao(gcom.seguranca.acesso.Operacao operacao) {
        this.operacao = operacao;
    }

    public gcom.seguranca.acesso.Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(gcom.seguranca.acesso.Grupo grupo) {
        this.grupo = grupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GrupoFuncionalidadeOperacao) ) return false;
        GrupoFuncionalidadeOperacao castOther = (GrupoFuncionalidadeOperacao) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	@Override
	public Date getUltimaAlteracao() {
		
		return null;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		
		
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

}
