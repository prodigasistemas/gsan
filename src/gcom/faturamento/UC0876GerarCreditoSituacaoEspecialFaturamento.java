package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.bean.ApagarDadosFaturamentoHelper;
import gcom.faturamento.bean.DeterminarValoresFaturamentoAguaEsgotoHelper;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoARealizarCategoriaPK;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0876] - Gerar Crédito Situação Especial 
 * de Faturamento, gerando maior facilidade na manutenção do mesmo.  
 *
 * @author Raphael Rossiter
 * @date 22/01/2009
 */
public class UC0876GerarCreditoSituacaoEspecialFaturamento {

private static UC0876GerarCreditoSituacaoEspecialFaturamento instancia;
	
	@SuppressWarnings("unused")
	private IRepositorioFaturamento repositorioFaturamento;
	
	@SuppressWarnings("unused")
	private SessionContext sessionContext;

	
	private UC0876GerarCreditoSituacaoEspecialFaturamento(IRepositorioFaturamento repositorioFaturamento, 
			SessionContext sessionContext) {

		this.repositorioFaturamento = repositorioFaturamento;
		this.sessionContext = sessionContext;
	}

	public static UC0876GerarCreditoSituacaoEspecialFaturamento getInstancia(IRepositorioFaturamento repositorioFaturamento,
			SessionContext sessionContext) {
		
		if (instancia == null) {
			instancia = new UC0876GerarCreditoSituacaoEspecialFaturamento(repositorioFaturamento, sessionContext);
		}
		return instancia;
	}
	
