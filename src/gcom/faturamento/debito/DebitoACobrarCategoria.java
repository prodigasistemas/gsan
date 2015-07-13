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


/** @author Hibernate CodeGenerator */
public class DebitoACobrarCategoria extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.debito.DebitoACobrarCategoriaPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** nullable persistent field */
    private gcom.faturamento.debito.DebitoACobrar debitoACobrar;

    /** nullable persistent field */
    private Categoria categoria;

    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroDebitoACobrarCategoria filtroDebitoACobrarCategoria = new FiltroDebitoACobrarCategoria();
		
		filtroDebitoACobrarCategoria. adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroDebitoACobrarCategoria. adicionarCaminhoParaCarregamentoEntidade("debitoACobrar");
		filtroDebitoACobrarCategoria. adicionarCaminhoParaCarregamentoEntidade("categoria");
		
		filtroDebitoACobrarCategoria. adicionarParametro(
				new ParametroSimples(FiltroDebitoACobrarCategoria.ID_DEBITO_A_COBRAR, debitoACobrar.getId()));
		filtroDebitoACobrarCategoria. adicionarParametro(
				new ParametroSimples(FiltroDebitoACobrarCategoria.ID_CATEGORIA, categoria.getId()));
		return filtroDebitoACobrarCategoria; 
	}
    
    
    /** full constructor */
    public DebitoACobrarCategoria(gcom.faturamento.debito.DebitoACobrarCategoriaPK comp_id, Integer quantidadeEconomia, Date ultimaAlteracao, BigDecimal valorCategoria, gcom.faturamento.debito.DebitoACobrar debitoACobrar, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorCategoria = valorCategoria;
        this.debitoACobrar = debitoACobrar;
        this.categoria = categoria;
    }

    /** default constructor */
    public DebitoACobrarCategoria() {
    }

    /** minimal constructor */
    public DebitoACobrarCategoria(gcom.faturamento.debito.DebitoACobrarCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.faturamento.debito.DebitoACobrarCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.faturamento.debito.DebitoACobrarCategoriaPK comp_id) {
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
		return "DebitoACobrarCategoria [comp_id=" + comp_id + "]";
	}

	public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoACobrarCategoria) ) return false;
        DebitoACobrarCategoria castOther = (DebitoACobrarCategoria) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
