package gcom.cadastro.geografico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorGeograficoLocal extends javax.ejb.EJBLocalObject {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairro
	 *            Descrição do parâmetro
	 */
    public void atualizarBairro(Bairro bairro,
    		Collection colecaoBairroArea,Collection colecaoBairroAreaRemover,
    		Usuario usuarioLogado) throws ControladorException;

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
			throws ControladorException;
	
	
	/**
	 * Verifica se o município possui CEP por logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 16/05/2006
	 * 
	 * @param municipio
	 * @return boolean
	 */
	public boolean verificarMunicipioComCepPorLogradouro(Municipio municipio) throws ControladorException ;
	
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
	
	public int pesquisarMaximoCodigoBairro(
			Integer idMunicipio)
			throws ControladorException;

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
	public Municipio pesquisarObjetoMunicipioRelatorio(Integer idMunicipio)
			throws ControladorException;
	
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
	public Bairro pesquisarObjetoBairroRelatorio(Integer codigoBairro, Integer idMunicipio)
			throws ControladorException;
	
	/**
	 * Permite inserir um Municipio
	 * 
	 * [UC0001] Inserir Municipio
	 * 
	 * @author Kassia Albuquerque	
	 * @date 18/12/2006
	 * 
	 */
	public Integer inserirMunicipio(Municipio municipio,Usuario usuarioLogado) throws ControladorException;
	
	/**
     * [UC0035] Inserir Bairro
     * 
     * Insere um objeto do tipo bairro no BD
     * 
     * @author Vivianne Sousa
     * @date 22/12/2006
     * @param bairro
     * @param colecaoBairroArea
     * @return idBairro
     * @throws ControladorException
     */
    public Integer inserirBairro(Bairro bairro,
    		Collection colecaoBairroArea,Usuario usuarioLogado) throws ControladorException;
    
	/**
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * 
	 * @return colecao de BairroArea
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection pesquisarBairroArea(Integer idBairro)
		throws ControladorException;
	
	/**
     * Remover Bairro
     * 
     * Remove os bairros e area bairro 
     * selecionados na lista da funcionalidade Manter Bairro 
     * 
     * @author Vivianne Sousa
     * @date 26/12/2006
     * @param bairro
     * @param colecaoBairroArea
     * @return idBairro
     * @throws ControladorException
     */
    public void removerBairro(String[] ids,Usuario usuarioLogado) throws ControladorException;
    
    
	 /**
	 * [UC0006] Manter Municipio
	 * 
	 * 			Filtrar Município
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @param Integer
	 * @return boolean
	 */
	public boolean verificarExistenciaMunicipio(Integer codigoMunicipio)throws ControladorException;
    
	
	/**
	 * [UC0005] Manter Municipio 
	 * 			
	 * 			Remover Municipio
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @pparam municpio
	 * @throws ControladorException
	 */
	public void removerMunicipio(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	
	/**
	 * [UC005] Manter Municipio [SB0001]Atualizar Municipio 
	 * 
	 * @author Kassia Albuquerque
	 * @date 10/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void atualizarMunicipio(Municipio municipio,Usuario usuarioLogado) throws ControladorException;
	
	 /**
	 * Método que retorna o maior id do Município
	 * 
	 * @author Rafael Corrêa
	 * @date 24/07/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    public int pesquisarMaximoIdMunicipio() throws ControladorException;
    
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
    	throws ControladorException;
	
    /**
	 * Método reponsável por retornar todos os municípios que possuem alguma
	 * associação com uma localidade (localidade.muni_idprincipal != null)
	 * 
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipiosAssociadoLocalidade() throws ControladorException;
    
}
