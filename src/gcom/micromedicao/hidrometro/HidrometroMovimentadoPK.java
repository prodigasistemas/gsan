package gcom.micromedicao.hidrometro;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class HidrometroMovimentadoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer hidrometroMovimentacaoId;

    /** identifier field */
    private Integer hidrometroId;

    /** full constructor */
    public HidrometroMovimentadoPK(Integer hidrometroMovimentacaoId, Integer hidrometroId) {
        this.hidrometroMovimentacaoId = hidrometroMovimentacaoId;
        this.hidrometroId = hidrometroId;
    }

    /** default constructor */
    public HidrometroMovimentadoPK() {
    }

    public Integer getHidrometroMovimentacaoId() {
        return this.hidrometroMovimentacaoId;
    }

    public void setHidrometroMovimentacaoId(Integer hidrometroMovimentacaoId) {
        this.hidrometroMovimentacaoId = hidrometroMovimentacaoId;
    }

    public Integer getHidrometroId() {
        return this.hidrometroId;
    }

    public void setHidrometroId(Integer hidrometroId) {
        this.hidrometroId = hidrometroId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("hidrometroMovimentacaoId", getHidrometroMovimentacaoId())
            .append("hidrometroId", getHidrometroId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof HidrometroMovimentadoPK) ) return false;
        HidrometroMovimentadoPK castOther = (HidrometroMovimentadoPK) other;
        return new EqualsBuilder()
            .append(this.getHidrometroMovimentacaoId(), castOther.getHidrometroMovimentacaoId())
            .append(this.getHidrometroId(), castOther.getHidrometroId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getHidrometroMovimentacaoId())
            .append(getHidrometroId())
            .toHashCode();
    }

}
