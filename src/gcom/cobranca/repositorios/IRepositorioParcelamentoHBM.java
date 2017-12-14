package gcom.cobranca.repositorios;

import gcom.cobranca.bean.CancelarParcelamentoHelper;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.util.ErroRepositorioException;

import java.util.List;

public interface IRepositorioParcelamentoHBM {

	public List<CancelarParcelamentoHelper> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException;

	public CancelarParcelamentoHelper pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ErroRepositorioException;

	public DebitoACobrar pesquisarDebitoACobrar(Integer idParcelamento, Integer idDebitoTipo) throws ErroRepositorioException;

	public List<Object[]> pesquisarDebitoACobrarCurtoELongoPrazo(Integer idParcelamento) throws ErroRepositorioException;
}
