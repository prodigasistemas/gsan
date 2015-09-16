package gcom.financeiro;

import gcom.cadastro.localidade.Localidade;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.relatorio.financeiro.RelatorioVolumesConsumidosNaoFaturadosBean;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;


/**
 * Interface para o repositório de financeiro
 * 
 * @author Raphael Rossiter
 * @since 09/01/2006
 */
public interface IRepositorioFinanceiro {

	
	/**
	 * Obtém os dados do resumoFaturamento a partir do ano e mês de referência
	 *
	 * [UC0175] - Gerar Lançamentos Contábeis do Faturamento
	 *
	 * @author Raphael Rossiter, Pedro Alexandre 
	 * @date 16/01/2006, 24/05/2007
	 *
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoFaturamento(Integer anoMesReferenciaFaturamento, Integer idLocalidade)throws ErroRepositorioException ;
	
	
	/**
	 * Obtém a conta contábil a partir do número da razão contábil e do núemro da conta
	 * @param razao
	 * @param conta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil obterContaContabil(Short razao, Integer conta) 
		throws ErroRepositorioException;
	
	
	/**
	 * Obtém a conta contábil a partir da tabela LANCAMENTO_ITEM_CONTABIL
	 * @param razao
	 * @param conta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil obterContaContabil(LancamentoItemContabil lancamentoItemContabil) 
		throws ErroRepositorioException;

	/**
	 * 
	 * Gera Lançamentos Contabeis do Faturamento
	 *
	 * [UC000348] - Gerar Lançamento Constábeis da Arrecadação
	 *
	 * Obter O Parametros Contabile Arrecadacao
	 *
	 * @author Rafael Santos
	 * @date 23/05/2006
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public Collection obterParametrosContabilArrecadacao(Integer idCategoria,Integer idItemLancamentoContabil,
		Integer idItemLancamento,Integer idTipoLancamento,Integer idTipoRecebimento) 
		throws ErroRepositorioException; */
	
	/**
	 * 
	 * Gera Lançamentos Contabeis do Faturamento
	 *
	 * [UC000348] - Gerar Lançamento Constábeis da Arrecadação
	 *
	 * @author Rafael Santos, Pedro Alexandre
	 * @date 23/05/2006, 25/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosResumoArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;
	
	/**
	 * 
	 * este metodo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * [UC0469] Gerar Integração para a Contabilidade
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) 
		throws ErroRepositorioException;	
	
	/**
	 * Exclui os registros da tabela RESUMO_DEVEDORES_DUVIDOSOS 
	 * por ano mês refência contábil 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil)
		throws ErroRepositorioException;
	
	/**
	 * Atualiza com o valor nulo o mês/ano de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil) 
		throws ErroRepositorioException;
	
	/**
	 * Seleciona todas as ocorrencias dos itens dos parâmetros
	 * baixa contábil
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public Collection<ParametrosDevedoresDuvidososItem> pesquisaParametrosDevedoresDuvidososItem(
		Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 01 Retorna o valor de água acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorAgua(int anoMesReferenciaBaixaContabil, 
		int idLocalidade, int idCategoria) throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 02 Retorna o valor do esgoto acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorEsgoto(int anoMesReferenciaBaixaContabil, 
		int idLocalidade, int idCategoria) throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 03 Retorna o valor da categoria acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 03 Retorna o valor da categoria acumulado por financiamento tipo esgoto, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por gupo contabil, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServicos(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 06 Retorna o valor da categoria acumulado por financiamento juros parcelamentos, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 07 Retorna o valor da categoria acumulado por financiamento por serviço, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServico(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException ;	
	
	/**
	 * [UC0345] - Gerar Relatorio de Resumo da Arrecadação
	 *
	 * @author Vivianne Sousa
	 * @date 10/04/2007
	 *
	 * @param idLancamentoTipo
	 * @throws ErroRepositorioException
	 */	
	public String obterDescricaoLancamentoTipo(Integer idLancamentoTipo) 
		throws ErroRepositorioException;
	
