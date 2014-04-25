package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaCategoriaConsumoFaixaHistorico implements Serializable {
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
    private Integer consumoFaixaInicio;

    /** nullable persistent field */
    private Integer consumoFaixaFim;

    /** nullable persistent field */
    private BigDecimal valorTarifaFaixa;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private ContaCategoriaHistorico contaCategoriaHistorico;

    /** persistent field */
    private Subcategoria subcategoria;
    
    /** full constructor */
    public ContaCategoriaConsumoFaixaHistorico(Integer id, BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, Integer consumoFaixaInicio, Integer consumoFaixaFim, BigDecimal valorTarifaFaixa, Date ultimaAlteracao, Categoria categoria, ContaCategoriaHistorico contaCategoriaHistorico, Subcategoria subcategoria) {
        this.id = id;
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.consumoFaixaInicio = consumoFaixaInicio;
        this.consumoFaixaFim = consumoFaixaFim;
        this.valorTarifaFaixa = valorTarifaFaixa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.categoria = categoria;
        this.contaCategoriaHistorico = contaCategoriaHistorico;
        this.subcategoria = subcategoria;
    }

    /** default constructor */
    public ContaCategoriaConsumoFaixaHistorico() {
    }

    /** minimal constructor */
    public ContaCategoriaConsumoFaixaHistorico(Integer id, Date ultimaAlteracao, ContaCategoriaHistorico contaCategoriaHistorico) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaCategoriaHistorico = contaCategoriaHistorico;
    }

    
    public ContaCategoriaHistorico getContaCategoriaHistorico() {
        return this.contaCategoriaHistorico;
    }

    public void setContaCategoriaHistorico(ContaCategoriaHistorico contaCategoriaHistorico) {
        this.contaCategoriaHistorico = contaCategoriaHistorico;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Integer getConsumoAgua() {
        return consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public Integer getConsumoEsgoto() {
        return consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public Integer getConsumoFaixaFim() {
        return consumoFaixaFim;
    }

    public void setConsumoFaixaFim(Integer consumoFaixaFim) {
        this.consumoFaixaFim = consumoFaixaFim;
    }

    public Integer getConsumoFaixaInicio() {
        return consumoFaixaInicio;
    }

    public void setConsumoFaixaInicio(Integer consumoFaixaInicio) {
        this.consumoFaixaInicio = consumoFaixaInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorAgua() {
        return valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public BigDecimal getValorEsgoto() {
        return valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public BigDecimal getValorTarifaFaixa() {
        return valorTarifaFaixa;
    }

    public void setValorTarifaFaixa(BigDecimal valorTarifaFaixa) {
        this.valorTarifaFaixa = valorTarifaFaixa;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
