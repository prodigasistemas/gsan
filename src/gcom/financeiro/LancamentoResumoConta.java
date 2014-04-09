package gcom.financeiro;

import gcom.faturamento.conta.Conta;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoResumoConta implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.financeiro.LancamentoResumo lancamentoResumo;

    /** persistent field */
    private Conta conta;

    /** full constructor */
    public LancamentoResumoConta(Date ultimaAlteracao, gcom.financeiro.LancamentoResumo lancamentoResumo, Conta conta) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoResumo = lancamentoResumo;
        this.conta = conta;
    }

    /** default constructor */
    public LancamentoResumoConta() {
    }

    /** minimal constructor */
    public LancamentoResumoConta(gcom.financeiro.LancamentoResumo lancamentoResumo, Conta conta) {
        this.lancamentoResumo = lancamentoResumo;
        this.conta = conta;
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

    public gcom.financeiro.LancamentoResumo getLancamentoResumo() {
        return this.lancamentoResumo;
    }

    public void setLancamentoResumo(gcom.financeiro.LancamentoResumo lancamentoResumo) {
        this.lancamentoResumo = lancamentoResumo;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
