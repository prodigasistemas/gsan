package gcom.cadastro.imovel;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocalHome;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.cliente.bean.ClienteImovelEconomiaHelper;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelClientesEspeciaisRelatorioHelper;
import gcom.cadastro.imovel.bean.ImovelEconomiaHelper;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.imovel.bean.InserirImovelHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.CobrancaSituacaoMotivo;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.FiltroCobrancaDocumentoItem;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacaoHistorico;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoHistorico;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.VencimentoAlternativo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrarCategoria;
import gcom.faturamento.debito.FiltroDebitoACobrarGeral;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.Rota;
import gcom.micromedicao.bean.RelatorioAnaliseConsumoHelper;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.relatorio.cadastro.GerarRelatorioImoveisDoacoesHelper;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.seguranca.ControladorPermissaoEspecialLocal;
import gcom.seguranca.ControladorPermissaoEspecialLocalHome;
import gcom.seguranca.acesso.Abrangencia;
import gcom.seguranca.acesso.ControladorAcessoLocal;
import gcom.seguranca.acesso.ControladorAcessoLocalHome;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.seguranca.transacao.ControladorTransacaoLocalHome;
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
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.transaction.SystemException;

import org.jboss.logging.Logger;

public class ControladorImovelSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	SessionContext sessionContext;

	private IRepositorioImovel repositorioImovel;
	private IRepositorioCategoria repositorioCategoria;
	private IRepositorioFaturamento repositorioFaturamento;
	private IRepositorioCobranca repositorioCobranca;
	
	private Logger logger = Logger.getLogger(ControladorImovelSEJB.class);

	public void atualizarImovelLigacaoAgua(Imovel imovel, Integer idLigacaoAguaSituacao) throws ControladorException {

		try {
			repositorioImovel.atualizarImovelLigacaoAgua(imovel,
					idLigacaoAguaSituacao);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public void atualizarImovelExecucaoOrdemServicoLigacaoAgua(Imovel imovel, LigacaoAguaSituacao situacaoAgua) throws ControladorException {

		try {
			repositorioImovel.atualizarImovelExecucaoOrdemServicoLigacaoAgua(imovel, situacaoAgua);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public void atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(Imovel imovel, LigacaoEsgotoSituacao situacaoEsgoto) throws ControladorException {

		try {
			repositorioImovel.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel, situacaoEsgoto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorMicromedicaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
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
	
	private ControladorPermissaoEspecialLocal getControladorPermissaoEspecial() {
		ControladorPermissaoEspecialLocalHome localHome = null;
		ControladorPermissaoEspecialLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorPermissaoEspecialLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_PERMISSAO_ESPECIAL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorAcessoLocal getControladorAcesso() {
		ControladorAcessoLocalHome localHome = null;
		ControladorAcessoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorAcessoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ACESSO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCobrancaLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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
			localHome = (ControladorFaturamentoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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

	public void ejbCreate() throws CreateException {
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioCategoria = RepositorioCategoriaHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
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

	public void inserirImovel(Imovel imovel) throws ControladorException {
		try {
			repositorioImovel.inserirImovel(imovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void informarImovelEconomias(Collection<ImovelEconomia> imoveisEconomias, Usuario usuarioLogado) throws ControladorException {
		Iterator<ImovelEconomia> imovelEconomiasIterator = imoveisEconomias.iterator();

		while (imovelEconomiasIterator.hasNext()) {
			ImovelEconomia imovelEconomia = (ImovelEconomia) imovelEconomiasIterator.next();

			Collection clienteImovelEconomias = imovelEconomia.getClienteImovelEconomias();
			Iterator clienteImovelEconomiaIterator = clienteImovelEconomias.iterator();

			Collection clientesImovelsAtualizadas = new HashSet();
			Collection clientesImovelsInseridas = new HashSet();
			Collection<ClienteImovelEconomia> clientesImoveisEconomiasHashSet = new HashSet();

			while (clienteImovelEconomiaIterator.hasNext()) {
				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator.next();
				clienteImovelEconomia.setImovelEconomia(imovelEconomia);
				
				if (clienteImovelEconomia.getId() != null && !clienteImovelEconomia.getId().equals("")) {
					clientesImovelsAtualizadas.add(clienteImovelEconomia);
				} else {
					clientesImovelsInseridas.add(clienteImovelEconomia);
				}
				if (clienteImovelEconomia != null && !clienteImovelEconomia.equals("")) {
					clientesImoveisEconomiasHashSet.add(clienteImovelEconomia);
				}
			}

			if (clientesImovelsAtualizadas != null && !clientesImovelsAtualizadas.isEmpty()) {
				this.getControladorCliente().atualizarClienteImovelEconomia(clientesImovelsAtualizadas);
			}

			imovelEconomia.setClienteImovelEconomias(null);

			Integer idImovelEconomia = null;

			Abrangencia abrangencia = new Abrangencia(usuarioLogado,imovelEconomia.getImovelSubcategoria().getComp_id().getImovel());

			if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.acesso.negado.abrangencia");
			} else {
				idImovelEconomia = (Integer) getControladorUtil().inserirOuAtualizar(imovelEconomia);
			}

			if (idImovelEconomia != null) {
				imovelEconomia.setId(idImovelEconomia);
			}

			Iterator clienteImovelEconomiaInserirIterator = clientesImovelsInseridas.iterator();

			while (clienteImovelEconomiaInserirIterator.hasNext()) {
				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaInserirIterator.next();

				clienteImovelEconomia.setImovelEconomia(imovelEconomia);
				if (clienteImovelEconomia.getDataFimRelacao() == null || clienteImovelEconomia.getDataFimRelacao().equals("")) {
					getControladorUtil().inserir(clienteImovelEconomia);
				}
			}

			imovelEconomia.setClienteImovelEconomias(new HashSet(clientesImoveisEconomiasHashSet));
		}
	}
	
	public Cep pesquisarCepImovel(Imovel imovel) throws ControladorException {
		try {
			return repositorioImovel.pesquisarCepImovel(imovel);
		} catch (ErroRepositorioException ex) {        
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}

	public int obterQuantidadeEconomias(Imovel imovel) throws ControladorException {
		Short quantidadeEconomias = null;

		try {
			Object objetoQuantidadeEconomias = repositorioImovel.pesquisarObterQuantidadeEconomias(imovel);
			quantidadeEconomias = (Short) objetoQuantidadeEconomias;
		} catch (ErroRepositorioException ex) {        
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return quantidadeEconomias.intValue();	
	}

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoImovelSubcategorias(Imovel imovel, Integer quantidadeMinimaEconomia) throws ControladorException {
		Collection retorno = null;
		Collection colecaoImovelSubCategoria = null;

		try {
			colecaoImovelSubCategoria = repositorioImovel.pesquisarImovelSubcategorias(imovel.getId());
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovelSubCategoria != null && !colecaoImovelSubCategoria.isEmpty()) {

			Iterator colecaoImovelSubCategoriaIterator = colecaoImovelSubCategoria.iterator();

			while (colecaoImovelSubCategoriaIterator.hasNext()) {
				ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) colecaoImovelSubCategoriaIterator.next();

				if (colecaoImovelSubCategoria.size() < quantidadeMinimaEconomia
						&& imovelSubcategoria.getQuantidadeEconomias() < quantidadeMinimaEconomia) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.quantidade_economia_por_subcategoria");
				}
			}

		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.pesquisa.nenhumresultado",null, "im�vel subcategoria");
		}
		retorno = colecaoImovelSubCategoria;

		return retorno;
	}

	@SuppressWarnings("rawtypes")
	public void removerImovelEconomia(ImovelEconomia imovelEconomia, Usuario usuarioLogado) throws ControladorException {

		if (!imovelEconomia.getImovelSubcategoria().getComp_id().getImovel()
				.getImovelPerfil().getId().equals(ImovelPerfil.TARIFA_SOCIAL)) {
			Abrangencia abrangencia = new Abrangencia(usuarioLogado, imovelEconomia.getImovelSubcategoria().getComp_id().getImovel());

			if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.acesso.negado.abrangencia");
			} else {

				FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
				filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA_ID,
								imovelEconomia.getId()));

				Collection colecaoTarifaSocial = getControladorUtil().pesquisar(filtroTarifaSocialDadoEconomia,
								TarifaSocialDadoEconomia.class.getName());

				if (colecaoTarifaSocial != null && !colecaoTarifaSocial.isEmpty()) {

					Iterator colecaoTarifaSocialIterator = colecaoTarifaSocial.iterator();

					while (colecaoTarifaSocialIterator.hasNext()) {
						TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifaSocialIterator.next();
						getControladorUtil().remover(tarifaSocialDadoEconomia);
					}
				}

				getControladorUtil().removerUm(
						Integer.parseInt(imovelEconomia.getId().toString()),
						ImovelEconomia.class.getName(), null, null);
			}
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.nao_exclusao_dados_economia");
		}
	}

	/**
	 * [UC0011] Inserir Im�vel
	 *
	 * @param inserirImovelHelper
	 * @return Integer
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	public Integer inserirImovelRetorno(InserirImovelHelper inserirImovelHelper)
			throws ControladorException {

		Integer id = null;
		
		Collection clientesImoveis = inserirImovelHelper.getClientes();
		
		Imovel imovel = inserirImovelHelper.getImovel();
		Localidade localidade = imovel.getLocalidade();
		SetorComercial setorComercial = imovel.getSetorComercial();
		Quadra quadra = imovel.getQuadra();
		QuadraFace quadraFace = imovel.getQuadraFace();
		LigacaoAguaSituacao ligacaoAguaSituacao = imovel.getLigacaoAguaSituacao();
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = imovel.getLigacaoEsgotoSituacao();
		Usuario usuario = inserirImovelHelper.getUsuario();
		
		/*
		 * Integra��o com o conceito de face da quadra
		 * 
		 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da 
		 * tabela SISTEMA_PARAMETROS); os campos de INDICADOR_REDE_AGUA, INDICADOR_REDE_ESGOTO
		 * DISTRITO_OPERACIONAL e BACIA ser�o obtidos a partir da face.
		 */
		IntegracaoQuadraFaceHelper integracaoQuadraFace = null;
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
    	if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
    		
    		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
    		filtroQuadraFace.adicionarParametro(new ParametroSimples(FiltroQuadraFace.ID, quadraFace.getId()));
    		
    		Collection colecaoQuadraFace = getControladorUtil().pesquisar(filtroQuadraFace, QuadraFace.class.getName());
    		
    		if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()) {
    			
    			QuadraFace quadraFaceNaBase = ((QuadraFace) ((List) colecaoQuadraFace).get(0));
    			
    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
    			
    			integracaoQuadraFace.setIndicadorRedeAgua(quadraFaceNaBase.getIndicadorRedeAgua());
    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraFaceNaBase.getIndicadorRedeEsgoto());
    		}
    	}
    	else{
    		
    		FiltroQuadra filtroQuadra = new FiltroQuadra();
    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, localidade.getId()));
    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, quadra.getId()));
    		
    		Collection colecaoQuadra = getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
    		
    		if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
    			
    			Quadra quadraNaBase = ((Quadra) ((List) colecaoQuadra).get(0));
    			
    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
    			
    			integracaoQuadraFace.setIndicadorRedeAgua(quadraNaBase.getIndicadorRedeAgua());
    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraNaBase.getIndicadorRedeEsgoto());
    		}
    	}
		
    	
		/*
		 * VERIFICANDO A SITUA��O DA LIGA��O DA �GUA DE ACORDO COM A QUADRA OU FACE DA QUADRA
		 * QUE EST� SENDO CADASTRADA PARA O IM�VEL.
		 */
		if (integracaoQuadraFace != null) {

			// SITUA��O DE LIGA��O DE AGUA
			if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
				integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.SEM_REDE)) {
				
				if (!(ligacaoAguaSituacao.getIndicadorExistenciaRede().toString()
					.equals(LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_NAO.toString()) && 
					(ligacaoAguaSituacao.getIndicadorExistenciaLigacao().toString()
					.equals(LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO.toString())))) {
					
					throw new ControladorException("atencao.imovel.ligacao_agua.incompativel");
				}
			}
			
			if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
				integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.COM_REDE)) {
				
				if (!(ligacaoAguaSituacao.getIndicadorExistenciaRede().toString()
					.equals(LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_SIM.toString()) && 
					(ligacaoAguaSituacao.getIndicadorExistenciaLigacao().toString()
					.equals(LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO.toString())))) {
					
					throw new ControladorException("atencao.imovel.ligacao_agua.incompativel");
				}
			}
			
			if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
				integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.REDE_PARCIAL)) {

				if (!(ligacaoAguaSituacao.getIndicadorExistenciaLigacao().toString()
					.equals(LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO.toString()))) {
					
					throw new ControladorException("atencao.imovel.ligacao_agua.incompativel");
				}
			}

			// SITUA��O DE LIGA��O ESGOTO
			if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
				integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.SEM_REDE)) {
				
				if (!(ligacaoEsgotoSituacao.getIndicadorExistenciaRede().toString()
					.equals(LigacaoEsgotoSituacao.INDICADOR_EXISTENCIA_REDE_NAO.toString()) && 
					(ligacaoEsgotoSituacao.getIndicadorExistenciaLigacao().toString()
					.equals(LigacaoEsgotoSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO.toString())))) {
					
					throw new ControladorException("atencao.imovel.ligacao_esgoto.incompativel");
				}
			}
			
			if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
				integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.COM_REDE)) {
				
				if (!(ligacaoEsgotoSituacao.getIndicadorExistenciaRede().toString()
					.equals(LigacaoEsgotoSituacao.INDICADOR_EXISTENCIA_REDE_SIM.toString()) && 
					(ligacaoEsgotoSituacao.getIndicadorExistenciaLigacao().toString()
					.equals(LigacaoEsgotoSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO.toString())))) {
					
					throw new ControladorException("atencao.imovel.ligacao_esgoto.incompativel");
				}

			}
			
			if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
				integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.REDE_PARCIAL)) {

				if (!(ligacaoEsgotoSituacao.getIndicadorExistenciaLigacao().toString()
					.equals(LigacaoEsgotoSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO.toString()))) {
					
					throw new ControladorException("atencao.imovel.ligacao_esgoto.incompativel");
				}
			}

		}

		/**
		 * alterado por pedro alexandre dia 17/11/2006 altera��o feita para
		 * acoplar o controle de abrang�ncia de usu�rio
		 */
		// ------------ CONTROLE DE ABRANGENCIA ----------------
		Abrangencia abrangencia = new Abrangencia(inserirImovelHelper.getUsuario(), 
		inserirImovelHelper.getImovel().getLocalidade());
		// ------------ CONTROLE DE ABRANGENCIA ----------------
		
		
//		 ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		    Operacao.OPERACAO_IMOVEL_INSERIR,imovel.getId(),
		    imovel.getId(),
		    new UsuarioAcaoUsuarioHelper(usuario,
		    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(imovel);

		// ------------ REGISTRAR TRANSA��O ----------------

/*		
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_IMOVEL_INSERIR, new UsuarioAcaoUsuarioHelper(
						usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_IMOVEL_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		
		imovel.setOperacaoEfetuada(operacaoEfetuada);
		imovel.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(imovel);
		// ------------ REGISTRAR TRANSA��O ----------------
*/		
		// [FS0022] Auto-incremento do Numero do Lote
		if (quadra.getIndicadorIncrementoLote() != null && 
			quadra.getIndicadorIncrementoLote().equals(new Short("1"))) {
			
			Integer numeroMaximo = pesquisarMaiorNumeroLoteDaQuadra(quadra.getId()) +1;
			imovel.setLote(new Short(numeroMaximo.toString()));
			imovel.setSubLote(new Short("0"));
		}

		if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.acesso.negado.abrangencia");
		} else {
			id = (Integer) getControladorUtil().inserir(imovel);
		}
		/** fim altera��o de controle de abrang�ncia ************************* */

		Imovel imovelNovo = new Imovel();

		imovelNovo.setId(id);

		Iterator iteratorCliente = clientesImoveis.iterator();
		ClienteImovel clienteImovel = null;

		while (iteratorCliente.hasNext()) {
			clienteImovel = null;
			clienteImovel = (ClienteImovel) iteratorCliente.next();
			//Alterado por S�vio Luiz data: 27/03/2009 sobre a CRC 1185
			//Colocado s� para descobrir como os usu�rios est�o cadastrando um �nico cliente im�vel 
			// com o indicador do nome da conta igual a 2 - Tempor�rio.
			if(clientesImoveis.size() == 1){
				if(clienteImovel.getIndicadorNomeConta().equals(new Short("2"))){
					sessionContext.setRollbackOnly();
					throw new ControladorException("Nome da Conta na Aba de Cliente � obrigat�rio.");
				}
			}
			
			//registrar transa��o clienteImovel
			registradorOperacao.registrarOperacao(clienteImovel);
			
			clienteImovel.setImovel(imovelNovo);
			clienteImovel.setUltimaAlteracao(new Date());
			this.getControladorCliente().inserirClienteImovel(clienteImovel);
		}

		// Obtem os valores que vem na cole��o de
		// subCategorias(economia)para cadastrar em Imovel_Subcategoria
		Collection subcategorias = inserirImovelHelper.getSubcategorias();
		Iterator iteratorSubCategoria = subcategorias.iterator();
		ImovelSubcategoria imovelSubcategoria = null;

		while (iteratorSubCategoria.hasNext()) {
			imovelSubcategoria = null;
			imovelSubcategoria = (ImovelSubcategoria) iteratorSubCategoria
					.next();
			
			//registrar transa��o imovelSubcategoria
			registradorOperacao.registrarOperacao(imovelSubcategoria);
			
			imovelSubcategoria.getComp_id().setImovel(imovelNovo);
			this.inserirImovelSubCategoria(imovelSubcategoria);
		}
		
		//Ramos atividades
		Collection ramosAtividades = inserirImovelHelper.getRamosAtividades();
		Iterator iteratorRamosAtividades = ramosAtividades.iterator();
		ImovelRamoAtividade imovelRamoAtividade = null;
		
		while (iteratorRamosAtividades.hasNext()) {
			
			 imovelRamoAtividade = (ImovelRamoAtividade) iteratorRamosAtividades.next();
			 
			 registradorOperacao.registrarOperacao(imovelRamoAtividade);
			 imovelRamoAtividade.getComp_id().setImovel(imovelNovo);
			 this.inserirImovelRamoAtividade(imovelRamoAtividade);			
		}
		
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
		inserirImovelHelper.getImovel().getLigacaoEsgotoSituacao().getId()));

		Collection colecaoLigacaoEsgotoSituacao = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao, 
		LigacaoEsgotoSituacao.class.getName());
		
		LigacaoEsgotoSituacao ligacaoEsgoto = (LigacaoEsgotoSituacao) 
		Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
		
		if (ligacaoEsgoto.getIndicadorFaturamentoSituacao().equals(LigacaoEsgotoSituacao.FATURAMENTO_ATIVO)) {
			inserirLigacaoEsgotoImovel(inserirImovelHelper);
		}
		
		return id;
	}

	
	/**
	 * Insere a Liga��o de Esgoto quando o im�vel � inserido com o indicador de faturamento igual a sim
	 *
	 * @author Rafael Corr�a, Raphael Rossiter
	 * @date 21/05/2008, 19/08/2008
	 *
	 * @param InserirImovelHelper
	 * @throws ControladorException
	 */
	private void inserirLigacaoEsgotoImovel(InserirImovelHelper inserirImovelHelper) throws ControladorException {
		
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		ligacaoEsgoto.setId(inserirImovelHelper.getImovel().getId());
		ligacaoEsgoto.setDataLigacao(new Date());
		
		if (inserirImovelHelper.getImovel().getFonteAbastecimento() != null) {
			
			FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, 
			inserirImovelHelper.getImovel().getFonteAbastecimento().getId()));
			
			Collection colecaoFonteAbastecimento = getControladorUtil().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
			
			FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(colecaoFonteAbastecimento);
			
			if (fonteAbastecimento.getIndicadorCalcularVolumeFixo().equals(FonteAbastecimento.INDICADOR_CALCULAR_VOLUME_FIXO)) {
			
				Integer consumoMinimo = null;
				
				if (inserirImovelHelper.getImovel().getAreaConstruida() != null) {
					consumoMinimo = (inserirImovelHelper.getImovel().getAreaConstruida().divide(new BigDecimal("4"))).intValue(); 
				} 
				
				ligacaoEsgoto.setConsumoMinimo(consumoMinimo);
				
			}
		}
		
		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
		filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoPerfil.ID, LigacaoEsgotoPerfil.CONVENCIONAL));
		
		Collection colecaoLigacaoEsgotoPerfil = getControladorUtil().pesquisar(filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());
		
		LigacaoEsgotoPerfil ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoPerfil);
		
		ligacaoEsgoto.setPercentual(ligacaoEsgotoPerfil.getPercentualEsgotoConsumidaColetada());
		ligacaoEsgoto.setPercentualAguaConsumidaColetada(new BigDecimal("100.00"));
		ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
		ligacaoEsgoto.setIndicadorCaixaGordura(LigacaoEsgoto.INDICADOR_SEM_CAIXA_GORDURA);
		ligacaoEsgoto.setImovel(inserirImovelHelper.getImovel());
		ligacaoEsgoto.setUltimaAlteracao(new Date());
		
		//Colocado por Raphael Rossiter em 19/08/2008
		if (inserirImovelHelper.getImovel().getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao()
			.equals(ConstantesSistema.SIM) &&
			inserirImovelHelper.getLigacaoEsgotoEsgotamento() != null){
			
			//Inserindo Situa��o Especial de Faturamento no momento da inclus�o do im�vel
			this.inserirSituacaoEspecialFaturamento(inserirImovelHelper);
			
			ligacaoEsgoto.setLigacaoEsgotoEsgotamento(inserirImovelHelper.getLigacaoEsgotoEsgotamento());
		}
		getControladorUtil().inserir(ligacaoEsgoto);
		
	}
	
	/**
	 * Atualizar os dados do Faturamento do Im�vel
	 *
	 * @author Mariana Victor
	 * @date 11/11/2010
	 *
	 * @param imovel
	 * @throws ControladorException
	 */
	public void atualizarImovelAlterarFaturamento(Imovel imovel, Usuario usuarioLogado) throws ControladorException {
		try {
//			 filtro imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			// Parte de Validacao com Timestamp

			filtroImovel.limparListaParametros();

			// Seta o filtro para buscar o cliente na base
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, imovel.getId()));

			// Procura o cliente na base
			Imovel imovelNaBase = (Imovel) ((List) (getControladorUtil()
					.pesquisar(filtroImovel, Imovel.class.getName()))).get(0);

			// Verificar se o cliente j� foi atualizado por outro usu�rio
			// durante
			// esta atualiza��o
			if (imovelNaBase.getUltimaAlteracao().after(
					imovel.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			imovel.setUltimaAlteracao(new Date());
			
			// ------------ <REGISTRAR TRANSA��O>----------------------------

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_IMOVEL_ATUALIZAR_FATURAMENTO, imovel.getId(),
					imovel.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(imovel);

			getControladorTransacao().registrarTransacao(imovel);
			
			// ------------ </REGISTRAR TRANSA��O>----------------------------
			
			// atualiza o imovel na base
			repositorioImovel.atualizarImovel(imovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 * @throws ControladorException
	 */
	public void atualizarImovel(Imovel imovel, Usuario usuarioLogado) throws ControladorException {

		try {
			// filtro imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			// Parte de Validacao com Timestamp

			filtroImovel.limparListaParametros();

			// Seta o filtro para buscar o imovel na base
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, imovel.getId()));

			// Procura o imovel na base
			Imovel imovelNaBase = (Imovel) ((List) (getControladorUtil()
					.pesquisar(filtroImovel, Imovel.class.getName()))).get(0);

			// Verificar se o imovel j� foi atualizado por outro usu�rio
			// durante
			// esta atualiza��o
			if (imovelNaBase.getUltimaAlteracao().after(
					imovel.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			imovel.setUltimaAlteracao(new Date());
			
			// ------------ <REGISTRAR TRANSA��O>----------------------------

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_IMOVEL_ATUALIZAR, imovel.getId(),
					imovel.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(imovel);

			getControladorTransacao().registrarTransacao(imovel);
			
			// ------------ </REGISTRAR TRANSA��O>----------------------------
			
			// atualiza o imovel na base
			repositorioImovel.atualizarImovel(imovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * Transferir Im�vel e Registrar a Transa��o
	 * 
	 * @author Davi Menezes
	 * @date 05/08/2011
	 * 
	 * @param imovel
	 * @exception ErroRepositorioException
	 * @throws ControladorException
	 */
	public void transferirImovel(Imovel imovel, Usuario usuarioLogado) throws ControladorException {

		try {
			// filtro imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			// Parte de Validacao com Timestamp
			filtroImovel.limparListaParametros();

			// Seta o filtro para buscar o imovel na base
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, imovel.getId()));

			// Procura o imovel na base
			Imovel imovelNaBase = (Imovel) ((List) (getControladorUtil()
					.pesquisar(filtroImovel, Imovel.class.getName()))).get(0);

			// Verificar se o imovel j� foi atualizado por 
			// outro usu�rio durante esta atualiza��o
			if (imovelNaBase.getUltimaAlteracao().after(
					imovel.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			imovel.setUltimaAlteracao(new Date());
			
			// ------------ <REGISTRAR TRANSA��O>----------------------------

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_TRANSFERIR_IMOVEL_LOGRADOURO, imovel.getId(),
					imovel.getId(),
						new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(imovel);

			getControladorTransacao().registrarTransacao(imovel);
			
			// ------------ </REGISTRAR TRANSA��O>----------------------------
			
			// atualiza o imovel na base
			repositorioImovel.atualizarImovel(imovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Atualizar Im�vel
	 *
	 * @author Raphael Rossiter
	 * @date 20/08/2008
	 *
	 * @param inserirImovelHelper
	 * @throws ControladorException
	 */
	public void atualizarImovel(InserirImovelHelper inserirImovelHelper) throws ControladorException {
		
		this.verificarAlteracaoImovelEmCampo(inserirImovelHelper);
		//------------ <REGISTRAR TRANSA��O>----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		Operacao.OPERACAO_IMOVEL_ATUALIZAR, inserirImovelHelper.getImovel().getId(), 
		inserirImovelHelper.getImovel().getId(), new UsuarioAcaoUsuarioHelper(
		inserirImovelHelper.getUsuario(), UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(inserirImovelHelper.getImovel());
		
		getControladorTransacao().registrarTransacao(inserirImovelHelper.getImovel());
		
		// ------------ </REGISTRAR TRANSA��O>----------------------------		
		
		// filtro imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(
		FiltroImovel.ID, inserirImovelHelper.getImovel().getId()));

		// cole��o com resultado da pesquisa de imovel
		Collection imoveis = getControladorUtil().pesquisar(filtroImovel,
		Imovel.class.getName());
		
		// imovel encontrado
		Imovel imovelNaBase = (Imovel) imoveis.iterator().next();

		// Verificar se o imovel j� foi atualizada por outro usu�rio durante
		// esta atualiza��o
		if (imovelNaBase.getUltimaAlteracao().after(
			inserirImovelHelper.getImovel().getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		
		try {
			
			// Atualiza a data de �ltima altera��o
			inserirImovelHelper.getImovel().setUltimaAlteracao(new Date());
			// cliente imoveis removidos do banco
			Collection colecaoClientesImoveisRemovidos = inserirImovelHelper.getColecaoClientesImoveisRemovidos();
			
			if (colecaoClientesImoveisRemovidos != null
					&& !colecaoClientesImoveisRemovidos.isEmpty()) {
				Iterator iteratorClientesImoveisRemovidos = colecaoClientesImoveisRemovidos
						.iterator();
				ClienteImovel clienteImovelRemovido = null;
				while (iteratorClientesImoveisRemovidos.hasNext()) {
					clienteImovelRemovido = (ClienteImovel) iteratorClientesImoveisRemovidos
							.next();

					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel
							.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
					filtroClienteImovel
							.adicionarParametro(new ParametroSimples(
									FiltroClienteImovel.IMOVEL_ID, inserirImovelHelper.getImovel().getId()));
					filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
					Collection colecaoClienteImovel = (Collection) getControladorUtil()
							.pesquisar(filtroClienteImovel,
									ClienteImovel.class.getName());

					boolean existe = this.verificarExistenciaClienteImovel(
							colecaoClienteImovel, clienteImovelRemovido);
					if (existe) {// se ja existe na base apenas atualiza para
						
						registradorOperacao.registrarOperacao(clienteImovelRemovido);
						
						getControladorUtil().atualizar(clienteImovelRemovido);
					}
					
				}
			}

			// clientes imoves na lista do imovel adicionar ao imovel
			Collection clientes = inserirImovelHelper.getClientes();
			
			if (clientes != null && !clientes.isEmpty()) {
				
				Iterator iteratorCliente = clientes.iterator();
				ClienteImovel clienteImovel = null;

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
				filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO);
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, inserirImovelHelper.getImovel().getId()));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				Collection colecaoClienteImovel = (Collection) getControladorUtil()
						.pesquisar(filtroClienteImovel,
								ClienteImovel.class.getName());

				boolean clienteResponsavel = false;
				
				// atualiza ou insere o cliente imovel
				while (iteratorCliente.hasNext()) {
					
					clienteImovel = (ClienteImovel) iteratorCliente.next();
					
					if (clienteImovel.getClienteRelacaoTipo().getId().equals(
							ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL)){
						clienteResponsavel = true;
					}
					
					boolean existe = this.verificarExistenciaClienteImovel(
							colecaoClienteImovel, clienteImovel);
					
					registradorOperacao.registrarOperacao(clienteImovel);
					
					if (existe) {// se ja existe na base apenas atualiza para
						// o cliente imovel
						clienteImovel.setImovel(inserirImovelHelper.getImovel());
						clienteImovel.setUltimaAlteracao(new Date());
						if (clienteImovel.getDataFimRelacao() == null || 
								clienteImovel.getDataFimRelacao().equals("") ) {
							getControladorUtil().atualizar(clienteImovel);
						}
					} else {// insere o cliente imovel par ao imovel caso n�o
						// exista na base
						clienteImovel.setImovel(inserirImovelHelper.getImovel());
						clienteImovel.setUltimaAlteracao(new Date());
						getControladorUtil().inserirOuAtualizar(clienteImovel);
					}
				}
				
				/*
				 * Altera��o solicitada por Rosana Carvalho e implementada por Tiago Moreno
				 * Data: 10/03/2008
				 * 
				 * [SB0001] 5.4. Caso n�o seja informado um novo cliente respons�vel, os indicadores 
				 * de envio da conta (IMOV_ICCONTA) e extrato para respons�vel (IMOV_ICEMISSAOEXTRATOFATURAMENTO)
				 * devem ser atualizados com o valor 2(dois) e o Conta Envio (ICTE_ID = 2)
				 * 
				 */
				if (!clienteResponsavel){

					if(inserirImovelHelper.getImovel().getImovelContaEnvio() == null ||
						(inserirImovelHelper.getImovel().getImovelContaEnvio().getId().intValue() != ImovelContaEnvio.ENVIAR_PARA_EMAIL.intValue() &&
						inserirImovelHelper.getImovel().getImovelContaEnvio().getId().intValue() != ImovelContaEnvio.ENVIAR_PARA_IMOVEL_E_PARA_EMAIL.intValue()) ){

						ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
						imovelContaEnvio.setId(2);
						
						//setando falor para imovel conta envio
						inserirImovelHelper.getImovel().setImovelContaEnvio(imovelContaEnvio);
					}
					
					//setando indicador de conta
					inserirImovelHelper.getImovel().setIndicadorConta(new Short("2"));
					
					//setando indicador de emissao de extrato
					inserirImovelHelper.getImovel().setIndicadorEmissaoExtratoFaturamento(new Short("2"));
					
					//atualizando o imovel
					getControladorUtil().atualizar(inserirImovelHelper.getImovel());
				}
				
			}
			
			/**
			 * Alterado por Arthur Carvalho 11/11/2009
			 * Urgencia para libera��o da vers�o, contudo necessita de corre��o
			 * So atualiza os dados da aba subcategoria caso tenha sido alterada
			 */
				
				Collection colecaoImovelSubcategoriasRemovidas = 
				inserirImovelHelper.getColecaoImovelSubcategoriasRemovidas();
				
				if (colecaoImovelSubcategoriasRemovidas != null
						&& !colecaoImovelSubcategoriasRemovidas.isEmpty()) {
					Iterator iColecaoImovelSubcategoriasRemovidas = colecaoImovelSubcategoriasRemovidas
							.iterator();
					ImovelSubcategoria imovelSubcategoria = null;
					while (iColecaoImovelSubcategoriasRemovidas.hasNext()) {
						imovelSubcategoria = (ImovelSubcategoria) iColecaoImovelSubcategoriasRemovidas
								.next();
						if (imovelSubcategoria.getComp_id().getImovel() != null
								&& imovelSubcategoria.getComp_id().getSubcategoria() != null) {
							registradorOperacao.registrarOperacao(imovelSubcategoria);						
							getControladorUtil().remover(imovelSubcategoria);
	
						}
					}
				}
				
				// imovel sub categoria
				Collection subcategorias = inserirImovelHelper.getSubcategorias();
				
				if (subcategorias != null && !subcategorias.isEmpty()) {
					Iterator iteratorImovelSubCategorias = subcategorias.iterator();
					ImovelSubcategoria imovelSubcategoria = null;
	
	
					// atualiza ou insere o imovel sub categoria
					while (iteratorImovelSubCategorias.hasNext()) {
	
						imovelSubcategoria = null;
						imovelSubcategoria = (ImovelSubcategoria) iteratorImovelSubCategorias
								.next();
	
						// [FS0018]- Validar a(s) categoria(s) do imovel com a(s)
						// respectiva(s) tarifa(s)
						if (imovelSubcategoria.getComp_id().getSubcategoria()
								.getCategoria() != null) {
	
							Boolean existeCategoria = repositorioImovel
									.verificarExistenciaTarifaConsumoCategoriaParaCategoriaImovel(
											inserirImovelHelper.getImovel().getId(),
											imovelSubcategoria.getComp_id()
													.getSubcategoria()
													.getCategoria().getId());
	
							if (!existeCategoria) {
								//sessionContext.setRollbackOnly();
								throw new ControladorException(
										"atencao.categoria_imovel_sem_tarifa",
										null, inserirImovelHelper.getImovel()
												.getId().toString());
							}
	
						}
						
							
						// n�o exista na base
						if ( imovelSubcategoria.getComp_id().getImovel() == null || 
								imovelSubcategoria.getComp_id().getImovel().equals("") ) {

							imovelSubcategoria.getComp_id().setImovel(inserirImovelHelper.getImovel());
							imovelSubcategoria.setUltimaAlteracao(new Date());
							
							registradorOperacao.registrarOperacao(imovelSubcategoria);
							getControladorUtil().inserir(imovelSubcategoria);
						} else {
							registradorOperacao.registrarOperacao(imovelSubcategoria);
							getControladorUtil().atualizar(imovelSubcategoria);
						}
					}
				}else {
					
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.required", null," Subcategoria do Im�vel");
				}
				
			Collection colecaoImovelRamoAtivadadeRemovidas = inserirImovelHelper.getColecaoRamoAtividadesRemovidas();
					
			if (colecaoImovelRamoAtivadadeRemovidas != null
					&& !colecaoImovelRamoAtivadadeRemovidas.isEmpty()) {
				Iterator iColecaoImovelRamoAtivadadeRemovidas = colecaoImovelRamoAtivadadeRemovidas
						.iterator();
				ImovelRamoAtividade imovelRamoAtividade = null;
				while (iColecaoImovelRamoAtivadadeRemovidas.hasNext()) {
					imovelRamoAtividade = (ImovelRamoAtividade) iColecaoImovelRamoAtivadadeRemovidas.next();
					if (imovelRamoAtividade.getComp_id().getImovel() != null) {
						registradorOperacao.registrarOperacao(imovelRamoAtividade);						
						getControladorUtil().remover(imovelRamoAtividade);

					}
				}
			}
				
			//Ramos atividades
			Collection ramosAtividades = inserirImovelHelper.getRamosAtividades();
			Iterator iteratorRamosAtividades = ramosAtividades.iterator();
			ImovelRamoAtividade imovelRamoAtividade = null;
			
			
			if(ramosAtividades != null && !ramosAtividades.isEmpty()){
				while (iteratorRamosAtividades.hasNext()) {
					
					 imovelRamoAtividade = (ImovelRamoAtividade) iteratorRamosAtividades.next();
					 
					 registradorOperacao.registrarOperacao(imovelRamoAtividade);
					 if (imovelRamoAtividade.getComp_id().getImovel() == null || 
							 imovelRamoAtividade.getComp_id().getImovel().equals("") ) {
					 imovelRamoAtividade.getComp_id().setImovel(inserirImovelHelper.getImovel());
					 imovelRamoAtividade.setUltimaAlteracao(new Date());
					 getControladorUtil().inserir(imovelRamoAtividade);			
					 } else {
						 getControladorUtil().atualizar(imovelRamoAtividade);
					 }
				}	
			}
			


			/**
			 * alterado por pedro alexandre dia 17/11/2006 altera��o feita para
			 * acoplar o controle de abrang�ncia de usu�rio
			 */
			// ------------ CONTROLE DE ABRANGENCIA ----------------
			Abrangencia abrangencia = new Abrangencia(inserirImovelHelper.getUsuario(), 
			inserirImovelHelper.getImovel());
			// ------------ CONTROLE DE ABRANGENCIA ----------------

			if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.acesso.negado.abrangencia");
			} else {
				// atualiza o imovel na base
				this.getControladorUtil().atualizar(inserirImovelHelper.getImovel());
			}
			
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
			inserirImovelHelper.getImovel().getLigacaoEsgotoSituacao().getId()));

			Collection colecaoLigacaoEsgotoSituacao = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
			
			if (ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao().equals(LigacaoEsgotoSituacao.FATURAMENTO_ATIVO)) {
				FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
				filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, 
				inserirImovelHelper.getImovel().getId()));
				
				Collection colecaoLigacaoEsgoto = getControladorUtil()
				.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
				
				if (colecaoLigacaoEsgoto != null && !colecaoLigacaoEsgoto.isEmpty()) {
					LigacaoEsgoto ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
					
					inserirImovelHelper.getImovel().setLigacaoEsgoto(ligacaoEsgoto);
					
					atualizarLigacaoEsgotoImovel(inserirImovelHelper);
				} else {
					inserirLigacaoEsgotoImovel(inserirImovelHelper);
				}
			}
			
			/**
			 * fim altera��o de controle de abrang�ncia
			 * *************************
			 */

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
			
		}catch (Exception e) {
			sessionContext.setRollbackOnly();
			
			if (e instanceof ControladorException){
			
				if(((ControladorException)e).getParametroMensagem().size() > 0){
					throw new ControladorException( e.getMessage(),null,((ControladorException)e).getParametroMensagem().get(0));
				}else{
					throw new ControladorException( e.getMessage(),e);
				}
				
			}else{
				throw new ControladorException("erro.sistema", e);
			}
		}	
	}

	private void verificarAlteracaoImovelEmCampo(InserirImovelHelper imovelHelper) {
		try {
			Imovel imovelnaBase = this.pesquisarImovel(imovelHelper.getImovel().getId());
			Rota rota = getControladorMicromedicao().buscarRotaDoImovel(imovelHelper.getImovel().getId());
			
			if (!imovelnaBase.getLocalidade().getId().equals(imovelHelper.getImovel().getLocalidade().getId())
					|| !imovelnaBase.getSetorComercial().getId().equals(imovelHelper.getImovel().getSetorComercial().getId())
					|| !imovelnaBase.getQuadra().getId().equals(imovelHelper.getImovel().getQuadra().getId())
					|| imovelnaBase.getQuadra().getNumeroQuadra() != imovelHelper.getImovel().getQuadra().getNumeroQuadra()
					|| imovelnaBase.getLote() != imovelHelper.getImovel().getLote()
					|| imovelnaBase.getSubLote() != imovelHelper.getImovel().getSubLote() 
					|| !imovelnaBase.getQuantidadeEconomias().equals(imovelHelper.getImovel().getQuantidadeEconomias())) {
				getControladorMicromedicao().validarImovelEmCampo(imovelHelper.getImovel().getId());
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Atualiza a Liga��o de Esgoto quando o im�vel � inserido com o indicador de faturamento igual a sim
	 *
	 * @author Rafael Corr�a, Raphael Rossiter
	 * @date 21/05/2008, 20/08/2008
	 *
	 * @param inserirImovelHelper
	 * @throws ControladorException
	 */
	private void atualizarLigacaoEsgotoImovel(InserirImovelHelper inserirImovelHelper) throws ControladorException {
		
		if (inserirImovelHelper.getImovel().getFonteAbastecimento() != null) {
			
			FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, 
			inserirImovelHelper.getImovel().getFonteAbastecimento().getId()));
			
			Collection colecaoFonteAbastecimento = getControladorUtil().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
			
			FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(colecaoFonteAbastecimento);
			
			if (fonteAbastecimento.getIndicadorCalcularVolumeFixo().equals(FonteAbastecimento.INDICADOR_CALCULAR_VOLUME_FIXO)) {
				
				Integer consumoMinimo = null;
				
				if (inserirImovelHelper.getImovel().getAreaConstruida() != null) {
					consumoMinimo = (inserirImovelHelper.getImovel().getAreaConstruida().divide(new BigDecimal("4"))).intValue(); 
				} 
				
				inserirImovelHelper.getImovel().getLigacaoEsgoto().setConsumoMinimo(consumoMinimo);
				
			}
			
		} else {
			inserirImovelHelper.getImovel().getLigacaoEsgoto().setConsumoMinimo(null);
		}
		
		
		//Colocado por Raphael Rossiter em 19/08/2008
		if (inserirImovelHelper.getImovel().getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao()
			.equals(ConstantesSistema.SIM)){
			
			inserirImovelHelper.getImovel().getLigacaoEsgoto()
			.setLigacaoEsgotoEsgotamento(inserirImovelHelper.getLigacaoEsgotoEsgotamento());
			
			if (inserirImovelHelper.getLigacaoEsgotoEsgotamento() != null){
				
				//Verificando a exist�ncia de situa��o especial para o im�vel
				FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = 
			    new FiltroFaturamentoSituacaoHistorico();
			    	
			    filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroSimples(
			    FiltroFaturamentoSituacaoHistorico.ID_IMOVEL, inserirImovelHelper.getImovel().getId()));
			    	
			    filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroNulo(
			    FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
			    	
			    Collection colecaoSituacaoEspecial = this.getControladorUtil()
			    .pesquisar(filtroFaturamentoSituacaoHistorico, FaturamentoSituacaoHistorico.class.getName());
			    
			    if (colecaoSituacaoEspecial == null || colecaoSituacaoEspecial.isEmpty()){
			    	
			    	//INSERINDO SITUA��O ESPECIAL
			    	this.inserirSituacaoEspecialFaturamento(inserirImovelHelper);
			    }
				
			}
		}
		
		//ligacaoEsgoto.setDataLigacao(null);
		inserirImovelHelper.getImovel().getLigacaoEsgoto().setPercentualAguaConsumidaColetada(new BigDecimal("100.00"));
		inserirImovelHelper.getImovel().getLigacaoEsgoto().setUltimaAlteracao(new Date());
		
		getControladorUtil().atualizar(inserirImovelHelper.getImovel().getLigacaoEsgoto());
	}

	/**
	 * Verifica se o ClienteImovel existe na Cole��o do Cliente Imovel do Imovel
	 * Author: Rafael Santos Data: 01/02/2006
	 * 
	 * @param colecaoClienteImovel
	 *            Cole��o Cliente Imovel
	 * @param clienteImovel
	 *            Cliente Imovel
	 * @return true se o cliente imovel existe, false caso contr�rio
	 */
	public boolean verificarExistenciaClienteImovel(
			Collection colecaoClienteImovel, ClienteImovel clienteImovel) {

		boolean retorno = false;
		boolean achou = false;
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			Iterator iColecaoClienteImovel = colecaoClienteImovel.iterator();

			while (iColecaoClienteImovel.hasNext() && !achou) {
				ClienteImovel clienteImovelNaBase = (ClienteImovel) iColecaoClienteImovel
						.next();

				if (clienteImovel.getId() != null
						&& clienteImovelNaBase.getId().intValue() == clienteImovel
								.getId().intValue()) {
					retorno = true;
					achou = true;
				}

			}

		}
		return retorno;
	}

	/**
	 * Verifica se ImovelSubCategoria existe na Cole��o de SubCategoria do
	 * Imovel Author: Rafael Santos Data: 01/02/2006
	 * 
	 * @param colecaoSubCategoria
	 *            Cole��o Imovel Sub Categoria
	 * @param imovelSubcategoria
	 *            ImovelSubcategoria
	 * @return true se a Imovel SubCategoria existe, false caso contr�rio
	 */
	public boolean verificarExistenciaImovelSubcategoria(
			Collection colecaoImovelSubCategoria,
			ImovelSubcategoria imovelSubcategoria) {

		boolean retorno = false;
		boolean achou = false;
		if (colecaoImovelSubCategoria != null
				&& !colecaoImovelSubCategoria.isEmpty()) {

			Iterator iColecaoImovelSubCategoria = colecaoImovelSubCategoria
					.iterator();

			while (iColecaoImovelSubCategoria.hasNext() && !achou) {
				ImovelSubcategoria ImovolSubCategoriaNaBase = (ImovelSubcategoria) iColecaoImovelSubCategoria
						.next();

				if (ImovolSubCategoriaNaBase.getComp_id().getSubcategoria()
						.getId().intValue() == imovelSubcategoria.getComp_id()
						.getSubcategoria().getId().intValue()) {
					retorno = true;
					achou = true;
				}

			}

		}
		return retorno;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovelSubcategoria
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void inserirImovelSubCategoria(ImovelSubcategoria imovelSubcategoria)
			throws ControladorException {

		getControladorUtil().inserir(imovelSubcategoria);

	}
	
	public void inserirImovelRamoAtividade(ImovelRamoAtividade imovelRamoAtividade) throws ControladorException{
		getControladorUtil().inserir(imovelRamoAtividade);
	}
	
	public void inserirOuAtualizarImovelRamoAtividade(ImovelRamoAtividade imovelRamoAtividade) throws ControladorException{
		getControladorUtil().inserirOuAtualizar(imovelRamoAtividade);
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovelSubcategoria
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void atualizarImovelSubCategoria(
			ImovelSubcategoria imovelSubcategoria) throws ControladorException {
		try {
			repositorioImovel.atualizarImovelSubCategoria(imovelSubcategoria);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param objeto
	 *            Descri��o do par�metro
	 * @param condicional
	 *            Descri��o do par�metro
	 * @param id
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void removerTodos(String objeto, String condicional, Integer id)
			throws ControladorException {
		try {
			repositorioImovel.removerTodos(objeto, condicional, id);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Collection obterQuantidadeEconomiasCategoria(Imovel imovel)
			throws ControladorException {
		return obterQuantidadeEconomiasCategoria(imovel.getId());
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Collection obterQuantidadeEconomiasContaCategoria(Conta conta)
			throws ControladorException {
		return obterQuantidadeEconomiasContaCategoria(conta.getId());
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Collection obterQuantidadeEconomiasCategoria(Integer imovel) throws ControladorException {
		Collection colecaoCategoria = new ArrayList();
		Collection colecaoImovelSubCategoriaArray = null;

		try {
			colecaoImovelSubCategoriaArray = repositorioImovel.pesquisarObterQuantidadeEconomiasCategoria(imovel);
		} catch (ErroRepositorioException ex) {
			new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovelSubCategoriaArray != null && !colecaoImovelSubCategoriaArray.isEmpty()) {

			Iterator colecaoImovelSubCategoriaArrayIterator = colecaoImovelSubCategoriaArray.iterator();

			while (colecaoImovelSubCategoriaArrayIterator.hasNext()) {

				Object[] imovelSubcategoriaArray = (Object[]) colecaoImovelSubCategoriaArrayIterator.next();

				Categoria categoria = new Categoria();
				categoria.setId((Integer) imovelSubcategoriaArray[0]);
				categoria.setDescricao(String.valueOf(imovelSubcategoriaArray[1]));
				categoria.setConsumoEstouro((Integer) imovelSubcategoriaArray[2]);
				categoria.setVezesMediaEstouro((BigDecimal) imovelSubcategoriaArray[3]);
				categoria.setQuantidadeEconomiasCategoria(((Short) imovelSubcategoriaArray[4]).intValue());
				categoria.setConsumoAlto((Integer) imovelSubcategoriaArray[6]);
				categoria.setMediaBaixoConsumo((Integer) imovelSubcategoriaArray[7]);
				categoria.setVezesMediaAltoConsumo((BigDecimal) imovelSubcategoriaArray[8]);
				categoria.setPorcentagemMediaBaixoConsumo((BigDecimal) imovelSubcategoriaArray[9]);

				if ((String) imovelSubcategoriaArray[10] != null) {
					categoria.setDescricaoAbreviada((String) imovelSubcategoriaArray[10]);
				}
				categoria.setNumeroConsumoMaximoEc((Integer) imovelSubcategoriaArray[11]);
				categoria.setIndicadorCobrancaAcrescimos((Short) imovelSubcategoriaArray[12]);
				categoria.setFatorEconomias((Short) imovelSubcategoriaArray[13]);

				CategoriaTipo categoriaTipo = new CategoriaTipo();
				categoriaTipo.setId((Integer) imovelSubcategoriaArray[14]);
				categoriaTipo.setDescricao((String) imovelSubcategoriaArray[15]);
				
				categoria.setCategoriaTipo(categoriaTipo);
				
				categoria.setNumeroConsumoMaximoEc( (Integer) imovelSubcategoriaArray[16] );

				colecaoCategoria.add(categoria);
			}

		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.nao_cadastrado.imovel_subcategoria", null);
		}

		return colecaoCategoria;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Collection obterQuantidadeEconomiasContaCategoria(Integer conta)
			throws ControladorException {
		// Cria��o das cole��es
		Collection colecaoCategoria = new ArrayList();
		Collection colecaoContaCategoriaArray = null;

		try {
			colecaoContaCategoriaArray = repositorioImovel
					.obterQuantidadeEconomiasCategoria(conta);
		} catch (ErroRepositorioException ex) {
			// sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		if (colecaoContaCategoriaArray != null
				&& !colecaoContaCategoriaArray.isEmpty()) {

			Iterator colecaoContaCategoriaArrayIterator = colecaoContaCategoriaArray
					.iterator();

			while (colecaoContaCategoriaArrayIterator.hasNext()) {

				// Obt�m o im�vel subcategoria
				Object[] contaCategoriaArray = (Object[]) colecaoContaCategoriaArrayIterator
						.next();

				// Cria os objetos categoria
				Categoria categoria = new Categoria();

				// Seta a categoria
				categoria.setId((Integer) contaCategoriaArray[0]);

				// Seta a descri��o
				categoria.setDescricao(String.valueOf(contaCategoriaArray[1]));

				// Seta o consumo estouro
				categoria.setConsumoEstouro((Integer) contaCategoriaArray[2]);
				// Seta n�mero de vezes m�dia estouro
				categoria
						.setVezesMediaEstouro((BigDecimal) contaCategoriaArray[3]);
				// Seta a quantidade de economias por categoria
				categoria.setQuantidadeEconomiasCategoria(new Integer(
						((Short) contaCategoriaArray[4]).toString()));
				// Seta o consumo alto
				categoria.setConsumoAlto((Integer) contaCategoriaArray[6]);
				// Seta a m�dia baixo consumo
				categoria
						.setMediaBaixoConsumo((Integer) contaCategoriaArray[7]);
				// Seta o n�mero de vezes m�dia consumo alto
				categoria
						.setVezesMediaAltoConsumo((BigDecimal) contaCategoriaArray[8]);
				// Seta o percentual da m�dia baixo consumo
				categoria
						.setPorcentagemMediaBaixoConsumo((BigDecimal) contaCategoriaArray[9]);
				// Seta a descricao abreviada
				if ((String) contaCategoriaArray[10] != null) {
					categoria
							.setDescricaoAbreviada((String) contaCategoriaArray[10]);
				}
				
				//FATOR_ECONOMIAS
				categoria.setFatorEconomias((Short) contaCategoriaArray[11]);
				
				// Consumo M�ximo
				categoria.setNumeroConsumoMaximoEc( (Integer) contaCategoriaArray[12]);

				colecaoCategoria.add(categoria);
			}

		} else {
			// Caso a cole��o n�o tenha retornado objetos
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.nao_cadastrado.imovel_subcategoria", null);
		}

		return colecaoCategoria;
	}

	/**
	 * Metodo que verifica se ja tem um imovel ou um imovel economia com o
	 * numero do iptu passado caso a pessoa passe o idSetorComercial verifica
	 * apenas no mesmo municipio
	 * 
	 * @param numeroIptu
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public void verificarExistenciaIPTU(String numeroIptu,
			Integer idSetorComercial) throws ControladorException {

		Collection setorComerciais = null;
		if (idSetorComercial != null) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID, new Integer(idSetorComercial)));
			filtroSetorComercial
					.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.MUNICIPIO);
			setorComerciais = getControladorUtil().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());
		}

		FiltroImovel filtroImovel = new FiltroImovel();
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) setorComerciais
					.iterator().next();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.SETOR_COMERCIAL_MUNICIPIO_ID, setorComercial
							.getMunicipio().getId()));
		}
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IPTU,
				numeroIptu));

		Collection imoveis = getControladorUtil().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (imoveis != null && !imoveis.isEmpty()) {
			String idMatricula = ""
					+ ((Imovel) ((List) imoveis).get(0)).getId();

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.imovel.iptu_jacadastrado",
					null, idMatricula);
		}

		FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
		// filtroImovelEconomia.adicionarParametro(new
		// ParametroSimples(FiltroImovelEconomia.IMOVEL_ID, imovel.getId()));
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) setorComerciais
					.iterator().next();
			filtroImovelEconomia.adicionarParametro(new ParametroSimples(
					FiltroImovelEconomia.MUNICIPIO_ID, setorComercial
							.getMunicipio().getId()));
		}
		filtroImovelEconomia.adicionarParametro(new ParametroSimples(
				FiltroImovelEconomia.IPTU, numeroIptu));
		filtroImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria");
		filtroImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.imovel");

		Collection imoveisEconomiaPesquisadas = getControladorUtil().pesquisar(
				filtroImovelEconomia, ImovelEconomia.class.getName());

		if (!imoveisEconomiaPesquisadas.isEmpty()) {
			ImovelEconomia imovelEconomia = (ImovelEconomia) ((List) imoveisEconomiaPesquisadas)
					.get(0);

			String idMatricula = ""
					+ imovelEconomia.getImovelSubcategoria().getComp_id()
							.getImovel().getId();

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.imovel.iptu_jacadastrado",
					null, idMatricula);
		}
	}

	/**
	 * Metodo que verifica se ja tem um imovel ou um imovel economia com o
	 * numero do iptu passado caso a pessoa passe o idSetorComercial verifica
	 * apenas no mesmo municipio
	 * 
	 * @param numeroIptu
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public void verificarExistenciaCELPE(String numeroCelp,
			Integer idSetorComercial) throws ControladorException {

		Collection setorComerciais = null;
		if (idSetorComercial != null) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID, new Integer(idSetorComercial)));
			filtroSetorComercial
					.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.MUNICIPIO);
			setorComerciais = getControladorUtil().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());
		}

		FiltroImovel filtroImovel = new FiltroImovel();
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) setorComerciais
					.iterator().next();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.SETOR_COMERCIAL_MUNICIPIO_ID, setorComercial
							.getMunicipio().getId()));
		}
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.NUMERO_CELPE, numeroCelp));

		Collection imoveis = getControladorUtil().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (imoveis != null && !imoveis.isEmpty()) {
			String idMatricula = ""
					+ ((Imovel) ((List) imoveis).get(0)).getId();

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.imovel.numero_celpe_jacadastrado", null,
					idMatricula);
		}

		FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
		// filtroImovelEconomia.adicionarParametro(new
		// ParametroSimples(FiltroImovelEconomia.IMOVEL_ID, imovel.getId()));
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) setorComerciais
					.iterator().next();
			filtroImovelEconomia.adicionarParametro(new ParametroSimples(
					FiltroImovelEconomia.MUNICIPIO_ID, setorComercial
							.getMunicipio().getId()));
		}
		filtroImovelEconomia.adicionarParametro(new ParametroSimples(
				FiltroImovelEconomia.NUMERO_CELPE, numeroCelp));
		filtroImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria");
		filtroImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.imovel");

		Collection imoveisEconomiaPesquisadas = getControladorUtil().pesquisar(
				filtroImovelEconomia, ImovelEconomia.class.getName());

		if (!imoveisEconomiaPesquisadas.isEmpty()) {
			ImovelEconomia imovelEconomia = (ImovelEconomia) ((List) imoveisEconomiaPesquisadas)
					.get(0);

			String idMatricula = ""
					+ imovelEconomia.getImovelSubcategoria().getComp_id()
							.getImovel().getId();

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.imovel.numero_celpe_jacadastrado", null,
					idMatricula);
		}
	}

	/**
	 * verifica se existe algum iptu no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Description of the Parameter
	 * @param imovel
	 *            Description of the Parameter
	 * @param numeroIptu
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void verificarExistenciaIPTU(Collection imoveisEconomia,
			Imovel imovel, String numeroIptu, Date dataUltimaAlteracao)
			throws ControladorException {

		// Cria Filtros
		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.SETOR_COMERCIAL_MUNICIPIO_ID, imovel
						.getSetorComercial().getMunicipio().getId()));
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IPTU,
				numeroIptu));

		Collection imoveis = getControladorUtil().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (imoveis != null && !imoveis.isEmpty()) {
			String idMatricula = ""
					+ ((Imovel) ((List) imoveis).get(0)).getId();

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.imovel.iptu_jacadastrado",
					null, idMatricula);
		}

		// comparando com a data diferente de null
		if (dataUltimaAlteracao != null) {

			FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();

			filtroImovelEconomia.adicionarParametro(new ParametroSimples(
					FiltroImovelEconomia.IMOVEL_ID, imovel.getId()));
			filtroImovelEconomia.adicionarParametro(new ParametroSimples(
					FiltroImovelEconomia.MUNICIPIO_ID, imovel
							.getSetorComercial().getMunicipio().getId()));
			filtroImovelEconomia.adicionarParametro(new ParametroSimples(
					FiltroImovelEconomia.IPTU, numeroIptu));

			filtroImovelEconomia
					.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.imovel");

			Collection imoveisEconomiaPesquisadas = getControladorUtil()
					.pesquisar(filtroImovelEconomia,
							ImovelEconomia.class.getName());

			if (!imoveisEconomiaPesquisadas.isEmpty()) {
				ImovelEconomia imovelEconomia = (ImovelEconomia) ((List) imoveisEconomiaPesquisadas)
						.get(0);

				if (imovelEconomia.getUltimaAlteracao().getTime() != dataUltimaAlteracao
						.getTime()) {
					String idMatricula = ""
							+ imovelEconomia.getImovelSubcategoria()
									.getComp_id().getImovel().getId();

					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.imovel.iptu_jacadastrado", null,
							idMatricula);
				}
			}
		}

		if (imoveisEconomia != null) {
			Iterator imovelEconomiaIterator = imoveisEconomia.iterator();

			while (imovelEconomiaIterator.hasNext()) {

				ImovelEconomia imovelEconomia = (ImovelEconomia) imovelEconomiaIterator
						.next();

				// caso seja o mesmo imovel economia n�o fa�a a verifica��o
				if (imovelEconomia.getUltimaAlteracao().getTime() != dataUltimaAlteracao
						.getTime()) {
					// verifica se o numero da iptu que veio do imovel economia
					// �
					// diferente de nulo.
					if (imovelEconomia.getNumeroIptu() != null
							&& !imovelEconomia.getNumeroIptu().equals("")) {

						// se o numero da iptu do imovel economia for diferente
						// de
						// nulo ent�o � verificado se o numero da iptu
						// que o veio do form � igual ao do objeto imovel
						// economia
						// cadastrado.
						if (imovelEconomia.getNumeroIptu().equals(
								new BigDecimal(numeroIptu))) {

							sessionContext.setRollbackOnly();
							throw new ControladorException(
									"atencao.imovel.iptu_jacadastrado", null,
									imovelEconomia.getImovelSubcategoria()
											.getComp_id().getImovel().getId()
											.toString());
						}
					}
				}
				// caso o imvel economia
			}
		} else {

		}

	}

	/**
	 * verifica se existe algum numero da celpe no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Description of the Parameter
	 * @param imovel
	 *            Description of the Parameter
	 * @param numeroCelpe
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void verificarExistenciaCelpe(Collection imoveisEconomia,
			Imovel imovel, String numeroCelpe, Date dataUltimaAlteracao)
			throws ControladorException {

		// Cria Filtros
		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.NUMERO_CELPE, numeroCelpe));

		Collection imoveis = getControladorUtil().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (!imoveis.isEmpty()) {
			String idMatricula = ""
					+ ((Imovel) ((List) imoveis).get(0)).getId();

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.imovel.numero_celpe_jacadastrado", null,
					idMatricula);
		}

		if (dataUltimaAlteracao != null) {

			FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();

			filtroImovelEconomia.adicionarParametro(new ParametroSimples(
					FiltroImovelEconomia.IMOVEL_ID, imovel.getId()));
			filtroImovelEconomia.adicionarParametro(new ParametroSimples(
					FiltroImovelEconomia.NUMERO_CELPE, numeroCelpe));

			filtroImovelEconomia
					.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.imovel");

			Collection imoveisEconomiaPesquisadas = getControladorUtil()
					.pesquisar(filtroImovelEconomia,
							ImovelEconomia.class.getName());

			if (!imoveisEconomiaPesquisadas.isEmpty()) {
				ImovelEconomia imovelEconomia = (ImovelEconomia) ((List) imoveisEconomiaPesquisadas)
						.get(0);

				if (imovelEconomia.getUltimaAlteracao().getTime() != dataUltimaAlteracao
						.getTime()) {

					String idMatricula = ""
							+ imovelEconomia.getImovelSubcategoria()
									.getComp_id().getImovel().getId();

					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.imovel.numero_celpe_jacadastrado", null,
							idMatricula);
				}
			}
		}

		if (imoveisEconomia != null) {

			Iterator imovelEconomiaIterator = imoveisEconomia.iterator();

			while (imovelEconomiaIterator.hasNext()) {

				ImovelEconomia imovelEconomia = (ImovelEconomia) imovelEconomiaIterator
						.next();

				// caso seja o mesmo imovel economia n�o fa�a a verifica��o
				if (imovelEconomia.getUltimaAlteracao().getTime() != dataUltimaAlteracao
						.getTime()) {

					// verifica se o numero da da celpe que veio do imovel
					// economia
					// � diferente de nulo.
					if (imovelEconomia.getNumeroCelpe() != null
							&& !imovelEconomia.getNumeroCelpe().equals("")) {

						Long numeroCelpeLong = new Long(numeroCelpe);

						// se o numero da iptu do imovel economia for diferente
						// de
						// nulo ent�o � verificado se o numero da iptu
						// que o veio do form � igual ao do objeto imovel
						// economia
						// cadastrado.
						if (imovelEconomia.getNumeroCelpe().equals(
								numeroCelpeLong)) {

							sessionContext.setRollbackOnly();

							throw new ControladorException(
									"atencao.imovel.numero_celpe_jacadastrado",
									null, ""
											+ imovelEconomia
													.getImovelSubcategoria()
													.getComp_id().getImovel()
													.getId());
						}
					}
				}
			}
		}
	}

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 18/11/2006
	 * 
	 * @param ids
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void removerImovel(String[] ids, Usuario usuarioLogado)
			throws ControladorException {
		// filtro imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		Imovel imovel = new Imovel();

		// Parte de Validacao com Timestamp

		if (ids.length > 0 && ids != null) {
			for (int i = 0; i < ids.length; i++) {
				String dadosImovel = ids[i];
				String[] idUltimaAlteracao = dadosImovel.split("-");

				filtroImovel.limparListaParametros();
				// pesquissou o imovel na base
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idUltimaAlteracao[0].trim()));

				// cole��o com resultado da pesquisa de imovel
				Collection imoveisNaBase = getControladorUtil().pesquisar(
						filtroImovel, Imovel.class.getName());
				// imovel encontrado
				Imovel imovelNaBase = (Imovel) imoveisNaBase.iterator().next();

				// Verificar se o imovel j� foi atualizada por outro usu�rio
				// durante
				// esta atualiza��o
				Calendar data = new GregorianCalendar();
				data.setTimeInMillis(new Long(idUltimaAlteracao[1].trim())
						.longValue());

				if (imovelNaBase.getUltimaAlteracao().after(data.getTime())) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.atualizacao.timestamp");
				}

				// filtroImovel.limparListaParametros();
				imovel = imovelNaBase;
				
				/**
				 * Altera��o realizada por Ana Maria em 13/10/2008 (Analista: Fab�ola Ara�jo)
				 * [FS0017] Registrar Fim de Rela��o do(s) Cliente(s) com o Im�vel
				 * (Caso existam clientes com rela��o ativa com o im�vel, o sistema dever� encerrar
				 *  esta rela��o, atualizando a data do t�rmino da rela��o e seu motivo de encerramento)
				 */
				Collection clientesImovel = getControladorCliente().pesquisarClienteImovel(imovel.getId());
				
				Iterator clientesImovelIterator = clientesImovel.iterator();
				while (clientesImovelIterator.hasNext()) {
					ClienteImovel clienteImovel = (ClienteImovel) clientesImovelIterator.next();
					ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
					clienteImovelFimRelacaoMotivo.setId(ClienteImovelFimRelacaoMotivo.EXCLUSAO_IMOVEL);
					clienteImovel.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);	
					clienteImovel.setDataFimRelacao(new Date());
					clienteImovel.setUltimaAlteracao(new Date());
					getControladorUtil().inserirOuAtualizar(clienteImovel);
				}


				// imovel = (Imovel) imoveis.iterator().next();
				imovel.setIndicadorExclusao(Imovel.IMOVEL_EXCLUIDO);
				imovel.setUltimaAlteracao(new Date());

				/**
				 * alterado por pedro alexandre dia 18/11/2006 altera��o feita
				 * para acoplar o controle de abrang�ncia de usu�rio
				 */
				// ------------ CONTROLE DE ABRANGENCIA --------------------
				Abrangencia abrangencia = new Abrangencia(usuarioLogado, imovel);

				if (!getControladorAcesso().verificarAcessoAbrangencia(
						abrangencia)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.acesso.negado.abrangencia");
				} else {
					
					// ------------ <REGISTRAR TRANSA��O>----------------------------

					RegistradorOperacao registradorOperacao = new RegistradorOperacao(
							Operacao.OPERACAO_IMOVEL_REMOVER, imovel.getId(), imovel.getId(),
							new UsuarioAcaoUsuarioHelper(usuarioLogado,
									UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

					registradorOperacao.registrarOperacao(imovel);
					
					// ------------ </REGISTRAR TRANSA��O>----------------------------		
					
					
					getControladorUtil().atualizar(imovel);
				}
				// ------------ CONTROLE DE ABRANGENCIA ---------------------
			}
		}

	}

	/**
	 * Pesquisa uma cole��o de im�veis com uma query especifica
	 * 
	 * @param idLocalidade
	 *            parametros para a consulta
	 * @param idSetorComercial
	 *            parametros para a consulta
	 * @param idQuadra
	 *            parametros para a consulta
	 * @param lote
	 *            Descri��o do par�metro
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarImovel(Integer idLocalidade,
			Integer idSetorComercial, Integer idQuadra, Short lote)
			throws ControladorException {

		Collection colecaoImovelArray = null;
		Collection<Imovel> colecaoRetorno = new ArrayList();

		try {
			colecaoImovelArray = repositorioImovel.pesquisarImovel(
					idLocalidade, idSetorComercial, idQuadra, lote,
					Imovel.IMOVEL_EXCLUIDO.intValue());
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		Iterator colecaoImovelArrayIt = colecaoImovelArray.iterator();
		Object[] imovelArray;
		Imovel imovel;
		Quadra quadra;
		Rota rota = new Rota();
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();

		while (colecaoImovelArrayIt.hasNext()) {

			imovelArray = (Object[]) colecaoImovelArrayIt.next();

			imovel = new Imovel();
			imovel.setId((Integer) imovelArray[0]);
			imovel.setLote(new Short(String.valueOf(imovelArray[4])));
			imovel.setSubLote(new Short(String.valueOf(imovelArray[5])));

			quadra = (Quadra) imovelArray[3];
			rota.setId((Integer) imovelArray[6]);
			faturamentoGrupo.setId((Integer) imovelArray[7]);

			rota.setFaturamentoGrupo(faturamentoGrupo);
			quadra.setRota(rota);

			imovel.setLocalidade((Localidade) imovelArray[1]);
			imovel.setSetorComercial((SetorComercial) imovelArray[2]);
			imovel.setQuadra(quadra);

			if (imovelArray[8] != null) {
				faturamentoSituacaoTipo.setId((Integer) imovelArray[8]);
				imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
			}
			
			if (imovelArray[9] != null ){
				cobrancaSituacao.setId((Integer)imovelArray[9]);
				imovel.setCobrancaSituacao(cobrancaSituacao);
			}

			colecaoRetorno.add(imovel);

		}

		return colecaoRetorno;

	}

	/**
	 * Atualiza apenas os dados (Localidade, Setor, Quadra e lote) do im�vel
	 * 
	 * @param imovel
	 *            parametros para a consulta
	 * @throws ControladorException
	 */
	public void atualizarImovelInscricao(Imovel imovel, Usuario usuario)
			throws ControladorException {
		try {
			
			//[UC0107 � Registrar Transa��o]
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
					imovel.getId()));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovelEnderecoAnterior");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovelCondominio");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("areaConstruidaFaixa");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("pavimentoCalcada");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaSuperior");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaInferior");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("pavimentoRua");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("pocoTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("despejo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("fonteAbastecimento");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("piscinaVolumeFaixa");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("consumoTarifa");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			
			Imovel imovelAlterado = (Imovel)Util.retonarObjetoDeColecao(this.getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName()));
			
			if (imovel.getLocalidade() != null && imovel.getLocalidade().getId() != null){
				imovelAlterado.setLocalidade(imovel.getLocalidade());
			}
			
			if (imovel.getSetorComercial() != null && imovel.getSetorComercial().getId() != null){
				imovelAlterado.setSetorComercial(imovel.getSetorComercial());
			}
			
			if (imovel.getQuadra() != null && imovel.getQuadra().getId() != null){
				imovelAlterado.setQuadra(imovel.getQuadra());
			}
			
			if (imovel.getLote() > 0){
				imovelAlterado.setLote(imovel.getLote());
			}
			
			if (imovel.getSubLote() > 0){
				imovelAlterado.setSubLote(imovel.getSubLote());
			}
			
			imovelAlterado.setUltimaAlteracao(new Date());
			
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				    Operacao.OPERACAO_IMOVEL_ATUALIZAR, imovelAlterado.getId(),
				    imovelAlterado.getId(),
				    new UsuarioAcaoUsuarioHelper(usuario,
				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		    registradorOperacao.registrarOperacao(imovelAlterado);
		    getControladorTransacao().registrarTransacao(imovelAlterado);
		    
			repositorioImovel.atualizarImovelInscricao(imovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
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

	public Collection filtrarImovelOutrosCriterios(String tipoMedicao)
			throws ControladorException {

		Collection<Imovel> collectionImovelOutrosCriterios = null;

		FiltroImovel filtroImovel = new FiltroImovel();

		if (tipoMedicao != null) {
			if (tipoMedicao.equals("1")) { // Tipo Medi��o correspondente
				// liga��o de �gua
				filtroImovel
						.adicionarParametro(new ParametroNaoNulo(
								FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_ID));
				collectionImovelOutrosCriterios = getControladorUtil()
						.pesquisar(filtroImovel, Imovel.class.getName());
			} else if (tipoMedicao.equals("2")) { // Tipo Medi��o
				// correspondente po�o
				filtroImovel
						.adicionarParametro(new ParametroNaoNulo(
								FiltroImovel.IMOVEL_HIDROMETRO_INSTALACAO_HISTORICO_ID));
				collectionImovelOutrosCriterios = getControladorUtil()
						.pesquisar(filtroImovel, Imovel.class.getName());
			} else if (tipoMedicao.equals("")) { // Tipo Medi��o
				// correspondente sem
				// medi��o
				filtroImovel
						.adicionarParametro(new ParametroNulo(
								FiltroImovel.IMOVEL_HIDROMETRO_INSTALACAO_HISTORICO_ID));
				collectionImovelOutrosCriterios = getControladorUtil()
						.pesquisar(filtroImovel, Imovel.class.getName());
			}
		}

		return collectionImovelOutrosCriterios;
	}

	public Collection<Imovel> pesquisarImovelParametrosClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		try {
			return this.repositorioImovel
					.pesquisarImovelParametrosClienteImovel(filtroClienteImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

//	public Integer verificarExistenciaImovelParaCliente(Integer idImovel)
//			throws ControladorException {
//		try {
//			return this.repositorioImovel
//					.verificarExistenciaImovelParaCliente(idImovel);
//		} catch (ErroRepositorioException ex) {
//			ex.printStackTrace();
//			throw new ControladorException("erro.sistema", ex);
//		}
//	}

	// ----- Metodo Para Carregar o Objeto ImovelMicromedicao
	// ----- Fl�vio Leonardo
	public Collection carregarImovelMicromedicao(Collection imoveisMicromedicao)
			throws ControladorException {

		Collection retorno = new ArrayList();
		ImovelMicromedicao imovelMicromedicao = null;
		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
		FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
		Collection pesquisaMedicao = new ArrayList();
		Collection pesquisaConsumo = new ArrayList();

		Iterator iterator = imoveisMicromedicao.iterator();
		while (iterator.hasNext()) {
			imovelMicromedicao = (ImovelMicromedicao) iterator.next();
			// ---- Ferifica se j� est� preenchido com medicaoHistorico
			if (imovelMicromedicao.getMedicaoHistorico() == null
					&& imovelMicromedicao.getImovel() != null
					&& imovelMicromedicao.getImovel().getId() != null) {
				filtroMedicaoHistorico.limparListaParametros();
				pesquisaMedicao = new ArrayList();
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota.faturamentoGrupo");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeInformada");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico");
				filtroMedicaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");

				if (imovelMicromedicao.getImovel() != null) {
					filtroMedicaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroMedicaoHistorico.IMOVEL_ID,
									imovelMicromedicao.getImovel().getId(),
									ParametroSimples.CONECTOR_OR));

					filtroMedicaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroMedicaoHistorico.LIGACAO_AGUA_ID,
									imovelMicromedicao.getImovel().getId()));
					if (imovelMicromedicao.getImovel() != null
							&& imovelMicromedicao.getImovel().getQuadra() != null
							&& imovelMicromedicao.getImovel().getQuadra()
									.getRota() != null
							&& imovelMicromedicao.getImovel().getQuadra()
									.getRota().getFaturamentoGrupo() != null
							&& imovelMicromedicao.getImovel().getQuadra()
									.getRota().getFaturamentoGrupo()
									.getAnoMesReferencia() != null) {
						filtroMedicaoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
										imovelMicromedicao.getImovel()
												.getQuadra().getRota()
												.getFaturamentoGrupo()
												.getAnoMesReferencia()));
					}

					pesquisaMedicao = getControladorUtil().pesquisar(
							filtroMedicaoHistorico,
							MedicaoHistorico.class.getName());
					if (!pesquisaMedicao.isEmpty()) {
						imovelMicromedicao
								.setMedicaoHistorico((MedicaoHistorico) pesquisaMedicao
										.iterator().next());
					}
				}
			}
			if (imovelMicromedicao.getConsumoHistorico() == null
					&& imovelMicromedicao.getImovel() != null
					&& imovelMicromedicao.getImovel().getId() != null) {
				filtroConsumoHistorico.limparListaParametros();
				filtroConsumoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoTipo");
				filtroConsumoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroConsumoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroConsumoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota.faturamentoGrupo");
				filtroConsumoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
				filtroConsumoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("consumoAnormalidade");
				filtroConsumoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico");
				if (imovelMicromedicao.getImovel() != null) {
					filtroConsumoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroConsumoHistorico.IMOVEL_ID,
									imovelMicromedicao.getImovel().getId()));
					if (imovelMicromedicao.getImovel() != null
							&& imovelMicromedicao.getImovel().getQuadra() != null
							&& imovelMicromedicao.getImovel().getQuadra()
									.getRota() != null
							&& imovelMicromedicao.getImovel().getQuadra()
									.getRota().getFaturamentoGrupo() != null
							&& imovelMicromedicao.getImovel().getQuadra()
									.getRota().getFaturamentoGrupo()
									.getAnoMesReferencia() != null) {
						filtroConsumoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroConsumoHistorico.ANO_MES_FATURAMENTO,
										imovelMicromedicao.getImovel()
												.getQuadra().getRota()
												.getFaturamentoGrupo()
												.getAnoMesReferencia()));
					}
					pesquisaConsumo = getControladorUtil().pesquisar(
							filtroConsumoHistorico,
							ConsumoHistorico.class.getName());

					if (!pesquisaConsumo.isEmpty()) {
						imovelMicromedicao
								.setConsumoHistorico((ConsumoHistorico) pesquisaConsumo
										.iterator().next());
					}
				}
			}
			if (imovelMicromedicao.getImovel() != null
					&& imovelMicromedicao.getImovel().getId() != null) {
				retorno.add(imovelMicromedicao);
			}
		}

		return retorno;
	}

	/**
	 * [UC0123] - Obter Descri�oes da Categoria do Im�vel Autor: S�vio Luiz
	 * Data: 27/12/2005
	 */

	public Categoria obterDescricoesCategoriaImovel(Imovel imovel)
			throws ControladorException {
		Collection descricoes = null;
		try {
			descricoes = repositorioImovel
					.obterDescricoesCategoriaImovel(imovel);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		Iterator descricoesIterator = descricoes.iterator();
		// Cria os objetos categoria
		Categoria categoria = new Categoria();
		boolean primeiraVez = true;
		boolean mesmaCategoria = false;
		String idCategoriaVerificar = null;
		String descricaoCategoria = null;
		String descricaoAbreviadaCategoria = null;

		while (descricoesIterator.hasNext()) {
			// cria um array de objetos para pegar os parametros de
			// retorno da pesquisa
			Object[] arrayDescricoes = (Object[]) descricoesIterator.next();

			if (arrayDescricoes[0] != null) {
				// id categoria
				if (primeiraVez) {
					idCategoriaVerificar = "" + arrayDescricoes[0];
					mesmaCategoria = true;
				} else {
					if (idCategoriaVerificar.equals("" + arrayDescricoes[0])) {
						mesmaCategoria = true;
					} else {
						mesmaCategoria = false;
					}
				}

			}
			// descri��o da categoria
			if (arrayDescricoes[1] != null) {

				if (primeiraVez) {
					descricaoCategoria = "" + arrayDescricoes[1];
				}
			}
			// descri��o abreviada da categoria
			if (arrayDescricoes[2] != null) {

				if (primeiraVez) {
					descricaoAbreviadaCategoria = "" + arrayDescricoes[2];
				}
			}

			primeiraVez = false;

		}
		if (mesmaCategoria) {
			categoria.setDescricao(descricaoCategoria);
			categoria.setDescricaoAbreviada(descricaoAbreviadaCategoria);
		} else {
			categoria.setDescricao("MISTO");
			categoria.setDescricaoAbreviada("MIS");
		}
		return categoria;
	}

	/**
	* [UC0185] Obter VAlor por Categoria 
	* Rateia um determinado valore entre as categorias do im�vel
	*
	* @author Rafael Santos, Ana Maria
	* @date 29/12/2005, 17/11/2008
	* @param colecaoCategorias
	* Cole��o de Categorias
	* @param valor
	* Valor
	* @return Cole��o com os valores por categorias
	* 
	*/
	public Collection obterValorPorCategoria(
	Collection<Categoria> colecaoCategorias, BigDecimal valor) {
		Collection colecaoValoresPorCategoria = new ArrayList();
	
		//acuama a quantidae de ecnomias das acategorias
		int somatorioQuantidadeEconomiasCadaCategoria = 0;
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
			Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();
		
			while (iteratorColecaoCategorias.hasNext()) {
				Categoria categoria = (Categoria) iteratorColecaoCategorias.next();
				somatorioQuantidadeEconomiasCadaCategoria = somatorioQuantidadeEconomiasCadaCategoria
				+ categoria.getQuantidadeEconomiasCategoria().intValue();
			}
	
		}
	
	//	 calcula o fator de multiplica��o
		BigDecimal fatorMultiplicacao = valor.divide(
		new BigDecimal(somatorioQuantidadeEconomiasCadaCategoria),2,BigDecimal.ROUND_DOWN);
	
		BigDecimal valorPorCategoriaAcumulado = new BigDecimal(0);
		
	
	//	 para cada categoria, calcula o Valor por Cageoria
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
			Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();
		
			while (iteratorColecaoCategorias.hasNext()) {
				Categoria categoria = (Categoria) iteratorColecaoCategorias.next();
			
				BigDecimal valorPorCategoria = new BigDecimal(0);
			
				valorPorCategoria = fatorMultiplicacao.multiply(new BigDecimal(
				categoria.getQuantidadeEconomiasCategoria()));
			
				BigDecimal valorTruncado = valorPorCategoria.setScale(2, BigDecimal.ROUND_DOWN);
			
				valorPorCategoriaAcumulado = valorPorCategoriaAcumulado.add(valorTruncado);
			
				colecaoValoresPorCategoria.add(valorTruncado);
			}
		}
		
		valorPorCategoriaAcumulado = valorPorCategoriaAcumulado.setScale(7);
	
		// caso o valor por categoria acumulado seja menor que o valor
		// acuma a diferen�a no valor por cageoria da primeira
		if (valorPorCategoriaAcumulado.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(
				valor.setScale(2, BigDecimal.ROUND_HALF_UP)) == -1) {
	
			BigDecimal diferenca = valor.subtract(valorPorCategoriaAcumulado);
		
			diferenca = diferenca.setScale(2, BigDecimal.ROUND_HALF_UP);
		
			BigDecimal categoriaPrimeira = (BigDecimal) colecaoValoresPorCategoria.iterator().next();
			
			categoriaPrimeira = categoriaPrimeira.add(diferenca);
		
			((ArrayList)colecaoValoresPorCategoria).set(0, categoriaPrimeira);
	
		}
	
		return colecaoValoresPorCategoria;

	}

	/**
	 * Atualiza uma categoria no sistema
	 * 
	 * @author Roberta Costa
	 * @date 30/12/2005
	 * @param categoria
	 *            Categoria a ser atualizada
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public void atualizarCategoria(Categoria categoria)
			throws ControladorException {

		// Valida��es
		if (categoria.getConsumoEstouro() < categoria.getConsumoMinimo()) {
			throw new ControladorException(
					"atencao.categoria.consumoEstouro_menor_consumoMinimo",
					null, "");
		}
		if (categoria.getVezesMediaEstouro().compareTo(new BigDecimal(1)) == -1) {
			throw new ControladorException(
					"atencao.categoria.vezesMediaEstouro_menor_um", null, "");
		}
		if (categoria.getPorcentagemMediaBaixoConsumo().shortValue() < 1) {
			throw new ControladorException(
					"atencao.categoria.porcentagemMediaBaixoConsumo_menor_um",
					null, "");
		}
		if (categoria.getMediaBaixoConsumo() < categoria.getConsumoMinimo()) {
			throw new ControladorException(
					"atencao.categoria.mediaBaixoConsumo_menor_consumoMinimo",
					null, "");
		}
		if (categoria.getConsumoAlto() < categoria.getConsumoMinimo()) {
			throw new ControladorException(
					"atencao.categoria.consumoAlto_menor_consumoMinimo", null,
					"");
		}
		if (categoria.getConsumoAlto() > categoria.getConsumoEstouro()) {
			throw new ControladorException(
					"atencao.categoria.consumoAlto_maior_consumoEstouro", null,
					"");
		}
		if (categoria.getVezesMediaAltoConsumo().compareTo(new BigDecimal(1)) == -1) {
			throw new ControladorException(
					"atencao.categoria.vezesMediaAltoConsumo_menor_um", null,
					"");
		}
		if (categoria.getVezesMediaAltoConsumo().compareTo(
				categoria.getVezesMediaEstouro()) == 1) {
			throw new ControladorException(
					"atencao.categoria.vezesMediaAltoConsumo_maior_vezesMediaEstouro",
					null, "");
		}

		// Verifica se a descri��o da Categoria j� foi cadastrada

		FiltroCategoria filtroCategoria = new FiltroCategoria();

		if (categoria.getDescricao() != null
				&& !categoria.getDescricao().equals("")) {

			filtroCategoria.adicionarParametro(new ComparacaoTexto(
					FiltroCategoria.DESCRICAO, categoria.getDescricao()));

			Collection categorias = this.getControladorUtil().pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (categorias != null && !categorias.isEmpty()) {
				Categoria categoriaNaBase = (Categoria) ((List) categorias)
						.get(0);
				if (!categoria.equals(categoriaNaBase)) {
					throw new ControladorException(
							"atencao.categoria_ja_existente", null, "descricao");
				}
			}
		}

		// -------------Parte que atualiza um cliente na
		// base----------------------

		// Parte de Validacao com Timestamp
		filtroCategoria.limparListaParametros();

		// Seta o filtro para buscar categoria na base
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.CODIGO, categoria.getId()));

		// Procura categoria na base
		Collection categoriaAtualizadas = getControladorUtil().pesquisar(
				filtroCategoria, Categoria.class.getName());

		Categoria categoriaAtualizada = (Categoria) Util
				.retonarObjetoDeColecao(categoriaAtualizadas);

		if (categoriaAtualizada == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente");
		}

		// Procura categoria na base
		Categoria categoriaNaBase = (Categoria) ((List) (getControladorUtil()
				.pesquisar(filtroCategoria, Categoria.class.getName()))).get(0);

		// Verificar se categoria j� foi atualizada por outro usu�rio durante
		// esta atualiza��o
		if (categoriaNaBase.getUltimaAlteracao().after(
				categoria.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Atualiza a data de �ltima altera��o
		categoria.setUltimaAlteracao(new Date());

		// Atualiza categoria
		getControladorUtil().atualizar(categoria);

		// -------------Fim da parte que atualiza uma categoria na
		// base---------------
	}

	/**
	 * Insere uma categoria no sistema
	 * 
	 * @author Roberta Costa
	 * @date 04/01/2006
	 * @param categoria
	 *            Categoria a ser inserida
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Integer inserirCategoria(Categoria categoria)
			throws ControladorException {

		// Valida��es
		if (categoria.getConsumoEstouro() < categoria.getConsumoMinimo()) {
			throw new ControladorException(
					"atencao.categoria.consumoEstouro_menor_consumoMinimo",
					null, "");
		}
		if (categoria.getVezesMediaEstouro().compareTo(new BigDecimal(1)) == -1) {
			throw new ControladorException(
					"atencao.categoria.vezesMediaEstouro_menor_um", null, "");
		}
		if (categoria.getMediaBaixoConsumo() < categoria.getConsumoMinimo()) {
			throw new ControladorException(
					"atencao.categoria.mediaBaixoConsumo_menor_consumoMinimo",
					null, "");
		}
		if (categoria.getPorcentagemMediaBaixoConsumo().compareTo(
				new BigDecimal("1")) == -1) {
			throw new ControladorException(
					"atencao.categoria.porcentagemMediaBaixoConsumo_menor_um",
					null, "");
		}
		if (categoria.getConsumoAlto() < categoria.getConsumoMinimo()) {
			throw new ControladorException(
					"atencao.categoria.consumoAlto_menor_consumoMinimo", null,
					"");
		}
		if (categoria.getConsumoAlto() > categoria.getConsumoEstouro()) {
			throw new ControladorException(
					"atencao.categoria.consumoAlto_maior_consumoEstouro", null,
					"");
		}
		if (categoria.getVezesMediaAltoConsumo().compareTo(new BigDecimal(1)) == -1) {
			throw new ControladorException(
					"atencao.categoria.vezesMediaAltoConsumo_menor_um", null,
					"");
		}
		if (categoria.getVezesMediaAltoConsumo().compareTo(
				categoria.getVezesMediaEstouro()) == 1) {
			throw new ControladorException(
					"atencao.categoria.vezesMediaAltoConsumo_maior_vezesMediaEstouro",
					null, "");
		}

		// Verifica se a descri��o da Categoria j� foi cadastrada
		FiltroCategoria filtroCategoria = new FiltroCategoria();

		if (categoria.getDescricao() != null
				&& !categoria.getDescricao().equals("")) {

			filtroCategoria.adicionarParametro(new ComparacaoTexto(
					FiltroCategoria.DESCRICAO, categoria.getDescricao()));

			Collection categorias = this.getControladorUtil().pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (categorias != null && !categorias.isEmpty()) {
				throw new ControladorException(
						"atencao.categoria_ja_existente", null, "descricao");
			}
		}

		// -------------Parte que insere uma categoria na
		// base----------------------

		Integer chaveCategoriaGerada = (Integer) getControladorUtil().inserir(
				categoria);

		categoria.setId(chaveCategoriaGerada);

		return chaveCategoriaGerada;
		// -------------Fim da parte que insere um categoria na
		// base---------------
	}

	/**
	 * Atualiza uma Subcategoria no sistema
	 * 
	 * [UC0059] Manter Subcategoria
	 * 
	 * @author Fernanda Paiva
	 * @date 09/01/2006
	 * 
	 * @param subcategoria
	 *            Subcategoria a ser atualizada
	 * 
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public void atualizarSubcategoria(Subcategoria subcategoria,
			Subcategoria subcategoriaVelha) throws ControladorException {

		FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

		// Valida��es
		if (subcategoria.getDescricao() != null
				&& !subcategoria.getDescricao().equals("")) {

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CODIGO, subcategoria.getCodigo()));

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID, subcategoria
							.getCategoria().getId()));

			filtroSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("categoria");

			Collection subcategorias = this.getControladorUtil().pesquisar(
					filtroSubcategoria, Subcategoria.class.getName());

			if (subcategorias != null && !subcategorias.isEmpty()) {

				Subcategoria subcategoriaNaBase = (Subcategoria) ((List) subcategorias)
						.get(0);
				if (!subcategoria.equals(subcategoriaNaBase)) {
					throw new ControladorException(
							"atencao.subcategoria_ja_existente", null, ""
									+ subcategoriaNaBase.getCodigo(),
							subcategoriaNaBase.getCategoria().getDescricao());
				}
			}
		}

		// -------------Parte que atualiza uma Subcategoria na
		// base----------------------

		// Parte de Validacao com Timestamp
		filtroSubcategoria.limparListaParametros();

		// Seta o filtro para buscar Subcategoria na base
		filtroSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.ID, subcategoria.getId()));

		Subcategoria subcategoriaNaBase = (Subcategoria) ((List) (getControladorUtil()
				.pesquisar(filtroSubcategoria, Subcategoria.class.getName())))
				.get(0);

		// Verificar se Subcategoria j� foi atualizada por outro usu�rio durante
		// esta atualiza��o
		if (subcategoriaNaBase.getUltimaAlteracao().after(
				subcategoria.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Atualiza a data de �ltima altera��o
		subcategoria.setUltimaAlteracao(new Date());

		// Atualiza Subcategoria
		getControladorUtil().atualizar(subcategoria);

		// -------------Fim da parte que atualiza uma subcategoria na
		// base---------------
	}

	/**
	 * [UC0164] Filtrar Imovel Por Outros Criterios
	 * 
	 * @author Rhawi Dantas
	 * @created 29/12/2005
	 * 
	 */
//	 public Collection pesquisarImovelOutrosCriterios(
//		 FiltrarImovelOutrosCriteriosHelper filtrarImovelOutrosCriteriosHelper) throws ControladorException {
//		 try {
//			 
//			 return this.repositorioImovel.pesquisarImovelOutrosCriterios(filtrarImovelOutrosCriteriosHelper);
//		 } catch (ErroRepositorioException ex) {
//			 ex.printStackTrace();
//			
//			 throw new ControladorException("erro.sistema", ex);
//		 }
//	 }

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialFaturamento(String valor,
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ControladorException {
		try {
			return this.repositorioImovel
					.pesquisarImovelSituacaoEspecialFaturamento(valor,
							situacaoEspecialFaturamentoHelper);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0177] Informar Situacao Especial de Cobranca
	 * 
	 * @author S�vio Luiz
	 * @created 07/03/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialCobranca(String valor,
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ControladorException {
		try {
			return this.repositorioImovel
					.pesquisarImovelSituacaoEspecialCobranca(valor,
							situacaoEspecialCobrancaHelper);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public int validarMesAnoReferencia(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ControladorException {
		try {
			return this.repositorioImovel
					.validarMesAnoReferencia(situacaoEspecialFaturamentoHelper);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa o maior ano mes de referencia da tabela de faturamento grupo
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public Integer validarMesAnoReferenciaCobranca(
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ControladorException {
		try {
			return this.repositorioImovel
					.validarMesAnoReferenciaCobranca(situacaoEspecialCobrancaHelper);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Collection<Categoria> pesquisarCategoria() throws ControladorException {
		try {
			return repositorioCategoria.pesquisarCategoria();
		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * 
	 * @return Quantidade de categorias cadastradas no BD
	 * @throws ControladorException
	 */
	public Object pesquisarObterQuantidadeCategoria()
			throws ControladorException {
		try {
			return repositorioCategoria.pesquisarObterQuantidadeCategoria();
		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public void atualizarFaturamentoSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idFaturamentoTipo, Usuario usuario) throws ControladorException {
		try {
			
			this.repositorioImovel.atualizarFaturamentoSituacaoTipo(colecaoIdsImoveis, idFaturamentoTipo);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Atualiza o id da cobran�a situa��o tipo da tabela im�vel com o id da
	 * situa��o escolhido pelo usuario
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void atualizarCobrancaSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idCobrancaTipo, Usuario usuario) throws ControladorException {
		
		try {
			this.repositorioImovel.atualizarCobrancaSituacaoTipo(colecaoIdsImoveis, idCobrancaTipo);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @throws SystemException 
	 * @throws IllegalStateException 
	 * 
	 */
	public void retirarSituacaoEspecialFaturamento(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper, 
													Collection pesquisarDadosImoveisParaSerRetirados) throws ControladorException {
		try {
			boolean retirar = true;
			
			Integer anoMesReferenciaFaturamentoGrupo = repositorioImovel.validarMesAnoReferencia(situacaoEspecialFaturamentoHelper);
			
			situacaoEspecialFaturamentoHelper.setAnoMesReferenciaFaturamentoGrupo(anoMesReferenciaFaturamentoGrupo);
			
			Collection colecaoImoveis = buildColecaoImoveis(pesquisarDadosImoveisParaSerRetirados);

			logger.info("Registrando a operacao de retirada de " + colecaoImoveis.size() + " imoveis "
							+ "da situacao especial de faturamento. Usuario=["
							+ situacaoEspecialFaturamentoHelper.getUsuarioLogado().getNomeUsuario() +"].");
			registrarOperacoes(situacaoEspecialFaturamentoHelper, colecaoImoveis);

			logger.info("Atualizando ano mes faturamento situacao historico para " + colecaoImoveis.size() + " imoveis.");
			Integer idFaturamentoSituacaoComando  = getControladorFaturamento().inserirFaturamentoSituacaoComando(situacaoEspecialFaturamentoHelper, retirar);
			this.repositorioFaturamento.atualizarAnoMesFaturamentoSituacaoHistorico(situacaoEspecialFaturamentoHelper, colecaoImoveis, idFaturamentoSituacaoComando);
				
			logger.info("Retirando da situacao especial de faturamento " + colecaoImoveis.size() + " imoveis.");
			this.repositorioImovel.retirarSituacaoEspecialFaturamento(colecaoImoveis);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	private void registrarOperacoes(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper, Collection colecaoImoveis) 
			throws ControladorException {
		Iterator iteraAnoMes = colecaoImoveis.iterator();
		
		while(iteraAnoMes.hasNext()){			
			String imov_id = iteraAnoMes.next().toString();
			registrarTransacao(situacaoEspecialFaturamentoHelper, imov_id);
		}
	}

	private void registrarTransacao(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper, String imov_id) throws ControladorException {
		
		Collection<FaturamentoSituacaoHistorico> colecaoFaturamento = pesquisarFaturamentoSituacaoHistoricoPorImovel(imov_id);
		
		if (colecaoFaturamento != null && !colecaoFaturamento.isEmpty()) {
			FaturamentoSituacaoHistorico fsh = colecaoFaturamento.iterator().next();
			
			fsh.setObservacaoRetira(situacaoEspecialFaturamentoHelper.getObservacaoRetira());
			fsh.setAnoMesFaturamentoRetirada(situacaoEspecialFaturamentoHelper.getAnoMesReferenciaFaturamentoGrupo());
			
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO,
					new Integer(imov_id), 
					new Integer(imov_id),
					new UsuarioAcaoUsuarioHelper(situacaoEspecialFaturamentoHelper.getUsuarioLogado(),
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(fsh);
			getControladorTransacao().registrarTransacao(fsh);
		}
	}
	
	private Collection<FaturamentoSituacaoHistorico> pesquisarFaturamentoSituacaoHistoricoPorImovel(String imov_id) throws ControladorException {
		FiltroFaturamentoSituacaoHistorico filtro = new FiltroFaturamentoSituacaoHistorico();
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,imov_id));
		
		Collection<FaturamentoSituacaoHistorico> colecaoFaturamento = getControladorUtil().pesquisar(filtro, FaturamentoSituacaoHistorico.class.getName());
		
		return colecaoFaturamento;
	}

	private Collection buildColecaoImoveis(Collection pesquisarDadosImoveisParaSerRetirados) throws ControladorException {
		
		Collection colecaoImoveis = new ArrayList();
		
		Iterator iterator = pesquisarDadosImoveisParaSerRetirados.iterator();
		
		while (iterator.hasNext()) {

			Object[] dadosImoveis = (Object[]) iterator.next();

			if (dadosImoveis != null) {
				Integer id = (Integer) dadosImoveis[0];

				Date ultimaAlteracao = (Date) dadosImoveis[1];
				Date ultimaAlteracaoImovel = this.pesquisarUltimaAlteracaoImovel(id);
				
				if (ultimaAlteracaoImovel.after(ultimaAlteracao)) {
					throw new ControladorException("atencao.atualizacao.timestamp");
				}

				colecaoImoveis.add(id);
			}
		}
		
		return colecaoImoveis;
	}

	/**
	 * Seta para null o id da cobran�a situa��o tipo da tabela im�vel
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void retirarSituacaoEspecialCobranca(SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper,
			Collection imoveisParaSerRetirados,Usuario usuario)throws ControladorException {
		try {
			
			boolean retirar = true;
			
			Integer idCobrancaSituacaoComando  = getControladorCobranca().inserirCobrancaSituacaoComando(situacaoEspecialCobrancaHelper,retirar);
						
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			Collection colecaoImoveis = null;
			
			Iterator iterator = imoveisParaSerRetirados.iterator();
			
			colecaoImoveis = new ArrayList();
			
			while (iterator.hasNext()) {

				Object[] dadosImoveis = (Object[]) iterator.next();

				if (dadosImoveis != null) {
					
					Integer id = (Integer) dadosImoveis[0];

					Date ultimaAlteracao = (Date) dadosImoveis[1];

					Date ultimaAlteracaoImovel = this.pesquisarUltimaAlteracaoImovel(id);
					
					// Controle de concorrencia
					if (ultimaAlteracaoImovel.after(ultimaAlteracao)) {
						throw new ControladorException(
								"atencao.atualizacao.timestamp");
					}

					// Adiciona os imoveis 
					colecaoImoveis.add(id);
					
				}
			
			}
			
			//-------------REGISTRAR TRANSA��O--------------------
			Iterator iteraAnoMes = colecaoImoveis.iterator();
			while(iteraAnoMes.hasNext()){			
				String imov_id = iteraAnoMes.next().toString();
				
				FiltroCobrancaSituacaoHistorico filtro = new FiltroCobrancaSituacaoHistorico();
				
				filtro.adicionarParametro(new ParametroSimples(
						FiltroCobrancaSituacaoHistorico.ID_IMOVEL,imov_id));
				
				Collection<CobrancaSituacaoHistorico> colecaoCobranca = getControladorUtil().pesquisar(filtro,CobrancaSituacaoHistorico.class.getName());
				
				CobrancaSituacaoHistorico csh = colecaoCobranca.iterator().next();	
				

				csh.setObservacaoRetira(situacaoEspecialCobrancaHelper.getObservacaoRetira());
				csh.setAnoMesCobrancaRetirada(sistemaParametro.getAnoMesFaturamento());
				
				RegistradorOperacao registradorOperacaoAnoMes = new RegistradorOperacao(
						Operacao.OPERACAO_RETIRAR_SITUACAO_ESPECIAL_COBRANCA,
						new Integer(imov_id),new Integer(imov_id),
						new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				
				registradorOperacaoAnoMes.registrarOperacao(csh);
								
				getControladorTransacao().registrarTransacao(csh);
			}
			//-------------REGISTRAR TRANSA��O--------------------	
			
			this.repositorioCobranca.atualizarAnoMesCobrancaSituacaoHistorico(
					situacaoEspecialCobrancaHelper,
					sistemaParametro.getAnoMesFaturamento(), colecaoImoveis,
					idCobrancaSituacaoComando);
		

			this.repositorioImovel
					.retirarSituacaoEspecialCobranca(colecaoImoveis);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Collection<Integer> pesquisarImoveisIds(Integer rotaId)
			throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImoveisIds(rotaId);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Object pesquisarImovelIdComConta(Integer imovelId,
			Integer anoMesReferencia) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImovelIdComConta(imovelId,
					anoMesReferencia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Integer verificarExistenciaImovel(Integer idImovel)
			throws ControladorException {
		try {
			return this.repositorioImovel.verificarExistenciaImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Permite efetuar o parcelamento dos d�bitos de um im�vel
	 * 
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 * 
	 * Obt�m as Op��es de Parcelamento do D�bito do Im�vel
	 * 
	 * [SF0002] Obter Op��es Parcelamento
	 * 
	 * Obt�m o perfil do im�vel
	 * 
	 * @author Roberta Costa
	 * @date 21/03/2006
	 * 
	 * @param situacaoAguaId
	 * @param situacaoEsgotoId
	 * @return ImovelSituacao
	 * @throws ControladorException
	 */
	public ImovelSituacao obterSituacaoImovel(Integer situacaoAguaId,
			Integer situacaoEsgotoId) throws ControladorException {

		FiltroImovelSituacao filtroImovelSituacao = new FiltroImovelSituacao();

		filtroImovelSituacao.adicionarParametro(new ParametroSimples(
				FiltroImovelSituacao.SITUACAO_AGUA_ID, situacaoAguaId));
		if (situacaoEsgotoId != null && !situacaoEsgotoId.equals("")) {
			filtroImovelSituacao
					.adicionarParametro(new ParametroSimples(
							FiltroImovelSituacao.SITUACAO_ESCGOTO_ID,
							situacaoEsgotoId));
		} else {
			filtroImovelSituacao.adicionarParametro(new ParametroNulo(
					FiltroImovelSituacao.SITUACAO_ESCGOTO_ID));
		}
		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");

		Collection<ImovelSituacao> colecaoImovelSituacao = this
				.getControladorUtil().pesquisar(filtroImovelSituacao,
						ImovelSituacao.class.getName());

		ImovelSituacao imovelSituacao = null;

		if (colecaoImovelSituacao != null && !colecaoImovelSituacao.isEmpty()) {
			// Pega a primeira ocorr�ncia da cole��o
			imovelSituacao = (ImovelSituacao) Util
					.retonarObjetoDeColecao(colecaoImovelSituacao);
		}

		return imovelSituacao;
	}

	/**
	 * Inseri uma cole��o de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Este fluxo secund�rio tem como objetivo pesquisar o im�vel digitado pelo
	 * usu�rio
	 * 
	 * [FS0008] - Verificar exist�ncia da matr�cula do im�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idImovelDigitado
	 * @return
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelDigitado(Integer idImovelDigitado)
			throws ControladorException {

		// Cria a vari�vel que vai armazenar o im�vel pesquisado
		Imovel imovelDigitado = null;

		// Pesquisa o im�vel informado pelo usu�rio no sistema
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		/*
		 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("lote");
		 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("subLote");
		 */
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				idImovelDigitado));
		Collection colecaoImovel = getControladorUtil().pesquisar(filtroImovel,
				Imovel.class.getName());

		// Caso exista o im�vel no sistema
		// Retorna para o usu�rio o im�vel retornado pela pesquisa
		// Caso contr�rio retorna um objeto nulo
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			imovelDigitado = (Imovel) Util
					.retonarObjetoDeColecao(colecaoImovel);
		}

		// Retorna o im�vel encontrado ou nulo se n�o existir
		return imovelDigitado;
	}

	/**
	 * Inseri uma cole��o de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se a localidade informada � a mesma do im�vel informado
	 * 
	 * [FS0009] - Verificar localidade da matr�cula do im�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idLocalidadeInformada
	 * @param imovelInformado
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarLocalidadeMatriculaImovel(
			String idLocalidadeInformada, Imovel imovelInformado)
			throws ControladorException {

		// Caso o im�vel tenha sido informado
		if (imovelInformado != null) {
			// Recupera a localidade do im�vel
			Localidade localidadeImovel = imovelInformado.getLocalidade();

			Integer codigoLocalidade = new Integer(idLocalidadeInformada);

			// Caso a localidade informada pelo usu�rio seja a mesma do im�vel
			// Retorna "true" indicandoque a localidade � a mesma
			// Caso contr�rio retorna "false" indicando que a localidade �
			// diferente
			if (codigoLocalidade.intValue() == localidadeImovel.getId()
					.intValue()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * Inseri uma cole��o de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se o usu�rio informou o im�vel ou o cliente, n�o pode existir os
	 * doi nem nenhum
	 * 
	 * [FS0010] Verificar preenchimento do im�vel e do cliente
	 * 
	 * @author Pedro Alexandre
	 * @date 24/03/2006
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @throws ControladorException
	 */
	public void verificarPreeenchimentoImovelECliente(String idImovel,
			String idCliente) throws ControladorException {

		// Caso o usu�rio n�o tenha informado o im�vel
		if (idImovel == null || idImovel.trim().equalsIgnoreCase("")) {
			// Caso o usu�rio n�o tenha informado o cliente, levanta uma exce��o
			// indicando que o
			// usu�rio n�o informou nem o im�vel nem o cliente
			if (idCliente == null || idCliente.trim().equalsIgnoreCase("")) {
				throw new ControladorException("atencao.naoinformado", null,
						"Matr�cula do Im�vel ou C�digo do Cliente");
			}
		} else {
			// Caso o usu�rio tenha informado o im�vel e o cliente, levanta uma
			// exce��o para
			// indicando que s� pode informar um dos dois
			if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
				throw new ControladorException(
						"atencao.cliente_imovel_informado");
			}
		}
	}

	/**
	 * [UC0224] Inserir Situa��o do im�vel
	 * 
	 * Verifica se o usu�rio informou o tipo da situa��o do im�vel
	 * 
	 * [FS0001] Verificar existencia de dados
	 * 
	 * @author R�mulo Aur�lio
	 * @date 29/03/2006
	 * 
	 * @param idImovelSituacaoTipo
	 * @param idLigacaoAguaSituacao
	 * @param idLigacaoEsgotoSituacao
	 * @throws ControladorException
	 */
	public Integer inserirSituacaoImovel(String idImovelSituacaoTipo,
			String idLigacaoAguaSituacao, String idLigacaoEsgotoSituacao)
			throws ControladorException {

		if (idImovelSituacaoTipo == null
				|| idImovelSituacaoTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException(
					"atencao.imovel_situacao_tipo_nao_informado");
		}
		if (idLigacaoAguaSituacao == null
				|| idLigacaoAguaSituacao.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException(
					"atencao.imovel_situacao_agua_ligacao_nao_informado");
		}

		if (idLigacaoEsgotoSituacao.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			idLigacaoEsgotoSituacao = null;
		}

		FiltroImovelSituacao filtroImovelSituacao = new FiltroImovelSituacao();
		filtroImovelSituacao.adicionarParametro(new ParametroSimples(
				FiltroImovelSituacao.SITUACAO_TIPO, idImovelSituacaoTipo));
		filtroImovelSituacao.adicionarParametro(new ParametroSimples(
				FiltroImovelSituacao.SITUACAO_AGUA_ID, idLigacaoAguaSituacao));
		if (idLigacaoEsgotoSituacao != null) {
			filtroImovelSituacao.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacao.SITUACAO_ESCGOTO_ID,
					idLigacaoEsgotoSituacao));
		} else {
			filtroImovelSituacao.adicionarParametro(new ParametroNulo(
					FiltroImovelSituacao.SITUACAO_ESCGOTO_ID));
		}

		Collection colecaoImovelSituacao = getControladorUtil().pesquisar(
				filtroImovelSituacao, ImovelSituacao.class.getName());

		if (colecaoImovelSituacao != null && !colecaoImovelSituacao.isEmpty()) {
			throw new ControladorException(
					"atencao.imovel_situacao_ja_existe_no_cadastro");
		}

		ImovelSituacaoTipo imovelSituacaoTipo = new ImovelSituacaoTipo();
		imovelSituacaoTipo.setId(new Integer(idImovelSituacaoTipo));

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(new Integer(idLigacaoAguaSituacao));

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		ImovelSituacao imovelSituacao = new ImovelSituacao();

		if (idLigacaoEsgotoSituacao != null
				&& !idLigacaoEsgotoSituacao.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			ligacaoEsgotoSituacao.setId(new Integer(idLigacaoEsgotoSituacao));
			imovelSituacao.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

		}

		imovelSituacao.setImovelSituacaoTipo(imovelSituacaoTipo);
		imovelSituacao.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		imovelSituacao.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSA��O----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_IMOVEL_SITUACAO_INSERIR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_IMOVEL_SITUACAO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		imovelSituacao.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(imovelSituacao);

		// ------------ REGISTRAR TRANSA��O----------------------------

		Integer idImovelSituacao = (Integer) getControladorUtil().inserir(
				imovelSituacao);

		return idImovelSituacao;

	}

	/**
	 * Obt�m a principal categoria do im�vel
	 * 
	 * [UC0306] Obter Principal Categoria do Im�vel
	 * 
	 * @author Pedro Alexandre, Ivan S�rgio
	 * @date 18/04/2006, 14/08/2007
	 * @alteracao - Correcao no [FS0001 - Verificar mais de uma categoria...]
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public Categoria obterPrincipalCategoriaImovel(Integer idImovel)
			throws ControladorException {
		// Cria a vari�vel que vai armazenar a categoria principal do im�vel
		Categoria categoriaPrincipal = null;
		
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		// Pesquisa o im�vel informado
		// Imovel imovel = this.pesquisarImovelDigitado(idImovel);

		// Cria a cole��o que vai armazenar as categorias com maiorquantidade de
		// economias
		Collection<Categoria> colecaoCategoriasComMaiorQtdEconomias = new ArrayList();

		// [UC0108] Obt�m a quantidade de economias por categoria
		Collection<Categoria> colecaoCategoriasImovel = this
				.obterQuantidadeEconomiasCategoria(idImovel);

		// Inicializa a quantidade de categoria
		int quantidadeCategoria = -1;

		// Caso a cole��o de categorias do im�vel n�o esteja nula
		if (colecaoCategoriasImovel != null) {
			// La�o para verificar qual a categoria com maior quantidade de
			// economia
			for (Categoria categoriaImovel : colecaoCategoriasImovel) {
				if (quantidadeCategoria < categoriaImovel
						.getQuantidadeEconomiasCategoria().intValue()) {
					quantidadeCategoria = categoriaImovel
							.getQuantidadeEconomiasCategoria().intValue();

					colecaoCategoriasComMaiorQtdEconomias = new ArrayList();
					colecaoCategoriasComMaiorQtdEconomias.add(categoriaImovel);
				} else if (quantidadeCategoria == categoriaImovel
						.getQuantidadeEconomiasCategoria().intValue()) {
					colecaoCategoriasComMaiorQtdEconomias.add(categoriaImovel);
				}
			}
		}

		// [FS0001]Verificar mais de uma categoria com a maior quantidade de
		// economias

		// Caso s� exista um objeto na cole��o, recuperaa categoria a atribui a
		// categoria principal
		// Caso contr�rio recupera a categoria com o menor id
		if (colecaoCategoriasComMaiorQtdEconomias.size() == 1) {
			categoriaPrincipal = colecaoCategoriasComMaiorQtdEconomias
					.iterator().next();
		} else if (colecaoCategoriasComMaiorQtdEconomias.size() > 1) {
			/*
			 * for (Categoria categoriaImovel :
			 * colecaoCategoriasComMaiorQtdEconomias) { int idTemp = -1; if
			 * (idTemp < categoriaImovel.getId().intValue()) { idTemp =
			 * categoriaImovel.getId().intValue(); categoriaPrincipal =
			 * categoriaImovel; } }
			 */
			int idTemp = -1;
			if(!sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)){
				for (Categoria categoriaImovel : colecaoCategoriasComMaiorQtdEconomias) {
					if (idTemp == -1) {
						idTemp = categoriaImovel.getId().intValue();
						categoriaPrincipal = categoriaImovel;
					} else if (categoriaImovel.getId().intValue() < idTemp) {
						idTemp = categoriaImovel.getId().intValue();
						categoriaPrincipal = categoriaImovel;
					}
				}
			}else{
				for (Categoria categoriaImovel : colecaoCategoriasComMaiorQtdEconomias) {
					if (idTemp == -1) {
						idTemp = categoriaImovel.getId().intValue();
						categoriaPrincipal = categoriaImovel;
					} else if (categoriaImovel.getId().intValue() > idTemp) {
						idTemp = categoriaImovel.getId().intValue();
						categoriaPrincipal = categoriaImovel;
					}
				}
			}
			
		}

		// Retorna a categoria principal
		return categoriaPrincipal;
	}

	/**
	 * Obt�m o indicador de exist�ncia de hidr�metro para o im�vel, caso exista
	 * retorna 1(um) indicando SIM caso contr�rio retorna 2(dois) indicando N�O
	 * 
	 * [UC0307] Obter Indicador de Exist�ncia de Hidr�metro no Im�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 18/04/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public int obterIndicadorExistenciaHidrometroImovel(Integer idImovel)
			throws ControladorException {

		// Inicia o indicador para 2(dois) N�O
		int retorno = 2;

		try {

			Integer indicador = this.repositorioImovel
					.obterIndicadorExistenciaHidrometroImovel(idImovel);

			if (indicador != null) {
				retorno = indicador.intValue();
			}

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		// // Pesquisa o im�vel informado
		// Imovel imovel = this.pesquisarImovelDigitado(idImovel);
		//
		// // Caso a situa��o da liga��o de �gua do im�vel seja igual a "Ligado"
		// ou
		// // "Cortado" e
		// // exista hidr�metro instalado na liga��o de �gua, atribui 1(um)
		// // indicando SIM
		// // Caso contr�rio e caso a situa��o da liga��o de esgoto corresponda
		// a
		// // "Ligado" e exista hidr�metro
		// // instalado no po�o, atribui 1(um) indicando SIM
		// if ((imovel.getLigacaoAguaSituacao().getId() ==
		// LigacaoAguaSituacao.LIGADO || imovel
		// .getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.CORTADO)
		// && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() !=
		// null) {
		// indicadorExistenciaHidrometro = 1;
		// } else if (imovel.getLigacaoEsgotoSituacao().getId() ==
		// LigacaoEsgotoSituacao.LIGADO
		// && imovel.getHidrometroInstalacaoHistorico() != null) {
		// indicadorExistenciaHidrometro = 1;
		// }

		// Retorna o indicador de exist�ncia de hidr�metro para o im�vel
		return retorno;
	}

	public Collection pesquisarSubcategoriasImovelRelatorio(Integer idImovel)
			throws ControladorException {

		Collection retorno = null;

		try {

			retorno = this.repositorioImovel
					.pesquisarSubcategoriasImovelRelatorio(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * Gerar Relat�rio de Im�veis Outros Crit�rios
	 * 
	 * @author Rafael Corr�a
	 * @date 25/07/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarRelatorioImovelOutrosCriterios(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal) throws ControladorException {

		Collection colecaoImoveis = null;

		try {
			// remove primeiro as linhas do crit�rio cobran�a
			colecaoImoveis = repositorioImovel
					.gerarRelatorioImovelOutrosCriterios(idImovelCondominio,
							idImovelPrincipal, idSituacaoLigacaoAgua,
							consumoMinimoInicialAgua, consumoMinimoFinalAgua,
							idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,

							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,

							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa, idUnidadeNegocio,cdRotaInicial,
							cdRotaFinal,sequencialRotaInicial,
							sequencialRotaFinal);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// colecao com os dados para o relatorio
		Collection colecaoGerarRelatorioImovelOutrosCriterios = null;

		if (colecaoImoveis == null || colecaoImoveis.isEmpty()) {
			throw new ControladorException(
					"atencao.filtro_consumo_tarifa_sem_records");
		}

		// para cada imovel pega as conta, debitos, creditos e guias
		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

			Iterator iteratorColecaoImoveis = colecaoImoveis.iterator();
			colecaoGerarRelatorioImovelOutrosCriterios = new ArrayList();

			ImovelRelatorioHelper imovelRelatorioHelper = null;
			// GerarRelacaoDebitosHelper gerarRelacaoDebitosHelper = null;
			while (iteratorColecaoImoveis.hasNext()) {

				Object[] contasDadosRelatorio = (Object[]) iteratorColecaoImoveis
						.next();

				imovelRelatorioHelper = new ImovelRelatorioHelper();

				// gerarRelacaoDebitosHelper = new GerarRelacaoDebitosHelper();

				// id gerencia regional
				if (contasDadosRelatorio[0] != null) { // 0
					imovelRelatorioHelper
							.setIdGerenciaRegional(((Integer) contasDadosRelatorio[0]));
				}
				// nome abreviado gerencia regional
				if (contasDadosRelatorio[1] != null) { // 1
					imovelRelatorioHelper
							.setDescricaoGerenciaRegional((String) contasDadosRelatorio[1]);
				}
				// id localidade
				if (contasDadosRelatorio[2] != null) { // 2
					imovelRelatorioHelper
							.setIdLocalidade(((Integer) contasDadosRelatorio[2]));
				}
				// descricao localidade
				if (contasDadosRelatorio[3] != null) { // 3
					imovelRelatorioHelper
							.setDescricaoLocalidade((String) contasDadosRelatorio[3]);
				}
				// id imovel
				if (contasDadosRelatorio[4] != null) { // 4
					imovelRelatorioHelper
							.setMatriculaImovel(((Integer) contasDadosRelatorio[4]));
				}
				// quantidade de economias
				if (contasDadosRelatorio[5] != null) { // 5
					imovelRelatorioHelper.setQuantidadeEconomia(new Integer(
							((Short) contasDadosRelatorio[5])));
				}
				// codigo setor comercial
				if (contasDadosRelatorio[6] != null) { // 6
					imovelRelatorioHelper
							.setCodigoSetorComercial(((Integer) contasDadosRelatorio[6]));
				}
				// descri��o setor comercial
				if (contasDadosRelatorio[7] != null) { // 7
					imovelRelatorioHelper
							.setDescricaoSetorComercial(((String) contasDadosRelatorio[7]));
				}
				// numero quadra
				if (contasDadosRelatorio[8] != null) { // 8
					imovelRelatorioHelper
							.setNumeroQuadra(((Integer) contasDadosRelatorio[8]));
				}
				// lote
				if (contasDadosRelatorio[9] != null) { // 9
					imovelRelatorioHelper
							.setNumeroLote(((Short) contasDadosRelatorio[9]));
				}
				// sub lote
				if (contasDadosRelatorio[10] != null) { // 10
					imovelRelatorioHelper
							.setNumeroSubLote(((Short) contasDadosRelatorio[10]));
				}
				// descricao ligacao agua situacao
				if (contasDadosRelatorio[11] != null) { // 11
					imovelRelatorioHelper
							.setLigacaoAguaSituacao((String) contasDadosRelatorio[11]);
				}
				// descricao ligacao esgoto situacao
				if (contasDadosRelatorio[12] != null) { // 12
					imovelRelatorioHelper
							.setLigacaoEsgotoSituacao((String) contasDadosRelatorio[12]);
				}
				// percentual
				if (contasDadosRelatorio[13] != null) { // 13
					imovelRelatorioHelper
							.setPercentual((BigDecimal) contasDadosRelatorio[13]);
				}
				// data liga��o esgoto
				if (contasDadosRelatorio[14] != null) { // 14
					imovelRelatorioHelper
							.setDataLigacaoEsgoto((Date) contasDadosRelatorio[14]);
				}
				// data liga��o �gua
				if (contasDadosRelatorio[15] != null) { // 15
					imovelRelatorioHelper
							.setDataLigacaoAgua((Date) contasDadosRelatorio[15]);
				}
				// c�digo cliente usuario
				if (contasDadosRelatorio[16] != null) { // 16
					imovelRelatorioHelper
							.setClienteUsuarioId((Integer) contasDadosRelatorio[16]);
				}
				// nome cliente usuario
				if (contasDadosRelatorio[17] != null) { // 17
					imovelRelatorioHelper
							.setClienteUsuarioNome((String) contasDadosRelatorio[17]);
				}
				// nome cliente resposanvel
				if (contasDadosRelatorio[18] != null) { // 18
					imovelRelatorioHelper
							.setClienteResponsavelId((Integer) contasDadosRelatorio[18]);
				}
				// nome cliente resposanvel
				if (contasDadosRelatorio[19] != null) { // 19
					imovelRelatorioHelper
							.setClienteResponsavelNome((String) contasDadosRelatorio[19]);
				}
				// nome logradouro
				if (contasDadosRelatorio[20] != null) { // 20
					imovelRelatorioHelper
							.setNomeLogradouro(((String) contasDadosRelatorio[20]));
				}
				// tipo logradouro
				if (contasDadosRelatorio[21] != null) { // 21
					imovelRelatorioHelper
							.setTipoLogradouro((String) contasDadosRelatorio[21]);
				}
				// titulo logradouro
				if (contasDadosRelatorio[22] != null) { // 22
					imovelRelatorioHelper
							.setTituloLogradouro((String) contasDadosRelatorio[22]);
				}
				// cep
				if (contasDadosRelatorio[23] != null) { // 23
					Cep cepImovel = new Cep();
					cepImovel.setCodigo((Integer) contasDadosRelatorio[23]);
					imovelRelatorioHelper.setCepFormatado(cepImovel
							.getCepFormatado());
				}
				// endere�o refer�ncia
				if (contasDadosRelatorio[24] != null) { // 24
					imovelRelatorioHelper
							.setComplementoImovel((String) contasDadosRelatorio[24]);
				}

				// comlplemente endereco
				if (contasDadosRelatorio[25] != null) { // 25
					imovelRelatorioHelper
							.setComplementoImovel((String) contasDadosRelatorio[25]);
				}

				// n�mero im�vel
				if (contasDadosRelatorio[26] != null) { // 26
					imovelRelatorioHelper
							.setNumeroImovel((String) contasDadosRelatorio[26]);
				}
				// nome bairro
				if (contasDadosRelatorio[27] != null) { // 27
					imovelRelatorioHelper
							.setNomeBairro((String) contasDadosRelatorio[27]);
				}
				// nome munic�pio
				if (contasDadosRelatorio[28] != null) { // 28
					imovelRelatorioHelper
							.setNomeMunicipio((String) contasDadosRelatorio[28]);
				}
				// sigla uf
				if (contasDadosRelatorio[29] != null) { // 29
					imovelRelatorioHelper
							.setUnidadeFederacao((String) contasDadosRelatorio[29]);
				}
				// indicador im�vel condom�nio
				if (contasDadosRelatorio[30] != null) { // 30
					imovelRelatorioHelper
							.setIndicadorImovelCondominio(((Integer) contasDadosRelatorio[30])
									.shortValue());
				}

				// n�mero moradores
				if (contasDadosRelatorio[31] != null) { // 31
					imovelRelatorioHelper
							.setNumeroMoradores(((Integer) contasDadosRelatorio[31])
									.shortValue());
				}
				// matr�cula im�vel condom�nio
				if (contasDadosRelatorio[32] != null) { // 32
					imovelRelatorioHelper
							.setMatriculaImovelCondominio((Integer) contasDadosRelatorio[32]);
				}
				// matr�cula im�vel principal
				if (contasDadosRelatorio[33] != null) { // 33
					imovelRelatorioHelper
							.setMatriculaImovelPrincipal((Integer) contasDadosRelatorio[33]);
				}
				// n�mero pontos utiliza��o
				if (contasDadosRelatorio[34] != null) { // 34
					imovelRelatorioHelper
							.setNumeroPontosUtilzacao(((Integer) contasDadosRelatorio[34])
									.shortValue());
				}
				// perfil im�vel
				if (contasDadosRelatorio[35] != null) { // 35
					imovelRelatorioHelper
							.setPerfilImovel((String) contasDadosRelatorio[35]);
				}
				// �rea constru�da maior faixa
				if (contasDadosRelatorio[36] != null) { // 36
					imovelRelatorioHelper
							.setAreaConstruidaMaiorFaixa((Integer) contasDadosRelatorio[36]);
				}
				// �rea constru�da menor faixa
				if (contasDadosRelatorio[37] != null) { // 37
					imovelRelatorioHelper
							.setAreaConstruidaMenorFaixa((Integer) contasDadosRelatorio[37]);
				}
				// �rea constru�da
				if (contasDadosRelatorio[38] != null) { // 38
					imovelRelatorioHelper
							.setAreaConstruidaImovel((BigDecimal) contasDadosRelatorio[38]);
				}
				// pavimento cal�ada
				if (contasDadosRelatorio[39] != null) { // 39
					imovelRelatorioHelper
							.setTipoPavimentoCalcada((String) contasDadosRelatorio[39]);
				}
				// pavimento rua
				if (contasDadosRelatorio[40] != null) { // 40
					imovelRelatorioHelper
							.setTipoPavimentoRua((String) contasDadosRelatorio[40]);
				}

				// despejo
				if (contasDadosRelatorio[41] != null) { // 41
					imovelRelatorioHelper
							.setTipoDespejo(((String) contasDadosRelatorio[41]));
				}
				// volume reservatorio superior menor faixa
				if (contasDadosRelatorio[42] != null) { // 42
					imovelRelatorioHelper
							.setVolumeReservatorioSuperiorMenorFaixa((BigDecimal) contasDadosRelatorio[42]);
				}
				// volume reservatorio superior maior faixa
				if (contasDadosRelatorio[43] != null) { // 43
					imovelRelatorioHelper
							.setVolumeReservatorioSuperiorMaiorFaixa((BigDecimal) contasDadosRelatorio[43]);
				}
				// volume reservatorio superior
				if (contasDadosRelatorio[44] != null) { // 44
					imovelRelatorioHelper
							.setVolumeReservatorioSuperior((BigDecimal) contasDadosRelatorio[44]);
				}
				// volume reservatorio inferior menor faixa
				if (contasDadosRelatorio[45] != null) { // 45
					imovelRelatorioHelper
							.setVolumeReservatorioInferiorMenorFaixa((BigDecimal) contasDadosRelatorio[45]);
				}
				// volume reservatorio inferior maior faixa
				if (contasDadosRelatorio[46] != null) { // 46
					imovelRelatorioHelper
							.setVolumeReservatorioInferiorMaiorFaixa((BigDecimal) contasDadosRelatorio[46]);
				}
				// volume reservatorio inferior
				if (contasDadosRelatorio[47] != null) { // 47
					imovelRelatorioHelper
							.setVolumeReservatorioInferior((BigDecimal) contasDadosRelatorio[47]);
				}
				// volume piscina menor faixa
				if (contasDadosRelatorio[48] != null) { // 48
					imovelRelatorioHelper
							.setVolumePiscinaMenorFaixa((BigDecimal) contasDadosRelatorio[48]);
				}
				// volume piscina maior faixa
				if (contasDadosRelatorio[49] != null) { // 49
					imovelRelatorioHelper
							.setVolumePiscinaMaiorFaixa((BigDecimal) contasDadosRelatorio[49]);
				}
				// volume piscina
				if (contasDadosRelatorio[50] != null) { // 50
					imovelRelatorioHelper
							.setVolumePiscina((BigDecimal) contasDadosRelatorio[50]);
				}

				// tipo de po�o
				if (contasDadosRelatorio[51] != null) { // 51
					imovelRelatorioHelper
							.setDescricaoTipoPoco(((String) contasDadosRelatorio[51]));
				}
				// di�metro da liga��o de �gua
				if (contasDadosRelatorio[52] != null) { // 52
					imovelRelatorioHelper
							.setDiametroLigacaoAgua((String) contasDadosRelatorio[52]);
				}
				// material da liga��o de �gua
				if (contasDadosRelatorio[53] != null) { // 53
					imovelRelatorioHelper
							.setMaterialLigacaoAgua((String) contasDadosRelatorio[53]);
				}
				// di�metro da liga��o de esgoto
				if (contasDadosRelatorio[54] != null) { // 54
					imovelRelatorioHelper
							.setDiametroLigacaoEsgoto((String) contasDadosRelatorio[54]);
				}
				// material da liga��o de esgoto
				if (contasDadosRelatorio[55] != null) { // 55
					imovelRelatorioHelper
							.setMaterialLigacaoEsgoto((String) contasDadosRelatorio[55]);
				}
				// consumo m�nimo esgoto
				if (contasDadosRelatorio[56] != null) { // 56
					imovelRelatorioHelper
							.setConsumoMinimoFixadoLigacaoEsgoto((Integer) contasDadosRelatorio[56]);
				}
				// percentual �gua coletada
				if (contasDadosRelatorio[57] != null) { // 57
					imovelRelatorioHelper
							.setPercentualAguaConsumidaColetada((BigDecimal) contasDadosRelatorio[57]);
				}
				// percentual esgoto
				if (contasDadosRelatorio[58] != null) { // 58
					imovelRelatorioHelper
							.setPercentual((BigDecimal) contasDadosRelatorio[58]);
				}
				// n�mero hidr�metro �gua
				if (contasDadosRelatorio[59] != null) { // 59
					imovelRelatorioHelper
							.setNumeroHidrometroAgua((String) contasDadosRelatorio[59]);
				}
				// ano fabrica��o hidr�metro �gua
				if (contasDadosRelatorio[60] != null) { // 60
					imovelRelatorioHelper
							.setAnoFabricaocaHidrometroAgua(((Integer) contasDadosRelatorio[60])
									.shortValue());
				}
				// capacidade hidr�metro �gua
				if (contasDadosRelatorio[61] != null) { // 61
					imovelRelatorioHelper
							.setCapacidadeHidrometroAgua(((String) contasDadosRelatorio[61]));
				}
				// marca hidr�metro �gua
				if (contasDadosRelatorio[62] != null) { // 62
					imovelRelatorioHelper
							.setMarcaHidrometroAgua((String) contasDadosRelatorio[62]);
				}
				// di�metro hidr�metro �gua
				if (contasDadosRelatorio[63] != null) { // 63
					imovelRelatorioHelper
							.setDiametroHidrometroAgua((String) contasDadosRelatorio[63]);
				}
				// tipo hidr�metro �gua
				if (contasDadosRelatorio[64] != null) { // 64
					imovelRelatorioHelper
							.setTipoHidrometroAgua((String) contasDadosRelatorio[64]);
				}
				// data instala��o hidr�metro �gua
				if (contasDadosRelatorio[65] != null) { // 65
					imovelRelatorioHelper
							.setDataInstalacaoHidrometroAgua((Date) contasDadosRelatorio[65]);
				}
				// local instala��o hidr�metro �gua
				if (contasDadosRelatorio[66] != null) { // 66
					imovelRelatorioHelper
							.setLocalInstalacaoHidrometroAgua((String) contasDadosRelatorio[66]);
				}
				// prote��o hidr�metro �gua
				if (contasDadosRelatorio[67] != null) { // 67
					imovelRelatorioHelper
							.setTipoProtecaoHidrometroAgua((String) contasDadosRelatorio[67]);
				}
				// indicador cavalete hidr�metro �gua
				if (contasDadosRelatorio[68] != null) { // 68
					imovelRelatorioHelper
							.setIndicadorExistenciaCavaleteAgua(((Integer) contasDadosRelatorio[68])
									.shortValue());
				}
				// n�mero hidr�metro po�o
				if (contasDadosRelatorio[69] != null) { // 69
					imovelRelatorioHelper
							.setNumeroHidrometroPoco((String) contasDadosRelatorio[69]);
				}
				// ano fabrica��o hidr�metro po�o
				if (contasDadosRelatorio[70] != null) { // 70
					imovelRelatorioHelper
							.setAnoFabricacaoHidrometroPoco(((Integer) contasDadosRelatorio[70])
									.shortValue());
				}
				// capacidade hidr�metro po�o
				if (contasDadosRelatorio[71] != null) { // 71
					imovelRelatorioHelper
							.setCapacidadeHidrometroPoco(((String) contasDadosRelatorio[71]));
				}
				// marca hidr�metro po�o
				if (contasDadosRelatorio[72] != null) { // 72
					imovelRelatorioHelper
							.setMarcaHidrometroPoco((String) contasDadosRelatorio[72]);
				}
				// di�metro hidr�metro po�o
				if (contasDadosRelatorio[73] != null) { // 73
					imovelRelatorioHelper
							.setDiametroHidrometroPoco((String) contasDadosRelatorio[73]);
				}
				// tipo hidr�metro po�o
				if (contasDadosRelatorio[74] != null) { // 74
					imovelRelatorioHelper
							.setTipoHidrometroPoco((String) contasDadosRelatorio[74]);
				}
				// data instala��o hidr�metro po�o
				if (contasDadosRelatorio[75] != null) { // 75
					imovelRelatorioHelper
							.setDataInstalacaoHidrometroPoco((Date) contasDadosRelatorio[75]);
				}
				// local instala��o hidr�metro po�o
				if (contasDadosRelatorio[76] != null) { // 76
					imovelRelatorioHelper
							.setLocalInstalacaoHidrometroPoco((String) contasDadosRelatorio[76]);
				}
				// prote��o hidr�metro po�o
				if (contasDadosRelatorio[77] != null) { // 77
					imovelRelatorioHelper
							.setTipoProtecaoHidrometroPoco((String) contasDadosRelatorio[77]);
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[78] != null) { // 78
					imovelRelatorioHelper
							.setIndicadorExistenciaCavaletePoco(((Integer) contasDadosRelatorio[78])
									.shortValue());
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[79] != null) { // 79
					imovelRelatorioHelper
							.setConsumoMinimoFixadoAgua((Integer) contasDadosRelatorio[79]);
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[80] != null) { // 80
					imovelRelatorioHelper
							.setConsumoMinimoFixadoLigacaoEsgoto((Integer) contasDadosRelatorio[80]);
				}
				// indicador Jardim
				if (contasDadosRelatorio[81] != null) { // 81
					imovelRelatorioHelper
							.setJardim(((Short) contasDadosRelatorio[81])
									.toString());
				}

				// Rota
				if (contasDadosRelatorio[82] != null) { // 82
					imovelRelatorioHelper
							.setCodigoRota((Short) contasDadosRelatorio[82]);
				}

				// N�mero Sequencial Rota
				if (contasDadosRelatorio[83] != null) { // 83
					imovelRelatorioHelper
							.setNumeroSequencialRota((Integer) contasDadosRelatorio[83]);
				}

				// id do Logradouro
				if (contasDadosRelatorio[84] != null) { // 84
					imovelRelatorioHelper.setIdLogradouro(""
							+ (Integer) contasDadosRelatorio[84]);
				}

				// consumo Medio
				Integer consumoMedio = getControladorCobranca()
						.pesquisarConsumoMedioConsumoHistoricoImovel(
								new Integer(imovelRelatorioHelper
										.getMatriculaImovel()));
				
				if (consumoMedio != null) {
					imovelRelatorioHelper.setConsumoMedioImovel(consumoMedio);
				}

				Imovel imovel = new Imovel();

				Localidade localidadeImovel = new Localidade();
				localidadeImovel.setId(imovelRelatorioHelper.getIdLocalidade());
				SetorComercial setorComercialImovel = new SetorComercial();
				setorComercialImovel.setCodigo(imovelRelatorioHelper
						.getCodigoSetorComercial().intValue());
				Quadra quadraImovel = new Quadra();
				quadraImovel.setNumeroQuadra(imovelRelatorioHelper
						.getNumeroQuadra().intValue());

				imovel.setLocalidade(localidadeImovel);
				imovel.setSetorComercial(setorComercialImovel);
				imovel.setQuadra(quadraImovel);
				imovel.setLote(new Short(imovelRelatorioHelper.getNumeroLote())
						.shortValue());
				imovel.setSubLote(new Short(imovelRelatorioHelper
						.getNumeroSubLote()).shortValue());

				// inscricao formatada do imovel
				imovelRelatorioHelper.setInscricaoImovel(imovel
						.getInscricaoFormatada());

				// obter endereco
				imovelRelatorioHelper.setEndereco(getControladorEndereco()
						.pesquisarEndereco(
								new Integer(imovelRelatorioHelper
										.getMatriculaImovel())));

				Collection colecaoSubcategoria = this
						.pesquisarSubcategoriasImovelRelatorio(imovelRelatorioHelper
								.getMatriculaImovel());

				// ------subcategoria
				String[] arraySubcatgegoriasQtdEconomias = new String[colecaoSubcategoria
						.size()];
				if (!colecaoSubcategoria.isEmpty()) {

					Iterator iterator = colecaoSubcategoria.iterator();

					int i = 0;

					while (iterator.hasNext()) {

						Object[] arraySubcategoria = (Object[]) iterator.next();

						arraySubcatgegoriasQtdEconomias[i] = arraySubcategoria[0]
								.toString()
								+ "/" + arraySubcategoria[1].toString();

						i++;

					}
					imovelRelatorioHelper
							.setSubcategoriaQtdEconomia(arraySubcatgegoriasQtdEconomias);
				}

				// [UC0307 Obter Indicador de Exist�ncia de Hidr�metro no
				// Im�vel]
				imovelRelatorioHelper
						.setIndicadorExistenciaHidrometro(obterIndicadorExistenciaHidrometroImovel(imovelRelatorioHelper
								.getMatriculaImovel()));

				// [UC123 Obter Descri��es da Categoria do Im�vel]
				imovelRelatorioHelper
						.setDescricaoAbreviadaCategoria(obterDescricoesCategoriaImovel(
								new Imovel(imovelRelatorioHelper
										.getMatriculaImovel()))
								.getDescricaoAbreviada());
				
//				 Se o consumo medio pra o maior anoMesReferencia estiver fora do intervalo informado no filtro
				// a linha da cole��o � removida para n�o exibir no relatorio dados fora dos parametros informados
				
				if ( intervaloMediaMinimaImovelInicial != null && !intervaloMediaMinimaImovelInicial.equals("") 
						&& intervaloMediaMinimaImovelInicial !=  null && !intervaloMediaMinimaImovelFinal.equals("") ){
					
					if ( consumoMedio != null && !(consumoMedio < new Integer(intervaloMediaMinimaImovelInicial).intValue()
							||consumoMedio > new Integer(intervaloMediaMinimaImovelFinal).intValue()) ){
						
						colecaoGerarRelatorioImovelOutrosCriterios
						.add(imovelRelatorioHelper);
						
					}
					// Se nao tiver sido informado o intervalo media minima imovel ele insere normalmente
				}else if( (intervaloMediaMinimaImovelInicial == null || intervaloMediaMinimaImovelInicial.equals(""))
						&& ( intervaloMediaMinimaImovelInicial ==  null 
								|| intervaloMediaMinimaImovelFinal.equals("")) ){
					
					colecaoGerarRelatorioImovelOutrosCriterios
					.add(imovelRelatorioHelper);
					
				}
				
//				colecaoGerarRelatorioImovelOutrosCriterios
//				.add(imovelRelatorioHelper);
			}
		}

		return colecaoGerarRelatorioImovelOutrosCriterios;
	}

	/**
	 * 
	 * [UC0164] Filtrar Imoveis por Outros Criterios
	 * 
	 * Filtra para saber a quantidade de imoveis antes de executar o filtro
	 * 
	 * @author Rafael Santos
	 * @date 01/08/2006
	 * 
	 */
	public Integer obterQuantidadaeRelacaoImoveisDebitos(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, Integer relatorio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal)
			throws ControladorException {

		Integer quantidade = null;

		try {
			// remove primeiro as linhas do crit�rio cobran�a
			quantidade = repositorioImovel
					.obterQuantidadaeRelacaoImoveisDebitos(idImovelCondominio,
							idImovelPrincipal, idSituacaoLigacaoAgua,
							consumoMinimoInicialAgua, consumoMinimoFinalAgua,
							idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,
							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,
							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa, idUnidadeNegocio, relatorio,cdRotaInicial,
							cdRotaFinal,sequencialRotaInicial,
							sequencialRotaFinal);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return quantidade;
	}

	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o do im�vel para exibi��o.
	 * 
	 * aki � montada a inscri��o
	 */
	public String pesquisarInscricaoImovel(Integer idImovel)
			throws ControladorException {

		String inscricao = null;
		Imovel imovel = new Imovel();
		Localidade localidade = new Localidade();
		SetorComercial setorComercial = new SetorComercial();
		Quadra quadra = new Quadra();
		try {
			Object[] objetoImovel = this.repositorioImovel
					.pesquisarInscricaoImovel(idImovel);
			if (objetoImovel != null) {
				// parte da montagem da inscri��o
				// primeiro o id da localidade
				localidade.setId(((Integer) objetoImovel[0]));
				imovel.setLocalidade(localidade);
				// codigo do setor comercial
				setorComercial
						.setCodigo(((Integer) objetoImovel[1]).intValue());
				imovel.setSetorComercial(setorComercial);
				// n�mero da quadra
				quadra.setNumeroQuadra(((Integer) objetoImovel[2]));
				imovel.setQuadra(quadra);
				// lote
				imovel.setLote(((Short) objetoImovel[3]));
				// sublote
				imovel.setSubLote(((Short) objetoImovel[4]));

				inscricao = imovel.getInscricaoFormatada();
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return inscricao;
	}
	
	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o do im�vel para exibi��o,
	 * independente do im�vel ter sido exclu�do ou n�o.
	 * 
	 * aqui � montada a inscri��o
	 */
	public String pesquisarInscricaoImovelExcluidoOuNao(Integer idImovel)
			throws ControladorException {

		String inscricao = null;
		Imovel imovel = new Imovel();
		Localidade localidade = new Localidade();
		SetorComercial setorComercial = new SetorComercial();
		Quadra quadra = new Quadra();
		try {
			Object[] objetoImovel = this.repositorioImovel.pesquisarInscricaoImovelExcluidoOuNao(idImovel);
			if (objetoImovel != null) {
				// parte da montagem da inscri��o
				// primeiro o id da localidade
				localidade.setId(((Integer) objetoImovel[0]));
				imovel.setLocalidade(localidade);
				// codigo do setor comercial
				setorComercial
						.setCodigo(((Integer) objetoImovel[1]).intValue());
				imovel.setSetorComercial(setorComercial);
				// n�mero da quadra
				quadra.setNumeroQuadra(((Integer) objetoImovel[2]));
				imovel.setQuadra(quadra);
				// lote
				imovel.setLote(((Short) objetoImovel[3]));
				// sublote
				imovel.setSubLote(((Short) objetoImovel[4]));

				inscricao = imovel.getInscricaoFormatada();
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return inscricao;
	}
	
	public String pesquisarInscricaoImovelSemPonto(Integer idImovel)
		throws ControladorException {

		String inscricao = null;
		Imovel imovel = new Imovel();
		Localidade localidade = new Localidade();
		SetorComercial setorComercial = new SetorComercial();
		Quadra quadra = new Quadra();
		try {
			Object[] objetoImovel = this.repositorioImovel
					.pesquisarInscricaoImovel(idImovel);
			if (objetoImovel != null) {
				// parte da montagem da inscri��o
				// primeiro o id da localidade
				localidade.setId(((Integer) objetoImovel[0]));
				imovel.setLocalidade(localidade);
				// codigo do setor comercial
				setorComercial
						.setCodigo(((Integer) objetoImovel[1]).intValue());
				imovel.setSetorComercial(setorComercial);
				// n�mero da quadra
				quadra.setNumeroQuadra(((Integer) objetoImovel[2]));
				imovel.setQuadra(quadra);
				// lote
				imovel.setLote(((Short) objetoImovel[3]));
				// sublote
				imovel.setSubLote(((Short) objetoImovel[4]));
		
				inscricao = imovel.getInscricaoFormatadaSemPonto();
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return inscricao;
	}


	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o do im�vel para exibi��o.
	 * 
	 * aki � montada a inscri��o
	 */
	public Object[] pesquisarLocalidadeSetorImovel(Integer idImovel)
			throws ControladorException {

		try {
			return this.repositorioImovel.pesquisarLocalidadeSetorImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Gerar Relat�rio de Dados das Economias do Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarRelatorioDadosEconomiaImovel(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio) throws ControladorException {

		Collection colecaoImoveis = null;

		if (quantidadeEconomiasInicial != null
				&& !quantidadeEconomiasInicial.equals("")) {
			if (new Integer(quantidadeEconomiasInicial).intValue() < 2) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atenca.quantidade_inicial_economias.menor");
			}
		}

		try {
			// remove primeiro as linhas do crit�rio cobran�a
			colecaoImoveis = repositorioImovel
					.gerarRelatorioDadosEconomiasImovelOutrosCriterios(
							idImovelCondominio, idImovelPrincipal,
							idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
							consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,

							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,

							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa, idUnidadeNegocio);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// colecao com os dados para o relatorio
		Collection colecaoGerarRelatorioDadosEconomiasImovel = null;

		if (colecaoImoveis == null || colecaoImoveis.isEmpty()) {
			throw new ControladorException(
					"atencao.filtro_consumo_tarifa_sem_records");
		}

		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

			Iterator iteratorColecaoImoveis = colecaoImoveis.iterator();
			colecaoGerarRelatorioDadosEconomiasImovel = new ArrayList();

			ImovelRelatorioHelper imovelRelatorioHelper = null;

			while (iteratorColecaoImoveis.hasNext()) {

				Object[] imovelDadosRelatorio = (Object[]) iteratorColecaoImoveis
						.next();

				imovelRelatorioHelper = new ImovelRelatorioHelper();

				// id
				if (imovelDadosRelatorio[0] != null) { // 0
					imovelRelatorioHelper
							.setMatriculaImovel(((Integer) imovelDadosRelatorio[0]));
				}

				// id gerencia regional
				if (imovelDadosRelatorio[1] != null) { // 1
					imovelRelatorioHelper
							.setIdGerenciaRegional(((Integer) imovelDadosRelatorio[1]));
				}
				// descricao abreviada da gerencia regional
				if (imovelDadosRelatorio[2] != null) { // 2
					imovelRelatorioHelper
							.setDescricaoGerenciaRegional(((String) imovelDadosRelatorio[2]));
				}
				// id localidade
				if (imovelDadosRelatorio[3] != null) { // 3
					imovelRelatorioHelper
							.setIdLocalidade(((Integer) imovelDadosRelatorio[3]));
				}
				// descricao localidade
				if (imovelDadosRelatorio[4] != null) { // 4
					imovelRelatorioHelper
							.setDescricaoLocalidade(((String) imovelDadosRelatorio[4]));
				}
				// codigo setor comercial
				if (imovelDadosRelatorio[5] != null) { // 5
					imovelRelatorioHelper
							.setCodigoSetorComercial(((Integer) imovelDadosRelatorio[5]));
				}
				// descricao setor comercial
				if (imovelDadosRelatorio[6] != null) { // 6
					imovelRelatorioHelper
							.setDescricaoSetorComercial(((String) imovelDadosRelatorio[6]));
				}

				// endere�o
				String endereco = getControladorEndereco().pesquisarEndereco(
						imovelRelatorioHelper.getMatriculaImovel());
				imovelRelatorioHelper.setEndereco(endereco);

				String inscricaoFormatada = this
						.pesquisarInscricaoImovel(imovelRelatorioHelper
								.getMatriculaImovel());
				imovelRelatorioHelper.setInscricaoImovel(inscricaoFormatada);

				Collection colecaoSubcategorias = null;
				try {
					// cole��o com os dados da sub categoria
					colecaoSubcategorias = repositorioImovel
							.gerarRelatorioDadosEconomiasImovelSubcategoria(imovelRelatorioHelper
									.getMatriculaImovel().toString());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				if (colecaoSubcategorias != null
						&& !colecaoSubcategorias.isEmpty()) {

					Iterator colecaoSubcategoriasIterator = colecaoSubcategorias
							.iterator();

					Collection colecaoSubcategoriasRelatorioHelper = new ArrayList();

					ImovelSubcategoriaHelper imovelSubcategoriaHelper = null;

					while (colecaoSubcategoriasIterator.hasNext()) {
						Object[] subcategoriasDadosRelatorio = (Object[]) colecaoSubcategoriasIterator
								.next();

						imovelSubcategoriaHelper = new ImovelSubcategoriaHelper();

						// descri��o
						if (subcategoriasDadosRelatorio[0] != null) {
							imovelSubcategoriaHelper
									.setSubcategoria((String) subcategoriasDadosRelatorio[0]);
						}

						// categoria
						if (subcategoriasDadosRelatorio[1] != null) {
							imovelSubcategoriaHelper
									.setCategoria((String) subcategoriasDadosRelatorio[1]);
						}

						// quantidade de economias
						if (subcategoriasDadosRelatorio[2] != null) {
							imovelSubcategoriaHelper
									.setQuantidadeEconomias((Short) subcategoriasDadosRelatorio[2]);
						}

						String idSubcategoria = null;
						// is Sub CAtegoria
						if (subcategoriasDadosRelatorio[3] != null) {
							idSubcategoria = ((Integer) subcategoriasDadosRelatorio[3])
									.toString();
						}

						Collection colecaoEconomias = null;
						try {
							// cole��o com os dados da sub categoria
							colecaoEconomias = repositorioImovel
									.gerarRelatorioDadosEconomiasImovelEconomia(
											imovelRelatorioHelper
													.getMatriculaImovel()
													.toString(), idSubcategoria);
						} catch (ErroRepositorioException e) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("erro.sistema", e);
						}

						if (colecaoEconomias != null
								&& !colecaoEconomias.isEmpty()) {

							Iterator colecaoEconomiasIterator = colecaoEconomias
									.iterator();

							ImovelEconomiaHelper imovelEconomiaHelper = null;

							Collection colecaoEconomiasRelatorioHelper = new ArrayList();

							while (colecaoEconomiasIterator.hasNext()) {
								Object[] economiasDadosRelatorio = (Object[]) colecaoEconomiasIterator
										.next();

								imovelEconomiaHelper = new ImovelEconomiaHelper();

								// complemento endere�o
								if (economiasDadosRelatorio[0] != null) {
									imovelEconomiaHelper
											.setComplementoEndereco((String) economiasDadosRelatorio[0]);
								}
								// n�mero de pontos de utiliza��o
								if (economiasDadosRelatorio[1] != null) {
									imovelEconomiaHelper
											.setNumeroPontosUtilizacao((Short) economiasDadosRelatorio[1]);
								}
								// n�mero de moradores
								if (economiasDadosRelatorio[2] != null) {
									imovelEconomiaHelper
											.setNumeroMoradores((Short) economiasDadosRelatorio[2]);
								}
								// n�mero do iptu
								if (economiasDadosRelatorio[3] != null) {
									imovelEconomiaHelper
											.setNumeroIptu((BigDecimal) economiasDadosRelatorio[3]);
								}
								// n�mero contrato celpe
								if (economiasDadosRelatorio[4] != null) {
									imovelEconomiaHelper
											.setNumeroContratoCelpe((Long) economiasDadosRelatorio[4]);
								}
								// �rea constru�da
								if (economiasDadosRelatorio[5] != null) {
									imovelEconomiaHelper
											.setAreaConstruidaImovelEconomia((BigDecimal) economiasDadosRelatorio[5]);
								}

								// id Imovel Economia
								String idImovelEconomia = null;
								if (economiasDadosRelatorio[6] != null) {
									idImovelEconomia = ((Integer) economiasDadosRelatorio[6])
											.toString();
								}

								Collection colecaoClientesImoveis = null;
								try {
									// cole��o com os dados da sub categoria
									colecaoClientesImoveis = repositorioImovel
											.gerarRelatorioDadosEconomiasImovelClienteEconomia(idImovelEconomia);
								} catch (ErroRepositorioException e) {
									sessionContext.setRollbackOnly();
									throw new ControladorException(
											"erro.sistema", e);
								}

								if (colecaoClientesImoveis != null
										&& !colecaoClientesImoveis.isEmpty()) {

									Iterator colecaoClientesImoveisIterator = colecaoClientesImoveis
											.iterator();

									Collection colecaoClientesImoveisRelatorioHelper = new ArrayList();

									ClienteImovelEconomiaHelper clienteImovelEconomiaHelper = null;

									while (colecaoClientesImoveisIterator
											.hasNext()) {
										Object[] clienteImovelDadosRelatorio = (Object[]) colecaoClientesImoveisIterator
												.next();

										clienteImovelEconomiaHelper = new ClienteImovelEconomiaHelper();

										// nome cliente
										if (clienteImovelDadosRelatorio[0] != null) {
											clienteImovelEconomiaHelper
													.setClienteNome((String) clienteImovelDadosRelatorio[0]);
										}
										// tipo da rela��o
										if (clienteImovelDadosRelatorio[1] != null) {
											clienteImovelEconomiaHelper
													.setRelacaoTipo((String) clienteImovelDadosRelatorio[1]);
										}
										// Cria um cliente para em seguida setar
										// o cpf e cnpj e utilizar o m�todo de
										// formata��o desses campos existentes
										// na classe cliente
										Cliente cliente = new Cliente();
										// cpf
										if (clienteImovelDadosRelatorio[2] != null) {
											cliente
													.setCpf((String) clienteImovelDadosRelatorio[2]);
											clienteImovelEconomiaHelper
													.setCpf(cliente
															.getCpfFormatado());
										}
										// cnpj
										if (clienteImovelDadosRelatorio[3] != null) {
											cliente
													.setCnpj((String) clienteImovelDadosRelatorio[3]);
											clienteImovelEconomiaHelper
													.setCnpj(cliente
															.getCnpjFormatado());
										}
										// data in�cio rela��o
										if (clienteImovelDadosRelatorio[4] != null) {
											clienteImovelEconomiaHelper
													.setRelacaoDataInicio((Date) clienteImovelDadosRelatorio[4]);
										}
										// data fim rela��o
										if (clienteImovelDadosRelatorio[5] != null) {
											clienteImovelEconomiaHelper
													.setRelacaoDataFim((Date) clienteImovelDadosRelatorio[5]);
										}
										// motivo fim rela��o
										if (clienteImovelDadosRelatorio[6] != null) {
											clienteImovelEconomiaHelper
													.setMotivoFimRelacao((String) clienteImovelDadosRelatorio[6]);
										}

										colecaoClientesImoveisRelatorioHelper
												.add(clienteImovelEconomiaHelper);
									}

									imovelEconomiaHelper
											.setClienteImovelEconomiaHelper(colecaoClientesImoveisRelatorioHelper);
								}

								colecaoEconomiasRelatorioHelper
										.add(imovelEconomiaHelper);
							}

							imovelSubcategoriaHelper
									.setColecaoImovelEconomia(colecaoEconomiasRelatorioHelper);

						}

						colecaoSubcategoriasRelatorioHelper
								.add(imovelSubcategoriaHelper);

					}

					imovelRelatorioHelper
							.setSubcategorias(colecaoSubcategoriasRelatorioHelper);

				}

				colecaoGerarRelatorioDadosEconomiasImovel
						.add(imovelRelatorioHelper);
			}
		}
		return colecaoGerarRelatorioDadosEconomiasImovel;
	}

	/**
	 * 
	 * Esse m�todo � usado para fzazer uma pesquisa na tabela im�vel e confirmar
	 * se o id passado � de um im�vel exclu�do(idExclusao)
	 * 
	 * Fl�vio Cordeiro
	 * 
	 * @throws ErroRepositorioException
	 */

	public Boolean confirmarImovelExcluido(Integer idImovel) {

		boolean retorno = false;

		try {
			retorno = repositorioImovel.confirmarImovelExcluido(idImovel);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Permite pesquisar entidades beneficentes [UC0389] Inserir Autoriza��o
	 * para Doa��o Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param idEntidadeBeneficente -
	 *            C�digo da entidade beneficente
	 * @return Collection<EntidadeBeneficente> - Cole��o de entidades
	 *         beneficentes
	 * @throws ControladorException
	 */
	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(
			Integer idEntidadeBeneficente) throws ControladorException {

		/** * Declara vari�veis locais ** */
		Collection<EntidadeBeneficente> colecaoEntidadeBeneficente = null;
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = null;

		filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();

		/** * Avalia se o par�metro idEntidadeBeneficente veio preenchido ** */
		if ( idEntidadeBeneficente != null && idEntidadeBeneficente.intValue() != 0) {
			filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(
					FiltroEntidadeBeneficente.ID, idEntidadeBeneficente));
		}
		try {
			/** * executa a pesquisa de entidade beneficente ** */
			colecaoEntidadeBeneficente = repositorioImovel
					.pesquisarEntidadeBeneficente(filtroEntidadeBeneficente);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoEntidadeBeneficente;
	}

	/**
	 * Permite pesquisar im�veis doa��o [UC0389] Inserir Autoriza��o para Doa��o
	 * Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param idImovelDoacao -
	 *            C�digo do im�vel doa��o
	 * @return Collection<ImovelDoacao> - Cole��o de im�veis doa��o
	 * @throws ControladorException
	 */
	public Collection<ImovelDoacao> pesquisarImovelDoacao(
			FiltroImovelDoacao filtroImovelDoacao) throws ControladorException {

		/** * Declara as vari�veis locais ** */
		Collection<ImovelDoacao> colecaoImovelDoacao = null;
		try {
			/** * Executa a pesquisa de imovel doacao ** */
			colecaoImovelDoacao = repositorioImovel
					.pesquisarImovelDoacao(filtroImovelDoacao);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoImovelDoacao;
	}

	/**
	 * Permite verificar se existe um determinado im�vel doa��o [UC0389] Inserir
	 * Autoriza��o para Doa��o Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param idImovel -
	 *            C�digo do im�vel
	 * @param idEntidadeBeneficente -
	 *            C�digo da entidade beneficente
	 * @return ImovelDoacao - Retorna um im�vel doa��o caso a combina��o de
	 *         im�vel e entidade beneficente exista.
	 * @throws ControladorException
	 */
	public ImovelDoacao verificarExistenciaImovelDoacao(Integer idImovel,
			Integer idEntidadeBeneficente) throws ControladorException {

		/** * Declara as vari�veis locais ** */
		ImovelDoacao retorno = null;
		FiltroImovelDoacao filtroImovelDoacao = null;
		Collection<ImovelDoacao> colecaoImovelDoacao = null;

		/** * Prepara o filtro com os par�metros passados ** */
		filtroImovelDoacao = new FiltroImovelDoacao();
		filtroImovelDoacao.adicionarParametro(new ParametroSimples(
				FiltroImovelDoacao.ID_IMOVEL, idImovel));
		filtroImovelDoacao.adicionarParametro(new ParametroSimples(
				FiltroImovelDoacao.ID_ENTIDADE_BENEFICENTE,
				idEntidadeBeneficente));
		filtroImovelDoacao.adicionarParametro(new ParametroNulo(FiltroImovelDoacao.DATA_CANCELAMENTO));

		try {
			/** * Executa a pesquisa de im�vel doa��o ** */
			colecaoImovelDoacao = repositorioImovel
					.pesquisarImovelDoacao(filtroImovelDoacao);

			/** * Avalia se existe algum im�vel doa��o ** */
			if (colecaoImovelDoacao != null && colecaoImovelDoacao.size() > 0) {
				/** * Caso exista im�vel doa��o, retorna-o ** */
				retorno = colecaoImovelDoacao.iterator().next();
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * Permite atualizar as informa��es do im�vel doa��o [UC0390] Manter
	 * Autoriza��o para Doa��o Mensal
	 * 
	 * @author C�sar Ara�jo, Pedro Alexandre
	 * @date 30/08/2006, 17/11/2006
	 * @param imovelDoacao -
	 *            C�digo do im�veo doa��o
	 * @throws ControladorException
	 */
	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao,
			Usuario usuarioLogado) throws ControladorException {

		/** * Declara as vari�veis locais ** */
		FiltroImovelDoacao filtroImovelDoacao = null;
		ImovelDoacao imovelDoacaoNaBase = null;

		try {
			/** * Instancia e define o filtro para localiza��o do im�vel doa��o ** */
			filtroImovelDoacao = new FiltroImovelDoacao();
			filtroImovelDoacao.limparListaParametros();
			filtroImovelDoacao.adicionarParametro(new ParametroSimples(
					FiltroImovelDoacao.ID, imovelDoacao.getId()));
			imovelDoacaoNaBase = this.pesquisarImovelDoacao(filtroImovelDoacao)
					.iterator().next();

			/*******************************************************************
			 * * Avalia se a data de altera��o do im�vel doa��o na base de dados
			 * � mais recente do que o registro em edi�ao
			 ******************************************************************/
			if (imovelDoacaoNaBase.getUltimaAlteracao().after(
					imovelDoacao.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// ------------ CONTROLE DE ABRANGENCIA ----------------
			Abrangencia abrangencia = new Abrangencia(usuarioLogado,
					imovelDoacao.getImovel());
			// ------------ CONTROLE DE ABRANGENCIA ----------------

			if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.acesso.negado.abrangencia");
			} else {
				/**
				 * * Seta a nova data para o registro e chama o m�todo para
				 * atualizar os dados **
				 */
				imovelDoacao.setUltimaAlteracao(new Date());
			}
			FaturamentoGrupo fatGrupo  = this.pesquisarGrupoImovel( imovelDoacao.getImovel().getId());
			
			Integer idDebitoTipo = repositorioImovel.pesquisarDebitoTipoImovelDoacao(imovelDoacao.getImovel().getId());
			
			Collection colecaoDebitosACobrarDeDoacaoAtivos = repositorioFaturamento
				.pesquisarDebitoACobrarDeDoacaoAtivos(imovelDoacao.getImovel().getId(), fatGrupo.getAnoMesReferencia(),idDebitoTipo);
			
			if(colecaoDebitosACobrarDeDoacaoAtivos!=null && !colecaoDebitosACobrarDeDoacaoAtivos.isEmpty()){
				
				Integer anoMesFaturamento =
					this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento();
				
				for (Iterator iterator = colecaoDebitosACobrarDeDoacaoAtivos.iterator(); iterator.hasNext();) {
					Integer idDebitoACobrar = (Integer) iterator.next();	
				
					FiltroDebitoACobrar filtro = new FiltroDebitoACobrar();
					
					filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID, idDebitoACobrar));
					
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO);
					
					Collection colecaoDebitos = 
						this.getControladorUtil().pesquisar(filtro, DebitoACobrar.class.getName());
					
					DebitoACobrar debito = (DebitoACobrar) Util.retonarObjetoDeColecao(colecaoDebitos);
					
					boolean existeCobrancaDocumentoItem
					 	= this.VerificaExistenciaCobrancaDocumentoItem(debito.getId());
					
					if(new Short(debito.getNumeroPrestacaoCobradas()).compareTo(new Short("0"))==0
							&& debito.getAnoMesReferenciaContabil()>=anoMesFaturamento
								&& !existeCobrancaDocumentoItem){
						
						this.ExcluirDebitoACobrar(debito);
						
						
					}else if(debito.getAnoMesReferenciaContabil()>=anoMesFaturamento){
						
						debito.setDebitoCreditoSituacaoAnterior(
								debito.getDebitoCreditoSituacaoAtual());
						
						DebitoCreditoSituacao debitoCreditoSituacao
							= new DebitoCreditoSituacao();						
						debitoCreditoSituacao.setId(DebitoCreditoSituacao.CANCELADA);

						debito.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
						debito.setUsuario(usuarioLogado);
						
						this.getControladorUtil().atualizar(debito);
			
					}else{
						
						debito.setDebitoCreditoSituacaoAnterior(null);
						
						DebitoCreditoSituacao debitoCreditoSituacao
							= new DebitoCreditoSituacao();						
						debitoCreditoSituacao.setId(DebitoCreditoSituacao.CANCELADA);

						debito.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
						debito.setAnoMesReferenciaContabil(anoMesFaturamento);
						debito.setUsuario(usuarioLogado);
						
						this.getControladorUtil().atualizar(debito);
						
					}
				}
			}
			
			repositorioImovel.atualizarImovelDoacao(imovelDoacao);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private void ExcluirDebitoACobrar(DebitoACobrar debitoACobrar) throws ControladorException {
		
		FiltroDebitoACobrarCategoria filtroDebitoACobrarCategoria
			= new FiltroDebitoACobrarCategoria();
		
		filtroDebitoACobrarCategoria.adicionarParametro(
				new ParametroSimples(FiltroDebitoACobrarCategoria.ID_DEBITO_A_COBRAR, debitoACobrar.getId()));
		
		Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria = 
			this.getControladorUtil()
				.pesquisar(filtroDebitoACobrarCategoria, DebitoACobrarCategoria.class.getName());
		
		for (DebitoACobrarCategoria debitoACobrarCategoria : colecaoDebitoACobrarCategoria) {
			this.getControladorUtil().remover(debitoACobrarCategoria);
		}
		
		this.getControladorUtil().remover(debitoACobrar);
		
		FiltroDebitoACobrarGeral filtroDebitoACobrarGeral 
			= new FiltroDebitoACobrarGeral();
		
		filtroDebitoACobrarGeral.adicionarParametro(
				new ParametroSimples(FiltroDebitoACobrarGeral.DEBITO_A_COBRAR_ID, debitoACobrar.getId()));
		
		Collection<DebitoACobrarGeral> colecaoDebitoACobrarGeral = 
			this.getControladorUtil()
				.pesquisar(filtroDebitoACobrarGeral, DebitoACobrarGeral.class.getName());
		
		DebitoACobrarGeral debitoACobrarGeral = 
			(DebitoACobrarGeral) Util.retonarObjetoDeColecao(colecaoDebitoACobrarGeral);
		
		this.getControladorUtil().remover(debitoACobrarGeral);
		
	}

	private boolean VerificaExistenciaCobrancaDocumentoItem(Integer id) throws ControladorException {
		
		boolean retorno = false;
		
		FiltroCobrancaDocumentoItem filtro = new FiltroCobrancaDocumentoItem();
			
		filtro.adicionarParametro(
				new ParametroSimples(FiltroCobrancaDocumentoItem.DEBITO_A_COBRAR_GERAL_ID,id));
		
		Collection colecaoCobrancaDocumentoItem = 
			this.getControladorUtil().pesquisar(filtro, CobrancaDocumentoItem.class.getName());
		
		if(colecaoCobrancaDocumentoItem!=null && !colecaoCobrancaDocumentoItem.isEmpty()){
			retorno = true;
		}
		
		return retorno;
	}

	/**
	 * Pesquisa um im�vel a partir do seu id.Retorna os dados que comp�em a
	 * inscri��o e o endere�o do mesmo
	 * 
	 * @author Raphael Rossiter
	 * @date 01/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelRegistroAtendimento(Integer idImovel)
			throws ControladorException {

		Imovel imovel = null;
		Collection colecaoImovel = null;

		try {

			colecaoImovel = this.repositorioImovel
					.pesquisarImovelRegistroAtendimento(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			imovel = new Imovel();

			imovel.setId(idImovel);

			Iterator imovelIterator = colecaoImovel.iterator();

			Object[] arrayImovel = (Object[]) imovelIterator.next();

			if (arrayImovel[20] != null && arrayImovel[21] != null) {

				LogradouroCep logradouroCep = null;
				if (arrayImovel[20] != null) {

					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) arrayImovel[20]);

					// id do Logradouro
					Logradouro logradouro = null;
					if (arrayImovel[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) arrayImovel[19]);
						logradouro.setNome("" + arrayImovel[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// Descri��o logradouro tipo
					if (arrayImovel[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayImovel[22]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o logradouro titulo
					if (arrayImovel[23] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayImovel[23]);
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					// id do CEP
					Cep cep = null;
					if (arrayImovel[10] != null) {
						cep = new Cep();
						cep.setCepId((Integer) arrayImovel[10]);
						cep.setCodigo((Integer) arrayImovel[16]);
					}

					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setCep(cep);
				}

				imovel.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (arrayImovel[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayImovel[21]);

					Bairro bairro = null;
					// nome bairro
					if (arrayImovel[3] != null) {
						bairro = new Bairro();
						bairro.setId((Integer) arrayImovel[3]);
						bairro.setCodigo((Integer) arrayImovel[37]);
						bairro.setNome("" + arrayImovel[4]);
					}

					Municipio municipio = null;
					// nome municipio
					if (arrayImovel[5] != null) {
						municipio = new Municipio();
						municipio.setId((Integer) arrayImovel[5]);
						municipio.setNome("" + arrayImovel[6]);

						// id da unidade federa��o
						if (arrayImovel[7] != null) {
							UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
							unidadeFederacao.setId((Integer) arrayImovel[7]);
							unidadeFederacao.setSigla("" + arrayImovel[8]);
							municipio.setUnidadeFederacao(unidadeFederacao);
						}

						bairro.setMunicipio(municipio);
					}

					logradouroBairro.setBairro(bairro);
				}

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (arrayImovel[24] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setId((Integer) arrayImovel[42]);
					enderecoReferencia.setDescricao("" + arrayImovel[24]);
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayImovel[17] != null) {
					imovel.setNumeroImovel("" + arrayImovel[17]);
				}

				// complemento endere�o
				if (arrayImovel[18] != null) {
					imovel.setComplementoEndereco("" + arrayImovel[18]);
				}
			}

			Localidade localidade = null;
			// id localidade
			if (arrayImovel[25] != null) {
				localidade = new Localidade();
				localidade.setId((Integer) arrayImovel[25]);
				localidade.setDescricao((String) arrayImovel[33]);
				
				//Municipio
				
				if(arrayImovel[46] != null){
					Municipio municipio = new Municipio();
					
					municipio.setId((Integer) arrayImovel[46]);
					municipio.setNome((String) arrayImovel[47]);

					localidade.setMunicipio(municipio);
				}
				imovel.setLocalidade(localidade);
			}

			SetorComercial setorComercial = null;
			// codigo setorComercial
			if (arrayImovel[26] != null) {
				setorComercial = new SetorComercial();
				setorComercial.setCodigo((Integer) arrayImovel[26]);
				setorComercial.setDescricao((String) arrayImovel[34]);
				setorComercial.setId((Integer) arrayImovel[35]);
				imovel.setSetorComercial(setorComercial);
			}

			Quadra quadra = null;
			// n�mero quadra
			if (arrayImovel[27] != null) {
				quadra = new Quadra();
				quadra.setNumeroQuadra((Integer) arrayImovel[27]);
				quadra.setId((Integer) arrayImovel[36]);
				imovel.setQuadra(quadra);
			}

			// lote
			if (arrayImovel[28] != null) {
				imovel.setLote((Short) arrayImovel[28]);
			}

			// sublote
			if (arrayImovel[29] != null) {
				imovel.setSubLote((Short) arrayImovel[29]);
			}

			Bacia bacia = null;
			SistemaEsgoto sistemaEsgoto = null;
			DivisaoEsgoto divisaoEsgoto = null;
			// id divisaoEsgoto
			if (arrayImovel[30] != null) {
				divisaoEsgoto = new DivisaoEsgoto();
				divisaoEsgoto.setId((Integer) arrayImovel[30]);
				sistemaEsgoto = new SistemaEsgoto();
				sistemaEsgoto.setDivisaoEsgoto(divisaoEsgoto);
				bacia = new Bacia();
				bacia.setSistemaEsgoto(sistemaEsgoto);

				if (imovel.getQuadra() != null) {
					imovel.getQuadra().setBacia(bacia);
				}
			}

			PavimentoRua pavimentoRua = null;
			// id pavimentoRua
			if (arrayImovel[31] != null) {
				pavimentoRua = new PavimentoRua();
				pavimentoRua.setId((Integer) arrayImovel[31]);
				imovel.setPavimentoRua(pavimentoRua);
			}

			PavimentoCalcada pavimentoCalcada = null;
			// id pavimentoCalcada
			if (arrayImovel[32] != null) {
				pavimentoCalcada = new PavimentoCalcada();
				pavimentoCalcada.setId((Integer) arrayImovel[32]);
				imovel.setPavimentoCalcada(pavimentoCalcada);
			}

			LigacaoAguaSituacao ligacaoAguaSituacao = null;
			// id ligacaoAguaSituacao
			if (arrayImovel[38] != null) {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();
				ligacaoAguaSituacao.setId((Integer) arrayImovel[38]);
				ligacaoAguaSituacao.setDescricao((String) arrayImovel[39]);
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			// id ligacaoEsgotoSituacao
			if (arrayImovel[40] != null) {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
				ligacaoEsgotoSituacao.setId((Integer) arrayImovel[40]);
				ligacaoEsgotoSituacao.setDescricao((String) arrayImovel[41]);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}

			ImovelPerfil imovelPerfil = null;
			// id imovelPerfil
			if (arrayImovel[43] != null) {
				imovelPerfil = new ImovelPerfil();
				imovelPerfil.setId((Integer) arrayImovel[43]);
				imovel.setImovelPerfil(imovelPerfil);
			}
						
			// CoordenadaX
			if (arrayImovel[44] != null) {
				imovel.setCoordenadaX((String) arrayImovel[44]);
			}
			// Coordenada Y
			if (arrayImovel[45] != null) {
				imovel.setCoordenadaY((String) arrayImovel[45]);
			}
		}

		return imovel;
	}

	/**
	 * Verifica a exist�ncia do hid�metro de acordo com tipo de medi��o
	 * informado (MedicaoTipo.LIGACAO_AGUA ou MedicaoTipo.POCO).
	 * 
	 * [UC0368] Atualizar Instala��o do Hidr�metro
	 * 
	 * [FS0003] - Validar exist�ncia do hidr�metro
	 * 
	 * @author lms
	 * @created 24/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void validarExistenciaHidrometro(Imovel imovel, Integer medicaoTipo)
			throws ControladorException {

		// [FS0003] - Validar exist�ncia do hidr�metro

		// Caso o tipo da medi��o informada seja Liga��o de �gua
		if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {
			/*
			 * Verificar se a situa��o da liga��o de �gua do im�vel est�
			 * diferente de POTENCIAL e FACT�VEL. Caso contr�rio, exibir a
			 * mensagem: "Liga��o de �gua do im�vel <<imov_id>> est� <<last_dsligacaoaguasituacao>>"
			 * e retornar para o passo correspondente do fluxo principal
			 */
			if (!LigacaoAguaSituacao.POTENCIAL.equals(imovel
					.getLigacaoAguaSituacao().getId())
					&& !LigacaoAguaSituacao.FACTIVEL.equals(imovel
							.getLigacaoAguaSituacao().getId())) {
			} else {
				throw new ControladorException(
						"atencao.imovel.ligacao_agua_situacao.incompativel",
						null,
						new String[] { Integer.toString(imovel.getId()),
								imovel.getLigacaoAguaSituacao().getDescricao() });
			}

			/*
			 * Caso o tipo de medi��o seja igual � "Liga��o de �gua" e n�o
			 * exista hidr�metro instalado na liga��o de �gua, exibir a
			 * mensagem: "N�o existe hidr�metro instalado na liga��o de �gua
			 * deste im�vel"
			 */
			if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() == null) {
				throw new ControladorException(
						"atencao.imovel.ligacao_agua.hidrometro.inexistente");
			}

		} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {
			// Caso o tipo da medi��o informada seja Po�o

			/*
			 * Caso n�o exista hidr�metro instalado no po�o, exibe a mensagem:
			 * "N�o existe hidr�metro instalado na sa�da deste im�vel"
			 */
			if (imovel.getHidrometroInstalacaoHistorico() == null) {
				throw new ControladorException(
						"atencao.imovel.poco.hidrometro.inexistente");
			}

		}

	}

	/**
	 * Verifica a exist�ncia da matr�cula do im�vel. Caso exista, verifica se o
	 * im�vel est� ativo.
	 * 
	 * [UC0368] Atualizar Instala��o do Hidr�metro
	 * 
	 * [FS0001] - Verificar a exist�ncia da matr�cula do im�vel [FS0002] -
	 * Verificar a situa��o do im�vel
	 * 
	 * @author lms
	 * @created 19/07/2006
	 * @throws ControladorException
	 * 
	 */
	public Imovel pesquisarImovelSituacaoAtiva(FiltroImovel filtroImovel)
			throws ControladorException {
		Imovel imovel = null;
		Collection imoveis = this.getControladorUtil().pesquisar(filtroImovel,
				Imovel.class.getName());
		// [FS0001] - Verificar exist�ncia da matr�cula do im�vel
		if (imoveis == null || imoveis.isEmpty()) {
			throw new ControladorException(
					"atencao.matricula.imovel.inexistente");
		}
		imovel = (Imovel) imoveis.iterator().next();
		// [FS0002] - Verificar situa��o do im�vel
		if (imovel.getIndicadorExclusao() == ConstantesSistema.INDICADOR_IMOVEL_EXCLUIDO) {
			throw new ControladorException("atencao.imovel.inativo", null,
					imovel.getId().toString());
		}
		return imovel;
	}

	/**
	 * Faz o controle de concorrencia do imovel
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	public void verificarImovelControleConcorrencia(Imovel imovel)
			throws ControladorException {

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				imovel.getId()));

		Collection colecaoImovel = getControladorUtil().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (colecaoImovel == null || colecaoImovel.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		Imovel imovelAtualizado = (Imovel) colecaoImovel.iterator().next();

		if (imovelAtualizado.getUltimaAlteracao().after(
				imovel.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

	}

	/**
	 * Consulta os Dados Cadastrais do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelDadosCadastrais(Integer idImovel)
			throws ControladorException {

		Imovel imovel = null;
		Collection colecaoImovel = null;

		try {

			colecaoImovel = this.repositorioImovel
					.consultarImovelDadosCadastrais(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			imovel = new Imovel();

			imovel.setId(idImovel);

			Quadra quadra = new Quadra();

			Iterator imovelIterator = colecaoImovel.iterator();

			Object[] arrayImovel = (Object[]) imovelIterator.next();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;
			// id ligacaoAguaSituacao - 0
			if (arrayImovel[0] != null) {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();

				ligacaoAguaSituacao.setDescricao((String) arrayImovel[0]);
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			// id ligacaoEsgotoSituacao - 1
			if (arrayImovel[1] != null) {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

				ligacaoEsgotoSituacao.setDescricao((String) arrayImovel[1]);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}

			ImovelPerfil imovelPerfil = null;
			// descricao imovelPerfil - 2
			if (arrayImovel[2] != null) {
				imovelPerfil = new ImovelPerfil();

				imovelPerfil.setDescricao((String) arrayImovel[2]);
				imovel.setImovelPerfil(imovelPerfil);
			}

			Despejo despejo = null;
			// descricao despejo - 3
			if (arrayImovel[3] != null) {
				despejo = new Despejo();

				despejo.setDescricao((String) arrayImovel[3]);
				imovel.setDespejo(despejo);
			}

			// area construida - 4
			if (arrayImovel[4] != null) {
				imovel.setAreaConstruida((BigDecimal) arrayImovel[4]);
			}

			// area construida faixa - menor 5
			AreaConstruidaFaixa areaConstruidaFaixa = null;
			if (arrayImovel[5] != null) {
				areaConstruidaFaixa = new AreaConstruidaFaixa();
				areaConstruidaFaixa.setMenorFaixa((Integer) arrayImovel[5]);
			}

			// area construida faixa - maior 6
			if (arrayImovel[6] != null) {
				if (areaConstruidaFaixa == null) {
					areaConstruidaFaixa = new AreaConstruidaFaixa();
				}
				areaConstruidaFaixa.setMaiorFaixa((Integer) arrayImovel[6]);
			}

			imovel.setAreaConstruidaFaixa(areaConstruidaFaixa);

			// testada do lote - 7
			if (arrayImovel[7] != null) {
				imovel.setTestadaLote((Short) arrayImovel[7]);
			}

			// volumente reservatorio inferior 8
			if (arrayImovel[8] != null) {
				imovel
						.setVolumeReservatorioInferior((BigDecimal) arrayImovel[8]);
			}

			// Volume Reservatorio Inferior - menor 9
			ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior = null;
			if (arrayImovel[9] != null) {
				reservatorioVolumeFaixaInferior = new ReservatorioVolumeFaixa();
				reservatorioVolumeFaixaInferior
						.setVolumeMenorFaixa((BigDecimal) arrayImovel[9]);
			}

			// Volume Reservatorio Inferior - maior 10
			if (arrayImovel[10] != null) {
				if (reservatorioVolumeFaixaInferior == null) {
					reservatorioVolumeFaixaInferior = new ReservatorioVolumeFaixa();
				}
				reservatorioVolumeFaixaInferior
						.setVolumeMaiorFaixa((BigDecimal) arrayImovel[10]);
			}

			imovel
					.setReservatorioVolumeFaixaInferior(reservatorioVolumeFaixaInferior);

			// volumente reservatorio superior 11
			if (arrayImovel[11] != null) {
				imovel
						.setVolumeReservatorioSuperior((BigDecimal) arrayImovel[11]);
			}

			// Volume Reservatorio Superior - menor 12
			ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior = null;
			if (arrayImovel[12] != null) {
				reservatorioVolumeFaixaSuperior = new ReservatorioVolumeFaixa();
				reservatorioVolumeFaixaSuperior
						.setVolumeMenorFaixa((BigDecimal) arrayImovel[12]);
			}

			// Volume Reservatorio Superior - maior 13
			if (arrayImovel[13] != null) {
				if (reservatorioVolumeFaixaSuperior == null) {
					reservatorioVolumeFaixaSuperior = new ReservatorioVolumeFaixa();
				}
				reservatorioVolumeFaixaSuperior
						.setVolumeMaiorFaixa((BigDecimal) arrayImovel[13]);
			}

			imovel
					.setReservatorioVolumeFaixaSuperior(reservatorioVolumeFaixaSuperior);

			// Volume Piscina - 14
			if (arrayImovel[14] != null) {
				imovel.setVolumePiscina((BigDecimal) arrayImovel[14]);
			}

			// Volume Piscina - menor 15
			PiscinaVolumeFaixa piscinaVolumeFaixa = null;
			if (arrayImovel[15] != null) {
				piscinaVolumeFaixa = new PiscinaVolumeFaixa();
				piscinaVolumeFaixa
						.setVolumeMenorFaixa((BigDecimal) arrayImovel[15]);
			}

			// Volume Piscina - maior 16
			if (arrayImovel[16] != null) {
				if (piscinaVolumeFaixa == null) {
					piscinaVolumeFaixa = new PiscinaVolumeFaixa();
				}
				piscinaVolumeFaixa
						.setVolumeMaiorFaixa((BigDecimal) arrayImovel[16]);
			}

			imovel.setPiscinaVolumeFaixa(piscinaVolumeFaixa);

			// Fonte Abastecimento- 17
			FonteAbastecimento fonteAbastecimento = null;
			if (arrayImovel[17] != null) {
				fonteAbastecimento = new FonteAbastecimento();
				fonteAbastecimento.setDescricao((String) arrayImovel[17]);
				imovel.setFonteAbastecimento(fonteAbastecimento);
			}

			// Poco Tipo- 18
			PocoTipo pocoTipo = null;
			if (arrayImovel[18] != null) {
				pocoTipo = new PocoTipo();
				pocoTipo.setDescricao((String) arrayImovel[18]);
				imovel.setPocoTipo(pocoTipo);
			}

			// Distrito Operacional- 19
			DistritoOperacional distritoOperacional = null;
			if (arrayImovel[19] != null) {
				distritoOperacional = new DistritoOperacional();
				distritoOperacional.setDescricao((String) arrayImovel[19]);

				quadra.setDistritoOperacional(distritoOperacional);
			}

			// Pavimento Rua- 20
			PavimentoRua pavimentoRua = null;
			if (arrayImovel[20] != null) {
				pavimentoRua = new PavimentoRua();
				pavimentoRua.setDescricao((String) arrayImovel[20]);
				imovel.setPavimentoRua(pavimentoRua);
			}

			// Pavimento Cal�ada- 21
			PavimentoCalcada pavimentoCalcada = null;
			if (arrayImovel[21] != null) {
				pavimentoCalcada = new PavimentoCalcada();
				pavimentoCalcada.setDescricao((String) arrayImovel[21]);
				imovel.setPavimentoCalcada(pavimentoCalcada);
			}

			// Numero IPTU- 22
			if (arrayImovel[22] != null) {
				imovel.setNumeroIptu((String) arrayImovel[22]);
			}

			// Numero CELPE- 23
			if (arrayImovel[23] != null) {
				imovel.setNumeroCelpe((Long) arrayImovel[23]);
			}

			// Coordenada X- 24
			if (arrayImovel[24] != null) {
				imovel.setCoordenadaX((String) arrayImovel[24]);
			}
			// Coordenada Y- 25
			if (arrayImovel[25] != null) {
				imovel.setCoordenadaY((String) arrayImovel[25]);
			}

			// Cadastro Ocorrencia- 26
			CadastroOcorrencia cadastroOcorrencia = null;
			if (arrayImovel[26] != null) {
				cadastroOcorrencia = new CadastroOcorrencia();
				cadastroOcorrencia.setDescricao((String) arrayImovel[26]);
				imovel.setCadastroOcorrencia(cadastroOcorrencia);
			}
			// Elo Anormalidade- 27
			EloAnormalidade eloAnormalidade = null;
			if (arrayImovel[27] != null) {
				eloAnormalidade = new EloAnormalidade();
				eloAnormalidade.setDescricao((String) arrayImovel[27]);
				imovel.setEloAnormalidade(eloAnormalidade);
			}

			// Indicador Imovel Condominio- 28
			if (arrayImovel[28] != null) {
				imovel.setIndicadorImovelCondominio((Short) arrayImovel[28]);
			}

			// Imovel Condominio- 29
			Imovel imovelCondominio = null;
			if (arrayImovel[29] != null) {
				imovelCondominio = new Imovel();
				imovelCondominio.setId((Integer) arrayImovel[29]);
				imovel.setImovelCondominio(imovelCondominio);
			}

			// Imovel Principal- 30
			Imovel imovelPrincipal = null;
			if (arrayImovel[30] != null) {
				imovelPrincipal = new Imovel();
				imovelPrincipal.setId((Integer) arrayImovel[30]);
				imovel.setImovelPrincipal(imovelPrincipal);
			}

			// Numero Pontos Utilizacao- 31
			if (arrayImovel[31] != null) {
				imovel.setNumeroPontosUtilizacao((Short) arrayImovel[31]);
			}

			// Numero Moradores- 32
			if (arrayImovel[32] != null) {
				imovel.setNumeroMorador((Short) arrayImovel[32]);
			}

			// Jardim- 33
			if (arrayImovel[33] != null) {
				imovel.setIndicadorJardim((Short) arrayImovel[33]);
			}

			// Divis�o de Esgoto- 34
			Bacia bacia = null;
			if (arrayImovel[34] != null) {
				bacia = new Bacia();
				bacia.setDescricao((String) arrayImovel[34]);

				// Divis�o de Esgoto
				if (arrayImovel[36] != null) {
					SistemaEsgoto sistemaEsgoto = new SistemaEsgoto();
					DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();

					divisaoEsgoto.setDescricao((String) arrayImovel[36]);

					sistemaEsgoto.setDivisaoEsgoto(divisaoEsgoto);
					bacia.setSistemaEsgoto(sistemaEsgoto);

				}

				quadra.setBacia(bacia);
			}
			imovel.setQuadra(quadra);
			
			//Tipo Habita��o - 37
			if (arrayImovel[37] != null) {
				ImovelTipoHabitacao imovelTipoHabitacao = new ImovelTipoHabitacao();
				imovelTipoHabitacao.setDescricao((String) arrayImovel[37]);
				imovel.setImovelTipoHabitacao(imovelTipoHabitacao);
			}
			
			//Tipo Propriedade - 38
			if (arrayImovel[38] != null) {
				ImovelTipoPropriedade imovelTipoPropriedade = new ImovelTipoPropriedade();
				imovelTipoPropriedade.setDescricao((String) arrayImovel[38]);
				imovel.setImovelTipoPropriedade(imovelTipoPropriedade);
			}
			
			//Tipo Contrucao - 39
			if (arrayImovel[39] != null) {
				ImovelTipoConstrucao imovelTipoConstrucao = new ImovelTipoConstrucao();
				imovelTipoConstrucao.setDescricao((String) arrayImovel[39]);
				imovel.setImovelTipoConstrucao(imovelTipoConstrucao);
			}
			
			//Tipo Cobertura - 40
			if (arrayImovel[40] != null) {
				ImovelTipoCobertura imovelTipoCobertura = new ImovelTipoCobertura();
				imovelTipoCobertura.setDescricao((String) arrayImovel[40]);
				imovel.setImovelTipoCobertura(imovelTipoCobertura);
			}
			
			//Indicador Exclus�o - 41
			if (arrayImovel[41] != null) {
				imovel.setIndicadorExclusao((Short) arrayImovel[41]);
			}
			
			//Nome Imovel - 42
			if (arrayImovel[42] != null) {
				imovel.setNomeImovel((String) arrayImovel[42]);
			}

			//Municipio - 43
			if(arrayImovel[43] != null){
				Localidade localidade = new Localidade();
				Municipio municipio = (Municipio) arrayImovel[43];
				localidade.setMunicipio(municipio);
				imovel.setLocalidade(localidade);
			}
			//Indicador Exclus�o - 44
			if (arrayImovel[41] != null) {
				imovel.setIndicadorNivelInstalacaoEsgoto((Short) arrayImovel[44]);
			}
		}

		return imovel;
	}

	/**
	 * Consulta os Clientes do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClientesImovel(Integer idImovel)
			throws ControladorException {

		Collection colecaoClientes = null;
		Collection<ClienteImovel> clienteImoveis = null;

		try {

			colecaoClientes = this.repositorioImovel
					.pesquisarClientesImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClientes != null && !colecaoClientes.isEmpty()) {

			clienteImoveis = new ArrayList();

			Iterator iteratorColecaoClientes = colecaoClientes.iterator();

			while (iteratorColecaoClientes.hasNext()) {

				ClienteImovel clienteImovel = criarClienteImovel(iteratorColecaoClientes);

				clienteImoveis.add(clienteImovel);

			}// fim do while
		}
		return clienteImoveis;
	}
	
	
	/**
	 * Consulta os Clientes do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClientesImovelDataMax(Integer idImovel)
			throws ControladorException {

		Collection colecaoClientes = null;
		Collection<ClienteImovel> clienteImoveis = null;

		try {

			colecaoClientes = this.repositorioImovel
					.pesquisarClientesImovelDataMax(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClientes != null && !colecaoClientes.isEmpty()) {

			clienteImoveis = new ArrayList();

			Iterator iteratorColecaoClientes = colecaoClientes.iterator();

			while (iteratorColecaoClientes.hasNext()) {

				ClienteImovel clienteImovel = criarClienteImovel(iteratorColecaoClientes);

				clienteImoveis.add(clienteImovel);

			}// fim do while
		}
		return clienteImoveis;
	}
	
	/**
	 * Pesquisa a cole��o de clientes do imovel mesmo que o im�vel j� tenha sido exclu�do [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 * parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ControladorException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesImovelExcluidoOuNao(Integer idImovel)
			throws ControladorException {

		Collection colecaoClientes = null;
		Collection<ClienteImovel> clienteImoveis = null;

		try {

			colecaoClientes = this.repositorioImovel
					.pesquisarClientesImovelExcluidoOuNao(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClientes != null && !colecaoClientes.isEmpty()) {

			clienteImoveis = new ArrayList();

			Iterator iteratorColecaoClientes = colecaoClientes.iterator();

			while (iteratorColecaoClientes.hasNext()) {

				ClienteImovel clienteImovel = criarClienteImovel(iteratorColecaoClientes);

				clienteImoveis.add(clienteImovel);

			}// fim do while
		}
		return clienteImoveis;
	}

	private ClienteImovel criarClienteImovel(Iterator iteratorColecaoClientes) {
		ClienteImovel clienteImovel = new ClienteImovel();

		Object[] arrayCliente = (Object[]) iteratorColecaoClientes
				.next();

		Cliente cliente = new Cliente();
		IClienteFone clienteFone = null;

		// 0 - id co cliente
		if (arrayCliente[0] != null) {
			cliente.setId((Integer) arrayCliente[0]);
		}
		// 1 - nome do cliente
		if (arrayCliente[1] != null) {
			cliente.setNome((String) arrayCliente[1]);
		}
		// 2 - descricao cliente rela��o tipo
		// 8 - id cliente rela��o tipo
		if (arrayCliente[2] != null) {
			ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
			clienteRelacaoTipo.setDescricao((String) arrayCliente[2]);
			clienteRelacaoTipo.setId((Integer) arrayCliente[8]);
			clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);
		}
		// 3 - data inicio rela��o
		if (arrayCliente[3] != null) {
			clienteImovel.setDataInicioRelacao((Date) arrayCliente[3]);
		}
		// 4 - telefone
		if (arrayCliente[4] != null) {
			clienteFone = new ClienteFone();
			clienteFone.setTelefone((String) arrayCliente[4]);
		}
		// 5 - cnpj
		if (arrayCliente[5] != null) {
			cliente.setCnpj((String) arrayCliente[5]);
		}
		// 6 - cpf
		if (arrayCliente[6] != null) {
			cliente.setCpf((String) arrayCliente[6]);
		}

		// 7 - ddd
		if (arrayCliente[7] != null) {
			clienteFone.setDdd((String) arrayCliente[7]);
		}
		
		// 9 - Id do ClienteImovel
		if (arrayCliente[9] != null) {
			clienteImovel.setId((Integer) arrayCliente[9]);
		}
		
		//10 - Rg
		if (arrayCliente[10] != null) {
			cliente.setRg((String) arrayCliente[10]);
		}
		
		// 11 - indicadorUso
		if (arrayCliente[11] != null) {
			cliente.setIndicadorUso((Short) arrayCliente[11]);
		}

		Set clienteFones = new TreeSet();
		if (clienteFone != null) {
			clienteFones.add(clienteFone);
			cliente.setClienteFones(clienteFones);
		}

		// cliente.setclImovel.setCliente(cliente);
		clienteImovel.setCliente(cliente);
		return clienteImovel;
	}

	/**
	 * Pesquisa a cole��o de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCategoriasImovel(Integer idImovel)
			throws ControladorException {

		Collection colecaoCategorias = null;
		Collection<ImovelSubcategoria> imoveisSubCategoria = null;

		try {

			colecaoCategorias = this.repositorioImovel
					.pesquisarCategoriasImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {

			imoveisSubCategoria = new ArrayList();

			Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();

			while (iteratorColecaoCategorias.hasNext()) {

				Object[] arrayCategoria = (Object[]) iteratorColecaoCategorias
						.next();

				ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();

				ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
				Subcategoria subcategoria = new Subcategoria();

				// 0 - descricao da categoria
				if (arrayCategoria[0] != null) {
					Categoria categoria = new Categoria();
					categoria.setDescricao((String) arrayCategoria[0]);
					categoria.setId(((Categoria) arrayCategoria[3]).getId());
					categoria
							.setQuantidadeEconomiasCategoria(((Short) arrayCategoria[2])
									.intValue());

					subcategoria.setCategoria(categoria);
				}
				// 1 - descricao da subcategoria
				if (arrayCategoria[1] != null) {
					subcategoria.setDescricao((String) arrayCategoria[1]);
				}

				// 2 - quantidade de economias
				if (arrayCategoria[2] != null) {
					imovelSubcategoria
							.setQuantidadeEconomias(((Short) arrayCategoria[2])
									.shortValue());
				}
				
				// 4 - id Subcategoria
				if (arrayCategoria[4] != null) {
					subcategoria
							.setId((Integer) arrayCategoria[4]);
				}

				imovelSubcategoriaPK.setSubcategoria(subcategoria);

				imovelSubcategoria.setComp_id(imovelSubcategoriaPK);

				imoveisSubCategoria.add(imovelSubcategoria);

			}// fim do while
		}
		return imoveisSubCategoria;

	}

	/**
	 * [UC0475] Consultar Perfil Imovel
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idImovel
	 * @return Perfil do Im�vel
	 * @exception ControladorException
	 */
	public ImovelPerfil obterImovelPerfil(Integer idImovel)
			throws ControladorException {
		ImovelPerfil imovelPerfil = null;
		try {
			imovelPerfil = this.repositorioImovel.obterImovelPerfil(idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return imovelPerfil;
	}

	/**
	 * Consulta os Dados Complementares do Imovel
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelDadosComplementares(Integer idImovel)
			throws ControladorException {
		Imovel imovel = null;
		Collection colecaoImovel = null;

		try {

			colecaoImovel = this.repositorioImovel
					.consultarImovelDadosComplementares(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			imovel = new Imovel();

			imovel.setId(idImovel);

			Iterator imovelIterator = colecaoImovel.iterator();

			Object[] arrayImovel = (Object[]) imovelIterator.next();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;
			
			// id ligacaoAguaSituacao - 0
			if (arrayImovel[0] != null) {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();

				ligacaoAguaSituacao.setDescricao((String) arrayImovel[0]);
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			// id ligacaoEsgotoSituacao - 1
			if (arrayImovel[1] != null) {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

				ligacaoEsgotoSituacao.setDescricao((String) arrayImovel[1]);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}

			ConsumoTarifa consumoTarifa = null;
			// descricao consumo Tarifa - 2
			if (arrayImovel[2] != null) {
				consumoTarifa = new ConsumoTarifa();

				consumoTarifa.setDescricao((String) arrayImovel[2]);
				imovel.setConsumoTarifa(consumoTarifa);
			}

			// numero Retificacao - 3
			if (arrayImovel[3] != null) {
				imovel.setNumeroRetificacao((Short) arrayImovel[3]);
			}

			// numero Parcelamento - 4
			if (arrayImovel[4] != null) {
				imovel.setNumeroParcelamento((Short) arrayImovel[4]);
			}

			// numero Reparcelamento - 5
			if (arrayImovel[5] != null) {
				imovel.setNumeroReparcelamento((Short) arrayImovel[5]);
			}

			// numero Reparcelamento Consecutivos - 6
			if (arrayImovel[6] != null) {
				imovel.setNumeroReparcelamentoConsecutivos((Short) arrayImovel[6]);
			}

			// cobranca situacao 
//			CobrancaSituacao cobrancaSituacao = null;
//			try {
//				cobrancaSituacao = repositorioImovel.pesquisarImovelCobrancaSituacao(idImovel);
//			} catch (ErroRepositorioException e) {
//				sessionContext.setRollbackOnly();
//				throw new ControladorException("erro.sistema", e);
//			}
//			
//			if (cobrancaSituacao != null){
//				imovel.setCobrancaSituacao(cobrancaSituacao);
//			}

			// cadastro ocorrencia - 8
			CadastroOcorrencia cadastroOcorrencia = null;
			if (arrayImovel[8] != null) {
				cadastroOcorrencia = new CadastroOcorrencia();
				cadastroOcorrencia.setDescricao((String) arrayImovel[8]);
				imovel.setCadastroOcorrencia(cadastroOcorrencia);
			}

			// Elo Anormalidade - 9
			EloAnormalidade eloAnormalidade = null;
			if (arrayImovel[9] != null) {
				eloAnormalidade = new EloAnormalidade();
				eloAnormalidade.setDescricao((String) arrayImovel[9]);
				imovel.setEloAnormalidade(eloAnormalidade);
			}
			
			// Id Funcionario - 10
			// Nome Funcionario - 11
			if (arrayImovel[10] != null) {
				Funcionario funcionario = new Funcionario();
				
				funcionario.setId((Integer) arrayImovel[10]);
				funcionario.setNome((String) arrayImovel[11]);
				
				imovel.setFuncionario(funcionario);
			}
			
			// Indicador Exclus�o - 12
			if (arrayImovel[12] != null) {
				imovel.setIndicadorExclusao((Short) arrayImovel[12]);
			}
			
			// Indicador Exclus�o - 12
			if (arrayImovel[13] != null) {
				imovel.setInformacoesComplementares((String) arrayImovel[13]);
			}
			
			

		}

		return imovel;

	}

	/**
	 * Pesquisa a cole��o de vencimento alternativos do imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarVencimentoAlternativoImovel(Integer idImovel)
			throws ControladorException {

		Collection colecaoVencimentosAlternativos = null;
		Collection<VencimentoAlternativo> imoveisVencimentosAlternativos = null;

		try {

			colecaoVencimentosAlternativos = this.repositorioImovel
					.pesquisarVencimentoAlternativoImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoVencimentosAlternativos != null
				&& !colecaoVencimentosAlternativos.isEmpty()) {

			imoveisVencimentosAlternativos = new ArrayList();

			Iterator iteratorColecaoVencimentoAlternativo = colecaoVencimentosAlternativos
					.iterator();

			while (iteratorColecaoVencimentoAlternativo.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoVencimentoAlternativo
						.next();

				VencimentoAlternativo vencimentoAlternativo = new VencimentoAlternativo();

				// ImovelSubcategoriaPK imovelSubcategoriaPK = new
				// ImovelSubcategoriaPK();
				// Subcategoria subcategoria = new Subcategoria();

				// 0 - date vencimento
				if (array[0] != null) {
					vencimentoAlternativo.setDateVencimento((Short) array[0]);
				}
				// 1 - data implantacao
				if (array[1] != null) {
					vencimentoAlternativo.setDataImplantacao((Date) array[1]);
				}

				// 2 - data exclusao
				if (array[2] != null) {
					vencimentoAlternativo.setDateExclusao((Date) array[2]);
				}

				imoveisVencimentosAlternativos.add(vencimentoAlternativo);

			}// fim do while
		}
		return imoveisVencimentosAlternativos;
	}

	/**
	 * Pesquisa a cole��o de Debitos Automaticos do imovel [UC0473] Consultar
	 * Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarDebitosAutomaticosImovel(Integer idImovel)
			throws ControladorException {

		Collection colecaoDebitosAutomaticos = null;
		Collection<DebitoAutomatico> imoveisDebitosAutomaticos = null;

		try {

			colecaoDebitosAutomaticos = this.repositorioImovel
					.pesquisarDebitosAutomaticosImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDebitosAutomaticos != null
				&& !colecaoDebitosAutomaticos.isEmpty()) {

			imoveisDebitosAutomaticos = new ArrayList();

			Iterator iteratorColecaoDebitosAutomaticos = colecaoDebitosAutomaticos
					.iterator();

			while (iteratorColecaoDebitosAutomaticos.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoDebitosAutomaticos
						.next();

				DebitoAutomatico debitoAutomatico = new DebitoAutomatico();

				// 0 - nome abreviado banco
				Agencia agencia = null;
				if (array[0] != null) {
					Banco banco = new Banco();
					banco.setDescricaoAbreviada((String) array[0]);
					agencia = new Agencia();
					agencia.setBanco(banco);
					debitoAutomatico.setAgencia(agencia);
				}
				// 1 - codigo agencia
				if (array[1] != null) {
					if (agencia == null) {
						agencia = new Agencia();
					}
					agencia.setCodigoAgencia((String) array[1]);
				}

				debitoAutomatico.setAgencia(agencia);

				// 2 - identicacao do cliente banco
				if (array[2] != null) {
					debitoAutomatico
							.setIdentificacaoClienteBanco((String) array[2]);
				}
				// 3 - data da op��o
				if (array[3] != null) {
					debitoAutomatico
							.setDataOpcaoDebitoContaCorrente((Date) array[3]);
				}
				// 4 - data da inclus�o
				if (array[4] != null) {
					debitoAutomatico
							.setDataInclusaoNovoDebitoAutomatico((Date) array[4]);
				}

				// 5 - data exclusao
				if (array[5] != null) {
					debitoAutomatico.setDataExclusao((Date) array[5]);
				}

				imoveisDebitosAutomaticos.add(debitoAutomatico);

			}// fim do while
		}
		return imoveisDebitosAutomaticos;
	}

	/**
	 * Pesquisa a cole��o de Faturamento Situa��o Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarFaturamentosSituacaoHistorico(Integer idImovel)
			throws ControladorException {

		Collection colecaoFaturamentosSituacaoHistorico = null;
		Collection<FaturamentoSituacaoHistorico> imoveisFaturamentosSituacaoHistorico = null;

		try {

			colecaoFaturamentosSituacaoHistorico = this.repositorioImovel
					.pesquisarFaturamentosSituacaoHistorico(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoFaturamentosSituacaoHistorico != null
				&& !colecaoFaturamentosSituacaoHistorico.isEmpty()) {

			imoveisFaturamentosSituacaoHistorico = new ArrayList();

			Iterator iteratorColecaoFaturamentosSituacaoHistorico = colecaoFaturamentosSituacaoHistorico
					.iterator();

			while (iteratorColecaoFaturamentosSituacaoHistorico.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoFaturamentosSituacaoHistorico
						.next();

				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();

				// 0 - id do fatutamento situacao tipo
				// 1 - descricao do fatutamento situacao tipo
				FaturamentoSituacaoTipo faturamentoSituacaoTipo = null;
				if (array[0] != null && array[1] != null) {
					faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
					faturamentoSituacaoTipo.setId((Integer) array[0]);
					faturamentoSituacaoTipo.setDescricao((String) array[1]);

					faturamentoSituacaoHistorico
							.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				}

				// 2 - id do fatutamento situacao motivo
				// 3 - descricao do fatutamento situacao motivo
				FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = null;
				if (array[2] != null && array[3] != null) {
					faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
					faturamentoSituacaoMotivo.setId((Integer) array[2]);
					faturamentoSituacaoMotivo.setDescricao((String) array[3]);

					faturamentoSituacaoHistorico
							.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);
				}

				// 4 - mes nao faturamento inicio
				if (array[4] != null) {
					faturamentoSituacaoHistorico
							.setAnoMesFaturamentoSituacaoInicio((Integer) array[4]);
				}

				// 5 - mes nao faturamento fim
				if (array[5] != null) {
					faturamentoSituacaoHistorico
							.setAnoMesFaturamentoSituacaoFim((Integer) array[5]);
				}

				// 6 - mes nao faturamento retirada
				if (array[6] != null) {
					faturamentoSituacaoHistorico
							.setAnoMesFaturamentoRetirada((Integer) array[6]);
				}

				Usuario usuario = null;
				// 7 - usuario
				if (array[7] != null) {
					usuario = new Usuario();
					usuario.setNomeUsuario((String) array[7]);
					faturamentoSituacaoHistorico.setUsuario(usuario);
				}
				
				// 8 - id
				if (array[8] != null) {
					faturamentoSituacaoHistorico.setId((Integer) array[8]);
				}

				imoveisFaturamentosSituacaoHistorico
						.add(faturamentoSituacaoHistorico);

			}// fim do while
		}
		return imoveisFaturamentosSituacaoHistorico;
	}

	/**
	 * Pesquisa a cole��o de cobran�as Situa��o Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCobrancasSituacaoHistorico(Integer idImovel)
			throws ControladorException {

		Collection colecaoCobrancasSituacaoHistorico = null;
		Collection<CobrancaSituacaoHistorico> imoveisCobrancasSituacaoHistorico = null;

		try {

			colecaoCobrancasSituacaoHistorico = this.repositorioImovel
					.pesquisarCobrancasSituacaoHistorico(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoCobrancasSituacaoHistorico != null
				&& !colecaoCobrancasSituacaoHistorico.isEmpty()) {

			imoveisCobrancasSituacaoHistorico = new ArrayList();

			Iterator iteratorColecaoCobrancasSituacaoHistorico = colecaoCobrancasSituacaoHistorico
					.iterator();

			while (iteratorColecaoCobrancasSituacaoHistorico.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoCobrancasSituacaoHistorico
						.next();

				CobrancaSituacaoHistorico cobrancaSituacaoHistorico = new CobrancaSituacaoHistorico();

				// 0 - descricao do cobranca situacao tipo
				CobrancaSituacaoTipo cobrancaSituacaoTipo = null;
				if (array[0] != null) {
					cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
					cobrancaSituacaoTipo.setDescricao((String) array[0]);
					cobrancaSituacaoHistorico
							.setCobrancaSituacaoTipo(cobrancaSituacaoTipo);
				}

				// 1 - descricao do fatutamento situacao motivo
				CobrancaSituacaoMotivo cobrancaSituacaoMotivo = null;
				if (array[1] != null) {
					cobrancaSituacaoMotivo = new CobrancaSituacaoMotivo();
					cobrancaSituacaoMotivo.setDescricao((String) array[1]);

					cobrancaSituacaoHistorico
							.setCobrancaSituacaoMotivo(cobrancaSituacaoMotivo);
				}

				// 2 - mes nao faturamento inicio
				if (array[2] != null) {
					cobrancaSituacaoHistorico
							.setAnoMesCobrancaSituacaoInicio((Integer) array[2]);
				}

				// 3 - mes nao faturamento fim
				if (array[3] != null) {
					cobrancaSituacaoHistorico
							.setAnoMesCobrancaSituacaoFim((Integer) array[3]);
				}

				// 4 - mes nao faturamento retirada
				if (array[4] != null) {
					cobrancaSituacaoHistorico
							.setAnoMesCobrancaRetirada((Integer) array[4]);
				}

				Usuario usuario = null;
				// 5- usuasrio
				if (array[5] != null) {
					usuario = new Usuario();
					usuario.setNomeUsuario((String) array[5]);
					cobrancaSituacaoHistorico.setUsuario(usuario);
				}
				
				// 6 - id
				if (array[6] != null) {
					cobrancaSituacaoHistorico
							.setId((Integer) array[6]);
				}

				imoveisCobrancasSituacaoHistorico
						.add(cobrancaSituacaoHistorico);

			}// fim do while
		}
		return imoveisCobrancasSituacaoHistorico;
	}

	/**
	 * Consutlar os Dados de Analise da Medi��o e Consumo do Imovel [UC0473]
	 * Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelAnaliseMedicaoConsumo(Integer idImovel)
			throws ControladorException {
		Imovel imovel = null;
		Collection colecaoImovel = null;

		try {

			colecaoImovel = this.repositorioImovel
					.consultarImovelAnaliseMedicaoConsumo(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			imovel = new Imovel();

			imovel.setId(idImovel);

			Iterator imovelIterator = colecaoImovel.iterator();

			Object[] arrayImovel = (Object[]) imovelIterator.next();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;
			// id ligacaoAguaSituacao - 0
			if (arrayImovel[0] != null) {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();

				ligacaoAguaSituacao.setDescricao((String) arrayImovel[0]);
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			// id ligacaoEsgotoSituacao - 1
			if (arrayImovel[1] != null) {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

				ligacaoEsgotoSituacao.setDescricao((String) arrayImovel[1]);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}

			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
			// id hidrometroInstalacaoHistorico - 2
			if (arrayImovel[2] != null) {
				hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();

				hidrometroInstalacaoHistorico.setId((Integer) arrayImovel[2]);
				imovel
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			}

			LigacaoAgua ligacaoAgua = new LigacaoAgua();
			// id ligacao Agua - 3
			if (arrayImovel[3] != null) {
				HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua = new HidrometroInstalacaoHistorico();
				hidrometroInstalacaoHistoricoAgua
						.setId((Integer) arrayImovel[3]);
				
				ligacaoAgua
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoAgua);				
			}
			
			imovel.getLigacaoAguaSituacao().setId( (Integer) arrayImovel[8] );
			
			if ( arrayImovel[8] != null && arrayImovel[8].equals( LigacaoAguaSituacao.CORTADO ) ){
				CorteTipo corteTipo = new CorteTipo();
				corteTipo.setDescricao(( arrayImovel[4] != null ? arrayImovel[4]+"" : null ) );
				
				ligacaoAgua.setCorteTipo( corteTipo );
				
				MotivoCorte motivoCorte = new MotivoCorte();
				motivoCorte.setDescricao( ( arrayImovel[5] != null ? arrayImovel[5]+"" : null ) );
				ligacaoAgua.setMotivoCorte( motivoCorte );
				
				ligacaoAgua.setDataCorte( (Date) ( arrayImovel[6] != null ? arrayImovel[6] : null ) );
				ligacaoAgua.setNumeroSeloCorte( (Integer) ( arrayImovel[7] != null ? arrayImovel[7] : null ) );
				
				ligacaoAgua.setCorteTipo( corteTipo );				
			} else if ( arrayImovel[8] != null && ( 
					arrayImovel[8].equals( LigacaoAguaSituacao.SUPRIMIDO ) ||
					arrayImovel[8].equals( LigacaoAguaSituacao.SUPR_PARC ) ||
					arrayImovel[8].equals( LigacaoAguaSituacao.SUPR_PARC_PEDIDO ) ) ){				
				SupressaoTipo supressaoTipo = new SupressaoTipo();
				
				supressaoTipo.setDescricao( ( arrayImovel[9] != null ? arrayImovel[9]+"" : null ) );
				
				ligacaoAgua.setSupressaoTipo( supressaoTipo );
				
				SupressaoMotivo motivoSupressao = new SupressaoMotivo();
				motivoSupressao.setDescricao( ( arrayImovel[10] != null ? arrayImovel[10]+"" : null ) );
				ligacaoAgua.setSupressaoMotivo( motivoSupressao );
				
				ligacaoAgua.setDataSupressao( (Date) ( arrayImovel[11] != null ? arrayImovel[11] : null ) );
				ligacaoAgua.setNumeroSeloSupressao( (Integer) ( arrayImovel[12] != null ? arrayImovel[12] : null ) );
				
				ligacaoAgua.setSupressaoTipo( supressaoTipo );
			}
			
			//data supressao parcial
			if (arrayImovel[13] != null) {
				imovel.setDataSupressaoParcial((Date)arrayImovel[13]);
			}
			
			imovel.setLigacaoAgua(ligacaoAgua);
			
			// Indicador Exclus�o - 14
			if (arrayImovel[14] != null) {
				imovel.setIndicadorExclusao((Short) arrayImovel[14]);
			}
		}

		return imovel;

	}

	/**
	 * 
	 * Registrar leituras e anormalidades
	 * 
	 * @author S�vio Luiz
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public void atualizarImovelLeituraAnormalidade(
			Map<Integer, MedicaoHistorico> mapAtualizarLeituraAnormalidadeImovel,
			Date dataAtual) throws ControladorException {

		try {

			this.repositorioImovel.atualizarImovelLeituraAnormalidade(
					mapAtualizarLeituraAnormalidadeImovel, dataAtual);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Consutlar os Dados do Historico de Faturamento [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelHistoricoFaturamento(Integer idImovel)
			throws ControladorException {
		Imovel imovel = null;
		Collection colecaoImovel = null;

		try {

			colecaoImovel = this.repositorioImovel
					.consultarImovelHistoricoFaturamento(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			imovel = new Imovel();

			imovel.setId(idImovel);

			Iterator imovelIterator = colecaoImovel.iterator();

			Object[] arrayImovel = (Object[]) imovelIterator.next();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;
			// id ligacaoAguaSituacao - 0
			if (arrayImovel[0] != null) {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();

				ligacaoAguaSituacao.setDescricao((String) arrayImovel[0]);
				ligacaoAguaSituacao.setId((Integer) arrayImovel[3]);
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			// id ligacaoEsgotoSituacao - 1
			if (arrayImovel[1] != null) {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

				ligacaoEsgotoSituacao.setDescricao((String) arrayImovel[1]);
				ligacaoEsgotoSituacao.setId((Integer) arrayImovel[4]);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}
			
			// indicador exclus�o - 2
			if (arrayImovel[2] != null) {
				imovel.setIndicadorExclusao((Short) arrayImovel[2]);
			}
			

			CobrancaSituacao cobrancaSituacao = null;
			try {
				cobrancaSituacao = repositorioImovel.pesquisarImovelCobrancaSituacao(idImovel);
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
			
			if (cobrancaSituacao != null){
				imovel.setCobrancaSituacao(cobrancaSituacao);
			}
			
			
		}

		return imovel;
	}

	/**
	 * Consutlar o cliente usu�rio do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public String consultarClienteUsuarioImovel(Integer idImovel)
			throws ControladorException {
		String nomeClienteUsuario = null;

		try {

			nomeClienteUsuario = this.repositorioImovel
					.consultarClienteUsuarioImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return nomeClienteUsuario;
	}

	/**
	 * Consutlar as contas do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasImovel(Integer idImovel)
			throws ControladorException {
		Collection colecaoContas = null;
		Collection<Conta> imoveisContas = null;

		try {

			colecaoContas = this.repositorioImovel
					.consultarContasImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoContas != null && !colecaoContas.isEmpty()) {

			imoveisContas = new ArrayList();

			Iterator iteratorColecaoContas = colecaoContas.iterator();

			while (iteratorColecaoContas.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoContas.next();

				Conta conta = new Conta();

				// 0 - id Conta
				if (array[0] != null) {
					conta.setId((Integer) array[0]);
				}

				// 1 - Referencia
				if (array[1] != null) {
					conta.setReferencia((Integer) array[1]);
				}

				// 2 - Data Vencimento
				if (array[2] != null) {
					conta.setDataVencimentoConta((Date) array[2]);
				}

				// 3 - Valor Agua
				if (array[3] != null) {
					conta.setValorAgua((BigDecimal) array[3]);
				}

				// 4 - Valor Esgoto
				if (array[4] != null) {
					conta.setValorEsgoto((BigDecimal) array[4]);
				}

				// 5 - Valor Debitos
				if (array[5] != null) {
					conta.setDebitos((BigDecimal) array[5]);
				}

				// 6 - Valor Creditos
				if (array[6] != null) {
					conta.setValorCreditos((BigDecimal) array[6]);
				}

				// 7 - debito Credito Situacao Atual
				DebitoCreditoSituacao debitoCreditoSituacaoAtual = null;
				if (array[7] != null) {
					debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
					debitoCreditoSituacaoAtual
							.setDescricaoAbreviada((String) array[7]);
					debitoCreditoSituacaoAtual
							.setDescricaoDebitoCreditoSituacao((String) array[9]);
					conta
							.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
				}

				// 8 - Motivo Revis�o
				if (array[8] != null) {
					ContaMotivoRevisao contaMotivoRevisao = new ContaMotivoRevisao();
					contaMotivoRevisao.setId((Integer) array[8]);
					conta.setContaMotivoRevisao(contaMotivoRevisao);
				}

				
				//--------------------------------------------------------------
				// Alterado por Yara T. Souza
				// 14/08/2008				
			
				// 10 - Valor Impostos
				if (array[10] != null) {
					conta.setValorImposto((BigDecimal) array[10]);
				}
				//--------------------------------------------------------------
				
				imoveisContas.add(conta);

			}// fim do while
		}
		return imoveisContas;

	}

	/**
	 * Consutlar as contas Historicos do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasHistoricosImovel(Integer idImovel)
			throws ControladorException {
		Collection colecaoContasHistoricos = null;
		Collection<ContaHistorico> imoveisContasHistoricos = null;

		try {

			colecaoContasHistoricos = this.repositorioImovel
					.consultarContasHistoricosImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoContasHistoricos != null
				&& !colecaoContasHistoricos.isEmpty()) {

			imoveisContasHistoricos = new ArrayList();

			Iterator iteratorColecaoContasHistoricos = colecaoContasHistoricos
					.iterator();

			while (iteratorColecaoContasHistoricos.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoContasHistoricos
						.next();

				ContaHistorico contaHistorico = new ContaHistorico();

				// 0 - id Conta
				if (array[0] != null) {
					contaHistorico.setId((Integer) array[0]);
				}

				// 1 - Referencia Contabil
				if (array[1] != null) {
					contaHistorico
							.setAnoMesReferenciaContabil((Integer) array[1]);
				}

				// 2 - Data Vencimento
				if (array[2] != null) {
					contaHistorico.setDataVencimentoConta((Date) array[2]);
				}

				// 3 - Valor Agua
				if (array[3] != null) {
					contaHistorico.setValorAgua((BigDecimal) array[3]);
				}

				// 4 - Valor Esgoto
				if (array[4] != null) {
					contaHistorico.setValorEsgoto((BigDecimal) array[4]);
				}

				// 5 - Valor Debitos
				if (array[5] != null) {
					contaHistorico.setValorDebitos((BigDecimal) array[5]);
				}

				// 6 - Valor Creditos
				if (array[6] != null) {
					contaHistorico.setValorCreditos((BigDecimal) array[6]);
				}

				// 7 - debito Credito Situacao Atual
				DebitoCreditoSituacao debitoCreditoSituacaoAtual = null;
				if (array[7] != null) {
					debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
					debitoCreditoSituacaoAtual
							.setDescricaoAbreviada((String) array[7]);
					debitoCreditoSituacaoAtual
							.setDescricaoDebitoCreditoSituacao((String) array[9]);
					contaHistorico
							.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
				}

				// 8 - Motivo Revis�o
				if (array[8] != null) {
					ContaMotivoRevisao contaMotivoRevisao = new ContaMotivoRevisao();
					contaMotivoRevisao.setId((Integer) array[8]);
					contaHistorico.setContaMotivoRevisao(contaMotivoRevisao);
				}

				//--------------------------------------------------------------
				// Alterado por Yara T. Souza
				// 14/08/2008
				
				// 10 - Valor Impostos
				if (array[10] != null) {
					contaHistorico.setValorImposto((BigDecimal) array[10]);
				}
				//--------------------------------------------------------------
				
				imoveisContasHistoricos.add(contaHistorico);

			}// fim do while
		}
		return imoveisContasHistoricos;

	}

	/**
	 * Consultar os dados de parcelamentos do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 20/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarParcelamentosDebitosImovel(Integer idImovel)
			throws ControladorException {

		Imovel imovel = null;
		Collection colecaoImovel = null;

		try {

			colecaoImovel = this.repositorioImovel
					.consultarParcelamentosDebitosImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			imovel = new Imovel();

			imovel.setId(idImovel);

			Iterator imovelIterator = colecaoImovel.iterator();

			Object[] arrayImovel = (Object[]) imovelIterator.next();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;
			// id ligacaoAguaSituacao - 0
			if (arrayImovel[0] != null) {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();

				ligacaoAguaSituacao.setDescricao((String) arrayImovel[0]);
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			// id ligacaoEsgotoSituacao - 1
			if (arrayImovel[1] != null) {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

				ligacaoEsgotoSituacao.setDescricao((String) arrayImovel[1]);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}

			// id numero de parcelamentos - 3
			if (arrayImovel[2] != null) {
				imovel.setNumeroParcelamento((Short) arrayImovel[2]);
			}
			// id numero de reparcelamentos - 3
			if (arrayImovel[3] != null) {
				imovel.setNumeroReparcelamento((Short) arrayImovel[3]);
			}
			// id numero de reparcelamentos consecutivos - 4
			if (arrayImovel[4] != null) {
				imovel.setNumeroReparcelamentoConsecutivos((Short) arrayImovel[4]);
			}
			// indicador exclus�o - 5
			if (arrayImovel[5] != null) {
				imovel.setIndicadorExclusao((Short) arrayImovel[5]);
			}

		}

		return imovel;

	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Cliente pesquisarClienteUsuarioImovel(Integer idImovel)
			throws ControladorException {

		Cliente cliente = null;
		Collection colecaoCliente = null;

		try {

			colecaoCliente = this.repositorioImovel
					.pesquisarClienteUsuarioImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			cliente = new Cliente();

			Iterator clienteIterator = colecaoCliente.iterator();

			Object[] arrayCliente = (Object[]) clienteIterator.next();

			cliente.setId((Integer) arrayCliente[0]);

			cliente.setNome((String) arrayCliente[1]);

			cliente.setCpf( (String) arrayCliente[2]);
			
			cliente.setCnpj( (String) arrayCliente[3]);
		}

		return cliente;
	}
	
	/**
	 * [UCXXXX] Consultar Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 22/05/2009
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Cliente pesquisarClienteUsuarioImovelExcluidoOuNao(Integer idImovel)
			throws ControladorException {

		Cliente cliente = null;
		Collection colecaoCliente = null;

		try {

			colecaoCliente = this.repositorioImovel
					.pesquisarClienteUsuarioImovelExcluidoOuNao(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			cliente = new Cliente();

			Iterator clienteIterator = colecaoCliente.iterator();

			Object[] arrayCliente = (Object[]) clienteIterator.next();

			cliente.setId((Integer) arrayCliente[0]);

			cliente.setNome((String) arrayCliente[1]);

			cliente.setCpf( (String) arrayCliente[2]);
			
			cliente.setCnpj( (String) arrayCliente[3]);
		}

		return cliente;
	}

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLigacaoAguaEsgoto(Integer idImovel,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
			short indicadorLigacaoAtualizada, short indiacadorConsumoFixado,
			Integer consumoDefinido, short indicadorVolumeFixado)
			throws ControladorException {
		try {
			repositorioImovel.atualizarImovelLigacaoAguaEsgoto(idImovel,
					idLigacaoAguaSituacao, idLigacaoEsgotoSituacao);
			// 3.Caso a situa��o encontrada determine que deve ser alterada a
			// data da liga��o
			if (indicadorLigacaoAtualizada == FiscalizacaoSituacao.INDICADOR_SIM) {
				// 3.1.Caso tenha sido alterado a situa��o de liga��o de �gua
				if (idLigacaoAguaSituacao != null
						&& !idLigacaoAguaSituacao.equals("")) {
					repositorioImovel.atualizarLigacaoAgua(
							idLigacaoAguaSituacao, consumoDefinido,
							indiacadorConsumoFixado);
				}
				// 3.2.Caso tenha sido alterado a situa��o de liga��o de esgoto
				if (idLigacaoEsgotoSituacao != null
						&& !idLigacaoEsgotoSituacao.equals("")) {
					repositorioImovel.atualizarLigacaoEsgoto(
							idLigacaoEsgotoSituacao, consumoDefinido,
							indicadorVolumeFixado);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Respons�vel pela inser��o de um Im�vel Doa��o
	 * 
	 * [UC0389] - Inserir Imovel Doacao
	 * 
	 * @author C�sar Ara�jo, Pedro Alexandre
	 * @date 03/08/2006, 16/11/2006
	 * 
	 * @param imovelDoacao
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirImovelDoacaoRetorno(ImovelDoacao imovelDoacao,
			Usuario usuarioLogado) throws ControladorException {

		Integer retorno = null;

		// ------------ CONTROLE DE ABRANGENCIA ----------------
		Abrangencia abrangencia = new Abrangencia(usuarioLogado, imovelDoacao
				.getImovel());
		// ------------ CONTROLE DE ABRANGENCIA ----------------

		if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.acesso.negado.abrangencia");
		} else {
			retorno = (Integer) this.getControladorUtil().inserir(imovelDoacao);
		}
		return retorno;
	}

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public Integer recuperarIdConsumoTarifa(Integer idImovel)
			throws ControladorException {
		Integer idConsumoTarifa = 0;
		if (idImovel != null && !idImovel.equals("")) {
			try {
				idConsumoTarifa = repositorioImovel
						.pesquisarConsumoTarifa(idImovel);
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}
		return idConsumoTarifa;
	}

	/**
	 * Filtrar o Imovel
	 * 
	 * @author Rafael Santos
	 * @date 27/11/2006
	 */
	public Collection<ClienteImovel> pesquisarImovel(String idImovel, String idLocalidade,
			String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio,
			String cep, String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
			boolean pesquisarImovelManterVinculo,
			boolean pesquisarImovelCondominio, Integer numeroPagina)
			throws ControladorException {

		Collection<ClienteImovel> colecaoImovel = null;
		Collection colecaoDadosImoveis = null;
		try {
			colecaoDadosImoveis = repositorioImovel.pesquisarImovel(idImovel, 
					idLocalidade, codigoSetorComercial, numeroQuadra, lote,
					subLote, codigoCliente, idMunicipio, cep, idBairro,
					idLogradouro, numeroImovelInicial, numeroImovelFinal, pesquisarImovelManterVinculo,
					pesquisarImovelCondominio, numeroPagina);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = null;
		Cliente cliente = null;
		ClienteImovel clienteImovel = null;

		if (colecaoDadosImoveis != null && !colecaoDadosImoveis.isEmpty()) {

			colecaoImovel = new ArrayList();

			Iterator iteratorColecaoDadosImoveis = colecaoDadosImoveis
					.iterator();

			while (iteratorColecaoDadosImoveis.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoDadosImoveis.next();
				cliente = new Cliente();
				imovel = new Imovel();
				clienteImovel = new ClienteImovel();

				LogradouroCep logradouroCep = null;
				if (array[20] != null) {

					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) array[20]);

					// id do Logradouro
					Logradouro logradouro = null;
					if (array[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) array[19]);
						logradouro.setNome("" + array[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// Descri��o logradouro tipo
					if (array[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + array[22]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o logradouro titulo
					if (array[23] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + array[23]);
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					// id do CEP
					Cep objetoCep = null;
					if (array[10] != null) {
						objetoCep = new Cep();
						objetoCep.setCepId((Integer) array[10]);
						objetoCep.setCodigo((Integer) array[16]);
					}

					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setCep(objetoCep);
				}

				imovel.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (array[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) array[21]);

					Bairro bairro = null;
					// nome bairro
					if (array[3] != null) {
						bairro = new Bairro();
						bairro.setId((Integer) array[3]);
						bairro.setNome("" + array[4]);
					}

					Municipio municipio = null;
					// nome municipio
					if (array[5] != null) {
						municipio = new Municipio();
						municipio.setId((Integer) array[5]);
						municipio.setNome("" + array[6]);

						// id da unidade federa��o
						if (array[7] != null) {
							UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
							unidadeFederacao.setId((Integer) array[7]);
							unidadeFederacao.setSigla("" + array[8]);
							municipio.setUnidadeFederacao(unidadeFederacao);
						}

						bairro.setMunicipio(municipio);
					}

					logradouroBairro.setBairro(bairro);
				}

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (array[24] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + array[24]);
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (array[17] != null) {
					imovel.setNumeroImovel("" + array[17]);
				}

				// complemento endere�o
				if (array[18] != null) {
					imovel.setComplementoEndereco("" + array[18]);
				}

				// id imovel
				if (array[25] != null) {
					imovel.setId((Integer) array[25]);
				}

				if (pesquisarImovelManterVinculo) {
					// nome cliente
					if (array[26] != null) {
						cliente.setNome((String) array[26]);
					}

					// id lote
					if (array[27] != null) {
						imovel.setLote(((Short) array[27]).shortValue());
					}

					// id subLote
					if (array[28] != null) {
						imovel.setSubLote(((Short) array[28]).shortValue());
					}

					// id localidade
					if (array[29] != null) {
						Localidade localidade = new Localidade();
						localidade.setId((Integer) array[29]);
						imovel.setLocalidade(localidade);
					}

					// codigo setor comercial
					if (array[30] != null) {
						SetorComercial setorComercial = new SetorComercial();
						setorComercial.setCodigo(((Integer) array[30])
								.intValue());
						imovel.setSetorComercial(setorComercial);
					}

					// numeroQuadra
					if (array[31] != null) {
						Quadra qudra = new Quadra();
						qudra.setNumeroQuadra(((Integer) array[31]).intValue());
						imovel.setQuadra(qudra);
					}
					
					if (array[32] != null) {
						imovel.setUltimaAlteracao((Date) array[32]);
					}

					if (array[33] != null) {
						Logradouro perimetroInicial = new Logradouro();
						perimetroInicial.setId((Integer) array[33]);
						
						if (array[34] != null) {
							perimetroInicial.setNome((String) array[34]);
						}
						
						if (array[35] != null) {
							LogradouroTipo logradouroTipo = new LogradouroTipo();
							logradouroTipo.setDescricaoAbreviada((String) array[35]);
							perimetroInicial.setLogradouroTipo(logradouroTipo);
						}
						
						if (array[36] != null) {
							LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
							logradouroTitulo.setDescricaoAbreviada((String) array[36]);
							perimetroInicial.setLogradouroTitulo(logradouroTitulo);
						}
						
						imovel.setPerimetroInicial(perimetroInicial);
					}
					
					if (array[37] != null) {
						Logradouro perimetroFinal = new Logradouro();
						perimetroFinal.setId((Integer) array[37]);
						
						if (array[38] != null) {
							perimetroFinal.setNome((String) array[38]);
						}
						
						if (array[39] != null) {
							LogradouroTipo logradouroTipo = new LogradouroTipo();
							logradouroTipo.setDescricaoAbreviada((String) array[39]);
							perimetroFinal.setLogradouroTipo(logradouroTipo);
						}
						
						if (array[40] != null) {
							LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
							logradouroTitulo.setDescricaoAbreviada((String) array[40]);
							perimetroFinal.setLogradouroTitulo(logradouroTitulo);
						}
						
						imovel.setPerimetroFinal(perimetroFinal);
					}
					
				} else {
					// id lote
					if (array[26] != null) {
						imovel.setLote(((Short) array[26]).shortValue());
					}

					// id subLote
					if (array[27] != null) {
						imovel.setSubLote(((Short) array[27]).shortValue());
					}

					// id localidade
					if (array[28] != null) {
						Localidade localidade = new Localidade();
						localidade.setId((Integer) array[28]);
						imovel.setLocalidade(localidade);
					}

					// codigo setor comercial
					if (array[29] != null) {
						SetorComercial setorComercial = new SetorComercial();
						setorComercial.setCodigo(((Integer) array[29])
								.intValue());
						imovel.setSetorComercial(setorComercial);
					}

					// numeroQuadra
					if (array[30] != null) {
						Quadra qudra = new Quadra();
						qudra.setNumeroQuadra(((Integer) array[30]).intValue());
						imovel.setQuadra(qudra);
					}

					if (array[31] != null) {
						imovel.setUltimaAlteracao((Date) array[31]);
					}
					
					if (array[32] != null) {
						Logradouro perimetroInicial = new Logradouro();
						perimetroInicial.setId((Integer) array[32]);
						
						if (array[33] != null) {
							perimetroInicial.setNome((String) array[33]);
						}
						
						if (array[34] != null) {
							LogradouroTipo logradouroTipo = new LogradouroTipo();
							logradouroTipo.setDescricaoAbreviada((String) array[34]);
							perimetroInicial.setLogradouroTipo(logradouroTipo);
						}
						
						if (array[35] != null) {
							LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
							logradouroTitulo.setDescricaoAbreviada((String) array[35]);
							perimetroInicial.setLogradouroTitulo(logradouroTitulo);
						}
						
						imovel.setPerimetroInicial(perimetroInicial);
					}
					
					if (array[36] != null) {
						Logradouro perimetroFinal = new Logradouro();
						perimetroFinal.setId((Integer) array[36]);
						
						if (array[37] != null) {
							perimetroFinal.setNome((String) array[37]);
						}
						
						if (array[38] != null) {
							LogradouroTipo logradouroTipo = new LogradouroTipo();
							logradouroTipo.setDescricaoAbreviada((String) array[38]);
							perimetroFinal.setLogradouroTipo(logradouroTipo);
						}
						
						if (array[39] != null) {
							LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
							logradouroTitulo.setDescricaoAbreviada((String) array[39]);
							perimetroFinal.setLogradouroTitulo(logradouroTitulo);
						}
						
						imovel.setPerimetroFinal(perimetroFinal);
					}

				}

				clienteImovel.setImovel(imovel);
				clienteImovel.setCliente(cliente);

				colecaoImovel.add(clienteImovel);
			}
		}

		return colecaoImovel;
	}

	/**
	 * Pesquisa a quantidade de Imoveis
	 * 
	 * @author Rafael Santos
	 * @date 27/11/2006
	 */
	public Integer pesquisarQuantidadeImovel(String idImovel,
			String idLocalidade, String codigoSetorComercial,
			String numeroQuadra, String lote, String subLote,
			String codigoCliente, String idMunicipio, String cep,
			String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
			boolean pesquisarImovelManterVinculo,
			boolean pesquisarImovelCondominio) throws ControladorException {

		Object quantidade = null;
		Integer retorno = null;

		try {
			quantidade = repositorioImovel.pesquisarQuantidadeImovel(idImovel,
					idLocalidade, codigoSetorComercial, numeroQuadra, lote,
					subLote, codigoCliente, idMunicipio, cep, idBairro,
					idLogradouro, numeroImovelInicial, numeroImovelFinal, pesquisarImovelManterVinculo,
					pesquisarImovelCondominio);

		} catch (ErroRepositorioException ex) {
			if (ex.getMessage().equals(ConstantesSistema.ERRO_SQL_CONVERSSAO_ALFANUMERICO_PARA_NUMERICO)){
				throw new ControladorException("atencao.pesquisa_nao_realizada_devido_caracteres_alfanumericos_campo");
			}else{
				throw new ControladorException("erro.sistema", ex);
			}
		}

		if (quantidade != null) {
			retorno = (Integer) quantidade;

		}

		return retorno;
	}

	/**
	 * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
	 * Matricula e Endere�o
	 * 
	 * @author Rafael Santos
	 * @date 27/11/2006
	 */
	/*
	 * public Collection pesquisarImovelInscricao(String idImovel, String
	 * idLocalidade, String codigoSetorComercial, String numeroQuadra, String
	 * lote, String subLote, String codigoCliente, String idMunicipio, String
	 * cep, String idBairro, String idLogradouro, boolean
	 * pesquisarImovelCondominio, Integer numeroPagina) throws
	 * ControladorException {
	 * 
	 * Collection colecaoClienteImovel = null; Collection colecaoDadosImoveis =
	 * null; try { colecaoDadosImoveis =
	 * repositorioImovel.pesquisarImovelInscricao( idImovel, idLocalidade,
	 * codigoSetorComercial, numeroQuadra, lote, subLote, codigoCliente,
	 * idMunicipio, cep, idBairro, idLogradouro, pesquisarImovelCondominio,
	 * numeroPagina); } catch (ErroRepositorioException ex) { throw new
	 * ControladorException("erro.sistema", ex); }
	 * 
	 * Imovel imovel = null; ClienteImovel clienteImovel = null;
	 * 
	 * if (colecaoDadosImoveis != null && !colecaoDadosImoveis.isEmpty()) {
	 * 
	 * colecaoClienteImovel = new ArrayList();
	 * 
	 * Iterator iteratorColecaoDadosImoveis = colecaoDadosImoveis .iterator();
	 * 
	 * while (iteratorColecaoDadosImoveis.hasNext()) {
	 * 
	 * Object[] array = (Object[]) iteratorColecaoDadosImoveis.next();
	 * 
	 * imovel = new Imovel();
	 * 
	 * LogradouroCep logradouroCep = null; if (array[20] != null) {
	 * 
	 * logradouroCep = new LogradouroCep(); logradouroCep.setId((Integer)
	 * array[20]); // id do Logradouro Logradouro logradouro = null; if
	 * (array[19] != null) { logradouro = new Logradouro();
	 * logradouro.setId((Integer) array[19]); logradouro.setNome("" + array[0]); }
	 * LogradouroTipo logradouroTipo = null; // Descri��o logradouro tipo if
	 * (array[22] != null) { logradouroTipo = new LogradouroTipo();
	 * logradouroTipo.setDescricao("" + array[22]);
	 * logradouro.setLogradouroTipo(logradouroTipo); } LogradouroTitulo
	 * logradouroTitulo = null; // Descri��o logradouro titulo if (array[23] !=
	 * null) { logradouroTitulo = new LogradouroTitulo();
	 * logradouroTitulo.setDescricao("" + array[23]);
	 * logradouro.setLogradouroTitulo(logradouroTitulo); } // id do CEP Cep
	 * objetoCep = null; if (array[10] != null) { objetoCep = new Cep();
	 * objetoCep.setCepId((Integer) array[10]); objetoCep.setCodigo((Integer)
	 * array[16]); }
	 * 
	 * logradouroCep.setLogradouro(logradouro); logradouroCep.setCep(objetoCep); }
	 * 
	 * imovel.setLogradouroCep(logradouroCep);
	 * 
	 * LogradouroBairro logradouroBairro = null; if (array[21] != null) {
	 * 
	 * logradouroBairro = new LogradouroBairro();
	 * logradouroBairro.setId((Integer) array[21]);
	 * 
	 * Bairro bairro = null; // nome bairro if (array[3] != null) { bairro = new
	 * Bairro(); bairro.setId((Integer) array[3]); bairro.setNome("" +
	 * array[4]); }
	 * 
	 * Municipio municipio = null; // nome municipio if (array[5] != null) {
	 * municipio = new Municipio(); municipio.setId((Integer) array[5]);
	 * municipio.setNome("" + array[6]); // id da unidade federa��o if (array[7] !=
	 * null) { UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
	 * unidadeFederacao.setId((Integer) array[7]); unidadeFederacao.setSigla("" +
	 * array[8]); municipio.setUnidadeFederacao(unidadeFederacao); }
	 * 
	 * bairro.setMunicipio(municipio); }
	 * 
	 * logradouroBairro.setBairro(bairro); }
	 * 
	 * imovel.setLogradouroBairro(logradouroBairro); // descricao de endere�o
	 * refer�ncia if (array[24] != null) { EnderecoReferencia enderecoReferencia =
	 * new EnderecoReferencia(); enderecoReferencia.setDescricao("" +
	 * array[24]); imovel.setEnderecoReferencia(enderecoReferencia); } // numero
	 * imovel if (array[17] != null) { imovel.setNumeroImovel("" + array[17]); } //
	 * complemento endere�o if (array[18] != null) {
	 * imovel.setComplementoEndereco("" + array[18]); } // id imovel if
	 * (array[25] != null) { imovel.setId((Integer) array[25]); } // id lote if
	 * (array[26] != null) { imovel.setLote(((Short) array[26]).shortValue()); } //
	 * id subLote if (array[27] != null) { imovel.setSubLote(((Short)
	 * array[27]).shortValue()); } // id localidade if (array[28] != null) {
	 * Localidade localidade = new Localidade(); localidade.setId((Integer)
	 * array[28]); imovel.setLocalidade(localidade); } // id imovel if
	 * (array[29] != null) { SetorComercial setorComercial = new
	 * SetorComercial(); setorComercial.setCodigo(((Integer)
	 * array[29]).intValue()); imovel.setSetorComercial(setorComercial); } // id
	 * numeroQuadra if (array[30] != null) { Quadra qudra = new Quadra();
	 * qudra.setNumeroQuadra(((Integer) array[30]).intValue());
	 * imovel.setQuadra(qudra); }
	 * 
	 * Cliente cliente = new Cliente(); // nome cliente if (array[31] != null) {
	 * cliente.setNome((String) array[31]); }
	 * 
	 * clienteImovel = new ClienteImovel(); clienteImovel.setImovel(imovel);
	 * clienteImovel.setCliente(cliente);
	 * 
	 * colecaoClienteImovel.add(clienteImovel); } }
	 * 
	 * return colecaoClienteImovel; }
	 */

	/**
	 * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
	 * Matricula e Endere�o
	 * 
	 * @author Rafael Santos, Raphael Rossiter
	 * @date 27/11/2006
	 */
	public Collection pesquisarImovelInscricao(String idImovel,
			String idLocalidade, String codigoSetorComercial,
			String numeroQuadra, String lote, String subLote,
			String codigoCliente, String idMunicipio, String cep,
			String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal, 
			boolean pesquisarImovelCondominio, Integer numeroPagina)
			throws ControladorException {

		Collection colecaoClienteImovel = null;
		Collection colecaoDadosImoveis = null;
		try {
			colecaoDadosImoveis = repositorioImovel
					.pesquisarImovelInscricaoNew(idImovel, idLocalidade,
							codigoSetorComercial, numeroQuadra, lote, subLote,
							codigoCliente, idMunicipio, cep, idBairro,
							idLogradouro, numeroImovelInicial, numeroImovelFinal, 
							pesquisarImovelCondominio, numeroPagina);

		} catch (ErroRepositorioException ex) {
			//CRC3586 
			//autor: Anderson Italo
			//data: 03/02/2010
			//caso seja exce��o devido a convers�o de alfanumerico para numerico
			if (ex.getMessage().equals(ConstantesSistema.ERRO_SQL_CONVERSSAO_ALFANUMERICO_PARA_NUMERICO)){
				throw new ControladorException("atencao.pesquisa_nao_realizada_devido_caracteres_alfanumericos_campo");
			}else{
				throw new ControladorException("erro.sistema", ex);
			}
		}

		Imovel imovel = null;
		ClienteImovel clienteImovel = null;

		if (colecaoDadosImoveis != null && !colecaoDadosImoveis.isEmpty()) {

			colecaoClienteImovel = new ArrayList();

			Iterator iteratorColecaoDadosImoveis = colecaoDadosImoveis
					.iterator();

			while (iteratorColecaoDadosImoveis.hasNext()) {

				Object[] array = (Object[]) iteratorColecaoDadosImoveis.next();

				imovel = new Imovel();

				LogradouroCep logradouroCep = null;
				if (array[20] != null) {

					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) array[20]);

					// id do Logradouro
					Logradouro logradouro = null;
					if (array[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) array[19]);
						logradouro.setNome("" + array[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// Descri��o logradouro tipo
					if (array[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + array[22]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o logradouro titulo
					if (array[23] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + array[23]);
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					// id do CEP
					Cep objetoCep = null;
					if (array[10] != null) {
						objetoCep = new Cep();
						objetoCep.setCepId((Integer) array[10]);
						objetoCep.setCodigo((Integer) array[16]);
					}

					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setCep(objetoCep);
				}

				imovel.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (array[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) array[21]);

					Bairro bairro = null;
					// nome bairro
					if (array[3] != null) {
						bairro = new Bairro();
						bairro.setId((Integer) array[3]);
						bairro.setNome("" + array[4]);
					}

					Municipio municipio = null;
					// nome municipio
					if (array[5] != null) {
						municipio = new Municipio();
						municipio.setId((Integer) array[5]);
						municipio.setNome("" + array[6]);

						// id da unidade federa��o
						if (array[7] != null) {
							UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
							unidadeFederacao.setId((Integer) array[7]);
							unidadeFederacao.setSigla("" + array[8]);
							municipio.setUnidadeFederacao(unidadeFederacao);
						}

						bairro.setMunicipio(municipio);
					}

					logradouroBairro.setBairro(bairro);
				}

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (array[24] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + array[24]);
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (array[17] != null) {
					imovel.setNumeroImovel("" + array[17]);
				}

				// complemento endere�o
				if (array[18] != null) {
					imovel.setComplementoEndereco("" + array[18]);
				}

				// id imovel
				if (array[25] != null) {
					imovel.setId((Integer) array[25]);
				}

				// id lote
				if (array[26] != null) {
					imovel.setLote(((Short) array[26]).shortValue());
				}

				// id subLote
				if (array[27] != null) {
					imovel.setSubLote(((Short) array[27]).shortValue());
				}

				// id localidade
				if (array[28] != null) {
					Localidade localidade = new Localidade();
					localidade.setId((Integer) array[28]);
					imovel.setLocalidade(localidade);
				}

				// id imovel
				if (array[29] != null) {
					SetorComercial setorComercial = new SetorComercial();
					setorComercial.setCodigo(((Integer) array[29]).intValue());
					imovel.setSetorComercial(setorComercial);
				}

				// id numeroQuadra
				if (array[30] != null) {
					Quadra qudra = new Quadra();
					qudra.setNumeroQuadra(((Integer) array[30]).intValue());
					imovel.setQuadra(qudra);
				}

				Cliente cliente = new Cliente();
				// nome cliente
				if (array[31] != null) {
					cliente.setNome((String) array[31]);
				}
				
				if (array[32] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) array[32]);
					
					if (array[33] != null) {
						perimetroInicial.setNome((String) array[33]);
					}
					
					if (array[34] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) array[34]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (array[35] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) array[35]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroInicial(perimetroInicial);
				}
				
				if (array[36] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) array[36]);
					
					if (array[37] != null) {
						perimetroFinal.setNome((String) array[37]);
					}
					
					if (array[38] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) array[38]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (array[39] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) array[39]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroFinal(perimetroFinal);
				}

				clienteImovel = new ClienteImovel();
				clienteImovel.setImovel(imovel);
				clienteImovel.setCliente(cliente);

				colecaoClienteImovel.add(clienteImovel);
			}
		}

		return colecaoClienteImovel;
	}

	/**
	 * [UC0490] Informar Ocorrencia de Cadastro e/ou Anormalidade de Elo
	 * 
	 * @author Tiago Moreno
	 * @date 27/11/2006
	 * 
	 * @param idImovel
	 * @param idOcorrenciaCadastro
	 * @param idAnormalidadeElo
	 * @param dataOcorrenciaCadastro
	 * @param dataAnormalidadeElo
	 * @param uploadPictureCadastro
	 * @param uploadPictureAnormalidade
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 */
	public void informarOcorrenciaCadastroAnormalidadeElo(String idImovel,
			String idOcorrenciaCadastro, String idAnormalidadeElo,
			String dataOcorrenciaCadastro, String dataAnormalidadeElo,
			byte[] uploadPictureCadastro, byte[] uploadPictureAnormalidade,
			Usuario usuarioLogado, Collection colecaoIdCadastroOcorrenciaRemover,
			Collection colecaoIdAnormalidadeRemover) throws ControladorException {

		// validando se o imovel existe na base
		if (this.verificarExistenciaImovel(new Integer(idImovel)) == 0) {
			throw new ControladorException("atencao.imovel.inexistente");
		}

		// validando se o imovel foi exclu�do
		Imovel imovelDigitado = this.pesquisarImovelDigitado(new Integer(
				idImovel));
		if (imovelDigitado.getIndicadorExclusao() != null
				&& imovelDigitado.getIndicadorExclusao().equals("1")) {
			throw new ControladorException("atencao.imovel.excluido");
		}

		// validando se foi informado ocorrencia de cadastro ou anormalidade de
		// elo
		if ((idOcorrenciaCadastro == null || idOcorrenciaCadastro
				.equalsIgnoreCase(""))
				&& (idAnormalidadeElo == null || idAnormalidadeElo
						.equalsIgnoreCase(""))) {
			throw new ControladorException(
					"atencao.informe_ocorrencia_cadastro_anormalidade_elo");
		}

		// instanciando o Objeto ImovelCadastroOcorrencia
		ImovelCadastroOcorrencia imovelCadastroOcorrencia = new ImovelCadastroOcorrencia();

		// instanciando o Objeto ImovelEloAnormalidade
		ImovelEloAnormalidade imovelEloAnormalidade = new ImovelEloAnormalidade();
		
		// ------------ REGISTRAR TRANSA��O ROTA----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
			    Operacao.OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR,
			    imovelDigitado.getId(),imovelDigitado.getId(),
			    new UsuarioAcaoUsuarioHelper(usuarioLogado,
			    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		// ------------ REGISTRAR TRANSA��O ROTA----------------------------

		
/*		
		// ------------ REGISTRAR TRANSA��O ROTA----------------------------
		RegistradorOperacao registradorOperacaoRota = new RegistradorOperacao(
				Operacao.OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		imovelCadastroOcorrencia.setOperacaoEfetuada(operacaoEfetuada);
		imovelCadastroOcorrencia.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacaoRota.registrarOperacao(imovelCadastroOcorrencia);

		imovelEloAnormalidade.setOperacaoEfetuada(operacaoEfetuada);
		imovelEloAnormalidade.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacaoRota.registrarOperacao(imovelEloAnormalidade);
		// ------------ REGISTRAR TRANSA��O ROTA----------------------------
*/
		// verifica se foi informado Ocorrencia de cadastro
		if (idOcorrenciaCadastro != null
				&& !idOcorrenciaCadastro.equalsIgnoreCase("")) {

			// Instancia o Objeto Imovel e setando o valor do Id preenchido
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(idImovel));

			// setando o valor do Imovel do Objeto Principal
			imovelCadastroOcorrencia.setImovel(imovel);

			// Instancia o Objeto CadastroOcorrencia e setando o valor do id
			// escolhido
			
			CadastroOcorrencia cadastroOcorrencia = new CadastroOcorrencia();
			cadastroOcorrencia.setId(new Integer(idOcorrenciaCadastro));
			Filtro filtro = cadastroOcorrencia.retornaFiltro();
			Collection <CadastroOcorrencia> collectionCadastroOcorrencia = getControladorUtil().pesquisar(filtro, CadastroOcorrencia.class.getName());
			cadastroOcorrencia = (CadastroOcorrencia)Util.retonarObjetoDeColecao(collectionCadastroOcorrencia);
			
			
			
			// setando o valor de Cadastro Ocorrencia do Objeto Principal
			imovelCadastroOcorrencia.setCadastroOcorrencia(cadastroOcorrencia);

			// setando a data da ocorrencia do cadastro
			if (dataOcorrenciaCadastro != null
					&& !dataOcorrenciaCadastro.equalsIgnoreCase("")) {

				Calendar data = Calendar.getInstance();

				data.setTime(Util
						.converteStringParaDate(dataOcorrenciaCadastro));

				if (data.get(Calendar.YEAR) < 1985) {
					throw new ControladorException(
							"atencao.data.ocorrencia.nao.inferior.1985");
				} else {
					if (data.getTime().after(new Date())) {
						throw new ControladorException(
								"atencao.data_inicio_ocorrencia");
					} else {
						imovelCadastroOcorrencia
								.setDataOcorrencia(Util
										.converteStringParaDate(dataOcorrenciaCadastro));
					}
				}
			}

			// setando a foto da ocorrencia do cadastro
			if (uploadPictureCadastro != null) {
				imovelCadastroOcorrencia
						.setFotoOcorrencia(uploadPictureCadastro);
			}

			// setando a data/hora no campo Ultima Alteracao
			imovelCadastroOcorrencia.setUltimaAlteracao(new Date());

			registradorOperacao.registrarOperacao(imovelCadastroOcorrencia);
			
			//Remove as ocorrencias do imovel.
			if ( colecaoIdCadastroOcorrenciaRemover != null && 
					!colecaoIdCadastroOcorrenciaRemover.equals("") ) {
				
				Iterator iteratorColecaoImovelCadastroOcorrencia = colecaoIdCadastroOcorrenciaRemover.iterator();
				
				while ( iteratorColecaoImovelCadastroOcorrencia.hasNext() ) {
					ImovelCadastroOcorrencia imovelcadOcorrencia = (ImovelCadastroOcorrencia) iteratorColecaoImovelCadastroOcorrencia.next();
					getControladorUtil().remover(imovelcadOcorrencia);
				}
			}
			
			//Remove as anormalidades do imovel.
			if ( colecaoIdAnormalidadeRemover != null && 
					!colecaoIdAnormalidadeRemover.equals("") ) {
				
				Iterator iteratorColecaoIdAnormalidade = colecaoIdAnormalidadeRemover.iterator();
				
				while ( iteratorColecaoIdAnormalidade.hasNext() ) {
					ImovelEloAnormalidade imoveleloAnormalidade = (ImovelEloAnormalidade) iteratorColecaoIdAnormalidade.next();
					getControladorUtil().remover(imoveleloAnormalidade);
				}
			}
			
			// inserindo no banco
			getControladorUtil().inserir(imovelCadastroOcorrencia);
		}

		// verifica se foi informado Anormalidade de Elo
		if (idAnormalidadeElo != null
				&& !idAnormalidadeElo.equalsIgnoreCase("")) {

			// Instancia o Objeto Imovel e setando o valor do Id preenchido
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(idImovel));

			// setando o valor do Imovel do Objeto Principal
			imovelEloAnormalidade.setImovel(imovel);

			// Instancia o Objeto CadastroOcorrencia e setando o valor do id
			// escolhido
			EloAnormalidade eloAnormalidade = new EloAnormalidade();
			eloAnormalidade.setId(new Integer(idAnormalidadeElo));
			Filtro filtro = eloAnormalidade.retornaFiltro();
			Collection <EloAnormalidade> collectionEloAnormalidade = getControladorUtil().pesquisar(filtro, EloAnormalidade.class.getName());
			eloAnormalidade = (EloAnormalidade)Util.retonarObjetoDeColecao(collectionEloAnormalidade);
			

			// setando o valor de Cadastro Ocorrencia do Objeto Principal
			imovelEloAnormalidade.setEloAnormalidade(eloAnormalidade);

			// setando a data da ocorrencia do cadastro
			if (dataAnormalidadeElo != null
					&& !dataAnormalidadeElo.equalsIgnoreCase("")) {

				Calendar data = Calendar.getInstance();

				data.setTime(Util.converteStringParaDate(dataAnormalidadeElo));

				if (data.get(Calendar.YEAR) < 1985) {
					throw new ControladorException(
							"atencao.data.anormalidade.nao.inferior.1985");
				} else {
					if (data.getTime().after(new Date())) {
						throw new ControladorException(
								"atencao.data_inicio_anormalidade");
					} else {
						imovelEloAnormalidade.setDataAnormalidade(Util
								.converteStringParaDate(dataAnormalidadeElo));
					}
				}
			}

			// setando a foto da ocorrencia do cadastro
			if (uploadPictureAnormalidade != null) {
				imovelEloAnormalidade
						.setFotoAnormalidade(uploadPictureAnormalidade);
			}
			
			//Remove as ocorrencias do imovel.
			if ( colecaoIdCadastroOcorrenciaRemover != null && 
					!colecaoIdCadastroOcorrenciaRemover.equals("") ) {
				
				Iterator iteratorColecaoImovelCadastroOcorrencia = colecaoIdCadastroOcorrenciaRemover.iterator();
				
				while ( iteratorColecaoImovelCadastroOcorrencia.hasNext() ) {
					ImovelCadastroOcorrencia imovelcadOcorrencia = (ImovelCadastroOcorrencia) iteratorColecaoImovelCadastroOcorrencia.next();
					getControladorUtil().remover(imovelcadOcorrencia);
				}
			}
			
			//Remove as anormalidades do imovel.
			if ( colecaoIdAnormalidadeRemover != null && 
					!colecaoIdAnormalidadeRemover.equals("") ) {
				
				Iterator iteratorColecaoIdAnormalidade = colecaoIdAnormalidadeRemover.iterator();
				
				while ( iteratorColecaoIdAnormalidade.hasNext() ) {
					ImovelEloAnormalidade imoveleloAnormalidade = (ImovelEloAnormalidade) iteratorColecaoIdAnormalidade.next();
					getControladorUtil().remover(imoveleloAnormalidade);
				}
			}

			// setando a data de ultima alteracao
			imovelEloAnormalidade.setUltimaAlteracao(new Date());
			
			registradorOperacao.registrarOperacao(imovelEloAnormalidade);
			// inserindo no banco
			getControladorUtil().inserir(imovelEloAnormalidade);
		}
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da liga��o de agua
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdLigacaoAguaSituacao(Integer idImovel)
			throws ControladorException {

		Integer retorno = null;

		try {
			retorno = repositorioImovel
					.pesquisaridLigacaoAguaSituacao(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da liga��o de esgoto
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdLigacaoEsgotoSituacao(Integer idImovel)
			throws ControladorException {

		Integer retorno = null;

		try {
			retorno = repositorioImovel
					.pesquisaridLigacaoEsgotoSituacao(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da ligacao de agua
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarTarifaImovel(Integer idImovel)
			throws ControladorException {

		Integer retorno = null;

		try {
			retorno = repositorioImovel
					.pesquisaridLigacaoEsgotoSituacao(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0490] Informar Situa��o de Cobran�a
	 * 
	 * @author Tiago Moreno,Vivianne Sousa
	 * @date 09/12/2006,12/05/2010
	 * 
	 * @param imovel
	 * @param situacaoCobranca
	 * @param cliente
	 * @param dataImplanta��o
	 * @param anoMesInicio
	 * @param anoMesFim
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 */
	public void inserirImovelSitucaoCobranca(Imovel imovel,
			CobrancaSituacao cobrancaSituacao, Cliente cliente,
			Cliente clienteEscritorio, Cliente clienteAdvogado,
			Date dataImplantacao, Integer anoMesInicio, Integer anoMesFim,
			Usuario usuarioLogado) throws ControladorException {

		ImovelCobrancaSituacao imovelCobrancaSituacao = new ImovelCobrancaSituacao();

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		// ------------ REGISTRAR TRANSA��O ROTA----------------------------
		RegistradorOperacao registradorOperacaoRota = new RegistradorOperacao(
				Operacao.OPERACAO_IMOVEL_COBRANCA_SITUACAO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_IMOVEL_COBRANCA_SITUACAO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		imovelCobrancaSituacao.setOperacaoEfetuada(operacaoEfetuada);
		imovelCobrancaSituacao.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacaoRota.registrarOperacao(imovelCobrancaSituacao);

		// ------------ REGISTRAR TRANSA��O ROTA----------------------------

		Integer existe = this.verificarExistenciaImovel(new Integer(imovel
				.getId()));

		if (existe != null && existe != 0) {
			imovelCobrancaSituacao.setImovel(imovel);
		} else {
			throw new ActionServletException("atencao.imovel.inexistente");
		}

		if (cliente.getId() != null) {
			Cliente cliente2 = getControladorCliente()
					.pesquisarClienteDigitado(new Integer(cliente.getId()));
			if (cliente2 != null) {
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_ID, cliente.getId()
								.toString()));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, imovel.getId()
								.toString()));
				Collection colecaoClienteImovel = getControladorUtil()
						.pesquisar(filtroClienteImovel,
								ClienteImovel.class.getName());

				if (colecaoClienteImovel == null
						|| colecaoClienteImovel.isEmpty()) {
					throw new ControladorException(
							"atencao.cliente_informado_nao_corresponde_imovel");
				} else {
					imovelCobrancaSituacao.setCliente(cliente);
				}
			} else {
				throw new ControladorException("atencao.cliente.inexistente");
			}
		}

		// TESTANDO O CLIENTE ESCRITORIO DE ADVOCACIA (Kassia Albuquerque)

		if (clienteEscritorio != null && !clienteEscritorio.equals("")) {

			if (clienteEscritorio.getClienteTipo() == null
					|| !clienteEscritorio.getClienteTipo()
							.getIndicadorPessoaFisicaJuridica().equals(
									ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
				throw new ControladorException(
						"atencao.escritorio_pessoa_juridica");
			} else if ( clienteEscritorio.getRamoAtividade() != null ) {
					
					if (clienteEscritorio.getRamoAtividade() != null
					&& cobrancaSituacao.getRamoAtividade() != null 
					&& clienteEscritorio.getRamoAtividade().getId()
						.equals(cobrancaSituacao.getRamoAtividade().getId() ) )  {
				
						imovelCobrancaSituacao.setEscritorio(clienteEscritorio);
					} else {
						throw new ControladorException(
						"atencao.ramo_atividade_escritorio_advocacia_igual_situacao_cobranca");
				
					}
			} else {
				throw new ControladorException(
				"atencao.ramo_atividade_juridica");
			}
		}

		// TESTANDO O CLIENTE ADVOGADO (Kassia Albuquerque)

		if (clienteAdvogado != null && !clienteAdvogado.equals("")) {

			if (clienteAdvogado.getClienteTipo() == null
					|| !clienteAdvogado.getClienteTipo()
							.getIndicadorPessoaFisicaJuridica().equals(
									ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				throw new ControladorException("atencao.advogado_pessoa_fisica");
			} else if ( clienteAdvogado.getProfissao() != null ) {
					
					
					if ( clienteAdvogado.getProfissao().getId() != null
							&& cobrancaSituacao.getProfissao() != null
							&& clienteAdvogado.getProfissao().getId()
							.equals(cobrancaSituacao.getProfissao().getId() ) )  {
				
						imovelCobrancaSituacao.setAdvogado(clienteAdvogado);
			
					} else {
						throw new ControladorException("atencao.profissao_advogado_igual_situacao_cobranca");
				
					}
			} else {
				throw new ControladorException("atencao.profissao_advogado");
			}
		}

		imovelCobrancaSituacao.setCobrancaSituacao(cobrancaSituacao);
		imovelCobrancaSituacao.setDataImplantacaoCobranca(dataImplantacao);

		if (anoMesFim != null) {
			if (sistemaParametro.getAnoMesFaturamento().intValue() < anoMesFim) {
				throw new ControladorException(
						"atencao.ano_mes_informado_maior_que.ano.mes.faturamento",
						null, Util.formatarAnoMesParaMesAno(sistemaParametro
								.getAnoMesFaturamento().intValue()));
			} else {
				imovelCobrancaSituacao.setAnoMesReferenciaFinal(anoMesFim);
			}
		}
		if (anoMesInicio != null) {
			imovelCobrancaSituacao.setAnoMesReferenciaInicio(anoMesInicio);
		}

		imovelCobrancaSituacao.setUltimaAlteracao(new Date());

		getControladorUtil().inserir(imovelCobrancaSituacao);

		//CRC3323 - comentado por Vivianne Sousa - analista:Fatima Sampaio - 12/05/2010
//		Imovel imovelAtualiza = this.pesquisarImovelDigitado(imovel.getId());
//
//		if (imovelAtualiza.getIndicadorExclusao().equals("1")) {
//			throw new ControladorException("atencao.imovel.excluido");
//		}
//
//		imovelAtualiza.setCobrancaSituacao(imovelCobrancaSituacao
//				.getCobrancaSituacao());
//		imovelAtualiza.setUltimaAlteracao(new Date());
//
//		imovelAtualiza.setOperacaoEfetuada(operacaoEfetuada);
//		imovelAtualiza.adicionarUsuario(usuarioLogado,
//				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacaoRota.registrarOperacao(imovelAtualiza);
//
//		getControladorUtil().atualizar(imovelAtualiza);

		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaSituacao.ID, cobrancaSituacao.getId()));
		filtroCobrancaSituacao
				.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
		Collection cCS = (Collection) getControladorUtil().pesquisar(
				filtroCobrancaSituacao, CobrancaSituacao.class.getName());
		CobrancaSituacao cS = (CobrancaSituacao) cCS.iterator().next();
		if (cS.getContaMotivoRevisao() != null) {
			Collection conta = (Collection) getControladorFaturamento()
					.obterContasImovelIntervalo(imovel.getId(),
							DebitoCreditoSituacao.NORMAL,
							DebitoCreditoSituacao.INCLUIDA,
							DebitoCreditoSituacao.RETIFICADA,
							imovelCobrancaSituacao.getAnoMesReferenciaInicio(),
							imovelCobrancaSituacao.getAnoMesReferenciaFinal(),
							null);

			if (conta != null && !conta.isEmpty()) {
				getControladorFaturamento().colocarRevisaoConta(conta, null,
						cS.getContaMotivoRevisao(), usuarioLogado);
			}
		}
	}

	/**
	 * [UC0490] Informar Situa��o de Cobran�a
	 * 
	 * @author Tiago Moreno,Vivianne Sousa
	 * @date 09/12/2006,11/05/2010
	 * 
	 * @param imovel
	 * @param situacaoCobranca
	 * @param cliente
	 * @param dataImplanta��o
	 * @param anoMesInicio
	 * @param anoMesFim
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 */

	public void retirarImovelSitucaoCobranca(Integer idImovel,
			Usuario usuarioLogado,String[] idsImovelCobrancaSituacao) throws ControladorException {

		Integer existe = this.verificarExistenciaImovel(idImovel);

		if (existe == null || existe == 0) {
			throw new ControladorException("atencao.imovel.inexistente");
		}
		
		if(idsImovelCobrancaSituacao != null){
			Collection colecaoImovelCobrancaSituacao = null;
			try {
				colecaoImovelCobrancaSituacao = repositorioImovel.
					pesquisarImovelCobrancaSituacaoPorImovel(idsImovelCobrancaSituacao);
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			if(colecaoImovelCobrancaSituacao != null && !colecaoImovelCobrancaSituacao.isEmpty()){

				Iterator iterImovelCobrancaSituacao = colecaoImovelCobrancaSituacao.iterator();
				
				while (iterImovelCobrancaSituacao.hasNext()) {
					ImovelCobrancaSituacao iCS = (ImovelCobrancaSituacao) iterImovelCobrancaSituacao.next();
					
					//3.1.1. Atualiza da data de encerramento da situa��o de cobran�a 
					iCS.setDataRetiradaCobranca(new Date());
					iCS.setUltimaAlteracao(new Date());

					//3.1.4. Caso o intervalo de refer�ncia dos d�bitos associados � situa��o de cobran�a 
					//esteja preenchido (ISCB_AMREFERENCIAINICIO e ISCB_AMREFERENCIAFINAL 
					//com o valor diferente de nulo)
					if(iCS.getAnoMesReferenciaInicio() != null 
							&& iCS.getAnoMesReferenciaFinal() != null){
						
						if(iCS.getContaMotivoRevisao() != null &&
								iCS.getCobrancaSituacao().getContaMotivoRevisao() != null){
							
							//3.1.4.1.	Seleciona as contas ativas do im�vel no intervalo 
							//de refer�ncia dos d�bitos associados a situa��o de cobran�a 
							//e que estejam em revis�o com o motivo de revis�o 
							//associado a situa��o da cobran�a 
							Collection conta = (Collection) getControladorFaturamento()
							.obterContasImovelIntervalo(idImovel,
									DebitoCreditoSituacao.NORMAL,
									DebitoCreditoSituacao.INCLUIDA,
									DebitoCreditoSituacao.RETIFICADA,
									iCS.getAnoMesReferenciaInicio(),
									iCS.getAnoMesReferenciaFinal(),
									iCS.getContaMotivoRevisao().getId());

							//3.1.4.2.	Caso existam contas, retira as contas selecionadas 
							//de revis�o [UC0149 � Retirar Conta de Revis�o] 
							if (conta != null && !conta.isEmpty()) {
								getControladorFaturamento().retirarRevisaoConta(conta,
										null, usuarioLogado, true, null);
							}
						}
					}
					
					// ------------ REGISTRAR TRANSA��O ----------------------------
					RegistradorOperacao registradorOperacaoRota = new RegistradorOperacao(
							Operacao.OPERACAO_IMOVEL_COBRANCA_SITUACAO_RETIRAR,
							new UsuarioAcaoUsuarioHelper(usuarioLogado,
									UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

					Operacao operacao = new Operacao();
					operacao.setId(Operacao.OPERACAO_IMOVEL_COBRANCA_SITUACAO_RETIRAR);

					OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
					operacaoEfetuada.setOperacao(operacao);

					iCS.setOperacaoEfetuada(operacaoEfetuada);
					iCS.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					registradorOperacaoRota.registrarOperacao(iCS);
					getControladorUtil().atualizar(iCS);
					
				}
			}
		}else{
			//[FS0008 � Verificar sele��o das situa��es de cobran�a para retirada].
			throw new ControladorException("atencao.necessario_selcionar_situacao_cobranca");
		}

		
//		FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();
//		filtroImovelCobrancaSituacao.adicionarParametro(new ParametroSimples(
//				FiltroImovelCobrancaSituacao.IMOVEL_ID, idImovel.toString()));
//		filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("imovel");
//
//		Collection colecaoICS = getControladorUtil().pesquisar(
//				filtroImovelCobrancaSituacao,ImovelCobrancaSituacao.class.getName());
//
//		if (colecaoICS != null && !colecaoICS.isEmpty()) {
//			ImovelCobrancaSituacao iCS = null;
//
//			for (Iterator iter = colecaoICS.iterator(); iter.hasNext();) {
//				ImovelCobrancaSituacao imovelCobrancaSituacao = (ImovelCobrancaSituacao) iter
//						.next();
//
//				if (imovelCobrancaSituacao.getDataRetiradaCobranca() == null) {
//					iCS = imovelCobrancaSituacao;
//					break;
//				}
//			}
//
//			iCS.setDataRetiradaCobranca(new Date());
//
//			Imovel imovel = this.pesquisarImovelDigitado(idImovel);
//
//			CobrancaSituacao cobrancaSituacao = imovel.getCobrancaSituacao();
//
//			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
//			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
//					FiltroCobrancaSituacao.ID, cobrancaSituacao.getId()));
//			filtroCobrancaSituacao
//					.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
//			Collection cCS = (Collection) getControladorUtil().pesquisar(
//					filtroCobrancaSituacao, CobrancaSituacao.class.getName());
//
//			CobrancaSituacao cS = (CobrancaSituacao) cCS.iterator().next();
//			Integer idContaMotivoRevisao = null;
//			try {
//				idContaMotivoRevisao = repositorioImovel
//						.pesquisarContaMotivoRevisao(idImovel);
//			} catch (ErroRepositorioException ex) {
//				throw new ControladorException("erro.sistema", ex);
//			}
//			if (idContaMotivoRevisao != null
//					&& !idContaMotivoRevisao.equals("")) {
//				if (cS.getContaMotivoRevisao() != null
//						&& iCS.getAnoMesReferenciaInicio() != null
//						&& iCS.getAnoMesReferenciaFinal() != null) {
//					Collection conta = (Collection) getControladorFaturamento()
//							.obterContasImovelIntervalo(imovel.getId(),
//									DebitoCreditoSituacao.NORMAL,
//									DebitoCreditoSituacao.INCLUIDA,
//									DebitoCreditoSituacao.RETIFICADA,
//									iCS.getAnoMesReferenciaInicio(),
//									iCS.getAnoMesReferenciaFinal(),
//									idContaMotivoRevisao);
//
//					if (conta != null && !conta.isEmpty()) {
//						getControladorFaturamento().retirarRevisaoConta(conta,
//								null, usuarioLogado, true);
//					}
//				}
//			}
//
//			imovel.setCobrancaSituacao(null);
//			// ------------ REGISTRAR TRANSA��O ROTA----------------------------
//			RegistradorOperacao registradorOperacaoRota = new RegistradorOperacao(
//					Operacao.OPERACAO_IMOVEL_COBRANCA_SITUACAO_RETIRAR,
//					new UsuarioAcaoUsuarioHelper(usuarioLogado,
//							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//			Operacao operacao = new Operacao();
//			operacao.setId(Operacao.OPERACAO_IMOVEL_COBRANCA_SITUACAO_RETIRAR);
//
//			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//			operacaoEfetuada.setOperacao(operacao);
//
//			imovel.setOperacaoEfetuada(operacaoEfetuada);
//			imovel.adicionarUsuario(usuarioLogado,
//					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//			registradorOperacaoRota.registrarOperacao(imovel);
//
//			// ------------ REGISTRAR TRANSA��O ROTA----------------------------
//			getControladorUtil().atualizar(imovel);
//
//			// ------------ REGISTRAR TRANSA��O ROTA----------------------------
//
//			iCS.setOperacaoEfetuada(operacaoEfetuada);
//			iCS.adicionarUsuario(usuarioLogado,
//					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//			registradorOperacaoRota.registrarOperacao(iCS);
//
//			// ------------ REGISTRAR TRANSA��O ROTA----------------------------
//			getControladorUtil().atualizar(iCS);
//		}

	}

	/**
	 * Pesquisa os im�veis do cliente de acordo com o tipo de rela��o
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @author Leonardo Vieira
	 * @created 12/12/2006
	 * 
	 * @param cliente
	 * @param relacaoClienteImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisClientesRelacao(Cliente cliente,
			ClienteRelacaoTipo relacaoClienteImovel,Integer numeroInicial)
			throws ControladorException {

		Collection retorno = null;

		try {
			retorno = repositorioImovel.pesquisarImoveisClientesRelacao(
					cliente, relacaoClienteImovel,numeroInicial);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;

	}

	public Integer pesquisarExistenciaImovelSubCategoria(Integer idImovel,
			Integer idCategoria) throws ControladorException {

		Integer retorno = null;

		try {

			retorno = repositorioImovel.pesquisarExistenciaImovelSubCategoria(
					idImovel, idCategoria);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;

	}

	public Collection pesquisarImoveisPorRota(Integer idRota)
			throws ControladorException {

		Collection retorno = null;

		try {
			retorno = repositorioImovel.pesquisarImoveisPorRota(idRota);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * Pesquisar imoveis por rota, situacao de ligacao de agua e situacao de ligacao
	 * de esgoto, utilizando paginacao
	 * 
	 * Utilizado no  
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @param idRota
	 * @param idsSituacaoLigacaoAgua
	 * @param idsSituacaoLigacaoEsgoto
	 * @param numeroInicial
	 * @return 
	 * @throws ControladorException
	 * 
	 * @author Francisco do Nascimento
	 * @date 21/10/2009
	 * 
	 */
	public Collection pesquisarImoveisPorRotaComPaginacao(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			int numeroInicial, int numeroMaximo) throws ControladorException {

		Collection retorno = null;

		/*
		 * Caso a rota n�o esteja com o indicador de rota alternativa ativo; a pesquisa
		 * dos im�veis ser� feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)){
			
			try {
				
				retorno = repositorioImovel.pesquisarImoveisPorRotaComPaginacaoSemRotaAlternativa(
						rota, idsSituacaoLigacaoAgua, idsSituacaoLigacaoEsgoto, 
						numeroInicial, numeroMaximo);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contr�rio; a pesquisa dos im�veis ser� feita a partir da rota alternativa 
		 * que estar� associada ao mesmo.
		 */
		else{
			
			try {
				
				retorno = repositorioImovel.pesquisarImoveisPorRotaComPaginacaoComRotaAlternativa(
						rota, idsSituacaoLigacaoAgua, idsSituacaoLigacaoEsgoto, 
						numeroInicial, numeroMaximo);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		
		return retorno;
	}

	/**
	 * Pesquisa os im�veis com determinada tarifa de consumo
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Im�veis
	 * 
	 * @author R�mulo Aurelio
	 * @created 19/12/2006
	 * 
	 * @param idLocalidadeInicial,
	 *            idLocalidadeFinal, codigoSetorComercialInicial,
	 *            codigoSetorComercialFinal, quadraInicial, quadraFinal,
	 *            loteInicial, loteFinal, subLoteInicial, subLoteFinal,
	 *            idTarifaAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarImoveisTarifaConsumo(String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String quadraInicial,
			String quadraFinal, String loteInicial, String loteFinal,
			String subLoteInicial, String subLoteFinal, String idTarifaAnterior)
			throws ControladorException {

		Collection colecaoDadosImoveis = null;

		try {
			colecaoDadosImoveis = repositorioImovel
					.pesquisarImoveisTarifaConsumo(idLocalidadeInicial,
							idLocalidadeFinal, codigoSetorComercialInicial,
							codigoSetorComercialFinal, quadraInicial,
							quadraFinal, loteInicial, loteFinal,
							subLoteInicial, subLoteFinal, idTarifaAnterior);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		// Collection colecaoDadosImoveis = retorno;

		Imovel imovel = null;

		Collection colecaoImoveis = null;

		if (colecaoDadosImoveis != null && !colecaoDadosImoveis.isEmpty()) {

			colecaoImoveis = new ArrayList();

			Iterator iteratorColecaoDadosImoveis = colecaoDadosImoveis
					.iterator();

			while (iteratorColecaoDadosImoveis.hasNext()) {

				Integer idImovel = (Integer) iteratorColecaoDadosImoveis.next();

				imovel = new Imovel();

				if (idImovel != null) {
					imovel.setId(idImovel);
				}

				colecaoImoveis.add(imovel);
			}
		} else {
			throw new ControladorException(
					"atencao.imoveis_parametros_nao_existentes", null);

		}
		return colecaoImoveis;

	}

	/**
	 * Atualiza a tarifa de consumo de um ou mais imoveis
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Im�veis
	 * 
	 * @author R�mulo Aurelio
	 * @created 19/12/2006
	 * 
	 * @param matricula,
	 *            tarifaAtual, colecaoImoveis
	 * @return
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */

	public void atualizarImoveisTarifaConsumo(String matricula,
			String tarifaAtual, Collection colecaoImoveis)
			throws ControladorException {

		if (colecaoImoveis == null || !colecaoImoveis.isEmpty()) {

			Iterator colecaoImoveisIterator = colecaoImoveis.iterator();

			while (colecaoImoveisIterator.hasNext()) {

				Imovel imovel = (Imovel) colecaoImoveisIterator.next();

				Collection colecaoCategorias = getControladorFaturamento()
						.pesquisarCategoriaPorTarifaConsumo(
								new Integer(tarifaAtual));

				if (colecaoCategorias == null || colecaoCategorias.isEmpty()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.categoria_imovel_sem_tarifa", null, imovel
									.getId().toString());
				}

				Collection colecaoCategoriasImovel = this
						.pesquisarCategoriasImovel(imovel.getId());

				Iterator colecaoCategoriasImovelIterator = colecaoCategoriasImovel
						.iterator();

				Iterator colecaoCategoriasIterator = colecaoCategorias
						.iterator();
				
				boolean existe = false;
				
				while (colecaoCategoriasImovelIterator.hasNext()) {
					
					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) colecaoCategoriasImovelIterator
					.next();
					
					while (colecaoCategoriasIterator.hasNext()) {

						Integer idCategoria = (Integer) colecaoCategoriasIterator
								.next();

						if (idCategoria.toString().equalsIgnoreCase(
								imovelSubcategoria.getComp_id()
										.getSubcategoria().getCategoria()
										.getId().toString())) {
							existe = true;
						}

					}
				}

				if (!existe) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.categoria_imovel_sem_tarifa", null, imovel
									.getId().toString());
				}
			}

			try {
				repositorioImovel.atualizarImoveisTarifaConsumo(matricula,
						tarifaAtual, colecaoImoveis);
			} catch (ErroRepositorioException ex) {
				throw new ControladorException("erro.sistema", ex);
			}

		}

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Atualiza o perfil do im�vel para o perfil normal
	 * 
	 * @date 04/01/2007, 07/05/08
	 * @author Rafael Corr�a, Francisco do Nascimento
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelPerfilNormal(Imovel imovel, RegistradorOperacao registradorOperacao )
			throws ControladorException {

		try {
			
			// para registrar a alteracao, precisamos carregar os objetos e realizar
			// as alteracoes que sao feitas no metodo RepositorioImovel.atualizarImovelPerfilNormal
			// : imovel perfil <- Normal
			// : consumo tarifa <- Consumo normal
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId(ImovelPerfil.NORMAL);
			ConsumoTarifa consumoTarifa = new ConsumoTarifa();
			consumoTarifa.setId(ConsumoTarifa.CONSUMO_NORMAL);
			imovel.setImovelPerfil(imovelPerfil);
			imovel.setConsumoTarifa(consumoTarifa);

			registradorOperacao.registrarOperacao(imovel);						
			getControladorTransacao().registrarTransacao(imovel);
			
			repositorioImovel.atualizarImovelPerfilNormal(imovel.getId());
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0490] - Informar Situa��o de Cobran�a
	 * 
	 * Pesquisa o im�vel com a situa��o da liga��o de �gua e a de esgoto
	 * 
	 * @date 13/01/2007
	 * @author Rafael Corr�a
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelComSituacaoAguaEsgoto(Integer idImovel)
			throws ControladorException {
		try {
			return this.repositorioImovel
					.pesquisarImovelComSituacaoAguaEsgoto(idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0006] - Verificar n�mero de IPTU
	 * 
	 * Verifica se j� existe outro im�vel ou economia com o mesmo n�mero de IPTU
	 * no mesmo munic�pio
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corr�a
	 * @throws ControladorException
	 */
	public Integer verificarNumeroIptu(String numeroIptu, Integer idImovel,
			Integer idImovelEconomia, Integer idMunicipio)
			throws ControladorException {
		try {
			return this.repositorioImovel.verificarNumeroIptu(numeroIptu,
					idImovel, idImovelEconomia, idMunicipio);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0007] - Verificar n�mero de contrato da companhia de energia el�trica
	 * 
	 * Verifica se j� existe outro im�vel ou economia com o mesmo n�mero de
	 * contrato da companhia el�trica
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corr�a
	 * @throws ControladorException
	 */
	public Integer verificarNumeroCompanhiaEletrica(
			Long numeroCompanhiaEletrica, Integer idImovel,
			Integer idImovelEconomia) throws ControladorException {
		try {
			return this.repositorioImovel.verificarNumeroCompanhiaEletrica(
					numeroCompanhiaEletrica, idImovel, idImovelEconomia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Obt�m o quantidade de economias da subCategoria por imovel
	 * 
	 * @param idImovel
	 *            O identificador do im�vel
	 * @return Cole��o de SubCategorias
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection<Subcategoria> obterQuantidadeEconomiasSubCategoria(
			Integer idImovel) throws ControladorException {

		// Cria��o das cole��es
		Collection<Subcategoria> colecaoSubcategoria = new ArrayList();
		Collection colecaoImovelSubCategoriaArray = null;

		try {
			colecaoImovelSubCategoriaArray = repositorioImovel
					.obterQuantidadeEconomiasSubCategoria(idImovel);

		} catch (ErroRepositorioException ex) {
			new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovelSubCategoriaArray != null
				&& !colecaoImovelSubCategoriaArray.isEmpty()) {

			Iterator colecaoImovelSubCategoriaArrayIterator = colecaoImovelSubCategoriaArray
					.iterator();

			while (colecaoImovelSubCategoriaArrayIterator.hasNext()) {

				// Obt�m o im�vel subcategoria
				Object[] imovelSubcategoriaArray = (Object[]) colecaoImovelSubCategoriaArrayIterator
						.next();

				// Cria os objetos Subcategoria
				Subcategoria subCategoria = new Subcategoria();

				// Seta a subCategoria
				subCategoria.setId((Integer) imovelSubcategoriaArray[0]);

				// Seta o codigo
				subCategoria.setCodigo((Integer) imovelSubcategoriaArray[1]);

				// Seta a descri��o
				subCategoria.setDescricao((String) imovelSubcategoriaArray[2]);

				// Seta a quantidade de economias por subCategoria
				subCategoria
						.setQuantidadeEconomias(((Short) imovelSubcategoriaArray[3])
								.intValue());

				// Seta o codigo tarifa social
				subCategoria
						.setCodigoTarifaSocial((String) imovelSubcategoriaArray[4]);

				// Seta o numero fator fiscaliza��o
				subCategoria
						.setNumeroFatorFiscalizacao((Short) imovelSubcategoriaArray[5]);

				// Seta o indicador tarifa consumo
				subCategoria
						.setIndicadorTarifaConsumo((Short) imovelSubcategoriaArray[6]);
				
				subCategoria.setIndicadorSazonalidade((Short) imovelSubcategoriaArray[10]);
				
				// Seta o a descri��o abreviada
				if(imovelSubcategoriaArray[12] != null){
				 subCategoria
						.setDescricaoAbreviada((String) imovelSubcategoriaArray[12]);
				}

				Categoria categoria = new Categoria();

				categoria.setId((Integer) imovelSubcategoriaArray[7]);
				categoria.setDescricao((String) imovelSubcategoriaArray[8]);
				categoria.setFatorEconomias((Short) imovelSubcategoriaArray[9]);
				if(imovelSubcategoriaArray[11] != null){
				  categoria.setDescricaoAbreviada((String) imovelSubcategoriaArray[11]);
				}

				subCategoria.setCategoria(categoria);

				colecaoSubcategoria.add(subCategoria);
			}

		} else {
			// Caso a cole��o n�o tenha retornado objetos
			throw new ControladorException(
					"atencao.nao_cadastrado.imovel_subcategoria", null);
		}

		return colecaoSubcategoria;
	}

	/**
	 * @date 21/02/2007
	 * @author Vivianne Sousa
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovel(Integer idImovel) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

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
			LogradouroCep logradouroCepNovo) throws ControladorException {

		try {

			this.repositorioImovel.atualizarLogradouroCep(logradouroCepAntigo,
					logradouroCepNovo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

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
	public void atualizarLogradouroBairro(
			LogradouroBairro logradouroBairroAntigo,
			LogradouroBairro logradouroBairroNovo) throws ControladorException {

		try {

			this.repositorioImovel.atualizarLogradouroBairro(
					logradouroBairroAntigo, logradouroBairroNovo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0302] Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade
	 * 
	 * Pequisa o identificador de cobranca de acr�scimo pro impontualidade para
	 * o im�vel do cliente respons�vel.
	 * 
	 * @author Pedro Alexandre
	 * @date 26/02/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public Short obterIndicadorGeracaoAcrescimosClienteImovel(Integer idImovel)
			throws ControladorException {
		try {
			return repositorioImovel
					.obterIndicadorGeracaoAcrescimosClienteImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Obter o consumo m�dio como n�o medido para o im�vel passado
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2007
	 */
	public Integer obterConsumoMedioNaoMedidoImovel(Imovel imovel)
			throws ControladorException {

		try {

			return repositorioImovel.obterConsumoMedioNaoMedidoImovel(imovel);

		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Obter a situa��o de cobran�a para o im�vel passado
	 * 
	 * @author Vivianne Sousa
	 * @date 07/03/2007
	 */
	public String obterSituacaoCobrancaImovel(Integer idImovel)
			throws ControladorException {

		try {

			return repositorioImovel.obterSituacaoCobrancaImovel(idImovel);

		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa cole��o de im�veis
	 * 
	 * @author Ana Maria
	 * @date 16/03/2007
	 */
	public Collection pesquisarColecaoImovel(
			FiltrarImovelInserirManterContaHelper filtro)
			throws ControladorException {

		try {
			return repositorioImovel.pesquisarColecaoImovel(filtro);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * 
	 * Pesquisa uma cole��o de im�veis com perfil bloqueado
	 * 
	 * @return Boolean
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarColecaoImovelPerfilBloqueado(FiltrarImovelInserirManterContaHelper filtro)
			throws ControladorException {

		try {
			return repositorioImovel.pesquisarColecaoImovelPerfilBloqueado(filtro);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa cole��o de im�veis do cliente
	 * 
	 * @author Ana Maria
	 * @date 16/03/206
	 */
	public Collection pesquisarColecaoImovelCliente(Integer codigoCliente,
			Integer relacaoTipo, Boolean verificarImovelPerfilBloqueado) throws ControladorException {

		try {

			return repositorioImovel.pesquisarColecaoImovelCliente(
					codigoCliente, relacaoTipo, verificarImovelPerfilBloqueado);

		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	/**
	 * Pesquisa quantidade de im�veis do cliente com perfil bloqueado
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 */
	public Integer pesquisarColecaoImovelClienteBloqueadoPerfil(Integer codigoCliente,
			Integer relacaoTipo)throws ControladorException {

		try {

			return repositorioImovel.pesquisarColecaoImovelClienteBloqueadoPerfil(
					codigoCliente, relacaoTipo);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Seleciona a Subcategoria principal de uma categoria
	 * 
	 * @param idCategoria
	 * @return
	 * @throws ControladorException
	 */
	public ImovelSubcategoria obterPrincipalSubcategoria(Integer idCategoria,
			Integer idImovel) throws ControladorException {

		try {
			Collection<ImovelSubcategoria> colSubCategorias = repositorioImovel
					.obterSubCategoriasPorCategoria(idCategoria, idImovel);

			ImovelSubcategoria subcategoriaPrincipal = null;

			// Selecionamos o de maior quantidade de economias
			if (colSubCategorias != null && colSubCategorias.size() > 0) {
				for (ImovelSubcategoria sub : colSubCategorias) {
					if (subcategoriaPrincipal == null
							|| subcategoriaPrincipal.getQuantidadeEconomias() < sub
									.getQuantidadeEconomias())
						subcategoriaPrincipal = sub;
				}
			}

			return subcategoriaPrincipal;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0150] - Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 16/04/2007
	 */
	public Collection obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
			Conta conta) throws ControladorException {

		return obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta
				.getId());
	}

	/**
	 * [UC0150] - Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 16/04/2007
	 */
	public Collection obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
			Integer conta) throws ControladorException {

		Collection colecaoSubcategoria = new ArrayList();
		Collection colecaoContaCategoriaArray = null;

		try {

			colecaoContaCategoriaArray = repositorioImovel
					.obterQuantidadeEconomiasCategoriaPorSubcategoria(conta);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoContaCategoriaArray != null
				&& !colecaoContaCategoriaArray.isEmpty()) {

			Iterator colecaoContaCategoriaArrayIterator = colecaoContaCategoriaArray
					.iterator();

			while (colecaoContaCategoriaArrayIterator.hasNext()) {

				Object[] contaCategoriaArray = (Object[]) colecaoContaCategoriaArrayIterator
						.next();

				Categoria categoria = new Categoria();
				Subcategoria subcategoria = new Subcategoria();

				// ID da categoria
				categoria.setId((Integer) contaCategoriaArray[8]);

				// DESCRI��O da categoria
				categoria.setDescricao((String) contaCategoriaArray[9]);

				// DESCRI��O ABREVIADA da categoria
				categoria
						.setDescricaoAbreviada((String) contaCategoriaArray[10]);
				
				//FATOR_ECONOMIAS
				categoria.setFatorEconomias((Short) contaCategoriaArray[11]);

				// CATEGORIA da subcategoria
				subcategoria.setCategoria(categoria);

				// ID da subcategoria
				subcategoria.setId((Integer) contaCategoriaArray[0]);

				// C�DIGO da subcategoria
				subcategoria.setCodigo((Integer) contaCategoriaArray[1]);

				// DESCRI��O da subcategoria
				subcategoria.setDescricao((String) contaCategoriaArray[2]);

				// C�DIGO TARIFA SOCIAL da subcategoria
				subcategoria
						.setCodigoTarifaSocial((String) contaCategoriaArray[3]);

				// QUANTIDADE ECONOMIAS da subcategoria
				if (contaCategoriaArray[4] != null) {
					subcategoria
							.setQuantidadeEconomias(((Short) contaCategoriaArray[4])
									.intValue());
				}

				// N�MERO FATOR FISCALIZA��O da subcategoria
				subcategoria
						.setNumeroFatorFiscalizacao((Short) contaCategoriaArray[5]);

				// N�MERO FATOR FISCALIZA��O da subcategoria
				subcategoria
						.setIndicadorTarifaConsumo((Short) contaCategoriaArray[6]);
				
				// DESCRI��O ABREVIADA da subcategoria
				if (contaCategoriaArray[12] != null) {
					subcategoria
							.setDescricaoAbreviada(((String) contaCategoriaArray[12])
									);
				}

				colecaoSubcategoria.add(subcategoria);
			}

		} else {
			// Caso a cole��o n�o tenha retornado objetos
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.nao_cadastrado.imovel_subcategoria", null);
		}

		return colecaoSubcategoria;
	}

	/**
	 * 
	 * Autor: Raphael Rossiter Data: 18/04/2007
	 * 
	 * @return Quantidade de subcategorias
	 * @throws ControladorException
	 */
	public Object pesquisarObterQuantidadeSubcategoria()
			throws ControladorException {
		try {
			return repositorioCategoria.pesquisarObterQuantidadeSubcategoria();
		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa todos os ids do perfil do im�vel.
	 * 
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsPerfilImovel()
			throws ControladorException {
		try {
			return repositorioImovel.pesquisarTodosIdsPerfilImovel();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa o cliente usuario do imovel
	 * 
	 * [UC 0275] Gerar resumo ligacoes economias
	 * 
	 * @author Bruno Barros
	 * @date 27/04/2007
	 * 
	 * @return Cliente
	 * @throws ControladorException
	 */
	public Cliente consultarClienteUsuarioImovel(Imovel imovel)
			throws ControladorException {
		try {
			return repositorioImovel.consultarClienteUsuarioImovel(imovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * Pesquisar Grupo do Imovel
	 * 
	 * @author Flavio Cordeiro
	 * @date 18/05/2007
	 * 
	 * @param idImovel
	 * @return
	 */
	public FaturamentoGrupo pesquisarGrupoImovel(Integer idImovel) {
		FaturamentoGrupo retorno = null;

		try {
			retorno = repositorioImovel.pesquisarGrupoImovel(idImovel);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	/**
	 * Gerar Relat�rio de Im�veis Outros Crit�rios
	 * 
	 * @author Rafael Corr�a
	 * @date 25/07/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarRelatorioImovelEnderecoOutrosCriterios(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String seqRotaInicial, String seqRotaFinal,
			String rotaInicial, String rotaFinal,
			String ordenacaoRelatorio) throws ControladorException {

		Collection colecaoImoveis = null;

		try {
			// remove primeiro as linhas do crit�rio cobran�a
			colecaoImoveis = repositorioImovel
					.gerarRelatorioImovelEnderecoOutrosCriterios(
							idImovelCondominio, idImovelPrincipal,
							idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
							consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,
							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,
							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa, idUnidadeNegocio, 
							seqRotaInicial, seqRotaFinal, rotaInicial, rotaFinal,
							ordenacaoRelatorio);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// colecao com os dados para o relatorio
		Collection colecaoGerarRelatorioImovelOutrosCriterios = null;

		if (colecaoImoveis == null || colecaoImoveis.isEmpty()) {
			throw new ControladorException(
					"atencao.filtro_consumo_tarifa_sem_records");
		}

		// para cada imovel pega as conta, debitos, creditos e guias
		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

			Iterator iteratorColecaoImoveis = colecaoImoveis.iterator();
			colecaoGerarRelatorioImovelOutrosCriterios = new ArrayList();

			ImovelRelatorioHelper imovelRelatorioHelper = null;
			// GerarRelacaoDebitosHelper gerarRelacaoDebitosHelper = null;
			while (iteratorColecaoImoveis.hasNext()) {

				Object[] contasDadosRelatorio = (Object[]) iteratorColecaoImoveis
						.next();

				imovelRelatorioHelper = new ImovelRelatorioHelper();

				// gerarRelacaoDebitosHelper = new GerarRelacaoDebitosHelper();

				// id gerencia regional
				if (contasDadosRelatorio[0] != null) { // 0
					imovelRelatorioHelper
							.setIdGerenciaRegional(((Integer) contasDadosRelatorio[0]));
				}
				// nome abreviado gerencia regional
				if (contasDadosRelatorio[1] != null) { // 1
					imovelRelatorioHelper
							.setDescricaoGerenciaRegional((String) contasDadosRelatorio[1]);
				}
				// id localidade
				if (contasDadosRelatorio[2] != null) { // 2
					imovelRelatorioHelper
							.setIdLocalidade(((Integer) contasDadosRelatorio[2]));
				}
				// descricao localidade
				if (contasDadosRelatorio[3] != null) { // 3
					imovelRelatorioHelper
							.setDescricaoLocalidade((String) contasDadosRelatorio[3]);
				}
				// id imovel
				if (contasDadosRelatorio[4] != null) { // 4
					imovelRelatorioHelper
							.setMatriculaImovel(((Integer) contasDadosRelatorio[4]));
				}
				// quantidade de economias
				if (contasDadosRelatorio[5] != null) { // 5
					imovelRelatorioHelper.setQuantidadeEconomia(new Integer(
							((Short) contasDadosRelatorio[5])));
				}
				// codigo setor comercial
				if (contasDadosRelatorio[6] != null) { // 6
					imovelRelatorioHelper
							.setCodigoSetorComercial(((Integer) contasDadosRelatorio[6]));
				}
				// descri��o setor comercial
				if (contasDadosRelatorio[7] != null) { // 7
					imovelRelatorioHelper
							.setDescricaoSetorComercial(((String) contasDadosRelatorio[7]));
				}
				// numero quadra
				if (contasDadosRelatorio[8] != null) { // 8
					imovelRelatorioHelper
							.setNumeroQuadra(((Integer) contasDadosRelatorio[8]));
				}
				// lote
				if (contasDadosRelatorio[9] != null) { // 9
					imovelRelatorioHelper
							.setNumeroLote(((Short) contasDadosRelatorio[9]));
				}
				// sub lote
				if (contasDadosRelatorio[10] != null) { // 10
					imovelRelatorioHelper
							.setNumeroSubLote(((Short) contasDadosRelatorio[10]));
				}
				// descricao ligacao agua situacao
				if (contasDadosRelatorio[11] != null) { // 11
					imovelRelatorioHelper
							.setLigacaoAguaSituacao((String) contasDadosRelatorio[11]);
				}
				// descricao ligacao esgoto situacao
				if (contasDadosRelatorio[12] != null) { // 12
					imovelRelatorioHelper
							.setLigacaoEsgotoSituacao((String) contasDadosRelatorio[12]);
				}
				// percentual
				if (contasDadosRelatorio[13] != null) { // 13
					imovelRelatorioHelper
							.setPercentual((BigDecimal) contasDadosRelatorio[13]);
				}
				// data liga��o esgoto
				if (contasDadosRelatorio[14] != null) { // 14
					imovelRelatorioHelper
							.setDataLigacaoEsgoto((Date) contasDadosRelatorio[14]);
				}
				// data liga��o �gua
				if (contasDadosRelatorio[15] != null) { // 15
					imovelRelatorioHelper
							.setDataLigacaoAgua((Date) contasDadosRelatorio[15]);
				}
				// c�digo cliente usuario
				if (contasDadosRelatorio[16] != null) { // 16
					imovelRelatorioHelper
							.setClienteUsuarioId((Integer) contasDadosRelatorio[16]);
				}
				// nome cliente usuario
				if (contasDadosRelatorio[17] != null) { // 17
					imovelRelatorioHelper
							.setClienteUsuarioNome((String) contasDadosRelatorio[17]);
				}
				// nome cliente resposanvel
				if (contasDadosRelatorio[18] != null) { // 18
					imovelRelatorioHelper
							.setClienteResponsavelId((Integer) contasDadosRelatorio[18]);
				}
				// nome cliente resposanvel
				if (contasDadosRelatorio[19] != null) { // 19
					imovelRelatorioHelper
							.setClienteResponsavelNome((String) contasDadosRelatorio[19]);
				}
				// nome logradouro
				if (contasDadosRelatorio[20] != null) { // 20
					imovelRelatorioHelper
							.setNomeLogradouro(((String) contasDadosRelatorio[20]));
				}
				// tipo logradouro
				if (contasDadosRelatorio[21] != null) { // 21
					imovelRelatorioHelper
							.setTipoLogradouro((String) contasDadosRelatorio[21]);
				}
				// titulo logradouro
				if (contasDadosRelatorio[22] != null) { // 22
					imovelRelatorioHelper
							.setTituloLogradouro((String) contasDadosRelatorio[22]);
				}
				// cep
				if (contasDadosRelatorio[23] != null) { // 23
					Cep cepImovel = new Cep();
					cepImovel.setCodigo((Integer) contasDadosRelatorio[23]);
					imovelRelatorioHelper.setCepFormatado(cepImovel
							.getCepFormatado());
				}
				// endere�o refer�ncia
				if (contasDadosRelatorio[24] != null) { // 24
					imovelRelatorioHelper
							.setEnderecoReferencia((String) contasDadosRelatorio[24]);
				}

				// comlplemente endereco
				if (contasDadosRelatorio[25] != null) { // 25
					imovelRelatorioHelper
							.setComplementoImovel((String) contasDadosRelatorio[25]);
				}

				// n�mero im�vel
				if (contasDadosRelatorio[26] != null) { // 26
					imovelRelatorioHelper
							.setNumeroImovel((String) contasDadosRelatorio[26]);
				}
				// nome bairro
				if (contasDadosRelatorio[27] != null) { // 27
					imovelRelatorioHelper
							.setNomeBairro((String) contasDadosRelatorio[27]);
				}
				// nome munic�pio
				if (contasDadosRelatorio[28] != null) { // 28
					imovelRelatorioHelper
							.setNomeMunicipio((String) contasDadosRelatorio[28]);
				}
				// sigla uf
				if (contasDadosRelatorio[29] != null) { // 29
					imovelRelatorioHelper
							.setUnidadeFederacao((String) contasDadosRelatorio[29]);
				}
				// indicador im�vel condom�nio
				if (contasDadosRelatorio[30] != null) { // 30
					imovelRelatorioHelper
							.setIndicadorImovelCondominio(((Integer) contasDadosRelatorio[30])
									.shortValue());
				}

				// n�mero moradores
				if (contasDadosRelatorio[31] != null) { // 31
					imovelRelatorioHelper
							.setNumeroMoradores(((Integer) contasDadosRelatorio[31])
									.shortValue());
				}
				// matr�cula im�vel condom�nio
				if (contasDadosRelatorio[32] != null) { // 32
					imovelRelatorioHelper
							.setMatriculaImovelCondominio((Integer) contasDadosRelatorio[32]);
				}
				// matr�cula im�vel principal
				if (contasDadosRelatorio[33] != null) { // 33
					imovelRelatorioHelper
							.setMatriculaImovelPrincipal((Integer) contasDadosRelatorio[33]);
				}
				// n�mero pontos utiliza��o
				if (contasDadosRelatorio[34] != null) { // 34
					imovelRelatorioHelper
							.setNumeroPontosUtilzacao(((Integer) contasDadosRelatorio[34])
									.shortValue());
				}
				// perfil im�vel
				if (contasDadosRelatorio[35] != null) { // 35
					imovelRelatorioHelper
							.setPerfilImovel((String) contasDadosRelatorio[35]);
				}
				// �rea constru�da maior faixa
				if (contasDadosRelatorio[36] != null) { // 36
					imovelRelatorioHelper
							.setAreaConstruidaMaiorFaixa((Integer) contasDadosRelatorio[36]);
				}
				// �rea constru�da menor faixa
				if (contasDadosRelatorio[37] != null) { // 37
					imovelRelatorioHelper
							.setAreaConstruidaMenorFaixa((Integer) contasDadosRelatorio[37]);
				}
				// �rea constru�da
				if (contasDadosRelatorio[38] != null) { // 38
					imovelRelatorioHelper
							.setAreaConstruidaImovel((BigDecimal) contasDadosRelatorio[38]);
				}
				// pavimento cal�ada
				if (contasDadosRelatorio[39] != null) { // 39
					imovelRelatorioHelper
							.setTipoPavimentoCalcada((String) contasDadosRelatorio[39]);
				}
				// pavimento rua
				if (contasDadosRelatorio[40] != null) { // 40
					imovelRelatorioHelper
							.setTipoPavimentoRua((String) contasDadosRelatorio[40]);
				}

				// despejo
				if (contasDadosRelatorio[41] != null) { // 41
					imovelRelatorioHelper
							.setTipoDespejo(((String) contasDadosRelatorio[41]));
				}
				// volume reservatorio superior menor faixa
				if (contasDadosRelatorio[42] != null) { // 42
					imovelRelatorioHelper
							.setVolumeReservatorioSuperiorMenorFaixa((BigDecimal) contasDadosRelatorio[42]);
				}
				// volume reservatorio superior maior faixa
				if (contasDadosRelatorio[43] != null) { // 43
					imovelRelatorioHelper
							.setVolumeReservatorioSuperiorMaiorFaixa((BigDecimal) contasDadosRelatorio[43]);
				}
				// volume reservatorio superior
				if (contasDadosRelatorio[44] != null) { // 44
					imovelRelatorioHelper
							.setVolumeReservatorioSuperior((BigDecimal) contasDadosRelatorio[44]);
				}
				// volume reservatorio inferior menor faixa
				if (contasDadosRelatorio[45] != null) { // 45
					imovelRelatorioHelper
							.setVolumeReservatorioInferiorMenorFaixa((BigDecimal) contasDadosRelatorio[45]);
				}
				// volume reservatorio inferior maior faixa
				if (contasDadosRelatorio[46] != null) { // 46
					imovelRelatorioHelper
							.setVolumeReservatorioInferiorMaiorFaixa((BigDecimal) contasDadosRelatorio[46]);
				}
				// volume reservatorio inferior
				if (contasDadosRelatorio[47] != null) { // 47
					imovelRelatorioHelper
							.setVolumeReservatorioInferior((BigDecimal) contasDadosRelatorio[47]);
				}
				// volume piscina menor faixa
				if (contasDadosRelatorio[48] != null) { // 48
					imovelRelatorioHelper
							.setVolumePiscinaMenorFaixa((BigDecimal) contasDadosRelatorio[48]);
				}
				// volume piscina maior faixa
				if (contasDadosRelatorio[49] != null) { // 49
					imovelRelatorioHelper
							.setVolumePiscinaMaiorFaixa((BigDecimal) contasDadosRelatorio[49]);
				}
				// volume piscina
				if (contasDadosRelatorio[50] != null) { // 50
					imovelRelatorioHelper
							.setVolumePiscina((BigDecimal) contasDadosRelatorio[50]);
				}

				// tipo de po�o
				if (contasDadosRelatorio[51] != null) { // 51
					imovelRelatorioHelper
							.setDescricaoTipoPoco(((String) contasDadosRelatorio[51]));
				}
				// di�metro da liga��o de �gua
				if (contasDadosRelatorio[52] != null) { // 52
					imovelRelatorioHelper
							.setDiametroLigacaoAgua((String) contasDadosRelatorio[52]);
				}
				// material da liga��o de �gua
				if (contasDadosRelatorio[53] != null) { // 53
					imovelRelatorioHelper
							.setMaterialLigacaoAgua((String) contasDadosRelatorio[53]);
				}
				// di�metro da liga��o de esgoto
				if (contasDadosRelatorio[54] != null) { // 54
					imovelRelatorioHelper
							.setDiametroLigacaoEsgoto((String) contasDadosRelatorio[54]);
				}
				// material da liga��o de esgoto
				if (contasDadosRelatorio[55] != null) { // 55
					imovelRelatorioHelper
							.setMaterialLigacaoEsgoto((String) contasDadosRelatorio[55]);
				}
				// consumo m�nimo esgoto
				if (contasDadosRelatorio[56] != null) { // 56
					imovelRelatorioHelper
							.setConsumoMinimoFixadoLigacaoEsgoto((Integer) contasDadosRelatorio[56]);
				}
				// percentual �gua coletada
				if (contasDadosRelatorio[57] != null) { // 57
					imovelRelatorioHelper
							.setPercentualAguaConsumidaColetada((BigDecimal) contasDadosRelatorio[57]);
				}
				// percentual esgoto
				if (contasDadosRelatorio[58] != null) { // 58
					imovelRelatorioHelper
							.setPercentual((BigDecimal) contasDadosRelatorio[58]);
				}
				// n�mero hidr�metro �gua
				if (contasDadosRelatorio[59] != null) { // 59
					imovelRelatorioHelper
							.setNumeroHidrometroAgua((String) contasDadosRelatorio[59]);
				}
				// ano fabrica��o hidr�metro �gua
				if (contasDadosRelatorio[60] != null) { // 60
					imovelRelatorioHelper
							.setAnoFabricaocaHidrometroAgua(((Integer) contasDadosRelatorio[60])
									.shortValue());
				}
				// capacidade hidr�metro �gua
				if (contasDadosRelatorio[61] != null) { // 61
					imovelRelatorioHelper
							.setCapacidadeHidrometroAgua(((String) contasDadosRelatorio[61]));
				}
				// marca hidr�metro �gua
				if (contasDadosRelatorio[62] != null) { // 62
					imovelRelatorioHelper
							.setMarcaHidrometroAgua((String) contasDadosRelatorio[62]);
				}
				// di�metro hidr�metro �gua
				if (contasDadosRelatorio[63] != null) { // 63
					imovelRelatorioHelper
							.setDiametroHidrometroAgua((String) contasDadosRelatorio[63]);
				}
				// tipo hidr�metro �gua
				if (contasDadosRelatorio[64] != null) { // 64
					imovelRelatorioHelper
							.setTipoHidrometroAgua((String) contasDadosRelatorio[64]);
				}
				// data instala��o hidr�metro �gua
				if (contasDadosRelatorio[65] != null) { // 65
					imovelRelatorioHelper
							.setDataInstalacaoHidrometroAgua((Date) contasDadosRelatorio[65]);
				}
				// local instala��o hidr�metro �gua
				if (contasDadosRelatorio[66] != null) { // 66
					imovelRelatorioHelper
							.setLocalInstalacaoHidrometroAgua((String) contasDadosRelatorio[66]);
				}
				// prote��o hidr�metro �gua
				if (contasDadosRelatorio[67] != null) { // 67
					imovelRelatorioHelper
							.setTipoProtecaoHidrometroAgua((String) contasDadosRelatorio[67]);
				}
				// indicador cavalete hidr�metro �gua
				if (contasDadosRelatorio[68] != null) { // 68
					imovelRelatorioHelper
							.setIndicadorExistenciaCavaleteAgua(((Integer) contasDadosRelatorio[68])
									.shortValue());
				}
				// n�mero hidr�metro po�o
				if (contasDadosRelatorio[69] != null) { // 69
					imovelRelatorioHelper
							.setNumeroHidrometroPoco((String) contasDadosRelatorio[69]);
				}
				// ano fabrica��o hidr�metro po�o
				if (contasDadosRelatorio[70] != null) { // 70
					imovelRelatorioHelper
							.setAnoFabricacaoHidrometroPoco(((Integer) contasDadosRelatorio[70])
									.shortValue());
				}
				// capacidade hidr�metro po�o
				if (contasDadosRelatorio[71] != null) { // 71
					imovelRelatorioHelper
							.setCapacidadeHidrometroPoco(((String) contasDadosRelatorio[71]));
				}
				// marca hidr�metro po�o
				if (contasDadosRelatorio[72] != null) { // 72
					imovelRelatorioHelper
							.setMarcaHidrometroPoco((String) contasDadosRelatorio[72]);
				}
				// di�metro hidr�metro po�o
				if (contasDadosRelatorio[73] != null) { // 73
					imovelRelatorioHelper
							.setDiametroHidrometroPoco((String) contasDadosRelatorio[73]);
				}
				// tipo hidr�metro po�o
				if (contasDadosRelatorio[74] != null) { // 74
					imovelRelatorioHelper
							.setTipoHidrometroPoco((String) contasDadosRelatorio[74]);
				}
				// data instala��o hidr�metro po�o
				if (contasDadosRelatorio[75] != null) { // 75
					imovelRelatorioHelper
							.setDataInstalacaoHidrometroPoco((Date) contasDadosRelatorio[75]);
				}
				// local instala��o hidr�metro po�o
				if (contasDadosRelatorio[76] != null) { // 76
					imovelRelatorioHelper
							.setLocalInstalacaoHidrometroPoco((String) contasDadosRelatorio[76]);
				}
				// prote��o hidr�metro po�o
				if (contasDadosRelatorio[77] != null) { // 77
					imovelRelatorioHelper
							.setTipoProtecaoHidrometroPoco((String) contasDadosRelatorio[77]);
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[78] != null) { // 78
					imovelRelatorioHelper
							.setIndicadorExistenciaCavaletePoco(((Integer) contasDadosRelatorio[78])
									.shortValue());
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[79] != null) { // 79
					imovelRelatorioHelper
							.setConsumoMinimoFixadoAgua((Integer) contasDadosRelatorio[79]);
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[80] != null) { // 80
					imovelRelatorioHelper
							.setConsumoMinimoFixadoLigacaoEsgoto((Integer) contasDadosRelatorio[80]);
				}
				// indicador Jardim
				if (contasDadosRelatorio[81] != null) { // 81
					imovelRelatorioHelper
							.setJardim(((Short) contasDadosRelatorio[81])
									.toString());
				}

				// Rota
				if (contasDadosRelatorio[82] != null) { // 82
					imovelRelatorioHelper
							.setCodigoRota((Short) contasDadosRelatorio[82]);
				}

				// N�mero Sequencial Rota
				if (contasDadosRelatorio[83] != null) { // 83
					imovelRelatorioHelper
							.setNumeroSequencialRota((Integer) contasDadosRelatorio[83]);
				}

				// consumo Medio
				Integer consumoMedio = getControladorCobranca()
						.pesquisarConsumoMedioConsumoHistoricoImovel(
								new Integer(imovelRelatorioHelper
										.getMatriculaImovel()));
				if (consumoMedio != null) {
					imovelRelatorioHelper.setConsumoMedioImovel(consumoMedio);
				}

				Imovel imovel = new Imovel();

				Localidade localidadeImovel = new Localidade();
				localidadeImovel.setId(imovelRelatorioHelper.getIdLocalidade());
				SetorComercial setorComercialImovel = new SetorComercial();
				setorComercialImovel.setCodigo(imovelRelatorioHelper
						.getCodigoSetorComercial().intValue());
				Quadra quadraImovel = new Quadra();
				quadraImovel.setNumeroQuadra(imovelRelatorioHelper
						.getNumeroQuadra().intValue());

				imovel.setLocalidade(localidadeImovel);
				imovel.setSetorComercial(setorComercialImovel);
				imovel.setQuadra(quadraImovel);
				imovel.setLote(new Short(imovelRelatorioHelper.getNumeroLote())
						.shortValue());
				imovel.setSubLote(new Short(imovelRelatorioHelper
						.getNumeroSubLote()).shortValue());

				// inscricao formatada do imovel
				imovelRelatorioHelper.setInscricaoImovel(imovel
						.getInscricaoFormatada());

				// obter endereco
				imovelRelatorioHelper.setEndereco(getControladorEndereco()
						.pesquisarEndereco(
								new Integer(imovelRelatorioHelper
										.getMatriculaImovel())));

				Collection colecaoSubcategoria = this
						.pesquisarSubcategoriasImovelRelatorio(imovelRelatorioHelper
								.getMatriculaImovel());

				// ------subcategoria
				String[] arraySubcatgegoriasQtdEconomias = new String[colecaoSubcategoria
						.size()];
				if (!colecaoSubcategoria.isEmpty()) {

					Iterator iterator = colecaoSubcategoria.iterator();

					int i = 0;

					while (iterator.hasNext()) {

						Object[] arraySubcategoria = (Object[]) iterator.next();

						arraySubcatgegoriasQtdEconomias[i] = arraySubcategoria[0]
								.toString()
								+ "/" + arraySubcategoria[1].toString();

						i++;

					}
					imovelRelatorioHelper
							.setSubcategoriaQtdEconomia(arraySubcatgegoriasQtdEconomias);
				}

				// [UC0307 Obter Indicador de Exist�ncia de Hidr�metro no
				// Im�vel]
				imovelRelatorioHelper
						.setIndicadorExistenciaHidrometro(obterIndicadorExistenciaHidrometroImovel(imovelRelatorioHelper
								.getMatriculaImovel()));

				// [UC123 Obter Descri��es da Categoria do Im�vel]
				imovelRelatorioHelper
						.setDescricaoAbreviadaCategoria(obterDescricoesCategoriaImovel(
								new Imovel(imovelRelatorioHelper
										.getMatriculaImovel()))
								.getDescricaoAbreviada());

				colecaoGerarRelatorioImovelOutrosCriterios
						.add(imovelRelatorioHelper);
			}
			
			// Organizar a cole��o
//			Collections.sort((List) colecaoGerarRelatorioImovelOutrosCriterios, new Comparator() {
//				public int compare(Object a, Object b) {
//					
//					String chave1 = ""; 
//						if (((ImovelRelatorioHelper) a)
//								.getIdLocalidade() != null) {
//							chave1 = chave1 + Util
//							.adicionarZerosEsquedaNumero(
//									3,
//									((ImovelRelatorioHelper) a)
//											.getIdLocalidade().toString());
//						}
//						
//						if (((ImovelRelatorioHelper) a).getTipoLogradouro() != null) {
//							chave1 = chave1 + ((ImovelRelatorioHelper) a).getTipoLogradouro();
//						}
//						
//						if (((ImovelRelatorioHelper) a).getTituloLogradouro() != null) {
//							chave1 = chave1 + ((ImovelRelatorioHelper) a).getTituloLogradouro();
//						}
//						
//						if (((ImovelRelatorioHelper) a).getNomeLogradouro() != null) {
//							chave1 = chave1 + ((ImovelRelatorioHelper) a).getNomeLogradouro();
//						}
//						
//						if (((ImovelRelatorioHelper) a)
//								.getNumeroImovel() != null) {
//							chave1 = chave1 + Util
//							.adicionarZerosEsquedaNumero(
//									5,
//									((ImovelRelatorioHelper) a)
//											.getNumeroImovel().trim());
//						}
//						
//						String chave2 = ""; 
//						if (((ImovelRelatorioHelper) b)
//								.getIdLocalidade() != null) {
//							chave2 = chave2 + Util
//							.adicionarZerosEsquedaNumero(
//									3,
//									((ImovelRelatorioHelper) b)
//											.getIdLocalidade().toString());
//						}
//						
//						if (((ImovelRelatorioHelper) b).getTipoLogradouro() != null) {
//							chave2 = chave2 + ((ImovelRelatorioHelper) b).getTipoLogradouro();
//						}
//						
//						if (((ImovelRelatorioHelper) b).getTituloLogradouro() != null) {
//							chave2 = chave2 + ((ImovelRelatorioHelper) b).getTituloLogradouro();
//						}
//						
//						if (((ImovelRelatorioHelper) b).getNomeLogradouro() != null) {
//							chave2 = chave2 + ((ImovelRelatorioHelper) b).getNomeLogradouro();
//						}
//						
//						if (((ImovelRelatorioHelper) b)
//								.getNumeroImovel() != null) {
//							chave2 = chave2 + Util
//							.adicionarZerosEsquedaNumero(
//									5,
//									((ImovelRelatorioHelper) b)
//											.getNumeroImovel().trim());
//						}
//
//					return chave1.compareTo(chave2);
//
//				}
//			});
			
		}

		return colecaoGerarRelatorioImovelOutrosCriterios;
	}

	/**
	 * [UC0591] - Gerar Relat�rio de Clientes Especiais
	 * 
	 * Pesquisas os im�veis de acordo com os par�metros da pesquisa
	 * 
	 * @author Rafael Corr�a
	 * @date 31/05/2007
	 */
	public Collection pesquisarImovelClientesEspeciaisRelatorio(
			String idUnidadeNegocio, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorInicial, String codigoSetorFinal,
			String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria,
			String[] idsSubcategoria, String idSituacaoAgua,
			String idSituacaoEsgoto, String qtdeEconomiasInicial,
			String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,
			String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial,
			String intervaloConsumoEsgotoFinal, String idClienteResponsavel,
			String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal,
			Date dataInstalacaoHidrometroInicial,
			Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,
			Integer anoMesFaturamento, String idLeituraAnormalidade,
			String leituraAnormalidade, String idConsumoAnormalidade,
			String consumoAnormalidade) throws ControladorException {

		Collection colecaoImovelClientesEspeciaisHelper = null;
		Collection colecaoDadosImoveis = null;
		try {
			colecaoDadosImoveis = repositorioImovel
					.pesquisarImovelClientesEspeciaisRelatorio(
							idUnidadeNegocio, idGerenciaRegional,
							idLocalidadeInicial, idLocalidadeFinal,
							codigoSetorInicial, codigoSetorFinal,
							codigoRotaInicial, codigoRotaFinal,
							idsPerfilImovel, idsCategoria, idsSubcategoria,
							idSituacaoAgua, idSituacaoEsgoto,
							qtdeEconomiasInicial, qtdeEconomiasFinal,
							intervaloConsumoAguaInicial,
							intervaloConsumoAguaFinal,
							intervaloConsumoEsgotoInicial,
							intervaloConsumoEsgotoFinal, idClienteResponsavel,
							intervaloConsumoResponsavelInicial,
							intervaloConsumoResponsavelFinal,
							dataInstalacaoHidrometroInicial,
							dataInstalacaoHidrometroFinal,
							idsCapacidadesHidrometro, idsTarifasConsumo,
							anoMesFaturamento, idLeituraAnormalidade,
							leituraAnormalidade, idConsumoAnormalidade,
							consumoAnormalidade);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDadosImoveis != null && !colecaoDadosImoveis.isEmpty()) {

			colecaoImovelClientesEspeciaisHelper = new ArrayList();

			Iterator colecaoDadosImoveisIterator = colecaoDadosImoveis
					.iterator();

			while (colecaoDadosImoveisIterator.hasNext()) {

				Object[] dadosImovel = (Object[]) colecaoDadosImoveisIterator
						.next();

				ImovelClientesEspeciaisRelatorioHelper imovelClientesEspeciaisRelatorioHelper = new ImovelClientesEspeciaisRelatorioHelper();

				Imovel imovel = new Imovel();
				Conta conta = null;

				// Ger�ncia Regional
				if (dadosImovel[0] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setIdGerenciaRegional((Integer) dadosImovel[0]);
					imovelClientesEspeciaisRelatorioHelper
							.setNomeGerenciaRegional((String) dadosImovel[1]);
				}

				// Localidade
				if (dadosImovel[2] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setIdLocalidade((Integer) dadosImovel[2]);
					imovelClientesEspeciaisRelatorioHelper
							.setNomeLocalidade((String) dadosImovel[3]);

					Localidade localidade = new Localidade();
					localidade.setId((Integer) dadosImovel[2]);
					imovel.setLocalidade(localidade);
				}

				// Setor Comercial
				if (dadosImovel[4] != null) {
					SetorComercial setorComercial = new SetorComercial();
					setorComercial.setCodigo((Integer) dadosImovel[4]);
					imovel.setSetorComercial(setorComercial);
					
					imovelClientesEspeciaisRelatorioHelper.
						setCodigoSetor((Integer)dadosImovel[4]);
				}

				// Quadra
				if (dadosImovel[5] != null) {
					Quadra quadra = new Quadra();
					quadra.setNumeroQuadra((Integer) dadosImovel[5]);
					imovel.setQuadra(quadra);
				}

				// Lote
				if (dadosImovel[6] != null) {
					imovel.setLote((Short) dadosImovel[6]);
				}

				// SubLote
				if (dadosImovel[7] != null) {
					imovel.setSubLote((Short) dadosImovel[7]);
				}

				// Inscri��o
				imovelClientesEspeciaisRelatorioHelper
						.setInscricaoImovel(imovel.getInscricaoFormatada());

				// Id do Im�vel
				if (dadosImovel[8] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setIdImovel((Integer) dadosImovel[8]);
				}

				// Categoria
				Categoria categoria = this
						.obterPrincipalCategoriaImovel(imovelClientesEspeciaisRelatorioHelper
								.getIdImovel());

				if (categoria != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setIdCategoria(categoria.getId());
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoCategoria(categoria.getDescricao());
				}

				// Subcategoria
				ImovelSubcategoria imovelSubcategoria = this
						.obterPrincipalSubcategoria(categoria.getId(),
								imovelClientesEspeciaisRelatorioHelper
										.getIdImovel());

				if (imovelSubcategoria != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setIdSubcategoria(imovelSubcategoria.getComp_id()
									.getSubcategoria().getId());

					try {

						String descricaoSubcategoria = repositorioImovel
								.obterDescricaoSubcategoria(imovelClientesEspeciaisRelatorioHelper
										.getIdSubcategoria());

						imovelClientesEspeciaisRelatorioHelper
								.setDescricaoSubcategoria(descricaoSubcategoria);

					} catch (ErroRepositorioException ex) {
						throw new ControladorException("erro.sistema", ex);
					}

				}

				// Cliente Usu�rio
				if (dadosImovel[9] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setIdClienteUsuario((Integer) dadosImovel[9]);
					imovelClientesEspeciaisRelatorioHelper
							.setNomeClienteUsuario((String) dadosImovel[10]);
				}

				// Quantidade de Economias
				if (dadosImovel[11] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setQtdeEconomias((Short) dadosImovel[11]);
				}

				// Descri��o da Situa��o da Liga��o de �gua
				if (dadosImovel[12] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoSituacaoLigacaoAgua((String) dadosImovel[12]);
				} else {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoSituacaoLigacaoAgua("");
				}

				// Descri��o da Situa��o da Liga��o de Esgoto
				if (dadosImovel[13] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoSituacaoLigacaoEsgoto((String) dadosImovel[13]);
				} else {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoSituacaoLigacaoEsgoto("");
				}

				// D�bitos Vencidos e Faturas em Aberto
				Calendar dataInicioVencimentoDebito = new GregorianCalendar();
				dataInicioVencimentoDebito.set(Calendar.YEAR, new Integer(
						"0001").intValue());
				dataInicioVencimentoDebito.set(Calendar.MONTH, 0);
				dataInicioVencimentoDebito.set(Calendar.DATE, new Integer("01")
						.intValue());

				// data final de vencimento de debito
				Calendar dataFimVencimentoDebito = new GregorianCalendar();
				dataFimVencimentoDebito.add(Calendar.DATE, -15);

				ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = getControladorCobranca()
						.obterDebitoImovelOuCliente(
								1,
								imovelClientesEspeciaisRelatorioHelper
										.getIdImovel().toString(), null, null,
								"000101", "999912",
								dataInicioVencimentoDebito.getTime(),
								dataFimVencimentoDebito.getTime(), 1, 1, 2, 2,
								1, 1, 1, null);

				Integer qtdeDebitos = new Integer("0");
				BigDecimal valorDebitos = new BigDecimal("0.00");

				// Contas
				if (obterDebitoImovelOuClienteHelper
						.getColecaoContasValoresImovel() != null
						&& !obterDebitoImovelOuClienteHelper
								.getColecaoContasValoresImovel().isEmpty()) {
					qtdeDebitos = qtdeDebitos
							+ obterDebitoImovelOuClienteHelper
									.getColecaoContasValoresImovel().size();

					Iterator colecaoContaValoresIterator = obterDebitoImovelOuClienteHelper
							.getColecaoContasValoresImovel().iterator();

					while (colecaoContaValoresIterator.hasNext()) {

						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) colecaoContaValoresIterator
								.next();

						valorDebitos = valorDebitos.add(contaValoresHelper
								.getValorTotalConta());

					}
				}

				// Guias de Pagamento
				if (obterDebitoImovelOuClienteHelper
						.getColecaoGuiasPagamentoValores() != null
						&& !obterDebitoImovelOuClienteHelper
								.getColecaoGuiasPagamentoValores().isEmpty()) {
					qtdeDebitos = qtdeDebitos
							+ obterDebitoImovelOuClienteHelper
									.getColecaoGuiasPagamentoValores().size();

					Iterator colecaoGuiasPagamentoValoresIterator = obterDebitoImovelOuClienteHelper
							.getColecaoGuiasPagamentoValores().iterator();

					while (colecaoGuiasPagamentoValoresIterator.hasNext()) {

						GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiasPagamentoValoresIterator
								.next();

						valorDebitos = valorDebitos
								.add(guiaPagamentoValoresHelper
										.getGuiaPagamento().getValorDebito());

					}
				}

				imovelClientesEspeciaisRelatorioHelper
						.setQtdeDebitosVencidos(qtdeDebitos);
				imovelClientesEspeciaisRelatorioHelper
						.setValorDebitosVencidos(valorDebitos);

				// Descri��o da Capacidade do Hidr�metro
				if (dadosImovel[14] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoCapacidadeHidrometro((String) dadosImovel[14]);
				} else {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoCapacidadeHidrometro("");
				}

				// Data de Instala��o do Hidr�metro
				if (dadosImovel[15] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setDataInstalacaoHidrometro((Date) dadosImovel[15]);
				}

				// Cliente Respons�vel
				if (dadosImovel[16] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setIdClienteResponsavel((Integer) dadosImovel[16]);
					imovelClientesEspeciaisRelatorioHelper
							.setNomeClienteResponsavel((String) dadosImovel[17]);
				}

				// Consumo �gua
				if (dadosImovel[18] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setConsumoAgua((Integer) dadosImovel[18]);
				}

				// Consumo Esgoto
				if (dadosImovel[19] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setConsumoEsgoto((Integer) dadosImovel[19]);
				}

				// Consumo M�nimo
				if (dadosImovel[20] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setConsumoMinimoEsgoto((Integer) dadosImovel[20]);
				}

				// Descri��o da Tarifa de Consumo
				if (dadosImovel[21] != null) {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoTarifaConsumo((String) dadosImovel[21]);
				} else {
					imovelClientesEspeciaisRelatorioHelper
							.setDescricaoTarifaConsumo("");
				}

				// Valor �gua
				if (dadosImovel[22] != null) {
					conta = new Conta();
					conta.setValorAgua((BigDecimal) dadosImovel[22]);
					imovelClientesEspeciaisRelatorioHelper
							.setValorAgua((BigDecimal) dadosImovel[22]);
				}

				// Valor Esgoto
				if (dadosImovel[23] != null) {
					conta.setValorEsgoto((BigDecimal) dadosImovel[23]);
					imovelClientesEspeciaisRelatorioHelper
							.setValorEsgoto((BigDecimal) dadosImovel[23]);
				}

				// Valor D�bitos
				if (dadosImovel[24] != null) {
					conta.setDebitos((BigDecimal) dadosImovel[24]);
				}

				// Valor Cr�ditos
				if (dadosImovel[25] != null) {
					conta.setValorCreditos((BigDecimal) dadosImovel[25]);
				}
				
				// Indicador Cobranca Multa
				if(dadosImovel[28] != null){
					conta.setIndicadorCobrancaMulta((Short) dadosImovel[28]);
					imovelClientesEspeciaisRelatorioHelper.setIndicadorCobrarMulta((Short) dadosImovel[28]);
				}

				// Valor da Conta
				if (conta != null) {
					imovelClientesEspeciaisRelatorioHelper.setValorConta(conta
							.getValorTotal());
				}

				colecaoImovelClientesEspeciaisHelper
						.add(imovelClientesEspeciaisRelatorioHelper);
				
				if (dadosImovel[26] != null) {
					imovelClientesEspeciaisRelatorioHelper.
						setDescricaoSetor((String)dadosImovel[26]);
				}

				if (dadosImovel[27] != null) {
					imovelClientesEspeciaisRelatorioHelper.
						setCodigoRota((Integer)dadosImovel[27]);
				}

			}
		}

		return colecaoImovelClientesEspeciaisHelper;
	}

	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situa��o da liga��o de esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoEsgotoSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgotoSituacao pesquisarLigacaoEsgotoSituacao(Integer idImovel)
			throws ControladorException {

		try {

			return repositorioImovel.pesquisarLigacaoEsgotoSituacao(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situa��o da liga��o de agua
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoAguaSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoAguaSituacao pesquisarLigacaoAguaSituacao(Integer idImovel)
			throws ControladorException {

		try {

			return repositorioImovel.pesquisarLigacaoAguaSituacao(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Gerar Relat�rio de Im�veis Outros Crit�rios
	 * 
	 * @author Rafael Corr�a
	 * @date 25/07/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarRelatorioCadastroConsumidoresInscricao(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal, String ordenacao, String[] pocoTipoListIds ) throws ControladorException {

		Collection colecaoImoveis = null;

		try {
			// remove primeiro as linhas do crit�rio cobran�a
			colecaoImoveis = repositorioImovel
					.gerarRelatorioCadastroConsumidoreInscricao(
							idImovelCondominio, idImovelPrincipal,
							idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
							consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,

							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,

							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa, idUnidadeNegocio, cdRotaInicial,
							cdRotaFinal, sequencialRotaInicial,
							sequencialRotaFinal, ordenacao, pocoTipoListIds);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// colecao com os dados para o relatorio
		Collection colecaoGerarRelatorioImovelOutrosCriterios = null;

//		if (colecaoImoveis == null || colecaoImoveis.isEmpty()) {
//			throw new ControladorException(
//					"atencao.filtro_consumo_tarifa_sem_records");
//		}

		// para cada imovel pega as conta, debitos, creditos e guias
		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

			Iterator iteratorColecaoImoveis = colecaoImoveis.iterator();
			colecaoGerarRelatorioImovelOutrosCriterios = new ArrayList();

			ImovelRelatorioHelper imovelRelatorioHelper = null;
			// GerarRelacaoDebitosHelper gerarRelacaoDebitosHelper = null;
			while (iteratorColecaoImoveis.hasNext()) {

				Object[] contasDadosRelatorio = (Object[]) iteratorColecaoImoveis
						.next();

				imovelRelatorioHelper = new ImovelRelatorioHelper();

				// gerarRelacaoDebitosHelper = new GerarRelacaoDebitosHelper();

				// id gerencia regional
				if (contasDadosRelatorio[0] != null) { // 0
					imovelRelatorioHelper
							.setIdGerenciaRegional(((Integer) contasDadosRelatorio[0]));
				}
				// nome abreviado gerencia regional
				if (contasDadosRelatorio[1] != null) { // 1
					imovelRelatorioHelper
							.setDescricaoGerenciaRegional((String) contasDadosRelatorio[1]);
				}
				// id localidade
				if (contasDadosRelatorio[2] != null) { // 2
					imovelRelatorioHelper
							.setIdLocalidade(((Integer) contasDadosRelatorio[2]));
				}
				// descricao localidade
				if (contasDadosRelatorio[3] != null) { // 3
					imovelRelatorioHelper
							.setDescricaoLocalidade((String) contasDadosRelatorio[3]);
				}
				// id imovel
				if (contasDadosRelatorio[4] != null) { // 4
					imovelRelatorioHelper
							.setMatriculaImovel(((Integer) contasDadosRelatorio[4]));
				}
				// quantidade de economias
				if (contasDadosRelatorio[5] != null) { // 5
					imovelRelatorioHelper.setQuantidadeEconomia(new Integer(
							((Short) contasDadosRelatorio[5])));
				}
				// codigo setor comercial
				if (contasDadosRelatorio[6] != null) { // 6
					imovelRelatorioHelper
							.setCodigoSetorComercial(((Integer) contasDadosRelatorio[6]));
				}
				// descri��o setor comercial
				if (contasDadosRelatorio[7] != null) { // 7
					imovelRelatorioHelper
							.setDescricaoSetorComercial(((String) contasDadosRelatorio[7]));
				}
				// numero quadra
				if (contasDadosRelatorio[8] != null) { // 8
					imovelRelatorioHelper
							.setNumeroQuadra(((Integer) contasDadosRelatorio[8]));
				}
				// lote
				if (contasDadosRelatorio[9] != null) { // 9
					imovelRelatorioHelper
							.setNumeroLote(((Short) contasDadosRelatorio[9]));
				}
				// sub lote
				if (contasDadosRelatorio[10] != null) { // 10
					imovelRelatorioHelper
							.setNumeroSubLote(((Short) contasDadosRelatorio[10]));
				}
				// descricao ligacao agua situacao
				if (contasDadosRelatorio[11] != null) { // 11
					imovelRelatorioHelper
							.setLigacaoAguaSituacao((Integer) contasDadosRelatorio[11]
									+ "");
				}
				// descricao ligacao esgoto situacao
				if (contasDadosRelatorio[12] != null) { // 12
					imovelRelatorioHelper
							.setLigacaoEsgotoSituacao((Integer) contasDadosRelatorio[12]
									+ "");
				}
				// percentual
				if (contasDadosRelatorio[13] != null) { // 13
					imovelRelatorioHelper
							.setPercentual((BigDecimal) contasDadosRelatorio[13]);
				}
				// data liga��o esgoto
				if (contasDadosRelatorio[14] != null) { // 14
					imovelRelatorioHelper
							.setDataLigacaoEsgoto((Date) contasDadosRelatorio[14]);
				}
				// data liga��o �gua
				if (contasDadosRelatorio[15] != null) { // 15
					imovelRelatorioHelper
							.setDataLigacaoAgua((Date) contasDadosRelatorio[15]);
				}
				// c�digo cliente usuario
				if (contasDadosRelatorio[16] != null) { // 16
					imovelRelatorioHelper
							.setClienteUsuarioId((Integer) contasDadosRelatorio[16]);
				}
				// nome cliente usuario
				if (contasDadosRelatorio[17] != null) { // 17
					imovelRelatorioHelper
							.setClienteUsuarioNome((String) contasDadosRelatorio[17]);
				}
				// nome cliente resposanvel
				if (contasDadosRelatorio[18] != null) { // 18
					imovelRelatorioHelper
							.setClienteResponsavelId((Integer) contasDadosRelatorio[18]);
				}
				// nome cliente resposanvel
				if (contasDadosRelatorio[19] != null) { // 19
					imovelRelatorioHelper
							.setClienteResponsavelNome((String) contasDadosRelatorio[19]);
				}
				// nome logradouro
				if (contasDadosRelatorio[20] != null) { // 20
					imovelRelatorioHelper
							.setNomeLogradouro(((String) contasDadosRelatorio[20]));
				}
				// tipo logradouro
				if (contasDadosRelatorio[21] != null) { // 21
					imovelRelatorioHelper
							.setTipoLogradouro((String) contasDadosRelatorio[21]);
				}
				// titulo logradouro
				if (contasDadosRelatorio[22] != null) { // 22
					imovelRelatorioHelper
							.setTituloLogradouro((String) contasDadosRelatorio[22]);
				}
				// cep
				if (contasDadosRelatorio[23] != null) { // 23
					Cep cepImovel = new Cep();
					cepImovel.setCodigo((Integer) contasDadosRelatorio[23]);
					imovelRelatorioHelper.setCepFormatado(cepImovel
							.getCepFormatado());
				}
				// endere�o refer�ncia
				if (contasDadosRelatorio[24] != null) { // 24
					imovelRelatorioHelper
							.setComplementoImovel((String) contasDadosRelatorio[24]);
				}

				// comlplemente endereco
				if (contasDadosRelatorio[25] != null) { // 25
					imovelRelatorioHelper
							.setComplementoImovel((String) contasDadosRelatorio[25]);
				}

				// n�mero im�vel
				if (contasDadosRelatorio[26] != null) { // 26
					imovelRelatorioHelper
							.setNumeroImovel((String) contasDadosRelatorio[26]);
				}
				// nome bairro
				if (contasDadosRelatorio[27] != null) { // 27
					imovelRelatorioHelper
							.setNomeBairro((String) contasDadosRelatorio[27]);
				}
				// nome munic�pio
				if (contasDadosRelatorio[28] != null) { // 28
					imovelRelatorioHelper
							.setNomeMunicipio((String) contasDadosRelatorio[28]);
				}
				// sigla uf
				if (contasDadosRelatorio[29] != null) { // 29
					imovelRelatorioHelper
							.setUnidadeFederacao((String) contasDadosRelatorio[29]);
				}
				// indicador im�vel condom�nio
				if (contasDadosRelatorio[30] != null) { // 30
					imovelRelatorioHelper
							.setIndicadorImovelCondominio(((Integer) contasDadosRelatorio[30])
									.shortValue());
				}

				// n�mero moradores
				if (contasDadosRelatorio[31] != null) { // 31
					imovelRelatorioHelper
							.setNumeroMoradores(((Integer) contasDadosRelatorio[31])
									.shortValue());
				}
				// matr�cula im�vel condom�nio
				if (contasDadosRelatorio[32] != null) { // 32
					imovelRelatorioHelper
							.setMatriculaImovelCondominio((Integer) contasDadosRelatorio[32]);
				}
				// matr�cula im�vel principal
				if (contasDadosRelatorio[33] != null) { // 33
					imovelRelatorioHelper
							.setMatriculaImovelPrincipal((Integer) contasDadosRelatorio[33]);
				}
				// n�mero pontos utiliza��o
				if (contasDadosRelatorio[34] != null) { // 34
					imovelRelatorioHelper
							.setNumeroPontosUtilzacao(((Integer) contasDadosRelatorio[34])
									.shortValue());
				}
				// perfil im�vel
				if (contasDadosRelatorio[35] != null) { // 35
					imovelRelatorioHelper
							.setPerfilImovel((String) contasDadosRelatorio[35]);
				}
				// �rea constru�da maior faixa
				if (contasDadosRelatorio[36] != null) { // 36
					imovelRelatorioHelper
							.setAreaConstruidaMaiorFaixa((Integer) contasDadosRelatorio[36]);
				}
				// �rea constru�da menor faixa
				if (contasDadosRelatorio[37] != null) { // 37
					imovelRelatorioHelper
							.setAreaConstruidaMenorFaixa((Integer) contasDadosRelatorio[37]);
				}
				// �rea constru�da
				if (contasDadosRelatorio[38] != null) { // 38
					imovelRelatorioHelper
							.setAreaConstruidaImovel((BigDecimal) contasDadosRelatorio[38]);
				}
				// pavimento cal�ada
				if (contasDadosRelatorio[39] != null) { // 39
					imovelRelatorioHelper
							.setTipoPavimentoCalcada((Integer) contasDadosRelatorio[39]
									+ "");
				}
				// pavimento rua
				if (contasDadosRelatorio[40] != null) { // 40
					imovelRelatorioHelper
							.setTipoPavimentoRua((Integer) contasDadosRelatorio[40]
									+ "");
				}

				// despejo
				if (contasDadosRelatorio[41] != null) { // 41
					imovelRelatorioHelper
							.setTipoDespejo((Integer) contasDadosRelatorio[41]
									+ "");
				}
				// volume reservatorio superior menor faixa
				if (contasDadosRelatorio[42] != null) { // 42
					imovelRelatorioHelper
							.setVolumeReservatorioSuperiorMenorFaixa((BigDecimal) contasDadosRelatorio[42]);
				}
				// volume reservatorio superior maior faixa
				if (contasDadosRelatorio[43] != null) { // 43
					imovelRelatorioHelper
							.setVolumeReservatorioSuperiorMaiorFaixa((BigDecimal) contasDadosRelatorio[43]);
				}
				// volume reservatorio superior
				if (contasDadosRelatorio[44] != null) { // 44
					imovelRelatorioHelper
							.setVolumeReservatorioSuperior(new BigDecimal(
									(Integer) contasDadosRelatorio[44]));
				}
				// volume reservatorio inferior menor faixa
				if (contasDadosRelatorio[45] != null) { // 45
					imovelRelatorioHelper
							.setVolumeReservatorioInferiorMenorFaixa((BigDecimal) contasDadosRelatorio[45]);
				}
				// volume reservatorio inferior maior faixa
				if (contasDadosRelatorio[46] != null) { // 46
					imovelRelatorioHelper
							.setVolumeReservatorioInferiorMaiorFaixa((BigDecimal) contasDadosRelatorio[46]);
				}
				// volume reservatorio inferior
				if (contasDadosRelatorio[47] != null) { // 47
					imovelRelatorioHelper
							.setVolumeReservatorioInferior(new BigDecimal(
									(Integer) contasDadosRelatorio[47]));
				}
				// volume piscina menor faixa
				if (contasDadosRelatorio[48] != null) { // 48
					imovelRelatorioHelper
							.setVolumePiscinaMenorFaixa((BigDecimal) contasDadosRelatorio[48]);
				}
				// volume piscina maior faixa
				if (contasDadosRelatorio[49] != null) { // 49
					imovelRelatorioHelper
							.setVolumePiscinaMaiorFaixa((BigDecimal) contasDadosRelatorio[49]);
				}
				// volume piscina
				if (contasDadosRelatorio[50] != null) { // 50
					imovelRelatorioHelper.setVolumePiscina(new BigDecimal(
							(Integer) contasDadosRelatorio[50]));
				}

				// tipo de po�o
				if (contasDadosRelatorio[51] != null) { // 51
					imovelRelatorioHelper
							.setDescricaoTipoPoco(((String) contasDadosRelatorio[51]));
				}
				// di�metro da liga��o de �gua
				if (contasDadosRelatorio[52] != null) { // 52
					imovelRelatorioHelper
							.setDiametroLigacaoAgua(contasDadosRelatorio[52]
									+ "");
				}
				// material da liga��o de �gua
				if (contasDadosRelatorio[53] != null) { // 53
					imovelRelatorioHelper
							.setMaterialLigacaoAgua((String) contasDadosRelatorio[53]);
				}
				// di�metro da liga��o de esgoto
				if (contasDadosRelatorio[54] != null) { // 54
					imovelRelatorioHelper
							.setDiametroLigacaoEsgoto(contasDadosRelatorio[54]
									+ "");
				}
				// material da liga��o de esgoto
				if (contasDadosRelatorio[55] != null) { // 55
					imovelRelatorioHelper
							.setMaterialLigacaoEsgoto((String) contasDadosRelatorio[55]);
				}
				// consumo m�nimo esgoto
				if (contasDadosRelatorio[56] != null) { // 56
					imovelRelatorioHelper
							.setConsumoMinimoFixadoLigacaoEsgoto((Integer) contasDadosRelatorio[56]);
				}
				// percentual �gua coletada
				if (contasDadosRelatorio[57] != null) { // 57
					imovelRelatorioHelper
							.setPercentualAguaConsumidaColetada((BigDecimal) contasDadosRelatorio[57]);
				}
				// percentual esgoto
				if (contasDadosRelatorio[58] != null) { // 58
					imovelRelatorioHelper
							.setPercentual((BigDecimal) contasDadosRelatorio[58]);
				}
				// n�mero hidr�metro �gua
				if (contasDadosRelatorio[59] != null) { // 59
					imovelRelatorioHelper
							.setNumeroHidrometroAgua((String) contasDadosRelatorio[59]);
				}
				// ano fabrica��o hidr�metro �gua
				if (contasDadosRelatorio[60] != null) { // 60
					imovelRelatorioHelper
							.setAnoFabricaocaHidrometroAgua(((Integer) contasDadosRelatorio[60])
									.shortValue());
				}
				// capacidade hidr�metro �gua
				if (contasDadosRelatorio[61] != null) { // 61
					imovelRelatorioHelper
							.setCapacidadeHidrometroAgua(contasDadosRelatorio[61]
									+ "");
				}
				// marca hidr�metro �gua
				if (contasDadosRelatorio[62] != null) { // 62
					imovelRelatorioHelper
							.setMarcaHidrometroAgua(contasDadosRelatorio[62]
									+ "");
				}
				// di�metro hidr�metro �gua
				if (contasDadosRelatorio[63] != null) { // 63
					imovelRelatorioHelper
							.setDiametroHidrometroAgua(contasDadosRelatorio[63]
									+ "");
				}
				// tipo hidr�metro �gua
				if (contasDadosRelatorio[64] != null) { // 64
					imovelRelatorioHelper
							.setTipoHidrometroAgua(contasDadosRelatorio[64]
									+ "");
				}
				// data instala��o hidr�metro �gua
				if (contasDadosRelatorio[65] != null) { // 65
					imovelRelatorioHelper
							.setDataInstalacaoHidrometroAgua((Date) contasDadosRelatorio[65]);
				}
				// local instala��o hidr�metro �gua
				if (contasDadosRelatorio[66] != null) { // 66
					imovelRelatorioHelper.setLocalInstalacaoHidrometroAgua(""
							+ contasDadosRelatorio[66]);
				}
				// prote��o hidr�metro �gua
				if (contasDadosRelatorio[67] != null) { // 67
					imovelRelatorioHelper
							.setTipoProtecaoHidrometroAgua(contasDadosRelatorio[67]
									+ "");
				}
				// indicador cavalete hidr�metro �gua
				if (contasDadosRelatorio[68] != null) { // 68
					imovelRelatorioHelper
							.setIndicadorExistenciaCavaleteAgua(((Integer) contasDadosRelatorio[68])
									.shortValue());
				}
				// n�mero hidr�metro po�o
				if (contasDadosRelatorio[69] != null) { // 69
					imovelRelatorioHelper
							.setNumeroHidrometroPoco((String) contasDadosRelatorio[69]);
				}
				// ano fabrica��o hidr�metro po�o
				if (contasDadosRelatorio[70] != null) { // 70
					imovelRelatorioHelper
							.setAnoFabricacaoHidrometroPoco(((Integer) contasDadosRelatorio[70])
									.shortValue());
				}
				// capacidade hidr�metro po�o
				if (contasDadosRelatorio[71] != null) { // 71
					imovelRelatorioHelper
							.setCapacidadeHidrometroPoco(((String) contasDadosRelatorio[71]));
				}
				// marca hidr�metro po�o
				if (contasDadosRelatorio[72] != null) { // 72
					imovelRelatorioHelper
							.setMarcaHidrometroPoco((String) contasDadosRelatorio[72]);
				}
				// di�metro hidr�metro po�o
				if (contasDadosRelatorio[73] != null) { // 73
					imovelRelatorioHelper
							.setDiametroHidrometroPoco((String) contasDadosRelatorio[73]);
				}
				// tipo hidr�metro po�o
				if (contasDadosRelatorio[74] != null) { // 74
					imovelRelatorioHelper
							.setTipoHidrometroPoco((String) contasDadosRelatorio[74]);
				}
				// data instala��o hidr�metro po�o
				if (contasDadosRelatorio[75] != null) { // 75
					imovelRelatorioHelper
							.setDataInstalacaoHidrometroPoco((Date) contasDadosRelatorio[75]);
				}
				// local instala��o hidr�metro po�o
				if (contasDadosRelatorio[76] != null) { // 76
					imovelRelatorioHelper
							.setLocalInstalacaoHidrometroPoco((String) contasDadosRelatorio[76]);
				}
				// prote��o hidr�metro po�o
				if (contasDadosRelatorio[77] != null) { // 77
					imovelRelatorioHelper
							.setTipoProtecaoHidrometroPoco((String) contasDadosRelatorio[77]);
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[78] != null) { // 78
					imovelRelatorioHelper
							.setIndicadorExistenciaCavaletePoco(((Integer) contasDadosRelatorio[78])
									.shortValue());
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[79] != null) { // 79
					imovelRelatorioHelper
							.setConsumoMinimoFixadoAgua((Integer) contasDadosRelatorio[79]);
				}
				// indicador cavalete hidr�metro po�o
				if (contasDadosRelatorio[80] != null) { // 80
					imovelRelatorioHelper
							.setConsumoMinimoFixadoLigacaoEsgoto((Integer) contasDadosRelatorio[80]);
				}
				// indicador Jardim
				if (contasDadosRelatorio[81] != null) { // 81
					imovelRelatorioHelper
							.setJardim(((Short) contasDadosRelatorio[81])
									.toString());
				}

				// Rota
				if (contasDadosRelatorio[82] != null) { // 82
					imovelRelatorioHelper
							.setCodigoRota((Short) contasDadosRelatorio[82]);
				}

				// N�mero Sequencial Rota
				if (contasDadosRelatorio[83] != null) { // 83
					imovelRelatorioHelper
							.setNumeroSequencialRota((Integer) contasDadosRelatorio[83]);
				}

				// Tipo Faturamento
				if (contasDadosRelatorio[84] != null) { // 84
					imovelRelatorioHelper
							.setTipoFaturamento(contasDadosRelatorio[84] + "");
				}

				// Id Logradouro
				if (contasDadosRelatorio[85] != null) { // 85
					imovelRelatorioHelper
							.setIdLogradouro(contasDadosRelatorio[85] + "");
				}

				// DDD
				if (contasDadosRelatorio[86] != null
						&& contasDadosRelatorio[87] != null) { // 86, 87
					imovelRelatorioHelper.setFone(contasDadosRelatorio[86]
							+ "." + contasDadosRelatorio[87]);
				}
				
				// Testada Lote
				if (contasDadosRelatorio[88] != null) {
					imovelRelatorioHelper.setTestadaLote((Short) contasDadosRelatorio[88]);
				}

				// consumo Medio
				Integer consumoMedio = getControladorCobranca()
						.pesquisarConsumoMedioConsumoHistoricoImovel(
								new Integer(imovelRelatorioHelper
										.getMatriculaImovel()));
				if (consumoMedio != null) {
					imovelRelatorioHelper.setConsumoMedioImovel(consumoMedio);
				}

				Imovel imovel = new Imovel();

				Localidade localidadeImovel = new Localidade();
				localidadeImovel.setId(imovelRelatorioHelper.getIdLocalidade());
				SetorComercial setorComercialImovel = new SetorComercial();
				setorComercialImovel.setCodigo(imovelRelatorioHelper
						.getCodigoSetorComercial().intValue());
				Quadra quadraImovel = new Quadra();
				quadraImovel.setNumeroQuadra(imovelRelatorioHelper
						.getNumeroQuadra().intValue());

				imovel.setLocalidade(localidadeImovel);
				imovel.setSetorComercial(setorComercialImovel);
				imovel.setQuadra(quadraImovel);
				imovel.setLote(new Short(imovelRelatorioHelper.getNumeroLote())
						.shortValue());
				imovel.setSubLote(new Short(imovelRelatorioHelper
						.getNumeroSubLote()).shortValue());

				// inscricao formatada do imovel
				imovelRelatorioHelper.setInscricaoImovel(imovel
						.getInscricaoFormatada());

				// obter endereco
				imovelRelatorioHelper.setEndereco(getControladorEndereco()
						.pesquisarEndereco(
								new Integer(imovelRelatorioHelper
										.getMatriculaImovel())));

				Collection colecaoSubcategoria = this
						.pesquisarSubcategoriasImovelRelatorio(imovelRelatorioHelper
								.getMatriculaImovel());

				// ------subcategoria
				String[] arraySubcatgegoriasQtdEconomias = new String[colecaoSubcategoria
						.size()];
				if (!colecaoSubcategoria.isEmpty()) {

					Iterator iterator = colecaoSubcategoria.iterator();

					int i = 0;

					while (iterator.hasNext()) {

						Object[] arraySubcategoria = (Object[]) iterator.next();

						arraySubcatgegoriasQtdEconomias[i] = arraySubcategoria[0]
								.toString()
								+ "/" + arraySubcategoria[1].toString();

						i++;

					}
					imovelRelatorioHelper
							.setSubcategoriaQtdEconomia(arraySubcatgegoriasQtdEconomias);
				}

				// [UC0307 Obter Indicador de Exist�ncia de Hidr�metro no
				// Im�vel]
				imovelRelatorioHelper
						.setIndicadorExistenciaHidrometro(obterIndicadorExistenciaHidrometroImovel(imovelRelatorioHelper
								.getMatriculaImovel()));

				// [UC123 Obter Descri��es da Categoria do Im�vel]
				imovelRelatorioHelper
						.setDescricaoAbreviadaCategoria(obterDescricoesCategoriaImovel(
								new Imovel(imovelRelatorioHelper
										.getMatriculaImovel()))
								.getDescricaoAbreviada());

				colecaoGerarRelatorioImovelOutrosCriterios
						.add(imovelRelatorioHelper);
			}
		}

		return colecaoGerarRelatorioImovelOutrosCriterios;

	}

	/**
	 * Filtra o Pagamento pelo seu id carregando os dados necess�rios
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author K�ssia Albuquerque
	 * @date 12/07/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento)
			throws ControladorException {

		try {

			return repositorioImovel.pesquisarPagamentoPeloId(idPagamento);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Filtra o Pagamento Historico pelo seu id carregando os dados necess�rios
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author K�ssia Albuquerque
	 * @date 12/07/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(
			Integer idPagamentoHistorico) throws ControladorException {

		try {

			return repositorioImovel
					.pesquisarPagamentoHistoricoPeloId(idPagamentoHistorico);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0549] Consultar Dados de um Pagamento
	 * 
	 * @author Kassia Albuquerque
	 * @date 05/07/2007
	 * 
	 * @throws ControladorException
	 */
	public Cliente obterDescricaoIdCliente(Integer idImovel)
			throws ControladorException {

		Cliente cliente = null;

		try {
			Object[] objetoCliente = this.repositorioImovel
					.obterDescricaoIdCliente(idImovel);

			if (objetoCliente != null) {
				cliente = new Cliente();
				cliente.setId((Integer) objetoCliente[0]);
				cliente.setNome((String) objetoCliente[1]);
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return cliente;
	}

	/**
	 * [UC0549] Consultar Dados de um Pagamento
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/07/2007
	 * 
	 * @throws ControladorException
	 */
	public String pesquisarNomeAgenteArrecadador(Integer idPagamentoHistorico)
			throws ControladorException {

		String nomeCliente = null;

		try {
			String objetoNomeCliente = this.repositorioImovel
					.pesquisarNomeAgenteArrecadador(idPagamentoHistorico);

			if (objetoNomeCliente != null) {

				nomeCliente = objetoNomeCliente;
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return nomeCliente;
	}

	/**
	 * Obt�m a o 117� caracter de uma String
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/07/2007
	 * 
	 * @return String
	 * @throws ControladorException
	 */

	public String pesquisarCaracterRetorno(Integer idConteudoRegistro)
			throws ControladorException {

		try {

			return repositorioImovel
					.pesquisarCaracterRetorno(idConteudoRegistro);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0623] - GERAR RESUMO DE METAS EXECUTDO NO M�S(CAERN)
	 * 
	 * @author S�vio Luiz
	 * @date 08/08/2007
	 */
	public ImovelSubcategoria obterPrincipalSubcategoriaComCodigoGrupo(
			Integer idCategoria, Integer idImovel) throws ControladorException {

		try {
			Object[] dadosSubCategorias = repositorioImovel
					.obterSubCategoriasComCodigoGrupoPorCategoria(idCategoria,
							idImovel);

			ImovelSubcategoria subcategoriaPrincipal = new ImovelSubcategoria();

			if (dadosSubCategorias != null) {
				Integer idSubCategoria = (Integer) dadosSubCategorias[0];
				Short quantidadeEconomias = (Short) dadosSubCategorias[1];
				Date dataUltimaAlteracao = (Date) dadosSubCategorias[2];
				Integer codigoGrupoSubcategoria = (Integer) dadosSubCategorias[3];
				Subcategoria subcategoria = new Subcategoria();
				subcategoria.setId(idSubCategoria);
				if (codigoGrupoSubcategoria != null) {
					subcategoria
							.setCodigoGrupoSubcategoria(codigoGrupoSubcategoria);
				}
				ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
				imovelSubcategoriaPK.setSubcategoria(subcategoria);
				subcategoriaPrincipal.setComp_id(imovelSubcategoriaPK);
				subcategoriaPrincipal.setUltimaAlteracao(dataUltimaAlteracao);
				subcategoriaPrincipal
						.setQuantidadeEconomias(quantidadeEconomias);
			}

			return subcategoriaPrincipal;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Categoria obterPrincipalCategoriaConta(Integer idConta)
			throws ControladorException {
		Categoria categoriaPrincipal = null;

		Collection<Categoria> colecaoCategoriasComMaiorQtdEconomias = new ArrayList();

		Collection<Categoria> colecaoCategoriasConta = this.obterQuantidadeEconomiasContaCategoria(idConta);

		int quantidadeCategoria = -1;

		if (colecaoCategoriasConta != null) {
			// La�o para verificar qual a categoria com maior quantidade de economia
			for (Categoria categoriaConta : colecaoCategoriasConta) {
				if (quantidadeCategoria < categoriaConta
						.getQuantidadeEconomiasCategoria().intValue()) {
					quantidadeCategoria = categoriaConta
							.getQuantidadeEconomiasCategoria().intValue();

					colecaoCategoriasComMaiorQtdEconomias = new ArrayList();
					colecaoCategoriasComMaiorQtdEconomias.add(categoriaConta);
				} else if (quantidadeCategoria == categoriaConta
						.getQuantidadeEconomiasCategoria().intValue()) {
					colecaoCategoriasComMaiorQtdEconomias.add(categoriaConta);
				}
			}
		}

		if (colecaoCategoriasComMaiorQtdEconomias.size() == 1) {
			categoriaPrincipal = colecaoCategoriasComMaiorQtdEconomias
					.iterator().next();
		} else if (colecaoCategoriasComMaiorQtdEconomias.size() > 1) {
			int idTemp = -1;
			for (Categoria categoriaImovel : colecaoCategoriasComMaiorQtdEconomias) {
				if (idTemp == -1) {
					idTemp = categoriaImovel.getId().intValue();
					categoriaPrincipal = categoriaImovel;
				} else if (categoriaImovel.getId().intValue() < idTemp) {
					idTemp = categoriaImovel.getId().intValue();
					categoriaPrincipal = categoriaImovel;
				}

			}
		}

		return categoriaPrincipal;
	}

	public Integer pesquisarSequencialRota(Integer idImovel)
			throws ControladorException {
		try {
			return repositorioImovel.pesquisarSequencialRota(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	private ControladorTransacaoLocal getControladorTransacao() {
		ControladorTransacaoLocalHome localHome = null;
		ControladorTransacaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorTransacaoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_TRANSACAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	protected ControladorAtualizacaoCadastralLocal getControladorAtualizacaoCadastral() {

		ControladorAtualizacaoCadastralLocalHome localHome = null;
		ControladorAtualizacaoCadastralLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAtualizacaoCadastralLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ATUALIZACAO_CADASTRAL);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
    /**
     * [UC0541] Emitir 2 Via de Conta Internet
     * 
     * @author Vivianne Sousa
     * @date 02/09/2007
     * 
     * @throws ErroRepositorioException
     */

    public Imovel pesquisarDadosImovel(Integer idImovel)throws ControladorException {
        try {
            return repositorioImovel.pesquisarDadosImovel(idImovel);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }
    
    /**
     * 
     * Permite Pesquisar as categorias do Im�vel [UC0394] Gerar D�bitos a Cobrar
     * 
     * de Doa��es
     * 
     * 
     * 
     * @author C�sar Ara�jo
     * 
     * @date 10/09/2006
     * 
     * @param Imovel
     * 
     * imovel - objeto imovel
     * 
     * @return Collection<Categoria> - Cole��o de categorias
     * 
     * @throws ErroRepositorioException
     * 
     */

    public Collection<Categoria> pesquisarCategoriasImovel(Imovel imovel)throws ControladorException {
        try {
            return repositorioImovel.pesquisarCategoriasImovel(imovel);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }
    
    /**
	 * 
	 * recupera os ImovelSubcategoria, com 5 resultados, ordenados por Qt economias desc
	 *
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
    
    public Collection<ImovelSubcategoria> pesquisarImovelSubcategoria(Imovel imovel) throws ControladorException {
        try {
            return repositorioImovel.pesquisarImovelSubcategoriasMaxCinco(imovel.getId());
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }
    
    public Collection<ImovelSubcategoria> pesquisarImovelSubcategorias(Imovel imovel) throws ControladorException {
        try {
            return repositorioImovel.pesquisarImovelSubcategorias(imovel.getId());
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }
    
    
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */
    public Collection pesquisarDadosImovel(Collection colecaoIdsImovel) throws ControladorException {
        try {
        	
        	Collection colecaoRelatorioAnaliseConsumoHelper = null;
            Collection colecaoImoveleRota =  repositorioImovel.pesquisarInscricaoImoveleRota(colecaoIdsImovel);
            
            
    		if (colecaoImoveleRota != null && !colecaoImoveleRota.isEmpty()) {

    			Iterator iteratorColecaoImoveiseRota = colecaoImoveleRota.iterator();
    			colecaoRelatorioAnaliseConsumoHelper = new ArrayList();

    			RelatorioAnaliseConsumoHelper relatorioAnaliseConsumoHelper = null;
    			while (iteratorColecaoImoveiseRota.hasNext()) {

    				Object[] imoveleRota = (Object[]) iteratorColecaoImoveiseRota.next();
    				relatorioAnaliseConsumoHelper = new RelatorioAnaliseConsumoHelper();

    				// id imovel
    				if (imoveleRota[0] != null) { 
    					relatorioAnaliseConsumoHelper.setMatriculaImovel("" + (Integer)imoveleRota[0]);
    					
    					relatorioAnaliseConsumoHelper.setEndereco(getControladorEndereco()
    							.pesquisarEnderecoFormatado((Integer)imoveleRota[0]));
    					
    					Categoria categoria = this.obterPrincipalCategoriaImovel((Integer)imoveleRota[0]);
    					relatorioAnaliseConsumoHelper.setCategoria(categoria.getDescricaoAbreviada());
    				}
    				
    				//id localidade
    				if (imoveleRota[1] != null) { 
    					relatorioAnaliseConsumoHelper.setIdLocalidade("" + (Integer)imoveleRota[1]);
    				}
    				
    				//codigo do setor comercial
    				if (imoveleRota[2] != null) { 
    					relatorioAnaliseConsumoHelper.setCodigoSetorComercial("" + (Integer)imoveleRota[2]);
    				}
    				
    				//numer da quadra
    				if (imoveleRota[3] != null) {
    					relatorioAnaliseConsumoHelper.setNumeroQuadra("" + (Integer)imoveleRota[3]);
    				}
    				
    				//lote
    				if (imoveleRota[4] != null) { 
    					relatorioAnaliseConsumoHelper.setLote("" + (Short)imoveleRota[4]);
    				}
    				
    				//subLote
    				if (imoveleRota[5] != null) { 
    					relatorioAnaliseConsumoHelper.setSubLote("" + (Short)imoveleRota[5]);
    				}
    				
    				//sequencial de rota
    				if (imoveleRota[6] != null) {
    					relatorioAnaliseConsumoHelper.setSeqRota("" + (Integer)imoveleRota[6]);
    				}
    				
    				//codigo de rota
    				if (imoveleRota[7] != null) {
    					relatorioAnaliseConsumoHelper.setRota("" + (Short)imoveleRota[7]);
    				}
    				
    				//quantidade de economias
    				if (imoveleRota[8] != null) {
    					relatorioAnaliseConsumoHelper.setQtdEconomias("" + (Short)imoveleRota[8]);
    				}
    				
    				Cliente cliente = this.pesquisarClienteUsuarioImovel(new Integer(relatorioAnaliseConsumoHelper.getMatriculaImovel()));
                    
                    relatorioAnaliseConsumoHelper.setUsuario(cliente.getNome());
    				
    				colecaoRelatorioAnaliseConsumoHelper.add(relatorioAnaliseConsumoHelper);
    			}
    		}	
            
            return colecaoRelatorioAnaliseConsumoHelper;
            
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
	}
    
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */
    public Collection pesquisarDadosImovel(
			FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper,
			Integer anoMes) throws ControladorException {
        try {
        	
        	Collection colecaoRelatorioAnaliseConsumoHelper = null;
            Collection colecaoImoveleRota =  repositorioImovel.pesquisarInscricaoImoveleRota(filtrarAnaliseExcecoesLeiturasHelper, anoMes);
            
            
    		if (colecaoImoveleRota != null && !colecaoImoveleRota.isEmpty()) {

    			Iterator iteratorColecaoImoveiseRota = colecaoImoveleRota.iterator();
    			colecaoRelatorioAnaliseConsumoHelper = new ArrayList();

    			RelatorioAnaliseConsumoHelper relatorioAnaliseConsumoHelper = null;
    			while (iteratorColecaoImoveiseRota.hasNext()) {

    				Object[] imoveleRota = (Object[]) iteratorColecaoImoveiseRota.next();
    				relatorioAnaliseConsumoHelper = new RelatorioAnaliseConsumoHelper();

    				// id imovel
    				if (imoveleRota[0] != null) { 
    					relatorioAnaliseConsumoHelper.setMatriculaImovel("" + (Integer)imoveleRota[0]);
    					
    					relatorioAnaliseConsumoHelper.setEndereco(getControladorEndereco()
    							.pesquisarEnderecoFormatado((Integer)imoveleRota[0]));
    					
    					Categoria categoria = this.obterPrincipalCategoriaImovel((Integer)imoveleRota[0]);
    					relatorioAnaliseConsumoHelper.setCategoria(categoria.getDescricaoAbreviada());
    				}
    				
    				//id localidade
    				if (imoveleRota[1] != null) { 
    					relatorioAnaliseConsumoHelper.setIdLocalidade("" + (Integer)imoveleRota[1]);
    				}
    				
    				//codigo do setor comercial
    				if (imoveleRota[2] != null) { 
    					relatorioAnaliseConsumoHelper.setCodigoSetorComercial("" + (Integer)imoveleRota[2]);
    				}
    				
    				//numer da quadra
    				if (imoveleRota[3] != null) {
    					relatorioAnaliseConsumoHelper.setNumeroQuadra("" + (Integer)imoveleRota[3]);
    				}
    				
    				//lote
    				if (imoveleRota[4] != null) { 
    					relatorioAnaliseConsumoHelper.setLote("" + (Short)imoveleRota[4]);
    				}
    				
    				//subLote
    				if (imoveleRota[5] != null) { 
    					relatorioAnaliseConsumoHelper.setSubLote("" + (Short)imoveleRota[5]);
    				}
    				
    				//sequencial de rota
    				if (imoveleRota[6] != null) {
    					relatorioAnaliseConsumoHelper.setSeqRota("" + (Integer)imoveleRota[6]);
    				}
    				
    				//codigo de rota
    				if (imoveleRota[7] != null) {
    					relatorioAnaliseConsumoHelper.setRota("" + (Short)imoveleRota[7]);
    				}
    				
    				//quantidade de economias
    				if (imoveleRota[8] != null) {
    					relatorioAnaliseConsumoHelper.setQtdEconomias("" + (Short)imoveleRota[8]);
    				}
    				
    				//usu�rio
    				if (imoveleRota[9] != null) {
    					relatorioAnaliseConsumoHelper.setUsuario((String) imoveleRota[9]);
    				}
    				
    				colecaoRelatorioAnaliseConsumoHelper.add(relatorioAnaliseConsumoHelper);
    			}
    		}	
            
            return colecaoRelatorioAnaliseConsumoHelper;
            
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
	}
    
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o ,de rota e endere�o para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarDadosImovel(String idsImovel)throws ControladorException {
        try {
        	
        	Collection colecaoRelatorioAnaliseConsumoHelper = null;
            Collection colecaoImoveleRota =  repositorioImovel.pesquisarInscricaoImoveleRota(idsImovel);
            
            
    		if (colecaoImoveleRota != null && !colecaoImoveleRota.isEmpty()) {

    			Iterator iteratorColecaoImoveiseRota = colecaoImoveleRota.iterator();
    			colecaoRelatorioAnaliseConsumoHelper = new ArrayList();

    			RelatorioAnaliseConsumoHelper relatorioAnaliseConsumoHelper = null;
    			while (iteratorColecaoImoveiseRota.hasNext()) {

    				Object[] imoveleRota = (Object[]) iteratorColecaoImoveiseRota.next();
    				relatorioAnaliseConsumoHelper = new RelatorioAnaliseConsumoHelper();

    				// id imovel
    				if (imoveleRota[0] != null) { 
    					relatorioAnaliseConsumoHelper.setMatriculaImovel("" + (Integer)imoveleRota[0]);
    					
    					relatorioAnaliseConsumoHelper.setEndereco(getControladorEndereco()
    							.pesquisarEnderecoFormatado((Integer)imoveleRota[0]));
    					
    					Categoria categoria = this.obterPrincipalCategoriaImovel((Integer)imoveleRota[0]);
    					relatorioAnaliseConsumoHelper.setCategoria(categoria.getDescricaoAbreviada());
    				}
    				
    				//id localidade
    				if (imoveleRota[1] != null) { 
    					relatorioAnaliseConsumoHelper.setIdLocalidade("" + (Integer)imoveleRota[1]);
    				}
    				
    				//codigo do setor comercial
    				if (imoveleRota[2] != null) { 
    					relatorioAnaliseConsumoHelper.setCodigoSetorComercial("" + (Integer)imoveleRota[2]);
    				}
    				
    				//numer da quadra
    				if (imoveleRota[3] != null) {
    					relatorioAnaliseConsumoHelper.setNumeroQuadra("" + (Integer)imoveleRota[3]);
    				}
    				
    				//lote
    				if (imoveleRota[4] != null) { 
    					relatorioAnaliseConsumoHelper.setLote("" + (Short)imoveleRota[4]);
    				}
    				
    				//subLote
    				if (imoveleRota[5] != null) { 
    					relatorioAnaliseConsumoHelper.setSubLote("" + (Short)imoveleRota[5]);
    				}
    				
    				//sequencial de rota
    				if (imoveleRota[6] != null) {
    					relatorioAnaliseConsumoHelper.setSeqRota("" + (Integer)imoveleRota[6]);
    				}
    				
    				//codigo de rota
    				if (imoveleRota[7] != null) {
    					relatorioAnaliseConsumoHelper.setRota("" + (Short)imoveleRota[7]);
    				}
    				
    				//quantidade de economias
    				if (imoveleRota[8] != null) {
    					relatorioAnaliseConsumoHelper.setQtdEconomias("" + (Short)imoveleRota[8]);
    				}
    				
    				Cliente cliente = this.pesquisarClienteUsuarioImovel(new Integer(relatorioAnaliseConsumoHelper.getMatriculaImovel()));
                    
                    relatorioAnaliseConsumoHelper.setUsuario(cliente.getNome());
    				
    				colecaoRelatorioAnaliseConsumoHelper.add(relatorioAnaliseConsumoHelper);
    			}
    		}	
            
            return colecaoRelatorioAnaliseConsumoHelper;
            
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
	}
    
	
	/**
	 * 
	 * Pesquisa os im�veis do cliente de acordo com o tipo de rela��o
	 * 
	 * 
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * 
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
	 * 
	 * @created 23/11/2007
	 * 
	 * 
	 * 
	 * @param cliente
	 * 
	 * @param relacaoClienteImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImoveisClientesRelacao(Collection idsCliente,Integer numeroInicial)

	throws ControladorException{
		try{
			return repositorioImovel.pesquisarImoveisClientesRelacao(idsCliente,numeroInicial);
		} catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
	}
	
	public CobrancaSituacao pesquisarImovelCobrancaSituacao(Integer idImmovel)
		throws ControladorException{
		try{
			return repositorioImovel.pesquisarImovelCobrancaSituacao(idImmovel);
		} catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
	}
	
	/**
	 * 
	 * Atualiza a situa��o de cobran�a do im�vel
	 * 
	 */
	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobranca, Integer idImovel)
		throws ControladorException{
		try{
			repositorioImovel.atualizarSituacaoCobrancaImovel(idSituacaoCobranca, idImovel);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis.
	 * Utilizado para corrigir o erro da listagem de Imoveis: o metodo pesquisarQuantidadeImovel nao
	 * traz a mesma quantidade de imovel do metodo pesquisarImovelInscricaoNew.
	 * 
	 * @author Ivan S�rgio
	 * @date 11/03/2008
	 */
	public Integer pesquisarQuantidadeImovelInscricao(
			String idImovel, String idLocalidade, String codigoSetorComercial,
			String numeroQuadra, String lote, String subLote,
			String codigoCliente, String idMunicipio, String cep,
			String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal, 
			boolean pesquisarImovelCondominio) throws ControladorException {

		Object quantidade = null;
		Integer retorno = null;

		try {
			quantidade = repositorioImovel.pesquisarQuantidadeImovelInscricao(
					idImovel, idLocalidade, codigoSetorComercial,
					numeroQuadra, lote, subLote, codigoCliente,
					idMunicipio, cep, idBairro, idLogradouro, numeroImovelInicial, numeroImovelFinal,  
					pesquisarImovelCondominio);

		} catch (ErroRepositorioException ex) {
			//CRC3586 
			//autor: Anderson Italo
			//data: 03/02/2010
			//caso seja exce��o devido a convers�o de alfanumerico para numerico
			if (ex.getMessage().equals(ConstantesSistema.ERRO_SQL_CONVERSSAO_ALFANUMERICO_PARA_NUMERICO)){
				throw new ControladorException("atencao.pesquisa_nao_realizada_devido_caracteres_alfanumericos_campo");
			}else{
				throw new ControladorException("erro.sistema", ex);
			}
		}

		if (quantidade != null) {
			retorno = (Integer) quantidade;

		}

		return retorno;
	}

    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilDiferenteCorporativo()throws ControladorException{
        try{
           return  repositorioImovel.pesquisarImovelPerfilDiferenteCorporativo();
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }
    
    /**
     * [UC0810] Obter Quantidade de Economias Virtuais
     *
     * @author Raphael Rossiter
     * @date 04/06/2008
     *
     * @param idImovel
     * @return Integer
     * @throws ControladorException
     */
    public Integer obterQuantidadeEconomiasVirtuais(Integer idImovel) throws ControladorException{
	
    	Integer qtdEconomias = 0;
    	
    	//[UC0801] - Obter Quantidade de Economias por Subcategoria
		Collection<Subcategoria> colecaoSubcategoria = this.obterQuantidadeEconomiasSubCategoria(idImovel);
		
		if (colecaoSubcategoria != null && !colecaoSubcategoria.isEmpty()){
			
			Iterator iterator = colecaoSubcategoria.iterator();
			
			while (iterator.hasNext()){
				
				Subcategoria subcategoria = (Subcategoria) iterator.next();
				
				//Caso a categoria associada � subcategoria tenha fator de economias diferente de NULO
				if (subcategoria.getCategoria().getFatorEconomias() != null){
				
					//A quantidade de economias ser� o fator de economias da categoria
					qtdEconomias = qtdEconomias + subcategoria.getCategoria().getFatorEconomias().intValue(); 
				}
				else{
					
					//A quantidade de economias ser� a quantidade de economias da subcategoria
					qtdEconomias = qtdEconomias + subcategoria.getQuantidadeEconomias();
				}
			}
		}
		
		return qtdEconomias;
    }
    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilTarifaSocialDiferenteCorporativo()throws ControladorException{
        try{
            return  repositorioImovel.pesquisarImovelPerfilTarifaSocialDiferenteCorporativo();
         } catch (ErroRepositorioException ex) {
             throw new ControladorException("erro.sistema", ex);
         }
     } 
    
    /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise()throws ControladorException{
	  try{
          return  repositorioImovel.pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise();
       } catch (ErroRepositorioException ex) {
           throw new ControladorException("erro.sistema", ex);
       }
	}    
    
    /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
    public Collection pesquisarImoveisComSituacaoLigadoEmAnalise(Integer idLocalidade)throws ControladorException{
        try{
            return  repositorioImovel.pesquisarImoveisComSituacaoLigadoEmAnalise(idLocalidade);
         } catch (ErroRepositorioException ex) {
             throw new ControladorException("erro.sistema", ex);
         }
     } 
     
    
    /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	
	public void atualizarSituacaoAguaPorImovel(String id, String situacaoAguaLigado) throws ControladorException{
		 try{
	            repositorioImovel.atualizarSituacaoAguaPorImovel(id,situacaoAguaLigado);
	      } catch (ErroRepositorioException ex) {
	             throw new ControladorException("erro.sistema", ex);
	      }
	}
    

	/**
	 * Consultar Perfil Quadra
	 * 
	 * @param idImovel
	 * @return Perfil da Quadra 
	 * @exception ControladorException
	 */
	public Integer obterQuadraPerfil(Integer idImovel)throws ControladorException {
		Integer idPerfilQuadra = null;
		try {
			idPerfilQuadra = this.repositorioImovel.obterQuadraPerfil(idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return idPerfilQuadra;
	}

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que n�o estejam com a situa��o
	 * atual igual a "PR�-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelIdComContaNaoPreFaturada(Integer imovelId,
			Integer anoMesReferencia) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImovelIdComContaNaoPreFaturada(imovelId,
					anoMesReferencia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	
	/**
	 * Inserir Situa��o Especial de Faturamento no momento da inclus�o do im�vel
	 * 
	 * @author Raphael Rossiter
	 * @date 19/08/2008
	 *
	 * @param inserirImovelHelper
	 * @throws ControladorException
	 */
	public void inserirSituacaoEspecialFaturamento(InserirImovelHelper inserirImovelHelper) 
		throws ControladorException{
		
		if (inserirImovelHelper.getLigacaoEsgotoEsgotamento().getFaturamentoSituacaoMotivo() != null){
			
			//PAR�METROS DO SISTEMA
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();
			
			//IMOVEL
			faturamentoSituacaoHistorico.setImovel(inserirImovelHelper.getImovel());
			
			//ANO_MES_INICIAL = ANO_MES_FATURAMENTO
			faturamentoSituacaoHistorico
			.setAnoMesFaturamentoSituacaoInicio(sistemaParametro.getAnoMesFaturamento());
			
			//ANO_MES_FINAL = ANO_MES_FATURAMENTO + Quantidade de meses da tabela LigacaoEsgotoEsgotamento
			faturamentoSituacaoHistorico
			.setAnoMesFaturamentoSituacaoFim(Util.somaMesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento(),
			inserirImovelHelper.getLigacaoEsgotoEsgotamento().getQuantidadeMesesSituacaoEspecial()));

			//ANO_MES_FATURAMENTO_RETIRADA = NULL
			faturamentoSituacaoHistorico.setAnoMesFaturamentoRetirada(null);
			
			//FATURAMENTO_SITUACAO_MOTIVO
			faturamentoSituacaoHistorico.setFaturamentoSituacaoMotivo(
			inserirImovelHelper.getLigacaoEsgotoEsgotamento().getFaturamentoSituacaoMotivo());

			//FATURAMENTO_SITUACAO_TIPO
			faturamentoSituacaoHistorico.setFaturamentoSituacaoTipo(
			inserirImovelHelper.getLigacaoEsgotoEsgotamento().getFaturamentoSituacaoTipo());
			
			//USU�RIO
			faturamentoSituacaoHistorico.setUsuario(inserirImovelHelper.getUsuario());
			
			//USU�RIO INFORMA
			faturamentoSituacaoHistorico.setUsuarioInforma(inserirImovelHelper.getUsuario());
			
			faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());
			
			//INSERINDO
			this.getControladorUtil().inserir(faturamentoSituacaoHistorico);
			
			Imovel imovel = inserirImovelHelper.getImovel();
			imovel.setFaturamentoSituacaoTipo(
				inserirImovelHelper.getLigacaoEsgotoEsgotamento().getFaturamentoSituacaoTipo());
			imovel.setFaturamentoSituacaoMotivo(
					inserirImovelHelper.getLigacaoEsgotoEsgotamento().getFaturamentoSituacaoMotivo());
			
			this.getControladorUtil().atualizar(imovel);
			
			
		}
		
	}

	
	/**
	 * Inserir Imovel
	 *
	 * Validar Aba de inserir imovel subcategoria
	 *
	 * @author Raphael Rossiter
	 * @date 22/08/2008
	 *
	 * @param colecaoSubcategorias
	 * @param permissaoEspecial
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void validarAbaInserirImovelSubcategoria(Collection colecaoSubcategorias, 
			int permissaoEspecial, Usuario usuarioLogado) throws ControladorException{
		
		if (colecaoSubcategorias == null || colecaoSubcategorias.isEmpty()) {
			throw new ControladorException("atencao.nenhuma_subcategoria");
        }
		
		//[FS0020] - Permiss�o Especial para Categoria.
		ImovelSubcategoria imovelSubcategoria = null;
		Categoria categoria = null;
		Iterator ite = colecaoSubcategorias.iterator();
		
		while (ite.hasNext()){
			
			imovelSubcategoria = (ImovelSubcategoria) ite.next();
			
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			
			filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
			imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getId()));
			
			Collection colecaoCategoria = this.getControladorUtil().pesquisar(filtroCategoria,
			Categoria.class.getName());
			
			categoria = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoria);
			
			if (categoria.getIndicadorPermissaoEspecial() == ConstantesSistema.SIM.shortValue() &&
				!this.getControladorPermissaoEspecial()
				.verificarPermissaoEspecial(permissaoEspecial, usuarioLogado)){
				
				throw new ControladorException("atencao.usuario.permissao_negada_orgao_publico", null);
			}
		}
	}

	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral)throws ControladorException {
	
		try {

			this.repositorioImovel.atualizarImovelSituacaoAtualizacaoCadastral(
					idImovel, idSituacaoAtualizacaoCadastral);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa)throws ControladorException {
	
		try {

			this.repositorioImovel.atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(
					idImovel, idSituacaoAtualizacaoCadastral, idEmpresa);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	   /**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/09/2008
	 */
	public Integer verificaExistenciaImovelAtualizacaoCadastral(Integer idImovel) throws ControladorException{
		
		try {

			return this.repositorioImovel.verificaExistenciaImovelAtualizacaoCadastral(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Informar Economia
	 * 
	 * @author Vivianne Sousa
	 * @date 23/10/2008
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel, Integer idSubcategoria) 
		throws ControladorException{
		
		try {

			return this.repositorioImovel.pesquisarImovelEconomia(idImovel,idSubcategoria);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
    /**
     * Pesquisar Im�vel Atualiza��o Cadastral(Dados da Inscri��o)
     * 
     * @author Ana Maria
     * @date 17/09/2008
     * 
     * @throws ErroRepositorioException
     */

    public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastralInscricao(Integer idImovel, Integer idEmpresa) 
    	throws ControladorException {
        try {
            return repositorioImovel.pesquisarImovelAtualizacaoCadastralInscricao(idImovel, idEmpresa);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }

	/**
	 * Consultar Im�veis Atualiza��o Cadastral por Quadra
	 * 
	 * @param idSetorComercial
	 * @param quadraInicial
	 * @param quadraFinal	 
	 * @param idEmpresa
	 * @return Collection<Imovel> - Cole��o de im�veis.
	 * 
	 * @author Ana Maria
     * @date 18/09/2008
	 * @exception ErroRepositorioException
	 */

	public Collection obterImoveisAtualizacaoCadastral(Integer idLocalidade, String codigoSetor,
			Integer quadraInicial, Integer quadraFinal, Integer idEmpresa, Integer idRota)throws ControladorException {
        try {
            return repositorioImovel.obterImoveisAtualizacaoCadastral(idLocalidade, codigoSetor, quadraInicial, 
            		quadraFinal, idEmpresa, idRota);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
	}

	/**
	 * Pesquisar exist�ncia de im�vel economia
	 * 
	 * @author Ana Maria
	 * @date 05/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean pesquisarExistenciaImovelEconomia(Integer idImovel, Integer idSubcategoria) 
		throws ControladorException{
		
		try {

			return this.repositorioImovel.pesquisarExistenciaImovelEconomia(idImovel,idSubcategoria);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * Valida��es do Imovel referente � aba de Localidade
	 * 
	 * @author Victor Cisneiros, Raphael Rossiter
	 * @date 28/01/2009, 11/05/2009
	 */
	public ImovelAbaLocalidadeRetornoHelper validarImovelAbaLocalidade(
			ImovelAbaLocalidadeHelper helper) throws ControladorException {
		
		Integer idImovel = helper.getIdImovel();
		String idLocalidade = helper.getIdLocalidade();
		String codigoSetorComercial = helper.getCodigoSetorComercial();
		String numeroQuadra = helper.getNumeroQuadra();
		String lote = helper.getLote();
		String subLote = helper.getSublote();
		
		Localidade localidade = null;
		SetorComercial setorComercial = null;
		Quadra quadra = null;
		QuadraFace quadraFace = null;
		
		// [FS0002] Verificar existencia da localidade
		if (Util.verificarIdNaoVazio(idLocalidade)) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, new Integer(idLocalidade)));
			Collection pesquisa = getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				localidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
				//[FS0028] - Verificar Localidade Bloqueada.
				if (localidade.getIndicadorBloqueio().intValue() == Localidade.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue()){
					if (helper.getUsuario() != null){
						if (!this.getControladorPermissaoEspecial().verificarPermissaoEspecial(PermissaoEspecial.BLOQUEAR_ALTERACAO_IMOVEIS, helper.getUsuario())){
							if (helper.getIdImovel() != null && helper.getIdImovel().intValue() > 0){
								//altera��o
								throw new ControladorException("atencao.localidade_bloqueada_alterar_imovel", null, "Localidade");
							}else{
								//inclus�o
								throw new ControladorException("atencao.localidade_bloqueada_inserir_imovel", null, "Localidade");
							}
						}
					}
				}
				
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Localidade");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Localidade");
		}
		
		// [FS0003] Verificar existencia do setor
		if (Util.verificarIdNaoVazio(codigoSetorComercial)) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, localidade.getId()));
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(codigoSetorComercial)));
			Collection pesquisa = getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				//[FS0029] - Verificar Setor Bloqueado
				if (setorComercial.getIndicadorBloqueio().intValue() == SetorComercial.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue()){
					if (helper.getUsuario() != null){
						if (!this.getControladorPermissaoEspecial().verificarPermissaoEspecial(PermissaoEspecial.BLOQUEAR_ALTERACAO_IMOVEIS, helper.getUsuario())){
							if (helper.getIdImovel() != null && helper.getIdImovel().intValue() > 0){
								//altera��o
								throw new ControladorException("atencao.setor_bloqueado_alterar_imovel", null, "Setor Comercial");
							}else{
								//inclus�o
								throw new ControladorException("atencao.setor_bloqueado_inserir_imovel", null, "Setor Comercial");
							}
						}
					}
				}
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Setor Comercial");
		}
		
		// [FS0004] Verificar existencia da quadra
		if (Util.verificarIdNaoVazio(numeroQuadra)) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));
			
			filtroQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroQuadra.BAIRRO);
			Collection pesquisa = getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				quadra = (Quadra) Util.retonarObjetoDeColecao(pesquisa);
				//FS0030] - Verificar Quadra Bloqueada.
				if (quadra.getIndicadorBloqueio().intValue() == Quadra.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue()){
					if (helper.getUsuario() != null){
						if (!this.getControladorPermissaoEspecial().verificarPermissaoEspecial(PermissaoEspecial.BLOQUEAR_ALTERACAO_IMOVEIS, helper.getUsuario())){
							if (helper.getIdImovel() != null && helper.getIdImovel().intValue() > 0){
								//altera��o
								throw new ControladorException("atencao.quadra_bloqueada_alterar_imovel", null, "Quadra");
							}else{
								//inclus�o
								throw new ControladorException("atencao.quadra_bloqueada_inserir_imovel", null, "Quadra");
							}
						}
					}
				}
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Quadra");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Quadra");
		}
		
		/*
		 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da tabela SISTEMA_PARAMETROS) 
		 * O campo face da quadra ser� obrigat�rio;
		 */
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
			
			if (helper.getIdQuadraFace() == null || helper.getIdQuadraFace().equals("")){
				
				throw new ControladorException("atencao.required", null, "Face");
			}
			else{
				
				FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
				
				filtroQuadraFace.adicionarParametro(new ParametroSimples(
				FiltroQuadraFace.ID, new Integer(helper.getIdQuadraFace())));
				
				Collection pesquisa = this.getControladorUtil().pesquisar(
				filtroQuadraFace, QuadraFace.class.getName());
				
				quadraFace = (QuadraFace) Util.retonarObjetoDeColecao(pesquisa);
			}
		}
		   
		
		if (lote == null || lote.trim().equals("")) {
			throw new ControladorException("atencao.required", null, "Lote");
		}
		
		if (subLote == null || subLote.trim().equals("")) {
			throw new ControladorException("atencao.required", null, "SubLote");
		}
		 
		// [FS0013] Verificar duplicidade de inscricao
		FiltroImovel filtroImovel = new FiltroImovel(); 
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.LOCALIDADE_ID, new Integer(idLocalidade)));
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.SETOR_COMERCIAL_CODIGO, new Integer(codigoSetorComercial)));
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.QUADRA_NUMERO, new Integer(numeroQuadra)));
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.LOTE, new Integer(lote)));
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.SUBLOTE, new Integer(subLote)));
		Collection colecaoImoveis = getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());
		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			if (idImovel != null) {
				if (!idImovel.equals(imovel.getId())) {
					throw new ControladorException("atencao.imovel_ja_cadastrado", null, imovel.getId().toString()); 
				}
			} else {
				throw new ControladorException("atencao.imovel_ja_cadastrado", null, imovel.getId().toString()); 
			}
		}
		
		ImovelAbaLocalidadeRetornoHelper retorno = new ImovelAbaLocalidadeRetornoHelper();
		retorno.setLocalidade(localidade);
		retorno.setSetorComercial(setorComercial);
		retorno.setQuadra(quadra);
		retorno.setQuadraFace(quadraFace);
		
		if(idImovel != null){
			// Desenvolvedor:Hugo Amorim, Arthur Carvalho 
			// Analista:F�tima Sampaio Data:02/07/2010
			// [FS0037] Verificar Im�vel em Processo de Faturamento
			// Verific�o se imovel esta em processo de faturamento,
			// caso esteja exibir mensagem de alerta
			
			boolean imovelEmProcessoDeFaturamento = 
				this.verificarImovelEmProcessoDeFaturamento(
						idImovel );
			
			//[FS0037] Verificar Im�vel em Processo de Faturamento
			if(imovelEmProcessoDeFaturamento){
				retorno.setFlagVerificarImovelEmProcessoDeFaturamento(true);
			}
		}
		return retorno;
	}
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * Valida��es do Imovel referente � aba de Endere�o
	 * 
	 * @author Victor Cisneiros
	 * @date 28/01/2009
	 */
	public ImovelAbaEnderecoRetornoHelper validarImovelAbaEndereco(
			ImovelAbaEnderecoHelper helper) throws ControladorException {
		
		Municipio municipio = null;
		Integer idMunicipio = null;
		
		boolean municipioEnderecoDiferenteMunicipioSetorComercial = false;
		
		Collection<Imovel> imovelEnderecos = helper.getImovelEnderecos();
		SetorComercial setorComercial = helper.getSetorComercial();
		Usuario usuarioLogado = helper.getUsuarioLogado();
		Integer idQuadraAnterior = helper.getIdQuadraAnterior();
		
		if (imovelEnderecos == null || imovelEnderecos.isEmpty()) {
			throw new ControladorException("atencao.imovel_endereco.nao_cadastrado");
		} else if (imovelEnderecos.size() > 1) {
			throw new ControladorException("atencao.imovel_endereco.mais_de_um");
		}
		
		// [FS0016] Verificar municipio do Setor Comercial
		Imovel imovelEndereco = (Imovel) Util.retonarObjetoDeColecao(imovelEnderecos);
		
		if ( imovelEndereco.getLogradouroBairro() != null && 
			 imovelEndereco.getLogradouroBairro().getLogradouro() != null && 
			 imovelEndereco.getLogradouroBairro().getLogradouro().getMunicipio() != null){
			
			municipio = imovelEndereco.getLogradouroBairro().getLogradouro().getMunicipio();						
			idMunicipio = imovelEndereco.getLogradouroBairro().getLogradouro().getMunicipio().getId();
			
		}
		
		if (imovelEndereco.getLogradouroBairro() != null && 
			imovelEndereco.getLogradouroBairro().getLogradouro() != null && 
			imovelEndereco.getLogradouroBairro().getLogradouro().getMunicipio() != null) {
			
			// Se Municipio do endereco informado for diferente do Municipio do 
			// SetorComercial, verificar se o Usuario tem permissao para realizar 
			// cadastro com valores diferentes de Municipio
			
			if (idMunicipio.intValue() != setorComercial.getMunicipio().getId().intValue()) {
				municipioEnderecoDiferenteMunicipioSetorComercial = true;
				
				if (helper.getIdFuncionalidade().equals(Funcionalidade.INSERIR_IMOVEL) &&
					!getControladorPermissaoEspecial().verificarPermissaoInserirImovelMunicipioLogradouroDiferenteSetor(usuarioLogado)){
					
					throw new ControladorException(
					"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial");
				}
				else if (helper.getIdFuncionalidade().equals(Funcionalidade.MANTER_IMOVEL) &&
						!getControladorPermissaoEspecial().verificarPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor(usuarioLogado)){
					
					throw new ControladorException(
					"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial");
				}
			}
		}
		
		/*
		 * [FS0034] Validar dados cadastrais do Munic�pio
		 * Autor: Hugo Leonardo
		 * Data: 17/01/2011
		 */
		if (helper.getIdFuncionalidade().equals(Funcionalidade.INSERIR_IMOVEL) && 
			municipio != null && 
			municipio.getIndicadorRelacaoQuadraBairro().compareTo(ConstantesSistema.SIM) == 0){
			
			if( imovelEndereco.getQuadra() != null && imovelEndereco.getQuadra().getBairro() != null &&
			    imovelEndereco.getLogradouroBairro().getBairro() != null && 
			    imovelEndereco.getQuadra().getBairro().getId().intValue() != imovelEndereco.getLogradouroBairro().getBairro().getId().intValue()){
				
				throw new ControladorException(
				"atencao.bairro_associado_quadra.nao.existe_logradourobairro", null, 
				imovelEndereco.getQuadra().getBairro().getNome().toString(), 
				imovelEndereco.getLogradouroBairro().getLogradouro().getNome().toString());
				
			}
			
			if( imovelEndereco.getQuadra() != null && imovelEndereco.getQuadra().getBairro() == null){
				
				throw new ControladorException(
						"atencao.nao.existe.bairro_associado_quadra", null,
						imovelEndereco.getQuadra().getId().toString());
			}
		}
		else if (helper.getIdFuncionalidade().equals(Funcionalidade.MANTER_IMOVEL) && 
				municipio != null && 
				municipio.getIndicadorRelacaoQuadraBairro().compareTo(ConstantesSistema.SIM) == 0){
				
				if( imovelEndereco.getQuadra() != null && !imovelEndereco.getQuadra().getId().equals(idQuadraAnterior) && 
				    imovelEndereco.getQuadra().getBairro() != null &&
				    imovelEndereco.getLogradouroBairro().getBairro() != null && 
				    imovelEndereco.getQuadra().getBairro().getId().intValue() != imovelEndereco.getLogradouroBairro().getBairro().getId().intValue()){
					
					throw new ControladorException(
					"atencao.bairro_associado_quadra.nao.existe_logradourobairro", null, 
					imovelEndereco.getQuadra().getBairro().getNome().toString(), 
					imovelEndereco.getLogradouroBairro().getLogradouro().getNome().toString());
					
				}
				
				/*
				 * [FS0034] Validar dados cadastrais do Munic�pio
				 * 
				 * Comentado por: Hugo Leonardo
				 * Autor: Aryed.
				 * Data: 02/02/2011
				 
				if( imovelEndereco.getLogradouroBairro().getLogradouro().getLogradouroBairros() != null &&
					imovelEndereco.getLogradouroBairro().getLogradouro().getLogradouroBairros().size() > 1 ){
					
					Collection<LogradouroBairro> logradouroBairros = imovelEndereco.getLogradouroBairro().getLogradouro().getLogradouroBairros();
					
					Iterator iteratorLogradouroBairros = logradouroBairros.iterator();
					while (iteratorLogradouroBairros.hasNext()) {
						LogradouroBairro logradouroBairro = (LogradouroBairro) iteratorLogradouroBairros.next();

						if(imovelEndereco.getQuadra() != null && imovelEndereco.getQuadra().getBairro() != null &&
								logradouroBairro.getBairro() != null && 
								imovelEndereco.getQuadra().getBairro().getId().intValue() == logradouroBairro.getBairro().getId().intValue()){
							
							throw new ControladorException(
									"atencao.mais_que_um_bairro_associado_quadra", null, 
									imovelEndereco.getQuadra().getBairro().getNome().toString(), 
									imovelEndereco.getQuadra().getId().toString(),
									logradouroBairro.getLogradouro().getNome().toString());
							
						}
					}
				}
				*/
				
				if( imovelEndereco.getQuadra() != null && imovelEndereco.getQuadra().getBairro() == null){
					
					throw new ControladorException(
							"atencao.nao.existe.bairro_associado_quadra", null,
							imovelEndereco.getQuadra().getId().toString());
				}
			}
		
		ImovelAbaEnderecoRetornoHelper resultado = new ImovelAbaEnderecoRetornoHelper();
		resultado.setMunicipioEnderecoDiferenteMunicipioSetorComercial(
				municipioEnderecoDiferenteMunicipioSetorComercial);
		return resultado;
	}
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * Valida��es do Imovel referente � aba de Clientes
	 * 
	 * @author Victor Cisneiros, Hugo Leonardo
	 * @date 28/01/2009, 19/07/2010
	 * @alteracao CRC4809
	 */
	public void validarImovelAbaCliente(
			Collection<ClienteImovel> imoveisClientes, Usuario usuario) throws ControladorException {
		
		if (imoveisClientes == null || imoveisClientes.isEmpty()) {
			throw new ControladorException("atencao.required", null, "Um Cliente do tipo Us�ario");
		}
		boolean existeClienteUsuario = false;
		boolean existeClienteCpfCnpj = false;
		boolean inserirNovoClienteImovel = false;
		
		// -------------------------------------------------------------------------------------------
		// Alterado por :  Hugo Leonardo - data : 20/07/2010 
		// Analista :  Adriana Ribeiro.
    	// [UC0011] - [FS0033] Verificar CPF de cliente
    	// -------------------------------------------------------------------------------------------

		// Se o usu�rio n�o tenha permiss�o especial.
		boolean temPermissaoParaAssociarClienteAoImovelSemCpf = 
			this.getControladorPermissaoEspecial().verificarPermissaoEspecial(
					PermissaoEspecial.ASSOCIAR_CLIENTE_AO_IMOVEL_SEM_CPF, usuario);		
		
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getIndicadorDocumentoObrigatorio() == ConstantesSistema.NAO.shortValue()){
			temPermissaoParaAssociarClienteAoImovelSemCpf = true;
		}
		
		for (ClienteImovel clienteImovel : imoveisClientes) {
			
			
			if (clienteImovel.getClienteRelacaoTipo() != null) {
				
				String relacaoTipo = clienteImovel.getClienteRelacaoTipo().getId().toString();
				
				if (relacaoTipo.equals(ClienteRelacaoTipo.USUARIO.toString())) {

					existeClienteUsuario = true;
					inserirNovoClienteImovel = verificarAtualizouCliente(clienteImovel);
					
					// se n�o tiver permiss�o especial
					if(!temPermissaoParaAssociarClienteAoImovelSemCpf){
						// verifica se existe cpf ou cnpj associado ao cliente.
						if(clienteImovel.getCliente() != null && 
							( ( clienteImovel.getCliente().getCpf() != null && !clienteImovel.getCliente().getCpf().equals("")) || 
							  (clienteImovel.getCliente().getCnpj() != null && !clienteImovel.getCliente().getCnpj().equals(""))
							) ) {
							
							existeClienteCpfCnpj = true;
						}
					}
				}
			}
		}
		if (!existeClienteUsuario) {
			throw new ControladorException("atencao.required", null, "Um Cliente do tipo Us�ario");
		}
		
		if(!temPermissaoParaAssociarClienteAoImovelSemCpf && !existeClienteCpfCnpj && inserirNovoClienteImovel){
			throw new ControladorException("atencao.imovel_cliente_sem_documento");
		}
	}
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * Valida��es do Imovel referente � aba de Caracteristicas
	 * 
	 * @author Victor Cisneiros, Ivan Sergio
	 * @date 28/01/2009, 23/04/2009
	 * @alteracao 23/04/2009 - CRC1657
	 */
	public ImovelAbaCaracteristicasRetornoHelper validarImovelAbaCaracteristicas(
			ImovelAbaCaracteristicasHelper helper) throws ControladorException {
		
		AreaConstruidaFaixa areaConstruidaFaixa = null;
		ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior = null;
		ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior = null;
		PiscinaVolumeFaixa piscinaVolumeFaixa = null;
		PavimentoCalcada pavimentoCalcada = null;
		PavimentoRua pavimentoRua = null;
		FonteAbastecimento fonteAbastecimento = null;
		LigacaoAguaSituacao ligacaoAguaSituacao = null;
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
		ImovelPerfil imovelPerfil = null;
		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = null;
		
		Imovel imovelAtualizar = helper.getImovelAtualizar();
		String areaConstruida = helper.getAreaConstruida();
		String idAreaConstruidaFaixa = helper.getIdAreaConstruidaFaixa();
		
		String volumeReservatorioInferior = helper.getVolumeReservatorioInferior();
		String volumeReservatorioSuperior = helper.getVolumeReservatorioSuperior();
		String volumePiscinaMovel = helper.getVolumePiscinaMovel();
		
		String idPavimentoCalcada = helper.getIdPavimentoCalcada();
		String idPavimentoRua = helper.getIdPavimentoRua();
		String idFonteAbastecimento = helper.getIdFonteAbastecimento();
		String idLigacaoAguaSituacao = helper.getIdLigacaoAguaSituacao();
		String idLigacaoEsgotoSituacao = helper.getIdLigacaoEsgotoSituacao();
		String idLigacaoEsgotoEsgotamento = helper.getIdLigacaoEsgotoEsgotamento();
		String idImovelPerfil = helper.getIdImovelPerfil();
		
		String idSetorComercial = helper.getIdSetorComercial();
		String idQuadra = helper.getIdQuadra();
		
		// [FS0009] Verificar preenchimento dos campos
		if (areaConstruida != null && !areaConstruida.equals("")) {
			FiltroAreaConstruidaFaixa filtroAreaConstruida = new FiltroAreaConstruidaFaixa();
            filtroAreaConstruida.adicionarParametro(new MenorQue(
                    FiltroAreaConstruidaFaixa.MENOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(areaConstruida)));
            filtroAreaConstruida.adicionarParametro(new MaiorQue(
                    FiltroAreaConstruidaFaixa.MAIOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(areaConstruida)));
            Collection pesquisa = getControladorUtil().pesquisar(
                    filtroAreaConstruida, AreaConstruidaFaixa.class.getName());
            if (pesquisa != null && !pesquisa.isEmpty()) {
				areaConstruidaFaixa = (AreaConstruidaFaixa) Util.retonarObjetoDeColecao(pesquisa);
			} else {
				// throw new ControladorException("atencao.pesquisa_inexistente", null, "�rea Constru�da");
			}
		} else if (Util.verificarIdNaoVazio(idAreaConstruidaFaixa)) {
			Integer id = new Integer(idAreaConstruidaFaixa);
			FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();
			filtroAreaConstruidaFaixa.adicionarParametro(new ParametroSimples(FiltroAreaConstruidaFaixa.CODIGO, id));
			Collection pesquisa = getControladorUtil().pesquisar(
					filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				areaConstruidaFaixa = (AreaConstruidaFaixa) Util.retonarObjetoDeColecao(pesquisa);
			} else {
				// throw new ControladorException("atencao.pesquisa_inexistente", null, "�rea Constru�da");
			}
		} else {
			throw new ControladorException("atencao.required", null, "�rea Constru�da");
		}
		
		if (Util.verificarNaoVazio(volumeReservatorioInferior)) {
			FiltroReservatorioVolumeFaixa filtroReservatorioVolumeFaixaInferior = new FiltroReservatorioVolumeFaixa();
            filtroReservatorioVolumeFaixaInferior.adicionarParametro(new MenorQue(
                    FiltroReservatorioVolumeFaixa.VOLUME_MENOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(volumeReservatorioInferior)));
            filtroReservatorioVolumeFaixaInferior.adicionarParametro(new MaiorQue(
                    FiltroReservatorioVolumeFaixa.VOLUME_MAIOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(volumeReservatorioInferior)));
            Collection pesquisa = getControladorUtil().pesquisar(
                    filtroReservatorioVolumeFaixaInferior, ReservatorioVolumeFaixa.class.getName());
            if (pesquisa != null && !pesquisa.isEmpty()) {
				reservatorioVolumeFaixaInferior = (ReservatorioVolumeFaixa) Util.retonarObjetoDeColecao(pesquisa);
			}
		}
		
		if (Util.verificarNaoVazio(volumeReservatorioSuperior)) {
			FiltroReservatorioVolumeFaixa filtroReservatorioVolumeFaixaSuperior = new FiltroReservatorioVolumeFaixa();
            filtroReservatorioVolumeFaixaSuperior.adicionarParametro(new MenorQue(
                    FiltroReservatorioVolumeFaixa.VOLUME_MENOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(volumeReservatorioSuperior)));
            filtroReservatorioVolumeFaixaSuperior.adicionarParametro(new MaiorQue(
                    FiltroReservatorioVolumeFaixa.VOLUME_MAIOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(volumeReservatorioSuperior)));
            Collection pesquisa = getControladorUtil().pesquisar(
                    filtroReservatorioVolumeFaixaSuperior, ReservatorioVolumeFaixa.class.getName());
            if (pesquisa != null && !pesquisa.isEmpty()) {
				reservatorioVolumeFaixaSuperior = (ReservatorioVolumeFaixa) Util.retonarObjetoDeColecao(pesquisa);
			}
		}
		
		if (Util.verificarNaoVazio(volumePiscinaMovel)) {
			FiltroPiscinaVolumeFaixa filtroPiscinaVolumeFaixa = new FiltroPiscinaVolumeFaixa();
            filtroPiscinaVolumeFaixa.adicionarParametro(new MenorQue(
                    FiltroPiscinaVolumeFaixa.VOLUME_MENOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(volumePiscinaMovel)));
            filtroPiscinaVolumeFaixa.adicionarParametro(new MaiorQue(
                    FiltroPiscinaVolumeFaixa.VOLUME_MAIOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(volumePiscinaMovel)));
            Collection pesquisa = getControladorUtil().pesquisar(
                    filtroPiscinaVolumeFaixa, PiscinaVolumeFaixa.class.getName());
            if (pesquisa != null && !pesquisa.isEmpty()) {
				piscinaVolumeFaixa = (PiscinaVolumeFaixa) Util.retonarObjetoDeColecao(pesquisa);
			}
		}
		
		// [FS0009] Verificar preenchimento dos campos
		if (Util.verificarIdNaoVazio(idPavimentoCalcada)) {
			Integer id = new Integer(idPavimentoCalcada);
			FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
			filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.ID, id));
			Collection pesquisa = getControladorUtil().pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				pavimentoCalcada = (PavimentoCalcada) Util.retonarObjetoDeColecao(pesquisa);
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Pavimento Cal�ada");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Pavimento Cal�ada");
		}
		
		// [FS0009] Verificar preenchimento dos campos
		if (Util.verificarIdNaoVazio(idPavimentoRua)) {
			Integer id = new Integer(idPavimentoRua);
			FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
			filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID, id));
			Collection pesquisa = getControladorUtil().pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				pavimentoRua = (PavimentoRua) Util.retonarObjetoDeColecao(pesquisa);
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Pavimento Rua");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Pavimento Rua");
		}
		
		// [FS0009] Verificar preenchimento dos campos
		if (Util.verificarIdNaoVazio(idFonteAbastecimento)) {
			Integer id = new Integer(idFonteAbastecimento);
			FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, id));
			Collection pesquisa = getControladorUtil().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(pesquisa);
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Fonte de Abastecimento");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Fonte de Abastecimento");
		}
		
		// [FS0009] Verificar preenchimento dos campos
		if (Util.verificarIdNaoVazio(idLigacaoAguaSituacao)) {
			Integer id = new Integer(idLigacaoAguaSituacao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, id));
			Collection pesquisa = getControladorUtil().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(pesquisa);
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Situacao Liga��o �gua");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Situacao Liga��o �gua");
		}
		
		// [FS0009] Verificar preenchimento dos campos
		if (Util.verificarIdNaoVazio(idLigacaoEsgotoSituacao)) {
			Integer id = new Integer(idLigacaoEsgotoSituacao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, id));
			Collection pesquisa = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(pesquisa);
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Situacao Liga��o Esgoto");
			}
		} else {
			throw new ControladorException("atencao.required", null, "Situacao Liga��o Esgoto");
		}
		
		// [FS0009] Verificar preenchimento dos campos
		if (Util.verificarIdNaoVazio(idImovelPerfil)) {
			// [FS0023] - Verificar Setor Comercial e Quadra
			if (idSetorComercial.equals("999") && idQuadra.equals("999") && !idImovelPerfil.equals(ImovelPerfil.CADASTRO_PROVISORIO.toString())) {
				throw new ControladorException("atencao.validar_perfil_cadastro_provisorio", null, "Perfil do Imovel");
			}else {
				Integer id = new Integer(idImovelPerfil);
				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, id));
				Collection pesquisa = getControladorUtil().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ControladorException("atencao.pesquisa_inexistente", null, "Perfil do Imovel");
				}
			}
		} else {
			throw new ControladorException("atencao.required", null, "Perfil do Imovel");
		}
		
		if (imovelAtualizar != null) {
			
			Quadra quadra = imovelAtualizar.getQuadra();
			QuadraFace quadraFace = imovelAtualizar.getQuadraFace();
			
			/*
			 * Integra��o com o conceito de face da quadra
			 * 
			 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da 
			 * tabela SISTEMA_PARAMETROS); os campos de INDICADOR_REDE_AGUA, INDICADOR_REDE_ESGOTO
			 * DISTRITO_OPERACIONAL e BACIA ser�o obtidos a partir da face.
			 */
			IntegracaoQuadraFaceHelper integracaoQuadraFace = null;
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
	    	if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
	    		
	    		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
	    		filtroQuadraFace.adicionarParametro(new ParametroSimples(FiltroQuadraFace.ID, quadraFace.getId()));
	    		
	    		Collection colecaoQuadraFace = getControladorUtil().pesquisar(filtroQuadraFace, QuadraFace.class.getName());
	    		
	    		if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()) {
	    			
	    			QuadraFace quadraFaceNaBase = ((QuadraFace) ((List) colecaoQuadraFace).get(0));
	    			
	    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
	    			
	    			integracaoQuadraFace.setIndicadorRedeAgua(quadraFaceNaBase.getIndicadorRedeAgua());
	    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraFaceNaBase.getIndicadorRedeEsgoto());
	    		}
	    	}
	    	else{
	    		
	    		FiltroQuadra filtroQuadra = new FiltroQuadra();

	    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, quadra.getId()));
	    		
	    		Collection colecaoQuadra = getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
	    		
	    		if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
	    			
	    			Quadra quadraNaBase = ((Quadra) ((List) colecaoQuadra).get(0));
	    			
	    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
	    			
	    			integracaoQuadraFace.setIndicadorRedeAgua(quadraNaBase.getIndicadorRedeAgua());
	    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraNaBase.getIndicadorRedeEsgoto());
	    		}
	    	}
	    	
			/*
			 * Caso n�o seja a situa��o do fluxo
			 * 
			 * [FS008] - VERIFICAR SITUA��O DA LIGA��O AGUA - FACTIVEL e POTENCIAL
			 * 
			 * Verifica a situa��o primeiro do imovel para depois caso n�o satisfa�a
			 * a condi��o faz em rela��o a quadra
			 */
			if (!(imovelAtualizar.getLigacaoAguaSituacao() != null && 
				(imovelAtualizar.getLigacaoAguaSituacao().getId().intValue() != 
				LigacaoAguaSituacao.FACTIVEL.intValue() && 
				imovelAtualizar.getLigacaoAguaSituacao().getId().intValue() != 
				LigacaoAguaSituacao.POTENCIAL.intValue()))) {
				
				
	
		    	//SITUA��O DE LIGA��O DE AGUA
				if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
					integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.SEM_REDE)) {
					
					if (!(ligacaoAguaSituacao.getId().toString()
						.equals(LigacaoAguaSituacao.POTENCIAL.toString()))) {
						
						throw new ControladorException("atencao.imovel.ligacao_agua.incompativel");
					}
				}
				
				if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
					integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.COM_REDE)) {
					
					if (!(ligacaoAguaSituacao.getId().toString()
						.equals(LigacaoAguaSituacao.FACTIVEL.toString()))) {
						
						throw new ControladorException("atencao.imovel.ligacao_agua.incompativel");
					}
				}
				
				if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
					integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.REDE_PARCIAL)) {

					if (!((ligacaoAguaSituacao.getId().toString()
						.equals(LigacaoAguaSituacao.POTENCIAL.toString())) || (ligacaoAguaSituacao
						.getId().toString()
						.equals(LigacaoAguaSituacao.FACTIVEL.toString())))) {
						
						throw new ControladorException("atencao.imovel.ligacao_agua.incompativel");
					}
				}
			}
	
			/*
			 * Caso n�o seja a situa��o do fluxo
			 * 
			 * [FS009] - VERIFICAR SITUA��O DA LIGA��O ESGOTO - FACTIVEL e POTENCIA
			 * 
			 * Verifica a situa��o primeiro do imovel para depois caso n�o satisfa�a a 
			 * condi��o faz em rela��o a quadra
			 */
			if (!(imovelAtualizar.getLigacaoEsgotoSituacao() != null && (imovelAtualizar
				.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.FACTIVEL.intValue() &&
				imovelAtualizar.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.POTENCIAL
				.intValue()))) {
	
				// SITUA��O DE LIGA��O ESGOTO
				if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
					integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.SEM_REDE)) {
						
					if (!(ligacaoEsgotoSituacao.getId().toString()
						.equals(LigacaoEsgotoSituacao.POTENCIAL.toString()))) {
						
						throw new ControladorException(
						"atencao.imovel.ligacao_esgoto.incompativel");
					}
				}
				
				if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
					integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.COM_REDE)) {
					
					if (!(ligacaoEsgotoSituacao.getId().toString()
						.equals(LigacaoEsgotoSituacao.FACTIVEL.toString()))) {
						
						throw new ControladorException(
						"atencao.imovel.ligacao_esgoto.incompativel");
					}
				}
				
				if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
					integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.REDE_PARCIAL)) {
	
					if (!((ligacaoEsgotoSituacao.getId().toString()
						.equals(LigacaoEsgotoSituacao.POTENCIAL.toString())) || (ligacaoEsgotoSituacao
						.getId().toString()
						.equals(LigacaoEsgotoSituacao.FACTIVEL.toString())))) {
						
						throw new ControladorException("atencao.imovel.ligacao_esgoto.incompativel");
					}
				}
			}
		}
		
		// LigacaoEsgotoEsgotamento
		if (ligacaoEsgotoSituacao != null && 
			ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)) {
			if (Util.verificarNaoVazio(idLigacaoEsgotoEsgotamento)) {
				FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
				filtroLigacaoEsgotoEsgotamento.adicionarParametro(
						new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.ID, idLigacaoEsgotoEsgotamento));
				filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
				filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
				
				Collection colecaoLigacaoEsgotoEsgotamento = getControladorUtil().pesquisar(
						filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
				ligacaoEsgotoEsgotamento = (LigacaoEsgotoEsgotamento) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoEsgotamento);
			} else {
				throw new ControladorException("atencao.required", null, "Esgotamento");
			}
		} 
		
		ImovelAbaCaracteristicasRetornoHelper retorno = new ImovelAbaCaracteristicasRetornoHelper();
		retorno.setAreaConstruidaFaixa(areaConstruidaFaixa);
		retorno.setPavimentoCalcada(pavimentoCalcada);
		retorno.setPavimentoRua(pavimentoRua);
		retorno.setFonteAbastecimento(fonteAbastecimento);
		retorno.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		retorno.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
		retorno.setImovelPerfil(imovelPerfil);
		retorno.setLigacaoEsgotoEsgotamento(ligacaoEsgotoEsgotamento);
		retorno.setReservatorioVolumeFaixaInferior(reservatorioVolumeFaixaInferior);
		retorno.setReservatorioVolumeFaixaSuperior(reservatorioVolumeFaixaSuperior);
		retorno.setPiscinaVolumeFaixa(piscinaVolumeFaixa);
		
		return retorno;
	}
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * Valida��es do Imovel referente � aba de Conclusao
	 * 
	 * @author Victor Cisneiros
	 * @date 28/01/2009
	 */
	public ImovelAbaConclusaoRetornoHelper validarImovelAbaConclusao(
			ImovelAbaConclusaoHelper helper) throws ControladorException {
		
		Rota rotaEntrega = null;
		Rota rotaAlternativa = null;
		String resultadoSequencialRotaEntrega = null;
		
		String numeroIptu = helper.getNumeroIptu();
		String numeroContratoCelpe = helper.getNumeroContratoCelpe();
		SetorComercial setorComercial = helper.getSetorComercial();
		String idQuadraImovel = helper.getIdQuadraImovel();
		String idImovelPrincipal = helper.getIdImovelPrincipal();
		String idFuncionario = helper.getIdFuncionario();
		String coordenadasX = helper.getCoordenadasUtmX();
		String coordenadasY = helper.getCoordenadasUtmY();
		String idRotaEntrega = helper.getIdRotaEntrega();
		String sequencialRotaEntrega = helper.getSequencialRotaEntrega();
		String idRotaAlternativa = helper.getIdRotaAlternativa();
		String numeroMedidorEnergia = helper.getNumeroMedidorEnergia();
		Collection<ClienteImovel> imoveisClientes = helper.getImoveisClientes();
		
		
		for (ClienteImovel clienteImovel : imoveisClientes) {
			if (clienteImovel.getClienteRelacaoTipo().getId().toString().equals(ClienteRelacaoTipo.RESPONSAVEL + "")) {
				if (Util.verificarNaoVazio(sequencialRotaEntrega)) {
					resultadoSequencialRotaEntrega = sequencialRotaEntrega;
				}
				if (Util.verificarNaoVazio(idRotaEntrega)) {
					rotaEntrega = new Rota();
					rotaEntrega.setId(new Integer(idRotaEntrega));
				}
			}
		}
		
		// [FS0011] Validar numero do IPTU
		if (Util.verificarNaoVazio(numeroIptu)) {
			Integer idMunicipio = setorComercial.getMunicipio().getId();
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_MUNICIPIO_ID, idMunicipio));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_ID, setorComercial.getId()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IPTU, numeroIptu));
			
			//******************************************************************
			// CRC1574
			// Este parametro so deve ser usado quando for uma atualizacao.
			//******************************************************************
			if (helper.getIdImovelAtualizar() != null)
				filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe (FiltroImovel.ID, helper.getIdImovelAtualizar()));
			//******************************************************************
			
			Collection colecaoImoveis = getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
				throw new ControladorException("atencao.imovel.iptu_jacadastrado", null, imovel.getId().toString());
			}
		}
		
		// [FS0007] Validar numero da CELPE
		if (Util.verificarNaoVazio(numeroContratoCelpe)) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(
					new ParametroSimples(FiltroImovel.NUMERO_CELPE, numeroContratoCelpe));
			
			//******************************************************************
			// CRC1574
			// Este parametro so deve ser usado quando for uma atualizacao.
			//******************************************************************
			if (helper.getIdImovelAtualizar() != null)
				filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe (FiltroImovel.ID, helper.getIdImovelAtualizar()));
			//******************************************************************
			
			Collection colecaoImoveis = getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
				throw new ControladorException("atencao.imovel.numero_celpe_jacadastrado", null, imovel.getId().toString());
			}
		}
		
		// [FS0015] Verificar quadra do imovel principal
		if (Util.verificarIdNaoVazio(idImovelPrincipal)) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovelPrincipal));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
			
			Collection colecaoImoveis = getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				
				
				Imovel imovelPrincipal = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
				
				if(idQuadraImovel != null && !idQuadraImovel.equals("") && setorComercial != null){
					
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, idQuadraImovel));
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
					
					Collection colecaoQuadra = 
						getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
					
					if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
						Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

						if (imovelPrincipal.getQuadra() == null || 
							imovelPrincipal.getQuadra().getId().intValue() != quadra.getId().intValue() ) {
								
							throw new ControladorException("atencao.imovel_principal.inexistente.quadra");
						}

					}				
				}
				
			} else {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Imovel Principal");
			}
		}
		
		// [FS0014] Verificar informacao das coordenadas UTM
		if ((Util.verificarNaoVazio(coordenadasX)) && !Util.verificarNaoVazio(coordenadasY) ||
			(Util.verificarNaoVazio(coordenadasY)) && !Util.verificarNaoVazio(coordenadasX)) {
			throw new ControladorException("atencao.informe.ambas.ou.nenhuma.coordenada");
		}
		
		// [FS0017] Verificar existencia do funcionario
		if (Util.verificarIdNaoVazio(idFuncionario)) {
			Integer id = new Integer(idFuncionario);
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, id));
			Collection pesquisa = getControladorUtil().pesquisar(filtroFuncionario, Funcionario.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente", null, "Funcionario");
			}
		}
		
		// [FS0025] - Validar N�mero do Medidor de Energia
		if (Util.verificarIdNaoVazio(numeroMedidorEnergia)) {
			
			try {
				Integer idImovel = repositorioImovel.pesquisarImovelComNumeroMedidorEnergiaImovel(numeroMedidorEnergia);
			
				if(idImovel != null
						&& !idImovel.equals(helper.getIdImovelAtualizar())) {
					throw new ControladorException("atencao.medidor_energia_ja_existente", null, idImovel.toString());
				}
			
			} catch (ErroRepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ImovelAbaConclusaoRetornoHelper resultado = new ImovelAbaConclusaoRetornoHelper();
		resultado.setRotaEntrega(rotaEntrega);
		resultado.setSequencialRotaEntrega(resultadoSequencialRotaEntrega);
		
		if (Util.verificarNaoVazio(idRotaAlternativa)) {
			rotaAlternativa = new Rota();
			rotaAlternativa.setId(new Integer(idRotaAlternativa));
		}
		resultado.setRotaAlternativa(rotaAlternativa);
		
		return resultado;
	}
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * @author Victor Cisneiros
	 * @date 18/02/2009
	 */
    public Integer pesquisarMaiorNumeroLoteDaQuadra(Integer idQuadra) throws ControladorException {
    	try {
    		return repositorioImovel.pesquisarMaiorNumeroLoteDaQuadra(idQuadra);
        } catch (ErroRepositorioException ex) {
        	sessionContext.setRollbackOnly();
            throw new ControladorException( ex.getMessage(), ex );
        }
    }

    /**
	 * Consultar os dodos cliente usu�rio do Imovel 
	 * 
	 * dados[0] = Nome
	 * dados[1] = Cpf
	 * dados[2] = Cnpj
	 * 
	 * @author Arthur Carvalho
	 * @date 12/03/2009
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
    public Object[] consultarDadosClienteUsuarioImovel(
			String idImovel) throws ControladorException {
		
    	Object[] dados = null;

		try {

			dados = this.repositorioImovel
					.consultarDadosClienteUsuarioImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return dados;
	}

    
    /**
	 * Gerar Relat�rio de Im�veis Outros Crit�rios 
	 * 
	 * @author R�mulo Aurelio
	 * @date 25/07/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return qtde
	 * @throws ControladorException
	 */
	public Integer gerarRelatorioCadastroConsumidoresInscricaoCount(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal, String[] pocoTipoListIds) throws ControladorException {

		Integer qtde = null;

		try {
			// remove primeiro as linhas do crit�rio cobran�a
			qtde = repositorioImovel
					.gerarRelatorioCadastroConsumidoresInscricaoCount(
							idImovelCondominio, idImovelPrincipal,
							idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
							consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,

							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,

							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa, idUnidadeNegocio, cdRotaInicial,
							cdRotaFinal, sequencialRotaInicial,
							sequencialRotaFinal, pocoTipoListIds);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return qtde;
	}
	
	/**
	 * Gerar Relat�rio de Im�veis Outros Crit�rios 
	 * 
	 * @author R�mulo Aurelio
	 * @date 18/03/2009
	 * 
	 * @return qtde
	 * @throws ControladorException
	 */
	public Integer gerarRelatorioImovelEnderecoOutrosCriteriosCount(
							String idImovelCondominio, 
							String idImovelPrincipal,
							String idSituacaoLigacaoAgua, 
							String consumoMinimoInicialAgua,
							String consumoMinimoFinalAgua, 
							String idSituacaoLigacaoEsgoto,
							String consumoMinimoInicialEsgoto, 
							String consumoMinimoFinalEsgoto,
							String intervaloValorPercentualEsgotoInicial,
							String intervaloValorPercentualEsgotoFinal,
							String intervaloMediaMinimaImovelInicial,
							String intervaloMediaMinimaImovelFinal,
							String intervaloMediaMinimaHidrometroInicial,
							String intervaloMediaMinimaHidrometroFinal, 
							String idImovelPerfil,
							String idPocoTipo, 
							String idFaturamentoSituacaoTipo,
							String idCobrancaSituacaoTipo, 
							String idSituacaoEspecialCobranca,
							String idEloAnormalidade, 
							String areaConstruidaInicial,
							String areaConstruidaFinal, 
							String idCadastroOcorrencia,
							String idConsumoTarifa, 
							String idGerenciaRegional,
							String idLocalidadeInicial, 
							String idLocalidadeFinal,
							String setorComercialInicial, 
							String setorComercialFinal,
							String quadraInicial, 
							String quadraFinal, 
							String loteOrigem,
							String loteDestno, 
							String cep, 
							String logradouro, 
							String bairro,
							String municipio, 
							String idTipoMedicao, 
							String indicadorMedicao,
							String idSubCategoria, 
							String idCategoria,
							String quantidadeEconomiasInicial, 
							String quantidadeEconomiasFinal,
							String diaVencimento, 
							String idCliente, 
							String idClienteTipo,
							String idClienteRelacaoTipo, 
							String numeroPontosInicial,
							String numeroPontosFinal, 
							String numeroMoradoresInicial,
							String numeroMoradoresFinal, 
							String idAreaConstruidaFaixa,
							String idUnidadeNegocio, 
							String rotaInicial, 
							String rotaFinal,
							String ordenacaoRelatorio,
							String seqRotaInicial, 
							String seqRotaFinal) throws ControladorException {

		Integer qtde = null;

		try {
			// remove primeiro as linhas do crit�rio cobran�a
			qtde = repositorioImovel
			.gerarRelatorioImovelEnderecoOutrosCriteriosCount(
					idImovelCondominio, 
					idImovelPrincipal,
					idSituacaoLigacaoAgua, 
					consumoMinimoInicialAgua,
					consumoMinimoFinalAgua, 
					idSituacaoLigacaoEsgoto,
					consumoMinimoInicialEsgoto, 
					consumoMinimoFinalEsgoto,
					intervaloValorPercentualEsgotoInicial,
					intervaloValorPercentualEsgotoFinal,
					intervaloMediaMinimaImovelInicial,
					intervaloMediaMinimaImovelFinal,
					intervaloMediaMinimaHidrometroInicial,
					intervaloMediaMinimaHidrometroFinal, 
					idImovelPerfil,
					idPocoTipo, 
					idFaturamentoSituacaoTipo,
					idCobrancaSituacaoTipo, 
					idSituacaoEspecialCobranca,
					idEloAnormalidade, 
					areaConstruidaInicial,
					areaConstruidaFinal, 
					idCadastroOcorrencia,
					idConsumoTarifa, 
					idGerenciaRegional,
					idLocalidadeInicial, 
					idLocalidadeFinal,
					setorComercialInicial, 
					setorComercialFinal,
					quadraInicial, 
					quadraFinal, 
					loteOrigem,
					loteDestno, 
					cep, 
					logradouro, 
					bairro,
					municipio, 
					idTipoMedicao, 
					indicadorMedicao,
					idSubCategoria, 
					idCategoria,
					quantidadeEconomiasInicial, 
					quantidadeEconomiasFinal,
					diaVencimento, 
					idCliente, 
					idClienteTipo,
					idClienteRelacaoTipo, 
					numeroPontosInicial,
					numeroPontosFinal, 
					numeroMoradoresInicial,
					numeroMoradoresFinal, 
					idAreaConstruidaFaixa,
					idUnidadeNegocio,
					rotaInicial, 
					rotaFinal,
					ordenacaoRelatorio,
					seqRotaInicial, 
					seqRotaFinal
					);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return qtde;
	}
    
    /**
	 * 
	 * Atualiza a situa��o de cobran�a e a situa��o do tipo de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/02/2009
	 */

	public void atualizarSituacaoCobrancaETipoIdsImoveis(Integer idSituacaoCobranca,Integer idSituacaoCobrancaTipo, Collection<Integer> idsImovel)
		throws ControladorException {
	    	try {
	    		repositorioImovel.atualizarSituacaoCobrancaETipoIdsImoveis(idSituacaoCobranca,idSituacaoCobrancaTipo, idsImovel);
	        } catch (ErroRepositorioException ex) {
	        	sessionContext.setRollbackOnly();
	            throw new ControladorException( ex.getMessage(), ex );
	        }
	}
	
	/**
	 * Verificar se o im�vel perfil est� bloqueado
	 * 
	 * @author Ana Maria
	 * @date 22/04/2009
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarImovelPerfilBloqueado(Integer idImovel) 
	throws ControladorException {
    	try {
    		return repositorioImovel.verificarImovelPerfilBloqueado(idImovel);
        } catch (ErroRepositorioException ex) {
        	sessionContext.setRollbackOnly();
            throw new ControladorException( ex.getMessage(), ex );
        }
	}
	
	/**
	 * 
	 * Autor: S�vio Luiz
	 * Data: 07/05/2009
	 * 
	 * Pesquisa o Fator de Economia
	 * 
	 * 
	 */
	public Short pesquisarFatorEconomiasCategoria(Integer idCategoria)throws ControladorException {
    	try {
    		return repositorioCategoria.pesquisarFatorEconomiasCategoria(idCategoria);
        } catch (ErroRepositorioException ex) {
        	sessionContext.setRollbackOnly();
            throw new ControladorException( ex.getMessage(), ex );
        }
	}
	
	/**
	 * Verificar se a ultima alteracao do im�vel 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 27/05/2009
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaAlteracaoImovel(Integer idImovel) 
			throws ControladorException {
		
		try {
    		return repositorioImovel.pesquisarUltimaAlteracaoImovel(idImovel);
        } catch (ErroRepositorioException ex) {
        	sessionContext.setRollbackOnly();
            throw new ControladorException( ex.getMessage(), ex );
        }
		
		
	}
	
	/**
	 * Pesquisa a cole��o de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCategoriaSubcategoriaImovel(Integer idImovel)
			throws ControladorException {

		Collection dadosCategoria = null;

		try {

			dadosCategoria = this.repositorioImovel
					.pesquisarCategoriaSubcategoriaImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return dadosCategoria;

	}
	
	/**
	 * 
	 * Pesquisa qual foi o consumo faturado do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 18/12/2009
	 * @return consumoFaturadoMes
	 * @throws ControladorException
	 */
	public Integer obterConsumoFaturadoImovelNoMes(Integer idImovel, Integer mesAnoReferencia) throws ControladorException{
		
		Integer consumoFaturadoMes = null;

		try {

			consumoFaturadoMes = this.repositorioImovel
					.obterConsumoFaturadoImovelNoMes(idImovel,mesAnoReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return consumoFaturadoMes;
		
	}
	
	 /** 
	 * Pesquisa o perfil do imovel do imovel informado
	 * 
	 * @author R�mulo Aur�lio
	 * @date 03/03/2010
	 * @throws ControladorException
	 */
	public ImovelPerfil recuperaPerfilImovel(Integer idImovel)  throws ControladorException{
		
		ImovelPerfil imovelPerfil = null;

		try {

			imovelPerfil = this.repositorioImovel
					.recuperaPerfilImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return imovelPerfil;
		
	}
	
	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	
	public void atualizarDataRetiradaImovelSituacaoCobranca(
			Integer idImovelSituacaoCobranca, Date dataRetirada) throws ControladorException {
		
		try {
    		 repositorioImovel.atualizarDataRetiradaImovelSituacaoCobranca(idImovelSituacaoCobranca, dataRetirada);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException( ex.getMessage(), ex );
        }
	}
	
	/**
	 * [UC0672] - Registrar Movimento de Retorno dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 12/03/2010
	 */
	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobrancaNova, 
			Integer idSituacaoCobrancaBanco, Integer idImovel) throws ControladorException {
		
		try {
    		 repositorioImovel.atualizarSituacaoCobrancaImovel(
    		    idSituacaoCobrancaNova,idSituacaoCobrancaBanco,idImovel);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException( ex.getMessage(), ex );
        }
	}
	
	/** 
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Atualizar N�mero Medidor de Energia do Im�vel.
	 * 
	 * @author Hugo Leonardo
	 * @date 15/03/2010
	 *
	 */
	public void atualizarNumeroMedidorEnergiaImovel(Integer matricula, String medidorEnergia) 
		throws ControladorException {
    	try {
    		repositorioImovel.atualizarNumeroMedidorEnergiaImovel(matricula,medidorEnergia);
        } catch (ErroRepositorioException ex) {
        	sessionContext.setRollbackOnly();
            throw new ControladorException( ex.getMessage(), ex );
        }
	}
	
	/** 
	 * Verifica se ja Existe dado na tabela cliente Imovel.
	 * 
	 * @author Arthur Carvalho
	 * @date 19/03/2010
	 * @throws ControladorException
	 */
	public void verificaRestricaoDaTabelaClienteImovel(ClienteImovel clienteImovelRemovido)
		throws ControladorException {
		
		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();

		//	Pesquisa que carrega o objeto Imovel.
		FiltroClienteImovel filtro = new FiltroClienteImovel();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
		
		//Verifica a Restri��o da base -- UNIQUE INDEX xak1_cliente_imovel --
		//clie_id
		if ( clienteImovelRemovido.getCliente().getId() != null ) {
			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.CLIENTE_ID, 
					clienteImovelRemovido.getCliente().getId() ) );
		}
		//imov_id
		if (clienteImovelRemovido.getImovel().getId() != null ) {
			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.IMOVEL_ID, 
					clienteImovelRemovido.getImovel().getId() ) );
		}
		//crtp_id
		if ( clienteImovelRemovido.getClienteRelacaoTipo().getId() != null ) {
			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 
					clienteImovelRemovido.getClienteRelacaoTipo().getId() ) );
		}
		//clim_dtrelacaoinicio
		if ( clienteImovelRemovido.getDataInicioRelacao() != null ) {
			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.DATA_INICIO_RELACAO, 
					clienteImovelRemovido.getDataInicioRelacao() ) );
		}
		//clim_dtrelacaofim
		if ( clienteImovelRemovido.getDataFimRelacao() != null ) {
			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.DATA_FIM_RELACAO, 
					clienteImovelRemovido.getDataFimRelacao() ) );
		} else {
			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.DATA_FIM_RELACAO, 
					new Date() ) );
		}
		
		Collection retornoClienteImovel = fachada.
							pesquisar(filtro, ClienteImovel.class.getName());
		
		if ( retornoClienteImovel != null && !retornoClienteImovel.equals("") && retornoClienteImovel.size() != 0 ) {
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(retornoClienteImovel);
			if (clienteImovel.getDataFimRelacao() != null ) {
				throw new ControladorException("atencao.dependencias.existentes");
			}
		}

	}
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que n�o estejam com a situa��o
	 * atual igual a "PR�-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelCondominioIdComContaNaoPreFaturada(Integer imovelId,
			Integer anoMesReferencia) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImovelCondominioIdComContaNaoPreFaturada(imovelId,
					anoMesReferencia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	/**  
	 * [UC0591] - Gerar Relat�rio de Clientes Especiais
	 * 
	 * 					Count
	 * 
	 * @author Hugo Amorim
	 * @date 11/05/2010
	 */
	public Integer pesquisarImovelClientesEspeciaisRelatorioCount(
			String idUnidadeNegocio, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorInicial, String codigoSetorFinal,
			String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria,
			String[] idsSubcategoria, String idSituacaoAgua,
			String idSituacaoEsgoto, String qtdeEconomiasInicial,
			String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,
			String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial,
			String intervaloConsumoEsgotoFinal, String idClienteResponsavel,
			String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal,
			Date dataInstalacaoHidrometroInicial,
			Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,
			Integer anoMesFaturamento, String idLeituraAnormalidade,
			String leituraAnormalidade, String idConsumoAnormalidade,
			String consumoAnormalidade)	throws ControladorException{
		
		
		try {
			return repositorioImovel
					.pesquisarImovelClientesEspeciaisRelatorioCount(
							idUnidadeNegocio, idGerenciaRegional,
							idLocalidadeInicial, idLocalidadeFinal,
							codigoSetorInicial, codigoSetorFinal,
							codigoRotaInicial, codigoRotaFinal,
							idsPerfilImovel, idsCategoria, idsSubcategoria,
							idSituacaoAgua, idSituacaoEsgoto,
							qtdeEconomiasInicial, qtdeEconomiasFinal,
							intervaloConsumoAguaInicial,
							intervaloConsumoAguaFinal,
							intervaloConsumoEsgotoInicial,
							intervaloConsumoEsgotoFinal, idClienteResponsavel,
							intervaloConsumoResponsavelInicial,
							intervaloConsumoResponsavelFinal,
							dataInstalacaoHidrometroInicial,
							dataInstalacaoHidrometroFinal,
							idsCapacidadesHidrometro, idsTarifasConsumo,
							anoMesFaturamento, idLeituraAnormalidade,
							leituraAnormalidade, idConsumoAnormalidade,
							consumoAnormalidade);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		
	}
	
	/**
	 * [UC0490] Informar Situa��o de Cobran�a
	 * [SB0004] � Selecionar Situa��es de Cobran�a
	 * 
	 * seleciona as situa��es de cobran�a 
	 * (a partir da tabela COBRAN�A_SITUACAO com CBST_ICUSO=1 
	 * e CBST_ICBLOQUEIOINCLUSAO=2)retirando as ocorr�ncias 
	 * com CBST_ID=CBST_ID da tabela IMOVEL_COBRANCA_SITUACAO 
	 * para IMOV_ID=Id do im�vel recebido e 
	 * ISCB_DTRETIRADACOBRANCA com valor igual a nulo
	 * 
	 * @author Vivianne Sousa
	 * @date 12/05/2010
	 */
	public Collection pesquisarCobrancaSituacao(
			Integer idImovel, boolean temPermissaoEspecial) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarCobrancaSituacao(idImovel, temPermissaoEspecial);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * 
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * 
	 * id localidade e codigo do setor
	 * 
	 *  Object[0] = idLocalidade
	 *  Object[1] = codigoSetor
	 * 
	 * @author Hugo Amorim
	 * @date 01/06/2010
	 */
	public Object[] pesquisarLocalidadeCodigoSetorImovel(Integer idImovel)
		throws ControladorException {
		
    	Object[] dados = null;
		
		try {

			dados = this.repositorioImovel
					.pesquisarLocalidadeCodigoSetorImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return dados;
	}
	
	/**
	 * Inserir e Atualizar Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2010
	 *
	 * @param idImovel
	 * @throws ControladorException
	 */
	public void verificarIndicadorNomeConta(Integer idImovel) throws ControladorException {
		
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		
		//IMOVEL
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
		
		//DATA FIM DE RELA��O COM NULL
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		
		Collection colecaoClienteImovel = this.getControladorUtil().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
			
			boolean nomeContaInformado = false;
			
			Iterator iterator = colecaoClienteImovel.iterator();
			
			while(iterator.hasNext()){
				
				ClienteImovel clienteImovel = (ClienteImovel) iterator.next(); 
			
				if (clienteImovel.getIndicadorNomeConta() != null &&
					clienteImovel.getIndicadorNomeConta().equals(ConstantesSistema.SIM)){
					
					nomeContaInformado = true;
					break;
				}
			}
			
			if (!nomeContaInformado){
				
				//ATUALIZANDO O CLIENTE DO TIPO USU�RIO PARA QUE SEU NOME SEJA COLOCADO NA CONTA
				try {
					
					this.repositorioImovel.atualizarClienteImovelUsuario(idImovel);
				} 
				catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
			}
		}
	}
	
	/**
	 * [UC0014] Manter Im�vel
	 * 
	 * [FS0037] Verificar Im�vel em Processo de Faturamento
	 * 
	 * @author Hugo Amorim
	 * @param idImovel
	 * @param anoMesFaturamento
	 * 
	 * @throws ControladorException
	 */
	public boolean verificarImovelEmProcessoDeFaturamento(
			Integer idImovel)throws ControladorException {	
		try {

			return repositorioFaturamento.verificarImovelEmProcessoDeFaturamento(
					idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}	
	}

	
	/**
 	 * @author Daniel Alves
	 * @date 20/07/2010
	 * @param idImovelPerfil
	 * @return ImovelPerfil
	 * @throws ErroRepositorioException
	 */
	public ImovelPerfil pesquisarImovelPerfil(Integer idImovelPerfil) throws ControladorException {	
		try {

			return repositorioImovel.pesquisarImovelPerfil(
					idImovelPerfil);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}	
	}
	
	
	/**
	 * @author Daniel Alves
	 * @date 28/07/2010
	 * @param idImovel
	 * @return Colecao de imovelSubcategoriaHelper
	 * @throws ControladorException
	 */
	public Collection<ImovelSubcategoriaHelper> consultaSubcategorias(int idImovel)throws ControladorException{
		try {
	
			return repositorioImovel.consultaSubcategorias(idImovel);
	
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}	
	}

		/**
	 * [UC0014] Manter Im�vel
	 * 
	 * [FS0037] Verificar Im�vel em Processo de Faturamento
	 * 
	 * @author Hugo Leonardo
	 * @param Collection
	 * @param anoMesFaturamento
	 * 
	 * @throws ControladorException
	 */
	public boolean verificarAtualizouCliente(ClienteImovel clienteImovel)throws ControladorException {	
		
		boolean retorno = true;
		
		Integer cont = 0;
		
		try {
			
			
			if(clienteImovel.getImovel() != null && clienteImovel.getCliente() != null){
				Integer idImovel = clienteImovel.getImovel().getId();
				Integer idCliente = clienteImovel.getCliente().getId();

				cont = repositorioFaturamento.pesquisarExisteClienteAssociadoAoImovelEmClienteImovel(
						idImovel, idCliente);
				
				if(cont > 0){
					retorno = false;
				}
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}	
		
		return retorno;
	}
	
	/**
	 *  @author Hugo Leonardo
	 *  @date 21/09/2010
	 *  
	 * 	UC_0009 - Manter Cliente
	 *  	[FS0008] ? Verificar permiss�o especial para cliente de im�vel p�blico
	 *  
	 * 	Verifica se o Cliente possui algum im�vel, cujo o tipo da categoria 
	 *  em subcategoria seja igual a PUBLICO.
	 *  
	 * 	Caso o cliente possua algum im�vel, retornar a quantidade de imoveis nesta situa��o
	 * 	Caso contr�rio retorna zero. 
	 * 
	 *  @param idCliente
	 *  @return Boolean
	 *  @throws ControladorException
	 */
	public Boolean pesquisarExistenciaImovelPublico(Integer idCliente) throws ControladorException{
		
		boolean retorno = false;
		Integer cont = 0;
		
		try {

			cont = this.repositorioImovel.obterQuantidadeImoveisPublicosAssociadoAoCliente(idCliente);
			
			if(cont > 0){
				retorno = true;
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
		
	}
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @param idQuadraAnterior
	 * @param idQuadraAtual
	 * @return
	 * @throws ControladorException
	 */
	public FaturamentoGrupo[] verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idQuadraAnterior,
			Integer idQuadraAtual) throws ControladorException {
		
		FaturamentoGrupo[] faturamentoGrupos = new FaturamentoGrupo[1];
		
		try {
			
			faturamentoGrupos = repositorioImovel.verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(idQuadraAnterior, idQuadraAtual );
			
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return faturamentoGrupos; 
		
	}
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 19/09/2010
	 * @param faturamentoGrupo
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificaGeracaoDadosLeituraGrupoFaturamento(FaturamentoGrupo faturamentoGrupo) throws ControladorException {
		
		boolean retorno = false;
		try {
			
			retorno = repositorioImovel.verificaGeracaoDadosLeituraGrupoFaturamento( faturamentoGrupo );
			
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno; 
	}

	/**
	 * [UC0074] Alterar Inscri��o de Im�vel
	 * [FS0010] � Verificar Duplicidade de Inscri��o
	 * @author Arthur Carvalho
	 * @date 19/09/2010
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param idQuadra
	 * @param idQuadraFace
	 * @param subLote
	 * @param lote
	 * @return
	 * @throws ControladorException
	 */
	public ImovelInscricaoAlterada verificarDuplicidadeImovelInscricaoAlterada(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idQuadraFace, Integer subLote, Integer lote) throws ControladorException {
		
		try {
			
			return repositorioImovel.verificarDuplicidadeImovelInscricaoAlterada( idLocalidade,  idSetorComercial,  idQuadra,
					 idQuadraFace,  subLote,  lote );
			
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * [UC0995] Emitir Declara��o Transfer�ncia de D�bitos/Cr�ditos
	 * @author Daniel Alves
	 * @date 23/09/2010
	 * @return Municipio
	 */
	public Municipio pesquisarMunicipioImovel(Integer idImovel) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarMunicipioImovel(idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * 
	 * [UC0630] Solicitar Emiss�o do Extrato de D�bitos
	 * 
	 * [SB0001] � Calcular valor dos descontos pagamento � vista.
	 * 
	 * @author Vivianne Sousa
	 * @date 21/10/2010
	 * 
	 * @throws ControladorException
	 */
	public Short consultarNumeroReparcelamentoConsecutivosImovel(Integer idImovel)
	throws ControladorException {
		
		try {
			return repositorioImovel.consultarNumeroReparcelamentoConsecutivosImovel(idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * TODO : COSANPA
	 * 
	 * Metodo para verificar se a rota que o imovel pertence
	 * ja foi gerada para o mes de faturamento do grupo 
	 * 
	 * @author Pamela Gatinho
	 * @date 01/08/2011
	 * @return boolean
	 */
	public boolean verificaGeracaoDadosLeituraRota(FaturamentoGrupo faturamentoGrupo, Rota rota) throws ControladorException {
		
		boolean retorno = false;
		try {
			
			retorno = repositorioImovel.verificaGeracaoDadosLeituraRota( faturamentoGrupo, rota );
			
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno; 
	}
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 06/12/2011
	 * 
	 * Pesquisa o perfil do imovel pelo id do im�vel
	 * 
	 * @param idImovelPerfil
	 * @return
	 * @throws ErroRepositorioException
	 * @throws ControladorException 
	 */
	public ImovelPerfil  pesquisarImovelPerfilIdImovel(
			Integer idImovel) throws ControladorException {
		try {

			return repositorioImovel.pesquisarImovelPerfilIdImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}	
	}
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho e Adriana Muniz
	 * 21/08/2012
	 * 
	 * M�todo que verifica se um determinado im�vel
	 * possui faturamento ativo de �gua
	 * 
	 * @param imovel
	 * @return
	 */
	public boolean isFaturamentoAguaAtivo(Imovel imovel) {
		return (imovel.getLigacaoAguaSituacao() != null
				&& (LigacaoAguaSituacao.FATURAMENTO_ATIVO
						.equals(imovel.getLigacaoAguaSituacao()
								.getIndicadorFaturamentoSituacao())));
	}
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho e Adriana Muniz
	 * 21/08/2012
	 * 
	 * M�todo que verifica se um determinado im�vel
	 * possui faturamento ativo de esgoto
	 * 
	 * @param imovel
	 * @return
	 */
	public boolean isFaturamentoEsgotoAtivo(Imovel imovel) {
		return (imovel.getLigacaoEsgotoSituacao() != null
				&& (LigacaoEsgotoSituacao.FATURAMENTO_ATIVO
						.equals(imovel.getLigacaoEsgotoSituacao()
								.getIndicadorFaturamentoSituacao())));
	}
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho e Adriana Muniz
	 * 21/08/2012
	 * 
	 * M�todo que retorna a colecao de categoria ou subcategoria,
	 * e suas respectivas quantidade de economias, verificando se a 
	 * empresa fatura por CATEGORIA ou SUBCATEGORIA.
	 * 
	 * @param imovel
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoCategoriaOuSubcategoriaDoImovel(Imovel imovel) throws ControladorException {
		
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		Collection colecaoCategoriaOUSubcategoria = null;

		// Verificando se a empresa fatura por CATEGORIA ou SUBCATEGORIA
		if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
			
			colecaoCategoriaOUSubcategoria = this.obterQuantidadeEconomiasCategoria(imovel);
		} else {

			// [UC0108] - Obter Quantidade de Economias por Subcategoria
			colecaoCategoriaOUSubcategoria = this.obterQuantidadeEconomiasSubCategoria(imovel.getId());
		}
		return colecaoCategoriaOUSubcategoria;
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovel(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio)throws ControladorException {
		
		try {
			return repositorioImovel.consultarImovel(idLocalidade, idGerenciaRegional,
					 idUnidadeNegocio);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovelCadastro(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer anoMesPesquisaInicial,Integer anoMesPesquisaFinal)throws ControladorException {
		
		try {
			return repositorioImovel.consultarImovelCadastro(idLocalidade, idGerenciaRegional,
					 idUnidadeNegocio,anoMesPesquisaInicial, anoMesPesquisaFinal);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel)throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarImovelEconomia(idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocialDadoEconomia(Integer idImovel)throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarTarifaSocialDadoEconomia(idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialCarta pesquisarTarifaSocialCarta(Integer idImovel,Integer codigoTipoCarta)throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarTarifaSocialCarta(idImovel,codigoTipoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQuantidadeImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarQuantidadeImoveisTarifaSocialCarta(idTarifaSocialComandoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta, Integer qtdeImoveis)throws ControladorException {
		
		try {
			repositorioImovel.atualizarTarifaSocialComandoCarta(idTarifaSocialComandoCarta, qtdeImoveis);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialCarta(Integer idTarifaSocialComandoCarta)  throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarTarifaSocialCarta(idTarifaSocialComandoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal pesquisarValorContaTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarValorContaTarifaSocialCartaDebito(idTarifaSocialComandoCarta,idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao,Integer numeroPagina) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarTarifaSocialComandoCarta(codigoTipoCarta, situacao, numeroPagina);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao)throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarQtdeTarifaSocialComandoCarta(codigoTipoCarta, situacao);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado
	 *  
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerComando(Integer idTarifaSocialComandoCarta)throws ControladorException {
		
		try {
			repositorioImovel.removerComando(idTarifaSocialComandoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta,Integer codigoTipoCarta) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarImoveisTarifaSocialCarta(idTarifaSocialComandoCarta, codigoTipoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado 
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarContasTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarContasTarifaSocialCartaDebito(idTarifaSocialComandoCarta, idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0004]�Retirar Im�vel tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer motivoExclusao, Imovel imovel,String observacaoRetira)throws ControladorException {
		
		try {
			repositorioImovel.retirarImovelTarifaSocial(motivoExclusao, imovel.getId());
			
			
			if(imovel.getFaturamentoSituacaoTipo() != null){
				//Caso a situa��o de faturamento(FTST_ID) do im�vel na tabela IMOVEL diferente de NULO
				Integer referenciaFaturamentoGrupo = getControladorFaturamento().retornaAnoMesFaturamentoGrupo(imovel.getId());
				
				repositorioImovel.atualizarDadosFaturamentoSituacaoHistorico(
					imovel.getId(),referenciaFaturamentoGrupo,observacaoRetira);
				
			}
			
			Integer referencia = getControladorFaturamento().pesquisarAnoMesReferenciaFaturamentoGrupo(imovel.getId());
			
			//O sistema atualiza o perfil, situa��o de faturamento e consumo tarifa do im�vel na tabela CADASTRO.IMOVEL
			repositorioImovel.atualizarDadosImovel(imovel.getId(),referencia);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDataExecucaoTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta)throws ControladorException {
		
		try {
			repositorioImovel.atualizarDataExecucaoTarifaSocialComandoCarta(idTarifaSocialComandoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocial(Integer idLocalidade) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarImoveisTarifaSocial(idLocalidade);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 05/04/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialComandoCarta pesquisarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarTarifaSocialComandoCarta(idTarifaSocialComandoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeImoveisExcluidostarifaSocial(
			Integer codigoTipoCarta,Integer idGerencia,Integer idUnidade,Integer idLocalidade, 
			Integer referenciaInicial, Integer refereciaFinal) throws ControladorException {
		
		try {
			Collection retorno = null;
			Collection colecaoQtdeImoveis = repositorioImovel.pesquisarQtdeImoveisExcluidostarifaSocial(
					codigoTipoCarta, idGerencia, idUnidade, idLocalidade,  referenciaInicial,  refereciaFinal);
			
			if(colecaoQtdeImoveis != null && !colecaoQtdeImoveis.isEmpty()){
				retorno = new ArrayList();
				Iterator iterColecaoQtdeImoveis = colecaoQtdeImoveis.iterator(); 
				
				while (iterColecaoQtdeImoveis.hasNext()) {
					Object[] dados = (Object[]) iterColecaoQtdeImoveis.next();
					RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper helper = new RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper();
					
					if(dados[0] != null){
						helper.setQtdeCartas((Integer)dados[0]);
					}
					if(dados[1] != null){
						helper.setIdGerencia((Integer)dados[1]);
						helper.setDescricaoGerencia((String)dados[5]);
					}
					if(dados[2] != null){
						helper.setIdUnidadeNegocio((Integer)dados[2]);
						helper.setDescricaoUnidadeNegocio((String)dados[6]);
					}
					if(dados[3] != null){
						helper.setIdLocalidade((Integer)dados[3]);
						helper.setDescricaoLocalidade((String)dados[7]);
					}
					if(dados[4] != null){
						helper.setMotivoExclusao((Integer)dados[4]);
						helper.setDescricaoMotivoExclusao(pesquisarDescricaoMotivoCarta((Integer)dados[4]));
						
					}

					Integer qtdeImoveis =  repositorioImovel.pesquisarQtdeImoveisExcluidostarifaSocial(
							referenciaInicial, refereciaFinal,codigoTipoCarta,helper);
					helper.setQtdeExcluidos(qtdeImoveis);
					
					retorno.add(helper);
					
				}
			}
			
			return retorno;
			 
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoMotivoCarta(Integer idCodigoMotivo)  throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarDescricaoMotivoCarta(idCodigoMotivo);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeTarifaSocialDadoEconomia(Integer idtarifaSocialExclusaoMotivo,
			Integer referenciaInicial, Integer refereciaFinal,Integer idGerencia,Integer idUnidade,
			Integer idLocalidade)  throws ControladorException {
		
		try {
			Collection retorno = null;
			Collection colecaoQtdeImoveis = repositorioImovel.pesquisarQtdeTarifaSocialDadoEconomia(
				idtarifaSocialExclusaoMotivo,referenciaInicial,refereciaFinal,idGerencia,idUnidade,idLocalidade);
			
			if(colecaoQtdeImoveis != null && !colecaoQtdeImoveis.isEmpty()){
				retorno = new ArrayList();
				Iterator iterColecaoQtdeImoveis = colecaoQtdeImoveis.iterator(); 
				
				while (iterColecaoQtdeImoveis.hasNext()) {
					Object[] dados = (Object[]) iterColecaoQtdeImoveis.next();
					RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper helper = new RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper();
					
					if(dados[0] != null){
						helper.setQtdeExcluidos((Integer)dados[0]);
					}
					if(dados[1] != null){
						helper.setIdGerencia((Integer)dados[1]);
						helper.setDescricaoGerencia((String)dados[4]);
					}
					if(dados[2] != null){
						helper.setIdUnidadeNegocio((Integer)dados[2]);
						helper.setDescricaoUnidadeNegocio((String)dados[5]);
					}
					if(dados[3] != null){
						helper.setIdLocalidade((Integer)dados[3]);
						helper.setDescricaoLocalidade((String)dados[6]);
					}

					retorno.add(helper);
				}
			}
			
			return retorno;
			 
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0008]-Verificar carta para o comando
	 *  
	 * @author Vivianne Sousa
	 * @date 19/04/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerCartasComando(Integer idTarifaSocialComandoCarta, 
			Integer idLocalidade, Integer tipoCarta) throws ControladorException {
		
		try {
			repositorioImovel.removerCartasComando(idTarifaSocialComandoCarta,idLocalidade,tipoCarta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0352] Emitir Contas e Cartas
	 * [SB0017] � Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Vivianne Sousa
	 * @date 29/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarAnoMesExclusaoTarifaSocialImovel(Integer idImovel) 
	throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarAnoMesExclusaoTarifaSocialImovel(idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 *  [UC1168] Encerrar Comandos de Cobran�a por Empresa
	 *
	 * @author Mariana Victor
	 * @created 10/05/2011
	 */
	public void retirarSituacaoCobrancaImovel(Integer idImovel, Integer idCobrancaSituacao) 
		throws ControladorException {
		
		try {
			repositorioImovel.retirarSituacaoCobrancaImovel(idImovel, idCobrancaSituacao);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Emitir OS de Empresa de Cobran�a - 
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Integer[]> pesquisarIdsImoveis(String[] idsOrdemServico)
		throws ControladorException {
		
		try {
			return repositorioImovel.pesquisarIdsImoveis(idsOrdemServico);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1174] Gerar Relat�rio Im�veis com Doa��es
	 * 
	 * Pesquisar Quantidade de Imoveis com Doa��es - 
	 * 
	 * @author Erivan Sousa
	 * @data 13/06/2011
	 */
	public Integer pesquisarQuantidadeImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ControladorException{
		Integer retorno = 0;
			
		try {
			retorno =  repositorioImovel.pesquisarQuantidadeImoveisDoacoes(filtro);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1174] Gerar Relat�rio Im�veis com Doa��es
	 * 
	 * Pesquisar Imoveis com Doa��es - 
	 * 
	 * @author Erivan Sousa
	 * @data 13/06/2011
	 */
	public Collection pesquisarImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ControladorException{
		Collection retorno;
			
		try {
			retorno =  repositorioImovel.pesquisarImoveisDoacoes(filtro);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	

	/**
	 * M�todo que retorna o id do im�vel  �rea comum
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio 
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovelCondomio
	 * @return imovel.id
	 */
	public Integer pesquisarImovelAreaComum(Integer idImovelCondominio) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImovelAreaComum(idImovelCondominio);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * <p>
	 * [UC0098] Manter V�nculos de Im�veis para Rateio Comum
	 * </p>
	 * <p>
	 * [SB0001] - [FS0012] - Caso a matr�cula do im�vel para �rea comum
	 * informada n�o exista na tabela IMOVEL, exibir a mensagem "Matr�cula
	 * inexistente no cadastro" e retornar para o passo correspondente no fluxo
	 * principal
	 * </p>
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * @param idImovel
	 * @return
	 */
	public Short verificarExistenciaDoImovel(Integer idImovel) throws ControladorException {
		try {
			return this.repositorioImovel.verificarExistenciaDoImovel(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * M�todo que retorna o id do im�vel condominio
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio 
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovelCondomio
	 * @return imovel.id
	 */
	public Integer pesquisarImovelCondominio(Integer idImovel) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImovelCondominio(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * M�todo que retorna o id do im�vel condominio
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * 
	 * @param idImovel, indicadorImovelAreaComum
	 */
	public void atualizarIndicadorImovelAreaComumDoImovel(Integer idImovel, Short indicadorImovelAreaComum)	throws ControladorException {
		try {
			this.repositorioImovel.atualizarIndicadorImovelAreaComumDoImovel(idImovel, indicadorImovelAreaComum);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0457] - Encerrar Ordem de Servi�o.
	 * [SB0009] - Verificar Situa��o Especial de Faturamento.
	 * 
	 * Verifica se um im�vel est� em situa��o especial de faturamento
	 * para um dado imovel (idImovel). 
	 * A situa��o especial de faturamento tem o ftst_id = 2
	 * 
	 * @param idImovel
	 * @return Imovel
	 * @throws ControladorException 
	 */
	public Imovel pesquisarImovelSituacaoEspecialFaturamento(Integer idImovel) throws ControladorException{
		try {
			return this.repositorioImovel.pesquisarImovelSituacaoEspecialFaturamento(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Obt�m a principal categoria
	 * 
	 * @author Rodrigo Cabral
	 * @date 13/09/2011
	 * 
	 * @param colecaoImovelSubCategorias
	 * @return
	 * @throws ControladorException
	 */
	public Categoria obterPrincipalCategoria(Collection colecaoImovelSubCategorias)
		throws ControladorException {
	// Cria a vari�vel que vai armazenar a categoria principal do im�vel
	Categoria categoriaPrincipal = null;
	
	SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
	
	// Pesquisa o im�vel informado
	// Imovel imovel = this.pesquisarImovelDigitado(idImovel);
	
	// Cria a cole��o que vai armazenar as categorias com maiorquantidade de
	// economias
	Collection<Categoria> colecaoCategoriasComMaiorQtdEconomias = new ArrayList();
	
	// [UC0108] Obt�m a quantidade de economias por categoria
	Collection<Categoria> colecaoCategoriasImovel = new ArrayList();
	
	Collection<ImovelSubcategoria> colecaoSubCategoriasImovel = colecaoImovelSubCategorias;
	
	Iterator iColecaoSubCategoriasImovel = colecaoSubCategoriasImovel.iterator();
	
	while (iColecaoSubCategoriasImovel.hasNext()){
		
		ImovelSubcategoria subCategoria = (ImovelSubcategoria) iColecaoSubCategoriasImovel.next();
		
		Categoria categoria = subCategoria.getComp_id().getSubcategoria().getCategoria();
		
		categoria.setQuantidadeEconomiasCategoria(new Integer(subCategoria.getQuantidadeEconomias()));
		
		colecaoCategoriasImovel.add(categoria);
		
	}
	
	
	// Inicializa a quantidade de categoria
	int quantidadeCategoria = -1;
	
	// Caso a cole��o de categorias do im�vel n�o esteja nula
	if (colecaoCategoriasImovel != null) {
		// La�o para verificar qual a categoria com maior quantidade de
		// economia
		for (Categoria categoriaImovel : colecaoCategoriasImovel) {
			if (quantidadeCategoria < categoriaImovel
					.getQuantidadeEconomiasCategoria().intValue()) {
				quantidadeCategoria = categoriaImovel
						.getQuantidadeEconomiasCategoria().intValue();
	
				colecaoCategoriasComMaiorQtdEconomias = new ArrayList();
				colecaoCategoriasComMaiorQtdEconomias.add(categoriaImovel);
			} else if (quantidadeCategoria == categoriaImovel
					.getQuantidadeEconomiasCategoria().intValue()) {
				colecaoCategoriasComMaiorQtdEconomias.add(categoriaImovel);
			}
		}
	}
	
	// [FS0001]Verificar mais de uma categoria com a maior quantidade de
	// economias
	
	// Caso s� exista um objeto na cole��o, recuperaa categoria a atribui a
	// categoria principal
	// Caso contr�rio recupera a categoria com o menor id
	if (colecaoCategoriasComMaiorQtdEconomias.size() == 1) {
		categoriaPrincipal = colecaoCategoriasComMaiorQtdEconomias
				.iterator().next();
	} else if (colecaoCategoriasComMaiorQtdEconomias.size() > 1) {
		/*
		 * for (Categoria categoriaImovel :
		 * colecaoCategoriasComMaiorQtdEconomias) { int idTemp = -1; if
		 * (idTemp < categoriaImovel.getId().intValue()) { idTemp =
		 * categoriaImovel.getId().intValue(); categoriaPrincipal =
		 * categoriaImovel; } }
		 */
		int idTemp = -1;
		if(!sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)){
			for (Categoria categoriaImovel : colecaoCategoriasComMaiorQtdEconomias) {
				if (idTemp == -1) {
					idTemp = categoriaImovel.getId().intValue();
					categoriaPrincipal = categoriaImovel;
				} else if (categoriaImovel.getId().intValue() < idTemp) {
					idTemp = categoriaImovel.getId().intValue();
					categoriaPrincipal = categoriaImovel;
				}
			}
		}else{
			for (Categoria categoriaImovel : colecaoCategoriasComMaiorQtdEconomias) {
				if (idTemp == -1) {
					idTemp = categoriaImovel.getId().intValue();
					categoriaPrincipal = categoriaImovel;
				} else if (categoriaImovel.getId().intValue() > idTemp) {
					idTemp = categoriaImovel.getId().intValue();
					categoriaPrincipal = categoriaImovel;
				}
			}
		}
		
	}
	
	// Retorna a categoria principal
	return categoriaPrincipal;
	}
	
	/**
	 * Seleciona a Subcategoria principal de uma categoria
	 * 
	 * @param idCategoria
	 * @return
	 * @throws ControladorException
	 */
	public ImovelSubcategoria obterPrincipalSubcategoria(Integer idCategoria,
			Collection colecaoImovelSubCategorias) throws ControladorException {

			Collection<ImovelSubcategoria> colSubCategorias = colecaoImovelSubCategorias;
			
			Collection<ImovelSubcategoria> colSubCategoriasPrincipal = new ArrayList();
			
			Iterator iColSubCategorias = colSubCategorias.iterator();
			
			while (iColSubCategorias.hasNext()){
				
				ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iColSubCategorias.next();
				
				if (imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getId() == idCategoria){
					
					colSubCategoriasPrincipal.add(imovelSubcategoria);
				}
				
			}

			ImovelSubcategoria subcategoriaPrincipal = null;

			// Selecionamos o de maior quantidade de economias
			if (colSubCategoriasPrincipal != null && colSubCategoriasPrincipal.size() > 0) {
				for (ImovelSubcategoria sub : colSubCategoriasPrincipal) {
					if (subcategoriaPrincipal == null
							|| subcategoriaPrincipal.getQuantidadeEconomias() < sub
									.getQuantidadeEconomias())
						subcategoriaPrincipal = sub;
				}
			}

			return subcategoriaPrincipal;
		
	}
	
	public void atualizarImovel(Imovel imovel) throws ControladorException {
			
			getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
			
			this.getControladorUtil().atualizar(imovel);
	}
	
	/**TODO:COSANPA
	 * MANTIS 494 
	 * 
	 *  Necess�rio para a gera��o do registro Tipo 3
	 * 
	 * @author Wellington Vernech Rocha
	 * 
	 * @return Ramo Atividade
	 * @throws ControladorException
	 */

	public Collection<ImovelRamoAtividade> pesquisarRamoAtividadeDoImovel(Integer idImovel)
			throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarRamoAtividadeDoImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	public void atualizarIdArquivoTextoImovelAtualizacaoCadastral(
			Integer idArquivoTexto, Integer idImovel) throws ControladorException {
		
		ArquivoTextoAtualizacaoCadastral arquitoTexto = new ArquivoTextoAtualizacaoCadastral();
		arquitoTexto.setId(idArquivoTexto);
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = getControladorAtualizacaoCadastral().pesquisarImovelAtualizacaoCadastral(idImovel);
		imovelAtualizacaoCadastral.setIdArquivoTexto(idArquivoTexto);
		
		this.getControladorUtil().atualizar(imovelAtualizacaoCadastral);
	}

	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacaoCadastral(
			Integer idImovel) throws ControladorException {
		try {
			return this.repositorioImovel.pesquisarImovelControleAtualizacaoCadastral(idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
}
