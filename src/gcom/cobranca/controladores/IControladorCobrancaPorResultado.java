package gcom.cobranca.controladores;

import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.util.ControladorException;

import java.util.Collection;

public interface IControladorCobrancaPorResultado {

	public void gerarNegociacoesCobrancaEmpresa(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;
	
	public void atualizarPagamentosContasCobranca(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;
	
	public void gerarArquivoTextoPagamentosCobrancaEmpresa(Integer idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;

	public void gerarMovimentoContas(ComandoEmpresaCobrancaConta comando, int idFuncionalidadeIniciada) throws ControladorException;

	public void gerarArquivoContas(Collection<Integer> comandos, Integer idEmpresa, int idFuncionalidadeIniciada) throws ControladorException;
}