	/**
	 * [UC00175] Gerar Lançamentos Contábeis do Faturamento
	 *
	 * Pesquisa os parâmetros contábil do faturamento.
	 *
	 * @author Pedro Alexandre
	 * @date 24/05/2007
	 *
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilFaturamento(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	/**
	 * Pesquisa as localidades que tem resumo de faturamento 
	 * para o ano/mês de faturamento informado.
	 *
	 * [UC00175] Gerar Lançamentos Contábeis do Faturamento
	 *
	 * @author Pedro Alexandre
	 * @date 25/05/2007
	 *
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * [UC00348] Gerar Lançamentos Contábeis da Arrecadação
	 *
	 * Pesquisa os parâmetros contábil da arrecadação.
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param idRecebimentoTipo	
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilArrecadacao(Integer idRecebimentoTipo, Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	/**
	 * Pesquisa as localidades que tem resumo da arrecadação 
	 * para o ano/mês de arrecadação informado.
	 *
	 * [UC00348] Gerar Lançamentos Contábeis da Arrecadação
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(Integer anoMesArrecadacao) throws ErroRepositorioException;

	/**
	 * Pesquisa os parâmetros dos devedores duvidosos por
	 * ano/mês de referência contábil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParametrosDevedoresDuvidosos pesquisarParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ErroRepositorioException ;

	/**
	 * Atualiza com o valor nulo o mês/ano de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 * para a localidade informada.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 22/11/2006, 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade)	throws ErroRepositorioException ;

	/**
	 * Exclui os registros da tabela RESUMO_DEVEDORES_DUVIDOSOS 
	 * por ano mês refência contábil e localidade
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 22/11/2006, 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * 
	 * @throws ErroRepositorioException
	 */	
	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException;

	/**
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException ;

	/**
	 * Linha 01 Retorna o valor de água acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 12/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 02 Retorna o valor do esgoto acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 03 Retorna o valor da categoria acumulado por tipo financiamento por parcelamento agua, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 04 Retorna o valor da categoria acumulado por financiamento por parcelamento esgoto, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 06 Retorna o valor da categoria acumulado por financiamento juros parcelamentos, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por parcelamento serviço, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamento(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,int idFinanciamentoTipo, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;
	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Flávio Cordeiro
	 * @date 06/06/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;

	/**
	 * Obtém os dados do resumo dos devedores duvidosos 
	 * a partir do ano e mês de referência contábil e da localidade.
	 *
	 * [UC0486] - Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre 
	 * @date 21/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException ;

	/**
	 * [UC0486] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * Pesquisa os parâmetros contábil dos devedores duvidosos.
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilDevedoresDuvidosos(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	/**
	 * Pesquisa a coleção de ids das localidades para processar os lançamentos 
	 * contábeis dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException ;

	/**
	 * Pesquisa uma coleção de ids de lançamentos contábeis por localidade.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idLancamentoOrigem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLancamentosContabeis(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idLancamentoOrigem)throws ErroRepositorioException ;

	/**
	 * Remove os Itens do lançamento contábil.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param idLancamentoContabil
	 * @throws ErroRepositorioException
	 */
	public void removerItensLancamentoContabil(Integer idLancamentoContabil) throws ErroRepositorioException;

	/**
	 * Remove os Lançamentos Contábeis.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param colecaoIdsLancamentosContabeis
	 * @throws ErroRepositorioException
	 */
	public void removerLancamentosContabeis(Collection<Integer> colecaoIdsLancamentosContabeis) throws ErroRepositorioException;

