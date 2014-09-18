package gcom.cobranca;

import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.CalcularValorDataVencimentoAnteriorHelper;
import gcom.cobranca.bean.CobrancaAcaoHelper;
import gcom.cobranca.bean.CobrancaCronogramaHelper;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.cobranca.bean.ConcluirParcelamentoDebitosHelper;
import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.DadosPesquisaCobrancaDocumentoHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarRelacaoParcelamentoHelper;
import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.cobranca.bean.GerarAtividadeAcaoCobrancaHelper;
import gcom.cobranca.bean.GerarResumoAcoesCobrancaCronogramaHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDadosConfirmarCartaoCreditoDebitoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.bean.ParcelamentoCartaoCreditoHelper;
import gcom.cobranca.bean.ParcelamentoRelatorioHelper;
import gcom.cobranca.bean.PesquisarQtdeRotasSemCriteriosParaAcoesCobranca;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.cobranca.bean.TransferenciasDebitoHelper;
import gcom.cobranca.cobrancaporresultado.ConsultarComandosContasCobrancaEmpresaHelper;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ImpostoDeduzidoHelper;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEncerrarOSHelper;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoGerarOSHelper;
import gcom.gui.relatorio.cobranca.FiltroRelatorioDocumentosAReceberHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.relatorio.cobranca.FiltrarRelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioAcompanhamentoAcoesCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioAnalisePerdasCreditosBean;
import gcom.relatorio.cobranca.RelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioDocumentoCobrancaOrdemCorteBean;
import gcom.relatorio.cobranca.RelatorioDocumentoCobrancaOrdemFiscalizacaoBean;
import gcom.relatorio.cobranca.RelatorioEmitirDeclaracaoTransferenciaDebitoBean;
import gcom.relatorio.cobranca.RelatorioNotificacaoDebitoBean;
import gcom.relatorio.cobranca.RelatorioVisitaCobrancaBean;
import gcom.relatorio.cobranca.parcelamento.ExtratoDebitoRelatorioHelper;
import gcom.relatorio.cobranca.parcelamento.RelacaoParcelamentoRelatorioHelper;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoAnaliticoBean;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoCartaoCreditoBean;
import gcom.relatorio.faturamento.conta.RelatorioContaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.fileupload.FileItem;

/**
 * Interface Controlador Cobranca PADR�O
 * 
 * @author Raphael Rossiter
 * @date 26/06/2007
 */
public interface IControladorCobranca {

	/**
	 * [UC0178] Religar Automaticamente Im�vel Cortado BATCH - Permite a
	 * Religa��o autom�tica de im�veis cortados Author: Rafael Santos Data:
	 * 02/01/2006
	 * 
	 * @exception ControladorException
	 *                Controlador Execpetion
	 */
	public void religarAutomaticamenteImovelCortado()
			throws ControladorException;

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
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void religarImovelCortado(String id, String situacaoAguaLigado,
			Date dataReligacaoAgua) throws ControladorException;

	/**
	 * [UC0067] Obter D�bito do Im�vel ou Cliente Author: Rafael Santos Data:
	 * 04/01/2006 Permite a obten��o dos d�btiso de um im�vel ou de um cliente
	 * 
	 * @param indicadorDebito
	 *            Inidicador de d�bito(1 - Imovel e 2 - Cliente
	 * @param idImovel
	 *            Matricula do Imovel
	 * @param codigoCliente
	 *            Codigo do Cliente
	 * @param clienteRelacaoTipo
	 *            Rela��o do Imovel com o Cliente
	 * @param anoMesInicialReferenciaDebito
	 *            Per�odo de Referencia de D�bito - Inicial
	 * @param anoMesFinalReferenciaDebito
	 *            Per�odo de Referencia de D�bito - Final
	 * @param anoMesInicialVencimentoDebito
	 *            Per�odo de Vencimento de D�bito - Inicial
	 * @param anoMesFinalVencimentoDebito
	 *            Per�odo de Vencimento de D�bito - Final
	 * @param indicadorPagamento
	 *            Indicador de Pagamento
	 * @param indicadorConta
	 *            Indicador de Conta
	 * @param indicadorDebitoACobrar
	 *            Indicador de Debito a Cobrar
	 * @param indicadorCreditoARealizar
	 *            Indicador de Credito a Realizar
	 * @param indicadorNotasPromissorias
	 *            Indicador de Notas Promissorias
	 * @param indicadorGuiasPagamento
	 *            Indicador de Guias de Pagamento
	 * @param indicadorCalcularAcrescimoImpontualidade
	 *            Indicador de Calculasr Acrescimo por Inpontualidade
	 * @exception ControladorException
	 *                Controlador Execption
	 */
	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuCliente(
			int indicadorDebito, String idImovel, String codigoCliente,
			Short clienteRelacaoTipo, String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVencimentoDebito,
			Date anoMesFinalVencimentoDebito, int indicadorPagamento,
			int indicadorConta, int indicadorDebitoACobrar,
			int indicadorCreditoARealizar, int indicadorNotasPromissorias,
			int indicadorGuiasPagamento,
			int indicadorCalcularAcrescimoImpontualidade,
			Boolean indicadorContas) throws ControladorException;
	
	/**
	 * Permite a obten��o dos d�bitos de um im�vel ou de um cliente
	 * 
	 * [UC0067] Obter D�bito do Im�vel ou Cliente
	 * 
	 * @author Rafael Santos ,Rafael Santos, Pedro Alexandre
	 * @date 04/01/2006,22/03/2006,13/03/2007
	 * 
	 * @param indicadorDebito
	 * @param idImovel
	 * @param codigoCliente
	 * @param clienteRelacaoTipo
	 * @param anoMesInicialReferenciaDebito
	 * @param anoMesFinalReferenciaDebito
	 * @param anoMesInicialVencimentoDebito
	 * @param anoMesFinalVencimentoDebito
	 * @param indicadorPagamento
	 * @param indicadorConta
	 * @param indicadorDebitoACobrar
	 * @param indicadorCreditoARealizar
	 * @param indicadorNotasPromissorias
	 * @param indicadorGuiasPagamento
	 * @param indicadorCalcularAcrescimoImpontualidade
	 * @param indicadorContas
	 * @param indicadorDividaAtiva
	 * @return
	 * @throws ControladorException
	 */
	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuCliente(
			int indicadorDebito, String idImovel, String codigoCliente,
			Short clienteRelacaoTipo, String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVencimentoDebito,
			Date anoMesFinalVencimentoDebito, int indicadorPagamento,
			int indicadorConta, int indicadorDebitoACobrar,
			int indicadorCreditoARealizar, int indicadorNotasPromissorias,
			int indicadorGuiasPagamento,
			int indicadorCalcularAcrescimoImpontualidade,
			Boolean indicadorContas, int indicadorDividaAtiva) throws ControladorException;

	/**
	 * [UC0216] Calcular Acrescimo por Impontualidade Author: Rafael Santos
	 * Silva Data:05/01/2006 Calcula os acrescimmos por
	 * Impontualidade(multa,juros de mora e atualiza��o monetaria)
	 * 
	 * @param anoMesReferenciaDebito
	 * @param dataVencimento
	 * @param dataPagamento
	 * @param valorDebito
	 * @param indicadorMulta
	 * @return
	 * @throws ControladorException
	 */
	public CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade(
			int anoMesReferenciaDebito, Date dataVencimento,
			Date dataPagamento, BigDecimal valorDebito,
			BigDecimal valorMultasCobradas, short indicadorMulta,
			String anoMesArrecadacao, Integer idConta, Short indicadorArrecadacao) throws ControladorException;

	/**
	 * [UC0200] Inserir D�bito Autom�tico
	 * 
	 * @author Roberta Costa
	 * @created 04/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matr�cula do Imovel
	 * @param codigoBanco
	 *            C�digo do Banco
	 * @param codigoAgencia
	 *            C�digo da Ag�ncia
	 * @param identificacaoCliente
	 *            Identifica��o do Cliente no Banco
	 * @param dataOpcao
	 *            Data da Op��o
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public String inserirDebitoAutomatico(String matriculaImovel,
			String codigoBanco, String codigoAgencia,
			String identificacaoCliente, Date dataOpcao)
			throws ControladorException;

	public String removerDebitoAutomatico(String matriculaImovel, String codigoBanco, String codigoAgencia, String identificacaoCliente, Date dataOpcao) throws ControladorException;

	/**
	 * Consultar Dados do Cliente Imovel Vinculado Auhtor: Rafael Santos Data:
	 * 23/01/2006 [UC0179] Consultar Historico Medi��o Indiviualizada
	 * 
	 * @param inscricaoImovel
	 *            Inscri��o do Imovel
	 * @return Dados do Imovel Vinculado
	 * @throws ControladorException
	 */
	public Cliente consultarDadosClienteImovelUsuario(Imovel imovel)
			throws ControladorException;

