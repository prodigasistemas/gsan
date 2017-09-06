package gcom.faturamento.controladores;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface IControladorRetificarConta {

	@SuppressWarnings("rawtypes")
	public Integer retificarConta(Integer mesAnoConta, Conta contaAtual, Imovel imovel, Collection colecaoDebitoCobrado, Collection colecaoCreditoRealizado,
			LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Collection colecaoCategoria, String consumoAgua,
			String consumoEsgoto, String percentualEsgoto, Date dataVencimentoConta, Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoRetificacao contaMotivoRetificacao, Map<String, String[]> requestMap, Usuario usuarioLogado, String consumoTarifa,
			boolean atualizarMediaConsumoHistorico, Integer leituraAnterior, Integer leituraAtual, boolean atualizarLeituraAnteriorEAtualConta,
			String retornoBotaoVoltar, Integer leituraAnteriorPoco, Integer leituraAtualPoco, Integer volumePoco, BigDecimal percentualColeta,
			Integer consumoMedidoProporcional) throws ControladorException;

	public void atualizarFaturaItemContaRetificada(Integer idContaRetificada, Conta contaInserida) throws ControladorException;

	public Collection<Object[]> obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContas(Collection<Object[]> colecaoContasManutencao)
			throws ControladorException;

	public Collection<Object[]> obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContasIds(Collection<Object[]> colecaoContasManutencao)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void retificarConjuntoConta(Collection colecaoImovel, Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao, Collection debitosTipoRetirar,
			Usuario usuarioLogado, Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, String indicadorContaPaga)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void retificarConjuntoContaCliente(Integer codigoCliente, Short relacaoTipo, Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado, Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void retificarConjuntoConta(Integer idGrupoFaturamento, Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado, Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException;

	public void retificarConjuntoConta(Collection<Conta> colecaoContas, String identificadores, LigacaoAguaSituacao ligacaoAguaSituacao, Integer consumoAgua,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Integer consumoEsgoto, Date dataVencimento, ContaMotivoRetificacao contaMotivoRetificacao,
			Short indicadorCategoriaEconomiaConta, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void retificarConjuntoContaConsumos(Integer idFuncionalidadeIniciada, Map parametros) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection retificarContasPagasSemDebitoCredito(Collection colecaoContasRetificar, Usuario usuarioLogado) throws ControladorException;
	
	public Conta pesquisarContaRetificacao(Integer idConta) throws ControladorException;
}
