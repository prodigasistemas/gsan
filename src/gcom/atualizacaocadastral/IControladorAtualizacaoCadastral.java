package gcom.atualizacaocadastral;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarGerarLoteAtualizacaoCadastralActionHelper;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioFichaFiscalizacaoCadastralHelper;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRotaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

public interface IControladorAtualizacaoCadastral {

	public void atualizarImoveisAprovados(Integer idFuncionalidade, Usuario usuarioLogado) throws Exception;

	public void apagarInformacoesRetornoImovelAtualizacaoCadastral(Integer idImovel) throws Exception;

	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Date dataInicial, Date dataFinal, Integer idSituacaoCadastral) throws ControladorException;

	public Integer recuperaValorSequenceImovelRetorno() throws Exception;

	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria, Integer idCategoria) throws ControladorException;

	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel) throws ControladorException;

	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriasAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

	public void aprovarImoveisEmLote(Usuario usuarioLogado, Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis) throws ControladorException;

	public Integer obterquantidadeImoveisAprovadosArquivo(Integer idArquivoAtualizacaoCadastral) throws ControladorException;

	public HashMap<String, Integer> obterDadosAnaliseSituacaoArquivoAtualizacaoCadastral(Integer idArquivo) throws ControladorException;

	public ImovelControleAtualizacaoCadastral obterImovelControlePorImovelRetorno(Integer idImovelRetorno) throws ControladorException;

	public ImovelControleAtualizacaoCadastral obterImovelControle(Integer idImovelControle) throws ControladorException;

	public void fiscalizarImovel(Integer idImovel) throws ControladorException;

	public Collection<RelatorioFichaFiscalizacaoCadastralHelper> pesquisarDadosFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis, boolean dadosOriginais) throws ControladorException;
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ControladorException;
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel, Integer tipoOperacao) throws ControladorException;

	public Collection<ImagemRetorno> pesquisarImagensRetornoPorIdImovel(Integer idImovel) throws ControladorException;

	public Collection<RelatorioRelacaoImoveisRotaBean> pesquisarDadosRelatorioRelacaoImoveisRotaAtualizacaoCadastral(String localidade, String setorComercial, String rota) throws ControladorException;

	public boolean verificarPermissaoAprovarImovel(Integer idUsuarioLogado, Integer idImovel);

	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> pesquisarOcupantesAtualizacaoCadastral(Integer idImovel) throws ControladorException;

	public List<ArquivoTextoAtualizacaoCadastral> regerarArquivosAtualizacaoCadastral(List<Integer> idsArquivos, String tipoArquivo, Date dataUltimaTransmissao, Integer idEmpresa) throws ControladorException;
	
	public List<ArquivoTextoAtualizacaoCadastral> gerarArquivosAtualizacaoCadastralRevisaoOuFiscalizacao(
			Integer situacao, List<Integer> idsArquivos, double percentualAleatorios, Integer idEmpresa, Integer lote) throws ControladorException;
	
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaPorImovel(TabelaColuna coluna, Integer idImovel, String complemento) throws ControladorException;
	
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaPorImovel(TabelaColuna coluna, Integer idImovel, String complemento, String complementoColuna) throws ControladorException;
	
	public TabelaAtualizacaoCadastral pesquisarTabelaPorImovel(Tabela tabela, Integer idImovel, String complemento) throws ControladorException;
	
	public void atualizarImovelRetorno(TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral, String campo) throws ControladorException;

	public void atualizarImovelParaSituacaoEmCampoPorArquivo(Integer idArquivo) throws ControladorException;
	
	public void atualizarSituacaoImovelControle(Integer idImovelControle, Integer idNovaSituacao) throws ControladorException;
	
	public boolean possuiInformacoesFiscalizacao(ImovelControleAtualizacaoCadastral imovelControle) throws ControladorException;
	
	public void atualizarRetornoPreAprovado(ImovelControleAtualizacaoCadastral imovelControle) throws ControladorException;

	public List<Visita> obterVisitasPorImovelControleECoordenadas(ImovelControleAtualizacaoCadastral imovelControle, String latitude, String longitude) throws ControladorException;
	
	public void inserirImagemRetorno(Integer matricula, String nomeImagem, String pasta, Integer idImovelRetorno) throws ControladorException;
	
	public Integer obterIdImovelRetorno(Integer idImovel) throws ControladorException;

	public boolean isImovelParaRemover(ConsultarMovimentoAtualizacaoCadastralHelper item, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) throws ControladorException;
	
	public void apagarImagemRetorno(Integer idImovel) throws Exception;
	
	public boolean existeSubcategoriaRetorno(Integer idImovel, Integer idSubcategoria)  throws ControladorException;
	
	public void atualizarSubcategoriaAoFiscalizar(Integer idImovel)  throws ControladorException;
	
	public void atualizarSubcategoriarAoPreAprovar(Integer idImovel) throws ControladorException;
	
  	public List<ImovelControleAtualizacaoCadastral> obterIdsImovelControleGeracaoLote(FiltrarGerarLoteAtualizacaoCadastralActionHelper helper) throws ControladorException;
 
	public void gerarLote(List<ImovelControleAtualizacaoCadastral> imoveisControle, Integer lote) throws ControladorException;
    
    public Integer obterProximoLote() throws ControladorException;
    
    public boolean isDefinicaoSubcategoriaValida(String idImovel,String[] registrosSelecionados) throws ControladorException;
    
    public Integer reprovarImoveisEmLote(Usuario usuarioLogado, Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis) throws ControladorException;

    public ImovelRetorno pesquisarImovelRetornoPorIdRetorno(Integer idImovelRetorno) throws ControladorException;
    
    public boolean houveMudancaoEconomiasPorCategoria(Integer idImovel) throws ControladorException;
    
    public boolean houveMudancaCategoria(Integer idImovel) throws ControladorException;
    
    public boolean houveMudancaSubcategoria(Integer idImovel) throws ControladorException;
    
    public boolean isImovelAprovadoComAlteracaoFaturamento(Integer idImovel) throws ControladorException;
    
    public boolean isAtualizadaoAntesFaturamento(Integer idImovel, Integer referenciaFaturamento) throws ControladorException;
    
    public String[] getDescricaoMudancaCategoria(Integer idImovel) throws ControladorException;
    
    public String[] getDescricaoMudancaSubcategoria(Integer idImovel) throws ControladorException;
    
    public String[] getDescricaoMudancaEconomiasPorSubcategoria(Integer idImovel) throws ControladorException;
    
    public void emitirTermoAlteracaoCadastral(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException;
    
    public void aprovarImovel(Integer idImovel) throws ControladorException;
    
    public List<Visita> obterVisitasPorCoordenadas(String latitude, String longitude) throws ControladorException;
    
    /**
     * Retorna a data da ultima alteracao de um imovel utilizando o controle de transacoes efeutas.
     * Encontra-se depreciado pois existem varias transacoes e muitas irrelevantes para esse comparacao.
     * 
     * Utilizar o metodo {@code IControladorAtualizacaoCadastral.verificarSeHouveAlteracaoNoImovelRelevanteParaAtualizacaoCadastral} no lugar.
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    @Deprecated
    public Date pesquisarDataDaUltimaAlteracaoDoImovel(Integer idImovel) throws ControladorException;
    
    /**
     * Verifica se houve alteracao no cadastral do imovel em atualizacao cadastral quanto a:
     * - CPF/Cliente/Relacao Cliente
     * - Quantidade de economias;
     * - Subcategoria e categorias;
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSeHouveAlteracaoNoImovelRelevanteParaAtualizacaoCadastral(Integer idImovel) throws ControladorException;
    
    /**
     * Pesquisa o Cliente Retorno pela matricula do imovel
     * e pelo tipo de relacao
     * 
     * 
     * @param matriculaCliente
     * @param tipoRelacao
     * @return
     * @throws ControladorException
     */
    public ClienteRetorno pesquisarClienteRetornoPorMatriculaClienteETipoRelacao(Integer matriculaCliente, Short tipoRelacao)
			throws ControladorException;
}
