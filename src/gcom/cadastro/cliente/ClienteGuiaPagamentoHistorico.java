package gcom.cadastro.cliente;

import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ClienteGuiaPagamentoHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private GuiaPagamentoHistorico guiaPagamentoHistorico;

    /** persistent field */
    private ClienteRelacaoTipo clienteRelacaoTipo;

    /** full constructor */
    public ClienteGuiaPagamentoHistorico(Integer id, Date ultimaAlteracao, Cliente cliente, GuiaPagamentoHistorico guiaPagamentoHistorico, ClienteRelacaoTipo clienteRelacaoTipo) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.guiaPagamentoHistorico = guiaPagamentoHistorico;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    /** default constructor */
    public ClienteGuiaPagamentoHistorico() {
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

	public GuiaPagamentoHistorico getGuiaPagamentoHistorico() {
		return guiaPagamentoHistorico;
	}

	public void setGuiaPagamentoHistorico(
			GuiaPagamentoHistorico guiaPagamentoHistorico) {
		this.guiaPagamentoHistorico = guiaPagamentoHistorico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
