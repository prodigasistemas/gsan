package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioSituacaoEspecialCobranca implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** persistent field */
    private CobrancaSituacaoTipo cobrancaSituacaoTipo;

    /** full constructor */
    public NegativacaoCriterioSituacaoEspecialCobranca(
    	Integer id, Date ultimaAlteracao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, CobrancaSituacaoTipo cobrancaSituacaoTipo) {
        
    	this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
    }

    /** default constructor */
    public NegativacaoCriterioSituacaoEspecialCobranca() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.NegativacaoCriterio getNegativacaoCriterio() {
        return this.negativacaoCriterio;
    }

    public void setNegativacaoCriterio(gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.negativacaoCriterio = negativacaoCriterio;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    /**
     *  Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof NegativacaoCriterioCpfTipo)) {
            return false;
        }
        NegativacaoCriterioSituacaoEspecialCobranca castOther = (NegativacaoCriterioSituacaoEspecialCobranca) other;

        return new EqualsBuilder().append(this.getCobrancaSituacaoTipo().getId(), castOther.getCobrancaSituacaoTipo().getId()).isEquals();
    }

   
   /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCobrancaSituacaoTipo().getId())
                .toHashCode();
    }

	public CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}
	
	public void setCobrancaSituacaoTipo(CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}

    
}
