package gcom.atendimentopublico;

import gcom.atendimentopublico.bean.ContratoPrestacaoServicoHelper;
import gcom.atendimentopublico.bean.DadosLigacoesBoletimCadastroHelper;
import gcom.atendimentopublico.bean.EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.bean.UnidadesFilhasHelper;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocal;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocalHome;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.BoletimOsConcluida;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividadePK;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterialPK;
import gcom.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacaoPK;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.atendimentopublico.ordemservico.bean.EmitirOrdemDeFiscalizacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterValorDebitoHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroLocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.VisualizacaoRegistroAtendimentoUrgencia;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.FiltroImovelSuprimido;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSuprimido;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.ControladorLocalidadeLocal;
import gcom.cadastro.localidade.ControladorLocalidadeLocalHome;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.cobranca.CobrancaBoletimMedicao;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.FiltroCobrancaBoletimMedicao;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.AutosInfracaoDebitoACobrar;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoBoletimMedicaoBean;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoRAHelper;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaClienteBean;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaHelper;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaItemBean;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServicoJuridicoBean;
import gcom.relatorio.atendimentopublico.RelatorioOSSituacaoBean;
import gcom.relatorio.atendimentopublico.RelatorioOSSituacaoHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarRelatorioReligacaoClientesInadiplentesHelper;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioReligacaoClientesInadiplentesHelper;
import gcom.seguranca.ControladorPermissaoEspecialLocal;
import gcom.seguranca.ControladorPermissaoEspecialLocalHome;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.IRepositorioUsuario;
import gcom.seguranca.acesso.usuario.RepositorioUsuarioHBM;
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
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Leandro Cavalcanti
 * @created 12 de junho de 2006
 */
public class ControladorAtendimentoPublicoSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	SessionContext sessionContext;

	private IRepositorioAtendimentoPublico repositorioAtendimentoPublico = null;	
	private IRepositorioUtil repositorioUtil = null;
	private IRepositorioUsuario repositorioUsuario = null;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioAtendimentoPublico = RepositorioAtendimentoPublicoHBM
				.getInstancia();
		repositorioUtil = RepositorioUtilHBM.getInstancia();
		repositorioUsuario = RepositorioUsuarioHBM.getInstancia(); 
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
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

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instáncia do ServiceLocator.

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
	 * Retorna a interface remota de ControladorParametro
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorLocalidadeLocal getControladorLocalidade() {
		ControladorLocalidadeLocalHome localHome = null;
		ControladorLocalidadeLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorLocalidadeLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);
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
	 * Retorna o valor de controladorFaturamento
	 * 
	 * @return O valor de controladorFaturamento
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {

		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instáncia do ServiceLocator.

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
	 * Retorna o valor de controladorCobranca
	 * 
	 * @return O valor de controladorCobranca
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instáncia do ServiceLocator.

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
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
	 */
	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
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
	 * Retorna o valor do ControladorMicromedicao
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a instância do ServiceLocator.
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
	 * Retorna o valor do ControladorOrdemServico
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @return O valor de ControladorOrdemServico
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;
		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
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
	 * Retorna o valor do ControladorLigacaoAgua
	 * 
	 * @author Flávio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @return O valor de controladorLigacaoAgua
	 */
	private ControladorLigacaoAguaLocal getControladorLigacaoAgua() {
		ControladorLigacaoAguaLocalHome localHome = null;
		ControladorLigacaoAguaLocal local = null;

		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorLigacaoAguaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_AGUA_SEJB);
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
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorEnderecoLocal getControladorEndereco() {

		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;

		// pega a instáncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
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
	 * Retorna o valor do ControladorBatch
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 * 
	 * @return O valor de ControladorBatch
	 */
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
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
	 * [UC0354] Efetuar Corte de Ligação de Água.
	 * 
	 * Permite efetuar Ligação de Água ou pelo menu ou pela funcionalidade
	 * encerrar a Execução da ordem de serviço.
	 * 
	 * [FS0003] Validar Consumo Mínimo. [SB0001] Gerar Ligação de Água [SB0002]
	 * Atualizar Imóvel Situação do Imovel.
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 03/08/2006
	 * 
	 * @param ligacaoAgua
	 * @param imovel
	 * @throws ControladorException
	 */
	public void efetuarLigacaoAgua(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		LigacaoAgua ligacaoAgua = integracaoComercialHelper.getLigacaoAgua();
		Imovel imovel = integracaoComercialHelper.getImovel();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();
		Usuario usuarioLogado = integracaoComercialHelper.getUsuarioLogado();

		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		
		if (ligacaoAgua != null && imovel != null) {

			// [SB0001] Gerar Ligação de Água

			// LAGU_ID
			ligacaoAgua.setId(imovel.getId());

			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAgua.ID, imovel.getId()));
			Collection colecaoLigacaoAguaBase = getControladorUtil().pesquisar(
					filtroLigacaoAgua, LigacaoAgua.class.getName());

			// LAGU_DTIMPLANTACAO
			ligacaoAgua.setDataImplantacao(new Date());

			// LAGU_TMULTIMAALTERACAO
			Date dataCorrente = new Date();
			ligacaoAgua.setUltimaAlteracao(dataCorrente);
			
			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR,
					ligacaoAgua.getId(), ligacaoAgua.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			registradorOperacao.registrarOperacao(ligacaoAgua);
			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			//Alteração feita por Sávio Luiz
			//Data: 25/05/2011
			if (!colecaoLigacaoAguaBase.isEmpty()) {
				getControladorUtil().atualizar(ligacaoAgua);
			}else{
				getControladorUtil().inserir(ligacaoAgua);
			}
			

			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);

			getControladorImovel()
					.atualizarImovelExecucaoOrdemServicoLigacaoAgua(imovel,
							ligacaoAguaSituacao);

			if (imovel.getLigacaoEsgotoSituacao() != null
					&& imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO
							.intValue()) {

				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
				ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);

				imovel.setUltimaAlteracao(new Date());
				// [SB0002] Atualizar Imóvel
				getControladorImovel()
						.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(
								imovel, ligacaoEsgotoSituacao);
			}
		}

		if (ordemServico != null) {

			if (!integracaoComercialHelper.isVeioEncerrarOS()
					&& ordemServico.getServicoTipo().getDebitoTipo() != null) {

				this
						.getControladorOrdemServico()
						.verificarOrdemServicoControleConcorrencia(ordemServico);
				getControladorOrdemServico().atualizaOSGeral(ordemServico);
			}

			if (ordemServico.getServicoTipo().getDebitoTipo() != null
					&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
				getControladorOrdemServico().gerarDebitoOrdemServico(
						ordemServico.getId(),
						ordemServico.getServicoTipo().getDebitoTipo().getId(),
						ordemServico.getValorAtual(), new Integer(qtdParcelas),
						ordemServico.getPercentualCobranca().toString(),
						integracaoComercialHelper.getUsuarioLogado());
			}

		}

	}

	/**
	 * [UC0354] Efetuar Ligação de Água.
	 * 
	 * Permite validar Ligação de Água Efetuar ou pelo menu ou pela
	 * funcionalidade encerrar a Execução da ordem de serviço.
	 * 
	 * 
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaEfetuar(Imovel imovel, LigacaoAgua ligacaoAgua)
			throws ControladorException {
		// [FS0003] Validar Consumo Minimo
		if (ligacaoAgua != null) {
			this.validarConsumoMinimoLigacaoAguaImovel(imovel, ligacaoAgua
					.getNumeroConsumoMinimoAgua().toString());
		}

	}

	/**
	 * [UC0354] Efetuar Ligação de Água.
	 * 
	 * Permite validar Ligação de Água Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a Execução da ordem de serviço.
	 * 
	 * [FS0008] Verificar Situação Rede de Água na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Água do Imóvel
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR LIGAÇÃO DE ÁGUA, não será necessário realizar as
		 * validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(ordem.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordem.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_LIGACAO_AGUA) {
				throw new ControladorException(
						"atencao.servico_associado_ligacao_agua_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3 e caso 5
//		this.getControladorOrdemServico().validaOrdemServico(ordem,veioEncerrarOS);
		this.getControladorOrdemServico().validaOrdemServicoDiasAditivoPrazo(ordem,veioEncerrarOS);
		
		// Caso 4
		if (ordem.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordem.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordem.getRegistroAtendimento().getImovel();

		// [FS0002] Validar Situação de Água do Imóvel.
		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.POTENCIAL
				.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.FACTIVEL
						.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.EM_FISCALIZACAO
						.intValue()) {

			throw new ControladorException(
					"atencao.situacao_validar_ligacao_agua_invalida_exibir",
					null, imovel.getLigacaoAguaSituacao().getDescricao());
		}

		/*
		 * [FS0007] Verificar Situação Rede de Água na Quadra
		 * 
		 * Integração com o conceito de face da quadra Raphael Rossiter em
		 * 21/05/2009
		 */
		IntegracaoQuadraFaceHelper integracao = this.getControladorLocalidade()
				.integracaoQuadraFace(imovel.getId());

		if ((integracao.getIndicadorRedeAgua()).equals(Quadra.SEM_REDE)) {

			throw new ControladorException(
					"atencao.seituacao_rede_agua_quadra", null, imovel.getId()
							+ "");
		}

		// [FS0006] Verificar Situação do Imovel
		if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			throw new ControladorException(
					"atencao.situacao_imovel_indicador_exclusao", null, imovel
							.getId()
							+ "");
		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * [UC0353] Efetuar Ligação de Esgoto.
	 * 
	 * Permite validar Ligação de esgoto Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a Execução da ordem de serviço.
	 * 
	 * [FS0008] Verificar Situação Rede de Esgoto na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Esgoto do Imóvel
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoEsgotoExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR LIGAÇÃO DE ESGOTO, não será necessário realizar as
		 * validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(ordem.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_LIGACAO_ESGOTO_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordem.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_LIGACAO_ESGOTO) {
				throw new ControladorException(
						"atencao.servico_associado_ligacao_esgoto_invalida");
			}

		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordem,
				veioEncerrarOS);
		// Caso 4
		if (ordem.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordem.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordem.getRegistroAtendimento().getImovel();

		// [FS0002] Validar Situação de Água do Imóvel.
		if (imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.POTENCIAL
				.intValue()
				&& imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.FACTIVEL
						.intValue()
				&& imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.EM_FISCALIZACAO
						.intValue()) {

			throw new ControladorException(
					"atencao.situacao_validar_ligacao_esgoto_invalida_exibir",
					null, imovel.getLigacaoAguaSituacao().getDescricao());
		}

		/*
		 * [FS0003] Verificar Situação Rede de Água na Quadra
		 * 
		 * Integração com o conceito de face da quadra Raphael Rossiter em
		 * 17/09/2009
		 */
		IntegracaoQuadraFaceHelper integracao = this.getControladorLocalidade()
				.integracaoQuadraFace(imovel.getId());

		if ((integracao.getIndicadorRedeEsgoto()).equals(Quadra.SEM_REDE)) {

			throw new ControladorException(
					"atencao.percentual_rede_esgoto_quadra", null, imovel
							.getId()
							+ "");
		}

		// [FS0006] Verificar Situação do Imovel
		if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			throw new ControladorException(
					"situacao_imovel_indicador_exclusao_esgoto", null, imovel
							.getId()
							+ "");
		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * Ligação de esgoto do Imóvel no momento da atualização. // [UC0105] -
	 * Obter Consumo Mínimo da Ligação //[FS0003] Validar Consumo Minimo
	 * 
	 * @author Leandro Cavalcanti
	 * @date 20/07/2006
	 * 
	 * @param volumeMinimoFixado
	 * @param ligacaoEsgoto
	 */
	public Integer validarConsumoMinimoLigacaoAguaImovel(Imovel imovel,
			String volumeMinimoFixado) throws ControladorException {

		// [FS0003] Validar Consumo Minimo
		/*
		 * atencao.situacao_volume_minimo_fixado_nao_multiplo= Valor do volume
		 * Mínimo Fixado deve ser alterado para {0} valor multiplo de quantidade
		 * de economias {1}.
		 */

		// [UC0105] - Obter Consumo Mínimo da Ligação
		int consumoMinimoObtido = getControladorMicromedicao()
				.obterConsumoMinimoLigacao(imovel, null);
		Integer consumoMinimoObtido1 = new Integer(consumoMinimoObtido);

		// Verificar se o volume Mínimo informado seja menor que o valor
		// Mínimo obtido para Imóvel.
		if (volumeMinimoFixado != null && !volumeMinimoFixado.trim().equals("")) {
			if (!volumeMinimoFixado.trim().equalsIgnoreCase(
					ConstantesSistema.SET_ZERO.toString())) {
				Integer consumoInformado = Integer.parseInt(volumeMinimoFixado);
				if (consumoInformado.intValue() < consumoMinimoObtido1
						.intValue()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.situacao_volume_minimo_fixado_menor_consumo_calculado",
							null, consumoMinimoObtido + "");
				}
			}
		} else {
			throw new ControladorException("atencao.requerid", null,
					"Situação da Ligação de Esgoto");
		}

		return new Integer(volumeMinimoFixado);
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * Ligação de agua do Imóvel no momento da exibição.
	 * 
	 * [FS0001] Verificar existência da matrícula do Imóvel. [FS0002] Verificar
	 * Situação do Imovel. [FS0003] Validar Situação de Esgoto do Imóvel.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param Imovel
	 */
	public void validarExibirLigacaoAguaImovel(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException {

		// [FS0001] Validar Ordem de Servico
		// Caso 2
		if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_LIGACAO_AGUA
				&& ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CONFIRMAR_DADOS_LIGACAO_AGUA
				&& ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CONFIRMAR_DADOS_CORTE_LIGACAO_AGUA
				&& ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CORTE_LIGACAO_AGUA
				&& ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CONFIRMAR_DADOS_SUPRESSAO_LIGACAO_AGUA
				&& ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_SUPRESSAO_LIGACAO_AGUA) {

			throw new ControladorException(
					"atencao.servico_associado_atualizar_ligacao_agua_invalida");
		}
		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);
		// Caso 4
		if (ordemServico.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordemServico.getRegistroAtendimento().getId());
		}

		// [FS0004] Validar Data do Encerramento da Ordem de Servico
		if (ordemServico.getServicoTipo().getId().intValue() == ServicoTipo.TIPO_LIGACAO_AGUA
				|| ordemServico.getServicoTipo().getId().intValue() == ServicoTipo.TIPO_CORTE_LIGACAO_AGUA
				|| ordemServico.getServicoTipo().getId().intValue() == ServicoTipo.TIPO_SUPRESSAO_LIGACAO_AGUA) {

			Date dataEncerramento = ordemServico.getDataEncerramento();

			if (dataEncerramento != null) {

				GregorianCalendar dtEncerramento = new GregorianCalendar();
				dtEncerramento.setTime(dataEncerramento);

				int qtdDias = Util.obterQuantidadeDiasEntreDuasDatas(
						dtEncerramento.getTime(), new Date());

				FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

				Collection colecao = getControladorUtil().pesquisar(
						filtroSistemaParametro,
						SistemaParametro.class.getName());

				if (colecao != null && !colecao.isEmpty()) {

					SistemaParametro sistemaParametro = (SistemaParametro) Util
							.retonarObjetoDeColecao(colecao);

					int qtdMaximo = sistemaParametro.getDiasMaximoAlterarOS()
							.intValue();

					if (qtdDias > qtdMaximo) {

						String[] msg = new String[2];

						msg[0] = "" + ordemServico.getId();
						msg[1] = "" + qtdMaximo;

						throw new ControladorException(
								"atencao.ligacao_agua_data_encerramento_os_invalida",
								null, msg);
					}
				}
			}
		}

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			throw new ControladorException("atencao.naocadastrado", null,
					"Ligacao de Água");
		}

		// [FS003] Validar Situação de Água do Imóvel.
		if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.POTENCIAL
				.intValue()
				|| imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.FACTIVEL
						.intValue()) {

			throw new ControladorException(
					"atencao.atualizar_ligacao_agua_situacao_invalida", null,
					imovel.getLigacaoAguaSituacao().getDescricao());
		}
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * retirada de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirRetiradaHidrometroAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR RETIRADA HIDROMETRO, não será necessário realizar as
		 * validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_RETIRADA_HIDROMETRO_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			int servicoTipo = ordemServico.getServicoTipo().getId().intValue();
			if (servicoTipo != ServicoTipo.TIPO_RETIRADA_HIDROMETRO) {

				throw new ControladorException(
						"atencao.servico_associado_retirada_hidrometro_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);
//		 Caso 4
		Imovel imovel = null;
		if ((ordemServico.getRegistroAtendimento() != null && ordemServico
				.getRegistroAtendimento().getImovel() == null)
				&& ordemServico.getImovel() == null) {

			throw new ControladorException(
					"atencao.ordem_servico_imovel_invalida");
		} else {

			if (ordemServico.getImovel() != null) {
				imovel = ordemServico.getImovel();
			} else {
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			}
		}

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		// // [FS0003] - Verificar Situação de Agua ou Esgoto.
		// // [FS0004] - Verificar a existência de hidrômetro no Imóvel/Ligação
		// de
		// // Água
		//
		// // Caso 1
		// if (servicoTipo == ServicoTipo.TIPO_RETIRADA_HIDROMETRO_POCO) {
		//
		// LigacaoEsgotoSituacao ligacaoEsgotoSituacao = imovel
		// .getLigacaoEsgotoSituacao();
		//
		// if (ligacaoEsgotoSituacao.getId().intValue() !=
		// LigacaoEsgotoSituacao.LIGADO
		// && ligacaoEsgotoSituacao.getId().intValue() !=
		// LigacaoEsgotoSituacao.LIG_FORA_DE_USO
		// && ligacaoEsgotoSituacao.getId().intValue() !=
		// LigacaoEsgotoSituacao.TAMPONADO) {
		//
		// throw new ControladorException(
		// "atencao.situacao_retirada_hidrometro_poco_invalida",
		// null, ligacaoEsgotoSituacao.getDescricao());
		// }
		//
		// if (imovel.getHidrometroInstalacaoHistorico() == null) {
		// throw new ControladorException(
		// "atencao.imovel_poco_sem_hidrometro", null, ""
		// + imovel.getId());
		// }
		//
		// // Caso 2
		// } else if (servicoTipo == ServicoTipo.TIPO_RETIRADA_HIDROMETRO) {
		//
		// LigacaoAguaSituacao ligacaoAguaSituacao = imovel
		// .getLigacaoAguaSituacao();
		//
		// if (ligacaoAguaSituacao.getId().intValue() !=
		// LigacaoAguaSituacao.LIGADO
		// && ligacaoAguaSituacao.getId().intValue() !=
		// LigacaoAguaSituacao.CORTADO) {
		//
		// throw new ControladorException(
		// "atencao.situacao_retirada_hidrometro_poco_invalida",
		// null, ligacaoAguaSituacao.getDescricao());
		// }
		//
		// if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() ==
		// null) {
		// throw new ControladorException(
		// "atencao.imovel_ligacao_agua_sem_hidrometro", null, ""
		// + imovel.getId());
		// }
		// }

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * remanejamento de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirRemanejmentoHidrometroAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR REMANEJAMENTO HIDROMETRO, não será necessário
		 * realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			int servicoTipo = ordemServico.getServicoTipo().getId().intValue();
			if (servicoTipo != ServicoTipo.TIPO_REMANEJAMENTO_HIDROMETRO_LIGACAO_AGUA) {

				throw new ControladorException(
						"atencao.servico_associado_remanejamento_hidrometro_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);
		
		//Caso 4
		Imovel imovel = null;
		if ((ordemServico.getRegistroAtendimento() != null && ordemServico
				.getRegistroAtendimento().getImovel() == null)
				&& ordemServico.getImovel() == null) {

			throw new ControladorException(
					"atencao.ordem_servico_imovel_invalida");
		} else {

			if (ordemServico.getImovel() != null) {
				imovel = ordemServico.getImovel();
			} else {
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			}
		}

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			throw new ControladorException("atencao.naocadastrado", null,
					"Ligação de Água");
		}

		// [FS0003] - Verificar a existência de hidrômetro no Imóvel

		// Caso 1
		/*
		 * if (servicoTipo == ServicoTipo.TIPO_REMANEJAMENTO_HIDROMETRO_POCO) {
		 * if (imovel.getHidrometroInstalacaoHistorico() == null) { throw new
		 * ControladorException( "atencao.imovel_poco_sem_hidrometro", null, "" +
		 * imovel.getId()); } // Caso 2 } else if (servicoTipo ==
		 * ServicoTipo.TIPO_REMANEJAMENTO_HIDROMETRO_LIGACAO_AGUA) { if
		 * (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() == null) {
		 * throw new ControladorException(
		 * "atencao.imovel_ligacao_agua_sem_hidrometro", null, "" +
		 * imovel.getId()); } }
		 */

		if (ordemServico.getRegistroAtendimento() == null || ordemServico.getRegistroAtendimento()
				.getSolicitacaoTipoEspecificacao().getIndicadorLigacaoAgua()
				.equals(MedicaoTipo.LIGACAO_AGUA.shortValue())) {

			if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() == null) {
				throw new ControladorException(
						"atencao.imovel_ligacao_agua_sem_hidrometro", null, ""
								+ imovel.getId());
			}
		} else {

			if (imovel.getHidrometroInstalacaoHistorico() == null) {
				throw new ControladorException(
						"atencao.imovel_poco_sem_hidrometro", null, ""
								+ imovel.getId());
			}
		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades do
	 * substituição de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 31/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirSubstituicaoHidrometro(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR SUBSTITUICAO HIDROMETRO, não será necessário
		 * realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			int servicoTipo = ordemServico.getServicoTipo().getId().intValue();

			if (servicoTipo != ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO) {

				throw new ControladorException(
						"atencao.servico_associado_substituicao_hidrometro_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);
		// Caso 4
		/*
		 * Autor: Vivianne Sousa Data: 11/12/2007 Analista Responsavel: Denys
		 */
		Imovel imovel = null;
		if ((ordemServico.getRegistroAtendimento() != null && ordemServico
				.getRegistroAtendimento().getImovel() == null)
				&& ordemServico.getImovel() == null) {

			throw new ControladorException(
					"atencao.ordem_servico_imovel_invalida");
		} else {

			if (ordemServico.getImovel() != null) {
				imovel = ordemServico.getImovel();
			} else {
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			}
		}

		// [FS0007] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		// [FS0008] - Verificar existência de hidrômetro no tipo de medição

		// Caso 1
		if (ordemServico.getRegistroAtendimento() == null
				|| ordemServico.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao()
						.getIndicadorLigacaoAgua().equals(
								MedicaoTipo.LIGACAO_AGUA.shortValue())) {
			if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() == null) {
				throw new ControladorException(
						"atencao.imovel_ligacao_agua_sem_hidrometro", null, ""
								+ imovel.getId());
			}
		} else {
			if (imovel.getHidrometroInstalacaoHistorico() == null) {
				throw new ControladorException(
						"atencao.imovel_poco_sem_hidrometro", null, ""
								+ imovel.getId());
			}
		}

		// [FS0002] Verificar Situação do hidrometro.
		/*
		 * Hidrometro hidrometro =
		 * hidrometroInstalacaoHistorico.getHidrometro();
		 * 
		 * if (hidrometro.getHidrometroSituacao().getId().intValue() !=
		 * HidrometroSituacao.DISPONIVEL .intValue()) { throw new
		 * ControladorException( "atencao.hidrometro_situacao_indisponivel",
		 * null, hidrometro.getHidrometroSituacao().getDescricao()); }
		 */

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades do
	 * restabelecimento Ligação de agua
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirRestabelecimentoLigacaoAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR RESTABELECIMENTO LIGAÇÃO DE ÁGUA, não será
		 * necessário realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_RESTABELECIMENTO_LIGACAO_AGUA) {
				throw new ControladorException(
						"atencao.servico_associado_restabelecimento_ligacao_agua_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
//		this.getControladorOrdemServico().validaOrdemServico(ordemServico,veioEncerrarOS);
		this.getControladorOrdemServico().validaOrdemServicoDiasAditivoPrazo(ordemServico,veioEncerrarOS);
		
		// Caso 4
		if (ordemServico.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordemServico.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			throw new ControladorException("atencao.naocadastrado", null,
					"Ligação de Água");
		}

		// [FS0003] Verificar a situação de Água
		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPRIMIDO
				.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPR_PARC_PEDIDO
						.intValue()) {

			throw new ControladorException(
					"atencao.situacao_ligacao_agua_invalida", null, ""
							+ imovel.getId(), "Suprimido ou Suprimido Parcial");
		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades de
	 * corte adimistrativo de Ligação de Água
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirCorteAdministrativoLigacaoAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR CORTE ADMINISTRATIVO LIGAÇÃO DE ÁGUA, não será
		 * necessário realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA) {
				throw new ControladorException(
						"atencao.servico_associado_corte_administrativo_ligacao_agua_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);
		// Caso 4

		// Comentado por Raphael Rossiter em 26/02/2007
		/*
		 * if (ordemServico.getRegistroAtendimento().getImovel() == null) {
		 * throw new ControladorException(
		 * "atencao.ordem_servico_ra_imovel_invalida", null, "" +
		 * ordemServico.getRegistroAtendimento().getId()); }
		 */

		if (ordemServico.getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_imovel_invalido");
		}

		// Comentado por Raphael Rossiter em 28/02/2007
		// Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel = ordemServico.getImovel();

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			throw new ControladorException("atencao.naocadastrado", null,
					"Ligação de Água");
		}

		// [FS0003] Verificar a situação de Água
		int idLigacaoAguaSituacao = imovel.getLigacaoAguaSituacao().getId()
				.intValue();
		if (idLigacaoAguaSituacao != LigacaoAguaSituacao.LIGADO.intValue()
				&& idLigacaoAguaSituacao != LigacaoAguaSituacao.LIGADO_EM_ANALISE
						.intValue()) {

			throw new ControladorException(
					"atencao.situacao_ligacao_agua_invalida", null, ""
							+ imovel.getId(), "Ligado");

		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades de
	 * reLigação de Água
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirReligacaoAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR RELIGAÇÃO LIGAÇÃO DE ÁGUA, não será necessário
		 * realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_RELIGACAO_AGUA) {
				throw new ControladorException(
						"atencao.servico_associado_religacao_agua_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
//		this.getControladorOrdemServico().validaOrdemServico(ordemServico,veioEncerrarOS);
		this.getControladorOrdemServico().validaOrdemServicoDiasAditivoPrazo(ordemServico,veioEncerrarOS);
		
		// Caso 4
		if (ordemServico.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordemServico.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			throw new ControladorException("atencao.naocadastrado", null,
					"Ligação de Água");
		}

		// [FS0003] Verificar a situação de Água
		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.CORTADO
				.intValue()) {

			throw new ControladorException(
					"atencao.situacao_ligacao_agua_invalida", null, ""
							+ imovel.getId(), "Cortado");

		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades do
	 * supressao Ligação de agua
	 * 
	 * @author Rafael Pinto
	 * @date 28/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirSupressaoLigacaoAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR SUPRESSÃO LIGAÇÃO DE ÁGUA, não será necessário
		 * realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_SUPRESSAO_LIGACAO_AGUA) {
				throw new ControladorException(
						"atencao.servico_associado_supressa_ligacao_agua_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
//		this.getControladorOrdemServico().validaOrdemServico(ordemServico,veioEncerrarOS);
		this.getControladorOrdemServico().validaOrdemServicoDiasAditivoPrazo(ordemServico,veioEncerrarOS);

		// Caso 4
		// Comentado por Raphael Rossiter em 28/02/2007
		/*
		 * if (ordemServico.getRegistroAtendimento().getImovel() == null) {
		 * throw new ControladorException(
		 * "atencao.ordem_servico_ra_imovel_invalida", null, "" +
		 * ordemServico.getRegistroAtendimento().getId()); }
		 */

		if (ordemServico.getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordemServico.getRegistroAtendimento().getId());
		}

		// Comentado por Raphael Rossiter em 28/02/2007
		// Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel = ordemServico.getImovel();

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			throw new ControladorException("atencao.naocadastrado", null,
					"Ligação de Água");
		}

		// [FS0003] Verificar a situação de Água
		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO
				.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO_EM_ANALISE
						.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.CORTADO
						.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPR_PARC_PEDIDO
						.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.EM_FISCALIZACAO
						.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.EM_CANCELAMENTO
						.intValue()) {

			throw new ControladorException(
					"atencao.situacao_ligacao_agua_supressao_invalida", null,
					imovel.getLigacaoAguaSituacao().getDescricao() + "");
		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * [UC0367]Atualizar Ligação de Agua no sistema.
	 * 
	 * [SB002] Atualiza Ligação de agua.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param ligacaoAgua
	 * @throws ControladorException
	 */
	public void atualizarLigacaoAgua(LigacaoAgua ligacaoAgua, Usuario usuario)
			throws ControladorException {

		this.getControladorMicromedicao().validarImovelEmCampo(ligacaoAgua.getId());
		
		if (ligacaoAgua != null) {

			// item [FS0001] Verificar existência da matrícula do Imóvel.
			if (ligacaoAgua.getImovel() != null) {

				// item [FS0002] Verificar Situação do Imovel
				if (ligacaoAgua.getImovel().getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.atualizar_imovel_situacao_invalida", null,
							ligacaoAgua.getImovel().getId() + "");
				}

				// item [FS0003] Validar Situação de Agua do Imovel
				if (ligacaoAgua.getImovel().getLigacaoAguaSituacao().getId()
						.intValue() == LigacaoAguaSituacao.POTENCIAL.intValue()
						|| ligacaoAgua.getImovel().getLigacaoAguaSituacao()
								.getId().intValue() == LigacaoAguaSituacao.FACTIVEL
								.intValue()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.atualizar_ligacao_agua_situacao_invalida",
							null, ligacaoAgua.getImovel().getId() + "");

				}

				if (ligacaoAgua.getImovel().getLigacaoAguaSituacao().getId()
						.intValue() == LigacaoAguaSituacao.CORTADO.intValue()) {

					// item [FS0005] Validar Tipo Corte
					if (ligacaoAgua.getCorteTipo() == null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizar_ligacao_agua_situacao_tipo_corte",
								null, ligacaoAgua.getImovel().getId() + "");

						// item [FS0006] Validar Motivo Corte
					} else if (ligacaoAgua.getMotivoCorte() == null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizar_ligacao_agua_situacao_motivo_corte",
								null, ligacaoAgua.getImovel().getId() + "");

						// item [FS0010] Validar Numero Selo Corte
					} else if (ligacaoAgua.getNumeroSeloCorte() == null
							|| ligacaoAgua.getNumeroSeloCorte().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizar_ligacao_agua_situacao_selo_corte",
								null, ligacaoAgua.getImovel().getId() + "");

					}

				}

				if (ligacaoAgua.getImovel().getLigacaoAguaSituacao().getId()
						.intValue() == LigacaoAguaSituacao.SUPRIMIDO.intValue()
						|| ligacaoAgua.getImovel().getLigacaoAguaSituacao()
								.getId().intValue() == LigacaoAguaSituacao.SUPR_PARC_PEDIDO
								.intValue()) {

					// item [FS0007] Validar Motivo Supressao
					if (ligacaoAgua.getSupressaoMotivo() == null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizar_ligacao_agua_situacao_motivo_supressao",
								null, ligacaoAgua.getImovel().getId() + "");

						// item [FS0008] Validar Tipo Supressao
					} else if (ligacaoAgua.getSupressaoTipo() == null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizar_ligacao_agua_situacao_tipo_supressao",
								null, ligacaoAgua.getImovel().getId() + "");

						// item [FS0011] Validar Numero Selo Supressao
					} else if (ligacaoAgua.getNumeroSeloSupressao() == null
							|| ligacaoAgua.getNumeroSeloSupressao().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizar_ligacao_agua_situacao_selo_supressao",
								null, ligacaoAgua.getImovel().getId() + "");

					}

				}

			} else {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.situacao_imovel_indicador_exclusao_esgoto",
						null, ligacaoAgua.getImovel().getId() + "");
			}

			this.verificarLigacaoAguaControleConcorrencia(ligacaoAgua);

			/*
			 * [UC0107] Registrar Transação
			 */
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_ATUALIZAR_LIGACAO_AGUA, ligacaoAgua
							.getId(), ligacaoAgua.getId(),
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// [UC0107] -Fim- Registrar Transação

			registradorOperacao.registrarOperacao(ligacaoAgua);

			// Efetuando uma Ligação de Agua
			getControladorUtil().atualizar(ligacaoAgua);

			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = ligacaoAgua
					.getHidrometroInstalacaoHistorico();

			if (hidrometroInstalacaoHistorico != null) {
				registradorOperacao
						.registrarOperacao(hidrometroInstalacaoHistorico);
				getControladorUtil().atualizar(hidrometroInstalacaoHistorico);
			}

		}
	}

	/**
	 * Faz o controle de concorrencia de ligacao Agua
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	private void verificarLigacaoAguaControleConcorrencia(
			LigacaoAgua ligacaoAgua) throws ControladorException {

		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, ligacaoAgua.getId()));

		Collection colecaoLigacao = getControladorUtil().pesquisar(
				filtroLigacaoAgua, LigacaoAgua.class.getName());

		if (colecaoLigacao == null || colecaoLigacao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		LigacaoAgua ligacaoAguaAtual = (LigacaoAgua) Util
				.retonarObjetoDeColecao(colecaoLigacao);

		if (ligacaoAguaAtual.getUltimaAlteracao().after(
				ligacaoAgua.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * Faz o controle de concorrencia de hidrometro instalacao historico
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	private void verificarHidrometroInstalacaoHistoricoControleConcorrencia(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico)
			throws ControladorException {

		FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();

		filtroHidrometroInstalacaoHistorico
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroInstalacaoHistorico.ID,
						hidrometroInstalacaoHistorico.getId()));

		Collection colecaoHidrometroInstalacaoHistorico = getControladorUtil()
				.pesquisar(filtroHidrometroInstalacaoHistorico,
						HidrometroInstalacaoHistorico.class.getName());

		if (colecaoHidrometroInstalacaoHistorico == null
				|| colecaoHidrometroInstalacaoHistorico.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAtual = (HidrometroInstalacaoHistorico) Util
				.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);

		// Verificar se categoria já foi atualizada por outro usuário durante
		// esta atualização
		if (hidrometroInstalacaoHistoricoAtual.getUltimaAlteracao().after(
				hidrometroInstalacaoHistorico.getUltimaAlteracao())) {

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

	}

	/**
	 * Faz o controle de concorrencia de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	private void verificarHidrometroControleConcorrencia(Hidrometro hidrometro)
			throws ControladorException {

		FiltroHidrometro filtroHidrometro = new FiltroHidrometro();

		filtroHidrometro.adicionarParametro(new ParametroSimples(
				FiltroHidrometro.ID, hidrometro.getId()));

		Collection colecaoHidrometro = getControladorUtil().pesquisar(
				filtroHidrometro, Hidrometro.class.getName());

		if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		Hidrometro hidrometroAtual = (Hidrometro) Util
				.retonarObjetoDeColecao(colecaoHidrometro);

		// Verificar se categoria já foi atualizada por outro usuário durante
		// esta atualização
		if (hidrometroAtual.getUltimaAlteracao().after(
				hidrometro.getUltimaAlteracao())) {

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

	}

	/**
	 * [UC0354] Efetuar Corte de Ligação de Água.
	 * 
	 * Permite efetuar Ligação de Esgoto ou pelo menu ou pela funcionalidade
	 * encerrar a Execução da ordem de serviço.
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * @param ligacaoEsgoto
	 * @param imovel
	 * @throws ControladorException
	 */
	public void inserirLigacaoEsgoto(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		LigacaoEsgoto ligacaoEsgoto = integracaoComercialHelper
				.getLigacaoEsgoto();
		Imovel imovel = integracaoComercialHelper.getImovel();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();

		/*
		 * [UC0107] Registrar Transação
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LIGACAO_ESGOTO_EFETUAR, imovel.getId(),
				imovel.getId(), new UsuarioAcaoUsuarioHelper(
						integracaoComercialHelper.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// [UC0107] -Fim- Registrar Transação

		getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		
		ligacaoEsgoto.setId(imovel.getId());
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
		filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgoto.ID, imovel.getId()));
		Collection colecaoLigacaoEsgotoBase = getControladorUtil().pesquisar(
				filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());

		registradorOperacao.registrarOperacao(ligacaoEsgoto);

		if (colecaoLigacaoEsgotoBase != null
				&& !colecaoLigacaoEsgotoBase.isEmpty()) {
			getControladorUtil().atualizar(ligacaoEsgoto);
		} else {
			getControladorUtil().inserir(ligacaoEsgoto);
		}

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);

		imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

		registradorOperacao.registrarOperacao(imovel);
		getControladorTransacao().registrarTransacao(imovel);

		getControladorImovel()
				.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel,
						ligacaoEsgotoSituacao);

		if (ordemServico != null) {

			if (!integracaoComercialHelper.isVeioEncerrarOS()
					&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
				getControladorOrdemServico().atualizaOSGeral(ordemServico);
			}

			if (ordemServico.getServicoTipo().getDebitoTipo() != null
					&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
				getControladorOrdemServico().gerarDebitoOrdemServico(
						ordemServico.getId(),
						ordemServico.getServicoTipo().getDebitoTipo().getId(),
						ordemServico.getValorAtual(), new Integer(qtdParcelas),
						ordemServico.getPercentualCobranca().toString(),
						integracaoComercialHelper.getUsuarioLogado());
			}
		}
	}

	/**
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserção da especificacao situacao criterio imovel.
	 * 
	 * [FS0001] Validar especificação da situaçãoo já existente [FS0002] Validar
	 * existência de hidrômetro na Ligação Água [FS0003] Validar existência de
	 * hidrômetro no Poço
	 * 
	 * @author Rafael Pinto
	 * @date 04/08/2006
	 * 
	 * @param equipeComponentes
	 */
	public void validarExibirInsercaoEspecificacaoImovSitCriterio(
			Collection colecaoEspecificacaoImovSitCriterio,
			EspecificacaoImovSitCriterio especImovSitCriterio)
			throws ControladorException {

		// Verificar objeto a ser inserido na base.
		if (especImovSitCriterio != null) {

			// [FS0002] Validar existência de hidrômetro na Ligação Água

			// Caso 1
			if (especImovSitCriterio.getLigacaoAguaSituacao() != null) {

				if (especImovSitCriterio.getLigacaoAguaSituacao().getId()
						.intValue() != LigacaoAguaSituacao.CORTADO.intValue()
						&& especImovSitCriterio.getLigacaoAguaSituacao()
								.getId().intValue() != LigacaoAguaSituacao.LIGADO
								.intValue()) {

					if (especImovSitCriterio
							.getIndicadorHidrometroLigacaoAgua() == null
							|| especImovSitCriterio
									.getIndicadorHidrometroLigacaoAgua()
									.shortValue() == ConstantesSistema.SIM
									.shortValue()) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.inserir_especificacao_situacao_imovel_ligacao_agua",
								null, "");

					}

				}

				// Caso 2
			} else {
				if (especImovSitCriterio.getIndicadorHidrometroLigacaoAgua() != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.inserir_especificacao_situacao_imovel_ligacao_agua",
							null, "");
				}
			}

			// [FS0003] Validar existência de hidrômetro no Poço

			// Caso 1
			if (especImovSitCriterio.getLigacaoEsgotoSituacao() != null) {

				if (especImovSitCriterio.getLigacaoEsgotoSituacao().getId()
						.intValue() != LigacaoEsgotoSituacao.TAMPONADO
						.intValue()
						&& especImovSitCriterio.getLigacaoEsgotoSituacao()
								.getId().intValue() != LigacaoEsgotoSituacao.LIGADO
								.intValue()) {

					if (especImovSitCriterio.getIndicadorHidrometroPoco() == null
							|| especImovSitCriterio
									.getIndicadorHidrometroPoco().shortValue() == ConstantesSistema.SIM
									.shortValue()) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.inserir_especificacao_situacao_imovel_ligacao_esgoto",
								null, "");

					}

				}

				// Caso 2
			} else {
				if (especImovSitCriterio.getIndicadorHidrometroPoco() != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.inserir_especificacao_situacao_imovel_ligacao_esgoto",
							null, "");
				}
			}

			// Testar se nova especificacao pode ser inserido na coleção
			if (colecaoEspecificacaoImovSitCriterio != null
					&& !colecaoEspecificacaoImovSitCriterio.isEmpty()) {

				// Varre coleção de especificacao da grid (ainda não inseridos
				// na base)
				for (Iterator iter = colecaoEspecificacaoImovSitCriterio
						.iterator(); iter.hasNext();) {

					EspecificacaoImovSitCriterio element = (EspecificacaoImovSitCriterio) iter
							.next();

					// [FS0001] Validar especificação da situação já existente
					if (element.equals(especImovSitCriterio)) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.inserir_especificacao_situacao_imovel_criterio_ja_informado",
								null, "");
					}

				}// fim do for
			}
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.inserir_especificacao_situacao_imovel_invalida",
					null, "");
		}
	}

	/**
	 * [UC0365] Efetuar Remanejamento de hidrômetro [SB0001] Atualizar Histórico
	 * de instalação do hidrômetro
	 * 
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/06/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @throws ControladorException
	 */

	public void efetuarRemanejamentoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper
				.getHidrometroInstalacaoHistorico();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();

		this.getControladorMicromedicao().validarImovelEmCampo(hidrometroInstalacaoHistorico.getLigacaoAgua().getId());
		
		this.verificarHidrometroInstalacaoHistoricoControleConcorrencia(hidrometroInstalacaoHistorico);

		getControladorUtil().atualizar(hidrometroInstalacaoHistorico);

		ordemServico.setUltimaAlteracao(new Date());

		// [SB006]Atualizar Ordem de Serviço
		if (!integracaoComercialHelper.isVeioEncerrarOS()
				&& ordemServico.getServicoTipo().getDebitoTipo() != null) {

			this.getControladorOrdemServico()
					.verificarOrdemServicoControleConcorrencia(ordemServico);

			getControladorOrdemServico().atualizaOSGeral(ordemServico);
		}

		if (ordemServico.getServicoTipo().getDebitoTipo() != null
				&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
			getControladorOrdemServico().gerarDebitoOrdemServico(
					ordemServico.getId(),
					ordemServico.getServicoTipo().getDebitoTipo().getId(),
					ordemServico.getValorAtual(),
					new Integer(qtdParcelas).intValue(),
					ordemServico.getPercentualCobranca().toString(),
					integracaoComercialHelper.getUsuarioLogado());
		}
	}

	/**
	 * [UC0357] Efetuar ReLigação de Água
	 * 
	 * Permite efetuar reLigação da Ligação de Água ou pelo menu ou pela
	 * funcionalidade encerrar a Execução da ordem de Serviço.
	 * 
	 * [SB0001] Atualizar Imóvel/Ligação de Água/Ligação de Esgoto
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/07/2006
	 * 
	 * @param ordemServico
	 * 
	 * @throws ControladorException
	 */
	public void efetuarReligacaoAgua(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		LigacaoAgua ligacaoAgua = integracaoComercialHelper.getLigacaoAgua();

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR, ligacaoAgua.getId(),
				ligacaoAgua.getId(), new UsuarioAcaoUsuarioHelper(
						integracaoComercialHelper.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		// [UC0107] -Fim- Registrar Transação

		// [SB0001] - Atualizar Imóvel/Ligação de Água/Ligação de Esgoto

		// Caso 1
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);
		ligacaoAguaSituacao.setUltimaAlteracao(new Date());

		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		imovel.setUltimaAlteracao(new Date());

		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		
		this.getControladorImovel().verificarImovelControleConcorrencia(imovel);
		// this.getControladorUtil().atualizar(imovel);

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = imovel
				.getLigacaoEsgotoSituacao();
		if (ligacaoEsgotoSituacao != null
				&& ligacaoEsgotoSituacao.getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO
						.intValue()) {

			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			ligacaoEsgotoSituacao.setUltimaAlteracao(new Date());

			// Colocado por Raphael Rossiter em 09/05/2007
			imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			// Comentado por Raphael Rossiter em 09/05/2007
			// this.getControladorUtil().atualizar(ligacaoEsgotoSituacao);
		}

		// Caso 2
		ligacaoAgua.setUltimaAlteracao(new Date());

		this.verificarLigacaoAguaControleConcorrencia(ligacaoAgua);

		registradorOperacao.registrarOperacao(ligacaoAgua);
		getControladorTransacao().registrarTransacao(ligacaoAgua);

		registradorOperacao.registrarOperacao(imovel);
		getControladorTransacao().registrarTransacao(imovel);

		getControladorImovel().atualizarImovelExecucaoOrdemServicoLigacaoAgua(
				imovel, ligacaoAguaSituacao);
		getControladorImovel()
				.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel,
						ligacaoEsgotoSituacao);

		this.getControladorLigacaoAgua().atualizarLigacaoAguaReligacao(
				ligacaoAgua);

		ordemServico.setUltimaAlteracao(new Date());

		if (!integracaoComercialHelper.isVeioEncerrarOS()
				&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
			this.getControladorOrdemServico()
					.verificarOrdemServicoControleConcorrencia(ordemServico);

			getControladorOrdemServico().atualizaOSGeral(ordemServico);
		}

		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();

		if (ordemServico.getServicoTipo().getDebitoTipo() != null
				&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
			getControladorOrdemServico().gerarDebitoOrdemServico(
					ordemServico.getId(),
					ordemServico.getServicoTipo().getDebitoTipo().getId(),
					ordemServico.getValorAtual(), new Integer(qtdParcelas),
					ordemServico.getPercentualCobranca().toString(),
					integracaoComercialHelper.getUsuarioLogado());
		}

	}

	/**
	 * [UC0363] Efetuar Retirada de hidrômetro [SB0001] Atualizar Histórico de
	 * instalação do hidrômetro
	 * 
	 * @author Thiago Tenório
	 * @date 30/06/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @throws ControladorException
	 */

	public void efetuarRetiradaHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = 
			integracaoComercialHelper.getHidrometroInstalacaoHistorico();
		
		hidrometroInstalacaoHistorico.setDataRetirada(new Date());
		
		this.getControladorMicromedicao().validarImovelEmCampo(hidrometroInstalacaoHistorico.getLigacaoAgua().getId());
		
		this.verificarHidrometroInstalacaoHistoricoControleConcorrencia(hidrometroInstalacaoHistorico);

		getControladorUtil().atualizar(hidrometroInstalacaoHistorico);

		this.verificarHidrometroControleConcorrencia(hidrometroInstalacaoHistorico.getHidrometro());

		getControladorUtil().atualizar(hidrometroInstalacaoHistorico.getHidrometro());

		try {
			// Caso o tipo de medição seja igual a Ligação de Água, atualiza as
			// colunas da tabela LIGACAO_AGUA
			// Integer id = hidrometroInstalacaoHistorico.getId();
			if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId().equals(
					MedicaoTipo.LIGACAO_AGUA)) {

				repositorioAtendimentoPublico
						.atualizarHidrometroInstalacaoHistoricoLigacaoAgua(
								hidrometroInstalacaoHistorico.getLigacaoAgua()
										.getId(), null);

				// Caso o tipo de medição seja igual a Poço, atualiza as colunas
				// da tabela POCO
			} else if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId()
					.equals(MedicaoTipo.POCO)) {

				repositorioAtendimentoPublico
						.atualizarHidrometroIntalacaoHistoricoImovel(
								hidrometroInstalacaoHistorico.getImovel()
										.getId(), null, null);
			}

			OrdemServico ordemServico = integracaoComercialHelper
					.getOrdemServico();
			if (ordemServico != null) {
				// [SB006]Atualizar Ordem de Serviço
				if (!integracaoComercialHelper.isVeioEncerrarOS()
						&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
					getControladorOrdemServico().atualizaOSGeral(
							integracaoComercialHelper.getOrdemServico());
				}

				if (ordemServico.getServicoTipo().getDebitoTipo() != null
						&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
					getControladorOrdemServico().gerarDebitoOrdemServico(
							ordemServico.getId(),
							ordemServico.getServicoTipo().getDebitoTipo()
									.getId(),
							ordemServico.getValorAtual(),
							new Integer(integracaoComercialHelper
									.getQtdParcelas()),
							ordemServico.getPercentualCobranca().toString(),
							integracaoComercialHelper.getUsuarioLogado());
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0365] Efetuar Instalação de hidrômetro
	 * 
	 * [SB0001] Gerar Histórico de instalação do hidrômetro [SB0002] Atualizar
	 * Imóvel/Ligação de Água [SB0003] Atualizar situação de hidrômetro na
	 * tabela HIDROMETRO
	 * 
	 * @author Ana Maria, Ivan Sérgio
	 * @date 12/07/2006, 24/03/2008
	 * @alteracao: Retirar os dois indicadores: indicadorTrocaProtecao e
	 *             indicadorTrocaRegistro; Gerar Boletim de Ordens de Servico
	 *             Concluidas;
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @param materialImovel
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */

	public void efetuarInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		Integer id = null;

		
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper
				.getHidrometroInstalacaoHistorico();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, integracaoComercialHelper.getImovel().getId()));
		Collection colecaoImovel = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());

		Iterator iColecaoImovel = colecaoImovel.iterator();
		Imovel imovel = (Imovel) iColecaoImovel.next();
		
		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());

		validacaoInstalacaoHidrometro(hidrometroInstalacaoHistorico.getHidrometro().getNumero());

		/*
		 * [UC0107] Registrar Transação
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR,
				imovel.getId(), imovel.getId(), new UsuarioAcaoUsuarioHelper(
						integracaoComercialHelper.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// [UC0107] -Fim- Registrar Transação

		// regitrando operacao
		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		// Retirar os dois indicadores: indicadorTrocaProtecao e
		// indicadorTrocaRegistro;
		Short indicadorTrocaProtecao = hidrometroInstalacaoHistorico
				.getIndicadorTrocaProtecao();
		hidrometroInstalacaoHistorico
				.setIndicadorTrocaProtecao(ConstantesSistema.NAO);
		Short indicadorTrocaRegistro = hidrometroInstalacaoHistorico
				.getIndicadorTrocaRegistro();
		hidrometroInstalacaoHistorico
				.setIndicadorTrocaRegistro(ConstantesSistema.NAO);

		id = (Integer) getControladorUtil().inserir(
				hidrometroInstalacaoHistorico);

		// [SB0002]Atualizar Imóvel/Ligação de Água
		try {
			// Caso o tipo de medição seja igual a Ligação de Água, atualiza as
			// colunas da tabela LIGACAO_AGUA
			if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId().equals(
					MedicaoTipo.LIGACAO_AGUA)) {
				repositorioAtendimentoPublico
						.atualizarHidrometroInstalacaoHistoricoLigacaoAgua(
								hidrometroInstalacaoHistorico.getLigacaoAgua()
										.getId(), id);
				// Caso o tipo de medição seja igual a Poço, atualiza as colunas
				// da tabela POCO
			} else if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId()
					.equals(MedicaoTipo.POCO)) {
				repositorioAtendimentoPublico
						.atualizarHidrometroIntalacaoHistoricoImovel(
								hidrometroInstalacaoHistorico.getImovel()
										.getId(), id, imovel.getPocoTipo().getId());
			}

			// [SB003]Atualizar situação de hidrômetro na tabela HIDROMETRO
			Integer situacaoHidrometro = HidrometroSituacao.INSTALADO;
			repositorioAtendimentoPublico.atualizarSituacaoHidrometro(
					hidrometroInstalacaoHistorico.getHidrometro().getId(),
					situacaoHidrometro);

			// [SB006]Atualizar Ordem de Serviço
			if (!integracaoComercialHelper.isVeioEncerrarOS()
					&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
				this
						.getControladorOrdemServico()
						.verificarOrdemServicoControleConcorrencia(ordemServico);
				getControladorOrdemServico().atualizaOSGeral(ordemServico);
			}

			// Gerar Boletim Ordens de Servico Concluida
			// Caso a Ordem de Servico nao esteja Associada a Documento de
			// Cobranca
			// nem a Registro de Atendimento

			boolean osAssociadaDOC = getControladorOrdemServico()
					.verificarOSAssociadaDocumentoCobranca(ordemServico.getId());
			boolean osAssociadaRA = getControladorOrdemServico()
					.verificarOSAssociadaRA(ordemServico.getId());

			if (!osAssociadaDOC && !osAssociadaRA) {
				// Recupera a data de Encerramento da OS
				FiltroOrdemServico filtroOs = new FiltroOrdemServico();
				filtroOs.adicionarParametro(new ParametroSimples(
						FiltroOrdemServico.ID, ordemServico.getId()));

				Collection colecaoDados = getControladorUtil().pesquisar(
						filtroOs, OrdemServico.class.getName());
				Iterator iColecaoDados = colecaoDados.iterator();
				OrdemServico os = (OrdemServico) iColecaoDados.next();

				Date dataEncerramentoOs = os.getDataEncerramento();

				// **************************************************************
				// Alterado por: Ivan Sergio
				// Data: 12/02/2009
				// CRC1222 - Seta a data de encerramento com o valor do Helper
				// de
				// integracao.
				// **************************************************************
				if (dataEncerramentoOs == null)
					dataEncerramentoOs = ordemServico.getDataEncerramento();
				// **************************************************************

				BoletimOsConcluida boletim = new BoletimOsConcluida();
				boletim.setId(ordemServico.getId());
				boletim.setOrdemServico(ordemServico);
				boletim.setLocalidade(ordemServico.getImovel().getLocalidade());
				boletim.setAnoMesReferenciaBoletim(Util
						.getAnoMesComoInt(dataEncerramentoOs));
				boletim.setCodigoFiscalizacao(new Short("0"));
				boletim.setUsuario(null);
				boletim.setDataFiscalizacao(null);
				boletim.setDataEncerramentoBoletim(null);
				boletim
						.setIndicadorTrocaProtecaoHidrometro(indicadorTrocaProtecao);
				boletim
						.setIndicadorTrocaRegistroHidrometro(indicadorTrocaRegistro);
				boletim
						.setHidrometroLocalInstalacao(hidrometroInstalacaoHistorico
								.getHidrometroLocalInstalacao());
				boletim.setUltimaAlteracao(new Date());
				getControladorUtil().inserir(boletim);
			}

			if (ordemServico.getServicoTipo().getDebitoTipo() != null
					&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
				getControladorOrdemServico().gerarDebitoOrdemServico(
						ordemServico.getId(),
						ordemServico.getServicoTipo().getDebitoTipo().getId(),
						ordemServico.getValorAtual(),
						new Integer(qtdParcelas).intValue(),
						ordemServico.getPercentualCobranca().toString(),
						integracaoComercialHelper.getUsuarioLogado());
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0362] Efetuar Instalacao de hidrômetro
	 * 
	 * Validar Instalacao de hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 13/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param tipoMedicao
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validacaoInstalacaoHidrometro(String numeroHidrometro)
			throws ControladorException {

		//Hidrometro hidrometro = getControladorMicromedicao()
		//		.pesquisarHidrometroPeloNumero(numeroHidrometro);

		 FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
		
		 filtroHidrometro
		 .adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
		 filtroHidrometro.adicionarParametro(new ParametroSimples(
		 FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
		
		 Collection colecaoHidrometro = null;
		
		 colecaoHidrometro = getControladorUtil().pesquisar(filtroHidrometro,
		 Hidrometro.class.getName());
		
		 // [FS002]Caso o hidrômetro informado esteja com a situação diferente
		 // de
		 // DISPONíVEL
		 Iterator iteratorHidrometro = colecaoHidrometro.iterator();
		 while (iteratorHidrometro.hasNext()) {
			 Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			 
			 Integer idSituacaoHidrometro = hidrometro.getHidrometroSituacao()
						.getId();
			if (!(idSituacaoHidrometro.equals(HidrometroSituacao.DISPONIVEL))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.hidrometro_situacao_indisponivel", null,
						hidrometro.getHidrometroSituacao().getDescricao());
			}
		 }
	}

	/**
	 * [UC0362] Efetuar Instalacao de hidrômetro
	 * 
	 * Validar Instalacao de hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 13/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param tipoMedicao
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validarExibirInstalacaoHidrometro(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR INSTALACAO HIDROMETRO, não será necessário realizar
		 * as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(
						ordemServico.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			int servicoTipo = ordemServico.getServicoTipo().getId().intValue();

			if (servicoTipo != ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO) {
				throw new ControladorException(
						"atencao.servico_associado_instalacao_hidrometro_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);
		// Caso 4
		/*
		 * Autor: Vivianne Sousa Data: 11/12/2007 Analista Responsavel: Denys
		 */
		Imovel imovel = null;
		if ((ordemServico.getRegistroAtendimento() != null && ordemServico
				.getRegistroAtendimento().getImovel() == null)
				&& ordemServico.getImovel() == null) {

			throw new ControladorException(
					"atencao.ordem_servico_imovel_invalida");
		} else {

			if (ordemServico.getImovel() != null) {
				imovel = ordemServico.getImovel();
			} else {
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			}
		}

		// [FS0002] Verificar Situação do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		// [FS0003] - Verificar Situação de Agua ou Esgoto.
		// [FS0004] - Verificar a existência de hidrômetro no Imóvel/Ligação de
		// Água

		/* HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null; */

		// Caso 1
		if (ordemServico.getRegistroAtendimento() == null
				|| ordemServico.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao()
						.getIndicadorLigacaoAgua().equals(
								MedicaoTipo.LIGACAO_AGUA.shortValue())) {
			LigacaoAguaSituacao ligacaoAguaSituacao = imovel
					.getLigacaoAguaSituacao();

			if (ligacaoAguaSituacao.getId().intValue() != LigacaoAguaSituacao.LIGADO &&
                    ligacaoAguaSituacao.getId().intValue() != LigacaoAguaSituacao.LIGADO_EM_ANALISE &&
                    	ligacaoAguaSituacao.getId().intValue() != LigacaoAguaSituacao.CORTADO) {

				throw new ControladorException(
						"atencao.instalacao_hidrometro_situacao_ligacao_agua_invalida",
						null, ligacaoAguaSituacao.getDescricao());
			}

			if (imovel.getLigacaoAgua() == null) {
				throw new ControladorException("atencao.naocadastrado", null,
						"Ligação de Água");
			}

			if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
				throw new ControladorException(
						"atencao.hidrometro_instalado_ligacao_agua", null, ""
								+ imovel.getId());
			}
		} else {

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = imovel
					.getLigacaoEsgotoSituacao();

			if (ligacaoEsgotoSituacao.getId().intValue() != LigacaoEsgotoSituacao.LIGADO) {

				throw new ControladorException(
						"atencao.situacao_instalacao_hidrometro_poco_invalida",
						null, ligacaoEsgotoSituacao.getDescricao());
			}

			if (imovel.getHidrometroInstalacaoHistorico() != null) {
				throw new ControladorException(
						"atencao.hidrometro_instalado_poco", null, ""
								+ imovel.getId());
			}
		}

		// [FS0002] Verificar Situação do hidrometro.
		/*
		 * Hidrometro hidrometro =
		 * hidrometroInstalacaoHistorico.getHidrometro();
		 * 
		 * if (hidrometro.getHidrometroSituacao().getId().intValue() !=
		 * HidrometroSituacao.DISPONIVEL .intValue()) { throw new
		 * ControladorException( "atencao.hidrometro_situacao_indisponivel",
		 * null, hidrometro.getHidrometroSituacao().getDescricao()); }
		 */

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * [UC0356] Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto
	 * 
	 * Permite Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto .
	 * 
	 * [FS0001]- Validar Ordem de Serviço. [FS0002] Verificar Situação do
	 * Imovel. [FS0003]- Validar Situação da Ligação de Esgoto do Imóvel.
	 * [FS0007]- Validar Situação da Ligação de Água do Imóvel. [FS0004 -
	 * Validar Volume Mínimo Fixado.
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * @param ordemServicoId
	 * @param imovel
	 * @param dataMudanca
	 * @param volumeMinimoFixado
	 * @param novaSituacaoEsgoto
	 * @throws ControladorException
	 */
	public String validarMudancaSituacaoFaturamentoLigacaoesgotoExibir(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException {

		String retorno = null;

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// [FS0001]- Validar Ordem de Serviço
		if (ordemServico == null || ordemServico.equals("")) {
			throw new ControladorException("atencao.ordem_nao_existente", null);
		}

		// [FS0001] Continuação
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		// Caso 4
		if (ordemServico.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordemServico.getRegistroAtendimento().getId());
		}

		// [FS0002] Verificar Situação do Imovel
		if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			throw new ControladorException("atencao.situacao.imovel.invalida",
					null, imovel.getId() + "");
		}

		// Validar Tipo de Serviço.
		if (ordemServico != null) {
			if (ordemServico.getServicoTipo().getId() != null) {
				// Tipo de Serviço.
				Integer tipoServico = ordemServico.getServicoTipo().getId();
				if (tipoServico.intValue() == ServicoTipo.TIPO_TAMPONAMENTO_LIGACAO_ESGOTO) {
					// [FS0007]- Validar Situação da Ligação de Água do Imóvel
					this.validarSituacaoAguaImovel(imovel, tipoServico);
					// [FS0003]- Validar Situação da Ligação de Esgoto do Imóvel
					this.validarSituacaoEsgotoImovel(imovel, tipoServico);

					return new String("TAMPONADO");

				} else if (tipoServico.intValue() == ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO) {
					// [FS0007]- Validar Situação da Ligação de Água do Imóvel
					this.validarSituacaoAguaImovel(imovel, tipoServico);
					// [FS0003]- Validar Situação da Ligação de Esgoto do Imóvel
					this.validarSituacaoEsgotoImovel(imovel, tipoServico);
					return new String("LIGADO FORA DE USO");
				} else if (tipoServico.intValue() == ServicoTipo.TIPO_RESTABELECIMENTO_LIGACAO_ESGOTO
						|| tipoServico.intValue() == ServicoTipo.TIPO_REATIVACAO_LIGACAO_ESGOTO) {
					// [FS0004 - Validar Volume Mínimo Fixado]
					this.validarSituacaoEsgotoImovel(imovel, tipoServico);

					return new String("LIGADO");
				} else {

					/*
					 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja
					 * associado a operação EFETUAR MUDANCA SITUACAO FATURAMENTO
					 * LIGACAO ESGOTO, não será necessário realizar as
					 * validações abaixo.
					 * 
					 * Autor: Raphael Rossiter Data: 26/04/2007
					 * 
					 */
					Integer idOperacao = this.getControladorOrdemServico()
							.pesquisarServicoTipoOperacao(
									ordemServico.getServicoTipo().getId());

					if (idOperacao == null
							|| idOperacao.intValue() != Operacao.OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO_INT) {

						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.servico_associado_atualizar_ligacao_esgoto_invalida");
					} else {
						return new String("");
					}
				}
			}
		}

		return retorno;
	}

	/**
	 * [UC0356] Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto
	 * 
	 * Permite Atualizar Ligação de Esgoto do Imóvel.
	 * 
	 * [FS0006]-Atualizar Ligação de Esgoto do Imóvel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * @param ordemServico
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	/*
	 * private void validarMudancaSituacaoFaturamentoLigacaoesgotoEfetuar(
	 * OrdemServico ordemServico) throws ControladorException {
	 * 
	 * Imovel imovel = ordemServico.getRegistroAtendimento().getImovel(); //
	 * Validar Tipo de Serviço. if (ordemServico != null) { if
	 * (ordemServico.getServicoTipo().getId() != null) { // Tipo de Serviço.
	 * Integer tipoServico = ordemServico.getServicoTipo().getId(); if
	 * (tipoServico.intValue() == ServicoTipo.TIPO_TAMPONAMENTO_LIGACAO_ESGOTO) {
	 * 
	 * LigacaoEsgotoSituacao ligacaoEsgotoimovel = new LigacaoEsgotoSituacao();
	 * ligacaoEsgotoimovel.setId(LigacaoEsgotoSituacao.TAMPONADO);
	 * imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoimovel);
	 * imovel.setUltimaAlteracao(new Date()); // 4.2.1.3 Caso usuário confirme a
	 * efetivação getControladorImovel()
	 * .atualizarImovelExecucaoOrdemServicoLigacaoEsgoto( imovel,
	 * ligacaoEsgotoimovel); } else if (tipoServico.intValue() ==
	 * ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO) {
	 * 
	 * LigacaoEsgotoSituacao ligacaoEsgotoimovel = new LigacaoEsgotoSituacao();
	 * ligacaoEsgotoimovel .setId(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
	 * imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoimovel);
	 * imovel.setUltimaAlteracao(new Date()); // 4.2.1.3 Caso usuário confirme a
	 * efetivação getControladorImovel()
	 * .atualizarImovelExecucaoOrdemServicoLigacaoEsgoto( imovel,
	 * ligacaoEsgotoimovel); } else if (tipoServico.intValue() ==
	 * ServicoTipo.TIPO_RESTABELECIMENTO_LIGACAO_ESGOTO) { // [FS0006]-Atualizar
	 * Ligação de Esgoto do Imóvel LigacaoEsgotoSituacao ligacaoEsgotoimovel =
	 * new LigacaoEsgotoSituacao();
	 * ligacaoEsgotoimovel.setId(LigacaoEsgotoSituacao.LIGADO);
	 * imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoimovel);
	 * imovel.setUltimaAlteracao(new Date()); // 4.2.1.3 Caso usuário confirme a
	 * efetivação getControladorImovel()
	 * .atualizarImovelExecucaoOrdemServicoLigacaoEsgoto( imovel,
	 * ligacaoEsgotoimovel); } else if (tipoServico.intValue() ==
	 * ServicoTipo.TIPO_REATIVACAO_LIGACAO_ESGOTO) { // [FS0006]-Atualizar
	 * Ligação de Esgoto do Imóvel LigacaoEsgotoSituacao ligacaoEsgotoimovel =
	 * new LigacaoEsgotoSituacao();
	 * ligacaoEsgotoimovel.setId(LigacaoEsgotoSituacao.LIGADO);
	 * imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoimovel);
	 * imovel.setUltimaAlteracao(new Date()); // 4.2.1.3 Caso usuário confirme a
	 * efetivação getControladorImovel()
	 * .atualizarImovelExecucaoOrdemServicoLigacaoEsgoto( imovel,
	 * ligacaoEsgotoimovel); } } } }
	 */

	/**
	 * [UC0356] Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto
	 * 
	 * Permite Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto .
	 * 
	 * [FS0001]- Validar Ordem de Serviço [FS0002] Verificar Situação do Imovel
	 * [FS0002] Verificar Situação do Imovel [FS0003]- Validar Situação da
	 * Ligação de Esgoto do Imóvel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * @param ordemServicoId
	 * @param imovel
	 * @param dataMudanca
	 * @param volumeMinimoFixado
	 * @param novaSituacaoEsgoto
	 * @throws ControladorException
	 */
	public void efetuarMudancaSituacaoFaturamentoLiagacaoEsgoto(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		// item 4.2.1 nova Situação do Esgoto igual Tamponamento da Ligação de
		// Esgoto
		/* Sistema deve atualizar a situação de esgoto do Imóvel para tamponado */

		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		LigacaoEsgoto ligacaoEsgoto = integracaoComercialHelper
				.getLigacaoEsgoto();

		// Controle de concorrencia
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
		filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgoto.ID, ligacaoEsgoto.getId()));
		Collection colecaoEsgotoBase = getControladorUtil().pesquisar(
				filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());

		if (!colecaoEsgotoBase.isEmpty()) {
			LigacaoEsgoto ligacaoEsgotoBase = (LigacaoEsgoto) Util
					.retonarObjetoDeColecao(colecaoEsgotoBase);

			if (ligacaoEsgotoBase.getUltimaAlteracao().after(
					ligacaoEsgoto.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				ordemServico.getRegistroAtendimento().getImovel().getId()));
		Collection colecaoImovelBase = getControladorUtil().pesquisar(
				filtroImovel, Imovel.class.getName());
		if (!colecaoImovelBase.isEmpty()) {
			Imovel imovelBase = (Imovel) Util
					.retonarObjetoDeColecao(colecaoImovelBase);

			if (imovelBase.getUltimaAlteracao().after(
					ordemServico.getRegistroAtendimento().getImovel()
							.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

		switch (ordemServico.getServicoTipo().getId()) {
		case ServicoTipo.TIPO_TAMPONAMENTO_LIGACAO_ESGOTO:
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.TAMPONADO);
			break;

		case ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO:
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			break;

		case ServicoTipo.TIPO_RESTABELECIMENTO_LIGACAO_ESGOTO:
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			break;

		case ServicoTipo.TIPO_REATIVACAO_LIGACAO_ESGOTO:
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			break;
		}

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO,
				ligacaoEsgoto.getId(), ligacaoEsgoto.getId(),
				new UsuarioAcaoUsuarioHelper(integracaoComercialHelper
						.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// regitrando operacao
		registradorOperacao.registrarOperacao(ordemServico);
		registradorOperacao.registrarOperacao(imovel);
		getControladorTransacao().registrarTransacao(imovel);
		// [UC0107] -Fim- Registrar Transação

		getControladorImovel()
				.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel,
						ligacaoEsgotoSituacao);

		ordemServico.setUltimaAlteracao(new Date());

		if (!integracaoComercialHelper.isVeioEncerrarOS()) {
			this.getControladorOrdemServico()
					.verificarOrdemServicoControleConcorrencia(ordemServico);

			getControladorOrdemServico().atualizaOSGeral(ordemServico);
		}

		if (ordemServico.getServicoTipo().getDebitoTipo() != null
				&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
			getControladorOrdemServico().gerarDebitoOrdemServico(
					ordemServico.getId(),
					ordemServico.getServicoTipo().getDebitoTipo().getId(),
					ordemServico.getValorAtual(),
					new Integer(integracaoComercialHelper.getQtdParcelas())
							.intValue(),
					ordemServico.getPercentualCobranca().toString(),
					integracaoComercialHelper.getUsuarioLogado());
		}

	}

	/**
	 * [UC0356]- Efetuar mudança de Faturamento na Ligação de Água
	 * [FS0006]-Atualizar Ligação de Esgoto
	 * 
	 * Permite atualizar a Tabele de Ligação Esdoto . Update LIGACAO_ESGOTO
	 * LESG_NNCONSUMOMINIMOESGOTO (volume Mínimo fixado) LESG_TMULTIMAALTERADAO
	 * (data e hora correntes) Where LESG_ID=IMOV_ID da tabela IMOVEL
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * 
	 * @param imovel
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	public void atualizarLigacaoEsgoto(Imovel imovel, String volumeMinimoFixado)
			throws ControladorException {

		// Ligação de Esgoto

		String idImovel = imovel.getId().toString();

		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();

		filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgoto.ID, idImovel));

		// filtroLigacaoEsgoto
		// .adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");

		/*
		 * Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(
		 * filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
		 */
		Collection colecaoLigacaoEsgoto = new ArrayList();
		LigacaoEsgoto ligacaoEsgoto = null;

		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		
		if (!Util.isVazioOrNulo(colecaoLigacaoEsgoto)) {
			ligacaoEsgoto = (LigacaoEsgoto) colecaoLigacaoEsgoto.iterator()
					.next();
		}

		if (Util.verificarNaoVazio(volumeMinimoFixado) && ligacaoEsgoto != null) {
			Integer volumeMinimoFixadoInformado = new Integer(
					volumeMinimoFixado);
			// Atualizando campos da tabela LigacaoEsgoto
			ligacaoEsgoto.setConsumoMinimo(volumeMinimoFixadoInformado);
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// Atualiza tabela LigacaoAgua
			getControladorUtil().atualizar(ligacaoEsgoto);
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.requerid", null,
					"Volume Mínimo Fixado");
		}
	}

	/**
	 * [UC0368] Atualizar Instalação do hidrômetro
	 * 
	 * [FS0001] - Verificar a existência da matrícula do Imóvel [FS0002] -
	 * Verificar a situação do Imóvel [FS0003] - Validar existência do
	 * hidrômetro [FS0004] - Validar leitura instalação hidrômetro [FS0005] -
	 * Validar leitura retirada hidrômetro [FS0006] - Validar leitura retirada
	 * corte [FS0007] - Validar Leitura Supressão [FS0009] - Verificar sucesso
	 * da transação
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void atualizarInstalacaoHidrometro(Imovel imovel,
			Integer medicaoTipo, Usuario usuario) throws ControladorException {

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
		
		Imovel atualizarPocoTipoImovel = null;
		
		atualizarPocoTipoImovel = 
			getControladorImovel().pesquisarImovel(imovel.getId());

		if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {
			hidrometroInstalacaoHistorico = imovel.getLigacaoAgua()
					.getHidrometroInstalacaoHistorico();
		} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {
			hidrometroInstalacaoHistorico = imovel
					.getHidrometroInstalacaoHistorico();
			
			atualizarPocoTipoImovel.setPocoTipo(imovel.getPocoTipo());
		}

		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
		
		
		// [FS0001] - Verificar a existência da matrícula do Imóvel
		// [FS0002] - Verificar a situação do Imóvel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				imovel.getId()));
		getControladorImovel().pesquisarImovelSituacaoAtiva(filtroImovel);

		// [FS0003] - Validar existência do hidrômetro
		getControladorImovel().validarExistenciaHidrometro(imovel, medicaoTipo);

		// [FS0004] - Validar leitura instalação hidrômetro

		/*
		 * Caso a leitura da instalção do hidrômetro informada seja igual a zero
		 * ou valores negativos, exibir a mensagem: "Leitura instalação deve
		 * somente conter números positivos"
		 */
		if (!Util.validarNumeroMaiorQueZERO(hidrometroInstalacaoHistorico
				.getNumeroLeituraInstalacao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.long", null,
					"Leitura de Instalação");
		}

		// [FS0005] - Validar leitura retirada hidrômetro

		if (hidrometroInstalacaoHistorico.getDataRetirada() == null) {

			/*
			 * Caso a data de retirada do hidrômetro não esteja informada, o
			 * sistema não deve deixar infomar a leitura de retirada do
			 * hidrômetro <<Qual a mensagem???>>
			 */

		} else {

			/*
			 * Caso a data de retirada do hidrômetro esteja informada e o
			 * usuário informar a leitura de retirada do hidrômetro igual a
			 * zeros ou valores negativos, exibir a mensagem: "Leitura de
			 * retirada do hidrômetro deve somente conter números positivos"
			 */

			if (!Util.validarNumeroMaiorQueZERO(hidrometroInstalacaoHistorico
					.getNumeroLeituraRetirada())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.long", null,
						"Leitura de Retirada");
			}

		}

		// [FS0006] - Validar leitura retirada corte

		if (imovel.getLigacaoAgua().getDataCorte() == null) {

			/*
			 * Caso a data do corte não esteja informada para o Imóvel, o
			 * sistema não deve deixar infomar a leitura de corte do hidrômetro <<Qual
			 * a mensagem???>>
			 */

		} else {

			/*
			 * Caso a data do corte esteja informada para o Imóvel e o usuário
			 * informar a leitura de corte da Ligação de Água igual a zeros ou
			 * valores negativos, exibir a mensagem: "Leitura de Corte da
			 * Ligação de Água deve somente conter números positivos"
			 */

			if (!Util.validarNumeroMaiorQueZERO(hidrometroInstalacaoHistorico
					.getNumeroLeituraCorte())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.long", null,
						"Leitura de Corte");
			}

		}

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR, imovel
						.getId(), imovel.getId(), new UsuarioAcaoUsuarioHelper(
						usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		// ------------ REGISTRAR TRANSAÇÃO ----------------

		// [FS0009] - Verificar sucesso da transação
		getControladorUtil().atualizar(hidrometroInstalacaoHistorico);
		getControladorUtil().atualizar(atualizarPocoTipoImovel);

	}

	/**
	 * [UC0356]- Efetuar mudança de Faturamento na Ligação de Água
	 * 
	 * [FS0007]- Validar Situação da Ligação de Água do Imóvel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * @param imovel
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	public String validarSituacaoAguaImovel(Imovel imovel, Integer tipoServico)
			throws ControladorException {
		String retorno = null;
		if (tipoServico.intValue() == ServicoTipo.TIPO_TAMPONAMENTO_LIGACAO_ESGOTO) {
			if (imovel.getLigacaoAguaSituacao().getId().equals(
					LigacaoAguaSituacao.LIGADO)
					|| imovel.getLigacaoAguaSituacao().getId().equals(
							LigacaoAguaSituacao.CORTADO)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.situacao.ligacaoagua.imovel.tamponamento.invalida",
						null, imovel.getId().toString());

			} else {
				retorno = new String("TAMPONADO");
			}
		} else if (tipoServico.intValue() == ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO) {
			if (imovel.getLigacaoAguaSituacao().getId().equals(
					LigacaoAguaSituacao.LIGADO)
					|| imovel.getLigacaoAguaSituacao().getId().equals(
							LigacaoAguaSituacao.CORTADO)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.situacao.ligacaoagua.imovel.desativacao.invalida",
						null, imovel.getId().toString());

			} else {
				retorno = new String("LIGADO FORA DE USO");
			}
		}
		return retorno;
	}

	/**
	 * [UC0356]- Efetuar mudança de Faturamento na Ligação de Água
	 * 
	 * [FS0003]- Validar Situação da Ligação de Esgoto do Imóvel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * @param imovel
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	public void validarSituacaoEsgotoImovel(Imovel imovel, Integer tipoServico)
			throws ControladorException {

		if (tipoServico.intValue() == LigacaoEsgotoSituacao.SITUACAO_TAMPONADO
				.intValue()
				&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIGADO)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.situacao_tamponamento_ligacao_esgoto_imovel_invalida",
					null, tipoServico.toString());
		} else if (tipoServico.intValue() == LigacaoEsgotoSituacao.SITUACAO_DESATIVACAO
				.intValue()
				&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIGADO)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.situacao_desativacao_ligacao_esgoto_imovel_invalida",
					null, tipoServico.toString());
		} else if (tipoServico.intValue() == LigacaoEsgotoSituacao.SITUACAO_RESTABELECIMENTO
				.intValue()
				&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.TAMPONADO)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.situacao_restabelecimento_ligacao_esgoto_imovel_invalida",
					null, tipoServico.toString());
		} else if (tipoServico.intValue() == LigacaoEsgotoSituacao.SITUACAO_REATIVACAO
				.intValue()
				&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIG_FORA_DE_USO)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.situacao_restabelecimento_ligacao_esgoto_imovel_invalida",
					null, tipoServico.toString());
		}
	}

	/**
	 * [UC0364] Efetuar Substituição de hidrômetro
	 * [SB0001] Atualizar o histórico da instalação do hidrômetro substituido
	 * [SB0002] Gerar Histórico de instalação do hidrômetro [SB0003] Atualizar
	 * Imóvel/Ligação de Água [SB0004] Atualizar situação de hidrômetro na
	 * tabela HIDROMETRO [SB0005] Atualizar situação do hidrômetro substituido
	 * na tabela HIDROMETRO
	 */
	@SuppressWarnings("rawtypes")
	public void efetuarSubstituicaoHidrometro(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException {

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper.getHidrometroInstalacaoHistorico();
		String matriculaImovel = integracaoComercialHelper.getMatriculaImovel();
		
		HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico = integracaoComercialHelper.getHidrometroSubstituicaoHistorico();
		String situacaoHidrometroSubstituido = integracaoComercialHelper.getSituacaoHidrometroSubstituido();
		
		Integer localArmazenagemHidrometro = null;
		
		if(integracaoComercialHelper != null && integracaoComercialHelper.getLocalArmazenagemHidrometro() != null){
		 localArmazenagemHidrometro = integracaoComercialHelper.getLocalArmazenagemHidrometro();
		}
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();

		Integer id = null;

		validacaoSubstituicaoHidrometro(matriculaImovel, hidrometroInstalacaoHistorico.getHidrometro().getNumero(), situacaoHidrometroSubstituido);
		
		if ( integracaoComercialHelper.getUsuarioLogado() != null ){
			hidrometroSubstituicaoHistorico.setUsuarioRetirada( integracaoComercialHelper.getUsuarioLogado() );
		}else{
			hidrometroSubstituicaoHistorico.setUsuarioRetirada(null);
		}
		
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR, 
				Integer.parseInt(matriculaImovel), 
				Integer.parseInt(matriculaImovel),
				new UsuarioAcaoUsuarioHelper(integracaoComercialHelper.getUsuarioLogado(), UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		try {
			registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

			if ( integracaoComercialHelper.getUsuarioLogado() != null ){
				hidrometroSubstituicaoHistorico.setUsuarioRetirada(integracaoComercialHelper.getUsuarioLogado());
			}else{
				hidrometroSubstituicaoHistorico.setUsuarioRetirada(null);
			}
			
			repositorioAtendimentoPublico.atualizarHidrometroInstalacoHistorico(hidrometroSubstituicaoHistorico);

			Short indicadorTrocaProtecao = hidrometroInstalacaoHistorico.getIndicadorTrocaProtecao();
			hidrometroInstalacaoHistorico.setIndicadorTrocaProtecao(ConstantesSistema.NAO);
			
			Short indicadorTrocaRegistro = hidrometroInstalacaoHistorico.getIndicadorTrocaRegistro();
			hidrometroInstalacaoHistorico.setIndicadorTrocaRegistro(ConstantesSistema.NAO);

			hidrometroInstalacaoHistorico.setIndicadorInstalcaoSubstituicao(ConstantesSistema.NAO);
			
			if ( integracaoComercialHelper.getUsuarioLogado() != null ){
				hidrometroInstalacaoHistorico.setUsuarioInstalacao(integracaoComercialHelper.getUsuarioLogado());
			}else{
				hidrometroInstalacaoHistorico.setUsuarioInstalacao(null);
			}
			
			hidrometroInstalacaoHistorico.setUsuarioRetirada(null);
			
			id = (Integer) getControladorUtil().inserir(hidrometroInstalacaoHistorico);

			// [SB0003]Atualizar Imóvel/Ligação de Água
			if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId().equals(MedicaoTipo.LIGACAO_AGUA)) {
				repositorioAtendimentoPublico.atualizarHidrometroInstalacaoHistoricoLigacaoAgua(hidrometroInstalacaoHistorico.getLigacaoAgua().getId(), id);

			} else if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId().equals(MedicaoTipo.POCO)) {
				repositorioAtendimentoPublico.atualizarHidrometroIntalacaoHistoricoImovel(hidrometroInstalacaoHistorico.getImovel().getId(), id, null);
			}

			// [SB004]Atualizar situação de hidrômetro na tabela HIDROMETRO
			Integer situacaoHidrometro = HidrometroSituacao.INSTALADO;
			repositorioAtendimentoPublico.atualizarSituacaoHidrometro(hidrometroInstalacaoHistorico.getHidrometro().getId(),situacaoHidrometro);

			situacaoHidrometro = new Integer(situacaoHidrometroSubstituido);
			repositorioAtendimentoPublico.atualizarSituacaoHidrometro(hidrometroSubstituicaoHistorico.getHidrometro().getId(), situacaoHidrometro);

			if(localArmazenagemHidrometro != null){
			repositorioAtendimentoPublico.atualizarLocalArmazanagemHidrometro(hidrometroSubstituicaoHistorico.getHidrometro().getId(), localArmazenagemHidrometro);
			}

			if (!integracaoComercialHelper.isVeioEncerrarOS()) {
				this.getControladorOrdemServico().verificarOrdemServicoControleConcorrencia(ordemServico);
				getControladorOrdemServico().atualizaOSGeral(ordemServico);
			}

			boolean osAssociadaDOC = getControladorOrdemServico().verificarOSAssociadaDocumentoCobranca(ordemServico.getId());
			boolean osAssociadaRA = getControladorOrdemServico().verificarOSAssociadaRA(ordemServico.getId());

			if (!osAssociadaDOC && !osAssociadaRA) {
				FiltroOrdemServico filtroOs = new FiltroOrdemServico();
				filtroOs.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, ordemServico.getId()));

				Collection colecaoDados = getControladorUtil().pesquisar(filtroOs, OrdemServico.class.getName());
				Iterator iColecaoDados = colecaoDados.iterator();
				OrdemServico os = (OrdemServico) iColecaoDados.next();

				Date dataEncerramentoOs = os.getDataEncerramento();

				if (dataEncerramentoOs == null)
					dataEncerramentoOs = ordemServico.getDataEncerramento();

				BoletimOsConcluida boletim = new BoletimOsConcluida();
				boletim.setId(ordemServico.getId());
				boletim.setOrdemServico(ordemServico);
				boletim.setLocalidade(ordemServico.getImovel().getLocalidade());
				boletim.setAnoMesReferenciaBoletim(Util.getAnoMesComoInt(dataEncerramentoOs));
				boletim.setCodigoFiscalizacao(new Short("0"));
				boletim.setUsuario(null);
				boletim.setDataFiscalizacao(null);
				boletim.setDataEncerramentoBoletim(null);
				boletim.setIndicadorTrocaProtecaoHidrometro(indicadorTrocaProtecao);
				boletim.setIndicadorTrocaRegistroHidrometro(indicadorTrocaRegistro);
				boletim.setHidrometroLocalInstalacao(hidrometroInstalacaoHistorico.getHidrometroLocalInstalacao());
				boletim.setUltimaAlteracao(new Date());
				getControladorUtil().inserir(boletim);
			}

			if (ordemServico.getServicoTipo().getDebitoTipo() != null
					&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
				getControladorOrdemServico().gerarDebitoOrdemServico(
						ordemServico.getId(),
						ordemServico.getServicoTipo().getDebitoTipo().getId(),
						ordemServico.getValorAtual(),
						new Integer(integracaoComercialHelper.getQtdParcelas()),
						ordemServico.getPercentualCobranca().toString(),
						integracaoComercialHelper.getUsuarioLogado());
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0364] Efetuar Substituição de hidrômetro
	 * 
	 * Validar Substituição de hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param situacaoHidrometroSubstituido
	 * 
	 * return void
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	public void validacaoSubstituicaoHidrometro(String matriculaImovel, String numeroHidrometro, String situacaoHidrometroSubstituido) throws ControladorException {

		this.getControladorMicromedicao().validarImovelEmCampo(new Integer(matriculaImovel));
		
		// Caso o hidrômetro substituido esteja com situacao igual a DISPONÍVEL
		if (situacaoHidrometroSubstituido.equals(-1) || situacaoHidrometroSubstituido.equals(HidrometroSituacao.INSTALADO.toString())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.hidrometro_situacao_disponivel");
		}

		FiltroHidrometro filtroHidrometro = new FiltroHidrometro();

		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
		filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));

		Collection colecaoHidrometro = null;

		colecaoHidrometro = getControladorUtil().pesquisar(filtroHidrometro,Hidrometro.class.getName());

		// [FS002]Caso o hidrômetro informado esteja com a situação diferente de DISPONÍVEL
		Iterator iteratorHidrometro = colecaoHidrometro.iterator();
		while (iteratorHidrometro.hasNext()) {
			Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			Integer idSituacaoHidrometro = hidrometro.getHidrometroSituacao().getId();
			
			if (!(idSituacaoHidrometro.equals(HidrometroSituacao.DISPONIVEL))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.hidrometro_situacao_indisponivel", null,hidrometro.getHidrometroSituacao().getDescricao());
			}
		}

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,matriculaImovel));

		Collection colecaoImoveis = null;

		colecaoImoveis = getControladorUtil().pesquisar(filtroImovel,Imovel.class.getName());
		Iterator iteratorImovel = colecaoImoveis.iterator();
		Imovel imovel = (Imovel) iteratorImovel.next();

		// [FS008]Caso situção do Imóvel não seja ativo(IMOV_ICEXCLUSAO da tabela IMOVEL correspondete a "não")
		if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.situacao_imovel_indicador_exclusao", null, imovel.getId().toString());
		}
	}

	/**
	 * [UC0360]- Efetuar Supressão da Ligação de Água
	 * 
	 * [SB0001]- Atualizar Ligação de Água [SB0002]- Atualizar Imóvel [SB0004]-
	 * Atualizar Histótico de Instalação de hidrômetro
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/07/2006
	 * @param imovel
	 * 
	 * @throws ControladorException
	 */

	public void efetuarSupressaoLigacaoAgua(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		Imovel imovel = integracaoComercialHelper.getImovel();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		LigacaoAgua ligacaoAgua = integracaoComercialHelper.getLigacaoAgua();
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper
				.getHidrometroInstalacaoHistorico();

		// Vivianne Sousa - 09/03/2009 - analista: Denys Tavares
		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();
		// 4.3.1 Caso PARM_ICSUPRESSAO = 1
		if (sistemaParametro.getIndicadorSupressao().equals(
				ConstantesSistema.SIM)) {

			// 4.3.1.1 caso o motivo de supressão selecionado seja igual a "A
			// PEDIDO DO CLIENTE",
			// verificar se existe débito para o imóvel
			SupressaoMotivo supressaoMotivo = ligacaoAgua.getSupressaoMotivo();
			if (supressaoMotivo.getId() != null
					&& supressaoMotivo.getId().equals(
							SupressaoMotivo.A_PEDIDO_DO_CLIENTE)) {

				// FS0015 - Verificar existencia de débitos
				ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = getControladorCobranca()
						.obterDebitoImovelOuCliente(1, // Indicador débito
														// imóvel
								imovel.getId().toString(), // Matrícula do
															// imóvel
								null, // Código do cliente
								null, // Tipo de relação do cliento com o
										// imóvel
								"190101", // Referência inicial do débito
								"999912", // Referência final do débito
								Util.converteStringParaDate("01/01/1901"), // Inicio
																			// Vencimento
								Util.converteStringParaDate("31/12/9999"), // Final
																			// Vencimento
								1, // Indicador pagamento
								1, // Indicador conta em revisão
								1, // Indicador débito a cobrar
								1, // Indicador crédito a realizar
								1, // Indicador notas promissórias
								1, // Indicador guias de pagamento
								1, // Indicador acréscimos por impontualidade
								null); // Indicador Contas

				if ((colecaoDebitoImovel.getColecaoContasValoresImovel() != null && colecaoDebitoImovel
						.getColecaoContasValoresImovel().size() > 0)
						|| (colecaoDebitoImovel
								.getColecaoGuiasPagamentoValores() != null && colecaoDebitoImovel
								.getColecaoGuiasPagamentoValores().size() > 0)
						|| (colecaoDebitoImovel.getColecaoDebitoACobrar() != null && colecaoDebitoImovel
								.getColecaoDebitoACobrar().size() > 0)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.nao_e_possivel_efetuar_supressao");
				}

			}
			// 4.3.1.2 caso contrario,
			// se o usuário não tiver permissão especial para efetuar supressão,
			// exibir a mensagem "Necessário permissão especial para efetuar
			// supressão
			else {

				boolean temPermissaoEfetuarSupressaoAgua = getControladorPermissaoEspecial()
						.verificarPermissaoEfetuarSupressaoAgua(
								integracaoComercialHelper.getUsuarioLogado());

				if (!temPermissaoEfetuarSupressaoAgua) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.necessario_permissao_para_efetuar_supressao");
				}

			}

		}

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR, imovel
						.getId(), imovel.getId(), new UsuarioAcaoUsuarioHelper(
						integracaoComercialHelper.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// [UC0107] -Fim- Registrar Transação

		// [SB0001] Atualizar Ligação de Água
		this.verificarLigacaoAguaControleConcorrencia(ligacaoAgua);
		registradorOperacao.registrarOperacao(ligacaoAgua);
		getControladorUtil().atualizar(ligacaoAgua);

		// [SB0001] Atualizar Imovel
		this.getControladorImovel().verificarImovelControleConcorrencia(imovel);
		registradorOperacao.registrarOperacao(imovel);
		getControladorUtil().atualizar(imovel);

		if (hidrometroInstalacaoHistorico != null) {
			hidrometroInstalacaoHistorico
					.setUsuarioRetirada(integracaoComercialHelper
							.getUsuarioLogado());
			registradorOperacao
					.registrarOperacao(hidrometroInstalacaoHistorico);
			/**
			 * Alterado por Arthur Carvalho
			 * Analista: Rosana Carvalho
			 * Data: 18/05/2010
			 * Devido a duplicidade da geração de debito na supressao do imóvel, 
			 * é efetuada a retirada do hidrometro sem geração de débito.
			 */
			this.efetuarRetiradaHidrometroSemGeracaoDebito(integracaoComercialHelper);
			getControladorUtil().atualizar(hidrometroInstalacaoHistorico);
		}
		// Atualiza Ordem de Servico
		FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
		filtroOrdemServico.adicionarParametro(new ParametroSimples(
				FiltroOrdemServico.ID, ordemServico.getId()));
		Collection colecaoOrdemServico = getControladorUtil().pesquisar(
				filtroOrdemServico, OrdemServico.class.getName());
		if (!colecaoOrdemServico.isEmpty()) {
			OrdemServico ordemServicoBase = (OrdemServico) Util
					.retonarObjetoDeColecao(colecaoOrdemServico);

			if (ordemServicoBase.getUltimaAlteracao().after(
					ordemServico.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}
		// [SB006]Atualizar Ordem de Serviço
		if (!integracaoComercialHelper.isVeioEncerrarOS()) {
			getControladorOrdemServico().atualizaOSGeral(ordemServico);
		}

		if (ordemServico.getServicoTipo().getDebitoTipo() != null
				&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
			getControladorOrdemServico().gerarDebitoOrdemServico(
					ordemServico.getId(),
					ordemServico.getServicoTipo().getDebitoTipo().getId(),
					ordemServico.getValorAtual(),
					new Integer(integracaoComercialHelper.getQtdParcelas()),
					ordemServico.getPercentualCobranca().toString(),
					integracaoComercialHelper.getUsuarioLogado());
		}

	}

	// atencao.situacao_volume_minimo_fixado_nao_multiplo= Valor do volume
	// Mínimo Fixado deve ser alterado para {0} valor multiplo de quantidade de
	// economias {1}.
	public Integer validarVolumeMinimoFixadoEsgoto(Imovel imovel,
			String volumeMinimoFixado) throws ControladorException {
		// [UC0105] - Obter Consumo Mínimo da Ligação
		int consumoMinimoObtido = getControladorMicromedicao()
				.obterConsumoMinimoLigacao(imovel, null);
		Integer consumoMinimoObtido1 = new Integer(consumoMinimoObtido);

		// Verificar se o volume Mínimo informado seja menor que o valor
		// Mínimo obtido para Imóvel.
		if (volumeMinimoFixado != null && !volumeMinimoFixado.trim().equals("")) {
			if (!volumeMinimoFixado.trim().equalsIgnoreCase(
					ConstantesSistema.SET_ZERO.toString())) {
				Integer consumoInformado = Integer.parseInt(volumeMinimoFixado);
				if (consumoInformado.intValue() < consumoMinimoObtido1
						.intValue()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.situacao_volume_minimo_fixado_menor_consumo_calculado",
							null, consumoMinimoObtido + "");
				}
			}
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.requerid", null,
					"Situação da Ligação de Esgoto");
		}

		return new Integer(volumeMinimoFixado);
	}

	/**
	 * [UC0359] Efetuar Restabelecimento Ligação de Água
	 * 
	 * Permite efetuar restabelecimento da Ligação de Água ou pelo menu ou pela
	 * funcionalidade encerrar a Execução da ordem de Serviço.
	 * 
	 * 
	 * [SB0001] Atualizar Imóvel/Ligação de Água/Ligação de Esgoto
	 * 
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/07/2006
	 * 
	 * @param idImovel,idOrdemServico
	 * 
	 * @throws ControladorException
	 */

	public void efetuarRestabelecimentoLigacaoAgua(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		// [SB0001] - Atualizar Imóvel/Ligação de Água

		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();

		// Caso 1
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		/*
		 * [UC0107] Registrar Transação
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR, imovel
						.getId(), imovel.getId(), new UsuarioAcaoUsuarioHelper(
						integracaoComercialHelper.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				ordemServico.getRegistroAtendimento().getImovel().getId()));
		Collection colecaoImovelBase = getControladorUtil().pesquisar(
				filtroImovel, Imovel.class.getName());
		Imovel imovelBase = null;
		if (!colecaoImovelBase.isEmpty()) {
			imovelBase = (Imovel) Util
					.retonarObjetoDeColecao(colecaoImovelBase);

			if (imovelBase.getUltimaAlteracao().after(
					ordemServico.getRegistroAtendimento().getImovel()
							.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);
		ligacaoAguaSituacao.setUltimaAlteracao(new Date());

		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		imovel.setUltimaAlteracao(new Date());

		this.getControladorImovel().verificarImovelControleConcorrencia(imovel);

		// regitrando operacao
		registradorOperacao.registrarOperacao(imovel);
		getControladorTransacao().registrarTransacao(imovel);

		// [SB0002] Atualizar Imóvel
		getControladorImovel().atualizarImovelExecucaoOrdemServicoLigacaoAgua(
				imovel, ligacaoAguaSituacao);

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = imovel
				.getLigacaoEsgotoSituacao();
		if (ligacaoEsgotoSituacao != null
				&& ligacaoEsgotoSituacao.getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO
						.intValue()) {

			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			ligacaoEsgotoSituacao.setUltimaAlteracao(new Date());

			getControladorImovel()
					.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel,
							ligacaoEsgotoSituacao);
		}

		// Caso 2
		LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
		ligacaoAgua.setDataRestabelecimentoAgua(ordemServico
				.getDataEncerramento());

		this.verificarLigacaoAguaControleConcorrencia(ligacaoAgua);

		// regitrando operacao
		registradorOperacao.registrarOperacao(ligacaoAgua);
		getControladorTransacao().registrarTransacao(ligacaoAgua);

		this.getControladorLigacaoAgua().atualizarLigacaoAguaRestabelecimento(
				ligacaoAgua);

		if (!integracaoComercialHelper.isVeioEncerrarOS()
				&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
			this.getControladorOrdemServico()
					.verificarOrdemServicoControleConcorrencia(ordemServico);

			// regitrando operacao
			registradorOperacao.registrarOperacao(ordemServico);
			getControladorTransacao().registrarTransacao(ordemServico);

			getControladorOrdemServico().atualizaOSGeral(ordemServico);
		}

		if (ordemServico.getServicoTipo().getDebitoTipo() != null
				&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
			getControladorOrdemServico().gerarDebitoOrdemServico(
					ordemServico.getId(),
					ordemServico.getServicoTipo().getDebitoTipo().getId(),
					ordemServico.getValorAtual(),
					new Integer(integracaoComercialHelper.getQtdParcelas()),
					ordemServico.getPercentualCobranca().toString(),
					integracaoComercialHelper.getUsuarioLogado());
		}
	}

	/**
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
	 * 
	 * [FS0003] - Validar atendimento do motivo de encerramento.
	 * 
	 * @author lms
	 * @created 28/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void validarAtendimentoMotivoEncerramento(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException {

		// [FS0003] - Validar atendimento do motivo de encerramento.

		/*
		 * Caso o indicador de deferimento esteja preenchido com não, o
		 * indicador de Execução do motivo de encerramento informado deverá
		 * estar com não, caso contrário, exibir a mensagem: "Motivo de
		 * encerramento do atendimento incompatível com o indicador de
		 * deferimento informado"
		 */
		if (osReferidaRetornoTipo.getAtendimentoMotivoEncerramento() != null) {
			if (ConstantesSistema.NAO.equals(osReferidaRetornoTipo
					.getIndicadorDeferimento())
					&& ConstantesSistema.NAO.equals(osReferidaRetornoTipo
							.getAtendimentoMotivoEncerramento()
							.getIndicadorExecucao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.atendimento_motivo_encerramento.incompativel");
			}
		}

	}

	/**
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
	 * 
	 * [FS0002] - Solicitar o indicador de troca de Serviço, situação e motivo
	 * de encerramento [FS0003] - Validar atendimento do motivo de encerramento
	 * [FS0005] - Validar indicador de deferimento [FS0006] - Validar indicador
	 * de deferimento x indicador de troca de Serviço [FS0007] - Verificar
	 * sucesso da transação
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public Integer inserirOSReferidaRetornoTipo(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException {

		// [FS0002] - Solicitar o indicador de troca de Serviço, situação e
		// motivo de encerramento

		/*
		 * Caso a Referência do tipo Serviço escolhido possuir o indicador de
		 * existência da Referência preenchido com não, as seguintes informações
		 * deverão estar desabilitadas para preenchimento e os seus conteúdos
		 * deverão estar preenchidos com:
		 * 
		 * ORRT_ICTROCASERVICO = 2 ORRT_CDSITUACAOOSREFERENCIA = null AMEN_ID =
		 * null
		 * 
		 */
		if (ConstantesSistema.NAO.equals(osReferidaRetornoTipo
				.getServicoTipoReferencia()
				.getIndicadorExistenciaOsReferencia())) {
			osReferidaRetornoTipo
					.setIndicadorTrocaServico(ConstantesSistema.NAO);
			osReferidaRetornoTipo.setSituacaoOsReferencia(null);
			osReferidaRetornoTipo.setAtendimentoMotivoEncerramento(null);
		}

		// [FS0003] - Validar atendimento do motivo de encerramento
		this.validarAtendimentoMotivoEncerramento(osReferidaRetornoTipo);

		// [FS0005] - Validar indicador de deferimento

		/*
		 * Apenas uma das descrições dos tipos de retorno da OS referida, por
		 * Referência do tipo de Serviço cujo identificador de uso esteja ATIVO,
		 * pode ter este indicador com o valor UM, o resto deverá possuir o
		 * valor igual a DOIS, caso contrário, exibir a mensagem: "Existe mais
		 * de um indicador de deferimento com situação de deferido para a mesma
		 * Referência do tipo Serviço informado."
		 */
		try {
			if (ConstantesSistema.SIM.equals(osReferidaRetornoTipo
					.getIndicadorDeferimento())) {
				int total = this.repositorioAtendimentoPublico
						.consultarTotalIndicadorDeferimentoAtivoPorServicoTipoReferencia(osReferidaRetornoTipo);
				if (total > 0) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.deferimento.incompativel");
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// [FS0006] - Validar indicador de deferimento x indicador de troca de
		// Serviço

		/*
		 * Caso o indicador de deferimento estiver marcando deferido, o
		 * indicador da troca de Serviço deve estar marcado como não, caso
		 * contrário, exibir a mensagem: "Indicador de troca de Serviço
		 * incompatível com o indicador de deferimento informado."
		 */
		if (ConstantesSistema.SIM.equals(osReferidaRetornoTipo
				.getIndicadorDeferimento())
				&& ConstantesSistema.SIM.equals(osReferidaRetornoTipo
						.getIndicadorTrocaServico())) {
			throw new ControladorException("atencao.troca_servico.incompativel");
		}

		osReferidaRetornoTipo
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		osReferidaRetornoTipo.setUltimaAlteracao(new Date());

		// [FS0007] - Verificar sucesso da transação
		return (Integer) getControladorUtil().inserir(osReferidaRetornoTipo);

	}

	/**
	 * [UC0381] Inserir Material com Unidade
	 * 
	 * Permite a inclusao de um novo material
	 * 
	 * 
	 * [SB0001] Gerar Material com Unidade
	 * 
	 * 1.1Inclui o material na tabela Material
	 * 
	 * 
	 * 
	 * @author Rômulo Aurélio, Diogo Peixoto
	 * @date 31/07/2006, 18/08/2011
	 * 
	 * @param codigoMaterial
	 * @param descricao
	 * @param descricaoAbreviada
	 * @param unidadeMaterial
	 * 
	 * @throws ControladorException
	 */
	public Integer inserirMaterial(String codigoMaterial, String descricao, String descricaoAbreviada,
			String unidadeMaterial, Usuario usuarioLogado) throws ControladorException {

		Material material = new Material();
		material.setDescricao(descricao);
		material.setDescricaoAbreviada(descricaoAbreviada);

		String idUnidadeMaterial = unidadeMaterial;

		if (Util.verificarIdNaoVazio(idUnidadeMaterial)){
			MaterialUnidade materialUnidade = new MaterialUnidade();
			materialUnidade.setId(new Integer(idUnidadeMaterial));
			material.setMaterialUnidade(materialUnidade);
		}

		material.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		// [FS0001] Verificar existencia da Descrição
		FiltroMaterial filtroMaterial = new FiltroMaterial();

		filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.DESCRICAO, material.getDescricao()));
		Collection<Material> colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());

		if (!Util.isVazioOrNulo(colecaoMaterial)) {
			throw new ControladorException("atencao.descricao_ja_existente_material", null, "" + material.getDescricao() + "");
		}

		if (Util.verificarNaoVazio(material.getDescricaoAbreviada())) {

			filtroMaterial = new FiltroMaterial();

			// [FS0002]- Verificar existência da Descrição abreviada
			filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.DESCRICAO_ABREVIADA, material.getDescricao()));
			colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());

			if (!Util.isVazioOrNulo(colecaoMaterial)) {
				throw new ControladorException("atencao.descricao_abreviada_ja_existente_material");
			}
		}

		if (Util.verificarIdNaoVazio(codigoMaterial)) {
			try{
				Integer codigo = Integer.valueOf(codigoMaterial);
				
				filtroMaterial.limparListaParametros();
				//[FS0005]- Verificar existência do código
				filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.CODIGO, codigo));
				colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());
				
				if (!Util.isVazioOrNulo(colecaoMaterial)) {
					throw new ControladorException("atencao.codigo_ja_existente_material");
				}
				material.setCodigo(codigo);
			}catch (NumberFormatException e) {
				throw new ControladorException("atencao.codigo_formato_invalido");
			}
		}
		
		material.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSação----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MATERIAL_INSERIR, new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MATERIAL_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		material.setOperacaoEfetuada(operacaoEfetuada);
		material.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(material);

		// --------FIM---- REGISTRAR TRANSação----------------------------
		Integer idMaterial = (Integer) getControladorUtil().inserir(material);

		return idMaterial;

	}

	/**
	 * [UC0385] Inserir Tipo Perfil Serviço
	 * 
	 * @author Ana Maria
	 * @date 01/08/2006
	 * 
	 * @pparam servicoPerfilTipo
	 * @throws ControladorException
	 */
	public Integer inserirServicoTipoPerfil(ServicoPerfilTipo servicoPerfilTipo)
			throws ControladorException {

		// [FS0004] Verificar existencia da Descrição
		FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();

		filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoPerfilTipo.DESCRICAO, servicoPerfilTipo
						.getDescricao()));

		filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoPerfilTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoServicoPerfilTipo = getControladorUtil().pesquisar(
				filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());

		if (colecaoServicoPerfilTipo != null
				&& !colecaoServicoPerfilTipo.isEmpty()) {
			throw new ControladorException(
					"atencao.descricao_ja_existente_tipo_perfil_servico", null,
					"" + servicoPerfilTipo.getDescricao() + "");
		}

		getControladorUtil().inserir(servicoPerfilTipo);

		// ------------ REGISTRAR TRANSação----------------------------
		/*
		 * RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		 * Operacao.OPERACAO_SERVICO_TIPO_INSERIR, new
		 * UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
		 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		 * 
		 * Operacao operacao = new Operacao();
		 * operacao.setId(Operacao.OPERACAO_SERVICO_TIPO_PERFIL_INSERIR);
		 * 
		 * OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		 * operacaoEfetuada.setOperacao(operacao);
		 * 
		 * servicoPerfilTipo.setOperacaoEfetuada(operacaoEfetuada);
		 * servicoPerfilTipo.adicionarUsuario(Usuario.USUARIO_TESTE,
		 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		 * registradorOperacao.registrarOperacao(servicoPerfilTipo);
		 */

		// ------------ REGISTRAR TRANSação----------------------------
		return servicoPerfilTipo.getId();
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0004] - Validar Perfil do Serviço
	 * 
	 * @author lms
	 * @date 01/08/2006
	 */
	public ServicoPerfilTipo pesquisarServicoPerfilTipo(
			Integer idServicoPerfilTipo) throws ControladorException {
		FiltroServicoPerfilTipo filtro = new FiltroServicoPerfilTipo();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroServicoPerfilTipo.ID, idServicoPerfilTipo));
		return pesquisar(filtro, ServicoPerfilTipo.class, "Tipo do Perfil");
	}
	
	/**
	 * 
	 * [UC0410] - Inserir Operacao
	 * 
	 * [FS0004] - Validar Operacao
	 * 
	 * @author lms
	 * @date 01/08/2006
	 */
	public Operacao pesquisarOperacao(Integer idOperacao) throws ControladorException {
		FiltroOperacao filtro = new FiltroOperacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacao));
		return pesquisar(filtro, Operacao.class, "Operação");
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0005] - Validar Tipo de Serviço de Referência
	 * 
	 * @author lms
	 * @date 02/08/2006
	 */
	public ServicoTipoReferencia pesquisarServicoTipoReferencia(
			Integer idServicoTipoReferencia) throws ControladorException {
		FiltroServicoTipoReferencia filtro = new FiltroServicoTipoReferencia();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoReferencia.ID, idServicoTipoReferencia));
		return pesquisar(filtro, ServicoTipoReferencia.class,
				"Tipo do Serviço de Referência");
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0009] - Validar Atividade
	 * 
	 * @author lms
	 * @date 05/08/2006
	 */
	public Atividade pesquisarAtividade(Integer idAtividade,
			String atividadeUnica) throws ControladorException {
		FiltroAtividade filtro = new FiltroAtividade();
		filtro.adicionarParametro(new ParametroSimples(FiltroAtividade.ID, idAtividade));
		if (atividadeUnica != null && !atividadeUnica.trim().equals("")) {
			filtro.adicionarParametro(new ParametroSimples(FiltroAtividade.INDICADORATIVIDADEUNICA, ConstantesSistema.INDICADOR_USO_ATIVO));
		}
		return pesquisar(filtro, Atividade.class, "Atividade");
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0010] - Validar Material
	 * 
	 * @author lms
	 * @date 08/08/2006
	 */
	public Material pesquisarMaterial(Integer idMaterial)
			throws ControladorException {
		FiltroMaterial filtro = new FiltroMaterial();
		filtro.adicionarParametro(new ParametroSimples(FiltroMaterial.ID,
				idMaterial));
		return pesquisar(filtro, Material.class, "Material");
	}

	private <T> T pesquisar(Filtro filtro, Class clazz, String label)
			throws ControladorException {
		T objeto = null;
		Collection colecao = getControladorUtil().pesquisar(filtro,
				clazz.getName());
		if (colecao == null || colecao.isEmpty()) {
			throw new ControladorException("atencao.pesquisa_inexistente",
					null, label);
		} else {
			objeto = (T) colecao.iterator().next();
		}
		return objeto;
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0006] - Validar Ordem de Execução
	 * 
	 * @author lms
	 * @date 05/08/2006
	 */
	public void validarOrdemExecucao(Collection colecaoServicoTipoAtividade,
			Short ordemExecucao) throws ControladorException {
		Set ordensExecucao = new TreeSet();
		if (colecaoServicoTipoAtividade != null
				&& !colecaoServicoTipoAtividade.isEmpty()) {
			for (Iterator iter = colecaoServicoTipoAtividade.iterator(); iter
					.hasNext();) {
				ordensExecucao.add(((ServicoTipoAtividade) iter.next())
						.getNumeroExecucao());
			}
		}
		if (ordensExecucao.contains(ordemExecucao)) {
			throw new ControladorException(
					"atencao.ordem_execucao.ja_existente", null, ordemExecucao
							+ "");
		}
		ordensExecucao.add(ordemExecucao);
		Short pred = 0;
		Short succ;
		for (Iterator iter = ordensExecucao.iterator(); iter.hasNext();) {
			succ = (Short) iter.next();
			if (succ - pred > 1) {
				throw new ControladorException(
						"atencao.ordem_execucao.invalida", null, ordemExecucao
								+ "");
			}
			pred = succ;
		}
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public Integer inserirServicoTipo(ServicoTipo servicoTipo,
			Usuario usuarioLogado,ServicoTipoBoletim servicoTipoBoletim) throws ControladorException {

		// [FS0001] - Verificar existência da Descrição
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.DESCRICAO, servicoTipo.getDescricao()));
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		ServicoTipo st = null;

		try {
			st = pesquisar(filtroServicoTipo, ServicoTipo.class, "");
		} catch (ControladorException e) {
		}

		if (st != null) {
			throw new ControladorException(
					"atencao.servico_tipo.descricao_ja_existente");
		}

		// ------------ REGISTRAR TRANSação----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_TIPO_SERVICO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_TIPO_SERVICO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		servicoTipo.setOperacaoEfetuada(operacaoEfetuada);
		servicoTipo.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(servicoTipo);

		// ------------ REGISTRAR TRANSação----------------------------

		// [FS0002] - Verificar existência da Descrição abreviada
		if (servicoTipo.getDescricaoAbreviada() != null && !servicoTipo.getDescricaoAbreviada().equals("")) {

			filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.DESCRICAO_ABREVIADA, servicoTipo
							.getDescricaoAbreviada()));

			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			try {
				st = pesquisar(filtroServicoTipo, ServicoTipo.class, "");
			} catch (ControladorException e) {
			}

			if (st != null) {
				throw new ControladorException(
						"atencao.servico_tipo.descricao_abreviada_ja_existente");
			}

		}

		// [FS0003] - Validar Tipo de Débito
		if (servicoTipo.getDebitoTipo() != null) {
			getControladorFaturamento().pesquisarDebitoTipo(
					servicoTipo.getDebitoTipo().getId());
		}

		// [FS0004] - Validar Perfil do Serviço
		if (servicoTipo.getServicoPerfilTipo() != null) {
			pesquisarServicoPerfilTipo(servicoTipo.getServicoPerfilTipo()
					.getId());
		}

		// [FS0006] - Validar Ordem de Execução

		// [FS0009] - Validar Atividade

		// [FS0010] - Validar Material

		Date dataUltimaAlteracao = new Date();

		servicoTipo.setUltimaAlteracao(dataUltimaAlteracao);
		servicoTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		Collection colecaoServicoTipoAtividades = servicoTipo
				.getServicoTipoAtividades();
		Collection colecaoServicoTipoMateriais = servicoTipo
				.getServicoTipoMateriais();
		
		Collection <AtendimentoMotivoEncerramento> colecaoServicoTipoMotivoEncerramentos = servicoTipo
		.getServicoTipoMotivoEncerramentos();

		Integer id = null;

		// inseri tipo servico referencia
		try {
			if (servicoTipo.getServicoTipoReferencia() != null
					&& servicoTipo.getServicoTipoReferencia().getId() == null) {
				ServicoTipoReferencia servicoTipoReferencia = servicoTipo
						.getServicoTipoReferencia();
				servicoTipoReferencia.setUltimaAlteracao(new Date());
				Integer referencia = (Integer) getControladorUtil().inserir(
						servicoTipoReferencia);

				servicoTipo.getServicoTipoReferencia().setId(referencia);
			} else if (servicoTipo.getServicoTipoReferencia() != null
					&& servicoTipo.getServicoTipoReferencia().getId() != null) {
				// [FS0005] - Validar Tipo de Serviço Referência
				if (servicoTipo.getServicoTipoReferencia() != null) {
					servicoTipo.getServicoTipoReferencia().setUltimaAlteracao(
							new Date());
					pesquisarServicoTipoReferencia(servicoTipo
							.getServicoTipoReferencia().getId());
				}
			}
			

			// [FS0008] - Verificar Sucesso da Operação
			id = inserirServicoTipoSemColecoes(servicoTipo);
		

		if (colecaoServicoTipoAtividades != null
				&& !colecaoServicoTipoAtividades.isEmpty()) {
			// Serviço Tipo Atividade (1..n)
			for (Iterator iter = colecaoServicoTipoAtividades.iterator(); iter
					.hasNext();) {

				ServicoTipoAtividade sta = (ServicoTipoAtividade) iter.next();
				sta.setServicoTipo(servicoTipo);
				sta.setAtividade(sta.getAtividade());
				sta.setUltimaAlteracao(dataUltimaAlteracao);
				sta.setComp_id(new ServicoTipoAtividadePK(servicoTipo.getId(), sta
						.getAtividade().getId()));

				getControladorUtil().inserir(sta);
			}
		}
		
		if (servicoTipo.getOperacao() != null) {
			ServicoTipoOperacao sto = new ServicoTipoOperacao();
			sto.setOperacao(servicoTipo.getOperacao());
			sto.setServicoTipo(servicoTipo);
			sto.setUltimaAlteracao(dataUltimaAlteracao);
			sto.setComp_id(new ServicoTipoOperacaoPK(servicoTipo.getId(), sto.getOperacao().getId()));
			
			getControladorUtil().inserir(sto);
		}
		

		if (colecaoServicoTipoMateriais != null
				&& !colecaoServicoTipoMateriais.isEmpty()) {
			// Serviço Tipo Material (1..n)
			for (Iterator iter = colecaoServicoTipoMateriais.iterator(); iter
					.hasNext();) {

				ServicoTipoMaterial stm = (ServicoTipoMaterial) iter.next();
				stm.setServicoTipo(servicoTipo);
				stm.setMaterial(stm.getMaterial());
				stm.setUltimaAlteracao(dataUltimaAlteracao);
				stm.setComp_id(new ServicoTipoMaterialPK(id, stm.getMaterial()
						.getId()));
				getControladorUtil().inserir(stm);
			}
		}
		
		if (colecaoServicoTipoMotivoEncerramentos != null
					&& !colecaoServicoTipoMotivoEncerramentos.isEmpty()) {
				for (AtendimentoMotivoEncerramento encerramento : colecaoServicoTipoMotivoEncerramentos) {
					if (encerramento != null) {
						ServicoTipoMotivoEncerramento servicoTipoMotivoEncerramento = new ServicoTipoMotivoEncerramento();
						servicoTipoMotivoEncerramento
								.setMotivoEncerramento(encerramento);
						servicoTipoMotivoEncerramento
								.setServicoTipo(servicoTipo);
						servicoTipoMotivoEncerramento
								.setUltimaAlteracao(new Date());

						getControladorOrdemServico()
								.inserirServicoTipoMotivoEncerramento(
										servicoTipoMotivoEncerramento);
					}
				}
			}
		
		//RM93 - adicionado por Vivianne Sousa - 07/01/2011 - analista:Rosana Carvalho
		if(servicoTipoBoletim != null){
			servicoTipoBoletim.setId(servicoTipo.getId());
			servicoTipoBoletim.setServicoTipo(servicoTipo);
			servicoTipoBoletim.setUltimaAlteracao(new Date());
			getControladorUtil().inserir(servicoTipoBoletim);
		}
		} catch (ControladorException e) {
			if(servicoTipo.getServicoTipoReferencia()!=null){
				servicoTipo.getServicoTipoReferencia().setId(null);
			}
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		}
		
		return id;

	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author Flávio
	 * @date 08/12/2006
	 */
	public Integer atualizarServicoTipo(ServicoTipo servicoTipo,ServicoTipoBoletim servicoTipoBoletim)
			throws ControladorException {

		// [FS0001] - Verificar existência da Descrição
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.DESCRICAO, servicoTipo.getDescricao()));
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// [FS0002] - Verificar existência da Descrição abreviada
		if (servicoTipo.getDescricaoAbreviada() != null) {

			filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.DESCRICAO_ABREVIADA, servicoTipo
							.getDescricaoAbreviada()));

			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
		}

		// [FS0003] - Validar Tipo de Débito
		if (servicoTipo.getDebitoTipo() != null) {
			getControladorFaturamento().pesquisarDebitoTipo(
					servicoTipo.getDebitoTipo().getId());
		}

		// [FS0004] - Validar Perfil do Serviço
		if (servicoTipo.getServicoPerfilTipo() != null) {
			pesquisarServicoPerfilTipo(servicoTipo.getServicoPerfilTipo()
					.getId());
		}
		
		// Validar Operacao
		if (servicoTipo.getOperacao() != null) {
			pesquisarOperacao(servicoTipo.getOperacao().getId());
			
			FiltroServicoTipoOperacao filtroServicoTipoOperacao = new FiltroServicoTipoOperacao();
			filtroServicoTipoOperacao.adicionarParametro(new ParametroSimples(FiltroServicoTipoOperacao.ID_SERVICO_TIPO, servicoTipo.getId()));
			
			Collection collection = getControladorUtil().pesquisar(filtroServicoTipoOperacao, ServicoTipoOperacao.class.getName());
			
			if (collection != null && !collection.isEmpty()) { //atualiza a operacao do servico tipo
				ServicoTipoOperacao sto = (ServicoTipoOperacao) collection.iterator().next();
				
				if (sto != null) {
					getControladorUtil().remover(sto);
					
					ServicoTipoOperacao stoNovo = new ServicoTipoOperacao();
					stoNovo.setOperacao(servicoTipo.getOperacao());
					stoNovo.setServicoTipo(servicoTipo);
					stoNovo.setUltimaAlteracao(new Date());
					stoNovo.setComp_id(new ServicoTipoOperacaoPK(servicoTipo.getId(), stoNovo.getOperacao().getId()));
					
					getControladorUtil().inserir(stoNovo);
					
				}
			} else { //insere a operacao do servico tipo
				ServicoTipoOperacao stoNovo = new ServicoTipoOperacao();
				stoNovo.setOperacao(servicoTipo.getOperacao());
				stoNovo.setServicoTipo(servicoTipo);
				stoNovo.setUltimaAlteracao(new Date());
				stoNovo.setComp_id(new ServicoTipoOperacaoPK(servicoTipo.getId(), stoNovo.getOperacao().getId()));
				
				getControladorUtil().inserir(stoNovo);
			}
			
			
		} else {
			FiltroServicoTipoOperacao filtroServicoTipoOperacao = new FiltroServicoTipoOperacao();
			filtroServicoTipoOperacao.adicionarParametro(new ParametroSimples(FiltroServicoTipoOperacao.ID_SERVICO_TIPO, servicoTipo.getId()));
			
			Collection colecao = getControladorUtil().pesquisar(filtroServicoTipoOperacao, ServicoTipoOperacao.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				ServicoTipoOperacao sto = (ServicoTipoOperacao) colecao.iterator().next();
				getControladorUtil().remover(sto);
			}
		}
			
			
		// [FS0006] - Validar Ordem de Execução

		// [FS0009] - Validar Atividade

		// [FS0010] - Validar Material

		Date dataUltimaAlteracao = new Date();

		servicoTipo.setUltimaAlteracao(dataUltimaAlteracao);
		servicoTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		Collection colecaoServicoTipoAtividades = servicoTipo
				.getServicoTipoAtividades();
		Collection colecaoServicoTipoMateriais = servicoTipo
				.getServicoTipoMateriais();

		Integer id = servicoTipo.getId();

		// inseri tipo servico referencia
		try {
			if (servicoTipo.getServicoTipoReferencia() != null
					&& servicoTipo.getServicoTipoReferencia().getId() == null) {
				ServicoTipoReferencia servicoTipoReferencia = servicoTipo
						.getServicoTipoReferencia();
				servicoTipoReferencia.setUltimaAlteracao(new Date());
				Integer referencia = (Integer) getControladorUtil().inserir(
						servicoTipoReferencia);

				servicoTipo.getServicoTipoReferencia().setId(referencia);
			} else if (servicoTipo.getServicoTipoReferencia() != null
					&& servicoTipo.getServicoTipoReferencia().getId() != null) {
				// [FS0005] - Validar Tipo de Serviço Referência
				if (servicoTipo.getServicoTipoReferencia() != null) {
					servicoTipo.getServicoTipoReferencia().setUltimaAlteracao(
							new Date());
					pesquisarServicoTipoReferencia(servicoTipo
							.getServicoTipoReferencia().getId());
				}
			}

			// [FS0008] - Verificar Sucesso da Operação
			atualizarServicoTipoSemColecoes(servicoTipo);
		} catch (ControladorException e) {
			servicoTipo.getServicoTipoReferencia().setId(null);
			sessionContext.setRollbackOnly();
		}

		// FiltroServicoTipoAtividade filtroServicoTipoAtividade = new
		// FiltroServicoTipoAtividade();
		// filtroServicoTipoAtividade.adicionarParametro(new
		// ParametroSimples(FiltroServicoTipoAtividade.));

		// Serviço Tipo Atividade (1..n)

		FiltroServicoTipoAtividade filtroServicoTipoAtividade = new FiltroServicoTipoAtividade();
		filtroServicoTipoAtividade
				.adicionarParametro(new ParametroSimples(
						FiltroServicoTipoAtividade.SERVICO_TIPO_ID, servicoTipo
								.getId()));
		filtroServicoTipoAtividade
				.adicionarCaminhoParaCarregamentoEntidade("atividade");

		FiltroServicoTipoMaterial filtroServicoTipoMaterial = new FiltroServicoTipoMaterial();
		filtroServicoTipoMaterial
				.adicionarParametro(new ParametroSimples(
						FiltroServicoTipoMaterial.ID_SERVICO_TIPO, servicoTipo
								.getId()));
		filtroServicoTipoMaterial
				.adicionarCaminhoParaCarregamentoEntidade("material");

		Collection colecaoServicoTipoMaterialBASE = getControladorUtil()
				.pesquisar(filtroServicoTipoMaterial,
						ServicoTipoMaterial.class.getName());

		Collection colecaoServicoTipoAtividadeBASE = getControladorUtil()
				.pesquisar(filtroServicoTipoAtividade,
						ServicoTipoAtividade.class.getName());

		for (Iterator iter = colecaoServicoTipoAtividadeBASE.iterator(); iter
				.hasNext();) {

			ServicoTipoAtividade sta = (ServicoTipoAtividade) iter.next();

			getControladorUtil().remover(sta);

		}

		for (Iterator iter = colecaoServicoTipoMaterialBASE.iterator(); iter
				.hasNext();) {

			ServicoTipoMaterial sta = (ServicoTipoMaterial) iter.next();

			getControladorUtil().remover(sta);

		}

		if (colecaoServicoTipoAtividades != null) {
			for (Iterator iter = colecaoServicoTipoAtividades.iterator(); iter
					.hasNext();) {

				ServicoTipoAtividade sta = (ServicoTipoAtividade) iter.next();
				sta.setServicoTipo(servicoTipo);
				sta.setAtividade(sta.getAtividade());
				sta.setUltimaAlteracao(dataUltimaAlteracao);
				sta.setComp_id(new ServicoTipoAtividadePK(id, sta
						.getAtividade().getId()));

				getControladorUtil().inserir(sta);
			}
		}

		// Serviço Tipo Material (1..n)
		if (colecaoServicoTipoMateriais != null) {
			for (Iterator iter = colecaoServicoTipoMateriais.iterator(); iter
					.hasNext();) {

				ServicoTipoMaterial stm = (ServicoTipoMaterial) iter.next();
				stm.setServicoTipo(servicoTipo);
				stm.setMaterial(stm.getMaterial());
				stm.setUltimaAlteracao(dataUltimaAlteracao);
				stm.setComp_id(new ServicoTipoMaterialPK(id, stm.getMaterial()
						.getId()));
				getControladorUtil().inserir(stm);
			}
		}

		
		//RM93 - adicionado por Vivianne Sousa - 07/01/2011 - analista:Rosana Carvalho
		if(servicoTipo.getIndicadorBoletim().equals(ConstantesSistema.NAO)){
			removerServicoTipoBoletim(servicoTipo.getId());
		}
		if(servicoTipoBoletim != null){
			servicoTipoBoletim.setId(servicoTipo.getId());
			servicoTipoBoletim.setServicoTipo(servicoTipo);
			servicoTipoBoletim.setUltimaAlteracao(new Date());

			FiltroServicoTipoBoletim filtroServicoTipoBoletim = new FiltroServicoTipoBoletim();
			filtroServicoTipoBoletim.adicionarParametro(new ParametroSimples(
			FiltroServicoTipoBoletim.ID, servicoTipo.getId()));
			Collection colecaoServicoTipoBoletim = this.getControladorUtil().pesquisar(
			filtroServicoTipoBoletim, ServicoTipoBoletim.class.getName());

			if (colecaoServicoTipoBoletim != null && !colecaoServicoTipoBoletim.isEmpty()) {
			getControladorUtil().atualizar(servicoTipoBoletim);
			} else {
			getControladorUtil().inserir(servicoTipoBoletim);
			}

		}


		return id;

	}

	private Integer inserirServicoTipoSemColecoes(ServicoTipo servicoTipo)
			throws ControladorException {

		servicoTipo.setServicoTipoAtividades(null);
		servicoTipo.setServicoTipoMateriais(null);

		return (Integer) getControladorUtil().inserir(servicoTipo);
	}

	private void atualizarServicoTipoSemColecoes(ServicoTipo servicoTipo)
			throws ControladorException {

		servicoTipo.setServicoTipoAtividades(null);
		servicoTipo.setServicoTipoMateriais(null);

		getControladorUtil().atualizar(servicoTipo);
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public ServicoTipoSubgrupo pesquisarServicoTipoSubgrupo(
			Integer idServicoTipoSubgrupo) throws ControladorException {
		FiltroServicoTipoSubgrupo filtro = new FiltroServicoTipoSubgrupo();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoReferencia.ID, idServicoTipoSubgrupo));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoReferencia.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		return pesquisar(filtro, ServicoTipoSubgrupo.class,
				"Serviço Tipo Subgrupo");
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public ServicoTipoPrioridade pesquisarServicoTipoPrioridade(
			Integer idServicoTipoPrioridade) throws ControladorException {
		FiltroServicoTipoPrioridade filtro = new FiltroServicoTipoPrioridade();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoPrioridade.ID, idServicoTipoPrioridade));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoPrioridade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		return pesquisar(filtro, ServicoTipoPrioridade.class,
				"Serviço Tipo Prioridade");
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public void validarAdicionarAtividade(
			Collection colecaoServicoTipoAtividade, Integer idAtividade)
			throws ControladorException {
		if (colecaoServicoTipoAtividade != null
				&& !colecaoServicoTipoAtividade.isEmpty()) {
			for (Iterator iter = colecaoServicoTipoAtividade.iterator(); iter
					.hasNext();) {
				ServicoTipoAtividade element = (ServicoTipoAtividade) iter
						.next();
				if (idAtividade.equals(element.getAtividade().getId())) {
					throw new ControladorException(
							"atencao.atividade.ja_existente");
				}
			}
		}
	}

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public void validarAdicionarMaterial(Collection colecaoServicoTipoMaterial,
			Integer idMaterial) throws ControladorException {
		if (colecaoServicoTipoMaterial != null
				&& !colecaoServicoTipoMaterial.isEmpty()) {
			for (Iterator iter = colecaoServicoTipoMaterial.iterator(); iter
					.hasNext();) {
				ServicoTipoMaterial element = (ServicoTipoMaterial) iter.next();
				if (idMaterial.equals(element.getMaterial().getId())) {
					throw new ControladorException(
							"atencao.material.ja_existente");
				}
			}
		}
	}

	/**
	 * [UC0436] Inserir Tipo de Serviço de Referência.
	 * 
	 * Permite a inclusão de um tipo de Serviço de Referência.
	 * 
	 * [FS0001] Verificar existencia da Descrição [FS0002]- Verificar existência
	 * da Descrição abreviada [FS0003] Validar indicador de existencia x
	 * Situação da Os de referencia
	 * 
	 * @author Rômulo Aurélio.
	 * @date 05/08/2006
	 * 
	 * 
	 * @param servicoTipoReferencia
	 * @throws ControladorException
	 */

	public Integer inserirTipoServicoReferencia(
			ServicoTipoReferencia servicoTipoReferencia, Usuario usuarioLogado)
			throws ControladorException {

		this.validarTipoServicoReferenciaParaInsercao(servicoTipoReferencia);

		// ------------------- Final [FS0003]--------------------------

		servicoTipoReferencia.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSação----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SERVICO_TIPO_REFERENCIA_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SERVICO_TIPO_REFERENCIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		servicoTipoReferencia.setOperacaoEfetuada(operacaoEfetuada);
		servicoTipoReferencia.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(servicoTipoReferencia);

		// --------FIM---- REGISTRAR TRANSação----------------------------
		Integer idTipoServicoReferencia = (Integer) getControladorUtil()
				.inserir(servicoTipoReferencia);

		return idTipoServicoReferencia;

	}

	/**
	 * 
	 * Este método valida os dados que são necessarios para a inserção do
	 * Serviço tipo referencia.
	 * 
	 * 
	 * @author Flávio Leonardo
	 * @date 31/10/2006
	 * 
	 * @param servicoTipoReferencia
	 * @return
	 * @throws ControladorException
	 */
	public void validarTipoServicoReferenciaParaInsercao(
			ServicoTipoReferencia servicoTipoReferencia)
			throws ControladorException {

		// [FS0001] Verificar existencia da Descrição

		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();

		filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoReferencia.DESCRICAO, servicoTipoReferencia
						.getDescricao()));

		Collection colecaoServicoTipoReferencia = getControladorUtil()
				.pesquisar(filtroServicoTipoReferencia,
						ServicoTipoReferencia.class.getName());

		if (colecaoServicoTipoReferencia != null
				&& !colecaoServicoTipoReferencia.isEmpty()) {
			throw new ControladorException(
					"atencao.descricao_ja_existente_servico_referencia", null,
					"" + servicoTipoReferencia.getDescricao() + "");
		}

		if (servicoTipoReferencia.getDescricaoAbreviada() != null
				&& !servicoTipoReferencia.getDescricaoAbreviada().equals("")) {

			filtroServicoTipoReferencia.limparListaParametros();

			// [FS0002]- Verificar existência da Descrição abreviada
			filtroServicoTipoReferencia
					.adicionarParametro(new ParametroSimples(
							FiltroServicoTipoReferencia.DESCRICAO_ABREVIADA,
							servicoTipoReferencia.getDescricaoAbreviada()));

			colecaoServicoTipoReferencia = getControladorUtil().pesquisar(
					filtroServicoTipoReferencia,
					ServicoTipoReferencia.class.getName());

			if (colecaoServicoTipoReferencia != null
					&& !colecaoServicoTipoReferencia.isEmpty()) {
				throw new ControladorException(
						"atencao.descricao_abreviada_ja_existente_servico_referencia",
						null, ""
								+ servicoTipoReferencia.getDescricaoAbreviada()
								+ "");
			}

		}

		// [FS0003]- Validar indicador de existência x Situação da Os de
		// Referência

		Short indicadorExistenciaOS = servicoTipoReferencia
				.getIndicadorExistenciaOsReferencia();

		if (indicadorExistenciaOS.equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {

			if (!(servicoTipoReferencia.getSituacaoOsReferenciaAntes()
					.equals(new Integer("1")))
					&& (servicoTipoReferencia.getSituacaoOsReferenciaAntes()
							.intValue() != 2)) {
				throw new ControladorException(
						"atencao.indicador_os_incompativel_os_antes", null);

			}

			if ((servicoTipoReferencia.getSituacaoOsReferenciaApos().intValue() != 2)
					&& (servicoTipoReferencia.getSituacaoOsReferenciaApos()
							.intValue() != 4)) {
				throw new ControladorException(
						"atencao.indicador_ordem_servico_incompativel", null);

			}

		}

		if (indicadorExistenciaOS
				.equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
			if (servicoTipoReferencia.getSituacaoOsReferenciaAntes() != null) {
				throw new ControladorException(
						"atencao.indicador_os_nao_permitido", null);

			}

			if (servicoTipoReferencia.getSituacaoOsReferenciaApos() != null) {
				throw new ControladorException(
						"atencao.indicador_os_nao_permitido", null);

			}

		}
	}

	/**
	 * [UC0449] Inserir Prioridade do Tipo de Serviço
	 * 
	 * Permite a inclusão de uma prioridade do tipo de Serviço.
	 * 
	 * [FS0001] Verificar existencia da Descrição [FS0003]- Verificar existência
	 * da Descrição abreviada [FS0002] Validar quantidade de horas início e
	 * quantidade de horas fim
	 * 
	 * @author Rômulo Aurélio.
	 * @date 11/08/2006
	 * 
	 * 
	 * @param servicoTipoPrioridade
	 * @throws ControladorException
	 */

	public Integer inserirPrioridadeTipoServico(
			ServicoTipoPrioridade servicoTipoPrioridade, Usuario usuarioLogado)
			throws ControladorException {

		// [FS0001] Verificar existencia da Descrição

		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();

		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoPrioridade.DESCRICAO, servicoTipoPrioridade
						.getDescricao()));

		Collection colecaoServicoTipoPrioridade = getControladorUtil()
				.pesquisar(filtroServicoTipoPrioridade,
						ServicoTipoPrioridade.class.getName());

		// ajeitar no apliccation a msg

		if (colecaoServicoTipoPrioridade != null
				&& !colecaoServicoTipoPrioridade.isEmpty()) {
			throw new ControladorException(
					"atencao.descricao_ja_existente_servico_referencia", null,
					"" + servicoTipoPrioridade.getDescricao() + "");
		}

		if (servicoTipoPrioridade.getDescricaoAbreviada() != null
				&& !servicoTipoPrioridade.getDescricaoAbreviada().equals("")) {

			filtroServicoTipoPrioridade.limparListaParametros();

			// [FS0002]- Verificar existência da Descrição abreviada
			filtroServicoTipoPrioridade
					.adicionarParametro(new ParametroSimples(
							FiltroServicoTipoPrioridade.DESCRICAO_ABREVIADA,
							servicoTipoPrioridade.getDescricaoAbreviada()));

			colecaoServicoTipoPrioridade = getControladorUtil().pesquisar(
					filtroServicoTipoPrioridade,
					ServicoTipoPrioridade.class.getName());

			if (colecaoServicoTipoPrioridade != null
					&& !colecaoServicoTipoPrioridade.isEmpty()) {
				throw new ControladorException(
						"atencao.descricao_abreviada_ja_existente_servico_referencia",
						null, ""
								+ servicoTipoPrioridade.getDescricaoAbreviada()
								+ "");
			}

		}

		// [FS0002] Validar quantidade de horas início e quantidade de horas fim

		if (servicoTipoPrioridade.getPrazoExecucaoInicio() != null) {

			if ((servicoTipoPrioridade.getPrazoExecucaoInicio() < 0)
					|| (servicoTipoPrioridade.getPrazoExecucaoInicio() == 0)) {
				throw new ControladorException(
						"atencao.quantidade_hora_invalida", null, "início de ");
			}

		}

		if (servicoTipoPrioridade.getPrazoExecucaoFim() != null) {

			if ((servicoTipoPrioridade.getPrazoExecucaoFim() < 0)
					|| (servicoTipoPrioridade.getPrazoExecucaoFim() == 0)) {
				throw new ControladorException(
						"atencao.quantidade_hora_invalida", null,
						"finalizar a ");
			}

		}

		servicoTipoPrioridade
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		servicoTipoPrioridade.setTmCadastramento(new Date());

		servicoTipoPrioridade.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSação----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SERVICO_TIPO_PRIORIDADE_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SERVICO_TIPO_PRIORIDADE_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		servicoTipoPrioridade.setOperacaoEfetuada(operacaoEfetuada);
		servicoTipoPrioridade.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(servicoTipoPrioridade);

		// --------FIM---- REGISTRAR TRANSação----------------------------
		Integer idTPrioridadeipoServico = (Integer) getControladorUtil()
				.inserir(servicoTipoPrioridade);

		return idTPrioridadeipoServico;

	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * atualização da instalação de hidrômetro do Imóvel no momento da exibição.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirAtualizarInstalacaoHidrometro(
			OrdemServico ordemServico, boolean menu)
			throws ControladorException {

		// [FS0001] Validar Ordem de Servico
		this.getControladorOrdemServico().validaOrdemServicoAtualizacao(
				ordemServico, menu);

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		if (imovel == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null,
					ordemServico.getRegistroAtendimento().getId().toString());
		}

		int servicoTipo = ordemServico.getServicoTipo().getId().intValue();

		if (servicoTipo != ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO
				&& servicoTipo != ServicoTipo.TIPO_CONFIRMAR_DADOS_INSTALACAO_HIDROMETRO
				&& servicoTipo != ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO
				&& servicoTipo != ServicoTipo.TIPO_CONFIRMAR_DADOS_SUBSTITUICAO_HIDROMETRO) {

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.servico_associado_atualizar_instalacao_hidrometro_invalida");
		}

		// [FS0002] Verificar Situação do Imovel.

		if (ConstantesSistema.INDICADOR_IMOVEL_EXCLUIDO == imovel
				.getIndicadorExclusao()) {

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		// [FS0004] Validar Data do Encerramento da Ordem de Servico
		if (servicoTipo == ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO
				|| servicoTipo == ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO) {

			Date dataEncerramento = ordemServico.getDataEncerramento();
			if (dataEncerramento != null) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataEncerramento);

				int qtdDias = Util.obterQuantidadeDiasEntreDuasDatas(calendar
						.getTime(), new Date());
				FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

				Collection colecao = getControladorUtil().pesquisar(
						filtroSistemaParametro,
						SistemaParametro.class.getName());

				if (colecao != null && !colecao.isEmpty()) {

					SistemaParametro sistemaParametro = (SistemaParametro) Util
							.retonarObjetoDeColecao(colecao);
					int qtdMaximo = sistemaParametro.getDiasMaximoAlterarOS()
							.intValue();
					if (qtdDias > qtdMaximo) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.instalacao_hidrometro_data_encerramento_os_invalida",
								null, new String[] {
										ordemServico.getId().toString(),
										qtdMaximo + "" });
					}
				}
			}
		}
	}

	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param servicoTipoId
	 * @param imovelId
	 * @param tipoMedicao
	 * @return valor do Débito
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal obterValorDebito(Integer servicoTipoId, Integer imovelId,
			Short tipoMedicao) throws ControladorException {
		BigDecimal valorDebito = null;
		try {
			
			FiltroServicoTipo filtroServicoTipo =
				new FiltroServicoTipo();
			
			filtroServicoTipo
				.adicionarParametro(
						new ParametroSimples(FiltroServicoTipo.ID, servicoTipoId));	
			
			Collection colecaoServicoTipo = 
				getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
						
			ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
			
			ObterValorDebitoHelper obterValorDebitoHelper = new ObterValorDebitoHelper();			
			obterValorDebitoHelper.setServicoTipo(servicoTipo);
			ImovelPerfil imovelPerfil = this.getControladorImovel().obterImovelPerfil(imovelId);
			obterValorDebitoHelper.setImovelPerfil(imovelPerfil);
			
			if(servicoTipo.getIndicativoTipoSevicoEconomias()!=null &&
					servicoTipo.getIndicativoTipoSevicoEconomias().compareTo(ConstantesSistema.SIM)==0){
				// [UC0062] - Obter Quantidade de Economias
				Imovel imovel = new Imovel(imovelId);
				Integer qtdEconomiaImovel = this.getControladorImovel().obterQuantidadeEconomias(imovel);
				obterValorDebitoHelper.setQuantidadeEconomia(qtdEconomiaImovel);
			}
			
			if (tipoMedicao.intValue() == MedicaoTipo.LIGACAO_AGUA.intValue()) {
				if (this.repositorioAtendimentoPublico
						.verificarExistenciaHidrometroEmLigacaoAgua(imovelId)) {
					obterValorDebitoHelper
							.setSituacaoMedicao(ServicoCobrancaValor.INDICADOR_MEDICAO_SIM);
					HidrometroCapacidade hidrometroCapacidade = this.repositorioAtendimentoPublico
							.obterHidrometroCapacidadeEmLigacaoAgua(imovelId);
					obterValorDebitoHelper
							.setHidrometroCapacidade(hidrometroCapacidade);
				} else {
					obterValorDebitoHelper
							.setSituacaoMedicao(ServicoCobrancaValor.INDICADOR_MEDICAO_NAO);
				}
			} else if (tipoMedicao.intValue() == MedicaoTipo.POCO.intValue()) {
				if (this.repositorioAtendimentoPublico
						.verificarExistenciaHidrometroEmImovel(imovelId)) {
					obterValorDebitoHelper
							.setSituacaoMedicao(ServicoCobrancaValor.INDICADOR_MEDICAO_SIM);
					HidrometroCapacidade hidrometroCapacidade = this.repositorioAtendimentoPublico
							.obterHidrometroCapacidadeEmImovel(imovelId);
					obterValorDebitoHelper
							.setHidrometroCapacidade(hidrometroCapacidade);
				} else {
					obterValorDebitoHelper
							.setSituacaoMedicao(ServicoCobrancaValor.INDICADOR_MEDICAO_NAO);
				}
			}
			
			Collection<Subcategoria> colecaoSubs =
				this.getControladorImovel().obterQuantidadeEconomiasSubCategoria(imovelId);
			
			List listaSubcategorias = (List) colecaoSubs;
			
			Collections.sort(listaSubcategorias,  
			        new Comparator() {  
			           public int compare(Object left, Object right) {  
			           Subcategoria leftKey = (Subcategoria) left;  
			           Subcategoria rightKey = (Subcategoria) right;  
		               return leftKey.getQuantidadeEconomias().compareTo(rightKey.getQuantidadeEconomias());  
		             }  
	        });
			
			Subcategoria principalSubcategoria = 
				(Subcategoria) listaSubcategorias.get(listaSubcategorias.size()-1);
			
			obterValorDebitoHelper.setSubcategoria(principalSubcategoria);
			
			Categoria categoria = 
				this.getControladorImovel().obterPrincipalCategoriaImovel(imovelId);
			
			obterValorDebitoHelper.setCategoria(categoria);
												 
			valorDebito = this.repositorioAtendimentoPublico
					.obterValorDebito(obterValorDebitoHelper);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return valorDebito;
	}

	/**
	 * método que retorna o número do hidrômetro da Ligação de Água
	 * 
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometroLigacaoAgua(Integer idLigacaoAgua)
			throws ControladorException {

		try {
			return repositorioAtendimentoPublico
					.pesquisarNumeroHidrometroLigacaoAgua(idLigacaoAgua);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * método que retorna o tipo da Ligação de Água e a data do corte da Ligação
	 * de Água
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLigacaoAgua(Integer idLigacaoAgua)
			throws ControladorException {

		try {
			return repositorioAtendimentoPublico
					.pesquisarDadosLigacaoAgua(idLigacaoAgua);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Consulta os dados das ordens de Serviço para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @created 07/10/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarOrdemServicoProgramacaoRelatorio(
			Integer idEquipe, Date dataRoteiro) throws ControladorException {

		try {
			Collection colecaoDadosOrdemServicoProgramacao = this.repositorioAtendimentoPublico
					.pesquisarOrdemServicoProgramacaoRelatorio(idEquipe,
							dataRoteiro);

			Collection colecaoOrdensServicoProgramacao = new ArrayList();
			Iterator colecaoDadosOrdemServicoProgramacaoIterator = colecaoDadosOrdemServicoProgramacao
					.iterator();
			while (colecaoDadosOrdemServicoProgramacaoIterator.hasNext()) {

				OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();

				// Obtém os dados do crédito realizado
				Object[] dadosArray = (Object[]) colecaoDadosOrdemServicoProgramacaoIterator
						.next();

				// Sequencial
				if (dadosArray[0] != null) {
					ordemServicoProgramacao
							.setNnSequencialProgramacao((Short) dadosArray[0]);
				}

				RegistroAtendimento registroAtendimento = new RegistroAtendimento();

				// número do RA
				if (dadosArray[1] != null) {
					registroAtendimento.setId((Integer) dadosArray[1]);
				}

				OrdemServico ordemServico = new OrdemServico();

				// número da OS
				if (dadosArray[2] != null) {
					ordemServico.setId((Integer) dadosArray[2]);
				}

				// Tipo de Serviço
				if (dadosArray[3] != null) {
					ServicoTipo servicoTipo = new ServicoTipo();
					servicoTipo.setId((Integer) dadosArray[3]);
					ordemServico.setServicoTipo(servicoTipo);
				}

				// Observação
				if (dadosArray[4] != null) {
					ordemServico.setObservacao((String) dadosArray[4]);
				} else {
					ordemServico.setObservacao("");
				}

				ordemServico.setRegistroAtendimento(registroAtendimento);
				ordemServicoProgramacao.setOrdemServico(ordemServico);

				colecaoOrdensServicoProgramacao.add(ordemServicoProgramacao);
			}

			return colecaoOrdensServicoProgramacao;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro na Ligação de Água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmLigacaoAgua(Integer imovelId)
			throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.verificarExistenciaHidrometroEmLigacaoAgua(imovelId);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro na Ligação de Água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmImovel(Integer imovelId)
			throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.verificarExistenciaHidrometroEmImovel(imovelId);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0387] Manter Tipo Perfil Serviço [SB0002] Remover Tipo Perfil Serviço
	 * 
	 * @author Kassia Albuquerque
	 * @date 08/11/2006
	 * 
	 * @pparam servicoPerfilTipo
	 * @throws ControladorException
	 */
	public void removerServicoTipoPerfil(String[] ids, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSação ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_TIPO_PERFIL_SERVICO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSação ----------------

		this.getControladorUtil().remover(ids,
				ServicoPerfilTipo.class.getName(), operacaoEfetuada,
				colecaoUsuarios);

	}

	/**
	 * [UC0387] Manter Tipo Perfil Serviço [SB0001] Atualizar Tipo Perfil
	 * Serviço
	 * 
	 * @author Kassia Albuquerque
	 * @date 01/11/2006
	 * 
	 * @pparam servicoPerfilTipo
	 * @throws ControladorException
	 */
	public void atualizarServicoTipoPerfil(ServicoPerfilTipo servicoPerfilTipo,
			Usuario usuarioLogado) throws ControladorException {

		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSação----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_TIPO_PERFIL_SERVICO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_TIPO_PERFIL_SERVICO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		servicoPerfilTipo.setOperacaoEfetuada(operacaoEfetuada);
		servicoPerfilTipo.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(servicoPerfilTipo);
		// ------------ REGISTRAR TRANSação----------------------------

		// [FS0003] - Atualização realizada por outro usuário
		FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
		// Seta o filtro para buscar o servicoPerfilTipo na base
		filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoPerfilTipo.ID, servicoPerfilTipo.getId()));

		// Procura servicoPerfilTipo na base
		Collection servicoPerfilTipoAtualizadas = getControladorUtil()
				.pesquisar(filtroServicoPerfilTipo,
						ServicoPerfilTipo.class.getName());

		ServicoPerfilTipo servicoPerfilTipoNaBase = (ServicoPerfilTipo) Util
				.retonarObjetoDeColecao(servicoPerfilTipoAtualizadas);

		if (servicoPerfilTipoNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente");
		}

		// Verificar se o servicoPerfilTipo já foi atualizado por outro usuário
		// durante esta atualização

		if (servicoPerfilTipoNaBase.getUltimaAlteracao().after(
				servicoPerfilTipo.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		servicoPerfilTipo.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(servicoPerfilTipo);

	}

	/**
	 * Faz o controle de concorrencia de ligacao Agua
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	private void verificarEspecificacaoImovelSituacaoControleConcorrencia(
			EspecificacaoImovelSituacao especificacaoImovelSituacao)
			throws ControladorException {

		FiltroEspecificacaoImovelSituacao filtro = new FiltroEspecificacaoImovelSituacao();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroEspecificacaoImovelSituacao.ID,
				especificacaoImovelSituacao.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro,
				EspecificacaoImovelSituacao.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		EspecificacaoImovelSituacao especificacaoImovelSituacaoAtual = (EspecificacaoImovelSituacao) Util
				.retonarObjetoDeColecao(colecao);

		if (especificacaoImovelSituacaoAtual.getUltimaAlteracao().after(
				especificacaoImovelSituacao.getUltimaAlteracao())) {

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * [UC0404] Manter Especificação da Situação do Imovel
	 * 
	 * Este caso de uso remove a especificação e os critério
	 * 
	 * [SB0002] Remover Especificação da situacao
	 * 
	 * @author Rafael Pinto
	 * @created 08/11/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerEspecificacaoSituacaoImovel(
			String[] idsEspecificacaoSituacaoImovel, Usuario usuario,
			Date ultimaAlteracao) throws ControladorException {

		try {

			for (int i = 0; i < idsEspecificacaoSituacaoImovel.length; i++) {
				String id = idsEspecificacaoSituacaoImovel[i];

				EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();

				especificacaoImovelSituacao.setId(new Integer(id));
				especificacaoImovelSituacao.setUltimaAlteracao(ultimaAlteracao);

				this
						.verificarEspecificacaoImovelSituacaoControleConcorrencia(especificacaoImovelSituacao);
			}

			Operacao operacao = new Operacao();
			operacao
					.setId(Operacao.OPERACAO_ESPECIFICACAO_SITUACAO_IMOVEL_REMOVER);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			repositorioAtendimentoPublico
					.removerEspecificacaoSituacaoImovelCriterio(idsEspecificacaoSituacaoImovel);

			UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
					usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

			Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
			colecaoUsuarios.add(usuarioAcaoUsuarioHelper);

			getControladorUtil().remover(idsEspecificacaoSituacaoImovel,
					EspecificacaoImovelSituacao.class.getName(),
					operacaoEfetuada, colecaoUsuarios);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0383] Manter Material [SB0003] Remover Material
	 * 
	 * @author Kassia Albuquerque
	 * @date 16/11/2006
	 * 
	 * @pparam material
	 * @throws ControladorException
	 */
	public void removerMaterial(String[] ids, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSação ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MATERIAL_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSação ----------------

		this.getControladorUtil().remover(ids, Material.class.getName(),
				operacaoEfetuada, colecaoUsuarios);

	}

	/**
	 * [UC0383] Manter Material [SB0001] Atualizar Material
	 * 
	 * @param Material  
	 * @param Usuario Logado
	 * 
	 * @author Diogo Peixoto
	 * @date 18/08/2011
	 */
	public void atualizarMaterial(Material material, Usuario usuarioLogado) throws ControladorException{

		Integer codigoMaterial = material.getCodigo();
		String descricaoMaterial = material.getDescricao();
		String descricaoAbreviadaMaterial = material.getDescricaoAbreviada();
		
		// [FS0001] Verificar existencia da Descrição
		FiltroMaterial filtroMaterial = new FiltroMaterial();
		filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.DESCRICAO, descricaoMaterial));
		filtroMaterial.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroMaterial.ID, material.getId()));
		Collection<Material> colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());

		if (!Util.isVazioOrNulo(colecaoMaterial)) {
			throw new ControladorException("atencao.descricao_ja_existente_material");
		}
		
		if (Util.verificarNaoVazio(descricaoAbreviadaMaterial)) {
			filtroMaterial.limparListaParametros();
			// [FS0002]- Verificar existência da Descrição abreviada
			filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.DESCRICAO_ABREVIADA, descricaoAbreviadaMaterial));
			filtroMaterial.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroMaterial.ID, material.getId()));
			colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());
			
			if (!Util.isVazioOrNulo(colecaoMaterial)) {
				throw new ControladorException("atencao.descricao_abreviada_ja_existente_material");
			}
		}

		if(codigoMaterial != null){
			filtroMaterial.limparListaParametros();
			//[FS0008]- Verificar existência do código
			filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.CODIGO, codigoMaterial));
			filtroMaterial.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroMaterial.ID, material.getId()));
			colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());
			
			if (!Util.isVazioOrNulo(colecaoMaterial)) {
				throw new ControladorException("atencao.codigo_ja_existente_material");
			}
		}
		
		material.setUltimaAlteracao(new Date());

		// ------------ INICIO REGISTRAR TRANSação----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MATERIAL_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MATERIAL_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		material.setOperacaoEfetuada(operacaoEfetuada);
		material.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(material);
		// ------------ FIM REGISTRAR TRANSação----------------------------

		// [FS0005] - Atualização realizada por outro usuário
		FiltroMaterial filtroMaterialBase = new FiltroMaterial();
		// Seta o filtro para buscar o material na base
		filtroMaterialBase.adicionarParametro(new ParametroSimples(FiltroMaterial.ID, material.getId()));

		// Procura material na base
		Collection<Material> materialAtualizadas = getControladorUtil().pesquisar(filtroMaterialBase, Material.class.getName());
		Material materialNaBase = (Material) Util.retonarObjetoDeColecao(materialAtualizadas);

		if (materialNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o material já foi atualizado por outro usuário
		// durante esta atualização

		if (materialNaBase.getUltimaAlteracao().after(material.getUltimaAlteracao())){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		material.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		this.getControladorUtil().atualizar(material);
	}

	/**
	 * [UC0498] Efetuar Ligação de Água com Instalação de hidrômetro.
	 * 
	 * Permite validar o efetuar Ligação de Água com Instalação de hidrômetro
	 * Exibir ou pelo menu ou pela funcionalidade encerrar a Execução da ordem
	 * de serviço.
	 * 
	 * [FS0008] Verificar Situação Rede de Água na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Água do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 27/11/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaComInstalacaoHidrometroExibir(
			OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR LIGACAO AGUA COM INSTALACAO HIDROMETRO, não será
		 * necessário realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(ordem.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordem.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO) {
				throw new ControladorException(
						"atencao.servico_associado_ligacao_agua_instalacao_hidrometro_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordem,
				veioEncerrarOS);
		// Caso 4
		if (ordem.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordem.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordem.getRegistroAtendimento().getImovel();

		// [FS0002] Validar Situação de Água do Imóvel.
		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.POTENCIAL
				.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.FACTIVEL
						.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.EM_FISCALIZACAO
						.intValue()) {

			throw new ControladorException(
					"atencao.situacao_validar_ligacao_agua_invalida_exibir",
					null, imovel.getLigacaoAguaSituacao().getDescricao());
		}

		/*
		 * [FS0007] Verificar Situação Rede de Água na Quadra
		 * 
		 * Integração com o conceito de face da quadra Raphael Rossiter em
		 * 21/05/2009
		 */
		IntegracaoQuadraFaceHelper integracao = this.getControladorLocalidade()
				.integracaoQuadraFace(imovel.getId());

		if ((integracao.getIndicadorRedeAgua()).equals(Quadra.SEM_REDE)) {

			throw new ControladorException(
					"atencao.seituacao_rede_agua_quadra", null, imovel.getId()
							+ "");
		}

		// [FS0006] Verificar Situação do Imovel
		if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			throw new ControladorException(
					"atencao.situacao_imovel_indicador_exclusao", null, imovel
							.getId()
							+ "");
		}

		// [FS0014] - Verificar existência de hidrômetro na Ligação de Água
		if (imovel.getLigacaoAgua() != null
				&& imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
			throw new ControladorException(
					"atencao.hidrometro_instalado_ligacao_agua", null, ""
							+ imovel.getId());
		}

		/*
		 * ===================================================================================
		 */
	}

	/**
	 * [UC0540] Efetuar Restabelecimento da Ligação de Água com Instalação de
	 * hidrômetro.
	 * 
	 * Permite validar o Efetuar Restabelecimento Ligação de Água com Instalação
	 * de hidrômetro Exibir ou pelo menu ou pela funcionalidade encerrar a
	 * Execução da ordem de serviço.
	 * 
	 * [FS0008] Verificar Situação Rede de Água na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Água do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 18/04/2007
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarRestabelecimentoLigacaoAguaComInstalacaoHidrometroExibir(
			OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException {

		// [FS0001] Validar Ordem de Servico

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR LIGACAO AGUA COM INSTALACAO HIDROMETRO, não será
		 * necessário realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(ordem.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_DE_HIDROMETRO_INT) {

			// Caso 2
			if (ordem.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO) {
				throw new ControladorException(
						"atencao.servico_associado_restabelecimento_ligacao_agua_instalacao_hidrometro_invalida");
			}

		}

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordem,
				veioEncerrarOS);
		// Caso 4
		if (ordem.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordem.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordem.getRegistroAtendimento().getImovel();

		// [FS0002] Validar Situação de Água do Imóvel.
		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPRIMIDO
				.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPR_PARC_PEDIDO
						.intValue()) {

			throw new ControladorException(
					"atencao.situacao_ligacao_agua_invalida_restabelecimento",
					null, imovel.getLigacaoAguaSituacao().getDescricao());
		}

		/*
		 * [FS0007] Verificar Situação Rede de Água na Quadra
		 * 
		 * Integração com o conceito de face da quadra Raphael Rossiter em
		 * 21/05/2009
		 */
		IntegracaoQuadraFaceHelper integracao = this.getControladorLocalidade()
				.integracaoQuadraFace(imovel.getId());

		if ((integracao.getIndicadorRedeAgua()).equals(Quadra.SEM_REDE)) {

			throw new ControladorException(
					"atencao.seituacao_rede_agua_quadra", null, imovel.getId()
							+ "");
		}

		// [FS0006] Verificar Situação do Imovel
		if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			throw new ControladorException(
					"atencao.situacao_imovel_indicador_exclusao", null, imovel
							.getId()
							+ "");
		}

		// [FS0014] - Verificar existência de hidrômetro na Ligação de Água
		if (imovel.getLigacaoAgua() != null
				&& imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
			throw new ControladorException(
					"atencao.hidrometro_instalado_ligacao_agua", null, ""
							+ imovel.getId());
		}
	}

	/**
	 * [UC0294] Manter Prioridade Tipo Servico [] Atualizar Prioridade do Tipo
	 * Servico Metodo que atualiza a Prioridade do Tipo de Servico
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 25/05/2006
	 * 
	 * @param Prioridade
	 *            Tipo Servico
	 * 
	 * @throws ControladorException
	 */

	public void atualizarPrioridadeTipoServico(
			ServicoTipoPrioridade servicoTipoPrioridade,
			Collection colecaoServicoTipoPrioridade)
			throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((servicoTipoPrioridade.getDescricao() == null || servicoTipoPrioridade
				.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (servicoTipoPrioridade.getDescricaoAbreviada() == null || servicoTipoPrioridade
						.getDescricaoAbreviada().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (servicoTipoPrioridade.getId() == null || servicoTipoPrioridade
						.getId().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (servicoTipoPrioridade.getPrazoExecucaoInicio() == null || servicoTipoPrioridade
						.getPrazoExecucaoInicio().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (servicoTipoPrioridade.getPrazoExecucaoFim() == null || servicoTipoPrioridade
						.getPrazoExecucaoFim().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Descrição da Prioridade foi preenchido

		if (servicoTipoPrioridade.getDescricao() == null
				|| servicoTipoPrioridade.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Descrição da Prioridade");
		}

		// Verifica se o campo Abreviatura da Prioridade foi preenchido

		if (servicoTipoPrioridade.getDescricaoAbreviada() == null
				|| servicoTipoPrioridade.getDescricaoAbreviada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Abreviatura da Prioridade");
		}

		// Verifica se o campo Horas para Execução do Serviço foi preenchido

		if (servicoTipoPrioridade.getPrazoExecucaoInicio() == null
				|| servicoTipoPrioridade.getPrazoExecucaoInicio().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Horas para Execução do Serviço");
		}

		// Verifica se o campo Horas para Finalização do Serviço foi preenchido

		if (servicoTipoPrioridade.getPrazoExecucaoFim() == null
				|| servicoTipoPrioridade.getPrazoExecucaoFim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Horas para Finalização do Serviço");
		}

		// [FS0003] - Atualização realizada por outro usuário
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoPrioridade.ID, servicoTipoPrioridade.getId()));

		Collection colecaoServicoTipoPrioridadeBase = getControladorUtil()
				.pesquisar(filtroServicoTipoPrioridade,
						ServicoTipoPrioridade.class.getName());

		if (colecaoServicoTipoPrioridadeBase == null
				|| colecaoServicoTipoPrioridadeBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ServicoTipoPrioridade servicoTipoBase = (ServicoTipoPrioridade) colecaoServicoTipoPrioridadeBase
				.iterator().next();

		if (servicoTipoBase.getUltimaAlteracao().after(
				servicoTipoBase.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		servicoTipoPrioridade.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(servicoTipoPrioridade);

	}

	/**
	 * [UC0498] Efetuar Ligação de Água com Instalação de hidrômetro.
	 * 
	 * Permite efetuar Ligação de Água com Instalação de Hidrômetro ou pelo menu
	 * ou pela funcionalidade encerrar a Execução da ordem de serviço.
	 * 
	 * @author Rafael Corrêa
	 * @date 29/11/2006
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarLigacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException {

		/*
		 * [UC0107] Registrar Transação
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO,
				integracaoComercialHelper.getLigacaoAgua().getId(),
				integracaoComercialHelper.getLigacaoAgua().getId(),
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		LigacaoAgua ligacaoAgua = integracaoComercialHelper.getLigacaoAgua();
		Imovel imovel = integracaoComercialHelper.getImovel();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();

		ligacaoAgua.setId(imovel.getId());

		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		
		if (ligacaoAgua != null && imovel != null) {

			// [SB0001] Gerar Ligação de Água

			// LAGU_DTIMPLANTACAO
			ligacaoAgua.setDataImplantacao(new Date());

			// LAGU_TMULTIMAALTERACAO
			Date dataCorrente = new Date();
			ligacaoAgua.setUltimaAlteracao(dataCorrente);

			// FiltroLigacaoAgua filtroLigacaoAgua = new
			// FiltroLigacaoAgua();
			// filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
			// FiltroLigacaoAgua.ID, imovel.getId()));
			// Collection colecaoLigacaoAguaBase = getControladorUtil()
			// .pesquisar(filtroLigacaoAgua,
			// LigacaoAgua.class.getName());
			//
			// if (!colecaoLigacaoAguaBase.isEmpty()) {
			// sessionContext.setRollbackOnly();
			// throw new ControladorException(
			// "atencao.imovel_ja_existente", null, ""
			// + ligacaoAgua.getId());
			// }

			// regitrando operacao
			ligacaoAgua
					.setHidrometroInstalacaoHistorico(integracaoComercialHelper
							.getHidrometroInstalacaoHistorico());
			
			//Alteração feita por Sávio Luiz
			//Data: 25/05/2011
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAgua.ID, imovel.getId()));
			Collection colecaoLigacaoAguaBase = getControladorUtil().pesquisar(
					filtroLigacaoAgua, LigacaoAgua.class.getName());

			registradorOperacao.registrarOperacao(ligacaoAgua);
			if (!colecaoLigacaoAguaBase.isEmpty()) {
				getControladorUtil().atualizar(ligacaoAgua);
			}else{
				getControladorUtil().inserir(ligacaoAgua);
			}
			
		}

		Integer id = null;

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper
				.getHidrometroInstalacaoHistorico();

		validacaoInstalacaoHidrometro(hidrometroInstalacaoHistorico
				.getHidrometro().getNumero());

		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		id = (Integer) getControladorUtil().inserir(
				hidrometroInstalacaoHistorico);

		try {

			// Caso o tipo de medição seja igual a Ligação de Água, atualiza as
			// colunas da tabela LIGACAO_AGUA
			// if
			// (hidrometroInstalacaoHistorico.getMedicaoTipo().getId().equals(
			// MedicaoTipo.LIGACAO_AGUA)) {
			//				
			// // Caso o tipo de medição seja igual a Poço, atualiza as colunas
			// // da tabela POCO
			// } else if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId()
			// .equals(MedicaoTipo.POCO)) {
			// repositorioAtendimentoPublico
			// .atualizarHidrometroIntalacaoHistoricoImovel(
			// hidrometroInstalacaoHistorico.getImovel()
			// .getId(), id);
			// }

			// [SB003]Atualizar situação de hidrômetro na tabela HIDROMETRO
			Integer situacaoHidrometro = HidrometroSituacao.INSTALADO;
			repositorioAtendimentoPublico.atualizarSituacaoHidrometro(
					hidrometroInstalacaoHistorico.getHidrometro().getId(),
					situacaoHidrometro);

			hidrometroInstalacaoHistorico.setId(id);
			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);

			ligacaoAgua
					.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			if (ligacaoAgua != null && imovel != null) {

				// // [SB0001] Gerar Ligação de Água
				//
				// // LAGU_DTIMPLANTACAO
				// ligacaoAgua.setDataImplantacao(new Date());
				//
				// // LAGU_TMULTIMAALTERACAO
				// Date dataCorrente = new Date();
				// ligacaoAgua.setUltimaAlteracao(dataCorrente);
				//
				// // FiltroLigacaoAgua filtroLigacaoAgua = new
				// // FiltroLigacaoAgua();
				// // filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				// // FiltroLigacaoAgua.ID, imovel.getId()));
				// // Collection colecaoLigacaoAguaBase = getControladorUtil()
				// // .pesquisar(filtroLigacaoAgua,
				// // LigacaoAgua.class.getName());
				// //
				// // if (!colecaoLigacaoAguaBase.isEmpty()) {
				// // sessionContext.setRollbackOnly();
				// // throw new ControladorException(
				// // "atencao.imovel_ja_existente", null, ""
				// // + ligacaoAgua.getId());
				// // }
				//
				// // regitrando operacao
				// ligacaoAgua.setOperacaoEfetuada(operacaoEfetuada);
				// ligacaoAgua.adicionarUsuario(usuario,
				// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				// registradorOperacao.registrarOperacao(ligacaoAgua);
				//
				// getControladorUtil().inserir(ligacaoAgua);

				repositorioAtendimentoPublico
						.atualizarHidrometroInstalacaoHistoricoLigacaoAgua(
								hidrometroInstalacaoHistorico.getLigacaoAgua()
										.getId(), id);

				LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
				ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);

				getControladorImovel()
						.atualizarImovelExecucaoOrdemServicoLigacaoAgua(imovel,
								ligacaoAguaSituacao);

				if (imovel.getLigacaoEsgotoSituacao() != null
						&& imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO
								.intValue()) {

					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);

					imovel.setUltimaAlteracao(new Date());
					// [SB0002] Atualizar Imóvel
					getControladorImovel()
							.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(
									imovel, ligacaoEsgotoSituacao);
				}
			}
			if (ordemServico != null) {
				registradorOperacao.registrarOperacao(ordemServico);

				if (!integracaoComercialHelper.isVeioEncerrarOS()
						&& ordemServico.getServicoTipo().getDebitoTipo() != null) {

					this.getControladorOrdemServico()
							.verificarOrdemServicoControleConcorrencia(
									ordemServico);
					getControladorOrdemServico().atualizaOSGeral(ordemServico);
				}

				if (ordemServico.getServicoTipo().getDebitoTipo() != null
						&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
					getControladorOrdemServico().gerarDebitoOrdemServico(
							ordemServico.getId(),
							ordemServico.getServicoTipo().getDebitoTipo()
									.getId(), ordemServico.getValorAtual(),
							new Integer(qtdParcelas),
							ordemServico.getPercentualCobranca().toString(),
							integracaoComercialHelper.getUsuarioLogado());
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0540] Efetuar Restabelecimento da Ligação de Água com Instalação de
	 * hidrômetro.
	 * 
	 * Permite efetuar o Restabelecimento Ligação de Água com Instalação de
	 * Hidrômetro ou pelo menu ou pela funcionalidade encerrar a Execução da
	 * ordem de serviço.
	 * 
	 * @author Rafael Corrêa
	 * @date 19/04/2007
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException {

		LigacaoAgua ligacaoAgua = integracaoComercialHelper.getLigacaoAgua();
		Imovel imovel = integracaoComercialHelper.getImovel();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();

		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		
		/*
		 * [UC0107] Registrar Transação
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_DE_HIDROMETRO,
				imovel.getId(), imovel.getId(), new UsuarioAcaoUsuarioHelper(
						usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		if (ligacaoAgua != null && imovel != null) {

			// [SB0001] Gerar Ligação de Água

			// LAGU_TMULTIMAALTERACAO
			Date dataCorrente = new Date();
			ligacaoAgua.setUltimaAlteracao(dataCorrente);

			// regitrando operacao
			registradorOperacao.registrarOperacao(ligacaoAgua);

			getControladorUtil().atualizar(ligacaoAgua);
		}

		Integer id = null;

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper
				.getHidrometroInstalacaoHistorico();

		validacaoInstalacaoHidrometro(hidrometroInstalacaoHistorico
				.getHidrometro().getNumero());

		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		id = (Integer) getControladorUtil().inserir(
				hidrometroInstalacaoHistorico);

		try {

			// [SB003]Atualizar situação de hidrômetro na tabela HIDROMETRO
			Integer situacaoHidrometro = HidrometroSituacao.INSTALADO;
			repositorioAtendimentoPublico.atualizarSituacaoHidrometro(
					hidrometroInstalacaoHistorico.getHidrometro().getId(),
					situacaoHidrometro);

			hidrometroInstalacaoHistorico.setId(id);
			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);

			ligacaoAgua
					.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			if (ligacaoAgua != null && imovel != null) {

				repositorioAtendimentoPublico
						.atualizarHidrometroInstalacaoHistoricoLigacaoAgua(
								hidrometroInstalacaoHistorico.getLigacaoAgua()
										.getId(), id);

				LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
				ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);

				getControladorImovel()
						.atualizarImovelExecucaoOrdemServicoLigacaoAgua(imovel,
								ligacaoAguaSituacao);

				if (imovel.getLigacaoEsgotoSituacao() != null
						&& imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO
								.intValue()) {

					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);

					imovel.setUltimaAlteracao(new Date());
					// [SB0002] Atualizar Imóvel
					getControladorImovel()
							.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(
									imovel, ligacaoEsgotoSituacao);
				}
			}

			registradorOperacao.registrarOperacao(ordemServico);

			if (!integracaoComercialHelper.isVeioEncerrarOS()
					&& ordemServico.getServicoTipo().getDebitoTipo() != null) {

				this
						.getControladorOrdemServico()
						.verificarOrdemServicoControleConcorrencia(ordemServico);
				getControladorOrdemServico().atualizaOSGeral(ordemServico);
			}

			if (ordemServico.getServicoTipo().getDebitoTipo() != null
					&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
				getControladorOrdemServico().gerarDebitoOrdemServico(
						ordemServico.getId(),
						ordemServico.getServicoTipo().getDebitoTipo().getId(),
						ordemServico.getValorAtual(), new Integer(qtdParcelas),
						ordemServico.getPercentualCobranca().toString(),
						integracaoComercialHelper.getUsuarioLogado());
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * @author Rafael Pinto
	 * @date 22/02/2007
	 * 
	 * @param servicoTipoId
	 * @param imovelId
	 * @param tipoMedicao
	 * @param idHidrometroCapacidade
	 * 
	 * @return valor do Débito
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal obterValorDebito(Integer servicoTipoId, Integer imovelId,
			HidrometroCapacidade hidrometroCapacidade)
			throws ControladorException {

		BigDecimal valorDebito = null;

		try {

			ObterValorDebitoHelper obterValorDebitoHelper = new ObterValorDebitoHelper();

			ServicoTipo servicoTipo = new ServicoTipo();
			servicoTipo.setId(servicoTipoId);
			obterValorDebitoHelper.setServicoTipo(servicoTipo);

			ImovelPerfil imovelPerfil = this.getControladorImovel()
					.obterImovelPerfil(imovelId);
			obterValorDebitoHelper.setImovelPerfil(imovelPerfil);

			obterValorDebitoHelper
					.setSituacaoMedicao(ServicoCobrancaValor.INDICADOR_MEDICAO_SIM);
			obterValorDebitoHelper
					.setHidrometroCapacidade(hidrometroCapacidade);

			valorDebito = this.repositorioAtendimentoPublico
					.obterValorDebito(obterValorDebitoHelper);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return valorDebito;
	}

	/**
	 * 
	 * Este método se destina a validar a ordem de servico do [UC0555]-Alterar
	 * Situacao Ligacao
	 * 
	 * @author Romulo Aurelio
	 * @date 27/03/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarOrdemServicoAlterarSituacaoLigacao(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException {

		// [FS0001] Validar Ordem de Servico
		// Caso 2
		if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_ALTERACAO_SITUACAO_LIGACAO) {
			throw new ControladorException(
					"atencao.servico_associado_alteracao_situacao_ligacao_invalida");
		}
		// Caso 3
		/*
		 * this.getControladorOrdemServico().validaOrdemServico(ordemServico,
		 * veioEncerrarOS);
		 */
		if (ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO) {
			throw new ControladorException("atencao.os.encerrada");
		}

		/*
		 * if (ordemServico.getImovel() == null) { throw new
		 * ControladorException( "atencao.ordem_servico_ra_imovel_invalida",
		 * null, "" + ordemServico.getRegistroAtendimento().getId()); }
		 */

		// Comentado por Raphael Rossiter em 28/02/2007
		// Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		/*
		 * Imovel imovel = ordemServico.getImovel(); // [FS0002] Verificar
		 * Situação do Imovel. if (imovel.getIndicadorExclusao() != null &&
		 * imovel.getIndicadorExclusao().intValue() !=
		 * ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
		 * 
		 * throw new ControladorException(
		 * "atencao.atualizar_imovel_situacao_invalida", null, imovel
		 * .getId().toString()); }
		 * 
		 * if (imovel.getLigacaoAgua() == null) { throw new
		 * ControladorException("atencao.naocadastrado", null, "Ligação de
		 * Água"); }
		 */

		// [FS0003] Verificar a situação de Água
		/*
		 * if (imovel.getLigacaoAguaSituacao().getId().intValue() !=
		 * LigacaoAguaSituacao.LIGADO .intValue() &&
		 * imovel.getLigacaoAguaSituacao().getId().intValue() !=
		 * LigacaoAguaSituacao.CORTADO .intValue() &&
		 * imovel.getLigacaoAguaSituacao().getId().intValue() !=
		 * LigacaoAguaSituacao.SUPR_PARC_PEDIDO .intValue() &&
		 * imovel.getLigacaoAguaSituacao().getId().intValue() !=
		 * LigacaoAguaSituacao.EM_FISCALIZACAO .intValue() &&
		 * imovel.getLigacaoAguaSituacao().getId().intValue() !=
		 * LigacaoAguaSituacao.EM_CANCELAMENTO .intValue()) {
		 * 
		 * throw new ControladorException(
		 * "atencao.situacao_ligacao_agua_supressao_invalida", null,
		 * imovel.getLigacaoAguaSituacao().getDescricao() + ""); }
		 */

	}

	/**
	 * [UC0555] Alterar Situacao da Ligacao
	 * 
	 * @author Romulo Aurelio
	 * @date 27/03/2007
	 * 
	 * @param imovel
	 * @param indicadorTipoLigacao
	 * @param idSituacaoLigacaoAguaNova
	 * @param idSituacaoLigacaoEsgotoNova
	 * 
	 * @return idImovel
	 * 
	 * @throws ControladorException
	 */

	public Integer alterarSituacaoLigacao(Imovel imovel,
			String indicadorTipoLigacao, String idSituacaoLigacaoAguaNova,
			String idSituacaoLigacaoEsgotoNova, String idOrdemServico,
			Usuario usuarioLogado) throws ControladorException {

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SITUACAO_LIGACAO_ALTERAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SITUACAO_LIGACAO_ALTERAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// [UC0107] -Fim- Registrar Transação

		// [FS0009] - Verificar existencia de debitos
		// adicionadao por Vivianne Sousa - 18/03/2009 - analista: Dennys
		boolean temPermissaoAlterarSituacaoLigacaoParaImovelComDebito = getControladorPermissaoEspecial()
				.verificarPermissaoAlterarSituacaoLigacaoParaImovelComDebito(
						usuarioLogado);
		// caso o usuario tenha permisão especial, não verificar existencia de
		// debitos
		if (!temPermissaoAlterarSituacaoLigacaoParaImovelComDebito) {

			ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = getControladorCobranca()
					.obterDebitoImovelOuCliente(1, imovel.getId().toString(),
							null, null, "190101", "999912",
							Util.converteStringParaDate("01/01/1901"),
							Util.converteStringParaDate("31/12/9999"), 1, 1, 1,
							1, 1, 1, 1, null);

			Integer quantidadeDebitos = 0;
			if (obterDebitoImovelOuClienteHelper
					.getColecaoContasValoresImovel() != null) {
				quantidadeDebitos += obterDebitoImovelOuClienteHelper
						.getColecaoContasValoresImovel().size();
			}
			if (obterDebitoImovelOuClienteHelper
					.getColecaoGuiasPagamentoValores() != null) {
				quantidadeDebitos += obterDebitoImovelOuClienteHelper
						.getColecaoGuiasPagamentoValores().size();
			}
			if (obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null) {
				quantidadeDebitos += obterDebitoImovelOuClienteHelper
						.getColecaoCreditoARealizar().size();
			}
			if (obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null) {
				quantidadeDebitos += obterDebitoImovelOuClienteHelper
						.getColecaoDebitoACobrar().size();
			}
			if (quantidadeDebitos > 0) {
				throw new ControladorException(
						"atencao.existencia.debitos.alterar.ligacao.situacao");
			}

		}

		// [FS0006] - Verificar existencia de hidrometro

		if ((indicadorTipoLigacao.equalsIgnoreCase(""
				+ LigacaoTipo.LIGACAO_AGUA) || (indicadorTipoLigacao
				.equalsIgnoreCase("3") && !indicadorTipoLigacao
				.equalsIgnoreCase("-1")))
				&& idSituacaoLigacaoAguaNova != null) {

			if (!idSituacaoLigacaoAguaNova.equalsIgnoreCase(""
					+ LigacaoAguaSituacao.LIGADO)
					|| !idSituacaoLigacaoAguaNova.equalsIgnoreCase(""
							+ LigacaoAguaSituacao.FACTIVEL)) {

				if (imovel.getLigacaoAgua() != null) {

					FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
					filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
							FiltroLigacaoAgua.ID, imovel.getLigacaoAgua()
									.getId()));

					Collection colecaoLigacaoAgua = getControladorUtil()
							.pesquisar(filtroLigacaoAgua,
									LigacaoAgua.class.getName());

					filtroLigacaoAgua
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");

					LigacaoAgua ligacaoAgua = (LigacaoAgua) colecaoLigacaoAgua
							.iterator().next();

					if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.hidrometro_ja_instalado_ligacao_agua",
								null, ligacaoAgua.getId().toString());
					} else {
						// [SB0001]- Deletar Dados da Ligacao

						// Registrar Transacao
						ligacaoAgua.setOperacaoEfetuada(operacaoEfetuada);
						ligacaoAgua.adicionarUsuario(usuarioLogado,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
						registradorOperacao.registrarOperacao(ligacaoAgua);
						// Registrar Transacao

						/*
						 * Pesquisa o grupo na base de dados e verifica se o
						 * registro não foi atualizado por outro usuário durante
						 * essa transação
						 */
						getControladorUtil().remover(ligacaoAgua);
						// [SB0002]- Atualizar Situacao do Imovel
						LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
						ligacaoAguaSituacao.setId(new Integer(
								idSituacaoLigacaoAguaNova));
						imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					}

				} else {
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(new Integer(
							idSituacaoLigacaoAguaNova));
					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}
			}

		}

		if ((indicadorTipoLigacao.equalsIgnoreCase(""
				+ LigacaoTipo.LIGACAO_ESGOTO) || indicadorTipoLigacao
				.equalsIgnoreCase("3"))
				&& idSituacaoLigacaoEsgotoNova != null) {

			if (!idSituacaoLigacaoEsgotoNova.equalsIgnoreCase(""
					+ LigacaoEsgotoSituacao.POTENCIAL)
					|| !idSituacaoLigacaoEsgotoNova.equalsIgnoreCase(""
							+ LigacaoEsgotoSituacao.FACTIVEL)) {

				if (imovel.getHidrometroInstalacaoHistorico() != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.hidrometro_ja_instalado_ligacao_esgoto",
							null, imovel.getId().toString());

				} else {
					// [SB0001]- Deletar Dados da Ligacao
					LigacaoEsgoto ligacaoEsgoto = imovel.getLigacaoEsgoto();
					if (ligacaoEsgoto != null) {
						// Registrar Transacao
						ligacaoEsgoto.setOperacaoEfetuada(operacaoEfetuada);
						ligacaoEsgoto.adicionarUsuario(usuarioLogado,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
						registradorOperacao.registrarOperacao(ligacaoEsgoto);
						// Registrar Transacao

						getControladorUtil().remover(ligacaoEsgoto);
					}
					// [SB0002]- Atualizar Situacao do Imovel
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(new Integer(
							idSituacaoLigacaoEsgotoNova));
					imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				}
			}
		}

		imovel.setUltimaAlteracao(new Date());

		// [FS0005]- Atualizacao Realizada por outro Usuario
		FiltroImovel filtroImovelBase = new FiltroImovel();
		filtroImovelBase.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, imovel.getId()));
		Collection colecaoImovelBase = getControladorUtil().pesquisar(
				filtroImovelBase, Imovel.class.getSimpleName());
		if (colecaoImovelBase != null && !colecaoImovelBase.isEmpty()) {
			// Recupera o grupo na base de dados
			Imovel imovelBase = (Imovel) colecaoImovelBase.iterator().next();

			// [FS0004] - Atualização realizada por outro usuário
			if (imovelBase.getUltimaAlteracao().after(
					imovel.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}

		// Registrar Transacao

		imovel.setOperacaoEfetuada(operacaoEfetuada);
		imovel.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(imovel);

		// Registrar Transacao

		// [SB0002]- Atualizar Situacao do Imovel
		getControladorUtil().atualizar(imovel);

		// [SB0003]- Atualizar Ordem de Servico

		String idMotivoEncerramento = AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO
				.toString();
		// Chamada ao [UC0457]Encerrar Ordem de Servico
		getControladorOrdemServico().encerrarOSComExecucaoSemReferencia(
				new Integer(idOrdemServico), new Date(), usuarioLogado,
				idMotivoEncerramento, new Date(), null, null, null, null, null,
				null, null, null, null,null,null,null);

		return imovel.getId();

	}

	/**
	 * Pesquisa todos os ids das situações de ligação de água.
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoAgua()
			throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.pesquisarTodosIdsSituacaoLigacaoAgua();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa todos os ids das situações de ligação de esgoto.
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoEsgoto()
			throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.pesquisarTodosIdsSituacaoLigacaoEsgoto();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * Este cso de uso permite efetuar a ligação de água e eventualmente a
	 * instalação de hidrômetro, sem informação de RA sendo chamado direto pelo
	 * menu.
	 * 
	 * [UC0579] - Efetuar Ligação de Água com Intalação de Hidrômetro
	 * 
	 * @author Flávio Leonardo
	 * @date 25/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper pesquisarEfetuarLigacaoAguaHidrometroSemRA(
			Integer idImovel) throws ControladorException {
		EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper retorno = null;
		try {
			Collection objetos = repositorioAtendimentoPublico
					.pesquisarEfetuarLigacaoAguaHidrometroSemRA(idImovel);
			short indicadorExclusao = 0;
			short indicadorRedeAgua = 0;
			if (!objetos.isEmpty()) {
				Iterator iterator = objetos.iterator();
				while (iterator.hasNext()) {
					retorno = new EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper();
					Object[] objeto = (Object[]) iterator.next();

					// idImovel
					if (objeto[0] != null) {
						retorno.setMatriculaImovel(((Integer) objeto[0]) + "");
					}
					// nomeCliente
					if (objeto[1] != null) {
						retorno.setClienteUsuario((String) objeto[1]);
					}
					// cpf
					if (objeto[2] != null) {
						retorno.setCpfCnpjCliente((String) objeto[2]);
					} else // cnpj
					if (objeto[3] != null) {
						retorno.setCpfCnpjCliente((String) objeto[3]);
					}
					// situacaoLigacaoAgua
					if (objeto[4] != null) {
						retorno.setSituacaoLigacaoAgua((String) objeto[4]);
					}
					// situacaoLigacaoEsgoto
					if (objeto[5] != null) {
						retorno.setSituacaoLigacaoEsgoto((String) objeto[5]);
					}
					// indicadorExclusaoImovel
					if (objeto[6] != null) {
						indicadorExclusao = ((Short) objeto[6]);
					}

					/*
					 * [FS0007] Verificar Situação Rede de Água na Quadra
					 * 
					 * Integração com o conceito de face da quadra Raphael
					 * Rossiter em 22/05/2009
					 */
					IntegracaoQuadraFaceHelper integracao = this
							.getControladorLocalidade().integracaoQuadraFace(
									Integer.valueOf(retorno
											.getMatriculaImovel()));

					indicadorRedeAgua = integracao.getIndicadorRedeAgua();

					// idSituacaoLigacaoAgua
					if (objeto[8] != null) {
						retorno.setIdSituacaoLigacaoAgua((Integer) objeto[8]);
					}
					// idSituacaoLigacaoEsgoto
					if (objeto[9] != null) {
						retorno.setIdSituacaoLigacaoEsgoto((Integer) objeto[9]);
					}

				}

				// [FS0006] Verifica Situação do Imovel
				if (indicadorExclusao == ConstantesSistema.SIM) {
					throw new ControladorException(
							"atencao.atualizar_situacao_imovel_indicador_exclusao_esgoto",
							null, idImovel.toString());
				}
				// [FS0007] Verifica Situação rede de água da quadra
				if (indicadorRedeAgua == ConstantesSistema.SIM) {
					throw new ControladorException(
							"atencao.seituacao_rede_agua_quadra", null,
							idImovel.toString());
				}
				// [FS0002] Validar Situação de Água do Imóvel
				if (!retorno.getIdSituacaoLigacaoAgua().equals(
						LigacaoAguaSituacao.POTENCIAL)
						&& !retorno.getIdSituacaoLigacaoAgua().equals(
								LigacaoAguaSituacao.FACTIVEL)
						&& !retorno.getIdSituacaoLigacaoAgua().equals(
								LigacaoAguaSituacao.EM_FISCALIZACAO)) {
					throw new ControladorException(
							"atencao.situacao_ligacao_agua_invaliga", null,
							idImovel.toString());
				}
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0874] Gerar Contrato de Prestação de Serviço
	 * 
	 * @author Rafael Corrêa, Rômulo Aurélio
	 * @date 03/05/2007, 15/12/2008
	 * 
	 * @throws ControladorException
	 */
	public Collection obterDadosContratoPrestacaoServico(Integer idImovel,
			Integer idCliente) throws ControladorException {

		try {
			Collection colecaoDadosRelatorio = this.repositorioAtendimentoPublico
					.obterDadosContratoPrestacaoServico(idImovel);

			// Collection dadosUnidadeNegocioResponsavel =

			Collection colecaoContratoPrestacaoServicoHelper = new ArrayList();
			Iterator colecaoDadosRelatorioIterator = colecaoDadosRelatorio
					.iterator();
			while (colecaoDadosRelatorioIterator.hasNext()) {

				ContratoPrestacaoServicoHelper contratoPrestacaoServicoHelper = new ContratoPrestacaoServicoHelper();

				// Obtém os dados do crédito realizado
				Object[] dadosRelatorio = (Object[]) colecaoDadosRelatorioIterator
						.next();

				Cliente cliente = null;
				Cliente clienteResponsavel = null;

				String enderecoCliente = "";
				String enderecoImovel = "";
				String categoriaPrincipal = "";

				// Nome Localidade
				if (dadosRelatorio[0] != null) {
					contratoPrestacaoServicoHelper
							.setNomeLocalidade((String) dadosRelatorio[0]);
				}

				// Id do Responsável
				if (dadosRelatorio[4] != null) {

					clienteResponsavel = new Cliente();

					clienteResponsavel.setId((Integer) dadosRelatorio[4]);

					// Nome Responsável
					if (dadosRelatorio[1] != null) {

						clienteResponsavel.setNome((String) dadosRelatorio[1]);
					}

					// CPF Responsável
					if (dadosRelatorio[2] != null) {
						clienteResponsavel.setCpf((String) dadosRelatorio[2]);
					}

					// RG Responsável
					if (dadosRelatorio[3] != null) {
						clienteResponsavel.setRg((String) dadosRelatorio[3]);
					}
				}

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				// Consumo Tarifa
				if (dadosRelatorio[5] != null) {
					ConsumoTarifa consumoTarifa = new ConsumoTarifa();
					consumoTarifa.setId((Integer) dadosRelatorio[5]);
					imovel.setConsumoTarifa(consumoTarifa);
				}

				// Nome Unidade Negócio
				if (dadosRelatorio[6] != null) {
					contratoPrestacaoServicoHelper
							.setNomeMunicipio((String) dadosRelatorio[6]);
				} else {
					contratoPrestacaoServicoHelper.setNomeMunicipio("");
				}

				if (clienteResponsavel == null) {
					throw new ControladorException(
							"atencao.localidade_sem_responsavel", null,
							contratoPrestacaoServicoHelper.getNomeLocalidade());
				}

				enderecoImovel = getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);

				Integer consumoMinimo = null;

				Categoria categoriaImovel = getControladorImovel()
						.obterPrincipalCategoriaImovel(idImovel);

				// [FS0001]- Validar imovel e identificar tipo de categoria

				if (categoriaImovel != null) {
					categoriaPrincipal = categoriaImovel.getDescricao();

					if (categoriaImovel.getCategoriaTipo().getId().intValue() != CategoriaTipo.PARTICULAR
							.intValue()) {
						throw new ControladorException(
								"atencao.categoria_diferente_de_particular",
								null, categoriaImovel.getCategoriaTipo()
										.getDescricao());
					}
				}

				consumoMinimo = getControladorMicromedicao()
						.obterConsumoMinimoLigacao(imovel, null);

				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idCliente));

				filtroCliente
						.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);

				Collection colecaoCliente = getControladorUtil().pesquisar(
						filtroCliente, Cliente.class.getName());

				if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

					cliente = (Cliente) Util
							.retonarObjetoDeColecao(colecaoCliente);

					if (cliente.getClienteTipo()
							.getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA
							.intValue()) {
						throw new ControladorException(
								"atencao.cliente_associado_contrato_pessoa_juridica");
					}
					if (cliente.getCpf() == null
							|| cliente.getCpf().equalsIgnoreCase("")) {
						throw new ControladorException(
								"atencao.cpf_cliente_nao_informado", null,
								"cliente responsável");
					}

					if (cliente.getRg() == null
							|| cliente.getRg().equalsIgnoreCase("")) {
						throw new ControladorException(
								"atencao.rg_cliente_nao_informado", null,
								"cliente");
					}

				}

				if (clienteResponsavel != null) {
					if (clienteResponsavel.getRg() == null
							|| clienteResponsavel.getRg().equalsIgnoreCase("")) {

						throw new ControladorException(
								"atencao.responsavel_localidade_sem_rg", null,
								contratoPrestacaoServicoHelper
										.getNomeLocalidade());

					}

					if (clienteResponsavel.getCpf() == null
							|| clienteResponsavel.getCpf().equalsIgnoreCase("")) {

						throw new ControladorException(
								"atencao.responsavel_unidade_negocio_sem_cpf",
								null, contratoPrestacaoServicoHelper
										.getNomeLocalidade());

					}

					enderecoCliente = getControladorEndereco()
							.pesquisarEnderecoClienteAbreviado(idCliente);

					contratoPrestacaoServicoHelper.setCliente(cliente);
					contratoPrestacaoServicoHelper
							.setClienteResponsavel(clienteResponsavel);
					contratoPrestacaoServicoHelper
							.setEnderecoCliente(enderecoCliente);
					contratoPrestacaoServicoHelper
							.setEnderecoImovel(enderecoImovel);
					contratoPrestacaoServicoHelper
							.setCategoria(categoriaPrincipal);
					contratoPrestacaoServicoHelper
							.setConsumoMinimo(consumoMinimo);

					colecaoContratoPrestacaoServicoHelper
							.add(contratoPrestacaoServicoHelper);
				}
			}
			return colecaoContratoPrestacaoServicoHelper;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public void atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(
			Integer idImovel, Integer idHidrometro) {
		try {
			repositorioAtendimentoPublico
					.atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(
							idImovel, idHidrometro);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}

	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Obtém os dados necessário da ligação de água, de esgoto e do hidrômetro
	 * instalado na ligação de água
	 * 
	 * @author Rafael Corrêa
	 * @date 17/05/2007
	 * 
	 * @throws ControladorException
	 */
	public DadosLigacoesBoletimCadastroHelper obterDadosLigacaoAguaEsgoto(
			Integer idImovel) throws ControladorException {

		DadosLigacoesBoletimCadastroHelper dadosLigacoesBoletimCadastroHelper = new DadosLigacoesBoletimCadastroHelper();
		Object[] dadosLigacos = null;

		try {
			dadosLigacos = this.repositorioAtendimentoPublico
					.obterDadosLigacaoAguaEsgoto(idImovel);

			if (dadosLigacos != null) {

				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
				Hidrometro hidrometro = null;

				// Diâmetro da Ligação de Água
				if (dadosLigacos[0] != null) {
					LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
					ligacaoAguaDiametro.setId((Integer) dadosLigacos[0]);
					ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
				}

				// Material da Ligação de Água
				if (dadosLigacos[1] != null) {
					LigacaoAguaMaterial ligacaoAguaMaterial = new LigacaoAguaMaterial();
					ligacaoAguaMaterial.setId((Integer) dadosLigacos[1]);
					ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterial);
				}

				// Diâmetro da Ligação de Esgoto
				if (dadosLigacos[2] != null) {
					LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
					ligacaoEsgotoDiametro.setId((Integer) dadosLigacos[2]);
					ligacaoEsgoto
							.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
				}

				// Material da Ligação de Esgoto
				if (dadosLigacos[3] != null) {
					LigacaoEsgotoMaterial ligacaoEsgotoMaterial = new LigacaoEsgotoMaterial();
					ligacaoEsgotoMaterial.setId((Integer) dadosLigacos[3]);
					ligacaoEsgoto
							.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterial);
				}

				// Leitura Inicial
				if (dadosLigacos[4] != null) {
					hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
					hidrometro = new Hidrometro();
					hidrometroInstalacaoHistorico
							.setNumeroLeituraInstalacao((Integer) dadosLigacos[4]);
				}

				// Capacidade do Hidrômetro
				if (dadosLigacos[5] != null) {
					HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
					hidrometroCapacidade.setId((Integer) dadosLigacos[5]);
					hidrometro.setHidrometroCapacidade(hidrometroCapacidade);
				}

				// Marca do Hidrômetro
				if (dadosLigacos[6] != null) {
					HidrometroMarca hidrometroMarca = new HidrometroMarca();
					hidrometroMarca.setId((Integer) dadosLigacos[6]);
					hidrometro.setHidrometroMarca(hidrometroMarca);
				}

				// Local de Instalação do Hidrômetro
				if (dadosLigacos[7] != null) {
					HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
					hidrometroLocalInstalacao.setId((Integer) dadosLigacos[7]);
					hidrometroInstalacaoHistorico
							.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
				}

				// Proteção do Hidrômetro
				if (dadosLigacos[8] != null) {
					HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
					hidrometroProtecao.setId((Integer) dadosLigacos[8]);
					hidrometroInstalacaoHistorico
							.setHidrometroProtecao(hidrometroProtecao);
				}

				// Indicador Cavalete
				if (dadosLigacos[9] != null) {
					hidrometroInstalacaoHistorico
							.setIndicadorExistenciaCavalete((Short) dadosLigacos[9]);
				}

				// Numero do Hidrômetro
				if (dadosLigacos[10] != null) {
					hidrometro.setNumero((String) dadosLigacos[10]);
				}

				if (hidrometroInstalacaoHistorico != null) {
					hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
				}
				ligacaoAgua
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
				dadosLigacoesBoletimCadastroHelper.setLigacaoAgua(ligacaoAgua);
				dadosLigacoesBoletimCadastroHelper
						.setLigacaoEsgoto(ligacaoEsgoto);

			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return dadosLigacoesBoletimCadastroHelper;

	}

	public RelatorioContratoPrestacaoServicoJuridicoBean gerarContratoJuridica(
			Integer idImovel, Integer idClienteEmpresa)
			throws ControladorException {

		RelatorioContratoPrestacaoServicoJuridicoBean relatorioBean = null;

		Categoria categoriaImovel = getControladorImovel()
				.obterPrincipalCategoriaImovel(idImovel);

		// [FS0001]- Validar imovel e identificar tipo de categoria

		if (categoriaImovel != null) {

			if (categoriaImovel.getCategoriaTipo().getId().intValue() != CategoriaTipo.PUBLICO
					.intValue()) {
				throw new ControladorException(
						"atencao.categoria_diferente_publica", null,
						categoriaImovel.getCategoriaTipo().getDescricao());
			}

		}
		ClienteImovel clienteImovel;
		try {
			clienteImovel = repositorioAtendimentoPublico
					.pesquisarDadosContratoJuridica(idImovel);

			if (clienteImovel != null) {

				SistemaParametro sistemaParametro = getControladorUtil()
						.pesquisarParametrosDoSistema();

				if (sistemaParametro.getClientePresidente() == null) {
					throw new ControladorException("atencao.cliente_sem_dados",
							null, "Presidente");
				}
				if (sistemaParametro.getClienteDiretorComercial() == null) {
					throw new ControladorException("atencao.cliente_sem_dados",
							null, "Diretor Financeiro");
				}

				// 3.4.1 Diretor Presidente
				Cliente clientePresidente = repositorioAtendimentoPublico
						.pesquisaClienteContrato(sistemaParametro
								.getClientePresidente().getId());
				// 3.4.2 Diretor Comercial
				Cliente clienteDiretor = repositorioAtendimentoPublico
						.pesquisaClienteContrato(sistemaParametro
								.getClienteDiretorComercial().getId());

				// [FS0002]- Identificar informações do presidente e diretor
				// fincanceiro da empresa

				if (clientePresidente == null) {
					throw new ControladorException("atencao.cliente_sem_dados",
							null, "Presidente");
				} else {
					if (clientePresidente.getCpf() == null
							|| clientePresidente.getCpf().equalsIgnoreCase("")) {
						throw new ControladorException(
								"atencao.cpf_cliente_nao_informado", null,
								"Presidente associado");
					}
					if (clientePresidente.getRg() == null
							|| clientePresidente.getRg().equalsIgnoreCase("")) {
						throw new ControladorException(
								"atencao.rg_cliente_nao_informado", null,
								"Presidente associado");
					}
				}

				if (clienteDiretor == null) {
					throw new ControladorException("atencao.cliente_sem_dados",
							null, "Diretor Financeiro");
				} else {
					if (clienteDiretor.getCpf() == null
							|| clienteDiretor.getCpf().equalsIgnoreCase("")) {
						throw new ControladorException(
								"atencao.cpf_cliente_nao_informado", null,
								"Diretor Financeiro");
					}
					if (clienteDiretor.getRg() == null
							|| clienteDiretor.getRg().equalsIgnoreCase("")) {
						throw new ControladorException(
								"atencao.rg_cliente_nao_informado", null,
								"Diretor Financeiro");
					}
				}

				// [FS0011]- Identificar profissao do presidente e diretor
				// financeiro
				if (clientePresidente.getProfissao() == null
						|| clientePresidente.getProfissao().getDescricao() == null
						|| clientePresidente.getProfissao().getDescricao()
								.equalsIgnoreCase("")) {
					throw new ControladorException(
							"atencao.profissao_cliente_presidente_inexistente",
							null, clientePresidente.getId().toString());
				}

				if (clienteDiretor.getProfissao() == null
						|| clienteDiretor.getProfissao().getDescricao() == null
						|| clienteDiretor.getProfissao().getDescricao()
								.equalsIgnoreCase("")) {
					throw new ControladorException(
							"atencao.profissao_cliente_diretor_inexistente",
							null, clienteDiretor.getId().toString());
				}

				// 3.4.3 Cliente Usuario
				Cliente clienteUsuario = clienteImovel.getCliente();
				// 3.4.4 Endereco Imovel
				String endereco = getControladorEndereco()
						.pesquisarEnderecoFormatado(idImovel);

				if (clienteUsuario.getCnpj() == null
						|| clienteUsuario.equals("")) {
					throw new ControladorException(
							"atencao.cnpj_cliente_imovel_nao_informado");
				}

				// 3.4.6 Cliente Representante Empresa
				Cliente clienteEmpresa = repositorioAtendimentoPublico
						.pesquisaClienteContrato(idClienteEmpresa);

				if (clienteEmpresa.getClienteTipo()
						.getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA
						.intValue()) {
					throw new ControladorException(
							"atencao.cliente_associado_contrato_pessoa_juridica");
				}

				// [FS0004]- Informações do cliente associado ao contrato
				if (clienteEmpresa.getCpf() == null
						|| clienteEmpresa.getCpf().equals("")) {
					throw new ControladorException(
							"atencao.cpf_cliente_nao_informado", null,
							"cliente responsável");
				}
				if (clienteEmpresa.getRg() == null
						|| clienteEmpresa.getRg().equals("")) {
					throw new ControladorException(
							"atencao.rg_cliente_nao_informado", null, "cliente");
				}
				Date dataCorrente = new Date();
				String anoCorrente = "" + Util.getAno(dataCorrente);
				// 3.5 Comarca(municipio do imovel)
				String municipio = repositorioAtendimentoPublico
						.pesquisarMunicipio(idImovel);

				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,
						Locale.getDefault());
				String dataCorrenteFormatada = df.format(new Date());

				relatorioBean = new RelatorioContratoPrestacaoServicoJuridicoBean(
						clientePresidente.getNome() != null ? clientePresidente
								.getNome() : "", clientePresidente
								.getCpfFormatado() != null ? clientePresidente
								.getCpfFormatado() : "", clientePresidente
								.getRg() != null ? clientePresidente.getRg()
								: "", clientePresidente.getProfissao()
								.getDescricao() != null ? clientePresidente
								.getProfissao().getDescricao() : "",
						clienteDiretor.getNome() != null ? clienteDiretor
								.getNome() : "", clienteDiretor
								.getCpfFormatado() != null ? clienteDiretor
								.getCpfFormatado() : "",
						clienteDiretor.getRg() != null ? clienteDiretor.getRg()
								: "", clienteDiretor.getProfissao()
								.getDescricao() != null ? clienteDiretor
								.getProfissao().getDescricao() : "",
						clienteUsuario.getNome() != null ? clienteUsuario
								.getNome() : "", clienteUsuario
								.getCnpjFormatado() != null ? clienteUsuario
								.getCnpjFormatado() : "",
						endereco != null ? endereco : "",
						idImovel.toString() != null ? idImovel.toString() : "",
						clienteEmpresa.getNome() != null ? clienteEmpresa
								.getNome() : "", clienteEmpresa
								.getCpfFormatado() != null ? clienteEmpresa
								.getCpfFormatado() : "",
						clienteEmpresa.getRg() != null ? clienteEmpresa.getRg()
								: "", municipio != null ? municipio : "",
						dataCorrenteFormatada, clienteUsuario.getClienteTipo()
								.getEsferaPoder().getDescricao(), ""
								+ idImovel.toString() + anoCorrente);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return relatorioBean;
	}

	/**
	 * [UC0608] Efetuar Ligação de Esgoto sem RA.
	 * 
	 * [FS0001] Verificar existência da matrícula do Imovel.
	 * 
	 * [FS0007] Verificar situação do imóvel.
	 * 
	 * [FS0008] Verificar Situação Rede de Esgoto da Quadra.
	 * 
	 * @author Sávio Luiz.
	 * @date 10/09/2007
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public String validarMatriculaImovel(Integer idImovel)
			throws ControladorException {

		String mensagem = null;

		int quantidadeImoveis = getControladorImovel()
				.verificarExistenciaImovel(idImovel);
		if (quantidadeImoveis == 0) {
			boolean imovelExcluido = getControladorImovel()
					.confirmarImovelExcluido(idImovel);
			// [FS0007] Verificar situação do imóvel.
			if (imovelExcluido) {
				throw new ControladorException(
						"atencao.atualizar_situacao_imovel_indicador_exclusao_esgoto",
						null, "" + idImovel);
			} else {
				// [FS0001] Verificar existência da matrícula do Imovel.
				mensagem = "Matrícula do imóvel inexistente.";
			}
		}

		/*
		 * [FS0007] - Verificar situação rede de esgoto da quadra
		 * 
		 * Integração com o conceito de face da quadra Raphael Rossiter em
		 * 22/05/2009
		 */
		IntegracaoQuadraFaceHelper integracao = this.getControladorLocalidade()
				.integracaoQuadraFace(idImovel);

		if ((integracao.getIndicadorRedeEsgoto()).equals(Quadra.SEM_REDE)) {

			throw new ControladorException(
					"atencao.percentual_rede_esgoto_quadra", null, ""
							+ idImovel);
		}

		return mensagem;
	}

	/**
	 * [UC0482]Emitir 2ª Via de Conta obter numero do hidrômetro na ligação de
	 * água.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2007
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public String obterNumeroHidrometroEmLigacaoAgua(Integer imovelId)
			throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.obterNumeroHidrometroEmLigacaoAgua(imovelId);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Capacidade de Hidrômetro pela Ligação de Água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmLigacaoAgua(
			Integer imovelId) throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.obterHidrometroCapacidadeEmLigacaoAgua(imovelId);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioCertidaoNegativaHelper> pesquisarRelatorioCertidaoNegativa(
			Imovel imo) throws ControladorException {

		Collection<RelatorioCertidaoNegativaHelper> retorno = new ArrayList<RelatorioCertidaoNegativaHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {

			colecaoPesquisa = this.repositorioAtendimentoPublico
					.pesquisarRelatorioCertidaoNegativa(imo);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		RelatorioCertidaoNegativaHelper helper = null;

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			if (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				helper = new RelatorioCertidaoNegativaHelper();

				// Nome do usuário
				helper.setNomeClienteUsuario((String) objeto[0]);

				// Matricula do imovel
				helper.setMatriculaImovel((Integer) objeto[1]);

				// Carregamos os dados no imovel para selecionarmos sua
				// matrícula formatada
				// Id do imovel
				Imovel imovel = new Imovel();
				imovel.setId((Integer) objeto[1]);

				// Id da localidade
				Localidade local = new Localidade();
				local.setId((Integer) objeto[2]);
				imovel.setLocalidade(local);

				// Código do Setor Comercial
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo((Integer) objeto[3]);
				imovel.setSetorComercial(setorComercial);

				// Numero da quadra
				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra((Integer) objeto[4]);
				imovel.setQuadra(quadra);

				imovel.setLote((Short) objeto[5]);
				imovel.setSubLote((Short) objeto[6]);

				// Inscrição formatada
				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				// Nome do Usuário
				Cliente clienteUsuario = getControladorImovel()
						.pesquisarClienteUsuarioImovel(imovel.getId());
				helper.setNomeUsuario(clienteUsuario.getNome());

				// Selecionamos os dados do Endereço
				String[] dadosEndereco = this
						.getControladorEndereco()
						.pesquisarEnderecoFormatadoDividido((Integer) objeto[1]);
				helper.setEndereco((String) dadosEndereco[0]);
				helper.setMunicipio((String) dadosEndereco[1]);
				helper.setBairro((String) dadosEndereco[3]);
				helper.setCEP(Cep.formatarCep((String) dadosEndereco[4]));
				helper.setLocalidade((String) objeto[15]);

				// Obtemos a principal categoria do imovel
				Categoria categoria = this.getControladorImovel()
						.obterPrincipalCategoriaImovel((Integer) objeto[1]);
				helper.setCategoria(categoria.getDescricao());

				// Obtemos a principal subcategoria do imovel
				ImovelSubcategoria subCategoria = this.getControladorImovel()
						.obterPrincipalSubcategoria(categoria.getId(),
								(Integer) objeto[1]);
				FiltroImovelSubCategoria filtro = new FiltroImovelSubCategoria();
				filtro
						.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria");
				filtro.adicionarParametro(new ParametroSimples(
						FiltroImovelSubCategoria.SUBCATEGORIA_ID, subCategoria
								.getComp_id().getSubcategoria().getId()));
				filtro.adicionarParametro(new ParametroSimples(
						FiltroImovelSubCategoria.IMOVEL_ID, imo.getId()));
				Collection colImovelSubCategoria = Fachada.getInstancia()
						.pesquisar(filtro, ImovelSubcategoria.class.getName());

				subCategoria = (ImovelSubcategoria) Util
						.retonarObjetoDeColecao(colImovelSubCategoria);

				helper.setSubcategoria(subCategoria.getComp_id()
						.getSubcategoria().getDescricao());

				// Obtemos a quantidade de economias do imovel
				helper.setEconomias((Short) objeto[7]);

				// Obtemos o perfil do imovel
				helper.setPerfilImovel((String) objeto[8]);

				// Obtemos a situação da ligacao de agua
				helper.setLigacaoAguaSituacao((String) objeto[9]);

				// Obtemos a situação da ligacao de esgoto
				helper.setLigacaoEsgotoSituacao((String) objeto[10]);

				// Obtemos o tipo do poco
				helper.setSituacaoPoco((String) objeto[11]);

				// Obtemos o nome abreviado da empresa
				helper.setDescricaoAbreviadaEmpresa((String) objeto[12]);

				// Obtemos o nome da empresa
				helper.setDescricaoEmpresa((String) objeto[13]);

				// Obtemos o CNPJ da empresa
				helper.setCNPJEmpresa((String) objeto[14]);

				// Obtemos o logradouro da empresa
				helper
						.setEnderecoEmpresa((objeto[16] != null ? ((String) objeto[16])
								.trim()
								: "")
								+ (objeto[19] != null ? ", "
										+ ((String) objeto[19]).trim() + " "
										: "")
								+ (objeto[17] != null ? ((String) objeto[17])
										.trim() : "")
								+ (objeto[18] != null ? ", "
										+ ((String) objeto[18]).trim() : "")
								+ (objeto[20] != null ? " - "
										+ ((String) objeto[20]).trim() : "")
								+ (objeto[21] != null ? " - CEP "
										+ Cep.formatarCep((Integer) objeto[21])
										: ""));

				// Obtemos o site
				helper.setSiteEmpresa((String) objeto[22]);

				// Obtemos O 0800
				helper.setZeroOitossentosEmpresa((String) objeto[23]);

				// Obtemos a inscrição estadual
				helper.setInscricaoEstadualEmpresa((String) objeto[24]);

				String area = "";

				if (objeto[25] != null) {
					area = Util.formatarMoedaReal((BigDecimal) objeto[25]);
				} else {
					AreaConstruidaFaixa areaConstruidaFaixa = (AreaConstruidaFaixa) objeto[26];
					if (areaConstruidaFaixa != null) {
						area = areaConstruidaFaixa.getFaixaCompleta();
					} else {
						area = "0";
					}
				}

				helper.setArea(area);

				if (objeto[27] != null) {
					helper.setNumeroHidrometro((String) objeto[27]);
				} else {
					helper.setNumeroHidrometro("");
				}
				
				// Unidade Negócio
				helper.setUnidadeNegocio((String) objeto[28]);
				
				// Cliente Tipo
				if(objeto[31] != null){
					// verifica se é cpf
					if(((Short) objeto[31]).compareTo(ConstantesSistema.SIM) == 0){
						
						if(objeto[29] != null){
							
							String cpf = Util.formatarCpf((String) objeto[29]) ;
							helper.setCpfCnpj( cpf );
						}
					}else{
						
						if(objeto[30] != null){
							
							String cnpj = Util.formatarCnpj((String) objeto[30]);
							helper.setCpfCnpj( cnpj );
						}
					}
				}
				
				// Endereço Completo
				String enderecoCompleto = getControladorEndereco().pesquisarEnderecoFormatado(imovel.getId());
				helper.setEnderecoCompleto(enderecoCompleto);
				
			}
		}

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		Calendar dataFimVencimentoDebito = new GregorianCalendar();
		dataFimVencimentoDebito.add(Calendar.DATE, -sistemaParametro
				.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());

		// Pesquisamos se o imovel possue debitos ou não
		ObterDebitoImovelOuClienteHelper debitoGeral = Fachada.getInstancia()
				.obterDebitoImovelOuCliente(1, imo.getId() + "", null, null,
						"190001", "299901", Util.criarData(1, 1, 1900),
						dataFimVencimentoDebito.getTime(), 1, 1, 1, 1, 1, 1, 1,
						true);

		if (sistemaParametro.getIndicadorCertidaoNegativaEfeitoPositivo() == ConstantesSistema.NAO
				.shortValue()) {

			if (debitoGeral != null) {
				if ((debitoGeral.getColecaoContasValores() != null && !debitoGeral
						.getColecaoContasValores().isEmpty())
						|| (debitoGeral.getColecaoGuiasPagamentoValores() != null && !debitoGeral
								.getColecaoGuiasPagamentoValores().isEmpty())) {
					throw new ControladorException(
							"atencao.imovel_com_debitos_certidao");
				} else {
					if (sistemaParametro
							.getIndicadorDebitoACobrarValidoCertidaoNegativa()
							.equals(ConstantesSistema.SIM)
							&& (debitoGeral.getColecaoDebitoACobrar() != null && !debitoGeral
									.getColecaoDebitoACobrar().isEmpty())) {
						throw new ControladorException(
								"atencao.imovel_com_debitos_certidao");
					}
				}
			}

		}

		// Verificamos se existe débito para o imovel selecionado
		if ((debitoGeral.getColecaoContasValores() != null && debitoGeral
				.getColecaoContasValores().size() > 0)
				|| (debitoGeral.getColecaoCreditoARealizar() != null && debitoGeral
						.getColecaoCreditoARealizar().size() > 0)
				|| (debitoGeral.getColecaoDebitoACobrar() != null && debitoGeral
						.getColecaoDebitoACobrar().size() > 0)
				|| (debitoGeral.getColecaoDebitoCreditoParcelamentoHelper() != null && debitoGeral
						.getColecaoDebitoCreditoParcelamentoHelper().size() > 0)
				|| (debitoGeral.getColecaoGuiasPagamentoValores() != null && debitoGeral
						.getColecaoGuiasPagamentoValores().size() > 0)) {

			Collection<RelatorioCertidaoNegativaItemBean> colItens = new ArrayList<RelatorioCertidaoNegativaItemBean>();

			if (debitoGeral.getColecaoContasValores() != null
					&& debitoGeral.getColecaoContasValores().size() > 0) {
				Iterator iteContas = debitoGeral.getColecaoContasValores()
						.iterator();

				// Total das contas
				BigDecimal totalContas = new BigDecimal(0);

				// Faturas em aberto
				while (iteContas.hasNext()) {
					ContaValoresHelper contaHelper = (ContaValoresHelper) iteContas
							.next();

					RelatorioCertidaoNegativaItemBean fatura = new RelatorioCertidaoNegativaItemBean(
							"Faturas", Util
									.formatarAnoMesParaMesAno(contaHelper
											.getConta().getReferencia())
									+ "-"
									+ contaHelper.getConta()
											.getDigitoVerificadorConta(), Util
									.formatarAnoMesParaMesAno(contaHelper
											.getConta().getReferencia()), Util
									.formatarData(contaHelper.getConta()
											.getDataVencimentoConta()), Util
									.formataBigDecimal(contaHelper
											.getValorTotalConta(), 2, true),
							Util.formatarData(contaHelper.getConta()
									.getDataValidadeConta()));

					totalContas = totalContas.add(contaHelper
							.getValorTotalConta());

					colItens.add(fatura);
				}

				// Incluimos o total
				RelatorioCertidaoNegativaItemBean fatura = new RelatorioCertidaoNegativaItemBean(
						"TOTAL:" + "", "", "", "", Util.formataBigDecimal(
								totalContas, 2, true), "");

				colItens.add(fatura);
			}

			// Total dos servicos
			BigDecimal totalServicos = new BigDecimal(0);

			if (debitoGeral.getColecaoGuiasPagamentoValores() != null
					&& debitoGeral.getColecaoGuiasPagamentoValores().size() > 0) {
				Iterator iteGuias = debitoGeral
						.getColecaoGuiasPagamentoValores().iterator();

				// Guias de pagamento
				while (iteGuias.hasNext()) {
					GuiaPagamentoValoresHelper guiaHelper = (GuiaPagamentoValoresHelper) iteGuias
							.next();

					RelatorioCertidaoNegativaItemBean guia = new RelatorioCertidaoNegativaItemBean(
							"Guias de Pagamento", guiaHelper.getGuiaPagamento()
									.getDebitoTipo().getDescricao(), Util
									.formatarAnoMesParaMesAno(guiaHelper
											.getGuiaPagamento()
											.getAnoMesReferenciaContabil()),
							Util.formatarData(guiaHelper.getGuiaPagamento()
									.getDataVencimento()), Util
									.formataBigDecimal(guiaHelper
											.getGuiaPagamento()
											.getValorDebito(), 2, true),
							(guiaHelper.getGuiaPagamento()
									.getNumeroPrestacaoTotal() - guiaHelper
									.getGuiaPagamento()
									.getNumeroPrestacaoDebito())
									+ "");

					totalServicos = totalServicos.add(guiaHelper
							.getGuiaPagamento().getValorDebito());

					colItens.add(guia);
				}
			}

			if (debitoGeral.getColecaoDebitoACobrar() != null
					&& debitoGeral.getColecaoDebitoACobrar().size() > 0) {
				Iterator iteDebitos = debitoGeral.getColecaoDebitoACobrar()
						.iterator();

				// Debitos
				while (iteDebitos.hasNext()) {
					DebitoACobrar debitoHelper = (DebitoACobrar) iteDebitos
							.next();

					// alterado por Vivianne Sousa data:11/04/2008
					// analista : Aryed

					RelatorioCertidaoNegativaItemBean debito = new RelatorioCertidaoNegativaItemBean(
							"Debitos a cobrar",
							debitoHelper.getDebitoTipo().getDescricao(),
							Util.formatarAnoMesParaMesAno(debitoHelper
									.getAnoMesReferenciaContabil()),
							debitoHelper.getNumeroPrestacaoCobradas()
									+ "/"
									+ debitoHelper
											.getNumeroPrestacaoDebitoMenosBonus(),
							Util.formataBigDecimal(debitoHelper
									.getValorTotalComBonus(), 2, true),
							(debitoHelper.getNumeroPrestacaoDebitoMenosBonus() - debitoHelper
									.getNumeroPrestacaoCobradas())
									+ "");

					// RelatorioCertidaoNegativaItemBean debito = new
					// RelatorioCertidaoNegativaItemBean(
					// "Debitos a cobrar",
					// debitoHelper.getDebitoTipo().getDescricao(),
					// Util.formatarAnoMesParaMesAno(
					// debitoHelper.getAnoMesReferenciaContabil() ),
					// debitoHelper.getNumeroPrestacaoCobradas() + "/" +
					// debitoHelper.getNumeroPrestacaoDebito(),
					// Util.formataBigDecimal(
					// debitoHelper.getValorRestanteCobrado() , 2, true ),
					// ( debitoHelper.getNumeroPrestacaoDebito() -
					// debitoHelper.getNumeroPrestacaoCobradas() ) + "" );

					totalServicos = totalServicos.add(debitoHelper
							.getValorTotalComBonus());

					colItens.add(debito);
				}
			}

			if (debitoGeral.getColecaoCreditoARealizar() != null
					&& debitoGeral.getColecaoCreditoARealizar().size() > 0) {
				Iterator iteCredito = debitoGeral.getColecaoCreditoARealizar()
						.iterator();

				// Debitos
				while (iteCredito.hasNext()) {
					CreditoARealizar creditoHelper = (CreditoARealizar) iteCredito
							.next();

					// alterado por Vivianne Sousa data:11/04/2008
					// analista responsavel: Adriano

					RelatorioCertidaoNegativaItemBean credito = new RelatorioCertidaoNegativaItemBean(
							"Crédito a Realizar",
							creditoHelper.getCreditoTipo().getDescricao(),
							Util.formatarAnoMesParaMesAno(creditoHelper
									.getAnoMesReferenciaContabil()),
							creditoHelper.getNumeroPrestacaoRealizada()
									+ "/"
									+ creditoHelper
											.getNumeroPrestacaoCreditoMenosBonus(),
							Util.formataBigDecimal(creditoHelper
									.getValorTotalComBonus(), 2, true),
							(creditoHelper
									.getNumeroPrestacaoCreditoMenosBonus() - creditoHelper
									.getNumeroPrestacaoRealizada())
									+ "");

					// RelatorioCertidaoNegativaItemBean credito = new
					// RelatorioCertidaoNegativaItemBean(
					// "Crédito a Realizar",
					// creditoHelper.getCreditoTipo().getDescricao(),
					// Util.formatarAnoMesParaMesAno(
					// creditoHelper.getAnoMesReferenciaContabil() ),
					// creditoHelper.getNumeroPrestacaoRealizada() + "/" +
					// creditoHelper.getNumeroPrestacaoCredito(),
					// Util.formataBigDecimal( creditoHelper.getValorTotal() ,
					// 2, true ),
					// ( creditoHelper.getNumeroPrestacaoCredito() -
					// creditoHelper.getNumeroPrestacaoRealizada() ) + "" );

					totalServicos = totalServicos.add(creditoHelper
							.getValorTotalComBonus());

					colItens.add(credito);
				}
			}

			// Incluimos o total
			RelatorioCertidaoNegativaItemBean servicos = new RelatorioCertidaoNegativaItemBean(
					"TOTAL:" + "", "", "", "", Util.formataBigDecimal(
							totalServicos, 2, true), "");

			colItens.add(servicos);

			helper.setItens(colItens);

		}

		// verificar se tem parcelamento no imovel
		try {

			Collection colecaoParcelamentoDoImovel = this
					.getControladorCobranca()
					.pesquisarParcelamentosSituacaoNormal(imo.getId());

			if (colecaoParcelamentoDoImovel.size() > 0
					&& !colecaoParcelamentoDoImovel.equals("")) {
				helper.setImovelComParcelamento(true);
			} else {
				helper.setImovelComParcelamento(false);
			}

		} catch (ControladorException e) {
			throw new ControladorException("erro.sistema", e);
		}
		retorno.add(helper);

		return retorno;
	}

	/**
	 * [UC0498] Efetuar Religação de Água com Instalação de hidrômetro.
	 * 
	 * Permite efetuar religação de Água com Instalação de Hidrômetro ou pelo
	 * menu ou pela funcionalidade encerrar a Execução da ordem de serviço.
	 * 
	 * @author Sávio Luiz
	 * @date 29/01/2008
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarReligacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException {

		/*
		 * [UC0107] Registrar Transação
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao
				.setId(Operacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		LigacaoAgua ligacaoAgua = integracaoComercialHelper.getLigacaoAgua();
		Imovel imovel = integracaoComercialHelper.getImovel();
		OrdemServico ordemServico = integracaoComercialHelper.getOrdemServico();
		String qtdParcelas = integracaoComercialHelper.getQtdParcelas();

		this.getControladorMicromedicao().validarImovelEmCampo(imovel.getId());
		
		if (ligacaoAgua != null && imovel != null) {

			Integer id = null;

			// [SB0003] Gerar Histórico de Instalação do Hidrometro

			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper
					.getHidrometroInstalacaoHistorico();

			validacaoInstalacaoHidrometro(hidrometroInstalacaoHistorico
					.getHidrometro().getNumero());

			hidrometroInstalacaoHistorico.setOperacaoEfetuada(operacaoEfetuada);
			hidrometroInstalacaoHistorico.adicionarUsuario(usuario,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao
					.registrarOperacao(hidrometroInstalacaoHistorico);

			id = (Integer) getControladorUtil().inserir(
					hidrometroInstalacaoHistorico);

			try {

				// [SB004] Atualizar Hidrômetro
				Integer situacaoHidrometro = HidrometroSituacao.INSTALADO;
				repositorioAtendimentoPublico.atualizarSituacaoHidrometro(
						hidrometroInstalacaoHistorico.getHidrometro().getId(),
						situacaoHidrometro);

				hidrometroInstalacaoHistorico.setId(id);
				hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);

				// [SB0001] Atualizar Imóvel/Ligação de Água

				Date dataCorrente = new Date();
				ligacaoAgua.setUltimaAlteracao(dataCorrente);

				// [FS0007] - Atualização realizada por outrio usuário
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAgua.ID, ligacaoAgua.getId()));
				Collection colecaoLigacaoAguaBase = getControladorUtil()
						.pesquisar(filtroLigacaoAgua,
								LigacaoAgua.class.getName());

				if (colecaoLigacaoAguaBase == null
						|| colecaoLigacaoAguaBase.isEmpty()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.atualizacao.timestamp");
				} else {
					LigacaoAgua ligacaoAguaBase = (LigacaoAgua) Util
							.retonarObjetoDeColecao(colecaoLigacaoAguaBase);
					if (ligacaoAguaBase.getUltimaAlteracao().after(
							ligacaoAgua.getUltimaAlteracao())) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizacao.timestamp");
					}
				}

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, ligacaoAgua.getId()));
				Collection colecaoImovelBase = getControladorUtil().pesquisar(
						filtroImovel, Imovel.class.getName());

				if (colecaoImovelBase == null || colecaoImovelBase.isEmpty()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.atualizacao.timestamp");
				} else {
					Imovel imovelBase = (Imovel) Util
							.retonarObjetoDeColecao(colecaoImovelBase);
					if (imovelBase.getUltimaAlteracao().after(
							ligacaoAgua.getUltimaAlteracao())) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.atualizacao.timestamp");
					}
				}

				// regitrando operacao
				ligacaoAgua.setOperacaoEfetuada(operacaoEfetuada);
				ligacaoAgua.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(ligacaoAgua);

				getControladorUtil().atualizar(ligacaoAgua);

			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);

			getControladorImovel()
					.atualizarImovelExecucaoOrdemServicoLigacaoAgua(imovel,
							ligacaoAguaSituacao);

			if (imovel.getLigacaoEsgotoSituacao() != null
					&& imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO
							.intValue()) {

				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
				ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);

				imovel.setUltimaAlteracao(new Date());

				getControladorImovel()
						.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(
								imovel, ligacaoEsgotoSituacao);
			}
		}

		// [SB0002] Atualizar Ordem de Serviço
		ordemServico.setOperacaoEfetuada(operacaoEfetuada);
		ordemServico.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(ordemServico);

		if (!integracaoComercialHelper.isVeioEncerrarOS()
				&& ordemServico.getServicoTipo().getDebitoTipo() != null) {

			this.getControladorOrdemServico()
					.verificarOrdemServicoControleConcorrencia(ordemServico);
			getControladorOrdemServico().atualizaOSGeral(ordemServico);
		}

		if (ordemServico.getServicoTipo().getDebitoTipo() != null
				&& ordemServico.getServicoNaoCobrancaMotivo() == null) {
			getControladorOrdemServico().gerarDebitoOrdemServico(
					ordemServico.getId(),
					ordemServico.getServicoTipo().getDebitoTipo().getId(),
					ordemServico.getValorAtual(), new Integer(qtdParcelas),
					ordemServico.getPercentualCobranca().toString(),
					integracaoComercialHelper.getUsuarioLogado());
		}

	}

	/**
	 * [UC0747] Efetuar Religação de Água com Instalação de hidrômetro.
	 * 
	 * Permite validar o efetuar religação de Água com Instalação de hidrômetro
	 * Exibir ou pelo menu ou pela funcionalidade encerrar a Execução da ordem
	 * de serviço.
	 * 
	 * [FS0002] Verificar Situação do Imovel. [FS0003] Validar Situação de Água
	 * 
	 * @author Sávio Luiz
	 * @date 29/01/2008
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarReligacaoAguaComInstalacaoHidrometroExibir(
			OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException {

		/*
		 * Caso o SERVICO_TIPO da ordem de serviço recebida esteja associado a
		 * operação EFETUAR LIGACAO AGUA COM INSTALACAO HIDROMETRO, não será
		 * necessário realizar as validações abaixo.
		 * 
		 * Autor: Raphael Rossiter Data: 26/04/2007
		 * 
		 */
		Integer idOperacao = this.getControladorOrdemServico()
				.pesquisarServicoTipoOperacao(ordem.getServicoTipo().getId());

		if (idOperacao == null
				|| idOperacao.intValue() != Operacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO) {

			// [FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordem.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO) {
				throw new ControladorException(
						"atencao.servico_associado_religacao_agua_instalacao_hidrometro_invalida");
			}
		}

		/*
		 * Validações já contidas no método anteriormente Autor: Raphael
		 * Rossiter Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */

		// Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordem,
				veioEncerrarOS);
		// Caso 4
		if (ordem.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordem.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordem.getRegistroAtendimento().getImovel();

		// [FS0003] Validar Situação de Água do Imóvel.
		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.CORTADO
				.intValue()) {

			throw new ControladorException(
					"atencao.situacao_validar_religacao_agua_invalida_exibir",
					null, imovel.getLigacaoAguaSituacao().getDescricao());
		}

		// [FS0002] Verificar Situação do Imovel
		if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			throw new ControladorException(
					"atencao.situacao_imovel_indicador_exclusao", null, imovel
							.getId()
							+ "");
		}

		/*
		 * ===================================================================================
		 */
	}

	private ControladorTransacaoLocal getControladorTransacao() {
		ControladorTransacaoLocalHome localHome = null;
		ControladorTransacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorTransacaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_TRANSACAO_SEJB);
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
	 * [UC0778] Gerar Relatório Gestão de Serviços UPA<br>
	 * [UC0497] Gerar Relatório Resumo de Solicitações de RA por Unidade
	 * <p>
	 * Retorna todas as unidades filhas (direta ou indiretamente) da Unidade
	 * Superior passada como parâmetro.
	 * 
	 * @see gcom.fachada.Fachada#pesquisarUnidadesFilhas(int)
	 * 
	 * @author Victor Cisneiros
	 * @date 09/07/2008
	 * 
	 * @param idUnidadeSuperior
	 * @throws ControladorException
	 */
	public UnidadesFilhasHelper pesquisarUnidadesFilhas(int idUnidadeSuperior)
			throws ControladorException {

		// hash id da UnidadeOrganizacional -> unidade
		LinkedHashMap<Integer, UnidadeOrganizacional> unidades = new LinkedHashMap<Integer, UnidadeOrganizacional>();
		// hash id da UnidadeOrganizacional -> colecao de unidades filhas
		Map<Integer, Collection<UnidadeOrganizacional>> unidadeTemFilhos = new LinkedHashMap<Integer, Collection<UnidadeOrganizacional>>();

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidadeSuperior));

		Collection pesquisa = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ControladorException("atencao.pesquisa_inexistente",
					null, "Unidade Superior");
		}

		UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util
				.retonarObjetoDeColecao(pesquisa);

		// adicionar a unidade superior encontrada na lista de restantes
		List<Integer> restantes = new ArrayList<Integer>();
		restantes.add(unidadeSuperior.getId());

		formarArvoreUnidades(unidadeSuperior, restantes, unidades,
				unidadeTemFilhos);

		return new UnidadesFilhasHelper(unidades, unidadeTemFilhos);
	}

	/**
	 * Metodo recursivo usado para encontrar todas as unidades filhas (direta ou
	 * indireta) de uma unidade superior.
	 * <p>
	 * 
	 * @see #pesquisarUnidadesFilhas(int)
	 */
	private void formarArvoreUnidades(
			UnidadeOrganizacional unidadeSuperiorAtual,
			List<Integer> restantes,
			Map<Integer, UnidadeOrganizacional> unidades,
			Map<Integer, Collection<UnidadeOrganizacional>> unidadeTemFilhos) {

		Fachada fachada = Fachada.getInstancia();

		if (!restantes.isEmpty()) {
			Integer idAtual = restantes.remove(restantes.size() - 1);

			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idAtual));

			Collection<UnidadeOrganizacional> pesquisa = fachada.pesquisar(
					filtro, UnidadeOrganizacional.class.getName());

			if (pesquisa != null && !pesquisa.isEmpty()) {

				for (UnidadeOrganizacional unidade : pesquisa) {
					restantes.add(unidade.getId());
					formarArvoreUnidades(unidade, restantes, unidades,
							unidadeTemFilhos);
				}

				unidades
						.put(unidadeSuperiorAtual.getId(), unidadeSuperiorAtual);
				unidadeTemFilhos.put(unidadeSuperiorAtual.getId(), pesquisa);

			} else {
				unidades
						.put(unidadeSuperiorAtual.getId(), unidadeSuperiorAtual);
				unidadeTemFilhos.put(unidadeSuperiorAtual.getId(), null);
			}
		}
	}

	public void efetuarLigacaoAguaComInstalacaoHidrometroSemRA(
			Integer idImovel, LigacaoAgua ligacaoAgua,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico)
			throws ControladorException {

		this.getControladorMicromedicao().validarImovelEmCampo(idImovel);

		try {

			
			if (hidrometroInstalacaoHistorico != null) {

				//Alteração feita por Sávio Luiz
				//Data: 25/05/2011
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAgua.ID, idImovel));
				Collection colecaoLigacaoAguaBase = getControladorUtil().pesquisar(
						filtroLigacaoAgua, LigacaoAgua.class.getName());

				if (!colecaoLigacaoAguaBase.isEmpty()) {
					getControladorUtil().atualizar(ligacaoAgua);
				}else{
					getControladorUtil().inserir(ligacaoAgua);
				}
				this.atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(
						idImovel, null);

				hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
				Integer idHidrometroInstalacaoHistorico = (Integer) getControladorUtil()
						.inserir(hidrometroInstalacaoHistorico);
				hidrometroInstalacaoHistorico
						.setId(idHidrometroInstalacaoHistorico);
				ligacaoAgua
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
				getControladorUtil().atualizar(ligacaoAgua);
				this.atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(null,
						hidrometroInstalacaoHistorico.getHidrometro().getId());

			} else {

				getControladorUtil().inserir(ligacaoAgua);
				this.atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(
						idImovel, null);

			}

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa os dados necessários para a geração do relatório
	 * 
	 * [UC0864] Gerar Certidão Negativa por Cliente
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioCertidaoNegativaClienteBean> pesquisarRelatorioCertidaoNegativaCliente(
			Collection<Integer> idsClientes, Cliente clienteInformado)
			throws ControladorException {

		Collection<RelatorioCertidaoNegativaClienteBean> retorno = new ArrayList<RelatorioCertidaoNegativaClienteBean>();

		try {
			Collection<Object[]> colecaoDadosRelatorioCertidaoNegativaCliente = this.repositorioAtendimentoPublico
					.pesquisarRelatorioCertidaoNegativaCliente(idsClientes);

			for (Object[] dadosRelatorio : colecaoDadosRelatorioCertidaoNegativaCliente) {

				RelatorioCertidaoNegativaClienteBean relatorioCertidaoNegativaClienteBean = new RelatorioCertidaoNegativaClienteBean();

				String cliente = clienteInformado.getId() + " - "
						+ clienteInformado.getNome();
				relatorioCertidaoNegativaClienteBean.setCliente(cliente);

				// Responsável
				if (dadosRelatorio[0] != null) {
					relatorioCertidaoNegativaClienteBean
							.setResponsavel(((Integer) dadosRelatorio[0])
									.toString());
				}
				
				// Cliente Tipo
				if(dadosRelatorio[5] != null){
					// verifica se é cpf
					if(((Short) dadosRelatorio[5]).compareTo(ConstantesSistema.SIM) == 0){
						
						if(dadosRelatorio[3] != null){
							
							String cpf = Util.formatarCpf((String) dadosRelatorio[3]) ;
							relatorioCertidaoNegativaClienteBean.setCpfCnpj( cpf );
						}
					}else{
						
						if(dadosRelatorio[4] != null){
							
							String cnpj = Util.formatarCnpj((String) dadosRelatorio[4]);
							relatorioCertidaoNegativaClienteBean.setCpfCnpj( cnpj );
						}
					}
				}
				
				// Id do Imóvel e Endereço
				if (dadosRelatorio[1] != null) {
					Integer idImovel = (Integer) dadosRelatorio[1];
					relatorioCertidaoNegativaClienteBean.setIdImovel(Util
							.retornaMatriculaImovelFormatada(idImovel));

					// Nome do Usuário
					Cliente clienteUsuario = getControladorImovel()
							.pesquisarClienteUsuarioImovel(idImovel);
					relatorioCertidaoNegativaClienteBean
							.setNomeUsuario(clienteUsuario.getNome());

					// Endereço
					String endereco = getControladorEndereco()
							.obterEnderecoAbreviadoImovel(idImovel);
					relatorioCertidaoNegativaClienteBean.setEndereco(endereco);
					
					// Endereço Completo
					String enderecoCompleto = getControladorEndereco().pesquisarEnderecoFormatado(idImovel);
					relatorioCertidaoNegativaClienteBean.setEnderecoCompleto(enderecoCompleto);
				}

				// Situação da Ligação de Água
				if (dadosRelatorio[2] != null) {
					relatorioCertidaoNegativaClienteBean
							.setSituacaoLigacaoAgua((String) dadosRelatorio[2]);
				}

				retorno.add(relatorioCertidaoNegativaClienteBean);
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 * 
	 * [FS0003] - Verificar se documento é válido
	 * 
	 * [FS0004] - Cliente não associado ao documento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 * 
	 * @param idImovel
	 * @param cpf
	 * @param cnpj
	 * @throws ControladorException
	 */
	public void verificarDocumentoValidoEmissaoInternet(Integer idImovel,
			String cpf, String cnpj) throws ControladorException {

		SistemaParametro sistemaParametro = this.getControladorUtil()
				.pesquisarParametrosDoSistema();

		/*
		 * O sistema solicita o documento válido para emissão (CPF/CNPJ), caso
		 * esta informação seja obrigatória para a empresa solicitante.
		 */
		if (sistemaParametro.getIndicadorDocumentoValido().equals(
				ConstantesSistema.SIM)) {

			/*
			 * O sistema deverá verificar se algum cliente do imóvel selecionado
			 * tenha o documento informado.
			 */
			Collection colecaoCliente = null;

			if (cpf != null && !cpf.equals("")) {

				try {
					colecaoCliente = this.repositorioAtendimentoPublico
							.pesquisarClienteAssociadoImovelComCPF(idImovel,
									cpf);
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
			} else if (cnpj != null && !cnpj.equals("")) {

				try {
					colecaoCliente = this.repositorioAtendimentoPublico
							.pesquisarClienteAssociadoImovelComCNPJ(idImovel,
									cnpj);
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
			}

			if (colecaoCliente == null || colecaoCliente.isEmpty()) {

				// [FS0004] - Cliente não associado ao documento
				throw new ControladorException(
						"atencao.cliente_nao_associado_documento", null,
						sistemaParametro.getNomeAbreviadoEmpresa());
			}
		}
	}

	/**
	 * [UC0482] Emitir 2a Via Conta
	 * 
	 * [FS0002] - Cliente sem documento
	 * 
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 * 
	 * @param idImovel
	 * @param usuario
	 * @throws ControladorException
	 */
	public void verificarClienteSemDocumento(Integer idImovel, Usuario usuario)
			throws ControladorException {

		SistemaParametro sistemaParametro = this.getControladorUtil()
				.pesquisarParametrosDoSistema();

		/*
		 * O sistema solicita o documento válido para emissão (CPF/CNPJ), caso
		 * esta informação seja obrigatória para a empresa solicitante.
		 */
		if (sistemaParametro.getIndicadorDocumentoValido().equals(
				ConstantesSistema.SIM)) {

			/*
			 * Verificando se o usuário logado possuí permissão especial para
			 * emitir 2 via de conta sem o documento (CPF ou CNPJ)informado.
			 */
			boolean temPermissaoEmitir2ViaSemDocumentoValido = false;

			if (usuario != null) {
				temPermissaoEmitir2ViaSemDocumentoValido = this
						.getControladorPermissaoEspecial()
						.verificarPermissaoEmitir2ViaSemDocumentoValido(usuario);
			}

			if (!temPermissaoEmitir2ViaSemDocumentoValido) {

				// O sistema verifica se algum dos clientes associados ao imóvel
				// tem documento válido.
				Collection colecaoCliente = null;

				try {
					colecaoCliente = this.repositorioAtendimentoPublico
							.pesquisarClienteAssociadoImovelComDocumentoInformado(idImovel);
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}

				if (colecaoCliente == null || colecaoCliente.isEmpty()) {

					// [FS0002] - Cliente sem documento
					throw new ControladorException(
							"atencao.cliente_documento_nao_informado");
				}
			}
		}
	}

	/**
	 * Controlador Permissao Especial
	 * 
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 * 
	 * @return ControladorLocalidadeLocal
	 */
	private ControladorPermissaoEspecialLocal getControladorPermissaoEspecial() {
		ControladorPermissaoEspecialLocalHome localHome = null;
		ControladorPermissaoEspecialLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorPermissaoEspecialLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_PERMISSAO_ESPECIAL_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * [UC0861] Inserir Perfil da ligacao de esgoto
	 * 
	 * @author Arthur Carvalho
	 * @date 16/10/2008
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */

	public Integer inserirPerfilLigacaoEsgoto(
			LigacaoEsgotoPerfil ligacaoEsgotoPerfil, Usuario usuarioLogado)
			throws ControladorException {

		Integer retorno = null;

		if (ligacaoEsgotoPerfil.getDescricao() != null
				&& ligacaoEsgotoPerfil.getDescricao().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Descrição");

		}

		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();

		filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.DESCRICAO, ligacaoEsgotoPerfil.getDescricao()));

		// Pesquisa se existe algum perfil da ligacao de esgoto com a descricao
		// informada

		Collection colecaoLigacaoEsgotoPerfilDescricao = new ArrayList();

		colecaoLigacaoEsgotoPerfilDescricao = getControladorUtil().pesquisar(
				filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());

		if (colecaoLigacaoEsgotoPerfilDescricao != null
				&& !colecaoLigacaoEsgotoPerfilDescricao.isEmpty()) {
			throw new ControladorException(
					"atencao.ligacao_esgoto_perfil_ja_cadastrada", null,
					ligacaoEsgotoPerfil.getDescricao());

		}

		// Ultima Alteracao
		ligacaoEsgotoPerfil.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_LIGACAO_ESGOTO_PERFIL,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_LIGACAO_ESGOTO_PERFIL);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		ligacaoEsgotoPerfil.setOperacaoEfetuada(operacaoEfetuada);
		ligacaoEsgotoPerfil.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(ligacaoEsgotoPerfil);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		retorno = (Integer) getControladorUtil().inserir(ligacaoEsgotoPerfil);

		return retorno;

	}

	/**
	 * [UC0861] Manter Perfil da Ligacao de Esgoto
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 20/10/2008
	 * 
	 * 
	 * @throws ControladorException
	 */

	public void atualizarPerfilLigacaoEsgoto(
			LigacaoEsgotoPerfil ligacaoEsgotoPerfil, Usuario usuarioLogado)
			throws ControladorException {

		if (ligacaoEsgotoPerfil.getDescricao() != null
				&& ligacaoEsgotoPerfil.getDescricao().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Descrição");

		}

		if (ligacaoEsgotoPerfil.getIndicadorUso() != null
				&& ligacaoEsgotoPerfil.getIndicadorUso().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Indicador de Uso");

		}
		if (ligacaoEsgotoPerfil.getIndicadorPrincipal() != null
				&& ligacaoEsgotoPerfil.getIndicadorPrincipal().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Indicador Principal");

		}

		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();

		filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoPerfil.DESCRICAO, ligacaoEsgotoPerfil
						.getDescricao()));

		// Pesquisa se existe alguma empresa com a descricao informada

		Collection colecaoLigacaoEsgotoPerfilDescricao = new ArrayList();

		colecaoLigacaoEsgotoPerfilDescricao = getControladorUtil().pesquisar(
				filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());

		if (colecaoLigacaoEsgotoPerfilDescricao != null
				&& !colecaoLigacaoEsgotoPerfilDescricao.isEmpty()) {

			LigacaoEsgotoPerfil ligacaoEsgotoPerfilBase = (LigacaoEsgotoPerfil) colecaoLigacaoEsgotoPerfilDescricao
					.iterator().next();

			if (ligacaoEsgotoPerfil.getId().intValue() != ligacaoEsgotoPerfilBase
					.getId().intValue()) {

				throw new ControladorException(
						"atencao.ligacao_esgoto_perfil_ja_cadastrada", null,
						ligacaoEsgotoPerfil.getDescricao());
			}
		}

		// Ultima Alteracao
		ligacaoEsgotoPerfil.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_LIGACAO_ESGOTO_PERFIL,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();

		operacao.setId(Operacao.OPERACAO_ATUALIZAR_LIGACAO_ESGOTO_PERFIL);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		ligacaoEsgotoPerfil.setOperacaoEfetuada(operacaoEfetuada);
		ligacaoEsgotoPerfil.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(ligacaoEsgotoPerfil);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

	}

	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 26/11/2008
	 */
	public BigDecimal obterPercentualAguaConsumidaColetadaImovel(
			Integer idImovel) throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.obterPercentualAguaConsumidaColetadaImovel(idImovel);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
	public void atualizarAutosInfracaoComPrazoRecursoVencido(
			SistemaParametro sistemaParametro, Integer idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);
		try {
			Date prazoEntregaRecursoVencido = Util.subtrairNumeroDiasDeUmaData(
					new Date(), sistemaParametro
							.getNumeroDiasPrazoRecursoAutoInfracao());
			Collection<AutosInfracao> colecaoAutosInfracao = repositorioAtendimentoPublico
					.pesquisarAutoInfracaoRecursoVencido(
							AutoInfracaoSituacao.AUTO_EM_PRAZO_DE_RECURSO,
							prazoEntregaRecursoVencido);

			Collection<Integer> idsAutosInfracao = new ArrayList();
			if (colecaoAutosInfracao != null && !colecaoAutosInfracao.isEmpty()) {
				for (AutosInfracao autosInfracao : colecaoAutosInfracao) {
					// [SB0002] - Gerar Débito a Cobrar
					gerarDebitoACobrarAutoInfracao(autosInfracao,
							sistemaParametro);
					idsAutosInfracao.add(autosInfracao.getId());
				}
			}

			// [SB0001] - Atualizar Autos de Infração
			if (idsAutosInfracao != null && !idsAutosInfracao.isEmpty()) {
				repositorioAtendimentoPublico.atualizarAutosInfracao(
						idsAutosInfracao,
						AutoInfracaoSituacao.AUTO_COM_PRAZO_DE_RECURSO_VENCIDO);
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			ex.printStackTrace();

			throw new EJBException(ex);
		}
	}

	/**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * [SB0002] - Gerar Débito a Cobrar
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
	public Short gerarDebitoACobrarAutoInfracao(AutosInfracao autosInfracao,
			SistemaParametro sistemaParametro) throws ControladorException {

		try {

			Short gerouDebitoACobrar = 2;
			Integer[] consumos = calcularConsumoImovelAutosInfracao(
					autosInfracao.getImovel().getId(), sistemaParametro);

			Collection<FiscalizacaoSituacaoServicoACobrar> collFiscalizacaoSituacaoSAC = repositorioAtendimentoPublico
					.pesquisarFiscalizacaoSituacaoServicoACobrar(autosInfracao
							.getFiscalizacaoSituacao().getId());

			if (collFiscalizacaoSituacaoSAC != null
					&& !collFiscalizacaoSituacaoSAC.isEmpty()) {
				for (FiscalizacaoSituacaoServicoACobrar fssc : collFiscalizacaoSituacaoSAC) {

					DebitoACobrar debitoACobrar = null;

					// código do consumo = 7
					if (fssc
							.getConsumoCalculo()
							.equals(
									FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_SETE)) {
						// indicador de atualização do auto igual a ativo
						if (fssc.getFiscalizacaoSituacao() != null
								&& fssc
										.getFiscalizacaoSituacao()
										.getIndicadorAtualizacaoAutosInfracao()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO)) {
							BigDecimal valorDebito = new BigDecimal("0.00");
							if (fssc.getDebitoTipo().getValorSugerido() != null) {
								valorDebito = fssc.getDebitoTipo()
										.getValorSugerido();
							}

							// [SB0004] - Calcular/Inserir Valor
							debitoACobrar = gerarDebitoACobrarAutoInfracao(
									fssc, autosInfracao, valorDebito);

						}
					}
					// código do consumo = 8
					if (fssc
							.getConsumoCalculo()
							.equals(
									FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_OITO)) {
						// indicador de atualização do auto igual a ativo
						if (fssc.getFiscalizacaoSituacao() != null
								&& fssc
										.getFiscalizacaoSituacao()
										.getIndicadorAtualizacaoAutosInfracao()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO)) {

							BigDecimal valorAcumulado = obterValorAcumuladoNaoCobrado(
									fssc, autosInfracao, consumos[2],
									sistemaParametro);

							// [SB0004] - Calcular/Inserir Valor
							debitoACobrar = gerarDebitoACobrarAutoInfracao(
									fssc, autosInfracao, valorAcumulado);

						}
					}
					// código do consumo = 9
					if (fssc
							.getConsumoCalculo()
							.equals(
									FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_NOVE)) {
						// indicador de atualização do auto igual a ativo
						if (fssc.getFiscalizacaoSituacao() != null
								&& fssc
										.getFiscalizacaoSituacao()
										.getIndicadorAtualizacaoAutosInfracao()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO)) {

							int consumoCobradoMes = 0;

							if (consumos[0] != null
									&& consumos[0].intValue() >= consumos[2]
											.intValue()) {
								consumoCobradoMes = consumos[0];
							} else {
								consumoCobradoMes = consumos[2];
							}

							// LigacaoAguaSituacao do Imóvel
							Integer idLigacaoAguaSituacaoImovel = getControladorImovel()
									.pesquisarIdLigacaoAguaSituacao(
											autosInfracao.getImovel().getId());

							// LigacaoEsgotoSituacao do Imóvel
							/*
							 * Integer idLigacaoEsgotoSituacaoImovel =
							 * getControladorImovel()
							 * .pesquisarIdLigacaoEsgotoSituacao(autosInfracao.getImovel().getId());
							 */

							Integer idLigacaoEsgotoSituacaoImovel = null;

							// [SB0003 - Calcular Valor de Água e/ou Esgoto]
							BigDecimal valorEstimado = getControladorOrdemServico()
									.calcularValorAguaEsgoto(
											consumoCobradoMes,
											sistemaParametro,
											autosInfracao.getImovel().getId(),
											fssc.getFiscalizacaoSituacao()
													.getId(),
											idLigacaoAguaSituacaoImovel,
											idLigacaoEsgotoSituacaoImovel);

							// [SB0004] - Calcular/Inserir Valor
							debitoACobrar = gerarDebitoACobrarAutoInfracao(
									fssc, autosInfracao, valorEstimado);
						}
					}

					// [SB0005] - Vincular Débitos a Cobrar ao Auto de Infração
					if (debitoACobrar != null) {
						AutosInfracaoDebitoACobrar autosInfracaoDebitoACobrar = new AutosInfracaoDebitoACobrar();
						autosInfracaoDebitoACobrar.setAutosInfracao(autosInfracao);
						DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
						debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
						debitoACobrarGeral.setId(debitoACobrar.getId());
						autosInfracaoDebitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);
						autosInfracaoDebitoACobrar.setUltimaAlteracao(new Date());
						autosInfracaoDebitoACobrar.setIndicadorMultaInfracao(fssc.getIndicadorMultaInfracao());
						getControladorUtil().inserir(autosInfracaoDebitoACobrar);
						
						//2. O sistema atualiza a indicação de geração de débito para a situação de fiscalização 
						getControladorOrdemServico().atualizarIndicadorDebitoOS(
							new Integer(1),
							autosInfracao.getFiscalizacaoSituacao().getId(), 
							autosInfracao.getOrdemServico().getId());
						
						gerouDebitoACobrar = 1;
						
					}
				}
			}

			return gerouDebitoACobrar;
		} catch (Exception ex) {

			ex.printStackTrace();

			throw new EJBException(ex);
		}

	}

	/**
	 * [UC0896] Manter Autos de Infração
	 * 
	 * [SB0007] - Calcular Consumo para o Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 17/06/2009
	 */
	private Integer[] calcularConsumoImovelAutosInfracao(Integer idImovel,
			SistemaParametro sistemaParametro) throws ControladorException {

		Integer[] consumos = new Integer[3];

		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, idImovel));

		Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(
				filtroLigacaoAgua, LigacaoAgua.class.getName());

		if (colecaoLigacaoAgua != null && !colecaoLigacaoAgua.isEmpty()) {

			LigacaoAgua ligacaoAgua = (LigacaoAgua) Util
					.retonarObjetoDeColecao(colecaoLigacaoAgua);
			if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null) {
				Integer consumoMedio = getControladorMicromedicao()
						.obterConsumoMedioEmConsumoHistorico(idImovel,
								LigacaoTipo.LIGACAO_AGUA);
				consumos[0] = consumoMedio;

				Integer maiorConsumo = getControladorMicromedicao()
						.pesquisarMaiorConsumoFaturadoQuantidadeMeses(
								idImovel,
								LigacaoTipo.LIGACAO_AGUA,
								sistemaParametro
										.getNumeroMesesMaximoCalculoMedia());
				consumos[1] = maiorConsumo;
			}
		}

		Integer consumoNaoMedido = getControladorMicromedicao()
				.obterConsumoNaoMedidoSemTarifa(idImovel,
						sistemaParametro.getAnoMesFaturamento());
		consumos[2] = consumoNaoMedido;

		return consumos;
	}

	/**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * [SB0004] - Calcular/Inserir Valor
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
	public DebitoACobrar gerarDebitoACobrarAutoInfracao(
			FiscalizacaoSituacaoServicoACobrar fssc,
			AutosInfracao autosInfracao, BigDecimal valorDebito)
			throws ControladorException {

		DebitoACobrar debitoACobrar = null;
		if (valorDebito != null
				&& valorDebito.compareTo(new BigDecimal("0.00")) > 0) {

			BigDecimal valorDebitoNovo = new BigDecimal("0.00");
			if (fssc.getNumeroVezesServicoCalculadoValor() != 0) {
				valorDebitoNovo = valorDebito.multiply(new BigDecimal(fssc
						.getNumeroVezesServicoCalculadoValor()));

			} else {
				valorDebitoNovo = valorDebito;
			}

			// id da ordem de serviço
			Integer idOrdemServico = null;
			if (autosInfracao.getOrdemServico() != null) {
				idOrdemServico = autosInfracao.getOrdemServico().getId();
			}
			// id do tipo do débito
			Integer idDebitoTipo = null;
			if (fssc.getDebitoTipo() != null) {
				idDebitoTipo = fssc.getDebitoTipo().getId();
			}

			Usuario usuario = null;

			if (autosInfracao.getUsuario() != null) {
				usuario = autosInfracao.getUsuario();
			} else {
				usuario = Usuario.USUARIO_BATCH;
			}

			int qtdParcelas = 1;

			if (autosInfracao.getNumeroParcelasDebito() != null) {
				qtdParcelas = autosInfracao.getNumeroParcelasDebito();
			}

			// [UC0479] - Gerar Débito Ordem de Serviço
			debitoACobrar = getControladorOrdemServico()
					.gerarDebitoOrdemServico(idOrdemServico, idDebitoTipo,
							valorDebitoNovo, qtdParcelas, "100", usuario);
		}

		return debitoACobrar;

	}

	private BigDecimal obterValorAcumuladoNaoCobrado(
			FiscalizacaoSituacaoServicoACobrar fssc,
			AutosInfracao autosInfracao, Integer consumoNaoMedido,
			SistemaParametro sistemaParametro) throws ControladorException {

		BigDecimal valorAcumulado = new BigDecimal("0.00");

		// Data de Corte
		LigacaoAgua ligacaoAgua = getControladorLigacaoAgua()
				.recuperaParametrosLigacaoAgua(
						autosInfracao.getImovel().getId());
		if (ligacaoAgua != null && !ligacaoAgua.equals("")) {
			Integer anoMesDataCorte = null;
			if (ligacaoAgua.getDataCorte() != null) {
				anoMesDataCorte = (Util.recuperaAnoMesDaData(ligacaoAgua
						.getDataCorte()));
			}
			// Último mês/ano faturado do imóvel
			Integer ultimaReferenciaConsumo = getControladorMicromedicao()
					.pesquisarUltimoAnoMesConsumoFaturado(
							autosInfracao.getImovel().getId(),
							LigacaoTipo.LIGACAO_AGUA);
			Integer quantidadeMeses = 0;
			if (anoMesDataCorte != null && ultimaReferenciaConsumo != null) {
				quantidadeMeses = Util.retornaQuantidadeMeses(
						ultimaReferenciaConsumo, anoMesDataCorte);
			}

			Integer numeroMesesCalculoMedia = 0;
			if (sistemaParametro
					.getNumeroMaximoMesesCalculoConsumoAutoInfracao() != null
					&& !sistemaParametro
							.getNumeroMaximoMesesCalculoConsumoAutoInfracao()
							.equals("")) {
				numeroMesesCalculoMedia = sistemaParametro
						.getNumeroMaximoMesesCalculoConsumoAutoInfracao()
						.intValue();
			}

			// verifica se a quantidade de meses calculado é maior que o limite
			// de meses.
			// Se for, a quantidade de meses será a quantidade limite.
			if (anoMesDataCorte == null
					|| quantidadeMeses > numeroMesesCalculoMedia) {
				quantidadeMeses = numeroMesesCalculoMedia;
			}

			Collection colecaoConsumoFaturaMesEReferencia = getControladorMicromedicao()
					.pesquisarConsumoFaturadoQuantidadeMesesPorReferencia(
							autosInfracao.getImovel().getId(),
							LigacaoTipo.LIGACAO_AGUA,
							quantidadeMeses.shortValue());

			// LigacaoAguaSituacao do Imóvel
			Integer idLigacaoAguaSituacaoImovel = getControladorImovel()
					.pesquisarIdLigacaoAguaSituacao(
							autosInfracao.getImovel().getId());

			// LigacaoEsgotoSituacao do Imóvel
			/*
			 * Integer idLigacaoEsgotoSituacaoImovel = getControladorImovel()
			 * .pesquisarIdLigacaoEsgotoSituacao(autosInfracao.getImovel().getId());
			 */

			Integer idLigacaoEsgotoSituacaoImovel = null;

			if (colecaoConsumoFaturaMesEReferencia != null
					&& !colecaoConsumoFaturaMesEReferencia.isEmpty()) {
				Iterator ite = colecaoConsumoFaturaMesEReferencia.iterator();
				while (ite.hasNext()) {
					Object[] parmsConsumoHistorico = (Object[]) ite.next();
					if (parmsConsumoHistorico != null) {
						Integer numeroConsumoFaturadoMes = 0;
						Integer referenciaFaturamento = 0;
						if (parmsConsumoHistorico[0] != null) {
							numeroConsumoFaturadoMes = (Integer) parmsConsumoHistorico[0];
						}
						if (parmsConsumoHistorico[1] != null) {
							referenciaFaturamento = (Integer) parmsConsumoHistorico[1];
						}

						if (numeroConsumoFaturadoMes.intValue() < consumoNaoMedido
								.intValue()) {
							numeroConsumoFaturadoMes = consumoNaoMedido;
						}

						// [SB0003 - Calcular Valor de Água e/ou Esgoto]

						BigDecimal valorTotal = getControladorOrdemServico()
								.calcularValorAguaEsgoto(
										numeroConsumoFaturadoMes,
										sistemaParametro,
										autosInfracao.getImovel().getId(),
										fssc.getFiscalizacaoSituacao().getId(),
										idLigacaoAguaSituacaoImovel,
										idLigacaoEsgotoSituacaoImovel);

						BigDecimal valorAgua = getControladorFaturamento()
								.pesquisarValorAguaConta(
										autosInfracao.getImovel().getId(),
										referenciaFaturamento);

						if (valorTotal != null && valorAgua != null) {
							valorAcumulado = valorAcumulado.add(valorTotal
									.subtract(valorAgua));
						}
					}
				}

			}

		}

		return valorAcumulado;
	}
	/**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 *  Este caso de uso permite realizar a recuperação das informações dos imóveis que estejam com seus ramais surprimidos,
	 *  parcialmente ou totalmente, por um período pré-determinado e os armazena em uma base de dados
	 * 	para uma posterior geração de arquivo de texto.
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void gerarDadosOrdemDeFiscalizacao(int idFuncionalidadeIniciada,
			Usuario usuarioLogado,SetorComercial setorComercial,SistemaParametro sistemaParametro) throws ControladorException{
		
		int idUnidadeIniciada = 0;
		
		idUnidadeIniciada = 
			getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada,
				UnidadeProcessamento.SETOR_COMERCIAL,setorComercial.getId());
		
		try{
			
			Integer numeroMesesBatchImoveisSuprimidos = 0;
			Integer qtdMesesNegativo = 0;
			
			if(sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos()!=null){
				numeroMesesBatchImoveisSuprimidos = sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos();
				qtdMesesNegativo =-numeroMesesBatchImoveisSuprimidos;
			}
			
			Date dataAtual = new Date();
			Date dataPesquisa = Util.adcionarOuSubtrairMesesAData(dataAtual,qtdMesesNegativo,00000);
			
			// O sistema deve excluir todos os registros da tabela BATCH_IMOVEIS_SUPRIMIDOS,
			// que estejam com a situação de finalizado na tabela ORDEM_SERVICO.
			this.excluirDadosFinalizados(setorComercial.getId());
			
			
			// Variáveis para a paginação da pesquisa
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeMaxima = 1000;
			int quantidadeInicio = 0;
			// ========================================================================
			
			while(!flagTerminou){
			
				Collection<EmitirOrdemDeFiscalizacaoHelper> colecaoHelpers =
					this.pesquisarImoveisSuprimidosParaGeracaoDeOrdemDeFiscalizacao(
							dataPesquisa,setorComercial.getId(),sistemaParametro,quantidadeInicio,quantidadeMaxima);
				
				//INICIALIZACAO VARIAVEIS
				StringBuilder linhaTxt = new StringBuilder();
				ImovelSuprimido imovelSuprimido = null;
				
				for (EmitirOrdemDeFiscalizacaoHelper helper : colecaoHelpers) {
					
					OrdemServico ordemServicoFiscalizacao = new OrdemServico();
					ordemServicoFiscalizacao.setImovel(helper.getImovel());
					
					Integer idOsGerada = getControladorOrdemServico()
						.gerarOrdemServicoFiscalizacao(ordemServicoFiscalizacao,usuarioLogado);
					
					ordemServicoFiscalizacao.setId(idOsGerada);
					
					linhaTxt = new StringBuilder();
					
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getNumeroInscricao(),20));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getMatriculaFormatada(),10));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getDescricaPerfilImovel(),13));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getDescricaoEndereco(),50));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getBairro(),30));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getMunicipio(),30));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getUnidadeFederacao(),2));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(Util.formatarCEP(helper.getCep()),10));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getQtdEconomiasRes().toString(),3));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getQtdEconomiasCom().toString(),3));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getQtdEconomiasInd().toString(),3));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getQtdEconomiasPub().toString(),3));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getQtdEconomiasTot().toString(),3));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getUltimaAlteracao().toString(),10));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getIdGrupoFaturamento().toString(),3));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getDescricaoSituacaoAgua(),20));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getCosumoMedio()!=null?helper.getCosumoMedio().toString():"",5));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getDataCorte()!=null?helper.getDataCorte():"",10));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getDataSupressaoParcial()!=null?helper.getDataSupressaoParcial():"",10));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getDataSupressaoTotal()!=null?helper.getDataSupressaoTotal():"",10));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getDescricaoSituacaoEsgoto().toString(),20));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(helper.getVolumeFixoEsgoto()!=null?helper.getVolumeFixoEsgoto().toString():"",5));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getDescricaoOcorrencia()!=null?helper.getDescricaoOcorrencia():"",12));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(Util.formatarMoedaReal(helper.getValorServicosAtualizacoes()),14));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximo(Util.formatarMoedaReal(helper.getValorDebito()),14));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getNomeCliente()!=null?helper.getNomeCliente():"",50));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getCpfCnpj()!=null?helper.getCpfCnpj():"",18));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getRg()!=null?helper.getRg():"",13));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getNumeroTelefonico()!=null?Util.formatarTelefone(helper.getNumeroTelefonico()):"",9));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getRamal()!=null?helper.getRamal():"",4));
					linhaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getTipoTelefonico()!=null?helper.getTipoTelefonico():"",3));
	
					
					
				    imovelSuprimido = new ImovelSuprimido();
				    
				    imovelSuprimido.setOrdemServico(ordemServicoFiscalizacao);
				    
				    String linha = linhaTxt.toString();
				    linha = linha.replace('\'',' ');
				    linha = linha.replace('`',' ');
				    
				    imovelSuprimido.setLinhaTxt(linha);
				    imovelSuprimido.setDataExecucao(helper.getDataEmissao());
				    imovelSuprimido.setIdLocalidade(helper.getIdLocalidade());
				    imovelSuprimido.setIdSetorComercial(helper.getIdSetorComercial());
				    imovelSuprimido.setCodigoSetorComercial(helper.getCodigoSetorComercial());
				    imovelSuprimido.setIdQuadra(helper.getIdQuadra());
				    imovelSuprimido.setNumeroQuadra(helper.getNumeroQuadra());
				    imovelSuprimido.setNumeroLote(helper.getNumeroLote());
				    imovelSuprimido.setNumeroSubLote(helper.getNumeroSubLote());
				    
				    this.repositorioAtendimentoPublico.inserirImovelSuprimido(imovelSuprimido);
				   
				    //Reiniciando Variaveis
				    imovelSuprimido = null;
				    linha = null;
				    linhaTxt = null;
				    
					
				}
								
				// Incrementa o nº do indice da páginação
				quantidadeInicio = quantidadeInicio + quantidadeMaxima;
	
				/**
				 * Caso a coleção de dados retornados for menor que a
				 * quantidade de registros seta a flag indicando que a
				 * paginação terminou.
				 */
				if (colecaoHelpers == null || 
						colecaoHelpers.size() < quantidadeMaxima) {
	
					flagTerminou = true;
				}
	
				if (colecaoHelpers != null) {
					colecaoHelpers.clear();
					colecaoHelpers = null;
				}
			
			}
		
			getControladorBatch().encerrarUnidadeProcessamentoBatch(
				null, idUnidadeIniciada, false);				
			
		} catch (Exception e) { 
			// Este catch serve para interceptar
			// qualquer exceção que o processo batch
			// venha a lançar e garantir que a unidade
			// de processamento do batch será atualizada
			// com o erro ocorrido						
			
			e.printStackTrace();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		 }	
		
	}
	
	/**
	 *  [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 * O sistema deve recuperar informações dos imóveis ativos 
	 * que estejam com seus ramais suprimidos (parcialmente ou totalmente) 
	 * de acordo com a quantidade de meses prefedefidos na tabela SISTEMA_PARAMETROS,
	 * que não possua Ordem de Serviço de Fiscalização ou que possua, porém,
	 * que esteja em aberto.
	 *  
	 * @author Hugo Amorim
	 * @param sistemaParametro 
	 * @throws ControladorException 
	 * @date 08/03/2010
	 * 
	 */
	private Collection<EmitirOrdemDeFiscalizacaoHelper> 
		pesquisarImoveisSuprimidosParaGeracaoDeOrdemDeFiscalizacao(
			Date dataPesquisa, Integer idSetorComercial, SistemaParametro sistemaParametro,
			Integer quantidadeInicio, Integer quantidadeMaxima) throws ControladorException {
		
		Collection<EmitirOrdemDeFiscalizacaoHelper> retorno = 
			new ArrayList<EmitirOrdemDeFiscalizacaoHelper>();
		
		try{	
		
			Collection<Object[]> colecaoDados = 
				this.repositorioAtendimentoPublico
					.pesquisarImoveisBatchEmitirOrdemFiscalizacao(idSetorComercial,dataPesquisa,
							quantidadeInicio,quantidadeMaxima);
			
			Iterator it = colecaoDados.iterator();
			
			while (it.hasNext()) {
				//imovel[0]
		     	//perfil[1]
		     	//idLocalida[2]
				//idSetor[3]
		     	//codSetor[4]
		     	//idQuadra[5]
		     	//numeroQuadra[6]
		      	//lote[7]
		     	//subLote[8]	
		     	//ultimaAlteracao[9]
		     	//ligacaoAguaSituacao[10]
		      	//desLigacaoAguaSituacao[11]
		      	//ligacaoEsgotoSituacao[12]
		        //descLigacaoEsgotoSituacao[13]
		    	//dataCorte[14]
		    	//nomeCliente[15]
		    	//cpf[16]
		    	//cnpj[17]
		    	//rg[18]
		    	//fone[19]
		    	//ramal[20]
		    	//foneTipo[21]
				//dataSupressao[22]
				//faturamentoGrupo[23]
				//consumoFixo[24]
				Object[] dados = (Object[]) it.next();
								
				Integer idImovel = (Integer) dados[0];
		     	String descPerfil = (String) dados[1];
		     	Integer idLocalidade = (Integer) dados[2];
		     	Integer idSetor = (Integer) dados[3];
		     	Integer codSetor = (Integer) dados[4];
		     	Integer idQuadra = (Integer) dados[5];
		     	Integer numeroQuadra = (Integer) dados[6];
		     	Short lote = (Short) dados[7];
		     	Short subLote = (Short) dados[8];
		     	Date ultimaAlteracao = (Date) dados[9];
		     	Integer idLigacaoAguaSituacao = (Integer) dados[10];
		     	String desLigacaoAguaSituacao = (String) dados[11];
		     	Integer idLigacaoEsgotoSituacao = (Integer) dados[12];
		     	String descLigacaoEsgotoSituacao = (String) dados[13];
		     	Date dataCorte = (Date) dados[14];
		     	String nomeCliente = (String) dados[15];
		     	String cpf = dados[16]!=null?(String) dados[16]:"";
		     	String cnpj = dados[17]!=null?(String) dados[17]:"";
		     	String rg = dados[18]!=null?(String) dados[18]:"";
		     	String fone = dados[19]!=null?(String) dados[19]:"";
		     	String ramal = dados[20]!=null?(String) dados[20]:"";
		     	String foneTipo = dados[21]!=null?(String) dados[21]:"";
		     	Date dataSupressao = (Date) dados[22];
		     	Integer faturamentoGrupo = (Integer) dados[23];
		     	Integer consumoFixoEsgoto = dados[24]!=null?(Integer) dados[24]:null;
		     	
		     	Imovel imovel = new Imovel();
		     	Localidade localidade = new Localidade(idLocalidade);
		     	SetorComercial setorComercial = new SetorComercial(idSetor,codSetor,null);
		     	Quadra quadra = new Quadra(idQuadra, numeroQuadra, null);
		     	imovel.setId(idImovel);
		     	imovel.setLote(lote);
		     	imovel.setSubLote(subLote);
		     	imovel.setLocalidade(localidade);
		     	imovel.setSetorComercial(setorComercial);
		     	imovel.setQuadra(quadra);
		     	
		     	//OBTEM ENDERECO DO IMOVEL
		     	String[] endereco = this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(idImovel);
		     	String descricaoEndereco = endereco[0];
		     	String municipio = endereco[1];
		     	String unidadeFederacao = endereco[2];
		     	String bairro = endereco[3];
		     	String cep = endereco[4];
		     	
		     	//OBTEM QUANTIDADE DE ECONOMIAS
		     	Collection imovelSubcategorias = 
		     		getControladorImovel().pesquisarCategoriasImovel(idImovel);
				
				Integer qtdeEconResidencial = new Integer(0);
				Integer qtdeEconComercial	= new Integer(0);
				Integer qtdeEconIndustrial	= new Integer(0);
				Integer qtdeEconPublica	= new Integer(0);
				Integer qtdeEconTotal	= new Integer(0);
						
				for (Iterator iterator = imovelSubcategorias.iterator(); iterator
						.hasNext();) {
					
					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
					
					if(imovelSubcategoria
							.getComp_id().getSubcategoria()
								.getCategoria().getId().compareTo(Categoria.RESIDENCIAL)==0){
						qtdeEconResidencial+=1;
						qtdeEconTotal+=1;
					}
					if(imovelSubcategoria
							.getComp_id().getSubcategoria()
								.getCategoria().getId().compareTo(Categoria.COMERCIAL)==0){
						qtdeEconComercial+=1;
						qtdeEconTotal+=1;
					}
					if(imovelSubcategoria
							.getComp_id().getSubcategoria()
								.getCategoria().getId().compareTo(Categoria.INDUSTRIAL)==0){
						qtdeEconIndustrial+=1;
						qtdeEconTotal+=1;
					}
					if(imovelSubcategoria
							.getComp_id().getSubcategoria()
								.getCategoria().getId().compareTo(Categoria.PUBLICO)==0){
						qtdeEconPublica+=1;
						qtdeEconTotal+=1;
					}		
				}
				
				/**
				 *  Alterando o cálculo da média
				 */
				MedicaoTipo medicao = new MedicaoTipo();
				medicao.setId(1);
				
				boolean houveIntslacaoHidrometro = this
						.getControladorMicromedicao()
						.verificarInstalacaoSubstituicaoHidrometro(
								imovel.getId(), medicao);
				
				//OBTEM CONSUMO MEDIO
				int[] consumos = getControladorMicromedicao().obterVolumeMedioAguaEsgoto(imovel.getId(),
						sistemaParametro.getAnoMesFaturamento(),1, houveIntslacaoHidrometro);
				
				String consumoMedio = consumos[0]+"";
				
				//OBTEM ANORMALIDADE DA LIGACAO AGUA
				String ocorrencia = getControladorMicromedicao()
					.pesquisarAnormalidadesImovel(idImovel,ConstantesSistema.CALCULAR_AGUA);
				
				//OBTEM VALOR DO SERVIÇO E DO DÉBITO
				String referenciaInicial = "01/0001";
				String referenciaFinal = "12/9999";
				String dataVencimentoInicial = "01/01/0001";
				String dataVencimentoFinal = "31/12/9999";

				// Para auxiliar na formatação de uma data
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				String mesInicial = referenciaInicial.substring(0, 2);
				String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
				String anoMesInicial = anoInicial + mesInicial;
				String mesFinal = referenciaFinal.substring(0, 2);
				String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
				String anoMesFinal = anoFinal + mesFinal;

				Date dataVencimentoDebitoI;
				Date dataVencimentoDebitoF;

				try {
					dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
				} catch (ParseException ex) {
					dataVencimentoDebitoI = null;
				}
				try {
					dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
				} catch (ParseException ex) {
					dataVencimentoDebitoF = null;
				}

				// seta valores constantes para chamar o metodo que consulta debitos do imovel
				Integer tipoImovel = new Integer(1);
				Integer indicadorPagamento = new Integer(1);
				Integer indicadorConta = new Integer(1);
				Integer indicadorDebito = new Integer(1);
				Integer indicadorCredito = new Integer(1);
				Integer indicadorNotas = new Integer(1);
				Integer indicadorGuias = new Integer(1);
				Integer indicadorAtualizar = new Integer(1);
				
				ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = getControladorCobranca()
						.obterDebitoImovelOuCliente(tipoImovel.intValue(),
								idImovel.toString(), null, null,
								anoMesInicial, anoMesFinal,
								dataVencimentoDebitoI,
								dataVencimentoDebitoF, indicadorPagamento
										.intValue(), indicadorConta
										.intValue(), indicadorDebito
										.intValue(), indicadorCredito
										.intValue(), indicadorNotas
										.intValue(), indicadorGuias
										.intValue(), indicadorAtualizar
										.intValue(), null);

				Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();

				ContaValoresHelper dadosConta = null;

				BigDecimal valorConta = new BigDecimal("0.00");
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				BigDecimal valorAgua = new BigDecimal("0.00");
				BigDecimal valorEsgoto = new BigDecimal("0.00");
				BigDecimal valorDebito = new BigDecimal("0.00");
				BigDecimal valorCredito = new BigDecimal("0.00");
				BigDecimal valorImposto = new BigDecimal("0.00");

				if (colecaoContaValores != null	&& !colecaoContaValores.isEmpty()) {
					java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
					// percorre a colecao de conta somando o valor para obter um valor total
					while (colecaoContaValoresIterator.hasNext()) {

						dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
						valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
						valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
						valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
						valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
						valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
						valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
						valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
					}
				}

				Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();

				BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
				BigDecimal valorDebitoACobrarSemJurosParcelamento = new BigDecimal("0.00");
				DebitoACobrar dadosDebito = null;

				if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
					java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
					// percorre a colecao de debito a cobrar somando o valor para obter um valor total
					while (colecaoDebitoACobrarIterator.hasNext()) {

						dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
						valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
						
						if (dadosDebito.getDebitoTipo() != null &&
								!dadosDebito.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
							valorDebitoACobrarSemJurosParcelamento = valorDebitoACobrarSemJurosParcelamento.add(dadosDebito.getValorTotalComBonus());
						}
						
					}
				}

				Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();

				BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
				BigDecimal valorCreditoARealizarSemDescontosParcelamento = new BigDecimal("0.00");
				CreditoARealizar dadosCredito = null;

				if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
					java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
					// percorre a colecao de credito a realizar somando o valor para obter um valor total
					while (colecaoCreditoARealizarIterator.hasNext()) {

						dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
						valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
						
						if (dadosCredito.getCreditoOrigem() != null && 
								!dadosCredito.getCreditoOrigem().getId().equals(CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)){
							valorCreditoARealizarSemDescontosParcelamento = valorCreditoARealizarSemDescontosParcelamento.add(dadosCredito.getValorTotalComBonus());
						}
					}
				}

				Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();

				BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
				GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

				if (colecaoGuiaPagamentoValores != null	&& !colecaoGuiaPagamentoValores.isEmpty()) {
					java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
					// percorre a colecao de guia de pagamento somando o valor para obter um valor total
					while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

						dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
						valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
					}
				}

				

				// Soma o valor total dos debitos e subtrai dos creditos
				BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
				valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
				valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);			
				
				//SETA DADOS PESQUISADOS EM UM HELPER E ADICIONA NA COLEÇÃO DE RETORNO
				EmitirOrdemDeFiscalizacaoHelper helper = 
					new EmitirOrdemDeFiscalizacaoHelper();
				
				helper.setImovel(imovel);
				helper.setIdLocalidade(idLocalidade);
				helper.setIdSetorComercial(idSetorComercial);
				helper.setCodigoSetorComercial(codSetor);
				helper.setIdQuadra(idQuadra);
				helper.setNumeroQuadra(numeroQuadra);
				helper.setNumeroLote(lote.intValue());
				helper.setNumeroSubLote(subLote.intValue());
				helper.setDataEmissao(new Date());
				helper.setNumeroInscricao(imovel.getInscricaoFormatada());
				helper.setMatriculaImovel(imovel.getId().toString());
				helper.setDescricaPerfilImovel(descPerfil);
				helper.setDescricaoEndereco(descricaoEndereco);
				helper.setBairro(bairro);
				helper.setMunicipio(municipio);
				helper.setUnidadeFederacao(unidadeFederacao);
				helper.setCep(cep);
				helper.setQtdEconomiasRes(qtdeEconResidencial);
				helper.setQtdEconomiasCom(qtdeEconComercial);
				helper.setQtdEconomiasInd(qtdeEconIndustrial);
				helper.setQtdEconomiasPub(qtdeEconPublica);
				helper.setQtdEconomiasTot(qtdeEconTotal);
				helper.setUltimaAlteracao(Util.formatarData(ultimaAlteracao));
				helper.setIdGrupoFaturamento(faturamentoGrupo);
				helper.setDescricaoSituacaoAgua(desLigacaoAguaSituacao);
				helper.setCosumoMedio(new Integer(consumoMedio));
				
				if(idLigacaoAguaSituacao.compareTo(LigacaoAguaSituacao.CORTADO)==0){
					helper.setDataCorte(Util.formatarData(dataCorte));
				}
				if(idLigacaoAguaSituacao.compareTo(LigacaoAguaSituacao.SUPR_PARC)==0 ||
						idLigacaoAguaSituacao.compareTo(LigacaoAguaSituacao.SUPR_PARC_PEDIDO)==0){
					helper.setDataSupressaoParcial(Util.formatarData(dataSupressao));
				}
				if(idLigacaoAguaSituacao.compareTo(LigacaoAguaSituacao.SUPRIMIDO)==0){
					helper.setDataSupressaoTotal(Util.formatarData(dataSupressao));
				}
				
				helper.setDescricaoSituacaoEsgoto(descLigacaoEsgotoSituacao);
				
				if(idLigacaoEsgotoSituacao.compareTo(LigacaoEsgotoSituacao.POTENCIAL)!=0 &&
						idLigacaoEsgotoSituacao.compareTo(LigacaoEsgotoSituacao.FACTIVEL)!=0){
					helper.setVolumeFixoEsgoto(consumoFixoEsgoto);
				}
				
				helper.setDescricaoOcorrencia(ocorrencia);				
				helper.setValorServicosAtualizacoes(valorTotalSemAcrescimo);
				helper.setValorDebito(valorDebitoACobrar);
				helper.setNomeCliente(nomeCliente);
				
				if(cpf!=null && !cpf.equals("")){
					helper.setCpfCnpj(Util.formatarCpf(cpf));
				}else if(cnpj!=null && !cnpj.equals("")){
					helper.setCpfCnpj(Util.formatarCnpj(cnpj));
				}
				
				helper.setRg(rg);
				helper.setNumeroTelefonico(fone);
				helper.setRamal(ramal);
				helper.setTipoTelefonico(foneTipo);
				
				retorno.add(helper);
			}
		
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		
		return retorno;
	}

	/**
	 *  [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 * 	O sistema deve excluir todos os registros da tabela IMOVEIS_SUPRIMIDOS,
	 *  que estejam com a situação de finalizado na tabela ORDEM_SERVICO. 
	 *  
	 * @author Hugo Amorim
	 * @param idSetorComercial 
	 * @date 08/03/2010
	 * 
	 */
	private void excluirDadosFinalizados(Integer idSetorComercial) throws ControladorException {
		
		
		FiltroImovelSuprimido filtroImovelSuprimido =
			new FiltroImovelSuprimido();
		
		filtroImovelSuprimido.adicionarParametro(
				new ParametroSimples(
						FiltroImovelSuprimido.ORDEM_SERVICO_SITUACAO,
						OrdemServico.SITUACAO_ENCERRADO));
		
		filtroImovelSuprimido.adicionarParametro(
				new ParametroSimples(
						FiltroImovelSuprimido.SETOR_COMERCIAL_ID,
						idSetorComercial));
		
		Collection<Object> colecaoImoveisSuprimidos = 
			this.getControladorUtil().pesquisar(filtroImovelSuprimido, ImovelSuprimido.class.getName());
		
		if(colecaoImoveisSuprimidos!=null && !colecaoImoveisSuprimidos.isEmpty()){
			this.getControladorBatch().removerColecaoObjetoParaBatch(colecaoImoveisSuprimidos);
		}
		
	}
	
	/**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 *  Step que gera os arquivos.
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 10/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void gerarArquivoOrdemDeFiscalizacao(Integer idFuncionalidadeIniciada, Usuario usuario)
		throws ControladorException{
		
		int idUnidadeIniciada = 0;
		
		// Criação do Arquivo
		// ========================================================================
		Date dataAtual = new Date();
		String nomeZip = null;
		nomeZip = "ORDEM_FISCALIZACAO_IMOVEIS_SUPRIMOS_"
				+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
		nomeZip = nomeZip.replace("/", "_");
		File compactado = new File(nomeZip + ".zip");
		ZipOutputStream zos = null;
		File leitura = new File(nomeZip + ".txt");
		BufferedWriter out = null;
		// ========================================================================
		
		try {
			
			// -------------------------
			// Registrar o início do processamento da Unidade de
			// Processamento do Batch
			// -------------------------

			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.FUNCIONALIDADE, 0);
			
			zos = new ZipOutputStream(new FileOutputStream(compactado));
        	out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
				
			// Variáveis para a paginação da pesquisa
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeMaxima = 1000;
			int quantidadeInicio = 0;
			// ========================================================================
			
			int sequencial = 0;
			
			Date dataExecucao = new Date();
			String data = Util.formatarData(dataExecucao);
			StringBuilder linha = null;
			while (!flagTerminou) {
				
				Collection<ImovelSuprimido> colecaoDados = 
					this.repositorioAtendimentoPublico
						.pesquisarDadosEmitirArquivoTextoDeOrdemFiscalizacao(quantidadeInicio,quantidadeMaxima);

				
				for (ImovelSuprimido imovelSuprimido : colecaoDados) {
					
					sequencial++;
					
					linha = new StringBuilder();
					
					linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(imovelSuprimido.getLinhaTxt(),410));
					linha.append(data);
					linha.append(sequencial+"");
					linha.append(System.getProperty("line.separator"));
					
					out.write(linha.toString());
					out.flush();
					
					imovelSuprimido.setDataExecucao(dataExecucao);
					imovelSuprimido.setSequencialImpressao(sequencial);
					getControladorBatch().atualizarObjetoParaBatch(imovelSuprimido);
					
					linha = null;
					
				}
				
				// Incrementa o nº do indice da páginação
				quantidadeInicio = quantidadeInicio + quantidadeMaxima;
	
				/**
				 * Caso a coleção de dados retornados for menor que a
				 * quantidade de registros seta a flag indicando que a
				 * paginação terminou.
				 */
				if (colecaoDados == null || 
						colecaoDados.size() < quantidadeMaxima) {
	
					flagTerminou = true;
				}
	
				if (colecaoDados != null) {
					colecaoDados.clear();
					colecaoDados = null;
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		
		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			throw new EJBException(e);
		} finally {
			try{
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);
		
				// close the stream
				zos.close();
				leitura.delete();
			} catch (IOException e) {
				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
	            throw new EJBException(e);
			}
        }	
		
	}
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e
	 * valor. Pesquisa a última vigência de cada tipo serviço, e retorna uma
	 * coleção.
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 03/02/2010 - 23/04/2010
	 */
	public Collection<ServicoCobrancaValor> pesquisarServicoCobrancaValorUltimaVigencia(
			Integer numeroPagina) throws ControladorException {
		
		Collection<ServicoCobrancaValor> retorno = new ArrayList<ServicoCobrancaValor>();
		
		try {
			Collection<ServicoCobrancaValor> colecao = this.repositorioAtendimentoPublico
					.pesquisarServicoCobrancaValorUltimaVigencia(numeroPagina);
			
			if (colecao != null && !colecao.isEmpty()) {
				
				 Iterator iterator = colecao.iterator();
				 while (iterator.hasNext()) {
					Object[] object = (Object[]) iterator.next();
					
					ServicoCobrancaValor servicoCobrancaValor = new ServicoCobrancaValor();
					servicoCobrancaValor.setId((Integer)object[0]);
					
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID,object[1].toString()));
					Collection<ServicoTipo> collServicoTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo,ServicoTipo.class.getName());
					
					ServicoTipo servicoTipo = collServicoTipo.iterator().next();
					
					servicoCobrancaValor.setServicoTipo(servicoTipo);
					
					ImovelPerfil imovelPerfil = null;
					
					if (object[2] != null) {
						FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,object[2].toString()));
						Collection<ImovelPerfil> collImovelPerfil = Fachada.getInstancia().pesquisar(filtroImovelPerfil,ImovelPerfil.class.getName());
						
						imovelPerfil = collImovelPerfil.iterator().next();

					}
									
					servicoCobrancaValor.setImovelPerfil(imovelPerfil);
					
					HidrometroCapacidade hidrometroCapacidade = null;
					
					if (object[3] != null) {
						
						FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
						filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID,object[3].toString()));
						Collection<HidrometroCapacidade> collHidrometroCapacidade = Fachada.getInstancia().pesquisar(filtroHidrometroCapacidade,HidrometroCapacidade.class.getName());
						
						hidrometroCapacidade = collHidrometroCapacidade.iterator().next();

					}			
					
					servicoCobrancaValor.setHidrometroCapacidade(hidrometroCapacidade);
					
					servicoCobrancaValor.setValor((BigDecimal) object[4]);
					
					servicoCobrancaValor.setIndicadorMedido((Short) object[5]);
					
					servicoCobrancaValor.setDataVigenciaInicial((Date) object[8]);
					
					servicoCobrancaValor.setDataVigenciaFinal((Date) object[9]);
					
					Subcategoria subcategoria = null;
					
					 if (object[7] != null) {
											
						 FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
						 filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,object[7].toString()));
						
						 Collection<Subcategoria> collSubCategoria = Fachada.getInstancia().pesquisar(filtroSubCategoria,Subcategoria.class.getName());
											
						 subcategoria = collSubCategoria.iterator().next();
					 }				
					
					servicoCobrancaValor.setSubCategoria(subcategoria);
					
					servicoCobrancaValor.setQuantidadeEconomiasInicial((Integer) object[10]);
					servicoCobrancaValor.setQuantidadeEconomiasFinal((Integer) object[11]);
					servicoCobrancaValor.setIndicadorConsideraEconomias((Short) object[12]);
					servicoCobrancaValor.setUltimaAlteracao((Date) object[6]);
					
					Categoria categoria = null;
					
					if (object[13] != null) {
						
						 FiltroCategoria filtroCategoria = new FiltroCategoria();
						 filtroCategoria.adicionarParametro(new ParametroSimples(
								 FiltroCategoria.CODIGO,new Integer(object[13].toString())));
						
						 Collection<Categoria> collCategoria = Fachada.getInstancia().pesquisar(filtroCategoria,Categoria.class.getName());
											
						 categoria = collCategoria.iterator().next();
					 }
					
					servicoCobrancaValor.setCategoria(categoria);
					
					retorno.add(servicoCobrancaValor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

		return retorno;
	}

	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e
	 * valor. Pesquisa a última vigência de cada tipo serviço, e retorna o
	 * total.
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Integer pesquisarServicoCobrancaValorUltimaVigenciaTotal()
			throws ControladorException {
		try {
			return repositorioAtendimentoPublico
					.pesquisarServicoCobrancaValorUltimaVigenciaTotal();
		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

	}

	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e
	 * valor. Pesquisa a última vigência de cada tipo serviço, e retorna uma
	 * coleção.
	 * 
	 * @author Hugo Leonardo
	 * @date 23/04/2010
	 */
	public Collection<ServicoCobrancaValor> replicarServicoCobrancaValorUltimaVigencia(
			String[] selecionacos) throws ControladorException {

		Collection<ServicoCobrancaValor> retorno = new ArrayList<ServicoCobrancaValor>();
		
		try {
			Collection<ServicoCobrancaValor> colecao = this.repositorioAtendimentoPublico
					.replicarServicoCobrancaValorUltimaVigencia(selecionacos);
			
			if (colecao != null && !colecao.isEmpty()) {
				
				 Iterator iterator = colecao.iterator();
				 while (iterator.hasNext()) {
					Object[] object = (Object[]) iterator.next();
					
					ServicoCobrancaValor servicoCobrancaValor = new ServicoCobrancaValor();
					servicoCobrancaValor.setId((Integer)object[0]);
					
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID,object[1].toString()));
					Collection<ServicoTipo> collServicoTipo = 
						this.getControladorUtil().pesquisar(filtroServicoTipo,ServicoTipo.class.getName());
					
					ServicoTipo servicoTipo = collServicoTipo.iterator().next();
					
					servicoCobrancaValor.setServicoTipo(servicoTipo);
					
					ImovelPerfil imovelPerfil = null;
					
					if (object[2] != null) {
						FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,object[2].toString()));
						Collection<ImovelPerfil> collImovelPerfil = 
							this.getControladorUtil().pesquisar(filtroImovelPerfil,ImovelPerfil.class.getName());
						
						imovelPerfil = collImovelPerfil.iterator().next();

					}
									
					servicoCobrancaValor.setImovelPerfil(imovelPerfil);
					
					HidrometroCapacidade hidrometroCapacidade = null;
					
					if (object[3] != null) {
						
						FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
						filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID,object[3].toString()));
						Collection<HidrometroCapacidade> collHidrometroCapacidade = 
							this.getControladorUtil().pesquisar(filtroHidrometroCapacidade,HidrometroCapacidade.class.getName());
						
						hidrometroCapacidade = collHidrometroCapacidade.iterator().next();

					}			
					
					servicoCobrancaValor.setHidrometroCapacidade(hidrometroCapacidade);
					
					servicoCobrancaValor.setValor((BigDecimal) object[4]);
					
					servicoCobrancaValor.setIndicadorMedido((Short) object[5]);
					
					servicoCobrancaValor.setDataVigenciaInicial((Date) object[8]);
					
					servicoCobrancaValor.setDataVigenciaFinal((Date) object[9]);
					
					Subcategoria subcategoria = null;
					
					 if (object[7] != null) {
											
						 FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
						 filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,object[7].toString()));
						 
						 Collection<Subcategoria> collSubCategoria = this.getControladorUtil().pesquisar(filtroSubCategoria,Subcategoria.class.getName());
											
						 subcategoria = collSubCategoria.iterator().next();
					 }				
					
					servicoCobrancaValor.setSubCategoria(subcategoria);
					
					servicoCobrancaValor.setQuantidadeEconomiasInicial((Integer) object[10]);
					servicoCobrancaValor.setQuantidadeEconomiasFinal((Integer) object[11]);
					servicoCobrancaValor.setIndicadorConsideraEconomias((Short) object[12]);
					servicoCobrancaValor.setUltimaAlteracao((Date) object[6]);
					
					Categoria categoria = null;
					if (object[13] != null) {
						FiltroCategoria filtroCategoria = new FiltroCategoria();
						filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, object[13].toString()));
	
						Collection<Categoria> collCategoria = this.getControladorUtil().pesquisar(
						filtroCategoria, Categoria.class.getName());
	
						categoria = collCategoria.iterator().next();
					}

					servicoCobrancaValor.setCategoria(categoria);

					servicoCobrancaValor.setIndicadorGeracaoDebito((Short) object[14]);
					
					
					
					retorno.add(servicoCobrancaValor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

		return retorno;
	}
	
	/**
	 * [UC0391] Inserir valor cobrança Serviço.
	 * 
	 * Verificar se existe vigência já cadastrada para o Serviço Tipo.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idServicoTipo
	 * @param opcao
	 * @throws ControladorException
	 * @data 03/05/2010
	 * 
	 * @see Caso a opcao = 1 - verifica as situações de inserir e atualizar Serviço Tipo.
	 * @see Caso a opcao = 2 - verifica a situação de retificar Serviço Tipo.
	 */
	public void verificarExistenciaVigenciaServicoTipo(String dataVigenciaInicial, String dataVigenciaFinal, Integer idServicoTipo, Integer opcao) 
			throws ControladorException {
		
		String retorno = "";
		
		try {
			retorno = repositorioAtendimentoPublico.verificarExistenciaVigenciaServicoTipo(
					dataVigenciaInicial, dataVigenciaFinal, idServicoTipo);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		// Caso a opcao seja iqual a 1 - verifica as situações de inserir e atualizar Serviço Tipo.
		// 
		// Caso a opcao seja iqual a 2 - verifica a situação de retificar Serviço Tipo.
		//
		if(opcao == 1){
			if (retorno != null && !retorno.equals("")) {
				throw new ControladorException(
						"atencao.data_vigencia_final_servico_ja_cadastrada", null, retorno);
			}
		}else if(opcao == 2){
			if (retorno != null && !retorno.equals("")) {
				throw new ControladorException(
						"atencao.data_vigencia_final_servico_ja_cadastrada_retificar", null, retorno);
			}
		}
	}
	
	/**
	 * [UC0363] Efetuar Retirada de hidrômetro quando chamado atraves da funcionalidade
	 * efetuar supressao do imovel 
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 18/05/2010
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @throws ControladorException
	 */

	public void efetuarRetiradaHidrometroSemGeracaoDebito(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = integracaoComercialHelper
				.getHidrometroInstalacaoHistorico();

		if (integracaoComercialHelper.getHidrometroInstalacaoHistorico() != null) {
			HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();
			hidrometroSituacao.setId(HidrometroSituacao.DISPONIVEL);
			integracaoComercialHelper.getHidrometroInstalacaoHistorico()
					.getHidrometro().setHidrometroSituacao(hidrometroSituacao);
		}
		
		
		this.getControladorMicromedicao().validarImovelEmCampo(hidrometroInstalacaoHistorico.getLigacaoAgua().getId());
		
		hidrometroInstalacaoHistorico.setDataRetirada(new Date());
		this.verificarHidrometroInstalacaoHistoricoControleConcorrencia(hidrometroInstalacaoHistorico);

		getControladorUtil().atualizar(hidrometroInstalacaoHistorico);

		this.verificarHidrometroControleConcorrencia(hidrometroInstalacaoHistorico.getHidrometro());

		getControladorUtil().atualizar(hidrometroInstalacaoHistorico.getHidrometro());

		try {
			// Caso o tipo de medição seja igual a Ligação de Água, atualiza as
			// colunas da tabela LIGACAO_AGUA
			// Integer id = hidrometroInstalacaoHistorico.getId();
			if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId().equals(
					MedicaoTipo.LIGACAO_AGUA)) {

				repositorioAtendimentoPublico
						.atualizarHidrometroInstalacaoHistoricoLigacaoAgua(
								hidrometroInstalacaoHistorico.getLigacaoAgua()
										.getId(), null);

				// Caso o tipo de medição seja igual a Poço, atualiza as colunas
				// da tabela POCO
			} else if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId()
					.equals(MedicaoTipo.POCO)) {

				repositorioAtendimentoPublico
						.atualizarHidrometroIntalacaoHistoricoImovel(
								hidrometroInstalacaoHistorico.getImovel()
										.getId(), null, null);
			}

			OrdemServico ordemServico = integracaoComercialHelper
					.getOrdemServico();
			if (ordemServico != null) {
				// [SB006]Atualizar Ordem de Serviço
				if (!integracaoComercialHelper.isVeioEncerrarOS()
						&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
					getControladorOrdemServico().atualizaOSGeral(
							integracaoComercialHelper.getOrdemServico());
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento tem o nivel selecionado como Urgência
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   04/06/2010
	 * 
	 */
	public Integer verificarRegistroAtendimentoUrgencia(Integer idRegistroAtendimento) throws ControladorException{
		try {
			return repositorioAtendimentoPublico
					.verificarRegistroAtendimentoUrgencia(idRegistroAtendimento);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] – Verificar RA de urgência
	 *  
	 * Adicionar os Usuários da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   03/06/2010
	 * 
	 */	
	
	public void inserirUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento,Short indicadorReiteracao)
			throws ControladorException {
		try {
			 Collection usuariosVisualizacaoRAUrgencia = repositorioAtendimentoPublico
					.pesquisarUsuarioVisualizacaoRaUrgencia(idRegistroAtendimento);
			 if(usuariosVisualizacaoRAUrgencia != null && !usuariosVisualizacaoRAUrgencia.isEmpty()){
				 
				Iterator iterator = usuariosVisualizacaoRAUrgencia.iterator();
				
				while(iterator.hasNext()){
					VisualizacaoRegistroAtendimentoUrgencia visualizacaoRegistroAtendimentoUrgencia = new VisualizacaoRegistroAtendimentoUrgencia();
					Object[] obj = (Object[]) iterator.next();
										
					RegistroAtendimento registroAtendimento = new RegistroAtendimento();
					registroAtendimento.setId((Integer)obj[0]);
					Usuario usuario = new Usuario();
					usuario.setId((Integer)obj[1]);
					
					visualizacaoRegistroAtendimentoUrgencia.setRegistroAtendimento(registroAtendimento);
					visualizacaoRegistroAtendimentoUrgencia.setUsuario(usuario);
					visualizacaoRegistroAtendimentoUrgencia.setIndicadorTramite((Integer)obj[2]);
					visualizacaoRegistroAtendimentoUrgencia.setIndicadorVisualizacao((Integer)obj[3]);
					visualizacaoRegistroAtendimentoUrgencia.setUltimaAlteracao(new Date());
					visualizacaoRegistroAtendimentoUrgencia.setIndicadorReiteracao(indicadorReiteracao);
					
					repositorioUtil.inserir(visualizacaoRegistroAtendimentoUrgencia);
					
				}
				
			 }
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento
	 *  [SB0004] – Verificar RA de urgência
	 *  
	 * Atualizar os Usuários da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   03/06/2010
	 * 
	 */
	public void atualizarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento, Integer idUnidade, Integer idUsuario, Integer indicadorTramite, Integer indicadorVisualizacao)
			throws ControladorException {
		try {
			String idUsuarios = null;
			//caso seja informado a unidade, seleciona todos os usuarios dela.
			if(idUnidade != null){
				Collection<Usuario> colecao  = (Collection<Usuario>) repositorioUsuario.pesquisarUsuariosUnidadeOrganizacional(idUnidade);
				if (colecao != null && colecao.size() > 0){
					
					idUsuarios = "";			
					
					for(Usuario usuario : colecao){
						idUsuarios += usuario.getId() +", ";
					}
					
					idUsuarios = Util.removerUltimosCaracteres(idUsuarios, 2);
					
					repositorioAtendimentoPublico.atualizarUsuarioRegistroAtendimentoUrgencia(idRegistroAtendimento, idUsuarios, idUsuario, indicadorTramite, indicadorVisualizacao);
					
				}				
				//caso nao retorne nenhum usuario nao deve executar o atualizar
				
			}else{
				repositorioAtendimentoPublico.atualizarUsuarioRegistroAtendimentoUrgencia(idRegistroAtendimento, idUsuarios, idUsuario, indicadorTramite, indicadorVisualizacao);
			}			
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento já está relacionado a uma Unidade informada.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   05/06/2010
	 * 
	 */
	public Integer verificarUsuariosRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, Integer idUnidade)
		throws ControladorException{
		
		try {
			return repositorioAtendimentoPublico
					.verificarUsuariosRegistroAtendimentoUrgencia(idRegistroAtendimento, idUnidade);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
				
	}
	
	
	/**	 
	 * [UC1028] Exibir Registro Atendimento Urgência
	 *  
	 * Verifica se o Usuario possui algum Registro de Atendimento urgente.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   07/06/2010
	 * 	  
	 */
	public Collection verificarUsuarioRegistroAtendimentoUrgencia(Integer idUsuario)
		throws ControladorException{
		
		try {
			return repositorioAtendimentoPublico
					.verificarUsuarioRegistroAtendimentoUrgencia(idUsuario);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
    /**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Hugo Amorim
	 * @date 15/07/2010
	 */
	public Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> 
		pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando, Collection idsSituacos)throws ControladorException{
		try {
			
			return repositorioAtendimentoPublico
					.pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
							idComando,idsSituacos);
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}  
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRAAnalitico(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) throws ControladorException {
		
		Collection colecaoRetorno = new ArrayList();
		
		try {
			
			Collection colecaoAcompanhamentoRA =  this.repositorioAtendimentoPublico.
				pesquisarRelatorioAcompanhamentoRAAnalitico(helper);
			
			Iterator iterator = colecaoAcompanhamentoRA.iterator();
			
			while (iterator.hasNext()) {
			
				RelatorioAcompanhamentoRAHelper relatorioHelper = new RelatorioAcompanhamentoRAHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				// Número RA
				String idRA = "";
				if ( objeto[0] != null ) {
					idRA = objeto[0].toString();
					
					relatorioHelper.setIdRA(idRA);
				}
				
				// Unidade Atendimento
				String idUnidadeAtendimento = "";
				if ( objeto[1] != null ) {
					idUnidadeAtendimento = objeto[1].toString();
					
					relatorioHelper.setIdUnidadeAtendimento(idUnidadeAtendimento);
				}
				
				// Solicitacao Tipo Especificacao
				String tipoSolicitacao = "";
				if ( objeto[2] != null ) {
					tipoSolicitacao = objeto[2].toString();
					
					relatorioHelper.setTipoSolicitacao( tipoSolicitacao);
				}
				
				// Data Abertura
				Date dataAbertura;
				if ( objeto[3] != null ) {
					dataAbertura = (Date) objeto[3];
					
					relatorioHelper.setDataAbertura(Util.formatarData(dataAbertura));
				}
				
				// Data Encerramento
				Date dataEncerramento;
				if ( objeto[4] != null ) {
					dataEncerramento = (Date) objeto[4];
					
					relatorioHelper.setDataEncerramento(Util.formatarData(dataEncerramento));
				}
				
				// Motivo Encerramento
				String motivoEncerramento = "";
				if( objeto[5] != null){
					motivoEncerramento = objeto[5].toString();
					
					relatorioHelper.setMotivoEncerramento( motivoEncerramento);
				}
				
				// Descrição Unidade Atendimento
				String descricaoUnidadeAtendimento = "";
				if( objeto[6] != null){
					descricaoUnidadeAtendimento = objeto[6].toString();
					
					relatorioHelper.setDescricaoUnidadeAtendimento(""+ 
							idUnidadeAtendimento + " - " + descricaoUnidadeAtendimento);
				}
				
				// Id Motivo Encerramento
				String idMotivoEncerramento;
				if( objeto[7] != null){

					idMotivoEncerramento = objeto[7].toString();
					relatorioHelper.setIdMotivoEncerramento(idMotivoEncerramento);
				}
				
				if(!Util.isVazioOrNulo(helper.getMunicipiosAssociados())){
					//Descrição Município (Caso o filtro tenha selecionado pelo menos um município associado à localidade)
					String descricaoMunicipio;
					if(objeto[8] != null){
						descricaoMunicipio = objeto[8].toString();
						relatorioHelper.setDescricaoMunicipio(descricaoMunicipio);
					}
					
					//ID Município (Caso o filtro tenha selecionado pelo menos um município associado à localidade)
					String idMunicipio;
					if(objeto[9] != null){
						idMunicipio = objeto[9].toString();
						relatorioHelper.setIdMunicipio(idMunicipio);
					}
				}
				colecaoRetorno.add(relatorioHelper);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return colecaoRetorno;
	}
	
	/**
	 * [UC1056] Pesquisar Total de RA's do Relatório de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 30/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Intenger
	 * 
	 * @throws ControladorException
	 */
	public Integer countPesquisarRelatorioAcompanhamentoRAAnalitico(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) throws ControladorException {
		
		try {
			
			return this.repositorioAtendimentoPublico
					.countPesquisarRelatorioAcompanhamentoRAAnalitico(helper);
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento Sintetico Encerrado
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoEncerrado(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 	throws ControladorException {
		Collection colecaoRetorno = new ArrayList();
		
		try {
			
			Collection colecaoAcompanhamentoRA =  this.repositorioAtendimentoPublico
				.pesquisarRelatorioAcompanhamentoRASinteticoEncerrado(helper);
			
			Iterator iterator = colecaoAcompanhamentoRA.iterator();
			
			String descricaoMunicipio = "";
			String descricaoUnidadeAtendimento = "";
			String motivoEncerramento = "";
			Integer quantidade = 0;
			while (iterator.hasNext()) {
			
				RelatorioAcompanhamentoRAHelper relatorioHelper = new RelatorioAcompanhamentoRAHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				// Descrição Unidade Atendimento
				if( objeto[0] != null){
					descricaoUnidadeAtendimento = objeto[0].toString();
				}
				
				// Motivo Encerramento
				if( objeto[1] != null){
					motivoEncerramento = objeto[1].toString();
				}
				
				// Quantidade
				if ( objeto[2] != null ) {
					quantidade = (Integer) objeto[2];
				}

				//Município Associado à Localidade
				if(!Util.isVazioOrNulo(helper.getMunicipiosAssociados())){
					if(objeto[3] != null){
						descricaoMunicipio = (String) objeto[3];
					}
				}
				relatorioHelper.setDescricaoUnidadeAtendimento( descricaoUnidadeAtendimento);
				relatorioHelper.setDescricaoMunicipio(descricaoMunicipio);
				relatorioHelper.setMotivoEncerramento( motivoEncerramento);
				relatorioHelper.setQuantidade(quantidade);
				colecaoRetorno.add(relatorioHelper);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return colecaoRetorno;
	}
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento Sintetico Aberto
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoAberto(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 	throws ControladorException {
		
		Collection colecaoRetorno = new ArrayList();
		
		try {
			
			Collection colecaoAcompanhamentoRA =  this.repositorioAtendimentoPublico
				.pesquisarRelatorioAcompanhamentoRASinteticoAberto(helper);
			
			Iterator iterator = colecaoAcompanhamentoRA.iterator();
			
			while (iterator.hasNext()) {
			
				RelatorioAcompanhamentoRAHelper relatorioHelper = new RelatorioAcompanhamentoRAHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				// Descrição Unidade Atendimento
				String descricaoUnidadeAtendimento = null;
				String descricaoMunicipio = null;
				Integer quantidade = 0;

				if( objeto[0] != null){
					descricaoUnidadeAtendimento = (String)objeto[0];
				}
				if(!Util.isVazioOrNulo(helper.getMunicipiosAssociados())){
					if ( objeto[1] != null ) {
						descricaoMunicipio = (String) objeto[1];
					}
					
					if ( objeto[2] != null ) {
						quantidade = (Integer) objeto[2];
					}
				}else{
					if ( objeto[1] != null ) {
						quantidade = (Integer) objeto[1];
					}
				}
				relatorioHelper.setDescricaoUnidadeAtendimento(descricaoUnidadeAtendimento);
				relatorioHelper.setDescricaoMunicipio(descricaoMunicipio);
				relatorioHelper.setQuantidade(quantidade);
				colecaoRetorno.add(relatorioHelper);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return colecaoRetorno;
	}
	
	/**
	 * Remover todas as LocalidadeComEspecificacaoUnidade
	 * [UC1091] Informar Associação de Localidade com Especificação e Unidade
	 * 
	 * @author Hugo Leonardo
	 * @date 30/11/2010
	 * 
	 * @param idLocalidade
	 * @return void
	 */
	public void removerLocalidadeComEspecificacaoUnidade( Integer idLocalidade) throws ControladorException {
		
		Collection resultadoPesquisa = null;
		
		try {
			
			FiltroLocalidadeEspecificacaoUnidade filtro = new FiltroLocalidadeEspecificacaoUnidade();
			
			filtro.adicionarParametro(new ParametroSimples(
					FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE_ID, idLocalidade));
			
			// Procura na base
			resultadoPesquisa =  getControladorUtil().pesquisar(
					filtro, LocalidadeEspecificacaoUnidade.class.getName());
			
			if (resultadoPesquisa != null && !resultadoPesquisa.isEmpty()) {
				
				Iterator iterator = resultadoPesquisa.iterator();
			
				LocalidadeEspecificacaoUnidade localidadeEspecificacaoUnidade = null;
				
				while (iterator.hasNext()) {
					
					localidadeEspecificacaoUnidade = (LocalidadeEspecificacaoUnidade) iterator.next();
					
					this.repositorioUtil.remover(localidadeEspecificacaoUnidade);
				}
			}
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1102] - Inserir Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @data: 03/12/2010
	 * 
	 * @param descricao
	 * @param indicadorUso
	 * @param indicadorCorteAdministrativo
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirTipoCorte(String descricao, String indicadorUso, String indicadorCorteAdministrativo, Usuario usuarioLogado)
			throws ControladorException {

		CorteTipo corteTipo = new CorteTipo();
		corteTipo.setDescricao(descricao);
		corteTipo.setIndicadorUso(new Short(indicadorUso));
		corteTipo.setIndicadorCorteAdministrativo(new Short(indicadorCorteAdministrativo));
		corteTipo.setUltimaAlteracao(new Date());

		// [FS0002] Verificar existencia da Descrição
		FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
		filtroCorteTipo.adicionarParametro(new ParametroSimples(
				FiltroCorteTipo.DESCRICAO, corteTipo.getDescricao()));
		
		Collection colecaoCorteTipo = getControladorUtil().pesquisar(filtroCorteTipo, CorteTipo.class.getName());

		if (colecaoCorteTipo != null && !colecaoCorteTipo.isEmpty()) {
			throw new ControladorException(
					"atencao.descricao_ja_existente_tipo_corte", null, corteTipo.getDescricao());
		}
		
		// ------------ REGISTRAR TRANSACAO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_TIPO_CORTE_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_TIPO_CORTE_INSERIR);
		
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		
		corteTipo.setOperacaoEfetuada(operacaoEfetuada);
		corteTipo.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(corteTipo);

		// --------FIM---- REGISTRAR TRANSACAO----------------------------
		Integer idTipoCorte = (Integer) getControladorUtil().inserir(corteTipo);

		return idTipoCorte;
	}
	
	/**
	 * [UC1103] Manter Tipo de Corte
	 *  
	 * @author Ivan Sergio
	 * @date 06/12/2010
	 * 
	 * @pparam cortetipo
	 * @throws ControladorException
	 */
	public void atualizarCorteTipo(CorteTipo corteTipo, Usuario usuarioLogado)
			throws ControladorException {

		FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
		
		// [FS0002] - Verificar Atualizacao Por Outro Usuario
		filtroCorteTipo.adicionarParametro(new ParametroSimples(
				FiltroCorteTipo.ID, corteTipo.getId()));
		CorteTipo corteTipo2 = (CorteTipo) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(
				filtroCorteTipo, CorteTipo.class.getName()));
		
		if (corteTipo2 == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}
		
		if (corteTipo.getUltimaAlteracao().after(corteTipo2.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		
		// [FS0005] Verificar tipo corte
		filtroCorteTipo = new FiltroCorteTipo();
		filtroCorteTipo.adicionarParametro(new ParametroSimples(
				FiltroCorteTipo.DESCRICAO, corteTipo.getDescricao()));
		filtroCorteTipo.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroCorteTipo.ID, corteTipo.getId()));
		
		Collection colecaoCorteTipo = getControladorUtil().pesquisar(filtroCorteTipo, CorteTipo.class.getName());

		if (colecaoCorteTipo != null && !colecaoCorteTipo.isEmpty()) {
			throw new ControladorException(
					"atencao.descricao_ja_existente_tipo_corte", null, corteTipo.getDescricao());
		}
		
		// ------------ INICIO REGISTRAR TRANSação----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_TIPO_CORTE_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_TIPO_CORTE_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		corteTipo.setOperacaoEfetuada(operacaoEfetuada);
		corteTipo.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(corteTipo);
		// ------------ FIM REGISTRAR TRANSação----------------------------

		corteTipo.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(corteTipo);
	}
	
	/**
	 * [UC1103] - Manter Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @data: 07/12/2010
	 * 
	 * @param ids
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void removerCorteTipo(String[] ids, Usuario usuarioLogado) throws ControladorException {
		
		// ------------ REGISTRAR TRANSação ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_TIPO_CORTE_REMOVER);
		
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		
		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSação ----------------
		
		this.getControladorUtil().remover(ids, CorteTipo.class.getName(),
				operacaoEfetuada, colecaoUsuarios);
		
	}

	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 27/12/2010
 	 * 
 	 * @param idRepavimentadora, idPavimento, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return boolean
	 */
	public boolean verificaRemoverCustoPavimentoPorRepavimentadora(Integer idRepavimentadora,
			Integer idPavimento, Integer indicadorPavimento)throws ControladorException{
		
		try {
			return this.repositorioAtendimentoPublico
					.verificaRemoverCustoPavimentoPorRepavimentadora(idRepavimentadora, idPavimento, indicadorPavimento);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 28/12/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return void
	 */
	public void verificaAtualizarCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, Date dataFinal, 
			Integer indicadorPavimento, Integer tipo )throws ControladorException{
		
		Integer valor = null;
		
		Date dtFinal = dataFinal;
		
		if(dataFinal == null){
			
			dataFinal = Util.converteStringParaDate("31/12/9999");
		}
		
		try {
			valor = this.repositorioAtendimentoPublico
					.verificaAtualizarCustoPavimentoPorRepavimentadora(idAtualizacao, 
							idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento, tipo);
			
			if(valor != 0){
				switch (valor) {
					case 1:
						
						if(dtFinal == null && tipo == 1){
							
							throw new ControladorException(
							"atencao.existe.custo_pavimento_por_repavimentadora_rua.vigente_fim_aberta");
						}
						
						throw new ControladorException(
						"atencao.existe.custo_pavimento_por_repavimentadora_rua.vigente");
					case 2:
						throw new ControladorException(
						"atencao.existe.custo_pavimento_por_repavimentadora_rua.vigente");
					case 3:
						if(dtFinal == null && tipo == 1){
							
							throw new ControladorException(
							"atencao.existe.custo_pavimento_por_repavimentadora_calcada.vigente_fim_aberta");
						}
						
						throw new ControladorException(
						"atencao.existe.custo_pavimento_por_repavimentadora_calcada.vigente");
					case 4:
					
						throw new ControladorException(
						"atencao.existe.custo_pavimento_por_repavimentadora_calcada.vigente");
				}
			}
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
	 * 
	 * 		[FS0010] Verificar se existem dias sem valor
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 11/01/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return Integer
	 */
	public Integer verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, Date dataFinal, 
			Integer indicadorPavimento, Integer tipo) throws ControladorException{
		
		Integer valor = 0;
		
		Date dtFinal = dataFinal;
		
		try {
					
			if(dataFinal != null){
				dataFinal = Util.adicionarNumeroDiasDeUmaData(dataFinal, 1);
			}else{
				dataFinal = Util.converteStringParaDate("31/12/9999");
			}
			
			dataInicio = Util.adicionarNumeroDiasDeUmaData(dataInicio, -1);
			
			valor = this.repositorioAtendimentoPublico
					.verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(idAtualizacao, 
							idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento, tipo);
			
			if(tipo == 2 && dtFinal == null){
				
				valor = this.repositorioAtendimentoPublico
						.verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(idAtualizacao, 
							idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento, new Integer("3"));
			}
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return valor;
	}
	
	/**
     * [UC0412] Manter Tipo de Serviço
     * 
     * @author Vivianne Sousa
     * @created 07/01/2011
     */
    public void removerServicoTipoBoletim(Integer idServicoTipo)throws ControladorException {
		
		try {
			
			this.repositorioAtendimentoPublico.removerServicoTipoBoletim(idServicoTipo);
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
   

	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) throws ControladorException{
	
		Collection colecaoRetorno = new ArrayList();
		Collection<Integer> idsOS = new ArrayList();
		Collection colecaoDebitosOS = null;
		Collection colecaoDebitosOSHistorico = null;
		Collection<OrdemServico> colecaoOS = new ArrayList();
		Collection colecaoIds = null;
		
		RelatorioReligacaoClientesInadiplentesHelper helper = null;
		
		try {			
			
			Collection colecaoIdsOS = 
				this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentesOS(relatorioHelper);
			
			Iterator iterator = colecaoIdsOS.iterator();
			
			while (iterator.hasNext()) {
			
				colecaoIds = null;
				
				Object[] objeto = null;
				Integer idUsuario = null;
				
				if(relatorioHelper.getEscolhaRelatorio() == 3 
						|| relatorioHelper.getEscolhaRelatorio() == 4){
					
					idUsuario = (Integer) iterator.next();
				}else{
					objeto = (Object[]) iterator.next();
				}
				
				// Todas as Ocorrencias no Período
				if(relatorioHelper.getEscolhaRelatorio() == 1){
					
					// [0]- OS
					// [1]- Imovel
					// [2]- Data Encerramento
					colecaoDebitosOS = this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentes(
							(Integer)objeto[0], (Integer)objeto[1], (Date)objeto[2], new Integer("1"));
					
					if(colecaoDebitosOS == null || colecaoDebitosOS.isEmpty()){
						
						colecaoDebitosOSHistorico = this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentes(
								(Integer)objeto[0], (Integer)objeto[1], (Date)objeto[2], new Integer("2"));
					}
				}
				else if(relatorioHelper.getEscolhaRelatorio() == 3 
						|| relatorioHelper.getEscolhaRelatorio() == 4){
					
					
					// [0]- Usuario
					// obtém  as OS's
					colecaoIds = this.repositorioAtendimentoPublico.pesquisarRecorrenciaPorUsuarioQueAbriuOuEncerrouOS(
							idUsuario, relatorioHelper);
					
					Collection<Integer> idsOSUsuario = new ArrayList();
					if(!Util.isVazioOrNulo(colecaoIds)){
						
						Iterator itImovel = colecaoIds.iterator();
						while(itImovel.hasNext()){
							
							Object[] obj = (Object[]) itImovel.next();
							
							// [0]- OS
							// [1]- Imovel
							// [1]- Data Encerramento
							
							// Verifica se existem debitos.
							colecaoDebitosOS = this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentes(
									null, (Integer)obj[1], (Date)obj[2], new Integer("1"));
							
							if(colecaoDebitosOS == null || colecaoDebitosOS.isEmpty()){
								
								colecaoDebitosOSHistorico = this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentes(
										null, (Integer)obj[1], (Date)obj[2], new Integer("2"));
							}
							
							if((colecaoDebitosOS != null && !colecaoDebitosOS.isEmpty()) || 
									(colecaoDebitosOSHistorico != null && !colecaoDebitosOSHistorico.isEmpty())){
								
								idsOSUsuario.add((Integer)obj[0]);
							}
						}
					}
					
					if(!Util.isVazioOrNulo(idsOSUsuario) && idsOSUsuario.size() > 1){
						idsOS.addAll(idsOSUsuario);
					}
				}
				else if(relatorioHelper.getEscolhaRelatorio() == 2 
						|| relatorioHelper.getEscolhaRelatorio() == 5){
					
					// [0]- Imovel
					// [1]- Quantidade
					
					//obtém as datas de encerramento as os's passando o imóvel.
					Collection colecaoResultado = 
						this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentesDatasOS(
							relatorioHelper, (Integer)objeto[0]);
					
					if(!Util.isVazioOrNulo(colecaoResultado)){
						
						Iterator itResultado = colecaoResultado.iterator();
						Collection<Integer> colecaoIdsOSTemp = new ArrayList();
						while (itResultado.hasNext()) {
						
							Object[] resultado = (Object[]) itResultado.next();
							
							Date data = (Date) resultado[0];
							Integer idOs = (Integer) resultado[1];
							
							// Verifica se existem debitos.
							colecaoDebitosOS = this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentes(
									null, (Integer)objeto[0], data, new Integer("1"));
							
							if(colecaoDebitosOS == null || colecaoDebitosOS.isEmpty()){
								
								colecaoDebitosOSHistorico = this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentes(
										null, (Integer)objeto[0], data, new Integer("2"));
							}
							
							if((colecaoDebitosOS != null && !colecaoDebitosOS.isEmpty()) || 
									(colecaoDebitosOSHistorico != null && !colecaoDebitosOSHistorico.isEmpty())){
								
								// recupera o id das OS do imóvel.
								colecaoIdsOSTemp.add(idOs);
							}
						}
						
						if(colecaoIdsOSTemp != null && 
							!colecaoIdsOSTemp.isEmpty() && 
							colecaoIdsOSTemp.size() > 1){
							
							idsOS.addAll(colecaoIdsOSTemp);
						}

					}
				}
				
				if(relatorioHelper.getEscolhaRelatorio() == 1 && ((colecaoDebitosOS != null && !colecaoDebitosOS.isEmpty()) || 
						(colecaoDebitosOSHistorico != null && !colecaoDebitosOSHistorico.isEmpty()))){
					
					idsOS.add((Integer)objeto[0]);
				}
				else if((relatorioHelper.getEscolhaRelatorio() == 2 || relatorioHelper.getEscolhaRelatorio() == 5) 
						&& (colecaoIds != null && colecaoIds.size() > 1)){
					
					idsOS.addAll(colecaoIds);
				}
			}
			
			if (idsOS != null && !idsOS.isEmpty()) {
				
				// pesquisa as ordens de Serviço para o relatório.
				colecaoOS = this.repositorioAtendimentoPublico
					.pesquisarRelatorioReligacaoClientesInadiplentes( idsOS);
			}
			
			if(colecaoOS != null && !colecaoOS.isEmpty()){
				
				for (OrdemServico os : colecaoOS) {
					
					// Matrícula
					String matricula = Util.retornaMatriculaImovelFormatada( os.getImovel().getId());
					
					// Endereço
					String endereco = getControladorEndereco().pesquisarEnderecoFormatado(os.getImovel().getId());
					
					// Perfil
					String perfil = os.getImovel().getImovelPerfil().getId().toString();
					
					FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
					filtroImovelPerfil.adicionarParametro(new ParametroSimples(
							FiltroImovelPerfil.ID, os.getImovel().getImovelPerfil().getId()));
					
					Collection collImovelPerfil = getControladorUtil().pesquisar(
							filtroImovelPerfil, ImovelPerfil.class.getName());
					
					ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(collImovelPerfil);
					perfil += imovelPerfil != null ? "- " + imovelPerfil.getDescricao() : "";
					
					// Número O.S
					String numeroOS = os.getId().toString();
					
					// Obter os usuários
					FiltroOrdemServicoUnidade filtroOrdemServicoUnidade = new FiltroOrdemServicoUnidade();
					filtroOrdemServicoUnidade.adicionarCaminhoParaCarregamentoEntidade(
							"atendimentoRelacaoTipo");
					filtroOrdemServicoUnidade.adicionarCaminhoParaCarregamentoEntidade(
							"usuario");
					filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(
							FiltroOrdemServicoUnidade.ORDEM_SERVICO_ID, os.getId()));
					
					Collection collOrdemServicoUnidades = getControladorUtil().pesquisar(
							filtroOrdemServicoUnidade, OrdemServicoUnidade.class.getName());
					
					Iterator it = collOrdemServicoUnidades.iterator();
					OrdemServicoUnidade unidade = null;

					// Usuário Abertura
					String usuarioAberturaOS = "";
					String nomeUsuarioAberturaOS = "";
					
					// Usuário Encerramento
					String usuarioEncerramentoOS = "";
					String nomeUsuarioEncerramentoOS = "";
					
					while (it.hasNext()){
						unidade = (OrdemServicoUnidade) it.next();
						
						if(unidade.getAtendimentoRelacaoTipo().getId() == 1){
							
							usuarioAberturaOS = unidade.getUsuario().getLogin();
							nomeUsuarioAberturaOS = "- " + unidade.getUsuario().getNomeUsuario();
						}
						
						if(unidade.getAtendimentoRelacaoTipo().getId() == 3){
							
							usuarioEncerramentoOS = unidade.getUsuario().getLogin();
							nomeUsuarioEncerramentoOS = "- " + unidade.getUsuario().getNomeUsuario();
						}
					}
					
					// Data Encerramento
					String dataEncerramento = Util.formatarData( os.getDataEncerramento());
					
					// Obtendo os débitos do imovel
					Integer tipoImovel = new Integer(1);
					Integer indicadorPagamento = new Integer(1);
					Integer indicadorConta = new Integer(1);
					Integer indicadorDebito = new Integer(1);
					Integer indicadorCredito = new Integer(1);
					Integer indicadorNotas = new Integer(1);
					Integer indicadorGuias = new Integer(1);
					Integer indicadorAtualizar = new Integer(1);
					
					String referenciaInicial = "01/0001";
					String referenciaFinal = "12/9999";
					String dataVencimentoInicial = "01/01/0001";
					String dataVencimentoFinal = "31/12/9999";

					// Para auxiliar na formatação de uma data
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String mesInicial = referenciaInicial.substring(0, 2);
					String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
					String anoMesInicial = anoInicial + mesInicial;
					String mesFinal = referenciaFinal.substring(0, 2);
					String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
					String anoMesFinal = anoFinal + mesFinal;

					Date dataVencimentoDebitoI;
					Date dataVencimentoDebitoF;

					try {
						dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
					} catch (ParseException ex) {
						dataVencimentoDebitoI = null;
					}
					try {
						dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
					} catch (ParseException ex) {
						dataVencimentoDebitoF = null;
					}
					
					ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = getControladorCobranca()
							.obterDebitoImovelOuCliente(tipoImovel.intValue(),
								os.getImovel().getId().toString().trim(), null, null,
								anoMesInicial, anoMesFinal,
								dataVencimentoDebitoI,
								dataVencimentoDebitoF, 
								indicadorPagamento.intValue(), 
								indicadorConta.intValue(), 
								indicadorDebito.intValue(), 
								indicadorCredito.intValue(), 
								indicadorNotas.intValue(), 
								indicadorGuias.intValue(), 
								indicadorAtualizar.intValue(), null);
					
					// Valor Conta
					Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();
					
					BigDecimal valorConta = new BigDecimal("0.00");
					ContaValoresHelper dadosConta = null;
					
					if (colecaoContaValores != null	&& !colecaoContaValores.isEmpty()) {
						java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
						// percorre a colecao de conta somando o valor para obter um valor total
						while (colecaoContaValoresIterator.hasNext()) {

							dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
							valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
						}
					}
					
					// Débito a Cobrar
					Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();

					BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
					DebitoACobrar dadosDebito = null;

					if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
						java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
						
						// percorre a colecao de debito a cobrar somando o valor para obter um valor total
						while (colecaoDebitoACobrarIterator.hasNext()) {

							dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
							valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
						}
					}
					
					// Guia de Pagamento
					BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
					GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;
					
					Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();
					
					if (colecaoGuiaPagamentoValores != null	&& !colecaoGuiaPagamentoValores.isEmpty()) {
						java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
						// percorre a colecao de guia de pagamento somando o valor para obter um valor total
						while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

							dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
							valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
						}
					}
					
					// Crédito a Realizar
					BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
					CreditoARealizar dadosCredito = null;
					
					Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();

					if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
						java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
						// percorre a colecao de credito a realizar somando o valor para obter um valor total
						while (colecaoCreditoARealizarIterator.hasNext()) {

							dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
							valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
						}
					}
					
					// Soma o valor total dos debitos e subtrai dos creditos
					BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
					valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
					valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);
					
					// Valor Débito
					String valorDebito = Util.formatarMoedaReal(valorTotalSemAcrescimo);
					
					if(relatorioHelper.getEscolhaRelatorio() == 1){
						
						helper = new RelatorioReligacaoClientesInadiplentesHelper(
								matricula,
								endereco,
								perfil,
								numeroOS,
								usuarioAberturaOS,
								nomeUsuarioAberturaOS,
								dataEncerramento,
								usuarioEncerramentoOS,
								nomeUsuarioEncerramentoOS,
								valorDebito
						);
					}
					else if(relatorioHelper.getEscolhaRelatorio() == 2){
						
						// Nome Cliente
						String nomeCliente = "";
						String cpfCnpj = "";
						String tipoRelacao = "";
						
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(
								FiltroClienteImovel.CLIENTE);
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(
								FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
						filtroClienteImovel.adicionarParametro(new ParametroSimples(
								FiltroClienteImovel.IMOVEL_ID, os.getImovel().getId()));
						filtroClienteImovel.setCampoOrderBy(FiltroClienteImovel.CLIENTE_NOME);
						
						Collection collClientesImoveis = getControladorUtil().pesquisar(
								filtroClienteImovel, ClienteImovel.class.getName());
						
						Iterator iClientesImoveis = collClientesImoveis.iterator();
						ClienteImovel clienteImovel = null;

						while (iClientesImoveis.hasNext()){
							clienteImovel = (ClienteImovel) iClientesImoveis.next();
							
							if(clienteImovel.getDataFimRelacao() == null){
								
								nomeCliente = clienteImovel.getCliente().getNome();
								cpfCnpj = Util.formatarCpf( clienteImovel.getCliente().getCpf());
								
								if(cpfCnpj == null || cpfCnpj.equals("")){
									cpfCnpj = Util.formatarCnpj( clienteImovel.getCliente().getCnpj());
								}
								
								tipoRelacao = clienteImovel.getClienteRelacaoTipo().getDescricao();
								
								helper = new RelatorioReligacaoClientesInadiplentesHelper(
										nomeCliente,
										cpfCnpj, 
										tipoRelacao,
										matricula,
										endereco,
										perfil,
										numeroOS,
										usuarioAberturaOS,
										nomeUsuarioAberturaOS,
										dataEncerramento,
										usuarioEncerramentoOS,
										nomeUsuarioEncerramentoOS,
										valorDebito
								);
								
								colecaoRetorno.add(helper);
								helper = null;
							}
						}
					}
					else if(relatorioHelper.getEscolhaRelatorio() == 3 || 
							relatorioHelper.getEscolhaRelatorio() == 4 || 
							relatorioHelper.getEscolhaRelatorio() == 5 ){
						
						// Nome Cliente
						String nomeCliente = "";
						String cpfCnpj = "";
						String tipoRelacao = "";
						
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(
								FiltroClienteImovel.CLIENTE);
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(
								FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
						filtroClienteImovel.adicionarParametro(new ParametroSimples(
								FiltroClienteImovel.IMOVEL_ID, os.getImovel().getId()));
						filtroClienteImovel.setCampoOrderBy(FiltroClienteImovel.CLIENTE_NOME);
						
						Collection collClientesImoveis = getControladorUtil().pesquisar(
								filtroClienteImovel, ClienteImovel.class.getName());
						
						Iterator iClientesImoveis = collClientesImoveis.iterator();
						ClienteImovel clienteImovel = null;

						while (iClientesImoveis.hasNext()){
							clienteImovel = (ClienteImovel) iClientesImoveis.next();
							
							if(clienteImovel.getDataFimRelacao() == null && 
									clienteImovel.getClienteRelacaoTipo().getId().compareTo(ClienteRelacaoTipo.USUARIO.intValue()) == 0){
								
								nomeCliente = clienteImovel.getCliente().getNome();
								cpfCnpj = Util.formatarCpf( clienteImovel.getCliente().getCpf());
								
								if(cpfCnpj == null || cpfCnpj.equals("")){
									cpfCnpj = Util.formatarCnpj( clienteImovel.getCliente().getCnpj());
								}
								
								tipoRelacao = clienteImovel.getClienteRelacaoTipo().getDescricao();
								
								helper = new RelatorioReligacaoClientesInadiplentesHelper(
										nomeCliente,
										cpfCnpj, 
										tipoRelacao,
										matricula,
										endereco,
										perfil,
										numeroOS,
										usuarioAberturaOS,
										nomeUsuarioAberturaOS,
										dataEncerramento,
										usuarioEncerramentoOS,
										nomeUsuarioEncerramentoOS,
										valorDebito
								);
								
								colecaoRetorno.add(helper);
								helper = null;
							}
						}
					}
					
					if(helper != null){
						
						colecaoRetorno.add(helper);
					}
				}
			}
			
        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }
        
        return colecaoRetorno;
	}
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer countRelatorioReligacaoClientesInadiplentes(
			FiltrarRelatorioReligacaoClientesInadiplentesHelper helper) throws ControladorException {
		
		Integer quantidade = 0;
		try {
			
			Collection colecaoIdsOS = 
				this.repositorioAtendimentoPublico.pesquisarRelatorioReligacaoClientesInadiplentesOS(helper);
		
			if(!Util.isVazioOrNulo(colecaoIdsOS)){
				
				quantidade = colecaoIdsOS.size();
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return quantidade;
	}
	
	/**
     * Obtém a coleção de perfis de tipo de serviço para OS.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */
	
	public Collection obterColecaoTipoOSgerada() throws ControladorException{
		Collection retornoSql = null;
		
		try{
			retornoSql = this.repositorioAtendimentoPublico.obterColecaoTipoOSgerada();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		Collection retorno = new ArrayList();
		ServicoTipo st = null;
		Iterator it = retornoSql.iterator();
		Object[] obj = null;
		
		while(it.hasNext()){
			obj = (Object[]) it.next();
			st = new ServicoTipo();
			
			st.setId((Integer)obj[0]);
			st.setDescricao((String)obj[1]);
			
			retorno.add(st);
			
		}
		
		return retorno;
	}
	
	
	/**
     * Obtém a coleção de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ControladorException
     */
	
	public Collection obterColecaoImovelOSCobrancaResultado(String[] categoriaImovel, String[] perfilImovel, String[] gerenciaRegional, String[] unidadeNegocio, 
			String idLocalidadeInicial, String idLocalidadeFinal, String idSetorComercialInicial, String idSetorComercialFinal,
			String idQuadraInicial, String idQuadraFinal, String tipoServico,String comando) throws ControladorException{
		
		Collection retornoQuery = null;
		
		try{
			retornoQuery = this.repositorioAtendimentoPublico.obterColecaoImovelOSCobrancaResultado(
																				categoriaImovel,
																				perfilImovel,
																				gerenciaRegional,
																				unidadeNegocio,
																				idLocalidadeInicial,
																				idLocalidadeFinal,
																				idSetorComercialInicial,
																				idSetorComercialFinal,
																				idQuadraInicial,
																				idQuadraFinal,
																				tipoServico,
																				comando
																			);
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		Collection retorno = new ArrayList();
		HashMap map = null;
		Iterator it = retornoQuery.iterator();
		while(it.hasNext()){
			Object[] objItems = (Object[])it.next();
			map = new HashMap();
			map.put("imovel",(Integer)objItems[0]);
			map.put("idGerenciaRegional",(Integer)objItems[1]);
			map.put("descGerenciaRegional",(String)objItems[2]);
			map.put("idUnidadeNegocio",(Integer)objItems[3]);
			map.put("descUnidadeNegocio",(String)objItems[4]);
			retorno.add(map);
			
		}
		
		return retorno;
	}
	
	/**
	 * [UC1177] Gerar Relatório de Ordens de Serviço por Situação
	 * 
	 * O segundo parâmetro (boletimGerado) é um booleano que
	 * indica se para um dado grupo de cobrança e um mês referencia
	 * foi gerado um boletim de medição.
	 * 
	 * @author Diogo Peixoto
	 * @date 09/06/2011
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 *
	 * @return RelatorioOSSituacaoHelper
	 * @throws ControladorException
	 */
	public RelatorioOSSituacaoHelper filtrarRelatorioOSSituacao(FiltrarRelatorioOSSituacaoHelper filtro)
		throws ControladorException{
		Collection<Object[]> colecaoOSSituacao = new ArrayList();
		Collection<RelatorioOSSituacaoBean> beans = new ArrayList();
		RelatorioOSSituacaoBean bean = null;
		RelatorioOSSituacaoHelper helper = new RelatorioOSSituacaoHelper();
		
		try {
			
			FiltroCobrancaBoletimMedicao filtroBoletim = new FiltroCobrancaBoletimMedicao();
			filtroBoletim.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.COBRANCA_GRUPO_ID, filtro.getIdGrupoCobranca()));
			filtroBoletim.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.ANO_MES_REFERENCIA, filtro.getDataReferencia()));
			Collection<CobrancaBoletimMedicao> medicoes = Fachada.getInstancia().pesquisar(filtroBoletim, CobrancaBoletimMedicao.class.getName());
			
			//Caso o grupo de cobrança com ano/mês já tenha sido gerado boletim 
			if(!Util.isVazioOrNulo(medicoes)){
				colecaoOSSituacao = this.repositorioAtendimentoPublico.filtrarRelatorioOSSituacao(filtro, true);
			//Caso contrário	
			}else{
				colecaoOSSituacao = this.repositorioAtendimentoPublico.filtrarRelatorioOSSituacao(filtro, false);
			}
			
			if (!Util.isVazioOrNulo(colecaoOSSituacao)){
				Iterator iteOSSituacao = colecaoOSSituacao.iterator();
				Object[] linha = null;
				while (iteOSSituacao.hasNext() ){
					
					linha = (Object[]) iteOSSituacao.next();

					if(filtro.getOpcaoRelatorio().equals("1")){
						String strIdOS = null;
						if(linha[0] != null){
							strIdOS = String.valueOf((Integer) linha[0]);
						}
						
						String strMatriculaIMovel = null;
						String endImovel = null;
						if(linha[1] != null){
							Integer idImovel = (Integer) linha[1];
							strMatriculaIMovel = Util.retornaMatriculaImovelFormatada(idImovel);
							endImovel = Fachada.getInstancia().obterEnderecoAbreviadoImovel(idImovel); 
						}
						
						String tipoServico = null;
						if(linha[2] != null){
							tipoServico = (String) linha[2];
						}
						
						Date dateEncerramento = null;
						if(linha[3] != null){
							dateEncerramento = (Date) linha[3];
						}
						
						String naoCobrada = "";
						if(linha[4] != null){
							naoCobrada = (String) linha[4];
						}
					
						BigDecimal valorConsumoFraudado = null;
						if(linha[5] != null){
							valorConsumoFraudado = (BigDecimal) linha[5];
						}
						
						BigDecimal valorMulta = null;
						if(linha[6] != null){
							valorMulta = (BigDecimal) linha[6];
						}
						
						String motivoEncerramento = null;
						if(linha[7] != null){
							motivoEncerramento = (String) linha[7];
						}

						String retornoFiscalizacao = null;
						if(linha[8] != null){
							retornoFiscalizacao = (String) linha[8];
						}

						String parecerEncerramento = null;
						if(linha[9] != null){
							parecerEncerramento = (String) linha[9];
						}
						
						String situacaoOS = null;
						if(linha[10] != null){
							situacaoOS = (String) linha[10];
							/*
							 * Se a situação da ordem de serviço for igual a TODAS, o resultset da situação os
							 * será números de acordo com o seguinte critério:
							 * 1.Fiscalizadas
							 * 2.Descontadas
							 * 3.Executadas
							 * 4.Justificadas
							 * 5.Penalizadas por Fiscalização
							 * 6.Penalizadas por Decurso de Prazo
							 * 
							 */
							if(filtro.getSituacaoOS().equals("8") || filtro.getSituacaoOS().equals("13")){
								Integer numeroSituacaoOS = Integer.valueOf(situacaoOS);
								switch (numeroSituacaoOS) {
								case 1:
									situacaoOS = "Fiscalizadas";
									break;

								case 2:
									situacaoOS = "Descontadas";
									break;
									
								case 3:
									situacaoOS = "Executadas";
									break;
								case 4:
									situacaoOS = "Justificadas";
									break;
									
								case 5:
									situacaoOS = "Penalizadas por Fiscalização";
									break;
									
								case 6:
									situacaoOS = "Penalizadas por Decurso de Prazo";
									break;
									
								case 7:
									situacaoOS = "Fiscalizadas";
									break;
								
								case 8:
									situacaoOS = "Encerradas com Execução";
									break;
								
								case 9:
									situacaoOS = "Pendentes";
									break;
								
								case 10:
									situacaoOS = "Encerrados por Decurso de Prazo";
									break;
								}
							}
						}
						
						bean = new RelatorioOSSituacaoBean(strIdOS, strMatriculaIMovel, endImovel, tipoServico, dateEncerramento, naoCobrada, valorConsumoFraudado,
								valorMulta, motivoEncerramento, retornoFiscalizacao, parecerEncerramento, situacaoOS);
						beans.add(bean);
					}else if(filtro.getOpcaoRelatorio().equals("2")){
						
						String tipoServico = (String) linha[0];
						String motivoEncerramento = (String) linha[1];
						String retornoFiscalizacao = (String) linha[2];
						String situacaoOS = (String) linha[3];
						/*
						 * Se a situação da ordem de serviço for igual a TODAS, o resultset da situação os
						 * será números de acordo com o seguinte critério:
						 * 1.Fiscalizadas
						 * 2.Descontadas
						 * 3.Executadas
						 * 4.Justificadas
						 * 5.Penalizadas por Fiscalização
						 * 6.Penalizadas por Decurso de Prazo
						 * 
						 */
						if(filtro.getSituacaoOS().equals("8") || filtro.getSituacaoOS().equals("13")){
							Integer numeroSituacaoOS = Integer.valueOf(situacaoOS);
							switch (numeroSituacaoOS) {
							case 1:
								situacaoOS = "Fiscalizadas";
								break;

							case 2:
								situacaoOS = "Descontadas";
								break;
								
							case 3:
								situacaoOS = "Executadas";
								break;
							case 4:
								situacaoOS = "Justificadas";
								break;
								
							case 5:
								situacaoOS = "Penalizadas por Fiscalização";
								break;
								
							case 6:
								situacaoOS = "Penalizadas por Decurso de Prazo";
								break;
							
							case 7:
								situacaoOS = "Fiscalizadas";
								break;
							
							case 8:
								situacaoOS = "Encerradas com Execução";
								break;
							
							case 9:
								situacaoOS = "Pendentes";
								break;
							
							case 10:
								situacaoOS = "Encerrados por Decurso de Prazo";
								break;
							}
						}
						
						Integer quantidade = 0;
						if(linha[4] != null){
							quantidade = (Integer) linha[4];
						}
						
						bean = new RelatorioOSSituacaoBean(tipoServico, motivoEncerramento, retornoFiscalizacao, situacaoOS, quantidade);
						beans.add(bean);
					}
				}		
				
				//Configurando os parâmetros da consulta
				GerenciaRegional gerenciaRegional = filtro.getGerenciaRegional();
				if(gerenciaRegional != null){
					helper.setGerenciaRegional(gerenciaRegional.getId() + " - " + gerenciaRegional.getNome());
				}
				
				SetorComercial setorComercial = filtro.getSetorComercial();
				if(setorComercial != null){
					helper.setSetorComercial(setorComercial.getId() + " - " + setorComercial.getDescricao());
				}else{
					helper.setSetorComercial(null);
				}
				
				UnidadeNegocio unidadeNegocio = filtro.getUnidadeNegocio();
				if(unidadeNegocio != null){
					helper.setUnidadeNegocio(unidadeNegocio.getId() + " - " + unidadeNegocio.getNome());
				}
				
				Quadra quadra = filtro.getQuadra();
				if(quadra != null){
					helper.setQuadra(quadra.getId() + " - " + quadra.getDescricao());
				}else{
					helper.setQuadra(null);
				}
				
				Localidade localidadePolo = filtro.getEloPolo();
				if(localidadePolo != null){
					helper.setLocalidadeEloPolo(localidadePolo.getId() + " - " + localidadePolo.getDescricao());
				}else{
					helper.setLocalidadeEloPolo(null);
				}
				
				Localidade localidade = filtro.getLocalidade();
				if(localidade != null){
					helper.setLocalidade(localidade.getId() + " - " + localidade.getDescricao());
				}else{
					helper.setLocalidade(null);
				}
				
				ServicoTipo servicoTipo = filtro.getServicoTipo();
				if(servicoTipo != null){
					helper.setTipoServico(servicoTipo.getDescricao());
				}else{
					helper.setTipoServico(null);
				}
				helper.setBeans(beans);
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return helper;
	}
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * Método utilizado para auxiliar na inserção dos dados no Relatório
	 * de Acompanhamento dos Boletins de Medição. Recebe como parâmetro
	 * o um filtro e retorno o Bean do relatório.
	 * 
	 * @author Diogo Peixoto
	 * @date 17/06/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return RelatorioAcompanhamentoBoletimMedicaoHelper
	 */
	public RelatorioAcompanhamentoBoletimMedicaoHelper filtrarRelatorioAcompanhamentoBoletimMedicao(
			FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ControladorException{
		
		Collection<Object[]> colecaoBoletinsMedicao = new ArrayList();
		Collection<Object[]> colecaoBoletinsMedicaoAcumulado = new ArrayList();
		
		Collection<RelatorioAcompanhamentoBoletimMedicaoBean> beans = new ArrayList();
		RelatorioAcompanhamentoBoletimMedicaoBean bean = null;
		RelatorioAcompanhamentoBoletimMedicaoHelper helper = null;
		
		BigDecimal valorMedidoPeriodoTotal = new BigDecimal("0.00");
		
		try {
			boolean relatorioDefinitivo = this.repositorioAtendimentoPublico.gruposIniciaodsJaForamEncerrados(filtro);
			colecaoBoletinsMedicao = this.repositorioAtendimentoPublico.filtrarRelatorioAcompanhamentoBoletimMedicao(filtro, relatorioDefinitivo);
			colecaoBoletinsMedicaoAcumulado = this.repositorioAtendimentoPublico.filtrarRelatorioAcompanhamentoBoletimMedicaoAcumuladas(filtro);
			
			if (!Util.isVazioOrNulo(colecaoBoletinsMedicao)){
				Iterator iteBoletinsMedicao = colecaoBoletinsMedicao.iterator();
				Iterator iteBoletinsMedicaoAcumulado = colecaoBoletinsMedicaoAcumulado.iterator();
				
				Object[] linha = null;
				Integer idItemServico;
				String descricaoServico;
				BigDecimal quantidadeOrcada;
				BigDecimal valorUnitario;
				Integer quantidadeItem;
				BigDecimal valorItem;
				String unidadeItem;
				BigDecimal valorOrcado;
				BigDecimal valorMedidoPeriodo = new BigDecimal("0.00");
				
				Object[] linhaAcumulado = null;
				Integer quantidadeItemAcumulada = 0;
				BigDecimal valorAcumuladoPeriodo = new BigDecimal("0.00");
				
				while (iteBoletinsMedicao.hasNext() ){
					linhaAcumulado = null;
					if(iteBoletinsMedicaoAcumulado.hasNext()){
						linhaAcumulado = (Object[]) iteBoletinsMedicaoAcumulado.next();
						if(linhaAcumulado[2] != null){
							quantidadeItemAcumulada = (Integer) linhaAcumulado[2]; 
						}
						if(linhaAcumulado[3] != null){
							valorAcumuladoPeriodo = (BigDecimal) linhaAcumulado[3];
						}
					}
					linha = (Object[]) iteBoletinsMedicao.next();

					idItemServico = null;
					if(linha[0] != null){
						idItemServico = (Integer) linha[0];
					}

					descricaoServico = null;
					if(linha[1] != null){
						descricaoServico = (String) linha[1];
					}

					quantidadeOrcada = new BigDecimal("0.00");;
					if(linha[2] != null){
						quantidadeOrcada = (BigDecimal) linha[2];
					}

					valorUnitario = new BigDecimal("0.00");;
					if(linha[3] != null){
						valorUnitario = (BigDecimal) linha[3];
					}

					quantidadeItem = 0;
					if(linha[4] != null){
						quantidadeItem = (Integer) linha[4];
					}

					valorItem = new BigDecimal("0.00");
					if(linha[5] != null){
						valorItem = (BigDecimal) linha[5];
					}

					unidadeItem = null;
					if(linha[6] != null){
						unidadeItem = (String) linha[6];
					}

					valorOrcado = new BigDecimal("0.00");
					if(linha[7] != null){
						valorOrcado = (BigDecimal) linha[7];
					}

					if(linha[8] != null){
						valorMedidoPeriodo = (BigDecimal) linha[8];
					}
					
					//Acumular para fazer o cálculo da penalidade de contrato de não execução.
					valorMedidoPeriodoTotal = valorMedidoPeriodoTotal.add(valorMedidoPeriodo);
					
					bean = new RelatorioAcompanhamentoBoletimMedicaoBean(idItemServico, descricaoServico, quantidadeOrcada, 
							valorUnitario, quantidadeItem, valorItem, unidadeItem, valorOrcado, valorMedidoPeriodo, quantidadeItemAcumulada,
							valorAcumuladoPeriodo);
					beans.add(bean);
				}
				
				BigDecimal taxaSucesso = this.repositorioAtendimentoPublico.pesquisarTaxaSucessoBoletimMedicao(filtro);
				
				String tipoRelatorio = null;
				BigDecimal penalidadeOS = new BigDecimal("0.00");
				BigDecimal penalidadeFiscalizacao = new BigDecimal("0.00");
				
				Collection<BigDecimal> penalidades = this.repositorioAtendimentoPublico.filtrarRelatorioAcompanhamentoBoletimMedicaoPenalidades(
						filtro, relatorioDefinitivo);
				Iterator<BigDecimal> iterator = penalidades.iterator();
				/*
				 * Calculas as penalidades de ordem de serviço e de fiscalização
				 * O método do repositório sempre irá retornar no mínimo dois valores.
				 */
				if(iterator.hasNext()){
					penalidadeOS = iterator.next();
					penalidadeFiscalizacao = iterator.next();
				}
				
				/*
				 * Caso o relatório seja definitivo o método do repositório irá retornar duas penalidades a mais:
				 *  penalidade pela não realização de serviços e penalidade por corte ou supressão indevida.
				 *  
				 */
				if(relatorioDefinitivo){
					tipoRelatorio = "DEFINITIVO";
					BigDecimal penalidadeNaoRealizacao = iterator.next();
					BigDecimal penalidadeCorteSupressao = iterator.next();
					BigDecimal penalidadeContratoNaoExecucao = new BigDecimal("0.00");
					
					double quantidadeOSExecutadas = this.repositorioAtendimentoPublico.pesquisarQuantidadeOSExecutadas(filtro);
					double quantidadeOSPenalizadas = this.repositorioAtendimentoPublico.pesquisarQuantidadeOSPenalizadas(filtro);
					double denominador = quantidadeOSExecutadas + quantidadeOSPenalizadas;
					
					if(denominador != 0){
						BigDecimal percentual = new BigDecimal(((100 * quantidadeOSPenalizadas) / denominador));
						percentual = percentual.setScale(2, BigDecimal.ROUND_HALF_UP);
						if(percentual.doubleValue() > 5.00){
							double penalidadeNaoExecucao = 0.02 * ((valorMedidoPeriodoTotal.doubleValue() + taxaSucesso.doubleValue()) - 
									(penalidadeOS.doubleValue() + penalidadeFiscalizacao.doubleValue()));
							penalidadeContratoNaoExecucao = new BigDecimal(penalidadeNaoExecucao);
							penalidadeContratoNaoExecucao = penalidadeContratoNaoExecucao.abs().setScale(2, BigDecimal.ROUND_HALF_UP);
						}
					}

					double penalidadeNaoRealizacaoServico = 0.02 * ((valorMedidoPeriodoTotal.doubleValue() + taxaSucesso.doubleValue()) - 
							(penalidadeCorteSupressao.doubleValue() + penalidadeNaoRealizacao.doubleValue()));
					
					BigDecimal penalidadeContratoNaoRealizacaoServico = 
						BigDecimal.valueOf(penalidadeNaoRealizacaoServico).setScale(2, BigDecimal.ROUND_HALF_UP);
					
					helper = new RelatorioAcompanhamentoBoletimMedicaoHelper(beans, tipoRelatorio, taxaSucesso, penalidadeOS, penalidadeFiscalizacao, 
							penalidadeContratoNaoExecucao, penalidadeCorteSupressao, penalidadeNaoRealizacao, penalidadeContratoNaoRealizacaoServico);
				}else{
					tipoRelatorio = "SIMULAÇÃO";
					helper = new RelatorioAcompanhamentoBoletimMedicaoHelper(beans, tipoRelatorio, taxaSucesso, penalidadeOS, penalidadeFiscalizacao);
				}				
			}
		}catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return helper;
	}
	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar as Ordens de serviços a partir de seu imóvel e tipo de serviço
	 * 
	 * @author Hugo Azevedo
	 * @data 14/01/2011
	 */
	
	public Collection obterOSImovelTipoServico(Integer id, Integer tipoServico) throws ControladorException{
		try{
			return repositorioAtendimentoPublico.obterOSImovelTipoServico(id,tipoServico);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * 
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
     * Obtém a quantida de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ControladorException
     */
	
	public int obterTotalOSColecaoImovelTipoServico(Collection colecaoImovel,Integer tipoServico) throws ControladorException{
		int retorno = 0;
		Collection retornoQuery = new ArrayList();
		Collection colecaoParametro = new ArrayList();
		Iterator it = colecaoImovel.iterator();
		while(it.hasNext()){
			HashMap mapa = (HashMap)it.next();
			Integer id = (Integer)mapa.get("imovel");
			colecaoParametro.add(id);
		}
		
		try{
			retornoQuery = repositorioAtendimentoPublico.obterTotalOSColecaoImovelTipoServico(colecaoParametro,tipoServico);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if(retornoQuery.size() > 0){
			Integer retornoInt = (Integer) Util.retonarObjetoDeColecao(retornoQuery); 
			retorno = retornoInt.intValue();	
		}
		
		return retorno;
	}
	
	/**
	 * [UC1189] Inserir Registro de Atendimento Loja Virtual
	 * 
	 * @author Magno Gouveia
	 * @date 12/07/2011
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarSolicitacaoTipoLojaVirtual() throws ControladorException {
		try{
			return repositorioAtendimentoPublico.pesquisarSolicitacaoTipoLojaVirtual();
		} catch (ErroRepositorioException erx) {
			erx.printStackTrace();
			throw new ControladorException("erro.sistema", erx);
		}
	}
	
	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0001] Selecionar Municípios da Região
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @return colecaoDeMunicipios
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarMunicipiosLojaVirtualCompesa() throws ControladorException {
		try {
			return repositorioAtendimentoPublico.pesquisarMunicipiosLojaVirtualCompesa();
		} catch (ErroRepositorioException erx) {
			erx.printStackTrace();
			throw new ControladorException("erro.sistema", erx);
		}
	}
	
	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0002] Exibir dados da loja
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @param id do municipio
	 * @return colecaoDeLojas
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarLojasDeAtendimentoLojaVirtualCompesa(Integer idMunicipio) throws ControladorException {
		try {
			return repositorioAtendimentoPublico.pesquisarLojasDeAtendimentoLojaVirtualCompesa(idMunicipio);
		} catch (ErroRepositorioException erx) {
			erx.printStackTrace();
			throw new ControladorException("erro.sistema", erx);
		}
	}
	
	
	
	public void ProcessarEncerramentoOSFiscalizacaoDecursoPrazo(Integer idFuncionalidadeIniciada) throws ControladorException{
		
				
				int idUnidadeIniciada = 0;	
		
				// -------------------------
				//
				// Registrar o início do processamento da Unidade de
				// Processamento
				// do Batch
				//
				// -------------------------

				idUnidadeIniciada = getControladorBatch()
						.iniciarUnidadeProcessamentoBatch(
								idFuncionalidadeIniciada,
								UnidadeProcessamento.FUNCIONALIDADE,
								0);

				
				Usuario usuarioLogado = Usuario.USUARIO_BATCH;

				
				try {
					
					SistemaParametro sistemaParametro = getControladorUtil()
							.pesquisarParametrosDoSistema();
					
					//1. Caso o número de dias para encerrar as ordens de serviço de fiscalização por decurso de prazo tenha sido informado
					if(sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() != null && sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo().intValue() > 0){
					
						
						Short idMotivoEncerramento = AtendimentoMotivoEncerramento.CANCELADO_POR_DERCURSO_DE_PRAZO;
					
						//seleciona as ordens de serviço de "Fiscalização" que ainda não tenham sido executadas
						Collection colecaoOS = repositorioAtendimentoPublico.obterColecaoOSFiscalizacaoNaoExecutadas();
						Date dataAtual = new Date();
						
						Iterator it = colecaoOS.iterator();
						
						//Unidade Organizacional do usuário logado
						FiltroUsuario filtroUsuario = new FiltroUsuario();
						filtroUsuario.adicionarParametro(
								new ParametroSimples(FiltroUsuario.ID, usuarioLogado.getId()));
						filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(
								FiltroUsuario.UNIDADE_ORGANIZACIONAL);	
						Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
						usuarioLogado = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
						
						
						while(it.hasNext()){
							Object[] obj = (Object[])it.next();
							
							Integer osId = (Integer) obj[0];
							Date dataEmissao = (Date) obj[1];
							
							
							//FERIADO NACIONAL
							Collection<NacionalFeriado> colecaoFeriadosNacionais = 
								getControladorUtil().pesquisarFeriadosNacionais();
							
							
							//Calculando a data de validade da Ordem de Serviço
							Date  DataVencimento = 
									Util.adicionarNumeroDiasUteisDeUmaData(
											dataEmissao, 
											sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo().intValue(), 
											colecaoFeriadosNacionais,
											null);
							
							//Verificar se a OS venceu
							if(Util.compararData(dataAtual, DataVencimento) == 1){
							
									// encerrar a ordem de serviço, com o motivo
									// correspodente a decurso de prazo
									// [UC0457] - Encerrar Ordem de Serviço
									this.getControladorOrdemServico()
										.encerrarOSSemExecucao(
												osId,
												dataAtual,
												usuarioLogado,
												idMotivoEncerramento.toString(),
												dataAtual, null, null, null, null,null,null);
								
								}
												 
						}
						
						getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
								idUnidadeIniciada, false);
						
						System.out.println("########## FINALIZADO EM  = " + new Date());
							
					}
					else{
						getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
								idUnidadeIniciada, false);
						
						System.out.println("########## FINALIZADO EM  = " + new Date());
					}

				} catch (Exception e) {
					// Este catch serve para interceptar qualquer exceção que o processo
					// batch venha a lançar e garantir que a unidade de processamento do
					// batch será atualizada com o erro ocorrido
					e.printStackTrace();

					getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
							idUnidadeIniciada, true);
					System.out.println("########## FINALIZADO EM  = " + new Date());

					throw new EJBException(e);
				}		
		
	}
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * Método que vai retornar as fotos de uma determinada
	 * ordem de serviço passada no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID (Ordem de Serviço ou da Foto Ordem de Serviço)
	 * @param Boolean - Indica se o id é da OS ou da Foto (true = OS, false = Foto) 
	 * 
	 * @return Collection<OrdemServicoFoto> - Coleção das Fotos da OS
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoFoto> pesquisarFotosOrdemServico(Integer id, boolean idOS) throws ControladorException{
		Collection<OrdemServicoFoto> fotos = null;
		Collection<Object[]> retorno = null;
		
		try {
			if(idOS){
				retorno = this.repositorioAtendimentoPublico.pesquisarFotosOrdemServico(id);
			}else{
				retorno = this.repositorioAtendimentoPublico.pesquisarFotosOrdemServicoPorIdFoto(id);
			}
			
			//Verifica se a consulta trouxe algum ResultSet
			if(!Util.isVazioOrNulo(retorno)){
				
				Object[] ordemServicoFoto;
				
				//Ordem de serviço foto e seus atributos necessários para instanciação.
				OrdemServicoFoto osFoto = null;
				Integer idFoto = null;
				OrdemServico os = null;
				String descricaoFoto = "";
				byte[] foto = null;
				
				fotos = new ArrayList<OrdemServicoFoto>();
				
				Iterator<Object[]> iterator = retorno.iterator();
					
				//Itera pelas linhas do ResultSet
				while(iterator.hasNext()){
					ordemServicoFoto = iterator.next();
					
					if(ordemServicoFoto[0] != null){
						idFoto = (Integer) ordemServicoFoto[0];
					}
					
					if(ordemServicoFoto[1] != null){
						os = new OrdemServico();
						os.setId((Integer) ordemServicoFoto[1]);
					}
					
					if(ordemServicoFoto[2] != null){
						descricaoFoto = (String) ordemServicoFoto[2];
					}
					
					if(ordemServicoFoto[3] != null){
						foto = (byte[]) ordemServicoFoto[3];
					}
					osFoto = new OrdemServicoFoto(idFoto, os, descricaoFoto, foto);
					fotos.add(osFoto);
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return fotos;
	}
	
	/**
	 *
	 * 
	 * @autor: Wellington Rocha
	 * @date: 03/07/2012
	 * 
	 *        Pesquisar Locais de Instação de Ramal Ativos
	 * 
	 *        Geração de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<RamalLocalInstalacao> pesquisarRamalLocalInstalacao()
			throws ControladorException {
		try {
			Collection<RamalLocalInstalacao> colecaoRamalLocalInstalacao = new ArrayList<RamalLocalInstalacao>();
			Collection colecao = repositorioAtendimentoPublico.pesquisarRamalLocalInstalacao();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator colecaoIterator = colecao.iterator();

				while (colecaoIterator.hasNext()) {
					RamalLocalInstalacao ramalLocalInstalacao = (RamalLocalInstalacao) colecaoIterator
							.next();
					colecaoRamalLocalInstalacao.add(ramalLocalInstalacao);
				}
			}

			return colecaoRamalLocalInstalacao;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
}
