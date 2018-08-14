package gcom.seguranca.transacao;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public interface IRepositorioTransacao {


	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
			Integer idOperacao, Integer idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String, String> argumentos, 
			Integer id1, String unidadeNegocio) 
		throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, Integer numeroPagina, String unidadeNegocio)
		throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(Integer idUsuarioAcao,
			 String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
		throws ErroRepositorioException;
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(Integer idUsuarioAcao,
			 String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio) 
		throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarOperacaoOrdemExibicao(int[] idTabelaColuna, int idOperacao) 
		throws ErroRepositorioException;
	
	public Integer pesquisarOperacaoEfetuada(Integer idOperacao,
			Integer argumentoValor, Integer id2)throws ErroRepositorioException;

	public Integer pesquisarTabelaLinhaColunaAlteracao(Integer idObjetoAlterado, 
			Integer idTabelaColuna)throws ErroRepositorioException;
	
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(
			FiltrarAlteracaoAtualizacaoCadastralActionHelper helper)throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public List consultarDadosTabelaColunaAtualizacaoCadastral(
			Long idRegistroAlterado,
			Integer idArquivo, Integer idImovel, Long idCliente,Integer idTipoAlteracao) throws ErroRepositorioException;
	
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral,	Short indicador, 
			Usuario usuario, 
			ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException;
	
	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(
			Integer idArgumento, Short indicador) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public List pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(
			String idEmpresa, String idArquivo, String idLeiturista) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public List pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral(
			Integer idTabelaAtualizacaoCadastral) throws ErroRepositorioException;
	
	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException;
	
	public Integer verificarOperacaoPendente(Integer idObjeto, Integer idUsuario) throws ErroRepositorioException;
	
	public void atualizarOperacaoEfetuadaPendente(Integer idOperacaoEfetuada, Integer idGrupoAtributo) throws ErroRepositorioException;
	
	public void atualizarTabelaLinhaAlteracaoPendente(Integer idOperacaoEfetuada, Integer idImovel) throws ErroRepositorioException;
	
	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) throws ErroRepositorioException;
	
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral) throws ErroRepositorioException;
	
	public boolean existeAlteracaoNaoAprovadaParaImovel(Integer idImovel) throws ErroRepositorioException;
	
	public Cliente obterClientePeloCPF(String cpf,Integer idCliente) throws ErroRepositorioException;
	
	public Cliente obterClientePeloCNPJ(String cnpj,Integer idCliente) throws ErroRepositorioException;
	
	public String obterValorAtualTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna) throws ErroRepositorioException;
	
	public ImovelSubcategoria recuperaImovelSubcategoriaAtualizacaoCadastral(Integer idAtualizacaoCadastral) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelDiferenteImovel(Integer idImovel,Integer idCliente)
		throws ErroRepositorioException;
	
	public ClienteRelacaoTipo recuperaTipoRelacaoClienteAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException;
	
	public ClienteFone recuperaClienteFoneAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException;
	
	public String obterValorAnteriorTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna)
			throws ErroRepositorioException;
	
	public TabelaAtualizacaoCadastral obterIdTabelaAtualizacaoCadastralPorCliente(Integer idCliente, Integer idTabelaColuna)
			throws ErroRepositorioException;
}
