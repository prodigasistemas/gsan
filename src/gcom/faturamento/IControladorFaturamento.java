package gcom.faturamento;

import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.ContasEmRevisaoRelatorioHelper;
import gcom.faturamento.bean.DeclaracaoQuitacaoAnualDebitosHelper;
import gcom.faturamento.bean.DeterminarValoresFaturamentoAguaEsgotoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.bean.EmitirHistogramaAguaHelper;
import gcom.faturamento.bean.ExecutarAtividadeFaturamentoHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaHelper;
import gcom.faturamento.bean.GerarRelatorioAnormalidadeConsumoHelper;
import gcom.faturamento.bean.GerarRelatorioAnormalidadePorAmostragemHelper;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.bean.VolumesFaturadosRelatorioHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.GerarImpostosDeduzidosContaHelper;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEmitirOSHelper;
import gcom.gui.faturamento.FaturamentoImediatoAjusteHelper;
import gcom.gui.faturamento.ImovelFaturamentoSeletivo;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.gui.portal.ConsultarEstruturaTarifariaPortalHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioContasRetidasHelper;
import gcom.relatorio.faturamento.RelatorioFaturasAgrupadasBean;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioProtocoloEntregaFaturaBean;
import gcom.relatorio.faturamento.autoinfracao.RelatorioAutoInfracaoBean;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface Controlador Faturamento PADR�O
 * 
 * @author Raphael Rossiter 
 * @date 20/12/2006
 */
