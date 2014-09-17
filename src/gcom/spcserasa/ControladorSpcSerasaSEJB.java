package gcom.spcserasa;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.Processo;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.batch.UnidadeIniciada;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.CpfTipo;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativCritCobrGrupo;
import gcom.cobranca.NegativCritCobrGrupoPK;
import gcom.cobranca.NegativCritElo;
import gcom.cobranca.NegativCritEloPK;
import gcom.cobranca.NegativCritGerReg;
import gcom.cobranca.NegativCritGerRegPK;
import gcom.cobranca.NegativCritNegRetMot;
import gcom.cobranca.NegativCritUndNeg;
import gcom.cobranca.NegativCritUndNegPK;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioClienteTipo;
import gcom.cobranca.NegativacaoCriterioClienteTipoPK;
import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.cobranca.NegativacaoCriterioImovelPerfil;
import gcom.cobranca.NegativacaoCriterioImovelPerfilPK;
import gcom.cobranca.NegativacaoCriterioLigacaoAgua;
import gcom.cobranca.NegativacaoCriterioLigacaoAguaPK;
import gcom.cobranca.NegativacaoCriterioLigacaoEsgoto;
import gcom.cobranca.NegativacaoCriterioLigacaoEsgotoPK;
import gcom.cobranca.NegativacaoCriterioSituacaoCobranca;
import gcom.cobranca.NegativacaoCriterioSituacaoEspecialCobranca;
import gcom.cobranca.NegativacaoCriterioSubcategoria;
import gcom.cobranca.NegativacaoCriterioSubcategoriaPK;
import gcom.cobranca.NegativacaoImoveis;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoGeradosExclusaoHelper;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegItem;
import gcom.cobranca.NegativadorMovimentoRegParcelamento;
import gcom.cobranca.NegativadorMovimentoRegRetMot;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.cobranca.NegativadorResultadoSimulacao;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.ResumoNegativacao;
import gcom.cobranca.ResumoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoTipoCriterioHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.DadosInclusoesComandoNegativacaoHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoFINAL;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativadosSomatorioDadosParcelamentoHelper;
import gcom.gui.cobranca.spcserasa.RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gcom.spcserasa.bean.InserirComandoNegativacaoPorCriterioHelper;
import gcom.spcserasa.bean.NegaticacaoAceitaHelper;
import gcom.spcserasa.bean.NegativacaoMovimentoHelper;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;
import gcom.spcserasa.bean.ObterDocumentosNegativacaoPorPeriodoHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.jboss.logging.Logger;

public class ControladorSpcSerasaSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(ControladorFaturamentoFINAL.class);
	
	SessionContext sessionContext;
	
	private IRepositorioSpcSerasa repositorioSpcSerasa = null;

	public void ejbCreate() throws CreateException {
		repositorioSpcSerasa = RepositorioSpcSerasaHBM.getInstancia();
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

	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorUtilLocal getControladorUtil() {
		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	private ControladorFaturamentoLocal getControladorFaturamento() {
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

	private ControladorEnderecoLocal getControladorEndereco() {
		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorEnderecoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorBatchLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorClienteLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
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
			localHome = (ControladorCadastroLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
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
	
	private Date obterMenorVencimento(List contas, List guias) throws ControladorException {
		Date data = null;
		Conta contaValoresHelper = null;
		GuiaPagamento guiaPagamentoValoresHelper = null;
		
		try {
			data = new Date();
			
			if (contas != null && !contas.isEmpty()) {
				
				for (int i = 0; i < contas.size(); i++) {
					Object conta = contas.get(i);
					
					if (conta instanceof ContaValoresHelper) {
						contaValoresHelper = ((ContaValoresHelper) conta).getConta();
						if (contaValoresHelper.getDataVencimentoConta().before(data)) {
							data = contaValoresHelper.getDataVencimentoConta();
						}
					}
				}
			}

			if (guias != null && !guias.isEmpty()) {
				for (int i = 0; i < guias.size(); i++) {
					Object guia = guias.get(i);
					
					if (guia instanceof GuiaPagamentoValoresHelper) {
						guiaPagamentoValoresHelper = ((GuiaPagamentoValoresHelper) guia).getGuiaPagamento();
						if (guiaPagamentoValoresHelper.getDataVencimento().before(data)) {
							data = guiaPagamentoValoresHelper.getDataVencimento();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
		return data;
	}
	
	private Date obterMaiorVencimento(List contas, List guias) throws ControladorException {
		Date data = null;
		Conta contaValoresHelper = null;
		GuiaPagamento guiaPagamentoValoresHelper = null;
		try {
			data = Util.criarData(1, 1, 1800);
			
			if (contas != null && !contas.isEmpty()) {
				for (int i = 0; i < contas.size(); i++) {
					Object conta = contas.get(i);
					
					if (conta instanceof ContaValoresHelper) {
						contaValoresHelper = ((ContaValoresHelper) conta).getConta();
						if (contaValoresHelper.getDataVencimentoConta().after(data)) {
							data = contaValoresHelper.getDataVencimentoConta();
						}
					}
				}
			}
			
			if (guias != null && !guias.isEmpty()) {
				for (int i = 0; i < guias.size(); i++) {
					Object guia = guias.get(i);
					
					if (guia instanceof GuiaPagamentoValoresHelper) {
						guiaPagamentoValoresHelper = ((GuiaPagamentoValoresHelper) guia).getGuiaPagamento();
						if (guiaPagamentoValoresHelper.getDataVencimento().after(data)) {
							data = guiaPagamentoValoresHelper.getDataVencimento();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return data;
	}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ControladorException
	 */
	public Integer pesquisarComandoNegativacao(ComandoNegativacaoHelper comandoNegativacaoHelper)
			throws ControladorException {

		Integer retorno = 0;

		try {
			retorno = repositorioSpcSerasa.pesquisarComandoNegativacao(comandoNegativacaoHelper);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @param numeroPagina
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarComandoNegativacaoParaPaginacao(ComandoNegativacaoHelper comandoNegativacaoHelper,
			Integer numeroPagina) throws ControladorException {

		Collection pesquisaComandoNegativacao = new ArrayList();
		Collection<ComandoNegativacaoHelper> retorno = new ArrayList<ComandoNegativacaoHelper>();

		try {
			pesquisaComandoNegativacao = repositorioSpcSerasa.pesquisarComandoNegativacaoParaPaginacao(
					comandoNegativacaoHelper, numeroPagina);

			if (pesquisaComandoNegativacao != null && !pesquisaComandoNegativacao.isEmpty()) {

				Iterator comandoNegativacaoIterator = pesquisaComandoNegativacao.iterator();

				while (comandoNegativacaoIterator.hasNext()) {
					ComandoNegativacaoHelper comandoNegativacao = new ComandoNegativacaoHelper();
					Object[] dadosComandoNegativacao = (Object[]) comandoNegativacaoIterator.next();

					if (dadosComandoNegativacao[0] != null) {
						comandoNegativacao.setIdComandoNegativacao((Integer) dadosComandoNegativacao[0]);
					}
					if (dadosComandoNegativacao[1] != null) {
						comandoNegativacao.setTituloComando((String) dadosComandoNegativacao[1]);
					}
					if (dadosComandoNegativacao[2] != null) {
						comandoNegativacao.setIndicadorComandoSimulado((Short) dadosComandoNegativacao[2]);
					}
					if (dadosComandoNegativacao[3] != null) {
						comandoNegativacao.setGeracaoComandoInicio((Date) dadosComandoNegativacao[3]);
					}
					if (dadosComandoNegativacao[4] != null) {
						comandoNegativacao.setExecucaoComandoInicio((Date) dadosComandoNegativacao[4]);
					}
					if (dadosComandoNegativacao[5] != null) {
						comandoNegativacao.setQuantidadeInclusoes((Integer) dadosComandoNegativacao[5]);
					}
					if (dadosComandoNegativacao[6] != null) {
						comandoNegativacao.setNomeUsuarioResponsavel((String) dadosComandoNegativacao[6]);
					}
					retorno.add(comandoNegativacao);
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return retorno;
	}

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ControladorException
	 */
	public Integer pesquisarDadosInclusoesComandoNegativacao(Integer idComandoNegativacao) throws ControladorException {

		Integer retorno = 0;
		try {
			retorno = repositorioSpcSerasa.pesquisarDadosInclusoesComandoNegativacao(idComandoNegativacao);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return retorno;
	}

	/**
	 * [UC 0653] Pesquisar Comando Negativa��o
	 * 
	 * @author K�ssia Albuquerque
	 * @date 29/10/2007
	 * @param idComandoNegativacao
	 * @param numeroPagina
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(Integer idComandoNegativacao,
			Integer numeroPagina) throws ControladorException {

		Collection pesquisaDadosInclusoesComandoNegativacao = new ArrayList();
		Collection<DadosInclusoesComandoNegativacaoHelper> retorno = new ArrayList<DadosInclusoesComandoNegativacaoHelper>();

		try {
			pesquisaDadosInclusoesComandoNegativacao = repositorioSpcSerasa
					.pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(idComandoNegativacao, numeroPagina);

			if (pesquisaDadosInclusoesComandoNegativacao != null && !pesquisaDadosInclusoesComandoNegativacao.isEmpty()) {

				Iterator dadosInclusoesComandoNegativacaoIterator = pesquisaDadosInclusoesComandoNegativacao.iterator();

				while (dadosInclusoesComandoNegativacaoIterator.hasNext()) {

					DadosInclusoesComandoNegativacaoHelper dadosInclusoesComandoNegativacaoHelper = new DadosInclusoesComandoNegativacaoHelper();
					Object[] dadosInclusoesComandoNegativacao = (Object[]) dadosInclusoesComandoNegativacaoIterator
							.next();

					if (dadosInclusoesComandoNegativacao[0] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setNomeCliente((String) dadosInclusoesComandoNegativacao[0]);
					}
					if (dadosInclusoesComandoNegativacao[1] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setQuantidadeInclusoes((Integer) dadosInclusoesComandoNegativacao[1]);
					}
					if (dadosInclusoesComandoNegativacao[2] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setValorTotalDebito((BigDecimal) dadosInclusoesComandoNegativacao[2]);
					}
					if (dadosInclusoesComandoNegativacao[3] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setQuantidadeItensIncluidos((Integer) dadosInclusoesComandoNegativacao[3]);
					}
					if (dadosInclusoesComandoNegativacao[4] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setIdImovel((Integer) dadosInclusoesComandoNegativacao[4]);
					}
					if (dadosInclusoesComandoNegativacao[5] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setNumeroCpf((String) dadosInclusoesComandoNegativacao[5]);
					}
					if (dadosInclusoesComandoNegativacao[6] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setNumeroCnpj((String) dadosInclusoesComandoNegativacao[6]);
					}
					if (dadosInclusoesComandoNegativacao[7] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setValorDebito((BigDecimal) dadosInclusoesComandoNegativacao[7]);
					}
					if (dadosInclusoesComandoNegativacao[8] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setDescricaoCobrancaSituacao((String) dadosInclusoesComandoNegativacao[8]);
					}
					if (dadosInclusoesComandoNegativacao[9] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setDataSituacaoDebito((Date) dadosInclusoesComandoNegativacao[9]);
					}
					if (dadosInclusoesComandoNegativacao[10] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setIndicadorAceito((Short) dadosInclusoesComandoNegativacao[10]);
					}
					if (dadosInclusoesComandoNegativacao[11] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setIndicadorCorrecao((Short) dadosInclusoesComandoNegativacao[11]);
					}
					if (dadosInclusoesComandoNegativacao[12] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setCodigoExclusaoTipo((Integer) dadosInclusoesComandoNegativacao[12]);
					}
					if (dadosInclusoesComandoNegativacao[13] != null) {
						dadosInclusoesComandoNegativacaoHelper
								.setNomeUsuario((String) dadosInclusoesComandoNegativacao[13]);
					}

					retorno.add(dadosInclusoesComandoNegativacaoHelper);
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return retorno;
	}

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
			throws ControladorException {

		ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper = null;
		Object[] pesquisaParametrosComandoNegativacao = null;
		Collection pesquisaTitularidadeNegativacao = null;
		Collection pesquisaGrupoCobranca = null;
		Collection pesquisaGerenciaRegional = null;
		Collection pesquisaUnidadeNegocio = null;
		Collection pesquisaEloPolo = null;
		Collection pesquisaSitLigacaoAgua = null;
		Collection pesquisaSitLigacaoEsgoto = null;
		Collection pesquisaSubcategoria = null;
		Collection pesquisaPerfilImovel = null;
		Collection pesquisaTipoCliente = null;

		try {
			pesquisaParametrosComandoNegativacao = repositorioSpcSerasa.pesquisarParametrosComandoNegativacao(idComandoNegativacao);
			pesquisaTitularidadeNegativacao = repositorioSpcSerasa.pesquisarTitularidadeCpfCnpjNegativacao(idComandoNegativacao);
			pesquisaGrupoCobranca = repositorioSpcSerasa.pesquisarGrupoCobranca(idComandoNegativacao);
			pesquisaGerenciaRegional = repositorioSpcSerasa.pesquisarGerenciaRegional(idComandoNegativacao);
			pesquisaUnidadeNegocio = repositorioSpcSerasa.pesquisarUnidadeNegocio(idComandoNegativacao);
			pesquisaEloPolo = repositorioSpcSerasa.pesquisarEloPolo(idComandoNegativacao);
			pesquisaSitLigacaoAgua = repositorioSpcSerasa.pesquisarSituacaoLigacaoAguaComando(idComandoNegativacao);
			pesquisaSitLigacaoEsgoto = repositorioSpcSerasa.pesquisarSituacaoLigacaoEsgotoComando(idComandoNegativacao);
			pesquisaSubcategoria = repositorioSpcSerasa.pesquisarSubcategoria(idComandoNegativacao);
			pesquisaPerfilImovel = repositorioSpcSerasa.pesquisarPerfilImovel(idComandoNegativacao);
			pesquisaTipoCliente = repositorioSpcSerasa.pesquisarTipoCliente(idComandoNegativacao);

		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

		if (pesquisaParametrosComandoNegativacao != null && !(pesquisaParametrosComandoNegativacao.equals(""))) {
			parametrosComandoNegativacaoHelper = new ParametrosComandoNegativacaoHelper();
			parametrosComandoNegativacaoHelper = setDadosGerais(parametrosComandoNegativacaoHelper,
					pesquisaParametrosComandoNegativacao, pesquisaTitularidadeNegativacao);
			parametrosComandoNegativacaoHelper = setDadosDebito(parametrosComandoNegativacaoHelper,
					pesquisaParametrosComandoNegativacao);
			parametrosComandoNegativacaoHelper = setDadosImovel(parametrosComandoNegativacaoHelper,
					pesquisaParametrosComandoNegativacao, pesquisaSitLigacaoAgua, pesquisaSitLigacaoEsgoto,
					pesquisaSubcategoria, pesquisaPerfilImovel, pesquisaTipoCliente);
			parametrosComandoNegativacaoHelper = setDadosLocalizacao(parametrosComandoNegativacaoHelper,
					pesquisaParametrosComandoNegativacao, pesquisaGrupoCobranca, pesquisaGerenciaRegional,
					pesquisaUnidadeNegocio, pesquisaEloPolo);
			parametrosComandoNegativacaoHelper = setDadosExclusao(parametrosComandoNegativacaoHelper,
					pesquisaParametrosComandoNegativacao);
		}
		return parametrosComandoNegativacaoHelper;

	}

	/**
	 * [UC0653] Pesquisar Comando Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @data 08/07/2010
	 */
	private ParametrosComandoNegativacaoHelper setDadosExclusao(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper,
			Object[] pesquisaParametrosComandoNegativacao) throws ControladorException {

		try {

			// Seta a Quantidade de Dias
			if (pesquisaParametrosComandoNegativacao[43] != null) {
				parametrosComandoNegativacaoHelper
						.setQuantidadeDias((Integer) pesquisaParametrosComandoNegativacao[43]);
			}

			if (pesquisaParametrosComandoNegativacao[44] != null) {

				Collection colecaoNegativadorRetornoMotivo = repositorioSpcSerasa
						.pesquisarNegativadorRetornoMotivo((Integer) pesquisaParametrosComandoNegativacao[44]);
				// Seta a cole��o de Motivo de Retorno
				if (colecaoNegativadorRetornoMotivo != null && !colecaoNegativadorRetornoMotivo.isEmpty()) {
					parametrosComandoNegativacaoHelper.setColecaoMotivoRetorno(colecaoNegativadorRetornoMotivo);
				}

			}

			return parametrosComandoNegativacaoHelper;

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	private ParametrosComandoNegativacaoHelper setDadosLocalizacao(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper,
			Object[] pesquisaParametrosComandoNegativacao, Collection pesquisaGrupoCobranca,
			Collection pesquisaGerenciaRegional, Collection pesquisaUnidadeNegocio, Collection pesquisaEloPolo) {
		// Seta a ColecaoGrupoCobranca
		ParametrosComandoNegativacaoHelper objetoCobrancaGrupo = setCobrancaGrupo(parametrosComandoNegativacaoHelper,
				pesquisaGrupoCobranca);
		if (objetoCobrancaGrupo.getColecaoGrupoCobranca() != null) {
			parametrosComandoNegativacaoHelper.setColecaoGrupoCobranca(objetoCobrancaGrupo.getColecaoGrupoCobranca());
		}

		// Seta a ColecaoGerenciaRegional
		ParametrosComandoNegativacaoHelper objetoGerenciaRegional = setGerenciaRegional(
				parametrosComandoNegativacaoHelper, pesquisaGerenciaRegional);
		if (objetoGerenciaRegional.getColecaoGerenciaRegional() != null) {
			parametrosComandoNegativacaoHelper.setColecaoGerenciaRegional(objetoGerenciaRegional
					.getColecaoGerenciaRegional());
		}
		// Seta a ColecaoUnidadeNegocio
		ParametrosComandoNegativacaoHelper objetoUnidadeNegocio = setUnidadeNegocio(parametrosComandoNegativacaoHelper,
				pesquisaUnidadeNegocio);
		if (objetoUnidadeNegocio.getColecaoUnidadeNegocio() != null) {
			parametrosComandoNegativacaoHelper
					.setColecaoUnidadeNegocio(objetoUnidadeNegocio.getColecaoUnidadeNegocio());
		}

		// Seta a ColecaoEloPolo
		ParametrosComandoNegativacaoHelper objetoEloPolo = setEloPolo(parametrosComandoNegativacaoHelper,
				pesquisaEloPolo);
		if (objetoEloPolo.getColecaoEloPolo() != null) {
			parametrosComandoNegativacaoHelper.setColecaoEloPolo(objetoEloPolo.getColecaoEloPolo());
		}
		// Seta id Localidade Inicial
		if (pesquisaParametrosComandoNegativacao[38] != null) {
			parametrosComandoNegativacaoHelper.setIdLocInicial((Integer) pesquisaParametrosComandoNegativacao[38]);
		}
		// Seta a Localidade Inicial
		if (pesquisaParametrosComandoNegativacao[29] != null) {
			parametrosComandoNegativacaoHelper.setLocInicial((String) pesquisaParametrosComandoNegativacao[29]);
		}
		// Seta id Localidade Final
		if (pesquisaParametrosComandoNegativacao[39] != null) {
			parametrosComandoNegativacaoHelper.setIdLocFinal((Integer) pesquisaParametrosComandoNegativacao[39]);
		}
		// Seta a Localidade Final
		if (pesquisaParametrosComandoNegativacao[30] != null) {
			parametrosComandoNegativacaoHelper.setLocFinal((String) pesquisaParametrosComandoNegativacao[30]);
		}
		// Seta id Setor Comencial Inicial
		if (pesquisaParametrosComandoNegativacao[40] != null) {
			parametrosComandoNegativacaoHelper.setCodSetComInicial((Integer) pesquisaParametrosComandoNegativacao[40]);
		}
		// Seta o Setor Comencial Inicial
		if (pesquisaParametrosComandoNegativacao[31] != null) {
			parametrosComandoNegativacaoHelper.setSetComInicial((String) pesquisaParametrosComandoNegativacao[31]);
		}
		// Seta id Setor Comencial Fianl
		if (pesquisaParametrosComandoNegativacao[41] != null) {
			parametrosComandoNegativacaoHelper.setCodSetComFinal((Integer) pesquisaParametrosComandoNegativacao[41]);
		}
		// Seta o Setor Comencial Final
		if (pesquisaParametrosComandoNegativacao[32] != null) {
			parametrosComandoNegativacaoHelper.setSetComFinal((String) pesquisaParametrosComandoNegativacao[32]);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setDadosImovel(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper,
			Object[] pesquisaParametrosComandoNegativacao, Collection pesquisaSitLigacaoAgua,
			Collection pesquisaSitLigacaoEsgoto, Collection pesquisaSubcategoria, Collection pesquisaPerfilImovel,
			Collection pesquisaTipoCliente) {
		// Seta o ID do Cliente
		if (pesquisaParametrosComandoNegativacao[24] != null) {
			parametrosComandoNegativacaoHelper.setIdCliente((Integer) pesquisaParametrosComandoNegativacao[24]);
		}
		// Seta o nome do Cliente
		if (pesquisaParametrosComandoNegativacao[25] != null) {
			parametrosComandoNegativacaoHelper.setNomeCliente((String) pesquisaParametrosComandoNegativacao[25]);
		}
		// Seta o Tipo de Relacao com o Cliente
		if (pesquisaParametrosComandoNegativacao[26] != null) {
			parametrosComandoNegativacaoHelper.setTipoRelClie((String) pesquisaParametrosComandoNegativacao[26]);
		}
		// Seta o valor do Indicador Especial de Cobranca
		if (pesquisaParametrosComandoNegativacao[27] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorEspCobranca((Short) pesquisaParametrosComandoNegativacao[27]);
		}
		// Seta o valor do Indicador da Situacao de Cobranca
		if (pesquisaParametrosComandoNegativacao[28] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorSitCobranca((Short) pesquisaParametrosComandoNegativacao[28]);
		}
		// Seta a ColecaoSitLigacaoAgua
		ParametrosComandoNegativacaoHelper objetoSitLigacaoAgua = setSitLigacaoAgua(parametrosComandoNegativacaoHelper,
				pesquisaSitLigacaoAgua);
		if (objetoSitLigacaoAgua.getColecaoSitLigacaoAgua() != null) {
			parametrosComandoNegativacaoHelper
					.setColecaoSitLigacaoAgua(objetoSitLigacaoAgua.getColecaoSitLigacaoAgua());
		}
		// Seta a ColecaoSitLigacaoEsgoto
		ParametrosComandoNegativacaoHelper objetoSitLigacaoEsgoto = setSitLigacaoEsgoto(
				parametrosComandoNegativacaoHelper, pesquisaSitLigacaoEsgoto);
		if (objetoSitLigacaoEsgoto.getColecaoSitLigacaoEsgoto() != null) {
			parametrosComandoNegativacaoHelper.setColecaoSitLigacaoEsgoto(objetoSitLigacaoEsgoto
					.getColecaoSitLigacaoEsgoto());
		}

		// Seta a ColecaoSubcategoria
		ParametrosComandoNegativacaoHelper objetoSubcategoria = setSubcategoria(parametrosComandoNegativacaoHelper,
				pesquisaSubcategoria);
		if (objetoSubcategoria.getColecaoSubcategoria() != null) {
			parametrosComandoNegativacaoHelper.setColecaoSubcategoria(objetoSubcategoria.getColecaoSubcategoria());
		}

		// Seta a ColecaoPerfilImovel
		ParametrosComandoNegativacaoHelper objetoPerfilImovel = setPerfilImovel(parametrosComandoNegativacaoHelper,
				pesquisaPerfilImovel);
		if (objetoPerfilImovel.getColecaoPerfilImovel() != null) {
			parametrosComandoNegativacaoHelper.setColecaoPerfilImovel(objetoPerfilImovel.getColecaoPerfilImovel());
		}
		// Seta a ColecaoTipoCliente
		ParametrosComandoNegativacaoHelper objetoTipoCliente = setTipoCliente(parametrosComandoNegativacaoHelper,
				pesquisaTipoCliente);
		if (objetoTipoCliente.getColecaoTipoCliente() != null) {
			parametrosComandoNegativacaoHelper.setColecaoTipoCliente(objetoTipoCliente.getColecaoTipoCliente());
		}

		if (pesquisaParametrosComandoNegativacao[42] != null) {
			parametrosComandoNegativacaoHelper.setIndicadorBaixaRenda((Short) pesquisaParametrosComandoNegativacao[42]);
		}

		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setDadosDebito(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper,
			Object[] pesquisaParametrosComandoNegativacao) {
		// Seta a Referencia Inicial
		if (pesquisaParametrosComandoNegativacao[10] != null) {
			parametrosComandoNegativacaoHelper.setReferenciaInicial((Integer) pesquisaParametrosComandoNegativacao[10]);
		}
		// Seta a Referencia Final
		if (pesquisaParametrosComandoNegativacao[11] != null) {
			parametrosComandoNegativacaoHelper.setReferenciaFinal((Integer) pesquisaParametrosComandoNegativacao[11]);
		}
		// Seta o Vencimento Inicial
		if (pesquisaParametrosComandoNegativacao[12] != null) {
			parametrosComandoNegativacaoHelper.setVencimentoInicial((Date) pesquisaParametrosComandoNegativacao[12]);
		}
		// Seta o Vencimento Final
		if (pesquisaParametrosComandoNegativacao[13] != null) {
			parametrosComandoNegativacaoHelper.setVencimentoFinal((Date) pesquisaParametrosComandoNegativacao[13]);
		}
		// Seta o Valor Minimo Debito
		if (pesquisaParametrosComandoNegativacao[14] != null) {
			parametrosComandoNegativacaoHelper
					.setValoMinimoDebito((BigDecimal) pesquisaParametrosComandoNegativacao[14]);
		}
		// Seta o Valor Maximo Debito
		if (pesquisaParametrosComandoNegativacao[15] != null) {
			parametrosComandoNegativacaoHelper
					.setValoMaximoDebito((BigDecimal) pesquisaParametrosComandoNegativacao[15]);
		}
		// Seta a Quntidade Minima de Contas
		if (pesquisaParametrosComandoNegativacao[16] != null) {
			parametrosComandoNegativacaoHelper.setQtdMinimaContas((Integer) pesquisaParametrosComandoNegativacao[16]);
		}
		// Seta a Quntidade Maxima de Contas
		if (pesquisaParametrosComandoNegativacao[17] != null) {
			parametrosComandoNegativacaoHelper.setQtdMaximaContas((Integer) pesquisaParametrosComandoNegativacao[17]);
		}
		// Seta o Indicador Conta Revisao
		if (pesquisaParametrosComandoNegativacao[18] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorContaRevisao((Short) pesquisaParametrosComandoNegativacao[18]);
		}
		// Seta o Indicador Guia Pagamento
		if (pesquisaParametrosComandoNegativacao[19] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorGuiaPagamento((Short) pesquisaParametrosComandoNegativacao[19]);
		}
		// Seta o Indicador Parcelamento Atraso
		if (pesquisaParametrosComandoNegativacao[20] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorParcelamentoAtraso((Short) pesquisaParametrosComandoNegativacao[20]);
		}
		// Seta Numero de dias de Atraso do Parcelamento
		if (pesquisaParametrosComandoNegativacao[21] != null) {
			parametrosComandoNegativacaoHelper
					.setNumDiasAtrasoParcelamento((Integer) pesquisaParametrosComandoNegativacao[21]);
		}
		// Seta o Indicador de Recebimento da Carta de Parcelamento em Atraso
		if (pesquisaParametrosComandoNegativacao[22] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorCartaParcelamentoAtraso((Short) pesquisaParametrosComandoNegativacao[22]);
		}
		// Seta Numero de dias de Atraso apos Recebimento da Carta
		if (pesquisaParametrosComandoNegativacao[23] != null) {
			parametrosComandoNegativacaoHelper
					.setNumDiasAtrasoAposRecCarta((Integer) pesquisaParametrosComandoNegativacao[23]);
		}
		// RM4097 - adicionado por Vivianne Sousa - 03/01/2011 - analista:Ana
		// Cristina
		if (pesquisaParametrosComandoNegativacao[45] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorContaNomeCliente((Short) pesquisaParametrosComandoNegativacao[45]);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setDadosGerais(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper,
			Object[] pesquisaParametrosComandoNegativacao, Collection pesquisaTitularidadeNegativacao) {

		// seta o id Negativa��o Crit�rio
		if (pesquisaParametrosComandoNegativacao[34] != null) {
			parametrosComandoNegativacaoHelper
					.setIdNagativacaoCriterio((Integer) pesquisaParametrosComandoNegativacao[34]);
		}
		// seta a data da �ltima altera��o de negativa��o comando
		if (pesquisaParametrosComandoNegativacao[35] != null) {
			parametrosComandoNegativacaoHelper
					.setUltimaAlteracaoNegComando((Date) pesquisaParametrosComandoNegativacao[35]);
		}
		// seta o id do Negativador
		if (pesquisaParametrosComandoNegativacao[36] != null) {
			parametrosComandoNegativacaoHelper.setIdNegativador((Integer) pesquisaParametrosComandoNegativacao[36]);
		}
		// Seta o nome do Negativador
		if (pesquisaParametrosComandoNegativacao[0] != null) {
			parametrosComandoNegativacaoHelper.setNegativador((String) pesquisaParametrosComandoNegativacao[0]);
		}
		// Seta a Quantidade de Inclus�es
		if (pesquisaParametrosComandoNegativacao[1] != null) {
			parametrosComandoNegativacaoHelper.setQtdInclusoes((Integer) pesquisaParametrosComandoNegativacao[1]);
		}
		// Seta o valor Total do D�bito
		if (pesquisaParametrosComandoNegativacao[2] != null) {
			parametrosComandoNegativacaoHelper
					.setValorTotalDebito((BigDecimal) pesquisaParametrosComandoNegativacao[2]);
		}
		// Seta a Quantidade de Intens Inclu�dos
		if (pesquisaParametrosComandoNegativacao[3] != null) {
			parametrosComandoNegativacaoHelper.setQtdItensIncluidos((Integer) pesquisaParametrosComandoNegativacao[3]);
		}
		// Seta o Titulo do Comando
		if (pesquisaParametrosComandoNegativacao[4] != null) {
			parametrosComandoNegativacaoHelper.setTituloComando((String) pesquisaParametrosComandoNegativacao[4]);
		}
		// Seta a Descricao da Solicitacao
		if (pesquisaParametrosComandoNegativacao[5] != null) {
			parametrosComandoNegativacaoHelper
					.setDescricaoSolicitacao((String) pesquisaParametrosComandoNegativacao[5]);
		}
		// Seta o Indicador Simulacao
		if (pesquisaParametrosComandoNegativacao[6] != null) {
			parametrosComandoNegativacaoHelper.setSimularNegativacao((Short) pesquisaParametrosComandoNegativacao[6]);
		}
		// Seta a Data de Execucao
		if (pesquisaParametrosComandoNegativacao[7] != null) {
			parametrosComandoNegativacaoHelper.setDataExecucao((Date) pesquisaParametrosComandoNegativacao[7]);
		}
		// Seta o Usuario Responsavel
		if (pesquisaParametrosComandoNegativacao[33] != null) {
			parametrosComandoNegativacaoHelper.setIdUsuario((Integer) pesquisaParametrosComandoNegativacao[33]);
			parametrosComandoNegativacaoHelper.setUsuarioResponsavel((String) pesquisaParametrosComandoNegativacao[8]);
		}
		// Seta o id do cliente
		if (pesquisaParametrosComandoNegativacao[9] != null) {
			parametrosComandoNegativacaoHelper.setQtdMaxInclusoes((Integer) pesquisaParametrosComandoNegativacao[9]);
		}
		// Titularidade do CPF/CNPJ da Negativacao
		ParametrosComandoNegativacaoHelper objetoTitularidadeNegativacao = setTitularidadeNegativacao(
				parametrosComandoNegativacaoHelper, pesquisaTitularidadeNegativacao);

		if (objetoTitularidadeNegativacao.getColecaoTitularNegativacao() != null
				&& !objetoTitularidadeNegativacao.getColecaoTitularNegativacao().isEmpty()) {
			parametrosComandoNegativacaoHelper.setColecaoTitularNegativacao(objetoTitularidadeNegativacao
					.getColecaoTitularNegativacao());
		}
		// Seta id Comando Simulado
		if (pesquisaParametrosComandoNegativacao[37] != null) {
			parametrosComandoNegativacaoHelper
					.setIdComandoNegativacaoSimulado((Integer) pesquisaParametrosComandoNegativacao[37]);
		}

		// RM3388 - Por: Ivan Sergio. Analista: Adriana. 28/01/2011
		// Seta Indicador Orgao Publico
		if (pesquisaParametrosComandoNegativacao[46] != null) {
			parametrosComandoNegativacaoHelper
					.setIndicadorOrgaoPublico((Short) pesquisaParametrosComandoNegativacao[46]);
		}

		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setTitularidadeNegativacao(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper,
			Collection pesquisaTitularidadeNegativacao) {

		if (pesquisaTitularidadeNegativacao != null && !pesquisaTitularidadeNegativacao.isEmpty()) {

			Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo = new ArrayList();
			Iterator titularidadeNegativacaoIterator = pesquisaTitularidadeNegativacao.iterator();
			while (titularidadeNegativacaoIterator.hasNext()) {
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = new NegativacaoCriterioCpfTipo();
				Object[] titularidadeNegativacao = (Object[]) titularidadeNegativacaoIterator.next();
				if (titularidadeNegativacao[3] != null) {
					CpfTipo cpfTipo = new CpfTipo();
					cpfTipo.setId((Integer) titularidadeNegativacao[3]);
					cpfTipo.setDescricaoTipoCpf((String) titularidadeNegativacao[0]);
					negativacaoCriterioCpfTipo.setCpfTipo(cpfTipo);
				}
				if (titularidadeNegativacao[1] != null) {
					negativacaoCriterioCpfTipo.setNumeroOrdemSelecao((Short) titularidadeNegativacao[1]);
				}
				if (titularidadeNegativacao[2] != null) {
					negativacaoCriterioCpfTipo.setIndicadorCoincidente((Short) titularidadeNegativacao[2]);
				}
				colecaoNegativacaoCriterioCpfTipo.add(negativacaoCriterioCpfTipo);
			}
			parametrosComandoNegativacaoHelper.setColecaoTitularNegativacao(colecaoNegativacaoCriterioCpfTipo);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setCobrancaGrupo(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaGrupoCobranca) {
		if (pesquisaGrupoCobranca != null && !pesquisaGrupoCobranca.isEmpty()) {
			Collection<CobrancaGrupo> colecaoGrupoCobranca = new ArrayList();
			Iterator grupoCobrancaIterator = pesquisaGrupoCobranca.iterator();
			while (grupoCobrancaIterator.hasNext()) {
				CobrancaGrupo cobrancaGrupoAuxiliar = new CobrancaGrupo();
				Object[] grupoCobranca = (Object[]) grupoCobrancaIterator.next();
				if (grupoCobranca[1] != null) {
					cobrancaGrupoAuxiliar.setId((Integer) grupoCobranca[1]);
					cobrancaGrupoAuxiliar.setDescricao((String) grupoCobranca[0]);
				}
				colecaoGrupoCobranca.add(cobrancaGrupoAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoGrupoCobranca(colecaoGrupoCobranca);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setGerenciaRegional(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaGerenciaRegional) {
		if (pesquisaGerenciaRegional != null && !pesquisaGerenciaRegional.isEmpty()) {
			Collection<GerenciaRegional> colecaoGerenciaRegional = new ArrayList();
			Iterator gerenciaRegionalIterator = pesquisaGerenciaRegional.iterator();
			while (gerenciaRegionalIterator.hasNext()) {
				GerenciaRegional gerenciaRegionalAuxiliar = new GerenciaRegional();
				Object[] gerenciaRegional = (Object[]) gerenciaRegionalIterator.next();
				if (gerenciaRegional[1] != null) {
					gerenciaRegionalAuxiliar.setId((Integer) gerenciaRegional[1]);
					gerenciaRegionalAuxiliar.setNome((String) gerenciaRegional[0]);
				}
				colecaoGerenciaRegional.add(gerenciaRegionalAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoGerenciaRegional(colecaoGerenciaRegional);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setUnidadeNegocio(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaUnidadeNegocio) {

		if (pesquisaUnidadeNegocio != null && !pesquisaUnidadeNegocio.isEmpty()) {
			Collection<UnidadeNegocio> colecaoUnidadeNegocio = new ArrayList();
			Iterator unidadeNegocioIterator = pesquisaUnidadeNegocio.iterator();
			while (unidadeNegocioIterator.hasNext()) {
				UnidadeNegocio unidadeNegocioAuxiliar = new UnidadeNegocio();
				Object[] unidadeNegocio = (Object[]) unidadeNegocioIterator.next();
				if (unidadeNegocio[1] != null) {
					unidadeNegocioAuxiliar.setId((Integer) unidadeNegocio[1]);
					unidadeNegocioAuxiliar.setNome((String) unidadeNegocio[0]);
				}
				colecaoUnidadeNegocio.add(unidadeNegocioAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setEloPolo(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaEloPolo) {

		if (pesquisaEloPolo != null && !pesquisaEloPolo.isEmpty()) {
			Collection<Localidade> colecaoEloPolo = new ArrayList();
			Iterator eloPoloIterator = pesquisaEloPolo.iterator();
			while (eloPoloIterator.hasNext()) {
				Localidade eloPoloAuxiliar = new Localidade();
				Object[] eloPolo = (Object[]) eloPoloIterator.next();
				if (eloPolo[1] != null) {
					eloPoloAuxiliar.setId((Integer) eloPolo[1]);
					eloPoloAuxiliar.setDescricao((String) eloPolo[0]);
				}
				colecaoEloPolo.add(eloPoloAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoEloPolo(colecaoEloPolo);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setSitLigacaoAgua(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaSitLigacaoAgua) {

		if (pesquisaSitLigacaoAgua != null && !pesquisaSitLigacaoAgua.isEmpty()) {
			Collection<LigacaoAguaSituacao> colecaoSitLigacaoAgua = new ArrayList();
			Iterator sitLigacaoAguaIterator = pesquisaSitLigacaoAgua.iterator();
			while (sitLigacaoAguaIterator.hasNext()) {
				LigacaoAguaSituacao ligacaoAguaSituacaoAuxiliar = new LigacaoAguaSituacao();
				Object[] sitLigacaoAgua = (Object[]) sitLigacaoAguaIterator.next();
				if (sitLigacaoAgua[1] != null) {
					ligacaoAguaSituacaoAuxiliar.setId((Integer) sitLigacaoAgua[1]);
					ligacaoAguaSituacaoAuxiliar.setDescricao((String) sitLigacaoAgua[0]);
				}
				colecaoSitLigacaoAgua.add(ligacaoAguaSituacaoAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoSitLigacaoAgua(colecaoSitLigacaoAgua);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setSitLigacaoEsgoto(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaSitLigacaoEsgoto) {

		if (pesquisaSitLigacaoEsgoto != null && !pesquisaSitLigacaoEsgoto.isEmpty()) {
			Collection<LigacaoEsgotoSituacao> colecaoSitLigacaoEsgoto = new ArrayList();
			Iterator sitLigacaoEsgotoIterator = pesquisaSitLigacaoEsgoto.iterator();
			while (sitLigacaoEsgotoIterator.hasNext()) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacaoAuxiliar = new LigacaoEsgotoSituacao();
				Object[] sitLigacaoEsgoto = (Object[]) sitLigacaoEsgotoIterator.next();
				if (sitLigacaoEsgoto[1] != null) {
					ligacaoEsgotoSituacaoAuxiliar.setId((Integer) sitLigacaoEsgoto[1]);
					ligacaoEsgotoSituacaoAuxiliar.setDescricao((String) sitLigacaoEsgoto[0]);
				}
				colecaoSitLigacaoEsgoto.add(ligacaoEsgotoSituacaoAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoSitLigacaoEsgoto(colecaoSitLigacaoEsgoto);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setSubcategoria(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaSubcategoria) {
		if (pesquisaSubcategoria != null && !pesquisaSubcategoria.isEmpty()) {
			Collection<Subcategoria> colecaoSubcategoria = new ArrayList();
			Iterator subcategoriaIterator = pesquisaSubcategoria.iterator();
			while (subcategoriaIterator.hasNext()) {
				Subcategoria subcategoriaAuxiliar = new Subcategoria();
				Object[] subcategoria = (Object[]) subcategoriaIterator.next();
				if (subcategoria[1] != null) {
					subcategoriaAuxiliar.setId((Integer) subcategoria[1]);
					subcategoriaAuxiliar.setDescricao((String) subcategoria[0]);
				}
				colecaoSubcategoria.add(subcategoriaAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoSubcategoria(colecaoSubcategoria);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setPerfilImovel(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaPerfilImovel) {

		if (pesquisaPerfilImovel != null && !pesquisaPerfilImovel.isEmpty()) {
			Collection<ImovelPerfil> colecaoPerfilImovel = new ArrayList();
			Iterator perfilImovelIterator = pesquisaPerfilImovel.iterator();
			while (perfilImovelIterator.hasNext()) {
				ImovelPerfil imovelPerfilAuxiliar = new ImovelPerfil();
				Object[] imovelPerfil = (Object[]) perfilImovelIterator.next();
				if (imovelPerfil[1] != null) {
					imovelPerfilAuxiliar.setId((Integer) imovelPerfil[1]);
					imovelPerfilAuxiliar.setDescricao((String) imovelPerfil[0]);
				}
				colecaoPerfilImovel.add(imovelPerfilAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoPerfilImovel(colecaoPerfilImovel);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private ParametrosComandoNegativacaoHelper setTipoCliente(
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Collection pesquisaTipoCliente) {

		if (pesquisaTipoCliente != null && !pesquisaTipoCliente.isEmpty()) {

			Collection<ClienteTipo> colecaoTipoCliente = new ArrayList();
			Iterator tipoClienteIterator = pesquisaTipoCliente.iterator();
			while (tipoClienteIterator.hasNext()) {
				ClienteTipo clienteTipoAuxiliar = new ClienteTipo();
				Object[] clienteTipo = (Object[]) tipoClienteIterator.next();
				if (clienteTipo[1] != null) {
					clienteTipoAuxiliar.setId((Integer) clienteTipo[1]);
					clienteTipoAuxiliar.setDescricao((String) clienteTipo[0]);
				}
				colecaoTipoCliente.add(clienteTipoAuxiliar);
			}
			parametrosComandoNegativacaoHelper.setColecaoTipoCliente(colecaoTipoCliente);
		}
		return parametrosComandoNegativacaoHelper;
	}

	private Cliente verificaCriterioNegativacaoImovel(int idImovel, int idNegativacaoCriterio)
			throws ErroRepositorioException, ControladorException {
		Cliente retorno = null;
		try {

			// [SB0010] Obter Documento da Negativacao
			List titularidadeDocumentos = this.repositorioSpcSerasa.obtemTitularidadesDocumentos(idNegativacaoCriterio);

			// [SB0011] Obter dados do cliente da Negativacao
			if (titularidadeDocumentos.size() == 1) {
				NegativacaoCriterioCpfTipo titularidade = (NegativacaoCriterioCpfTipo) Util
						.retonarObjetoDeColecao(titularidadeDocumentos);
				Short idClienteRelacaoTipo = 0;

				if (titularidade.equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)) {
					idClienteRelacaoTipo = ClienteRelacaoTipo.USUARIO;
				} else if (titularidade.equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)) {
					idClienteRelacaoTipo = ClienteRelacaoTipo.PROPRIETARIO;
				} else if (titularidade.equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)) {
					idClienteRelacaoTipo = ClienteRelacaoTipo.RESPONSAVEL;
				} else if (titularidade.equals(NegativacaoCriterioCpfTipo.RESPONSAVEL_PELO_PARCELAMENTO)) {
					idClienteRelacaoTipo = 0;
				}
				Cliente clienteUsuario = (Cliente) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa
						.obtemDadosClienteNegativacao(idImovel, idClienteRelacaoTipo));
				retorno = clienteUsuario;
			} else {
				// Collection<Cliente> clientesTitularidade = null;
				// Cliente clienteProprietario = null;
				// Cliente clienteUsu = null;
				// para cada imovel da lista de imoveis
				Iterator itTitularidadeDocumentos = titularidadeDocumentos.iterator();
				while (itTitularidadeDocumentos.hasNext()) {
					NegativacaoCriterioCpfTipo titularidades = (NegativacaoCriterioCpfTipo) itTitularidadeDocumentos
							.next();

					if (titularidades.getNumeroOrdemSelecao() != 0) {
						/*
						 * Cliente clienteUsuario = (Cliente) Util
						 * .retonarObjetoDeColecao(this.repositorioSpcSerasa
						 * .obtemDadosClienteNegativacao(idImovel,
						 * titularidades.
						 * getNegativacaoCriterio().getClienteRelacaoTipo
						 * ().getId())); if( (clienteUsuario.getCpf().equals("")
						 * || clienteUsuario.getCpf() == "" ||
						 * clienteUsuario.getCpf()==null)||
						 * (clienteUsuario.getCnpj().equals("") ||
						 * clienteUsuario.getCnpj() == "" ||
						 * clienteUsuario.getCnpj() == null)) { continue; }else{
						 * retorno = clienteUsuario; break; } }else{ Cliente
						 * clienteUsuario = (Cliente) Util
						 * .retonarObjetoDeColecao(this.repositorioSpcSerasa
						 * .obtemDadosClienteNegativacao(idImovel,
						 * titularidades.
						 * getNegativacaoCriterio().getClienteRelacaoTipo
						 * ().getId()));
						 * if(titularidades.getNegativacaoCriterio(
						 * ).getClienteRelacaoTipo().getId() == 1){
						 * clienteProprietario = clienteUsuario; }else
						 * if(titularidades
						 * .getNegativacaoCriterio().getClienteRelacaoTipo
						 * ().getId() == 2){ clienteUsu = clienteUsuario; }
						 * if(clienteProprietario != null && clienteUsu !=
						 * null){
						 * if(clienteProprietario.getCpf().equals(clienteUsu
						 * .getCpf())){ retorno = clienteProprietario; } }
						 */
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	public void geraNegativacaoImovel(Collection imoveisNegCliente, NegativacaoCriterio negCriterio,
			int idUsuarioResponsavel, int quantidadeRegistros, int primeiraVez,
			NegativacaoMovimentoHelper negativacaoMovimentoHelper, Short tipoComando) throws ControladorException,
			ErroRepositorioException {
		Imovel imovelNegativado = null;

		Iterator itimoveisNegCliente = imoveisNegCliente.iterator();
		while (itimoveisNegCliente.hasNext()) {
			imovelNegativado = (Imovel) itimoveisNegCliente.next();

			// [SB0005] 1.
			Integer ocorrenciaImovel = this.repositorioSpcSerasa
					.verificaExistenciaNegativacao(imovelNegativado.getId());
			if (ocorrenciaImovel >= 1 && ocorrenciaImovel != null) {
				// Passa para pr�ximo imovel.
				continue;
				// Caso Contrario [SB0005] 2.
			} else {

				// [SB0006] 1. chama o [SB0010] - Obter documento da
				// Negativacao.
				Cliente clienteDocumentoNegativacao = this.verificaCriterioNegativacaoImovel(imovelNegativado.getId(),
						negCriterio.getId());

				// [SB0006] 2. Verifica CPF e CNPJ
				if ((clienteDocumentoNegativacao != null)) {
					if ((clienteDocumentoNegativacao.getCpf().trim().equals(""))
							&& (clienteDocumentoNegativacao.getCnpj().trim().equals(""))) {
						continue;
					}
				} else {
					continue;
				}

				// [SB0006] 3.
				if (negCriterio.getIndicadorNegativacaoImovelParalisacao() == 2
						&& imovelNegativado.getCobrancaSituacaoTipo() != null) {
					continue;
				}
				// [SB0006] 4.
				Integer ocorrenciaCobrancaImovel = (Integer) this.repositorioSpcSerasa
						.verificaExistenciaImovelCobrancaSituacao(imovelNegativado.getId());
				if (ocorrenciaCobrancaImovel == null) {
					ocorrenciaCobrancaImovel = 0;
				}
				if (negCriterio.getIndicadorNegativacaoImovelParalisacao() == 2 && ocorrenciaCobrancaImovel > 0) {
					continue;
				}
				// [SB0006] 5.
				Integer ocorrenciaSubCatImovelCriterio = this.repositorioSpcSerasa
						.verificaSubCategoriaImovelNegativacaoCriterio(imovelNegativado.getId(), negCriterio.getId());
				if (ocorrenciaSubCatImovelCriterio == null) {
					ocorrenciaSubCatImovelCriterio = 0;
				}
				if (ocorrenciaSubCatImovelCriterio == 0) {
					continue;
				}
				// [SB0006] 6.
				Integer ocorrenciaPerfilImovelCriterio = this.repositorioSpcSerasa
						.verificaPerfilImovelNegativacaoCriterio(negCriterio.getId(), imovelNegativado
								.getImovelPerfil().getId());
				if (ocorrenciaPerfilImovelCriterio == null) {
					ocorrenciaPerfilImovelCriterio = 0;
				}
				if (ocorrenciaPerfilImovelCriterio == 0) {
					continue;
				}
				// [SB0006] 7.
				Integer ocorrenciaClienteUsuarioNegativacaoCriterio = this.repositorioSpcSerasa
						.verificaTipoClienteNegativacaoCriterio(imovelNegativado.getId(), negCriterio.getId());
				if (ocorrenciaClienteUsuarioNegativacaoCriterio == null) {
					ocorrenciaClienteUsuarioNegativacaoCriterio = 0;
				}
				if (ocorrenciaClienteUsuarioNegativacaoCriterio == 0) {
					continue;
				}

				// [SB0006] 8.
				// seta valores constantes para chamar o metodo que consulta
				// debitos do imovel
				Integer tipoImovel = new Integer(1);
				Integer indicadorPagamento = new Integer(1);
				Integer indicadorDebito = new Integer(2);
				Integer indicadorCredito = new Integer(2);
				Integer indicadorNotas = new Integer(2);
				Integer indicadorAtualizar = new Integer(1);

				// Obtendo os d�bitos do imovel
				ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = getControladorCobranca()
						.obterDebitoImovelOuCliente(tipoImovel.intValue(), imovelNegativado.getId().toString(), null,
								null, negCriterio.getAnoMesReferenciaContaInicial().toString(),
								negCriterio.getAnoMesReferenciaContaFinal().toString(),
								negCriterio.getDataVencimentoDebitoInicial(),
								negCriterio.getDataVencimentoDebitoFinal(), indicadorPagamento.intValue(),
								(int) negCriterio.getIndicadorNegativacaoContaRevisao(), indicadorDebito.intValue(),
								indicadorCredito.intValue(), indicadorNotas.intValue(),
								(int) negCriterio.getIndicadorNegativacaoGuiaPagamento(),
								indicadorAtualizar.intValue(), null);
				// [SB0006] 9.
				// Cole��o de Contas
				Collection<ContaValoresHelper> colecaoContasValores = colecaoDebitoImovel.getColecaoContasValores();
				Iterator itColecaoContasValores = null;
				if (colecaoContasValores != null) {
					itColecaoContasValores = colecaoContasValores.iterator();
					while (itColecaoContasValores.hasNext()) {
						ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();
						if (contaValores.getValorPago() != null) {
							itColecaoContasValores.remove();
						}
					}
				}

				// [SB0006] 9.
				// Cole��o de Guias de Pagamento
				Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores = colecaoDebitoImovel
						.getColecaoGuiasPagamentoValores();
				Iterator itColecaoGuiasPagamentoValores = null;
				if (colecaoGuiasPagamentoValores != null) {
					itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
					while (itColecaoGuiasPagamentoValores.hasNext()) {
						GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores
								.next();
						if (guiaPagamentoValores.getValorPago() != null) {
							itColecaoGuiasPagamentoValores.remove();
						}

					}
				}

				// [SB0006] 10.
				if (colecaoContasValores.isEmpty() && colecaoGuiasPagamentoValores.isEmpty()) {
					continue;
				}

				// [SB0006] 11.
				BigDecimal valorTotalConta = new BigDecimal(0);
				BigDecimal valorTotalGuiaPagamento = new BigDecimal(0);
				BigDecimal valorTotal = new BigDecimal(0);
				Integer quantidadeTotalItensDebito = 0;
				// Varre lista de contas para totalizar
				if (colecaoContasValores != null) {
					itColecaoContasValores = colecaoContasValores.iterator();
					while (itColecaoContasValores.hasNext()) {
						ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();
						// [SB0006] 11.1 Acumula valores total da conta.
						valorTotalConta = valorTotalConta.add(contaValores.getValorTotalConta());
						quantidadeTotalItensDebito += 1;
					}
				}

				// varre lista de guias de pagamento para totalizar
				if (colecaoGuiasPagamentoValores != null) {
					itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
					while (itColecaoGuiasPagamentoValores.hasNext()) {
						GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores
								.next();
						// [SB0006] 11.2 Acumula valor debito da guia de
						// paramento
						valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValores.getGuiaPagamento()
								.getValorDebito());
						quantidadeTotalItensDebito += 1;
					}
				}
				// Valor total de debitos do imovel.
				valorTotal = valorTotal.add(valorTotalConta.add(valorTotalGuiaPagamento));

				// [SB0006] 12.
				if (valorTotal.floatValue() < negCriterio.getValorMinimoDebito().floatValue()
						|| valorTotal.floatValue() > negCriterio.getValorMaximoDebito().floatValue()) {
					continue;
				}
				// [SB0006] 14.
				if (quantidadeTotalItensDebito < negCriterio.getQuantidadeMinimaContas()
						|| quantidadeTotalItensDebito > negCriterio.getQuantidadeMaximaContas()) {
					continue;
				}
				Parcelamento clienteParcelamento = new Parcelamento();
				Integer indicadorImovelParcelamento = 0;
				Integer indicadorRecebimentoCarta = 0;
				Integer numeroDiasAtaso = 0;
				itColecaoContasValores = colecaoContasValores.iterator();
				while (itColecaoContasValores.hasNext()) {
					ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();

					// obtem imovel para pesquisar se o mesmo tem parcelamento.
					Integer imovelParcelamento = this.repositorioSpcSerasa.verificaDebitoCobradoConta(contaValores
							.getConta().getId());
					if (imovelParcelamento == null) {
						imovelParcelamento = 0;
					}
					if (imovelParcelamento > 0) {
						clienteParcelamento = (Parcelamento) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa
								.verificaImovelParcelamento(imovelNegativado.getId()));
						if (clienteParcelamento != null) {
							indicadorImovelParcelamento = 1;
						}
					}
				}
				// [SB0006] 16.
				if (negCriterio.getIndicadorParcelamentoAtraso() == 1) {
					// [SB0006] 16.1
					if (indicadorImovelParcelamento == 0) {
						continue;
					}
					if (negCriterio.getNumeroDiasParcelamentoAtraso() != 0
							&& negCriterio.getNumeroDiasParcelamentoAtraso() != null) {
						if (clienteParcelamento != null) {
							numeroDiasAtaso = Util.obterQuantidadeDiasEntreDuasDatas(new Date(),
									clienteParcelamento.getParcelamento());
						}
					}
					// [SB0006] 16.2.1
					if (numeroDiasAtaso < negCriterio.getNumeroDiasParcelamentoAtraso()) {
						continue;
					}
				}
				// [SB0006] 17.
				if (negCriterio.getIndicadorNegativacaoRecebimentoCartaParcelamento() == 1) {
					// [SB0006] 17.1.
					indicadorRecebimentoCarta = this.repositorioSpcSerasa.verificaCartaAvisoParcelamento(
							imovelNegativado.getId(), negCriterio.getNumeroDiasAtrasoRecebimentoCartaParcelamento());
					if (indicadorRecebimentoCarta == null) {
						indicadorRecebimentoCarta = 0;
					}
					if (indicadorRecebimentoCarta == 0) {
						continue;
					}
				}
				// [SB0007] - Gerar Registro da Negativa��o
				this.geraRegistroNegativacao(imovelNegativado, negCriterio.getNegativacaoComando().getNegativador()
						.getId(), idUsuarioResponsavel, negCriterio.getNegativacaoComando().getId(),
						quantidadeRegistros, primeiraVez, negCriterio, valorTotal, negativacaoMovimentoHelper,
						colecaoDebitoImovel, tipoComando, null);
				primeiraVez = 1;
			}
		}
	}

	/**
	 * M�todo que inicia o caso de uso de Gerar Movimento de Inclusao de
	 * Negativacao [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [Fluxo
	 * Principal]
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param idComandoNegativacao
	 * @param tipoComando
	 * @param comunicacaoInterna
	 * @param idNegativador
	 * @param idUsuarioResponsaval
	 * @param ObjectImovel
	 *            - Collecao de [0] Integer - Matricula do Imovel [1] Integer -
	 *            id do cliente da negativacao [2] String - cpf do cliente da
	 *            negativacao [3] String - cnpj do cliente da negativaca [4]
	 *            Collection - lista da contas e guias de pagamento do imovel
	 *            [5] Intetger - quantidade de itens em d�bito do imovel [6]
	 *            BigDecimal - valor total dos d�bitos do imovel
	 * @param dataPrevista
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public Integer gerarMovimentoInclusaoNegativacao(Integer idComandoNegativacao, Short tipoComando,
			String comunicacaoInterna, Integer idNegativador, int idUsuarioResponsaval, Collection ObjectImovel,
			Date dataPrevista, String indicadorBaixaRenda, String indicadorContaNomeCliente,
			String indicadorImovelCategoriaPublico) throws ControladorException {

		// 2.0
		Integer quantidadeInclusao = 0;
		Integer quantidadeItensIncluidos = 0;
		BigDecimal valorTotal = new BigDecimal(0);
		Integer idNegativacaoMovimento = 0;
		Integer quantidadeRegistro = 0;

		Object[] objQuantidades = new Object[5];
		objQuantidades[0] = quantidadeInclusao;
		objQuantidades[1] = quantidadeItensIncluidos;
		objQuantidades[2] = valorTotal;
		objQuantidades[3] = idNegativacaoMovimento;
		objQuantidades[4] = quantidadeRegistro;

		try {

			NegativacaoComando nComando = null;

			// 3.0

			// [SB0001]- Gerar Moviemnto de Inclus�o de Negativa��o por
			// Matr�cular de Im�veis
			if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {

				// 1
				Negativador n = new Negativador();
				n.setId(idNegativador);

				// 2
				NegativadorContrato nContrato = repositorioSpcSerasa.consultarNegativadorContratoVigente(n.getId());

				// 3
				Usuario usuario = new Usuario();
				usuario.setId(idUsuarioResponsaval);

				nComando = new NegativacaoComando();
				nComando.setIndicadorSimulacao((short) 2);
				nComando.setIndicadorComandoCriterio((short) 2);
				nComando.setDataPrevista(dataPrevista);
				nComando.setDataHoraComando(new Date());
				nComando.setDataHoraRealizacao(new Date());
				nComando.setUltimaAlteracao(new Date());
				nComando.setUsuario(usuario);
				nComando.setDescricaoComunicacaoInterna(comunicacaoInterna);
				nComando.setNegativador(n);
				// CRC4496 - adicionado por Vivianne Sousa - 29/06/2010 -
				// Adriana Ribeiro
				nComando.setIndicadorBaixaRenda(new Short(indicadorBaixaRenda));
				// RM4097 - adicionado por Vivianne Sousa - 29/12/2010 - Ana
				// Cristina
				nComando.setIndicadorContaNomeCliente(new Short(indicadorContaNomeCliente));
				// RM3388 - adicionado por Ivan Sergio - 26/01/2011 - Analista
				// Adriana
				nComando.setIndicadorOrgaoPublico(new Short(indicadorImovelCategoriaPublico));

				RepositorioUtilHBM.getInstancia().inserir(nComando);
				nComando.getId();

				// 4
				objQuantidades = gerarMovimentodeInclusaoNegativacaoPorMatriculadeImovel(objQuantidades, nContrato, n,
						tipoComando, nComando, ObjectImovel);
			}

			// 4.0
			if ((Integer) objQuantidades[0] > 0) {

				// 4.1
				nComando.setDataHoraRealizacao(new Date());
				nComando.setQuantidadeInclusoes((Integer) objQuantidades[0]);
				nComando.setValorDebito((BigDecimal) objQuantidades[2]);
				nComando.setQuantidadeItensIncluidos((Integer) objQuantidades[1]);
				nComando.setUltimaAlteracao(new Date());

				RepositorioUtilHBM.getInstancia().atualizar(nComando);

				// 4.2 Caso o comando n�o seja uma simula��o
				if (nComando.getIndicadorSimulacao() == 2) {
					// 4.2.1 [SB0012] - Gerar Arquivo TXT para envio ao
					// negativador

					FiltroNegativadorMovimento fnm = new FiltroNegativadorMovimento();
					fnm.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID,
							(Integer) objQuantidades[3]));
					Collection colecaoNegativadorMovimento = RepositorioUtilHBM.getInstancia().pesquisar(fnm,
							NegativadorMovimento.class.getName());
					NegativadorMovimento nm = null;
					if (colecaoNegativadorMovimento != null && !colecaoNegativadorMovimento.isEmpty()) {
						nm = (NegativadorMovimento) colecaoNegativadorMovimento.iterator().next();
					}

					// Soma o registro do trailler
					objQuantidades[4] = (Integer) objQuantidades[4] + 1;
					this.gerarArquivo(nm.getId(), true, idNegativador);

					Negativador n = nComando.getNegativador();

					// 4.2.2
					NegativadorContrato nContrato = repositorioSpcSerasa.consultarNegativadorContratoVigente(n.getId());
					nContrato.setNumeroSequencialEnvio(nm.getNumeroSequencialEnvio());
					if (nContrato.getNumeroInclusoesEnviadas() != null) {
						nContrato.setNumeroInclusoesEnviadas(nContrato.getNumeroInclusoesEnviadas()
								+ (Integer) objQuantidades[0]);
					} else {
						nContrato.setNumeroInclusoesEnviadas((Integer) objQuantidades[0]);
					}
					RepositorioUtilHBM.getInstancia().atualizar(nContrato);

					// 4.2.3
					nm.setNumeroRegistrosEnvio((Integer) objQuantidades[4]);
					nm.setValorTotalEnvio((BigDecimal) objQuantidades[2]);

					RepositorioUtilHBM.getInstancia().atualizar(nm);

				}
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return (Integer) objQuantidades[0];
	}

	/**
	 * M�todo que gera os movimento de inclusao de negativacao por Matricula de
	 * Imovel [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0001]
	 * Gerar Movimento de Inclusao de Negativacao por Matricula de Imovel
	 * 
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @return objQuantidades
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private Object[] gerarMovimentodeInclusaoNegativacaoPorMatriculadeImovel(Object[] objQuantidades,
			NegativadorContrato nContrato, Negativador n, Short tipoComando, NegativacaoComando nComando,
			Collection objectImovel) throws ControladorException, ErroRepositorioException {

		Integer quantidadeRegistro = 0;

		NegativadorMovimentoReg.resetNumeroProximoRegistro();

		if (objectImovel != null) {
			Iterator it = objectImovel.iterator();
			while (it.hasNext()) {
				DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper = (DadosNegativacaoPorImovelHelper) it
						.next();

				objQuantidades[0] = (Integer) objQuantidades[0] + 1;
				objQuantidades[1] = (Integer) objQuantidades[1]
						+ dadosNegativacaoPorImovelHelper.getQtdItensDebitoImovel();
				objQuantidades[2] = ((BigDecimal) objQuantidades[2]).add(dadosNegativacaoPorImovelHelper
						.getTotalDebitosImovel());

				// 4.2
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa
						.getDadosCliente(dadosNegativacaoPorImovelHelper.getIdCliente()));

				// 4.3
				quantidadeRegistro = gerarRegistroDeInclusaoNegativacao(tipoComando, n, nComando, null,
						quantidadeRegistro, dadosNegativacaoPorImovelHelper.getIdImovel(),
						dadosNegativacaoPorImovelHelper.getTotalDebitosImovel(), null, dadosNegativacaoPorImovelHelper,
						cliente, nContrato, objQuantidades, null);

			}

		}

		objQuantidades[4] = quantidadeRegistro;

		return objQuantidades;
	}

	/**
	 * M�todo que gera os movimento de inclusao de negativacao por criterio
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0002] Gerar
	 * Movimento de Inclusao de Negativacao por Criterio
	 * 
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param nc
	 * @param Object
	 *            [] obj obj[0] Integer - quantidadeInclusao obj[1] Integer -
	 *            quantidadeItens obj[2] BigDecimal - valorTotalDebito
	 * 
	 * @return
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	/*
	 * private Object[] gerarMovimentodeInclusaoNegativacaoPorCriterio(String
	 * tipoComando,Object[] objQuantidades,NegativacaoComando nComando) throws
	 * ControladorException, ErroRepositorioException {
	 * 
	 * //1. NegativacaoCriterio nCriterio = null; if
	 * (nComando.getNegativacaoCriterios() != null &&
	 * !nComando.getNegativacaoCriterios().isEmpty()) { nCriterio =
	 * (NegativacaoCriterio)
	 * nComando.getNegativacaoCriterios().iterator().next(); }
	 * 
	 * //2.0 Negativador n = nComando.getNegativador();
	 * 
	 * //3.0 NegativadorContrato nContrato =
	 * repositorioSpcSerasa.consultarNegativadorContratoVigente(n.getId());
	 * 
	 * //4.0 if (nCriterio != null ) { if (nCriterio.getCliente() != null) {
	 * 
	 * // chamando o subfluxo 3
	 * this.gerarMovimentoDeInclusaoDeNegativacaoParaImoveisCliente
	 * (tipoComando,objQuantidades,nCriterio,n,nComando,nContrato); } else { //
	 * camando o subfluxo 4 objQuantidades =
	 * this.gerarMovimentoDeInclusaoDeNegativacaoParaImoveis
	 * (tipoComando,objQuantidades,nCriterio,n,nComando,nContrato ); } }
	 * 
	 * return objQuantidades; }
	 */

	/**
	 * M�todo que gera os movimento de inclusao de negativacao por Matricula de
	 * Imovel [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0003]
	 * Gerar Movimento de Inclusao de Negativacao Para os Imoveis do Cliente
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param nc
	 * @param Object
	 *            [] obj obj[0] Integer - quantidadeInclusao obj[1] Integer -
	 *            quantidadeItens obj[2] BigDecimal - valorTotalDebito
	 * 
	 * @return
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */

	/*
	 * private void
	 * gerarMovimentoDeInclusaoDeNegativacaoParaImoveisCliente(String
	 * tipoComando,Object[] objQuantidades,NegativacaoCriterio nCriterio,
	 * Negativador n,NegativacaoComando nComando, NegativadorContrato nContrato)
	 * throws ControladorException, ErroRepositorioException { if
	 * (nCriterio.getCliente() != null) { //1.0 List clienteimoveis = null;
	 * if(nCriterio.getClienteRelacaoTipo() == null) { clienteimoveis =
	 * this.repositorioSpcSerasa
	 * .consultarImoveisCliente(nCriterio.getCliente().getId(), null); } else {
	 * clienteimoveis =
	 * this.repositorioSpcSerasa.consultarImoveisCliente(nCriterio
	 * .getCliente().getId(), nCriterio.getClienteRelacaoTipo().getId()); } if
	 * (clienteimoveis != null && !clienteimoveis.isEmpty()) { Iterator it =
	 * clienteimoveis.iterator(); //2.0 Integer idNegativacaoMovimento = 0;
	 * while(it.hasNext()) { Imovel imovel = (Imovel)it.next();
	 * idNegativacaoMovimento =
	 * gerarMovimentoDeInclusaoDeNegativacaoParaImovel(tipoComando
	 * ,objQuantidades
	 * ,imovel.getId(),n,nComando,nCriterio,nContrato,idNegativacaoMovimento); }
	 * } } }
	 */

	/*
	 * M�todo que gera os movimento de inclusao de negativacao por Matricula de Imovel
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0002]
	 * Gerar Movimento de Inclusao de Negativacao Por Crit�rio
	 */
	@SuppressWarnings("unchecked")
	public void gerarMovimentodeInclusaoNegativacaoPorCriterio(Integer idRota, NegativacaoCriterio criterio,
			Negativador negativador, NegativacaoComando comando, NegativadorContrato contrato,
			NegativadorMovimento movimento, Integer idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.ROTA, idRota);

		try {
			Collection<Integer> colecaoIdsImoveis = this.pesquisarImoveisParaNegativacao(idRota, criterio, comando);

			if (colecaoIdsImoveis != null && !colecaoIdsImoveis.isEmpty()) {
				
				for (Integer idImovel : colecaoIdsImoveis) {
					
					if (criterio.getQuantidadeMaximaInclusoes() != null && !criterio.getQuantidadeMaximaInclusoes().equals("")) {
						if (!NegativacaoComando.continuarInclusaoImoveis(criterio.getQuantidadeMaximaInclusoes())) {
							break;
						}
					}

					boolean ocorrenciaImovel = this.repositorioSpcSerasa.verificarExistenciaNegativacaoImovel(idImovel);
					boolean isNegativacaoPorPeriodo = this.negativacaoPorPeriodo(idImovel);

					if (!ocorrenciaImovel || (ocorrenciaImovel && isNegativacaoPorPeriodo)) {
						
						Collection<Object[]> colecaoDadosClienteEDebito = this.verificarCriteriodeNegativacaoParaImovel(
								idImovel, criterio, comando, isNegativacaoPorPeriodo);

						if (colecaoDadosClienteEDebito != null && !colecaoDadosClienteEDebito.isEmpty()) {
							for (Object[] objeto : colecaoDadosClienteEDebito) {
								// [0] Integer - Qtd de Itens em D�bito
								// [1] BigDecimal - Valor Total dos D�bitos do Im�vel
								// [2] Cliente - Cliente do D�bito
								// [3] ObterDebitoImovelOuClienteHelper colecaoDebitoImovel

								// Verificar se o im�vel satifaz o crit�rio de negativa��o
								if (objeto != null) {
									NegativacaoComando.incrementarQuantidadeImoveisJaIncluidos();

									if (comando.getIndicadorSimulacao() == 2) {
										this.gerarRegistroDeInclusaoNegativacao(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO,
												negativador, comando, criterio, new Integer(0), idImovel, (BigDecimal) objeto[1],
												(ObterDebitoImovelOuClienteHelper) objeto[3], null, (Cliente) objeto[2], contrato, null,
												movimento);
									} else {
										NegativadorResultadoSimulacao negativacaoSimulacao = new NegativadorResultadoSimulacao();
										negativacaoSimulacao.setNegativacaoComando(comando);

										Imovel imovel = new Imovel();
										imovel.setId(idImovel);

										negativacaoSimulacao.setImovel(imovel);
										negativacaoSimulacao.setValorDebito((BigDecimal) objeto[1]);

										Cliente cliente = (Cliente) objeto[2];
										if (cliente.getCnpj() != null && cliente.getCnpj().length() > 0) {
											negativacaoSimulacao.setNumeroCnpj(cliente.getCnpj());
										} else {
											negativacaoSimulacao.setNumeroCpf(cliente.getCpf());
										}
										negativacaoSimulacao.setQuantidadeItensIncluidos((Integer) objeto[0]);
										negativacaoSimulacao.setUltimaAlteracao(new Date());

										RepositorioUtilHBM.getInstancia().inserir(negativacaoSimulacao);
									}
								}
							}
						}
					}
				}
			}

			logger.info("***** Encerrando unidade (ROTA) = " + idRota + " *****");
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new ControladorException("erro.sistema", e);
		}
	}

	private Collection<Integer> pesquisarImoveisParaNegativacao(Integer idRota, NegativacaoCriterio criterio,
			NegativacaoComando comando) throws ErroRepositorioException {
		
		Collection<Integer> colecaoIdsImoveis = null;

		if (comando.getComandoSimulacao() != null && !comando.getComandoSimulacao().equals("")) {
			colecaoIdsImoveis = this.repositorioSpcSerasa.consultarImoveisNegativacaoSimulada(comando, idRota);
		} else if (criterio.getCliente() != null) {
			// [SB0003] Gera movimento de inclus�o de negativa�ao para Im�veis do Cliente
			colecaoIdsImoveis = this.repositorioSpcSerasa.consultarImoveisCliente(criterio, idRota);
		} else {
			// [SB0004] Gera movimento de inclus�o de negativa��o para os Im�veis
			colecaoIdsImoveis = this.repositorioSpcSerasa.pesquisarImoveisParaNegativacao(idRota, comando.getId());
		}
		
		return colecaoIdsImoveis;
	}

	/**
	 * Verificar criterio de negativacao para o imovel
	 * 
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0006] Verificar
	 * criterio de negativacao para o imovel
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param nc
	 * @return Object[] [0] = Integer - quantidade de itens em debito [1] =
	 *         BigDecimal - Valor Total dos D�bitos do imovel [2] = Cliente -
	 *         cliente do debito [3] = ObterDebitoImovelOuClienteHelper
	 *         colecaoDebitoImovel
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private Object[] verificarCriteriodeNegativacaoParaImovel(Integer idImovel, NegativacaoCriterio nCriterio,
			NegativacaoComando nComando) throws ControladorException, ErroRepositorioException {

		// RM3388 - adicionado por Ivan Sergio - 28/01/2011 - Adriana Ribeiro
		// Verificar im�vel �rg�o p�blico
		// int idCategoria = 0;
		// Categoria categoria = null;
		// categoria = this.getControladorImovel()
		// .obterPrincipalCategoriaImovel(idImovel);
		// if (categoria != null) {
		// idCategoria = categoria.getId();
		// if(idCategoria == Categoria.PUBLICO_INT){
		// return null;
		// }
		// }

		// Pesquisar dados p/ crit�rio de negativa��o
		Object[] dadosImovel = repositorioSpcSerasa.pesquisarDadosImovelParaNegativacao(idImovel);

		// [SB0006] 1.
		// [SB0006] 2.
		Integer ocorrenciaCobrancaImovel = (Integer) this.repositorioSpcSerasa
				.verificaExistenciaImovelCobrancaSituacao(idImovel);

		// CRC3323 - adicionado por Vivianne Sousa - analista:Fatima Sampaio -
		// 06/05/2010
		Integer ocorrenciaCobrancaSituacaoTipo = this.repositorioSpcSerasa
				.verificarExistenciaDeCobrancaSituacaoTipoParaImovel(idImovel);
		Collection ocorrenciaCobrancaSituacaoHistorico = this.repositorioSpcSerasa
				.verificarExistenciaDeCobrancaSituacaoHistoricoParaImovel(idImovel);

		if (nCriterio.getIndicadorNegativacaoImovelParalisacao() == 2
				&& ((ocorrenciaCobrancaSituacaoTipo != null && ocorrenciaCobrancaSituacaoTipo.compareTo(new Integer(0)) > 0) || ocorrenciaCobrancaSituacaoHistorico != null
						&& !ocorrenciaCobrancaSituacaoHistorico.isEmpty())) {
			return null;
		}

		if (nCriterio.getIndicadorNegativacaoImovelSituacaoCobranca() == 2 && ocorrenciaCobrancaImovel > 0) {
			return null;
		}

		// [SB0006] 3. Caso o indicador de baixa renda
		// (NEGATIVACAO_COMANDO.NGCM_ ICBAIXARENDA)
		// corresponda a N�O (2) indicar que o im�vel n�o satisfaz o crit�rio de
		// negativa��o
		// e retornar para o passo que chamou este subfluxo.
		// CRC4496 - adicionado por Vivianne Sousa - 29/06/2010 - Adriana
		// Ribeiro
		if (nComando.getIndicadorBaixaRenda().equals(ConstantesSistema.NAO)) {
			Integer idPerfilQuadra = getControladorImovel().obterQuadraPerfil(idImovel);
			if (idPerfilQuadra.equals(2)) {
				return null;
			}
		}

		// [SB0006] 6. Caso o indicador de �rg�o p�blico (NGCM_ICORGAOPUBLICO da
		// tabela NEGATIVACAO_COMANDO)
		// corresponda a N�O (2) e o im�vel seja �rg�o p�blico (CATG_ID com
		// valor correspondente a
		// PUBLICO - <<Inclui>> [UC0306 - Obter Principal Categoria do Im�vel]),
		// indicar que o im�vel n�o
		// satisfaz o crit�rio de negativa��o e retornar para o passo que chamou
		// este subfluxo.
		// RM3388 - adicionado por Ivan Sergio - 28/01/2011 - Adriana Ribeiro
		if (nComando.getIndicadorOrgaoPublico().equals(ConstantesSistema.NAO)) {
			int idCategoria = 0;
			Categoria categoria = null;
			categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
			if (categoria != null) {
				idCategoria = categoria.getId();
				if (idCategoria == Categoria.PUBLICO_INT) {
					return null;
				}
			}
		}

		// CRC4523 - adicionado por Vivianne Sousa - 07/07/2010
		// [SB0006] 4. Caso o usu�rio deseje excluir do comando os im�veis
		// enviados e
		// aceitos num determinado n�mero de dias em rela��o ao �ltimo envio
		if (nCriterio.getNumeroDiasRetorno() != null) {

			Date dataAtualMenosNNDiasRetorno = Util.adicionarNumeroDiasDeUmaData(new Date(), nCriterio
					.getNumeroDiasRetorno().intValue());

			Collection colecaoReg = repositorioSpcSerasa.pesquisarNegativadorMovimentoRegPorImovel(idImovel,
					dataAtualMenosNNDiasRetorno, nComando.getNegativador().getId());

			if (colecaoReg != null && !colecaoReg.isEmpty()) {
				return null;
			}
		}

		// CRC4523 - adicionado por Vivianne Sousa - 12/07/2010
		// [SB0006] 5. Caso o usu�rio deseje excluir do comando os im�veis
		// rejeitados
		// por determinado(s) motivo(s) em rela��o ao �ltimo envio
		// (existe ocorr�ncia na tabela NEGATIV_CRIT_NEG_RET_MOT para
		// NGCT_ID=NGCT_ID da tabela NEGATIVACAO_CRITERIO)
		Integer ultimoNegativadorRetornoMotivoDoReg = repositorioSpcSerasa
				.pesquisarUltimoNegativadorRetornoMotivoDoReg(idImovel, nComando.getNegativador().getId());

		if (ultimoNegativadorRetornoMotivoDoReg != null) {

			Integer idNegativCritNegRetMot = repositorioSpcSerasa.pesquisarIdNegativCritNegRetMot(
					ultimoNegativadorRetornoMotivoDoReg, nCriterio.getId());

			if (idNegativCritNegRetMot != null) {
				return null;
			}
		}

		Object[] existeCriterio = repositorioSpcSerasa.verificarExistenciaCriterio(nCriterio.getId());

		// [SB0006] 5.
		if (existeCriterio[0] != null && (Integer) existeCriterio[0] != 0) {
			Integer ocorrenciaLigacaoAguaCriterio = this.repositorioSpcSerasa
					.verificaLigacaoAguaImovelNegativacaoCriterio(nCriterio.getId(), (Integer) dadosImovel[2]);

			if (ocorrenciaLigacaoAguaCriterio == null || ocorrenciaLigacaoAguaCriterio <= 0) {
				return null;
			}
		}
		// [SB0006] 6.
		if (existeCriterio[1] != null && (Integer) existeCriterio[1] != 0) {
			Integer ocorrenciaLigacaoEsgotoCriterio = this.repositorioSpcSerasa
					.verificaLigacaoEsgotoImovelNegativacaoCriterio(nCriterio.getId(), (Integer) dadosImovel[3]);

			if (ocorrenciaLigacaoEsgotoCriterio == null || ocorrenciaLigacaoEsgotoCriterio <= 0) {
				return null;
			}
		}
		// [SB0006] 7.
		if (existeCriterio[2] != null && (Integer) existeCriterio[2] != 0) {
			Integer ocorrenciaSubCatImovelCriterio = this.repositorioSpcSerasa
					.verificaSubCategoriaImovelNegativacaoCriterio(idImovel, nCriterio.getId());

			if (ocorrenciaSubCatImovelCriterio == null || ocorrenciaSubCatImovelCriterio <= 0) {
				return null;
			}
		}
		// [SB0006] 8.
		if (existeCriterio[3] != null && (Integer) existeCriterio[3] != 0) {
			Integer ocorrenciaPerfilImovelCriterio = this.repositorioSpcSerasa.verificaPerfilImovelNegativacaoCriterio(
					nCriterio.getId(), (Integer) dadosImovel[1]);

			if (ocorrenciaPerfilImovelCriterio == null || ocorrenciaPerfilImovelCriterio <= 0) {
				return null;
			}
		}
		// [SB0006] 9.
		if (existeCriterio[4] != null && (Integer) existeCriterio[4] != 0) {
			Integer ocorrenciaClienteUsuarioNegativacaoCriterio = this.repositorioSpcSerasa
					.verificaTipoClienteNegativacaoCriterio(idImovel, nCriterio.getId());

			if (ocorrenciaClienteUsuarioNegativacaoCriterio != null && ocorrenciaClienteUsuarioNegativacaoCriterio <= 0) {
				return null;
			}
		}

		// [SB0006]
		// 2. Caso o indicador de situa��o especial de cobran�a corresponda a
		// SIM (1) identificar na tabela
		// NEGATIV_CRIT_SIT_ESP_COB as situa��es especiais de cobran�a
		// selecionadas.
		// Para os im�veis que n�o estiverem nas situa��es de cobran�a
		// selecionadas
		// (CBSP_ID da tabela IMOVEL diferente do CBSP_ID selecionado ou existir
		// ocorr�ncia na tabela
		// COBRANCA_SITUACAO_HISTORICO com IMOV_ID=IMOV_ID da tabela IMOVEL e
		// CBSH_AMCOBRANCARETIRADA com o
		// valor diferente de nulo e CBSP_ID = ao CBSP_ID da tabela
		// NEGATIV_CRIT_SIT_ESP_COB) indicar que o im�vel n�o satisfaz o
		// crit�rio de
		// negativa��o e retornar para o passo que chamou este subfluxo
		if (existeCriterio[5] != null && (Integer) existeCriterio[5] != 0) {

			Integer ocorrenciaCobrancaSituacaoEspecialNegativacaoCriterio = this.repositorioSpcSerasa
					.verificaCobrancaSituacaoEspecialNegativacaoCriterio(idImovel, nCriterio.getId());

			if (ocorrenciaCobrancaSituacaoEspecialNegativacaoCriterio != null
					&& ocorrenciaCobrancaSituacaoEspecialNegativacaoCriterio <= 0) {
				return null;
			}
		}

		// [SB0006]
		// 4. Caso o indicador de situa��o de cobran�a corresponda a SIM (1)
		// identificar na tabela NEGATIV_CRIT_SIT_COB
		// as situa��es de cobran�a selecionadas. Para Os im�veis que n�o
		// estiverem nas situa��es de cobran�a selecionadas
		// (CBST_ID da tabela IMOVEL diferente ao CBST_ID selecionado ou existe
		// ocorr�ncia na tabela COBRANCA_SITUACAO_HISTORICO com
		// IMOV_ID=IMOV_ID da tabela IMOVEL e CBSH_AMCOBRANCARETIRADA com o
		// valor diferente de nulo e CBST_ID = ao CBST_ID da
		// tabela NEGATIV_CRIT_SIT_COB) indicar que o im�vel n�o satisfaz o
		// crit�rio de negativa��o e
		// retornar para o passo que chamou este subfluxo
		if (existeCriterio[6] != null && (Integer) existeCriterio[6] != 0) {

			Integer ocorrenciaCobrancaSituacaoNegativacaoCriterio = this.repositorioSpcSerasa
					.verificaCobrancaSituacaoNegativacaoCriterio(idImovel, nCriterio.getId());

			if (ocorrenciaCobrancaSituacaoNegativacaoCriterio != null
					&& ocorrenciaCobrancaSituacaoNegativacaoCriterio <= 0) {
				return null;
			}
		}

		// 8.0
		// Obtendo os d�bitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = getControladorCobranca().obterDebitoImovelOuCliente(1,
				idImovel.toString(), null, null, nCriterio.getAnoMesReferenciaContaInicial().toString(),
				nCriterio.getAnoMesReferenciaContaFinal().toString(), nCriterio.getDataVencimentoDebitoInicial(),
				nCriterio.getDataVencimentoDebitoFinal(), 1, (int) nCriterio.getIndicadorNegativacaoContaRevisao(), 2,
				2, 2, (int) nCriterio.getIndicadorNegativacaoGuiaPagamento(), 1, null);

		// [SB0006] 10.
		// Cole��o de Contas
		Collection<ContaValoresHelper> colecaoContasValores = colecaoDebitoImovel.getColecaoContasValores();
		// adicionado por Vivianne Sousa 21/06/2010 - Fatima Sampaio
		this.retirarContaPagaOuParceladaEEntradaParcelamento(colecaoContasValores);
		Iterator itColecaoContasValores = null;
		// if(colecaoContasValores != null){
		// itColecaoContasValores = colecaoContasValores.iterator();
		// while(itColecaoContasValores.hasNext()){
		// ContaValoresHelper contaValores = (ContaValoresHelper)
		// itColecaoContasValores.next();
		// if(contaValores.getValorPago() != null){
		// itColecaoContasValores.remove();
		// }
		// }
		// }

		// [SB0006] 11.
		// Cole��o de Guias de Pagamento
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores = colecaoDebitoImovel
				.getColecaoGuiasPagamentoValores();
		// adicionado por Vivianne Sousa 21/06/2010 - Fatima Sampaio
		this.retirarGuiaPagamentoDeEntradaParcelamento(colecaoGuiasPagamentoValores);
		Iterator itColecaoGuiasPagamentoValores = null;
		// if(colecaoGuiasPagamentoValores != null){
		// itColecaoGuiasPagamentoValores =
		// colecaoGuiasPagamentoValores.iterator();
		// while(itColecaoGuiasPagamentoValores.hasNext()){
		// GuiaPagamentoValoresHelper guiaPagamentoValores =
		// (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores.next();
		// if(guiaPagamentoValores.getValorPago() != null){
		// itColecaoGuiasPagamentoValores.remove();
		// }
		//
		// }
		// }

		if (colecaoContasValores == null) {
			colecaoContasValores = new ArrayList();
		}

		if (colecaoGuiasPagamentoValores == null) {
			colecaoGuiasPagamentoValores = new ArrayList();
		}

		// [SB0006] 12.
		if ((colecaoContasValores.isEmpty() && colecaoGuiasPagamentoValores.isEmpty())) {
			return null;
		}

		// [SB0006] 13.
		BigDecimal valorTotalConta = new BigDecimal(0);
		BigDecimal valorTotalGuiaPagamento = new BigDecimal(0);
		BigDecimal valorTotal = new BigDecimal(0);
		Integer quantidadeTotalItensDebito = 0;
		Collection colecaoContasIds = null;
		// Varre lista de contas para totalizar
		if (colecaoContasValores != null) {
			colecaoContasIds = new ArrayList();
			itColecaoContasValores = colecaoContasValores.iterator();
			while (itColecaoContasValores.hasNext()) {
				ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();
				// [SB0006] 11.1 Acumula valores total da conta.
				valorTotalConta = valorTotalConta.add(contaValores.getValorTotalConta());
				colecaoContasIds.add(contaValores.getConta().getId());
				// 13
				quantidadeTotalItensDebito += 1;
			}
		}

		// varre lista de guias de pagamento para totalizar
		if (colecaoGuiasPagamentoValores != null) {
			itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
			while (itColecaoGuiasPagamentoValores.hasNext()) {
				GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores
						.next();
				// [SB0006] 11.2 Acumula valor debito da guia de paramento
				valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValores.getGuiaPagamento()
						.getValorDebito());

				// 13
				quantidadeTotalItensDebito += 1;
			}
		}
		// Valor total de debitos do imovel.
		valorTotal = valorTotal.add(valorTotalConta.add(valorTotalGuiaPagamento));

		// [SB0006] 14.
		if (valorTotal.floatValue() < nCriterio.getValorMinimoDebito().floatValue()
				|| valorTotal.floatValue() > nCriterio.getValorMaximoDebito().floatValue()) {
			return null;
		}
		// [SB0006] 16.
		if (quantidadeTotalItensDebito < nCriterio.getQuantidadeMinimaContas()
				|| quantidadeTotalItensDebito > nCriterio.getQuantidadeMaximaContas()) {
			return null;
		}

		Parcelamento parcelamento = null;
		Integer indicadorImovelParcelamento = 0;
		Integer indicadorRecebimentoCarta = 0;
		Integer numeroDiasAtaso = 0;
		boolean contaParcelada = false;
		itColecaoContasValores = colecaoContasValores.iterator();
		while (itColecaoContasValores.hasNext()) {
			ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();

			// obtem imovel para pesquisar se o mesmo tem parcelamento.
			Integer imovelParcelamento = this.repositorioSpcSerasa.verificaDebitoCobradoConta(contaValores.getConta()
					.getId());

			if (imovelParcelamento != null && imovelParcelamento > 0) {
				contaParcelada = true;
				break;
			}
		}
		// [SB0006] 16.
		if (nCriterio.getIndicadorParcelamentoAtraso() == 1) {
			if (contaParcelada) {
				parcelamento = (Parcelamento) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa
						.verificaImovelParcelamento(idImovel));
				if (parcelamento != null) {
					indicadorImovelParcelamento = 1;
				}
			}
			// [SB0006] 16.1
			if (indicadorImovelParcelamento == 0) {
				return null;
			}
			// 16.2
			if (nCriterio.getNumeroDiasParcelamentoAtraso() != null) {
				if (parcelamento != null) {
					numeroDiasAtaso = Util
							.obterQuantidadeDiasEntreDuasDatas(parcelamento.getParcelamento(), new Date());
				}
			}
			// [SB0006] 16.2.1
			if (numeroDiasAtaso < nCriterio.getNumeroDiasParcelamentoAtraso()) {
				return null;
			}
		}

		// [SB0006] 17.
		if (nCriterio.getIndicadorNegativacaoRecebimentoCartaParcelamento() == 1) {
			// [SB0006] 17.1.
			indicadorRecebimentoCarta = this.repositorioSpcSerasa.verificaCartaAvisoParcelamento(idImovel,
					nCriterio.getNumeroDiasAtrasoRecebimentoCartaParcelamento());
			if (indicadorRecebimentoCarta == null) {
				indicadorRecebimentoCarta = 0;
			}
			if (indicadorRecebimentoCarta == 0) {
				return null;
			}
		}

		// [SB0006] 1. chama o [SB0010] - Obter documento da Negativacao.
		Cliente clienteDocumentoNegativacao = null;
		// this.verificaCriterioNegativacaoImovel(idImovel,nCriterio.getId());
		if (parcelamento != null) {
			clienteDocumentoNegativacao = this.obterDocumentoNegativacao(idImovel, nCriterio.getId(),
					parcelamento.getCliente());
		} else {
			clienteDocumentoNegativacao = this.obterDocumentoNegativacao(idImovel, nCriterio.getId(), null);
		}

		// [SB0006] 2. Verifica CPF e CNPJ
		if (clienteDocumentoNegativacao == null) {
			return null;
		}

		// RM4097 - adicionado por Vivianne Sousa - 03/01/2011 - analista:Ana
		// Cristina
		if (nComando.getIndicadorContaNomeCliente() != null
				&& nComando.getIndicadorContaNomeCliente().equals(ConstantesSistema.SIM) && colecaoContasIds != null
				&& !colecaoContasIds.isEmpty()) {

			boolean existeClienteConta = getControladorFaturamento().verificarSeExisteClienteConta(
					clienteDocumentoNegativacao.getId(), colecaoContasIds);

			if (!existeClienteConta) {
				return null;
			}
		}

		// RM805 - Adicionado por Rodrigo Cabral - 03/05/2011 - Verifica se o
		// cliente permite negativa��o
		if (clienteDocumentoNegativacao.getIndicadorPermiteNegativacao() != null
				&& clienteDocumentoNegativacao.getIndicadorPermiteNegativacao().equals(ConstantesSistema.NAO)) {

			return null;
		}

		Object[] object = new Object[5];
		object[0] = quantidadeTotalItensDebito;
		object[1] = valorTotal;
		object[2] = clienteDocumentoNegativacao;
		object[3] = colecaoDebitoImovel;

		return object;
	}


	/**
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o
	 * [SB0007] Gerar
	 */
	private Integer gerarRegistroDeInclusaoNegativacao(Short tipoComando, Negativador negativador,
			NegativacaoComando comando, NegativacaoCriterio criterio, Integer quantidadeRegistro,
			Integer imovel, BigDecimal valorTotalImovel, ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper,
			DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper, Cliente cliente, NegativadorContrato contrato,
			Object[] objetoQuantidades, NegativadorMovimento movimento) throws ControladorException, ErroRepositorioException {

		Integer idNegativacaoMovimento = null;

		if (movimento == null) {
			FiltroNegativadorMovimento filtroNegativadorMovimento = new FiltroNegativadorMovimento();
			filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(
					FiltroNegativadorMovimento.NEGATIVACAO_COMANDO, comando.getId()));
			
			Collection colecaoNegativadorMovimento = RepositorioUtilHBM.getInstancia().pesquisar(
					filtroNegativadorMovimento, NegativadorMovimento.class.getName());
			
			if (colecaoNegativadorMovimento != null && !colecaoNegativadorMovimento.isEmpty()) {
				movimento = (NegativadorMovimento) colecaoNegativadorMovimento.iterator().next();
				idNegativacaoMovimento = movimento.getId();
			} else {
				int numeroSequencialEnvio = contrato.getNumeroSequencialEnvio() + 1;
				
				idNegativacaoMovimento = this.gerarNegativadorMovimento(negativador.getId(), numeroSequencialEnvio, comando.getId());
				
				objetoQuantidades[3] = idNegativacaoMovimento;
				quantidadeRegistro = this.gerarRegistroDeInclusaoTipoHeader(tipoComando, quantidadeRegistro, negativador, contrato,
						comando, criterio, idNegativacaoMovimento);
			}
		} else {
			idNegativacaoMovimento = movimento.getId();
		}
		
		quantidadeRegistro = this.gerarRegistroDeInclusaoTipoDetalhe(tipoComando, quantidadeRegistro, negativador, idNegativacaoMovimento, criterio,
				imovel, valorTotalImovel, obterDebitoImovelOuClienteHelper, dadosNegativacaoPorImovelHelper, comando, cliente);

		return quantidadeRegistro;
	}

	public Integer gerarNegativadorMovimento(Integer idNegativador, Integer numeroSequencialEnvio,
			Integer idNegativadorComando) throws ControladorException {
		
		try {
			NegativadorMovimento negativadorMovimento = new NegativadorMovimento();
			negativadorMovimento.setCodigoMovimento(NegativadorMovimento.CODIGO_MOVIMENTO_INCLUSAO);
			negativadorMovimento.setDataEnvio(new Date());
			negativadorMovimento.setDataProcessamentoEnvio(new Date());
			negativadorMovimento.setNumeroSequencialEnvio(numeroSequencialEnvio);
			negativadorMovimento.setNegativador(new Negativador(idNegativador));
			negativadorMovimento.setNegativacaoComando(new NegativacaoComando(idNegativadorComando));
			negativadorMovimento.setUltimaAlteracao(new Date());

			return (Integer) getControladorUtil().inserir(negativadorMovimento);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Gerar Registro de negativacao tipo header
	 * [UC0671] Gerar Movimento de Inclusao de Negativa��o
	 * [SB0008] Gerar Registro de tipo header
	 */
	public Integer gerarRegistroDeInclusaoTipoHeader(Short tipoComando, Integer quantidadeRegistro,
			Negativador negativador, NegativadorContrato contrato, NegativacaoComando comando,
			NegativacaoCriterio criterio, Integer idNegativacaoMovimento) throws ControladorException {

		quantidadeRegistro = NegativadorMovimentoReg.getNumeroProximoRegistro();

		StringBuilder registroTipoHeader = null;
		try {
			if (negativador.getId().equals(Negativador.NEGATIVADOR_SPC)) {
				// 1. Gerar Registro tipo Header SPC
				registroTipoHeader = this.geraRegistroTipoHeaderSPC(contrato.getNumeroSequencialEnvio(), quantidadeRegistro);
			} else {
				// 2. Gerar Registro tipo Header SERASA
				registroTipoHeader = this.geraRegistroTipoHeaderSERASA(contrato.getNumeroSequencialEnvio(), quantidadeRegistro);
			}

			// 3. Gerar o registro do movimento da negativa��o correspondente ao registro tipo Hearder
			Integer idNegativadorRegistroTipo = null;
			if (negativador.getId().equals(Negativador.NEGATIVADOR_SPC)){
				idNegativadorRegistroTipo = NegativadorRegistroTipo.ID_SPC_HEADER;
			} else {
				idNegativadorRegistroTipo = NegativadorRegistroTipo.ID_SERASA_HEADER;
			}
			
			this.gerarNegativadorMovimentoRegistro(negativador.getId(), idNegativacaoMovimento, registroTipoHeader,
					quantidadeRegistro, criterio, idNegativadorRegistroTipo);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return quantidadeRegistro;

	}

	private void gerarNegativadorMovimentoRegistro(Integer idNegativador, Integer idNMovimento, StringBuilder registroHeader,
			Integer quantidadeRegistros, NegativacaoCriterio criterio, Integer idRegistroTipo) throws ControladorException {
		
		NegativadorMovimentoReg negativadorMovimentoReg = new NegativadorMovimentoReg();
		negativadorMovimentoReg.setNegativadorMovimento(new NegativadorMovimento(idNMovimento));
		negativadorMovimentoReg.setNegativadorRegistroTipo(new NegativadorRegistroTipo(idRegistroTipo));
		if (criterio != null)
			negativadorMovimentoReg.setNegativacaoCriterio(criterio);
		negativadorMovimentoReg.setConteudoRegistro(registroHeader.toString());
		negativadorMovimentoReg.setIndicadorSituacaoDefinitiva(ConstantesSistema.NAO);
		negativadorMovimentoReg.setNumeroRegistro(quantidadeRegistros);
		negativadorMovimentoReg.setIndicadorItemAtualizado(ConstantesSistema.NAO);
		negativadorMovimentoReg.setUltimaAlteracao(new Date());
		
		try {
			getControladorUtil().inserir(negativadorMovimentoReg);
		} catch (ControladorException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Gerar Registro de negativacao tipo header
	 * [UC0671] Gerar Movimento de Inclusao de Negativa��o
	 * [SB0009] Gerar Registro de tipo Detalhe
	 */
	private Integer gerarRegistroDeInclusaoTipoDetalhe(Short tipoComando, Integer quantidadeRegistro, Negativador negativador,
			Integer idNegativacaoMovimento, NegativacaoCriterio criterio, Integer idImovel, BigDecimal valorTotalImovel,
			ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper, DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper,
			NegativacaoComando comando, Cliente cliente) throws ControladorException {

		try {
			NegativadorMovimento movimento = new NegativadorMovimento();
			movimento.setId(idNegativacaoMovimento);

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa.getDadosImoveis(idImovel));

			// Detalhe SPC
			if (new Integer(negativador.getId()).equals(Negativador.NEGATIVADOR_SPC)) {
				quantidadeRegistro = quantidadeRegistro + 1;
				int numeroRegistro = NegativadorMovimentoReg.getNumeroProximoRegistro();
				
				this.inserirNegativadorMovimentoRegistroConsumidorSPC(tipoComando,
						quantidadeRegistro,
						criterio,
						idImovel,
						cliente,
						movimento,
						numeroRegistro);

				NegativadorMovimentoReg negativadorMovimentoRegistro = this.inserirNegativadorMovimentoRegistroSPC(
						tipoComando,
						criterio,
						valorTotalImovel,
						cliente,
						movimento,
						imovel,
						numeroRegistro,
						quantidadeRegistro,
						obterDebitoImovelOuClienteHelper,
						dadosNegativacaoPorImovelHelper);

				this.inserirNegativadorMovimentoRegistroItemContas(
						tipoComando,
						obterDebitoImovelOuClienteHelper,
						dadosNegativacaoPorImovelHelper,
						negativadorMovimentoRegistro);

				this.inserirNegativadorMovimentoRegistroItemGuiasPagamento(tipoComando,
						obterDebitoImovelOuClienteHelper,
						dadosNegativacaoPorImovelHelper,
						negativadorMovimentoRegistro);

				// Detalhe SERASA
			} else if (new Integer(negativador.getId()).equals(Negativador.NEGATIVADOR_SERASA)) {
				quantidadeRegistro = NegativadorMovimentoReg.getNumeroProximoRegistro();

				NegativadorMovimentoReg negativadorMovimentoRegistro = this.inserirNegativadorMovimentoRegistroSERASA(
						tipoComando,
						quantidadeRegistro,
						criterio,
						valorTotalImovel,
						obterDebitoImovelOuClienteHelper,
						dadosNegativacaoPorImovelHelper,
						cliente,
						movimento,
						imovel);
				
				this.inserirNegativadorMovimentoRegistroItemContas(
						tipoComando,
						obterDebitoImovelOuClienteHelper,
						dadosNegativacaoPorImovelHelper,
						negativadorMovimentoRegistro);

				this.inserirNegativadorMovimentoRegistroItemGuiasPagamento(tipoComando,
						obterDebitoImovelOuClienteHelper,
						dadosNegativacaoPorImovelHelper,
						negativadorMovimentoRegistro);
			}

			this.inserirNegativacaoImoveisInclusaoNegativacao(comando, cliente, imovel);
			this.inserirImovelCobrancaSituacaoInclusaoNegativacao(tipoComando, negativador, obterDebitoImovelOuClienteHelper,
					dadosNegativacaoPorImovelHelper, cliente, imovel);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return quantidadeRegistro;
	}

	private NegativadorMovimentoReg inserirNegativadorMovimentoRegistroSERASA(Short tipoComando, Integer quantidadeRegistro,
			NegativacaoCriterio criterio, BigDecimal valorTotalImovel, ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper,
			DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper, Cliente cliente, NegativadorMovimento movimento, Imovel imovel)
			throws ControladorException, ErroRepositorioException {
		
		StringBuilder detalheConsumidorSERASA = this.geraRegistroTipoDetalheSERASA(quantidadeRegistro, valorTotalImovel,
				obterDebitoImovelOuClienteHelper, imovel, dadosNegativacaoPorImovelHelper, tipoComando, cliente);

		NegativadorMovimentoReg negativadorMovimentoRegistro = new NegativadorMovimentoReg();
		negativadorMovimentoRegistro.setNegativadorMovimento(movimento);
		negativadorMovimentoRegistro.setNegativadorRegistroTipo(new NegativadorRegistroTipo(NegativadorRegistroTipo.ID_SERASA_DETALHE));
		negativadorMovimentoRegistro.setConteudoRegistro(detalheConsumidorSERASA.toString());
		negativadorMovimentoRegistro.setValorDebito(valorTotalImovel);
		negativadorMovimentoRegistro.setCobrancaDebitoSituacao(new CobrancaDebitoSituacao(CobrancaDebitoSituacao.PENDENTE));
		negativadorMovimentoRegistro.setImovel(imovel);
		negativadorMovimentoRegistro.setLocalidade(new Localidade(imovel.getLocalidade().getId()));
		negativadorMovimentoRegistro.setQuadra(new Quadra(imovel.getQuadra().getId()));
		negativadorMovimentoRegistro.setCodigoSetorComercial(imovel.getQuadra().getSetorComercial().getCodigo());
		negativadorMovimentoRegistro.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
		negativadorMovimentoRegistro.setImovelPerfil(new ImovelPerfil(imovel.getImovelPerfil().getId()));
		negativadorMovimentoRegistro.setCliente(cliente);
		
		Categoria categoria = getControladorImovel().obterPrincipalCategoriaImovel(imovel.getId());
		negativadorMovimentoRegistro.setCategoria(categoria);
		
		negativadorMovimentoRegistro.setNumeroCpf(cliente.getCpf());
		negativadorMovimentoRegistro.setNumeroCnpj(cliente.getCnpj());
		
		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO))
			negativadorMovimentoRegistro.setNegativacaoCriterio(criterio);
		
		negativadorMovimentoRegistro.setIndicadorSituacaoDefinitiva(ConstantesSistema.NAO);
		negativadorMovimentoRegistro.setNumeroRegistro(quantidadeRegistro);
		negativadorMovimentoRegistro.setCobrancaSituacao(new CobrancaSituacao(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SERASA));
		negativadorMovimentoRegistro.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);
		negativadorMovimentoRegistro.setLigacaoAguaSituacao(new LigacaoAguaSituacao(imovel.getLigacaoAguaSituacao().getId()));
		negativadorMovimentoRegistro.setLigacaoEsgotoSituacao(new LigacaoEsgotoSituacao(imovel.getLigacaoEsgotoSituacao().getId()));
		negativadorMovimentoRegistro.setUltimaAlteracao(new Date());

		Integer idDetalheRegistro = (Integer) RepositorioUtilHBM.getInstancia().inserir(negativadorMovimentoRegistro);
		negativadorMovimentoRegistro.setId(idDetalheRegistro);
		
		return negativadorMovimentoRegistro;
	}

	private void inserirNegativadorMovimentoRegistroItemGuiasPagamento(Short tipoComando, ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper,
			DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper, NegativadorMovimentoReg negativadorMovimentoRegistro)
			throws ErroRepositorioException {
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores = null;
		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
			colecaoGuiasPagamentoValores = dadosNegativacaoPorImovelHelper.getColecaoGuias();
		} else if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO)) {
			colecaoGuiasPagamentoValores = obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores();
		}

		if (colecaoGuiasPagamentoValores != null) {
			for (GuiaPagamentoValoresHelper guiaPagamentoValores : colecaoGuiasPagamentoValores) {
				
				NegativadorMovimentoRegItem item = new NegativadorMovimentoRegItem();
				item.setNegativadorMovimentoReg(negativadorMovimentoRegistro);
				item.setDocumentoTipo(new DocumentoTipo(DocumentoTipo.GUIA_PAGAMENTO));
				item.setGuiaPagamentoGeral(new GuiaPagamentoGeral(guiaPagamentoValores.getGuiaPagamento().getId()));
				item.setCobrancaDebitoSituacao(negativadorMovimentoRegistro.getCobrancaDebitoSituacao());
				item.setValorDebito(guiaPagamentoValores.getGuiaPagamento().getValorDebito());
				item.setDataSituacaoDebito(new Date());
				item.setIndicadorSituacaoDefinitiva(ConstantesSistema.NAO);
				item.setUltimaAlteracao(new Date());
				
				try {
					getControladorUtil().inserir(item);
				} catch (ControladorException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void inserirNegativadorMovimentoRegistroItemContas(Short tipoComando,
			ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper,
			DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper,
			NegativadorMovimentoReg negativadorMovimentoRegistro) throws ErroRepositorioException {
		
		Collection<ContaValoresHelper> colecaoContasValores = null;
		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
			colecaoContasValores = dadosNegativacaoPorImovelHelper.getColecaoConta();
		} else if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO)) {
			colecaoContasValores = obterDebitoImovelOuClienteHelper.getColecaoContasValores();
		}

		if (colecaoContasValores != null) {
			for (ContaValoresHelper contaValores : colecaoContasValores) {
				NegativadorMovimentoRegItem item = new NegativadorMovimentoRegItem();
				item.setNegativadorMovimentoReg(negativadorMovimentoRegistro);
				item.setDocumentoTipo(new DocumentoTipo(DocumentoTipo.CONTA));
				item.setContaGeral(new ContaGeral(contaValores.getConta().getId()));
				item.setCobrancaDebitoSituacao(negativadorMovimentoRegistro.getCobrancaDebitoSituacao());
				item.setValorDebito(contaValores.getValorTotalConta());
				item.setDataSituacaoDebito(new Date());
				item.setIndicadorSituacaoDefinitiva(ConstantesSistema.NAO);
				item.setUltimaAlteracao(new Date());
				
				try {
					getControladorUtil().inserir(item);
				} catch (ControladorException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private NegativadorMovimentoReg inserirNegativadorMovimentoRegistroSPC(Short tipoComando, NegativacaoCriterio criterio,
			BigDecimal valorTotalImovel, Cliente cliente, NegativadorMovimento movimento, Imovel imovel, int numeroRegistro,
			Integer quantidadeRegistro, ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper,
			DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper) throws ControladorException, ErroRepositorioException {
		
		char[] detalheSPC = this.geraRegistroTipoDetalheSPC(tipoComando, imovel.getId(), valorTotalImovel, obterDebitoImovelOuClienteHelper,
				dadosNegativacaoPorImovelHelper, cliente, quantidadeRegistro+"");
		
		NegativadorMovimentoReg negativadorMovimentoRegistro = new NegativadorMovimentoReg();
		negativadorMovimentoRegistro.setNegativadorMovimento(movimento);
		negativadorMovimentoRegistro.setNegativadorRegistroTipo(new NegativadorRegistroTipo(NegativadorRegistroTipo.ID_SPC_DETALHE_SPC));
		negativadorMovimentoRegistro.setConteudoRegistro(new String(detalheSPC));
		negativadorMovimentoRegistro.setValorDebito(valorTotalImovel);
		negativadorMovimentoRegistro.setCobrancaDebitoSituacao(new CobrancaDebitoSituacao(CobrancaDebitoSituacao.PENDENTE));
		negativadorMovimentoRegistro.setImovel(imovel);
		negativadorMovimentoRegistro.setLocalidade(new Localidade(imovel.getLocalidade().getId()));
		negativadorMovimentoRegistro.setQuadra(new Quadra(imovel.getQuadra().getId()));
		negativadorMovimentoRegistro.setCodigoSetorComercial(imovel.getQuadra().getSetorComercial().getCodigo());
		negativadorMovimentoRegistro.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
		negativadorMovimentoRegistro.setImovelPerfil(new ImovelPerfil(imovel.getImovelPerfil().getId()));
		negativadorMovimentoRegistro.setCliente(cliente);

		Categoria categoria = getControladorImovel().obterPrincipalCategoriaImovel(imovel.getId());
		negativadorMovimentoRegistro.setCategoria(categoria);

		negativadorMovimentoRegistro.setNumeroCpf(cliente.getCpf());
		negativadorMovimentoRegistro.setNumeroCnpj(cliente.getCnpj());
		negativadorMovimentoRegistro.setUltimaAlteracao(new Date());
		
		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO))
			negativadorMovimentoRegistro.setNegativacaoCriterio(criterio);
		
		negativadorMovimentoRegistro.setIndicadorSituacaoDefinitiva((short) 2);
		negativadorMovimentoRegistro.setNumeroRegistro(numeroRegistro);
		negativadorMovimentoRegistro.setCobrancaSituacao(new CobrancaSituacao(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC));
		negativadorMovimentoRegistro.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);
		negativadorMovimentoRegistro.setLigacaoAguaSituacao(new LigacaoAguaSituacao(imovel.getLigacaoAguaSituacao().getId()));
		negativadorMovimentoRegistro.setLigacaoEsgotoSituacao(new LigacaoEsgotoSituacao(imovel.getLigacaoEsgotoSituacao().getId()));
		
		Integer id = (Integer) RepositorioUtilHBM.getInstancia().inserir(negativadorMovimentoRegistro);
		negativadorMovimentoRegistro.setId(id);
		
		return negativadorMovimentoRegistro;
	}

	private void inserirNegativadorMovimentoRegistroConsumidorSPC(Short tipoComando, Integer quantidadeRegistro, NegativacaoCriterio criterio,
			Integer idImovel, Cliente cliente, NegativadorMovimento movimento, int numeroRegistro) throws ControladorException, ErroRepositorioException {

		StringBuilder detalheConsumidorSPC = this.geraRegistroTipoDetalheConsumidorSPC(quantidadeRegistro, cliente, idImovel);

		NegativadorMovimentoReg negativadorMovimentoRegistro = new NegativadorMovimentoReg();
		negativadorMovimentoRegistro.setNegativadorMovimento(movimento);
		negativadorMovimentoRegistro.setNegativadorRegistroTipo(new NegativadorRegistroTipo(
				NegativadorRegistroTipo.ID_SPC_DETALHE_CONSUMIDOR));
		negativadorMovimentoRegistro.setConteudoRegistro(detalheConsumidorSPC.toString());
		negativadorMovimentoRegistro.setUltimaAlteracao(new Date());
		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO))
			negativadorMovimentoRegistro.setNegativacaoCriterio(criterio);
		negativadorMovimentoRegistro.setIndicadorSituacaoDefinitiva((short) 2);
		negativadorMovimentoRegistro.setNumeroRegistro(numeroRegistro);
		negativadorMovimentoRegistro.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);
		
		RepositorioUtilHBM.getInstancia().inserir(negativadorMovimentoRegistro);
	}

	private void inserirImovelCobrancaSituacaoInclusaoNegativacao(Short tipoComando, Negativador negativador,
			ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper, DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper,
			Cliente cliente, Imovel imovel) throws ErroRepositorioException {
		ImovelCobrancaSituacao imovelCobrancaSituacao = new ImovelCobrancaSituacao();
		imovelCobrancaSituacao.setImovel(imovel);
		imovelCobrancaSituacao.setDataImplantacaoCobranca(new Date());
		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		if (new Integer(negativador.getId()).equals(Negativador.NEGATIVADOR_SPC)) {
			cobrancaSituacao.setId(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC);
		} else if (new Integer(negativador.getId()).equals(Negativador.NEGATIVADOR_SERASA)) {
			cobrancaSituacao.setId(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SERASA);
		}
		imovelCobrancaSituacao.setCobrancaSituacao(cobrancaSituacao);
		imovelCobrancaSituacao.setCliente(cliente);
		imovelCobrancaSituacao.setUltimaAlteracao(new Date());
		Integer anoMesReferenciaInicio = null;
		Integer anoMesReferenciaFinal = null;
		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
			anoMesReferenciaInicio = dadosNegativacaoPorImovelHelper.getAnoMesReferenciaInicioDebito();
			anoMesReferenciaFinal = dadosNegativacaoPorImovelHelper.getAnoMesReferenciaFinalDebito();
		} else if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO)) {
			anoMesReferenciaInicio = obterDebitoImovelOuClienteHelper.getAnoMesReferenciaInicioDebito();
			anoMesReferenciaFinal = obterDebitoImovelOuClienteHelper.getAnoMesReferenciaFinalDebito();
		}
		imovelCobrancaSituacao.setAnoMesReferenciaInicio(anoMesReferenciaInicio);
		imovelCobrancaSituacao.setAnoMesReferenciaFinal(anoMesReferenciaFinal);
		RepositorioUtilHBM.getInstancia().inserir(imovelCobrancaSituacao);
	}

	private void inserirNegativacaoImoveisInclusaoNegativacao(NegativacaoComando comando,
			Cliente cliente, Imovel imovel) throws ErroRepositorioException {
		NegativacaoImoveis negativacaoImoveis = new NegativacaoImoveis();
		negativacaoImoveis.setNegativacaoComando(comando);
		negativacaoImoveis.setImovel(imovel);
		negativacaoImoveis.setUltimaAlteracao(new Date());
		negativacaoImoveis.setIndicadorExcluido((short) 2);
		negativacaoImoveis.setCliente(cliente);
		RepositorioUtilHBM.getInstancia().inserir(negativacaoImoveis);
	}

	private char[] geraRegistroTipoDetalheSPC(Short tipoComando, Integer idImovel,
			BigDecimal valorTotalImovel, ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper,
			DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper, Cliente cliente,
			String numeroRegistroString) throws ControladorException {
		
		char[] registroInclusao = new char[340];
		
		try {
			for (int i = 0; i < registroInclusao.length; i++) {
				registroInclusao[i] = ' ';
			}
			
			// D2.01
			colocarConteudo("02", 1, registroInclusao);
			
			if (cliente.getCnpj() != null && cliente.getCnpj().length() > 0) {
				// D2.02
				colocarConteudo("1", 3, registroInclusao);
				String cnpj = Util.adicionarZerosEsquedaNumero(15, "" + cliente.getCnpj());
				// D2.03
				colocarConteudo(cnpj, 4, registroInclusao);
			} else {
				// D2.02
				colocarConteudo("2", 3, registroInclusao);
				String cpf = Util.adicionarZerosEsquedaNumero(15, "" + cliente.getCpf());
				// D2.03
				colocarConteudo(cpf, 4, registroInclusao);
			}
			
			// D2.04
			colocarConteudo("I", 19, registroInclusao);
			// D2.05
			colocarConteudo("C", 20, registroInclusao);
			
			Date maiorData = null;
			Date menorData = null;
			
			if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
				maiorData = this.obterMaiorVencimento(dadosNegativacaoPorImovelHelper.getColecaoConta(),
						dadosNegativacaoPorImovelHelper.getColecaoGuias());
				menorData = this.obterMenorVencimento(dadosNegativacaoPorImovelHelper.getColecaoConta(),
						dadosNegativacaoPorImovelHelper.getColecaoGuias());
				
			} else if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO)) {
				maiorData = this.obterMaiorVencimento((List) obterDebitoImovelOuClienteHelper.getColecaoContasValores(),
						(List) obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores());
				menorData = this.obterMenorVencimento((List) obterDebitoImovelOuClienteHelper.getColecaoContasValores(),
						(List) obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores());
			}
			
			if (maiorData != null) {
				String D206 = Util.formatarDataSemBarraDDMMAAAA(maiorData);
				colocarConteudo(D206, 21, registroInclusao);
			}
			
			if (menorData != null) {
				String D207 = Util.formatarDataSemBarraDDMMAAAA(menorData);
				colocarConteudo(D207, 29, registroInclusao);
			}
			
			String valorString = Util.formatarMoedaReal(valorTotalImovel);
			String valorNovo = "";
			
			for (int i = 0; i < valorString.length(); i++) {
				if (valorString.charAt(i) != '.' && valorString.charAt(i) != ',') {
					valorNovo = valorNovo + valorString.charAt(i);
				}
			}
			
			valorString = valorNovo;
			valorString = Util.adicionarZerosEsquedaNumero(13, valorString);
			
			colocarConteudo(valorString, 37, registroInclusao);
			colocarConteudo(idImovel + "", 50, registroInclusao);
			colocarConteudo("00102779", 80, registroInclusao);
			colocarConteudo("00", 88, registroInclusao);
			
			numeroRegistroString = Util.adicionarZerosEsquedaNumero(6, numeroRegistroString);
			
			colocarConteudo(numeroRegistroString, 335, registroInclusao);
		} catch (Exception e) {
			
			throw new ControladorException("erro.sistema", e);
		}
		
		return registroInclusao;
	}

	/**
	 * Obter Documento de Negativacao
	 *
	 * [UC0671] Gerar Movimento de Inclusao de Nwegativa��o [SB0010] Obter
	 * Documento de Negativacao
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param nc
	 * @param Object
	 *            [] obj obj[0] Integer - quantidadeInclusao obj[1] Integer -
	 *            quantidadeItens obj[2] BigDecimal - valorTotalDebito
	 * @return
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private Cliente obterDocumentoNegativacao(Integer idImovel, int idNegativacaoCriterio, Cliente clienteParcelamento)
			throws ErroRepositorioException, ControladorException {

		Cliente retorno = null;

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		Collection<ClienteImovel> clientesImovel = repositorioSpcSerasa.pesquisarClienteImovelParaNegativacao(idImovel,
				sistemaParametro.getCnpjEmpresa());

		if (clientesImovel == null || clientesImovel.isEmpty()) {
			return null;
		}

		if (clienteParcelamento != null && !clienteParcelamento.equals("")) {
			ClienteImovel clienteImovelParc = new ClienteImovel();
			clienteImovelParc.setCliente(clienteParcelamento);
			ClienteRelacaoTipo relacaoCliente = new ClienteRelacaoTipo();
			relacaoCliente.setId(4);
			clienteImovelParc.setClienteRelacaoTipo(relacaoCliente);
			clientesImovel.add(clienteImovelParc);
		}

		Collection<ClienteImovel> clientesImovelCPFCNPJ = new ArrayList();
		// boolean achoCnpj = false;
		Iterator ItClientesImovel = clientesImovel.iterator();
		while (ItClientesImovel.hasNext()) {
			ClienteImovel clienteImovel = (ClienteImovel) ItClientesImovel.next();
			String clienteCNPJ = clienteImovel.getCliente().getCnpj();
			String clienteCPF = clienteImovel.getCliente().getCpf();
			if ((clienteCNPJ != null && !clienteCNPJ.trim().equals("") && Util.validarStringNumerica(clienteCNPJ))
					|| (clienteCPF != null && !clienteCPF.trim().equals("") && Util.validarStringNumerica(clienteCPF))) {
				clientesImovelCPFCNPJ.add(clienteImovel);
				// if(clienteCNPJ != null && !clienteCNPJ.trim().equals("") &&
				// !clienteCNPJ.trim().equals("")
				// && Util.validarStringNumerica(clienteCNPJ)){
				// achoCnpj = true;
				// }
			}
		}

		// /////////////////////////////////////////////////////////////////////

		// Collection<ClienteImovel> clientesImovelNegativacao = new
		// ArrayList();
		// if(clientesImovelCPFCNPJ != null &&
		// !clientesImovelCPFCNPJ.isEmpty()){
		// if(achoCnpj){
		// Iterator ItClientesImovelCnpj = clientesImovelCPFCNPJ.iterator();
		// while (ItClientesImovelCnpj.hasNext()) {
		// ClienteImovel clienteImovel = (ClienteImovel)
		// ItClientesImovelCnpj.next();
		// String clienteCNPJ = clienteImovel.getCliente().getCnpj();
		// if(clienteCNPJ != null && !clienteCNPJ.trim().equals("") &&
		// Util.validarStringNumerica(clienteCNPJ)){
		// clientesImovelNegativacao.add(clienteImovel);
		// }
		// }
		// }else{
		// // pesquisando a categoria
		// // [UC0306] - Obtter principal categoria do im�vel
		// Categoria categoria =
		// this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
		// Imovel imovel = new Imovel();
		// imovel.setId(idImovel);
		// Integer qtdEconomia =
		// this.getControladorImovel().obterQuantidadeEconomias(imovel);
		// if(qtdEconomia <= 5 && categoria.getId() ==
		// Categoria.RESIDENCIAL_INT){
		// clientesImovelNegativacao.addAll(clientesImovelCPFCNPJ);
		// }
		// }
		// }else{
		// return null;
		// }
		//
		// if(clientesImovelNegativacao == null ||
		// clientesImovelNegativacao.isEmpty()){
		// return null;
		// }

		// RM3447 - alterado por Vivianne Sousa - 28/12/2010 - analista:Adriana
		// Ribeiro
		// Priorizar titularidade de clientes na negativa��o
		if (clientesImovelCPFCNPJ == null || clientesImovelCPFCNPJ.isEmpty()) {
			return null;
		}

		// 1
		List negCritCpfTps = this.repositorioSpcSerasa.obtemTitularidadesDocumentos(idNegativacaoCriterio);

		// 2
		if (negCritCpfTps.size() == 1) {

			NegativacaoCriterioCpfTipo negCritCpfTp = (NegativacaoCriterioCpfTipo) Util
					.retonarObjetoDeColecao(negCritCpfTps);

			short clienteTipo = 4;
			if (negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)) {
				clienteTipo = ClienteRelacaoTipo.USUARIO;
			} else if (negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)) {
				clienteTipo = ClienteRelacaoTipo.PROPRIETARIO;
			} else if (negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)) {
				clienteTipo = ClienteRelacaoTipo.RESPONSAVEL;
			}

			Iterator clientesImovelIterator = clientesImovelCPFCNPJ.iterator();
			while (clientesImovelIterator.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) clientesImovelIterator.next();
				if (clienteImovel.getClienteRelacaoTipo().getId().shortValue() == clienteTipo) {

					boolean achouCnpjCpf = false;

					String clienteCNPJ = clienteImovel.getCliente().getCnpj();
					if (clienteCNPJ != null && !clienteCNPJ.trim().equals("")
							&& Util.validarStringNumerica(clienteCNPJ)) {
						achouCnpjCpf = true;
					} else {
						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
						Categoria categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						Integer qtdEconomia = this.getControladorImovel().obterQuantidadeEconomias(imovel);
						if (qtdEconomia <= 5 && categoria.getId() == Categoria.RESIDENCIAL_INT) {
							achouCnpjCpf = true;
						}
					}

					if (achouCnpjCpf) {
						boolean verificarEnderecoCliente = getControladorEndereco().verificarExistenciaClienteEndereco(
								clienteImovel.getCliente().getId());
						if (verificarEnderecoCliente) {
							return clienteImovel.getCliente();
						} else {
							return null;
						}
					}
					// else{
					// return null;
					// }

				}
			}
		} else {
			// 3
			// 3.1.1
			Iterator itNegCritCpfTp = negCritCpfTps.iterator();
			while (itNegCritCpfTp.hasNext()) {
				NegativacaoCriterioCpfTipo negCritCpfTp = (NegativacaoCriterioCpfTipo) itNegCritCpfTp.next();
				// 3.1
				if (negCritCpfTp.getNumeroOrdemSelecao() != 0) {
					Short clinteTipo = 0;
					if (negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)) {
						clinteTipo = ClienteRelacaoTipo.USUARIO;
					} else if (negCritCpfTp.getCpfTipo().getId()
							.equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)) {
						clinteTipo = ClienteRelacaoTipo.PROPRIETARIO;
					} else if (negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)) {
						clinteTipo = ClienteRelacaoTipo.RESPONSAVEL;
					} else {
						clinteTipo = 4;
					}

					Iterator clientesImovelIterator = clientesImovelCPFCNPJ.iterator();
					while (clientesImovelIterator.hasNext()) {
						ClienteImovel clienteImovel = (ClienteImovel) clientesImovelIterator.next();
						if (clienteImovel.getClienteRelacaoTipo().getId().shortValue() == (clinteTipo.shortValue())) {

							boolean achouCnpjCpf = false;

							String clienteCNPJ = clienteImovel.getCliente().getCnpj();
							if (clienteCNPJ != null && !clienteCNPJ.trim().equals("")
									&& Util.validarStringNumerica(clienteCNPJ)) {
								achouCnpjCpf = true;
							} else {
								// pesquisando a categoria
								// [UC0306] - Obtter principal categoria do
								// im�vel
								Categoria categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(
										idImovel);
								Imovel imovel = new Imovel();
								imovel.setId(idImovel);
								Integer qtdEconomia = this.getControladorImovel().obterQuantidadeEconomias(imovel);
								if (qtdEconomia <= 5 && categoria.getId() == Categoria.RESIDENCIAL_INT) {
									achouCnpjCpf = true;
								}
							}

							if (achouCnpjCpf) {
								boolean verificarEnderecoCliente = getControladorEndereco()
										.verificarExistenciaClienteEndereco(clienteImovel.getCliente().getId());
								if (verificarEnderecoCliente) {
									return clienteImovel.getCliente();
								} else {
									return null;
								}
							}
							// else{
							// return null;
							// }

						}
					}

				}
			}

			// ///////////////////////////////////////////////////////////////////
			// RM3447 - comentado por Vivianne Sousa - 28/12/2010
			// Collection<ClienteImovel> clientesImovelNegativacao = new
			// ArrayList();
			// if(clientesImovelCPFCNPJ != null &&
			// !clientesImovelCPFCNPJ.isEmpty()){
			// if(achoCnpj){
			// Iterator ItClientesImovelCnpj = clientesImovelCPFCNPJ.iterator();
			// while (ItClientesImovelCnpj.hasNext()) {
			// ClienteImovel clienteImovel = (ClienteImovel)
			// ItClientesImovelCnpj.next();
			// String clienteCNPJ = clienteImovel.getCliente().getCnpj();
			// if(clienteCNPJ != null && !clienteCNPJ.trim().equals("") &&
			// Util.validarStringNumerica(clienteCNPJ)){
			// clientesImovelNegativacao.add(clienteImovel);
			// }
			// }
			// }else{
			// // pesquisando a categoria
			// // [UC0306] - Obtter principal categoria do im�vel
			// Categoria categoria =
			// this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
			// Imovel imovel = new Imovel();
			// imovel.setId(idImovel);
			// Integer qtdEconomia =
			// this.getControladorImovel().obterQuantidadeEconomias(imovel);
			// if(qtdEconomia <= 5 && categoria.getId() ==
			// Categoria.RESIDENCIAL_INT){
			// clientesImovelNegativacao.addAll(clientesImovelCPFCNPJ);
			// }
			// }
			// }else{
			// return null;
			// }
			//
			// if(clientesImovelNegativacao == null ||
			// clientesImovelNegativacao.isEmpty()){
			// return null;
			// }
			//
			// // 1
			// List negCritCpfTps = this.repositorioSpcSerasa
			// .obtemTitularidadesDocumentos(idNegativacaoCriterio);
			//
			// //2
			// if (negCritCpfTps.size() == 1 ) {
			//
			// NegativacaoCriterioCpfTipo negCritCpfTp =
			// (NegativacaoCriterioCpfTipo)
			// Util.retonarObjetoDeColecao(negCritCpfTps);
			//
			// short clienteTipo = 4;
			// if(negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)){
			// clienteTipo = ClienteRelacaoTipo.USUARIO;
			// }else
			// if(negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)){
			// clienteTipo = ClienteRelacaoTipo.PROPRIETARIO;
			// }else
			// if(negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)){
			// clienteTipo = ClienteRelacaoTipo.RESPONSAVEL;
			// }
			//
			//
			// Iterator clientesImovelIterator =
			// clientesImovelNegativacao.iterator();
			// while (clientesImovelIterator.hasNext()) {
			// ClienteImovel clienteImovel = (ClienteImovel)
			// clientesImovelIterator.next();
			// if(clienteImovel.getClienteRelacaoTipo().getId().shortValue() ==
			// clienteTipo ){
			// boolean verificarEnderecoCliente =
			// getControladorEndereco().verificarExistenciaClienteEndereco(clienteImovel.getCliente().getId());
			// if(verificarEnderecoCliente){
			// return clienteImovel.getCliente();
			// }else{
			// return null;
			// }
			// }
			// }
			// //chamdno o [SB0011] Obter Dados do Cliente da Negaticacao
			// //retorno =
			// obterDadosClienteNegativacao(imovel,negCritCpfTp.getCpfTipo().getId(),
			// clienteParcelamento);
			//
			// } else {
			// //3
			// // 3.1.1
			// //Cliente clienteOrdemSelecao = null;
			// Iterator itNegCritCpfTp = negCritCpfTps.iterator();
			// while(itNegCritCpfTp.hasNext()){
			// NegativacaoCriterioCpfTipo negCritCpfTp =
			// (NegativacaoCriterioCpfTipo) itNegCritCpfTp.next();
			// //3.1
			// if(negCritCpfTp.getNumeroOrdemSelecao()!= 0){
			// Short clinteTipo = 0;
			// if(negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)){
			// clinteTipo = ClienteRelacaoTipo.USUARIO;
			// }else
			// if(negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)){
			// clinteTipo = ClienteRelacaoTipo.PROPRIETARIO;
			// }else
			// if(negCritCpfTp.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)){
			// clinteTipo = ClienteRelacaoTipo.RESPONSAVEL;
			// }else{
			// clinteTipo = 4;
			// }
			//
			// Iterator clientesImovelIterator =
			// clientesImovelNegativacao.iterator();
			// while (clientesImovelIterator.hasNext()) {
			// ClienteImovel clienteImovel = (ClienteImovel)
			// clientesImovelIterator.next();
			// if(clienteImovel.getClienteRelacaoTipo().getId().shortValue() ==
			// (clinteTipo.shortValue())){
			// boolean verificarEnderecoCliente =
			// getControladorEndereco().verificarExistenciaClienteEndereco(clienteImovel.getCliente().getId());
			// if(verificarEnderecoCliente){
			// return clienteImovel.getCliente();
			// }else{
			// return null;
			// }
			// }
			// }
			// // 3.1.1
			// //chamdno o [SB0011] Obter Dados do Cliente da Negaticacao
			// //retorno =
			// obterDadosClienteNegativacao(imovel,negCritCpfTp.getCpfTipo().getId(),
			// clienteParcelamento);
			// /*if (retorno != null && (retorno.getCnpj() != null ||
			// retorno.getCpf() != null)) {
			// clienteOrdemSelecao = retorno;
			// break;
			// }*/
			// }
			// }

			// ////////////////////////////////////////////////////////////////////////////////////

			// Cliente semNumeroOrdemSelecao = null;
			/*
			 * itNegCritCpfTp = negCritCpfTps.iterator();
			 * while(itNegCritCpfTp.hasNext()){ NegativacaoCriterioCpfTipo
			 * negCritCpfTp = (NegativacaoCriterioCpfTipo)
			 * itNegCritCpfTp.next(); //3.1
			 * if(negCritCpfTp.getNumeroOrdemSelecao() == 0){ // 3.2.1 //chamdno
			 * o [SB0011] Obter Dados do Cliente da Negaticacao retorno =
			 * obterDadosClienteNegativacao
			 * (imovel,negCritCpfTp.getCpfTipo().getId(), clienteParcelamento);
			 * 
			 * if (retorno != null && clienteOrdemSelecao != null) {
			 * if(retorno.getCpf() != null && clienteOrdemSelecao.getCpf() !=
			 * null && !clienteOrdemSelecao.getCpf().equals(retorno.getCpf())) {
			 * return null; } if(retorno.getCnpj() != null &&
			 * clienteOrdemSelecao.getCnpj() != null &&
			 * !clienteOrdemSelecao.getCnpj().equals(retorno.getCnpj())) {
			 * return null; } } } }
			 */
		}

		// 4.0 Est� validando no item anterior

		return retorno;
	}

	/*****************************************************************************************/

	public void geraRegistroNegativacao(Imovel imovelNegativado, int idNegativador, int idUsuarioResponsaval,
			int idNegativadorComando, int quantidadeRegistros, int primeiraVez, NegativacaoCriterio negCriterio,
			BigDecimal valorTotalDebitosImovel, NegativacaoMovimentoHelper negativacaoMovimentoHelper,
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel, Short tipoComando,
			DadosNegativacaoPorImovelHelper listaDadosImovel) throws ControladorException, ErroRepositorioException {

		int idNegativadorMovimento = 0;
		int idDetalheReg = 0;
		StringBuilder registroTipoHeader = new StringBuilder();

		// Numero do Sequencial de envio.
		int saEnvio = this.repositorioSpcSerasa.getSaEnvioContratoNegativador(idNegativador);

		// Caso esteja gerando o primeiro registro de negativa��o
		if (primeiraVez == 0) {
			// Modifica flag para n�o entrar mais de uma vez na
			// gera��o do Header.
			// chama metodo que gera os dados do movimento da negativacao na
			// tabela NEGATIVADOR_MOVIMENTO
			idNegativadorMovimento = this.gerarNegativadorMovimento(idNegativador, (saEnvio + 1), idNegativadorComando);

			// guarda id do movimento.
			negativacaoMovimentoHelper.setIdNegativacaoMovimento(idNegativadorMovimento);

			if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)) {
				// [SB0008] - Gerar Registro tipo Header SPC
				registroTipoHeader = this.geraRegistroTipoHeaderSPC(saEnvio, quantidadeRegistros);
			} else {
				// [SB0008] - Gerar Registro tipo Header SERASA
				registroTipoHeader = this.geraRegistroTipoHeaderSERASA(saEnvio, quantidadeRegistros);
			}

			// [SB0008] - 2.
			Integer idNegativadorRegistroTipo = null;
			if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)){
				idNegativadorRegistroTipo = NegativadorRegistroTipo.ID_SPC_HEADER;
			} else {
				idNegativadorRegistroTipo = NegativadorRegistroTipo.ID_SERASA_HEADER;
			}
			
			this.gerarNegativadorMovimentoRegistro(idNegativador, idNegativadorMovimento, registroTipoHeader,
					quantidadeRegistros, negCriterio, idNegativadorRegistroTipo);
		}

		StringBuilder registroDetalheConsumidor = null;
		if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)) {
			// [SB0009]
			registroDetalheConsumidor = this.geraRegistroTipoDetalheConsumidorSPC(quantidadeRegistros,
					negCriterio.getCliente(), imovelNegativado.getId());

			// Gera registro do movimento da negativa��o correspondente ao
			// registro tipo Detalhe Consumidor SPC.
			idDetalheReg = this.repositorioSpcSerasa.geraRegistroNegativacaoRegDetalhe(idNegativador,
					idUsuarioResponsaval, saEnvio, idNegativadorComando,
					negativacaoMovimentoHelper.getIdNegativacaoMovimento(), registroDetalheConsumidor,
					quantidadeRegistros, negCriterio.getId());
		} else {
			// [SB0009]

			// if(tipoComando.equals("POR MATRICULA DE IMOVEIS")){
			// Obtem dados do cliente.
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa
					.getDadosCliente(listaDadosImovel.getIdCliente()));

			// D1.04 - Maior data dos debitos no formato AAAAMMDD
			// registroDetalheConsumidor.append(Util.recuperaDataInvertida(
			// this.obterMaiorMenorVencimento
			// ((List)tosck.getColecaoContasValores(),
			// (List)tosck.getColecaoGuiasPagamentoValores(),1)));
			//
			// // D1.05 - Mesmo conteudo de D1.04
			// registroDetalheConsumidor.append(Util.recuperaDataInvertida(
			// this.obterMaiorMenorVencimento
			// ((List)tosck.getColecaoContasValores(),
			// (List)tosck.getColecaoGuiasPagamentoValores(),1)));

			// }else if(tipoComando.equals("POR CRITERIO")){
			// // Obtem dados do cliente.
			// // cliente = (Cliente) Util
			// // .retonarObjetoDeColecao(this.repositorioSpcSerasa
			// // .getDadosCliente(cliente.getId()));
			//
			// // D1.04 - Maior data dos debitos no formato AAAAMMDD
			// registroDetalheConsumidor.append(Util.recuperaDataInvertida(
			// this.obterMaiorMenorVencimento
			// ((List)tosck.getColecaoContasValores(),
			// (List)tosck.getColecaoGuiasPagamentoValores(),1)));
			//
			// // D1.05 - Mesmo conteudo de D1.04
			// registroDetalheConsumidor.append(Util.recuperaDataInvertida(
			// this.obterMaiorMenorVencimento
			// ((List)tosck.getColecaoContasValores(),
			// (List)tosck.getColecaoGuiasPagamentoValores(),1)));
			//
			// }

			registroDetalheConsumidor = this.geraRegistroTipoDetalheSERASA(quantidadeRegistros,
					valorTotalDebitosImovel, colecaoDebitoImovel, imovelNegativado, listaDadosImovel, tipoComando,
					cliente);
		}
		// debug
		int idCategoria = 0;

		int idImovel = imovelNegativado.getId();
		// listaDadosImovel.getIdImovel();

		// Set clienteImovel = imovelNegativado.getClienteImoveis();
		// Iterator clienteIterator = clienteImovel.iterator();
		// Cliente cliente = (Cliente) clienteIterator.next();
		// cliente.getId();

		Categoria categoria = null;
		categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
		if (categoria != null) {
			idCategoria = categoria.getId();
		}
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa.getDadosImoveis(imovelNegativado
				.getId()));
		int idLocalidade = imovel.getLocalidade().getId();
		int idQuadra = imovel.getQuadra().getId();
		int stComercialCD = imovel.getQuadra().getSetorComercial().getId();
		int numeroQuadra = imovel.getQuadra().getNumeroQuadra();
		int iper_id = imovel.getImovelPerfil().getId();

		int idDebitoSituacao = this.repositorioSpcSerasa.obtemDebitoSituacao();
		int idCliente = 0;
		String cpfCliente = null;
		String cnpjCliente = null;
		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
			idCliente = listaDadosImovel.getIdCliente();

			if (listaDadosImovel.getCpfCliente() != null) {
				cpfCliente = listaDadosImovel.getCpfCliente().replace(".", "").replace("-", "");
			}
			if (listaDadosImovel.getCnpjCliente() != null) {
				cnpjCliente = listaDadosImovel.getCnpjCliente().replace(".", "").replace("-", "");
			}

			// Gera registro movimento da negativa��o correspondente
			// ao registro tipo detail-SPC.
			if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)) {
				StringBuilder registroDetalheSPC = this.geraRegistroTipoDetalheSPC(quantidadeRegistros,
						colecaoDebitoImovel, negCriterio, imovelNegativado, valorTotalDebitosImovel, listaDadosImovel,
						tipoComando);

				idDetalheReg = this.repositorioSpcSerasa.geraRegistroNegativacaoRegDetalheSPC(idNegativador,
						negativacaoMovimentoHelper.getIdNegativacaoMovimento(), registroDetalheSPC,
						quantidadeRegistros, valorTotalDebitosImovel, idDebitoSituacao, idImovel, idLocalidade,
						idQuadra, stComercialCD, numeroQuadra, iper_id, idCliente, idCategoria, cpfCliente,
						cnpjCliente, null);
			} else {
				idDetalheReg = this.repositorioSpcSerasa.geraRegistroNegativacaoRegDetalheSPC(idNegativador,
						negativacaoMovimentoHelper.getIdNegativacaoMovimento(), registroDetalheConsumidor,
						quantidadeRegistros, valorTotalDebitosImovel, idDebitoSituacao, idImovel, idLocalidade,
						idQuadra, stComercialCD, numeroQuadra, iper_id, idCliente, idCategoria, cpfCliente,
						cnpjCliente, null);
			}
			valorTotalDebitosImovel = new BigDecimal(0);
			// [SB0009] 1.5 - Para cada item da lista de contas e guias de
			// pagamento do imovel...
			Collection<ContaValoresHelper> colecaoContasValores = listaDadosImovel.getColecaoConta();
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores = listaDadosImovel.getColecaoGuias();
			Iterator itColecaoContasValores = null;
			Iterator itColecaoGuiasPagamentoValores = null;
			if (colecaoContasValores != null) {
				itColecaoContasValores = colecaoContasValores.iterator();
				while (itColecaoContasValores.hasNext()) {
					ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();

					this.repositorioSpcSerasa.geraRegistroNegativacaoMovimentoRegItem(idDebitoSituacao,
							contaValores.getValorTotalConta(), idDetalheReg, 1, null, contaValores.getConta().getId());
				}
			}

			// varre lista de guias de pagamento para totalizar
			if (colecaoGuiasPagamentoValores != null) {
				itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
				while (itColecaoGuiasPagamentoValores.hasNext()) {
					GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores
							.next();

					this.repositorioSpcSerasa.geraRegistroNegativacaoMovimentoRegItem(idDebitoSituacao,
							guiaPagamentoValores.getGuiaPagamento().getValorDebito(), idDetalheReg, 7,
							guiaPagamentoValores.getGuiaPagamento().getId(), null);

				}
			}
		} else if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO)) {
			idCliente = negCriterio.getCliente().getId();// listaDadosImovel.getIdCliente();
			idDebitoSituacao = this.repositorioSpcSerasa.obtemDebitoSituacao();

			// listaDadosImovel.getCpfCliente()
			if (negCriterio.getCliente().getCpf() != null) {
				cpfCliente = negCriterio.getCliente().getCpf().replace(".", "").replace("-", "");
			}
			if (negCriterio.getCliente().getCnpj() != null) {
				cnpjCliente = negCriterio.getCliente().getCnpj().replace(".", "").replace("-", "");
			}

			// Gera registro movimento da negativa��o correspondente
			// ao registro tipo detail-SPC.
			if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)) {
				StringBuilder registroDetalheSPC = this.geraRegistroTipoDetalheSPC(quantidadeRegistros,
						colecaoDebitoImovel, negCriterio, imovelNegativado, valorTotalDebitosImovel, listaDadosImovel,
						tipoComando);

				idDetalheReg = this.repositorioSpcSerasa.geraRegistroNegativacaoRegDetalheSPC(idNegativador,
						negativacaoMovimentoHelper.getIdNegativacaoMovimento(), registroDetalheSPC,
						quantidadeRegistros, valorTotalDebitosImovel, idDebitoSituacao, idImovel, idLocalidade,
						idQuadra, stComercialCD, numeroQuadra, iper_id, idCliente, idCategoria, cpfCliente,
						cnpjCliente, negCriterio.getId());
			} else {
				idDetalheReg = this.repositorioSpcSerasa.geraRegistroNegativacaoRegDetalheSPC(idNegativador,
						negativacaoMovimentoHelper.getIdNegativacaoMovimento(), registroDetalheConsumidor,
						quantidadeRegistros, valorTotalDebitosImovel, idDebitoSituacao, idImovel, idLocalidade,
						idQuadra, stComercialCD, numeroQuadra, iper_id, idCliente, idCategoria, cpfCliente,
						cnpjCliente, negCriterio.getId());
			}
			valorTotalDebitosImovel = new BigDecimal(0);
			// [SB0009] 1.5 - Para cada item da lista de contas e guias de
			// pagamento do imovel...
			Collection<ContaValoresHelper> colecaoContasValores = colecaoDebitoImovel.getColecaoContasValores();
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores = colecaoDebitoImovel
					.getColecaoGuiasPagamentoValores();
			Iterator itColecaoContasValores = null;
			Iterator itColecaoGuiasPagamentoValores = null;
			if (colecaoContasValores != null) {
				itColecaoContasValores = colecaoContasValores.iterator();
				while (itColecaoContasValores.hasNext()) {
					ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();
					this.repositorioSpcSerasa.geraRegistroNegativacaoMovimentoRegItem(idDebitoSituacao,
							contaValores.getValorTotalConta(), idDetalheReg, 1, null, contaValores.getConta().getId());
				}
			}

			// varre lista de guias de pagamento para totalizar
			if (colecaoGuiasPagamentoValores != null) {
				itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
				while (itColecaoGuiasPagamentoValores.hasNext()) {
					GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores
							.next();
					this.repositorioSpcSerasa.geraRegistroNegativacaoMovimentoRegItem(idDebitoSituacao,
							guiaPagamentoValores.getGuiaPagamento().getValorDebito(), idDetalheReg, 7,
							guiaPagamentoValores.getGuiaPagamento().getId(), null);
				}
			}
		}

		// [SB0012] - Gerar Arquivo TXT para envio ao negativador
		this.gerarArquivo(negativacaoMovimentoHelper.getIdNegativacaoMovimento(), true, idNegativador);
	}

	// GERA REGISTRO HEADER SPC *******************
	// ********************************************
	// CAMPOS CONFORME LAYOUT DO UC0671 [SB0008] **
	// ********************************************
	public StringBuilder geraRegistroTipoHeaderSPC(int saEnvio, int quantidadeRegistros) throws ControladorException,
			ErroRepositorioException {
		// 1.1
		StringBuilder registroHeader = new StringBuilder();
		// H.01
		registroHeader.append("00");
		// H.02
		registroHeader.append("REMESSA");
		// seta a data corrente
		// H.03
		String dataAtualString = Util.formatarDataSemBarraDDMMAAAA(new Date());
		// .recuperaDataInvertida(new Date());
		registroHeader.append(Util.completaString(dataAtualString, 8));
		// H.04
		registroHeader.append(Util.adicionarZerosEsquedaNumero(8, "" + (saEnvio + 1)));
		// H.05
		registroHeader.append("01101");
		// H.06
		registroHeader.append("00102779");
		// H.07
		String h07 = Util.recuperaDataInvertida(new Date());
		registroHeader.append(Util.completaString(h07, 8));
		// H.08
		registroHeader.append(Util.completaString(" ", 271));
		// H.09
		registroHeader.append("SPC  ");
		// H.10
		registroHeader.append("07");
		// H.11
		registroHeader.append("          ");
		// H.12
		registroHeader.append(Util.adicionarZerosEsquedaNumero(6, "" + quantidadeRegistros));

		return registroHeader;
	}

	// GERA REGISTRO DETALHE CONSUMIDOR SPC *******
	// ********************************************
	// CAMPOS CONFORME LAYOUT DO UC0671 [SB0009] **
	// ********************************************
	public StringBuilder geraRegistroTipoDetalheConsumidorSPC(int quantidadeRegistros,
			Cliente cliente, Integer idImovel) throws ControladorException {

		StringBuilder registroDetalheConsumidor = new StringBuilder();
		
		try {
			registroDetalheConsumidor.append("01");
			// D1.02
			registroDetalheConsumidor.append("50040905");
			// D1.03 - Nome do cliente
			String nomeCliente = cliente.getNome();
			registroDetalheConsumidor.append(Util.completaString(nomeCliente, 45));
			
			if (cliente.getCnpj() != null && cliente.getCnpj().length() > 0) {
				// D1.04 - Indicador se tem ou n�o CNPJ ou CPF Preenchido
				registroDetalheConsumidor.append("1");
				// D1.05 - CPF ou CNPJ
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(15, cliente.getCnpj()));
			} else {
				// D1.04 - Indicador se tem ou n�o CNPJ ou CPF Preenchido
				registroDetalheConsumidor.append("2");
				// D1.05 - CPF ou CNPJ
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(15, cliente.getCpf()));
			}
			// D1.06 - RG Cliente
			registroDetalheConsumidor.append(Util.completaString(cliente.getRg(), 20));
			
			// D1.07 - Data de Nascimento
			String D107;
			if (cliente.getDataNascimento() != null) {
				D107 = Util.formatarDataSemBarraDDMMAAAA((Date) cliente.getDataNascimento());
			} else {
				D107 = "";
			}
			registroDetalheConsumidor.append(Util.completaString(D107, 8));
			// D1.08 - Nome da Mae
			registroDetalheConsumidor.append(Util.completaString(cliente.getNomeMae(), 45));
			
			String[] parmsEndereco = null;
			Integer idClienteResponsavel = this.getControladorCliente().retornaIdClienteResponsavelIndicadorEnvioConta(idImovel);
			
			if (idClienteResponsavel != null) {
				parmsEndereco = this.getControladorEndereco().pesquisarEnderecoClienteDividido(idClienteResponsavel);
				// D1.09 - Endereco
				String ender = parmsEndereco[0];
				registroDetalheConsumidor.append(Util.completaString(ender, 50));
				// Obtem dados do endere�o do cliente.
				ClienteEndereco cliEnder = null;
				
				Collection colecaoClienteEndereco = this.repositorioSpcSerasa.getDadosEnderecoCliente(idClienteResponsavel);
				
				if (colecaoClienteEndereco == null || colecaoClienteEndereco.isEmpty()) {
					colecaoClienteEndereco = this.repositorioSpcSerasa.getCep(cliente.getId());
				}
				
				cliEnder = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);
				
				// D1.10 - numero
				registroDetalheConsumidor.append(Util.completaString(cliEnder.getNumero(), 5));
				// D1.11 - complemento
				registroDetalheConsumidor.append(Util.completaString(cliEnder.getComplemento(), 30));
				
				if (cliEnder.getLogradouroBairro() != null && !cliEnder.getLogradouroBairro().equals("") && cliEnder.getLogradouroBairro().getBairro() != null) {
					// D1.12 - Bairro
					registroDetalheConsumidor.append(Util.completaString(cliEnder.getLogradouroBairro().getBairro().getNome(), 25));
				} else {
					// D1.12 - Bairro
					registroDetalheConsumidor.append(Util.completaString("" + cliEnder.getLogradouroCep().getCep().getBairro(), 25));
				}
				
				// D1.13 - CEP
				registroDetalheConsumidor.append(Util.completaString("" + cliEnder.getLogradouroCep().getCep().getCodigo(), 8));
				// D1.14 - Munic�pio
				registroDetalheConsumidor.append(Util.completaString(cliEnder.getLogradouroCep().getCep().getMunicipio(), 30));
				// D1.15 - Unidade da Federa��o
				registroDetalheConsumidor.append(Util.completaString(cliEnder.getLogradouroCep().getCep().getSigla(), 2));
			} else {
				parmsEndereco = this.getControladorEndereco().pesquisarEnderecoImovelDividido(idImovel);
				// D1.09 - Endereco
				String ender = parmsEndereco[0];
				registroDetalheConsumidor.append(Util.completaString(ender, 50));
				// D1.10 - numero
				registroDetalheConsumidor.append(Util.completaString(parmsEndereco[5], 5));
				// D1.11 - complemento
				registroDetalheConsumidor.append(Util.completaString(parmsEndereco[6], 30));
				// D1.12 - Bairro
				registroDetalheConsumidor.append(Util.completaString(parmsEndereco[3], 25));
				// D1.13 - CEP
				registroDetalheConsumidor.append(Util.completaString(parmsEndereco[4], 8));
				// D1.14 - Munic�pio
				registroDetalheConsumidor.append(Util.completaString(parmsEndereco[1], 30));
				// D1.15 - Unidade da Federa��o
				registroDetalheConsumidor.append(Util.completaString(parmsEndereco[2], 2));
			}
			
			IClienteFone cliFone = (IClienteFone) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa.getDddFone(cliente.getId()));
			
			// D1.16 - DDD
			if (cliFone != null) {
				registroDetalheConsumidor.append(Util.completaString(cliFone.getDdd(), 2));
			} else {
				registroDetalheConsumidor.append("  ");
			}
			
			// D1.17 - Preencher com espa�os em branco
			registroDetalheConsumidor.append(Util.completaString(" ", 20));
			
			// D1.18 - Fone
			if (cliFone != null && cliFone.getTelefone().length() >= 8) {
				registroDetalheConsumidor.append(Util.completaString(cliFone.getTelefone().substring(0, 8), 8));
			} else {
				registroDetalheConsumidor.append("        ");
			}
			
			// D1.19 - Preencher com espa�os em branco
			registroDetalheConsumidor.append(Util.completaString(" ", 10));
			
			// D1.20 - N�mero do registro
			registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(6, "" + quantidadeRegistros));
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}

		return registroDetalheConsumidor;
	}

	// GERA REGISTRO DETALHE **********************
	// ********************************************
	// CAMPOS CONFORME LAYOUT DO UC0671 [SB0009] **
	// ********************************************
	public StringBuilder geraRegistroTipoDetalheSPC(int quantidadeRegistros,
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel, NegativacaoCriterio negCriterio,
			Imovel imovelNegativado, BigDecimal valorTotalDebitosImovel,
			DadosNegativacaoPorImovelHelper listaDadosImovel, Short tipoComando) throws ControladorException,
			ErroRepositorioException {
		StringBuilder registroDetalheSPC = new StringBuilder();

		// //////////////////////////////////DETALHE SPC
		// D2.01
		registroDetalheSPC.append("02");
		// D2.02 - Espa�os em Branco
		// registroDetalheSPC.append(Util.completaString(" ", 45));
		// D2.03 - Indicador se tem ou n�o CNPJ ou CPF
		// Preenchido

		if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
			if (listaDadosImovel.getCnpjCliente() != null && listaDadosImovel.getCnpjCliente().length() > 0) {
				registroDetalheSPC.append("1");
			} else {
				registroDetalheSPC.append("2");
			}
			registroDetalheSPC.append(Util.adicionarZerosEsquedaNumero(15, listaDadosImovel.getCnpjCliente()));

			registroDetalheSPC.append("I");

			registroDetalheSPC.append("C");
			// Maior data
			Date maiorData = this.obterMaiorVencimento(listaDadosImovel.getColecaoConta(),
					listaDadosImovel.getColecaoGuias());

			String D206 = Util.recuperaDataInvertida(maiorData);
			registroDetalheSPC.append(D206);
			// Menor data
			Date menorData = this.obterMenorVencimento(listaDadosImovel.getColecaoConta(),
					listaDadosImovel.getColecaoGuias());
			String D207 = Util.recuperaDataInvertida(menorData);
			registroDetalheSPC.append(D207);
		} else if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO)) {
			if (negCriterio.getCliente().getCnpj() != null && negCriterio.getCliente().getCnpj().length() > 0) {
				registroDetalheSPC.append("1");
			} else {
				registroDetalheSPC.append("2");
			}
			registroDetalheSPC.append(Util.adicionarZerosEsquedaNumero(15, negCriterio.getCliente().getCnpj()));

			registroDetalheSPC.append("I");

			registroDetalheSPC.append("C");
			// Maior data
			Date maiorData = this.obterMaiorVencimento((List) colecaoDebitoImovel.getColecaoContasValores(),
					(List) colecaoDebitoImovel.getColecaoGuiasPagamentoValores());

			String D206 = Util.recuperaDataInvertida(maiorData);
			registroDetalheSPC.append(D206);
			// Menor data
			Date menorData = this.obterMenorVencimento((List) colecaoDebitoImovel.getColecaoContasValores(),
					(List) colecaoDebitoImovel.getColecaoGuiasPagamentoValores());
			String D207 = Util.recuperaDataInvertida(menorData);
			registroDetalheSPC.append(D207);
		}

		// Valor total debito imovel
		registroDetalheSPC
				.append(Util.adicionarZerosEsquedaNumero(13, (valorTotalDebitosImovel + "").replace(".", "")));
		// imov_id
		registroDetalheSPC.append(Util.adicionarZerosEsquedaNumero(30, "" + imovelNegativado.getId()));
		// D210 - Preencher com espa�os em branco
		registroDetalheSPC.append(Util.adicionarZerosEsquedaNumero(7, " "));
		// D211 - 00
		registroDetalheSPC.append("00");
		// D212 - Preencher com espa�os em branco - 03 posicoes
		registroDetalheSPC.append("   ");
		// D213 - Preencher com espa�os em branco - 232 posicoes
		registroDetalheSPC.append(Util.adicionarZerosEsquedaNumero(231, " "));
		// D214 - Preencher com espa�os em branco - 10 posicoes
		registroDetalheSPC.append("          ");
		// D215 - numero do registro
		registroDetalheSPC.append(Util.adicionarZerosEsquedaNumero(6, "" + quantidadeRegistros));

		return registroDetalheSPC;
	}

	/**
	 * M�todo que Exclui a negativacao de um imovel [UC0675] Excluir Negativa��o
	 * InLine [Fluxo principal] 8.0
	 * 
	 * @author Thiago Toscano
	 * @date 24/01/2008
	 *
	 * @throws ControladorException
	 */
	public void excluirNegativacaoOnLine(Imovel imovel, NegativadorMovimentoReg negativadorMovimentoReg,
			Collection itensConta, Collection itensGuiaPagamento, NegativadorExclusaoMotivo negativadorExclusaoMotivo,
			Date dataExclusao, Usuario usuarioSelecionado, Usuario usuarioLogado) throws ControladorException {
		try {

			// ------------ REGISTRAR TRANSA��O ----------------
			// RegistradorOperacao registradorOperacao = new
			// RegistradorOperacao(
			// Operacao.EXCLUIR_NEGATIVACAO_ON_LINE,
			// new UsuarioAcaoUsuarioHelper(usuarioLogado,
			// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			//
			// Operacao operacao = new Operacao();
			// operacao.setId(Operacao.EXCLUIR_NEGATIVACAO_ON_LINE);
			//
			// OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			// operacaoEfetuada.setOperacao(operacao);
			// ------------ REGISTRAR TRANSA��O ----------------

			// ------------------------------------------------------------------
			// Alterado por: Yara Taciane
			// Data : 22/07/2008
			// Solicita��o : F�tima Sampio
			// ------------------------------------------------------------------

			// 6.1
			NegativadorExclusaoMotivo negatExclusaoMotivo = repositorioSpcSerasa
					.pesquisarMotivoExclusao(negativadorExclusaoMotivo.getId());

			// Caso a situa��o de cobran�a do d�bito associada ao motivo da
			// exclus�o selecionado
			// corresponda a "Pago" ou "Parcelado", verificar a situa��o do
			// d�bito do Item.
			if (negatExclusaoMotivo != null
					&& negatExclusaoMotivo.getCobrancaDebitoSituacao() != null
					&& (negatExclusaoMotivo.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.PAGO) || negatExclusaoMotivo
							.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.PARCELADO))) {

				Object[] obj = new Object[9];
				Integer quantidadeItensNegativacao = 0;
				Integer quantidadeItensNegativacaoPendente = 0;
				Integer quantidadeItensNegativacaoPago = 0;
				Integer quantidadeItensNegativacaoParcelado = 0;
				Integer quantidadeItensNegativacaoCancelado = 0;

				BigDecimal valorItensNegativacaoPendente = new BigDecimal(0);
				BigDecimal valorItensNegativacaoPago = new BigDecimal(0);
				BigDecimal valorItensNegativacaoParcelado = new BigDecimal(0);
				BigDecimal valorItensNegativacaoCancelado = new BigDecimal(0);

				obj[0] = quantidadeItensNegativacao;
				obj[1] = quantidadeItensNegativacaoPendente;
				obj[2] = quantidadeItensNegativacaoPago;
				obj[3] = quantidadeItensNegativacaoParcelado;
				obj[4] = quantidadeItensNegativacaoCancelado;
				obj[5] = valorItensNegativacaoPendente;
				obj[6] = valorItensNegativacaoPago;
				obj[7] = valorItensNegativacaoParcelado;
				obj[8] = valorItensNegativacaoCancelado;

				// [SB001] - Verificar Situa��o D�bito dos Itens da Negativa��o.
				Collection collNegativadorMovimentoRegItem = verificarSituacaoDebitoItensNegativacao(
						negativadorMovimentoReg, obj);

				quantidadeItensNegativacao = (Integer) obj[0];
				quantidadeItensNegativacaoPendente = (Integer) obj[1];
				quantidadeItensNegativacaoPago = (Integer) obj[2];
				quantidadeItensNegativacaoParcelado = (Integer) obj[3];
				quantidadeItensNegativacaoCancelado = (Integer) obj[4];
				valorItensNegativacaoPendente = (BigDecimal) obj[5];
				valorItensNegativacaoPago = (BigDecimal) obj[6];
				valorItensNegativacaoParcelado = (BigDecimal) obj[7];
				valorItensNegativacaoCancelado = (BigDecimal) obj[8];

				// 1.4 Situa��o Predominante do D�bito da Negativa��o.
				CobrancaDebitoSituacao cds = negativadorMovimentoReg.getCobrancaDebitoSituacao();

				// 1.5 Caso a situa��o da negativacao n�o seja definitiva.
				if (negativadorMovimentoReg.getIndicadorSituacaoDefinitiva() == 2) {

					// [SB0003] - Determinar Situa��o Predominante do D�bito da
					// Negativa��o.
					Object[] rspdn = determinarSituacaoPredominanteDebitoNegativacao(negativadorMovimentoReg,
							collNegativadorMovimentoRegItem, obj);
					// Situa��o Predominante do D�bito da Negativa��o.
					cds = (CobrancaDebitoSituacao) rspdn[0];
				}

				// 1.6 Caso a situa��o de cobran�a do d�bito associado ao motivo
				// da exclus�o selecionado
				// corresponda a "Pago" e a Situa��o Predominante do D�bito da
				// Negativa��o n�o corresponda a
				// d�bito "Pago".
				if (negatExclusaoMotivo.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.PAGO)) {
					if (!cds.getId().equals(CobrancaDebitoSituacao.PAGO)) {
						// 1.6.1 , 1.6.2
						throw new ControladorException("atencao.debito_negativado_nao_pago", null);
					}
				}

				// 1.7 Caso a situa��o de cobran�a do d�bito associado ao motivo
				// da exclus�o selecionado
				// corresponda a "Parcelado" e a Situa��o Predominante do D�bito
				// da Negativa��o n�o corresponda a
				// d�bito "Parcelado".
				if (negatExclusaoMotivo.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.PARCELADO)) {
					if (!cds.getId().equals(CobrancaDebitoSituacao.PARCELADO)) {
						// 1.7.1 , 1.7.2
						throw new ControladorException("atencao.debito_negativado_nao_parcelado", null);
					}
				}

			}

			// -------------------------------------------------------------------

			// 8.1
			FiltroNegativacaoImoveis fni = new FiltroNegativacaoImoveis();
			fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID, imovel.getId()));
			fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID,
					negativadorMovimentoReg.getNegativadorMovimento().getNegativacaoComando().getId()));

			// ------------ REGISTRAR TRANSA��O ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_EXCLUIR_NEGATIVACAO_ONLINE, negativadorMovimentoReg.getId(),
					negativadorMovimentoReg.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSA��O ----------------

			Collection collNegativacaoImoveis = RepositorioUtilHBM.getInstancia().pesquisar(fni,
					NegativacaoImoveis.class.getName());
			if (collNegativacaoImoveis != null) {
				Iterator it = collNegativacaoImoveis.iterator();
				if (it.hasNext()) {
					NegativacaoImoveis ni = (NegativacaoImoveis) it.next();
					ni.setIndicadorExcluido((short) 1);
					ni.setDataExclusao(dataExclusao);
					ni.setUltimaAlteracao(new Date());
					// // ------------ REGISTRAR TRANSA��O ----------------
					// registradorOperacao.registrarOperacao(ni);
					// // ------------ REGISTRAR TRANSA��O ----------------

					RepositorioUtilHBM.getInstancia().atualizar(ni);
				}
			}

			// consultando o NegativadorExclusaoMotivo selecionado pelo usuario
			// para carregar
			// o CobrancaDebitoSituacao
			FiltroNegativadorExclusaoMotivo fnem = new FiltroNegativadorExclusaoMotivo();
			fnem.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID, negativadorExclusaoMotivo
					.getId()));
			fnem.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorExclusaoMotivo.COBRANCA_DEBITO_SITUACAO);
			Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(fnem,
					NegativadorExclusaoMotivo.class.getName());
			NegativadorExclusaoMotivo nEMCadastrado = (NegativadorExclusaoMotivo) Util.retonarObjetoDeColecao(coll);

			// 8.2
			negativadorMovimentoReg.setUsuario(usuarioSelecionado);
			negativadorMovimentoReg.setCobrancaDebitoSituacao(nEMCadastrado.getCobrancaDebitoSituacao());
			negativadorMovimentoReg.setDataSituacaoDebito(dataExclusao);
			negativadorMovimentoReg.setCodigoExclusaoTipo(new Integer(2));
			negativadorMovimentoReg.setIndicadorSituacaoDefinitiva((short) 1);
			negativadorMovimentoReg.setNegativadorExclusaoMotivo(negativadorExclusaoMotivo);
			negativadorMovimentoReg.setUltimaAlteracao(new Date());
			// adicionado por Vivianne Sousa - 21/08/2009 - analista:Fatima
			negativadorMovimentoReg.setCobrancaSituacao(null);

			// ------------ REGISTRAR TRANSA��O----------------------------
			registradorOperacao.registrarOperacao(negativadorMovimentoReg);
			// ------------ REGISTRAR TRANSA��O----------------------------

			RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);

			// 8.3
			if (itensConta != null) {
				Iterator it = itensConta.iterator();
				while (it.hasNext()) {
					NegativadorMovimentoRegItem nmri = (NegativadorMovimentoRegItem) it.next();
					nmri.setCobrancaDebitoSituacaoAposExclusao(nmri.getCobrancaDebitoSituacao());
					nmri.setDataSituacaoDebitoAposExclusao(nmri.getDataSituacaoDebito());
					nmri.setUltimaAlteracao(new Date());
					// // ------------ REGISTRAR TRANSA��O ----------------
					// registradorOperacao.registrarOperacao(nmri);
					// // ------------ REGISTRAR TRANSA��O ----------------
					RepositorioUtilHBM.getInstancia().atualizar(nmri);
				}
			}
			// 8.3
			if (itensGuiaPagamento != null) {
				Iterator it = itensGuiaPagamento.iterator();
				while (it.hasNext()) {
					NegativadorMovimentoRegItem nmri = (NegativadorMovimentoRegItem) it.next();
					nmri.setCobrancaDebitoSituacaoAposExclusao(nmri.getCobrancaDebitoSituacao());
					nmri.setDataSituacaoDebitoAposExclusao(nmri.getDataSituacaoDebito());
					nmri.setUltimaAlteracao(new Date());
					// // ------------ REGISTRAR TRANSA��O ----------------
					// registradorOperacao.registrarOperacao(nmri);
					// // ------------ REGISTRAR TRANSA��O ----------------
					RepositorioUtilHBM.getInstancia().atualizar(nmri);
				}
			}

			// -----------------------------------------------------------------------------------------------------------------
			// Alterado em 27/03/2008

			// 8.5
			// FiltroImovelCobrancaSituacao fics = new
			// FiltroImovelCobrancaSituacao();
			// fics.adicionarParametro(new
			// ParametroSimples(FiltroImovelCobrancaSituacao.IMOVEL_ID,imovel.getId()));
			// fics.adicionarParametro(new
			// ParametroNulo("dataRetiradaCobranca"));
			//
			// if
			// (negativadorMovimentoReg.getNegativadorMovimento().getNegativador().getId().equals(Negativador.NEGATIVADOR_SPC))
			// {
			// fics.adicionarParametro(new
			// ParametroSimples("cobrancaSituacao.id",CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO,ConectorOr.CONECTOR_OR,3));
			// fics.adicionarParametro(new
			// ParametroSimples("cobrancaSituacao.id",CobrancaSituacao.CARTA_ENVIADA_AO_SPC,ConectorOr.CONECTOR_OR,3));
			// fics.adicionarParametro(new
			// ParametroSimples("cobrancaSituacao.id",CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NO_SPC,ConectorOr.CONECTOR_OR,3));
			// } else
			// if(negativadorMovimentoReg.getNegativadorMovimento().getNegativador().getId().equals(Negativador.NEGATIVADOR_SERASA)){
			// fics.adicionarParametro(new
			// ParametroSimples("cobrancaSituacao.id",CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO,ConectorOr.CONECTOR_OR,3));
			// fics.adicionarParametro(new
			// ParametroSimples("cobrancaSituacao.id",CobrancaSituacao.CARTA_ENVIADA_A_SERASA,ConectorOr.CONECTOR_OR,3));
			// fics.adicionarParametro(new
			// ParametroSimples("cobrancaSituacao.id",CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NA_SERASA,ConectorOr.CONECTOR_OR,3));
			// }
			//
			// Collection collImovelCobrancaSituacao =
			// RepositorioUtilHBM.getInstancia().pesquisar(fics,
			// ImovelCobrancaSituacao.class.getName());
			//
			//
			//
			// if (collImovelCobrancaSituacao != null) {
			// Iterator it = collImovelCobrancaSituacao.iterator();
			// while(it.hasNext()) {
			// ImovelCobrancaSituacao ics = (ImovelCobrancaSituacao) it.next();
			// ics.setDataRetiradaCobranca(new Date());
			// ics.setUltimaAlteracao(new Date());
			// // ------------ REGISTRAR TRANSA��O ----------------
			// registradorOperacao.registrarOperacao(ics);
			// // ------------ REGISTRAR TRANSA��O ----------------
			// RepositorioUtilHBM.getInstancia().atualizar(ics);
			// }
			// }

			// 8.5
			Collection imoveisCobrancaSituacao = this.repositorioSpcSerasa
					.consultarImovelCobrancaSituacaoPorNegativador(imovel, negativadorMovimentoReg
							.getNegativadorMovimento().getNegativador().getId());

			if (imoveisCobrancaSituacao != null) {

				Iterator iter = imoveisCobrancaSituacao.iterator();

				while (iter.hasNext()) {
					ImovelCobrancaSituacao imovelCobrancaSituacao = (ImovelCobrancaSituacao) iter.next();
					imovelCobrancaSituacao.setDataRetiradaCobranca(new Date());
					imovelCobrancaSituacao.setUltimaAlteracao(new Date());

					// ------------ REGISTRAR TRANSA��O ----------------
					// registradorOperacao.registrarOperacao(imovelCobrancaSituacao);
					// ------------ REGISTRAR TRANSA��O ----------------

					RepositorioUtilHBM.getInstancia().atualizar(imovelCobrancaSituacao);
				}

			}

			// CRC3323 - comentado por Vivianne Sousa - analista:Fatima Sampaio
			// - 10/05/2010
			// 8.6
			// if(imovel.getCobrancaSituacao()!= null){
			//
			// if (
			// imovel.getCobrancaSituacao().getId().equals(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO)||
			// imovel.getCobrancaSituacao().getId().equals(CobrancaSituacao.CARTA_ENVIADA_AO_SPC)
			// ||
			// imovel.getCobrancaSituacao().getId().equals(CobrancaSituacao.CARTA_ENVIADA_A_SERASA)
			// ||
			// imovel.getCobrancaSituacao().getId().equals(CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NO_SPC)
			// ||
			// imovel.getCobrancaSituacao().getId().equals(CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NA_SERASA)
			// ) {
			//
			// fni = new FiltroNegativacaoImoveis();
			// fni.adicionarParametro(new
			// ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID,
			// imovel.getId()));
			// fni.adicionarParametro(new
			// ParametroSimplesDiferenteDe(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID,
			// negativadorMovimentoReg.getNegativadorMovimento().getNegativacaoComando().getId()));
			// fni.adicionarParametro(new
			// ParametroSimples(FiltroNegativacaoImoveis.INDICADOR_EXCLUIDO,
			// "2"));
			//
			// //8.6.1
			// collNegativacaoImoveis =
			// RepositorioUtilHBM.getInstancia().pesquisar(fni,
			// NegativacaoImoveis.class.getName());
			// if (collNegativacaoImoveis.isEmpty()) {
			// imovel.setCobrancaSituacao(null);
			// imovel.setUltimaAlteracao(new Date());
			// // ------------ REGISTRAR TRANSA��O ----------------
			// registradorOperacao.registrarOperacao(imovel);
			// // ------------ REGISTRAR TRANSA��O ----------------
			// RepositorioUtilHBM.getInstancia().atualizar(imovel);
			// }
			// }
			//
			//
			// }

			// -----------------------------------------------------------------------------------------------------------------
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc
	 * ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o [SB0003] -
	 * Selecionar Negativadores
	 * 
	 * @author Thiago Toscano
	 * @date 21/12/2007
	 * 
	 */
	public Collection consultarNegativadoresParaExclusaoMovimento() throws ControladorException {

		Collection coll = new ArrayList();

		try {

			coll = repositorioSpcSerasa.consultarNegativadoresParaExclusaoMovimento();

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return coll;
	}

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc
	 * ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o [SB0001] - Gerar
	 * Movimento da Exclus�o de Negativa��o
	 * 
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 * 
	 */
	public Collection gerarMovimentoExclusaoNegativacao(Integer[] idNegativador) throws ControladorException {

		System.out.println(".................entrou em gerarMovimentoExclusaoNegativacao...............");

		// todas as movimentacoes
		Collection coll = new ArrayList();

		try {
			// consultar as negativacoes para excluir
			Collection collNegativadoresParaExclusao = repositorioSpcSerasa
					.consultarNegativacoesParaExclusaoMovimento(idNegativador);

			// gerando um map dos navegadores com os NegativadorMovimentoReg
			Map mapNegativadorCOMNegativadorMovimentoReg = new HashMap();
			Iterator it = collNegativadoresParaExclusao.iterator();
			while (it.hasNext()) {
				NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) it.next();

				if (mapNegativadorCOMNegativadorMovimentoReg.containsKey(negativadorMovimentoReg
						.getNegativadorMovimento().getNegativador())) {
					ArrayList al = (ArrayList) mapNegativadorCOMNegativadorMovimentoReg.get(negativadorMovimentoReg
							.getNegativadorMovimento().getNegativador());
					al.add(negativadorMovimentoReg);
				} else {
					ArrayList al = new ArrayList();
					al.add(negativadorMovimentoReg);
					mapNegativadorCOMNegativadorMovimentoReg.put(negativadorMovimentoReg.getNegativadorMovimento()
							.getNegativador(), al);
				}
			}

			// 7.0
			it = mapNegativadorCOMNegativadorMovimentoReg.keySet().iterator();
			// Map mapNegativadorNegativadorMovimento = new HashMap();
			while (it.hasNext()) {
				Negativador n = (Negativador) it.next();

				Collection collv = (ArrayList) mapNegativadorCOMNegativadorMovimentoReg.get(n);
				NegativadorContrato nc = repositorioSpcSerasa.consultarNegativadorContratoVigente(n.getId());

				// NegativacaoComando ncom = new NegativacaoComando();
				// ncom.setId(1);

				NegativadorMovimento nm = new NegativadorMovimento();
				nm.setCodigoMovimento(new Short("2"));
				nm.setDataEnvio(new Date());
				nm.setDataProcessamentoEnvio(new Date());
				nm.setNumeroSequencialEnvio(nc.getNumeroSequencialEnvio() + 1);
				nm.setUltimaAlteracao(new Date());
				nm.setNegativador(n);
				// nm.setNegativacaoComando(ncom);
				//
				// OperacaoEfetuada operacaoEfetuada, Usuario usuarioLogado,
				// RegistradorOperacao registradorOperacao

				RepositorioUtilHBM.getInstancia().inserir(nm);

				// Object[0] Integer numeroRegistro = new Integer(0);
				// Object[1] BigDecimal valor = new BigDecimal(0);
				// Object[2] Integer quantidadeExclusao = new Integer(0);

				Object[] header = this.gerarRegistroTipoHeader(new Integer(0), new BigDecimal(0), new Integer(0), n,
						nm, nc, collv);
				header = this.gerarRegistroTipoDetalhe((Integer) header[0], (BigDecimal) header[1],
						(Integer) header[2], n, nm, nc, collv);
				header = this.gerarRegistroTipoTrailler((Integer) header[0], (BigDecimal) header[1],
						(Integer) header[2], n, nm, nc, collv);

				// NegativadorMovimentoGeradosExclusaoHelper helper = new
				// NegativadorMovimentoGeradosExclusaoHelper();
				// helper.setDescricaoNegativador(n.getCliente().getNome());
				// helper.setDataProcessamento(nm.getUltimaAlteracao());
				// helper.setHoraProcessamento(nm.getUltimaAlteracao());
				// helper.setNsa(nm.getNumeroSequencialEnvio() + "");
				// helper.setQuantidadeRegistros((Integer)header[0] + "");
				// helper.setValorDebito( gcom.util.Util.formataBigDecimal(
				// (BigDecimal)header[1],2,true) + "");
				// coll.add(helper);

				this.gerarArquivo(nm.getId(), false, nm.getNegativador().getId());

				Integer numeroExclusoesJaEnviadas = nc.getNumeroExclusoesEnviadas();
				if (numeroExclusoesJaEnviadas == null) {
					numeroExclusoesJaEnviadas = 0;
				}

				nc.setNumeroSequencialEnvio(nm.getNumeroSequencialEnvio());
				nc.setNumeroExclusoesEnviadas(numeroExclusoesJaEnviadas + ((Integer) header[2]));
				nc.setUltimaAlteracao(new Date());

				RepositorioUtilHBM.getInstancia().atualizar(nc);

				nm.setNumeroRegistrosEnvio((Integer) header[0]);
				nm.setValorTotalEnvio((BigDecimal) header[1]);
				nm.setUltimaAlteracao(new Date());

				RepositorioUtilHBM.getInstancia().atualizar(nm);

				// coll.add(nm);
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return coll;
	}

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o BATCH
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o [SB0001] - Gerar
	 * Movimento da Exclus�o de Negativa��o
	 * 
	 * @author Yara T. Souza
	 * @date 26/12/2007
	 * 
	 */
	public Collection gerarMovimentoExclusaoNegativacao(Integer idFuncionalidadeIniciada, Integer[] idNegativador)
			throws ControladorException {

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.FUNCIONALIDADE, 0);

		// todas as movimentacoes
		Collection coll = new ArrayList();

		try {
			// adicionado por Vivianne Sousa - 11/02/2010
			// Desfaz movimento de inclus�o incompleto, no caso de reiniciar
			// batch.
			this.desfazerMovimentoExclusaoIncompleto();

			// consultar as negativacoes para excluir
			Collection collNegativadoresParaExclusao = repositorioSpcSerasa
					.consultarNegativacoesParaExclusaoMovimento(idNegativador);

			// gerando um map dos navegadores com os NegativadorMovimentoReg
			Map mapNegativadorCOMNegativadorMovimentoReg = new HashMap();
			Iterator it = collNegativadoresParaExclusao.iterator();
			while (it.hasNext()) {
				NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) it.next();

				if (mapNegativadorCOMNegativadorMovimentoReg.containsKey(negativadorMovimentoReg
						.getNegativadorMovimento().getNegativador())) {
					ArrayList al = (ArrayList) mapNegativadorCOMNegativadorMovimentoReg.get(negativadorMovimentoReg
							.getNegativadorMovimento().getNegativador());
					al.add(negativadorMovimentoReg);
				} else {
					ArrayList al = new ArrayList();
					al.add(negativadorMovimentoReg);
					mapNegativadorCOMNegativadorMovimentoReg.put(negativadorMovimentoReg.getNegativadorMovimento()
							.getNegativador(), al);
				}
			}

			// 7.0
			it = mapNegativadorCOMNegativadorMovimentoReg.keySet().iterator();

			while (it.hasNext()) {
				Negativador n = (Negativador) it.next();

				Collection collv = (ArrayList) mapNegativadorCOMNegativadorMovimentoReg.get(n);
				NegativadorContrato nc = repositorioSpcSerasa.consultarNegativadorContratoVigente(n.getId());

				NegativadorMovimento nm = new NegativadorMovimento();
				nm.setCodigoMovimento(new Short("2"));
				nm.setDataEnvio(new Date());
				nm.setDataProcessamentoEnvio(new Date());
				nm.setNumeroSequencialEnvio(nc.getNumeroSequencialEnvio() + 1);
				nm.setUltimaAlteracao(new Date());
				nm.setNegativador(n);

				this.getControladorBatch().inserirObjetoParaBatch(nm);

				Object[] header = this.gerarRegistroTipoHeader(new Integer(0), new BigDecimal(0), new Integer(0), n,
						nm, nc, collv);
				header = this.gerarRegistroTipoDetalhe((Integer) header[0], (BigDecimal) header[1],
						(Integer) header[2], n, nm, nc, collv);
				header = this.gerarRegistroTipoTrailler((Integer) header[0], (BigDecimal) header[1],
						(Integer) header[2], n, nm, nc, collv);

				this.gerarArquivo(nm.getId(), false, nm.getNegativador().getId());

				Integer numeroExclusoesJaEnviadas = nc.getNumeroExclusoesEnviadas();
				if (numeroExclusoesJaEnviadas == null) {
					numeroExclusoesJaEnviadas = 0;
				}

				nc.setNumeroSequencialEnvio(nm.getNumeroSequencialEnvio());
				nc.setNumeroExclusoesEnviadas(numeroExclusoesJaEnviadas + ((Integer) header[2]));
				nc.setUltimaAlteracao(new Date());

				this.getControladorBatch().atualizarObjetoParaBatch(nc);

				nm.setNumeroRegistrosEnvio((Integer) header[0]);
				nm.setValorTotalEnvio((BigDecimal) header[1]);
				nm.setUltimaAlteracao(new Date());

				this.getControladorBatch().atualizarObjetoParaBatch(nm);

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		return coll;
	}

	/**
	 * M�todo que gera o Header a ser enviado [UC0673] - Gerar Movimento de
	 * Exclusao de Negativacao [SB0004] - Gerar Registro Tipo Header
	 *
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 *
	 * @param n
	 *            - negativador
	 * @param nm
	 *            -negativaromovimento
	 * @param nc
	 *            - negativadorContrato
	 * @param collNegativadorMovimentoReg
	 * @return Object[] Object[0] Integer numeroRegistro = new Integer(0);
	 *         Object[1] BigDecimal valor = new BigDecimal(0); Object[2] Integer
	 *         quantidadeExclusao = new Integer(0);
	 * @throws ErroRepositorioException
	 * 
	 */
	private Object[] gerarRegistroTipoHeader(Integer numeroRegistro, BigDecimal valor, Integer quantidadeExclusao,
			Negativador n, NegativadorMovimento nm, NegativadorContrato nc, Collection collNegativadorMovimentoReg)
			throws ControladorException, ErroRepositorioException {
		Object[] retorno = new Object[3];
		retorno[0] = numeroRegistro + 1;
		retorno[1] = valor;
		retorno[2] = quantidadeExclusao;

		char[] registro = new char[340];
		for (int i = 0; i < registro.length; i++) {
			registro[i] = ' ';
		}

		String ddmmaaaa = Util.formatarDataSemBarraDDMMAAAA(new Date());
		String aaaammdd = Util.formatarDataSemBarra(new Date());

		if (n.getId().equals(Negativador.NEGATIVADOR_SPC)) {
			String numeroContrato = (nc.getNumeroSequencialEnvio() + 1) + "";
			if (numeroContrato.length() < 8) {
				while (numeroContrato.length() != 8) {
					numeroContrato = "0" + numeroContrato;
				}
			}

			String tamanho271 = "";
			while (tamanho271.length() != 271) {
				tamanho271 = " " + tamanho271;
			}

			// h.01
			colocarConteudo("00", 1, registro);
			// h.02
			colocarConteudo("REMESSA", 3, registro);
			// h.03
			colocarConteudo(ddmmaaaa, 10, registro);
			// h.04
			colocarConteudo(numeroContrato, 18, registro);
			// h.05
			colocarConteudo("01101", 26, registro);
			// h.06
			colocarConteudo("00102779", 31, registro);
			// h.07
			colocarConteudo(aaaammdd, 39, registro);
			// h.08
			colocarConteudo(tamanho271, 47, registro);
			// h.09
			colocarConteudo("SPC  ", 318, registro);
			// h.10
			colocarConteudo("07", 323, registro);
			// h.011
			colocarConteudo("          ", 325, registro);
			// h.012
			colocarConteudo("000001", 335, registro);

		} else if (n.getId().equals(Negativador.NEGATIVADOR_SERASA)) {
			registro = new char[600];
			for (int i = 0; i < registro.length; i++) {
				registro[i] = ' ';
			}

			String numeroContrato = (nc.getNumeroSequencialEnvio() + 1) + "";
			if (numeroContrato.length() < 6) {
				while (numeroContrato.length() != 6) {
					numeroContrato = "0" + numeroContrato;
				}
			}

			// h.01
			colocarConteudo("0", 1, registro);
			// h.02 PARAM_NNCNPJEMPRESA(Sistema Parametro)
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			String cnpjEmpresa = sistemaParametro.getCnpjEmpresa();
			cnpjEmpresa = cnpjEmpresa.substring(0, 8);
			// registroHeader.append(Util.adicionarZerosEsquedaNumero(9,
			// cnpjEmpresa));
			colocarConteudo(Util.adicionarZerosEsquedaNumero(9, cnpjEmpresa), 2, registro);
			// h.03
			colocarConteudo(aaaammdd, 11, registro);
			// h.04
			colocarConteudo("0081", 19, registro);
			// h.05
			colocarConteudo("34129707", 23, registro);
			// h.06
			colocarConteudo("0000", 31, registro);
			// h.07
			colocarConteudo("FATIMA SAMPAIO", 35, registro);
			// h.08
			colocarConteudo("SERASA-CONVEM04", 105, registro);
			// h.09
			colocarConteudo(numeroContrato, 120, registro);
			// h.10
			colocarConteudo("E", 126, registro);
			// h.11
			// colocarConteudo("    ", 127, registro);
			// h.12
			// colocarConteudo("    ", 127, registro);
			// h.13
			// colocarConteudo("    ", 127, registro);
			// h.14
			colocarConteudo("0000001", 594, registro);

		}

		FiltroNegativadorRegistroTipo fnrt = new FiltroNegativadorRegistroTipo();
		fnrt.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.NEGATIVADOR_ID, n.getId()));
		fnrt.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.CODIGO_REGISTRO,
				NegativadorRegistroTipo.TIPO_HEADER));

		NegativadorRegistroTipo nrt = (NegativadorRegistroTipo) Util.retonarObjetoDeColecao(RepositorioUtilHBM
				.getInstancia().pesquisar(fnrt, NegativadorRegistroTipo.class.getName()));

		NegativadorMovimentoReg nmr = new NegativadorMovimentoReg();
		nmr.setNegativadorMovimento(nm);
		nmr.setNegativadorRegistroTipo(nrt);
		nmr.setConteudoRegistro(new String(registro));
		nmr.setIndicadorSituacaoDefinitiva(new Short((short) 1));
		nmr.setNumeroRegistro(new Integer(1));
		nmr.setUltimaAlteracao(new Date());
		nmr.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);
		RepositorioUtilHBM.getInstancia().inserir(nmr);

		return retorno;
	}

	private String getConteudo(int posicaoInicial, int tamanho, char[] registro) {
		String retorno = "";
		while (retorno.length() != tamanho) {
			retorno = retorno + registro[(retorno.length() + posicaoInicial) - 1];
		}
		return retorno;
	}

	private void colocarConteudo(String conteudo, int posicaoInicial, char[] registro) {
		if (registro.length >= ((posicaoInicial + conteudo.length()) - 1)) {
			for (int i = 0; i < conteudo.length(); i++) {
				registro[posicaoInicial + i - 1] = conteudo.charAt(i);
			}
		}
	}

	/**
	 * M�todo que gera o Detalhe a ser enviado [UC0673] - Gerar Movimento de
	 * Exclusao de Negativacao [SB0005] - Gerar Registro Tipo Detalhe
	 *
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 *
	 * @param n
	 *            - negativador
	 * @param nm
	 *            -negativaromovimento
	 * @param nc
	 *            - negativadorContrato
	 * @param collNegativadorMovimentoReg
	 *            Object[0] Integer numeroRegistro = new Integer(0); Object[1]
	 *            BigDecimal valor = new BigDecimal(0); Object[2] Integer
	 *            quantidadeExclusao = new Integer(0);
	 * @throws ErroRepositorioException
	 * 
	 */
	private Object[] gerarRegistroTipoDetalhe(Integer numeroRegistro, BigDecimal valor, Integer quantidadeExclusao,
			Negativador n, NegativadorMovimento nm, NegativadorContrato nc, Collection collNegativadorMovimentoReg)
			throws ControladorException {

		try {

			Object[] retorno = new Object[3];
			retorno[0] = numeroRegistro;
			retorno[1] = valor;
			retorno[2] = quantidadeExclusao;

			if (collNegativadorMovimentoReg != null && !collNegativadorMovimentoReg.isEmpty()) {
				Iterator it = collNegativadorMovimentoReg.iterator();
				while (it.hasNext()) {
					NegativadorMovimentoReg nmr = (NegativadorMovimentoReg) it.next();

					if (n.getId().equals(Negativador.NEGATIVADOR_SPC)) {

						retorno[0] = new Integer((Integer) retorno[0]) + 1;

						// geradno 6 digitos do numero de registro
						String sNumeroRegistro = ((Integer) retorno[0]).toString();
						if (sNumeroRegistro.length() < 6) {
							while (sNumeroRegistro.length() != 6) {
								sNumeroRegistro = "0" + sNumeroRegistro;
							}
						}

						if (nmr.getNegativadorRegistroTipo().getId().equals(NegativadorRegistroTipo.ID_SPC_DETALHE_SPC)) {

							// Gerar Registro de Exclus�o Tipo
							// Detalhe-Consumidor
							this.gerarNegMovRegDetalheConsumidor(nmr, nm, sNumeroRegistro);

							retorno[0] = new Integer((Integer) retorno[0]) + 1;

							// geradno 6 digitos do numero de registro
							sNumeroRegistro = ((Integer) retorno[0]).toString();
							if (sNumeroRegistro.length() < 6) {
								while (sNumeroRegistro.length() != 6) {
									sNumeroRegistro = "0" + sNumeroRegistro;
								}
							}

							NegativadorExclusaoMotivo nem = obterNegativadorExclusaoMotivo(nmr, n);

							char[] registro = new char[340];
							for (int i = 0; i < registro.length; i++) {
								registro[i] = ' ';
							}

							char[] registroInclusao = new char[340];
							for (int i = 0; i < registroInclusao.length; i++) {
								registroInclusao[i] = ' ';
							}
							if (nmr.getConteudoRegistro() != null) {
								for (int i = 0; i < nmr.getConteudoRegistro().length(); i++) {
									if (i <= registroInclusao.length) {
										registroInclusao[i] = nmr.getConteudoRegistro().toCharArray()[i];
									}
								}
							}

							// h.01
							if (registroInclusao != null && registroInclusao.length > 3)
								colocarConteudo(getConteudo(1, 2, registroInclusao), 1, registro);
							// h.02
							if (registroInclusao != null && registroInclusao.length > 4)
								colocarConteudo(getConteudo(3, 1, registroInclusao), 3, registro);
							// h.03
							if (registroInclusao != null && registroInclusao.length > 19)
								colocarConteudo(getConteudo(4, 15, registroInclusao), 4, registro);
							// h.04
							colocarConteudo("E", 19, registro);
							// h.05
							if (registroInclusao != null && registroInclusao.length > 21)
								colocarConteudo(getConteudo(20, 1, registroInclusao), 20, registro);
							// h.06
							if (registroInclusao != null && registroInclusao.length > 29)
								colocarConteudo(getConteudo(21, 8, registroInclusao), 21, registro);
							// h.07
							if (registroInclusao != null && registroInclusao.length > 27)
								colocarConteudo(getConteudo(29, 8, registroInclusao), 29, registro);
							// h.08
							if (registroInclusao != null && registroInclusao.length > 49)
								colocarConteudo(getConteudo(37, 13, registroInclusao), 37, registro);
							// h.09
							if (registroInclusao != null && registroInclusao.length > 80)
								colocarConteudo(getConteudo(50, 30, registroInclusao), 50, registro);
							// h.10
							if (registroInclusao != null && registroInclusao.length > 88)
								colocarConteudo(getConteudo(80, 8, registroInclusao), 80, registro);
							// h.11
							if (registroInclusao != null && registroInclusao.length > 90)
								colocarConteudo(getConteudo(88, 2, registroInclusao), 88, registro);

							// h.12
							String codigoExclusaoMotivo = "";
							if (nem.getCodigoExclusaoMotivo() != null) {
								codigoExclusaoMotivo = nem.getCodigoExclusaoMotivo() + "";
							}

							if (codigoExclusaoMotivo.length() < 3) {
								while (codigoExclusaoMotivo.length() != 3) {
									codigoExclusaoMotivo = "0" + codigoExclusaoMotivo;
								}
							}

							colocarConteudo(codigoExclusaoMotivo, 90, registro);
							// h.13
							if (registroInclusao != null && registroInclusao.length > 2)
								colocarConteudo(getConteudo(93, 232, registroInclusao), 325, registro);
							// h.14
							if (registroInclusao != null && registroInclusao.length > 335)
								colocarConteudo(getConteudo(325, 10, registroInclusao), 325, registro);
							// h.15
							colocarConteudo(sNumeroRegistro, 335, registro);

							NegativadorMovimentoReg nmrExclusao = new NegativadorMovimentoReg();
							nmrExclusao.setNegativadorMovimento(nm);
							nmrExclusao.setNegativadorMovimentoRegInclusao(nmr);
							nmrExclusao.setNegativadorRegistroTipo(nmr.getNegativadorRegistroTipo());
							nmrExclusao.setConteudoRegistro(new String(registro));
							nmrExclusao.setUltimaAlteracao(new Date());
							nmrExclusao.setUsuario(nmr.getUsuario());
							nmrExclusao.setCodigoExclusaoTipo(nmr.getCodigoExclusaoTipo());
							nmrExclusao.setValorDebito(nmr.getValorDebito());
							nmrExclusao.setCobrancaDebitoSituacao(nmr.getCobrancaDebitoSituacao());
							nmrExclusao.setDataSituacaoDebito(nmr.getDataSituacaoDebito());
							nmrExclusao.setImovel(nmr.getImovel());
							nmrExclusao.setLocalidade(nmr.getLocalidade());
							nmrExclusao.setQuadra(nmr.getQuadra());
							nmrExclusao.setCodigoSetorComercial(nmr.getCodigoSetorComercial());
							nmrExclusao.setNumeroQuadra(nmr.getNumeroQuadra());
							nmrExclusao.setImovelPerfil(nmr.getImovelPerfil());
							nmrExclusao.setCliente(nmr.getCliente());
							nmrExclusao.setCategoria(nmr.getCategoria());
							nmrExclusao.setNumeroCpf(nmr.getNumeroCpf());
							nmrExclusao.setNumeroCnpj(nmr.getNumeroCnpj());
							nmrExclusao.setCpfTipo(nmr.getCpfTipo());
							nmrExclusao.setIndicadorSituacaoDefinitiva(new Short((short) 1));
							nmrExclusao.setNumeroRegistro(((Integer) retorno[0]));
							nmrExclusao.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);

							// **************************************************************************
							// RM3755
							// Autor: Ivan Sergio
							// Data: 11/01/2011
							// **************************************************************************

							nmrExclusao.setLigacaoAguaSituacao(nmr.getLigacaoAguaSituacao());
							nmrExclusao.setLigacaoEsgotoSituacao(nmr.getLigacaoEsgotoSituacao());

							// **************************************************************************

							// Inserir nmrExclusao
							getControladorUtil().inserir(nmrExclusao);
							// inicio do 7

							retorno[1] = new BigDecimal(((BigDecimal) retorno[1]).doubleValue()
									+ nmr.getValorDebito().doubleValue());
							retorno[2] = new Integer((Integer) retorno[2]) + 1;

							atualizarNegativacao(n.getId(), nmr, nem);

						}
					} else if (n.getId().equals(Negativador.NEGATIVADOR_SERASA)) {

						retorno[0] = new Integer((Integer) retorno[0]) + 1;

						NegativadorExclusaoMotivo nem = obterNegativadorExclusaoMotivo(nmr, n);

						char[] registro = new char[600];
						for (int i = 0; i < registro.length; i++) {
							registro[i] = ' ';
						}

						char[] registroInclusao = new char[600];
						for (int i = 0; i < registroInclusao.length; i++) {
							registroInclusao[i] = ' ';
						}

						if (nmr.getConteudoRegistro() != null) {
							for (int i = 0; i < nmr.getConteudoRegistro().length(); i++) {
								if (i <= registroInclusao.length) {
									registroInclusao[i] = nmr.getConteudoRegistro().toCharArray()[i];
								}
							}
						}

						String codigoIdExclusaoMotivo = nem.getCodigoExclusaoMotivo() + "";
						if (codigoIdExclusaoMotivo.length() < 2) {
							while (codigoIdExclusaoMotivo.length() != 2) {
								codigoIdExclusaoMotivo = "0" + codigoIdExclusaoMotivo;
							}
						}

						String sNumeroRegistro = ((Integer) retorno[0]).toString();
						if (sNumeroRegistro.length() < 7) {
							while (sNumeroRegistro.length() != 7) {
								sNumeroRegistro = "0" + sNumeroRegistro;
							}
						}
						// D.01
						colocarConteudo("1", 1, registro);
						// D.02
						colocarConteudo("E", 2, registro);
						// D.03
						// colocarConteudo(getConteudo(3, 1, registroInclusao),
						// 3, registro);
						// //D.04
						// colocarConteudo(getConteudo(9, 1, registroInclusao),
						// 9, registro);
						// //D.05
						// colocarConteudo(getConteudo(17, 1, registroInclusao),
						// 17, registro);
						// //D.06
						// colocarConteudo(getConteudo(25, 1, registroInclusao),
						// 25, registro);
						// //D.07
						// colocarConteudo(getConteudo(28, 1, registroInclusao),
						// 28, registro);
						// D.08
						colocarConteudo(getConteudo(32, 1, registroInclusao), 32, registro);
						// D.09
						colocarConteudo(getConteudo(33, 1, registroInclusao), 33, registro);
						// D.10
						colocarConteudo(getConteudo(34, 15, registroInclusao), 34, registro);
						// D.11
						colocarConteudo(codigoIdExclusaoMotivo, 49, registro);
						// D.12
						colocarConteudo(getConteudo(51, 1, registroInclusao), 51, registro);
						// D.13
						colocarConteudo(getConteudo(52, 15, registroInclusao), 52, registro);
						// D.14
						colocarConteudo(getConteudo(67, 2, registroInclusao), 67, registro);
						// //D.15
						// colocarConteudo(getConteudo(69, 1, registroInclusao),
						// 69, registro);
						// //D.16
						// colocarConteudo(getConteudo(70, 1, registroInclusao),
						// 70, registro);
						// //D.17
						// colocarConteudo(getConteudo(71, 1, registroInclusao),
						// 71, registro);
						// //D.18
						// colocarConteudo(getConteudo(86, 1, registroInclusao),
						// 86, registro);
						// //D.19
						// colocarConteudo(getConteudo(88, 1, registroInclusao),
						// 88, registro);
						// //D.20
						// colocarConteudo(getConteudo(89, 1, registroInclusao),
						// 89, registro);
						// //D.21
						// colocarConteudo(getConteudo(104, 1,
						// registroInclusao), 104, registro);
						// D.22
						colocarConteudo(getConteudo(106, 70, registroInclusao), 106, registro);
						// D.23
						colocarConteudo(getConteudo(176, 8, registroInclusao), 176, registro);
						// D.24
						// colocarConteudo(getConteudo(184, 1,
						// registroInclusao), 184, registro);
						// D.25
						colocarConteudo(getConteudo(254, 70, registroInclusao), 254, registro);
						// D.26
						// colocarConteudo(getConteudo(324, 1,
						// registroInclusao), 324, registro);
						// //D.27
						// colocarConteudo(getConteudo(369, 1,
						// registroInclusao), 369, registro);
						// //D.28
						// colocarConteudo(getConteudo(389, 1,
						// registroInclusao), 389, registro);
						// //D.29
						// colocarConteudo(getConteudo(414, 1,
						// registroInclusao), 414, registro);
						// //D.30
						// colocarConteudo(getConteudo(416, 1,
						// registroInclusao), 416, registro);
						// //D.31
						// colocarConteudo(getConteudo(424, 1,
						// registroInclusao), 424, registro);
						// D.32
						colocarConteudo(getConteudo(439, 16, registroInclusao), 439, registro);
						// D.33
						// colocarConteudo(getConteudo(455, 1,
						// registroInclusao), 455, registro);
						// //D.34
						// colocarConteudo(getConteudo(464, 1,
						// registroInclusao), 464, registro);
						// //D.35
						// colocarConteudo(getConteudo(489, 1,
						// registroInclusao), 489, registro);
						// //D.36
						// colocarConteudo(getConteudo(493, 1,
						// registroInclusao), 493, registro);
						// //D.37
						// colocarConteudo(getConteudo(502, 1,
						// registroInclusao), 502, registro);
						// //D.38
						// colocarConteudo(getConteudo(510, 1,
						// registroInclusao), 510, registro);
						// //D.39
						// colocarConteudo(getConteudo(525, 1,
						// registroInclusao), 525, registro);
						// //D.40
						// colocarConteudo(getConteudo(534, 1,
						// registroInclusao), 534, registro);
						// D.41
						colocarConteudo(sNumeroRegistro, 594, registro);

						NegativadorMovimentoReg nmrExclusao = new NegativadorMovimentoReg();
						nmrExclusao.setNegativadorMovimento(nm);
						nmrExclusao.setNegativadorMovimentoRegInclusao(nmr);
						nmrExclusao.setNegativadorRegistroTipo(nmr.getNegativadorRegistroTipo());
						nmrExclusao.setConteudoRegistro(new String(registro));
						nmrExclusao.setUltimaAlteracao(new Date());
						nmrExclusao.setUsuario(nmr.getUsuario());
						nmrExclusao.setCodigoExclusaoTipo(nmr.getCodigoExclusaoTipo());
						nmrExclusao.setValorDebito(nmr.getValorDebito());
						nmrExclusao.setCobrancaDebitoSituacao(nmr.getCobrancaDebitoSituacao());
						nmrExclusao.setDataSituacaoDebito(nmr.getDataSituacaoDebito());
						nmrExclusao.setImovel(nmr.getImovel());
						nmrExclusao.setLocalidade(nmr.getLocalidade());
						nmrExclusao.setQuadra(nmr.getQuadra());
						nmrExclusao.setCodigoSetorComercial(nmr.getCodigoSetorComercial());
						nmrExclusao.setNumeroQuadra(nmr.getNumeroQuadra());
						nmrExclusao.setImovelPerfil(nmr.getImovelPerfil());
						nmrExclusao.setCliente(nmr.getCliente());
						nmrExclusao.setCategoria(nmr.getCategoria());
						nmrExclusao.setNumeroCpf(nmr.getNumeroCpf());
						nmrExclusao.setNumeroCnpj(nmr.getNumeroCnpj());
						nmrExclusao.setCpfTipo(nmr.getCpfTipo());
						nmrExclusao.setIndicadorSituacaoDefinitiva(new Short((short) 1));
						nmrExclusao.setNumeroRegistro(((Integer) retorno[0]));
						nmrExclusao.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);

						// **************************************************************************
						// RM3755
						// Autor: Ivan Sergio
						// Data: 10/01/2011
						// **************************************************************************

						nmrExclusao.setLigacaoAguaSituacao(nmr.getLigacaoAguaSituacao());
						nmrExclusao.setLigacaoEsgotoSituacao(nmr.getLigacaoEsgotoSituacao());

						// **************************************************************************

						RepositorioUtilHBM.getInstancia().inserir(nmrExclusao);

						retorno[1] = new BigDecimal(((BigDecimal) retorno[1]).doubleValue()
								+ nmr.getValorDebito().doubleValue());
						retorno[2] = new Integer((Integer) retorno[2]) + 1;

						atualizarNegativacao(n.getId(), nmr, nem);

					}
				}
			}

			return retorno;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private void gerarNegMovRegDetalheConsumidor(NegativadorMovimentoReg nmr, NegativadorMovimento nm,
			String sNumeroRegistro) throws ErroRepositorioException {
		NegativadorMovimentoReg nmrDetalheConsumidor = repositorioSpcSerasa.pesquisarRegistroTipoConsumidor(
				nmr.getNumeroRegistro() - 1, nmr.getNegativadorMovimento().getId());

		char[] registro = new char[340];
		for (int i = 0; i < registro.length; i++) {
			registro[i] = ' ';
		}
		if (nmrDetalheConsumidor.getConteudoRegistro() != null) {
			for (int i = 0; i < nmrDetalheConsumidor.getConteudoRegistro().length(); i++) {
				if (i <= registro.length) {
					registro[i] = nmrDetalheConsumidor.getConteudoRegistro().toCharArray()[i];
				}
			}
		}

		// h.15
		colocarConteudo(sNumeroRegistro, 335, registro);

		NegativadorMovimentoReg nmrExclusao = new NegativadorMovimentoReg();
		nmrExclusao.setNegativadorMovimento(nm);
		nmrExclusao.setNegativadorMovimentoRegInclusao(nmrDetalheConsumidor);
		nmrExclusao.setNegativadorRegistroTipo(nmrDetalheConsumidor.getNegativadorRegistroTipo());
		nmrExclusao.setConteudoRegistro(new String(registro));
		nmrExclusao.setUltimaAlteracao(new Date());
		nmrExclusao.setUsuario(nmrDetalheConsumidor.getUsuario());
		nmrExclusao.setCodigoExclusaoTipo(nmrDetalheConsumidor.getCodigoExclusaoTipo());
		nmrExclusao.setValorDebito(nmrDetalheConsumidor.getValorDebito());
		nmrExclusao.setCobrancaDebitoSituacao(nmrDetalheConsumidor.getCobrancaDebitoSituacao());
		nmrExclusao.setDataSituacaoDebito(nmrDetalheConsumidor.getDataSituacaoDebito());
		nmrExclusao.setImovel(nmrDetalheConsumidor.getImovel());
		nmrExclusao.setLocalidade(nmrDetalheConsumidor.getLocalidade());
		nmrExclusao.setQuadra(nmrDetalheConsumidor.getQuadra());
		nmrExclusao.setCodigoSetorComercial(nmrDetalheConsumidor.getCodigoSetorComercial());
		nmrExclusao.setNumeroQuadra(nmrDetalheConsumidor.getNumeroQuadra());
		nmrExclusao.setImovelPerfil(nmrDetalheConsumidor.getImovelPerfil());
		nmrExclusao.setCliente(nmrDetalheConsumidor.getCliente());
		nmrExclusao.setCategoria(nmrDetalheConsumidor.getCategoria());
		nmrExclusao.setNumeroCpf(nmrDetalheConsumidor.getNumeroCpf());
		nmrExclusao.setNumeroCnpj(nmrDetalheConsumidor.getNumeroCnpj());
		nmrExclusao.setCpfTipo(nmrDetalheConsumidor.getCpfTipo());
		nmrExclusao.setIndicadorSituacaoDefinitiva(new Short((short) 1));
		nmrExclusao.setNumeroRegistro(new Integer(sNumeroRegistro));
		nmrExclusao.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);

		// **************************************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 10/01/2011
		// **************************************************************************

		nmrExclusao.setLigacaoAguaSituacao(nmrDetalheConsumidor.getLigacaoAguaSituacao());
		nmrExclusao.setLigacaoEsgotoSituacao(nmrDetalheConsumidor.getLigacaoEsgotoSituacao());

		// **************************************************************************

		// Inserir registro de exclus�o do tipo Detalhe-Consumidor
		RepositorioUtilHBM.getInstancia().inserir(nmrExclusao);

		// Atualiza a exclus�o do registro Tipo Detalhe-Consumidor
		nmrDetalheConsumidor.setCodigoExclusaoTipo(1);
		nmrDetalheConsumidor.setUltimaAlteracao(new Date());
		RepositorioUtilHBM.getInstancia().atualizar(nmrDetalheConsumidor);

	}

	/**
	 * M�todo que atualiza a negativacao
	 * 
	 * [UC0673] - Gerar Movimento de Exclusao de Negativacao [SB0007] -
	 * Atualizar Negativacao
	 * 
	 * @author Thiago Toscano
	 * @date 04/01/2008
	 *
	 * @param tipoNegativador
	 *            (1 para spc; 2 para serasa)
	 * @param nmr
	 * @param nem
	 * @throws ErroRepositorioException
	 */
	private void atualizarNegativacao(Integer codigoNegativador, NegativadorMovimentoReg nmr,
			NegativadorExclusaoMotivo nem) throws ErroRepositorioException {
		nmr.setCodigoExclusaoTipo(new Integer(1));
		nmr.setIndicadorSituacaoDefinitiva(new Short((short) 1));
		nmr.setNegativadorExclusaoMotivo(nem);
		nmr.setUltimaAlteracao(new Date());
		// adicionado por Vivianne Sousa - 21/08/2009 - analista:Fatima
		nmr.setCobrancaSituacao(null);

		RepositorioUtilHBM.getInstancia().atualizar(nmr);

		// sb7.4 atualizar os negativador movimento reg item
		FiltroNegativadorMovimentoRegItem fnmri = new FiltroNegativadorMovimentoRegItem();
		fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,
				nmr.getId()));
		// fnmri.adicionarParametro(new
		// ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,nmr.getId()));
		Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(fnmri,
				NegativadorMovimentoRegItem.class.getName());
		if (coll != null && !coll.isEmpty()) {
			Iterator itt = coll.iterator();
			while (itt.hasNext()) {
				NegativadorMovimentoRegItem nmri = (NegativadorMovimentoRegItem) itt.next();
				nmri.setCobrancaDebitoSituacaoAposExclusao(nmri.getCobrancaDebitoSituacao());
				nmri.setDataSituacaoDebitoAposExclusao(nmri.getDataSituacaoDebito());
				nmri.setUltimaAlteracao(new Date());
				RepositorioUtilHBM.getInstancia().atualizar(nmri);
			}
		}

		// sb 7.5
		FiltroNegativacaoImoveis fni = new FiltroNegativacaoImoveis();
		fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID, nmr.getImovel().getId()));
		fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID, nmr
				.getNegativadorMovimento().getNegativacaoComando().getId()));
		fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.INDICADOR_EXCLUIDO, new Integer(2)));
		coll = RepositorioUtilHBM.getInstancia().pesquisar(fni, NegativacaoImoveis.class.getName());
		if (coll != null && !coll.isEmpty()) {
			Iterator itt = coll.iterator();
			while (itt.hasNext()) {
				NegativacaoImoveis ni = (NegativacaoImoveis) itt.next();
				ni.setIndicadorExcluido(new Short((short) 1));
				ni.setDataExclusao(new Date());
				ni.setUltimaAlteracao(new Date());
				RepositorioUtilHBM.getInstancia().atualizar(ni);

				// sb7.7
				if (nmr.getImovel() != null) {

					Collection imoveisCobrancaSituacao = this.repositorioSpcSerasa
							.consultarImovelCobrancaSituacaoPorNegativador(nmr.getImovel(), codigoNegativador);

					if (imoveisCobrancaSituacao != null) {

						Iterator iter = imoveisCobrancaSituacao.iterator();

						while (iter.hasNext()) {
							ImovelCobrancaSituacao imovelCobrancaSituacao = (ImovelCobrancaSituacao) iter.next();
							imovelCobrancaSituacao.setDataRetiradaCobranca(new Date());
							imovelCobrancaSituacao.setUltimaAlteracao(new Date());

							RepositorioUtilHBM.getInstancia().atualizar(imovelCobrancaSituacao);
						}

					}

				}

				// CRC3323 - comentado por Vivianne Sousa - analista:Fatima
				// Sampaio - 10/05/2010
				// if (nmr.getImovel() != null &&
				// nmr.getImovel().getCobrancaSituacao() != null) {
				// // sb8
				// if (
				// nmr.getImovel().getCobrancaSituacao().getId().equals(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO)
				// ||
				// nmr.getImovel().getCobrancaSituacao().getId().equals(CobrancaSituacao.CARTA_ENVIADA_AO_SPC)
				// ||
				// nmr.getImovel().getCobrancaSituacao().getId().equals(CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NO_SPC)
				// ||
				// nmr.getImovel().getCobrancaSituacao().getId().equals(CobrancaSituacao.CARTA_ENVIADA_A_SERASA)
				// ||
				// nmr.getImovel().getCobrancaSituacao().getId().equals(CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NA_SERASA)
				// ) {
				//
				// //sb8.1
				// if (coll.size() == 1) {
				// nmr.getImovel().setCobrancaSituacao(null);
				// nmr.getImovel().setUltimaAlteracao(new Date());
				//
				// RepositorioUtilHBM.getInstancia().atualizar(nmr.getImovel());
				// }
				//
				// }
				// }

			}

		}
	}

	/**
	 * M�todo que gera o trailler a ser enviado [UC0673] - Gerar Movimento de
	 * Exclusao de Negativacao [SB0008] - Gerar Registro Tipo trailler
	 * 
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 *
	 * @param n
	 *            - negativador
	 * @param nm
	 *            -negativaromovimento
	 * @param nc
	 *            - negativadorContrato
	 * @param collNegativadorMovimentoReg
	 *            Object[0] Integer numeroRegistro = new Integer(0); Object[1]
	 *            BigDecimal valor = new BigDecimal(0); Object[2] Integer
	 *            quantidadeExclusao = new Integer(0);
	 * @throws ErroRepositorioException
	 * 
	 */
	private Object[] gerarRegistroTipoTrailler(Integer numeroRegistro, BigDecimal valor, Integer quantidadeExclusao,
			Negativador n, NegativadorMovimento nm, NegativadorContrato nc, Collection collNegativadorMovimentoReg)
			throws ErroRepositorioException {
		Object[] retorno = new Object[3];
		retorno[0] = numeroRegistro + 1;
		retorno[1] = valor;
		retorno[2] = quantidadeExclusao;

		char[] registro = new char[340];
		for (int i = 0; i < registro.length; i++) {
			registro[i] = ' ';
		}

		// String ddmmaaaa = Util.formatarDataSemBarra(new Date());
		// String aaaammdd = ddmmaaaa.substring(0,2) + ddmmaaaa.substring(2,4) +
		// ddmmaaaa.substring(4,8) ;

		String numeroContrato = (nc.getNumeroSequencialEnvio() + 1) + "";
		if (numeroContrato.length() < 8) {
			while (numeroContrato.length() != 8) {
				numeroContrato = "0" + numeroContrato;
			}
		}
		if (n.getId().equals(Negativador.NEGATIVADOR_SPC)) {

			String tamanho316 = "";
			while (tamanho316.length() != 316) {
				tamanho316 = " " + tamanho316;
			}

			String sNumeroRegistro = retorno[0].toString();
			if (sNumeroRegistro.length() < 6) {
				while (sNumeroRegistro.length() != 6) {
					sNumeroRegistro = "0" + sNumeroRegistro;
				}
			}

			// h.01
			colocarConteudo("99", 1, registro);
			// h.02
			colocarConteudo(sNumeroRegistro, 3, registro);
			// h.03
			colocarConteudo(tamanho316, 9, registro);
			// h.04
			colocarConteudo("          ", 325, registro);
			// h.05
			colocarConteudo(sNumeroRegistro, 335, registro);
		} else if (n.getId().equals(Negativador.NEGATIVADOR_SERASA)) {
			registro = new char[600];
			for (int i = 0; i < registro.length; i++) {
				registro[i] = ' ';
			}

			String sNumeroRegistro = retorno[0].toString();
			if (sNumeroRegistro.length() < 7) {
				while (sNumeroRegistro.length() != 7) {
					sNumeroRegistro = "0" + sNumeroRegistro;
				}
			}

			// h.01
			colocarConteudo("9", 1, registro);
			// h.02
			// colocarConteudo(sNumeroRegistro, 2, registro);
			// h.03
			// colocarConteudo(tamanho316, 534, registro);
			// h.04
			colocarConteudo(sNumeroRegistro, 594, registro);
		}

		FiltroNegativadorRegistroTipo fnrt = new FiltroNegativadorRegistroTipo();
		fnrt.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.NEGATIVADOR_ID, n.getId()));
		fnrt.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.CODIGO_REGISTRO,
				NegativadorRegistroTipo.TIPO_TRAILLER));

		NegativadorRegistroTipo nrt = (NegativadorRegistroTipo) Util.retonarObjetoDeColecao(RepositorioUtilHBM
				.getInstancia().pesquisar(fnrt, NegativadorRegistroTipo.class.getName()));

		NegativadorMovimentoReg nmr = new NegativadorMovimentoReg();
		nmr.setNegativadorMovimento(nm);
		nmr.setNegativadorRegistroTipo(nrt);
		nmr.setConteudoRegistro(new String(registro));
		nmr.setIndicadorSituacaoDefinitiva(new Short((short) 1));
		nmr.setNumeroRegistro((Integer) retorno[0]);
		nmr.setUltimaAlteracao(new Date());
		nmr.setIndicadorItemAtualizado(ConstantesSistema.NAO_ACEITO);

		RepositorioUtilHBM.getInstancia().inserir(nmr);

		return retorno;
	}

	/**
	 * M�todo que retorna o motivo da Exclusao
	 *
	 * @author Thiago Toscano
	 * @throws ErroRepositorioException
	 * @date 26/12/2007
	 *
	 */
	private NegativadorExclusaoMotivo obterNegativadorExclusaoMotivo(NegativadorMovimentoReg nmr, Negativador n)
			throws ErroRepositorioException {
		NegativadorExclusaoMotivo nemt = null;
		if (nmr.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.PAGO)) {
			nemt = repositorioSpcSerasa.pesquisarCodigoMotivoExclusao(CobrancaDebitoSituacao.PAGO, n.getId(),
					NegativadorExclusaoMotivo.PAGAMENTO_DA_DIVIDA);
		} else if (nmr.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.PARCELADO)) {
			nemt = repositorioSpcSerasa.pesquisarCodigoMotivoExclusao(CobrancaDebitoSituacao.PARCELADO, n.getId(),
					NegativadorExclusaoMotivo.RENEGOCIACAO_DA_DIVIDA);
		} else if (nmr.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.CANCELADO)) {
			nemt = repositorioSpcSerasa.pesquisarCodigoMotivoExclusao(CobrancaDebitoSituacao.CANCELADO, n.getId(),
					NegativadorExclusaoMotivo.MOTIVO_NAO_IDENTIFICADO);
		}

		return nemt;
	}

	/**
	 * M�todo consuta os Negativadores que tenham movimento de Exclus�o do spc
	 * ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclus�o de Negativa��o [SB0002] - Gerar
	 * TxT de Movimento de Exclus�o de Negativacao
	 * 
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 * 
	 */
	public Collection gerarArquivoTXTMovimentoExclusaoNegativacao(Integer idMovimento) throws ControladorException {

		Integer idNegativador = null;
		try {
			Collection retorno = new ArrayList();

			FiltroNegativadorMovimento fnm = new FiltroNegativadorMovimento();
			fnm.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID, idMovimento));
			fnm.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(fnm, NegativadorMovimento.class.getName());
			if (coll != null && !coll.isEmpty()) {
				NegativadorMovimento nm = (NegativadorMovimento) coll.iterator().next();

				NegativadorMovimentoGeradosExclusaoHelper helper = new NegativadorMovimentoGeradosExclusaoHelper();
				helper.setDescricaoNegativador(nm.getNegativador().getCliente().getNome());
				helper.setDataProcessamento(nm.getUltimaAlteracao());
				helper.setHoraProcessamento(nm.getUltimaAlteracao());
				helper.setNsa(nm.getNumeroSequencialEnvio() + "");
				helper.setQuantidadeRegistros(nm.getNumeroRegistrosEnvio() + "");
				helper.setValorDebito(gcom.util.Util.formataBigDecimal(nm.getValorTotalEnvio(), 2, true) + "");
				retorno.add(helper);

				idNegativador = nm.getNegativador().getId();
			}

			if (idNegativador != null) {
				this.gerarArquivo(idMovimento, false, idNegativador);
			}

			return retorno;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * M�todo privado que gera uma string buffer que representa o arquivo dos
	 * movimentos de exclusao de negativacao [UC0673] - Gerar Movimento da
	 * Exclus�o de Negativa��o [SB0009] - Gerar Arquivo TxT para Envio ao
	 * Negativador
	 * 
	 * @author Thiago Toscano
	 * @date 27/12/2007
	 * 
	 * @param idMovimento
	 * @return o arquivo
	 * @throws ErroRepositorioException
	 * @throws ControladorException
	 */
	private void gerarArquivo(Integer idMovimento, boolean trailler, Integer idNegativador) throws ControladorException {

		StringBuffer sb = new StringBuffer();
		int numeroRegistro = 0;

		ZipOutputStream zos = null;
		BufferedWriter out = null;

		try {

			FiltroNegativadorMovimento fnm = new FiltroNegativadorMovimento();
			fnm.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID, idMovimento));
			Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(fnm, NegativadorMovimento.class.getName());
			if (coll != null && !coll.isEmpty()) {
				NegativadorMovimento nm = (NegativadorMovimento) coll.iterator().next();
				coll = repositorioSpcSerasa.consultarNegativadorMovimentoRegistroParaGerarArquivo(idMovimento, "H");
				if (coll != null && !coll.isEmpty()) {
					NegativadorMovimentoReg nmr = (NegativadorMovimentoReg) coll.iterator().next();
					sb.append(nmr.getConteudoRegistro());
					sb.append("\n");
				}
				coll = repositorioSpcSerasa.consultarNegativadorMovimentoRegistroParaGerarArquivo(idMovimento, "D");
				if (coll != null && !coll.isEmpty()) {
					Iterator it = coll.iterator();
					while (it.hasNext()) {
						NegativadorMovimentoReg nmr = (NegativadorMovimentoReg) it.next();
						sb.append(nmr.getConteudoRegistro());
						sb.append("\n");
						numeroRegistro = nmr.getNumeroRegistro();
					}
				}
				if (trailler) {
					StringBuilder registroTrailler = new StringBuilder();
					// obtem dados do negativador Criterio [SB0002] 1.
					NegativacaoCriterio negCriterio = (NegativacaoCriterio) Util
							.retonarObjetoDeColecao(this.repositorioSpcSerasa.getNegativacaoCriterio(nm
									.getNegativacaoComando().getId()));
					if (new Integer(nm.getNegativador().getId()).equals(Negativador.NEGATIVADOR_SPC)) {
						registroTrailler = this.geraRegistroTipoTraillerSPC(numeroRegistro);
					} else {
						registroTrailler = this.geraRegistroTipoTraillerSERASA(numeroRegistro);
					}

					Integer idNegativadorRegistroTipo = null;
					if (nm.getId().equals(Negativador.NEGATIVADOR_SPC)){
						idNegativadorRegistroTipo = NegativadorRegistroTipo.ID_SPC_TRAILLER;
					} else {
						idNegativadorRegistroTipo = NegativadorRegistroTipo.ID_SERASA_TRAILLER;
					}
					
					this.gerarNegativadorMovimentoRegistro(nm.getNegativador().getId(), nm.getId(), registroTrailler,
							(numeroRegistro + 1), negCriterio, idNegativadorRegistroTipo);
					
					sb.append(registroTrailler.toString());
					sb.append("\n");
				} else {
					coll = repositorioSpcSerasa.consultarNegativadorMovimentoRegistroParaGerarArquivo(idMovimento, "T");
					if (coll != null && !coll.isEmpty()) {
						NegativadorMovimentoReg nmr = (NegativadorMovimentoReg) coll.iterator().next();
						sb.append(nmr.getConteudoRegistro());
						sb.append("\n");
					}
				}
			}

			Date data = new Date();
			String AAAAMMDD = Util.formatarDataAAAAMMDD(data);
			String HHMM = Util.formatarDataHHMM(data);
			String formatodatahora = AAAAMMDD + "_" + HHMM;
			File leituraTipo = null;

			EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.SPC_SERASA);

			String emailRemetente = envioEmail.getEmailRemetente();

			String tituloMensagem = envioEmail.getTituloMensagem();

			String corpoMensagem = envioEmail.getCorpoMensagem();
			String emailReceptor = envioEmail.getEmailReceptor();
			System.out.println("email destinat�rio:" + emailReceptor);

			String nomeZip = "";
			if (idNegativador.equals(Negativador.NEGATIVADOR_SPC)) {
				nomeZip = "REG_SPC_" + formatodatahora;
				leituraTipo = new File(nomeZip + ".env");
			} else {
				nomeZip = "REG_SERASA_" + formatodatahora;
				leituraTipo = new File(nomeZip + ".txt");
			}

			File compactado = new File(nomeZip + ".zip"); // nomeZip
			zos = new ZipOutputStream(new FileOutputStream(compactado));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
			out.write(sb.toString());
			out.flush();

			out.close();

			ZipUtil.adicionarArquivo(zos, leituraTipo);

			zos.close();

			ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor, emailRemetente, tituloMensagem, corpoMensagem,
					compactado);

			leituraTipo.delete();

		} catch (IOException ex) {
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		} catch (Exception e) {
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Author: Rafael Santos Data: 04/01/2006
	 * 
	 * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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
	 * M�todo usado para pesquisa de Comando Negativa��o (Helper)
	 * 
	 * [UC0655] Filtrar Comando Negativa��o
	 * 
	 * @author Thiago Vieira
	 * @date 02/01/2008
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoHelper(ComandoNegativacaoHelper comandoNegativacaoHelper)
			throws ControladorException {

		Collection pesquisaComandoNegativacao = new ArrayList();
		Collection<ComandoNegativacaoHelper> retorno = new ArrayList<ComandoNegativacaoHelper>();

		try {

			pesquisaComandoNegativacao = repositorioSpcSerasa
					.pesquisarComandoNegativacaoHelper(comandoNegativacaoHelper);

			if (pesquisaComandoNegativacao != null && !pesquisaComandoNegativacao.isEmpty()) {

				Iterator comandoNegativacaoIterator = pesquisaComandoNegativacao.iterator();

				while (comandoNegativacaoIterator.hasNext()) {
					ComandoNegativacaoHelper comandoNegativacao = new ComandoNegativacaoHelper();
					Object[] dadosComandoNegativacao = (Object[]) comandoNegativacaoIterator.next();

					if (dadosComandoNegativacao[0] != null) {
						comandoNegativacao.setIdComandoNegativacao((Integer) dadosComandoNegativacao[0]);
					}
					if (dadosComandoNegativacao[1] != null) {
						comandoNegativacao.setTituloComando((String) dadosComandoNegativacao[1]);
					}
					if (dadosComandoNegativacao[2] != null) {
						comandoNegativacao.setIndicadorComandoSimulado((Short) dadosComandoNegativacao[2]);
					}
					if (dadosComandoNegativacao[3] != null) {
						comandoNegativacao.setGeracaoComandoInicio((Date) dadosComandoNegativacao[3]);
					}
					if (dadosComandoNegativacao[4] != null) {
						comandoNegativacao.setExecucaoComandoInicio((Date) dadosComandoNegativacao[4]);
					}
					if (dadosComandoNegativacao[5] != null) {
						comandoNegativacao.setQuantidadeInclusoes((Integer) dadosComandoNegativacao[5]);
					}
					if (dadosComandoNegativacao[6] != null) {
						comandoNegativacao.setNomeUsuarioResponsavel((String) dadosComandoNegativacao[6]);
					}
					if (dadosComandoNegativacao[7] != null) {
						comandoNegativacao.setNomeClienteNegativador((String) dadosComandoNegativacao[7]);
					}
					retorno.add(comandoNegativacao);
				}
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * [UC0651] Inserir Comando Negativa��o [FS0015] Verificar exist�ncia de
	 * negativa��o para o im�vel no negativador
	 * 
	 * @author Ana Maria
	 * @date 04/12/2007
	 * 
	 * @param idNegativador
	 * @param idImovel
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarExistenciaNegativacaoImovel(Integer idImovel) throws ControladorException {
		try {
			return repositorioSpcSerasa.verificarExistenciaNegativacaoImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0651] Inserir Comando Negativa��o [SB0003] Determinar Data Prevista
	 * para Execu��o do Comando
	 * 
	 * @author Ana Maria
	 * @date 11/12/2007
	 * 
	 * @param idNegativador
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaDataRealizacaoComando(Integer idNegativador, Integer icSimulacao)
			throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarUltimaDataRealizacaoComando(idNegativador, icSimulacao);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

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
	public Integer inserirComandoNegativacao(InserirComandoNegativacaoPorCriterioHelper helper) throws ControladorException {

		Integer retorno = null;

		try {
			// [FS0014]- Verificar exist�ncia de comando para os mesmos par�metros
			String nomeNegativacaoComando = repositorioSpcSerasa.verificarExistenciaComandoMesmoParametro(helper);

			if (nomeNegativacaoComando != null && !nomeNegativacaoComando.equals("")) {
				throw new ControladorException("atencao.comando_nao_realizado_mesmo_parametro", null, nomeNegativacaoComando);
			}

			// Incluir Comando Negativacao
			NegativacaoComando negativacaoComando = helper.getNegativacaoComando();
			negativacaoComando.setUltimaAlteracao(new Date());
			negativacaoComando.setIndicadorBaixaRenda(new Short(helper.getIndicadorBaixaRenda()));

			Integer idNegativacaoComandoGerado = (Integer) this.getControladorUtil().inserir(negativacaoComando);
			negativacaoComando.setId(idNegativacaoComandoGerado);

			// Incluir Negativacao Criterio
			NegativacaoCriterio negativacaoCriterio = helper.getNegativacaoCriterio();
			negativacaoCriterio.setNegativacaoComando(negativacaoComando);
			negativacaoCriterio.setUltimaAlteracao(new Date());

			Integer idNegativacaoCriterioGerado = (Integer) this.getControladorUtil().inserir(negativacaoCriterio);
			negativacaoCriterio.setId(idNegativacaoCriterioGerado);

			// Incluir Negativacao Criterio CPF Tipo
			Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo = helper.getColecaoNegativacaoCriterioCpfTipo();
			Iterator negativacaoCriterioCpfTipoIterator = colecaoNegativacaoCriterioCpfTipo.iterator();
			while (negativacaoCriterioCpfTipoIterator.hasNext()) {
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = (NegativacaoCriterioCpfTipo) negativacaoCriterioCpfTipoIterator.next();
				negativacaoCriterioCpfTipo.setNegativacaoCriterio(negativacaoCriterio);
				negativacaoCriterioCpfTipo.setUltimaAlteracao(new Date());
				this.getControladorUtil().inserir(negativacaoCriterioCpfTipo);
			}

			// Incluir Negativacao Criterio Situacao Cobranca
			if (helper.getIdsCobrancaSituacao() != null && helper.getIdsCobrancaSituacao().length > 0) {
				String[] idsCobrancaSituacao = (String[]) helper.getIdsCobrancaSituacao();
				int indexCobrancaSituacao = 0;

				if (!idsCobrancaSituacao[indexCobrancaSituacao].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsCobrancaSituacao.length > indexCobrancaSituacao) {

						Integer idCobrancaSituacao = new Integer(idsCobrancaSituacao[indexCobrancaSituacao]);

						NegativacaoCriterioSituacaoCobranca negativacaoCriterioSituacaoCobranca = new NegativacaoCriterioSituacaoCobranca();

						negativacaoCriterioSituacaoCobranca.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioSituacaoCobranca.setUltimaAlteracao(new Date());

						CobrancaSituacao cobranca = new CobrancaSituacao();
						cobranca.setId(idCobrancaSituacao);

						negativacaoCriterioSituacaoCobranca.setCobrancaSituacao(cobranca);

						this.getControladorUtil().inserir(negativacaoCriterioSituacaoCobranca);

						indexCobrancaSituacao++;
					}
				}
			}

			// Incluir Negativacao Criterio Situacao Cobranca
			if (helper.getIdsCobrancaSituacaoTipo() != null && helper.getIdsCobrancaSituacaoTipo().length > 0) {
				String[] idsCobrancaSituacaoTipo = (String[]) helper.getIdsCobrancaSituacaoTipo();
				int indexCobrancaSituacaoTipo = 0;

				if (!idsCobrancaSituacaoTipo[indexCobrancaSituacaoTipo].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsCobrancaSituacaoTipo.length > indexCobrancaSituacaoTipo) {

						Integer idCobrancaSituacaoTipo = new Integer(idsCobrancaSituacaoTipo[indexCobrancaSituacaoTipo]);

						NegativacaoCriterioSituacaoEspecialCobranca negativacaoCriterioSituacaoEspecialCobranca = new NegativacaoCriterioSituacaoEspecialCobranca();

						negativacaoCriterioSituacaoEspecialCobranca.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioSituacaoEspecialCobranca.setUltimaAlteracao(new Date());

						CobrancaSituacaoTipo cobranca = new CobrancaSituacaoTipo();
						cobranca.setId(idCobrancaSituacaoTipo);

						negativacaoCriterioSituacaoEspecialCobranca.setCobrancaSituacaoTipo(cobranca);

						this.getControladorUtil().inserir(negativacaoCriterioSituacaoEspecialCobranca);

						indexCobrancaSituacaoTipo++;
					}
				}
			}

			// Incluir Negativacao Situa��o Liga��o �gua
			if (helper.getIdsLigacaoAguaSituacao() != null && helper.getIdsLigacaoAguaSituacao().length > 0) {
				String[] idsLigacaoAguaSituacao = (String[]) helper.getIdsLigacaoAguaSituacao();
				int indexLigacaoAguaSituacao = 0;

				if (!idsLigacaoAguaSituacao[indexLigacaoAguaSituacao].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsLigacaoAguaSituacao.length > indexLigacaoAguaSituacao) {
						Integer idLigacaoAguaSituacao = new Integer(idsLigacaoAguaSituacao[indexLigacaoAguaSituacao]);
						NegativacaoCriterioLigacaoAgua ncLigacaoAgua = new NegativacaoCriterioLigacaoAgua();
						LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
						ligacaoAguaSituacao.setId(idLigacaoAguaSituacao);

						NegativacaoCriterioLigacaoAguaPK negativacaoCriterioLigacaoAguaPK = new NegativacaoCriterioLigacaoAguaPK();
						negativacaoCriterioLigacaoAguaPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioLigacaoAguaPK.setLigacaoAguaSituacao(ligacaoAguaSituacao);
						ncLigacaoAgua.setComp_id(negativacaoCriterioLigacaoAguaPK);
						ncLigacaoAgua.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncLigacaoAgua);

						indexLigacaoAguaSituacao++;
					}
				}
			}

			// Incluir Negativacao Situa��o Liga��o Esgoto
			if (helper.getIdsLigacaoEsgotoSituacao() != null && helper.getIdsLigacaoEsgotoSituacao().length > 0) {
				String[] idsLigacaoEsgotoSituacao = (String[]) helper.getIdsLigacaoEsgotoSituacao();
				int indexLigacaoEsgotoSituacao = 0;

				if (!idsLigacaoEsgotoSituacao[indexLigacaoEsgotoSituacao].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsLigacaoEsgotoSituacao.length > indexLigacaoEsgotoSituacao) {
						Integer idLigacaoEsgotoSituacao = new Integer(idsLigacaoEsgotoSituacao[indexLigacaoEsgotoSituacao]);
						NegativacaoCriterioLigacaoEsgoto ncLigacaoEsgoto = new NegativacaoCriterioLigacaoEsgoto();
						LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
						ligacaoEsgotoSituacao.setId(idLigacaoEsgotoSituacao);

						NegativacaoCriterioLigacaoEsgotoPK negativacaoCriterioLigacaoEsgotoPK = new NegativacaoCriterioLigacaoEsgotoPK();
						negativacaoCriterioLigacaoEsgotoPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioLigacaoEsgotoPK.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
						ncLigacaoEsgoto.setComp_id(negativacaoCriterioLigacaoEsgotoPK);
						ncLigacaoEsgoto.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncLigacaoEsgoto);

						indexLigacaoEsgotoSituacao++;
					}
				}
			}

			// Incluir Negativacao Criterio SubCategoria
			if (helper.getIdsSubcategoria() != null && helper.getIdsSubcategoria().length > 0) {
				String[] idsSubCategoria = (String[]) helper.getIdsSubcategoria();
				int indexSubCategoria = 0;
				if (!idsSubCategoria[indexSubCategoria].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsSubCategoria.length > indexSubCategoria) {
						Integer idSubCategoria = new Integer(idsSubCategoria[indexSubCategoria]);
						NegativacaoCriterioSubcategoria ncSubacategoria = new NegativacaoCriterioSubcategoria();
						Subcategoria subcategoria = new Subcategoria();
						subcategoria.setId(idSubCategoria);

						NegativacaoCriterioSubcategoriaPK negativacaoCriterioSubcategoriaPK = new NegativacaoCriterioSubcategoriaPK();
						negativacaoCriterioSubcategoriaPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioSubcategoriaPK.setSubcategoria(subcategoria);
						ncSubacategoria.setComp_id(negativacaoCriterioSubcategoriaPK);
						ncSubacategoria.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncSubacategoria);

						indexSubCategoria++;
					}
				}
			}

			// Incluir Negativacao Imovel Perfil
			if (helper.getIdsPerfilImovel() != null && helper.getIdsPerfilImovel().length > 0) {
				String[] idsPerfilImovel = (String[]) helper.getIdsPerfilImovel();
				int indexPerfilaImovel = 0;

				if (!idsPerfilImovel[indexPerfilaImovel].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsPerfilImovel.length > indexPerfilaImovel) {
						Integer idPerfilImovel = new Integer(idsPerfilImovel[indexPerfilaImovel]);
						NegativacaoCriterioImovelPerfil ncImovelPerfil = new NegativacaoCriterioImovelPerfil();
						ImovelPerfil imovelPerfil = new ImovelPerfil();
						imovelPerfil.setId(idPerfilImovel);

						NegativacaoCriterioImovelPerfilPK negativacaoCriterioImovelPerfilPK = new NegativacaoCriterioImovelPerfilPK();
						negativacaoCriterioImovelPerfilPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioImovelPerfilPK.setImovelPerfil(imovelPerfil);
						ncImovelPerfil.setComp_id(negativacaoCriterioImovelPerfilPK);
						ncImovelPerfil.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncImovelPerfil);

						indexPerfilaImovel++;
					}
				}
			}

			// Incluir Negativacao Cliente Tipo
			if (helper.getIdsTipoCliente() != null && helper.getIdsTipoCliente().length > 0) {
				String[] idsTipoCliente = (String[]) helper.getIdsTipoCliente();
				int indexClienteTipo = 0;

				if (!idsTipoCliente[indexClienteTipo].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsTipoCliente.length > indexClienteTipo) {
						Integer idTipoCliente = new Integer(idsTipoCliente[indexClienteTipo]);
						NegativacaoCriterioClienteTipo ncClienteTipo = new NegativacaoCriterioClienteTipo();
						ClienteTipo clienteTipo = new ClienteTipo();
						clienteTipo.setId(idTipoCliente);

						NegativacaoCriterioClienteTipoPK negativacaoCriterioClienteTipoPK = new NegativacaoCriterioClienteTipoPK();
						negativacaoCriterioClienteTipoPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioClienteTipoPK.setClienteTipo(clienteTipo);
						ncClienteTipo.setComp_id(negativacaoCriterioClienteTipoPK);
						ncClienteTipo.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncClienteTipo);

						indexClienteTipo++;
					}
				}
			}

			// Incluir Negativacao Grupo Cobran�a
			if (helper.getIdsCobrancaGrupo() != null && helper.getIdsCobrancaGrupo().length > 0) {
				String[] idsCobrancaGrupo = (String[]) helper.getIdsCobrancaGrupo();
				int indexCobrancaGrupo = 0;

				if (!idsCobrancaGrupo[indexCobrancaGrupo].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsCobrancaGrupo.length > indexCobrancaGrupo) {
						Integer idCobrancaGrupo = new Integer(idsCobrancaGrupo[indexCobrancaGrupo]);
						NegativCritCobrGrupo ncCobrGrupo = new NegativCritCobrGrupo();
						CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
						cobrancaGrupo.setId(idCobrancaGrupo);

						NegativCritCobrGrupoPK negativCritCobrGrupoPK = new NegativCritCobrGrupoPK();
						negativCritCobrGrupoPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritCobrGrupoPK.setCobrancaGrupo(cobrancaGrupo);
						ncCobrGrupo.setComp_id(negativCritCobrGrupoPK);
						ncCobrGrupo.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncCobrGrupo);

						indexCobrancaGrupo++;
					}
				}
			}

			// Incluir Negativacao Gerencia Regional
			if (helper.getIdsGerenciaRegional() != null && helper.getIdsGerenciaRegional().length > 0) {
				String[] idsGerenciaRegional = (String[]) helper.getIdsGerenciaRegional();
				int indexGerenciaRegional = 0;

				if (!idsGerenciaRegional[indexGerenciaRegional].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsGerenciaRegional.length > indexGerenciaRegional) {
						Integer idGerenciaRegional = new Integer(idsGerenciaRegional[indexGerenciaRegional]);
						NegativCritGerReg negativCritGerReg = new NegativCritGerReg();
						GerenciaRegional gerenciaRegional = new GerenciaRegional();
						gerenciaRegional.setId(idGerenciaRegional);

						NegativCritGerRegPK negativCritGerRegPK = new NegativCritGerRegPK();
						negativCritGerRegPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritGerRegPK.setGerenciaRegional(gerenciaRegional);
						negativCritGerReg.setComp_id(negativCritGerRegPK);
						negativCritGerReg.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(negativCritGerReg);

						indexGerenciaRegional++;
					}
				}
			}

			// Incluir Negativacao Unidade Negocio
			if (helper.getIdsUnidadeNegocio() != null && helper.getIdsUnidadeNegocio().length > 0) {
				String[] idsUnidadeNegocio = (String[]) helper.getIdsUnidadeNegocio();
				int indexUnidadeNegocio = 0;

				if (!idsUnidadeNegocio[indexUnidadeNegocio].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsUnidadeNegocio.length > indexUnidadeNegocio) {
						Integer idUnidadeNegocio = new Integer(idsUnidadeNegocio[indexUnidadeNegocio]);
						NegativCritUndNeg negativCritUndNeg = new NegativCritUndNeg();
						UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
						unidadeNegocio.setId(idUnidadeNegocio);

						NegativCritUndNegPK negativCritUndNegPK = new NegativCritUndNegPK();
						negativCritUndNegPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritUndNegPK.setUnidadeNegocio(unidadeNegocio);
						negativCritUndNeg.setComp_id(negativCritUndNegPK);
						negativCritUndNeg.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(negativCritUndNeg);

						indexUnidadeNegocio++;
					}
				}
			}

			// Incluir Negativacao Elo Polo
			if (helper.getIdsEloPolo() != null && helper.getIdsEloPolo().length > 0) {
				String[] idsEloPolo = (String[]) helper.getIdsEloPolo();
				int indexEloPolo = 0;

				if (!idsEloPolo[indexEloPolo].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsEloPolo.length > indexEloPolo) {
						Integer idEloPolo = new Integer(idsEloPolo[indexEloPolo]);
						NegativCritElo negativCritElo = new NegativCritElo();
						Localidade elo = new Localidade();
						elo.setId(idEloPolo);

						NegativCritEloPK negativCritEloPK = new NegativCritEloPK();
						negativCritEloPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritEloPK.setLocalidade(elo);
						negativCritElo.setComp_id(negativCritEloPK);
						negativCritElo.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(negativCritElo);

						indexEloPolo++;
					}
				}
			}

			// Incluir Negativacao Criterio Negativador Retorno Motivo
			if (helper.getIdsMotivoRetorno() != null && helper.getIdsMotivoRetorno().length > 0) {
				String[] idsMotivoRetorno = (String[]) helper.getIdsMotivoRetorno();
				int indexMotivoRetorno = 0;

				if (!idsMotivoRetorno[indexMotivoRetorno].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsMotivoRetorno.length > indexMotivoRetorno) {

						Integer idMotivoRetorno = new Integer(idsMotivoRetorno[indexMotivoRetorno]);
						NegativCritNegRetMot negativCritNegRetMot = new NegativCritNegRetMot();

						NegativadorRetornoMotivo negativadorRetornoMotivo = new NegativadorRetornoMotivo();
						negativadorRetornoMotivo.setId(idMotivoRetorno);
						negativCritNegRetMot.setNegativadorRetornoMotivo(negativadorRetornoMotivo);

						negativCritNegRetMot.setNegativacaoCriterio(negativacaoCriterio);
						negativCritNegRetMot.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(negativCritNegRetMot);

						indexMotivoRetorno++;
					}
				}
			}

			retorno = idNegativacaoComandoGerado;

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 *
	 * Ponto inicial do caso de uso de Executar Comando de Negativa��o [UC0687]
	 * Executar Comando de Negativa��o Fluxo Principal
	 *
	 * @author Ana Maria
	 * @date 27/03/2008
	 */
	/*
	 * public void executaComandoNegativacao(Integer idFuncionalidadeIniciada)
	 * throws ControladorException {
	 * 
	 * // ------------------------- // // Registrar o in�cio do processamento da
	 * Unidade de // Processamento // do Batch // // -------------------------
	 * int idUnidadeIniciada = 0; idUnidadeIniciada = getControladorBatch()
	 * .iniciarUnidadeProcessamentoBatch( idFuncionalidadeIniciada,
	 * UnidadeProcessamento.FUNCIONALIDADE,0); try { //1.0 NegativacaoComando nc
	 * = repositorioSpcSerasa.consultarNegativacaoComandadoParaExecutar(); if
	 * (nc != null && !nc.equals("")) { int quantidadeImoveis = 0; //3.0 //3.1
	 * quantidadeImoveis = quantidadeImoveis +
	 * this.gerarMovimentoInclusaoNegativacao(nc.getId(), "POR CRITERIO", null,
	 * nc.getNegativador().getId(), 0, null, null);
	 * 
	 * 
	 * if (quantidadeImoveis == 0 ) {
	 * getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
	 * idUnidadeIniciada, true); throw new ControladorException(
	 * "atencao.comando.negativacao.nenhum.imovel.stisfaz.criterio"); } } else {
	 * getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
	 * idUnidadeIniciada, true); throw new ControladorException(
	 * "atencao.comando.negativacao.vazio.para.executar"); }
	 * getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
	 * idUnidadeIniciada, false);
	 * 
	 * } catch (Exception ex) {
	 * 
	 * getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
	 * idUnidadeIniciada, true); //sessionContext.setRollbackOnly(); throw new
	 * ControladorException("erro.sistema", ex); } }
	 */

	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio>
	 *
	 * @author Desenvolvedor 02
	 * @date 08/05/2008
	 *
	 * @return
	 * @throws ControladorException
	 */
	public List consultarLocalidadeParaGerarResumoDiarioNegativacao() throws ControladorException {

		List retorno = null;

		try {
			retorno = repositorioSpcSerasa.consultarLocalidadeParaGerarResumoDiarioNegativacao();
		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 *
	 * Consulta as rotas para a geracao do resumo diario da negativacao [UC0688]
	 * Gerar Resumo Di�rio da Negativa��o Fluxo principal Item 1.0
	 *
	 * @author Francisco do Nascimento
	 * @date 03/02/2009
	 */
	public List consultarRotasParaGerarResumoDiarioNegativacao() throws ControladorException {

		List retorno = null;

		try {
			retorno = repositorioSpcSerasa.consultarRotasParaGerarResumoDiarioNegativacao();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio>
	 *
	 * @author Desenvolvedor 02
	 * @date 08/05/2008
	 *
	 * @param idLocalidade
	 * @return
	 * @throws ControladorException
	 */
	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(Integer idLocalidade) throws ControladorException {

		List retorno = null;

		try {
			retorno = repositorioSpcSerasa.consultarNegativacaoParaGerarResumoDiarioNegativacao(idLocalidade);
		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 *
	 * Apaga todos os ResumoNegativacao [UC0688] Gerar Resumo Di�rio da
	 * Negativa��o Fluxo principal Item 2.0
	 *
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public void apagarResumoNegativacao() throws ControladorException {

		try {
			repositorioSpcSerasa.apagarResumoNegativacao();
		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 *
	 * Apaga todos os ResumoNegativacao [UC0688] Gerar Resumo Di�rio da
	 * Negativa��o Fluxo principal Item 2.0
	 *
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public void apagarResumoNegativacao(Integer numeroPenultimaExecResumoNegat) throws ControladorException {

		try {
			repositorioSpcSerasa.apagarResumoNegativacao(numeroPenultimaExecResumoNegat);
		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 *
	 * Ponto inicial do caso de uso de Gerar Resumo Di�rio da Negativa��o
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o fluxo principal
	 *
	 * @author Thiago Toscano,Vivianne Sousa
	 * @date 07/01/2008,10/03/2010
	 */
	public void gerarResumoDiarioNegativacao(Integer idFuncionalidadeIniciada, Integer idSetor)
			throws ControladorException {

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.ROTA, idSetor);

		try {

			int quantidadeInicio = 0;
			int quantidadeMaxima = 50;

			boolean flagFimPesquisa = false;

			while (!flagFimPesquisa) {

				Collection coll = repositorioSpcSerasa.consultarNegativadorMovimentoReg(idSetor, quantidadeInicio,
						quantidadeMaxima);

				if (coll != null) {

					if (coll.size() < quantidadeMaxima) {
						flagFimPesquisa = true;
					}

					Iterator it = coll.iterator();
					while (it.hasNext()) {

						Object[] dadosNmr = (Object[]) it.next();
						NegativadorMovimentoReg nmr = new NegativadorMovimentoReg();

						nmr.setId((Integer) dadosNmr[0]);
						if (dadosNmr[1] != null) {
							nmr.setCodigoExclusaoTipo((Integer) dadosNmr[1]);
						}
						if (dadosNmr[2] != null) {
							nmr.setValorDebito((BigDecimal) dadosNmr[2]);
						}

						System.out.println("-------------- NMR : " + nmr.getId());

						Object[] obj = new Object[10];

						// 2.2 Processa os itens da negativa��o
						// [SB0001 Processar Itens da Negativa��o].
						Collection collNegativadorMovimentoRegItem = processarItemNegativacao(nmr, obj);
						Short indicadorSituacaoDefinitiva = (Short) obj[9];

						// 2.4
						// [SB0002 Determinar Situa��o Predominante do D�bito da
						// Negativa��o].
						// retornoSituacaoPredominanteDebitoNegativacao
						Object[] rspdn = determinarSituacaoPredominanteDebitoNegativacao(nmr,
								collNegativadorMovimentoRegItem, obj);

						CobrancaDebitoSituacao novoCDS = (CobrancaDebitoSituacao) rspdn[0];

						// 2.5
						if (novoCDS.getId() != null) {
							repositorioSpcSerasa.atualizarNegativadorMovimentoReg(novoCDS.getId(), (Date) rspdn[1],
									indicadorSituacaoDefinitiva, ConstantesSistema.NAO, nmr.getId());

						} else {

							System.out.println("************************* ENTROU ALGUMA VEZ!!!");
							repositorioSpcSerasa.atualizarNegativadorMovimentoReg(indicadorSituacaoDefinitiva,
									ConstantesSistema.NAO, nmr.getId());

						}

					}

				} else {
					flagFimPesquisa = true;
				}

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Throwable ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Processa os itens de uma NegativadorMovimentoReg [UC0688] Gerar Resumo
	 * Di�rio da Negativa��o [SB0001] Processar Itens da Negativa��o
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 *
	 * @param nmr
	 * @param Object
	 *            []
	 * 
	 *            Integer quantidadeNegativacao = 0; Integer
	 *            quantidadeNegativacaoPendente = 0; Integer
	 *            quantidadeNegativacaoPago = 0; Integer
	 *            quantidadeNegativacaoParcelado = 0; Integer
	 *            quantidadeNegativacaoCancelado = 0;
	 *
	 *            BigDecimal valorNegativacaoPendente = new BigDecimal(0);
	 *            BigDecimal valorNegativacaoPago = new BigDecimal(0);
	 *            BigDecimal valorNegativacaoParcelado = new BigDecimal(0);
	 *            BigDecimal valorNegativacaoCancelado = new BigDecimal(0);
	 *
	 * @return Collection de NegativadorMovimentoRegItem
	 *
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private Collection processarItemNegativacao(NegativadorMovimentoReg nmr, Object[] obj) throws ControladorException {

		try {

			Integer quantidadeNegativacao = 0;
			Integer quantidadeNegativacaoPendente = 0;
			Integer quantidadeNegativacaoPago = 0;
			Integer quantidadeNegativacaoParcelado = 0;
			Integer quantidadeNegativacaoCancelado = 0;

			BigDecimal valorNegativacaoPendente = new BigDecimal(0);
			BigDecimal valorNegativacaoPago = new BigDecimal(0);
			BigDecimal valorNegativacaoParcelado = new BigDecimal(0);
			BigDecimal valorNegativacaoCancelado = new BigDecimal(0);
			Short indicadorSituacaoDefinitiva = ConstantesSistema.SIM;

			Collection coll = this.repositorioSpcSerasa.consultarNegativadorMovimentoRegItem(nmr.getId());
			Collection collectionNegMovRegItem = new ArrayList<NegativadorMovimentoRegItem>();

			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				// 2.0
				while (it.hasNext()) {

					Object[] dadosNegMovRegItem = (Object[]) it.next();

					NegativadorMovimentoRegItem nmri = new NegativadorMovimentoRegItem();
					nmri.setNegativadorMovimentoReg(nmr);
					if (dadosNegMovRegItem[0] != null) {
						nmri.setId((Integer) dadosNegMovRegItem[0]);
					}
					if (dadosNegMovRegItem[1] != null) {
						CobrancaDebitoSituacao cds = new CobrancaDebitoSituacao();
						cds.setId((Integer) dadosNegMovRegItem[1]);
						nmri.setCobrancaDebitoSituacao(cds);
					}
					if (dadosNegMovRegItem[2] != null) {
						CobrancaDebitoSituacao cds = new CobrancaDebitoSituacao();
						cds.setId((Integer) dadosNegMovRegItem[2]);
						nmri.setCobrancaDebitoSituacaoAposExclusao(cds);
					}
					if (dadosNegMovRegItem[3] != null) {
						nmri.setIndicadorSituacaoDefinitiva((Short) dadosNegMovRegItem[3]);
					}
					if (dadosNegMovRegItem[4] != null) {
						nmri.setValorDebito((BigDecimal) dadosNegMovRegItem[4]);
					}
					if (dadosNegMovRegItem[5] != null) {
						ContaGeral conta = new ContaGeral();
						conta.setId((Integer) dadosNegMovRegItem[5]);
						conta.setIndicadorHistorico((Short) dadosNegMovRegItem[6]);
						nmri.setContaGeral((ContaGeral) conta);
					}
					if (dadosNegMovRegItem[7] != null) {
						GuiaPagamentoGeral guiaPag = new GuiaPagamentoGeral();
						guiaPag.setId((Integer) dadosNegMovRegItem[7]);
						guiaPag.setIndicadorHistorico((Short) dadosNegMovRegItem[8]);
						nmri.setGuiaPagamentoGeral((GuiaPagamentoGeral) guiaPag);
					}
					if (dadosNegMovRegItem[9] != null) {
						nmri.setDataSituacaoDebito((Date) dadosNegMovRegItem[9]);
					}
					if (dadosNegMovRegItem[10] != null) {
						nmri.setDataSituacaoDebitoAposExclusao((Date) dadosNegMovRegItem[10]);
					}
					if (dadosNegMovRegItem[11] != null) {
						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId((Integer) dadosNegMovRegItem[11]);
						nmri.setDocumentoTipo(documentoTipo);
					}

					collectionNegMovRegItem.add(nmri);

					CobrancaDebitoSituacao cds = null;
					quantidadeNegativacao = quantidadeNegativacao + 1;

					if (nmri != null) {
						// 2.1
						cds = nmri.getCobrancaDebitoSituacao();

						if (cds.getId().equals(CobrancaDebitoSituacao.PENDENTE)) {

							valorNegativacaoPendente = valorNegativacaoPendente.add(nmri.getValorDebito());
							quantidadeNegativacaoPendente = quantidadeNegativacaoPendente + 1;

						} else if (cds.getId().equals(CobrancaDebitoSituacao.PAGO)) {

							valorNegativacaoPago = valorNegativacaoPago.add(nmri.getValorDebito());
							quantidadeNegativacaoPago = quantidadeNegativacaoPago + 1;

						} else if (cds.getId().equals(CobrancaDebitoSituacao.PARCELADO)) {

							valorNegativacaoParcelado = valorNegativacaoParcelado.add(nmri.getValorDebito());
							quantidadeNegativacaoParcelado = quantidadeNegativacaoParcelado + 1;

						} else if (cds.getId().equals(CobrancaDebitoSituacao.CANCELADO)) {

							valorNegativacaoCancelado = valorNegativacaoCancelado.add(nmri.getValorDebito());
							quantidadeNegativacaoCancelado = quantidadeNegativacaoCancelado + 1;

						}

						if (nmri.getIndicadorSituacaoDefinitiva() != 1) {
							indicadorSituacaoDefinitiva = ConstantesSistema.NAO;
						}

					}

				}
			}
			obj[0] = quantidadeNegativacao;
			obj[1] = quantidadeNegativacaoPendente;
			obj[2] = quantidadeNegativacaoPago;
			obj[3] = quantidadeNegativacaoParcelado;
			obj[4] = quantidadeNegativacaoCancelado;
			obj[5] = valorNegativacaoPendente;
			obj[6] = valorNegativacaoPago;
			obj[7] = valorNegativacaoParcelado;
			obj[8] = valorNegativacaoCancelado;
			obj[9] = indicadorSituacaoDefinitiva;

			return collectionNegMovRegItem;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * M�todo que determina a cobrancaDebitoSituacao do
	 * NegativadorMovimentoRegItem [UC0688] Gerar Resumo Di�rio da Negativa��o
	 * [SB0002] Determinar Situa��o do D�bito do Item da Negativa��o
	 *
	 * @author Thiago Toscano
	 * @date 08/01/2008
	 *
	 * @param nmri
	 * @return [0]CobrancaDebitoSituacao [1]Data da Situacao do Debito do Item
	 *         da Negativacao [2]True - indicando se est� no historico; False -
	 *         indicando se est� n�ohistorico
	 * @throws ErroRepositorioException
	 */
	private Object[] determinarSituacaoDebitoItemNegativacao(NegativadorMovimentoRegItem nmri)
			throws ControladorException {

		try {

			DebitoCreditoSituacao dcs = null;

			CobrancaDebitoSituacao cdsRetorno = null;

			Date dataSituacaoRetorno = null;
			Boolean estaNoHistoricoRetorno = null;
			Integer idConta = null;
			Date dataParcelamentoItem = null;
			BigDecimal valorEntradaParcelamento = null;
			BigDecimal valorPagoRetorno = null;

			BigDecimal valorParcelamento = null;
			Short numeroPrestacoesParcelamento = null;
			Integer identificadorParcelamento = null;
			BigDecimal valorCancelamentoRetorno = null;

			Boolean estaDataSituacaoRetorno = false;
			Date dataCancelamentoItem = null;
			BigDecimal valorConta = BigDecimal.ZERO;
			// Boolean retornar = false;

			// 1.1
			if (nmri != null && nmri.getContaGeral() != null) {
				// 1.1.1
				ContaGeral cg = nmri.getContaGeral();

				idConta = cg.getId();
				// System.out.println(" ### Proc135.conta = " + idConta +
				// ", icHist=" + cg.getIndicadorHistorico());

				CobrancaDebitoSituacao cobrancaDebitoSituacaoNMRI = nmri.getCobrancaDebitoSituacao();
				CobrancaDebitoSituacao cobrancaDebitoSituacaoNMR = nmri.getNegativadorMovimentoReg()
						.getCobrancaDebitoSituacao();

				// 1.1.2
				if (cg.getIndicadorHistorico() == 1) {
					// 1.1.2.1
					ContaHistorico ch = this.repositorioSpcSerasa.consultaContaHistorico(idConta);

					// 1.1.2.2
					// gcom.faturamento.debito.DebitoCreditoSituacao
					if (ch.getDebitoCreditoSituacaoAtual().getId()
							.equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)) {

						// 1.1.2.2.1
						Imovel imovelItem = ch.getImovel();
						// 1.1.2.2.2
						int anoMesReferenciaConta = ch.getAnoMesReferenciaConta();

						// 1.1.2.2.3
						ContaHistorico chma = this.repositorioSpcSerasa.consultaContaHistoricoMaisAtual(
								imovelItem.getId(), anoMesReferenciaConta);

						// Conta mais Atual
						Conta cma = this.repositorioSpcSerasa.consultaContaMaisAtual(imovelItem.getId(),
								anoMesReferenciaConta);

						// 1.1.2.2.4
						// 1.1.2.2.5
						// 1.1.2.2.6
						// 1.1.2.2.7
						if (chma != null && chma.getId() != null) {
							// esta no historico
							// 1.1.2.2.5
							idConta = chma.getId();
							dcs = chma.getDebitoCreditoSituacaoAtual();

							if (chma.getDataCancelamento() == null) {
								dataCancelamentoItem = chma.getUltimaAlteracao();
							} else {
								dataCancelamentoItem = chma.getDataCancelamento();
							}
							valorConta = chma.getValorTotal();

							estaNoHistoricoRetorno = true;
						} else if (cma != null && cma.getId() != null) {
							// nao esta no historico
							// 1.1.2.2.5
							if (cma == null) {
								// Se ContaMaisAtual for null pego a Conta do
								// Item.
								cma = this.repositorioSpcSerasa.consultaConta(idConta);
							}
							idConta = cma.getId();
							dcs = cma.getDebitoCreditoSituacaoAtual();

							if (cma.getDataCancelamento() == null) {
								dataCancelamentoItem = cma.getUltimaAlteracao();
							} else {
								dataCancelamentoItem = cma.getDataCancelamento();
							}
							valorConta = cma.getValorTotal();
							estaNoHistoricoRetorno = false;
						} else {
							idConta = ch.getId();

							// Pego do Historico normal, sem ser ch mais atual
							// se cma for null
							dcs = ch.getDebitoCreditoSituacaoAtual();
							// O que est� acontecendo � que
							// ch.getDataCancelamento() � null

							if (ch.getDataCancelamento() == null) {
								dataCancelamentoItem = ch.getUltimaAlteracao();
							} else {
								dataCancelamentoItem = ch.getDataCancelamento();
							}
							valorConta = ch.getValorTotal();
							estaNoHistoricoRetorno = true;
						}
						// 1.1.2.3
					} else {

						// 1.1.2.3.1
						dcs = ch.getDebitoCreditoSituacaoAtual();
						// 1.1.2.3.2
						if (ch.getDataCancelamento() == null) {
							dataCancelamentoItem = ch.getUltimaAlteracao();
						} else {
							dataCancelamentoItem = ch.getDataCancelamento();
						}
						// 1.1.2.3.3
						estaNoHistoricoRetorno = true;
						// 1.1.2.3.4
						valorConta = ch.getValorTotal();
					}
					// 1.1.3
				} else {

					estaNoHistoricoRetorno = false;
					// 1.1.3.1
					Conta c = this.repositorioSpcSerasa.consultaConta(idConta);

					// 1.1.3.2
					if (c.getDebitoCreditoSituacaoAtual().getId()
							.equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)) {

						// 1.1.3.2.1
						Conta cma = this.repositorioSpcSerasa.consultaContaMaisAtual(c.getImovel().getId(),
								c.getReferencia());

						// 1.1.3.2.2
						if (cma != null) {
							idConta = cma.getId();
							c = cma;
						}

					}
					// 1.1.3.3
					dcs = c.getDebitoCreditoSituacaoAtual();
					// 1.1.3.4
					if (c.getDataCancelamento() == null) {
						dataCancelamentoItem = c.getUltimaAlteracao();
					} else {
						dataCancelamentoItem = c.getDataCancelamento();
					}
					valorConta = c.getValorTotal();
				}

				// 1.1.4
				// alterado por Vivianne Sousa - 02/04/2009 - analista:Fatima
				// CRC1599
				if (dcs.getId().equals(DebitoCreditoSituacao.PARCELADA)
						|| dcs.getId().equals(DebitoCreditoSituacao.ENTRADA_DE_PARCELAMENTO)
						|| (cobrancaDebitoSituacaoNMR.getId().equals(CobrancaDebitoSituacao.PARCELADO) && cobrancaDebitoSituacaoNMRI
								.getId().equals(CobrancaDebitoSituacao.PARCELADO))) {

					Parcelamento parcelamento = null;
					Date dataEnvio = nmri.getNegativadorMovimentoReg().getNegativadorMovimento().getDataEnvio();

					if (dcs.getId().equals(DebitoCreditoSituacao.PARCELADA)
							|| dcs.getId().equals(DebitoCreditoSituacao.ENTRADA_DE_PARCELAMENTO)) {
						parcelamento = this.repositorioSpcSerasa.consultaParcelamentoConta(idConta,
								ParcelamentoSituacao.NORMAL, dataEnvio);
					} else {
						parcelamento = this.repositorioSpcSerasa.consultaParcelamentoConta(idConta,
								ParcelamentoSituacao.DESFEITO, dataEnvio);

						if (parcelamento == null) {
							parcelamento = this.repositorioSpcSerasa.consultaParcelamentoConta(nmri.getContaGeral()
									.getId(), ParcelamentoSituacao.DESFEITO, dataEnvio);

						}
					}

					if (parcelamento != null) {
						if (parcelamento.getParcelamento() != null) {
							dataParcelamentoItem = parcelamento.getParcelamento();
						}

						if (parcelamento.getValorEntrada() != null) {
							valorEntradaParcelamento = parcelamento.getValorEntrada();
						}

						valorParcelamento = parcelamento.getValorParcelado();

						if (parcelamento.getNumeroPrestacoes() != null) {
							numeroPrestacoesParcelamento = parcelamento.getNumeroPrestacoes();
						}

						identificadorParcelamento = parcelamento.getId();
					}
				}
				// 1.2
			} else if (nmri != null && nmri.getGuiaPagamentoGeral() != null
					&& nmri.getGuiaPagamentoGeral().getId() != null) {
				// 1.2.1
				GuiaPagamentoGeral gpg = nmri.getGuiaPagamentoGeral();
				Integer codigoGuiaPagamentoGeral = gpg.getId();
				// 1.2.1
				if (gpg.getIndicadorHistorico() == 1) {
					// 1.2.1.1
					estaNoHistoricoRetorno = true;

					GuiaPagamentoHistorico gph = repositorioSpcSerasa
							.consultaGuiaPagamentoHistorico(codigoGuiaPagamentoGeral);

					// 1.2.1.2
					dcs = gph.getDebitoCreditoSituacaoByDcstIdatual();
					// 1.2.1.3
					dataCancelamentoItem = gph.getUltimaAlteracao();

					// 1.2.2
				} else {
					estaNoHistoricoRetorno = false;

					GuiaPagamento gp = repositorioSpcSerasa.consultaGuiaPagamento(codigoGuiaPagamentoGeral);

					// 1.2.2.1
					dcs = gp.getDebitoCreditoSituacaoAtual();
					// 1.2.2.2
					dataCancelamentoItem = gp.getUltimaAlteracao();

				}
				// 1.2.3
				// alterado por Vivianne Sousa - 02/04/2009 - analista:Fatima
				// CRC1599
				if (dcs.getId().equals(DebitoCreditoSituacao.PARCELADA)
						|| dcs.getId().equals(DebitoCreditoSituacao.ENTRADA_DE_PARCELAMENTO)) {

					Parcelamento parcelamento = this.repositorioSpcSerasa
							.consultaParcelamentoGuiaPagamento(gpg.getId());

					if (parcelamento.getParcelamento() != null) {
						dataParcelamentoItem = parcelamento.getParcelamento();
					}

					if (parcelamento.getValorEntrada() != null) {
						valorEntradaParcelamento = parcelamento.getValorEntrada();
					}

					valorParcelamento = parcelamento.getValorParcelado();

					if (parcelamento.getNumeroPrestacoes() != null) {
						numeroPrestacoesParcelamento = parcelamento.getNumeroPrestacoes();
					}

					identificadorParcelamento = parcelamento.getId();

				}
			}

			// 2
			if (nmri != null
					&& nmri.getContaGeral() != null
					&& dcs != null
					&& (dcs.getId().equals(DebitoCreditoSituacao.NORMAL)
							|| dcs.getId().equals(DebitoCreditoSituacao.RETIFICADA) || dcs.getId().equals(
							DebitoCreditoSituacao.INCLUIDA)) && valorConta.equals(new BigDecimal("0.00"))) {
				// 2.1
				valorPagoRetorno = BigDecimal.ZERO;
				// 2.2
				cdsRetorno = new CobrancaDebitoSituacao();
				cdsRetorno.setId(CobrancaDebitoSituacao.PAGO);
				// 2.3
				dataSituacaoRetorno = new Date();
				estaDataSituacaoRetorno = true;

				// 3.0
			} else if (dcs != null
					&& (dcs.getId().equals(DebitoCreditoSituacao.PARCELADA) || dcs.getId().equals(
							DebitoCreditoSituacao.ENTRADA_DE_PARCELAMENTO))) {
				// 3.1
				cdsRetorno = new CobrancaDebitoSituacao();
				cdsRetorno.setId(CobrancaDebitoSituacao.PARCELADO);

				// 3.2
				dataSituacaoRetorno = dataParcelamentoItem;
				estaDataSituacaoRetorno = true;

				// 4.0
			} else if (dcs != null
					&& (dcs.getId().equals(DebitoCreditoSituacao.CANCELADA) || dcs.getId().equals(
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO))) {
				// 4.1
				cdsRetorno = new CobrancaDebitoSituacao();
				cdsRetorno.setId(CobrancaDebitoSituacao.CANCELADO);

				// 4.2
				dataSituacaoRetorno = dataCancelamentoItem;
				estaDataSituacaoRetorno = true;

				// 4.3
				valorCancelamentoRetorno = nmri.getValorDebito();

				// 5.0
			} else {
				// ------------------------
				// CRC996
				// @Autor : Yara T. Souza
				// @Data : 14/01/2009
				// -----------------------

				Collection pagamentos = new ArrayList();
				// 5.1
				if (nmri != null && nmri.getContaGeral() != null) {
					// 5.1.1
					if (estaNoHistoricoRetorno) {

						ContaHistorico ch = this.repositorioSpcSerasa.consultaContaHistorico(idConta);

						pagamentos = this.repositorioSpcSerasa.consultarPagamentoHistorcioDoItem(ch, null);

						// 5.1.2
					} else {
						pagamentos = this.repositorioSpcSerasa.consultarPagamentoDoItem(idConta, null);

					}
					// 5.2
				} else if (nmri != null && nmri.getGuiaPagamentoGeral() != null
						&& nmri.getGuiaPagamentoGeral().getId() != null) {
					// 5.2.1
					if (nmri != null && nmri.getGuiaPagamentoGeral().getIndicadorHistorico() == 1) {

						// consultar o pagamento_historico com o
						GuiaPagamentoHistorico gph = this.repositorioSpcSerasa.consultaGuiaPagamentoHistorico(nmri
								.getGuiaPagamentoGeral().getId());

						pagamentos = this.repositorioSpcSerasa.consultarPagamentoHistorcioDoItem(null, gph);

						// 5.2.1
					} else {
						pagamentos = this.repositorioSpcSerasa.consultarPagamentoDoItem(null, nmri
								.getGuiaPagamentoGeral().getId());
					}
				}

				// 6.0
				if (pagamentos != null && !pagamentos.isEmpty()) {
					// 6.1
					cdsRetorno = new CobrancaDebitoSituacao();
					cdsRetorno.setId(CobrancaDebitoSituacao.PAGO);

					// 6.2
					// S� pode vir um pagamento.
					dataSituacaoRetorno = null;

					Iterator it = pagamentos.iterator();
					while (it.hasNext()) {

						Date dataPagamento = null;

						Object obj = it.next();
						if (obj instanceof Pagamento) {
							Pagamento p = (Pagamento) obj;
							dataPagamento = p.getDataPagamento();
							valorPagoRetorno = p.getValorPagamento();
						} else if (obj instanceof PagamentoHistorico) {
							PagamentoHistorico ph = (PagamentoHistorico) obj;
							dataPagamento = ph.getDataPagamento();
							valorPagoRetorno = ph.getValorPagamento();
						}

						if (dataSituacaoRetorno == null) {
							estaDataSituacaoRetorno = true;
							dataSituacaoRetorno = dataPagamento;
						} else if (dataSituacaoRetorno.getTime() > dataPagamento.getTime()) {
							dataSituacaoRetorno = dataPagamento;
							estaDataSituacaoRetorno = true;
						}

					}

				}

			}

			if (estaDataSituacaoRetorno == false) {
				// 7.0
				// ---------------------------------------------------------
				cdsRetorno = new CobrancaDebitoSituacao();
				cdsRetorno.setId(CobrancaDebitoSituacao.PENDENTE);
				dataSituacaoRetorno = new Date();
				// ---------------------------------------------------------

			}

			Object[] obj = new Object[9];
			obj[0] = cdsRetorno;
			obj[1] = dataSituacaoRetorno;
			obj[2] = estaNoHistoricoRetorno;
			obj[3] = valorEntradaParcelamento;
			obj[4] = valorPagoRetorno;

			obj[5] = valorParcelamento;
			obj[6] = numeroPrestacoesParcelamento;
			obj[7] = identificadorParcelamento;
			obj[8] = valorCancelamentoRetorno;

			return obj;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * atualiza o NegativadorMovimentoRegItem [UC0688] Gerar Resumo Di�rio da
	 * Negativa��o [SB0003] Atualizar Item da Negativa��o
	 *
	 * @author Thiago Toscano
	 * @date 08/01/2008
	 *
	 * @param nmri
	 * @return
	 * @throws ErroRepositorioException
	 */
	// CRC2725 - comentado por Vivianne Sousa - 30/09/2009 analista:Fatima
	// private void atualizarItemNegativacao(NegativadorMovimentoRegItem nmri,
	// CobrancaDebitoSituacao cds,
	// Date dataCobrancaDebtitoSituacao,BigDecimal valorPago, boolean
	// estahNoHistorico,
	// BigDecimal valorNegativacaoCancelamento) throws ErroRepositorioException
	// {
	//
	// //Caso a negativa��o n�o esteja exclu�da.
	// if (nmri!= null &&
	// nmri.getNegativadorMovimentoReg().getCodigoExclusaoTipo() == null) {
	//
	// if (nmri.getCobrancaDebitoSituacao()!= null &&
	// !nmri.getCobrancaDebitoSituacao().getId().equals(cds.getId())) {
	// nmri.setCobrancaDebitoSituacao(cds);
	// nmri.setDataSituacaoDebito(dataCobrancaDebtitoSituacao);
	// }
	// if (estahNoHistorico) {
	// nmri.setIndicadorSituacaoDefinitiva((short)1);
	// }
	// //------------------------------------------------------------------
	// //CRC996
	// //Autor : Yara T. Souza
	// //data : 15/01/2009
	// //------------------------------------------------------------------
	// //Caso Sit. do D�bito do Item Negativa��o corresponda a d�bito pago.
	// if (cds!= null && cds.getId().equals(CobrancaDebitoSituacao.PAGO) &&
	// valorPago!= null) {
	// nmri.setValorPago(valorPago);
	// }else{
	// nmri.setValorPago(null);
	// }
	//
	// //Vivianne Sousa - 03/04/2009 CRC1599
	// //Caso Sit. do D�bito do Item Negativa��o corresponda a d�bito cancelado
	// if (cds!= null && cds.getId().equals(CobrancaDebitoSituacao.CANCELADO) &&
	// valorNegativacaoCancelamento!= null) {
	// nmri.setValorCancelado(valorNegativacaoCancelamento);
	// }else{
	// nmri.setValorCancelado(null);
	// }
	//
	// } else {
	//
	// nmri.setCobrancaDebitoSituacaoAposExclusao(cds);
	// nmri.setDataSituacaoDebitoAposExclusao(dataCobrancaDebtitoSituacao);
	//
	// if (estahNoHistorico) {
	// nmri.setIndicadorSituacaoDefinitiva((short)1);
	// }
	// //------------------------------------------------------------------
	// //CRC996
	// //Autor : Yara T. Souza
	// //data : 15/01/2009
	// //------------------------------------------------------------------
	// //Caso Sit. do D�bito do Item Negativa��o corresponda a d�bito pago.
	// if (cds!= null && cds.getId().equals(CobrancaDebitoSituacao.PAGO) &&
	// valorPago!= null) {
	// nmri.setValorPago(valorPago);
	// }else{
	// nmri.setValorPago(null);
	// }
	//
	// //Vivianne Sousa - 03/04/2009 CRC1599
	// //Caso Sit. do D�bito do Item Negativa��o corresponda a d�bito cancelado
	// if (cds!= null && cds.getId().equals(CobrancaDebitoSituacao.CANCELADO) &&
	// valorNegativacaoCancelamento!= null) {
	// nmri.setValorCancelado(valorNegativacaoCancelamento);
	// }else{
	// nmri.setValorCancelado(null);
	// }
	// }
	//
	// if(nmri!= null){
	// nmri.setUltimaAlteracao(new Date());
	// RepositorioUtilHBM.getInstancia().atualizar(nmri);
	// }
	//
	// }

	/**
	 * Processa os itens de uma NegativadorMovimentoReg [UC0688] Gerar Resumo
	 * Di�rio da Negativa��o [SB0002] Determinar Situa��o do D�bito da
	 * Negativa��o
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 *
	 *
	 * @param nmr
	 *            obj[0] = quantidadeNegativacao; obj[1] =
	 *            quantidadeNegativacaoPendente; obj[2] =
	 *            quantidadeNegativacaoPago; obj[3] =
	 *            quantidadeNegativacaoParcelado; obj[4] =
	 *            quantidadeNegativacaoCancelado; obj[5] =
	 *            valorNegativacaoPendente; obj[6] = valorNegativacaoPago;
	 *            obj[7] = valorNegativacaoParcelado; obj[8] =
	 *            valorNegativacaoCancelado;
	 * 
	 * @return Object[0] - CobrancaDebitoSituacao
	 * @return Object[1] - Data da Situacao Predominante do debito da
	 *         Negativa��o
	 * 
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private Object[] determinarSituacaoPredominanteDebitoNegativacao(NegativadorMovimentoReg nmr,
			Collection collNegativadorMovimentoRegItem, Object[] obj) throws ControladorException,
			ErroRepositorioException {
		CobrancaDebitoSituacao cbs = null;
		Date data = null;

		boolean temItemDependente = false;

		NegativadorMovimentoRegItem nmri = new NegativadorMovimentoRegItem();
		// 1.0
		if (collNegativadorMovimentoRegItem != null) {
			Iterator it = collNegativadorMovimentoRegItem.iterator();
			while (it.hasNext()) {

				nmri = (NegativadorMovimentoRegItem) it.next();

				// Se foi excluido, tem que pegar o ap�s a exclus�o .
				if (nmr.getCodigoExclusaoTipo() != null) {
					if (nmri != null
							&& nmri.getCobrancaDebitoSituacaoAposExclusao().getId()
									.equals(CobrancaDebitoSituacao.PENDENTE)) {
						temItemDependente = true;
						break;
					}
				} else {
					if (nmri != null
							&& nmri.getCobrancaDebitoSituacao().getId().equals(CobrancaDebitoSituacao.PENDENTE)) {
						temItemDependente = true;
						break;
					}
				}

			}
		}

		// 1.1
		if (temItemDependente) {
			// 1.1
			cbs = new CobrancaDebitoSituacao();
			cbs.setId(CobrancaDebitoSituacao.PENDENTE);

			// 1.2
			data = new Date();

		} else {

			Integer quantidadeNegativacao = (Integer) obj[0];

			Integer quantidadeNegativacaoPago = (Integer) obj[2];
			Integer quantidadeNegativacaoParcelado = (Integer) obj[3];
			Integer quantidadeNegativacaoCancelado = (Integer) obj[4];
			// BigDecimal valorNegativacaoPendente = (BigDecimal)obj[5];

			BigDecimal valorNegativacaoPago = (BigDecimal) obj[6];
			BigDecimal valorNegativacaoParcelado = (BigDecimal) obj[7];
			BigDecimal valorNegativacaoCancelado = (BigDecimal) obj[8];

			// 2.0

			BigDecimal valorDebito = nmr.getValorDebito();
			BigDecimal porcentagem = new BigDecimal(0.7);
			BigDecimal valorMinimo = valorDebito.multiply(porcentagem);
			// 3.0

			double quantidadeMinima = quantidadeNegativacao * porcentagem.doubleValue();

			if (valorNegativacaoCancelado.doubleValue() >= valorMinimo.doubleValue()
					|| quantidadeNegativacaoCancelado.doubleValue() >= quantidadeMinima) {
				// 6.1
				cbs = new CobrancaDebitoSituacao();
				cbs.setId(CobrancaDebitoSituacao.CANCELADO);
				// 6.2
				data = repositorioSpcSerasa.getMaiorDataNegativadorMovimentoRegItem(cbs, nmr);

			} else if (valorNegativacaoParcelado.doubleValue() >= valorMinimo.doubleValue()
					|| quantidadeNegativacaoParcelado.doubleValue() >= quantidadeMinima) {
				// 5.1
				cbs = new CobrancaDebitoSituacao();
				cbs.setId(CobrancaDebitoSituacao.PARCELADO);
				// 5.2
				data = repositorioSpcSerasa.getMaiorDataNegativadorMovimentoRegItem(cbs, nmr);

			} else if (valorNegativacaoPago.doubleValue() >= valorMinimo.doubleValue()
					|| quantidadeNegativacaoPago.doubleValue() >= quantidadeMinima) {
				// 4.1
				cbs = new CobrancaDebitoSituacao();
				cbs.setId(CobrancaDebitoSituacao.PAGO);
				// 4.2
				data = repositorioSpcSerasa.getMaiorDataNegativadorMovimentoRegItem(cbs, nmr);

			} else {
				// 7.0
				cbs = new CobrancaDebitoSituacao();

				if (valorNegativacaoCancelado.doubleValue() >= valorNegativacaoPago.doubleValue()
						&& valorNegativacaoCancelado.doubleValue() >= valorNegativacaoParcelado.doubleValue()) {

					cbs = new CobrancaDebitoSituacao();
					cbs.setId(CobrancaDebitoSituacao.CANCELADO);

					data = repositorioSpcSerasa.getMaiorDataNegativadorMovimentoRegItem(cbs, nmr);

				}
				if (valorNegativacaoParcelado.doubleValue() >= valorNegativacaoPago.doubleValue()
						&& valorNegativacaoParcelado.doubleValue() >= valorNegativacaoCancelado.doubleValue()) {

					cbs = new CobrancaDebitoSituacao();
					cbs.setId(CobrancaDebitoSituacao.PARCELADO);

					data = repositorioSpcSerasa.getMaiorDataNegativadorMovimentoRegItem(cbs, nmr);

				}
				if (valorNegativacaoPago.doubleValue() >= valorNegativacaoParcelado.doubleValue()
						&& valorNegativacaoPago.doubleValue() >= valorNegativacaoCancelado.doubleValue()) {

					cbs = new CobrancaDebitoSituacao();
					cbs.setId(CobrancaDebitoSituacao.PAGO);

					data = repositorioSpcSerasa.getMaiorDataNegativadorMovimentoRegItem(cbs, nmr);

				}
			}

		}

		Object[] objResposta = new Object[2];
		objResposta[0] = cbs;
		objResposta[1] = data;

		return objResposta;
	}

	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 * 
	 * seleciona os im�veis enviados para a negativa��o e que foram efetivamente
	 * negativados e atualiza a situa��o de cobran�a dos referidos im�veis e a
	 * data de confirma��o da negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */
	public void determinarConfirmacaoDaNegativacao(Integer idFuncionalidadeIniciada, Integer idLocalidade)
			throws ControladorException {

		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {

			int quantidadeInicio = 0;
			int quantidadeMaxima = 100;

			boolean flagFimPesquisa = false;

			while (!flagFimPesquisa) {

				Collection coll = repositorioSpcSerasa.pesquisarNegativadorMovimentoReg(idLocalidade, quantidadeInicio,
						quantidadeMaxima);

				if (coll != null) {

					if (coll.size() < quantidadeMaxima) {
						flagFimPesquisa = true;
					}

					// if (coll.size() < quantidadeInicio) {
					// flagFimPesquisa = true;
					// } else {
					// quantidadeInicio = quantidadeInicio + quantidadeMaxima;
					// }

					Iterator it = coll.iterator();
					while (it.hasNext()) {

						Object[] dados = (Object[]) it.next();

						Integer idNegativadorMovimentoReg = null;
						Integer idImovel = null;
						Integer idNegativacaoImoveis = null;
						Integer idNegativador = null;
						Integer idClienteNegativadorMovimentoReg = null;
						short numeroPrazoInclusao = 0;
						Date dataProcessamentoEnvio = null;
						Integer codigoExclusaoTipo = null;
						Date dataExclusao = null;

						idNegativadorMovimentoReg = (Integer) dados[0];
						idImovel = (Integer) dados[1];
						idNegativacaoImoveis = (Integer) dados[2];
						idNegativador = (Integer) dados[3];
						if (dados[4] != null) {
							idClienteNegativadorMovimentoReg = (Integer) dados[4];
						}
						numeroPrazoInclusao = (Short) dados[5];
						dataProcessamentoEnvio = (Date) dados[6];

						if (dados[7] != null) {
							codigoExclusaoTipo = (Integer) dados[7];
						}

						if (dados[8] != null) {
							dataExclusao = (Date) dados[8];
						}
						// -------------------------------------------

						System.out.println("-------------- NMR : " + idNegativadorMovimentoReg);

						// Data de confirma��o recebe NGMV_DTPROCESSAMENTOENVIO
						// da tabela NEGATIVADOR_MOVIMENTO
						// mais NGCN_NNPRAZOINCLUSAO da tabela
						// NEGATIVADOR_CONTRATO mais 1 dia
						Date dataProcessamentoEnvioMaisNumeroPrazoInclusaoMaisUm = Util.adicionarNumeroDiasDeUmaData(
								dataProcessamentoEnvio, numeroPrazoInclusao);
						dataProcessamentoEnvioMaisNumeroPrazoInclusaoMaisUm = Util.adicionarNumeroDiasDeUmaData(
								dataProcessamentoEnvioMaisNumeroPrazoInclusaoMaisUm, 1);

						// 1.2. Atualizar a situa��o de cobran�a do im�vel na
						// tabela IMOVEL
						Integer idCobrancaSituacaoImovel = null;
						if (idNegativador.equals(Negativador.NEGATIVADOR_SPC)) {
							idCobrancaSituacaoImovel = CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NO_SPC;
						} else if (idNegativador.equals(Negativador.NEGATIVADOR_SERASA)) {
							idCobrancaSituacaoImovel = CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NA_SERASA;
						}
						// CRC3323 - comentado por Vivianne Sousa -
						// analista:Fatima Sampaio - 10/05/2010
						// getControladorImovel().atualizarSituacaoCobrancaImovel(idCobrancaSituacaoImovel,idImovel);

						// 1.3. Atualizar a situa��o de cobran�a do im�vel na
						// tabela IMOVEL_COBRANCA_SITUACAO
						Integer idCobrancaSituacaoImovelCobrancaSituacao = null;
						if (idNegativador.equals(Negativador.NEGATIVADOR_SPC)) {
							idCobrancaSituacaoImovelCobrancaSituacao = CobrancaSituacao.CARTA_ENVIADA_AO_SPC;
						} else if (idNegativador.equals(Negativador.NEGATIVADOR_SERASA)) {
							idCobrancaSituacaoImovelCobrancaSituacao = CobrancaSituacao.CARTA_ENVIADA_A_SERASA;
						}
						List collImovelCobrancaSituacao = this.repositorioSpcSerasa.consultarImovelCobrancaSituacao(
								idImovel, idCobrancaSituacaoImovelCobrancaSituacao);
						if (collImovelCobrancaSituacao != null && !collImovelCobrancaSituacao.isEmpty()) {
							Iterator iter = collImovelCobrancaSituacao.iterator();
							while (iter.hasNext()) {
								Integer idImovelCobrancaSituacao = (Integer) iter.next();
								getControladorImovel().atualizarDataRetiradaImovelSituacaoCobranca(
										idImovelCobrancaSituacao, dataProcessamentoEnvioMaisNumeroPrazoInclusaoMaisUm);
							}
						}

						// 1.4. Incluir a nova situa��o de cobran�a do im�vel na
						// tabela IMOVEL_COBRANCA_SITUACAO
						List idImovelCobrancaSituacaoNegativadoAutomaticamente = this.repositorioSpcSerasa
								.consultarImovelCobrancaSituacao(idImovel, idCobrancaSituacaoImovel);

						// verifica��o adicionada para permitir reiniciar o
						// batch
						if (idImovelCobrancaSituacaoNegativadoAutomaticamente == null
								|| idImovelCobrancaSituacaoNegativadoAutomaticamente.isEmpty()) {

							ImovelCobrancaSituacao ics = new ImovelCobrancaSituacao();
							CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
							Cliente cliente = new Cliente();
							Imovel imovel = new Imovel();
							cobrancaSituacao.setId(idCobrancaSituacaoImovel);
							imovel.setId(idImovel);
							ics.setImovel(imovel);
							ics.setDataImplantacaoCobranca(dataProcessamentoEnvioMaisNumeroPrazoInclusaoMaisUm);

							if (codigoExclusaoTipo != null) {

								ics.setDataRetiradaCobranca(dataExclusao);

								// [SB0002] � Atualizar Data da Retirada da
								// Situa��o Carta Enviada
								this.repositorioSpcSerasa.atualizarDataRetiradaSituacaoCartaEnviada(dataExclusao,
										idImovel);

							} else {
								ics.setDataRetiradaCobranca(null);
							}
							ics.setCobrancaSituacao(cobrancaSituacao);
							cliente.setId(idClienteNegativadorMovimentoReg);
							ics.setCliente(cliente);
							ics.setUltimaAlteracao(new Date());
							RepositorioUtilHBM.getInstancia().inserir(ics);
						}

						// 1.5. Atualizar a situa��o de cobran�a do im�vel na
						// tabela NEGATIVADOR_MOVIMENTO_REG
						repositorioSpcSerasa.atualizarSituacaoCobrancaNegativadorMovimentoReg(idCobrancaSituacaoImovel,
								idNegativadorMovimentoReg);

						// atualiza��o ficou para o final para permitir
						// reiniciar o batch
						// 1.1. Atualizar a tabela NEGATIVACAO_IMOVEIS
						if (idNegativacaoImoveis != null) {
							repositorioSpcSerasa.atualizarDataConfirmacaoNegativacaoImoveis(idNegativacaoImoveis,
									dataProcessamentoEnvioMaisNumeroPrazoInclusaoMaisUm);
						}
					}

				} else {
					flagFimPesquisa = true;
				}

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Throwable ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new ControladorException("erro.sistema", ex);
		}

	}

	// GERA REGISTRO HEADER SERASA **************
	// ********************************************
	// CAMPOS CONFORME LAYOUT DO UC0671 [SB0008] **
	// ********************************************
	public StringBuilder geraRegistroTipoHeaderSERASA(int numeroSequencialEnvio, int quantidadeRegistros) throws ControladorException {
		
		StringBuilder registroHeader = new StringBuilder();
		
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteResponsavelNegativacao");
		Collection pesquisaSistemaParametro = getControladorUtil().pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro) Util.retonarObjetoDeColecao(pesquisaSistemaParametro);
		
		// H.01 - C�digo do Registro Header = 0
		registroHeader.append("0");
		
		// H.02 - CNPJ da Institui��o Informante (7 primeiros d�gitos)
		String cnpjEmpresa = sistemaParametro.getCnpjEmpresa();
		cnpjEmpresa = cnpjEmpresa.substring(0, 8);
		registroHeader.append(Util.adicionarZerosEsquedaNumero(9, cnpjEmpresa));
		
		// H.03 - Data do Movimento (Gera��o do Arquivo)
		String dataAtualString = Util.recuperaDataInvertida(new Date());
		registroHeader.append(Util.adicionarZerosEsquedaNumero(8, dataAtualString));
		
		// H.04 - DDD do Telefone da Institui��o Informante
		registroHeader.append(Util.completaStringComZeroAEsquerda(sistemaParametro.getDddTelefone(), 4));
		
		// H.05 - Telefone da Institui��o Informante
		registroHeader.append(sistemaParametro.getNumeroTelefone());
		
		// H.06 - Ramal da Institui��o Informante
		registroHeader.append(sistemaParametro.getNumeroRamal());
		
		// H.07 - Nome do Contato da Institui��o Informante
		registroHeader.append(Util.completaString(sistemaParametro.getClienteResponsavelNegativacao().getNome(), 70));
		
		// H.08 - Identifica��o do arquivo fixo
		registroHeader.append("SERASA-CONVEM04");
		
		// H.09 - N�mero da remessa do arquivo
		numeroSequencialEnvio += 1;
		registroHeader.append(Util.adicionarZerosEsquedaNumero(6, numeroSequencialEnvio + ""));
		
		// H.010 - C�digo de Envio do Arquivo (E - Entrada / R - Retorno) 
		registroHeader.append("E");
		
		// H.11 - Diferencial de remessa
		registroHeader.append(Util.completaString(" ", 4));
		
		// H.12 - Deixar em Branco
		registroHeader.append(Util.completaString(" ", 3));
		
		// H.13 - LOGON
		registroHeader.append(Util.completaString(" ", 8));
		
		// H.14 - Deixar em branco
		registroHeader.append(Util.completaString(" ", 392));
		
		// H.15 - C�digo de Erros
		registroHeader.append(Util.completaString(" ", 60));
		
		// H.16 - Sequ�ncia do Registro
		registroHeader.append(Util.adicionarZerosEsquedaNumero(7, "" + quantidadeRegistros));
		
		return registroHeader;
	}

	// GERA REGISTRO DETALHE CONSUMIDOR SERASA ****
	// ********************************************
	// CAMPOS CONFORME LAYOUT DO UC0671 [SB0009] **
	// ********************************************
	public StringBuilder geraRegistroTipoDetalheSERASA(int quantidadeRegistros, BigDecimal valorTotalDebitosImovel,
			ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper, Imovel imovelNegativado,
			DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper, Short tipoComando, Cliente cliente) throws ControladorException {

		try {
			StringBuilder registroDetalheConsumidor = new StringBuilder();
			
			// D.01 - C�digo do Registro Detalhe = 1
			registroDetalheConsumidor.append("1");
			
			// D.02 - C�digo da Opera��o (I - Inclus�o / E - Exclus�o
			registroDetalheConsumidor.append("I");
			
			// D.03 Filial e D�gito do CNPJ da Empresa Contratante (YYYY-ZZ)
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			String cnpjEmpresa = sistemaParametro.getCnpjEmpresa();
			registroDetalheConsumidor.append(cnpjEmpresa.substring(8));

			// D.04 - Maior data dos debitos no formato AAAAMMDD
			Date maiorData = null;
			// D.05 - Menor data dos debitos no formato AAAAMMDD
			Date menorData = null;

			if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
				maiorData = this.obterMaiorVencimento(dadosNegativacaoPorImovelHelper.getColecaoConta(),
						dadosNegativacaoPorImovelHelper.getColecaoGuias());
				menorData = this.obterMenorVencimento(dadosNegativacaoPorImovelHelper.getColecaoConta(),
						dadosNegativacaoPorImovelHelper.getColecaoGuias());

			} else if (tipoComando.equals(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO)) {
				maiorData = this.obterMaiorVencimento((List) obterDebitoImovelOuClienteHelper.getColecaoContasValores(),
						(List) obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores());
				menorData = this.obterMenorVencimento((List) obterDebitoImovelOuClienteHelper.getColecaoContasValores(),
						(List) obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores());
			}

			if (maiorData != null) {
				String D204 = Util.recuperaDataInvertida(maiorData);
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(8, D204));
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(8, D204));
			} else {
				registroDetalheConsumidor.append("00000000");
				registroDetalheConsumidor.append("00000000");
			}

			// D.06 - C�digo de natureza da opera��o
			// TODO - ??????
			registroDetalheConsumidor.append("SB ");
			// D.07 - C�digo da pra�a Embratel
			registroDetalheConsumidor.append("    ");

			if (cliente.getCnpj() != null && cliente.getCnpj().length() > 0) {
				// D.08 - Tipo de Pessoa
				registroDetalheConsumidor.append("J");
				// D.09 - Tipo de Pessoa do primeiro documento (1 - CNPJ / 2 - CPF)
				registroDetalheConsumidor.append("1");
				// D.10 - N�mero do documento
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(15, "" + cliente.getCnpj()));
			} else {
				// D.08 - Tipo de Pessoa
				registroDetalheConsumidor.append("F");
				// D.09 - Tipo de Pessoa do primeiro documento (1 - CNPJ / 2 - CPF)
				registroDetalheConsumidor.append("2");
				// D.10 - N�mero do documento
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(15, "" + cliente.getCpf()));
			}

			// D.11 - Motivo da Baixa
			registroDetalheConsumidor.append("  ");

			if (cliente.getCpf() != null && cliente.getCpf().length() > 0 && cliente.getRg() != null && cliente.getRg().length() > 0) {
				// D.12 - Tipo do segundo documento (3 - RG)
				registroDetalheConsumidor.append("3");
				// D.13 - N�mero do segundo documento
				registroDetalheConsumidor.append(Util.completaStringComEspacoAEsquerda(cliente.getRg(), 15));
				// D.14 - UF do segundo documento
				if (cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getId() != null) {
					FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
					filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.ID,
							cliente.getUnidadeFederacao().getId()));

					UnidadeFederacao unidadeFederacao = (UnidadeFederacao) Util.retonarObjetoDeColecao(
							RepositorioUtilHBM.getInstancia().pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName()));

					registroDetalheConsumidor.append(Util.completaString(unidadeFederacao.getSigla(), 2));
				} else {
					registroDetalheConsumidor.append("  ");
				}
			} else {
				// D.12
				registroDetalheConsumidor.append(" ");
				// D.13
				registroDetalheConsumidor.append(Util.completaString(" ", 15));
				// D.14
				registroDetalheConsumidor.append("  ");
			}

			// os campo abaixo referentes a: D.15.16.17.18.19.20.21
			registroDetalheConsumidor.append(" ");
			registroDetalheConsumidor.append(" ");
			registroDetalheConsumidor.append(Util.completaString(" ", 15));
			registroDetalheConsumidor.append("  ");
			registroDetalheConsumidor.append(" ");
			registroDetalheConsumidor.append(Util.completaString(" ", 15));
			registroDetalheConsumidor.append("  ");

			// D.22 - Nome cliente devedor
			registroDetalheConsumidor.append(Util.completaString(cliente.getNome(), 70));

			// D.23 - Data de nascimento do cliente
			if (cliente.getCpf() != null && cliente.getCpf().length() > 0) {
				if (cliente.getDataNascimento() != null && !cliente.getDataNascimento().equals("")) {
					registroDetalheConsumidor.append(Util.completaString(Util.recuperaDataInvertida(cliente.getDataNascimento()), 8));
				} else {
					registroDetalheConsumidor.append("00000000");
				}
			} else {
				registroDetalheConsumidor.append("00000000");
			}

			// D.24 - Nome do pai
			registroDetalheConsumidor.append(Util.completaString(" ", 70));
			// D.25 - Nome da M�e
			if (cliente.getNomeMae() != null) {
				registroDetalheConsumidor.append(Util.completaString(cliente.getNomeMae(), 70));
			} else {
				registroDetalheConsumidor.append(Util.completaString(" ", 70));
			}
			// D.26 - Endereco completo
			String endereco = this.getControladorEndereco().pesquisarEnderecoClienteAbreviado(cliente.getId());

			if (endereco != null && endereco.length() > 45) {
				registroDetalheConsumidor.append(Util.completaString(endereco.substring(0, 45), 45));
			} else {
				registroDetalheConsumidor.append(Util.completaString(endereco, 45));
			}

			ClienteEndereco clienteEndereco = null;
			Collection colecaoClienteEndereco = this.repositorioSpcSerasa.getDadosEnderecoCliente(cliente.getId());
			if (colecaoClienteEndereco == null || colecaoClienteEndereco.isEmpty()) {
				colecaoClienteEndereco = this.repositorioSpcSerasa.getCep(cliente.getId());
			}

			clienteEndereco = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);

			if (clienteEndereco.getLogradouroBairro() != null) {
				// D.27 - Bairro
				registroDetalheConsumidor.append(Util.completaString(clienteEndereco.getLogradouroBairro().getBairro().getNome(), 20));
			} else {
				// D.27 - Bairro
				registroDetalheConsumidor.append(Util.completaString(clienteEndereco.getLogradouroCep().getCep().getBairro(), 20));
			}

			// D.28 - Munic�pio
			registroDetalheConsumidor.append(Util.completaString(clienteEndereco.getLogradouroCep().getCep().getMunicipio(), 25));
			// D.29 - Unidade da Federa��o
			registroDetalheConsumidor.append(Util.completaString(clienteEndereco.getLogradouroCep().getCep().getSigla(), 2));
			// D.30 - Cep
			registroDetalheConsumidor.append(Util.completaString("" + clienteEndereco.getLogradouroCep().getCep().getCodigo(), 8));

			String valorString = Util.formatarMoedaReal(valorTotalDebitosImovel);
			String valorNovo = "";
			for (int i = 0; i < valorString.length(); i++) {
				if (valorString.charAt(i) != '.' && valorString.charAt(i) != ',') {
					valorNovo = valorNovo + valorString.charAt(i);
				}
			}
			valorString = valorNovo;
			// D.31 - Valor total dos debitos
			registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(15, valorString));
			// D.32 - C�digo do Imovel
			registroDetalheConsumidor.append(Util.completaString(imovelNegativado.getId().toString(), 16));
			// D.33 - Nosso n�mero
			registroDetalheConsumidor.append("         ");

			// D.34
			if (endereco != null && endereco.length() > 45) {
				registroDetalheConsumidor.append(Util.completaString(endereco.substring(45), 25));
			} else {
				registroDetalheConsumidor.append(Util.completaString(" ", 25));
			}

			// D.35 - DDD
			IClienteFone cliFone = (IClienteFone) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa.getDddFone(cliente.getId()));
			if (cliFone != null) {
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(4, cliFone.getDdd()));
			} else {
				registroDetalheConsumidor.append("    ");
			}

			// D.36 - Fone
			if (cliFone != null) {
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(9, cliFone.getTelefone()));
			} else {
				registroDetalheConsumidor.append("         ");
			}

			// D.37 - Data do Menor vencimento do d�bito
			if (menorData != null) {
				String D207 = Util.recuperaDataInvertida(menorData);
				registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(8, D207));
			} else {
				registroDetalheConsumidor.append("        ");
			}

			// D.38
			registroDetalheConsumidor.append(Util.completaString(" ", 15));
			// D.39
			registroDetalheConsumidor.append(Util.completaString(" ", 9));
			// D.40
			registroDetalheConsumidor.append(Util.completaString(" ", 60));
			// D.41
			registroDetalheConsumidor.append(Util.adicionarZerosEsquedaNumero(7, "" + quantidadeRegistros));
			
			return registroDetalheConsumidor;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso crit�rio)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper) throws ControladorException {

		Collection retorno = new ArrayList();

		try {
			retorno = repositorioSpcSerasa
					.pesquisarComandoNegativacaoTipoCriterio(comandoNegativacaoTipoCriterioHelper);
		} catch (ErroRepositorioException e) {

			e.printStackTrace();
		}

		return retorno;
	}

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso matr�cula)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(ComandoNegativacaoHelper comandoNegativacaoHelper)
			throws ControladorException {

		Collection retorno = new ArrayList();

		try {
			retorno = repositorioSpcSerasa.pesquisarComandoNegativacaoTipoMatricula(comandoNegativacaoHelper);
		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores
	 * Insere Processo Batch para Registrar Movimento de Retorno do Negativador.
	 */
	public void inserirProcessoRegistrarNegativadorMovimentoRetorno(Usuario usuario) throws ControladorException {
		Fachada fachada = Fachada.getInstancia();

		int idProcesso = Processo.GERAR_MOVIMENTO_RETORNO_NEGATIVACAO;

		ProcessoIniciado processoIniciado = new ProcessoIniciado();
		Processo processo = new Processo();
		processo.setId(idProcesso);
		processoIniciado.setUsuario(usuario);
		processoIniciado.setDataHoraAgendamento(new Date());
		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		processoIniciado.setProcesso(processo);
		processoIniciado.setProcessoSituacao(processoSituacao);

		Integer codigoProcessoIniciadoGerado = (Integer) fachada.inserirProcessoIniciado(processoIniciado);
	}

	/**
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores
	 * 
	 * Busca o Arquivo salvo na pasta bin, para processar Movimento
	 * de Retorno da Negativa��o.
	 */
	private Object[] getArquivoNegativadorMovimentoRetorno() throws ControladorException {
		StringBuilder stringBuilderTxt = new StringBuilder();
		Integer idNegativador = null;
		Object[] retorno = new Object[4];

		int quantidadeRegistros = 0;

		String nomeArquivo = "REG_SPC_SERASA_" + Util.formatarDataAAAAMMDD(new Date());

		File file = new File(nomeArquivo);
		FileInputStream fin = null;
		InputStreamReader reader = null;
		BufferedReader buffer = null;
		try {
			fin = new FileInputStream(file);
			reader = new InputStreamReader(fin);
			buffer = new BufferedReader(reader);
			
			boolean eof = false;
			boolean primeiraLinha = true;

			while (!eof) {
				String linhaLida = buffer.readLine();

				if (primeiraLinha) {
					String identificacaoArquivo = "";

					identificacaoArquivo = getConteudo(105, 15, linhaLida.toCharArray());
					if (identificacaoArquivo.toUpperCase().equals("SERASA-CONVEM04")) {
						idNegativador = Negativador.NEGATIVADOR_SERASA;
					} else {
						identificacaoArquivo = getConteudo(318, 5, linhaLida.toCharArray());
						identificacaoArquivo = identificacaoArquivo.trim();

						if (identificacaoArquivo.toUpperCase().equalsIgnoreCase("SPC")) {
							idNegativador = Negativador.NEGATIVADOR_SPC;
						}
					}

					primeiraLinha = false;
				}

				if (linhaLida != null && linhaLida.length() > 0) {
					stringBuilderTxt.append(linhaLida);
					stringBuilderTxt.append("\n");
					quantidadeRegistros = quantidadeRegistros + 1;
				} else {
					break;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IoUtil.fecharStream(buffer);
			IoUtil.fecharStream(reader);
			IoUtil.fecharStream(fin);
		}

		retorno[0] = idNegativador;
		retorno[1] = stringBuilderTxt;
		retorno[2] = quantidadeRegistros;
		retorno[3] = nomeArquivo;

		return retorno;
	}

	/**
	 * Ponto inicial do caso de uso de Registrar Movimento de Retorno dos Negativadores
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores
	 */
	public Collection registrarNegativadorMovimentoRetorno(Integer idFuncionalidadeIniciada) throws ControladorException {
		
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

		logger.info(" [UC0672] Registrar Movimento de Retorno dos Negativadores ");

		Collection collRegistrosLidos = new ArrayList();
		String nomeArquivo = null;

		try {
			Integer countRegistro = 0;
			String numeroSequencialRetorno = "";
			NegativadorMovimento negativadorMovimento = null;

			Object[] retornoArquivo = getArquivoNegativadorMovimentoRetorno();
			Integer idNegativador = (Integer) retornoArquivo[0];
			StringBuilder stringBuilderTxt = (StringBuilder) retornoArquivo[1];
			Integer quantidadeRegistros = (Integer) retornoArquivo[2];
			nomeArquivo = (String) retornoArquivo[3];

			Negativador negativador = new Negativador();
			negativador.setId(idNegativador);

			String[] reg = new String[2];

			if (idNegativador.equals(Negativador.NEGATIVADOR_SPC)) {

				Object[] retorno = validarArquivoMovimentoRetornoSPC(stringBuilderTxt, negativador);

				collRegistrosLidos = (Collection) retorno[0];
				negativadorMovimento = (NegativadorMovimento) retorno[1];

				Object[] registrosLidos = collRegistrosLidos.toArray();

				for (int i = 0; i < registrosLidos.length; i = i + 1) {

					if (i % 501 == 0) {
						logger.info("Retorno " + negativadorMovimento.getNumeroSequencialEnvio() + ": Processados " + i + " / " + registrosLidos.length);
					}

					if (i > 0) {
						reg[0] = (String) registrosLidos[i];

						if (i + 1 < registrosLidos.length) {
							reg[1] = (String) registrosLidos[i + 1];
						} else {
							reg[1] = null;
						}

						i = i + 1;

						this.atualizarMovimentoEnvioSPC(reg, negativador, negativadorMovimento);

					} else {
						numeroSequencialRetorno = getConteudo(18, 8, registrosLidos[i].toString().toCharArray());
					}

				}
			} else if (idNegativador.equals(Negativador.NEGATIVADOR_SERASA)) {
				Object[] retorno = validarArquivoMovimentoRetornoSERASA(stringBuilderTxt, negativador);
				collRegistrosLidos = (Collection) retorno[0];
				Iterator it = collRegistrosLidos.iterator();
				negativadorMovimento = (NegativadorMovimento) retorno[1];

				while (it.hasNext()) {

					String registro = (String) it.next();
					countRegistro = countRegistro + 1;

					if (countRegistro > 1) {

						this.atualizarMovimentoEnvioSERASA(registro, negativador, negativadorMovimento);

					} else {
						numeroSequencialRetorno = getConteudo(120, 6, registro.toCharArray());
					}

				}
			}

			NegativadorContrato negativadorContrato = new NegativadorContrato();

			negativadorContrato = this.repositorioSpcSerasa.consultarNegativadorContratoVigente(negativador.getId());

			negativadorContrato.setNumeroSequencialRetorno(Util.converterStringParaInteger(numeroSequencialRetorno));

			RepositorioUtilHBM.getInstancia().atualizar(negativadorContrato);

			negativadorMovimento.setDataRetorno(new Date());
			negativadorMovimento.setDataProcessamentoRetorno(new Date());
			negativadorMovimento.setNumeroSequencialRetorno(Util.converterStringParaInteger(numeroSequencialRetorno));
			negativadorMovimento.setNumeroRegistrosRetorno(quantidadeRegistros);
			negativadorMovimento.setUltimaAlteracao(new Date());

			RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimento);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			envioEmailErroMovimentoRetorno(ex, nomeArquivo);
			throw new ControladorException("erro.sistema", ex);
		}

		return collRegistrosLidos;
	}

	private void envioEmailErroMovimentoRetorno(Exception excecao, String nomeArquivo) {

		EnvioEmail envioEmail;
		ByteArrayOutputStream baos = null;
		try {
			envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.SPC_SERASA_MOV_RETORNO);

			String emailRemetente = envioEmail.getEmailRemetente();

			String tituloMensagem = envioEmail.getTituloMensagem();

			baos = new ByteArrayOutputStream();
			excecao.printStackTrace(new PrintStream(baos));

			String corpoMensagem = "O arquivo : " + nomeArquivo + " \n " + envioEmail.getCorpoMensagem() + " \n "
					+ " Exce��o: " + excecao;
			String emailReceptor = envioEmail.getEmailReceptor();

			System.out.println("email destinat�rio:" + emailReceptor);

			ServicosEmail.enviarMensagem(emailRemetente, emailReceptor, tituloMensagem, corpoMensagem);
		} catch (ControladorException e1) {
			e1.printStackTrace();
		} catch (ErroEmailException e) {
			e.printStackTrace();
		} finally {
			IoUtil.fecharStream(baos);
		}
	}

	/**
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores Validar Arquivo
	 * Movimento Retorno Online.
	 * 
	 * @author Yara T. Souza
	 * @date 09/12/2008
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] validarArquivoMovimentoRetorno(StringBuilder stringBuilderTxt, Negativador negativador)
			throws ControladorException {

		Object[] retorno = null;
		if (negativador.getId().equals(Negativador.NEGATIVADOR_SPC)) {
			retorno = validarArquivoMovimentoRetornoSPC(stringBuilderTxt, negativador);
		} else if (negativador.getId().equals(Negativador.NEGATIVADOR_SERASA)) {
			retorno = validarArquivoMovimentoRetornoSERASA(stringBuilderTxt, negativador);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores
	 * 
	 * [SB0001] - Validar Arquivo de Movimento de Retorno do SPC
	 * 
	 * @author Yara Taciane
	 * @date 10/01/2008
	 * 
	 * @param negativadorMovimentoRegRetMot
	 * @throws ControladorException
	 * 
	 *             Tenho que verificar se � spc ou serasa aqui nesse m�todo.
	 * 
	 */

	private Object[] validarArquivoMovimentoRetornoSPC(StringBuilder stringBuilderTxt, Negativador negativador)
			throws ControladorException {

		Object[] retorno = new Object[2];
		// ----------------------------------------------------------------
		// [SB0001] - Validar Arquivo de Movimento de Retorno do SPC
		// ----------------------------------------------------------------

		String numeroRegistro = "";

		int countRegistro = 0;
		Collection collRegistrosLidos = new ArrayList();
		NegativadorMovimento negativadorMovimento = null;

		StringTokenizer stk = new StringTokenizer(stringBuilderTxt.toString(), "\n");

		while (stk.hasMoreTokens()) {

			countRegistro = countRegistro + 1;

			String registro = stk.nextToken();

			// H.01
			String tipoRegistro = getConteudo(1, 2, registro.toCharArray());

			// --------------------------------------------------------------------------------------
			// Verifica Header (Primeira Linha)
			// ---------------------------------------------------------------------------------------
			if (countRegistro == 1) {
				// s� p/ header

				if (!tipoRegistro.toUpperCase().equals("00")) {
					throw new ControladorException("atencao.arquivo.movimento.negativador.sem.header");
				}

				// H.02
				String operacao = getConteudo(3, 7, registro.toCharArray());
				// 3.
				if (!operacao.toUpperCase().equalsIgnoreCase("RETORNO")) {
					throw new ControladorException("atencao.operacao_nao_corresponde_retorno");
				}

				// H.09
				String unidadeNegocio = getConteudo(318, 5, registro.toCharArray());
				unidadeNegocio = unidadeNegocio.trim();
				// 4.
				if (!unidadeNegocio.toUpperCase().equalsIgnoreCase("SPC")) {
					throw new ControladorException("atencao.movimento_nao_spc");
				}

				// 5.
				NegativadorContrato negativadorContrato = new NegativadorContrato();

				try {
					negativadorContrato = this.repositorioSpcSerasa.consultarNegativadorContratoVigente(negativador
							.getId());

					String numeroSequencialEnvioBD = negativadorContrato.getNumeroSequencialEnvio() + "";

					String qtdZeros = "";

					int tamanho = 8 - numeroSequencialEnvioBD.length();

					for (int i = 0; i < tamanho; i++) {
						qtdZeros = qtdZeros + "0";
					}

					numeroSequencialEnvioBD = qtdZeros + numeroSequencialEnvioBD;

					// H.04
					Integer numeroSequencialArquivo = Util.converterStringParaInteger(getConteudo(18, 8,
							registro.toCharArray()));

					// 6.

					if (numeroSequencialArquivo > Util.converterStringParaInteger(numeroSequencialEnvioBD)) {
						throw new ControladorException("atencao.movimento_fora_sequencia");
					}

					// 7.
					negativadorMovimento = this.repositorioSpcSerasa.getNegativadorMovimento(negativador,
							numeroSequencialArquivo);
					if (negativadorMovimento != null && negativadorMovimento.getDataRetorno() != null) {
						throw new ControladorException("atencao.movimento_retorno_ja_processado");
					}

					// ----------------------------------------------------------------------------------

					// 8.
					// H.12
					numeroRegistro = getConteudo(335, 6, registro.toCharArray());
					Integer numRegistro = Util.converterStringParaInteger(numeroRegistro);

					NegativadorMovimentoReg negativadorMovimentoReg = this.repositorioSpcSerasa
							.getNegativadorMovimentoReg(negativadorMovimento, numRegistro);

					if (negativadorMovimentoReg != null && negativadorMovimentoReg.getConteudoRegistro() == null) {
						throw new ControladorException("atencao.arquivo_movimento_sem_registros");
					}

					// ------------------------------------------------------------------------
					// � HEADER MAS ATUALIZA O REGISTRO DE ENVIO.
					// ------------------------------------------------------------------------
					String[] reg = new String[2];
					reg[0] = registro;

					Object[] negativadorMovimentoRegistros = new Object[2];
					negativadorMovimentoRegistros[0] = negativadorMovimentoReg;
					this.atualizarRegistroEnvioSPC(negativador, reg, negativadorMovimentoRegistros);
					// ------------------------------------------------------------------------

				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

			}

			// ----------------------------------------------------------------------------------------

			if ((stk.hasMoreTokens() == true && tipoRegistro.equals("00")) && countRegistro > 1) {
				throw new ControladorException("atencao.arquivo_movimento_nao_possui_trailler");
			}

			if (stk.hasMoreTokens() == false && tipoRegistro.equals("99")) {
				// T.02
				String totalRegistros = getConteudo(3, 6, registro.toCharArray());

				int tRegistros = Util.converterStringParaInteger(totalRegistros.trim());
				if (tRegistros != countRegistro) {
					throw new ControladorException("atencao.total_registro_do_arquivo_invalido");
				}

			} else if (stk.hasMoreTokens() == false && !tipoRegistro.equals("99")) {
				throw new ControladorException("atencao.arquivo_movimento_nao_possui_trailler");
			}

			if (!tipoRegistro.equals("99") && !tipoRegistro.equals("01") && !tipoRegistro.equals("02")
					&& countRegistro > 1) {
				throw new ControladorException("atencao.arquivo_movimento_contem_registros_com_tipos_invalidos");
			}

			String numeroSequencia = getConteudo(335, 6, registro.toCharArray());

			try {
				int numSequencia = Integer.parseInt(numeroSequencia.trim());
				if (numSequencia != countRegistro) {
					throw new ControladorException("atencao.arquivo_movimento_contem_registros_com_sequencia_invalida");
				}
			} catch (Exception e) {

			}

			collRegistrosLidos.add(registro);

		}

		retorno[0] = collRegistrosLidos;
		retorno[1] = negativadorMovimento;

		return retorno;

		// --------------------------------------------------------------------------------------------

	}

	/**
	 * 
	 * Registrar Movimento de Retorno dos Negativadores [UC0672] Registrar
	 * Movimento de Retorno dos Negativadores
	 * 
	 * [SB0002] - Atualizar Movimento de Envio SPC
	 * 
	 * @author Yara Taciane
	 * @throws ErroRepositorioException
	 * @date 10/01/2008
	 * 
	 */

	private void atualizarMovimentoEnvioSPC(Object[] registro, Negativador negativador,
			NegativadorMovimento negativadorMovimento) throws ControladorException {

		try {

			// ----------------------------------------------------------------------------
			// [SB0002] - Atualizar Movimento de Envio SPC
			// ----------------------------------------------------------------------------
			NegativadorMovimentoReg negativadorMovimentoReg = null;
			Object[] negativadorMovimentoRegistros = new Object[2];

			// 7.

			for (int i = 0; i < registro.length; i++) {

				if (registro[i] != null) {

					Integer numeroRegistro = null;

					// ---------------------------------------------------------------
					numeroRegistro = Util.converterStringParaInteger(getConteudo(335, 6, registro[i].toString()
							.toCharArray()));

					// ---------------------------------------------------------------
					if (numeroRegistro != null) {

						negativadorMovimentoReg = this.repositorioSpcSerasa.getNegativadorMovimentoReg(
								negativadorMovimento, numeroRegistro);

						if (negativadorMovimentoReg != null && negativadorMovimentoReg.getConteudoRegistro() == null) {
							throw new ControladorException("atencao.arquivo_movimento_sem_registros");
						}

						negativadorMovimentoRegistros[i] = negativadorMovimentoReg;

					}

				}

			}
			// ---------------------------------------------------------------------------------
			// [SB0005] - Atualizar Registro Envio
			// -------------------------------------------------------------------------------
			this.atualizarRegistroEnvioSPC(negativador, registro, negativadorMovimentoRegistros);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * 
	 * Registrar Movimento de Retorno dos Negativadores
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores
	 * 
	 * [SB0003] - Validar Arquivo de Movimento de Retorno do SERASA
	 */
	private Object[] validarArquivoMovimentoRetornoSERASA(StringBuilder texto, Negativador negativador) throws ControladorException {

		int contadorRegistro = 0;
		NegativadorMovimento negativadorMovimento = null;

		Collection colecaoRegistrosLidos = new ArrayList();

		StringTokenizer stk = new StringTokenizer(texto.toString(), "\n");

		while (stk.hasMoreTokens()) {

			contadorRegistro = contadorRegistro + 1;

			String registro = stk.nextToken();

			// H.01
			String tipoRegistro = getConteudo(1, 1, registro.toCharArray());

			if (contadorRegistro == 1) {
				if (!tipoRegistro.toUpperCase().equals("0")) {
					throw new ControladorException("atencao.arquivo.movimento.sem.header");
				}

				// H.02
				String cnpj = getConteudo(2, 9, registro.toCharArray());

				SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();

				String cnpjEmpresa = null;
				if (sistemaParametros != null && sistemaParametros.getCnpjEmpresa() != null) {
					cnpjEmpresa = "0" + sistemaParametros.getCnpjEmpresa().substring(0, 8);
				}

				if (!cnpj.toUpperCase().equals(cnpjEmpresa)) {
					throw new ControladorException("atencao.cnpj_nao_corresponde_ao_contratante");
				}

				// H.03
				String dataMovimento = getConteudo(11, 8, registro.toCharArray());
				if (!Util.validarDiaMesAno(dataMovimento)) {
					throw new ControladorException("atencao.data_movimento_invalida", null, dataMovimento);
				}

				// H.08
				String identificacaoArquivoFixo = getConteudo(105, 15, registro.toCharArray());
				if (!identificacaoArquivoFixo.toUpperCase().equals("SERASA-CONVEM04")) {
					throw new ControladorException("atencao.identificacao_arquivo_invalida", null, identificacaoArquivoFixo);
				}

				// H.10
				String codigoEnvioArquivo = getConteudo(126, 1, registro.toCharArray());
				if (!codigoEnvioArquivo.toUpperCase().equals("R")) {
					throw new ControladorException("atencao.codigo_envio_arquivo_invalido", null, codigoEnvioArquivo);
				}

				try {
					NegativadorContrato negativadorContrato = this.repositorioSpcSerasa.consultarNegativadorContratoVigente(negativador.getId());

					String numeroSequencialEnvioBase = negativadorContrato.getNumeroSequencialEnvio() + "";

					String quantidadeZeros = "";

					int tamanho = 6 - numeroSequencialEnvioBase.length();

					for (int i = 0; i < tamanho; i++) {
						quantidadeZeros = quantidadeZeros + "0";
					}

					numeroSequencialEnvioBase = quantidadeZeros + numeroSequencialEnvioBase;

					// H.09
					String numeroSequencialArquivo = getConteudo(120, 6, registro.toCharArray());

					if (Util.converterStringParaInteger(numeroSequencialArquivo) > Util.converterStringParaInteger(numeroSequencialEnvioBase)) {
						throw new ControladorException("atencao.movimento_fora_sequencia");
					}

					if (negativadorContrato.getIndicadorControleNsaRetorno().equals(ConstantesSistema.SIM)) {
						String numeroSequencialRetornoBase = (negativadorContrato.getNumeroSequencialRetorno() + 1) + "";

						String quantidadeZerosRetorno = "";

						int tamanhoretorno = 8 - numeroSequencialRetornoBase.length();

						for (int i = 0; i < tamanhoretorno; i++) {
							quantidadeZerosRetorno = quantidadeZerosRetorno + "0";
						}

						numeroSequencialRetornoBase = quantidadeZerosRetorno + numeroSequencialRetornoBase;

						if (Util.converterStringParaInteger(numeroSequencialArquivo).compareTo(Util.converterStringParaInteger(numeroSequencialRetornoBase)) != 0) {
							throw new ControladorException("atencao.movimento_fora_sequencia");
						}
					}

					negativadorMovimento = repositorioSpcSerasa.getNegativadorMovimento(negativador,
							Util.converterStringParaInteger(numeroSequencialArquivo));
					if (negativadorMovimento != null && negativadorMovimento.getDataRetorno() != null) {
						throw new ControladorException("atencao.movimento_retorno_ja_processado");
					}

					// H.14
					Integer numeroRegistro = Util.converterStringParaInteger(getConteudo(594, 7, registro.toCharArray()));

					NegativadorMovimentoReg negativadorMovimentoReg = this.repositorioSpcSerasa.getNegativadorMovimentoReg(
							negativadorMovimento, numeroRegistro);

					if (negativadorMovimentoReg.getConteudoRegistro() == null) {
						throw new ControladorException("atencao.arquivo_movimento_sem_registros");
					}

					this.atualizarRegistroEnvio(negativador, registro, negativadorMovimentoReg);
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

			}

			if (stk.hasMoreTokens() == false && !tipoRegistro.equals("9")) {
				throw new ControladorException("atencao.arquivo_movimento_nao_possui_trailler");
			}

			if (!tipoRegistro.equals("9") && !tipoRegistro.equals("1") && !tipoRegistro.equals("0") && contadorRegistro > 1) {
				throw new ControladorException("atencao.arquivo_movimento_contem_registros_com_tipos_invalidos");
			}

			try {
				int numeroSequencia = Util.converterStringParaInteger(getConteudo(594, 7, registro.toCharArray()).trim());

				if (numeroSequencia != contadorRegistro) {
					throw new ControladorException("atencao.arquivo_movimento_contem_registros_com_sequencia_invalida");
				}
			} catch (Exception e) {

			}

			colecaoRegistrosLidos.add(registro);
		}

		if (negativadorMovimento.getNumeroRegistrosEnvio() != contadorRegistro) {
			throw new ControladorException("atencao.total_registro_do_arquivo_invalido");
		}

		Object[] retorno = new Object[2];
		retorno[0] = colecaoRegistrosLidos;
		retorno[1] = negativadorMovimento;

		return retorno;

		// ------------------------------------------------------------------

	}

	/**
	 * 
	 * Registrar Movimento de Retorno dos Negativadores [UC0672] Registrar
	 * Movimento de Retorno dos Negativadores
	 * 
	 * [SB0004] - Atualizar Movimento de Envio SERASA
	 * 
	 * @author Yara Taciane
	 * @date 10/01/2008
	 * 
	 */
	private void atualizarMovimentoEnvioSERASA(String registro, Negativador negativador,
			NegativadorMovimento negativadorMovimento) throws ControladorException {

		// ----------------------------------------------------------------------------
		// [SB0004] - Atualizar Movimento de Envio SERASA
		// ----------------------------------------------------------------------------

		String numeroRegistro = "";
		// 7.

		try {
			// ---------------------------------------------------------------
			numeroRegistro = getConteudo(594, 7, registro.toCharArray()).trim();
			Integer numRegistro = Util.converterStringParaInteger(numeroRegistro);
			// ------------------------------------------------------------

			if (!numeroRegistro.equalsIgnoreCase("")) {

				NegativadorMovimentoReg negativadorMovimentoReg = this.repositorioSpcSerasa.getNegativadorMovimentoReg(
						negativadorMovimento, numRegistro);
				if (negativadorMovimentoReg != null && negativadorMovimentoReg.getConteudoRegistro() == null) {
					throw new ControladorException("atencao.arquivo_movimento_sem_registros");
				}

				// ---------------------------------------------------------------------------------
				// [SB0005] - Atualizar Registro Envio
				// -------------------------------------------------------------------------------
				this.atualizarRegistroEnvio(negativador, registro, negativadorMovimentoReg);

			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * 
	 * Registrar Movimento de Retorno dos Negativadores [UC0672] Registrar
	 * Movimento de Retorno dos Negativadores
	 * 
	 * [Atualizar Registro Envio SPC] verificarRegistroSPC_01
	 * verificarRegistroSPC_02
	 * 
	 * @author Yara Taciane
	 * @date 10/01/2008
	 * 
	 * @param negativadorMovimentoRegRetMot
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private void atualizarRegistroEnvioSPC(Negativador negativador, Object[] registro,
			Object[] negativadorMovimentoRegistros) throws ControladorException {

		try {

			String conteudoRegistro = "";
			String tipoRegistro = "";
			String sequencial = "";
			boolean isHeader = false;

			NegativadorMovimentoReg negativadorMovimentoReg = null;

			if (Negativador.NEGATIVADOR_SPC.equals(negativador.getId())) {

				for (int i = 0; i < registro.length; i++) {

					if (negativadorMovimentoRegistros[i] != null) {

						negativadorMovimentoReg = (NegativadorMovimentoReg) negativadorMovimentoRegistros[i];

						if (registro[i] != null) {

							conteudoRegistro = getConteudo(1, 324, registro[i].toString().toCharArray());
							sequencial = getConteudo(335, 6, registro[i].toString().toCharArray());

							// Quando � Header ele sempre vai levantar exce��o
							tipoRegistro = getConteudo(1, 2, registro[i].toString().toCharArray());

							if (!tipoRegistro.equals("00") && !tipoRegistro.equals("99")) {
								String conteudo = negativadorMovimentoReg.getConteudoRegistro();
								String conteudoNegativadorMovimentoReg = getConteudo(1, 324, conteudo.toCharArray());

								if (!conteudoRegistro.equals(conteudoNegativadorMovimentoReg)) {
									throw new ControladorException(
											"atencao.conteudo_registro_nao_corresponde_ao_enviado", null, sequencial);
								}
							} else {
								isHeader = true;
							}

						}

					}

				}

				if (isHeader) {
					NegativadorMovimentoReg negativadorMovimentoReg_01 = (NegativadorMovimentoReg) negativadorMovimentoRegistros[0];
					verificarRegistroSPC_01(registro, negativador, negativadorMovimentoReg_01);
				} else {
					NegativadorMovimentoReg negativadorMovimentoReg_01 = (NegativadorMovimentoReg) negativadorMovimentoRegistros[0];
					NegativadorMovimentoReg negativadorMovimentoReg_02 = (NegativadorMovimentoReg) negativadorMovimentoRegistros[1];

					verificarRegistroSPC_01(registro, negativador, negativadorMovimentoReg_01);
					verificarRegistroSPC_02(registro, negativador, negativadorMovimentoReg_02);

				}

			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * Registrar Movimento de Retorno dos Negativadores [UC0672] Verifica o
	 * negativadorMovimentoRegRetMot do Registro 01 do SPC e atualiza o
	 * negativadorMovimentoReg com o indicador de Aceito com valor 1 ou 2
	 * 
	 * [Verificar Registro 01 do SPC]
	 * 
	 * @author Yara Taciane
	 * @date 10/01/2008
	 * 
	 * @param negativadorMovimentoRegRetMot
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private void verificarRegistroSPC_01(Object[] registro, Negativador negativador,
 NegativadorMovimentoReg negativadorMovimentoReg)
			throws ControladorException, ErroRepositorioException {

		short indicadorRegistroAceito = -1;
		String codigoRetorno = "-1";
		String campoCodigoRetorno1 = "";
		String campoCodigoRetorno2 = "";

		// --------------------------------------------------------------------------------------
		// [SB005] - Atualizar Registro de Envio para SPC
		// --------------------------------------------------------------------------------------
		// 1.0
		// ---------------------------------------------------------------------------------------

		// 2.0
		// --------------------------------------------------------------------------------
		// atribuir o valor um ao campo indicador registro aceito
		indicadorRegistroAceito = 1;
		// -------------------------------------------------------------------------------

		// Para cada ocorr�ncia do c�digo de retorno
		// Caso o registro seja do SPC
		// 3.0
		// -------------------------------------------------------------------------------
		String registro1 = (String) registro[0];
		String registro2 = (String) registro[1];
		// ------------------------------------------------------------------------------
		// Para o REGISTRO 01
		// ------------------------------------------------------------------------------

		if ((registro1 != null && !"".equals(registro1)) && (registro2 != null && !"".equals(registro2))) {

			campoCodigoRetorno1 = getConteudo(325, 10, registro1.toCharArray()).trim();
			campoCodigoRetorno2 = getConteudo(325, 10, registro2.toCharArray()).trim();

			if (campoCodigoRetorno1.equals("0000000000") && !campoCodigoRetorno1.equals("")) {
				if (!campoCodigoRetorno2.equals("0000000000") && !campoCodigoRetorno2.equals("")) {
					campoCodigoRetorno1 = "0000000098";
				}
			}

		} else if (registro1 != null && !"".equals(registro1)) {
			campoCodigoRetorno1 = getConteudo(325, 10, registro1.toCharArray()).trim();
		}

		int cont = 0;
		for (int j = 0; j < 9; j = j + 2) {

			codigoRetorno = campoCodigoRetorno1.substring(j, j + 2);

			if (codigoRetorno.equals("00")) {
				cont = cont + 1;
			}

			if (!codigoRetorno.equals("00") || (cont == 5 && codigoRetorno.equals("00"))) {
				Integer codRetorno = Util.converterStringParaInteger(codigoRetorno);

				NegativadorMovimentoRegRetMot negativadorMovimentoRegRetMot = new NegativadorMovimentoRegRetMot();
				negativadorMovimentoRegRetMot.setNegativadorMovimentoReg(negativadorMovimentoReg);

				FiltroNegativadorRetornoMotivo fnrm = new FiltroNegativadorRetornoMotivo();
				fnrm.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codRetorno.shortValue()));
				fnrm.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, Negativador.NEGATIVADOR_SPC));

				NegativadorRetornoMotivo negativadorRetornoMot = null;

				try {

					negativadorRetornoMot = (NegativadorRetornoMotivo) Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().pesquisar(fnrm,
							NegativadorRetornoMotivo.class.getName()));

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				// [FS007] - Verifica a exixt�ncia do motivo de
				if (negativadorRetornoMot != null) {
					negativadorMovimentoRegRetMot.setNegativadorMovimentoReg(negativadorMovimentoReg);
					negativadorMovimentoRegRetMot.setNegativadorRetornoMotivo(negativadorRetornoMot);
					negativadorMovimentoRegRetMot.setUltimaAlteracao(new Date());

					// [FS008] -Verifica a indica��o de registro aceito
					// Caso n�o corresponda a aceito
					if (negativadorRetornoMot.getIndicadorRegistroAceito() != 1) {
						indicadorRegistroAceito = 2;
						negativadorRetornoMot.setIndicadorRegistroAceito(indicadorRegistroAceito);
					}

					// ************************************************************************
					// 03/12/2008
					// Consultar antes, e s� inserir se n�o existir.
					// RepositorioUtilHBM.getInstancia().inserir(negativadorMovimentoRegRetMot);
					this.inserirNegativadorMovimentoRegRetMot(negativadorMovimentoRegRetMot);
					// ************************************************************************
				} else {

					throw new ControladorException("atencao.arquivo_movimento_codigo_retorno_invalido");
				}

			}

		}

		// ----------------------------------------------------------------------------------------------
		// 4.0
		// ------------------------------------------------------------------------------------------------
		negativadorMovimentoReg.setIndicadorAceito(indicadorRegistroAceito);
		// -------------------------------------------------------------------------------------------------
		// [in�cio] - Altera��o 05/05/2008 - Indicar Exclus�o do im�vel caso a
		// inclus�o da negativa��o n�o seja aceita.
		// -------------------------------------------------------------------------------------------------
		NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();

		Imovel imovel = negativadorMovimentoReg.getImovel();
		// 5.0
		if (negativadorMovimento.getCodigoMovimento() == 1 && imovel != null) {
			try {

				if (indicadorRegistroAceito == ConstantesSistema.NAO_ACEITO) {
					// FiltroNegativacaoImoveis filtroNegativacaoImoveis = new
					// FiltroNegativacaoImoveis();
					// filtroNegativacaoImoveis.adicionarParametro(new
					// ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID,imovel.getId()));
					// filtroNegativacaoImoveis.adicionarParametro(new
					// ParametroSimples(FiltroNegativacaoImoveis.NEGATIVADOR_ID,negativador.getId()));
					// filtroNegativacaoImoveis.adicionarParametro(new
					// ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID,
					// negativadorMovimento.getNegativacaoComando().getId()));
					//
					// NegativacaoImovei negativacaoImoveis =
					// (NegativacaoImovei)
					// Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().
					// pesquisar(filtroNegativacaoImoveis,NegativacaoImovei.class.getName()));
					NegativacaoImoveis negativacaoImoveis = obterNegativacaoImoveis(negativador, negativadorMovimento, imovel,
							negativadorMovimentoReg.getCliente());

					if (negativacaoImoveis != null) {
						// 5.1
						short indicadorExcluido = 1;
						negativacaoImoveis.setIndicadorExcluido(indicadorExcluido);

						Date dataExclusao = new Date();
						negativacaoImoveis.setDataExclusao(dataExclusao);

						Date ultimaAlteracao = new Date();
						negativacaoImoveis.setUltimaAlteracao(ultimaAlteracao);

						// indicar a exclus�o do im�vel.
						RepositorioUtilHBM.getInstancia().atualizar(negativacaoImoveis);

						// CRC3323 - comentado por Vivianne Sousa -
						// analista:Fatima Sampaio - 10/05/2010
						// 5.2
						// FiltroImovel filtroImovel = new FiltroImovel();
						// filtroImovel.adicionarParametro(new
						// ParametroSimples(FiltroImovel.ID,imovel.getId()));
						// filtroImovel.adicionarParametro(new
						// ParametroSimples(FiltroImovel.COBRANCA_SITUACAO_ID,CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO));
						//
						// Imovel imovelRetorno = (Imovel)
						// Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().
						// pesquisar(filtroImovel,Imovel.class.getName()));
						//
						// if(imovelRetorno!= null){
						// imovelRetorno.setCobrancaSituacao(null);
						// imovelRetorno.setUltimaAlteracao(new Date());
						// RepositorioUtilHBM.getInstancia().atualizar(imovelRetorno);
						// }

						// CRC3323 - alterado por Vivianne Sousa -
						// analista:Fatima Sampaio - 10/05/2010
						// 5.3
						CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
						cobrancaSituacao.setId(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC);

						ImovelCobrancaSituacao imovelCobrancaSituacao = repositorioSpcSerasa.getImovelCobrancaSituacao(imovel, cobrancaSituacao,
								negativadorMovimentoReg.getCliente().getId());

						if (imovelCobrancaSituacao != null) {
							imovelCobrancaSituacao.setDataRetiradaCobranca(new Date());
							imovelCobrancaSituacao.setUltimaAlteracao(new Date());
							RepositorioUtilHBM.getInstancia().atualizar(imovelCobrancaSituacao);
						}

					}

				} else if (indicadorRegistroAceito == ConstantesSistema.ACEITO) {
					// adicionado por Vivianne Sousa - 12/03/2010 - analista:
					// F�tima Sampaio

					// 6.2.1. Atualizar a situa��o de cobran�a do im�vel na
					// tabela IMOVEL
					Integer idCobrancaSituacaoImovel = CobrancaSituacao.CARTA_ENVIADA_AO_SPC;
					// CRC3323 - comentado por Vivianne Sousa - analista:Fatima
					// Sampaio - 10/05/2010
					// getControladorImovel().atualizarSituacaoCobrancaImovel(idCobrancaSituacaoImovel,
					// CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO,imovel.getId());
					ImovelCobrancaSituacao imovelCobrancaSituacao = null;
					// CRC3323 - alterado por Vivianne Sousa - analista:Fatima
					// Sampaio - 10/05/2010
					// 6.2.2. Atualizar a situa��o de cobran�a do im�vel na
					// tabela IMOVEL_COBRANCA_SITUACAO
					List collImovelCobrancaSituacao = this.repositorioSpcSerasa.consultarImovelCobrancaSituacaoAtual(imovel.getId(),
							CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC, negativadorMovimentoReg.getCliente().getId());
					if (collImovelCobrancaSituacao != null && !collImovelCobrancaSituacao.isEmpty()) {
						Iterator iter = collImovelCobrancaSituacao.iterator();
						while (iter.hasNext()) {
							Integer idImovelCobrancaSituacao = (Integer) iter.next();
							getControladorImovel().atualizarDataRetiradaImovelSituacaoCobranca(idImovelCobrancaSituacao, new Date());
							imovelCobrancaSituacao = getControladorImovel().obterImovelCobrancaSituacao(idImovelCobrancaSituacao);
						}
					}

					// 6.2.3. Incluir a nova situa��o de cobran�a do im�vel na
					// tabela IMOVEL_COBRANCA_SITUACAO
					List idImovelCobrancaSituacaoNegativadoAutomaticamente = this.repositorioSpcSerasa.consultarImovelCobrancaSituacaoAtual(imovel.getId(),
							idCobrancaSituacaoImovel, negativadorMovimentoReg.getCliente().getId());
					// verifica��o adicionada para permitir reiniciar o batch
					if (idImovelCobrancaSituacaoNegativadoAutomaticamente == null || idImovelCobrancaSituacaoNegativadoAutomaticamente.isEmpty()) {
						ImovelCobrancaSituacao ics = new ImovelCobrancaSituacao();
						CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
						cobrancaSituacao.setId(idCobrancaSituacaoImovel);
						ics.setImovel(imovel);
						ics.setDataImplantacaoCobranca(new Date());
						ics.setDataRetiradaCobranca(null);
						ics.setCobrancaSituacao(cobrancaSituacao);
						ics.setCliente(negativadorMovimentoReg.getCliente());
						ics.setUltimaAlteracao(new Date());

						// RM6364 - Altera��o para negativa��o por per�odo
						// alterado por Vivianne Sousa - 12/12/2011
						if (imovelCobrancaSituacao != null) {
							if (imovelCobrancaSituacao.getAnoMesReferenciaInicio() != null) {
								ics.setAnoMesReferenciaInicio(imovelCobrancaSituacao.getAnoMesReferenciaInicio());
							}
							if (imovelCobrancaSituacao.getAnoMesReferenciaFinal() != null) {
								ics.setAnoMesReferenciaFinal(imovelCobrancaSituacao.getAnoMesReferenciaFinal());
							}
						}
						RepositorioUtilHBM.getInstancia().inserir(ics);
					}

					// 6.2.4. Atualizar a situa��o de cobran�a do im�vel na
					// tabela NEGATIVADOR_MOVIMENTO_REG
					// repositorioSpcSerasa.atualizarSituacaoCobrancaNegativadorMovimentoReg(
					// idCobrancaSituacaoImovel,negativadorMovimentoReg.getId());
					CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
					cobrancaSituacao.setId(idCobrancaSituacaoImovel);
					negativadorMovimentoReg.setCobrancaSituacao(cobrancaSituacao);

				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

		}

		// -------------------------------------------------------------------------------------------------
		// [fim] - Altera��o 05/05/2008 - Indicar Exclus�o do im�vel caso a
		// inclus�o da negativa��o n�o seja aceita.
		// -------------------------------------------------------------------------------------------------

		try {
			negativadorMovimentoReg.setIndicadorAceito(indicadorRegistroAceito);
			RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * Registrar Movimento de Retorno dos Negativadores [UC0672] Verifica o
	 * negativadorMovimentoRegRetMot do Registro 02 do SPC e atualiza o
	 * negativadorMovimentoReg com o indicador de Aceito com valor 1 ou 2
	 * 
	 * [Verificar Registro 02 do SPC]
	 * 
	 * @author Yara Taciane
	 * @date 10/01/2008
	 * 
	 * @param negativadorMovimentoRegRetMot
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private void verificarRegistroSPC_02(Object[] registro, Negativador negativador,
 NegativadorMovimentoReg negativadorMovimentoReg)
			throws ControladorException, ErroRepositorioException {

		short indicadorRegistroAceito = -1;
		String codigoRetorno = "-1";
		String campoCodigoRetorno1 = "";
		String campoCodigoRetorno2 = "";

		// --------------------------------------------------------------------------------------
		// [SB005] - Atualizar Registro de Envio para SPC
		// --------------------------------------------------------------------------------------
		// 1.0
		// ---------------------------------------------------------------------------------------

		// 2.0
		// --------------------------------------------------------------------------------
		// atribuir o valor um ao campo indicador registro aceito
		indicadorRegistroAceito = 1;
		// -------------------------------------------------------------------------------

		// Para cada ocorr�ncia do c�digo de retorno
		// Caso o registro seja do SPC
		// 3.0
		// -------------------------------------------------------------------------------
		String registro1 = (String) registro[0];
		String registro2 = (String) registro[1];

		campoCodigoRetorno1 = getConteudo(325, 10, registro1.toCharArray()).trim();
		campoCodigoRetorno2 = getConteudo(325, 10, registro2.toCharArray()).trim();

		// ------------------------------------------------------------------------------
		// Para o REGISTRO 01
		// ------------------------------------------------------------------------------

		if (registro2 != null && !"".equals(registro2)) {

			if (campoCodigoRetorno2.equals("0000000000") && !campoCodigoRetorno2.equals("")) {
				if (!campoCodigoRetorno1.equals("0000000000") && !campoCodigoRetorno1.equals("")) {
					campoCodigoRetorno2 = "0000000098";
				}
			}

			int cont = 0;
			for (int j = 0; j < 9; j = j + 2) {

				codigoRetorno = campoCodigoRetorno2.substring(j, j + 2);

				if (codigoRetorno.equals("00")) {
					cont = cont + 1;
				}

				if (!codigoRetorno.equals("00") || (cont == 5 && codigoRetorno.equals("00"))) {
					Integer codRetorno = Util.converterStringParaInteger(codigoRetorno);

					NegativadorMovimentoRegRetMot negativadorMovimentoRegRetMot = new NegativadorMovimentoRegRetMot();
					negativadorMovimentoRegRetMot.setNegativadorMovimentoReg(negativadorMovimentoReg);

					FiltroNegativadorRetornoMotivo fnrm = new FiltroNegativadorRetornoMotivo();
					fnrm.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codRetorno.shortValue()));
					fnrm.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR,
							Negativador.NEGATIVADOR_SPC));

					NegativadorRetornoMotivo negativadorRetornoMot = null;

					try {

						negativadorRetornoMot = (NegativadorRetornoMotivo) Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().pesquisar(fnrm,
								NegativadorRetornoMotivo.class.getName()));

					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

					// [FS007] - Verifica a exixt�ncia do motivo de
					if (negativadorRetornoMot != null) {
						negativadorMovimentoRegRetMot.setNegativadorMovimentoReg(negativadorMovimentoReg);
						negativadorMovimentoRegRetMot.setNegativadorRetornoMotivo(negativadorRetornoMot);
						negativadorMovimentoRegRetMot.setUltimaAlteracao(new Date());

						// [FS008] -Verifica a indica��o de registro aceito
						// Caso n�o corresponda a aceito
						if (negativadorRetornoMot.getIndicadorRegistroAceito() != 1) {
							indicadorRegistroAceito = 2;
							negativadorRetornoMot.setIndicadorRegistroAceito(indicadorRegistroAceito);
						}

						// ************************************************************************
						// 03/12/2008
						// Consultar antes, e s� inserir se n�o existir.
						// RepositorioUtilHBM.getInstancia().inserir(negativadorMovimentoRegRetMot);
						this.inserirNegativadorMovimentoRegRetMot(negativadorMovimentoRegRetMot);

						// ************************************************************************

					} else {

						throw new ControladorException("atencao.arquivo_movimento_codigo_retorno_invalido");
					}

				}

			}

		}

		// ----------------------------------------------------------------------------------------------
		// 4.0
		// ------------------------------------------------------------------------------------------------
		negativadorMovimentoReg.setIndicadorAceito(indicadorRegistroAceito);
		// -------------------------------------------------------------------------------------------------
		// [in�cio] - Altera��o 05/05/2008 - Indicar Exclus�o do im�vel caso a
		// inclus�o da negativa��o n�o seja aceita.
		// -------------------------------------------------------------------------------------------------
		NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();

		Imovel imovel = negativadorMovimentoReg.getImovel();
		// 5.0
		if (negativadorMovimento.getCodigoMovimento() == 1 && imovel != null) {

			try {

				if (indicadorRegistroAceito == ConstantesSistema.NAO_ACEITO) {
					// FiltroNegativacaoImoveis filtroNegativacaoImoveis = new
					// FiltroNegativacaoImoveis();
					// filtroNegativacaoImoveis.adicionarParametro(new
					// ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID,imovel.getId()));
					// filtroNegativacaoImoveis.adicionarParametro(new
					// ParametroSimples(FiltroNegativacaoImoveis.NEGATIVADOR_ID,negativador.getId()));
					// filtroNegativacaoImoveis.adicionarParametro(new
					// ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID,
					// negativadorMovimento.getNegativacaoComando().getId()));
					//
					// NegativacaoImovei negativacaoImoveis =
					// (NegativacaoImovei)
					// Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().
					// pesquisar(filtroNegativacaoImoveis,NegativacaoImovei.class.getName()));

					NegativacaoImoveis negativacaoImoveis = obterNegativacaoImoveis(negativador, negativadorMovimento, imovel,
							negativadorMovimentoReg.getCliente());

					if (negativacaoImoveis != null) {
						// 5.1
						short indicadorExcluido = 1;
						negativacaoImoveis.setIndicadorExcluido(indicadorExcluido);

						Date dataExclusao = new Date();
						negativacaoImoveis.setDataExclusao(dataExclusao);

						Date ultimaAlteracao = new Date();
						negativacaoImoveis.setUltimaAlteracao(ultimaAlteracao);

						// indicar a exclus�o do im�vel.
						RepositorioUtilHBM.getInstancia().atualizar(negativacaoImoveis);

						// CRC3323 - comentado por Vivianne Sousa -
						// analista:Fatima Sampaio - 10/05/2010
						// 5.2
						// FiltroImovel filtroImovel = new FiltroImovel();
						// filtroImovel.adicionarParametro(new
						// ParametroSimples(FiltroImovel.ID,imovel.getId()));
						// filtroImovel.adicionarParametro(new
						// ParametroSimples(FiltroImovel.COBRANCA_SITUACAO_ID,CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO));
						//
						// Imovel imovelRetorno = (Imovel)
						// Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().
						// pesquisar(filtroImovel,Imovel.class.getName()));
						//
						// if(imovelRetorno!= null){
						// imovelRetorno.setCobrancaSituacao(null);
						// imovelRetorno.setUltimaAlteracao(new Date());
						// RepositorioUtilHBM.getInstancia().atualizar(imovelRetorno);
						// }

						// CRC3323 - alterado por Vivianne Sousa -
						// analista:Fatima Sampaio - 10/05/2010
						// 5.3
						CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
						cobrancaSituacao.setId(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC);

						ImovelCobrancaSituacao imovelCobrancaSituacao = repositorioSpcSerasa.getImovelCobrancaSituacao(imovel, cobrancaSituacao,
								negativadorMovimentoReg.getCliente().getId());

						if (imovelCobrancaSituacao != null) {
							imovelCobrancaSituacao.setDataRetiradaCobranca(new Date());
							imovelCobrancaSituacao.setUltimaAlteracao(new Date());
							RepositorioUtilHBM.getInstancia().atualizar(imovelCobrancaSituacao);
						}

					}

					// 6.2
				} else if (indicadorRegistroAceito == ConstantesSistema.ACEITO) {
					// adicionado por Vivianne Sousa - 12/03/2010 - analista:
					// F�tima Sampaio

					// 6.2.1. Atualizar a situa��o de cobran�a do im�vel na
					// tabela IMOVEL
					Integer idCobrancaSituacaoImovel = CobrancaSituacao.CARTA_ENVIADA_AO_SPC;
					// getControladorImovel().atualizarSituacaoCobrancaImovel(idCobrancaSituacaoImovel,
					// CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO,imovel.getId());
					ImovelCobrancaSituacao imovelCobrancaSituacao = null;

					// 6.2.2. Atualizar a situa��o de cobran�a do im�vel na
					// tabela IMOVEL_COBRANCA_SITUACAO
					List collImovelCobrancaSituacao = this.repositorioSpcSerasa.consultarImovelCobrancaSituacaoAtual(imovel.getId(),
							CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC, negativadorMovimentoReg.getCliente().getId());
					if (collImovelCobrancaSituacao != null && !collImovelCobrancaSituacao.isEmpty()) {
						Iterator iter = collImovelCobrancaSituacao.iterator();
						while (iter.hasNext()) {
							Integer idImovelCobrancaSituacao = (Integer) iter.next();
							getControladorImovel().atualizarDataRetiradaImovelSituacaoCobranca(idImovelCobrancaSituacao, new Date());
							imovelCobrancaSituacao = getControladorImovel().obterImovelCobrancaSituacao(idImovelCobrancaSituacao);
						}
					}

					// 6.2.3. Incluir a nova situa��o de cobran�a do im�vel na
					// tabela IMOVEL_COBRANCA_SITUACAO
					List idImovelCobrancaSituacaoNegativadoAutomaticamente = this.repositorioSpcSerasa.consultarImovelCobrancaSituacaoAtual(imovel.getId(),
							idCobrancaSituacaoImovel, negativadorMovimentoReg.getCliente().getId());
					// verifica��o adicionada para permitir reiniciar o batch
					if (idImovelCobrancaSituacaoNegativadoAutomaticamente == null || idImovelCobrancaSituacaoNegativadoAutomaticamente.isEmpty()) {
						ImovelCobrancaSituacao ics = new ImovelCobrancaSituacao();
						CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
						cobrancaSituacao.setId(idCobrancaSituacaoImovel);
						ics.setImovel(imovel);
						ics.setDataImplantacaoCobranca(new Date());
						ics.setDataRetiradaCobranca(null);
						ics.setCobrancaSituacao(cobrancaSituacao);
						ics.setCliente(negativadorMovimentoReg.getCliente());
						ics.setUltimaAlteracao(new Date());

						// RM6364 - Altera��o para negativa��o por per�odo
						// alterado por Vivianne Sousa - 12/12/2011
						if (imovelCobrancaSituacao != null) {
							if (imovelCobrancaSituacao.getAnoMesReferenciaInicio() != null) {
								ics.setAnoMesReferenciaInicio(imovelCobrancaSituacao.getAnoMesReferenciaInicio());
							}
							if (imovelCobrancaSituacao.getAnoMesReferenciaFinal() != null) {
								ics.setAnoMesReferenciaFinal(imovelCobrancaSituacao.getAnoMesReferenciaFinal());
							}
						}

						RepositorioUtilHBM.getInstancia().inserir(ics);
					}

					// 6.2.4. Atualizar a situa��o de cobran�a do im�vel na
					// tabela NEGATIVADOR_MOVIMENTO_REG
					// repositorioSpcSerasa.atualizarSituacaoCobrancaNegativadorMovimentoReg(
					// idCobrancaSituacaoImovel,negativadorMovimentoReg.getId());
					CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
					cobrancaSituacao.setId(idCobrancaSituacaoImovel);
					negativadorMovimentoReg.setCobrancaSituacao(cobrancaSituacao);

				}

			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}

		// -------------------------------------------------------------------------------------------------
		// [fim] - Altera��o 05/05/2008 - Indicar Exclus�o do im�vel caso a
		// inclus�o da negativa��o n�o seja aceita.
		// -------------------------------------------------------------------------------------------------

		try {
			negativadorMovimentoReg.setIndicadorAceito(indicadorRegistroAceito);
			RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	private void inserirNegativadorMovimentoRegRetMot(NegativadorMovimentoRegRetMot negativadorMovimentoRegRetMot)
			throws ControladorException {
		
		if (negativadorMovimentoRegRetMot != null) {
			try {
				Integer idNegativadorMovimentoRegRetMot = this.repositorioSpcSerasa.pesquisarNegativadorMovimentoRegRetMot(
						negativadorMovimentoRegRetMot.getNegativadorMovimentoReg().getId(),
						negativadorMovimentoRegRetMot.getNegativadorRetornoMotivo().getId());

				if (idNegativadorMovimentoRegRetMot == null) {
					RepositorioUtilHBM.getInstancia().inserir(negativadorMovimentoRegRetMot);
				}

			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
		}
	}

	/**
	 * Registrar Movimento de Retorno dos Negativadores
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores
	 * [SB0005] - Atualizar Registro de Envio
	 */
	private void atualizarRegistroEnvio(Negativador negativador, String registro, NegativadorMovimentoReg negativadorMovimentoReg)
			throws ControladorException, ErroRepositorioException {

		short indicadorRegistroAceito = -1;
		String tipoRegistro = "";
		String sequencialArquivo = "";
		String sequencialRegistro = "";
		String campoCodigoRetorno = "";

		if (Negativador.NEGATIVADOR_SERASA.equals(negativador.getId())) {
			String quantidadeZeros = "";
			sequencialArquivo = getConteudo(594, 7, registro.toCharArray());

			sequencialRegistro = negativadorMovimentoReg.getNumeroRegistro() + "";

			int tamanho = 7 - sequencialRegistro.length();

			for (int i = 0; i < tamanho; i++) {
				quantidadeZeros = quantidadeZeros + "0";
			}

			sequencialRegistro = quantidadeZeros + sequencialRegistro;
			if (!tipoRegistro.equals("0") && !tipoRegistro.equals("9")) {
				if (!sequencialArquivo.equals(sequencialRegistro)) {
					throw new ControladorException("atencao.conteudo_registro_nao_corresponde_ao_enviado", null, sequencialArquivo);
				}
			}

			indicadorRegistroAceito = 1;
			campoCodigoRetorno = getConteudo(534, 60, registro.toCharArray()).trim();
			
			if (campoCodigoRetorno.equals("")) {
				String codigoRetorno = "0";

				indicadorRegistroAceito = this.inserirNegativadorMovimentoRegistroRetornoMotivo(
						negativadorMovimentoReg, indicadorRegistroAceito, codigoRetorno);
				
			} else {
				for (int j = 0; j <= campoCodigoRetorno.length() - 3; j = j + 3) {
					String codigoRetorno = campoCodigoRetorno.substring(j, j + 3);

					if (!codigoRetorno.equals("")) {
						indicadorRegistroAceito = this.inserirNegativadorMovimentoRegistroRetornoMotivo(
								negativadorMovimentoReg, indicadorRegistroAceito, codigoRetorno);
					}
				}
			}
		}
		
		negativadorMovimentoReg.setIndicadorAceito(indicadorRegistroAceito);
		NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();

		Imovel imovel = negativadorMovimentoReg.getImovel();

		if (negativadorMovimento.getCodigoMovimento() == 1 && imovel != null) {
			try {
				if (indicadorRegistroAceito == ConstantesSistema.NAO_ACEITO) {
					NegativacaoImoveis negativacaoImoveis = obterNegativacaoImoveis(negativador,
							negativadorMovimento, imovel, negativadorMovimentoReg.getCliente());

					if (negativacaoImoveis != null) {
						negativacaoImoveis.setIndicadorExcluido(ConstantesSistema.SIM);
						negativacaoImoveis.setDataExclusao(new Date());
						negativacaoImoveis.setUltimaAlteracao(new Date());
						RepositorioUtilHBM.getInstancia().atualizar(negativacaoImoveis);

						CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
						if (Negativador.NEGATIVADOR_SERASA.equals(negativador.getId())) {
							cobrancaSituacao.setId(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SERASA);
						} else if (Negativador.NEGATIVADOR_SPC.equals(negativador.getId())) {
							cobrancaSituacao.setId(CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC);
						}

						ImovelCobrancaSituacao imovelCobrancaSituacao = repositorioSpcSerasa.getImovelCobrancaSituacao(
								imovel, cobrancaSituacao, negativadorMovimentoReg.getCliente().getId());

						if (imovelCobrancaSituacao != null) {
							imovelCobrancaSituacao.setDataRetiradaCobranca(new Date());
							imovelCobrancaSituacao.setUltimaAlteracao(new Date());
							RepositorioUtilHBM.getInstancia().atualizar(imovelCobrancaSituacao);
						}

						negativadorMovimentoReg.setCobrancaSituacao(cobrancaSituacao);
						negativadorMovimentoReg.setUltimaAlteracao(new Date());
						RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);
					}
				} else if (indicadorRegistroAceito == ConstantesSistema.ACEITO) {
					Integer idCobrancaSituacaoImovel = null;
					Integer indicadorAnaliseNegativacao = null;
					
					if (negativador.getId().equals(Negativador.NEGATIVADOR_SPC)) {
						idCobrancaSituacaoImovel = CobrancaSituacao.CARTA_ENVIADA_AO_SPC;
						indicadorAnaliseNegativacao = CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC;
					} else if (negativador.getId().equals(Negativador.NEGATIVADOR_SERASA)) {
						idCobrancaSituacaoImovel = CobrancaSituacao.CARTA_ENVIADA_A_SERASA;
						indicadorAnaliseNegativacao = CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SERASA;
					}

					ImovelCobrancaSituacao imovelCobrancaSituacao = null;

					List colecaoImovelCobrancaSituacao = this.repositorioSpcSerasa.consultarImovelCobrancaSituacaoAtual(
							imovel.getId(), indicadorAnaliseNegativacao, negativadorMovimentoReg.getCliente().getId());
					
					for (int i = 0; i < colecaoImovelCobrancaSituacao.size(); i++) {
						Integer idImovelCobrancaSituacao = (Integer) colecaoImovelCobrancaSituacao.get(i);
						getControladorImovel().atualizarDataRetiradaImovelSituacaoCobranca(idImovelCobrancaSituacao, new Date());
						imovelCobrancaSituacao = getControladorImovel().obterImovelCobrancaSituacao(idImovelCobrancaSituacao);
					}

					List idImovelCobrancaSituacaoNegativadoAutomaticamente = this.repositorioSpcSerasa.consultarImovelCobrancaSituacaoAtual(
							imovel.getId(), idCobrancaSituacaoImovel, negativadorMovimentoReg.getCliente().getId());

					if (idImovelCobrancaSituacaoNegativadoAutomaticamente == null || idImovelCobrancaSituacaoNegativadoAutomaticamente.isEmpty()) {
						ImovelCobrancaSituacao imovelCobrancaSituacaoAtual = new ImovelCobrancaSituacao();
						imovelCobrancaSituacaoAtual.setImovel(imovel);
						imovelCobrancaSituacaoAtual.setDataImplantacaoCobranca(new Date());
						imovelCobrancaSituacaoAtual.setDataRetiradaCobranca(null);
						imovelCobrancaSituacaoAtual.setCobrancaSituacao(new CobrancaSituacao(idCobrancaSituacaoImovel));
						imovelCobrancaSituacaoAtual.setCliente(negativadorMovimentoReg.getCliente());
						imovelCobrancaSituacaoAtual.setUltimaAlteracao(new Date());

						if (imovelCobrancaSituacao != null) {
							if (imovelCobrancaSituacao.getAnoMesReferenciaInicio() != null) {
								imovelCobrancaSituacaoAtual.setAnoMesReferenciaInicio(imovelCobrancaSituacao.getAnoMesReferenciaInicio());
							}
							if (imovelCobrancaSituacao.getAnoMesReferenciaFinal() != null) {
								imovelCobrancaSituacaoAtual.setAnoMesReferenciaFinal(imovelCobrancaSituacao.getAnoMesReferenciaFinal());
							}
						}
						RepositorioUtilHBM.getInstancia().inserir(imovelCobrancaSituacaoAtual);
					}

					CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
					cobrancaSituacao.setId(idCobrancaSituacaoImovel);
					negativadorMovimentoReg.setCobrancaSituacao(cobrancaSituacao);
				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}

		try {
			negativadorMovimentoReg.setIndicadorAceito(indicadorRegistroAceito);
			negativadorMovimentoReg.setUltimaAlteracao(new Date());
			RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);
			logger.info("Negativador Movimento Registro [" + negativadorMovimentoReg.getId() + "] Atualizado");
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	private short inserirNegativadorMovimentoRegistroRetornoMotivo(NegativadorMovimentoReg negativadorMovimentoReg, short indicadorRegistroAceito,
			String codigoRetorno) throws ErroRepositorioException, ControladorException {
		NegativadorMovimentoRegRetMot negativadorMovimentoRegRetMot = new NegativadorMovimentoRegRetMot();
		negativadorMovimentoRegRetMot.setNegativadorMovimentoReg(negativadorMovimentoReg);

		FiltroNegativadorRetornoMotivo filtroRetornoMotivo = new FiltroNegativadorRetornoMotivo();
		filtroRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codigoRetorno));
		filtroRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR,
				Negativador.NEGATIVADOR_SERASA));

		NegativadorRetornoMotivo negativadorRetornoMotivo = (NegativadorRetornoMotivo) Util.retonarObjetoDeColecao(
				RepositorioUtilHBM.getInstancia().pesquisar(filtroRetornoMotivo, NegativadorRetornoMotivo.class.getName()));

		if (negativadorRetornoMotivo != null) {

			negativadorMovimentoRegRetMot.setNegativadorMovimentoReg(negativadorMovimentoReg);
			negativadorMovimentoRegRetMot.setNegativadorRetornoMotivo(negativadorRetornoMotivo);
			negativadorMovimentoRegRetMot.setUltimaAlteracao(new Date());

			if (negativadorRetornoMotivo.getIndicadorRegistroAceito() != 1) {
				indicadorRegistroAceito = 2;
			}

			this.inserirNegativadorMovimentoRegRetMot(negativadorMovimentoRegRetMot);
		} else {
			throw new ControladorException("atencao.arquivo_movimento_codigo_retorno_invalido");
		}
		return indicadorRegistroAceito;
	}

	public NegativacaoImoveis obterNegativacaoImoveis(Negativador negativador, NegativadorMovimento negativadorMovimento,
			Imovel imovel, Cliente cliente) throws ControladorException {

		try {
			FiltroNegativacaoImoveis filtro = new FiltroNegativacaoImoveis();
			filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID, imovel.getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVADOR_ID, negativador.getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID,
					negativadorMovimento.getNegativacaoComando().getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.ID_CLIENTE, cliente.getId()));

			NegativacaoImoveis negativacaoImoveis = (NegativacaoImoveis) Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().pesquisar(
					filtro, NegativacaoImoveis.class.getName()));

			if (negativacaoImoveis == null) {
				filtro = new FiltroNegativacaoImoveis();
				filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID, imovel.getId()));
				filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVADOR_ID, negativador.getId()));
				filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID, negativadorMovimento
						.getNegativacaoComando().getId()));

				negativacaoImoveis = (NegativacaoImoveis) Util.retonarObjetoDeColecao(RepositorioUtilHBM.getInstancia().pesquisar(filtro,
						NegativacaoImoveis.class.getName()));
			}

			return negativacaoImoveis;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso matr�cula)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(ComandoNegativacaoHelper comandoNegativacaoHelper,
			Integer numeroPagina) throws ControladorException {

		Collection retorno = new ArrayList();

		try {
			retorno = repositorioSpcSerasa.pesquisarComandoNegativacaoTipoMatricula(comandoNegativacaoHelper,
					numeroPagina);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso crit�rio)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper, Integer numeroPagina)
			throws ControladorException {

		Collection retorno = new ArrayList();

		try {
			retorno = repositorioSpcSerasa.pesquisarComandoNegativacaoTipoCriterio(
					comandoNegativacaoTipoCriterioHelper, numeroPagina);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * M�todo usado para consulta de comandos de negativa��o por tipo de comando
	 * (nesse caso matr�cula)usado no caso de uso [UC0691] (sem pagina��o)
	 * 
	 * @author Yara Taciane ,Vivianne Sousa
	 * @date 21/01/2008,14/12/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarNegativadorMovimentoCount(NegativadorMovimentoHelper negativadorMovimentoHelper)
			throws ControladorException {

		try {
			return repositorioSpcSerasa.pesquisarNegativadorMovimentoCount(negativadorMovimentoHelper);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * M�todo usado para contar a quantidade de ocorr�ncias de
	 * negativadorMovimento Registro aceitos usado no caso de uso [UC061]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer verificarTotalRegistrosAceitos(Integer idNegativadorMovimento) throws ControladorException {

		Integer retorno;

		try {
			retorno = repositorioSpcSerasa.verificarTotalRegistrosAceitos(idNegativadorMovimento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * M�todo usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper)
			throws ControladorException {

		Collection retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarNegativadorMovimentoRegistroAceito(negativadorMovimentoHelper);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

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
	public void removerComandoNegativacaoPorCriterio(String[] ids) throws ControladorException {

		try {
			for (int i = 0; i < ids.length; i++) {

				NegativacaoCriterio negativacaoCriterio = repositorioSpcSerasa.pesquisarNegativacaoCriterio(Integer
						.parseInt(ids[i]));

				/*
				 * Remove Titularidades do CPF/CNPJ da Negativa��o,
				 * Subcategorias, Perfis de im�vel, Tipos de cliente, Grupos de
				 * Cobran�a, Ger�ncias Regionais, Unidades Neg�cio, Elos P�lo do
				 * crit�rio
				 */

				repositorioSpcSerasa.removerParametrosCriterio(negativacaoCriterio.getId());

				// Remove Negativa��o Crit�rio
				getControladorUtil().remover(negativacaoCriterio);

				// Remove Negativa��o Comando
				repositorioSpcSerasa.removerNegativacaoComando(Integer.parseInt(ids[i]));

			}
			// remove primeiro as linhas do crit�rio cobran�a

			// ------------ REGISTRAR TRANSA��O ----------------
			/*
			 * Operacao operacao = new Operacao();
			 * operacao.setId(Operacao.OPERACAO_CRITERIO_COBRANCA_REMOVER);
			 * 
			 * OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			 * operacaoEfetuada.setOperacao(operacao); // ------------ REGISTRAR
			 * TRANSA��O ----------------
			 * 
			 * //repositorioCobranca.removerCobrancaCriterioLinha(
			 * idsCobrancaCriterio);
			 * 
			 * // ------------ REGISTRAR TRANSA��O ----------------
			 * UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new
			 * UsuarioAcaoUsuarioHelper( usuarioLogado,
			 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			 * Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new
			 * ArrayList(); colecaoUsuarios.add(usuarioAcaoUsuarioHelper); //
			 * ------------ REGISTRAR TRANSA��O ----------------
			 */

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

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
			throws ControladorException {

		try {

			// [FS0012]- Verificar exist�ncia de comando para os mesmos
			// par�metros
			String nomeNegativacaoComando = repositorioSpcSerasa
					.verificarExistenciaComandoMesmoParametroAtualizacao(helper);

			if (nomeNegativacaoComando != null && !nomeNegativacaoComando.equals("")) {
				throw new ControladorException("atencao.comando_nao_realizado_mesmo_parametro", null,
						nomeNegativacaoComando);
			}

			FiltroNegativacaoComando filtroNegativacaoComando = new FiltroNegativacaoComando();

			// Seta o filtro para buscar o cliente na base
			filtroNegativacaoComando.adicionarParametro(new ParametroSimples(FiltroNegativacaoComando.ID, helper
					.getNegativacaoComando().getId()));

			Collection colecaoNegativacaoComando = getControladorUtil().pesquisar(filtroNegativacaoComando,
					NegativacaoComando.class.getName());

			// verifica se a negativa��o comando ainda existe na base, porque
			// ela pode ter sido excluida com isso
			// n�o � poss�vel analizar a data de ultima altera��o
			if (colecaoNegativacaoComando == null || colecaoNegativacaoComando.isEmpty()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Procura a negativa��o comando na base
			NegativacaoComando negativacaoComandoNaBase = (NegativacaoComando) ((List) colecaoNegativacaoComando)
					.get(0);

			// Verificar se a negativa��o comando j� foi atualizado por outro
			// usu�rio
			// durante esta atualiza��o
			if (negativacaoComandoNaBase.getUltimaAlteracao()
					.after(helper.getNegativacaoComando().getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Atualizar Comando Negativacao
			NegativacaoComando negativacaoComando = helper.getNegativacaoComando();
			negativacaoComando.setUltimaAlteracao(new Date());

			if (negativacaoComando.getIndicadorBaixaRenda() == null) {
				negativacaoComando.setIndicadorBaixaRenda(ConstantesSistema.NAO);
			}

			getControladorUtil().atualizar(negativacaoComando);

			// Atualizar Negativacao Criterio
			NegativacaoCriterio negativacaoCriterio = helper.getNegativacaoCriterio();
			negativacaoCriterio.setUltimaAlteracao(new Date());

			getControladorUtil().atualizar(negativacaoCriterio);

			// Remover
			repositorioSpcSerasa.removerParametrosCriterio(negativacaoCriterio.getId());

			// Incluir Negativacao Criterio CPF Tipo
			Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo = helper
					.getColecaoNegativacaoCriterioCpfTipo();
			Iterator negativacaoCriterioCpfTipoIterator = colecaoNegativacaoCriterioCpfTipo.iterator();
			while (negativacaoCriterioCpfTipoIterator.hasNext()) {
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = (NegativacaoCriterioCpfTipo) negativacaoCriterioCpfTipoIterator
						.next();
				negativacaoCriterioCpfTipo.setNegativacaoCriterio(negativacaoCriterio);
				negativacaoCriterioCpfTipo.setUltimaAlteracao(new Date());
				this.getControladorUtil().inserir(negativacaoCriterioCpfTipo);
			}

			// Incluir Negativacao Liga��o �gua
			if (helper.getIdsLigacaoAguaSituacao() != null && helper.getIdsLigacaoAguaSituacao().length > 0) {
				String[] idsLigacaoAguaSituacao = (String[]) helper.getIdsLigacaoAguaSituacao();
				int indexLigacaoAguaSituacao = 0;

				if (!idsLigacaoAguaSituacao[indexLigacaoAguaSituacao].equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsLigacaoAguaSituacao.length > indexLigacaoAguaSituacao) {
						Integer idLigacaoAguaSituacao = new Integer(idsLigacaoAguaSituacao[indexLigacaoAguaSituacao]);
						NegativacaoCriterioLigacaoAgua ncLigacaoAgua = new NegativacaoCriterioLigacaoAgua();
						LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
						ligacaoAguaSituacao.setId(idLigacaoAguaSituacao);

						NegativacaoCriterioLigacaoAguaPK negativacaoCriterioLigacaoAguaPK = new NegativacaoCriterioLigacaoAguaPK();
						negativacaoCriterioLigacaoAguaPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioLigacaoAguaPK.setLigacaoAguaSituacao(ligacaoAguaSituacao);
						ncLigacaoAgua.setComp_id(negativacaoCriterioLigacaoAguaPK);
						ncLigacaoAgua.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncLigacaoAgua);

						indexLigacaoAguaSituacao++;
					}
				}
			}

			// Incluir Negativacao Liga��o Esgoto
			if (helper.getIdsLigacaoEsgotoSituacao() != null && helper.getIdsLigacaoEsgotoSituacao().length > 0) {
				String[] idsLigacaoEsgotoSituacao = (String[]) helper.getIdsLigacaoEsgotoSituacao();
				int indexLigacaoEsgotoSituacao = 0;

				if (!idsLigacaoEsgotoSituacao[indexLigacaoEsgotoSituacao].equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsLigacaoEsgotoSituacao.length > indexLigacaoEsgotoSituacao) {
						Integer idLigacaoEsgotoSituacao = new Integer(
								idsLigacaoEsgotoSituacao[indexLigacaoEsgotoSituacao]);
						NegativacaoCriterioLigacaoEsgoto ncLigacaoEsgoto = new NegativacaoCriterioLigacaoEsgoto();
						LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
						ligacaoEsgotoSituacao.setId(idLigacaoEsgotoSituacao);

						NegativacaoCriterioLigacaoEsgotoPK negativacaoCriterioLigacaoEsgotoPK = new NegativacaoCriterioLigacaoEsgotoPK();
						negativacaoCriterioLigacaoEsgotoPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioLigacaoEsgotoPK.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
						ncLigacaoEsgoto.setComp_id(negativacaoCriterioLigacaoEsgotoPK);
						ncLigacaoEsgoto.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncLigacaoEsgoto);

						indexLigacaoEsgotoSituacao++;
					}
				}
			}

			// Incluir Negativacao Criterio SubCategoria
			if (helper.getIdsSubcategoria() != null && helper.getIdsSubcategoria().length > 0) {
				String[] idsSubCategoria = (String[]) helper.getIdsSubcategoria();
				int indexSubCategoria = 0;
				if (!idsSubCategoria[indexSubCategoria].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsSubCategoria.length > indexSubCategoria) {
						Integer idSubCategoria = new Integer(idsSubCategoria[indexSubCategoria]);
						NegativacaoCriterioSubcategoria ncSubacategoria = new NegativacaoCriterioSubcategoria();
						Subcategoria subcategoria = new Subcategoria();
						subcategoria.setId(idSubCategoria);

						NegativacaoCriterioSubcategoriaPK negativacaoCriterioSubcategoriaPK = new NegativacaoCriterioSubcategoriaPK();
						negativacaoCriterioSubcategoriaPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioSubcategoriaPK.setSubcategoria(subcategoria);
						ncSubacategoria.setComp_id(negativacaoCriterioSubcategoriaPK);
						ncSubacategoria.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncSubacategoria);

						indexSubCategoria++;
					}
				}
			}

			// Incluir Negativacao Imovel Perfil
			if (helper.getIdsPerfilImovel() != null && helper.getIdsPerfilImovel().length > 0) {
				String[] idsPerfilImovel = (String[]) helper.getIdsPerfilImovel();
				int indexPerfilaImovel = 0;

				if (!idsPerfilImovel[indexPerfilaImovel].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsPerfilImovel.length > indexPerfilaImovel) {
						Integer idPerfilImovel = new Integer(idsPerfilImovel[indexPerfilaImovel]);
						NegativacaoCriterioImovelPerfil ncImovelPerfil = new NegativacaoCriterioImovelPerfil();
						ImovelPerfil imovelPerfil = new ImovelPerfil();
						imovelPerfil.setId(idPerfilImovel);

						NegativacaoCriterioImovelPerfilPK negativacaoCriterioImovelPerfilPK = new NegativacaoCriterioImovelPerfilPK();
						negativacaoCriterioImovelPerfilPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioImovelPerfilPK.setImovelPerfil(imovelPerfil);
						ncImovelPerfil.setComp_id(negativacaoCriterioImovelPerfilPK);
						ncImovelPerfil.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncImovelPerfil);

						indexPerfilaImovel++;
					}
				}
			}

			// Incluir Negativacao Cliente Tipo
			if (helper.getIdsTipoCliente() != null && helper.getIdsTipoCliente().length > 0) {
				String[] idsTipoCliente = (String[]) helper.getIdsTipoCliente();
				int indexClienteTipo = 0;

				if (!idsTipoCliente[indexClienteTipo].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsTipoCliente.length > indexClienteTipo) {
						Integer idTipoCliente = new Integer(idsTipoCliente[indexClienteTipo]);
						NegativacaoCriterioClienteTipo ncClienteTipo = new NegativacaoCriterioClienteTipo();
						ClienteTipo clienteTipo = new ClienteTipo();
						clienteTipo.setId(idTipoCliente);

						NegativacaoCriterioClienteTipoPK negativacaoCriterioClienteTipoPK = new NegativacaoCriterioClienteTipoPK();
						negativacaoCriterioClienteTipoPK.setNegativacaoCriterio(negativacaoCriterio);
						negativacaoCriterioClienteTipoPK.setClienteTipo(clienteTipo);
						ncClienteTipo.setComp_id(negativacaoCriterioClienteTipoPK);
						ncClienteTipo.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncClienteTipo);

						indexClienteTipo++;
					}
				}
			}

			// Incluir Negativacao Grupo Cobran�a
			if (helper.getIdsCobrancaGrupo() != null && helper.getIdsCobrancaGrupo().length > 0) {
				String[] idsCobrancaGrupo = (String[]) helper.getIdsCobrancaGrupo();
				int indexCobrancaGrupo = 0;

				if (!idsCobrancaGrupo[indexCobrancaGrupo]
						.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsCobrancaGrupo.length > indexCobrancaGrupo) {
						Integer idCobrancaGrupo = new Integer(idsCobrancaGrupo[indexCobrancaGrupo]);
						NegativCritCobrGrupo ncCobrGrupo = new NegativCritCobrGrupo();
						CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
						cobrancaGrupo.setId(idCobrancaGrupo);

						NegativCritCobrGrupoPK negativCritCobrGrupoPK = new NegativCritCobrGrupoPK();
						negativCritCobrGrupoPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritCobrGrupoPK.setCobrancaGrupo(cobrancaGrupo);
						ncCobrGrupo.setComp_id(negativCritCobrGrupoPK);
						ncCobrGrupo.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(ncCobrGrupo);

						indexCobrancaGrupo++;
					}
				}
			}

			// Incluir Negativacao Gerencia Regional
			if (helper.getIdsGerenciaRegional() != null && helper.getIdsGerenciaRegional().length > 0) {
				String[] idsGerenciaRegional = (String[]) helper.getIdsGerenciaRegional();
				int indexGerenciaRegional = 0;

				if (!idsGerenciaRegional[indexGerenciaRegional].equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsGerenciaRegional.length > indexGerenciaRegional) {
						Integer idGerenciaRegional = new Integer(idsGerenciaRegional[indexGerenciaRegional]);
						NegativCritGerReg negativCritGerReg = new NegativCritGerReg();
						GerenciaRegional gerenciaRegional = new GerenciaRegional();
						gerenciaRegional.setId(idGerenciaRegional);

						NegativCritGerRegPK negativCritGerRegPK = new NegativCritGerRegPK();
						negativCritGerRegPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritGerRegPK.setGerenciaRegional(gerenciaRegional);
						negativCritGerReg.setComp_id(negativCritGerRegPK);
						negativCritGerReg.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(negativCritGerReg);

						indexGerenciaRegional++;
					}
				}
			}

			// Incluir Negativacao Unidade Negocio
			if (helper.getIdsUnidadeNegocio() != null && helper.getIdsUnidadeNegocio().length > 0) {
				String[] idsUnidadeNegocio = (String[]) helper.getIdsUnidadeNegocio();
				int indexUnidadeNegocio = 0;

				if (!idsUnidadeNegocio[indexUnidadeNegocio].equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsUnidadeNegocio.length > indexUnidadeNegocio) {
						Integer idUnidadeNegocio = new Integer(idsUnidadeNegocio[indexUnidadeNegocio]);
						NegativCritUndNeg negativCritUndNeg = new NegativCritUndNeg();
						UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
						unidadeNegocio.setId(idUnidadeNegocio);

						NegativCritUndNegPK negativCritUndNegPK = new NegativCritUndNegPK();
						negativCritUndNegPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritUndNegPK.setUnidadeNegocio(unidadeNegocio);
						negativCritUndNeg.setComp_id(negativCritUndNegPK);
						negativCritUndNeg.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(negativCritUndNeg);

						indexUnidadeNegocio++;
					}
				}
			}

			// Incluir Negativacao Elo Polo
			if (helper.getIdsEloPolo() != null && helper.getIdsEloPolo().length > 0) {
				String[] idsEloPolo = (String[]) helper.getIdsEloPolo();
				int indexEloPolo = 0;

				if (!idsEloPolo[indexEloPolo].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					while (idsEloPolo.length > indexEloPolo) {
						Integer idEloPolo = new Integer(idsEloPolo[indexEloPolo]);
						NegativCritElo negativCritElo = new NegativCritElo();
						Localidade elo = new Localidade();
						elo.setId(idEloPolo);

						NegativCritEloPK negativCritEloPK = new NegativCritEloPK();
						negativCritEloPK.setNegativacaoCriterio(negativacaoCriterio);
						negativCritEloPK.setLocalidade(elo);
						negativCritElo.setComp_id(negativCritEloPK);
						negativCritElo.setUltimaAlteracao(new Date());

						this.getControladorUtil().inserir(negativCritElo);

						indexEloPolo++;
					}
				}
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public StringBuilder geraRegistroTipoTraillerSPC(int quantidadeRegistros) throws ControladorException, ErroRepositorioException {

		StringBuilder registroTrailler = new StringBuilder();
		// T.01
		registroTrailler.append("99");
		// T.02
		quantidadeRegistros += 1;
		registroTrailler.append(Util.adicionarZerosEsquedaNumero(6, "" + quantidadeRegistros));
		// T.03
		registroTrailler.append(Util.completaString(" ", 316));
		// T.04
		registroTrailler.append(Util.completaString(" ", 10));
		// T.05
		registroTrailler.append(Util.adicionarZerosEsquedaNumero(6, "" + quantidadeRegistros));

		return registroTrailler;
	}

	public StringBuilder geraRegistroTipoTraillerSERASA(Integer numeroRegistro) throws ControladorException,
			ErroRepositorioException {

		StringBuilder registroTrailler = new StringBuilder();
		// T.01
		registroTrailler.append("9");
		// T.02
		registroTrailler.append(Util.completaString(" ", 532));
		// T.04
		registroTrailler.append(Util.completaString(" ", 60));
		// T.05
		registroTrailler.append(Util.adicionarZerosEsquedaNumero(7, numeroRegistro.toString()));

		return registroTrailler;
	}

	/**
	 * M�todo usado para pesquisar Negativador Movimento usado no caso de uso
	 * [UC0682]
	 * 
	 * @author Yara Taciane
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegativadorMovimento(NegativadorMovimentoHelper negativadorMovimentoHelper,
			Integer numeroPagina) throws ControladorException {

		Collection retorno = new ArrayList();

		try {
			retorno = repositorioSpcSerasa.pesquisarNegativadorMovimento(negativadorMovimentoHelper, numeroPagina);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * M�todo usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegativadorMovimentoRegistroAceito(
			NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina) throws ControladorException {

		Collection retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarNegativadorMovimentoRegistroAceito(negativadorMovimentoHelper,
					numeroPagina);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * M�todo totaliza os debitos do imovel usado no caso de uso [UC0671]
	 * 
	 * @author Marcio Roberto
	 * @date 20/02/2008
	 * 
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal totalizaDebitosImovelListas(Collection colecaoContasValores,
			Collection colecaoGuiasPagamentoValores) throws ControladorException {
		BigDecimal retorno;

		// [SB0006] 11.
		BigDecimal valorTotalConta = new BigDecimal(0);
		BigDecimal valorTotalGuiaPagamento = new BigDecimal(0);
		BigDecimal valorTotal = new BigDecimal(0);
		Integer quantidadeTotalItensDebito = 0;

		// Cole��o de Contas - remove valores pagos
		Iterator itColecaoContasValores = null;
		if (colecaoContasValores != null) {
			itColecaoContasValores = colecaoContasValores.iterator();
			while (itColecaoContasValores.hasNext()) {
				ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();
				if (contaValores.getValorPago() != null) {
					itColecaoContasValores.remove();
				}
			}
		}

		// [SB0006] 9.
		// Cole��o de Guias de Pagamento - remove valores pagos
		Iterator itColecaoGuiasPagamentoValores = null;
		if (colecaoGuiasPagamentoValores != null) {
			itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
			while (itColecaoGuiasPagamentoValores.hasNext()) {
				GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores
						.next();
				if (guiaPagamentoValores.getValorPago() != null) {
					itColecaoGuiasPagamentoValores.remove();
				}

			}
		}

		// Varre lista de contas para totalizar
		if (colecaoContasValores != null) {
			itColecaoContasValores = colecaoContasValores.iterator();
			while (itColecaoContasValores.hasNext()) {
				ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();
				// [SB0006] 11.1 Acumula valores total da conta.
				valorTotalConta = valorTotalConta.add(contaValores.getValorTotalConta());
				quantidadeTotalItensDebito += 1;
			}
		}

		// varre lista de guias de pagamento para totalizar
		if (colecaoGuiasPagamentoValores != null) {
			itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
			while (itColecaoGuiasPagamentoValores.hasNext()) {
				GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores
						.next();
				// [SB0006] 11.2 Acumula valor debito da guia de paramento
				valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValores.getGuiaPagamento()
						.getValorDebito());
				quantidadeTotalItensDebito += 1;
			}
		}
		// Valor total de debitos do imovel.
		retorno = valorTotal.add(valorTotalConta.add(valorTotalGuiaPagamento));

		return retorno;
	}

	/**
	 * M�todo usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegatiacaoParaImovel(Imovel imovel, Negativador negativador) throws ControladorException {

		Collection retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarNegatiacaoParaImovel(imovel, negativador);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Conta a quantidade de Clientes Negativados [UC0693] Gerar Relat�rio
	 * Acompanhamaneto de Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCount(DadosConsultaNegativacaoHelper helper)
			throws ControladorException {

		Integer retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarRelatorioAcompanhamentoClientesNegativadorCount(helper);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Conta a quantidade de Clientes Negativados [UC0693] Gerar Relat�rio
	 * Acompanhamaneto de Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Collection pesquisarRelatorioAcompanhamentoClientesNegativador(DadosConsultaNegativacaoHelper helper)
			throws ControladorException {

		Collection retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarRelatorioAcompanhamentoClientesNegativador(helper);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Retorna o somat�rio do valor do D�bito do NegativadoMovimentoReg pela
	 * CobrancaDebitoSituacao [UC0693] Gerar Relat�rio Acompanhamaneto de
	 * Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public BigDecimal pesquisarSomatorioValorDebito(NegativadorMovimentoReg negativadorMovimentoReg,
			CobrancaDebitoSituacao cobrancaDebitoSituacao) throws ControladorException {
		BigDecimal retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarSomatorioValorDebito(negativadorMovimentoReg,
					cobrancaDebitoSituacao);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Retorna o ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel) throws ControladorException {
		ImovelCobrancaSituacao retorno;

		try {
			retorno = repositorioSpcSerasa.getImovelCobrancaSituacao(imovel);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Conta a quantidade de Neg [UC0693] Gerar Relat�rio Negativacoes Excluidas
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Integer pesquisarRelatorioNegativacoesExcluidasCount(DadosConsultaNegativacaoHelper helper)
			throws ControladorException {

		Integer retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarRelatorioNegativacoesExcluidasCount(helper);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Conta a quantidade de Neg [UC0693] Gerar Relat�rio Negativacoes Excluidas
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Collection pesquisarRelatorioNegativacoesExcluidas(DadosConsultaNegativacaoHelper helper)
			throws ControladorException {

		Collection retorno = new ArrayList();

		try {
			Collection coll = repositorioSpcSerasa.pesquisarRelatorioNegativacoesExcluidas(helper);

			if (helper.getPeriodoExclusaoNegativacaoInicio() != null
					&& helper.getPeriodoExclusaoNegativacaoFim() != null) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {

					NegativadorMovimentoReg negr = (NegativadorMovimentoReg) it.next();

					Date dataExclusao = repositorioSpcSerasa.pesquisarDataExclusaoNegativacao(negr.getImovel().getId(),
							negr.getNegativadorMovimento().getNegativacaoComando().getId());
					if ((Util.compararData(dataExclusao, helper.getPeriodoExclusaoNegativacaoInicio()) == 1 || Util
							.compararData(dataExclusao, helper.getPeriodoExclusaoNegativacaoInicio()) == 0)
							&& Util.compararData(dataExclusao, helper.getPeriodoExclusaoNegativacaoFim()) == -1) {

						retorno.add(negr);

					}

				}
			} else {
				retorno = coll;
			}

		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Pesquisar se a negativa��o do im�vel . [UC0675] Excluir Negativa��o
	 * Online.
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Collection pesquisarImovelNegativado(Imovel imovel, Negativador negativador) throws ControladorException {

		Collection retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarImovelNegativado(imovel, negativador);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 *
	 * Retorna o NegativadorMovimentoReg [UC0673] Excluir Negativa��o Online
	 * 
	 * @author Yara Taciane
	 * @date 27/03/2008
	 */

	public NegativadorMovimentoReg pesquisarNegativadorMovimentoRegInclusao(Imovel imovel, Negativador negativador)
			throws ControladorException {

		NegativadorMovimentoReg retorno;

		try {
			retorno = repositorioSpcSerasa.pesquisarNegativadorMovimentoRegInclusao(imovel, negativador);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC0651] Inserir Comando Negativa��o [FS0026] Verificar exist�ncia de
	 * comando para o negativador na data
	 * 
	 * @author Ana Maria
	 * @date 07/05/2008
	 * 
	 * @param idNegativador
	 * @param Data
	 * @return boolean
	 */
	public boolean verificarExistenciaComandoNegativador(String idNegativador, Date dataPrevista)
			throws ControladorException {
		try {
			return repositorioSpcSerasa.verificarExistenciaComandoNegativador(idNegativador, dataPrevista);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa a Data da Exclus�o da Negativa��o
	 * 
	 * @author Yara Taciane
	 * @date 9/05/2008
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataExclusaoNegativacao(int idImovel, int idNegativacaoComando) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarDataExclusaoNegativacao(idImovel, idNegativacaoComando);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	public NegativacaoCriterio pesquisarComandoNegativacaoSimulado(Integer idComandoNegativacao)
			throws ControladorException {

		NegativacaoCriterio negativacaoCriterio = null;
		Object[] pesquisaComandoNegativacaoSimulacao = null;

		try {

			pesquisaComandoNegativacaoSimulacao = repositorioSpcSerasa
					.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);

		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

		if (pesquisaComandoNegativacaoSimulacao != null && !(pesquisaComandoNegativacaoSimulacao.equals(""))) {
			negativacaoCriterio = new NegativacaoCriterio();
			// seta o titulo Negativa��o Crit�rio
			if (pesquisaComandoNegativacaoSimulacao[0] != null) {
				negativacaoCriterio.setDescricaoTitulo((String) pesquisaComandoNegativacaoSimulacao[0]);
			}
			// seta o indicador de simula��o e a data da �ltima altera��o de
			// negativa��o comando
			if (pesquisaComandoNegativacaoSimulacao[1] != null) {
				NegativacaoComando negComando = new NegativacaoComando();
				negComando.setIndicadorSimulacao((Short) pesquisaComandoNegativacaoSimulacao[1]);
				negComando.setDataHoraRealizacao((Date) pesquisaComandoNegativacaoSimulacao[2]);
				negativacaoCriterio.setNegativacaoComando(negComando);
			}

		}
		return negativacaoCriterio;

	}

	/**
	 * [UC0678] Relat�rio Negativador Resultado Simulacao pesquisar Negativador
	 * Resultado Simulacao
	 * 
	 * @author Yara Taciane
	 * @date 09/05/2008
	 * 
	 * @param idNegativacaoComando
	 * @return NegativadorResultadoSimulacao
	 */
	public Collection pesquisarNegativadorResultadoSimulacao(Integer idNegativacaoComando) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarNegativadorResultadoSimulacao(idNegativacaoComando);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0678] Relat�rio Negativador Resultado Simulacao pesquisar Negativador
	 * Resultado Simulacao
	 * 
	 * @author Yara Taciane
	 * @date 09/05/2008
	 * 
	 * @param idNegativacaoComando
	 * @return NegativadorResultadoSimulacao
	 */
	public NegativacaoCriterio pesquisarNegativacaoCriterio(Integer idNegativacaoComando) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarNegativacaoCriterio(idNegativacaoComando);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Processa os itens de uma NegativadorMovimentoReg [UC0688] Gerar Resumo
	 * Di�rio da Negativa��o [SB0001] Processar Itens da Negativa��o
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 *
	 * @param nmr
	 * @param Object
	 *            []
	 * 
	 *            Integer quantidadeNegativacao = 0; Integer
	 *            quantidadeNegativacaoPendente = 0; Integer
	 *            quantidadeNegativacaoPago = 0; Integer
	 *            quantidadeNegativacaoParcelado = 0; Integer
	 *            quantidadeNegativacaoCancelado = 0;
	 *
	 *            BigDecimal valorNegativacaoPendente = new BigDecimal(0);
	 *            BigDecimal valorNegativacaoPago = new BigDecimal(0);
	 *            BigDecimal valorNegativacaoParcelado = new BigDecimal(0);
	 *            BigDecimal valorNegativacaoCancelado = new BigDecimal(0);
	 *
	 * @return Collection de NegativadorMovimentoRegItem
	 *
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private Collection verificarSituacaoDebitoItensNegativacao(NegativadorMovimentoReg nmr, Object[] obj)
			throws ControladorException, ErroRepositorioException {

		// 1.1 Atribuir o valor zero as seguintes campos:
		Integer quantidadeNegativacao = 0;
		Integer quantidadeNegativacaoPendente = 0;
		Integer quantidadeNegativacaoPago = 0;
		Integer quantidadeNegativacaoParcelado = 0;
		Integer quantidadeNegativacaoCancelado = 0;

		BigDecimal valorNegativacaoPendente = new BigDecimal(0);
		BigDecimal valorNegativacaoPago = new BigDecimal(0);
		BigDecimal valorNegativacaoParcelado = new BigDecimal(0);
		BigDecimal valorNegativacaoCancelado = new BigDecimal(0);

		Collection colecaoItens = new ArrayList();

		// 1.2 Selecionar os Itens da Negativa��o.
		Collection coll = this.repositorioSpcSerasa.consultarNegativadorMovimentoRegItem(nmr.getId());
		if (coll != null && !coll.isEmpty()) {
			Iterator it = coll.iterator();
			// 1.3 Para cada Item da Negativa��o selecionado:
			while (it.hasNext()) {

				Object[] dadosNegMovRegItem = (Object[]) it.next();

				NegativadorMovimentoRegItem nmri = new NegativadorMovimentoRegItem();
				nmri.setNegativadorMovimentoReg(nmr);
				if (dadosNegMovRegItem[0] != null) {
					nmri.setId((Integer) dadosNegMovRegItem[0]);
				}
				if (dadosNegMovRegItem[1] != null) {
					CobrancaDebitoSituacao cds = new CobrancaDebitoSituacao();
					cds.setId((Integer) dadosNegMovRegItem[1]);
					nmri.setCobrancaDebitoSituacao(cds);
				}
				if (dadosNegMovRegItem[2] != null) {
					CobrancaDebitoSituacao cds = new CobrancaDebitoSituacao();
					cds.setId((Integer) dadosNegMovRegItem[2]);
					nmri.setCobrancaDebitoSituacaoAposExclusao(cds);
				}
				if (dadosNegMovRegItem[3] != null) {
					nmri.setIndicadorSituacaoDefinitiva((Short) dadosNegMovRegItem[3]);
				}
				if (dadosNegMovRegItem[4] != null) {
					nmri.setValorDebito((BigDecimal) dadosNegMovRegItem[4]);
				}
				if (dadosNegMovRegItem[5] != null) {
					ContaGeral conta = new ContaGeral();
					conta.setId((Integer) dadosNegMovRegItem[5]);
					conta.setIndicadorHistorico((Short) dadosNegMovRegItem[6]);
					nmri.setContaGeral((ContaGeral) conta);
				}
				if (dadosNegMovRegItem[7] != null) {
					GuiaPagamentoGeral guiaPag = new GuiaPagamentoGeral();
					guiaPag.setId((Integer) dadosNegMovRegItem[7]);
					guiaPag.setIndicadorHistorico((Short) dadosNegMovRegItem[8]);
					nmri.setGuiaPagamentoGeral((GuiaPagamentoGeral) guiaPag);
				}
				if (dadosNegMovRegItem[9] != null) {
					nmri.setDataSituacaoDebito((Date) dadosNegMovRegItem[9]);
				}
				if (dadosNegMovRegItem[10] != null) {
					nmri.setDataSituacaoDebitoAposExclusao((Date) dadosNegMovRegItem[10]);
				}
				if (dadosNegMovRegItem[11] != null) {
					DocumentoTipo documentoTipo = new DocumentoTipo();
					documentoTipo.setId((Integer) dadosNegMovRegItem[11]);
					nmri.setDocumentoTipo(documentoTipo);
				}

				CobrancaDebitoSituacao cds = null;

				if (nmri != null) {
					// 1.3.1 Situa��o do D�bito do Item da Negativa��o
					cds = nmri.getCobrancaDebitoSituacao();
				}

				// 1.3.2 Caso a situa��o do item n�o seja definitiva.
				if (nmri != null && nmri.getIndicadorSituacaoDefinitiva() == 2) {

					// [SB0002] - Determinar Situa��o do D�bito do Item da
					// Negativa��o.
					Object[] rdsdin = determinarSituacaoDebitoItemNegativacao(nmri);
					cds = (CobrancaDebitoSituacao) rdsdin[0];

				}
				// 1.3.3 Adicionar 1 a quantidade de Itens da Negativa��o.
				quantidadeNegativacao = quantidadeNegativacao + 1;
				// 1.3.4 Acumular o Valor do Item de acordo com as seguintes
				// regras:

				// 1.3.4.1 Caso a Situa��o do D�bito do Item da Negativa��o
				// corresponda a Item Pendente.
				if (nmri != null && cds.getId().equals(CobrancaDebitoSituacao.PENDENTE)) {

					valorNegativacaoPendente = new BigDecimal(valorNegativacaoPendente.doubleValue()
							+ nmri.getValorDebito().doubleValue());

					quantidadeNegativacaoPendente = quantidadeNegativacaoPendente + 1;
					// 1.3.4.2 Caso a Situa��o do D�bito do Item da Negativa��o
					// corresponda a D�bito Pago.
				} else if (nmri != null && cds.getId().equals(CobrancaDebitoSituacao.PAGO)) {
					valorNegativacaoPago = new BigDecimal(valorNegativacaoPago.doubleValue()
							+ nmri.getValorDebito().doubleValue());

					quantidadeNegativacaoPago = quantidadeNegativacaoPago + 1;
					// 1.3.4.3 Caso a Situa��o do D�bito do Item da Negativa��o
					// corresponda a D�bito Parcelado.
				} else if (nmri != null && cds.getId().equals(CobrancaDebitoSituacao.PARCELADO)) {
					valorNegativacaoParcelado = new BigDecimal(valorNegativacaoParcelado.doubleValue()
							+ nmri.getValorDebito().doubleValue());

					quantidadeNegativacaoParcelado = quantidadeNegativacaoParcelado + 1;
					// 1.3.4.4 Caso a Situa��o do D�bito do Item da Negativa��o
					// corresponda a D�bito Cancelado.
				} else if (nmri != null && cds.getId().equals(CobrancaDebitoSituacao.CANCELADO)) {
					valorNegativacaoCancelado = new BigDecimal(valorNegativacaoCancelado.doubleValue()
							+ nmri.getValorDebito().doubleValue());

					quantidadeNegativacaoCancelado = quantidadeNegativacaoCancelado + 1;
				}

				colecaoItens.add(nmri);

			}

		}

		obj[0] = quantidadeNegativacao;
		obj[1] = quantidadeNegativacaoPendente;
		obj[2] = quantidadeNegativacaoPago;
		obj[3] = quantidadeNegativacaoParcelado;
		obj[4] = quantidadeNegativacaoCancelado;
		obj[5] = valorNegativacaoPendente;
		obj[6] = valorNegativacaoPago;
		obj[7] = valorNegativacaoParcelado;
		obj[8] = valorNegativacaoCancelado;

		return colecaoItens;
	}

	/**
	 * 
	 * Informa��es Atualizadas em (maior data e hora da �ltima execu��o
	 * 
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public Date getDataUltimaAtualizacaoResumoNegativacao(Integer numeroExecucaoResumoNegativacao)
			throws ControladorException {
		try {
			return repositorioSpcSerasa.getDataUltimaAtualizacaoResumoNegativacao(numeroExecucaoResumoNegativacao);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	//
	// /**
	// *
	// * Retorna o somat�rio do VALOR PARCELADO - ENTRADAdo D�bito do
	// NegativadoMovimentoReg pela CobrancaDebitoSituacao
	// * [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes Negativados
	// * @author Yara Taciane
	// * @date 04/08/2008
	// */
	// public BigDecimal
	// pesquisarSomatorioValorDebitoAposExclusao(NegativadorMovimentoReg
	// negativadorMovimentoReg,CobrancaDebitoSituacao
	// cobrancaDebitoSituacao)throws ControladorException{
	// try{
	// return
	// repositorioSpcSerasa.pesquisarSomatorioValorDebitoAposExclusao(negativadorMovimentoReg,cobrancaDebitoSituacao);
	// } catch (ErroRepositorioException e) {
	//
	// //e.printStackTrace();
	// throw new ControladorException("erro.sistema", e);
	// }
	// }

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
	public NegativacaoComando consultarNegativacaoComandadoParaExecutar() throws ControladorException {
		NegativacaoComando retorno = null;

		try {
			retorno = repositorioSpcSerasa.consultarNegativacaoComandadoParaExecutar();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * M�todo consutla um negativacaoComando [UC0671] Gerar Movimento de
	 * Inclusao de Negativacao [Fluixo princiapal] 4.0
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
	public NegativacaoComando consultarNegativacaoComando(Integer idNegativacaoComando) throws ControladorException {
		NegativacaoComando retorno = null;

		try {
			retorno = repositorioSpcSerasa.consultarNegativacaoComando(idNegativacaoComando);
		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

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
	public NegativadorContrato consultarNegativadorContratoVigente(Integer negativador) throws ControladorException {
		NegativadorContrato retorno = null;

		try {
			retorno = repositorioSpcSerasa.consultarNegativadorContratoVigente(negativador);
		} catch (ErroRepositorioException e) {

			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * UC0671 - Gerar Movimento de Inclus�o de Negativa��o] SB0004 - Gerar
	 * Movimento de Inclus�o de Negativa��o para os Im�veis
	 * 
	 * @author Anderson Italo
	 * @date 19/03/2010
	 */
	public List pesquisarParametroNegativacaoCriterio(Integer idNegativacaoCriterio) throws ControladorException {
		try {
			List retorno = repositorioSpcSerasa.pesquisarParametroNegativacaoCriterio(idNegativacaoCriterio);
			return retorno;
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisar as rotas dos Im�veis
	 */
	public Collection pesquisarRotasImoveis() throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarRotasImoveis();
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisar as localidades dos Im�veis que est�o no resultado da simula��o
	 */
	public Collection pesquisarRotasImoveisComandoSimulacao(Integer idNegativacaoComando) throws ControladorException {
		try {

			return repositorioSpcSerasa.pesquisarRotasImoveisComandoSimulacao(idNegativacaoComando);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Object[] pesquisarQuantidadeInclusaoNegativacao(Integer idNegativacaoComando) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarQuantidadeInclusaoNegativacao(idNegativacaoComando);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	public Integer pesquisarQuantidadeInclusaoItemNegativacao(Integer idNegativacaoComando) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarQuantidadeInclusaoItemNegativacao(idNegativacaoComando);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	public Object[] pesquisarQuantidadeInclusaoNegativacaoSimulacao(Integer idNegComando) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarQuantidadeInclusaoNegativacaoSimulacao(idNegComando);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void gerarArquivoNegativacao(Integer idComando, Integer idMovimento, Integer idNegativador,
			NegativadorMovimento negativadorMovimento, boolean trailler) throws ControladorException {
		
		try {
			StringBuffer registroLinha = this.gerarRegistroHeaderArquivoNegativacao(idMovimento);

			Integer numeroRegistro = 1;
			Object[] dados = this.gerarRegistroDetalheArquivoNegativacao(idMovimento, registroLinha, numeroRegistro);
			numeroRegistro = (Integer) dados[0] + 1;
			registroLinha = (StringBuffer) dados[1];

			if (trailler) {
				registroLinha = this.gerarRegistroTraillerArquivoNegativacao(idComando, negativadorMovimento, registroLinha, numeroRegistro);
			} else if (negativadorMovimento.getNegativador().getId().equals(Negativador.NEGATIVADOR_SERASA)) {
				registroLinha = this.atualizarRegistroTraillerArquivoNegativacaoSERASA(idMovimento, registroLinha, numeroRegistro);
			}
			
			enviarEmailArquivoNegativacao(idNegativador, registroLinha);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private StringBuffer atualizarRegistroTraillerArquivoNegativacaoSERASA(Integer idMovimento, StringBuffer registroLinha,
			Integer numeroRegistro) throws ErroRepositorioException {
		Collection colecaoRegistroTrailler = repositorioSpcSerasa.consultarNegativadorMovimentoRegistroParaGerarArquivo(idMovimento, "T");

		if (colecaoRegistroTrailler != null && !colecaoRegistroTrailler.isEmpty()) {

			NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) colecaoRegistroTrailler.iterator().next();

			String conteudoTrailler = negativadorMovimentoReg.getConteudoRegistro();
			conteudoTrailler = conteudoTrailler.substring(0, conteudoTrailler.length() - 6);
			conteudoTrailler = conteudoTrailler + Util.adicionarZerosEsquedaNumero(6, numeroRegistro.toString());
			conteudoTrailler = conteudoTrailler.substring(0, 2) + Util.adicionarZerosEsquedaNumero(6, numeroRegistro.toString())
					+ conteudoTrailler.substring(8, conteudoTrailler.length());

			negativadorMovimentoReg.setConteudoRegistro(conteudoTrailler);
			negativadorMovimentoReg.setNumeroRegistro(numeroRegistro);
			RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);

			registroLinha.append(negativadorMovimentoReg.getConteudoRegistro());
		}
		
		return registroLinha;
	}

	private StringBuffer gerarRegistroTraillerArquivoNegativacao(Integer idComando, NegativadorMovimento negativadorMovimento,
			StringBuffer registroLinha, Integer numeroRegistro) throws ErroRepositorioException, ControladorException {
		
		NegativacaoCriterio criterio = null;

		if (idComando != null) {
			criterio = repositorioSpcSerasa.pesquisarNegativacaoCriterio(idComando);
		} else {
			criterio = (NegativacaoCriterio) Util.retonarObjetoDeColecao(repositorioSpcSerasa.getNegativacaoCriterio(
					negativadorMovimento.getNegativacaoComando().getId()));
		}

		StringBuilder registroTrailler = new StringBuilder();
		if (negativadorMovimento.getNegativador().getId().equals(Negativador.NEGATIVADOR_SPC)) {
			registroTrailler = this.geraRegistroTipoTraillerSPC(numeroRegistro);
		} else {
			registroTrailler = this.geraRegistroTipoTraillerSERASA(numeroRegistro);
		}

		Integer idRegistroTipo = null;
		if (negativadorMovimento.getId().equals(Negativador.NEGATIVADOR_SPC)){
			idRegistroTipo = NegativadorRegistroTipo.ID_SPC_TRAILLER;
		} else {
			idRegistroTipo = NegativadorRegistroTipo.ID_SERASA_TRAILLER;
		}
		
		this.gerarNegativadorMovimentoRegistro(negativadorMovimento.getNegativador().getId(), negativadorMovimento.getId(),
				registroTrailler, numeroRegistro, criterio, idRegistroTipo);

		registroLinha.append(registroTrailler.toString());
		
		return registroLinha;
	}

	private Object[] gerarRegistroDetalheArquivoNegativacao(Integer idMovimento,
			StringBuffer registroLinha, Integer numeroRegistro) throws ErroRepositorioException {
		
		Object[] dados = new Object[2];
		
		Collection colecaoRegistroDetalhe = repositorioSpcSerasa.consultarNegativadorMovimentoRegistroParaGerarArquivoInclusao(
				idMovimento, "D");
		
		if (colecaoRegistroDetalhe != null && !colecaoRegistroDetalhe.isEmpty()) {
			Iterator iteratorColecaoRegistroDetalhe = colecaoRegistroDetalhe.iterator();
			
			while (iteratorColecaoRegistroDetalhe.hasNext()) {
				NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) iteratorColecaoRegistroDetalhe.next();

				String conteudoDetalhe = negativadorMovimentoReg.getConteudoRegistro();
				if (conteudoDetalhe.substring(0, 2).equals("01")) {
					negativadorMovimentoReg.setImovel(null);
				}

				numeroRegistro += 1;
				conteudoDetalhe = conteudoDetalhe.substring(0, conteudoDetalhe.length() - 6);
				conteudoDetalhe = conteudoDetalhe + Util.adicionarZerosEsquedaNumero(6, numeroRegistro.toString());
				negativadorMovimentoReg.setConteudoRegistro(conteudoDetalhe);
				negativadorMovimentoReg.setNumeroRegistro(numeroRegistro);
				RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);

				registroLinha.append(negativadorMovimentoReg.getConteudoRegistro());
				registroLinha.append("\n");
			}
		}
		
		dados[0] = numeroRegistro;
		dados[1] = registroLinha;
		
		return dados;
	}

	private StringBuffer gerarRegistroHeaderArquivoNegativacao(Integer idMovimento) throws ErroRepositorioException {
		Collection colecaoRegistroHeader = repositorioSpcSerasa.consultarNegativadorMovimentoRegistroParaGerarArquivo(
				idMovimento, NegativadorRegistroTipo.TIPO_HEADER);

		StringBuffer registroLinha = new StringBuffer();
		
		if (colecaoRegistroHeader != null && !colecaoRegistroHeader.isEmpty()) {
			NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) colecaoRegistroHeader.iterator().next();

			String conteudoHeader = negativadorMovimentoReg.getConteudoRegistro();
			conteudoHeader = conteudoHeader.substring(0, conteudoHeader.length() - 6);
			conteudoHeader = conteudoHeader + Util.adicionarZerosEsquedaNumero(6, "1");
			negativadorMovimentoReg.setConteudoRegistro(conteudoHeader);
			negativadorMovimentoReg.setNumeroRegistro(1);
			RepositorioUtilHBM.getInstancia().atualizar(negativadorMovimentoReg);

			registroLinha.append(negativadorMovimentoReg.getConteudoRegistro());
			registroLinha.append("\n");
		}
		
		return registroLinha;
	}

	private void enviarEmailArquivoNegativacao(Integer idNegativador, StringBuffer registroLinha) throws ControladorException {
		Date data = new Date();
		String AAAAMMDD = Util.formatarDataAAAAMMDD(data);
		String HHMM = Util.formatarDataHHMM(data);
		String formatodatahora = AAAAMMDD + "_" + HHMM;
		BufferedWriter out = null;
		ZipOutputStream zos = null;
		
		String nomeZip = "";
		File leituraTipo = null;

		if (idNegativador.equals(Negativador.NEGATIVADOR_SPC)) {
			nomeZip = "REG_SPC_" + formatodatahora;
			leituraTipo = new File(nomeZip + ".env");
		} else {
			nomeZip = "REG_SERASA_" + formatodatahora;
			leituraTipo = new File(nomeZip + ".txt");
		}
		
		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.SPC_SERASA);
		String emailRemetente = envioEmail.getEmailRemetente();
		String tituloMensagem = envioEmail.getTituloMensagem();
		String corpoMensagem = envioEmail.getCorpoMensagem();
		String emailReceptor = envioEmail.getEmailReceptor();

		try {
			File compactado = new File(nomeZip + ".zip");
			zos = new ZipOutputStream(new FileOutputStream(compactado));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
			out.write(registroLinha.toString());
			out.flush();
			out.close();

			ZipUtil.adicionarArquivo(zos, leituraTipo);
			zos.close();

			ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor, emailRemetente, tituloMensagem, corpoMensagem, compactado);

			leituraTipo.delete();
		} catch (IOException ex) {
			throw new ControladorException("erro.sistema", ex);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		} finally {
			IoUtil.fecharStream(out);
			IoUtil.fecharStream(zos);
		}
	}

	/**
	 * M�todo que atualiza o n�mero da execu��o do resumo da negativa��o na
	 * tabela SISTEMA_PARAMETROS mais um [UC0688] Gerar Resumo Di�rio da
	 * Negativa��o. *
	 *
	 * @author Yara Taciane
	 * @date 11/11/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroExecucaoResumoNegativacao(Integer idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o in�cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.FUNCIONALIDADE, 0);

		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		Integer numeroExecucao = sistemaParametro.getNumeroExecucaoResumoNegativacao() + 1;

		// [UC0688] Gerar Resumo Di�rio da Negativa��o.
		// -------------------------------------------------------------------------------------------
		// Alterado por : Yara Taciane - data : 08/07/2008
		// Analista : F�tima Sampaio
		// -------------------------------------------------------------------------------------------

		// O sistema atualiza o n�mero da execu��o do resumo da negativa��o na
		// tabela SISTEMA_PARAMETROS mais um).
		sistemaParametro.setNumeroExecucaoResumoNegativacao(numeroExecucao);

		sistemaParametro.setUltimaAlteracao(new Date());

		this.getControladorUtil().atualizarSistemaParametro(sistemaParametro);

		System.out.println(" Fim --- > altualizado Sistema Parametro = "
				+ sistemaParametro.getNumeroExecucaoResumoNegativacao());

		getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

	}

	/**
	 * [UC0014] - ManterImovel
	 * 
	 * Verificar exist�ncia de negativa��o para o cliente-im�vel
	 * 
	 * @author Victor Cisneiros
	 * @date 12/01/2009
	 */
	public boolean verificarNegativacaoDoClienteImovel(Integer idCliente, Integer idImovel) throws ControladorException {
		try {
			return repositorioSpcSerasa.verificarNegativacaoDoClienteImovel(idCliente, idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa a cole��o de clientes do im�vel para negativa��o sem o cliente
	 * empresa do sistema par�metro
	 * 
	 * @author Ana Maria
	 * @date 17/12/2008
	 * @param idImovel
	 * @return Collection
	 * @exception ErroRepositorioException
	 * 
	 */
	public Collection pesquisarClienteImovelParaNegativacao(Integer idImovel) throws ControladorException {
		try {
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			return repositorioSpcSerasa.pesquisarClienteImovelParaNegativacao(idImovel,
					sistemaParametro.getCnpjEmpresa());
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public Object[] pesquisarDadosImovelParaNegativacao(Integer idImovel) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarDadosImovelParaNegativacao(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

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
			throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarRotasPorCobrancaGrupoParaNegativacao(nCriterio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * Informa��es Atualizadas em (maior data e hora da �ltima execu��o
	 * 
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public List pesquisarNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarNegativadorMovimentoRegItens(idNegativadorMovimentoReg);
		} catch (ErroRepositorioException e) {

			// e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

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
			throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarRotasPorGerenciaRegionalParaNegativacao(nCriterio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

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
			throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarRotasPorUnidadeNegocioParaNegativacao(nCriterio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

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
	public List pesquisarRotasPorLocalidadeParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarRotasPorLocalidadeParaNegativacao(nCriterio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

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
	public List pesquisarRotasPorLocalidadesParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarRotasPorLocalidadesParaNegativacao(nCriterio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Gerar Relat�rio de Negativa��es Exclu�das
	 * 
	 * Pesquisar o somat�rio do valor paga ou parcelado pelo registro
	 * negativador
	 * 
	 * @author Yara T. Souza
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarSomatorioNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg,
			Integer idCobrancaDebitoSituacao) throws ControladorException {
		try {
			return repositorioSpcSerasa.pesquisarSomatorioNegativadorMovimentoRegItens(idNegativadorMovimentoReg,
					idCobrancaDebitoSituacao);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o [SB0006] Acompanhar Pagamento
	 * do Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @return Collection de
	 *
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	private Short acompanharPagamentoParcelamento(NegativadorMovimentoReg nmr, Short indicadorExistenciaParcelamento)
			throws ControladorException, ErroRepositorioException {

		// 1
		indicadorExistenciaParcelamento = ConstantesSistema.NAO;

		// 2
		BigDecimal valorEntradaPago = new BigDecimal(0);
		Short numeroPrestacoesCobradasPagas = 0;
		Short numeroPrestacoesCobradasNaoPagas = 0;
		Short numeroPrestacoesNaoCobradas = 0;
		BigDecimal valorParceladoNaoCobrado = new BigDecimal(0);
		BigDecimal valorParceladoCobradoPago = new BigDecimal(0);
		BigDecimal valorParceladoCobradoNaoPago = new BigDecimal(0);

		// 3
		Integer idParcelamento = this.repositorioSpcSerasa
				.pesquisarMenorParcelamentoNegativadorMovimentoRegParcelamento(nmr.getId());

		// [SB0007 - Determinar Situa��o Atual do D�bito A Cobrar]
		Object[] obj = determinarSituacaoAtualDebitoACobrar(nmr, idParcelamento);
		Integer situacaoAtualItem = null;
		Short numeroPrestacoesCobradas = null;
		Short numeroParcelaBonus = null;
		Short indicadorParcelamentoAtivo = null;
		Integer idDebitoACobrarGeral = null;
		Parcelamento parcelamento = null;

		if (obj != null) {

			situacaoAtualItem = (Integer) obj[0];
			numeroPrestacoesCobradas = (Short) obj[1];
			numeroParcelaBonus = (Short) obj[2];
			indicadorParcelamentoAtivo = (Short) obj[3];
			idDebitoACobrarGeral = (Integer) obj[4];
			parcelamento = (Parcelamento) obj[5];

			if (situacaoAtualItem.equals(DebitoCreditoSituacao.PARCELADA)) {

				while (situacaoAtualItem.equals(DebitoCreditoSituacao.PARCELADA)
						&& existeNegativadorMovimentoRegParcelamentoComParcelamentoInativo(nmr.getId(), idParcelamento)) {

					// 5.1.1
					idParcelamento = idParcelamento = this.repositorioSpcSerasa
							.obterIDParcelamentoAtual(idDebitoACobrarGeral);

					// [SB0007 - Determinar Situa��o Atual do D�bito A Cobrar]
					obj = determinarSituacaoAtualDebitoACobrar(nmr, idParcelamento);

					if (obj != null) {
						situacaoAtualItem = (Integer) obj[0];
						numeroPrestacoesCobradas = (Short) obj[1];
						numeroParcelaBonus = (Short) obj[2];
						indicadorParcelamentoAtivo = (Short) obj[3];
						idDebitoACobrarGeral = (Integer) obj[4];
						parcelamento = (Parcelamento) obj[5];
					} else {
						situacaoAtualItem = null;
						numeroPrestacoesCobradas = null;
						numeroParcelaBonus = null;
						indicadorParcelamentoAtivo = null;
						idDebitoACobrarGeral = null;
						parcelamento = null;
					}

				}

				if (situacaoAtualItem.equals(DebitoCreditoSituacao.PARCELADA)) {
					// 5.2.1
					indicadorExistenciaParcelamento = ConstantesSistema.SIM;
					// 5.2.2
					indicadorParcelamentoAtivo = ConstantesSistema.NAO;
				}

			}

			if (situacaoAtualItem != null) {

				// 6
				if (situacaoAtualItem.equals(DebitoCreditoSituacao.CANCELADA)) {

					NegativadorMovimentoRegParcelamento nmrp = this.repositorioSpcSerasa
							.pesquisarNegativadorMovimentoRegParcelamento(nmr.getId(), idParcelamento);
					Integer numeroPrestacoesMenosNumeroPrestacoesCobradas = parcelamento.getNumeroPrestacoes()
							- numeroPrestacoesCobradas.shortValue();
					BigDecimal valorCancelado = parcelamento.getValorPrestacao().multiply(
							new BigDecimal(numeroPrestacoesMenosNumeroPrestacoesCobradas));

					if (nmrp != null) {
						// 6.1
						// Caso existam os dados do parcelamento da negativa��o
						// (existe ocorr�ncia na tabela
						// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO
						// para NMRG_ID=NMRG_ID da tabela
						// NEGATIVADOR_MOVIMENTO_REG e
						// PARC_ID=Identificador do Parcelamento),
						// atualizar os dados do cancelamento do parcelamento da
						// negativa��o

						nmrp.setValorParceladoCancelado(valorCancelado);
						nmrp.setIndicadorParcelamentoAtivo(ConstantesSistema.NAO);
						nmrp.setUltimaAlteracao(new Date());

						getControladorUtil().atualizar(nmrp);

					} else {
						// 6.2
						// caso contrario,
						// inserir os dados do cancelamento do parcelamento da
						// negativa��o
						nmrp = new NegativadorMovimentoRegParcelamento();

						nmrp.setNegativadorMovimentoReg(nmr);
						nmrp.setParcelamento(parcelamento);
						nmrp.setValorParcelado(parcelamento.getValorParcelado());
						nmrp.setValorParceladoEntrada(parcelamento.getValorEntrada());
						nmrp.setNumeroPrestacoes(parcelamento.getNumeroPrestacoes());
						nmrp.setValorParceladoCancelado(valorCancelado);
						nmrp.setIndicadorParcelamentoAtivo(ConstantesSistema.NAO);
						nmrp.setUltimaAlteracao(new Date());

						getControladorUtil().inserir(nmrp);

					}

				} else {

					// 7
					if (parcelamento.getValorEntrada() != null) {

						BigDecimal valorPagamentoContas = BigDecimal.ZERO;
						BigDecimal valorPagamentoContasHistorico = BigDecimal.ZERO;
						BigDecimal valorPagamentoGuias = BigDecimal.ZERO;
						BigDecimal valorPagamentoGuiasHistorico = BigDecimal.ZERO;

						// 7.1
						Collection colecaoIdsContaEntradaParcelamento = this.repositorioSpcSerasa
								.pesquisarIdsContaEntradaParcelamento(idParcelamento);
						Collection colecaoIdsContaHistoricoEntradaParcelamento = this.repositorioSpcSerasa
								.pesquisarIdsContaHistoricoEntradaParcelamento(idParcelamento);

						// 7.2
						if (colecaoIdsContaEntradaParcelamento != null && !colecaoIdsContaEntradaParcelamento.isEmpty()) {
							valorPagamentoContas = this.repositorioSpcSerasa
									.pesquisarValorPagamentoDeContas(colecaoIdsContaEntradaParcelamento);
						}
						if (colecaoIdsContaHistoricoEntradaParcelamento != null
								&& !colecaoIdsContaHistoricoEntradaParcelamento.isEmpty()) {
							valorPagamentoContasHistorico = this.repositorioSpcSerasa
									.pesquisarValorPagamentoDeContasHistorico(colecaoIdsContaHistoricoEntradaParcelamento);
						}

						// se n�o existir conta selecionada como entrada de
						// parcelamento, o sitema gera guia de entrada
						if (valorPagamentoContas.equals(BigDecimal.ZERO)
								&& valorPagamentoContasHistorico.equals(BigDecimal.ZERO)) {
							// 7.3
							Integer idGuiaEntradaParcelamento = this.repositorioSpcSerasa
									.pesquisarIdGuiaEntradaParcelamento(idParcelamento);
							GuiaPagamentoHistorico guiaHistorico = this.repositorioSpcSerasa
									.pesquisarGuiaHistoricoEntradaParcelamento(idParcelamento);
							Integer idGuiaHistoricoEntradaParcelamento = null;
							if (guiaHistorico != null) {
								idGuiaHistoricoEntradaParcelamento = guiaHistorico.getId();
							}

							// 7.4
							if (idGuiaEntradaParcelamento != null) {
								valorPagamentoGuias = this.repositorioSpcSerasa
										.pesquisarValorPagamentoDeGuia(idGuiaEntradaParcelamento);
							} else {
								if (idGuiaHistoricoEntradaParcelamento != null) {
									valorPagamentoGuiasHistorico = this.repositorioSpcSerasa
											.pesquisarValorPagamentoDeGuiaHistorico(idGuiaHistoricoEntradaParcelamento);

									if (valorPagamentoGuiasHistorico.equals(BigDecimal.ZERO)) {
										// [FS0005] - Verificar exist�ncia de
										// pagamento da guia
										// Caso n�o exista ocorr�ncia de
										// pagamento,
										// efetuar nova pesquisa pelo ano e m�s
										// de refer�ncia seguinte

										Integer referenciaContabilGuiaMaisUm = Util
												.somaUmMesAnoMesReferencia(guiaHistorico.getAnoMesReferenciaContabil());
										valorPagamentoGuiasHistorico = this.repositorioSpcSerasa
												.pesquisarValorPagamentoDeGuiaHistorico(
														idGuiaHistoricoEntradaParcelamento,
														referenciaContabilGuiaMaisUm);
									}

								}

							}

						}

						// 7.5
						valorEntradaPago = valorPagamentoContas.add(valorPagamentoContasHistorico)
								.add(valorPagamentoGuias).add(valorPagamentoGuiasHistorico);
					}

					// 8

					// 8.1
					Collection colecaoIdsConta = this.repositorioSpcSerasa.pesquisarIdsContasCobrancaParcelamento(
							parcelamento.getImovel().getId(), parcelamento.getAnoMesReferenciaFaturamento());
					Collection colecaoIdsContaHistorico = this.repositorioSpcSerasa
							.pesquisarIdsContasHistoricoCobrancaParcelamento(parcelamento.getImovel().getId(),
									parcelamento.getAnoMesReferenciaFaturamento());

					int numeroContas = colecaoIdsConta.size() + colecaoIdsContaHistorico.size();

					// 8.2
					Integer quantidadePagamentos = 0;
					if (colecaoIdsConta != null && !colecaoIdsConta.isEmpty()) {
						quantidadePagamentos = this.repositorioSpcSerasa.pesquisarQtdeContasPagas(colecaoIdsConta);
					}

					Integer quantidadePagamentosHistorico = 0;
					if (colecaoIdsContaHistorico != null && !colecaoIdsContaHistorico.isEmpty()) {
						quantidadePagamentosHistorico = this.repositorioSpcSerasa
								.pesquisarQtdeContasHistoricoPagas(colecaoIdsContaHistorico);
					}
					Integer numeroPagamentos = Util.somaInteiros(quantidadePagamentos, quantidadePagamentosHistorico);

					// 8.3
					numeroPrestacoesCobradasPagas = numeroPagamentos.shortValue();

					// 8.4
					Integer numeroContasMenosNumeroPagamento = (numeroContas - numeroPagamentos.intValue());
					numeroPrestacoesCobradasNaoPagas = numeroContasMenosNumeroPagamento.shortValue();

					// 8.5
					Integer numeroPrestacoesMenosNumeroPrestacoesCobradas = parcelamento.getNumeroPrestacoes()
							.shortValue() - numeroPrestacoesCobradas.shortValue() - numeroParcelaBonus.shortValue();
					numeroPrestacoesNaoCobradas = numeroPrestacoesMenosNumeroPrestacoesCobradas.shortValue();

					// 8.6
					valorParceladoCobradoPago = parcelamento.getValorPrestacao().multiply(
							new BigDecimal(numeroPrestacoesCobradasPagas));

					// 8.7
					valorParceladoCobradoNaoPago = parcelamento.getValorPrestacao().multiply(
							new BigDecimal(numeroPrestacoesCobradasNaoPagas));

					if (numeroPrestacoesNaoCobradas.equals(new Short("0"))) {
						// [FS0006] - Verificar ocorr�ncia da �ltima presta��o
						// cobrada
						BigDecimal valorParcelado = parcelamento.getValorParcelado();
						BigDecimal diferencaValorParcelado = valorParcelado.subtract(valorParceladoCobradoPago
								.add(valorParceladoCobradoNaoPago));

						if (!diferencaValorParcelado.equals(BigDecimal.ZERO)) {
							if (!valorParceladoCobradoPago.equals(BigDecimal.ZERO)) {
								valorParceladoCobradoPago = valorParceladoCobradoPago.add(diferencaValorParcelado);
							} else {
								valorParceladoCobradoNaoPago = valorParceladoCobradoNaoPago
										.add(diferencaValorParcelado);
							}

						}

					}

					// 8.8
					valorParceladoNaoCobrado = parcelamento.getValorPrestacao().multiply(
							new BigDecimal(numeroPrestacoesNaoCobradas));

					NegativadorMovimentoRegParcelamento nmrp = this.repositorioSpcSerasa
							.pesquisarNegativadorMovimentoRegParcelamento(nmr.getId(), idParcelamento);

					if (nmrp != null) {
						// 9
						// Caso existam os dados do parcelamento da negativa��o
						// (existe ocorr�ncia na tabela
						// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO
						// para NMRG_ID=NMRG_ID da tabela
						// NEGATIVADOR_MOVIMENTO_REG e PARC_ID=Identificador do
						// Parcelamento),
						// atualizar os dados do parcelamento da negativa��o

						nmrp.setValorParceladoEntradaPago(valorEntradaPago);
						nmrp.setValorParceladoNaoCobrado(valorParceladoNaoCobrado);
						nmrp.setValorParceladoCobradoPago(valorParceladoCobradoPago);
						nmrp.setValorParceladoCobradoNaoPago(valorParceladoCobradoNaoPago);
						nmrp.setNumeroPrestacoesNaoCobradas(numeroPrestacoesNaoCobradas);
						nmrp.setNumeroPrestacoesCobradasPagas(numeroPrestacoesCobradasPagas);
						nmrp.setNumeroPrestacoesCobradasNaoPagas(numeroPrestacoesCobradasNaoPagas);
						nmrp.setIndicadorParcelamentoAtivo(indicadorParcelamentoAtivo);
						nmrp.setUltimaAlteracao(new Date());

						getControladorUtil().atualizar(nmrp);

					} else {
						// 10
						// caso contrario,
						// inserir os dados do parcelamento da negativa��o
						nmrp = new NegativadorMovimentoRegParcelamento();

						nmrp.setNegativadorMovimentoReg(nmr);
						nmrp.setParcelamento(parcelamento);
						nmrp.setValorParcelado(parcelamento.getValorParcelado());
						nmrp.setValorParceladoEntrada(parcelamento.getValorEntrada());
						nmrp.setValorParceladoEntradaPago(valorEntradaPago);
						nmrp.setValorParceladoNaoCobrado(valorParceladoNaoCobrado);
						nmrp.setValorParceladoCobradoPago(valorParceladoCobradoPago);
						nmrp.setValorParceladoCobradoNaoPago(valorParceladoCobradoNaoPago);
						nmrp.setNumeroPrestacoes(parcelamento.getNumeroPrestacoes());
						nmrp.setNumeroPrestacoesNaoCobradas(numeroPrestacoesNaoCobradas);
						nmrp.setNumeroPrestacoesCobradasPagas(numeroPrestacoesCobradasPagas);
						nmrp.setNumeroPrestacoesCobradasNaoPagas(numeroPrestacoesCobradasNaoPagas);
						nmrp.setIndicadorParcelamentoAtivo(indicadorParcelamentoAtivo);
						nmrp.setUltimaAlteracao(new Date());

						getControladorUtil().inserir(nmrp);

					}

				}

			}
		}

		return indicadorExistenciaParcelamento;
	}

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 22/04/2009
	 */
	public Boolean existeNegativadorMovimentoRegParcelamento(Integer idNegativadorMovimentoReg)
			throws ControladorException {
		try {
			Boolean existe = false;
			Collection colNMRP = repositorioSpcSerasa
					.pesquisarNegativadorMovimentoRegParcelamento(idNegativadorMovimentoReg);

			if (colNMRP != null && !colNMRP.isEmpty()) {
				existe = true;
			}

			return existe;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o [SB0007] - Determinar
	 * Situa��o Atual do D�bito A Cobrar
	 *
	 * @author Vivianne Sousa
	 * @date 23/04/2009
	 *
	 * @param nmr
	 * @param idParcelamento
	 * 
	 * @return obj[0] = situacaoAtualItem obj[1] = numeroPrestacoesCobradas
	 *         obj[2] = numeroParcelaBonus obj[3] = indicadorParcelamentoAtivo
	 *         obj[4] = idDebitoACobrarGeral
	 * @throws ErroRepositorioException
	 */
	private Object[] determinarSituacaoAtualDebitoACobrar(NegativadorMovimentoReg nmr, Integer idParcelamento)
			throws ControladorException, ErroRepositorioException {

		Object[] obj = null;
		Integer situacaoAtualItem = null;
		Short numeroPrestacoesCobradas = null;
		Short numeroParcelaBonus = 0;

		// 1
		Parcelamento parcelamento = null;
		parcelamento = this.repositorioSpcSerasa.pesquisarDadosParcelamento(idParcelamento);
		ParcelamentoSituacao parcelamentoSituacao = parcelamento.getParcelamentoSituacao();

		// 2
		Short indicadorParcelamentoAtivo = ConstantesSistema.SIM;

		// 3
		if (parcelamentoSituacao.getId().equals(ParcelamentoSituacao.DESFEITO)) {

			NegativadorMovimentoRegParcelamento nmrp = this.repositorioSpcSerasa
					.pesquisarNegativadorMovimentoRegParcelamento(nmr.getId(), idParcelamento);

			if (nmrp != null) {
				// 3.1
				// Caso existam os dados do parcelamento da negativa��o
				// (existe ocorr�ncia na tabela
				// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO
				// para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG e
				// PARC_ID=Identificador do Parcelamento),
				// atualizar os dados do cancelamento do parcelamento da
				// negativa��o

				nmrp.setValorParceladoCancelado(nmrp.getValorParcelado());
				nmrp.setIndicadorParcelamentoAtivo(ConstantesSistema.NAO);
				nmrp.setUltimaAlteracao(new Date());

				getControladorUtil().atualizar(nmrp);

			} else {
				// 3.2
				// caso contrario,
				// inserir os dados do cancelamento do parcelamento da
				// negativa��o
				nmrp = new NegativadorMovimentoRegParcelamento();

				nmrp.setNegativadorMovimentoReg(nmr);
				nmrp.setParcelamento(parcelamento);
				nmrp.setValorParcelado(parcelamento.getValorParcelado());
				nmrp.setValorParceladoEntrada(parcelamento.getValorEntrada());
				nmrp.setNumeroPrestacoes(parcelamento.getNumeroPrestacoes());
				nmrp.setValorParceladoCancelado(nmrp.getValorParcelado());
				nmrp.setIndicadorParcelamentoAtivo(ConstantesSistema.NAO);
				nmrp.setUltimaAlteracao(new Date());

				getControladorUtil().inserir(nmrp);

			}

		} else {

			// 4
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			Integer idDebitoACobrarGeral = null;
			Integer idDebitoACobrar = this.repositorioSpcSerasa.consultaDebitoACobrarParcelamento(idParcelamento);
			if (idDebitoACobrar != null) {
				idDebitoACobrarGeral = idDebitoACobrar;
				debitoACobrarGeral.setIndicadorHistorico(ConstantesSistema.NAO);

			} else {
				Integer idDebitoACobrarHistorico = this.repositorioSpcSerasa
						.consultaDebitoACobrarHistoricoParcelamento(idParcelamento);
				if (idDebitoACobrarHistorico != null) {
					idDebitoACobrarGeral = idDebitoACobrarHistorico;
					debitoACobrarGeral.setIndicadorHistorico(ConstantesSistema.SIM);
				}
			}

			// 5
			if (debitoACobrarGeral.getIndicadorHistorico() == 1) {

				// 5.1
				DebitoACobrarHistorico debitoACobrarHistorico = this.repositorioSpcSerasa
						.obterDebitoACobrarHistorico(idDebitoACobrarGeral);

				// 5.2
				situacaoAtualItem = debitoACobrarHistorico.getDebitoCreditoSituacaoAtual().getId();

				// 5.3
				numeroPrestacoesCobradas = debitoACobrarHistorico.getPrestacaoCobradas();

				// 5.4
				if (debitoACobrarHistorico.getNumeroParcelaBonus() != null) {
					numeroParcelaBonus = debitoACobrarHistorico.getNumeroParcelaBonus();
				}

				// 5.5
				indicadorParcelamentoAtivo = ConstantesSistema.NAO;

				// 6
			} else if (debitoACobrarGeral.getIndicadorHistorico() == 2) {

				// 6.1
				DebitoACobrar debitoACobrar = this.repositorioSpcSerasa.obterDebitoACobrar(idDebitoACobrarGeral);

				// 6.2
				situacaoAtualItem = debitoACobrar.getDebitoCreditoSituacaoAtual().getId();

				// 6.3
				numeroPrestacoesCobradas = debitoACobrar.getNumeroPrestacaoCobradas();

				// 6.4
				if (debitoACobrar.getNumeroParcelaBonus() != null) {
					numeroParcelaBonus = debitoACobrar.getNumeroParcelaBonus();
				}
			}

			obj = new Object[7];
			obj[0] = situacaoAtualItem;
			obj[1] = numeroPrestacoesCobradas;
			obj[2] = numeroParcelaBonus;
			obj[3] = indicadorParcelamentoAtivo;
			obj[4] = idDebitoACobrarGeral;
			obj[5] = parcelamento;
			obj[6] = numeroParcelaBonus;

		}

		return obj;
	}

	/**
	 * [UC0688] - Gerar Resumo Diario da Negativa��o retorna o id da tabela
	 * NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com NMRP_ICPARCELAMENTOATIVO=1 e
	 * NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG
	 * 
	 * @author Vivianne Sousa
	 * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return nmrp_id
	 */
	public Boolean existeNegativadorMovimentoRegParcelamentoComParcelamentoAtivo(Integer idNegativadorMovimentoReg)
			throws ControladorException {
		try {

			Boolean retorno = false;
			Collection idNegativadorMovimentoRegParcelamento = repositorioSpcSerasa
					.pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoAtivo(idNegativadorMovimentoReg);

			if (idNegativadorMovimentoRegParcelamento != null && !idNegativadorMovimentoRegParcelamento.isEmpty()) {
				retorno = true;
			}

			return retorno;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0688] - Gerar Resumo Diario da Negativa��o retorna o id da tabela
	 * NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para NMRG_ID=NMRG_ID da tabela
	 * NEGATIVADOR_MOVIMENTO_REG e PARC_ID=Identificador do Parcelamento e
	 * NMRP_ICPARCELAMENTOATIVO=2
	 * 
	 * @author Vivianne Sousa
	 * @data 23/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return nmrp_id
	 */
	public Boolean existeNegativadorMovimentoRegParcelamentoComParcelamentoInativo(Integer idNegativadorMovimentoReg,
			Integer idParcelamento) throws ControladorException {
		try {

			Boolean retorno = false;
			Integer idNegativadorMovimentoRegParcelamento = repositorioSpcSerasa
					.pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoInativo(idNegativadorMovimentoReg,
							idParcelamento);

			if (idNegativadorMovimentoRegParcelamento != null) {
				retorno = true;
			}

			return retorno;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0688] - Gerar Resumo Diario da Negativa��o [UC0694] - Gerar Relat�rio
	 * Negativa��es Exclu�das
	 * 
	 * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para
	 * NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG)
	 * 
	 * @author Vivianne Sousa
	 * @data 28/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper
	 */
	public RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper pesquisarNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg) throws ControladorException {

		try {

			RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper retorno = new RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper();

			Collection colecaoNegativadorMovimentoRegParcelamento = repositorioSpcSerasa
					.pesquisarNegativadorMovimentoRegParcelamento(idNegativadorMovimentoReg);
			Iterator iterator = colecaoNegativadorMovimentoRegParcelamento.iterator();

			BigDecimal valorParceladoEntrada = BigDecimal.ZERO;
			BigDecimal valorEntradaPago = BigDecimal.ZERO;
			BigDecimal valorParcelado = BigDecimal.ZERO;
			BigDecimal valorParceladoPago = BigDecimal.ZERO;

			if (colecaoNegativadorMovimentoRegParcelamento != null
					&& !colecaoNegativadorMovimentoRegParcelamento.isEmpty()) {

				while (iterator.hasNext()) {
					NegativadorMovimentoRegParcelamento nmrp = (NegativadorMovimentoRegParcelamento) iterator.next();

					// somat�rio de NMRP_VLPARCELADOENTRADA
					valorParceladoEntrada = valorParceladoEntrada.add(nmrp.getValorParceladoEntrada());

					// somat�rio de NMRP_VLPARCELADOENTRADAPAGO
					if (nmrp.getValorParceladoEntradaPago() != null) {
						valorEntradaPago = valorEntradaPago.add(nmrp.getValorParceladoEntradaPago());
					}

					// NMRP_VLPARCELADO da tabela
					// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com
					// NMRP_ICPARCELAMENTOATIVO=1
					// mais (somat�rio(NMRP_VLPARCELADOCOBRADOPAGO e
					// NMRP_VLPARCELADOCOBRADONAOPAGO)) com
					// NMRP_ICPARCELAMENTOATIVO=2
					if (nmrp.getIndicadorParcelamentoAtivo().equals(ConstantesSistema.SIM)) {
						valorParcelado = valorParcelado.add(nmrp.getValorParcelado());
					} else {
						BigDecimal valorCobradoPagoMaisValorCobradoNaoPago = BigDecimal.ZERO;

						if (nmrp.getValorParceladoCobradoPago() != null) {
							valorCobradoPagoMaisValorCobradoNaoPago = nmrp.getValorParceladoCobradoPago().add(
									nmrp.getValorParceladoCobradoNaoPago());
						}

						valorParcelado = valorParcelado.add(valorCobradoPagoMaisValorCobradoNaoPago);
					}

					// somat�rio de NMRP_VLPARCELADOCOBRADOPAGO
					if (nmrp.getValorParceladoCobradoPago() != null) {
						valorParceladoPago = valorParceladoPago.add(nmrp.getValorParceladoCobradoPago());
					}

				}

			}

			retorno.setValorParceladoEntrada(valorParceladoEntrada);
			retorno.setValorParceladoEntradaPago(valorEntradaPago);
			retorno.setValorParcelado(valorParcelado);
			retorno.setValorParceladoPago(valorParceladoPago);

			return retorno;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0693] - Gerar Relat�rio Acompanhamento de Clientes Negativados
	 * 
	 * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para
	 * NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG)
	 * 
	 * @author Vivianne Sousa
	 * @data 30/04/2009
	 */
	public Object[] pesquisarDadosParcelamentoRelatorioAcompanhamentoClientesNegativados(
			Integer idNegativadorMovimentoReg) throws ControladorException {

		try {

			Object[] retorno = null;
			RelatorioAcompanhamentoClientesNegativadosSomatorioDadosParcelamentoHelper helperSomatorio = null;
			Collection colecaoNegativadorMovimentoRegParcelamento = repositorioSpcSerasa
					.pesquisarNegativadorMovimentoRegParcelamento(idNegativadorMovimentoReg);

			if (colecaoNegativadorMovimentoRegParcelamento != null
					&& !colecaoNegativadorMovimentoRegParcelamento.isEmpty()) {

				BigDecimal valorParceladoTotalParc = BigDecimal.ZERO;
				BigDecimal valorParceladoEntradaTotalParc = BigDecimal.ZERO;
				BigDecimal valorParceladoEntradaPagoTotalParc = BigDecimal.ZERO;
				Integer numeroPrestacoesTotalParc = 0;
				Integer numeroPrestacoesCobradasPagasTotalParc = 0;
				BigDecimal valorParceladoCobradoPagoTotalParc = BigDecimal.ZERO;
				Integer numeroPrestacoesCobradasNaoPagasTotalParc = 0;
				BigDecimal valorParceladoCobradoNaoPagoTotalParc = BigDecimal.ZERO;
				Integer numeroPrestacoesNaoCobradasTotalParc = 0;
				BigDecimal valorParceladoNaoCobradoTotalParc = BigDecimal.ZERO;
				BigDecimal valorParceladoCanceladoTotalParc = BigDecimal.ZERO;
				Iterator iterator = colecaoNegativadorMovimentoRegParcelamento.iterator();
				retorno = new Object[2];

				if (colecaoNegativadorMovimentoRegParcelamento.size() > 1) {
					while (iterator.hasNext()) {
						NegativadorMovimentoRegParcelamento nmrp = (NegativadorMovimentoRegParcelamento) iterator
								.next();

						// NMRP_VLPARCELADO da tabela
						// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com
						// NMRP_ICPARCELAMENTOATIVO=1
						// mais (somat�rio(NMRP_VLPARCELADOCOBRADOPAGO e
						// NMRP_VLPARCELADOCOBRADONAOPAGO)) com
						// NMRP_ICPARCELAMENTOATIVO=2
						if (nmrp.getIndicadorParcelamentoAtivo().equals(ConstantesSistema.SIM)) {
							valorParceladoTotalParc = valorParceladoTotalParc.add(nmrp.getValorParcelado());
						} else {
							BigDecimal valorCobradoPagoMaisValorCobradoNaoPago = BigDecimal.ZERO;

							if (nmrp.getValorParceladoCobradoPago() != null) {
								valorCobradoPagoMaisValorCobradoNaoPago = nmrp.getValorParceladoCobradoPago().add(
										nmrp.getValorParceladoCobradoNaoPago());
							}

							valorParceladoTotalParc = valorParceladoTotalParc
									.add(valorCobradoPagoMaisValorCobradoNaoPago);
						}

						// somat�rio de NMRP_VLPARCELADOENTRADA
						valorParceladoEntradaTotalParc = valorParceladoEntradaTotalParc.add(nmrp
								.getValorParceladoEntrada());

						// somat�rio de NMRP_VLPARCELADOENTRADAPAGO
						if (nmrp.getValorParceladoEntradaPago() != null) {
							valorParceladoEntradaPagoTotalParc = valorParceladoEntradaPagoTotalParc.add(nmrp
									.getValorParceladoEntradaPago());
						}

						// NMRP_NNPRESTACOES da tabela
						// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com
						// NMRP_ICPARCELAMENTOATIVO=1
						// mais (somat�rio(NMRP_NNPRESTACOESCOBRADASPAGAS e
						// NMRP_NNPRESTACOESCOBRADASNAOPAGAS)) com
						// NMRP_ICPARCELAMENTOATIVO=2
						if (nmrp.getIndicadorParcelamentoAtivo().equals(ConstantesSistema.SIM)) {
							numeroPrestacoesTotalParc = numeroPrestacoesTotalParc.intValue()
									+ nmrp.getNumeroPrestacoes().intValue();
						} else {

							if (nmrp.getNumeroPrestacoesCobradasPagas() != null) {
								numeroPrestacoesTotalParc = numeroPrestacoesTotalParc.intValue()
										+ nmrp.getNumeroPrestacoesCobradasPagas().intValue()
										+ nmrp.getNumeroPrestacoesCobradasNaoPagas().intValue();
							}
						}

						// somat�rio de NMRP_NNPRESTACOESCOBRADASPAGAS
						if (nmrp.getNumeroPrestacoesCobradasPagas() != null) {
							numeroPrestacoesCobradasPagasTotalParc = numeroPrestacoesCobradasPagasTotalParc.intValue()
									+ nmrp.getNumeroPrestacoesCobradasPagas().intValue();
						}

						// somat�rio de NMRP_VLPARCELADOCOBRADOPAGO
						if (nmrp.getValorParceladoCobradoPago() != null) {
							valorParceladoCobradoPagoTotalParc = valorParceladoCobradoPagoTotalParc.add(nmrp
									.getValorParceladoCobradoPago());
						}

						// somat�rio de NMRP_NNPRESTACOESCOBRADASNAOPAGAS
						if (nmrp.getNumeroPrestacoesCobradasNaoPagas() != null) {
							numeroPrestacoesCobradasNaoPagasTotalParc = numeroPrestacoesCobradasNaoPagasTotalParc
									.intValue() + nmrp.getNumeroPrestacoesCobradasNaoPagas().intValue();
						}

						// somat�rio de NMRP_VLPARCELADOCOBRADONAOPAGO
						if (nmrp.getValorParceladoCobradoNaoPago() != null) {
							valorParceladoCobradoNaoPagoTotalParc = valorParceladoCobradoNaoPagoTotalParc.add(nmrp
									.getValorParceladoCobradoNaoPago());
						}

						// NMRP_NNPRESTACOESNAOCOBRADAS da tabela
						// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com
						// NMRP_ICPARCELAMENTOATIVO=1
						if (nmrp.getIndicadorParcelamentoAtivo().equals(ConstantesSistema.SIM)
								&& nmrp.getNumeroPrestacoesNaoCobradas() != null) {
							numeroPrestacoesNaoCobradasTotalParc = numeroPrestacoesNaoCobradasTotalParc.intValue()
									+ nmrp.getNumeroPrestacoesNaoCobradas();
						}

						// NMRP_VLPARCELADONAOCOBRADO da tabela
						// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com
						// NMRP_ICPARCELAMENTOATIVO=1
						if (nmrp.getIndicadorParcelamentoAtivo().equals(ConstantesSistema.SIM)
								&& nmrp.getValorParceladoNaoCobrado() != null) {
							valorParceladoNaoCobradoTotalParc = valorParceladoNaoCobradoTotalParc.add(nmrp
									.getValorParceladoNaoCobrado());
						}

						// somat�rio de NMRP_VLPARCELADOCANCELADO
						if (nmrp.getValorParceladoCancelado() != null) {
							valorParceladoCanceladoTotalParc = valorParceladoCanceladoTotalParc.add(nmrp
									.getValorParceladoCancelado());
						}
					}
					helperSomatorio = new RelatorioAcompanhamentoClientesNegativadosSomatorioDadosParcelamentoHelper();
					helperSomatorio.setValorParceladoTotal(valorParceladoTotalParc);
					helperSomatorio.setValorParceladoEntradaTotal(valorParceladoEntradaTotalParc);
					helperSomatorio.setValorParceladoEntradaPagoTotal(valorParceladoEntradaPagoTotalParc);
					helperSomatorio.setNumeroPrestacoesTotal(numeroPrestacoesTotalParc.shortValue());
					helperSomatorio.setNumeroPrestacoesCobradasPagasTotal(numeroPrestacoesCobradasPagasTotalParc
							.shortValue());
					helperSomatorio.setValorParceladoCobradoPagoTotal(valorParceladoCobradoPagoTotalParc);
					helperSomatorio.setNumeroPrestacoesCobradasNaoPagasTotal(numeroPrestacoesCobradasNaoPagasTotalParc
							.shortValue());
					helperSomatorio.setValorParceladoCobradoNaoPagoTotal(valorParceladoCobradoNaoPagoTotalParc);
					helperSomatorio.setNumeroPrestacoesNaoCobradasTotal(numeroPrestacoesNaoCobradasTotalParc
							.shortValue());
					helperSomatorio.setValorParceladoNaoCobradoTotal(valorParceladoNaoCobradoTotalParc);
					helperSomatorio.setValorParceladoCanceladoTotal(valorParceladoCanceladoTotalParc);
				}

				retorno[0] = colecaoNegativadorMovimentoRegParcelamento;
				retorno[1] = helperSomatorio;
			}

			return retorno;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 * Retorna o somat�rio do VALOR PAGO e do VALOR CANCELADO [UC0693] Gerar
	 * Relat�rio Acompanhamaneto de Clientes Negativados
	 * 
	 * @author Vivianne Sousa
	 * @date 29/04/2009
	 */
	public Object[] pesquisarSomatorioValorPagoEValorCancelado(Integer idNegativadorMovimentoReg)
			throws ControladorException {
		try {

			return repositorioSpcSerasa.pesquisarSomatorioValorPagoEValorCancelado(idNegativadorMovimentoReg);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0937] Obter Itens de Negativa��o Associados � Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/09/2009
	 */
	public Collection obterItensNegativacaoAssociadosAConta(Integer idImovel, Integer referencia)
			throws ControladorException {
		try {

			Collection colecaoRetorno = repositorioSpcSerasa.obterNegativadorMovimentoRegItemAssociadosAConta(idImovel,
					referencia);
			Collection colecaoNmriAssociadosAContaHistorico = repositorioSpcSerasa
					.obterNegativadorMovimentoRegItemAssociadosAContaHistorico(idImovel, referencia);

			if (colecaoNmriAssociadosAContaHistorico != null && !colecaoNmriAssociadosAContaHistorico.isEmpty()) {
				if (colecaoRetorno != null && !colecaoRetorno.isEmpty()) {
					colecaoRetorno.addAll(colecaoNmriAssociadosAContaHistorico);
				} else {
					colecaoRetorno = colecaoNmriAssociadosAContaHistorico;
				}
			}

			if (colecaoRetorno != null && !colecaoRetorno.isEmpty()) {
				// atualiza NMRG_ICITEMATUALIZADO para 1
				repositorioSpcSerasa.atualizarIndicadorItemAtualizadoNegativadorMovimentoReg(ConstantesSistema.SIM,
						colecaoRetorno);
			}

			return colecaoRetorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores [SB0017] - Verificar
	 * correspond�ncia do pagamento com item de cobran�a ou de negativa��o
	 * 
	 * [UC0265] Inserir Pagamentos [SB0005] - Inclui Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItemAPartirPagamento(Pagamento pagamento) throws ControladorException {

		Integer idConta = pagamento.getContaGeral() != null ? pagamento.getContaGeral().getId() : null;
		Integer idGuiaPagamento = pagamento.getGuiaPagamento() != null ? pagamento.getGuiaPagamento().getId() : null;
		Collection colecaoNegativadorMovimentoRegItem = null;

		if (idConta != null) {
			// O sistema verifica se existem itens de negativa��o associados a
			// esta conta
			// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
			colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(pagamento.getImovel().getId(),
					pagamento.getAnoMesReferenciaPagamento());

		} else if (idGuiaPagamento != null) {
			// O sistema verifica se existem itens de negativa��o associados a
			// esta guia de pagamento
			colecaoNegativadorMovimentoRegItem = this
					.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuiaPagamento);
		}

		if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
			// Caso existam itens de negativa��o associados a esta conta:
			// Caso existam itens de negativa��o associados a esta guia de
			// pagamento:
			Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
			while (iterNmri.hasNext()) {
				Integer idNmri = (Integer) iterNmri.next();
				// [SB0018 - Atualizar Item da Negativa��o].
				atualizarNegativadorMovimentoRegItemPorPagamento(idNmri, pagamento);
			}
		}

	}

	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores [SB0017] - Atualizar
	 * Item da Negativa��o
	 * 
	 * [UC0265] - Inserir Pagamentos [SB0006] - Atualizar Item da Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItemPorPagamento(Integer idNegativadorMovimentoRegItem,
			Pagamento pagamento) throws ControladorException {
		try {

			repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNegativadorMovimentoRegItem,
					pagamento.getValorPagamento(), pagamento.getDataPagamento(), CobrancaDebitoSituacao.PAGO);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0266] Manter Pagamentos [SB0007] - Verifica Associa��o do Pagamento
	 * com Itens de Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2009
	 */
	public void verificaAssociacaoPagamentoComItensNegativacao(Pagamento pagamento) throws ControladorException {
		try {

			Integer idConta = pagamento.getContaGeral() != null ? pagamento.getContaGeral().getId() : null;
			Integer idGuiaPagamento = pagamento.getGuiaPagamento() != null ? pagamento.getGuiaPagamento().getId()
					: null;
			Collection colecaoNegativadorMovimentoRegItem = null;

			if (idConta != null) {
				// Situa��o 1: Pagamento referente a conta
				// 1.Caso o pagamento exclu�do seja referente a conta
				// 1.1.O sistema obt�m dados da conta do pagamento
				Object[] dadosConta = repositorioSpcSerasa.pesquisarImovelEReferenciaDaConta(idConta);
				Integer idImovel = (Integer) dadosConta[0];
				Integer amReferenciaConta = (Integer) dadosConta[1];

				// 1.2. O sistema verifica se existem itens de negativa��o
				// associados � conta do pagamento
				// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
				// passando o identificador do im�vel (IMOV_ID da tabela CONTA
				// ou da tabela CONTA_HISTORICO)
				// e a refer�ncia (CNTA_AMREFERENCIACONTA da tabela CONTA
				// ou CNHI_AMREFERENCIACONTA da tabela CONTA_HISTORICO).
				colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(idImovel, amReferenciaConta);

			} else if (idGuiaPagamento != null) {
				// Situa��o 2: Pagamento referente a guia
				// 1.Caso o pagamento exclu�do seja referente a guia

				// 1.1.O sistema verifica se existem itens de negativa��o
				// associados � guia do pagamento (existe ocorr�ncia na tabela
				// NEGATIVADOR_MOVIMENTO_REG_ITEM com NMRI_ICSITDEFINITIVA=2 e
				// GPAG_ID=GPAG_ID da tabela PAGAMENTO).
				colecaoNegativadorMovimentoRegItem = this
						.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuiaPagamento);

			}

			if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
				// Caso existam itens de negativa��o associados � conta do
				// pagamento
				// Caso existam itens de negativa��o associados � guia anterior
				Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();
					// [SB0008 - Atualizar Item da Negativa��o - Desfazer
					// Pagamento].
					atualizarNegativadorMovimentoRegItemDesfazerPagamento(idNmri);
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0266] Manter Pagamentos [SB0008] - Atualizar Item da Negativa��o -
	 * Desfazer Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItemDesfazerPagamento(Integer idNegativadorMovimentoRegItem)
			throws ControladorException {
		try {

			repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNegativadorMovimentoRegItem);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0266] Manter Pagamentos [SB0004] - Atualiza Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2009
	 */
	public void verificaAssociacaoPagamentoComItensNegativacao(Pagamento pagamento, Pagamento pagamentoAnterior)
			throws ControladorException {

		Integer idTipoDocumento = pagamento.getDocumentoTipo().getId();

		if (idTipoDocumento.equals(DocumentoTipo.CONTA)) {
			// 1.1.3.O sistema verifica se h� rela��o do pagamento da Conta com
			// itens de negativa��o
			// [SB0005 - Verifica Associa��o Pagamento da Conta com Itens de
			// Negativa��o].
			verificaAssociacaoPagamentoContaComItensNegativacao(pagamento, pagamentoAnterior);

		} else if (idTipoDocumento.equals(DocumentoTipo.GUIA_PAGAMENTO)) {
			// 1.2.3.O sistema verifica se h� rela��o do pagamento da Guia com
			// itens de negativa��o
			// [SB0006 - Verifica Associa��o Pagamento da Guia com Itens de
			// Negativa��o].
			verificaAssociacaoPagamentoGuiaComItensNegativacao(pagamento, pagamentoAnterior);

		}

		// 1.4.Pagamento associado a novo tipo de documento:
		// 1.4.1.O sistema verifica se h� rela��o da nova associa��o do
		// pagamento com itens de negativa��o
		// [SB0010 - Verifica Associa��o Novo Tipo Documento do Pagamento com
		// Itens de Negativa��o].
		verificaAssociacaoNovoTipoDocumentoPagamentoComItensNegativacao(pagamento, pagamentoAnterior);

	}

	/**
	 * [UC0266] Manter Pagamentos [SB0005] - Verifica Associa��o do Pagamento da
	 * Conta com Itens de Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2009
	 */
	public void verificaAssociacaoPagamentoContaComItensNegativacao(Pagamento pagamentoAtualizacao,
			Pagamento pagamentoAnterior) throws ControladorException {
		try {

			Integer idContaPagamentoAtualizacao = pagamentoAtualizacao.getContaGeral() != null ? pagamentoAtualizacao
					.getContaGeral().getId() : null;
			Integer idContaPagamentoAnterior = pagamentoAnterior.getContaGeral() != null ? pagamentoAnterior
					.getContaGeral().getId() : null;

			// Situa��o 1: Pagamento alterado para determinada conta
			if (idContaPagamentoAnterior == null && idContaPagamentoAtualizacao != null) {
				// 1.Caso o pagamento esteja sendo alterado para determinada
				// conta

				// 1.1.O sistema verifica se existem itens de negativa��o
				// associados � conta do pagamento (Id da Conta)
				// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
				// passando o identificador do im�vel (IMOV_ID da tabela IMOVEL)
				// e a refer�ncia (Refer�ncia da Conta).

				Collection colecaoNegativadorMovimentoRegItem = null;

				if (pagamentoAtualizacao.getContaGeral() != null
						&& pagamentoAtualizacao.getContaGeral().getConta() != null) {

					colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(pagamentoAtualizacao
							.getImovel().getId(), pagamentoAtualizacao.getContaGeral().getConta().getReferencia());

				} else if (pagamentoAtualizacao.getContaGeral() != null
						&& pagamentoAtualizacao.getContaGeral().getContaHistorico() != null) {

					colecaoNegativadorMovimentoRegItem = repositorioSpcSerasa
							.obterNegativadorMovimentoRegItemAssociadosAContaHistorico(pagamentoAtualizacao.getImovel()
									.getId(), pagamentoAtualizacao.getContaGeral().getContaHistorico()
									.getAnoMesReferenciaConta());

				}

				if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
					// Caso existam itens de negativa��o associados � conta do
					// pagamento
					Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0009 - Atualizar Item da Negativa��o - Efetuar
						// Pagamento].
						atualizarNegativadorMovimentoRegItemPorPagamento(idNmri, pagamentoAtualizacao);
					}
				}
			}

			// Situa��o 2: Pagamento alterado para outra conta
			if (idContaPagamentoAnterior != null && !idContaPagamentoAnterior.equals(idContaPagamentoAtualizacao)) {
				// 2.Caso o pagamento esteja sendo alterado para outra conta

				// 2.1.O sistema obt�m dados da conta anterior
				Object[] dadosContaAnterior = repositorioSpcSerasa
						.pesquisarImovelEReferenciaDaConta(idContaPagamentoAnterior);
				Integer idImovelContaAnterior = (Integer) dadosContaAnterior[0];
				Integer amReferenciaContaAnterior = (Integer) dadosContaAnterior[1];
				// 2.2.O sistema verifica se existem itens de negativa��o
				// associados � conta anterior
				// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
				// passando o identificador do im�vel (IMOV_ID da tabela CONTA)
				// e a refer�ncia (CNTA_AMREFERENCIACONTA da tabela CONTA).
				Collection colecaoNegativadorMovimentoRegItemDesfazer = obterItensNegativacaoAssociadosAConta(
						idImovelContaAnterior, amReferenciaContaAnterior);

				if (colecaoNegativadorMovimentoRegItemDesfazer != null
						&& !colecaoNegativadorMovimentoRegItemDesfazer.isEmpty()) {
					// 1.1.Caso existam itens de negativa��o associados � conta
					// anterior
					Iterator iterNmri = colecaoNegativadorMovimentoRegItemDesfazer.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0008 - Atualizar Item da Negativa��o - Desfazer
						// Pagamento].
						atualizarNegativadorMovimentoRegItemDesfazerPagamento(idNmri);
					}
				}

				// 2.4.O sistema verifica se existem itens de negativa��o
				// associados � conta atual do pagamento
				// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
				// passando o identificador do im�vel (IMOV_ID da tabela IMOVEL)
				// e a refer�ncia (Refer�ncia da Conta).

				Integer referencia = null;

				if (pagamentoAtualizacao.getContaGeral() != null) {

					if (pagamentoAtualizacao.getContaGeral().getConta() != null) {

						if (((Integer) pagamentoAtualizacao.getContaGeral().getConta().getReferencia()) != null) {

							referencia = pagamentoAtualizacao.getContaGeral().getConta().getReferencia();

						}

					} else if (pagamentoAtualizacao.getContaGeral().getContaHistorico() != null) {

						if (((Integer) pagamentoAtualizacao.getContaGeral().getContaHistorico()
								.getAnoMesReferenciaConta()) != null) {

							referencia = pagamentoAtualizacao.getContaGeral().getContaHistorico()
									.getAnoMesReferenciaConta();

						}

					}

				}

				// Collection colecaoNegativadorMovimentoRegItemEfetuar =
				// obterItensNegativacaoAssociadosAConta(
				// pagamentoAtualizacao.getImovel().getId(),
				// pagamentoAtualizacao.getContaGeral().getConta().getReferencia());

				Collection colecaoNegativadorMovimentoRegItemEfetuar = obterItensNegativacaoAssociadosAConta(
						pagamentoAtualizacao.getImovel().getId(), referencia);

				if (colecaoNegativadorMovimentoRegItemEfetuar != null
						&& !colecaoNegativadorMovimentoRegItemEfetuar.isEmpty()) {
					// 2.4.1.Caso existam itens de negativa��o associados �
					// conta atual
					Iterator iterNmri = colecaoNegativadorMovimentoRegItemEfetuar.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0009 - Atualizar Item da Negativa��o - Efetuar
						// Pagamento].
						atualizarNegativadorMovimentoRegItemPorPagamento(idNmri, pagamentoAtualizacao);
					}
				}

			}

			// Situa��o 3: Valor e/ou Data do Pagamento alterados, sem mudan�a
			// da conta do pagamento
			if (idContaPagamentoAnterior != null && idContaPagamentoAnterior.equals(idContaPagamentoAtualizacao)) {

				// 3.Caso o valor e/ou a data do pagamento estejam sendo
				// alterados,
				// sem mudan�a da conta do pagamento
				if (pagamentoAtualizacao.getValorPagamento().compareTo(pagamentoAnterior.getValorPagamento()) != 0
						|| pagamentoAtualizacao.getDataPagamento().compareTo(pagamentoAnterior.getDataPagamento()) != 0) {

					// 3.1.O sistema obt�m dados da conta do pagamento
					Object[] dadosConta = repositorioSpcSerasa
							.pesquisarImovelEReferenciaDaConta(idContaPagamentoAtualizacao);
					Integer idImovelConta = (Integer) dadosConta[0];
					Integer amReferenciaConta = (Integer) dadosConta[1];
					// 3.2. O sistema verifica se existem itens de negativa��o
					// associados � conta do pagamento
					// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
					// passando o identificador do im�vel (IMOV_ID da tabela
					// CONTA)
					// e a refer�ncia (CNTA_AMREFERENCIACONTA da tabela CONTA).
					Collection colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(
							idImovelConta, amReferenciaConta);

					if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
						// 3.3.Caso existam itens de negativa��o associados �
						// conta do pagamento:
						Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
						while (iterNmri.hasNext()) {
							Integer idNmri = (Integer) iterNmri.next();
							// [SB0009 - Atualizar Item da Negativa��o - Efetuar
							// Pagamento].
							atualizarNegativadorMovimentoRegItemPorPagamento(idNmri, pagamentoAtualizacao);
						}
					}

				}

			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0266] Manter Pagamentos [SB0006] - Verifica Associa��o do Pagamento da
	 * Guia com Itens de Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2009
	 */
	public void verificaAssociacaoPagamentoGuiaComItensNegativacao(Pagamento pagamentoAtualizacao,
			Pagamento pagamentoAnterior) throws ControladorException {

		Integer idGuiaPagamentoAtualizacao = pagamentoAtualizacao.getGuiaPagamento() != null ? pagamentoAtualizacao
				.getGuiaPagamento().getId() : null;
		Integer idGuiaPagamentoAnterior = pagamentoAnterior.getGuiaPagamento() != null ? pagamentoAnterior
				.getGuiaPagamento().getId() : null;

		// Situa��o 1: Pagamento alterado para determinada guia
		if (idGuiaPagamentoAnterior == null && idGuiaPagamentoAtualizacao != null) {
			// 1.Caso o pagamento esteja sendo alterado para determinada guia

			// 1.1.O sistema verifica se existem itens de negativa��o associados
			// � guia
			// (existe ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_ITEM
			// com NMRI_ICSITDEFINITIVA=2 e GPAG_ID=Id da Guia).
			Collection colecaoNegativadorMovimentoRegItem = this
					.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuiaPagamentoAtualizacao);

			if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
				// 1.1.1. Caso existam itens de negativa��o associados � guia:
				Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();
					// [SB0009 - Atualizar Item da Negativa��o - Efetuar
					// Pagamento].
					atualizarNegativadorMovimentoRegItemPorPagamento(idNmri, pagamentoAtualizacao);
				}
			}

		}

		// Situa��o 2: Pagamento alterado para outra guia
		if (idGuiaPagamentoAnterior != null && !idGuiaPagamentoAnterior.equals(idGuiaPagamentoAtualizacao)) {
			// 2.Caso o pagamento esteja sendo alterado para outra guia

			// 2.1.O sistema verifica se existem itens de negativa��o associados
			// � guia anterior
			Collection colecaoNegativadorMovimentoRegItemDesfazer = this
					.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuiaPagamentoAnterior);

			if (colecaoNegativadorMovimentoRegItemDesfazer != null
					&& !colecaoNegativadorMovimentoRegItemDesfazer.isEmpty()) {
				// 2.2. Caso existam itens de negativa��o associados � guia
				// anterior:
				Iterator iterNmri = colecaoNegativadorMovimentoRegItemDesfazer.iterator();
				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();
					// [SB0008 - Atualizar Item da Negativa��o - Desfazer
					// Pagamento].
					atualizarNegativadorMovimentoRegItemDesfazerPagamento(idNmri);
				}
			}

			// 2.3.O sistema verifica se existem itens de negativa��o associados
			// � guia atual do pagamento
			Collection colecaoNegativadorMovimentoRegItemEfetuar = this
					.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuiaPagamentoAtualizacao);

			if (colecaoNegativadorMovimentoRegItemEfetuar != null
					&& !colecaoNegativadorMovimentoRegItemEfetuar.isEmpty()) {
				// 2.3.1.Caso existam itens de negativa��o associados � guia
				// atual:
				Iterator iterNmri = colecaoNegativadorMovimentoRegItemEfetuar.iterator();
				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();
					// [SB0009 - Atualizar Item da Negativa��o - Efetuar
					// Pagamento].
					atualizarNegativadorMovimentoRegItemPorPagamento(idNmri, pagamentoAtualizacao);
				}
			}
		}

		// Situa��o 3: Valor e/ou Data do Pagamento alterados, sem mudan�a da
		// guia do pagamento
		if (idGuiaPagamentoAnterior != null && idGuiaPagamentoAnterior.equals(idGuiaPagamentoAtualizacao)) {

			// 3.Caso o valor e/ou a data do pagamento estejam sendo alterados,
			// sem mudan�a da guia do pagamento
			if (pagamentoAtualizacao.getValorPagamento().compareTo(pagamentoAnterior.getValorPagamento()) != 0
					|| pagamentoAtualizacao.getDataPagamento().compareTo(pagamentoAnterior.getDataPagamento()) != 0) {

				// 3.1.O sistema verifica se existem itens de negativa��o
				// associados � guia do pagamento
				Collection colecaoNegativadorMovimentoRegItem = this
						.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuiaPagamentoAtualizacao);

				if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
					// 3.3.Caso existam itens de negativa��o associados � conta
					// do pagamento:
					Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0009 - Atualizar Item da Negativa��o - Efetuar
						// Pagamento].
						atualizarNegativadorMovimentoRegItemPorPagamento(idNmri, pagamentoAtualizacao);
					}
				}

			}

		}

	}

	/**
	 * [UC0266] Manter Pagamentos [SB0010] - Verifica Associa��o Novo Tipo
	 * Documento do Pagamento com Itens de Negativa��o.
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2009
	 */
	public void verificaAssociacaoNovoTipoDocumentoPagamentoComItensNegativacao(Pagamento pagamentoAtualizacao,
			Pagamento pagamentoAnterior) throws ControladorException {
		try {

			Integer idGuiaPagamentoAtualizacao = pagamentoAtualizacao.getGuiaPagamento() != null ? pagamentoAtualizacao
					.getGuiaPagamento().getId() : null;
			Integer idGuiaPagamentoAnterior = pagamentoAnterior.getGuiaPagamento() != null ? pagamentoAnterior
					.getGuiaPagamento().getId() : null;
			Integer idContaPagamentoAtualizacao = pagamentoAtualizacao.getContaGeral() != null ? pagamentoAtualizacao
					.getContaGeral().getId() : null;
			Integer idContaPagamentoAnterior = pagamentoAnterior.getContaGeral() != null ? pagamentoAnterior
					.getContaGeral().getId() : null;

			// Situa��o 1: Pagamento desvinculado da conta
			if (idContaPagamentoAnterior != null && idContaPagamentoAtualizacao == null) {
				// 1.Caso o pagamento esteja sendo desvinculado da conta

				// 1.1.O sistema obt�m dados da conta do pagamento
				Object[] dadosContaAnterior = repositorioSpcSerasa
						.pesquisarImovelEReferenciaDaConta(idContaPagamentoAnterior);
				Integer idImovelContaAnterior = (Integer) dadosContaAnterior[0];
				Integer amReferenciaContaAnterior = (Integer) dadosContaAnterior[1];

				// 1.2.O sistema verifica se existem itens de negativa��o
				// associados � conta do pagamento
				// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
				// passando o identificador do im�vel (IMOV_ID da tabela CONTA)
				// e a refer�ncia (CNTA_AMREFERENCIACONTA da tabela CONTA).
				Collection colecaoNegativadorMovimentoRegItemDesfazer = obterItensNegativacaoAssociadosAConta(
						idImovelContaAnterior, amReferenciaContaAnterior);

				if (colecaoNegativadorMovimentoRegItemDesfazer != null
						&& !colecaoNegativadorMovimentoRegItemDesfazer.isEmpty()) {
					// 1.3.Caso existam itens de negativa��o associados � conta
					// do pagamento:
					Iterator iterNmri = colecaoNegativadorMovimentoRegItemDesfazer.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0008 - Atualizar Item da Negativa��o - Desfazer
						// Pagamento].
						atualizarNegativadorMovimentoRegItemDesfazerPagamento(idNmri);
					}
				}
			}

			// Situa��o 2: Pagamento desvinculado da guia
			if (idGuiaPagamentoAnterior != null && idGuiaPagamentoAtualizacao == null) {
				// 2.Caso o pagamento esteja sendo desvinculado da guia

				// 2.1. O sistema verifica se existem itens de negativa��o
				// associados � guia do pagamento
				Collection colecaoNegativadorMovimentoRegItem = this
						.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuiaPagamentoAnterior);

				if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
					// 2.2.Caso existam itens de negativa��o associados � guia
					// do pagamento:
					Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0008 - Atualizar Item da Negativa��o - Desfazer
						// Pagamento].
						atualizarNegativadorMovimentoRegItemDesfazerPagamento(idNmri);
					}
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0150] Retificar Conta [SB0002] - Gerar Dados da Conta - 6.
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItemAPartirConta(Conta conta) throws ControladorException {

		// Caso o valor total da conta seja zero atribuir o valor 2
		// caso contr�rio, atribuir o valor 1
		// Integer idCobrancaDebitoSituacao = CobrancaDebitoSituacao.PENDENTE;
		if (conta.getValorTotal().compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
			Integer idCobrancaDebitoSituacao = CobrancaDebitoSituacao.PAGO;

			// 6.O sistema verifica se existem itens de negativa��o associados �
			// conta
			// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
			Collection colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(conta.getImovel()
					.getId(), conta.getReferencia());

			if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
				// 6.1. Caso existam itens de negativa��o associados � conta:
				Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();
					// [SB0005] - Atualizar Item da Negativa��o
					atualizarNegativadorMovimentoRegItem(idNmri, ConstantesSistema.VALOR_ZERO, new Date(),
							idCobrancaDebitoSituacao);
				}
			}
		}

	}

	/**
	 * [UC0150] Retificar Conta [SB0005] - Atualizar Item da Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(Integer idNegativadorMovimentoRegItem, BigDecimal valorPago,
			Date dataSituacaoDebito, Integer idCobrancaDebitoSituacao) throws ControladorException {
		try {

			repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNegativadorMovimentoRegItem, valorPago,
					dataSituacaoDebito, idCobrancaDebitoSituacao);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0266] Manter Pagamentos [FS0003] - Verificar exist�ncia de itens de
	 * negativa��o para a conta inclu�da
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void verificarExistenciaItensNegativacaoParaContaIncluida(Conta conta) throws ControladorException {
		// try{

		// Caso a situa��o atual da conta corresponda a inclu�da
		if (conta.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.INCLUIDA)) {
			// Caso existam itens de negativa��o associados � conta
			// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
			// passando o identificador do im�vel (IMOV_ID da tabela CONTA)
			// e a refer�ncia (CNTA_AMREFERENCIACONTA da tabela CONTA)
			Collection colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(conta.getImovel()
					.getId(), conta.getReferencia());

			if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
				// exibir a mensagem �N�o � poss�vel cancelar a conta. H�
				// negativa��o para o
				// im�vel <<IMOV_ID da tabela CONTA>> e esta conta faz parte do
				// d�bito negativado�
				throw new ControladorException("atencao.nao_e_possivel_cancelar_conta", null, conta.getImovel().getId()
						.toString());

			}
		}

		// } catch (ErroRepositorioException e) {
		// sessionContext.setRollbackOnly();
		// throw new ControladorException("erro.sistema", e);
		// }
	}

	/**
	 * [UC0147] - Cancelar Conta Fluxo Principal 1.1.2. Verificar se h� rela��o
	 * do cancelamento com itens de negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 17/09/2009
	 */
	public void verificarRelacaoDoCancelamentoComItensNegativacao(Conta conta, Integer idContaCanceladaRetificacao)
			throws ControladorException {

		// Verificar se existem itens de negativa��o associados � conta
		// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
		// passando o identificador do im�vel (IMOV_ID da tabela CONTA)
		// e a refer�ncia (CNTA_AMREFERENCIACONTA da tabela CONTA).
		Collection colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(
				conta.getImovel().getId(), conta.getReferencia());

		if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
			// Caso existam itens de negativa��o associados � conta:
			Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
			while (iterNmri.hasNext()) {
				Integer idNmri = (Integer) iterNmri.next();
				// se idContaCanceladaRetificacao for null [SB0001 - Atualizar
				// Item da Negativa��o].
				// e se != null [SB0002 - Atualizar Item Negativa��o - Conta
				// Retificada].
				atualizarNegativadorMovimentoRegItem(idNmri, new Date(), CobrancaDebitoSituacao.CANCELADO,
						idContaCanceladaRetificacao, conta.getId());
			}

		}

	}

	/**
	 * [UC0147] Cancelar Conta [SB0001] - Atualizar Item da Negativa��o [SB0002]
	 * - Atualizar Item Negativa��o - Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(Integer idNegativadorMovimentoRegItem, Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao, Integer idContaCanceladaPorRetificacao,
			Integer idContaRetificadaECancelada) throws ControladorException {
		try {

			repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNegativadorMovimentoRegItem,
					dataSituacaoDebito, idCobrancaDebitoSituacao, idContaCanceladaPorRetificacao,
					idContaRetificadaECancelada);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0329] Restabelecer Situa��o Anterior de Conta Verificar se h� rela��o
	 * do desfazer cancelamento ou retifica��o com itens de negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 21/09/2009
	 */
	public void verificarRelacaoDoDesfazerCancelamentoOuRetificacaoComItensNegativacao(
			Collection colecaoNegativadorMovimentoRegItem, Integer situacaoAtualConta,
			Conta contaCanceladaOuCanceladaPorRetificacao, Conta contaRetificada) throws ControladorException {
		try {

			// 1.8. Caso existam itens de negativa��o associados � conta
			if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {

				if (situacaoAtualConta.equals(DebitoCreditoSituacao.CANCELADA)) {
					// 1.8.1. Caso a Situa��o Atual da Conta corresponda a
					// cancelada:
					Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0001 - Atualizar Item da Negativa��o-Desfazer
						// Cancelamento].
						repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNmri, new Date(),
								CobrancaDebitoSituacao.PENDENTE, null);
					}

				} else if (situacaoAtualConta.equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)) {
					// 1.8.2. Caso a Situa��o Atual da Conta corresponda a
					// cancelada por retifica��o:
					Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0002 - Atualizar Item da Negativa��o- Desfazer
						// Retifica��o].
						repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNmri, new Date(),
								CobrancaDebitoSituacao.PENDENTE, contaCanceladaOuCanceladaPorRetificacao.getId(),
								contaRetificada.getId(), contaRetificada.getValorTotal(), null);
					}
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0188] Manter Guia de Pagamento verifica se existe negativador
	 * movimento reg item associado a guia de pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAGuiaPagamento(Integer idGuia)
			throws ControladorException {
		try {

			return repositorioSpcSerasa.pesquisarNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuia);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0188] Manter Guia de Pagamento Fluxo Principal 2.1.2.2. Verificar se
	 * h� rela��o do cancelamento com itens de negativa��o:
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public void verificarRelacaoDoCancelamentoComItensNegativacao(GuiaPagamento guiaPagamento)
			throws ControladorException {
		try {

			// 2.1.2.2.1. Verificar se existem itens de negativa��o associados �
			// guia
			Collection colecaoNegativadorMovimentoRegItem = this
					.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(guiaPagamento.getId());

			if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
				// Caso existam itens de negativa��o associados � guia:
				Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();
					// [SB0001 - Atualizar Item da Negativa��o].
					repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNmri, new Date(),
							CobrancaDebitoSituacao.CANCELADO);
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0213] Desfazer Parcelamento de D�bitos [SB0001] - Atualizar Item da
	 * Negativa��o
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos [SB0013] - Atualizar Item da
	 * Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 24/09/2009
	 */
	public void verificarRelacaoDoParcelamentoComItensNegativacao(Parcelamento parcelamento, Conta conta,
			GuiaPagamento guiaPagamento) throws ControladorException {
		try {

			Integer idConta = conta != null ? conta.getId() : null;
			Integer idGuiaPagamento = guiaPagamento != null ? guiaPagamento.getId() : null;
			Collection colecaoNegativadorMovimentoRegItem = null;

			if (idConta != null) {

				// Verificar se existem itens de negativa��o associados � conta
				// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
				colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(conta.getImovel().getId(),
						conta.getReferencia());

			} else if (idGuiaPagamento != null) {
				// Verificar se existem itens de negativa��o associados � guia
				colecaoNegativadorMovimentoRegItem = this
						.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(guiaPagamento.getId());

			}

			if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
				// 2.2.1.2. Caso existam itens de negativa��o associados �
				// conta: OU
				// 2.3.1.2. Caso existam itens de negativa��o associados � guia:
				Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();

					if (parcelamento != null) {
						// [UC0214] Efetuar Parcelamento de D�bitos
						// [SB0013] - Atualizar Item da Negativa��o
						Integer codigoExclusaoTipo = repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNmri,
								CobrancaDebitoSituacao.PARCELADO, parcelamento.getParcelamento(), false);

						if (codigoExclusaoTipo == null) {
							inserirOuAtualizarNegativadorMovimentoRegParcelamento(idNmri, parcelamento);
						}
					} else {
						// [UC0213] Desfazer Parcelamento de D�bitos
						// [SB0001] - Atualizar Item da Negativa��o
						repositorioSpcSerasa.atualizarNegativadorMovimentoRegItem(idNmri,
								CobrancaDebitoSituacao.PENDENTE, new Date(), true);
					}

				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw e;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos [SB0013] - Atualizar Item da
	 * Negativa��o
	 * 
	 * @author Vivianne Sousa
	 * @date 24/09/2009
	 */
	public void inserirOuAtualizarNegativadorMovimentoRegParcelamento(Integer idNmri, Parcelamento parcelamento)
			throws ControladorException {
		try {

			Integer idNmr = repositorioSpcSerasa.pesquisarIdNegativadorMovimentoReg(idNmri);

			NegativadorMovimentoRegParcelamento nmrp = repositorioSpcSerasa
					.pesquisarNegativadorMovimentoRegParcelamento(idNmr, parcelamento.getId());

			// 3. Caso existam dados do parcelamento para a negativa��o,
			// atualizar os dados do parcelamento na tabela
			// NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO
			// 4. Caso contr�rio, inserir os dados do parcelamento
			// na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO

			if (nmrp == null) {
				// Caso n�o existam dados do parcelamento para a negativa��o
				nmrp = new NegativadorMovimentoRegParcelamento();

				NegativadorMovimentoReg nmr = new NegativadorMovimentoReg();
				nmr.setId(idNmr);
				nmrp.setNegativadorMovimentoReg(nmr);

				nmrp.setIndicadorParcelamentoAtivo(ConstantesSistema.SIM);
			}

			nmrp.setParcelamento(parcelamento);

			if (parcelamento.getValorParcelado() != null) {
				nmrp.setValorParcelado(parcelamento.getValorParcelado());
			}

			if (parcelamento.getValorEntrada() != null) {
				nmrp.setValorParceladoEntrada(parcelamento.getValorEntrada());
			}

			if (parcelamento.getNumeroPrestacoes() != null) {
				nmrp.setNumeroPrestacoes(parcelamento.getNumeroPrestacoes());
			}

			nmrp.setUltimaAlteracao(new Date());

			this.getControladorUtil().inserirOuAtualizar(nmrp);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw e;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * @author Vivianne Sousa
	 * @date 25/09/2009
	 */
	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(
			Collection<GuiaPagamento> colecaoGuiasPagamento) throws ControladorException {
		try {

			for (GuiaPagamento guiaPagamento : colecaoGuiasPagamento) {

				// 6.2. Verificar se existem itens de negativa��o associados �
				// guia
				Collection colecaoNegativadorMovimentoRegItem = this
						.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(guiaPagamento.getId());

				if (colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()) {
					// Caso existam itens de negativa��o associados � guia:
					Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
					while (iterNmri.hasNext()) {
						Integer idNmri = (Integer) iterNmri.next();
						// [SB0001 - Atualizar Item da Negativa��o].
						repositorioSpcSerasa.atualizarIndicadorSituacaoDefinitivaNmri(idNmri, ConstantesSistema.SIM);
					}
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(ContaHistorico contaHistorico)
			throws ControladorException {
		try {

			Integer idContaHistorico = contaHistorico.getId();

			Object[] dadosContaAnterior = repositorioSpcSerasa.pesquisarImovelEReferenciaDaConta(idContaHistorico);
			Integer idImovelContaHistorico = (Integer) dadosContaAnterior[0];
			Integer amReferenciaContaHistorico = (Integer) dadosContaAnterior[1];

			// 4.2. Verificar se existem itens de negativa��o associados � conta
			// [UC0937 - Obter Itens de Negativa��o Associados � Conta]
			// passando o identificador do im�vel (IMOV_ID da tabela
			// CONTA_HISTORICO)
			// e a refer�ncia (CNHI_AMREFERENCIACONTA da tabela
			// CONTA_HISTORICO).
			Collection colecaoNegativadorMovimentoRegItem = obterItensNegativacaoAssociadosAConta(
					idImovelContaHistorico, amReferenciaContaHistorico);

			if (colecaoNegativadorMovimentoRegItem != null) {

				Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();

				while (iterNmri.hasNext()) {
					Integer idNmri = (Integer) iterNmri.next();

					// Integer idContaNmri = repositorioSpcSerasa.
					// pesquisarIdContaNegativadorMovimentoRegItem(idNmri);
					//
					// 4.3.1.1. Caso a conta correspondente ao item seja
					// igual � conta transferida para o hist�rico
					// if(idContaNmri.equals(idContaHistorico)){

					// 4.3.1.1.1. Situa��o Atual da Conta = DCST_IDATUAL da
					// tabela CONTA_HISTORICO.
					// Integer idSituacaoAtualConta = repositorioSpcSerasa.
					// pesquisarDebitoCreditoSituacaoAtualDaConta(idContaHistorico);
					Integer idSituacaoAtualConta = contaHistorico.getDebitoCreditoSituacaoAtual().getId();

					if (!idSituacaoAtualConta.equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)) {
						// 4.3.1.2.2. Caso a situa��o atual da conta n�o
						// corresponda
						// a �cancelada por retifica��o� (Situa��o Atual da
						// Conta diferente de 4),
						// atualizar a tabela NEGATIVADOR_MOVIMENTO_REG_ITEM
						repositorioSpcSerasa.atualizarIndicadorSituacaoDefinitivaNmri(idNmri, ConstantesSistema.SIM);

					}

					// }

				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1006] Gerar Resumo da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 29/10/2009
	 */
	public void gerarResumoNegativacao(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException {

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		Integer numeroExecucao = sistemaParametro.getNumeroExecucaoResumoNegativacao() + 1;
		Integer numeroExecucaoParaRemocao = sistemaParametro.getNumeroExecucaoResumoNegativacao() - 1;
		// -------------------------
		// Registrar o in�cio do processamento da Unidade de Processamento do
		// Batch
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.ROTA, idRota);

		try {

			// 2. O sistema exclui o resumo di�rio das negativa��es das rotas
			// que tiveram a execu��o descontinuada
			repositorioSpcSerasa.apagarResumoNegativacao(numeroExecucaoParaRemocao, idRota);
			repositorioSpcSerasa.apagarResumoNegativacao(numeroExecucao, idRota);

			Collection coll = repositorioSpcSerasa.consultarNegativacaoParaGerarResumoDiarioNegativacao(idRota);

			if (coll != null) {

				Iterator it = coll.iterator();
				while (it.hasNext()) {

					Object[] obj = (Object[]) it.next();

					ResumoNegativacaoHelper rn = new ResumoNegativacaoHelper();

					Negativador negativador = new Negativador();
					NegativacaoComando negativacaoComando = new NegativacaoComando();
					CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
					CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
					GerenciaRegional gerenciaRegional = new GerenciaRegional();
					UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
					Localidade elo = new Localidade();
					Localidade localidade = new Localidade();
					SetorComercial setorComercial = new SetorComercial();
					Quadra quadra = new Quadra();
					ImovelPerfil imovelPerfil = new ImovelPerfil();
					Categoria categoria = new Categoria();
					ClienteTipo clienteTipo = new ClienteTipo();
					EsferaPoder esferaPoder = new EsferaPoder();

					negativador.setId((Integer) obj[0]);
					rn.setNegativador(negativador);

					negativacaoComando.setId((Integer) obj[1]);
					rn.setNegativacaoComando(negativacaoComando);

					rn.setDataProcessamentoEnvio((Date) obj[2]);

					rn.setIndicadorNegativacaoConfirmada(((Integer) obj[3]).shortValue());

					cobrancaDebitoSituacao.setId((Integer) obj[4]);
					rn.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);

					cobrancaGrupo.setId((Integer) obj[5]);
					rn.setCobrancaGrupo(cobrancaGrupo);

					gerenciaRegional.setId((Integer) obj[6]);
					rn.setGerenciaRegional(gerenciaRegional);

					unidadeNegocio.setId((Integer) obj[7]);
					rn.setUnidadeNegocio(unidadeNegocio);

					elo.setId((Integer) obj[8]);
					rn.setLocalidadeElo(elo);

					localidade.setId((Integer) obj[9]);
					rn.setLocalidade(localidade);

					setorComercial.setId((Integer) obj[10]);
					rn.setSetorComercial(setorComercial);

					quadra.setId((Integer) obj[11]);
					rn.setQuadra(quadra);

					rn.setCodigoSetorcomercial((Integer) obj[12]);
					rn.setNumeroQuadra((Integer) obj[13]);

					imovelPerfil.setId((Integer) obj[14]);
					rn.setImovelPerfil(imovelPerfil);

					categoria.setId((Integer) obj[15]);
					rn.setCategoria(categoria);

					clienteTipo.setId((Integer) obj[16]);
					rn.setClienteTipo(clienteTipo);

					esferaPoder.setId((Integer) obj[17]);
					rn.setEsferaPoder(esferaPoder);

					rn.setQuantidadeInclusoes((Integer) obj[18]);

					rn.setValorDebito((BigDecimal) obj[19]);
					rn.setValorPendente((BigDecimal) obj[20]);
					rn.setValorPago((BigDecimal) obj[21]);
					rn.setValorParcelado((BigDecimal) obj[22]);
					rn.setValorCancelado((BigDecimal) obj[23]);

					// **********************************************************************
					// RM3755
					// Autor: Ivan Sergio
					// Data: 11/01/2011
					// **********************************************************************
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId((Integer) obj[24]);
					rn.setLigacaoAguaSituacao(ligacaoAguaSituacao);

					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId((Integer) obj[25]);
					rn.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
					// **********************************************************************

					rn.setNumeroExecucaoResumoNegativacao(numeroExecucao);

					ResumoNegativacao resumoNegativacao = rn.getResumoNegativacao();
					resumoNegativacao.setUltimaAlteracao(new Date());
					this.getControladorUtil().inserir(resumoNegativacao);

				}

			}
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o Fluxo Principal 3.6
	 *
	 * @author Vivianne Sousa
	 * @date 29/10/2009
	 */
	public void acompanharPagamentoDoParcelamento(Integer idFuncionalidadeIniciada, Integer idRota)
			throws ControladorException {

		// -------------------------
		// Registrar o in�cio do processamento da Unidade de Processamento do
		// Batch
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.ROTA, idRota);

		try {

			// cole��o com o id do negativador movimento reg que a situa��o de
			// d�bito de cobran�a da negativa��o
			// corresponda a �Parcelado� e o parcelamento esteja ativo
			Collection coll = repositorioSpcSerasa.pesquisarNegativadorMovimentoRegComParcelamentoAtivo(idRota);

			if (coll != null) {

				Iterator it = coll.iterator();
				while (it.hasNext()) {

					Integer idNmr = (Integer) it.next();
					NegativadorMovimentoReg nmr = new NegativadorMovimentoReg();
					nmr.setId(idNmr);

					System.out.println("-------------- NMR : " + idNmr);

					// 3.6.1. Atribuir o valor um ao Indicador de Exist�ncia de
					// Parcelamento.
					Short indicadorExistenciaParcelamento = ConstantesSistema.SIM;

					while (indicadorExistenciaParcelamento.equals(ConstantesSistema.SIM)) {

						// [SB0006]-Acompanhar Pagamento do Parcelamento
						indicadorExistenciaParcelamento = this.acompanharPagamentoParcelamento(nmr,
								indicadorExistenciaParcelamento);

					}

				}

			}
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o Fluxo principal Item 1.0
	 *
	 * @author Vivianne Sousa
	 * @date 09/02/2010
	 */
	public List consultarSetorParaGerarResumoDiarioNegativacao() throws ControladorException {

		List retorno = null;

		try {
			retorno = repositorioSpcSerasa.consultarSetorParaGerarResumoDiarioNegativacao();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	private void desfazerMovimentoExclusaoIncompleto() throws ControladorException {
		try {

			Integer idMovimentoIncompleto = repositorioSpcSerasa.verificarOcorrenciaMovimentoExclusaoIncompleto();

			// Caso exista movimento de exclus�o incompleto
			if (idMovimentoIncompleto != null) {

				repositorioSpcSerasa.atualizarNegativacaoImoveis(idMovimentoIncompleto);

				repositorioSpcSerasa.atualizarCodigoExclusaoTipoNegativadorMovimentoReg(idMovimentoIncompleto);

				repositorioSpcSerasa.apagarNegativadorMovimentoReg(idMovimentoIncompleto);

				repositorioSpcSerasa.apagarNegativadorMovimento(idMovimentoIncompleto);

			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0688] Gerar Resumo Di�rio da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public boolean existeOcorrenciaMovimentoExclusaoIncompleto() throws ControladorException {
		try {

			boolean retorno = false;
			Integer idMovimentoIncompleto = repositorioSpcSerasa.verificarOcorrenciaMovimentoExclusaoIncompleto();

			// Caso exista movimento de exclus�o incompleto
			if (idMovimentoIncompleto != null) {
				retorno = true;
			}

			return retorno;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * UC1009 � Obter Itens de Negativa��o Associados � Guia
	 * 
	 * pesquisa ocorr�ncia na tabela NEGATIVADOR_MOVIMENTO_REG_ITEM com
	 * NMRI_ICSITDEFINITIVA=2 e GPAG_ID=GPAG_ID do PAGAMENTO
	 * 
	 * @author Vivianne Sousa
	 * @data 10/03/2010
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(Integer idGuia)
			throws ControladorException {
		try {

			Collection colecaoRetorno = repositorioSpcSerasa
					.obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(idGuia);

			if (colecaoRetorno != null && !colecaoRetorno.isEmpty()) {
				// atualiza NMRG_ICITEMATUALIZADO para 1
				repositorioSpcSerasa.atualizarIndicadorItemAtualizadoNegativadorMovimentoReg(ConstantesSistema.SIM,
						colecaoRetorno);
			}
			return colecaoRetorno;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	public List consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao() throws ControladorException {

		try {
			return repositorioSpcSerasa.consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0473] Consultar Dados Complementares do Im�vel
	 *
	 * @author Vivianne Sousa
	 * @date 04/05/2010
	 */
	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel) throws ControladorException {

		try {
			Collection retorno = null;

			Collection colecaoDadosNegativadorMovimentoReg = repositorioSpcSerasa
					.consultarDadosNegativadorMovimentoReg(idImovel);

			if (colecaoDadosNegativadorMovimentoReg != null && !colecaoDadosNegativadorMovimentoReg.isEmpty()) {

				retorno = new ArrayList();
				Map mapDadosNegativadorMovimentoReg = new HashMap();
				Iterator it = colecaoDadosNegativadorMovimentoReg.iterator();
				while (it.hasNext()) {

					Object[] dados = (Object[]) it.next();
					String negativador = (String) dados[0];

					if (mapDadosNegativadorMovimentoReg.containsKey(negativador)) {

						NegaticacaoAceitaHelper helper = (NegaticacaoAceitaHelper) mapDadosNegativadorMovimentoReg
								.get(negativador);
						if (((Integer) dados[1]) == 1) {
							// inclus�o confirmada
							helper.setInclusoesConfirmadas((Integer) dados[2]);
						} else if (((Integer) dados[1]) == 2) {
							// inclus�o n�o confirmada
							helper.setInclusoesNaoConfirmadas((Integer) dados[2]);
						}

					} else {
						NegaticacaoAceitaHelper helper = new NegaticacaoAceitaHelper();
						helper.setNomeNegativador((String) dados[0]);
						if (((Integer) dados[1]) == 1) {
							// inclus�o confirmada
							helper.setInclusoesConfirmadas((Integer) dados[2]);
						} else if (((Integer) dados[1]) == 2) {
							// inclus�o n�o confirmada
							helper.setInclusoesNaoConfirmadas((Integer) dados[2]);
						}
						mapDadosNegativadorMovimentoReg.put(negativador, helper);

					}
				}

				retorno = new ArrayList(mapDadosNegativadorMovimentoReg.values());

			}

			return retorno;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0651] - Inserir Comando de Negativa��o [FS0030] - Verificar exist�ncia
	 * de inclus�o no negativador para o im�vel
	 * 
	 * [UC0671] - Gerar Movimento de Inclus�o de Negativa��o [SB0005] � Gerar
	 * Negativa��o para o Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @data 06/05/2010
	 */
	public boolean verificarExistenciaDeInclusaoNoNegativadorParaImovel(Integer idImovel, Integer idNegativador)
			throws ControladorException {
		try {
			boolean existeInclusaoNoNegativadorParaImovel = false;
			Integer idReg = repositorioSpcSerasa.verificarExistenciaDeInclusaoNoNegativadorParaImovel(idImovel,
					idNegativador);
			if (idReg != null) {
				existeInclusaoNoNegativadorParaImovel = true;
			}
			return existeInclusaoNoNegativadorParaImovel;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0651] Inserir Comando de Negativa��o [SB0005] � Obter D�bito do Im�vel
	 * 
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o [SB0006] � Verificar
	 * Crit�rio de Negativa��o para o Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @data 21/06/2010
	 */
	public Collection retirarContaPagaOuParceladaEEntradaParcelamento(Collection colecaoContasValores)
			throws ControladorException {

		// 2.Retirar das listas retornadas pelo [UC0067]
		// os itens que possuam valor de pagamento e tamb�m as contas parceladas
		// (DCST_IDATUAL da tabela CONTA com o valor correspondente a
		// parcelada),
		// as contas de entrada de parcelamento (PARC_ID da tabela CONTA com o
		// valor diferente de nulo)
		if (colecaoContasValores != null && !colecaoContasValores.isEmpty()) {

			Iterator iteraConta = colecaoContasValores.iterator();

			while (iteraConta.hasNext()) {

				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iteraConta.next();

				Conta conta = contaValoresHelper.getConta();

				DebitoCreditoSituacao debito = conta.getDebitoCreditoSituacaoAtual();

				if ((contaValoresHelper.getValorPago() != null && contaValoresHelper.getValorPago().compareTo(
						ConstantesSistema.VALOR_ZERO) == 1)
						|| debito.getId().intValue() == DebitoCreditoSituacao.PARCELADA.intValue()
						|| conta.getParcelamento() != null) {

					iteraConta.remove();
				}

			}
		}

		return colecaoContasValores;
	}

	/**
	 * [UC0651] Inserir Comando de Negativa��o [SB0005] � Obter D�bito do Im�vel
	 * 
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o [SB0006] � Verificar
	 * Crit�rio de Negativa��o para o Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @data 21/06/2010
	 */
	public Collection retirarGuiaPagamentoDeEntradaParcelamento(Collection colecaoGuiaPagamentoValoresHelper)
			throws ControladorException {

		// 2. Retirar das listas retornadas pelo [UC0067] (lista de contas e
		// guias de pagamento) os itens que possuam valor de pagamento e tamb�m
		// as contas parceladas (DCST_IDATUAL da tabela CONTA com o valor
		// correspondente a parcelada), as contas de entrada de parcelamento
		// (PARC_ID da tabela CONTA com o valor diferente de nulo) e as guias de
		// entrada de parcelamento (DBTP_ID da tabela GUIA_PAGAMENTO com o valor
		// correspondente a entrada de parcelamento).

		if (colecaoGuiaPagamentoValoresHelper != null && !colecaoGuiaPagamentoValoresHelper.isEmpty()) {

			Iterator iteraGuia = colecaoGuiaPagamentoValoresHelper.iterator();

			while (iteraGuia.hasNext()) {

				GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) iteraGuia.next();

				GuiaPagamento guiaPagamento = guiaPagamentoValoresHelper.getGuiaPagamento();

				if ((guiaPagamentoValoresHelper.getValorPago() != null && guiaPagamentoValoresHelper.getValorPago()
						.compareTo(ConstantesSistema.VALOR_ZERO) == 1)
						|| guiaPagamento.getDebitoTipo().getId().equals(DebitoTipo.ENTRADA_PARCELAMENTO)) {

					iteraGuia.remove();
				}

			}
		}

		return colecaoGuiaPagamentoValoresHelper;
	}

	/**
	 * [UC0472] Consultar Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @data 03/12/2010
	 */
	public Collection pesquisarNegativadorRetornoMotivoDoReg(Integer idImovel) throws ControladorException {

		try {
			return repositorioSpcSerasa.pesquisarNegativadorRetornoMotivoDoReg(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0681] Consultar Movimentos dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 07/12/2010
	 */
	public void atualizarIndicadorCorrecaoEUsuarioCorrecao(Integer usuarioCorrecao, Short indicadorCorrecao,
			Collection colecaoIdsNegativadorMovimentoReg) throws ControladorException {

		try {
			repositorioSpcSerasa.atualizarIndicadorCorrecaoEUsuarioCorrecao(usuarioCorrecao, indicadorCorrecao,
					colecaoIdsNegativadorMovimentoReg);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * @author Vivianne Sousa
	 * @date 21/12/2010
	 *
	 * @param codigoImovel
	 * @param codigoCobrancaSituacao
	 */
	public boolean existeImovelCobrancaSituacaoParaImovel(Integer codigoImovel, Integer codigoCobrancaSituacao)
			throws ControladorException {
		try {
			boolean existeImovelCobrancaSituacaoParaImovel = false;

			List collImovelCobrancaSituacao = this.repositorioSpcSerasa.consultarImovelCobrancaSituacao(codigoImovel,
					codigoCobrancaSituacao);

			if (collImovelCobrancaSituacao != null && !collImovelCobrancaSituacao.isEmpty()) {
				existeImovelCobrancaSituacaoParaImovel = true;
			}

			return existeImovelCobrancaSituacaoParaImovel;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 * Conta a quantidade de Clientes Negativados para a Unidade, Ger�ncia e
	 * Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes
	 * Negativados
	 * 
	 * @author Mariana Victor
	 * @date 10/02/2011
	 */
	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(
			DadosConsultaNegativacaoHelper helper, NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia,
			Integer idUnidade, Short indicadorExcluido) throws ControladorException {
		try {
			return this.repositorioSpcSerasa.pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(helper,
					negativadorMovimentoReg, idGerencia, idUnidade, indicadorExcluido);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 * Soma os valores de d�bitos dos Clientes Negativados para a Unidade,
	 * Ger�ncia e Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de
	 * Clientes Negativados
	 * 
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(
			DadosConsultaNegativacaoHelper helper, NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia,
			Integer idUnidade, Short indicadorExcluido) throws ControladorException {
		try {
			return this.repositorioSpcSerasa.pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(
					helper, negativadorMovimentoReg, idGerencia, idUnidade, indicadorExcluido);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 * Soma os valores Pagos dos Clientes Negativados para a Unidade, Ger�ncia e
	 * Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de Clientes
	 * Negativados
	 * 
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(
			DadosConsultaNegativacaoHelper helper, NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia,
			Integer idUnidade, Short indicadorExcluido) throws ControladorException {
		try {
			return this.repositorioSpcSerasa.pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(
					helper, negativadorMovimentoReg, idGerencia, idUnidade, indicadorExcluido);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 * Conta a quantidade de Clientes Negativados com contas pagas na Unidade,
	 * Ger�ncia e Data de Envio [UC0693] Gerar Relat�rio Acompanhamaneto de
	 * Clientes Negativados
	 * 
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(
			DadosConsultaNegativacaoHelper helper, NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia,
			Integer idUnidade, Short indicadorExcluido) throws ControladorException {
		try {
			return this.repositorioSpcSerasa.pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(helper,
					negativadorMovimentoReg, idGerencia, idUnidade, indicadorExcluido);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

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
	public void desfazerAtualizacaoExecucaoDescontinuada(Integer idFuncionalidadeIniciada, NegativacaoComando nComando)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.ROTA, 0);

		try {

			// 1. O sistema obt�m as quantidades de linhas geradas nas tabelas
			// de negativa��o pela execu��o descontinuada:

			// 1.1. Quantidade Total de Itens
			Integer qtdTotalItens = this.repositorioSpcSerasa.pesquisarQuantidadeTotalItens(nComando.getId());

			// 1.2. Quantidade Total de Regs
			Integer qtdTotalRegistros = this.repositorioSpcSerasa.pesquisarQuantidadeTotalRegistros(nComando.getId());

			// 1.3. Quantidade de Im�veis em Reg
			// Integer qtdImoveisEmRegistro =
			// this.repositorioSpcSerasa.pesquisarQuantidadeImoveisEmRegistro(nComando.getId());

			// 1.4. Quantidade de Im�veis em Negativa��o Im�veis
			Integer qtdImoveisEmNegativacao = this.repositorioSpcSerasa
					.pesquisarQuantidadeImoveisEmNegativacao(nComando.getId());

			// 1.5. Quantidade de Im�veis em Situ��o de Cobran�a
			Integer qtdImoveisEmCobrancaSituacao = this.repositorioSpcSerasa
					.pesquisarQuantidadeImoveisEmCobrancaSituacao(nComando.getId());

			/*
			 * 2. Caso a Quantidade de Im�veis em Reg, a Quantidade de Im�veis
			 * em Negativa��o Im�veis e a Quantidade de Im�veis em Situ��o de
			 * Cobran�a n�o sejam coincidentes:
			 */
			/*
			 * if (!qtdImoveisEmRegistro.equals(qtdImoveisEmNegativacao) ||
			 * !qtdImoveisEmRegistro.equals(qtdImoveisEmCobrancaSituacao) ||
			 * !qtdImoveisEmNegativacao.equals(qtdImoveisEmCobrancaSituacao)){
			 * 
			 * throw new
			 * ControladorException("atencao.quantidades_inconsistentes"); }
			 */

			// 3. O sistema efetua as exclus�es das atualiza��es
			// obrigatoriamente nesta ordem:

			// 3.1. Exclui os im�veis da situa��o de cobran�a
			Integer qtdImoveisEmCobrancaSituacaoDeletados = this.repositorioSpcSerasa
					.apagarImovelCobrancaSituacao(nComando.getId());

			/*
			 * 3.2. Caso a quantidade de im�veis exclu�dos da situa��o de
			 * cobran�a seja diferente da Quantidade de Im�veis em Situ��o de
			 * Cobran�a:
			 */
			if (!qtdImoveisEmCobrancaSituacaoDeletados.equals(qtdImoveisEmCobrancaSituacao)) {

				throw new ControladorException("atencao.imovel_situacao_cobranca_inconsistente");
			}

			// 3.3. Exclui os im�veis em negativa��o
			Integer qtdImoveisEmNegativacaoDeletados = this.repositorioSpcSerasa.apagarNegativacaoImoveis(nComando
					.getId());

			/*
			 * 3.4. Caso a quantidade de im�veis exclu�dos em negativa��o seja
			 * diferente da Quantidade de Im�veis em Negativa��o Im�veis:
			 */
			if (!qtdImoveisEmNegativacaoDeletados.equals(qtdImoveisEmNegativacao)) {

				throw new ControladorException("atencao.negativacao_imoveis_inconsistente");
			}

			// 3.5. Exclui os itens em negativa��o
			Integer qtdTotalItensDeletados = this.repositorioSpcSerasa.apagarNegativacaoMovRegItem(nComando.getId());

			/*
			 * 3.6. Caso a quantidade de itens exclu�dos em negativa��o seja
			 * diferente da Quantidade Total de Itens:
			 */
			if (!qtdTotalItensDeletados.equals(qtdTotalItens)) {

				throw new ControladorException("atencao.negativacao_mov_reg_item_inconsistente");
			}

			// Exclui os registros de parcelamento dos im�veis em negativa��o
			this.repositorioSpcSerasa.apagarNegativadorMovimentoRegParcelamento(nComando.getId());

			// 3.7. Exclui os registros dos im�veis em negativa��o
			Integer qtdTotalRegistrosDeletados = this.repositorioSpcSerasa.apagarNegativacaoMovReg(nComando.getId());

			/*
			 * 3.8. Caso a quantidade de registros dos im�veis em negativa��o
			 * seja diferente da Quantidade Total de Regs:
			 */
			if (!qtdTotalRegistrosDeletados.equals(qtdTotalRegistros)) {

				throw new ControladorException("atencao.negativacao_mov_reg_inconsistente");
			}

			// 3.9. Exclui o movimento
			this.repositorioSpcSerasa.apagarNegativadorMovimentoPorComando(nComando.getId());

			System.out.println(" %%%% Encerrando DELETE Executar Comando Negativacao ");

			UnidadeIniciada unidadeIniciada = new UnidadeIniciada();
			unidadeIniciada.setId(idUnidadeIniciada);

			this.getControladorUtil().remover(unidadeIniciada);

		} catch (Exception e) {

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);

			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * [SB0005] - Gerar Negativa��o para o Im�vel
	 */
	public boolean negativacaoPorPeriodo(Integer idImovel)  throws ControladorException {
		boolean retorno = false;
		
		Short indicadorNegativacaoPeriodo = getControladorCliente().pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(
				idImovel, new Integer(ClienteRelacaoTipo.PROPRIETARIO.toString()));
		
		if(indicadorNegativacaoPeriodo != null && indicadorNegativacaoPeriodo.compareTo(ConstantesSistema.SIM) == 0) {
			retorno = true;
		}
		
		return retorno;
	}
	
	public Collection<Object[]> verificarCriteriodeNegativacaoParaImovel(Integer idImovel, NegativacaoCriterio criterio,
			NegativacaoComando comando, boolean indicadorNegativacaoPorPeriodo) throws ControladorException {
		try {
			Object[] dadosImovel = repositorioSpcSerasa.pesquisarDadosImovelParaNegativacao(idImovel);

			Integer ocorrenciaCobrancaImovel = (Integer) this.repositorioSpcSerasa.verificaExistenciaImovelCobrancaSituacao(idImovel);

			Integer ocorrenciaCobrancaSituacaoTipo = this.repositorioSpcSerasa.verificarExistenciaDeCobrancaSituacaoTipoParaImovel(idImovel);
			
			Collection ocorrenciaCobrancaSituacaoHistorico = this.repositorioSpcSerasa.verificarExistenciaDeCobrancaSituacaoHistoricoParaImovel(idImovel);

			if (criterio.getIndicadorNegativacaoImovelParalisacao() == 2
					&& ((ocorrenciaCobrancaSituacaoTipo != null && ocorrenciaCobrancaSituacaoTipo.compareTo(new Integer(0)) > 0)
							|| ocorrenciaCobrancaSituacaoHistorico != null && !ocorrenciaCobrancaSituacaoHistorico.isEmpty())) {
				return null;
			}

			if (criterio.getIndicadorNegativacaoImovelSituacaoCobranca() == 2 && ocorrenciaCobrancaImovel > 0) {
				return null;
			}

			if (comando.getIndicadorBaixaRenda().equals(ConstantesSistema.NAO)) {
				Integer idPerfilQuadra = getControladorImovel().obterQuadraPerfil(idImovel);
				if (idPerfilQuadra.equals(2)) {
					return null;
				}
			}

			Categoria categoriaPrincipalImovel = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
			if (comando.getIndicadorOrgaoPublico().equals(ConstantesSistema.NAO)) {
				int idCategoria = 0;
				if (categoriaPrincipalImovel != null) {
					idCategoria = categoriaPrincipalImovel.getId();
					if (idCategoria == Categoria.PUBLICO_INT) {
						return null;
					}
				}
			}

			// Caso o usu�rio deseje excluir do comando os im�veis enviados e aceitos
			// em um determinado n�mero de dias em rela��o ao �ltimo envio
			if (criterio.getNumeroDiasRetorno() != null) {
				Date dataAtualMenosNumeroDiasRetorno = Util.adicionarNumeroDiasDeUmaData(
						new Date(), criterio.getNumeroDiasRetorno().intValue());

				Collection colecaoReg = repositorioSpcSerasa.pesquisarNegativadorMovimentoRegPorImovel(
						idImovel, dataAtualMenosNumeroDiasRetorno, comando.getNegativador().getId());

				if (colecaoReg != null && !colecaoReg.isEmpty()) {
					return null;
				}
			}

			// Caso o usu�rio deseje excluir do comando os im�veis rejeitados
			// por determinado(s) motivo(s) em rela��o ao �ltimo envio
			Integer ultimoNegativadorRetornoMotivoDoReg = repositorioSpcSerasa.pesquisarUltimoNegativadorRetornoMotivoDoReg(
					idImovel, comando.getNegativador().getId());

			if (ultimoNegativadorRetornoMotivoDoReg != null) {
				Integer idNegativCritNegRetMot = repositorioSpcSerasa.pesquisarIdNegativCritNegRetMot(
						ultimoNegativadorRetornoMotivoDoReg, criterio.getId());

				if (idNegativCritNegRetMot != null) {
					return null;
				}
			}

			Object[] existeCriterio = repositorioSpcSerasa.verificarExistenciaCriterio(criterio.getId());

			if (existeCriterio[0] != null && (Integer) existeCriterio[0] != 0) {
				Integer ocorrenciaLigacaoAguaCriterio = this.repositorioSpcSerasa.verificaLigacaoAguaImovelNegativacaoCriterio(
						criterio.getId(), (Integer) dadosImovel[2]);

				if (ocorrenciaLigacaoAguaCriterio == null || ocorrenciaLigacaoAguaCriterio <= 0) {
					return null;
				}
			}

			if (existeCriterio[1] != null && (Integer) existeCriterio[1] != 0) {
				Integer ocorrenciaLigacaoEsgotoCriterio = this.repositorioSpcSerasa.verificaLigacaoEsgotoImovelNegativacaoCriterio(
						criterio.getId(), (Integer) dadosImovel[3]);

				if (ocorrenciaLigacaoEsgotoCriterio == null || ocorrenciaLigacaoEsgotoCriterio <= 0) {
					return null;
				}
			}

			if (existeCriterio[2] != null && (Integer) existeCriterio[2] != 0) {
				Integer ocorrenciaSubCatImovelCriterio = this.repositorioSpcSerasa.verificaSubCategoriaImovelNegativacaoCriterio(
						idImovel, criterio.getId());

				if (ocorrenciaSubCatImovelCriterio == null || ocorrenciaSubCatImovelCriterio <= 0) {
					return null;
				}
			}

			if (existeCriterio[3] != null && (Integer) existeCriterio[3] != 0) {
				Integer ocorrenciaPerfilImovelCriterio = this.repositorioSpcSerasa.verificaPerfilImovelNegativacaoCriterio(
						criterio.getId(), (Integer) dadosImovel[1]);

				if (ocorrenciaPerfilImovelCriterio == null || ocorrenciaPerfilImovelCriterio <= 0) {
					return null;
				}
			}

			if (existeCriterio[4] != null && (Integer) existeCriterio[4] != 0) {
				Integer ocorrenciaClienteUsuarioNegativacaoCriterio = this.repositorioSpcSerasa.verificaTipoClienteNegativacaoCriterio(
						idImovel, criterio.getId());

				if (ocorrenciaClienteUsuarioNegativacaoCriterio != null && ocorrenciaClienteUsuarioNegativacaoCriterio <= 0) {
					return null;
				}
			}

			if (existeCriterio[5] != null && (Integer) existeCriterio[5] != 0) {
				Integer ocorrenciaCobrancaSituacaoEspecialNegativacaoCriterio = this.repositorioSpcSerasa
						.verificaCobrancaSituacaoEspecialNegativacaoCriterio(idImovel, criterio.getId());

				if (ocorrenciaCobrancaSituacaoEspecialNegativacaoCriterio != null
						&& ocorrenciaCobrancaSituacaoEspecialNegativacaoCriterio <= 0) {
					return null;
				}
			}

			if (existeCriterio[6] != null && (Integer) existeCriterio[6] != 0) {
				Integer ocorrenciaCobrancaSituacaoNegativacaoCriterio = this.repositorioSpcSerasa
						.verificaCobrancaSituacaoNegativacaoCriterio(idImovel, criterio.getId());

				if (ocorrenciaCobrancaSituacaoNegativacaoCriterio != null
						&& ocorrenciaCobrancaSituacaoNegativacaoCriterio <= 0) {
					return null;
				}
			}

			// Obtendo os d�bitos do imovel
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = getControladorCobranca().obterDebitoImovelOuCliente(
					1, idImovel.toString(), null, null, criterio.getAnoMesReferenciaContaInicial().toString(),
					criterio.getAnoMesReferenciaContaFinal().toString(), criterio.getDataVencimentoDebitoInicial(),
					criterio.getDataVencimentoDebitoFinal(), 1, (int) criterio.getIndicadorNegativacaoContaRevisao(),
					2, 2, 2, (int) criterio.getIndicadorNegativacaoGuiaPagamento(), 1, null);

			// Cole��o de Contas
			Collection<ContaValoresHelper> colecaoContasValores = colecaoDebitoImovel.getColecaoContasValores();
			this.retirarContaPagaOuParceladaEEntradaParcelamento(colecaoContasValores);

			Iterator itColecaoContasValores = null;

			// Cole��o de Guias de Pagamento
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();
			this.retirarGuiaPagamentoDeEntradaParcelamento(colecaoGuiasPagamentoValores);
			
			Iterator itColecaoGuiasPagamentoValores = null;

			if (colecaoContasValores == null) {
				colecaoContasValores = new ArrayList();
			}

			if (colecaoGuiasPagamentoValores == null) {
				colecaoGuiasPagamentoValores = new ArrayList();
			}

			if ((colecaoContasValores.isEmpty() && colecaoGuiasPagamentoValores.isEmpty())) {
				return null;
			}

			BigDecimal valorTotalConta = new BigDecimal(0);
			BigDecimal valorTotalGuiaPagamento = new BigDecimal(0);
			BigDecimal valorTotal = new BigDecimal(0);
			Integer quantidadeTotalItensDebito = 0;
			Collection colecaoContasIds = null;

			if (colecaoContasValores != null) {
				colecaoContasIds = new ArrayList();
				itColecaoContasValores = colecaoContasValores.iterator();
				while (itColecaoContasValores.hasNext()) {
					ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();
					valorTotalConta = valorTotalConta.add(contaValores.getValorTotalConta());
					colecaoContasIds.add(contaValores.getConta().getId());
					quantidadeTotalItensDebito += 1;
				}
			}

			if (colecaoGuiasPagamentoValores != null) {
				itColecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores.iterator();
				while (itColecaoGuiasPagamentoValores.hasNext()) {
					GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores.next();
					valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValores.getGuiaPagamento().getValorDebito());
					quantidadeTotalItensDebito += 1;
				}
			}

			valorTotal = valorTotal.add(valorTotalConta.add(valorTotalGuiaPagamento));

			if (valorTotal.floatValue() < criterio.getValorMinimoDebito().floatValue()
					|| valorTotal.floatValue() > criterio.getValorMaximoDebito().floatValue()) {
				return null;
			}

			if (quantidadeTotalItensDebito < criterio.getQuantidadeMinimaContas()
					|| quantidadeTotalItensDebito > criterio.getQuantidadeMaximaContas()) {
				return null;
			}

			Parcelamento parcelamento = null;
			Integer indicadorImovelParcelamento = 0;
			Integer indicadorRecebimentoCarta = 0;
			Integer numeroDiasAtaso = 0;
			boolean contaParcelada = false;
			
			itColecaoContasValores = colecaoContasValores.iterator();
			while (itColecaoContasValores.hasNext()) {
				ContaValoresHelper contaValores = (ContaValoresHelper) itColecaoContasValores.next();

				Integer imovelParcelamento = this.repositorioSpcSerasa.verificaDebitoCobradoConta(contaValores.getConta().getId());

				if (imovelParcelamento != null && imovelParcelamento > 0) {
					contaParcelada = true;
					break;
				}
			}

			if (criterio.getIndicadorParcelamentoAtraso() == 1) {
				if (contaParcelada) {
					parcelamento = (Parcelamento) Util.retonarObjetoDeColecao(this.repositorioSpcSerasa.verificaImovelParcelamento(idImovel));
					if (parcelamento != null) {
						indicadorImovelParcelamento = 1;
					}
				}

				if (indicadorImovelParcelamento == 0) {
					return null;
				}

				if (criterio.getNumeroDiasParcelamentoAtraso() != null) {
					if (parcelamento != null) {
						numeroDiasAtaso = Util.obterQuantidadeDiasEntreDuasDatas(parcelamento.getParcelamento(), new Date());
					}
				}

				if (numeroDiasAtaso < criterio.getNumeroDiasParcelamentoAtraso()) {
					return null;
				}
			}

			if (criterio.getIndicadorNegativacaoRecebimentoCartaParcelamento() == 1) {
				indicadorRecebimentoCarta = this.repositorioSpcSerasa.verificaCartaAvisoParcelamento(
						idImovel, criterio.getNumeroDiasAtrasoRecebimentoCartaParcelamento());
				
				if (indicadorRecebimentoCarta == null) {
					indicadorRecebimentoCarta = 0;
				}
				
				if (indicadorRecebimentoCarta == 0) {
					return null;
				}
			}

			Collection<Object[]> colecaoRetorno = null;
			
			if (indicadorNegativacaoPorPeriodo) {
				ObterDocumentosNegativacaoPorPeriodoHelper helper = new ObterDocumentosNegativacaoPorPeriodoHelper(
						idImovel, colecaoContasValores, colecaoGuiasPagamentoValores, contaParcelada, criterio,
						categoriaPrincipalImovel, comando, colecaoContasIds);

				colecaoRetorno = this.obterDocumentosNegativacaoPorPeriodo(helper);

				if (colecaoRetorno == null) {
					return null;
				}

			} else {
				Cliente clienteDocumentoNegativacao = null;

				if (parcelamento != null) {
					clienteDocumentoNegativacao = this.obterDocumentoNegativacao(idImovel, criterio.getId(), parcelamento.getCliente());
				} else {
					clienteDocumentoNegativacao = this.obterDocumentoNegativacao(idImovel, criterio.getId(), null);
				}

				if (clienteDocumentoNegativacao == null) {
					return null;
				}

				if (comando.getIndicadorContaNomeCliente() != null
						&& comando.getIndicadorContaNomeCliente().equals(ConstantesSistema.SIM)
						&& colecaoContasIds != null && !colecaoContasIds.isEmpty()) {

					boolean existeClienteConta = getControladorFaturamento().verificarSeExisteClienteConta(
							clienteDocumentoNegativacao.getId(), colecaoContasIds);

					if (!existeClienteConta) {
						return null;
					}
				}

				if (clienteDocumentoNegativacao.getIndicadorPermiteNegativacao() != null
						&& clienteDocumentoNegativacao.getIndicadorPermiteNegativacao().equals(ConstantesSistema.NAO)) {
					return null;
				}
				
				if (!getControladorCliente().existeEnderecoParaCliente(clienteDocumentoNegativacao.getId())) {
					return null;
				}

				colecaoRetorno = new ArrayList<Object[]>();
				Object[] object = new Object[5];
				object[0] = quantidadeTotalItensDebito;
				object[1] = valorTotal;
				object[2] = clienteDocumentoNegativacao;
				object[3] = colecaoDebitoImovel;
				colecaoRetorno.add(object);
			}

			return colecaoRetorno;

		} catch (Exception e) {

			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	private Collection<Object[]> obterDocumentosNegativacaoPorPeriodo(
			ObterDocumentosNegativacaoPorPeriodoHelper helper) throws ControladorException {

		try {
			Map<Integer, Object[]> mapDocumentosNegativacaoPorPeriodo = new HashMap();
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			List negativacaoCriterioCpfTipos = this.repositorioSpcSerasa.obtemTitularidadesDocumentos(helper.getCriterio().getId());

			Imovel imovel = new Imovel();
			Integer idImovel = helper.getIdImovel();
			imovel.setId(idImovel);
			Integer qtdEconomiasImovel = this.getControladorImovel().obterQuantidadeEconomias(imovel);

			if (helper.getColecaoContasValores() != null) {
				Iterator iteratorColecaoContasValores = helper.getColecaoContasValores().iterator();
				while (iteratorColecaoContasValores.hasNext()) {
					ContaValoresHelper contaValores = (ContaValoresHelper) iteratorColecaoContasValores.next();
					Integer anoMesReferenciaDebito = contaValores.getConta().getReferencia();

					Integer idCliente = null;
					Integer imovelParcelamento = null;
					
					if (helper.isContaParcelada()) {
						imovelParcelamento = this.repositorioSpcSerasa.verificaDebitoCobradoConta(contaValores.getConta().getId());
					}

					if (imovelParcelamento != null) {
						Parcelamento parcelamento = (Parcelamento) Util.retonarObjetoDeColecao(
								repositorioSpcSerasa.verificaImovelParcelamento(idImovel));
						
						if (parcelamento != null) {
							Cliente clienteParcelamento = parcelamento.getCliente();
							idCliente = this.obterDocumentoNegativacaoConta(idImovel, negativacaoCriterioCpfTipos, clienteParcelamento,
									contaValores.getConta().getId(), helper.getCategoriaPrincipalDoImovel(), qtdEconomiasImovel);
						}
					} else {
						idCliente = this.obterDocumentoNegativacaoConta(idImovel, negativacaoCriterioCpfTipos, null,
								contaValores.getConta().getId(), helper.getCategoriaPrincipalDoImovel(), qtdEconomiasImovel);
					}

					if (idCliente != null) {
						boolean existeNegativacaoImovelECliente = repositorioSpcSerasa.verificarExistenciaNegativacaoImovelECliente(
								idImovel, idCliente);

						if (!existeNegativacaoImovelECliente && getControladorCliente().existeEnderecoParaCliente(idCliente)) {
							if (mapDocumentosNegativacaoPorPeriodo.containsKey(idCliente)) {
								
								Object[] object = mapDocumentosNegativacaoPorPeriodo.get(idCliente);
								object[0] = (Integer) object[0] + 1;
								object[1] = ((BigDecimal) object[1]).add(contaValores.getValorTotalConta());
								ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = (ObterDebitoImovelOuClienteHelper) object[3];
								Collection colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();
								colecaoContaValores.add(contaValores);

								if (colecaoDebitoImovel.getAnoMesReferenciaInicioDebito().compareTo(anoMesReferenciaDebito) == 1) {
									colecaoDebitoImovel.setAnoMesReferenciaInicioDebito(anoMesReferenciaDebito);
								}

								if (colecaoDebitoImovel.getAnoMesReferenciaFinalDebito().compareTo(anoMesReferenciaDebito) == -1) {
									colecaoDebitoImovel.setAnoMesReferenciaInicioDebito(anoMesReferenciaDebito);
								}
							} else {
								Object[] object = new Object[5];
								object[0] = 1;
								object[1] = contaValores.getValorTotalConta();
								Cliente clienteNegativacao = getControladorCliente().pesquisarDadosClienteParaNegativacao(
										idCliente, sistemaParametro.getCnpjEmpresa());
								object[2] = clienteNegativacao;
								ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = new ObterDebitoImovelOuClienteHelper();
								Collection colecaoContaValores = new ArrayList<ContaValoresHelper>();
								colecaoContaValores.add(contaValores);
								colecaoDebitoImovel.setColecaoContasValores(colecaoContaValores);
								colecaoDebitoImovel.setAnoMesReferenciaInicioDebito(anoMesReferenciaDebito);
								colecaoDebitoImovel.setAnoMesReferenciaFinalDebito(anoMesReferenciaDebito);
								object[3] = colecaoDebitoImovel;

								if (clienteNegativacao != null
										&& (clienteNegativacao.getIndicadorPermiteNegativacao() == null
												|| clienteNegativacao.getIndicadorPermiteNegativacao().equals(ConstantesSistema.SIM))) {
									mapDocumentosNegativacaoPorPeriodo.put(idCliente, object);
								}
							}
						}
					}
				}
			}

			if (helper.getColecaoGuiasPagamentoValores() != null) {
				Iterator itColecaoGuiasPagamentoValores = helper.getColecaoGuiasPagamentoValores().iterator();
				while (itColecaoGuiasPagamentoValores.hasNext()) {
					GuiaPagamentoValoresHelper guiaPagamentoValores = (GuiaPagamentoValoresHelper) itColecaoGuiasPagamentoValores.next();
					Integer idCliente = obterDocumentoNegativacaoGuiaPagamento(idImovel, negativacaoCriterioCpfTipos, null,
							guiaPagamentoValores.getGuiaPagamento().getId(), helper.getCategoriaPrincipalDoImovel(), qtdEconomiasImovel);

					if (idCliente != null) {
						boolean existeNegativacaoImovelECliente = repositorioSpcSerasa.verificarExistenciaNegativacaoImovelECliente(idImovel, idCliente);

						if (!existeNegativacaoImovelECliente && getControladorCliente().existeEnderecoParaCliente(idCliente)) {

							if (mapDocumentosNegativacaoPorPeriodo.containsKey(idCliente)) {
								Object[] object = mapDocumentosNegativacaoPorPeriodo.get(idCliente);
								object[0] = (Integer) object[0] + 1;
								object[1] = ((BigDecimal) object[1]).add(guiaPagamentoValores.getGuiaPagamento().getValorDebito());
								ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = (ObterDebitoImovelOuClienteHelper) object[3];
								Collection colecaoGuiaPagamentoValoresHelper = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();
								
								if (colecaoGuiaPagamentoValoresHelper != null) {
									colecaoGuiaPagamentoValoresHelper.add(guiaPagamentoValores);
								} else {
									colecaoGuiaPagamentoValoresHelper = new ArrayList<GuiaPagamentoValoresHelper>();
									colecaoGuiaPagamentoValoresHelper.add(guiaPagamentoValores);
								}
							} else {
								Object[] object = new Object[5];
								object[0] = 1;
								object[1] = guiaPagamentoValores.getGuiaPagamento().getValorDebito();
								Cliente clienteNegativacao = getControladorCliente().pesquisarDadosClienteParaNegativacao(
										idCliente, sistemaParametro.getCnpjEmpresa());
								object[2] = clienteNegativacao;
								ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = new ObterDebitoImovelOuClienteHelper();
								new ObterDebitoImovelOuClienteHelper();
								Collection colecaoGuiaPagamentoValoresHelper = new ArrayList<GuiaPagamentoValoresHelper>();
								colecaoGuiaPagamentoValoresHelper.add(guiaPagamentoValores);
								colecaoDebitoImovel.setColecaoGuiasPagamentoValores(colecaoGuiaPagamentoValoresHelper);
								object[3] = colecaoDebitoImovel;

								boolean existeClienteConta = true;
								if (helper.getComando().getIndicadorContaNomeCliente() != null
										&& helper.getComando().getIndicadorContaNomeCliente().equals(ConstantesSistema.SIM)
										&& helper.getColecaoContasIds() != null
										&& !helper.getColecaoContasIds().isEmpty()) {

									existeClienteConta = getControladorFaturamento().verificarSeExisteClienteConta(
											clienteNegativacao.getId(), helper.getColecaoContasIds());
								}

								if (clienteNegativacao != null && existeClienteConta
										&& (clienteNegativacao.getIndicadorPermiteNegativacao() == null
												|| clienteNegativacao.getIndicadorPermiteNegativacao().equals(ConstantesSistema.SIM))) {
									mapDocumentosNegativacaoPorPeriodo.put(idCliente, object);
								}
							}
						}
					}
				}
			}

			Collection<Object[]> colecaoRetorno = null;
			if (!mapDocumentosNegativacaoPorPeriodo.isEmpty()) {
				colecaoRetorno = mapDocumentosNegativacaoPorPeriodo.values();
			}

			return colecaoRetorno;

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	private Integer obterDocumentoNegativacaoConta(Integer idImovel,
			List negativacaoCriterioCpfTipos, Cliente clienteParcelamento,
			Integer idConta, Categoria categoriaPrincipalDoImovel,
			Integer qtdEconomiasDoImovel) throws ErroRepositorioException, ControladorException {

		Integer retorno = null;

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		Collection<ClienteConta> clientesConta = getControladorFaturamento().pesquisarClienteContaECliente(
				idConta, sistemaParametro.getCnpjEmpresa());

		if (clientesConta == null || clientesConta.isEmpty()) {
			return null;
		}

		if (clienteParcelamento != null && !clienteParcelamento.equals("")) {
			ClienteConta clienteContaParc = new ClienteConta();
			clienteContaParc.setCliente(clienteParcelamento);
			ClienteRelacaoTipo relacaoCliente = new ClienteRelacaoTipo();
			relacaoCliente.setId(4);
			clienteContaParc.setClienteRelacaoTipo(relacaoCliente);
			clientesConta.add(clienteContaParc);
		}

		Collection<ClienteConta> clientesContaCPFCNPJ = new ArrayList();
		Iterator ItClientesConta = clientesConta.iterator();
		while (ItClientesConta.hasNext()) {
			ClienteConta clienteConta = (ClienteConta) ItClientesConta.next();
			String clienteCNPJ = clienteConta.getCliente().getCnpj();
			String clienteCPF = clienteConta.getCliente().getCpf();
			if ((clienteCNPJ != null && !clienteCNPJ.trim().equals("") && Util.validarStringNumerica(clienteCNPJ))
					|| (clienteCPF != null && !clienteCPF.trim().equals("") && Util.validarStringNumerica(clienteCPF))) {
				clientesContaCPFCNPJ.add(clienteConta);
			}
		}

		if (clientesContaCPFCNPJ == null || clientesContaCPFCNPJ.isEmpty()) {
			return null;
		}

		if (negativacaoCriterioCpfTipos.size() == 1) {

			NegativacaoCriterioCpfTipo cpfTipo = (NegativacaoCriterioCpfTipo) Util.retonarObjetoDeColecao(negativacaoCriterioCpfTipos);

			short clienteTipo = 4;
			if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)) {
				clienteTipo = ClienteRelacaoTipo.USUARIO;
			} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)) {
				clienteTipo = ClienteRelacaoTipo.PROPRIETARIO;
			} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)) {
				clienteTipo = ClienteRelacaoTipo.RESPONSAVEL;
			}

			Iterator clientesContaIterator = clientesContaCPFCNPJ.iterator();
			while (clientesContaIterator.hasNext()) {
				ClienteConta clienteConta = (ClienteConta) clientesContaIterator.next();
				if (clienteConta.getClienteRelacaoTipo().getId().shortValue() == clienteTipo) {

					boolean achouCnpjCpf = false;

					String clienteCNPJ = clienteConta.getCliente().getCnpj();
					
					if (clienteCNPJ != null && !clienteCNPJ.trim().equals("") && Util.validarStringNumerica(clienteCNPJ)) {
						achouCnpjCpf = true;
					} else {
						if (qtdEconomiasDoImovel <= 5 && categoriaPrincipalDoImovel.getId() == Categoria.RESIDENCIAL_INT) {
							achouCnpjCpf = true;
						}
					}
					
					if (achouCnpjCpf) {
						return clienteConta.getCliente().getId();
					}
				}
			}
		} else {
			Iterator iteratorNegativacaoCriterioCpfTipos = negativacaoCriterioCpfTipos.iterator();
			while (iteratorNegativacaoCriterioCpfTipos.hasNext()) {
				NegativacaoCriterioCpfTipo cpfTipo = (NegativacaoCriterioCpfTipo) iteratorNegativacaoCriterioCpfTipos.next();

				if (cpfTipo.getNumeroOrdemSelecao() != 0) {
					
					Short clinteTipo = 0;
					if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)) {
						clinteTipo = ClienteRelacaoTipo.USUARIO;
					} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)) {
						clinteTipo = ClienteRelacaoTipo.PROPRIETARIO;
					} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)) {
						clinteTipo = ClienteRelacaoTipo.RESPONSAVEL;
					} else {
						clinteTipo = 4;
					}

					Iterator clientesContaIterator = clientesContaCPFCNPJ.iterator();
					while (clientesContaIterator.hasNext()) {
						ClienteConta clienteConta = (ClienteConta) clientesContaIterator.next();
						
						if (clienteConta.getClienteRelacaoTipo().getId().shortValue() == (clinteTipo.shortValue())) {

							boolean achouCnpjCpf = false;

							String clienteCNPJ = clienteConta.getCliente().getCnpj();
							if (clienteCNPJ != null && !clienteCNPJ.trim().equals("") && Util.validarStringNumerica(clienteCNPJ)) {
								achouCnpjCpf = true;
							} else {
								if (qtdEconomiasDoImovel <= 5 && categoriaPrincipalDoImovel.getId() == Categoria.RESIDENCIAL_INT) {
									achouCnpjCpf = true;
								}
							}
							if (achouCnpjCpf) {
								return clienteConta.getCliente().getId();
							}
						}
					}
				}
			}
		}

		return retorno;
	}
	
	private Integer obterDocumentoNegativacaoGuiaPagamento(Integer idImovel,
			List negativacaoCriterioCpfTipos, Cliente clienteParcelamento,Integer idGuia,
			Categoria categoriaPrincipalDoImovel,Integer qtdEconomiasDoImovel)
			throws ErroRepositorioException, ControladorException {
		Integer retorno = null;

		Collection<ClienteGuiaPagamento> clientesGuiaPagamento = getControladorArrecadacao().pesquisarClienteGuiaPagamentoECliente(idGuia);

		if (clientesGuiaPagamento == null || clientesGuiaPagamento.isEmpty()) {
			return null;
		}

		Collection<ClienteGuiaPagamento> clientesGuiaPagamentoCPFCNPJ = new ArrayList();
		Iterator ItClientesConta = clientesGuiaPagamento.iterator();
		while (ItClientesConta.hasNext()) {
			ClienteGuiaPagamento clienteGuiaPagamento = (ClienteGuiaPagamento) ItClientesConta.next();
			String clienteCNPJ = clienteGuiaPagamento.getCliente().getCnpj();
			String clienteCPF = clienteGuiaPagamento.getCliente().getCpf();
			if ((clienteCNPJ != null && !clienteCNPJ.trim().equals("") && Util.validarStringNumerica(clienteCNPJ))
					|| (clienteCPF != null && !clienteCPF.trim().equals("") && Util.validarStringNumerica(clienteCPF))) {
				clientesGuiaPagamentoCPFCNPJ.add(clienteGuiaPagamento);
			}
		}

		if (clientesGuiaPagamentoCPFCNPJ == null || clientesGuiaPagamentoCPFCNPJ.isEmpty()) {
			return null;
		}

		if (negativacaoCriterioCpfTipos.size() == 1) {
			NegativacaoCriterioCpfTipo cpfTipo = (NegativacaoCriterioCpfTipo) Util.retonarObjetoDeColecao(negativacaoCriterioCpfTipos);

			short clienteTipo = 4;
			if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)) {
				clienteTipo = ClienteRelacaoTipo.USUARIO;
			} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)) {
				clienteTipo = ClienteRelacaoTipo.PROPRIETARIO;
			} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)) {
				clienteTipo = ClienteRelacaoTipo.RESPONSAVEL;
			}

			Iterator clientesGuiaPagamentoIterator = clientesGuiaPagamentoCPFCNPJ.iterator();
			while (clientesGuiaPagamentoIterator.hasNext()) {
				ClienteGuiaPagamento clienteGuiaPagamento = (ClienteGuiaPagamento) clientesGuiaPagamentoIterator.next();
				if (clienteGuiaPagamento.getClienteRelacaoTipo().getId().shortValue() == clienteTipo) {

					boolean achouCnpjCpf = false;

					String clienteCNPJ = clienteGuiaPagamento.getCliente().getCnpj();
					if (clienteCNPJ != null && !clienteCNPJ.trim().equals("")
							&& Util.validarStringNumerica(clienteCNPJ)) {
						achouCnpjCpf = true;
					} else {
						if (qtdEconomiasDoImovel <= 5
								&& categoriaPrincipalDoImovel.getId() == Categoria.RESIDENCIAL_INT) {
							achouCnpjCpf = true;
						}
					}
					if (achouCnpjCpf) {
						return clienteGuiaPagamento.getCliente().getId();
					}
				}
			}
		} else {
			Iterator itNegCritCpfTp = negativacaoCriterioCpfTipos.iterator();
			while (itNegCritCpfTp.hasNext()) {
				NegativacaoCriterioCpfTipo cpfTipo = (NegativacaoCriterioCpfTipo) itNegCritCpfTp.next();

				if (cpfTipo.getNumeroOrdemSelecao() != 0) {
					Short clienteTipo = 0;
					if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_USUARIO)) {
						clienteTipo = ClienteRelacaoTipo.USUARIO;
					} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_PROPRIETARIO)) {
						clienteTipo = ClienteRelacaoTipo.PROPRIETARIO;
					} else if (cpfTipo.getCpfTipo().getId().equals(NegativacaoCriterioCpfTipo.CLIENTE_RESPONSAVEL)) {
						clienteTipo = ClienteRelacaoTipo.RESPONSAVEL;
					} else {
						clienteTipo = 4;
					}

					Iterator clientesGuiaPagamentoIterator = clientesGuiaPagamentoCPFCNPJ.iterator();
					while (clientesGuiaPagamentoIterator.hasNext()) {
						ClienteGuiaPagamento clienteGuiaPagamento = (ClienteGuiaPagamento) clientesGuiaPagamentoIterator.next();
						
						if (clienteGuiaPagamento.getClienteRelacaoTipo().getId().shortValue() == (clienteTipo.shortValue())) {
							boolean achouCnpjCpf = false;

							String clienteCNPJ = clienteGuiaPagamento.getCliente().getCnpj();
							if (clienteCNPJ != null && !clienteCNPJ.trim().equals("") && Util.validarStringNumerica(clienteCNPJ)) {
								achouCnpjCpf = true;
							} else {
								if (qtdEconomiasDoImovel <= 5 && categoriaPrincipalDoImovel.getId() == Categoria.RESIDENCIAL_INT) {
									achouCnpjCpf = true;
								}
							}
							if (achouCnpjCpf) {
								return clienteGuiaPagamento.getCliente().getId();
							}
						}
					}
				}
			}
		}
		return retorno;
	}
}
