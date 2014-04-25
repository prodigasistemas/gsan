package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorMovimentoRegRetMot implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoReg;

    /** persistent field */
    private gcom.cobranca.NegativadorRetornoMotivo negativadorRetornoMotivo;

    /** full constructor */
    public NegativadorMovimentoRegRetMot(Integer id, Date ultimaAlteracao, gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoReg, gcom.cobranca.NegativadorRetornoMotivo negativadorRetornoMotivo) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
        this.negativadorRetornoMotivo = negativadorRetornoMotivo;
    }

    /** default constructor */
    public NegativadorMovimentoRegRetMot() {
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

    public gcom.cobranca.NegativadorMovimentoReg getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public gcom.cobranca.NegativadorRetornoMotivo getNegativadorRetornoMotivo() {
        return this.negativadorRetornoMotivo;
    }

    public void setNegativadorRetornoMotivo(gcom.cobranca.NegativadorRetornoMotivo negativadorRetornoMotivo) {
        this.negativadorRetornoMotivo = negativadorRetornoMotivo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
