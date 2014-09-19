package gcom.gerencial.cadastro;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.HistogramaAguaEconomia;
import gcom.faturamento.HistogramaAguaLigacao;
import gcom.faturamento.HistogramaEsgotoEconomia;
import gcom.faturamento.HistogramaEsgotoLigacao;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaCategoria;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaEconomiaHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaEconomiaSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaLigacaoHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaLigacaoSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoEconomiaHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoEconomiaSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoLigacaoHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoLigacaoSemQuadraHelper;
import gcom.gerencial.cadastro.bean.ResumoColetaEsgotoHelper;
import gcom.gerencial.cadastro.bean.ResumoColetaEsgotoPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoConsumoAguaHelper;
import gcom.gerencial.cadastro.bean.ResumoConsumoAguaPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoLigacaoEconomiaConsultarHelper;
import gcom.gerencial.cadastro.bean.ResumoLigacaoEconomiaHelper;
import gcom.gerencial.cadastro.bean.ResumoLigacaoEconomiaPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoLigacaoEconomiaRegiaoHelper;
import gcom.gerencial.cadastro.bean.ResumoMetasHelper;
import gcom.gerencial.cadastro.bean.ResumoParcelamentoHelper;
import gcom.gerencial.cadastro.bean.ResumoParcelamentoPorAnoHelper;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cobranca.UnResumoParcelamento;
import gcom.gerencial.cobranca.UnResumoParcelamentoPorAno;
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.micromedicao.GRota;
import gcom.gerencial.micromedicao.UnResumoColetaEsgoto;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
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
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public class ControladorGerencialCadastroSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;

	private IRepositorioGerencialCadastro repositorioGerencialCadastro = null;

	//private IRepositorioGerencialCobranca repositorioGerencialCobranca = null;
	 
	private IRepositorioImovel repositorioImovel = null; 

	// private IRepositorioUtil repositorioUtil = null;

	SessionContext sessionContext;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		// repositorioUtil = RepositorioUtilHBM.getInstancia();
		
		repositorioGerencialCadastro = RepositorioGerencialCadastroHBM.getInstancia();
		
		//repositorioGerencialCobranca = RepositorioGerencialCobrancaHBM.getInstancia();
		
		repositorioImovel = RepositorioImovelHBM.getInstancia();
	}

	/**
	 * Retorna a interface remota de ControladorImovel
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	private ControladorImovelLocal getControladorImovel() {
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

	/**
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
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
	 * < <Descri��o do m�todo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a inst�ncia do ServiceLocator.

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
	 * M�todo que gera o resumo dos histogramas de �gua e esgoto
	 * 
	 * [UC0566] - Gerar Histograma de �gua e Esgoto
	 * 
	 * @author Bruno Barros
	 * @date 09/05/2007
	 * 
	 */
	public void gerarResumoHistograma(int anoMesReferencia, int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

         
		//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
   		//this.repositorioGerencialCadastro.excluirResumoGerencial(( getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), "micromedicao.un_resumo_coleta_esgoto", "rece_amreferencia", idSetor );				
		
		
		try {
			System.out.println("*************************************");
			System.out.println("INICIO DO HISTOGRAMA SETOR "+idSetor);
			System.out.println("*************************************");
			
			//[SB1000] ? Gerar Histograma para Im?s Faturados
			//1.	O sistema seleciona as contas do m고de faturamento corrente e gera o histograma de ᧵a por liga磯, o histograma de ᧵a por economia, o histograma de esgoto por liga磯 e o histograma de esgoto por economia acumulando os valores das liga絥s e das economias agrupados por:

			List histogramaAguaLigacao = this.repositorioGerencialCadastro
					.getContasHistograma(idSetor);
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		repositorioGerencialCadastro.excluirResumo( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				HistogramaAguaEconomia.class.getName(), 
	   				"anoMesReferencia", 
	   				idSetor,
	   				true );

	   		repositorioGerencialCadastro.excluirResumo( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				HistogramaAguaLigacao.class.getName(), 
	   				"anoMesReferencia", 
	   				idSetor,
	   				true );
	   		
	   		repositorioGerencialCadastro.excluirResumo( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				HistogramaEsgotoEconomia.class.getName(), 
	   				"referencia", 
	   				idSetor,
	   				true );			
	   		
	   		repositorioGerencialCadastro.excluirResumo( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				HistogramaEsgotoLigacao.class.getName(), 
	   				"referencia", 
	   				idSetor,
	   				true );
	   		
			List<HistogramaAguaLigacaoHelper> listaSimplificadaAguaLigacao = new ArrayList<HistogramaAguaLigacaoHelper>();
			List<HistogramaAguaEconomiaHelper> listaSimplificadaAguaEconomia = new ArrayList<HistogramaAguaEconomiaHelper>();
			List<HistogramaEsgotoLigacaoHelper> listaSimplificadaEsgotoLigacao = new ArrayList<HistogramaEsgotoLigacaoHelper>();
			List<HistogramaEsgotoEconomiaHelper> listaSimplificadaEsgotoEconomia = new ArrayList<HistogramaEsgotoEconomiaHelper>();

			for (int i = 0; i < histogramaAguaLigacao.size(); i++) {
				Object obj = histogramaAguaLigacao.get(i);
				
				if ( i % 100 == 0 || i == 0 ){
					System.out.println("PROCESSANDO CONTA " + (i+1) + " DE " + histogramaAguaLigacao.size() );
				}

				if (obj instanceof Object[]) {

					HistogramaAguaLigacaoHelper histogramaAguaLigacaoHelper;
					HistogramaAguaEconomiaHelper histogramaAguaEconomiaHelper;
					HistogramaEsgotoLigacaoHelper histogramaEsgotoLigacaoHelper;
					HistogramaEsgotoEconomiaHelper histogramaEsgotoEconomiaHelper;

					Object[] retorno = (Object[]) obj;

					Integer idConta = (Integer) retorno[0];
					Integer idImovel = (Integer) retorno[12];

					// Variaveis para preenchimento dos Helper`s
					Integer idGerenciaRegional = (Integer) retorno[1];
					Integer idUnidadeNegocio = (Integer) retorno[2];
					Integer idElo = (Integer) retorno[3];
					Integer idLocalidade = (Integer) retorno[4];
					Integer idSetorCormecial = (Integer) retorno[5];
					Integer codigoSetorComercial = (Integer) retorno[6];
					Integer idQuadra = (Integer) retorno[7];
					Integer numeroQuadra = (Integer) retorno[8];				

					// Verificamos se o imovel possue categoria mista
					//Integer idLigacaoMista = (this.repositorioGerencialCadastro
					//		.pesquisaQuantidadeCategorias(idConta) == 1 ? 2 : 1);
					Integer idConsumoTarifa = (Integer) retorno[9];
					Integer idPerfilImovel = (Integer) retorno[10];					
					
					// Pesquisamos a esfera de poder
					Integer idEsferaPoder = this.repositorioGerencialCadastro
							.pesquisarEsferaPoderClienteResponsavelImovel(idImovel);

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (idEsferaPoder.equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							idEsferaPoder = clienteTemp.getClienteTipo()
									.getEsferaPoder().getId();
						}
					}

					Integer idSituacaoAgua = (Integer) retorno[11];
					Integer idSituacaoEsgoto = (Integer) retorno[13];
					Float percentualEsgoto = ( (BigDecimal) retorno[14] ).floatValue();
					
					// Verificamos o consumo real
					Integer idConsumoReal = this.repositorioGerencialCadastro
							.verificarConsumoReal(idImovel);
					// Verificamos a existencia de hidr�metro
					Integer idHidrometro = this.repositorioGerencialCadastro
							.verificarExistenciaHidrometro(idImovel);
					// Verificamos a existencia de poco no imovel
					Integer idPoco = this.repositorioGerencialCadastro
							.verificarExistenciaPoco(idImovel);
					// Verificamos a existencia de volume fixo de agua
					Integer idVolumeFixoAgua = this.repositorioGerencialCadastro
							.verificarExistenciaVolumeFixoAgua(idImovel);
					Integer idVolumeFixoEsgoto = this.repositorioGerencialCadastro
							.verificarExistenciaVolumeFixoEsgoto(idImovel);
					// Valores a serem calculados
					Integer quantidadeEconomiaLigacaoAgua = 0;
					Integer quantidadeEconomiaLigacaoEsgoto = 0;
					Integer quantidadeEconomiaAgua = 0;
					Integer quantidadeEconomiaEsgoto = 0;
					Float valorFaturadoLigacaoAgua = 0f;
					Integer volumeFaturadoLigacaoAgua = 0;
					Float valorFaturadoEconomiaAgua = 0f;
					Integer volumeFaturadoEconomiaAgua = 0;
					Float valorFaturadoLigacaoEsgoto = 0f;
					Integer volumeFaturadoLigacaoEsgoto = 0;
					Float valorFaturadoEconomiaEsgoto = 0f;
					Integer volumeFaturadoEconomiaEsgoto = 0;
					Integer qtdEconomias = 0;
					Integer quantidadeConsumoAguaLigacao = 0;
                    Integer quantidadeConsumoAguaEconomia = 0;
					Integer quantidadeConsumoEsgotoLigacao = 0;
                    Integer quantidadeConsumoEsgotoEconomia = 0;					
                    Categoria contaCategoriaPrincipal = null;

                    //2.	O sistema deverᠡcumular os valores das liga絥s e das economias 
                    //do histograma de ᧵a por liga磯 e por economia e do histograma de esgoto 
                    //por liga磯 e por economia conforme a seguir:
					
                    // Criamos um la篠com todas as categorias da conta
					FiltroContaCategoria filtro = new FiltroContaCategoria();
					filtro.adicionarCaminhoParaCarregamentoEntidade("comp_id");
					filtro
							.adicionarCaminhoParaCarregamentoEntidade("comp_id.categoria");
					filtro
							.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria");					
					filtro
							.adicionarCaminhoParaCarregamentoEntidade("comp_id.categoria.categoriaTipo");
					filtro.adicionarParametro(new ParametroSimples(
							FiltroContaCategoria.CONTA_ID, idConta));
                    
					Collection colContaCategoria = getControladorUtil()
							.pesquisar(filtro, ContaCategoria.class.getName());
                    
                    Iterator iteContaCategoria = colContaCategoria.iterator();					
                    
					// Identificamos a categoria principal
                    contaCategoriaPrincipal = this.repositorioGerencialCadastro.obterPrincipalCategoriaConta( idConta );
                    
                    FiltroCategoria filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(new ParametroSimples(
							FiltroCategoria.CODIGO, contaCategoriaPrincipal.getId()));
					filtroCategoria.adicionarCaminhoParaCarregamentoEntidade("categoriaTipo");
					Collection colCategoria = getControladorUtil().pesquisar(
							filtroCategoria, Categoria.class.getName());
					Categoria categoriaExistente = (Categoria) colCategoria.iterator().next();

                    ////[SB0001 ? Preparar Dados do Histograma para Uma Categoria e Uma Economia].
                    ContaCategoria contaCategoriaAnterior = null;
                    // Usado para verificar se Categoria Mista
                    Integer idLigacaoMista = 2;
                    Integer qtdLigacoesNasEconomias = 0;
                    
					while (iteContaCategoria.hasNext()) {                        
						// Verificamos se quantidade de economias dessa conta 鍊						// maior do que a anterior
						ContaCategoria contaCategoria = (ContaCategoria) iteContaCategoria
								.next();

						// DADOS EXCLUSIVOS DAS TABELAS DE AGUA
						/**
						 * **** DADOS PARA A TABELA HISTOGRAMA AGUA LIGACAO
						 * *****
						 */

						if (contaCategoria.getConsumoAgua() != null) {
							quantidadeConsumoAguaLigacao += contaCategoria
									.getConsumoAgua();
						}
						quantidadeEconomiaLigacaoAgua += contaCategoria
								.getQuantidadeEconomia();

						if (contaCategoria.getValorAgua() != null) {
							valorFaturadoLigacaoAgua += contaCategoria
									.getValorAgua().floatValue();
						}

						// Obtemos o consumo faturado de agua
						// Se o consumo de agua for menor do que o minimo * as
						// economias
						// Devemos pegar o m�nimo
						if (contaCategoria.getConsumoAgua() != null
								&& contaCategoria.getConsumoMinimoAgua() != null) {
							if (contaCategoria.getConsumoAgua() < (contaCategoria
									.getConsumoMinimoAgua() )) {
								volumeFaturadoLigacaoAgua += contaCategoria
										.getConsumoMinimoAgua();
							} else {
								volumeFaturadoLigacaoAgua += contaCategoria
										.getConsumoAgua();
							}
						}
						/**
						 * **** FIM DADOS PARA A TABELA HISTOGRAMA AGUA LIGACAO
						 * *****
						 */
						
						// Verifica se Categoria Mista
						if (contaCategoriaAnterior != null &&
								!contaCategoria.getComp_id().getCategoria().getId().equals(
										contaCategoriaAnterior.getComp_id().getCategoria().getId())) {
							idLigacaoMista = 1;
						}
						
						
						/**
						 * **** DADOS PARA A TABELA HISTOGRAMA ESGOTO LIGACAO
						 * *****
						 */
						
						if (contaCategoria.getConsumoEsgoto() != null) {
							quantidadeConsumoEsgotoLigacao += contaCategoria
									.getConsumoEsgoto();
						}						

   						quantidadeEconomiaLigacaoEsgoto += contaCategoria
								.getQuantidadeEconomia();
   						
						if (contaCategoria.getValorEsgoto() != null) {
							valorFaturadoLigacaoEsgoto += contaCategoria
									.getValorEsgoto().floatValue();
						}

						// Obtemos o consumo faturado de agua
						// Se o consumo de agua for menor do que o minimo * as
						// economias
						// Devemos pegar o m���mo
						if (contaCategoria.getConsumoEsgoto() != null
								&& contaCategoria.getConsumoMinimoEsgoto() != null) {
							if (contaCategoria.getConsumoEsgoto() < (contaCategoria
									.getConsumoMinimoEsgoto() )) {
								volumeFaturadoLigacaoEsgoto += contaCategoria
										.getConsumoMinimoEsgoto();
							} else {
								volumeFaturadoLigacaoEsgoto += contaCategoria
										.getConsumoEsgoto();
							}
						}
						/**
						 * **** FIM DADOS PARA A TABELA HISTOGRAMA ESGOTO
						 * LIGACAO *****
						 */
						if ( getControladorUtil().pesquisarParametrosDoSistema().getIndicadorTarifaCategoria() == ConstantesSistema.SIM  ){
							// Verificamos a quantidade de economias da conta faturada
							qtdEconomias = this.repositorioGerencialCadastro
									.pesquisaQuantidadeEconomias(idConta,
											contaCategoria.getComp_id()
													.getCategoria().getId());
						} else {
							// Verificamos a quantidade de economias da conta faturada
							qtdEconomias = this.repositorioGerencialCadastro
									.pesquisaQuantidadeEconomiasPorSubcategoria(idConta,
											contaCategoria.getComp_id().getCategoria().getId(),
											contaCategoria.getComp_id().getSubcategoria().getId());
						}
                        /**
						 * **** DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA
						 * *****
						 */
						quantidadeEconomiaAgua += qtdEconomias;
						
						if (contaCategoria.getValorAgua() != null) {
							valorFaturadoEconomiaAgua += contaCategoria
									.getValorAgua().floatValue();
									// qtdEconomias;
						}					

						// Obtemos o percentual de esgoto
						// Alterado Bruno Barros / 23/11/2007
//						percentualEsgoto = this.repositorioGerencialCadastro
//								.percentualColetaEsgoto(idImovel);
						
						// Obtemos o consumo faturado de agua
						// Se o consumo de agua for menor do que o minimo * as
						// economias
						// Devemos pegar o m�nimo
						if (contaCategoria.getConsumoAgua() != null
								&& contaCategoria.getConsumoMinimoAgua() != null) {
							if (contaCategoria.getConsumoAgua() < (contaCategoria
									.getConsumoMinimoAgua() )) {
								volumeFaturadoEconomiaAgua += contaCategoria
										.getConsumoMinimoAgua();
							} else {
								volumeFaturadoEconomiaAgua += contaCategoria
										.getConsumoAgua();
										// qtdEconomias;
							}
						}
						
						if ( contaCategoria.getConsumoAgua() != null && contaCategoria.getConsumoAgua() != 0 ){
							quantidadeConsumoAguaEconomia += contaCategoria.getConsumoAgua();
						}						
						
//						if ( contaCategoria.getComp_id().getCategoria().getId().equals(
//							 contaCategoriaPrincipal.getId() ) && contaCategoriaAnterior == null){
//							qtdLigacoesNasEconomias = 1;	
//						}				
						if ( contaCategoriaAnterior == null) {
							qtdLigacoesNasEconomias = 1;
						}
						
						/**
						 * **** DADOS PARA A TABELA HISTOGRAMA ESGOTO ECONOMIA
						 * *****
						 */
						quantidadeEconomiaEsgoto += qtdEconomias;
						
						if (contaCategoria.getValorEsgoto() != null) {
							valorFaturadoEconomiaEsgoto += contaCategoria
									.getValorEsgoto().floatValue();
									// qtdEconomias;
						}

						// Obtemos o consumo faturado de agua
						// Se o consumo de agua for menor do que o minimo * as
						// economias
						// Devemos pegar o m�nimo
						if (contaCategoria.getConsumoEsgoto() != null
								&& contaCategoria.getConsumoMinimoEsgoto() != null) {
							if (contaCategoria.getConsumoEsgoto() < (contaCategoria
									.getConsumoMinimoEsgoto() )) {
								volumeFaturadoEconomiaEsgoto += contaCategoria
										.getConsumoMinimoEsgoto();
							} else {
								volumeFaturadoEconomiaEsgoto += contaCategoria
										.getConsumoEsgoto();
										// qtdEconomias;
							}
						}
						
	
						if ( contaCategoria.getConsumoEsgoto() != null && contaCategoria.getConsumoEsgoto() != 0 ){
							quantidadeConsumoEsgotoEconomia += contaCategoria.getConsumoEsgoto();
						}
						
						contaCategoriaAnterior = contaCategoria;
					}
					
					
					//********************************************************************************
					//********************************************************************************
					if ( quantidadeConsumoAguaEconomia != null && !quantidadeConsumoAguaEconomia.equals(0) ){
						quantidadeConsumoAguaEconomia = ( quantidadeConsumoAguaEconomia / quantidadeEconomiaAgua );
					}
					
					// Criamos um helper para os histograma de agua por economia
					histogramaAguaEconomiaHelper = new HistogramaAguaEconomiaHelper(
							idGerenciaRegional, idUnidadeNegocio, idElo,
							idLocalidade, idSetorCormecial,
							codigoSetorComercial, idQuadra, numeroQuadra,
							categoriaExistente.getCategoriaTipo().getId(),
							contaCategoriaPrincipal.getId(), 
							idConsumoTarifa, idPerfilImovel,
							idEsferaPoder, idSituacaoAgua, idConsumoReal,
							idHidrometro, idPoco, idVolumeFixoAgua,
							quantidadeConsumoAguaEconomia, qtdLigacoesNasEconomias);
					

					// Agrupamos o histograma agua por economia
					if (!valorFaturadoEconomiaAgua.equals(0f)) {
						if (listaSimplificadaAguaEconomia
								.contains(histogramaAguaEconomiaHelper)) {
							int posicao = listaSimplificadaAguaEconomia
									.indexOf(histogramaAguaEconomiaHelper);

							HistogramaAguaEconomiaHelper jaCadastrado = (HistogramaAguaEconomiaHelper) listaSimplificadaAguaEconomia
									.get(posicao);

							jaCadastrado.setQuantidadeEconomia(jaCadastrado
									.getQuantidadeEconomia()
									+ quantidadeEconomiaAgua);

							jaCadastrado.setValorFaturadoEconomia(jaCadastrado
									.getValorFaturadoEconomia()
									+ valorFaturadoEconomiaAgua);

							jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado
									.getVolumeFaturadoEconomia()
									+ volumeFaturadoEconomiaAgua);
							
							jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() 
									+ qtdLigacoesNasEconomias);
						} else {
							histogramaAguaEconomiaHelper
									.setQuantidadeEconomia(quantidadeEconomiaAgua);

							histogramaAguaEconomiaHelper
									.setValorFaturadoEconomia(valorFaturadoEconomiaAgua);

							histogramaAguaEconomiaHelper
									.setVolumeFaturadoEconomia(volumeFaturadoEconomiaAgua);

							listaSimplificadaAguaEconomia
									.add(histogramaAguaEconomiaHelper);
						}				
					}
				    /**
				     * **** FIM DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA
				     * *****
				     */
					
					if ( quantidadeConsumoEsgotoEconomia != null && !quantidadeConsumoEsgotoEconomia.equals(0) ){
						quantidadeConsumoEsgotoEconomia += (quantidadeConsumoEsgotoEconomia / quantidadeEconomiaEsgoto);
					}
					
					// Criamos um helper para os histograma de Esgoto por
					// Economia
					histogramaEsgotoEconomiaHelper = new HistogramaEsgotoEconomiaHelper(
							idGerenciaRegional, idUnidadeNegocio, idElo,
							idLocalidade, idSetorCormecial,
							codigoSetorComercial, idQuadra, numeroQuadra,
							categoriaExistente.getCategoriaTipo().getId(),
							contaCategoriaPrincipal.getId(), idConsumoTarifa, idPerfilImovel,
							idEsferaPoder, idSituacaoEsgoto, idConsumoReal,
							idHidrometro, idPoco, idVolumeFixoEsgoto,
							percentualEsgoto, quantidadeConsumoEsgotoEconomia, qtdLigacoesNasEconomias);
					

					// Agrupamos o histograma esgoto por economia
					if (!valorFaturadoEconomiaEsgoto.equals(0f)) {
						if (listaSimplificadaEsgotoEconomia
								.contains(histogramaEsgotoEconomiaHelper)) {
							int posicao = listaSimplificadaEsgotoEconomia
									.indexOf(histogramaEsgotoEconomiaHelper);

							HistogramaEsgotoEconomiaHelper jaCadastrado = (HistogramaEsgotoEconomiaHelper) listaSimplificadaEsgotoEconomia
									.get(posicao);

							jaCadastrado.setQuantidadeEconomia(jaCadastrado
									.getQuantidadeEconomia()
									+ quantidadeEconomiaEsgoto);

							jaCadastrado.setValorFaturadoEconomia(jaCadastrado
									.getValorFaturadoEconomia()
									+ valorFaturadoEconomiaEsgoto);

							jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado
									.getVolumeFaturadoEconomia()
									+ volumeFaturadoEconomiaEsgoto);
							
							jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() 
									+ qtdLigacoesNasEconomias);

						} else {
							histogramaEsgotoEconomiaHelper
									.setQuantidadeEconomia(quantidadeEconomiaEsgoto);

							histogramaEsgotoEconomiaHelper
									.setValorFaturadoEconomia(valorFaturadoEconomiaEsgoto);

							histogramaEsgotoEconomiaHelper
									.setVolumeFaturadoEconomia(volumeFaturadoEconomiaEsgoto);

							listaSimplificadaEsgotoEconomia
									.add(histogramaEsgotoEconomiaHelper);
						}
					/**
					 * **** FIM DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA
					 * *****
					 */
					}
					
					
					
					
					
					//[SB0003 ? Preparar Dados do Histograma para Mais de Uma Categoria].
					//FiltroCategoria filtroCategoria = new FiltroCategoria();
					
					//filtroCategoria.adicionarParametro(new ParametroSimples(
					//		FiltroCategoria.CODIGO, contaCategoriaPrincipal.getId()));
					//filtroCategoria.adicionarCaminhoParaCarregamentoEntidade("categoriaTipo");
					
					//Collection colCategoria = getControladorUtil().pesquisar(
					//		filtroCategoria, Categoria.class.getName());
					
					//Categoria categoriaExistente = (Categoria) colCategoria.iterator().next();
						
					// Criamos um helper para os histograma de agua por liga��o
					histogramaAguaLigacaoHelper = new HistogramaAguaLigacaoHelper(
							idGerenciaRegional, idUnidadeNegocio, idElo,
							idLocalidade, idSetorCormecial,
							codigoSetorComercial, idQuadra, numeroQuadra,
							categoriaExistente.getCategoriaTipo().getId(),
							contaCategoriaPrincipal.getId(), 
                            idLigacaoMista, idConsumoTarifa,
							idPerfilImovel, idEsferaPoder, idSituacaoAgua,
							idConsumoReal, idHidrometro, idPoco,
							idVolumeFixoAgua, quantidadeConsumoAguaLigacao);
					
					// Agrupamos o histograma agua ligacao
					if (!valorFaturadoLigacaoAgua.equals(0f)) {
						if (listaSimplificadaAguaLigacao
								.contains(histogramaAguaLigacaoHelper)) {
							int posicao = listaSimplificadaAguaLigacao
									.indexOf(histogramaAguaLigacaoHelper);

							HistogramaAguaLigacaoHelper jaCadastrado = (HistogramaAguaLigacaoHelper) listaSimplificadaAguaLigacao
									.get(posicao);

							jaCadastrado.setQuantidadeLigacao(jaCadastrado
									.getQuantidadeLigacao()
									+ 1);

							jaCadastrado
									.setQuantidadeEconomiaLigacao(jaCadastrado
											.getQuantidadeEconomiaLigacao()
											+ quantidadeEconomiaLigacaoAgua);

							jaCadastrado.setValorFaturadoLigacao(jaCadastrado
									.getValorFaturadoLigacao()
									+ valorFaturadoLigacaoAgua);

							jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado
									.getVolumeFaturadoLigacao()
									+ volumeFaturadoLigacaoAgua);
						} else {
							histogramaAguaLigacaoHelper
									.setQuantidadeLigacao(1);

							histogramaAguaLigacaoHelper
									.setQuantidadeEconomiaLigacao(quantidadeEconomiaLigacaoAgua);

							histogramaAguaLigacaoHelper
									.setValorFaturadoLigacao(valorFaturadoLigacaoAgua);

							histogramaAguaLigacaoHelper
									.setVolumeFaturadoLigacao(volumeFaturadoLigacaoAgua);

							listaSimplificadaAguaLigacao
									.add(histogramaAguaLigacaoHelper);
						}
					}
					
					// Criamos um helper para os histograma de Esgoto por
					// liga��o
					histogramaEsgotoLigacaoHelper = new HistogramaEsgotoLigacaoHelper(
							idGerenciaRegional, idUnidadeNegocio, idElo,
							idLocalidade, idSetorCormecial,
							codigoSetorComercial, idQuadra, numeroQuadra,
							categoriaExistente.getCategoriaTipo().getId(),
							contaCategoriaPrincipal.getId(), 
                            idLigacaoMista, idConsumoTarifa,
							idPerfilImovel, idEsferaPoder, idSituacaoEsgoto,
							idConsumoReal, idHidrometro, idPoco,
							idVolumeFixoEsgoto, percentualEsgoto,
							quantidadeConsumoEsgotoLigacao);

					// Agrupamos o histograma esgoto por ligacao
					if (!valorFaturadoLigacaoEsgoto.equals(0f)) {
						if (listaSimplificadaEsgotoLigacao
								.contains(histogramaEsgotoLigacaoHelper)) {
							int posicao = listaSimplificadaEsgotoLigacao
									.indexOf(histogramaEsgotoLigacaoHelper);

							HistogramaEsgotoLigacaoHelper jaCadastrado = (HistogramaEsgotoLigacaoHelper) listaSimplificadaEsgotoLigacao
									.get(posicao);

							jaCadastrado.setQuantidadeLigacao(jaCadastrado
									.getQuantidadeLigacao() + 1);

							jaCadastrado
									.setQuantidadeEconomiaLigacao(jaCadastrado
											.getQuantidadeEconomiaLigacao()
											+ quantidadeEconomiaLigacaoEsgoto);

							jaCadastrado.setValorFaturadoLigacao(jaCadastrado
									.getValorFaturadoLigacao()
									+ valorFaturadoLigacaoEsgoto);

							jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado
									.getVolumeFaturadoLigacao()
									+ volumeFaturadoLigacaoEsgoto);
						} else {
							histogramaEsgotoLigacaoHelper
									.setQuantidadeLigacao(1);

							histogramaEsgotoLigacaoHelper
									.setQuantidadeEconomiaLigacao(quantidadeEconomiaLigacaoEsgoto);

							histogramaEsgotoLigacaoHelper
									.setValorFaturadoLigacao(valorFaturadoLigacaoEsgoto);

							histogramaEsgotoLigacaoHelper
									.setVolumeFaturadoLigacao(volumeFaturadoLigacaoEsgoto);

							listaSimplificadaEsgotoLigacao
									.add(histogramaEsgotoLigacaoHelper);
						}
					}
									
				}
			}

			for (int j = 0; j < listaSimplificadaAguaLigacao.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaAguaLigacao(
								anoMesReferencia,
								(HistogramaAguaLigacaoHelper) listaSimplificadaAguaLigacao
										.get(j));
			}

			for (int j = 0; j < listaSimplificadaAguaEconomia.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaAguaEconomima(
								anoMesReferencia,
								(HistogramaAguaEconomiaHelper) listaSimplificadaAguaEconomia
										.get(j));
			}

			for (int j = 0; j < listaSimplificadaEsgotoLigacao.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaEsgotoLigacao(
								anoMesReferencia,
								(HistogramaEsgotoLigacaoHelper) listaSimplificadaEsgotoLigacao
										.get(j));
			}

			for (int j = 0; j < listaSimplificadaEsgotoEconomia.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaEsgotoEconomia(
								anoMesReferencia,
								(HistogramaEsgotoEconomiaHelper) listaSimplificadaEsgotoEconomia
										.get(j));
			}
			
			gerarResumoHistogramaImoveisNaoFaturados( anoMesReferencia, idSetor, idFuncionalidadeIniciada );

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			System.out.println(" ERRO NO SETOR COMERCIAL " + idSetor);

			ex.printStackTrace();			

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * 
	 * @param anoMesReferencia
	 * @param idSetor
	 * @param unidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoHistogramaImoveisNaoFaturados( int anoMesReferencia, int idSetor, int unidadeIniciada ) throws ControladorException {
		try{
			// selecionamos o consumo historico
			List consumoHistoricoHistograma = this.repositorioGerencialCadastro
				.getConsumoHistoricoImoveisNaoFaturados( idSetor );
	
			List<HistogramaAguaLigacaoHelper> listaSimplificadaAguaLigacao = new ArrayList<HistogramaAguaLigacaoHelper>();
			List<HistogramaAguaEconomiaHelper> listaSimplificadaAguaEconomia = new ArrayList<HistogramaAguaEconomiaHelper>();
			List<HistogramaEsgotoLigacaoHelper> listaSimplificadaEsgotoLigacao = new ArrayList<HistogramaEsgotoLigacaoHelper>();
			List<HistogramaEsgotoEconomiaHelper> listaSimplificadaEsgotoEconomia = new ArrayList<HistogramaEsgotoEconomiaHelper>();
			
			for (int i = 0; i < consumoHistoricoHistograma.size(); i++) {
				Object obj = consumoHistoricoHistograma.get(i);

				if (obj instanceof Object[]) {

					HistogramaAguaLigacaoHelper histogramaAguaLigacaoHelper;
					HistogramaAguaEconomiaHelper histogramaAguaEconomiaHelper;
					HistogramaEsgotoLigacaoHelper histogramaEsgotoLigacaoHelper;
					HistogramaEsgotoEconomiaHelper histogramaEsgotoEconomiaHelper;

					Object[] retorno = (Object[]) obj;

					Integer idImovel = (Integer) retorno[11];
					Imovel imovel = new Imovel();
					imovel.setId( idImovel );
					
					// Selecionamos todas as categorias distintas do imovel
					Collection colCategoriaImovel = this.repositorioGerencialCadastro.getCategoriasImovelDistintas( idImovel );
					Iterator iteCategoriaImovel = colCategoriaImovel.iterator();
					Integer consumoMinimoMes = 0;			
					Integer consumoFaturadoMes = 0;
					Integer ligacaoMista = 0;
					
					// Obtemos o consumo m�nimo de cada categoria do imovel
					while ( iteCategoriaImovel.hasNext() ){
						Categoria categoria = ( Categoria ) iteCategoriaImovel.next();						
						categoria.setConsumoMinimo( this.obterConsumoMinimoCategoria( imovel, categoria ) );
						consumoMinimoMes += categoria.getConsumoMinimo();
						ligacaoMista++;
					}
					
					ligacaoMista = ( ligacaoMista == 1 ? 2 : 1 );
					
					// Selecionamos o consumo faturado do mes
					consumoFaturadoMes = (Integer) retorno[15];
					
					Short quantidadeEconomiasImovel = (Short) retorno[13];
					List<Long> consumosImovelporCategoria = new ArrayList<Long>();
					List<Short> quantidadeEconomiasImovelporCategoria = new ArrayList<Short>();
					
					Categoria categoriaPrincipal = null;
					Short quantidadeEconomiasAnterior = 0;
					// Obtemos o consumo do imovel para cada categoria e selecionamos
					// a principal categoria do imovel
					
					iteCategoriaImovel = colCategoriaImovel.iterator();
					
					while( iteCategoriaImovel.hasNext() ){
						Categoria categoria = ( Categoria ) iteCategoriaImovel.next();
						long fator = ( consumoFaturadoMes - consumoMinimoMes ) / quantidadeEconomiasImovel;
						Short quantidadeEconomias = this.repositorioImovel.pesquisarObterQuantidadeEconomias( imovel, categoria );
						long consumoImovelCategoria = categoria.getConsumoMinimo() + fator * quantidadeEconomias;
						consumosImovelporCategoria.add( consumoImovelCategoria );
						quantidadeEconomiasImovelporCategoria.add( quantidadeEconomias );
						
						// Selecionamos a categoria principal
						if ( categoriaPrincipal == null || quantidadeEconomias > quantidadeEconomiasAnterior ){
							categoriaPrincipal = categoria;
						}
						
						quantidadeEconomiasAnterior = quantidadeEconomias;
					}
					
					// Variaveis para preenchimento dos Helper`s
					Integer idGerenciaRegional = (Integer) retorno[0];
					Integer idUnidadeNegocio = (Integer) retorno[1];
					Integer idElo = (Integer) retorno[2];
					Integer idLocalidade = (Integer) retorno[3];
					Integer idSetorCormecial = (Integer) retorno[4];
					Integer codigoSetorComercial = (Integer) retorno[5];
					Integer idQuadra = (Integer) retorno[6];
					Integer numeroQuadra = (Integer) retorno[7];
					
					// Verificamos se o imovel possue categoria mista					
					Integer idConsumoTarifa = (Integer) retorno[8];
					Integer idPerfilImovel = (Integer) retorno[9];
					// Pesquisamos a esfera de poder
					Integer idEsferaPoder = this.repositorioGerencialCadastro
							.pesquisarEsferaPoderClienteResponsavelImovel(idImovel);
                    
                    // Verificamos se a esfera de poder foi encontrada
                    // [FS0002] Verificar existencia de cliente responsavel
                    if (idEsferaPoder.equals(0)) {
                        Cliente clienteTemp = this.getControladorImovel()
                                .consultarClienteUsuarioImovel(imovel);
                        if (clienteTemp != null) {
                            idEsferaPoder = clienteTemp.getClienteTipo()
                                    .getEsferaPoder().getId();
                        }
                    }
                    
					// Obtemos o percentual de esgoto
					Float percentualEsgoto = ( ( retorno[18] ) == null ? 0 : ( ( BigDecimal )retorno[18] ).floatValue() );
					
					Integer idSituacaoAgua = (Integer) retorno[10];
					Integer idSituacaoEsgoto = (Integer) retorno[12];
					Integer idTipoLigacao = (Integer) retorno[14];		
					
					// Verificamos o consumo real
					Integer idConsumoReal = ( ( ( Integer ) retorno[17] ).equals( 1 ) ? 1 : 2 );
					// Verificamos a existencia de hidr�metro
					Integer idHidrometro = this.repositorioGerencialCadastro.verificarExistenciaHidrometro(idImovel);
					// Verificamos a existencia de poco no imovel
					Integer idPoco = ( retorno[16] != null && !( ( Integer ) retorno[16] ).equals( 0 ) ? 1 : 2 );
					Integer idVolumeFixoAgua = this.repositorioGerencialCadastro.verificarExistenciaVolumeFixoAgua(idImovel);
					Integer idVolumeFixoEsgoto = this.repositorioGerencialCadastro.verificarExistenciaVolumeFixoEsgoto(idImovel);

					// Valores a serem calculados
					Integer quantidadeLigacaoAgua = 0;
					Integer quantidadeEconomiaLigacaoAgua = 0;
					Short quantidadeEconomiaLigacaoEsgoto = 0;
					Integer quantidadeEconomiaAgua = 0;
					Integer quantidadeEconomiaEsgoto = 0;
					Integer volumeFaturadoLigacaoAgua = 0;
					Long volumeFaturadoEconomiaAgua = 0l;
					Integer quantidadeLigacaoEsgoto = 0;
					Integer volumeFaturadoLigacaoEsgoto = 0;
					Long volumeFaturadoEconomiaEsgoto = 0l;
					Integer quantidadeConsumoAgua = 0;
					Integer quantidadeConsumoEsgoto = 0;
					Integer index = 0;
					
					iteCategoriaImovel = colCategoriaImovel.iterator();

					while (iteCategoriaImovel.hasNext()) {
						// Verificamos se quantidade de economias dessa conta �
						// maior do que a anterior
						Categoria categoria = (Categoria) iteCategoriaImovel
								.next();

						if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_AGUA ) ){
							/**	***** FIM DADOS PARA A TABELA HISTOGRAMA AGUA LIGACAO ******* **/							
							quantidadeLigacaoAgua++;							
							quantidadeEconomiaLigacaoAgua += ( Short ) quantidadeEconomiasImovelporCategoria.get( index ); 
	
							// Obtemos o consumo faturado de agua
							// Se o consumo de agua for menor do que o minimo * as
							// economias
							// Devemos pegar o m�nimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoLigacaoAgua += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoLigacaoAgua += consumosImovelporCategoria.get( index ).intValue();
							}
                            
                            quantidadeConsumoAgua += consumosImovelporCategoria.get( index ).intValue();
							/**	***** FIM DADOS PARA A TABELA HISTOGRAMA AGUA LIGACAO ******* **/
							/**	**** DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA ****** **/
							quantidadeEconomiaAgua += ( Short ) quantidadeEconomiasImovelporCategoria.get( index );
							
							// Obtemos o consumo faturado de agua
							// Se o consumo de agua for menor do que o minimo * as
							// economias
							// Devemos pegar o m�nimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoEconomiaAgua += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoEconomiaAgua += consumosImovelporCategoria.get( index ); // quantidadeEconomiasImovelporCategoria.get( index );
							}
                            
							/**	**** FIM DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA ****** **/							
						}

						if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_ESGOTO ) ){
							/** ***** DADOS PARA A TABELA HISTOGRAMA ESGOTO LIGACAO ****** **/							
							quantidadeLigacaoEsgoto++;
							
							quantidadeEconomiaLigacaoEsgoto = ( Short ) quantidadeEconomiasImovelporCategoria.get( index );
							
							// Obtemos o consumo faturado de esgoto
							// Se o consumo de esgoto for menor do que o minimo * as
							// economias
							// Devemos pegar o m�nimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoLigacaoEsgoto += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoLigacaoEsgoto += ( ( Long ) consumosImovelporCategoria.get( index ) ).intValue();
							}
							
							quantidadeConsumoEsgoto += consumosImovelporCategoria.get( index ).intValue();
							/** ***** FIM DADOS PARA A TABELA HISTOGRAMA ESGOTO LIGACAO ****** **/
							
							/**	**** DADOS PARA A TABELA HISTOGRAMA ESGOTO ECONOMIA ****** **/
							quantidadeEconomiaEsgoto += ( Short ) quantidadeEconomiasImovelporCategoria.get( index );
							
							// Obtemos o consumo faturado de agua
							// Se o consumo de agua for menor do que o minimo * as
							// economias
							// Devemos pegar o m�nimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoEconomiaEsgoto += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoEconomiaEsgoto += consumosImovelporCategoria.get( index ); // quantidadeEconomiasImovelporCategoria.get( index );
							}
							/**	**** FIM DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA ****** **/							
							
						}
					}
					
					if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_AGUA ) ){
						// Criamos um helper para os histograma de agua por liga��o
						histogramaAguaLigacaoHelper = new HistogramaAguaLigacaoHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								idQuadra, 
								numeroQuadra,
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(), 
								ligacaoMista, 
								idConsumoTarifa,
								idPerfilImovel, 
								idEsferaPoder, 
								idSituacaoAgua,
								idConsumoReal, 
								idHidrometro, 
								idPoco,
								idVolumeFixoAgua, 
								quantidadeConsumoAgua);
						
						if (listaSimplificadaAguaLigacao
								.contains(histogramaAguaLigacaoHelper)) {
							int posicao = listaSimplificadaAguaLigacao
									.indexOf(histogramaAguaLigacaoHelper);

							HistogramaAguaLigacaoHelper jaCadastrado = (HistogramaAguaLigacaoHelper) listaSimplificadaAguaLigacao
									.get(posicao);

							jaCadastrado.setQuantidadeLigacao(jaCadastrado
									.getQuantidadeLigacao()
									+ quantidadeLigacaoAgua);

							jaCadastrado
									.setQuantidadeEconomiaLigacao(jaCadastrado
											.getQuantidadeEconomiaLigacao()
											+ quantidadeEconomiaLigacaoAgua);

							jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado
									.getVolumeFaturadoLigacao()
									+ volumeFaturadoLigacaoAgua);
						} else {
							histogramaAguaLigacaoHelper
									.setQuantidadeLigacao(quantidadeLigacaoAgua);

							histogramaAguaLigacaoHelper
									.setQuantidadeEconomiaLigacao(quantidadeEconomiaLigacaoAgua);
							
							histogramaAguaLigacaoHelper
									.setValorFaturadoLigacao( 0f );

							histogramaAguaLigacaoHelper
									.setVolumeFaturadoLigacao(volumeFaturadoLigacaoAgua);

							listaSimplificadaAguaLigacao
									.add(histogramaAguaLigacaoHelper);
						}						
	
						// Criamos um helper para os histograma de agua por economia
						histogramaAguaEconomiaHelper = new HistogramaAguaEconomiaHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								idQuadra, 
								numeroQuadra,
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(), 
								idConsumoTarifa, 
								idPerfilImovel,
								idEsferaPoder, 
								idSituacaoAgua, 
								idConsumoReal,
								idHidrometro, 
								idPoco, 
								idVolumeFixoAgua,
								quantidadeConsumoAgua,
								1);
						
						if (listaSimplificadaAguaEconomia
								.contains(histogramaAguaEconomiaHelper)) {
							int posicao = listaSimplificadaAguaEconomia
									.indexOf(histogramaAguaEconomiaHelper);

							HistogramaAguaEconomiaHelper jaCadastrado = (HistogramaAguaEconomiaHelper) listaSimplificadaAguaEconomia
									.get(posicao);

							jaCadastrado.setQuantidadeEconomia(jaCadastrado
									.getQuantidadeEconomia()
									+ quantidadeEconomiaAgua);

							jaCadastrado.setVolumeFaturadoEconomia(
									(Integer)jaCadastrado.getVolumeFaturadoEconomia().intValue()
									+ volumeFaturadoEconomiaAgua.intValue());
						} else {
							histogramaAguaEconomiaHelper
									.setQuantidadeEconomia(quantidadeEconomiaAgua);
							
							histogramaAguaEconomiaHelper
									.setValorFaturadoEconomia( 0f );
							
							
							histogramaAguaEconomiaHelper
									.setVolumeFaturadoEconomia(volumeFaturadoEconomiaAgua.intValue());

							listaSimplificadaAguaEconomia
									.add(histogramaAguaEconomiaHelper);
						}
					}

					if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_ESGOTO ) ){
						// Criamos um helper para os histograma de Esgoto por liga��o
						histogramaEsgotoLigacaoHelper = new HistogramaEsgotoLigacaoHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								idQuadra, 
								numeroQuadra,
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(), 
								ligacaoMista, 
								idConsumoTarifa,
								idPerfilImovel, 
								idEsferaPoder, 
								idSituacaoEsgoto,
								idConsumoReal, 
								idHidrometro, 
								idPoco,
								idVolumeFixoEsgoto, 
								percentualEsgoto,
								quantidadeConsumoEsgoto);
						
						if (listaSimplificadaEsgotoLigacao
								.contains(histogramaEsgotoLigacaoHelper)) {
							int posicao = listaSimplificadaEsgotoLigacao
									.indexOf(histogramaEsgotoLigacaoHelper);

							HistogramaEsgotoLigacaoHelper jaCadastrado = (HistogramaEsgotoLigacaoHelper) listaSimplificadaEsgotoLigacao
									.get(posicao);

							jaCadastrado.setQuantidadeLigacao(jaCadastrado
									.getQuantidadeLigacao()
									+ quantidadeLigacaoEsgoto);

							jaCadastrado
									.setQuantidadeEconomiaLigacao(jaCadastrado
											.getQuantidadeEconomiaLigacao()
											+ quantidadeEconomiaLigacaoEsgoto);

							jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado
									.getVolumeFaturadoLigacao()
									+ volumeFaturadoLigacaoEsgoto);
						} else {
							histogramaEsgotoLigacaoHelper
									.setQuantidadeLigacao(quantidadeLigacaoEsgoto);

							histogramaEsgotoLigacaoHelper
									.setQuantidadeEconomiaLigacao(quantidadeEconomiaLigacaoEsgoto.intValue());
							
							histogramaEsgotoLigacaoHelper
									.setValorFaturadoLigacao( 0f );

							histogramaEsgotoLigacaoHelper
									.setVolumeFaturadoLigacao(volumeFaturadoLigacaoEsgoto);

							listaSimplificadaEsgotoLigacao
									.add(histogramaEsgotoLigacaoHelper);
						}						
						
						// Criamos um helper para os histograma de Esgoto por Economia
						histogramaEsgotoEconomiaHelper = new HistogramaEsgotoEconomiaHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								idQuadra, 
								numeroQuadra,
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(), 
								idConsumoTarifa, 
								idPerfilImovel,
								idEsferaPoder, 
								idSituacaoEsgoto, 
								idConsumoReal,
								idHidrometro, 
								idPoco, 
								idVolumeFixoEsgoto,
								percentualEsgoto, 
								quantidadeConsumoEsgoto,
								1);
	
						// Agrupamos o histograma esgoto por economia
						if (listaSimplificadaEsgotoEconomia
								.contains(histogramaEsgotoEconomiaHelper)) {
							int posicao = listaSimplificadaEsgotoEconomia
									.indexOf(histogramaEsgotoEconomiaHelper);
	
							HistogramaEsgotoEconomiaHelper jaCadastrado = (HistogramaEsgotoEconomiaHelper) listaSimplificadaEsgotoEconomia
									.get(posicao);
	
							jaCadastrado.setQuantidadeEconomia(jaCadastrado
									.getQuantidadeEconomia()
									+ quantidadeEconomiaEsgoto);
	
							jaCadastrado.setVolumeFaturadoEconomia( ( (Integer) jaCadastrado
									.getVolumeFaturadoEconomia().intValue()
									+ volumeFaturadoEconomiaEsgoto.intValue() ) );
	
						} else {
							histogramaEsgotoEconomiaHelper
									.setQuantidadeEconomia(quantidadeEconomiaEsgoto);
							
							histogramaEsgotoEconomiaHelper
									.setValorFaturadoEconomia( 0f );
	
							histogramaEsgotoEconomiaHelper
									.setVolumeFaturadoEconomia(volumeFaturadoEconomiaEsgoto.intValue());
	
							listaSimplificadaEsgotoEconomia
									.add(histogramaEsgotoEconomiaHelper);
						}
					}
				}
			}

			for (int j = 0; j < listaSimplificadaAguaLigacao.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaAguaLigacao(
								anoMesReferencia,
								(HistogramaAguaLigacaoHelper) listaSimplificadaAguaLigacao
										.get(j));
			}

			for (int j = 0; j < listaSimplificadaAguaEconomia.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaAguaEconomima(
								anoMesReferencia,
								(HistogramaAguaEconomiaHelper) listaSimplificadaAguaEconomia
										.get(j));
			}

			for (int j = 0; j < listaSimplificadaEsgotoLigacao.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaEsgotoLigacao(
								anoMesReferencia,
								(HistogramaEsgotoLigacaoHelper) listaSimplificadaEsgotoLigacao
										.get(j));
			}

			for (int j = 0; j < listaSimplificadaEsgotoEconomia.size(); j++) {
				this.repositorioGerencialCadastro
						.inserirHistogramaEsgotoEconomia(
								anoMesReferencia,
								(HistogramaEsgotoEconomiaHelper) listaSimplificadaEsgotoEconomia
										.get(j));
			}					
			

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			System.out.println(" ERRO NO SETOR COMERCIAL " + idSetor);
	
			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			throw new EJBException(ex);
		}
	}

	/**
	 * M�todo que gera o resumo das liga��es e economias
	 * 
	 * [UC0275] - Gerar Resumo das Liga��es/Economias
	 * 
	 * @author Thiago Toscano, Bruno Barros, Ivan Sergio, Daniel Alves
	 * @date 19/04/2006, 17/04/2007, 09/01/2009, 07/04/2010
	 * @alteracao 09/01/2009 - CRC937 - Verificar o indicador de Imovel Condominio
	 * 									para acumular a qtdEconomias. 
	 * @alteracao 07/04/2010 - CRC3558 - Adicionar os novos campos,
	 *  [Quantidade de liga絥s cortadas mes  e	Quantidade de religa絥s no mes].
	 */
	public void gerarResumoLigacoesEconomias(int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

		try {

			List imoveisResumoLigacaoEconomias = this.repositorioGerencialCadastro
					.getImoveisResumoLigacaoEconomias(idSetor);

			List<ResumoLigacaoEconomiaHelper> listaSimplificada = new ArrayList();
			List<UnResumoLigacaoEconomia> listaResumoLigacoesEconomia = new ArrayList();

			Imovel imovelObterQtdEconomia = null;

			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		repositorioGerencialCadastro.excluirResumo( getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), UnResumoLigacaoEconomia.class.getName(), "referencia", idSetor, false );				
			
			
			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoLigacaoEconomias.size(); i++) {

				Object obj = imoveisResumoLigacaoEconomias.get(i);

				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;
					
					//******************************************************
					// Alteracao - CRC937
					//******************************************************
					Integer quantidadeEconomia = null;
					Short indicadorCondominio = (Short) retorno[24];
					
					if (indicadorCondominio == 1) {
						quantidadeEconomia = 1;
					}else {
						// Pesquisamos as quantidades de economia do imovel corrente
						imovelObterQtdEconomia = new Imovel();
						imovelObterQtdEconomia.setId((Integer) retorno[0]);
						quantidadeEconomia = this.getControladorImovel().obterQuantidadeEconomias(imovelObterQtdEconomia);
						if (quantidadeEconomia == null) {
							quantidadeEconomia = 0;
						}
					}
					//******************************************************
					
					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					ResumoLigacaoEconomiaHelper helper = new ResumoLigacaoEconomiaHelper(
							(Integer) retorno[1], // Gerencia Regional
							(Integer) retorno[2], // Unidade de negocio
							(Integer) retorno[3], // Localidade
							(Integer) retorno[4], // Elo
							(Integer) retorno[5], // Id Setor Comercial
							(Integer) retorno[6], // id Rota
							(Integer) retorno[7], // Id Quadra
							(Integer) retorno[8], // Codigo do Setor Comercial
							(Integer) retorno[9], // Numero da quadra
							(Integer) retorno[10], // Perfil do imovel
							(Integer) retorno[11],// Situacao da ligacao da
							// agua
							(Integer) retorno[12],// Situacao da ligacao do
							// esgoto
							(Integer) retorno[13],// Perfil da ligacao de agua
							(Integer) retorno[14],// Perfil da ligacao de
							// esgoto
							(Integer) retorno[15],// Possue hidrometro
							// instalado ?
							(Integer) retorno[16],// Possue hidrometro
							// instalado no poco ?
							(Integer) retorno[17],// Possue volume minimo de
							// agua fixado ?
							(Integer) retorno[18],// Possue volume minimo de
							// esgoto fixado ?
							(Integer) retorno[19],// Possue poco
							(Integer) retorno[22], // Tipo de Tarifa de Consumo
							(Short)   retorno[23]);// codigo rota

					Integer idImovel = (Integer) retorno[0]; // Codigo do
					// imovel que
					// esta sendo
					// processado

					// Pesquisamos a esfera de poder do cliente responsavel
					helper
							.setIdEsfera(this.repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
					// Pesquisamos o tipo de cliente responsavel do imovel
					helper
							.setIdTipoClienteResponsavel(this.repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do im�vel

					Categoria categoria = null;
					categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(categoria.getId(),
										idImovel);

						if (subcategoria != null) {
							helper.setIdSubCategoria(subcategoria.getComp_id()
									.getSubcategoria().getId());
						}
					}

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsfera().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdEsfera(clienteTemp.getClienteTipo()
									.getEsferaPoder().getId());
						}
					}
					if (helper.getIdTipoClienteResponsavel().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdTipoClienteResponsavel(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// se ja existe um objeto igual a ele entao soma as
					// ligacoes e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals ( excecao
					// quantidadeEconomia, quantidadeLigacoes )
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						ResumoLigacaoEconomiaHelper jaCadastrado = (ResumoLigacaoEconomiaHelper) listaSimplificada
								.get(posicao);

						// Somamos as economias
						jaCadastrado.setQtdEconomias(jaCadastrado.getQtdEconomias().intValue() + quantidadeEconomia);

						// Incrementamos as ligacoes
						jaCadastrado.setQtdLigacoes(jaCadastrado.getQtdLigacoes() + 1);						
						
						if ( retorno[25] != null && Util.recuperaAnoMesDaData( (Date) retorno[25] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue()  ){
							jaCadastrado.setQtdLigacoesNovasAgua(jaCadastrado.getQtdLigacoesNovasAgua() + 1 );
						}
						
						if ( retorno[26] != null && Util.recuperaAnoMesDaData( (Date) retorno[26] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue()  ){
							jaCadastrado.setQtdLigacoesNovasEsgoto(jaCadastrado.getQtdLigacoesNovasEsgoto() + 1 );
						}
						
						//retorno[27] = "data de Corte da liga磯 de ᧵a"
						if(retorno[27] != null && Util.recuperaAnoMesDaData( (Date) retorno[27] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue() &&							 
							 retorno[11] != null && (Integer)retorno[11] == 5){ //retorno[11] = last_id
							
							jaCadastrado.setQtLigacoesCortesMes(jaCadastrado.getQtLigacoesCortesMes()+1 );
							
						}						
						
						//retorno[28] = "data da religacao de agua" ou lagu_dtReligacaoAgua
						if(retorno[28] != null && Util.recuperaAnoMesDaData( (Date) retorno[28] ).intValue() == 
							this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue() &&
							((Integer)retorno[11] == 3 || (Integer)retorno[11] == 4)){
							
							jaCadastrado.setQtLigacoesReligadasMes(jaCadastrado.getQtLigacoesReligadasMes() + 1);
								
						}
						    
					} else {
						// Somamos as economias
						helper.setQtdEconomias(helper.getQtdEconomias()
								.intValue()
								+ quantidadeEconomia);

						// Incrementamos as ligacoes
						helper.setQtdLigacoes(helper.getQtdLigacoes() + 1);
						
						if ( retorno[25] != null && Util.recuperaAnoMesDaData( (Date) retorno[25] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue()  ){
							helper.setQtdLigacoesNovasAgua( 1 );
						}
						
						if ( retorno[26] != null && Util.recuperaAnoMesDaData( (Date) retorno[26] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue()  ){
							helper.setQtdLigacoesNovasEsgoto( 1 );
						}	
						
						if ( retorno[27] != null && Util.recuperaAnoMesDaData( (Date) retorno[27] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue() &&
							 retorno[11] != null && (Integer)retorno[11] == 5){ //retorno[11] = last_id
												
							helper.setQtLigacoesCortesMes( 1 );
						}
						
						if ( retorno[28] != null && Util.recuperaAnoMesDaData( (Date) retorno[28] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue() &&
							 ((Integer)retorno[11] == 3 || (Integer)retorno[11] == 4)){
							helper.setQtLigacoesReligadasMes( 1 );
						}

						listaSimplificada.add(helper);
					}
				}
			}

			// /**
			// * para todas as ImovelResumoLigacaoEconomiaHelper cria
			// * ResumoLigacoesEconomia
			// */
			for (int i = 0; i < listaSimplificada.size(); i++) {
				ResumoLigacaoEconomiaHelper helper = (ResumoLigacaoEconomiaHelper) listaSimplificada
						.get(i);

				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento();

				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}

				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}

				// Localidade
				GLocalidade localidade = null;
				if (helper.getIdLocalidade() != null) {
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdElo());
				}

				// Setor comercial
				GSetorComercial setorComercial = null;
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}

				// Rota
				GRota rota = null;
				if (helper.getIdRota() != null) {
					rota = new GRota();
					rota.setId(helper.getIdRota());
					rota.setCodigoRota(helper.getCodigoRota());
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}

				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}

				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}

				// Tipo do cliente responsavel
				GClienteTipo clienteTipo = null;
				if (helper.getIdTipoClienteResponsavel() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoClienteResponsavel());
				}

				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}

				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}

				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null) {
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId(helper.getIdPerfilLigacaoAgua());
				}

				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null) {
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto
							.setId(helper.getIdPerfilLigacaoEsgoto());
				}

				// Indicador de hidrometro
				Short indicadorHidrometro = null;
				if (helper.getIdHidrometro() != null) {
					indicadorHidrometro = helper.getIdHidrometro().shortValue();
				}

				// Indicador de hidrometro no poco
				Short indicadorHidrometroPoco = null;
				if (helper.getIdHidrometroPoco() != null) {
					indicadorHidrometroPoco = helper.getIdHidrometroPoco()
							.shortValue();
				}

				// Indicador de volume minimo de agua fixado
				Short indicadorVolumeFixadoAgua = null;
				if (helper.getIdVolFixadoAgua() != null) {
					indicadorVolumeFixadoAgua = helper.getIdVolFixadoAgua()
							.shortValue();
				}

				// Indicador de volume minimo de esgoto fixado
				Short indicadorVolumeFixadoEsgoto = null;
				if (helper.getIdVolFixadoEsgoto() != null) {
					indicadorVolumeFixadoEsgoto = helper.getIdVolFixadoEsgoto()
							.shortValue();
				}

				// Indicador de poco
				Short indicadorPoco = null;
				if (helper.getIdPoco() != null) {
					indicadorPoco = helper.getIdPoco().shortValue();
				}

				// Tipo de Tarifa de Consumo
				GConsumoTarifa consumoTarifa = null;
				if (helper.getIdTipoTarifaConsumo() != null) {
					consumoTarifa = new GConsumoTarifa();
					consumoTarifa.setId(helper.getIdTipoTarifaConsumo());
				}

				// Quantidade ligacoes
				Integer qtdLigacoes = helper.getQtdLigacoes();

				// Quantidade economias
				Integer qtdEconomias = helper.getQtdEconomias();

				// Criamos um resumo de ligacao economia (***UFA***)
				UnResumoLigacaoEconomia resumoLigacoesEconomia = new UnResumoLigacaoEconomia(
						anoMesReferencia, gerenciaRegional, unidadeNegocio,
						localidade, elo, setorComercial, rota, quadra,
						codigoSetorComercial, numeroQuadra, imovelPerfil,
						esferaPoder, clienteTipo, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao, categoria, subcategoria,
						perfilLigacaoAgua, perfilLigacaoEsgoto,
						indicadorHidrometro, indicadorHidrometroPoco,
						indicadorVolumeFixadoAgua, indicadorVolumeFixadoEsgoto,
						indicadorPoco, consumoTarifa, qtdLigacoes, qtdEconomias,
						helper.getCodigoRota(), helper.getQtdLigacoesNovasAgua(), 
						helper.getQtdLigacoesNovasEsgoto(), helper.getQtLigacoesCortesMes(), 
						helper.getQtLigacoesReligadasMes());

				// Adicionamos a lista que deve ser inserida
				listaResumoLigacoesEconomia.add(resumoLigacoesEconomia);
			}

			this.getControladorBatch().inserirColecaoObjetoParaBatchGerencial(
					(Collection) listaResumoLigacoesEconomia);

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido

			System.out.println(" ERRO NO SETOR" + idSetor);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}

	/**
	 * M�todo que gera o resumo das liga��es e economias
	 * 
	 * [UC0275] - Gerar Resumo das Liga��es/Economias Regiao
	 * 
	 * @author Ivan S�rgio
	 * @date 20/04/2007
	 */
	public void gerarResumoLigacaoEconomiaRegiao(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {
			List imoveisResumoLigacaoEconomiaRegiao = this.repositorioGerencialCadastro
					.getImoveisResumoLigacaoEconomiaRegiao(idLocalidade);

			List<ResumoLigacaoEconomiaRegiaoHelper> listaSimplificada = new ArrayList();
			List<ResumoLigacaoEconomiaRegiao> listaResumoLigacaoEconomiaRegiao = new ArrayList();

			Imovel imovelObterQtdEconomia = null;

			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoLigacaoEconomiaRegiao.size(); i++) {
				Object obj = imoveisResumoLigacaoEconomiaRegiao.get(i);

				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					// Pesquisamos as quantidades de economia do imovel corrente
					imovelObterQtdEconomia = new Imovel();
					imovelObterQtdEconomia.setId((Integer) retorno[0]);
					Integer quantidadeEconomia = this.getControladorImovel()
							.obterQuantidadeEconomias(imovelObterQtdEconomia);
					if (quantidadeEconomia == null) {
						quantidadeEconomia = 0;
					}

					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					ResumoLigacaoEconomiaRegiaoHelper helper = new ResumoLigacaoEconomiaRegiaoHelper(
							(Integer) retorno[1], // Regiao
							(Integer) retorno[2], // Microrregiao
							(Integer) retorno[3], // Municipio
							(Integer) retorno[4], // Bairro
							(Integer) retorno[5], // Perfil Imovel
							(Integer) retorno[6], // Ligacao Agua Situacao
							(Integer) retorno[7], // Ligacao Esgoto Situacao
							(Integer) retorno[8], // Esfera de Poder do
							// Cliente
							(Integer) retorno[9], // Tipo de Cliente do
							// Cliente Responsavel
							(Integer) retorno[10], // Perfil da Ligacao da Agua
							(Integer) retorno[11], // Perfil da Ligacao do
							// Esgoto
							(Integer) retorno[12], // Possue hidrometro
							// instalado ?
							(Integer) retorno[13], // Possue hidrometro
							// instalado no poco ?
							(Integer) retorno[14], // Possue volume minimo de
							// agua fixado ?
							(Integer) retorno[15], // Possue volume minimo de
							// esgoto fixado ?
							(Integer) retorno[16]);// Possue poco ?

					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do im�vel
					Integer idImovel = (Integer) retorno[0]; // Codigo do
					// imovel que
					// esta sendo
					// processado
					Categoria categoria = null;
					categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);

					if (categoria != null) {
						helper.setIdPrincipalCategoriaImovel(categoria.getId());

						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(categoria.getId(),
										idImovel);

						if (subcategoria != null) {
							helper
									.setIdPrincipalSubCategoriaImovel(subcategoria
											.getComp_id().getSubcategoria()
											.getId());
						}
					}

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsferaCliente().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdEsferaCliente(clienteTemp
									.getClienteTipo().getEsferaPoder().getId());
						}
					}

					if (helper.getIdTipoClienteResponsavel().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdTipoClienteResponsavel(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// se ja existe um objeto igual a ele entao soma as
					// ligacoes e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacoes iguals ( excecao
					// quantidadeEconomia, quantidadeLigacoes )
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						ResumoLigacaoEconomiaRegiaoHelper jaCadastrado = (ResumoLigacaoEconomiaRegiaoHelper) listaSimplificada
								.get(posicao);

						// Somamos as economias
						jaCadastrado.setQuantidadeEconomia(jaCadastrado
								.getQuantidadeEconomia().intValue()
								+ quantidadeEconomia);

						// Incrementamos as ligacoes
						jaCadastrado.setQuantidadeLigacoes(jaCadastrado
								.getQuantidadeLigacoes() + 1);

					} else {
						// Somamos as economias
						helper.setQuantidadeEconomia(helper
								.getQuantidadeEconomia().intValue()
								+ quantidadeEconomia);

						// Incrementamos as ligacoes
						helper.setQuantidadeLigacoes(helper
								.getQuantidadeLigacoes() + 1);

						listaSimplificada.add(helper);
					}
				}
			}

			/**
			 * para todas as ImovelResumoLigacaoEconomiaRegiaoHelper cria
			 * ResumoLigacoesEconomiaRegiao
			 */
			for (int i = 0; i < listaSimplificada.size(); i++) {
				ResumoLigacaoEconomiaRegiaoHelper helper = (ResumoLigacaoEconomiaRegiaoHelper) listaSimplificada
						.get(i);

				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = Util
						.getAnoMesComoInteger(new Date());

				// Regiao
				Regiao regiao = null;
				if (helper.getIdRegiao() != null) {
					regiao = new Regiao();
					regiao.setId(helper.getIdRegiao());
				}

				// Microrregiao
				Microrregiao microrregiao = null;
				if (helper.getIdMicrorregiao() != null) {
					microrregiao = new Microrregiao();
					microrregiao.setId(helper.getIdMicrorregiao());
				}

				// Municipio
				Municipio municipio = null;
				if (helper.getIdMunicipio() != null) {
					municipio = new Municipio();
					municipio.setId(helper.getIdMunicipio());
				}

				// Bairro
				Bairro bairro = null;
				if (helper.getIdBairro() != null) {
					bairro = new Bairro();
					bairro.setId(helper.getIdBairro());
				}

				// Perfil do imovel
				ImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}

				// Situacao da ligacao de agua
				LigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}

				// Principal Categoria do Imovel
				Categoria categoria = null;
				if (helper.getIdPrincipalCategoriaImovel() != null) {
					categoria = new Categoria();
					categoria.setId(helper.getIdPrincipalCategoriaImovel());
				}

				// Principal Sub Categoria do Imovel
				Subcategoria subcategoria = null;
				if (helper.getIdPrincipalSubCategoriaImovel() != null) {
					subcategoria = new Subcategoria();
					subcategoria.setId(helper
							.getIdPrincipalSubCategoriaImovel());
				}

				// Esfera de poder do cliente responsavel
				EsferaPoder esferaPoder = null;
				if (helper.getIdEsferaCliente() != null) {
					esferaPoder = new EsferaPoder();
					esferaPoder.setId(helper.getIdEsferaCliente());
				}

				// Tipo do cliente responsavel
				ClienteTipo clienteTipo = null;
				if (helper.getIdTipoClienteResponsavel() != null) {
					clienteTipo = new ClienteTipo();
					clienteTipo.setId(helper.getIdTipoClienteResponsavel());
				}

				// Perfil da ligacao de agua
				LigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null) {
					perfilLigacaoAgua = new LigacaoAguaPerfil();
					perfilLigacaoAgua.setId(helper.getIdPerfilLigacaoAgua());
				}

				// Perfil da ligacao de esgoto
				LigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null) {
					perfilLigacaoEsgoto = new LigacaoEsgotoPerfil();
					perfilLigacaoEsgoto
							.setId(helper.getIdPerfilLigacaoEsgoto());
				}

				// Indicador de hidrometro
				Short indicadorHidrometro = null;
				if (helper.getIdIndicadorHidrometro() != null) {
					indicadorHidrometro = helper.getIdIndicadorHidrometro()
							.shortValue();
				}

				// Indicador de hidrometro no poco
				Short indicadorHidrometroPoco = null;
				if (helper.getIdIndicadorHidrometroPoco() != null) {
					indicadorHidrometroPoco = helper
							.getIdIndicadorHidrometroPoco().shortValue();
				}

				// Indicador de volume minimo de agua fixado
				Short indicadorVolumeFixadoAgua = null;
				if (helper.getIdIndicadorVolumeMinimoAguaFixado() != null) {
					indicadorVolumeFixadoAgua = helper
							.getIdIndicadorVolumeMinimoAguaFixado()
							.shortValue();
				}

				// Indicador de volume minimo de esgoto fixado
				Short indicadorVolumeFixadoEsgoto = null;
				if (helper.getIdIndicadorVolumeMinimoEsgotoFixado() != null) {
					indicadorVolumeFixadoEsgoto = helper
							.getIdIndicadorVolumeMinimoEsgotoFixado()
							.shortValue();
				}

				// Indicador de poco
				Short indicadorPoco = null;
				if (helper.getIdIndicadorPoco() != null) {
					indicadorPoco = helper.getIdIndicadorPoco().shortValue();
				}

				// Quantidade ligacoes
				Integer qtdLigacoes = helper.getQuantidadeLigacoes();

				// Quantidade economias
				Integer qtdEconomias = helper.getQuantidadeEconomia();

				// Criamos um resumo de ligacao economia regiao
				ResumoLigacaoEconomiaRegiao resumoLigacoesEconomiaRegiao = new ResumoLigacaoEconomiaRegiao(
						anoMesReferencia, regiao, microrregiao, municipio,
						bairro, imovelPerfil, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao, categoria, subcategoria,
						esferaPoder, clienteTipo, perfilLigacaoAgua,
						perfilLigacaoEsgoto, indicadorHidrometro,
						indicadorHidrometroPoco, indicadorVolumeFixadoAgua,
						indicadorVolumeFixadoEsgoto, indicadorPoco,
						qtdLigacoes, qtdEconomias);

				// Adicionamos a lista que deve ser inserida
				listaResumoLigacaoEconomiaRegiao
						.add(resumoLigacoesEconomiaRegiao);
			}

			// this.repositorioGerencialCadastro
			// .inserirResumoLigacaoEconomiaRegiaoBatch(listaResumoLigacaoEconomiaRegiao);

			this.getControladorBatch().inserirColecaoObjetoParaBatchGerencial(
					(Collection) listaResumoLigacaoEconomiaRegiao);

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}// gerarResumoLigacaoEconomiaRegiao

    /**
     * M�todo que gera o resumo do consumo de agua
     * 
     * [UC0570] - Gerar Resumo do consumo de agua
     * 
     * @author Bruno Barros, Ivan S鲧io
     * @date 23/04/2007, 19/01/2009
     * @alteracao 19/01/2009 - CRC1012 - Adicionado o Indicador de Ligacao Faturada.
     * 
     */
    public void gerarResumoConsumoAgua(int idSetor, int idFuncionalidadeIniciada)
            throws ControladorException {

        int idUnidadeIniciada = 0;

        // -------------------------
        //
        // Registrar o in�cio do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch()
                .iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
                        UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

        Integer anoMesReferencia = null;
        Integer imovelErro = null;

        try {
            
            List imoveisResumoConsumoAgua = this.repositorioGerencialCadastro
                    .getHistoricosConsumoAgua(idSetor);

            List<ResumoConsumoAguaHelper> listaSimplificada = new ArrayList();
            //List<UnResumoConsumoAgua> listaResumoConsumoAgua = new ArrayList();

            Imovel imovelObterQtdEconomia = null;
            
            //FS0001 - Verificar existencia de dados para o ano/mes referencia informado
            repositorioGerencialCadastro.excluirResumo( 
                    getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
                    UnResumoConsumoAgua.class.getName(), 
                    "referencia", 
                    idSetor,
                    false);             

            // pra cada objeto obter a categoria
            // caso ja tenha um igual soma a quantidade de economias e a
            // quantidade de ligacoes
            for (int i = 0; i < imoveisResumoConsumoAgua.size(); i++) {
                Object obj = imoveisResumoConsumoAgua.get(i);
                
                //System.out.println( i + " de " + imoveisResumoConsumoAgua.size() + " do Setor " + idSetor );

                if (obj instanceof Object[]) {
                    Object[] retorno = (Object[]) obj;

                    // Montamos um objeto de resumo, com as informacoes do
                    // retorno
                    ResumoConsumoAguaHelper helper = new ResumoConsumoAguaHelper(
                            (Integer) retorno[1], // Gerencia Regional
                            (Integer) retorno[2], // Unidade de negocio
                            (Integer) retorno[3], // Localidade
                            (Integer) retorno[4], // Elo
                            (Integer) retorno[5], // Id Setor Comercial
                            (Integer) retorno[6], // id Rota
                            (Integer) retorno[7], // Id Quadra
                            (Integer) retorno[8], // Codigo do Setor Comercial
                            (Integer) retorno[9], // Numero da quadra
                            (Integer) retorno[10],// Perfil do imovel
                            (Integer) retorno[11],// Situacao da ligacao da agua
                            (Integer) retorno[12],// Situacao da ligacao do esgoto
                            (Integer) retorno[13],// Perfil da ligacao de agua
                            (Integer) retorno[14],// Perfil da ligacao de esgoto
                            (Integer) retorno[15] // Consumo tipo 
                        );
                    
                    Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
                    imovelErro = idImovel;
                    Integer idConta = (Integer) retorno[20]; // C?o da conta que esta sendo processada
                    Conta conta = new Conta();
                    conta.setId( idConta );                 
                    
                    // Quantidade de Economias
                    Integer quantidadeEconomia = 0;                 
                    
                    // Verificamos se existe economias pela conta
                    if ( idConta != null ){
                        Collection<Categoria> colQuantEco = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria( conta );
                        
                        if ( colQuantEco != null && colQuantEco.size() > 0  ){
                            Iterator iteQuanEco = colQuantEco.iterator();                           
                            
                            while ( iteQuanEco.hasNext() ){
                                Categoria catTemp = (Categoria) iteQuanEco.next();
                                
                                quantidadeEconomia += catTemp.getQuantidadeEconomiasCategoria();                                
                            }
                        }
                    }
                    
                    // Caso n㯠obtenha as quantidades pela conta, pesquisamos as quantidades de economia do imovel corrente                    
                    if ( quantidadeEconomia == 0 ){                                     
                        imovelObterQtdEconomia = new Imovel();
                        imovelObterQtdEconomia.setId((Integer) retorno[0]);
                        quantidadeEconomia = this.getControladorImovel()
                                .obterQuantidadeEconomias(imovelObterQtdEconomia);
                        if (quantidadeEconomia == null) {
                            quantidadeEconomia = 0;
                        }
                    }
                    
                    // Consumo de Agua
                    Integer consumoAgua = null;
                    // Verificamos se o consumo de Agua esta na conta ou no consumoHistorico
                    /*********************************************************
                     * Autor: Ivan Sergio
                     * Solicitante: Flavio Leonardo
                     * Data: 03/11/2010
                     *********************************************************/
                    BigDecimal valorConta = (BigDecimal) retorno[22];
                    if( idConta != null && (valorConta.compareTo(BigDecimal.ZERO) > 0)){
                        consumoAgua = (Integer) retorno[21];                        
                    } else {
                        consumoAgua= (Integer) retorno[16];
                    }
                    
                    helper.setQuantidadeConsumoAgua( consumoAgua );
                    
                    // [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
                    String indicadorHidrometroString = new Integer(
                            getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
                    Integer indicadorHidrometro = new Integer(indicadorHidrometroString);
                    // Caso indicador de hidr�metro esteja nulo
                    // Seta 2(dois) = N�O no indicador de
                    // hidr�metro
                    if (indicadorHidrometro == null) {
                        indicadorHidrometro = 2;
                    }
                    helper.setIdHidrometro( indicadorHidrometro );          
                    

                    // Pesquisamos a esfera de poder do cliente responsavel
                    helper
                            .setIdEsferaPoder(this.repositorioGerencialCadastro
                                    .pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
                    // Pesquisamos o tipo de cliente responsavel do imovel
                    helper
                            .setIdClienteTipo(this.repositorioGerencialCadastro
                                    .pesquisarTipoClienteClienteResponsavelImovel(idImovel));
                    
                    // pesquisando a categoria
                    Categoria categoria = null;
                    Categoria categoriaConta = null;
                    
                    // [UC0632] - Obter Principal Categoria da Conta
                    // Verificamos se existe conta para este imovel
                    if ( idConta != null ){
                        categoriaConta = this.getControladorImovel().obterPrincipalCategoriaConta( idConta );
                    }
                    
                    // Pesquisamos a categoria do imovel
                    Categoria categoriaImovel = this.getControladorImovel()
                        .obterPrincipalCategoriaImovel(idImovel);
                    
                    // Verificamos se devemos guardar a categoria da conta ou do imovel
                    if ( categoriaConta == null ){
                        categoria = categoriaImovel;
                    } else {
                        categoria = categoriaConta;
                    }

                    // Pesquisamos a subcategoria
                    if (categoria != null) {
                        helper.setIdCategoria(categoria.getId());

                        // Pesquisando a principal subcategoria
                        // Lembrando que a categoria usada para pesquisar a subcategoria e sempre a do imovel
                        ImovelSubcategoria subcategoria = this
                                .getControladorImovel()
                                .obterPrincipalSubcategoria(categoriaImovel.getId(),
                                        idImovel);
                        
                        helper.setIdSubCategoria(subcategoria.getComp_id()
                                .getSubcategoria().getId());
                    }

                    // Verificamos se a esfera de poder foi encontrada
                    // [FS0002] Verificar existencia de cliente responsavel
                    if (helper.getIdEsferaPoder().equals(0)) {
                        Imovel imovel = new Imovel();
                        imovel.setId(idImovel);
                        Cliente clienteTemp = this.getControladorImovel()
                                .consultarClienteUsuarioImovel(imovel);
                        if (clienteTemp != null) {
                            helper.setIdEsferaPoder(clienteTemp
                                    .getClienteTipo().getEsferaPoder().getId());
                        }
                    }
                    
                    if (helper.getIdClienteTipo().equals(0)) {
                        Imovel imovel = new Imovel();
                        imovel.setId(idImovel);
                        Cliente clienteTemp = this.getControladorImovel()
                                .consultarClienteUsuarioImovel(imovel);
                        if (clienteTemp != null) {
                            helper.setIdClienteTipo(clienteTemp
                                    .getClienteTipo().getId());
                        }
                    }

                    // Calculamos o consumo excedente
                    // [UC0105] - Obter consumo minimo ligacao
                    // Valor minimo de consumo de agua                  
                    anoMesReferencia = (Integer) retorno[18];
                    Imovel imovelTemp = new Imovel();
                    imovelTemp.setId(idImovel);
                    imovelTemp = this.getControladorImovel()
                            .pesquisarImovel(imovelTemp.getId());                   

                    Integer valorMinimoAgua = getControladorMicromedicao()
                            .obterConsumoMinimoLigacao(imovelTemp, null);

                    // Setamos o valor do excedente
                
                    helper.setQuantidadeConsumoAguaExcedente(helper
                            .getQuantidadeConsumoAgua()
                            - valorMinimoAgua);

                    // Caso o valor excedente seja negativo, seramos, pois nao
                    // houve excedente.
                    if (helper.getQuantidadeConsumoAguaExcedente() <= 0) {
                        helper.setQuantidadeConsumoAguaExcedente(0);
                    }
                    
                    // Marcamos o helper como possuindo ou n�o consumo excedente
                    helper.setIdVolumeExcedente(helper
                            .getQuantidadeConsumoAguaExcedente() > 0 ? 1 : 2);
                    
                    //**********************************************************
                    // CRC1012 - Verificamos as Ligacoes Faturadas
                    //**********************************************************
                    if (idConta != null) {
                        helper.setIndicadorLigacaoFaturada(1);
                    }else {
                        helper.setIndicadorLigacaoFaturada(2);
                    }
                    //**********************************************************

                    // se ja existe um objeto igual a ele entao soma as
                    // ligacoes e as economias no ja existente
                    // um objeto eh igual ao outro se ele tem todos as
                    // informacos iguals ( excecao
                    // quantidadeEconomia, quantidadeLigacoes )
                    if (listaSimplificada.contains(helper)) {
                        int posicao = listaSimplificada.indexOf(helper);
                        ResumoConsumoAguaHelper jaCadastrado = (ResumoConsumoAguaHelper) listaSimplificada
                                .get(posicao);

                        // Somamos as economias
                        jaCadastrado.setQuantidadeEconomias(jaCadastrado
                                .getQuantidadeEconomias().intValue()
                                + quantidadeEconomia);

                        // Incrementamos as ligacoes
                        jaCadastrado.setQuantidadeLigacoes(jaCadastrado
                                .getQuantidadeLigacoes() + 1);

                        // Acumulamos o consumo de agua
                        jaCadastrado.setQuantidadeConsumoAgua(jaCadastrado
                                .getQuantidadeConsumoAgua().intValue()
                                + helper.getQuantidadeConsumoAgua());
                        
                        // Acumulamos o consumo Excedente
                        jaCadastrado.setQuantidadeConsumoAguaExcedente(
                                jaCadastrado.getQuantidadeConsumoAguaExcedente() +
                                helper.getQuantidadeConsumoAguaExcedente() );
                        
                        // Guardamos o volume faturado, caso este seja maior que o m���mo
                        if ( helper.getQuantidadeConsumoAgua() > valorMinimoAgua ){
                            jaCadastrado.setVolumeFaturado( 
                                    jaCadastrado.getVolumeFaturado() + 
                                    helper.getQuantidadeConsumoAgua() );
                        } else {
                            jaCadastrado.setVolumeFaturado( 
                                    jaCadastrado.getVolumeFaturado() + 
                                    valorMinimoAgua );
                        }                       
                    } else {
                        // Somamos as economias
                        helper.setQuantidadeEconomias(helper
                                .getQuantidadeEconomias().intValue()
                                + quantidadeEconomia);

                        // Incrementamos as ligacoes
                        helper.setQuantidadeLigacoes(helper
                                .getQuantidadeLigacoes() + 1);
                        
                        // Guardamos o volume faturado, caso este seja maior que o m���mo
                        if ( helper.getQuantidadeConsumoAgua() > valorMinimoAgua ){
                            helper.setVolumeFaturado( 
                                    helper.getQuantidadeConsumoAgua() );
                        } else {
                            helper.setVolumeFaturado( 
                                    valorMinimoAgua );
                        }                       

                        listaSimplificada.add(helper);
                    }
                }
            }

            for (int i = 0; i < listaSimplificada.size(); i++) {
                this.repositorioGerencialCadastro.inserirResumoConsumoAgua(
                        anoMesReferencia,
                        (ResumoConsumoAguaHelper) listaSimplificada.get(i));
            }

            // --------------------------------------------------------
            //
            // Registrar o fim da execu��o da Unidade de Processamento
            //
            // --------------------------------------------------------

            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
                    idUnidadeIniciada, false);

        } catch (Exception ex) {
            // Este catch serve para interceptar qualquer exce��o que o processo
            // batch venha a lan�ar e garantir que a unidade de processamento do
            // batch ser� atualizada com o erro ocorrido
            System.out.println(" ERRO NO SETOR " + idSetor);
            System.out.println("Imovel que deu erro: "+ imovelErro);

            ex.printStackTrace();
            sessionContext.setRollbackOnly();

            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
                    idUnidadeIniciada, true);

            throw new EJBException(ex);
        }
    }   


	/**
	 * [UC0344] Consultar Resumo Anormalidade
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoLigacoesEconomias(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		try {
			// [FS0001] Verificar exist�ncia de dados para o ano/m�s de
			// refer�ncia retornado

			// if( countResumoPendencia == null || countResumoPendencia == 0 ){
			// throw new ControladorException(
			// "atencao.nao_existe_resumo_ligacoes_economia", null,
			// Util.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia()));
			// }

			Object[] objetoResumoLigacaoEconomia = null;
			Iterator iteratorResumoLigacaoEconomia = null;
			ResumoLigacaoEconomiaConsultarHelper resumoLigacaoEconomiaConsultarHelper = null;
			ResumoLigacaoEconomiaConsultarHelper resumoLigacaoEconomiaConsultarHelperAnterior = null;
			List colecaoLigacaoEconomiaHelper = new ArrayList();

			List retorno = this.repositorioGerencialCadastro
					.consultarResumoLigacoesEconomias(informarDadosGeracaoRelatorioConsultaHelper);

			boolean primeiraVez = true;
			// Monta os objetos e carrega uma colecao para mandar para a proxima
			// camada
			iteratorResumoLigacaoEconomia = retorno.iterator();
			while (iteratorResumoLigacaoEconomia.hasNext()) {
				objetoResumoLigacaoEconomia = (Object[]) iteratorResumoLigacaoEconomia
						.next();
				resumoLigacaoEconomiaConsultarHelper = new ResumoLigacaoEconomiaConsultarHelper();

				resumoLigacaoEconomiaConsultarHelper
						.setAnoMesReferncia(informarDadosGeracaoRelatorioConsultaHelper
								.getAnoMesReferencia().toString());
				resumoLigacaoEconomiaConsultarHelper
						.setDescricaoEstado((String) objetoResumoLigacaoEconomia[0]);
				resumoLigacaoEconomiaConsultarHelper
						.setIdGerenciaRegional(objetoResumoLigacaoEconomia[1] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[1]);
				resumoLigacaoEconomiaConsultarHelper
						.setDescricaoGerenciaRegional((String) objetoResumoLigacaoEconomia[2]);
				resumoLigacaoEconomiaConsultarHelper
						.setIdElo((Integer) objetoResumoLigacaoEconomia[3] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[3]);
				resumoLigacaoEconomiaConsultarHelper
						.setDescricaoElo((String) objetoResumoLigacaoEconomia[4]);
				resumoLigacaoEconomiaConsultarHelper
						.setIdLocalidade((Integer) objetoResumoLigacaoEconomia[5] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[5]);
				resumoLigacaoEconomiaConsultarHelper
						.setDescricaoLocalidade((String) objetoResumoLigacaoEconomia[6]);
				resumoLigacaoEconomiaConsultarHelper
						.setIdSetorComercial((Integer) objetoResumoLigacaoEconomia[7] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[7]);
				resumoLigacaoEconomiaConsultarHelper
						.setDescricaoSetorComercial((String) objetoResumoLigacaoEconomia[8]);
				resumoLigacaoEconomiaConsultarHelper
						.setIdQuadra((Integer) objetoResumoLigacaoEconomia[9] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[9]);
				resumoLigacaoEconomiaConsultarHelper
						.setNumeroQuadra((String) objetoResumoLigacaoEconomia[10]);

				resumoLigacaoEconomiaConsultarHelper
						.setIdSituacaoLigacaoAgua((Integer) objetoResumoLigacaoEconomia[11] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[11]);
				resumoLigacaoEconomiaConsultarHelper
						.setIdSituacaoLigacaoEsgoto((Integer) objetoResumoLigacaoEconomia[13] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[13]);
				resumoLigacaoEconomiaConsultarHelper
						.setIdCategoria((Integer) objetoResumoLigacaoEconomia[15] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[15]);

				if (primeiraVez) {
					primeiraVez = false;
					resumoLigacaoEconomiaConsultarHelper
							.setDescricaoSituacaoLigacaoAgua((String) objetoResumoLigacaoEconomia[12]);
					resumoLigacaoEconomiaConsultarHelper
							.setDescricaoSituacaoLigacaoEsgoto((String) objetoResumoLigacaoEconomia[14]);
					resumoLigacaoEconomiaConsultarHelper
							.setDescricaoCategoria((String) objetoResumoLigacaoEconomia[16]);

				} else {
					if (!resumoLigacaoEconomiaConsultarHelperAnterior
							.getIdSituacaoLigacaoAgua().equals(
									resumoLigacaoEconomiaConsultarHelper
											.getIdSituacaoLigacaoAgua())) {
						resumoLigacaoEconomiaConsultarHelper
								.setDescricaoSituacaoLigacaoAgua((String) objetoResumoLigacaoEconomia[12]);
					}
					if (!resumoLigacaoEconomiaConsultarHelperAnterior
							.getIdSituacaoLigacaoEsgoto().equals(
									resumoLigacaoEconomiaConsultarHelper
											.getIdSituacaoLigacaoEsgoto())) {
						resumoLigacaoEconomiaConsultarHelper
								.setDescricaoSituacaoLigacaoEsgoto((String) objetoResumoLigacaoEconomia[14]);
					}
					if (!resumoLigacaoEconomiaConsultarHelperAnterior
							.getIdCategoria().equals(
									resumoLigacaoEconomiaConsultarHelper
											.getIdCategoria())) {
						resumoLigacaoEconomiaConsultarHelper
								.setDescricaoCategoria((String) objetoResumoLigacaoEconomia[16]);
					}
				}

				resumoLigacaoEconomiaConsultarHelper
						.setQuantidadeLigacoesComHidrometro((Integer) objetoResumoLigacaoEconomia[17] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[17]);
				resumoLigacaoEconomiaConsultarHelper
						.setQuantidadeLigacoesSemHidrometro((Integer) objetoResumoLigacaoEconomia[18] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[18]);
				resumoLigacaoEconomiaConsultarHelper
						.setTotalLigacoes((Integer) objetoResumoLigacaoEconomia[19] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[19]);
				resumoLigacaoEconomiaConsultarHelper
						.setQuantidadeEconomiaComHidrometro((Integer) objetoResumoLigacaoEconomia[20] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[20]);
				resumoLigacaoEconomiaConsultarHelper
						.setQuantidadeEconomiaSemHidrometro((Integer) objetoResumoLigacaoEconomia[21] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[21]);
				resumoLigacaoEconomiaConsultarHelper
						.setTotalEconomia((Integer) objetoResumoLigacaoEconomia[22] == null ? null
								: (Integer) objetoResumoLigacaoEconomia[22]);

				colecaoLigacaoEconomiaHelper
						.add(resumoLigacaoEconomiaConsultarHelper);
				resumoLigacaoEconomiaConsultarHelperAnterior = resumoLigacaoEconomiaConsultarHelper;
			}

			// [FS0007] Nenhum registro encontrado
			if (retorno == null || retorno.equals("") || retorno.isEmpty()) {
				throw new ControladorException(
						"atencao.pesquisa.nenhumresultado");
			}

			return colecaoLigacaoEconomiaHelper;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Retorna o valor do ControladorMicromedicao
	 * 
	 * @author Bruno Barros
	 * @date 23/04/2007
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
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
	 * M�todo que gera o resumo do Parcelamento
	 * 
	 * [UC0565] - Gerar Resumo do Parcelamento
	 * 
	 * @author Marcio Roberto
	 * @date 04/05/2007
	 * 
	 */
	public void gerarResumoParcelamento(int idLocalidade,
			int idFuncionalidadeIniciada, int anoMes)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {
			
			System.out.println("INICIO DO RESUMO PARCELAMENTO LOCALIDADE "+idLocalidade);
			
			List imoveisResumoParcelamento = this.repositorioGerencialCadastro
					.getImoveisResumoParcelamento(idLocalidade, anoMes);

			List<ResumoParcelamentoHelper> listaSimplificadaParcelamento = new ArrayList();
			List<UnResumoParcelamento> listaResumoParcelamento = new ArrayList();

			UnResumoParcelamento resumoParcelamento = null;

			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		this.repositorioGerencialCadastro.excluirResumoGerencialC( getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), "cobranca.un_resumo_parcelamento", "repa_amreferencia","loca_id", idLocalidade );				
			
			
			// caso ja tenha um igual soma as quantidades
			for (int i = 0; i < imoveisResumoParcelamento.size(); i++) {

				Object obj = imoveisResumoParcelamento.get(i);

				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					// Pesquisamos as quantidades do imovel corrente
					resumoParcelamento = new UnResumoParcelamento();
					resumoParcelamento.setId((Integer) retorno[0]);

					// Contas
					Integer quantidadeContas = this.repositorioGerencialCadastro
							.pesquisarObterQuantidadeContas((Integer) retorno[0]);
					if (quantidadeContas == null) {
						quantidadeContas = 0;
					}

					// Guias
					Integer quantidadeGuias = this.repositorioGerencialCadastro
							.pesquisarObterQuantidadeGuias((Integer) retorno[0]);
					if (quantidadeGuias == null) {
						quantidadeGuias = 0;
					}

					// Quantidade Servicos Indiretos
					Short quantidadeServicosIndiretos = this.repositorioGerencialCadastro
							.pesquisarObterQuantidadeServicosIndiretos((Integer) retorno[0]);
					if (quantidadeServicosIndiretos == null) {
						quantidadeServicosIndiretos = 0;
					}

					// Valor das Contas
					BigDecimal valorContas = (BigDecimal) retorno[16];
					if (valorContas == null) {
						valorContas = new BigDecimal(0);
					}

					// Valor Guias Pagmto
					BigDecimal valorGuias = (BigDecimal) retorno[17];
					if (valorGuias == null) {
						valorGuias = new BigDecimal(0);
					}

					// Valor Servicos Indiretos
					BigDecimal valorServicosIndiretos = this.repositorioGerencialCadastro
							.pesquisarObterValorServicosIndiretos(
									(Integer) retorno[0],
									" debitoacobrar.lancamentoItemContabil.id not in (2,3)");
					if (valorServicosIndiretos == null) {
						valorServicosIndiretos = new BigDecimal(0);
					}

					// Valor creditos
					BigDecimal valorCreditos = (BigDecimal) retorno[18];
					if (valorCreditos == null) {
						valorCreditos = new BigDecimal(0);
					}

					// Valor Servicos Indiretos
					BigDecimal valorAcrescimoImpontualidade = (BigDecimal) retorno[25];
					if(valorAcrescimoImpontualidade == null){
						valorAcrescimoImpontualidade = new BigDecimal(0);
					}

					if (valorAcrescimoImpontualidade == null) {
						valorAcrescimoImpontualidade = new BigDecimal(0);
					}

					// Valor Sancoes
					BigDecimal valorSancoes = this.repositorioGerencialCadastro
							.pesquisarObterValorServicosIndiretos(
									(Integer) retorno[0],
									" debitoacobrar.lancamentoItemContabil.id = 3");
					if (valorSancoes == null) {
						valorSancoes = new BigDecimal(0);
					}

					// Valor creditos
					BigDecimal valorDescontoAcrescimo = (BigDecimal) retorno[19];
					if (valorDescontoAcrescimo == null) {
						valorDescontoAcrescimo = new BigDecimal(0);
					}

					// Valor Desconto Inatividade
					BigDecimal valorDescontoInatividade = (BigDecimal) retorno[20];
					if (valorDescontoInatividade == null) {
						valorDescontoInatividade = new BigDecimal(0);
					}

					// Valor Desconto Antiguidade
					BigDecimal valorDescontoAntiguidade = (BigDecimal) retorno[21];
					if (valorDescontoAntiguidade == null) {
						valorDescontoAntiguidade = new BigDecimal(0);
					}

					// Valor total Parcelamento
					BigDecimal valorTotalParcelamento = (BigDecimal) retorno[22];
					if (valorTotalParcelamento == null) {
						valorTotalParcelamento = new BigDecimal(0);
					}
					// Ano Mes Referencia
					Integer anoMesRef = (Integer) retorno[23];

					
					// valor debito a cobrar Total
					BigDecimal valorDebitoACobrarTotal = (BigDecimal) retorno[24]; 
					if (valorDebitoACobrarTotal == null) {
						valorDebitoACobrarTotal = new BigDecimal(0);
					}
					// valor debito a cobrar Acrescimos
					BigDecimal valorDebitoACobrarAcrescimos = this.repositorioGerencialCadastro
					.pesquisarObterValorServicosIndiretos(
							(Integer) retorno[0],
							" debitoacobrar.lancamentoItemContabil.id = 2");
					if (valorDebitoACobrarAcrescimos == null) {
						valorDebitoACobrarAcrescimos = new BigDecimal(0); 
					}
					
					// valor debito a cobrar Acrescimos
					BigDecimal valorDebitoACobrarReligSancoes = new BigDecimal(0); //(BigDecimal) retorno[0]; 
					if (valorDebitoACobrarReligSancoes == null) {
						valorDebitoACobrarReligSancoes = new BigDecimal(0);
					}
					// valor debitos a cobrar parcelamentos
					BigDecimal valorDebitoACobrarParcelamentos = (BigDecimal) retorno[26];
					if (valorDebitoACobrarParcelamentos == null){
						valorDebitoACobrarParcelamentos = new BigDecimal(0); 
					}
					// valor entrada
					BigDecimal valorEntrada = (BigDecimal) retorno[27];
					if (valorEntrada == null){
						valorEntrada = new BigDecimal(0);
					}
					// valor juros parcelamento
					BigDecimal valorJurosParcelamento = (BigDecimal) retorno[28];
					if (valorJurosParcelamento == null) {
						valorJurosParcelamento = new BigDecimal(0);
					}
					// quantidade total de parcelas
					Short quantidadeTotalParcelas = (Short) retorno[29];
					if (quantidadeTotalParcelas == null){
						quantidadeTotalParcelas = 0;
					}
					
					Integer consumoTarifa = (Integer) retorno[30];
					if(consumoTarifa == null){
						consumoTarifa = 0;
					}
					
					Short numeroParcelamentoConsecutivos = (Short) retorno[31];
					if(numeroParcelamentoConsecutivos == null){
						numeroParcelamentoConsecutivos = new Short("0");
					}
					
					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do im�vel
					Integer idImovel = (Integer) retorno[1]; // Codigo do

					// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
					String indicadorHidrometroString = new Integer(
							getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
					Short indicadorHidrometro = new Short(indicadorHidrometroString);
					// Caso indicador de hidr�metro esteja nulo
					// Seta 2(dois) = N�O no indicador de
					// hidr�metro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}					
					
					
					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					
					ResumoParcelamentoHelper helper = new ResumoParcelamentoHelper(
							(Integer) retorno[2], // Gerencia Regional
							(Integer) retorno[3], // Unidade de negocio
							(Integer) retorno[4], // Elo
							(Integer) retorno[5], // Localidade
							(Integer) retorno[6], // Id Setor Comercial
							(Integer) retorno[7], // id Rota
							(Integer) retorno[8], // Id Quadra
							(Integer) retorno[9], // Codigo do Setor Comercial
							(Integer) retorno[10], // Numero da quadra
							(Integer) retorno[11], // Perfil do imovel
							(Integer) retorno[12], // Situacao da ligacao da
							// agua
							(Integer) retorno[13], // Situacao da ligacao do
							// esgoto
							(Integer) retorno[14], // Perfil ligacao agua
							(Integer) retorno[15], // Perfil ligacao esgoto
							(Integer) retorno[30], // Tarifa Consumo
							indicadorHidrometro,   // Indicador Hidrometro
							numeroParcelamentoConsecutivos, // Numero Parcelamentos Consecutivos
							(Short) retorno[32]);//codigo rota
					
					// Pesquisamos a esfera de poder do cliente responsavel
					helper
							.setIdEsfera(this.repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
					// Pesquisamos o tipo de cliente responsavel do imovel
					helper
							.setIdTipoClienteResponsavel(this.repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					Categoria categoria = null;
					categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(categoria.getId(),
										idImovel);

						if (subcategoria != null) {
							helper.setIdSubCategoria(subcategoria.getComp_id()
									.getSubcategoria().getId());
						}
					}

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsfera().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);

						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdEsfera(clienteTemp.getClienteTipo()
									.getEsferaPoder().getId());
						}
					}
					if (helper.getIdTipoClienteResponsavel().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdTipoClienteResponsavel(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// se ja existe um objeto igual a ele entao soma os
					// valores e as quantidades ja existentes.
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals
					if (listaSimplificadaParcelamento.contains(helper)) {
						int posicao = listaSimplificadaParcelamento
								.indexOf(helper);
						ResumoParcelamentoHelper jaCadastrado = (ResumoParcelamentoHelper) listaSimplificadaParcelamento
								.get(posicao);
						// Somatorios
						// Contas
						jaCadastrado.setQtdContas(jaCadastrado.getQtdContas()
								+ quantidadeContas);
						// Guias
						jaCadastrado.setQtdGuias(jaCadastrado.getQtdGuias()
								+ quantidadeGuias);
						// Servicos Indiretos
						jaCadastrado
								.setQtdServicosIndiretos((short) (jaCadastrado
										.getQtdServicosIndiretos() + quantidadeServicosIndiretos));
						// qtdParcelamentos
						Short p = (((Integer) (jaCadastrado
								.getQtdParcelamento() + 1)).shortValue());
						jaCadastrado.setQtdParcelamento(p);
						// Valor Contas
						jaCadastrado.setVlContas(jaCadastrado.getVlContas()
								.add(valorContas));
						// Valor Guias
						jaCadastrado.setVlGuias(jaCadastrado.getVlGuias().add(
								valorGuias));
						// Valor Servicos Indiretos
						jaCadastrado.setVlServicosIndiretos(jaCadastrado
								.getVlServicosIndiretos().add(valorGuias));
						// Valor Creditos
						jaCadastrado.setVlCreditoRealizar(jaCadastrado
								.getVlCreditoRealizar().add(valorCreditos));
						// Valor Acrescimo Impontualidade
						jaCadastrado.setVlAcrescimoImpontualidade(jaCadastrado
								.getVlAcrescimoImpontualidade().add(
										valorAcrescimoImpontualidade));
						// Valor Sancoes
						jaCadastrado.setVlSancoes(jaCadastrado.getVlSancoes()
								.add(valorSancoes));
						// Valor Desconto Acrescimo
						jaCadastrado.setVlDescontoAcrescimo(jaCadastrado
								.getVlDescontoAcrescimo().add(
										valorDescontoAcrescimo));
						// Valor Desconto Inatividade
						jaCadastrado.setVlDescontoInatividade(jaCadastrado
								.getVlDescontoInatividade().add(
										valorDescontoInatividade));
						// Valor Desconto Antiguidade
						jaCadastrado.setVlDescontoAntiguidade(jaCadastrado
								.getVlDescontoAntiguidade().add(
										valorDescontoAntiguidade));
						// Valor Total Parcelamento
						jaCadastrado.setVlTotalParcelamento(jaCadastrado
								.getVlTotalParcelamento().add(
										valorTotalParcelamento));
						// AnoMesReferencia
						jaCadastrado.setAnoMesReferencia(jaCadastrado
								.getAnoMesReferencia());
						
						// valor debitos a cobrar total 
						jaCadastrado.setVlDebitosACobrarTotal(jaCadastrado
								.getVlDebitosACobrarTotal().add(valorDebitoACobrarTotal));
						
						// valor debitos a cobrar acrescimos 
						jaCadastrado.setVlDebitosACobrarAcrescimos(jaCadastrado
								.getVlDebitosACobrarAcrescimos().add(valorDebitoACobrarAcrescimos));
						
						// valor debitos a cobrar religsancoes
						jaCadastrado.setVlDebitosACobrarReligSancoes(jaCadastrado
								.getVlDebitosACobrarReligSancoes().add(valorDebitoACobrarReligSancoes));
						
						// valor debitos a cobrar parcelamentos
						jaCadastrado.setVlDebitosACobrarParcelamentos(jaCadastrado
								.getVlDebitosACobrarParcelamentos().add(valorDebitoACobrarParcelamentos));

						// valor entrada
						jaCadastrado.setVlEntrada(jaCadastrado
								.getVlEntrada().add(valorEntrada));
						
						// valor juros parcelamento
						jaCadastrado.setVlJurosParcelamento(jaCadastrado
								.getVlJurosParcelamento().add(valorJurosParcelamento));

						Integer qtdMediaParcelas = jaCadastrado.getQtdMediaParcelas() + quantidadeTotalParcelas;
						// media parcelas
						jaCadastrado.setQtdMediaParcelas(qtdMediaParcelas.shortValue());                                    

						Integer qtdTotalParcelas = jaCadastrado.getQtdTotalParcelas() + quantidadeTotalParcelas;
						// total parcelas
						jaCadastrado.setQtdTotalParcelas(qtdTotalParcelas.shortValue());
						
					} else {
						// Somatorios
						// Contas
						helper.setQtdContas(helper.getQtdContas().intValue()
								+ quantidadeContas);
						// Guias
						helper.setQtdGuias(helper.getQtdGuias().intValue()
								+ quantidadeGuias);

						Short s = (((Integer) (helper.getQtdServicosIndiretos() + quantidadeServicosIndiretos))
								.shortValue());
						// Servicos Indiretos
						helper.setQtdServicosIndiretos(s);
						Short p = (((Integer) (helper.getQtdParcelamento() + 1))
								.shortValue());
						// qtdParcelamentos
						helper.setQtdParcelamento(p);
						// vlContas
						helper.setVlContas(helper.getVlContas()
								.add(valorContas));
						// vlGuias
						helper.setVlGuias(helper.getVlGuias().add(valorGuias));
						// vlServicosPrestados
						helper.setVlServicosIndiretos(helper
								.getVlServicosIndiretos().add(
										valorServicosIndiretos));
						// vlCreditos
						helper.setVlCreditoRealizar(helper
								.getVlCreditoRealizar().add(valorCreditos));
						// vlAcrescimoImpontualidade
						helper.setVlAcrescimoImpontualidade(helper
								.getVlAcrescimoImpontualidade().add(
										valorAcrescimoImpontualidade));
						// vlSancoes
						helper.setVlSancoes(helper.getVlSancoes().add(
								valorSancoes));
						// vlDescontoAcrescimo
						helper.setVlDescontoAcrescimo(helper
								.getVlDescontoAcrescimo().add(
										valorDescontoAcrescimo));

						// vlDescontoInatividade
						helper.setVlDescontoInatividade(helper
								.getVlDescontoInatividade().add(
										valorDescontoInatividade));
						// vlDescontoAntiguidade
						helper.setVlDescontoAntiguidade(helper
								.getVlDescontoAntiguidade().add(
										valorDescontoAntiguidade));
						// vlTotalParcalamento
						helper.setVlTotalParcelamento(helper
								.getVlTotalParcelamento().add(
										valorTotalParcelamento));
						listaSimplificadaParcelamento.add(helper);
						// AnoMesReferencia
						helper.setAnoMesReferencia(anoMesRef);
						
						// valor debitos a cobrar total 
						helper.setVlDebitosACobrarTotal(valorDebitoACobrarTotal);
						
						// valor debitos a cobrar acrescimos 
						helper.setVlDebitosACobrarAcrescimos(valorDebitoACobrarAcrescimos);
						
						// valor debitos a cobrar religsancoes
						helper.setVlDebitosACobrarReligSancoes(valorDebitoACobrarReligSancoes);
						
						// valor debitos a cobrar parcelamentos
						helper.setVlDebitosACobrarParcelamentos(valorDebitoACobrarParcelamentos);

						// valor entrada
						helper.setVlEntrada(valorEntrada);
						
						// valor juros parcelamento
						helper.setVlJurosParcelamento(valorJurosParcelamento);

						// media parcelas
						helper.setQtdMediaParcelas(quantidadeTotalParcelas);

						// total parcelas
						helper.setQtdTotalParcelas(quantidadeTotalParcelas);
					}
				}
			}

			/**
			 * para todas as ImovelResumoParcelamentoHelper cria
			 * ResumoParcelamento
			 */
			for (int i = 0; i < listaSimplificadaParcelamento.size(); i++) {
				ResumoParcelamentoHelper helper = (ResumoParcelamentoHelper) listaSimplificadaParcelamento
						.get(i);

				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();

				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}

				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}

				// Localidade
				GLocalidade localidade = null;
				if (helper.getIdLocalidade() != null) {
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdElo());
				}

				// Setor comercial
				GSetorComercial setorComercial = null;
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}

				// Rota
				GRota rota = null;
				if (helper.getIdRota() != null) {
					rota = new GRota();
					rota.setId(helper.getIdRota());
					rota.setCodigoRota(helper.getCodigoRota());
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}

				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}

				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}

				// Tipo do cliente responsavel
				GClienteTipo clienteTipo = null;
				if (helper.getIdTipoClienteResponsavel() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoClienteResponsavel());
				}

				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}

				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}

				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null) {
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId(helper.getIdPerfilLigacaoAgua());
				}

				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null) {
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto
							.setId(helper.getIdPerfilLigacaoEsgoto());
				}

				// Quantidade Contas
				Integer qtdContas = helper.getQtdContas();

				// Quantidade Parcelamentos
				Short qtdParcelamentos = helper.getQtdParcelamento();

				// Quantidade Guias
				Integer qtdGuias = helper.getQtdGuias();

				
				// Valor das Contas
				BigDecimal vlContas = helper.getVlContas();

				// Valor Guias Pagmto
				BigDecimal vlGuias = helper.getVlGuias();

				

				// Valor creditos
				BigDecimal vlCreditos = helper.getVlCreditoRealizar();

				// Valor Servicos Indiretos
				BigDecimal vlAcrescimoImpontualidade = helper
						.getVlAcrescimoImpontualidade();

				// Valor Sancoes
				//BigDecimal vlSancoes = helper.getVlSancoes();

				// Valor creditos
				BigDecimal vlDescontoAcrescimo = helper
						.getVlDescontoAcrescimo();

				// Valor Desconto Inatividade
				BigDecimal vlDescontoInatividade = helper
						.getVlDescontoInatividade();

				// Valor Desconto Antiguidade
				BigDecimal vlDescontoAntiguidade = helper
						.getVlDescontoAntiguidade();

				// Ultima Alteracao
				Date ultimaAlteracao = new Date();

				// valor debitos a cobrar total 
				BigDecimal vlDebitosACobrarTotal         = helper.getVlDebitosACobrarTotal();   

				// valor debitos a cobrar acrescimos 
				BigDecimal vlDebitosACobrarAcrescimos    = helper.getVlDebitosACobrarAcrescimos();

				// valor debitos a cobrar religsancoes
				BigDecimal vlDebitosACobrarReligSancoes  = helper.getVlDebitosACobrarReligSancoes();

				// valor debitos a cobrar parcelamentos
				BigDecimal vlDebitosACobrarParcelamentos = helper.getVlDebitosACobrarParcelamentos();

				// valor entrada
				BigDecimal vlEntrada                     = helper.getVlEntrada();

				// valor juros parcelamento
				BigDecimal vlJurosParcelamento           = helper.getVlJurosParcelamento();

				// media parcelas
				Integer qtdMediaParcelas                 = new BigDecimal((helper.getQtdMediaParcelas()/qtdParcelamentos)).intValue();

				// total parcelas
				Integer qtdTotalParcelas                 = helper.getQtdTotalParcelas().intValue(); 
				
				//  Perfil da ligacao de agua
				GConsumoTarifa consumoTarifa = null;
				if (helper.getConsumoTarifa() != null) {
					consumoTarifa = new GConsumoTarifa();
					consumoTarifa.setId(helper.getConsumoTarifa());
				}
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				Short numeroParcelamentoConsecutivosShort = helper.getNumeroParcelamentoConsecutivos();
				
				Integer	numeroParcelamentoConsecutivos = null;
				if(numeroParcelamentoConsecutivosShort != null){
					numeroParcelamentoConsecutivos = numeroParcelamentoConsecutivosShort.intValue(); 
				}else{
					numeroParcelamentoConsecutivos = 0;
				}

				// Criamos um resumo do parcelamento (***UFA***)
				UnResumoParcelamento resumoParcelamentoGrava = new UnResumoParcelamento(
						anoMesReferencia, 			codigoSetorComercial, 		numeroQuadra,
						qtdContas, 					vlContas, 					vlGuias, 
						vlCreditos, 				vlDescontoAcrescimo, 
						qtdGuias, 					 
						vlDescontoInatividade,		vlAcrescimoImpontualidade,
						ultimaAlteracao, 			qtdParcelamentos, 			
						vlDescontoAntiguidade, 		subcategoria, 				clienteTipo,
						ligacaoAguaSituacao, 		quadra, 					ligacaoEsgotoSituacao,
						gerenciaRegional, 			setorComercial, 			perfilLigacaoAgua,
						imovelPerfil, 				unidadeNegocio, 			elo, 
						localidade,					perfilLigacaoEsgoto, 		esferaPoder, 
						categoria, 					rota,   					vlJurosParcelamento,  
						vlEntrada	, 				vlDebitosACobrarParcelamentos, 
						vlDebitosACobrarTotal,	 	vlDebitosACobrarAcrescimos, 
						vlDebitosACobrarReligSancoes,							qtdTotalParcelas, 
						qtdMediaParcelas, 			consumoTarifa, 				indicadorHidrometro,
						numeroParcelamentoConsecutivos, helper.getCodigoRota());						
				

				// Adicionamos a lista que deve ser inserida
				listaResumoParcelamento.add(resumoParcelamentoGrava);
			}

			this.getControladorBatch().inserirColecaoObjetoParaBatchGerencial(
					(Collection) listaResumoParcelamento);

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido

			System.out.println(" ERRO NA LOCALIDADE " + idLocalidade);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
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
	 * M�todo que gera o resumo de Metas da CAERN
	 * 
	 * [UC0623] - Gerar Resumo de Metas
	 * 
	 * @author S�vio Luiz
	 * @date 20/07/2007
	 * 
	 */
	public void gerarResumoMetas(int idSetor, Date dataInicial, Date dataFinal,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

		Integer anoMesReferencia = Util.recuperaAnoMesDaData(dataInicial);

		try {

			List imoveisResumoConsumoAgua = this.repositorioGerencialCadastro
					.pesquisarDadosResumoMetas(idSetor, dataInicial, dataFinal);

			List<ResumoMetasHelper> listaSimplificada = new ArrayList();
			Imovel imovelObterQtdEconomia = null;

			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoConsumoAgua.size(); i++) {
				Object obj = imoveisResumoConsumoAgua.get(i);

				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					// Pesquisamos as quantidades de economia do imovel corrente
					imovelObterQtdEconomia = new Imovel();
					imovelObterQtdEconomia.setId((Integer) retorno[0]);
					Integer quantidadeEconomia = this.getControladorImovel()
							.obterQuantidadeEconomias(imovelObterQtdEconomia);
					if (quantidadeEconomia == null) {
						quantidadeEconomia = 0;
					}

					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					ResumoMetasHelper helper = new ResumoMetasHelper(
							(Integer) retorno[1], // Gerencia Regional
							(Integer) retorno[2], // Unidade de negocio
							(Integer) retorno[3], // Localidade
							(Integer) retorno[4], // Elo
							(Integer) retorno[5], // Id Setor Comercial
							(Integer) retorno[6], // id Rota
							(Integer) retorno[7], // Id Quadra
							(Integer) retorno[8], // Codigo do Setor Comercial
							(Integer) retorno[9], // Numero da quadra
							(Integer) retorno[10],// Perfil do imovel
							(Integer) retorno[11],// Situacao da ligacao da
							// agua
							(Integer) retorno[12],// Situacao da ligacao do
							// esgoto
							(Integer) retorno[13],// Perfil da ligacao de agua
							(Integer) retorno[14],// Perfil da ligacao de
							// esgoto
							(Date) retorno[15],// Data Liga��o
							(Date) retorno[16],// Data Supress�o
							(Date) retorno[17],// Data Corte
							(Date) retorno[18],// Data Religa��o
							(Date) retorno[19],// Perfil da ligacao de esgoto
							(Integer) retorno[20]// Hidrometro instalado
					);

					Integer idImovel = (Integer) retorno[0]; // Codigo do
					// imovel que
					// esta sendo
					// processado

					// seta o tipo de data
					helper.setarTipoData(dataInicial, dataFinal);

					// Pesquisamos a esfera de poder do cliente responsavel

					helper
							.setIdEsferaPoder(this.repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));

					// Pesquisamos o tipo de cliente responsavel do imovel

					helper
							.setIdClienteTipo(this.repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do im�vel
					Categoria categoria = null;
					categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoriaComCodigoGrupo(
										categoria.getId(), idImovel);
						if (subcategoria != null) {
							helper.setIdSubCategoria(subcategoria.getComp_id()
									.getSubcategoria().getId());
							if (subcategoria.getComp_id().getSubcategoria() != null
									&& subcategoria.getComp_id()
											.getSubcategoria()
											.getCodigoGrupoSubcategoria() != null) {
								helper.setCodigoGrupoSubcategoria(subcategoria
										.getComp_id().getSubcategoria()
										.getCodigoGrupoSubcategoria());
							}
						}
					}

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsferaPoder().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);

						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);

						if (clienteTemp != null
								&& clienteTemp.getClienteTipo() != null
								&& clienteTemp.getClienteTipo()
										.getEsferaPoder() != null) {
							helper.setIdEsferaPoder(clienteTemp
									.getClienteTipo().getEsferaPoder().getId());
						}
					}
					if (helper.getIdClienteTipo().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdClienteTipo(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// se ja existe um objeto igual a ele entao soma as
					// ligacoes e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals ( excecao
					// quantidadeEconomia, quantidadeLigacoes )
					if (listaSimplificada.contains(helper)) {

						int posicao = listaSimplificada.indexOf(helper);
						ResumoMetasHelper jaCadastrado = (ResumoMetasHelper) listaSimplificada
								.get(posicao);

						// Somamos as liga��es cadastradas
						jaCadastrado
								.setQuantidadeLigacoesCadastradas(jaCadastrado
										.getQuantidadeLigacoesCadastradas()
										.intValue() + 1);
						// Somamos as economias
						jaCadastrado.setQuantidadeEconomias(jaCadastrado
								.getQuantidadeEconomias().intValue()
								+ quantidadeEconomia);

						int[] tiposData = helper.getTipoData();

						for (int tipoData : tiposData) {

							if (tipoData != 0) {

								switch (tipoData) {
								case ResumoMetasHelper.INIDCADOR_DATA_SUPRESSAO:
									// Somamos as liga��es suprimidas
									jaCadastrado
											.setQuantidadeLigacoesSuprimidas(jaCadastrado
													.getQuantidadeLigacoesSuprimidas()
													.intValue() + 1);
									break;

								case ResumoMetasHelper.INIDCADOR_DATA_CORTE:
									// Somamos as liga��es suprimidas
									jaCadastrado
											.setQuantidadeLigacoesCortadas(jaCadastrado
													.getQuantidadeLigacoesCortadas()
													.intValue() + 1);
									break;

								case ResumoMetasHelper.INIDCADOR_DATA_LIGACAO:
									jaCadastrado = setarDadosResumoMetasHelper(
											jaCadastrado, idImovel,
											anoMesReferencia);
									break;

								case ResumoMetasHelper.INIDCADOR_DATA_RELIGACAO:
									jaCadastrado = setarDadosResumoMetasHelper(
											jaCadastrado, idImovel,
											anoMesReferencia);
									break;
								case ResumoMetasHelper.INIDCADOR_DATA_RESTABELECIMENTO:
									jaCadastrado = setarDadosResumoMetasHelper(
											jaCadastrado, idImovel,
											anoMesReferencia);
									break;

								}
							}
						}

					} else {

						// Somamos as liga��es cadastradas
						helper
								.setQuantidadeLigacoesCadastradas(helper
										.getQuantidadeLigacoesCadastradas()
										.intValue() + 1);
						// Somamos as economias
						helper.setQuantidadeEconomias(helper
								.getQuantidadeEconomias().intValue()
								+ quantidadeEconomia);

						int[] tiposData = helper.getTipoData();

						for (int tipoData : tiposData) {

							if (tipoData != 0) {

								switch (tipoData) {
								case ResumoMetasHelper.INIDCADOR_DATA_SUPRESSAO:
									// Somamos as liga��es suprimidas
									helper
											.setQuantidadeLigacoesSuprimidas(helper
													.getQuantidadeLigacoesSuprimidas()
													.intValue() + 1);
									break;

								case ResumoMetasHelper.INIDCADOR_DATA_CORTE:
									// Somamos as liga��es suprimidas
									helper.setQuantidadeLigacoesCortadas(helper
											.getQuantidadeLigacoesCortadas()
											.intValue() + 1);
									break;

								case ResumoMetasHelper.INIDCADOR_DATA_LIGACAO:
									helper = setarDadosResumoMetasHelper(
											helper, idImovel, anoMesReferencia);
									break;

								case ResumoMetasHelper.INIDCADOR_DATA_RELIGACAO:
									helper = setarDadosResumoMetasHelper(
											helper, idImovel, anoMesReferencia);
									break;

								case ResumoMetasHelper.INIDCADOR_DATA_RESTABELECIMENTO:
									helper = setarDadosResumoMetasHelper(
											helper, idImovel, anoMesReferencia);
									break;

								}
							}
						}

						listaSimplificada.add(helper);

					}
				}
			}

			for (int i = 0; i < listaSimplificada.size(); i++) {
				this.repositorioGerencialCadastro.inserirResumoMetas(
						anoMesReferencia, (ResumoMetasHelper) listaSimplificada
								.get(i));
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			System.out.println(" ERRO NO SETOR COMERCIAL " + idSetor);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}

	/**
	 * M�todo que gera o resumo de Metas da CAERN
	 * 
	 * [UC0570] - Gerar Resumo do consumo de agua
	 * 
	 * @author S�vio Luiz
	 * @date 20/07/2007
	 * 
	 */
	private ResumoMetasHelper setarDadosResumoMetasHelper(
			ResumoMetasHelper resumoMetasHelper, Integer idImovel,
			Integer amArrecadacao) {

		try {
			// Somar ao acumulador de quantidade de liga��es ativas + 1
			resumoMetasHelper.setQuantidadeLigacoesAtivas(resumoMetasHelper
					.getQuantidadeLigacoesAtivas() + 1);

			boolean existeDebito = getControladorFaturamento()
					.verificarDebitoMais3MesesFaturaEmAberto(amArrecadacao,idImovel);
			if (existeDebito) {
				// Somar ao acumulador de quantidade de liga��es ativas + 1
				resumoMetasHelper
						.setQuantidadeLigacoesAtivasDebito3M(resumoMetasHelper
								.getQuantidadeLigacoesAtivasDebito3M() + 1);
			}

			if (resumoMetasHelper.getIdHidrometroInstalado() != null
					&& !resumoMetasHelper.getIdHidrometroInstalado().equals("")) {
				// Somar ao acumulador de quantidade de liga��es ativas + 1
				resumoMetasHelper
						.setQuantidadeLigacoesConsumoMedido(resumoMetasHelper
								.getQuantidadeLigacoesConsumoMedido() + 1);

				Integer consumoFaturadoSemLigacao = getControladorMicromedicao()
						.pesquisarConsumoFaturado(idImovel,
								LigacaoTipo.LIGACAO_AGUA, null,null, amArrecadacao);

				if (consumoFaturadoSemLigacao != null
						&& consumoFaturadoSemLigacao >= 0
						&& consumoFaturadoSemLigacao <= 5) {
					// Somar ao acumulador de quantidade de liga��es ativas + 1
					resumoMetasHelper
							.setQuantidadeLigacoesConsumoAte5M3(resumoMetasHelper
									.getQuantidadeLigacoesConsumoAte5M3() + 1);
				}

				Integer consumoFaturadoImovelAtual = getControladorMicromedicao()
						.pesquisarConsumoFaturado(idImovel,
								LigacaoTipo.LIGACAO_AGUA,
								ConsumoTipo.MEDIA_IMOVEL,
								ConsumoTipo.MEDIA_HIDROMETRO, amArrecadacao);
				Integer consumoFaturadoImovelMenos1 = getControladorMicromedicao()
						.pesquisarConsumoFaturado(idImovel,
								LigacaoTipo.LIGACAO_AGUA,
								ConsumoTipo.MEDIA_IMOVEL,
								ConsumoTipo.MEDIA_HIDROMETRO, Util.subtraiAteSeisMesesAnoMesReferencia(amArrecadacao,1));
				
				Integer consumoFaturadoImovelMenos2 = getControladorMicromedicao()
						.pesquisarConsumoFaturado(idImovel,
								LigacaoTipo.LIGACAO_AGUA,
								ConsumoTipo.MEDIA_IMOVEL,
								ConsumoTipo.MEDIA_HIDROMETRO, Util.subtraiAteSeisMesesAnoMesReferencia(amArrecadacao,2));
				Integer consumoFaturadoImovelMenos3 = getControladorMicromedicao()
						.pesquisarConsumoFaturado(idImovel,
								LigacaoTipo.LIGACAO_AGUA,
								ConsumoTipo.MEDIA_IMOVEL,
								ConsumoTipo.MEDIA_HIDROMETRO, Util.subtraiAteSeisMesesAnoMesReferencia(amArrecadacao,3));
				if (consumoFaturadoImovelAtual != null
						&& !consumoFaturadoImovelAtual.equals("") &&
						consumoFaturadoImovelMenos1 != null
						&& !consumoFaturadoImovelMenos1.equals("") &&
						consumoFaturadoImovelMenos2 != null
						&& !consumoFaturadoImovelMenos2.equals("") &&
						consumoFaturadoImovelMenos3 != null
						&& !consumoFaturadoImovelMenos3.equals("")) {
					// Somar ao acumulador de quantidade de liga��es ativas
					// + 1
					resumoMetasHelper
							.setQuantidadeLigacoesConsumoMedio(resumoMetasHelper
									.getQuantidadeLigacoesConsumoMedio() + 1);
				}

			} else {
				// Somar ao acumulador de quantidade de liga��es ativas + 1
				resumoMetasHelper
						.setQuantidadeLigacoesConsumoNaoMedido(resumoMetasHelper
								.getQuantidadeLigacoesConsumoNaoMedido() + 1);
			}

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			System.out.println(" ERRO IMOVEL " + idImovel);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			throw new EJBException(ex);
		}

		return resumoMetasHelper;
	}

	/**
	 * M�todo que gera o resumo da pendencia
	 * 
	 * [UC0335] - Gerar Resumo do Parcelamento
	 * 
	 * @author Bruno Barros
	 * @date 19/07/2007
	 * 
	 */

	// public void gerarResumoPendencia( int idSetor,
	// int idFuncionalidadeIniciada) throws ControladorException {
	//		
	// int idUnidadeIniciada = 0;
	//
	// // -------------------------
	// //
	// // Registrar o in�cio do processamento da Unidade de
	// // Processamento
	// // do Batch
	// //
	// // -------------------------
	// idUnidadeIniciada = getControladorBatch()
	// .iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
	// UnidadeProcessamento.LOCALIDADE, idSetor);
	//		
	// try {
	//			
	// this.gerarResumoPendenciaContaGerencia(idSetor);
	//		
	// getControladorBatch().encerrarUnidadeProcessamentoBatch(
	// idUnidadeIniciada, false);
	//
	// } catch (Exception ex) {
	// // Este catch serve para interceptar qualquer exce��o que o processo
	// // batch venha a lan�ar e garantir que a unidade de processamento do
	// // batch ser� atualizada com o erro ocorrido
	//			
	// System.out.println( " ERRO NO SETOR " + idSetor);
	//
	// ex.printStackTrace();
	// getControladorBatch().encerrarUnidadeProcessamentoBatch(
	// idUnidadeIniciada, true);
	//
	// throw new EJBException(ex);
	// }
	// }
	/**
	 * Gera a primeira parte do resumo de pendencias [SB0001A]
	 * 
	 * @author Bruno Barros
	 * @date 19/07/2007
	 * 
	 */
	// private void gerarResumoPendenciaContaGerencia( int idSetor ) throws
	// ControladorException, ErroRepositorioException {
	// System.out.println( " ***RESUMO DE PENDENCIAS DAS CONTAS POR GERENCIA -
	// SETOR " + idSetor + "***");
	//		
	// /**
	// * O sistema seleciona as contas pendentes ( a partir
	// * da tabela CONTA com CNTA_AMREFERENCIACONTA <
	// * PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS
	// * e ( DCST_IDATUAL = 0 ou (DCST_IDATUAL = (1,2) e
	// * CNTA_AMREFERENCIACONTABIL < PARM_AMREFENRECIAFATURAMENTO
	// * ou (DCST_IDATUAL = (3,4,5) e CNTA_AMREFERENCIACONTABIL >
	// * PARM_AMREFERENCIAFATURAMENTO */
	// List contasPendentes =
	// this.repositorioGerencialCadastro.getContasPendentes( idSetor );
	//		
	// // Varremos
	// for ( int i = 0; i < contasPendentes.size(); i++ ){
	// Object obj = contasPendentes.get(i);
	//
	// if (obj instanceof Object[]) {
	// Object[] linha = (Object[]) obj;
	//
	// ResumoPendenciaContasGerenciaHelper helper = new
	// ResumoPendenciaContasGerenciaHelper(
	// (Integer) linha[0], // Gerencia Regional
	// (Integer) linha[1], // Unidade Negocio
	// (Integer) linha[2], // Elo
	// (Integer) linha[3], // Localidade
	// (Integer) linha[4], // Setor Comercial
	// (Integer) linha[5], // Rota
	// (Integer) linha[6], // Quadra
	// (Integer) linha[7], // Codigo Setor Comercial
	// (Integer) linha[8], // Numero da quadra
	// (Integer) linha[9], // Perfil do imovel
	// (Integer) linha[10], // Situacao Ligacao Agua
	// (Integer) linha[11], // Situacao Ligacao Esgoto
	// (Integer) linha[12], // Perfil liga��o Agua
	// (Integer) linha[13], // Perfil liga��o Esgoto
	// (Integer) linha[14], // Volume Fixado Agua
	// (Integer) linha[15], // Volume Fixado Agua
	// (Integer) linha[16], // Volume Fixado Esgoto
	// new Integer(1),
	// (Integer) linha[17], // Ano mes de referencia documento
	// (Integer) linha[18]); // Referencia do vencimento da conta
	//				
	// Integer idImovel = ( Integer )linha[19]; // Codigo do imovel que esta
	// sendo processado
	//				
	// // Pesquisamos a esfera de poder do cliente responsavel
	// helper.setIdEsferaPoder(
	// this.repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(
	// idImovel ) );
	// // Pesquisamos o tipo de cliente responsavel do imovel
	// helper.setIdTipoClienteResponsavel(
	// this.repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(
	// idImovel ) );
	//				
	// // pesquisando a categoria
	// // [UC0306] - Obtter principal categoria do im�vel
	// Categoria categoria = null;
	// categoria = this.getControladorImovel()
	// .obterPrincipalCategoriaImovel(idImovel);
	// if (categoria != null) {
	// helper.setIdPrincipalCategoriaImovel(categoria.getId());
	//					
	// // Pesquisando a principal subcategoria
	// ImovelSubcategoria subcategoria =
	// this.getControladorImovel().obterPrincipalSubcategoria(
	// categoria.getId(), idImovel );
	//					
	// if ( subcategoria != null ){
	// helper.setIdPrincipalSubCategoriaImovel(
	// subcategoria.getComp_id().getSubcategoria().getId() );
	// }
	// }
	//				
	// // Verificamos se a esfera de poder foi encontrada
	// // [FS0002] Verificar existencia de cliente responsavel
	// if ( helper.getIdEsferaPoder().equals( 0 ) ){
	// Imovel imovel = new Imovel();
	// imovel.setId(idImovel);
	// Cliente clienteTemp =
	// this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
	// if ( clienteTemp != null ){
	// helper.setIdEsferaPoder(
	// clienteTemp.getClienteTipo().getEsferaPoder().getId() );
	// }
	// }
	// if ( helper.getIdTipoClienteResponsavel().equals( 0 ) ){
	// Imovel imovel = new Imovel();
	// imovel.setId(idImovel);
	// Cliente clienteTemp =
	// this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
	// if ( clienteTemp != null ){
	// helper.setIdTipoClienteResponsavel( clienteTemp.getClienteTipo().getId()
	// );
	// }
	// }
	// }
	// }
	// }
	/**
	 * M�todo que gera o resumo de Metas da CAERN
	 * 
	 * [UC0623] - Gerar Resumo de Metas
	 * 
	 * @author S�vio Luiz
	 * @date 20/07/2007
	 * 
	 */
	public void gerarResumoMetasAcumulado(int idSetor,
			Integer anoMesArrecadacao, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

		try {

			List imoveisResumoConsumoAgua = this.repositorioGerencialCadastro
					.pesquisarDadosResumoMetasAcumulado(idSetor);

			List<ResumoMetasHelper> listaSimplificada = new ArrayList();

			Imovel imovelObterQtdEconomia = null;

			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoConsumoAgua.size(); i++) {
				Object obj = imoveisResumoConsumoAgua.get(i);

				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					// Pesquisamos as quantidades de economia do imovel corrente
					imovelObterQtdEconomia = new Imovel();
					imovelObterQtdEconomia.setId((Integer) retorno[0]);
					Integer quantidadeEconomia = this.getControladorImovel()
							.obterQuantidadeEconomias(imovelObterQtdEconomia);
					if (quantidadeEconomia == null) {
						quantidadeEconomia = 0;
					}

					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					ResumoMetasHelper helper = new ResumoMetasHelper(
							(Integer) retorno[1], // Gerencia Regional
							(Integer) retorno[2], // Unidade de negocio
							(Integer) retorno[3], // Localidade
							(Integer) retorno[4], // Elo
							(Integer) retorno[5], // Id Setor Comercial
							(Integer) retorno[6], // id Rota
							(Integer) retorno[7], // Id Quadra
							(Integer) retorno[8], // Codigo do Setor Comercial
							(Integer) retorno[9], // Numero da quadra
							(Integer) retorno[10],// Perfil do imovel
							(Integer) retorno[11],// Situacao da ligacao da
							// agua
							(Integer) retorno[12],// Situacao da ligacao do
							// esgoto
							(Integer) retorno[13],// Perfil da ligacao de agua
							(Integer) retorno[14],// Perfil da ligacao de
							// esgoto
							(Date) retorno[15],// Data Liga��o
							(Date) retorno[16],// Data Supress�o
							(Date) retorno[17],// Data Corte
							(Date) retorno[18],// Data Religa��o
							(Date) retorno[19],// Perfil da ligacao de esgoto
							(Integer) retorno[20]// Hidrometro instalado
					);

					Integer idImovel = (Integer) retorno[0]; // Codigo do
					// imovel que
					// esta sendo
					// processado

					// Pesquisamos a esfera de poder do cliente responsavel

					helper
							.setIdEsferaPoder(this.repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));

					// Pesquisamos o tipo de cliente responsavel do imovel

					helper
							.setIdClienteTipo(this.repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do im�vel
					Categoria categoria = null;
					categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoriaComCodigoGrupo(
										categoria.getId(), idImovel);
						if (subcategoria != null) {
							helper.setIdSubCategoria(subcategoria.getComp_id()
									.getSubcategoria().getId());
							if (subcategoria.getComp_id().getSubcategoria() != null
									&& subcategoria.getComp_id()
											.getSubcategoria()
											.getCodigoGrupoSubcategoria() != null) {
								helper.setCodigoGrupoSubcategoria(subcategoria
										.getComp_id().getSubcategoria()
										.getCodigoGrupoSubcategoria());
							}
						}
					}

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsferaPoder().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);

						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);

						if (clienteTemp != null
								&& clienteTemp.getClienteTipo() != null
								&& clienteTemp.getClienteTipo()
										.getEsferaPoder() != null) {
							helper.setIdEsferaPoder(clienteTemp
									.getClienteTipo().getEsferaPoder().getId());
						}
					}
					if (helper.getIdClienteTipo().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdClienteTipo(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// se ja existe um objeto igual a ele entao soma as
					// ligacoes e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals ( excecao
					// quantidadeEconomia, quantidadeLigacoes )
					if (listaSimplificada.contains(helper)) {

						int posicao = listaSimplificada.indexOf(helper);
						ResumoMetasHelper jaCadastrado = (ResumoMetasHelper) listaSimplificada
								.get(posicao);

						// Somamos as liga��es cadastradas
						if ((jaCadastrado.getIdLigacaoAguaSituacao() != null && (!jaCadastrado
								.getIdLigacaoAguaSituacao().equals(
										LigacaoAguaSituacao.POTENCIAL) && !jaCadastrado
								.getIdLigacaoAguaSituacao().equals(
										LigacaoAguaSituacao.FACTIVEL)))
								|| (jaCadastrado.getIdLigacaoEsgotoSituacao() != null && (!jaCadastrado
										.getIdLigacaoEsgotoSituacao()
										.equals(LigacaoEsgotoSituacao.POTENCIAL) && !jaCadastrado
										.getIdLigacaoEsgotoSituacao().equals(
												LigacaoEsgotoSituacao.FACTIVEL)))) {
							jaCadastrado
									.setQuantidadeLigacoesCadastradas(jaCadastrado
											.getQuantidadeLigacoesCadastradas()
											.intValue() + 1);

							// Somamos as economias
							jaCadastrado.setQuantidadeEconomias(jaCadastrado
									.getQuantidadeEconomias().intValue()
									+ quantidadeEconomia);
						}
						// Somamos as liga��es cadastradas
						if (jaCadastrado.getIdLigacaoAguaSituacao() != null
								&& (jaCadastrado.getIdLigacaoAguaSituacao()
										.equals(LigacaoAguaSituacao.SUPR_PARC)
										|| jaCadastrado
												.getIdLigacaoAguaSituacao()
												.equals(
														LigacaoAguaSituacao.SUPR_PARC_PEDIDO) || jaCadastrado
										.getIdLigacaoAguaSituacao().equals(
												LigacaoAguaSituacao.SUPRIMIDO))) {

							// Somamos as liga��es suprimidas
							jaCadastrado
									.setQuantidadeLigacoesSuprimidas(jaCadastrado
											.getQuantidadeLigacoesSuprimidas()
											.intValue() + 1);
						}

						// Somamos as liga��es cadastradas
						if (jaCadastrado.getIdLigacaoAguaSituacao() != null
								&& (jaCadastrado.getIdLigacaoAguaSituacao()
										.equals(LigacaoAguaSituacao.CORTADO))) {
							// Somamos as liga��es suprimidas
							jaCadastrado
									.setQuantidadeLigacoesCortadas(jaCadastrado
											.getQuantidadeLigacoesCortadas()
											.intValue() + 1);
						}
						// Somamos as liga��es cadastradas
						if (jaCadastrado.getIdLigacaoAguaSituacao() != null
								&& (jaCadastrado.getIdLigacaoAguaSituacao()
										.equals(LigacaoAguaSituacao.LIGADO))
								|| (jaCadastrado.getIdLigacaoEsgotoSituacao() != null && (jaCadastrado
										.getIdLigacaoEsgotoSituacao()
										.equals(LigacaoEsgotoSituacao.LIGADO)))) {

							jaCadastrado = setarDadosResumoMetasHelper(
									jaCadastrado, idImovel, anoMesArrecadacao);
						}

					} else {

						// Somamos as liga��es cadastradas
						if ((helper.getIdLigacaoAguaSituacao() != null && (!helper
								.getIdLigacaoAguaSituacao().equals(
										LigacaoAguaSituacao.POTENCIAL) && !helper
								.getIdLigacaoAguaSituacao().equals(
										LigacaoAguaSituacao.FACTIVEL)))
								|| (helper.getIdLigacaoEsgotoSituacao() != null && (!helper
										.getIdLigacaoEsgotoSituacao()
										.equals(LigacaoEsgotoSituacao.POTENCIAL) && !helper
										.getIdLigacaoEsgotoSituacao().equals(
												LigacaoEsgotoSituacao.FACTIVEL)))) {
							helper.setQuantidadeLigacoesCadastradas(helper
									.getQuantidadeLigacoesCadastradas()
									.intValue() + 1);

							// Somamos as economias
							helper.setQuantidadeEconomias(helper
									.getQuantidadeEconomias().intValue()
									+ quantidadeEconomia);
						}
						// Somamos as liga��es cadastradas
						if (helper.getIdLigacaoAguaSituacao() != null
								&& (helper.getIdLigacaoAguaSituacao().equals(
										LigacaoAguaSituacao.SUPR_PARC)
										|| helper
												.getIdLigacaoAguaSituacao()
												.equals(
														LigacaoAguaSituacao.SUPR_PARC_PEDIDO) || helper
										.getIdLigacaoAguaSituacao().equals(
												LigacaoAguaSituacao.SUPRIMIDO))) {

							// Somamos as liga��es suprimidas
							helper.setQuantidadeLigacoesSuprimidas(helper
									.getQuantidadeLigacoesSuprimidas()
									.intValue() + 1);
						}

						// Somamos as liga��es cadastradas
						if (helper.getIdLigacaoAguaSituacao() != null
								&& (helper.getIdLigacaoAguaSituacao()
										.equals(LigacaoAguaSituacao.CORTADO))) {
							// Somamos as liga��es suprimidas
							helper
									.setQuantidadeLigacoesCortadas(helper
											.getQuantidadeLigacoesCortadas()
											.intValue() + 1);
						}
						// Somamos as liga��es cadastradas
						if ((helper.getIdLigacaoAguaSituacao() != null && (helper
								.getIdLigacaoAguaSituacao()
								.equals(LigacaoAguaSituacao.LIGADO)))
								|| (helper.getIdLigacaoEsgotoSituacao() != null && (helper
										.getIdLigacaoEsgotoSituacao()
										.equals(LigacaoEsgotoSituacao.LIGADO)))) {

							helper = setarDadosResumoMetasHelper(helper,
									idImovel, anoMesArrecadacao);
						}

						listaSimplificada.add(helper);

					}
				}
			}

			for (int i = 0; i < listaSimplificada.size(); i++) {
				this.repositorioGerencialCadastro.inserirResumoMetasAcumulado(
						anoMesArrecadacao,
						(ResumoMetasHelper) listaSimplificada.get(i));
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------n

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o
			// processo
			// batch venha a lan�ar e garantir que a unidade de processamento
			// do
			// batch ser� atualizada com o erro ocorrido
			System.out.println(" ERRO NO SETOR COMERCIAL " + idSetor);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}

	public Integer obterConsumoMinimoCategoria( Imovel imovel, Categoria categoria )
		throws ControladorException{
		
		Integer consumoMinimoCategoria = 0;
		
		// Selecionamos o consumo m�nimo dessa faixa
		try {
			// [UC0108] - Obter Quantidade de Economias por Categoria
			Short quantidadeEconomias = this.repositorioImovel.pesquisarObterQuantidadeEconomias( imovel, categoria );			
			Integer consumoMinimo = this.repositorioGerencialCadastro.getConsumoMinimoImovelCategoria( imovel.getId(), categoria.getId() );
			consumoMinimoCategoria = quantidadeEconomias * consumoMinimo;
		} catch (ErroRepositorioException e) {
			new ControladorException("erro.sistema", e);				
		}
		
		return consumoMinimoCategoria;
	}
	
	/**
	 * M鴯do que gera resumo indicadores de comercializa磯
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da Comercializa磯
	 * 
	 * @author Rafael Corrꡍ
	 * @date 06/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresComercializacao(int idFuncionalidadeIniciada)
			throws ControladorException {
		
		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);
		
		try {
			Integer anoMesResumoLigacaoEconomia = this.repositorioGerencialCadastro
					.pesquisarMaiorAnoMesResumoLigacaoEconomia();
			Integer anoMesResumoIndicadorLigacaoEconomia = this.repositorioGerencialCadastro
					.pesquisarMaiorAnoMesResumoIndicadorLigacaoEconomia();
			
//			if (anoMesResumoLigacaoEconomia == null
//					|| (anoMesResumoIndicadorLigacaoEconomia != null && anoMesResumoLigacaoEconomia
//							.intValue() <= anoMesResumoIndicadorLigacaoEconomia
//							.intValue())) {
//				throw new ControladorException(
//						"atencao.ano.mes.indicador.maior.igual.ano.mes.resumo", null, "da Comercializa磯");
//			}
			
			if (anoMesResumoLigacaoEconomia != null
					&& (anoMesResumoIndicadorLigacaoEconomia == null || anoMesResumoLigacaoEconomia
							.intValue() > anoMesResumoIndicadorLigacaoEconomia
							.intValue())) {
				/**
				 * @autor Adriana Muniz
				 * @date 14/02/2012
				 * 
				 * Soma um ao ano/mes referꮣia passado com parametro para o metodo de atualiza磯.
				 **/
				anoMesResumoIndicadorLigacaoEconomia = Util.somaUmMesAnoMesReferencia(anoMesResumoIndicadorLigacaoEconomia);
				
				this.repositorioGerencialCadastro.atualizarDadosResumoIndicadorLigacaoEconomia(anoMesResumoIndicadorLigacaoEconomia);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
		
	}
	
	
	/**
	 * M�todo que gera o resumo do coleta esgoto
	 * 
	 * [UC0573] - Gerar Resumo do coleta esgoto
	 * 
	 * @author Marcio Roberto, Bruno Barros, Ivan Sergio
	 * @date 18/09/2007, 07/02/2008, 29/07/2008, 15/08/2008
	 * @alteracao: 29/07/2008 - SubCategoria deve sempre ser obtida pela Categoria do Imovel.
	 * 			   15/08/2008 - Alteracao no calculo do Volume Faturado.
	 * 
	 */
	public void gerarResumoColetaEsgoto(int idSetor, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

		Integer anoMesReferencia = null;
		Integer idImovel = null;
		
		try {
			Integer anoMesFaturamento = getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento();
			
			List imoveisResumoColetaEsgoto = this.repositorioGerencialCadastro
					.getHistoricosColetaEsgoto(idSetor, anoMesFaturamento);

			List<ResumoColetaEsgotoHelper> listaSimplificada = new ArrayList();
			//List<UnResumoColetaEsgoto> listaResumoConsumoAgua = new ArrayList();

			Imovel imovelObterQtdEconomia = null;
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		repositorioGerencialCadastro.excluirResumo( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				UnResumoColetaEsgoto.class.getName(), 
	   				"referencia", 
	   				idSetor,
	   				false );				
			
			
			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoColetaEsgoto.size(); i++) {
				Object obj = imoveisResumoColetaEsgoto.get(i);
				
				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					ResumoColetaEsgotoHelper helper = new ResumoColetaEsgotoHelper(
							(Integer) retorno[1], // Gerencia Regional
							(Integer) retorno[2], // Unidade de negocio
							(Integer) retorno[3], // Localidade
							(Integer) retorno[4], // Elo
							(Integer) retorno[5], // Id Setor Comercial
							(Integer) retorno[6], // id Rota
							(Integer) retorno[7], // Id Quadra
							(Integer) retorno[8], // Codigo do Setor Comercial
							(Integer) retorno[9], // Numero da quadra
							(Integer) retorno[10],// Perfil do imovel
							(Integer) retorno[11],// Situacao da ligacao da agua
							(Integer) retorno[12],// Situacao da ligacao do esgoto
							(Integer) retorno[13],// Perfil da ligacao de agua
							(Integer) retorno[14],// Perfil da ligacao de esgoto
							(Integer) retorno[15], // Consumo tipo
							(Short)   retorno[24] //codigo rota
					);
					// Imovel
					idImovel = (Integer) retorno[0];
					
					// Id da Conta
					Integer idConta = ( Integer ) retorno[22];
					Conta conta = new Conta();
					conta.setId( idConta );
					
					FiltroConta filtroConta = new FiltroConta();
					filtroConta.adicionarParametro( new ParametroSimples(FiltroConta.ID, idConta));
					
					Collection contas = Fachada.getInstancia().pesquisar(filtroConta, Conta.class.getName());
					
					Integer motivoSituacaoFaturamento = null;
					
					if ( contas != null && !contas.isEmpty() ){
						
						conta = (Conta)Util.retonarObjetoDeColecao(contas);
						
						motivoSituacaoFaturamento = this.
								repositorioGerencialCadastro.getMotivoSituacaoFaturamento(
										idImovel, conta.getReferencia());
						
					}
					
					helper.setMotivoFaturamentoSituacao(motivoSituacaoFaturamento);
					
					//Indicador de Faturamento
					helper.setIndicadorFaturamento((Short)retorno[25]);
					
					//Situa磯 de Faturamento
					helper.setFaturamentoSituacao((Integer)retorno[26]);
					
					// Quantidade de Economias
					Integer quantidadeEconomia = 0;					
					
					// Verificamos se existe economias pela conta
					if ( idConta != null && !idConta.equals(0)){
						Collection<Categoria> colQuantEco = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria( conta );
						
						if ( colQuantEco != null && colQuantEco.size() > 0  ){
							Iterator iteQuanEco = colQuantEco.iterator();							
							
							while ( iteQuanEco.hasNext() ){
								Categoria catTemp = (Categoria) iteQuanEco.next();
								
								quantidadeEconomia += catTemp.getQuantidadeEconomiasCategoria();								
							}
						}
					}
					
					// Caso n㯠obtenha as quantidades pela conta, pesquisamos as quantidades de economia do imovel corrente					
					if ( quantidadeEconomia == 0 ){										
						imovelObterQtdEconomia = new Imovel();
						imovelObterQtdEconomia.setId((Integer) retorno[0]);
						quantidadeEconomia = this.getControladorImovel()
								.obterQuantidadeEconomias(imovelObterQtdEconomia);
						if (quantidadeEconomia == null) {
							quantidadeEconomia = 0;
						}
					}
					

					// Consumo de Esgoto
					Integer consumoEsgoto = null;
					// Verificamos se o consumo de esgoto esta na conta ou no consumoHistorico
					if( idConta != null && !idConta.equals(0)){
						consumoEsgoto = (Integer) retorno[23];						
					} else {
					    consumoEsgoto = (Integer) retorno[16];
					}
					
					// Pesquisamos a esfera de poder do cliente responsavel
					helper.setIdEsferaPoder(
							this.repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
					
					// Pesquisamos o tipo de cliente responsavel do imovel
					helper.setIdClienteTipo(
							this.repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					// pesquisando a categoria
					Categoria categoria = null;
					Categoria categoriaConta = null;
					
					// [UC0632] - Obter Principal Categoria da Conta
					// Verificamos se existe conta para este imovel
					if ( idConta != null && !idConta.equals(0)){
						categoriaConta = this.getControladorImovel().obterPrincipalCategoriaConta( idConta );
					}
					
					// Pesquisamos a categoria do imovel
					Categoria categoriaImovel = this.getControladorImovel()
						.obterPrincipalCategoriaImovel(idImovel);
					
					// Verificamos se devemos guardar a categoria da conta ou do imovel
					if ( categoriaConta == null ){
						categoria = categoriaImovel;
					} else {
						categoria = categoriaConta;
					}

					// Pesquisamos a subcategoria
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						// Lembrando que a categoria usada para pesquisar a subcategoria e sempre a do imovel
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(categoriaImovel.getId(),
										idImovel);
						
						helper.setIdSubCategoria(subcategoria.getComp_id()
								.getSubcategoria().getId());
					}
					
					/*
					// pesquisando a categoria
					Categoria categoria = null;
					
					// [UC0632] - Obter Principal Categoria da Conta
					// Verificamos se existe conta para este imovel
					if ( idConta != null ){
						categoria = this.getControladorImovel().obterPrincipalCategoriaConta( idConta );
					}
					
					// Caso n㯠encontre a categoria pela conta, seleciona pelo imovel
					if ( categoria == null ){					
						// [UC0306] - Obtter principal categoria do imovel					
						categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);
					}
					
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(categoria.getId(),
										idImovel);

						if (subcategoria != null) {
							helper.setIdSubCategoria(subcategoria.getComp_id()
									.getSubcategoria().getId());
						}
					}
					*/

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsferaPoder().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdEsferaPoder(clienteTemp
									.getClienteTipo().getEsferaPoder().getId());
						}
					}
					if (helper.getIdClienteTipo().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdClienteTipo(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// Ano M�s Referencia.
					anoMesReferencia = (Integer) retorno[17];

					// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
					String indicadorHidrometroString = new Integer(
							getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
					Short indicadorHidrometro = new Short(indicadorHidrometroString);
					// Caso indicador de hidr�metro esteja nulo
					// Seta 2(dois) = N�O no indicador de
					// hidr�metro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}
					helper.setIndicadorHidrometro(indicadorHidrometro);
					
					// Verificamos a existencia de poco no imovel
					Integer idPoco = this.repositorioGerencialCadastro
							.verificarExistenciaPoco(idImovel);
					if(idPoco == null){
						idPoco = 0;
					}
//					helper.setPoco(idPoco.shortValue());

					// Existencia de Hidrometro no Po�o.	
					//Integer hidrometroInstaladoPoco = (Integer) retorno[20];
					//helper.setMedidoFonteAlternativa(hidrometroInstaladoPoco.shortValue());

					Imovel imovelTemp = new Imovel();
					imovelTemp.setId(idImovel);
					imovelTemp = this.getControladorImovel()
							.pesquisarImovel(imovelTemp.getId());
					
					//***************************************************
					// Calcula o Volume Faturado
					//***************************************************
					Integer valorMinimoEsgoto = 0;
					
					if (idConta != null && !idConta.equals(0)) {
						valorMinimoEsgoto = 
							this.repositorioGerencialCadastro.pesquisarConsumoMinimoEsgotoConta(idConta);
						
						if (valorMinimoEsgoto == null) {
							valorMinimoEsgoto = 0;
						}
					}else {
						valorMinimoEsgoto = 
							getControladorMicromedicao().obterConsumoMinimoLigacao(imovelTemp, null);
					}
					
					Integer voFaturado = valorMinimoEsgoto;
					short icColetaEsgotoExcedente = 2;
					helper.setIndicadorColetaEsgotoExcedente(icColetaEsgotoExcedente);
					
					if(consumoEsgoto > valorMinimoEsgoto){
						voFaturado = consumoEsgoto;
						icColetaEsgotoExcedente = 1;
						helper.setIndicadorColetaEsgotoExcedente(icColetaEsgotoExcedente);
					}
					
					if(voFaturado == null ){
						voFaturado = 0;
					}
					helper.setVoFaturado(voFaturado);
					//***************************************************

					helper.setQuantidadeColetaEsgoto(consumoEsgoto);
					
					// Setamos o valor do excedente
					helper.setVolumeExcedente(helper
							.getQuantidadeColetaEsgoto()
							- valorMinimoEsgoto);
 
					// Caso o valor excedente seja negativo, seramos, pois nao
					// houve excedente.
					if (helper.getVolumeExcedente() <= 0) {
						helper.setVolumeExcedente(0);
					}

					//short icColetaEsgotoExcedente = 2;
					//helper.setIndicadorColetaEsgotoExcedente(icColetaEsgotoExcedente);
					//if(helper.getVolumeExcedente() > 0){
					//	icColetaEsgotoExcedente = 1;
					//	helper.setIndicadorColetaEsgotoExcedente(icColetaEsgotoExcedente);
					//}

					//	Obtemos o percentual de esgoto 
					//System.out.println(retorno[21].getClass().getName());
					float percentualEsgoto = 0f; 
					percentualEsgoto = ((Double) retorno[19]).floatValue();
					if(percentualEsgoto == 0){
						percentualEsgoto = 0.0f;
					}
					helper.setPcEsgoto(percentualEsgoto);
						
					// percentual coleta
					BigDecimal pcColeta = (BigDecimal) retorno[21];
					//pcColeta = ((Double) retorno[21]).floatValue();
					//if(pcColeta == 0){
					//	pcColeta = 0.0f;
					//}
					helper.setPcColeta(pcColeta.floatValue());
					
					
					// Indicador volume minimo esgoto
					//Integer icVlMinimo = (Integer) retorno[18];
//					helper.setIcVlMinimoEsgoto(icVlMinimo.shortValue());
					
					// helper
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						ResumoColetaEsgotoHelper jaCadastrado = (ResumoColetaEsgotoHelper) listaSimplificada
								.get(posicao);

						// Somamos as economias
						jaCadastrado.setQuantidadeEconomias(jaCadastrado
								.getQuantidadeEconomias().intValue()
								+ quantidadeEconomia);

						// Incrementamos as ligacoes
						jaCadastrado.setQuantidadeLigacoes(jaCadastrado
								.getQuantidadeLigacoes() + 1);

						// Acumulamos o consumo de agua
						jaCadastrado.setQuantidadeColetaEsgoto(jaCadastrado
								.getQuantidadeColetaEsgoto().intValue()
								+ helper.getQuantidadeColetaEsgoto());
						
						// Acumulamos o consumo Excedente
						jaCadastrado.setVolumeExcedente(jaCadastrado
								.getVolumeExcedente().intValue()
								+ helper.getVolumeExcedente().intValue());
						
						// Acumulamos o consumo faturado
						jaCadastrado.setVoFaturado(jaCadastrado
								.getVoFaturado().intValue()
								+ helper.getVoFaturado().intValue());
						
					} else {
						// Incrementamos as ligacoes
						helper.setQuantidadeLigacoes(helper
								.getQuantidadeLigacoes() + 1);
						
						// Somamos as economias
						helper.setQuantidadeEconomias(helper
								.getQuantidadeEconomias().intValue()
								+ quantidadeEconomia);

						// Ano Mes Referencia.
						helper.setAnoMesReferencia(anoMesReferencia);
						
						listaSimplificada.add(helper);
					}
				}
			}
			
			imoveisResumoColetaEsgoto.clear();
			imoveisResumoColetaEsgoto = null;

			for (int i = 0; i < listaSimplificada.size(); i++) {
				this.repositorioGerencialCadastro.inserirResumoColetaEsgoto(
						anoMesReferencia,
						(ResumoColetaEsgotoHelper) listaSimplificada.get(i));
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			System.out.println(" ERRO NO SETOR " + idSetor + " COLETA ESGOTO - IMOVEL " + idImovel);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}
	
	public void excluirResumoGerencial(Integer anoMesFaturamento, String string, String string2, int idSetor) throws ControladorException {
		try {
			this.repositorioGerencialCadastro.excluirResumo(anoMesFaturamento, string, string2, idSetor, false);
		} catch (ErroRepositorioException e) {
			new ControladorException("erro.sistema", e);				
		}
		
				
	}
	
    public void excluirResumoGerencial ( 
     		  int anomesreferencia , 
     		  String resumoGerencial, 
     		  String resumoCampoAnoMes,
     		  String resumoCampoUnidadeProcessamento,
     		  int idUnidadeProcessamento ) throws ControladorException{
		try {
			this.repositorioGerencialCadastro.excluirResumoGerencial(
		     		  anomesreferencia , 
		     		  resumoGerencial, 
		     		  resumoCampoAnoMes,
		     		  resumoCampoUnidadeProcessamento,
		     		  idUnidadeProcessamento );
		} catch (ErroRepositorioException e) {
			new ControladorException("erro.sistema", e);				
		}
	}
	
	
	public void excluirResumoGerencialSQL(Integer anoMesFaturamento, String string, String string2, int idSetor) throws ControladorException {
		try {
			this.repositorioGerencialCadastro.excluirResumoSQL(anoMesFaturamento, string, string2, idSetor, false);
		} catch (ErroRepositorioException e) {
			new ControladorException("erro.sistema", e);				
		}		
	}

	public void excluirResumoGerencialC(Integer anoMesFaturamento, String string, String string2, String string3, int idCampo) throws ControladorException {
		try {
			this.repositorioGerencialCadastro.excluirResumoGerencialC(anoMesFaturamento, string, string2, string3, idCampo);
		} catch (ErroRepositorioException e) {
			new ControladorException("erro.sistema", e);				
		}
		
				
	}

	/**
	 * M鴯do que gera o resumo das liga絥s e economias por ano
	 * 
	 * Gerar Resumo das Liga絥s/Economias Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 29/04/2010
	 *  [Quantidade de liga絥s cortadas mes  e	Quantidade de religa絥s no mes].
	 */
	public void gerarResumoLigacoesEconomiasPorAno(int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in���o do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
		
		//Variᶥis para a pagina磯 da pesquisa de Imovel por Grupo Faturamento
		// ========================================================================
		boolean flagTerminou = false;
		final int quantidadeRegistros = 10000;
		int numeroIndice = 0;
		// ========================================================================
		
		try {
			
			Integer anoMesReferencia = getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento();
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		repositorioGerencialCadastro.excluirResumoSQL( 
	   				anoMesReferencia, "cadastro.un_resumo_ligacao_economia_ref_2010", "rele_amreferencia", idSetor, false );
			
			while(!flagTerminou){
			
				List imoveisResumoLigacaoEconomias = this.repositorioGerencialCadastro
						.getImoveisResumoLigacaoEconomiasPorAno(idSetor,numeroIndice,
								quantidadeRegistros);
																																																																															
				Imovel imovelObterQtdEconomia = null;				
				
				// pra cada objeto obter a categoria
				// caso ja tenha um igual soma a quantidade de economias e a
				// quantidade de ligacoes
				for (int i = 0; i < imoveisResumoLigacaoEconomias.size(); i++) {
	
					Object obj = imoveisResumoLigacaoEconomias.get(i);
	
					if (obj instanceof Object[]) {
						Object[] retorno = (Object[]) obj;
						
						//******************************************************
						// Alteracao - CRC937
						//******************************************************
						Integer quantidadeEconomia = null;
						Short indicadorCondominio = (Short) retorno[20];
						
						if (indicadorCondominio == 1) {
							quantidadeEconomia = 1;
						}else {
							// Pesquisamos as quantidades de economia do imovel corrente
							imovelObterQtdEconomia = new Imovel();
							imovelObterQtdEconomia.setId((Integer) retorno[0]);
							quantidadeEconomia = this.getControladorImovel().obterQuantidadeEconomias(imovelObterQtdEconomia);
							if (quantidadeEconomia == null) {
								quantidadeEconomia = 0;
							}
						}
						//******************************************************
						
						// Montamos um objeto de resumo, com as informacoes do
						// retorno
						ResumoLigacaoEconomiaPorAnoHelper helper = new ResumoLigacaoEconomiaPorAnoHelper(
								(Integer) retorno[1], // Gerencia Regional
								(Integer) retorno[2], // Unidade de negocio
								(Integer) retorno[3], // Localidade
								(Integer) retorno[4], // Elo
								(Integer) retorno[5], // Id Setor Comercial
								(Integer) retorno[6], // Codigo do Setor Comercial
								(Integer) retorno[7], // Perfil do imovel
								(Integer) retorno[8],// Situacao da ligacao da agua
								(Integer) retorno[9],// Situacao da ligacao do esgoto
								(Integer) retorno[10],// Perfil da ligacao de agua
								(Integer) retorno[11],// Perfil da ligacao de esgoto
								(Integer) retorno[12],// Possue hidrometro instalado ?
								(Integer) retorno[13],// Possue hidrometro instalado no poco ?
								(Integer) retorno[14],// Possue volume minimo de agua fixado ?
								(Integer) retorno[15],// Possue volume minimo de esgoto fixado ?
								(Integer) retorno[16],// Possue poco
								(Integer) retorno[19]); // Tipo de Tarifa de Consumo
	
						Integer idImovel = (Integer) retorno[0]; // Codigo do
						// imovel que
						// esta sendo
						// processado
	
						// Pesquisamos a esfera de poder do cliente responsavel
						helper
								.setIdEsfera(this.repositorioGerencialCadastro
										.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper
								.setIdTipoClienteResponsavel(this.repositorioGerencialCadastro
										.pesquisarTipoClienteClienteResponsavelImovel(idImovel));
	
						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
	
						Categoria categoria = null;
						categoria = this.getControladorImovel()
								.obterPrincipalCategoriaImovel(idImovel);
						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());
	
							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria = this
									.getControladorImovel()
									.obterPrincipalSubcategoria(categoria.getId(),
											idImovel);
	
							if (subcategoria != null) {
								helper.setIdSubCategoria(subcategoria.getComp_id()
										.getSubcategoria().getId());
							}
						}
	
						// Verificamos se a esfera de poder foi encontrada
						// [FS0002] Verificar existencia de cliente responsavel
						if (helper.getIdEsfera().equals(0)) {
							Imovel imovel = new Imovel();
							imovel.setId(idImovel);
							Cliente clienteTemp = this.getControladorImovel()
									.consultarClienteUsuarioImovel(imovel);
							if (clienteTemp != null) {
								helper.setIdEsfera(clienteTemp.getClienteTipo()
										.getEsferaPoder().getId());
							}
						}
						if (helper.getIdTipoClienteResponsavel().equals(0)) {
							Imovel imovel = new Imovel();
							imovel.setId(idImovel);
							Cliente clienteTemp = this.getControladorImovel()
									.consultarClienteUsuarioImovel(imovel);
							if (clienteTemp != null) {
								helper.setIdTipoClienteResponsavel(clienteTemp
										.getClienteTipo().getId());
							}
						}
						
						Boolean incrementaQtdLigacoesNovasAgua = false;
						Boolean incrementaQtdLigacoesNovasEsgoto = false;
						
						if ( retorno[21] != null && Util.recuperaAnoMesDaData( (Date) retorno[21] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue()  ){
							//jaCadastrado.setQtdLigacoesNovasAgua(jaCadastrado.getQtdLigacoesNovasAgua() + 1 );
							incrementaQtdLigacoesNovasAgua = true;
						}
						
						if ( retorno[22] != null && Util.recuperaAnoMesDaData( (Date) retorno[22] ).intValue() ==
							 this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao().intValue()  ){
							//jaCadastrado.setQtdLigacoesNovasEsgoto(jaCadastrado.getQtdLigacoesNovasEsgoto() + 1 );
							incrementaQtdLigacoesNovasEsgoto = true;
						}
						
						//Atualiza os dados na base, se os dados ja estiverem cadastrados
						Integer atualizarResumo = 
							this.repositorioGerencialCadastro
								.atualizarResumoLigacoesEconomiasPorAno(helper.getIdGerenciaRegional(),
										helper.getIdUnidadeNegocio(),
										helper.getIdLocalidade(),
										helper.getIdElo(),
										helper.getIdSetorComercial(),
										helper.getCodigoSetorComercial(),
										helper.getIdPerfilImovel(),
										helper.getIdEsfera(),
										helper.getIdTipoClienteResponsavel(),
										helper.getIdSituacaoLigacaoAgua(),
										helper.getIdSituacaoLigacaoEsgoto(),
										helper.getIdCategoria(),
										helper.getIdSubCategoria(),
										helper.getIdPerfilLigacaoAgua(),
										helper.getIdPerfilLigacaoEsgoto(),
										helper.getIdHidrometro().shortValue(),
										helper.getIdHidrometroPoco().shortValue(),
										helper.getIdVolFixadoAgua().shortValue(),
										helper.getIdVolFixadoEsgoto().shortValue(),
										helper.getIdPoco().shortValue(),
										helper.getIdTipoTarifaConsumo(),
										quantidadeEconomia,
										anoMesReferencia,
										incrementaQtdLigacoesNovasAgua,
										incrementaQtdLigacoesNovasEsgoto
										);
						
						//Se nenhuma linha foi atualizada 頩nserida uma nova linha na tabela.
						if (atualizarResumo != null && atualizarResumo == 0){
							
							//Somamos as economias
							helper.setQtdEconomias(helper.getQtdEconomias()
									.intValue()
									+ quantidadeEconomia);
	
							// Incrementamos as ligacoes
							helper.setQtdLigacoes(helper.getQtdLigacoes() + 1);
							
							if ( retorno[21] != null && Util.recuperaAnoMesDaData( (Date) retorno[21] ).intValue() ==
								 this.getControladorUtil().pesquisarParametrosDoSistema()
								 .getAnoMesArrecadacao().intValue()  ){
								helper.setQtdLigacoesNovasAgua( 1 );
							}
							
							if ( retorno[22] != null && Util.recuperaAnoMesDaData( (Date) retorno[22] ).intValue() ==
								 this.getControladorUtil().pesquisarParametrosDoSistema()
								 .getAnoMesArrecadacao().intValue()  ){
								helper.setQtdLigacoesNovasEsgoto( 1 );
							}
							
							this.repositorioGerencialCadastro.
								inserirResumoLigacoesEconomiasPorAno(anoMesReferencia, helper);
							
						}
						
					}
				}
				
				/**
				 * Incrementa o n? do indice da p᧩na磯
				 */
				numeroIndice = numeroIndice + quantidadeRegistros;

				/**
				 * Caso a cole磯 de imoveis retornados for menor que a
				 * quantidade de registros seta a flag indicando que a
				 * pagina磯 terminou.
				 */
				if (imoveisResumoLigacaoEconomias == null || 
						imoveisResumoLigacaoEconomias.size() < quantidadeRegistros) {

					flagTerminou = true;
				}

				if (imoveisResumoLigacaoEconomias != null) {
					imoveisResumoLigacaoEconomias.clear();
					imoveisResumoLigacaoEconomias = null;
				}	
				
			}//Fim da Paginacao

			// --------------------------------------------------------
			//
			// Registrar o fim da execu磯 da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido

			System.out.println(" ERRO NO SETOR" + idSetor);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}

	/**
     * M鴯do que gera o resumo do consumo de agua por ano
     * 
     *Gerar Resumo do consumo de agua por ano
     * 
     * @author Fernando Fontelles
     * @date 24/05/10
     * 
     */
    public void gerarResumoConsumoAguaPorAno(int idSetor, int idFuncionalidadeIniciada)
            throws ControladorException {

        int idUnidadeIniciada = 0;

        // -------------------------
        //
        // Registrar o in�cio do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch()
                .iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
                        UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

        Integer anoMesReferencia = null;
        Integer imovelErro = null;

        try {
            
            List imoveisResumoConsumoAgua = this.repositorioGerencialCadastro
                    .getHistoricosConsumoAguaPorAno(idSetor);

            List<ResumoConsumoAguaPorAnoHelper> listaSimplificada = new ArrayList();

            Imovel imovelObterQtdEconomia = null;
            
            //FS0001 - Verificar existencia de dados para o ano/mes referencia informado
            repositorioGerencialCadastro.excluirResumoSQL( 
                    getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
                    "micromedicao.un_resumo_consumo_agua_ref_2010", 
                    "reca_amreferencia", 
                    idSetor,
                    false);             

            // pra cada objeto obter a categoria
            // caso ja tenha um igual soma a quantidade de economias e a
            // quantidade de ligacoes
            for (int i = 0; i < imoveisResumoConsumoAgua.size(); i++) {
                Object obj = imoveisResumoConsumoAgua.get(i);
                
                //System.out.println( i + " de " + imoveisResumoConsumoAgua.size() + " do Setor " + idSetor );

                if (obj instanceof Object[]) {
                    Object[] retorno = (Object[]) obj;

                    // Montamos um objeto de resumo, com as informacoes do
                    // retorno
                    ResumoConsumoAguaPorAnoHelper helper = new ResumoConsumoAguaPorAnoHelper(
                            (Integer) retorno[1], // Gerencia Regional
                            (Integer) retorno[2], // Unidade de negocio
                            (Integer) retorno[3], // Localidade
                            (Integer) retorno[4], // Elo
                            (Integer) retorno[5], // Id Setor Comercial
//                            (Integer) retorno[6], // id Rota
//                            (Integer) retorno[7], // Id Quadra
                            (Integer) retorno[6], // Codigo do Setor Comercial
//                            (Integer) retorno[9], // Numero da quadra
                            (Integer) retorno[7],// Perfil do imovel
                            (Integer) retorno[8],// Situacao da ligacao da agua
                            (Integer) retorno[9],// Situacao da ligacao do esgoto
                            (Integer) retorno[10],// Perfil da ligacao de agua
                            (Integer) retorno[11],// Perfil da ligacao de esgoto
                            (Integer) retorno[12] // Consumo tipo 
                        );
                    
                    Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
                    imovelErro = idImovel;
                    Integer idConta = (Integer) retorno[17]; // C?o da conta que esta sendo processada
                    Conta conta = new Conta();
                    conta.setId( idConta );                 
                    
                    // Quantidade de Economias
                    Integer quantidadeEconomia = 0;                 
                    
                    // Verificamos se existe economias pela conta
                    if ( idConta != null ){
                        Collection<Categoria> colQuantEco = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria( conta );
                        
                        if ( colQuantEco != null && colQuantEco.size() > 0  ){
                            Iterator iteQuanEco = colQuantEco.iterator();                           
                            
                            while ( iteQuanEco.hasNext() ){
                                Categoria catTemp = (Categoria) iteQuanEco.next();
                                
                                quantidadeEconomia += catTemp.getQuantidadeEconomiasCategoria();                                
                            }
                        }
                    }
                    
                    // Caso n㯠obtenha as quantidades pela conta, pesquisamos as quantidades de economia do imovel corrente                    
                    if ( quantidadeEconomia == 0 ){                                     
                        imovelObterQtdEconomia = new Imovel();
                        imovelObterQtdEconomia.setId((Integer) retorno[0]);
                        quantidadeEconomia = this.getControladorImovel()
                                .obterQuantidadeEconomias(imovelObterQtdEconomia);
                        if (quantidadeEconomia == null) {
                            quantidadeEconomia = 0;
                        }
                    }
                    
                    // Consumo de Agua
                    Integer consumoAgua = null;
                    // Verificamos se o consumo de Agua esta na conta ou no consumoHistorico
                    if( idConta != null ){
                        consumoAgua = (Integer) retorno[18];                        
                    } else {
                        consumoAgua= (Integer) retorno[13];
                    }
                    
                    helper.setQuantidadeConsumoAgua( consumoAgua );
                    
                    // [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
                    String indicadorHidrometroString = new Integer(
                            getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
                    Integer indicadorHidrometro = new Integer(indicadorHidrometroString);
                    // Caso indicador de hidr�metro esteja nulo
                    // Seta 2(dois) = N�O no indicador de
                    // hidr�metro
                    if (indicadorHidrometro == null) {
                        indicadorHidrometro = 2;
                    }
                    helper.setIdHidrometro( indicadorHidrometro );          
                    

                    // Pesquisamos a esfera de poder do cliente responsavel
                    helper
                            .setIdEsferaPoder(this.repositorioGerencialCadastro
                                    .pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
                    // Pesquisamos o tipo de cliente responsavel do imovel
                    helper
                            .setIdClienteTipo(this.repositorioGerencialCadastro
                                    .pesquisarTipoClienteClienteResponsavelImovel(idImovel));
                    
                    // pesquisando a categoria
                    Categoria categoria = null;
                    Categoria categoriaConta = null;
                    
                    // [UC0632] - Obter Principal Categoria da Conta
                    // Verificamos se existe conta para este imovel
                    if ( idConta != null ){
                        categoriaConta = this.getControladorImovel().obterPrincipalCategoriaConta( idConta );
                    }
                    
                    // Pesquisamos a categoria do imovel
                    Categoria categoriaImovel = this.getControladorImovel()
                        .obterPrincipalCategoriaImovel(idImovel);
                    
                    // Verificamos se devemos guardar a categoria da conta ou do imovel
                    if ( categoriaConta == null ){
                        categoria = categoriaImovel;
                    } else {
                        categoria = categoriaConta;
                    }

                    // Pesquisamos a subcategoria
                    if (categoria != null) {
                        helper.setIdCategoria(categoria.getId());

                        // Pesquisando a principal subcategoria
                        // Lembrando que a categoria usada para pesquisar a subcategoria e sempre a do imovel
                        ImovelSubcategoria subcategoria = this
                                .getControladorImovel()
                                .obterPrincipalSubcategoria(categoriaImovel.getId(),
                                        idImovel);
                        
                        helper.setIdSubCategoria(subcategoria.getComp_id()
                                .getSubcategoria().getId());
                    }

                    // Verificamos se a esfera de poder foi encontrada
                    // [FS0002] Verificar existencia de cliente responsavel
                    if (helper.getIdEsferaPoder().equals(0)) {
                        Imovel imovel = new Imovel();
                        imovel.setId(idImovel);
                        Cliente clienteTemp = this.getControladorImovel()
                                .consultarClienteUsuarioImovel(imovel);
                        if (clienteTemp != null) {
                            helper.setIdEsferaPoder(clienteTemp
                                    .getClienteTipo().getEsferaPoder().getId());
                        }
                    }
                    
                    if (helper.getIdClienteTipo().equals(0)) {
                        Imovel imovel = new Imovel();
                        imovel.setId(idImovel);
                        Cliente clienteTemp = this.getControladorImovel()
                                .consultarClienteUsuarioImovel(imovel);
                        if (clienteTemp != null) {
                            helper.setIdClienteTipo(clienteTemp
                                    .getClienteTipo().getId());
                        }
                    }

                    // Calculamos o consumo excedente
                    // [UC0105] - Obter consumo minimo ligacao
                    // Valor minimo de consumo de agua                  
                    anoMesReferencia = (Integer) retorno[15];
                    Imovel imovelTemp = new Imovel();
                    imovelTemp.setId(idImovel);
                    imovelTemp = this.getControladorImovel()
                            .pesquisarImovel(imovelTemp.getId());                   

                    Integer valorMinimoAgua = getControladorMicromedicao()
                            .obterConsumoMinimoLigacao(imovelTemp, null);

                    // Setamos o valor do excedente
                
                    helper.setQuantidadeConsumoAguaExcedente(helper
                            .getQuantidadeConsumoAgua()
                            - valorMinimoAgua);

                    // Caso o valor excedente seja negativo, seramos, pois nao
                    // houve excedente.
                    if (helper.getQuantidadeConsumoAguaExcedente() <= 0) {
                        helper.setQuantidadeConsumoAguaExcedente(0);
                    }
                    
                    // Marcamos o helper como possuindo ou n�o consumo excedente
                    helper.setIdVolumeExcedente(helper
                            .getQuantidadeConsumoAguaExcedente() > 0 ? 1 : 2);
                    
                    //**********************************************************
                    // Verificamos as Ligacoes Faturadas
                    //**********************************************************
                    if (idConta != null) {
                        helper.setIndicadorLigacaoFaturada(1);
                    }else {
                        helper.setIndicadorLigacaoFaturada(2);
                    }
                    //**********************************************************

                    // se ja existe um objeto igual a ele entao soma as
                    // ligacoes e as economias no ja existente
                    // um objeto eh igual ao outro se ele tem todos as
                    // informacos iguals ( excecao
                    // quantidadeEconomia, quantidadeLigacoes )
                    if (listaSimplificada.contains(helper)) {
                        int posicao = listaSimplificada.indexOf(helper);
                        	ResumoConsumoAguaPorAnoHelper jaCadastrado = 
                        		(ResumoConsumoAguaPorAnoHelper) listaSimplificada
                                .get(posicao);

                        // Somamos as economias
                        jaCadastrado.setQuantidadeEconomias(jaCadastrado
                                .getQuantidadeEconomias().intValue()
                                + quantidadeEconomia);

                        // Incrementamos as ligacoes
                        jaCadastrado.setQuantidadeLigacoes(jaCadastrado
                                .getQuantidadeLigacoes() + 1);

                        // Acumulamos o consumo de agua
                        jaCadastrado.setQuantidadeConsumoAgua(jaCadastrado
                                .getQuantidadeConsumoAgua().intValue()
                                + helper.getQuantidadeConsumoAgua());
                        
                        // Acumulamos o consumo Excedente
                        jaCadastrado.setQuantidadeConsumoAguaExcedente(
                                jaCadastrado.getQuantidadeConsumoAguaExcedente() +
                                helper.getQuantidadeConsumoAguaExcedente() );
                        
                        // Guardamos o volume faturado, caso este seja maior que o m���mo
                        if ( helper.getQuantidadeConsumoAgua() > valorMinimoAgua ){
                            jaCadastrado.setVolumeFaturado( 
                                    jaCadastrado.getVolumeFaturado() + 
                                    helper.getQuantidadeConsumoAgua() );
                        } else {
                            jaCadastrado.setVolumeFaturado( 
                                    jaCadastrado.getVolumeFaturado() + 
                                    valorMinimoAgua );
                        }                       
                    } else {
                        // Somamos as economias
                        helper.setQuantidadeEconomias(helper
                                .getQuantidadeEconomias().intValue()
                                + quantidadeEconomia);

                        // Incrementamos as ligacoes
                        helper.setQuantidadeLigacoes(helper
                                .getQuantidadeLigacoes() + 1);
                        
                        // Guardamos o volume faturado, caso este seja maior que o m���mo
                        if ( helper.getQuantidadeConsumoAgua() > valorMinimoAgua ){
                            helper.setVolumeFaturado( 
                                    helper.getQuantidadeConsumoAgua() );
                        } else {
                            helper.setVolumeFaturado( 
                                    valorMinimoAgua );
                        }                       

                        listaSimplificada.add(helper);
                    }
                }
            }

            for (int i = 0; i < listaSimplificada.size(); i++) {
                this.repositorioGerencialCadastro.inserirResumoConsumoAguaPorAno(
                        anoMesReferencia,
                        (ResumoConsumoAguaPorAnoHelper) listaSimplificada.get(i));
            }

            // --------------------------------------------------------
            //
            // Registrar o fim da execu��o da Unidade de Processamento
            //
            // --------------------------------------------------------

            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
                    idUnidadeIniciada, false);

        } catch (Exception ex) {
            // Este catch serve para interceptar qualquer exce��o que o processo
            // batch venha a lan�ar e garantir que a unidade de processamento do
            // batch ser� atualizada com o erro ocorrido
            System.out.println(" ERRO NO SETOR " + idSetor);
            System.out.println("Imovel que deu erro: "+ imovelErro);

            ex.printStackTrace();
            sessionContext.setRollbackOnly();

            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
                    idUnidadeIniciada, true);

            throw new EJBException(ex);
        }
    }
	
    /**
	 * M鴯do que gera o resumo do coleta esgoto Por Ano
	 * 
	 * Gerar Resumo do coleta esgoto Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 15/06/2010
	 * 
	 */
	public void gerarResumoColetaEsgotoPorAno(int idSetor, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);

		Integer anoMesReferencia = null;
		Integer idImovel = null;
		
		try {
			Integer anoMesFaturamento = getControladorUtil().
				pesquisarParametrosDoSistema().getAnoMesFaturamento();
			
			List imoveisResumoColetaEsgoto = this.repositorioGerencialCadastro
					.getHistoricosColetaEsgotoPorAno(idSetor, anoMesFaturamento);

			List<ResumoColetaEsgotoPorAnoHelper> listaSimplificada = new ArrayList();
			//List<UnResumoColetaEsgoto> listaResumoConsumoAgua = new ArrayList();

			Imovel imovelObterQtdEconomia = null;
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		repositorioGerencialCadastro.excluirResumoSQL( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				"micromedicao.un_resumo_coleta_esgoto_ref_2010", 
	   				"rece_amreferencia", 
	   				idSetor,
	   				false );				
			
			
			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoColetaEsgoto.size(); i++) {
				Object obj = imoveisResumoColetaEsgoto.get(i);
				
				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					ResumoColetaEsgotoPorAnoHelper helper = new ResumoColetaEsgotoPorAnoHelper(
							(Integer) retorno[1], // Gerencia Regional
							(Integer) retorno[2], // Unidade de negocio
							(Integer) retorno[3], // Localidade
							(Integer) retorno[4], // Elo
							(Integer) retorno[5], // Id Setor Comercial
//							(Integer) retorno[6], // id Rota
//							(Integer) retorno[7], // Id Quadra
							(Integer) retorno[6], // Codigo do Setor Comercial
//							(Integer) retorno[9], // Numero da quadra
							(Integer) retorno[7],// Perfil do imovel
							(Integer) retorno[8],// Situacao da ligacao da agua
							(Integer) retorno[9],// Situacao da ligacao do esgoto
							(Integer) retorno[10],// Perfil da ligacao de agua
							(Integer) retorno[11],// Perfil da ligacao de esgoto
							(Integer) retorno[12] // Consumo tipo
//							(Short)   retorno[24] //codigo rota
					);
					// Imovel
					idImovel = (Integer) retorno[0];
					
					// Id da Conta
					Integer idConta = ( Integer ) retorno[19];
					Conta conta = new Conta();
					conta.setId( idConta );
					
					FiltroConta filtroConta = new FiltroConta();
					filtroConta.adicionarParametro( new ParametroSimples(FiltroConta.ID, idConta));
					
					Collection contas = Fachada.getInstancia().pesquisar(filtroConta, Conta.class.getName());
					
					Integer motivoSituacaoFaturamento = null;
					
					if ( contas != null && !contas.isEmpty() ){
						
						conta = (Conta)Util.retonarObjetoDeColecao(contas);
						
						motivoSituacaoFaturamento = this.
								repositorioGerencialCadastro.getMotivoSituacaoFaturamento(
										idImovel, conta.getReferencia());
						
					}
					
					//Motivo Situacao Faturamento
					helper.setMotivoFaturamentoSituacao(motivoSituacaoFaturamento);
					
					//Indicador de Faturamento
					helper.setIndicadorFaturamento((Short)retorno[21]);
					
					//Situa磯 de Faturamento
					helper.setFaturamentoSituacao((Integer)retorno[22]);
					
					// Quantidade de Economias
					Integer quantidadeEconomia = 0;					
					
					// Verificamos se existe economias pela conta
					if ( idConta != null && !idConta.equals(0)){
						Collection<Categoria> colQuantEco = 
							this.getControladorImovel().obterQuantidadeEconomiasContaCategoria( conta );
						
						if ( colQuantEco != null && colQuantEco.size() > 0  ){
							Iterator iteQuanEco = colQuantEco.iterator();							
							
							while ( iteQuanEco.hasNext() ){
								Categoria catTemp = (Categoria) iteQuanEco.next();
								
								quantidadeEconomia += catTemp.getQuantidadeEconomiasCategoria();								
							}
						}
					}
					
					// Caso n㯠obtenha as quantidades pela conta, pesquisamos as quantidades de economia do imovel corrente					
					if ( quantidadeEconomia == 0 ){										
						imovelObterQtdEconomia = new Imovel();
						imovelObterQtdEconomia.setId((Integer) retorno[0]);
						quantidadeEconomia = this.getControladorImovel()
								.obterQuantidadeEconomias(imovelObterQtdEconomia);
						if (quantidadeEconomia == null) {
							quantidadeEconomia = 0;
						}
					}
					

					// Consumo de Esgoto
					Integer consumoEsgoto = null;
					// Verificamos se o consumo de esgoto esta na conta ou no consumoHistorico
					if( idConta != null && !idConta.equals(0)){
						consumoEsgoto = (Integer) retorno[20];						
					} else {
					    consumoEsgoto = (Integer) retorno[13];
					}
					
					// Pesquisamos a esfera de poder do cliente responsavel
					helper.setIdEsferaPoder(
							this.repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
					
					// Pesquisamos o tipo de cliente responsavel do imovel
					helper.setIdClienteTipo(
							this.repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					// pesquisando a categoria
					Categoria categoria = null;
					Categoria categoriaConta = null;
					
					// [UC0632] - Obter Principal Categoria da Conta
					// Verificamos se existe conta para este imovel
					if ( idConta != null && !idConta.equals(0)){
						categoriaConta = this.getControladorImovel().obterPrincipalCategoriaConta( idConta );
					}
					
					// Pesquisamos a categoria do imovel
					Categoria categoriaImovel = this.getControladorImovel()
						.obterPrincipalCategoriaImovel(idImovel);
					
					// Verificamos se devemos guardar a categoria da conta ou do imovel
					if ( categoriaConta == null ){
						categoria = categoriaImovel;
					} else {
						categoria = categoriaConta;
					}

					// Pesquisamos a subcategoria
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						// Lembrando que a categoria usada para pesquisar a subcategoria e sempre a do imovel
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(categoriaImovel.getId(),
										idImovel);
						
						helper.setIdSubCategoria(subcategoria.getComp_id()
								.getSubcategoria().getId());
					}

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsferaPoder().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdEsferaPoder(clienteTemp
									.getClienteTipo().getEsferaPoder().getId());
						}
					}
					
					if (helper.getIdClienteTipo().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdClienteTipo(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// Ano Mes Referencia.
					anoMesReferencia = (Integer) retorno[14];

					// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
					String indicadorHidrometroString = new Integer(
							getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
					Short indicadorHidrometro = new Short(indicadorHidrometroString);
					// Caso indicador de hidrometro esteja nulo
					// Seta 2(dois) = NAO no indicador de
					// hidrometro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}
					helper.setIndicadorHidrometro(indicadorHidrometro);
					
					// Verificamos a existencia de poco no imovel
					Integer idPoco = this.repositorioGerencialCadastro
							.verificarExistenciaPoco(idImovel);
					if(idPoco == null){
						idPoco = 0;
					}

					Imovel imovelTemp = new Imovel();
					imovelTemp.setId(idImovel);
					imovelTemp = this.getControladorImovel()
							.pesquisarImovel(imovelTemp.getId());
					
					//***************************************************
					// Calcula o Volume Faturado
					//***************************************************
					Integer valorMinimoEsgoto = 0;
					
					if (idConta != null && !idConta.equals(0)) {
						valorMinimoEsgoto = 
							this.repositorioGerencialCadastro.pesquisarConsumoMinimoEsgotoConta(idConta);
						
						if (valorMinimoEsgoto == null) {
							valorMinimoEsgoto = 0;
						}
					}else {
						valorMinimoEsgoto = 
							getControladorMicromedicao().obterConsumoMinimoLigacao(imovelTemp, null);
					}
					
					Integer voFaturado = valorMinimoEsgoto;
					short icColetaEsgotoExcedente = 2;
					helper.setIndicadorColetaEsgotoExcedente(icColetaEsgotoExcedente);
					
					if(consumoEsgoto > valorMinimoEsgoto){
						voFaturado = consumoEsgoto;
						icColetaEsgotoExcedente = 1;
						helper.setIndicadorColetaEsgotoExcedente(icColetaEsgotoExcedente);
					}
					
					if(voFaturado == null ){
						voFaturado = 0;
					}
					helper.setVoFaturado(voFaturado);
					//***************************************************

					helper.setQuantidadeColetaEsgoto(consumoEsgoto);
					
					// Setamos o valor do excedente
					helper.setVolumeExcedente(helper
							.getQuantidadeColetaEsgoto()
							- valorMinimoEsgoto);
 
					// Caso o valor excedente seja negativo, seramos, pois nao
					// houve excedente.
					if (helper.getVolumeExcedente() <= 0) {
						helper.setVolumeExcedente(0);
					}

					float percentualEsgoto = 0f; 
					percentualEsgoto = ((Double) retorno[16]).floatValue();
					if(percentualEsgoto == 0){
						percentualEsgoto = 0.0f;
					}
					helper.setPcEsgoto(percentualEsgoto);
						
					// percentual coleta
					BigDecimal pcColeta = (BigDecimal) retorno[18];
					helper.setPcColeta(pcColeta.floatValue());
					
					// helper
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						ResumoColetaEsgotoPorAnoHelper jaCadastrado = 
							(ResumoColetaEsgotoPorAnoHelper) listaSimplificada.get(posicao);

						// Somamos as economias
						jaCadastrado.setQuantidadeEconomias(jaCadastrado
								.getQuantidadeEconomias().intValue()
								+ quantidadeEconomia);

						// Incrementamos as ligacoes
						jaCadastrado.setQuantidadeLigacoes(jaCadastrado
								.getQuantidadeLigacoes() + 1);

						// Acumulamos o consumo de agua
						jaCadastrado.setQuantidadeColetaEsgoto(jaCadastrado
								.getQuantidadeColetaEsgoto().intValue()
								+ helper.getQuantidadeColetaEsgoto());
						
						// Acumulamos o consumo Excedente
						jaCadastrado.setVolumeExcedente(jaCadastrado
								.getVolumeExcedente().intValue()
								+ helper.getVolumeExcedente().intValue());
						
						// Acumulamos o consumo faturado
						jaCadastrado.setVoFaturado(jaCadastrado
								.getVoFaturado().intValue()
								+ helper.getVoFaturado().intValue());
						
					} else {
						// Incrementamos as ligacoes
						helper.setQuantidadeLigacoes(helper
								.getQuantidadeLigacoes() + 1);
						
						// Somamos as economias
						helper.setQuantidadeEconomias(helper
								.getQuantidadeEconomias().intValue()
								+ quantidadeEconomia);

						// Ano Mes Referencia.
						helper.setAnoMesReferencia(anoMesReferencia);
						
						listaSimplificada.add(helper);
					}
				}
			}
			
			imoveisResumoColetaEsgoto.clear();
			imoveisResumoColetaEsgoto = null;

			for (int i = 0; i < listaSimplificada.size(); i++) {
				this.repositorioGerencialCadastro.inserirResumoColetaEsgotoPorAno(
						anoMesReferencia,
						(ResumoColetaEsgotoPorAnoHelper) listaSimplificada.get(i));
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execucao da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer excecaoo que o processo
			// batch venha a lancar e garantir que a unidade de processamento do
			// batch sera atualizada com o erro ocorrido
			System.out.println(" ERRO NO SETOR " + idSetor + " COLETA ESGOTO POR ANO - IMOVEL " + idImovel);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}
	
	/**
	 * M鴯do que gera o resumo do Parcelamento PorAno
	 * 
	 * Gerar Resumo do Parcelamento Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 21/06/2010
	 * 
	 */
	public void gerarResumoParcelamentoPorAno(int idLocalidade,
			int idFuncionalidadeIniciada, int anoMes)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {
			
			System.out.println("INICIO DO RESUMO PARCELAMENTO POR ANO LOCALIDADE "+idLocalidade);
			
			List imoveisResumoParcelamento = this.repositorioGerencialCadastro
					.getImoveisResumoParcelamentoPorAno(idLocalidade, anoMes);

			List<ResumoParcelamentoPorAnoHelper> listaSimplificadaParcelamento = new ArrayList();
			List<UnResumoParcelamentoPorAno> listaResumoParcelamento = new ArrayList();

			UnResumoParcelamentoPorAno resumoParcelamento = null;

			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		this.repositorioGerencialCadastro.excluirResumoGerencialC( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				"cobranca.un_resumo_parcelamento_ref_2010", "repa_amreferencia","loca_id", idLocalidade );				
			
			
			// caso ja tenha um igual soma as quantidades
			for (int i = 0; i < imoveisResumoParcelamento.size(); i++) {

				Object obj = imoveisResumoParcelamento.get(i);

				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					// Pesquisamos as quantidades do imovel corrente
					resumoParcelamento = new UnResumoParcelamentoPorAno();
					resumoParcelamento.setId((Integer) retorno[0]);

					// Contas
					Integer quantidadeContas = this.repositorioGerencialCadastro
							.pesquisarObterQuantidadeContas((Integer) retorno[0]);
					if (quantidadeContas == null) {
						quantidadeContas = 0;
					}

					// Guias
					Integer quantidadeGuias = this.repositorioGerencialCadastro
							.pesquisarObterQuantidadeGuias((Integer) retorno[0]);
					if (quantidadeGuias == null) {
						quantidadeGuias = 0;
					}

					// Quantidade Servicos Indiretos
					Short quantidadeServicosIndiretos = this.repositorioGerencialCadastro
							.pesquisarObterQuantidadeServicosIndiretos((Integer) retorno[0]);
					if (quantidadeServicosIndiretos == null) {
						quantidadeServicosIndiretos = 0;
					}

					// Valor das Contas
					BigDecimal valorContas = (BigDecimal) retorno[13];
					if (valorContas == null) {
						valorContas = new BigDecimal(0);
					}

					// Valor Guias Pagmto
					BigDecimal valorGuias = (BigDecimal) retorno[14];
					if (valorGuias == null) {
						valorGuias = new BigDecimal(0);
					}

					// Valor Servicos Indiretos
					BigDecimal valorServicosIndiretos = this.repositorioGerencialCadastro
							.pesquisarObterValorServicosIndiretos(
									(Integer) retorno[0],
									" debitoacobrar.lancamentoItemContabil.id not in (2,3)");
					if (valorServicosIndiretos == null) {
						valorServicosIndiretos = new BigDecimal(0);
					}

					// Valor creditos
					BigDecimal valorCreditos = (BigDecimal) retorno[15];
					if (valorCreditos == null) {
						valorCreditos = new BigDecimal(0);
					}

					// Valor Servicos Indiretos
					BigDecimal valorAcrescimoImpontualidade = (BigDecimal) retorno[22];
					if(valorAcrescimoImpontualidade == null){
						valorAcrescimoImpontualidade = new BigDecimal(0);
					}

					if (valorAcrescimoImpontualidade == null) {
						valorAcrescimoImpontualidade = new BigDecimal(0);
					}

					// Valor Sancoes
					BigDecimal valorSancoes = this.repositorioGerencialCadastro
							.pesquisarObterValorServicosIndiretos(
									(Integer) retorno[0],
									" debitoacobrar.lancamentoItemContabil.id = 3");
					if (valorSancoes == null) {
						valorSancoes = new BigDecimal(0);
					}

					// Valor creditos
					BigDecimal valorDescontoAcrescimo = (BigDecimal) retorno[16];
					if (valorDescontoAcrescimo == null) {
						valorDescontoAcrescimo = new BigDecimal(0);
					}

					// Valor Desconto Inatividade
					BigDecimal valorDescontoInatividade = (BigDecimal) retorno[17];
					if (valorDescontoInatividade == null) {
						valorDescontoInatividade = new BigDecimal(0);
					}

					// Valor Desconto Antiguidade
					BigDecimal valorDescontoAntiguidade = (BigDecimal) retorno[18];
					if (valorDescontoAntiguidade == null) {
						valorDescontoAntiguidade = new BigDecimal(0);
					}

					// Valor total Parcelamento
					BigDecimal valorTotalParcelamento = (BigDecimal) retorno[19];
					if (valorTotalParcelamento == null) {
						valorTotalParcelamento = new BigDecimal(0);
					}
					// Ano Mes Referencia
					Integer anoMesRef = (Integer) retorno[20];

					
					// valor debito a cobrar Total
					BigDecimal valorDebitoACobrarTotal = (BigDecimal) retorno[21]; 
					if (valorDebitoACobrarTotal == null) {
						valorDebitoACobrarTotal = new BigDecimal(0);
					}
					// valor debito a cobrar Acrescimos
					BigDecimal valorDebitoACobrarAcrescimos = this.repositorioGerencialCadastro
					.pesquisarObterValorServicosIndiretos(
							(Integer) retorno[0],
							" debitoacobrar.lancamentoItemContabil.id = 2");
					if (valorDebitoACobrarAcrescimos == null) {
						valorDebitoACobrarAcrescimos = new BigDecimal(0); 
					}
					
					// valor debito a cobrar Acrescimos
					BigDecimal valorDebitoACobrarReligSancoes = new BigDecimal(0); //(BigDecimal) retorno[0]; 
					if (valorDebitoACobrarReligSancoes == null) {
						valorDebitoACobrarReligSancoes = new BigDecimal(0);
					}
					// valor debitos a cobrar parcelamentos
					BigDecimal valorDebitoACobrarParcelamentos = (BigDecimal) retorno[23];
					if (valorDebitoACobrarParcelamentos == null){
						valorDebitoACobrarParcelamentos = new BigDecimal(0); 
					}
					// valor entrada
					BigDecimal valorEntrada = (BigDecimal) retorno[24];
					if (valorEntrada == null){
						valorEntrada = new BigDecimal(0);
					}
					// valor juros parcelamento
					BigDecimal valorJurosParcelamento = (BigDecimal) retorno[25];
					if (valorJurosParcelamento == null) {
						valorJurosParcelamento = new BigDecimal(0);
					}
					// quantidade total de parcelas
					Short quantidadeTotalParcelas = (Short) retorno[26];
					if (quantidadeTotalParcelas == null){
						quantidadeTotalParcelas = 0;
					}
					
					Integer consumoTarifa = (Integer) retorno[27];
					if(consumoTarifa == null){
						consumoTarifa = 0;
					}
					
					Short numeroParcelamentoConsecutivos = (Short) retorno[28];
					if(numeroParcelamentoConsecutivos == null){
						numeroParcelamentoConsecutivos = new Short("0");
					}
					
					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do im�vel
					Integer idImovel = (Integer) retorno[1]; // Codigo do

					// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
					String indicadorHidrometroString = new Integer(
							getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
					Short indicadorHidrometro = new Short(indicadorHidrometroString);
					// Caso indicador de hidr�metro esteja nulo
					// Seta 2(dois) = N�O no indicador de
					// hidr�metro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}					
					
					
					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					
					ResumoParcelamentoPorAnoHelper helper = new ResumoParcelamentoPorAnoHelper(
							(Integer) retorno[2], // Gerencia Regional
							(Integer) retorno[3], // Unidade de negocio
							(Integer) retorno[4], // Elo
							(Integer) retorno[5], // Localidade
							(Integer) retorno[6], // Id Setor Comercial
//							(Integer) retorno[7], // id Rota
//							(Integer) retorno[8], // Id Quadra
							(Integer) retorno[7], // Codigo do Setor Comercial
//							(Integer) retorno[8], // Numero da quadra
							(Integer) retorno[8], // Perfil do imovel
							(Integer) retorno[9], // Situacao da ligacao da
							// agua
							(Integer) retorno[10], // Situacao da ligacao do
							// esgoto
							(Integer) retorno[11], // Perfil ligacao agua
							(Integer) retorno[12], // Perfil ligacao esgoto
							(Integer) retorno[27], // Tarifa Consumo
							indicadorHidrometro,   // Indicador Hidrometro
							numeroParcelamentoConsecutivos // Numero Parcelamentos Consecutivos
//							(Short) retorno[32]
							);//codigo rota
					
					// Pesquisamos a esfera de poder do cliente responsavel
					helper
							.setIdEsfera(this.repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
					// Pesquisamos o tipo de cliente responsavel do imovel
					helper
							.setIdTipoClienteResponsavel(this.repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					Categoria categoria = null;
					categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);
					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());

						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoria = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(categoria.getId(),
										idImovel);

						if (subcategoria != null) {
							helper.setIdSubCategoria(subcategoria.getComp_id()
									.getSubcategoria().getId());
						}
					}

					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsfera().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);

						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdEsfera(clienteTemp.getClienteTipo()
									.getEsferaPoder().getId());
						}
					}
					if (helper.getIdTipoClienteResponsavel().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdTipoClienteResponsavel(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// se ja existe um objeto igual a ele entao soma os
					// valores e as quantidades ja existentes.
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals
					if (listaSimplificadaParcelamento.contains(helper)) {
						int posicao = listaSimplificadaParcelamento
								.indexOf(helper);
						ResumoParcelamentoPorAnoHelper jaCadastrado = 
							(ResumoParcelamentoPorAnoHelper) listaSimplificadaParcelamento
								.get(posicao);
						// Somatorios
						// Contas
						jaCadastrado.setQtdContas(jaCadastrado.getQtdContas()
								+ quantidadeContas);
						// Guias
						jaCadastrado.setQtdGuias(jaCadastrado.getQtdGuias()
								+ quantidadeGuias);
						// Servicos Indiretos
						jaCadastrado
								.setQtdServicosIndiretos((short) (jaCadastrado
										.getQtdServicosIndiretos() + quantidadeServicosIndiretos));
						// qtdParcelamentos
						Short p = (((Integer) (jaCadastrado
								.getQtdParcelamento() + 1)).shortValue());
						jaCadastrado.setQtdParcelamento(p);
						// Valor Contas
						jaCadastrado.setVlContas(jaCadastrado.getVlContas()
								.add(valorContas));
						// Valor Guias
						jaCadastrado.setVlGuias(jaCadastrado.getVlGuias().add(
								valorGuias));
						// Valor Servicos Indiretos
						jaCadastrado.setVlServicosIndiretos(jaCadastrado
								.getVlServicosIndiretos().add(valorGuias));
						// Valor Creditos
						jaCadastrado.setVlCreditoRealizar(jaCadastrado
								.getVlCreditoRealizar().add(valorCreditos));
						// Valor Acrescimo Impontualidade
						jaCadastrado.setVlAcrescimoImpontualidade(jaCadastrado
								.getVlAcrescimoImpontualidade().add(
										valorAcrescimoImpontualidade));
						// Valor Sancoes
						jaCadastrado.setVlSancoes(jaCadastrado.getVlSancoes()
								.add(valorSancoes));
						// Valor Desconto Acrescimo
						jaCadastrado.setVlDescontoAcrescimo(jaCadastrado
								.getVlDescontoAcrescimo().add(
										valorDescontoAcrescimo));
						// Valor Desconto Inatividade
						jaCadastrado.setVlDescontoInatividade(jaCadastrado
								.getVlDescontoInatividade().add(
										valorDescontoInatividade));
						// Valor Desconto Antiguidade
						jaCadastrado.setVlDescontoAntiguidade(jaCadastrado
								.getVlDescontoAntiguidade().add(
										valorDescontoAntiguidade));
						// Valor Total Parcelamento
						jaCadastrado.setVlTotalParcelamento(jaCadastrado
								.getVlTotalParcelamento().add(
										valorTotalParcelamento));
						// AnoMesReferencia
						jaCadastrado.setAnoMesReferencia(jaCadastrado
								.getAnoMesReferencia());
						
						// valor debitos a cobrar total 
						jaCadastrado.setVlDebitosACobrarTotal(jaCadastrado
								.getVlDebitosACobrarTotal().add(valorDebitoACobrarTotal));
						
						// valor debitos a cobrar acrescimos 
						jaCadastrado.setVlDebitosACobrarAcrescimos(jaCadastrado
								.getVlDebitosACobrarAcrescimos().add(valorDebitoACobrarAcrescimos));
						
						// valor debitos a cobrar religsancoes
						jaCadastrado.setVlDebitosACobrarReligSancoes(jaCadastrado
								.getVlDebitosACobrarReligSancoes().add(valorDebitoACobrarReligSancoes));
						
						// valor debitos a cobrar parcelamentos
						jaCadastrado.setVlDebitosACobrarParcelamentos(jaCadastrado
								.getVlDebitosACobrarParcelamentos().add(valorDebitoACobrarParcelamentos));

						// valor entrada
						jaCadastrado.setVlEntrada(jaCadastrado
								.getVlEntrada().add(valorEntrada));
						
						// valor juros parcelamento
						jaCadastrado.setVlJurosParcelamento(jaCadastrado
								.getVlJurosParcelamento().add(valorJurosParcelamento));

						Integer qtdMediaParcelas = jaCadastrado.getQtdMediaParcelas() + quantidadeTotalParcelas;
						// media parcelas
						jaCadastrado.setQtdMediaParcelas(qtdMediaParcelas.shortValue());                                    

						Integer qtdTotalParcelas = jaCadastrado.getQtdTotalParcelas() + quantidadeTotalParcelas;
						// total parcelas
						jaCadastrado.setQtdTotalParcelas(qtdTotalParcelas.shortValue());
						
					} else {
						// Somatorios
						// Contas
						helper.setQtdContas(helper.getQtdContas().intValue()
								+ quantidadeContas);
						// Guias
						helper.setQtdGuias(helper.getQtdGuias().intValue()
								+ quantidadeGuias);

						Short s = (((Integer) (helper.getQtdServicosIndiretos() + quantidadeServicosIndiretos))
								.shortValue());
						// Servicos Indiretos
						helper.setQtdServicosIndiretos(s);
						Short p = (((Integer) (helper.getQtdParcelamento() + 1))
								.shortValue());
						// qtdParcelamentos
						helper.setQtdParcelamento(p);
						// vlContas
						helper.setVlContas(helper.getVlContas()
								.add(valorContas));
						// vlGuias
						helper.setVlGuias(helper.getVlGuias().add(valorGuias));
						// vlServicosPrestados
						helper.setVlServicosIndiretos(helper
								.getVlServicosIndiretos().add(
										valorServicosIndiretos));
						// vlCreditos
						helper.setVlCreditoRealizar(helper
								.getVlCreditoRealizar().add(valorCreditos));
						// vlAcrescimoImpontualidade
						helper.setVlAcrescimoImpontualidade(helper
								.getVlAcrescimoImpontualidade().add(
										valorAcrescimoImpontualidade));
						// vlSancoes
						helper.setVlSancoes(helper.getVlSancoes().add(
								valorSancoes));
						// vlDescontoAcrescimo
						helper.setVlDescontoAcrescimo(helper
								.getVlDescontoAcrescimo().add(
										valorDescontoAcrescimo));

						// vlDescontoInatividade
						helper.setVlDescontoInatividade(helper
								.getVlDescontoInatividade().add(
										valorDescontoInatividade));
						// vlDescontoAntiguidade
						helper.setVlDescontoAntiguidade(helper
								.getVlDescontoAntiguidade().add(
										valorDescontoAntiguidade));
						// vlTotalParcalamento
						helper.setVlTotalParcelamento(helper
								.getVlTotalParcelamento().add(
										valorTotalParcelamento));
						listaSimplificadaParcelamento.add(helper);
						// AnoMesReferencia
						helper.setAnoMesReferencia(anoMesRef);
						
						// valor debitos a cobrar total 
						helper.setVlDebitosACobrarTotal(valorDebitoACobrarTotal);
						
						// valor debitos a cobrar acrescimos 
						helper.setVlDebitosACobrarAcrescimos(valorDebitoACobrarAcrescimos);
						
						// valor debitos a cobrar religsancoes
						helper.setVlDebitosACobrarReligSancoes(valorDebitoACobrarReligSancoes);
						
						// valor debitos a cobrar parcelamentos
						helper.setVlDebitosACobrarParcelamentos(valorDebitoACobrarParcelamentos);

						// valor entrada
						helper.setVlEntrada(valorEntrada);
						
						// valor juros parcelamento
						helper.setVlJurosParcelamento(valorJurosParcelamento);

						// media parcelas
						helper.setQtdMediaParcelas(quantidadeTotalParcelas);

						// total parcelas
						helper.setQtdTotalParcelas(quantidadeTotalParcelas);
					}
				}
			}

			/**
			 * para todas as ImovelResumoParcelamentoHelper cria
			 * ResumoParcelamento
			 */
			for (int i = 0; i < listaSimplificadaParcelamento.size(); i++) {
				ResumoParcelamentoPorAnoHelper helper = (ResumoParcelamentoPorAnoHelper) 
					listaSimplificadaParcelamento
						.get(i);

				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();

				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}

				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}

				// Localidade
				GLocalidade localidade = null;
				if (helper.getIdLocalidade() != null) {
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdElo());
				}

				// Setor comercial
				GSetorComercial setorComercial = null;
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}

				// Rota
