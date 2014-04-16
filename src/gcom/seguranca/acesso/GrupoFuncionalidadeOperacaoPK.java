package gcom.seguranca.acesso;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GrupoFuncionalidadeOperacaoPK extends ObjetoGcom  {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer grupoId;

    /** identifier field */
    private Integer operacaoId;

    /** identifier field */
    private Integer funcionalidadeId;

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[3];
		retorno[0] = "grupoId";
		retorno[1] = "funcionalidadeId";
		retorno[2] = "operacaoId";
		return retorno;
	}
    
    /** full constructor */
    public GrupoFuncionalidadeOperacaoPK(Integer grupoId, Integer operacaoId, Integer funcionalidadeId) {
        this.grupoId = grupoId;
        this.operacaoId = operacaoId;
        this.funcionalidadeId = funcionalidadeId;
    }

    /** default constructor */
    public GrupoFuncionalidadeOperacaoPK() {
    }

    public Integer getGrupoId() {
        return this.grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public Integer getOperacaoId() {
        return this.operacaoId;
    }

    public void setOperacaoId(Integer operacaoId) {
        this.operacaoId = operacaoId;
    }

    public Integer getFuncionalidadeId() {
        return this.funcionalidadeId;
    }

    public void setFuncionalidadeId(Integer funcionalidadeId) {
        this.funcionalidadeId = funcionalidadeId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("grupoId", getGrupoId())
            .append("operacaoId", getOperacaoId())
            .append("funcionalidadeId", getFuncionalidadeId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GrupoFuncionalidadeOperacaoPK) ) return false;
        GrupoFuncionalidadeOperacaoPK castOther = (GrupoFuncionalidadeOperacaoPK) other;
        return new EqualsBuilder()
            .append(this.getGrupoId(), castOther.getGrupoId())
            .append(this.getOperacaoId(), castOther.getOperacaoId())
            .append(this.getFuncionalidadeId(), castOther.getFuncionalidadeId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getGrupoId())
            .append(getOperacaoId())
            .append(getFuncionalidadeId())
            .toHashCode();
    }

}
