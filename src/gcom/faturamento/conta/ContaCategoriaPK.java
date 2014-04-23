package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaCategoriaPK extends ObjetoGcom{
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.conta.Conta conta;

    /** identifier field */
    private Categoria categoria;
    
    /** identifier field */
    private Subcategoria subcategoria;

    /** full constructor */
    public ContaCategoriaPK(gcom.faturamento.conta.Conta conta, Categoria categoria) {
        this.conta = conta;
        this.categoria = categoria;
    }

    /** default constructor */
    public ContaCategoriaPK() {
    }

    public gcom.faturamento.conta.Conta getConta() {
        return this.conta;
    }

    public void setConta(gcom.faturamento.conta.Conta conta) {
        this.conta = conta;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}


	public String toString() {
        return new ToStringBuilder(this)
            .append("conta", getConta())
            .append("categoria", getCategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ContaCategoriaPK) ) return false;
        ContaCategoriaPK castOther = (ContaCategoriaPK) other;
        return new EqualsBuilder()
//            .append(this.getConta(), castOther.getConta())
            .append(this.getCategoria(), castOther.getCategoria())
            .append(this.getSubcategoria(),castOther.getSubcategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getConta())
            .append(getCategoria())
            .toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "categoria";
 		retorno[1] = "conta";
 		return retorno;		
	}

	public String getDescricao(){
		return this.categoria.getDescricao();
	}
	
	public void initializeLazy(){
		if (this.getCategoria() != null){
			this.getCategoria().initializeLazy();
		}
		if (this.getSubcategoria() != null){
			this.getSubcategoria().initializeLazy();
		}		
	}
}
