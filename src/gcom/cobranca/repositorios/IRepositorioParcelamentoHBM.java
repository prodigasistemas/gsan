package gcom.cobranca.repositorios;

import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.util.ErroRepositorioException;

import java.util.List;

public interface IRepositorioParcelamentoHBM {

	public List<CancelarParcelamentoDTO> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException;
	
	public CancelarParcelamentoDTO pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ErroRepositorioException;
}
