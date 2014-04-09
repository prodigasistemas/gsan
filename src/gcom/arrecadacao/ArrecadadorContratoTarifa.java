package gcom.arrecadacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArrecadadorContratoTarifa implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.arrecadacao.ArrecadadorContratoTarifaPK comp_id;

    /** nullable persistent field */
    private BigDecimal valorTarifa;

    /** nullable persistent field */
    private Short numeroDiaFloat;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.arrecadacao.ArrecadacaoForma arrecadacaoForma;

    /** nullable persistent field */
    private gcom.arrecadacao.ArrecadadorContrato arrecadadorContrato;
    
    /** nullable persistent field */
    private BigDecimal valorTarifaPercentual;

    /** full constructor */
    public ArrecadadorContratoTarifa(gcom.arrecadacao.ArrecadadorContratoTarifaPK comp_id, BigDecimal valorTarifa, Short numeroDiaFloat, Date ultimaAlteracao, gcom.arrecadacao.ArrecadacaoForma arrecadacaoForma, gcom.arrecadacao.ArrecadadorContrato arrecadadorContrato) {
        this.comp_id = comp_id;
        this.valorTarifa = valorTarifa;
        this.numeroDiaFloat = numeroDiaFloat;
        this.ultimaAlteracao = ultimaAlteracao;
        this.arrecadacaoForma = arrecadacaoForma;
        this.arrecadadorContrato = arrecadadorContrato;
    }

    /** default constructor */
    public ArrecadadorContratoTarifa() {
    }

    /** minimal constructor */
    public ArrecadadorContratoTarifa(gcom.arrecadacao.ArrecadadorContratoTarifaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.arrecadacao.ArrecadadorContratoTarifaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.arrecadacao.ArrecadadorContratoTarifaPK comp_id) {
        this.comp_id = comp_id;
    }

    public BigDecimal getValorTarifa() {
        return this.valorTarifa;
    }

    public void setValorTarifa(BigDecimal valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public Short getNumeroDiaFloat() {
        return this.numeroDiaFloat;
    }

    public void setNumeroDiaFloat(Short numeroDiaFloat) {
        this.numeroDiaFloat = numeroDiaFloat;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.arrecadacao.ArrecadacaoForma getArrecadacaoForma() {
        return this.arrecadacaoForma;
    }

    public void setArrecadacaoForma(gcom.arrecadacao.ArrecadacaoForma arrecadacaoForma) {
        this.arrecadacaoForma = arrecadacaoForma;
    }

    public gcom.arrecadacao.ArrecadadorContrato getArrecadadorContrato() {
        return this.arrecadadorContrato;
    }

    public void setArrecadadorContrato(gcom.arrecadacao.ArrecadadorContrato arrecadadorContrato) {
        this.arrecadadorContrato = arrecadadorContrato;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ArrecadadorContratoTarifa) ) return false;
        ArrecadadorContratoTarifa castOther = (ArrecadadorContratoTarifa) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public BigDecimal getValorTarifaPercentual() {
		return valorTarifaPercentual;
	}

	public void setValorTarifaPercentual(BigDecimal valorTarifaPercentual) {
		this.valorTarifaPercentual = valorTarifaPercentual;
	}
}
