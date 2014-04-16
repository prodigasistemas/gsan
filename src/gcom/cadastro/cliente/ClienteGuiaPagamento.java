package gcom.cadastro.cliente;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ClienteGuiaPagamento extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroClienteGuiaPagamento filtroClienteGuiaPagamento = new FiltroClienteGuiaPagamento();

		filtroClienteGuiaPagamento.adicionarParametro(new ParametroSimples(
				FiltroClienteGuiaPagamento.ID, this.getId()));
		filtroClienteGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
		filtroClienteGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento");

		return filtroClienteGuiaPagamento;
	}

    /** identifier field */
    private Integer id;

    /** persistent field */
    private gcom.cadastro.cliente.Cliente cliente;

    /** persistent field */
    private gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo;

    /** persistent field */
    private GuiaPagamento guiaPagamento;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** full constructor */
    public ClienteGuiaPagamento(gcom.cadastro.cliente.Cliente cliente, gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo, GuiaPagamento guiaPagamento) {
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.guiaPagamento = guiaPagamento;
    }

    /** default constructor */
    public ClienteGuiaPagamento() {
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

    public GuiaPagamento getGuiaPagamento() {
        return this.guiaPagamento;
    }

    public void setGuiaPagamento(GuiaPagamento guiaPagamento) {
        this.guiaPagamento = guiaPagamento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