	/**
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por parcelamento serviço, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServico(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstado(
			int anoMesReferencia) throws ErroRepositorioException ;


	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException ;
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(
			int anoMesReferencia, Integer gerenciaRegional)throws ErroRepositorioException;


	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegionalPorLocalidade(
			int anoMesReferencia, Integer gerenciaRegional)throws ErroRepositorioException ;

	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer unidadeNegocio) throws ErroRepositorioException;

	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorLocalidade(
			int anoMesReferencia, Integer localidade)throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Remove as contas a receber contábil
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void removerContasAReceberContabil(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores de água e esgoto pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores de impostos pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorImpostos(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos cobrados para serviços pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaServico(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos cobrados para parcelamentos pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores das guias de pagamento para entradas de parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaEntradaParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores das guias de pagamento para serviços pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaServico(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos realizados para pagamentos em duplicidade ou em excesso pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaPagamentoExcesso(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos realizados para descontos no parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos realizados para descontos condicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoCondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos realizados para descontos incondicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoIncondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos realizados para ajustes para zerar conta pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaAjusteZerarConta(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos realizados para devoluções pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDevolucao(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos a cobrar para serviço pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaServico(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos a cobrar para documentos emitidos pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaDocumentosEmitidos(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos a cobrar para financiamentos a cobrar de
	 * curto prazo pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosCurtoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos a cobrar para financiamentos a cobrar de
	 * longo prazo pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosLongoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos a cobrar para parcelamentos a cobrar de
	 * curto prazo pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosCurtoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos a cobrar para parcelamentos a cobrar de
	 * longo prazo pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosLongoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos a cobrar para arrasto de água, arrasto de
	 * esgoto e arrasto de serviço pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaArrasto(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos a realizar para descontos concedidos no
	 * parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontosParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os descontos concedidos no parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontosParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos a realizar para devoluções pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDevolucao(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para as devoluções pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDevolucao(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos a realizar para descontos incondicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoIncondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os descontos incondicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoIncondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos a realizar para contas pagas em excesso
	 * ou em duplicidade pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaPagamentoExcesso(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os pagamentos em excesso ou duplicidade no parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualPagamentoExcesso(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos a realizar para descontos condicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoCondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os descontos condicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoCondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos créditos a realizar para ajustes para zerar contas
	 * pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaAjusteZerarConta(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os ajustes para zerar a conta no parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualAjusteZerarConta(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Seleciona as quadras da localidade informada 
	 * onde existe contas a serem baixadas contabiolmente
	 * 
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Pedro Alexandre
	 * @date 22/11/2006
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterQuadrasPorLocalidadeParaGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, int idLocalidade) throws ErroRepositorioException ;


	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * verifica se a conta informada possui cliente responsável 
	 * com esfera de poder de tipo de cliente igual a municipal,
	 * estadual ou federal.
	 *
	 * @author Pedro Alexandre
	 * @date 23/07/2007
	 *
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaClienteResponsavelConta(int idConta) throws ErroRepositorioException ;

	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 21/12/2007, 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException;

	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 26/12/2007, 12/04/2011 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 07/01/2008, 12/04/2011 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio)
			throws ErroRepositorioException; 
	
	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;	

	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;

	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;
	
	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Localidade
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;

	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Município
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Localidade
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;
	
	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Município
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Localidade
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Município
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0751] - Gerar Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * Remove o valor dos volumes consumidos e não faturados 
	 * 
	 * @author Rafael Corrêa
	 * @date 19/02/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 *            Ano e mês de referência faturamento
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void removerValorVolumesConsumidosNaoFaturados(
			int anoMesReferenciaFaturamento, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0751] - Gerar Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * Acumula os valores de água e esgoto pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 *            Ano e mês de referência faturamento
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosValorVolumesConsumidosNaoFaturadosAguaEsgoto(
			int anoMesReferenciaFaturamento, Integer idLocalidade, Date ultimoDiaMesCorrenteFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0717] - Consultar dados do relatorio de Saldo do Contas a Receber Contabil
	 * 
	 * @date 16/01/08
	 * @author Frncisco do Nascimento
	 * 
	 * @param anoMesReferencia
	 * @param gerencia
	 * @param unidadeNegocio
	 * @param localidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosRelatorioSaldoContasAReceberContabil(String opcaoTotalizacao,
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @param quantidadeMaxima 
     * @param indice 
     * @date: 09/04/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa1(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;

    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa2(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa3(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;
    
    /**
     * [[UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;

    /**
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para juros cobrados de
     * longo prazo pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 26/05/2008
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaJurosCobrados(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException;
    /**
	 * [UC0824] Gerar Relatório dos Parâmetros Contábeis
	 * 
	 * @author Bruno Barros
	 * @date 08/07/2008
	 * 
	 * @return Collection<RelatorioParametrosContabeisFaturamentoBean>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> 
		pesquisarDadosRelatorioParametrosContabeisFaturamento( Integer referenciaContabil ) 
		throws ErroRepositorioException;    
        
    /**
     * [UC0824] Gerar Relatório dos Parâmetros Contábeis
     * 
     * @author Bruno Barros
     * @date 08/07/2008
     * 
     * @return Collection<RelatorioParametrosContabeisArrecadacaoBean>
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> 
        pesquisarDadosRelatorioParametrosContabeisArrecadacao( Integer referenciaContabil ) 
        throws ErroRepositorioException;            
    
	/**
	 * [UC0822] Gerar Relatório do Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * @author Victor Cisneiros
	 * @date 15/07/2008
	 */
    public List<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisarVolumesConsumidosNaoFaturados(
    		Integer mesAno, String opcaoTotalizacao, Integer idEntidade) throws ErroRepositorioException;
	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade da Caema
	 *
	 * @author Arthur Carvalho
	 * @date 02/03/09
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCaema(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores de água e esgoto pela gerência, localidade e categoria
	 * 
	 * @author Vivianne Sousa
	 * @date 14/08/2009
	 * 
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
     * [UC0714] - Gerar Contas a Receber Contábil
     * 
     * Acumula DCCG_VLCATEGORIA da tabela DEBITO_COBRADO_CATEGORIA 
     * com DBCB_ID=DBCB_ID da tabela DEBITO_COBRADO e 
     * CNTA_ID=CNTA_ID da tabela CONTA 
     * com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
     *
     * @author Vivianne Sousa
     * @date 14/08/2009
     *
     * @param idLocalidade
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitosCobradosCategoria( Integer idLocalidade)
            throws ErroRepositorioException;
    
    /**
     * [UC0714] - Gerar Contas a Receber Contábil
     * 
     * Acumula negativamente CRCG_VLCATEGORIA 
     * da tabela CREDITO_REALIZADO_CATEGORIA 
     * com CRRZ_ID=CRRZ_ID da tabela CREDITO_REALIZADO 
     * e CNTA_ID=CNTA_ID da tabela CONTA 
     * com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
     *
     * @author Vivianne Sousa
     * @date 14/08/2009
     *
     * @param idLocalidade
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoria(Integer idLocalidade)
            throws ErroRepositorioException;
    
    /**
     * [UC0714] - Gerar Contas a Receber Contábil
     * 
     * @author Vivianne Sousa
     * @date 17/08/2009
     *
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarValorPagamentoImovel(
    		Integer idLocalidade, Integer anoMesReferenciaArrecadacao)
            throws ErroRepositorioException;
    
    /**
     * [UC0714] - Gerar Contas a Receber Contábil
	 * @author Vivianne Sousa
	 * @date 17/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Localidade pesquisarUnidadeNegocioEGerenciaDaLocalidade(
			Integer idLocalidade)throws ErroRepositorioException;
	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade - COSANPA
	 *
	 * @author Raphael Rossiter
	 * @date 17/11/2009
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCOSANPA(String idLancamentoOrigem, String anoMes) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param anoMesReferenciaArrecadacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAvisosBancariosParaGerarLancamentosContabeis(Integer anoMesReferenciaArrecadacao) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param nomeConta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil pesquisarContaContabilPorNomeConta(String nomeConta) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param idAvisoBancario
	 * @param valorContabilizado
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorContabilizado(Integer idAvisoBancario, BigDecimal valorContabilizado)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberResumo(
			int anoMesReferenciaRecebimentos, Integer idLocalidade, Session session)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarContasAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarGuiasPagamentoAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDebitosACobrarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarCreditosARealizarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarImpostoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDividaAtivaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDividaAtivaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarImpostoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarOutrasReceitasResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	public Collection pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoGuiaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	public Collection pesquisarResumoPagamentoContaServico(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoHistoricoPagamentoContaCredito(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoHistoricoPagamentoContaServico(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoReceitaAgrupadoPorBanco(ResumoReceitaHelper resumo)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoReceitaRelatorioAnalitico(ResumoReceitaHelper resumo)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorTotalContasDevedoresDuvidosos( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString,
			Integer idParametrosDevedoresDuvidosos)
		throws ErroRepositorioException ;
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioResumo(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioHistoricoResumo(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra , Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra ,Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade,  Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos )  throws ErroRepositorioException ;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException ;

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @throws ErroRepositorioException
	 */
	public void atualizaContaAnoMesReferenciaContabilDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra ,
			Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 10/01/2011
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos (Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 10/01/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idQuadra
	 * @throws ErroRepositorioException
	 */
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra)	throws ErroRepositorioException ;
	

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 17/03/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException ;
	
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Mariana Victor
	 * @date 28/03/2011
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberFaixaResumo(int anoMesReferenciaRecebimentos, Integer idLocalidade, Session session) throws ErroRepositorioException;
	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Tiago Moreno
	 * @date 28/06/11
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCosama(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @param anoMesReferenciaContabil
	 * @param valorTotalValoresBaixados
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorBaixadoParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil, BigDecimal valorTotalValoresBaixados) throws ErroRepositorioException ;
	
	/**
	 * 
	 *  Pesquisa os Valores Baixados No resumo Agrupados Por Localidade
	 * 
	 * @author Arthur Carvalho
	 * @date 19/09/2011
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorBaixadoAgrupadoPorLocalidadeResumoDevedoresDuvidosos( int anoMesReferenciaContabil )  throws ErroRepositorioException ;
	
	
	public Collection<Object[]> pesquisarDadosCreditosARealizar(int anoMesReferenciaContabil, Integer idLocalidade, Integer creditoOrigem) throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarValorResidual(int anoMesReferenciaContabil, Integer idLocalidade, Integer creditoOrigem) throws ErroRepositorioException;
	
    /**
     * Gerar Relatório dos Parâmetros Contábeis
     * 
     * @author Reinaldo Viana
     * @date 02/09/2015
     * 
     * @return Collection<RelatorioParametrosContabeisContasAReceberBean>
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> 
        pesquisarDadosRelatorioParametrosContabeisContasAReceber( Integer referenciaContabil ) 
        throws ErroRepositorioException;  
	 
	 
}
