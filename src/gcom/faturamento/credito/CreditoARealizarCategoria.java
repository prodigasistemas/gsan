package gcom.faturamento.credito;

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
public class CreditoARealizarCategoria extends ObjetoTransacao  {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.credito.CreditoARealizarCategoriaPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.faturamento.credito.CreditoARealizar creditoARealizar;

    /** nullable persistent field */
    private Categoria categoria;

    /** full constructor */
    public CreditoARealizarCategoria(gcom.faturamento.credito.CreditoARealizarCategoriaPK comp_id, Integer quantidadeEconomia, BigDecimal valorCategoria, Date ultimaAlteracao, gcom.faturamento.credito.CreditoARealizar creditoARealizar, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorCategoria = valorCategoria;
        this.ultimaAlteracao = ultimaAlteracao;
        this.creditoARealizar = creditoARealizar;
        this.categoria = categoria;
    }

    /** default constructor */
    public CreditoARealizarCategoria() {
    }

    /** minimal constructor */
    public CreditoARealizarCategoria(gcom.faturamento.credito.CreditoARealizarCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.faturamento.credito.CreditoARealizarCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.faturamento.credito.CreditoARealizarCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Integer quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public BigDecimal getValorCategoria() {
        return this.valorCategoria;
    }

    public void setValorCategoria(BigDecimal valorCategoria) {
        this.valorCategoria = valorCategoria;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.credito.CreditoARealizar getCreditoARealizar() {
        return this.creditoARealizar;
    }

    public void setCreditoARealizar(gcom.faturamento.credito.CreditoARealizar creditoARealizar) {
        this.creditoARealizar = creditoARealizar;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CreditoARealizarCategoria) ) return false;
        CreditoARealizarCategoria castOther = (CreditoARealizarCategoria) other;
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
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroCreditoARealizarCategoria filtroCreditoARealizarCategoria = new FiltroCreditoARealizarCategoria();
		
		filtroCreditoARealizarCategoria. adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroCreditoARealizarCategoria. adicionarCaminhoParaCarregamentoEntidade("creditoARealizar");
		filtroCreditoARealizarCategoria. adicionarCaminhoParaCarregamentoEntidade("categoria");
		
		filtroCreditoARealizarCategoria. adicionarParametro(
				new ParametroSimples(FiltroCreditoARealizarCategoria.ID_CREDITO_A_REALIZAR, creditoARealizar.getId()));
		filtroCreditoARealizarCategoria. adicionarParametro(
				new ParametroSimples(FiltroCreditoARealizarCategoria.ID_CATEGORIA, categoria.getId()));
		return filtroCreditoARealizarCategoria; 
	}
}
