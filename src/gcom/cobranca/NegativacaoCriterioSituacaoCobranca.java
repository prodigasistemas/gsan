package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioSituacaoCobranca implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** persistent field */
    private CobrancaSituacao cobrancaSituacao;

    /** full constructor */
    public NegativacaoCriterioSituacaoCobranca(
    	Integer id, Date ultimaAlteracao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, CobrancaSituacao cobrancaSituacao) {
        
    	this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.cobrancaSituacao = cobrancaSituacao;
    }

    /** default constructor */
    public NegativacaoCriterioSituacaoCobranca() {
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
        NegativacaoCriterioSituacaoCobranca castOther = (NegativacaoCriterioSituacaoCobranca) other;

        return new EqualsBuilder().append(this.getCobrancaSituacao().getId(), castOther.getCobrancaSituacao().getId()).isEquals();
    }

   
   /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCobrancaSituacao().getId())
                .toHashCode();
    }

	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}
	
	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}    

}
