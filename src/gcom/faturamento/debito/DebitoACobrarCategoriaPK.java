package gcom.faturamento.debito;

import gcom.cadastro.imovel.Categoria;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoACobrarCategoriaPK extends ObjetoGcom{
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.debito.DebitoACobrar debitoACobrar;

    /** identifier field */
    private Categoria categoria;

    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "debitoACobrar.id";
		retorno[1] = "categoria.id";
		return retorno;
	}
    
    /** full constructor */
    public DebitoACobrarCategoriaPK(gcom.faturamento.debito.DebitoACobrar debitoACobrar, Categoria categoria) {
        this.debitoACobrar = debitoACobrar;
        this.categoria = categoria;
    }

    /** default constructor */
    public DebitoACobrarCategoriaPK() {
    }

    public gcom.faturamento.debito.DebitoACobrar getDebitoACobrar() {
        return this.debitoACobrar;
    }

    public void setDebitoACobrar(gcom.faturamento.debito.DebitoACobrar debitoACobrar) {
        this.debitoACobrar = debitoACobrar;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("debitoACobrar", getDebitoACobrar())
            .append("categoria", getCategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoACobrarCategoriaPK) ) return false;
        DebitoACobrarCategoriaPK castOther = (DebitoACobrarCategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getDebitoACobrar(), castOther.getDebitoACobrar())
            .append(this.getCategoria(), castOther.getCategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDebitoACobrar())
            .append(getCategoria())
            .toHashCode();
    }

}
