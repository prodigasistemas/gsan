package gcom.cadastro.cliente;

import gcom.faturamento.conta.IConta;

import java.util.Date;

public interface IClienteConta {

	public Integer getId();
    public Cliente getCliente();
    public ClienteRelacaoTipo getClienteRelacaoTipo();
    public IConta getConta();
    public Short getIndicadorNomeConta();
    public Date getUltimaAlteracao();

    public void setId(Integer id);
    public void setCliente(Cliente cliente);
    public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo);
    public void setConta(IConta conta);
	public void setIndicadorNomeConta(Short indicadorNomeConta);
	public void setUltimaAlteracao(Date ultimaAlteracao);

}
