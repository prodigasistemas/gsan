package gcom.cobranca.controladores;

import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.List;

public interface IControladorCobrancaPorResultado {

	public List<Object[]> pesquisarQuantidadeContas(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, boolean percentualInformado) throws ControladorException;

	public void gerarMovimentoContas(ComandoEmpresaCobrancaConta comando, int idFuncionalidadeIniciada) throws ControladorException;

	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, boolean percentualInformado, Integer anoMesFaturamento) throws ControladorException;

	public void gerarArquivoContas(Collection<Integer> comandos, Integer idEmpresa, int idFuncionalidadeIniciada) throws ControladorException;

	public void gerarNegociacoesCobrancaEmpresa(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;

	public void atualizarPagamentosContasCobranca(int idFuncionalidadeIniciada, Integer idEmpresa, Integer anoMesArrecadacao) throws ControladorException;

	public void gerarArquivoTextoPagamentosCobrancaEmpresa(Integer idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;

	public Integer pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(Integer idEmpresa, String dataPagamentoInicial, String dataPagamentoFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(RelatorioPagamentosContasCobrancaEmpresaHelper helper) throws ControladorException;
}
