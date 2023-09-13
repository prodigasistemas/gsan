package gcom.financeiro;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.IRepositorioArrecadacao;
import gcom.arrecadacao.RecebimentoTipo;
import gcom.arrecadacao.RepositorioArrecadacaoHBM;
import gcom.arrecadacao.ResumoArrecadacao;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.ProcessoIniciado;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.ControladorLocalidadeLocal;
import gcom.cadastro.localidade.ControladorLocalidadeLocalHome;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.financeiro.bean.AcumularValoresHelper;
import gcom.financeiro.bean.GerarIntegracaoContabilidadeHelper;
import gcom.financeiro.bean.GerarResumoDevedoresDuvidososHelper;
import gcom.financeiro.bean.ResumoDevedoresDuvidososRelatorioHelper;
import gcom.financeiro.lancamento.IRepositorioLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoContabil;
import gcom.financeiro.lancamento.LancamentoContabilItem;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.financeiro.lancamento.RepositorioLancamentoItemContabilHBM;
import gcom.relatorio.contasareceber.RelatorioParametrosContabeisContasAReceberBean;
import gcom.relatorio.financeiro.RelatorioEvolucaoContasAReceberContabilBean;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisArrecadacaoBean;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisFaturamentoBean;
import gcom.relatorio.financeiro.RelatorioSaldoContasAReceberContabilBean;
import gcom.relatorio.financeiro.RelatorioVolumesConsumidosNaoFaturadosBean;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.IoUtil;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.hibernate.Session;
import org.jboss.logging.Logger;

public class ControladorFinanceiro implements SessionBean {
	
	private static Logger logger = Logger.getLogger(ControladorFinanceiro.class);
	
	protected static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	protected IRepositorioFinanceiro repositorioFinanceiro = null;
	protected IRepositorioLancamentoItemContabil repositorioLancamentoItemContabil = null;
	protected IRepositorioFaturamento repositorioFaturamento = null;
	protected IRepositorioArrecadacao repositorioArrecadacao = null;

	public void ejbCreate() throws CreateException {

		repositorioFinanceiro = RepositorioFinanceiroHBM.getInstancia();
		repositorioLancamentoItemContabil = RepositorioLancamentoItemContabilHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioArrecadacao = RepositorioArrecadacaoHBM.getInstancia();

	}

	public void ejbRemove() {
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	protected ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
    private ControladorEnderecoLocal getControladorEndereco() {

        ControladorEnderecoLocalHome localHome = null;
        ControladorEnderecoLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorEnderecoLocalHome) locator
                    .getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
            local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }    
    
	protected ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorLocalidadeLocal getControladorLocalidade() {
		ControladorLocalidadeLocalHome localHome = null;
		ControladorLocalidadeLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorLocalidadeLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}	
	
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * [UC0175] - Gerar Lan�amentos Cont�beis do Faturamento
	 * Author: Raphael Rossiter, Pedro Alexandre 
	 * Data: 16/01/2006, 23/05/2007
	 * 
	 * Gera os lan�amentos cont�beis a partir dos dados selecionados na tabela RESUMO_FATURAMENTO
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarLancamentosContabeisFaturamento(
		Integer anoMesReferenciaFaturamento, 
		Integer idLocalidade, 
		int idFuncionalidadeIniciada) 
		throws ControladorException{
		
		//[FS0001 - Validar ano/m�s do Faturamento]
		Integer anoMesFaturamentoAtual =  
			getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento();
		
		if(anoMesReferenciaFaturamento.intValue() >anoMesFaturamentoAtual.intValue()){
			//levanta a exce��o para a pr�xima camada
			throw new ControladorException("atencao.mes_ano.faturamento.inferior",
				null,
				Util.formatarAnoMesParaMesAno(anoMesFaturamentoAtual.toString()));
		}

		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = 
			getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada,
				UnidadeProcessamento.LOCALIDADE,
				(idLocalidade));
		
