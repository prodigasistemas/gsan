package gcom.cadastro.cliente;

import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.IConta;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ClienteConta implements Serializable, IClienteConta {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
	private Short indicadorNomeConta;
    private Date ultimaAlteracao;
    private Cliente cliente;
    private ClienteRelacaoTipo clienteRelacaoTipo;
    private Conta conta;

    public ClienteConta(Cliente cliente, ClienteRelacaoTipo clienteRelacaoTipo, Conta conta) {
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.conta = conta;
    }

    public ClienteConta() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteRelacaoTipo getClienteRelacaoTipo() {
        return this.clienteRelacaoTipo;
    }

    public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo) {
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    public IConta getConta() {
        return this.conta;
    }

    public void setConta(IConta conta) {
    	if (conta != null) {
    		this.conta = new Conta(conta.getId());
    	}
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
