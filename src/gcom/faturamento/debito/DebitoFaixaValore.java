package gcom.faturamento.debito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoFaixaValore implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private BigDecimal valorFaixaInicio;

    /** persistent field */
    private BigDecimal valorFaixaFim;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public DebitoFaixaValore(Integer id, BigDecimal valorFaixaInicio, BigDecimal valorFaixaFim, Date ultimaAlteracao) {
        this.id = id;
        this.valorFaixaInicio = valorFaixaInicio;
        this.valorFaixaFim = valorFaixaFim;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public DebitoFaixaValore() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorFaixaInicio() {
        return this.valorFaixaInicio;
    }

    public void setValorFaixaInicio(BigDecimal valorFaixaInicio) {
        this.valorFaixaInicio = valorFaixaInicio;
    }

    public BigDecimal getValorFaixaFim() {
        return this.valorFaixaFim;
    }

    public void setValorFaixaFim(BigDecimal valorFaixaFim) {
        this.valorFaixaFim = valorFaixaFim;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
