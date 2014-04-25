package gcom.faturamento;

import gcom.faturamento.conta.Conta;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoImediatoAjuste implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorCobradoAgua;

    /** nullable persistent field */
    private BigDecimal valorCobradoEsgoto;

    /** nullable persistent field */
    private Integer numeroConsumoAgua;

    /** nullable persistent field */
    private Integer numeroConsumoEsgoto;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Conta conta;

    /** full constructor */
    public FaturamentoImediatoAjuste(BigDecimal valorCobradoAgua, BigDecimal valorCobradoEsgoto, Integer numeroConsumoAgua, Integer numeroConsumoEsgoto, Date ultimaAlteracao, Conta conta) {
        this.valorCobradoAgua = valorCobradoAgua;
        this.valorCobradoEsgoto = valorCobradoEsgoto;
        this.numeroConsumoAgua = numeroConsumoAgua;
        this.numeroConsumoEsgoto = numeroConsumoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.conta = conta;
    }

    /** default constructor */
    public FaturamentoImediatoAjuste() {
    }

    /** minimal constructor */
    public FaturamentoImediatoAjuste(Conta conta) {
        this.conta = conta;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorCobradoAgua() {
        return this.valorCobradoAgua;
    }

    public void setValorCobradoAgua(BigDecimal valorCobradoAgua) {
        this.valorCobradoAgua = valorCobradoAgua;
    }

    public BigDecimal getValorCobradoEsgoto() {
        return this.valorCobradoEsgoto;
    }

    public void setValorCobradoEsgoto(BigDecimal valorCobradoEsgoto) {
        this.valorCobradoEsgoto = valorCobradoEsgoto;
    }

    public Integer getNumeroConsumoAgua() {
        return this.numeroConsumoAgua;
    }

    public void setNumeroConsumoAgua(Integer numeroConsumoAgua) {
        this.numeroConsumoAgua = numeroConsumoAgua;
    }

    public Integer getNumeroConsumoEsgoto() {
        return this.numeroConsumoEsgoto;
    }

    public void setNumeroConsumoEsgoto(Integer numeroConsumoEsgoto) {
        this.numeroConsumoEsgoto = numeroConsumoEsgoto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
