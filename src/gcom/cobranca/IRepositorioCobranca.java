package gcom.cobranca;

import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.batch.auxiliarbatch.CobrancaDocumentoControleGeracao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.DadosPesquisaCobrancaDocumentoHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarRelacaoParcelamentoHelper;
import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.cobranca.bean.PesquisarQtdeRotasSemCriteriosParaAcoesCobranca;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoGerarOSHelper;
import gcom.gui.relatorio.cobranca.FiltroRelatorioDocumentosAReceberHelper;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.relatorio.cobranca.RelatorioAcompanhamentoAcoesCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioAnalisePerdasCreditosBean;
import gcom.relatorio.cobranca.RelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.parcelamento.RelacaoParcelamentoRelatorioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Interface para o reposit�rio de cobranca
 * 
 * @author Rafael Santos
 * @since 02/01/2006
 */
public interface IRepositorioCobranca {

	/**
	 * Faz parte de [UC0178] Religar Automaticamente Im�vel Cortado Author:
	 * Rafael Santos Data: 02/01/2006 Pesquisa os imoveis cortados h� 60 dias ou
	 * mais a data do �ltimo dia do m�s de faturamento
	 * 
	 * @return Colec��o de Matriculas
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarImoveisCortados(String situacaoEsgotoLigado,
			String situacaoAguaCortado, Date anoMesReferenciaFaturamento)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0178] Religar Automaticamente Im�vel Cortado Author:
	 * Rafael Santos Data: 02/01/2006 Caso o imovel possua hidrometro na liga��o
	 * de �gua e o tipo do ultimo consumo faturado tenha sido real
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param anoMesFaturamento
	 *            Ano Mes Faturamento
	 * @param consumoTipoReal
	 *            Tipo de Consumo Real
	 * @param ligacaoTipoLigacaoAgua
	 *            Tipo de Liga��o Agua
	 * @return Consumo Historico do Imovel
	 * @throws ErroRepositorioException
	 *             Erro no Repositorio
	 */
	public String pesquisarImoveisHidrometroAguaConsumoFaturadoReal(String id,
			String anoMesFaturamento, String consumoTipoReal,
			String ligacaoTipoLigacaoAgua) throws ErroRepositorioException;

	/**
	 * [UC0178] Religar Automaticamente Im�vel Cortado Auhtor: Rafael Santos
	 * Data: 03/01/2006
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param situacaoAguaLigado
	 *            Situa��o Agua
	 * @param dataReligacaoAgua
	 *            Data Religacao Agua
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void religarImovelCortado(String id, String situacaoAguaLigado,
			Date dataReligacaoAgua) throws ErroRepositorioException;

	/**
	 * [UC0067] Obter D�bito do Im�vel ou Cliente Obtem os d�bitos de um im�vel
	 * Author: Rafael Santos Data: 03/01/2006
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param contaSituacaoNormal
	 *            Situa��o Normal de Conta
	 * @param contaSituacaoRetificada
	 *            Situa��o Retificada de Conta
	 * @param contaSituacaoIncluida
	 *            Situa��o Inclu�da de Conta
	 * @param anoMesInicialReferenciaDebito
	 *            Ano Mes Inicial Referencia Debito
	 * @param anoMesFinalReferenciaDebito
	 *            Ano Mes Final Referencia Debito
	 * @param anoMesInicialVecimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @param anoMesFinalVencimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @return Cole��o de Contas do Imovel
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImoveis(Collection idsImoveis,
			int indicadorPagamento, int indicadorConta,
			String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada,
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorDividaAtiva)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Im�vel ou Cliente Obtem os d�bitos
	 * de um cliente Author: Rafael Santos Data: 05/01/2006
	 * 
	 * @param idsContas
	 *            Cole��o de Ids das Contas
	 * @param contaSituacaoNormal
	 *            Situa��o Normal de Conta
	 * @param contaSituacaoRetificada
	 *            Situa��o Retificada de Conta
	 * @param contaSituacaoIncluida
	 *            Situa��o Inclu�da de Conta
	 * @param anoMesInicialReferenciaDebito
	 *            Ano Mes Inicial Referencia Debito
	 * @param anoMesFinalReferenciaDebito
	 *            Ano Mes Final Referencia Debito
	 * @param anoMesInicialVecimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @param anoMesFinalVencimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @return Cole��o de Contas do Imovel
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasCliente(Integer idCliente,
			Short relacaoTipo, int indicadorPagamento, int indicadorConta,
			String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada,
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito, 
			int indicadorDividaAtiva)
			throws ErroRepositorioException;
	
	/**
	 * [UC0067] Obter D�bito do Im�vel ou Cliente Obtem os d�bitos de um im�vel
	 * Author: Rafael Santos Data: 03/01/2006
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param contaSituacaoNormal
	 *            Situa��o Normal de Conta
	 * @param contaSituacaoRetificada
	 *            Situa��o Retificada de Conta
	 * @param contaSituacaoIncluida
	 *            Situa��o Inclu�da de Conta
	 * @param anoMesInicialReferenciaDebito
	 *            Ano Mes Inicial Referencia Debito
	 * @param anoMesFinalReferenciaDebito
	 *            Ano Mes Final Referencia Debito
	 * @param anoMesInicialVecimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @param anoMesFinalVencimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @return Cole��o de Contas do Imovel
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImovel(Integer idImovel,
				int indicadorPagamento, int indicadorConta,
				String contaSituacaoNormal, String contaSituacaoRetificada,
				String contaSituacaoIncluida, String contaSituacaoParcelada,
				String anoMesInicialReferenciaDebito,
				String anoMesFinalReferenciaDebito,
				Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito, int indicadorDividaAtiva)
				throws ErroRepositorioException;
	
	public int pesquisarQuantidadeContasVencidasPorImovel(Integer idImovel, int indicadorPagamento, int indicadorConta, int indicadorDividaAtiva) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Im�vel ou Cliente Obtem o Valor
	 * Total dos Pagamentos da Conta Author: Rafael Santos Data: 05/01/2006
	 * 
	 * @param idConta
	 *            Id Conta
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarValorTotalPagamentoMenorDataPagamento(
			String idConta) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Im�vel ou Cliente Obtem o Valor
	 * Total dos Pagamentos da Guia de Pagamento Author: Rafael Santos Data:
	 * 07/01/2006
	 * 
	 * @param idGuiaPagamento
	 *            Id Guia Pagamento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarValorTotalGuiaPagamentoMenorDataGuiaPagamento(
			String idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006
	 * 
	 * @param idImovel
	 *            Matricula do Imovel
	 * @param situacaoNormal
	 *            situacao de debito credito
	 * @return Cole��o de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarImovel(String idImovel,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006 Pesquisa os ID dos imoveis dos cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Rela��o Tipo Cliente Imovel
	 * @return Cole��o de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIDImoveisClienteImovel(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 07/01/2006 Pesquisa os ID dos clientes contas
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Rela��o Tipo Cliente Imovel
	 * @return ID dos Imvoeis Cliente Conta
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIDImoveisClienteConta(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 06/01/2006 Colec��o de Debitos a Cobrar de Cliente
	 * 
	 * @param colecaoIdImoveis
	 *            Cole��o de ID dos Imoveis
	 * @param situacaoNormal
	 *            Situa��o Normal
	 * @return Cole��o de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarCliente(Collection idsImoveis,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006 Colec��o de Creditos a Realizar de Cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param situacaoNormal
	 *            Situa��o Normal
	 * @return Cole��o de Creditos A Realizar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditosARealizarCliente(Collection idsImoveis,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006
	 * 
	 * @param idImovel
	 *            Matricula do Imovel
	 * @return Cole��o de Creditos A Realizar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditosARealizarImovel(String idImovel,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 07/01/2006 Colec��o de Guias de Pagamento do Cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @param situacaoNormal
	 *            Situa��o Normal
	 * @param clienteRelacaoTipo
	 *            Rela��o Cliente Tipo
	 * @return Cole��o de Guias de Pagamento do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoCliente(Integer idCliente, int indicadorPagamento,
			String situacaoNormal, Short clienteRelacaoTipo,
			Date dataVencimentoInicial, Date dataVencimentoFinal)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0216] Calcular Acr�scimo por Impontualidade Rafael Santos
	 * Data: 09/01/2006 Dados do Indices Acrescimo Impontualidade
	 * 
	 * @param anoMesReferenciaDebito
	 *            Ano M�s de Referencia de D�bito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarIndiceAcrescimoImpontualidade(
			int anoMesReferenciaDebito) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0216] Calcular Acr�scimo por Impontualidade Rafael Santos
	 * Data: 09/01/2006 Pesquisa os dados do Indices Acrescimo Impontualidade
	 * menor ao ano mes referencia
	 * 
	 * @param anoMesReferenciaDebito
	 *            Ano M�s de Referencia de D�bito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarMenorIndiceAcrescimoImpontualidade(
			int anoMesReferenciaDebito) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 07/01/2006
	 * 
	 * @param idImovel
	 *            Matricula do Imovel
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @param situacaoNormal
	 *            Situa��o Normal
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @return Cole��o de Guias de Pagamentos
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoImovel(Integer idImovel, int indicadorPagamento,
			String situacaoNormal, Date dataVencimentoInicial,
			Date dataVencimentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir D�bito Autom�tico [FS0004] Verificar Data de Op��o
	 * posterior j� informada
	 * 
	 * @author Roberta Costa
	 * @created 04/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDataOpcao(String matriculaImovel, Date dataOpcao,
			String identificadorCliente, String codigoAgencia)
			throws ErroRepositorioException;

	/**
	 * [UC0201] Excluir D�bito Autom�tico [FS0004] Verificar Data de Op��o
	 * posterior j� informada
	 * 
	 * @author Roberta Costa
	 * @created 05/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDataOpcaoExclusao(String matriculaImovel,
			Date dataOpcao, String identificadorCliente)
			throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir D�bito Autom�tico Verificar se o Im�vel j� � D�bito
	 * Autom�tico
	 * 
	 * @author Roberta Costa
	 * @created 05/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDebitoAutomatico(String matriculaImovel)
			throws ErroRepositorioException;

	public void atualizarDataExclusao(String matriculaImovel, Integer idAgencia) throws ErroRepositorioException;
	
	public void atualizarDataExclusao(String matriculaImovel) throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir D�bito Autom�tico
	 */
	public void inserirDebitoAutomatico(DebitoAutomatico debitoAutomatic)
			throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir D�bito Autom�tico Atualiza o indicador de d�bio
	 * autom�tico em Im�vel
	 */
	public void atualizarIndicadorDebitoAutomatico(String matriculaImovel,
			Integer indicadorDebito) throws ErroRepositorioException;