	/**
	 * Consultar Historico Medi��o Individualizada Auhtor: Rafael Santos Data:
	 * 23/01/2006 [UC0179] Consultar Historico Medi��o Indiviualizada
	 * 
	 * @param imovelCondominio
	 *            Imovel Condominio
	 * @param anoMesFaturamento
	 *            Ano M�s Fauramento
	 * @return Cole��o deDados do Historico Medi��o Individualizada
	 * @throws ControladorException
	 */
	public Collection consultarHistoricoMedicaoIndividualizada(
			Imovel imovelCondominio, String anoMesFaturamento)
			throws ControladorException;

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
			ConsumoHistorico consumoHistorico) throws ControladorException;

	/**
	 * Consultar Dados Consumo Tipo Imovel Auhtor: Rafael Santos Data:
	 * 23/01/2006 [UC0179] Consultar Historico Medi��o Indiviualizada
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return
	 * @throws ControladorException
	 */
	public ConsumoTipo consultarDadosConsumoTipoConsumoHistorico(
			ConsumoHistorico consumoHistorico) throws ControladorException;

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
	public ConsumoHistorico obterConsumoHistoricoMedicaoIndividualizada(
			Imovel imovel, LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito
	 * 
	 * @author Fernanda Paiva
	 * @created 14/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAnterior
	 *            DebitoCreditoSituacaoAtual idConta
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarSituacaoConta(String codigoConta, int situacaoAtual,
			int anoMesReferenciaContabil) throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito
	 * 
	 * @author Fernanda Paiva
	 * @created 15/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idGuiaPagamento
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarSituacaoGuiaPagamento(String codigoGuiaPagamento,
			int situacaoAtualGuia, int anoMesReferenciaContabil)
			throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito
	 * 
	 * @author Fernanda Paiva
	 * @created 20/02/2006
	 * 
	 * @param idParcelamento
	 *            motivo parcelamentoSituacao
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarParcelamento(Integer codigoParcelamento,
			Integer parcelamentoSituacao, String motivo,Integer usuarioId)
			throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito
	 * 
	 * @author Fernanda Paiva
	 * @created 16/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idDebitoACobrar
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarSituacaoDebitoACobrar(String codigoDebitoACobrar,
			int situacaoAtualDebito, int anoMesReferenciaContabil)
			throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito
	 * 
	 * @author Fernanda Paiva
	 * @created 16/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idCreditoARealizar
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarSituacaoCreditoARealizar(
			String codigoCreditoARealizar, int situacaoAtualCredito,
			int anoMesReferenciaContabil) throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito - remover debito a cobrar referente
	 * ao parcelamento
	 * 
	 * @author Fernanda Paiva
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerDebitoACobrarDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito - remover credito a realizar
	 * referente ao parcelamento
	 * 
	 * @author Fernanda Paiva
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerCreditoARealizarDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito - remover guia pagamento referente
	 * ao parcelamento
	 * 
	 * @author Fernanda Paiva
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerGuiaPagamentoDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ControladorException;

	/**
	 * [UC0246] Executar Atividade de A��o de Cobran�a
	 * 
	 * @author Pedro Alexandre
	 * @created 03/02/2006
	 * 
	 * @param idsAtividadesCobrancaCronograma
	 *            Array de id's de atividades de cobran�a do cronograma
	 * @param idsAtividadesCobrancaEventuais
	 *            Array de id�s de atividades de cobran�a eventuais
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void executarAtividadeAcaoCobranca(
			String[] idsAtividadesCobrancaCronograma,
			String[] idsAtividadesCobrancaEventuais)
			throws ControladorException;

	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Pedro Alexandre
	 * @created 01/02/2006
	 * 
	 * @param grupoCobranca
	 *            Grupo de Cobran�a
	 * @param anoMesReferencia
	 *            Ano/M�s de refer�ncia do ciclo de cobran�a
	 * @param idCronogramaAtividadeAcaoCobranca
	 *            C�digo do cronograma da atividade da a��o de cobran�a
	 * @param idComandoAtividadeAcaoCobranca
	 *            C�digo do comando da atividade da a��o de cobran�a
	 * @param rotas
	 *            Cole��o de rotas
	 * @param acaoCobranca
	 *            A��o de cobran�a
	 * @param atividadeCobranca
	 *            Atividade de cobran�a
	 * @param indicadorCriterio
	 *            Indicador do crit�rio a ser utilizado
	 * @param criterioCobranca
	 *            Crit�rio de cobran�a
	 * @param cliente
	 *            Cliente
	 * @param relacaoClienteImovel
	 *            Tipo de rela��o entre cliente e im�vel
	 * @param anoMesReferenciaInicial
	 *            Ano/M�s de refer�ncia inicial
	 * @param anoMesReferenciaFinal
	 *            Ano/M�s de refer�ncia final
	 * @param dataVencimentoInicial
	 *            Data de vencimento inicial
	 * @param dataVencimentoFinal
	 *            Data de vencimento final
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public GerarAtividadeAcaoCobrancaHelper gerarAtividadeAcaoCobranca(
			CobrancaGrupo grupoCobranca, int anoMesReferenciaCicloCobranca,
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Collection<Rota> rotas, CobrancaAcao acaoCobranca,
			CobrancaAtividade atividadeCobranca, Integer indicadorCriterio,
			CobrancaCriterio criterioCobranca, Cliente cliente,
			ClienteRelacaoTipo relacaoClienteImovel,
			String anoMesReferenciaInicial, String anoMesReferenciaFinal,
			Date dataVencimentoInicial, Date dataVencimentoFinal,
			Date dataAtual, int idFuncionalidadeIniciada,Cliente clienteSuperior)
			throws ControladorException;

	/**
	 * [UC0246] Executar Atividade de A��o de Cobran�a [SF0001] Selecionar Lista
	 * de Rotas
	 * 
	 * @author Pedro Alexandre
	 * @created 06/02/2006
	 * 
	 * @param cobrancaGrupo
	 *            Grupo de cobran�a
	 * @param cobrancaAcaoAtividadeComando
	 *            Cobran�a A��o Atividade Comando
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public Collection<Rota> pesquisarListaRotasComando(
			CobrancaGrupo cobrancaGrupo,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando)
			throws ControladorException;

	/**
	 * [UC0246] Executar Atividade de A��o de Cobran�a Pesquisa uma cole��o de
	 * CobrancaAcaoAtividadeCronograma
	 * 
	 * @author Pedro Alexandre
	 * @created 01/02/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronograma()
			throws ControladorException;

	/**
	 * [UC0246] Executar Atividade de A��o de Cobran�a Pesquisa uma cole��o de
	 * CobrancaAcaoAtividadeComando
	 * 
	 * @author Pedro Alexandre
	 * @created 01/02/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeComando()
			throws ControladorException;

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
			throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Verifica a exist�ncia de parcelamento no m�s
	 * 
	 * [FS0012] Verifica a exist�ncia de parcelamento no m�s
	 * 
	 * @author Roberta Costa
	 * @date 21/03/2006
	 * 
	 * @param codigoImovel
	 * @return Collection<Parcelamento>
	 */
	public Collection<Parcelamento> verificarParcelamentoMesImovel(
			Integer codigoImovel) throws ControladorException;

	/**
	 * Obtem a Lista de Rotas
	 * 
	 * [UC0244] Manter Comando A��o Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 22/03/2006
	 * 
	 * @param codigoImovel
	 * @param idRotaInicial
	 * @param idRotaFinal
	 * @param idSetorComercialInicial
	 * @param idSetorComercialFinal
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param idGerenciaRegional
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterListasRotas(String idRotaInicial,
			String idRotaFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String idLocalidadeInicial,
			String idLocalidadeFinal, String idGerenciaRegional,
			String idUnidadeNegocio, 
			String codigoRotaIncial, 
			String codigoRotaFinal,
			String numeroQuadraInicial,
			String numeroQuadraFinal ) 
	throws ControladorException;

	/**
	 * 
	 * Obter Lista de Rotas Comando
	 * 
	 * [UC0244] - Manter Comando de A��o de Cobran�a
	 * 
	 * Selecionar as Lsitas de Rotas do Comando
	 * 
	 * [SF0009] - Selecionar Lista de Rotas do Comando
	 * 
	 * @author Rafael Santos
	 * @date 22/03/2006
	 * 
	 * @param idCobrancaGrupo
	 * @return
	 */
	public Collection obterListaRotasComando(String idCobrancaGrupo,
			Collection colecaoIdCobrancaAtividadeComandoRota)
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Executa o Comando Eventual
	 * 
	 * [SF0009] Executar Comando Eventual
	 * 
	 * @author Rafael Santos
	 * @date 23/03/2006
	 * 
	 * @param cobrancaAtividade
	 * @param cobrancaAcaoAtividadeComando
	 * @param cobrancaAcao
	 * @param colecaoRotas
	 * @return
	 * @throws ControladorException
	 */
	public GerarAtividadeAcaoCobrancaHelper executarComandoEventual(
			CobrancaAtividade cobrancaAtividade,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			CobrancaAcao cobrancaAcao, Collection colecaoRotas)
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Verificar Sele��o de pelo menos uma atividade de cobran�a
	 * 
	 * [FS0002] - Verificar Sele��o de pelo menos uma atividade de cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 23/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterListaAtividadeCronogramaAcaoCobrancaComandadas()
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Verificar Sele��o de pelo menos uma atividade de cobran�a
	 * 
	 * [FS0002] - Verificar Sele��o de pelo menos uma atividade de cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 23/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterListaAtividadesEventuaisAcaoCobrancaComandadas()
			throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Atualiza a situa��o das Contas para Efetuar Parcelamento
	 * 
	 * atualizarContaEfetuarParcelamentoDebito
	 * 
	 * @author Roberta Costa
	 * @date 21/03/2006
	 * 
	 * @param efetuarParcelamentoDebitosActionForm
	 * @param colecaoContaValores
	 * @return Collection
	 */
	public void atualizarContaEfetuarParcelamentoDebito(Conta conta, boolean isContaEntradaParcelamento, Integer maiorAnoMesContas)
			throws ControladorException;
	
	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Obt�m as Op��es de Parcelamento do D�bito do Im�vel
	 * 
	 * [SB0002] Obter Op��es Parcelamento
	 * 
	 * @author Roberta Costa, Vivianne Sousa, Raphael Rossiter
	 * @date 21/03/2006, 30/10/2006, 12/09/2008
	 * 
	 * @param efetuarParcelamentoDebitosActionForm
	 * @param colecaoContaValores
	 * @return Collection
	 */
	public NegociacaoOpcoesParcelamentoHelper obterOpcoesDeParcelamento(
			ObterOpcoesDeParcelamentoHelper helper) throws ControladorException ;


	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Exclui Comando de Atividade do Cronograma de A��o de Cobran�a
	 * 
	 * [SB0001] - Excluir Comando de Atividade de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 24/03/2006
	 * 
	 * @param idCobrancaAcaoAtividadeCrongrama
	 */
	public void excluirComandoAtividadeCronogramaAcaoCobranca(
			String[] idsCobrancaAcaoAtividadeCronograma)
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Exclui Comando de Atividade de Eventual de A��o de Cobran�a
	 * 
	 * [SB0003] - Excluir Comando de Atividade Eventual de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 24/03/2006
	 * 
	 * @param idCobrancaAcaoAtividadeCrongrama
	 */
	public void excluirComandoAtividadeEventualAcaoCobranca(
			String[] idsCobrancaAcaoAtividadeEventual)
			throws ControladorException;

	/**
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar Linhas do Criterios
	 * 
	 * [SB0005] - Consultar Linhas do Criterios
	 * 
	 * @author Rafael Santos
	 * @date 24/03/2006
	 * 
	 * @param idCriterioCobranca
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarLinhasCriterio(String idCriterioCobranca)
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Selecionar Crit�rios do Comando
	 * 
	 * [SB0004] - Selecionar Crit�rios do Comando
	 * 
	 * @author Rafael Santos
	 * @date 24/03/2006
	 * 
	 * @param idCobrancaAcao
	 * @param idCobrancaAcaoAtividadeComando
	 * @param indicadorCriterioComandoMarcado
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarCriteriosComando(String idCobrancaAcao)
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar Cobranca A��o CAtividade Comando
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public CobrancaAcaoAtividadeComando consultarCobrancaAcaoAtividadeComando(
			String idCobrancaAcaoAtividadeComando) throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar O Periodo Final da Conta para usar em Cobranca A��o Atividade
	 * Comando
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public String consultarPeriodoFinalContaCobrancaAcaoAtividadeComando()
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar O Periodo Vencimento da Conta para usar em Cobranca A��o
	 * Atividade Comando
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 */
	public String consultarPeriodoVencimentoContaFinalCobrancaAcaoAtividadeComando()
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Cobranca Grupo
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoCobrancaGrupo() throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Cobranca Atividade
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoCobrancaAtividade()
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Cobranca Acao
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoCobrancaAcao() throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Gerencia Regionais
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoGerenciaRegional()
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Unidade de Negocio
	 * 
	 * @author Rafael Santos
	 * @date 11/10/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoUnidadeNegocio() throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Rela��es Cliente Tipo
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoClienteRelacaoTipo()
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Cobran�a Atividade pela Atividade
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public CobrancaAtividade obterCobrancaAtividade(String idCobrancaAtividade)
			throws ControladorException;

	/**
	 * 
	 * Permite excluir um comando de atividade de cobran�a do crongrama ou
	 * alterar/excluir um comando deatividade de cobran�a eventual
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Consultar as Cole��o de Rotas do Setor Comercial
	 * 
	 * @author Rafael Santos
	 * @date 25/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoRota(String idSetorComercial)
			throws ControladorException;

	/**
	 * Permite inserir uma Resolu��o Diretoria
	 * 
	 * [UC0217] Inserir Resolu��o de Diretoria
	 * 
	 * @author Rafael Corr�a
	 * @date 30/03/2006
	 * 
	 */

	public Integer inserirResolucaoDiretoria(
			ResolucaoDiretoria resolucaoDiretoria, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Gera os D�bitos a Cobrar dos Acr�scimos por Impontualidade
	 * 
	 * [SB0004] - Gerar D�bitos a Cobrar dos Acr�scimos por Impontualidade
	 * 
	 * @author Roberta Costa
	 * @date 03/02/2006
	 * 
	 * @param resolucaoDiretoria
	 * @param codigoImovel
	 * @param valorEntrada
	 * @param situacaoAgua
	 * @param situacaoEsgoto
	 * @return
	 */
	public void gerarDebitosACobrarAcrescimosImpontualidade(Imovel imovel,
			BigDecimal valorAtualizacaoMonetaria, BigDecimal valorJurosMora,
			BigDecimal valorMulta, BigDecimal taxaJuros,
			Integer parcelamentoId, Collection<Categoria> colecaoCategoria, Usuario usuarioLogado, 
			boolean isContaEntradaParcelamento, Integer anoMesGuiaEntrada, Integer maiorAnoMesConta)
			throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Gera os D�bitos a Cobrar do Parcelamento
	 * 
	 * [SB0005] - Gerar D�bitos a Cobrar do Parcelamento
	 * 
	 * @author Roberta Costa
	 * @date 29/03/2006
	 * 
	 * @param resolucaoDiretoria
	 * @param codigoImovel
	 * @param valorEntrada
	 * @param situacaoAgua
	 * @param situacaoEsgoto
	 * @return
	 */
	public void gerarDebitosACobrarParcelamento(Imovel imovel,
			Short numeroPrestacao, BigDecimal valorTotalContas,
			BigDecimal valorTotalGuiasPagamento,
			BigDecimal valorTotalAcrescimosImpontualidade,
			BigDecimal valorTotalServicosDebitosACobrarCurtoPrazo,
			BigDecimal valorTotalServicosDebitosACobrarLongoPrazo,
			BigDecimal valorTotalReparcelamentosCurtoPrazo,
			BigDecimal valorTotalReparcelamentosLongoPrazo,
			BigDecimal valorTotalJurosParcelamento, BigDecimal taxaJuros,
			Integer parcelamentoId, Collection<Categoria> colecaoCategoria,
			BigDecimal valorEntrada, Integer indicadorDividaAtiva, Usuario usuarioLogado, 
			boolean isContaEntradaParcelamento, Integer anoMesGuiaEntrada, Integer maiorAnoMesConta) throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Gera os Cr�dito a Realizar do Parcelamento
	 * 
	 * [SB0006] - Gerar Cr�dito a Realizar do Parcelamento
	 * 
	 * @author Roberta Costa
	 * @date 29/03/2006
	 * 
	 * @param resolucaoDiretoria
	 * @param codigoImovel
	 * @param valorEntrada
	 * @param situacaoAgua
	 * @param situacaoEsgoto
	 * @return
	 */
	public void gerarCreditoARealizarParcelamento(Imovel imovel,
			Short numeroPrestacao, BigDecimal taxaJuros,
			Integer parcelamentoId,
			BigDecimal valorDescontoAcresimosImpontualidade,
			BigDecimal valorDescontoAntiguidadeDebito,
			BigDecimal valorDescontoInatividadeLigacaoAgua,
			BigDecimal valorCreditoAnteriores,
			Collection<Categoria> colecaoCategoria,
			BigDecimal valorDescontoSancoesRDEspecial,
			BigDecimal valorDescontoTarifaSocialRDEspecial, 
			boolean isContaEntradaParcelamento, Integer anoMesGuiaEntrada, Integer maiorAnoMesConta) throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Gera os Dados do Parcelamento
	 * 
	 * [SB0008] - Gerar Dados do Parcelamento
	 * 
	 * @author Roberta Costa - Vivianne Sousa
	 * @date 29/03/2006 - 26/09/2006
	 * 
	 * @param dataParcelamento
	 * @param valorConta
	 * @param valorGuiaPapagamento
	 * @param valorServicosACobrar
	 * @param valorParcelamentosACobrar
	 * @param valorCreditoARealizar
	 * @param valorAtualizacaoMonetaria
	 * @param valorJurosMora
	 * @param valorMulta
	 * @param valorDebitoAtualizado
	 * @param valorDescontoAcrescimos
	 * @param valorDescontoAntiguidade
	 * @param valorDescontoInatividade
	 * @param valorEntrada
	 * @param valorJurosParcelamento
	 * @param numeroPrestacoes
	 * @param valorPrestacao
	 * @param indicadorRestabelecimento
	 * @param indicadorContasRevisao
	 * @param indicadorGuiasPagamento
	 * @param indicadorAcrescimosImpotualidade
	 * @param indicadorDebitosACobrar
	 * @param indicadorCreditoARealizar
	 * @param percentualDescontoAcrescimos
	 * @param percentualDescontoAntiguidade
	 * @param percentualDescontoInatividadeLigacaoAgua
	 * @param imovel
	 * @param usuario
	 * @param parcelamentoPerfilId
	 * @param colecaoContaValores
	 * @param colecaoGuiaPagamentoValores
	 * @param colecaoDebitoACobrar
	 * @param colecaoCreditoARealizar
	 * @param taxaJuros
	 * @param indicadorConfirmacaoParcelamento
	 * @param cliente
	 * @return
	 * @throws ControladorException
	 */
	public Integer gerarDadosParcelamento(Date dataParcelamento,
			BigDecimal valorConta, BigDecimal valorGuiaPapagamento,
			BigDecimal valorServicosACobrar,
			BigDecimal valorParcelamentosACobrar,
			BigDecimal valorCreditoARealizar,
			BigDecimal valorAtualizacaoMonetaria, BigDecimal valorJurosMora,
			BigDecimal valorMulta, BigDecimal valorDebitoAtualizado,
			BigDecimal valorDescontoAcrescimos,
			BigDecimal valorDescontoAntiguidade,
			BigDecimal valorDescontoInatividade, BigDecimal valorEntrada,
			BigDecimal valorJurosParcelamento, Short numeroPrestacoes,
			BigDecimal valorPrestacao, Short indicadorRestabelecimento,
			Short indicadorContasRevisao, Short indicadorGuiasPagamento,
			Short indicadorAcrescimosImpotualidade,
			Short indicadorDebitosACobrar, Short indicadorCreditoARealizar,
			BigDecimal percentualDescontoAcrescimos,
			BigDecimal percentualDescontoAntiguidade,
			BigDecimal percentualDescontoInatividadeLigacaoAgua, Imovel imovel,
			Usuario usuario, Integer parcelamentoPerfilId,
			Collection<ContaValoresHelper> colecaoContaValores,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores,
			Collection<DebitoACobrar> colecaoDebitoACobrar,
			Collection<CreditoARealizar> colecaoCreditoARealizar,
			BigDecimal taxaJuros, Short indicadorConfirmacaoParcelamento,
			Cliente cliente,BigDecimal descontoSancoesRDEspecial,
			BigDecimal descontoTarifaSocialRDEspecial,
			Integer anoMesReferenciaContabil) throws ControladorException;

	/**
	 * 
	 * Inserir um comando de atividade de cobran�a eventual
	 * 
	 * [UC0243] - Inserir Comando A��o de Cobran�a
	 * 
	 * Inserir cobranca acao atividade comando
	 * 
	 * [SB0007] - Inserir cobranca acao atividade comando
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 04/04/2006
	 */

	public void inserirComandoAcaoCobrancaEventual(String idCobrancaAcao,
			String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String idCliente,
			String idClienteRelacaoTipo, String anoMesReferencialInicial,
			String anoMesReferencialFinal, String dataVencimentoContaInicial,
			String dataVencimentoContaFinal, String indicador,
			String idRotaInicial, String idRotaFinal, String idUnidadeNegocio,
			String codigoRotaInicial, String codigoRotaFinal,String numeroQuadraInicial,
			String numeroQuadraFinal)
			throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite a consulta de documentos de cobran�a
	 * 
	 * [UC0257] - Consultar Documentos de Cobran�a
	 * 
	 * Apresenta os itens dos documentos de cobran�a
	 * 
	 * [SB0001] - Apresenta Itens do Documento de Cobran�a
	 * 
	 * @author Rafael Corr�a & Raphael Rossiter
	 * @date 05/04/2006
	 */

	public CobrancaDocumentoHelper apresentaItensDocumentoCobranca(
			CobrancaDocumento cobrancaDocumento) throws ControladorException;

	/**
	 * 
	 * Inserir um comando de atividade de cobran�a eventual
	 * 
	 * [UC0243] - Inserir Comando A��o de Cobran�a
	 * 
	 * Verificar exist�ncia do comando eventual na base
	 * 
	 * [FS0016] - Verificar exist�ncia do comando eventual
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 04/04/2006
	 */
	public void verficarExistenciaComandoEventual(String[] idsCobrancaAcao,
			String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idCliente,
			String idClienteRelacaoTipo, String anoMesReferencialInicial,
			String anoMesReferencialFinal, String dataVencimentoContaInicial,
			String dataVencimentoContaFinal, String indicador,
			String rotaInicial, String rotaFinal, String idComando,
			String idUnidadeNegocio,String codigoClienteSuperior,String numeroQuadraInicial,String numeroQuadraFinal) throws ControladorException;

	/**
	 * 
	 * Inserir um comando de atividade de cobran�a eventual
	 * 
	 * [UC0243] - Inserir Comando A��o de Cobran�a
	 * 
	 * Verificar refer�ncia final menor que refer�ncia inicial
	 * 
	 * [FS0012] - Verificar refer�ncia final menor que refer�ncia inicial
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 04/04/2006
	 */
	public void validarAnoMesInicialFinalComandoAcaoCobranca(
			String anoMesContaInicial, String anoMesContaFinal)
			throws ControladorException;

	/**
	 * 
	 * Inserir um comando de atividade de cobran�a eventual
	 * 
	 * [UC0243] - Inserir Comando A��o de Cobran�a
	 * 
	 * Verificar data final menos que data inicial
	 * 
	 * [FS0014] - Verificar data final menos que data inicial
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 04/04/2006
	 */
	public void verificarVencimentoContaComandoAcaoCobranca(
			String anoMesVencimentoInicial, String anoMesVencimentoFinal)
			throws ControladorException;

	/**
	 * 
	 * Inserir um comando de atividade de cobran�a eventual
	 * 
	 * [UC0243] - Inserir Comando A��o de Cobran�a
	 * 
	 * Inserir cobranca acao atividade comando
	 * 
	 * [SB0007] - Inserir cobranca acao atividade comando
	 * 
	 * @author Rafael Santos
	 * @param usuarioLogado
	 * @throws ControladorException
	 * @date 04/04/2006
	 */
	public Collection inserirComandoAcaoCobrancaCriterioEventual(
			String[] idsCobrancaAcao, String idCobrancaAtividade,
			String idCobrancaGrupo, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String idCliente,
			String idClienteRelacaoTipo, String anoMesReferencialInicial,
			String anoMesReferencialFinal, String dataVencimentoContaInicial,
			String dataVencimentoContaFinal, String indicador,
			String idRotaInicial, String idRotaFinal, String idComando,
			String unidadeNegocio, Usuario usuarioLogado, String titulo,
			String descricaoSolicitacao, String prazoExecucao,
			String quantidadeMaximaDocumentos,String valorLimiteObrigatoria,
			String indicadorImoveisDebito,String indicadorGerarBoletimCadastro,
			String codigoClienteSuperior,String codigoRotaInicial, 
			String codigoRotaFinal,String logradouroId,
			String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo,
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, String[] situacaoFiscalizacao,
			String numeroQuadraInicial,String numeroQuadraFinal ) throws ControladorException;

	/**
	 * 
	 * Inserir Comando de A��o de Cobran�a
	 * 
	 * [UC0243] Inserir Comando A��o de Cobran�a
	 * 
	 * Consultar Cobranca A��o
	 * 
	 * @author Rafael Santos
	 * @date 04/04/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public CobrancaAcao consultarCobrancaAcao(String idCobrancaAcao)
			throws ControladorException;

	/**
	 * 
	 * Inserir Comando de A��o de Cobran�a
	 * 
	 * [UC0243] Inserir Comando A��o de Cobran�a
	 * 
	 * Consultar CobrancaAtividade
	 * 
	 * @author Rafael Santos
	 * @date 04/04/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public CobrancaAtividade consultarCobrancaAtividade(
			String idCobrancaAtividade) throws ControladorException;

	/**
	 * 
	 * Inserir Comando de A��o de Cobran�a
	 * 
	 * [UC0243] Inserir Comando A��o de Cobran�a
	 * 
	 * Atualizar Comando
	 * 
	 * @author Rafael Santos
	 * @date 05/04/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			GerarAtividadeAcaoCobrancaHelper gerarAtividadeAcaoCobrancaHelper)
			throws ControladorException;

	/**
	 * 
	 * Inserir Comando de A��o de Cobran�a
	 * 
	 * [UC0243] Inserir Comando A��o de Cobran�a
	 * 
	 * Concluir Comando de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @param tipoConsumo 
	 * @param cosumoMedioFinal 
	 * @param cosumoMedioInicial 
	 * @date 05/04/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public Collection concluirComandoAcaoCobranca(String periodoInicialConta,
			String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String[] idsCobrancaAcao,
			String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String localidadeOrigemID,
			String localidadeDestinoID, String setorComercialOrigemCD,
			String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial,
			String rotaFinal, String setorComercialOrigemID,
			String setorComercialDestinoID, String idComando,
			String unidadeNegocio, Usuario usuarioLogado, String titulo,
			String descricaoSolicitacao, String prazoExecucao,
			String quantidadeMaximaDocumentos, String valorLimiteObrigatoria, String indicadorImoveisDebito,
			String indicadorGerarBoletimCadastro,String codigoClienteSuperior, String codigoRotaInicial,
			String codigoRotaFinal,String logradouroId, String cosumoMedioInicial, String cosumoMedioFinal, String tipoConsumo,
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, String[] situacaoFiscalizacao,
			String numeroQuadraInicial,String numeroQuadraFinal) throws ControladorException;

	/**
	 * 
	 * Inserir Comando de A��o de Cobran�a
	 * 
	 * [UC0243] Inserir Comando A��o de Cobran�a
	 * 
	 * Executar Comando Concluir Comando de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 05/04/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public Collection executarComandoAcaoCobranca(String periodoInicialConta,
			String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String[] idsCobrancaAcao,
			String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String localidadeOrigemID,
			String localidadeDestinoID, String setorComercialOrigemCD,
			String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial,
			String rotaFinal, String setorComercialOrigemID,
			String setorComercialDestinoID, String idComando,
			Usuario usuarioLogado, String titulo, String descricaoSolicitacao,
			String prazoExecucao, String quantidadeMaximaDocumentos, String valorLimiteObrigatoria,
			String indicadorImoveisDebito, String indicadorGerarBoletimCadastro,
			String codigoClienteSuperior, String codigoRotaInicial,
			String codigoRotaFinal,String consumoMedioInicial, String consumoMedioFinal, 
			String tipoConsumo,String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, 
			String[] situacaoFiscalizacao,String numeroQuadraInicial,String numeroQuadraFinal)
			throws ControladorException;

	/**
	 * 
	 * Obter Lista de Rotas Comando
	 * 
	 * [UC0243] - Inserir Comando de A��o de Cobran�a
	 * 
	 * Selecionar as Lsitas de Rotas do Comando
	 * 
	 * [SF0009] - Selecionar Lista de Rotas do Comando
	 * 
	 * @author Rafael Santos
	 * @date 22/03/2006
	 * 
	 * @param idCobrancaGrupo
	 * @return
	 */
	public Collection obterListaRotasComando(String idCobrancaGrupo,
			String idCobrancaAcaoAtividadeComando) throws ControladorException;

	/**
	 * Permite atualizar uma Resolu��o de Diretoria
	 * 
	 * [UC0218] Manter Resolu��o de Diretoria
	 * 
	 * [SF0001] - Atualizar Resolu��o de Diretoria
	 * 
	 * @author Rafael Corr�a
	 * @date 10/04/2006
	 * 
	 */

	public void atualizarResolucaoDiretoria(
			ResolucaoDiretoria resolucaoDiretoria, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Obt�m o Perfil do parcelamento para o im�vel
	 * 
	 * @author Roberta Costa
	 * @date 24/04/2006
	 * 
	 * @param codigoImovel
	 * @param imovelSituacao
	 * @param subcategoria
	 * @param resolucaoDiretoria
	 * @return ParcelamentoPerfil
	 */
	public ParcelamentoPerfil obterPerfilParcelamento(Integer codigoImovel,
			Integer imovelSituacaoId, Integer perfilImovelId,
			Integer subcategoriaId, Integer resolucaoDiretoria,Integer categoriaId)
			throws ControladorException;

	/**
	 * 
	 * Manter Comando de A��o de Cobran�a
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Concluir Comando de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 24/04/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public void concluirManterComandoAcaoCobranca(String periodoInicialConta,
			String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String idCobrancaAcao,
			String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String localidadeOrigemID,
			String localidadeDestinoID, String setorComercialOrigemCD,
			String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial,
			String rotaFinal, String setorComercialOrigemID,
			String setorComercialDestinoID,
			String idCobrancaAcaoAtividadeComando, Date realizacao,
			Date comando, Date ultimaDataAtualizacao, Usuario usuario,
			Empresa empresa, Integer quantidadeDocumentos,
			BigDecimal valorDocumentos, Integer quantidadeItensCobrados,
			String idComando, String unidadeNegocio, String titulo,
			String descricaoSolicitacao, String prazoExecucao,
			String quantidadeMaximaDocumentos, String valorLimiteObrigatoria, String indicadorImoveisDebito,
			String indicadorGerarBoletimCadastro,String codigoClienteSuperior,
			String codigoRotaInicial, String codigoRotaFinal,
			String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo, 
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, 
			String[] situacaoFiscalizacao,String numeroQuadraInicial,
			String numeroQuadraFinal) throws ControladorException;

	/**
	 * 
	 * Manter Comando de A��o de Cobran�a
	 * 
	 * [UC0244] Manter Comando A��o de Cobran�a
	 * 
	 * Executar Comando Concluir Comando de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 24/04/2006
	 * 
	 * @param idCobrancaAcaoAtividadeComando
	 * @return
	 * @throws ControladorException
	 */
	public void executarComandoManterAcaoCobranca(String periodoInicialConta,
			String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String idCobrancaAcao,
			String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String localidadeOrigemID,
			String localidadeDestinoID, String setorComercialOrigemCD,
			String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial,
			String rotaFinal, String setorComercialOrigemID,
			String setorComercialDestinoID, String idComando,
			String idCobrancaAcaoAtividadeComando, Date ultimaDataAtualizacao,
			Date comando, Date realizacao, Usuario usuario, Empresa empresa,
			Integer quantidadeDocumentos, BigDecimal valorDocumentos,
			Integer quantidadeItensCobrados, String titulo,
			String descricaoSolicitacao, String prazoExecucao,
			String quantidadeMaximaDocumentos, String valorLimiteObrigatoria, String indicadorImoveisDebito,
			String indicadorGerarBoletimCadastro, String codigoClienteSuperior, 
			String codigoRotaInicial, String codigoRotaFinal,
			String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo, 
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, 
			String[] situacaoFiscalizacao,String numeroQuadraInicial,String numeroQuadraFinal)
			throws ControladorException;

	/**
	 * 
	 * Manter um comando de atividade de cobran�a eventual
	 * 
	 * [UC0244] - Inserir Comando A��o de Cobran�a
	 * 
	 * Manter cobranca acao atividade comando
	 * 
	 * [SB0006] - Atualizar Comando Eventual
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 24/04/2006
	 */
	public CobrancaAcaoAtividadeComando atualizarComandoAcaoCobrancaEventual(
			String idCobrancaAcao, String idCobrancaAtividade,
			String idCobrancaGrupo, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String idCliente,
			String idClienteRelacaoTipo, String anoMesReferencialInicial,
			String anoMesReferencialFinal, String dataVencimentoContaInicial,
			String dataVencimentoContaFinal, String indicador,
			String idRotaInicial, String idRotaFinal,
			String idCobrancaAcaoAtividadeComando, Date ultimaDataAtualizacao,
			Date comando, Date realizacao, Usuario usuario, Empresa empresa,
			Integer quantidadeDocumentos, BigDecimal valorDocumentos,
			Integer quantidadeItensCobrados, String idComando,
			String unidadeNegocio, String titulo, String descricaoSolicitacao,
			String prazoExecucao, String quantidadeMaximaDocumentos,
			String valorLimiteObrigatoria,String indicadorImoveisDebito,
			String indicadorGerarBoletimCadastro, String codigoClienteSuperior,
			String codigoRotaInicial, String codigoRotaFinal,
			String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo, 
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, 
			String[] situacaoFiscalizacao,String numeroQuadraInicial,String numeroQuadraFinal) throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Faz as atualiza��es e inser��es do parcelamento do d�bito
	 * 
	 * concluirParcelamentoDebitos
	 * 
	 * @author Roberta Costa - Vivianne Sousa - Raphael Rossiter
	 * @date 26/04/2006 - 26/09/2006 - 29/09/2008
	 *
	 * @param ConcluirParcelamentoDebitosHelper
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer concluirParcelamentoDebitos(ConcluirParcelamentoDebitosHelper helper, Usuario usuarioLogado) throws ControladorException;

	/**
	 * 
	 * Inseri um Cronograma de Cobran�a com as A��es de Cobran�as e suas
	 * Atividades
	 * 
	 * [UC0312] Inserir Cronograma de Cobran�a
	 * 
	 * @author Fl�vio Cordeiro
	 * @data 25/04/2006
	 * 
	 * @param cobrancaGrupoCronogramaMes,
	 *            cobrancaAcaoCronograma,
	 *            cobrancasAtividadesParaInsercao(Collection)
	 */
	public void inserirCobrancaCronograma(
			Collection colecaoCobrancaCronogramaHelper, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * 
	 * Filtrar Cronograma de Cobran�a com as A��es de Cobran�as e suas
	 * Atividades
	 * 
	 * [UC03125] Inserir Cronograma de Cobran�a
	 * 
	 * @author Fl�vio Cordeiro
	 * @data 29/04/2006
	 * 
	 * @param idGrupoCobranca,
	 *            mesAno
	 * @return Colecao de CobrancaAcaoAtividadeCronograma
	 */
	public FiltroCobrancaAcaoAtividadeCronograma filtrarCobrancaCronograma(
			String idGrupoCobranca, String mesAno) throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito
	 * 
	 * @author Fernanda Paiva
	 * @created 29/04/2006
	 * 
	 * @param codigoImovel
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarDadosParcelamentoParaImovel(Integer codigoImovel)
			throws ControladorException;

	/**
	 * [UC0213] Desfazer Parcelamentos por entrada n�o paga
	 * 
	 * Este caso de uso permite desfazer os parcelamentos de d�bitos efetuados
	 * no m�s cuja entrada n�o tenha sido paga.
	 * 
	 * @author Fernanda Paiva
	 * @created 02/05/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void desfazerParcelamentosPorEntradaNaoPaga(
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Desfazer Parcelamentos D�bitos
	 * 
	 * Este caso de uso permite desfazer os parcelamentos de d�bitos
	 * 
	 * @author Fernanda Paiva
	 * @created 02/05/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public Integer desfazerParcelamentosDebito(String motivo, Integer codigo,Usuario usuario)
			throws ControladorException;

	/**
	 * [UC0316] Inserir Crit�rio de Cobran�a
	 * 
	 * Este caso de uso inseriR a cobran�a crit�rio e as linhas da cobran�a
	 * crit�rio
	 * 
	 * @author S�vio luiz
	 * @created 04/05/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public Integer inserirCobrancaCriterio(CobrancaCriterio cobrancaCriterio,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0317] Manter Crit�rio de Cobran�a
	 * 
	 * Este caso de uso atualiza a cobran�a crit�rio e as linhas da cobran�a
	 * crit�rio
	 * 
	 * [SB0001] Atualizar Crit�rio de Cobran�a
	 * 
	 * @author S�vio luiz
	 * @created 11/05/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarCobrancaCriterio(CobrancaCriterio cobrancaCriterio,
			Collection colecaoCobrancaCriterioLinha,
			Collection colecaoCobrancaCriterioLinhaRemovidas,
			Collection colecaoCriterioSituacaoCobrancaNovos,
			Collection colecaoCriterioSituacaoLigacaoAguaNovos,
			Collection colecaoCriterioSituacaoLigacaoEsgotoNovos,			
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0317] Manter Crit�rio de Cobran�a
	 * 
	 * Este caso de uso remove a cobran�a crit�rio e as linhas da cobran�a
	 * crit�rio
	 * 
	 * [SB0002] Excluir Crit�rio de Cobran�a
	 * 
	 * @author S�vio luiz
	 * @created 11/05/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerCobrancaCriterio(String[] idsCobrancaCriterio,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * 
	 * Manter - Atualizar um Cornograma de Cobran�a com as A��es de Cobran�as e
	 * suas Atividades
	 * 
	 * [UC0313] Manter Cornograma de Cobran�a
	 * 
	 * @author Fl�vio Cordeiro
	 * @data 05/05/2006
	 * 
	 * @param cobrancaGrupoCronogramaMes,
	 *            cobrancaAcaoCronograma,
	 *            cobrancasAtividadesParaInsercao(Collection)
	 */

	public void atualizarCobrancaCronograma(
			Collection colecaoCobrancaCronogramaHelper,
			Collection colecaoCronogramaHelperErroAtualizacao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0313] Manter Cronograma Cobran�a
	 * 
	 * [SB0002] Excluir Cronograma de Cobran�a
	 * 
	 * @param ids
	 * @throws ControladorException
	 */

	public void removerCobrancaCronograma(
			Collection<CobrancaCronogramaHelper> colecaocobrancaCronogramaHelperRemover)
			throws ControladorException;
	
	/**
	 * [UC0313] Manter Cronograma Cobran�a
	 * 
	 * [SB0002] Excluir Cronograma de Cobran�a
	 * 
	 * @param ids
	 * @throws ControladorException
	 */
	public void removerCobrancaCronograma(String[] idsCobrancaCronograma,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0313] Manter Cronograma Cobran�a
	 * 
	 * [SB0002] Excluir Cobran�a Acao Cronograma
	 * 
	 * @param ids
	 * @throws ControladorException
	 */

	public void removerCobrancaAtividadeCronograma(String[] ids)
			throws ControladorException;

	/**
	 * Inseri o Perfil de Parcelamento na base
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/05/2006
	 * 
	 * @param parcelamentoPerfilNova
	 * @param collectionParcelamentoQuantidadeReparcelamentoHelper
	 * @param collectionParcelamentoDescontoInatividade
	 * @param collectionParcelamentoDescontoAntiguidade
	 * @return o idPerfilParcelamneto
	 */
	public Integer inserirPerfilParcelamento(
			ParcelamentoPerfil parcelamentoPerfilNova,
			Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
			Collection collectionParcelamentoDescontoInatividade,
			Collection collectionParcelamentoDescontoAntiguidade,
			Usuario usuarioLogado,
			Collection collectionParcelamentoDescontoInatividadeAVista) throws ControladorException;

	/**
	 * [UC0221] Manter Perfil de Parcelamento
	 * 
	 * Remove um objeto do tipo ParcelamentoPerfil no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 11/05/2006
	 * 
	 * @param ids
	 * @return
	 */
	public void removerPerfilParcelamento(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Atualizar o Perfil de Parcelamento na base
	 * 
	 * [UC0221] Manter Perfil de Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 17/05/2006
	 * 
	 * @param parcelamentoPerfilNova
	 * @param collectionParcelamentoQuantidadeReparcelamentoHelper
	 * @param collectionParcelamentoDescontoInatividade
	 * @param collectionParcelamentoDescontoAntiguidade
	 * @param collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas
	 * @param collectionParcelamentoDescontoInatividadeLinhaRemovidas
	 * @param collectionParcelamentoDescontoAntiguidadeLinhaRemovidas
	 * @param collectionParcelamentoQuantidadePrestacaoLinhaRemovidas
	 * @return
	 */
	public void atualizarPerfilParcelamento(
			ParcelamentoPerfil parcelamentoPerfil,
			Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
			Collection collectionParcelamentoDescontoInatividade,
			Collection collectionParcelamentoDescontoAntiguidade,
			Collection collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas,
			Collection collectionParcelamentoDescontoInatividadeLinhaRemovidas,
			Collection collectionParcelamentoDescontoAntiguidadeLinhaRemovidas,
			Collection collectionParcelamentoQuantidadePrestacaoLinhaRemovidas,
			Usuario usuarioLogado,Collection collectionParcelamentoDescontoInatividadeAVista,
			Collection collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas) throws ControladorException;

	/**
	 * 
	 * Filtrar os Comandos de A��o de Cobran�a tipo comando Cronograma
	 * 
	 * [UC0326] - Filtrar Comandos de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 10/05/2006
	 * 
	 * @return filtroCobrancaAcaoAtividadeCronograma
	 * @throws ControladorException
	 */
	public FiltroCobrancaAcaoAtividadeCronograma construirFiltroCobrancaAcaoAtividadeCronograma(
			String anoMesPeriodoReferenciaCobrancaInicial,
			String anoMesPeriodoReferenciaCobrancaFinal,
			String[] grupoCobranca, String[] acaoCobranca,
			String[] atividadeCobranca,
			String dataPeriodoPrevisaoComandoInicial,
			String dataPeriodoPrevisaoComandoFinal,
			String dataPeriodoComandoInicial, String dataPeriodoComandoFinal,
			String dataPeriodoRealizacaoComandoInicial,
			String dataPeriodoRealizacaoComandoFinal,
			String intervaloValorDocumentosInicial,
			String intervaloValorDocumentosFinal,
			String intervaloQuantidadeDocumentosInicial,
			String intervaloQuantidadeDocumentosFinal,
			String intervaloQuantidadeItensDocumentosInicial,
			String intervaloQuantidadeItensDocumentosFinal,
			String situacaoCronograma, String situacaoComando)
			throws ControladorException;

	/**
	 * Consultar Comando de A��o de Cobran�a
	 * 
	 * [UC0325] - Consultar Comandos de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 10/05/2006
	 * 
	 * @param filtroCobrancaAcaoAtividadeCronograma
	 * @return
	 * @throws ControladorException
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronograma(
			FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma)
			throws ControladorException;

	/**
	 * 
	 * [UC0325] Consultar Comandos A��o de Cobran�a
	 * 
	 * Consultar Comando Cobranca A��o Atividade CobrancaVerificar pelo ID
	 * 
	 * [FS0002] - Verificar Sele��o de pelo menos uma atividade de cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 11/05/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public CobrancaAcaoAtividadeCronograma obterCobrancaAcaoAtividadeCronograma(
			String idCobrancaAcaoAtividadeCronograma)
			throws ControladorException;

	/**
	 * 
	 * Filtrar os Comandos de A��o de Cobran�a tipo comando Eventual
	 * 
	 * [UC0326] - Filtrar Comandos de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @param tipoConsumo 
	 * @param consumoMedioFinal 
	 * @param consumoMedioInicial 
	 * @date 12/05/2006
	 * 
	 * @return filtroCobrancaAcaoAtividadeComando
	 * @throws ControladorException
	 */
	public FiltroCobrancaAcaoAtividadeComando construirFiltroCobrancaAcaoAtividadeEventual(
			String[] grupoCobranca, String[] acaoCobranca,
			String[] atividadeCobranca,
			String anoMesPeriodoReferenciaContasInicial,
			String anoMesPeriodoReferenciaContasFinal,
			String dataPeriodoComandoInicial, String dataPeriodoComandoFinal,
			String dataPeriodoRealizacaoComandoInicial,
			String dataPeriodoRealizacaoComandoFinal,
			String dataPeriodoVencimentoContasInicial,
			String dataPeriodoVencimentoContasFinal,
			String intervaloValorDocumentosInicial,
			String intervaloValorDocumentosFinal,
			String intervaloQuantidadeDocumentosInicial,
			String intervaloQuantidadeDocumentosFinal,
			String intervaloQuantidadeItensDocumentosInicial,
			String intervaloQuantidadeItensDocumentosFinal,
			String situacaoComando, String indicadorCriterio,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idRotaInicial,
			String idRotaFinal, String idCliente, String idClienteRelacaoTipo,
			String criterioCobranca, String unidadeNegocio, 
			String[] idCobrancaAcaoAtividadeComando, String dataEmissaoInicial,
			String dataEmissaoFinal, String consumoMedioInicial,
			String consumoMedioFinal, String tipoConsumo, 
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, 
			String[] situacaoFiscalizacao,String numeroQuadraInicial,String numeroQuadraFinal)throws ControladorException;

	/**
	 * Filtrar os Comandos de A��o de Cobran�a tipo comando Eventual
	 * 
	 * [UC0326] - Filtrar Comandos de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 15/05/2006
	 * 
	 * @param localidadeID
	 * @param setorComercialCD
	 * @return
	 * @throws ControladorException
	 */
	public SetorComercial obterSetorComercialLocalidade(String localidadeID,
			String setorComercialCD) throws ControladorException;

	/**
	 * 
	 * Filtrar os Comandos de A��o de Cobran�a tipo comando Eventual
	 * 
	 * [UC0326] - Filtrar Comandos de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 15/05/2006
	 * 
	 * @param codigoSetorComercial
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoRotaSetorComercialLocalidade(
			String codigoSetorComercial, String idLocalidade)
			throws ControladorException;

	/**
	 * 
	 * Filtrar os Comandos de A��o de Cobran�a tipo comando Eventual
	 * 
	 * [UC0326] - Filtrar Comandos de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 15/05/2006
	 * 
	 * @param localidadeID
	 * @return
	 */
	public Localidade obterLocalidadeGerenciaRegional(String localidadeID)
			throws ControladorException;

	/**
	 * 
	 * Filtrar os Comandos de A��o de Cobran�a tipo comando Eventual
	 * 
	 * [UC0326] - Filtrar Comandos de A��o de Cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 15/05/2006
	 * 
	 * @param idCliente
	 * @return
	 */
	public Cliente obterCliente(String idCliente) throws ControladorException;

	/**
	 * 
	 * Filtrar os Comandos de A��o de Cobran�a tipo comando Eventual
	 * 
	 * [UC0326] - Filtrar Comandos de A��o de Cobran�a
	 * 
	 * 
	 * 
	 * @author Administrador
	 * @date 19/05/2006
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 */
	public CobrancaCriterio obterCobrancaCriterio(String idCobrancaCriterio)
			throws ControladorException;

	/**
	 * Consultar Comando de A��o de Cobran�a
	 * 
	 * [UC0325] - Consultar Comandos de A��o de Cobran�a - Tipo Eventual
	 * 
	 * @author Rafael Santos
	 * @date 10/05/2006
	 * 
	 * @param filtroCobrancaAcaoAtividadeCronograma
	 * @return
	 * @throws ControladorException
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeEventual(
			FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando)
			throws ControladorException;

	/**
	 * 
	 * [UC0325] Consultar Comandos A��o de Cobran�a
	 * 
	 * Consultar Comando Cobranca A��o Atividade Cobranca - Verificar pelo ID
	 * 
	 * [SB0004] - Consultar Dados do Comando de A��o de cobran�a Eventual
	 * 
	 * @author Rafael Santos
	 * @date 11/05/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public CobrancaAcaoAtividadeComando obterCobrancaAcaoAtividadeComando(
			String idCobrancaAcaoAtividadeComando) throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite a emiss�o de um ou mais documentos de cobran�a
	 * 
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param
	 * @return void
	 */
	/*
	 * public void emitirDocumentoCobranca( Integer
	 * idCronogramaAtividadeAcaoCobranca, Integer
	 * idComandoAtividadeAcaoCobranca, Date dataAtualPesquisa, Integer
	 * idAcaoCobranca) throws ControladorException;
	 */

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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa, 
			String[] tipoDebito, String valorDebitoInicial, String valorDebitoFinal,
			String qtdContasInicial, String qtdContasFinal, String referenciaFaturaInicial,
			String referenciaFaturaFinal, String vencimentoInicial, String vencimentoFinal,
			String qtdImoveis, String qtdMaiores, String ordenacao,
			String indicadorCpfCnpj, String cpfCnpj

	) throws ControladorException;

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
			throws ControladorException;

	/**
	 * Retorna o count do resultado da pesquisa de Cobran�a Cronograma
	 * 
	 * 
	 * @author Fl�vio Leonardo
	 * @date 14/06/2006
	 * 
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarCobrancaCronogramaCount(Filtro filtro)
			throws ControladorException;

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
			throws ControladorException;

	/**
	 * Gerar Relat�rio de Crit�rio de Cobran�a
	 * 
	 * Pesquisa as linhas de crit�rio de cobran�a atrav�s do id do crit�rio de
	 * cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @data 09/08/2006
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection pesquisarCobrancaCriterioLinha(Integer idCriterioCobranca)
			throws ControladorException;

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
			Integer idParcelamentoPerfil) throws ControladorException;

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
			Integer idParcelamentoPerfil) throws ControladorException;

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
			Integer idParcelamentoPerfil) throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Pesquisa os d�bitos do im�vel a partir das informa��es do formul�rio
	 * 
	 * pesquisarDebitosImovel
	 * 
	 * @author Roberta Costa
	 * @date 23/08/2006
	 * 
	 * @param codigoImovel
	 * @param codigoImovelAntes
	 * @param dataParcelamento
	 * @param resolucaoDiretoria
	 * @param fimIntervaloParcelamento
	 * @param inicioIntervaloParcelamento
	 * @param indicadorContasRevisao
	 * @param indicadorGuiasPagamento
	 * @param indicadorAcrescimosImpotualidade
	 * @param indicadorDebitosACobrar
	 * @param indicadorCreditoARealizar
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarDebitosImovel(String codigoImovel,
			String codigoImovelAntes, String dataParcelamento,
			String resolucaoDiretoria, String fimIntervaloParcelamento,
			String inicioIntervaloParcelamento, String indicadorContasRevisao,
			String indicadorGuiasPagamento,
			String indicadorAcrescimosImpotualidade,
			String indicadorDebitosACobrar, String indicadorCreditoARealizar,
			Boolean indicadorContas, String indicadorDividaAtiva) throws ControladorException;

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
			throws ControladorException;

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
			throws ControladorException;

	/**
	 * Este caso de uso permite gerar e emitir extrato dos d�bitos de um im�vel
	 * 
	 * [UC0444] Gerar e Emitir Extrato de D�bito
	 * 
	 * @author Roberta Costa, Vivianne Sousa
	 * @date 06/09/2006, 11/09/2006
	 * 
	 * @param imovel
	 * @param indicadorGeracaoTaxaCobranca
	 * @param colecaoContas
	 * @param colecaoGuiasPagamento
	 * @param colecaoDebitosACobrar
	 * @param valorAcrescimosImpontualidade
	 * @param valorDesconto
	 * @return
	 * @throws ControladorException
	 */
	// Quando implementar Notas Promiss�rias acrescentar nos par�metros
	public ExtratoDebitoRelatorioHelper gerarEmitirExtratoDebito(Imovel imovel,
			Short indicadorGeracaoTaxaCobranca, Collection colecaoContas,
			Collection colecaoGuiasPagamento, Collection colecaoDebitosACobrar,
			BigDecimal valorAcrescimosImpontualidade, BigDecimal valorDesconto,
			BigDecimal valorDocumento,
			Collection<CreditoARealizar> colecaoCreditoARealizar,
			Cliente cliente,ResolucaoDiretoria resolucaoDiretoria,
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento,
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento) throws ControladorException;

	/**
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * Calcular valor e Data de vencimento anterior
	 * 
	 * [SB0001] - Calcular Valor e Data de Vencimento Anterior
	 * 
	 * @author Raphael Rossiter, Vivianne Sousa
	 * @data 30/05/2006, 14/09/2006
	 * 
	 * @param Collection
	 *            <CobrancaDocumentoItem>
	 * @return CalcularValorDataVencimentoAnteriorHelper
	 */
	public CalcularValorDataVencimentoAnteriorHelper calcularValorDataVencimentoAnterior(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem,
			int qtdMaxItens) throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite a emiss�o de um ou mais documentos de cobran�a
	 * 
	 * [UC0349] Emitir Documento de Cobran�a - Ordem de Corte
	 * 
	 * @author Ana Maria
	 * @data 07/09/2006
	 * 
	 * @param
	 * @return void
	 */
	/*
	 * public void emitirDocumentoCobrancaOrdemCorte( Integer
	 * idCronogramaAtividadeAcaoCobranca, Integer
	 * idComandoAtividadeAcaoCobranca, Date dataAtualPesquisa, Integer
	 * idAcaoCobranca) throws ControladorException;
	 */

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
	public Collection<CobrancaDocumento> consultarImovelDocumentosCobranca(
			Integer idImovel, Integer numeroPagina) throws ControladorException;

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
			throws ControladorException;

	/**
	 * Este caso de consulta os dados do imovel, esse metodo consulta a
	 * quantidade de documentos de itens de cobran�a do imovel
	 * 
	 * [UC0476] - Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 18/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQuantidadeImovelDocumentosItemCobranca(
			Integer idImovel) throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite a emiss�o de um ou mais documentos de cobran�a
	 * 
	 * [UC0477] Emitir Documento de Cobran�a - Ordem de Supress�o
	 * 
	 * @author Ana Maria
	 * @data 15/09/2006
	 * 
	 * @param
	 * @return void
	 */
	/*
	 * public void emitirDocumentoCobrancaOrdemSupressao(
	 * 
	 * Integer idCronogramaAtividadeAcaoCobranca, Integer
	 * idComandoAtividadeAcaoCobranca, Date dataAtualPesquisa, Integer
	 * idAcaoCobranca) throws ControladorException;
	 */

	/**
	 * Pesquisa os dados do parcelamento necess�rios para o relat�rio atrav�s do
	 * id do parcelamento
	 * 
	 * @author Rafael Corr�a
	 * @date 25/09/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public ParcelamentoRelatorioHelper pesquisarParcelamentoRelatorio(
			Integer idParcelamento) throws ControladorException;

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
			Integer idParcelamento) throws ControladorException;

	/**
	 * 
	 * [UC0349] Emitir Documento de Cobran�a - Ordem de Fiscaliza��o
	 * 
	 * @author Ana Maria
	 * @data 11/10/2006
	 * 
	 * @param
	 * @return void
	 */
	/*
	 * public void emitirDocumentoCobrancaOrdemFiscalizacao( Integer
	 * idCronogramaAtividadeAcaoCobranca, Integer
	 * idComandoAtividadeAcaoCobranca, Date dataAtualPesquisa, Integer
	 * idAcaoCobranca) throws ControladorException;
	 */

	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * P�s-oncid��o: Resumo das a��es de cobran�a gerado e atividade encerrar da
	 * a��o de cobran�a, se for o caso, realizada
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 */
	public void gerarResumoAcoesCobrancaCronograma(
			Object[] dadosCobrancaAcaoAtividadeCronograma,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0004] - Atualizar Item do Documento de Cobran�a
	 * 
	 * Acumula a quantidade e o valor do item, na situia�o de d�bito
	 * correspondente Armazena a data da situa��o do d�bito do imte do documento
	 * de cobran�a refrente a situa��o do d�bito do item do
	 * 
	 * documento de cobran�a
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 */
	public void atualizarItemDocumentoCobranca(int

	idSituacaoDebito, BigDecimal valorItemCobrado, Date dataSituacaoDebito,
			Collection<GerarResumoAcoesCobrancaCronogramaHelper>

			colecaoGerarResumoAcoesCobrancaCronogramaHelper);

	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0001] - Processar Documentos de Cobran�a
	 * 
	 * Pesquisa o Imovel para ser usado para acumular valores no
	 * RESUMO_COBRANCA_ACAO
	 * 
	 * @author Rafael Santos
	 * @date 23/10/2006
	 * 
	 */
	public Imovel pesquisarDadosImovel(int idImovel)
			throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0001] - Processar Documentos de Cobran�a
	 * 
	 * Acumular ou insere na coel��o de RESUMO_COBRANCA_ACAO
	 * 
	 * @author Rafael Santos
	 * @date 24/10/2006
	 * 
	 */
	// public Collection<ResumoCobrancaAcao> acumularResumoCobrancaAcao(
	// Collection<ResumoCobrancaAcao>
	//
	// colecaoResumoCobrancaAcao,
	// int anoMesReferenciaCobrancaGrupoCronogramaMes,
	// int idCobrancaAcaoCronograma, Date dataRealizacaoAtividadeEmitir,
	// Date dataPrevistaAtividadeEncerrar, int idCobrancaGrupo,
	// Imovel imovel, Categoria categoria, int idCobrancaAcao,
	// Integer idSituacaoAcao, Integer idSituacaoPredominanteDebito,
	// Integer idFiscalizacao, int indicadorCronogramaComando,
	// Date dataSituacaoAcao, Date dataSituacaoDebito,
	// BigDecimal valorDocumento, BigDecimal valorLimitePrioridade, Integer
	// idCobrancaCriterio,Date dataRealizacaoAtividadeEncerrar);
	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Cria um Objto ResumoCobrancaAcao com os valores informados
	 * 
	 * @author Rafael Santos
	 * @date 24/10/2006
	 * 
	 */
	// public ResumoCobrancaAcao criarResumoCobrancaAcao(
	// int anoMesReferenciaCobrancaGrupoCronogramaMes,
	// int idCobrancaAcaoCronograma, Date dataRealizacaoAtividadeEmitir,
	// Date dataPrevistaAtividadeEncerrar, int idCobrancaGrupo,
	// Imovel imovel, Categoria categoria, int idCobrancaAcao,
	// Integer idSituacaoAcao, Integer idSituacaoPredominanteDebito,
	// Integer idFiscalizacao, int indicadorCronogramaComando,
	// Integer indicadorAntesApos, Integer indicadorAcimaLimite,
	// BigDecimal valorDocumento,Integer idCobrancaCriterio);
	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0006] Processar A��o com Ordens de Servi�o
	 * 
	 * @author Rafael Santos
	 * @throws ControladorException
	 * @date 25/10/2006
	 * 
	 */
	public void processarAcaoOrdemServico(
			Date dataPrevistaAtividadeEncerrar,
			Date dataPrevistaAtividadeEmitir,
			Date dataComandoAtividadeEncerrar,
			Date dataRealizacaoAtividadeEmitir, Usuario usuarioLogado,
			int anoMesReferenciaCobrancaGrupoCronogramaMes,
			int idCobrancaAcaoCronograma, int idCobrancaGrupo,
			CobrancaAcao cobrancaAcao, Date dataRealizacaoAtividadeEncerrar)
			throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * [SB0006] - Processar A��o com Ordens de Servi�o
	 * 
	 * Acumular ou insere na coel��o de RESUMO_COBRANCA_ACAO
	 * 
	 * @author Rafael Santos
	 * @date 25/10/2006
	 * 
	 */
	public void acumularResumoCobrancaAcaoOrdemServico(
			Collection<ResumoCobrancaAcao> colecaoResumoCobrancaAcao,
			int anoMesReferenciaCobrancaGrupoCronogramaMes,
			int idCobrancaAcaoCronograma, Date dataRealizacaoAtividadeEmitir,
			Date dataPrevistaAtividadeEncerrar, int idCobrancaGrupo,
			Imovel imovel, Categoria categoria, int idCobrancaAcao,
			Integer idSituacaoAcao, Integer idSituacaoPredominanteDebito,
			int idFiscalizacao, int indicadorCronogramaComando,
			BigDecimal valorDocumento, Integer indicadorAntesApos,
			Integer indicadorAcimaLimite, Date dataRealizacaoAtividadeEncerrar);

	/**
	 * retorna o objeto ResolucaoDiretoria com a maior data Vig�ncia inicial
	 * 
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 08/11/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio()
			throws ControladorException;

	/**
	 * Pesquisa o im�vel para parcelamento com controle de abrang�ncia.
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 27/11/2006
	 * 
	 * @param filtroImovel
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Imovel> pesquisarImovelEfetuarParcelamento(
			FiltroImovel filtroImovel, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Boolean verificarQtdeReparcelamentoPerfil(Integer idPerfilParc,
			Short numeroReparcelamentoConsecutivos) throws ControladorException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronogramaId(
			Integer idCobrancaAcaoAtividadeCronograma)
			throws ControladorException;

	/**
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Item 1 O sistema seleciona os cronogramas de cobran�a dos grupos de
	 * cobran�a e meses de refer�ncia.
	 * 
	 * @author Pedro Alexandre
	 * @date 19/01/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarCobrancaGrupoCronogramaMes()
			throws ControladorException;

	/**
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar �nica Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @exception controladorException
	 *                controlador Exception
	 */
	public void verificarUnicaFatura(Collection colecaoContas,
			ParcelamentoPerfil parcelamentoPerfil) throws ControladorException;

	/**
	 * Metodo criado para criar os debitos para os parcelamentos q tenham juros
	 * e nao tenha criado o debito dos juros DBTP_ID = 44
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 23/02/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void gerarDebitoCobrarNaoCriados();

	/**
	 * Verificar se os itens do parcelamento(Conta, Debito a cobrar e Credit a
	 * realizar) j� est�o no historico
	 * 
	 * [UC0252] Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @date 09/04/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarItensParcelamentoNoHistorico(Integer idImovel,
			Integer idParcelamento) throws ControladorException;

	// /**
	// *
	// * Este caso de uso gera os avisos de cobran�a dos documentos de cobran�a
	// *
	// * [UC0575] Emitir Aviso de Cobran�a
	// *
	// *
	// * @author S�vio Luiz
	// * @data 02/04/2007
	// *
	// * @param
	// * @return void
	// */
	// public void emitirAvisoCobranca(Integer
	// idCronogramaAtividadeAcaoCobranca,
	// Integer idComandoAtividadeAcaoCobranca, Date dataAtualPesquisa,
	// Integer idAcaoCobranca, CobrancaGrupo grupoCobranca)
	// throws ControladorException;

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
			Integer idDocumentoCobranca) throws ControladorException;

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
	public void atualizarParcelamentoItensDebitoACobrar(
			Collection colecaoIdsDebitoACobrar) throws ControladorException;

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
	public void removerDocumentosItensDebitoACobrar(
			Collection colecaoIdsDebitoACobrar) throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * P�s-oncid��o: Resumo das a��es de cobran�a gerado e atividade encerrar da
	 * a��o de cobran�a, se for o caso, realizada
	 * 
	 * @author S�vio Luiz
	 * @date 11/05/2007
	 * 
	 */
	public void inserirResumoAcoesCobrancaCronograma(
			Object[] dadosCobrancaAcaoAtividadeCronograma,
			int idFuncionalidadeIniciada) throws ControladorException;

	// /**
	// *
	// * Este caso de uso gera os avisos de cobran�a dos documentos de cobran�a
	// *
	// * [UC0575] Emitir Parcelamento em Atraso
	// *
	// *
	// * @author S�vio Luiz
	// * @data 12/04/2007
	// *
	// * @param
	// * @return void
	// */
	// public void emitirParcelamentoEmAtraso(
	// Integer idCronogramaAtividadeAcaoCobranca,
	// Integer idComandoAtividadeAcaoCobranca, Date dataAtualPesquisa,
	// Integer idAcaoCobranca, CobrancaGrupo grupoCobranca)
	// throws ControladorException;

	/**
	 * Esta funcionalidade permite informar dados para gera��o de relat�rios ou
	 * consultas
	 * 
	 * [UC0304] - Informar Dados para Gera��o de Relat�rio ou Consulta
	 * 
	 * @author S�vio Luiz
	 * @date 22/05/2007
	 * 
	 */
	public InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsulta(
			String mesAnoFaturamento, String[] idsCobrancaGrupo,
			String[] idsGerenciaRegional,String[] idsUnidadeNegocio, Integer idEloPolo,
			Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra,
			String[] idsImovelPerfil, String[] idsLigacaoAguaSituacao,
			String[] idsLigacaoEsgotoSituacao, String[] idsCategoria,
			String[] idsEsferaPoder, String[] idsEmpresas, String tipoImpressao)
			throws ControladorException;

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
			throws ControladorException;

	/**
	 * Pesquisar rela��o de protocolos de documentos de cobran�a do eventual
	 * 
	 * @author Ana Maria
	 * @date 21/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarProtocoloDocumentoCobrancaEventual(
			Integer idCobrancaAcaoAtividadeComand) throws ControladorException;

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
	 * @author Rafael Santos,S�vio Luiz
	 * @date 16/10/2006,04/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaAcaoCronograma(
			int idCobrancaGrupoCronogramaMes) throws ControladorException;

	/**
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Retorna CAAC_TMREALIZACAO do COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos,S�vio Luiz
	 * @date 16/10/2006,04/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
			int idCobrancaAcaoCronograma, int idCobrancaAtividade)
			throws ControladorException;

	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * [FS0004] Validar Registro Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @created 05/06/2007
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @exception ControladorException
	 */
	public Object[] validarRegistroAtendimentoTransferenciaDebitoCredito(
			Integer idRA, boolean levantarExcecao) throws ControladorException;

	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * @author Raphael Rossiter
	 * @created 08/06/2007
	 * 
	 * @param idRA,
	 *            idImovelDestino
	 * @exception ControladorException
	 */
	public Integer validarTransferenciaDebitoCreditoDadosImoveis(Integer idRA,
			Integer idImovelDestino) throws ControladorException;

	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * [SB0001] - Apresentar D�bitos/Cr�ditos do Im�vel de Origem
	 * 
	 * [FS0002] - Verificar exist�ncia de d�bitos/cr�ditos no im�vel de origem
	 * 
	 * @author Raphael Rossiter
	 * @created 08/06/2007
	 * 
	 * @param idImovelOrigem
	 * @exception ControladorException
	 */
	public ObterDebitoImovelOuClienteHelper apresentarDebitoCreditoImovelOrigem(
			Integer idImovelOrigem) throws ControladorException;

	/**
	 * [UC0609] Transfer�ncia de D�bitos/Cr�ditos
	 * 
	 * [SB00002] Transfer�ncia dos D�bitos/Cr�ditos selecionados para o im�vel
	 * destino
	 * 
	 * @author Vivianne Sousa
	 * @date 09/06/2007
	 * 
	 * @param idImovelDestino
	 * @param colecaoConta
	 * @param colecaoDebitosACobrar
	 * @param colecaoCreditosARealizar
	 * @param colecaoGuiasPagamento
	 * @throws ControladorException
	 */
	public void transferirDebitosCreditos(Integer idImovelDestino,
			Collection colecaoContas, Collection colecaoDebitosACobrar,
			Collection colecaoCreditosARealizar,
			Collection colecaoGuiasPagamento, Usuario usuarioLogado,
			Integer idRegistroAtendimento, String identificadoresConta)
			throws ControladorException;

	/**
	 * [UC0594] Gerar Rela��o de Parcelamento
	 * 
	 * @author Ana Maria
	 * 
	 * @date 30/05/2007
	 */
	public Collection<RelacaoParcelamentoRelatorioHelper> filtrarRelacaoParcelamento(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento)
			throws ControladorException;

	public Collection gerarColecaoDocumentoCobrancaOrdemServico(
			CobrancaAcao cobrancaAcao,
			Date dataRealizacaoAtividadeEncerrar, Usuario usuarioLogado,
			Collection<DadosPesquisaCobrancaDocumentoHelper> colecaoCobrancaDocumentoParaAtualizar,
			Date dataPrevistaAtividadeEncerrar, Date dataComandoAtividadeEncerrar)
			throws ControladorException;

	/**
	 * [UC0XXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * @author S�vio Luiz
	 * @created 15/06/2006
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarCobrancaAcaoAtividadeComandoSemRealizacao()
			throws ControladorException;

	/**
	 * 
	 * [UCXXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * [SB0001] - Processar Documento de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/06/2007
	 * 
	 */
