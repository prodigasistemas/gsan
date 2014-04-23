package gcom.cadastro.imovel;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 * @created 7 de Junho de 2004
 */
public interface ControladorImovelRemote extends javax.ejb.EJBObject {

	/**
	 * Insere um imovel na base
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void inserirImovel(Imovel imovel) throws RemoteException;

	/**
	 * Retorna a quantidade de economias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return Quantidade de economias
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public int obterQuantidadeEconomias(Imovel imovel) throws RemoteException;

	/**
	 * Retorna a coleção de economias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return Quantidade de economias
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public Collection obterColecaoImovelSubcategorias(Imovel imovel)
			throws RemoteException;

	/**
	 * Retorna a quantidade de categorias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return uma coleção de categorias
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public Collection obterQuantidadeEconomiasCategoria(Imovel imovel)
			throws RemoteException;

	/**
	 * 
	 * @return Quantidade de categorias cadastradas no BD
	 * @throws RemoteException
	 */
	public Object pesquisarObterQuantidadeCategoria() throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param subCategorias
	 *            Description of the Parameter
	 * @param enderecoImoveis
	 *            Description of the Parameter
	 * @param clientes
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public Integer inserirImovelRetorno(Imovel imovel,
			Collection subCategorias, Collection enderecoImoveis,
			Collection clientes, Collection colecaoClientesImoveisRemovidos,
			Collection colecaoImovelSubcategoriasRemovidas)
			throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovelSubcategoria
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void inserirImovelSubCategoria(ImovelSubcategoria imovelSubcategoria)
			throws RemoteException;

	/**
	 * inseri o imóvel economia e o cliente imovel economia do imóvel
	 * subcategoria
	 * 
	 * @param imoveisEconomias
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void informarImovelEconomias(Collection imoveisEconomias)
			throws RemoteException;

	/**
	 * remove o imóvel economia e o cliente imovel economia do imóvel
	 * subcategoria
	 * 
	 * @param imovelEconomia
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void removerImovelEconomia(ImovelEconomia imovelEconomia)
			throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizarImovel(Imovel imovel) throws RemoteException,
			ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param subcategorias
	 *            Descrição do parâmetro
	 * @param enderecoImovel
	 *            Descrição do parâmetro
	 * @param clientes
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarImovel(Imovel imovel, Collection subcategorias,
			Collection enderecoImovel, Collection clientes)
			throws RemoteException;

	/**
	 * verifica se existe algum iptu no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param numeroIptu
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Description of the Exception
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void verificarExistenciaIPTU(Collection imoveisEconomia,
			Imovel imovel, String numeroIptu, Date dataUltimaAlteracao)
			throws RemoteException, ErroRepositorioException;

	/**
	 * verifica se existe algum numero da celpe no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param numeroCelpe
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Description of the Exception
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void verificarExistenciaCelpe(Collection imoveisEconomia,
			Imovel imovel, String numeroCelpe, Date dataUltimaAlteracao)
			throws RemoteException, ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param ids
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void removerImovel(String[] ids) throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovelSubcategoria
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarImovelSubCategoria(
			ImovelSubcategoria imovelSubcategoria) throws RemoteException;

	// ----- Metodo Para Carregar o Objeto ImovelMicromedicao
	// ----- Flávio Leonardo
	public Collection carregarImovelMicromedicao(Collection imoveisMicromedicao)
			throws RemoteException;
	
	/**
	 * Permite pesquisar entidades beneficentes
	 * [UC0389] Inserir Autorização para Doação Mensal
	 * @author  César Araújo
	 * @date    30/08/2006
	 * @param   idEntidadeBeneficente - Código da entidade beneficente
	 * @return  Collection<EntidadeBeneficente> - Coleção de entidades beneficentes
	 * @throws  ControladorException
	 */
	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(Integer idEntidadeBeneficente) throws RemoteException;
	
	/**
	 * Permite pesquisar imóveis doação 
	 * [UC0389] Inserir Autorização para Doação Mensal
	 * @author  César Araújo
	 * @date    30/08/2006
	 * @param   idImovelDoacao - Código do imóvel doação
	 * @return  Collection<ImovelDoacao> - Coleção de imóveis doação
	 * @throws  ControladorException
	 */
	public Collection<ImovelDoacao> pesquisarImovelDoacao(FiltroImovelDoacao filtroImovelDoacao) throws RemoteException;

	/**
	 * Permite verificar se existe um determinado imóvel doação 
	 * [UC0389] Inserir Autorização para Doação Mensal
	 * @author  César Araújo
	 * @date    30/08/2006
	 * @param   idImovel - Código do imóvel
	 * @param   idEntidadeBeneficente - Código da entidade beneficente
	 * @return  ImovelDoacao - Retorna um imóvel doação caso a combinação de imóvel e entidade beneficente exista.  
	 * @throws  ControladorException
	 */
	public ImovelDoacao verificarExistenciaImovelDoacao(Integer idImovel, Integer idEntidadeBeneficente) throws RemoteException;
		
	/**
	 * Permite atualizar as informações do imóvel doação 
	 * [UC0390] Manter Autorização para Doação Mensal
	 * @author  César Araújo
	 * @date    30/08/2006
	 * @param   imovelDoacao - Código do imóveo doação
	 * @throws  ControladorException
	 */
	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao) throws RemoteException;


}
