package gcom.seguranca.transacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TransacaoEfetuada implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.seguranca.transacao.Transacao transacao;

    /** full constructor */
    public TransacaoEfetuada(Date ultimaAlteracao, gcom.seguranca.transacao.Transacao transacao) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.transacao = transacao;
    }

    /** default constructor */
    public TransacaoEfetuada() {
    }

    /** minimal constructor */
    public TransacaoEfetuada(gcom.seguranca.transacao.Transacao transacao) {
        this.transacao = transacao;
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

    public gcom.seguranca.transacao.Transacao getTransacao() {
        return this.transacao;
    }

    public void setTransacao(gcom.seguranca.transacao.Transacao transacao) {
        this.transacao = transacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
