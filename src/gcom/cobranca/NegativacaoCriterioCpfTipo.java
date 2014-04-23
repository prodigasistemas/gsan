package gcom.cobranca;

import gcom.cadastro.CpfTipo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioCpfTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public final static Integer CLIENTE_USUARIO = 1;
	public final static Integer CLIENTE_PROPRIETARIO = 2;
	public final static Integer CLIENTE_RESPONSAVEL = 3;
	public final static Integer RESPONSAVEL_PELO_PARCELAMENTO = 4;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Short numeroOrdemSelecao;

    /** persistent field */
    private short indicadorCoincidente;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** persistent field */
    private CpfTipo cpfTipo;

    /** full constructor */
    public NegativacaoCriterioCpfTipo(Integer id, Short numeroOrdemSelecao, short indicadorCoincidente, Date ultimaAlteracao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, CpfTipo cpfTipo) {
        this.id = id;
        this.numeroOrdemSelecao = numeroOrdemSelecao;
        this.indicadorCoincidente = indicadorCoincidente;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.cpfTipo = cpfTipo;
    }

    /** default constructor */
    public NegativacaoCriterioCpfTipo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNumeroOrdemSelecao() {
        return this.numeroOrdemSelecao;
    }

    public void setNumeroOrdemSelecao(Short numeroOrdemSelecao) {
        this.numeroOrdemSelecao = numeroOrdemSelecao;
    }

    public short getIndicadorCoincidente() {
        return this.indicadorCoincidente;
    }

    public void setIndicadorCoincidente(short indicadorCoincidente) {
        this.indicadorCoincidente = indicadorCoincidente;
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

    public CpfTipo getCpfTipo() {
        return this.cpfTipo;
    }

    public void setCpfTipo(CpfTipo cpfTipo) {
        this.cpfTipo = cpfTipo;
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
        NegativacaoCriterioCpfTipo castOther = (NegativacaoCriterioCpfTipo) other;

        return new EqualsBuilder().append(this.getCpfTipo().getId(), castOther.getCpfTipo().getId()).isEquals();
    }

   
   /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCpfTipo().getId())
                .toHashCode();
    }    

}
