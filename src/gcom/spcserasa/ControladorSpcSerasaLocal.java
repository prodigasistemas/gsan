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

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Marcio Roberto
 * @created 24 de Outubro de 2007
 * @version 1.0
 */

public interface ControladorSpcSerasaLocal extends javax.ejb.EJBLocalObject {

	/**
	 * Permite inserir negativacao SPC SERASA
	 * 
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * 
	 * @author Marcio Roberto
	 * @date 24/10/2007
	 * 
	 */

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarComandoNegativacao(
			ComandoNegativacaoHelper comandoNegativacaoHelper)
			throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoParaPaginacao(
			ComandoNegativacaoHelper comandoNegativacaoHelper,
			Integer numeroPagina) throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 31/10/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarDadosInclusoesComandoNegativacao(
			Integer idComandoNegativacao) throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 31/10/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(
			Integer idComandoNegativacao, Integer numeroPagina)
			throws ControladorException;

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 09/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return ParametrosComandoNegativacaoHelper
	 * @throws ControladorException
	 */
	public ParametrosComandoNegativacaoHelper pesquisarParametrosComandoNegativacao(Integer idComandoNegativacao)
			throws ControladorException;
	

	/**
	 * [UC0651] Inserir Comando Negativa��o [FS0015] Verificar exist�ncia de
	 * negativa��o para o im�vel no negativador
	 * 
	 * @author Ana Maria
	 * @date 04/12/2007
	 * 
	 * @param idImovel
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */

	public Boolean verificarExistenciaNegativacaoImovel(Integer idImovel)
		throws ControladorException;	

	/**
	 * [UC0651] Inserir Comando Negativa��o
	 * [SB0003] Determinar Data Prevista para Execu��o do Comando
	 * 
	 * @author Ana Maria
	 * @date 11/12/2007
	 * 
	 * @param idNegativador
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaDataRealizacaoComando(Integer idNegativador,Integer icSimulacao)
		throws ControladorException;	
	
	/**
	 * [UC0365] Inserir Comando Negativa��o
	 * 
	 * [SB0004] Inclui Comando de Negativa��o por crit�rio
	 * 
	 * @author Ana Maria
	 * @date 13/12/2007
	 * 
	 * @throws ControladorException
	 */
	public Integer inserirComandoNegativacao(InserirComandoNegativacaoPorCriterioHelper helper)
			throws ControladorException;	

