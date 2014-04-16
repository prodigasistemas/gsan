package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsProgramacaoEquipePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idOrdemServicoProgramacao;

    /** identifier field */
    private Integer idEquipe;

    /** full constructor */
    public OsProgramacaoEquipePK(Integer idOrdemServicoProgramacao, Integer idEquipe) {
        this.idOrdemServicoProgramacao = idOrdemServicoProgramacao;
        this.idEquipe = idEquipe;
    }

    /** default constructor */
    public OsProgramacaoEquipePK() {
    }

    public Integer getIdOrdemServicoProgramacao() {
        return this.idOrdemServicoProgramacao;
    }

    public void setIdOrdemServicoProgramacao(Integer idOrdemServicoProgramacao) {
        this.idOrdemServicoProgramacao = idOrdemServicoProgramacao;
    }

    public Integer getIdEquipe() {
        return this.idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idOrdemServicoProgramacao", getIdOrdemServicoProgramacao())
            .append("idEquipe", getIdEquipe())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OsProgramacaoEquipePK) ) return false;
        OsProgramacaoEquipePK castOther = (OsProgramacaoEquipePK) other;
        return new EqualsBuilder()
            .append(this.getIdOrdemServicoProgramacao(), castOther.getIdOrdemServicoProgramacao())
            .append(this.getIdEquipe(), castOther.getIdEquipe())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdOrdemServicoProgramacao())
            .append(getIdEquipe())
            .toHashCode();
    }

}
