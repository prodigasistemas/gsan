package gcom.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.micromedicao.Rota;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCanceladosHelper;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface ControladorFaturamentoRemote extends javax.ejb.EJBObject {

	@SuppressWarnings("rawtypes")
	public void inserirFaturamentoCronograma(Collection faturamentoAtividadeCronogramas, FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal)
			throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void inserirFaturamentoGrupoCronogramaMensal(FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void atualizarFaturamentoGrupoCronogramaMensal(FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas) throws RemoteException;

	public void verificarExistenciaCronogramaGrupo(FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	public boolean verificarExistenciaCronogramaAtividadeGrupo(FaturamentoAtividade faturamentoAtividade, FaturamentoGrupo faturamentoGrupo)
			throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection selecionarAtividadeFaturamentoQuePodeSerComandada(FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection verificarExistenciaRotaGrupo(FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection verificarSituacaoAtividadeRota(Collection colecaoRotasGrupo, FaturamentoAtividade faturamentoAtividade,
			FaturamentoGrupo faturamentoGrupo, boolean habilitada) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void inserirComandoAtividadeFaturamento(FaturamentoGrupo faturamentoGrupo, FaturamentoAtividade faturamentoAtividade, Collection colecaoRotas,
			Date dataVencimentoGrupo) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection buscarAtividadeComandadaNaoRealizada(FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma) throws RemoteException;

	public void removerComandoAtividadeFaturamento(String[] ids) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void atualizarComandoAtividadeFaturamento(FaturamentoAtividadeCronograma faturamentoAtividadeCronograma,
			Collection colecaoFaturamentoAtividadeCronogramaRota) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgoto(Integer anoMesReferencia, Integer ligacaoSituacaoAguaId,
			Integer ligacaoSituacaoEsgotoId, Short indicadorFaturamentoAgua, Short indicadorFaturamentoEsgoto, Collection categoriasImovel,
			Integer consumoFaturadoAguaMes, Integer consumoFaturadoEsgotoMes, int consumoMinimoLigacao, Date dataLeituraAnterior, Date dataLeituraAtual,
			BigDecimal percentualEsgoto, Integer tarifaImovel) throws RemoteException;

	public Date buscarDataLeituraCronograma(Imovel imovel, boolean situacao, Integer anoMesReferencia) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta(String mesAnoConta, String imovelID, Integer situacaoAguaConta,
			Integer situacaoEsgotoConta, Collection colecaoCategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto) throws RemoteException;

	public BigDecimal calcularValorTotalDebitoConta(Collection<DebitoCobrado> colecaoDebitoCobrado, Map<String, String[]> requestMap) throws RemoteException;

	public BigDecimal calcularValorTotalCreditoConta(Collection<CreditoRealizado> colecaoCreditoRealizado, Map<String, String[]> requestMap)
			throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void inserirConta(Integer mesAnoConta, Imovel imovel, Collection colecaoDebitoCobrado, LigacaoAguaSituacao ligacaoAguaSituacao,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Collection colecaoCategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto,
			Date dataVencimentoConta, Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta, ContaMotivoInclusao contaMotivoInclusao,
			Map<String, String[]> requestMap) throws RemoteException;

	public void cancelarConta(Collection<Conta> colecaoContas, String identificadores, ContaMotivoCancelamento contaMotivoCancelamento) throws RemoteException;

	public void colocarRevisaoConta(Collection<Conta> colecaoContas, String identificadores, ContaMotivoRevisao contaMotivoRevisao) throws RemoteException;

	public void retirarRevisaoConta(Collection<Conta> colecaoContas, String identificadores) throws RemoteException;

	public void alterarVencimentoConta(Collection<Conta> colecaoContas, String identificadores, Date dataVencimento) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void retificarConta(Integer mesAnoConta, Conta contaAtual, Imovel imovel, Collection colecaoDebitoCobrado, Collection colecaoCreditoRealizado,
			LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Collection colecaoCategoria, String consumoAgua,
			String consumoEsgoto, String percentualEsgoto, Date dataVencimentoConta, Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoRetificacao contaMotivoRetificacao, Map<String, String[]> requestMap) throws RemoteException;

	public Collection<DebitoCobrado> obterDebitosCobradosConta(Conta conta) throws RemoteException;

	public Collection<CreditoRealizado> obterCreditosRealizadosConta(Conta conta) throws RemoteException;

	public void inserirConsumoTarifa(ConsumoTarifa consumoTarifa, ConsumoTarifaVigencia consumoTarifaVigencia,
			Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria, Collection<ConsumoTarifaFaixa> colecaoConsumoTarifaFaixa) throws RemoteException;

	public BigDecimal calcularValorTotalAguaOuEsgotoPorCategoria(Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoHelper, String tipoRetorno)
			throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovelManter(Imovel imovel, Integer situacaoNormal, Integer situacaoIncluida, Integer situacaoRetificada)
			throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoTotalizandoPorCategoria(Collection colecaoCalcularValoresAguaEsgotoHelper)
			throws RemoteException;

	public void removerFaturamentoCronograma(String[] ids, String pacoteNomeObjeto) throws RemoteException;

	public void gerarDebitoACobrarDoacao(Collection<Rota> rotas) throws ControladorException;

	public void atualizarDataHoraRealizacaoAtividade(Integer idAtividade, Integer anoMesReferencia, Integer idFaturamentoGrupo) throws ControladorException;

	public void atualizarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comandoOriginal, FaturamentoSituacaoComando comandoInserir,
			Collection<FaturamentoSituacaoHistorico> colecaoHistoricoInserir, FaturamentoSituacaoComando comandoAtualizar,
			Collection<FaturamentoSituacaoHistorico> colecaoHistoricoAtualizar) throws ControladorException;

	public Integer pesquisarSituacaoEspecialFaturamentoCount(FaturamentoSituacaoComando comando) throws ControladorException;

	public Collection<FaturamentoSituacaoComando> pesquisarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comando, Integer numeroPaginasPesquisa)
			throws ControladorException;

	public Collection<RelatorioJurosMultasDebitosCanceladosHelper> pesquisarRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro) throws ControladorException;

}
