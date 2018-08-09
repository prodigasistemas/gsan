package gcom.atualizacaocadastral;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioFichaFiscalizacaoCadastralHelper;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRotaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

	public Collection<RelatorioFichaFiscalizacaoCadastralHelper> pesquisarDadosFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ControladorException;

	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ControladorException;

	public Collection<ImagemRetorno> pesquisarImagensRetornoPorIdImovel(Integer idImovel) throws ControladorException;

	public Collection<RelatorioRelacaoImoveisRotaBean> pesquisarDadosRelatorioRelacaoImoveisRotaAtualizacaoCadastral(String localidade, String setorComercial, String rota) throws ControladorException;

	public boolean verificarPermissaoAprovarImovel(Integer idUsuarioLogado, Integer idImovel);

	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> pesquisarOcupantesAtualizacaoCadastral(Integer idImovel) throws ControladorException;

	public List<ArquivoTextoAtualizacaoCadastral> regerarArquivosAtualizacaoCadastral(List<Integer> idsArquivos, double percentualFiscalizacao) throws ControladorException;
	
	public List<ArquivoTextoAtualizacaoCadastral> regerarArquivosAtualizacaoCadastral(List<Integer> idsArquivos) throws ControladorException;
	
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaPorImovel(TabelaColuna coluna, Integer idImovel) throws ControladorException;
	
	public TabelaAtualizacaoCadastral pesquisarTabelaPorImovel(Tabela tabela, Integer idImovel) throws ControladorException;
	
	public void atualizarImovelRetorno(TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral) throws ControladorException;

	public void atualizarImovelParaSituacaoEmCampoPorArquivo(Integer idArquivo) throws ControladorException;
}