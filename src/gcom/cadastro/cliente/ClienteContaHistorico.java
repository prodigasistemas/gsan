package gcom.cadastro.cliente;

import gcom.faturamento.conta.ContaHistorico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ClienteContaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorNomeConta;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private ClienteRelacaoTipo clienteRelacaoTipo;

    /** persistent field */
    private ContaHistorico contaHistorico;

    /** full constructor */
    public ClienteContaHistorico(Integer id, short indicadorNomeConta, Date ultimaAlteracao, Cliente cliente, ClienteRelacaoTipo clienteRelacaoTipo, ContaHistorico contaHistorico) {
        this.id = id;
        this.indicadorNomeConta = indicadorNomeConta;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.contaHistorico = contaHistorico;
    }

    /** default constructor */
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

    public short getIndicadorNomeConta() {
        return indicadorNomeConta;
    }

    public void setIndicadorNomeConta(short indicadorNomeConta) {
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

}