		try {
			
			/*
			 *  Pesquisa os dados do resumo do faturamento para o ano/m�s de refer�ncia atual e 
			 *  para a localidade informada.
			 *  
			 *  0 - id da localidade
			 *  1 - id do tipo de lan�amento
			 *  2 - id do item de lan�amento
			 *  3 - id do item de lan�amento cont�bil
			 *  4 - id da categoria
			 *  5 - soma do valor do resumo do faturamento 
			 */
			Collection<Object[]> colecaoDadosResumoFaturamento = 
				repositorioFinanceiro.obterDadosResumoFaturamento(
					anoMesReferenciaFaturamento, 
					idLocalidade);
		
			/*
			 * Caso exista resumo de faturamento para a localidade e o ano/m�s 
			 * cria o lancamento cont�bil junto com seus items 
			 * para cada conjunto de mesmo tipo de lan�amento
			 */
			if (colecaoDadosResumoFaturamento != null && !colecaoDadosResumoFaturamento.isEmpty()){
				
				//flag utilizada somente quando for a primeira entrada
				boolean flagPrimeiraVez = true;
				int idTipoLancamentoTemp = -1;
				Collection<Object[]> colecaoDadosResumoPorTipoLancamento = new ArrayList();
				
				// defini��o da origem do lan�amento
				LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
				lancamentoOrigem.setId(LancamentoOrigem.FATURAMENTO);
				
				//Cria a vari�vel que vai armazenar o lan�amento cont�bil
				LancamentoContabil lancamentoContabilInsert = null;
				
				//la�o para gerar os lan�amentos por grupo de tipo de lan�amento
				for(Object[] dadosResumoFaturamento : colecaoDadosResumoFaturamento){
					
					//recupera o tipo de lan�amento atual 
					Integer idTipoLancamento = (Integer) dadosResumoFaturamento[1];
					
					/*
					 * Caso seja a primeira entrada do "for"
					 * adiciona os dados a cole��o e atualiza o item temporario
					 * criando tamb�m o lan�amento contabil que ira ser inserindo
					 * junto com seus items.
					 * 
					 *  Caso contr�rio (n�o seja a primeira entrada do la�o "for")
					 *  verifica se o item de lan�amento mudou, caso n�o tenha mudado 
					 *  adiciona os dados ao conjunto do mesmo item
					 *  caso contr�rio, se mudou o item de lan�amento o conjunto est� fechado
					 *  para o lan�amento cont�bil e chama o me�todo para inserir o
					 *  lan�amento cont�bil junto com seus itens. 
					 */
					if(flagPrimeiraVez){
						colecaoDadosResumoPorTipoLancamento.add(dadosResumoFaturamento);
						flagPrimeiraVez = false;
						idTipoLancamentoTemp = idTipoLancamento;
						
						LancamentoTipo tipoLancamento = new LancamentoTipo();
						tipoLancamento.setId(idTipoLancamento);
						
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidade);
						
						//cria o lan�amento cont�bil para ser inserido 
						lancamentoContabilInsert = new LancamentoContabil();
						lancamentoContabilInsert.setAnoMes(anoMesReferenciaFaturamento);
						lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
						lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
						lancamentoContabilInsert.setLocalidade(localidade);
						lancamentoContabilInsert.setRecebimentoTipo(null);
						lancamentoContabilInsert.setUltimaAlteracao(new Date());
					}else{
						/*
						 * Caso ainda seja o mesmo item adicona os dados para 
						 * ser gerado os itens do lan�amento para o mesmo lan�amento.
						 * Caso contr�rio chama o met�do para inseri os itens e o lan�amento cont�bil.
						 */
						if(idTipoLancamento == idTipoLancamentoTemp){
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoFaturamento);
						}else{
							/* met�do para inserir o lan�amento cont�bil assim como seus itens */
							this.inserirLancamentoContabilItemFaturamento(
								lancamentoContabilInsert, 
								colecaoDadosResumoPorTipoLancamento);
							
							//limpaa cole��o e adiciona os dados do resumo atual
							colecaoDadosResumoPorTipoLancamento.clear();
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoFaturamento);
							
							LancamentoTipo tipoLancamento = new LancamentoTipo();
							tipoLancamento.setId(idTipoLancamento);
							
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							
							//cria o lan�amento cont�bil que vai ser inserido
							lancamentoContabilInsert = new LancamentoContabil();
							lancamentoContabilInsert.setAnoMes(anoMesReferenciaFaturamento);
							lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
							lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
							lancamentoContabilInsert.setLocalidade(localidade);
							lancamentoContabilInsert.setRecebimentoTipo(null);
							lancamentoContabilInsert.setUltimaAlteracao(new Date());
							
							//atualiza o tipo de lan�amento tempor�rio com o novo valor
							idTipoLancamentoTemp = idTipoLancamento;
						}
					}
				}
				
				/*
				 * �ltimo registro
				 * Esse "if" � para verificar se ainda existe um �ltimo registro na cole��o
				 * caso exista algum item, adiciona o lan�amento cont�bil junto com o item. 
				 */
				if(colecaoDadosResumoPorTipoLancamento != null && colecaoDadosResumoPorTipoLancamento.size() > 0 ){
					this.inserirLancamentoContabilItemFaturamento(
						lancamentoContabilInsert, 
						colecaoDadosResumoPorTipoLancamento);
					colecaoDadosResumoPorTipoLancamento = null;
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * Gera o lan�amento cont�bil junto com seus itens. 
	 *
	 * [UC0175] - Gerar Lan�amentos Cont�beis do Faturamento
	 *
	 * @author Pedro Alexandre
	 * @date 24/05/2007
	 *
	 * @param lancamentoContabil
	 * @param colecaoDadosResumoPorTipoLancamento
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void inserirLancamentoContabilItemFaturamento(LancamentoContabil lancamentoContabil,Collection<Object[]> colecaoDadosResumoPorTipoLancamento) throws ControladorException {
		try{
			/*
			 * Caso exista dados para os itens do resumo do faturamento.
			 */
			if(colecaoDadosResumoPorTipoLancamento != null && !colecaoDadosResumoPorTipoLancamento.isEmpty()){
				
				Collection colecaoLancamentoContabilItem = new ArrayList();
				
				//flag que indica se o lan�amento cont�bil j� foi inserido ou n�o. 
				boolean flagInseridoLancamentoContabil = false;
				
				/*
				 * Dados do resumo do faturamento
				 *  0 - id da localidade
				 *  1 - id do tipo de lan�amento
				 *  2 - id do item de lan�amento
				 *  3 - id do item de lan�amento cont�bil
				 *  4 - id da categoria
				 *  5 - soma do valor do resumo do faturamento
				 */
				for(Object[] dadosResumoFaturamento : colecaoDadosResumoPorTipoLancamento){
					
					//recupera os dados do resumo do faturamento
					Integer idLancamentoTipo =         (Integer) dadosResumoFaturamento[1];
					Integer idLancamentoItem =         (Integer) dadosResumoFaturamento[2];
					Integer idLancamentoItemContabil = (Integer) dadosResumoFaturamento[3];
					Integer idCategoria =              (Integer) dadosResumoFaturamento[4];
					BigDecimal valorLancamento =       (BigDecimal) dadosResumoFaturamento[5]; 
					
					/* 
					 * Verifica se existe conta cont�bil para o item que vai ser inserido 
					 * 
					 * 0 - id conta cont�bil do d�bito
					 * 1 - id conta cont�bil cr�dito 
					 * 2 - descri��o do hist�rico do d�bito
					 * 3 - descri��o do hist�rico do cr�dito
					 */
					Object[] dadosContaContabil = this.repositorioFinanceiro.obterParametrosContabilFaturamento(idCategoria, idLancamentoItemContabil, idLancamentoItem, idLancamentoTipo);
					
					if(dadosContaContabil != null){
						Integer idLancamentoContabil = null;
						/*
						 * Caso exista dados para a conta cont�bil e o item cont�bil 
						 * ainda n�o foi inserido 
						 * inseri o item cont�bil na base.
						 */
						if(!flagInseridoLancamentoContabil){
							idLancamentoContabil = (Integer)getControladorUtil().inserir(lancamentoContabil);
							lancamentoContabil.setId(idLancamentoContabil);
							flagInseridoLancamentoContabil = true;
						}
						
						//recupera os dados da conta cont�bil para o item do resumo do faturamento.
						Integer idContaContabilDebito = (Integer) dadosContaContabil[0];
						Integer idContaContabilCredito = (Integer) dadosContaContabil[1];
						String descricaoHistoricoDebito = (String) dadosContaContabil[2];
						String descricaoHistoricoCredito = (String) dadosContaContabil[3];
						
						//cria os indicadores de d�bito e cr�dito.
						Short indicadorDebito = new Short("2"); 
						Short indicadorCredito = new Short("1");
						
						Date ultimaAlteracao = new Date();
						
						//cria as contas cont�beis de cr�dito e d�bito.
						ContaContabil contaContabilCredito = new ContaContabil();
						contaContabilCredito.setId(idContaContabilCredito);
						
						ContaContabil contaContabilDebito = new ContaContabil();
						contaContabilDebito.setId(idContaContabilDebito);
						
						/**  Item de cr�dito */
						LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
								valorLancamento, 
								descricaoHistoricoCredito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilCredito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
						
						/** Item de d�bito */
						LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
								valorLancamento, 
								descricaoHistoricoDebito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilDebito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
						
					}
				}
				//inserios itens de lan�amento cont�beis. 
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoLancamentoContabilItem); 
			}
	
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}
	
	
	public void inserirLancamentoContabilItem(LancamentoContabil lancamentoContabil, int etapa, 
			BigDecimal valorTotalResidencial, BigDecimal valorTotalComercial, BigDecimal valorTotalIndustrial,
			BigDecimal valorTotalPublico, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemContabil, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemCategoria) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		
		//Utilizado dentro do switch
		LancamentoItem lancamentoItem = new LancamentoItem();
		Categoria categoria = new Categoria();
        
		switch (lancamentoContabil.getLancamentoTipo().getId().intValue()) {
		
			case LancamentoTipo.AGUA_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
								
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("111"), valorTotalResidencial, null, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);				
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = COMERCIAL 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("112"), null, valorTotalComercial, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 113
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("113"), null, null, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 114
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("114"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				}
			
				break;
			
				
			case LancamentoTipo.ESGOTO_INT:

				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);					
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);					
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 211
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("211"), valorTotalResidencial, null, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);					
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 212
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = COMERCIAL 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("212"), null, valorTotalComercial, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 213
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("213"), null, null, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);					
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 214
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("214"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);				
				
				}
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_INCLUIDOS_CURTO_PRAZO_INT:
				
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else {
					
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}

				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_INCLUIDOS_LONGO_PRAZO_INT:
				
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
				
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else {
					
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}

				
				break;

				
			case LancamentoTipo.FATURAMENTO_ADICIONAL_GUIA_PAGAMENTO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else {
					
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_CANCELADOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_CANCELADOS_LONGO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				
				break;
	
				
			case LancamentoTipo.CANCELAMENTOS_POR_REFATURAMENTO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 113
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 114
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 07
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 211
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 08
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 212
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 09
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 213
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 10
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 214
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 11
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 12
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 14
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 15
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_COBRADOS_SUP_CANCELAMENTOS_POR_REFATURAMENTO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 113
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 114
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 07
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 211
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 08
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 212
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 10
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 214
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 11
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 12
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 14
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 15
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 16
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 17
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 18
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.INCLUSOES_POR_REFATURAMENTO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 113
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 114
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 07
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 08
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 09
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 211
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 10
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 212
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 11
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 213
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 12
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 214
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 13
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 14
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 15
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_COBRADOS_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_TRANSFERIDOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_REALIZADOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 07
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 08
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 09
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 10
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 11
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 12
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 13
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 14
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 15
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 16
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 17
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 18
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) + PUBLICO 
				*/
				else if (etapa == 18){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 19, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 19
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) 
				*/
				else if (etapa == 19){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_CREDITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 20, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 20
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito =  D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) 
				*/
				else if (etapa == 20){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 21, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 21
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 21){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 22, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 23
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) + PUBLICO 
				*/
				else if (etapa == 22){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 23, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 23
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 23){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 24, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 24
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA) + PUBLICO 
				*/
				else if (etapa == 24){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 25, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 25
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 25){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 26, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 26
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_REALIZADOS_LONGO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 07
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 08
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 09
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 10
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 11
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 151
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 12
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 152
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 13
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 14
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 15
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 16
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 17
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 18
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) + PUBLICO 
				*/
				else if (etapa == 18){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 19, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 19
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) 
				*/
				else if (etapa == 19){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 20, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 20
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) 
				*/
				else if (etapa == 20){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 21, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 21
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 21){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 22, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 22
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_CANCELADOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 113
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 114
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 07
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 211
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 08
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 212
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 09
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 213
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 10
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 214
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 11
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 12
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
					
				}
				
				/*
				* ETAPA N� 14
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 15
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 16
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 17
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 18
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_CANCELADOS_LONGO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 113
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INSDUSTRIAL 
				*/
				else if (etapa == 3) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 114
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 05
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 06
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 07
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 211
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 08
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 212
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 09
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 213
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 9) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 10
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 214
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 11
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 12
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do �tem de lan�amento cont�bil
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = Valor acumulado para cada �tem de lan�amento cont�bil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
					
				}
				
				/*
				* ETAPA N� 14
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 15
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 16
				* Raz�o Cont�bil = 31 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 17
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 18
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_COBRADOS_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_TRASFERIDOS_PARA_CURTO_PRAZO_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 13 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.DEBITOS_ANTERIORES_COBRADOS_INT:
				
				/*
				* ETAPA N� 01
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 111
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 02
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 112
				* Indicador D�bito/Cr�dito = D�bito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 03
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 121
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para pr�xima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA N� 04
				* Raz�o Cont�bil = 12 e Conta Cont�bil = 122
				* Indicador D�bito/Cr�dito = Cr�dito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			default:
			
				break;
		}
	}
	
	
	
	/**
	 * [UC00175] - Gerar Lan�amentos Cont�beis
	 * Author: Raphael Rossiter
	 * Data: 17/01/2006
	 * 
	 * Auxilia no cadastramento de um objeto do tipo LANCAMENTO_CONTABIL_ITEM quando a situa��o
	 * exige que o valor do faturamento seja acumulado por CATEGORIA.
	 *
	 * @param lancamentoContabil
	 * @param numeroRazao
	 * @param numeroConta
	 * @param valorTotalResidencial
	 * @param valorTotalComercial
	 * @param valorTotalIndustrial
	 * @param valorTotalPublico
	 * @param indicadorDebitoCredito
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemAcumulandoCategoria(LancamentoContabil lancamentoContabil, 
			Short numeroRazao, Integer numeroConta, BigDecimal valorTotalResidencial, BigDecimal valorTotalComercial, 
			BigDecimal valorTotalIndustrial, BigDecimal valorTotalPublico, Short indicadorDebitoCredito) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		BigDecimal valorItemFaturamento = new BigDecimal("0.00");
		
		try {

			contaContabil = repositorioFinanceiro.obterContaContabil(numeroRazao, numeroConta);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		if (valorTotalResidencial != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalResidencial);
		}
		
		if (valorTotalComercial != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalComercial);
		}
		
		if (valorTotalIndustrial != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalIndustrial);
		}
		
		if (valorTotalPublico != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalPublico);
		}
		
		
		lancamentoContabilItem.setContaContabil(contaContabil);
		lancamentoContabilItem.setValorLancamento(valorItemFaturamento);
		lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
	
	
		this.getControladorUtil().inserir(lancamentoContabilItem);

	}
	
	
	
	/**
	 * [UC00175] - Gerar Lan�amentos Cont�beis
	 * Author: Raphael Rossiter
	 * Data: 17/01/2006
	 * 
	 * Auxilia no cadastramento de um ou v�rios objetos do tipo LANCAMENTO_CONTABIL_ITEM quando a situa��o
	 * exige que o valor do faturamento seja acumulado por LANCAMENTO_ITEM_CONTABIL.
	 * 
	 * @param lancamentoContabil
	 * @param colecaoAcumularValoresPorLancamentoItemContabil
	 * @param indicadorCreditoDebito
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	protected void inserirLancamentoContabilItemAcumulandoItems(LancamentoContabil lancamentoContabil, 
			Collection<AcumularValoresHelper> colecaoAcumularValoresPorLancamentoItemContabil, 
			Short indicadorDebitoCredito) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		
		Iterator colecaoAcumularValoresPorLancamentoItemContabilIt = 
		colecaoAcumularValoresPorLancamentoItemContabil.iterator();
			
		AcumularValoresHelper acumularValoresPorLancamentoItemContabil = null;
			
		LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
			
		while (colecaoAcumularValoresPorLancamentoItemContabilIt.hasNext()){
			
			acumularValoresPorLancamentoItemContabil = (AcumularValoresHelper)
			colecaoAcumularValoresPorLancamentoItemContabilIt.next();
				
			lancamentoItemContabil.setId(acumularValoresPorLancamentoItemContabil
			.getIdLancamentoItemContabil());
				
			try {

				contaContabil = repositorioFinanceiro.obterContaContabil(lancamentoItemContabil);
					
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
				
			lancamentoContabilItem.setContaContabil(contaContabil);
			lancamentoContabilItem.setValorLancamento(acumularValoresPorLancamentoItemContabil
			.getValorItemFaturamento());
			lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
				
				
			this.getControladorUtil().inserir(lancamentoContabilItem);
		}
	}
	
	
	
	/**
	 * 
	 * @param lancamentoContabil
	 * @param numeroRazao
	 * @param numeroConta
	 * @param colecaoAcumularValoresPorLancamentoItemContabilCategoria
	 * @param lancamentoItem
	 * @param categoria1
	 * @param categoria2
	 * @param categoria3
	 * @param indicadorDebitoCredito
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	protected void inserirLancamentoContabilItemAcumulandoItemsCategorias(LancamentoContabil lancamentoContabil, 
			Short numeroRazao, Integer numeroConta, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemContabilCategoria, 
			LancamentoItem lancamentoItem, Categoria categoria1, Categoria categoria2,
			Categoria categoria3, Short indicadorDebitoCredito) 
			throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		
		try {

			contaContabil = repositorioFinanceiro.obterContaContabil(numeroRazao, numeroConta);
				
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		Iterator colecaoAcumularValoresPorLancamentoItemContabilCategoriaIt = 
		colecaoAcumularValoresPorLancamentoItemContabilCategoria.iterator();
			
		AcumularValoresHelper acumularValoresPorLancamentoItemContabilCategoria = null;

		//Para os casos que trabalham com mais de uma categoria
		BigDecimal valorAcumuladoLancamentoItemCategoria = new BigDecimal("0.00");
		
		while (colecaoAcumularValoresPorLancamentoItemContabilCategoriaIt.hasNext()){
			
			acumularValoresPorLancamentoItemContabilCategoria = (AcumularValoresHelper)
			colecaoAcumularValoresPorLancamentoItemContabilCategoriaIt.next();
				
			
			//Para acumular com tr�s categorias
			if (categoria2 != null && categoria3 != null){

				if (acumularValoresPorLancamentoItemContabilCategoria.getIdLancamentoItem().equals(
					lancamentoItem.getId()) && 
					(acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria1.getId()) || 
					acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria2.getId()) || 
					acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria3.getId()))){
					
					
					valorAcumuladoLancamentoItemCategoria.add(acumularValoresPorLancamentoItemContabilCategoria
					.getValorItemFaturamento());
				
				}
			}
			//Para acumular com apenas uma categoria
			else{
			
				if (acumularValoresPorLancamentoItemContabilCategoria.getIdLancamentoItem().equals(
					lancamentoItem.getId()) && 
					acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria1.getId())){
						
					lancamentoContabilItem.setContaContabil(contaContabil);
					lancamentoContabilItem.setValorLancamento(acumularValoresPorLancamentoItemContabilCategoria
					.getValorItemFaturamento());
					lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
						
					this.getControladorUtil().inserir(lancamentoContabilItem);
						
					break;
						
				}
			}
		}
		
		
		//Para os casos que trabalham com mais de uma categoria
		if (categoria2 != null && categoria3 != null){

			lancamentoContabilItem.setContaContabil(contaContabil);
			lancamentoContabilItem.setValorLancamento(valorAcumuladoLancamentoItemCategoria);
			lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
				
			this.getControladorUtil().inserir(lancamentoContabilItem);
		}
	}
	
	
	
	@SuppressWarnings("rawtypes")
	protected void inserirLancamentoContabilItemAcumulandoItemEspecifico(LancamentoContabil lancamentoContabil, 
			Short numeroRazao, Integer numeroConta, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemContabil, LancamentoItem lancamentoItem, 
			Short indicadorDebitoCredito) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		
		try {

			contaContabil = repositorioFinanceiro.obterContaContabil(numeroRazao, numeroConta);
				
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		
		Iterator colecaoAcumularValoresPorLancamentoItemContabilIt = 
		colecaoAcumularValoresPorLancamentoItemContabil.iterator();
			
		AcumularValoresHelper acumularValoresPorLancamentoItemContabil = null;
			
		BigDecimal valorItemFaturamento = new BigDecimal("0.00");
		
			
		while (colecaoAcumularValoresPorLancamentoItemContabilIt.hasNext()){
			
			acumularValoresPorLancamentoItemContabil = (AcumularValoresHelper)
			colecaoAcumularValoresPorLancamentoItemContabilIt.next();
				
			if (acumularValoresPorLancamentoItemContabil.getIdLancamentoItem().equals(
				lancamentoItem.getId())){
			
				valorItemFaturamento.add(acumularValoresPorLancamentoItemContabil
				.getValorItemFaturamento());
				
			}
		}
		
		
		if (!valorItemFaturamento.equals(new BigDecimal("0.00"))){
		
			lancamentoContabilItem.setContaContabil(contaContabil);
			lancamentoContabilItem.setValorLancamento(valorItemFaturamento);
			lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
			
			this.getControladorUtil().inserir(lancamentoContabilItem);
			
		}
		
	}

	/**
	 * Pesquisa uma cole��o de lan�amento de item cont�bil
	 * 	 
	 * @return Cole��o de Lan�amentos de Item Cont�bil 
	 * @exception ErroRepositorioException   Erro no hibernate
	 */
	public Collection<LancamentoItemContabil> pesquisarLancamentoItemContabil() throws ControladorException{
		try{
			//pesquisa os lan�amentos de item cont�bil existentes no sisitema
			return repositorioLancamentoItemContabil.pesquisarLancamentoItemContabil();
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				
				//levanta a exce��o para a pr�xima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	/**
	 * Gera Lan�amentos Cont�beis do Faturamento
	 *
	 * [UC000348] - Gerar Lan�amento Cont�beis da Arrecada��o
	 *
	 * @author Rafael Santos, Pedro Alexandre
	 * @date 22/05/2006, 25/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @throws ControladorException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarLancamentoContabeisArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException {
		
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		 * 
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));
		
		try {
			Integer anoMesArrecadacaoAtual =  getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao();
			if(anoMesReferenciaArrecadacao.intValue() >anoMesArrecadacaoAtual.intValue()){
				//levanta a exce��o para a pr�xima camada
				throw new ControladorException("atencao.mes_ano.arrecadacao.inferior",null,Util.formatarAnoMesParaMesAno(anoMesArrecadacaoAtual.toString()));
			}
			
			/*
			 *  Pesquisa os dados do resumo da arrecada��o para o ano/m�s de refer�ncia atual e 
			 *  para a localidade informada.
			 *  
			 *  0 - id da localidade
			 *  1 - id do tipo de recebimento
			 *  2 - id do tipo de lan�amento
			 *  3 - id do item de lan�amento
			 *  4 - id do item de lan�amento cont�bil
			 *  5 - id da categoria
			 *  6 - soma do valor do resumo da arrecada��o 
			 */
			Collection<Object[]> colecaoDadosResumoArrecadacao = repositorioFinanceiro.obterDadosResumoArrecadacao(anoMesReferenciaArrecadacao, idLocalidade);
		
			/*
			 * Caso exista resumo da arrecada��o para a localidade e o ano/m�s 
			 * cria o lan�amento cont�bil junto com seus items 
			 * para cada conjunto de mesmo tipo de lan�amento
			 */
			if (colecaoDadosResumoArrecadacao != null && !colecaoDadosResumoArrecadacao.isEmpty()){
				
				//flag utilizada somente quando for a primeira entrada
				boolean flagPrimeiraVez = true;
				int idTipoLancamentoTemp = -1;
				Collection<Object[]> colecaoDadosResumoPorTipoLancamento = new ArrayList();
				
				// defini��o da origem do lan�amento
				LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
				lancamentoOrigem.setId(LancamentoOrigem.ARRECADACAO);
				
				//Cria a vari�vel que vai armazenar o lan�amento cont�bil
				LancamentoContabil lancamentoContabilInsert = null;
				
				//la�o para gerar os lan�amentos por grupo de tipo de lan�amento
				for(Object[] dadosResumoArrecadacao : colecaoDadosResumoArrecadacao){
					
					//recupera o id do tipo de recebimento
					Integer idRecebimentoTipo = (Integer) dadosResumoArrecadacao[1];
					
					//recupera o tipo de lan�amento atual 
					Integer idTipoLancamento = (Integer) dadosResumoArrecadacao[2];
					
					/*
					 * Caso seja a primeira entrada do "for"
					 * adiciona os dados a cole��o e atualiza o item temporario
					 * criando tamb�m o lan�amento contabil que ira ser inserindo
					 * junto com seus items.
					 * 
					 *  Caso contr�rio (n�o seja a primeira entrada do la�o "for")
					 *  verifica se o item de lan�amento mudou, caso n�o tenha mudado 
					 *  adiciona os dados ao conjunto do mesmo item
					 *  caso contr�rio, se mudou o item de lan�amento o conjunto est� fechado
					 *  para o lan�amento cont�bil e chama o m�todo para inserir o
					 *  lan�amento cont�bil junto com seus itens. 
					 */
					if(flagPrimeiraVez){
						colecaoDadosResumoPorTipoLancamento.add(dadosResumoArrecadacao);
						flagPrimeiraVez = false;
						idTipoLancamentoTemp = idTipoLancamento;
						
						RecebimentoTipo recebimentoTipo = new RecebimentoTipo();
						recebimentoTipo.setId(idRecebimentoTipo);
						
						LancamentoTipo tipoLancamento = new LancamentoTipo();
						tipoLancamento.setId(idTipoLancamento);
						
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidade);
						
						//cri o lan�amento cont�bil que vai ser inserido
						lancamentoContabilInsert = new LancamentoContabil();
						lancamentoContabilInsert.setAnoMes(anoMesReferenciaArrecadacao);
						lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
						lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
						lancamentoContabilInsert.setLocalidade(localidade);
						lancamentoContabilInsert.setRecebimentoTipo(recebimentoTipo);
						lancamentoContabilInsert.setUltimaAlteracao(new Date());
					}else{
						/*
						 * Caso ainda seja o mesmo item adicona os dados para 
						 * ser gerado os itens do lan�amento para o mesmo lan�amento.
						 * Caso contr�rio chama o met�do para inseri os itens e o lan�amento cont�bil.
						 */
						if(idTipoLancamento == idTipoLancamentoTemp){
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoArrecadacao);
						}else{
							/* met�do para inserir o lan�amento cont�bil assim como seus itens */
							this.inserirLancamentoContabilItemArrecadacao(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento);
							
							//limpaa cole��o e adiciona os dados do resumo atual
							colecaoDadosResumoPorTipoLancamento.clear();
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoArrecadacao);
							
							RecebimentoTipo recebimentoTipo = new RecebimentoTipo();
							recebimentoTipo.setId(idRecebimentoTipo);
							
							LancamentoTipo tipoLancamento = new LancamentoTipo();
							tipoLancamento.setId(idTipoLancamento);
							
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							
							//cria o lan�amento cont�bil que vai ser inserido
							lancamentoContabilInsert = new LancamentoContabil();
							lancamentoContabilInsert.setAnoMes(anoMesReferenciaArrecadacao);
							lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
							lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
							lancamentoContabilInsert.setLocalidade(localidade);
							lancamentoContabilInsert.setRecebimentoTipo(recebimentoTipo);
							lancamentoContabilInsert.setUltimaAlteracao(new Date());
							
							//atualiza o tipo de lan�amento tempor�rio com o novo valor
							idTipoLancamentoTemp = idTipoLancamento;
						}
					}
				}
				
				/*
				 * �ltimo registro
				 * Esse "if" � para verificar se ainda existe um �ltimo registro na cole��o
				 * caso exista algum item, adiciona o lan�amento cont�bil junto com o item. 
				 */
				if(colecaoDadosResumoPorTipoLancamento != null && colecaoDadosResumoPorTipoLancamento.size() > 0 ){
					this.inserirLancamentoContabilItemArrecadacao(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento);
					colecaoDadosResumoPorTipoLancamento = null;
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * Este metodo � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Pedro Alexandre
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) throws ControladorException{
		
		Collection colecaoObjetoGerar = null;
		Collection colecaoGerarIntegracaoContabilidade = null;
		
		try {

			colecaoObjetoGerar = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
			
			if(!colecaoObjetoGerar.isEmpty()){
				Iterator iteratorPesquisa = colecaoObjetoGerar.iterator();
				
				colecaoGerarIntegracaoContabilidade = new ArrayList();
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = null;
				Object[] objetoGerar = null;
				
				while(iteratorPesquisa.hasNext()){
					gerarIntegracaoContabilidadeHelper = new GerarIntegracaoContabilidadeHelper();
					
					objetoGerar = (Object[]) iteratorPesquisa.next();
					
					//numero cartao
					if(objetoGerar[0] != null){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao((Short)objetoGerar[0]);
					}

					//lancamento tipo
					if(objetoGerar[1] != null){
						gerarIntegracaoContabilidadeHelper.setIdTipoLancamento((Integer) objetoGerar[1]);
					}
					
					//folha
					if(objetoGerar[2] != null){
						gerarIntegracaoContabilidadeHelper.setFolha((Integer) objetoGerar[2]);
					}
					
					//linha
					if(objetoGerar[3] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorLinha((Integer)objetoGerar[3]);
					}
					
					//prefixo contabil
					if(objetoGerar[4] != null){
						gerarIntegracaoContabilidadeHelper.setNumeroPrefixoContabil((String) objetoGerar[4]);
					}
					
					//conta
					if(objetoGerar[5] != null){
						gerarIntegracaoContabilidadeHelper.setCont((Integer) objetoGerar[5]);
					}
					
					//analise
					if(objetoGerar[6] != null){
						gerarIntegracaoContabilidadeHelper.setAnalise((Integer) objetoGerar[6]);
					}
					
					//digito
					if(objetoGerar[7] != null){
						gerarIntegracaoContabilidadeHelper.setDigito((Integer) objetoGerar[7]);
					}
					
					//terceiros
					if(objetoGerar[8] != null){
						gerarIntegracaoContabilidadeHelper.setTerceiros((Integer) objetoGerar[8]);
					}
					
					//referencia
					if(objetoGerar[9] != null){
						gerarIntegracaoContabilidadeHelper.setReferencial((Integer) objetoGerar[9]);
					}
					
					//valor lancamento
					if(objetoGerar[10] != null){
						gerarIntegracaoContabilidadeHelper.setValorLancamento((BigDecimal)objetoGerar[10]);
					}
					
					//indicador debito credito
					if(objetoGerar[11] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorDebitoConta((Integer) objetoGerar[11]);
					}
					
					//indicador debito credito
					if(objetoGerar[12] != null){
						gerarIntegracaoContabilidadeHelper.setCartao2((Integer) objetoGerar[12]);
					}
					
					//id localidade
					if(objetoGerar[13] != null){
						gerarIntegracaoContabilidadeHelper.setIdLocalidade((Integer) objetoGerar[13]);
					}
					
					colecaoGerarIntegracaoContabilidade.add(gerarIntegracaoContabilidadeHelper);
				}
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoGerarIntegracaoContabilidade;
	}
	
	/**
	 * este caso de uso gera a integra��o para a contabilidade
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Pedro Alexandre
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	@SuppressWarnings("unchecked")
	public void gerarIntegracaoContabilidade(
			String idLancamentoOrigem, String anoMes, String data) throws ControladorException {
	       
        Collection<Object[]> colecaoDadosGerarIntegracao = null;
        
        try {
            colecaoDadosGerarIntegracao = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
        } catch (ErroRepositorioException ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", ex);
        }
       
        StringBuilder gerarIntegracaoTxt = new StringBuilder();
        String ano = data.substring(6,10);
        String mes = data.substring(3,5);
        String dia = data.substring(0,2);
        String anoMesDia = ano + "/" + mes + "/" + dia;       
        String descricaoMes = Util.retornaDescricaoMes(new Integer(mes).intValue());
       
        String descricaoLancamento = "";
        String historico = "VALOR ";
        
        if (idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")) {
			descricaoLancamento = "FATURAMENTO";
			historico += "FATURAMENTO DO MES DE " + descricaoMes + "/" + ano;
			
		} else if (idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")) {
			descricaoLancamento = "ARRECADACAO";
			historico += "ARRECADACAO DO MES DE " + descricaoMes + "/" + ano;
			
		} else if (idLancamentoOrigem.equals(LancamentoOrigem.DEVEDORES_DUVIDOSOS + "")) {
			descricaoLancamento = "DEVEDORES_DUVIDOSOS";
			historico += "DEVEDORES DUVIDOSOS DO MES DE " + descricaoMes + "/" + ano;
		}
       
        if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()) {
           
            String numeroConta = null;
            String centroCusto = null;
            BigDecimal valorDebito = null;
            BigDecimal valorCredito = null;
            String moeda = "BRL";
           
            // La�o para gerar o txt
            for(Object[] dadosGerarIntegracao : colecaoDadosGerarIntegracao){
                numeroConta = (String) dadosGerarIntegracao[0];                           
                centroCusto = (String) dadosGerarIntegracao[1];                           
                valorDebito  = (BigDecimal) dadosGerarIntegracao[2];                       
                valorCredito = (BigDecimal) dadosGerarIntegracao[3];                           
               
                /*
                 * Inicio da gera��o do txt
                 */
               
                // SEQ = 1
                // CAMPO = DISPONIVEL
                // INICIO = 1 FIM = 1
                // TAMANHO = 1
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 2
                // CAMPO = ANO
                // INICIO = 2 FIM = 5
                // TAMANHO = 4
                gerarIntegracaoTxt.append(Util.truncarString(ano, 4));
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 3
                // CAMPO = CONTA CONTABIL
                // INICIO = 6 FIM = 13
                // TAMANHO = 8
                gerarIntegracaoTxt.append(Util.truncarString(numeroConta, 8));
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 4
                // CAMPO = CENTRO CUSTO
                // INICIO = 14 FIM = 23
                // TAMANHO = 10
                if(centroCusto!=null) {
                    gerarIntegracaoTxt.append(centroCusto);
                }
                
                gerarIntegracaoTxt.append(";");
                
                // SEQ = 5
                // CAMPO = LOCALIDADE
                // INICIO = 24 FIM = 73
                // TAMANHO = 50
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 6
                // CAMPO = MUNICIPIO
                // INICIO = 74 FIM = 123
                // TAMANHO = 50
//                }
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 7
                // CAMPO = DISPONIVEL
                // INICIO = 124 FIM = 129
                // TAMANHO = 6
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 8
                // CAMPO = MOEDA
                // INICIO = 130 FIM = 132
                // TAMANHO = 3
                gerarIntegracaoTxt.append(moeda);
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 9
                // CAMPO = VALOR DEBITO
                // INICIO = 133 FIM = 143
                // TAMANHO = 11
                if(valorDebito!= null) {
                    gerarIntegracaoTxt.append(valorDebito.toString());
                }
                
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 10
                // CAMPO = VALOR CREDITO
                // INICIO = 144 FIM = 154
                // TAMANHO = 11
                if(valorCredito!=null) {
                    gerarIntegracaoTxt.append(valorCredito.toString());   
                }
                
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 11
                // CAMPO = DISPONIVEL
                // INICIO = 155 FIM = 155
                // TAMANHO = 1
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 12
                // CAMPO = VALOR DEBITO
                // INICIO = 156 FIM = 166
                // TAMANHO = 11
                if(valorDebito!= null) {
                    gerarIntegracaoTxt.append(valorDebito.toString());
                }
                
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 13
                // CAMPO = VALOR CREDITO
                // INICIO = 167 FIM = 177
                // TAMANHO = 11
                if(valorCredito!=null) {
                    gerarIntegracaoTxt.append(valorCredito.toString());   
                }
                
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 14
                // CAMPO = DISPONIVEL
                // INICIO = 178 FIM = 182
                // TAMANHO = 5
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 15
                // CAMPO = HISTORICO
                // INICIO = 183 FIM = 438
                // TAMANHO = 255
                gerarIntegracaoTxt.append(historico);
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 16
                // CAMPO = DISPONIVEL
                // INICIO = 439 FIM = 443
                // TAMANHO = 5
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
                gerarIntegracaoTxt.append(";");
               
                // SEQ = 16
                // CAMPO = DATA
                // INICIO = 444 FIM = 453
                // TAMANHO = 10
                gerarIntegracaoTxt.append(anoMesDia);
               
                gerarIntegracaoTxt.append(System.getProperty("line.separator"));
               
            }           
           
            // Gerando o arquivo zip
            String nomeZip = "CONTABILIDADE_" + descricaoLancamento + "_" + (data.replace("/","_"));
            BufferedWriter out = null;
            ZipOutputStream zos = null;
            File compactadoTipo = new File(nomeZip + ".zip");
            File leituraTipo = new File(nomeZip + ".txt");

            if (gerarIntegracaoTxt != null && gerarIntegracaoTxt.length() != 0) {
                try {
                    System.out.println("CRIANDO ZIP");
                    zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
                    out.write(gerarIntegracaoTxt.toString());
                    out.flush();
                    ZipUtil.adicionarArquivo(zos, leituraTipo);
                    zos.close();
                    leituraTipo.delete();
                    out.close();
                } catch (IOException ex) {
                    throw new ControladorException("erro.sistema", ex);
                }
               
                try {
                    // Envia de Arquivo por email
                    EnvioEmail envioEmail = this.getControladorCadastro().pesquisarEnvioEmail(
                    		EnvioEmail.GERAR_INTEGRACAO_PARA_CONTABILIDADE);

                    String emailRemetente = envioEmail.getEmailRemetente();
                    String tituloMensagem = envioEmail.getTituloMensagem();
                    String corpoMensagem = envioEmail.getCorpoMensagem();
                    String emailReceptor = envioEmail.getEmailReceptor();
                   
                    ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor, emailRemetente,
                    		tituloMensagem, corpoMensagem, compactadoTipo);
                } catch (Exception e) {
                    System.out.println("Erro ao enviar email.");
                }           
            }
        }else {
            if (idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")) {
                throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null, "Resumo Faturamento");
            } else if (idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")) {
                throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Arrecada��o");
            } else if (idLancamentoOrigem.equals(LancamentoOrigem.DEVEDORES_DUVIDOSOS + "")) {
                throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Devedores Duvidosos");
            }
        }
    }
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 25/10/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	public void apagarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, int idFuncionalidadeIniciada)	throws ControladorException {
		
		System.out.println("Localidade " + idLocalidade);
	
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));
		
		try {
			
			//Recupera os par�metros dos devedores duvidosos.
			ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
			
			
			//[FS0001] - Verificar exist�ncia dos par�metros
			if(parametrosDevedoresDuvidosos == null){
				throw new ControladorException("atencao.naocadastrado.referencia_contabil");
			}

				
			// 2-Caso seja um reprocessamento
			if ( parametrosDevedoresDuvidosos.getDataProcessamento() != null ) {
				
				Collection colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
				if ( colecaoQuadraId != null ) {
					
					Iterator iteratorIdsQuadra = colecaoQuadraId.iterator();
					while ( iteratorIdsQuadra.hasNext() ) {
						Integer idQuadra = (Integer) iteratorIdsQuadra.next();
						//o sistema atualiza com o valor nulo o ano/m�s refer�ncia cont�bil das contas baixadas
						//contabilmente no ano/m�s refer�ncia cont�bil
						this.repositorioFinanceiro.atualizaContaAnoMesReferenciaContabil(anoMesReferenciaContabil, idLocalidade, idQuadra);
					}
				}
				
		
				//exclui o resumo dos devedores duvidosos,referente ao ano/m�s refer�ncia cont�bil
				this.repositorioFinanceiro.removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(anoMesReferenciaContabil,idLocalidade);
				parametrosDevedoresDuvidosos.setValorBaixado(BigDecimal.ZERO);
				getControladorUtil().atualizar(parametrosDevedoresDuvidosos);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	

	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *	Esse caso de uso foi dividido em 3 steps:
	 *
	 *	1 - apagarResumoDevedoresDuvidosos
	 *	2 - gerarResumoDevedoresDuvidosos
	 *  3 - atualiza
	 *
	 * Gera o resumo dos devedores duvidosos e marca as contas baixadas contabilmente.
	 *
	 * @author Rafael Pinto, Pedro Alexandre,Vivianne Sousa, Arthur Carvalho
	 * @date 22/11/2006, 06/06/2007,09/09/2009 , 30/11/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */		
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public void gerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in?cio do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));
		
		try {
			
			Collection<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperTemp = null;
			List<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperPrincipal = new ArrayList();
			Integer anoMesArrecadacao = null;
			
			//Recupera os par?metros dos devedores duvidosos.
			ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);			
			
			BigDecimal valorLimiteBaixado = parametrosDevedoresDuvidosos.getValorABaixar();
			BigDecimal valorTotalJaBaixado = parametrosDevedoresDuvidosos.getValorBaixado();
			

			BigDecimal valorTotalValoresBaixados = BigDecimal.ZERO;
			
			//5.4-Caso o valor total dos valores baixados seja maior
			//5.5-Caso contr?rio,processar o grupo de contas do pr?ximo im?vel
			if(valorTotalJaBaixado.compareTo(valorLimiteBaixado) != 1) {
				
				//[FS0001] - Verificar exist?ncia dos par?metros
				if(parametrosDevedoresDuvidosos == null){
					throw new ControladorException("atencao.naocadastrado.referencia_contabil");
				}
				
				String anoMesString = ""+ parametrosDevedoresDuvidosos.getAnoMesReferenciaContabil();
				
				Collection colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
				
				//ATUALIZA AS CONTAS
				if ( colecaoQuadraId != null ) {
					
					Iterator iteratorIdsQuadra = colecaoQuadraId.iterator();
					while ( iteratorIdsQuadra.hasNext() ) {
						
						Integer idQuadra = (Integer) iteratorIdsQuadra.next();
					
						
						////Pesquisa o valor total das contas que est?o na situa??o de Devedor Duvidosos
						valorTotalValoresBaixados =  this.repositorioFinanceiro.obterValorTotalContasDevedoresDuvidosos( anoMesReferenciaContabil,
								idLocalidade, idQuadra, anoMesString, parametrosDevedoresDuvidosos.getId() ) ;
						
						//Recupera o percentual permitido e calcula o valor limite permitido de estouro
						BigDecimal percentualPermitido = new BigDecimal(0.10);
						BigDecimal valorLimiteBaixadoComPercentual = valorLimiteBaixado.add(valorLimiteBaixado.multiply(percentualPermitido));
						if ( valorTotalValoresBaixados == null ) {
							valorTotalValoresBaixados = BigDecimal.ZERO;
						}
						
						parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
						
						valorTotalJaBaixado = parametrosDevedoresDuvidosos.getValorBaixado().add(valorTotalValoresBaixados);
						
						if(valorTotalJaBaixado.compareTo(valorLimiteBaixadoComPercentual) != 1){
						
							//7.Atualiza nos par?metros para baixa das contas dos devedores duvidosos a data e hora
							//do processamento e o valor total baixado
							this.repositorioFinanceiro.atualizarValorBaixadoParametrosDevedoresDuvidosos(anoMesReferenciaContabil ,valorTotalValoresBaixados);
							
							
							this.repositorioFinanceiro.atualizaContaAnoMesReferenciaContabilDevedoresDuvidosos(anoMesReferenciaContabil, 
									idLocalidade, idQuadra ,parametrosDevedoresDuvidosos.getId() );
							
							
							//[SB0001] - Acumular o resumo dos devedores duvidosos
							colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidosos(anoMesReferenciaContabil,idLocalidade, idQuadra ,
										parametrosDevedoresDuvidosos.getId());
							
							//Caso a cole??o temporaria n?o esteja vazia 
							//acumula os registros que est?o na mesma quebra
							//e adiciona os novos registros.
							if(colecaoGerarResumoDevedoresHelperTemp != null && !colecaoGerarResumoDevedoresHelperTemp.isEmpty()){
								
								if(colecaoGerarResumoDevedoresHelperPrincipal.isEmpty()){
									colecaoGerarResumoDevedoresHelperPrincipal.addAll(colecaoGerarResumoDevedoresHelperTemp);
									colecaoGerarResumoDevedoresHelperTemp = null;
								}else{
									for(GerarResumoDevedoresDuvidososHelper gerarResumoDevedoresDuvidososHelperTemp : colecaoGerarResumoDevedoresHelperTemp ){
										if(colecaoGerarResumoDevedoresHelperPrincipal.contains(gerarResumoDevedoresDuvidososHelperTemp)){
											int posicao = colecaoGerarResumoDevedoresHelperPrincipal.indexOf(gerarResumoDevedoresDuvidososHelperTemp);
	
											GerarResumoDevedoresDuvidososHelper jaCadastrado = colecaoGerarResumoDevedoresHelperPrincipal.get(posicao);
											
											if ( gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado()  != null && 
													!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("") && 
													!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("0") &&
													jaCadastrado.getValorBaixado() != null ) {
												
												jaCadastrado.setValorBaixado(jaCadastrado.getValorBaixado().add(gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado()));
											}
											
										}else{
											
											if ( gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado() != null && 
													!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("") && 
													!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("0") ) {
												colecaoGerarResumoDevedoresHelperPrincipal.add(gerarResumoDevedoresDuvidososHelperTemp);
											}
										}
									}
									colecaoGerarResumoDevedoresHelperTemp = null;
								}
							}
						}
					}
					
				
					Collection colecaoDevedoresDuvidosos = new ArrayList();
					
					GerenciaRegional gerenciaRegional = new GerenciaRegional();
					Integer idGerenciaRegional = this.getControladorLocalidade().pesquisarIdGerenciaParaLocalidade(idLocalidade);
					gerenciaRegional.setId(idGerenciaRegional);
					Localidade localidade = new Localidade();
					localidade.setId(idLocalidade);
					
					//6.Inserir as linhas acumuladas do resumo dos devedores duvidosos
					if(colecaoGerarResumoDevedoresHelperPrincipal != null && !colecaoGerarResumoDevedoresHelperPrincipal.isEmpty()){
						for(GerarResumoDevedoresDuvidososHelper temp : colecaoGerarResumoDevedoresHelperPrincipal){
							//Caso o valor seja maior que zero o resumo vai ser inserido 
							//caso contr?rio passar para o pr?ximo registro.
							if(temp.getValorBaixado() != null && temp.getValorBaixado().compareTo(BigDecimal.ZERO) == 1){
								LancamentoItem lancamentoItem = null ;
								LancamentoTipo lancamentoTipo = null;
								LancamentoItemContabil lancamentoItemContabil = null;
								Categoria categoria = null;
								
								if(temp.getIdCategoria() != null){
									categoria = new Categoria();
									categoria.setId(temp.getIdCategoria());
								}
								
								if(temp.getIdLancamentoItem() != null ){
									lancamentoItem = new LancamentoItem();
									lancamentoItem.setId(temp.getIdLancamentoItem());
								}
		
								if(temp.getIdLancamentoTipo() != null ){
									lancamentoTipo = new LancamentoTipo();
									lancamentoTipo.setId(temp.getIdLancamentoTipo());
								}
		
								if(temp.getIdLancamentoItemContabil() != null ){
									lancamentoItemContabil = new LancamentoItemContabil();
									lancamentoItemContabil.setId(temp.getIdLancamentoItemContabil());
								}
								
								SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
								if ( sistemaParametro != null ) {
									anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();
								}
								
								ResumoDevedoresDuvidosos resumoDevedoresDuvidosos = new ResumoDevedoresDuvidosos(
										anoMesReferenciaContabil, 
										anoMesArrecadacao, 
										temp.getNumeroSequenciaTipoLancamento(), 
										temp.getNumeroSequencialItemTipoLancamento(), 
										temp.getValorBaixado(), 
										new Date(), 
										gerenciaRegional,
										localidade,
										categoria,
										lancamentoItemContabil,
										lancamentoTipo,
										lancamentoItem);
								
								colecaoDevedoresDuvidosos.add(resumoDevedoresDuvidosos);
							}
						}
					}
				
					if ( colecaoDevedoresDuvidosos != null ) {
						//Inserindo o resumo
						getControladorBatch().inserirColecaoObjetoParaBatch(colecaoDevedoresDuvidosos);
						colecaoDevedoresDuvidosos = null;
					}
				}
				
				getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
				
			}else{
				
				getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 30/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Collection<GerarResumoDevedoresDuvidososHelper> acumularResumoDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)	throws ControladorException {
	    
	    Collection<GerarResumoDevedoresDuvidososHelper> colecaoRetorno = new ArrayList();
	    GerarResumoDevedoresDuvidososHelper gerarResumoDevedoresDuvidososHelper = null;
	    Collection<Object[]> colecaoDadosTemporaria = null;
	    final Short ZERO = 0;
	    
	    Short maxSequencialImpressaoMais10 = this.getControladorFaturamento().recuperarValorMaximoSequencialImpressaoMais10();
	    
		try {

			Integer idGerenciaRegional = this.getControladorLocalidade().pesquisarIdGerenciaParaLocalidade(idLocalidade);
	
			//TIPO DE FINANCIAMENTO = AGUA
			Collection<Object[]> valorAguaDevedoresDuvidosos = this.repositorioFinanceiro.
						pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra ,
								 idParametrosDevedoresDuvidosos);
			
			if ( valorAguaDevedoresDuvidosos != null ) {

				Iterator iteratorValorAguaCategoria = valorAguaDevedoresDuvidosos.iterator();
				while (iteratorValorAguaCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorAguaCategoria.next();
					BigDecimal valorAgua = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.AGUA, 
							LancamentoItem.AGUA, 
							null, 
							new Short("100"), 
							ZERO, 
							valorAgua);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = ESGOTO
			Collection<Object[]> valorEsgotoDevedoresDuvidosos = this.repositorioFinanceiro.
			pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade,  idQuadra, idParametrosDevedoresDuvidosos);
			
			if ( valorEsgotoDevedoresDuvidosos != null ) {

				Iterator iteratorValorEsgotoCategoria = valorEsgotoDevedoresDuvidosos.iterator();
				while (iteratorValorEsgotoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorEsgotoCategoria.next();
					BigDecimal valorEsgoto = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.ESGOTO, 
							LancamentoItem.ESGOTO, 
							null, 
							new Short("200"), 
							ZERO, 
							valorEsgoto);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( agua )
			Collection<Object[]> valorAguaParcelamentoDevedoresDuvidosos = this.repositorioFinanceiro.
				pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra, idParametrosDevedoresDuvidosos);
			
			if ( valorAguaParcelamentoDevedoresDuvidosos != null ) {

				Iterator iteratorValorAguaParcelamentoCategoria = valorAguaParcelamentoDevedoresDuvidosos.iterator();
				while (iteratorValorAguaParcelamentoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorAguaParcelamentoCategoria.next();
					BigDecimal valorAguaParcelamento = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.PARCELAMENTOS_COBRADOS, 
							LancamentoItem.AGUA, 
							null, 
							new Short("1000"), 
							new Short("10"), 
							valorAguaParcelamento);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( esgoto )
			Collection<Object[]> valorEsgotoParcelamentoDevedoresDuvidosos = this.repositorioFinanceiro.
			pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra,  idParametrosDevedoresDuvidosos);
		
			if ( valorEsgotoParcelamentoDevedoresDuvidosos != null ) {

				Iterator iteratorValorEsgotoParcelamentoCategoria = valorEsgotoParcelamentoDevedoresDuvidosos.iterator();
				while (iteratorValorEsgotoParcelamentoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorEsgotoParcelamentoCategoria.next();
					BigDecimal valorEsgotoParcelamento = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.PARCELAMENTOS_COBRADOS, 
							LancamentoItem.ESGOTO, 
							null, 
							new Short("1000"), 
							new Short("20"), 
							valorEsgotoParcelamento);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
			
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( grupo contabil )
			colecaoDadosTemporaria = repositorioFinanceiro.pesquisarValorServicoParceladoDevedoresDuvidosos(anoMesReferenciaContabil,idLocalidade, idQuadra,
					 idParametrosDevedoresDuvidosos);
			
			if (colecaoDadosTemporaria != null && !colecaoDadosTemporaria.isEmpty()) {
				
				for (Object[] dadosDebitoCobrado : colecaoDadosTemporaria) {
					
					BigDecimal valorDebito =(BigDecimal) dadosDebitoCobrado[0];
					Short numeroSequencialImpressao = (Short) dadosDebitoCobrado[1]; 
					Integer idLancamentoItemContabil = (Integer) dadosDebitoCobrado[2];
					Integer categoriaId = (Integer) dadosDebitoCobrado[3];
					
					if(valorDebito != null && valorDebito.compareTo(BigDecimal.ZERO) == 1){

						gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
								idGerenciaRegional, 
								categoriaId, 
								LancamentoTipo.PARCELAMENTOS_COBRADOS, 
								LancamentoItem.GRUPO_CONTABIL, 
								idLancamentoItemContabil, 
								new Short("1000"), 
								numeroSequencialImpressao, 
								valorDebito);
						
						colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
					}
				}
			}
				
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( juros )
			Collection<Object[]> valorJurosParcelamentoDevedoresDuvidosos = this.repositorioFinanceiro.
				pesquisarValorJurosDoParcelamentoDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra, idParametrosDevedoresDuvidosos);
		
			if ( valorJurosParcelamentoDevedoresDuvidosos != null ) {

				Iterator iteratorValorJurosParcelamentoCategoria = valorJurosParcelamentoDevedoresDuvidosos.iterator();
				while (iteratorValorJurosParcelamentoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorJurosParcelamentoCategoria.next();
					BigDecimal valorJurosParcelamento = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.PARCELAMENTOS_COBRADOS, 
							LancamentoItem.JUROS, 
							null, 
							new Short("1000"), 
							maxSequencialImpressaoMais10, 
							valorJurosParcelamento);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = FINANCIAMENTOS COBRADOS ( grupo contabil )
			colecaoDadosTemporaria  = repositorioFinanceiro.pesquisarValorPorTipoFinanciamentoDevedoresDuvidosos( anoMesReferenciaContabil,
					idLocalidade, idQuadra, idParametrosDevedoresDuvidosos);
			
			if (colecaoDadosTemporaria != null && !colecaoDadosTemporaria.isEmpty()) {
				
				for (Object[] dadosDebitoCobrado : colecaoDadosTemporaria) {
					BigDecimal valorDebito =(BigDecimal) dadosDebitoCobrado[0];
					Short numeroSequencialImpressao = (Short) dadosDebitoCobrado[1]; 
					Integer idLancamentoItemContabil = (Integer) dadosDebitoCobrado[2];
					Integer categoriaId = (Integer) dadosDebitoCobrado[3];
					
					if(valorDebito != null && valorDebito.compareTo(BigDecimal.ZERO) == 1){
						gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
								idGerenciaRegional, 
								categoriaId, 
								LancamentoTipo.FINANCIAMENTOS_COBRADOS, 
								LancamentoItem.GRUPO_CONTABIL, 
								idLancamentoItemContabil, 
								new Short("1300"), 
								numeroSequencialImpressao, 
								valorDebito);
						
						colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
					}
				}
				
			}
			
			//TIPO DE FINANCIAMENTO = DEVOLUCAO
			Collection<Object[]> colecaoDevolucoes  = repositorioFinanceiro.pesquisarDevolucoesValoresContaDevedoresDuvidosos( anoMesReferenciaContabil,
					idLocalidade, idQuadra, idParametrosDevedoresDuvidosos);
			
			if (colecaoDevolucoes != null && !colecaoDevolucoes.isEmpty()) {
				
				for (Object[] dadosDebitoCobrado : colecaoDevolucoes) {
					
					BigDecimal valorCredito =(BigDecimal) dadosDebitoCobrado[0];
					Short numeroSequencialImpressao = (Short) dadosDebitoCobrado[1]; 
					Integer idLancamentoItemContabil = (Integer) dadosDebitoCobrado[2]; 
					Integer categoriaId = (Integer) dadosDebitoCobrado[3];
					
					if(valorCredito != null ){
						gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
								idGerenciaRegional, 
								categoriaId, 
								LancamentoTipo.DEVOLUCAO__VALORES_EM_CONTA_INT, 
								LancamentoItem.CREDITOS_PARA_COBRANCA_INDEVIDA, 
								idLancamentoItemContabil, 
								new Short("1400"), 
								numeroSequencialImpressao, 
								valorCredito);
						
						colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
					}
				}
			}
		
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoRetorno;
	}
	
	
	
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * Gera o resumo dos devedores duvidosos e marca as contas baixadas contabilmente.
	 *
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */		
	public void atualizarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, int idFuncionalidadeIniciada)	throws ControladorException {
	
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.RELATORIO,(0));
		
		try {
			BigDecimal valorUltrapassado = new BigDecimal(0);
			
			Object[] parametrosARemover = new Object[2];
			boolean primeiraVez = true;
			
//			Recupera os par�metros dos devedores duvidosos.
			ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
			
			if ( parametrosDevedoresDuvidosos.getValorBaixado().compareTo(parametrosDevedoresDuvidosos.getValorABaixar()) == 1 ) {
			
				Collection<Object[]> colecaoValorBaixadoELocalidade  = repositorioFinanceiro.pesquisarValorBaixadoAgrupadoPorLocalidadeResumoDevedoresDuvidosos( anoMesReferenciaContabil )  ;
				
				if (colecaoValorBaixadoELocalidade != null && !colecaoValorBaixadoELocalidade.isEmpty()) {
					
					for (Object[] valorBaixadoELocalidade : colecaoValorBaixadoELocalidade) {
						
						BigDecimal valorBaixado =(BigDecimal) valorBaixadoELocalidade[0];
						
						valorUltrapassado = parametrosDevedoresDuvidosos.getValorBaixado().subtract(parametrosDevedoresDuvidosos.getValorABaixar());
						
						if ( (valorBaixado.compareTo(valorUltrapassado) == 1) && primeiraVez) {
							parametrosARemover = valorBaixadoELocalidade;
							primeiraVez = false;
						} else if ( valorBaixado.compareTo(valorUltrapassado) == 1 ) {
							BigDecimal valorBaixadoJaCadastrado =(BigDecimal) parametrosARemover[0];
							
							if ( valorBaixado.compareTo(valorBaixadoJaCadastrado) == -1 ) {
								parametrosARemover = valorBaixadoELocalidade;
							}
						}
					}
				}
				
				
				//exclui o resumo dos devedores duvidosos,referente ao ano/m�s refer�ncia cont�bil
				BigDecimal valorBaixadoARemover = (BigDecimal) parametrosARemover[0];
				Integer idLocalidade = (Integer) parametrosARemover[1];
				this.repositorioFinanceiro.removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(anoMesReferenciaContabil, idLocalidade);
				this.repositorioFinanceiro.atualizaContaAnoMesReferenciaContabil(anoMesReferenciaContabil, idLocalidade);
				
				parametrosDevedoresDuvidosos.setValorBaixado(parametrosDevedoresDuvidosos.getValorBaixado().subtract(valorBaixadoARemover));
				
				getControladorUtil().atualizar(parametrosDevedoresDuvidosos);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * [UC0345] - Gerar Relatorio de Resumo da Arrecada��o
	 *
	 * @author Vivianne Sousa
	 * @date 10/04/2007
	 *
	 * @param idLancamentoTipo
	 * @throws ErroRepositorioException
	 */	
	public String obterDescricaoLancamentoTipo(Integer idLancamentoTipo) 
		throws ControladorException{
		try {
			return repositorioFinanceiro
					.obterDescricaoLancamentoTipo(idLancamentoTipo);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * Pesquisa as localidades que tem resumo de faturamento 
	 * para o ano/m�s de faturamento informado.
	 *
	 * [UC00175] Gerar Lan�amentos Cont�beis do Faturamento
	 *
	 * @author Pedro Alexandre
	 * @date 25/05/2007
	 *
	 * @param anoMesFaturamento
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(Integer anoMesFaturamento) throws ControladorException{
		try{
			//pesquisa os lan�amentos de item cont�bil existentes no sisitema
			return 
				this.repositorioFinanceiro.pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(
						anoMesFaturamento);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exce��o para a pr�xima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	/**
	 * Gera o lan�amento cont�bil junto com seus itens. 
	 *
	 * [UC0348] - Gerar Lan�amentos Cont�beis da Arrecada��o
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param lancamentoContabil
	 * @param colecaoDadosResumoPorTipoLancamento
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void inserirLancamentoContabilItemArrecadacao(LancamentoContabil lancamentoContabil,Collection<Object[]> colecaoDadosResumoPorTipoLancamento) throws ControladorException {
		try{
			/*
			 * Caso exista dados para os itens do resumo da arrecada��o
			 * inseri os itens do lan�amento cont�bil.  
			 */
			if(colecaoDadosResumoPorTipoLancamento != null && !colecaoDadosResumoPorTipoLancamento.isEmpty()){
				
				Collection colecaoLancamentoContabilItem = new ArrayList();
				
				//flaq que indica se o lan�amento cont�bil j� foi inserido
				boolean flagInseridoLancamentoContabil = false;
				
				/*
				 * Dados do resumo da arrecada��o
				 * 
				 *  0 - id da localidade
				 *  1 - id do tipo de recebimento
				 *  2 - id do tipo de lan�amento
				 *  3 - id do item de lan�amento
				 *  4 - id do item de lan�amento cont�bil
				 *  5 - id da categoria
				 *  6 - soma do valor do resumo da arrecada��o 
				 */
				for(Object[] dadosResumoFaturamento : colecaoDadosResumoPorTipoLancamento){
					
					//recupera os dados do resumo do faturamento
					Integer idTipoRecebimento = 	   (Integer) dadosResumoFaturamento[1];
					Integer idLancamentoTipo =         (Integer) dadosResumoFaturamento[2];
					Integer idLancamentoItem =         (Integer) dadosResumoFaturamento[3];
					Integer idLancamentoItemContabil = (Integer) dadosResumoFaturamento[4];
					Integer idCategoria =              (Integer) dadosResumoFaturamento[5];
					BigDecimal valorLancamento =       (BigDecimal) dadosResumoFaturamento[6]; 
					
					/* 
					 * Verifica se existe conta cont�bil para o item que vai ser inserido 
					 * 
					 * 0 - id conta cont�bil do d�bito
					 * 1 - id conta cont�bil cr�dito 
					 * 2 - descri��o do hist�rico do d�bito
					 * 3 - descri��o do hist�rico do cr�dito
					 */
					Object[] dadosContaContabil = this.repositorioFinanceiro.obterParametrosContabilArrecadacao(idTipoRecebimento, idCategoria, idLancamentoItemContabil, idLancamentoItem, idLancamentoTipo);
					
					if(dadosContaContabil != null){
						Integer idLancamentoContabil = null;
						/*
						 * Caso exista dados para a conta cont�bil do item do resumo da arrecada��o
						 * e o lan�amento cont�bil n�o foi inserido ainda 
						 * inseri o lan�amento cont�bil na base.
						 */
						if(!flagInseridoLancamentoContabil){
							idLancamentoContabil = (Integer)getControladorUtil().inserir(lancamentoContabil);
							lancamentoContabil.setId(idLancamentoContabil);
							flagInseridoLancamentoContabil = true;
						}
						
						//recupera os dados da conta cont�bil.
						Integer idContaContabilDebito = (Integer) dadosContaContabil[0];
						Integer idContaContabilCredito = (Integer) dadosContaContabil[1];
						String descricaoHistoricoDebito = (String) dadosContaContabil[2];
						String descricaoHistoricoCredito = (String) dadosContaContabil[3];
						
						//cria os indicadores de d�bito e cr�dito.
						Short indicadorDebito = new Short("2"); 
						Short indicadorCredito = new Short("1");
						
						Date ultimaAlteracao = new Date();
						
						//cria as contas de d�bito e cr�dito.
						ContaContabil contaContabilCredito = new ContaContabil();
						contaContabilCredito.setId(idContaContabilCredito);
						
						ContaContabil contaContabilDebito = new ContaContabil();
						contaContabilDebito.setId(idContaContabilDebito);
						
						/**  Item de cr�dito */
						LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
								valorLancamento, 
								descricaoHistoricoCredito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilCredito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
						
						/** Item de d�bito */
						LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
								valorLancamento, 
								descricaoHistoricoDebito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilDebito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
						
					}
				}
				
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoLancamentoContabilItem); 
			}
	
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}
	
	/**
	 * Pesquisa as localidades que tem resumo de arrecada��o 
	 * para o ano/m�s de arrecada��o informado.
	 *
	 * [UC00348] Gerar Lan�amentos Cont�beis da arrecada��o
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(Integer anoMesArrecadacao) throws ControladorException{
		try{
			return this.repositorioFinanceiro.pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(anoMesArrecadacao);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exce��o para a pr�xima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	
	/**
	 * Pesquisa a cole��o de ids das localidades para processar o resumo 
	 * dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ControladorException{
		try{
			return this.repositorioFinanceiro.pesquisarIdsLocalidadesGerarResumoDevedoresDuvidosos(anoMesReferenciaContabil);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exce��o para a pr�xima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
//	***********************************************************************************************************************
//	******************************** PARTE PARA SEER COLOCADA NO CONTROLADOR CAERN
		

		/**
		 * Este metodo � utilizado para pesquisar os registros q ser�o
		 * usados para contru��o do txt do caso de uso
		 *
		 * [UC0469] Gerar Integra��o para a Contabilidade
		 *
		 * @author Fl�vio Leonardo
		 * @date 28/05/2007
		 *
		 * @param idLancamentoOrigem
		 * @param anoMes
		 * @return
		 * @throws ControladorException
		 */
		/*public Collection pesquisarGerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes) throws ControladorException{
			
			Collection colecaoObjetoGerar = null;
			Collection colecaoGerarIntegracaoContabilidade = null;
			
			try {

				colecaoObjetoGerar = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeCaern(idLancamentoOrigem, anoMes);
				
				if(!colecaoObjetoGerar.isEmpty()){
					Iterator iteratorPesquisa = colecaoObjetoGerar.iterator();
					
					colecaoGerarIntegracaoContabilidade = new ArrayList();
					GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = null;
					Object[] objetoGerar = null;
					
					while(iteratorPesquisa.hasNext()){
						gerarIntegracaoContabilidadeHelper = new GerarIntegracaoContabilidadeHelper();
						
						objetoGerar = (Object[]) iteratorPesquisa.next();
						
						//indicador debito credito
						if(objetoGerar[10] != null){
							gerarIntegracaoContabilidadeHelper.setIndicadorDebitoConta(new Integer((Short) objetoGerar[10]));
						}
						
						//LCO_DEB_CRE
						if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
								&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
							gerarIntegracaoContabilidadeHelper.setCreditoDebito("C");
						}else if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
								&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
							gerarIntegracaoContabilidadeHelper.setCreditoDebito("D");
						}
						
						//numero cartao
						if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
								&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
							gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Short("402"));
						}else if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
								&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
							gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Short("401"));
						}

						//lancamento tipo
						if(objetoGerar[1] != null){
							gerarIntegracaoContabilidadeHelper.setIdTipoLancamento(new Integer((Short) objetoGerar[1]));
						}
						
						//folha
						if(objetoGerar[2] != null){
							gerarIntegracaoContabilidadeHelper.setFolha(new Integer((Short) objetoGerar[2]));
						}
						
						//linha
						if(objetoGerar[3] != null){
							gerarIntegracaoContabilidadeHelper.setIndicadorLinha(new Integer((Short)objetoGerar[3]));
						}
						
						//prefixo contabil
						if(objetoGerar[4] != null){
							gerarIntegracaoContabilidadeHelper.setNumeroPrefixoContabil((String) objetoGerar[4]);
						}
						
						//conta
						if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
							String numero = ((String) objetoGerar[5]).trim();
							gerarIntegracaoContabilidadeHelper.setNumeroContaCredito(numero);
							gerarIntegracaoContabilidadeHelper.setNumeroContaDebito("");
						}else if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
								&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
							String numero = ((String) objetoGerar[5]).trim();
							gerarIntegracaoContabilidadeHelper.setNumeroContaDebito(numero);
							gerarIntegracaoContabilidadeHelper.setNumeroContaCredito("");
						}
						
						//digito
						if(objetoGerar[6] != null){
							gerarIntegracaoContabilidadeHelper.setDigito(new Integer(((String) objetoGerar[6]).trim()));
						}
						
						//terceiros
						if(objetoGerar[7] != null){
							gerarIntegracaoContabilidadeHelper.setTerceiros(new Integer(((String) objetoGerar[7]).trim()));
						}
						
						//referencia
						if(objetoGerar[8] != null){
							gerarIntegracaoContabilidadeHelper.setReferencial(new Integer(((String) objetoGerar[8]).trim()));
						}
						
						//valor lancamento
						if(objetoGerar[9] != null){
							gerarIntegracaoContabilidadeHelper.setValorLancamento((BigDecimal)objetoGerar[9]);
						}
						
						//Cartao2
						if(objetoGerar[11] != null){
							gerarIntegracaoContabilidadeHelper.setCartao2(new Integer((Short) objetoGerar[11]));
						}
						
//						Versao
						if(objetoGerar[12] != null){
							gerarIntegracaoContabilidadeHelper.setVersao(new Integer((Short) objetoGerar[12]));
						}
						
						//id localidade
						if(objetoGerar[13] != null){
							gerarIntegracaoContabilidadeHelper.setIdLocalidade((Integer)objetoGerar[13]);
						}
						
						//codigo centro custo
						if(objetoGerar[14] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
								&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
							gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(((String) objetoGerar[14]).trim()));
							gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(0));
						}else if(objetoGerar[14] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
								&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
							gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(((String) objetoGerar[14]).trim()));
							gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(0));
						}
						
						colecaoGerarIntegracaoContabilidade.add(gerarIntegracaoContabilidadeHelper);
					}
				}
				
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
			
			return colecaoGerarIntegracaoContabilidade;
		}
		*/
		/**
		 * este caso de uso gera a integra��o para a contabilidade
		 *
		 * [UC0469] Gerar Integra��o para a Contabilidade
		 *
		 * @author Pedro Alexandre
		 * @date 28/05/2007
		 *
		 * @param idLancamentoOrigem
		 * @param anoMes
		 * @param data
		 * @throws ControladorException
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void gerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes, String data) throws ControladorException{
			
			/*
			 * Pesquisa os dados para gerar a integra��o para a contabilidade.
			 * 
			 * 0 - n�mero do cart�o
			 * 1 - c�digo tipo
			 * 2 - n�mero folha
			 * 3 - indicador linha
			 * 4 - prefixo cont�bil
			 * 5 - n�mero conta
			 * 6 - n�mero d�gito
			 * 7 - n�mero terceiros
			 * 8 - c�digo refer�ncia
			 * 9 - valor lan�amento
			 * 10 - indicador d�bito cr�dito
			 * 11 - n�mero cart�o 2
			 * 12 - n�mero vers�o
			 * 13 - id da localidade
			 * 14 - c�digo centro custo
			 * 
			 */
			Collection<Object[]> colecaoDadosGerarIntegracao = null;

			colecaoDadosGerarIntegracao = this.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
			
			/** defini��o das vari�veis */
			StringBuilder gerarIntegracaoTxt = new StringBuilder();
			String dataFormatada = data.replace("/","");
			
			/*
			 * Caso a cole��o dos dados n�o esteja vazia
			 */
			if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
				
				
				Short numeroCartao = null;
				String creditoDebito = "";
				BigDecimal valorLancamento = null;
				
				/*
				 * La�o para gerar o txt 
				 */
				Iterator iterator = colecaoDadosGerarIntegracao.iterator();
				while (iterator.hasNext()){
					GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = (GerarIntegracaoContabilidadeHelper)iterator.next();
					//n�mero do cart�o 
					numeroCartao = gerarIntegracaoContabilidadeHelper.getNumeroCartao();
						
					//CreditoDebito
					creditoDebito = gerarIntegracaoContabilidadeHelper.getCreditoDebito();
					
			
					//valor do lan�amento
					valorLancamento = (BigDecimal) gerarIntegracaoContabilidadeHelper.getValorLancamento();
					
					
					/*
					 * Inicio da gera��o do txt
					 */
					//Cartao
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(3,numeroCartao + ""));
					//Sequencial
					gerarIntegracaoTxt.append("01");
					//Lote
					gerarIntegracaoTxt.append("8888");
					//Documento
					gerarIntegracaoTxt.append("200001");
					//Linha
					gerarIntegracaoTxt.append("01");
					//data completa
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8,dataFormatada));
					//CreditoDebito
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(creditoDebito.trim(), 1));
					//COnta Debito
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getNumeroContaDebito()+"", 20));
					//COnta Debito
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getNumeroContaCredito()+"", 20));
					//Moeda
					gerarIntegracaoTxt.append("SSSSS");
					//Valor Lancamento
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda((valorLancamento + "").replace(".",""), 17));
					//LCO_HISTORICO
					if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
						gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("VL FATURAMENTO", 15));
					}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
						gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("VL ARRECADACAO", 15));
					}
					//MesAno
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarAnoMesParaMesAnoSemBarra(new Integer(anoMes))+"",6));
					//COdigo Custo Debito
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoDebito()+"", 9));
					//COdigo Custo Debito
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoCredito()+"", 9));
					//dia mes ano fechamento
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8,dataFormatada));
					//FILLER
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("DDDD", 4));
					//ANOMES
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(6,anoMes));
					//FILLER
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("LANCAMENTO GCOM", 15));
					//FILLER
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("", 318));
					//FILLER
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("", 33));
					//Quebra de Linha
					gerarIntegracaoTxt.append(System.getProperty("line.separator"));

				}
				/*
				 * Determina se o arquivo � de faturamento ou arrecada��o 
				 * para concatenar no nome do arquivo .zip
				 */
				String descricaoLancamento = "";
				if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
					descricaoLancamento = "FATURAMENTO";
				}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
					descricaoLancamento = "ARRECADACAO";
				}
				
				/*
				 * Gerando o arquivo zip
				 */
				String nomeZip = "CONTABILIDADE_" + descricaoLancamento + "_" + (data.replace("/","_"));
				BufferedWriter out = null;
				ZipOutputStream zos = null;
				File compactadoTipo = new File(nomeZip + ".zip");
				File leituraTipo = new File(nomeZip + ".txt");

				/*
				 * Caso oarquivo txt n�o esteja vazio 
				 * adiciona o txt ao arquivo zip.
				 */
				if (gerarIntegracaoTxt != null && gerarIntegracaoTxt.length() != 0) {
					try {
						System.out.println("CRIANDO ZIP");
						zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

						out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
						out.write(gerarIntegracaoTxt.toString());
						out.flush();
						ZipUtil.adicionarArquivo(zos, leituraTipo);
						zos.close();
						leituraTipo.delete();
						out.close();
					} catch (IOException ex) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", ex);
					} catch (Exception e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);

					}
					
				}
				//caso n�o exista informa��o para os dados informados
			}else{
				if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
					throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
				}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
					throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
				}
			}
		}
		
		
