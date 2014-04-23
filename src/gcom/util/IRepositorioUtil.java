package gcom.util;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;

import java.util.Collection;
import java.util.List;

/**
 * Interface para o repositório de util
 * 
 * @author rodrigo
 */
public interface IRepositorioUtil {

	/**
	 * Retorna a contagem máxima de registros de uma determinada classe no
	 * sistema
	 * 
	 * @param classe
	 *            Classe (.class) que será feita a contagem
	 * @return Número de registros
	 * @exception ErroRepositorioException
	 *                Erro no mecanismo hibernate
	 */

	public int registroMaximo(Class classe) throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @param atributo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public int valorMaximo(Class classe, String atributo)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @param atributo
	 *            Descrição do parâmetro
	 * @param parametro1
	 *            Descrição do parâmetro
	 * @param parametro2
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public int valorMaximo(Class classe, String atributo, String parametro1,
			String parametro2) throws ErroRepositorioException;

	/**
	 * Retorna o único registro do SistemaParametros.
	 * 
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public SistemaParametro pesquisarParametrosDoSistema()
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @param limite
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro,
			String pacoteNomeObjeto, int limite)
			throws ErroRepositorioException;

	public Object inserir(Object objeto) throws ErroRepositorioException;

	public void atualizar(Object objeto) throws ErroRepositorioException;

	public void remover(int id, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ErroRepositorioException;

	public void remover(Object objeto) throws ErroRepositorioException;

	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException;

	public Object inserirOuAtualizar(Object objeto)
			throws ErroRepositorioException;

	public Collection pesquisar(Collection ids, Filtro filtro,
			String pacoteNomeObjeto) throws ErroRepositorioException;

	/**
	 * Este método de pesquisa serve para localizar qualquer objeto no sistema.
	 * Ele aceita como parâmetro um offset que indica a página desejada no
	 * esquema de paginação. A paginação procura 10 registros de casa vez.
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param filtro
	 *            Filtro da pesquisa
	 * @param pageOffset
	 *            Indicador da página desejada do esquema de paginação
	 * @param pacoteNomeObjeto
	 *            Pacote do objeto
	 * @return Coleção dos resultados da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public Collection pesquisar(Filtro filtro, int pageOffset,
			String pacoteNomeObjeto) throws ErroRepositorioException;

	/**
	 * Informa o número total de registros de uma pesquisa, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException;

	/**
	 * Método que insere uma Lista em Batch
	 *
	 * inserirBatch
	 *
	 * @author Roberta Costa
	 * @date 17/05/2006
	 *
	 * @param list
	 * @throws ErroRepositorioException
	 */
	public void inserirBatch(List list) throws ErroRepositorioException;
	
	/**
	 * Recupera a coleção de feriados nacionais
	 *
	 * @author Pedro Alexandre 
	 * @date 13/09/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<NacionalFeriado>  pesquisarFeriadosNacionais() throws ErroRepositorioException ;

	/**
	 * Obtem o próximo valor do sequence do Banco do Imovel ou Cliente
	 * 
	 * @author Rafael Santos
	 * @since 17/11/2006
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Object obterNextVal(Object objeto) throws ErroRepositorioException; 
	
	/**
	 * [UC???] - ????????
	 * 
	 * @author Rômulo Aurélio Filho
	 * @date 25/01/2007
	 * @descricao O método retorna um objeto com a maior data de Implementacao
	 *            do Banco e sua ultima alteracao
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public DbVersaoBase pesquisarDbVersaoBase() throws ErroRepositorioException ;
	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException;
}
