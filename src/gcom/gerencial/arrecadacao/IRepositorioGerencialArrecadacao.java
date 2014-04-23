package gcom.gerencial.arrecadacao;

import java.util.List;

import gcom.util.ErroRepositorioException;

/**
 * 
 * 
 * @author Ivan Sérgio
 * @created 10/05/2007
 */
public interface IRepositorioGerencialArrecadacao {
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Aguá/Esgoto]
	 * 
	 * @author Ivan Sérgio
	 * @date 19/05/2008
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgoto(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - CONTA]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosConta(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - GUIA DE PAGAMENTO]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamento(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - DEBITO A COBRAR]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrar(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * @author Ivan Sérgio
	 * @date 22/05/2008
	 *
	 * @param idSetorComercial
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditos(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_arrecadacao
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoArrecadacao()
			throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Aguá/Esgoto - Valor Nao Identificado]
	 * 
	 * @author Ivan Sérgio
	 * @date 02/06/2008
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado(
			int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
	 * 
 	 * [UC0533] - Gerar Resumo da Arrecadacao
	 * 
	 * @author Ivan Sérgio
	 * @date 12/06/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarDadosPagamentoSemContaGuiaDebito(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/***
	 * [UC0533] - Gerar Resumo da Arrecadacao - Devolucao
	 * 
	 * @author Ivan Sérgio
	 * @date 09/10/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucao(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 02/06/2010
	 * 		 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
	 * 
 	 * Gerar Resumo da Arrecadacao Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 03/06/2010
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarDadosPagamentoSemContaGuiaDebitoPorAno(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Aguá/Esgoto - Valor Nao Identificado
	 * 
	 * @author Fernando Fontelles
	 * @date 05/06/2010
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificadoPorAno(
			int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - CONTA
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosContaPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - GUIA DE PAGAMENTO
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamentoPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - DEBITO A COBRAR
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrarPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditosPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/***
	 * Gerar Resumo da Arrecadacao Por Ano - Devolucao
	 * 
	 * @author Fernando Fontelles
	 * @date 10/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucaoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
}
