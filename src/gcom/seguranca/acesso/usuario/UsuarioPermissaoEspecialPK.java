package gcom.seguranca.acesso.usuario;

import java.util.Date;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class UsuarioPermissaoEspecialPK extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer usuarioId;

    /** identifier field */
    private Integer permissaoEspecialId;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "usuarioId";
		retorno[1] = "permissaoEspecialId";
		return retorno;
	}
    
    /** full constructor */
    public UsuarioPermissaoEspecialPK(Integer usuarioId, Integer permissaoEspecialId) {
        this.usuarioId = usuarioId;
        this.permissaoEspecialId = permissaoEspecialId;
    }

    /** default constructor */
    public UsuarioPermissaoEspecialPK() {
    }

    public Integer getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getPermissaoEspecialId() {
        return this.permissaoEspecialId;
    }

    public void setPermissaoEspecialId(Integer permissaoEspecialId) {
        this.permissaoEspecialId = permissaoEspecialId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("usuarioId", getUsuarioId())
            .append("permissaoEspecialId", getPermissaoEspecialId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioPermissaoEspecialPK) ) return false;
        UsuarioPermissaoEspecialPK castOther = (UsuarioPermissaoEspecialPK) other;
        return new EqualsBuilder()
            .append(this.getUsuarioId(), castOther.getUsuarioId())
            .append(this.getPermissaoEspecialId(), castOther.getPermissaoEspecialId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUsuarioId())
            .append(getPermissaoEspecialId())
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
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"permissaoEspecialId"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Id Permissao"};
		return labels;		
	}

}