	/**
	 * M�todo que inicia o caso de uso de Gerar Movimento de Inclusao de Negativacao
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o
	 * [Fluxo Principal] 
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param idComandoNegativacao
	 * @param tipoComando
	 * @param comunicacaoInterna
	 * @param idNegativador
	 * @param idUsuarioResponsaval
	 * @param ObjectImovel - Collecao de 
	 * 			[0] Integer - Matricula do Imovel
	 * 			[1] Integer - id do cliente da negativacao 
	 * 			[2] String - cpf do cliente da negativacao
	 * 			[3] String - cnpj do cliente da negativaca
	 * 			[4] Collection - lista da contas e guias de pagamento do imovel
	 * 			[5] Intetger - quantidade de itens em d�bito do imovel
	 * 			[6] BigDecimal - valor total dos d�bitos do imovel
	 * @param dataPrevista
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public Integer gerarMovimentoInclusaoNegativacao(Integer idComandoNegativacao, Short tipoComando, 
			String comunicacaoInterna, Integer idNegativador, int idUsuarioResponsaval, Collection ObjectImovel, 
			Date dataPrevista,String indicadorBaixaRenda,String indicadorContaNomeCliente,
			String indicadorImovelCategoriaPublico) throws ControladorException ;
	/**
	 * M�todo que Exclui a negativacao de um imovel
	 * [UC0675] Excluir Negativa��o InLine
	 * [Fluxo principal] 8.0 
	 * 
	 * @author Thiago Toscano
	 * @date 24/01/2008
	 *
	 * @throws ControladorException
	 */
	public void excluirNegativacaoOnLine(Imovel imovel, NegativadorMovimentoReg negativadorMovimentoReg, Collection itensConta, Collection itensGuiaPagamento, NegativadorExclusaoMotivo negativadorExclusaoMotivo, Date dataExclusao, Usuario usuario, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 *
	 * Ponto inicial do caso de uso de Executar Comando de Negativa��o
	 * [UC0687] Executar Comando de Negativa��o
	 * Fluxo Principal
	 *
	 * @author Ana Maria
	 * @date 27/03/2008
	 */
	//public void executaComandoNegativacao(Integer idFuncionalidadeIniciada) throws ControladorException;

	/**
	 *
	 * Ponto inicial do caso de uso de Gerar Resumo Di�rio da Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 * fluxo principal
	 *
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public void gerarResumoDiarioNegativacao(Integer idFuncionalidadeIniciada,Integer idLocalidade) throws ControladorException ;
	
	
	/**
	 *
	 * Ponto inicial do caso de uso de Gerar Resumo Di�rio da Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o	
	 *
	 * @author Yara Taciane
	 * @date 07/01/2008
	 */
	public List consultarLocalidadeParaGerarResumoDiarioNegativacao() throws ControladorException ;
	
	
	/**
	 *
	 * Ponto inicial do caso de uso de Gerar Resumo Di�rio da Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o	
	 *
	 * @author Yara Taciane
	 * @date 07/01/2008
	 */
	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(Integer idLocalidade) throws ControladorException ;
	
	

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc ou seraza
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o
	 * 
	 * @author Thiago Toscano
	 * @date 21/12/2007
	 * 
	 */
	public Collection consultarNegativadoresParaExclusaoMovimento() 
			throws ControladorException;
	
	
	
	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o
	 * [SB0001] - Gerar Movimento da Exclus�o de Negativa��o
	 * 
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 * 
	 */
	public Collection gerarMovimentoExclusaoNegativacao(Integer[] id)
			throws ControladorException;
	
	
	
	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o
	 * [SB0001] - Gerar Movimento da Exclus�o de Negativa��o
	 * 
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 * 
	 */
	public Collection gerarMovimentoExclusaoNegativacao(Integer idFuncionalidadeIniciada,Integer[] id)
			throws ControladorException;
	
	
	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o
	 * [SB0002] - Gerar TxT de Movimento de Exclus�o de Negativacao
	 * 
	 * @author Thiago Toscano
	 * @param idMovimento - 
	 * @return Collection  - NegativadorMovimento
	 * @date 26/12/2007
	 * 
	 */
	public Collection gerarArquivoTXTMovimentoExclusaoNegativacao(Integer idMovimento) 
		throws ControladorException;

	
	/**
	 * M�todo usado para pesquisa de Comando Negativa��o (Helper)
	 * 
	 * [UC0655] Filtrar Comando Negativa��o
	 * @author Thiago Vieira
	 * @date 02/01/2008
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoHelper(
			ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;
	
	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 *  (nesse caso crit�rio)usado no caso de uso [UC0691] 
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper) throws ControladorException;
	
	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 *  (nesse caso matr�cula)usado no caso de uso [UC0691] 
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(
			ComandoNegativacaoHelper comandoNegativacaoHelper, Integer numeroPagina) throws ControladorException;
	
	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 *  (nesse caso matr�cula)usado no caso de uso [UC0691] 
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(
			ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;
	

	/**
	 * M�todo usado para Regitrar Movimento de Retorno de Negativa��o
	 * 
	 * [UC0672] Regitrar Movimento de Retorno de Negativa��o
	 * @author Yara Taciane 
	 * @date 11/01/2008
	 * 
	 * @param registrarNegativadorMovimentoRetorno
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection registrarNegativadorMovimentoRetorno(
			Integer idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 *  (nesse caso crit�rio)usado no caso de uso [UC0691] 
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper, Integer numeroPagina) throws ControladorException;
		
	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 *  (nesse caso matr�cula)usado no caso de uso [UC0691] (sem pagina��o)
	 * 
	 * @author Yara Taciane ,Vivianne Sousa
	 * @date 21/01/2008,14/12/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Integer pesquisarNegativadorMovimentoCount(
			NegativadorMovimentoHelper negativadorMovimentoHelper)
			throws ControladorException;

	/**
	 * 
	 * 
	 * M�todo usado para contar a quantidade de ocorr�ncias de
	 * negativadorMovimento Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 21/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ControladorException
	 */
	public Integer verificarTotalRegistrosAceitos(Integer idNegativadorMovimento)
			throws ControladorException;
	
	
	
	/**
	 * 
	 * 
	 * M�todo usado para apresentar os registros de negativadorMovimento Registro aceitos 
	 * usado no caso de uso [UC0681] 
	 * 
	 * @author Yara Taciane
	 * @date 21/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection  pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper)
			throws ControladorException;
	
	/**
	 * [UC0317] Manter Comando de Negativa��o por Crit�rio
	 * 
	 * [SB0001] Excluir Comando de Negativa��o por Crit�rio
	 * 
	 * @author Ana Maria
	 * @param ids
	 * @param usuarioLogado
	 * @created 21/01/2008
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerComandoNegativacaoPorCriterio(String[] ids) throws ControladorException;
	
	/**
	 * [UC0652] Manter Comando de Negativa��o por Crit�rio
	 * 
	 * [SB0002] Atualizar Comando de Negativa��o por crit�rio
	 * 
	 * @author Ana Maria
	 * @date 24/01/2008
	 * 
	 * @throws ControladorException
	 */
	public void atualizarComandoNegativacao(InserirComandoNegativacaoPorCriterioHelper helper)
			throws ControladorException;
	
	
	/**
	 * 
	 * 
	 * M�todo usado para pesquisar um Negativador Movimento.
	 * 
	 * [UC0682] - Filtrar Movimento Negativador
	 * 
	 * @author Yara Taciane
	 * @date 21/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarNegativadorMovimento(
			NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina)
			throws ControladorException;
	
	/**
	 * 
	 * 
	 * M�todo usado para apresentar os registros de negativadorMovimento Registro aceitos 
	 * usado no caso de uso [UC0681] 
	 * 
	 * @author Yara Taciane
	 * @date 21/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection  pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina)
			throws ControladorException;
	
	
	/**
	 * M�todo usado para Pesquisar se a inclus�o do im�vel est� com retorno ou foi aceita.
	 * usado no caso de uso [0675]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegatiacaoParaImovel(Imovel imovel, Negativador negativador)
			throws ControladorException;
	
	
	
	
	/**
	*
	* Conta a quantidade de Clientes Negativados
	* [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	* @author Yara Taciane
	* @date 17/03/2008
	*/

	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCount(DadosConsultaNegativacaoHelper helper) throws ControladorException ;

	
	
	/**
	*
	* Conta a quantidade de Clientes Negativados
	* [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	* @author Yara Taciane
	* @date 17/03/2008
	*/
	
	public Collection pesquisarRelatorioAcompanhamentoClientesNegativador(DadosConsultaNegativacaoHelper helper)throws ControladorException ;
		
	/**
	*
	* Retorna o somat�rio do valor do D�bito do NegativadoMovimentoReg pela CobrancaDebitoSituacao 
	* [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	* @author Yara Taciane
	* @date 17/03/2008
	*/
	public BigDecimal pesquisarSomatorioValorDebito(NegativadorMovimentoReg negativadorMovimentoReg, CobrancaDebitoSituacao cobrancaDebitoSituacao)throws ControladorException ;

	/**
	*
	* Retorna o  ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	* [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	* @author Yara Taciane
	* @date 17/03/2008
	*/
	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel)throws ControladorException ;

	
	/**
	*
	* Conta a quantidade de Neg
	* [UC0693] Gerar Relat�rio Negativacoes Excluidas
	* @author Yara Taciane
	* @date 17/03/2008
	*/
	public Collection pesquisarRelatorioNegativacoesExcluidas(DadosConsultaNegativacaoHelper helper)throws ControladorException ;

