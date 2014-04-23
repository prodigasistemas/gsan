package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTipoMaterial implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.atendimentopublico.ordemservico.ServicoTipoMaterialPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal quantidadePadrao;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.Material material;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

    /** full constructor */
    public ServicoTipoMaterial(gcom.atendimentopublico.ordemservico.ServicoTipoMaterialPK comp_id, Date ultimaAlteracao, BigDecimal quantidadePadrao, gcom.atendimentopublico.ordemservico.Material material, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.quantidadePadrao = quantidadePadrao;
        this.material = material;
        this.servicoTipo = servicoTipo;
    }

    /** default constructor */
    public ServicoTipoMaterial() {
    }

    /** minimal constructor */
    public ServicoTipoMaterial(gcom.atendimentopublico.ordemservico.ServicoTipoMaterialPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipoMaterialPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.atendimentopublico.ordemservico.ServicoTipoMaterialPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getQuantidadePadrao() {
        return this.quantidadePadrao;
    }

    public void setQuantidadePadrao(BigDecimal quantidadePadrao) {
        this.quantidadePadrao = quantidadePadrao;
    }

    public gcom.atendimentopublico.ordemservico.Material getMaterial() {
        return this.material;
    }

    public void setMaterial(gcom.atendimentopublico.ordemservico.Material material) {
        this.material = material;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ServicoTipoMaterial) ) return false;
        ServicoTipoMaterial castOther = (ServicoTipoMaterial) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
