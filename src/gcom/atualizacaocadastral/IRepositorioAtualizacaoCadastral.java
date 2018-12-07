package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.IImovelTipoOcupanteQuantidade;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRepositorioAtualizacaoCadastral {

	public void apagarImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public List<ImovelSubcategoriaRetorno> pesquisarImovelSubcategoriaRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria, Integer idCategoria) throws ErroRepositorioException;

	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

	public Collection<ClienteImovelRetorno> pesquisarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsClienteRetorno(Integer idImovel) throws ErroRepositorioException;

	public Collection<IImovel> obterImoveisParaAtualizar(Integer tipoOperacao) throws ErroRepositorioException;

	public Collection<IImovelSubcategoria> obterImovelSubcategoriaParaAtualizar(Integer idImovel) throws ErroRepositorioException;

	public Collection<IImovelRamoAtividade> obterImovelRamoAtividadeParaAtualizar(Integer idImovel) throws ErroRepositorioException;

	public Collection<IClienteFone> obterClienterFoneParaAtualizar(Integer idImovel) throws ErroRepositorioException;

	public void apagarImovelQuantidadesOcupantes(Integer idImovel) throws ErroRepositorioException;

	public void apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public void apagarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public void apagarClienteEnderecoRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;

	public void apagarClienteFoneRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;

	public void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;

	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Integer idSituacaoCadastral, Date dataInicial, Date dataFinal) throws ErroRepositorioException;

	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ErroRepositorioException;

	public Integer recuperaValorSequenceImovelRetorno() throws ErroRepositorioException;

	public void apagarImagemRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<ImovelControleAtualizacaoCadastral> obterImoveisControle(Collection<IImovel> listaImoveisRetorno);

	public ImovelControleAtualizacaoCadastral obterImovelControlePorImovelRetorno(Integer idImovelRetorno);

	public ImovelControleAtualizacaoCadastral obterImovelControle(Integer idImovel);

	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriasAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

	public Collection<ClienteImovelRetorno> obterClientesParaAtualizar() throws ErroRepositorioException;

	public ICliente pesquisarClienteRetorno(ClienteImovelRetorno clienteImovel) throws ErroRepositorioException;

	public IImovel pesquisarImovelRetorno(Integer idImovel) throws ErroRepositorioException;

	public Collection<IClienteFone> pesquisarClienteFoneRetorno(Integer idCliente) throws ErroRepositorioException;

	public Collection<IClienteEndereco> pesquisarClienteEnderecoRetorno(Integer idCliente) throws ErroRepositorioException;

	public Collection<ClienteImovelRetorno> obterClienteImoveisDoImovel(Integer idImovelRetorno) throws ErroRepositorioException;

	public Collection<ClienteImovelRetorno> obterClientesNovaRelacao() throws ErroRepositorioException;

	public boolean existeRelacaoClienteImovel(Integer idImovel, Integer idCliente, Integer idClienteRelacaoTipo) throws ErroRepositorioException;

	public Collection<ClienteImovelRetorno> obterClientesParaIncluir() throws ErroRepositorioException;

	public Collection<IClienteImovel> obterClientesParaExcluirRelacao() throws ErroRepositorioException;

	public void aprovarImovel(Integer idImovelRetorno) throws ErroRepositorioException;

	public Integer obterquantidadeImoveisAprovadosArquivo(Integer idArquivoAtualizacaoCadastral) throws ErroRepositorioException;

	public Integer obterquantidadeImoveisComAlteracaoFaturamentoArquivo(Integer idArquivoAtualizacaoCadastral, String colunaALterada) throws ErroRepositorioException;

	public Integer obterquantidadeImoveisComAnormalidadeArquivo(Integer idArquivoAtualizacaoCadastral) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImoveisPorRotaAtualizacaoCadastral(String localidade, String setorComercial, String rota) throws ErroRepositorioException;

	public Collection<ImovelSubcategoriaRetorno> pesquisarSubcategoriasImovelRetorno(Integer idImovel) throws ErroRepositorioException;

	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> pesquisarOcupantesAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

	public Collection<IImovelTipoOcupanteQuantidade> obterImovelQuantidadesOcupantesParaAtualizar(Integer idImovel) throws ErroRepositorioException;

	public List<Integer> obterImoveisPorSituacao(Integer idArquivo, Integer idSituacao) throws ErroRepositorioException;

	public List<Integer> obterImoveisPorSituacaoELote(Integer idArquivo, Integer idSituacao, Integer lote) throws ErroRepositorioException;

	public TabelaColunaAtualizacaoCadastral obterTabelaColuna(TabelaColuna coluna, Integer idImovel, String complemento) throws ErroRepositorioException;

	public TabelaAtualizacaoCadastral obterTabela(Tabela tabela, Integer idImovel, String complemento) throws ErroRepositorioException;

	public void atualizarImovelRetorno(TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral, String campo) throws ErroRepositorioException;

	public void atualizarImovelParaSituacaoEmCampoPorArquivo(Integer idArquivo) throws ErroRepositorioException;

	public boolean possuiInformacoesFiscalizacao(ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException;

	public List<TabelaColunaAtualizacaoCadastral> obterColunasPreAprovadas(ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException;

	public List<Integer> obterImoveisARevisitar(Integer idArquivo, Date dataUltimaTransmissao) throws ErroRepositorioException;

	public List<Visita> pesquisarVisitasPorImovelControle(ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException;

	public void atualizarSituacaoConjuntoImovelControle(Integer situacao, List<Integer> ids) throws ErroRepositorioException;

	public List<Visita> pesquisarVisitasPorImovelControleELatitudeELongitude(ImovelControleAtualizacaoCadastral imovelControle, String latitude, String longitude) throws ErroRepositorioException;

	public List<ImovelControleAtualizacaoCadastral> obterImoveisControlePorImovel(List<Integer> ids) throws ErroRepositorioException;
	
	public Integer obterIdImovelRetorno(Integer idImovel) throws ErroRepositorioException;

	public boolean possuiClienteComCpfOuCnpjCadastrado(Integer idImovel) throws ErroRepositorioException;
	
	public boolean possuiClienteComCpfOuCnpjTransmitido(Integer idImovel) throws ErroRepositorioException;
	
	public boolean existeSubcategoriaRetorno(Integer idImovel, Integer idSubcategoria) throws ErroRepositorioException;
	
	public void atualizarSubcategoriarAoFiscalizar(Integer idImovel) throws ErroRepositorioException;
	
	public void atualizarSubcategoriarAoPreAprovar(Integer idImovel) throws ErroRepositorioException;
	
	public List<ImovelControleAtualizacaoCadastral> obterIdsImovelControleGeracaoLote(Integer idLocalidade, Integer codigoSetor, String dataInicio, String dataFim, Integer idLeiturista, boolean incluirImoveisNovos) throws ErroRepositorioException;

    public boolean isLoteExistente(Integer lote) throws ErroRepositorioException;
    
    public Integer obterProximoLote() throws ErroRepositorioException;
	
	public List<Integer> obterImagensImoveisAprovador() throws ErroRepositorioException;

	public void reprovarImoveis(List<Integer> imoveisParaReprovar) throws ErroRepositorioException;

	public List<TabelaColunaAtualizacaoCadastral> obterTabelaColunas(TabelaColuna coluna, Integer idImovel) throws ErroRepositorioException;
	
	public boolean existeSubcategoriaRetorno(TabelaColunaAtualizacaoCadastral tabelaColuna) throws ErroRepositorioException;
}