	/**
	*
	* Conta a quantidade de Neg
	* [UC0693] Gerar Relat�rio Negativacoes Excluidas
	* @author Yara Taciane
	* @date 17/03/2008
	*/
	public Integer pesquisarRelatorioNegativacoesExcluidasCount(DadosConsultaNegativacaoHelper helper)throws ControladorException ;

	
	/**
	*
	* Pesquisar se a negativa��o do im�vel .
	* [UC0675] Excluir Negativa��o Online.	
	* @author Yara Taciane
	* @date 17/03/2008
	*/
	public Collection pesquisarImovelNegativado(Imovel imovel, Negativador negativador)throws ControladorException ;

	
	/**
	*
	* Retorna o  NegativadorMovimentoReg
	* [UC0673] Excluir Negativa��o Online
	* @author Yara Taciane
	* @date 27/03/2008
	*/
	public NegativadorMovimentoReg pesquisarNegativadorMovimentoRegInclusao(Imovel imovel, Negativador negativador)throws ControladorException ;


	
	
	/**
	 *
	 * Apagar todos os registros da Tabela de Resumo de Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o	  
	 *
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */

	public void  apagarResumoNegativacao() throws ControladorException;
	
		/**
	*
	* Apaga todos os ResumoNegativacao
	* [UC0688] Gerar Resumo Di�rio da Negativa��o
	* Fluxo principal Item 2.0
	*
	* @author Yara Taciane
	* @date 28/07/2008
	*/
	public void  apagarResumoNegativacao(Integer numeroPenultimaExecResumoNegat) throws ControladorException;
	
	

