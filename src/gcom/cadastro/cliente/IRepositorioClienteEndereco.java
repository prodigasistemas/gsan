package gcom.cadastro.cliente;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.localidade.Localidade;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

public interface IRepositorioClienteEndereco {

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteEndereco(FiltroClienteEndereco filtroClienteEndereco) throws ErroRepositorioException;

	public Collection<Cliente> pesquisarClienteEnderecoClienteImovel(FiltroClienteEndereco filtroClienteEndereco) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarEnderecosClienteAbreviado(Integer idCliente) throws ErroRepositorioException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ErroRepositorioException;

	public Localidade pesquisarLocalidadeCliente(Integer idCliente) throws ErroRepositorioException;
}