//	***********************************************************************************************************************

	/**
	 * Inserir o processo para gerar o resumo dos devedores duvidosos. 
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 18/06/2007
	 *
	 * @param processoIniciado
	 * @param dadosProcessamento
	 * @return
	 * @throws ControladorException
	 */
	public Integer gerarResumoDevedoresDuvidosos(ProcessoIniciado processoIniciado,Map<String, Object> dadosProcessamento)	throws ControladorException {
		//Recupera o ano/m�s de refer�ncia cont�bil.
		Integer anoMesReferenciaContabil = new Integer((String) dadosProcessamento.get("anoMesReferenciaContabil"));
		
		ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos;
		try {

			//[FS0001] - Verificar exist�ncia dos par�metros
			parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
			if(parametrosDevedoresDuvidosos == null){
				throw new ControladorException("atencao.naocadastrado.referencia_contabil");
			}else{
				if(parametrosDevedoresDuvidosos.getDataProcessamento() != null){
					//2-Caso seja um reprocessamento zerar o valor baixado
					parametrosDevedoresDuvidosos.setValorBaixado(BigDecimal.ZERO);
					this.getControladorUtil().atualizar(parametrosDevedoresDuvidosos);
				}
	
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}	
		//chama o met�do para inserir o processo de gerar resumo devedores duvidosos
		return this.getControladorBatch().inserirProcessoIniciado(processoIniciado,dadosProcessamento);
	}
	
	 /**
     * [UC0714] Gerar Contas a Receber Cont�bil
     *
     * M�todo respons�vel pela gera��o de contas a receber cont�bil
     *
     * @author Rafael Corr�a
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @throws ControladorException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarContasAReceberContabil(Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException {

    	logger.info("LOCALIDADE " + idLocalidade);

    	int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			if (!sistemaParametro.getAnoMesArrecadacao().equals(sistemaParametro.getAnoMesFaturamento())) {
				throw new ControladorException("atencao.arrecadacao_ou_faturamento_nao_encerrados");
			}

			int anoMesAnteriorFaturamento = Util.subtrairMesDoAnoMes(sistemaParametro.getAnoMesFaturamento(), 1);
			int anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();

			Collection colecaoContasAReceberContabil = new ArrayList();

			// exclui os dados do saldo de contas a receber cont�bil do m�s de refer�ncia do faturamento j� encerrado
			repositorioFinanceiro.removerContasAReceberContabil(anoMesAnteriorFaturamento, idLocalidade);

			// Valores de �gua e Esgoto
			adicionarContaAReceberContabilAguaEsgotoImpostos(idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

			// D�bitos Cobrados
			adicionarContaAReceberContabilDebitosCobrados(idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

			// Guias de Pagamento
			adicionarContaAReceberContabilGuiasPagamento(idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

			// Cr�ditos Realizados
			adicionarContaAReceberContabilCreditosRealizados(idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

			// D�bitos a Cobrar
			adicionarContaAReceberContabilDebitosACobrar(idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

			// Cr�ditos a Realizar
			adicionarContaAReceberContabilCreditosARealizar(idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

			// adicionado por Vivianne Sousa 14/08/2009 - Aryed Lins
			adicionarContaAReceberContabilValoresContabilizadosComoPerdas(idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

			// adicionado por Vivianne Sousa 17/08/2009 - Aryed Lins
			adicionarContaAReceberContabilRecebimentosNaoIdentificados(idLocalidade, anoMesAnteriorFaturamento, anoMesArrecadacao,
					colecaoContasAReceberContabil);

			if (colecaoContasAReceberContabil != null && !colecaoContasAReceberContabil.isEmpty()) {
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoContasAReceberContabil);
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

			System.out.println("fim da gera��o " + "Localidade " + idLocalidade);

		} catch (Exception e) {

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

    /**
     * [UC0714] Gerar Contas a Receber Cont�bil
     *
     * Cria o objeto de ContaAReceberContabil de acordo com os par�metros
     * passados
     *
     * @author Rafael Corr�a
     * @date 08/11/2007
     *
     */
    private ContaAReceberContabil criarContaAReceberContabil(
            int anoMesReferencia, Integer idGerenciaRegional,
            Integer idUnidadeNegocio, Integer idLocalidade,
            Integer idCategoria, BigDecimal valorItem,
            Integer idLancamentoTipo, int sequenciaLancamentoTipo,
            Integer idLancamentoItem, int sequenciaLancamentoItem,
            Integer idLancamentoItemContabil) {

        ContaAReceberContabil retorno = new ContaAReceberContabil();

        retorno.setAnoMesReferencia(anoMesReferencia);

        // Ger�ncia Regional
        GerenciaRegional gerenciaRegional = new GerenciaRegional();
        gerenciaRegional.setId(idGerenciaRegional);
        retorno.setGerenciaRegional(gerenciaRegional);

        // Unidade Neg�cio
        UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
        unidadeNegocio.setId(idUnidadeNegocio);
        retorno.setUnidadeNegocio(unidadeNegocio);

        // Localidade
        Localidade localidade = new Localidade();
        localidade.setId(idLocalidade);
        retorno.setLocalidade(localidade);

        // Categoria
        Categoria categoria = new Categoria();
        categoria.setId(idCategoria);
        retorno.setCategoria(categoria);

        // Valor Acumulado
        retorno.setValorItemLancamento(valorItem);

        // Lan�amento Tipo
        LancamentoTipo lancamentoTipo = new LancamentoTipo();
        lancamentoTipo.setId(idLancamentoTipo);
        retorno.setLancamentoTipo(lancamentoTipo);

        // Seq��ncia do Lan�amento Tipo
        retorno.setNumeroSequenciaTipoLancamento(sequenciaLancamentoTipo);

        // Lan�amento Item
        LancamentoItem lancamentoItem = new LancamentoItem();
        lancamentoItem.setId(idLancamentoItem);
        retorno.setLancamentoItem(lancamentoItem);

        // Seq��ncia do Lan�amento Item
        retorno.setNumeroSequenciaItemTipoLancamento(sequenciaLancamentoItem);
       
        // Lan�amento Item Cont�bil
        if (idLancamentoItemContabil != null) {
            LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
            lancamentoItemContabil.setId(idLancamentoItemContabil);
            retorno.setLancamentoItemContabil(lancamentoItemContabil);
        }
           
       
        //Colocado por Raphael Rossiter em 21/02/2008
        retorno.setUltimaAlteracao(new Date());

        return retorno;

    }

    /**
     * [UC0714] Gerar Contas a Receber Cont�bil
     *
     * Adiciona os dados de �gua e esgoto
     *
     * @author Rafael Corr�a
     * @date 08/11/2007
     *
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void adicionarContaAReceberContabilAguaEsgotoImpostos(Integer idLocalidade,
            int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {
    	
        Collection colecaoDadosValorAguaEsgoto = repositorioFinanceiro
                .pesquisarDadosContasCategoriaValorAguaEsgoto(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosValorAguaEsgoto != null
                && !colecaoDadosValorAguaEsgoto.isEmpty()) {

            Iterator colecaoDadosValorAguaEsgotoIterator = colecaoDadosValorAguaEsgoto
                    .iterator();

            while (colecaoDadosValorAguaEsgotoIterator.hasNext()) {

                Object[] dadosValorAguaEsgoto = (Object[]) colecaoDadosValorAguaEsgotoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosValorAguaEsgoto[0];
                Integer idUnidadeNegocioConta = (Integer) dadosValorAguaEsgoto[1];
                Integer idLocalidadeConta = (Integer) dadosValorAguaEsgoto[2];
                Integer idCategoriaConta = (Integer) dadosValorAguaEsgoto[3];

                BigDecimal valorAgua = (BigDecimal) dadosValorAguaEsgoto[4];

                BigDecimal valorEsgoto = (BigDecimal) dadosValorAguaEsgoto[5];

                // �gua
                if (valorAgua != null
                        && valorAgua.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorAgua,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.AGUA, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Esgoto
                if (valorEsgoto != null
                        && valorEsgoto.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorEsgoto,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.ESGOTO, 20, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);

                }

            }

            colecaoDadosValorAguaEsgoto = null;

        }
        
        Collection colecaoDadosValorImpostos = repositorioFinanceiro
        .pesquisarDadosContasCategoriaValorImpostos(
						anoMesAnteriorFaturamento, idLocalidade);

		if (colecaoDadosValorImpostos != null
				&& !colecaoDadosValorImpostos.isEmpty()) {

			Iterator colecaoDadosValorImpostosIterator = colecaoDadosValorImpostos
					.iterator();

			while (colecaoDadosValorImpostosIterator.hasNext()) {

				Object[] dadosValorImpostos = (Object[]) colecaoDadosValorImpostosIterator
						.next();

				Integer idGerenciaRegionalConta = (Integer) dadosValorImpostos[0];
				Integer idUnidadeNegocioConta = (Integer) dadosValorImpostos[1];
				Integer idLocalidadeConta = (Integer) dadosValorImpostos[2];
				Integer idCategoriaConta = (Integer) dadosValorImpostos[3];

				BigDecimal valorImpostos = (BigDecimal) dadosValorImpostos[4];

				// Valor dos Impostos
				if (valorImpostos != null
						&& valorImpostos.compareTo(new BigDecimal("0.00")) > 0) {

					// Cria o objeto com os valores passados
					/**
					 * Alterar o lancamento tipo dos impostos deduzidos
					 * 
					 * @author Wellington Rocha
					 * @date 18/09/2013*/
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento, idGerenciaRegionalConta,
							idUnidadeNegocioConta, idLocalidadeConta,
							idCategoriaConta, valorImpostos.multiply(new BigDecimal("-1")),
							LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS, 790,
							LancamentoItem.IMPOSTOS_DEDUZIDOS, 10, null);

					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}

			}

			colecaoDadosValorImpostos = null;

		}
    }

    /**
	 * [UC0714] Gerar Contas a Receber Cont�bil
	 * 
	 * Adiciona os dados de d�bitos cobrados
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void adicionarContaAReceberContabilDebitosCobrados(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Servi�o
        Collection colecaoDadosDebitosCobradosServico = repositorioFinanceiro
                .pesquisarDadosDebitosCobradosCategoriaServico(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosCobradosServico != null
                && !colecaoDadosDebitosCobradosServico.isEmpty()) {

            Iterator colecaoDadosDebitosCobradosServicoIterator = colecaoDadosDebitosCobradosServico
                    .iterator();

            while (colecaoDadosDebitosCobradosServicoIterator.hasNext()) {

                Object[] dadosDebitosCobradosServico = (Object[]) colecaoDadosDebitosCobradosServicoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosDebitosCobradosServico[0];
                Integer idUnidadeNegocioConta = (Integer) dadosDebitosCobradosServico[1];
                Integer idLocalidadeConta = (Integer) dadosDebitosCobradosServico[2];
                Integer idCategoriaConta = (Integer) dadosDebitosCobradosServico[3];
                Integer idLancamentoItemContabil = (Integer) dadosDebitosCobradosServico[4];

                BigDecimal valorCategoria = (BigDecimal) dadosDebitosCobradosServico[5];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.FINANCIAMENTOS_COBRADOS, 30, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosCobradosServico = null;

        }

        // Parcelamento
        Collection colecaoDadosDebitosCobradosParcelamento = repositorioFinanceiro
                .pesquisarDadosDebitosCobradosCategoriaParcelamento(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosCobradosParcelamento != null
                && !colecaoDadosDebitosCobradosParcelamento.isEmpty()) {

            Iterator colecaoDadosDebitosCobradosParcelamentoIterator = colecaoDadosDebitosCobradosParcelamento
                    .iterator();

            while (colecaoDadosDebitosCobradosParcelamentoIterator.hasNext()) {

                Object[] dadosDebitosCobradosParcelamento = (Object[]) colecaoDadosDebitosCobradosParcelamentoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosDebitosCobradosParcelamento[0];
                Integer idUnidadeNegocioConta = (Integer) dadosDebitosCobradosParcelamento[1];
                Integer idLocalidadeConta = (Integer) dadosDebitosCobradosParcelamento[2];
                Integer idCategoriaConta = (Integer) dadosDebitosCobradosParcelamento[3];
                Integer idLancamentoItemContabil = (Integer) dadosDebitosCobradosParcelamento[4];

                BigDecimal valorCategoria = (BigDecimal) dadosDebitosCobradosParcelamento[5];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.PARCELAMENTOS_COBRADOS, 40, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosCobradosParcelamento = null;

        }

    }

    /**
     * [UC0714] Gerar Contas a Receber Cont�bil
     *
     * Adiciona os dados das guias de pagamento
     *
     * @author Rafael Corr�a
     * @date 08/11/2007
     *
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void adicionarContaAReceberContabilGuiasPagamento(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Entradas de Parcelamento
        Collection colecaoDadosGuiasPagamentoEntradaParcelamento = repositorioFinanceiro
                .pesquisarDadosGuiasPagamentoCategoriaEntradaParcelamento(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosGuiasPagamentoEntradaParcelamento != null
                && !colecaoDadosGuiasPagamentoEntradaParcelamento.isEmpty()) {

            Iterator colecaoDadosGuiasPagamentoEntradaParcelamentoIterator = colecaoDadosGuiasPagamentoEntradaParcelamento
                    .iterator();

            while (colecaoDadosGuiasPagamentoEntradaParcelamentoIterator
                    .hasNext()) {

                Object[] dadosGuiasPagamentoEntradaParcelamento = (Object[]) colecaoDadosGuiasPagamentoEntradaParcelamentoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[0];
                Integer idUnidadeNegocioConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[1];
                Integer idLocalidadeConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[2];
                Integer idCategoriaConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[3];

                BigDecimal valorCategoria = (BigDecimal) dadosGuiasPagamentoEntradaParcelamento[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 200,
                            LancamentoItem.ENTRADAS_PARCELAMENTO, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosGuiasPagamentoEntradaParcelamento = null;

        }

        // Servi�o
        Collection colecaoDadosGuiaPagamentoServico = repositorioFinanceiro
                .pesquisarDadosGuiasPagamentoCategoriaServico(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosGuiaPagamentoServico != null
                && !colecaoDadosGuiaPagamentoServico.isEmpty()) {

            Iterator colecaoDadosGuiaPagamentoServicoIterator = colecaoDadosGuiaPagamentoServico
                    .iterator();

            while (colecaoDadosGuiaPagamentoServicoIterator.hasNext()) {

                Object[] dadosGuiaPagamentoServico = (Object[]) colecaoDadosGuiaPagamentoServicoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosGuiaPagamentoServico[0];
                Integer idUnidadeNegocioConta = (Integer) dadosGuiaPagamentoServico[1];
                Integer idLocalidadeConta = (Integer) dadosGuiaPagamentoServico[2];
                Integer idCategoriaConta = (Integer) dadosGuiaPagamentoServico[3];
                Integer idLancamentoItemContabil = (Integer) dadosGuiaPagamentoServico[4];

                BigDecimal valorCategoria = (BigDecimal) dadosGuiaPagamentoServico[5];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 200,
                            LancamentoItem.GUIAS_PAGAMENTO, 20, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosGuiaPagamentoServico = null;

        }

    }

    /**
     * [UC0714] Gerar Contas a Receber Cont�bil
     *
     * Adiciona os dados de cr�ditos realizados
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void adicionarContaAReceberContabilCreditosRealizados(Integer idLocalidade, int anoMesAnteriorFaturamento, Collection colecaoContasAReceberContabil) throws ErroRepositorioException {
    	// Pagamento em duplicidade ou excesso
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
				repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPagamentoExcesso(anoMesAnteriorFaturamento, idLocalidade), 
				anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.CONTAS_PAGA_EM_DUPLICIDADE_EXCESSO, 50);

		// Descontos concedidos no parcelamento
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
				repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO), 
				anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO, 60);
		
		// Descontos concedidos no parcelamento Faixa Conta
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
				repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, CreditoOrigem.DESCONTOS_CONCEDIDOS_PARCELAMENTO_FAIXA_CONTA), 
				anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.DESCONTOS_CONCEDIDOS_PARCELAMENTO_FAIXA_CONTA, 65);

		// Descontos condicionais
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
				repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaDescontoCondicional(anoMesAnteriorFaturamento, idLocalidade), 
				anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.DESCONTOS_CONDICIONAIS, 70);

		// Descontos incondicionais
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
				repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaDescontoIncondicional(anoMesAnteriorFaturamento, idLocalidade), 
				anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.DESCONTOS_INCONDICIONAIS, 80);

		// Ajustes para Zerar a Conta
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
				repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, CreditoOrigem.AJUSTES_PARA_ZERAR_CONTA), 
				anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.AJUSTES_PARA_ZERAR_CONTA, 90);

		// Devolu��es
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
				repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaDevolucao(anoMesAnteriorFaturamento, idLocalidade), 
				anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.VALORES_COBRADOS_INDEVIDAMENTE, 95);
		
		// Descontos concedidos no parcelamento Creditos Anteriores Curto e Long Prazo
		Collection colecaoCurtoELongo = repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.DESCONTOS_CREDITOS_ANTERIORES_CURTO_PRAZO);
		colecaoCurtoELongo.addAll(repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.DESCONTOS_CREDITOS_ANTERIORES_LONGO_PRAZO));
		
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil, colecaoCurtoELongo, anoMesAnteriorFaturamento, 
				LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.DESCONTOS_CREDITOS_ANTERIORES, 100);
		
		// Descontos concedidos subsidio Agua Para
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil,
		       repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, CreditoOrigem.BOLSA_AGUA), 
		       anoMesAnteriorFaturamento, LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.SUBSIDIO_AGUA_PARA, 97);
		
		// Descontos concedidos na Recuperacao de cr�dito 
		Collection colecaoRecuperacao = repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.RECUPERACAO_CREDITO_CONTA_CANCELADA);
		colecaoRecuperacao.addAll(repositorioFinanceiro.pesquisarDadosCreditosRealizadosCategoriaPorCreditoOrigem(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.RECUPERACAO_CREDITO_CONTA_PARCELADA));
		
		distribuirCreditosRelizadosContaAReceberContabil(colecaoContasAReceberContabil, colecaoRecuperacao, anoMesAnteriorFaturamento, 
					LancamentoTipo.DOCUMENTOS_EMITIDOS, 100, LancamentoItem.RECUPERACAO_CREDITO, 99);
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void distribuirCreditosRelizadosContaAReceberContabil(
    		Collection colecaoContasAReceberContabil,
    		Collection colecaoDados,
    		int anoMesAnteriorFaturamento,
            Integer idLancamentoTipo, 
            int sequenciaLancamentoTipo,
            Integer idLancamentoItem, 
            int sequenciaLancamentoItem) {
    	
    	if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iterator = colecaoDados.iterator();

			while (iterator.hasNext()) {
				Object[] dados = (Object[]) iterator.next();
				Integer idGerenciaRegional = (Integer) dados[0];
				Integer idUnidadeNegocio = (Integer) dados[1];
				Integer idLocalidade = (Integer) dados[2];
				Integer idCategoria = (Integer) dados[3];
				BigDecimal valor = (BigDecimal) dados[4];

				if (valor != null && valor.doubleValue() > 0) {
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento, 
							idGerenciaRegional, 
							idUnidadeNegocio, 
							idLocalidade, 
							idCategoria, 
							valor.multiply(new BigDecimal("-1")), 
							idLancamentoTipo, 
							sequenciaLancamentoTipo, 
							idLancamentoItem, 
							sequenciaLancamentoItem, 
							null);

					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}
			}
		}
    }
    
    /**
     * [UC0714] Gerar Contas a Receber Cont�bil
     *
     * Adiciona os dados de d�bitos a cobrar
     *
     * @author Rafael Corr�a
     * @date 08/11/2007
     *
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void adicionarContaAReceberContabilDebitosACobrar(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Servi�o
        Collection colecaoDadosDebitosACobrarServico = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaServico(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarServico != null
                && !colecaoDadosDebitosACobrarServico.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarServicoIterator = colecaoDadosDebitosACobrarServico
                    .iterator();

            while (colecaoDadosDebitosACobrarServicoIterator.hasNext()) {

                Object[] dadosDebitosACobrarServico = (Object[]) colecaoDadosDebitosACobrarServicoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarServico[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarServico[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarServico[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarServico[3];
                Integer idLancamentoItemContabil = (Integer) dadosDebitosACobrarServico[4];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarServico[5];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarServico[6];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.FINANCIAMENTOS_A_COBRAR, 400,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO,
                            10, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.FINANCIAMENTOS_A_COBRAR, 400,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO,
                            20, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarServico = null;

        }

        // Documentos Emitidos
        Collection colecaoDadosDebitosACobrarDocumentosEmitidos = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaDocumentosEmitidos(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarDocumentosEmitidos != null
                && !colecaoDadosDebitosACobrarDocumentosEmitidos.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarDocumentosEmitidosIterator = colecaoDadosDebitosACobrarDocumentosEmitidos
                    .iterator();

            while (colecaoDadosDebitosACobrarDocumentosEmitidosIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarDocumentosEmitidos = (Object[]) colecaoDadosDebitosACobrarDocumentosEmitidosIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarDocumentosEmitidos[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarDocumentosEmitidos[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500, LancamentoItem.DOCUMENTOS_EMITIDOS, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600, LancamentoItem.DOCUMENTOS_EMITIDOS, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarDocumentosEmitidos = null;

        }

        // Financimentos A Cobrar de Curto Prazo
        Collection colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaFinancimentosCurtoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo != null
                && !colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo
                        .isEmpty()) {

            Iterator colecaoDadosDebitosACobrarFinanciamentosCurtoPrazoIterator = colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarFinanciamentosCurtoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarFinanciamentosCurtoPrazo = (Object[]) colecaoDadosDebitosACobrarFinanciamentosCurtoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosCurtoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosCurtoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO,
                            20, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO,
                            20, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo = null;

        }

        // Financimentos A Cobrar de Longo Prazo
        Collection colecaoDadosDebitosACobrarFinanciamentosLongoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaFinancimentosLongoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarFinanciamentosLongoPrazo != null
                && !colecaoDadosDebitosACobrarFinanciamentosLongoPrazo
                        .isEmpty()) {

            Iterator colecaoDadosDebitosACobrarFinanciamentosLongoPrazoIterator = colecaoDadosDebitosACobrarFinanciamentosLongoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarFinanciamentosLongoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarFinanciamentosLongoPrazo = (Object[]) colecaoDadosDebitosACobrarFinanciamentosLongoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosLongoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosLongoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO,
                            30, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO,
                            30, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarFinanciamentosLongoPrazo = null;

        }

        // Parcelamentos A Cobrar de Curto Prazo
        Collection colecaoDadosDebitosACobrarParcelamentosCurtoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaParcelamentosCurtoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarParcelamentosCurtoPrazo != null
                && !colecaoDadosDebitosACobrarParcelamentosCurtoPrazo.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarParcelamentosCurtoPrazoIterator = colecaoDadosDebitosACobrarParcelamentosCurtoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarParcelamentosCurtoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarParcelamentosCurtoPrazo = (Object[]) colecaoDadosDebitosACobrarParcelamentosCurtoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosCurtoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosCurtoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            40, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            40, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarParcelamentosCurtoPrazo = null;

        }

        // Parcelamentos A Cobrar de Longo Prazo
        Collection colecaoDadosDebitosACobrarParcelamentosLongoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaParcelamentosLongoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarParcelamentosLongoPrazo != null
                && !colecaoDadosDebitosACobrarParcelamentosLongoPrazo.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarParcelamentosLongoPrazoIterator = colecaoDadosDebitosACobrarParcelamentosLongoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarParcelamentosLongoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarParcelamentosLongoPrazo = (Object[]) colecaoDadosDebitosACobrarParcelamentosLongoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosLongoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosLongoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            50, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            50, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarParcelamentosLongoPrazo = null;

        }
       
        // Juros Cobrados
        Collection colecaoDadosJurosCobrados = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaJurosCobrados(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosJurosCobrados != null
                && !colecaoDadosJurosCobrados.isEmpty()) {

            Iterator colecaoDadosJurosCobradosIterator = colecaoDadosJurosCobrados
                    .iterator();

            while (colecaoDadosJurosCobradosIterator
                    .hasNext()) {

                Object[] dadosJurosCobrados = (Object[]) colecaoDadosJurosCobradosIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosJurosCobrados[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosJurosCobrados[1];
                Integer idLocalidadeDebito = (Integer) dadosJurosCobrados[2];
                Integer idCategoriaDebito = (Integer) dadosJurosCobrados[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosJurosCobrados[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosJurosCobrados[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.JUROS_COBRADOS,
                            55, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.JUROS_COBRADOS,
                            55, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosJurosCobrados = null;

        }

        // Arrasto
        Collection colecaoDadosDebitosACobrarArrasto = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaArrasto(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarArrasto != null
                && !colecaoDadosDebitosACobrarArrasto.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarArrastoIterator = colecaoDadosDebitosACobrarArrasto
                    .iterator();

            while (colecaoDadosDebitosACobrarArrastoIterator.hasNext()) {

                Object[] dadosDebitosACobrarArrasto = (Object[]) colecaoDadosDebitosACobrarArrastoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarArrasto[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarArrasto[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarArrasto[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarArrasto[3];

                BigDecimal valorCategoria = (BigDecimal) dadosDebitosACobrarArrasto[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCategoria,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA,
                            60, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarArrasto = null;

        }

    }

    /**
     * [UC0714] Gerar Contas a Receber Cont�bil
     *
     * Adiciona os dados de cr�ditos a realizar
     *
     * @author Rafael Corr�a, Ivan Sergio
     * @date 08/11/2007, 30/12/2010
     *
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void adicionarContaAReceberContabilCreditosARealizar(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

    	Collection contasDescontoParcelamento = obterContaContabilCreditoARealizarCurtoELongoPrazo(anoMesAnteriorFaturamento, idLocalidade, 
    			CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO,
    			LancamentoItem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO,
    			500, 70, 600, 60);
    	
    	if (contasDescontoParcelamento != null && !contasDescontoParcelamento.isEmpty()) {
    		colecaoContasAReceberContabil.addAll(contasDescontoParcelamento);
    	}
    	
		Collection contasDescontoParcelamentoFaixaConta = obterContaContabilCreditoARealizarCurtoELongoPrazo(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.DESCONTOS_CONCEDIDOS_PARCELAMENTO_FAIXA_CONTA,
				LancamentoItem.DESCONTOS_CONCEDIDOS_PARCELAMENTO_FAIXA_CONTA,
				700, 10, 800, 20);

    	if (contasDescontoParcelamentoFaixaConta != null && !contasDescontoParcelamentoFaixaConta.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasDescontoParcelamentoFaixaConta);
		}

    	Collection contasDescontoCreditosAnterioresCurtoPrazo = obterContaContabilCreditoARealizarCurtoELongoPrazo(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.DESCONTOS_CREDITOS_ANTERIORES_CURTO_PRAZO,
				LancamentoItem.DESCONTOS_CONCEDIDOS_PARCELAMENTO_CREDITOS_ANTERIORES_CURTO_PRAZO,
				900, 10,1000, 20);

    	if (contasDescontoCreditosAnterioresCurtoPrazo != null && !contasDescontoCreditosAnterioresCurtoPrazo.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasDescontoCreditosAnterioresCurtoPrazo);
		}

    	Collection contasDescontoCreditosAnterioresLongoPrazo = obterContaContabilCreditoARealizarCurtoELongoPrazo(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.DESCONTOS_CREDITOS_ANTERIORES_LONGO_PRAZO,
				LancamentoItem.DESCONTOS_CONCEDIDOS_PARCELAMENTO_CREDITOS_ANTERIORES_LONGO_PRAZO,
				1100, 10,1200, 20);

    	if (contasDescontoCreditosAnterioresLongoPrazo != null && !contasDescontoCreditosAnterioresLongoPrazo.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasDescontoCreditosAnterioresLongoPrazo);
		}
    	
    	//CONTAS PAGAS EM DUPLICIDADE EXCESSO
    	Collection contasDuplicidade = obterContaContabilCreditoARealizarDuplicidade(idLocalidade, anoMesAnteriorFaturamento);
    	
    	if (contasDuplicidade != null && !contasDuplicidade.isEmpty()) {
    		colecaoContasAReceberContabil.addAll(contasDuplicidade);
    	}
    	    	
        //DESCONTOS INCONDICIONAIS
		Collection contasDescontosIncondicionais = obterContaContabilCreditoARealizar(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.DESCONTOS_INCONDICIONAIS, 
				400,LancamentoItem.DESCONTOS_INCONDICIONAIS, 40, null); 
		
		if (contasDescontosIncondicionais != null && !contasDescontosIncondicionais.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasDescontosIncondicionais);
		}
		
		//DESCONTOS CONDICIONAIS
		Collection contasDescontosCondicionais = obterContaContabilCreditoARealizar(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.VALORES_COBRADOS_INDEVIDAMENTE, 
				400,LancamentoItem.DESCONTOS_CONDICIONAIS, 35, null);
		
		if (contasDescontosCondicionais != null && !contasDescontosCondicionais.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasDescontosCondicionais);
		}
		
		//AJUSTES PARA ZERAR CONTA
		Collection contasAjustesParaZerarConta = obterContaContabilCreditoARealizar(anoMesAnteriorFaturamento, idLocalidade, 
				CreditoOrigem.AJUSTES_PARA_ZERAR_CONTA, 
				400, LancamentoItem.AJUSTES_PARA_ZERAR_CONTA, 50, null);
		
		if (contasAjustesParaZerarConta != null && !contasAjustesParaZerarConta.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasAjustesParaZerarConta);
		}
		
    	Collection contasDevolucaoJuros = obterContaContabilCreditoARealizar(anoMesAnteriorFaturamento, idLocalidade, 
    	        CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO, 
    	        400, LancamentoItem.DEVOLUCAO_JUROS_PARCELAMENTO, 80, null);
    	    
	    if (contasDevolucaoJuros != null && !contasDevolucaoJuros.isEmpty()) {
	      colecaoContasAReceberContabil.addAll(contasDevolucaoJuros);
	    }
	    
	    
	    Collection contasContabeisDevolucaoAgua = obterContaContabilCreditoARealizar(anoMesAnteriorFaturamento, idLocalidade, 
	        CreditoOrigem.DEVOLUCAO_TARIFA_AGUA, 
	        400, LancamentoItem.DEVOLUCAO_TARIFA_AGUA, 90, null);
	    
	    if (contasContabeisDevolucaoAgua != null && !contasContabeisDevolucaoAgua.isEmpty()) {
	      colecaoContasAReceberContabil.addAll(contasContabeisDevolucaoAgua);
	    }
	    
	    
	    Collection contasContabeisDevolucaoEsgoto = obterContaContabilCreditoARealizar(anoMesAnteriorFaturamento, idLocalidade, 
	        CreditoOrigem.DEVOLUCAO_TARIFA_ESGOTO, 
	        400, LancamentoItem.DEVOLUCAO_TARIFA_ESGOTO, 100, null);
	    
	    if (contasContabeisDevolucaoEsgoto != null && !contasContabeisDevolucaoEsgoto.isEmpty()) {
	      colecaoContasAReceberContabil.addAll(contasContabeisDevolucaoEsgoto);
	    }
	    
	    
	    Collection contasContabeisServicosIndiretos = obterContaContabilCreditoARealizar(anoMesAnteriorFaturamento, idLocalidade, 
	        CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE, 
	        400, LancamentoItem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE, 110, null);
	    
	    if (contasContabeisServicosIndiretos != null && !contasContabeisServicosIndiretos.isEmpty()) {
	      colecaoContasAReceberContabil.addAll(contasContabeisServicosIndiretos);
	    }
	    
	    // RECUPERACAO DE CR�DITO 
 		Collection contasRecuperacoCredito = obterContaContabilCreditoARealizarRecperacaoCredito(anoMesAnteriorFaturamento, idLocalidade);
 		
 		if (contasRecuperacoCredito != null && !contasRecuperacoCredito.isEmpty()) {
 			colecaoContasAReceberContabil.addAll(contasRecuperacoCredito);
 		}
		
	}

    @SuppressWarnings("rawtypes")
	private Collection obterContaContabilCreditoARealizarCurtoELongoPrazo(Integer anoMesAnteriorFaturamento, Integer idLocalidade,
    		Integer idCreditoOrigem, Integer idLancamentoItem, 
    		int sequenciaLancamentoTipoCurtoPrazo, int sequenciaLancamentoItemCurtoPrazo,
            int sequenciaLancamentoTipoLongoPrazo, int sequenciaLancamentoItemLongoPrazo) throws ErroRepositorioException {
    	
    	Collection colecaoContas = new ArrayList();
    	
        Collection colecaoCreditosARealizar = repositorioFinanceiro.pesquisarDadosCreditosARealizarCategoriaDescontosParcelamento(
        		anoMesAnteriorFaturamento, idLocalidade, idCreditoOrigem);
        
        Collection<Object[]> colecaoCreditosARealizarValorResidual = repositorioFinanceiro
        		.pesquisarDadosCreditosARealizarCategoriaValorResidualDescontosParcelamento(anoMesAnteriorFaturamento, idLocalidade, idCreditoOrigem);

        if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()) {

            Iterator colecaoCreditosARealizarIterator = colecaoCreditosARealizar.iterator();

            while (colecaoCreditosARealizarIterator.hasNext()) {

                Object[] dados = (Object[]) colecaoCreditosARealizarIterator.next();

                Integer idGerenciaRegionalCredito = (Integer) dados[0];
                Integer idUnidadeNegocioCredito = (Integer) dados[1];
                Integer idLocalidadeCredito = (Integer) dados[2];
                Integer idCategoriaCredito = (Integer) dados[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dados[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dados[5];
                
                if (colecaoCreditosARealizarValorResidual != null && !colecaoCreditosARealizarValorResidual.isEmpty()) {
                	
                	Collection colecaoRemovidos = new ArrayList<Object[]>();
                	
                	for (Object[] dadosValorResidual : colecaoCreditosARealizarValorResidual) {
                        Integer idCategoriaValorResidual = (Integer) dadosValorResidual[3];

                        BigDecimal valorResidual = (BigDecimal) dadosValorResidual[4];
                        
                        if (idCategoriaValorResidual.equals(idCategoriaCredito)) {
                        	if (valorResidual != null) {
                        		valorCurtoPrazo = valorCurtoPrazo.add(valorResidual);
                        	}
                        	colecaoRemovidos.add(dadosValorResidual);
                        }
                        
					}
                	
                	colecaoCreditosARealizarValorResidual.removeAll(colecaoRemovidos);
                	
                }

                // Curto Prazo
                if (valorCurtoPrazo != null && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCredito, idUnidadeNegocioCredito,
                            idLocalidadeCredito, idCategoriaCredito,
                            valorCurtoPrazo.multiply(new BigDecimal("-1")),
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500, idLancamentoItem, 70, null);

                    colecaoContas.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCredito, idUnidadeNegocioCredito,
                            idLocalidadeCredito, idCategoriaCredito,
                            valorLongoPrazo.multiply(new BigDecimal("-1")),
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600, idLancamentoItem, 60, null);

                    colecaoContas.add(contaAReceberContabil);
                }

            }

            colecaoCreditosARealizar = null;

        }
        
        // Verifica se restou algum dado que n�o estava presente na colecaoDadosCreditosARealizarDescontoParcelamento
        if (colecaoCreditosARealizarValorResidual != null && !colecaoCreditosARealizarValorResidual.isEmpty()) {
        	for (Object[] dadosCreditosARealizarValorDescontoParcelamento : colecaoCreditosARealizarValorResidual) {
        		
        		Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[0];
        		Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[1];
                Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[2];
                Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[3];
                BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorDescontoParcelamento[4];
                
                if (valorResidual != null
                        && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCreditoValorResidual, idUnidadeNegocioCreditoValorResidual,
                            idLocalidadeCreditoValorResidual, idCategoriaCreditoValorResidual,
                            valorResidual.multiply(new BigDecimal("-1")),
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500, idLancamentoItem, 70, null);

                    colecaoContas.add(contaAReceberContabil);
                }
			}
        	
        	colecaoCreditosARealizarValorResidual = null;
        }
        
        return colecaoContas;

    }
    
	private Collection obterContaContabilCreditoARealizarValoresCobradosIndevidamente(Integer idLocalidade, int anoMesAnteriorFaturamento) throws ErroRepositorioException {
		
		Collection contasContabeis = new ArrayList();
		
		Collection colecaoDevolucoes = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaDevolucao(
                        anoMesAnteriorFaturamento, idLocalidade);
        
        // Valor Residual Devolu��o
        Collection<Object[]> colecaoValorResidualDevolucoes = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualDevolucao(
                        anoMesAnteriorFaturamento, idLocalidade);

        Collection contasCreditoARealizar = obterContaAReceberContabilCreditoARealizar(anoMesAnteriorFaturamento,
        		colecaoDevolucoes, 
        		colecaoValorResidualDevolucoes,
        		400,LancamentoItem.VALORES_COBRADOS_INDEVIDAMENTE, 30, null);
		
		if (contasCreditoARealizar != null && !contasCreditoARealizar.isEmpty()) {
			contasContabeis.addAll(contasCreditoARealizar);
		}
		
		Collection contasCreditoARealizarResidual = obterContaAReceberContabilResidual(anoMesAnteriorFaturamento, 
				colecaoValorResidualDevolucoes,
				400,LancamentoItem.VALORES_COBRADOS_INDEVIDAMENTE, 30, null);
		
		if (contasCreditoARealizarResidual != null && !contasCreditoARealizarResidual.isEmpty()) {
			contasContabeis.addAll(contasCreditoARealizarResidual);
		}
		contasCreditoARealizar.clear();
		contasCreditoARealizarResidual.clear();
		
		return contasContabeis;
	}


	private Collection obterContaContabilCreditoARealizarDuplicidade(Integer idLocalidade, int anoMesAnteriorFaturamento) throws ErroRepositorioException {
		Collection contasCreditoARealizar;
		Collection contasCreditoARealizarResidual;
		
		Collection colecaoContasAReceberContabil = new ArrayList();
		
		// Pagamento em duplicidade ou excesso
		Collection colecaoDadosCreditosARealizarPagamentoExcesso = repositorioFinanceiro
				.pesquisarDadosCreditosARealizarCategoriaPagamentoExcesso(
						anoMesAnteriorFaturamento, idLocalidade);
		
        // Valor Residual Pagamentos em Duplicidade ou Excesso
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualPagamentoExcesso = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualPagamentoExcesso(
                        anoMesAnteriorFaturamento, idLocalidade);

        contasCreditoARealizar = obterContaAReceberContabilCreditoARealizar(anoMesAnteriorFaturamento,
        		colecaoDadosCreditosARealizarPagamentoExcesso, 
        		colecaoDadosCreditosARealizarValorResidualPagamentoExcesso,
        		400,LancamentoItem.CONTAS_PAGA_EM_DUPLICIDADE_EXCESSO, 25, null);
		
		if (contasCreditoARealizar != null && !contasCreditoARealizar.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizar);
		}
		
		contasCreditoARealizarResidual = obterContaAReceberContabilResidual(anoMesAnteriorFaturamento, 
				colecaoDadosCreditosARealizarValorResidualPagamentoExcesso,
				400,LancamentoItem.CONTAS_PAGA_EM_DUPLICIDADE_EXCESSO, 25, null);
		
		if (contasCreditoARealizarResidual != null && !contasCreditoARealizarResidual.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizarResidual);
		}
		contasCreditoARealizar.clear();
		contasCreditoARealizarResidual.clear();
		
		return colecaoContasAReceberContabil;
	}

	private Collection obterContaContabilCreditoARealizarDescontosCondicionais(Integer idLocalidade, int anoMesAnteriorFaturamento) throws ErroRepositorioException {
		Collection contasCreditoARealizar;
		Collection contasCreditoARealizarResidual;
		
		Collection colecaoContasAReceberContabil = new ArrayList();
		
		// Descontos condicionais
		Collection colecaoDadosCreditosARealizarDescontoCondicional = repositorioFinanceiro
				.pesquisarDadosCreditosARealizarCategoriaDescontoCondicional(
						anoMesAnteriorFaturamento, idLocalidade);
		
        // Valor Residual Descontos Condicionais
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualDescontoCondicional = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoCondicional(
                        anoMesAnteriorFaturamento, idLocalidade);

        contasCreditoARealizar = obterContaAReceberContabilCreditoARealizar(anoMesAnteriorFaturamento,
        		colecaoDadosCreditosARealizarDescontoCondicional, 
        		colecaoDadosCreditosARealizarValorResidualDescontoCondicional,
        		400,LancamentoItem.DESCONTOS_CONDICIONAIS, 35, null);
		
		if (contasCreditoARealizar != null && !contasCreditoARealizar.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizar);
		}
		
		contasCreditoARealizarResidual = obterContaAReceberContabilResidual(anoMesAnteriorFaturamento, 
				colecaoDadosCreditosARealizarValorResidualDescontoCondicional,
				400,LancamentoItem.DESCONTOS_CONDICIONAIS, 35, null);
		
		if (contasCreditoARealizarResidual != null && !contasCreditoARealizarResidual.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizarResidual);
		}
		contasCreditoARealizar.clear();
		contasCreditoARealizarResidual.clear();
		
		return colecaoContasAReceberContabil;
	}

	private Collection  obterContaContabilCreditoARealizarAjustesParaZerarConta(Integer idLocalidade, int anoMesAnteriorFaturamento) throws ErroRepositorioException {
		Collection contasCreditoARealizar;
		Collection contasCreditoARealizarResidual;
		
		Collection colecaoContasAReceberContabil = new ArrayList();
	
		// Ajustes para Zerar a Conta
		Collection colecaoDadosCreditosARealizarAjusteZerarConta = repositorioFinanceiro.pesquisarDadosCreditosARealizarCategoriaAjusteZerarConta(anoMesAnteriorFaturamento, idLocalidade);
		
        // Valor Residual Ajustes para Zerar a Conta
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualAjusteZerarConta = repositorioFinanceiro.pesquisarDadosCreditosARealizarCategoriaValorResidualAjusteZerarConta(
                        anoMesAnteriorFaturamento, idLocalidade);

        contasCreditoARealizar = obterContaAReceberContabilCreditoARealizar(anoMesAnteriorFaturamento,
        		colecaoDadosCreditosARealizarAjusteZerarConta, 
        		colecaoDadosCreditosARealizarValorResidualAjusteZerarConta,
        		400, LancamentoItem.AJUSTES_PARA_ZERAR_CONTA, 50, null);
		
		if (contasCreditoARealizar != null && !contasCreditoARealizar.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizar);
		}
		
		contasCreditoARealizarResidual = obterContaAReceberContabilResidual(anoMesAnteriorFaturamento, 
				colecaoDadosCreditosARealizarValorResidualAjusteZerarConta,
				400, LancamentoItem.AJUSTES_PARA_ZERAR_CONTA, 50, null);
		
		if (contasCreditoARealizarResidual != null && !contasCreditoARealizarResidual.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizarResidual);
		}
		contasCreditoARealizar.clear();
		contasCreditoARealizarResidual.clear();
		
		return colecaoContasAReceberContabil;
	}

    private Collection obterContaContabilCreditoARealizarRecperacaoCredito(Integer anoMesAnteriorFaturamento, Integer idLocalidade) 
    		throws ErroRepositorioException {
    	Collection colecaoContasAReceberContabil = new ArrayList();
    	Collection colecaotemporaria = null;
    	
    	colecaotemporaria = obterContaContabilCreditoARealizarPorOrigemCredito(anoMesAnteriorFaturamento, idLocalidade, 
    			CreditoOrigem.RECUPERACAO_CREDITO_CONTA_CANCELADA, 400, LancamentoItem.RECUPERACAO_CREDITO_CONTA_CANCELADA, 60, null);
    	
    	if (colecaotemporaria != null & !colecaotemporaria.isEmpty()) {
    		colecaoContasAReceberContabil.addAll(colecaotemporaria);
    	}
    	colecaotemporaria.clear();
    	
    	colecaotemporaria = obterContaContabilCreditoARealizarPorOrigemCredito(anoMesAnteriorFaturamento, idLocalidade, 
    			CreditoOrigem.RECUPERACAO_CREDITO_CONTA_PARCELADA, 400, LancamentoItem.RECUPERACAO_CREDITO_CONTA_PARCELADA, 70, null);
    	
    	if (colecaotemporaria != null & !colecaotemporaria.isEmpty()) {
    		colecaoContasAReceberContabil.addAll(colecaotemporaria);
    	}
    	colecaotemporaria.clear();
    	
    	return colecaoContasAReceberContabil;
    }
    
    private Collection obterContaContabilCreditoARealizar(Integer anoMesAnteriorFaturamento, Integer idLocalidade,
    		Integer creditoOrigem, int sequenciaLancamentoTipo,
            Integer idLancamentoItem, int sequenciaLancamentoItem,
            Integer idLancamentoItemContabil) 
    		throws ErroRepositorioException {
    	Collection colecaoContasAReceberContabil = new ArrayList();
    	Collection colecaotemporaria = null;
    	
    	colecaotemporaria = obterContaContabilCreditoARealizarPorOrigemCredito(anoMesAnteriorFaturamento, idLocalidade, 
    			creditoOrigem, sequenciaLancamentoTipo, idLancamentoItem, sequenciaLancamentoItem, idLancamentoItemContabil);
    	
    	if (colecaotemporaria != null & !colecaotemporaria.isEmpty()) {
    		colecaoContasAReceberContabil.addAll(colecaotemporaria);
    	}
    	colecaotemporaria.clear();
    	
    	return colecaoContasAReceberContabil;
    }
    
    
    private Collection obterContaContabilCreditoARealizarPorOrigemCredito(Integer anoMesAnteriorFaturamento, Integer idLocalidade, 
    		Integer creditoOrigem, int sequenciaLancamentoTipo,
            Integer idLancamentoItem, int sequenciaLancamentoItem,
            Integer idLancamentoItemContabil) 
    		throws ErroRepositorioException {
    		
    	Collection colecaoContasAReceberContabil = new ArrayList();
    		
		Collection colecaoDadosCreditosARealizarRecuperacaoCreditoContasCanceladas = repositorioFinanceiro.pesquisarDadosCreditosARealizar(
				anoMesAnteriorFaturamento, idLocalidade,creditoOrigem);

		Collection<Object[]> colecaoDadosCreditosARealizarValorResidualRecuperacaoCreditoContasCanceladas = repositorioFinanceiro
				.pesquisarDadosCreditosARealizarValorResidual(anoMesAnteriorFaturamento, idLocalidade, creditoOrigem);

		
		Collection contasCreditoARealizar = obterContaAReceberContabilCreditoARealizar(anoMesAnteriorFaturamento,
				colecaoDadosCreditosARealizarRecuperacaoCreditoContasCanceladas, 
				colecaoDadosCreditosARealizarValorResidualRecuperacaoCreditoContasCanceladas,
				sequenciaLancamentoTipo, idLancamentoItem, sequenciaLancamentoItem, idLancamentoItemContabil);
		
		if (contasCreditoARealizar != null && !contasCreditoARealizar.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizar);
		}
		contasCreditoARealizar.clear();
		
		contasCreditoARealizar = obterContaAReceberContabilResidual(anoMesAnteriorFaturamento, 
				colecaoDadosCreditosARealizarValorResidualRecuperacaoCreditoContasCanceladas,
				sequenciaLancamentoTipo, idLancamentoItem, sequenciaLancamentoItem, idLancamentoItemContabil);
		
		if (contasCreditoARealizar != null && !contasCreditoARealizar.isEmpty()) {
			colecaoContasAReceberContabil.addAll(contasCreditoARealizar);
		}
		contasCreditoARealizar.clear();

		return colecaoContasAReceberContabil;
    }
    
	@SuppressWarnings("unchecked")
	private Collection obterContaAReceberContabilResidual(int anoMesAnteriorFaturamento, 
			Collection<Object[]> colecaoValorResidual, int sequenciaLancamentoTipo,
            Integer idLancamentoItem, int sequenciaLancamentoItem,
            Integer idLancamentoItemContabil) {
		
		Collection colecaoContasAReceberContabil = new ArrayList();
		if (colecaoValorResidual != null && !colecaoValorResidual.isEmpty()) {
			for (Object[] dadosCreditosARealizarValorResidual : colecaoValorResidual) {

				Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidual[0];
				Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidual[1];
				Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidual[2];
				Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidual[3];
				BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidual[4];

				if (valorResidual != null && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
					// Cria o objeto com os valores passados
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCreditoValorResidual,
							idUnidadeNegocioCreditoValorResidual,
							idLocalidadeCreditoValorResidual,
							idCategoriaCreditoValorResidual,
							valorResidual.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR,sequenciaLancamentoTipo,
				            idLancamentoItem, sequenciaLancamentoItem,
				            idLancamentoItemContabil);

					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}
			}

			colecaoValorResidual = null;
		}
		
		return colecaoContasAReceberContabil;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection obterContaAReceberContabilCreditoARealizar(int anoMesAnteriorFaturamento,
			Collection colecaoCreditosARealizar,
			Collection<Object[]> colecaoCreditosARealizarValorResidual,
			int sequenciaLancamentoTipo,
            Integer idLancamentoItem, int sequenciaLancamentoItem,
            Integer idLancamentoItemContabil) {
		
		Collection colecaoContasAReceberContabil = new ArrayList();
		
		if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()) {

			Iterator colecaoCreditosARealizarIterator = colecaoCreditosARealizar.iterator();

			while (colecaoCreditosARealizarIterator.hasNext()) {

				Object[] dadosCreditosARealizar = (Object[]) colecaoCreditosARealizarIterator.next();

				Integer idGerenciaRegionalCredito = (Integer) dadosCreditosARealizar[0];
				Integer idUnidadeNegocioCredito = (Integer) dadosCreditosARealizar[1];
				Integer idLocalidadeCredito = (Integer) dadosCreditosARealizar[2];
				Integer idCategoriaCredito = (Integer) dadosCreditosARealizar[3];

				BigDecimal valorCategoria = (BigDecimal) dadosCreditosARealizar[4];

				if (colecaoCreditosARealizarValorResidual != null && !colecaoCreditosARealizarValorResidual.isEmpty()) {

					Collection colecaoRemovidos = new ArrayList<Object[]>();

					for (Object[] dadosCreditosARealizarValorResidual : colecaoCreditosARealizarValorResidual) {
						Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidual[3];

						BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidual[4];

						if (idCategoriaCreditoValorResidual.equals(idCategoriaCredito)) {

							if (valorResidual != null) {
								valorCategoria = valorCategoria.add(valorResidual);
							}

							colecaoRemovidos.add(dadosCreditosARealizarValorResidual);
							break;
						}
					}
					colecaoCreditosARealizarValorResidual.removeAll(colecaoRemovidos);
				}

				if (valorCategoria != null && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {
					
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCredito,
							idUnidadeNegocioCredito,
							idLocalidadeCredito,
							idCategoriaCredito,
							valorCategoria.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR,sequenciaLancamentoTipo,
				            idLancamentoItem, sequenciaLancamentoItem,
				            idLancamentoItemContabil);
					
					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}
			}
			colecaoCreditosARealizar = null;
		}
		return colecaoContasAReceberContabil;
	}
	
	/**
	 * [UC0751] Gerar Valor Referente a Volumes Consumidos e N�o Faturados
	 * 
	 * M�todo respons�vel pela gera��o de valor dos volumes consumidos e n�o faturados
	 * 
	 * @author Rafael Corr�a, Pedro Alexandre
	 * @date 19/02/2008, 08/07/2008
	 * 
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	@SuppressWarnings("unchecked")
	public void gerarValorVolumesConsumidosNaoFaturados(Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException {

		System.out.println("LOCALIDADE " + idLocalidade);
		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE, idLocalidade);

		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();


		try {
			//Sistema verifica se todos os grupos j� foram faturados
			Collection<FaturamentoGrupo> colecaoFaturamentoGrupoNaoFaturados = repositorioFaturamento.pesquisarFaturamentoGrupoNaoFaturados(sistemaParametro.getAnoMesFaturamento());
			//[FS0002] - Verificar exist�ncia de grupo n�o faturado
			if (colecaoFaturamentoGrupoNaoFaturados != null	&& !colecaoFaturamentoGrupoNaoFaturados.isEmpty()) {
				throw new ControladorException("atencao.existe.grupo.nao.faturado");
			}

			Collection colecaoValorVolumesConsumidosNaoFaturados = new ArrayList();

			// item 2
			// exclui os dados referentes ao valor dos volumes consumidos e n�o
			// faturados do m�s de refer�ncia do faturamento corrente
			repositorioFinanceiro.removerValorVolumesConsumidosNaoFaturados(sistemaParametro.getAnoMesFaturamento(), idLocalidade);

			Integer idGerenciaRegional = this.getControladorLocalidade().pesquisarIdGerenciaParaLocalidade(idLocalidade);
			//System.out.println("Gerencia Regional -> " + idGerenciaRegional);
			
			Integer idUnidadeNegocio = this.getControladorLocalidade().pesquisarIdUnidadeNegocioParaLocalidade(idLocalidade);
			
			System.out.println("Pesquisando");
			// Valores de �gua e Esgoto
			this.adicionarValorVolumesConsumidosNaoFaturadosAguaEsgoto(idLocalidade,sistemaParametro.getAnoMesFaturamento(), colecaoValorVolumesConsumidosNaoFaturados,idGerenciaRegional,idUnidadeNegocio);
			System.out.println("Fim Pesquisando");
			
			if (colecaoValorVolumesConsumidosNaoFaturados != null && !colecaoValorVolumesConsumidosNaoFaturados.isEmpty()) {
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoValorVolumesConsumidosNaoFaturados);
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

			System.out.println("fim da gera��o " + "Localidade " + idLocalidade);

		} catch (Exception e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC0714] Gerar Contas a Receber Cont�bil
	 * 
	 * Cria o objeto de ContaAReceberContabil de acordo com os par�metros
	 * passados
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 */
	private ValorVolumesConsumidosNaoFaturado criarValorVolumesConsumidosNaoFaturado(
			int anoMesReferencia, Integer idUnidadeNegocio,
			Integer idGerenciaRegional, Integer idLocalidade,
			Integer idCategoria, BigDecimal valorItem,
			Integer idLancamentoTipo, int sequenciaLancamentoTipo,
			Integer idLancamentoItem, int sequenciaLancamentoItem) {

		ValorVolumesConsumidosNaoFaturado retorno = new ValorVolumesConsumidosNaoFaturado();

		retorno.setAnoMesReferencia(anoMesReferencia);

		// Ger�ncia Regional
		GerenciaRegional gerenciaRegional = new GerenciaRegional();
		gerenciaRegional.setId(idGerenciaRegional);
		retorno.setGerenciaRegional(gerenciaRegional);

		// Unidade Neg�cio
		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		unidadeNegocio.setId(idUnidadeNegocio);
		retorno.setUnidadeNegocio(unidadeNegocio);

		// Localidade
		Localidade localidade = new Localidade();
		localidade.setId(idLocalidade);
		retorno.setLocalidade(localidade);

		// Categoria
		Categoria categoria = new Categoria();
		categoria.setId(idCategoria);
		retorno.setCategoria(categoria);

		// Valor Acumulado
		retorno.setValorItemLancamento(valorItem);

		// Lan�amento Tipo
		LancamentoTipo lancamentoTipo = new LancamentoTipo();
		lancamentoTipo.setId(idLancamentoTipo);
		retorno.setLancamentoTipo(lancamentoTipo);

		// Seq��ncia do Lan�amento Tipo
		retorno.setNumeroSequenciaTipoLancamento(sequenciaLancamentoTipo);

		// Lan�amento Item
		LancamentoItem lancamentoItem = new LancamentoItem();
		lancamentoItem.setId(idLancamentoItem);
		retorno.setLancamentoItem(lancamentoItem);

		// Seq��ncia do Lan�amento Item
		retorno.setNumeroSequenciaItemTipoLancamento(sequenciaLancamentoItem);

		return retorno;

	}
	
	/**
	 * [UC0751] Gerar Valor Referente a Volumes Consumidos e N�o Faturados
	 * 
	 * Adiciona os dados de �gua e esgoto
	 * 
	 * @author Rafael Corr�a, Pedro Alexandre
	 * @date 08/11/2007, 08/06/2008
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private void adicionarValorVolumesConsumidosNaoFaturadosAguaEsgoto(
			Integer idLocalidade,
			int anoMesFaturamento,
			Collection colecaoValorVolumesConsumidosNaoFaturados, 
			Integer idGerenciaRegional, 
			Integer idUnidadeNegocio)	throws ErroRepositorioException {
		
		int mes = Util.obterMes(anoMesFaturamento);
		int ano = Util.obterAno(anoMesFaturamento);
		
		int dia = new Integer(Util.obterUltimoDiaMes(mes, ano));
		
		Date ultimoDiaMesFaturamento = Util.criarData(dia, mes, ano);
		
		Collection colecaoDadosValorAguaEsgoto = repositorioFinanceiro.pesquisarDadosValorVolumesConsumidosNaoFaturadosAguaEsgoto(anoMesFaturamento, idLocalidade, ultimoDiaMesFaturamento);

		if (colecaoDadosValorAguaEsgoto != null	&& !colecaoDadosValorAguaEsgoto.isEmpty()) {

			Iterator colecaoDadosValorAguaEsgotoIterator = colecaoDadosValorAguaEsgoto.iterator();

			while (colecaoDadosValorAguaEsgotoIterator.hasNext()) {

				Object[] dadosValorAguaEsgoto = (Object[]) colecaoDadosValorAguaEsgotoIterator.next();

				Integer idCategoriaConta = (Integer) dadosValorAguaEsgoto[0];

				BigDecimal valorAgua = (BigDecimal) dadosValorAguaEsgoto[1];

				BigDecimal valorEsgoto = (BigDecimal) dadosValorAguaEsgoto[2];

				// �gua
				if (valorAgua != null && valorAgua.compareTo(BigDecimal.ZERO) != 0) {

					// Cria o objeto com os valores passados
					ValorVolumesConsumidosNaoFaturado volumesConsumidosNaoFaturado = this.criarValorVolumesConsumidosNaoFaturado(
							anoMesFaturamento, 
							idUnidadeNegocio,
							idGerenciaRegional,
							idLocalidade,
							idCategoriaConta, 
							valorAgua,
							LancamentoTipo.VALOR_REFERENTE_VOLUMES_NAO_FATURADOS, 
							100,
							LancamentoItem.AGUA, 
							10);

					colecaoValorVolumesConsumidosNaoFaturados.add(volumesConsumidosNaoFaturado);
				}

				// Esgoto
				if (valorEsgoto != null	&& valorEsgoto.compareTo(BigDecimal.ZERO) != 0) {

					// Cria o objeto com os valores passados
					ValorVolumesConsumidosNaoFaturado volumesConsumidosNaoFaturado = this.criarValorVolumesConsumidosNaoFaturado(
							anoMesFaturamento, 
							idUnidadeNegocio,
							idGerenciaRegional,
							idLocalidade,
							idCategoriaConta, 
							valorEsgoto,
							LancamentoTipo.VALOR_REFERENTE_VOLUMES_NAO_FATURADOS, 
							100,
							LancamentoItem.ESGOTO, 
							20);

					colecaoValorVolumesConsumidosNaoFaturados.add(volumesConsumidosNaoFaturado);

				}

			}
			colecaoDadosValorAguaEsgoto = null;
		}
	}

	
	/**
	 * <Breve descri��o sobre o caso de uso>
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
	public ParametrosDevedoresDuvidosos pesquisarParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil)	throws ControladorException {
		try {
			return this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	
	
	/**
	 * Gera os lan�amentos dos devedores duvidosos.
	 *
	 * [UC0486] Gerar Lan�amentos Cont�beis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	public void gerarLancamentosContabeisDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException{
		
		
		System.out.println("Localidade " + idLocalidade);

		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));

		
		//[FS0001 - Validar Refer�ncia Cont�bil]
		Integer anoMesArrecadacaoAtual =  getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao();
		if(anoMesReferenciaContabil.intValue() >anoMesArrecadacaoAtual.intValue()){
			//levanta a exce��o para a pr�xima camada
			throw new ControladorException("atencao.mes_ano.contabil.superior",null,Util.formatarAnoMesParaMesAno(anoMesArrecadacaoAtual.toString()));
		}

		//remove os lan�amentos cont�beis j� gerados
		this.removerLancamentoContabil(anoMesReferenciaContabil,idLocalidade,LancamentoOrigem.DEVEDORES_DUVIDOSOS);
		
		try {
			
			/*
			 *  Pesquisa os dados do resumo dos devedores duvidosos 
			 *  para o ano/m�s de refer�ncia atual e para a localidade informada.
			 *  
			 *  0 - id da localidade
			 *  1 - id do tipo de lan�amento
			 *  2 - id do item de lan�amento
			 *  3 - id do item de lan�amento cont�bil
			 *  4 - id da categoria
			 *  5 - soma do valor do resumo dos devedores duvidosos 
			 */
			Collection<Object[]> colecaoDadosResumoDevedoresDuvidosos = repositorioFinanceiro.obterDadosResumoDevedoresDuvidosos(anoMesReferenciaContabil, idLocalidade);
		
			/*
			 * Caso exista resumo de devedores duvidosos para a localidade e o ano/m�s 
			 * cria o lancamento cont�bil junto com seus items 
			 * para cada conjunto de mesmo tipo de lan�amento
			 */
			if (colecaoDadosResumoDevedoresDuvidosos != null && !colecaoDadosResumoDevedoresDuvidosos.isEmpty()){
				
				//flag utilizada somente quando for a primeira entrada
				boolean flagPrimeiraVez = true;
				int idTipoLancamentoTemp = -1;
				Collection<Object[]> colecaoDadosResumoPorTipoLancamento = new ArrayList();
				
				// defini��o da origem do lan�amento
				LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
				lancamentoOrigem.setId(LancamentoOrigem.DEVEDORES_DUVIDOSOS);
				
				//Cria a vari�vel que vai armazenar o lan�amento cont�bil
				LancamentoContabil lancamentoContabilInsert = null;
				
				//la�o para gerar os lan�amentos por grupo de tipo de lan�amento
				for(Object[] dadosResumoDevedoresDuvidosos : colecaoDadosResumoDevedoresDuvidosos){
					
					//recupera o tipo de lan�amento atual 
					Integer idTipoLancamento = (Integer) dadosResumoDevedoresDuvidosos[1];
					
					/*
					 * Caso seja a primeira entrada do "for"
					 * adiciona os dados a cole��o e atualiza o item temporario
					 * criando tamb�m o lan�amento contabil que ira ser inserido
					 * junto com seus items.
					 * 
					 *  Caso contr�rio (n�o seja a primeira entrada do la�o "for")
					 *  verifica se o item de lan�amento mudou, caso n�o tenha mudado 
					 *  adiciona os dados ao conjunto do mesmo item
					 *  caso contr�rio, se mudou o item de lan�amento o conjunto est� fechado
					 *  para o lan�amento cont�bil e chama o m�todo para inserir o
					 *  lan�amento cont�bil junto com seus itens. 
					 */
					if(flagPrimeiraVez){
						colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
						flagPrimeiraVez = false;
						idTipoLancamentoTemp = idTipoLancamento;
						
						LancamentoTipo tipoLancamento = new LancamentoTipo();
						tipoLancamento.setId(idTipoLancamento);
						
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidade);
						
						//cria o lan�amento cont�bil para ser inserido 
						lancamentoContabilInsert = new LancamentoContabil();
						lancamentoContabilInsert.setAnoMes(anoMesReferenciaContabil);
						lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
						lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
						lancamentoContabilInsert.setLocalidade(localidade);
						lancamentoContabilInsert.setRecebimentoTipo(null);
						lancamentoContabilInsert.setUltimaAlteracao(new Date());
					}else{
						/*
						 * Caso ainda seja o mesmo item adicona os dados para 
						 * ser gerado os itens do lan�amento para o mesmo lan�amento.
						 * Caso contr�rio chama o met�do para inseri os itens e o lan�amento cont�bil.
						 */
						if(idTipoLancamento == idTipoLancamentoTemp){
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
						}else{
							/* met�do para inserir o lan�amento cont�bil assim como seus itens */
							this.inserirLancamentoContabilItemDevedoresDuvidosos(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento);
							
							//limpa cole��o e adiciona os dados do resumo atual
							colecaoDadosResumoPorTipoLancamento.clear();
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
							
							LancamentoTipo tipoLancamento = new LancamentoTipo();
							tipoLancamento.setId(idTipoLancamento);
							
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							
							//cria o lan�amento cont�bil que vai ser inserido
							lancamentoContabilInsert = new LancamentoContabil();
							lancamentoContabilInsert.setAnoMes(anoMesReferenciaContabil);
							lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
							lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
							lancamentoContabilInsert.setLocalidade(localidade);
							lancamentoContabilInsert.setRecebimentoTipo(null);
							lancamentoContabilInsert.setUltimaAlteracao(new Date());
							
							//atualiza o tipo de lan�amento tempor�rio com o novo valor
							idTipoLancamentoTemp = idTipoLancamento;
						}
					}
				}
				
				/*
				 * �ltimo registro
				 * Esse "if" � para verificar se ainda existe um �ltimo registro na cole��o
				 * caso exista algum item, adiciona o lan�amento cont�bil junto com o item. 
				 */
				if(colecaoDadosResumoPorTipoLancamento != null && colecaoDadosResumoPorTipoLancamento.size() > 0 ){
					this.inserirLancamentoContabilItemDevedoresDuvidosos(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento);
					colecaoDadosResumoPorTipoLancamento = null;
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * Gera o lan�amento cont�bil junto com seus itens. 
	 *
	 * [UC0486] - Gerar Lan�amentos Cont�beis dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param lancamentoContabil
	 * @param colecaoDadosResumoPorTipoLancamento
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	protected void inserirLancamentoContabilItemDevedoresDuvidosos(LancamentoContabil lancamentoContabil,Collection<Object[]> colecaoDadosResumoPorTipoLancamento) throws ControladorException {
		try{
			/*
			 * Caso exista dados para os itens do resumo do faturamento.
			 */
			if(colecaoDadosResumoPorTipoLancamento != null && !colecaoDadosResumoPorTipoLancamento.isEmpty()){
				
				Collection colecaoLancamentoContabilItem = new ArrayList();
				
				//flag que indica se o lan�amento cont�bil j� foi inserido ou n�o. 
				boolean flagInseridoLancamentoContabil = false;
				
				/*
				 * Dados do resumo dos devedores duvidosos
				 *  0 - id da localidade
				 *  1 - id do tipo de lan�amento
				 *  2 - id do item de lan�amento
				 *  3 - id do item de lan�amento cont�bil
				 *  4 - id da categoria
				 *  5 - soma do valor do resumo dos devedores duvidosos
				 */
				for(Object[] dadosResumoDevedoresDuvidosos : colecaoDadosResumoPorTipoLancamento){
					
					//recupera os dados do resumo dos devedores duvidosos
					Integer idLancamentoTipo =         (Integer) dadosResumoDevedoresDuvidosos[1];
					Integer idLancamentoItem =         (Integer) dadosResumoDevedoresDuvidosos[2];
					Integer idLancamentoItemContabil = (Integer) dadosResumoDevedoresDuvidosos[3];
					Integer idCategoria =              (Integer) dadosResumoDevedoresDuvidosos[4];
					BigDecimal valorLancamento =       (BigDecimal) dadosResumoDevedoresDuvidosos[5]; 
					
					/* 
					 * Verifica se existe conta cont�bil para o item que vai ser inserido 
					 * 
					 * 0 - id conta cont�bil do d�bito
					 * 1 - id conta cont�bil cr�dito 
					 * 2 - descri��o do hist�rico do d�bito
					 * 3 - descri��o do hist�rico do cr�dito
					 */
					Object[] dadosContaContabil = this.repositorioFinanceiro.obterParametrosContabilDevedoresDuvidosos(idCategoria, idLancamentoItemContabil, idLancamentoItem, idLancamentoTipo);
					
					if(dadosContaContabil != null){
						Integer idLancamentoContabil = null;
						/*
						 * Caso exista dados para a conta cont�bil e o item cont�bil 
						 * ainda n�o foi inserido 
						 * inseri o item cont�bil na base.
						 */
						if(!flagInseridoLancamentoContabil){
							idLancamentoContabil = (Integer)getControladorUtil().inserir(lancamentoContabil);
							lancamentoContabil.setId(idLancamentoContabil);
							flagInseridoLancamentoContabil = true;
						}
						
						//recupera os dados da conta cont�bil para o item 
						//do resumo dos devedores duvidosos.
						Integer idContaContabilDebito = (Integer) dadosContaContabil[0];
						Integer idContaContabilCredito = (Integer) dadosContaContabil[1];
						String descricaoHistoricoDebito = (String) dadosContaContabil[2];
						String descricaoHistoricoCredito = (String) dadosContaContabil[3];
						
						//cria os indicadores de d�bito e cr�dito.
						Short indicadorDebito = new Short("2"); 
						Short indicadorCredito = new Short("1");
						
						Date ultimaAlteracao = new Date();
						
						//cria as contas cont�beis de cr�dito e d�bito.
						ContaContabil contaContabilCredito = new ContaContabil();
						contaContabilCredito.setId(idContaContabilCredito);
						
						ContaContabil contaContabilDebito = new ContaContabil();
						contaContabilDebito.setId(idContaContabilDebito);
						
						/**  Item de cr�dito */
						LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
								valorLancamento, 
								descricaoHistoricoCredito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilCredito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
						
						/** Item de d�bito */
						LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
								valorLancamento, 
								descricaoHistoricoDebito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilDebito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
						
					}
				}
				//inseri os itens de lan�amento cont�beis. 
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoLancamentoContabilItem); 
			}
	
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}
	
	/**
	 * Pesquisa a cole��o de ids das localidades para processar o lan�amentos  
	 * cont�beis dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Lan�amentos Cont�beis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ControladorException{
		try{
			return this.repositorioFinanceiro.pesquisarIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos(anoMesReferenciaContabil);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exce��o para a pr�xima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}

	/**
	 * Remove os lan�amentos cont�beis e seus respectivos itens 
	 * de acordo com os par�metros informados. 
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idLancamentoOrigem
	 * @throws ControladorException
	 */
	public void removerLancamentoContabil(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idLancamentoOrigem) throws ControladorException{
		try{
			Collection<Integer> colecaoIdsLancamentosContabeis = this.repositorioFinanceiro.pesquisarIdsLancamentosContabeis(anoMesReferenciaContabil, idLocalidade,idLancamentoOrigem);
			
			if(colecaoIdsLancamentosContabeis != null && !colecaoIdsLancamentosContabeis.isEmpty()){
				for(Integer idLancamentoContabil : colecaoIdsLancamentosContabeis){
					this.repositorioFinanceiro.removerItensLancamentoContabil(idLancamentoContabil);
				}
				
				this.repositorioFinanceiro.removerLancamentosContabeis(colecaoIdsLancamentosContabeis);
				colecaoIdsLancamentosContabeis = null;
			}
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exce��o para a pr�xima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorio(
			String opcaoTotalizacao, int mesAnoReferencia,
			Integer gerenciaRegional, Integer localidade, Integer unidadeNegocio)
			throws ControladorException {

		Collection retorno = new ArrayList();
		Collection colecaoResumoDevedoresDuvidososRelatorio = null;

		// Converter de mesAno para anoMes para que funcione nas consultas
		int anoMesReferencia = Util.formatarMesAnoParaAnoMes(mesAnoReferencia);
		boolean consultarResumoDevedoresDuvidososRelatorio = true;
		
		try {

			if (opcaoTotalizacao.equals("estado")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorEstado(anoMesReferencia);

			} else if (opcaoTotalizacao.equals("estadoGerencia")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorEstadoPorGerenciaRegional(anoMesReferencia);

			} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
				
				consultarResumoDevedoresDuvidososRelatorio = false;
				
				retorno = consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(anoMesReferencia);
				
			} else if (opcaoTotalizacao.equals("estadoLocalidade")) {
				
				consultarResumoDevedoresDuvidososRelatorio = false;
				
				retorno = consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(anoMesReferencia);

			} else if (opcaoTotalizacao.equals("gerenciaRegional")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia, gerenciaRegional);

			} else if (opcaoTotalizacao.equals("gerenciaRegionalLocalidade")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegionalPorLocalidade(
								anoMesReferencia, gerenciaRegional);

			} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(
								anoMesReferencia, unidadeNegocio);				
				
			} else if (opcaoTotalizacao.equals("localidade")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorLocalidade(
								anoMesReferencia, localidade);
			}
			
			
			if (consultarResumoDevedoresDuvidososRelatorio){
				
				Iterator iterator = colecaoResumoDevedoresDuvidososRelatorio.iterator();

				// Prepara cada linha do relat�rio

				String tipoLancamento = null;
				String itemLancamento = null;
				String itemContabel = null;

				String descGerenciaRegionalAnterior = null;
				String idGerenciaRegionalAnterior = null;
				String descLocalidadeAnterior = null;
				String idLocalidadeAnterior = null;
				String descLancamentoTipoSuperior = "";
				
				String descUnidadeNegocioAnterior = null;
				String idUnidadeNegocioAnterior = null;

				Object[] elementAnterior = new Object[13];
				BigDecimal[] arrayValores = new BigDecimal[5];

				Boolean agrupaPorGerencia = false;
				if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")|| opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
					agrupaPorGerencia = true;
				}

				Boolean agrupaPorLocalidade = false;
				if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")|| opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalLocalidade")|| opcaoTotalizacao.equalsIgnoreCase("localidade")) {
					agrupaPorLocalidade = true;
				}

				Boolean agrupaPorUnidadeNegocio = false;
				if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
					agrupaPorUnidadeNegocio = true;
				}

				
				while (iterator.hasNext()) {
					Object[] element = null;
					String tempTipoLancamento = null;
					String tempItemLancamento = null;
					String tempItemContabel = null;

					element = (Object[]) iterator.next();

					if (tipoLancamento == null) {
						tipoLancamento = (String) element[1];
						itemLancamento = (String) element[2];
						itemContabel = (String) element[3];
					}

					tempTipoLancamento = (String) element[1];
					tempItemLancamento = (String) element[2];
					tempItemContabel = (String) element[3];

					boolean condicaoIgual = true;
					// compara se o registro atual eh do
					// mesmo tipo de Recebimento, mesmo tipo de lan�amento
					// e mesmo item de lan�amento do registro anterior
					if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

						// se o registro possuir item contabel
						// compara se eh do mesmo item contabel do registro anterior
						if (itemContabel == null && tempItemContabel == null
							|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
						
							// se for agrupado por gerencia
							// compara se o registro atual eh da
							// mesma gerencia regional do registro anterior
							if (!agrupaPorGerencia || descGerenciaRegionalAnterior == null || 
							(agrupaPorGerencia	&& descGerenciaRegionalAnterior.equalsIgnoreCase((String) element[9]))) {

								switch (((Integer) element[8]).intValue()) {
								case 1:
									arrayValores[0] = (BigDecimal) element[0];
									break;
								case 2:
									arrayValores[1] = (BigDecimal) element[0];
									break;
								case 3:
									arrayValores[2] = (BigDecimal) element[0];
									break;
								case 4:
									arrayValores[4] = (BigDecimal) element[0];
									break;
								}
							} else {
								condicaoIgual = false;
							}

						} else {

							condicaoIgual = false;
						}

					} else {

						condicaoIgual = false;

					}
					
					if (!condicaoIgual) {
						
						ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
							new ResumoDevedoresDuvidososRelatorioHelper(
								(BigDecimal[]) arrayValores,
								(String) elementAnterior[1],
								(String) elementAnterior[2],
								(String) elementAnterior[3],
								(Short) elementAnterior[4],
								(Short) elementAnterior[5], (Integer) elementAnterior[6],
								(Integer) elementAnterior[7],
								 false,
								descGerenciaRegionalAnterior,
								idGerenciaRegionalAnterior, descLocalidadeAnterior,
								idLocalidadeAnterior,descLancamentoTipoSuperior,
								descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

						retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
						
					
						arrayValores = new BigDecimal[5];
						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

					}
					
					
					
					elementAnterior[1] = element[1]; // descricaoTipoLancamento
					if (((String)element[1]).equalsIgnoreCase((String)element[2])){
						elementAnterior[2] = null; // descricaoItemLancamento
					}else{
						elementAnterior[2] = element[2]; // descricaoItemLancamento
					}
					
					elementAnterior[3] = element[3]; // descricaoItemContabil
					elementAnterior[4] = element[4]; // indicadorImpressao
					elementAnterior[5] = element[5]; // indicadorTotal
					elementAnterior[6] = element[6]; // lancamentoTipo
					elementAnterior[7] = element[7]; // lancamentoTipoSuperior

					

					// identifica pelo que vai ser "quebrado" o rel�torio
					if (agrupaPorGerencia) { 
						// quebra p�gina por Ger�ncia Regional e n�o mostra a Localidade
						descGerenciaRegionalAnterior = "" + element[9];
						idGerenciaRegionalAnterior = "" + element[10];
					}
					if (agrupaPorLocalidade) {
						if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")
								|| opcaoTotalizacao
										.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
							// quebra a p�gina por Localidade e mostra a Ger�ncia
							// Regional
							descGerenciaRegionalAnterior = "" + element[9];
							idGerenciaRegionalAnterior = "" + element[10];
							descLocalidadeAnterior = "" + element[11];
							idLocalidadeAnterior = "" + element[12];
						} else {
							// quebra a p�gina por Localidade e n�o mostra a Ger�ncia
							// Regional
							descLocalidadeAnterior = "" + element[9];
							idLocalidadeAnterior = "" + element[10];
						}
					}
					
					if (agrupaPorUnidadeNegocio){
						descUnidadeNegocioAnterior = "" + element[9];
						idUnidadeNegocioAnterior = "" + element[10];
						
					}

					tipoLancamento = tempTipoLancamento;
					itemLancamento = tempItemLancamento;
					itemContabel = tempItemContabel;

				}

				if (colecaoResumoDevedoresDuvidososRelatorio != null
						&& !colecaoResumoDevedoresDuvidososRelatorio.isEmpty()) {
					// adiciona a ultima linha
			
					ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
						new ResumoDevedoresDuvidososRelatorioHelper(
							(BigDecimal[]) arrayValores,
							(String) elementAnterior[1],
							(String) elementAnterior[2],
							(String) elementAnterior[3],
							(Short) elementAnterior[4],
							(Short) elementAnterior[5], (Integer) elementAnterior[6],
							(Integer) elementAnterior[7],
							 false,
							descGerenciaRegionalAnterior,
							idGerenciaRegionalAnterior, descLocalidadeAnterior,
							idLocalidadeAnterior,descLancamentoTipoSuperior,
							descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

					retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
				}
				
			}
			

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(int anoMesReferencia)
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio = new ArrayList();
		Collection retorno = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(anoMesReferencia);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio.iterator();

		// Prepara cada linha do relat�rio
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		while (iterator.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iterator.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lan�amento
			// e mesmo item de lan�amento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
				
					// se for agrupado por gerencia
					// compara se o registro atual eh da
					// mesma gerencia regional do registro anterior

					switch (((Integer) element[8]).intValue()) {
					case 1:
						arrayValores[0] = (BigDecimal) element[0];
						break;
					case 2:
						arrayValores[1] = (BigDecimal) element[0];
						break;
					case 3:
						arrayValores[2] = (BigDecimal) element[0];
						break;
					case 4:
						arrayValores[4] = (BigDecimal) element[0];
						break;
					}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
				
			
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			if (idGerenciaRegionalAnterior != null && !idGerenciaRegionalAnterior.equals("" + element[14])){
				//quebra por gerencia
				retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno);
			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			descUnidadeNegocioAnterior = "" + element[9];
			idUnidadeNegocioAnterior = "" + element[10];
				
			descGerenciaRegionalAnterior = "" + element[13];
			idGerenciaRegionalAnterior = "" + element[14];
				
			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio != null
				&& !colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
			
			retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno);
			
		}

		return retorno;
		
	
	}
	
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(int anoMesReferencia, 
			Integer idGerenciaRegional , Collection colecaoResumoDevedoresDuvidososRelatorio )
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia, idGerenciaRegional);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iteratorResumoDevedoresDuvidososRelatorioPorGerenciaRegional = colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional.iterator();

		// Prepara cada linha do relat�rio
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		Boolean agrupaPorGerencia = true;
		
		while (iteratorResumoDevedoresDuvidososRelatorioPorGerenciaRegional.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iteratorResumoDevedoresDuvidososRelatorioPorGerenciaRegional.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lan�amento
			// e mesmo item de lan�amento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
				
					// se for agrupado por gerencia
					// compara se o registro atual eh da
					// mesma gerencia regional do registro anterior
					if (!agrupaPorGerencia || descGerenciaRegionalAnterior == null || 
					(agrupaPorGerencia	&& descGerenciaRegionalAnterior.equalsIgnoreCase((String) element[9]))) {

						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}
					} else {
						condicaoIgual = false;
					}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
				
			
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			descGerenciaRegionalAnterior = "" + element[9];
			idGerenciaRegionalAnterior = "" + element[10];
			
			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional != null
				&& !colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
		}

		return colecaoResumoDevedoresDuvidososRelatorio;
	
	}
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(int anoMesReferencia)
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade = new ArrayList();
		Collection retorno = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(anoMesReferencia);
			
			if (colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade != null &&
					!colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade.isEmpty()){
				
				Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade.iterator();

				// Prepara cada linha do relat�rio

				String tipoLancamento = null;
				String itemLancamento = null;
				String itemContabel = null;

				String descGerenciaRegionalAnterior = null;
				String idGerenciaRegionalAnterior = null;
				String descLocalidadeAnterior = null;
				String idLocalidadeAnterior = null;
				String descLancamentoTipoSuperior = "";
				
				String descUnidadeNegocioAnterior = null;
				String idUnidadeNegocioAnterior = null;

				Object[] elementAnterior = new Object[13];
				BigDecimal[] arrayValores = new BigDecimal[5];

				
				while (iterator.hasNext()) {
					Object[] element = null;
					String tempTipoLancamento = null;
					String tempItemLancamento = null;
					String tempItemContabel = null;

					element = (Object[]) iterator.next();

					if (tipoLancamento == null) {
						tipoLancamento = (String) element[1];
						itemLancamento = (String) element[2];
						itemContabel = (String) element[3];
					}

					tempTipoLancamento = (String) element[1];
					tempItemLancamento = (String) element[2];
					tempItemContabel = (String) element[3];

					boolean condicaoIgual = true;
					// compara se o registro atual eh do
					// mesmo tipo de Recebimento, mesmo tipo de lan�amento
					// e mesmo item de lan�amento do registro anterior
					if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

						// se o registro possuir item contabel
						// compara se eh do mesmo item contabel do registro anterior
						if (itemContabel == null && tempItemContabel == null
							|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
						
						
								switch (((Integer) element[8]).intValue()) {
								case 1:
									arrayValores[0] = (BigDecimal) element[0];
									break;
								case 2:
									arrayValores[1] = (BigDecimal) element[0];
									break;
								case 3:
									arrayValores[2] = (BigDecimal) element[0];
									break;
								case 4:
									arrayValores[4] = (BigDecimal) element[0];
									break;
								}
						

						} else {

							condicaoIgual = false;
						}

					} else {

						condicaoIgual = false;

					}
					
					if (!condicaoIgual) {
						
						ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
							new ResumoDevedoresDuvidososRelatorioHelper(
								(BigDecimal[]) arrayValores,
								(String) elementAnterior[1],
								(String) elementAnterior[2],
								(String) elementAnterior[3],
								(Short) elementAnterior[4],
								(Short) elementAnterior[5], (Integer) elementAnterior[6],
								(Integer) elementAnterior[7],
								 false,
								descGerenciaRegionalAnterior,
								idGerenciaRegionalAnterior, descLocalidadeAnterior,
								idLocalidadeAnterior,descLancamentoTipoSuperior,
								descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

						retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
						
					
						arrayValores = new BigDecimal[5];
						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

					}
					
					
					if (idUnidadeNegocioAnterior != null && !idUnidadeNegocioAnterior.equals("" + element[16])){
						//quebra por Unidade Negocio
						retorno = consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(
								anoMesReferencia,new Integer (idUnidadeNegocioAnterior),retorno);
						
					}
					
					if (idGerenciaRegionalAnterior != null && !idGerenciaRegionalAnterior.equals("" + element[10])){
						//quebra por gerencia
						retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno);
						
					}
					
					elementAnterior[1] = element[1]; // descricaoTipoLancamento
					if (((String)element[1]).equalsIgnoreCase((String)element[2])){
						elementAnterior[2] = null; // descricaoItemLancamento
					}else{
						elementAnterior[2] = element[2]; // descricaoItemLancamento
					}
					
					elementAnterior[3] = element[3]; // descricaoItemContabil
					elementAnterior[4] = element[4]; // indicadorImpressao
					elementAnterior[5] = element[5]; // indicadorTotal
					elementAnterior[6] = element[6]; // lancamentoTipo
					elementAnterior[7] = element[7]; // lancamentoTipoSuperior

				
					descGerenciaRegionalAnterior = "" + element[9];
					idGerenciaRegionalAnterior = "" + element[10];
					descLocalidadeAnterior = "" + element[11];
					idLocalidadeAnterior = "" + element[12];
					descUnidadeNegocioAnterior = "" + element[15];
					idUnidadeNegocioAnterior = "" + element[16];
						

					tipoLancamento = tempTipoLancamento;
					itemLancamento = tempItemLancamento;
					itemContabel = tempItemContabel;

				}

				if (colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade != null
						&& !colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade.isEmpty()) {
					// adiciona a ultima linha
			
					ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
						new ResumoDevedoresDuvidososRelatorioHelper(
							(BigDecimal[]) arrayValores,
							(String) elementAnterior[1],
							(String) elementAnterior[2],
							(String) elementAnterior[3],
							(Short) elementAnterior[4],
							(Short) elementAnterior[5], (Integer) elementAnterior[6],
							(Integer) elementAnterior[7],
							 false,
							descGerenciaRegionalAnterior,
							idGerenciaRegionalAnterior, descLocalidadeAnterior,
							idLocalidadeAnterior,descLancamentoTipoSuperior,
							descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

					retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
					
					retorno = consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(anoMesReferencia,new Integer (idUnidadeNegocioAnterior),retorno);
					
					retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno);
					
					retorno = consultarResumoDevedoresDuvidososRelatorioPorEstado(anoMesReferencia,retorno);
				}
			}
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}
	
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(int anoMesReferencia, 
			Integer idUnidadeNegocio , Collection colecaoResumoDevedoresDuvidososRelatorio )
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioPorUnidadeNegocio = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioPorUnidadeNegocio = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(anoMesReferencia, idUnidadeNegocio);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioPorUnidadeNegocio.iterator();
		
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		while (iterator.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iterator.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lan�amento
			// e mesmo item de lan�amento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
				
						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
				
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			descGerenciaRegionalAnterior = "" + element[13];
			idGerenciaRegionalAnterior = "" + element[14];
			descUnidadeNegocioAnterior = "" + element[9];
			idUnidadeNegocioAnterior = "" + element[10];

			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorio != null
				&& !colecaoResumoDevedoresDuvidososRelatorio.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
		}

		return colecaoResumoDevedoresDuvidososRelatorio;
	}

	
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstado(int anoMesReferencia, 
			Collection colecaoResumoDevedoresDuvidososRelatorio )
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioPorEstado = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioPorEstado = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorEstado(anoMesReferencia);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioPorEstado.iterator();
		
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		while (iterator.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iterator.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lan�amento
			// e mesmo item de lan�amento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {

						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
				
			
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorioPorEstado != null
				&& !colecaoResumoDevedoresDuvidososRelatorioPorEstado.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
		}
		
		return colecaoResumoDevedoresDuvidososRelatorio;
	}

	
	/**
	 * [UC0718] Gerar Relat�rio de Evolucao do Contas a Receber Contabil
	 * 
	 * @author Francisco Junior, Ivan Sergio
	 * @date 02/01/08, 20/07/2010
	 * @alteracao - 20/07/2010: Alterado a posicao e descricao do item IMPOSTOS DEDUZIDOS;
	 * 							Os valores do item IMPOSTOS DEDUZIDOS sao obtidos a partir da tabela
	 * 							RESUMO ARRECADACAO sequencias 1200, 1300, 1400 e 1500 (lcit_id = 19, 20, 21 e 22);
	 * 			  - 28/07/2010: Modificado o sinal de Impostos Deduzidos para subtrair;
	 * 							Adicionado o item Impostos de Meses Anteriores Classificados no Mes;
	 * 
	 * @param opcaoTotalizacao
	 * @param mesAno
	 * @param codigoGerencia
	 * @param codigoLocalidade
	 * @param unidadeNegocio
	 * @return Colecao 
	 * @throws ControladorException
	 */
	public Collection<RelatorioEvolucaoContasAReceberContabilBean> 
		consultarDadosEvolucaoContasAReceberContabilRelatorio(String opcaoTotalizacao,
		int mesAno, Integer codigoGerencia, Integer codigoLocalidade, Integer codigoMunicipio, Integer unidadeNegocio) throws ControladorException {
		
		// este mapeamento � composto de uma descri��o do grupo como chave (localidade, gerencia ou unidade de negocio)
		// e a cole��o de valores associado a esta chave
		HashMap<Integer, BigDecimal[][]> dadosAgrupados = new HashMap<Integer, BigDecimal[][]>();  
		HashMap<Integer, String> labelChaves = new HashMap<Integer, String>();
		int anoMes = Util.formatarMesAnoParaAnoMes(mesAno);
		// As linhas de detalhes s�o fixas e nesta ordem 
		String descricoes[] = {
			"S A L D O    A N T E R I O R", 		// linha 1 
			"(+) FATURAMENTO AGUA",					// linha 2
			"(+) FATURAMENTO ESGOTO",				// linha 3
			"(+) FINANCIAMENTOS INCLUIDOS",			// linha 4
			"(+) JUROS DE PARCELAMENTO",			// linha 5
			"(+) GUIAS DE PAGAMENTO",				// linha 6
			"(+) INCLUSOES POR REFATURAMENTO",		// linha 7
			"(=) FATURAMENTO BRUTO DO MES",			// linha 8
			"(-) FINANCIAMENTOS CANCELADOS",		// linha 9	
			"(-) PARCELAMENTOS CANCELADOS",			// linha 10
			"(-) CANCELAMENTOS POR REFATURAMENTO",	// linha 11
			"(-) CR�DITOS A REALIZAR POR COBRAN�A INDEVIDA", // linha 12
			"(-) DESCONTOS INCONDICIONAIS INCLU�DOS", // linha 13
			"(-) GUIAS DE DEVOLU��O DE VALORES COBRADOS INDEVIDAMENTE INCLU�DAS", // linha 14
			"(=) FATURAMENTO L�QUIDO DO MES",		// linha 12 -> 15
			"(-) DESCONTOS CONCEDIDOS NO PARCELAMENTO",	// linha 13 -> 16
			"(-) OUTROS CR�DITOS INCLU�DOS",	// linha 14 -> 17
//			"(+) IMPOSTOS CANCELADOS POR REFATURAMENTO",// linha 16 -> 18
//			"(-) IMPOSTOS INCLU�DOS POR REFATURAMENTO",	// linha 17 -> 19
			"(+) OUTROS CR�DITOS CANCELADOS POR REFATURAMENTO",	// linha 18 -> 20 - > 18
			"(-) OUTROS CR�DITOS CONCEDIDOS POR REFATURAMENTO",	// linha 19 -> 21 - > 19
			"(-) RECEBIMENTOS CLASSIFICADOS",		// linha 20 -> 22 -> 20
			"(-) IMPOSTOS DEDUZIDOS NA ARRECADACAO",	// linha 15 -> 23 -> 21
			"(-) RECEBIMENTOS DE MESES ANTERIORES CLASSIFICADOS NO MES", // linha 21 -> 24 ->22
			"(-) IMPOSTOS DE MESES ANTERIORES CLASSIFICADOS NO MES", // linha -> 25 -> 23
			"(-) CANCELAMENTOS POR PRESCRICAO", // linha 22 -> 26 -> 24
			"S A L D O    A T U A L"				// linha 23 -> 27 ->25
			};
	 
		Collection pesquisaSaldo = new ArrayList();
		Collection pesquisaDadosFaturamento = new ArrayList();
		Collection pesquisaDadosRecebimentos = new ArrayList();
		int anoMesAnterior = Util.subtraiAteSeisMesesAnoMesReferencia(anoMes, 1);
		try {

			if (opcaoTotalizacao.equals("estado")) {
				// quando os parametros gerencia, unidade de negocio e localidade s�o passados nulos, 
				// a pesquisa � feita sem restri��o, trazendo os dados referentes a todo o estado.
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, null, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, null);

			} else if (opcaoTotalizacao.equals("estadoGerencia")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
						anoMesAnterior);				
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
						anoMes);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorGerenciaRegional(
						anoMes);
				
			} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMesAnterior, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, null);

			} else if (opcaoTotalizacao.equals("estadoLocalidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMesAnterior, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, null);

			} else if (opcaoTotalizacao.equals("estadoMunicipio")) {
				pesquisaSaldo = repositorioFinanceiro.
					consultarSaldoEvolucaoContasAReceberContabilRelatorioPorMunicipio(anoMesAnterior);
				pesquisaDadosFaturamento = repositorioFinanceiro.
					consultarDadosEvolucaoContasAReceberContabilRelatorioPorMunicipio(anoMes);
				pesquisaDadosRecebimentos = repositorioFinanceiro.
					consultarRecebimentosContasAReceberContabilRelatorioPorMunicipio(anoMes);

			} else if (opcaoTotalizacao.equals("gerenciaRegional")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, codigoGerencia, null, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, codigoGerencia, null, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, codigoGerencia, null, null, null);
			} else if (opcaoTotalizacao.equals("gerenciaRegionalUnidadeNegocio")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMesAnterior, codigoGerencia);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, codigoGerencia);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, codigoGerencia);
			} else if (opcaoTotalizacao.equals("gerenciaRegionalLocalidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMesAnterior, codigoGerencia, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMes, codigoGerencia, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
						anoMes, codigoGerencia, null);

			} else if (opcaoTotalizacao.equals("unidadeNegocioLocalidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMesAnterior, null, unidadeNegocio);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, unidadeNegocio);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, unidadeNegocio);
			} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, unidadeNegocio, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, unidadeNegocio, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, unidadeNegocio, null, null);				
			} else if (opcaoTotalizacao.equals("localidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, null, codigoLocalidade, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, codigoLocalidade, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, codigoLocalidade, null);
			} else if (opcaoTotalizacao.equals("municipio")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, null, null, codigoMunicipio);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, codigoMunicipio);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, codigoMunicipio);
			}
			// Tratando a consulta dos saldos em conta a receber contabil
			Iterator iter = pesquisaSaldo.iterator();
			Integer idDescAnterior = -1;
			while (iter.hasNext()){
				Object[] element = (Object[]) iter.next();
				String desc = (String) element[0];
				Integer idDesc = (Integer) element[1];
				labelChaves.put(idDesc, desc);
				
				Integer codigo = (Integer) element[3];
				// recupera o conjunto de valores atual para a chave 'desc'
				BigDecimal[][] valoresItem = dadosAgrupados.get(idDesc);
				// quando a descri��o atual for diferente da anterior � pq � hora de criar um novo grupo de valoresItem
				if (idDesc.intValue() != idDescAnterior.intValue()){				
					valoresItem = new BigDecimal[descricoes.length][2];
					for (int i = 0; i < valoresItem.length; i++) {
						valoresItem[i][0] = new BigDecimal(0);
						valoresItem[i][1] = new BigDecimal(0);
					}
					// SALDO ANTERIOR
					valoresItem[0] = new BigDecimal[2];										
					valoresItem[0][0] = new BigDecimal(0); // PARTICULAR
					valoresItem[0][1] = new BigDecimal(0); // PUBLICO
					dadosAgrupados.put(idDesc, valoresItem);
				}
				// no select retornou o codigo do tipo de categoria (1 ou 2)
				// entao em cada linha de resultado devemos verificar se foi do particular ou privado
				if (codigo.intValue() == CategoriaTipo.PARTICULAR){
					valoresItem[0][0] = (BigDecimal) element[4];	// PARTICULAR
				} else {
					valoresItem[0][1] = (BigDecimal) element[4];    // PUBLICO
				}				
				idDescAnterior = idDesc;				
			}
			
			// conjunto de valores: cada linha � um item diretamente associado ao array descricoes
			// e na primeira coluna o valor de particular, na segunda, o valor de privado
			// na defini��o do bean do relat�rio, o valor total � definido como a soma destes dois em cada linha
			BigDecimal[][] valoresItem = null; 
			idDescAnterior = -1;
			
			//Tratando dados de faturamento
			iter = pesquisaDadosFaturamento.iterator();
			while (iter.hasNext()){
				Object[] element = (Object[]) iter.next();
				String desc = (String) element[0];
				Integer idDesc = (Integer) element[1];	
				labelChaves.put(idDesc, desc);
				
				Short sequenciaTipoLancamento = (Short) element[2];
				Short sequenciaItemTipoLancamento = (Short) element[3];
				Integer tipoCategoria = (Integer) element[4];
				BigDecimal valorItem = (BigDecimal) element[5];
				
				if (idDescAnterior.intValue() != idDesc.intValue()){
					// continuando o array de valoresItem iniciado com o item SALDO ANTERIOR
					valoresItem = dadosAgrupados.get(idDesc);
					if (valoresItem == null){
						valoresItem = new BigDecimal[descricoes.length][2];
						for (int i = 0; i < valoresItem.length; i++) {
							valoresItem[i][0] = new BigDecimal(0);
							valoresItem[i][1] = new BigDecimal(0);
						}
						dadosAgrupados.put(idDesc, valoresItem);
					}
				} 
				idDescAnterior = idDesc;
				
				// o indice diz respeito a posicao deste item na exibi��o, que � tamb�m 
				// a posi��o no array valoresItem
				short indiceTipoLancamento = -1;
				switch (sequenciaTipoLancamento.shortValue()) {				
				case ResumoFaturamento.FATURAMENTO_AGUA_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 1;
					break;
				case ResumoFaturamento.FATURAMENTO_ESGOTO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 2;
					break;
				case ResumoFaturamento.FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
					indiceTipoLancamento = 8;
					break;
				case ResumoFaturamento.FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_2:					
					indiceTipoLancamento = 3;
					break;
				case ResumoFaturamento.GUIAS_PAGAMENTO_SEQUENCIA_TIPO_LANCAMENTO:					
					indiceTipoLancamento = 5;
					break;					
//				case ResumoFaturamento.IMPOSTOS_DEDUZIDOS_SEQUENCIA_TIPO_LANCAMENTO:					
//					indiceTipoLancamento = 22;
//					break;
				case ResumoFaturamento.JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2:
					indiceTipoLancamento = 4;
					break;
				case ResumoFaturamento.PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
					indiceTipoLancamento = 9;
					break;
				case ResumoFaturamento.CREDITOS_A_REALIZAR_POR_COBRANCA_INDEVIDA_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 11;
					break;
				case ResumoFaturamento.DESCONTOS_INCONDICIONAIS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 12;
					break;
				case ResumoFaturamento.GUIAS_DE_DEVOLUCOES_DE_VALORES_COBRADOS_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 13;
					break;
//				case ResumoFaturamento.VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
//				case ResumoFaturamento.VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
				case ResumoFaturamento.VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_3:
					indiceTipoLancamento = 16;
					break;					
				case ResumoFaturamento.DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2:
					if (sequenciaItemTipoLancamento.shortValue() == ResumoFaturamento.
							DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_ITEM_TIPO_LANCAMENTO){
						indiceTipoLancamento = 15;						
					} else {
						continue;
					}
					break;					
//				case ResumoFaturamento.IMPOSTOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
//					indiceTipoLancamento = 17;
//					break;					
//				case ResumoFaturamento.IMPOSTOS_INCLUIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
//					indiceTipoLancamento = 18;
//					break;					
				case ResumoFaturamento.OUTROS_CREDITOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 17;
					break;					
				case ResumoFaturamento.OUTROS_CREDITOS_CONCEDIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 18;
					break;					
				case ResumoFaturamento.CANCELAMENTOS_POR_PRESCRICAO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 23;
					break;
				default:
					if (sequenciaTipoLancamento.shortValue() >= 
						ResumoFaturamento.INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1
						&& sequenciaTipoLancamento.shortValue() <=	
						ResumoFaturamento.INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2){
						indiceTipoLancamento = 6;	
					} else if (sequenciaTipoLancamento.shortValue() >= 
						ResumoFaturamento.CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1
						&& sequenciaTipoLancamento.shortValue() <=
						ResumoFaturamento.CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2){
						indiceTipoLancamento = 10;
					} else {
						// existem valores de sequenciaTipoLancamento que nao serao usados nesse relatorio 
						continue;						
					}
				}
				if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR){
					if (valoresItem[indiceTipoLancamento][0] == null){ // PARTICULAR
						valoresItem[indiceTipoLancamento][0] = valorItem;
					} else {
						valoresItem[indiceTipoLancamento][0] = valoresItem[indiceTipoLancamento][0].add(valorItem);	
					}					 
				} else {
					if (valoresItem[indiceTipoLancamento][1] == null){ // PUBLICO
						valoresItem[indiceTipoLancamento][1] = valorItem;
					} else {
						valoresItem[indiceTipoLancamento][1] = valoresItem[indiceTipoLancamento][1].add(valorItem);	
					}
				}
				
			}
			
			idDescAnterior = -1;

			// Pesquisando dados de recebimentos referentes ao preenchimento das linhas 15 e 16			
			iter = pesquisaDadosRecebimentos.iterator();
			while (iter.hasNext()){
				Object[] element = (Object[]) iter.next();
				String desc = (String) element[0];
				Integer idDesc = (Integer) element[1];			
				labelChaves.put(idDesc, desc);
				
				Short sequenciaTipoLancamento = (Short) element[2];
				Integer tipoCategoria = (Integer) element[3];
				BigDecimal valorItem = (BigDecimal) element[4];

				if (idDescAnterior.intValue() != idDesc.intValue()){
					// continuando o array de valoresItem iniciado com o item SALDO ANTERIOR
					valoresItem = dadosAgrupados.get(idDesc);
					if (valoresItem == null){
						valoresItem = new BigDecimal[descricoes.length][2];
						for (int i = 0; i < valoresItem.length; i++) {
							valoresItem[i][0] = new BigDecimal(0);
							valoresItem[i][1] = new BigDecimal(0);
						}
						dadosAgrupados.put(idDesc, valoresItem);
					}
				} 
				idDescAnterior = idDesc;
				
				short indiceTipoLancamento = -1;
				short indicadorOperacao = 1; // 1=> SOMAR .... 2=>SUBTRAIR
				switch (sequenciaTipoLancamento.shortValue()) {
					case ResumoArrecadacao.RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
						indiceTipoLancamento = 19;
						indicadorOperacao = 1;
						break;
					case ResumoArrecadacao.RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
						indiceTipoLancamento = 19;
						indicadorOperacao = 2;
						break;
					case ResumoArrecadacao.RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1:
						indiceTipoLancamento = 21;
						indicadorOperacao = 1;
						break;
					case ResumoArrecadacao.RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2:
						indiceTipoLancamento = 21;
						indicadorOperacao = 2;
						break;
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_1:	
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_2:
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_3:
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_4:
						indiceTipoLancamento = 20;
						indicadorOperacao = 1;
						break;
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1:	
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2:
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_3:
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_4:
						indiceTipoLancamento = 22;
						indicadorOperacao = 1;
						break;
					default:
						continue;
				}
				if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR){
					if (valoresItem[indiceTipoLancamento][0] == null){ // PARTICULAR
						valoresItem[indiceTipoLancamento][0] = valorItem;
					} else {
						if (indicadorOperacao == 1){
							valoresItem[indiceTipoLancamento][0] = valoresItem[indiceTipoLancamento][0].add(valorItem);	
						} else {
							valoresItem[indiceTipoLancamento][0] = valoresItem[indiceTipoLancamento][0].subtract(valorItem);
						}
							
					}					 
				} else {
					if (valoresItem[indiceTipoLancamento][1] == null){ // PUBLICO
						valoresItem[indiceTipoLancamento][1] = valorItem;
					} else {
						if (indicadorOperacao == 1){
							valoresItem[indiceTipoLancamento][1] = valoresItem[indiceTipoLancamento][1].add(valorItem);
						} else {
							valoresItem[indiceTipoLancamento][1] = valoresItem[indiceTipoLancamento][1].subtract(valorItem);
						}
					}
				}								
			}
						
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		Collection<RelatorioEvolucaoContasAReceberContabilBean> dados = 
			new ArrayList<RelatorioEvolucaoContasAReceberContabilBean>();

		// Colocando em ordem alfabetica as descricoes dos grupos
		ArrayList chavesOrdenadas = new ArrayList(dadosAgrupados.keySet());
		Collections.sort(chavesOrdenadas);
		for (Iterator iter = chavesOrdenadas.iterator(); iter.hasNext();) {
			Integer chave = (Integer) iter.next();
			BigDecimal[][] valoresItem = dadosAgrupados.get(chave);
			
			// Calculando a linha 8 Faturamento Bruto do mes : SOMA(valor2:valor7)
			for (int ind = 1; ind < 7; ind++) {
				valoresItem[7][0] = valoresItem[7][0].add(valoresItem[ind][0]); // PARTICULAR
				valoresItem[7][1] = valoresItem[7][1].add(valoresItem[ind][1]); // PUBLICO
			}
			//	Calculando a linha 15 Faturamento Liquido do mes : Faturamento Bruto (valor8) - SOMA(valor9:valor14)
			valoresItem[14][0] = valoresItem[7][0];
			valoresItem[14][1] = valoresItem[7][1];
			for (int ind = 8; ind < 14; ind++) {
				valoresItem[14][0] = valoresItem[14][0].subtract(valoresItem[ind][0]); // PARTICULAR
				valoresItem[14][1] = valoresItem[14][1].subtract(valoresItem[ind][1]); // PUBLICO
			}

			// calculando a linha 26 - Saldo Atual: V1 + V12 + V16 + V17 + V21 - (V13, V14, V15. V22, V18, V19, V20)
			// PARTICULAR
			valoresItem[24][0] = valoresItem[0][0].add(valoresItem[14][0])
				.add(valoresItem[17][0]).subtract(valoresItem[15][0]).subtract(valoresItem[16][0])
				.subtract(valoresItem[20][0]).subtract(valoresItem[18][0])
				.subtract(valoresItem[19][0]).subtract(valoresItem[21][0]).subtract(valoresItem[22][0])
				.subtract(valoresItem[23][0]);
			// PUBLICO
			valoresItem[24][1] = valoresItem[0][1].add(valoresItem[14][1])
				.add(valoresItem[17][1]).subtract(valoresItem[15][1]).subtract(valoresItem[16][1])
				.subtract(valoresItem[20][1]).subtract(valoresItem[18][1])
				.subtract(valoresItem[19][1]).subtract(valoresItem[21][1]).subtract(valoresItem[22][1])
				.subtract(valoresItem[23][1]);
			

			for (int i=0; i < descricoes.length; i++) {							
				RelatorioEvolucaoContasAReceberContabilBean bean = new 
					RelatorioEvolucaoContasAReceberContabilBean((BigDecimal[]) valoresItem[i], descricoes[i]);
				
				if (opcaoTotalizacao.equals("estado")){
					bean.setTipoGrupo("TOTAL DO ESTADO");
				} else if (opcaoTotalizacao.equals("estadoGerencia")) {					
					bean.setTipoGrupo("Ger�ncia Regional");					
				
				} else if (opcaoTotalizacao.equals("estadoLocalidade") 					
					|| opcaoTotalizacao.equals("gerenciaRegionalLocalidade")
					|| opcaoTotalizacao.equals("unidadeNegocioLocalidade")) {
					bean.setTipoGrupo("Localidade");					
				} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio") 
						|| opcaoTotalizacao.equals("gerenciaRegionalUnidadeNegocio")) {
					bean.setTipoGrupo("Unidade de Neg�cio");					
				} else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")){
					bean.setTipoGrupo("Munic�pio");
				}
				
				bean.setDescricaoGrupo(labelChaves.get(chave));				
				dados.add(bean);			
			}
			
		}		
	
		return dados;
	}
		
	public Collection consultarDadosRelatorioSaldoContasAReceberContabil(String opcaoTotalizacao, int mesAno, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio)
			throws ControladorException {
		
		Collection pesquisaDados = new ArrayList();
		HashMap<String, RelatorioSaldoContasAReceberContabilBean> dadosAgrupados = new HashMap<String, RelatorioSaldoContasAReceberContabilBean>();
		int anoMes = Util.formatarMesAnoParaAnoMes(mesAno);
		
		try {
			pesquisaDados = repositorioFinanceiro.consultarDadosRelatorioSaldoContasAReceberContabil(opcaoTotalizacao, anoMes, gerencia, unidadeNegocio, localidade, municipio);
			
			String tipoGrupo = "";
			if(opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")){
				tipoGrupo = "Ger�ncia";
			} else if(opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade") ||
					opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalLocalidade") ||
					opcaoTotalizacao.equalsIgnoreCase("unidadeNegocioLocalidade")){
				tipoGrupo = "Localidade";
			} else if(opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio") ||
					opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalUnidadeNegocio")){
				tipoGrupo = "Unidade de Neg�cio";
			} else if(opcaoTotalizacao.equalsIgnoreCase("municipio") ||
					opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")){
				tipoGrupo = "Munic�pio";
			}
			
			// nomeItemGrupo, idItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem, numeroConta, soma(valor)
			Iterator iter = pesquisaDados.iterator();
			BigDecimal totalizadorSomaSemPerdaParticular = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerdaPublico = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerda = new BigDecimal("0");
			
			BigDecimal totalizadorSomaSemPerdaResidencial = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerdaComercial = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerdaIndustrial = new BigDecimal("0");
			
			//************************************************************
			//CRC???
			//Autor: Ivan Sergio
			//Solicitante: Eduardo
			//19/07/2010
			//Removido o item IMPOSTOS DEDUZIDOS e adicionando no final
			//do relatorio.
			//************************************************************
			String descImpostosDeduzidos = "";
			BigDecimal valorImpostosDeduzidosParticular = new BigDecimal(0);
			BigDecimal valorImpostosDeduzidosPublico = new BigDecimal(0);
			BigDecimal valorImpostosDeduzidosTotal = new BigDecimal(0);
			
			//************************************************************
			//CRC3294
			//Autor: Ivan Sergio
			//Data: 30/11/2010
			//************************************************************
			int indicadorFinCobCurtoPrazo = 0;
			int indicadorFinCobLongoPrazo = 0;
			int indicadorConta = 0;
			int indicadorGuia = 0;
			RelatorioSaldoContasAReceberContabilBean beanFinCobCurtoPrazo = null;
			RelatorioSaldoContasAReceberContabilBean beanFinCobLongoPrazo = null;
			RelatorioSaldoContasAReceberContabilBean beanConta = null;
			RelatorioSaldoContasAReceberContabilBean beanGuia = null;
			String chaveFinCobCurtoPrazo = "";
			String chaveFinCobLongoPrazo = "";
			String chaveConta = "";
			String chaveGuia = "";
			Boolean primeiraVez = true;
			Integer idItemGrupoAnterior = null;
			
			
			while(iter.hasNext()){
				
				Object[] objetos = (Object[]) iter.next();
				
				Integer idGerenciaRegional = (Integer) objetos[0];
				String nomeGerenciaRegional = (String) objetos[1]; 
				Integer idUnidadeNegocio = (Integer) objetos[2];
				String nomeUnidadeNegocio = (String) objetos[3]; 
				String codigoCentroCusto = (String) objetos[4]; 
				String nomeItemGrupo = (String) objetos[5];
				Integer idItemGrupo = (Integer) objetos[6];
				Integer numSequenciaTipoLancamento = (Integer) objetos[7];
				Integer numSequenciaLancamentoItem = (Integer) objetos[8];
				String descricaoLancamentoTipo = (String) objetos[9];
				String descricaoLancamentoItem = (String) objetos[10];
				Integer numeroConta = (Integer) objetos[11];
				Integer tipoCategoria = (Integer) objetos[12];
				BigDecimal somaValor = (BigDecimal) objetos[13];
				String descricaoItemContabil = ( String ) objetos[14];
				Integer idLancamentoTipo = (Integer) objetos[15];
				Integer idCategoria = (Integer) objetos[16];
				
				if (primeiraVez){
					idItemGrupoAnterior = idItemGrupo;
					primeiraVez = false;
				}
				
				if (!idItemGrupoAnterior.equals(idItemGrupo)){
					
					idItemGrupoAnterior = idItemGrupo;
					totalizadorSomaSemPerdaParticular = new BigDecimal("0");
					totalizadorSomaSemPerdaPublico = new BigDecimal("0");
					totalizadorSomaSemPerda = new BigDecimal("0");
					
					totalizadorSomaSemPerdaResidencial = new BigDecimal("0");
					totalizadorSomaSemPerdaComercial = new BigDecimal("0");
					totalizadorSomaSemPerdaIndustrial = new BigDecimal("0");
				}
				
				//*****************************************************************************
				// Verifica se adiciona o subtotal para Financiamento Cobrar CURTO Prazo
				//*****************************************************************************
				if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(10) && indicadorFinCobCurtoPrazo == 0) {
					
					indicadorFinCobCurtoPrazo = 1;
					beanFinCobCurtoPrazo = new RelatorioSaldoContasAReceberContabilBean(
							idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio,
							nomeUnidadeNegocio, codigoCentroCusto, tipoGrupo, idItemGrupo,
							nomeItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem,
							"                            SUBTOTAL",
							totalizadorSomaSemPerdaParticular, totalizadorSomaSemPerdaPublico,
							totalizadorSomaSemPerda);
					
					beanFinCobCurtoPrazo.setSequenciaLancamentoTipo(400);
					beanFinCobCurtoPrazo.setSequenciaLancamentoItem(11); // Serve apenas para ordenacao
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()) {
						beanFinCobCurtoPrazo.setValorItemParticular(somaValor); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobCurtoPrazo.setValorItemResidencial(somaValor);
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobCurtoPrazo.setValorItemComercial(somaValor);
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobCurtoPrazo.setValorItemIndustrial(somaValor);
						}
					}else {
						beanFinCobCurtoPrazo.setValorItemPublico(somaValor); // PUBLICO	
					}
					
				} else if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(10) && indicadorFinCobCurtoPrazo == 1) {
					
					chaveFinCobCurtoPrazo = "z" + nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()) {
						beanFinCobCurtoPrazo.setValorItemParticular(beanFinCobCurtoPrazo.getValorItemParticular().add(somaValor)); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobCurtoPrazo.setValorItemResidencial(
									beanFinCobCurtoPrazo.getValorItemResidencial().add(somaValor));
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobCurtoPrazo.setValorItemComercial(
									beanFinCobCurtoPrazo.getValorItemComercial().add(somaValor));
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobCurtoPrazo.setValorItemIndustrial(
									beanFinCobCurtoPrazo.getValorItemIndustrial().add(somaValor));
						}
					}else {
						beanFinCobCurtoPrazo.setValorItemPublico(
								beanFinCobCurtoPrazo.getValorItemPublico().add(somaValor)); // PUBLICO
					}
					
				}else if (indicadorFinCobCurtoPrazo == 1) {
					dadosAgrupados.put(chaveFinCobCurtoPrazo, beanFinCobCurtoPrazo);
					indicadorFinCobCurtoPrazo = 2;
				}
				//*****************************************************************************
				
				
				//*****************************************************************************
				// Verifica se adiciona o subtotal para Financiamento Cobrar LONGO Prazo
				//*****************************************************************************
				if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(20) &&
						indicadorFinCobLongoPrazo == 0) {
					
					indicadorFinCobLongoPrazo = 1;
					beanFinCobLongoPrazo = new RelatorioSaldoContasAReceberContabilBean(
							idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio,
							nomeUnidadeNegocio, codigoCentroCusto, tipoGrupo, idItemGrupo,
							nomeItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem,
							"                            SUBTOTAL",
							totalizadorSomaSemPerdaParticular, totalizadorSomaSemPerdaPublico,
							totalizadorSomaSemPerda);
					
					beanFinCobLongoPrazo.setSequenciaLancamentoTipo(400);
					beanFinCobLongoPrazo.setSequenciaLancamentoItem(21); // Serve apenas para ordenacao
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanFinCobLongoPrazo.setValorItemParticular(somaValor); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobLongoPrazo.setValorItemResidencial(somaValor);
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobLongoPrazo.setValorItemComercial(somaValor);
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobLongoPrazo.setValorItemIndustrial(somaValor);
						}
					}else {
						beanFinCobLongoPrazo.setValorItemPublico(somaValor); // PUBLICO	
					}
					
				}else if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(20) &&
						indicadorFinCobLongoPrazo == 1) {
					
					chaveFinCobLongoPrazo = "zz" + nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanFinCobLongoPrazo.setValorItemParticular(beanFinCobLongoPrazo.getValorItemParticular().add(somaValor)); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobLongoPrazo.setValorItemResidencial(
									beanFinCobLongoPrazo.getValorItemResidencial().add(somaValor));
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobLongoPrazo.setValorItemComercial(
									beanFinCobLongoPrazo.getValorItemComercial().add(somaValor));
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobLongoPrazo.setValorItemIndustrial(
									beanFinCobLongoPrazo.getValorItemIndustrial().add(somaValor));
						}
					}else {
						beanFinCobLongoPrazo.setValorItemPublico(
								beanFinCobLongoPrazo.getValorItemPublico().add(somaValor)); // PUBLICO
					}
					
				}else if (indicadorFinCobLongoPrazo == 1) {
					dadosAgrupados.put(chaveFinCobLongoPrazo, beanFinCobLongoPrazo);
					indicadorFinCobLongoPrazo = 2;
				}
				//*****************************************************************************
				
				
				//*****************************************************************************
				// Verifica se adiciona o subtotal para itens referentes a Contas (Documentos Emitidos)
				//*****************************************************************************
				if (numSequenciaTipoLancamento.equals(100) && indicadorConta == 0) {
					
					indicadorConta = 1;
					beanConta = new RelatorioSaldoContasAReceberContabilBean(
							idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio,
							nomeUnidadeNegocio, codigoCentroCusto, tipoGrupo, idItemGrupo,
							nomeItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem,
							"                            SUBTOTAL",
							totalizadorSomaSemPerdaParticular, totalizadorSomaSemPerdaPublico,
							totalizadorSomaSemPerda);
					
					beanConta.setSequenciaLancamentoTipo(100);
					beanConta.setSequenciaLancamentoItem(101); // Serve apenas para ordenacao
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanConta.setValorItemParticular(somaValor); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanConta.setValorItemResidencial(somaValor);
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanConta.setValorItemComercial(somaValor);
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanConta.setValorItemIndustrial(somaValor);
						}
					} else {
						beanConta.setValorItemPublico(somaValor); // PUBLICO	
					}
					
				} else if (numSequenciaTipoLancamento.equals(100) && indicadorConta == 1) {
					beanConta.setDescricaoLancamentoItem(descricaoLancamentoItem);
					
					chaveConta = "zzz" + nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanConta.setValorItemParticular(beanConta.getValorItemParticular().add(somaValor)); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanConta.setValorItemResidencial(
									beanConta.getValorItemResidencial().add(somaValor));
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanConta.setValorItemComercial(
									beanConta.getValorItemComercial().add(somaValor));
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanConta.setValorItemIndustrial(
									beanConta.getValorItemIndustrial().add(somaValor));
						}
					}else {
						beanConta.setValorItemPublico(
								beanConta.getValorItemPublico().add(somaValor)); // PUBLICO
					}
					
				} else if (indicadorConta == 1) {
					dadosAgrupados.put(chaveConta, beanConta);
					indicadorConta = 2;
				}
				//*****************************************************************************
				
			
				//*****************************************************************************
				// Verifica se adiciona o subtotal para itens referentes a Guias (Documentos Emitidos)
				//*****************************************************************************
				if (numSequenciaTipoLancamento.equals(200) && indicadorGuia == 0) {
					
					indicadorGuia = 1;
					beanGuia = new RelatorioSaldoContasAReceberContabilBean(
							idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio,
							nomeUnidadeNegocio, codigoCentroCusto, tipoGrupo, idItemGrupo,
							nomeItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem,
							"                            SUBTOTAL",
							totalizadorSomaSemPerdaParticular, totalizadorSomaSemPerdaPublico,
							totalizadorSomaSemPerda);
					
					beanGuia.setSequenciaLancamentoTipo(200);
					beanGuia.setSequenciaLancamentoItem(99); // Serve apenas para ordenacao
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanGuia.setValorItemParticular(somaValor); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanGuia.setValorItemResidencial(somaValor);
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanGuia.setValorItemComercial(somaValor);
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanGuia.setValorItemIndustrial(somaValor);
						}
					}else {
						beanGuia.setValorItemPublico(somaValor); // PUBLICO	
					}
					
				}else if (numSequenciaTipoLancamento.equals(200) && indicadorGuia == 1) {
					beanGuia.setDescricaoLancamentoItem(descricaoLancamentoItem);
					
					chaveGuia = "zzzz" + nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanGuia.setValorItemParticular(beanGuia.getValorItemParticular().add(somaValor)); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanGuia.setValorItemResidencial(
									beanGuia.getValorItemResidencial().add(somaValor));
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanGuia.setValorItemComercial(
									beanGuia.getValorItemComercial().add(somaValor));
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanGuia.setValorItemIndustrial(
									beanGuia.getValorItemIndustrial().add(somaValor));
						}
					}else {
						beanGuia.setValorItemPublico(
								beanGuia.getValorItemPublico().add(somaValor)); // PUBLICO
					}
					
				} else if (indicadorGuia == 1) {
					dadosAgrupados.put(chaveGuia, beanGuia);
					indicadorGuia = 2;
				}
				//*****************************************************************************
				
				String chave = nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
				
				RelatorioSaldoContasAReceberContabilBean bean = dadosAgrupados.get(chave);
				
				if (bean == null){
					bean = new RelatorioSaldoContasAReceberContabilBean
					(idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio, nomeUnidadeNegocio,
							codigoCentroCusto, tipoGrupo, idItemGrupo, nomeItemGrupo, descricaoLancamentoTipo, 
							descricaoLancamentoItem, descricaoItemContabil, totalizadorSomaSemPerdaParticular, 
							totalizadorSomaSemPerdaPublico, totalizadorSomaSemPerda);
					dadosAgrupados.put(chave, bean);
					bean.setSequenciaLancamentoTipo(numSequenciaTipoLancamento);
					bean.setSequenciaLancamentoItem(numSequenciaLancamentoItem);
				} 
				
				if (numSequenciaTipoLancamento.equals(310)) {
					descImpostosDeduzidos = descricaoLancamentoItem;
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()) {
						valorImpostosDeduzidosParticular = somaValor;
					}
					if (tipoCategoria.intValue() == CategoriaTipo.PUBLICO.intValue()) {
						valorImpostosDeduzidosPublico = somaValor;
					}
					valorImpostosDeduzidosTotal = valorImpostosDeduzidosTotal.add(somaValor);
				}
				bean.setDescImpostosDeduzidos(descImpostosDeduzidos);
				bean.setValorImpostosDeduzidosParticular(valorImpostosDeduzidosParticular);
				bean.setValorImpostosDeduzidosPublico(valorImpostosDeduzidosPublico);
				bean.setValorImpostosDeduzidosTotal(valorImpostosDeduzidosTotal);
				bean.setIdLancamentoTipo(idLancamentoTipo);
				
				if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
					bean.setValorItemParticular(bean.getValorItemParticular().add(somaValor)); // PARTICULAR
					
					if (idCategoria.equals(Categoria.RESIDENCIAL)) {
						bean.setValorItemResidencial(somaValor);
					}else if (idCategoria.equals(Categoria.COMERCIAL)) {
						bean.setValorItemComercial(somaValor);
					}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
						bean.setValorItemIndustrial(somaValor);
					}
					
					bean.setCodigoContabilParticular(numeroConta);
					
					//Total geral em perdas
					if (idLancamentoTipo.intValue() != LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.intValue()
							&& idLancamentoTipo.intValue() != LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.intValue() ){
						
						if (!numSequenciaTipoLancamento.equals(310)) {
							totalizadorSomaSemPerdaParticular = totalizadorSomaSemPerdaParticular.add(somaValor);
							
							if (idCategoria.equals(Categoria.RESIDENCIAL)) {
								totalizadorSomaSemPerdaResidencial = 
									totalizadorSomaSemPerdaResidencial.add(somaValor);
							}else if (idCategoria.equals(Categoria.COMERCIAL)) {
								totalizadorSomaSemPerdaComercial =
									totalizadorSomaSemPerdaComercial.add(somaValor);
							}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
								totalizadorSomaSemPerdaIndustrial =
									totalizadorSomaSemPerdaIndustrial.add(somaValor);
							}
						}
						bean.setTotalGeralSemPerdasParticular(totalizadorSomaSemPerdaParticular);
						totalizadorSomaSemPerda = totalizadorSomaSemPerdaParticular.add(totalizadorSomaSemPerdaPublico);
						bean.setTotalGeralSemPerdas(totalizadorSomaSemPerda);
						
						bean.setTotalGeralSemPerdasResidencial(totalizadorSomaSemPerdaResidencial);
						bean.setTotalGeralSemPerdasComercial(totalizadorSomaSemPerdaComercial);
						bean.setTotalGeralSemPerdasIndustrial(totalizadorSomaSemPerdaIndustrial);
					}
					
				} else {
					bean.setValorItemPublico(somaValor); // PUBLICO					
					bean.setCodigoContabilPublico(numeroConta);
					
					//Total geral em perdas
					if (idLancamentoTipo.intValue() != LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.intValue()
							&& idLancamentoTipo.intValue() != LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.intValue() ){
						
						if (!numSequenciaTipoLancamento.equals(310)) {
							totalizadorSomaSemPerdaPublico = totalizadorSomaSemPerdaPublico.add(somaValor);
						}
						bean.setTotalGeralSemPerdasPublico(totalizadorSomaSemPerdaPublico);
						totalizadorSomaSemPerda = totalizadorSomaSemPerdaParticular.add(totalizadorSomaSemPerdaPublico);
						bean.setTotalGeralSemPerdas(totalizadorSomaSemPerda);
					}
				}
			} 
		}catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		return new ArrayList(dadosAgrupados.values());
	}
	
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/04/2008 
     */
    public void gerarTXTContasBaixadasContabilmente(
            Map parametros, Integer idSetorComercial, Integer idFuncionalidadeIniciada, Integer faixa)
            throws ControladorException{
        
        int idUnidadeIniciada = 0;

        
        try {
        /*
         * Registrar o in�cio do processamento da Unidade de
         * Processamento do Batch
        */
                    
            idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.SETOR_COMERCIAL,(idSetorComercial));

            Integer referenciaInicial = (Integer)parametros.get("referenciaInicial");
            Integer referenciaFinal = (Integer)parametros.get("referenciaFinal");
            Short tipo = (Short)parametros.get("tipo");
            Short periodicidade = (Short)parametros.get("periodicidade");
            
            Map contasBaixadasContabilmenteMap = null;
            StringBuilder contasBaixadasContabilmenteTXT = new StringBuilder();
            
            String nomeZip = "";    
            String nomeDiretorio = "";
            if(periodicidade.equals(ConstantesSistema.ACUMULADO)){
                nomeDiretorio = "ContasBContabilAcumulado" + referenciaFinal;
            }else{
                
                if(referenciaInicial.equals(referenciaFinal)){
                    nomeDiretorio = "ContasBContabilMensal"+referenciaInicial;
                }else{
                    nomeDiretorio = "ContasBContabilMensal"+referenciaInicial + "A" + referenciaFinal;
                }    
            }
            IoUtil.criarDiretorio(nomeDiretorio);
            
            if(tipo.equals(ConstantesSistema.ANALITICO)){
                
                Collection colecaoQuadras = getControladorLocalidade().pesquisarIdQuadraPorSetorComercial(idSetorComercial);
                Iterator iterator = colecaoQuadras.iterator();

                while (iterator.hasNext()) {

                    Integer idQuadra = (Integer) iterator.next();
                   
                    contasBaixadasContabilmenteMap = this
                                .consultarDadosContasBaixadasContabilmenteTXT(
                                        referenciaInicial, referenciaFinal, idQuadra, faixa, periodicidade);
                        
                    StringBuilder txtContasBaixadasContabilmentePorQuadra =  (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
                    if (txtContasBaixadasContabilmentePorQuadra != null){
                        contasBaixadasContabilmenteTXT.append(txtContasBaixadasContabilmentePorQuadra);
                    }
                }
                
                nomeZip = "CONTAS_BAIXADAS_CONTABILMENTE_"
                    + Util.adicionarZerosEsquedaNumero(3,"" + idSetorComercial)  + "_F" + faixa ;
                
            }else if (tipo.equals(ConstantesSistema.SINTETICO)){
                
                contasBaixadasContabilmenteTXT.append(this.
                    consultarSomatorioValorContasBaixadasContabilmenteTXT(
                            referenciaInicial, referenciaFinal, periodicidade));
                
                nomeZip = "CONTAS_BAIXADAS_CONTABILMENTE_SINTETICO"  ;
                
            }
            
            
            if (contasBaixadasContabilmenteTXT != null
                    && contasBaixadasContabilmenteTXT.length() != 0) {

                BufferedWriter out = null;
                File arquivoTxt = new File(nomeDiretorio + "/" + nomeZip + ".txt");
                
                System.out.println("CRIANDO ZIP -- " + idSetorComercial);
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoTxt.getAbsolutePath())));
                out.write(contasBaixadasContabilmenteTXT.toString());
                out.flush();
                out.close();
                
                out = null;
                arquivoTxt = null;
                contasBaixadasContabilmenteTXT = null;
                
            }
            
            
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
        
        } catch (Exception ex) {
            ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
        }
        
        
    }
    
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @param quantidadeMaxima 
     * @param indice 
     * @date: 09/04/2008 
     */
    public Map
            consultarDadosContasBaixadasContabilmenteTXT(
                Integer referenciaInicio, Integer referenciaFinal,
                Integer idQuadra, Integer faixa,Short periodicidade)throws ControladorException{
        
        StringBuilder contasBaixadasContabilmenteTXT = null;
        Collection colecaoDadosContasBaixadasContabilmente = null;
        
        Map retorno = new HashMap();
        
        try {
            
            if (faixa.equals(ConstantesSistema.FAIXA_1)){
                
                colecaoDadosContasBaixadasContabilmente = repositorioFinanceiro
                .consultarDadosContasBaixadasContabilmentePorQuadraFaixa1(
                        referenciaInicio, referenciaFinal, idQuadra, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_2)){
                
                colecaoDadosContasBaixadasContabilmente = repositorioFinanceiro
                .consultarDadosContasBaixadasContabilmentePorQuadraFaixa2(
                        referenciaInicio, referenciaFinal, idQuadra, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_3)){
                
                colecaoDadosContasBaixadasContabilmente = repositorioFinanceiro
                .consultarDadosContasBaixadasContabilmentePorQuadraFaixa3(
                        referenciaInicio, referenciaFinal, idQuadra,periodicidade);
            }

        if (colecaoDadosContasBaixadasContabilmente != null 
                && !colecaoDadosContasBaixadasContabilmente.isEmpty()) {

            Iterator iteratorColecaoDadosContasBaixadasContabilmente = colecaoDadosContasBaixadasContabilmente.iterator();
            contasBaixadasContabilmenteTXT = new StringBuilder();
            
            StringBuilder contaTxt = null;
            Imovel imovel = null;
            Localidade localidade = null;
            SetorComercial setorComercial = null;
            Quadra quadra = null;
            
            while (iteratorColecaoDadosContasBaixadasContabilmente.hasNext()) {

                Object[] arrayDados = (Object[]) 
                    iteratorColecaoDadosContasBaixadasContabilmente.next();

                contaTxt = new StringBuilder();
                imovel = new Imovel();
                
                //referencia Baixa Contabil
                String referenciaBaixaContabil = "";
                if (arrayDados[0] != null) {
                    referenciaBaixaContabil = Util.formatarAnoMesParaMesAno((Integer) arrayDados[0]);
                }
                
                //matricula e endere�o do imovel 
                String matriculaImovel = "";
                String endereco = "";
                if (arrayDados[1] != null) {
                    matriculaImovel = ((Integer) arrayDados[1]).toString();
                    
                    endereco = getControladorEndereco().pesquisarEndereco((Integer) arrayDados[1]);
                }
                
                //referencia da fatura
                String referenciaFatura = "";
                if (arrayDados[2] != null) {
                    referenciaFatura = Util.formatarAnoMesParaMesAno((Integer) arrayDados[2]);
                }
                
                //id Localidade
                localidade = new Localidade();
                if (arrayDados[3] != null) {
                    localidade.setId((Integer) arrayDados[3]);
                    imovel.setLocalidade(localidade);
                }
                
                //codigo do setor comercial
                setorComercial = new SetorComercial();
                if (arrayDados[4] != null) {
                    setorComercial.setId((Integer) arrayDados[4]);
                    imovel.setSetorComercial(setorComercial);
                }
                
                //numero da quadra
                if (arrayDados[5] != null) {
                    quadra = new Quadra();
                    quadra.setNumeroQuadra((Integer) arrayDados[5]);
                    imovel.setQuadra(quadra);
                }
                
                //lote
                if (arrayDados[6] != null) {
                    imovel.setLote((Short) arrayDados[6]);
                }
                
                if (arrayDados[7] != null) {
                    imovel.setLote((Short) arrayDados[7]);
                }
                
                //valor da conta
                String valorContaFormatado = "";
                if (arrayDados[8] != null) {
                    BigDecimal valorConta = (BigDecimal) arrayDados[8];
                    valorContaFormatado = Util.formatarMoedaReal(valorConta);
                }
                
                //nome do usuario
                String nomeUsuario = "";
                if (arrayDados[9] != null) {
                    nomeUsuario = (String) arrayDados[9];
                }
                
                //situa��o de ligacao agua
                String situacaoAgua = "";
                if (arrayDados[10] != null) {
                    situacaoAgua = (String) arrayDados[10];
                }
                
                //situa��o de liga��o esgoto
                String situacaoEsgoto = "";
                if (arrayDados[11] != null) {
                    situacaoEsgoto = (String) arrayDados[11];
                }
                
                contaTxt.append(Util.completaString(referenciaBaixaContabil,10));
                contaTxt.append(Util.completaString(matriculaImovel,12));
                contaTxt.append(Util.completaString(imovel.getInscricaoFormatada(),23));
                contaTxt.append(Util.completaString(nomeUsuario,53));
                contaTxt.append(Util.completaString(endereco,53));
                contaTxt.append(Util.completaString(situacaoAgua,23));
                contaTxt.append(Util.completaString(situacaoEsgoto,23));
                contaTxt.append(Util.completaString(referenciaFatura,10));
                contaTxt.append(Util.completaStringComEspacoAEsquerda(valorContaFormatado,15));
                
                contasBaixadasContabilmenteTXT.append(contaTxt.toString());
               
                contaTxt = null;
                contasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
                
                iteratorColecaoDadosContasBaixadasContabilmente.remove();
            }
        }
        
        colecaoDadosContasBaixadasContabilmente = null;
        retorno.put("linhasTXT",contasBaixadasContabilmenteTXT);
        
        return retorno;
        
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
      }

    }
    

    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     */
    public Map
            consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                Integer referenciaInicio, Integer referenciaFinal,Integer faixa,Short periodicidade)throws ControladorException{
        
        StringBuilder somatorioValorContasBaixadasContabilmenteTXT = null;
        Collection colecaoSomatorioValorContasBaixadasContabilmente = null;
        String mensagemFaixa = "";
        
        Map retorno = new HashMap();
        
        try {
            
            if (faixa.equals(ConstantesSistema.FAIXA_1)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
                        referenciaInicio, referenciaFinal, periodicidade);
                
                mensagemFaixa = ConstantesSistema.MENSAGEM_FAIXA_1;
                
                System.out.println("FAIXA_1");
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_2)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
                        referenciaInicio, referenciaFinal, periodicidade);
                
                mensagemFaixa = ConstantesSistema.MENSAGEM_FAIXA_2;
                
                System.out.println("FAIXA_2");
            }else if (faixa.equals(ConstantesSistema.FAIXA_3)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
                        referenciaInicio, referenciaFinal, periodicidade);
                
                mensagemFaixa = ConstantesSistema.MENSAGEM_FAIXA_3;
                System.out.println("FAIXA_3");
            }
            
        BigDecimal valorAcumulado = BigDecimal.ZERO;
        valorAcumulado = valorAcumulado.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        
        if (colecaoSomatorioValorContasBaixadasContabilmente != null 
                && !colecaoSomatorioValorContasBaixadasContabilmente.isEmpty()) {

            Iterator iteratorColecaoSomatorioValorContasBaixadasContabilmente = colecaoSomatorioValorContasBaixadasContabilmente.iterator();
            somatorioValorContasBaixadasContabilmenteTXT = new StringBuilder();
            
            while (iteratorColecaoSomatorioValorContasBaixadasContabilmente.hasNext()) {

            	Object valorObject = iteratorColecaoSomatorioValorContasBaixadasContabilmente.next();
            	
            	if (valorObject != null){
            		 BigDecimal valor = (BigDecimal) valorObject;
            		 valorAcumulado = valorAcumulado.add(valor);
            	}
            	
            }   
               
            //valor da conta
            String valorFormatado = "";
            valorFormatado = Util.formatarMoedaReal(valorAcumulado);
            
            somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaString(mensagemFaixa,50));
            somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaStringComEspacoAEsquerda(valorFormatado,15));
            
            somatorioValorContasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
           
        }
        
        colecaoSomatorioValorContasBaixadasContabilmente = null;
        
        retorno.put("linhasTXT",somatorioValorContasBaixadasContabilmenteTXT);
        retorno.put("somatorio",valorAcumulado);
        
        return retorno;
        
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
      }

    }

    
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 26/05/2008 
     */
    public StringBuilder
            consultarSomatorioValorContasBaixadasContabilmenteTXT(
                Integer referenciaInicial, Integer referenciaFinal,Short periodicidade)throws ControladorException{
        
        StringBuilder somatorioValorContasBaixadasContabilmenteTXT = new StringBuilder();
        Map contasBaixadasContabilmenteMap = null;
        
        StringBuilder txtSomatorioValorContasBaixadasContabilmente = null;
        BigDecimal somatorioValor = BigDecimal.ZERO;
        BigDecimal somatorioValorTotal = BigDecimal.ZERO;
        
        String cabecalho = "RELA��O DAS CONTAS BAIXADAS CONTABILMENTE EM " 
            + Util.formatarAnoMesParaMesAno(referenciaFinal);
        somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaStringComEspacoAEsquerda("",10));
        somatorioValorContasBaixadasContabilmenteTXT.append(cabecalho);
        somatorioValorContasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
        
        
        contasBaixadasContabilmenteMap = this.consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                referenciaInicial, referenciaFinal, ConstantesSistema.FAIXA_1, periodicidade);
        
        txtSomatorioValorContasBaixadasContabilmente = 
            (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
        if (txtSomatorioValorContasBaixadasContabilmente != null){
            somatorioValorContasBaixadasContabilmenteTXT.append(txtSomatorioValorContasBaixadasContabilmente);
            somatorioValor = (BigDecimal)contasBaixadasContabilmenteMap.get("somatorio");
            somatorioValorTotal = somatorioValor;
        }
        
        
        contasBaixadasContabilmenteMap = null;
        contasBaixadasContabilmenteMap = this.consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                referenciaInicial, referenciaFinal, ConstantesSistema.FAIXA_2, periodicidade);

        txtSomatorioValorContasBaixadasContabilmente = 
            (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
        if (txtSomatorioValorContasBaixadasContabilmente != null){
            somatorioValorContasBaixadasContabilmenteTXT.append(txtSomatorioValorContasBaixadasContabilmente);
            somatorioValor = (BigDecimal)contasBaixadasContabilmenteMap.get("somatorio");
            somatorioValorTotal = somatorioValorTotal.add(somatorioValor);
        }
        
        
        contasBaixadasContabilmenteMap = null;
        contasBaixadasContabilmenteMap = this.consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                referenciaInicial, referenciaFinal, ConstantesSistema.FAIXA_3, periodicidade);

        txtSomatorioValorContasBaixadasContabilmente = 
            (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
        if (txtSomatorioValorContasBaixadasContabilmente != null){
            somatorioValorContasBaixadasContabilmenteTXT.append(txtSomatorioValorContasBaixadasContabilmente);
            somatorioValor = (BigDecimal)contasBaixadasContabilmenteMap.get("somatorio");
            somatorioValorTotal = somatorioValorTotal.add(somatorioValor);
        }
        
        
        somatorioValorContasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
        somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaString("VALOR TOTAL",50));
        somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaStringComEspacoAEsquerda(
                Util.formatarMoedaReal(somatorioValorTotal),15));
        
        return somatorioValorContasBaixadasContabilmenteTXT;
        
       
    }
    
	/**
	 * [UC0824] Gerar Relat�rio dos Par�metros Cont�beis
	 * 
	 * @author Bruno Barros
	 * @date 08/07/2008
	 * 
	 * @return Collection<RelatorioParametrosContabeisFaturamentoBean>
	 * @throws ErroRepositorioException
	 */
    public Collection<RelatorioParametrosContabeisFaturamentoBean> 
		pesquisarDadosRelatorioParametrosContabeisFaturamento( String referenciaContabil ) throws ControladorException {
		
		Collection pesquisaDados = new ArrayList();
		Integer anoMes = null;
		
		// Verificamos se o ano mes de referencia foi informado
		if ( referenciaContabil != null && !referenciaContabil.equals( "" )){
			anoMes = Integer.parseInt( Util.formatarMesAnoParaAnoMesSemBarra( referenciaContabil ) );
		}
		
		Collection<RelatorioParametrosContabeisFaturamentoBean> colRetorno = new ArrayList();		
		
		try {
			pesquisaDados = repositorioFinanceiro.pesquisarDadosRelatorioParametrosContabeisFaturamento( anoMes );			
			
			// Montamos os dados
			Iterator iter = pesquisaDados.iterator();
			
			while(iter.hasNext()){
				Object[] objetos = (Object[]) iter.next();
				
				RelatorioParametrosContabeisFaturamentoBean bean = new RelatorioParametrosContabeisFaturamentoBean(
						(String) objetos[0], // Descricao do tipo de lancamento
						(String) objetos[1], // Descricao do Item de lancamento
						(String) objetos[2], // Descricao do Item de lancamento contabil
						(String) objetos[3], // Descricao da categoria
						(String) objetos[4], // Numero da conta para debito
						(String) objetos[5], // Numero da conta para credito
						( objetos.length > 6 ? (BigDecimal) objetos[6] : null ) // So informamos o valor caso esse tenha sido calculado  				
				);
                
				colRetorno.add( bean );
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		return colRetorno;		
	}
    
    /**
     * [UC0824] Gerar Relat�rio dos Par�metros Cont�beis
     * 
     * @author Bruno Barros
     * @date 08/07/2008
     * 
     * @return Collection<RelatorioParametrosContabeisArrecadacaoBean>
     * @throws ErroRepositorioException
     */
    public Collection<RelatorioParametrosContabeisArrecadacaoBean> 
        pesquisarDadosRelatorioParametrosContabeisArrecadacao( String referenciaContabil ) throws ControladorException {
        
        Collection pesquisaDados = new ArrayList();
        Integer anoMes = null;
        
        // Verificamos se o ano mes de referencia foi informado
        if ( referenciaContabil != null && !referenciaContabil.equals( "" )){
            anoMes = Integer.parseInt( Util.formatarMesAnoParaAnoMesSemBarra( referenciaContabil ) );
        }
        
        Collection<RelatorioParametrosContabeisArrecadacaoBean> colRetorno = new ArrayList();       
        
        try {
            pesquisaDados = repositorioFinanceiro.pesquisarDadosRelatorioParametrosContabeisArrecadacao( anoMes );          
            
            // Montamos os dados
            Iterator iter = pesquisaDados.iterator();
            
            while(iter.hasNext()){
                Object[] objetos = (Object[]) iter.next();
                
                RelatorioParametrosContabeisArrecadacaoBean bean = new RelatorioParametrosContabeisArrecadacaoBean(
                        (String) objetos[0], // Descricao do tipo de recebimento
                        (String) objetos[1], // Descricao do tipo de lancamento
                        (String) objetos[2], // Descricao do Item de lancamento
                        (String) objetos[3], // Descricao do Item de lancamento contabil
                        (String) objetos[4], // Descricao da categoria
                        (String) objetos[5], // Numero da conta para cr�dito
                        (String) objetos[6], // Numero da conta para debito
                        ( objetos.length > 7 ? (BigDecimal) objetos[7] : null ) // So informamos o valor caso esse tenha sido calculado                 
                );
                
                colRetorno.add( bean );
            }
        } catch (ErroRepositorioException e) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", e);
        }
        
        return colRetorno;      
    }
    

	/**
 	 * [UC0822] Gerar Relat�rio do Valor Referente a Volumes Consumidos e N�o Faturados
 	 * 
 	 * @author Victor Cisneiros
 	 * @date 15/07/2008
 	 */
     public List<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisarVolumesConsumidosNaoFaturados(
     		Integer mesAno, String opcaoTotalizacao, Integer idEntidade) throws ControladorException { 
    	 try {
    		 return repositorioFinanceiro.pesquisarVolumesConsumidosNaoFaturados(mesAno, opcaoTotalizacao, idEntidade);
    	 } catch (ErroRepositorioException e) {
    		 throw new ControladorException(e.getMessage(), e);
    	 }
     }
     
     /**
 	 * [UC0714] Gerar Contas a Receber Cont�bil
 	 * Adiciona os Valores Contabilizados como Perdas  
 	 * @author Vivianne Sousa
 	 * @date 14/08/2009
 	 */
     private void adicionarContaAReceberContabilValoresContabilizadosComoPerdas(
             Integer idLocalidade, int anoMesAnteriorFaturamento,
             Collection colecaoContasAReceberContabil)
             throws ErroRepositorioException {
    	 
    	 Map<Integer, BigDecimal> mapValoresContabilizadosComoPerdas = null;
    	 Integer gerenciaRegionalId = null;
         Integer unidadeNegocioId = null;
         Integer localidadeId = null;
    	 
    	 Collection colecaoDadosContaAguaEsgoto = repositorioFinanceiro
    	 	.pesquisarDadosContasCategoriaValorAguaEsgoto(idLocalidade);

		 if (colecaoDadosContaAguaEsgoto != null && !colecaoDadosContaAguaEsgoto.isEmpty()) {
			 //Acumula CTCG_VLAGUA a CTCG_VLESGOTO da tabela CONTA_CATEGORIA e 
			 //da tabela CONTA com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
			 
			 mapValoresContabilizadosComoPerdas = new HashMap();
		     Iterator colecaoDadosContaAguaEsgotoIterator = colecaoDadosContaAguaEsgoto.iterator();
		     while (colecaoDadosContaAguaEsgotoIterator.hasNext()) {
		         Object[] dadosContaAguaEsgoto = (Object[]) colecaoDadosContaAguaEsgotoIterator.next();
		    	 
		         if(gerenciaRegionalId == null){
		        	 gerenciaRegionalId = (Integer) dadosContaAguaEsgoto[0];
		        	 unidadeNegocioId = (Integer) dadosContaAguaEsgoto[1];
		        	 localidadeId = (Integer) dadosContaAguaEsgoto[2];
		         }
		         
                 Integer idCategoriaConta = (Integer) dadosContaAguaEsgoto[3];
                 
                 BigDecimal valorAgua = new BigDecimal("0.00");
                 if (dadosContaAguaEsgoto[4] != null) {
                	 valorAgua = (BigDecimal) dadosContaAguaEsgoto[4]; 
                 }
                 
                 BigDecimal valorEsgoto = new BigDecimal("0.00");
                 if (dadosContaAguaEsgoto[5] != null) {
                	 valorEsgoto = (BigDecimal) dadosContaAguaEsgoto[5];
                 }
                 
                 BigDecimal valorCategoria = valorAgua.add(valorEsgoto);
                 
                 if (!mapValoresContabilizadosComoPerdas.containsKey(idCategoriaConta)) {
                	 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,BigDecimal.ZERO);
				 }

                 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,
                		 mapValoresContabilizadosComoPerdas.get(idCategoriaConta).add(valorCategoria));
                 
		     }
		 }    	 
    	 
		 
		 Collection colecaoDadosDebitoCobrado = repositorioFinanceiro
 	 	.pesquisarDadosDebitosCobradosCategoria(idLocalidade);

		 if (colecaoDadosDebitoCobrado != null && !colecaoDadosDebitoCobrado.isEmpty()) {
			 //Acumula DCCG_VLCATEGORIA da tabela DEBITO_COBRADO_CATEGORIA 
			 //com DBCB_ID=DBCB_ID da tabela DEBITO_COBRADO e 
			 //CNTA_ID=CNTA_ID da tabela CONTA com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo	 
			 
		     Iterator colecaoDadosDebitoCobradoIterator = colecaoDadosDebitoCobrado.iterator();
		     while (colecaoDadosDebitoCobradoIterator.hasNext()) {
		         Object[] dadosDebitoCobrado = (Object[]) colecaoDadosDebitoCobradoIterator.next();
		    	 
		         if(gerenciaRegionalId == null){
		        	 gerenciaRegionalId = (Integer) dadosDebitoCobrado[0];
		        	 unidadeNegocioId = (Integer) dadosDebitoCobrado[1];
		        	 localidadeId = (Integer) dadosDebitoCobrado[2];
		         }
		         
		         Integer idCategoriaConta = (Integer) dadosDebitoCobrado[3];
		         
		         BigDecimal valorCategoria = new BigDecimal("0.00");
                 if (dadosDebitoCobrado[4] != null) {
                	 valorCategoria = (BigDecimal)dadosDebitoCobrado[4];
                 }
                 
                 if (!mapValoresContabilizadosComoPerdas.containsKey(idCategoriaConta)) {
                	 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,BigDecimal.ZERO);
				 }

                 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,
                		 mapValoresContabilizadosComoPerdas.get(idCategoriaConta).add(valorCategoria));
                 

		     }
		 }    	 
		 
		 Collection colecaoDadosCreditoRealizado = repositorioFinanceiro
	 	 	.pesquisarDadosCreditosRealizadosCategoria(idLocalidade);

		 if (colecaoDadosCreditoRealizado != null && !colecaoDadosCreditoRealizado.isEmpty()) {
			 //Acumula negativamente CRCG_VLCATEGORIA da tabela CREDITO_REALIZADO_CATEGORIA
			 //com CRRZ_ID=CRRZ_ID da tabela CREDITO_REALIZADO e 
			 //CNTA_ID=CNTA_ID da tabela CONTA com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo	 
			 
		     Iterator colecaoDadosCreditoRealizadoIterator = colecaoDadosCreditoRealizado.iterator();
		     while (colecaoDadosCreditoRealizadoIterator.hasNext()) {
		         Object[] dadosCreditoRealizado = (Object[]) colecaoDadosCreditoRealizadoIterator.next();
		    	 
		         if(gerenciaRegionalId == null){
		        	 gerenciaRegionalId = (Integer) dadosCreditoRealizado[0];
		        	 unidadeNegocioId = (Integer) dadosCreditoRealizado[1];
		        	 localidadeId = (Integer) dadosCreditoRealizado[2];
		         }
		         
		         Integer idCategoriaConta = (Integer) dadosCreditoRealizado[3];
		         
		         BigDecimal valorCategoria = new BigDecimal("0.00");
                 if (dadosCreditoRealizado[4] != null) {
                	 valorCategoria = (BigDecimal)dadosCreditoRealizado[4];
                 }
            
                 if (!mapValoresContabilizadosComoPerdas.containsKey(idCategoriaConta)) {
                	 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,BigDecimal.ZERO);
				 }

                 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,
                		 mapValoresContabilizadosComoPerdas.get(idCategoriaConta).subtract(valorCategoria));

		     }
		 }    	 
			 
		 if(mapValoresContabilizadosComoPerdas != null){
			 //Pesquisa a cole��o de categorias no sistema
			 Collection<Integer> colecaoIdsCategorias = this.repositorioArrecadacao.pesquisarIdsCategorias();
			 
			 for (Integer idCategoria : colecaoIdsCategorias) {
				 if (mapValoresContabilizadosComoPerdas.containsKey(idCategoria)) {
					 if (mapValoresContabilizadosComoPerdas.get(idCategoria).doubleValue() > 0.00) {
						
						  BigDecimal valorCategoria = mapValoresContabilizadosComoPerdas.get(idCategoria);
						
						  //Gravar o valor com sinal negativo
						  ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
			                        anoMesAnteriorFaturamento, gerenciaRegionalId,
			                        unidadeNegocioId, localidadeId,
			                        idCategoria, valorCategoria.multiply(new BigDecimal("-1")),
			                        LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS, 700,
			                        LancamentoItem.VALORES_CONTABILIZADOS_COMO_PERDAS, 60, null);
		
			                colecaoContasAReceberContabil.add(contaAReceberContabil);
						
					 }
				 }
			 }			
		 }

     }
     
     /**
  	 * [UC0714] Gerar Contas a Receber Cont�bil
  	 * Adiciona os Recebimentos N�o Identificados
  	 * @author Vivianne Sousa, Ivan Sergio
  	 * @date 17/08/2009, 08/11/2010
  	 * @alteracao: 08/11/2010 - RM2477 - Altera��o no Relat�rio de Saldo de contas cont�bil (Item 4)
  	 */
      private void adicionarContaAReceberContabilRecebimentosNaoIdentificados(
              Integer idLocalidade, int anoMesAnteriorFaturamento,
              int anoMesArrecadacao, Collection colecaoContasAReceberContabil)
              throws ControladorException,ErroRepositorioException {
     	 
    	  Localidade localidade = repositorioFinanceiro.pesquisarUnidadeNegocioEGerenciaDaLocalidade(idLocalidade);
     	  Integer idGerenciaRegional = localidade.getGerenciaRegional().getId();
          Integer idUnidadeNegocio = localidade.getUnidadeNegocio().getId();
          
          Collection colecaoValorPagamentoImovel = repositorioFinanceiro
	 	 	.pesquisarValorPagamentoImovel(idLocalidade,anoMesArrecadacao);
          
          Map<Integer, BigDecimal> mapRecebimentosNaoIdentificados = null;

		 if (colecaoValorPagamentoImovel != null && !colecaoValorPagamentoImovel.isEmpty()) {
			 
			 Iterator iterValorPagamentoImovel = colecaoValorPagamentoImovel.iterator(); 
			 BigDecimal valorPagamento = BigDecimal.ZERO;
			 Integer idImovel = null;
			 Imovel imovel = null;
			 mapRecebimentosNaoIdentificados = new HashMap();
			 
			 while (iterValorPagamentoImovel.hasNext()) {
				Object[] arrayDadosPagamento = (Object[]) iterValorPagamentoImovel.next();
				
				idImovel = (Integer) arrayDadosPagamento[0];
				imovel = new Imovel();
				imovel.setId(idImovel);
				
				valorPagamento = (BigDecimal) arrayDadosPagamento[1];

				if (idImovel != null) {

					// [UC0108 - Obter Quantidade de Economias por Categoria]
					Collection colecaoCategoriasImovel = getControladorImovel().
							obterQuantidadeEconomiasCategoria(imovel);
					
					Iterator iteratorColecaoCategoriasImovel = colecaoCategoriasImovel.iterator();

					// [UC0185 - Obter Valor por Categoria]
					Iterator iteratorColecaoValorPagamentoPorCategoria = (getControladorImovel()
							.obterValorPorCategoria(colecaoCategoriasImovel,valorPagamento)).iterator();

					while (iteratorColecaoCategoriasImovel.hasNext()
							&& iteratorColecaoValorPagamentoPorCategoria.hasNext()) {
						
						Categoria categoria = (Categoria) iteratorColecaoCategoriasImovel.next();
						valorPagamento = (BigDecimal) iteratorColecaoValorPagamentoPorCategoria.next();

						if (!mapRecebimentosNaoIdentificados.containsKey(categoria.getId())) {
							mapRecebimentosNaoIdentificados.put(categoria.getId(),BigDecimal.ZERO);
						}

						mapRecebimentosNaoIdentificados.put(categoria.getId(),
								mapRecebimentosNaoIdentificados.get(categoria.getId()).add(valorPagamento));
					}

				} else {
					if (!mapRecebimentosNaoIdentificados.containsKey(Categoria.RESIDENCIAL)) {
						mapRecebimentosNaoIdentificados.put(Categoria.RESIDENCIAL,BigDecimal.ZERO);
					}
					mapRecebimentosNaoIdentificados.put(Categoria.RESIDENCIAL,
						mapRecebimentosNaoIdentificados.get(Categoria.RESIDENCIAL).add(valorPagamento));
				} 
			}				
				
			 //Pesquisa a cole��o de categorias no sistema
			 Collection<Integer> colecaoIdsCategorias = this.repositorioArrecadacao.pesquisarIdsCategorias();
			 
			 for (Integer idCategoria : colecaoIdsCategorias) {
				 if (mapRecebimentosNaoIdentificados.containsKey(idCategoria)) {
					 if (mapRecebimentosNaoIdentificados.get(idCategoria).doubleValue() > 0.00) {
						
						  BigDecimal valorCategoria = mapRecebimentosNaoIdentificados.get(idCategoria);
						  //Gravar o valor com sinal negativo
						  ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
			                        anoMesAnteriorFaturamento, idGerenciaRegional,
			                        idUnidadeNegocio, idLocalidade,
			                        idCategoria, valorCategoria.multiply(new BigDecimal("-1")),
			                        LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS, 800,
			                        LancamentoItem.RECEBIMENTOS_NAO_IDENTIFICADOS, 60, null);
		
			                colecaoContasAReceberContabil.add(contaAReceberContabil);
						
					 }
				 }
			 }			 
			 
		 }
          
      }
      
	public void gerarLancamentosContabeisAvisosBancarios(Integer anoMesArrecadacao, int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {

			/*
			 * Seleciona, a partir da tabela AVISO_BANCARIO com ano/m�s de refer�ncia da arrecada��o (AVBC_AMREFERENCIAARRECADACAO) menor ou igual ao ano/m�s da
			 * arrecada��o recebido e com valor contabilizado (AVBC_VLCONTABILIZADO) diferente do valor realizado (AVBC_VLREALIZADO) e com valor realizado maior
			 * que zero ordenando pelo arrecadador (ARRC_ID) e pela data de realiza��o (AVBC_DTREALIZADA)
			 */
			Collection colecaoAvisosBancarios = this.pesquisarAvisosBancariosParaGerarLancamentosContabeis(anoMesArrecadacao);

			if (colecaoAvisosBancarios != null && !colecaoAvisosBancarios.isEmpty()) {

				// Gera os lan�amentos cont�beis na tabela LANCAMENTO_CONTABIL para o m�s e ano da arrecada��o (LCNB_AMLANCAMENTO) e origem com o valor correspondente a aviso banc�rio (LCOR_ID)
				LancamentoContabil lancamentoContabil = this.gerarLancamentoContabilParaAvisoBancario(anoMesArrecadacao);

				Iterator iterator = colecaoAvisosBancarios.iterator();

				while (iterator.hasNext()) {

					AvisoBancario avisoBancario = (AvisoBancario) iterator.next();

					BigDecimal valorASerContabilizado = avisoBancario.getValorRealizado().subtract(avisoBancario.getValorContabilizado());

					/*
					 * Para os avisos banc�rios de cr�dito (AVBC_ICCREDITODEBITO=1) e com o valor a ser contabilizado positivo
					 * OU
					 * Para os avisos banc�rios de d�bito (AVBC_ICCREDITODEBITO=2) e com valor a ser contabilizado negativo:
					 */
					if ((avisoBancario.getIndicadorCreditoDebito().equals(AvisoBancario.INDICADOR_CREDITO) && valorASerContabilizado.compareTo(new BigDecimal("0.00")) > 0)
							|| (avisoBancario.getIndicadorCreditoDebito().equals(AvisoBancario.INDICADOR_DEBITO) && valorASerContabilizado.compareTo(new BigDecimal("0.00")) < 0)) {

						/*
						 * CONTA CONT�BIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta (CNCT_NMCONTA) correspondente a BANCO
						 * C�DIGO TERCEIRO = (CTBC_NNCONTACONTABIL da tabela CONTA_BANCARIA com CTBC_ID igual a CTBC_ID da tabela AVISO_BANCARIO)
						 */
						ContaContabil contaContabilDebito = this.pesquisarContaContabilPorNomeConta("BANCO");

						this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, AvisoBancario.INDICADOR_DEBITO, avisoBancario, contaContabilDebito, valorASerContabilizado.abs(),
								avisoBancario.getContaBancaria().getNumeroContaContabil());

						/*
						 * CONTA CONT�BIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta (CNCT_NMCONTA) correspondente a ARRECADACAO A DISCRIMINAR
						 * C�DIGO TERCEIRO = NULL
						 */
						ContaContabil contaContabilCredito = this.pesquisarContaContabilPorNomeConta("ARRECADACAO A DISCRIMINAR");

						this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, AvisoBancario.INDICADOR_CREDITO, avisoBancario, contaContabilCredito, valorASerContabilizado.abs(),
								null);
					}

					/*
					 * Para os avisos banc�rios de d�bito (AVBC_ICCREDITODEBITO=2) e com o valor a ser contabilizado positivo
					 * OU
					 * Para os avisos banc�rios de cr�dito (AVBC_ICCREDITODEBITO=1) e com valor a ser contabilizado negativo
					 */
					else {

						/*
						 * CONTA CONT�BIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta (CNCT_NMCONTA) correspondente a ARRECADACAO A DISCRIMINAR
						 * C�DIGO TERCEIRO = NULL
						 */
						ContaContabil contaContabilDebito = this.pesquisarContaContabilPorNomeConta("ARRECADACAO A DISCRIMINAR");

						this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, AvisoBancario.INDICADOR_DEBITO, avisoBancario, contaContabilDebito, valorASerContabilizado.abs(), null);

						/*
						 * CONTA CONT�BIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta (CNCT_NMCONTA) correspondente a BANCO
						 * C�DIGO TERCEIRO = (CTBC_NNCONTACONTABIL da tabela CONTA_BANCARIA com CTBC_ID igual a CTBC_ID da tabela  AVISO_BANCARIO)
						 */
						ContaContabil contaContabilCredito = this.pesquisarContaContabilPorNomeConta("BANCO");

						this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, AvisoBancario.INDICADOR_CREDITO, avisoBancario, contaContabilCredito, valorASerContabilizado.abs(),
								avisoBancario.getContaBancaria().getNumeroContaContabil());
					}

					BigDecimal valorContabilizado = avisoBancario.getValorContabilizado().add(valorASerContabilizado);
					this.atualizarValorContabilizado(avisoBancario.getId(), valorContabilizado);
				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	protected List<AvisoBancario> pesquisarAvisosBancariosParaGerarLancamentosContabeis(Integer anoMesReferenciaArrecadacao) 
    	throws ControladorException {
		
    	List<AvisoBancario> retorno = null;
		
		try {
			retorno = repositorioFinanceiro.pesquisarAvisosBancariosParaGerarLancamentosContabeis(anoMesReferenciaArrecadacao);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
    
    
    /**
     * [UC0992] Gerar Lan�amentos Cont�beis dos Avisos Banc�rios 
     *
     * @author Raphael Rossiter
     * @date 22/02/2010
     *
     * @param anoMesReferenciaArrecadacao
     * @return LancamentoContabil
     * @throws ControladorException
     */
    protected LancamentoContabil gerarLancamentoContabilParaAvisoBancario(Integer anoMesReferenciaArrecadacao) throws ControladorException {
    	
    	LancamentoContabil lancamentoContabil = new LancamentoContabil();
    	
    	//ANO M�S LAN�AMENTO = Ano/m�s de refer�ncia da arrecada��o recebido
    	lancamentoContabil.setAnoMes(anoMesReferenciaArrecadacao.intValue());
    	
    	//ORIGEM DO LAN�AMENTO = Valor correspondente a aviso banc�rio da tabela LANCAMENTO_ORIGEM
    	LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
    	lancamentoOrigem.setId(LancamentoOrigem.AVISO_BANCARIO);
    	lancamentoContabil.setLancamentoOrigem(lancamentoOrigem);
    	
    	/*
    	 * TIPO DO LANCAMENTO = Tipo de Lan�amento (LCTP_ID da tabela LANCAMENTO_TIPO com o 
    	 * valor correspondente a aviso banc�rio)
    	 */
    	LancamentoTipo lancamentoTipo = new LancamentoTipo();
    	lancamentoTipo.setId(LancamentoTipo.AVISO_BANCARIO);
    	lancamentoContabil.setLancamentoTipo(lancamentoTipo);
    	
    	//LOCALIDADE = LOCA_ID da tabela LOCALIDADE com LOCA_ICSEDE = 1
		Localidade localidadeSede = this.getControladorCobranca().pesquisarLocalidadeSede();
		lancamentoContabil.setLocalidade(localidadeSede);
    	
    	//�LTIMA ALTERA��O
    	lancamentoContabil.setUltimaAlteracao(new Date());
    	
    	//INSERINDO O LAN�AMENTO CONT�BIL
    	Integer idLancamentoContabil = (Integer) this.getControladorUtil().inserir(lancamentoContabil);
    	lancamentoContabil.setId(idLancamentoContabil);
    	
    	return lancamentoContabil;
    }
    
    
    /**
     * [UC0992] Gerar Lan�amentos Cont�beis dos Avisos Banc�rios 
     */
    protected LancamentoContabilItem gerarLancamentoContabilItem(Integer anoMesReferenciaArrecadacao, LancamentoContabil lancamentoContabil, Short indicadorCreditoDebito,
    		AvisoBancario avisoBancario, ContaContabil contaContabil, BigDecimal valorLancamento, Integer codigoTerceiro) throws ControladorException {

		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		lancamentoContabilItem.setIndicadorDebitoCredito(indicadorCreditoDebito);
		lancamentoContabilItem.setValorLancamento(valorLancamento);
		lancamentoContabilItem.setContaContabil(contaContabil);

		/*
		 * DESCRI��O HIST�RICO = Descri��o da forma de arrecada��o
		 * (ARFM_DSARRECADACAOFORMA da tabela ARRECADACAO_FORMA com ARFM_ID da
		 * tabela AVISO_BANCARIO) concatenado com a data de realiza��o do aviso
		 * banc�rio (AVBC_DTREALIZADA) no formato DD/MM/AAAA e com a data de
		 * lan�amento do aviso banc�rio (AVBC_DTLANCAMENTO) no formato
		 * DD/MM/AAAA. Separar os campos com ponto e v�rgula.
		 */
		String descricaoArrecadacaoForma = "";
		if (avisoBancario.getDataRealizada() != null) {
			descricaoArrecadacaoForma = descricaoArrecadacaoForma + Util.formatarData(avisoBancario.getDataRealizada()) + ";";
		}

		descricaoArrecadacaoForma = descricaoArrecadacaoForma + Util.formatarData(avisoBancario.getDataLancamento());

		if (avisoBancario.getArrecadacaoForma() != null) {
			descricaoArrecadacaoForma = descricaoArrecadacaoForma + ";" + Util.completaString(avisoBancario.getArrecadacaoForma().getDescricao(), 38);
		}

		lancamentoContabilItem.setDescricaoHistorico(descricaoArrecadacaoForma);
		lancamentoContabilItem.setCodigoTerceiro(codigoTerceiro);

		/*
		 * Caso o ano/m�s de refer�ncia da arrecada��o recebido seja igual ao
		 * ano/m�s da data de realiza��o do aviso banc�rio (AVBC_DTREALIZADA),
		 * atribuir a data de realiza��o do aviso banc�rio;
		 * 
		 * Caso contr�rio atribuir a data do �ltimo dia do ano/m�s de refer�ncia
		 * da arrecada��o recebido.
		 */
		if (avisoBancario.getDataRealizada() != null && anoMesReferenciaArrecadacao.equals(Util.getAnoMesComoInteger(avisoBancario.getDataRealizada()))) {
			lancamentoContabilItem.setDataLancamento(avisoBancario.getDataRealizada());
		} else {

			Date dataLancamento = Util.obterUltimaDataMes(Util.obterMes(anoMesReferenciaArrecadacao), Util.obterAno(anoMesReferenciaArrecadacao));

			lancamentoContabilItem.setDataLancamento(dataLancamento);
		}

		lancamentoContabilItem.setUltimaAlteracao(new Date());

		Integer idLancamentoContabilItem = (Integer) this.getControladorUtil().inserir(lancamentoContabilItem);
		lancamentoContabilItem.setId(idLancamentoContabilItem);

		return lancamentoContabilItem;
	}
    
    /**
	 * [UC0992] Gerar Lan�amentos Cont�beis dos Avisos Banc�rios 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param nomeConta
	 * @return ContaContabil
	 * @throws ControladorException
	 */
	public ContaContabil pesquisarContaContabilPorNomeConta(String nomeConta) 
	throws ControladorException{
	
		ContaContabil retorno = null;
		
		try {
			
			retorno = repositorioFinanceiro.pesquisarContaContabilPorNomeConta(nomeConta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		return retorno;
	}
	
	
	/**
	 * [UC0992] Gerar Lan�amentos Cont�beis dos Avisos Banc�rios 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param idAvisoBancario
	 * @param valorContabilizado
	 * @throws ControladorException
	 */
	protected void atualizarValorContabilizado(Integer idAvisoBancario, BigDecimal valorContabilizado)
			throws ControladorException {
		
		try {
			
			repositorioFinanceiro.atualizarValorContabilizado(idAvisoBancario, valorContabilizado);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * @author Raphael Rossiter
	 * @date 24/02/2010
	 *
	 * @return O valor de controladorCobrancaLocal
	 */
	protected ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
            int idFuncionalidadeIniciada) throws ControladorException {

        System.out.println("LOCALIDADE " + idLocalidade);
        int idUnidadeIniciada = 0;

        // -------------------------
        //
        // Registrar o in�cio do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
        idFuncionalidadeIniciada, UnidadeProcessamento.LOCALIDADE, idLocalidade);
        
        Session session = HibernateUtil.getSession();

        try {

        	Collection colecaoDocumentosAReceberResumo = new ArrayList();
        	
        	/*
        	 * O sistema verifica se o resumo j� foi gerado para o �ltimo faturamento encerrado;
        	 * Caso existam, o sistema exclui os dados de refer�ncia igual � �ltima refer�ncia de 
        	 * faturamento gerada (ocorr�ncias da tabela DOCUMENTOS_A_RECEBER_RESUMO com 
        	 * DRRS_AMREFERENCIADOCUMENTOS=PARM_AMREFERENCIAFATURAMENTO -1 da tabela SISTEMA_PARAMETROS);
        	 */
        	SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
        	
        	int anoMesReferenciaFaturamentoMenosUm = Util.subtrairMesDoAnoMes(
        	sistemaParametro.getAnoMesFaturamento(), 1);

        	this.removerDocumentosAReceberFaixaResumo(anoMesReferenciaFaturamentoMenosUm, idLocalidade, session);
        	this.removerDocumentosAReceberResumo(anoMesReferenciaFaturamentoMenosUm, idLocalidade, session);
        	
        	//[SB0001 � Gerar resumo a partir de contas];
            this.gerarResumoAPartirConta(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo, session);
            
            //[SB0002 � Gerar resumo a partir de guias de pagamento]
            this.gerarResumoAPartirGuiaPagamento(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo, session);
            
            //[SB0003 � Gerar resumo a partir de d�bitos a cobrar]
            this.gerarResumoAPartirDebitoACobrar(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo, session);
            
            //[SB0004 � Gerar resumo a partir de cr�ditos a realizar]
            this.gerarResumoAPartirCreditoARealizar(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo, session);

            //ENCERRANDO A UNIDADE DE PROCESSAMENTO
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
            idUnidadeIniciada, false);

            System.out.println("FIM DA GERA��O - Localidade " + idLocalidade);

        } catch (Exception e) {
            getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
            idUnidadeIniciada, true);
            
            throw new EJBException(e);
        } finally {
        	HibernateUtil.closeSession(session);
        }
    }
	
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
	public void removerDocumentosAReceberResumo(int anoMesReferenciaFaturamento, 
		Integer idLocalidade, Session session) throws ControladorException {
		
		try {
			
			repositorioFinanceiro.removerDocumentosAReceberResumo(anoMesReferenciaFaturamento, 
			idLocalidade, session);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0001 � Gerar resumo a partir de contas]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirConta(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo, Session session) throws ControladorException{
		
		Collection<Object[]> colecaoResumoContas = null;
		
		try {
			
			colecaoResumoContas = repositorioFinanceiro.pesquisarContasAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade, session);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoContas);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0002 � Gerar resumo a partir de guias de pagamento]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirGuiaPagamento(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo, Session session) throws ControladorException{
			
		Collection<Object[]> colecaoResumoGuiasPagamento = null;
			
		try {
				
			colecaoResumoGuiasPagamento = repositorioFinanceiro.pesquisarGuiasPagamentoAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade, session);
				
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
			
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoGuiasPagamento);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0003 � Gerar resumo a partir de d�bitos a cobrar]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirDebitoACobrar(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo, Session session) throws ControladorException{
				
		Collection<Object[]> colecaoResumoDebitosACobrar = null;
				
		try {
					
			colecaoResumoDebitosACobrar = repositorioFinanceiro.pesquisarDebitosACobrarAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade, session);
					
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
				
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoDebitosACobrar);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0004 � Gerar resumo a partir de cr�ditos a realizar]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirCreditoARealizar(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo, Session session) throws ControladorException{
					
		Collection<Object[]> colecaoResumoCreditoARealizar = null;
					
		try {
						
			colecaoResumoCreditoARealizar = repositorioFinanceiro.pesquisarCreditosARealizarAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade, session);
						
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
					
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoCreditoARealizar);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 *
	 * @author Raphael Rossiter, Mariana Victor
	 * @date 11/03/2010, 29/03/2011
	 *
	 * @param anoMesReferenciaFaturamento
	 * @param colecaoDocumentosAcumulados
	 * @return Collection<DocumentosAReceberResumo>
	 * @throws ControladorException
	 */
	public void gerarDocumentosAReceberResumo(int anoMesReferenciaFaturamento,
			Collection<Object[]> colecaoDocumentosAcumulados) throws ControladorException{
		
		Collection<DocumentosAReceberFaixaResumo> colecaoDocumentosAReceberFaixaResumo = new ArrayList();
		
		
		if (colecaoDocumentosAcumulados != null && !colecaoDocumentosAcumulados.isEmpty()){
			
			Iterator iterator = colecaoDocumentosAcumulados.iterator();
			DocumentosAReceberResumo documentosAReceberResumo = null;
			Date dataVencimentoDocumentoAuxiliar = null;
			
			while (iterator.hasNext()){
				
				Object[] dadosAcumulados = (Object[]) iterator.next();
				
				
					//GERENCIA REGIONAL
					GerenciaRegional gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId((Integer) dadosAcumulados[0]);
					
					//UNIDADE DE NEGOCIO
					UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId((Integer) dadosAcumulados[1]);
					
					//LOCALIDADE
					Localidade localidade = new Localidade();
					localidade.setId((Integer) dadosAcumulados[2]);
					
					//IMOVEL PERFIL
					ImovelPerfil imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId((Integer) dadosAcumulados[3]);
					
					//ESFERA PODER
					EsferaPoder esferaPoder = new EsferaPoder();
					esferaPoder.setId((Integer) dadosAcumulados[4]);
					
					//CATEGORIA
					Categoria categoria = new Categoria();
					categoria.setId((Integer) dadosAcumulados[5]);
					
					//DOCUMENTO TIPO
					DocumentoTipo documentoTipo = new DocumentoTipo();
					documentoTipo.setId((Integer) dadosAcumulados[6]);
					
					/*
					 * QUANTIDADE DE DIAS VENCIDOS
					 * 
					 * Para os documentos do tipo CONTA E GUIA DE PAGAMENTO:
					 * Calcular a quantidade de dias vencidos a partir da data de vencimento do documento em 
					 * rela��o ao �ltimo dia do m�s de refer�ncia do faturamento (data referente ao �ltimo dia 
					 * de PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS menos a data de 
					 * vencimento do documento);
					 * 
					 * Caso contr�rio atribuir 0 (zero) a quantidade de dias de vencimento
					 */
					Integer quantidadeDiasVencido = 0;
					
					Date dataVencimentoDocumento = null;
					
					if (documentoTipo.getId().equals(DocumentoTipo.CONTA) ||
						documentoTipo.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
						
						dataVencimentoDocumento = (Date) dadosAcumulados[7];
						
						Date dataUltimoDiaReferenciaFaturamento = Util.obterUltimaDataMes(Util.obterMes(
						anoMesReferenciaFaturamento), Util.obterAno(anoMesReferenciaFaturamento));
						
						if (dataUltimoDiaReferenciaFaturamento.compareTo(dataVencimentoDocumento) > 0){
							
							quantidadeDiasVencido = Util.obterQuantidadeDiasEntreDuasDatas(dataVencimentoDocumento,
							dataUltimoDiaReferenciaFaturamento);
						}
					}
					
					if (documentosAReceberResumo != null
							&& !(documentosAReceberResumo.getGerenciaRegional().getId().equals(gerenciaRegional.getId())
								&& documentosAReceberResumo.getUnidadeNegocio().getId().equals(unidadeNegocio.getId())
								&& documentosAReceberResumo.getLocalidade().getId().equals(localidade.getId())
							    && documentosAReceberResumo.getImovelPerfil().getId().equals(imovelPerfil.getId())
								&& documentosAReceberResumo.getEsferaPoder().getId().equals(esferaPoder.getId())
								&& documentosAReceberResumo.getCategoria().getId().equals(categoria.getId())
								&& documentosAReceberResumo.getDocumentoTipo().getId().equals(documentoTipo.getId())
								&& ((dataVencimentoDocumento == null && dataVencimentoDocumentoAuxiliar == null)
										|| (dataVencimentoDocumento != null && dataVencimentoDocumentoAuxiliar != null
												&& dataVencimentoDocumento.compareTo(dataVencimentoDocumentoAuxiliar) == 0)))) {
						
						
						this.inserirDocumentosAReceberResumo(documentosAReceberResumo, colecaoDocumentosAReceberFaixaResumo);
						
						colecaoDocumentosAReceberFaixaResumo = new ArrayList();
						
						documentosAReceberResumo = new DocumentosAReceberResumo();
						dataVencimentoDocumentoAuxiliar = null;
						
												
					}
					
					if (dataVencimentoDocumento != null) {
						dataVencimentoDocumentoAuxiliar = (Date) dataVencimentoDocumento.clone();
					} else {
						dataVencimentoDocumentoAuxiliar = null;
					}
					
					if (documentosAReceberResumo == null) {
						documentosAReceberResumo = new DocumentosAReceberResumo();
						dataVencimentoDocumentoAuxiliar = null;
					}
					
					/*
					 * Ano e M�s de Refer�ncia dos documentos a receber (PARM_AMREFERENCIAFATURAMENTO - 1 da 
					 * tabela SISTEMA_PARAMETROS)
					 */
					documentosAReceberResumo.setAnoMesReferenciaRecebimentos(anoMesReferenciaFaturamento);
					
					documentosAReceberResumo.setGerenciaRegional(gerenciaRegional);
					documentosAReceberResumo.setUnidadeNegocio(unidadeNegocio);
					documentosAReceberResumo.setLocalidade(localidade);
					documentosAReceberResumo.setImovelPerfil(imovelPerfil);
					documentosAReceberResumo.setEsferaPoder(esferaPoder);
					documentosAReceberResumo.setCategoria(categoria);
					documentosAReceberResumo.setDocumentoTipo(documentoTipo);
					documentosAReceberResumo.setQuantidadeDiasVencidos(quantidadeDiasVencido);
					
					/*
					 * INDICADOR SITUCAO DOCUMENTO
					 * Para os documentos do tipo CONTA E GUIA DE PAGAMENTO colocar 2 - VENCIDO, j� para os documentos
					 * do tipo DEBITO A COBRAR e CREDITO A REALIZAR colocar 1 - A VENCER
					 */
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA) ||
						documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
						
						if (quantidadeDiasVencido == 0){
							
							documentosAReceberResumo.setIndicadorSituacaoDocumentos(
							DocumentosAReceberResumo.DOCUMENTO_A_VENCER);
						}
						else{
							
							documentosAReceberResumo.setIndicadorSituacaoDocumentos(
							DocumentosAReceberResumo.DOCUMENTO_VENCIDO);
						}
					}
					else{
						
						documentosAReceberResumo.setIndicadorSituacaoDocumentos(
						DocumentosAReceberResumo.DOCUMENTO_A_VENCER);
					}
					
					
					documentosAReceberResumo.setQuantidadeDiasVencidos(quantidadeDiasVencido);
					

					DocumentosAReceberFaixaResumo documentosAReceberFaixaResumo = new DocumentosAReceberFaixaResumo();
					
					
					//QUANTIDADE DE DOCUMENTOS
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA) ||
						documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){

						documentosAReceberFaixaResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[8]);
						
						if (documentosAReceberResumo.getQuantidadeDocumentos() != null
								&& documentosAReceberResumo.getQuantidadeDocumentos() > 0) {
							documentosAReceberResumo.setQuantidadeDocumentos(documentosAReceberResumo.getQuantidadeDocumentos()
									+ (Integer) dadosAcumulados[8]);
						} else {
							documentosAReceberResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[8]);
						}
					}
					else{
						documentosAReceberFaixaResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[7]);
						
						if (documentosAReceberResumo.getQuantidadeDocumentos() != null
								&& documentosAReceberResumo.getQuantidadeDocumentos() > 0) {
							documentosAReceberResumo.setQuantidadeDocumentos(documentosAReceberResumo.getQuantidadeDocumentos()
									+ (Integer) dadosAcumulados[7]);
						} else {
							documentosAReceberResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[7]);
						}
					}
					
					//VALOR DOS DOCUMENTOS
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)){
						
						Conta contaParaCalculo = new Conta();
						
						contaParaCalculo.setValorAgua((BigDecimal) dadosAcumulados[9]);
						contaParaCalculo.setValorEsgoto((BigDecimal) dadosAcumulados[10]);
						contaParaCalculo.setDebitos((BigDecimal) dadosAcumulados[11]);
						contaParaCalculo.setValorCreditos((BigDecimal) dadosAcumulados[12]);
						contaParaCalculo.setValorImposto((BigDecimal) dadosAcumulados[13]);
						

						documentosAReceberFaixaResumo.setValorDocumentos(contaParaCalculo.getValorTotalContaBigDecimal());
						
						if (documentosAReceberResumo.getValorDocumentos() != null && !documentosAReceberResumo.getValorDocumentos().equals(BigDecimal.ZERO)) {
							documentosAReceberResumo.setValorDocumentos(documentosAReceberResumo.getValorDocumentos()
									.add(contaParaCalculo.getValorTotalContaBigDecimal()));
						} else {
							documentosAReceberResumo.setValorDocumentos(contaParaCalculo.getValorTotalContaBigDecimal());
						}
					}
					else if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){

						documentosAReceberFaixaResumo.setValorDocumentos((BigDecimal) dadosAcumulados[9]);
						
						if (documentosAReceberResumo.getValorDocumentos() != null && !documentosAReceberResumo.getValorDocumentos().equals(BigDecimal.ZERO)) {
							documentosAReceberResumo.setValorDocumentos(documentosAReceberResumo.getValorDocumentos()
									.add((BigDecimal) dadosAcumulados[9]));
						} else {
							documentosAReceberResumo.setValorDocumentos((BigDecimal) dadosAcumulados[9]);
						}
					}
					else{
						documentosAReceberFaixaResumo.setValorDocumentos((BigDecimal) dadosAcumulados[8]);
						
						if (documentosAReceberResumo.getValorDocumentos() != null && !documentosAReceberResumo.getValorDocumentos().equals(BigDecimal.ZERO)) {
							documentosAReceberResumo.setValorDocumentos(documentosAReceberResumo.getValorDocumentos().add((BigDecimal) dadosAcumulados[8]));
						} else {
							documentosAReceberResumo.setValorDocumentos((BigDecimal) dadosAcumulados[8]);
						}
						if (documentosAReceberResumo.getValorDocumentosSemParcelaAtual() != null && !documentosAReceberResumo.getValorDocumentosSemParcelaAtual().equals(BigDecimal.ZERO)) {
							documentosAReceberResumo.setValorDocumentosSemParcelaAtual(documentosAReceberResumo.getValorDocumentosSemParcelaAtual().add((BigDecimal) dadosAcumulados[10]));
						} else {
							documentosAReceberResumo.setValorDocumentosSemParcelaAtual((BigDecimal) dadosAcumulados[10]);
						}
					}
					
					//ULTIMA ALTERACAO
					documentosAReceberResumo.setUltimaAlteracao(new Date());
					

					//FAIXA
					FaixaDocumentosAReceber faixaDocumentosAReceber = new FaixaDocumentosAReceber();
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)){
						faixaDocumentosAReceber.setId((Integer) dadosAcumulados[14]);
					} else if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
						faixaDocumentosAReceber.setId((Integer) dadosAcumulados[10]);
					} else {
						faixaDocumentosAReceber.setId((Integer) dadosAcumulados[9]);
					}
					
					documentosAReceberFaixaResumo.setFaixaDocumentosAReceber(faixaDocumentosAReceber);
					documentosAReceberFaixaResumo.setUltimaAlteracao(new Date());
					colecaoDocumentosAReceberFaixaResumo.add(documentosAReceberFaixaResumo);
					
					if (!iterator.hasNext()) {
						this.inserirDocumentosAReceberResumo(documentosAReceberResumo, colecaoDocumentosAReceberFaixaResumo);
						colecaoDocumentosAReceberFaixaResumo = new ArrayList();
					}

			}
		}

	}
	
	private void inserirDocumentosAReceberResumo(DocumentosAReceberResumo documentosAReceberResumo,
			Collection<DocumentosAReceberFaixaResumo> colecaoDocumentosAReceberFaixaResumo) throws ControladorException{
		Integer idDocumentosAReceberResumo = (Integer) getControladorBatch().inserirObjetoParaBatch(
				documentosAReceberResumo);
		
		if (colecaoDocumentosAReceberFaixaResumo != null && !colecaoDocumentosAReceberFaixaResumo.isEmpty()) {
			DocumentosAReceberResumo documentosAReceberResumoInserido = new DocumentosAReceberResumo();
			documentosAReceberResumoInserido.setId(idDocumentosAReceberResumo);
			
			Iterator iteratorDocumentosFaixa = colecaoDocumentosAReceberFaixaResumo.iterator();
			while (iteratorDocumentosFaixa.hasNext()) {
				DocumentosAReceberFaixaResumo documentosAReceberFaixaResumo = (DocumentosAReceberFaixaResumo) iteratorDocumentosFaixa.next();
				
				documentosAReceberFaixaResumo.setDocumentosAReceberResumo(documentosAReceberResumoInserido);
				
				getControladorBatch().inserirObjetoParaBatch(
						documentosAReceberFaixaResumo);
				
			}
			
		}
	}
	
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Fl�vio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e ser� executado
     * atrav�s de um batch
     */
    
    public void gerarResumoReceita(int idFuncionalidadeIniciada) throws ControladorException{
		
  	 int idUnidadeIniciada = 0;
		

		 idUnidadeIniciada = 
			getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada,
				UnidadeProcessamento.FUNCIONALIDADE,0);
  	  
  	try {
			//1. apagar dados da tabela resumo_receita
			this.apagarDadosResumoReceitaMesAnoArrecadacao();
			  
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			  
			int anoMes = sistemaParametro.getAnoMesArrecadacao();
			String mes = (anoMes+"").substring(4,6);
			String ano = (anoMes+"").substring(0,4);
			String ultimoDiaMes = Util.obterUltimoDiaMes(new Integer(mes), new Integer(ano));
			  
			String dataFinal = ultimoDiaMes+"/"+mes+"/"+ano;
			String dataInicial = "01/"+mes+"/"+ano;
			  
			this.inserirDadosContasResumoReceita(dataInicial, dataFinal, sistemaParametro);
			this.inserirDadosContasHistoricoResumoReceita(dataInicial, dataFinal, sistemaParametro);
			this.inserirDadosContasOutrosResumoReceita(dataInicial, dataFinal, sistemaParametro);
			
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(
					null, idUnidadeIniciada, false);
			
		} catch (ControladorException e) {
			
			e.printStackTrace();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Fl�vio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e ser� executado
     * atrav�s de um batch
     */
    public void inserirDadosContasResumoReceita(String dataInicial, String dataFinal, SistemaParametro sistemaParametro){
  	  	
  	  try{
  	  //[SB001] - Resumo dos Pagamentos de Contas
			Collection colecaoImpostos =
				this.pesquisarImpostoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoContas =
				this.pesquisarResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDividaAtiva = 
				this.pesquisarDividaAtivaResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoCredito = pesquisarResumoPagamentoContaCredito(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoServico = pesquisarResumoPagamentoContaServico(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			//---INSERIR
			
			ResumoReceitaHelper helper = null;
			ResumoReceita resumoReceitaInserir = null;
			Arrecadador arrecadador = null;
			Banco banco = null;
			ContaBancaria contaBancaria= null;
			GerenciaRegional gerenciaRegional = null;
			UnidadeNegocio unidadeNegocio = null;
			Localidade localidade = null;
			ContaContabil contabil = null;
			Categoria categoria = null;
			
			//apartir da colecaoContas
			if(colecaoContas != null && !colecaoContas.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoContas.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaAgua() != null && !helper.getSomaAgua().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_AGUA));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaAgua());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					if(helper.getSomaEsgoto() != null && !helper.getSomaEsgoto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_ESGOTO));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaEsgoto());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoCredito != null && !colecaoCredito.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorCredito = colecaoCredito.iterator();
				
				while(iteratorCredito.hasNext()){
					helper = iteratorCredito.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaCredito() != null && !helper.getSomaCredito().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_CREDITO));
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getSomaCredito());
						resumoReceitaInserir.setValorReceita(valorInserir);
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoServico != null && !colecaoServico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorServico = colecaoServico.iterator();
				
				while(iteratorServico.hasNext()){
					helper = iteratorServico.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
										
					if(helper.getSomaServico() != null && !helper.getSomaServico().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						//contabil.setId(new Integer(ContaContabil.RECEITA_SERVICOS));
						contabil.setId(helper.getContaContabil());
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaServico());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			//apartir da colecaoImpostos
			if(colecaoImpostos != null && !colecaoImpostos.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorImpostos = colecaoImpostos.iterator();
				
				while(iteratorImpostos.hasNext()){
					helper = iteratorImpostos.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					System.out.println("categoria imposto:"+ categoria.getId());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaImposto() != null && !helper.getSomaImposto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_IMPOSTOS));
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getValorTotal());
						resumoReceitaInserir.setValorReceita(valorInserir);
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir da colecaoDividaAtiva
			if(colecaoDividaAtiva != null && !colecaoDividaAtiva.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDividaAtiva = colecaoDividaAtiva.iterator();
				
				while(iteratorDividaAtiva.hasNext()){
					helper = iteratorDividaAtiva.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaDividaAtiva() != null && !helper.getSomaDividaAtiva().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_DIVIDA_ATIVA));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
  	  } catch (ControladorException e) {
			
			e.printStackTrace();
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Fl�vio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e ser� executado
     * atrav�s de um batch
     */
    public void inserirDadosContasHistoricoResumoReceita(String dataInicial, String dataFinal, SistemaParametro sistemaParametro){
	  	
  	  try{
  	  //[SB001] - Resumo dos Pagamentos de Contas
			Collection colecaoImpostos =
				this.pesquisarImpostoHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoContas =
				this.pesquisarResumoHistoricoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDividaAtiva = 
				this.pesquisarDividaAtivaHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoCredito = pesquisarResumoHistoricoPagamentoContaCredito(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoServico = pesquisarResumoHistoricoPagamentoContaServico(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			//---INSERIR
			
			ResumoReceitaHelper helper = null;
			ResumoReceita resumoReceitaInserir = null;
			Arrecadador arrecadador = null;
			Banco banco = null;
			ContaBancaria contaBancaria= null;
			GerenciaRegional gerenciaRegional = null;
			UnidadeNegocio unidadeNegocio = null;
			Localidade localidade = null;
			ContaContabil contabil = null;
			Categoria categoria = null;
			
			//apartir da colecaoContas
			if(colecaoContas != null && !colecaoContas.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoContas.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaAgua() != null && !helper.getSomaAgua().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_AGUA));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaAgua());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					if(helper.getSomaEsgoto() != null && !helper.getSomaEsgoto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_ESGOTO));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaEsgoto());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoCredito != null && !colecaoCredito.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorCredito = colecaoCredito.iterator();
				
				while(iteratorCredito.hasNext()){
					helper = iteratorCredito.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaCredito() != null && !helper.getSomaCredito().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_CREDITO));
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getSomaCredito());
						resumoReceitaInserir.setValorReceita(valorInserir);
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoServico != null && !colecaoServico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorServico = colecaoServico.iterator();
				
				while(iteratorServico.hasNext()){
					helper = iteratorServico.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaServico() != null && !helper.getSomaServico().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
//						contabil.setId(new Integer(ContaContabil.RECEITA_SERVICOS));
						contabil.setId(helper.getContaContabil());
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaServico());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			//apartir da colecaoImpostos
			if(colecaoImpostos != null && !colecaoImpostos.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorImpostos = colecaoImpostos.iterator();
				while(iteratorImpostos.hasNext()){
					helper = iteratorImpostos.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaImposto() != null && !helper.getSomaImposto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_IMPOSTOS));
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getValorTotal());
						resumoReceitaInserir.setValorReceita(valorInserir);
						System.out.println("conta contabil:"+contabil.getId().toString() + " valor:"+helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir da colecaoDividaAtiva
			if(colecaoDividaAtiva != null && !colecaoDividaAtiva.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDividaAtiva = colecaoDividaAtiva.iterator();
				
				while(iteratorDividaAtiva.hasNext()){
					helper = iteratorDividaAtiva.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaDividaAtiva() != null && !helper.getSomaDividaAtiva().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_DIVIDA_ATIVA));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
  	  } catch (ControladorException e) {
			
			e.printStackTrace();
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Fl�vio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e ser� executado
     * atrav�s de um batch
     */
    public void inserirDadosContasOutrosResumoReceita(String dataInicial, String dataFinal, SistemaParametro sistemaParametro){
	  	
  	  try{
  		  
  		  	Collection colecaoGuias = 
				this.pesquisarPagamentoGuiaResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
  		  
			Collection colecaoGuiasHistorico = 
				this.pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoOutrasReceitas = 
				this.pesquisarOutrasReceitasResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoOutrasReceitasHistorico =
				this.pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoNaoClassificado = 
				this.pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoNaoClassificadoHistorico = 
				this.pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDebitoACobrar = 
				this.pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDebitoACobrarHistorico = 
				this.pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoHistoricoSemCorrespondente = 
				this.pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDevolucaoAvisoBancario = 
				this.pesquisarDevolucaoAvisoBancarioResumo(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDevolucaoAvisoBancarioHistorico = 
				this.pesquisarDevolucaoAvisoBancarioHistoricoResumo(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			//---INSERIR
			
			ResumoReceitaHelper helper = null;
			ResumoReceita resumoReceitaInserir = null;
			Arrecadador arrecadador = null;
			Banco banco = null;
			ContaBancaria contaBancaria= null;
			GerenciaRegional gerenciaRegional = null;
			UnidadeNegocio unidadeNegocio = null;
			Localidade localidade = null;
			ContaContabil contabil = null;
			Categoria categoria = null;
			
			//apartir
			if(colecaoGuias != null && !colecaoGuias.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoGuias.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoGuia() != null && !helper.getSomaPagamentoGuia().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoGuia());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoGuiasHistorico != null && !colecaoGuiasHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoGuiasHistorico.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoGuia() != null && !helper.getSomaPagamentoGuia().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoGuia());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			//apartir
			if(colecaoOutrasReceitas != null && !colecaoOutrasReceitas.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorOutrasReceitas = colecaoOutrasReceitas.iterator();
				
				while(iteratorOutrasReceitas.hasNext()){
					helper = iteratorOutrasReceitas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaOutrasReceitas() != null && !helper.getSomaOutrasReceitas().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.OUTRAS_RECEITAS));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir
			if(colecaoOutrasReceitasHistorico != null && !colecaoOutrasReceitasHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorOutrasReceitas = colecaoOutrasReceitasHistorico.iterator();
				System.out.println("INICIO OUTRAS RECEITAS HISTORICO\n");
				while(iteratorOutrasReceitas.hasNext()){
					helper = iteratorOutrasReceitas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaOutrasReceitas() != null && !helper.getSomaOutrasReceitas().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(helper.getContaContabil());
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir
			if(colecaoNaoClassificado != null && !colecaoNaoClassificado.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorNaoClassificados = colecaoNaoClassificado.iterator();
				
				while(iteratorNaoClassificados.hasNext()){
					helper = iteratorNaoClassificados.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.PAGAMENTO_NAO_CLASSIFICADO));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
				
//				apartir
			if(colecaoNaoClassificadoHistorico != null && !colecaoNaoClassificadoHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorNaoClassificados = colecaoNaoClassificadoHistorico.iterator();
				
				while(iteratorNaoClassificados.hasNext()){
					helper = iteratorNaoClassificados.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.HISTORICO_PAG_NAO_CLASSIFICADO));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir
			if(colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDebitoCobrar = colecaoDebitoACobrar.iterator();
				
				while(iteratorDebitoCobrar.hasNext()){
					helper = iteratorDebitoCobrar.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoDebCobrar() != null && !helper.getSomaPagamentoDebCobrar().equals(BigDecimal.ZERO)){
						System.out.println("contaCOntabil:"+ helper.getContaContabil());
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						//contabil.setId(new Integer("18"));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoDebCobrar());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
//			apartir
			if(colecaoDebitoACobrarHistorico != null && !colecaoDebitoACobrarHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDebitoCobrar = colecaoDebitoACobrarHistorico.iterator();
				
				while(iteratorDebitoCobrar.hasNext()){
					helper = iteratorDebitoCobrar.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoDebCobrar() != null && !helper.getSomaPagamentoDebCobrar().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						//contabil.setId(new Integer("18"));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoDebCobrar());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
//			apartir
			if(colecaoHistoricoSemCorrespondente != null && !colecaoHistoricoSemCorrespondente.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorSemCorrespondente = colecaoHistoricoSemCorrespondente.iterator();
				
				while(iteratorSemCorrespondente.hasNext()){
					helper = iteratorSemCorrespondente.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoHistoricoSemCorrespondente() != null && !helper.getSomaPagamentoHistoricoSemCorrespondente().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.PAG_HISTORICO_SEM_CORRESPONDENTE));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			
			//apartir
			if(colecaoDevolucaoAvisoBancario != null && !colecaoDevolucaoAvisoBancario.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDevolucaoAvisoBancario = colecaoDevolucaoAvisoBancario.iterator();
				
				while(iteratorDevolucaoAvisoBancario.hasNext()){
					helper = iteratorDevolucaoAvisoBancario.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.DEVOLUCAO_AVISO_BANCARIO));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(new BigDecimal("-"+helper.getValorTotal()));
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			
			//apartir
			if(colecaoDevolucaoAvisoBancarioHistorico != null && !colecaoDevolucaoAvisoBancarioHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDevolucaoAvisoBancario = colecaoDevolucaoAvisoBancarioHistorico.iterator();
				
				while(iteratorDevolucaoAvisoBancario.hasNext()){
					helper = iteratorDevolucaoAvisoBancario.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.DEVOLUCAO_AVISO_BANCARIO));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(new BigDecimal("-"+helper.getValorTotal()));
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			
  	  } catch (ControladorException e) {
			
			e.printStackTrace();
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * 
     * 1.O sistema apaga os dados da tabela RESUMO_RECEITA referente ao M�s/Ano da 
     * arrecada��o que est� aberta.
     *
     * Autor: Fl�vio Cordeiro
     */
    public void apagarDadosResumoReceitaMesAnoArrecadacao(){
  	  try {
			
  		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			
			FiltroResumoReceita filtroResumoReceita = new FiltroResumoReceita();
			filtroResumoReceita.adicionarParametro(
					new ParametroSimples(FiltroResumoReceita.ANO_MES_REFERENCIA, 
					sistemaParametro.getAnoMesArrecadacao()));
			
			Collection<ResumoReceita> colecaoResumoReceita = getControladorUtil().pesquisar(filtroResumoReceita, ResumoReceita.class.getName());
			
			if(!colecaoResumoReceita.isEmpty()){
				Iterator iter = colecaoResumoReceita.iterator();
				ResumoReceita resumoReceita = null;
				
				while (iter.hasNext()) {
					resumoReceita = (ResumoReceita)iter.next();
					getControladorUtil().remover(resumoReceita);
				}	
			}
			
		} catch (ControladorException e) {
			
			e.printStackTrace();
		}
    }
    
  /**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaAgua((BigDecimal)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setSomaEsgoto((BigDecimal)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	 /**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoPagamentoContaCredito(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaCredito((BigDecimal)objeto[8]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoHistoricoPagamentoContaCredito(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaCredito((BigDecimal)objeto[8]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaServico(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoPagamentoContaServico(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaServico((BigDecimal)objeto[8]);
					}
					
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoContaServico(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoHistoricoPagamentoContaServico(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaServico((BigDecimal)objeto[8]);
					}
					if ( objeto[9] != null ){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarImpostoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarImpostoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setLocalidadeNome((String)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setSomaImposto((BigDecimal)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setContaBancariaId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setBancoId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setArrecadadorId((Integer)objeto[9]);
					}
					
					//dividir valores por categoria
					if(objeto[6] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, helper.getSomaImposto());
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaImposto(valorCategoria);
																
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarImpostoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarImpostoHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setLocalidadeNome((String)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setSomaImposto((BigDecimal)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setContaBancariaId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setBancoId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setArrecadadorId((Integer)objeto[9]);
					}
					
					//dividir valores por categoria
					if(helper.getSomaImposto() != null && objeto[3] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, helper.getSomaImposto());
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaImposto(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarDividaAtivaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDividaAtivaResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaDividaAtiva((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					
					//dividir valores por categoria
					if(objeto[6] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, helper.getSomaDividaAtiva());
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaDividaAtiva(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
				
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarDividaAtivaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDividaAtivaHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaDividaAtiva((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					
					//dividir valores por categoria
					if(objeto[5] != null && objeto[3] != null){
						
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaDividaAtiva(valorCategoria);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
				
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoHistoricoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setContaBancariaId((Integer)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setBancoId((Integer)objeto[6]);
					}					
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaAgua((BigDecimal)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setSomaEsgoto((BigDecimal)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisarOutrasReceitasResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarOutrasReceitasResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaOutrasReceitas((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setCategoriaId((Integer)objeto[9]);
					}
					
					
					//dividir valores por categoria
					if(objeto[5] != null && objeto[3] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaOutrasReceitas(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoGuiaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoGuiaResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoGuia((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoGuia((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					
					if (objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		//------------------------------------------
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoNaoClassificadoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					
//					ResumoReceitaHelper resumoHelper = null;
					
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null && !objeto[3].equals("")){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(helper.getImovelId() != null && !helper.getImovelId().equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel(helper.getImovelId());
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
												
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							System.out.println("=========================================================");
							System.out.println("INICIO DE PAGAMENTO NAO CLASSIFICADOS");
							System.out.println("========================================================\n");
							System.out.println("Valor total: "+(BigDecimal)objeto[5]);
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								
								System.out.println("Valor Categoria: "+valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
							
							System.out.println("=============================================================");
							
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
				
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
								
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
				}
					
				
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
					
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoDebitoCobrarResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoDebCobrar((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoDebCobrar((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoHistoricoSemCorrespondente((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoHistoricoSemCorrespondente(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
								
				}
				
				
				//Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
				}
				
				colecaoAgrupada = quebra;
				
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	
	public Collection agruparDadosColecaoResumoreceita(Collection colecaoResumo){
		Collection retorno = null;
		
		ResumoReceitaHelper resumoReceitaHelper = null;
		ResumoReceitaHelper resumoReceitaAtual = null;
		retorno = new ArrayList();
		
		if(!colecaoResumo.isEmpty()){
			BigDecimal valorTotal = BigDecimal.ZERO;
			
			Iterator iteratorResumo = colecaoResumo.iterator();
			
	  		while(iteratorResumo.hasNext()) {
	  			
	  			resumoReceitaAtual = (ResumoReceitaHelper)iteratorResumo.next();
	  			
	  			if(resumoReceitaHelper != null 
	  			   && resumoReceitaHelper.getArrecadadorId().equals(resumoReceitaAtual.getArrecadadorId()) 
	  			   && resumoReceitaHelper.getBancoId().equals(resumoReceitaAtual.getBancoId())
	  			   && resumoReceitaHelper.getGerenciaRegionalId().equals(resumoReceitaAtual.getGerenciaRegionalId())
	  			   && resumoReceitaHelper.getUnidadeNegocioId().equals(resumoReceitaAtual.getUnidadeNegocioId())
	  			   && resumoReceitaHelper.getLocalidadeId().equals(resumoReceitaAtual.getLocalidadeId())
	  			   && resumoReceitaHelper.getDataRealizacao().equals(resumoReceitaAtual.getDataRealizacao())){
	  				
	  				if(valorTotal.equals(BigDecimal.ZERO)){
	  					valorTotal = valorTotal.add(resumoReceitaHelper.retornaValorTotal());
	  				}else{
	  					valorTotal = valorTotal.add(valorTotal);
	  				}
	  				resumoReceitaHelper.setValorTotal(valorTotal);
	  				
	  				//retorno.add(resumoReceitaHelper);
	  					  				
	  			}else{
	  				if(resumoReceitaHelper == null){
	  					resumoReceitaHelper = resumoReceitaAtual;
	  				}
	  				
	  				valorTotal = valorTotal.add(resumoReceitaHelper.retornaValorTotal());
	  					  				
	  				resumoReceitaHelper.setValorTotal(valorTotal);
	  				
					retorno.add(resumoReceitaHelper);
					
					valorTotal = BigDecimal.ZERO;
	  				
	  				if(iteratorResumo.hasNext()){
	  					resumoReceitaHelper = (ResumoReceitaHelper)iteratorResumo.next();
	  					if(!iteratorResumo.hasNext()){
	  		  				valorTotal = valorTotal.add(resumoReceitaHelper.retornaValorTotal());
	  						resumoReceitaHelper.setValorTotal(valorTotal);
	  		  				
	  	  					retorno.add(resumoReceitaHelper);
	  					}
	  				}
	  			}
			}
		}
		
		return retorno;
	}
 
	
	public Collection pesquisarResumoReceitaAgrupadoPorBanco(ResumoReceitaHelper resumo)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoReceitaAgrupadoPorBanco(resumo);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceita receita = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					receita = new ResumoReceita();
					
					if(objeto[0] != null){
						Banco banco = new Banco();
						FiltroBanco filtroBanco = new FiltroBanco();
						filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, (Integer)objeto[0]));
						Collection colecao = getControladorUtil().pesquisar(filtroBanco, Banco.class.getName());
						banco = (Banco)Util.retonarObjetoDeColecao(colecao);
						receita.setBanco(banco);
					}
					if(objeto[1] != null){
						ContaBancaria contaBancaria = new ContaBancaria();
						contaBancaria.setNumeroConta((String)objeto[1]);
						receita.setContaBancaria(contaBancaria);
					}
					if(objeto[2] != null){
						ContaContabil contaContabil = new ContaContabil();
						contaContabil.setNumeroConta((String)objeto[2]);
						receita.setContaContabil(contaContabil);
					}
					if(objeto[3] != null){
						receita.setValorReceita((BigDecimal)objeto[3]);
					}
					
					colecaoRetorno.add(receita);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoRetorno;
		
	}
	
	
	public Collection pesquisarResumoReceitaRelatorioAnalitico(ResumoReceitaHelper resumo)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoReceitaRelatorioAnalitico(resumo);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceita receita = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
				objeto = (Object[]) iteratorObjetos.next();
				receita = new ResumoReceita();
				
				if(objeto[0] != null){
					Banco banco = null;
					FiltroBanco filtroBanco = new FiltroBanco();
					filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, (Integer)objeto[0]));
					Collection colecao = getControladorUtil().pesquisar(filtroBanco, Banco.class.getName());
					banco = (Banco)Util.retonarObjetoDeColecao(colecao);
					receita.setBanco(banco);
				}
				if(objeto[1] != null){
					ContaBancaria contaBancaria = new ContaBancaria();
					contaBancaria.setNumeroConta((String)objeto[1]);
					receita.setContaBancaria(contaBancaria);
				}
				if(objeto[2] != null){
					Arrecadador arrecadador = null;
					FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID, (Integer)objeto[2]));
					Collection colecaoArrecadador = getControladorUtil().pesquisar(filtroArrecadador, Arrecadador.class.getName());
					arrecadador = (Arrecadador)Util.retonarObjetoDeColecao(colecaoArrecadador);
					receita.setArrecadador(arrecadador);
				}
				if(objeto[3] != null){
					receita.setDataRealizada((Date)objeto[3]);
				}
				if(objeto[4] != null){
					ContaContabil contaContabil = new ContaContabil();
					contaContabil.setNumeroConta((String)objeto[4]);
					receita.setContaContabil(contaContabil);
				}
				if(objeto[5] != null){
					receita.setValorReceita((BigDecimal)objeto[5]);
				}
				
				colecaoRetorno.add(receita);
							
			}

			}
			
			
	} catch (ErroRepositorioException e) {
		
		e.printStackTrace();
	}
	return colecaoRetorno;
		
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		

		List<ResumoReceitaHelper> quebra = 
		            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarOutrasReceitasHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaOutrasReceitas((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}

					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
					//dividir valores por categoria
					if(objeto[5] != null /*&& objeto[3] != null*/){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								
								resumoHelper = new ResumoReceitaHelper(helper);
								if ( objeto[9] != null ){
									resumoHelper.setContaContabil((Integer)objeto[9]);
								}
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaOutrasReceitas(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
							
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
								
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
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
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		//------------------------------------------
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDevolucaoAvisoBancarioResumo(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					
//					ResumoReceitaHelper resumoHelper = null;
					
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null && !objeto[3].equals("")){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(helper.getImovelId() != null && !helper.getImovelId().equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel(helper.getImovelId());
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
												
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
				
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
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
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		//------------------------------------------
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDevolucaoAvisoBancarioHistoricoResumo(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					
//					ResumoReceitaHelper resumoHelper = null;
					
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null && !objeto[3].equals("")){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(helper.getImovelId() != null && !helper.getImovelId().equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel(helper.getImovelId());
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
												
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
				
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Rodrigo Cabral
     * @date: 16/03/2011
     */
    public Map
            consultarSomatorioValorContasBaixadasContabilmenteFaixa(
                Integer referenciaInicio, Integer referenciaFinal,Integer faixa,Short periodicidade)throws ControladorException{
        
        Collection colecaoSomatorioValorContasBaixadasContabilmente = null;
        
        Map retorno = new HashMap();
        
        try {
            
            if (faixa.equals(ConstantesSistema.FAIXA_1)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
                        referenciaInicio, referenciaFinal, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_2)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
                        referenciaInicio, referenciaFinal, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_3)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
                        referenciaInicio, referenciaFinal, periodicidade);
                
            }
            
            BigDecimal valorAcumulado = BigDecimal.ZERO;
            valorAcumulado = valorAcumulado.setScale(2, BigDecimal.ROUND_HALF_DOWN);
            
            if (colecaoSomatorioValorContasBaixadasContabilmente != null 
                    && !colecaoSomatorioValorContasBaixadasContabilmente.isEmpty()) {

                Iterator iteratorColecaoSomatorioValorContasBaixadasContabilmente = colecaoSomatorioValorContasBaixadasContabilmente.iterator();
               
                while (iteratorColecaoSomatorioValorContasBaixadasContabilmente.hasNext()) {

                	Object valorObject = iteratorColecaoSomatorioValorContasBaixadasContabilmente.next();
                	
                	if (valorObject != null){
                		 BigDecimal valor = (BigDecimal) valorObject;
                		 valorAcumulado = valorAcumulado.add(valor);
                	}
                	
                }   
               
            }
            
        retorno.put("somatorio",valorAcumulado);
       
        return retorno;
        
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
      }

    }

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
	public void removerDocumentosAReceberFaixaResumo(int anoMesReferenciaFaturamento, 
		Integer idLocalidade, Session session) throws ControladorException {
		
		try {
			
			repositorioFinanceiro.removerDocumentosAReceberFaixaResumo(anoMesReferenciaFaturamento, 
			idLocalidade, session);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	public Collection<RelatorioParametrosContabeisContasAReceberBean>  pesquisarDadosRelatorioParametrosContabeisContasAReceber(String referenciaContabil) throws ControladorException{
	
		Collection pesquisaDados = new ArrayList();
		Integer anoMes = null;
		
		// Verificamos se o ano mes de referencia foi informado
		if ( referenciaContabil != null && !referenciaContabil.equals( "" )){
			anoMes = Integer.parseInt( Util.formatarMesAnoParaAnoMesSemBarra( referenciaContabil ) );
		}
		
		Collection<RelatorioParametrosContabeisContasAReceberBean> colRetorno = new ArrayList();		
		
		try {
			pesquisaDados = repositorioFinanceiro.pesquisarDadosRelatorioParametrosContabeisContasAReceber( anoMes );			
			
			// Montamos os dados
			Iterator iter = pesquisaDados.iterator();
			
			while(iter.hasNext()){
				Object[] objetos = (Object[]) iter.next();
				
				RelatorioParametrosContabeisContasAReceberBean bean = new RelatorioParametrosContabeisContasAReceberBean(
						(String) objetos[0], // Descricao do tipo de lancamento
						(String) objetos[1], // Descricao do Item de lancamento
						(String) objetos[2], // Descricao da categoria
						(String) objetos[3], // Numero da conta 
						(BigDecimal) objetos[4]  // valor da conta				
				);
                
				colRetorno.add( bean );
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		return colRetorno;		
		
	}
	
}
