package gcom.cobranca.repositorios;

import gcom.util.ErroRepositorioException;

import java.util.List;

public interface IRepositorioParcelamentoHBM {

	public List<Object[]> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException;
}
