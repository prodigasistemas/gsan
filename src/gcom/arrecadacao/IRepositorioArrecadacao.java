package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.arrecadacao.aviso.bean.PagamentosDevolucoesHelper;
import gcom.arrecadacao.aviso.bean.ValoresArrecadacaoDevolucaoAvisoBancarioHelper;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.bean.ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper;
import gcom.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gcom.arrecadacao.bean.PesquisarAnaliseArrecadacaoHelper;
import gcom.arrecadacao.bean.PesquisarAnaliseAvisosBancariosHelper;
import gcom.arrecadacao.bean.PesquisarAvisoBancarioPorContaCorrenteHelper;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoCartaoDebito;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gcom.relatorio.arrecadacao.GuiaDevolucaoRelatorioHelper;
import gcom.relatorio.arrecadacao.RelatorioAnaliseArrecadacaoBean;
import gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancariosBean;
import gcom.relatorio.arrecadacao.RelatorioAvisoBancarioPorContaCorrenteBean;
import gcom.relatorio.arrecadacao.RelatorioDocumentoNaoAceitosBean;
import gcom.relatorio.arrecadacao.RelatorioTranferenciaPagamentoBean;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface para o reposit�rio de cliente
 * 
 * @author S�vio Luiz
 * @created 22 de Abril de 2005
 */
public interface IRepositorioArrecadacao {

	public Integer pesquisarIdRegistroCodigo(String codigoRegistro)
			throws ErroRepositorioException;

	public Short pesquisarNumeroDiasFloat(Integer codigoBanco,
			Integer idFormaArrecadacao) throws ErroRepositorioException;

	public AvisoBancario pesquisarAvisoBancario(Integer codigoBanco,
			Date dataGeracaoArquivo, Date dataPrevistaCredito,Integer idArrecadadorMovimento,
			Integer idFormaArrecadacao)
			throws ErroRepositorioException;

	public Integer pesquisarExistenciaGuiaPagamento(Imovel imovel,
			Integer idDebitoTipo, BigDecimal valorPagamento) throws ErroRepositorioException;
	
	public GuiaPagamento pesquisarExistenciaGuiaPagamento(Imovel imovel,
			BigDecimal valorPagamento) throws ErroRepositorioException;
	
	public Integer pesquisarExistenciaGuiaPagamentoCliente(Integer idCliente,
			Integer idDebitoTipo) throws ErroRepositorioException;

	public Double pesquisarDeducoesAvisoBancario(String codigoAgente,
			Date dataLancamento, String numeroSequencial)
			throws ErroRepositorioException;

	public Short pesquisarValorMaximoNumeroSequencial(Date dataLancamento,
			String idArrecadador) throws ErroRepositorioException;

	/*public ArrecadadorContrato pesquisarNumeroSequecialArrecadadorContrato(
			Short idArrecadador) throws ErroRepositorioException;*/
	
	public ArrecadadorContrato pesquisarNumeroSequecialArrecadadorContrato(
			Integer idArrecadadorContrato) throws ErroRepositorioException;

	public Integer pesquisarIdArrecadacaoForma(String codigoArrecadacaoForma)
			throws ErroRepositorioException;

	public Integer verificarExistenciaAgencia(String codigoAgencia,Integer idBanco)
			throws ErroRepositorioException;

	public Integer verificarExistenciaBanco(Integer idBanco)
			throws ErroRepositorioException;

	public Integer pesquisarIdDepositoArrecadacao(Integer codigoBanco, String codigoConvenio)
	throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro do movimento dos arrecadadores
	 * 
	 * [UC0263] - Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Raphael Rossiter
	 * @date 02/03/2006
	 * 
	 * @param filtroArrecadadorMovimento
	 * @return Uma cole��o de objetos do tipo ArrecadadorMovimento de acordo com
	 *         os par�metros recebidos atrav�s do filtro. Est� consulta inclui
	 *         os movimentos abertos e fechados
	 * @throws ErroRepositorioException
	 */
	public Collection<ArrecadadorMovimento> filtrarMovimentoArrecadadores(
			FiltroArrecadadorMovimento filtroArrecadadorMovimento)
			throws ErroRepositorioException;

