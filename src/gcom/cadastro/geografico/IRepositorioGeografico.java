package gcom.cadastro.geografico;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;

import java.util.Collection;

/**
 * Interface para o repositório de cliente
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */
public interface IRepositorioGeografico {

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
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param codigoSetorComercial
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarMunicipoPeloSetorComercial(
			String codigoSetorComercial, String idMunicipio)
			throws ErroRepositorioException;

	/**
	 * Método que retorna o maior código do bairro de um município
	 * 
	 * @author Rafael Corrêa
	 * @date 10/07/2006
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ControladorException
	 */
	
	public int pesquisarMaximoCodigoBairro(Integer idMunicipio)
			throws ErroRepositorioException;

	/**
	 * Pesquisa um município pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Município
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoMunicipioRelatorio(
			Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * Pesquisa um bairro pelo código e pelo id do município
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Bairro
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoBairroRelatorio(Integer codigoBairro,
			Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * 
	 * @return colecao de BairroArea
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection pesquisarBairroArea(Integer idBairro)
			throws ErroRepositorioException;
	
	/**
	 * Remove todos os BairroArea de um determinado Bairro
	 * 
	 * @author Vivianne Sousa
	 * @date 27/12/200
	 * 
	 * @param idBairro
	 * @exception ErroRepositorioException
	 *              
	 */
	public void removerTodosBairroAreaPorBairro(Integer idBairro)
			throws ErroRepositorioException;
	
	 /**
	 * Método que retorna o maior id do Município
	 * 
	 * @author Rafael Corrêa
	 * @date 24/07/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoIdMunicipio()
			throws ErroRepositorioException;
    
    /**
	 * Método que retorna o municipio do Imovel
	 * 
	 * @author Hugo Amorim
	 * @date 27/08/2009
	 * 
	 * @return Municipio
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipioDoImovel(Integer idImovel) 
    	throws ErroRepositorioException;
	
    /**
	 * Método reponsável por retornar todos os municípios que possuem alguma
	 * associação com uma localidade (localidade.muni_idprincipal != null)
	 * 
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */  
    public Collection pesquisarMunicipiosAssociadoLocalidade() throws ErroRepositorioException;
}
