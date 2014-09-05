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
import gcom.gui.faturamento.ImovelFaturamentoSeletivo;
import gcom.micromedicao.Rota;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCanceladosHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * < <Descri��o da Interface>>
 * 
 * @author Administrador
 */
public interface ControladorFaturamentoRemote extends javax.ejb.EJBObject {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void inserirFaturamentoCronograma(
			Collection faturamentoAtividadeCronogramas,
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal)
			throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descri��o do par�metro
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void inserirFaturamentoGrupoCronogramaMensal(
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas) throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descri��o do par�metro
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void atualizarFaturamentoGrupoCronogramaMensal(
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas) throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a exist�ncia do cronograma para o grupo
	 */
	public void verificarExistenciaCronogramaGrupo(
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a exist�ncia do cronograma para o grupo
	 */
	public boolean verificarExistenciaCronogramaAtividadeGrupo(
			FaturamentoAtividade faturamentoAtividade,
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a exist�ncia do cronograma para o grupo
	 */
	public Collection selecionarAtividadeFaturamentoQuePodeSerComandada(
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @return uma cole��o de rotas pertencentes ao grupo selecionado
	 */
	public Collection verificarExistenciaRotaGrupo(
			FaturamentoGrupo faturamentoGrupo) throws RemoteException;

	/**
	 * 
	 * @param colecaoRotasGrupo
	 * @param faturamentoAtividade
	 * @param faturamentoGrupo
	 * @return
	 * @throws RemoteException
	 */
	public Collection verificarSituacaoAtividadeRota(
			Collection colecaoRotasGrupo,
			FaturamentoAtividade faturamentoAtividade,
			FaturamentoGrupo faturamentoGrupo, boolean habilitada)
			throws RemoteException;

	public void inserirComandoAtividadeFaturamento(
			FaturamentoGrupo faturamentoGrupo,
			FaturamentoAtividade faturamentoAtividade, Collection colecaoRotas,
			Date dataVencimentoGrupo) throws RemoteException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Retorna uma lista de atividades de faturamento comandadas e ainda n�o
	 * realizadas
	 * 
	 */
	public Collection buscarAtividadeComandadaNaoRealizada(
			FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma)
			throws RemoteException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * [SB0002] - Excluir Comando de Atividade de Faturamento
	 * 
	 */
	public void removerComandoAtividadeFaturamento(String[] ids)
			throws RemoteException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Atualizar Comando de Atividade de Faturamento
	 * 
	 */
	public void atualizarComandoAtividadeFaturamento(
			FaturamentoAtividadeCronograma faturamentoAtividadeCronograma,
			Collection colecaoFaturamentoAtividadeCronogramaRota)
			throws RemoteException;
	
	/**
	 * 
	 * [UC0120 - Calcular Valores de �gua e/ou Esgoto]
	 * 
	 * @param anoMesReferencia
	 * @param ligacaoSituacaoAguaId
	 * @param ligacaoSituacaoEsgotoId
	 * @param indicadorFaturamentoAgua
	 * @param indicadorFaturamentoEsgoto
	 * @param categoriasImovel
	 * @param consumoFaturadoAguaMes
	 * @param consumoFaturadoEsgotoMes
	 * @param consumoMinimoLigacao
	 * @param dataLeituraAnterior
	 * @param dataLeituraAtual
	 * @param percentualEsgoto
	 * @param tarifaImovel
	 * @return
	 * @throws RemoteException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgoto(Integer anoMesReferencia,
			Integer ligacaoSituacaoAguaId, Integer ligacaoSituacaoEsgotoId,
			Short indicadorFaturamentoAgua, Short indicadorFaturamentoEsgoto,
			Collection categoriasImovel, Integer consumoFaturadoAguaMes,
			Integer consumoFaturadoEsgotoMes, int consumoMinimoLigacao,
			Date dataLeituraAnterior, Date dataLeituraAtual,
			BigDecimal percentualEsgoto, Integer tarifaImovel) throws RemoteException;
	
	/**
	 * 
	 * @param imovel
	 * @param situacao
	 * @return
	 * @throws RemoteException
	 */
	public Date buscarDataLeituraCronograma(Imovel imovel, boolean situacao, Integer anoMesReferencia) throws RemoteException;
	
	
	/**
	 * Calcula os valores da conta de acordo com os par�metros passados
	 * 
	 * [UC0145] - Inserir Conta
	 * [SF0001] - Determinar Valores para Faturamento de �gua e/ou Esgoto
	 * Author: Raphael Rossiter
	 * 05/12/2005
	 * @param mesAnoConta
	 * @param imovelID
	 * @param situacaoAguaConta
	 * @param situacaoEsgotoConta
	 * @param colecaoCategoria
	 * @param consumoAgua
	 * @param consumoEsgoto
	 * @param percentualEsgoto
	 * @return Collection<CalcularValoresAguaEsgotoHelper>
	 * @throws ControladorException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta(String mesAnoConta, String imovelID,
			Integer situacaoAguaConta, Integer situacaoEsgotoConta, Collection colecaoCategoria, 
			String consumoAgua, String consumoEsgoto, String percentualEsgoto) throws RemoteException;
	
	
	/**
	 * C�lcula o valor total dos d�bitos de uma conta de acordo com o informado pelo usu�rio
	 * 
	 * [UC0145] - Inserir Conta
	 * Author: Raphael Rossiter
	 * Data: 10/01/2006
	 * @param colecaoDebitoCobrado
	 * @param requestMap
	 * @return BigDecimal valorTotalDebitoConta
	 * @throws ControladorException
	 */
	public BigDecimal calcularValorTotalDebitoConta(Collection<DebitoCobrado> colecaoDebitoCobrado, 
			Map<String, String[]> requestMap) throws RemoteException ;
	
	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 10/01/2006
	 * C�lcula o valor total dos cr�ditos de uma conta de acordo com o informado
	 * pelo usu�rio
	 * 
	 * @param colecaoCreditoRealizado
	 * @param requestMap
	 * @return BigDecimal valorTotalCreditoConta
	 * @throws ControladorException
	 */
	public BigDecimal calcularValorTotalCreditoConta(
			Collection<CreditoRealizado> colecaoCreditoRealizado,
			Map<String, String[]> requestMap) throws RemoteException;
	
	
	/**
	 * [UC0145] - Inserir Conta
	 * Author: Raphael Rossiter
	 * 05/12/2005 
	 * @param mesAnoConta
	 * @param imovel
	 * @param colecaoDebitoCobrado
	 * @param ligacaoAguaSituacao
	 * @param ligacaoEsgotoSituacao
	 * @param colecaoCategoria
	 * @param consumoAgua
	 * @param consumoEsgoto
	 * @param percentualEsgoto
	 * @param dataVencimentoConta
	 * @param calcularValoresConta
	 * @param motivoInclusaoConta
	 * @throws ControladorException
	 */
	public void inserirConta(Integer mesAnoConta, Imovel imovel, Collection colecaoDebitoCobrado, 
			LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto,
			Date dataVencimentoConta, Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta, 
			ContaMotivoInclusao contaMotivoInclusao, Map<String, String[]> requestMap) throws RemoteException;

	
	/**
	 * [UC0147] - Cancelar Conta
	 * Author: Raphael Rossiter
	 * Data: 10/12/2005
	 * @param colecaoContas - cole��o com todas as contas do im�vel
	 * @param identificadores - identifica atrav�s do ID, quais as contas que ser�o canceladas
	 * @param motivoCancelamentoConta - motivo do cancelamento escolhido pelo usu�rio
	 */
	public void cancelarConta (Collection<Conta> colecaoContas, String identificadores,
			ContaMotivoCancelamento contaMotivoCancelamento) throws RemoteException;
	
	
	/**
	 * [UC0148] - Colocar Conta em Revis�o
	 * Author: Raphael Rossiter
	 * Data: 21/12/2005
	 * @param colecaoContas - cole��o com todas as contas do im�vel
	 * @param identificadores - identifica atrav�s do ID, quais as contas que ser�o colocadas em revis�o
	 * @param motivoRevis�oConta - motivo da revis�o escolhida pelo usu�rio
	 */
	public void colocarRevisaoConta (Collection<Conta> colecaoContas, String identificadores,
			ContaMotivoRevisao contaMotivoRevisao) throws RemoteException;
	
	
	/**
	 * [UC0149] - Retirar Conta em Revis�o
	 * Author: Raphael Rossiter
	 * Data: 22/12/2005
	 * @param colecaoContas - cole��o com todas as contas do im�vel
	 * @param identificadores - identifica atrav�s do ID, quais as contas que ser�o retiradas de revis�o
	 */
	public void retirarRevisaoConta (Collection<Conta> colecaoContas, String identificadores) 
		throws RemoteException;
	
	
	/**
	 * [UC0151] - Alterar Vencimento de Conta
	 * Author: Raphael Rossiter
	 * Data: 22/12/2005
	 * @param colecaoContas - cole��o com todas as contas do im�vel
	 * @param identificadores - identifica atrav�s do ID, quais as contas que sofrer�o altera��o na sua data de vencimento
	 * @param dataVencimento - a nova data de vencimento da conta
	 */
	public void alterarVencimentoConta (Collection<Conta> colecaoContas, String identificadores, Date dataVencimento) 
		throws RemoteException;
	
	
	/**
	 * 
	 * @param mesAnoConta
	 * @param contaAtual
	 * @param imovel
	 * @param colecaoDebitoCobrado
	 * @param colecaoCreditoRealizado
	 * @param ligacaoAguaSituacao
	 * @param ligacaoEsgotoSituacao
	 * @param colecaoCategoria
	 * @param consumoAgua
	 * @param consumoEsgoto
	 * @param percentualEsgoto
	 * @param dataVencimentoConta
	 * @param calcularValoresConta
	 * @param contaMotivoRetificacao
	 * @param requestMap
	 * @throws ControladorException
	 */
	public void retificarConta(Integer mesAnoConta, Conta contaAtual,
			Imovel imovel,
			Collection colecaoDebitoCobrado,
			Collection colecaoCreditoRealizado,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua,
			String consumoEsgoto, String percentualEsgoto,
			Date dataVencimentoConta,
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoRetificacao contaMotivoRetificacao, Map<String, String[]> requestMap)
			throws RemoteException;
	
	
	/**
	 * [UC0150] - Retificar Conta
	 * Author: Raphael Rossiter
	 * Data: 26/12/2005
	 * @param conta
	 * @return uma cole��o com os d�bitos cobrados de uma conta
	 * @throws ControladorException
	 */
	public Collection<DebitoCobrado> obterDebitosCobradosConta(Conta conta) throws RemoteException;
	
	
	/**
	 * [UC0150] - Retificar Conta
	 * Author: Raphael Rossiter
	 * Data: 28/12/2005
	 * @param conta
	 * @return uma cole��o com os cr�ditos realizados de uma conta
	 * @throws ControladorException
	 */
	public Collection<CreditoRealizado> obterCreditosRealizadosConta(Conta conta) throws RemoteException;
	
	/**
	 * [UC0168] - Inserir Tarifa de Consumo
	 * 
	 * @param consumoTarifa
	 * @param consumoTarifaVigencia
	 * @param colecaoConsumoTarifaCategoria
	 * @param colecaoConsumoTarifaFaixa
	 * @throws ControladorException
	 */
	public void inserirConsumoTarifa(ConsumoTarifa consumoTarifa,
			ConsumoTarifaVigencia consumoTarifaVigencia,
			Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria,
			Collection<ConsumoTarifaFaixa> colecaoConsumoTarifaFaixa)
			throws RemoteException;
	
	
	/**
	 * [UC0145] - Inserir Conta
	 * Author: Raphael Rossiter
	 * Data: 12/01/2006
	 * C�lcula o valor total de �gua ou esgoto
	 * @param calcularValoresAguaEsgotoHelper
	 * @param tipoRetorno
	 * @return valorTotalAguaOuEsgoto
	 */
	public BigDecimal calcularValorTotalAguaOuEsgotoPorCategoria(Collection<CalcularValoresAguaEsgotoHelper>
	calcularValoresAguaEsgotoHelper, String tipoRetorno) throws RemoteException;
	
	
	/**
	 * [UC0146] - Manter Conta
	 * Author: Raphael Rossiter
	 * Data: 21/01/2006
	 * 
	 * Obt�m as contas de um im�vel que poder�o ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterContasImovelManter(Imovel imovel, Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada) throws RemoteException;
	
	
	
	/**
	 * 
	 * @param colecaoCalcularValoresAguaEsgotoHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoTotalizandoPorCategoria(
			Collection colecaoCalcularValoresAguaEsgotoHelper) throws RemoteException;
	
	
	public void removerFaturamentoCronograma(String[] ids, String pacoteNomeObjeto) throws RemoteException;
	
	/**
	 * Permite gerar os d�bitos de doa��es para os im�veis contidos na cole��o
	 * [UC0394] Gerar D�bitos a Cobrar de Doa��es
	 * @author  C�sar Ara�jo
	 * @date    05/08/2006
	 * @param   Collection<ImovelCobrarDoacaoHelper> imovelCobrarDoacaoHelpers - Cole��o de imovelCobrarDoacaoHelpers 
	 * @return  Collection<ImovelCobrarDoacaoHelper> - Cole��o de ImovelCobrarDoacaoHelper 
	 *          j� com as informa��es necess�rias para registro da cobran�a
	 * @throws  ErroRepositorioException
	**/ 
	public void gerarDebitoACobrarDoacao(Collection<Rota> rotas) throws ControladorException;

	
	
	public void atualizarDataHoraRealizacaoAtividade(Integer idAtividade,
			Integer anoMesReferencia, Integer idFaturamentoGrupo)
			throws ControladorException;
	
	/**
	 *[UC0928] - Manter Situa��o Especial de Faturamento
	 *[SB] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 17/08/2009
	 *@author Marlon Patrick
	 */
	public void atualizarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comandoOriginal,FaturamentoSituacaoComando comandoInserir, Collection<FaturamentoSituacaoHistorico> colecaoHistoricoInserir,FaturamentoSituacaoComando comandoAtualizar, Collection<FaturamentoSituacaoHistorico> colecaoHistoricoAtualizar) throws ControladorException;

	/**
	 *[UC0928] - Manter Situa��o Especial de Faturamento
	 *[SB] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 19/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarSituacaoEspecialFaturamentoCount(FaturamentoSituacaoComando comando) throws ControladorException;

	/**
	 *[UC0928] - Manter Situa��o Especial de Faturamento
	 *
	 *@since 19/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<FaturamentoSituacaoComando> pesquisarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comando,Integer numeroPaginasPesquisa)throws ControladorException;

	/**
	 *[UC0958] - Gerar Relat�rio de juros, Multas e D�bitos Cancelados
	 *
	 *@since 13/10/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioJurosMultasDebitosCanceladosHelper> pesquisarRelatorioJurosMultasDebitosCancelados(FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro) throws ControladorException;

}
