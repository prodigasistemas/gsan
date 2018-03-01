package gcom.cadastro.localidade;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

public interface IRepositorioLocalidade {

	public Collection<Localidade> pesquisarLocalidadePorGerenciaRegional(int idGerenciaRegional) throws ErroRepositorioException;

	public Integer pesquisarIdLocalidade(Integer idImovel) throws ErroRepositorioException;

	public int pesquisarMaximoIdLocalidade() throws ErroRepositorioException;

	public Object[] pesquisarObjetoLocalidadeRelatorio(Integer idLocalidade) throws ErroRepositorioException;

	public Integer verificarExistenciaLocalidade(Integer idLocalidade) throws ErroRepositorioException;

	public void atualizarLogradouroBairroGerenciaRegional(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo)
			throws ErroRepositorioException;

	public void atualizarLogradouroCepGerenciaRegional(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ErroRepositorioException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarTodosIdLocalidade() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarEloPolo() throws ErroRepositorioException;

	public Integer pesquisarIdLocalidadePorConta(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarIdLocalidadePorGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesMunicipio(Integer idMunicipio) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadesImoveis() throws ErroRepositorioException;

	public Integer pesquisarIdLocalidadePorDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

	public Integer pesquisarIdLocalidadePorContaHistorico(Integer idContaHistorico) throws ErroRepositorioException;

	public Integer pesquisarIdLocalidadePorGuiaPagamentoHistorico(Integer idGuiaPagamentoHistorico) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsLocalidades() throws ErroRepositorioException;

	public ConsumoTarifa obterConsumoTarifaPadrao(Integer idLocalidade) throws ErroRepositorioException;
	
	public Integer pesquisarIdLocalidadePorDebitoACobrarHistorico(Integer idDebitoACobrarHistorico)throws ErroRepositorioException;

}