	/**
	 * [UC0201] Remover D�bito Autom�tico Verificar se o Im�vel j� � D�bito
	 * Autom�tico para o mesmo Banco e Ag�ncia
	 * 
	 * @author Roberta Costa
	 * @created 09/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDebitoAutomaticoBancoAgencia(String codigoBanco,
			String codigoAgencia) throws ErroRepositorioException;

	/**
	 * [UC0246] Executar Atividade de A��o de Cobran�a Pesquisa uma cole��o de
	 * CobrancaAcaoAtividadeCronograma
	 * 
	 * @author Pedro Alexandre
	 * @created 01/02/2006
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronograma()
			throws ErroRepositorioException;

	/**
	 * [UC0246] Executar Atividade de A��o de Cobran�a Pesquisa uma cole��o de
	 * CobrancaAcaoAtividadeComando
	 * 
	 * @author Pedro Alexandre
	 * @created 01/02/2006
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeComando()
			throws ErroRepositorioException;

	/**
	 * Consultar Dados do Cliente Imovel Vinculado Auhtor: Rafael Santos Data:
	 * 23/01/2006
	 * 
	 * @param inscricaoImovel
	 *            Inscri��o do Imovel
	 * @return Dados do Imovel Vinculado
	 * @throws ControladorException
	 */
	public Object[] consultarDadosClienteImovelUsuario(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * Consultar Matriculas dos Imoveis Vinculados do Imovel condominio Auhtor:
	 * Rafael Santos Data: 23/01/2006 [UC0179] Consultar Historico Medi��o
	 * Indiviualizada
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarConsumoHistoricoImoveisVinculados(
			ConsumoHistorico consumoHistorico) throws ErroRepositorioException;

	/**
	 * Consultar Consumo Tipo do Consumo Historico Auhtor: Rafael Santos Data:
	 * 23/01/2006
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return Dados do Consumo Tipo
	 * @throws ControladorException
	 */
	public Object[] consultarDadosConsumoTipoConsumoHistorico(
			ConsumoHistorico consumoHistorico) throws ErroRepositorioException;

	/**
	 * Consultar Consumo Historico da Medicao Individualizada Auhtor: Rafael
	 * Santos Data: 23/01/2006 [UC0179] Consultar Historico Medi��o
	 * Indiviualizada
	 * 
	 * @param imovel
	 *            Imovel
	 * @param ligcaoTipo
	 *            Tipo de Ligaca��o
	 * @param anoMesFaturamento
	 *            Ano Mes Faturamento
	 * @return
	 * @throws ControladorException
	 */
	public Object[] obterConsumoHistoricoMedicaoIndividualizada(Imovel imovel,
			LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * conta
	 * 
	 * @author Fernanda Paiva
	 * @created 14/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAnterior
	 *            DebitoCreditoSituacaoAtual idConta
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoConta(String codigoConta, int situacaoAtual,
			int anoMesReferenciaContabil) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * guia pagamento
	 * 
	 * @author Fernanda Paiva
	 * @created 15/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idGuiaPagamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoGuiaPagamento(String codigoGuiaPagamento,
			int situacaoAtualGuia, int anoMesReferenciaContabil)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o parcelamento
	 * 
	 * @author Fernanda Paiva
	 * @created 15/02/2006
	 * 
	 * @param idParcelamento
	 *            motivo parcelamentoSituacao
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarParcelamento(Integer codigoParcelamento,
			Integer parcelamentoSituacao, String motivo, Integer usuarioId)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * debitoacobrar
	 * 
	 * @author Fernanda Paiva
	 * @created 16/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idDebitoACobrar
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoDebitoACobrar(String codigoDebitoACobrar,
			int situacaoAtualDebito, int anoMesReferenciaContabil)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * creditoarealizar
	 * 
	 * @author Fernanda Paiva
	 * @created 16/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idCreditoARealizar
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoCreditoARealizar(
			String codigoCreditoARealizar, int situacaoAtualCredito,
			int anoMesReferenciaContabil) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento D�bitos Remove d�bitos a cobrar categoria
	 * referentes ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerDebitoACobrarCategoriaDoParcelamento(Integer idDebito)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento D�bitos Remove d�bitos a cobrar referentes
	 * ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerDebitoACobrarDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento D�bitos Pesquisa d�bito a cobrar
	 * referentes ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarDoParcelamento(
			Integer codigoImovel, Integer codigoParcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento D�bitos Remove creditos a realizar
	 * referentes ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerCreditoARealizarDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento D�bitos Remove guia pagamento referente ao
	 * parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerGuiaPagamentoDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento D�bitos Remove guia pagamento referentes
	 * ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerGuiaPagamentoCobrancaDoParcelamento(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @created 16/02/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel,
			int numeroSequencialDocumento) throws ErroRepositorioException;
	
	/**
	 * [UCXXXX] - Emitir Documento de Cobran�a
	 *
	 * @author Rafael Corr�a
	 * @date 02/09/2009
	 *
	 * @param idCobrancaDocumento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> pesquisarCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * Inseri a cobran�a situa��o historico na base passando a cole��o de
	 * cobran�a situa��o historico
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 20/03/2006
	 * 
	 * @param collectionCobrancaSituacaoHistorico
	 * @return
	 */
	public void inserirCobrancaSituacaoHistorico(
			Collection collectionCobrancaSituacaoHistorico)
			throws ErroRepositorioException;

	/**
	 * Atualiza o ano mes de cobranca situa��o historico
	 * 
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author S�vio Luiz
	 * @date 17/03/2006
	 * 
	 * @param situacaoEspecialFaturamentoHelper
	 * @throws ErroRepositorioException
	 */
	public void atualizarAnoMesCobrancaSituacaoHistorico(
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper,
			Integer anoMesReferencia, Collection colecaoImoveis, Integer idFaturamentoSituacaoConsumo)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006 Pesquisa os ID das Contas dos cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Rela��o Tipo Cliente Imovel
	 * @return Cole��o de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIDContasClienteConta(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;

	/**
	 * Atualiza o numero de parcelamento e reparcelamento na tabela de imovel
	 * 
	 * Desfazer Parcelamento de Debitos
	 * 
	 * @author Fernanda Paiva
	 * @date 29/04/2006
	 * 
	 * @param imovel
	 * @param numeroParcelamento
	 * @param numeroReparcelamento
	 * @param numeroReparcelamentoConsecutivo
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosParcelamentoImovel(Integer codigoImovel,
			Short numeroParcelamento, Short numeroReparcelamento,
			Short numeroReparcelamentoConsecutivo)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada N�o Paga Author: Fernanda
	 * Paiva Data: 02/05/2006
	 * 
	 * Obtem os parcelamentos de d�bitos efetuados no m�s de faturamento
	 * corrente e que estejam com situa��o normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 */
	public Collection pesquisarParcelamentosSituacaoNormalNoMes(
			String parcelamentoSituacao, Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada N�o Paga Author: Fernanda
	 * Paiva Data: 02/05/2006
	 * 
	 * Obtem as guias de pagamentos dos parcelamentos de d�bitos efetuados no
	 * m�s de faturamento corrente e que estejam com situa��o normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 */
	public Collection pesquisarGuiaPagamentoDoParcelamento(String parcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada N�o Paga Author: Fernanda
	 * Paiva Data: 02/05/2006
	 * 
	 * Obtem os pagamentos para a guia de pagamentos dos parcelamentos de
	 * d�bitos efetuados no m�s de faturamento corrente e que estejam com
	 * situa��o normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 */
	public Collection pesquisarPagamentoParaGuiaPagamentoDoParcelamento(
			String pagamento,Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0317] Manter Crit�rio de Cobran�a
	 * 
	 * Este caso de uso remove as linhas da cobran�a crit�rio passando a colecao
	 * de cobran�a criterio
	 * 
	 * [SB0002] Excluir Crit�rio de Cobran�a
	 * 
	 * @author S�vio luiz
	 * @created 11/05/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerCobrancaCriterioLinha(String[] idscobrancaCriterio)
			throws ErroRepositorioException;

	public Collection gerarRelacaoDebitos(String idImovelCondominio,
			String idImovelPrincipal, String idNomeConta,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa, String ordenacao,
			int quantidadeImovelInicio, String indicadorCpfCnpj, String cpfCnpj

	) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitir(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), rota (ROTA_ID), sequencial da rota (IMOV_NNSEQUENCIALROTA)e 
	 * cobranca documento (CBDO_ID)
	 * 
	 * @author Raphael Rossiter
	 * @data 27/06/2007
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitirPorRota(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * Seleciona os �tns do documento de cobran�a correspondentes a conta e
	 * ordenar por ano/m�s de refer�ncia da conta
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteConta(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Rafael Corr�a
	 * @data 02/09/2009
	 * 
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaRelatorio(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando)
			throws ErroRepositorioException;

	/**
	 * 
	 * Gerar Rela��o de D�bitos
	 * 
	 * [UC0227] Gerar Rela��o de D�bitos
	 * 
	 * @author Rafael Santos
	 * @date 12/06/2006
	 * 
	 */
	public Integer obterQuantidadaeRelacaoImoveisDebitos(
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
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa)
			throws ErroRepositorioException;

	/**
	 * Retorna o count do resultado da pesquisa de Cobranca Cronograma
	 * 
	 * pesquisarCobrancaCronogramaCount
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 14/06/2006
	 * 
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarCobrancaCronogramaCount(Filtro filtro)
			throws ErroRepositorioException;

	public void removerCobrancaCronograma(Integer idGrupoCronogramaMes)
			throws ErroRepositorioException;

	/**
	 * 
	 * Consultar Rela��o de Debitos do Imovel Consulta o Consumo Medio do Imovel
	 * 
	 * [UC0227] - Gerar Rel��o de D�bitos
	 * 
	 * @author Rafael Santos
	 * @date 15/06/2006
	 * 
	 * @param imovelId
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMedioConsumoHistoricoImovel(Integer imovelId)
			throws ErroRepositorioException;

	/**
	 * Gerar Relat�rio de Crit�rio de Cobran�a
	 * 
	 * Pesquisa as linhas de crit�rio de cobran�a atrav�s do id do crit�rio de
	 * cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @data 09/08/2006
	 * 
	 */
	public Collection pesquisarCobrancaCriterioLinha(Integer idCriterioCobranca)
			throws ErroRepositorioException;

	/**
	 * Gerar Relat�rio de Perfil de Parcelamento
	 * 
	 * Pesquisa os Parcelamentos Desconto Antiguidade atrav�s do id de Perfil de
	 * Parcelamento
	 * 
	 * @author Rafael Corr�a
	 * @data 22/08/2006
	 */
	public Collection pesquisarParcelamentoDescontoAntiguidade(
			Integer idParcelamentoPerfil) throws ErroRepositorioException;

	/**
	 * Gerar Relat�rio de Perfil de Parcelamento
	 * 
	 * Pesquisa os Parcelamentos Desconto Inatividade atrav�s do id de Perfil de
	 * Parcelamento
	 * 
	 * @author Rafael Corr�a
	 * @data 22/08/2006
	 */
	public Collection pesquisarParcelamentoDescontoInatividade(
			Integer idParcelamentoPerfil) throws ErroRepositorioException;

	/**
	 * Gerar Relat�rio de Perfil de Parcelamento
	 * 
	 * Pesquisa os Reparcelamentos Consecutivos atrav�s do id de Perfil de
	 * Parcelamento
	 * 
	 * @author Rafael Corr�a
	 * @data 22/08/2006
	 */
	public Collection pesquisarReparcelamentoConsecutivo(
			Integer idParcelamentoPerfil) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite iniciar processos batch de faturamento ou
	 * cobran�a previdamento comandados e processos mensais ou eventuais
	 * 
	 * [UC0001] - Iniciar Processo
	 * 
	 * Este subfluxo inicia os processo batch de cobran�a do sistema
	 * 
	 * [SB0002] - Iniciar Process de Cobran�a Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 17/08/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronogramaComandadosNaoRealizados()
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite iniciar processos batch de faturamento ou
	 * cobran�a previdamento comandados e processos mensais ou eventuais
	 * 
	 * [UC0001] - Iniciar Processo
	 * 
	 * Este subfluxo inicia os processo batch de cobran�a do sistema
	 * 
	 * [SB0002] - Iniciar Process de Cobran�a Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 17/08/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeCronogramaEventuaisComandadosNaoRealizados()
			throws ErroRepositorioException;

	/**
	 * [UC0476] Emitir Documento de Cobran�a - Ordem de Corte
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Ana Maria
	 * @data 07/09/2006
	 * 
	 * @param Collection
	 *            <CobrancaDocumento>
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<EmitirDocumentoCobrancaHelper> pesquisarCobrancaDocumentoOrdemCorte(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0476] Emitir Documento de Cobran�a
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), rota (ROTA_ID), sequencial da rota
	 * (IMOV_NNSEQUENCIALROTA)e cobranca documento (CBDO_ID)
	 * 
	 * @author Raphael Rossiter
	 * @data 07/08/2007
	 * 
	 * @return Collection<EmitirDocumentoCobrancaHelper>
	 */
	public Collection<EmitirDocumentoCobrancaHelper> pesquisarCobrancaDocumentoOrdemCortePorRota(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException ;
	
	
	
	/**
	 * [UC0582] Emitir Boletim de Cadastro
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Rafael Corr�a
	 * @data 16/05/2007
	 * 
	 * @param Collection
	 *            <CobrancaDocumento>
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> pesquisarCobrancaDocumentoBoletimCadastro(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;

	/**
	 * Este caso de consulta os dados do imovel, esse metodo consulta os
	 * documentos de cobran�a do imovel
	 * 
	 * [UC0472] - Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 18/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarImovelDocumentosCobranca(Integer idImovel,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Este caso de consulta os dados do imovel, esse metodo consulta a
	 * quantidade de documentos de cobran�a do imovel
	 * 
	 * [UC0472] - Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 18/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQuantidadeImovelDocumentosCobranca(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Este caso de consulta os dados do imovel, esse metodo consulta a
	 * quantidade de documentos de itens de cobran�a do imovel
	 * 
	 * [UC0472] - Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 18/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQuantidadeImovelDocumentosItemCobranca(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados do parcelamento necess�rios para o relat�rio atrav�s do
	 * id do parcelamento
	 * 
	 * @author Rafael Corr�a
	 * @date 25/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentoRelatorio(Integer idParcelamento)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os itens do parcelamento necess�rios para o relat�rio atrav�s do
	 * id do parcelamento
	 * 
	 * @author Rafael Corr�a
	 * @date 25/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentoItemPorIdParcelamentoRelatorio(
			Integer idParcelamento) throws ErroRepositorioException;

	/**
	 * Obtem os percentuais de desconto por tempo de inatividade
	 * 
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 2/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcelamentoDescontoInatividade obterPercentualDescontoInatividade(
			Integer idPerfilParc, int qtdeMeses)
			throws ErroRepositorioException;

	/**
	 * Consultar Servi�os/Atualiza��es do documento de cobra�a
	 * 
	 * 
	 * [UC0349] - Emitir Documento de cobran�a - Ordem de Fiscaliza��o
	 * 
	 * @author Ana Maria
	 * @date 18/10/2006
	 * 
	 * @param idDocumentoCobranca
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarServioAtualizacao(Integer idDocumentoCobranca)
			throws ErroRepositorioException;

	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @created 23/10/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void associarContaParcelamento(Conta conta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Retorna os CBCM_ID da tabela COBRANCA_GRUPO_CRONOGRAMA_MES
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaGrupoCronogramaMes()
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Retorna os CBCR_ID da tabela COBRANCA_ACAO_CRONOGRAMA com CBCM_ID da
	 * tabela COBRANCA_GRUPO_CRONOGRAMA_MES
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaAcaoCronograma(
			int idCobrancaGrupoCronogramaMes) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Retorna CAAC_TMREALIZACAO do COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
			int idCobrancaAcaoCronograma, int idCobrancaAtividade)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Retorna os DOTP_ID da tabela COBRANCA_ACAO com CBAC_ID de
	 * COBRANCA_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaAcao(int idCobrancaAcao)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0001] - Processar Documentos Cobran�a
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos,S�vio Luiz
	 * @date 17/10/2006,28/05/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<DadosPesquisaCobrancaDocumentoHelper> pesquisarCobrancaDocumento(
			int idCobrancaAtividadeAcaoCronograma)
			throws ErroRepositorioException; 

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0002] - Determinar Situa��o da A��o de Cobran�a
	 * 
	 * Retorna os ORSE_ID da tabela ORDEM_SERVICO com CBDO_ID da tabela
	 * COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos,S�vio Luiz
	 * @date 17/10/2006,28/05/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarOrdemServico(
			int idDocumentoCobranca)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0007] - Determinar Situa��o da Ordem de Servi�o
	 * 
	 * Retorna os AMEN_ICEXECUCAO da tabela ATENDIMENTO_MOTIVO_ENCERRAMENTO com
	 * AMEN_ID da tabela ORDEM_SERVI�O
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAtendimentoMotivoEncerramento(
			int idAtendimentoMotivoEncerramento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os CNTA_ID,GPAG_ID,DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM com
	 * CBDO_ID da tabela COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(int idCobrancaDocumento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os CNTG_ID da tabela COBRANCA_GERAL com CNTA_ID da tabela
	 * COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaGeral(int idConta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os DCST_IDATUAL,CNHI_DTCANCELAMENTO da tabela CONTA_HISTORICO com
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaHistorico(int idContaHistorico)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os DCST_IDATUAL,CNTA_DTCANCELAMENTO da tabela CONTA com CNTA_ID
	 * da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarConta(int idConta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os PARC_TM_PARCELAMENTO da tabela PARCELAMENTO com PARCcom
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarParcelamentoConta(int idConta,
			int idParcelamentoSituacao) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os GPGE_ID da tabela GUIA_PAGAMENTO_GERAL com GPAG_ID da tabela
	 * COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagamentoGeral(int idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os DCST_IDATUAL,GPGE_DTCANCELAMENTO da tabela
	 * GUIA_PAGAMENTO_HISTORICO com GPAG_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarGuiaPagamentoHistorico(
			int idGuiaPagamentoHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os DCST_IDATUAL,GPAG_DTCANCELAMENTO da tabela GUIA_PAGAMENTO com
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarGuiaPagamento(int idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os PARC_TM_PARCELAMENTO da tabela PARCELAMENTO com GPAG_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarParcelamentoGuiaPagamento(int idGuiaPagamento,
			int idParcelamentoSituacao) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os DAGE_ID da tabela DEBITO_A_COBRAR_GERAL com DBAC_ID da tabela
	 * COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarGeral(int idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os DCST_IDATUAL,DAGE_DTCANCELAMENTO da tabela
	 * DEBITO_A_COBRAR_HISTORICO com DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoACobrarHistorico(
			int idDebitoACobrarHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os DCST_IDATUAL,GPAG_DTCANCELAMENTO da tabela DEBITO_A_COBRAR com
	 * DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoACobrar(int idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna os PARC_TM_PARCELAMENTO da tabela PARCELAMENTO com DBAC_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos,S�vio Luiz
	 * @date 17/10/2006,29/05/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarParcelamentoDebitoACobrar(int idDebitoACobrar,
			int idParcelamentoSituacao) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna o Menor PGHI_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosContaHistorico(int idContaHistorico)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna o Menor PGMT_DTPAGAMENTO da tabela PAGAMENTO com CNTA_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosConta(int idConta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna o Menor PGHI_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * GPAG_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosGuiaPagamentoHistorico(
			int idGuiaPagamentoHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna o Menor PGMT_DTPAGAMENTO da tabela PAGAMENTO com GPAG_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosGuiaPagamento(int idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna o Menor PGHI_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosDebitoACobrarHistorico(
			int idDebitoACobrarHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0003] - Determinar Situa��o do D�bito do Item do Documento de Cobran�a
	 * 
	 * Retorna o Menor PGMT_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosDebitoACobrar(int idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0005] - Determinar Situa��o Predominante do D�bito do Documento de
	 * Cobran�a
	 * 
	 * Retorna o CBCT_PCVLMINIMOPGPARCCANC, CBCBT_PCQTMINIMOPGPARCCANC da tabela
	 * COBRANCA_CRITERIO com CBCT_ID da tabela COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaCriterio(int idCobrancaCriterio)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0004] - Atualizar item do documento de cobran�a
	 * 
	 * Atualizar os COBRANCA_DOCUMENT_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 19/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaDocumentoItem(
			Collection colecaoCobrancaDocumentoItem)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0004] - Processar Documento de Cobran�a
	 * 
	 * Atualizar os COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos
	 * @date 19/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaDocumento(Collection colecaoCobrancaDocumento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0001] - Processar Documentos de Cobran�a
	 * 
	 * Retorna os dados do Imovel
	 * 
	 * @author Rafael Santos
	 * @date 19/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosImovel(int idImovel)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0001] - Processar Documento de Cobran�a
	 * 
	 * Atualizar os COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA
	 * 
	 * @author Rafael Santos
	 * @date 25/10/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaAcaoAtividadeCronograma(
			int idCobrancaAcaoAtividadeCrongrama)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0006] - Processar A��o com Ordens de Servi�o
	 * 
	 * Retorna os ORSE_ID da tabela ORDEM_SERVICO com SVTP_ID da tabela
	 * COBRANCA_ACAO e ORSE_TMGERACAO entre CAAC_DTPREVISTA do Emitir e do
	 * Encerrar
	 * 
	 * @author Rafael Santos
	 * @date 25/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicos(int idServicoTipo,
			Date dataPrevistaAtividadeEncerrar,
			Date dataPrevistaAtividadeEmitir, int indice)
			throws ErroRepositorioException;

	/**
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 31/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcelamentoFaixaValor obterParcelamentoFaixaValor(
			Integer idParcelamentoQtdePrestacao, BigDecimal valorFaixa)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @created 16/02/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel,
			int numeroSequencialDocumento) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com c�digo de Barras [SB0005] - Processar
	 * Recebimento de Acr�scimos por Inpontualidade Autor: S�vio Luiz
	 * Data:06/11/2006
	 */
	public Collection pesquisarCobrancaDocumentoItemComConta(
			Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com c�digo de Barras [SB0005] - Processar
	 * Recebimento de Acr�scimos por Inpontualidade Autor: S�vio Luiz
	 * Data:06/11/2006
	 */
	public Collection pesquisarCobrancaDocumentoItemComGuiaPagamento(
			Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0178] Processar Pagamentos com c�digo de Barras Auhtor: S�cio Luiz
	 * Data: 06/11/2006
	 * 
	 */
	public void atualizarGuiaPagamento(Collection idsGuiaPagamento,
			Date dataVencimento) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * O sistema exclui o resumo das a��es de cobran�a correspondente ao
	 * cronograma de a��o de cobran�a que esta sendo processado
	 * 
	 * @author Rafael Santos
	 * @date 08/11/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public void deletarResumoCobrancaAcao(int idCobrancaAcaoCronograma)
			throws ErroRepositorioException;

	/**
	 * retorna o objeto ResolucaoDiretoria com a maior data Vig�ncia inicial
	 * 
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 08/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio()
			throws ErroRepositorioException;

	/**
	 * Obtem a condi��o referente � qtde de reparcelamentos consecutivos j�
	 * realizadas para o perfil do parcelamento para o im�vel
	 * 
	 * a partir da tabela PARCELAMENTO_QUANTIDADE_REPARCELAMENTO com
	 * PCPF_ID=PCPF_ID da tabela PARCELAMENTO_PERFIL e
	 * PQTR_QTMAXIMAREPARCELAMENTO igual ou menor que
	 * IMOV_NNREPARCELAMENTOCONSECUTVOS, caso mais de uma ocorrencia seja
	 * selecionada, escolher a que tiver o maior valor de
	 * PQTR_QTMAXIMAREPARCELAMENTO
	 * 
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcelamentoQuantidadeReparcelamento obterQtdeReparcelamentoPerfil(
			Integer idPerfilParc, Short numeroReparcelamentoConsecutivos)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento D�bitos Remove ClienteGuiaPagamento
	 * referentes ao parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @created 28/11/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerClienteGuiaPagamentoDoParcelamento(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa o crit�rio de cobran�a linha definido para a rota
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @author Leonardo Vieira
	 * @created 12/12/2006
	 * 
	 * @param idRota
	 * @param idCobrancaAcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public CobrancaCriterio pesquisarCriterioCobrancaRota(Integer idRota,
			Integer idCobrancaAcao) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa o crit�rio de cobran�a linha definido para o crit�rio de
	 * cobran�a
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * 
	 * @author Leonardo Vieira
	 * @created 12/12/2006
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaCriterioLinha> pesquisarCobrancaCriterioLinhaCriterio(
			Integer idCobrancaCriterio) throws ErroRepositorioException;
		
	public CobrancaCriterio pesquisarCobrancaCriterioIdCriterio(
			Integer idCobrancaCriterio) throws ErroRepositorioException;

	/**
	 * Pesquisa o documento de cobranca da acao precedente (2.3.1)
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a 
	 * [SB0003] Gerar Atividade de A��o de Cobran�a o Im�vel
	 * 
	 * @author Francisco do Nascimento
	 * @date 22/12/2008
	 *
	 * @param idImovel
	 * @param idServicoTipo
	 * @param indicadorExecucao
	 * @param dataEncerramento
	 * @return Id do documento de cobran�a
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDocumentoCobrancaAcaoPrecedente(Integer idImovel,
			Integer idServicoTipo, Short indicadorExecucao,
			Date dataEncerramento) throws ErroRepositorioException;

	public Integer pesquisarDocumentoCobrancaRelativoAcaoPrecedente(
			Integer idImovel, Integer idDocumentoTipo, Date dataEmissao,
			Date dataEmissaoValidade) throws ErroRepositorioException;

	public Collection pesquisarImovelCobrancaSituacao(Integer idImovel)
			throws ErroRepositorioException;

	public DebitoTipo pesquisarDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronograma(
			Integer idCronogramaAtividadeAcaoCobranca)
			throws ErroRepositorioException;

	public CobrancaAcaoAtividadeComando pesquisarCobrancaAcaoAtividadeComando(
			Integer idCobrancaAcaoAtividadeComando)
			throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItemContaGuiaPagamentoDebitoACobrar(
			Integer idCobrancaDocumento) throws ErroRepositorioException;

	public CobrancaDocumento pesquisarCobrancaDocumento(Integer idImovel,
			Integer idDocumentoTipo) throws ErroRepositorioException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronogramaId(
			Integer idCobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada N�o Paga Author: Raphael
	 * Rossiter Data: 28/12/2006
	 * 
	 * Obtem as contas dos parcelamentos de d�bitos efetuados no m�s de
	 * faturamento corrente e que estejam com situa��o normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaDoParcelamento(Integer parcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada N�o Paga Author: Raphael
	 * Rossiter Data: 28/12/2006
	 * 
	 * Obtem os pagamentos para a conta dos parcelamentos de d�bitos efetuados
	 * no m�s de faturamento corrente e que estejam com situa��o normal
	 * 
	 * @param conta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoParaContaDoParcelamento(String idConta)
			throws ErroRepositorioException;

	/**
	 * verifica se conta tem debito cobrado (CNTA_ID ocorre na tabela
	 * DEBITO_COBRADO)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar �nica Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Conta verificarContaDebitoCobrado(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * obtem o consumo m�dio do imovel CSHI_NNCONSUMOCONSUMOMEDIO da tabla
	 * CONSUMO_HISTORICO com IMOV_ID = IMOV_ID da tabela CONTA e o maior m�s/ano
	 * de consumo(CSHI_AMFATURAMENTO)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar �nica Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterConsumoMedioImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * (DBTP_ID da tabela DEBITO_COBRADO com CNTA_ID = CNTA_ID da conta a ser
	 * parcelada ocorrendo na tabela FISCALIZACAO_SITUACAO_SERVICO_A_COBRAR)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar �nica Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterIdDebitoTipoDeFiscalizacaoSituacaoServicoACobrar(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * DBCB_NNPRESTACAO da tabela DEBITO_COBRADO com CNTA_ID = CNTA_ID da conta
	 * e DBTP_ID da tabela DEBITO_COBRADO com CNTA_ID = CNTA_ID ocorrendo na
	 * tabela FISCALIZACAO_SITUACAO_SERVICO_A_COBRAR
	 * 
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar �nica Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterNumeroPrestacaoDebitoCobrado(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Metodo criado para pesquisar os parcelamentos q tenham juros e nao tenha
	 * criado o debito dos juros DBTP_ID = 44
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 23/02/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentosSemDebitos()
			throws ErroRepositorioException;

	/**
	 * Pesquisa a colecao de a��o de cobran�a passando o id da acao precedente
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
	 * @created 27/02/2007
	 * 
	 * @param idCobracaoAcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesqsuisarAcaoCobrancaPelaPrecedente(
			Integer idCobracaoAcao) throws ErroRepositorioException;

	/**
	 * Pesquisa a colecao de a��o de cobran�a passando o id da acao precedente
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
	 * @created 27/02/2007
	 * 
	 * @param idCobracaoAcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarIdDocumentoCobranca(Integer idImovel,
			Integer idDocumentoTipo, Date dataEmissao)
			throws ErroRepositorioException;

	/**
	 * Obt�m a menor data de pagamento para as guias de pagamento
	 * 
	 * [UC0302] Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 19/03/2007
	 * 
	 * @param idGuiaPagamento
	 * @param idImovel
	 * @param idDebitoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentoGuiaPagamento(
			Integer idGuiaPagamento, Integer idImovel, Integer idDebitoTipo)
			throws ErroRepositorioException;

	/**
	 * obtem o numero de Consumo Faturado Mes CSHI_NNCONSUMOFATURADOMES da
	 * tabela CONSUMO_HISTORICO com IMOV_ID = IMOV_ID da tabela CONTA e o maior
	 * m�s/ano de consumo(CSHI_AMFATURAMENTO)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar �nica Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 19/03/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterNumeroConsumoFaturadoMes(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * verificar se a(s) conta(s) parceladas j� est�o no hist�rico [UC0252]
	 * Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 09/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection verificarContaHistoricoParcelamento(Integer idImovel,Integer idParcelamento)
			throws ErroRepositorioException;

	/**
	 * verificar se a(s) debito(s) a cobrar parcelados j� est�o no hist�rico
	 * [UC0252] Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 09/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection verificarDebitoACobrarHistoricoParcelamento(
			Integer idImovel,Integer idParcelamento) throws ErroRepositorioException;

	/**
	 * verificar se a(s) credito(s) a realizar utilizados no parcelados j� est�o
	 * no hist�rico [UC0252] Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 09/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection verificarCreditoARealizarHistoricoParcelamento(
			Integer idImovel,Integer idParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0XXX] Emitir Aviso de Cobran�a
	 * 
	 * Seleciona os itens do documento de cobran�a correspondentes a guia
	 * pagamento
	 * 
	 * @author S�vio Luiz
	 * @data 09/04/2007
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<Object[]> selecionarDadosCobrancaDocumentoItemReferenteGuiaPagamento(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;

	/**
	 * retorna referenciaContabil da conta cancelada por retifica��o [UC0252]
	 * Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 13/04/2007
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCanceladaRetificacao(Integer idImovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * retorna o objeto ParcelamentoFaixaValor com o valor do debito(valorFaixa)
	 * com desconto maior q o da faixa e menor que pr�xima faixa
	 * 
	 * [UC0575] - Emitir Parcelamento em Atraso
	 * 
	 * @author S�vio Luiz
	 * @date 14/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosParcelamentoComMaiorTimestemp(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobran�a
	 * 
	 * @author Ana Maria
	 * @created 13/04/2007
	 * 
	 * @param idCliente
	 * @param numeroSequencialDocumento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarCobrancaDocumentoItemCliente(Integer idCliente,
			int numeroSequencialDocumento) throws ErroRepositorioException;

	/**
	 * Consulta o id e a situa��o da ordem de servi�o associada ao documento de
	 * cobran�a passado como par�metro
	 * 
	 * @author S�vio Luiz
	 * @created 13/04/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Object[] pesquisarDadosOrdemServicoDocumentoCobranca(
			Integer idDocumentoCobranca) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar D�bitos a Cobrar de Doa��es
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsParcelamentosItemDebitoACobrar(
			Collection idsDebitoACobrar) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar D�bitos a Cobrar de Doa��es
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsCobrancaDocumentoItemDebitoACobrar(
			Collection idsDebitoACobrar) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar D�bitos a Cobrar de Doa��es
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarCobrancaDocumentoItemDebitoACobrar(
			Collection idsDocumentoItemDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar D�bitos a Cobrar de Doa��es
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarParcelamentosItemDebitoACobrar(
			Collection idsParcelamentosItemDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Pesquisa os dados de cobranca documento agrupado para pegar a quantidade
	 * e o valor dos documentos
	 * 
	 * @author S�vio Luiz
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosCobrancaDocumentoAgrupadoPorDataPrevista(
			int idCobrancaAtividadeAcaoCronograma)
			throws ErroRepositorioException;

	/**
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Pesquisa os dados de cobranca documento agrupado para pegar a quantidade
	 * e o valor dos documentos
	 * 
	 * @author S�vio Luiz
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosCobrancaDocumentoAgrupadoPorDataComando(
			int idCobrancaAtividadeAcaoCronograma)
			throws ErroRepositorioException;

	/**
	 * 
	 * atualiza o sequencial de conta impress�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarSequencialCobrancaDocumentoImpressao(
			Map<Integer, Integer> mapAtualizaSequencial)
			throws ErroRepositorioException;

	/**
	 * Pesquisar rela��o de protocolos de documentos de cobran�a do cronograma
	 * 
	 * @author Ana Maria
	 * @date 15/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarProtocoloDocumentoCobrancaCronograma(
			Integer idCobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException;

	/**
	 * Pesquisar rela��o de protocolos de documentos de cobran�a do cronograma
	 * 
	 * @author Ana Maria
	 * @date 21/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarProtocoloDocumentoCobrancaEventual(
			Integer idCobrancaAcaoAtividadeComand)
			throws ErroRepositorioException;
	
	/**
	 * Recupera a cole��o de OS para o encerramento
	 * 
	 * [UC0478] - Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0007] - Determinar Situa��o da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 28/05/2007
	 * 
	 * @throws ControladorException
	 */
	public void atualizarParmsOS(Collection colecaoIdsOS,Integer idMotivoEncerramento)
			throws ErroRepositorioException;
	
	/**
	 * Recupera os dados de documento item
	 * 
	 * @author S�vio Luiz
	 * @created 29/05/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosCobrancaDocumentoItem(
			Integer idDocumentoCobranca) throws ErroRepositorioException;

	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * [FS0004] Validar Registro Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @created 05/06/2007
	 * 
	 * @param idRA
	 * @exception ErroRepositorioException
	 */
	public Object[] pesquisarRegistroAtendimentoTransferenciaDebitoCredito(
			Integer idRA) throws ErroRepositorioException ;
	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * [FS0004] Validar Registro Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @created 05/06/2007
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @exception ErroRepositorioException
	 */
	public EspecificacaoTipoValidacao pesquisarEspecificacaoTipoValidacaoTransferenciaDebitoCredito(
			Integer idSolicitacaoTipoEspecificacao) throws ErroRepositorioException ;
	
	/**
	 * Pesquisar rela��o de parcelamento 
	 * 
	 * @author Ana Maria
	 * @date 01/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<RelacaoParcelamentoRelatorioHelper> pesquisarRelacaoParcelamento(FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento)
				throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0001] - Processar Documentos de Cobran�a
	 * 
	 * Retorna os dados do Imovel
	 * 
	 * @author S�vio Luiz
	 * @date 11/06/2007
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosImovelPorOS(int idOrdemServico)
			throws ErroRepositorioException;
	
	/**
	 * [UC0XXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * @author S�vio Luiz, Anderson Italo
	 * @created 15/06/2006, 25/02/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarCobrancaAcaoAtividadeComandoSemRealizacao()
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das a��es de cobran�a eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * [SB0001] - Processar Documentos Cobran�a
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author S�vio Luiz
	 * @date 18/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<DadosPesquisaCobrancaDocumentoHelper> pesquisarCobrancaDocumentoEventual(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das a��es de cobran�a eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * [SB0001] - Processar Documentos Cobran�a
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author S�vio Luiz
	 * @date 18/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarOrdemServicoEventual(int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das a��es de cobran�a eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 19/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public void deletarResumoCobrancaAcaoEventual(int idCobrancaAcaoCronograma)
			throws ErroRepositorioException;
	
	/**
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Pesquisa os dados de cobranca documento agrupado para pegar a quantidade
	 * e o valor dos documentos
	 * 
	 * @author S�vio Luiz
	 * @date 19/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosCobrancaDocumentoEventualAgrupadoPorDataPrevista(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das a��es de cobran�a eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 19/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaAcaoAtividadeComando(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;

	/**
	 * Ana Maria Data: 08/07/2007 Pesquisa os ID dos Imov�is pelo Cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @return Cole��o de Ids dos Im�veis
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsImoveisCliente(String codigoCliente) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente 
	 * Ana Maria Data: 08/08/2007
	 * 
	 * @param idImoveis
	 *            Matriculas dos Imoveis
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @param situacaoNormal
	 *            Situa��o Normal
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @return Cole��o de Guias de Pagamentos
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoIdsImoveis(Integer idCliente, Collection idsImoveis, int indicadorPagamento,
			String situacaoNormal, Date dataVencimentoInicial,
			Date dataVencimentoFinal) throws ErroRepositorioException;
	
	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Ana Maria Data:
	 * 08/08/2007
	 * 
	 * @param idImoveis
	 *            Matriculas dos Imoveis
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @param situacaoNormal
	 *            Situa��o Normal
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @return Cole��o de Guias de Pagamentos
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoIdsImoveis(Integer idCliente, Collection idsImoveis, int indicadorPagamento,
			String situacaoNormal, Short clienteRelacaoTipo, Date dataVencimentoInicial,
			Date dataVencimentoFinal) throws ErroRepositorioException;
	
	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente
	 *  Pesquisa os ID dos imoveis dos cliente sem rala��o fim
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Rela��o Tipo Cliente Imovel
	 * @return Cole��o de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdImoveisClienteSemRelacaoFim(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;
	
	/**
	 * Faz parte de [UC0067] Obter D�bito do Imovel ou Cliente Pesquisa os ID
	 * dos imoveis dos cliente sem rala��o fim
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Rela��o Tipo Cliente Imovel
	 * @return Cole��o de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdImoveisClienteSuperiorSemRelacaoFim(
			String codigoCliente)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Gerar Curva ABC de Debitos
	 * 
	 * [UC0621] Gerar Curva ABC de Debitos
	 * 
	 * @author Ivan S�rgio
	 * @date 01/08/2007
	 * 
	 */
	public Collection gerarCurvaAbcDebitos(
			String classificacao,
			String indicadorImovelMedicaoIndividualizada,
			String indicadorImovelParalizacaoFaturamentoCobranca,
			String[] gerenciaRegional,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String idMunicipio,
			String[] situacaoLigacaoAgua,
			String[] situacaoLigacaoEsgoto,
			String intervaloConsumoMinimoFixadoEsgotoInicial,
			String intervaloConsumoMinimoFixadoEsgotoFinal,
			String indicadorMedicao,
			String idTipoMedicao,
			String idPerfilImovel,
			String idTipoCategoria,
			String[] categoria,
			String idSubCategoria) throws ErroRepositorioException;

	/**
	 * [UC0630] - Solicitar Emiss�o do Extrato de D�bitos
	 * Author: Vivianne Sousa 
	 * Data: 22/08/2007
	 * 
	 * Obtem os parcelamentos de d�bitos efetuados que estejam com situa��o normal
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentosSituacaoNormal(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0630] - Solicitar Emiss�o do Extrato de D�bitos
	 * Author: Vivianne Sousa 
	 * Data: 22/08/2007
	 * 
	 * @param idParcelamento
	 * 
	 * @return Cole��o de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarImovelParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0630] - Solicitar Emiss�o do Extrato de D�bitos
	 * Author: Vivianne Sousa 
	 * Data: 22/08/2007
	 * 
	 * @param idParcelamento
	 * 
	 * @return Cole��o de Creditos A Realizar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditosARealizarParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author S�vio Luiz
	 * @data 26/05/2006
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitirCAER(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;
	
	/**
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarRDUtilizadaPeloImovel(
			Integer idRD, Integer idImovel)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 01/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoACobrarSancoes(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 06/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoACobrar(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito) throws ErroRepositorioException ;
	
	/**
	 * Faz parte de [UC0067] Obter D�bito do Im�vel ou Cliente Obtem os d�bitos total
	 * de um cliente 
	 */
	public Collection pesquisarDebitosCliente(Integer idCliente, Short relacaoTipo, Collection idsImoveis,
			int indicadorPagamento, int indicadorConta, 
			String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada,
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorDividaAtiva)
			throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 14/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoCobradoContas(Integer idImovel,	
			Integer anoMesInicialReferenciaDebito,
			Integer anoMesFinalReferenciaDebito, int indicadorDividaAtiva) throws ErroRepositorioException ;
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 20/09/2007
	 * 
	 * @param idImovel
	 * @param anoMesInicialReferenciaDebito
	 * @param anoMesFinalReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoCobradoSancoes(
			Integer idImovel,
			Integer anoMesInicialReferenciaDebito,
			Integer anoMesFinalReferenciaDebito, int indicadorDividaAtiva) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0701] Informar �ndices dos Acr�scimos de Impontualidade 
	 * 
	 * @author S�vio Luiz
	 * @created 26/09/2007
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaximoAnoMesIndicesAcerscimosImpontualidade() 
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0216] Calcular Acr�scimo por Impontualidade
	 * 
	 * @autor: Raphael Rossiter
	 * 
	 * Pesquisa os dados do Indices Acrescimo Impontualidade menor ao
	 * ano mes referencia
	 * 
	 * @param anoMesReferenciaDebito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarMenorIndiceAcrescimoImpontualidade() 
		throws ErroRepositorioException ;
	
	/**
	 * Retorna uma colecao de Debitos
	 * por Faixa de Valores dos Imoveis
	 * 
	 * @author Ivan Sergio
	 * @created 20/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoImovelPorFaixaValores(
			String idImovel,
			String valorMinimoDebito,
			String anoMesReferenciaInicial,
			String anoMesReferenciaFinal,
			String classificacao,
			boolean pesquisaMunicipio) throws ErroRepositorioException;
	
//	Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloUnidadeNegocio(String idUnidadeNegocio,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloGrupo(String idGrupoCobranca,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloGerencia(String idGerenciaRegional,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloLocalidade(String idLocalidadeInicial, 
			String idLocalidadeFinal, String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloSetor(String codigoSetorComercialInicial, 
			String codigoSetorComercialFinal, String idLocalidade,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotas(String codigoSetorComercial, 
			String rotaInicial, String rotaFinal, String idLocalidade,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	 * @author Francisco do Nascimento
	 * @date 27/02/08
	 */
	public Collection pesquisarRotasPorCobrancaAcao(String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0067] Obter D�bito do Im�vel ou Cliente
	 * 
	 * @autor: Raphael Rossiter
	 * 
	 * Verifica se existe uma devolu��o associada ao credito
	 * 
	 * @param creditoARealizar
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean existeDevolucao(CreditoARealizar creditoARealizar) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0067] Inserir Comando Negaiva��o
	 * 
	 * @autor: Ana Maria
	 * 
	 * [FS0019] Verificar exist�ncia de Parcelamento
	 * 
	 * @param idImovel
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteResponsavelParcelamento(Integer idImovel) 
		throws ErroRepositorioException;
    
    /**
     * [UC0737] Atualiza Quantdade de Parcela Paga Consecutiva e Parcela B�nus
     * 
     * Retorna dados dos parcelamentos com RD = 8 que estejam com situa��o normal 
     * e que n�o exista outro parcelamento com data posterior  
     * 
     * @author Vivianne Sousa
     * @date 31/01/2008
     * 
     * @return Collection retorno
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> pesquisarParcelamentoRDEspecial(
            Integer situacaoParcelamento, Integer idLocalidade)
            throws ErroRepositorioException ;
    

	/**
	 * [UC0200] Inserir D�bito Autom�tico
	 * 
	 * @autor Rodrigo Silveira
	 * @date 28/01/2008
	 * [FS0006] Verificar Data de Op��o j� Exclu�da
	 * 
	 * @param matriculaImovel
	 * @param dataOpcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String verificarDataOpcaoJaExcluida(String matriculaImovel, Date dataOpcao) throws ErroRepositorioException;
    
    /**
     * [UC0737] Atualiza Quantidade de Parcela Paga Consecutiva e Parcela B�nus
     * 
     * @author Vivianne Sousa
     * @created 07/02/2008
     * 
     * @param idParcelamento
     * @exception ErroRepositorioException
     *                Repositorio Exception
     */
    public void atualizarNumeroParcelasPagasConsecutivasParcelamento(Integer idParcelamento,
            Short numeroParcelas)throws ErroRepositorioException;
    
    /**
     *[UC0676] - Consultar Resumo da Negativacao
	 * 
	 * Pesquisa resumo Negativacao
	 * 
	 * @author Marcio Roberto
	 * @date 28/02/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarNegativacao(
			DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int tipo)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0001] - Processar Documentos Cobran�a
	 * Consultar os documentos de cobranca sem carregar os criterios de cobranca
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos,S�vio Luiz, Francisco do Nascimento
	 * @date 17/10/2006,28/05/2007, 22/05/2008
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoResumoAcaoCobranca(
			int idCobrancaAtividadeAcaoCronograma, int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;	
	
	/**
	 * M�todo para atualizar a situacao do documento item de acordo com a situacao da 
	 *    conta / guia de pagamento / debito a cobrar.
	 * Este m�todo ser� usado nos m�todos cancelar/desfazer cancelamento conta,  incluir / desfazer parcelamento,
	 * incluir / excluir pagamento, movimento de arrecadadores
	 * 
	 * @param situacaoDebito
	 * @param dataSituacao
	 * @param idConta
	 * @param idGuiaPagamento
	 * @param idDebitoACobrar
	 * @throws ErroRepositorioException
	 * 
	 * @author Francisco do Nascimento
	 * @date 26/05/2008
	 * 
	 */
	public void atualizarSituacaoCobrancaDocumentoItem(Integer situacaoDebito, Date dataSituacao, 
			Integer idConta, Integer idGuiaPagamento, Integer idDebitoACobrar) throws ErroRepositorioException;
	
	/**
	 * Recupera os dados de documento item com situacao atualizada 
	 * 
	 * @author Francisco do Nascimento
	 * @created 19/05/08
	 * 
	 * @param idDocumentoCobranca id do documento de cobranca
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosCobrancaDocumentoItemSituacaoJaAtualizada(
			Integer idDocumentoCobranca) throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * [SB0001] - Processar Documentos Cobran�a Eventual 
	 * Consultar os documentos de cobranca sem carregar os criterios de cobranca
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CACM_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_COMANDO
	 * 
	 * @author Rafael Santos,S�vio Luiz, Francisco do Nascimento
	 * @date 17/10/2006,28/05/2007, 04/06/2008
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoEventualSemCriterio(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o crit�rio de situacao de cobran�a definido para o crit�rio de
	 * cobran�a
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @author Francisco do Nascimento
	 * @created 10/06/2008
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CriterioSituacaoCobranca> pesquisarCobrancaCriterioSituacaoCobranca(
			Integer idCobrancaCriterio) throws ErroRepositorioException;
	
	/**
	 * Pesquisa o crit�rio de situacao de ligacao de agua definido para o crit�rio de
	 * cobran�a
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @author Francisco do Nascimento
	 * @created 10/06/2008
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CriterioSituacaoLigacaoAgua> pesquisarCobrancaCriterioSituacaoLigacaoAgua(
			Integer idCobrancaCriterio) throws ErroRepositorioException;
	
	/**
	 * Pesquisa o crit�rio de situacao de ligacao de esgoto definido para o crit�rio de
	 * cobran�a
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @author Francisco do Nascimento
	 * @created 10/06/2008
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CriterioSituacaoLigacaoEsgoto> pesquisarCobrancaCriterioSituacaoLigacaoEsgoto(
			Integer idCobrancaCriterio) throws ErroRepositorioException;	
			
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE D�BITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel,
			Date dataEmissao) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE D�BITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel,
			Date dataEmissao) throws ErroRepositorioException ;
	
	/**
	 * Verificar se existe pagamento para contas de um imovel as quais foram utilizadas
	 *  como entrada num parcelamento 
	 * @param idImovel
	 * @param dataParcelamento
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * @author Francisco do Nascimento
	 * @date 03/07/2008
	 * 
	 */
	public boolean exitePagamentoContaEntradaParcelamento(Integer idImovel, 
		Date dataParcelamento) throws ErroRepositorioException;	
	
	/**
	 * Consulta as contas transferidas
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarContasTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Consulta os d�bitos a cobrar transferidos
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarDebitosACobrarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Consulta as guias de pagamento transferidas
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarGuiasDePagamentoTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Consulta os cr�ditos a realizar transferidos
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarCreditosARealizarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0870] Gerar Movimento de Contas em Cobran�a por Empresa
	 * 
	 * Pesquisa as contas associadas ao im�vel
	 * 
	 * @author: Rafael Corr�a
	 * @date: 28/10/2008
	 */
	public Collection<Object[]> pesquisarContasInformarContasEmCobranca(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, 
			Collection<Integer> idsImoveis, SistemaParametro sistemaParametro) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 15/07/2008
	 */
	public Collection<Object[]> obterNomeCPFTestemunhas(Integer unidadeUsuario) throws ErroRepositorioException;
	
	/**
	 * [UC0852] Incluir D�bito a Cobrar de Entrada de Parcelamento N�o Paga
	 *
	 * Cancela uma guia de pagamento
	 *
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 *
	 * @param idGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void cancelarGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException ;
	
	/**
	 * [UC0852] Incluir D�bito a Cobrar de Entrada de Parcelamento N�o Paga
	 *
	 * Desassociar a conta do parcelamento
	 *
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 *
	 * @param idConta
	 * @throws ErroRepositorioException
	 */
	public void desassociarContaParcelamento(Integer idConta) throws ErroRepositorioException ;
	
	/**
	 * [UC0852] Incluir D�bito a Cobrar de Entrada de Parcelamento N�o Paga
	 *
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 *
	 * @param parcelamento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaDoParcelamentoNaoPago(Integer parcelamento)
			throws ErroRepositorioException ;
	
	/**
	 * Recupera os itens do parcelamento a partir do parcelamento
	 * 
	 * @author S�vio Luiz
	 * @date 23/10/2008
	 */
	public Collection<Object[]> pesquisarItensParcelamentos(Integer idParcelamento) throws ErroRepositorioException; 
	
	/**
	 * Pesquisa se a conta est� na tabela de empresa cobranca conta
	 * 
	 * @author S�vio Luiz
	 * @date 23/10/2008
	 */
	public Integer pesquisarEmpresaCobrancaConta(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * Recupera os dados do debito a cobrar
	 * 
	 * @author S�vio Luiz
	 * @date 23/10/2008
	 */
	public Object[] pesquisarDadosDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException; 
	
	/**
	 * Recupera os itens do parcelamento a partir do parcelamento
	 * 
	 * @author S�vio Luiz
	 * @date 23/10/2008
	 */
	public Collection<Object[]> pesquisarItensParcelamentosNivel2(Integer idPagamento) throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	
	public Collection pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobranca(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException;
	public Collection pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobrancaResumido(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCriterio(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal,int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException ;
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Integer pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobrancaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosArquivoTextoContasCobrancaEmpresa(
			Collection ids, 
			Integer idUnidadeNegocio,
			Integer numeroPagina, 
			int quantidadeRegistros,
			Integer idProgramaEspecial) throws ErroRepositorioException;
	
	public Collection obterUnidadeNegocioEmpresaCobrancaConta(Integer[] ids) throws ErroRepositorioException;
	
	/**
	 * Pesquisa se a conta est� na tabela de empresa cobranca conta
	 * 
	 * @author S�vio Luiz
	 * @date 23/10/2008
	 */
	public void removerEmpresaCobrancaContaPagamentos(Integer anoMesReferenciaArrecadacao,Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE D�BITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel,
			BigDecimal valorPagamento) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE D�BITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel,
			BigDecimal valorPagamento) throws ErroRepositorioException ;
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 *
	 * @author Raphael Rossiter
	 * @date 13/11/2008
	 *
	 * @return Collection<ResolucaoDiretoria>
	 * @throws ErroRepositorioException
	 */
	public  Collection<ResolucaoDiretoria>  pesquisarResolucaoDiretoriaMaiorDataVigenciaInicioPermissaoEspecial()
	throws ErroRepositorioException ;
	
	/**
	 * Pesquisa a quantidade de Rotas que nao possui um Criterio definido para cada uma das Acoes de Cobrancas passadas no filtro
	 * 
	 * @author Victor Cisneiros
	 * @date 10/12/2008
	 */
	public Integer pesquisarQtdeRotasSemCriteriosParaAcoesCobranca(
			PesquisarQtdeRotasSemCriteriosParaAcoesCobranca filtro) throws ErroRepositorioException;
	
	/**
	 * Deleta as OrdemServicoUnidade geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarOrdemServicoUnidadeGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Remover os debitos a cobrar das ordens de servico geradas pelos documentos
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/11/2009
	 */
	public void removerDebitoACobrarOrdemServicoGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deleta as OrdemServico geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarOrdemServicoGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deleta as DebitoACobrar geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarDebitoACobrarGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca, Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Deleta as CobrancaDocumentoItem geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarCobrancaDocumentoItemGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deleta os Documentos de Cobranca com os Ids passados como parametro
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarCobrancaDocumentos(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deletar os registros de imoveis nao gerados 
	 * 
	 * @author Francisco do Nascimento
	 * @date 17/11/2009
	 */
	public void removerImoveisNaoGerados(
			Integer idCobrancaComandoCronograma, Integer idCobrancaComandoEventual)  throws ErroRepositorioException;
	
	/**
	 * Inserir os registros de imoveis nao gerados dos documentos excedentes 
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/11/2009
	 */
	public void inserirImoveisNaoGeradosParaDocumentosExcedentes(Collection<Integer> idsDocumentosCobranca) 
		throws ErroRepositorioException;
	
	/**
	 * Pesquisar Quantidade de Ordens de Servi�o Encerradas
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer pesquisarQuantidadeOrdensServicoEncerradasPorCobrancaAcaoAtividade(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Quantidade de Pagamentos por Documentos de Cobran�a
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer pesquisarQuantidadePagamentosPorDocumentosCobranca(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Quantidade de Comandos Sucessores de um Comando
	 * 
	 * @author Victor Cisneiros
	 * @date 29/12/2008
	 */
	public Integer pesquisarQuantidadeComandosSucessores(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	/**
	 * [UC0868] Gerar Relatorio de Pagamentos das Contas em Cobranca por Empresa
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 08/01/2009
	 */
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(
			RelatorioPagamentosContasCobrancaEmpresaHelper helper) 
			throws ErroRepositorioException;
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaOpcaoTotalizacao(
			RelatorioPagamentosContasCobrancaEmpresaHelper helper) 
			throws ErroRepositorioException;
	
	
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(
			Integer idEmpresa, Integer referenciaPagamentoInicial, Integer referenciaPagamentoFinal
			) throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extens�o de Comando de das Contas em Cobranca por Empresa
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 03/02/2009
	 * 
	 * 
	 */
	
	public Collection pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresaParaCobranca(
			Integer idComandoEmpresaCobrancaConta) throws ErroRepositorioException ;
	
	public Integer retornaAnoMesContaUltimaExtensao(Integer idComandoEmpresaCobrancaConta) 
			throws ErroRepositorioException;

	
	
		
	/**
	 * [UC0878] Gerar Rela��o de Parcelamento - Vis�o Analitica
	 * 
	 * @author Bruno Barros
	 * 
	 * @date 04/02/2009
	 */
	public Collection<Object[]> filtrarRelacaoParcelamentoAnalitico(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento, Integer anoMesReferenciaFaturamento) throws ErroRepositorioException;	
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * [FS0028] Verifica se existeParcelas em atraso
	 * [FS0029] Verificar se existe parcelamento em andamento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParcelamentoMaisAtualDoImovel(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * [FS0028] Verifica se existeParcelas em atraso
	 * [FS0029] Verificar se existe parcelamento em andamento
	 * 
	 * verificar se existe parcelas a serem cobradas 
	 * (selecionar na tabela de debito a cobrar (FATURAMENTO.DEBITO_A_COBRAR),
	 * a linha que tenha o PARC_ID = PARC_ID da tabela COBRANCA.PARCELAMENTO e 
	 * o tipo de d�bito (DBTP_ID) = 40) 
	 * e caso o n�mero de presta��es do d�bito n�o seja igual ao n�mero de presta��es cobradas  
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2009
	 * 
	 * @param idParcelamento
	 * 
	 * @return Cole��o de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection obterDebitoACobrarDoParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * [FS0028] Verifica se existeParcelas em atraso
	 * 
	 * verificar se existe parcelas em atraso, ou seja, 
	 * verificar nas contas que ir�o fazer parte do parcelamento, 
	 * se existe algum d�bito de parcelamento da RDIR_ID = RDIR_IDPARCELASEMATRASO
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2009
	 * 
	 * @param idParcelamento
	 * 
	 * @return Cole��o de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasComParcelasEmAtrasoDoParcelamento(
			Integer idImovel,Integer refInicialInformada,
			Integer refFinalInformada) throws ErroRepositorioException;
	/**
	 * [UC0880] Gerar Movimento de Extens�o de Contas em Cobran�a por Empresa
	 * 
	 * Pesquisa as contas associadas ao im�vel
	 * 
	 * @author: R�mulo Aur�lio 
	 * @param idPerfil 
	 * @throws SQLException 
	 * @date: 13/02/2009
	 */
	public void inserirMovimentoExtensaoContasEmCobranca(
			Integer idLocalidade, Integer idPerfil) throws ErroRepositorioException;
	
	/**
	 * [UC0891] Gerar Relatorio de Im�veis com Acordo
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 01/04/2009
	 */
	public Collection pesquisarDadosGerarRelatorioImoveisComAcordo(
			Integer idUnidadeNegocio, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idGerenciaRegional,
			Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial,
			Integer rotaFinal, Integer sequencialRotaInicial,
			Integer sequencialRotaFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal) throws ErroRepositorioException ;
	
	public Integer pesquisarDadosGerarRelatorioImoveisComAcordoCount(
			Integer idUnidadeNegocio, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idGerenciaRegional,
			Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial,
			Integer rotaFinal, Integer sequencialRotaInicial,
			Integer sequencialRotaFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal) throws ErroRepositorioException;
		
	public CicloMetaGrupo pesquisarCicloMetaGrupoPorGrupoLocalidade(int anoMes, int idGrupo,
			int idLocalidade) throws ErroRepositorioException;
	
	public CicloMeta pesquisarMetaCiclo(int anoMes, int idCobrancaAcao) throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeDocumentosGeradosAcimaValorLimite(Integer idCAAC, Integer idCACM,
			Integer idLocalidade, BigDecimal valorLimite) throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeDocumentosGerados(Integer idCAAC, Integer idCACM, Integer idLocalidade)
		throws ErroRepositorioException;
	
	public Collection pesquisarDocumentosCobrancaExcedentes(Integer idCAAC, Integer idCACM, int quantidadeParaRemover,
		Integer idLocalidade) throws ErroRepositorioException;
	
	public void adicionarMetaCicloLocalidade(int idMetaCiclo, int idGrupo,
		int idLocalidade, int quantidadeASerAdicionada) throws ErroRepositorioException;
	
	public Collection<CicloMetaGrupo> pesquisarCicloMetaGrupoPorCicloMeta(Integer idCicloMeta, Integer idGrupo) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0000] - Gerar Metas do Cilo
	 * Pesquisar a quantidade de imoveis em cada grupo/localidade
	 *
	 * @author Francisco do Nascimento
	 * @date 23/04/2009
	 *
	 * @param idsLast Colecao de ids de situacao de liga��o de �gua
	 * @return Colecao no formato [idGrupo, idLocalidade, qtdImoveis]
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarQuantidadeImoveisPorGrupoLocalidade(Collection idsLast)
		throws ErroRepositorioException;

	/**
	 * [UC0216] Calcular Acrescimo por Impontualidade
	 *
	 * Caso o im�vel tenha d�bito autom�tico e o recebimento, mesmo em atraso, tenha sido atrav�s
	 * de d�bito autom�tico, o sistema n�o calcula os acr�scimos de impontualidade
	 * 
	 * (ARMV_DSIDENTIFICACAOSERVICO da tabela ARRECADADOR_MOVIMENTO com valor igual a "DEBITO 
	 * AUTOMATICO" com ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO_ITEM com AMIT_ID = 
	 * AMIT_ID da tabela PAGAMENTO com CNTA_ID = CNTA_ID recebido)
	 *
	 * @author Raphael Rossiter
	 * @date 12/05/2009
	 *
	 * @param idConta
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoDeContaPorDebitoAutomatico(Integer idConta) throws ErroRepositorioException ;
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * Author: Vivianne Sousa 
	 * Data: 13/05/2009
	 * 
	 * @param idParcelamento
	 * 
	 * @return Cole��o de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * Author: Vivianne Sousa 
	 * Data: 13/05/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * 
	 * @return idParcelamento
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdParcelamentoNormal(Integer idImovel, Integer referencia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * Author: Vivianne Sousa 
	 * Data: 18/05/2009
	 */
	public BigDecimal pesquisarValorDebitoCobradoParcelamentoConta(Integer idConta)
	throws ErroRepositorioException;
	
	/**
	 * [UC0878] Gerar Rela��o de Parcelamento - Vis�o Analitica
	 * 
	 * @author Bruno Barros
	 * 
	 * @date 05/06/2009
	 */
	public Integer pesquisarQuantidadeContasNaoPagasParcelamento(
			Integer idParcelamento,Integer idDebitoACobrar ) throws ErroRepositorioException;	
	/**
	 * retorna id da ResolucaoDiretoria 
	 * 
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 08/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public  Integer  pesquisarResolucaoDiretoriaComPercentualDoacao()
			throws ErroRepositorioException;
	
	/**
	 * retorna cole��o de ids de Rota de um Grupo de faturamento 
	 * 
	 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Crian�a para Negocia��o a Vista
	 * 
	 * @author Vivianne Sousa
	 * @date 11/06/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRotasPorGrupoFaturamento(
			Integer idGrupoFaturamento) throws ErroRepositorioException;
	
	/**
	 * retorna cole��o de idImovel de uma Rota
	 * 
	 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Crian�a para Negocia��o a Vista
	 * 
	 * @author Vivianne Sousa
	 * @date 12/06/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosImoveisPorRota(Integer idRota) throws ErroRepositorioException ;
	
	/**
	 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Crian�a para Negocia��o a Vista
	 * 
	 * @author Vivianne Sousa
	 * @date 17/06/2009
	 */
	public void deletarCobrancaDocumentoECobrancaDocumentoItem(
			Integer idRota,Integer documentoTipo)throws ErroRepositorioException;
	
	 /**
	 * Seleciona os itens do documento de cobran�a correspondentes a debito a cobrar
	 * 
	 * [UC0910] Emitir Cartas da Campanha de Solidariedade da crian�a para Negocia��o a Vista
	 * 
	 * @author Vivianne Sousa
	 * @data 17/06/2009
	 */
	public BigDecimal selecionarValorTotalCobrancaDocumentoItemReferenteDebito(
			CobrancaDocumento cobrancaDocumento)throws ErroRepositorioException;
	
	/**
	 * [UC0910] Emitir Cartas da Campanha de Solidariedade da crian�a para Negocia��o a Vista
	 * 
	 * O sistema ordena a lista de documentos de cobran�a por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Vivianne Sousa
	 * @data 17/06/2009
	 * 
	 * @param idRota,
	 *            idDocumentoTipo
	 * @return Collection<CobrancaDocumento>
	 */
	 public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitir(
			Integer idRota, Integer idDocumentoTipo)throws ErroRepositorioException;
	 
	/**
	 * retorna a Data de Vencimento da Rota 
	 * (FACR_DTCONTAVENCIMENTO da tabela FATURAMENTO_ATIV_CRON_ROTA com ROTA_ID = ROTA_ID j� pesquisado antes 
	 * e FTAC_ID = FTAC_ID da tabela FATURAMENTO_ATIVIDADE_CRONOGRAMA e FTAT_ID = 5 
	 * e FTCM_ID = FTCM_ID da tabela FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTGR_ID = ao grupo informado 
	 * e FTCM_AMREFERENCIA = anoMesFaturamento da tabela SISTEMA_PARAMETROS )
	 * 
	 * [UC0910] Emitir Cartas da Campanha de Solidariedade da crian�a para Negocia��o a Vista
	 * 
	 * @author Vivianne Sousa
	 * @data 22/06/2009
	 */
	public Date pesquisarDataVencimentoRota(
			Integer idRota, Integer anoMesFaturamento, Integer grupoFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * 
	 *Esse metodo pesquisa as faturas de um cliente respons�vel federal, de acordo
	 *com o Mes/Ano, de acordo com o id do Cliente
	 * 
	 * @author Jose Guilherme Macedo Vieira
	 * @date 08/07/2009
	 *
	 * @param Integer anoMes
	 * @param Integer clienteID
	 * @return Collection - uma colecao de objetos Fatura
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaFaturaClienteResponsavelFederal(
			Integer anoMes,
			Integer clienteID) throws ErroRepositorioException ;
	
	
	/**
	 * 
	 *Pesquisa por todos os impostos a partir de uma fatura de um cliente responsavel federal
	 *
	 *OBS: O id da fatura passado tem que ser de uma fatura de um cliente responsavel federal
	 *
	 * @author Jose Guilherme Macedo Vieira
	 * @date 08/07/2009
	 *
	 * @param Integer idFatura
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaImpostoFaturaClienteResponsavelFederal(Integer anoMes, Integer idCliente) 
						throws ErroRepositorioException ;	
	
	/**
	 * 
	 * Esse m�todo est� de acordo com o UC0919 - Gerar Relat�rio de Impostos Por Cliente Respons�vel,
	 * no caso do Relat�rio Anal�tico o qual necessita recuperar os Im�veis que est�o associados a
	 * uma Fatura (cujo id � passado como par�metro Integer), a partir da tabela fatura_item.  
	 *
	 * @author Jose Guilherme Macedo Vieira
	 * @date 13/07/2009
	 *
	 * @param Integer idFatura
	 * @return Collection<Imovel> - a colecao de imoveis associadas a uma fatura
	 * @throws ErroRepositorioException
	 */
	public Collection<Imovel> pesquisarImoveisFaturaClienteResponsavel(Integer idFatura)
	 throws ErroRepositorioException;
	
	/**
	 * 
	 * Esse m�todo est� de acordo com o UC0258 - Filtrar Documentos de Cobran�a,
	 * retorna conjunto de CAAC_ID(a��es do ciclo) selecionados 
	 *
	 * @author Anderson Italo
	 * @date 03/08/2009
	 *
	 * @param Collection<Integer> idsAcao
	 * @param Integer anoMesReferencia
	 * @return Collection<CobrancaAcaoAtividadeCronograma> - a colecao de cobrancaAcaoAtividadeCronograma associados 
	 * as acoes e anoMesReferencia Informados
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarAcoesCiclo(Collection<Integer> idsAcao, Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * Atualizar total de documentos, itens e valores realizados nos comandos
	 * de acao de cobranca
	 *  
	 * Data: 28/07/2009
	 * @author Francisco do Nascimento
	 * 
	 * @param idCAAC Identificador de CobrancaAcaoAtividadeCronograma
	 */
	public Object[] calcularTotaisCronogramaAcaoCobranca(Integer idCAAC) 
		throws ErroRepositorioException;	

	/**
	 * 
	 *Pesquisa todas as localidades do cicloMeta agrupando por localidade, unidade e gerencia.
	 *
	 *
	 * @author Genival Barbosa
	 * @date 03/08/2009
	 *
	 * @param Integer idFatura
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public List consultarColecaoCicloMetaGrupoRelatorio(Integer idCicloMeta) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa
	 * - Pesquisa dados da cobran�a 
	 * @author Hugo Amorim
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarValorTotalCobranca
		(Integer idComando,Date dateInicial, Date dateFinal) throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa
	 * - Pesquisa dados da cobran�a criterio
	 * @author Hugo Amorim
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarValorTotalCobrancaCriterio
		(Integer idComando,Date dateInicial, Date dateFinal) throws ErroRepositorioException;
	
	/**
	 *  Pesquisa o acr�scimo da maneira correta
	 *  
	 * Data: 07/09/2009 
	 * Author: Rafael Corr�a
	 * 
	 * @param anoMesReferenciaDebito
	 *            Ano M�s de Referencia de D�bito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarMenorIgualIndiceAcrescimoImpontualidade(
			int anoMesReferenciaDebito) throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa
	 * - Pesquisa dados do popup da cobran�a
	 * @author Hugo Amorim
	 * @throws ErroRepositorioException  
	 */
	public Collection pesquisarDadosPopup
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * Este m�todo est� de acordo com o UC[0258]Filtrar Documento de Cobranca,
	 * � utilizado pelo relat�rio filtrar documentos de cobran�a
	 * 
	 * @author Anderson Italo
	 * @date 19/08/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarCobrancaDocumento(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;
	
	/**
	 * Este m�todo est� de acordo com o [UC0906] Gerar Relat�rio de Acompanhamento das Supress�es, 
	 * Religa��es e Reestabelecimentos. � utilizado pelo relat�rio filtrar os registros do relatorio
	 * 
	 * @author Anderson Italo
	 * @date 28/08/2009
	 *
	 * @param FiltroSupressoesReligacoesReestabelecimentoHelper filtro
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarSupressoesReligacoesReestabelecimentos(FiltroSupressoesReligacoesReestabelecimentoHelper filtro) throws ErroRepositorioException;
	
	/**
	 * Este m�todo est� de acordo com o UC[0258]Filtrar Documento de Cobranca,
	 * � utilizado pelo relat�rio filtrar documentos de cobran�a para totalizar
	 * os registros filtrados
	 * 
	 * @author Anderson Italo
	 * @date 19/08/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarCobrancaDocumentoCount(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;

	/**
	 * 
	 * UC0905 - Gerar Relatorio Acompanhamento Acao Cobran�a
	 * 
	 * @author Genival Barbosa
	 * @date 26/08/2009
	 * 
	 * @param RelatorioAcompanhamentoAcoesCobrancaHelper helper
	 *            
	 * @return Lista de acoes de cobranca
	 */
	public List consultarColecaoAcaoCobranca(RelatorioAcompanhamentoAcoesCobrancaHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * Este m�todo est� de acordo com o UC[0258]Filtrar Documento de Cobranca
	 * 
	 * @author Anderson Italo
	 * @date 16/09/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List consultarDocumentosCobranca(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;
	
	/**
	 * [UC0959] Gerar Arquivo Texto de Pagamentos das Contas em Cobran�a por Empresa
	 * 
	 * @author: Hugo Amorim
	 * @date: 05/10/2009
	 */
	public Collection pesquisarDadosArquivoTextoPagamentosContasCobrancaEmpresa(
			Integer idEmpresa,Integer referenciaInicial, Integer referenciaFinal, 
			Integer quantidadeRegistros , Integer numeroIndice,
			Integer idUnidadeNegocio) throws ErroRepositorioException;
	
	public Collection obterUnidadeNegocioPagamentosEmpresaCobrancaConta() throws ErroRepositorioException ;

	/**
	 * [UC0745] Gerar Arquivo Texto para Faturamento
	 * 
	 * @author S�vio Luiz
	 */
	public CobrancaDocumento pesquisarCobrancaDocumentoImpressaoSimultanea(
			Date dataEmissao,Integer idImovel )
			throws ErroRepositorioException;

	/**
	 * [UC????] Relatorio Comando Documento Cobranca
	 * Alterado para verificar tipo da a��o a partir da tabela documento_tipo
	 * @author R�mulo Aur�lio, Anderson Italo
	 * 
	 * @data 20/10/2009, 04/05/2010
	 */
	public DocumentoTipo pesquisarTipoAcaoCobrancaParaRelatorio(
			Integer cobrancaAcaoAtividadeComando, Integer cobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException ;

	public Integer obterOrdemServicoAssociadaDocumentoCobranca(
			Integer idCobrancaDocumento) throws ErroRepositorioException;
	
	/**
	 *
	 * @author R�mulo Aur�lio
	 * @date 20/10/2009
	 *
	 * @param idCobrancaDocumento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public CobrancaDocumento pesquisarCobrancaDocumento(Integer idCobrancaDocumento) throws ErroRepositorioException ;
	
	/**
	 * Este m�todo est� de acordo com o [UC0901] Gerar Metas do Ciclo
	 * 
	 * @author Anderson Italo
	 * @date 21/09/2009
	 *
	 * @param Integer idCicloMeta
	 * @throws ErroRepositorioException
	 */
	public void removerCicloMetaGrupo(Integer idCicloMeta) throws ErroRepositorioException;

	
	/**
	 * retorna cole��o de idEmpresaCobranca de uma Rota
	 * 
	 * @author Arthur Carvalho
	 * @date 06/11/2009
	 * 
	 * @param id da localidade 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarEmpresaCobrancaDaRota(Integer idLocalidade) throws ErroRepositorioException;

	
	/**
	 * [UC0630] - Solicitar Emiss�o do Extrato de D�bitos
	 * Author: R�mulo Aur�lio
	 * Data: 05/11/2009
	 * 
	 * Obtem os d�bitos a cobrar que estejam com PARC_ID = NULL,
	 * com SITUACAO NORMAL E FINANCIAMENTO_TIPO = 2
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarParceladoComIDNulo(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * atualiza  o valor do documento e o valor de desconto do documento de cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 12/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorDocumentoEValorDescontoCobrancaDocumento(
			Integer idCobrancaDocumento, BigDecimal valorDocumento,
			BigDecimal valorDesconto)throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * pesquisa o documento de cobranca do imovel 
	 * e do documento tipo passado como parametro
	 * 
	 * @author Vivianne Sousa
	 * @date 19/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoDoImovel(
			Integer idImovel, Integer idDocumentoTipo)throws ErroRepositorioException;
	
	
	public void inserirCartaFinalAno(Integer idCobrancaGrupo,Integer idEmpresa,Integer idLocalidade,
			Integer codigoSetor,Integer numeroQuadra,Integer lote, Integer subLote,
			Integer sequencial,	String txt_parte1,String txt_parte2, Integer idRota) 
			throws ErroRepositorioException;
	
	public Collection pesquisarCartaFinalAnoGrupo(Integer idCobrancaGrupo) 
	throws ErroRepositorioException ;
	
	/**
	 * @author Vivianne Sousa
	 * @date 20/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarCartaFinalAno(Integer idRota) 
			throws ErroRepositorioException;
	
	/**
	 * @author Anderson Italo
	 * @date 26/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorGrupoCobranca(Integer idCobrancaGrupo, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra) throws ErroRepositorioException;
	
	/**
	 * @author Anderson Italo
	 * @date 30/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorComandoEventual(Integer idCobrancaAcaoAtividadeComando, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra) throws ErroRepositorioException;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [FS0008] � Verificar validade da data
	 *
	 * @author Raphael Rossiter
	 * @date 07/01/2010
	 *
	 * @param idCliente
	 * @param idArrecadacaoForma
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroDiasFloatCartao(Integer idCliente, Integer idArrecadacaoForma) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 11/01/2010
	 *
	 * @param idCliente
	 * @param dataVencimento
	 * @return GuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public GuiaPagamento pesquisarGuiaPagamentoCartaoCredito(Integer idCliente, Date dataVencimento) throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @param guiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarGuiaPagamentoCartaoCredito(GuiaPagamento guiaPagamento) throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @return Localidade
	 * @throws ErroRepositorioException
	 */
	public Localidade pesquisarLocalidadeSede() throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @return DebitoTipo
	 * @throws ErroRepositorioException
	 */
	public DebitoTipo pesquisarDebitoTipoCartaoCredito() throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0004] � Incluir Dados da Confirma��o dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 18/01/2010
	 *
	 * @param idArrecadador
	 * @param dataLancamento
	 * @return AvisoBancario
	 * @throws ErroRepositorioException
	 */
	public AvisoBancario pesquisarAvisoBancario(Integer idArrecadador, Date dataLancamento) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0005  � Calcular Valor da  Dedu��o]
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idArrecadador
	 * @param idArrecadacaoForma
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ArrecadadorContratoTarifa pesquisarArrecadadorContratoTarifa(Integer idArrecadador, 
			Integer idArrecadacaoForma) throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0004] � Incluir Dados da Confirma��o dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 28/04/2010
	 *
	 * @param avisoBancario
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorAvisoBancario(AvisoBancario avisoBancario) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0004] � Incluir Dados da Confirma��o dos Pagamentos 
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idAvisoBancario
	 * @return
	 * @throws ErroRepositorioException
	 */
	public AvisoDeducoes pesquisarAvisoDeducoes(Integer idAvisoBancario) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0004] � Incluir Dados da Confirma��o dos Pagamentos 
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idAvisoBancario
	 * @param valorTotalAvisoDeducoes
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorAvisoDeducoes(Integer idAvisoBancario, BigDecimal valorTotalAvisoDeducoes) 
		throws ErroRepositorioException ;
	/**
	 *  [UC0349] Emitir Documento de Cobran�a � Aviso de Corte
	 * 
	 * inserir na tabela temporaria os dados para gerar os arquivos do aviso de corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDocumentoCobrancaImpressao(Integer idCobrancaDocumento,
			String linhaTxt, Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma, Integer sequencialImpressao)throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a � Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDocumentoCobrancaImpressao(
			Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a � Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDocumentoCobrancaImpressao(Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a � Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDocumentoCobrancaImpressao(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0987] Inserir Faixa de Dias Vencidos para Documentos a Receber
	 * 
	 * Verificar se existe Faixa inicial j� cadastrada.
	 * 
	 * @author Hugo Leonardo
	 * @param  valorInicialFaixa
     * @throws ControladorException 
	 * @data  22/02/2010
	 *
	 * @return String
	 */
	public String verificarExistenciaFaixaInicial(Integer valorInicialFaixa) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0987] Inserir Faixa de Dias Vencidos para Documentos a Receber
	 * 
	 * Verificar se existe Faixa final j� cadastrada.
	 * 
	 * @author Hugo Leonardo
	 * @param  valorFinalFaixa
     * @throws ControladorException 
	 * @data  22/02/2010
	 *
	 * @return Boolean
	 */
	public Integer verificarExistenciaFaixaFinal(Integer valorFinalFaixa) 
		throws ErroRepositorioException;
	
	
	/** 
	 * [UC990] Gerar Relat�rio de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @param quantidadeMaxima 
	 * @param quantidadeInicio 
	 * @date 22/02/2010
	 *
	 */
	public Collection pesquisarRelatorioDocumentosAReceber(
			FiltroRelatorioDocumentosAReceberHelper helper,String tipoTotalizacao, int quantidadeInicio, int quantidadeMaxima
			) throws ErroRepositorioException;
	/** 
	 * [UC990] Count Relat�rio de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @date 22/02/2010
	 *
	 */
	public Integer countRelatorioDocumentosAReceber(
			FiltroRelatorioDocumentosAReceberHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * [UC????] Relatorio Comando Documento Cobranca
	 * Retorna a a��o de cobran�a para exibi��o 
	 * de parametros do relat�rio
	 * 
	 * @author Anderson Italo
	 * @data 04/05/2010
	 */
	public CobrancaAcao pesquisarAcaoCobrancaParaRelatorio(
			Integer cobrancaAcaoAtividadeComando, Integer cobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException;


//	/**
//	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
//	 * 
//	 * @author Vivianne Sousa
//	 * @date 07/04/2010
//	 */
//	public void inserirCobrancaDocumentoControleGeracao(
//			Integer idProcesso,
//			Integer quantidadeCobrancaDocumento, 
//			Integer quantidadeCobrancaDocumentoItem,
//			BigDecimal valorTotalCobrancaDocumento) 
//			throws ErroRepositorioException;

	/**
	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarQuantidadeCobrancaDocumento(
			Integer idCobrancaDocumentoControleGeracao, 
			Integer quantidadeCobrancaDocumento) throws ErroRepositorioException;

	
	/**
	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSomar(
			Integer idCobrancaDocumentoControleGeracao,
			Integer quantidadeCobrancaDocumento, 
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento)  throws ErroRepositorioException;

	/**
	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public Integer pesquisarQuantidadeCobrancaDocumento(
			Integer idCobrancaDocumentoControleGeracao) throws ErroRepositorioException;
	
	/**
	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSomar(
			Integer idCobrancaDocumentoControleGeracao,
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento)  throws ErroRepositorioException;
	
	/**
	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public CobrancaDocumentoControleGeracao pesquisarCobrancaDocumentoControleGeracao(
			Integer idCobrancaDocumentoControleGeracao) throws ErroRepositorioException;
	/**
	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 19/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracao(
			Integer quantidadeCobrancaDocumento, 
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento,
			Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma)
	throws ErroRepositorioException;

	/**
	 *  [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Vivianne Sousa
	 * @date 19/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSubtrair(
			Integer idCobrancaDocumentoControleGeracao,
			Integer quantidadeCobrancaDocumento, 
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento)
	throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 29/04/2010
	 */
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 03/05/2010
	 */
	public Collection pesquisarDadosImovelCobrancaSituacaoPorImovel(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * 	Atualiza valores do Documento de Cobran�a
	 * de cartas.
	 * 
	 * @author Hugo Amorim
	 * @data 29/04/2010
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarValoresDocumentoCobrancaCartas(Integer id,
			BigDecimal descontoTotalPagamentoAVista,
			BigDecimal valorTotalImpostosConta) throws ErroRepositorioException;
	
	/**
	 * Author: Arthur Carvalho
	 * Data: 14/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTodosParcelamentosSituacaoNormal(String situacao) 
		throws ErroRepositorioException;

	
	
	/**
	 * [UC0998] Gerar Rela��o de Parcelamento - Vis�o Cart�o de Cr�dito
	 * 
	 * Bean que preencher� o relatorio
	 * 
	 * @author Hugo Amorim
	 * @date 11/06/2010
	 *
	 */
	public Collection<Object[]> filtrarRelacaoParcelamentoCartaoCredito(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1038] Prescrever D�bitos de Im�veis
	 * 
	 * @author Hugo Leonardo
	 * @date 07/07/2010
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	/**TODO: COSANPA
	 * Altera��es para atender ao Mantis 490
     * Considerar a data de vencimento da conta ao inv�s de considerar o anoMes referencia
     * da mesma
     * @author Wellington Rocha
     * @date 25/01/2012*/
	public void prescreverDebitosDeImoveis(Integer anoMesFaturamento, String dataFormatada, 
			Integer usuario) throws ErroRepositorioException;
	
	/**TODO: COSANPA
	 * Mantis 490: M�todo criado para prescrever d�bitos de im�veis com contas inclu�das
	 * 
	 * @author Wellington Rocha
	 * @date 02/02/2012*/
	public void prescreverDebitosDeImoveisContasInlcuidas(Integer anoMesFaturamento, String dataFormatada, 
			Integer usuario) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1038] Prescrever D�bitos de Im�veis
	 * 
	 * @author Hugo Leonardo
	 * @date 07/07/2010
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection obterCobrancaSituacaoParaPrescreverDebitos() throws ErroRepositorioException;
	

	/**
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * @author Hugo Amorim
	 * @created 14/07/2010
	 *
	 * @exception ErroRepositorioException
	 *             
	 */
	public void removerCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando) throws ErroRepositorioException;
	
	/**
	 * Obtem os percentuais de desconto por tempo de inatividade a vista
	 * 
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 20/07/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcDesctoInativVista obterPercentualDescontoInatividadeAVista(
			Integer idPerfilParc, int qtdeMeses)throws ErroRepositorioException;
	
	/**
	 * [UC1038] Prescrever D�bitos de Im�veis
	 * Pesquisa imoveis para execu��o do batch
	 * 
	 * @author Hugo Leonardo
	 * @date 19/07/2010
	 */
	public Collection obterContasPrescreverDebitosDeImoveis(Integer idLocalidade, 
			Integer anoMesFaturamento, String idsCobrancaSituacao, 
			int numeroIndice, int quantidadeRegistros) throws ErroRepositorioException;
	
	

	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * Atualizar total de documentos, itens e valores realizados nos comandos
	 * de acao de cobranca
	 *  
	 * Data: 19/07/2010
	 * @author Arthur Carvalho
	 * 
	 * @param idCACM Identificador de CobrancaAcaoAtividadeComando
	 */
	public Object[] calcularTotaisComandoAcaoCobranca(Integer idCAAC) 
			throws ErroRepositorioException;
	
	/**
	 * 
	 * atualiza a forma da emiss�o do documento de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 12/08/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarFormaEmissaoCobrancaDocumento(
			Integer idCobrancaDocumento)throws ErroRepositorioException;
	
	/**
	 * Pesquisa Documentos de cobran�as validos para imovel para determinado tipo de documento
	 * 
	 * @author Hugo Amorim
	 * @date 09/09/2010
	 */
	public Collection<CobrancaDocumento> pesquisarDadosCobrancaDocumentoValidoImovel(Integer idImovel,Integer idDocumentoTipo,
			Integer idAcaoCobranca)throws ErroRepositorioException;
	

	/**
	* [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	*
	* Data: 16/09/2010
	* @author Vivianne Sousa
	*/
	public Integer pesquisarCobrancaDocumentoFisc(
		Integer idFiscalizacaoSituacao,
		Integer idOrdemServico,
		Integer idCobrancaDocumento) throws ErroRepositorioException;
	
	/**
	 * [UC0852] � Incluir D�bito a Cobrar de Entrada de Parcelamento N�o Paga
	 * 
	 * @author Arthur Carvalho
	 * @date 22/09/2010
	 * @param parcelamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagamentoDoParcelamentoPorEntradaNaoPaga(String parcelamento)
			throws ErroRepositorioException;
	
	/**
	 * 
	 *Pesquisa por todos os impostos a partir de uma fatura de um cliente responsavel federal
	 *
	 *OBS: O id da fatura passado tem que ser de uma fatura de um cliente responsavel federal
	 *
	 * @author Fernando Fontelles
	 * @date 24/09/2010
	 *
	 * @param Integer anoMes
	 * @param Integer idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaImpostoFaturaClienteResponsavelFederalAnalitico(Integer anoMes, Integer idCliente) 
						throws ErroRepositorioException ;

	/**
	 * [UC1112] Processar  Encerramento Ordens de Servi�o da A��o de Cobran�a
	 * 
	 * @author Mariana Victor
	 * @created 02/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Integer> pesquisarOrdemServicoParaEncerrar(Integer idCobrancaAcaoCronograma)
			throws ErroRepositorioException;
	
	/**
	 * [UC1112] Processar  Encerramento Ordens de Servi�o da A��o de Cobran�a
	 * 
	 * @author Mariana Victor
	 * @created 07/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarAtividadeCronograma(Integer idCobrancaAcao)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarLocalidade(int idOrdemServico)
			throws ErroRepositorioException;
	
	/**
	 * [UC1112] Processar  Encerramento Ordens de Servi�o da A��o de Cobran�a
	 * 
	 * @author Mariana Victor
	 * @created 10/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarAtivCronogOrdemServicoParaEncerrar(Integer idAtividadeCronograma)
			throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a 
	 * [SB0003] Gerar Atividade de A��o de Cobran�a para Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	**/
	public Collection pesquisarIdDocumentoCobrancaParaImovel(Integer idImovel, 
			Integer idDocumentoTipo)throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a 
	 * [SB0003] Gerar Atividade de A��o de Cobran�a para Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	**/
	public Integer pesquisarQtdeDocumentoCobrancaItemConta(Collection idsCobrancaDocumento, 
			Collection colecaoConta)throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a 
	 * [SB0003] Gerar Atividade de A��o de Cobran�a para Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	**/
	public Integer pesquisarQtdeDocumentoCobrancaItemDebitoACobrar(Collection idsCobrancaDocumento, 
			Collection colecaoDebitoACobrar)throws ErroRepositorioException ;
	/**
	 * [UC676] Consultar Resumo Negativa��o
	 * 
	 * @author Ivan Sergio
	 * @date 14/01/2011
	 * 
	 * @param dadosConsultaNegativacaoHelper
	 * @param idSituacaoDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarNegativacaoLigacaoAguaPorSituacaoDebito(
			DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int idSituacaoDebito)
			throws ErroRepositorioException;
	
	/**
	 *  [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 20/01/2011
	**/
	public Integer pesquisarQuantidadeContasDebito(Integer idCobrancaDocumento)
		throws ErroRepositorioException;
	
	/**
	 *  [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 20/01/2011
	**/
	public List<String> pesquisarTipoDeCorte()
		throws ErroRepositorioException;
	

	/**
	 *  [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 26/01/2011
	**/
	public List<String> pesquisarOcorrenciasFiscalizacao()
		throws ErroRepositorioException;
	
	
	/**
	 *  [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author R�mulo Aur�lio
	 * @created 17/02/2011
	**/
	
	public void atualizarDataRealizacaoCobrancaAcaoAtivCronograma(Integer idCobAcaoAtivCron) throws ErroRepositorioException;
	

	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * Seleciona os itens do documento de cobran�a correspondentes a guia
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteGuia(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * Seleciona os itens do documento de cobran�a correspondentes a d�bito � cobrar
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteDebitoACobrar(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * Seleciona os itens do documento de cobran�a correspondentes a credito � cobrar
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteCreditoACobrar(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 *  [UC0968] Emitir Cartas da Campanha de Final de Ano 2009
	 * 
	 * inserir na tabela temporaria os dados para gerar os arquivos do aviso de corte
	 * 
	 * @author Mariana Victor
	 * @date 17/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDocumentoCobrancaImpressaoFichaCompensasao(Integer idCobrancaDocumento,
			String linhaTxt, String conteudoFichaCompensacao, Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma, Integer sequencialImpressao) 
			throws ErroRepositorioException;

	/**
	 * [UC0968] Emitir Cartas da Campanha de Final de Ano 2009
	 * 
	 * @author Mariana Victor
	 * @date 17/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDocumentoCobrancaImpressaoFichaCompensacao(
			Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma) 
			throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa por todos os impostos arrecadados a partir de um m�s de refer�ncia
	 * de um determinado cliente federal ou de TODOS os clientes federais.
	 *
	 * @author Diogo Peixoto
	 * @date 23/03/2011
	 *
	 * @param Integer anoMesFerencia
	 * @param Integer idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImpostosArrecadacaoClienteResponsavelFederal(Integer anoMesFerencia, Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 22/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarItemServicoContrato(
			Integer idGrupoCobranca) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletim(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimento(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepAsfalto(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepParalalo(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepCalcada(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	

	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDesconto(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDescontoSemDecursoPrazo(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarSituacaoAtualContaPeloCronogramaCobranca(Integer idGrupoCobranca, Integer referencia)
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public void atualizaIndicadorBoletimOS(Collection idsOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public void atualizaIndicadorCobrancaAcaoOSNaoAceitas(Collection idsOSNaoAceitas)
			throws ErroRepositorioException; 
	
	/**
	 * 
	 * Pesquisa por todos os impostos arrecadados a partir de um m�s de refer�ncia
	 * de um determinado cliente federal ou de TODOS os clientes federais.
	 *
	 * @author Diogo Peixoto
	 * @date 24/03/2011
	 *
	 * @param Integer anoMesFerencia
	 * @param Integer idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImpostosArrecadacaoClienteResponsavelFederalAnalitico(Integer anoMesFerencia, Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 *  [UC1153] Solicitar Gera��o/Emiss�o Boletim de Medi��o de Cobran�a
	 *  
	 *  [FS0002] � A��es n�o encerradas no cronograma.
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Integer pesquisarAcoesEncerradasCronograma(Integer anoMesReferencia, Integer idCobrancaGrupo)
	 		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  Pesquisa os Itens de Servi�o relacionados ao boletim de medi��o de cobran�a selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimDesconto(Integer anoMesReferencia, Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal)
		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  Pesquisa os Itens de Servi�o relacionados ao boletim de medi��o de cobran�a selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimExecutados(Integer anoMesReferencia, Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal)
		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  Pesquisa os Itens de Servi�o relacionados ao boletim de medi��o de cobran�a selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimSucesso(Integer anoMesReferencia, Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal)
		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  De acordo com o c�digo da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	**/
	public Object[] obterQuantidadeOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  De acordo com o c�digo da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	**/
	public Object[] obterSomatorioOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  De acordo com o c�digo da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	**/
	public Object[] obterQuantidadeOSBoletimMedicaoCobrancaDesconto(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  Consulta os valores da totaliza��o da taxa de sucesso.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	**/
	public Object[] obterTotalizacaoOSBoletimMedicaoCobrancaSucesso(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  Pesquisa dados da empresa e do contrado do boletim de cobran�a
	 * 
	 * @author Mariana Victor
	 * @created 24/03/2011
	**/
	public Object[] pesquisarDadosBoletimMedicaoCobranca(Integer anoMesReferencia, Integer idCobrancaGrupo)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Relat�rio de An�lise de Perdas com Cr�dito
	 * 
	 * [UC1155] Gerar Relat�rio de An�lise de Perdas com Cr�dito
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param mesAno para an�lise
	 * @throws ErroRepositorioException
	 */
	public RelatorioAnalisePerdasCreditosBean gerarRelatorioAnalisePerdasCreditos(String anoMesReferencia)
		throws ErroRepositorioException;
	
	/**
	 * Retorna o maior ano mesReferencia da tabela docs_a_rec_resumo
	 * 
	 * [UC1155] Gerar Relat�rio de An�lise de Perdas com Cr�dito
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param mesAno para an�lise
	 * @throws ErroRepositorioException 
	 * @throws ErroRepositorioException
	 */
	public int maiorAnoMesReferenciaDocumentosAReceberResumo()throws ErroRepositorioException;
	

	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection<BigDecimal> pesquisarValorContaouContaHistorico(Integer idImovel, Integer referencia)
			throws ErroRepositorioException;
	
	/**
	 * [UC0870] Gerar Movimento de Contas em Cobran�a por Empresa
	 * 
	 * Pesquisa a quantidade de contas associadas ao im�vel
	 * 
	 * @author: Mariana Victor
	 * @date: 13/04/2011
	 */
	public Integer pesquisarQuantidadeContasEmCobrancaPorImovel(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Integer idImovel, SistemaParametro sistemaParametro) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoImovelPerfil(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoGerenciaRegional(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoUnidadeNegocio(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 13/04/2011
	 */
	public Integer pesquisarQuantidadeContasArquivoTextoContasCobrancaEmpresa(
			Collection ids, Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * Pesquisa a Situa��o de cobran�a a partir do c�digo constante.
	 * 
	 * @author: Mariana Victor
	 * @date: 18/04/2011
	 */
	public Integer pesquisarCobrancaSituacao(Integer codigoConstante) throws ErroRepositorioException;
	public boolean pesquisarDebitoCobradoParcelamento(Integer codigoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * @author R�mulo Aur�lio
	 * @throws ErroRepositorioException 
	 * @date 12/05/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamento(
			String numeroParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * @author R�mulo Aur�lio
	 * @throws ErroRepositorioException 
	 * @date 12/05/2011
	 */
	public Collection pesquisarDebitoContratoParcelamentoPorTipoDocumento(
			ContratoParcelamento contratoParcelamento, Integer idDocumentoTipo) throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * 
	 * Pesquisa os dados dos comandos
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 */
	public Collection pesquisarDadosConsultarComandosContasCobrancaEmpresaResumido(
			Integer idEmpresa, Date cicloInicial, Date cicloFinal, int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * 
	 * Pesquisa os dados de um comando para exibir no popup
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 * @throws ErroRepositorioException  
	 */
	public Collection pesquisarDadosPopupExtensaoComandoCobranca
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * 
	 * - Pesquisa dados da cobran�a
	 * 
	 * @author: Mariana Victor
	 * @date: 06/05/2011
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection pesquisarValorTotalCobrancaComandoEmpresa
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * 
	 * Pesquisa a quantidade de contas, agrupando por im�vel
	 * 
	 * @author: Mariana Victor
	 * @date: 06/05/2011
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection pesquisarValorTotalCobrancaComandoEmpresaPorImovel
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobran�a por Empresa
	 * 
	 * @author Mariana Victor
	 * @data 09/05/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarIndicadorGeracaoTxt(Collection idsComandos)
		throws ErroRepositorioException;
	
	/**
	 * [UC1168] Encerrar Comandos de Cobran�a por Empresa
	 *
	 * Pesquisa os ids dos im�veis e das ordens de servi�os geradas para um determinado comando 
	 *
	 * @author Mariana Victor
	 * @created 09/05/2011
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection<Object[]> pesquisarImovelOrdemServicoParaEncerrarComando
		(int quantidadeInicio, Integer idComando) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1168] Encerrar Comandos de Cobran�a por Empresa
	 * 
	 * @author Mariana Victor
	 * @data 09/05/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarDataEncerramentoComando(Integer idComando) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Gerar OS
	 * 
	 * @author Mariana Victor
	 * @data 17/05/2011
	 */
	public Collection<Integer> pesquisarIdsImoveis(MovimentarOrdemServicoGerarOSHelper helper) throws ErroRepositorioException;

	/**
	 * [UC1136] Inserir Contrato Parcelamento Por Cliente
	 * 
	 * Pesquisa a Referencia da conta por ID
	 * 
	 * @author: Paulo Diniz
	 * @date: 14/05/2011
	 */
	public Integer pesquisarReferenciaContaPorId(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * [UC1136] Inserir Contrato Parcelamento Por Cliente
	 * 
	 * Verifica Conta Vinculada A Contrato Parcelamento Ativo
	 * 
	 * @author: Paulo Diniz
	 * @date: 14/05/2011
	 */
	public boolean verificaContaVinculadaAContratoParcelAtivo(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * [UC1136] Inserir Contrato Parcelamento Por Cliente
	 * 
	 * Verifica Guia Vinculada A Contrato Parcelamento Ativo
	 * 
	 * @author: Paulo Diniz
	 * @date: 14/05/2011
	 */
	public boolean verificaGuiaVinculadaAContratoParcelAtivo(Integer idGuia) throws ErroRepositorioException;
	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Emitir OS Gerada pela Empresa
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Object[]> pesquisarDadosOSGeradasPelaEmpresa(Integer idComando,
			Integer idTipoServico) throws ErroRepositorioException;

	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Emitir OS de Registro de Atendimento
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Object[]> pesquisarDadosOSRegistroAtendimento(Integer idComando, Integer idTipoServico) throws ErroRepositorioException;
	
	/**
	 * obtem contas em d�bito do im�vel, comparando a data de vencimento original
	 * usado no emitir contas da CAEMA
	 * 
	 * Author: Vivianne Sousa
	 * Data: 15/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImovelDataVencimentoOriginal(
			Integer idImovel,
			int indicadorPagamento, 
			int indicadorConta,
			String contaSituacaoNormal, 
			String contaSituacaoRetificada,
			String contaSituacaoIncluida, 
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, 
			Date anoMesFinalVencimentoDebito, 
			int indicadorDividaAtiva)
			throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobran�a
	 * 
	 * Consulta chamada pelo "[FS0006 � Validar Comando]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public ComandoEmpresaCobrancaConta pesquisarComandoEmpresaCobrancaConta(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobran�a
	 * 
	 * Consulta chamada pelo "[FS0007 � Validar OS]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Short pesquisarSituacaoOrdemServico(Integer numeroOS) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobran�a
	 * 
	 * Consulta chamada pelo "[FS0007 � Validar OS]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Boolean verificarOrdemServicoComando(Integer numeroOS, Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas
	 * 
	 * @author Paulo Diniz
	 * @throws ErroRepositorioException 
	 * @data 30/06/2011
	 */
	public List<Object[]> pesquisarOrdensServicoContasPagasParceladas() throws ErroRepositorioException;
	
	/**
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
	 * Pesquisar EmpresaCobrancaConta a partir do im�vel
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	public Collection obterColecaoEmpresaCobrancaContaResultadoporImovel(Integer id) throws ErroRepositorioException;
	
	/**
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas	
	 * @author Mariana Victor, Ana Maria	 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	 
	 * */	
	public Collection<Object[]> pesquisarEmpresasComandosCobrancaAtivosExecutados() throws ErroRepositorioException;	
	
	/**	
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas
	 * 	 
	 * 2. O sistema dever� verificar todos os comandos de cobran�a por resultados ativos e executados 	
	 * 
	 * @author Mariana Victor, Ana Maria	 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	
	 * */	
	public Collection<Integer> pesquisarComandosCobrancaAtivosExecutados(Integer idEmpresa) throws ErroRepositorioException;		
	
	/**	
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas	
	 * 
	 * 2.1.	Para cada comando selecionado o sistema dever� selecionar os ordens de servi�o ativas associadas ao mesmo 	 
	 * 
	 * @author Mariana Victor, Ana Maria	 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	 */	
	public Collection<Object[]> pesquisarOrdensServicoAtivasComando(Integer idComando) throws ErroRepositorioException;	
	
	/**	 
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas	 
	 * 2.1.1. Para cada Ordem de Servi�o selecionada, o sistema dever� verificar se as contas associadas ao im�vel da ordem de servi�o est�o quitadas ou parceladas  	 
	 * 
	 * @author Mariana Victor, Ana Maria	 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	 
	 * */	
	public Boolean verificarExisteContasEmAberto(Integer idOS) throws ErroRepositorioException;

	/**
     * [UC0067] Obter D�bito do Im�vel ou Cliente
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param idDebitoACobrar
	 * 
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificaDebitoACobrarVinculadoAContratoParcelAtivo(Integer idDebitoACobrar) throws ErroRepositorioException;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * [SB0020] - Processar Pagamento de Contrato Parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 03/08/2011
	 */
	public Object[] obterDadosDocumentoCobrancaItemContratoParcelamento(Integer idPrestacao) 
		throws ErroRepositorioException;

	/**
	 * [UC 0869] Gerar Arqv Texto das Contas em Cobran�a por Empresa
	 * 
	 * @author Paulo Diniz
	 * @data 03/08/2011
	 * 
	 */
	public Object[] pesquisarDadosQtdContasEDiasVencidos(Integer idComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * Pesquisa dados do popup
	 * 
	 * @author Hugo Azevedo
	 * @date 25/08/2011
	 */
	
	public Collection pesquisarDadosPopupExtensaoComandoAguaSituacao(
			Integer idComando) throws ErroRepositorioException;

	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepAsfaltoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepParalaloRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepCalcadaRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDescontoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medi��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDescontoSemDecursoPrazoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC 0869] Gerar Arqv Texto das Contas em Cobran�a por Empresa
	 * 
	 * @author Paulo Diniz
	 * @data 03/08/2011
	 * 
	 */
	public Collection<CmdEmpresaCobrancaContaLigacaoAguaSituacao> pesquisarColecaoLigacaoAguaSituacaoPorComandoEmpresaCobrancaConta(Integer idComando)
	throws ErroRepositorioException;
	
	/**
	 * [UC0067] Obter D�bito do Im�vel ou Cliente
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2011
	 * 
	 * @param idImovel
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short obterIndicadorAcrescimosClienteResponsavel(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * [UC0067] Obter D�bito do Im�vel ou Cliente
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2011
	 * 
	 * @param idImovel
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short obterIndicadorAcrescimosCliente(Integer idCliente) throws ErroRepositorioException ;
	
	

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoEmitirEtiquetasEnderecoAlternativo(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao)
			throws ErroRepositorioException;
	
	public void atualizarDocumentoDeCobrancaHistorico(Integer codigoImovel, Integer codigoParcelamento) throws ErroRepositorioException;
	
	
	
}
