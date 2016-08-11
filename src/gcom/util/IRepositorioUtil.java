package gcom.util;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;

import java.util.Collection;
import java.util.List;

public interface IRepositorioUtil {
	
	public Object obterPorId(Class classe, Integer id) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public int registroMaximo(Class classe) throws ErroRepositorioException;
	
	public Collection listar(Class classe) throws ErroRepositorioException;	

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo, String parametro1, String parametro2) throws ErroRepositorioException;

	public SistemaParametro pesquisarParametrosDoSistema() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro, String pacoteNomeObjeto, int limite) throws ErroRepositorioException;

	public Object inserir(Object objeto) throws ErroRepositorioException;

	public void atualizar(Object objeto) throws ErroRepositorioException;

	public void remover(int id, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper) throws ErroRepositorioException;

	public void remover(Object objeto) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException;

	public Object inserirOuAtualizar(Object objeto) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Collection ids, Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, int pageOffset, String pacoteNomeObjeto) throws ErroRepositorioException;

	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void inserirBatch(List list) throws ErroRepositorioException;

	public Collection<NacionalFeriado> pesquisarFeriadosNacionais() throws ErroRepositorioException;

	public Object obterNextVal(Object objeto) throws ErroRepositorioException;

	public DbVersaoBase pesquisarDbVersaoBase() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException;
	
	public Object inserirComCommit(Object objeto) throws ErroRepositorioException;
}
