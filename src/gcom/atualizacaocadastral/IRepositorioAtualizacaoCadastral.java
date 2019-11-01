package gcom.atualizacaocadastral;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.IImovelTipoOcupanteQuantidade;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarGerarLoteAtualizacaoCadastralActionHelper;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ErroRepositorioException;

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

	public Collection<IClienteFone> obterClienteFoneParaAtualizar(Integer idImovel, Integer idCliente) throws ErroRepositorioException;

	public void apagarImovelQuantidadesOcupantes(Integer idImovel) throws ErroRepositorioException;

	public void apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public void apagarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;

	public void apagarClienteEnderecoRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;

	public void apagarClienteFoneRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;

	public void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException;

	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Integer idSituacaoCadastral, Date dataInicial, Date dataFinal) throws ErroRepositorioException;

	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ErroRepositorioException;
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel, Integer tipoOperacao) throws ErroRepositorioException;

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

	public void aprovarImovel(Integer idImovelRetorno, Date dataLiberacao) throws ErroRepositorioException;

	public Integer obterquantidadeImoveisAprovadosArquivo(Integer idArquivoAtualizacaoCadastral) throws ErroRepositorioException;

	public Integer obterquantidadeImoveisComAlteracaoFaturamentoArquivo(Integer idArquivoAtualizacaoCadastral, String colunaALterada) throws ErroRepositorioException;

	public Integer obterquantidadeImoveisComAnormalidadeArquivo(Integer idArquivoAtualizacaoCadastral) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosOriginaisFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRetornoFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImoveisPorRotaAtualizacaoCadastral(String localidade, String setorComercial, String rota) throws ErroRepositorioException;

	public Collection<ImovelSubcategoriaRetorno> pesquisarSubcategoriasImovelRetorno(Integer idImovel) throws ErroRepositorioException;

	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> pesquisarOcupantesAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

	public Collection<IImovelTipoOcupanteQuantidade> obterImovelQuantidadesOcupantesParaAtualizar(Integer idImovel) throws ErroRepositorioException;

	public List<Integer> obterImoveisPorSituacao(Integer idArquivo, Integer idSituacao) throws ErroRepositorioException;
	
	/**
	 * Busca no banco todos os imoveis de um arquivo, podendo se acrescentar matriculas atraves
	 * do parametro {@code idsImoveis}.
	 * 
	 * NAO SE DEVE PASSAR {@code dataUltimaTransmissao} e {@code idsImoveis} com valores nao
	 * nulos ao mesmo tempo. 
	 * 
	 * 
	 * @param idArquivo - id do arquivo texto atualizacao cadastral
	 * @param situacoes - quais as situacoes devem ter imoveis como NAO INFORMATIVOs
	 * @param dataUltimaTransmissao - data para filtrar em caso de REVISITA
	 * @param idsImoveis - ids dos imoveis que se quer acrescentar como NAO INFORMATIVO
	 * @return {@code ImovelControleAtualizacaoCadastral} com informacoes
	 * @throws ErroRepositorioException
	 */
	public List<ImovelControleAtualizacaoCadastral> obterImoveisPorArquivoSituacaoLoteTrazendoInformativos(Integer idArquivo, List<Integer> situacoes, 
			Date dataUltimaTransmissao, List<Integer> idsImoveis) throws ErroRepositorioException;

	public List<Integer> obterImoveisPorSituacaoELote(Integer idArquivo, Integer idSituacao, Integer lote) throws ErroRepositorioException;
	
	public Integer obterQuantidadeImoveisPorSituacaoELote(Integer idArquivo, Integer idSituacao, Integer lote) throws ErroRepositorioException;
	
	public List<Integer> obterImoveisPorIdArquivoESituacaoELoteParaSorteioComQuantidadeEAleatoriamente(
			Integer idArquivo, Integer idSituacao, Integer lote, Integer quantidade) throws ErroRepositorioException;
	
	public List<Integer> obterImoveisPorLote(Integer lote) throws ErroRepositorioException;
	
	public List<Integer> obterIdsArquivosPorLote(Integer lote) throws ErroRepositorioException;

	public TabelaColunaAtualizacaoCadastral obterTabelaColuna(TabelaColuna coluna, Integer idImovel, String complemento) throws ErroRepositorioException;
	
	public TabelaColunaAtualizacaoCadastral obterTabelaColuna(TabelaColuna coluna, Integer idImovel, String complemento, String complementoColuna) throws ErroRepositorioException;

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
	
	public List<ImovelControleAtualizacaoCadastral> obterIdsImovelControleGeracaoLote(FiltrarGerarLoteAtualizacaoCadastralActionHelper helper) throws ErroRepositorioException;
	
    public boolean isLoteExistente(Integer lote) throws ErroRepositorioException;
    
    public Integer obterProximoLote() throws ErroRepositorioException;
	
	public List<Integer> obterImagensImoveisAprovador() throws ErroRepositorioException;

	public void reprovarImoveis(List<Integer> imoveisParaReprovar) throws ErroRepositorioException;

	public List<TabelaColunaAtualizacaoCadastral> obterTabelaColunas(TabelaColuna coluna, Integer idImovel) throws ErroRepositorioException;
	
	public boolean existeSubcategoriaRetorno(TabelaColunaAtualizacaoCadastral tabelaColuna) throws ErroRepositorioException;
	
	public ImovelRetorno pesquisarImovelRetornoPorIdRetorno(Integer idImovelRetorno) throws ErroRepositorioException;
	
	public List<ImovelSubcategoriaRetorno> pesquisarDadosSubcategoriaRetornoFichaFiscalizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
	
	public Short pesquisarQuantidadeEconomiasOriginais(Integer idImovel, Integer idCategoria) throws ErroRepositorioException;
	
	public List<ImovelSubcategoriaAtualizacaoCadastral> obterSubcategoriasOriginais(Integer idImovel) throws ErroRepositorioException;
	
    public List<ImovelSubcategoriaRetorno> obterSubcategoriasRetorno(Integer idImovel) throws ErroRepositorioException;
    
    public List<Visita> obterVisitasPorCoordenadas(String latitude, String longitude) throws ErroRepositorioException;
    
    /**
     * Marca as visitas dos imoveis controle informados com o 
     * indicadorExclusao = true
     * 
     * Nao passar mais de HUM MIL ids de uma so vez
     * 
     * @param idsImoveisControle
     * @throws ErroRepositorioException
     */
		public void excluirVisitasDosImoveisControle(List<Integer> idsImoveisControle) throws ErroRepositorioException;
		
		/*
     * Compara se um imovel teve alteracao na situacao da agua, na situacao do esgoto 
     * ou alteracao do hidrometro no seu cadastro, estando ele em atualizacao cadastral 
     * 
     * @param idImovel
     * @return true se houve alteracao, false nos demais casos
     * @throws ErroRepositorioException
     */
    public boolean obterIndicadorSeHouveAlteracaoNaSituacaoAguaEEsgotoEHidrometroDoImovelDuranteAtualizacaoCadastral(Integer idImovel)
			throws ErroRepositorioException;
    
    /**
     * Compara as informacoes do cadastro com a de atualizacao cadastral, identificando se o imovel teve mudanca no cadastro
     * quanto a sua categoria, subcategoria ou quantidade de econimias.
     * 
     * @param idImovel
     * @return true se houve alteracao, false nos demais casos
     * @throws ErroRepositorioException
     */
    public boolean obterIndicadorSeHouveAlteracaoNasSubcategoriasEQuantidadesDeEconomiasDoImovelDuranteAtualizacaoCadastral(Integer idImovel)
			throws ErroRepositorioException;
    
    /**
     * Compara as informacoes do cadastro com a de atualizacao cadastral, identificando se o imovel teve mudanca no cadastro
     * de cliente, olhando para o id, tipo_relacao e cpf/cnpj  
     * 
     * @param idImovel
     * @return true se houve alteracao, false nos demais casos
     * @throws ErroRepositorioException
     */
    public boolean obterIndicadorSeHouveAlteracaoNosClientesDoImovelDuranteAtualizacaoCadastral(Integer idImovel)
			throws ErroRepositorioException;
    
    /**
     * Metodo para pesquisar o ClienteRetorno com as informacoes de clie_id e crtp_id (ClienteRelacaoTipo)
     * 
     * @param idImovel
     * @param tipoRelacao
     * @return ClienteRetorno do imovel com esse tipo de relacionamento
     */
    public ClienteRetorno pesquisarClienteRetornoPorMatriculaClienteETipoRelacao(Integer idImovel, Short tipoRelacao)
    		throws ErroRepositorioException;
}