//	public void processarDocumentoCobrancaEventual(
//			int idCobrancaAtividadeAcaoComando, Usuario usuarioLogado,
//			int idCobrancaAcao, Integer idServicoTipoAcaoCobranca,
//			Date dataRealizacaoEncerrar) throws ControladorException;

	/**
	 * 
	 * [UCXXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * [SB0006] - Determinar Situa��o da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 19/06/2007
	 * 
	 */
	public void pesquisarDocumentosCobrancaParaGeracaoResumoEventual(
			Integer idCobrancaAtividadeAcaoComando, Usuario usuarioLogado,
			Integer idCobrancaAcao, Date dataEncerramentoPrevista,
			Date dataRealizacaoEncerrar, Date dataRealizacao)
			throws ControladorException;

	/**
	 * 
	 * [UCXXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * P�s-oncid��o: Resumo das a��es de cobran�a gerado e atividade encerrar da
	 * a��o de cobran�a, se for o caso, realizada
	 * 
	 * @author S�vio Luiz
	 * @date 15/06/2007
	 * 
	 */
	public void gerarResumoAcoesCobrancaEventual(
			Object[] dadosCobrancaAcaoAtividadeEventual,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * 
	 * [UCXXXX] Gerar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * [SB0006] - Determinar Situa��o da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/06/2007
	 * 
	 */
	public void inserirResumoAcoesCobrancaEventual(
			Object[] dadosCobrancaAcaoAtividadeEventual,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Esta funcionalidade permite informar dados para gera��o de relat�rios ou
	 * consultas
	 * 
	 * [UC0616] - Informar Dados para Consulta do Resumo das A��es de Cobran�a
	 * Eventual
	 * 
	 * @author S�vio Luiz
	 * @date 25/06/2007
	 * 
	 */
	public InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventual(
			String dataEmissaoInicial, String dataEmissaoFinal,
			String idCobrancaAcaoAtividadeComando,
			String tituloCobrancaAcaoAtividadeComando,
			String[] idsCobrancaGrupo, String[] idsGerenciaRegional,
			Integer idEloPolo, Integer idLocalidade, Integer idSetorComercial,
			Integer nmQuadra, String[] idsImovelPerfil,
			String[] idsLigacaoAguaSituacao, String[] idsLigacaoEsgotoSituacao,
			String[] idsCategoria, String[] idsEsferaPoder, String[] idsEmpresas,
			String[] idsUnidadeNegocio)
			throws ControladorException;

	
	/**
	 * Gerar Curva ABC de Debitos
	 * 
	 * [UC0621] Gerar Curva ABC de Debitos
	 * 
	 * @author Ivan S�rgio
	 * @date 07/08/2007
	 * 
	 */
	public Collection gerarCurvaAbcDebitos(
			String classificacao,
			String referenciaCobrancaInicial,
			String referenciaCobrancaFinal,
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
			String intervaloMesesCortadoSuprimidoInicial,
			String intervaloMesesCortadoSuprimidoFinal,
			String intervaloConsumoMinimoFixadoEsgotoInicial,
			String intervaloConsumoMinimoFixadoEsgotoFinal,
			String indicadorMedicao,
			String idTipoMedicao,
			String idPerfilImovel,
			String idTipoCategoria,
			String[] categoria,
			String idSubCategoria,
			String valorMinimoDebito,
			String intervaloQuantidadeDocumentosInicial,
			String intervaloQuantidadeDocumentosFinal,
			String indicadorPagamentosNaoClassificados)
		throws ControladorException;

	
	/**
	 * [UC0630] - Solicitar Emiss�o do Extrato de D�bitos
	 * Apresentar D�bitos/Cr�ditos do Im�vel 
	 * 
	 * @author Vivianne Sousa
	 * @created 21/08/2007
	 * 
	 * @param idImovel
	 * @exception ControladorException
	 */
	public ObterDebitoImovelOuClienteHelper apresentarDebitoCreditoImovelExtratoDebito(
			Integer idImovel, boolean indicadorParcelamento) throws ControladorException;
	
	/**
	 * [UC0444] Gerar e Emitir Extrato de D�bitos
	 * [UC0251] Gerar Atividade de A��o de Cobranca
	 * 
	 * Recuparea o valor da taxa de cobran�a para gerar o documento de cobran�a
	 * 
	 * gerarDocumentoCobranca
	 * 
	 * @author Vivianne Sousa
	 * @date 31/08/2007
	 * 
	 * @param imovel
	 * @param indicadorCobrancaTaxaExtrato
	 * @throws ControladorException
	 */
	public BigDecimal obterValorTaxaDocumentoCobranca (
		Imovel imovel, Short indicadorCobrancaTaxaExtrato) throws ControladorException;
	
	/**
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarRDUtilizadaPeloImovel(Integer idRD, Integer idImovel)
			throws ControladorException;
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar �nica Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 01/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoACobrarSancoes(Integer idImovel, Integer anoMesLimiteMinimo, Integer anoMesLimiteMaximo) throws ControladorException;
	
	
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
			Integer anoMesFinalReferenciaDebito, int indicadorDividaAtiva) throws ControladorException ;
	
	/**
	 * [UC0214] Inserir A��o de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @created 14/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirAcaoCobranca(CobrancaAcaoHelper cobrancaAcaoHelper)
			throws ControladorException;
	
	/**
	 * [UC0701] Informar �ndices dos Acr�scimos de Impontualidade 
	 * 
	 * @author S�vio Luiz
	 * @created 26/09/2007
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaximoAnoMesIndicesAcerscimosImpontualidade() 
			throws ControladorException;
	
	/**
	 * [UC0644] Filtrar A��o de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @param icNotasPromissoria 
	 * @param icCreditosARealizar 
	 * @created 10/10/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FiltroCobrancaAcao filtrarAcaoCobranca(String descricaoAcaoCobranca,
			String numeroDiasValidade, String idAcaoPredecessora,
			String numeroDiasEntreAcoes, String idTipoDocumentoGerado,
			String idSituacaoLigacaoAgua, String idSituacaoLigacaoEsgoto,
			String idCobrancaCriterio, String descricaoCobrancaCriterio,
			String idServicoTipo, String descricaoServicoTipo,
			String ordemCronograma, String icCompoeCronograma,
			String icAcaoObrigatoria, String icRepetidaCiclo,
			String icSuspensaoAbastecimento, String icDebitosACobrar,
			String icAcrescimosImpontualidade, String icGeraTaxa,
			String icEmitirBoletimCadastro, String icImoveisSemDebitos,
			String icMetasCronograma, String icOrdenamentoCronograma,
			String icOrdenamentoEventual, String icDebitoInterfereAcao,
			String numeroDiasRemuneracaoTerceiro,
			String icUso, String icCreditosARealizar, String icNotasPromissoria) throws ControladorException;
	
	
//	Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloGerencia(String idGerenciaRegional,
			String idCobrancaAcao) 
			throws ControladorException;
	
	//Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloLocalidade(String idLocalidadeInicial, 
			String idLocalidadeFinal, String idCobrancaAcao) 
			throws ControladorException;
	//Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloSetor(String codigoSetorComercialInicial, 
			String codigoSetorComercialFinal, String idLocalidade,
			String idCobrancaAcao) 
			throws ControladorException;
	
	//Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotas(String codigoSetorComercial, 
			String rotaInicial, String rotaFinal, String idLocalidade,
			String idCobrancaAcao) 
			throws ControladorException;
	
//	Fl�vio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloUnidadeNegocio(String idUnidadeNegocio,
			String idCobrancaAcao) 
			throws ControladorException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloGrupo(String idGrupoCobranca,
			String idCobrancaAcao) 
			throws ControladorException;

	/**
	 * [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	 * @param idCobrancaAcao
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarRotasPorCobrancaAcao(String idCobrancaAcao) 
		throws ControladorException;
	
	/**
	 * [UC0645] Manter A��o de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @created 06/11/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */

	public void atualizarAcaoCobranca(CobrancaAcao cobrancaAcao,CobrancaAcaoHelper cobrancaAcaoHelper)
			throws ControladorException;

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
			throws ControladorException;
	
	
	/**
	 * [UC0543] Associar Conjunto de Rotas a Crit�rio de Cobran�a
	 * 
	 * @author Raphael Rossiter, Anderson Italo
	 * @date 24/01/2008, 01/06/2009
	 * Adicionado registro de transa��o CRC1946
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public void associarConjuntoRotasCriterioCobranca(Collection colecaoRotas, Usuario usuarioLogado, 
			RotaAcaoCriterioHelper rotaAcaoCriterioHelper) throws ControladorException;
    
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
    public Collection pesquisarParcelamentoRDEspecial(
            Integer situacaoParcelamento, Integer idLocalidade)throws ControladorException;
    
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
            Short numeroParcelas)throws ControladorException;
    
    
    /**
	 * [UC0676] - Consultar Resumo da Negativacao
	 * 
	 * @author Marcio Roberto
	 * @date 28/02/2008
	 * 
	 * @return NegativacaoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoNegativacao(DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int tipo)
			throws ControladorException;

	/**
	 * Informa as unidades organizacionais testemunha
	 * 
	 * [UC0796] Informar Unidade Organizacional Testemunha
	 * 
	 * @author Rafael Corr�a
	 * @param usuarioLogado
	 * @date 19/05/2008
	 * 
	 */
	public void informarUnidadeOrganizacionalTestemunha(
			Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas,
			Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaRemovidas,
			Usuario usuarioLogado) throws ControladorException;
    
	/**
	 * Metodo que cria o relatorio de parcelamento de d�bitos default
	 * @author brunobarros
	 * 
	 * @param usuario
	 * @param idParcelamento
	 * @param unidadeUsuario
	 * @param colecaoFaturasEmAberto
	 * @param colecaoGuiasPagamento
	 * @param colecaoServicosACobrar
	 * @param colecaoCreditoARealizar
	 * @return
	 */
	public Object gerarRelatorioParcelamentoCobranca(
			Usuario usuario,
			String idParcelamento,
			UnidadeOrganizacional unidadeUsuario,
			Collection colecaoFaturasEmAberto,
			Collection colecaoGuiasPagamento,
			Collection colecaoServicosACobrar,
			Collection colecaoCreditoARealizar,
			boolean parcelamentoLojaVirtual);
	
	/**
	 * Atualiza o documento de cobranca apos a OS ser encerrada. Este metodo sera usado nos metodos de 
	 * encerrar OS 
	 * 
	 * @param OS
	 * @throws ControladorException
	 * 
	 * @author Francisco do Nascimento
	 * @date 16/05/2008
	 * 
	 */
	public void atualizarCobrancaDocumentoAposEncerrarOS(OrdemServico OS) throws ControladorException;
	
	/**
	 * 
	 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a
	 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada
	 * e realizar a atividade encerrar das a��es que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * Este m�todo se refere ao [SB0007] que foi separado para uma unidade de processamento
	 * 
	 * P�s-oncid��o: Resumo das a��es de cobran�a gerado e atividade encerrar da
	 * a��o de cobran�a, se for o caso, realizada
	 * 
	 * @author Francisco do Nascimento
	 * @date 16/05/2008
	 * 
	 */
	public void gerarResumoAcoesCobrancaCronogramaEncerrarOS(
		Object[] dadosCobrancaAcaoAtividadeCronograma,
		int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Atualiza cobranca documento com os campos passados na colecao
	 * [UC478 - Gerar Resumo de Acoes de cobranca]
	 * 
	 * @param colecaoCobrancaDocumento
	 * @throws ControladorException
	 * 
	 * @author Francisco do Nascimento
	 * @date 27/05/2008
	 * 
	 */
	public void atualizarCobrancaDocumento(Collection colecaoCobrancaDocumento) throws ControladorException;
	
	/**
	 * [UC478 -  GERAR RESUMO DE ACOES DE COBRANCA]
	 * Atualizar a situacao do item do documento de cobranca a partir de um pagamento realizado
	 * @param pagamento
	 * 
	 * @author Francisco do Nascimento
	 * @date 28/05/2008
	 * 
	 */
	public void atualizarSituacaoCobrancaDocumentoItemAPartirPagamento(Pagamento pagamento, 
			Integer idCobrancaDebitoSituacao) throws ControladorException;	
	
	/**
	 * Consulta as contas transferidas
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<TransferenciasDebitoHelper> consultarContasTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;
	
	/**
	 * Consulta os d�bitos a cobrar transferidos
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<TransferenciasDebitoHelper> consultarDebitosACobrarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;
	
	/**
	 * Consulta as guias de pagamento transferidas
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<TransferenciasDebitoHelper> consultarGuiasDePagamentoTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;
	
	/**
	 * Consulta os cr�ditos a realizar transferidos
	 * 
	 * [UC0204] - Consultar Transfer�ncias do D�bito
	 * 
	 * @author Rafael Corr�a
	 * @date 22/08/2008
	 */
	public Collection<TransferenciasDebitoHelper> consultarCreditosARealizarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;

	/**
	 * @author Vivianne Sousa
	 * @date 15/07/2008
	 */
	public Collection<Usuario> obterNomeCPFTestemunhas(Integer unidadeUsuario) throws ControladorException;
	
	/**
	 * [UC0852] Incluir D�bito a Cobrar de Entrada de Parcelamento N�o Paga
	 * 
	 * Este caso de uso permite incluir um d�bito a cobrar da entrada de pagamento n�o paga.
	 * 
	 * @author Raphael Rossiter
	 * @created 26/08/2008
	 * 
	 * @throws ControladorException
	 */
	public void incluirDebitoACobrarEntradaParcelamentoNaoPaga(
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Permite inserir um ComandoEmpresaCobrancaConta
	 * 
	 * [UC0866] Gerar Comando Contas em Cobran�a por Empresa
	 * 
	 * @author Rafael Corr�a
	 * @param usuarioLogado
	 * @date 28/10/2008
	 * 
	 */
	public Integer inserirComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC0870] - Gerar Movimento de Contas em Cobran�a por Empresa
	 *
	 * @author Rafael Corr�a
	 * @date 17/04/2008
	 *
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
	public void gerarMovimentoContasEmCobranca(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, int idFuncionalidadeIniciada) 
		throws ControladorException;
	
    /**
     * Gerar e Emitir Extrato de D�bito
     * [UC0349] Emitir Documento de Cobran�a
     * 
     * @author Vivianne Sousa
     * @date 15/09/2008
     */
	public Date obterDataValidadeDocumentoCobranca(CobrancaDocumento cobrancaDocumento,
			Usuario usuario,Date maiorDataVencimentoContas)throws ControladorException;
	
	/**
	 * [UC0867] Atualizar Pagamentos das Contas em Cobran�a
	 *
	 * @author S�vio Luiz
	 * @date 23/10/2008
	 *
	 * @param idLocalidade
	 * @param anoMesArrecadacao
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void atualizarPagamentosContasCobranca(Integer idLocalidade,
			Integer anoMesArrecadacao,int idFuncionalidadeIniciada)
			throws ControladorException;

	
	/**
	 * [UC0214] - Efetuar Parcelamento de D�bitos
	 *
	 * @author Raphael Rossiter
	 * @date 13/11/2008
	 *
	 * @return Collection<ResolucaoDiretoria>
	 * @throws ControladorException
	 */
	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicioPermissaoEspecial()
			throws ControladorException ;


	/**
	 * [UC0869] Gerar Arquivo Texto de Contas em Cobran�a por Empresa
	 * 
	 * Pesquisa a quantidade de contas
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Collection<GerarArquivoTextoContasCobrancaEmpresaHelper> pesquisarDadosGerarArquivoTextoContasCobrancaEmpresa(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal,int pagina) throws ControladorException;

	public Integer pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ControladorException;

	public void gerarArquivoTextoContasEmCobrancaEmpresa(Collection ids, 
															Integer idEmpresa,
															Integer idUnidadeNegocio,
															int idFuncionalidadeIniciada)
			throws ControladorException;
	
	public Collection obterUnidadeNegocioEmpresaCobrancaConta(Integer[] ids)
	throws ControladorException;
	
	/**
	 * Pesquisa a quantidade de Rotas que nao possui um Criterio definido para cada uma das Acoes de Cobrancas passadas no filtro
	 * 
	 * @author Victor Cisneiros
	 * @date 10/12/2008
	 */
	public Integer pesquisarQtdeRotasSemCriteriosParaAcoesCobranca(
			PesquisarQtdeRotasSemCriteriosParaAcoesCobranca filtro) throws ControladorException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 *
	 * @author Raphael Rossiter
	 * @date 29/09/2008
	 *
	 * @param parcelamentoPerfil
	 * @param conta
	 * @return Collection<ParcelamentoDescontoAntiguidade>
	 * @throws ControladorException
	 */
	public Collection<ParcelamentoDescontoAntiguidade> obterParcelamentoDescontoAntiguidadeParaConta(
			ParcelamentoPerfil parcelamentoPerfil, Conta conta) throws ControladorException ;
	
	/**
	 * Cancela os Documentos de Cobran�as Gerados do Cronograma ou Eventual
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public void cancelarDocumentosCobrancaDoCronogramaOuEventual(Usuario usuarioLogado, 
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ControladorException;
	
	/**
	 * Verifica se � poss�vel realizar o cancelamento dos Documentos de Cobran�a do Cronograma ou Comando
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public boolean verificarCancelamentoDocumentosCobranca(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ControladorException;

	/**
	 * [UC0868] Gerar Relatorio de Pagamentos das Contas em Cobranca por Empresa
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 08/01/2009
	 */
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(
			RelatorioPagamentosContasCobrancaEmpresaHelper helper)
			throws ControladorException;
	
	public Integer pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(
			Integer idEmpresa, Integer referenciaPagamentoInicial,
			Integer referenciaPagamentoFinal)
			throws ControladorException;
	
	/**
	 * [UC0879] Gerar Extensao de Comando de Contas em Cobran�a por Empresa [CRC1109]
	 * 
	 * Pesquisa a quantidade de contas
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 02/02/2009
	 */
	
	public Collection<GerarExtensaoComandoContasCobrancaEmpresaHelper> pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresa(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice)
			throws ControladorException;
	

	public void inserirExtensaoComandoContasCobrancaEmpresa(
			ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao,
			Collection colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper)
			throws ControladorException;
	
	/**
	 * [UC0880] Gerar Movimento de Extensao de Contas em Cobran�a por Empresa
	 * 
	 * Pesquisa os im�veis das contas
	 * 
	 * @author: R�mulo Aur�lio 
	 * @date: 09/02/2009
	 */
	
	public void gerarMovimentoExtensaoContasEmCobranca( 
			Integer idLocalidade,int idFuncionalidadeIniciada) 
	throws ControladorException;
	
	/*public Collection<Integer> pesquisarImoveisGerarMovimentoExtensaoContasEmCobrancaEmpresa(
			Integer idLocalidade,
			Integer numeroPagina) throws ControladorException ;*/
	/**
	 * [UC0878] Gerar Rela��o de Parcelamento - Vis�o Analitica
	 * 
	 * @author Bruno Barros
	 * 
	 * @date 04/02/2009
	 */
	public Collection<RelatorioRelacaoParcelamentoAnaliticoBean> filtrarRelacaoParcelamentoAnalitico(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento) throws ControladorException;	
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * [FS0028] Verifica se existeParcelas em atraso
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void verificaSeExisteParcelasEmAtraso(
			Integer idImovel, 
			Integer idResolucaoDiretoria,
			Integer refInicialInformada,
			Integer refFinalInformada)throws ControladorException;
	
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
			Integer idSetorComercialFinal)
			throws ControladorException;
	
	public Integer pesquisarDadosGerarRelatorioImoveisComAcordoCount(
			Integer idUnidadeNegocio, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idGerenciaRegional,
			Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial,
			Integer rotaFinal, Integer sequencialRotaInicial,
			Integer sequencialRotaFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal)
			throws ControladorException;
	
//	public void removerDocumentosCobrancaExcedentes(CobrancaGrupo grupoCobranca, int anoMesReferencia,
//			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
//			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
//			CobrancaAcao cobrancaAcao, int inicio, int fim) throws ControladorException;	

	/**
	 * 
	 * @author Francisco do Nascimento
	 * @date 23/04/2009
	 *
	 * @param cicloMeta
	 * @return void
	 * @throws ControladorException
	 */
	public void distribuirMetasCiclo(CicloMeta cicloMeta) throws ControladorException;
	
	/**
	 * 
	 * Atualizar metas dos grupos/localidade 
	 *
	 * @author Francisco do Nascimento
	 * @date 28/04/2009
	 *
	 * @param cicloMeta
	 * @param helpersLocalidade
	 * @throws ControladorException
	 */
	public void atualizarDistribuicaoMetasCicloGrupoLocalidade(CicloMeta cicloMeta, 
			Collection<InformarCicloMetaGrupoHelper> helpersLocalidade) throws ControladorException;

	/**
	 * 
	 * Dado um objeto de cicloMeta, consultar a colecao de ciclo meta grupo, agrupando 
	 * por gerencia, unidade de negocio e localidade
	 * 
	 * @author Francisco do Nascimento
	 * @date 13/05/2009
	 *
	 * @param cicloMeta Objeto ciclo meta 
	 * @return Helpers com os agrupamentos definidos de ciclo meta grupo
	 */
	public TreeMap<String, InformarCicloMetaGrupoHelper> consultarColecaoCicloMetaGrupo(CicloMeta cicloMeta) 
		throws ControladorException;
	
	/**
	 * 
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author R�mulo Aur�lio
	 * @date 28/05/2009
	 * 
	 * @return
	 */
	public void inserirSituacaoEspecialCobranca(
			Collection colecaoImoveisParaSerInseridos, 
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper,
			Usuario usuarioLogado,
			Integer idCobrancaSituacaoTipo,
			Integer anoMesReferenciaInicial,
			Integer anoMesReferenciaFinal)
			throws ControladorException;
	
	/**
	 * [UC0177] Informar Situacao Especial Cobranca
	 * 
	 * @author R�mulo Aur�lio
	 * @created 27/05/2009
	 * 
	 */
	public Integer inserirCobrancaSituacaoComando(
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper, 
			boolean retirar)
			throws ControladorException ;
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
	public  Integer  pesquisarResolucaoDiretoriaComPercentualDoacao()throws ControladorException;
	
	/**
     * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Crian�a para Negocia��o a Vista
     * 
     * @author: Vivianne Sousa
     * @date: 11/06/2009
     */
	 public void gerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(
	           Integer idRota , Integer idFuncionalidadeIniciada)
            throws ControladorException;
    
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
			Integer idGrupoFaturamento) throws ControladorException;
	
	/**
	 * [UC0910] Emitir Cartas da Campanha de Solidariedade da crian�a para Negocia��o a Vista
	 * 
	 * @author Vivianne Sousa
	 * @data 17/06/2009
	 * 
	 * @param
	 * @return void
	 */
	public void emitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(Integer idGrupoFaturamento, 
			Integer idFuncionalidadeIniciada)throws ControladorException;
	
	/**
	 * 
	 * Esse metodo est� de acordo com o UC0919 - Gerar Relatorio de Impostos Por Cliente Responsavel. Dessa forma,
	 * esse m�todo realiza a pesquisa por impostos por cliente respons�vel, agrupando os impostos a partir das
	 * faturas de cada cliente. Para esse caso de uso, todos os CLIENTES devem ser FEDERAIS, dessa forma a pesquisa
	 * est� sendo realizada por cliente respons�veis federais, ou seja clientes que tem na tabela cadastro.cliente_tipo
	 * EPOD_ID = A CONSTANTE DE CLIENTE FEDERAL DO SISTEMA (EX:3). - continuar descricao -
	 *
	 * @author Jose Guilherme Macedo Vieira
	 * @date 08/07/2009
	 *
	 * @param Integer anoMes - o ano/mes j� formatado, sem barra, na forma de Integer
	 * @param Integer clienteID - o id do cliente
	 * @param String tipoRelatorio (SINTETICO ou ANALITICO)
	 * @return Collection<RelatorioImpostosPorClienteResponsavelHelper> - a cole��o de helpers do relatorio
	 * @throws ControladorException
	 */
	public Collection<ImpostoDeduzidoHelper> pesquisarImpostosPorClienteResponsavelFederal(
			Integer anoMes, 
			Integer clienteID, 
			String tipoRelatorio) throws ControladorException;
	
	/**
	 * Faz parte de [UC0216] Calcular Acr�scimo por Impontualidade Santos Data:
	 * 09/01/2006 Dados do Indices Acrescimo Impontualidade
	 * 
	 * @param anoMesReferenciaDebito
	 *            Ano M�s de Referencia de D�bito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarIndiceAcrescimoImpontualidade(
			int anoMesReferenciaDebito) throws ControladorException;
	
	public Collection<RelatorioNotificacaoDebitoBean> gerarRelatorioNotificacaoDebito(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, int tamanhoMaximoDebito, String quantidadeRelatorios, String tipoEnderecoRelatorio) throws ControladorException;
	
	/**
	 * 
	 * Dado um objeto de cicloMeta, consultar a colecao de ciclo meta grupo, agrupando 
	 * por gerencia, unidade de negocio e localidade
	 * 
	 * @author Genival Barbosa
	 * @date 04/08/2009
	 *
	 * @param cicloMeta Objeto ciclo meta 
	 * @return Helpers com os agrupamentos definidos de ciclo meta grupo
	 */
	public List consultarColecaoCicloMetaGrupoRelatorio(CicloMeta cicloMeta) 
		throws ControladorException;

	
	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa
	 * - Pesquisa dados do popup
	 * @author Hugo Amorim
	 * @throws ControladorException 
	 * @throws ErroRepositorioException  
	 */
	public Object[] pesquisarDadosPopupExtensaoComando
		(Integer idComando,Date dateInicial, Date dateFinal) throws ControladorException;
	
	/**
	 * [UCXXXX] - Gerar Conta
	 *
	 * @author Rafael Corr�a
	 * @date 22/07/2009
	 *
	 * @param anoMes
	 * @param idFaturamentoGrupo
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param codigoSetorComercialInicial
	 * @param codigoSetorComercialFinal
	 * @param codigoRotaInicial
	 * @param codigoRotaFinal
	 * @return Collection<RelatorioContaBean>
	 * @throws ControladorException
	 */
	public List<RelatorioContaBean> pesquisarDadosContaRelatorio(
			Integer anoMes, Integer idFaturamentoGrupo,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal,
			Short codigoRotaInicial, Short codigoRotaFinal,
			Short sequencialRotaInicial, Short sequencialRotaFinal, String indicadorEmissao, String indicadorOrdenacao) throws ControladorException;

	/**
	 * 
	 * UC0905 - Gerar Relatorio Acompanhamento Acao Cobran�a
	 * 
	 * 
	 * @author Genival Barbosa
	 * @date 26/08/2009
	 * 
	 * @param RelatorioAcompanhamentoAcoesCobrancaHelper helper
	 *            
	 * @return Lista de acoes de cobranca
	 */
	public List consultarColecaoAcaoCobranca(RelatorioAcompanhamentoAcoesCobrancaHelper helper) 
		throws ControladorException;
	
	/**
	 * retorna conjunto de CAAC_ID(a��es do ciclo) selecionados
	 * [UC0258] Filtrar Documentos de Cobran�a
	 * 
	 * @author Anderson Italo
	 * @data 03/08/2009
	 */
	public Collection<Integer> pesquisarIdsAcoesCiclo(Collection<Integer> idsAcao, 
								Integer anoMesReferencia) throws ControladorException;
	
	/**
	 * Este m�todo est� de acordo com o UC[0258]Filtrar Documento de Cobranca,
	 * � utilizado pelo relat�rio filtrar documentos de cobran�a
	 * 
	 * @author Anderson Italo
	 * @date 19/08/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return List
	 * @throws ControladorException
	 */
	public List filtrarCobrancaDocumento(FiltrarDocumentoCobrancaHelper filtro)
			throws ControladorException;
	
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
	 * @throws ControladorException
	 */
	public Integer filtrarCobrancaDocumentoCount(FiltrarDocumentoCobrancaHelper filtro)
			throws ControladorException;
	
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
	public List filtrarSupressoesReligacoesReestabelecimentos(FiltroSupressoesReligacoesReestabelecimentoHelper filtro)
		throws ControladorException;
	
	/**
	 *  Solicitar Emiss�o do Extrato de D�bitos
	 * Author: Vivianne Sousa 
	 * Data: 14/09/2009
	 * 
	 * Obtem os parcelamentos de d�bitos efetuados que estejam com situa��o normal
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentosSituacaoNormal(Integer idImovel) 
		throws ControladorException;
	
	/**
	 * [UC0959] Gerar Arquivo Texto de Pagamentos das Contas em Cobran�a por Empresa
	 * 
	 * @author: Hugo Amorim
	 * @date: 05/10/2009
	 */
	public void gerarArquivoTextoPagamentosContasEmCobrancaEmpresa(
			Integer idEmpresa,Integer referenciaInicial, Integer referenciaFinal, 
			int idFuncionalidadeIniciada, Integer idUnidadeNegocio)
			throws ControladorException;


	public Collection obterUnidadeNegocioPagamentosEmpresaCobrancaConta()
			throws ControladorException;
	
	/**
	 * Este m�todo est� de acordo com o UC[0258]Filtrar Documento de Cobranca
	 * 
	 * @author Anderson Italo
	 * @date 11/09/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return Collection<CobrancaDocumento>
	 * @throws ControladorException
	 */
	public Collection<CobrancaDocumento> consultarCobrancaDocumento(
			FiltrarDocumentoCobrancaHelper filtro) throws ControladorException;
	
	/**
	 * Este m�todo est� de acordo com o [UC0901] Gerar Metas do Ciclo
	 * 
	 * @author Anderson Italo
	 * @date 21/09/2009
	 *
	 * @param Integer idCicloMeta
	 * @throws ControladorException
	 */
	public void removerCicloMetaGrupo(Integer idCicloMeta) throws ControladorException;
	
	/**
	 * 
	 * [UC0000] - Gerar Metas do Cilo
	 * Pesquisar a quantidade de imoveis em cada grupo/localidade
	 *
	 * @author Anderson Italo
	 * @date 25/09/2009
	 *
	 * @param idsLast Colecao de ids de situacao de liga��o de �gua
	 * @return Colecao no formato [idGrupo, idLocalidade, qtdImoveis]
	 */
	public Collection pesquisarQuantidadeImoveisPorGrupoLocalidade(Collection colecaoIdsSituacaoLigacaoAgua) throws ControladorException;
	
	/**
	 * [UC0960] Transferir Rotas entre Grupos e Empresas
	 *
	 * @author Anderson Italo
	 * @date 26/10/2009
	 * 
	 * @param idGrupoFaturamentoDestino
	 * @param idGrupoCobrancaDestino
	 * @param idEmpresaFaturamentoDestino
	 * @param idEmpresaCobrancaDestino
	 * @param usuarioLogado
	 */
	public void transferirRotasEntreGrupoEmpresa(FaturamentoGrupo grupoFaturamentoDestino,CobrancaGrupo grupoCobrancaDestino,
			Empresa empresaFaturamentoDestino,Empresa empresaCobrancaDestino, 
			Collection colecaoRotas,Usuario usuarioLogado)throws ControladorException;
	
	
	/**
	 * [UC????] Relatorio Comando Documento Cobranca
	 * Alterado para verificar tipo da a��o a partir da tabela documento_tipo
	 * @author R�mulo Aur�lio, Anderson Italo
	 * 
	 * @data 20/10/2009, 04/05/2010
	 */
	public DocumentoTipo pesquisarTipoAcaoCobrancaParaRelatorio(
			Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma)
			throws ControladorException ;
	
	/**
     * [UCXXXX] - Gerar Relatorio Comando Documento Cobranca
     *
     * @author R�mulo Aur�lio
     * @date 20/10/2009
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<RelatorioComandoDocumentoCobrancaHelper> gerarRelatorioComandoDocumentoCobranca(
            Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando)
            throws ControladorException ;
    
	/**
     * [] - 
     * 
     * @author: Vivianne Sousa
     * @date: 06/11/2009
     */
    public void gerarCartasDeFinalDeAno(Integer idRota , Integer idFuncionalidadeIniciada)
            throws ControladorException;
    
    /**
     * [] - 
     * 
     * @author: Vivianne Sousa
     * @date: 10/11/2009
     */
	public void emitirCartasDeFinalDeAno(Integer idGrupoFaturamento, 
			Integer idFuncionalidadeIniciada)throws ControladorException;
	
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
	public boolean existeCobrancaDocumentoDoImovel(
			Integer idImovel, Integer idDocumentoTipo)throws ControladorException;

	/**
	 * 
	 * Este caso de uso permite a emiss�o de um ou mais documentos de cobran�a
	 * 
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param
	 * @return void
	 */
	public void emitirDocumentoCobranca(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException;
	
	/**
	 *[UC0349] Emitir Documento de Cobran�a � Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
     */
	public void emitirDocumentoCobranca(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca)throws ControladorException;
	/**
	 * @author Anderson Italo
	 * @date 26/11/2009
	 * 
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorGrupoCobranca(Integer idCobrancaGrupo, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra)
			throws ControladorException;
	
	/**
	 * @author Anderson Italo
	 * @date 30/11/2009
	 * 
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorComandoEventual(Integer idCobrancaAcaoAtividadeComando, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra)
			throws ControladorException;
	
	/**
	 * Identifica e obt�m as informa��es necess�rias para confirmar pagamento(s) por cart�o de cr�dito
	 * ou d�bito 
	 *
	 * @author Raphael Rossiter
	 * @date 05/01/2010
	 *
	 * @param modalidadeCartao
	 * @param matriculaImovel
	 * @return ObterDadosConfirmarCartaoCreditoDebitoHelper
	 * @throws ControladorException
	 */
	public ObterDadosConfirmarCartaoCreditoDebitoHelper obterDadosConfirmarCartaoCreditoDebito(Short modalidadeCartao,
			Integer matriculaImovel) throws ControladorException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [FS0008] � Verificar validade da data
	 *
	 * @author Raphael Rossiter
	 * @date 15/01/2010
	 *
	 * @param dataConfirmacaoOperadora
	 * @param idCliente
	 * @param idArrecadacaoForma
	 * @throws ControladorException
	 */
	public void verificarValidadeData(Date dataConfirmacaoOperadora, Integer idCliente, Integer idArrecadacaoForma) 
		throws ControladorException;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [FS0007] � Somat�rio Inv�lido
     * [FS00010] � Somat�rio Inv�lido
	 *
	 * @author Raphael Rossiter
	 * @date 08/01/2010
	 *
	 * @param colecaoTransacao
	 * @param valorTotalParaQuitacao
	 * @throws ControladorException
	 */
	public void verificarSomatorio(Collection colecaoTransacao, BigDecimal valorTotalParaQuitacao) 
		throws ControladorException;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @param idParcelamentoSelecionado
	 * @param colecaoTransacao
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void confirmarCartaoCredito(Integer idParcelamentoSelecionado, 
		Collection colecaoTransacao, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 *  [FS0005 � Alerta Parcelamento Com Parcela Paga]
	 *  [FS0006 � Parcelamento Sem D�bito a Cobrar]
	 *  [FS0007 � Parcelamento Com Pagamento de Cart�o de Cr�dito J� Informado]
	 *  
	 * @author Raphael Rossiter
	 * @date 14/01/2010
	 *
	 * @param parcelamento
	 * @return int
	 * @throws ControladorException
	 */
	public int validarParcelamentoCartaoCredito(Parcelamento parcelamento) throws ControladorException;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0004] � Incluir Dados da Confirma��o dos Pagamentos 
	 *
	 * @author Raphael Rossiter
	 * @date 29/04/2010
	 *
	 * @param parcelamentoCartaoCreditoHelper
	 * @param colecaoConta
	 * @param colecaoGuia
	 * @param colecaoDebitoACobrar
	 * @param colecaoParcelamento
	 * @param colecaoAntecipacaoDebitosDeParcelamento
	 * @param colecaoAntecipacaoCreditosDeParcelamento
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void confirmarCartaoDebito(ParcelamentoCartaoCreditoHelper parcelamentoCartaoCreditoHelper, 
		Collection<ContaValoresHelper> colecaoConta, Collection<GuiaPagamentoGeral> colecaoGuia,
		Collection<DebitoACobrarGeral> colecaoDebitoACobrar, Collection<Parcelamento> colecaoParcelamento,
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento,
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento,
		Usuario usuarioLogado) throws ControladorException;
	
	
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
			Integer idArrecadacaoForma) throws ControladorException ;
	
	
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
	public GuiaPagamento pesquisarGuiaPagamentoCartaoCredito(Integer idCliente, Date dataVencimento)
	throws ControladorException;
	
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0003] � Inserir Guia de Pagamento Cliente
	 *
	 * @author Raphael Rossiter
	 * @date 02/02/2010
	 *
	 * @param idCliente
	 * @param dataVencimento
	 * @param valorDebito
	 * @param idDebitoTipo
	 * @param usuarioLogado
	 * @return GuiaPagamento
	 * @throws ControladorException
	 */
	public GuiaPagamento inserirGuiaPagamentoCliente(Integer idCliente, Date dataVencimento,
			BigDecimal valorDebito, Integer idDebitoTipo, Usuario usuarioLogado) throws ControladorException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @param guiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarGuiaPagamentoCartaoCredito(GuiaPagamento guiaPagamento) throws ControladorException ;
	
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idArrecadador
	 * @param valorPagamento
	 * @return BigDecimal
	 * @throws ControladorException
	 */
	public BigDecimal calcularValorDeducao(Integer idArrecadador, BigDecimal valorPagamento, Integer idArrecadacaoForma) 
		throws ControladorException;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 * 
	 * [SB0005  � Calcular Valor da  Dedu��o] 
	 *
	 * @author Raphael Rossiter
	 * @date 02/02/2010
	 *
	 * @param avisoBancario
	 * @param valorDeducao
	 * @throws ControladorException
	 */
	public void gerarAtualizarAvisoDeducoes(AvisoBancario avisoBancario, BigDecimal valorDeducao) 
		throws ControladorException ;
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @return Localidade
	 * @throws ErroRepositorioException
	 */
	public Localidade pesquisarLocalidadeSede() throws ControladorException ;
	
	/**
	 * [UC0987] � Inserir Faixa de Dias Vencidos para Documentos a Receber
	 * 
	 * @author Hugo Leonardo
	 * @date 22/02/2010
	 *
	 * @param documentosReceberFaixaDiasVencidos
	 * @throws ControladorException
	 */
	public Integer inserirDocumentosReceberFaixaDiasVencidos(DocumentosReceberFaixaDiasVencidos documentosReceberFaixaDiasVencidos,
			Usuario usuarioLogado) throws ControladorException;
	
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
		throws ControladorException;
	
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
	public Boolean verificarExistenciaFaixaFinal(Integer valorFinalFaixa) 
		throws ControladorException;
	
	
	/**
	 * 
	 * [UC0995] Emitir Declara��o Transferencia de D�bitos/Dr�ditos
	 * 
	 * Prepara os beans para o relat�rio.
	 * 
	 * @author Daniel Alves 
     * @throws ControladorException 
	 * @data  02/03/2010
	 *
	 * @return List<Bean>
	 */
	public Collection<RelatorioEmitirDeclaracaoTransferenciaDebitoBean> 
	   gerarRelatorioEmitirDeclaracaoTransferenciaDebitoCredito(String clienteUsuarioDestino,
			   String clienteUsuarioOrigem, String valorNovaConta, 
			   String indicadorTipoEmissao, String municipio)throws ControladorException;
	
	/**
	 * 
	 * Calcula Valor total de debitos
	 * 	
	 * 		Valor Contas + Valor Debitos + Valor Guias - Creditos				
	 * 
	 * @author Hugo Amorim
	 * @param debitoImovelClienteHelper
	 * @return Valor Total de Debitos
	 */
	public BigDecimal calcularValorTotalDebitos(
			ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper);
	
	/**
	 * 
	 * Calcula Valor Contas de debitos
	 * 	
	 * @author Hugo Amorim
	 * @param debitoImovelClienteHelper
	 * @return Valor Total de Debitos
	 */
	public BigDecimal calcularValorContas(
			ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper);

