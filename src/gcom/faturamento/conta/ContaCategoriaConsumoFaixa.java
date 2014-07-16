package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaCategoriaConsumoFaixa implements Serializable, IContaCategoriaConsumoFaixa {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private BigDecimal valorAgua;
    private Integer consumoAgua;
    private BigDecimal valorEsgoto;
    private Integer consumoEsgoto;
    private Date ultimaAlteracao;
    private Integer consumoFaixaInicio;
    private Integer consumoFaixaFim;
    private BigDecimal valorTarifaFaixa;
    private Categoria categoria;
    private ContaCategoria contaCategoria;
    private Subcategoria subcategoria;

    public ContaCategoriaConsumoFaixa(BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, Date ultimaAlteracao, Integer consumoFaixaInicio, Integer consumoFaixaFim, BigDecimal valorTarifaFaixa, Categoria categoria, ContaCategoria contaCategoria, Subcategoria subcategoria) {
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.consumoFaixaInicio = consumoFaixaInicio;
        this.consumoFaixaFim = consumoFaixaFim;
        this.valorTarifaFaixa = valorTarifaFaixa;
        this.categoria = categoria;
        this.contaCategoria = contaCategoria;
        this.subcategoria = subcategoria;
    }

    public ContaCategoriaConsumoFaixa() {
    }

    public ContaCategoriaConsumoFaixa(Categoria categoria, ContaCategoria contaCategoria) {
        this.categoria = categoria;
        this.contaCategoria = contaCategoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorAgua() {
        return this.valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public Integer getConsumoAgua() {
        return this.consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public BigDecimal getValorEsgoto() {
        return this.valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public Integer getConsumoEsgoto() {
        return this.consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getConsumoFaixaInicio() {
        return this.consumoFaixaInicio;
    }

    public void setConsumoFaixaInicio(Integer consumoFaixaInicio) {
        this.consumoFaixaInicio = consumoFaixaInicio;
    }

    public Integer getConsumoFaixaFim() {
        return this.consumoFaixaFim;
    }

    public void setConsumoFaixaFim(Integer consumoFaixaFim) {
        this.consumoFaixaFim = consumoFaixaFim;
    }

    public BigDecimal getValorTarifaFaixa() {
        return this.valorTarifaFaixa;
    }

    public void setValorTarifaFaixa(BigDecimal valorTarifaFaixa) {
        this.valorTarifaFaixa = valorTarifaFaixa;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public IContaCategoria getContaCategoria() {
        return this.contaCategoria;
    }

    public void setContaCategoria(IContaCategoria contaCategoria) {
    	if (this.contaCategoria == null) {
    		this.contaCategoria = new ContaCategoria();
    		
    	}
    	
    	if (this.contaCategoria.getComp_id() == null) {
    		ContaCategoriaPK comp_id = new ContaCategoriaPK();
    		this.contaCategoria.setComp_id(comp_id);
    	}
    	
    	this.contaCategoria.getComp_id().setConta(contaCategoria.getConta());
    	this.contaCategoria.getComp_id().setCategoria(contaCategoria.getCategoria());
    	this.contaCategoria.getComp_id().setSubcategoria(contaCategoria.getSubcategoria());
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

}
