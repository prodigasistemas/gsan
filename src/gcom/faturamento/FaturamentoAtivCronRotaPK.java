package gcom.faturamento;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoAtivCronRotaPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer faturamentoAtividadeCronogramaId;

    /** identifier field */
    private Integer rotaId;
    
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "faturamentoAtividadeCronogramaId";
		retorno[1] = "rotaId";
		return retorno;
	}

    /** full constructor */
    public FaturamentoAtivCronRotaPK(Integer faturamentoAtividadeCronogramaId, Integer rotaId) {
        this.faturamentoAtividadeCronogramaId = faturamentoAtividadeCronogramaId;
        this.rotaId = rotaId;
    }

    /** default constructor */
    public FaturamentoAtivCronRotaPK() {
    }

    public Integer getFaturamentoAtividadeCronogramaId() {
        return this.faturamentoAtividadeCronogramaId;
    }

    public void setFaturamentoAtividadeCronogramaId(Integer faturamentoAtividadeCronogramaId) {
        this.faturamentoAtividadeCronogramaId = faturamentoAtividadeCronogramaId;
    }

    public Integer getRotaId() {
        return this.rotaId;
    }

    public void setRotaId(Integer rotaId) {
        this.rotaId = rotaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("faturamentoAtividadeCronogramaId", getFaturamentoAtividadeCronogramaId())
            .append("rotaId", getRotaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FaturamentoAtivCronRotaPK) ) return false;
        FaturamentoAtivCronRotaPK castOther = (FaturamentoAtivCronRotaPK) other;
        return new EqualsBuilder()
            .append(this.getFaturamentoAtividadeCronogramaId(), castOther.getFaturamentoAtividadeCronogramaId())
            .append(this.getRotaId(), castOther.getRotaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFaturamentoAtividadeCronogramaId())
            .append(getRotaId())
            .toHashCode();
    }

}
