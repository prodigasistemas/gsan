package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public interface ControladorTransacaoLocal extends javax.ejb.EJBLocalObject {

	@SuppressWarnings("rawtypes")
	public void inserirOperacaoEfetuada(Collection usuariosAcaoUsuarioHelp, OperacaoEfetuada operacaoEfetuada, TabelaLinhaAlteracao tabelaLinhaAlteracao, Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao, Integer idOperacao, Integer idUsuario, Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal, Hashtable<String, String> argumentos, Integer id1, String unidadeNegocio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(Integer idUsuarioAcao, String[] idOperacoes, String idUsuario, Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal, Hashtable<String, String> argumentos, Integer id1, Integer numeroPagina, String unidadeNegocio) throws ControladorException;

	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(Integer idUsuarioAcao, String[] idOperacoes, String idUsuario, Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal, Hashtable<String, String> argumentos, Integer id1, String unidadeNegocio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(Integer idUsuarioAcao, String[] idOperacoes, String idUsuario, Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal, Hashtable<String, String> argumentos, Integer id1, String unidadeNegocio) throws ControladorException;

	public void registrarTransacao(ObjetoTransacao objetoTransacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public HashMap consultarResumoInformacoesOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada, int idItemAnalisado);

	@SuppressWarnings("rawtypes")
	public void ordenarTabelaLinhaColunaAlteracao(Collection linhas, int idOperacao) throws ControladorException;

	public Integer pesquisarOperacaoEfetuada(Integer idOperacao, Integer argumentoValor, Integer id2) throws ControladorException;

	public Integer pesquisarTabelaLinhaColunaAlteracao(Integer idObjetoAlterado, Integer idTabelaColuna) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirOperacaoEfetuadaAtualizacaoCadastral(Collection usuariosAcaoUsuarioHelp, OperacaoEfetuada operacaoEfetuada, TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral, Collection<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral) throws ControladorException;

	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) throws ControladorException;

	public Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> consultarDadosTabelaColunaAtualizacaoCadastral(Long idRegistroAlterado, Integer idArquivo, Integer idImovel, Long idCliente, Integer idTipoAlteracao) throws Exception;

	public void processaRegistroOperacaoObjetohelper(UsuarioAcaoUsuarioHelper usuario, Integer idTipoAlteracao, ObjetoTransacao objetoHelper, OperacaoEfetuada operacaoEfetuada, Integer idTabela);

	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer idImovel, String[] idsAtualizacaoCadastral, Short indicador, Usuario usuarioLogado, String campo, Integer tipoAlteracao) throws ControladorException;

	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(Integer idArgumento, Short indicador) throws ControladorException;

	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(Integer idArgumento, Short indicador) throws ControladorException;

	public void registrarTransacao(ObjetoTransacao objetoTransacao, String[] atributos) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarAtualizarOperacaoPendente(Integer idImovel, Collection colecaoClientes, Integer idUsuario) throws ControladorException;

	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) throws ControladorException;
	
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral) throws ControladorException;
}