	/**
	 * Controlador Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Controlador Micromedicao 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorMicromedicaoLocal
	 */
	protected ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Controlador Faturamento 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorFaturamentoLocal
	 */
	protected ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Controlador Util 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorUtilLocal
	 */
	protected ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 23/01/2009
	 *
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	public void gerarCreditoSituacaoEspecialFaturamentoImovel(Imovel imovel, FaturamentoGrupo faturamentoGrupo, 
			SistemaParametro sistemaParametro,int atividade) throws ControladorException {

		/*
		 * CASO O IMÓVEL ESTEJA COM A SITUAÇÃO ESPECIAL DE FATURAMENTO IGUAL 
		 * "Situação Especial de Nitrato" (Código 10) (FTST_ID da tabela IMOVEL com valor igual a 10)
		 */ 
		if (imovel.getFaturamentoSituacaoTipo() != null &&
			imovel.getFaturamentoSituacaoTipo().getId().equals(FaturamentoSituacaoTipo.NITRATO)) {

			// [UC0108] - Obter Quantidade de Economias por Categoria
			Collection colecaoCategorias = this.getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);
			Collection colecaoCategoriaOUSubcategoria = getControladorImovel().obterColecaoCategoriaOuSubcategoriaDoImovel(imovel);
			
			//Caso a atividade seja gerar dados para leitura, é gerado um credito a realizar com o valor zerado, 
			//e a situação do débito crédito fica como pré-faturada
			if(atividade == FaturamentoAtividade.GERAR_ARQUIVO_LEITURA){
				
				BigDecimal valorCredito = new BigDecimal("0.00");
				
				Integer idDebitoCreditoSituacaoAtual = DebitoCreditoSituacao.PRE_FATURADA;
					
				//GERANDO O CRÉDITO A REALIZAR
				this.gerarCreditoARealizarImovel(imovel, faturamentoGrupo.getAnoMesReferencia(), valorCredito, 
				colecaoCategorias, sistemaParametro,idDebitoCreditoSituacaoAtual);
			}else{
				
				Integer idDebitoCreditoSituacaoAtual = DebitoCreditoSituacao.NORMAL;
				
				Object[] dadosCreditoARealizar = null;
				
				try{
					dadosCreditoARealizar = repositorioFaturamento.pesquisarCreditoARealizar(imovel.getId(),CreditoTipo.CREDITO_NITRATO,
							idDebitoCreditoSituacaoAtual,faturamentoGrupo.getAnoMesReferencia());
				
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					new ControladorException("erro.sistema", ex);
				}
				
				//Caso o crédito não tenha sido atualizado, não tenha sido atualizado o crédito de nitrato por impressão simultânea,
				//atualiza o crédito de nitrato.
				if(dadosCreditoARealizar == null || dadosCreditoARealizar.equals("") ){

					//Inicializando o objeto que armazenará as informações que serão utilizadas no cálculo da conta
					DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = 
					new DeterminarValoresFaturamentoAguaEsgotoHelper();
					
					
					//Obtendo os consumos de água e esgoto do imóvel a ser faturado
					
					//LIGACAO_TIPO_AGUA
					LigacaoTipo ligacaoTipoAgua = new LigacaoTipo();
					ligacaoTipoAgua.setId(LigacaoTipo.LIGACAO_AGUA);
		
					// LIGACAO_TIPO_ESGOTO
					LigacaoTipo ligacaoTipoEsgoto = new LigacaoTipo();
					ligacaoTipoEsgoto.setId(LigacaoTipo.LIGACAO_ESGOTO);
					
					//CONSUMO_HISTORICO_AGUA
					ConsumoHistorico consumoHistoricoAgua = this.getControladorMicromedicao()
					.obterConsumoHistoricoMedicaoIndividualizada(imovel, ligacaoTipoAgua, 
					faturamentoGrupo.getAnoMesReferencia());
					
					Integer consumoAgua = null;
					ConsumoTipo consumoTipoAgua = null;
					
					if (consumoHistoricoAgua != null){
						consumoAgua = consumoHistoricoAgua.getNumeroConsumoFaturadoMes();
						consumoTipoAgua = consumoHistoricoAgua.getConsumoTipo(); 
					}
					
					//CONSUMO_HISTORICO_ESGOTO
					ConsumoHistorico consumoHistoricoEsgoto = this.getControladorMicromedicao()
					.obterConsumoHistoricoMedicaoIndividualizada(imovel, ligacaoTipoEsgoto, 
					faturamentoGrupo.getAnoMesReferencia());
					
					Integer consumoEsgoto = null;
					ConsumoTipo consumoTipoEsgoto = null;
					
					if (consumoHistoricoEsgoto != null){
						consumoEsgoto = consumoHistoricoEsgoto.getNumeroConsumoFaturadoMes();
						consumoTipoEsgoto = consumoHistoricoEsgoto.getConsumoTipo();
					}
					
					
					if (this.getControladorFaturamento().permiteFaturamentoParaAgua(imovel.getLigacaoAguaSituacao(), 
						consumoAgua, consumoTipoAgua) ||
						this.getControladorFaturamento().permiteFaturamentoParaEsgoto(imovel.getLigacaoEsgotoSituacao(), 
						consumoEsgoto, consumoTipoEsgoto)) {
						
						//Determinar Valores para Faturamento de Água e/ou Esgoto
						helperValoresAguaEsgoto = this.getControladorFaturamento()
						.determinarValoresFaturamentoAguaEsgoto(imovel, faturamentoGrupo.getAnoMesReferencia(), 
						colecaoCategoriaOUSubcategoria, faturamentoGrupo, consumoHistoricoAgua, consumoHistoricoEsgoto);
						
					}
					
					/*
					 * O sistema calcula 50% do valor calculado da água e gera um crédito a realizar para cada 
					 * um dos imóveis selecionados
					 */
					if (helperValoresAguaEsgoto.getValorTotalAgua().compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
							
						BigDecimal valorCredito = 
						helperValoresAguaEsgoto.getValorTotalAgua().divide(new BigDecimal(2));
						
						//GERANDO O CRÉDITO A REALIZAR
						this.gerarCreditoARealizarImovel(imovel, faturamentoGrupo.getAnoMesReferencia(), valorCredito, 
						colecaoCategorias, sistemaParametro,idDebitoCreditoSituacaoAtual);
					}
				}
			}
		}
	}
	
	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 23/01/2009
	 *
	 * @param imovel
	 * @param anoMesFaturamento
	 * @param valorCredito
	 * @param colecaoCategoria
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	protected void gerarCreditoARealizarImovel(Imovel imovel, Integer anoMesFaturamento, 
			BigDecimal valorCredito, Collection colecaoCategoria, SistemaParametro sistemaParametro,int idDebitoCreditoSituacaoAtual) throws ControladorException{
		
		//CREDITO_A_REALIZAR_GERAL
		//=====================================================================================================
		CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
		
		creditoARealizarGeral.setIndicadorHistorico(ConstantesSistema.NAO);
		creditoARealizarGeral.setUltimaAlteracao(new Date());
		
		//INSERINDO NA BASE
		Integer idCreditoARealizarGeral = (Integer) this
		.getControladorUtil().inserir(creditoARealizarGeral);
		
		creditoARealizarGeral.setId(idCreditoARealizarGeral);
		//=====================================================================================================
		
		//CREDITO_A_REALIZAR
		//=====================================================================================================
		CreditoARealizar creditoARealizar = new CreditoARealizar();
		
		creditoARealizar.setCreditoARealizarGeral(creditoARealizarGeral);
		creditoARealizar.setId(idCreditoARealizarGeral);
	
		//IMOVEL
		creditoARealizar.setImovel(imovel);
		
		//CREDITO_TIPO
		CreditoTipo creditoTipo = new CreditoTipo();
		creditoTipo.setId(CreditoTipo.CREDITO_NITRATO);
		
		creditoARealizar.setCreditoTipo(creditoTipo);
		
		//GERACAO_CREDITO
		creditoARealizar.setGeracaoCredito(new Date());
		
		//REFERENCIA_CREDITO
		creditoARealizar.setAnoMesReferenciaCredito(anoMesFaturamento);
		
		//REFERENCIA COBRANCA
		creditoARealizar.setAnoMesCobrancaCredito(sistemaParametro.getAnoMesArrecadacao());
		
		//REFERENCIA CONTABIL
		creditoARealizar.setAnoMesReferenciaContabil(sistemaParametro.getAnoMesFaturamento());
		
		//VALOR CREDITO
		creditoARealizar.setValorCredito(valorCredito);
		
		//VALOR RESIDUAL MES ANTERIOR
		creditoARealizar.setValorResidualMesAnterior(BigDecimal.ZERO);
		
		//NUMERO PRESTACAO
		creditoARealizar.setNumeroPrestacaoCredito(new Short("1"));
		
		//PRESTACOES REALIZADAS
		creditoARealizar.setNumeroPrestacaoRealizada(new Short("0"));
		
		//LOCALIDADE
		creditoARealizar.setLocalidade(imovel.getLocalidade());
		
		//QUADRA
		creditoARealizar.setQuadra(imovel.getQuadra());
		
		//CODIGO_SETOR_COMERCIAL
		creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
		
		//NUMERO_QUADRA
		creditoARealizar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
		
		//LOTE
		creditoARealizar.setNumeroLote(imovel.getLote());
		
		//SUBLOTE
		creditoARealizar.setNumeroSubLote(imovel.getSubLote());
		
		//LANCAMENTO_ITEM_CONTABIL
		LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
		lancamentoItemContabil.setId(LancamentoItemContabil.ACRESCIMOS_POR_IMPONTUALIDADE);
		
		creditoARealizar.setLancamentoItemContabil(lancamentoItemContabil);
		
		//DEBITO_CREDITO_SITUACAO_ATUAL
		DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
		debitoCreditoSituacaoAtual.setId(idDebitoCreditoSituacaoAtual);
		
		creditoARealizar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
		
		//CREDITO_ORIGEM
		CreditoOrigem creditoOrigem = new CreditoOrigem();
		creditoOrigem.setId(CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE);
		
		creditoARealizar.setCreditoOrigem(creditoOrigem);
		
		creditoARealizar.setUltimaAlteracao(new Date());
		
		//INSERINDO NA BASE
		this.getControladorUtil().inserir(creditoARealizar);
		//=====================================================================================================
		
		
		//CREDITO_A_REALIZAR_CATEGORIA
		//=====================================================================================================
		
		if (colecaoCategoria != null && !colecaoCategoria.isEmpty()){
			
			Iterator iteratorCategoria = colecaoCategoria.iterator();
			
			//[UC0185] - Obter Valor por Categoria
			Collection colecaoValorPorCategoria = this.getControladorImovel()
			.obterValorPorCategoria(colecaoCategoria, valorCredito);
			
			Iterator iteratorValorPorCategoria = colecaoValorPorCategoria.iterator();
			
			while (iteratorCategoria.hasNext()){
				
				Categoria categoria = (Categoria) iteratorCategoria.next();
				
				BigDecimal valorPorCategoria = (BigDecimal) iteratorValorPorCategoria.next();
				
				CreditoARealizarCategoria creditoARealizarCategoria = new CreditoARealizarCategoria();
				
				//PK
				CreditoARealizarCategoriaPK creditoARealizarCategoriaPK = new CreditoARealizarCategoriaPK();
				creditoARealizarCategoriaPK.setCategoriaId(categoria.getId());
				creditoARealizarCategoriaPK.setCreditoARealizarId(creditoARealizar.getId());
				
				creditoARealizarCategoria.setComp_id(creditoARealizarCategoriaPK);
				
				//CREDITO_A_REALIZAR
				creditoARealizarCategoria.setCreditoARealizar(creditoARealizar);
				
				//CATEGORIA
				creditoARealizarCategoria.setCategoria(categoria);
				
				//QUANTIDADE_ECONOMIAS_CATEGORIA
				creditoARealizarCategoria.setQuantidadeEconomia(categoria.getQuantidadeEconomiasCategoria());
				
				//VALOR_POR_CATEGORIA
				creditoARealizarCategoria.setValorCategoria(valorPorCategoria);
				
				creditoARealizarCategoria.setUltimaAlteracao(new Date());
				
				//INSERINDO NA BASE
				this.getControladorUtil().inserir(creditoARealizarCategoria);
				
			}
		}
		//=====================================================================================================
	}
	
	
	/**
	 * [UC0876] - Gerar Crédito Situação Especial Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 27/01/2009
	 *
	 * @param helper
	 * @throws ControladorException
	 */
	public void apagarDadosGeradosCreditoSituacaoEspecialFaturamento(ApagarDadosFaturamentoHelper helper) 
		throws ControladorException {

			
		/* 
		 * Retorna a quantidade de créditos a realizar existentes para uma rota em um determinado anoMês de 
		 * referência e de acordo com o tipo de crédito recebido.
		 */
		Integer quantidadeCreditosARealizar = null;
		try {
				
			quantidadeCreditosARealizar = repositorioFaturamento.quantidadeCreditosARealizarRota(
			helper.getAnoMesFaturamento(), helper.getRota(), helper.getIdCreditoTipo());
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (quantidadeCreditosARealizar != null && quantidadeCreditosARealizar.intValue() > 0) {

			/*
			 * Caso a situação atual NÃO seja PRÉ-FATURAMENTO:
			 * 
			 * DELETAR apenas os créditos com data de geração entre a data atual menos 3 dias e a data atual
			 * Desenvolvedor: Raphael Rossiter em 27/01/2009 
			 * Analista: Aryed Lins
			 */
			if (!helper.getIdDebitoCreditoSituacaoAtual().equals(DebitoCreditoSituacao.PRE_FATURADA)){
					
				helper.setDataEmissaoInicial(Util.subtrairNumeroDiasDeUmaData(new Date(), 3));
				helper.setDataEmissaoFinal(Util.adaptarDataFinalComparacaoBetween(new Date()));
			}
				
			try {
					
				// deleta CREDITO_A_REALIZAR_CATEGORIA
				repositorioFaturamento.apagarCreditoARealizarCategoria(helper);
				
				// update CREDITO_A_REALIZAR_GERAL
				repositorioFaturamento.atualizarCreditoARealizarGeral(helper);

				// deleta CREDITO_A_REALIZAR
				repositorioFaturamento.apagarCreditoARealizar(helper);

				// delete CREDITO_A_REALIZAR_GERAL
				repositorioFaturamento.apagarCreditoARealizarGeral(helper);

			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
		} 
	}
}
