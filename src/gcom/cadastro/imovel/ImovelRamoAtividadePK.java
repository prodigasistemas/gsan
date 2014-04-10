package gcom.cadastro.imovel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import gcom.cadastro.cliente.RamoAtividade;
import gcom.interceptor.ObjetoGcom;

public class ImovelRamoAtividadePK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    private gcom.cadastro.imovel.Imovel imovel;

    private RamoAtividade ramo_atividade;

    public ImovelRamoAtividadePK(gcom.cadastro.imovel.Imovel imovel,
            RamoAtividade ramo_atividade) {
        this.imovel = imovel;
        this.ramo_atividade = ramo_atividade;
    }

    public ImovelRamoAtividadePK() {
    }
    
    public ImovelRamoAtividadePK(int idImovel, int idRamoAtividade) {
    	this.imovel = new Imovel(new Integer(idImovel));
    	this.ramo_atividade = new RamoAtividade(new Integer(idRamoAtividade));
    	
    }
    

    public gcom.cadastro.imovel.Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
        this.imovel = imovel;
    } 
    
    

    public RamoAtividade getRamo_atividade() {
		return ramo_atividade;
	}

	public void setRamo_atividade(RamoAtividade ramo_atividade) {
		this.ramo_atividade = ramo_atividade;
	}

	public String toString() {
        return new ToStringBuilder(this).append("imovel", getImovel()).append(
                "ramo_atividade", this.getRamo_atividade()).toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if (!(other instanceof ImovelSubcategoriaPK))
            return false;
        ImovelRamoAtividadePK castOther = (ImovelRamoAtividadePK) other;
        return new EqualsBuilder().append(this.getImovel(),
                castOther.getImovel()).append(this.getRamo_atividade(),
                castOther.getRamo_atividade()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getImovel()).append(
                this.getRamo_atividade()).toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "ramo_atividade";
 		retorno[1] = "imovel";
 		return retorno;		
	}
	
}
