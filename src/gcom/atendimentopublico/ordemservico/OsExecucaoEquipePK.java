package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsExecucaoEquipePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idOsAtividadePeriodoExecucao;

    /** identifier field */
    private Integer idEquipe;

    /** full constructor */
    public OsExecucaoEquipePK(Integer idOsAtividadePeriodoExecucao, Integer idEquipe) {
        this.idOsAtividadePeriodoExecucao = idOsAtividadePeriodoExecucao;
        this.idEquipe = idEquipe;
    }

    /** default constructor */
    public OsExecucaoEquipePK() {
    }

    public Integer getIdOsAtividadePeriodoExecucao() {
        return this.idOsAtividadePeriodoExecucao;
    }

    public void setIdOsAtividadePeriodoExecucao(Integer idOsAtividadePeriodoExecucao) {
        this.idOsAtividadePeriodoExecucao = idOsAtividadePeriodoExecucao;
    }

    public Integer getIdEquipe() {
        return this.idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idOsAtividadePeriodoExecucao", getIdOsAtividadePeriodoExecucao())
            .append("idEquipe", getIdEquipe())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OsExecucaoEquipePK) ) return false;
        OsExecucaoEquipePK castOther = (OsExecucaoEquipePK) other;
        return new EqualsBuilder()
            .append(this.getIdOsAtividadePeriodoExecucao(), castOther.getIdOsAtividadePeriodoExecucao())
            .append(this.getIdEquipe(), castOther.getIdEquipe())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdOsAtividadePeriodoExecucao())
            .append(getIdEquipe())
            .toHashCode();
    }

}
