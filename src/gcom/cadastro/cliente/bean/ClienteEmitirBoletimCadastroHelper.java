package gcom.cadastro.cliente.bean;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;

import java.util.Collection;

/**
 * 
 * Helper usado para o [UC0582] - Emitir Boletim de Cadastro
 *
 * @author Rafael Corrêa
 * @date 16/05/2007
 */
public class ClienteEmitirBoletimCadastroHelper {

	private Cliente cliente;
	
	private ClienteEndereco clienteEndereco;
	
	private Collection<ClienteFone> clientesFone;
	
	private String enderecoFormatado;
	
	private TarifaSocialDadoEconomia tarifaSocialDadoEconomia;

	/**
	 * @return Retorna o campo cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Retorna o campo clienteEndereco.
	 */
	public ClienteEndereco getClienteEndereco() {
		return clienteEndereco;
	}

	/**
	 * @param clienteEndereco O clienteEndereco a ser setado.
	 */
	public void setClienteEndereco(ClienteEndereco clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}

	/**
	 * @return Retorna o campo clienteFone.
	 */
	public Collection getClientesFone() {
		return clientesFone;
	}

	/**
	 * @param clienteFone O clienteFone a ser setado.
	 */
	public void setClienteFone(Collection clientesFone) {
		this.clientesFone = clientesFone;
	}
	
	/**
	 * @return Retorna o campo enderecoFormatado.
	 */
	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}

	/**
	 * @param enderecoFormatado O enderecoFormatado a ser setado.
	 */
	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}

	public TarifaSocialDadoEconomia getTarifaSocialDadoEconomia() {
		return tarifaSocialDadoEconomia;
	}

	public void setTarifaSocialDadoEconomia(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia) {
		this.tarifaSocialDadoEconomia = tarifaSocialDadoEconomia;
	}
	
}
