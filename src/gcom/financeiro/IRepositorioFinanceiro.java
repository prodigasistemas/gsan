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

public interface IRepositorioFinanceiro {

	public Collection<Object[]> obterDadosResumoFaturamento(Integer anoMesReferenciaFaturamento, Integer idLocalidade)throws ErroRepositorioException ;
	
	public ContaContabil obterContaContabil(Short razao, Integer conta) throws ErroRepositorioException;
	
	public ContaContabil obterContaContabil(LancamentoItemContabil lancamentoItemContabil) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterDadosResumoArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;	
	
	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil) throws ErroRepositorioException;
	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil) throws ErroRepositorioException;
	
	public Collection<ParametrosDevedoresDuvidososItem> pesquisaParametrosDevedoresDuvidososItem( Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException;
	
	public ResumoDevedoresDuvidosos acumularValorAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) throws ErroRepositorioException;
	
	public ResumoDevedoresDuvidosos acumularValorEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) throws ErroRepositorioException ;
	
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServicos(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;
	
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServico(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException ;	
	
	public String obterDescricaoLancamentoTipo(Integer idLancamentoTipo) throws ErroRepositorioException;
	
	public Object[] obterParametrosContabilFaturamento(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(Integer anoMesFaturamento) throws ErroRepositorioException;

	public Object[] obterParametrosContabilArrecadacao(Integer idRecebimentoTipo, Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(Integer anoMesArrecadacao) throws ErroRepositorioException;

	public ParametrosDevedoresDuvidosos pesquisarParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ErroRepositorioException ;

	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade)	throws ErroRepositorioException ;

	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadesGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException ;

	public BigDecimal acumularValorAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	public BigDecimal acumularValorEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamento(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,int idFinanciamentoTipo, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;

	public Collection<Object[]> obterDadosResumoDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException ;

	public Object[] obterParametrosContabilDevedoresDuvidosos(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException ;

	public Collection<Integer> pesquisarIdsLancamentosContabeis(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idLancamentoOrigem)throws ErroRepositorioException ;

	public void removerItensLancamentoContabil(Integer idLancamentoContabil) throws ErroRepositorioException;

	public void removerLancamentosContabeis(Collection<Integer> colecaoIdsLancamentosContabeis) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServico(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstado(int anoMesReferencia) throws ErroRepositorioException ;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorGerenciaRegional(int anoMesReferencia) throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(int anoMesReferencia) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(int anoMesReferencia, Integer gerenciaRegional)throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegionalPorLocalidade(int anoMesReferencia, Integer gerenciaRegional)throws ErroRepositorioException ;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(int anoMesReferencia, Integer unidadeNegocio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoDevedoresDuvidososRelatorioPorLocalidade(int anoMesReferencia, Integer localidade)throws ErroRepositorioException;
	
	public void removerContasAReceberContabil(int anoMesReferenciaContabil, Integer idLocalidade)throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(int anoMesReferenciaContabil, Integer idLocalidade)throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosContasCategoriaValorImpostos(int anoMesReferenciaContabil, Integer idLocalidade)throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaServico(int anoMesReferenciaContabil, Integer idLocalidade)throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaParcelamento(int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaEntradaParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaServico(int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaPagamentoExcesso(int anoMesReferenciaContabil, Integer idLocalidade) 
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoParcelamento(int anoMesReferenciaContabil, Integer idLocalidade, Integer idCreditoOrigem)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoCondicional(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoIncondicional(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaAjusteZerarConta(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDevolucao(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaServico(int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaDocumentosEmitidos(int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosCurtoPrazo(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosLongoPrazo(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosCurtoPrazo(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosLongoPrazo(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaArrasto(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontosParcelamento(int anoMesReferenciaContabil, Integer idLocalidade, Integer idCreditoOrigem)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontosParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade, Integer idCreditoOrigem)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDevolucao(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDevolucao(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoIncondicional(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoIncondicional(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaPagamentoExcesso(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualPagamentoExcesso(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoCondicional(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoCondicional(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaAjusteZerarConta(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualAjusteZerarConta(int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<Integer> obterQuadrasPorLocalidadeParaGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, int idLocalidade) throws ErroRepositorioException ;

	public boolean verificarExistenciaClienteResponsavelConta(int idConta) throws ErroRepositorioException ;

	@SuppressWarnings("rawtypes")
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) throws ErroRepositorioException; 
	
	@SuppressWarnings("rawtypes")
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(int anoMesReferencia) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorGerenciaRegional(int anoMesReferencia) throws ErroRepositorioException;	

	@SuppressWarnings("rawtypes")
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorMunicipio(int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorMunicipio(int anoMesReferencia) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	public void removerValorVolumesConsumidosNaoFaturados(int anoMesReferenciaFaturamento, Integer idLocalidade) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosValorVolumesConsumidosNaoFaturadosAguaEsgoto(
			int anoMesReferenciaFaturamento, Integer idLocalidade, Date ultimoDiaMesCorrenteFaturamento)
			throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarDadosRelatorioSaldoContasAReceberContabil(String opcaoTotalizacao,
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException;
    
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa1(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;

    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa2(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;
    
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa3(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;

    @SuppressWarnings("rawtypes")
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;
    
    @SuppressWarnings("rawtypes")
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;

    @SuppressWarnings("rawtypes")
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;

    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaJurosCobrados(int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException;
	
    public Collection<Object[]> pesquisarDadosRelatorioParametrosContabeisFaturamento(Integer referenciaContabil) throws ErroRepositorioException;    
        
    public Collection<Object[]> pesquisarDadosRelatorioParametrosContabeisArrecadacao(Integer referenciaContabil) throws ErroRepositorioException;            
    
    public List<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisarVolumesConsumidosNaoFaturados(
    		Integer mesAno, String opcaoTotalizacao, Integer idEntidade) throws ErroRepositorioException;
	
    @SuppressWarnings("rawtypes")
    public Collection pesquisarGerarIntegracaoContabilidadeCaema(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(Integer idLocalidade) throws ErroRepositorioException;
	
    public Collection<Object[]> pesquisarDadosDebitosCobradosCategoria( Integer idLocalidade) throws ErroRepositorioException;
    
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoria(Integer idLocalidade) throws ErroRepositorioException;
    
    public Collection<Object[]> pesquisarValorPagamentoImovel(Integer idLocalidade, Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;
    
	public Localidade pesquisarUnidadeNegocioEGerenciaDaLocalidade(Integer idLocalidade)throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerarIntegracaoContabilidadeCOSANPA(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarAvisosBancariosParaGerarLancamentosContabeis(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;
	
	public ContaContabil pesquisarContaContabilPorNomeConta(String nomeConta)  throws ErroRepositorioException;
	
	public void atualizarValorContabilizado(Integer idAvisoBancario, BigDecimal valorContabilizado) throws ErroRepositorioException ;
	
	public void removerDocumentosAReceberResumo(int anoMesReferenciaRecebimentos, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarContasAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarGuiasPagamentoAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarDebitosACobrarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarCreditosARealizarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade, Session session) throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarImpostoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDividaAtivaResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDividaAtivaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoHistoricoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarImpostoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarOutrasReceitasResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoGuiaResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoPagamentoContaCredito(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoPagamentoContaServico(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoHistoricoPagamentoContaCredito(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoHistoricoPagamentoContaServico(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoReceitaAgrupadoPorBanco(ResumoReceitaHelper resumo) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoReceitaRelatorioAnalitico(ResumoReceitaHelper resumo) throws ErroRepositorioException;
	
	public BigDecimal obterValorTotalContasDevedoresDuvidosos( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString,
			Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException ;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucaoAvisoBancarioResumo(Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucaoAvisoBancarioHistoricoResumo(Date dataInicial, Date dataFinal) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra , Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra ,Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade,  Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos )  throws ErroRepositorioException ;

	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException ;

	public void atualizaContaAnoMesReferenciaContabilDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra ,
			Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos (Integer idLocalidade) throws ErroRepositorioException;
	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra)	throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException ;
	
	public void removerDocumentosAReceberFaixaResumo(int anoMesReferenciaRecebimentos, Integer idLocalidade, Session session) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerarIntegracaoContabilidadeCosama(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	public void atualizarValorBaixadoParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil, BigDecimal valorTotalValoresBaixados) throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarValorBaixadoAgrupadoPorLocalidadeResumoDevedoresDuvidosos( int anoMesReferenciaContabil )  throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizar(int anoMesReferenciaContabil, Integer idLocalidade, Integer creditoOrigem) throws ErroRepositorioException ;
	
	public Collection<Object[]> pesquisarDadosCreditosARealizarValorResidual(int anoMesReferenciaContabil, Integer idLocalidade, Integer creditoOrigem) throws ErroRepositorioException;
	
    public Collection<Object[]> pesquisarDadosRelatorioParametrosContabeisContasAReceber( Integer referenciaContabil ) throws ErroRepositorioException;  
	 
	 
}