	/**
	 * [UC0651] Inserir Comando Negativa��o
	 * [FS0026] Verificar exist�ncia de comando para o negativador na data
	 * 
	 * @author Ana Maria
	 * @date 07/05/2008
	 * 
	 * @param idNegativador
	 * @param Data 
	 * @return boolean
	 */
	public boolean verificarExistenciaComandoNegativador(String idNegativador, Date dataPrevista)
		throws ControladorException;

	
	
	/**
	 * [UC0694] Relat�rio Negativa��o Exclu�das
	 * Pesquisar data da Negativa��o Exclu�da
	 * 
	 * @author Yara Taciane
	 * @date 09/05/2008
	 * 
	 * @param idImovel
	 * @param idNegativacaoComando
	 * @return Date 	
	 */
	public Date pesquisarDataExclusaoNegativacao(int idImovel, int idNegativacaoComando)throws ControladorException;
	
	public NegativacaoCriterio pesquisarComandoNegativacaoSimulado(Integer idComandoNegativacao)
		throws ControladorException;

	
	
	/**
	 * [UC0678] Relat�rio Negativador Resultado Simulacao
	 * pesquisar  Negativador Resultado Simulacao
	 * 
	 * @author Yara Taciane
	 * @date 09/05/2008
	 * 	
	 * @param idNegativacaoComando
	 * @return NegativadorResultadoSimulacao 	
	 */
	public Collection pesquisarNegativadorResultadoSimulacao(Integer idNegativacaoComando)throws ControladorException;
	
	
	
	/**
	 * [UC0678] Relat�rio Negativador Resultado Simulacao
	 * pesquisar  Negativador Resultado Simulacao
	 * 
	 * @author Yara Taciane
	 * @date 09/05/2008
	 * 	
	 * @param idNegativacaoComando
	 * @return NegativadorResultadoSimulacao 	
	 */
	public NegativacaoCriterio pesquisarNegativacaoCriterio(Integer idNegativacaoComando)throws ControladorException;


	

	/**	
	 * 
	 * Informa��es Atualizadas em (maior data e hora da �ltima execu��o 
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public Date getDataUltimaAtualizacaoResumoNegativacao(Integer numeroExecucaoResumoNegativacao)throws ControladorException;

	
//
//	/**
//	*
//	* Retorna o somat�rio do VALOR PARCELADO - ENTRADAdo D�bito do NegativadoMovimentoReg pela CobrancaDebitoSituacao 
//	* [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
//	* @author Yara Taciane
//	* @date 04/08/2008
//	*/
//	public BigDecimal pesquisarSomatorioValorDebitoAposExclusao(NegativadorMovimentoReg negativadorMovimentoReg,CobrancaDebitoSituacao cobrancaDebitoSituacao)throws ControladorException;


	/**
	* M�todo que retorna todas NegativacaoComando que ainda nao tenha sido executada (dataHoraRealizacao == null)
	* [UC0687] Executar Comando de Negativa��o
	* [Fluxo Principal] - Item 2.0
	*
	* @author Thiago Toscano
	* @date 21/01/2008
	*
	* @return
	* @throws ErroRepositorioException
	*/
	public NegativacaoComando consultarNegativacaoComandadoParaExecutar()throws ControladorException;
	
	/**
	 * M�todo consutla um negativacaoComando  
	 * [UC0671] Gerar Movimento de Inclusao de Negativacao 
	 * [Fluixo princiapal] 4.0 
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param idNegativacaoComando
	 * @param datahora
	 * @param quantidade
	 * @param valorTotalDebito
	 * @throws ErroRepositorioException
	 */
	public NegativacaoComando consultarNegativacaoComando(Integer idNegativacaoComando)throws ControladorException;
	
