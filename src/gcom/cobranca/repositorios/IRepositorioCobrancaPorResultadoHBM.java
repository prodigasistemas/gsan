package gcom.cobranca.repositorios;

import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRepositorioCobrancaPorResultadoHBM {

	public List<Object[]> pesquisarContas(Integer idImovel, ComandoEmpresaCobrancaContaHelper helper, boolean percentualInformado, Integer referenciaAtual) throws ErroRepositorioException;

	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean percentualInformado, Integer referenciaAtual) throws ErroRepositorioException;

	public boolean isContasPagas(Integer idImovel, Integer idComando) throws ErroRepositorioException;

	public EmpresaCobrancaConta pesquisarEmpresaCobrancaConta(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(Integer idEmpresa, String dataPagamentoInicial, String dataPagamentoFinal) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaOpcaoTotalizacao(RelatorioPagamentosContasCobrancaEmpresaHelper helper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(RelatorioPagamentosContasCobrancaEmpresaHelper helper) throws ErroRepositorioException;
	
	public Integer pesquisarMenorFaixa(Integer idEmpresa) throws ErroRepositorioException;
	
	public boolean isImovelEmCobranca(Integer idImovel) throws ErroRepositorioException;
	
	public boolean isClienteComCpfOuCnpj(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosConsultaComando(Integer idComando) throws ErroRepositorioException;

	public List<ComandoEmpresaCobrancaConta> pesquisarDadosComando(Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice, int quantidadeRegistros) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterComandosParaIniciar(Integer[] comandos) throws ErroRepositorioException;
}
