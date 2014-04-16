package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ImovelSubcategoriaPK extends ObjetoGcom{
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.cadastro.imovel.Imovel imovel;

    /** identifier field */
    private gcom.cadastro.imovel.Subcategoria subcategoria;

    /** full constructor */
    public ImovelSubcategoriaPK(gcom.cadastro.imovel.Imovel imovel,
            gcom.cadastro.imovel.Subcategoria subcategoria) {
        this.imovel = imovel;
        this.subcategoria = subcategoria;
    }
    
    public ImovelSubcategoriaPK(int matriculaImovel, Integer idSubcategoria) {
    	Imovel imovel = new Imovel(new Integer(matriculaImovel));
		Subcategoria subcategoria = new Subcategoria(idSubcategoria);
		
        this.imovel = imovel;
        this.subcategoria = subcategoria;
    }

    /** default constructor */
    public ImovelSubcategoriaPK() {
    }

    public gcom.cadastro.imovel.Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
        this.imovel = imovel;
    }

    public gcom.cadastro.imovel.Subcategoria getSubcategoria() {
        return this.subcategoria;
    }

    public void setSubcategoria(gcom.cadastro.imovel.Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String toString() {
        return new ToStringBuilder(this).append("imovel", getImovel()).append(
                "subcategoria", getSubcategoria()).toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if (!(other instanceof ImovelSubcategoriaPK))
            return false;
        ImovelSubcategoriaPK castOther = (ImovelSubcategoriaPK) other;
        return new EqualsBuilder().append(this.getImovel(),
                castOther.getImovel()).append(this.getSubcategoria(),
                castOther.getSubcategoria()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getImovel()).append(
                getSubcategoria()).toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "subcategoria";
 		retorno[1] = "imovel";
 		return retorno;		
	}

}
