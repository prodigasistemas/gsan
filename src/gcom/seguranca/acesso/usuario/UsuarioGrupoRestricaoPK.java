package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class UsuarioGrupoRestricaoPK extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
   
	private static final int ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL = 100;
	
	/** identifier field */
    private Integer grupoId;

    /** identifier field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL)
    private Integer usuarioId;

    /** identifier field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL)
    private Integer funcionalidadeId;

    /** identifier field */
    private Integer operacaoId;

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[4];
		retorno[0] = "grupoId";
		retorno[1] = "usuarioId";
		retorno[2] = "funcionalidadeId";
		retorno[3] = "operacaoId";
		return retorno;
	}
    
    /** full constructor */
    public UsuarioGrupoRestricaoPK(Integer grupoId, Integer usuarioId, Integer funcionalidadeId, Integer operacaoId) {
        this.grupoId = grupoId;
        this.usuarioId = usuarioId;
        this.funcionalidadeId = funcionalidadeId;
        this.operacaoId = operacaoId;
    }

    /** default constructor */
    public UsuarioGrupoRestricaoPK() {
    }

    public Integer getGrupoId() {
        return this.grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public Integer getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getFuncionalidadeId() {
        return this.funcionalidadeId;
    }

    public void setFuncionalidadeId(Integer funcionalidadeId) {
        this.funcionalidadeId = funcionalidadeId;
    }

    public Integer getOperacaoId() {
        return this.operacaoId;
    }

    public void setOperacaoId(Integer operacaoId) {
        this.operacaoId = operacaoId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("grupoId", getGrupoId())
            .append("usuarioId", getUsuarioId())
            .append("funcionalidadeId", getFuncionalidadeId())
            .append("operacaoId", getOperacaoId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioGrupoRestricaoPK) ) return false;
        UsuarioGrupoRestricaoPK castOther = (UsuarioGrupoRestricaoPK) other;
        return new EqualsBuilder()
            .append(this.getGrupoId(), castOther.getGrupoId())
            .append(this.getUsuarioId(), castOther.getUsuarioId())
            .append(this.getFuncionalidadeId(), castOther.getFuncionalidadeId())
            .append(this.getOperacaoId(), castOther.getOperacaoId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getGrupoId())
            .append(getUsuarioId())
            .append(getFuncionalidadeId())
            .append(getOperacaoId())
            .toHashCode();
    }

	@Override
	public Date getUltimaAlteracao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

}
