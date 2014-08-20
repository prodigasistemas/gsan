package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaCategoriaHistoricoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private ContaHistorico contaHistorico;
    private Categoria categoria;
    private Subcategoria subcategoria;

    public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public ContaCategoriaHistoricoPK(ContaHistorico contaHistorico, Categoria categoria) {
        this.contaHistorico = contaHistorico;
        this.categoria = categoria;
    }
	
    public ContaCategoriaHistoricoPK(ContaHistorico contaHistorico, Categoria categoria, Subcategoria subcategoria) {
        this.contaHistorico = contaHistorico;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
    }
    
    public ContaCategoriaHistoricoPK(Integer idConta, Categoria categoria, Subcategoria subcategoria) {
        this.contaHistorico = new ContaHistorico(idConta);
        this.categoria = categoria;
        this.subcategoria = subcategoria;
    }

	public String getDescricao(){
		return this.categoria.getDescricao();
	}

    public ContaCategoriaHistoricoPK() {
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("contaHistorico", getContaHistorico())
            .append("categoria", getCategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ContaCategoriaHistoricoPK) ) return false;
        ContaCategoriaHistoricoPK castOther = (ContaCategoriaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getContaHistorico(), castOther.getContaHistorico())
            .append(this.getCategoria(), castOther.getCategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getContaHistorico())
            .append(getCategoria())
            .toHashCode();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ContaHistorico getContaHistorico() {
        return contaHistorico;
    }

    public void setContaHistorico(ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }

	public Integer getIdConta() {
		return this.contaHistorico.getId();
	}

	public void setIdConta(Integer id) {
		this.contaHistorico.setId(id);
	}

	public IConta getConta() {
		return contaHistorico;
	}

	public void setConta(ContaHistorico conta) {
		this.contaHistorico = conta;
	}

	public void setConta(IConta conta) {
	}

}