	/**
	 * Consulta todos os contratos em vigencia de um negativador
	 *
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 *
	 * @param negativador
	 * @return
	 * @throws ErroRepositorioException
	 */	
	public NegativadorContrato consultarNegativadorContratoVigente(Integer negativador)throws ControladorException;
	
	/**
	 * UC0671 - Gerar Movimento de Inclus�o de Negativa��o]
	 * SB0004 -  Gerar Movimento de Inclus�o de Negativa��o para os Im�veis
	 * @author Anderson Italo
	 * @date 19/03/2010
	 */
	public List pesquisarParametroNegativacaoCriterio(Integer idNegativacaoCriterio)
		throws ControladorException;
	
	/**
	 * 
	 * Pesquisar as rotas dos Im�veis
	 * 
	 * @author Ana Maria, Francisco do Nascimento
	 * @date 05/06/2008, 14/01/09
	 * 
	 */
	public Collection pesquisarRotasImoveis()throws ControladorException;	
	
	/**
	 * 
	 * Pesquisar as rotas dos Im�veis que est�o resultado da simula��o
	 * 
	 * @author Ana Maria
	 * @date 05/06/2008
	 * 
	 */
	public Collection pesquisarRotasImoveisComandoSimulacao(Integer idNegativacaoComando)
		throws ControladorException;	
	
	/**	
	 * 
	 * Inserir Negativador Movimento
	 * @author Ana Maria 
	 * @date 11/08/2008
	 */
	public Integer geraRegistroNegativadorMovimento(int idNegativador,
			int saenvio, int idNegativadorComando)throws ControladorException;
	
	/**
	 * Gerar Registro de negativacao tipo header
	 * 
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o
	 * [SB0008] Gerar Registro de tipo header
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param nc
	 * @param Object[] obj
	 * 			obj[0] Integer - quantidadeInclusao
	 * 			obj[1] Integer - quantidadeItens
	 * 			obj[2] BigDecimal - valorTotalDebito
	 * @return
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public Integer gerarRegistroDeInclusaoTipoHeader(Short tipoComando, Integer quantidadeRegistro, Negativador n,
			NegativadorContrato nContrato,NegativacaoComando nComando, NegativacaoCriterio nCriterio,
			Integer idNegativacaoMovimento) throws ControladorException;
	
	public Object[] pesquisarQuantidadeInclusaoNegativacao(Integer idNegativacaoComando)
		throws ControladorException;
	
	public Integer pesquisarQuantidadeInclusaoItemNegativacao(Integer idNegativacaoComando)
		throws ControladorException;	
	
	public Object[] pesquisarQuantidadeInclusaoNegativacaoSimulacao(Integer idNegComando)
	throws ControladorException;
	
	public void gerarArquivoNegativacao(Integer idComando, Integer idMovimento, Integer idNegativador, NegativadorMovimento negativadorMovimento, boolean trailler) throws ControladorException;
	
	/**
	 * M�todo que gera os movimento de inclusao de negativacao por Matricula de Imovel
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o
	 * [SB0002] Gerar Movimento de Inclusao de Negativacao Por Crit�rio
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param nc
	 * @param Object[] obj
	 * 			obj[0] Integer - quantidadeInclusao
	 * 			obj[1] Integer - quantidadeItens
	 * 			obj[2] BigDecimal - valorTotalDebito
	 * 
	 * @return
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarMovimentodeInclusaoNegativacaoPorCriterio(Integer idLocalidade, NegativacaoCriterio nCriterio,
			Negativador n, NegativacaoComando nComando, NegativadorContrato nContrato, 
			NegativadorMovimento negMovimento, Integer idFuncionalidadeIniciada)
			throws ControladorException;
	/**
	* M�todo que atualiza o n�mero da execu��o do resumo da 
	* negativa��o na tabela SISTEMA_PARAMETROS mais um
	* [UC0688] Gerar Resumo Di�rio da Negativa��o.	*
	*
	* @author Yara Taciane
	* @date 11/11/2008
	*
	* @return
	* @throws ErroRepositorioException
	*/
	public void atualizarNumeroExecucaoResumoNegativacao(Integer idFuncionalidadeIniciada)throws ControladorException;
	
