package gcom.cadastro.cliente;

import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Interface para o repositório de cliente tipo
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public interface IRepositorioClienteTipo {

    /**
     * pesquisa uma coleção de cliente(s) de acordo com critérios existentes no
     * filtro
     * 
     * @param filtroCliente
     *            Description of the Parameter
     * @return Description of the Return Value
     * @exception ErroRepositorioException
     *                Description of the Exception
     */
    public Collection pesquisarClienteTipo(FiltroClienteTipo FiltroClienteTipo)
            throws ErroRepositorioException;

}
