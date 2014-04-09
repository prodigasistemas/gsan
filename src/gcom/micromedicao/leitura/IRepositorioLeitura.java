package gcom.micromedicao.leitura;

import gcom.util.ErroRepositorioException;

/**
 * Interface para o repositório de funcionário
 * 
 * @author Sávio Luiz
 * @created 13 de Janeiro de 2006
 */

public interface IRepositorioLeitura {

	
	public Integer verificarExistenciaLeituraAnormalidade(Integer idLeituraAnormalidade) throws ErroRepositorioException;

}