	/**
	 * [UC0014] - ManterImovel
	 * 
	 * Verificar exist�ncia de negativa��o para o cliente-im�vel
	 * 
	 * @author Victor Cisneiros
	 * @date 12/01/2009
	 */
	public boolean verificarNegativacaoDoClienteImovel(
			Integer idCliente, Integer idImovel) throws ControladorException;
	
	/**
	 * Pesquisa a cole��o de clientes do im�vel para negativa��o
	 * sem o cliente empresa do sistema par�metro
	 * 
	 * @author Ana Maria 
	 * @date 17/12/2008
	 * @param idImovel
	 * @return Collection
	 * @exception ErroRepositorioException
	 * 
	 */
	public Collection pesquisarClienteImovelParaNegativacao(Integer idImovel)
		throws ControladorException;
	
	public Object[] pesquisarDadosImovelParaNegativacao(Integer idImovel)
		throws ControladorException;

	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por grupo de cobran�a para um crit�rio de negativa��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorCobrancaGrupoParaNegativacao(NegativacaoCriterio nCriterio)
		throws ControladorException;
	
	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por gerencial regional para um crit�rio de negativa��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorGerenciaRegionalParaNegativacao(NegativacaoCriterio nCriterio)
		throws ControladorException;
	
	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por Unidade de Negocio para um crit�rio de negativa��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorUnidadeNegocioParaNegativacao(NegativacaoCriterio nCriterio)
		throws ControladorException;
	
	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por Localidade para um crit�rio de negativa��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorLocalidadeParaNegativacao(NegativacaoCriterio nCriterio)
		throws ControladorException;
	