	/**
	 * Calcula o valor total dos avisos banc�rios de um determinado movimento
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Um BigDecimal que representa o somat�rio de todos os avisos
	 *         banc�rios de um determinado movimento
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterTotalArrecadacaoAvisoBancarioPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento)
			throws ErroRepositorioException;

	/**
	 * Obt�m o n�mero de registros em ocorr�ncia de um determinado movimento
	 * (n�mero de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com ARMV_ID =
	 * ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_DSOCORRENCIA diferente de
	 * "OK")
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @param descricaoOcorrencia
	 * @return Um Integer que representa a quantidade de registros selecionados
	 * @throws ErroRepositorioException
	 */
	public Integer obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento,
			String descricaoOcorrencia) throws ErroRepositorioException;

	/**
	 * Obt�m o n�mero de registros que n�o foram aceitos de um determinado
	 * movimento (n�mero de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com
	 * ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_ICACEITACAO
	 * igual a 2 (N�O))
	 * 
	 * @author Raphael Rossiter
	 * @date 08/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Um integer que representa a quantidade de registros selecionados
	 * @throws ControladorException
	 */
	public Integer obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento, Short indicadorAceitacao)
			throws ErroRepositorioException;

	/**
	 * Seleciona os avisos banc�rios de um determinado movimento
	 * 
	 * @author Raphael Rossiter
	 * @date 09/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Uma Collection com os avisos banc�rios de um determinado
	 *         movimento
	 * @throws ErroRepositorioException
	 */
	public Collection<AvisoBancario> obterAvisosBancariosPorArrecadadorMovimento(
			ArrecadadorMovimento arrecadadorMovimento)
			throws ErroRepositorioException;

	/**
	 * Calcula o valor total dos pagamentos associados a um determinado aviso
	 * banc�rio (soma (PGMT_VLPAGAMENTO) da tabela PAGAMENTO com AVBC_ID =
	 * AVBC_ID da tabela AVISO_BANCARIO)
	 * 
	 * @author Raphael Rossiter
	 * @date 09/03/2006
	 * 
	 * @param avisoBancario
	 * @return Um BigDecimal que representa o somat�rio de todos os pagamentos
	 *         de um determinado aviso
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterTotalPagamentoPorAvisoBancario(
			AvisoBancario avisoBancario) throws ErroRepositorioException;

	/**
	 * Calcula o valor total das devolu��es associados a um determinado aviso
	 * banc�rio (soma (DEVL_VLDEVOLUCAO) da tabela DEVOLUCAO com AVBC_ID =
	 * AVBC_ID da tabela AVISO_BANCARIO)
	 * 
	 * @author Raphael Rossiter
	 * @date 09/03/2006
	 * 
	 * @param avisoBancario
	 * @return Um BigDecimal que representa o somat�rio de todos as devolu��es
	 *         de um determinado aviso
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterTotalDevolucaoPorAvisoBancario(
			AvisoBancario avisoBancario) throws ErroRepositorioException;

	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador.
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa
	 * @data 20/03/2006,05/12/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItem>
	 */
	public Collection<ArrecadadorMovimentoItem> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia)
			throws ErroRepositorioException;
	
	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador.
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa, Kassia Albuquerque
	 * @data 20/03/2006,05/12/2006,22/08/2007
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItem>
	 */
	public Collection<ArrecadadorMovimentoItem> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia,String codigoArrecadacaoForma)
			throws ErroRepositorioException;

	/**
	 * Faz a pesquisa de devolu��o fazendo os carregamentos de clienteContas,
	 * clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corr�a
	 * @date
	 * 
	 * @param FiltroDevolucao
	 * @return Collection<Devolucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucao(
			FiltroDevolucao filtroDevolucao) throws ErroRepositorioException;

	/**
	 * Exclui os dados di�rios da arrecada��o do ano/m�s da arrecada��o corrente
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @author Pedro Alexandre
	 * @date 11/04/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosDiariosArrecadacaoPorAnoMesArrecadacao(
			int anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	/**
	 * Acumula a quantidade e o valor dos pagamentos com ano/m�s de refer�ncia
	 * da arrecada��o igual ao ano/m�s de refer�ncia da arrecada��o corrente
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @author Pedro Alexandre
	 * @date 11/04/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularQuantidadeEValorPagamentoPorAnoMesArrecadacao(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Acumula a quantidade e o valor das devolucoes com ano/m�s de refer�ncia
	 * da arrecada��o igual ao ano/m�s de refer�ncia da arrecada��o corrente
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 01/04/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularQuantidadeEValorDevolucaoPorAnoMesArrecadacao(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualiza a situa��o atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente a pagamento classificado (tabela PAGAMENTO_SITUACAO) e
	 * atualiza o id da conta nos pagamentos (seta CNTA_ID da tabela PAGAMENTO
	 * para CNTA_ID da tabela CONTA)
	 * 
	 * [SF0002] Processar Pagamento de Conta
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 19/04/2006, 06/12/2006
	 * 
	 * @param mapPagamentosProcessados
	 * @return void
	 */
	public void processarPagamentoConta(
			Map<Integer, Collection> mapPagamentosProcessados)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualiza a situacao atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente ao par�metro passado
	 * 
	 * @author Raphael Rossiter
	 * @date 19/04/2006
	 * 
	 * @param idsPagamentos,
	 *            pagamentoSituacao
	 * @return void
	 */
	public void atualizarSituacaoPagamento(String[] idsPagamentos,
			Integer pagamentoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de D�bito Autom�tico para o banco
	 * 
	 * pesquisa todos os bancos que tenham contrato vigente para arrecadador
	 * contas com forma de arrecada��o correspondente a debito autom�tico
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author S�vio Luiz
	 * @date 18/04/2006
	 * 
	 * @return Cole��o de Bancos
	 * @throws ErroRepositorioException
	 */

	public Collection<Banco> pesquisaBancosDebitoAutomatico()
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de D�bito Autom�tico para o banco
	 * 
	 * pesquisa os movimentos de d�bito autom�tico para o banco,referentes ao
	 * grupo e ano/m�s de faturamento informados
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author S�vio Luiz, Raphael Rossiter, Anderson Italo
	 * @date 18/04/2006, 01/12/2008, 04/02/2010
	 * 
	 * [FS0006] � Verificar a situa��o da conta
	 * Caso a situa��o da conta sejam normal (0) ou retificada (1) ou inclu�da (2), 
	 * gerar movimentos de d�bito autom�tico para o banco.
	 * Caso contr�rio retornar para o passo correspondente no subfluxo.
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return Cole��o de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisaDebitoAutomaticoMovimento(
			Collection colecaoFaturamentoGrupo, Integer anoMesReferenciaFaturamento,
			Collection colecaoidsBanco) throws ErroRepositorioException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Cole��o de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarAvisoBancarioAbertoFechado(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de D�bito Autom�tico para o banco
	 * 
	 * pesquisa 2 campos do arrecadador contrato
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 18/04/2006
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return C�digo do Conv�nio, numero sequencial de envio
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisaCamposArrecadadorContrato(Integer idBanco)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de D�bito Autom�tico para o banco
	 * 
	 * pesquisa a agencia passando o id do banco
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2006
	 * 
	 * @param idBanco
	 * @return Agencia
	 * @throws ErroRepositorioException
	 */

	public Agencia pesquisaAgenciaPorBanco(Integer idBanco)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de D�bito Autom�tico para o banco
	 * 
	 * atualiza o numero sequencial arquivo envio debito automatico
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2006
	 * 
	 * @param idBanco
	 * @return C�digo do Conv�nio, numero sequencial de envio
	 * @throws ErroRepositorioException
	 */

	public void atualizarNumeroSequencialArrecadadorContrato(
			Integer idArrecadadorContrato, Integer numeroSequencialArquivo)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de D�bito Autom�tico para o banco
	 * 
	 * pesquisa o email do arrecadador contrato passando o c�digo do banco
	 * 
	 * [SB0003] - Regerar arquivo TXT para um movimento de d�bito autom�tico
	 * gerado anteriormente
	 * 
	 * @author S�vio Luiz
	 * @date 25/04/2006
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarEmailArrecadadorContrato(Short codigoBanco)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualizar Valor Excedente do Pagamento
	 * 
	 * [SF0009] Atualizar Valor Excedente do Pagamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2006
	 * 
	 * @param pagamento
	 * @return void
	 */
	public void atualizarValorExcedentePagamento(Pagamento pagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualizar Valor Excedente do Pagamento
	 * 
	 * [SF0009] Atualizar Valor Excedente do Pagamento
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 25/04/2006, 29/11/2006
	 * 
	 * @param colecaoPagamento
	 * @return void
	 */
	public void atualizarValorExcedentePagamento(
			Collection<Pagamento> colecaoPagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualiza a situa��o atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente a pagamento classificado (tabela PAGAMENTO_SITUACAO) e
	 * atualiza o id da guia de pagamento nos pagamentos (seta GPAG_ID da tabela
	 * PAGAMENTO para GPAG_ID da tabela GUIA_PAGAMENTO)
	 * 
	 * [SF0004] Processar Pagamento de Guia de Pagamento
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 26/04/2006, 11/12/2006
	 * 
	 * @param mapPagamentosProcessados
	 * @throws ErroRepositorioException
	 */
	public void processarPagamentoGuiaPagamento(
			Map<Integer, Collection> mapPagamentosProcessados)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona a guia de pagamento correspondente ao pagamento
	 * atrav�s do im�vel, cliente e do tipo de d�bito (a partir da tabela
	 * GUIA_PAGAMENTO com IMOV_ID, CLIE_ID e DBTP_ID da tabela PAGAMENTO e
	 * DCST_IDATUAL com valor correspondente a normal da tabela
	 * DEBITO_CREDITO_SITUACAO)
	 * 
	 * [SF0003] Selecionar Guia de Pagamento pela Localidade, Im�vel, Cliente e
	 * D�bito Tipo
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre, Pedro Alexandre
	 * @date 26/04/2006, 14/03/2007, 05/06/2007
	 * 
	 * @param imovel
	 * @param cliente
	 * @param debitoTipo
	 * @param anoMesFaturamento
	 * 
	 * @return Collection<GuiaPagamento>
	 */
	public Collection<GuiaPagamento> selecionarGuiaPagamentoPelaLocalidadeImovelClienteDebitoTipo(
			Imovel imovel, Cliente cliente, DebitoTipo debitoTipo, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualiza a situa��o atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente a pagamento classificado (tabela PAGAMENTO_SITUACAO) e
	 * atualiza o id do d�bito a cobrar nos pagamentos (seta DBAC_ID da tabela
	 * PAGAMENTO para DBAC_ID da tabela DEBITO_A_COBRAR)
	 * 
	 * [SF0004] Processar Pagamento de D�bito a Cobrar
	 * 
	 * @author Raphael Rossiter ,Pedro Alexandre
	 * @date 27/04/2006, 12/12/2006
	 * 
	 * @param mapPagamentosProcessados
	 * @throws ErroRepositorioException
	 */
	public void processarPagamentoDebitoACobrar(
			Map<Integer, Collection> mapPagamentosProcessados)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona o d�bito a cobrar correspondente ao pagamento atrav�s
	 * do im�vel e do tipo de d�bito (a partir da tabela DEBITO_A_COBRAR com
	 * IMOV_ID e DBTP_ID da tabela PAGAMENTO e DCST_IDATUAL com valor
	 * correspondente a normal da tabela DEBITO_CREDITO_SITUACAO)
	 * 
	 * [SF0005] Selecionar D�bito a Cobrar pela Localidade, Im�vel e D�bito Tipo
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 26/04/2006, 05/06/2007
	 * 
	 * @param imovel,
	 * @param debitoTipo
	 * @param anoMesFaturamento
	 * 
	 * @return Collection<DebitoACobrar>
	 */
	public Collection<DebitoACobrar> selecionarDebitoACobrarPelaLocalidadeImovelDebitoTipo(
			Imovel imovel, DebitoTipo debitoTipo, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * Atualizar Devolucao
	 * 
	 * @author Fernanda Paiva
	 * @created 03/05/2006
	 * 
	 * @param valor
	 *            arrecadacao
	 * 
	 * @exception controladorException
	 *                controlador Exception
	 */
	public void atualizaValorArrecadacaoAvisoBancaraio(BigDecimal valor,
			Integer codigoAvisoBancario) throws ErroRepositorioException;

	/**
	 * 
	 * Faz a pesquisa de guia de devolu��o para o relat�rio fazendo os
	 * carregamentos de clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corr�a
	 * @date 11/09/2006
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<GuiaDevolucao> pesquisarGuiaDevolucaoRelatorio(
			FiltroGuiaDevolucao filtroGuiaDevolucao)
			throws ErroRepositorioException;

	/**
	 * [UC0324] - Filtrar Guia de Devolucao
	 * 
	 * [SF0001] - Seleciona Guias de Devolu��o do Cliente
	 * 
	 * Faz a pesquisa de guia de devolu��o fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corr�a
	 * @date
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<GuiaDevolucao> pesquisarGuiaDevolucao(
			FiltroGuiaDevolucao filtroGuiaDevolucao, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * [UC0324] - Filtrar Guia de Devolucao
	 * 
	 * [SF0001] - Seleciona Guias de Devolu��o do Cliente
	 * 
	 * Faz a pesquisa de guia de devolu��o fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corr�a
	 * @date
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarGuiaDevolucaoCount(
			FiltroGuiaDevolucao filtroGuiaDevolucao)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 23/05/2006, 27/04/2011
	 * @param anoMesReferencia
	 * @param estadoMunicipio
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstado(
			int anoMesReferencia, boolean estadoMunicipio) throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorLocalidade(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Diogo Peixoto
	 * @created 20/04/2011
	 * 
	 * @param anoMesReferencia
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorMunicipio(int anoMesReferencia)
		throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Diogo Peixoto
	 * @created 20/04/2011
	 * 
	 * @param anoMesReferencia
	 * @param idMunicipio
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorMunicipio(int anoMesReferencia, Integer idMunicipio)
		throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorGerenciaRegional(
			int anoMesReferencia, Integer gerenciaRegional)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorGerenciaRegionalPorLocalidade(
			int anoMesReferencia, Integer gerenciaRegional)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a gera��o do relat�rio '[UC0345] Gerar
	 * Relat�rio de Resumo Arrecada��o' de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorLocalidade(
			int anoMesReferencia, Integer localidade)
			throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Conta
	 * 
	 * pesquisa o nome do banco e c�digo da agencia passando o id do im�vel
	 * 
	 * [SB0017] - Gerar Linhas das contas com D�bito Autom�tico
	 * 
	 * @author S�vio Luiz
	 * @date 26/05/2006
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarParmsDebitoAutomatico(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consulta a qtde de registros ResumoArrecadacao para a gera��o do
	 * relat�rio '[UC0345] Gerar Relat�rio de Resumo do Arrecadacao' de acordo
	 * com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 02/06/2006, 20/04/2011
	 * 
	 * @param opcaoTotalizacao
	 * @param mesAnoReferencia
	 * @param gerenciaRegional
	 * @param localidade
	 * @param municipio
	 * @return
	 * @throws ControladorException
	 */
	public Integer consultarQtdeRegistrosResumoArrecadacaoRelatorio(int anoMesReferencia, Integer localidade, Integer gerenciaRegional,
			Integer municipio, String opcaoTotalizacao) throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Inseri os resumos das arrecada��es gerados pelo batch no sistema
	 * 
	 * @author Pedro Alexandre
	 * @date 17/05/2006
	 * 
	 * @param colecaoResumoArrecadacao
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoArrecadacao(
			Collection<ResumoArrecadacao> colecaoResumoArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Verifica se j� existe resumo da arrecada��o para o ano/m�s de refer�ncia
	 * da arrecada��o
	 * 
	 * [FS0003] - Verificar a exist�ncia do resumo da arrecada��o
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoArrecadacaoPorAnoMesArrecadacao(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos classificados de conta do ano/m�s de refer�ncia da
	 * arrecada��o com a situa��o atual(PGST_IDATUAL) igual a pagamento
	 * classificado ou baixar valor excedente e com o c�digo da conta diferente
	 * de nulo (CNTA_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosClassificadosContas(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos classificados de guia de pagamento do ano/m�s de
	 * refer�ncia da arrecada��o com a situa��o atual(PGST_IDATUAL) igual a
	 * pagamento classificado ou baixar valor excedente e com o c�digo da guia
	 * de pagamento diferente de nulo (GPAG_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosClassificadosGuiasPagamento(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos classificados de d�bito a cobrar do ano/m�s de
	 * refer�ncia da arrecada��o com a situa��o atual(PGST_IDATUAL) igual a
	 * pagamento classificado ou baixar valor excedente e com o c�digo do d�bito
	 * a cobrar diferente de nulo (DBAC_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosClassificadosDebitoACobrar(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos n�o classificados do m�s, que s�o do ano/m�s de
	 * refer�ncia da arrecada��o com a situa��o atual(PGST_IDATUAL) diferente de
	 * pagamento classificado
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosNaoClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es classificadas do ano/m�s de refer�ncia da
	 * arrecada��o e com situa��o atual igual a devolu��o classificada ou
	 * devolu��o de outros valores
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesClassificadas(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es n�o classificadas do m�s, para situa��o atual
	 * diferente de devolu��o classificada e devolu��o de outros valores
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesNaoClassificadasMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos de contas efetuados em meses anteriores
	 * classificados no m�s, que s�oos do ano/m�s de refer�ncia anterior ao da
	 * arrecada��o, que foram classificados no m�s, com situa��o atual igual a
	 * pagamento classificado ou baixar valor excedente e com c�digo da conta
	 * diferente de nulo (CNTA_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosContasEfetuadosEmMesesAnterioresClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos de guias de pagamento efetuados em meses
	 * anteriores classificados no m�s, que s�o os do ano/m�s de refer�ncia
	 * anterior ao da arrecada��o, que foram classificados no m�s, com situa��o
	 * atual igual a pagamento classificado ou baixar valor excedente e com
	 * c�digo da guia de pagamento diferente de nulo (GPAG_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosGuiasPagamentoEfetuadosEmMesesAnterioresClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos de d�bitos a cobrar efetuados em meses anteriores
	 * classificados no m�s, que s�o os do ano/m�s de refer�ncia anterior ao da
	 * arrecada��o, que foram classificados no m�s, com situa��o atual igual a
	 * pagamento classificado ou baixar valor excedente e com c�digo do d�bito a
	 * cobrar diferente de nulo (DBAC_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosDebitoACobrarEfetuadosEmMesesAnterioresClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es efetuadas em meses anteriores classificadas no
	 * m�s, que s�o as do ano/m�s de refer�ncia anterior ao da arrecada��o e que
	 * foram classificadas no m�s, comsitua��o atual igual a devolu��o
	 * classificada ou devolu��o de outros valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos n�o classificados com baixa comandada, que s�o os
	 * que est�o com a situa��o atual com o valor correspondente a baixar
	 * excedente
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosNaoClassificadosComBaixaComandada()
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os pagamentos n�o classificados, que s�o os do ano/m�s de
	 * refer�ncia igual ou anterior ao da arrecada��o e que est�o n�o
	 * classificados,com situa��o atual diferente de pagamento classificado e de
	 * baixar valor excedente
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosNaoClassificados(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es n�o classificadas, que s�o as do ano/m�s de
	 * refer�ncia igual ou anterior ao da arrecada��o e que continuam n�o
	 * classificados, com situa��o atual com o valor diferente de devolu��o
	 * classificada e devolu��o de outros valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesNaoClassificadas(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 100
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor de �gua por categoria e localidade paa os pagamentos
	 * classificados de conta
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAguaPagamentosClassificadosConta(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 200
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor de esgoto por categoria e localidade paa os pagamentos
	 * classificados de conta
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgotoPagamentosClassificadosConta(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 300
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor do d�bitos cobrados por localidade, categoria e item
	 * cont�bil dos pagamentos classificados de conta para tipo de financiamento
	 * igual a servi�o
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lan�amento igual a 400
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor do d�bitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de �gua
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoAgua(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lan�amento igual a 500
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor do d�bitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de esgoto
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoEsgoto(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lan�amento igual a 600
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor do d�bitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de servi�o e grupo de parcelamento diferente de juros
	 * cobrados
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoServicoGrupoParcelamentoDiferenteJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lan�amento igual a 700
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor do d�bitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de servi�o e grupo de parcelamento igual a juros cobrados
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoServicoGrupoParcelamentoIgualJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 800
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos cr�ditos realizados por localidade e categoria para
	 * os pagamentos classificados de contas, para origem de cr�dito igual a
	 * contas pagas em duplicidade/excesso.
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCreditoContasPagasEmDuplicidadeExcesso(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lan�amento igual a 900
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos cr�ditos realizados por localidade, categoria e item
	 * cont�bil para os pagamentos classificados de contas, para origem de
	 * cr�dito igual a valores cobrados indevidamente.
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCreditoValoresCobradosIndevidamente(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 1000
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos cr�ditos realizados por localidade e categoria para
	 * os pagamentos classificados de contas, para origem de cr�dito igual a
	 * descontos concedidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCredito(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria,Integer idCreditoOrigem) throws ErroRepositorioException;

	

	

	
	
	/**
	 * Sequencial do tipo lan�amento igual a 1700
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor da entrada do parcelamento por localidade e categoria dos
	 * pagamentos classificados de guias de pagamento com tipo de financiamento
	 * igual a entrada de parcelamento
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosClassificadosGuiaPagamentoFinanciamentoTipoEntradaParcelamento(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 1800
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor da guia de pagamento por localidade, categoria e item
	 * cont�bil dos pagamentos classificados de guias de pagamento com tipo de
	 * financiamento igual a servi�o
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosClassificadosGuiaPagamentoFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 1900
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor que falta ser cobrado dos d�bitos a cobrar dos pagamentos
	 * classificados de d�bito a cobrar por localidade, categoria e item
	 * cont�bil
	 * 
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosClassificadosDebitoACobrar(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;


	




	/**
	 * Sequencial do tipo lan�amento igual a 2600
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es classificadas para acumular o valor da devolu��o
	 * por categoria com situa��o igual a devolu��o classificada.
	 * 
	 * @author Pedro Alexandre
	 * @date 25/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesClassificadasSituacaoAtualDevolucaoClassificada(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 2700
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es classificadas para acumular o valor da devolu��o
	 * por categoria e item cont�bil com situa��o igual a devolu��o de outros
	 * valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 25/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesClassificadasSituacaoAtualDevolucaoOutrosValores(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	

	

	

	/**
	 * Sequencial do tipo lan�amento igual a 3500
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor de �gua por localidade e categoria para os pagamentos de
	 * contas efetuados em meses anteriores classificados no m�s.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAguaPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 3600
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor de esgoto por localidade e categoria para os pagamentos
	 * de contas efetuados em meses anteriores classificados no m�s.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgotoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 3700
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos d�bitos cobrados por localidade, categoria e item
	 * cont�bil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no m�s para tipo de financiamento igual a servi�o.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lan�amento igual a 3800
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos d�bitos cobrados por localidade e categoria para os
	 * pagamentos de contas efetuados em meses anteriores classificados no m�s
	 * para tipo de financiamento igual a parcelamento de �gua.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoAgua(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	/**
	 * Sequencial do tipo lan�amento igual a 3900
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos d�bitos cobrados por localidade e categoria para os
	 * pagamentos de contas efetuados em meses anteriores classificados no m�s
	 * para tipo de financiamento igual a parcelamento de esgoto.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoEsgoto(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	/**
	 * Sequencial do tipo lan�amento igual a 4000
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos d�bitos cobrados por localidade, categoria e item
	 * cont�bil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no m�s para tipo de financiamento igual a parcelamento de
	 * servi�o e grupo de parcelamento diferente de juros cobrados.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoServicoGrupoParcelamentoDiferenteJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lan�amento igual a 4100
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos d�bitos cobrados por localidade, categoria e item
	 * cont�bil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no m�s para tipo de financiamento igual a parcelamento de
	 * servi�o e grupo de parcelamento igual a juros cobrados.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoServicoGrupoParcelamentoIgualJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 4200
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos cr�ditos realizados por localidade e categoria para
	 * os pagamentos de contas efetuados em meses anteriores classificados no
	 * m�s, para origem do cr�dito igual a documentos pagos em
	 * duplicidade/excesso.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesOrigemCreditoContasPagasEmDuplicidadeExcesso(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	/**
	 * Sequencial do tipo lan�amento igual a 4300
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos cr�ditos realizados por localidade, categoria e item
	 * cont�bil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no m�s, para origem do cr�dito igual a valores cobrados
	 * indevidamente.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesOrigemCreditoValoresCobradosIndevidamente(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 4400
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor dos cr�ditos realizados por localidade e categoria, para
	 * os pagamentos de contas efetuados em meses anteriores classificados no
	 * m�s, para origem do cr�dito igual a descontos concedidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesOrigemCreditoDescontosConcedidos(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	

	
	

	

	/**
	 * Sequencial do tipo lan�amento igual a 5100
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor das entrads do parcelamento por localidade e categoria
	 * dos pagamento de guias de pagamento efetuados em meses anteriores
	 * classificados no m�s com tipo de financiamento igual a entrada de
	 * parcelamento.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoEntradaParcelamento(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 5200
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor das entrads do parcelamento por localidade, categoria e
	 * item cont�bil dos pagamento de guias de pagamento efetuados em meses
	 * anteriores classificados no m�s com tipo de financiamento igual a
	 * servi�o.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 5300
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor que falta ser cobrado dos d�bitos a cobrar por
	 * localidade, categoria e item cont�bil para os pagamentos de d�bitos a
	 * cobrar efetuados em meses anteriores classificados no m�s.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosDebitoACobrarEfetuadosEmMesesAnteriores(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 5500
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es efetuadas em meses anteriores classificadas no m�s
	 * para acumular o valor da devolu��o por localidade e categoria com
	 * situa��o atual igual a devolu��o classificada.
	 * 
	 * @author Pedro Alexandre
	 * @date 30/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMesSituacaoAtualDevolucaoClassificada(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 5600
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es efetuadas em meses anteriores classificadas no m�s
	 * para acumular o valor da devolu��o por localidade, categoria e item
	 * cont�bil, com situa��o atual igual a devolu��o de outros valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 31/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMesSituacaoAtualDevolucaoOutrosValores(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	
	

	/**
	 * Sequencial do tipo lan�amento igual a 6200 (SOMA DOS SEQ.100,200,3500 e
	 * 3600)
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Este met�do acumular os valores dos sequencias : 100, 200, 3500 e
	 * 3600,para ser acumuldo � soma dos recebimentos de valores contabilizados
	 * como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAgua_EsgotoPagamentosClassificadosNoMes_EfetuadosEmMesesAnterioresContaContabilizadasComoPerdas(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 6200 (SOMA DOS SEQ.
	 * 300,400,500,600,700,3700,3800,3900,4000 e 4100)
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Este met�do acumular os valores dos sequencias : 300, 400, 500, 600, 700,
	 * 3700, 3800, 3900, 4000 e 4100,para ser acumulado � soma dos recebimentos
	 * de valores contabilizados como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosNoMes_EfetuadosEmMesesAnterioresContaContabilizadaComoPerdasFinanciamentoTipoServico_ParcelamentoAgua_ParcelamentoEsgoto_ParcelamentoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 6200 (SOMA DOS
	 * SEQ.800,900,1000,4200,4300 e 4400)
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Este met�do acumular os valores dos sequencias : 800, 900, 1000, 4200,
	 * 4300 e 4400, para ser acumulado negativamente � soma dos recebimentos de
	 * valores contabilizados como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosNoMes_EfetuadosMesesAnterioresContaContabilizadaComoPerdasOrigemCredito_ContasPagasEmDuplicidadeExcesso_ValoresCobradosIndevidamente_DescontosConcedidos(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 6200 (SOMA DOS
	 * SEQ.1200,1300,1400,1500,4600,4700,4800 e 4900)
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Este met�do acumular os valores dos sequencias : 1200, 1300, 1400, 1500,
	 * 4600, 4700, 4800 e 4900, para ser acumulado negativamente � soma dos
	 * recebimentos de valores contabilizados como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImpostosDeduzidosPagamentosClassificadosNoMes_MesesAnterioresContaContabilizadasComoPerdasImpostoTipo_IR_CSLL_COFINS_PISPASEP(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os d�bitos cobrados das contas dos pagamentos classificados de
	 * contas e dos pagamentos anteriores de conta classificados no m�s.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<DebitoCobrado> pesquisarDebitosCobradosContasPagamentosClassificados_PagamentosAnterioresContaClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa os cr�ditos realizados das contas dos pagamentos classificados
	 * de contas e dos pagamentos anteriores de conta classificados no m�s.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CreditoRealizado> pesquisarCreditosRealizadosContasPagamentosClassificados_PagamentosAnterioresContaClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o tipo do documento correspondente a
	 * conta
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorConta(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o tipo do documento correspondente a
	 * conta
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idImovel
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarPagamentosPorConta(
			Integer anoMesReferencia, Integer idLocalidade, Integer idImovel,
			Integer anoMesReferenciaPagamento) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformada(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformada(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID n�o informado
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoSemGuiaInformada(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID n�o informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 12/12/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoSemGuiaInformada(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID n�o informado
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarSemDebitoInformada(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID n�o informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarSemDebitoInformada(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformado(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona a conta correspondente ao pagamento atrav�s do im�vel
	 * e ano/m�s de refer�ncia do pagamento (a partir da tabela CONTA com
	 * IMOV_ID = IMOV_ID da tabela PAGAMENTO, PGMT_AMREFERENCIAPAGAMENTO da
	 * tabela PAGAMENTO e DCST_IDATUAL com o valor correspondente a normal,
	 * retificada ou inclu�da, da tabela DEBTIO_CREDITO_SITUACAO)
	 * 
	 * [SF0001] Selecionar Conta pelo Im�vel e Ano/M�s de Refer�ncia
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 05/06/2007
	 * 
	 * @param imovel
	 * @param anoMesReferenciaPagamento
	 * @param anoMesFaturamento
	 * 
	 * @return Conta
	 */
	public Object[] selecionarContaPorImovelAnoMesReferencia(Imovel imovel,
			Integer anoMesReferenciaPagamento, Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Im�vel pesquisarPagamento
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoImovel(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Conta do Cliente
	 * pesquisarPagamentoClienteConta
	 * 
	 * @author Rafael Corr�a
	 * @date 12/12/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoClienteConta(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Guia de Pagamento do Cliente
	 * pesquisarPagamentoClienteGuiaPagamento
	 * 
	 * @author Rafael Corr�a
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoClienteGuiaPagamento(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, 
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Rafael Corr�a
	 * @date 12/06/06
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoClienteDebitoACobrar(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal )
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoLocalidade(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, Integer numeroPagina,
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancario(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, 
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadador(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, 
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Consultar dados diarios da arrecadacao
	 * 
	 * @author Fernanda Paiva
	 * @date 09/06/2006
	 * 
	 * @param anoMesReferencia,
	 *            id
	 * @return Cole��o de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection consultarDadosDiarios(int anoMesReferencia, int id,
			String descricao, int idElo) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona as devolu��es com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/m�s de refer�ncia preenchido
	 * (DEVL_AMREFERENCIADEVOLUCAO com valor diferente de nulo)
	 * 
	 * @author Raphael Rossiter
	 * @data 14/06/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesEmDuplicidadeOUExcesso(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona as devolu��es com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/m�s de refer�ncia preenchido
	 * (DEVL_AMREFERENCIADEVOLUCAO com valor diferente de nulo)
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 14/06/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesEmDuplicidadeOUExcesso(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona as devolu��es com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/m�s de refer�ncia n�o
	 * preenchido (DEVL_AMREFERENCIADEVOLUCAO com valor diferente nulo)
	 * 
	 * @author Raphael Rossiter
	 * @data 14/06/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesCobradasIndevidamente(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona as devolu��es com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/m�s de refer�ncia n�o
	 * preenchido (DEVL_AMREFERENCIADEVOLUCAO com valor diferente nulo)
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 14/06/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesCobradasIndevidamente(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * [SF0010] Selecionar Pagamentos n�o Classificados de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 26/04/2006
	 * 
	 * @param imovel,
	 *            anoMesReferenciaDevolucao
	 * @return Collection<Pagamento>
	 */
	public Collection<Pagamento> selecionarPagamentosNaoClassificadosConta(
			Imovel imovel, Integer anoMesReferenciaDevolucao)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualiza a situacao atual das devolucoes (DVST_IDATUAL) com valor
	 * correspondente a pagamento em duplicidade n�o encontrado (tabela
	 * DEVOLUCAO_SITUACAO)
	 * 
	 * [SF0011] Processar Devolu��es de Pagamentos
	 * 
	 * @author Raphael Rossiter
	 * @date 15/06/2006
	 * 
	 * @param idsDevolucoes
	 * @return void
	 */
	public void atualizarSituacaoDevolucao(String[] idsDevolucao,
			Integer devolucaoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualiza a situacao anterior dos pagamentos (PGST_IDANTERIOR) (tabela
	 * PAGAMENTO_SITUACAO)
	 * 
	 * [SF0008] Processar Pagamento a Maior ou a Menor
	 * 
	 * @author Raphael Rossiter
	 * @date 19/04/2006
	 * 
	 * @param idsPagamentos
	 * @return void
	 */
	public void atualizarSituacaoAnteriorPagamento(String[] idsPagamentos,
			Integer pagamentoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * [SF0012] Selecionar Pagamentos n�o classificados de guia de pagamento ou
	 * d�bito a cobrar
	 * 
	 * @author Raphael Rossiter
	 * @date 15/06/2006
	 * 
	 * @param imovel,
	 *            anoMesReferenciaDevolucao
	 * @return Collection<Pagamento>
	 */
	public Collection<Pagamento> selecionarPagamentosNaoClassificadosGuiaPagamentoDebitoACobrar(
			Imovel imovel, Cliente cliente, DebitoTipo debitoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Fernanda Paiva
	 * @date 16/08/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Cole��o de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarAvisoBancarioAbertoFechadoCount(
			AvisoBancarioHelper avisoBancarioHelper,
			AvisoBancarioHelper avisoBancarioHelperNovo)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Fernanda Paiva
	 * @date 16/08/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Cole��o de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarAvisoBancarioAbertoFechadoParaPaginacao(
			AvisoBancarioHelper avisoBancarioHelper, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoCliente(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, Integer numeroPagina, String valorPagamentoInicial,
            String valorPagamentoFinal )
			throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rafael Corr�a
	 * @date 21/12/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoAvisoBancarioCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoImovelCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<Pagamento> pesquisarPagamentoImovelParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;

	/**
	 * 
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * para o Relat�rio
	 * 
	 * @author Rafael Corr�a
	 * @date 29/08/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<Pagamento> pesquisarPagamentoImovelRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um sql que ser� usado na pesquisa de pagamentos
	 * para o Relat�rio
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rafael Corr�a
	 * @date 12/12/06
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoClienteRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * para o Relat�rio
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Rafael Corr�a
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * para o Relat�rio
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Rafael Corr�a
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * para o Relat�rio
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoLocalidadeRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Rafael Corr�a
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoMovimentoArrecadadorCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoLocalidadeCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	public Integer verificarExistenciaGuiaDevolucao(Integer idGuiaDevolucao)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os avisos banc�rios para o relat�rio atrav�s das op��es
	 * selecionadas no Filtrar Aviso Banc�rio
	 * 
	 * @author Rafael Corr�a
	 * @date 04/09/06
	 * 
	 * @return Collection<AvisoBancarioRelatorioHelper>
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarAvisoBancarioRelatorio(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os avisos dedu��es de um aviso banc�rio para o relat�rio atrav�s
	 * do id do aviso banc�rio
	 * 
	 * @author Rafael Corr�a
	 * @date 05/09/06
	 * 
	 * @return Collection<DeducoesRelatorioHelper>
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarAvisoDeducoesAvisoBancarioRelatorio(
			Integer idAvisoBancario) throws ErroRepositorioException;

	/**
	 * Pesquisa os avisos acertos de um aviso banc�rio para o relat�rio atrav�s
	 * do id do aviso banc�rio
	 * 
	 * @author Rafael Corr�a
	 * @date 05/09/06
	 * 
	 * @return Collection<AcertosRelatorioHelper>
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarAvisoAcertosAvisoBancarioRelatorio(
			Integer idAvisoBancario) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa dos dados di�rios da arrecada��o
	 * 
	 * [UC0333] Filtrar Dados Di�rios da Arrecada��o
	 * 
	 * @author Rafael Santos
	 * @date 05/09/2006
	 * 
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacao(
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String idLocalidade, String idGerenciaRegional,
			String idArrecadador, String idElo, String[] idsImovelPerfil,
			String[] idsLigacaoAgua, String[] idsLigacaoEsgoto,
			String[] idsDocumentosTipos, String[] idsCategoria,
			String[] idsEsferaPoder) throws ErroRepositorioException;

	/**
	 * Pesquisa dos dados di�rios de devolucao
	 * 
	 * [UC0333] Filtrar Dados Di�rios da Arrecada��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 21/07/2008
	 * 
	 * @return
	 */
	public Collection filtrarDevolucaoDadosDiarios(
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String idLocalidade, String idGerenciaRegional,
			String idArrecadador, String idElo, String[] idsImovelPerfil,
			String[] idsLigacaoAgua, String[] idsLigacaoEsgoto,
			String[] idsDocumentosTipos, String[] idsCategoria,
			String[] idsEsferaPoder) throws ErroRepositorioException;
	
	/**
	 * Pesquisa dos dados di�rios da arrecada��o pela Gerencia
	 * 
	 * [UC0333] Filtrar Dados Di�rios da Arrecada��o
	 * 
	 * @author Rafael Santos
	 * @date 05/09/2006
	 * 
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacaoValoresDiarios(
			String idGerenciaRegional) throws ErroRepositorioException;

	/**
	 * Retornar Cole��o do movimento do arrecadador
	 * 
	 * Seleciona Movimento Arrecadadores
	 * 
	 * @author Fernanda Paiva
	 * @date
	 * @throws ErroRepositorioException
	 */
	public Collection<ArrecadadorMovimento> retornarColecaoMovimentoArrecadadores(
			FiltroArrecadadorMovimento filtro, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Retornar o valor do somatorio dos acertos daquele aviso bancario
	 * 
	 * @author Fernanda Paiva
	 * @date
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarSomatorioAvisoAcerto(
			Integer indicadorCreditoDebito, Integer idAviso,
			Integer indicadorArrecadacaoDevolucao)
			throws ErroRepositorioException;

	/**
	 * Retornar os avisos bancario aberto e/ou fechado
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarAvisoBancarioAbertoFechadoFinal(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os dados da Guia de Pagamento necess�rios para o relat�rio
	 * atrav�s do id da Guia de Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/10/06
	 * 
	 * @return GuiaPagamentoRelatorioHelper
	 * @throws ErroRepositorioException
	 */

	public GuiaPagamentoRelatorioHelper pesquisarGuiaPagamentoRelatorio(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Pesquisa o nome do cliente da guia de pagamento atrav�s do id da Guia de
	 * Pagamento e com CRTP_ID com o valor correspondente a usu�rio(2)
	 * 
	 * @author Vivianne Sousa
	 * @date 04/10/06
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarNomeClienteGuiaPagamentoRelatorio(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados da Guia de Devolu��o necess�rios para o relat�rio
	 * atrav�s do id da Guia de Devolu��o
	 * 
	 * @author Ana Maria
	 * @date 05/10/06
	 * 
	 * @return GuiaDevolucaoRelatorioHelper
	 * @throws ErroRepositorioException
	 */

	public GuiaDevolucaoRelatorioHelper pesquisarGuiaDevolucaoRelatorio(
			Integer idGuiaDevolucao) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados do Cliente pelo Im�vel
	 * 
	 * @author Ana Maria
	 * @date 06/10/06
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarClienteImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * para pesquisar os pagamento historicos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos historicos do Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovel(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * 
	 * Filtar a quantiade de pagamento historicos do imovel [UC0255] Filtrar
	 * Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoHistoricoImovelCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ErroRepositorioException;

	/**
	 * Filtra os pagamento historicos do Imovel para pagina��o
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovelParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtra os Pagamento Historicos do Cliente Conta
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteConta(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtrar os pagamentos historicos do Cliente Guia Pagamento
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteGuiaPagamento(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtra os pagamentos historicos do debito a cobrar
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 12/06/06,06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteDebitoACobrar(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtrar a quantidade de pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rafael Santos
	 * @date 06/10/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ErroRepositorioException;

	/**
	 * Filtra os pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 21/08/06,06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoCliente(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtra a quantiadade dos Pagamento Historicos da Localidade
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoHistoricoLocalidadeCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ErroRepositorioException;

	/**
	 * Filtra os Pagamento Historicos da Localidade
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidade(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtra oas pagamento historicos do Aviso Bancario
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancario(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtra a quantidade de pagamento historicos do avio bancario
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtra os pagamento historicos do aviso bancario para pagina��o
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancarioParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtrar pagamentos historicos do movimento arrecador
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoMovimentoArrecadador(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Faz a pesquisa de devolu��oHistorico fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 09/10/2006
	 * 
	 * @param FiltroDevolucaoHistorico
	 * @return Collection<DevolucaoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<DevolucaoHistorico> pesquisarDevolucaoHistorico(
			FiltroDevolucaoHistorico filtroDevolucaoHistorico)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * hist�rico para o Relat�rio
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos hist�rico do tipo Debito a Cobrar do Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 17/10/06
	 * 
	 * @return Collection<PagamentoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidadeRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Consulta dados da tabela dados diarios arrecadacao
	 * 
	 * @author Rafael Santos
	 * @created 21/10/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosDiarios(int idGerenciaRegional,
			int idLocalidade, int idElo) throws ErroRepositorioException;

	/**
	 * Pesquisa conta e ag�ncia do sistema de par�metros
	 * 
	 * @author Ana Maria
	 * @date 23/10/06
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaAgenciaSistemaParametro()
			throws ErroRepositorioException;

	/**
	 * Pesquisa id do lan�amento contabil
	 * 
	 * @author S�vio Luiz
	 * @date 08/11/06
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLancamentoItemContabil(Integer idCreditoTipo)
			throws ErroRepositorioException;

	/**
	 * pesquisar descri��o do D�bito Autom�tico
	 * 
	 * @author S�vio Luiz
	 * @date 22/11/06
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public String pesquisarDescricaoDebitoAutomatico(Integer codigoRetorno)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a lista de ano/m�s de arrecada�a� menores e igual ao ano/m�s de
	 * arrecada��o atual.
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 29/11/2006
	 * 
	 * @param anoMesArrecadacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnoMesArrecadacaoMenorIgualAtual(
			Integer anoMesArrecadacaoAtual) throws ErroRepositorioException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 30/11/06
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param numeroPagina
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarMovimentoArrecadadorParaPaginacao(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, Integer numeroPagina,
			String indicadorAbertoFechado) throws ErroRepositorioException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 30/11/06
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarMovimentoArrecadadoresCount(String codigoBanco,
			String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, String indicadorAbertoFechado)
			throws ErroRepositorioException;

	/**
	 * retorna o somatorio de PGMT_VLPAGAMENTO da tabela PAGAMENTO com AMIT_ID
	 * =AMIT_ID da tabela ARRECADADOR_MOVIMENTO_ITEM
	 * 
	 * [UC0254] Efetuar An�lise do Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 05/12/2006
	 * 
	 * @param idArrecadadorMovimentoItem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal recuperaValorPagamentoArrecadadorMovimentoItem(
			Integer idArrecadadorMovimentoItem) throws ErroRepositorioException;

	/**
	 * retorna a decri��o da Forma de Arrecada��o (arfm_dsarrecadacaoforma) da
	 * tabela ARRECADACAO_FORMA a partir do codigoArrecadacaoForma
	 * (arfm_cdarrecadacaoforma) passado
	 * 
	 * [UC0262] Distribuir Dados do Registro do Movimento do Arrecadador
	 * 
	 * @author Vivianne Sousa
	 * @date 06/12/2006
	 * 
	 * @param codigoArrecadacaoForma
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String recuperaDescricaoArrecadacaoForma(
			String codigoArrecadacaoForma) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * Atualiza a situacao atual dos pagamentos (PGST_IDATUAL) (tabela
	 * PAGAMENTO_SITUACAO)
	 * 
	 * [SF0008] Processar Pagamento a Maior ou a Menor
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 19/04/2006, 30/11/2006
	 * 
	 * @param colecaoIdsPagamentos
	 * @param pagamentoSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoPagamento(Collection colecaoIdsPagamentos,
			Integer pagamentoSituacao) throws ErroRepositorioException;

	/**
	 * Atualiza o valor excedente e a situa��o dos pagamentos informados para o
	 * tipode situa��o informada.
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 12/12/2006
	 * 
	 * @param colecaoPagamento
	 * @param pagamentoSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoEValorExcedentePagamento(
			Collection<Pagamento> colecaoPagamento, Integer pagamentoSituacao)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a lista de ano/m�s de arrecada��o menores e igual ao ano/m�s de
	 * arrecada��o atual e igual ao id do im�vel informado.
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 29/11/2006
	 * 
	 * @param anoMesArrecadacaoAtual
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnoMesArrecadacaoMenorIgualAtualPorImovel(
			Integer anoMesArrecadacaoAtual, Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisar uma cole��o de ids de localidades que possuem pagamentos
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Pedro Alexandre
	 * @date 29/11/2006
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsImovelPorLocalidade(Integer idLocalidade,
			Integer numeroPaginas, Integer quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * Pesquisar os ids das localidades que possuem pagamentos
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @author Pedro Alexandre
	 * @date 04/12/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadeComPagamentosOuDevolucoes()
			throws ErroRepositorioException;

	/**
	 * Pesquisar os ano/m�s de refer�ncia do pagamentos para um im�vel e ano/m�s
	 * de arrecada��o informados para o tipo de documento informado.
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Pedro Alexandre
	 * @date 06/12/2006
	 * 
	 * @param anoMesArrecadacaoAtual
	 * @param idImovel
	 * @param idDocumentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnoMesReferenciaPagamentoParaImovel(
			Integer anoMesArrecadacaoAtual, Integer idImovel,
			Integer idDocumentoTipo) throws ErroRepositorioException;

	/**
	 * Pesquisa a esfera do poder do cliente respons�vel pelo im�vel.
	 * 
	 * [UC0301] - Gerar Dados Di�rios da Arrecadacao
	 * 
	 * @author Pedro Alexandre
	 * @date 05/12/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarEsferaPoderClienteResponsavelPeloImovel(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * Atualiza a situ��o dos pagamentos informados.
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 12/12/2006
	 * 
	 * @param pagamentoSituacao
	 * @param colecaoPagamentos
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoPagamento(Integer pagamentoSituacao,
			Collection<Pagamento> colecaoPagamentos)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * para o Relat�rio
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Rafael Corr�a
	 * @date 12/12/06
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoLocalidadeAmbosRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal )
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o im�vel pelo id fazendo os carregamentos necess�rios
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corr�a
	 * @date 16/12/06
	 * 
	 * @return Imovel
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovelPagamento(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o cliente pelo id fazendo os carregamentos necess�rios
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corr�a
	 * @date 16/12/06
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClientePagamento(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o endere�o de correspond�ncia do cliente pelo seu id fazendo os carregamentos necess�rios
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corr�a
	 * @date 16/12/06
	 * 
	 * @return ClienteEndereco
	 * @throws ErroRepositorioException
	 */
	public ClienteEndereco pesquisarClienteEnderecoPagamento(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o telefone padr�o do cliente pelo seu id fazendo os carregamentos necess�rios
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corr�a
	 * @date 16/12/06
	 * 
	 * @return ClienteFone
	 * @throws ErroRepositorioException
	 */
	public IClienteFone pesquisarClienteFonePagamento(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa os clientes do im�vel pelo seu id do im�vel fazendo os carregamentos necess�rios
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corr�a
	 * @date 16/12/06
	 * 
	 * @return Collection<ClienteImovel>
	 * @throws ErroRepositorioException
	 */
	public Collection<ClienteImovel> pesquisarClientesImoveisPagamento(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Rafael Corr�a
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina, 
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

    

    /**
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Verifica se j� existe resumo da arrecada��o para o ano/m�s de refer�ncia
     * da arrecada��o
     * 
     * [FS0003] - Verificar a exist�ncia do resumo da arrecada��o
     * 
     * @author Pedro Alexandre
     * @date 16/05/2006
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarResumoArrecadacaoPorAnoMesArrecadacao(Integer anoMesReferenciaArrecadacao,Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * Pesquisa uma cole��o de ids das categorias cadastradas
     *
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * @author Pedro Alexandre
     * @date 15/12/2006
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCategorias() throws ErroRepositorioException ;
    
    /**
     * Pesquisa uma cole��o de ids dos lan�amentos de itens cont�beis cadastrados
     *
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * @author Pedro Alexandre
     * @date 15/12/2006
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarDadosLancamentosItemContabil() throws ErroRepositorioException ;

    /**
     * pesquisa a lista dos acertos da Arrecada��o/Devolucao do Aviso Bancario
     * 
     *[UC0268] - Apresentar An�lise do Aviso Banc�rio
     * 
     * @author Vivianne Sousa
     * @date 13/12/2006
     * 
     * @param idAvisoBancario
     * @return
     * @throws ErroRepositorioException
     */
     public Collection pesquisarAcertosAvisoBancario(Integer idAvisoBancario,
   		  Integer indicadorArrecadacaoDevolucao) 
     	throws ErroRepositorioException;
     
    /**
     * O sistema seleciona a lista de pagamentos associados ao aviso banc�rio 
     * a partir da tabela PAGAMENTO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
     * classificados por LOCA_ID ,IMOV_ID e PGMT_AMREFERENCIAPAGAMENTO
     * 
     * [UC0268] - Apresentar An�lise do Aviso Banc�rio
     * 
     * @author Vivianne Sousa
     * @date 15/12/2006
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarPagamentoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;
    
    /**
     * O sistema seleciona a lista de desvolu��es associados ao aviso banc�rio 
     * a partir da tabela DEVOLUCAO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
     * 
     * [UC0268] - Apresentar An�lise do Aviso Banc�rio
     * 
     * @author Vivianne Sousa
     * @date 15/12/2006
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarDevolucaoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;
    
    /**
     * Pesquisa os avisos dedu��es de um aviso banc�rio para o relat�rio atrav�s
     * do id do aviso banc�rio
     * 
     *[UC0268] - Apresentar An�lise do Aviso Banc�rio
     * 
     * @author Vivianne Sousa
     * @date 13/12/2006
     * 
     * @param idAvisoBancario
     * @return
     * @throws ErroRepositorioException
     */
     public Collection pesquisarDeducoesAvisoBancario(Integer idAvisoBancario) 
     	throws ErroRepositorioException;
     
     /**
      *[UC0268] - Apresentar An�lise do Aviso Banc�rio
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarValorAcertosAvisoBancario(Integer idAvisoBancario)
     	throws ErroRepositorioException ;
     
     /**
      * somatorio do valor das dedu��es existentes para o aviso bancario
      * 
      * [UC0268] - Apresentar An�lise do Aviso Banc�rio
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public BigDecimal pesquisarSomatorioDeducoesAvisoBancario(Integer idAvisoBancario) 
     	throws ErroRepositorioException ;
 	
     /**
      * [UC0268] - Apresentar An�lise do Aviso Banc�rio
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarAvisoBancario(Integer idAvisoBancario)
     	throws ErroRepositorioException;
     
     /**
      * Pesquisa alguns valores necessarios para 
      * obter a situa��o do aviso bancario, se aberto ou fechado
      * 
      * [UC0254] - Efetuar An�lise do Movimento dos Arrecadadores
      * 
      * @author Vivianne Sousa
      * @date 11/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarAvisoBancarioAvisoAcertos(Integer idAvisoBancario)
     	throws ErroRepositorioException;
     
     /**
      * Sequencial do tipo lan�amento igual a 1200
       * 
       * [UC0276] - Encerrar Arrecada��o do M�s
       * 
       * Pesquisa os daods de ContaImpostosDeduzidos dos pagamentos
       * classificados de contas para acumular o valor do imposto por
       * localidade e categoria e tipo de imposto.
       * 
       * @author Pedro ALexandre
       * @date 15/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idTipoImposto
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarContasImpostosDeduzidosPagamentosClassificadosContaPorTipoImposto(
    		  Integer idLocalidade, 
    		  Integer anoMesReferenciaArrecadacao,
    		  Integer idTipoImposto) throws ErroRepositorioException ;

      /**
       * <Breve descri��o sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idSituacaoAnterior
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAnterior(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idSituacaoAnterior) throws ErroRepositorioException ;

      /**
       * <Breve descri��o sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idDevolucaoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarDevolucoesNaoClassificadasMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descri��o sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idImpostoTipo
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarContasImpostosDeduzidosPagamentosContasEfetuadosEmMesesAnterioresClassificadosMesPorTipoImposto(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idImpostoTipo) throws ErroRepositorioException ;

      /**
       * <Breve descri��o sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idPagamentoSituacaoAnterior
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosComBaixaComandadaPorSituacaoAnterior(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idPagamentoSituacaoAnterior) throws ErroRepositorioException ;

      /**
       * <Breve descri��o sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idPagamentoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesEMesesAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idPagamentoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descri��o sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro ALexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idDevolucaoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarDevolucoesNaoClassificadasMesEAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descri��o sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 16/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idSituacaoAtual) throws ErroRepositorioException ;

      /**
       * Exclui os dados di�rios da arrecada��o do ano/m�s da arrecada��o corrente por localidade
       * 
       * [UC0301] Gerar Dados Di�rios da Arrecada��o
       * 
       * @author Pedro Alexandre
       * @date 11/04/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @throws ErroRepositorioException
       */
      public void excluirDadosDiariosArrecadacaoPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

      /**
       * [UC0263] Filtrar Movimento dos Arrecadadores
       * 
       * @author Vivianne Sousa
       * @date 30/11/06
       * 
       * @param codigoBanco
       * @param codigoRemessa
       * @param descricaoIdentificacaoServico
       * @param numeroSequencialArquivo
       * @param dataGeracaoInicio
       * @param dataGeracaoFim
       * @param ultimaAlteracaoInicio
       * @param ultimaAlteracaoFim
       * @param descricaoOcorrencia
       * @param indicadorAceitacao
       * @param indicadorAbertoFechado
       * @return
       * @throws ErroRepositorioException
       */
      public Collection filtrarMovimentoArrecadadorParaRelatorio(String codigoBanco, String codigoRemessa, 
    		  String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio, 
    		  Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia,
    		  String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
      
      /**
       * [UC0263] Filtrar Movimento dos Arrecadadores
       * 
       * @author Vivianne Sousa
       * @date  04/01/07
       * 
       * @param codigoBanco
       * @param codigoRemessa
       * @param descricaoIdentificacaoServico
       * @param numeroSequencialArquivo
       * @param dataGeracaoInicio
       * @param dataGeracaoFim
       * @param ultimaAlteracaoInicio
       * @param ultimaAlteracaoFim
       * @param descricaoOcorrencia
       * @param indicadorAceitacao
       * @param indicadorAbertoFechado
       * 
       * @throws ControladorException
       */
      public Integer filtrarMovimentoArrecadadoresRelatorioCount(String codigoBanco, String codigoRemessa, 
      		String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio, 
      		Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, 
      		String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
      
      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Pesquisa a cole��o de guias de pagamento categoria 
       * para o id da guia informada.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idGuiaPagamento
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarGuiaPagamentoCategoria(Integer idGuiaPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Pesquisa a cole��o de cliente de guias de pagamento  
       * para o id da guia informada.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idGuiaPagamento
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Para cada guia de pagamento transferida para o hist�rico 
       * atualiza o indicador de que a guia de pagamento est� no hist�rico.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idsGuiasPagamento
       * @throws ErroRepositorioException
       */
      public void atualizarIndicadorGuiaPagamentoNoHistorico(Collection idsGuiasPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Atualiza o ano/m�s de refer�ncia da arrecada��o.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesArrecadacaoAtual
       * @param anoMesArrecadacaoNovo
       * @throws ErroRepositorioException
       */
      public void atualizarAnoMesArrecadacao(int anoMesArrecadacaoAtual, int anoMesArrecadacaoNovo) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Pesquisa as contas correspondentes aos pagamentos classificados de conta 
       * e os pagamentos anteriores de conta classificados no m�s.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */

      public Collection pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros, Integer idSetorComercial) throws ErroRepositorioException ;


      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Pesquisa as guias de pagamento correspondentes aos pagamentos classificados de guia de 
       * pagamento e aos pagamentos anteriores de guia de pagamento classificados no m�s.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<GuiaPagamento> pesquisarGuiasPagamentoDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Pesquisar os pagamentos classificados ou com valor excedente baixado e com 
       * valor excedente maior do que zero.
       *
       * @author Pedro Alexandre
       * @date 10/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<Integer> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, int numeroIndice, int quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecada��o do M�s
       *
       * Pesquisa as devolu��es classificadas para transferir para o hist�rico.
       *
       * @author Pedro Alexandre
       * @date 10/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * O sistema seleciona a lista de pagamentos associados ao aviso banc�rio 
       * a partir da tabela PAGAMENTO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
       * 
       * @author Vivianne Sousa
       * @date 17/01/2007
       * 
       * @return Collection
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentoPorAvisoBancario(Integer idAvisoBancario) 
      	throws ErroRepositorioException;
      
  	/**
  	 * @author Ana Maria
  	 * @date 29/01/2007
  	 * 
  	 * @param idGuiaPagamento
  	 * 
  	 * @return Collection
  	 * @throws ErroRepositorioException
  	 */
  	public Collection pesquisarGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;      
  	
  	
  	/**
	 * Atualiza logradouroBairro de um ou mais im�veis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
	/**
	 * Atualiza logradouroCep de um ou mais im�veis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
	/**
     * Pesquisa o cliente da guia de pagamento 
     * atrav�s do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 28/02/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarClienteDeGuiaPagamento(Integer idGuiaPagamento)
    	throws ErroRepositorioException ;
    
	 /**
     * Pesquisa o cliente da guia de pagamento 
     * atrav�s do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 06/03/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarImovelDeClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;
    
    /**
     * Pesquisa o cliente da guia de pagamento 
     * atrav�s do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 28/02/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarClienteDeClienteImovel(Integer idGuiaPagamento) 
    	throws ErroRepositorioException;
    
    /**
     * Este caso de uso cria um filtro que ser� usado na pesquisa de pagamentos
     * para o Relat�rio
     * 
     * [UC0255] Filtrar Pagamentos
     * 
     * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
     * pesquisarPagamentoLocalidade
     * 
     * @author Rafael Corr�a
     * @date 12/12/06
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarPagamentoImovelAmbosRelatorio(String idImovel)
            throws ErroRepositorioException;
    
    
    /**
     * [UC0213] Desfazer Parcelamento Debito -
     *  remover guia pagamento referente ao parcelamento
     * 
     * remove a guia de pagamento do Pagamento
     * 
     * @author Vivianne Sousa
     * @date 06/03/2007
     * 
     * @param 
     * @return void
     */
    public void removerGuiaPagamentoPagamento(Integer idPagamento) throws ErroRepositorioException ;

    /**
     * [UC0301] Gerar Dados Di�rios da Arrecada��o
     *
     * Pesquisa os ano/m�s de refer�ncia dos pagamentos para ano/m�s 
     * de refer�ncia maior ou igual ao ano/m�s de refer�ncia atual da arrecada��o
     *
     * @author Pedro Alexandre 
     * @date 07/03/2007
     *
     * @param anoMesArrecadacaoAtual
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarAnoMesArrecadacaoPagamentoMaiorIgualAnoMesArrecadacaoAtual(Integer anoMesArrecadacaoAtual, Integer idLocalidade) throws ErroRepositorioException ;


    /**
     * [UC0113] - Faturar Grupo de Faturamento
     *
     * @author Raphael Rossiter 
     * @date 20/03/2007
     *
     * @param idDebitoACobrar
     * @return Integer 
     * @throws ErroRepositorioException
     */
    public Integer pesquisarQuantidadePagamentosPorDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devolu��es
     * 
     * Atualiza a situacao atual dos pagamentos (PGST_IDATUAL) (tabela
     * PAGAMENTO_SITUACAO)
     *
     * @author Pedro Alexandre
     * @date 23/03/2007
     *
     * @param colecaoIdsPagamentos
     * @throws ErroRepositorioException
     */
    public void atualizarSituacaoPagamentoClassificado(Collection<Integer> colecaoIdsPagamentos) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devolu��es
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situa��o atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor n�o confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id da conta nos pagamentos (seta CNTA_ID da tabela PAGAMENTO
     * para CNTA_ID da tabela CONTA)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereConta(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devolu��es
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situa��o atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor n�o confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id da guia de pagamento nos pagamentos (seta GPAG_ID da tabela PAGAMENTO
     * para GPAG_ID da tabela GUIA PAGAMENTO)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereGuiaPagamento(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devolu��es
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situa��o atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor n�o confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id do d�bito a cobrar nos pagamentos (seta DBAC_ID da tabela PAGAMENTO
     * para DBAC_ID da tabela DEBITO A COBRAR)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereDebitoACobrar(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] - Classificar Pagamentos e Devolu��es
     *
     * <Breve descri��o sobre o subfluxo>
     *
     * [SB0008] - Processar Pagamento a Maior ou a Menor	
     *
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param colecaoPagamentos
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereIdentificadorDocumentoIgualANulo(Collection colecaoPagamentos) throws ErroRepositorioException ;

    /**
     * Remove o id da guia de pagamento dos pagamentos referentes a conta
     * para poder mandar a guia de pagamento para o hist�rico.
     *
     * [UC0000] Gerar Hist�rco para encerrar Faturamento
     *
     * @author Pedro Alexandre
     * @date 01/04/2007
     *
     * @param idConta
     * @return
     * @throws ErroRepositorioException
     */
    public void apagarIdGuiaPagamentoPagamentos(Integer idGuiaPagamento) throws ErroRepositorioException ;
    
    /**
	 * Pesquisa os movimentos dos arrecadores para a gera��o do relat�rio
	 * 
	 * [UCXXXX] Acompanhar Movimento dos Arrecadadores
	 * 
	 * @author Rafael Corr�a
	 * @date 02/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoArrecadadoresRelatorio(
			Integer mesAnoReferencia, Integer idArrecadador,
			Integer idFormaArrecadacao, Date dataPagamentoInicial,
			Date dataPagamentoFinal) throws ErroRepositorioException;


	/**
     * Sequencial do tipo lan�amento igual a 750
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor do d�bitos cobrados por localidade, categoria dos
     * pagamentos classificados de conta para tipo de financiamento igual a
     * doa��es
     * 
     * @author Pedro Alexandre
     * @date 03/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    
    /**
     * Sequencial do tipo lan�amento igual a 4150
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor dos d�bitos cobrados por localidade, categoria e item
     * cont�bil para os pagamentos de contas efetuados em meses anteriores
     * classificados no m�s para tipo de financiamento igual doa��es.
     * 
     * @author Pedro Alexandre
     * @date 03/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * Pesquisa as guias de pagamento correspondentes aos pagamentos classificados de guia de 
     * pagamento e aos pagamentos anteriores de guia de pagamento classificados no m�s.
     *
     * @author Pedro Alexandre
     * @date 09/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<GuiaPagamento> pesquisarGuiasPagamentoDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * Pesquisar os pagamentos classificados ou com valor excedente baixado e com 
     * valor excedente maior do que zero para transferir para o hist�rico.
     *
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Pagamento> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lan�amento igual a 4000
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor dos d�bitos cobrados por localidade, categoria e item
     * cont�bil para os pagamentos de contas efetuados em meses anteriores
     * classificados no m�s para tipo de financiamento igual a parcelamento de
     * servi�o e grupo de parcelamento diferente de juros cobrados.
     * 
     * @author Pedro Alexandre
     * @date 23/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * Pesquisa as devolu��es classificadas para transferir para o hist�rico.
     *
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * Pesquisa as contas correspondentes aos pagamentos classificados de conta 
     * e os pagamentos anteriores de conta classificados no m�s.
     *
     * @author Pedro Alexandre
     * @date 09/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Conta> pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;


    /**
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * Pesquisa a conta  
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param idConta
     * @return
     * @throws ErroRepositorioException
     */
    public Conta pesquisarConta(Integer idConta) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecada��o do M�s
     *
     * Pesquisar o pagamento
     *
     * @author Pedro Alexandre
     * @date 10/04/2007
     *
     * @param idPagamento
     * @return
     * @throws ErroRepositorioException
     */
    public Pagamento pesquisarPagamento(Integer idPagamento) throws ErroRepositorioException ;

	public Collection<Integer> pesquisarIdsSetoresComPagamentosOuDevolucoes() throws ErroRepositorioException;

    public Integer pesquisarIdLocalidadePorSetorComercial(Integer idSetorComercial) throws ErroRepositorioException ;
    
    public Collection<DebitoACobrar> pesquisarDebitosACobrarDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lan�amento igual a 4400, 4410, 4420, 4430
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor dos cr�ditos realizados por localidade e categoria, para
     * os pagamentos de contas efetuados em meses anteriores classificados no
     * m�s, para origem do cr�dito igual a descontos concedidos.
     * 
     * @author Pedro Alexandre
     * @date 23/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idOrigemCredito
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorOrigemCredito(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idOrigemCredito) throws ErroRepositorioException ;

    
    /**
     * Sequencial do tipo lan�amento igual a 400 e 500
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor do d�bitos cobrados por localidade, categoria dos
     * pagamentos classificados de conta para tipo de financiamento igual a
     * parcelamento de �gua ou parcelamento de esgoto.
     * 
     * @author Pedro Alexandre
     * @date 18/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idFinanciamentoTipo
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idFinanciamentoTipo) throws ErroRepositorioException ;

    
    /**
     * Sequencial do tipo lan�amento igual a 3800
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor dos d�bitos cobrados por localidade e categoria para os
     * pagamentos de contas efetuados em meses anteriores classificados no m�s
     * para tipo de financiamento igual a parcelamento de �gua.
     * 
     * @author Pedro Alexandre
     * @date 18/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idFinanciamentoTipo
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idFinanciamentoTipo) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lan�amento igual a 300
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor do d�bitos cobrados por localidade, categoria e item
     * cont�bil dos pagamentos classificados de conta para tipo de financiamento
     * igual a servi�o
     * 
     * @author Pedro Alexandre
     * @date 22/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idLancamentoItemContabil
     * @param idCategoria
     * @param colecaoIdsFinanciamentoTipo
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria, Collection<Integer> colecaoIdsFinanciamentoTipo) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lan�amento igual a 3700
     * 
     * [UC0276] - Encerrar Arrecada��o do M�s
     * 
     * Acumula o valor dos d�bitos cobrados por localidade, categoria e item
     * cont�bil para os pagamentos de contas efetuados em meses anteriores
     * classificados no m�s para tipo de financiamento igual a servi�o.
     * 
     * @author Pedro Alexandre
     * @date 23/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idLancamentoItemContabil
     * @param idCategoria
     * @param idsFinanciamentoTipos
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria, Collection<Integer> idsFinanciamentoTipos) throws ErroRepositorioException ;
    
    /**
     * [UC0242] Registrar Movimento Arrecadadores
     * 
     * Atualiza o arrecadador contrato
     * 
     * 
     * @author S�vio Luiz,Vivianne Sousa
     * @date 19/04/2007,28/11/2007
     * 
     * @return Cole��o de Bancos
     * @throws ErroRepositorioException
     */

    public void atualizarDadosArrecadadorContrato(
            ArrecadadorContrato arrecadadorContrato,
            boolean flagEnvioDebitoAutomatico, boolean flagRetornoCodigoBarras,
            boolean flagRetornoDebitoAutomatico, boolean flagRetornoFichaCompensacao)
            throws ErroRepositorioException;

    
    /**
     * Pesquisar os ano/m�s de refer�ncia do pagamentos para um im�vel e ano/m�s
     * de arrecada��o informados para o tipo de documento informado.
     * 
     * [UC0300] Classificar Pagamentos e Devolu��es
     * 
     * @author Pedro Alexandre
     * @date 06/12/2006
     * 
     * @param anoMesArrecadacaoAtual
     * @param idImovel
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarAnoMesReferenciaPagamentoParaImovel(Integer anoMesArrecadacaoAtual, Integer idImovel) throws ErroRepositorioException ;
    
    /**
     * Seleciona os pagamentos hist�rios de um aviso
     * 
     * @author Rafael Corr�a
     * @date 23/04/2007
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    @SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;

	public Pagamento pesquisarPagamentoDeConta(Integer idConta) throws ErroRepositorioException ;
	
	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Vivianne Sousa
	 * @data 23/04/2006
	 * 
	 * @param idPagamento
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarContaEmPagamento(Integer idPagamento, Integer idConta) throws ErroRepositorioException;
	
	public Integer pesquisarIdPagamentoDaGuia(Integer idGuiaPagamento) throws ErroRepositorioException;
	
	public Integer pesquisarIdPagamentoDoDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;
	
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorUnidadeNegocio(int anoMesReferencia) throws ErroRepositorioException;
	
	public Collection consultarResumoArrecadacaoRelatorioPorUnidadeNegocio(int anoMesReferencia, Integer localidade) throws ErroRepositorioException;
	
	public PagamentosDevolucoesHelper filtrarPagamentos(FiltroPagamento filtroPagamento) throws ErroRepositorioException;
	
	public PagamentosDevolucoesHelper filtrarDevolucoes(FiltroDevolucao filtroDevolucao) throws ErroRepositorioException;	
	
	/**
	 * Pesquisar valores de arrecada��o e devolu��o do aviso banc�rio
	 * 
	 * @author Ana Maria
	 * @date 14/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ValoresArrecadacaoDevolucaoAvisoBancarioHelper pesquisarValoresAvisoBancario(
			Integer idAvisoBancario) throws ErroRepositorioException;	
	
	/**
	 * Atualizar Pagamentos
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarAvisoBancarioPagamentos(Collection<Integer> idsPagamentos, Integer idAvisoBancarioD)
			throws ErroRepositorioException;
	
	/**
	 * Atualizar valor de arrecada��o calculado 
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarValorArrecadacaoAvisoBancario(String valorArrecadacaoInformado, String valorArrecadacaoCalculado, 
			Integer idAvisoBancario)throws ErroRepositorioException;
	
	/**
	 * Atualizar Devolu��es
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarAvisoBancarioDevolucoes(Collection<Integer> idsDevolucoes, Integer idAvisoBancarioD)
			throws ErroRepositorioException;
	
	 /**
	 * Atualizar valor de devolu��o calculado 
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarValorDevolucaoAvisoBancario(String valorDevolucaoInformado, String valorDevolucaoCalculado, 
			Integer idAvisoBancario)throws ErroRepositorioException;	
	
	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores - Relat�rio
	 * 
	 * @author Ana Maria
	 * @date 13/07/2007
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> filtrarIdsMovimentoArrecadador(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0619] Gerar Rela��o de Acompanhamento dos Movimentos Arrecadadores por NSA
	 * 
	 * @author Ana Maria
	 * @date 12/07/2007
	 * 
	 * @param idMovimentoArrecadador
	 * @return
	 */
	public Collection<MovimentoArrecadadoresPorNSAHelper> gerarMovimentoArrecadadoresNSA
			(Collection<Integer> idsArrecadadorMovimento, Integer codigoFormaArrecadacao)throws ErroRepositorioException;
	
	
	/**
	 * Processamento R�pido
	 * 
	 * @author Raphael Rossiter
	 * @date 17/08/2007
	 * 
	 * @return Collection<Conta>
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> pesquisarContaComPagamentoHistorico()
			throws ErroRepositorioException ;
	
	/**
	 * Pesquisa a agencia
	 * 
	 * @author S�vio Luiz
	 * @date 05/11/2007
	 * 
	 * @return Agencia
	 * @throws ErroRepositorioException
	 */
	public Agencia pesquisarAgencia(String codigoAgencia,
			Integer idBanco) throws ErroRepositorioException;
	
	/**
	 * [UC0626] Gerar Resumo de Metas Acumulado no M�s (CAERN)
	 * 
	 * @author S�vio Luiz
	 * @data 28/11/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarPagamentoDeContas(Collection colecaoConta)
			throws ErroRepositorioException;
	
	/**
	 * [UC0739] Informar Situa��o de Expurgo do Pagamento
	 * 
	 * @author S�vio Luiz
	 * @data 02/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarDadosPagamentoExpurgado(String dataPagamento,Integer idCliente,Integer anoMesArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0739] Informar Situa��o de Expurgo do Pagamento
	 * 
	 * @author S�vio Luiz
	 * @data 02/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarDadosPagamentoHistoricoExpurgado(String dataPagamento,Integer idCliente,Integer anoMesArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0739] Informar Situa��o de Expurgadodo Pagamento
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 04/01/2008
	 * 
	 * @param idsPagamentos
	 * @return void
	 */
	public void atualizarSituacaoExpurgado(Collection colecaoPagamento)
			throws ErroRepositorioException;

	
	/**
	 * Pesquisa a ContaHistorico para o im�vel no ano/m�s de refer�ncia informados
	 *
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 *
	 * @author Pedro Alexandre
	 * @date 15/01/2008
	 *
	 * @param imovel
	 * @param anoMesReferenciaPagamento
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] selecionarContaHistoricoPorImovelAnoMesReferencia(Imovel imovel,Integer anoMesReferenciaPagamento, Integer anoMesFaturamento) throws ErroRepositorioException ;


	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecada��o e Expurgo
	 * 
	 * @author S�vio Luiz
	 * @data 10/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarDadosComparativosFaturamentoArrecadacaoExpurgo(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ErroRepositorioException;
	
	/**
	 * [UCXXX] Pesquisa uma Guia de Arrecada��o
	 * 
	 * @author Roberto Barbalho
	 * @data 25/01/2008
	 * 
	 * @param guiaDevolucaoId
	 * @return GuiaDevolucao
	 */
	public GuiaDevolucao pesquisarGuiaDevolucao(Integer guiaDevolucaoId) throws ErroRepositorioException;
    
    /**
     * [UC0242] Registrar Movimento dos Arrecadadores
     * 
     * Atualiza o valor total do movimento (armv_vltotalmovimento) (tabela
     * ARRECADADOR_MOVIMENTO)
     * 
     * @author Vivianne Sousa
     * @date 31/01/2008
     * 
     * @param idArrecadadorMovimento
     * @param valorTotalMovimento
     * @return void
     */
    public void atualizarValorMovimentoArrecadadorMovimento(
            Integer idArrecadadorMovimento, BigDecimal valorTotalMovimento) throws ErroRepositorioException;
    
    
    /**
     * [UC0737] Atualiza Quantidade de Parcela Paga Consecutiva e Parcela B�nus
     * 
     * @author Vivianne Sousa
     * @data 01/02/2008
     * 
     * @param idConta
     * @return dataPagamento
     */
    public Date pesquisarDataPagamentoDeConta(Integer idConta)
            throws ErroRepositorioException;
    
    /**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecada��o e Expurgo
	 * 
	 * @author S�vio Luiz
	 * @data 17/02/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarPagamentoExpurgado(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ErroRepositorioException;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecada��o e Expurgo
	 * 
	 * @author S�vio Luiz
	 * @data 17/02/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarPagamentoHistoricoExpurgado(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ErroRepositorioException;

	
	/**
	 * Sequencial do tipo lan�amento igual a 100
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor de �gua e esgoto por categoria 
	 * e localidade paa os pagamentos classificados de conta
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] acumularValorAguaEsgotoPagamentosClassificadosConta(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException ;

	/**
	 * Sequencial do tipo lan�amento igual a 3500
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Acumula o valor de �gua por localidade e categoria para os pagamentos de
	 * contas efetuados em meses anteriores classificados no m�s.
	 * 
	 * @author Pedro Alexandre, Pedro Alexandre
	 * @date 23/05/2006, 23/05/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] acumularValorAguaEsgotoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException ;

	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - GUIA DE PAGAMENTO
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param imovel
	 * @param numeroGuia
	 * @param anoGuia
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Integer[] pesquisarExistenciaGuiaPagamento(Imovel imovel,
			Integer numeroGuia, Integer anoGuia) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE D�BITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param imovel
	 * @param lotePagamento
	 * @param anoGuia
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Integer[] pesquisarExistenciaGuiaPagamentoPorLotePagamento(Imovel imovel,
			Integer lotePagamento, Integer anoGuia) throws ErroRepositorioException ;

 	/**
 	 * [UC0301] Gerar Dados Diarios da Arrecadacao
 	 * Consulta das tarifas dos contratos do arrecadador
 	 * 
 	 * @author Francisco do Nascimento
 	 * @date 18/07/2008
 	 * 
 	 */
 	public ArrecadadorContratoTarifa pesquisarArrecadadorContratoTarifa(Integer idArrecadador,
 			Integer idFormaArrecadacao) throws ErroRepositorioException;
	/**
	 * Exclui resumo arrecada��o do ano/m�s da arrecada��o corrente
	 * por localidade
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Vivianne Sousa
	 * @date 11/08/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoArrecadacaoPorAnoMesArrecadacaoPorLocalidade(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0818] Gerar Historico do Encerramento da Arrecada��o
	 * 
	 * Pesquisa os creditos a realizar correspondentes as devolu��es classificadas
	 * 
	 * @author Vivianne Sousa
	 * @date 26/08/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CreditoARealizar> pesquisarCreditoaRealizarDeDevolucoesClassificadas(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	

	/**
	 * [UC0826] Gerar Relat�rio An�lise da Arreca��o
	 * 
	 * @see RepositorioArrecadacaoHBM#pesquisarAnaliseArrecadacao(PesquisarAnaliseArrecadacaoHelper)
	 * 
	 * @author Victor Cisneiros
	 * @date 24/07/2008
	 */
    public List<RelatorioAnaliseArrecadacaoBean> pesquisarAnaliseArrecadacao(
    		PesquisarAnaliseArrecadacaoHelper helper) throws ErroRepositorioException;
    
	/**
	 * [UC0827] Gerar Relat�rio An�lise dos Avisos Bancarios
	 * 
	 * @see RepositorioArrecadacaoHBM#pesquisarAnaliseAvisosBancarios(PesquisarAnaliseAvisosBancariosHelper)
	 * 
	 * @author Victor Cisneiros
	 * @date 30/07/2008
	 */
    public List<RelatorioAnaliseAvisosBancariosBean> pesquisarAnaliseAvisosBancarios(
    		PesquisarAnaliseAvisosBancariosHelper helper) throws ErroRepositorioException;
    
    /**
     * [UC0829] Gerar Relat�rio Avisos Bancarios Por Conta Corrente
     * 
     * @see RepositorioArrecadacaoHBM#pesquisarAvisoBancarioPorContaCorrente(PesquisarAvisoBancarioPorContaCorrenteHelper)
     * 
     * @author Victor Cisneiros
     * @date 21/08/2008
     */
    public List<RelatorioAvisoBancarioPorContaCorrenteBean> pesquisarAvisoBancarioPorContaCorrente(
    		PesquisarAvisoBancarioPorContaCorrenteHelper helper) throws ErroRepositorioException;
    
    /**
     * [UC0829] Gerar Relat�rio Avisos Bancarios Por Conta Corrente
     * 
     * @see RepositorioArrecadacaoHBM#pesquisarPagamentosDosAvisos(PesquisarAvisoBancarioPorContaCorrenteHelper, Collection)
     * 
     * @author Victor Cisneiros
     * @date 21/08/2008
     */
    public List<Object[]> pesquisarPagamentosDosAvisos(
    		PesquisarAvisoBancarioPorContaCorrenteHelper helper, Collection<Integer> idsAvisos) throws ErroRepositorioException;
	
    /**
     * [UC0828] Atualizar Diferen�a Acumulada no M�s
     * 
     * @see RepositorioArrecadacaoHBM#pesquisarDiferencaAcumuladaNoMes(int)
     * 
     * @author Victor Cisneiros
     * @date 01/09/2008
     */
    public List<Object[]> pesquisarDiferencaAcumuladaNoMes(int anoMesArrecadacao, int anoMesArrecadacaoAnterior) throws ErroRepositorioException;
    
    /**
     * [UC0828] Atualizar Diferen�a Acumulada no M�s
     * 
     * @author Victor Cisneiros
     * @date 01/09/2008
     */
    public void removerDiferencasAcumuladasNoMes(int anoMesArrecadacao) throws ErroRepositorioException;

	/**
	 * Exclui os dados di�rios da devolucao do ano/m�s da arrecada��o corrente
	 * por localidade
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @author Francisco do Nascimento
	 * @date 22/10/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosDiariosDevolucaoPorAnoMesArrecadacaoPorLocalidade(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;    
    
    /**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecada��o e Expurgo
	 * 
	 * @author S�vio Luiz
	 * @data 17/02/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection<Pagamento> pesquisarPagamentoPorLocalidade(Integer idLocalidade,Integer anoMesReferencia)
			throws ErroRepositorioException;
	

    
    /**
     * 
     * [UC0818] - Gerar Hist�rico do Encerramento da Arrecada��o
     *
     * Fluxo Principal
     *
     * [FS0001] - Verifica existencia resumo arrecada��o   
     *
     * @author bruno
     * @date 24/10/2008
     *
     * @param anoMesReferencia
     * @return
     */
    public Integer verificarExistenciaResumoArrecadacaoParaAnoMes( Integer anoMesReferencia ) throws ErroRepositorioException;
       
	/**
	 * [UC0333] Consultar Dados Di�rios da Arrecada��o
	 * 
	 * M�todo para filtrar os dados di�rios para qualquer aba da funcionalidade
	 * 
	 * @author Francisco do Nascimento
	 * @date 12/11/2008
	 *
	 * @param filtro
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacao(FiltroConsultarDadosDiariosArrecadacao filtro)
	 throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0333] Consultar Dados Di�rios da Arrecada��o
	 *
	 * Verificar se existe dados diarios da arrecadacao de acordo com o filtro 
	 * passado
	 *
	 * @author Francisco do Nascimento
	 * @date 18/11/2008
	 *
	 * @param filtro
	 * @return boolean de existencia dos dados
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaDadosDiariosArrecadacao(FiltroConsultarDadosDiariosArrecadacao filtro)
		throws ErroRepositorioException;
	
	/**
	 * Atualiza o valor excedente, a situa��o anterior para NULO e a situa��o atual dos pagamentos informados para o
	 * tipo de situa��o informada.
	 * 
	 * @author Raphael Rossiter
	 * @date 27/11/2008
	 * 
	 * @param colecaoPagamento
	 * @param pagamentoSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoAnteriorAtualEValorExcedentePagamento(
			Collection<Pagamento> colecaoPagamento, Integer pagamentoSituacao)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado e
	 * o ano/m�s de refer�ncia cont�bil da guia de pagamento seja 
	 * menor ao ano/m�s de refer�ncia do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformadaRefContabilMenorRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException;



	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado e
	 * o ano/m�s de refer�ncia cont�bil da guia de pagamento seja 
	 * maior ou igual ao ano/m�s de refer�ncia do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformadaRefContabilMaiorIgualRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID informado e 
	 * o ano/m�s de refer�ncia cont�bil do d�bito a cobrar seja 
	 * maior ou igual ao ano/m�s de refer�ncia do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @param referenciafaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformadoRefContabilMaiorIgualRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
	 * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID informado e 
	 * o ano/m�s de refer�ncia cont�bil do d�bito a cobrar seja 
	 * menor q o ano/m�s de refer�ncia do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @param referenciafaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformadoRefContabilMenorRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException;
	
	/**
	 * Sequencial do tipo lan�amento igual a 2440
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es do tipo descontos por pagamento a vista
	 * 
	 * @author Francisco do Nascimento
	 * @date 03/12/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesDescontosPagamentoAVista(
			Integer anoMesReferenciaArrecadacao, 
			Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lan�amento igual a 2470
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisa as devolu��es do tipo descontos por credito
	 * 
	 * @author Francisco do Nascimento
	 * @date 04/12/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesDescontosCreditos(
			Integer anoMesReferenciaArrecadacao, 
			Integer idLocalidade)
			throws ErroRepositorioException;	
	
	/**
	 * Pesquisa os bancos q tem imoveis cadastrados em debito automatico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBancoDebitoAutomatico()
		throws ErroRepositorioException;
	
	/**
	  * [UC0146] Manter Conta
	  * 
	  * FS0028 - Verificar par�metro consulta e d�bito autom�tico
	  * @return
	  * @throws ErroRepositorioException
	  */
	public Collection pesquisarImoveisBancoDebitoAutomatico(String[] bancos)
			throws ErroRepositorioException;
	
	public Integer countImoveisBancoDebitoAutomatico(String[] bancos, 
			Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, String indicadorContaPaga, Integer somenteDebitoAutomatico)
		throws ErroRepositorioException;
	
	public Collection selecionarImoveisBancoDebitoAutomatico(String[] bancos, 
			Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, String indicadorContaPaga)
		throws ErroRepositorioException;
	
	 /**
	 * Consultar os dados do movimento arrecadador 
	 * 
	 * @author Arthur Carvalho
	 * @date 03/04/2009
	 */
    public Collection<Object[]>  consultarNomeArrecadadorNomeAgencia(
			String idArrecadadorMovimento ) throws ErroRepositorioException ;
    
	/**
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2009
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentosMesEMesesAnterioresCampanhaSolidariedadeCrianca(
			Integer anoMesReferenciaArrecadacao, 
			Integer idLocalidade, Integer idRD) throws ErroRepositorioException;
	
	/**
	 * Sequencial do tipo lan�amento igual a 2450
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * Pesquisa as devolu��es do tipo descontos por pagamento a vista da campanha de solidariedade a crian�a
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2009
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesDescontosPagamentoAVistaCampanhaCrianca(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idRDComPercentualDoacao)
			throws ErroRepositorioException;
    
    /**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - CONTA
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarExistenciaContaPorNumeroFatura(String numeroFatura) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - DOCUMENTO DE COBRAN�A
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param numeroDocumentoFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsCobrancaDocumentoPorNumeroDocumentoFatura(String numeroDocumentoFatura) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - DOCUMENTO DE COBRAN�A
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2009
	 *
	 * @param idCobrancaDocumento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException ;
    
    /**
     * [UC0300] Classificar Pagamentos e Devolu��es
     * 
     * O sistema seleciona os pagamentos com ano/m�s de refer�ncia da
     * arrecada��o igual ou menor que o ano/m�s de refer�ncia da arrecada��o
     * corrente (seleciona a partir da tabela PAGAMENTO para
     * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
     * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
     * debito a cobrar e o campo DBAC_ID informado e 
     * o ano/m�s de refer�ncia cont�bil do d�bito a cobrar seja 
     * maior ou igual ao ano/m�s de refer�ncia do faturamento 
     * 
     * @author Bruno Barros
     * @data 16/06/2009
     * 
     * @param anoMesReferenciaFaturamento
     * @param idLocalidade
     * @param referenciafaturamento
     * @return Collection<Object[]>
     */
    public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarHistoricoComDebitoInformadoRefContabil(
            Integer anoMesReferencia, Integer idLocalidade )
            throws ErroRepositorioException;   
    
    /**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - GUIA DE PAGAMENTO
	 *
	 * @author Raphael Rossiter
	 * @date 29/06/2009
	 *
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(String numeroGuiaFatura) throws ErroRepositorioException ;

	/**
	 * [UC0264] - Distribuir Dados do C�digo de Barras
	 * 
	 * [SB0008] - Distribuir Pagamento Legado COSANPA
	 * 
	 * @author Raphael Rossiter
	 * @created 27/07/2009
	 * 
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarFaturaPorNumeroFatura(String numeroFatura) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * [SB0016] - Processar Pagamento Antecipado de Conta
	 *
	 * @author Raphael Rossiter
	 * @date 13/10/2009
	 *
	 * @param idGuiaPagamento
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaParaPagamentoParcial(Integer idGuiaPagamento) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0264] - Distribuir Dados do C�digo de Barras
	 * 
	 * [SB0008] - Distribuir Pagamento Legado COSANPA
	 * 
	 * @author Raphael Rossiter
	 * @created 27/07/2009
	 * 
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Fatura pesquisarFaturaPorNumeroFaturaObjetpCompleto(String numeroFatura)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecada��o e Expurgo
	 * 
	 * @author R�mulo Aur�lio
	 * @data 26/10/2008
	 * Pesquisa Quantidadede contas faturas para localidade/ refer�ncia
	*/
	public Object[] pesquisarQuantidadeContasFaturadas(Integer idLocalidade,
			Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecada��o e Expurgo
	 * 
	 * @author R�mulo Aur�lio
	 * @data 26/10/2008
	 * Quantidade/valor de documentos pagoa para unidade de neg�cio / refer�ncia
	 */ 
	public Object[] pesquisarQuantidadeDocumentosPagos(Integer idLocalidade,
			Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/09
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;
	
	/**
	 * Pesquisa Guia pelo Id
	 * 
	 * @author Hugo Amorim
	 * @date 11/01/2010
	 * 
	 * @param idGuia
	 * @return idGuia
	 * @exception ErroRepositorioException
	 *                
	 */
	public Integer pesquisarExistenciaGuiaPagamento(Integer idGuia) throws ErroRepositorioException;
	
	/**
	 * [UC0978] Pesquisa Relat�rio de Pagamento para Entidades Beneficentes Analitico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesAnalitico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, 
			String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao) throws ErroRepositorioException;
	
	
	/**
	 * [UC0978] Pesquisa Relat�rio de Pagamento para Entidades Beneficentes Sintetico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesSintetico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;
	/**
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * Pesquisar o pagamento
	 * 
	 * @author Ivan Sergio
	 * @date 26/03/2010
	 * 
	 * @param idPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Pagamento pesquisarPagamentoParaEncerrarArrecadacao(Integer idPagamento)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0978] Count para Relat�rio de Pagamento para Entidades Beneficentes Analitico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 *         opcaoTotalizacao
	 * @exception ErroRepositorioException
	 */ 
	public int pesquisarPagamentoEntidadesBeneficentesAnaliticoCount(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;
	
	/**
	 * [UC0978] Count para Relat�rio de Pagamento para Entidades Beneficentes Sintetico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 *         opcaoTotalizacao
	 * @exception ErroRepositorioException
	 */ 
	public int pesquisarPagamentoEntidadesBeneficentesSinteticoCount(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;	
	
	
	/**
	 * [UC0927] � Confirmar Cart�o de Cr�dito/D�bito
	 *
	 * @author Raphael Rossiter
	 * @date 03/02/2010
	 *
	 * @param idArrecadador
	 * @param numeroNsa
	 * @param codigoOpcaoExtrato
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroNsaPorArrecadador(Integer idArrecadador, Integer numeroNsa,
			String codigoOpcaoExtrato) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com c�digo de Barras
	 * 
	 * [SB0019] � Gerar D�bitos/Cr�ditos Parcelas Antecipadas 
	 *
	 * @author Raphael Rossiter
	 * @date 13/04/2010
	 *
	 * @param idDebitoACobrar
	 * @param numeroPrestacoesCobradas
	 * @param numeroPrestacoesAntecipadas
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroPrestacoesAntecipadasECobradas(Integer idDebitoACobrar, Integer numeroPrestacoesCobradas,
			Integer numeroPrestacoesAntecipadas) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com c�digo de Barras
	 * 
	 * [SB0019] � Gerar D�bitos/Cr�ditos Parcelas Antecipadas 
	 *
	 * @author Raphael Rossiter
	 * @date 14/04/2010
	 *
	 * @param idCreditoARealizar
	 * @param numeroPrestacoesRealizadas
	 * @param numeroPrestacoesAntecipadas
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroPrestacoesAntecipadasERealizadas(Integer idCreditoARealizar, Integer numeroPrestacoesRealizadas,
			Integer numeroPrestacoesAntecipadas) throws ErroRepositorioException ;
	
	
	/**
	 * 
	 * [UC0259] � Processar Pagamento com C�digo de Barras
	 * 
	 * [SB0019] � Gerar D�bitos/Cr�ditos Parcelas Antecipadas.
	 *
	 * @author Raphael Rossiter
	 * @date 19/04/2010
	 *
	 * @param idParcelamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public DebitoACobrar pesquisarDebitoACobrarJurosParcelamento(Integer idParcelamento)
	throws ErroRepositorioException ;
	
	/**
	 * [UC0259] � Processar Pagamento com C�digo de Barras
	 * 
	 * [SB0019] � Gerar D�bitos/Cr�ditos Parcelas Antecipadas. 
	 *
	 * @author Raphael Rossiter
	 * @date 19/04/2010
	 *
	 * @param debitoACobrar
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroParcelasBonus(DebitoACobrar debitoACobrar) throws ErroRepositorioException ;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario historico
	 * 
	 * @author Arthur Carvalho
	 * @date 12/05/10
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;
	
		/**
		 * [UC0322] Inserir Guia Devolucao.
		 * 
		 * [FS0023] Verificar cr�dito a realizar. �Verificarasds 
		 *
		 * @author Hugo Leonardo
		 * @date 26/05/2010
		 *
		 * @param idImovel, anoMesReferenciaConta
		 * @throws ErroRepositorioException
		 */
		public Integer verificarExistenciaCreditoARealizar(Integer idImovel, Integer anoMesReferenciaConta)
				throws ErroRepositorioException;
		
		/**
		 * [UC0322] Inserir Guia Devolucao.
		 * 
		 * [FS0023] Verificar cr�dito a realizar Hist�rico. �Verificarasds 
		 *
		 * @author Hugo Leonardo
		 * @date 26/05/2010
		 *
		 * @param idImovel, anoMesReferenciaConta
		 * @throws ErroRepositorioException
		 */
		public Integer verificarExistenciaCreditoARealizarHistorico(Integer idImovel, Integer anoMesReferenciaConta)
				throws ErroRepositorioException;
		
		/**
		 * [UC0194] Inserir Cr�dito a realizar.
		 * 		[FS0013] Verificar Guia devolu��o. �Verificarasds 
		 *
		 * @author Hugo Leonardo
		 * @date 27/05/2010
		 *
		 * @param idImovel, anoMesReferenciaGuiaDevolucao
		 * @throws ControladorException
		 */
		public Integer verificarExistenciaGuiaDevolucao(Integer idImovel, Integer anoMesReferenciaGuiaDevolucao)
				throws ErroRepositorioException;
		
		/**
		 * [UC0977] - Registrar Movimento Cart�o de Cr�dito
		 * 
		 * [SB0006� Distribuir Dados do Registro de Movimento do Arrecadador] 
		 *
		 * @author Raphael Rossiter
		 * @date 08/06/2010
		 *
		 * @param parcelamentoPagamentoCartaoCredito
		 * @throws ErroRepositorioException
		 */
		public void confirmarPagamentoCartaoCreditoOperadora(ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCredito) 
			throws ErroRepositorioException ;
		
		/**
		 * [UC0977] - Registrar Movimento Cart�o de Cr�dito
		 * 
		 * [SB0006� Distribuir Dados do Registro de Movimento do Arrecadador] 
		 *
		 * @author Raphael Rossiter
		 * @date 08/06/2010
		 *
		 * @param pagamentoCartaoDebito
		 * @throws ErroRepositorioException
		 */
		public void confirmarPagamentoCartaoDebitoOperadora(PagamentoCartaoDebito pagamentoCartaoDebito) 
			throws ErroRepositorioException ;

	/**
	 * 
	 * [UC0511] Filtrar Contrato Arrecadador
	 * @author Arthur Carvalho
	 * @date 26/05/10
	 * 
	 */
	public boolean verificarExistenciaContrato(String numeroContrato) throws ErroRepositorioException;
	
	/**
	 * [UC0339] Consultar Dados Di�rios da Arrecada��o
	 *
	 * @author Hugo Amorim
	 * @date 29/06/2010
	 *
	 * @throws ControladorException
	 */
	public Date pesquisarDataProcessamentoMes(Integer anoMes) throws ErroRepositorioException;
	
	/**
	 * [UC0629] Consultar Arquivo Texto para Leitura
	 *
	 * @author Raphael Rossiter
	 * @date 29/06/2010
	 *
	 * @param helper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarArquivoTextoRoteiroEmpresaCount(ConsultarArquivoTextoRoteiroEmpresaHelper helper)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0629] Consultar Arquivo Texto para Leitura 
	 *
	 * @author Raphael Rossiter
	 * @date 29/06/2010
	 *
	 * @param helper
	 * @param numeroPagina
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarArquivoTextoRoteiroEmpresaParaPaginacao(ConsultarArquivoTextoRoteiroEmpresaHelper helper, Integer numeroPagina)
		throws ErroRepositorioException ;
	
	/**
	 * [UC1043] Gerar Relat�rio An�lise Pagamento Cart�o D�bito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer relatorioAnalisePagamentoCartaoDebitoCount(
			ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * [UC1043] Gerar Relat�rio An�lise Pagamento Cart�o D�bito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAnalisePagamentoCartaoDebito(
			ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * [UC1043] Gerar Relat�rio An�lise Pagamento Cart�o D�bito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosItenRelatorioAnalisePagamentoCartaoDebito(
			Integer integer)throws ErroRepositorioException;
	
	/**
	 * [UC1043] Gerar Relat�rio An�lise Pagamento Cart�o D�bito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDetalheItenRelatorioAnalisePagamentoCartaoDebito(
			Integer tipoItem, Integer idItem)throws ErroRepositorioException;
	
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensa��o 
	 *
	 * @author Raphael Rossiter
	 * @date 24/11/2010
	 *
	 * @param idConta
	 * @return CobrancaDocumento
	 * @throws ErroRepositorioException
	 */
	public CobrancaDocumento pesquisarCobrancaDocumentoProcessarFichaCompensacao(Integer idCobrancaDocumento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0339] Consultar Dados Di�rios da Arrecada��o
	 *
	 * @author Mariana Victor
	 * @date 01/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarFaturamentoCobradoEmConta(Integer anoMes)
			throws ErroRepositorioException;
	
	/**
	 * [UC0339] Consultar Dados Di�rios da Arrecada��o
	 *
	 * @author Arthur Carvalho
	 * @date 22/03/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarFaturamentoCobradoEmContaComQuebra(Integer anoMes, Integer idGerenciaRegional, Integer idCategoria)
			throws ErroRepositorioException;
	
	/**
	 * [UC0188] Manter Guia de Pagamento
	 *
	 * [FS0019] � Verificar bloqueio de guia de pagamento
	 * 
	 * @author Mariana Victor
	 * @date 27/04/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> verificarBloqueioGuiaPagamento(Collection<GuiaPagamento> guiasPagamentos)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioPrimeiraSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioSegundaSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioTerceiraSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioQuartaSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensa��o
	 * 
	 * @author Mariana Victor
	 * @data 04/08/2011
	 */
	public CobrancaDocumentoItem pesquisarCobrancaDocumentoItemProcessarFichaCompensacao(Integer idPrestacao) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensa��o 
	 *
	 * @author Raphael Rossiter
	 * @date 26/07/2011
	 *
	 * @param idGuiaPagamento
	 * @return GuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public GuiaPagamento pesquisarGuiaPagamentoProcessarFichaCompensacao(Integer idGuiaPagamento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC 1215] � Gerar Relat�rio de Documentos n�o Aceitos
	 * 
	 * @author Raimundo Martins
	 *
	 * @date 19/08/2011
	 */
	public List<RelatorioDocumentoNaoAceitosBean> pesquisarDocumentosNaoAceitos(Arrecadador arrecadador, 
			String periodoInicial, String periodoFinal, Integer movimentoArrecadadorCodigo, 
			AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma) throws ErroRepositorioException;
	
	public boolean existeClienteIdDocNaoIdentificado();

	/**
	 * [UC 1217] � Gerar Relat�rio de Transferencia de Pagamento
	 * 
	 * @author Raimundo Martins
	 *
	 * @date 19/08/2011
	 */
	public List<RelatorioTranferenciaPagamentoBean> pesquisarTransfereciasPagamento(Arrecadador arrecadador, 
			String periodoInicial, String periodoFinal, AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma, 
			DebitoTipo debitoTipo, DocumentoTipo documentoTipo) throws ErroRepositorioException;


	/**
     * [UC0242] - Registrar Movimento dos Arrecadadores
	 * 
	 * @author Mariana Victor
	 * @date 18/08/2011
	 * 
	 * @param codigoConstante
	 * 
	 * @return DebitoTipo
	 * @throws ErroRepositorioException
	 */
	public DebitoTipo obterDebitoTipoCodigoConstante(Integer codigoConstante)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos N�o Aceitos
	 * 
	 * 3. O sistema identifica os pagamentos com documentos n�o aceitos 
	 * 	 que foram gerados para um cliente fict�cio e
	 *   junto com o filtro selecionado pelo usu�rio.
	 * 
	 * @author Mariana Victor
	 * @date 19/08/2011
	 * 
	 * @param codigoConstante
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentosDocumentosNaoAceitos(
			InformarAcertoDocumentosNaoAceitosPagamentoHelper helper)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos N�o Aceitos
	 * 
	 *  7.2.1. Total do Pagamento (PGMT _VLPAGAMENTO do pagamento doc. n�o aceito).
	 * 
	 * @author Mariana Victor
	 * @date 22/08/2011
	 * 
	 * @param idPagamento
	 * 
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorPagamento(
			Integer idPagamento)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos N�o Aceitos
	 * 
	 * @author Mariana Victor
	 * @date 24/08/2011
	 * 
	 * @param idGuia
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLocalidadeGuiaPagamento(
			Integer idGuia)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos N�o Aceitos
	 * 
	 * @author Mariana Victor
	 * @date 24/08/2011
	 * 
	 * @param idConta
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLocalidadeConta(
			Integer idConta)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos N�o Aceitos
	 * 
	 * @author Mariana Victor
	 * @date 24/08/2011
	 * 
	 * @param idDebitoACobrar
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLocalidadeDebitoACobrar(
			Integer idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Calcula o valor total do movimento arrecadador
	 * 
	 * @author Gustavo Amaral
	 * @date 14/09/2011
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */	
	public Collection pesquisarValorAcertosArrecadadorMovimento(Integer idArrecadadorMovimento)
			throws ErroRepositorioException;
	
	/**
	 * Filtrar a quantidade de pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rodrigo Cabral
	 * @date 15/09/11
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoHistoricoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um sql que ser� usado na pesquisa de pagamentos
	 * para o Relat�rio
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos Historico do Cliente
	 * 
	 * @author Rodrigo Cabral
	 * @date 16/09/11
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoHistoricoClienteRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;
	

	/* TODO: COSANPA
	 * Criado para a consulta, de manter conta por conjunto de im�veis, pelo id do banco
	 * e pelo grupo de faturamento
	 * 
	 * */
	
	/**@author Adriana Muniz
	  * @date: 07/04/2011
	  * [UC0146] Manter Conta
	  * 
	  * FS0028 - Verificar par�metro consulta e d�bito autom�tico
	  * @return
	  * @throws ErroRepositorioException
	  */
	public Collection pesquisarImoveisBancoDebitoAutomaticoEPorGrupoFaturamento(String[] bancos, 
			Integer idGrupoFaturamento) throws ErroRepositorioException;
	
	/* TODO: COSANPA
	 * autor: Adriana Muniz
	 * 
	 * Altera��o para a conta considerar como filtro grupo de faturamento, quando o mesmo estiver preenchido
	 */
	/**
	 * @autor: Adriana Muniz
	 * @date: 27/04/2011
	 * 
	 * [UC0146] Manter Conta
	 * 
	 * FS0028 - Verificar par�metro consulta e d�bito autom�tico
	 * 
	 * M�todo para retornar a quantidade de contas a partir dos im�veis com d�bito autom�tico - 
	 * Manter Contas de um Conjunto de im�veis. 
	 * 
	 */
	public Integer countImoveisBancoDebitoAutomaticoPorGrupoFaturamento(String[] bancos, 
			Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, String indicadorContaPaga, Integer idGrupoFaturamento, Integer somenteDebitoAutomatico)
		throws ErroRepositorioException;
	

	/**
	 * TODO: COSANPA
	 * 
	 * Mantis 537
	 * 
	 * @author Wellington Rocha
	 * @data 15/04/2012
	 * 
	 * @param idConta
	 * @return pagamento
	 */
	public Object[] pesquisarPagamentoDeContaEmHistorico(Integer idConta)
			throws ErroRepositorioException;
	

	/**TODO:COSANPA
	 * 
	 * @author Adriana Muniz
	 * data: 05/09/2012
	 * 
	 * @param helper
	 * @param filtro
	 * @return
	 */
	public Collection obterFormasDeArrecadacaoPorDia(
			Object helper, FiltroConsultarDadosDiariosArrecadacao filtro) throws ErroRepositorioException;
	

	/**TODO:COSANPA
	 * 
	 * Relat�rio Analitico dos valores di�rios da arrecada��o
	 * 
	 * @author Adriana Muniz
	 * data: 02/10/2012
	 * 
	 * obt�m as formas de arrecada��o com tarifa por dia
	 * @param helper
	 * @param filtro
	 * @return cole��o
	 */
	public Collection obterFormasDeArrecadacaoComTarifaPorDia(
			Object helper, FiltroConsultarDadosDiariosArrecadacao filtro) throws ErroRepositorioException;
	

	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 05/12/2012
	 * 
	 * 
	 * Exclui os dados di�rios da arrecada��o do ano/m�s da arrecada��o corrente
	 * por localidade da a tabela arrecadacao_dados_diarios_auxiliar
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosDiariosArrecadacaoAuxiliarPorAnoMesArrecadacaoPorLocalidade(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 05/12/2012
	 * 
	 * Acumula a quantidade e o valor dos pagamentos com ano/m�s de refer�ncia
	 * da arrecada��o igual ao ano/m�s de refer�ncia da arrecada��o corrente,
	 * 
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularQuantidadeEValorPagamentoPorAnoMesArrecadacaoAuxiliar(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**TODO: COSANPA
	 * @author Adriana Muniz
	 * @date 10/12/2012
	 * 
	 * Consultar Dados Di�rios da Arrecada��o a partir da tabela arrecadacao_dados_diarios_aulixiar
	 *
	 * Verificar se existe dados diarios da arrecadacao de acordo com o filtro 
	 * passado
	 *
	 *
	 * @param filtro
	 * @return boolean de existencia dos dados
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaDadosDiariosArrecadacaoAuxiliar(FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro)
		throws ErroRepositorioException;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 11/12/2012
	 * 
	 * Consultar Dados Di�rios da Arrecada��o Auxiliar
	 * 
	 * @param filtro
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarDadosDiariosArrecadacaoAuxiliar(FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ErroRepositorioException;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 11/12/2012
	 * 
	 * Exclui os dados di�rios da devolucao auxiliar do ano/m�s da arrecada��o corrente
	 * por localidade
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosDiariosDevolucaoPorAnoMesArrecadacaoAuxiliarPorLocalidade(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/** TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 12/12/2012
	 * 
	 * Acumula a quantidade e o valor das devolucoes com ano/m�s de refer�ncia
	 * da arrecada��o igual ao ano/m�s de refer�ncia da arrecada��o corrente 
	 * para ser persistido na tabela devolucao_dados_diarios_auxiliar
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularQuantidadeEValorDevolucaoPorAnoMesArrecadacaoAuxiliar(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**TODO:COSANPA
    *
    * Relat�rio Analitico dos valores di�rios da arrecada��o com tarifa
    *
    * @author Adriana Muniz
    * data: 19/12/2012
    *
    * obt�m as formas de arrecada��o com tarifa por dia
    * @param helper
    * @param filtro
    * @return cole��o
    */
   public Collection obterFormasDeArrecadacaoComTarifaPorDiaAuxiliar(
           Object helper, FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ErroRepositorioException;

   /**
	 * TODO: COSANPA
	 * 
	 * Mantis: ***
	 * 
	 * Mudan�a de conta cont�bil de recebimentos at� 12/2012 classificados no m�s atual;
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idImpostoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImpostosDeduzidosPagamentosContasEfetuadosAte122012ClassificadosMesPorTipoImposto(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idImpostoTipo) throws ErroRepositorioException;
	
	/**
	 * TODO: COSANPA
	 * 
	 * Mantis *** 
	 * 
	 * Contabilizar valores arrecadados at� dezembro de 2012 em contas diferentes.
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] acumularValorAguaEsgotoPagamentosContasEfetuadosAte122012ClassificadosNoMes(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
	
	/**
	 * Mantis ***
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Contabilizar valores arrecadados at� 31/12/2012 em contas diferentes
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idFinanciamentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosAte122012ClassificadosNoMesPorFinanciamentoTipo(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, 
			Integer idFinanciamentoTipo)
			throws ErroRepositorioException;
	
	/**
	 * Mantis ***
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Altera��o na contabiliza��o de contas arrecadadas at� 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idOrigemCredito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosAte122012ClassificadosNoMesPorOrigemCredito(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, 
			Integer idOrigemCredito)
			throws ErroRepositorioException;
	
	/**
	 * TODO: COSANPA
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Mantis ***
	 * 
	 * Contabilizar em contas diferentes os valores arrecadados at� 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosAte122012ClassificadosNoMesFinanciamentoTipoEntradaParcelamento(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
	
	
	/**
	 * Mantis ***
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Contabilizar em contas diferentes valores arrecadados at� 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @param idsFinanciamentoTipos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosAte122012ClassificadosNoMesPorFinanciamentoTipo(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, 
			Integer idCategoria,
			Collection<Integer> idsFinanciamentoTipos)
			throws ErroRepositorioException;
	
	/**
	 * Mantis ***
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Contabilizar em contas separadas valores arrecadados at� 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosAte122012ClassificadosNoMesFinanciamentoTipoDoacoes(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, 
			Integer idLancamentoItemContabil)
			throws ErroRepositorioException;
	
	/**
	 * Mantis ****
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Contabilizar em contas separadas valores arrecadados at� 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosAte122012ClassificadosNoMesOrigemCreditoValoresCobradosIndevidamente(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, 
			Integer idLancamentoItemContabil)
			throws ErroRepositorioException;
	
	/**
	 * Mantis ****
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Contabilizar em contas separadas valores arrecadados at� 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosAte122012ClassificadosNoMesFinanciamentoTipoServico(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, 
			Integer idCategoria)
			throws ErroRepositorioException;
	
	
	/**
	 * Mantis ****
	 * 
	 * [UC0276] - Encerrar Arrecada��o do M�s
	 * 
	 * Contabilizar em contas separadas valores arrecadados at� 31/12/2012
	 * 
	 * @author Wellington Rocha
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosDebitoACobrarEfetuadosAte122012(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, 
			Integer idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarTotalArrecadacaoRelatorioBIG(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Object[] pesquisarPrazoMedioRecebimentoContasRelatorioBIG(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection pesquisarDadosRelatorioBIG(Integer anoMesReferencia)
			throws ErroRepositorioException;
	
	public Collection<PagamentoHelper> pesquisarValoresPagamentos(Integer pagamentoSituacao, Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;
	
	public void atualizarSituacaoPagamento(Integer pagamentoSituacao, Integer idPagamento) throws ErroRepositorioException;
	
	public void atualizarGuiasPagamentoNaoPagasAtePeriodo(Integer financiamentoTipoServico, 
			Collection<Integer> idsGuiasPagamentoNaoPagas, Integer anoMesReferencia) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdsGuiasPagamentoNaoPagas(Date dataVencimentoLimite,  Integer idLocalidade) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdsLocalidadeComGuiasPagamentoNaoPagas(Integer financiamentoTipoServico, 
			Date dataVencimentoLimite) throws ErroRepositorioException;
	
	public Collection<Pagamento> obterPagamentos(Collection<Integer> idsPagamentos) throws ErroRepositorioException;
	
	public Collection<DebitoAutomatico> pesquisarDebitoAutomaticoSemDataExclusao(Integer idImovel) throws ErroRepositorioException;
	
	public Collection pesquisarClienteGuiaPagamentoECliente(Integer idGuiaPagamento) throws ErroRepositorioException;
}
