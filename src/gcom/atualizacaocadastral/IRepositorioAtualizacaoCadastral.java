package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRepositorioAtualizacaoCadastral {

	
	public void apagarImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	public List<ImovelSubcategoriaRetorno> pesquisarImovelSubcategoriaRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria) throws ErroRepositorioException;
	
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

	public Collection<ClienteImovelRetorno> pesquisarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdsClienteRetorno(Integer idImovel) throws ErroRepositorioException;

	
	public Collection<IImovel> obterImoveisParaAtualizar() throws ErroRepositorioException;
	
	public Collection<IImovelSubcategoria> obterImovelSubcategoriaParaAtualizar(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<IImovelRamoAtividade> obterImovelRamoAtividadeParaAtualizar(Integer idImovel) throws ErroRepositorioException;

	public Collection<IClienteFone> obterClienterFoneParaAtualizar(Integer idImovel) throws ErroRepositorioException;


	public void apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	public void apagarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	public void apagarClienteEnderecoRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;
	
	public void apagarClienteFoneRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;
	
	public void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;
	
	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisAprovadosPorPeriodo(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarImoveisDisponiveisPorPeriodo(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarImoveisTransmitidosPorPeriodo(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ErroRepositorioException;
	
	public Integer recuperaValorSequenceImovelRetorno() throws ErroRepositorioException;
}
