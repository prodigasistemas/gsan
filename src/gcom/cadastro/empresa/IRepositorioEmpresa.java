package gcom.cadastro.empresa;

import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * < <Descrição da Interface>>
 * 
 * @author Sávio Luiz
 */
public interface IRepositorioEmpresa {

	/**
	 * Pesquisa as empresas que serão processadas no emitir contas
	 * 
	 * @author Sávio Luiz
	 * @date 09/01/2007
	 * 
	 */
	public Collection pesquisarIdsEmpresa()
			throws ErroRepositorioException;


}
