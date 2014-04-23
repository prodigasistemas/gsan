package gcom.cadastro.funcionario;

import gcom.util.ErroRepositorioException;

/**
 * Interface para o repositório de funcionário
 * 
 * @author Sávio Luiz
 * @created 13 de Janeiro de 2006
 */

public interface IRepositorioFuncionario {

	
	public Integer verificarExistenciaFuncionario(Integer idFuncionario) throws ErroRepositorioException;

}