//				GRota rota = null;
//				if (helper.getIdRota() != null) {
//					rota = new GRota();
//					rota.setId(helper.getIdRota());
//					rota.setCodigoRota(helper.getCodigoRota());
//				}

				// Quadra
//				GQuadra quadra = null;
//				if (helper.getIdQuadra() != null) {
//					quadra = new GQuadra();
//					quadra.setId(helper.getIdQuadra());
//				}

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}

				// Numero da quadra
//				Integer numeroQuadra = null;
//				if (helper.getNumeroQuadra() != null) {
//					numeroQuadra = (helper.getNumeroQuadra());
//				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}

				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}

				// Tipo do cliente responsavel
				GClienteTipo clienteTipo = null;
				if (helper.getIdTipoClienteResponsavel() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoClienteResponsavel());
				}

				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}

				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}

				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null) {
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId(helper.getIdPerfilLigacaoAgua());
				}

				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null) {
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto
							.setId(helper.getIdPerfilLigacaoEsgoto());
				}

				// Quantidade Contas
				Integer qtdContas = helper.getQtdContas();

				// Quantidade Parcelamentos
				Short qtdParcelamentos = helper.getQtdParcelamento();

				// Quantidade Guias
				Integer qtdGuias = helper.getQtdGuias();

				
				// Valor das Contas
				BigDecimal vlContas = helper.getVlContas();

				// Valor Guias Pagmto
				BigDecimal vlGuias = helper.getVlGuias();

				

				// Valor creditos
				BigDecimal vlCreditos = helper.getVlCreditoRealizar();

				// Valor Servicos Indiretos
				BigDecimal vlAcrescimoImpontualidade = helper
						.getVlAcrescimoImpontualidade();

				// Valor Sancoes
				//BigDecimal vlSancoes = helper.getVlSancoes();

				// Valor creditos
				BigDecimal vlDescontoAcrescimo = helper
						.getVlDescontoAcrescimo();

				// Valor Desconto Inatividade
				BigDecimal vlDescontoInatividade = helper
						.getVlDescontoInatividade();

				// Valor Desconto Antiguidade
				BigDecimal vlDescontoAntiguidade = helper
						.getVlDescontoAntiguidade();

				// Ultima Alteracao
				Date ultimaAlteracao = new Date();

				// valor debitos a cobrar total 
				BigDecimal vlDebitosACobrarTotal         = helper.getVlDebitosACobrarTotal();   

				// valor debitos a cobrar acrescimos 
				BigDecimal vlDebitosACobrarAcrescimos    = helper.getVlDebitosACobrarAcrescimos();

				// valor debitos a cobrar religsancoes
				BigDecimal vlDebitosACobrarReligSancoes  = helper.getVlDebitosACobrarReligSancoes();

				// valor debitos a cobrar parcelamentos
				BigDecimal vlDebitosACobrarParcelamentos = helper.getVlDebitosACobrarParcelamentos();

				// valor entrada
				BigDecimal vlEntrada                     = helper.getVlEntrada();

				// valor juros parcelamento
				BigDecimal vlJurosParcelamento           = helper.getVlJurosParcelamento();

				// media parcelas
				Integer qtdMediaParcelas                 = new BigDecimal((helper.getQtdMediaParcelas()/qtdParcelamentos)).intValue();

				// total parcelas
				Integer qtdTotalParcelas                 = helper.getQtdTotalParcelas().intValue(); 
				
				//  Perfil da ligacao de agua
				GConsumoTarifa consumoTarifa = null;
				if (helper.getConsumoTarifa() != null) {
					consumoTarifa = new GConsumoTarifa();
					consumoTarifa.setId(helper.getConsumoTarifa());
				}
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				Short numeroParcelamentoConsecutivosShort = helper.getNumeroParcelamentoConsecutivos();
				
				Integer	numeroParcelamentoConsecutivos = null;
				if(numeroParcelamentoConsecutivosShort != null){
					numeroParcelamentoConsecutivos = numeroParcelamentoConsecutivosShort.intValue(); 
				}else{
					numeroParcelamentoConsecutivos = 0;
				}

				// Criamos um resumo do parcelamento
				UnResumoParcelamentoPorAno resumoParcelamentoGrava = new UnResumoParcelamentoPorAno(
						anoMesReferencia, 			codigoSetorComercial, 		/*numeroQuadra,*/
						qtdContas, 					vlContas, 					vlGuias, 
						vlCreditos, 				vlDescontoAcrescimo, 
						qtdGuias, 					 
						vlDescontoInatividade,		vlAcrescimoImpontualidade,
						ultimaAlteracao, 			qtdParcelamentos, 			
						vlDescontoAntiguidade, 		subcategoria, 				clienteTipo,
						ligacaoAguaSituacao, 		/*quadra, 	*/				ligacaoEsgotoSituacao,
						gerenciaRegional, 			setorComercial, 			perfilLigacaoAgua,
						imovelPerfil, 				unidadeNegocio, 			elo, 
						localidade,					perfilLigacaoEsgoto, 		esferaPoder, 
						categoria, 					/*rota,   			*/		vlJurosParcelamento,  
						vlEntrada	, 				vlDebitosACobrarParcelamentos, 
						vlDebitosACobrarTotal,	 	vlDebitosACobrarAcrescimos, 
						vlDebitosACobrarReligSancoes,							qtdTotalParcelas, 
						qtdMediaParcelas, 			consumoTarifa, 				indicadorHidrometro,
						numeroParcelamentoConsecutivos /*, helper.getCodigoRota()*/);						
				

				// Adicionamos a lista que deve ser inserida
				listaResumoParcelamento.add(resumoParcelamentoGrava);
			}

			this.getControladorBatch().inserirColecaoObjetoParaBatchGerencial(
					(Collection) listaResumoParcelamento);

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido

			System.out.println(" ERRO NA LOCALIDADE " + idLocalidade);

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}
	
	/***
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 11/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param idSetor
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoHistogramaSemQuadra(
			int anoMesReferencia,
			int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = 0;

		// Registrar o inicio do Processamento do Batch
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada, 
						UnidadeProcessamento.SETOR_COMERCIAL,
						idSetor);
		
		try {
			System.out.println("*************************************************");
			System.out.println("INICIO DO HISTOGRAMA SEM QUADRA SETOR: " + idSetor);
			System.out.println("*************************************************");
			
			// [SB1000] ? Gerar Histograma para Im?s Faturados
			List histogramaAguaLigacao = this.repositorioGerencialCadastro.getContasHistogramaSemQuadra(
					idSetor, anoMesReferencia);
			
			// FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		repositorioGerencialCadastro.excluirResumoSQL( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				"faturamento.histo_agua_econ_sqdra", 
	   				"haes_amreferencia", 
	   				idSetor,
	   				true );

	   		repositorioGerencialCadastro.excluirResumoSQL( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				"faturamento.histo_agua_ligacao_sqdra", 
	   				"hals_amreferencia", 
	   				idSetor,
	   				true );
	   		
	   		repositorioGerencialCadastro.excluirResumoSQL( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				"faturamento.histo_esgt_econ_sqdra", 
	   				"hees_amreferencia", 
	   				idSetor,
	   				true );			
	   		
	   		repositorioGerencialCadastro.excluirResumoSQL( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				"faturamento.histo_esgt_ligacao_sqdra", 
	   				"hels_amreferencia", 
	   				idSetor,
	   				true );
	   		
			List<HistogramaAguaLigacaoSemQuadraHelper> listaSimplificadaAguaLigacao = 
				new ArrayList<HistogramaAguaLigacaoSemQuadraHelper>();
			List<HistogramaAguaEconomiaSemQuadraHelper> listaSimplificadaAguaEconomia = 
				new ArrayList<HistogramaAguaEconomiaSemQuadraHelper>();
			List<HistogramaEsgotoLigacaoSemQuadraHelper> listaSimplificadaEsgotoLigacao = 
				new ArrayList<HistogramaEsgotoLigacaoSemQuadraHelper>();
			List<HistogramaEsgotoEconomiaSemQuadraHelper> listaSimplificadaEsgotoEconomia = 
				new ArrayList<HistogramaEsgotoEconomiaSemQuadraHelper>();
			
			// Verifica o indicador da tarifa categoria
			Short indicadorTarifaCategoria = 
				getControladorUtil().pesquisarParametrosDoSistema().getIndicadorTarifaCategoria();
			
			for (int i = 0; i < histogramaAguaLigacao.size(); i++) {
				Object obj = histogramaAguaLigacao.get(i);
				
				if ( i % 100 == 0 || i == 0 ) {
					System.out.println("PROCESSANDO CONTA " + (i+1) + " DE " + histogramaAguaLigacao.size());
				}

				if (obj instanceof Object[]) {
					HistogramaAguaLigacaoSemQuadraHelper histogramaAguaLigacaoSemQuadraHelper;
					HistogramaAguaEconomiaSemQuadraHelper histogramaAguaEconomiaSemQuadraHelper;
					HistogramaEsgotoLigacaoSemQuadraHelper histogramaEsgotoLigacaoSemQuadraHelper;
					HistogramaEsgotoEconomiaSemQuadraHelper histogramaEsgotoEconomiaSemQuadraHelper;
					Object[] retorno = (Object[]) obj;

					Integer idConta = (Integer) retorno[0];
					Integer idImovel = (Integer) retorno[10];

					// Variaveis para preenchimento dos Helper`s
					Integer idGerenciaRegional = (Integer) retorno[1];
					Integer idUnidadeNegocio = (Integer) retorno[2];
					Integer idElo = (Integer) retorno[3];
					Integer idLocalidade = (Integer) retorno[4];
					Integer idSetorCormecial = (Integer) retorno[5];
					Integer codigoSetorComercial = (Integer) retorno[6];
					Integer idConsumoTarifa = (Integer) retorno[7];
					Integer idPerfilImovel = (Integer) retorno[8];
					Integer idSituacaoAgua = (Integer) retorno[9];
					Integer idSituacaoEsgoto = (Integer) retorno[11];
					Float percentualEsgoto = ((BigDecimal) retorno[12]).floatValue();
					
					// Verificamos se o imovel possue categoria mista
					//Integer idLigacaoMista = (this.repositorioGerencialCadastro
					//	.pesquisaQuantidadeCategorias(idConta) == 1 ? 2 : 1);
					
					// Pesquisamos a esfera de poder
					Integer idEsferaPoder = this.repositorioGerencialCadastro
						.pesquisarEsferaPoderClienteResponsavelImovel(idImovel);
					
					// Verificamos se a esfera de poder foi encontrada;
					// [FS0002] Verificar existencia de cliente responsavel
					if (idEsferaPoder.equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							idEsferaPoder = clienteTemp.getClienteTipo().getEsferaPoder().getId();
						}
					}
					
					// Verificamos o consumo real AGUA
					Integer idConsumoReal = this.repositorioGerencialCadastro
							.verificarConsumoReal(idImovel);
					// Verificamos o consumo real ESGOTO
					Integer idConsumoRealEsgoto = this.repositorioGerencialCadastro
							.verificarConsumoRealEsgoto(idImovel);
					// Verificamos a existencia de hidrometro
					Integer idHidrometro = this.repositorioGerencialCadastro
							.verificarExistenciaHidrometro(idImovel);
					// Verificamos a existencia de poco no imovel
					Integer idPoco = this.repositorioGerencialCadastro
							.verificarExistenciaPoco(idImovel);
					// Verificamos a existencia de volume fixo de agua
					Integer idVolumeFixoAgua = this.repositorioGerencialCadastro
							.verificarExistenciaVolumeFixoAgua(idImovel);
					// Verificamos a existencia de volume fixo de esgoto
					Integer idVolumeFixoEsgoto = this.repositorioGerencialCadastro
							.verificarExistenciaVolumeFixoEsgoto(idImovel);
					
					/***********************************************************************************
					************************************************************************************
					** 2.1 Caso o indicador de tarifa da categoria = 1 (por Categoria);
					************************************************************************************
					***********************************************************************************/
					if (indicadorTarifaCategoria.equals(ConstantesSistema.SIM)) {
						// Filtra a Conta na tabela ContaCategoria por Categoria ordenando pela quantidade
						//de Economia da Categoria;
						List listaContaCategoria = 
							this.repositorioGerencialCadastro.filtrarContaCategoriaHistogramaPorCategoria(idConta);
						
						if (listaContaCategoria != null && !listaContaCategoria.isEmpty()) {
							// 2.1.1 Caso a conta faturada corresponda a UMA so categoria;
							if (listaContaCategoria.size() == 1) {
								Object objContaCategoria = listaContaCategoria.get(0);
								Object[] retornoContaCategoria = (Object[]) objContaCategoria;
								
								Integer idCategoria = (Integer) retornoContaCategoria[0];
								Integer idTipoCategoria = (Integer) retornoContaCategoria[1];
								Short quantidadeEconomia = (Short) retornoContaCategoria[2];
								BigDecimal valorAgua = (BigDecimal) retornoContaCategoria[3];
								Integer consumoAgua = (Integer) retornoContaCategoria[4];
								Integer consumoMinimoAgua = (Integer) retornoContaCategoria[5];
								BigDecimal valorEsgoto = (BigDecimal) retornoContaCategoria[6];
								Integer consumoEsgoto = (Integer) retornoContaCategoria[7];
								Integer consumoMinimoEsgoto = (Integer) retornoContaCategoria[8];
								
								// Pesquisamos a SubCategoria da Conta Categoria
								Integer idSubCategoria = 
									this.repositorioGerencialCadastro.pesquisarSubCategoriaContaCategoria(
											idConta, idCategoria);
								
								//************************************************************
								// Autor: Ivan Sergio
								// Responsavel: Edardo
								// Data: 22/12/2010
								// Caso a subcategoria possua indicador de tarifa de consumo
								// diferente de 1 (SIM), deve-se atribuir o valo 0 (zero);
								//************************************************************
								idSubCategoria = this.validarIndicadorTarifaConsumoSubcategoria(idSubCategoria);
								//************************************************************
								
								// 2.1.1.1 Caso a Conta Faturada corresponda a UMA so economia;
								if (quantidadeEconomia.intValue() == 1) {
									/***************************************************************************
									****************************************************************************
									** [SB0001] - Prepara Dados do Histograma para UMA Categoria e UMA Economia;
									**************************************************************************** 
									***************************************************************************/
									Integer quantidadeConsumoAgua = consumoAgua;
									Integer quantidadeConsumoEsgoto = consumoEsgoto;
									Integer idLigacaoMista = 2;
									//***************************************************************************
									// Histograma Agua Ligacao
									//***************************************************************************
									histogramaAguaLigacaoSemQuadraHelper = 
										this.preparaDadosHistogramaAguaLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoAgua, idConsumoReal,
												idHidrometro, idPoco, idVolumeFixoAgua,
												quantidadeConsumoAgua, quantidadeEconomia, valorAgua,
												consumoAgua, consumoMinimoAgua);
									// Agrupamos o histograma agua ligacao
									if (!histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaAguaLigacao.contains(histogramaAguaLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaLigacao.indexOf(histogramaAguaLigacaoSemQuadraHelper);

											HistogramaAguaLigacaoSemQuadraHelper jaCadastrado = (HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaAguaLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
																						
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaAguaLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaAguaLigacao.add(histogramaAguaLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Agua Economia
									//***************************************************************************
									histogramaAguaEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaAguaEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel, idEsferaPoder,
												idSituacaoAgua, idConsumoReal, idHidrometro,
												idPoco, idVolumeFixoAgua, quantidadeConsumoAgua,
												quantidadeEconomia, valorAgua, consumoAgua,
												consumoMinimoAgua);
									// Agrupamos o histograma agua por economia
									if (!histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaAguaEconomia.contains(histogramaAguaEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaEconomia.indexOf(histogramaAguaEconomiaSemQuadraHelper);
											
											HistogramaAguaEconomiaSemQuadraHelper jaCadastrado = (HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(posicao);
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
										} else {
											listaSimplificadaAguaEconomia.add(histogramaAguaEconomiaSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Ligacao
									//***************************************************************************
									histogramaEsgotoLigacaoSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o Histograma Esgoto por Ligacao
									if (!histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaEsgotoLigacao.contains(histogramaEsgotoLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoLigacao.indexOf(histogramaEsgotoLigacaoSemQuadraHelper);

											HistogramaEsgotoLigacaoSemQuadraHelper jaCadastrado = (HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaEsgotoLigacao.add(histogramaEsgotoLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Economia
									//***************************************************************************
									histogramaEsgotoEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o histograma esgoto por economia
									if (!histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaEsgotoEconomia.contains(histogramaEsgotoEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoEconomia.indexOf(histogramaEsgotoEconomiaSemQuadraHelper);

											HistogramaEsgotoEconomiaSemQuadraHelper jaCadastrado = (HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(posicao);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaEsgotoEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia() + histogramaEsgotoEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
										} else {
											listaSimplificadaEsgotoEconomia.add(histogramaEsgotoEconomiaSemQuadraHelper);
										}
									}
									//***************************************************************************
								
								// 2.1.1.2 Caso contrario (>1)
								}else if (quantidadeEconomia.intValue() > 1)  {
									/***********************************************************************************
									************************************************************************************
									** [SB0002] - Prepara Dados do Histograma para UMA Categoria e MAIS DE UMA Economia;
									************************************************************************************
									***********************************************************************************/
									Integer quantidadeConsumoAgua = consumoAgua;
									Integer quantidadeConsumoAguaEconomia = 0;
									Integer idLigacaoMista = 2;
									
									if (consumoAgua != null && quantidadeEconomia.intValue() != 0) {
										quantidadeConsumoAguaEconomia = (consumoAgua / quantidadeEconomia.intValue());
									}
									Integer quantidadeConsumoEsgoto = consumoEsgoto;
									Integer quantidadeConsumoEsgotoEconomia = 0;
									if (consumoEsgoto != null && quantidadeEconomia.intValue() != 0) {
										quantidadeConsumoEsgotoEconomia = (consumoEsgoto / quantidadeEconomia.intValue());
									}
									//***************************************************************************
									// Histograma Agua Ligacao
									//***************************************************************************
									histogramaAguaLigacaoSemQuadraHelper = 
										this.preparaDadosHistogramaAguaLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoAgua, idConsumoReal,
												idHidrometro, idPoco, idVolumeFixoAgua,
												quantidadeConsumoAgua, quantidadeEconomia, valorAgua,
												consumoAgua, consumoMinimoAgua);
									// Agrupamos o histograma agua ligacao
									if (!histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaAguaLigacao.contains(histogramaAguaLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaLigacao.indexOf(histogramaAguaLigacaoSemQuadraHelper);

											HistogramaAguaLigacaoSemQuadraHelper jaCadastrado = (HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaAguaLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaAguaLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaAguaLigacao.add(histogramaAguaLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Agua Economia
									//***************************************************************************
									histogramaAguaEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaAguaEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel, idEsferaPoder,
												idSituacaoAgua, idConsumoReal, idHidrometro,
												idPoco, idVolumeFixoAgua, quantidadeConsumoAguaEconomia,
												quantidadeEconomia, valorAgua, consumoAgua,
												consumoMinimoAgua);
									// Agrupamos o histograma agua por economia
									if (!histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaAguaEconomia.contains(histogramaAguaEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaEconomia.indexOf(histogramaAguaEconomiaSemQuadraHelper);
											
											HistogramaAguaEconomiaSemQuadraHelper jaCadastrado = (HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(posicao);
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
										} else {
											listaSimplificadaAguaEconomia.add(histogramaAguaEconomiaSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Ligacao
									//***************************************************************************
									histogramaEsgotoLigacaoSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o Histograma Esgoto por Ligacao
									if (!histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaEsgotoLigacao.contains(histogramaEsgotoLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoLigacao.indexOf(histogramaEsgotoLigacaoSemQuadraHelper);

											HistogramaEsgotoLigacaoSemQuadraHelper jaCadastrado = (HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaEsgotoLigacao.add(histogramaEsgotoLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Economia
									//***************************************************************************
									histogramaEsgotoEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgotoEconomia, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o histograma esgoto por economia
									if (!histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaEsgotoEconomia.contains(histogramaEsgotoEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoEconomia.indexOf(histogramaEsgotoEconomiaSemQuadraHelper);

											HistogramaEsgotoEconomiaSemQuadraHelper jaCadastrado = (HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(posicao);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaEsgotoEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia() + histogramaEsgotoEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
										} else {
											listaSimplificadaEsgotoEconomia.add(histogramaEsgotoEconomiaSemQuadraHelper);
										}
									}
									//***************************************************************************
								}
							// 2.1.2 Caso Contrario (a Conta faturada corresponda a MAIS DE UMA Categoria);
							}else if (listaContaCategoria.size() > 1) {
								/*********************************************************************
								**********************************************************************
								// [SB0003] - Prepara Dados do Histograma para MAIS DE UMA Categoria;
								**********************************************************************
								*********************************************************************/
								Integer idCategoria = 0;
								Integer idSubCategoria = 0;
								Integer idTipoCategoria = 0;
								Short quantidadeEconomia = 0;
								BigDecimal valorAgua = new BigDecimal(0);
								Integer consumoAgua = 0;
								Integer consumoMinimoAgua = 0;
								BigDecimal valorEsgoto = new BigDecimal(0);
								Integer consumoEsgoto = 0;
								Integer consumoMinimoEsgoto = 0;
								
								Integer quantidadeConsumoAgua = 0;
								Integer quantidadeConsumoEsgoto = 0;
								
								for(int x = 0; x < listaContaCategoria.size(); x++) {
									Object objContaCategoria = listaContaCategoria.get(x);
									Object[] retornoContaCategoria = (Object[]) objContaCategoria;
									
									if (x == 0) {
										idCategoria = (Integer) retornoContaCategoria[0];
										idTipoCategoria = (Integer) retornoContaCategoria[1];
										
										// Pesquisamos a SubCategoria da Conta Categoria
										idSubCategoria = 
											this.repositorioGerencialCadastro.pesquisarSubCategoriaContaCategoria(
													idConta, idCategoria);
									}
									
									Short valorQuantidadeEconomia = (Short) retornoContaCategoria[2];
									quantidadeEconomia = (short) (quantidadeEconomia + valorQuantidadeEconomia);
									if (retornoContaCategoria[3] != null) {
										valorAgua = valorAgua.add((BigDecimal) retornoContaCategoria[3]);
									}
									if (retornoContaCategoria[4] != null) {
										quantidadeConsumoAgua += (Integer) retornoContaCategoria[4];
										consumoAgua += (Integer) retornoContaCategoria[4];
									}
									if (retornoContaCategoria[5] != null) {
										consumoMinimoAgua += (Integer) retornoContaCategoria[5];
									}
									if (retornoContaCategoria[6] != null) {
										valorEsgoto = valorEsgoto.add((BigDecimal) retornoContaCategoria[6]);
									}
									if (retornoContaCategoria[7] != null) {
										quantidadeConsumoEsgoto += (Integer) retornoContaCategoria[7];
										consumoEsgoto += (Integer) retornoContaCategoria[7];
									}
									if (retornoContaCategoria[8] != null) {
										consumoMinimoEsgoto += (Integer) retornoContaCategoria[8];
									}
									
									// Verificamos o Volume Faturado Agua
									if (consumoAgua != null && consumoMinimoAgua != null) {
										if (consumoAgua >= consumoMinimoAgua) {
											consumoMinimoAgua = consumoAgua;
										} else {
											consumoAgua = consumoMinimoAgua;
										}
									}
									
									// Verificamos o Volume Faturado Agua
									if (consumoEsgoto != null && consumoMinimoEsgoto != null) {
										if (consumoEsgoto >= consumoMinimoEsgoto) {
											consumoMinimoEsgoto = consumoEsgoto;
										} else {
											consumoEsgoto = consumoMinimoEsgoto;
										}
									}
								}
								
								//************************************************************
								// Autor: Ivan Sergio
								// Responsavel: Edardo
								// Data: 22/12/2010
								// Caso a subcategoria possua indicador de tarifa de consumo
								// diferente de 1 (SIM), deve-se atribuir o valo 0 (zero);
								//************************************************************
								idSubCategoria = this.validarIndicadorTarifaConsumoSubcategoria(idSubCategoria);
								//************************************************************
								
								//Integer quantidadeConsumoAgua = consumoAgua;
								Integer quantidadeConsumoAguaEconomia = 0;
								Integer idLigacaoMista = 1;
								
								if (quantidadeConsumoAgua != null && quantidadeEconomia.intValue() != 0) {
									quantidadeConsumoAguaEconomia = (quantidadeConsumoAgua / quantidadeEconomia.intValue());
								}
								
								//Integer quantidadeConsumoEsgoto = consumoEsgoto;
								Integer quantidadeConsumoEsgotoEconomia = 0;
								if (quantidadeConsumoEsgoto != null && quantidadeEconomia.intValue() != 0) {
									quantidadeConsumoEsgotoEconomia = (quantidadeConsumoEsgoto / quantidadeEconomia.intValue());
								}
								
								//***************************************************************************
								// Histograma Agua Ligacao
								//***************************************************************************
								histogramaAguaLigacaoSemQuadraHelper = 
									this.preparaDadosHistogramaAguaLigacao(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idLigacaoMista, idConsumoTarifa, idPerfilImovel,
											idEsferaPoder, idSituacaoAgua, idConsumoReal,
											idHidrometro, idPoco, idVolumeFixoAgua,
											quantidadeConsumoAgua, quantidadeEconomia, valorAgua,
											consumoAgua, consumoMinimoAgua);
								// Agrupamos o histograma agua ligacao
								if (!histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
									if (listaSimplificadaAguaLigacao.contains(histogramaAguaLigacaoSemQuadraHelper)) {
										int posicao = listaSimplificadaAguaLigacao.indexOf(histogramaAguaLigacaoSemQuadraHelper);

										HistogramaAguaLigacaoSemQuadraHelper jaCadastrado = (HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(posicao);
										jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
										jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaAguaLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoLigacao() + 
												histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaAguaLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
									} else {
										listaSimplificadaAguaLigacao.add(histogramaAguaLigacaoSemQuadraHelper);
									}
								}
								
								//***************************************************************************
								// Histograma Agua Economia
								//***************************************************************************
								histogramaAguaEconomiaSemQuadraHelper =
									this.preparaDadosHistogramaAguaEconomia(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idConsumoTarifa, idPerfilImovel, idEsferaPoder,
											idSituacaoAgua, idConsumoReal, idHidrometro,
											idPoco, idVolumeFixoAgua, quantidadeConsumoAguaEconomia,
											quantidadeEconomia, valorAgua, consumoAgua,
											consumoMinimoAgua);
								// Agrupamos o histograma agua por economia
								if (!histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
									if (listaSimplificadaAguaEconomia.contains(histogramaAguaEconomiaSemQuadraHelper)) {
										int posicao = listaSimplificadaAguaEconomia.indexOf(histogramaAguaEconomiaSemQuadraHelper);
										
										HistogramaAguaEconomiaSemQuadraHelper jaCadastrado = (HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(posicao);
										jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
										jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getQuantidadeEconomia());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoEconomia() + 
												histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
									} else {
										listaSimplificadaAguaEconomia.add(histogramaAguaEconomiaSemQuadraHelper);
									}
								}
								
								//***************************************************************************
								// Histograma Esgoto Ligacao
								//***************************************************************************
								histogramaEsgotoLigacaoSemQuadraHelper =
									this.preparaDadosHistogramaEsgotoLigacao(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idLigacaoMista, idConsumoTarifa, idPerfilImovel,
											idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
											idHidrometro, idPoco, idVolumeFixoEsgoto,
											percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
											valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
								// Agrupamos o Histograma Esgoto por Ligacao
								if (!histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
									if (listaSimplificadaEsgotoLigacao.contains(histogramaEsgotoLigacaoSemQuadraHelper)) {
										int posicao = listaSimplificadaEsgotoLigacao.indexOf(histogramaEsgotoLigacaoSemQuadraHelper);

										HistogramaEsgotoLigacaoSemQuadraHelper jaCadastrado = (HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(posicao);
										jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
										jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoLigacao() + 
												histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
									} else {
										listaSimplificadaEsgotoLigacao.add(histogramaEsgotoLigacaoSemQuadraHelper);
									}
								}
								
								//***************************************************************************
								// Histograma Esgoto Economia
								//***************************************************************************
								histogramaEsgotoEconomiaSemQuadraHelper =
									this.preparaDadosHistogramaEsgotoEconomia(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idConsumoTarifa, idPerfilImovel,
											idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
											idHidrometro, idPoco, idVolumeFixoEsgoto,
											percentualEsgoto, quantidadeConsumoEsgotoEconomia, quantidadeEconomia,
											valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
								// Agrupamos o histograma esgoto por economia
								if (!histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
									if (listaSimplificadaEsgotoEconomia.contains(histogramaEsgotoEconomiaSemQuadraHelper)) {
										int posicao = listaSimplificadaEsgotoEconomia.indexOf(histogramaEsgotoEconomiaSemQuadraHelper);

										HistogramaEsgotoEconomiaSemQuadraHelper jaCadastrado = (HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(posicao);
										jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaEsgotoEconomiaSemQuadraHelper.getQuantidadeEconomia());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoEconomia() + 
												histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia() + histogramaEsgotoEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
										jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
									} else {
										listaSimplificadaEsgotoEconomia.add(histogramaEsgotoEconomiaSemQuadraHelper);
									}
								}
							}
						}
					
					/***************************************************************************************
					****************************************************************************************
					** 2.2 Caso o indicador de tarifa da categoria = 2 (por SubCategoria)
					**************************************************************************************** 
					***************************************************************************************/
					} else if (indicadorTarifaCategoria.equals(ConstantesSistema.NAO)) {
						// Filtra a Conta na tabela ContaCategoria por SubCategoria ordenando pela quantidade
						//de Economia da SubCategoria;
						List listaContaCategoria = 
							this.repositorioGerencialCadastro.filtrarContaCategoriaHistograma(idConta);
						
						if (listaContaCategoria != null && !listaContaCategoria.isEmpty()) {
							// 2.2.1 Caso a conta faturada corresponda a UMA so SUBCATEGORIA;
							if (listaContaCategoria.size() == 1) {
								Object objContaCategoria = listaContaCategoria.get(0);
								Object[] retornoContaCategoria = (Object[]) objContaCategoria;
								
								Integer idCategoria = (Integer) retornoContaCategoria[0];
								Integer idTipoCategoria = (Integer) retornoContaCategoria[1];
								Short quantidadeEconomia = (Short) retornoContaCategoria[2];
								BigDecimal valorAgua = (BigDecimal) retornoContaCategoria[3];
								Integer consumoAgua = (Integer) retornoContaCategoria[4];
								Integer consumoMinimoAgua = (Integer) retornoContaCategoria[5];
								BigDecimal valorEsgoto = (BigDecimal) retornoContaCategoria[6];
								Integer consumoEsgoto = (Integer) retornoContaCategoria[7];
								Integer consumoMinimoEsgoto = (Integer) retornoContaCategoria[8];
								Integer idSubCategoria = (Integer) retornoContaCategoria[9];
								
								//************************************************************
								// Autor: Ivan Sergio
								// Responsavel: Edardo
								// Data: 22/12/2010
								// Caso a subcategoria possua indicador de tarifa de consumo
								// diferente de 1 (SIM), deve-se atribuir o valo 0 (zero);
								//************************************************************
								idSubCategoria = this.validarIndicadorTarifaConsumoSubcategoria(idSubCategoria);
								//************************************************************
								
								// 2.2.1.1 Caso a Conta Faturada corresponda a UMA so Economia;
								if (quantidadeEconomia.intValue() == 1) {
									/******************************************************************************
									*******************************************************************************
									** [SB0010] - Prepara Dados do Histograma para UMA SubCategoria e UMA Economia;
									******************************************************************************* 
									******************************************************************************/
									Integer quantidadeConsumoAgua = consumoAgua;
									Integer quantidadeConsumoEsgoto = consumoEsgoto;
									Integer idLigacaoMista = 2;
									//***************************************************************************
									// Histograma Agua Ligacao
									//***************************************************************************
									histogramaAguaLigacaoSemQuadraHelper = 
										this.preparaDadosHistogramaAguaLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoAgua, idConsumoReal,
												idHidrometro, idPoco, idVolumeFixoAgua,
												quantidadeConsumoAgua, quantidadeEconomia, valorAgua,
												consumoAgua, consumoMinimoAgua);
									
									// Agrupamos o histograma agua ligacao
									if (!histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaAguaLigacao.contains(histogramaAguaLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaLigacao.indexOf(histogramaAguaLigacaoSemQuadraHelper);

											HistogramaAguaLigacaoSemQuadraHelper jaCadastrado = (HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaAguaLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaAguaLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaAguaLigacao.add(histogramaAguaLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Agua Economia
									//***************************************************************************
									histogramaAguaEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaAguaEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel, idEsferaPoder,
												idSituacaoAgua, idConsumoReal, idHidrometro,
												idPoco, idVolumeFixoAgua, quantidadeConsumoAgua,
												quantidadeEconomia, valorAgua, consumoAgua,
												consumoMinimoAgua);
									// Agrupamos o histograma agua por economia
									if (!histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaAguaEconomia.contains(histogramaAguaEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaEconomia.indexOf(histogramaAguaEconomiaSemQuadraHelper);
											
											HistogramaAguaEconomiaSemQuadraHelper jaCadastrado = (HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(posicao);
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
										} else {
											listaSimplificadaAguaEconomia.add(histogramaAguaEconomiaSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Ligacao
									//***************************************************************************
									histogramaEsgotoLigacaoSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o Histograma Esgoto por Ligacao
									if (!histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaEsgotoLigacao.contains(histogramaEsgotoLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoLigacao.indexOf(histogramaEsgotoLigacaoSemQuadraHelper);

											HistogramaEsgotoLigacaoSemQuadraHelper jaCadastrado = (HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaEsgotoLigacao.add(histogramaEsgotoLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Economia
									//***************************************************************************
									histogramaEsgotoEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o histograma esgoto por economia
									if (!histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaEsgotoEconomia.contains(histogramaEsgotoEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoEconomia.indexOf(histogramaEsgotoEconomiaSemQuadraHelper);

											HistogramaEsgotoEconomiaSemQuadraHelper jaCadastrado = (HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(posicao);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaEsgotoEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia() + histogramaEsgotoEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
										} else {
											listaSimplificadaEsgotoEconomia.add(histogramaEsgotoEconomiaSemQuadraHelper);
										}
									}
									//***************************************************************************
									
								// 2.2.1.2 Caso contrario (>1)
								}else if (quantidadeEconomia.intValue() > 1)  {
									/**************************************************************************************
									***************************************************************************************
									// [SB0011] - Prepara Dados do Histograma para UMA SubCategoria e MAIS DE UMA Economia;
									***************************************************************************************
									**************************************************************************************/
									Integer quantidadeConsumoAgua = consumoAgua;
									Integer quantidadeConsumoAguaEconomia = 0;
									Integer idLigacaoMista = 2;
									
									if (consumoAgua != null && quantidadeEconomia.intValue() != 0) {
										quantidadeConsumoAguaEconomia = (consumoAgua / quantidadeEconomia.intValue());
									}
									Integer quantidadeConsumoEsgoto = consumoEsgoto;
									Integer quantidadeConsumoEsgotoEconomia = 0;
									if (consumoEsgoto != null && quantidadeEconomia.intValue() != 0) {
										quantidadeConsumoEsgotoEconomia = (consumoEsgoto / quantidadeEconomia.intValue());
									}
									//***************************************************************************
									// Histograma Agua Ligacao
									//***************************************************************************
									histogramaAguaLigacaoSemQuadraHelper = 
										this.preparaDadosHistogramaAguaLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoAgua, idConsumoReal,
												idHidrometro, idPoco, idVolumeFixoAgua,
												quantidadeConsumoAgua, quantidadeEconomia, valorAgua,
												consumoAgua, consumoMinimoAgua);
									
									// Agrupamos o histograma agua ligacao
									if (!histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaAguaLigacao.contains(histogramaAguaLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaLigacao.indexOf(histogramaAguaLigacaoSemQuadraHelper);

											HistogramaAguaLigacaoSemQuadraHelper jaCadastrado = (HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaAguaLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaAguaLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaAguaLigacao.add(histogramaAguaLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Agua Economia
									//***************************************************************************
									histogramaAguaEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaAguaEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel, idEsferaPoder,
												idSituacaoAgua, idConsumoReal, idHidrometro,
												idPoco, idVolumeFixoAgua, quantidadeConsumoAguaEconomia,
												quantidadeEconomia, valorAgua, consumoAgua,
												consumoMinimoAgua);
									// Agrupamos o histograma agua por economia
									if (!histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaAguaEconomia.contains(histogramaAguaEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaAguaEconomia.indexOf(histogramaAguaEconomiaSemQuadraHelper);
											
											HistogramaAguaEconomiaSemQuadraHelper jaCadastrado = (HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(posicao);
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
										} else {
											listaSimplificadaAguaEconomia.add(histogramaAguaEconomiaSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Ligacao
									//***************************************************************************
									histogramaEsgotoLigacaoSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoLigacao(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idLigacaoMista, idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o Histograma Esgoto por Ligacao
									if (!histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
										if (listaSimplificadaEsgotoLigacao.contains(histogramaEsgotoLigacaoSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoLigacao.indexOf(histogramaEsgotoLigacaoSemQuadraHelper);

											HistogramaEsgotoLigacaoSemQuadraHelper jaCadastrado = (HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(posicao);
											jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
											jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoLigacao() + 
													histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
										} else {
											listaSimplificadaEsgotoLigacao.add(histogramaEsgotoLigacaoSemQuadraHelper);
										}
									}
									
									//***************************************************************************
									// Histograma Esgoto Economia
									//***************************************************************************
									histogramaEsgotoEconomiaSemQuadraHelper =
										this.preparaDadosHistogramaEsgotoEconomia(
												idGerenciaRegional, idUnidadeNegocio, idElo,
												idLocalidade, idSetorCormecial, codigoSetorComercial,
												idTipoCategoria, idCategoria, idSubCategoria,
												idConsumoTarifa, idPerfilImovel,
												idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
												idHidrometro, idPoco, idVolumeFixoEsgoto,
												percentualEsgoto, quantidadeConsumoEsgotoEconomia, quantidadeEconomia,
												valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
									// Agrupamos o histograma esgoto por economia
									if (!histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
										if (listaSimplificadaEsgotoEconomia.contains(histogramaEsgotoEconomiaSemQuadraHelper)) {
											int posicao = listaSimplificadaEsgotoEconomia.indexOf(histogramaEsgotoEconomiaSemQuadraHelper);

											HistogramaEsgotoEconomiaSemQuadraHelper jaCadastrado = (HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(posicao);
											jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaEsgotoEconomiaSemQuadraHelper.getQuantidadeEconomia());
											
											BigDecimal valorFaturado = new BigDecimal(
													jaCadastrado.getValorFaturadoEconomia() + 
													histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia())
													.setScale(2, RoundingMode.HALF_UP);
											
											jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
											jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia() + histogramaEsgotoEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
											jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
										} else {
											listaSimplificadaEsgotoEconomia.add(histogramaEsgotoEconomiaSemQuadraHelper);
										}
									}
									//***************************************************************************
								}
							// 2.2.2 Caso contrario, a conta faturada corresponda a MAIS DE UMA SUBCATEGORIA;
							}else if (listaContaCategoria.size() > 1) {
								/***********************************************************************
								************************************************************************
								// [SB0012] - Prepara Dados do Histograma para MAIS DE UMA SubCategoria;
								************************************************************************
								***********************************************************************/
								// Identificamos a categoria principal
			                    Categoria contaCategoriaPrincipal = 
			                    	this.repositorioGerencialCadastro.obterPrincipalCategoriaConta(idConta);
								
								Integer idCategoria = contaCategoriaPrincipal.getId();
								Integer idTipoCategoria = 0;
								Integer idSubCategoria = 0;
								Short quantidadeEconomia = 0;
								BigDecimal valorAgua = new BigDecimal(0);
								Integer consumoAgua = 0;
								Integer consumoMinimoAgua = 0;
								BigDecimal valorEsgoto = new BigDecimal(0);
								Integer consumoEsgoto = 0;
								Integer consumoMinimoEsgoto = 0;
								
								// Utilizado para verificar ligacoes mistas
								Integer idCategoriaAtual = null;
								Integer idCategoriaAnterior = 0;
								Integer idLigacaoMista = 2;
								
								Integer quantidadeConsumoAgua = 0;
								Integer quantidadeConsumoEsgoto = 0;
								
								for(int x = 0; x < listaContaCategoria.size(); x++) {
									Object objContaCategoria = listaContaCategoria.get(x);
									Object[] retornoContaCategoria = (Object[]) objContaCategoria;
									
									idCategoriaAtual = (Integer) retornoContaCategoria[0];
									if (!idCategoriaAtual.equals(idCategoriaAnterior) && !idCategoriaAnterior.equals(0)) {
										idLigacaoMista = 1;
									}
									idCategoriaAnterior = idCategoriaAtual;
									
									// Seleciona o Tipo e a SubCategoria da Principal Categoria da ContaCategoria
									if (idCategoria.equals((Integer) retornoContaCategoria[0])) {
										// Caso ja encontrado nao preenche mais;
										if (idTipoCategoria.equals(0)) {
											idTipoCategoria = (Integer) retornoContaCategoria[1];
											idSubCategoria = (Integer) retornoContaCategoria[9];
										}
									}
									
									Short valorQuantidadeEconomia = (Short) retornoContaCategoria[2];
									quantidadeEconomia = (short) (quantidadeEconomia + valorQuantidadeEconomia);
									if (retornoContaCategoria[3] != null) {
										valorAgua = valorAgua.add((BigDecimal) retornoContaCategoria[3]);
									}
									if (retornoContaCategoria[4] != null) {
										quantidadeConsumoAgua += (Integer) retornoContaCategoria[4];
										consumoAgua += (Integer) retornoContaCategoria[4];
									}
									if (retornoContaCategoria[5] != null) {
										consumoMinimoAgua += (Integer) retornoContaCategoria[5];
									}
									if (retornoContaCategoria[6] != null) {
										valorEsgoto = valorEsgoto.add((BigDecimal) retornoContaCategoria[6]);
									}
									if (retornoContaCategoria[7] != null) {
										quantidadeConsumoEsgoto += (Integer) retornoContaCategoria[7];
										consumoEsgoto += (Integer) retornoContaCategoria[7];
									}
									if (retornoContaCategoria[8] != null) {
										consumoMinimoEsgoto += (Integer) retornoContaCategoria[8];
									}
									
									// Verificamos o Volume Faturado Agua
									if (consumoAgua != null && consumoMinimoAgua != null) {
										if (consumoAgua >= consumoMinimoAgua) {
											consumoMinimoAgua = consumoAgua;
										} else {
											consumoAgua = consumoMinimoAgua;
										}
									}
									
									// Verificamos o Volume Faturado Agua
									if (consumoEsgoto != null && consumoMinimoEsgoto != null) {
										if (consumoEsgoto >= consumoMinimoEsgoto) {
											consumoMinimoEsgoto = consumoEsgoto;
										} else {
											consumoEsgoto = consumoMinimoEsgoto;
										}
									}
								}
								
								//************************************************************
								// Autor: Ivan Sergio
								// Responsavel: Edardo
								// Data: 22/12/2010
								// Caso a subcategoria possua indicador de tarifa de consumo
								// diferente de 1 (SIM), deve-se atribuir o valo 0 (zero);
								//************************************************************
								idSubCategoria = this.validarIndicadorTarifaConsumoSubcategoria(idSubCategoria);
								//************************************************************
								
								//Integer quantidadeConsumoAgua = consumoAgua;
								Integer quantidadeConsumoAguaEconomia = 0;
								if (quantidadeConsumoAgua != null && quantidadeEconomia.intValue() != 0) {
									quantidadeConsumoAguaEconomia = (quantidadeConsumoAgua / quantidadeEconomia.intValue());
								}
								
								//Integer quantidadeConsumoEsgoto = consumoEsgoto;
								Integer quantidadeConsumoEsgotoEconomia = 0;
								if (quantidadeConsumoEsgoto != null && quantidadeEconomia.intValue() != 0) {
									quantidadeConsumoEsgotoEconomia = (quantidadeConsumoEsgoto / quantidadeEconomia.intValue());
								}
								
								//***************************************************************************
								// Histograma Agua Ligacao
								//***************************************************************************
								histogramaAguaLigacaoSemQuadraHelper = 
									this.preparaDadosHistogramaAguaLigacao(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idLigacaoMista, idConsumoTarifa, idPerfilImovel,
											idEsferaPoder, idSituacaoAgua, idConsumoReal,
											idHidrometro, idPoco, idVolumeFixoAgua,
											quantidadeConsumoAgua, quantidadeEconomia, valorAgua,
											consumoAgua, consumoMinimoAgua);
								
								// Agrupamos o histograma agua ligacao
								if (!histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
									if (listaSimplificadaAguaLigacao.contains(histogramaAguaLigacaoSemQuadraHelper)) {
										int posicao = listaSimplificadaAguaLigacao.indexOf(histogramaAguaLigacaoSemQuadraHelper);

										HistogramaAguaLigacaoSemQuadraHelper jaCadastrado = (HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(posicao);
										jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
										jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaAguaLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoLigacao() + 
												histogramaAguaLigacaoSemQuadraHelper.getValorFaturadoLigacao())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaAguaLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
									} else {
										listaSimplificadaAguaLigacao.add(histogramaAguaLigacaoSemQuadraHelper);
									}
								}
								
								//***************************************************************************
								// Histograma Agua Economia
								//***************************************************************************
								histogramaAguaEconomiaSemQuadraHelper =
									this.preparaDadosHistogramaAguaEconomia(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idConsumoTarifa, idPerfilImovel, idEsferaPoder,
											idSituacaoAgua, idConsumoReal, idHidrometro,
											idPoco, idVolumeFixoAgua, quantidadeConsumoAguaEconomia,
											quantidadeEconomia, valorAgua, consumoAgua,
											consumoMinimoAgua);
								// Agrupamos o histograma agua por economia
								if (!histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
									if (listaSimplificadaAguaEconomia.contains(histogramaAguaEconomiaSemQuadraHelper)) {
										int posicao = listaSimplificadaAguaEconomia.indexOf(histogramaAguaEconomiaSemQuadraHelper);
										
										HistogramaAguaEconomiaSemQuadraHelper jaCadastrado = (HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(posicao);
										jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
										jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getQuantidadeEconomia());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoEconomia() + 
												histogramaAguaEconomiaSemQuadraHelper.getValorFaturadoEconomia())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia()	+ histogramaAguaEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
									} else {
										listaSimplificadaAguaEconomia.add(histogramaAguaEconomiaSemQuadraHelper);
									}
								}
								
								//***************************************************************************
								// Histograma Esgoto Ligacao
								//***************************************************************************
								histogramaEsgotoLigacaoSemQuadraHelper =
									this.preparaDadosHistogramaEsgotoLigacao(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idLigacaoMista, idConsumoTarifa, idPerfilImovel,
											idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
											idHidrometro, idPoco, idVolumeFixoEsgoto,
											percentualEsgoto, quantidadeConsumoEsgoto, quantidadeEconomia,
											valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
								// Agrupamos o Histograma Esgoto por Ligacao
								if (!histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao().equals(0f)) {
									if (listaSimplificadaEsgotoLigacao.contains(histogramaEsgotoLigacaoSemQuadraHelper)) {
										int posicao = listaSimplificadaEsgotoLigacao.indexOf(histogramaEsgotoLigacaoSemQuadraHelper);

										HistogramaEsgotoLigacaoSemQuadraHelper jaCadastrado = (HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(posicao);
										jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao() + 1);
										jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getQuantidadeEconomiaLigacao());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoLigacao() + 
												histogramaEsgotoLigacaoSemQuadraHelper.getValorFaturadoLigacao())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoLigacao(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() + histogramaEsgotoLigacaoSemQuadraHelper.getVolumeFaturadoLigacao());
									} else {
										listaSimplificadaEsgotoLigacao.add(histogramaEsgotoLigacaoSemQuadraHelper);
									}
								}
								
								//***************************************************************************
								// Histograma Esgoto Economia
								//***************************************************************************
								histogramaEsgotoEconomiaSemQuadraHelper =
									this.preparaDadosHistogramaEsgotoEconomia(
											idGerenciaRegional, idUnidadeNegocio, idElo,
											idLocalidade, idSetorCormecial, codigoSetorComercial,
											idTipoCategoria, idCategoria, idSubCategoria,
											idConsumoTarifa, idPerfilImovel,
											idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
											idHidrometro, idPoco, idVolumeFixoEsgoto,
											percentualEsgoto, quantidadeConsumoEsgotoEconomia, quantidadeEconomia,
											valorEsgoto, consumoEsgoto, consumoMinimoEsgoto);
								// Agrupamos o histograma esgoto por economia
								if (!histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia().equals(0f)) {
									if (listaSimplificadaEsgotoEconomia.contains(histogramaEsgotoEconomiaSemQuadraHelper)) {
										int posicao = listaSimplificadaEsgotoEconomia.indexOf(histogramaEsgotoEconomiaSemQuadraHelper);

										HistogramaEsgotoEconomiaSemQuadraHelper jaCadastrado = (HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(posicao);
										jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()	+ histogramaEsgotoEconomiaSemQuadraHelper.getQuantidadeEconomia());
										
										BigDecimal valorFaturado = new BigDecimal(
												jaCadastrado.getValorFaturadoEconomia() + 
												histogramaEsgotoEconomiaSemQuadraHelper.getValorFaturadoEconomia())
												.setScale(2, RoundingMode.HALF_UP);
										
										jaCadastrado.setValorFaturadoEconomia(valorFaturado.floatValue());
										jaCadastrado.setVolumeFaturadoEconomia(jaCadastrado.getVolumeFaturadoEconomia() + histogramaEsgotoEconomiaSemQuadraHelper.getVolumeFaturadoEconomia());
										jaCadastrado.setQuantidadeLigacoes(jaCadastrado.getQuantidadeLigacoes() + 1);
									} else {
										listaSimplificadaEsgotoEconomia.add(histogramaEsgotoEconomiaSemQuadraHelper);
									}
								}
							}
						}
					}
				}
			}
			
			/****************************************************************
			* Inseri Histograma Agua Ligacao
			****************************************************************/
			System.out.println("HISTOGRAMA - INSERINDO AGUA LIGACAO: " + listaSimplificadaAguaLigacao.size() + " registros.");
			for (int i = 0; i < listaSimplificadaAguaLigacao.size(); i++) {
				this.repositorioGerencialCadastro.inserirHistogramaAguaLigacaoSemQuadra(
						anoMesReferencia,
						(HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(i));
			}
			/****************************************************************
			* Inseri Histograma Agua Economia
			****************************************************************/
			System.out.println("HISTOGRAMA - INSERINDO AGUA ECONOMIA: " + listaSimplificadaAguaEconomia.size() + " registros.");
			for (int i = 0; i < listaSimplificadaAguaEconomia.size(); i++) {
				this.repositorioGerencialCadastro.inserirHistogramaAguaEconomimaSemQuadra(
						anoMesReferencia,
						(HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(i));
			}
			/****************************************************************
			* Inseri Histograma Esgoto Ligacao
			****************************************************************/
			System.out.println("HISTOGRAMA - INSERINDO ESGOTO LIGACAO: " + listaSimplificadaEsgotoLigacao.size() + " registros.");
			for (int i = 0; i < listaSimplificadaEsgotoLigacao.size(); i++) {
				this.repositorioGerencialCadastro.inserirHistogramaEsgotoLigacaoSemQuadra(
						anoMesReferencia,
						(HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(i));
			}
			/****************************************************************
			* Inseri Histograma Esgoto Economia
			****************************************************************/
			System.out.println("HISTOGRAMA - INSERINDO ESGOTO ECONOMIA: " + listaSimplificadaEsgotoEconomia.size() + " registros.");
			for (int i = 0; i < listaSimplificadaEsgotoEconomia.size(); i++) {
				this.repositorioGerencialCadastro.inserirHistogramaEsgotoEconomiaSemQuadra(
						anoMesReferencia,
						(HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(i));
			}
			
			this.gerarResumoHistogramaImoveisNaoFaturadosSemQuadra(
					anoMesReferencia, idSetor, idFuncionalidadeIniciada);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			System.out.println(" ERRO NO SETOR COMERCIAL " + idSetor);
			ex.printStackTrace();			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	/***
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * [SB0001] - Prepara Dados do Histograma Agua Ligacao
	 * @author Ivan Sergio
	 * @date: 13/08/2010
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idElo
	 * @param idLocalidade
	 * @param idSetorCormecial
	 * @param codigoSetorComercial
	 * @param idTipoCategoria
	 * @param idCategoria
	 * @param idSubcategoria
	 * @param idLigacaoMista
	 * @param idConsumoTarifa
	 * @param idPerfilImovel
	 * @param idEsferaPoder
	 * @param idSituacaoAgua
	 * @param idConsumoReal
	 * @param idHidrometro
	 * @param idPoco
	 * @param idVolumeFixoAgua
	 * @param quantidadeConsumoAgua
	 * @param quantidadeEconomia
	 * @param valorAgua
	 * @param consumoAgua
	 * @param consumoMinimoAgua
	 * @return
	 * @throws ControladorException
	 */
	private HistogramaAguaLigacaoSemQuadraHelper preparaDadosHistogramaAguaLigacao(
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo,
			Integer idLocalidade, Integer idSetorCormecial,	Integer codigoSetorComercial,
			Integer idTipoCategoria, Integer idCategoria, Integer idSubcategoria,
			Integer idLigacaoMista, Integer idConsumoTarifa, Integer idPerfilImovel,
			Integer idEsferaPoder, Integer idSituacaoAgua, Integer idConsumoReal,
			Integer idHidrometro, Integer idPoco, Integer idVolumeFixoAgua,
			Integer quantidadeConsumoAgua, Short quantidadeEconomia, BigDecimal valorAgua,
			Integer consumoAgua, Integer consumoMinimoAgua) throws ControladorException {
		
		HistogramaAguaLigacaoSemQuadraHelper histogramaAguaLigacaoSemQuadraHelper = 
			new HistogramaAguaLigacaoSemQuadraHelper(
					idGerenciaRegional, idUnidadeNegocio, idElo,
					idLocalidade, idSetorCormecial, codigoSetorComercial,
					idTipoCategoria, idCategoria, idSubcategoria,
					idLigacaoMista, idConsumoTarifa, idPerfilImovel,
					idEsferaPoder, idSituacaoAgua, idConsumoReal, 
					idHidrometro, idPoco, idVolumeFixoAgua,
					quantidadeConsumoAgua);
		
		try {
			//**********************************************************************************************
			// Prepara dados Agua
			if (valorAgua != null && !valorAgua.equals("0.00")) {
				histogramaAguaLigacaoSemQuadraHelper.setQuantidadeLigacao(1);
				histogramaAguaLigacaoSemQuadraHelper.setQuantidadeEconomiaLigacao(quantidadeEconomia.intValue());
				histogramaAguaLigacaoSemQuadraHelper.setValorFaturadoLigacao(valorAgua.floatValue());
				
				// [SB0004] - Obter Volume Faturado Agua;
				Integer volumeFaturadoAguaLigacao = 0;
				if (consumoAgua != null && consumoMinimoAgua != null) {
					if (consumoAgua >= consumoMinimoAgua) {
						volumeFaturadoAguaLigacao = consumoAgua;
					} else {
						volumeFaturadoAguaLigacao = consumoMinimoAgua;
					}
				}
				histogramaAguaLigacaoSemQuadraHelper.setVolumeFaturadoLigacao(volumeFaturadoAguaLigacao);
			}
			//**********************************************************************************************
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new EJBException(ex);
		}
		
		return histogramaAguaLigacaoSemQuadraHelper;
	}
	
	/***
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * [SB0001] - Prepara Dados do Histograma Agua Economia
	 * @author Ivan Sergio
	 * @date: 13/08/2010
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idElo
	 * @param idLocalidade
	 * @param idSetorCormecial
	 * @param codigoSetorComercial
	 * @param idTipoCategoria
	 * @param idCategoria
	 * @param idSubCategoria
	 * @param idConsumoTarifa
	 * @param idPerfilImovel
	 * @param idEsferaPoder
	 * @param idSituacaoAgua
	 * @param idConsumoReal
	 * @param idHidrometro
	 * @param idPoco
	 * @param idVolumeFixoAgua
	 * @param quantidadeConsumoAgua
	 * @param quantidadeEconomia
	 * @param valorAgua
	 * @param consumoAgua
	 * @param consumoMinimoAgua
	 * @return
	 * @throws ControladorException
	 */
	private HistogramaAguaEconomiaSemQuadraHelper preparaDadosHistogramaAguaEconomia(
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo,
			Integer idLocalidade, Integer idSetorCormecial, Integer codigoSetorComercial,
			Integer idTipoCategoria, Integer idCategoria, Integer idSubCategoria,
			Integer idConsumoTarifa, Integer idPerfilImovel, Integer idEsferaPoder,
			Integer idSituacaoAgua, Integer idConsumoReal, Integer idHidrometro,
			Integer idPoco, Integer idVolumeFixoAgua, Integer quantidadeConsumoAgua,
			Short quantidadeEconomia, BigDecimal valorAgua, Integer consumoAgua,
			Integer consumoMinimoAgua) throws ControladorException {
		
		HistogramaAguaEconomiaSemQuadraHelper histogramaAguaEconomiaSemQuadraHelper = 
			new HistogramaAguaEconomiaSemQuadraHelper(
					idGerenciaRegional, idUnidadeNegocio, idElo,
					idLocalidade, idSetorCormecial, codigoSetorComercial,
					idTipoCategoria, idCategoria, idSubCategoria,
					idConsumoTarifa, idPerfilImovel, idEsferaPoder,
					idSituacaoAgua, idConsumoReal, idHidrometro,
					idPoco, idVolumeFixoAgua, quantidadeConsumoAgua, 0);
		
		try {
			//**********************************************************************************************
			// Prepara dados Agua
			//**********************************************************************************************
			if (valorAgua != null && !valorAgua.equals("0.00")) {
				histogramaAguaEconomiaSemQuadraHelper.setQuantidadeLigacoes(1);
				histogramaAguaEconomiaSemQuadraHelper.setQuantidadeEconomia(quantidadeEconomia.intValue());
				histogramaAguaEconomiaSemQuadraHelper.setValorFaturadoEconomia(valorAgua.floatValue());
				
				// [SB0004] - Obter Volume Faturado Agua;
				Integer volumeFaturadoAguaEconomia = 0;
				if (consumoAgua != null && consumoMinimoAgua != null) {
					if (consumoAgua >= consumoMinimoAgua) {
						volumeFaturadoAguaEconomia = consumoAgua;
					} else {
						volumeFaturadoAguaEconomia = consumoMinimoAgua;
					}
				}
				histogramaAguaEconomiaSemQuadraHelper.setVolumeFaturadoEconomia(volumeFaturadoAguaEconomia);
			}
			//**********************************************************************************************
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new EJBException(ex);
		}
		
		return histogramaAguaEconomiaSemQuadraHelper;
	}
	
	/***
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * [SB0001] - Prepara Dados do Histograma Esgoto Ligacao
	 * @author Ivan Sergio
	 * @date: 13/08/2010
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idElo
	 * @param idLocalidade
	 * @param idSetorCormecial
	 * @param codigoSetorComercial
	 * @param idTipoCategoria
	 * @param idCategoria
	 * @param idSubCategoria
	 * @param idLigacaoMista
	 * @param idConsumoTarifa
	 * @param idPerfilImovel
	 * @param idEsferaPoder
	 * @param idSituacaoEsgoto
	 * @param idConsumoRealEsgoto
	 * @param idHidrometro
	 * @param idPoco
	 * @param idVolumeFixoEsgoto
	 * @param percentualEsgoto
	 * @param quantidadeConsumoEsgoto
	 * @param quantidadeEconomia
	 * @param valorEsgoto
	 * @param consumoEsgoto
	 * @param consumoMinimoEsgoto
	 * @return
	 * @throws ControladorException
	 */
	private HistogramaEsgotoLigacaoSemQuadraHelper preparaDadosHistogramaEsgotoLigacao(
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo,
			Integer idLocalidade, Integer idSetorCormecial, Integer codigoSetorComercial,
			Integer idTipoCategoria, Integer idCategoria, Integer idSubCategoria,
			Integer idLigacaoMista, Integer idConsumoTarifa, Integer idPerfilImovel,
			Integer idEsferaPoder, Integer idSituacaoEsgoto, Integer idConsumoRealEsgoto,
			Integer idHidrometro, Integer idPoco, Integer idVolumeFixoEsgoto,
			Float percentualEsgoto,	Integer quantidadeConsumoEsgoto, Short quantidadeEconomia,
			BigDecimal valorEsgoto, Integer consumoEsgoto, Integer consumoMinimoEsgoto) throws ControladorException {
		
		HistogramaEsgotoLigacaoSemQuadraHelper histogramaEsgotoLigacaoSemQuadraHelper = 
			new HistogramaEsgotoLigacaoSemQuadraHelper(
					idGerenciaRegional, idUnidadeNegocio, idElo,
					idLocalidade, idSetorCormecial, codigoSetorComercial,
					idTipoCategoria, idCategoria, idSubCategoria,
					idLigacaoMista, idConsumoTarifa, idPerfilImovel,
					idEsferaPoder, idSituacaoEsgoto, idConsumoRealEsgoto,
					idHidrometro, idPoco, idVolumeFixoEsgoto,
					percentualEsgoto, quantidadeConsumoEsgoto);
		
		try {
			//**********************************************************************************************
			// Prepara dados Esgoto
			//**********************************************************************************************
			if (valorEsgoto != null && !valorEsgoto.equals("0.00")) {
				histogramaEsgotoLigacaoSemQuadraHelper.setQuantidadeLigacao(1);
				histogramaEsgotoLigacaoSemQuadraHelper.setQuantidadeEconomiaLigacao(quantidadeEconomia.intValue());
				histogramaEsgotoLigacaoSemQuadraHelper.setValorFaturadoLigacao(valorEsgoto.floatValue());
				
				// [SB0005] - Obter Volume Faturado Esgoto;
				Integer volumeFaturadoEsgotoLigacao = 0;
				if (consumoEsgoto != null && consumoMinimoEsgoto != null) {
					if (consumoEsgoto >= consumoMinimoEsgoto) {
						volumeFaturadoEsgotoLigacao = consumoEsgoto;
					} else {
						volumeFaturadoEsgotoLigacao = consumoMinimoEsgoto;
					}
				}
				histogramaEsgotoLigacaoSemQuadraHelper.setVolumeFaturadoLigacao(volumeFaturadoEsgotoLigacao);
			}
			//**********************************************************************************************
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new EJBException(ex);
		}
		
		return histogramaEsgotoLigacaoSemQuadraHelper;
	}
	
	/***
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * [SB0001] - Prepara Dados do Histograma Esgoto Economia
	 * @author Ivan Sergio
	 * @date: 13/08/2010
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idElo
	 * @param idLocalidade
	 * @param idSetorCormecial
	 * @param codigoSetorComercial
	 * @param idTipoCategoria
	 * @param idCategoria
	 * @param idSubCategoria
	 * @param idConsumoTarifa
	 * @param idPerfilImovel
	 * @param idEsferaPoder
	 * @param idSituacaoEsgoto
	 * @param idConsumoRealEsgoto
	 * @param idHidrometro
	 * @param idPoco
	 * @param idVolumeFixoEsgoto
	 * @param percentualEsgoto
	 * @param quantidadeConsumoEsgoto
	 * @param quantidadeEconomia
	 * @param valorEsgoto
	 * @param consumoEsgoto
	 * @param consumoMinimoEsgoto
	 * @return
	 * @throws ControladorException
	 */
	private HistogramaEsgotoEconomiaSemQuadraHelper preparaDadosHistogramaEsgotoEconomia(
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo,
			Integer idLocalidade, Integer idSetorCormecial, Integer codigoSetorComercial,
			Integer idTipoCategoria, Integer idCategoria, Integer idSubCategoria,
			Integer idConsumoTarifa, Integer idPerfilImovel,
			Integer idEsferaPoder, Integer idSituacaoEsgoto, Integer idConsumoRealEsgoto,
			Integer idHidrometro, Integer idPoco, Integer idVolumeFixoEsgoto,
			Float percentualEsgoto, Integer quantidadeConsumoEsgoto, Short quantidadeEconomia,
			BigDecimal valorEsgoto, Integer consumoEsgoto, Integer consumoMinimoEsgoto) throws ControladorException {
		
		HistogramaEsgotoEconomiaSemQuadraHelper histogramaEsgotoEconomiaSemQuadraHelper = 
			new HistogramaEsgotoEconomiaSemQuadraHelper(
					idGerenciaRegional, idUnidadeNegocio, idElo,
					idLocalidade, idSetorCormecial, codigoSetorComercial,
					idTipoCategoria, idCategoria, idSubCategoria,
					idConsumoTarifa, idPerfilImovel, idEsferaPoder,
					idSituacaoEsgoto, idConsumoRealEsgoto, idHidrometro,
					idPoco, idVolumeFixoEsgoto, percentualEsgoto,
					quantidadeConsumoEsgoto, 0);
		
		try {
			//**********************************************************************************************
			// Prepara dados Esgoto
			//**********************************************************************************************
			if (valorEsgoto != null && !valorEsgoto.equals("0.00")) {
				histogramaEsgotoEconomiaSemQuadraHelper.setQuantidadeLigacoes(1);
				histogramaEsgotoEconomiaSemQuadraHelper.setQuantidadeEconomia(quantidadeEconomia.intValue());
				histogramaEsgotoEconomiaSemQuadraHelper.setValorFaturadoEconomia(valorEsgoto.floatValue());
				
				// [SB0005] - Obter Volume Faturado Esgoto;
				Integer volumeFaturadoEsgotoEconomia = 0;
				if (consumoEsgoto != null && consumoMinimoEsgoto != null) {
					if (consumoEsgoto >= consumoMinimoEsgoto) {
						volumeFaturadoEsgotoEconomia = consumoEsgoto;
					} else {
						volumeFaturadoEsgotoEconomia = consumoMinimoEsgoto;
					}
				}
				histogramaEsgotoEconomiaSemQuadraHelper.setVolumeFaturadoEconomia(volumeFaturadoEsgotoEconomia);
			}
			//**********************************************************************************************
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new EJBException(ex);
		}
		
		return histogramaEsgotoEconomiaSemQuadraHelper;
	}
	
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * [SB2000] Gerar Histograma Imoveis Nao Faturados
	 * @author Ivan Sergio
	 * @date 19/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param idSetor
	 * @param unidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoHistogramaImoveisNaoFaturadosSemQuadra(
			int anoMesReferencia, int idSetor, int unidadeIniciada) throws ControladorException {
		
		try{
			// selecionamos o consumo historico
			List consumoHistoricoHistograma = this.repositorioGerencialCadastro
				.getConsumoHistoricoImoveisNaoFaturadosSemQuadra(idSetor, anoMesReferencia);
	
			List<HistogramaAguaLigacaoSemQuadraHelper> listaSimplificadaAguaLigacao = new ArrayList<HistogramaAguaLigacaoSemQuadraHelper>();
			List<HistogramaAguaEconomiaSemQuadraHelper> listaSimplificadaAguaEconomia = new ArrayList<HistogramaAguaEconomiaSemQuadraHelper>();
			List<HistogramaEsgotoLigacaoSemQuadraHelper> listaSimplificadaEsgotoLigacao = new ArrayList<HistogramaEsgotoLigacaoSemQuadraHelper>();
			List<HistogramaEsgotoEconomiaSemQuadraHelper> listaSimplificadaEsgotoEconomia = new ArrayList<HistogramaEsgotoEconomiaSemQuadraHelper>();
			
			for (int i = 0; i < consumoHistoricoHistograma.size(); i++) {
				Object obj = consumoHistoricoHistograma.get(i);

				if (obj instanceof Object[]) {

					HistogramaAguaLigacaoSemQuadraHelper histogramaAguaLigacaoSemQuadraHelper;
					HistogramaAguaEconomiaSemQuadraHelper histogramaAguaEconomiaSemQuadraHelper;
					HistogramaEsgotoLigacaoSemQuadraHelper histogramaEsgotoLigacaoSemQuadraHelper;
					HistogramaEsgotoEconomiaSemQuadraHelper histogramaEsgotoEconomiaSemQuadraHelper;

					Object[] retorno = (Object[]) obj;

					Integer idImovel = (Integer) retorno[9];
					Imovel imovel = new Imovel();
					imovel.setId(idImovel);
					
					// Selecionamos todas as categorias distintas do imovel
					Collection colCategoriaImovel = this.repositorioGerencialCadastro.getCategoriasImovelDistintas( idImovel );
					Iterator iteCategoriaImovel = colCategoriaImovel.iterator();
					Integer consumoMinimoMes = 0;			
					Integer consumoFaturadoMes = 0;
					Integer ligacaoMista = 0;
					
					// Obtemos o consumo m�nimo de cada categoria do imovel
					while ( iteCategoriaImovel.hasNext() ){
						Categoria categoria = ( Categoria ) iteCategoriaImovel.next();						
						categoria.setConsumoMinimo( this.obterConsumoMinimoCategoria( imovel, categoria ) );
						consumoMinimoMes += categoria.getConsumoMinimo();
						ligacaoMista++;
					}
					
					ligacaoMista = ( ligacaoMista == 1 ? 2 : 1 );
					
					// Selecionamos o consumo faturado do mes
					consumoFaturadoMes = (Integer) retorno[13];
					
					Short quantidadeEconomiasImovel = (Short) retorno[11];
					List<Long> consumosImovelporCategoria = new ArrayList<Long>();
					List<Short> quantidadeEconomiasImovelporCategoria = new ArrayList<Short>();
					
					Categoria categoriaPrincipal = null;
					Short quantidadeEconomiasAnterior = 0;
					// Obtemos o consumo do imovel para cada categoria e selecionamos
					// a principal categoria do imovel
					
					iteCategoriaImovel = colCategoriaImovel.iterator();
					
					while( iteCategoriaImovel.hasNext() ){
						Categoria categoria = ( Categoria ) iteCategoriaImovel.next();
						long fator = ( consumoFaturadoMes - consumoMinimoMes ) / quantidadeEconomiasImovel;
						Short quantidadeEconomias = this.repositorioImovel.pesquisarObterQuantidadeEconomias( imovel, categoria );
						long consumoImovelCategoria = categoria.getConsumoMinimo() + fator * quantidadeEconomias;
						consumosImovelporCategoria.add( consumoImovelCategoria );
						quantidadeEconomiasImovelporCategoria.add( quantidadeEconomias );
						
						// Selecionamos a categoria principal
						if ( categoriaPrincipal == null || quantidadeEconomias > quantidadeEconomiasAnterior ){
							categoriaPrincipal = categoria;
						}
						
						quantidadeEconomiasAnterior = quantidadeEconomias;
					}
					
					// Variaveis para preenchimento dos Helper`s
					Integer idGerenciaRegional = (Integer) retorno[0];
					Integer idUnidadeNegocio = (Integer) retorno[1];
					Integer idElo = (Integer) retorno[2];
					Integer idLocalidade = (Integer) retorno[3];
					Integer idSetorCormecial = (Integer) retorno[4];
					Integer codigoSetorComercial = (Integer) retorno[5];
					
					// Verificamos se o imovel possue categoria mista					
					Integer idConsumoTarifa = (Integer) retorno[6];
					Integer idPerfilImovel = (Integer) retorno[7];
					// Pesquisamos a esfera de poder
					Integer idEsferaPoder = this.repositorioGerencialCadastro
							.pesquisarEsferaPoderClienteResponsavelImovel(idImovel);
                    
                    // Verificamos se a esfera de poder foi encontrada
                    // [FS0002] Verificar existencia de cliente responsavel
                    if (idEsferaPoder.equals(0)) {
                        Cliente clienteTemp = this.getControladorImovel()
                                .consultarClienteUsuarioImovel(imovel);
                        if (clienteTemp != null) {
                            idEsferaPoder = clienteTemp.getClienteTipo()
                                    .getEsferaPoder().getId();
                        }
                    }
                    
					// Obtemos o percentual de esgoto
					Float percentualEsgoto = ( ( retorno[16] ) == null ? 0 : ( ( BigDecimal )retorno[16] ).floatValue() );
					
					Integer idSituacaoAgua = (Integer) retorno[8];
					Integer idSituacaoEsgoto = (Integer) retorno[10];
					Integer idTipoLigacao = (Integer) retorno[12];		
					
					// Verificamos o consumo real
					Integer idConsumoReal = ( ( ( Integer ) retorno[15] ).equals( 1 ) ? 1 : 2 );
					// Verificamos a existencia de hidrometro
					Integer idHidrometro = this.repositorioGerencialCadastro.verificarExistenciaHidrometro(idImovel);
					// Verificamos a existencia de poco no imovel
					Integer idPoco = ( retorno[14] != null && !( ( Integer ) retorno[14] ).equals( 0 ) ? 1 : 2 );
					Integer idVolumeFixoAgua = this.repositorioGerencialCadastro.verificarExistenciaVolumeFixoAgua(idImovel);
					Integer idVolumeFixoEsgoto = this.repositorioGerencialCadastro.verificarExistenciaVolumeFixoEsgoto(idImovel);

					// Valores a serem calculados
					Integer quantidadeLigacaoAgua = 0;
					Integer quantidadeEconomiaLigacaoAgua = 0;
					Short quantidadeEconomiaLigacaoEsgoto = 0;
					Integer quantidadeEconomiaAgua = 0;
					Integer quantidadeEconomiaEsgoto = 0;
					Integer volumeFaturadoLigacaoAgua = 0;
					Long volumeFaturadoEconomiaAgua = 0l;
					Integer quantidadeLigacaoEsgoto = 0;
					Integer volumeFaturadoLigacaoEsgoto = 0;
					Long volumeFaturadoEconomiaEsgoto = 0l;
					Integer quantidadeConsumoAgua = 0;
					Integer quantidadeConsumoEsgoto = 0;
					Integer index = 0;
					
					iteCategoriaImovel = colCategoriaImovel.iterator();

					while (iteCategoriaImovel.hasNext()) {
						// Verificamos se quantidade de economias dessa conta eh
						// maior do que a anterior
						Categoria categoria = (Categoria) iteCategoriaImovel
								.next();

						if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_AGUA ) ){
							/**	***** FIM DADOS PARA A TABELA HISTOGRAMA AGUA LIGACAO ******* **/							
							quantidadeLigacaoAgua++;							
							quantidadeEconomiaLigacaoAgua += ( Short ) quantidadeEconomiasImovelporCategoria.get( index ); 
	
							// Obtemos o consumo faturado de agua
							// Se o consumo de agua for menor do que o minimo * as
							// economias
							// Devemos pegar o minimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoLigacaoAgua += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoLigacaoAgua += consumosImovelporCategoria.get( index ).intValue();
							}
                            
                            quantidadeConsumoAgua += consumosImovelporCategoria.get( index ).intValue();
							/**	***** FIM DADOS PARA A TABELA HISTOGRAMA AGUA LIGACAO ******* **/
							/**	**** DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA ****** **/
							quantidadeEconomiaAgua += ( Short ) quantidadeEconomiasImovelporCategoria.get( index );
							
							// Obtemos o consumo faturado de agua
							// Se o consumo de agua for menor do que o minimo * as
							// economias
							// Devemos pegar o minimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoEconomiaAgua += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoEconomiaAgua += consumosImovelporCategoria.get( index ); // quantidadeEconomiasImovelporCategoria.get( index );
							}
                            
							/**	**** FIM DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA ****** **/							
						}

						if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_ESGOTO ) ){
							/** ***** DADOS PARA A TABELA HISTOGRAMA ESGOTO LIGACAO ****** **/							
							quantidadeLigacaoEsgoto++;
							
							quantidadeEconomiaLigacaoEsgoto = ( Short ) quantidadeEconomiasImovelporCategoria.get( index );
							
							// Obtemos o consumo faturado de esgoto
							// Se o consumo de esgoto for menor do que o minimo * as
							// economias
							// Devemos pegar o minimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoLigacaoEsgoto += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoLigacaoEsgoto += ( ( Long ) consumosImovelporCategoria.get( index ) ).intValue();
							}
							
							quantidadeConsumoEsgoto += consumosImovelporCategoria.get( index ).intValue();
							/** ***** FIM DADOS PARA A TABELA HISTOGRAMA ESGOTO LIGACAO ****** **/
							
							/**	**** DADOS PARA A TABELA HISTOGRAMA ESGOTO ECONOMIA ****** **/
							quantidadeEconomiaEsgoto += ( Short ) quantidadeEconomiasImovelporCategoria.get( index );
							
							// Obtemos o consumo faturado de agua
							// Se o consumo de agua for menor do que o minimo * as
							// economias
							// Devemos pegar o minimo
							if ( ( Long ) consumosImovelporCategoria.get( index ) < categoria.getConsumoMinimo() ){
								volumeFaturadoEconomiaEsgoto += categoria.getConsumoMinimo();
							} else {
								volumeFaturadoEconomiaEsgoto += consumosImovelporCategoria.get( index ); // quantidadeEconomiasImovelporCategoria.get( index );
							}
							/**	**** FIM DADOS PARA A TABELA HISTOGRAMA AGUA ECONOMIA ****** **/							
							
						}
					}
					
					if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_AGUA ) ){
						// Criamos um helper para os histograma de agua por ligacao
						histogramaAguaLigacaoSemQuadraHelper = new HistogramaAguaLigacaoSemQuadraHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(),
								0,
								ligacaoMista, 
								idConsumoTarifa,
								idPerfilImovel, 
								idEsferaPoder, 
								idSituacaoAgua,
								idConsumoReal, 
								idHidrometro, 
								idPoco,
								idVolumeFixoAgua, 
								quantidadeConsumoAgua);
						
						if (listaSimplificadaAguaLigacao.contains(histogramaAguaLigacaoSemQuadraHelper)) {
							int posicao = listaSimplificadaAguaLigacao.indexOf(histogramaAguaLigacaoSemQuadraHelper);

							HistogramaAguaLigacaoSemQuadraHelper jaCadastrado =
								(HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(posicao);
							
							jaCadastrado.setQuantidadeLigacao(
									jaCadastrado.getQuantidadeLigacao() + quantidadeLigacaoAgua);
							
							jaCadastrado.setQuantidadeEconomiaLigacao(
									jaCadastrado.getQuantidadeEconomiaLigacao() + quantidadeEconomiaLigacaoAgua);
							
							jaCadastrado.setVolumeFaturadoLigacao(
									jaCadastrado.getVolumeFaturadoLigacao() + volumeFaturadoLigacaoAgua);
						} else {
							histogramaAguaLigacaoSemQuadraHelper.setQuantidadeLigacao(quantidadeLigacaoAgua);
							histogramaAguaLigacaoSemQuadraHelper.setQuantidadeEconomiaLigacao(
									quantidadeEconomiaLigacaoAgua);
							histogramaAguaLigacaoSemQuadraHelper.setValorFaturadoLigacao( 0f );
							histogramaAguaLigacaoSemQuadraHelper.setVolumeFaturadoLigacao(
									volumeFaturadoLigacaoAgua);
							
							listaSimplificadaAguaLigacao.add(histogramaAguaLigacaoSemQuadraHelper);
						}						
	
						// Criamos um helper para os histograma de agua por economia
						histogramaAguaEconomiaSemQuadraHelper = new HistogramaAguaEconomiaSemQuadraHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(),
								0,
								idConsumoTarifa, 
								idPerfilImovel,
								idEsferaPoder, 
								idSituacaoAgua, 
								idConsumoReal,
								idHidrometro, 
								idPoco, 
								idVolumeFixoAgua,
								quantidadeConsumoAgua,
								quantidadeLigacaoAgua);
						
						if (listaSimplificadaAguaEconomia.contains(histogramaAguaEconomiaSemQuadraHelper)) {
							int posicao = listaSimplificadaAguaEconomia.indexOf(
									histogramaAguaEconomiaSemQuadraHelper);

							HistogramaAguaEconomiaSemQuadraHelper jaCadastrado = 
								(HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(posicao);

							jaCadastrado.setQuantidadeEconomia(
									jaCadastrado.getQuantidadeEconomia() + quantidadeEconomiaAgua);

							jaCadastrado.setVolumeFaturadoEconomia(
									(Integer)jaCadastrado.getVolumeFaturadoEconomia().intValue()
									+ volumeFaturadoEconomiaAgua.intValue());
							
							jaCadastrado.setQuantidadeLigacoes(
									jaCadastrado.getQuantidadeLigacoes() + quantidadeLigacaoAgua);
						} else {
							histogramaAguaEconomiaSemQuadraHelper.setQuantidadeEconomia(
									quantidadeEconomiaAgua);
							histogramaAguaEconomiaSemQuadraHelper.setValorFaturadoEconomia( 0f );
							histogramaAguaEconomiaSemQuadraHelper.setVolumeFaturadoEconomia(
									volumeFaturadoEconomiaAgua.intValue());
							
							listaSimplificadaAguaEconomia.add(histogramaAguaEconomiaSemQuadraHelper);
						}
					}

					if ( idTipoLigacao.equals( LigacaoTipo.LIGACAO_ESGOTO ) ){
						// Criamos um helper para os histograma de Esgoto por ligacao
						histogramaEsgotoLigacaoSemQuadraHelper = new HistogramaEsgotoLigacaoSemQuadraHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(), 
								0,
								ligacaoMista, 
								idConsumoTarifa,
								idPerfilImovel, 
								idEsferaPoder, 
								idSituacaoEsgoto,
								idConsumoReal, 
								idHidrometro, 
								idPoco,
								idVolumeFixoEsgoto, 
								percentualEsgoto,
								quantidadeConsumoEsgoto);
						
						if (listaSimplificadaEsgotoLigacao.contains(histogramaEsgotoLigacaoSemQuadraHelper)) {
							int posicao = listaSimplificadaEsgotoLigacao.indexOf(
									histogramaEsgotoLigacaoSemQuadraHelper);

							HistogramaEsgotoLigacaoSemQuadraHelper jaCadastrado = 
								(HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(
										posicao);

							jaCadastrado.setQuantidadeLigacao(jaCadastrado.getQuantidadeLigacao()
									+ quantidadeLigacaoEsgoto);

							jaCadastrado.setQuantidadeEconomiaLigacao(jaCadastrado.getQuantidadeEconomiaLigacao() 
									+ quantidadeEconomiaLigacaoEsgoto);

							jaCadastrado.setVolumeFaturadoLigacao(jaCadastrado.getVolumeFaturadoLigacao() 
									+ volumeFaturadoLigacaoEsgoto);
						} else {
							histogramaEsgotoLigacaoSemQuadraHelper
									.setQuantidadeLigacao(quantidadeLigacaoEsgoto);

							histogramaEsgotoLigacaoSemQuadraHelper
									.setQuantidadeEconomiaLigacao(quantidadeEconomiaLigacaoEsgoto.intValue());
							
							histogramaEsgotoLigacaoSemQuadraHelper
									.setValorFaturadoLigacao( 0f );

							histogramaEsgotoLigacaoSemQuadraHelper
									.setVolumeFaturadoLigacao(volumeFaturadoLigacaoEsgoto);

							listaSimplificadaEsgotoLigacao
									.add(histogramaEsgotoLigacaoSemQuadraHelper);
						}						
						
						// Criamos um helper para os histograma de Esgoto por Economia
						histogramaEsgotoEconomiaSemQuadraHelper = new HistogramaEsgotoEconomiaSemQuadraHelper(
								idGerenciaRegional, 
								idUnidadeNegocio, 
								idElo,
								idLocalidade, 
								idSetorCormecial,
								codigoSetorComercial, 
								categoriaPrincipal.getCategoriaTipo().getId(),
								categoriaPrincipal.getId(),
								0,
								idConsumoTarifa, 
								idPerfilImovel,
								idEsferaPoder, 
								idSituacaoEsgoto, 
								idConsumoReal,
								idHidrometro, 
								idPoco, 
								idVolumeFixoEsgoto,
								percentualEsgoto, 
								quantidadeConsumoEsgoto,
								quantidadeLigacaoEsgoto);
	
						// Agrupamos o histograma esgoto por economia
						if (listaSimplificadaEsgotoEconomia.contains(histogramaEsgotoEconomiaSemQuadraHelper)) {
							int posicao = listaSimplificadaEsgotoEconomia.indexOf(
									histogramaEsgotoEconomiaSemQuadraHelper);
	
							HistogramaEsgotoEconomiaSemQuadraHelper jaCadastrado = 
								(HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(
										posicao);
	
							jaCadastrado.setQuantidadeEconomia(jaCadastrado.getQuantidadeEconomia()
									+ quantidadeEconomiaEsgoto);
	
							jaCadastrado.setVolumeFaturadoEconomia( ( (Integer) jaCadastrado
									.getVolumeFaturadoEconomia().intValue()
									+ volumeFaturadoEconomiaEsgoto.intValue() ) );
							
							jaCadastrado.setQuantidadeLigacoes(
									jaCadastrado.getQuantidadeLigacoes() + quantidadeLigacaoEsgoto);
	
						} else {
							histogramaEsgotoEconomiaSemQuadraHelper
									.setQuantidadeEconomia(quantidadeEconomiaEsgoto);
							
							histogramaEsgotoEconomiaSemQuadraHelper
									.setValorFaturadoEconomia( 0f );
	
							histogramaEsgotoEconomiaSemQuadraHelper
									.setVolumeFaturadoEconomia(volumeFaturadoEconomiaEsgoto.intValue());
	
							listaSimplificadaEsgotoEconomia
									.add(histogramaEsgotoEconomiaSemQuadraHelper);
						}
					}
				}
			}
			
			System.out.println("HISTOGRAMA NAO FATURADOS - INSERINDO AGUA LIGACAO: " + listaSimplificadaAguaLigacao.size() + " registros.");
			for (int j = 0; j < listaSimplificadaAguaLigacao.size(); j++) {
				this.repositorioGerencialCadastro.inserirHistogramaAguaLigacaoSemQuadra(
								anoMesReferencia,
								(HistogramaAguaLigacaoSemQuadraHelper) listaSimplificadaAguaLigacao.get(j));
			}
			
			System.out.println("HISTOGRAMA NAO FATURADOS - INSERINDO AGUA ECONOMIA: " + listaSimplificadaAguaEconomia.size() + " registros.");
			for (int j = 0; j < listaSimplificadaAguaEconomia.size(); j++) {
				this.repositorioGerencialCadastro.inserirHistogramaAguaEconomimaSemQuadra(
								anoMesReferencia,
								(HistogramaAguaEconomiaSemQuadraHelper) listaSimplificadaAguaEconomia.get(j));
			}
			
			System.out.println("HISTOGRAMA NAO FATURADOS - INSERINDO ESGOTO LIGACAO: " + listaSimplificadaEsgotoLigacao.size() + " registros.");
			for (int j = 0; j < listaSimplificadaEsgotoLigacao.size(); j++) {
				this.repositorioGerencialCadastro.inserirHistogramaEsgotoLigacaoSemQuadra(
								anoMesReferencia,
								(HistogramaEsgotoLigacaoSemQuadraHelper) listaSimplificadaEsgotoLigacao.get(j));
			}
			
			System.out.println("HISTOGRAMA NAO FATURADOS - INSERINDO ESGOTO ECONOMIA: " + listaSimplificadaEsgotoEconomia.size() + " registros.");
			for (int j = 0; j < listaSimplificadaEsgotoEconomia.size(); j++) {
				this.repositorioGerencialCadastro.inserirHistogramaEsgotoEconomiaSemQuadra(
								anoMesReferencia,
								(HistogramaEsgotoEconomiaSemQuadraHelper) listaSimplificadaEsgotoEconomia.get(j));
			}

		} catch (Exception ex) {
			System.out.println(" ERRO NO SETOR COMERCIAL " + idSetor);
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}
	
	/**
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 22/12/2010
	 * 
	 * @param idSubcategoria
	 * @return
	 */
	private Integer validarIndicadorTarifaConsumoSubcategoria(Integer idSubCategoria) throws ControladorException {
		try {
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.ID, idSubCategoria));
			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.INDICADOR_TARIFA_CONSUMO, ConstantesSistema.SIM));
			
			Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(
					this.getControladorUtil().pesquisar(filtroSubCategoria, Subcategoria.class.getName()));
			
			if (subcategoria == null) {
				idSubCategoria = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
		
		return idSubCategoria;
	}
}
