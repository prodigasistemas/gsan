package gcom.faturamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class MovimentoContaCategoriaConsumoFaixa implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int consumoFaturadoAguaNaFaixa;

    /** nullable persistent field */
    private BigDecimal valorFaturadoAguaNaFaixa;

    /** nullable persistent field */
    private Integer limiteInicialConsumoNaFaixa;

    /** nullable persistent field */
    private Integer limiteFinalConsumoNaFaixa;

    /** nullable persistent field */
    private Integer consumoFaturadoEsgotoNaFaixa;

    /** nullable persistent field */
    private BigDecimal valorFaturadoEsgotoNaFaixa;

    /** persistent field */
    private Date ultimaAlteracao;
    
    
    /** nullable persistent field */
    private BigDecimal valorTarifaNaFaixa;

    /** persistent field */
    private gcom.faturamento.MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoria;

    
    public int getConsumoFaturadoAguaNaFaixa() {
        return consumoFaturadoAguaNaFaixa;
    }

    
    public void setConsumoFaturadoAguaNaFaixa(int consumoFaturadoAguaNaFaixa) {
        this.consumoFaturadoAguaNaFaixa = consumoFaturadoAguaNaFaixa;
    }

    
    public Integer getConsumoFaturadoEsgotoNaFaixa() {
        return consumoFaturadoEsgotoNaFaixa;
    }

    
    public void setConsumoFaturadoEsgotoNaFaixa(Integer consumoFaturadoEsgotoNaFaixa) {
        this.consumoFaturadoEsgotoNaFaixa = consumoFaturadoEsgotoNaFaixa;
    }

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getLimiteFinalConsumoNaFaixa() {
        return limiteFinalConsumoNaFaixa;
    }

    
    public void setLimiteFinalConsumoNaFaixa(Integer limiteFinalConsumoNaFaixa) {
        this.limiteFinalConsumoNaFaixa = limiteFinalConsumoNaFaixa;
    }

    
    public Integer getLimiteInicialConsumoNaFaixa() {
        return limiteInicialConsumoNaFaixa;
    }

    
    public void setLimiteInicialConsumoNaFaixa(Integer limiteInicialConsumoNaFaixa) {
        this.limiteInicialConsumoNaFaixa = limiteInicialConsumoNaFaixa;
    }

    
    public gcom.faturamento.MovimentoContaPrefaturadaCategoria getMovimentoContaPrefaturadaCategoria() {
        return movimentoContaPrefaturadaCategoria;
    }

    
    public void setMovimentoContaPrefaturadaCategoria(
            gcom.faturamento.MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoria) {
        this.movimentoContaPrefaturadaCategoria = movimentoContaPrefaturadaCategoria;
    }

    
    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    
    public BigDecimal getValorFaturadoAguaNaFaixa() {
        return valorFaturadoAguaNaFaixa;
    }

    
    public void setValorFaturadoAguaNaFaixa(BigDecimal valorFaturadoAguaNaFaixa) {
        this.valorFaturadoAguaNaFaixa = valorFaturadoAguaNaFaixa;
    }

    
    public BigDecimal getValorFaturadoEsgotoNaFaixa() {
        return valorFaturadoEsgotoNaFaixa;
    }

    
    public void setValorFaturadoEsgotoNaFaixa(BigDecimal valorFaturadoEsgotoNaFaixa) {
        this.valorFaturadoEsgotoNaFaixa = valorFaturadoEsgotoNaFaixa;
    }


	public BigDecimal getValorTarifaNaFaixa() {
		return valorTarifaNaFaixa;
	}


	public void setValorTarifaNaFaixa(BigDecimal valorTarifaNaFaixa) {
		this.valorTarifaNaFaixa = valorTarifaNaFaixa;
	}
    
}
