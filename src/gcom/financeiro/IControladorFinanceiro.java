package gcom.financeiro;

import gcom.batch.ProcessoIniciado;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.relatorio.financeiro.RelatorioEvolucaoContasAReceberContabilBean;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisArrecadacaoBean;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisFaturamentoBean;
import gcom.relatorio.financeiro.RelatorioVolumesConsumidosNaoFaturadosBean;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Interface Controlador Financeiro PADRÃO
 *
 * @author Raphael Rossiter
 * @date 26/06/2007
 */
public interface IControladorFinanceiro {

	/**
	 * Pesquisa uma coleção de lançamento item contábil
	 * 	 
	 * @return Coleção de Lançamentos de Item Contábil 
	 * @exception ErroRepositorioException  Erro no hibernate
	 */
	public Collection<LancamentoItemContabil> pesquisarLancamentoItemContabil() throws ControladorException;
	
	/**
	 * Gera Lançamentos Contabeis do Faturamento
	 *
	 * [UC000348] - Gerar Lançamento Constábeis da Arrecadação
	 *
	 * @author Rafael Santos
	 * @date 22/05/2006
	 *
	 * @param anoMesArrecadacao
	 * @throws ControladorException 
	 */
	public void gerarLancamentoContabeisArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException; 
	
