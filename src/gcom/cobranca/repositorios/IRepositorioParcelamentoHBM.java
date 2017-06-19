package gcom.cobranca.repositorios;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.util.ErroRepositorioException;

import java.util.List;

public interface IRepositorioParcelamentoHBM {

	public Parcelamento pesquisarPorId(Integer id) throws ErroRepositorioException;
	
	public List<CancelarParcelamentoDTO> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException;
	
	public CancelarParcelamentoDTO pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ErroRepositorioException;
}
