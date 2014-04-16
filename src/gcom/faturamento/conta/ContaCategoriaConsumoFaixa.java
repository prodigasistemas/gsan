package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaCategoriaConsumoFaixa implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorAgua;

    /** nullable persistent field */
    private Integer consumoAgua;

    /** nullable persistent field */
    private BigDecimal valorEsgoto;

    /** nullable persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer consumoFaixaInicio;

    /** nullable persistent field */
    private Integer consumoFaixaFim;

    /** nullable persistent field */
    private BigDecimal valorTarifaFaixa;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private gcom.faturamento.conta.ContaCategoria contaCategoria;
    
    /** persistent field */
    private Subcategoria subcategoria;


    /** full constructor */
    public ContaCategoriaConsumoFaixa(BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, Date ultimaAlteracao, Integer consumoFaixaInicio, Integer consumoFaixaFim, BigDecimal valorTarifaFaixa, Categoria categoria, gcom.faturamento.conta.ContaCategoria contaCategoria, Subcategoria subcategoria) {
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

    /** default constructor */
    public ContaCategoriaConsumoFaixa() {
    }

    /** minimal constructor */
    public ContaCategoriaConsumoFaixa(Categoria categoria, gcom.faturamento.conta.ContaCategoria contaCategoria) {
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

    public gcom.faturamento.conta.ContaCategoria getContaCategoria() {
        return this.contaCategoria;
    }

    public void setContaCategoria(gcom.faturamento.conta.ContaCategoria contaCategoria) {
        this.contaCategoria = contaCategoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo subcategoria.
	 */
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria O subcategoria a ser setado.
	 */
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

}