/** 
	 * [UC990] Gerar Relat�rio de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @date 22/02/2010
	 *
	 */
	public Collection pesquisarRelatorioDocumentosAReceber(
			FiltroRelatorioDocumentosAReceberHelper helper) throws ControladorException;
	
	/** 
	 * [UC990] Count Relat�rio de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @date 22/02/2010
	 *
	 */
	public Integer countRelatorioDocumentosAReceber(
			FiltroRelatorioDocumentosAReceberHelper helper)
		throws ControladorException;
	
	/**
	 * [UC0630] Solicitar Emiss�o do Extrato de D�bitos 
	 *
	 * @author Raphael Rossiter
	 * @date 30/03/2010
	 *
	 * @param debitoACobrar
	 * @param quantidadeParcelas
	 * @throws ControladorException
	 */
	public void verificarQuantidadeParcelasInformada(DebitoACobrar debitoACobrar,
			Short quantidadeParcelas) throws ControladorException;
	
	/**
	 *[UC0349] Emitir Documento de Cobran�a � Cartas Campanha
	 * 
	 * @author Hugo Amorim
	 * @date 23/04/2010
	 */
	public void emitirDocumentoCobrancaCartasCampanha(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca)throws ControladorException;
	
	/**
	 * [UC????] Relatorio Comando Documento Cobranca
	 * Retorna a a��o de cobran�a para exibi��o 
	 * de parametros do relat�rio
	 * 
	 * @author Anderson Italo
	 * @data 04/05/2010
	 */
	public CobrancaAcao pesquisarAcaoCobrancaParaRelatorio(
			Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma)
			throws ControladorException;

	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a - step1
	 *
	 * @author Pedro Alexandre, Ivan Sergio, Raphael Rossiter,Vivianne Sousa
	 * @date 01/02/2006, 18/05/2009, 20/07/2009 , 05/04/2010
	 *
	 */
	public void gerarAtividadeAcaoCobranca(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Rota rota, CobrancaAcao acaoCobranca,
			CobrancaAtividade atividadeCobranca, Integer indicadorCriterio,
			CobrancaCriterio criterioCobranca, Cliente cliente,
			ClienteRelacaoTipo relacaoClienteImovel,
			String anoMesReferenciaInicial, String anoMesReferenciaFinal,
			Date dataVencimentoInicial, Date dataVencimentoFinal,
			Date dataAtual, int idFuncionalidadeIniciada,Cliente clienteSuperior,
			Integer idCobrancaDocumentoControleGeracao)
			throws ControladorException;
	
	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a - step2
	 *
	 * @author Pedro Alexandre, Ivan Sergio, Raphael Rossiter,Vivianne Sousa
	 * @date 01/02/2006, 18/05/2009, 20/07/2009 , 05/04/2010
	 *
	 */
	public void atualizarComandoAtividadeAcaoCobranca(
			CobrancaGrupo grupoCobranca, int anoMesReferenciaCicloCobranca,
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			CobrancaAcao acaoCobranca, Integer indicadorCriterio,
			CobrancaCriterio criterioCobranca, 
			int idFuncionalidadeIniciada,Integer idCobrancaDocumentoControleGeracao)
			throws ControladorException;
	
	/**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a - step3
	 *  
	 * @author Pedro Alexandre, Ivan Sergio, Raphael Rossiter,Vivianne Sousa
	 * @date 01/02/2006, 18/05/2009, 20/07/2009 , 05/04/2010
	 *
	 */
	public void emitirDocumentoCobranca(CobrancaGrupo grupoCobranca, 
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			CobrancaAcao acaoCobranca, CobrancaCriterio criterioCobranca, 
			Date dataAtual, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 29/04/2010
	 */
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(
			Integer idImovel) throws ControladorException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 03/05/2010
	 */
	public Collection pesquisarDadosImovelCobrancaSituacaoPorImovel(
			Integer idImovel) throws ControladorException;
	
	/**
	 * 
	 * Este caso de uso permite a emiss�o de um ou mais documentos de cobran�a
	 * 
	 * @author Hugo Amorim
	 * @data 22/04/2010
	 * 
	 * @param
	 * @return void
	 */
	public void gerarDocumentoCobrancaImpressaoCartasCampanha(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException;
	
	/**
	 * 
	 * 
	 * [UC0968] Emitir Cartas da Campanha
	 * 
	 * @author Hugo Amorim
	 * @data 22/04/2010
	 * 
	 * @param
	 * @return void
	 */
	public void emitirCartasCampanha(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException;
	
	public Collection pesquisarImovelCobrancaSituacao(Integer idImovel)throws ControladorException ;
	
	/**
	 * [UC0252] � Consultar Parcelamentos de D�bitos
	 *
	 * @author Raphael Rossiter
	 * @date 20/05/2010
	 *
	 * @param colecaoParcelamentoPagamentoCartaoCredito
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void atualizarParcelamentoPagamentoCartaoCredito(Collection colecaoParcelamentoPagamentoCartaoCredito, Usuario usuarioLogado) 
		throws ControladorException ;
	
	/**
	 * [UC0252] � Consultar Parcelamentos de D�bitos 
	 *
	 * @author Raphael Rossiter
	 * @date 25/05/2010
	 *
	 * @param idParcelamento
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean parcelamentoPagamentoCartaoCreditoJaConfirmado(Integer idParcelamento) throws ControladorException;
	
	/**
	 * [UC0998] Gerar Rela��o de Parcelamento - Vis�o Cart�o de Cr�dito
	 * 
	 * Bean que preencher� o relatorio
	 * 
	 * @author Hugo Amorim
	 * @date 11/06/2010
	 *
	 */
	public Collection<RelatorioRelacaoParcelamentoCartaoCreditoBean> filtrarRelacaoParcelamentoCartaoCredito(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento) throws ControladorException;	
	
	/**
	 * 
	 * [UC1038] Prescrever D�bitos de Im�veis
	 * 
	 * @author Hugo Leonardo
	 * @date 07/07/2010
	 * 
	 */
	public void gerarPrescreverDebitosDeImoveis( Integer idFuncionalidadeIniciada, 
			Integer anoMesFaturamento, Date dataPrescricao, Integer usuario, String idsCobrancaSituacao) 
		throws ControladorException ;
	
	/**
	 * 
	 * [UC1038] Prescrever D�bitos de Im�veis
	 * 
	 * @author Hugo Leonardo
	 * @date 08/07/2010
	 * 
	 */
	public Collection obterCobrancaSituacaoParaPrescreverDebitos() throws ControladorException;
	
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
			Integer idComando)throws ControladorException;
	
	/**
	 * Pesquisa Documentos de cobran�as validos para imovel para determinado tipo de documento
	 * 
	 * @author Hugo Amorim
	 * @date 09/09/2010
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoValidoImovel(Integer idImovel,Integer idDocumentoTipo,
		Integer idAcaoCobranca)throws ControladorException;
	
	/**
	 * [UC0630] Solicitar Emiss�o do Extrato de D�bitos
	 * 
	 * [SB0001] � Calcular valor dos descontos pagamento � vista.
	 * 
	 * @author Vivianne Sousa
	 * @date 21/10/2010
	 */
	public NegociacaoOpcoesParcelamentoHelper calcularValorDosDescontosPagamentoAVista(
			ObterOpcoesDeParcelamentoHelper helper) throws ControladorException;
	public Integer recuperaMaiorAnoMesContasParcelamento(Collection colecaoContas) throws ControladorException;
	
	/**
	 * 
	 * [UC1112] Processar Encerramento Ordens de Servi�o da A��o de Cobran�a
	 * 
	 * Ordens de Servi�o que n�o tenham sido executadas encerradas por decurso de prazo
	 * 
	 * @author Mariana Victor
	 * @date 02/12/2010
	 * 
	 */
	public void processarEncerramentoOSAcaoCobranca(
			Integer dadosAtividadeCronograma, Integer idCobrancaAcaoAtividadeCronograma,
			int idFuncionalidadeIniciada) throws ControladorException;
	
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
			throws ControladorException;
	
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
			throws ControladorException;
	
	
	/**
	 * [UC676] Consultar Resumo Negativa��o
	 * 
	 * @author Ivan Sergio
	 * @date 14/01/2011
	 * 
	 * @param dadosConsultaNegativacaoHelper
	 * @param idSituacaoDebito
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoNegativacaoLigacaoAguaPorSituacaoDebito(
			DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int idSituacaoDebito)
			throws ControladorException;
	
	/**
	 * [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Vcitor
	 * @date 17/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioVisitaCobrancaBean> gerarRelatorioVisitaCobranca(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			int tamanhoMaximoDebito, String quantidadeRelatorios, Collection<CobrancaDocumento> colecaoDocumentoCobranca) throws ControladorException;	
	
	/**
	 * [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Vcitor
	 * @date 20/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioDocumentoCobrancaOrdemCorteBean> gerarRelatorioDocumentoCobrancaOrdemCorte(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			int tamanhoMaximoDebito, String quantidadeRelatorios) throws ControladorException;

	/**
	 * [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Victor
	 * @date 25/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioDocumentoCobrancaOrdemFiscalizacaoBean> gerarRelatorioDocumentoCobrancaOrdemFiscalizacao(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, int tamanhoMaximoDebito,
			String quantidadeRelatorios) throws ControladorException;
	
	/**
	 *  [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 20/01/2011
	**/
	public List<String> pesquisarTipoDeCorte() throws ControladorException;

	/**
	 *  [UC0xxx] Emitir Documentos de Cobran�a Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 26/01/2011
	**/
	public List<String> pesquisarOcorrenciasFiscalizacao() throws ControladorException;

	/**
	 * [UC0919] Gerar Relat�rio de Impostos Por Cliente Respons�vel
	 * 
	 * @author Diogo Peixoto
	 * @date 24/03/2011
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<ImpostoDeduzidoHelper> pesquisarImpostosArrecadacaoClienteResponsavelFederal(
			Integer anoMes, Integer clienteID, String tipoRelatorio) throws ControladorException;
	


	/**
	 * [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 * 
	 *  Pesquisa os Itens de Servi�o relacionados ao boletim de medi��o de cobran�a selecionado
	 *  
	 * @author Mariana Victor
	 * @date 21/03/2011
	 * 
	 * @param FiltrarRelatorioBoletimMedicaoCobrancaHelper
	 * 
	 * @return Collection<RelatorioBoletimMedicaoCobrancaHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioBoletimMedicaoCobrancaHelper> pesquisarItensServico(
			FiltrarRelatorioBoletimMedicaoCobrancaHelper helper, String tipoCobrancaBoletim) throws ControladorException;

	/**
	 *  [UC1153] Solicitar Gera��o/Emiss�o Boletim de Medi��o de Cobran�a
	 *  
	 *  [FS0002] � A��es n�o encerradas no cronograma.
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Integer pesquisarAcoesEncerradasCronograma(Integer anoMesReferencia, Integer idCobrancaGrupo)
		throws ControladorException;

	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  De acordo com o c�digo da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	**/
	public Object[] obterQuantidadeOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;

	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  De acordo com o c�digo da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	**/
	public Object[] obterSomatorioOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;
	
	
	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  De acordo com o c�digo da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	**/
	public Object[] obterQuantidadeOSBoletimMedicaoCobrancaDesconto(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;

	
	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  Consulta os valores da totaliza��o da taxa de sucesso.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	**/
	public Object[] obterTotalizacaoOSBoletimMedicaoCobrancaSucesso(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;
	

	/**
	 *  [UC1152] Emiss�o Boletim Medi��o Cobran�a
	 *  
	 *  Pesquisa dados da empresa e do contrado do boletim de cobran�a
	 * 
	 * @author Mariana Victor
	 * @created 24/03/2011
	**/
	public Object[] pesquisarDadosBoletimMedicaoCobranca(Integer anoMesReferencia, Integer idCobrancaGrupo)
		 throws ControladorException;
	

	
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
	 * @throws ControladorException
	 */
	public RelatorioAnalisePerdasCreditosBean gerarRelatorioAnalisePerdasCreditos(String anoMesReferencia)  throws ControladorException;
	
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
	 * @throws ControladorException 
	 */
	public int maiorAnoMesReferenciaDocumentosAReceberResumo() throws ControladorException;

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
	public void gerarBoletimMedicao(Integer idGrupoCobranca, Integer referencia,Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoImovelPerfil(Integer idComando) throws ControladorException;
	
	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoGerenciaRegional(Integer idComando) throws ControladorException;
	
	/**
	 * [UC0879] Gerar Extens�o de Comando de Contas em Cobran�a por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoUnidadeNegocio(Integer idComando) throws ControladorException;

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * Pesquisa a Situa��o de cobran�a a partir do c�digo constante.
	 * 
	 * @author: Mariana Victor
	 * @date: 18/04/2011
	 */
	public Integer pesquisarCobrancaSituacao(Integer codigoConstante) throws ControladorException;
	
	/**
	 * [UC1136] Inserir Contrato Parcelamento Por Cliente
	 * 
	 * Pesquisa a Referencia da conta por ID
	 * 
	 * @author: Paulo Diniz
	 * @date: 14/05/2011
	 */
	public Integer pesquisarReferenciaContaPorId(Integer idConta)throws ControladorException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * @author R�mulo Aur�lio
	 * @throws ErroRepositorioException 
	 * @date 12/05/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamento(
			String numeroParcelamento) throws ControladorException;
	
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * [FS0008] - Verificar possibilidade de cancelamento do contrato de parcelamento por cliente
	 * @author R�mulo Aur�lio
	 * @throws ErroRepositorioException 
	 * @date 12/05/2011
	 */
	public void verificarPossibilidadeCancelamentoContratoParcelamento(ContratoParcelamento contratoParcelamento) 
	throws ControladorException;
	
	

	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * 
	 * Pesquisa os dados dos comandos
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 */
	public Collection<ConsultarComandosContasCobrancaEmpresaHelper> pesquisarConsultarComandosContasCobrancaEmpresa(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal,int pagina) throws ControladorException;
	
	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * 
	 * Pesquisa os dados de um comando para exibir no popup
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011 
	 */
	public Object[] pesquisarDadosPopupExtensaoComandoCobranca
		(Integer idComando,Date dateInicial, Date dateFinal) throws ControladorException;
	
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
		(Integer idComando) throws ControladorException;

	/**
	 * [UC1168] Encerrar Comandos de Cobran�a por Empresa
	 *
	 * @author Mariana Victor
	 * @created 09/05/2011
	 */
	public void encerrarComandosCobrancaPorEmpresa(Integer idFuncionalidadeIniciada, String idEmpresa, 
			Usuario usuarioLogado, Integer idComando, Integer idCobrancaSituacao) throws ControladorException;
	
	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Encerrar ordem(ns) de servi�o.
	 * 
	 * @author: Mariana Victor
	 * @date: 17/05/2011
	 */
	public void movimentarOrdemServicoEncerrarOS(MovimentarOrdemServicoEncerrarOSHelper helper, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * 
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Gerar OS
	 * 
	 * @author Mariana Victor
	 * @data 17/05/2011
	 */
	public Collection<Integer> pesquisarIdsImoveis(MovimentarOrdemServicoGerarOSHelper helper) throws ControladorException;

	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Emitir OS Gerada pela Empresa
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Object[]> pesquisarDadosOSGeradasPelaEmpresa(Integer idComando, Integer idTipoServico) throws ControladorException;

	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Emitir OS de Registro de Atendimento
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Object[]> pesquisarDadosOSRegistroAtendimento(Integer idComando, Integer idTipoServico) throws ControladorException;

	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * [SB0001] � Cancelar Contrato de Parcelamento Por Cliente
	 * @author R�mulo Aur�lio
	 * @throws ControladorException 
	 * @date 12/05/2011
	 */
	public void cancelarContratoParcelamentoCliente(
			ContratoParcelamento contratoParcelamento, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Atualizar a situacao do item do documento de cobranca a partir de um pagamento realizado, informando a data de pagamento
	 * @param pagamento
	 * 
	 * @author Mariana Victor
	 * @date 08/06/2011
	 */
	public void atualizarSituacaoCobrancaDocumentoItemAPartirPagamento(Pagamento pagamento, 
			Integer idCobrancaDebitoSituacao, Date dataPagamneto) throws ControladorException;

	public Collection<ContaValoresHelper> pesquisarContasDebito(
			Integer idCliente, Short relacaoTipo, Integer idImovel,
			Collection idImoveis, Collection idImoveisAtuais,
			int indicadorDebito, int indicadorPagamento, int indicadorConta,
			int indicadorCalcularAcrescimoImpontualidade,
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVencimentoDebito,
			Date anoMesFinalVencimentoDebito, String anoMesArrecadacao, int indicadorDividaAtiva)throws ControladorException;
	

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
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVencimentoDebito, 
			Date anoMesFinalVencimentoDebito, 
			int indicadorDividaAtiva)
	throws ControladorException;
	

	/**
	 * 
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobran�a
	 * 
	 * @author Mariana Victor
	 * @date 17/06/2011
	 * 
	 * @param FileItem
	 * @throws ControladorException
	 */
	public StringBuilder validarArquivoTxtEncerramentoOSCobranca(FileItem arquivoAnexo) throws ControladorException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobran�a
	 * 
	 * Processamento do batch
	 * 
	 * @author Mariana Victor
	 * @date 21/06/2011
	 */
	public void processarArquivoTxtEncerramentoOSCobranca(int idFuncionalidadeIniciada,
			String idEmpresa, Usuario usuario, StringBuilder stringBuilder, String nomeArquivo) throws ControladorException;
	
	/**
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @data 30/06/2011
	 */
	public List<Object[]> pesquisarOrdensServicoContasPagasParceladas() throws ControladorException;
	
	
	/**
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
	 * Pesquisar EmpresaCobrancaConta a partir do im�vel
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	
	public Collection obterColecaoEmpresaCobrancaContaResultadoporImovel(Integer id) throws ControladorException;

	/**
	 * [UC1183] Gerar Arquivo TXT OS Contas Pagas/Parceladas
	 *
	 * @author Paulo Diniz, Mariana Victor, Ana Maria
	 * @date 02/07/2011
	 *
	 * @throws ControladorException
	 */
	public void gerarArquivoTxtOSContasPagasParceladas(
			int idFuncionalidadeIniciada) 
		throws ControladorException;
	
	
	/**
	 * [UC 0869] Gerar Arqv Texto das Contas em Cobran�a por Empresa
	 * 
	 * @author Paulo Diniz
	 * @data 03/08/2011
	 * 
	 */
	public Object[] pesquisarDadosQtdContasEDiasVencidos(Integer idComando) throws ControladorException;
	
	
	/**
	 * [UC 0869] Gerar Arqv Texto das Contas em Cobran�a por Empresa
	 * 
	 * @author Paulo Diniz
	 * @data 03/08/2011
	 * 
	 */
	public Collection<CmdEmpresaCobrancaContaLigacaoAguaSituacao> pesquisarColecaoLigacaoAguaSituacaoPorComandoEmpresaCobrancaConta(Integer idComando)
	throws ControladorException;

	/**
	 * [UC1167] Consultar Comandos de Cobran�a por Empresa
	 * Pesquisa dados do popup
	 * 
	 * @author Hugo Azevedo
	 * @date 25/08/2011
	 */
	
	public Collection pesquisarDadosPopupExtensaoComandoAguaSituacao(Integer idComando) throws ControladorException;
}
