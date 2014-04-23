package gcom.atendimentopublico.ordemservico;

import gcom.seguranca.acesso.Operacao;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTipoOperacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.atendimentopublico.ordemservico.ServicoTipoOperacaoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Operacao operacao;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

    /** full constructor */
    public ServicoTipoOperacao(gcom.atendimentopublico.ordemservico.ServicoTipoOperacaoPK comp_id, Date ultimaAlteracao, Operacao operacao, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.operacao = operacao;
        this.servicoTipo = servicoTipo;
    }

    /** default constructor */
    public ServicoTipoOperacao() {
    }

    /** minimal constructor */
    public ServicoTipoOperacao(gcom.atendimentopublico.ordemservico.ServicoTipoOperacaoPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipoOperacaoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.atendimentopublico.ordemservico.ServicoTipoOperacaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Operacao getOperacao() {
        return this.operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
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
        if ( !(other instanceof ServicoTipoOperacao) ) return false;
        ServicoTipoOperacao castOther = (ServicoTipoOperacao) other;
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
