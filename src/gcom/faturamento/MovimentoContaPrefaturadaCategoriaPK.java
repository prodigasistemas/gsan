package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MovimentoContaPrefaturadaCategoriaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.faturamento.MovimentoContaPrefaturada movimentoContaPrefaturada;

    /** identifier field */
    private Categoria categoria;

    /** identifier field */
    private Subcategoria subcategoria;

    /** full constructor */
    public MovimentoContaPrefaturadaCategoriaPK(gcom.faturamento.MovimentoContaPrefaturada movimentoContaPrefaturada, Categoria categoria, Subcategoria subcategoria) {
        this.movimentoContaPrefaturada = movimentoContaPrefaturada;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
    }

    /** default constructor */
    public MovimentoContaPrefaturadaCategoriaPK() {
    }

    public gcom.faturamento.MovimentoContaPrefaturada getMovimentoContaPrefaturada() {
        return this.movimentoContaPrefaturada;
    }

    public void setMovimentoContaPrefaturada(gcom.faturamento.MovimentoContaPrefaturada movimentoContaPrefaturada) {
        this.movimentoContaPrefaturada = movimentoContaPrefaturada;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Subcategoria getSubcategoria() {
        return this.subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("movimentoContaPrefaturada", getMovimentoContaPrefaturada())
            .append("categoria", getCategoria())
            .append("subcategoria", getSubcategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof MovimentoContaPrefaturadaCategoriaPK) ) return false;
        MovimentoContaPrefaturadaCategoriaPK castOther = (MovimentoContaPrefaturadaCategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getMovimentoContaPrefaturada(), castOther.getMovimentoContaPrefaturada())
            .append(this.getCategoria(), castOther.getCategoria())
            .append(this.getSubcategoria(), castOther.getSubcategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getMovimentoContaPrefaturada())
            .append(getCategoria())
            .append(getSubcategoria())
            .toHashCode();
    }

}
