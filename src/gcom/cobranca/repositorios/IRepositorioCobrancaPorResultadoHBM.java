package gcom.cobranca.repositorios;

import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.List;

public interface IRepositorioCobrancaPorResultadoHBM {

	public Collection<Object[]> pesquisarQuantidadeContas(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, Integer referenciaAtual) throws ErroRepositorioException;
	
	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, boolean percentualInformado, Integer referenciaAtual) throws ErroRepositorioException;
	
	public boolean isContasPagas(Integer idImovel, Integer idComando) throws ErroRepositorioException;
	
	public EmpresaCobrancaConta pesquisarEmpresaCobrancaConta(Integer idConta) throws ErroRepositorioException;
}
