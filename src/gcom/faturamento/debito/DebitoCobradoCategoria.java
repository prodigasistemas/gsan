package gcom.faturamento.debito;

import gcom.cadastro.imovel.Categoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class DebitoCobradoCategoria extends ObjetoTransacao implements IDebitoCobradoCategoria {
	private static final long serialVersionUID = 1L;
  
    private DebitoCobradoCategoriaPK comp_id;
    private Integer quantidadeEconomia;
    private Date ultimaAlteracao;
    private BigDecimal valorCategoria;
    private DebitoCobrado debitoCobrado;
    private Categoria categoria;

    public DebitoCobradoCategoria(DebitoCobradoCategoriaPK comp_id, Integer quantidadeEconomia, Date ultimaAlteracao, BigDecimal valorCategoria, gcom.faturamento.debito.DebitoCobrado debitoCobrado, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorCategoria = valorCategoria;
        this.debitoCobrado = debitoCobrado;
        this.categoria = categoria;
    }

    public DebitoCobradoCategoria() {
    }

    public DebitoCobradoCategoria(DebitoCobradoCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public DebitoCobradoCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(DebitoCobradoCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Integer quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorCategoria() {
        return this.valorCategoria;
    }

    public void setValorCategoria(BigDecimal valorCategoria) {
        this.valorCategoria = valorCategoria;
    }

    public DebitoCobrado getDebitoCobrado() {
        return this.debitoCobrado;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
    	if (comp_id == null) {
    		comp_id = new DebitoCobradoCategoriaPK();
    	}
    	
    	if (comp_id.getCategoriaId() == null) {
        	comp_id.setCategoriaId(categoria != null ? categoria.getId() : null);
        }

    	this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoCobradoCategoria) ) return false;
        DebitoCobradoCategoria castOther = (DebitoCobradoCategoria) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroDebitoCobradoCategoria filtro = new FiltroDebitoCobradoCategoria();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroDebitoCobradoCategoria.DEBITO_COBRADO_ID, this.getComp_id()));
		return filtro; 
	}

	public void setDebitoCobrado(IDebitoCobrado debitoCobrado) {
		if (comp_id == null) {
    		comp_id = new DebitoCobradoCategoriaPK();
    	}
    	
		if (comp_id.getDebitoCobradoId() == null) {
			comp_id.setDebitoCobradoId(debitoCobrado != null ? debitoCobrado.getId() : null);
		}
		
		if (debitoCobrado != null) {
			comp_id.setDebitoCobradoId(debitoCobrado.getId());
		}
        this.debitoCobrado = (DebitoCobrado)debitoCobrado;
	}
	   
}
