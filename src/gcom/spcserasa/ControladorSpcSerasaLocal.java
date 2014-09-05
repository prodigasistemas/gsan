package gcom.spcserasa;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoTipoCriterioHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.gui.cobranca.spcserasa.RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.bean.InserirComandoNegativacaoPorCriterioHelper;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SuppressWarnings("rawtypes")
public interface ControladorSpcSerasaLocal extends javax.ejb.EJBLocalObject {

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 */
	public Integer pesquisarComandoNegativacao(ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 */
	
	public Collection pesquisarComandoNegativacaoParaPaginacao(ComandoNegativacaoHelper comandoNegativacaoHelper, Integer numeroPagina)
			throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 */
	public Integer pesquisarDadosInclusoesComandoNegativacao(Integer idComandoNegativacao) throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 */
	public Collection pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(Integer idComandoNegativacao, Integer numeroPagina) throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 */
	public ParametrosComandoNegativacaoHelper pesquisarParametrosComandoNegativacao(Integer idComandoNegativacao) throws ControladorException;

	/**
	 * [UC0651] Inserir Comando Negativa��o [FS0015] Verificar exist�ncia de
	 * negativa��o para o im�vel no negativador
	 */

	public Boolean verificarExistenciaNegativacaoImovel(Integer idImovel) throws ControladorException;

	/**
	 * [UC0651] Inserir Comando Negativa��o [SB0003] Determinar Data Prevista
	 * para Execu��o do Comando
	 */
	public Date pesquisarUltimaDataRealizacaoComando(Integer idNegativador, Integer icSimulacao) throws ControladorException;

	/**
	 * [UC0365] Inserir Comando Negativa��o
	 * 
	 * [SB0004] Inclui Comando de Negativa��o por crit�rio
	 */
	public Integer inserirComandoNegativacao(InserirComandoNegativacaoPorCriterioHelper helper) throws ControladorException;

	/**
	 * M�todo que inicia o caso de uso de Gerar Movimento de Inclusao de
	 * Negativacao [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [Fluxo
	 * Principal]
	 */
	public Integer gerarMovimentoInclusaoNegativacao(Integer idComandoNegativacao, Short tipoComando, String comunicacaoInterna, Integer idNegativador,
			int idUsuarioResponsaval, Collection ObjectImovel, Date dataPrevista, String indicadorBaixaRenda, String indicadorContaNomeCliente,
			String indicadorImovelCategoriaPublico) throws ControladorException;

	/**
	 * M�todo que Exclui a negativacao de um imovel [UC0675] Excluir Negativa��o
	 * Online [Fluxo principal] 8.0
	 */
	public void excluirNegativacaoOnLine(Imovel imovel, NegativadorMovimentoReg negativadorMovimentoReg, Collection itensConta, Collection itensGuiaPagamento,
			NegativadorExclusaoMotivo negativadorExclusaoMotivo, Date dataExclusao, Usuario usuario, Usuario usuarioLogado) throws ControladorException;

	/**
	 * Ponto inicial do caso de uso de Gerar Resumo Di�rio da Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o fluxo principal
	 */
	public void gerarResumoDiarioNegativacao(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException;

	/**
	 * Ponto inicial do caso de uso de Gerar Resumo Di�rio da Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 */
	public List consultarLocalidadeParaGerarResumoDiarioNegativacao() throws ControladorException;

	/**
	 * Ponto inicial do caso de uso de Gerar Resumo Di�rio da Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 */
	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(Integer idLocalidade) throws ControladorException;

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc
	 * ou seraza
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o
	 */
	public Collection consultarNegativadoresParaExclusaoMovimento() throws ControladorException;

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc
	 * ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o [SB0001] - Gerar
	 * Movimento da Exclus�o de Negativa��o
	 */
	public Collection gerarMovimentoExclusaoNegativacao(Integer[] id) throws ControladorException;

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc
	 * ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o [SB0001] - Gerar
	 * Movimento da Exclus�o de Negativa��o
	 */
	public Collection gerarMovimentoExclusaoNegativacao(Integer idFuncionalidadeIniciada, Integer[] id) throws ControladorException;

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc
	 * ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o [SB0002] - Gerar
	 * TxT de Movimento de Exclus�o de Negativacao
	 */
	public Collection gerarArquivoTXTMovimentoExclusaoNegativacao(Integer idMovimento) throws ControladorException;

	/**
	 * M�todo usado para pesquisa de Comando Negativa��o (Helper)
	 * 
	 * [UC0655] Filtrar Comando Negativa��o
	 */

	public Collection pesquisarComandoNegativacaoHelper(ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso crit�rio)usado no caso de uso [UC0691]
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper)
			throws ControladorException;

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso matr�cula)usado no caso de uso [UC0691]
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(ComandoNegativacaoHelper comandoNegativacaoHelper, Integer numeroPagina)
			throws ControladorException;

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso matr�cula)usado no caso de uso [UC0691]
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;

	/**
	 * M�todo usado para Regitrar Movimento de Retorno de Negativa��o
	 * 
	 * [UC0672] Regitrar Movimento de Retorno de Negativa��o
	 */

	public Collection registrarNegativadorMovimentoRetorno(Integer idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso crit�rio)usado no caso de uso [UC0691]
	 */
	public Collection pesquisarComandoNegativacaoTipoCriterio(ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper, Integer numeroPagina)
			throws ControladorException;

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso matr�cula)usado no caso de uso [UC0691] (sem pagina��o)
	 */
	public Integer pesquisarNegativadorMovimentoCount(NegativadorMovimentoHelper negativadorMovimentoHelper) throws ControladorException;

	/**
	 * M�todo usado para contar a quantidade de ocorr�ncias de
	 * negativadorMovimento Registro aceitos usado no caso de uso [UC0681]
	 */
	public Integer verificarTotalRegistrosAceitos(Integer idNegativadorMovimento) throws ControladorException;

	/**
	 * M�todo usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 */
	public Collection pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper) throws ControladorException;

	/**
	 * [UC0317] Manter Comando de Negativa��o por Crit�rio
	 * 
	 * [SB0001] Excluir Comando de Negativa��o por Crit�rio
	 */
	public void removerComandoNegativacaoPorCriterio(String[] ids) throws ControladorException;

	/**
	 * [UC0652] Manter Comando de Negativa��o por Crit�rio
	 * 
	 * [SB0002] Atualizar Comando de Negativa��o por crit�rio
	 */
	public void atualizarComandoNegativacao(InserirComandoNegativacaoPorCriterioHelper helper) throws ControladorException;

	/**
	 * M�todo usado para pesquisar um Negativador Movimento.
	 * 
	 * [UC0682] - Filtrar Movimento Negativador
	 */
	public Collection pesquisarNegativadorMovimento(NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina) throws ControladorException;

	/**
	 * M�todo usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 */
	public Collection pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina)
			throws ControladorException;

	/**
	 * M�todo usado para Pesquisar se a inclus�o do im�vel est� com retorno ou
	 * foi aceita. usado no caso de uso [0675]
	 */
	public Collection pesquisarNegatiacaoParaImovel(Imovel imovel, Negativador negativador) throws ControladorException;

	/**
	 * Conta a quantidade de Clientes Negativados [UC0693] Gerar Relat�rio
	 * Acompanhamaneto de Clientes Negativados
	 */
	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCount(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	/**
	 * Conta a quantidade de Clientes Negativados [UC0693] Gerar Relat�rio
	 * Acompanhamaneto de Clientes Negativados
	 */
	public Collection pesquisarRelatorioAcompanhamentoClientesNegativador(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	/**
	 * Retorna o somat�rio do valor do D�bito do NegativadoMovimentoReg pela
	 * CobrancaDebitoSituacao [UC0693] Gerar Relat�rio Acompanhamaneto de
	 * Clientes Negativados
	 */
	public BigDecimal pesquisarSomatorioValorDebito(NegativadorMovimentoReg negativadorMovimentoReg, CobrancaDebitoSituacao cobrancaDebitoSituacao)
			throws ControladorException;

	/**
	 * Retorna o ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	 */
	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel) throws ControladorException;

	/**
	 * Conta a quantidade de Neg [UC0693] Gerar Relat�rio Negativacoes Excluidas
	 */
	public Collection pesquisarRelatorioNegativacoesExcluidas(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	/**
	 * Conta a quantidade de Neg [UC0693] Gerar Relat�rio Negativacoes Excluidas
	 */
	public Integer pesquisarRelatorioNegativacoesExcluidasCount(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	/**
	 * Pesquisar se a negativa��o do im�vel . [UC0675] Excluir Negativa��o
	 * Online.
	 */
	public Collection pesquisarImovelNegativado(Imovel imovel, Negativador negativador) throws ControladorException;

	/**
	 * Retorna o NegativadorMovimentoReg [UC0673] Excluir Negativa��o Online
	 */
	public NegativadorMovimentoReg pesquisarNegativadorMovimentoRegInclusao(Imovel imovel, Negativador negativador) throws ControladorException;

	/**
	 * Apagar todos os registros da Tabela de Resumo de Negativa��o [UC0688]
	 * Gerar Resumo Di�rio da Negativa��o
	 */
	public void apagarResumoNegativacao() throws ControladorException;

	/**
	 * Apaga todos os ResumoNegativacao [UC0688] Gerar Resumo Di�rio da
	 * Negativa��o Fluxo principal Item 2.0
	 */
	public void apagarResumoNegativacao(Integer numeroPenultimaExecResumoNegat) throws ControladorException;

	/**
	 * [UC0651] Inserir Comando Negativa��o [FS0026] Verificar exist�ncia de
	 * comando para o negativador na data
	 */
	public boolean verificarExistenciaComandoNegativador(String idNegativador, Date dataPrevista) throws ControladorException;

	/**
	 * [UC0694] Relat�rio Negativa��o Exclu�das Pesquisar data da Negativa��o
	 * Exclu�da
	 */
	public Date pesquisarDataExclusaoNegativacao(int idImovel, int idNegativacaoComando) throws ControladorException;

	public NegativacaoCriterio pesquisarComandoNegativacaoSimulado(Integer idComandoNegativacao) throws ControladorException;

	/**
	 * [UC0678] Relat�rio Negativador Resultado Simulacao pesquisar Negativador
	 * Resultado Simulacao
	 */
	public Collection pesquisarNegativadorResultadoSimulacao(Integer idNegativacaoComando) throws ControladorException;

	/**
	 * [UC0678] Relat�rio Negativador Resultado Simulacao pesquisar Negativador
	 * Resultado Simulacao
	 */
	public NegativacaoCriterio pesquisarNegativacaoCriterio(Integer idNegativacaoComando) throws ControladorException;

	/**
	 * Informa��es Atualizadas em (maior data e hora da �ltima execu��o
	 */
	public Date getDataUltimaAtualizacaoResumoNegativacao(Integer numeroExecucaoResumoNegativacao) throws ControladorException;

	/**
	 * M�todo que retorna todas NegativacaoComando que ainda nao tenha sido
	 * executada (dataHoraRealizacao == null) [UC0687] Executar Comando de
	 * Negativa��o [Fluxo Principal] - Item 2.0
	 *
	 * @author Thiago Toscano
	 * @date 21/01/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public NegativacaoComando consultarNegativacaoComandadoParaExecutar() throws ControladorException;

	/**
	 * M�todo consutla um negativacaoComando [UC0671] Gerar Movimento de
	 * Inclusao de Negativacao [Fluixo princiapal] 4.0
	 */
	public NegativacaoComando consultarNegativacaoComando(Integer idNegativacaoComando) throws ControladorException;

	/**
	 * Consulta todos os contratos em vigencia de um negativador
	 */
	public NegativadorContrato consultarNegativadorContratoVigente(Integer negativador) throws ControladorException;

	/**
	 * UC0671 - Gerar Movimento de Inclus�o de Negativa��o] SB0004 - Gerar
	 * Movimento de Inclus�o de Negativa��o para os Im�veis
	 */
	public List pesquisarParametroNegativacaoCriterio(Integer idNegativacaoCriterio) throws ControladorException;

	/**
	 * Pesquisar as rotas dos Im�veis
	 */
	public Collection pesquisarRotasImoveis() throws ControladorException;

	/**
	 * Pesquisar as rotas dos Im�veis que est�o resultado da simula��o
	 */
	public Collection pesquisarRotasImoveisComandoSimulacao(Integer idNegativacaoComando) throws ControladorException;

	/**
	 * Gerar Registro de negativacao tipo header
	 * 
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0008] Gerar
	 * Registro de tipo header
	 */
	public Integer gerarRegistroDeInclusaoTipoHeader(Short tipoComando, Integer quantidadeRegistro, Negativador n, NegativadorContrato nContrato,
			NegativacaoComando nComando, NegativacaoCriterio nCriterio, Integer idNegativacaoMovimento) throws ControladorException;

	public Object[] pesquisarQuantidadeInclusaoNegativacao(Integer idNegativacaoComando) throws ControladorException;

	public Integer pesquisarQuantidadeInclusaoItemNegativacao(Integer idNegativacaoComando) throws ControladorException;

	public Object[] pesquisarQuantidadeInclusaoNegativacaoSimulacao(Integer idNegComando) throws ControladorException;

	public void gerarArquivoNegativacao(Integer idComando, Integer idMovimento, Integer idNegativador, NegativadorMovimento negativadorMovimento,
			boolean trailler) throws ControladorException;

	/**
	 * M�todo que gera os movimento de inclusao de negativacao por Matricula de
	 * Imovel [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0002]
	 * Gerar Movimento de Inclusao de Negativacao Por Crit�rio
	 */
	public void gerarMovimentodeInclusaoNegativacaoPorCriterio(Integer idLocalidade, NegativacaoCriterio nCriterio, Negativador n, NegativacaoComando nComando,
			NegativadorContrato nContrato, NegativadorMovimento negMovimento, Integer idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * M�todo que atualiza o n�mero da execu��o do resumo da negativa��o na
	 * tabela SISTEMA_PARAMETROS mais um [UC0688] Gerar Resumo Di�rio da
	 * Negativa��o. *
	 */
	public void atualizarNumeroExecucaoResumoNegativacao(Integer idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0014] - ManterImovel
	 * 
	 * Verificar exist�ncia de negativa��o para o cliente-im�vel
	 */
	public boolean verificarNegativacaoDoClienteImovel(Integer idCliente, Integer idImovel) throws ControladorException;

	/**
	 * Pesquisa a cole��o de clientes do im�vel para negativa��o sem o cliente
	 * empresa do sistema par�metro
	 */
	public Collection pesquisarClienteImovelParaNegativacao(Integer idImovel) throws ControladorException;

	public Object[] pesquisarDadosImovelParaNegativacao(Integer idImovel) throws ControladorException;

	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por grupo de cobran�a para um crit�rio de negativa��o
	 */
	public List pesquisarRotasPorCobrancaGrupoParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por gerencial regional para um crit�rio de negativa��o
	 */
	public List pesquisarRotasPorGerenciaRegionalParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por Unidade de Negocio para um crit�rio de negativa��o
	 */
	public List pesquisarRotasPorUnidadeNegocioParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por Localidade para um crit�rio de negativa��o
	 */
	public List pesquisarRotasPorLocalidadeParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por Localidade para um crit�rio de negativa��o
	 */
	public List pesquisarRotasPorLocalidadesParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	/**
	 * Validar o Arquivo de Movimento de Retorno.
	 */
	public Object[] validarArquivoMovimentoRetorno(StringBuilder stringBuilderTxt, Negativador negativador) throws ControladorException;

	/**
	 * Insere Processo para Registrar Movimento de Retorno do Negativadaor.
	 */
	public void inserirProcessoRegistrarNegativadorMovimentoRetorno(Usuario usuario) throws ControladorException;

	/**
	 * Gerar Relat�rio de Negativa��es Exclu�das
	 */
	public List pesquisarNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg) throws ControladorException;

	/**
	 * Gerar Relat�rio Negatica��es Exclu�das
	 * 
	 * Pesquisar o somat�rio do valor paga ou parcelado pelo registro
	 * negativador
	 */
	public BigDecimal pesquisarSomatorioNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg, Integer idCobrancaDebitoSituacao)
			throws ControladorException;

	/**
	 * Consulta as rotas para a geracao do resumo diario da negativacao [UC0688]
	 * Gerar Resumo Di�rio da Negativa��o Fluxo principal Item 1.0
	 */
	public List consultarRotasParaGerarResumoDiarioNegativacao() throws ControladorException;

	/**
	 * [UC0688] - Gerar Resumo Diario da Negativa��o [UC0694] - Gerar Relat�rio
	 * Negativa��es Exclu�das
	 * 
	 * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para
	 * NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG)
	 */
	public RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper pesquisarNegativadorMovimentoRegParcelamento(Integer idNegativadorMovimentoReg)
			throws ControladorException;

	/**
	 * Retorna o somat�rio do VALOR PAGO e do VALOR CANCELADO [UC0693] Gerar
	 * Relat�rio Acompanhamaneto de Clientes Negativados
	 */
	public Object[] pesquisarSomatorioValorPagoEValorCancelado(Integer idNegativadorMovimentoReg) throws ControladorException;

	/**
	 * [UC0693] - Gerar Relat�rio Acompanhamento de Clientes Negativados
	 * 
	 * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para
	 * NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG)
	 */
	public Object[] pesquisarDadosParcelamentoRelatorioAcompanhamentoClientesNegativados(Integer idNegativadorMovimentoReg) throws ControladorException;

	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores Fluxo Principal 10.
	 */
	public void atualizarNegativadorMovimentoRegItemAPartirPagamento(Pagamento pagamento) throws ControladorException;

	/**
	 * [UC0266] Manter Pagamentos [SB0007] - Verifica Associa��o do Pagamento
	 * com Itens de Negativa��o
	 */
	public void verificaAssociacaoPagamentoComItensNegativacao(Pagamento pagamento) throws ControladorException;

	/**
	 * [UC0266] Manter Pagamentos [SB0004] - Atualiza Pagamento
	 */
	public void verificaAssociacaoPagamentoComItensNegativacao(Pagamento pagamento, Pagamento pagamentoAnterior) throws ControladorException;

	/**
	 * [UC0150] Retificar Conta [SB0002] - Gerar Dados da Conta - 6.
	 */
	public void atualizarNegativadorMovimentoRegItemAPartirConta(Conta conta) throws ControladorException;

	/**
	 * [UC0266] Manter Pagamentos [FS0003] - Verificar exist�ncia de itens de
	 * negativa��o para a conta inclu�da
	 */
	public void verificarExistenciaItensNegativacaoParaContaIncluida(Conta conta) throws ControladorException;

	/**
	 * [UC0266] Manter Pagamentos Fluxo Principal 1.1.2. Verificar se h� rela��o
	 * do cancelamento com itens de negativa��o
	 */
	public void verificarRelacaoDoCancelamentoComItensNegativacao(Conta conta, Integer idContaCanceladaRetificacao) throws ControladorException;

	/**
	 * [UC0937] Obter Itens de Negativa��o Associados � Conta
	 */
	public Collection obterItensNegativacaoAssociadosAConta(Integer idImovel, Integer referencia) throws ControladorException;

	/**
	 * [UC0147] Cancelar Conta [SB0001] - Atualizar Item da Negativa��o [SB0002]
	 * - Atualizar Item Negativa��o - Conta Retificada
	 */
	public void atualizarNegativadorMovimentoRegItem(Integer idNegativadorMovimentoRegItem, Date dataSituacaoDebito, Integer idCobrancaDebitoSituacao,
			Integer idContaCanceladaPorRetificacao, Integer idContaRetificadaECancelada) throws ControladorException;

	/**
	 * [UC0329] Restabelecer Situa��o Anterior de Conta Verificar se h� rela��o
	 * do desfazer cancelamento ou retifica��o com itens de negativa��o
	 */
	public void verificarRelacaoDoDesfazerCancelamentoOuRetificacaoComItensNegativacao(Collection colecaoNegativadorMovimentoRegItem,
			Integer situacaoAtualConta, Conta contaCanceladaOuCanceladaPorRetificacao, Conta contaRetificada) throws ControladorException;

	/**
	 * [UC0188] Manter Guia de Pagamento verifica se existe negativador
	 * movimento reg item associado a guia de pagamento
	 */
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAGuiaPagamento(Integer idGuia) throws ControladorException;

	/**
	 * [UC0188] Manter Guia de Pagamento Fluxo Principal 2.1.2.2. Verificar se
	 * h� rela��o do cancelamento com itens de negativa��o:
	 */
	public void verificarRelacaoDoCancelamentoComItensNegativacao(GuiaPagamento guiaPagamento) throws ControladorException;

	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos [SB0013] - Atualizar Item da
	 * Negativa��o
	 */
	public void verificarRelacaoDoParcelamentoComItensNegativacao(Parcelamento parcelamento, Conta conta, GuiaPagamento guiaPagamento)
			throws ControladorException;

	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 */
	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(Collection<GuiaPagamento> colecaoGuiasPagamento) throws ControladorException;

	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 */
	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(ContaHistorico contaHistorico) throws ControladorException;

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 */
	public void gerarResumoNegativacao(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException;

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o Fluxo Principal 3.6
	 */
	public void acompanharPagamentoDoParcelamento(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException;

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o Fluxo principal Item 1.0
	 */
	public List consultarSetorParaGerarResumoDiarioNegativacao() throws ControladorException;

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 */
	public boolean existeOcorrenciaMovimentoExclusaoIncompleto() throws ControladorException;

	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 */
	public void determinarConfirmacaoDaNegativacao(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException;

	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 */
	public List consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao() throws ControladorException;

	/**
	 * [UC0473] Consultar Dados Complementares do Im�vel
	 */
	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel) throws ControladorException;

	/**
	 * [UC0651] - Inserir Comando de Negativa��o [FS0030] - Verificar exist�ncia
	 * de inclus�o no negativador para o im�vel
	 */
	public boolean verificarExistenciaDeInclusaoNoNegativadorParaImovel(Integer idImovel, Integer idNegativador) throws ControladorException;

	/**
	 * [UC0651] Inserir Comando de Negativa��o [SB0005] � Obter D�bito do Im�vel
	 * 
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o [SB0006] � Verificar
	 * Crit�rio de Negativa��o para o Im�vel
	 */
	public Collection retirarContaPagaOuParceladaEEntradaParcelamento(Collection colecaoContasValores) throws ControladorException;

	/**
	 * [UC0651] Inserir Comando de Negativa��o [SB0005] � Obter D�bito do Im�vel
	 * 
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o [SB0006] � Verificar
	 * Crit�rio de Negativa��o para o Im�vel
	 */
	public Collection retirarGuiaPagamentoDeEntradaParcelamento(Collection colecaoGuiaPagamentoValoresHelper) throws ControladorException;

	/**
	 * [UC0472] Consultar Im�vel
	 */
	public Collection pesquisarNegativadorRetornoMotivoDoReg(Integer idImovel) throws ControladorException;

	/**
	 * [UC0681] Consultar Movimentos dos Negativadores
	 */
	public void atualizarIndicadorCorrecaoEUsuarioCorrecao(Integer usuarioCorrecao, Short indicadorCorrecao, Collection colecaoIdsNegativadorMovimentoReg)
			throws ControladorException;

	public boolean existeImovelCobrancaSituacaoParaImovel(Integer codigoImovel, Integer codigoCobrancaSituacao) throws ControladorException;

	/**
	 * Conta a quantidade de Clientes Negativados para a Unidade, Ger�ncia e
	 * Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes
	 * Negativados
	 */
	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	/**
	 * Soma os valores de d�bitos dos Clientes Negativados para a Unidade,
	 * Ger�ncia e Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de
	 * Clientes Negativados
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	/**
	 * Soma os valores Pagos dos Clientes Negativados para a Unidade, Ger�ncia e
	 * Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes
	 * Negativados
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	/**
	 * Conta a quantidade de Clientes Negativados com contas pagas na Unidade,
	 * Ger�ncia e Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de
	 * Clientes Negativados
	 */
	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	/**
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * [SB0014] Desfazer Atualiza��o da Execu��o Descontinuada
	 */
	public void desfazerAtualizacaoExecucaoDescontinuada(Integer idFuncionalidadeIniciada, NegativacaoComando nComando) throws ControladorException;

	/**
	 * UC1009 - Obter Itens de Negativa��o Associados � Guia
	 * 
	 * Pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_ITEM com
	 * NMRI_ICSITDEFINITIVA=2 e GPAG_ID=GPAG_ID do PAGAMENTO
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(Integer idGuia) throws ControladorException;
	
	public Integer gerarNegativadorMovimento(Integer idNegativador, Integer numeroSequencialEnvio, Integer idNegativadorComando) throws ControladorException;
}
