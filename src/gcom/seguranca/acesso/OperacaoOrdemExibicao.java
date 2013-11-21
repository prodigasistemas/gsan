package gcom.seguranca.acesso;

import gcom.seguranca.transacao.TabelaColuna;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OperacaoOrdemExibicao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.seguranca.acesso.OperacaoOrdemExibicaoPK comp_id;

    /** nullable persistent field */
    private Integer numeroOrdem;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.seguranca.acesso.Operacao operacao;

    /** nullable persistent field */
    private TabelaColuna tabelaColuna;

    /** full constructor */
    public OperacaoOrdemExibicao(gcom.seguranca.acesso.OperacaoOrdemExibicaoPK comp_id, Integer numeroOrdem, Date ultimaAlteracao, gcom.seguranca.acesso.Operacao operacao, TabelaColuna tabelaColuna) {
        this.comp_id = comp_id;
        this.numeroOrdem = numeroOrdem;
        this.ultimaAlteracao = ultimaAlteracao;
        this.operacao = operacao;
        this.tabelaColuna = tabelaColuna;
    }

    /** default constructor */
    public OperacaoOrdemExibicao() {
    }

    /** minimal constructor */
    public OperacaoOrdemExibicao(gcom.seguranca.acesso.OperacaoOrdemExibicaoPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.seguranca.acesso.OperacaoOrdemExibicaoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.OperacaoOrdemExibicaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getNumeroOrdem() {
        return this.numeroOrdem;
    }

    public void setNumeroOrdem(Integer numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.seguranca.acesso.Operacao getOperacao() {
        return this.operacao;
    }

    public void setOperacao(gcom.seguranca.acesso.Operacao operacao) {
        this.operacao = operacao;
    }

    public TabelaColuna getTabelaColuna() {
        return this.tabelaColuna;
    }

    public void setTabelaColuna(TabelaColuna tabelaColuna) {
        this.tabelaColuna = tabelaColuna;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OperacaoOrdemExibicao) ) return false;
        OperacaoOrdemExibicao castOther = (OperacaoOrdemExibicao) other;
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
