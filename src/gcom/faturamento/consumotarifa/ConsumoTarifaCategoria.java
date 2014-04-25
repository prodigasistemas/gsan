package gcom.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoTarifaCategoria implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer numeroConsumoMinimo;

    /** nullable persistent field */
    private BigDecimal valorTarifaMinima;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia;

    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private Subcategoria subCategoria;
    
    private Set consumoTarifaFaixas;

    /** full constructor */
    public ConsumoTarifaCategoria(Integer numeroConsumoMinimo, 
    	BigDecimal valorTarifaMinima, Date ultimaAlteracao, 
    	gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia,Categoria categoria) {
        
    	this.numeroConsumoMinimo = numeroConsumoMinimo;
        this.valorTarifaMinima = valorTarifaMinima;
        this.ultimaAlteracao = ultimaAlteracao;
        this.consumoTarifaVigencia = consumoTarifaVigencia;
        this.categoria = categoria;
    }

    /** default constructor */
    public ConsumoTarifaCategoria() {
    }

    /** minimal constructor */
    public ConsumoTarifaCategoria(gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia, Categoria categoria) {
        this.consumoTarifaVigencia = consumoTarifaVigencia;
        this.categoria = categoria;
    }
    
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ConsumoTarifaCategoria)) {
            return false;
        }
        ConsumoTarifaCategoria castOther = (ConsumoTarifaCategoria) other;

        return (this.getCategoria().getId().equals(castOther.getCategoria().getId()) &&
        		this.getSubCategoria().getId().equals(castOther.getSubCategoria().getId()));
    }
    
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroConsumoMinimo() {
        return this.numeroConsumoMinimo;
    }

    public void setNumeroConsumoMinimo(Integer numeroConsumoMinimo) {
        this.numeroConsumoMinimo = numeroConsumoMinimo;
    }

    public BigDecimal getValorTarifaMinima() {
        return this.valorTarifaMinima;
    }

    public void setValorTarifaMinima(BigDecimal valorTarifaMinima) {
        this.valorTarifaMinima = valorTarifaMinima;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.consumotarifa.ConsumoTarifaVigencia getConsumoTarifaVigencia() {
        return this.consumoTarifaVigencia;
    }

    public void setConsumoTarifaVigencia(gcom.faturamento.consumotarifa.ConsumoTarifaVigencia consumoTarifaVigencia) {
        this.consumoTarifaVigencia = consumoTarifaVigencia;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getConsumoTarifaFaixas() {
		return consumoTarifaFaixas;
	}

	public void setConsumoTarifaFaixas(Set consumoTarifaFaixas) {
		this.consumoTarifaFaixas = consumoTarifaFaixas;
	}

	public Subcategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Subcategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

}
