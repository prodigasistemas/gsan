package gcom.cadastro.localidade;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rossiter
 * @version 1.0
 */

public interface IRepositorioSetorComercial {

    /**
     * Pesquisa uma coleção de setor comercial com uma query especifica
     * 
     * @param filtroSetorComercial
     *            parametros para a consulta
     * @return Description of the Return Value
     */

    public Collection pesquisarSetorComercial(int idLocalidade)
            throws ErroRepositorioException;
    
	/**
	 * Método que retorna o maior código do setor comercial de uma localidade
	 * 
	 * @author Rafael Corrêa
	 * @date 11/07/2006
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ControladorException
	 */
	
	public int pesquisarMaximoCodigoSetorComercial(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa um setor comercial pelo código e pelo id da localidade
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return SetorComercial
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoSetorComercialRelatorio(Integer codigoSetorComercial,
			Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * Pesquisar os ids do Setor comercial pela localidade
	 * 
	 * @author Ana Maria
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade)
			throws ErroRepositorioException;
    /**
     * Pesquisar os todos os ids de Setor comercial 
     * 
     * @author Vivianne Sousa
     * @date 07/02/2007
     * 
     * @return Collection<Integer>
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCodigosSetorComercial()
            throws ErroRepositorioException ;
    
	/**
	 * [UC0928]-Manter Situação Especial de Faturamento
	 * [FS0003]-Verificar a existência do setor
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaSetorComercial(Integer idSetorComercial)throws ErroRepositorioException;


}
