package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Imovel;

import java.util.Date;

public interface IClienteImovel {
	
	public abstract Imovel getImovel();
	
	public abstract void setImovel(Imovel imovel);
	
	public abstract Cliente getCliente();
	
	public abstract void setCliente(Cliente cliente);
	
	public abstract ClienteRelacaoTipo getClienteRelacaoTipo();
	
	public abstract void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo);
	 
	public abstract Date getUltimaAlteracao();
	
	public abstract void setUltimaAlteracao(Date ultimaAlteracao);
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public Short getIndicadorNomeConta();

	public void setIndicadorNomeConta(Short indicadorNomeConta);
	
}