public interface IControladorFaturamento {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void inserirFaturamentoCronograma(
			Collection faturamentoAtividadeCronogramas,
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			RegistradorOperacao registradorOperacao, Usuario usuarioLogado, Integer anoMesInformado)
			throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descri��o do par�metro
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void inserirFaturamentoGrupoCronogramaMensal(
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas, Usuario usuarioLogado, Integer anoMesInformado)
			throws ControladorException;

	
	public void faturarGrupoFaturamento(Collection colecaoRotas,
			FaturamentoGrupo faturamentoGrupo, int atividade, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Este caso de uso � respons�vel pelo pr�-faturamento das contas que ser�o emitidas no cliente
	 * (Gera��o Simult�nea)
	 *
	 * [UC0000] - Pr�-Faturar Grupo de Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 09/04/2008
	 *
	 * @param colecaoRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void preFaturarGrupoFaturamento(Rota rota,
			Integer anoMesFaturamento, Integer idFaturamentoGrupo, int idFuncionalidadeIniciada) 
		throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descri��o do par�metro
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 */
	public void atualizarFaturamentoGrupoCronogramaMensal(
			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal,
			Collection faturamentoAtividadeCronogramas,
			Collection colecaoTodasAtividades, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a exist�ncia do cronograma para o grupo
	 */
	public void verificarExistenciaCronogramaGrupo(
			FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @param faturamentoAtividade
	 */
	public boolean verificarExistenciaCronogramaAtividadeGrupo(
			FaturamentoAtividade faturamentoAtividade,
			FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @return um boleano que identifica a exist�ncia do cronograma para o grupo
	 */
	public Collection selecionarAtividadeFaturamentoQuePodeSerComandada(
			FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param faturamentoGrupo
	 * @return uma cole��o de rotas pertencentes ao grupo selecionado
	 */
	public Collection verificarExistenciaRotaGrupo(
			FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	/**
	 * 
	 * @param colecaoRotasGrupo
	 * @param faturamentoAtividade
	 * @param faturamentoGrupo
	 * @return
	 */
	public Collection verificarSituacaoAtividadeRota(
			Collection colecaoRotasGrupo,
			FaturamentoAtividade faturamentoAtividade,
			FaturamentoGrupo faturamentoGrupo, boolean habilitada)
			throws ControladorException;

	public Integer inserirComandoAtividadeFaturamento(
			FaturamentoGrupo faturamentoGrupo,
			FaturamentoAtividade faturamentoAtividade, Collection colecaoRotas,
			Date dataVencimentoGrupo, Usuario usuarioLogado
//			, Date dataVencimentoContasNaoImpressas
			) throws ControladorException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Retorna uma lista de atividades de faturamento comandadas e ainda n�o
	 * realizadas
	 * 
	 */
	public Collection buscarAtividadeComandadaNaoRealizada(Integer numeroPagina)
			throws ControladorException;

	/**
	 * Este caso de uso permite alterar ou excluir um comando de atividade de
	 * faturamento
	 * 
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Retorna o count do resultado da pesquisa de Faturamento Atividade
	 * Cronograma n�o realizadas
	 * 
	 * buscarAtividadeComandadaNaoRealizadaCount
	 * 
	 * @author Roberta Costa
	 * @date 18/07/2006
	 * 
	 * @param filtroFaturamentoAtividadeCronograma
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer buscarAtividadeComandadaNaoRealizadaCount()
			throws ControladorException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * [SB0002] - Excluir Comando de Atividade de Faturamento
	 * 
	 */
	public void removerComandoAtividadeFaturamento(String[] ids)
			throws ControladorException;

	/**
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Atualizar Comando de Atividade de Faturamento
	 * 
	 */
	public void atualizarComandoAtividadeFaturamento(
			FaturamentoAtividadeCronograma faturamentoAtividadeCronograma,
			Collection colecaoFaturamentoAtividadeCronogramaRota)
			throws ControladorException;

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
	 * @throws ControladorException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgoto(
			Integer anoMesReferencia, Integer ligacaoSituacaoAguaId,
			Integer ligacaoSituacaoEsgotoId, Short indicadorFaturamentoAgua,
			Short indicadorFaturamentoEsgoto, Collection categoriasImovel,
			Integer consumoFaturadoAguaMes, Integer consumoFaturadoEsgotoMes,
			int consumoMinimoLigacao, Date dataLeituraAnterior,
			Date dataLeituraAtual, BigDecimal percentualEsgoto,
			Integer tarifaImovel, ConsumoTipo consumoTipoAgua, ConsumoTipo consumoTipoEsgoto) 
			throws ControladorException;

	/**
	 * 
	 * @param imovel
	 * @param situacao
	 * @return
	 * @throws ControladorException
	 */
	public Date buscarDataLeituraCronograma(Imovel imovel, boolean situacao,
			Integer anoMesReferencia) throws ControladorException;

	/**
	 * Calcula os valores da conta de acordo com os par�metros passados
	 * 
	 * [UC0145] - Inserir Conta [SF0001] - Determinar Valores para Faturamento
	 * de �gua e/ou Esgoto Author: Raphael Rossiter 05/12/2005
	 * 
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
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta(
			String mesAnoConta, String imovelID, Integer situacaoAguaConta,
			Integer situacaoEsgotoConta, Collection colecaoCategoriaOUSubcategoria,
			String consumoAgua, String consumoEsgoto, String percentualEsgoto,
			Integer idConsumoTarifaConta, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * C�lcula o valor total dos d�bitos de uma conta de acordo com o informado
	 * pelo usu�rio
	 * 
	 * [UC0145] - Inserir Conta Author: Raphael Rossiter Data: 10/01/2006
	 * 
	 * @param colecaoDebitoCobrado
	 * @param requestMap
	 * @return BigDecimal valorTotalDebitoConta
	 * @throws ControladorException
	 */
	public BigDecimal calcularValorTotalDebitoConta(
			Collection<DebitoCobrado> colecaoDebitoCobrado,
			Map<String, String[]> requestMap) throws ControladorException;

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
			Map<String, String[]> requestMap) throws ControladorException;

	/**
	 * [UC0145] - Inserir Conta Author: Raphael Rossiter 05/12/2005
	 * 
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
	public Integer inserirConta(Integer mesAnoConta, Imovel imovel,
			Collection colecaoDebitoCobrado,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua,
			String consumoEsgoto, String percentualEsgoto,
			Date dataVencimentoConta,
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoInclusao contaMotivoInclusao,
			Map<String, String[]> requestMap, Usuario usuarioLogado,
			Integer leituraAnterior,Integer leituraAtual)
			throws ControladorException;

	/**
	 * [UC0147] - Cancelar Conta Author: Raphael Rossiter Data: 10/12/2005
	 * 
	 * @param colecaoContas -
	 *            cole��o com todas as contas do im�vel
	 * @param identificadores -
	 *            identifica atrav�s do ID, quais as contas que ser�o canceladas
	 * @param motivoCancelamentoConta -
	 *            motivo do cancelamento escolhido pelo usu�rio
	 */
	public void cancelarConta(Collection<Conta> colecaoContas,
			String identificadores,
			ContaMotivoCancelamento contaMotivoCancelamento,
			Usuario usuarioLogado,
            boolean removerIdContaPagamento ) throws ControladorException;

	/**
	 * [UC0148] - Colocar Conta em Revis�o Author: Raphael Rossiter Data:
	 * 21/12/2005
	 * 
	 * @param colecaoContas -
	 *            cole��o com todas as contas do im�vel
	 * @param identificadores -
	 *            identifica atrav�s do ID, quais as contas que ser�o colocadas
	 *            em revis�o
	 * @param motivoRevis�oConta -
	 *            motivo da revis�o escolhida pelo usu�rio
	 */
	public void colocarRevisaoConta(Collection<Conta> colecaoContas,
			String identificadores, ContaMotivoRevisao contaMotivoRevisao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0149] - Retirar Conta em Revis�o Author: Raphael Rossiter Data:
	 * 22/12/2005
	 * 
	 * @param colecaoContas -
	 *            cole��o com todas as contas do im�vel
	 * @param identificadores -
	 *            identifica atrav�s do ID, quais as contas que ser�o retiradas
	 *            de revis�o
	 */
	public void retirarRevisaoConta(Collection<Conta> colecaoContas,
			String identificadores, Usuario usuario, boolean verificarPermissaoEspecial, Integer funcionalidade) throws ControladorException;

	/**
	 * [UC0151] - Alterar Vencimento de Conta Author: Raphael Rossiter Data:
	 * 22/12/2005
	 * 
	 * @param colecaoContas -
	 *            cole��o com todas as contas do im�vel
	 * @param identificadores -
	 *            identifica atrav�s do ID, quais as contas que sofrer�o
	 *            altera��o na sua data de vencimento
	 * @param dataVencimento -
	 *            a nova data de vencimento da conta
	 */
	public void alterarVencimentoConta(Collection<Conta> colecaoContas,
			String identificadores, Date dataVencimento, Usuario usuario)
			throws ControladorException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 26/12/2005
	 * 
	 * @param conta
	 * @return uma cole��o com os d�bitos cobrados de uma conta
	 * @throws ControladorException
	 */
	public Collection<DebitoCobrado> obterDebitosCobradosConta(Conta conta)
			throws ControladorException;

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 28/12/2005
	 * 
	 * @param conta
	 * @return uma cole��o com os cr�ditos realizados de uma conta
	 * @throws ControladorException
	 */
	public Collection<CreditoRealizado> obterCreditosRealizadosConta(Conta conta)
			throws ControladorException;

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
	 * @param colecaoClientes 
	 * @throws ControladorException
	 */
	public Integer retificarConta(Integer mesAnoConta, Conta contaAtual,
			Imovel imovel, Collection colecaoDebitoCobrado,
			Collection colecaoCreditoRealizado,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua,
			String consumoEsgoto, String percentualEsgoto,
			Date dataVencimentoConta,
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoRetificacao contaMotivoRetificacao,
			Map<String, String[]> requestMap, Usuario usuarioLogado, String consumoTarifa, 
            boolean atualizarMediaConsumoHistorico,Integer leituraAnterior,
            Integer leituraAtual,boolean atualizarLeituraAnteriorEAtualConta , String retorno,
            Integer leituraAnteriorPoco,Integer leituraAtualPoco,Integer volumePoco,BigDecimal percentualColeta)
			throws ControladorException;

	/**
	 * [UC0183 - Inserir D�bito A Cobrar]
	 * 
	 * @author Rafael Santos, Pedro Alexandre, Raphael Rossiter
	 * @date 23/12/2005, 21/11/2006, 13/04/2010 
	 *
	 * @param numeroPrestacoes
	 * @param debitoACobrar
	 * @param valorTotalServico
	 * @param imovel
	 * @param percentualAbatimento
	 * @param valorEntrada
	 * @param usuarioLogado
	 * @param debitoParaPagamentoAntecipado
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer inserirDebitoACobrar(Integer numeroPrestacoes, DebitoACobrar debitoACobrar, 
			BigDecimal valorTotalServico, Imovel imovel, BigDecimal percentualAbatimento,
			BigDecimal valorEntrada, Usuario usuarioLogado, boolean debitoParaPagamentoAntecipado)
			throws ControladorException ;

	/**
	 * [UC0186 - Calcular Presta��o] Author: Rafael Santos Data: 23/12/2005
	 * 
	 * @param taxaJurosFinanciamento
	 *            Taxa de Juros do Financiamento
	 * @param numeroPrestacoes
	 *            Numero de Prestacoes
	 * @param valorTotalServico
	 *            Valor Total de Servico
	 * @param valorEntrada
	 *            Valor de Entrada
	 * @param percentualAbatimento
	 *            PErcentual Abatimento
	 * @return O valor da Prestacao
	 */
	public ArrayList calcularValorPrestacao(BigDecimal taxaJurosFinanciamento,
			Integer numeroPrestacoes, BigDecimal valorTotalServico,
			BigDecimal valorEntrada, BigDecimal percentualAbatimento,
			String idTipoDebito, BigDecimal valorTotalServicoAParcelar,
			Imovel imovel, Usuario usuario) throws ControladorException;

	/**
	 * [UC0183 - Inserir D�bito A Cobrar] Author: Rafael Santos Data: 29/12/2005
	 * Inserir Debito A Cobrar por Categoria
	 * 
	 * @param debitoACobrar
	 *            Debito A Cobrar
	 */
	public void inserirDebitoACobrarCategoria(DebitoACobrar debitoACobrar,
			Imovel imovel) throws ControladorException;

	/**
	 * [US0184] Manter D�bito A Cobrar Author: Rafael Santos Data: 30/12/2005
	 * 
	 * @param idsLista
	 *            de Id de D�bito a Cobrar
	 * @throws ControladorException
	 */
	public void cancelarDebitoACobrar(String[] ids, Usuario usuarioLogado, Integer matriculaImovel)
			throws ControladorException;

	/**
	 * Remover Tarifa de Consumo
	 * 
	 * @throws ControladorException
	 */

	public void removerTarifaConsumo(String[] ids) throws ControladorException;

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
			Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria)
			throws ControladorException;

	public void atualizarConsumoTarifa(
			ConsumoTarifaVigencia consumoTarifaVigencia,
			Collection<CategoriaFaixaConsumoTarifaHelper> colecaoCategoriaFaixaConsumoTarifaHelper, String func)
			throws ControladorException;

	/**
	 * [UC0145] - Inserir Conta Author: Raphael Rossiter Data: 12/01/2006
	 * C�lcula o valor total de �gua ou esgoto
	 * 
	 * @param calcularValoresAguaEsgotoHelper
	 * @param tipoRetorno
	 * @return valorTotalAguaOuEsgoto
	 */
	public BigDecimal calcularValorTotalAguaOuEsgotoPorCategoria(
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoHelper,
			String tipoRetorno) throws ControladorException;

	/**
	 * [UC0187] - Inserir Guia de Pagamento 
	 *
	 * @author Rafael Corr�a, Pedro Alexandre, Ivan S�rgio, Raphael Rossiter
	 * @date 16/01/2006, 23/11/2006, 05/07/2007, 11/01/2010
	 *
	 * @param guiaPagamento
	 * @param usuarioLogado
	 * @param qtdeDiasVencimento
	 * @param colecaoGuiaPagamentoItem
	 * @return String[]
	 * @throws ControladorException
	 */
	public String[] inserirGuiaPagamento(GuiaPagamento guiaPagamento, Usuario usuarioLogado, 
			Integer qtdeDiasVencimento, Collection colecaoGuiaPagamentoItem, Localidade localidadeParaCliente, boolean verificarPermissaoEspecial)
			throws ControladorException ;

	/**
	 * [UC0188] - Manter Guia de Pagamento
	 * 
	 * @author Rafael Corr�a
	 * @since 16/01/2006
	 * @param guiaPagamento
	 *            GuiaPagamento
	 * @throws ControladorException
	 */
	public void manterGuiaPagamento(GuiaPagamento guiaPagamento,
			Collection guiasPagamento, String[] registrosRemocao,
			ImovelCobrancaSituacao imovelCobrancaSituacao, Usuario usarioLogado)
			throws ControladorException;

	/**
	 * Verifica se o M�s/Ano informado � inferior ao M�s/Ano do Sistema
	 * 
	 * @param anoMesFaturamento
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarReferenciaFaturamentoCorrente(
			String anoMesFaturamento) throws ControladorException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 18/01/2006
	 * 
	 */
	public void inserirFaturamentoSituacaoHistorico(
			Collection collectionFaturamentoSituacaoHistorico)
			throws ControladorException;

	/**
	 * Consulta ResumoFaturamento para a gera��o do relat�rio '[UC0173] Gerar
	 * Relat�rio de Resumo Faturamento' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Rodrigo Silveira, Diogo Peixoto
	 * @created 18/01/2006, 25/04/2011
	 * 
	 * @param opcaoTotalizacao
	 * @param anoMesReferencia
	 * @param gerenciaRegional
	 * @param localidade
	 * @param municipio
	 * @param unidadeNegocio
	 * @param opcaoRelatorio
	 * 
	 * @return
	 */
	public Collection consultarResumoFaturamentoRelatorio(
			String opcaoTotalizacao, int anoMesReferencia,
			Integer gerenciaRegional, Integer localidade, 
			Integer municipio, Integer unidadeNegocio,String opcaoRelatorio)
			throws ControladorException;

	/**
	 * [UC0194] - Inserir Cr�dito a Realizar
	 * 
	 * @author Roberta Costa
	 * @since 12/01/2006
	 * @param creditoARealizar
	 *            CreditoARealizar
	 * @throws ControladorException
	 */
	public void inserirCreditoARealizar(Imovel imovel,
			CreditoARealizar creditoARealizaro, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0195] - Manter Cr�dito a Realizar Permite Cancelar um ou mais cr�ditos
	 * a realizar de um determinado im�vel
	 * 
	 * @author Roberta Costa
	 * @since 18/01/2006
	 * @param creditoARealizar
	 *            CreditoARealizar
	 * @throws ControladorException
	 */
	public void cancelarCreditoARealizar(String[] ids, Imovel imovel,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0146] - Manter Conta Author: Raphael Rossiter Data: 21/01/2006
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
	public Collection obterContasImovelManter(Imovel imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada) throws ControladorException;

	/**
	 * [UC0302] - Gerar Debitos A Cobrar de Acrescimos por Impontualidade
	 * Author: Fernanda Paiva Data: 24/04/2006
	 * 
	 * Obt�m as contas de um im�vel com ano/mes da data de vencimento menor ou
	 * igual ao ano/mes de referencia da arrecadacao corrente e com situacao
	 * atual correspondente a normal, retificada ou incluida.
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterContasImovel(Integer imovel, Integer situacaoNormal,
			Integer situacaoIncluida, Integer situacaoRetificada,
			Integer anoMesReferenciaArrecadacao) throws ControladorException;

	/**
	 * [UC0302] - Gerar Debitos A Cobrar de Acrescimos por Impontualidade
	 * Author: Fernanda Paiva Data: 24/04/2006
	 * 
	 * Obt�m as guias de pagamento de um im�vel com ano/mes da data de
	 * vencimento menor ou igual ao ano/mes de referencia da arrecadacao
	 * corrente e com situacao atual correspondente a normal, retificada ou
	 * incluida.
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterGuiasPagamentoImovel(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesReferenciaArrecadacao)
			throws ControladorException;

	/**
	 * Encerra o faturamento do m�s
	 * 
	 * [UC0155] - Encerrar Faturamento do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 07/10/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarFaturamentoMes(
			Collection<Integer> colecaoIdsLocalidades,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0167] - Obter Valor a Cobrar de Curto e Longo Prazo Author: Pedro
	 * Alexandre Data: 10/01/2006
	 * 
	 * @param numeroPrestacoes
	 *            n� de presta��es
	 * @param numeroPrestacoesCobradas
	 *            n� de presta��es cobradas
	 * @param valorCategoria
	 *            valor da categoria
	 * @throws ControladorException
	 */
	public BigDecimal[] obterValorACobrarDeCurtoELongoPrazo(
			short numeroPrestacoes, short numeroPrestacoesCobradas,
			BigDecimal valorCategoria) throws ControladorException;





	/**
	 * 
	 * @param colecaoCalcularValoresAguaEsgotoHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoTotalizando(
			Collection colecaoCalcularValoresAguaEsgotoHelper)
			throws ControladorException;

	/**
	 * Obtem os Debitos A Cobrar Categoria do Debito a Cobrar
	 * 
	 * @param debitoACobrarID
	 *            Id do Debito A Cobrar
	 * @return Cole��o de Debitos a Cobrar Categoria
	 */
	public Collection obterDebitoACobrarCategoria(Integer debitoACobrarID)
			throws ControladorException;

	/**
	 * Obtem os Debitos A Cobrar do Imovel
	 * 
	 * @param imovelID
	 *            Id do Imovel
	 * @param debitoCreditoSituacaoAtualID
	 *            ID do Debito Credito Situa��o
	 * @return Cole��o de Debitos a Cobrar
	 */
	public Collection obterDebitoACobrarImovel(Integer imovelID,
			Integer debitoCreditoSituacaoAtualID, int anoMesFaturamento)
			throws ControladorException;

	/**
	 * Obtem os Credito A Realizar do Imovel
	 * 
	 * @param imovelID
	 *            Id do Imovel
	 * @param debitoCreditoSituacaoAtualID
	 *            ID do Debito Credito Situa��o
	 * @return Cole��o de Creditos a Realizar
	 */
	public Collection obterCreditoARealizarImovel(Integer imovelID,
			Integer debitoCreditoSituacaoAtualID, int anoMesFaturamento)
			throws ControladorException;

	/**
	 * Obtem os Creditos Realizado Categoria
	 * 
	 * @param creditoARealizarID
	 *            Id do Creditoa A Realizar
	 * @return Cole��o de Creditos Realizados Categoria
	 */
	public Collection obterCreditoRealizarCategoria(Integer creditoARealizarID)
			throws ControladorException;

	/**
	 * Determina o Vencimento da Conta
	 * 
	 * @param imovel
	 *            Imovel
	 * @return Data do Vencimento da Conta
	 */
	// public Date determinarVencimentoConta(Imovel imovel);
	/**
	 * [UC0113] - Gerar Faturamento Grupo [SF007] - Determinar Vencimento da
	 * Conta Determina o Vencimento da Conta
	 * 
	 * @param imovel
	 *            Imovel
	 * @return Data do Vencimento da Conta
	 */
	/*
	 * public Date determinarVencimentoConta(Imovel imovel, Rota rota) throws
	 * ControladorException;
	 */

	public void removerFaturamentoCronograma(String[] ids,
			String pacoteNomeObjeto) throws ControladorException;

	/**
	 * Inseri uma cole��o de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa a conta do im�vel com a refer�ncia informada pelo usu�rio
	 * 
	 * [FS0012] - Verificar exist�ncia da conta
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return
	 * @throws ControladorException
	 */
	public Conta pesquisarContaDigitada(String idImovel, String referenciaConta)
			throws ControladorException;

	/**
	 * Inseri uma cole��o de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa o tipo de d�bito informado pelo usu�rio
	 * 
	 * [FS0020] - Verificar exist�ncia do tipo de d�bito
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idTipoDebitoDigitado
	 * @return
	 * @throws ControladorException
	 */
	public DebitoTipo pesquisarTipoDebitoDigitado(Integer idTipoDebitoDigitado)
			throws ControladorException;

	/**
	 * Permite executar as atividades do faturamento previamente comandadas
	 * 
	 * [UC0111] Executar Atividade do Faturamento
	 * 
	 * Lista as atividades de faturamento do cronograma que foram comandadas
	 * 
	 * obterAtividadesFaturamentoCronogramaComandada
	 * 
	 * @author Raphael Rossiter, Roberta Costa
	 * @date 29/03/2006, 29/04/20004
	 * 
	 * @return Collection<ExecutarAtividadeFaturamentoHelper>
	 * @throws ControladorException
	 */
	public Collection<ExecutarAtividadeFaturamentoHelper> obterAtividadesFaturamentoCronogramaComandada(
			Integer numeroPagina) throws ControladorException;

	/**
	 * 
	 * Inserir Debito A Cobrar para o Imovel
	 * 
	 * [UC0183] - Inserir Debito a Cobrar
	 * 
	 * @author Rafael Santos
	 * @date 01/04/2006
	 * 
	 * @param idDebitoTipo
	 * @return
	 */
	public DebitoTipo pesquisarDebitoTipo(String idDebitoTipo)
			throws ControladorException;

	/**
	 * [UC0186 - Calcular Presta��o] Author: Rafael Santos Data: 03/04/2006
	 * 
	 * @param taxaJurosFinanciamento
	 *            Taxa de Juros do Financiamento
	 * @param numeroPrestacoes
	 *            Numero de Prestacoes
	 * @param valorTotalServico
	 *            Valor Total de Servico
	 * @param valorEntrada
	 *            Valor de Entrada
	 * @return O valor da Prestacao
	 */
	public BigDecimal calcularPrestacao(BigDecimal taxaJurosFinanciamento,
			Integer numeroPrestacoes, BigDecimal valorTotalServico,
			BigDecimal valorEntrada) throws ControladorException;

	public void reajustarTarifaConsumo(
			Map<ConsumoTarifaVigencia, Map<ConsumoTarifaCategoria, BigDecimal>> mapReajuste)
			throws ControladorException;

	/**
	 * 
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
	 * 
	 * @author Administrador
	 * @date 03/04/2006
	 * 
	 * @param rotas
	 * @throws ControladorException
	 */
	public void gerarTaxaEntregaDeContaEmOutroEndereco(Collection<Rota> rotas,
			Integer anoMes, int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * O sistema seleciona os grupos de faturamento que possuem cronograma para
	 * o m�s corrente * [UC0144] Inserir Comando Atividade de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 03/03/2006
	 * 
	 * @return Collection<FaturamentoGrupo>
	 * @throws ErroRepositorioException
	 */
	public Collection<FaturamentoGrupo> pesquisarFaturamentoGrupoComCronogramaMensalParaMesCorrente()
			throws ControladorException;

	/**
	 * O sistema seleciona os grupos de faturamento que possuem cronograma para
	 * o m�s corrente
	 * 
	 * [UC0104] Manter Comando Atividade de Faturamento
	 * 
	 * Caso esteja no atualizar pode escolher todos os grupos exceto o
	 * selecionado para atualiza��o
	 * 
	 * pesquisarFaturamentoGrupoComCronogramaMensalParaMesCorrenteSemGupoSelecionado
	 * 
	 * @author Roberta Costa
	 * @date 20/07/2006
	 * 
	 * @return Collection<FaturamentoGrupo>
	 * @throws ControladorException
	 */
	public Collection<FaturamentoGrupo> pesquisarFaturamentoGrupoComCronogramaMensalParaMesCorrenteSemGupoSelecionado(
			Integer grupoSelecionado) throws ControladorException;

	/**
	 * 
	 * Metodo que retorna os im�veis das quadras pertencentes �s rotas
	 * 
	 * Utilizado pelo [UC0302] Gerar D�bitos a Cobrar de Acr�scimos por
	 * Impontualidade
	 * 
	 * @author fernanda paiva
	 * @date 20/04/2006
	 * 
	 * @param anoMes
	 * @return
	 */
	public Collection gerarDebitosACobrarDeAcrescimosPorImpontualidade(
			Collection rotas, 
			Short indicadorGeracaoMulta,
			Short indicadorGeracaoJuros, 
			Short indicadorGeracaoAtualizacao,
			int idFuncionalidadeIniciada, 
			boolean indicadorEncerrandoArrecadacao)throws ControladorException;

	/**
	 * Gera as faturas das contas dos im�veis para o cliente respons�vel pelo
	 * im�vel
	 * 
	 * [UC0320] Gerar Fatura de Cliente Respons�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2006
	 * 
	 * @throws ControladorException
	 */
	public void gerarFaturaClienteResponsavel(int idFuncionalidadeIniciada)
			throws ControladorException;

	public void inserirMensagemConta(ContaMensagem contaMensagem,
			String[] setorComercial, String[] quadra) throws ControladorException;

	/**
	 * Permite executar as atividades do faturamento previamente comandadas
	 * 
	 * [UC0111] Executar Atividade do Faturamento
	 * 
	 * Executa as atividade do Faturamento
	 * 
	 * executarAtividadeFaturamento
	 * 
	 * @author Roberta Costa
	 * @date 03/05/20006
	 * 
	 * @param idsFaturamentoAtividadeCronograma
	 * @throws ControladorException
	 */
	public void executarAtividadeFaturamento(
			String[] idsFaturamentoAtividadeCronograma)
			throws ControladorException;

	/**
	 * Met�do respons�vel por emitir as faturas geradas pelo [UC0320]
	 * 
	 * [UC0321] Emitir Fatura de Cliente Respons�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 27/04/2006
	 * 
	 * @param colecaoFatura
	 * @throws ControladorException
	 */
	public void emitirFaturaClienteResponsavel(
			Collection<Fatura> colecaoFatura, Integer anoMesFaturamentoCorrente)
			throws ControladorException;

	/**
	 * [UC0329] - Restabelecer Situa��o Anterior de Conta
	 * 
	 * @author Fernanda Paiva
	 * @date 05/05/2006
	 * 
	 * @param registrosRemocao
	 * @throws ControladorException
	 */
	public void restabelecerSituacaoAnteriorConta(Collection colecaoContas, Usuario usuario)
			throws ControladorException;

	/**
	 * Retorna o count do resultado da pesquisa de Faturamento Atividade
	 * Cronograma
	 * 
	 * pesquisarFaturamentoAtividadeCronogramaCount
	 * 
	 * @author Roberta Costa
	 * @date 05/05/2006
	 * 
	 * @param FaturamentoGrupoCronogramaMensal
	 *            faturamentoGrupoCronogramaMensal
	 * @param Integer
	 *            numeroPagina
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarFaturamentoAtividadeCronogramaComandadaNaoRealizadaCount()
			throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author S�vio Luiz, Pedro Alexandre
	 * @date 15/05/2006, 19/09/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param faturamentoGrupo
	 * @throws ControladorException
	 */
	public void emitirContas(Integer anoMesReferenciaFaturamento,
			FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada,
			int tipoConta, Integer idEmpresa,
			Short indicadorEmissaoExtratoFaturamento)
			throws ControladorException;

	public void emitirContasOrgaoPublico(Integer anoMesReferenciaFaturamento,
			FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada,
			int tipoConta, Integer idEmpresa,
			Short indicadorEmissaoExtratoFaturamento)
			throws ControladorException;

	public void atualizarMensagemConta(ContaMensagem contaMensagem)
			throws ControladorException;

	/**
	 * Pesquisa todas as contas para testar o batch
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 02/06/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsTodasConta() throws ControladorException;

	public Collection gerarRelacaoAcompanhamentoFaturamento(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			int anoMesReferencia

	) throws ControladorException;

	public Collection<FaturamentoAtividadeCronograma> pesquisarRelacaoAtividadesGrupo(
			Integer faturamentoGrupoId) throws ControladorException;

	/**
	 * O m�todo recebe uma cole��o de faturamento atividades acha as que tem
	 * atividade predecessora e compara a data desta com a data da sua
	 * predecessora.
	 * 
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 * @param faturamentoGrupoCronogramaMensal
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void validarFaturamentoCronograma(
			Collection faturamentoAtividadeCronogramas)
			throws ControladorException;

	/**
	 * [UC0169] Manter Taraifa de Consumo Prepara a Vig�ncia para Ser reajustada
	 * 
	 * @author Rafel Santos
	 * @date 21/07/2006
	 * 
	 */
	public void iniciarProcessoReajustarTarifaConsumo(
			Map listaParametrosValoresCategoria, Date dataNovaVigencia,
			String[] idsRecuperados) throws ControladorException;

	/**
	 * Metodo para validar: Caso usu�rio informe uma data prevista, de qualquer
	 * atividade, com o m�s/ano maior que o m�s ]/ano do cronograma+1, exibir a
	 * mensagem: "A data prevista da atividade n� pode ser superior a <<m�s/ano
	 * do cronograma+1>>"
	 * 
	 * @param faturamentoAtividadeCronogramas
	 *            Descri��o do par�metro
	 * @param mesAno
	 * @throws ControladorException
	 */
	public void validarFaturamentoCronogramaAtividadeMaiorQueMesAnoCronograma(
			int anoMes, Collection faturamentoAtividadeCronogramas)
			throws ControladorException;

	public Integer calcularConsumoTotalAguaOuEsgotoPorCategoria(
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoHelper,
			String tipoRetorno) throws ControladorException;

	/**
	 * Este caso de uso calcula a tarifa min�ma de �gua para um im�vel
	 * 
	 * [UC0451] Obter Tarifa Min�ma de �gua para um Im�vel
	 * 
	 * @author Roberta Costa
	 * @date 09/08/2006
	 * 
	 * @param imovel
	 * @param colecaoCategorias
	 * @return BigDecimal
	 * @throws ControladorException
	 */
	public BigDecimal obterTarifaMinimaAguaImovel(Imovel imovel)
			throws ControladorException;

	/**
	 * Este caso de uso inicia um processo para o mecanismo batch
	 * 
	 * [UC0111] - Iniciar Processo
	 * 
	 * Este subfluxo tem o papel de iniciar um processo de faturamento
	 * comandado, neste m�todo � feita uma busca para obter as atividades
	 * comandadas e n�o realizadas
	 * 
	 * [SB0001] - Iniciar Processo de Faturamento Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 14/08/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */

	public Collection<FaturamentoAtividadeCronograma> pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadas(
			int numeroPagina) throws ControladorException;

	/**
	 * Este caso de uso inicia um processo para o mecanismo batch
	 * 
	 * [UC0111] - Iniciar Processo
	 * 
	 * Este subfluxo tem o papel de iniciar um processo de faturamento
	 * comandado, neste m�todo � feita uma busca para obter as atividades
	 * comandadas e n�o realizadas
	 * 
	 * [SB0001] - Iniciar Processo de Faturamento Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 14/08/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public int pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadasCount()
			throws ControladorException;

	/**
	 * Pesquisa a existencia de uma conta pelo id da conta e pela data da ultima
	 * alteracao
	 * 
	 * @param id
	 *            Descri��o do par�metro
	 * @param ultimaAlteracao
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public Integer pesquisarExistenciaContaParaConcorrencia(String idConta,
			String ultimaAlteracao) throws ControladorException;

	/**
	 * Pesquisa a existencia de um debito tipo pelo id
	 * 
	 * @param id
	 *            Descri��o do par�metro
	 * @param ultimaAlteracao
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaDebitoTipo(Integer idDebitoTipo)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * @author lms
	 * @date 01/08/2006
	 */
	public DebitoTipo pesquisarDebitoTipo(Integer idDebitoTipo)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public CreditoTipo pesquisarCreditoTipo(Integer idCreditoTipo)
			throws ControladorException;

	/**
	 * Obtem dados da conta
	 * 
	 * @param idConta
	 *            Id da Conta
	 * @author Fernanda Paiva
	 * @date 04/09/2006
	 * 
	 * @return uma colecao de conta
	 */
	public Collection consultarConta(Integer idConta)
			throws ControladorException;

	/**
	 * Pesquisa a soma dos valores das multas cobradas para a conta.
	 * 
	 * @author Pedro Alexandre
	 * @date 19/09/2006
	 * 
	 * @param idConta
	 * @return
	 * @throws ControladorException
	 */
	public BigDecimal pesquisarValorMultasCobradas(int idConta)
			throws ControladorException;

	/**
	 * [UC0482]Emitir 2� Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContas(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,
			Short contaSemCodigoBarras) throws ControladorException;

	/**
	 * Recupera o id do cliente respons�vel pela conta
	 * 
	 * [UC0348] - Emitir Contas [UC0482]Emitir 2� Via de Conta
	 * 
	 * @author S�vio Luiz, Vivianne Sousa
	 * @date 15/05/2006 , 22/05/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisarIdClienteResponsavelConta(Integer idConta,
			boolean contaHistorico) throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0002] Determinar tipo de liga��o e tipo de medi��o
	 * 
	 * @author S�vio Luiz
	 * @date 15/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Integer[] determinarTipoLigacaoMedicao(
			EmitirContaHelper emitirContaHelper) throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0003] Obter Dados do Consumo e Medicao Anterior
	 * 
	 * @author S�vio Luiz
	 * @date 17/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public StringBuilder obterDadosConsumoAnterior(EmitirContaHelper emitirConta, int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0004] Obter Dados da Medi��o da Conta
	 * 
	 * @author S�vio Luiz
	 * @date 17/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Object[] obterDadosMedicaoConta(EmitirContaHelper emitirContaHelper,
			Integer tipoMedicao) throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0005] Obter Consumo Faturado e Consumo m�dio Di�rio
	 * 
	 * @author S�vio Luiz
	 * @date 17/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterConsumoFaturadoConsumoMedioDiario(
			EmitirContaHelper emitirContaHelper, Integer tipoMedicao,
			String diasConsumo) throws ControladorException;

	/**
	 * M�todo que retorna a soma de quantidade economia
	 * 
	 * [UC0348] Emitir Contas [UC0482]Emitir 2� Via de Conta
	 * 
	 * [SB0007] Obter Quantidade de Economias da Conta
	 * 
	 * @author S�vio Luiz, Vivianne Sousa
	 * @date 19/05/2006, 22/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterQuantidadeEconomiasConta(Integer idConta,
			boolean contaHistorico) throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0009] Obter Mensagem de Rateio de Consumo ou Consumo fixo de Esgoto
	 * 
	 * @author S�vio Luiz
	 * @date 19/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public StringBuilder obterMensagemRateioConsumo(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao)
			throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author S�vio Luiz
	 * @date 24/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemConta3Partes(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException;

	/**
	 * M�todo que retorna uma array de object de qualidade de agua
	 * 
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author S�vio Luiz
	 * @date 25/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsQualidadeAgua(
			EmitirContaHelper emitirContaHelper) throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00018] Gerar Linhas das Contas com D�bito Autom�tico
	 * 
	 * @author S�vio Luiz
	 * @date 24/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public StringBuilder[] gerarLinhasDemaisContas(
			EmitirContaHelper emitirContaHelper, Integer sequencialEmpresa,
			BigDecimal valorConta) throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00010] Gerar Linhas da Descri��o dos Servi�os e Tarifas
	 * 
	 * @author S�vio Luiz
	 * @date 26/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public StringBuilder gerarLinhasDescricaoServicoTarifas(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao)
			throws ControladorException;

	/**
	 * [UC0351 - Calcular Impostos Deduzidos da Conta
	 *
	 * @author Fernanda Paiva, Raphael Rossiter
	 * @date 21/09/2006, 04/04/2008
	 *
	 * @param idImovel
	 * @param anoMesReferencia
	 * @param valorAgua
	 * @param valorEsgoto
	 * @param valorDebito
	 * @param valorCredito
	 * @return GerarImpostosDeduzidosContaHelper
	 * @throws ControladorException
	 */
	public GerarImpostosDeduzidosContaHelper gerarImpostosDeduzidosConta(
			Integer idImovel, Integer anoMesReferencia, BigDecimal valorAgua,
			BigDecimal valorEsgoto, BigDecimal valorDebito,
			BigDecimal valorCredito, boolean preFaturamento) throws ControladorException;

	/**
	 * [UC0150] - Retificar Conta Author: Fernanda Paiva Data: 25/09/2006
	 * 
	 * Inseri na tabela ClienteConta os dados referentes aos clientes do imovel
	 * 
	 * @param conta
	 * @param colecaoCreditoRealizado
	 * @param imovel
	 * @param colecaoCategoria
	 * @throws ControladorException
	 */
	public void inserirClienteImovel(Imovel imovel, Conta contaAtual)
			throws ControladorException;

	/**
	 * [UC0150] - Retificar Conta Author: Fernanda Paiva Data: 25/09/2006
	 * 
	 * Inseri na tabela impostos deduzidos da conta
	 * 
	 * @param conta
	 * @param colecaoCreditoRealizado
	 * @param imovel
	 * @param colecaoCategoria
	 * @throws ControladorException
	 */
	public void inserirImpostosDeduzidosConta(
			GerarImpostosDeduzidosContaHelper impostosDeduzidosConta,
			Conta contaAtual) throws ControladorException;

	/**
	 * Permite gerar os d�bitos de doa��es para os im�veis contidos na cole��o
	 * [UC0394] Gerar D�bitos a Cobrar de Doa��es
	 * 
	 * @author C�sar Ara�jo, Raphael Rossiter
	 * @date 05/08/2006
	 * 
	 * @param Collection
	 *            <Rota> rotas
	 * @param int
	 *            idFuncionalidadeIniciada
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarDoacao(Collection<Rota> rotas,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * 
	 * @author Raphael Rossiter
	 * @date 30/10/2006
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaRetificacao(Integer idConta)
			throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * inserir guia de pagamento no momento da exibi��o.
	 * 
	 * [FS0003] Validar registro de atendimento [FS0007] Validar ordem de
	 * servico.
	 * 
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 * 
	 * @param RegistroAtendimento,OrdemServico,idImovel,idCliente
	 */
	public void validarExibirInserirGuiaPagamento(RegistroAtendimento ra,
			OrdemServico ordemServico, Integer idImovel, Integer idCliente)
			throws ControladorException;

	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * [SB0005] - Processar Recebimento de Acrescimos por Impontualidade
	 * 
	 * @author S�vio Luiz
	 * @since 07/10/2006
	 * @param guiaPagamento
	 *            GuiaPagamento
	 * @throws ControladorException
	 */

	public Integer inserirGuiaPagamentoCodigoBarras(
			GuiaPagamento guiaPagamento, Integer idDebitoTipo)
			throws ControladorException;

	public void atualizarAnoMesReferenciaFaturamentoGrupo(
			FaturamentoGrupo faturamentoGrupo,
			Integer anoMesReferenciaFaturamento, int atividade)
			throws ControladorException;

	/**
	 * Seleciona as contaas agrupando por im�vel
	 * 
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 22/11/2006, 08/06/2007
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public HashMap obterContaAgrupadasPorImovel(int anoMesReferenciaContabil,
			int idLocalidade, int idQuadra) throws ControladorException;

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 23/11/2006
	 * 
	 * @param filtroImovel
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelContaManter(FiltroImovel filtroImovel,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * 
	 * 
	 * Utilizado pelo [UC0] Manter Conta
	 * 
	 * @author Rafael Santos
	 * @date 23/11/2006
	 * 
	 * @param idConta
	 * @param dataUltimaAlteracao
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Object pesquisarDataUltimaAlteracaoConta(Integer idConta)
			throws ControladorException;

	public void atualizarDataHoraRealizacaoAtividade(Integer idAtividade,
			Integer anoMesReferencia, Integer idFaturamentoGrupo)
			throws ControladorException;

	/**
	 * Recupera a data de realiza��o passando o id do imovel e a quantidade de
	 * meses que quer subtrair,caso n�o queira subtrair colocar 0 [UC0488]
	 * Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * [SB0004] - Calcular Valor de �gua e/ou Esgoto
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataRealizacaoFaturamentoAtividadeCronagrama(
			Integer idImovel, int quantidadeMeses) throws ControladorException;

	public Collection obterContasImovelIntervalo(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesInicio,
			Integer anoMesFim, Integer idContaMotivoRevisao)
			throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Atualiza o ano/m�s de refer�ncia do faturamento somando mais um m�s.
	 * 
	 * @author Pedro Alexandre
	 * @date 08/01/2007
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @throws ControladorException
	 */
	public void atualizarAnoMesFaturamento(
			Integer anoMesFaturamentoSistemaParametro)
			throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Pesquisar os ids das localidades para encerrar o faturamento do m�s.
	 * 
	 * @author Pedro Alexandre
	 * @date 08/01/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarIdsLocalidadeParaEncerrarFaturamento()
			throws ControladorException;

	/**
	 * Pesquisar os ids das localidades para gerar o resumo das
	 * liga��es/economias.
	 * 
	 * @author Rodrigo Silveira
	 * @date 17/01/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias()
			throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Tarnsfere para o hist�rico de contas
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param contas
	 * @param anoMesFaturamentoSistemaParametro
	 * @throws ControladorException
	 */
	public void transferirContasParaHistorico(Collection<Conta> contas,
			int anoMesFaturamentoSistemaParametro) throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Para cada conta transferida para o hist�rico, atualiza o indicador de que
	 * a conta est� no hist�rico na tabela ContaGeral.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/10/2006
	 * 
	 * @param colecaoContas
	 * @throws ControladorException
	 */
	public void atualizarIndicadorContaNoHistorico(Collection colecaoContas)
			throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Transfere para o hist�rico os d�bitos a cobrar.
	 * 
	 * @author Pedro Alexandre
	 * @date 10/10/2006
	 * 
	 * @param debitosACobrar
	 * @throws ControladorException
	 */
	public void transferirDebitosACobrarParaHistorico(
			Collection<DebitoACobrar> debitosACobrar)
			throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Para cada d�bito a cobrar transferido para o hist�rico, atualiza o
	 * indicador de que o d�bito a cobrar est� no hist�rico na tabela
	 * DebitoACobrarGeral.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/10/2006
	 * 
	 * @param colecaoDebitosACobrar
	 * @throws ControladorException
	 */
	public void atualizarIndicadorDebitoACobrarNoHistorico(
			Collection colecaoDebitosACobrar) throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Transfere para o hist�rico os cr�ditos a realizar.
	 * 
	 * @author Pedro Alexandre
	 * @date 10/10/2006
	 * 
	 * @param creditosARealizar
	 * @throws ControladorException
	 */
	public void transferirCreditoARealizarParaHistorico(
			Collection<CreditoARealizar> creditosARealizar)
			throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Para cada cr�dito a realizar transferido para o hist�rico, atualiza o
	 * indicador de que o cr�dito a realizar est� no hist�rico na tabela
	 * CreditoARealizarGeral.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/10/2006
	 * 
	 * @param colecaoCreditosARealizar
	 * @throws ControladorException
	 */
	public void atualizarIndicadorCreditosARealizarNoHistorico(
			Collection colecaoCreditosARealizar) throws ControladorException;

	/**
	 * [UC0532] Gerar Relat�rio de Faturamento das Liga��es com Medi��o
	 * Individualizada
	 * 
	 * @author Vivianne Sousa
	 * @date 10/01/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			String anoMesfaturamentoGrupo) throws ControladorException;

	/**
	 * [UC0493] Emitir de Extrato de Consumo de Im�vel Condom�nio
	 * 
	 * Fl�vio Cordeiro 08/01/2007
	 */
	public void emitirExtratoConsumoImovelCondominio(String anoMesFaturamento,
			String idFaturamento, int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * [UC0173] Gerar Relat�rio de Resumo do Faturamento
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 24/01/2007, 25/04/2011
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Integer consultarQtdeRegistrosResumoFaturamentoRelatorio(int mesAnoReferencia, Integer localidade, 
			Integer municipio, Integer gerenciaRegional, String opcaoTotalizacao) throws ControladorException;

	/**
	 * [UC0335] Gerar Resumo de Pend�ncia
	 * 
	 * Pesquisar os ids das localidade
	 * 
	 * @author Ana Maria
	 * @date 29/01/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidade()
			throws ControladorException;

	/**
	 * @author Ana Maria
	 * @date 26/01/2007
	 * 
	 * @param idConta
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection obterConta(Integer idConta) throws ControladorException;

	/**
	 * [UC] Gerar Relat�rio de Contas Emitidas
	 * 
	 * @author Vivianne Sousa
	 * @param tipoImpressao 
	 * @created 30/01/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarContasEmitidasRelatorio(int anoMesReferencia,
			Integer grupoFaturamento, Collection esferaPoder, String tipoImpressao)
			throws ControladorException;

	/**
	 * [UC] Gerar Relat�rio de Contas Emitidas
	 * 
	 * @author Vivianne Sousa
	 * @created 02/02/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQtdeContasEmitidasRelatorio(int anoMesReferencia,
			Integer grupoFaturamento, Collection esferaPoder)
			throws ControladorException;

	/**
	 * [UC0155] Encerrar Faturamento do M�s
	 * 
	 * Metodo respons�vel pela transfer�ncia das contas, d�bito a cobrar e
	 * cr�dito a realizar para o hist�rico, assim com a atualiza�a� dos im�veis.
	 * 
	 * @author Pedro Alexandre
	 * @date 06/02/2007
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void gerarHistoricoParaEncerrarFaturamento(
			int anoMesFaturamentoSistemaParametro, Integer idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * retorna o anoMes do faturamento grupo do im�vel passado
	 */
	public Integer retornaAnoMesFaturamentoGrupo(Integer idImovel)
			throws ControladorException;

	/**
	 * Monta a colecao de resultdos apartir da tbela conta impressao para
	 * geracao do relatorio de MAPA DE CONTROLE DAS CONTAS EMITIDAS
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 13/02/2007
	 * 
	 * @param idGrupoFaturamento
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarMapaControleContaRelatorio(
			Integer idGrupoFaturamento, String mesAno, Usuario usuarioLogado,
			String tipoRelatorio, String indicadorFichaCompensacao) throws ControladorException;

	/**
	 * Monta a colecao de resultdos apartir da tabela conta impressao para
	 * geracao do relatorio de RESUMO CONTAS EMITIDAS POR LOCALIDADE NO GRUPO
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 13/02/2007
	 * 
	 * @param idGrupoFaturamento
	 * @param anoMes
	 * @param tipoImpressao 
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarResumoContasLocalidade(Integer idGrupoFaturamento,
			String anoMes, Integer idFirma, String tipoImpressao) throws ControladorException;

	/**
	 * Recupera as contas
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarContasImoveis(Integer anoMes, Collection idsImovel,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, 
			Integer anoMesFim, String indicadorContaPaga)
			throws ControladorException;
	

	/**
	 * 
	 * [UC0544] - Gerar Arquivo Texto do Faturamento
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 23/03/2007
	 * 
	 * @param anoMes
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void chamarGerarArquivoTextoFaturamento(int anoMes,
			String idCliente, Collection colecaoClientesAptos);

	/**
	 * [UC0147] - Cancelar Conjunto Conta
	 * 
	 * @author Ana Maria
	 * @date 10/12/2005
	 * 
	 * @param colecaoContas
	 * @param identificadores
	 * @param contaMotivoCancelamento
	 * @param usuarioLogadosquisar
	 * @throws ControladorException
	 */
	public void cancelarConjuntoConta(Collection colecaoImovel,
			ContaMotivoCancelamento contaMotivoCancelamento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, 
			Integer anoMesFim, Usuario usuarioLogado, String indicadorContaPaga)
			throws ControladorException;

	/**
	 * Alterar Vencimento do Conjunto de Conta
	 * 
	 * @author Ana Maria
	 * @date 20/01/2007
	 * 
	 * @param colecaoContas
	 * @param dataVencimento
	 * @throws ControladorException
	 */

	public void alterarVencimentoConjuntoConta(Collection colecaoImovel, Date dataVencimento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuario,
			String indicadorContaPaga, String[] bancos)
			throws ControladorException;

	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Ana Maria
	 * @date 24/01/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoConta(Collection colecaoImovel,
			Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, 
			Integer anoMesFim, String indicadorContaPaga)
			throws ControladorException;	

	/**
	 * Informar Tarifa de Consumo por Subcategoria
	 * 
	 * @autor Tiago Moreno
	 * @date 05/01/2006
	 * @param consumoTarifa
	 * @param consumoTarifaVigencia
	 * @param colecaoConsumoTarifaCategoria
	 * @param colecaoConsumoTarifaFaixa
	 * @throws ControladorException
	 */

	public void informarConsumoTarifaSubcategoria(ConsumoTarifa consumoTarifa,
			ConsumoTarifaVigencia consumoTarifaVigencia,
			Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria)
			throws ControladorException;

	/**
	 * [UC0157] - Simular C�lculo da Conta
	 * 
	 * [FS0003] - Verificar Consumo M�nimo
	 * 
	 * @author Raphael Rossiter
	 * @date 02/04/2007
	 * 
	 * @param idLigacaoAguaSituacao,
	 *            consumoFaturado
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarConsumoFaturadoAgua(Integer idLigacaoAguaSituacao,
			Integer consumoFaturado) throws ControladorException;

	/**
	 * [UC0157] - Simular C�lculo da Conta
	 * 
	 * [FS0004] - Verificar Volume M�nimo
	 * 
	 * @author Raphael Rossiter
	 * @date 02/04/2007
	 * 
	 * @param idLigacaoEsgotoSituacao,
	 *            consumoFaturado
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarConsumoFaturadoEsgoto(Integer idLigacaoEsgotoSituacao,
			Integer consumoFaturado) throws ControladorException;

	/**
	 * [UC0XXX] Emitir Aviso de Cobran�a
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 09/04/2007
	 * 
	 */
	public Object[] pesquisarAnoMesEDiaVencimentoFaturamentoGrupo(
			Integer idImovel) throws ControladorException;

	/**
	 * Pesquisa a soma dos valores das multas cobradas para a conta.
	 * 
	 * @author S�vio Luiz
	 * @date 13/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorMultasCobradasPorFinanciamnetoTipo(
			int idConta) throws ControladorException;

	/**
	 * Este caso de uso calcula a tarifa min�ma de �gua para um im�vel
	 * (SUBCATEGORIA)
	 * 
	 * [UC0451] Obter Tarifa Min�ma de �gua para um Im�vel
	 * 
	 * @author Raphael Rossiter
	 * @date 13/04/2006
	 * 
	 * @param imovel
	 * @param colecaoSubcategorias
	 * @return BigDecimal
	 * @throws ControladorException
	 */
	public BigDecimal obterTarifaMinimaAguaImovelPorSubcategoria(Imovel imovel)
			throws ControladorException;

	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0004] Verificar Crit�rio
	 * de Cobran�a para Im�vel Pesquisa a soma dos imoveis com parcelamento.
	 * 
	 * @author S�vio Luiz
	 * @date 13/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public int pesquisarQuantidadeDebitosCobradosComParcelamento(
			Collection<ContaValoresHelper> colecaoContasValores)
			throws ControladorException;

	/**
	 * Pesquisar conjunto de contas p/ emiss�o da 2�Via
	 * 
	 * @author Ana Maria
	 * @date 19/04/2007
	 * 
	 * @param colecaoImovel
	 * @param anoMes
	 * @throws ControladorException
	 */

	public Collection pesquisarConjuntoContaEmitir2Via(Collection colecaoImovel, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga)
			throws ControladorException;

	/**
	 * Gera credito a realizar para os im�veis de determinados grupos
	 * 
	 * BATCH PARA CORRE��O DA BASE
	 * 
	 * @author S�vio Luiz
	 * @date 02/05/2007
	 * 
	 */
	public void gerarCreditoARealizarPorImoveisDoGrupo(Collection idsGrupos,
			Integer anoMesReferenciaConta, Integer anoMesReferenciaDebito)
			throws ControladorException;

	/**
	 * [UC0144] Inserir Comando Atividade Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 05/05/2007
	 * 
	 * @param diaVencimento,
	 *            mesVencimento, anoVencimento
	 * @throws ControladorException
	 */
	public Date obterDataVencimentoFaturamentoGrupo(int diaVencimento,
			int mesVencimento, int anoVencimento) throws ControladorException;

	/**
	 * Recupera o id da Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 08/05/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarAnoMesReferenciaFaturamentoGrupo(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0XXX] - Gerar Relat�rio Tarifa de Consumo
	 * 
	 * Pesquisas as tarifas de consumo para o relat�rio
	 * 
	 * @author Rafael Corr�a
	 * @date 11/05/2007
	 * 
	 * @param descricao,
	 *            dataVigenciaInicial, dataVigenciaFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConsumoTarifaRelatorio(String descricao,
			Date dataVigenciaInicial, Date dataVigenciaFinal)
			throws ControladorException;

	/**
	 * [UC0XXX] - Gerar Relat�rio de Tarifa de Consumo
	 * 
	 * Pesquisas a data final de validade de uma tarifa de consumo
	 * 
	 * @author Rafael Corr�a
	 * @date 11/05/2007
	 * 
	 * @param Integer
	 *            idConsumoTarifa
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataFinalValidadeConsumoTarifa(
			Integer idConsumoTarifa, Date dataInicioVigencia)
			throws ControladorException;

	/**
	 * Recupera as contas do Im�veis
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer obterContasConjuntoImoveis(Integer anoMes,
			Collection idsImovel, Integer codigoCliente, Short relacaoTipo,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer idGrupoFaturamento,
			Integer anoMesFinal, String indicadorContaPaga, Integer somenteDebitoAutomatico) 
			throws ControladorException;
	
	/**
	 * Recupera quantidade contas sem est� em revis�o dos Im�veis
	 * 
	 * @author Ana Maria
	 * @date 19/03/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer obterContasRevisaoConjuntoImoveis(Integer anoMes,
			Collection idsImovel, Integer codigoCliente, Short relacaoTipo,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer idGrupoFaturamento,
			Integer anoMesFinal, String indicadorContaPaga) 
			throws ControladorException;
	
	/**
	 * [UC0147] - Cancelar Conjunto Conta Cliente
	 * 
	 * @author Ana Maria
	 * @date 10/12/2005
	 * 
	 * @param colecaoContas
	 * @param identificadores
	 * @param contaMotivoCancelamento
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void cancelarConjuntoContaCliente(Integer codigoCliente, Short relacaoTipo,
			ContaMotivoCancelamento contaMotivoCancelamento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * Alterar Vencimento do Conjunto de Conta
	 * 
	 * @author Ana Maria
	 * @date 20/01/2007
	 * 
	 * @param colecaoContas
	 * @param dataVencimento
	 * @throws ControladorException
	 */
	public void alterarVencimentoConjuntoContaCliente(Integer codigoCliente, Short relacaoTipo,
			Date dataVencimentoInformada, Integer anoMes, Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim, Usuario usuario, Integer codigoClienteSuperior)
			throws ControladorException;

	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Ana Maria
	 * @date 24/01/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoContaCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes,
			ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException;

	/**
	 * Pesquisar conjunto de contas p/ emiss�o da 2�Via
	 * 
	 * @author Ana Maria
	 * @date 19/04/2007
	 * 
	 * @param colecaoImovel
	 * @param anoMes
	 * @throws ControladorException
	 */
	public Collection pesquisarConjuntoContaClienteEmitir2Via(
			Integer codigoCliente, Short relacaoTipo, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException;

	/**
	 * Recupera id de conta(s) sem revis�o ou em revis�o por a��o do usu�rio
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasNaoEmRevisaoOuEmRevisaoPorAcaoUsuario(
			Collection idsConta) throws ControladorException;

	/**
	 * Recupera id de contas que est�o em revis�o por ac�o do usuario
	 * 
	 * @author Vivianne Sousa
	 * @date 14/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasEmRevisaoPorAcaoUsuario(Collection idsConta)
			throws ControladorException;

	/**
	 * Met�do respons�vel por inserir um Tipo de Credito
	 * 
	 * [UC0000 - Inserir Tipo Credito
	 * 
	 * @author Thiago Ten�rio
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param agencia
	 *            bancaria
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirTipoCredito(CreditoTipo creditoTipo,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Met�do respons�vel por inserir um Tipo de Credito
	 * 
	 * [UC0000 - Atualizar Tipo Credito
	 * 
	 * @author Thiago Ten�rio
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param agencia
	 *            bancaria
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer atualizarTipoCredito(CreditoTipo creditoTipo,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0513] Manter Tipo de Credito
	 * 
	 * Remover Tipo de Credito
	 * 
	 * @author Thiago Ten�rio
	 * @date 19/03/2007
	 * 
	 */
	public void removerTipoCredito(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite gerar um ralatorio analitico do faturamento
	 * 
	 * [UC0xxx]Gerar Relat�rio Anal�tico do Faturamento
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 18/05/2007
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param indicadorLocalidadeInformatizada
	 * @param idLocalidades
	 * @param idSetores
	 * @param idQuadras
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosRelatorioAnaliticoFaturamento(
			int anoMesFaturamento, Integer idFaturamentoGrupo,
			int indicadorLocalidadeInformatizada, Collection idLocalidades,
			Collection idSetores, Collection idQuadras, String tipoRelatorio,
			Usuario usuarioLogado);
	
	
	/**
	 * Met�do respons�vel por informar uma n�o entrega de documentos
	 * 
	 * [UC0000 - Informar Nao Entrega de Documentos
	 * 
	 * @author Thiago Ten�rio
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param DocumentoNaoEntregue
	 * 
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer informarNaoEntregaDocumentos(
			Collection colecaoDocumentosNaoEntregues, Usuario usuarioLogado)
			throws ControladorException;

	
	/**
	 * [UC0482]Emitir 2� Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 18/05/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContasHistorico(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,
			Short contaSemCodigoBarras) throws ControladorException;

	/**
	 * [UC0600] Emitir Histograma de �gua
	 * 
	 * @author Rafael Pinto
	 * @date 04/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<EmitirHistogramaAguaHelper> pesquisarEmitirHistogramaAgua(
			FiltrarEmitirHistogramaAguaHelper filtro)
			throws ControladorException;

	/**
	 * [UC0145] - Inserir Conta Author: Raphael Rossiter Data: 13/01/2006
	 * 
	 * Seleciona a partir da tabela CLIENTE_IMOVEL para IMOV_ID=Id do im�vel e
	 * CLIM_DTRELACAOFIM com o valor correspondente a nulo
	 * 
	 * @param IMOVEL
	 * @throws ControladorException
	 */
	public void inserirClienteConta(Conta conta, Imovel imovel)
			throws ControladorException;

	/**
	 * Inseri o tipo de debito na base
	 * 
	 * [UC0529] Inserir Tipo de D�bito
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 09/03/2007
	 * 
	 * @param descricao
	 * @param descricaoAbreviada
	 * @param idTipoFinanciamento
	 * @param indicadorGeracaoDebitoAutomatica
	 * @param indicadorGeracaoDebitoConta
	 * @param idLancamentoItemContabil
	 * @param valorLimeteDebito
	 * @param usuarioLogado
	 * @return
	 */

	public Integer inserirDebitoTipo(String descricao,
			String descricaoAbreviada, String idTipoFinanciamento,
			String indicadorGeracaoDebitoAutomatica,
			String indicadorGeracaoDebitoConta,
			String idLancamentoItemContabil, String valorLimeteDebito,
			Usuario usuarioLogado, String valorSugerido, String indicadorDebitoCartaoCredito,
			String indicadorJurosParCliente) throws ControladorException;

	/**
	 * Atualizar o tipo de debito na base
	 * 
	 * [UC0530] Atualizar Tipo de D�bito
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 15/03/2007
	 * 
	 * @param id
	 * @param descricao
	 * @param descricaoAbreviada
	 * @param idTipoFinanciamento
	 * @param indicadorGeracaoDebitoAutomatica
	 * @param indicadorGeracaoDebitoConta
	 * @param idLancamentoItemContabil
	 * @param valorLimeteDebito
	 * @param usuarioLogado
	 * @return
	 */

	public void atualizarDebitoTipo(DebitoTipo debitoTipoBase, String id,
			String descricao, String descricaoAbreviada,
			String idTipoFinanciamento,
			String indicadorGeracaoDebitoAutomatica,
			String indicadorGeracaoDebitoConta,
			String idLancamentoItemContabil, String valorLimiteDebito,
			String indicadorUso, Usuario usuarioLogado,String valorSugerido,
			String indicadorDebitoCartaoCredito, String indicadorJurosParCliente)
			throws ControladorException;

	/**
	 * Determina qual a menor datade vencimento para uma cole��o de contas
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 19/06/2007
	 * 
	 * @param colecaoContas
	 * @return
	 * @throws ControladorException
	 */
	public Date determinarMenorDataVencimentoConta(Collection colecaoContas)
			throws ControladorException;

	/**
	 * Remove os contratos de demanda selecionados pelo usu�rio
	 * 
	 * [UC0513] - Manter Contrato de Demanda
	 * 
	 * @author Rafael Corr�a
	 * @date 27/06/2007
	 * 
	 * @param idsContratosDemanda
	 * @throws ControladorException
	 */
	public void removerContratosDemanda(String[] idsContratosDemanda,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Obter a data de vencimento de um grupo de faturamento, no m�s de
	 * faturamento corrente.
	 * 
	 * [UC0618] Obter data de vencimento do grupo
	 * 
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 * 
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ControladorException
	 */
	public Date obterDataVencimentoGrupo(Integer idFaturamentoGrupo, Integer anoMesInformado)
			throws ControladorException;

	/**
	 * 
	 * Permite atualizar os dados de um contrato de demanda
	 * 
	 * [UC0513] Manter Contrato de Demanda
	 * 
	 * [SB0001] Atualizar Contrato de Demanda
	 * 
	 * @author Rafael Corr�a
	 * @param usuarioLogado
	 * @date 28/06/2007
	 * 
	 */
	public void atualizarContratoDemanda(ContratoDemanda contratoDemanda,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * 
	 * Metodo que retorna a data de revis�o da conta
	 * 
	 * @author Vivianne Sousa
	 * @date 06/07/2007
	 * 
	 * @param idsConta
	 * @return
	 */
	public Collection pesquisarDataRevisaoConta(Collection idsConta)
			throws ControladorException;

	/**
	 * [UC0146] - Manter Conta Author: Raphael Rossiter Data: 21/01/2006
	 * 
	 * Obt�m as contas de um im�vel que poder�o ser mantidas
	 * 
	 * @param imovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarImoveisComDebito(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0623] - Gerar Resumo de Metas CAERN Author: S�vio Luiz Data:
	 * 20/07/2007
	 * 
	 * Obt�m as contas de um im�vel que poder�o ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoMetas(Integer anoMesReferencia)
			throws ControladorException;

	/**
	 * [UC0623] - Gerar Resumo de Metas CAERN Author: S�vio Luiz Data:
	 * 20/07/2007
	 * 
	 * Obt�m as contas de um im�vel que poder�o ser mantidas
	 * 
	 * @param imovel
	 * @param situacaoNormal
	 * @param situacaoIncluida
	 * @param situacaoRetificada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoMetasAcumulado(Integer anoMesReferencia)
			throws ControladorException;

	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Pedro Alexandre
	 * @date 27/07/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Short recuperarValorMaximoSequencialImpressaoMais10()
			throws ControladorException;

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 10/08/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualConta(Integer idImovel, Integer anoMesReferencia)
			throws ControladorException ;
	
	
	/**
	 * [UC0147] - Cancelar Conjunto Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void cancelarConjuntoConta(Integer idGrupoFaturamento,
			ContaMotivoCancelamento contaMotivoCancelamento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuarioLogado)
			throws ControladorException ;
	
	/**
	 * Alterar Vencimento do Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void alterarVencimentoConjuntoConta(Integer idGrupoFaturamento,
			Date dataVencimentoInformada, Integer anoMes, Integer anoMesFim,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,Usuario usuarioLogado)
			throws ControladorException ;
	
	
	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoConta(Integer idGrupoFaturamento,
			Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException ;
	
	/**
	 * Pesquisar conjunto de contas p/ emiss�o da 2�Via
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarConjuntoContaEmitir2Via(
			Integer idGrupoFaturamento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException ;


	/**
	 * Metodo temporario para corre��o da base de dados
	 * 
	 * Gerar Cr�dito a Realizar para os im�veis com contas com vencimento em
	 * 14/08/2007 com multa da conta 06/2007 cobrada na conta 07/2007 e que
	 * pagaram em 17/07/2007
	 * 
	 * @author Pedro Alexandre
	 * @date 20/08/2007
	 * 
	 * @throws ControladorException
	 */

	public void gerarCreditoARealizarPorImoveisComContasComVencimento14_08_2007() throws ControladorException ;
	
	
	/**
	 * [UC0216] Calcular Acrescimo por Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2007
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaAtualizacaoTarifaria(Integer idConta)
			throws ControladorException ;

	/**
	 * Recupera o data prevista do faturamento atividade cronograma
	 * 
	 * @author S�vio Luiz
	 * @date 28/08/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarFaturamentoAtividadeCronogramaDataPrevista(
			Integer faturamentoGrupoId, Integer faturamentoAtividadeId,
			Integer anoMesReferencia) throws ControladorException;
	
	
	/**
	 * [UC0596] Inserir Qualidade de Agua
	 *
	 * @author K�ssia Albuquerque
	 * @date 06/08/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public void inserirQualidadeAgua(QualidadeAgua qualidadeAgua,Collection colecaoQualidadeAgua,
			Usuario usuarioLogado,QualidadeAguaPadrao qualidadeAguaPadrao) throws ControladorException;


	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 30/09/2007
	 * 
	 * @return Fatura
	 * @throws ErroRepositorioException
	 */
	public Fatura pesquisarFaturaPorQualificador(Short codigoQualificador, Integer anoMesReferencia,
			BigDecimal valorDebito) throws ControladorException ;
	
	/**
	 * obtem o consumo m�dio faturado nos ultimos 6 meses
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 05/09/2007
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public Integer obterValorConsumoMedio6meses(Integer idImovel)
		throws ControladorException;
	
	/**
	 * Pesquisa os dados necess�rio para a gera��o do relat�rio
	 * 
	 * [UC0637] - Gerar Relat�rios Volumes Faturados
	 * 
	 * @author Rafael Corr�a
	 * @created 11/09/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<VolumesFaturadosRelatorioHelper> pesquisarDadosRelatorioVolumesFaturados(
			Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5,
			Integer anoMes6) throws ControladorException;
	
	/**
	 * Pesquisa os dados necess�rio para a gera��o do relat�rio resumido
	 * 
	 * [UC0637] - Gerar Relat�rios Volumes Faturados
	 * 
	 * @author Rafael Corr�a
	 * @created 13/09/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<VolumesFaturadosRelatorioHelper> pesquisarDadosRelatorioVolumesFaturadosResumido(
			Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5,
			Integer anoMes6) throws ControladorException;
	
	/**
	 * Pesquisa os dados necess�rio para a gera��o do relat�rio
	 * 
	 * [UC0635] - Gerar Relat�rios de Contas em Revis�o
	 * 
	 * @author Rafael Corr�a
	 * @created 20/09/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<ContasEmRevisaoRelatorioHelper> pesquisarDadosRelatorioContasRevisao(
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, 
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil, Integer referenciaInicial,
			Integer referenciaFinal, Integer idCategoria, Integer idEsferaPoder) throws ControladorException;
	
	/**
	 * Pesquisa os dados necess�rio para a gera��o do relat�rio resumido
	 * 
	 * [UC0635] - Gerar Relat�rios de Contas em Revis�o
	 * 
	 * @author Rafael Corr�a
	 * @created 20/09/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<ContasEmRevisaoRelatorioHelper> pesquisarDadosRelatorioContasRevisaoResumido(
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, 
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil, Integer referenciaInicial,
			Integer referenciaFinal, Integer idCategoria, Integer idEsferaPoder) throws ControladorException;
	
	/**
	 * Pesquisa os dados necess�rio para a gera��o do relat�rio
	 * 
	 * [UC0638] - Gerar Relat�rios Anormalidade Consumo
	 * 
	 * @author Rafael Corr�a
	 * @created 15/10/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<GerarRelatorioAnormalidadeConsumoHelper> pesquisarDadosRelatorioAnormalidadeConsumo(
			Integer idGrupoFaturamento, Short codigoRota, Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal, 
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo, Collection<Integer> colecaoIdsAnormalidadeLeitura, 
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada, Integer tipoMedicao, 
			Collection<Integer> colecaoIdsEmpresa,Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria) throws ControladorException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 18/09/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasAtualizacaoTarifaria(
			Integer idImovel,
			Integer inicialReferencia,
			Integer finalReferencia,
			Date inicialVencimento,
			Date finalVencimento,
			Integer indicadorContasRevisao)
			throws ControladorException;
	
	/**
	 * Pesquisa os Valores das Faixas de D�bitos
	 * 
	 * @author Ivan S�rgio
	 * @created 14/09/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDebitoFaixaValores(
			Integer idFaixaValor,
			Double valorFaixa) throws ControladorException;
	
	
	public void removerQualidadeAgua(String[] ids, Usuario usuarioLogado)
	throws ControladorException;
	
	 /**
	 * [UC0626] Gerar Resumo de Metas Acumulado no M�s (CAERN)
	 * 
	 * @author S�vio Luiz
	 * @data 28/11/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public boolean verificarDebitoMais3MesesFaturaEmAberto(
			Integer anoMesReferencia, Integer idImovel) throws ControladorException;

	public Boolean pesquisarExisteciaParcelamentoConta(Integer idConta)
		throws ControladorException;
	
	/**
	 * Monta uma cole��o de contas categoria a partir de uma cole��o de categoria recebida
	 * @param colecaoCategoria
	 * @param conta
	 * @return
	 */
	public Collection montarColecaoContaCategoria(Collection colecaoSubcategoria, Conta conta);
    
    
    /**
     * [UC0352] Emitir Contas e Cartas
     * 
     * [SB0032] Obter Fator de Vencimento
     * 
     * @author Vivianne Sousa
     * @date 29/01/2008
     * 
     * @param colecaoConta
     * @throws ControladorExceptions
     */
    public String obterFatorVencimento(Date dataVencimento)throws ControladorException;
    
	/**
	 * [UC0532] Gerar Relat�rio de Faturamento das Liga��es com Medi��o
	 * Individualizada
	 * 
	 * @author Rafael Corr�a
	 * @date 02/06/2008
	 * 
	 * @param colecaoLigacoesMedicao
	 * @throws ControladorException
	 */
	public Collection pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(
			Collection<Imovel> colecaoImoveisGerarRelatorio,
			String anoMesfaturamentoGrupo) throws ControladorException;
    
    /**
     * [UC0254] - Efetuar An�lise do Movimento dos Arrecadadores
     * 
     * obtem imovel, localidade e conta atraves do id da conta
     * 
     * @author Vivianne Sousa
     * @date 29/01/2008
     * 
     * @param idConta
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */

    public Conta obterImovelLocalidadeConta(Integer idConta)
        throws ControladorException;
    
    /**
     * [UC0254] - Efetuar An�lise do Movimento dos Arrecadadores
     * 
     * obtem imovel, localidade e contaHistorico atraves do id da conta historico
     * 
     * @author Vivianne Sousa
     * @date 29/01/2008
     * 
     * @param idConta
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public ContaHistorico obterImovelLocalidadeContaHistorico(Integer idConta)
        throws ControladorException;
    
    /**
     * [UC0737] Atualiza Quantidade de Parcela Paga Consecutiva e Parcela B�nus
     * 
     * Retorna dados dos parcelamentos com RD = 8 que estejam com situa��o normal 
     * e que n�o exista outro parcelamento com data posterior  
     * 
     * @author Vivianne Sousa
     * @date 27/12/2007
     * 
     * @return 
     * @throws ErroRepositorioException
     */
    public void atualizaQtdeParcelaPagaConsecutivaEParcelaBonus(Integer idLocalidade, int idFuncionalidadeIniciada)
        throws ControladorException;
    
    //*************************************************************************************************
    //[UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	//Fl�vio Leonardo
	//09/05/2008
    //Contas Canceladas
    public Collection gerarRelatorioContasCanceladas(RelatorioContasCanceladasRetificadasHelper helper)throws ControladorException;
    
    //Contas Retificadas
    public Collection gerarRelatorioContasRetificadas(RelatorioContasCanceladasRetificadasHelper helper)throws ControladorException;
    
    
    /**
     * @author Vivianne Sousa
     * @date 15/05/2008
     */
    public Integer pesquisarMaxIdConta()throws ControladorException;
    
    /**
     * @author Vivianne Sousa
     * @date 15/05/2008
     */
    public Integer pesquisarMaxIdContaHistorico()throws ControladorException;
    
	/**
	 * [UC0641] - Emitir TXT de Fatura de Cliente Respons�vel
	 *
	 * @author Rafael Corr�a
	 * @date 10/07/2008
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioFaturasAgrupadasBean> pesquisarDadosRelatorioFaturasAgrupadas(Integer anoMesReferencia, Cliente cliente, Collection<Integer> idsClientes) 
			throws ControladorException;
	
	/**
	 * [UC0641] - Emitir TXT de Fatura de Cliente Respons�vel
	 *
	 * @author Rafael Corr�a
	 * @date 10/07/2008
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDadosRelatorioFaturasAgrupadasCount(Integer anoMesReferencia, Cliente cliente, Collection<Integer> idsClientes) throws ControladorException;
	
	/**
	 * [UC0641] - Emitir TXT de Fatura de Cliente Respons�vel
	 *
	 * Pesquisa os clientes associados as faturas de uma determinada esfera de porder 
	 *
	 * @author Rafael Corr�a
	 * @date 21/01/2009
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarClientesFaturas(Integer idEsferaPoder) throws ControladorException;
	
	/**
	 * [UC0352] - Emitir TXT de Fatura de Cliente Respons�vel
	 * 
	 * Pesquisa o percentual de reten��o
	 *
	 * @author Rafael Corr�a
	 * @date 12/07/2008
	 *
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarPercentualAliquota()
			throws ControladorException;
	
	/**
	 * [UCXXXX] - Relat�rio de Protocolo de Entrega de Faturas
	 *
	 * @author Rafael Corr�a
	 * @date 12/11/2008
	 *
	 * @throws ControladorException
	 */
	public Collection<RelatorioProtocoloEntregaFaturaBean> pesquisarDadosRelatorioProtocoloEntregaFatura(Integer anoMesReferencia, Cliente cliente, Collection<Integer> idsClientes) 
			throws ControladorException;
    
    /**
	 * [UC0001] Inserir Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 26/01/2007
	 * 
	 * @param Distrito
	 *            Operaciona Descri��o do par�metro
	 */
	public Integer inserirContratoDemanda(ContratoDemanda contratoDemanda,
			Usuario usuarioLogado) throws ControladorException ;
	
	/**
	 * [UC0153] - Apresentar Dados Para An�lise da Medi��o e Consumo
	 * 
	 * Pesquisa a situa��o especial de faturamento vigente do m�s/ano informada
	 *
	 * @author Rafael Corr�a
	 * @date 11/08/2008
	 *
	 * @param idImovel
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public Collection<FaturamentoSituacaoHistorico> pesquisarSituacaoEspecialFaturamentoVigente(
			Integer idImovel, Integer anoMesReferencia)
			throws ControladorException;
	
	/**
	 * [UC0194] Inserir Credito a Realizar
	 * 
	 * Pesquisa a quantidade de contas e contas hist�rico para um im�vel em uma refer�ncia
	 * 
	 * @author Rafael Corr�a
	 * @date 14/08/2008
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeContasEContasHistorico(Integer idImovel,
			Integer referenciaConta) throws ControladorException;
	
	/**
	 * [UC0857] - Gerar Relat�rio de Arrecada��o das Multas de Autos de Infra��o
	 * 
	 * Pesquisa os dados necess�rios para gera��o do relat�rio
	 *
	 * @author Rafael Corr�a
	 * @date 10/09/2008
	 *
	 * @param idUnidadeNegocio, idFuncionario, dataPagamentoInicial, dataPagamentoFinal
	 * @throws ControladorException
	 */
	public Collection<RelatorioAutoInfracaoBean> pesquisarDadosRelatorioAutoInfracao(
			Integer idUnidadeNegocio, Integer idFuncionario,
			Integer dataPagamentoInicial, Integer dataPagamentoFinal)
			throws ControladorException;
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Im�vel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param imovel
	 * @param anoMesFaturamento
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean permiteFaturamentoParaAgua(LigacaoAguaSituacao ligacaoAguaSituacao, Integer consumoAgua, 
			ConsumoTipo consumoTipo) throws ControladorException;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 03/07/2008
	 *
	 * @param rota
	 * @param anoMesFaturamento
	 * @param faturamentoGrupo
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarArquivoTextoParaFaturamento(
			Rota rota, Integer anoMesFaturamento, FaturamentoGrupo faturamentoGrupo,
			Date dataComando,int idFuncionalidadeIniciada) throws ControladorException ;
	
    /**
     * Verificar se esse grupo de Faturamento j� est� comandado
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public Boolean verificarGrupoFaturamentoComandado(
    		int anoMesReferenciaFaturamento, int idGrupoFaturamento) throws ControladorException;
    
    
    /**
     * retorna o maior valor entre o ano/m�s da data corrente e 
     * o ano/m�s de referencia do faturamento
     * (PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS)
     * 
     * @author Vivianne Sousa
     * @date 06/05/2008
     */
    public Integer obterReferenciaContabilConta(SistemaParametro sistemaParametro);

	/**
	 * [UC0870] Gerar Movimento de Contas em Cobran�a por Empresa
	 * 
	 * Pesquisa os im�veis das contas
	 * 
	 * @author: Rafael Corr�a
	 * @date: 28/10/2008
	 */
	public Collection<Integer> pesquisarImoveisInformarContasEmCobranca(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper,
			Integer numeroPagina, boolean percentualInformado) throws ControladorException;
    
    /**
	* [UC0866] Gerar Comando Contas em Cobran�a por Empresa
	*
	* Pesquisa a quantidade de contas
	*
	* @author: Rafael Corr�a
	* @date: 27/10/2008
	*/
	public Collection pesquisarQuantidadeContas(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper)
			throws ControladorException;
	
	/**
	 * [UC0000] - Retificar Conjunto de Conta a partir do [UC0146] Manter Conta
	 *
	 * @author Raphael Rossiter
	 * @date 04/11/2008
	 *
	 * @param colecaoContas
	 * @param identificadores
	 * @param ligacaoAguaSituacao
	 * @param consumoAgua
	 * @param ligacaoEsgotoSituacao
	 * @param consumoEsgoto
	 * @param dataVencimento
	 * @param contaMotivoRetificacao
	 * @param usuarioLogado
	 */
	public void retificarConjuntoConta(Collection<Conta> colecaoContas,
			String identificadores, LigacaoAguaSituacao ligacaoAguaSituacao, Integer consumoAgua,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Integer consumoEsgoto, Date dataVencimento, 
			ContaMotivoRetificacao contaMotivoRetificacao, Short indicadorCategoriaEconomiaConta, Usuario usuarioLogado) throws ControladorException;
	
	
	/**
	 * [UC0193] - Consultar Hist�rico de Faturamento
	 *
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 *
	 * @param imovelID
	 */
    public Collection obterDebitoACobrarImovel(Integer imovelID)
            throws ControladorException;
    
    /**
	 * [UC0193] - Consultar Hist�rico de Faturamento
	 *
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 *
	 * @param imovelID
	 */
    public Collection obterDebitoACobrarHistoricoImovel(Integer imovelID)
            throws ControladorException;
    
    /**
	 * [UC0193] - Consultar Hist�rico de Faturamento
	 *
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 *
	 * @param imovelID
	 */
    public Collection obterCreditoARealizarImovel(Integer imovelID)
            throws ControladorException;
    
    /**
	 * [UC0193] - Consultar Hist�rico de Faturamento
	 *
	 * @author Vivianne Sousa
	 * @date 11/11/2008
	 *
	 * @param imovelID
	 */
    public Collection obterCreditoARealizarHistoricoImovel(Integer imovelID)
            throws ControladorException;
    
    /**
	 * [UC0193] - Consultar Hist�rico de Faturamento
	 *
	 * @author Vivianne Sousa
	 * @date 12/11/2008
	 *
	 * @param imovelID
	 */
    public Collection obterGuiaPagamentoImovel(Integer imovelID)
            throws ControladorException ;
    
    /**
	 * [UC0193] - Consultar Hist�rico de Faturamento
	 *
	 * @author Vivianne Sousa
	 * @date 12/11/2008
	 *
	 * @param imovelID
	 */
    public Collection obterGuiaPagamentoHistoricoImovel(Integer imovelID)
            throws ControladorException;
    /**
	 * Pesquisar categoria por tarifa consumo
	 * 
	 * @author R�mulo Aur�lio
	 * @date 19/12/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
    public Collection pesquisarCategoriaPorTarifaConsumo(Integer idConsumoTarifa)
	throws ControladorException;
    
    /**
	 * [UC0877] - EmitirGuiaPagamentoEmAtraso
	 *
	 * @author Fl�vio Leonardo
	 * @date 27/01/2009
	 */
	public Collection pesquisarDadosRelatorioGuiaPagamentoEmAtraso(FiltroGuiaPagamento filtro) throws ControladorException;
    
    /**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Im�vel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param imovel
	 * @param anoMesFaturamento
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean permiteFaturamentoParaEsgoto(LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Integer consumoEsgoto, 
			ConsumoTipo consumoTipo) throws ControladorException;
	
	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 *
	 * [SB0002] - Determinar Valores para Faturamento de �gua e/ou Esgoto
	 *
	 * @author Raphael Rossiter
	 * @date 28/03/2008
	 *
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param colecaoCategoriasOUSubCategorias
	 * @return DeterminarValoresFaturamentoAguaEsgotoHelper
	 * @throws ControladorException
	 */
	public DeterminarValoresFaturamentoAguaEsgotoHelper determinarValoresFaturamentoAguaEsgoto(Imovel imovel, 
			Integer anoMesFaturamento, Collection colecaoCategoriasOUSubCategorias, 
			FaturamentoGrupo faturamentoGrupo, ConsumoHistorico consumoHistoricoAgua,
			ConsumoHistorico consumoHistoricoEsgoto) throws ControladorException;
    
    /**
     * 
     * [UC0871] Manter Fatura de Cliente Respons�vel
     * 
     * Inserir Fatura Item e Fatura Item Historico
     * Fl�vio Leonardo
     * 
     */
    public int inserirFaturaItemFaturaItemHistorico(Collection<FaturaItem> colecaoFaturaItem, Usuario usuarioLogado)
    throws ControladorException;
    
    /**
     * 
     * [UC0871] Manter Fatura de Cliente Respons�vel
     * 
     *  Remover Fatura Item e Inserir Fatura Item Historico
     * Fl�vio Leonardo
     * 
     */
    public void removerFaturaItemFaturaItemHistorico(Collection<FaturaItem> colecaoFaturaItemRemover, Usuario usuarioLogado)
    throws ControladorException;
    
    /**
	 * [UC0871] Manter Fatura de Cliente Respons�vel
	 */
	public BigDecimal somarValorFaturasItemFatura(Fatura fatura) throws ControladorException;
	
	/**
	 * [UC0871] Manter Fatura de Cliente Respons�vel
	 */
	public Date vencimentoFaturasItemFatura(Fatura fatura) throws ControladorException;
	
	/**
	 * [UC0871] Manter Fatura de Cliente Respons�vel
	 * @param fatura
	 * @throws ErroRepositorioException
	 */
	public void alterarVencimentoFaturaFaturaItem(Fatura fatura)throws ControladorException;

	/**
 	 * [UC0157] - Simular C�lculo da Conta
 	 *
 	 * @author Rafael Corr�a
 	 * @date 26/03/2009
 	 * 
	 * @throws ControladorException 
 	 */
	public Integer calcularConsumoMinimo(BigDecimal areaTotal, Integer anoMes,
			Collection<Categoria> colecaoCategoria,
			Collection<Subcategoria> colecaoSubcategoria, BigDecimal pontosUtilizacao,
			BigDecimal numeroMoradores)
			throws ControladorException;
	
	/**
	 * [UC0876] - Gerar Cr�dito Situa��o Especial Faturamento 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarCreditoSituacaoEspecialFaturamento(
			Collection colecaoFaturamentoAtividadeCronogramaRota, FaturamentoGrupo faturamentoGrupo,int atividade, 
			int idFuncionalidadeIniciada) throws ControladorException ;
	
    /**
	 * Consultar Leituras N�o Registradas
	 * 
	 * @author Vinicius Medeiros
	 * @date 11/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
    public Collection retornarLeiturasNaoRegistradas(FaturamentoGrupo faturamentoGrupo)
    	throws ControladorException;
    
    /**
	 * Pesquisa a soma dos valores das multas cobradas para a conta.
	 * 
	 * @author S�vio Luiz
	 * @date 13/04/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorCreditoPorOrigem(
			int idConta) throws ControladorException;

	/**
	 * Pesquisa o valor da �gua da conta.
	 * 
	 * @author S�vio Luiz
	 * @date 11/05/2009
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorAguaConta(
			Integer idImovel, Integer referencia) throws ControladorException;
    
	
	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author R�mulo Aur�lio
	 * @created 18/01/2006
	 * 
	 */
	public Integer inserirFaturamentoSituacaoComando(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper, 
			boolean retirar)
			throws ControladorException;
	
	/**
	 * [UC0896] Manter Autos de Infra��o
	 * 
	 * @author R�mulo Aur�lio
	 * @created 06/05/2009
	 * 
	 */
	public void atualizarAutoInfracao(AutosInfracao autosInfracao, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC0896] Manter Autos de Infra��o
	 * 
	 * @author Rafael Corr�a
	 * @created 15/06/2009
	 * 
	 */
	public void validarExistenciaDebitoAutoInfracao(Integer idAutosInfracao) throws ControladorException;
	
	/**
	 * [UC0896] Manter Autos de Infra��o
	 * 
	 * @author R�mulo Aur�lio
	 * @created 06/05/2009
	 * 
	 */
	
	public void validarDataEmissaoAutoInfracao(Date dataEmissao, 
			Integer idAutoInfracaoSituacao, SistemaParametro sistemaParametro)
			throws ControladorException;
	
	/**
	 * [UC0896] Manter Autos de Infra��o
	 * 
	 * @author R�mulo Aur�lio
	 * @created 06/05/2009
	 * 
	 */
	
	public void validarDataInicioRecursoAutoInfracao(Date dataEmissao, Date dataInicioRecurso, 
			SistemaParametro sistemaParametro)
			throws ControladorException;
	
	/**
	 * [UC0896] Manter Autos de Infra��o
	 * 
	 * @author R�mulo Aur�lio
	 * @created 06/05/2009
	 * 
	 */
	public void validarDataTerminoRecursoAutoInfracao(Date dataInicioRecurso, 
			Date dataTerminoRecurso) throws ControladorException;
	
	/**
	 * [UC0896] Manter Autos de Infra��o
	 * 
	 * @author Rafael Corr�a
	 * @created 17/06/2009
	 * 
	 */
	public void validarQuantidadeParcelasAutoInfracao(Integer numeroParcelas) throws ControladorException;
	
	/**
	 * Inserir Situacao Especial de Faturamento
	 * 
	 * @author R�mulo Aur�lio
	 * @date 11/05/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirSituacaoEspecialFaturamento(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper,
			boolean retirar, Collection colecaoImoveisParaInserir, 
			Integer  idFaturamentoSituacaoTipo, Integer anoMesReferenciaInicial,
			Integer anoMesReferenciaFinal ) throws ControladorException;
	
	/**
	 * [UC0857] - Conta quantidade de registros do relatorio
	 *
	 * @author Hugo Amorim
	 * @date 10/07/2009
	 *
	 * @param idUnidadeNegocio, idFuncionario, dataPagamentoInicial, dataPagamentoFinal
	 * @throws ErroRepositorioException
	 */
	public int countRelatorioAutoInfracao(
			Integer idUnidadeNegocio, Integer idFuncionario,
			Integer dataPagamentoInicial, Integer dataPagamentoFinal)
			throws ControladorException;
	
	/**
	 * [UC0184] - Manter d�bito a Cobrar
	 *
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 *
	 */
	public boolean verificarAutosAssociadosAoDebito(String[] idsDebitosACobrar)
			throws ControladorException;
	
	/**
	 * [UC0184] - Manter d�bito a Cobrar
	 *
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 *
	 */
	public void cancelarAutosInfracao(String[] idsDebitosACobrar) throws ControladorException;
	
	/**
	 * [UC0896] - Manter Autos de Infra��o
	 *
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 *
	 */
	public boolean validarExistenciaDebitoAtivosAutoInfracao(Integer idAutoInfracao)
			throws ControladorException;
	/**
	 * [UC0896] - Manter Autos de Infra��o
	 *
	 * @author Hugo Amorim
	 * @date 20/07/2009
	 *
	 */
	public boolean validarExistenciaDeDebitosAutoInfracao(Integer idAutoInfracao)
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
	public void atualizarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comandoOriginal,FaturamentoSituacaoComando comandoInserir, ArrayList<FaturamentoSituacaoHistorico> colecaoHistoricoInserir,FaturamentoSituacaoComando comandoRetirar, ArrayList<FaturamentoSituacaoHistorico> colecaoHistoricoRetirar) throws ControladorException;

	/**
	 * [UC0146] Manter Conta
	 *
	 * [SB0008] Retificar Conjunto Conta
	 * 
	 * [FS0033] Verificar permiss�o especial para informar apenas volume de esgoto
	 *
	 * @author Raphael Rossiter
	 * @date 02/07/2009
	 *
	 * @param helper
	 * @throws ControladorException
	 */
	public void retificarConjuntoContaConsumos(Integer idFuncionalidadeIniciada,Map parametros) 
		throws ControladorException;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito 
	 *
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 30/07/2009, 14/01/2010
	 *
	 * @param idArrecadacaoForma
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCartoes(Integer idArrecadacaoForma) throws ControladorException;
	
	 /**
	 * Atualiza Dados do Parcelamento para Cart�o de Cr�dito
	 *
	 * @author Hugo Amorim
	 * @date 31/07/2009
	 *
	 */
	public void confimarParcelamentoCartaoCredito(
			Parcelamento parcelamento,Collection parcelamentoPagamentoCartaoCreditoCollection,Collection debitoACobrar, 
			Collection<CreditoARealizar> colecaoCreditoAtualizar, Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0187] Inserir Guia de Pagamento
	 *
	 * [FS0020] Im�vel n�o possui conta para pagamento parcial
	 * 
	 * @author Raphael Rossiter
	 * @date 12/08/2009
	 *
	 * @param idImovel
	 * @param idDebitoTipo
	 * @throws ControladorException
	 */
	public Collection obterContasParaPagamentoParcial(Integer idImovel, Integer idDebitoTipo) 
		throws ControladorException;
	
	/**
	 * [UC0187] Inserir Guia de Pagamento
	 *
	 * [FS0021] Valor informado maior ou igual que valor da conta selecionada.
	 * 
	 * @author Raphael Rossiter
	 * @date 13/08/2009
	 *
	 * @param idConta
	 * @param idDebitoTipo
	 * @param valorTotalServico
	 * @throws ControladorException
	 */
	public void validarValorTotalServicoParaPagamentoParcial(Integer idConta, Integer idDebitoTipo, 
		BigDecimal valorTotalServico) throws ControladorException;
	
	/**
	* [UC0819] Gerar Historico do Encerramento do Faturamento
	*
	* @author Vivianne Sousa
	* @date 04/08/2009
	*
	* @return
	* @throws ErroRepositorioException
	*/
	public Collection<Integer> pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento()
		throws ControladorException;
	
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
	 *[SB] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 19/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<FaturamentoSituacaoComando> pesquisarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comando,Integer numeroPaginasPesquisa )throws ControladorException;
	
	/**
	 * [UC0926] - Gerar B�nus de Tarifa Social
	 *
	 * @author Hugo Amorim
	 * @date 25/08/2008
	 *
	 * @param faturamentoGrupo
	 * @param sistemaParametro
	 * @param colecaoRotas
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarBonusTarifaSocial(
			FaturamentoGrupo faturamentoGrupo,
			SistemaParametro sistemaParametro, Collection<Rota> colecaoRotas,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ControladorException 
	 */
	public Collection pesquisarVencimentoConta(Integer idImovel, Integer anoMesReferencia) throws ControladorException;
	
	/**
	 * Retorna a qualidade de �gua associada ao im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 07/09/2009
	 * @param imovel
	 * @return QualidadeAgua
	 * @throws ControladorException 
	 */
	public QualidadeAgua getQualidadeAgua(Imovel imovel, Integer anoMes) throws ControladorException;
	
	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idConta
	 * @return dataPagamento
	 */
	public Collection pesquisarDataPagamento(Integer idContal) 
		throws ControladorException;

	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Vivianne Sousa
	 * @date 09/09/2009
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idQuadra
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> obterContas(int anoMesReferenciaContabil,
			int idLocalidade, int idQuadra) throws ControladorException;

	
	/**
	 * PesquisarDataPrevistaFaturamentoAtividadeCronograma
	 * 
	 * [SB0004] - Calcular Valor de �gua e/ou Esgoto
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 11/09/2009
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataPrevistaFaturamentoAtividadeCronograma(
			Integer idImovel, int quantidadeMeses) throws ControladorException;

	
	/**
	 * [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2009
	 * @param RelatorioContasCanceladasRetificadasHelper
	 * @return quantidade de registros
	 */
    public Integer pesquisarQuantidadeContasCanceladasOuRetificadas(
    	RelatorioContasCanceladasRetificadasHelper helper,int tipoPesquisa)
    	throws ControladorException;
    
    /**
	 * M�todo respons�vel por verificar se existe no banco um determinado ID na tabela de 
	 * faturamento_grupo - caso exista o id passado como par�metro na tabela, retorna true, caso
	 * contr�rio retorna false
	 * 
	 * @param Integer id - id de um FaturamentoGrupo  
	 * @return boolean - true para existir o id na tabela, false para n�o existir
	 * @exception ErroRepositorioException
	 */
	public boolean verificarExistenciaIdGrupoFaturamento(Integer id) throws ControladorException;

    
	/**
	 * Atualiza um conjunto de leituras e anormalidades bem 
	 * como seu consumo e suas contas prefaturadas
	 * 
	 *  @author Bruno Barros
	 *  @date 09/09/2009
	 *  @param buffer - BufferedReader com o arquivo selecionado
	 *  @return void
	 *  @throws ControladorException
	 */
	public RetornoAtualizarFaturamentoMovimentoCelularHelper atualizarFaturamentoMovimentoCelular( 
			BufferedReader buffer, String nomeArquivo, boolean offLine,boolean finalizarArquivo, 
			Integer idRota , ArquivoTextoRetornoIS atualizarFaturamentoMovimentoCelular,
			BufferedReader bufferOriginal)
		throws ControladorException;

    
    /**
	 * Pesquisa a quantidade de dados do relat�rio
	 * [UC0635] - Gerar Relat�rios de Contas em Revis�o
	 * @author Arthur Carvalho
	 * @created 14/09/2009
	 * @exception ControladorException
	 */
	public Integer pesquisarDadosRelatorioContasRevisaoCount(
			Integer idGerenciaRegional, Integer idUnidadeNegocio, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, 
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil, Integer referenciaInicial, 
			Integer referenciaFinal, Integer idCategoria, Integer idEsferaPoder) throws ControladorException;
	

	public Integer gerarRelacaoAcompanhamentoFaturamentoCount(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			int anoMesReferencia

	) throws ControladorException;

	/**
	 *[UC0958] - Gerar Relat�rio de juros, Multas e D�bitos Cancelados
	 *
	 *@since 13/10/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioJurosMultasDebitosCanceladosHelper> pesquisarRelatorioJurosMultasDebitosCancelados(FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro) throws ControladorException;
	
	
	/**
	 *[UC0958] - Count Relat�rio de juros, Multas e D�bitos Cancelados
	 * 
	 *@since 10/12/2009
	 *@author Hugo Amorim
	 */
	public int countRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException;
	
	/**
	 * 
	 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim
	 * @since 14/12/2009
	 *
	 */
	public void gerarTxtContasProjetosEspeciais(
			String anoMes, Integer idCliente,Integer idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * 
	 * [UC0972] count TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim
	 * @since 15/12/2009
	 *
	 */
	public Integer countTxtContasProjetosEspeciais(String anoMes,Integer idCliente)
			throws ControladorException;
	
	/**
	 * [UC0352] - Emitir Contas e Cartas
	 * 
	 * @author S�vio Luiz
	 * @date 29/12/2009
	 */
	public String[] obterMensagemAnormalidadeConsumo(EmitirContaHelper emitirContaHelper) throws ControladorException;
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * Gerar um per�do de leitura para faturamento
	 * 
	 * @author Raphael Rossiter
	 * @data 18/09/2007
	 */
	public Date[] gerarPeriodoLeituraFaturamento(
			Date dataLeituraAtualFaturamento,
			Date dataLeituraAnteriorFaturamento,
			FaturamentoGrupo faturamentoGrupo,
			Integer anoMesFaturamento, Integer anoMesFaturamentoAnterior)
			throws ControladorException;
	
	/**
	 * [UC0980] - Gerar Resumo Simula��o do Faturamento
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 19/01/2010
	 */
	public void gerarResumoSimulacaoFaturamento(
			Collection colecaoFaturamentoAtividadeCronogramaRota,
			FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * Obter Dados da Medi��o da Conta
	 * 
	 * @author TiagoMoreno
	 * @date 25/02/2010
	 * 
	 * @param DataAnterior e DataAtual
	 * @throws ControladorException
	 */
	public Object[] obterLeituraAnteriorEAtual(Integer idImovel,
			Integer amReferencia) throws ControladorException;

	/**
	 * 
	 * [UC0994] - Envio de Email da Conta para o Cliente
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 02/03/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param localidade
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	
	public void envioEmailContaParaCliente(Localidade localidade,
			int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * 
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 *
	 * @author Hugo Leonardo
	 * @param  form
     * @throws ControladorException 
	 * @data  26/02/2010
	 * 
	 */
	public Collection<FaturamentoImediatoAjuste> pesquisarFaturamentoImediatoAjuste(
				FaturamentoImediatoAjusteHelper helper, int qtd) throws ControladorException;
	
	/**
	 * 
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 *
	 * @author Hugo Leonardo
     * @throws ControladorException 
	 * @data  01/03/2010
	 * 
	 */
	 public Integer contarFaturamentoImediatoAjuste(FaturamentoImediatoAjusteHelper helper) 
	 	throws ControladorException;
	
	 /**
	 * Este caso de uso permite enviar email para cliente informando que sua
	 * conta j� foi gerada. Retorno do celular
	 * 
	 * @author Raphael Rossiter
	 * @date 03/03/2010
	 * 
	 * @param rota
	 * @param colContaPreFaturada
	 * @param efetuarRateio
	 * @param atualizaSituacaoAtualConta - Caso seja chamado via a funcionalidade de ISC, n�o atualiza a situa��o atual da conta que n�o foi impressa.
	 *                                     Caso seja chamado via a funcionalidade de consistir, atualiza a situa��o atual da conta. 
	 * @throws ControladorException
	 */
	 public void processarMovimentoContaPrefaturada(Rota rota,
			Collection<MovimentoContaPrefaturada> colContaPreFaturada,
			boolean efetuarRateio) throws ControladorException ;
	
	/**
	 * [UC1001] Emitir declara��o de quita��o anual de d�bitos
	 * 
	 * 	Este caso de uso permite a gera��o de declara��o de quita��o de d�bitos.
	 * 
	 * @author Hugo Amorim
	 * @param sistemaParametro 
	 * @date 17/03/2010
	 */
	public void gerarDadosDeclaracaoQuitacaoAnualDebitos(
			int idFuncionalidadeIniciada,Collection<Integer> anos,Rota rota, 
			Short indicadorContaParcelada,Short indicadorCobrancaJudical,
			Date dataVerificacaoPagamentos) throws ControladorException;
	
	/**
	 * [UC1008] Gerar TXT declara��o de quita��o anual de d�bitos
	 * 
	 * 	Este caso de uso permite a gera��o do TXT da declara��o de quita��o de d�bitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Integer> pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos() 
		throws ControladorException;
	
	/**
	 * [UC1008] Gerar TXT declara��o de quita��o anual de d�bitos
	 * 
	 * 	Este caso de uso permite a gera��o do TXT da declara��o de quita��o de d�bitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public void gerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
			Integer idFuncionalidadeIniciada,Integer idGrupoFaturamento,Empresa empresa)
			throws ControladorException;
	/**
	 * [UC1010] Emitir 2� via de declara��o anual de quita��o de d�bitos
	 *
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException 
	 * @date 23/03/2010
	 */	
	public Collection<ExtratoQuitacaoItem> pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(Integer idExtratoQuitacao)
			throws ControladorException;
	
	/**
	 * [UC0194] - Inserir Cr�dito a Realizar 
	 *
	 * @author Raphael Rossiter
	 * @date 14/04/2010
	 *
	 * @param creditoARealizar
	 * @param imovel
	 * @param usuarioLogado
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer gerarCreditoARealizar(CreditoARealizar creditoARealizar, Imovel imovel, Usuario usuarioLogado)
		throws ControladorException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 27/04/2010
	 *
	 * @param idDebitoACobrar
	 * @throws ControladorException
	 */
	public void atualizarSituacaoAtualDebitoACobrar(Integer idDebitoACobrar) throws ControladorException ;


	/**
	 * [SB0002] � Replicar os d�bitos existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada d�bito tipo, e retorna uma cole��o com limite de 10 registros. 
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigencia(
			Integer numeroPagina) throws ControladorException;

	/**
	 * [SB0002] � Replicar os d�bitos existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo d�bito, e retorna o total.   
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Integer pesquisarDebitoTipoVigenciaUltimaVigenciaTotal()
			throws ControladorException;
	
	/**
	 * [SB0002] � Replicar os d�bitos existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo d�bito, e retorna uma cole��o.   
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(String[] selecionados) 
			throws ControladorException;
	
	/**
	 * [UC1001] Emitir declara��o de quita��o anual de d�bitos
	 * 
	 * 	Pequisa as contas do imovel e verifica se o mesmo esta de acordo
	 * com os parametros do caso de uso, se sim retorno uma cole��o de dados para inser��o.
	 * 
	 * @author Hugo Amorim
	 * @param indicadorCobrancaJudical 
	 * @param indicadorContaParcelada 
	 * @throws ErroRepositorioException 
	 * @date 17/03/2010
	 */
	public DeclaracaoQuitacaoAnualDebitosHelper pesquisarDadosParaGeracaoDaDeclaracaodeQuitacaoDebitos(
			Integer idImovel, Integer ano, Date dataVerificacaoPagamentos, 
			Short indicadorContaParcelada, Short indicadorCobrancaJudical) throws ControladorException;
	
	/**
	 * [UC0982] Inserir tipo de D�bito com Vig�ncia.
	 * 
	 * Verificar se existe vig�ncia j� cadastrada para o tipo de d�bito.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idDebitoTipo
	 * @param opcao
	 * @throws ControladorException
	 * @data 30/04/2010
	 * 
	 */
	public void verificarExistenciaVigenciaDebito(String dataVigenciaInicial, String dataVigenciaFinal, Integer idDebitoTipo, Integer opcao) 
			throws ControladorException;
	
	/**
	 * [UC1008] Gerar TXT declara��o de quita��o anual de d�bitos
	 * 
	 * 	Este caso de uso permite a gera��o do TXT da declara��o de quita��o de d�bitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Empresa> pesquisarEmpresasParaGeraracaoExtrato(Integer idGrupoFaturamento)
		throws ControladorException;
	
	/**
	 * [UC0391] Inserir valor de cobran�a de servi�o.
	 * 
	 * Verificar se existe valor de cobran�a de servi�o j� cadastrada.
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 07/06/2010
	 * 
	 */
	public Boolean validarVigenciaValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor) throws ControladorException;
	
	/**
	 * M�todo que retorna uma array de object com a soma do valor dos debitos
	 * cobrados de parcelamento,o numero da prestacao e o numero total de
	 * presta��es
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author S�vio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomatico(Integer idConta)
			throws ControladorException;
	
	/**
	 * M�todo que retorna uma array de object com a soma do valor dos debitos
	 * cobrados de parcelamento,o numero da prestacao e o numero total de
	 * presta��es
	 * 
	 * [UC0482]Emitir 2� Via de Conta
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomaticoHistorico(
			Integer idConta) throws ControladorException;
	
	/**
	 * M�todo que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author S�vio Luiz, Vivianne Sousa
	 * @date 19/05/2006, 16/01/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoPorTipo(Integer idConta)
	 throws ControladorException;
	
	/**
	 * M�todo que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * 
	 * [UC0482]Emitir 2� Via de Conta
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoHistoricoPorTipo(Integer idConta)
	 throws ControladorException;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos servi�os de parcelamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2008
	 * 
	 * @param conta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoCobradoDeParcelamento(Conta conta)
	 throws ControladorException; 
	
	/**
	 * TODO:COSANPA
	 * 
	 * M�todo para retornar uma cole��o de debitos cobrados do parcelamento
	 * */
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos servi�os de parcelamento
	 * 
	 * @author Adriana Muniz
	 * @date 13/05/2011
	 * 
	 * @param conta
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoCobradoDeParcelamentoIS(Conta conta)
			throws ControladorException;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos servi�os de parcelamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2008
	 * 
	 * @param conta
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoCobradoNaoParcelamento(Conta conta)
	 throws ControladorException;
	/**
     * Met�do respons�vel por emitir os txts das contas.
     * 
     * [UC0348] Emitir Contas
     * 
     * [SB0009] Obter Mensagem de Rateio de Consumo ou Consumo fixo de Esgoto
     * 
     * @author Vivianne Sousa
     * @date 13/11/2007
     * 
     * @param colecaoConta
     * @throws ControladorException
     */
    public StringBuilder obterMensagemRateioConsumoFichaCompensacao(
            EmitirContaHelper emitirContaHelper, String consumoRateio,
            Object[] parmsMedicaoHistorico, Integer tipoMedicao)
            throws ControladorException;
    
    /**
     * [UC1041] Gerar Taxa Percentual da Tarifa M�nima para Cortado 
     *
     * @author Raphael Rossiter
     * @date 09/07/2010
     *
     * @param colecaoFaturamentoAtividadeCronogramaRota
     * @param faturamentoGrupo
     * @param atividade
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    public void gerarTaxaPercentualTarifaMinimaCortado(
			Collection colecaoFaturamentoAtividadeCronogramaRota, FaturamentoGrupo faturamentoGrupo,int atividade, 
			int idFuncionalidadeIniciada) throws ControladorException ;
    
    /**
	 * [UC1035] Efetivar Alterar Inscri��o de Im�vel
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 08/07/2010
	 * 
	 */
    public void alterarInscricoesImoveis(
    		Integer idFuncionalidadeIniciada,Integer idLocalidade)
    		throws ControladorException;
    
    /**
     * [UC1042] Verificar Farturamento dos Im�veis Cortados 
     *
     * @author Raphael Rossiter
     * @date 13/07/2010
     *
     * @param colecaoFaturamentoAtividadeCronogramaRota
     * @param faturamentoGrupo
     * @param atividade
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    public void verificarFaturamentoImoveisCortados(
			Collection colecaoFaturamentoAtividadeCronogramaRota, FaturamentoGrupo faturamentoGrupo,int atividade, 
			int idFuncionalidadeIniciada) throws ControladorException ;
    
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 *
	 * @author Vivianne Sousa
	 * @date 21/07/2010
	 */
	public Conta pesquisarUltimaContaDoImovel(Integer idImovel)throws ControladorException;
	
	/**
	 * Pesquisa Conta Historico
	 * 
	 * Pesquisa a conta historico do im�vel com a refer�ncia informada pelo usu�rio
	 * 
	 * 
	 * @author Fernando Fontelles
	 * @date 06/08/2010
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return
	 */
	public ContaHistorico pesquisarContaHistoricoDigitada(String idImovel, String referenciaConta)
			throws ControladorException;
	
	/**
	 * Verifica a Quantidade de Alteracoes no Vencimento da Conta
	 * 
	 * @author Hugo Leonardo
	 * @date 10/08/2010
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarQuantidadeAlteracoesVencimentoConta( Collection idsConta) 
			throws ControladorException;
	
	/**
	 * Verifica a Quantidade de Alteracoes no Vencimento da Conta
	 * 
	 * @author Hugo Leonardo
	 * @date 11/08/2010
	 * 
	 * @param idConta
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarQuantidadeAlteracoesVencimentoConta(
			Integer idConta) throws ControladorException;

	
	/**
	 * [UC1051] Gerar Relat�rio de Amostragem das Anormalidades Informadas
	 * 
	 * @author Hugo Leonardo
	 * @date 09/08/2010
	 * 
	 * @throws ControladorException
	 */
	public Collection<GerarRelatorioAnormalidadePorAmostragemHelper> pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota, Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal, 
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo, Collection<Integer> colecaoIdsAnormalidadeLeitura, 
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada, Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria, Integer limite) throws ControladorException;

    
    /**
     *  [UC0820] - Atualizar Faturamento do Movimento Celular
     *  
     * Verifica se a quantidade de im�veis que chegaram 
     * � a esperada.
     * 
     * @author bruno
     * @date 16/08/2010
     * 
     * @param idRota - Id da rota ser verificada
     * @param anoMesFaturamento - Ano mes de faturamento a ser pesquisado
     * 
     * @return Integer
     *  
     */
    public Integer pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(Integer idRota,
            Integer anoMesFaturamento ) throws ControladorException;
    
    /**
     * @author R�mulo Aur�lio
     * @throws ControladorException
     * @data 22/06/2010
     */
    public Integer retornaAnoMesFaturamentoGrupoDaRota(Integer idRota)
            throws ControladorException;
    
	/**
	 * 
	 * Remove os im�veis que ja foram enviados para uma
	 * determinada rota em impress�o simultanea
	 * 
	 * @autor Bruno Barros.
	 * @date 24/08/2010 
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * @param BufferedReader - Buffer com TODOS os im�veis da rota
	 * 
	 * @return BufferedReader Novo buffer apenas com as matriculas que ainda precisam ser
	 * processadas
	 */
	public BufferedReader removerImoveisJaProcessadosBufferImpressaoSimultanea( Integer idRota, BufferedReader reader ) 
		throws ControladorException;    

    /**
	 * Metodo que retornar o grupo de faturamento a partir do id do Imovel
	 * 
	 * @author R�mulo Aur�lio
	 * @date 24/08/2010
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo recuperaGrupoFaturamentoDoImovel(Integer idImovel)throws ControladorException;
	
	  
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 *
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 *
	 * @param idOrdemServico
	 */
	public boolean verificarExistenciaAutosInfracaoPorOS(
			Integer idOrdemServico) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 *
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 *
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public AutosInfracao pesquisarAutosInfracaoPorOS(
			Integer idOrdemServico)throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 *
	 * @author Vivianne Sousa
	 * @date 24/08/2010
	 *
	 * @param idAutoInfracao
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaAutosInfracaoDebitoACobrar(
			Integer idAutoInfracao)throws ControladorException;
	
    /**
     * [UC0352] Emitir Contas e Cartas
     * 
     * [SB0031] Obter Representa��o num�rica do Nosso N�mero da Ficha de Compensa��o
     * 
     * @author Vivianne Sousa
     * @date 13/11/2007
     * 
     * @param colecaoConta
     * @throws ControladorException
     */
    public StringBuilder obterNossoNumeroFichaCompensacao(String idDocumentoTipo, String idDocumentoEmitido)
            throws ControladorException;

    
    /**
	 * [UC1010] Emitir 2� via de declara��o anual de quita��o de d�bitos
	 *
	 * @Author Daniel Alves
	 * @Date 14/09/2010
	 * 
	 */
	public Collection pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(String idImovel)throws ControladorException;
	
	/**
	 * [UC1073] � Religar Im�veis Cortados com Consumo Real
	 * 
	 * @author Vivianne Sousa
	 * @date 13/09/2010
	 * 
	 */
	public void religarImovelCortadoComConsumoReal(Integer anoMesReferenciaFaturamento, 
		Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException ;
	
	/**
	 * [UC0811] Processar Requisi��es do Dispositivo M�vel Impressao Simultanea.
	 * 
	 * M�todo criado para evitar o if "compesa" ou if "caern". Para todas as 
	 * empresas, o pr�ximo arquivo do leiturista � disponibilizado assim que o 
	 * arquivo anterior � finalizado. Apenas na compesa, n�o permite.
	 * 
	 * @author Bruno Barros
	 * @date 05/10/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public boolean liberaProximoArquivoImpressaoSimultaneaOnLine() throws ControladorException;	
	
	/**
	 * [UC1083] Prescrever D�bitos de Im�veis P�blicos
	 * 
	 * @author Hugo Leonardo
	 * @date 18/10/2010
	 * 
	 * @param PrescreverDebitosImovelHelper
	 * @throws ControladorException
	 */
	public Integer prescreverDebitosImoveisPublicos(PrescreverDebitosImovelHelper helper) 
		throws ControladorException;
	

	/**
	 * 
	 * [UC1038] Prescrever D�bitos de Im�veis P�blicos
	 * 
	 * @author Hugo Leonardo
	 * @date 18/10/2010
	 * 
	 */
	public void prescreverDebitosImoveisPublicosManual(Integer idFuncionalidadeIniciada, Map parametros) 
		throws ControladorException;
	
	/**
	 * 
	 * [UC1083] Prescrever D�bitos de Im�veis P�blicos Autom�tico
	 * 
	 * @author Hugo Leonardo
	 * @date 19/10/2010
	 * 
	 */
	public Collection obterDadosPrescricaoDebitosAutomaticos() throws ControladorException;
	
	/**
	 * 
	 * [UC1083] Prescrever D�bitos de Im�veis P�blicos Autom�tico
	 * 
	 * @author Hugo Leonardo
	 * @date 19/10/2010
	 * 
	 */
	public void prescreverDebitosImoveisPublicosAutomatico(Integer idFuncionalidadeIniciada, 
			Integer anoMesReferencia, Integer anoMesPrescricao, Integer usuario, String idsEsferaPoder) 
		throws ControladorException;
	
	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author S�vio Luiz, Ivan Sergio
	 * @date 24/05/2006, 01/12/2010
	 * @alteracoes: 01/12/2010 - RM247
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemConta(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro,
			int tipoConta,
			Collection<NacionalFeriado> colecaoNacionalFeriado) throws ControladorException;
	
    /**
     * 
     * [UC0629] Consultar Arquivo Texto para Leitura
     * 
     * [FS0015], [FS0016]
     * 
     * @autor Bruno Barros
     * @date 18/11/2010
     * @param idsArquivos
     */
    public void gerarArquivoImoveisNaoEnviados( String[] idsArquivos ) 
        throws ControladorException;
     
     
    public void gerarArquivoTextoParaFaturamento(
            Rota rota, 
            Integer anoMesFaturamento, 
            FaturamentoGrupo faturamentoGrupo,
            Date dataComando,
            Boolean regerar) throws ControladorException;
    
	/**
	 * [RM-4643 (COMPESA)] 
	 * Verificamos se o im�vel sofreu altera��es 
	 * depois de ter sido mandado para o GSAN a primeira vez
	 * 
	 * @author Bruno Barros
	 * @date 14/12/2010
	 * 
	 * @param anoMes
	 * @param idImovel
	 * @param tipoMedicao
	 * @param leitura
	 * @param idAnormalidade
	 * @return
	 * @throws ControladorException
	 */
	public boolean reprocessarImovelImpressaoSimultanea(
			Integer anoMes,
			Integer idImovel,
			Short tipoMedicao,
			Integer leitura,
			Integer idAnormalidade,
			Short icImpresso
			) throws ControladorException;

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * [UC0348] Emitir Contas
	 * @author Vivianne Sousa
	 * @date 22/11/2010
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author S�vio Luiz, Ivan Sergio
	 * @date 24/05/2006, 01/12/2010
	 * @alteracoes: 01/12/2010 - RM247
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(Integer idImovel,
			Integer anoMesReferencia) throws ControladorException ;

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Mariana Victor
	 * @date 22/10/2010
	 *
	 * @param idRota
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarArquivoTextoRoteiroEmpresa(Integer idRota, Integer anoMesReferencia)
			throws ControladorException ;
	
	/**
	 * 
	 * Retifica��o de um conjunto de contas que foram pagas e que o pagamento n�o estava o d�bito e/ou cr�dito (Conta paga via Impress�o Simult�nea) 
	 *
	 * @author S�vio Luiz
	 * @date 27/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasPagasSemDebitoCreditoPago(Integer amreferencia,Integer idGrupo) throws ControladorException;
	
	/**
	 * 
	 * Retifica��o de um conjunto de contas que foram pagas e que o pagamento n�o estava o d�bito e/ou cr�dito (Conta paga via Impress�o Simult�nea) 
	 *
	 * @author S�vio Luiz
	 * @date 27/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection retificarContasPagasSemDebitoCredito(Collection colecaoContasRetificar,Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * Inserir D�bitos para as contas impressas via Impress�o Simult�nea de Contas que sairam com o valor da conta errada (Alguns grupos com tarifa proporcional
	 *  que n�o estava levando em considera��o a quantidade de economias)
	 *
	 * @author S�vio Luiz
	 * @date 12/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasComValorFaixasErradas(Integer amreferencia) throws ControladorException;
	
	/**
	 * Inserir D�bitos para as contas impressas via Impress�o Simult�nea de Contas que sairam com o valor da conta errada (Alguns grupos com tarifa proporcional
	 *  que n�o estava levando em considera��o a quantidade de economias)
	 *
	 * @author S�vio Luiz
	 * @date 12/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void inserirDebitosContasComValorFaixasErradas(Integer amreferencia, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC0651] Inserir Comando de Negativa��o
	 * [FS0031] � Verificar exist�ncia de conta em nome do cliente
	 * 
	 * Pesquisa os relacionamentos entre cliente e conta.
	 * 
	 * @author Vivianne Sousa
	 * @date 29/12/2010
	 * 
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarSeExisteClienteConta(Integer idCliente,Collection colecaoContasIds)
		throws ControladorException;

	/**
	 * [UC0204] Consultar Conta
	 * 
	 * Pesquisa o consumo faturado do im�vel
	 * 
	 * @author Mariana Victor
	 * @date 06/01/2011
	 * 
	 * @param [UC0204] Consultar Conta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] consultarConsumoCadastrado(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0473] Consultar Dados Complementares do Im�vel
	 * 
	 * Pesquisa as matr�culas associadas � mesma tarifa de consumo do im�vel.
	 * 
	 * @author Mariana Victor
	 * @date 06/01/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> consultarMatriculasAssociadas(Integer idConsumoTarifa, Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0048] � Verificar exist�ncia da conta.
	 * 
	 * @author Mariana Victor
	 * @date 27/01/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaAnoMesImovel(Integer idImovel, int anoMesReferencia)
		throws ControladorException;
	
	/**
	 * [UC0146] Manter Conta
	 * [FS0037]-Verificar ocorr�ncias mesmo motivo no ano
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisaQtdeContaEContaHistoricoRetificadaMotivo(
			Integer idMotivo,Integer idImovel)throws ControladorException ;
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaTabelaColunaContaMotivoRetificacaoColuna(
			Integer idMotivo)throws ControladorException;
	
	/**
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * Metodo respons�vel pela identifica��o dos grandes consumidores
	 * e altera��o seus respectivos perfis.
	 * 
	 * @author Mariana Victor
	 * @date 07/02/2011
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void identificarGrandesConsumidores(
			Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0352] Emitir Contas e Cartas
	 * 
	 * [SB0048] � Obter Nome do Cliente
	 * @author Mariana Victor
	 * @date 11/03/2011
	 * 
	 * */
	public String obterNomeCliente(Integer idConta) throws ControladorException;

	/**
	 * [UC1129] Gerar Relat�rio Devolu��o dos Pagamentos em Duplicidade
	 * 
	 * @author Hugo Leonardo
	 * @date 10/03/2011
	 * 
	 * @param FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper
	 * 
	 * @return Collection<RelatorioDevolucaoPagamentosDuplicidadeHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioDevolucaoPagamentosDuplicidade(
			FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper helper)throws ControladorException;
	

	/**
	 * [UC0150] - Retificar Conta Author: Raphael Rossiter Data: 13/01/2006
	 * 
	 * Inseri na tabela CREDITO_REALIZADO os dados referentes aos cr�ditos da
	 * conta
	 * 
	 * @param conta
	 * @param colecaoCreditoRealizado
	 * @param imovel
	 * @param colecaoCategoria
	 * @throws ControladorException
	 */
	public void inserirCreditoRealizado(Conta conta,
			Collection colecaoCreditoRealizado, Imovel imovel,
			Collection colecaoCategoria) throws ControladorException ;
	
	/**
	 * [UC0145] - Inserir Conta Author: Raphael Rossiter Data: 13/01/2006
	 * 
	 * Inclui a quantidade de economias por categoria do cr�dito realizado na
	 * tabela CREDITO_REALIZADO_CATEGORIA
	 * 
	 * @param creditoRealizado
	 * @param colecaoCategoria
	 * @throws ControladorException
	 */
	public void inserirCreditoRealizadoCategoria(CreditoRealizado creditoRealizado,
			Collection colecaoCategoriaOuSubcategoria)
			throws ControladorException;

	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado 
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @exception ErroRepositorioException
	 */
	public Integer pesquisarQtdeContaNaoPaga(Collection idContas) throws ControladorException;
	
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensa��o 
	 *
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 *
	 * @param identificacaoCodigoBarras
	 * @param valorPagamento
	 * @return Conta
	 * @throws ControladorException
	 */
	public Conta pesquisarContaTipoBoleto(Integer identificacaoCodigoBarras, BigDecimal valorPagamento) throws ControladorException;
	
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensa��o 
	 *
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 *
	 * @param identificacaoCodigoBarras
	 * @param valorPagamento
	 * @return Conta
	 * @throws ControladorException
	 */
	public ContaHistorico pesquisarContaHistoricoTipoBoleto(Integer identificacaoCodigoBarras, BigDecimal valorPagamento) throws ControladorException;
	
	/**
	 * [UC0866] Gerar Comando Contas em Cobran�a por Empresa
	 * 
	 * Pesquisa a quantidade de contas, agrupando por im�vel
	 * 
	 * @author: Mariana Victor
	 * @date: 07/04/2011
	 */
	public Collection<Object[]> pesquisarQuantidadeContasAgrupandoPorImovel(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper) throws ControladorException;
	
	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 *
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa( Integer IdRota, Integer anoMes, Integer idLeituristaNovo ) throws ControladorException;
	
	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 *
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa( Collection<Integer> idsImovel, Integer anoMes, Integer idLeituristaNovo ) throws ControladorException;
	
	/**
	 * [UC0120] - Calcular Valores de �gua e/ou Esgoto 
	 *
	 * @author Raphael Rossiter
	 * @date 11/04/2011
	 *
	 * @param consumoTarifa
	 * @param anoMesReferencia
	 * @param dataLeituraAnterior
	 * @param dataLeituraAtual
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection obterConsumoTarifaVigencia(ConsumoTarifa consumoTarifa, Integer anoMesReferencia, Date dataLeituraAnterior,
			Date dataLeituraAtual) throws ControladorException;
	
	/**
	 * [UC1166]  Gerar  txt para impress�o de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGrupoFaturamentoGrupoNaoFaturados(
			Integer anoMesReferenciaFaturamento)throws ControladorException;
	
	/**
	 * [UC1166]  Gerar  txt para impress�o de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarTxtImpressaoContasBraille(int idFuncionalidadeIniciada) throws ControladorException ;
	
	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Pesquisa a quantidade de contas, agrupando por im�vel
	 * 
	 * @author: Mariana Victor
	 * @date: 12/05/2011
	 */
	public Collection<Object[]> pesquisarQuantidadeContasComandoAgrupandoPorImovel(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper) throws ControladorException;

	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Pesquisa a quantidade de contas
	 * 
	 * @author: Mariana Victor
	 * @date: 12/05/2011
	 */
	public Object[] pesquisarQuantidadeContasComando(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper) throws ControladorException;
	
	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Pesquisa as ordens de servi�o selecionadas
	 * 
	 * @author: Mariana Victor
	 * @date: 19/05/2011
	 */
	public Collection<Integer[]> pesquisarOSComandoSelecionado(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper) throws ControladorException;
	
	/**
	 * [UC1173] Informar Consumo por Par�metros
	 * 
	 * [FS0005] � Validar m�s ano maior ou igual 
	 * 
	 * @author Mariana Victor
	 * @date 20/05/2011
	 * 
	 * @param anoMesReferenciaInformado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(
			int anoMesReferenciaInformado)throws ControladorException;

	/**
	 * 
	 * [UC1173] Informar Consumo por Par�metros
	 * 
	 * [SB0001] - Gerar Dados do consumo por par�metro.
	 * 
	 * @author Mariana Victor
	 * @date 20/05/2011
	 * 
	 * @param anoMesReferenciaInformado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer informarConsumoMinimoParametro(
			Collection colecaoConsumoMinimoParametro,
			Collection colecaoConsumoMinimoParametroBase, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 01/06/2011
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return
	 * @throws ControladorException
	 */
	public boolean pesquisarContaDoImovelDiferentePreFaturada(Integer idImovel, Integer anoMesReferencia) throws ControladorException ;
	
	/**
	 * [UC0713] Emitir Ordem de Servi�o Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 */
	public Integer pesquisarFaturamentoGrupoImovel(Integer idImovel)throws ControladorException;
	
	/**
	 * 
	 * @author Gustavo Amaral
	 * @date 20/07/2011
	 */
	public void atualizarIndicadorContaHistorico(Integer idFaturaItem)throws ControladorException;
	
	/**
	 * Para testes
	 * @author Rafael Pinto
	 * @date 30/07/2011
	 * 
	 */
	public void deletarResumoFaturamento(Integer anoMes)throws ControladorException ;
/**
	 * [UC1194] Consultar Estrutura Tarif�ria Loja Virtual
	 * [SB0001]  Pesquisar Tarifa Social ou Tarifa M�nima
	 * 
	 * M�todo que vai retornar um Helper que possui o consumo
	 * da tarifa m�nima e da tarifa social e seus respectivos
	 * valores.
	 * 
	 * @author Diogo Peixoto
	 * @since 14/07/2011
	 * 
	 * @param idCategoria
	 * 
	 * @return Collection<ConsultarEstruturaTarifariaPortalHelper>
	 */
	public ArrayList<ConsultarEstruturaTarifariaPortalHelper> pesquisarEstruturaTarifaria(Integer idCategoria) throws ControladorException;
	
	/**
	 * [UC1194] Consultar Estrutura Tarif�ria Loja Virtual 
	 * 
	 * M�todo que vai retornar um Helper que possui o consumo n�o medido
	 * de chafariz p�blico.
	 * 
	 * @author Diogo Peixoto
	 * @since 06/09/2011
	 * 
	 * @return ConsultarEstruturaTarifariaPortalHelper
	 */
	public ConsultarEstruturaTarifariaPortalHelper pesquisarEstruturaTarifariaChafarizPublico() throws ControladorException;
	
	/**
	 * [UC1194] Consultar Estrutura Tarif�ria Loja Virtual
	 * [SB0001]  Pesquisar Tarifa Social ou Tarifa M�nima
	 * 
	 * M�todo que vai retornar um Helper que possui o consumo
	 * da tarifa m�nima e da tarifa social e seus respectivos
	 * valores.
	 * 
	 * @author Diogo Peixoto
	 * @since 14/07/2011
	 * 
	 * @param idCategoria
	 * 
	 * @return Collection<ConsultarEstruturaTarifariaPortalHelper>
	 */
	public ArrayList<ConsultarEstruturaTarifariaPortalHelper> pesquisarEstruturaTarifariaAguaBruta(Integer idCategoria)throws ControladorException;
	
	/**
	 * [UC0146] - Manter Conta
	 * 
	 * Metodo respons�vel por percorrer a lista de contas e retornar apenas 
	 *  as que n�o est�o ligadas a algum Contrato de Parcelamento por Cliente 
	 * 
	 * @author Mariana Victor
	 * @date 14/07/2011
	 * 
	 * @param colecaoContas
	 * 
	 * @return Collection<Conta>
	 */
	public Collection<Conta> obterColecaoSemContasEmContratoParcelamento(Collection colecaoContas) throws ControladorException;
	
	/**
	 * [UC0482] Emitir 2� Via de Conta
	 * 
	 * Metodo respons�vel por percorrer a lista de contas e retornar apenas 
	 *  as que n�o est�o ligadas a algum Contrato de Parcelamento por Cliente 
	 * 
	 * @author Mariana Victor
	 * @date 14/07/2011
	 * 
	 * @param colecaoContas
	 * 
	 * @return Collection<Integer>
	 */
	public Collection<Integer> obterColecaoSemContasEmContratoParcelamentoIDs(Collection<Integer> colecaoIdsContas) 
		throws ControladorException;
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * [SB0008] Retificar Conjunto Conta
	 * 
	 * Metodo respons�vel por percorrer a lista de contas e retornar apenas 
	 *  as que n�o est�o ligadas a algum Contrato de Parcelamento por Cliente 
	 * 
	 * @author Mariana Victor
	 * @date 15/07/2011
	 * 
	 * @param colecaoContas
	 * 
	 * @return Collection<Integer>
	 */
	public Collection<Object[]> obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContas(Collection<Object[]> colecaoContasManutencao) 
		throws ControladorException;
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * [SB0008] Retificar Conjunto Conta
	 * 
	 * Metodo respons�vel por percorrer a lista de contas e retornar apenas 
	 *  as que n�o est�o ligadas a algum Contrato de Parcelamento por Cliente 
	 * 
	 * @author Mariana Victor
	 * @date 15/07/2011
	 * 
	 * @param colecaoContas
	 * 
	 * @return Collection<Integer>
	 */
	public Collection<Object[]> obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContasIds(Collection<Object[]> colecaoContasManutencao) 
		throws ControladorException;

	/**
	 * [UC1187] Colocar D�bito a Cobrar em Revis�o
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param colecaoDebitosACobrar
	 * @param contaMotivoRevisao
	 * @throws ControladorException
	 */
	public void colocarRevisaoDebitoACobrar(Collection<DebitoACobrar> colecaoDebitosACobrar,
			ContaMotivoRevisao contaMotivoRevisao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC1188] Retirar D�bito A Cobrar de Revis�o
	 * 
	 * Este caso de uso permite retirar de revis�o uma lista de d�bito a cobrar recebida.
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param colecaoDebitosACobrar
	 * @param identificadores
	 * @throws ControladorException
	 */
	public void retirarRevisaoDebitoACobrar(Collection<Conta> colecaoDebitosACobrar,
			Usuario usuarioLogado) throws ControladorException;

	/**
     * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param idDebitoACobrar
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public DebitoACobrar obterDebitoACobrar(Integer idDebitoACobrar) throws ControladorException;
	/**
	 * UC1198 - Relat�rio das Multas de Autos de Infra��o Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	
	public Collection obterColecaoGrupoFaturamento() throws ControladorException;
	
	/**
	 * UC1198 - Relat�rio das Multas de Autos de Infra��o Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ControladorException
	 */
	
	public Collection pesquisarDadosRelatorioAutoInfracaoPendentes(Integer grupo, Integer funcionario) throws ControladorException;

	/**
	 * [UC1214] Informar Acerto Documentos N�o Aceitos
	 * 
	 * [SB0002] ? Selecionar D�bitos Pagos
	 * 
	 * @author Mariana Victor
	 * @date 23/08/2011
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return
	 * @throws ControladorException
	 */
	public Conta pesquisarContaOuContaHistoricoDigitada(String idImovel, String referenciaConta)
			throws ControladorException;
	
	/**
	 * [UC1216] Suspender Leitura para Im�vel com Hidr�metro Retirado
	 * 
	 * @author Vivianne Sousa
	 * @date 23/08/2011
	 */
	public void suspenderLeituraParaImovelComHidrometroRetirado(Integer idFuncionalidadeIniciada,
			Integer referenciaFaturamento,Integer grupofaturamento,Integer idRota) throws ControladorException;
	
	/**
	 * [UC1218] Suspender Leitura para Im�vel com Consumo Real n�o Superior a 10m3
	 * 
	 * @author Vivianne Sousa
	 * @date 26/08/2011
	 */
	public void suspenderLeituraParaImovelComConsumoRealNaoSuperiorA10(Integer idFuncionalidadeIniciada,
			Integer referenciaFaturamento,Integer grupofaturamento,Integer idRota) throws ControladorException;
	
	/**
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Raphael Rossiter
	 * @date 20/07/2011
	 * 
	 * @param conta
	 * @param consumoAguaMovimentoCelular
	 * @param consumoAguaGSAN
	 * @param consumoEsgotoMovimentoCelular
	 * @param consumoEsgotoGSAN
	 * @throws ControladorException
	 */
	public void atualizarConsumoMovimentoCelular(Conta conta, Integer consumoAguaMovimentoCelular, Integer consumoAguaGSAN,
			Integer consumoEsgotoMovimentoCelular, Integer consumoEsgotoGSAN) throws ControladorException ;
	
	/**
     * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2011
	 * 
	 * @param idImovel
	 * 
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterPercentualColetaEsgotoImovel(Integer idImovel)
			throws ControladorException;
	
	/**
	 * @TODO - COSANPA
	 * 
	 * M�todo para obter im�veis com conta PF
	 * 
	 * @author Felipe Santos
	 * @date 25/05/2011
	 */
	
	public Collection obterImoveisComContaPF(Integer anoMesReferencia,
			Rota rota) throws ControladorException;

	/**
	 * Recupera a data realizada do faturamento atividade cronograma
	 * 
	 * @author S�vio Luiz
	 * @date 28/08/2007
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarFaturamentoAtividadeCronogramaDataRealizada(
			Integer faturamentoGrupoId, Integer faturamentoAtividadeId,
			Integer anoMesReferencia) throws ControladorException;
	
	/*
	 * TODO : COSANPA
	 * M�todo criado para obter os im�veis de uma rota que possuem conta
	 */
	
	 /** @author Pamela Gatinho  
	 * @date 21/06/2011
	 * 
	 * @return Collection Dados dos im�veis com conta
	 * @param anoMesReferencia
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public Collection obterImoveisComConta(Integer anoMesReferencia,
			Rota rota) throws ControladorException;
	
	/**
	 * TODO : COSANPA
	 * @author Pamela Gatinho  
	 * @date 04/08/2011
	 * 
	 * Metodo que obtem a conta do im�vel, so n�o retorna
	 * a conta que estiver com a situacao
	 * CANCELADA POR RETIFICACAO
	 * 
	 * @return Conta
	 * @param anoMesReferencia
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public Conta obterContaImovel(Integer idImovel, Integer anoMesReferencia) throws ControladorException;
	
	/**
	 * TODO - COSANPA
	 * 
	 * M�todo para obter todos os im�veis que j� foram processados
	 * na transmiss�o do arquivo de retorno do IS
	 * 
	 * @author Felipe Santos
	 * @date 22/08/2011
	 * 
	 * @param idRota
	 * @return List<Integer> retorno
	 * @throws ControladorException
	 */
	public List<Integer> obterImoveisMovimentoContaPF(Integer idRota, Integer anoMesFaturamento)
			throws ControladorException;
	
	
	/**
	 * TODO - COSANPA
	 * 
	 * M�todo para obter todos os im�veis que faltam ser transmitidos
	 * na transmiss�o do arquivo de retorno do IS
	 * 
	 * @author Felipe Santos
	 * @date 24/08/2011
	 * 
	 * @param idRota
	 * @return List<Integer> retorno
	 * @throws ControladorException
	 */
	public List<Integer> obterImoveisFaltandoTransmitir(Integer idRota, Integer anoMesFaturamento)
			throws ControladorException;
	
	public Collection<RelatorioContasRetidasHelper> pesquisarDadosRelatorioContasRetidas(
			int anoMesReferencia, Integer idFaturamentoGrupo) throws ControladorException;
	
	public Collection pesquisarDadosRelatorioMedicaoFaturamento(
			int anoMesReferencia, Integer idFaturamentoGrupo, Integer idEmpresa) throws ControladorException;
	
	public void atualizarConta(Conta conta) throws ControladorException;
	
	public BigDecimal verificarPercentualEsgotoAlternativo(Imovel imovel,
			Integer consumoFaturadoEsgoto) throws ControladorException;
	
	public BigDecimal[] calcularValorRateioImovel(Imovel imovel,  FaturamentoGrupo faturamentoGrupo) 
		throws ControladorException, ErroRepositorioException;
	
	public StringBuilder obterNomeArquivoRetorno(ArquivoTextoRetornoIS arquivoRetorno);
	
	public MovimentoContaPrefaturada obterMovimentoImovel(Integer idImovel, Integer anoMesReferencia) throws ControladorException;
	
	public ExtratoQuitacao obterExtratoQuitacaoImovel(Integer idImovel, Integer anoReferencia) throws ControladorException;
	
	public String obterMsgQuitacaoDebitos(Imovel imovel, Integer anoMesReferencia)
	throws ControladorException;

	public long obterDiferencaDiasCronogramas(Integer anoMesAtual, Rota rota, Integer idFaturamentoAtividade)
		throws ControladorException;
	
	public Integer inserirGuiaPagamentoCodigoBarrasPorCliente(
			GuiaPagamento guiaPagamento, Integer idDebitoTipo, Integer idLocalidade)
			throws ControladorException;
	
	public DebitoACobrar gerarDebitoACobrar(Integer anoMesReferenciaArrecadacao, Integer anoMesReferenciaFaturamento, Imovel imovel, 
			Short numeroPrestacaoDebito, Short numeroPrestacaoCobradas, Integer anoMesReferenciaDebito, BigDecimal valorDebito, 
			DebitoTipo debitoTipo, Usuario usuario) throws ControladorException;
	
	public Map<Integer, Conta> incluirContasParaRefaturarPagamentos(Collection<Pagamento> pagamentos, Usuario usuarioLogado) throws ControladorException, ErroRepositorioException;
	
	@SuppressWarnings("unchecked")
	public Collection<Integer> getListaIdContas(Collection<Pagamento> pagamentos);
		
	public Collection<ContaHistorico> pesquisarContaOuContaHistorico(Collection<Pagamento> pagamentos) throws ControladorException;

	public void atualizarVecimentoFaturaClienteResponsavel(Date dataVencimento, String anoMesReferencia) throws ControladorException;
	
	public Integer countFaturasClienteResponsaveis(String anoMesReferencia) throws ControladorException;
	
	public Conta incluirDebitoContaRetificadaPagamentosDiferenca2Reais(Integer idConta, DebitoACobrar debito) throws Exception;
	
	public Conta incluirCreditoContaRetificadaPagamentosDiferenca2Reais(Integer idConta, CreditoARealizar credito) throws Exception;
	
	public Collection pesquisarClienteContaECliente(Integer idConta, String cnpjEmpresa) throws ControladorException;

	public void faturarImovelSeletivo(ImovelFaturamentoSeletivo imovelFaturamentoSeletivo) throws ControladorException;
}
