package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaAtividadeComandoRotaPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer cobrancaAcaoAtividadeComandoId;

    /** identifier field */
    private Integer rotaId;

    /** full constructor */
    public CobrancaAtividadeComandoRotaPK(Integer cobrancaAcaoAtividadeComandoId, Integer rotaId) {
        this.cobrancaAcaoAtividadeComandoId = cobrancaAcaoAtividadeComandoId;
        this.rotaId = rotaId;
    }

    /** default constructor */
    public CobrancaAtividadeComandoRotaPK() {
    }

    public Integer getCobrancaAcaoAtividadeComandoId() {
        return this.cobrancaAcaoAtividadeComandoId;
    }

    public void setCobrancaAcaoAtividadeComandoId(Integer cobrancaAcaoAtividadeComandoId) {
        this.cobrancaAcaoAtividadeComandoId = cobrancaAcaoAtividadeComandoId;
    }

    public Integer getRotaId() {
        return this.rotaId;
    }

    public void setRotaId(Integer rotaId) {
        this.rotaId = rotaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cobrancaAcaoAtividadeComandoId", getCobrancaAcaoAtividadeComandoId())
            .append("rotaId", getRotaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CobrancaAtividadeComandoRotaPK) ) return false;
        CobrancaAtividadeComandoRotaPK castOther = (CobrancaAtividadeComandoRotaPK) other;
        return new EqualsBuilder()
            .append(this.getCobrancaAcaoAtividadeComandoId(), castOther.getCobrancaAcaoAtividadeComandoId())
            .append(this.getRotaId(), castOther.getRotaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCobrancaAcaoAtividadeComandoId())
            .append(getRotaId())
            .toHashCode();
    }

    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "cobrancaAcaoAtividadeComandoId";
		retorno[1] = "rotaId";
		return retorno;
	}    
    
    
}
