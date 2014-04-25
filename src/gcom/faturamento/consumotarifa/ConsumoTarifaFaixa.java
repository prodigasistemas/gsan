package gcom.faturamento.consumotarifa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoTarifaFaixa implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer numeroConsumoFaixaInicio;

    /** nullable persistent field */
    private Integer numeroConsumoFaixaFim;

    /** nullable persistent field */
    private BigDecimal valorConsumoTarifa;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.consumotarifa.ConsumoTarifaCategoria consumoTarifaCategoria;

    /** full constructor */
    public ConsumoTarifaFaixa(Integer numeroConsumoFaixaInicio, Integer numeroConsumoFaixaFim, BigDecimal valorConsumoTarifa, Date ultimaAlteracao, gcom.faturamento.consumotarifa.ConsumoTarifaCategoria consumoTarifaCategoria) {
        this.numeroConsumoFaixaInicio = numeroConsumoFaixaInicio;
        this.numeroConsumoFaixaFim = numeroConsumoFaixaFim;
        this.valorConsumoTarifa = valorConsumoTarifa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.consumoTarifaCategoria = consumoTarifaCategoria;
    }

    /** default constructor */
    public ConsumoTarifaFaixa() {
    }

    /** minimal constructor */
    public ConsumoTarifaFaixa(gcom.faturamento.consumotarifa.ConsumoTarifaCategoria consumoTarifaCategoria) {
        this.consumoTarifaCategoria = consumoTarifaCategoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroConsumoFaixaInicio() {
        return this.numeroConsumoFaixaInicio;
    }

    public void setNumeroConsumoFaixaInicio(Integer numeroConsumoFaixaInicio) {
        this.numeroConsumoFaixaInicio = numeroConsumoFaixaInicio;
    }

    public Integer getNumeroConsumoFaixaFim() {
        return this.numeroConsumoFaixaFim;
    }

    public void setNumeroConsumoFaixaFim(Integer numeroConsumoFaixaFim) {
        this.numeroConsumoFaixaFim = numeroConsumoFaixaFim;
    }

    public BigDecimal getValorConsumoTarifa() {
        return this.valorConsumoTarifa;
    }

    public void setValorConsumoTarifa(BigDecimal valorConsumoTarifa) {
        this.valorConsumoTarifa = valorConsumoTarifa;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.consumotarifa.ConsumoTarifaCategoria getConsumoTarifaCategoria() {
        return this.consumoTarifaCategoria;
    }

    public void setConsumoTarifaCategoria(gcom.faturamento.consumotarifa.ConsumoTarifaCategoria consumoTarifaCategoria) {
        this.consumoTarifaCategoria = consumoTarifaCategoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
