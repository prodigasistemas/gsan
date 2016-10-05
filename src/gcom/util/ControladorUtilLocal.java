package gcom.util;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ControladorUtilLocal extends javax.ejb.EJBLocalObject {

	public Object obterPorId(Class classe, Integer id) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public int registroMaximo(Class classe) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo, String parametro1, String parametro2) throws ControladorException;

	public SistemaParametro pesquisarParametrosDoSistema() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro, String pacoteNomeObjeto, int limite) throws ControladorException;

	public Object inserir(Object objeto) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Collection ids, Filtro filtro, String pacoteNomeObjeto) throws ControladorException;

	public void remover(String[] ids, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;

	public void remover(Object object) throws ControladorException;

	public void removerUm(int id, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;

	public Object inserirOuAtualizar(Object objeto) throws ControladorException;

	public void atualizar(Object objeto) throws ControladorException;

	public void validarCampoFinalMaiorIgualCampoInicial(Date inicio, Date fim, String msgErro) throws ControladorException;

	public void validarCampoFinalMaiorIgualCampoInicial(Integer inicio, Integer fim, String msgErro) throws ControladorException;

	public void validarCampoFinalMaiorIgualCampoInicial(BigDecimal inicio, BigDecimal fim, String msgErro) throws ControladorException;

	public void validarDataMenorDataAtual(Date data, String msgErro) throws ControladorException;

	public void validarAnoMesMenorAnoMesAtual(Integer anoMes, String msgErro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, int pageOffset, String pacoteNomeObjeto) throws ControladorException;

	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto) throws ControladorException;

	public void validarAnoMesInicialFinalPeriodo(String anoMesReferenciaInicial, String anoMesReferenciaFinal, String descricaoCampoAnoMesReferenciaInicial,
			String descricaoAnoMesReferenciaFinal, String mensagemErroDoApplicationProperties) throws ControladorException;

	public void verificarDataInicialFinalPeriodo(String dataPeriodoInicial, String dataPeriodoFinal, String descricaoCampoDataReferenciaInicial,
			String descricaoDataReferenciaFinal, String mensagemErroDoApplicationProperties) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirBatch(List list) throws ControladorException;

	public void verificaObjetoRemocao(int id, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto, String nomeTabela) throws ControladorException;

	public Collection<NacionalFeriado> pesquisarFeriadosNacionais() throws ControladorException;

	public void atualizarSistemaParametro(SistemaParametro sistemaParametro) throws ControladorException;

	public DbVersaoBase pesquisarDbVersaoBase() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Map<Integer, Map<Object, Object>> dividirColecao(Collection colecao);

	public void mandaArquivoLeituraEmail(String nomeArquivo, StringBuilder arquivo, String emailReceptor, String emailRemetente, String tituloMensagem,
			String corpoMensagem) throws ControladorException;

	public Integer calcularDiferencaDiasUteisEntreDuasDatas(Date dataInicio, Date dataFim, Municipio municipio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto) throws ControladorException;
	
	public Object inserirComCommit(Object objeto) throws ControladorException;
	
	public String getCaminhoDownloadArquivos(String modulo);
	
	public Collection listar(Class tipo) throws ControladorException;
}
