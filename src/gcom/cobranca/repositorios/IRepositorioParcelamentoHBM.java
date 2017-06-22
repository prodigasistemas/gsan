package gcom.cobranca.repositorios;

import gcom.cobranca.bean.CancelarParcelamentoHelper;
import gcom.util.ErroRepositorioException;

import java.util.List;

public interface IRepositorioParcelamentoHBM {

	public List<CancelarParcelamentoHelper> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException;
	
	public CancelarParcelamentoHelper pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ErroRepositorioException;
}
