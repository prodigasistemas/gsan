package gcom.cadastro.localidade;

import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * 
 * Title: GCOM
 * 
 * Description: Interface do Repositório de Gerência Regional
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 13 de Janeiro de 2006
 * @version 1.0
 */
public interface IRepositorioGerenciaRegional {

	/**
	 * Pesquisa uma coleção de gerências regionais
	 * 
	 * @return Coleção de Gerências Regionais
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<GerenciaRegional> pesquisarGerenciaRegional()
			throws ErroRepositorioException;

	/**
	 * Pesquisa uma gerência regional pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Gerência Regional
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoGerenciaRegionalRelatorio(
			Integer idGerenciaRegional) throws ErroRepositorioException;

    /**
     * Pesquisa o id da gerência regional para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecadação do Mês
     * 
     * @author Pedro Alexandre
     * @date 05/01/2007
     * 
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Integer pesquisarIdGerenciaParaLocalidade(Integer idLocalidade) throws ErroRepositorioException ;

}
