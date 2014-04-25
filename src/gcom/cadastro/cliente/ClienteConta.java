package gcom.cadastro.cliente;

import gcom.faturamento.conta.Conta;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ClienteConta implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

	/**
	 * persistent field
	 */
	private Short indicadorNomeConta;
    
	/** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private gcom.cadastro.cliente.Cliente cliente;

    /** persistent field */
    private gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo;

    /** persistent field */
    private Conta conta;

    /** full constructor */
    public ClienteConta(gcom.cadastro.cliente.Cliente cliente, gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo, Conta conta) {
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.conta = conta;
    }

    /** default constructor */
    public ClienteConta() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public gcom.cadastro.cliente.Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
        this.cliente = cliente;
    }

    public gcom.cadastro.cliente.ClienteRelacaoTipo getClienteRelacaoTipo() {
        return this.clienteRelacaoTipo;
    }

    public void setClienteRelacaoTipo(gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo) {
        this.clienteRelacaoTipo = clienteRelacaoTipo;
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

	public Short getIndicadorNomeConta() {
		return indicadorNomeConta;
	}

	public void setIndicadorNomeConta(Short indicadorNomeConta) {
		this.indicadorNomeConta = indicadorNomeConta;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
