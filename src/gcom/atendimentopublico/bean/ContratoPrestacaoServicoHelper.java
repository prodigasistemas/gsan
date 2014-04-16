package gcom.atendimentopublico.bean;

import gcom.cadastro.cliente.Cliente;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ContratoPrestacaoServicoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Cliente cliente;
    
    private String nomeLocalidade;
    
    private String nomeMunicipio;
    
    private Cliente clienteResponsavel;
    
    private String enderecoCliente;
    
    private String enderecoImovel;
    
    private String categoria;
    
    private Integer consumoMinimo;
    
	public ContratoPrestacaoServicoHelper(){}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteResponsavel() {
		return clienteResponsavel;
	}

	public void setClienteResponsavel(Cliente clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}

	

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	
	

}
