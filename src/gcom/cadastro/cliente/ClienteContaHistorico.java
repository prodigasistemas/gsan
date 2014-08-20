package gcom.cadastro.cliente;

import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.IConta;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ClienteContaHistorico implements Serializable, IClienteConta {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private Short indicadorNomeConta;
    private Date ultimaAlteracao;
    private Cliente cliente;
    private ClienteRelacaoTipo clienteRelacaoTipo;
    private ContaHistorico contaHistorico;

    public ClienteContaHistorico(Integer id, short indicadorNomeConta, Date ultimaAlteracao, Cliente cliente, ClienteRelacaoTipo clienteRelacaoTipo, ContaHistorico contaHistorico) {
        this.id = id;
        this.indicadorNomeConta = indicadorNomeConta;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.contaHistorico = contaHistorico;
    }

    public ClienteContaHistorico() {
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteRelacaoTipo getClienteRelacaoTipo() {
        return clienteRelacaoTipo;
    }

    public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo) {
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    public ContaHistorico getContaHistorico() {
        return contaHistorico;
    }

    public void setContaHistorico(ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public IConta getConta() {
		return contaHistorico;
	}

	public void setConta(IConta conta) {
		if (conta != null) {
			this.contaHistorico = new ContaHistorico(conta.getId());
		}
	}

}
