package gcom.gerencial.cadastro.imovel;

import gcom.util.ErroRepositorioException;

import java.util.Collection;



public interface IRepositorioGerencialImovel {

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 27/04/2007
	 *
	 * @param imovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarObterQuantidadeEconomiasCategoria(Integer imovel) throws ErroRepositorioException ;

}