	/**
	 * este caso de uso gera a integração para a contabilidade
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Pedro Alexandre
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	public void gerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes, String data) throws ControladorException;

	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ControladorException
	 */		
	public void gerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, int idFuncionalidadeIniciada)
		throws ControladorException;
	
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
		throws ControladorException;
	

	/**
	 * [UC0175] - Gerar Lançamentos Contábeis do Faturamento
	 * Author: Raphael Rossiter, Pedro Alexandre 
	 * Data: 16/01/2006, 23/05/2007
	 * 
	 * Gera os lançamentos contábeis a partir dos dados selecionados na tabela RESUMO_FATURAMENTO
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarLancamentosContabeisFaturamento(Integer anoMesReferenciaFaturamento, Integer idLocalidade, int idFuncionalidadeIniciada ) throws ControladorException;

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
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(Integer anoMesFaturamento) throws ControladorException;

	/**
	 * Pesquisa as localidades que tem resumo de arrecadação 
	 * para o ano/mês de arrecadação informado.
	 *
	 * [UC00348] Gerar Lançamentos Contábeis da arrecadação
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(Integer anoMesArrecadacao) throws ControladorException;

	/**
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ControladorException;
	
	/**
	 * este caso de uso gera a integração para a contabilidade
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Flávio Leonardo
	 * @date 06/06/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	public void gerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes, String data) throws ControladorException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 18/06/2007
	 *
	 * @param processoIniciado
	 * @param dadosProcessamento
	 * @return
	 * @throws ControladorException
	 */
	public Integer gerarResumoDevedoresDuvidosos(ProcessoIniciado processoIniciado,Map<String, Object> dadosProcessamento)	throws ControladorException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 18/06/2007
	 *
	 * @param processoIniciado
	 * @param dadosProcessamento
	 * @return
	 * @throws ControladorException
	 */
	public ParametrosDevedoresDuvidosos pesquisarParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil)	throws ControladorException ;

	/**
	 * Gera os lançamentos dos devedores duvidosos.
	 *
	 * [UC0486] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarLancamentosContabeisDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Pesquisa a coleção de ids das localidades para processar o lançamentos  
	 * contábeis dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ControladorException;
	
	/**
	 * Remove os lançamentos contábeis e seus respectivos itens 
	 * de acordo com os parâmetros informados. 
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idLancamentoOrigem
	 * @throws ControladorException
	 */
	public void removerLancamentoContabil(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idLancamentoOrigem) throws ControladorException;
	
	/**
	 * [UC0714] Gerar Contas a Receber Contábil
	 * 
	 * Método responsável pela geração de contas a receber contábil
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void gerarContasAReceberContabil(Integer idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC0751] Gerar Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * Método responsável pela geração de valor dos volumes consumidos e não faturados
	 * 
	 * @author Rafael Corrêa
	 * @date 19/02/2008
	 * 
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void gerarValorVolumesConsumidosNaoFaturados(Integer idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorio(String opcaoTotalizacao, int mesAnoReferencia,
			Integer gerenciaRegional, Integer localidade, Integer unidadeNegocio)throws ControladorException;

	/**
	 * [UC0718] Gerar Relatório de Evolucao do Contas a Receber Contabil
	 * 
	 * @author Francisco Junior
	 * @date 02/01/08
	 * 
	 * @param opcaoTotalizacao
	 * @param mesAno
	 * @param codigoGerencia
	 * @param codigoLocalidade
	 * @param unidadeNegocio
	 * @return Colecao 
	 * @throws ControladorException
	 */
	public Collection<RelatorioEvolucaoContasAReceberContabilBean> consultarDadosEvolucaoContasAReceberContabilRelatorio(String opcaoTotalizacao,
			int mesAno, Integer codigoGerencia, Integer codigoLocalidade, Integer codigoMunicipio, Integer unidadeNegocio) throws ControladorException;

	/**
	 * [UC0717] - Consultar dados do relatorio de Saldo do Contas a Receber Contabil
	 * 
	 * @date 17/01/08
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
			throws ControladorException;	
    
     
   /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     */
     public void gerarTXTContasBaixadasContabilmente(
                Map parametros, Integer idSetorComercial, Integer idFuncionalidadeIniciada,Integer faixa)
                throws ControladorException;
     
     
 	/**
 	 * [UC0824] Gerar Relatório dos Parâmetros Contábeis
 	 * 
 	 * @author Bruno Barros
 	 * @date 08/07/2008
 	 * 
 	 * @return Collection<RelatorioParametrosContabeisFaturamentoBean>
 	 * @throws ErroRepositorioException
 	 */
 	public Collection<RelatorioParametrosContabeisFaturamentoBean> 
 		pesquisarDadosRelatorioParametrosContabeisFaturamento( String referenciaContabil ) throws ControladorException;
 	/**
 	 * [UC0822] Gerar Relatório do Valor Referente a Volumes Consumidos e Não Faturados
 	 * 
 	 * @author Victor Cisneiros
 	 * @date 15/07/2008
 	 */
     public List<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisarVolumesConsumidosNaoFaturados(
     		Integer mesAno, String opcaoTotalizacao, Integer idEntidade) throws ControladorException;
     
    /**
     * [UC0824] Gerar Relatório dos Parâmetros Contábeis
     * 
     * @author Bruno Barros
     * @date 08/07/2008
     * 
     * @return Collection<RelatorioParametrosContabeisArrecadacaoBean>
     * @throws ErroRepositorioException
     */
    public Collection<RelatorioParametrosContabeisArrecadacaoBean> 
        pesquisarDadosRelatorioParametrosContabeisArrecadacao( String referenciaContabil ) throws ControladorException;
    
    /**
     * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
     *
     * @author Raphael Rossiter
     * @date 22/02/2010
     *
     * @param anoMesArrecadacao
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    public void gerarLancamentosContabeisAvisosBancarios(Integer anoMesArrecadacao, 
    	int idFuncionalidadeIniciada) throws ControladorException ;
    
    /**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoDocumentosAReceber(Integer idLocalidade,
            int idFuncionalidadeIniciada) throws ControladorException ;
	
	
	   /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Flávio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e será executado
     * através de um batch
     */
    
    public void gerarResumoReceita(int idFuncionalidadeIniciada) throws ControladorException;
    
    public Collection pesquisarResumoReceitaAgrupadoPorBanco(ResumoReceitaHelper resumo)
		throws ControladorException;
    
    public Collection pesquisarResumoReceitaRelatorioAnalitico(ResumoReceitaHelper resumo)
	throws ControladorException;
    
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 14/09/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ControladorException
	 */		
	public void apagarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, int idFuncionalidadeIniciada)
		throws ControladorException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void atualizarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, int idFuncionalidadeIniciada)
		throws ControladorException;
	
	/**
     * [[UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Rodrigo Cabral
     * @date: 16/03/2011 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Map consultarSomatorioValorContasBaixadasContabilmenteFaixa(
    		Integer referenciaInicio, Integer referenciaFinal,Integer faixa, Short periodicidade)
    		throws ControladorException;

}