	/**
	 * UC0671 - Gerar movimento de inclus�o da negativa��o
	 * 
	 * Pesquisar rotas por Localidade para um crit�rio de negativa��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorLocalidadesParaNegativacao(NegativacaoCriterio nCriterio)
		throws ControladorException;	
	
	/**
	 * Validar o Arquivo de Movimento de Retorno.
	 * 
	 * @author Yara T. Souza
	 * @date 09/12/2008
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] validarArquivoMovimentoRetorno(StringBuilder stringBuilderTxt, Negativador negativador)throws ControladorException;
	
	
	
	/**
	 * Insere Processo para Registrar Movimento de Retorno do Negativadaor.
	 * 
	 * @author Yara T. Souza
	 * @param usuario 
	 * @date 09/12/2008
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void inserirProcessoRegistrarNegativadorMovimentoRetorno(Usuario usuario)throws ControladorException;
	

	
	/**
	 * Gerar Relat�rio de Negativa��es Exclu�das
	 * @author Yara T. Souza
	 * @date 16/01/2009
	 */
	public List pesquisarNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg)throws ControladorException;

	
	/**
	 * Gerar Relat�rio Negatica��es Exclu�das
	 * 
	 * Pesquisar o somat�rio do valor paga ou parcelado pelo registro negativador
	 * 
	 * @author Yara T. Souza
	 * @date 14/01/2009
	 * 
	 * @param idNegativadorMovimentoReg,idCobrancaDebitoSituacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public BigDecimal pesquisarSomatorioNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg, Integer idCobrancaDebitoSituacao)throws ControladorException;

	/**
	 *
	 * Consulta as rotas para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 * Fluxo principal Item 1.0
	 *
	 * @author Francisco do Nascimento
	 * @date 03/02/2009
	 */
	public List consultarRotasParaGerarResumoDiarioNegativacao()
		throws ControladorException;
	/**
     * [UC0688] - Gerar Resumo Diario da Negativa��o
     * [UC0694] - Gerar Relat�rio Negativa��es Exclu�das
     * 
     * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para NMRG_ID=NMRG_ID 
     * da tabela NEGATIVADOR_MOVIMENTO_REG)
     * 
     * @author Vivianne Sousa
     * @data 28/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper
	 */
	public RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper pesquisarNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg)throws ControladorException;
	
	/**
	 *
	 * Retorna o somat�rio do VALOR PAGO e do VALOR CANCELADO 
	 * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	 * @author Vivianne Sousa
	 * @date 29/04/2009
	 */
	public Object[] pesquisarSomatorioValorPagoEValorCancelado(Integer idNegativadorMovimentoReg)throws ControladorException;

	/**
     * [UC0693] - Gerar Relat�rio Acompanhamento de Clientes Negativados
     * 
     * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para NMRG_ID=NMRG_ID 
     * da tabela NEGATIVADOR_MOVIMENTO_REG)
     * 
     * @author Vivianne Sousa
     * @data 30/04/2009
	 */
	public Object[] pesquisarDadosParcelamentoRelatorioAcompanhamentoClientesNegativados(
			Integer idNegativadorMovimentoReg)throws ControladorException;
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores
	 * Fluxo Principal 10.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItemAPartirPagamento(
			Pagamento pagamento)throws ControladorException;
	
	/**
	 * [UC0266] Manter Pagamentos
	 * [SB0007] - Verifica Associa��o do Pagamento com Itens de Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2009
	 */
	public void verificaAssociacaoPagamentoComItensNegativacao(
			Pagamento pagamento) throws ControladorException;
	
	/**
	 * [UC0266] Manter Pagamentos
	 * [SB0004] - Atualiza Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2009
	 */
	public void verificaAssociacaoPagamentoComItensNegativacao(
			Pagamento pagamento,Pagamento pagamentoAnterior) throws ControladorException;
	
	/**
	 * [UC0150] Retificar Conta
	 * [SB0002] - Gerar Dados da Conta - 6.
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItemAPartirConta(Conta conta)throws ControladorException;
	
	/**
	 * [UC0266] Manter Pagamentos
	 * [FS0003] - Verificar exist�ncia de itens de negativa��o para a conta inclu�da
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void verificarExistenciaItensNegativacaoParaContaIncluida(
			Conta conta) throws ControladorException;
	
	/**
	 * [UC0266] Manter Pagamentos
	 * Fluxo Principal 
	 * 1.1.2. Verificar se h� rela��o do cancelamento com itens de negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 17/09/2009
	 */
	public void verificarRelacaoDoCancelamentoComItensNegativacao(
			Conta conta,Integer idContaCanceladaRetificacao) throws ControladorException;
	/**
	 * [UC0937] Obter Itens de Negativa��o Associados � Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/09/2009
	 */
	public Collection obterItensNegativacaoAssociadosAConta(
			Integer idImovel, Integer referencia)throws ControladorException;
	
	/**
	 * [UC0147] Cancelar Conta
	 * [SB0001] - Atualizar Item da Negativa��o
	 * [SB0002] - Atualizar Item Negativa��o - Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao,
			Integer idContaCanceladaPorRetificacao,
			Integer idContaRetificadaECancelada) throws ControladorException;
	
	/**
	 * [UC0329] Restabelecer Situa��o Anterior de Conta 
	 * Verificar se h� rela��o do desfazer cancelamento ou retifica��o com itens de negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 21/09/2009
	 */
	public void verificarRelacaoDoDesfazerCancelamentoOuRetificacaoComItensNegativacao(
			Collection colecaoNegativadorMovimentoRegItem, 
			Integer situacaoAtualConta,
			Conta contaCanceladaOuCanceladaPorRetificacao,
			Conta contaRetificada) throws ControladorException;
	
	/**
	 * [UC0188] Manter Guia de Pagamento
	 * verifica se existe negativador movimento reg item 
	 * associado a guia de pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAGuiaPagamento(
			Integer idGuia) throws ControladorException;
	
	/**
	 * [UC0188] Manter Guia de Pagamento
	 * Fluxo Principal 
	 * 2.1.2.2.	Verificar se h� rela��o do cancelamento com itens de negativa��o:
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public void verificarRelacaoDoCancelamentoComItensNegativacao(
			GuiaPagamento guiaPagamento) throws ControladorException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * [SB0013] - Atualizar Item da Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 24/09/2009
	 */
	public void verificarRelacaoDoParcelamentoComItensNegativacao(
			Parcelamento parcelamento, Conta conta, GuiaPagamento guiaPagamento) throws ControladorException;
	
	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * @author Vivianne Sousa
	 * @date 25/09/2009
	 */
	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(
			Collection<GuiaPagamento> colecaoGuiasPagamento) throws ControladorException;
	
	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(
			ContaHistorico contaHistorico) throws ControladorException;
	
	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 29/10/2009
	 */
	public void gerarResumoNegativacao(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException;
	
	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 * Fluxo Principal 3.6
	 *
	 * @author Vivianne Sousa
	 * @date 29/10/2009
	 */
	public void acompanharPagamentoDoParcelamento(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException ;
	
	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 * Fluxo principal Item 1.0
	 *
	 * @author Vivianne Sousa
	 * @date 09/02/2010
	 */
	public List consultarSetorParaGerarResumoDiarioNegativacao()
		throws ControladorException;
	
	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public boolean existeOcorrenciaMovimentoExclusaoIncompleto()throws ControladorException;

	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 * 
	 * seleciona os im�veis enviados para a negativa��o e 
	 * que foram efetivamente negativados e atualiza a situa��o de cobran�a 
	 * dos referidos im�veis e a data de confirma��o da negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */ 
	public void determinarConfirmacaoDaNegativacao(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException ;
	
	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	public List consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao()
		throws ControladorException;
	
	/**
	 * [UC0473] Consultar Dados Complementares do Im�vel
	 *
	 * @author Vivianne Sousa
	 * @date 04/05/2010
	 */
	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel)
	throws ControladorException;
	
	/**
	 * [UC0651] - Inserir Comando de Negativa��o
	 * [FS0030] - Verificar exist�ncia de inclus�o no negativador para o im�vel
	 * 
     * @author Vivianne Sousa
     * @data 06/05/2010
	 */
	public boolean verificarExistenciaDeInclusaoNoNegativadorParaImovel(
			Integer idImovel, Integer idNegativador) throws ControladorException;
	
	/**
	 * [UC0651] Inserir Comando de Negativa��o
	 * [SB0005] � Obter D�bito do Im�vel
	 * 
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * [SB0006] � Verificar Crit�rio de Negativa��o para o Im�vel
	 * 
     * @author Vivianne Sousa
     * @data 21/06/2010
	 */
	public Collection retirarContaPagaOuParceladaEEntradaParcelamento (Collection colecaoContasValores) 
		throws ControladorException ;

	/**
	 * [UC0651] Inserir Comando de Negativa��o
	 * [SB0005] � Obter D�bito do Im�vel
	 * 
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * [SB0006] � Verificar Crit�rio de Negativa��o para o Im�vel
	 * 
     * @author Vivianne Sousa
     * @data 21/06/2010
	 */
	public Collection retirarGuiaPagamentoDeEntradaParcelamento (
			Collection colecaoGuiaPagamentoValoresHelper) throws ControladorException ;
	
	/**
	 * [UC0472] Consultar Im�vel
	 * 
     * @author Vivianne Sousa
     * @data 03/12/2010
	 */
	public Collection pesquisarNegativadorRetornoMotivoDoReg(
			Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0681] Consultar Movimentos dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 07/12/2010
	 */
	public void atualizarIndicadorCorrecaoEUsuarioCorrecao(
			Integer usuarioCorrecao, Short indicadorCorrecao, 
			Collection colecaoIdsNegativadorMovimentoReg)  throws ControladorException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 21/12/2010
	 *
	 * @param codigoImovel
	 * @param codigoCobrancaSituacao
	 */
	public boolean existeImovelCobrancaSituacaoParaImovel(
			Integer codigoImovel, Integer codigoCobrancaSituacao) throws ControladorException;
	
	/**
	 *
	 * Conta a quantidade de Clientes Negativados para a Unidade, Ger�ncia e Data de Envio 
	 * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 10/02/2011
	 */
	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;
	
	/**
	 *
	 * Soma os valores de d�bitos dos Clientes Negativados para a Unidade, Ger�ncia e Data de Envio 
	 * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
	 	throws ControladorException;
	
	/**
	 *
	 * Soma os valores Pagos dos Clientes Negativados para a Unidade, Ger�ncia e Data de Envio 
	 * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
	 	throws ControladorException;
	
	/**
	 *
	 * Conta a quantidade de Clientes Negativados com contas pagas na Unidade, Ger�ncia e Data de Envio 
	 * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ControladorException;
	
	/**
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * 
	 * [SB0014] � Desfazer Atualiza��o da Execu��o Descontinuada
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param nComando
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void desfazerAtualizacaoExecucaoDescontinuada(Integer idFuncionalidadeIniciada, NegativacaoComando nComando)throws ControladorException ;

	/**
	 * UC1009 � Obter Itens de Negativa��o Associados � Guia 
	 * 
     * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
     * com NMRI_ICSITDEFINITIVA=2 e GPAG_ID=GPAG_ID do PAGAMENTO
     * 
     * @author Vivianne Sousa
     * @data 10/03/2010
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(
			Integer idGuia) throws ControladorException;
}
