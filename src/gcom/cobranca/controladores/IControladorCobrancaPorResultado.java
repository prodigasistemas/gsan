package gcom.cobranca.controladores;

import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.GerarExtensaoComandoContasCobrancaEmpresaHelper;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IControladorCobrancaPorResultado {

	public List<Object[]> pesquisarQuantidadeContas(ComandoEmpresaCobrancaContaHelper helper, boolean percentualInformado) throws ControladorException;
	
	public List<Object[]> pesquisarContas(Integer idImovel, ComandoEmpresaCobrancaContaHelper helper, boolean percentualInformado) throws ControladorException;

	public void gerarMovimentoContas(ComandoEmpresaCobrancaConta comando, int idFuncionalidadeIniciada) throws ControladorException;

	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean percentualInformado, Integer anoMesFaturamento) throws ControladorException;

	public void gerarArquivoContas(Collection<Integer> comandos, Integer idEmpresa, int idFuncionalidadeIniciada) throws ControladorException;

	public void gerarNegociacoesCobrancaEmpresa(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;

	public void atualizarPagamentosContasCobranca(int idFuncionalidadeIniciada, Integer idEmpresa, Integer anoMesArrecadacao) throws ControladorException;

	public void gerarArquivoTextoPagamentosCobrancaEmpresa(Integer idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;

	public Integer pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(Integer idEmpresa, String dataPagamentoInicial, String dataPagamentoFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(RelatorioPagamentosContasCobrancaEmpresaHelper helper) throws ControladorException;

	public int retirarSituacaoCobranca(BufferedReader buffer, Usuario usuario) throws ControladorException;

	public void gerarComando(ComandoEmpresaCobrancaContaHelper helper) throws ControladorException;

	public Object[] pesquisarDadosConsultaComando(Integer idComando, Date dateInicial, Date dateFinal) throws ControladorException;

	public Collection<GerarExtensaoComandoContasCobrancaEmpresaHelper> pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresa(Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice)
			throws ControladorException;

	public List<ComandoEmpresaCobrancaConta> pesquisarDadosComando(Integer idEmpresa, Date comandoInicial, Date comandoFinal, int pagina) throws ControladorException;

	public Integer inserirComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterComandosParaIniciar(Integer[] comandos) throws ControladorException;
}
