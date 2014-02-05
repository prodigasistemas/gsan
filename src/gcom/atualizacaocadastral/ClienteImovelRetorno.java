package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.IClienteAtualizacaoCadastral;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.Imovel;

import java.util.Date;

public class ClienteImovelRetorno extends ClienteRetorno implements IClienteImovel {
	
	private Integer id;
	private Imovel imovel;
	private Cliente cliente;
	private ClienteRelacaoTipo clienteRelacaoTipo;
	
	private Date ultimaAlteracao;
	private Short indicadorNomeConta;
	private Integer idClienteRetorno;
	private Integer idImovelRetorno;

	public ClienteImovelRetorno() {
		
	}
	
	public ClienteImovelRetorno(IClienteAtualizacaoCadastral clienteTxt, int matriculaImovel) {
		this.imovel = new Imovel(new Integer(matriculaImovel));
		this.cliente = new Cliente(clienteTxt.getIdCliente());
		this.clienteRelacaoTipo = new ClienteRelacaoTipo(clienteTxt.getIdClienteRelacaoTipo());
	}
	
	public Imovel getImovel() {
		return imovel;
	}
	
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public ClienteRelacaoTipo getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}
	
	public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
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

	public Integer getIdClienteRetorno() {
		return idClienteRetorno;
	}

	public void setIdClienteRetorno(Integer idClienteRetorno) {
		this.idClienteRetorno = idClienteRetorno;
	}

	public Integer getIdImovelRetorno() {
		return idImovelRetorno;
	}

	public void setIdImovelRetorno(Integer idImovelRetorno) {
		this.idImovelRetorno = idImovelRetorno;
	}
	
}
