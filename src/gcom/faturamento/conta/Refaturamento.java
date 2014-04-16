package gcom.faturamento.conta;

import gcom.cadastro.funcionario.Funcionario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Refaturamento implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private BigDecimal valorAntesRefaturamento;

    /** persistent field */
    private BigDecimal valorAposRefaturamento;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.conta.Conta conta;

    /** persistent field */
    private Funcionario funcionario;

    /** persistent field */
    private gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento;

    /** persistent field */
    private gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao;

    /** persistent field */
    private gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao;

    /** full constructor */
    public Refaturamento(BigDecimal valorAntesRefaturamento, BigDecimal valorAposRefaturamento, Date ultimaAlteracao, gcom.faturamento.conta.Conta conta, Funcionario funcionario, gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento, gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao, gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao) {
        this.valorAntesRefaturamento = valorAntesRefaturamento;
        this.valorAposRefaturamento = valorAposRefaturamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.conta = conta;
        this.funcionario = funcionario;
        this.contaMotivoCancelamento = contaMotivoCancelamento;
        this.contaMotivoRevisao = contaMotivoRevisao;
        this.contaMotivoRetificacao = contaMotivoRetificacao;
    }

    /** default constructor */
    public Refaturamento() {
    }

    /** minimal constructor */
    public Refaturamento(BigDecimal valorAntesRefaturamento, BigDecimal valorAposRefaturamento, gcom.faturamento.conta.Conta conta, Funcionario funcionario, gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento, gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao, gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao) {
        this.valorAntesRefaturamento = valorAntesRefaturamento;
        this.valorAposRefaturamento = valorAposRefaturamento;
        this.conta = conta;
        this.funcionario = funcionario;
        this.contaMotivoCancelamento = contaMotivoCancelamento;
        this.contaMotivoRevisao = contaMotivoRevisao;
        this.contaMotivoRetificacao = contaMotivoRetificacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorAntesRefaturamento() {
        return this.valorAntesRefaturamento;
    }

    public void setValorAntesRefaturamento(BigDecimal valorAntesRefaturamento) {
        this.valorAntesRefaturamento = valorAntesRefaturamento;
    }

    public BigDecimal getValorAposRefaturamento() {
        return this.valorAposRefaturamento;
    }

    public void setValorAposRefaturamento(BigDecimal valorAposRefaturamento) {
        this.valorAposRefaturamento = valorAposRefaturamento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.conta.Conta getConta() {
        return this.conta;
    }

    public void setConta(gcom.faturamento.conta.Conta conta) {
        this.conta = conta;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public gcom.faturamento.conta.ContaMotivoCancelamento getContaMotivoCancelamento() {
        return this.contaMotivoCancelamento;
    }

    public void setContaMotivoCancelamento(gcom.faturamento.conta.ContaMotivoCancelamento contaMotivoCancelamento) {
        this.contaMotivoCancelamento = contaMotivoCancelamento;
    }

    public gcom.faturamento.conta.ContaMotivoRevisao getContaMotivoRevisao() {
        return this.contaMotivoRevisao;
    }

    public void setContaMotivoRevisao(gcom.faturamento.conta.ContaMotivoRevisao contaMotivoRevisao) {
        this.contaMotivoRevisao = contaMotivoRevisao;
    }

    public gcom.faturamento.conta.ContaMotivoRetificacao getContaMotivoRetificacao() {
        return this.contaMotivoRetificacao;
    }

    public void setContaMotivoRetificacao(gcom.faturamento.conta.ContaMotivoRetificacao contaMotivoRetificacao) {
        this.contaMotivoRetificacao = contaMotivoRetificacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
