package gcom.atendimentopublico.registroatendimento;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.atendimentopublico.EspecificacaoPavimentacaoServicoTipo;
import gcom.atendimentopublico.FiltroEspecificacaoPavimentacaoServicoTipo;
import gcom.atendimentopublico.bean.DadosRAReiteracaoHelper;
import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK;
import gcom.atendimentopublico.ordemservico.FiltroEspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.portal.QuestionarioSatisfacaoCliente;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ExibirRAFaltaAguaImovelHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarGestaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper;
import gcom.atendimentopublico.registroatendimento.bean.GerarNumeracaoRAManualHelper;
import gcom.atendimentopublico.registroatendimento.bean.GestaoRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterIndicadorExistenciaHidrometroHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterRAAssociadoHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFaltaAguaPendenteHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAPorUnidadePorUsuarioHelper;
import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoEncerradoLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoFaltaAguaGeneralizadaHelper;
import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoPendenteLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.ResumoRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ResumoRegistroAtendimentoPorAnoHelper;
import gcom.atendimentopublico.registroatendimento.bean.VerificarRAFaltaAguaHelper;
import gcom.atualizacaocadastral.IClienteEndereco;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.ControladorUnidadeLocal;
import gcom.cadastro.unidade.ControladorUnidadeLocalHome;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FiltroVencimentoAlternativo;
import gcom.faturamento.VencimentoAlternativo;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.CreditosHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.atendimentopublico.registroatendimento.GAtendimentoMotivoEncerramento;
import gcom.gerencial.atendimentopublico.registroatendimento.GMeioSolicitacao;
import gcom.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipo;
import gcom.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipoEspecificacao;
import gcom.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimento;
import gcom.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimentoPorAno;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocal;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocalHome;
import gcom.gerencial.cadastro.IRepositorioGerencialCadastro;
import gcom.gerencial.cadastro.RepositorioGerencialCadastroHBM;
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
import gcom.gerencial.micromedicao.GRota;
import gcom.interceptor.RegistradorOperacao;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroAbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroManutencaoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.seguranca.ControladorPermissaoEspecialLocal;
import gcom.seguranca.ControladorPermissaoEspecialLocalHome;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesAplicacao;
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
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.collection.PersistentSet;

public class ControladorRegistroAtendimentoSEJB implements SessionBean {
	
	
	private static final long serialVersionUID = 1L;
	
	private IRepositorioRegistroAtendimento repositorioRegistroAtendimento = null;
	private IRepositorioGerencialCadastro repositorioGerencialCadastro = null;
	
	SessionContext sessionContext;
	
	public void ejbCreate() throws CreateException {
		repositorioRegistroAtendimento = RepositorioRegistroAtendimentoHBM
		.getInstancia();
		repositorioGerencialCadastro = RepositorioGerencialCadastroHBM
		.getInstancia();		
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
		
		// pega a instância do ServiceLocator.
		
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
	
	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;
		
		// pega a instância do ServiceLocator.
		
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
	 * Retorna o valor de controladorCadastro
	 * 
	 * @return O valor de controladorCadastro
	 */
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
	
	
	/**
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorCliente
	 */
	private ControladorClienteLocal getControladorCliente() {
		
		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;
		
		// pega a instância do ServiceLocator.
		
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();
			
			localHome = (ControladorClienteLocalHome) locator
			.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
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
	
	private ControladorPermissaoEspecialLocal getControladorPermissaoEspecial() {
		
		ControladorPermissaoEspecialLocalHome localHome = null;
		ControladorPermissaoEspecialLocal local = null;
		
		// pega a instância do ServiceLocator.
		
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();
			
			localHome = (ControladorPermissaoEspecialLocalHome) locator
			.getLocalHome(ConstantesJNDI.CONTROLADOR_PERMISSAO_ESPECIAL_SEJB);
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
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorCliente
	 */
	private ControladorCobrancaLocal getControladorCobranca() {
		
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;
		
		// pega a instância do ServiceLocator.
		
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
	 * Retorna o valor de controladorUnidade
	 * 
	 * @return O valor de controladorUnidade
	 */
	private ControladorUnidadeLocal getControladorUnidade() {
		
		ControladorUnidadeLocalHome localHome = null;
		ControladorUnidadeLocal local = null;
		
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();
			
			localHome = (ControladorUnidadeLocalHome) locator
			.getLocalHome(ConstantesJNDI.CONTROLADOR_UNIDADE_SEJB);
			
			local = localHome.create();
			
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorEndereco
	 * 
	 * @return O valor de controladorEndereco
	 */
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
	
	/**
	 * Retorna o valor de controladorOrdemServico
	 * 
	 * @return O valor de controladorOrdemServico
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;
		
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();
			
			localHome = (ControladorOrdemServicoLocalHome) locator
			.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
			
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
	
	private ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {
		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;
		
		// pega a instância do ServiceLocator.
		
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();
			
			localHome = (ControladorAtendimentoPublicoLocalHome) locator
			.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);
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
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Data Prevista = Data válida obtida a partir da Data do Atendimento +
	 * número de dias previstos para a especificação do tipo de solicitação
	 * (STEP_NNDIAPRAZO da tabela SOLICITACAO_TIPO_ESPECIFICACAO).
	 * 
	 * Caso o sistema deva sugerir a unidade destino para o primeiro
	 * encaminhamento do Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na
	 * tabela SISTEMA_PARAMETROS)
	 * 
	 * Caso a Especificação esteja associada a uma unidade (UNID_ID da tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO com o valor diferente de nulo), definir a
	 * unidade destino a partir da Especificação (UNID_ID e UNID_DSUNIDADE da
	 * tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com SETP_ID=Id da Especificação
	 * selecionada).
	 * 
	 * [SB0003] Define Data Prevista e Unidade Destino da Especificação
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacao(
			Date dataAtendimento, Integer idSolicitacaoTipoEspecificacao)
	throws ControladorException {
		
		DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper retorno = new DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper();
		Object[] parmsEspecificacao = null;
		try {
			parmsEspecificacao = repositorioRegistroAtendimento
			.pesquisarIndicadorFaltaAguaRA(idSolicitacaoTipoEspecificacao);
			if (parmsEspecificacao != null) {
				
				if (parmsEspecificacao[0] != null) {
					retorno.setIndFaltaAgua(((Short) parmsEspecificacao[0])
							.toString());
				}
				if (parmsEspecificacao[1] != null) {
					retorno.setIndMatricula(((Integer) parmsEspecificacao[1])
							.toString());
				}
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				idSolicitacaoTipoEspecificacao));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			// Exige matrícula do Imóvel
			boolean imovelObrigatorio = this
			.especificacaoExigeMatriculaImovel(solicitacaoTipoEspecificacao);
			
			if (imovelObrigatorio) {
				retorno.setImovelObrigatorio("SIM");
			} else {
				retorno.setImovelObrigatorio("NAO");
			}
			
			// Exige pavimento rua
			boolean pavimentoRuaObrigatorio = this
			.especificacaoExigePavimentoRua(solicitacaoTipoEspecificacao);
			
			if (pavimentoRuaObrigatorio) {
				retorno.setPavimentoRuaObrigatorio("SIM");
			} else {
				retorno.setPavimentoRuaObrigatorio("NAO");
			}
			
			// Exige pavimento calçada
			boolean pavimentoCalcadaObrigatorio = this
			.especificacaoExigePavimentoCalcada(solicitacaoTipoEspecificacao);
			
			if (pavimentoCalcadaObrigatorio) {
				retorno.setPavimentoCalcadaObrigatorio("SIM");
			} else {
				retorno.setPavimentoCalcadaObrigatorio("NAO");
			}
			
			SistemaParametro sistemaParametro = this.getControladorUtil()
			.pesquisarParametrosDoSistema();
			
			if (solicitacaoTipoEspecificacao.getDiasPrazo() != null) {
				
				//adicionado por Vivianne Sousa 21/11/2008
				//analista Fatima Sampaio
				if(sistemaParametro.getIndicadorCalculoPrevisaoRADiasUteis() != null &&
						sistemaParametro.getIndicadorCalculoPrevisaoRADiasUteis().equals(ConstantesSistema.SIM)){
					//Caso o sistema deva considerar apenas os dias uteis no calculo da data prevista
					
					//FERIADO NACIONAL
					Collection<NacionalFeriado> colecaoFeriadosNacionais = 
						getControladorUtil().pesquisarFeriadosNacionais();
					
					//FERIADO MUNICIPAL
//					FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();
//					
//					filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(
//					FiltroMunicipioFeriado.ID_MUNICIPIO, municipio.getId()));
//					
//					Collection<MunicipioFeriado> colecaoFeriadosMunicipais = 
//					getControladorUtil().pesquisar(filtroMunicipioFeriado,
//					MunicipioFeriado.class.getName());
					
					retorno.setDataPrevista(Util.adicionarNumeroDiasUteisDeUmaData(
							dataAtendimento, solicitacaoTipoEspecificacao.getDiasPrazo(),
							colecaoFeriadosNacionais,null));
					
				}else{
					retorno.setDataPrevista(Util.adicionarNumeroDiasDeUmaData(
							dataAtendimento, solicitacaoTipoEspecificacao.getDiasPrazo()));
				}
				
			} else {
				
				retorno.setDataPrevista(dataAtendimento);
			}
			
			
			if (sistemaParametro.getIndicadorSugestaoTramite() != null
					&& sistemaParametro.getIndicadorSugestaoTramite().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)
							&& solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
							&& solicitacaoTipoEspecificacao.getUnidadeOrganizacional()
							.getIndicadorTramite() == ConstantesSistema.INDICADOR_USO_ATIVO
							.shortValue()) {
				
				/*
				 * [UC0366] Inserir Registro de Atendimento [FS0018] Verificar
				 * existência de unidade centralizadora
				 */
				UnidadeOrganizacional unidadeDestino = this
				.getControladorUnidade()
				.verificarExistenciaUnidadeCentralizadora(
						solicitacaoTipoEspecificacao
						.getUnidadeOrganizacional());
				
				retorno.setUnidadeOrganizacional(unidadeDestino);
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Valida os dados gerais do atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/07/2006
	 * 
	 * @param dataAtendimento,
	 *            horaAtendimento, tempoEsperaInicial, tempoEsperaFinal,
	 *            idUnidadeOrganizacional
	 * @return void
	 * @throws ControladorException
	 */
	public void validarInserirRegistroAtendimentoDadosGerais(
			String dataAtendimento, String horaAtendimento,
			String tempoEsperaInicial, String tempoEsperaFinal,
			String idUnidadeOrganizacional, String numeroRAManual)
	throws ControladorException {
		
		// [FS0001] - Verificar data do atendimento
		Date dataCorrente = new Date();
		Date dataAtendimentoObjeto = Util
		.converteStringParaDate(dataAtendimento);
		
		if (dataAtendimentoObjeto.after(dataCorrente)) {
			throw new ControladorException(
					"atencao.data_atendimento_posterior_data_corrente", null,
					Util.formatarData(dataCorrente));
		}
		
		// [FS0002] - Verificar hora do atendimento
		if (Util.datasIguais(dataAtendimentoObjeto, dataCorrente)
				&& Util.compararHoraMinuto(Util
						.formatarHoraSemSegundos(horaAtendimento), Util
						.formatarHoraSemSegundos(dataCorrente), ">")) {
			throw new ControladorException(
					"atencao.hora_atendimento_posterior_hora_corrente", null,
					Util.formatarHoraSemSegundos(dataCorrente));
		}
		
		// [FS0014] - Verificar tempo de espera inicial para atendimento
		if (Util.datasIguais(dataAtendimentoObjeto, dataCorrente)
				&& tempoEsperaInicial != null
				&& tempoEsperaInicial.length() > 0
				&& Util.compararHoraMinuto(Util
						.formatarHoraSemSegundos(tempoEsperaInicial), Util
						.formatarHoraSemSegundos(dataCorrente), ">")) {
			throw new ControladorException(
					"atencao.tempo_espera_atendimento_posterior_hora_corrente",
					null, "Tempo de Espera Inicial", Util
					.formatarHoraSemSegundos(dataCorrente));
		}
		
		// [FS0015] - Verificar tempo de espera final para atendimento
		if (Util.datasIguais(dataAtendimentoObjeto, dataCorrente)
				&& tempoEsperaFinal != null
				&& tempoEsperaFinal.length() > 0
				&& Util.compararHoraMinuto(Util
						.formatarHoraSemSegundos(tempoEsperaFinal), Util
						.formatarHoraSemSegundos(dataCorrente), ">")) {
			throw new ControladorException(
					"atencao.tempo_espera_atendimento_posterior_hora_corrente",
					null, "Tempo de Espera Final ", Util
					.formatarHoraSemSegundos(dataCorrente));
		}
		
		// [FS0016] - Verificar tempo de espera final para atendimento menor que
		// o inicial
		if (tempoEsperaInicial != null
				&& tempoEsperaFinal != null
				&& tempoEsperaInicial.length() > 0
				&& tempoEsperaFinal.length() > 0
				&& Util.compararHoraMinuto(Util
						.formatarHoraSemSegundos(tempoEsperaInicial), Util
						.formatarHoraSemSegundos(tempoEsperaFinal), ">")) {
			
			throw new ControladorException(
			"atencao.tempo_espera_final_anterior_tempo_espera_final_registro_atendimento");
		}
		
		// [FS0033] Verificar autorização da unidade de atendimento para
		// abertura de registro de atendimento
		this.getControladorUnidade().verificarAutorizacaoUnidadeAberturaRA(
				new Integer(idUnidadeOrganizacional), true);
		
		// [FS0042] Verificar número Informado
		if (numeroRAManual != null && !numeroRAManual.equalsIgnoreCase("")) {
			
			if (Util.validarStringNumerica(numeroRAManual) == false){
				throw new ControladorException(
				"atencao.campo_aceita_apenas_numero", null, "Número do RA Manual");
			}
			
			// String[] arrayNumeroRAManual = numeroRAManual.split("-");
			String[] arrayNumeroRAManual = new String[2];
			arrayNumeroRAManual[0] = numeroRAManual.substring(0, numeroRAManual
					.length() - 1).trim();
			arrayNumeroRAManual[1] = numeroRAManual.substring(numeroRAManual
					.length() - 1, numeroRAManual.length()).trim();
			
			Integer numeracao = null;
			Integer digitoModulo11 = null;
			if (arrayNumeroRAManual[0] != null && !arrayNumeroRAManual[0].equals("")
					&& arrayNumeroRAManual[1] != null && !arrayNumeroRAManual[1].equals("")){
				numeracao = new Integer(arrayNumeroRAManual[0]);
				digitoModulo11 = new Integer(arrayNumeroRAManual[1]);
			}else{
				//não informou 2 posições (número e digito verificador)
				throw new ControladorException(
				"atencao.numero_ra_manual_obrigatorio_numero_digito");
			}
			
			Integer numeracaoComDigito = new Integer(arrayNumeroRAManual[0]
			                                                             + arrayNumeroRAManual[1]);
			
			// Caso o número informado seja maior que o número atualizado como
			// ltimo emitido
			SistemaParametro sistemaParametro = this.getControladorUtil()
			.pesquisarParametrosDoSistema();
			
			Integer ultimaNumeracao = sistemaParametro.getUltimoRAManual()
			.intValue();
			
			if (numeracao.intValue() > ultimaNumeracao) {
				throw new ControladorException(
				"atencao.numeracao_ra_manual_superior");
			}
			
			// Caso o dígito verificador do número informado não bata com o
			// dígito calculado com o módulo 11
			if (!digitoModulo11.equals(Util.obterDigitoVerificadorModulo11(Long
					.parseLong(numeracao.toString())))) {
				throw new ControladorException(
				"atencao.numeracao_ra_manual_digito_invalido");
			}
			
			// Caso o número informado já esteja informado em outro registro
			// atendimento
			Integer quantidadeRA = null;
			
			try {
				quantidadeRA = this.repositorioRegistroAtendimento
				.verificaNumeracaoRAManualInformada(numeracaoComDigito);
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (quantidadeRA != null) {
				throw new ControladorException(
						"atencao.numeracao_ra_manual_ja_informado", null,
						quantidadeRA.toString());
			}
		}
		
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especificação exija a matrícula do Imóvel (STEP_ICMATRICULA com o
	 * valor correspondente a um na tabela SOLICITACAO_TIPO_ESPECIFICACAO),
	 * obrigatório; caso contrário, opcional
	 * 
	 * @author Raphael Rossiter
	 * @date 28/07/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarExigenciaImovelPelaEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		boolean retorno = false;
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				idSolicitacaoTipoEspecificacao));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			if (solicitacaoTipoEspecificacao.getIndicadorMatricula() != null
					&& solicitacaoTipoEspecificacao.getIndicadorMatricula()
					.equals(
							ConstantesSistema.INDICADOR_USO_ATIVO
							.intValue())) {
				
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a especificação informada para o RA tem indicativo que é para
	 * verificar débito (STEP_ICVERIFICADEBITO da tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO com valor igual a SIM (1)), o sistema
	 * deverá verificar se o imóvel informado tenha débito <<incluir>> [UC0067]
	 * Obter Débito do Imóvel ou Cliente (passando o imóvel). [FS0043] -
	 * Verifica imóvel com débito.
	 * 
	 * [SB0032] - Verifica se o imóvel informado tem débito.
	 * 
	 * [FS0043] - Verifica imóvel com débito
	 * 
	 * @author Raphael Rossiter
	 * @date 19/02/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public void verificarImovelComDebitos(
			Integer idSolicitacaoTipoEspecificacao, Integer idImovel)
	throws ControladorException {
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				idSolicitacaoTipoEspecificacao));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			if (solicitacaoTipoEspecificacao.getIndicadorVerificarDebito() != null
					&& solicitacaoTipoEspecificacao
					.getIndicadorVerificarDebito().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
					.intValue()) {
				
				Date dataVencimentoInicial = Util.criarData(1, 1, 0001);
				Date dataVencimentoFinal = new Date();
				
				// [UC0067] Obter Débito do Imóvel ou Cliente
				ObterDebitoImovelOuClienteHelper imovelDebitos = this
				.getControladorCobranca().obterDebitoImovelOuCliente(1,
						idImovel.toString(), null, null, "000101",
						"999912", dataVencimentoInicial,
						dataVencimentoFinal, 1, 1, 1, 1, 1, 1, 1, true);
				
				// [FS0043] - Verifica imóvel com débito
				if ((imovelDebitos.getColecaoDebitoACobrar() != null && !imovelDebitos
						.getColecaoDebitoACobrar().isEmpty())
						|| (imovelDebitos.getColecaoContasValores() != null && !imovelDebitos
								.getColecaoContasValores().isEmpty())
								|| (imovelDebitos.getColecaoContasValoresImovel() != null && !imovelDebitos
										.getColecaoContasValoresImovel().isEmpty())
										|| (imovelDebitos.getColecaoGuiasPagamentoValores() != null && !imovelDebitos
												.getColecaoGuiasPagamentoValores().isEmpty())) {
					
					throw new ControladorException(
							"atencao.imovel_com_debitos", null, idImovel
							.toString(), solicitacaoTipoEspecificacao
							.getDescricao());
				}
			}
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Obter e habilitar/desabilitar os Dados da Identificação do Local da
	 * ocorrência de acordo com as situações abaixo descritas no caso de uso
	 * 
	 * [SB0004] Obtém e Habilita/Desabilita Dados da Identificação do Local da
	 * ocorrência e Dados do Solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 28/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao, idSolicitacaoTipo,
	 *            levantarExceptionImovelInexistente
	 * @return ObterDadosIdentificacaoLocalOcorrenciaHelper
	 * @throws ControladorException
	 */
	public ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrencia(
			Integer idImovel, Integer idSolicitacaoTipoEspecificacao,
			Integer idSolicitacaoTipo,
			boolean levantarExceptionImovelInexistente)
	throws ControladorException {
		
		ObterDadosIdentificacaoLocalOcorrenciaHelper retorno = new ObterDadosIdentificacaoLocalOcorrenciaHelper();
		
		Imovel imovel = this.getControladorImovel()
		.pesquisarImovelRegistroAtendimento(idImovel);
		
		if (imovel != null) {
			
			// [FS0020] - Verificar existência de registro de atendimento para o
			// Imóvel com a mesma especificação
			this.verificarExistenciaRAImovelMesmaEspecificacao(imovel.getId(),
					idSolicitacaoTipoEspecificacao);
			
			retorno.setImovel(imovel);
			
			if (this
					.verificarExigenciaImovelPelaEspecificacao(idSolicitacaoTipoEspecificacao)) {
				
				retorno.setHabilitarAlteracaoEndereco(false);
				retorno.setInformarEndereco(false);
				
				/*
				 * [FS0019] - Verificar endereço do Imóvel
				 * 
				 * Caso a matrícula do Imóvel seja obrigatória (STEP_ICMATRICULA
				 * com o valor correspondente a um na tabela
				 * SOLICITACAO_TIPO_ESPECIFICACAO) e o endereço do Imóvel seja
				 * descritivo (LGBR_ID com o valor nulo na tabela IMOVEL para
				 * IMOV_ID=matrícula do Imóvel)
				 */
				if (imovel.getLogradouroBairro() == null) {
					
					String enderecoDescritivo = this.getControladorEndereco()
					.pesquisarEnderecoFormatado(imovel.getId());
					
					retorno.setEnderecoDescritivo(enderecoDescritivo);
				}
				
			} else if (imovel.getLogradouroBairro() != null) {
				retorno.setHabilitarAlteracaoEndereco(true);
				retorno.setInformarEndereco(false);
			} else {
				retorno.setHabilitarAlteracaoEndereco(true);
				retorno.setInformarEndereco(true);
			}
			
		} else if (levantarExceptionImovelInexistente) {
			
			// [FS0005] Verificar existência da matrícula do Imóvel
			throw new ControladorException("atencao.label_inexistente", null,
			"Imóvel");
		}
		
		return retorno;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Obter e habilitar/desabilitar os Dados da Identificação do Local da
	 * ocorrência de acordo com as situações abaixo descritas no caso de uso
	 * 
	 * [SB0004] Obtém e Habilita/Desabilita Dados da Identificação do Local da
	 * ocorrência e Dados do Solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 28/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao, idSolicitacaoTipo,
	 *            levantarExceptionImovelInexistente
	 * @return ObterDadosIdentificacaoLocalOcorrenciaHelper
	 * @throws ControladorException
	 */
	public ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaAtualizar(
			Integer idImovel, Integer idSolicitacaoTipoEspecificacao,
			Integer idSolicitacaoTipo, Integer idRegistroAtendimento,
			boolean levantarExceptionImovelInexistente)
	throws ControladorException {
		
		ObterDadosIdentificacaoLocalOcorrenciaHelper retorno = new ObterDadosIdentificacaoLocalOcorrenciaHelper();
		
		Imovel imovel = this.getControladorImovel()
		.pesquisarImovelRegistroAtendimento(idImovel);
		
		if (imovel != null) {
			
			// [FS0020] - Verificar existência de registro de atendimento para o
			// Imóvel com a mesma especificação
			this.verificarExistenciaRAImovelMesmaEspecificacaoRAAtualizar(
					imovel.getId(), idRegistroAtendimento,
					idSolicitacaoTipoEspecificacao);
			
			retorno.setImovel(imovel);
			
			if (this
					.verificarExigenciaImovelPelaEspecificacao(idSolicitacaoTipoEspecificacao)) {
				
				retorno.setHabilitarAlteracaoEndereco(false);
				retorno.setInformarEndereco(false);
				
				/*
				 * [FS0019] - Verificar endereço do Imóvel
				 * 
				 * Caso a matrícula do Imóvel seja obrigatória (STEP_ICMATRICULA
				 * com o valor correspondente a um na tabela
				 * SOLICITACAO_TIPO_ESPECIFICACAO) e o endereço do Imóvel seja
				 * descritivo (LGBR_ID com o valor nulo na tabela IMOVEL para
				 * IMOV_ID=matrícula do Imóvel)
				 */
				if (imovel.getLogradouroBairro() == null) {
					
					String enderecoDescritivo = this.getControladorEndereco()
					.pesquisarEnderecoFormatado(imovel.getId());
					
					retorno.setEnderecoDescritivo(enderecoDescritivo);
				}
				
			} else if (imovel.getLogradouroBairro() != null) {
				retorno.setHabilitarAlteracaoEndereco(true);
				retorno.setInformarEndereco(false);
			} else {
				retorno.setHabilitarAlteracaoEndereco(true);
				retorno.setInformarEndereco(true);
			}
			
		} else if (levantarExceptionImovelInexistente) {
			
			// [FS0005] Verificar existência da matrícula do Imóvel
			throw new ControladorException("atencao.label_inexistente", null,
			"Imóvel");
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0002] Habilita/Desabilita Município, Bairro, Área do Bairro e Divisão
	 * de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 02/08/2006
	 * 
	 * @param idSolicitacaoTipo
	 * @return ObterDadosIdentificacaoLocalOcorrenciaHelper
	 * @throws ControladorException
	 */
	public ObterDadosIdentificacaoLocalOcorrenciaHelper habilitarGeograficoDivisaoEsgoto(
			Integer idSolicitacaoTipo) throws ControladorException {
		
		ObterDadosIdentificacaoLocalOcorrenciaHelper retorno = null;
		
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		
		filtroSolicitacaoTipo
		.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");
		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipo.ID, idSolicitacaoTipo));
		
		Collection colecaoSolicitacaoTipo = this.getControladorUtil()
		.pesquisar(filtroSolicitacaoTipo,
				SolicitacaoTipo.class.getName());
		
		if (colecaoSolicitacaoTipo != null && !colecaoSolicitacaoTipo.isEmpty()) {
			
			retorno = new ObterDadosIdentificacaoLocalOcorrenciaHelper();
			
			SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipo);
			
			if (solicitacaoTipo.getIndicadorFaltaAgua() == ConstantesSistema.SIM
					.shortValue()) {
				retorno.setSolicitacaoTipoRelativoFaltaAgua(true);
			} else {
				retorno.setSolicitacaoTipoRelativoFaltaAgua(false);
			}
			
			if (solicitacaoTipo.getSolicitacaoTipoGrupo() != null
					&& solicitacaoTipo.getSolicitacaoTipoGrupo()
					.getIndicadorEsgoto() == ConstantesSistema.SIM
					.shortValue()) {
				retorno.setSolicitacaoTipoRelativoAreaEsgoto(true);
			} else {
				retorno.setSolicitacaoTipoRelativoAreaEsgoto(false);
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o Imóvel com a mesma
	 * especificação (existe ocorrência na tabela REGISTRO_ATENDIMENTO com
	 * IMOV_ID=matrícula do Imóvel e STEP_ID=Id da Especificação selecionada e
	 * RGAT_CDSITUACAO=1).
	 * 
	 * [FS0020] - Verificar existência de registro de atendimento para o Imóvel
	 * com a mesma especificação
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarExistenciaRAImovelMesmaEspecificacao(Integer idImovel,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		RegistroAtendimento registroAtendimento = null;
		
		try {
			
			registroAtendimento = repositorioRegistroAtendimento
			.verificarExistenciaRAImovelMesmaEspecificacao(idImovel,
					idSolicitacaoTipoEspecificacao);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (registroAtendimento != null) {
			throw new ControladorException(
					"atencao.imovel_ja_possui_ra_com_mesma_especificacao",
					null, registroAtendimento.getSolicitacaoTipoEspecificacao()
					.getDescricao(), registroAtendimento.getId()
					.toString(), registroAtendimento.getImovel()
					.getId().toString());
		}
	}
	
	/**
	 * [UC0366] Atualizar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o Imóvel com a mesma
	 * especificação (existe ocorrência na tabela REGISTRO_ATENDIMENTO com
	 * IMOV_ID=matrícula do Imóvel e STEP_ID=Id da Especificação selecionada e
	 * RGAT_CDSITUACAO=1).
	 * 
	 * [FS0020] - Verificar existência de registro de atendimento para o Imóvel
	 * com a mesma especificação
	 * 
	 * @author Sávio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarExistenciaRAImovelMesmaEspecificacaoRAAtualizar(
			Integer idImovel, Integer idRA,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		RegistroAtendimento registroAtendimento = null;
		
		try {
			
			registroAtendimento = repositorioRegistroAtendimento
			.verificarExistenciaRAAtualizarImovelMesmaEspecificacao(
					idImovel, idRA, idSolicitacaoTipoEspecificacao);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (registroAtendimento != null) {
			throw new ControladorException(
					"atencao.imovel_ja_possui_ra_com_mesma_especificacao",
					null, registroAtendimento.getSolicitacaoTipoEspecificacao()
					.getDescricao(), registroAtendimento
					.getSolicitacaoTipoEspecificacao().getId()
					.toString(), registroAtendimento.getImovel()
					.getId().toString());
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o Imóvel (existe
	 * ocorrência na tabela REGISTRO_ATENDIMENTO com IMOV_ID=matrícula do Imóvel
	 * e RGAT_CDSITUACAO=1)
	 * 
	 * [SB0021]Verifica existência de Registro de Atendimento Pendente para o
	 * Imóvel
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificaExistenciaRAPendenteImovel(Integer idImovel)
	throws ControladorException {
		
		boolean retorno = false;
		Integer quantidadeRA = null;
		
		try {
			
			quantidadeRA = repositorioRegistroAtendimento
			.verificaExistenciaRAPendenteImovel(idImovel);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (quantidadeRA != null && quantidadeRA > 0) {
			retorno = true;
		}
		
		return retorno;
	}
	
	/**
	 * [UC0399] Inserir Tipo de Solicitação com Especificação
	 * 
	 * [SB0001] - Gerar Tipo Solicitação com Especificações
	 * 
	 * @author Sávio Luiz
	 * @date 01/08/2006
	 * 
	 * @param solicitacaoTipo,
	 *            colecaoSolicitacaoTipoEspecificacao, usuarioLogado
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer inserirTipoSolicitacaoEspecificacao(
			SolicitacaoTipo solicitacaoTipo,
			Collection colecaoSolicitacaoTipoEspecificacao,
			Usuario usuarioLogado) throws ControladorException {
		Integer idTipoSolicitacao = null;
		
		// [SF0001] - Verificar existencia da descrição
		try {
			
			Integer idSolicitacaoTipoNaBase = repositorioRegistroAtendimento
			.verificarExistenciaDescricaoTipoSolicitacao(solicitacaoTipo
					.getDescricao());
			if (idSolicitacaoTipoNaBase != null
					&& !idSolicitacaoTipoNaBase.equals("")) {
				throw new ControladorException(
				"atencao.tipo.solicitacao.ja.existe");
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,
				solicitacaoTipo.getId(), solicitacaoTipo.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado, 
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		registradorOperacao.registrarOperacao(solicitacaoTipo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		// inseri tipo solicitacao especificacao
		idTipoSolicitacao = (Integer) getControladorUtil().inserir(
				solicitacaoTipo);
		solicitacaoTipo.setId(idTipoSolicitacao);
		
		Iterator iteratorSolicitacaoTipoEspecificacao = colecaoSolicitacaoTipoEspecificacao
		.iterator();
		while (iteratorSolicitacaoTipoEspecificacao.hasNext()) {
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iteratorSolicitacaoTipoEspecificacao
			.next();
			// recupera a coleção de especificação Serviço tipo
			Collection colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao
			.getEspecificacaoServicoTipos();
			// limpa a coleção de especificação Serviço tipo no objeto
			// solicitação tipo especificação para não dar erro
			solicitacaoTipoEspecificacao.setEspecificacaoServicoTipos(null);
			// inseri o tipo de solicitação no tipo de solicitação especificação
			solicitacaoTipoEspecificacao.setSolicitacaoTipo(solicitacaoTipo);
			
			//registra transação
			registradorOperacao.registrarOperacao(solicitacaoTipoEspecificacao);
			
			// inseri tipo solicitacao especificacao
			Integer idSolicitacaoTipoEspecificacao = (Integer) getControladorUtil()
			.inserir(solicitacaoTipoEspecificacao);
			// caso a coleção de especificação Serviço tipo seja diferente de
			// nula
			if (colecaoEspecificacaoServicoTipo != null
					&& !colecaoEspecificacaoServicoTipo.isEmpty()) {
				Iterator iteratorSolicitacaoTipoServico = colecaoEspecificacaoServicoTipo
				.iterator();
				while (iteratorSolicitacaoTipoServico.hasNext()) {
					EspecificacaoServicoTipo especificacaoServicoTipo = (EspecificacaoServicoTipo) iteratorSolicitacaoTipoServico
					.next();
					EspecificacaoServicoTipoPK especificacaoServicoTipoPK = especificacaoServicoTipo
					.getComp_id();
					especificacaoServicoTipoPK
					.setIdSolicitacaoTipoEspecificacao(idSolicitacaoTipoEspecificacao);
					especificacaoServicoTipo
					.setComp_id(especificacaoServicoTipoPK);
					// inseri especificacao Serviço tipo
					getControladorUtil().inserir(especificacaoServicoTipo);
				}
			}
			
		}
		
		return idTipoSolicitacao;
	}
	
	// private Collection<Integer> colecaoRAPorUnidadeSuperior;
	
	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return void
	 * @throws ControladorException
	 */
	public Collection<RegistroAtendimento> filtrarRegistroAtendimento(
			FiltrarRegistroAtendimentoHelper filtroRA)
			throws ControladorException {
		
		Collection<RegistroAtendimento> toReturn = new ArrayList();
		Collection colecaoRA = new ArrayList();
		
		try {
			/*
			 * Adicionado por Victor Cisneiros 19/set/2008
			 * 
			 * Se a pesquisa for por Unidade Superior, pesquisar todas as unidades filhas dessa Unidade Superior
			 * e então pesquisar os registros cuja unidade atual está presente nessa colecao de unidades filhas
			 */
			if (filtroRA.getUnidadeSuperior() != null) {
				Integer idUnidadeSuperior = filtroRA.getUnidadeSuperior().getId();
				
				Collection<UnidadeOrganizacional> unidadesFilhas = getControladorAtendimentoPublico().pesquisarUnidadesFilhas(
						idUnidadeSuperior).getUnidades().values();
				
				Collection<Integer> collectionIdsUnidadeAtual = new ArrayList<Integer>();
				for (UnidadeOrganizacional unidade : unidadesFilhas) {
					collectionIdsUnidadeAtual.add(unidade.getId());
				}
				
				filtroRA.setCollectionIdsUnidadeAtual(collectionIdsUnidadeAtual);
			}
			
			colecaoRA = repositorioRegistroAtendimento.filtrarRA(filtroRA);
			
			if (colecaoRA == null || colecaoRA.isEmpty()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
				"atencao.filtrar_ra_consulta_vazia");
			} else {
				toReturn = colecaoRA;
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return toReturn;
	}
	
	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return integer - tamanho
	 * @throws ControladorException
	 */
	public Integer filtrarRegistroAtendimentoTamanho(
			FiltrarRegistroAtendimentoHelper filtroRA)
	throws ControladorException {
		
		try {
			if (filtroRA.getUnidadeSuperior() != null) {
				Integer idUnidadeSuperior = filtroRA.getUnidadeSuperior().getId();
				
				Collection<UnidadeOrganizacional> unidadesFilhas = getControladorAtendimentoPublico().pesquisarUnidadesFilhas(
						idUnidadeSuperior).getUnidades().values();
				
				Collection<Integer> collectionIdsUnidadeAtual = new ArrayList<Integer>();
				for (UnidadeOrganizacional unidade : unidadesFilhas) {
					collectionIdsUnidadeAtual.add(unidade.getId());
				}
				
				filtroRA.setCollectionIdsUnidadeAtual(collectionIdsUnidadeAtual);
			}
			
			return repositorioRegistroAtendimento.filtrarRATamanho(filtroRA);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento que estáo na unidade atual informada
	 * (existe ocorrência na tabela REGISTRO_ATENDIMENTO com TRAMITE=Id da
	 * Unidade Atual e maior TRAM_TMTRAMITE)
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<RegistroAtendimento>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaRAPorUnidadeAtual(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ControladorException {
		try {
			
			return repositorioRegistroAtendimento
			.recuperaRAPorUnidadeAtual(unidadeOrganizacional);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [SB003] Seleciona Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 09/08/2006
	 * 
	 * @param colecaoUnidadeSuperior
	 * @throws ControladorException
	 */
	/*
	 * private void recuperarListaRAPorUnidadeSubordinada( Collection<UnidadeOrganizacional>
	 * colecaoUnidadeSuperior) throws ControladorException { Collection<Integer>
	 * colecaoIdRA = null; Collection<UnidadeOrganizacional>
	 * colecaoUnidadesSubordinadas = null; for (Iterator iter =
	 * colecaoUnidadeSuperior.iterator(); iter.hasNext();) {
	 * UnidadeOrganizacional unidadeAtual = (UnidadeOrganizacional) iter
	 * .next(); try { colecaoIdRA = repositorioRegistroAtendimento
	 * .recuperaRAPorUnidadeAtual(unidadeAtual); if (colecaoIdRA != null &&
	 * !colecaoIdRA.isEmpty()) {
	 * colecaoRAPorUnidadeSuperior.addAll(colecaoIdRA); } } catch
	 * (ErroRepositorioException ex) { sessionContext.setRollbackOnly();
	 * ex.printStackTrace(); throw new ControladorException("erro.sistema", ex); }
	 * colecaoUnidadesSubordinadas = getControladorUnidade()
	 * .recuperarUnidadesSubordinadasPorUnidadeSuperior( unidadeAtual); if
	 * (colecaoUnidadesSubordinadas != null &&
	 * !colecaoUnidadesSubordinadas.isEmpty()) {
	 * recuperarListaRAPorUnidadeSubordinada(colecaoUnidadesSubordinadas); } } }
	 */
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0035] - Define Unidade Destino da Associação de Localidade e Especificação
	 * 
	 * 	Caso o sistema deva sugerir a unidade destino para o primeiro encaminhamento do 
	 * Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na tabela SISTEMA_PARAMETROS), definir a 
	 * Unidade Destino da Aba 2 - Dados do Local da Ocorrência de acordo com as regras abaixo:
	 * 		
	 * 1. Caso a exista uma Unidade de Destino associada à Localidade e à Especificação do 
	 * Registro de Atendimento (Existir ocorrência na tabela LOCAL_ESPEC_UNIDADE com LOCA_ID= Id da Localidade, 
	 * STEP_ID= Id da Especificação selecionada e UNID_ID<>nulo), definir prioritariamente a 
	 * unidade destino a partir desta associação da Localidade, Especificação e 
	 * Unidade (UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e 
	 * UNID_ID=UNID_ID da tabela LOCAL_ESPEC_UNIDADE com  LOCA_ID= Id da Localidade  e 
	 * STEP_ID=Id da Especificação selecionada) [FS0018 - Verificar existência de unidade centralizadora].
	 * 		
	 * 2. Caso contrário exista ocorrência na tabela LOCAL_ESPEC_UNIDADE segue o fluxo anterior
	 * O sistema deva sugerir a unidade destino para o primeiro
	 * encaminhamento do Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na
	 * tabela SISTEMA_PARAMETROS), definir a Unidade Destino da Localidade de
	 * acordo com as regras abaixo. Caso a Especificação não esteja associada a
	 * uma unidade (UNID_ID da tabela SOLICITACAO_TIPO_ESPECIFICACAO com o valor
	 * nulo): Caso o Tipo de Solicitação não seja relativoÁrea de esgoto
	 * (SOTG_ICESGOTO da tabela SOLICITACAO_TIPO_GRUPO com o valor
	 * correspondente a dois para SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com
	 * SOTP_ID=Id do Tipo de Solicitação selecionado), definir a unidade destino
	 * a partir da localidade informada/selecionada (UNID_ID e UNID_DSUNIDADE da
	 * tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da
	 * tabela LOCALIDADE_SOLIC_TIPO_GRUPO com LOCA_ID=Id da Localidade e
	 * SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com SOTP_ID=Id do Tipo de
	 * Solicitação selecionado) [FS0018Verificar existência de unidade
	 * centralizadora].
	 * 
	 * [SB0005] Define Unidade Destino da Localidade
	 * 
	 * @author Raphael Rossiter, Hugo Leonardo
	 * @date 04/08/2006, 01/12/2010
	 * 
	 * @param idLocalidade,
	 *            idSolicitacaoTipo, idSolicitacaoTipoEspecificacao
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoLocalidade(
			Integer idSolicitacaoTipoEspecificacao, Integer idLocalidade,
			Integer idSolicitacaoTipo, boolean solicitacaoTipoRelativoAreaEsgoto)
	throws ControladorException {
		
		UnidadeOrganizacional retorno = null;
		
		SistemaParametro sistemaParametro = this.getControladorUtil()
			.pesquisarParametrosDoSistema();
	
		/**
		 * ------------------------------------------------------------
		 * @Autor: Hugo Nascimento
		 * @Data: 01/12/2010
		 * RM_3326
		 * ------------------------------------------------------------
		 */
		// [SB0035] - Define Unidade Destino da Associação de Localidade e Especificação
		FiltroLocalidadeEspecificacaoUnidade filtroLocalidadeEspecificacaoUnidade = 
			new FiltroLocalidadeEspecificacaoUnidade();
		
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE_ID, idLocalidade));
		
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO_ID, 
						idSolicitacaoTipoEspecificacao));
		
		filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL);
		
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidadeEspecificacaoUnidade.INDICADOR_TRAMITE_UNIDADE_ORGANIZACIONAL, 
						ConstantesSistema.SIM));
		
		Collection colecaoLocalidadeEspecificacaoUnidade = 
			this.getControladorUtil().pesquisar( filtroLocalidadeEspecificacaoUnidade,
				LocalidadeEspecificacaoUnidade.class.getName());
		
		if ( !Util.isVazioOrNulo(colecaoLocalidadeEspecificacaoUnidade) ) {
			
			LocalidadeEspecificacaoUnidade localidadeEspecificacaoUnidade = 
				(LocalidadeEspecificacaoUnidade) Util.retonarObjetoDeColecao(colecaoLocalidadeEspecificacaoUnidade);
			
			if (sistemaParametro.getIndicadorSugestaoTramite() != null
					&& sistemaParametro.getIndicadorSugestaoTramite().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)){
				
				retorno = localidadeEspecificacaoUnidade.getComp_id().getUnidadeOrganizacional();
				
				if (retorno != null && retorno.getIndicadorTramite() == 1) {
					/*
					 * [UC0366] Inserir Registro de Atendimento [FS0018]
					 * Verificar existência de unidade centralizadora
					 */
					retorno = this.getControladorUnidade().verificarExistenciaUnidadeCentralizadora(retorno);
					
					return retorno;
				}
			}
		}
		/**
		 * -------------------------------------------------------------------
		 */
	
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = 
			new FiltroSolicitacaoTipoEspecificacao();
		

		filtroSolicitacaoTipoEspecificacao
		.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				idSolicitacaoTipoEspecificacao));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			if (sistemaParametro.getIndicadorSugestaoTramite() != null
					&& sistemaParametro.getIndicadorSugestaoTramite().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)
							&& solicitacaoTipoEspecificacao.getUnidadeOrganizacional() == null
							&& !solicitacaoTipoRelativoAreaEsgoto) {
				
				try {
					
					retorno = repositorioRegistroAtendimento
					.definirUnidadeDestinoLocalidade(idLocalidade,
							idSolicitacaoTipo);
					
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
				
				if (retorno != null) {
					
					/*
					 * [UC0366] Inserir Registro de Atendimento [FS0018]
					 * Verificar existência de unidade centralizadora
					 */
					retorno = this.getControladorUnidade()
					.verificarExistenciaUnidadeCentralizadora(retorno);
				}
				
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso o Tipo de Solicitação seja relativo Área de esgoto (SOTG_ICESGOTO da
	 * tabela SOLICITACAO_TIPO_GRUPO com o valor correspondente a um para
	 * SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com SOTP_ID=Id do Tipo de
	 * Solicitação selecionado). Caso a quadra esteja preenchida, obter a
	 * divisão de esgoto da quadra (DVES_ID e DVES_DSDIVISAOESGOTO da tabela
	 * DIVISAO_ESGOTO com DVES_ID=DVES_ID da tabela SISTEMA_ESGOTO com
	 * SESG_ID=SESG_ID da tabela BACIA com BACI_ID=BACI_ID da tabela QUADRA com
	 * QDRA_ID=Id da quadra informada/selecionada).
	 * 
	 * [SB0006] Obtém divisão de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param solicitacaoTipoRelativoAreaEsgoto,
	 *            idQuadra
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public DivisaoEsgoto obterDivisaoEsgoto(Integer idQuadra,
			boolean solicitacaoTipoRelativoAreaEsgoto)
	throws ControladorException {
		
		DivisaoEsgoto retorno = null;
		
		if (solicitacaoTipoRelativoAreaEsgoto) {
			
			try {
				
				retorno = repositorioRegistroAtendimento
				.obterDivisaoEsgoto(idQuadra);
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso informe a divisão de esgoto: Caso tenha informado a quadra e a mesma
	 * não pertença a divisão de esgoto informada (Id da divisão de esgoto é
	 * diferente de DVES_ID da tabela QUADRA com QDRA_ID=Id da quadra
	 * informada).
	 * 
	 * Caso tenha informado o setor comercial sem a quadra e o setor comercial
	 * não pertença divisão de esgoto informada (Id da divisão de esgoto é
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=Id do setor
	 * comercial informado).
	 * 
	 * Caso tenha informado a localidade sem o setor comercial e a localidade
	 * não pertença divisão de esgoto informada (Id da divisão de esgoto é
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=STCM_ID da
	 * tabela SETOR_COMERCIAL com LOCA_ID=Id da localidade informada).
	 * 
	 * [FS0013] Verificar compatibilidade entre divisão de esgoto e
	 * localidade/setor/quadra
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idLocalidade,
	 *            idSetorComercial, idQuadra, idDivisaoEsgoto
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
			Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			int idDivisaoEsgoto) throws ControladorException {
		
		DivisaoEsgoto divisaoEsgoto = null;
		
		if (idQuadra != null) {
			
			try {
				
				divisaoEsgoto = repositorioRegistroAtendimento
				.verificarCompatibilidadeDivisaoEsgotoQuadra(idQuadra,
						idDivisaoEsgoto);
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (divisaoEsgoto == null) {
				throw new ControladorException(
						"atencao.divisao_esgoto_nao_compativel_com_localidade_setor_quadra",
						null, "Quadra");
			}
			
		} else if (idSetorComercial != null) {
			
			try {
				
				divisaoEsgoto = repositorioRegistroAtendimento
				.verificarCompatibilidadeDivisaoEsgotoSetor(
						idSetorComercial, idDivisaoEsgoto);
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (divisaoEsgoto == null) {
				throw new ControladorException(
						"atencao.divisao_esgoto_nao_compativel_com_localidade_setor_quadra",
						null, "Setor Comercial");
			}
		} else if (idLocalidade != null) {
			
			try {
				
				divisaoEsgoto = repositorioRegistroAtendimento
				.verificarCompatibilidadeDivisaoEsgotoLocalidade(
						idLocalidade, idDivisaoEsgoto);
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (divisaoEsgoto == null) {
				throw new ControladorException(
						"atencao.divisao_esgoto_nao_compativel_com_localidade_setor_quadra",
						null, "Localidade");
			}
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0035] - Define Unidade Destino da Associação de Localidade e Especificação
	 * 
	 * 	Caso o sistema deva sugerir a unidade destino para o primeiro encaminhamento do 
	 * Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na tabela SISTEMA_PARAMETROS), definir a 
	 * Unidade Destino da Aba 2 - Dados do Local da Ocorrência de acordo com as regras abaixo:
	 * 		
	 * 1. Caso a exista uma Unidade de Destino associada à Localidade e à Especificação do 
	 * Registro de Atendimento (Existir ocorrência na tabela LOCAL_ESPEC_UNIDADE com LOCA_ID= Id da Localidade, 
	 * STEP_ID= Id da Especificação selecionada e UNID_ID<>nulo), definir prioritariamente a 
	 * unidade destino a partir desta associação da Localidade, Especificação e 
	 * Unidade (UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e 
	 * UNID_ID=UNID_ID da tabela LOCAL_ESPEC_UNIDADE com  LOCA_ID= Id da Localidade  e 
	 * STEP_ID=Id da Especificação selecionada) [FS0018 - Verificar existência de unidade centralizadora].
	 * 		
	 * 2. Caso não exista o sistema deva sugerir a unidade destino para o primeiro
	 * encaminhamento do Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na
	 * tabela SISTEMA_PARAMETROS).
	 * 
	 * Caso a Especificação não esteja associada a uma unidade (UNID_ID da
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com o valor nulo):
	 * 
	 * Caso o Tipo de Solicitação não seja relativo Área de esgoto
	 * (SOTG_ICESGOTO da tabela SOLICITACAO_TIPO_GRUPO com o valor
	 * correspondente a um para SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com
	 * SOTP_ID=Id do Tipo de Solicitação selecionado).
	 * 
	 * Caso a localidade informada/selecionada para o RA 
	 * esteja associada ao grupo  de esgoto(
	 * existe ocorrencia na tabela LOCALIDADE_SOLIC_TIPO_GRUPO 
	 * para LOCA_ID = id da Localidade e SOTG_ID=SOTG_ID da tabela
	 * SOLICITACAO_TIPO com SOTP_ID = id do tipo de solicitacaoselecionado), 
	 * definir a unidade destino a partir da localidade(
	 * UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1
	 * e UNID_ID=UNID_ID da tabela LOCALIDADE_SOLIC_TIPO_GRUPO)
	 * 
	 * Caso contrario, Definir a unidade destino a partir da divisão de esgoto
	 * informada/selecionada (UNID_ID e UNID_DSUNIDADE da tabela
	 * UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da tabela
	 * DIVISAO_ESGOTO com DVES_ID=Id da divisão selecionada) [FS0018 Verificar
	 * existência de unidade centralizadora].
	 * 
	 * [SB0007] Define Unidade Destino da divisão de Esgoto
	 * 
	 * @author Raphael Rossiter, Vivianne Sousa, Hugo Leonardo
	 * @date 08/08/2006, 04/11/2008, 01/12/2010
	 * 
	 * @param idDivisaoEsgoto,
	 *            idSolicitacaoTipoEspecificacao,
	 *            solicitacaoTipoRelativoAreaEsgoto
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoDivisaoEsgoto(
			Integer idSolicitacaoTipoEspecificacao, Integer idDivisaoEsgoto,
			boolean solicitacaoTipoRelativoAreaEsgoto,
			Integer idLocalidade, Integer idSolicitacaoTipo)
	throws ControladorException {
		
		UnidadeOrganizacional retorno = null;
		
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		/**
		 * ------------------------------------------------------------
		 * @Autor: Hugo Nascimento
		 * @Data: 01/12/2010
		 * RM_3326
		 * ------------------------------------------------------------
		 */
		// [SB0035] - Define Unidade Destino da Associação de Localidade e Especificação
		FiltroLocalidadeEspecificacaoUnidade filtroLocalidadeEspecificacaoUnidade = 
			new FiltroLocalidadeEspecificacaoUnidade();
		
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE_ID, idLocalidade));
		
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO_ID, 
						idSolicitacaoTipoEspecificacao));
		
		filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL);
		
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidadeEspecificacaoUnidade.INDICADOR_TRAMITE_UNIDADE_ORGANIZACIONAL, 
						ConstantesSistema.SIM));
		
		Collection colecaoLocalidadeEspecificacaoUnidade = 
			this.getControladorUtil().pesquisar( filtroLocalidadeEspecificacaoUnidade,
				LocalidadeEspecificacaoUnidade.class.getName());
		
		if ( !Util.isVazioOrNulo(colecaoLocalidadeEspecificacaoUnidade) ) {
			
			LocalidadeEspecificacaoUnidade localidadeEspecificacaoUnidade = 
				(LocalidadeEspecificacaoUnidade) Util.retonarObjetoDeColecao(colecaoLocalidadeEspecificacaoUnidade);
			
			if (sistemaParametro.getIndicadorSugestaoTramite() != null
					&& sistemaParametro.getIndicadorSugestaoTramite().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)){
				
				retorno = localidadeEspecificacaoUnidade.getComp_id().getUnidadeOrganizacional();
				
				if (retorno != null && retorno.getIndicadorTramite() == 1) {
					/*
					 * [UC0366] Inserir Registro de Atendimento [FS0018]
					 * Verificar existência de unidade centralizadora
					 */
					retorno = this.getControladorUnidade().verificarExistenciaUnidadeCentralizadora(retorno);
					
					return retorno;
				}
			}
		}
		/**
		 * -------------------------------------------------------------------
		 */
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID, idSolicitacaoTipoEspecificacao));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			if (sistemaParametro.getIndicadorSugestaoTramite() != null
					&& sistemaParametro.getIndicadorSugestaoTramite().equals(ConstantesSistema.INDICADOR_USO_ATIVO)
					&& solicitacaoTipoEspecificacao.getUnidadeOrganizacional() == null
					&& solicitacaoTipoRelativoAreaEsgoto) {
				
				try {
					
					//alterado por Vivianne Sousa, 04/11/2008
					//analista responsavel: Fatima Sampaio
					// 1.1.1.1 caso a localidade informada/selecionada para o RA 
					// esteja associada ao grupo  de esgoto
					retorno = repositorioRegistroAtendimento.definirUnidadeDestinoLocalidade(idLocalidade,idSolicitacaoTipo);
					
					// 1.1.1.2 caso contrario...
					if (retorno == null){
						retorno = repositorioRegistroAtendimento.definirUnidadeDestinoDivisaoEsgoto(idDivisaoEsgoto);
					}
					
					
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
				
				if (retorno != null) {
					
					/*
					 * [UC0366] Inserir Registro de Atendimento [FS0018]
					 * Verificar existência de unidade centralizadora
					 */
					retorno = this.getControladorUnidade()
					.verificarExistenciaUnidadeCentralizadora(retorno);
				}
				
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a unidade destino informada não possa receber registros de
	 * atendimento (UNID_ICTRAMITE=2 na tabela UNIDADE_ORGANIZACIONAL com
	 * UNID_ID=Id da unidade destino informada).
	 * caso a unidade destino informada nao esteja ATIVA (UNID_IUSO=2 na
	 * tabela UNIDADE_ORGANIZACIONAL com UNID_ID= id da unidade destino
	 * informada).
	 * 
	 * [FS0021] - Verificar possibilidade de encaminhamento para a unidade
	 * destino
	 * 
	 * @author Raphael Rossiter, Anderson Italo
	 * @date 08/08/2006, 17/07/2009
	 * 
	 * @param UnidadeOrganizacional
	 * @return void
	 * @throws ControladorException
	 */
	public void verificaPossibilidadeEncaminhamentoUnidadeDestino(
			UnidadeOrganizacional unidadeDestino) throws ControladorException {
		
		if (unidadeDestino != null
				&& unidadeDestino.getIndicadorTramite() == ConstantesSistema.INDICADOR_USO_DESATIVO
				.shortValue()) {
			throw new ControladorException(
			"atencao.unidade_destino_nao_possivel_encaminhamento");
			
		}
		
		if (unidadeDestino != null 
				&& unidadeDestino.getIndicadorUso() == ConstantesSistema.INDICADOR_USO_DESATIVO
				.shortValue()) {
			throw new ControladorException(
			"atencao.unidade_destino_inativa", null, unidadeDestino.getId().toString());
			
		}
	}
	
	/**
	 * [UC0399] Inserir Tipo de Solicitação com Especificações
	 * 
	 * Verifica se o Serviço tipo tem como Serviço automatico geração
	 * automática.
	 * 
	 * [SF0003] Validar Tipo de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void verificarServicoTipoReferencia(Integer idServicoTipo)
	throws ControladorException {
		try {
			Integer idServicoTipoNaBase = repositorioRegistroAtendimento
			.verificarServicoTipoReferencia(idServicoTipo);
			if (idServicoTipoNaBase != null && !idServicoTipoNaBase.equals("")) {
				throw new ControladorException(
				"atencao.tipo.servico.nao.geracao.automatica");
			}
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0399] Inserir Tipo de Solicitação com Especificações
	 * 
	 * Verifica se na coleção existe algum ordem de execução .
	 * 
	 * [SF0004] Validar valor ordem execução parte
	 * 
	 * @author Sávio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaOrdemExecucao(
			Collection colecaoEspecificacaoServicoTipo, Short ordemExecucao)
	throws ControladorException {
		if (colecaoEspecificacaoServicoTipo != null
				&& !colecaoEspecificacaoServicoTipo.isEmpty()) {
			Iterator iteratorEspecificacaoServicoTipo = colecaoEspecificacaoServicoTipo
			.iterator();
			while (iteratorEspecificacaoServicoTipo.hasNext()) {
				EspecificacaoServicoTipo especificacaoServicoTipo = (EspecificacaoServicoTipo) iteratorEspecificacaoServicoTipo
				.next();
				if (especificacaoServicoTipo.getOrdemExecucao() != null
						&& !especificacaoServicoTipo.equals("")) {
					
					if (especificacaoServicoTipo.getOrdemExecucao().equals(
							ordemExecucao)) {
						throw new ControladorException(
								"atencao.valor.ordem.execucao.ja.existe", null,
								"" + ordemExecucao);
					}
				}
			}
		}
	}
	
	/**
	 * [UC0399] Inserir Tipo de Solicitação com Especificações
	 * 
	 * Verifica se na coleção existe algum ordem de execução fora da
	 * ordem(1,2,3,4,5,6).Ex.:não exista numero 2.
	 * 
	 * [SF0004] Validar valor ordem execução 2 parte
	 * 
	 * @author Sávio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void verificarOrdemExecucaoForaOrdem(
			Collection colecaoEspecificacaoServicoTipo)
	throws ControladorException {
		if (colecaoEspecificacaoServicoTipo != null
				&& !colecaoEspecificacaoServicoTipo.isEmpty()) {
			// ordena a coleção pela ordem de execução
			Collections.sort((List) colecaoEspecificacaoServicoTipo,
					new Comparator() {
				public int compare(Object a, Object b) {
					Short codigo1 = ((EspecificacaoServicoTipo) a)
					.getOrdemExecucao();
					Short codigo2 = ((EspecificacaoServicoTipo) b)
					.getOrdemExecucao();
					if (codigo1 == null || codigo1.equals("")) {
						return -1;
					} else {
						return codigo1.compareTo(codigo2);
					}
				}
			});
			
			boolean primeiraVez = true;
			Integer ordemExecucaoProximo = null;
			
			Iterator iteratorEspecificacaoServicoTipo = colecaoEspecificacaoServicoTipo
			.iterator();
			while (iteratorEspecificacaoServicoTipo.hasNext()) {
				EspecificacaoServicoTipo especificacaoServicoTipo = (EspecificacaoServicoTipo) iteratorEspecificacaoServicoTipo
				.next();
				// caso a ordem de execução seja diferente de nulo
				if (especificacaoServicoTipo.getOrdemExecucao() != null
						&& !especificacaoServicoTipo.getOrdemExecucao().equals(
						"")) {
					Integer ordemExecucao = new Integer(
							especificacaoServicoTipo.getOrdemExecucao());
					if (primeiraVez) {
						ordemExecucaoProximo = ordemExecucao;
						primeiraVez = false;
					}
					
					if (ordemExecucao.equals(ordemExecucaoProximo)) {
						ordemExecucaoProximo = ordemExecucaoProximo + 1;
						
					} else {
						throw new ControladorException(
								"atencao.valor.ordem.execucao.fora.sequencia",
								null, "" + ordemExecucao);
					}
				}
				
			}
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0020] Verifica Situação do Imóvel e Especificação
	 * 
	 * @author Raphael Rossiter
	 * @date 10/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarSituacaoImovelEspecificacao(Imovel imovel,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		Collection colecaoEspecificacaoImovSitCriterio = null;
		
		try {
			
			colecaoEspecificacaoImovSitCriterio = this.repositorioRegistroAtendimento
			.verificarSituacaoImovelEspecificacao(idSolicitacaoTipoEspecificacao);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (colecaoEspecificacaoImovSitCriterio != null
				&& !colecaoEspecificacaoImovSitCriterio.isEmpty()) {
			
			// [UC0409 Obter Indicador de existência de hidrômetro na Ligação
			// de Água e poço
			ObterIndicadorExistenciaHidrometroHelper indicadorHidrometroAguaPoco = this
			.obterIndicadorExistenciaHidrometroLigacaoAguaPoco(imovel
					.getId());
			
			Short hidrometroLigacaoAguaImovel = indicadorHidrometroAguaPoco
			.getIndicadorLigacaoAgua();
			Short hidrometroLigacaoPocoImovel = indicadorHidrometroAguaPoco
			.getIndicadorPoco();
			
			String comSemHidrometroLigacaoAgua = "SEM";
			String comSemHidrometroLigacaoPoco = "SEM";
			
			if (hidrometroLigacaoAguaImovel != null
					&& hidrometroLigacaoAguaImovel
					.equals(ConstantesSistema.SIM)) {
				comSemHidrometroLigacaoAgua = "COM";
			}
			
			if (hidrometroLigacaoPocoImovel != null
					&& hidrometroLigacaoPocoImovel
					.equals(ConstantesSistema.SIM)) {
				comSemHidrometroLigacaoPoco = "COM";
			}
			
			Iterator especificacaoImovSitCriterioIterator = colecaoEspecificacaoImovSitCriterio
			.iterator();
			
			Object[] arrayEspecificacaoImovSitCriterio = null;
			
			Integer idSituacaoAguaCriterio = null;
			Integer idSituacaoEsgotoCriterio = null;
			Short hidrometroLigacaoAguaCriterio = null;
			Short hidrometroLigacaoPocoCriterio = null;
			String descricaoEspecificacao = null;
			
			/*
			 * Unão dos dois ïúltimos parâmetros (O Arquivo erro.jsp só
			 *	aceita um total de quatro parâmetros)
			 */			 
			String concatenacao = comSemHidrometroLigacaoAgua
			+ " hidrômetro na ligação de Água. Imóvel "
			+ comSemHidrometroLigacaoPoco
			+ " hidrômetro na ligação de esgoto.";
			
			//boolean correspondencia = false;
			
			boolean primeiraCondicao = false;
			boolean segundaCondicao = false;
			boolean terceiraCondicao = false;
			boolean quartaCondicao = false;
			
			while (especificacaoImovSitCriterioIterator.hasNext()) {
				
				arrayEspecificacaoImovSitCriterio = (Object[]) especificacaoImovSitCriterioIterator
				.next();
				
				idSituacaoAguaCriterio = (Integer) arrayEspecificacaoImovSitCriterio[0];
				idSituacaoEsgotoCriterio = (Integer) arrayEspecificacaoImovSitCriterio[1];
				hidrometroLigacaoAguaCriterio = (Short) arrayEspecificacaoImovSitCriterio[2];
				hidrometroLigacaoPocoCriterio = (Short) arrayEspecificacaoImovSitCriterio[3];
				descricaoEspecificacao = (String) arrayEspecificacaoImovSitCriterio[4];
				
				
				
				//LigacaoAguaSituacao
				if (idSituacaoAguaCriterio != null
						&& imovel.getLigacaoAguaSituacao().getId().equals(idSituacaoAguaCriterio)) {
					
					primeiraCondicao = true;
				} else if (idSituacaoAguaCriterio == null){
					primeiraCondicao = true;
				}
				
				
				// LigacaoEsgotoSituacao
				if (idSituacaoEsgotoCriterio != null 
						&& imovel.getLigacaoEsgotoSituacao().getId().equals(idSituacaoEsgotoCriterio)) {
					segundaCondicao = true;
				}else if (idSituacaoEsgotoCriterio == null){
					segundaCondicao = true;
				}
				
				// hidrometroLigacaoAguaCriterio
				if (hidrometroLigacaoAguaCriterio != null
						&& hidrometroLigacaoAguaImovel.equals(hidrometroLigacaoAguaCriterio)) {
					
					terceiraCondicao = true;
				} else if (hidrometroLigacaoAguaCriterio == null){
					terceiraCondicao = true;
				}
				
				// hidrometroLigacaoPocoCriterio
				if (hidrometroLigacaoPocoCriterio != null
						&& hidrometroLigacaoPocoImovel.equals(hidrometroLigacaoPocoCriterio)) {
					
					quartaCondicao = true;
				}else if (hidrometroLigacaoPocoCriterio == null ){
					quartaCondicao = true;
				}
			}
			
			//[FS0034] - Verificar correspondência entre os dados do imóvel para a especificação
			if ( !primeiraCondicao || !segundaCondicao || !terceiraCondicao || !quartaCondicao) {
				
				throw new ControladorException(
						"atencao.imovel_especificacao_correspondencia_invalida",
						null, imovel.getId().toString(),
						descricaoEspecificacao, imovel.getLigacaoAguaSituacao()
						.getDescricao(), imovel
						.getLigacaoEsgotoSituacao().getDescricao(),
						concatenacao);
			}
			
		}
		
	}
	
	/**
	 * [UC0418] Obter Unidade Atual do RA
	 * 
	 * Este caso de uso permite obter a unidade atual de um registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 03/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeAtualRA(
			Integer idRegistroAtendimento) throws ControladorException {
		
		UnidadeOrganizacional retorno = null;
		try {
			
			// verifica a unidade atual(unid_iddestino) do registro de
			// atendimento pelo último Trâmite efetuado
			retorno = repositorioRegistroAtendimento
			.verificaUnidadeAtualRA(idRegistroAtendimento);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC0419] Obter Indicador de Autorização para Manutenção do RA
	 * 
	 * Este caso de uso Obtém o indicador de autorização para manutenção do RA,
	 * ou seja, se o usuário tem autorização para efetuar a manutenção do RA
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * @param idUnidadeOrganizacional
	 * @param idUusuario
	 * @throws ControladorException
	 */
	public Short obterIndicadorAutorizacaoManutencaoRA(Integer idUnidadeOrganizacional, 
		Integer idUsuario) throws ControladorException {
		
		// Atribui o valor 2-não ao indicador
		Short retorno = ConstantesSistema.NAO;
		
		// Se o usuário não tenha permissão especial.
		Usuario usuario = new Usuario();
		usuario.setId(new Integer(idUsuario));
		
		boolean temPermissaoParaTramitarParaQualquerUnidade = 
			this.getControladorPermissaoEspecial().verificarPermissaoEspecial(
				PermissaoEspecial.TRAMITAR_RA_PARA_QUALQUER_UNIDADE, 
				usuario);	

		//Caso o usuário possua permissão especial, 
		//ele pode tramitar para qualquer unidade
		
		if(temPermissaoParaTramitarParaQualquerUnidade){
			retorno = ConstantesSistema.SIM;
		}else {
			try {
				
				Object[] dadosUnidade = null;
				
				if (idUsuario != null) {
					
					// Pesquisar a unidade organizacional do usúario, 
					// o indicador da unidade de central de atendimento
					// e a unidade superior
					dadosUnidade = 
						this.repositorioRegistroAtendimento.pesquisarUnidadeOrganizacionalUsuario(idUsuario);
					
					Integer idUnidadeSuperior = 
						this.repositorioRegistroAtendimento.verificaUnidadeSuperiorUnidade(idUnidadeOrganizacional);
					
					if (dadosUnidade != null) {
						
						Integer idUnidadeOrganizacionalUsuario = (Integer) dadosUnidade[0];
						Short indicadorCentralAtendimento = (Short) dadosUnidade[1];
						
						/*
						 * Caso a unidade do RA corresponda unidade de lotação do
						 * usuário, atribuir o valor 1-sim
						 */
						if (idUnidadeOrganizacional != null && 
							idUnidadeOrganizacional.equals(idUnidadeOrganizacionalUsuario)) {
							
							retorno = ConstantesSistema.SIM;
							
							/*
							 * Caso a unidade de lotação do usuário corresponda
							 * unidade de central de atendimento ao cliente(com
							 * valor 1), atribuir o valor 1-sim
							 */
						} else if (indicadorCentralAtendimento != null && 
									indicadorCentralAtendimento.equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {
							
							retorno = ConstantesSistema.SIM;
						} else if (idUnidadeSuperior != null) {
							/*
							 * Caso a unidade de lotação do usuário corresponda
							 * unidade superior, atribuir o valor 1-sim ao indicador
							 * de unidade subordinada
							 */
							while (idUnidadeSuperior != null) {
								
								if (idUnidadeOrganizacionalUsuario != null && 
									idUnidadeOrganizacionalUsuario.equals(idUnidadeSuperior)) {
									
									retorno = ConstantesSistema.SIM;
									break;
								}
								idUnidadeSuperior = 
									this.repositorioRegistroAtendimento.verificaUnidadeSuperiorUnidade(idUnidadeSuperior);
							}
						}
					}
				}
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		return retorno;
	}
	
	/**
	 * [UC0420] Obter Descrição da situação da RA
	 * 
	 * Este caso de uso permite obter a descrição de um registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 04/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRA(
			Integer idRegistroAtendimento) throws ControladorException {
		
		ObterDescricaoSituacaoRAHelper retorno = new ObterDescricaoSituacaoRAHelper();
		try {
			
			// verifica o Código da situação(RGAT_CDSITUACAO) do registro de
			// atendimento
			Short situacao = repositorioRegistroAtendimento
			.verificaSituacaoRA(idRegistroAtendimento);
			
			if (situacao != null) {
				// caso o valor igual a 1
				if (situacao.equals(RegistroAtendimento.SITUACAO_PENDENTE)) {
					retorno
					.setCodigoSituacao(RegistroAtendimento.SITUACAO_PENDENTE);
					retorno
					.setDescricaoSituacao(RegistroAtendimento.SITUACAO_DESCRICAO_PENDENTE);
					retorno
					.setDescricaoAbreviadaSituacao(RegistroAtendimento.SITUACAO_DESC_ABREV_PENDENTE);
					// caso o valor igual a 2
				} else if (situacao
						.equals(RegistroAtendimento.SITUACAO_ENCERRADO)) {
					retorno
					.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
					retorno
					.setDescricaoSituacao(RegistroAtendimento.SITUACAO_DESCRICAO_ENCERRADO);
					retorno
					.setDescricaoAbreviadaSituacao(RegistroAtendimento.SITUACAO_DESC_ABREV_ENCERRADO);
					// caso o valor igual a 3
				} else if (situacao
						.equals(RegistroAtendimento.SITUACAO_BLOQUEADO)) {
					retorno
					.setCodigoSituacao(RegistroAtendimento.SITUACAO_BLOQUEADO);
					retorno
					.setDescricaoSituacao(RegistroAtendimento.SITUACAO_DESCRICAO_BLOQUEADO);
					retorno
					.setDescricaoAbreviadaSituacao(RegistroAtendimento.SITUACAO_DESC_ABREV_BLOQUEADO);
				}
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC0421] Obter Unidade de Atendimento do RA
	 * 
	 * Este caso de uso permite obter a unidade de atendimento de um registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 04/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeAtendimentoRA(
			Integer idRegistroAtendimento) throws ControladorException {
		
		UnidadeOrganizacional retorno = null;
		try {
			
			// verificar unidade de atendimento do registro de atendimento
			retorno = repositorioRegistroAtendimento
			.verificaUnidadeAtendimentoRA(idRegistroAtendimento,
					AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC0422] Obter endereço da ocorrência do RA
	 * 
	 * Este caso de uso permite obter o endereço da ocorrência de um registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String obterEnderecoOcorrenciaRA(Integer idRegistroAtendimento)
	throws ControladorException {
		
		String enderecoOcorrencia = null;
		
		String enderecoDescritivo = this
		.obterEnderecoDescritivoRA(idRegistroAtendimento);
		if (enderecoDescritivo != null && !enderecoDescritivo.equals("")) {
			enderecoOcorrencia = enderecoDescritivo;
		} else {
			try {
				
				Object[] dadosRegistroAtendimento = null;
				
				// verifica o Código do logradourobairro e o Código do Imóvel no
				// registro atendimento
				dadosRegistroAtendimento = repositorioRegistroAtendimento
				.verificaEnderecoOcorrenciaRA(idRegistroAtendimento);
				
				if (dadosRegistroAtendimento != null) {
					
					Integer idLogradouroBairro = (Integer) dadosRegistroAtendimento[0];
					Integer idImovel = (Integer) dadosRegistroAtendimento[1];
					/*
					 * Caso o registro de atendimento esteja associado a um
					 * logradouro, obter o endereço da ocorrência a partir do
					 * registro de atendimento
					 */
					if (idLogradouroBairro != null
							&& !idLogradouroBairro.equals("")) {
						enderecoOcorrencia = this.getControladorEndereco()
						.pesquisarEnderecoRegistroAtendimentoFormatado(
								idRegistroAtendimento);
						// Caso contrário, obter a endereço da ocorrência a
						// partir
						// do Imóvel associado ao RA
					} else if (idImovel != null && !idImovel.equals("")) {
						enderecoOcorrencia = this.getControladorEndereco()
						.pesquisarEnderecoFormatado(idImovel);
					}
				}
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		return enderecoOcorrencia;
	}
	
	/**
	 * [UC0423] Obter endereço do Solicitante do RA
	 * 
	 * Este caso de uso permite obter o endereço do solicitante de um registro
	 * de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String obterEnderecoSolicitanteRA(
			Integer idRegistroAtendimentoSolicitante)
	throws ControladorException {
		
		String enderecoSolicitante = null;
		
		String enderecoDescritivo = this
		.obterEnderecoDescritivoRA(idRegistroAtendimentoSolicitante);
		if (enderecoDescritivo != null && !enderecoDescritivo.equals("")) {
			enderecoSolicitante = enderecoDescritivo;
		} else {
			try {
				
				Object[] dadosRegistroAtendimentoSolicitante = null;
				
				// verifica o Código do logradourobairro e o Código do cliente
				// no
				// registro atendimento solicitante
				dadosRegistroAtendimentoSolicitante = repositorioRegistroAtendimento
				.verificaEnderecoRASolicitante(idRegistroAtendimentoSolicitante);
				
				if (dadosRegistroAtendimentoSolicitante != null) {
					
					Integer idLogradouroBairro = (Integer) dadosRegistroAtendimentoSolicitante[0];
					Integer idCliente = (Integer) dadosRegistroAtendimentoSolicitante[1];
					/*
					 * Caso o solicitante do registro de atendimento esteja
					 * associado a um logradouro, obter o endereço do
					 * solicitante a partir do solicitante do registro de
					 * atendimento
					 */
					if (idLogradouroBairro != null
							&& !idLogradouroBairro.equals("")) {
						enderecoSolicitante = this
						.getControladorEndereco()
						.pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(
								idRegistroAtendimentoSolicitante);
						// Caso contrário, obter a endereço da ocorrência a
						// partir
						// do cliente associado ao solicitante do RA
					} else {
						if (idCliente != null && !idCliente.equals("")) {
							enderecoSolicitante = this.getControladorEndereco()
							.pesquisarEnderecoClienteAbreviado(
									idCliente);
						}
					}
				}
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		return enderecoSolicitante;
	}
	
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Este caso de uso permite obter o registro de atendimento associado a
	 * outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterRAAssociadoHelper obterRAAssociadoConsultarRA(Integer idRegistroAtendimento)
	throws ControladorException {
		ObterRAAssociadoHelper retorno = new ObterRAAssociadoHelper();
		
		// Atribui o valor 0-SEM RA ASSOCIADO ao Código de existência de RA
		// associado
		retorno
		.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_SEM_RA);
		
		// Atribui o valor nulo ao registro de atendimento associado
		retorno.setRegistroAtendimentoAssociado(null);
		try {
			
			Object[] dadosRegistroAtendimentoDuplicidade = repositorioRegistroAtendimento
			.verificaDuplicidadeRegistroAtendimentoConsultarRA(idRegistroAtendimento);
			
			Object[] dadosRegistroAtendimentoReativado = repositorioRegistroAtendimento
			.verificaRegistroAtendimentoReativadoConsultarRA(idRegistroAtendimento);
			
			Object[] dadosRegistroAtendimentoReativacao = repositorioRegistroAtendimento
			.verificaRegistroAtendimentoReativacaoConsultarRA(idRegistroAtendimento);
			
			/*
			 * Caso o registro de atendimento esteja associado a outro em razão
			 * de duplicidade, obter o registro de atendimento associado a
			 * partir do registro de atendimento que representa a duplicidade e
			 * atribuir o valor 1-COM RA DE REFERÊNCIA ao ao Código de
			 * existência
			 */
			if (dadosRegistroAtendimentoDuplicidade != null) {
				
				RegistroAtendimento raDuplicidade = setDadosRegistroAtendimentoAssociadoConsultarRA(dadosRegistroAtendimentoDuplicidade);
				
				retorno
				.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA);
				
				retorno
				.setRegistroAtendimentoAssociado(raDuplicidade);
				
			} else if (dadosRegistroAtendimentoReativado != null) {
				
				/*
				 * Caso o registro atendimento tenha sido reativado, obter o
				 * registro de atendimento associado a partir do registro de
				 * atendimento que representa a última reativação realizada e
				 * atribuir o valor 2-COM RA ATUAL ao Código de existência de RA
				 * associado
				 */
				while (dadosRegistroAtendimentoReativado != null) {
					
					RegistroAtendimento raReativado = setDadosRegistroAtendimentoAssociadoConsultarRA(dadosRegistroAtendimentoReativado);
					
					retorno
					.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL);
					retorno
					.setRegistroAtendimentoAssociado(raReativado);
					
					dadosRegistroAtendimentoReativado = repositorioRegistroAtendimento
					.verificaRegistroAtendimentoReativadoConsultarRA(raReativado
							.getId());
				}
				
				/*
				 * Caso o registro de atendimento seja reativação de outro,
				 * obter o registro de atendimento associado a partir do
				 * registro de atendimento que foi reativado e atribuir o valor
				 * 3-COM RA ANTERIOR ao Código de existência de RA associado
				 */
			} else if (dadosRegistroAtendimentoReativacao != null) {
				RegistroAtendimento raReativacao = setDadosRegistroAtendimentoAssociadoConsultarRA(dadosRegistroAtendimentoReativacao);
				
				retorno
				.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_RA_ANTERIOR);
				retorno
				.setRegistroAtendimentoAssociado(raReativacao);
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	
	private RegistroAtendimento setDadosRegistroAtendimentoAssociadoConsultarRA(Object[] dadosRegistroAtendimentoDuplicidade) {
		RegistroAtendimento raReativado = new RegistroAtendimento();
		
		if (dadosRegistroAtendimentoDuplicidade[0] != null) {
			raReativado.setId((Integer) dadosRegistroAtendimentoDuplicidade[0]);
		}
		
		if (dadosRegistroAtendimentoDuplicidade[1] != null) {
			raReativado.setCodigoSituacao((Short) dadosRegistroAtendimentoDuplicidade[1]);
		}
		
		if (dadosRegistroAtendimentoDuplicidade[2] != null) {
			RaMotivoReativacao raMotivoReativacao = new RaMotivoReativacao();
			raMotivoReativacao.setId((Integer) dadosRegistroAtendimentoDuplicidade[2]);
			raMotivoReativacao.setDescricao((String) dadosRegistroAtendimentoDuplicidade[3]);
			raReativado.setRaMotivoReativacao(raMotivoReativacao);
		}
		
		if (dadosRegistroAtendimentoDuplicidade[4] != null) {
			raReativado.setRegistroAtendimento((Date) dadosRegistroAtendimentoDuplicidade[4]);
		}
		
		if (dadosRegistroAtendimentoDuplicidade[5] != null) {
			raReativado.setDataPrevistaAtual((Date) dadosRegistroAtendimentoDuplicidade[5]);
		}
		return raReativado;
	}
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Este caso de uso permite obter o registro de atendimento associado a
	 * outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterRAAssociadoHelper obterRAAssociado(Integer idRegistroAtendimento)
	throws ControladorException {
		ObterRAAssociadoHelper retorno = new ObterRAAssociadoHelper();
		
		// Atribui o valor 0-SEM RA ASSOCIADO ao Código de existência de RA
		// associado
		retorno
		.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_SEM_RA);
		
		// Atribui o valor nulo ao registro de atendimento associado
		retorno.setRegistroAtendimentoAssociado(null);
		try {
			
			RegistroAtendimento registroAtendimentoDuplicidade = repositorioRegistroAtendimento
			.verificaDuplicidadeRegistroAtendimento(idRegistroAtendimento);
			
			RegistroAtendimento registroAtendimentoReativado = repositorioRegistroAtendimento
			.verificaRegistroAtendimentoReativado(idRegistroAtendimento);
			
			RegistroAtendimento registroAtendimentoReativacao = repositorioRegistroAtendimento
			.verificaRegistroAtendimentoReativacao(idRegistroAtendimento);
			
			/*
			 * Caso o registro de atendimento esteja associado a outro em razão
			 * de duplicidade, obter o registro de atendimento associado a
			 * partir do registro de atendimento que representa a duplicidade e
			 * atribuir o valor 1-COM RA DE REFERÊNCIA ao ao Código de
			 * existência
			 */
			if (registroAtendimentoDuplicidade != null) {
				retorno
				.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA);
				
				retorno
				.setRegistroAtendimentoAssociado(registroAtendimentoDuplicidade);
				
			} else if (registroAtendimentoReativado != null) {
				
				/*
				 * Caso o registro atendimento tenha sido reativado, obter o
				 * registro de atendimento associado a partir do registro de
				 * atendimento que representa a última reativação realizada e
				 * atribuir o valor 2-COM RA ATUAL ao Código de existência de RA
				 * associado
				 */
				while (registroAtendimentoReativado != null) {
					retorno
					.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL);
					retorno
					.setRegistroAtendimentoAssociado(registroAtendimentoReativado);
					
					registroAtendimentoReativado = repositorioRegistroAtendimento
					.verificaRegistroAtendimentoReativado(registroAtendimentoReativado
							.getId());
				}
				
				/*
				 * Caso o registro de atendimento seja reativação de outro,
				 * obter o registro de atendimento associado a partir do
				 * registro de atendimento que foi reativado e atribuir o valor
				 * 3-COM RA ANTERIOR ao Código de existência de RA associado
				 */
			} else if (registroAtendimentoReativacao != null) {
				retorno
				.setCodigoExistenciaRAAssociado(RegistroAtendimento.CODIGO_ASSOCIADO_RA_ANTERIOR);
				retorno
				.setRegistroAtendimentoAssociado(registroAtendimentoReativacao);
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0434] Obter Unidade de Encerramento do RA
	 * 
	 * Este caso de uso permite obter a unidade de encerramento de um registro
	 * de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeEncerramentoRA(
			Integer idRegistroAtendimento) throws ControladorException {
		
		UnidadeOrganizacional retorno = null;
		try {
			
			// verificar unidade de atendimento do registro de atendimento
			retorno = repositorioRegistroAtendimento
			.verificaUnidadeAtendimentoRA(idRegistroAtendimento,
					AtendimentoRelacaoTipo.ENCERRAR);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC0445] Obter Nome do Solicitante do RA
	 * 
	 * Este caso de uso permite obter o nome do solicitante de um registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 09/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public String obterNomeSolicitanteRA(
			Integer idRegistroAtendimento)
	throws ControladorException {
		
		String retorno = null;
		try {
			Object[] dadosRegistroAtendimento = null;
			
			if (idRegistroAtendimento != null) {
				
				// Pesquisar a unidade organizacional do usuário e o indicador
				// da unidade de central de atendimento
				dadosRegistroAtendimento = repositorioRegistroAtendimento
				.pesquisarRegistroAtendimentoSolicitante(idRegistroAtendimento);
				
				if (dadosRegistroAtendimento != null) {
					String nomeSolicitante = (String) dadosRegistroAtendimento[0];
					Integer idCliente = (Integer) dadosRegistroAtendimento[1];
					String nomeCliente = (String) dadosRegistroAtendimento[2];
					Integer idUnidade = (Integer) dadosRegistroAtendimento[3];
					String nomeFuncionario = (String) dadosRegistroAtendimento[4];
					
					/*
					 * Caso o solicitante do registro de atendimento seja um
					 * cliente, obter o nome do solicitante a partir do nome do
					 * cliente
					 */
					if (idCliente != null) {
						retorno = nomeCliente;
						/*
						 * Caso o solicitante do registro de atendimento seja um
						 * unidade, obter o nome do solicitante a partir do nome
						 * do Funcionário Responsável
						 */
					} else if (idUnidade != null) {
						retorno = nomeFuncionario;
						// Caso contrário obter o nome do solicitante a partir
						// dos dados do solicitante
					} else {
						retorno = nomeSolicitante;
					}
				}
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC0424] Consultar Registro Atendimento
	 * 
	 * Retorno o Tramite mais atual a partir
	 * 
	 * @author Rafael Pinto
	 * @date 10/08/2006
	 * 
	 * @param idRA
	 * @return Tramite
	 * @throws ErroRepositorioException
	 */
	public Tramite recuperarTramiteMaisAtualPorRA(Integer idRA)
	throws ControladorException {
		
		Tramite tramite = null;
		try {
			tramite = repositorioRegistroAtendimento
			.recuperarTramiteMaisAtualPorRA(idRA);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return tramite;
	}
	
	/**
	 * 
	 * [UC0425] - Reiterar Registro de Atendimento
	 * 
	 * @author lms
	 * @date 10/08/2006
	 */
	public Integer reiterarRegistroAtendimento(
			RegistroAtendimento registroAtendimento, Usuario usuario,
			RAReiteracao raReiteracao,Collection colecaoFonesSolicitante)
	throws ControladorException {
		
		try {
			
			Date dataAtual = new Date();
			
			// [FS0001] - Verificar se o RA está cancelado ou bloqueado
			
			/*
			 * Caso o usuário esteja tentando reiterar o registro de atendimento
			 * e o mesmo esteja com a situação de cancelado ou bloqueado, o
			 * sistema deverá exibir a mensagem: "Este R.A. já está encerrado ou
			 * bloqueado. não possível reiterá-lo."
			 */
			if (RegistroAtendimento.SITUACAO_ENCERRADO
					.equals(registroAtendimento.getCodigoSituacao())
					|| RegistroAtendimento.SITUACAO_BLOQUEADO
					.equals(registroAtendimento.getCodigoSituacao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
				"atencao.registro_atendimento.cancelado_bloqueado");
			}
			
			// [FS0002] - Verificar se o RA está no prazo de atendimento
			
			/*
			 * Caso o usuário esteja tentando reiterar o registro de atendimento
			 * e o mesmo ainda esteja no prazo de atendimento, o sistema deverá
			 * exibir a mensagem: "Prazo previsto para o atendimento ainda não
			 * expirou. não possível reiterá-lo."
			 */
			if (Util.compararDiaMesAno(registroAtendimento
					.getDataPrevistaAtual(), dataAtual) > 0) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
				"atencao.registro_atendimento.prazo_nao_expirado");
			}
			
			// [FS0003] - Verificar se o RA já foi realizado no mesmo dia
			
//			/*
//			 * Caso o usuário esteja tentando reiterar o registro de atendimento
//			 * e o mesmo já foi reiterado no mesmo dia, o sistema deverá exibir
//			 * a mensagem: "Este R.A. já foi reiterado hoje. não possível
//			 * reiterá-lo."
//			 */
//			if (registroAtendimento.getUltimaReiteracao() != null) {
//				if (Util.compararDiaMesAno(registroAtendimento
//						.getUltimaReiteracao(), dataAtual) == 0) {
//					sessionContext.setRollbackOnly();
//					throw new ControladorException(
//					"atencao.registro_atendimento.ja_reiterado_hoje");
//				}
//			}
			
			// [FS0004] - Verificar se a unidade organizacional pode reiterar a
			// RA
			
			UnidadeOrganizacional unidadeAtualRA = obterUnidadeAtualRA(registroAtendimento
					.getId());
			
			/*
			 * Caso o indicador de autorização de manutenção do R.A. esteja com
			 * não, o sistema deverá exibir a mensagem: "Este R.A. foi
			 * registrado por outra Unidade Organizacional, reiteração
			 * impedida."
			 */
			Short indicadorAutorizacaoManutencaoRA = obterIndicadorAutorizacaoManutencaoRA(
					unidadeAtualRA.getId(), usuario.getId());
			
			if (ConstantesSistema.NAO.equals(indicadorAutorizacaoManutencaoRA)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
				"atencao.registro_atendimento.manutencao_nao_autorizada");
			}
			
			// [FS0005] - Atualização realizada por outro usuário
			
			/*
			 * Caso o usuário esteja tentando alterar o registro de atendimento
			 * e a mesma já tenha sido atualizada durante a manutenção corrente,
			 * o sistema deverá exibir a mensagem: "Este R.A. foi atualizado por
			 * outro usuário. não foi possível efetuar a reiteração."
			 */
			FiltroRegistroAtendimento filtro = new FiltroRegistroAtendimento();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroRegistroAtendimento.ID, registroAtendimento.getId()));
			Collection collection = getControladorUtil().pesquisar(filtro,
					RegistroAtendimento.class.getName());
			Date timestamp = ((RegistroAtendimento) collection.iterator()
					.next()).getUltimaAlteracao();
			
			if (timestamp.after(registroAtendimento.getUltimaAlteracao())) {
				throw new ControladorException(
				"atencao.registro_atendimento.timestamp");
			}
			
			raReiteracao.setUltimaAlteracao(new Date());
			Integer idRAReiteracao = (Integer)getControladorUtil().inserir(raReiteracao);
			raReiteracao.setId(idRAReiteracao);
			
			if(colecaoFonesSolicitante != null){
				RAReiteracaoFone fone = new RAReiteracaoFone();
				Iterator iterFones = colecaoFonesSolicitante.iterator();
				
				while (iterFones.hasNext()) {
					ClienteFone clienteFone = (ClienteFone) iterFones.next();
					
					fone.setDdd(clienteFone.getDdd());
					fone.setFone(clienteFone.getTelefone());
					fone.setFoneTipo(clienteFone.getFoneTipo());
					fone.setIndicadorPadrao(clienteFone.getIndicadorTelefonePadrao());
					fone.setRamal(clienteFone.getRamal());
					fone.setRaReiteracao(raReiteracao);
					fone.setUltimaAlteracao(new Date());
					
					getControladorUtil().inserir(fone);
				}
			}
			//[SB0007] – Inclui RA de urgência
			Collection usuariosVisualizacaoRAUrgencia = repositorioRegistroAtendimento
				.pesquisarUsuarioVisualizacaoRaUrgencia(registroAtendimento.getId());
			
			 if(usuariosVisualizacaoRAUrgencia != null && !usuariosVisualizacaoRAUrgencia.isEmpty()){
				 
				Iterator iterator = usuariosVisualizacaoRAUrgencia.iterator();
				
				while(iterator.hasNext()){
					VisualizacaoRegistroAtendimentoUrgencia visualizacaoRegistroAtendimentoUrgencia = new VisualizacaoRegistroAtendimentoUrgencia();
					Integer idUsuario = (Integer) iterator.next();
	
					Usuario usuarioPesquisado = new Usuario();
					usuarioPesquisado.setId(idUsuario);
					
					visualizacaoRegistroAtendimentoUrgencia.setRegistroAtendimento(registroAtendimento);
					visualizacaoRegistroAtendimentoUrgencia.setUsuario(usuarioPesquisado);
					visualizacaoRegistroAtendimentoUrgencia.setIndicadorTramite(2);
					visualizacaoRegistroAtendimentoUrgencia.setIndicadorVisualizacao(2);
					visualizacaoRegistroAtendimentoUrgencia.setUltimaAlteracao(new Date());
					visualizacaoRegistroAtendimentoUrgencia.setIndicadorReiteracao(ConstantesSistema.SIM);
					
					getControladorUtil().inserir(visualizacaoRegistroAtendimentoUrgencia);
					
				}
				
			 }
			
			
			// atualiza o registro de atendimento
			short qtd = 1;
			if (registroAtendimento.getQuantidadeReiteracao() != null) {
				qtd = (short) (registroAtendimento.getQuantidadeReiteracao() + 1);
			}
			
			registroAtendimento.setQuantidadeReiteracao(qtd);
			registroAtendimento.setUltimaReiteracao(dataAtual);
			registroAtendimento.setUltimaAlteracao(dataAtual);
			
			// [FS0006] - Verificar sucesso da transação
			getControladorUtil().atualizar(registroAtendimento);
			
			return registroAtendimento.getId();
			
		} catch (ControladorException e) {
			
			sessionContext.setRollbackOnly();
			throw e;
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar os parametros para atualizar o registro atendimento escolhido
	 * pelo usuário
	 * 
	 * @author Sávio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsRegistroAtendimento(
			Integer idRegistroAtendimento) throws ControladorException {
		try {
			return repositorioRegistroAtendimento
			.pesquisarParmsRegistroAtendimento(idRegistroAtendimento);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Verificar existencia ordem de Serviço para o registro atendimento
	 * pesquisado
	 * 
	 * @author Sávio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOrdemServicoParaRA(Integer idRegistroAtendimento)
	throws ControladorException {
		try {
			return repositorioRegistroAtendimento
			.verificarOrdemServicoParaRA(idRegistroAtendimento);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 11/08/2006
	 */
	public RegistroAtendimento validarRegistroAtendimento(Integer idRA)
	throws ControladorException {
		
		RegistroAtendimento registroAtendimento = null;
		
		try {
			registroAtendimento = repositorioRegistroAtendimento
			.pesquisarRegistroAtendimento(idRA);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		// [FS0001] - Validar Registro de Atendimento
		
		/*
		 * Caso o registro de atendimento seja recebido e a especificação
		 * associada ao mesmo não permite geração de ordem de Serviço, exibir a
		 * mensagem: "Especificação da Solicitação do Registro de Atendimento <<id_Ra>>
		 * não permite geração de ordem de Serviço" e retornar para o passo
		 * correspondente no fluxo principal.
		 */
		if (ConstantesSistema.NAO.equals(registroAtendimento
				.getSolicitacaoTipoEspecificacao()
				.getIndicadorGeracaoOrdemServico())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_atendimento.nao_permite_geracao_os",
					null, "" + registroAtendimento.getId());
		}
		
		/*
		 * Caso o registro de atendimento seja informado e a situação do mesmo
		 * esteja encerrado, exibir a mensagem: "Situação do Registro de
		 * Atendimento <<id_RA>> <<descricao_situação>> e não permite geração
		 * de ordem de Serviço." e retornar para o passo correspondente no fluxo
		 * principal.
		 */
		
		if (!RegistroAtendimento.SITUACAO_PENDENTE.equals(registroAtendimento
				.getCodigoSituacao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_atendimento.diferente_pendente", null,
					new String[] { "" + registroAtendimento.getId(),
							"" + registroAtendimento.getDescricaoSituacao() });
		}
		
		return registroAtendimento;
	}
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 11/08/2006
	 */
	public void verificarUnidadeUsuario(
			RegistroAtendimento registroAtendimento, Usuario usuario)
	throws ControladorException {
		
		// [FS0004] - Verificar Unidade do usuário
		
		/*
		 * Caso o indicador de autorização para manutenção do RA retornado pelo
		 * UC0419 esteja com valor correspondente a 2-não, exibir a mensagem:
		 * "Este RA foi aberto pela unidade <<descricao_unidade>>. não possível
		 * gerar ordem de Serviço." E retornar para o passo correspondente do
		 * fluxo principal.
		 */
		UnidadeOrganizacional unidadeOrganizacional = obterUnidadeAtualRA(registroAtendimento
				.getId());
		
		Short indicadorAutorizacaoManutencaoRA = obterIndicadorAutorizacaoManutencaoRA(
				unidadeOrganizacional.getId(), usuario.getId());
		
		if (ConstantesSistema.NAO.equals(indicadorAutorizacaoManutencaoRA)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.unidade_atendimento.nao_permite_geracao_os", null,
					"" + unidadeOrganizacional.getDescricao());
		}
		
	}
	
	/**
	 * [UC0446] Consultar Trâmites
	 * 
	 * Retorna a Coleção de Tramites do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<Tramite>
	 * @throws ControladorException
	 */
	public Collection<Tramite> obterTramitesRA(Integer idRA)
	throws ControladorException {
		
		Collection<Tramite> colecaoTramite = new ArrayList();
		try {
			colecaoTramite = repositorioRegistroAtendimento
			.obterTramitesRA(idRA);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoTramite;
	}
	
	/**
	 * [UC0447] Consultar RA Solicitantes
	 * 
	 * Retorna a Coleção de registro de atendimento solicitantes
	 * 
	 * @author Rafael Pinto
	 * @date 14/08/2006
	 * 
	 * @param idRA
	 * @return Collection<RegistroAtendimentoSolicitante>
	 * @throws ControladorException
	 */
	public Collection<RegistroAtendimentoSolicitante> obterRASolicitante(
			Integer idRA) throws ControladorException {
		
		Collection<RegistroAtendimentoSolicitante> colecaoRASolicitante = new ArrayList();
		try {
			colecaoRASolicitante = repositorioRegistroAtendimento
			.obterRASolicitante(idRA);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoRASolicitante;
	}
	
	/**
	 * [UC0431] Consultar Ordens de Serviço do Registro Atendimento
	 * 
	 * Retorna a Coleção de OS's do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> obterOSRA(Integer idRA)
	throws ControladorException {
		Collection<OrdemServico> colecaoOS = new ArrayList();
		try {
			colecaoOS = repositorioRegistroAtendimento.obterOSRA(idRA);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoOS;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [FS0012] - Verificar possibilidade de atualização do registro de
	 * atendimento
	 * 
	 * @author Sávio Luiz
	 * @date 14/08/2006
	 * 
	 * @param idRA,idUsuarioLogado
	 * @throws ControladorException
	 */
	
	public UnidadeOrganizacional verificarPossibilidadeAtualizacaoRA(
			Integer idRA, Integer idUsuarioLogado) throws ControladorException {
		
		// [UC0418] - Obter Unidade Atual do RA
		UnidadeOrganizacional unidadeOrganizacional = obterUnidadeAtualRA(idRA);
		// [UC0419] - Obter Indicador de Autorização para Manutenção do RA
		Short indicadorAutorizacaoRA = obterIndicadorAutorizacaoManutencaoRA(
				unidadeOrganizacional.getId(), idUsuarioLogado);
		// [UC0420] - Obter Descrição da situação do RA
		ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRAHelper = obterDescricaoSituacaoRA(idRA);
		
		// caso o indicador de atualização seja igual a 2-não
		if (indicadorAutorizacaoRA != null
				&& indicadorAutorizacaoRA.equals(new Short("2"))) {
			throw new ControladorException(
					"atencao.unidade_organizacional_nao_pode_ser_alterada",
					null, unidadeOrganizacional.getDescricao());
		}
		
		Object[] parmsRA = null;
		Integer idOrdemServProg = null;
		try {
			parmsRA = repositorioRegistroAtendimento.verificarParmsRA(idRA);
			
			idOrdemServProg = repositorioRegistroAtendimento
			.verificarExistenciaOrdemServicoProgramacaoRA(idRA);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (parmsRA != null) {
			// caso o registro atendimento duplicidade esteja diferente de nulo
			if (parmsRA[0] != null) {
				Integer idDuplicidade = (Integer) parmsRA[0];
				throw new ControladorException(
						"atencao.registro_atendimento_duplicidade", null, ""
						+ idRA, "" + idDuplicidade);
			}
			// caso a situação de registro atendimento seja igual a encerrado
			if (parmsRA[1] != null) {
				Short situacaoRA = (Short) parmsRA[1];
				if (situacaoRA.equals(RegistroAtendimento.SITUACAO_ENCERRADO)) {
					throw new ControladorException(
							"atencao.registro_atendimento_situacao", null, ""
							+ idRA, obterDescricaoSituacaoRAHelper
							.getDescricaoSituacao());
				}
			}
		}
		// caso exista ordem de Serviço não encerrada e programada para o
		// registro de atendimento
		if (idOrdemServProg != null) {
			throw new ControladorException(
					"atencao.ordem_servico_nao_encerrada_programada_ra", null,
					"" + idRA);
		}
		
		return unidadeOrganizacional;
		
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especificação exija a matrícula do Imóvel (STEP_ICMATRICULA com o
	 * valor correspondente a um na tabela SOLICITACAO_TIPO_ESPECIFICACAO.
	 * 
	 * @author Raphael Rossiter
	 * @date 14/08/2006
	 * 
	 * @param SolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean especificacaoExigeMatriculaImovel(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao)
	throws ControladorException {
		
		boolean retorno = false;
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				solicitacaoTipoEspecificacao.getId()));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao especificacaoBase = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			if (especificacaoBase.getIndicadorMatricula() != null
					&& especificacaoBase.getIndicadorMatricula().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO.intValue())) {
				
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a especificação exija o pavimento da rua (STEP_ICPAVIMENTORUA com o
	 * valor correspondente a um na tabela SOLICITACAO_TIPO_ESPECIFICACAO.
	 * 
	 * @author Raphael Rossiter
	 * @date 14/08/2006
	 * 
	 * @param SolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean especificacaoExigePavimentoRua(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		
		boolean retorno = false;
		
		if (solicitacaoTipoEspecificacao.getIndicadorPavimentoRua() == ConstantesSistema.INDICADOR_USO_ATIVO
				.shortValue()) {
			
			retorno = true;
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a especificação exija o pavimento da calçada
	 * (STEP_ICPAVIMENTOCALCADA com o valor correspondente a um na tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO.
	 * 
	 * @author Raphael Rossiter
	 * @date 14/08/2006
	 * 
	 * @param SolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean especificacaoExigePavimentoCalcada(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		
		boolean retorno = false;
		
		if (solicitacaoTipoEspecificacao.getIndicadorPavimentoCalcada() == ConstantesSistema.INDICADOR_USO_ATIVO
				.shortValue()) {
			
			retorno = true;
		}
		
		return retorno;
	}
	
	/**
	 * [UC0409] Obter Indicador de existência de hidrômetro na Ligação de Água e
	 * no poço
	 * 
	 * Este caso de uso Obtém o indicador de existência de hidrômetro na ligação
	 * de Água e no poço
	 * 
	 * 
	 * @author Ana Maria, Rômulo Aurélio
	 * @date 14/08/2006, 20/05/2010
	 * 
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public ObterIndicadorExistenciaHidrometroHelper obterIndicadorExistenciaHidrometroLigacaoAguaPoco(
			Integer idImovel) throws ControladorException {
		
		ObterIndicadorExistenciaHidrometroHelper retorno = new ObterIndicadorExistenciaHidrometroHelper();
		
		retorno.setIndicadorLigacaoAgua(ConstantesSistema.NAO);
		retorno.setIndicadorPoco(ConstantesSistema.NAO);
		
		try {
			Object[] dadosImovel = null;
			
			if (idImovel != null) {
				
				// Pesquisar a situação e o indicador de existência de
				// hidrômetro na ligação de Água e no poço
				dadosImovel = repositorioRegistroAtendimento
				.pesquisarHidrometroImovel(idImovel);
				
				if (dadosImovel != null) {
					Short indicadorExistenciaLigacaoAgua = (Short) dadosImovel[0];
					Short indicadorExistenciaLigacaoEsgoto = (Short) dadosImovel[1];
					Integer idHidrometroPoco = (Integer) dadosImovel[2];
					Integer idHidrometroLigacaoAgua = (Integer) dadosImovel[3];
					
					
					/*
					 * Caso a situação da ligação de Água do Imóvel(last_id)
					 * corresponda a ligado ou cortado e exista hidrômetro
					 * instalado na ligação de Água (hidi_id com valor diferente
					 * de nulo na tabela LIGACAO_AGUA), atribui o valor 1-SIM ao
					 * indicador de existência de hidrômetro na ligação de Água
					 */
					
					if (indicadorExistenciaLigacaoAgua.equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {
						if (idHidrometroLigacaoAgua != null
								&& !idHidrometroLigacaoAgua.equals("")) {
							retorno
							.setIndicadorLigacaoAgua(ConstantesSistema.SIM);
						}
					}
					/*
					 * Caso a situação da ligação do esgoto do Imóvel(lest_id)
					 * corresponda a ligado ou tamponado e exista hidrômetro
					 * instalado no poço (hidi_id com valor diferente de nulo na
					 * tabela IMOVEL), atribui o valor 1-SIM ao indicador de
					 * existência de hidrômetro no poço
					 */
					
					if (indicadorExistenciaLigacaoEsgoto.equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {
						if (idHidrometroPoco != null
								&& !idHidrometroPoco.equals("")) {
							retorno.setIndicadorPoco(ConstantesSistema.SIM);
						}
					}
				}
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0024] - Verificar registro de Atendimento Sem Identificação do Local
	 * de ocorrência
	 * 
	 * @author Sávio Luiz
	 * @date 15/08/2006
	 * 
	 * @param idRA,idUsuarioLogado
	 * @throws ControladorException
	 */
	
	public int verificarRASemIdentificacaoLO(Integer idImovel, Integer idRA)
	throws ControladorException {
		
		// Indicador de validação de matricula do Imóvel com valor = 2(não)
		int indicadorValidacaoImovel = 2;
		
		Short codigoSituacao = null;
		Integer idLogradouroBairro = null;
		try {
			codigoSituacao = repositorioRegistroAtendimento
			.pesquisarCdSituacaoRegistroAtendimento(idRA);
			if (idImovel != null) {
				idLogradouroBairro = repositorioRegistroAtendimento
				.pesquisarImovelDescritivo(idImovel);
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		// caso o registro de atendimento esteja sem a identificação do local de
		// ocorrência
		if (codigoSituacao != null
				&& codigoSituacao
				.equals(RegistroAtendimento.SITUACAO_BLOQUEADO)) {
			// caso o registro de atendimento esteja acsociado a um Imóvel
			if (idImovel != null) {
				// caso o endereço do imovel não seja descritivo
				if (idLogradouroBairro != null) {
					// atribuir o valor 1(Sim) ao indicador de validação de
					// matrícula do Imóvel
					indicadorValidacaoImovel = 1;
				}
			}
		}
		return indicadorValidacaoImovel;
		
	}
	
	/**
	 * [UC0452] Obter Dados do Registro de Atendimento
	 * 
	 * Este caso de uso permite obter dados de um registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimento(
			Integer idRegistroAtendimento) throws ControladorException {
		
		ObterDadosRegistroAtendimentoHelper retorno = null;
		
		if (idRegistroAtendimento != null) {
			
			retorno = new ObterDadosRegistroAtendimentoHelper();
			
			Object[] dadosRegistroAtendimento = null;
			try {
				
				// Pesquisa os dados do registro de atendimento
				dadosRegistroAtendimento = repositorioRegistroAtendimento
				.pesquisarDadosRegistroAtendimento(idRegistroAtendimento);
				
				if (dadosRegistroAtendimento != null) {
					
					RegistroAtendimento RA = new RegistroAtendimento();
					
					// número do RA
					RA.setId((Integer) dadosRegistroAtendimento[0]);
					
					// número Manual
					if (dadosRegistroAtendimento[65] != null) {
						RA.setManual((Integer) dadosRegistroAtendimento[65]);
					}
					
					RA.setCodigoSituacao((Short) dadosRegistroAtendimento[1]);
					RA.setUltimaAlteracao((Date) dadosRegistroAtendimento[30]);
					RA
					.setQuantidadeReiteracao((Short) dadosRegistroAtendimento[56]);
					RA.setUltimaReiteracao((Date) dadosRegistroAtendimento[57]);
					RA
					.setParecerEncerramento((String) dadosRegistroAtendimento[58]);
					
					// Situação do RA
					ObterDescricaoSituacaoRAHelper SituacaoRA = obterDescricaoSituacaoRA(idRegistroAtendimento);
					retorno.setDescricaoSituacaoRA(SituacaoRA
							.getDescricaoSituacao());
					
					// Dados do RA Associado
					setDadosRAAssociado(idRegistroAtendimento, retorno);
					
					// Especificação e Tipo de solicitação
					if ((Integer) dadosRegistroAtendimento[2] != null) {
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
						solicitacaoTipoEspecificacao
						.setId((Integer) dadosRegistroAtendimento[2]);
						solicitacaoTipoEspecificacao
						.setDescricao((String) dadosRegistroAtendimento[3]);
						
						// Colocado por Raphael Rossiter em 24/08/2006
						if (dadosRegistroAtendimento[31] != null) {
							solicitacaoTipoEspecificacao
							.setIndicadorCliente((Short) dadosRegistroAtendimento[31]);
						}
						
						// Colocado por Leonardo Regis em 28/08/2006
						if (dadosRegistroAtendimento[32] != null) {
							solicitacaoTipoEspecificacao
							.setIndicadorParecerEncerramento((Integer) dadosRegistroAtendimento[32]);
						}
						
						if (dadosRegistroAtendimento[63] != null) {
							solicitacaoTipoEspecificacao
							.setIndicadorGeracaoDebito((Integer) dadosRegistroAtendimento[63]);
						}
						
						if (dadosRegistroAtendimento[64] != null) {
							solicitacaoTipoEspecificacao
							.setIndicadorGeracaoCredito((Integer) dadosRegistroAtendimento[64]);
						}
						
						if ((Integer) dadosRegistroAtendimento[4] != null) {
							SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
							solicitacaoTipo
							.setId((Integer) dadosRegistroAtendimento[4]);
							solicitacaoTipo
							.setDescricao((String) dadosRegistroAtendimento[5]);
							solicitacaoTipo
							.setIndicadorTarifaSocial((Short) dadosRegistroAtendimento[29]);
							solicitacaoTipoEspecificacao
							.setSolicitacaoTipo(solicitacaoTipo);
						}
						
						//Tipo do débito
						if (dadosRegistroAtendimento[70] != null){
							DebitoTipo debitoTipo = new DebitoTipo();
							debitoTipo.setId((Integer) dadosRegistroAtendimento[70]);
							debitoTipo.setDescricao((String) dadosRegistroAtendimento[71]);
							
							solicitacaoTipoEspecificacao.setDebitoTipo(debitoTipo);
						}
						
						//Servico Tipo
						if (dadosRegistroAtendimento[76] != null){
							ServicoTipo servicoTipo = new ServicoTipo();
							servicoTipo.setId((Integer) dadosRegistroAtendimento[76]);
							servicoTipo.setValor((BigDecimal) dadosRegistroAtendimento[77]);
							
							solicitacaoTipoEspecificacao.setServicoTipo(servicoTipo);
						}	
						//Valor do débito
						if (dadosRegistroAtendimento[72] != null){
							solicitacaoTipoEspecificacao.setValorDebito((BigDecimal) dadosRegistroAtendimento[72]);
						}
						
						//Indicador permite alterar valor
						solicitacaoTipoEspecificacao.setIndicadorPermiteAlterarValor((Short) dadosRegistroAtendimento[73]);
						
						//Indicador cobrar juros
						solicitacaoTipoEspecificacao.setIndicadorCobrarJuros((Short) dadosRegistroAtendimento[74]);
						
						RA.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
					}
					// Meio de solicitação
					if ((Integer) dadosRegistroAtendimento[6] != null) {
						MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
						meioSolicitacao
						.setId((Integer) dadosRegistroAtendimento[6]);
						meioSolicitacao
						.setDescricao((String) dadosRegistroAtendimento[7]);
						RA.setMeioSolicitacao(meioSolicitacao);
					}
					// Dados do Imóvel
					if ((Integer) dadosRegistroAtendimento[8] != null) {
						Imovel imovel = new Imovel();
						imovel.setId((Integer) dadosRegistroAtendimento[8]);
						if ((Integer) dadosRegistroAtendimento[9] != null) {
							Localidade localidade = new Localidade();
							localidade
							.setId((Integer) dadosRegistroAtendimento[9]);
							
							imovel.setLocalidade(localidade);
						}
						if ((Integer) dadosRegistroAtendimento[10] != null) {
							SetorComercial setorComercial = new SetorComercial();
							setorComercial
							.setCodigo((Integer) dadosRegistroAtendimento[10]);
							imovel.setSetorComercial(setorComercial);
						}
						if ((Integer) dadosRegistroAtendimento[75] != null) {
							Quadra quadra = new Quadra();
							quadra.setNumeroQuadra((Integer) dadosRegistroAtendimento[11]);
							quadra.setId((Integer) dadosRegistroAtendimento[75]);
							imovel.setQuadra(quadra);
						}
						if (dadosRegistroAtendimento[67] != null) {
							retorno
							.setCodigoRota((Short) dadosRegistroAtendimento[67]);
						}
						if (dadosRegistroAtendimento[68] != null) {
							retorno
							.setSequencialRota((Integer) dadosRegistroAtendimento[68]);
						}
						if (dadosRegistroAtendimento[69] != null) {
							imovel
							.setNumeroImovel((String) dadosRegistroAtendimento[69]);
							RA.setNumeroImovel(imovel.getNumeroImovel());
						}
						if ((Integer) dadosRegistroAtendimento[59] != null) {
							LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
							ligacaoAguaSituacao
							.setId((Integer) dadosRegistroAtendimento[59]);
							ligacaoAguaSituacao
							.setDescricao((String) dadosRegistroAtendimento[60]);
							imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
						}
						if ((Integer) dadosRegistroAtendimento[61] != null) {
							LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
							ligacaoEsgotoSituacao
							.setId((Integer) dadosRegistroAtendimento[61]);
							ligacaoEsgotoSituacao
							.setDescricao((String) dadosRegistroAtendimento[62]);
							imovel
							.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
						}
						imovel.setLote((Short) dadosRegistroAtendimento[12]);
						imovel.setSubLote((Short) dadosRegistroAtendimento[13]);
												
						ImovelPerfil imovelPerfil = new ImovelPerfil();
						imovelPerfil.setDescricao((String) dadosRegistroAtendimento[82]);
						imovel.setImovelPerfil(imovelPerfil);									
						
						RA.setImovel(imovel);
					}
					
					// Data e Hora do Atendimento
					RA
					.setRegistroAtendimento((Date) dadosRegistroAtendimento[14]);
					
					// Data Prevista
					RA
					.setDataPrevistaAtual((Date) dadosRegistroAtendimento[15]);
					
					RA
					.setDataPrevistaOriginal((Date) dadosRegistroAtendimento[46]);
					RA.setObservacao((String) dadosRegistroAtendimento[47]);
					RA
					.setIndicadorAtendimentoOnline((Short) dadosRegistroAtendimento[48]);
					if (dadosRegistroAtendimento[49] != null) {
						RA
						.setDataInicioEspera((Date) dadosRegistroAtendimento[49]);
					}
					
					if (dadosRegistroAtendimento[50] != null) {
						RA
						.setDataFimEspera((Date) dadosRegistroAtendimento[50]);
					}
					
					// Data do Encerramento
					RA.setDataEncerramento((Date) dadosRegistroAtendimento[16]);
					
					// Motivo do Encerramento
					if ((Integer) dadosRegistroAtendimento[17] != null) {
						AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
						atendimentoMotivoEncerramento
						.setId((Integer) dadosRegistroAtendimento[17]);
						atendimentoMotivoEncerramento
						.setDescricao((String) dadosRegistroAtendimento[18]);
						atendimentoMotivoEncerramento
						.setIndicadorExecucao((Short) dadosRegistroAtendimento[66]);
						RA
						.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
					}
					
					// Dados do Solicitante do RA
					setSolicitanteRA(idRegistroAtendimento, retorno);
					
					// Dados do endereço de ocorrência do RA
					setEnderecoOcorrenciaRA(idRegistroAtendimento, retorno);
					
					// Ponto de Referência
					RA
					.setPontoReferencia((String) dadosRegistroAtendimento[19]);
					// Dados Área do Bairro
					if ((Integer) dadosRegistroAtendimento[20] != null) {
						BairroArea bairroArea = new BairroArea();
						bairroArea
						.setId((Integer) dadosRegistroAtendimento[20]);
						bairroArea
						.setNome((String) dadosRegistroAtendimento[21]);
						if ((Integer) dadosRegistroAtendimento[22] != null) {
							
							Bairro bairro = new Bairro();
							bairro
							.setId((Integer) dadosRegistroAtendimento[22]);
							bairro
							.setNome((String) dadosRegistroAtendimento[23]);
							
							if (dadosRegistroAtendimento[42] != null) {
								Municipio municipio = new Municipio();
								municipio
								.setId((Integer) dadosRegistroAtendimento[42]);
								municipio
								.setNome((String) dadosRegistroAtendimento[43]);
								
								bairro.setMunicipio(municipio);
							}
							bairroArea.setBairro(bairro);
						}
						RA.setBairroArea(bairroArea);
					}
					
					// Local/Setor/Quadra
					if ((Integer) dadosRegistroAtendimento[26] != null) {
						Localidade localidadeRA = new Localidade();
						localidadeRA
						.setId((Integer) dadosRegistroAtendimento[26]);
						localidadeRA
						.setDescricao((String) dadosRegistroAtendimento[51]);
						RA.setLocalidade(localidadeRA);
					}
					if ((Integer) dadosRegistroAtendimento[27] != null) {
						SetorComercial setorRA = new SetorComercial();
						setorRA.setId((Integer) dadosRegistroAtendimento[40]);
						setorRA
						.setCodigo((Integer) dadosRegistroAtendimento[27]);
						setorRA
						.setDescricao((String) dadosRegistroAtendimento[55]);
						RA.setSetorComercial(setorRA);
					}
					if ((Integer) dadosRegistroAtendimento[28] != null) {
						Quadra quadraRA = new Quadra();
						quadraRA.setId((Integer) dadosRegistroAtendimento[41]);
						quadraRA
						.setNumeroQuadra((Integer) dadosRegistroAtendimento[28]);
						RA.setQuadra(quadraRA);
					}
					// divisão de esgoto
					if ((Integer) dadosRegistroAtendimento[24] != null) {
						DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
						divisaoEsgoto
						.setId((Integer) dadosRegistroAtendimento[24]);
						divisaoEsgoto
						.setDescricao((String) dadosRegistroAtendimento[25]);
						RA.setDivisaoEsgoto(divisaoEsgoto);
					}
					// Colocado por Ana Maria em 29/08/2006
					// Logradouro Bairro
					if ((Integer) dadosRegistroAtendimento[33] != null) {
						LogradouroBairro logradouroBairro = new LogradouroBairro();
						logradouroBairro
						.setId((Integer) dadosRegistroAtendimento[33]);
						RA.setLogradouroBairro(logradouroBairro);
					}
					// Logradouro cep
					if ((Integer) dadosRegistroAtendimento[34] != null) {
						LogradouroCep logradouroCep = new LogradouroCep();
						logradouroCep
						.setId((Integer) dadosRegistroAtendimento[34]);
						RA.setLogradouroCep(logradouroCep);
					}
					
					RA
					.setComplementoEndereco((String) dadosRegistroAtendimento[35]);
					
					// Local ocorrência
					if ((Integer) dadosRegistroAtendimento[36] != null) {
						LocalOcorrencia localOcorrencia = new LocalOcorrencia();
						localOcorrencia
						.setId((Integer) dadosRegistroAtendimento[36]);
						localOcorrencia
						.setDescricao((String) dadosRegistroAtendimento[52]);
						RA.setLocalOcorrencia(localOcorrencia);
					}
					// Pavimento Rua
					if ((Integer) dadosRegistroAtendimento[37] != null) {
						PavimentoRua pavimentoRua = new PavimentoRua();
						pavimentoRua
						.setId((Integer) dadosRegistroAtendimento[37]);
						pavimentoRua
						.setDescricao((String) dadosRegistroAtendimento[53]);
						RA.setPavimentoRua(pavimentoRua);
					}
					// Pavimento calçada
					if ((Integer) dadosRegistroAtendimento[38] != null) {
						PavimentoCalcada pavimentoCalcada = new PavimentoCalcada();
						pavimentoCalcada
						.setId((Integer) dadosRegistroAtendimento[38]);
						pavimentoCalcada
						.setDescricao((String) dadosRegistroAtendimento[54]);
						RA.setPavimentoCalcada(pavimentoCalcada);
					}
					
					// Descrição Local ocorrência
					RA
					.setDescricaoLocalOcorrencia((String) dadosRegistroAtendimento[39]);
					
					// Ra Motivo Reativacao
					if (dadosRegistroAtendimento[44] != null) {
						RaMotivoReativacao raMotivoReativacao = new RaMotivoReativacao();
						
						raMotivoReativacao
						.setId((Integer) dadosRegistroAtendimento[44]);
						raMotivoReativacao
						.setDescricao((String) dadosRegistroAtendimento[45]);
						
						RA.setRaMotivoReativacao(raMotivoReativacao);
					}
					
					// Motivo Nao Cobranca
					if (dadosRegistroAtendimento[78] != null) {
						ServicoNaoCobrancaMotivo naoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
						
						naoCobrancaMotivo
						.setId((Integer) dadosRegistroAtendimento[78]);
						naoCobrancaMotivo
						.setDescricao((String) dadosRegistroAtendimento[79]);
						
						RA.setServicoNaoCobrancaMotivo(naoCobrancaMotivo);
					}
					
					//**************************************************
					// Por: Ivan Sergio
					// Data: 09/09/2009
					// CRC2621
					//**************************************************
					// Numeros das Coordenadas Norte e Leste
					RA.setNnCoordenadaNorte((BigDecimal) dadosRegistroAtendimento[80]);
					RA.setNnCoordenadaLeste((BigDecimal) dadosRegistroAtendimento[81]);
					
					// Dados da Unidade de Atendimento e a Unidade Atual
					RA = setUnidadesRA(RA, retorno);
					
					retorno.setRegistroAtendimento(RA);
					
				}
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		return retorno;
	}
	
	/**
	 * [UC0452] Obter Dados do Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 16/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @param retorno
	 */
	private void setEnderecoOcorrenciaRA(Integer idRegistroAtendimento,
			ObterDadosRegistroAtendimentoHelper retorno)
	throws ControladorException {
		// endereço da ocorrência
		String enderecoOcorrencia = obterEnderecoOcorrenciaRA(idRegistroAtendimento);
		if (enderecoOcorrencia != null) {
			retorno.setEnderecoOcorrencia(enderecoOcorrencia);
		}
	}
	
	/**
	 * [UC0452] Obter Dados do Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 16/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @param retorno
	 */
	private RegistroAtendimento setUnidadesRA(RegistroAtendimento RA,
			ObterDadosRegistroAtendimentoHelper retorno)
	throws ControladorException {
		// Unidade de Atendimento
		UnidadeOrganizacional unidadeAtendimento = obterUnidadeAtendimentoRA(RA.getId());
		if (unidadeAtendimento != null) {
			retorno.setUnidadeAtendimento(unidadeAtendimento);
		}
		// Unidade Atual
		UnidadeOrganizacional unidadeAtual = obterUnidadeAtualRA(RA.getId());
		if (unidadeAtual != null) {
			retorno.setUnidadeAtual(unidadeAtual);
		}
		
		// inclusão da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
		// Vivianne Sousa 19/06/2008 analista:Fátima Sampaio
		RA.setUnidadeAtual(unidadeAtual);
		
		return RA;
	}
	
	/**
	 * [UC0452] Obter Dados do Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 15/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @param retorno
	 */
	private void setDadosRAAssociado(Integer idRegistroAtendimento,
			ObterDadosRegistroAtendimentoHelper retorno)
	throws ControladorException {
		// Dados do RA Associado
		ObterRAAssociadoHelper dadosRAAssociado = obterRAAssociado(idRegistroAtendimento);
		if (dadosRAAssociado.getCodigoExistenciaRAAssociado() != RegistroAtendimento.CODIGO_ASSOCIADO_SEM_RA) {
			Short codigoExistenciaRAassociado = dadosRAAssociado
			.getCodigoExistenciaRAAssociado();
			// Código de existência de RA Associado
			retorno.setCodigoExistenciaRAAssociado(codigoExistenciaRAassociado);
			// Registro Atendimento Associado
			retorno.setRAAssociado(dadosRAAssociado
					.getRegistroAtendimentoAssociado());
			// Situação do RA Associado
			ObterDescricaoSituacaoRAHelper situacaoRAAssociado = obterDescricaoSituacaoRA(dadosRAAssociado
					.getRegistroAtendimentoAssociado().getId());
			retorno.setDescricaoSituacaoRAAssociado(situacaoRAAssociado
					.getDescricaoSituacao());
			
			// Títulos do número e da situação do RA Associado
			if (codigoExistenciaRAassociado
					.equals(RegistroAtendimento.CODIGO_ASSOCIADO_SEM_RA)) {
				retorno.setTituloNumeroRAAssociado(null);
				retorno.setTituloSituacaoRAAssociado(null);
			} else if (codigoExistenciaRAassociado
					.equals(RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA)) {
				retorno
				.setTituloNumeroRAAssociado(RegistroAtendimento.NUMERO_RA_REFERENCIA);
				retorno
				.setTituloSituacaoRAAssociado(RegistroAtendimento.SITUACAO_RA_REFERENCIA);
			} else if (codigoExistenciaRAassociado
					.equals(RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL)) {
				retorno
				.setTituloNumeroRAAssociado(RegistroAtendimento.NUMERO_RA_ATUAL);
				retorno
				.setTituloSituacaoRAAssociado(RegistroAtendimento.SITUACAO_RA_ATUAL);
			} else if (codigoExistenciaRAassociado
					.equals(RegistroAtendimento.CODIGO_ASSOCIADO_RA_ANTERIOR)) {
				retorno
				.setTituloNumeroRAAssociado(RegistroAtendimento.NUMERO_RA_ANTERIOR);
				retorno
				.setTituloSituacaoRAAssociado(RegistroAtendimento.SITUACAO_RA_ANTERIOR);
			}
		}
	}
	
	/**
	 * [UC0452] Obter Dados do Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 15/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @param retorno
	 */
	private void setSolicitanteRA(Integer idRegistroAtendimento,
			ObterDadosRegistroAtendimentoHelper retorno)
	throws ErroRepositorioException {
		// Pesquisar dados do Solicitante do RA
		Object[] dadosRegistroAtendimentoSolicitante = repositorioRegistroAtendimento
		.pesquisarDadosRASolicitante(idRegistroAtendimento);
		
		if (dadosRegistroAtendimentoSolicitante != null) {
			RegistroAtendimentoSolicitante raSolicitante = new RegistroAtendimentoSolicitante();
			
			raSolicitante.setID((Integer) dadosRegistroAtendimentoSolicitante[5]);
			
			raSolicitante.setNumeroProtocoloAtendimento((String) dadosRegistroAtendimentoSolicitante[6]);
			
			/*
			 * Caso o principal solicitante do registro de atendimento seja um
			 * cliente, obter os dados do cliente
			 */
			if ((Integer) dadosRegistroAtendimentoSolicitante[0] != null) {
				Cliente cliente = new Cliente();
				cliente.setId((Integer) dadosRegistroAtendimentoSolicitante[0]);
				cliente
				.setNome((String) dadosRegistroAtendimentoSolicitante[1]);
				raSolicitante.setCliente(cliente);
				/*
				 * Caso o principal solicitante do registro de atendimento seja
				 * uma unidade, obter dados da unidade
				 */
			} else if ((Integer) dadosRegistroAtendimentoSolicitante[2] != null) {
				UnidadeOrganizacional unidade = new UnidadeOrganizacional();
				unidade.setId((Integer) dadosRegistroAtendimentoSolicitante[2]);
				unidade
				.setDescricao((String) dadosRegistroAtendimentoSolicitante[3]);
				raSolicitante.setUnidadeOrganizacional(unidade);
				// Caso contrário obter o nome do solicitante a partir dos dados
				// do solicitante
			} else {
				raSolicitante
				.setSolicitante((String) dadosRegistroAtendimentoSolicitante[4]);
			}
			retorno.setSolicitante(raSolicitante);
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do endereço da ocorrência e LGCP_ID=LGCP_ID do endereço
	 * da ocorrência e STEP_ID=Id da Especificação selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * [SB0008] Verifica existência de Registro de Atendimento Pendente para o
	 * Local da ocorrência
	 * 
	 * @author Raphael Rossiter
	 * @date 15/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroBairro, idLogradouroCep
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento verificaExistenciaRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ControladorException {
		
		RegistroAtendimento ra = null;
		Collection colecaoRegistroAtendimento = null;
		
		try {
			colecaoRegistroAtendimento = repositorioRegistroAtendimento
			.verificaExistenciaRAPendenteLocalOcorrencia(
					idSolicitacaoTipoEspecificacao, idLogradouroBairro,
					idLogradouroCep);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (colecaoRegistroAtendimento != null
				&& !colecaoRegistroAtendimento.isEmpty()) {
			
			ra = new RegistroAtendimento();
			
			Iterator raIterator = colecaoRegistroAtendimento.iterator();
			
			Object[] arrayRA = (Object[]) raIterator.next();
			
			if (arrayRA[20] != null && arrayRA[21] != null) {
				
				LogradouroCep logradouroCep = null;
				if (arrayRA[20] != null) {
					
					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) arrayRA[20]);
					
					// id do Logradouro
					Logradouro logradouro = null;
					if (arrayRA[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) arrayRA[19]);
						logradouro.setNome("" + arrayRA[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// Descrição logradouro tipo
					if (arrayRA[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada("" + arrayRA[22]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descrição logradouro titulo
					if (arrayRA[23] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo
						.setDescricaoAbreviada("" + arrayRA[23]);
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					// id do CEP
					Cep cep = null;
					if (arrayRA[10] != null) {
						cep = new Cep();
						cep.setCepId((Integer) arrayRA[10]);
						cep.setCodigo((Integer) arrayRA[16]);
					}
					
					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setCep(cep);
				}
				
				ra.setLogradouroCep(logradouroCep);
				
				LogradouroBairro logradouroBairro = null;
				if (arrayRA[21] != null) {
					
					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayRA[21]);
					
					Bairro bairro = null;
					// nome bairro
					if (arrayRA[3] != null) {
						bairro = new Bairro();
						bairro.setId((Integer) arrayRA[3]);
						bairro.setCodigo((Integer) arrayRA[17]);
						bairro.setNome("" + arrayRA[4]);
					}
					
					Municipio municipio = null;
					// nome municipio
					if (arrayRA[5] != null) {
						municipio = new Municipio();
						municipio.setId((Integer) arrayRA[5]);
						municipio.setNome("" + arrayRA[6]);
						
						// id da unidade federação
						if (arrayRA[7] != null) {
							UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
							unidadeFederacao.setId((Integer) arrayRA[7]);
							unidadeFederacao.setSigla("" + arrayRA[8]);
							municipio.setUnidadeFederacao(unidadeFederacao);
						}
						
						bairro.setMunicipio(municipio);
					}
					
					logradouroBairro.setBairro(bairro);
				}
				
				ra.setLogradouroBairro(logradouroBairro);
				
				// complemento endereço
				if (arrayRA[18] != null) {
					ra.setComplementoEndereco("" + arrayRA[18]);
				}
			}
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacao.setId((Integer) arrayRA[24]);
			solicitacaoTipoEspecificacao.setDescricao((String) arrayRA[9]);
			ra.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		}
		
		return ra;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do endereço da ocorrência e LGCP_ID=LGCP_ID do endereço
	 * da ocorrência e STEP_ID=Id da Especificação selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * [SB0025] Verifica Registro de Atendimento de Falta de Água Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroCep, idLogradouroBairro
	 * @return RegistroAtendimentoPendenteLocalOcorrenciaHelper
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimentoPendenteLocalOcorrenciaHelper pesquisarRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ControladorException {
		
		RegistroAtendimentoPendenteLocalOcorrenciaHelper retorno = null;
		
		Collection colecaoRegistroAtendimento = null;
		
		try {
			colecaoRegistroAtendimento = repositorioRegistroAtendimento
			.pesquisarRAPendenteLocalOcorrencia(
					idSolicitacaoTipoEspecificacao, idLogradouroCep,
					idLogradouroBairro);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (colecaoRegistroAtendimento != null
				&& !colecaoRegistroAtendimento.isEmpty()) {
			
			retorno = new RegistroAtendimentoPendenteLocalOcorrenciaHelper();
			
			Collection colecaoRegistroAtendimentoRetorno = colecaoRegistroAtendimentoRetorno = new ArrayList();
			
			RegistroAtendimento ra = null;
			
			Iterator raIterator = colecaoRegistroAtendimento.iterator();
			
			Object[] arrayRA = null;
			
			boolean primeiroRegistro = true;
			
			while (raIterator.hasNext()) {
				
				ra = new RegistroAtendimento();
				
				arrayRA = (Object[]) raIterator.next();
				
				// endereço e SolicitacaoTipoEspecificacao
				if (primeiroRegistro) {
					
					if (arrayRA[20] != null && arrayRA[21] != null) {
						
						LogradouroCep logradouroCep = null;
						if (arrayRA[20] != null) {
							
							logradouroCep = new LogradouroCep();
							logradouroCep.setId((Integer) arrayRA[20]);
							
							// id do Logradouro
							Logradouro logradouro = null;
							if (arrayRA[19] != null) {
								logradouro = new Logradouro();
								logradouro.setId((Integer) arrayRA[19]);
								logradouro.setNome("" + arrayRA[0]);
							}
							LogradouroTipo logradouroTipo = null;
							// Descrição logradouro tipo
							if (arrayRA[22] != null) {
								logradouroTipo = new LogradouroTipo();
								logradouroTipo.setDescricaoAbreviada(""
										+ arrayRA[22]);
								logradouro.setLogradouroTipo(logradouroTipo);
							}
							LogradouroTitulo logradouroTitulo = null;
							// Descrição logradouro titulo
							if (arrayRA[23] != null) {
								logradouroTitulo = new LogradouroTitulo();
								logradouroTitulo.setDescricaoAbreviada(""
										+ arrayRA[23]);
								logradouro
								.setLogradouroTitulo(logradouroTitulo);
							}
							// id do CEP
							Cep cep = null;
							if (arrayRA[10] != null) {
								cep = new Cep();
								cep.setCepId((Integer) arrayRA[10]);
								cep.setCodigo((Integer) arrayRA[16]);
							}
							
							logradouroCep.setLogradouro(logradouro);
							logradouroCep.setCep(cep);
						}
						
						ra.setLogradouroCep(logradouroCep);
						
						LogradouroBairro logradouroBairro = null;
						if (arrayRA[21] != null) {
							
							logradouroBairro = new LogradouroBairro();
							logradouroBairro.setId((Integer) arrayRA[21]);
							
							Bairro bairro = null;
							// nome bairro
							if (arrayRA[3] != null) {
								bairro = new Bairro();
								bairro.setId((Integer) arrayRA[3]);
								bairro.setCodigo((Integer) arrayRA[17]);
								bairro.setNome("" + arrayRA[4]);
							}
							
							Municipio municipio = null;
							// nome municipio
							if (arrayRA[5] != null) {
								municipio = new Municipio();
								municipio.setId((Integer) arrayRA[5]);
								municipio.setNome("" + arrayRA[6]);
								
								// id da unidade federação
								if (arrayRA[7] != null) {
									UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
									unidadeFederacao
									.setId((Integer) arrayRA[7]);
									unidadeFederacao.setSigla("" + arrayRA[8]);
									municipio
									.setUnidadeFederacao(unidadeFederacao);
								}
								
								bairro.setMunicipio(municipio);
							}
							
							logradouroBairro.setBairro(bairro);
						}
						
						ra.setLogradouroBairro(logradouroBairro);
						
						retorno.setEnderecoOcorrencia(ra
								.getEnderecoFormatadoAbreviado());
					}
					
					SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
					solicitacaoTipo.setId((Integer) arrayRA[25]);
					solicitacaoTipo.setDescricao("" + arrayRA[26]);
					
					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao.setId((Integer) arrayRA[24]);
					solicitacaoTipoEspecificacao
					.setDescricao((String) arrayRA[9]);
					
					solicitacaoTipoEspecificacao
					.setSolicitacaoTipo(solicitacaoTipo);
					
					// Carregando o endereço da ocorrência e a Especificação no
					// objeto de retorno
					retorno
					.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
					retorno.setEnderecoOcorrencia(ra
							.getEnderecoFormatadoAbreviado());
					
					primeiroRegistro = false;
				}
				
				// Id
				ra.setId((Integer) arrayRA[27]);
				
				// complemento endereço
				if (arrayRA[18] != null) {
					ra.setComplementoEndereco("" + arrayRA[18]);
				}
				
				// pontoReferencia
				if (arrayRA[28] != null) {
					ra.setPontoReferencia("" + arrayRA[28]);
				}
				
				// Data do RegistroAtendimento
				if (arrayRA[29] != null) {
					ra.setRegistroAtendimento((Date) arrayRA[29]);
				}
				
				// Carregando todos os RAs pendentes para um mesmo local de
				// ocorrência
				colecaoRegistroAtendimentoRetorno.add(ra);
			}
			
			retorno
			.setColecaoRegistroAtendimento(colecaoRegistroAtendimentoRetorno);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * select a.svtp_id from ATENDIMENTOPUBLICO.SERVICO_TIPO A,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_ATIVIDADE B,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_MATERIAL C WHERE A.SVTP_DSSERVICOTIPO
	 * LIKE '%DESC%' AND A.SVTP_DSABREVIADO LIKE '%DESC%' AND (A.SVTP_VLSERVICO >=
	 * 000000 AND A.SVTP_VLSERVICO <= 99999) AND A.SVTP_ICPAVIMENTO = 1 OU 2 and
	 * A.SVTP_ICATUALIZACOMERCIAL = 1 OU 2 AND A.SVTP_ICTERCEIRIZADO = 1 OU 2
	 * AND A.SVTP_CDSERVICOTIPO = ("O" OR "C") AND (A.SVTP_NNTEMPOMEDIOEXECUCAO >=
	 * 0000 AND A.SVTP_NNTEMPOMEDIOEXECUCAO <= 9999) AND DBTP_ID = ID INFORMADO
	 * AND AND CRTP_ID = ID INFORMADO AND STSG_ID = ID INFORMADO AND STRF_ID =
	 * ID INFORMADO AND STPR_ID = ID INFORMADO AND A.SVTP_ID = B.SVTP_ID AND
	 * B.ATIV_ID IN (ID's INFORMADOS) AND A.SVTP_ID = C.SVTP_ID AND C.MATE_ID IN
	 * (ID's INFORMADOS)
	 * 
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal)
			throws ControladorException {
		Collection servicotipos = null;
		return servicotipos;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0017] - Verificar registro de Atendimento de falta de Água
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * @param idRA
	 * @throws ControladorException
	 */
	public VerificarRAFaltaAguaHelper verificarRegistroAtendimentoFaltaAgua(
			Integer idRegistroAtendimento, Date dataAtendimento,
			Integer idBairroArea, Integer idBairro, Integer idEspecificacao,
			Short indFaltaAgua, Integer indMatricula, String continua)
	throws ControladorException {
		
		VerificarRAFaltaAguaHelper verificarRAFaltaAguaHelper = new VerificarRAFaltaAguaHelper();
		
		String mensagem = null;
		
		try {
			
			// caso esteja atualizado um registro de atendimento de falta de
			// agua
			if (indFaltaAgua != null && indFaltaAgua.equals(new Short("1"))) {
				// caso seja a primeira vez então continua está vazio e
				// pesquisado o programa de abastecimento e manutenção,caso não
				// seja a primeira vez então não necessario pesquisar o
				// programa de manutenção e então segue o fluxo
				if (continua == null || continua.equals("")) {
					mensagem = verificarProgramacaoAbastecimentoManutencao(
							dataAtendimento, idBairroArea, idBairro);
				}
				// caso o programa de abastecimento e ou manutenção esteja vazio
				// então continua o fluxo, senão retorna para quem chamou
				if (mensagem == null) {
					// caso esteja atualizando um registro de atendimento de
					// falta
					// de agua generalizada
					if (indMatricula != null
							&& indMatricula
							.equals(SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_NAO_OBRIGATORIO)) {
						Collection colecaoExibirRAFaltaAguaImovelHelper = null;
						if (idRegistroAtendimento != null
								&& idBairroArea != null
								&& idEspecificacao != null) {
							colecaoExibirRAFaltaAguaImovelHelper = repositorioRegistroAtendimento
							.pesquisarRAAreaBairro(
									idRegistroAtendimento,
									idBairroArea, idEspecificacao);
						}
						// se existe registro de atendimento de falta de Água
						// generalizada em abreto para a mesma area de bairro
						if (colecaoExibirRAFaltaAguaImovelHelper != null
								&& !colecaoExibirRAFaltaAguaImovelHelper
								.isEmpty()) {
							String nomeBairroArea = repositorioRegistroAtendimento
							.pesquisarNomeBairroArea(idBairroArea);
							Integer idMotivoEncerramento = repositorioRegistroAtendimento
							.pesquisarIdAtendimentoEncerramentoMotivo();
							
							// Alterado por Raphael Rossiter em 09/02/2007
							String desSolTipoEspecificacao = repositorioRegistroAtendimento
							.pesquisarDescricaoSolicitacaoTipoEspecificacao(idEspecificacao);
							
							mensagem = "Existe Registro de Atendimento de "
								+ desSolTipoEspecificacao
								+ " em aberto para a Área de bairro "
								+ nomeBairroArea + ".";
							// seta os parametros que serão recuperados pelo
							// action que chamou esse método
							verificarRAFaltaAguaHelper
							.setCasoUso1(VerificarRAFaltaAguaHelper.ENCERRAR_REGISTRO_ATENDIMENTO);
							verificarRAFaltaAguaHelper.setMensagem(mensagem);
							ExibirRAFaltaAguaImovelHelper exibirRAFaltaAguaImovelHelper = (ExibirRAFaltaAguaImovelHelper) Util
							.retonarObjetoDeColecao(colecaoExibirRAFaltaAguaImovelHelper);
							verificarRAFaltaAguaHelper
							.setIdRAReferencia(exibirRAFaltaAguaImovelHelper
									.getIdRA());
							verificarRAFaltaAguaHelper
							.setIdMotivoEncerramento(idMotivoEncerramento);
						}
					}
					// caso esteja atualizando um registro de atendimento de
					// falta
					// de agua no Imóvel
					if (indMatricula != null
							&& indMatricula
							.equals(SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_OBRIGATORIO)) {
						
						Integer idRAReferencia = null;
						if (idBairroArea != null) {
							idRAReferencia = repositorioRegistroAtendimento
							.pesquisarRAAreaBairroFaltaAguaImovel(idBairroArea);
						}
						// se existe registro de atendimento de falta de Água
						// generalizada em aberto para a mesma area de bairro
						if (idRAReferencia != null) {
							String nomeBairroArea = repositorioRegistroAtendimento
							.pesquisarNomeBairroArea(idBairroArea);
							Integer idMotivoEncerramento = repositorioRegistroAtendimento
							.pesquisarIdAtendimentoEncerramentoMotivo();
							String desSolTipoEspecificacao = repositorioRegistroAtendimento
							.descricaoSolTipoEspecFaltaAguaGeneralizada();
							mensagem = "Existe Registro de Atendimento de "
								+ desSolTipoEspecificacao
								+ " em aberto para a Área de bairro "
								+ nomeBairroArea + ".";
							// seta os parametros que serão recuperados pelo
							// action que chamou esse método
							verificarRAFaltaAguaHelper
							.setCasoUso1(VerificarRAFaltaAguaHelper.ENCERRAR_REGISTRO_ATENDIMENTO);
							verificarRAFaltaAguaHelper.setMensagem(mensagem);
							verificarRAFaltaAguaHelper
							.setIdRAReferencia(idRAReferencia);
							verificarRAFaltaAguaHelper
							.setIdMotivoEncerramento(idMotivoEncerramento);
						} else {
							Collection colecaoExibirRAFaltaAguaImovelHelper = null;
							if (idRegistroAtendimento != null
									&& idBairroArea != null
									&& idEspecificacao != null) {
								colecaoExibirRAFaltaAguaImovelHelper = repositorioRegistroAtendimento
								.pesquisarRAAreaBairro(
										idRegistroAtendimento,
										idBairroArea, idEspecificacao);
							}
							// caso exista registro de atendimento de falta de
							// Água no Imóvel em aberto para o mesmo bairro Área
							if (colecaoExibirRAFaltaAguaImovelHelper != null
									&& !colecaoExibirRAFaltaAguaImovelHelper
									.isEmpty()) {
								String nomeBairroArea = repositorioRegistroAtendimento
								.pesquisarNomeBairroArea(idBairroArea);
								String desSolTipoEspecificacao = repositorioRegistroAtendimento
								.descricaoSolTipoEspecAguaGeneralizada(idEspecificacao);
								mensagem = "Existe Registro de Atendimento de "
									+ desSolTipoEspecificacao
									+ " em aberto para a Área de bairro "
									+ nomeBairroArea + ".";
								// seta os parametros que serão recuperados pelo
								// action que chamou esse método
								verificarRAFaltaAguaHelper
								.setCasoUso1(VerificarRAFaltaAguaHelper.EXIBIR_RA_FALTA_AGUA_IMOVEL);
								verificarRAFaltaAguaHelper
								.setMensagem(mensagem);
								
								// Abrir registro de atendimento de falta de
								// Água generalizada passando a solicitação e a
								// especificação pré determinadas
								// seta os parametros que serão recuperados pelo
								// action que chamou esse método
								verificarRAFaltaAguaHelper
								.setCasoUso2(VerificarRAFaltaAguaHelper.INSERIR_REGISTRO_ATENDIMENTO);
								
							}
						}
					}
				} else {
					// seta os parametros que serão recuperados pelo
					// action que chamou esse método
					verificarRAFaltaAguaHelper
					.setCasoUso1(VerificarRAFaltaAguaHelper.CONSULTAR_PROGRAMACAO);
					verificarRAFaltaAguaHelper.setMensagem(mensagem);
				}
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return verificarRAFaltaAguaHelper;
		
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0017] - Verificar registro de Atendimento de falta de Água
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @throws ControladorException
	 */
	public VerificarRAFaltaAguaHelper verificarRegistroAtendimentoFaltaAguaInserir(
			Date dataAtendimento, Integer idBairroArea, Integer idBairro,
			Integer idEspecificacao, Short indFaltaAgua, Integer indMatricula,
			String continua) throws ControladorException {
		
		VerificarRAFaltaAguaHelper verificarRAFaltaAguaHelper = new VerificarRAFaltaAguaHelper();
		
		String mensagem = null;
		
		try {
			
			// caso esteja atualizado um registro de atendimento de falta de
			// agua
			if (indFaltaAgua != null && indFaltaAgua.equals(new Short("1"))) {
				// caso seja a primeira vez então continua está vazio e é
				// pesquisado o programa de abastecimento e manutenção,caso não
				// seja a primeira vez então não necessario pesquisar o
				// programa de manutenção e então segue o fluxo
				if (continua == null || continua.equals("")) {
					mensagem = verificarProgramacaoAbastecimentoManutencao(
							dataAtendimento, idBairroArea, idBairro);
				}
				// caso o programa de abastecimento e ou manutenção esteja vazio
				// então continua o fluxo, senão retorna para quem chamou
				if (mensagem == null) {
					// caso esteja atualizando um registro de atendimento de
					// falta
					// de agua generalizada
					if (indMatricula != null
							&& indMatricula
							.equals(SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_NAO_OBRIGATORIO)) {
						Collection colecaoExibirRAFaltaAguaImovelHelper = null;
						if (idBairroArea != null && idEspecificacao != null) {
							colecaoExibirRAFaltaAguaImovelHelper = repositorioRegistroAtendimento
							.pesquisarRAAreaBairroInserir(idBairroArea,
									idEspecificacao);
						}
						// se existe registro de atendimento de falta de Água
						// generalizada em abreto para a mesma area de bairro
						if (colecaoExibirRAFaltaAguaImovelHelper != null
								&& !colecaoExibirRAFaltaAguaImovelHelper
								.isEmpty()) {
							String nomeBairroArea = repositorioRegistroAtendimento
							.pesquisarNomeBairroArea(idBairroArea);
							
							// Alterado por Raphael Rossiter em 09/02/2007
							String desSolTipoEspecificacao = repositorioRegistroAtendimento
							.pesquisarDescricaoSolicitacaoTipoEspecificacao(idEspecificacao);
							
							mensagem = "Existe Registro de Atendimento de "
								+ desSolTipoEspecificacao
								+ " em aberto para a Área de bairro "
								+ nomeBairroArea + ".";
							// seta os parametros que serão recuperados pelo
							// action que chamou esse método
							verificarRAFaltaAguaHelper
							.setCasoUso1(VerificarRAFaltaAguaHelper.EXIBIR_ADICIONAR_SOLICITANTE);
							verificarRAFaltaAguaHelper.setMensagem(mensagem);
							
							ExibirRAFaltaAguaImovelHelper exibirRAFaltaAguaImovelHelper = (ExibirRAFaltaAguaImovelHelper) Util
							.retonarObjetoDeColecao(colecaoExibirRAFaltaAguaImovelHelper);
							
							verificarRAFaltaAguaHelper
							.setIdRAReferencia(exibirRAFaltaAguaImovelHelper
									.getIdRA());
						}
					}
					// caso esteja atualizando um registro de atendimento de
					// falta
					// de agua no Imóvel
					if (indMatricula != null
							&& indMatricula
							.equals(SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_OBRIGATORIO)) {
						
						Integer idRAReferencia = null;
						if (idBairroArea != null) {
							idRAReferencia = repositorioRegistroAtendimento
							.pesquisarRAAreaBairroFaltaAguaImovel(idBairroArea);
						}
						// se existe registro de atendimento de falta de Água
						// generalizada em aberto para a mesma area de bairro
						if (idRAReferencia != null) {
							String nomeBairroArea = repositorioRegistroAtendimento
							.pesquisarNomeBairroArea(idBairroArea);
							String desSolTipoEspecificacao = repositorioRegistroAtendimento
							.descricaoSolTipoEspecFaltaAguaGeneralizada();
							mensagem = "Existe Registro de Atendimento de "
								+ desSolTipoEspecificacao
								+ " em aberto para a Área de bairro "
								+ nomeBairroArea + ".";
							// seta os parametros que serão recuperados pelo
							// action que chamou esse método
							verificarRAFaltaAguaHelper
							.setCasoUso1(VerificarRAFaltaAguaHelper.EXIBIR_ADICIONAR_SOLICITANTE);
							verificarRAFaltaAguaHelper.setMensagem(mensagem);
							
							verificarRAFaltaAguaHelper
							.setIdRAReferencia(idRAReferencia);
							
						} else {
							Collection colecaoExibirRAFaltaAguaImovelHelper = null;
							if (idBairroArea != null && idEspecificacao != null) {
								colecaoExibirRAFaltaAguaImovelHelper = repositorioRegistroAtendimento
								.pesquisarRAAreaBairroInserir(
										idBairroArea, idEspecificacao);
							}
							// caso exista registro de atendimento de falta de
							// Água no Imóvel em aberto para o mesmo bairro Área
							if (colecaoExibirRAFaltaAguaImovelHelper != null
									&& !colecaoExibirRAFaltaAguaImovelHelper
									.isEmpty()) {
								String nomeBairroArea = repositorioRegistroAtendimento
								.pesquisarNomeBairroArea(idBairroArea);
								String desSolTipoEspecificacao = repositorioRegistroAtendimento
								.descricaoSolTipoEspecAguaGeneralizada(idEspecificacao);
								mensagem = "Existe Registro de Atendimento de "
									+ desSolTipoEspecificacao
									+ " em aberto para a Área de bairro "
									+ nomeBairroArea + ".";
								// seta os parametros que serão recuperados pelo
								// action que chamou esse método
								verificarRAFaltaAguaHelper
								.setCasoUso1(VerificarRAFaltaAguaHelper.EXIBIR_RA_FALTA_AGUA_IMOVEL);
								verificarRAFaltaAguaHelper
								.setMensagem(mensagem);
								
								// Abrir registro de atendimento de falta de
								// Água generalizada passando a solicitação e a
								// especificação pré determinadas
								// seta os parametros que serão recuperados pelo
								// action que chamou esse método
								verificarRAFaltaAguaHelper
								.setCasoUso2(VerificarRAFaltaAguaHelper.INSERIR_REGISTRO_ATENDIMENTO);
								
							}
						}
					}
				} else {
					// seta os parametros que serão recuperados pelo
					// action que chamou esse método
					verificarRAFaltaAguaHelper
					.setCasoUso1(VerificarRAFaltaAguaHelper.CONSULTAR_PROGRAMACAO);
					verificarRAFaltaAguaHelper.setMensagem(mensagem);
				}
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return verificarRAFaltaAguaHelper;
		
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0018] - Verificar Programação de Abastecimento e/ou de Manutenção
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * @param idRA
	 * @throws ControladorException
	 */
	public String verificarProgramacaoAbastecimentoManutencao(
			Date dataAtendimento, Integer idBairroArea, Integer idBairro)
	throws ControladorException {
		
		String mensagemRetorno = null;
		
		try {
			Integer idAbastecimentoProgramacao = null;
			if (dataAtendimento != null && idBairroArea != null
					&& idBairro != null) {
				idAbastecimentoProgramacao = repositorioRegistroAtendimento
				.verificarOcorrenciaAbastecimentoProgramacao(
						dataAtendimento, idBairroArea, idBairro);
			}
			
			// caso não exista ocorrência na tabela de abastecimentoProgramacao
			if (idAbastecimentoProgramacao == null) {
				String nomeBairroArea = repositorioRegistroAtendimento
				.pesquisarNomeBairroArea(idBairroArea);
				String dataAtendimentoString = Util
				.formatarData(dataAtendimento);
				mensagemRetorno = "não há programação de abastecimento no dia "
					+ dataAtendimentoString + " para a Área de bairro "
					+ nomeBairroArea + ".";
			}
			// caso exista programação de abastecimento para a Área de bairro
			// (caso a mensagem de retorno esteja nula)
			if (mensagemRetorno == null) {
				Integer idManutencaoProgramacao = null;
				if (dataAtendimento != null && idBairroArea != null
						&& idBairro != null) {
					idManutencaoProgramacao = repositorioRegistroAtendimento
					.verificarOcorrenciaManutencaoProgramacao(
							dataAtendimento, idBairroArea, idBairro);
				}
				// caso exista ocorrência na tabela manutenção
				if (idManutencaoProgramacao != null) {
					String nomeBairroArea = repositorioRegistroAtendimento
					.pesquisarNomeBairroArea(idBairroArea);
					String dataAtendimentoString = Util
					.formatarData(dataAtendimento);
					mensagemRetorno = "Há manutenção prevista no dia "
						+ dataAtendimentoString + " para a Área de bairro "
						+ nomeBairroArea + ".";
				}
			}
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		return mensagemRetorno;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0019] - Exibe Registros de Atendimentos de falta de Água no Imóvel
	 * 
	 * @author Sávio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idRA
	 * @throws ControladorException
	 */
	public RAFaltaAguaPendenteHelper carregarObjetoRAFaltaAguaPendente(
			Integer idRegistroAtendimento, Integer idBairroArea,
			Integer idEspecificacao) throws ControladorException {
		
		RAFaltaAguaPendenteHelper rAFaltaAguaPendenteHelper = new RAFaltaAguaPendenteHelper();
		
		try {
			
			Collection colecaoExibirRAFaltaAguaImovelHelper = null;
			
			// Atualizar RA
			if (idRegistroAtendimento != null) {
				
				colecaoExibirRAFaltaAguaImovelHelper = repositorioRegistroAtendimento
				.pesquisarRAAreaBairro(idRegistroAtendimento,
						idBairroArea, idEspecificacao);
				
				// [SB0019] - Exibir Registros de Atendimento de
				// Falta de Água no Imóvel da Área do Bairro
				Object[] parmsFaltaAguaAreaBairro = repositorioRegistroAtendimento
				.pesquisarParmsRAFaltaAguaImovel(idRegistroAtendimento);
				if (parmsFaltaAguaAreaBairro != null) {
					// id da solicitação tipo
					if (parmsFaltaAguaAreaBairro[0] != null) {
						rAFaltaAguaPendenteHelper
						.setIdSolicitacaoTipo((Integer) parmsFaltaAguaAreaBairro[0]);
					}
					// descrição da solicitação tipo
					if (parmsFaltaAguaAreaBairro[1] != null) {
						rAFaltaAguaPendenteHelper
						.setDescricaoSolicitacaoTipo((String) parmsFaltaAguaAreaBairro[1]);
					}
					// id da solicitação tipo especificação
					if (parmsFaltaAguaAreaBairro[2] != null) {
						rAFaltaAguaPendenteHelper
						.setIdSolicitacaoTipoEspecificacao((Integer) parmsFaltaAguaAreaBairro[2]);
					}
					// descrição da solicitação tipo
					// especificação
					if (parmsFaltaAguaAreaBairro[3] != null) {
						rAFaltaAguaPendenteHelper
						.setDescricaoSolicitacaoTipoEspecificacao((String) parmsFaltaAguaAreaBairro[3]);
					}
					// codigo do bairro
					if (parmsFaltaAguaAreaBairro[4] != null) {
						rAFaltaAguaPendenteHelper
						.setCodigoBairro((Integer) parmsFaltaAguaAreaBairro[4]);
					}
					// nome bairro
					if (parmsFaltaAguaAreaBairro[5] != null) {
						rAFaltaAguaPendenteHelper
						.setNomeBairro((String) parmsFaltaAguaAreaBairro[5]);
					}
					// id do bairro da Área
					if (parmsFaltaAguaAreaBairro[6] != null) {
						rAFaltaAguaPendenteHelper
						.setIdBairroArea((Integer) parmsFaltaAguaAreaBairro[6]);
					}
					// nome bairro da Área
					if (parmsFaltaAguaAreaBairro[7] != null) {
						rAFaltaAguaPendenteHelper
						.setNomeBairroArea((String) parmsFaltaAguaAreaBairro[7]);
					}
				}
			}
			// Inserir RA
			else {
				
				colecaoExibirRAFaltaAguaImovelHelper = repositorioRegistroAtendimento
				.pesquisarRAAreaBairroInserir(idBairroArea,
						idEspecificacao);
			}
			
			// seta a coleção de falta de agua para a mesma
			// Área de bairro
			rAFaltaAguaPendenteHelper
			.setColecaoExibirRAFaltaAguaHelper(colecaoExibirRAFaltaAguaImovelHelper);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		return rAFaltaAguaPendenteHelper;
	}
	
	/**
	 * [UC0427] Tramitar Registro de Atendimento
	 * 
	 * Validar Tramitação
	 * 
	 * [FS0001] Verificar se o RA está cancelado ou bloqueado. [FS0002]
	 * Verificar situações das OS(ordem de Serviço) associadas ao RA [FS0003]
	 * Verificar se o tipo de solicitação Tarifa Social [FS0008] Validar Unidade
	 * de Destino
	 * 
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 * 
	 * @param tramite
	 * @throws ControladorException
	 */
	public void validarTramitacao(Tramite tramite, Usuario usuario)
	throws ControladorException {
		
		if (tramite != null) {
			
			boolean possuiOSTerceirizado = false;
			
			boolean possuiOSPendente = false;
			
			// [FS0001]
			if (new Short(tramite.getRegistroAtendimento().getCodigoSituacao())
					.intValue() == RegistroAtendimento.SITUACAO_ENCERRADO
					.intValue()
					|| new Short(tramite.getRegistroAtendimento()
							.getCodigoSituacao()).intValue() == RegistroAtendimento.SITUACAO_BLOQUEADO
							.intValue()) {
				
				sessionContext.setRollbackOnly();
				throw new ControladorException(
				"atencao.tramitar_ra_situacao_bloqueado_encerrado");
			}
			
			// [FS0002]
			Collection<OrdemServico> colecaoOrdemServico = obterOSRA(tramite
					.getRegistroAtendimento().getId());
			
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {
				
				for (Iterator iter = colecaoOrdemServico.iterator(); iter
				.hasNext();) {
					
					OrdemServico element = (OrdemServico) iter.next();
					
					if ((new Short(element.getSituacao())).intValue() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO
							.intValue()) {
						
						// Caso 1
						sessionContext.setRollbackOnly();
						throw new ControladorException(
						"atencao.tramitar_ra_os_em_andamento");
						
					} else if ((new Short(element.getSituacao())).intValue() == OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO
							.intValue()) {
						
						// Caso 2
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.tramitar_ra_os_referencia", null,
								element.getOsReferencia().getId() + "");
						
					} else if ((new Short(element.getSituacao())).intValue() == OrdemServico.SITUACAO_PENDENTE
							.intValue()) {
						
						// Caso 3
						if (getControladorOrdemServico()
								.verificarExistenciaOSProgramada(
										element.getId())) {
							sessionContext.setRollbackOnly();
							throw new ControladorException(
							"atencao.tramitar_ra_os_programada");
						}
						
						possuiOSPendente = true;
						
						// [FS0008 Caso 4]
						if ((new Short(element.getServicoTipo()
								.getIndicadorTerceirizado())).intValue() == 1) {
							possuiOSTerceirizado = true;
						}
					}
				}
			}
			
			// [UC0418] - Obter Unidade Atual do RA
			UnidadeOrganizacional unidadeAtual = obterUnidadeAtualRA(tramite
					.getRegistroAtendimento().getId());
			
			
			// ALTERAR VALIDAÇÃO PARA SISTEMA PARAMETRO
			// RAFAEL CORRÊA 16/10/2008
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			
			if (sistemaParametro.getIndicadorControleTramitacaoRA().equals(ConstantesSistema.SIM)) {
				
				// [UC0419] - Obter Indicador de Autorização para Manutenção do RA
				Short indicadorAutorizacao = this
				.obterIndicadorAutorizacaoManutencaoRA(unidadeAtual
						.getId(), usuario.getId());
				
				if (ConstantesSistema.NAO.equals(indicadorAutorizacao)) {
					
					// [FS0003]
					if ((new Short(tramite.getRegistroAtendimento()
							.getSolicitacaoTipoEspecificacao()
							.getSolicitacaoTipo().getIndicadorTarifaSocial()))
							.intValue() != 1
							|| new Short(tramite.getUsuarioRegistro()
									.getUnidadeOrganizacional()
									.getIndicadorTarifaSocial()).intValue() != 1) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
						"atencao.tramitar_ra_tarifa_social");
					}
					
				}
				
			}
			
			// [FS0006] Validar Data
			// [FS0007] Validar Hora
			Date dataCorrente = new Date();
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(tramite
					.getDataTramite(), dataCorrente);
			
			if (qtdeDias < 0) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.tramitar_ra_data_maior_que_atual", null, Util
						.formatarData(dataCorrente));
				
			} else if (Util.datasIguais(tramite.getDataTramite(), dataCorrente)) {
				if (Util.compararHoraMinuto(Util.formatarHoraSemData(tramite
						.getDataTramite()), Util
						.formatarHoraSemData(dataCorrente), ">")) {
					
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.tramitar_ra_hora_maior_que_atual", null,
							Util.formatarHoraSemData(dataCorrente));
				}
			}
			
			// [FS0008]
			// Caso 1
			if (tramite.getUnidadeOrganizacionalOrigem().getId().intValue() == tramite
					.getUnidadeOrganizacionalDestino().getId().intValue()) {
				
				sessionContext.setRollbackOnly();
				throw new ControladorException(
				"atencao.tramitar_ra_unidade_origem_destino_iguais");
			}
			
			// Caso 2
			if ((new Short(tramite.getUnidadeOrganizacionalDestino()
					.getIndicadorTramite())).intValue() == 2) {
				if ((new Short(tramite.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo()
						.getIndicadorTarifaSocial())).intValue() == 2) {
					
					sessionContext.setRollbackOnly();
					throw new ControladorException(
					"atencao.tramitar_ra_unidade_destino_tramite");
				}
			}
			
			//alteracao adicionada por Anderson Italo
			//Adicionado validação para verificar indicador de uso da unidadeDestino
			//data: 22/07/2009
			if ((new Short(tramite.getUnidadeOrganizacionalDestino().getIndicadorUso()))
					.intValue() == 2) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.unidade_destino_inativa", null, tramite.getUnidadeOrganizacionalDestino().getId().toString());
			}
			//fim alteracao
			
			if (tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo() != null
					&& tramite.getUnidadeOrganizacionalDestino()
					.getUnidadeTipo().getCodigoTipo() != null) {
				
				if (tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo()
						.getCodigoTipo().equals(
								UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {
					
					// Caso 3
					if (tramite.getUnidadeOrganizacionalOrigem()
							.getUnidadeTipo() == null
							|| tramite.getUnidadeOrganizacionalOrigem()
							.getUnidadeTipo().getCodigoTipo() == null
							|| !tramite
							.getUnidadeOrganizacionalOrigem()
							.getUnidadeTipo()
							.getCodigoTipo()
							.equals(
									UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)) {
						
						sessionContext.setRollbackOnly();
						throw new ControladorException(
						"atencao.tramitar_ra_unidade_centralizadora");
					}
					// Caso 4
					if (!possuiOSTerceirizado) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.tramitar_ra_os_possui_terceiros",
								null,tramite.getRegistroAtendimento().getId().toString());
					}
				}
				// Caso 5
				if (tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo()
						.getCodigoTipo().equals(
								UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)
								&& !tramite.getUnidadeOrganizacionalOrigem().getUnidadeTipo()
								.getCodigoTipo().equals(
										UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)){
					
					// Caso seja COMPESA: 
					// Alteracao para permitir uma adequacao a nova estrutura implantada na COMPESA
					// Francisco/Fátima : 10/12/2009
					if(sistemaParametro.getNomeAbreviadoEmpresa()!= null && 
							sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_COMPESA)
						&& possuiOSPendente){
						
						Collection<Integer> colecaoUnidades = null;
						try{
							colecaoUnidades = repositorioRegistroAtendimento.
							pesquisarPossiveisUnidadesCentralizadorasRa(tramite.getRegistroAtendimento().getId());
						} catch (ErroRepositorioException ex) {
							sessionContext.setRollbackOnly();
							ex.printStackTrace();
							throw new ControladorException("erro.sistema", ex);
						}
						
						if(colecaoUnidades == null || colecaoUnidades.isEmpty()){
							sessionContext.setRollbackOnly();
							throw new ControladorException(
							"atencao.tramitar_ra_unidade_origem_central");
						} else {

							boolean encontrouUnidadeValida = false;
							
							for (Iterator iter = colecaoUnidades.iterator(); iter
									.hasNext();) {
								Integer idUnidade = (Integer) iter.next();
								if (idUnidade.intValue() == tramite
										.getUnidadeOrganizacionalDestino().getId().intValue()){
									encontrouUnidadeValida = true;
								}
							}
							if (!encontrouUnidadeValida){
								sessionContext.setRollbackOnly();
								throw new ControladorException(
								"atencao.tramitar_ra_unidade_origem_central");
							}
						}
								
					} else {						
					
						Integer unidadeCentralizadora = 0;
						try{
							unidadeCentralizadora = repositorioRegistroAtendimento.
							pesquisarUnidadeCentralizadoraRa(tramite.getRegistroAtendimento().getId());
						} catch (ErroRepositorioException ex) {
							sessionContext.setRollbackOnly();
							ex.printStackTrace();
							throw new ControladorException("erro.sistema", ex);
						}
						if(unidadeCentralizadora == null || unidadeCentralizadora!= tramite
								.getUnidadeOrganizacionalDestino().getId()
								.intValue()){
							sessionContext.setRollbackOnly();
							throw new ControladorException(
							"atencao.tramitar_ra_unidade_origem_central");
						}
					}
				}
			}
			
			//caso 6
			//caso a unidade atual do registro seja "terceirizada" e 
			//o usuario não estiver lotado nesta unidade
			
			if (tramite.getUnidadeOrganizacionalOrigem().getUnidadeTipo() != null
					&& tramite.getUnidadeOrganizacionalOrigem().getUnidadeTipo()
					.getCodigoTipo() != null) {
				
				if (tramite.getUnidadeOrganizacionalOrigem().getUnidadeTipo()
						.getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)
						&& !usuario.getUnidadeOrganizacional().getId()
						.equals(tramite.getUnidadeOrganizacionalOrigem().getId())){
					
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.tramitar_ra_unidade_usuario");
				}
			}
			
			//adicionado por Vivianne Sousa, 29/10/2008
			//analista responsavel:Fabiola
			//[FS0011]Validar Tramite para terceira
			if (tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo() != null
					&& tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo().getCodigoTipo() != null
					&& tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo()
					.getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {
				
				boolean existe = getControladorOrdemServico().existeMaisDeUmaOrdemServicoPendente(
						tramite.getRegistroAtendimento().getId());
				
				if (existe){
					
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.tramitar_ra_possui_mais_de_uma_os_pensente",
							null, tramite.getRegistroAtendimento().getId().toString());
					
				}
			}
			
		}
	}
	
	/**
	 * [UC0427] Tramitar Registro de Atendimento
	 * 
	 * Tramitar
	 * 
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 * 
	 * @param tramite
	 * @param dataConcorrente
	 * @throws ControladorException
	 */
	public void tramitar(Tramite tramite, Date dataConcorrente)
	throws ControladorException {
		
		Tramite ultimoTramitebase = this.pesquisarUltimaDataTramite(tramite
				.getRegistroAtendimento().getId());
		
		java.sql.Timestamp dataTramiteTramite = new java.sql.Timestamp(tramite
				.getDataTramite().getTime());
		
		if (ultimoTramitebase != null
				&& ultimoTramitebase.getDataTramite().compareTo(
						dataTramiteTramite) == 0) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
			"atencao.tramite_existente_dia_hora_informada");
			
		} else {
			
			try {
				Date dataAtual = repositorioRegistroAtendimento
				.verificarConcorrenciaRA(tramite
						.getRegistroAtendimento().getId());
				
				if (dataConcorrente != null && dataAtual != null) {
					
					if ((dataAtual.after(dataConcorrente))) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
						"atencao.tramitar_ra_usuario_concorrente");
					}
				}
				
				getControladorUtil().inserir(tramite);
				
				// inclusão da coluna unidade atual na tabela REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// Vivianne Sousa 10/06/2008 analista:Fátima Sampaio
				
				//6.2 Atualiza a unidade atual do registro de atendimento 
				// e das ordens de servico associadas a ele
				this.atualizarUnidadeOrganizacionalAtualRA(
						tramite.getUnidadeOrganizacionalDestino().getId(), 
						tramite.getRegistroAtendimento().getId());
				
				getControladorOrdemServico().atualizarUnidadeOrganizacionalAtualOS(
						tramite.getUnidadeOrganizacionalDestino().getId(), 
						tramite.getRegistroAtendimento().getId());
				
				
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a matrícula do Imóvel da Aba Dados do Local da ocorrência tenha sido
	 * informada e o Cliente informado não seja um cliente do Imóvel (inexiste
	 * ocorrência na tabela CLIENTE_IMOVEL com CLIE_ID=Id do cliente e
	 * IMOV_ID=matrícula do Imóvel e CLIM_DTRELACAOFIM com o valor nulo).
	 * 
	 * [FS0027] Verificar informação do Imóvel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idCliente,
	 *            idImovel
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente verificarInformacaoImovel(Integer idCliente,
			Integer idImovel, boolean levantarException)
	throws ControladorException {
		
		Cliente retorno = null;
		
		/*TODO: COSANPA
		 * 
		 * Data: 24/01/2011
		 * 
		 * Retirada da pesquisa de cliente por imóvel, que limitava ao solicitante da RA está vinculado ao imóvel.
		 */
		
/*		if (idImovel != null) {
			ClienteImovel clienteImovel = this.getControladorCliente()
			.pesquisarClienteImovel(idCliente, idImovel);
			
			if (clienteImovel != null) {
				retorno = clienteImovel.getCliente();
			} else {
				throw new ControladorException(
						"atencao.cliente_informado_nao_corresponde_imovel",
						null, idImovel.toString());
			}
		} else {*/
			retorno = this.getControladorCliente().pesquisarCliente(idCliente);
			
			if (retorno == null && levantarException) {
				throw new ControladorException("atencao.cliente.inexistente",
						null, idCliente.toString());
			}
		//}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado).
	 * 
	 * [FS0012] Verificar existência do cliente solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaClienteSolicitante(
			Integer idRegistroAtendimento, Integer idCliente)
	throws ControladorException {
		
		Integer qtdRegistros = null;
		
		try {
			qtdRegistros = this.repositorioRegistroAtendimento
			.verificarExistenciaClienteSolicitante(
					idRegistroAtendimento, idCliente);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (qtdRegistros != null && qtdRegistros > 0) {
			throw new ControladorException(
					"atencao.cliente_solicitante_ja_informado_registro_atendimento",
					null, idCliente.toString(), idRegistroAtendimento
					.toString());
		}
		
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada).
	 * 
	 * [FS0026] Verificar existência da unidade solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadeSolicitante(
			Integer idRegistroAtendimento, Integer idUnidade)
	throws ControladorException {
		
		Integer qtdRegistros = null;
		
		try {
			qtdRegistros = this.repositorioRegistroAtendimento
			.verificarExistenciaUnidadeSolicitante(
					idRegistroAtendimento, idUnidade);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (qtdRegistros != null && qtdRegistros > 0) {
			throw new ControladorException(
					"atencao.unidade_solicitante_ja_informado_registro_atendimento",
					null, idUnidade.toString(), idRegistroAtendimento
					.toString());
		}
		
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento e [UC0408] Atualizar Registro de
	 * Atendimento
	 * 
	 * 
	 * [FS0040] Verificar preenchimento campos 2 ABA
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void validarCamposObrigatoriosRA_2ABA(String idImovel,
			String pontoReferencia, String idMunicipio,
			String descricaoMunicipio, String cdBairro, String descricaoBairro,
			String idAreaBairro, String idlocalidade,
			String descricaoLocalidade, String cdSetorComercial,
			String descricaoSetorComercial, String numeroQuadra,
			String idDivisaoEsgoto, String idUnidade, String descricaoUnidade,
			String idLocalOcorrencia, String idPavimentoRua,
			String idPavimentoCalcada, String descricaoLocalOcorrencia,
			String imovelObrigatorio, String pavimentoRuaObrigatorio,
			String pavimentoCalcadaObrigatorio,
			String solicitacaoTipoRelativoFaltaAgua,
			String solicitacaoTipoRelativoAreaEsgoto,
			String desabilitarMunicipioBairro, String indRuaLocalOcorrencia,
			String indCalcadaLocalOcorrencia, Integer idEspecificacao,
			Integer idRAAtualizacao, 
			Collection colecaoEndereco,
			SolicitacaoTipoEspecificacao especificacao,
			Collection colecaoPagamento, Usuario usuarioLogado) throws ControladorException {
		
		short indicadorInformarPagamentoDuplicidade = especificacao.getIndicadorInformarPagamentoDuplicidade();
		
		if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue()){
			
			if(colecaoPagamento == null || colecaoPagamento.isEmpty()){
				throw new ControladorException("nao_exite_pagamento_duplicidade");
			}
		}
		
		boolean camposLO = 
			this.validarCamposHabilitadosLocalOcorrencia(
				idImovel, 
				pontoReferencia, 
				idMunicipio, 
				cdBairro, 
				idAreaBairro,
				idlocalidade, 
				cdSetorComercial, 
				numeroQuadra, 
				idDivisaoEsgoto,
				idUnidade, 
				idLocalOcorrencia, 
				idPavimentoRua,
				idPavimentoCalcada);
		
		if (camposLO || 
			(descricaoLocalOcorrencia != null && !descricaoLocalOcorrencia.equals(""))) {
			
			if (camposLO) {
				// se o indicador do Imóvel for diferente de nulo e o Imóvel
				// estiver nulo
				if (imovelObrigatorio != null && imovelObrigatorio.equals("SIM")) {
					if (idImovel == null || idImovel.equals("")) {
						throw new ControladorException("atencao.required",null, "Imóvel");
					}
				}
				
				// Verificação de endereço obrigatório
				if ((idImovel == null || idImovel.equalsIgnoreCase("")) && 
					(solicitacaoTipoRelativoFaltaAgua != null && solicitacaoTipoRelativoFaltaAgua.equals("NAO")) && 
					(colecaoEndereco == null || colecaoEndereco.isEmpty())) {
					
					throw new ControladorException("atencao.required", null,"Endereço");
				}
				
				if (solicitacaoTipoRelativoFaltaAgua != null && solicitacaoTipoRelativoFaltaAgua.equals("SIM")) {
					if (desabilitarMunicipioBairro == null) {
						if (idMunicipio == null || idMunicipio.equals("")) {
							throw new ControladorException("atencao.required",null, "Município");
							
						}
						if (cdBairro == null || cdBairro.equals("")) {
							throw new ControladorException("atencao.required",null, "Município");
						}
					}
					
					if (idAreaBairro == null || idAreaBairro.equals("")) {
						throw new ControladorException("atencao.required",null, "Área do Bairro");
					}
				}
				// valida a localidade o setor comercial e a divisão de esgoto
				if (idImovel == null || idImovel.equals("")) {
					if (idlocalidade == null || idlocalidade.equals("")) {
						throw new ControladorException("atencao.required",null, "Localidade");
					}
					// valida o setor comercial
					if (cdSetorComercial == null || cdSetorComercial.equals("")) {
						if (numeroQuadra != null && !numeroQuadra.equals("")) {
							throw new ControladorException("atencao.required",null, "Setor Comercial");
						}
					}
					if (solicitacaoTipoRelativoAreaEsgoto != null && solicitacaoTipoRelativoAreaEsgoto.equals("SIM")) {
						if (idDivisaoEsgoto == null|| idDivisaoEsgoto.equals("")) {
							throw new ControladorException("atencao.required",null, "divisão de Esgoto");
						}
					}
				}
				if ((pavimentoRuaObrigatorio != null && pavimentoRuaObrigatorio.equals("SIM")) || 
					(indRuaLocalOcorrencia != null && indRuaLocalOcorrencia.equals("1"))) {
					
					if (idPavimentoRua == null || idPavimentoRua.equals("")) {
						throw new ControladorException("atencao.required",null, "Pavimento da Rua");
					}
				}
				if ((pavimentoCalcadaObrigatorio != null && pavimentoCalcadaObrigatorio.equals("SIM")) || 
					(indCalcadaLocalOcorrencia != null && indCalcadaLocalOcorrencia.equals("1"))) {
					
					if (idPavimentoCalcada == null || idPavimentoCalcada.equals("")) {
						throw new ControladorException("atencao.required",null, "Pavimento da Calçada");
					}
				}
			}
		} else {
			throw new ControladorException(
			"atencao.informar_descricao_ou_dados_local_ocorrencia");
		}
		
		/*
		 * Validações para o imóvel - Colocado por Raphael Rossiter em
		 * 20/02/2007. Caso a especificação venha com valor nulo, não será
		 * necessário realizar o [SB0020] - Verifica Situação do Imóvel e
		 * Especificação (O método foi invocado pelo Manter RA)
		 */
		if (idImovel != null && !idImovel.equals("")) {
			
			Imovel imovelSoId = 
				this.getControladorImovel().pesquisarImovelRegistroAtendimento(new Integer(idImovel));
			
			// Inserir RA
			if (idRAAtualizacao == null) {
				
				// [SB0020] - Verifica Situação do Imóvel e Especificação
				this.verificarSituacaoImovelEspecificacao(imovelSoId,
						idEspecificacao);
				
				// [SB0032] - Verifica se o imóvel informado tem débito
				this.verificarImovelComDebitos(idEspecificacao, imovelSoId
						.getId());
				
				this.verificarAlteracaoVencimentoRecente(idEspecificacao, usuarioLogado, imovelSoId.getId());
				
			}
			// Manter RA
			else {
				
				/*
				 * Caso o usuário tenha modificado a matrícula do imóvel, será
				 * necessário invocar o [SB0032] - Verifica se o imóvel
				 * informado tem débito
				 */
				if (idEspecificacao != null) {
					
					/*
					 * parametrosRA[7] = idSolicitacaoTipoEspecificacao
					 * parametrosRA[10] = idImovel
					 */
					Object[] parametrosRA = this.pesquisarParmsRegistroAtendimento(idRAAtualizacao);
					
					if (parametrosRA != null && parametrosRA[10] != null && 
						parametrosRA[10] != imovelSoId.getId()) {
						
						// [SB0032] - Verifica se o imóvel informado tem débito
						this.verificarImovelComDebitos(idEspecificacao,imovelSoId.getId());
					}
				}
			}
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0030] Verificar preenchimento dos dados de identificação do
	 * solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public boolean validarCamposHabilitadosLocalOcorrencia(String idImovel,
			String pontoReferencia, String idMunicipio, String cdBairro,
			String idAreaBairro, String idlocalidade, String cdSetorComercial,
			String numeroQuadra, String idDivisaoEsgoto, String idUnidade,
			String idLocalOcorrencia, String idPavimentoRua,
			String idPavimentoCalcada) {
		boolean camposLO = false;
		
		String[] dadoslocalOcorrencia = { idImovel, pontoReferencia,
				idMunicipio, cdBairro, idAreaBairro, idlocalidade,
				cdSetorComercial, numeroQuadra, idDivisaoEsgoto, idUnidade,
				idLocalOcorrencia, idPavimentoRua, idPavimentoCalcada };
		
		for (int i = 0; i < dadoslocalOcorrencia.length; i++) {
			if (dadoslocalOcorrencia[i] != null
					&& !dadoslocalOcorrencia[i].equals("")
					&& !dadoslocalOcorrencia[i].equalsIgnoreCase(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				camposLO = true;
				break;
			}
		}
		
		return camposLO;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0030] Verificar preenchimento dos dados de identificação do
	 * solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificaDadosSolicitante(Integer idCliente,
			Integer idUnidadeSolicitante, Integer idFuncionario,
			String nomeSolicitante, Collection colecaoEndereco,
			Collection colecaoFone, Short indicadorClienteEspecificacao,
			Integer idImovel, Integer idRegistroAtendimento,
			Integer idEspecificacao, Integer idMeioSolicitacao) 
		throws ControladorException {
		
		// Validação de campos requeridos
		if (idCliente == null
				&& idUnidadeSolicitante == null
				&& idFuncionario == null
				&& nomeSolicitante == null
				&& (idEspecificacao == null || (idEspecificacao != null && this
						.clienteObrigatorioInserirRegistroAtendimento(idEspecificacao)))) {
			throw new ControladorException(
			"atencao.dados_solicitante_nao_informado");
		} else {
			
			if (idCliente != null && idEspecificacao != null) {
				validarObrigatoriedadeDocumento(idCliente, idEspecificacao, 
						idMeioSolicitacao, idImovel);
			}
			
			if (indicadorClienteEspecificacao != null
					&& indicadorClienteEspecificacao
					.equals(ConstantesSistema.INDICADOR_USO_ATIVO)
					&& idCliente == null) {
				throw new ControladorException("atencao.required", null,
				"Cliente");
			}
			
			if (idFuncionario != null && idUnidadeSolicitante == null) {
				throw new ControladorException("atencao.required", null,
				"Unidade Solicitante");
			}
			
			if (idFuncionario == null && idUnidadeSolicitante != null) {
				throw new ControladorException("atencao.required", null,
				"Funcionário Responsável");
			}
			
			if (idCliente != null
					&& (colecaoEndereco == null || colecaoEndereco.isEmpty())) {
				throw new ControladorException("atencao.required", null,
				"endereço");
			}
		}
		
		if (idCliente != null) {
			
			if (idRegistroAtendimento != null) {
				// [FS0012] Verificar existência do cliente solicitante
				this.verificarExistenciaClienteSolicitante(
						idRegistroAtendimento, idCliente);
			}
			/* TODO: COSANPA
			 * 
			 * Data: 24/01/2011
			 * 
			 * Inserir RA
			 * Retirada a condição do solicitante está vinculado ao imóvel. Um cliente não ligado ao imovel,
			 * pode abrir uma RA para determinado imóvel.
			 * 
			 */
			// [FS0027] Verificar informação do Imóvel
			//if (idImovel != null) {
				//this.verificarInformacaoImovel(idCliente, idImovel, true);
			//}
		}
		
		if (idUnidadeSolicitante != null) {
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional
			.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID,
					idUnidadeSolicitante));
			
			filtroUnidadeOrganizacional
			.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoUnidade = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				
				throw new ControladorException("atencao.label_inexistente",
						null, "Unidade Solicitante");
				
			} else if (idRegistroAtendimento != null) {
				
				// [FS0026] Verificar existência da unidade solicitante
				this.verificarExistenciaUnidadeSolicitante(
						idRegistroAtendimento, idUnidadeSolicitante);
			}
		}
		
		if (idFuncionario != null) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));
			
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID,
					idUnidadeSolicitante));
			
			Collection colecaoFuncionario = this.getControladorUtil()
			.pesquisar(filtroFuncionario, Funcionario.class.getName());
			
			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {
				
				throw new ControladorException("atencao.label_inexistente",
						null, "Funcionário Responsável");
				
			}
		}
	}
	
	private void validarObrigatoriedadeDocumento(Integer idCliente, Integer idEspecificacao, 
			Integer idMeioSolicitacao, Integer idImovel) throws ControladorException {
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = 
			new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(
				new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));
		
		Collection colecaoEspecificacao = getControladorUtil()
		.pesquisar(filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoEspecificacao != null && !colecaoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao especificacao = 
				(SolicitacaoTipoEspecificacao) colecaoEspecificacao.iterator().next();
			
			/**
			 *  [UC0366] Inserir Registro de Atendimento
			 * 
			 *  [FS0044] Verifica cliente com obrigatoriedade de documento.
			 *
			 *  Author: Hugo Leonardo 
			 *  Analista: Rosana Carvalho 
			 *  Date: 08/09/2010
			 *  Crc: CRC_3857
			 *
			 */
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			
			Collection colecaoCliente = getControladorUtil().pesquisar(filtroCliente, 
					Cliente.class.getName());
			
			if (especificacao.getIndicadorDocumentoObrigatorio() == ConstantesSistema.SIM.shortValue()) {
				
				Collection<SolicitacaoDocumentoObrigatorio> colecaoSolDocObrig = new ArrayList();
				
				FiltroSolicitacaoDocumentoObrigatorio filtroSolDocObrig = 
						new FiltroSolicitacaoDocumentoObrigatorio();
				
				filtroSolDocObrig.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoDocumentoObrigatorio.SOLICITACAO_TIPO_ESPECIFICACAO_ID, 
							especificacao.getId()));
				
				filtroSolDocObrig.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoDocumentoObrigatorio.MEIO_SOLICITACAO_ID, idMeioSolicitacao));
				
				colecaoSolDocObrig = this.getControladorUtil().pesquisar(filtroSolDocObrig, 
						SolicitacaoDocumentoObrigatorio.class.getName());
				
				// caso exista associação na tabela SOL_ESPEC_DOC_OBRIG_MEIO para o step_id e meso_id
				if(colecaoSolDocObrig != null && !colecaoSolDocObrig.isEmpty()){
					
					if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
						Cliente cliente = (Cliente) colecaoCliente.iterator().next();
						
						if ((cliente.getCpf() == null || cliente.getCpf().trim().equals("")) && 
								(cliente.getCnpj() == null || cliente.getCnpj().trim().equals("")) ) {
							
							if(idImovel == null){
								
								throw new ControladorException("atencao.cliente_sem_documento", null, cliente.getNome());
							}else {
								if(especificacao.getIndicadorValidarDocResponsavel() == ConstantesSistema.NAO.shortValue()){
		
									throw new ControladorException("atencao.cliente_sem_documento", null, cliente.getNome());
								}else if(especificacao.getIndicadorValidarDocResponsavel() == ConstantesSistema.SIM.shortValue()){
									
									FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
									filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
									filtroClienteImovel.adicionarParametro(
											new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 
													ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL));
									filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
									
									filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
									
									Collection colecaoClienteImovel = getControladorUtil().pesquisar(filtroClienteImovel, 
											ClienteImovel.class.getName());
									
									if (colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()) {
										
										throw new ControladorException("atencao.cliente_sem_documento_sem_cliente_responsavel", 
												null, cliente.getNome());
									}else{
										
										ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();
										Cliente clienteResp = clienteImovel.getCliente();
										
										if ((clienteResp.getCpf() == null || clienteResp.getCpf().trim().equals("")) && 
												(clienteResp.getCnpj() == null || clienteResp.getCnpj().trim().equals("")) ) {
										
											throw new ControladorException("atencao.cliente_sem_documento_sem_cliente_responsavel_valido", 
													null, cliente.getNome(), clienteImovel.getCliente().getNome());
										}
									}
									
									
								}
							}
							
						}
					}
				}else{
				
					
					
					FiltroSolicitacaoDocumentoObrigatorio filtroSolDocObrigOutrasEmpresas = 
							new FiltroSolicitacaoDocumentoObrigatorio();
					
					filtroSolDocObrigOutrasEmpresas.adicionarParametro(new ParametroSimples( 
							FiltroSolicitacaoDocumentoObrigatorio.SOLICITACAO_TIPO_ESPECIFICACAO_ID, 
								especificacao.getId()));
					
					colecaoSolDocObrig = this.getControladorUtil().pesquisar(filtroSolDocObrigOutrasEmpresas, 
							SolicitacaoDocumentoObrigatorio.class.getName());
					
					// caso não exista associação na tabela SOL_ESPEC_DOC_OBRIG_MEIO para o step_id e meso_id
					if(colecaoSolDocObrig == null || colecaoSolDocObrig.isEmpty()){
						
						if(especificacao.getIndicadorDocumentoObrigatorio() == ConstantesSistema.SIM.shortValue()){
							
							if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
								Cliente cliente = (Cliente) colecaoCliente.iterator().next();
								
								if ((cliente.getCpf() == null || cliente.getCpf().trim().equals("")) && 
										(cliente.getCnpj() == null || cliente.getCnpj().trim().equals("")) ) {
									
									if(idImovel == null){
										
										throw new ControladorException("atencao.cliente_sem_documento", null, cliente.getNome());
									}else{
										
										if(especificacao.getIndicadorValidarDocResponsavel() == ConstantesSistema.NAO.shortValue()){
											
											throw new ControladorException("atencao.cliente_sem_documento", null, cliente.getNome());
										}else if(especificacao.getIndicadorValidarDocResponsavel() == ConstantesSistema.SIM.shortValue()){
											
											FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
											filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
											filtroClienteImovel.adicionarParametro(
													new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 
															ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL));
											filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
											
											filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
											
											Collection colecaoClienteImovel = getControladorUtil().pesquisar(filtroClienteImovel, 
													ClienteImovel.class.getName());
											
											if (colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()) {
												
												throw new ControladorException("atencao.cliente_sem_documento_sem_cliente_responsavel", 
														null, cliente.getNome());
											}else{
												
												ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();
												Cliente clienteResp = clienteImovel.getCliente();
												
												if ((clienteResp.getCpf() == null || clienteResp.getCpf().trim().equals("")) && 
														(clienteResp.getCnpj() == null || clienteResp.getCnpj().trim().equals("")) ) {
												
													throw new ControladorException("atencao.cliente_sem_documento_sem_cliente_responsavel_valido", 
														null, cliente.getNome(), clienteImovel.getCliente().getNome());
												}
											}
										}
									}
								}
							}
						}// fim associação na tabela SOL_ESPEC_DOC_OBRIG_MEIO
					}
				}
			}
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0027] Inclui Solicitante do Registro de Atendimento
	 * (REGISTRO_ATENDIMENTO_SOLICITANTE)
	 * 
	 * @author Raphael Rossiter
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public boolean clienteObrigatorioInserirRegistroAtendimento(
			Integer idEspecificacao) throws ControladorException {
		
		boolean retorno = true;
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.INDICADOR_SOLICITANTE,
				ConstantesSistema.NAO));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			retorno = false;
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0027] Inclui Solicitante do Registro de Atendimento
	 * (REGISTRO_ATENDIMENTO_SOLICITANTE)
	 * 
	 * @author Raphael Rossiter, Raphael Rossiter, Paulo Diniz
	 * @date 24/08/2006, 07/08/2009, x, 27/06/2011
	 * 
	 * @throws ControladorException
	 */
	public void inserirRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimento, Integer idCliente,
			Collection colecaoEndereco, String pontoReferencia,
			String nomeSolicitante, boolean novoSolicitante,
			Integer idUnidadeSolicitante, Integer idFuncionario,
			Collection colecaoFone, String protocoloAtendimento, 
			String habilitarCampoSatisfacaoEmail, String enviarEmailSatisfacao, String enderecoEmail) throws ControladorException {
		
		RegistroAtendimento ra = new RegistroAtendimento();
		RegistroAtendimentoSolicitante solicitanteInserir = new RegistroAtendimentoSolicitante();
		
		// Registro Atendimento
		ra.setId(idRegistroAtendimento);
		solicitanteInserir.setRegistroAtendimento(ra);
		
		// Cliente
		if (idCliente != null) {
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			solicitanteInserir.setCliente(cliente);
		}
		
		// número do Imóvel, Complemento do endereço, LogradouroCep e
		// LogradouroBairro
		if (idCliente == null) {
			IClienteEndereco endereco = (IClienteEndereco) Util
			.retonarObjetoDeColecao(colecaoEndereco);
			
			if (endereco != null) {
				solicitanteInserir.setNumeroImovel(endereco.getNumero());
				
				if (endereco.getComplemento() != null) {
					solicitanteInserir.setComplementoEndereco(endereco
							.getComplemento());
				}
				
				if (endereco.getLogradouroCep() != null) {
					solicitanteInserir.setLogradouroCep(endereco.getLogradouroCep());
				}
				if (endereco.getLogradouroBairro() != null) {
					solicitanteInserir.setLogradouroBairro(endereco.getLogradouroBairro());
				}
				if (endereco.getPerimetroInicial() != null) {
					solicitanteInserir.setPerimetroInicial(endereco.getPerimetroInicial());
				}
				if (endereco.getPerimetroFinal() != null) {
					solicitanteInserir.setPerimetroFinal(endereco.getPerimetroFinal());
				}
				
			}
			
		}
		
		// Ponto de Referência
		if (pontoReferencia != null && !pontoReferencia.equalsIgnoreCase("")) {
			solicitanteInserir.setPontoReferencia(pontoReferencia);
		}
		
		// Nome do Solicitante
		if (nomeSolicitante != null && !nomeSolicitante.equalsIgnoreCase("")) {
			solicitanteInserir.setSolicitante(nomeSolicitante);
		}
		
		//IndicadorEmailPesquisa e EnderecoEmail
		if(habilitarCampoSatisfacaoEmail != null){
			if(enviarEmailSatisfacao != null && enviarEmailSatisfacao.equals("2")){
				solicitanteInserir.setIndicadorEnvioEmailPesquisa(new Short("2"));
			}else{
				solicitanteInserir.setIndicadorEnvioEmailPesquisa(new Short("1"));
				solicitanteInserir.setEnderecoEmail(enderecoEmail);
			}
		}else{
			solicitanteInserir.setIndicadorEnvioEmailPesquisa(new Short("2"));
			// Magno Gouveia [Gravar o email informado na Loja Virtual]
			if(Util.verificarNaoVazio(enderecoEmail)){
				solicitanteInserir.setEnderecoEmail(enderecoEmail);
			}
		}
		
		// Indicador Solicitante Principal
		if (novoSolicitante) {
			solicitanteInserir
			.setIndicadorSolicitantePrincipal(ConstantesSistema.INDICADOR_NOVO_SOLICITANTE);
		} else {
			solicitanteInserir
			.setIndicadorSolicitantePrincipal(ConstantesSistema.INDICADOR_INSERIR_SOLICITANTE_RA);
		}
		
		// Ultima alteração
		solicitanteInserir.setUltimaAlteracao(new Date());
		
		// Unidade Solicitante
		if (idUnidadeSolicitante != null) {
			UnidadeOrganizacional unidadeSolicitante = new UnidadeOrganizacional();
			unidadeSolicitante.setId(idUnidadeSolicitante);
			solicitanteInserir.setUnidadeOrganizacional(unidadeSolicitante);
		}
		
		if (idFuncionario != null) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(idFuncionario);
			solicitanteInserir.setFuncionario(funcionario);
		}
		
		//PROTOCOLO DE ATENDIMENTO
		solicitanteInserir.setNumeroProtocoloAtendimento(protocoloAtendimento);
		
		Integer idRASolicitante = (Integer) this.getControladorUtil().inserir(
				solicitanteInserir);
		solicitanteInserir.setID(idRASolicitante);
		
		if (colecaoFone != null && !colecaoFone.isEmpty() && idCliente == null) {
			this.inserirRegistroAtendimentoSolicitanteFone(solicitanteInserir,
					colecaoFone);
		}
	}
	
	public void inserirRegistroAtendimentoSolicitante(RADadosGeraisHelper raDadosGerais, RALocalOcorrenciaHelper raLocalOcorrencia, RASolicitanteHelper raSolicitante) throws ControladorException {
		
		RegistroAtendimento ra = new RegistroAtendimento();
		RegistroAtendimentoSolicitante solicitanteInserir = new RegistroAtendimentoSolicitante();
		
		// Registro Atendimento
		ra.setId(raDadosGerais.getIdRegistroAtendimento());
		solicitanteInserir.setRegistroAtendimento(ra);
		
		// Cliente
		if (raSolicitante.getIdCliente() != null) {
			Cliente cliente = new Cliente();
			cliente.setId(raSolicitante.getIdCliente());
			solicitanteInserir.setCliente(cliente);
		}
		
		// número do Imóvel, Complemento do endereço, LogradouroCep e
		// LogradouroBairro
		if (raSolicitante.getIdCliente() == null) {
			ClienteEndereco endereco = (ClienteEndereco) Util
			.retonarObjetoDeColecao(raLocalOcorrencia.getColecaoEndereco());
			
			if (endereco != null) {
				solicitanteInserir.setNumeroImovel(endereco.getNumero());
				
				if (endereco.getComplemento() != null) {
					solicitanteInserir.setComplementoEndereco(endereco
							.getComplemento());
				}
				
				solicitanteInserir
				.setLogradouroCep(endereco.getLogradouroCep());
				solicitanteInserir.setLogradouroBairro(endereco
						.getLogradouroBairro());
				solicitanteInserir.setPerimetroInicial(endereco.getPerimetroInicial());
				solicitanteInserir.setPerimetroFinal(endereco.getPerimetroFinal());
				
			}
			
		}
		
		// Ponto de Referência
		if (raSolicitante.getPontoReferenciaSolicitante() != null && !raSolicitante.getPontoReferenciaSolicitante().equalsIgnoreCase("")) {
			solicitanteInserir.setPontoReferencia(raSolicitante.getPontoReferenciaSolicitante());
		}
		
		// Nome do Solicitante
		if (raSolicitante.getNomeSolicitante() != null && !raSolicitante.getNomeSolicitante().equalsIgnoreCase("")) {
			solicitanteInserir.setSolicitante(raSolicitante.getNomeSolicitante());
		}
		
		//IndicadorEmailPesquisa e EnderecoEmail
		if(raSolicitante.getHabilitarCampoSatisfacaoEmail() != null){
			if(raSolicitante.getEnviarEmailSatisfacao() != null && raSolicitante.getEnviarEmailSatisfacao().equals("2")){
				solicitanteInserir.setIndicadorEnvioEmailPesquisa(new Short("2"));
			}else{
				solicitanteInserir.setIndicadorEnvioEmailPesquisa(new Short("1"));
				solicitanteInserir.setEnderecoEmail(raSolicitante.getEnderecoEmail());
			}
		}else{
			solicitanteInserir.setIndicadorEnvioEmailPesquisa(new Short("2"));
			// Magno Gouveia [Gravar o email informado na Loja Virtual]
			if(Util.verificarNaoVazio(raSolicitante.getEnderecoEmail())){
				solicitanteInserir.setEnderecoEmail(raSolicitante.getEnderecoEmail());
			}
		}
		
		// Indicador Solicitante Principal
		if (raSolicitante.isNovoSolicitante()) {
			solicitanteInserir
			.setIndicadorSolicitantePrincipal(ConstantesSistema.INDICADOR_NOVO_SOLICITANTE);
		} else {
			solicitanteInserir
			.setIndicadorSolicitantePrincipal(ConstantesSistema.INDICADOR_INSERIR_SOLICITANTE_RA);
		}
		
		// Ultima alteração
		solicitanteInserir.setUltimaAlteracao(new Date());
		
		// Unidade Solicitante
		if (raSolicitante.getIdUnidadeSolicitante() != null) {
			UnidadeOrganizacional unidadeSolicitante = new UnidadeOrganizacional();
			unidadeSolicitante.setId(raSolicitante.getIdUnidadeSolicitante());
			solicitanteInserir.setUnidadeOrganizacional(unidadeSolicitante);
		}
		
		if (raDadosGerais.getIdFuncionario() != null) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(raDadosGerais.getIdFuncionario());
			solicitanteInserir.setFuncionario(funcionario);
		}
		
		//PROTOCOLO DE ATENDIMENTO
		solicitanteInserir.setNumeroProtocoloAtendimento(raDadosGerais.getProtocoloAtendimento());
		
		Integer idRASolicitante = (Integer) this.getControladorUtil().inserir(
				solicitanteInserir);
		solicitanteInserir.setID(idRASolicitante);
		
		if (raSolicitante.getColecaoFone() != null && !raSolicitante.getColecaoFone().isEmpty() && raSolicitante.getIdCliente() == null) {
			this.inserirRegistroAtendimentoSolicitanteFone(solicitanteInserir,
					raSolicitante.getColecaoFone());
		}
	}
	
	/**
	 * 
	 * passa os parametros do registro atendimento solicitante e a coleção de
	 * fones do solicitante e retorna um objeto de Registro Atendimento
	 * Solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 02/09/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoSolicitante inserirDadosNoRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimento, Integer idCliente,
			Collection colecaoEndereco, String pontoReferencia,
			String nomeSolicitante, Integer idUnidadeSolicitante,
			Integer idFuncionario, Collection colecaoFone, String fonePadrao)
	throws ControladorException {
		
		RegistroAtendimento ra = new RegistroAtendimento();
		RegistroAtendimentoSolicitante solicitanteInserir = new RegistroAtendimentoSolicitante();
		
		// Registro Atendimento
		ra.setId(idRegistroAtendimento);
		solicitanteInserir.setRegistroAtendimento(ra);
		
		// Cliente
		if (idCliente != null) {
			
			FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,idCliente));
			
			Collection colecaoCliente = this.getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());
			
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
			
			solicitanteInserir.setCliente(cliente);
		}
		
		// número do Imóvel, Complemento do endereço, LogradouroCep e
		// LogradouroBairro
		if (idCliente == null) {
			ClienteEndereco endereco = (ClienteEndereco) Util
			.retonarObjetoDeColecao(colecaoEndereco);
			solicitanteInserir.setNumeroImovel(endereco.getNumero());
			
			if (endereco.getComplemento() != null) {
				solicitanteInserir.setComplementoEndereco(endereco
						.getComplemento());
			}
			
			solicitanteInserir.setLogradouroCep(endereco.getLogradouroCep());
			solicitanteInserir.setLogradouroBairro(endereco
					.getLogradouroBairro());
			solicitanteInserir.setPerimetroInicial(endereco.getPerimetroInicial());
			solicitanteInserir.setPerimetroFinal(endereco.getPerimetroFinal());
		}
		
		// Ponto de Referência
		if (pontoReferencia != null && !pontoReferencia.equalsIgnoreCase("")) {
			solicitanteInserir.setPontoReferencia(pontoReferencia);
		}
		
		// Nome do Solicitante
		if (nomeSolicitante != null && !nomeSolicitante.equalsIgnoreCase("")) {
			solicitanteInserir.setSolicitante(nomeSolicitante);
		}
		
		// Unidade Solicitante
		if (idUnidadeSolicitante != null) {
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID,idUnidadeSolicitante));
			
			Collection colecaoUnidadeOrganizacional = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			UnidadeOrganizacional unidadeSolicitante = (UnidadeOrganizacional) 
			Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
			
			solicitanteInserir.setUnidadeOrganizacional(unidadeSolicitante);
		}
		
		if (idCliente == null) {
			if (fonePadrao != null && !fonePadrao.equals("")) {
				// Responsável pera troca do solicitante principal
				// caso tenha sido trocado então sai da coleção
				boolean trocaPrincipal = false;
				
				if (colecaoFone != null && !colecaoFone.isEmpty()) {
					Iterator iteratorFone = colecaoFone.iterator();
					while (iteratorFone.hasNext()) {
						ClienteFone clienteFone = (ClienteFone) iteratorFone
						.next();
						// caso a colecao só tenha um solicitante então o
						// solicitante será o principal
						if (colecaoFone.size() == 1) {
							clienteFone.setIndicadorTelefonePadrao(new Short(
							"1"));
						} else {
							// senão se o id socilitante seja igual ao o id
							// do solicitante que foi escolhido como
							// principal
							if (clienteFone.getUltimaAlteracao().getTime() == Long
									.parseLong(fonePadrao)) {
								// se for diferente de 1, ou seja se o
								// solicitante não era o principal
								if (clienteFone.getIndicadorTelefonePadrao() != 1) {
									// seta o valor 1 ao indicador principal do
									// solicitante
									clienteFone
									.setIndicadorTelefonePadrao(new Short(
									"1"));
									// verifica se o indicador principal do
									// solicitante que era 1 anteriormente já
									// foi mudado para 2(nesse caso o boolean
									// trocaPrincipal está com o valor true).
									if (trocaPrincipal) {
										break;
									} else {
										trocaPrincipal = true;
									}
									// caso o solicitante principal não tenha
									// mudado então sai do loop
								} else {
									break;
								}
								
							} else {
								// parte que muda o indicador principal do
								// solicitante(que não mais principal)
								// para 2
								if (clienteFone.getIndicadorTelefonePadrao() == 1) {
									clienteFone
									.setIndicadorTelefonePadrao(new Short(
									"2"));
									if (trocaPrincipal) {
										break;
									} else {
										trocaPrincipal = true;
									}
								}
							}
						}
					}
				}
			}
		}
		if (idFuncionario != null) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID,idFuncionario));
			
			Collection colecaoFuncionario = this.getControladorUtil().pesquisar(
					filtroFuncionario, Funcionario.class.getName());
			
			Funcionario funcionario = (Funcionario) 
			Util.retonarObjetoDeColecao(colecaoFuncionario);
			
			solicitanteInserir.setFuncionario(funcionario);
		}
		
		if (colecaoFone != null && !colecaoFone.isEmpty()) {
			solicitanteInserir.setSolicitanteFones(new HashSet(colecaoFone));
		}
		return solicitanteInserir;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0027] Inclui Solicitante do Registro de Atendimento (SOLICITANTE_FONE)
	 * 
	 * @author Raphael Rossiter
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void inserirRegistroAtendimentoSolicitanteFone(
			RegistroAtendimentoSolicitante solicitante, Collection colecaoFone)
	throws ControladorException {
		
		Iterator itaratorClienteFone = colecaoFone.iterator();
		IClienteFone clienteFone = null;
		SolicitanteFone solicitanteFone = null;
		
		while (itaratorClienteFone.hasNext()) {
			clienteFone = (IClienteFone) itaratorClienteFone.next();
			
			solicitanteFone = new SolicitanteFone();
			
			// Registro Atendimento Solicitante
			solicitanteFone.setRegistroAtendimentoSolicitante(solicitante);
			
			// Tipo do Telefone
			solicitanteFone.setFoneTipo(clienteFone.getFoneTipo());
			
			// DDD
			solicitanteFone.setDdd(Short.valueOf(clienteFone.getDdd()));
			
			// Telefone
			solicitanteFone.setFone(clienteFone.getTelefone());
			
			// Ramal
			if (clienteFone.getRamal() != null
					&& !clienteFone.getRamal().equalsIgnoreCase("")) {
				solicitanteFone.setRamal(clienteFone.getRamal());
			}
			
			// Indicador Fone Padrao
			if (clienteFone.getIndicadorTelefonePadrao() != null) {
				solicitanteFone.setIndicadorPadrao(clienteFone
						.getIndicadorTelefonePadrao());
			} else {
				solicitanteFone
				.setIndicadorPadrao(ConstantesSistema.INDICADOR_NAO_TELEFONE_PRINCIPAL);
			}
			
			// Ultima alteração
			solicitanteFone.setUltimaAlteracao(new Date());
			
			this.getControladorUtil().inserir(solicitanteFone);
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0028] Inclui Registro de Atendimento (REGISTRO_ATENDIMENTO)
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer[] inserirRegistroAtendimento(RADadosGeraisHelper raDadosGerais, RALocalOcorrenciaHelper raLocalOcorrencia, RASolicitanteHelper raSolicitante) throws ControladorException {
		
		Integer[] retorno = null;
		boolean existeEspecificacaoPavimentacaoServicoTipo = false;
		
		if(raLocalOcorrencia.getIdLocalOcorrencia() != null && raLocalOcorrencia.getIdPavimentoCalcada() != null && raLocalOcorrencia.getIdPavimentoRua() != null){
			FiltroEspecificacaoPavimentacaoServicoTipo filtro = new FiltroEspecificacaoPavimentacaoServicoTipo();
			filtro.adicionarParametro(new ParametroSimples(FiltroEspecificacaoPavimentacaoServicoTipo.LOCALOCORRENCIA_ID, raLocalOcorrencia.getIdLocalOcorrencia()));
			filtro.adicionarParametro(new ParametroSimples(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTOCALCADA_ID, raLocalOcorrencia.getIdPavimentoCalcada()));
			filtro.adicionarParametro(new ParametroSimples(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTORUA_ID, raLocalOcorrencia.getIdPavimentoRua()));
			filtro.adicionarParametro(new ParametroSimples(FiltroEspecificacaoPavimentacaoServicoTipo.SOLICITACAOTIPOESPECIFICACAO_ID, raDadosGerais.getIdSolicitacaoTipoEspecificacao()));
			
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.SERVICOTIPO);
			
			Collection result = getControladorUtil().pesquisar(filtro, EspecificacaoPavimentacaoServicoTipo.class.getName());
			
			if(result != null && !result.isEmpty()){
				EspecificacaoPavimentacaoServicoTipo especificaoPavimento = (EspecificacaoPavimentacaoServicoTipo) Util.retonarObjetoDeColecao(result);
				
				raSolicitante.idServicoTipo(especificaoPavimento.getServicoTipo().getId());
				existeEspecificacaoPavimentacaoServicoTipo = true;
			}
		}
		
		OrdemServico osGeradaAutomatica = null;
		if (existeEspecificacaoPavimentacaoServicoTipo || this.gerarOrdemServicoAutomatica(raDadosGerais.getIdSolicitacaoTipoEspecificacao())) {
			
			retorno = new Integer[2];
			
			osGeradaAutomatica = this.gerarOrdemServicoAutomatica(raSolicitante.getIdServicoTipo(), raDadosGerais.getIdSolicitacaoTipo());
		} else {
			retorno = new Integer[1];
		}
		
		RegistroAtendimento ra = new RegistroAtendimento();
		
		ra.setIndicadorAtendimentoOnline(raDadosGerais.getIndicadorAtendimentoOnLine());
		ra.setNnDiametro(raLocalOcorrencia.getNnDiametro());
		
		if (raDadosGerais.getNumeroRAManual() != null && !raDadosGerais.getNumeroRAManual().equalsIgnoreCase("")) {
			ra.setManual(Integer.parseInt(raDadosGerais.getNumeroRAManual()));
		}
		
		String dataHoraAtendimento = raDadosGerais.getDataAtendimento() + " " + Util.formatarHoraSemSegundos(raDadosGerais.getHoraAtendimento()) + ":00";
		Date dataHoraAtendimentoObjetoDate = Util.converteStringParaDateHora(dataHoraAtendimento);
		
		ra.setRegistroAtendimento(dataHoraAtendimentoObjetoDate);
		
		if (raDadosGerais.getTempoEsperaInicial() != null && !raDadosGerais.getTempoEsperaInicial().equalsIgnoreCase("")) {
			Date dataEsperaInicial = Util.converteStringParaDateHora(raDadosGerais.getDataAtendimento() + " " + Util.formatarHoraSemSegundos(raDadosGerais.getTempoEsperaInicial()) + ":00");
			
			ra.setDataInicioEspera(dataEsperaInicial);
			
			if(raDadosGerais.getTempoEsperaFinal() != null && !raDadosGerais.getTempoEsperaFinal().equals("")){
				Date dataEsperaFinal = Util.converteStringParaDateHora(raDadosGerais.getDataAtendimento() + " " + Util.formatarHoraSemSegundos(raDadosGerais.getTempoEsperaFinal()) + ":00");
				ra.setDataFimEspera(dataEsperaFinal);
			}
		}
		
		MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
		meioSolicitacao.setId(raDadosGerais.getIdMeioSolicitacao());
		
		ra.setMeioSolicitacao(meioSolicitacao);
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(raDadosGerais.getIdSolicitacaoTipoEspecificacao());
		
		ra.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		
		Date dataPrevistaObjetoDate = Util.converteStringParaDate(raDadosGerais.getDataPrevista());
		
		ra.setDataPrevistaOriginal(dataPrevistaObjetoDate);
		ra.setDataPrevistaAtual(dataPrevistaObjetoDate);
		
		if (raDadosGerais.getObservacao() != null && !raDadosGerais.getObservacao().equalsIgnoreCase("")) {
			ra.setObservacao(raDadosGerais.getObservacao());
		}
		
		if (raLocalOcorrencia.getIdImovel() != null) {
			Imovel imovel = new Imovel();
			imovel.setId(raLocalOcorrencia.getIdImovel());
			ra.setImovel(imovel);
		}
		
		if(raLocalOcorrencia.getNnCoordenadaNorte() != null){
			ra.setNnCoordenadaNorte(raLocalOcorrencia.getNnCoordenadaNorte());
		}
		if(raLocalOcorrencia.getNnCoordenadaLeste() != null){
			ra.setNnCoordenadaLeste(raLocalOcorrencia.getNnCoordenadaLeste());
		}
		
		ra.setIndicadorCoordenadaSemLogradouro(raLocalOcorrencia.getIndicCoordenadaSemLogradouro());
		
		if (raLocalOcorrencia.getDescricaoLocalOcorrencia() != null && !raLocalOcorrencia.getDescricaoLocalOcorrencia().equalsIgnoreCase("")) {
			ra.setDescricaoLocalOcorrencia(raLocalOcorrencia.getDescricaoLocalOcorrencia());
			ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_BLOQUEADO);
		} else {
			
			if (!raDadosGerais.getIndicadorRaAtualizacaoCadastral() && (this.especificacaoExigeMatriculaImovel(solicitacaoTipoEspecificacao) 
					|| (raLocalOcorrencia.getColecaoEndereco() != null && !raLocalOcorrencia.getColecaoEndereco().isEmpty()))) {
				
				Imovel imovelEndereco = (Imovel) Util
				.retonarObjetoDeColecao(raLocalOcorrencia.getColecaoEndereco());
				
				ra.setLogradouroBairro(imovelEndereco.getLogradouroBairro());
				
				ra.setLogradouroCep(imovelEndereco.getLogradouroCep());
				
				if (imovelEndereco.getComplementoEndereco() != null) {
					ra.setComplementoEndereco(imovelEndereco
							.getComplementoEndereco());
				}
				
				ra.setNumeroImovel(imovelEndereco.getNumeroImovel());
				
				ra.setPerimetroInicial(imovelEndereco.getPerimetroInicial());
				ra.setPerimetroFinal(imovelEndereco.getPerimetroFinal());
			}
			
			if (raLocalOcorrencia.getPontoReferenciaLocalOcorrencia() != null && !raLocalOcorrencia.getPontoReferenciaLocalOcorrencia().equalsIgnoreCase("")) {
				ra.setPontoReferencia(raLocalOcorrencia.getPontoReferenciaLocalOcorrencia());
			}
			
			if (raLocalOcorrencia.getIdBairroArea() != null && !raLocalOcorrencia.getIdBairroArea().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				BairroArea bairroArea = new BairroArea();
				bairroArea.setId(raLocalOcorrencia.getIdBairroArea());
				ra.setBairroArea(bairroArea);
			}
			
			if (raLocalOcorrencia.getIdLocalidade() != null) {
				Localidade localidade = new Localidade();
				localidade.setId(raLocalOcorrencia.getIdLocalidade());
				ra.setLocalidade(localidade);
			}
			
			if (raLocalOcorrencia.getIdSetorComercial() != null) {
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setId(raLocalOcorrencia.getIdSetorComercial());
				ra.setSetorComercial(setorComercial);
			}
			
			if (raLocalOcorrencia.getIdQuadra() != null) {
				Quadra quadra = new Quadra();
				quadra.setId(raLocalOcorrencia.getIdQuadra());
				ra.setQuadra(quadra);
			}
			
			if (raLocalOcorrencia.getIdDivisaoEsgoto() != null
					&& !raLocalOcorrencia.getIdDivisaoEsgoto()
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
				divisaoEsgoto.setId(raLocalOcorrencia.getIdDivisaoEsgoto());
				ra.setDivisaoEsgoto(divisaoEsgoto);
			}
			
			if (raLocalOcorrencia.getIdLocalOcorrencia() != null
					&& !raLocalOcorrencia.getIdLocalOcorrencia()
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LocalOcorrencia localOcorrencia = new LocalOcorrencia();
				localOcorrencia.setId(raLocalOcorrencia.getIdLocalOcorrencia());
				ra.setLocalOcorrencia(localOcorrencia);
			}
			
			if (raLocalOcorrencia.getIdPavimentoRua() != null && !raLocalOcorrencia.getIdPavimentoRua()
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				PavimentoRua pavimentoRua = new PavimentoRua();
				pavimentoRua.setId(raLocalOcorrencia.getIdPavimentoRua());
				ra.setPavimentoRua(pavimentoRua);
			}
			
			if (raLocalOcorrencia.getIdPavimentoCalcada() != null && !raLocalOcorrencia.getIdPavimentoCalcada()
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				PavimentoCalcada pavimentoCalcada = new PavimentoCalcada();
				pavimentoCalcada.setId(raLocalOcorrencia.getIdPavimentoCalcada());
				ra.setPavimentoCalcada(pavimentoCalcada);
			}
			
			ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_PENDENTE);
			
			ra.setUnidadeAtual(null);
		}
		
		
		if (raDadosGerais.getIdRAJAGerado() != null){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_atendimento_insercao_ja_realizada", null, raDadosGerais.getIdRAJAGerado().toString());
		}
		
		ra.setUltimaAlteracao(new Date());
		
		Integer idRAGerado = (Integer) this.getControladorUtil().inserir(ra);
		ra.setId(idRAGerado);
		
		retorno[0] = idRAGerado;
		
		this.inserirRegistroAtendimentoUnidade(ra, raDadosGerais.getIdUnidadeAtendimento(),
				raDadosGerais.getIdUsuarioLogado());
		
		this.inserirRegistroAtendimentoSolicitante(idRAGerado, raSolicitante.getIdCliente(),
				raSolicitante.getColecaoEnderecoSolicitante(), raSolicitante.getPontoReferenciaSolicitante(),
				raSolicitante.getNomeSolicitante(), raSolicitante.isNovoSolicitante(), raSolicitante.getIdUnidadeSolicitante(),
				raDadosGerais.getIdFuncionario(), raSolicitante.getColecaoFone(), raDadosGerais.getProtocoloAtendimento(),
				raSolicitante.getHabilitarCampoSatisfacaoEmail(), raSolicitante.getEnviarEmailSatisfacao(), raSolicitante.getEnderecoEmail()	);
		
		this.inserirRegistroAtendimentoAnexo(idRAGerado, raDadosGerais.getColecaoRegistroAtendimentoAnexo());
		
		
		this.inserirRegistroAtendimentoConta(idRAGerado, raLocalOcorrencia.getColecaoContas());

		this.inserirRegistroAtendimentoPagamentoDuplicidade(idRAGerado,raLocalOcorrencia.getColecaoPagamentos(),raDadosGerais.getIdSolicitacaoTipoEspecificacao());
		
		Tramite tramite = new Tramite();
		
		tramite.setRegistroAtendimento(ra);
		
		UnidadeOrganizacional unidadeOrigem = new UnidadeOrganizacional();
		unidadeOrigem.setId(raDadosGerais.getIdUnidadeAtendimento());
		
		tramite.setUnidadeOrganizacionalOrigem(unidadeOrigem);
		
		if (raLocalOcorrencia.getIdUnidadeDestino() != null) {
			UnidadeOrganizacional unidadeDestino = new UnidadeOrganizacional();
			unidadeDestino.setId(raLocalOcorrencia.getIdUnidadeDestino());
			
			tramite.setUnidadeOrganizacionalDestino(unidadeDestino);
		} else {
			tramite.setUnidadeOrganizacionalDestino(unidadeOrigem);
		}
		
		Usuario usuario = new Usuario();
		usuario.setId(raDadosGerais.getIdUsuarioLogado());
		
		tramite.setUsuarioRegistro(usuario);
		tramite.setUsuarioResponsavel(usuario);
		
		if (raLocalOcorrencia.getParecerUnidadeDestino() != null
				&& !raLocalOcorrencia.getParecerUnidadeDestino().equalsIgnoreCase("")) {
			tramite.setParecerTramite(raLocalOcorrencia.getParecerUnidadeDestino());
		} else {
			tramite
			.setParecerTramite("TRAMITE GERADO PELO SISTEMA NA ABERTURA DO REGISTRO DE ATENDIMENTO");
		}
		
		tramite.setDataTramite(new Date());
		tramite.setUltimaAlteracao(new Date());
		
		this.tramitar(tramite, null);
		
		if (this.gerarOrdemServicoAutomatica(raDadosGerais.getIdSolicitacaoTipoEspecificacao())
				&& osGeradaAutomatica != null) {
			
			if (ra.getImovel() != null) {
				osGeradaAutomatica.setImovel(ra.getImovel());
			}
			
			osGeradaAutomatica.setRegistroAtendimento(ra);
			osGeradaAutomatica.setObservacao(raDadosGerais.getObservacao());
			Integer idOrdemServico = this.getControladorOrdemServico()
			.gerarOrdemServico(osGeradaAutomatica, usuario, 
					Funcionalidade.INSERIR_REGISTRO_ATENDIMENTO);
			
			retorno[1] = idOrdemServico;
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0028] Inclui Registro de Atendimento (REGISTRO_ATENDIMENTO_UNIDADE)
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void inserirRegistroAtendimentoUnidade(RegistroAtendimento ra,
			Integer idUnidadeAtendimento, Integer idUsuarioLogado)
	throws ControladorException {
		
		RegistroAtendimentoUnidade raUnidade = new RegistroAtendimentoUnidade();
		
		// Registro Atendimento
		raUnidade.setRegistroAtendimento(ra);
		
		// Unidade Atendimento
		UnidadeOrganizacional unidadeAtendimento = new UnidadeOrganizacional();
		unidadeAtendimento.setId(idUnidadeAtendimento);
		
		raUnidade.setUnidadeOrganizacional(unidadeAtendimento);
		
		// usuário Logado
		Usuario usuario = new Usuario();
		usuario.setId(idUsuarioLogado);
		
		raUnidade.setUsuario(usuario);
		
		// Atendimento Relação Tipo
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		
		raUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		
		// última Alteração
		raUnidade.setUltimaAlteracao(new Date());
		
		this.getControladorUtil().inserir(raUnidade);
	}
	
	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Pré-Encerramento
	 * 
	 * [FS0001] Verificar possibilidade de encerramento do registro de
	 * atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 25/08/2006
	 * 
	 * @param registroAtendimento
	 * @param usuarioLogado
	 * @param indicadorAutorizacaoManutencaoRA
	 * @throws ControladorException
	 */
	public void validarPreEncerramentoRA(
			RegistroAtendimento registroAtendimento, Usuario usuarioLogado,
			Short indicadorAutorizacaoManutencaoRA) throws ControladorException {
		if (registroAtendimento != null) {
			// Caso indicador de autorização de manutenção seja 2-não
			if (indicadorAutorizacaoManutencaoRA.intValue() == ConstantesSistema.NAO
					.intValue()) {
				// Caso tipo de solicitação do RA seja da Área de Tarifa Social
				if (new Short(registroAtendimento
						.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo()
						.getIndicadorTarifaSocial()).intValue() == 1
						&& new Short(usuarioLogado.getUnidadeOrganizacional()
								.getIndicadorTarifaSocial()).intValue() != 1) {
					
					UnidadeOrganizacional unidadeAtual = this
					.obterUnidadeAtualRA(registroAtendimento.getId());
					
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.encerrar_ra_nao_tarifa_social", null,
							unidadeAtual.getDescricao());
				}
			}
			if (new Short(registroAtendimento.getCodigoSituacao()).intValue() == RegistroAtendimento.SITUACAO_ENCERRADO
					.intValue()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.encerrar_ra_ja_encerrado", null,
						registroAtendimento.getId() + "");
			}
			
			// Caso exista ordem de Serviço não encerrada e programada para
			// o registro de atendimento
			Collection<OrdemServico> colecaoOrdemServico = obterOSRA(registroAtendimento
					.getId());
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {
				for (OrdemServico ordemServico : colecaoOrdemServico) {
					if ((new Short(ordemServico.getSituacao())).intValue() != OrdemServico.SITUACAO_ENCERRADO
							.intValue()
							&& getControladorOrdemServico()
							.verificarExistenciaOSProgramada(
									ordemServico.getId())) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.encerrar_ra_existe_os_programada",
								null, registroAtendimento.getId() + "");
					}
				}
			}
		}
	}
	
	
	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Pré-Encerramento
	 * 
	 * [FS0001] Verificar possibilidade de encerramento do registro de
	 * atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2007
	 * 
	 * @param registroAtendimento
	 * @param usuarioLogado
	 * @param indicadorAutorizacaoManutencaoRA
	 * @throws ControladorException
	 */
	public void validarPreEncerramentoRASemTarifaSocial(
			RegistroAtendimento registroAtendimento, Usuario usuarioLogado,
			Short indicadorAutorizacaoManutencaoRA) throws ControladorException {
		
		if (registroAtendimento != null) {
			// Caso indicador de autorização de manutenção seja 2-não
			if (indicadorAutorizacaoManutencaoRA.intValue() == ConstantesSistema.NAO
					.intValue()) {
				
				UnidadeOrganizacional unidadeAtual = this
				.obterUnidadeAtualRA(registroAtendimento.getId());
				
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.encerrar_ra_nao_tarifa_social", null,
						unidadeAtual.getDescricao());
				
			}
			if (new Short(registroAtendimento.getCodigoSituacao()).intValue() == RegistroAtendimento.SITUACAO_ENCERRADO
					.intValue()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.encerrar_ra_ja_encerrado", null,
						registroAtendimento.getId() + "");
			}
			
			// Caso exista ordem de Serviço não encerrada e programada para
			// o registro de atendimento
			Collection<OrdemServico> colecaoOrdemServico = obterOSRA(registroAtendimento
					.getId());
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {
				for (OrdemServico ordemServico : colecaoOrdemServico) {
					if ((new Short(ordemServico.getSituacao())).intValue() != OrdemServico.SITUACAO_ENCERRADO
							.intValue()
							&& getControladorOrdemServico()
							.verificarExistenciaOSProgramada(
									ordemServico.getId())) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.encerrar_ra_existe_os_programada",
								null, registroAtendimento.getId() + "");
					}
				}
			}
		}
	}
	
	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * [FS003] Validar RA de Referência
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 */
	public RegistroAtendimento validarRAReferencia(Integer idRA,
			Integer idRAReferencia) throws ControladorException {
		// Caso o número do RA Referência seja igual ao número do ra que está
		// sendo encerrado.
		if (idRA.intValue() == idRAReferencia.intValue()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
			"atencao.encerrar_ra_referencia_igual_ra");
		}
		RegistroAtendimento registroAtendimento = null;
		try {
			registroAtendimento = repositorioRegistroAtendimento
			.pesquisarRegistroAtendimento(idRAReferencia);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		// Caso o número do RA de Referência não exista.
		if (registroAtendimento == null) {
			// sessionContext.setRollbackOnly();
			// throw new ControladorException(
			// "atencao.encerrar_ra_referencia_inexistente");
		} else {
			// Caso o número do RA de Referência esteja encerrado.
			if (new Short(registroAtendimento.getCodigoSituacao()).intValue() == RegistroAtendimento.SITUACAO_ENCERRADO
					.intValue()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
				"atencao.encerrar_ra_referencia_ja_encerrado");
			}
		}
		return registroAtendimento;
	}
	
	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Encerramento
	 * 
	 * [FS0004] Verificar data do encerramento [FS0005] Verificar hora do
	 * encerramento
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 * 
	 * @param registroAtendimento
	 * @throws ControladorException
	 */
	public void validarEncerramentoRA(RegistroAtendimento registroAtendimento)
	throws ControladorException {
		if (registroAtendimento != null) {
			Date dataCorrente = new Date();
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(
					registroAtendimento.getDataEncerramento(), dataCorrente);
			if (qtdeDias < 0) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.encerrar_ra_data_encerramento_anterior_data_corrente",
						null, Util.formatarData(dataCorrente));
			} else if (Util.datasIguais(registroAtendimento
					.getDataEncerramento(), dataCorrente)) {
				if (Util.compararHoraMinuto(Util
						.formatarHoraSemData(registroAtendimento
								.getDataEncerramento()), Util
								.formatarHoraSemData(dataCorrente), ">")) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.encerrar_ra_hora_encerramento_anterior_hora_corrente",
							null, Util.formatarHoraSemSegundos(dataCorrente));
				}
			}
			Date dataRA = registroAtendimento.getRegistroAtendimento();
			int qtdeDiasRA = Util.obterQuantidadeDiasEntreDuasDatas(dataRA,
					registroAtendimento.getDataEncerramento());
			if (qtdeDiasRA < 0) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.encerrar_ra_data_encerramento_posterior_data_atendimento",
						null, Util.formatarData(dataRA));
			} else if (qtdeDiasRA == 0) {
				if (Util.compararHoraMinuto(Util.formatarHoraSemData(dataRA),
						Util.formatarHoraSemData(registroAtendimento
								.getDataEncerramento()), ">")) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.encerrar_ra_hora_encerramento_posterior_hora_atendimento",
							null, Util.formatarHoraSemSegundos(dataRA));
				}
			}
			try {
				Date maiorData = this.repositorioRegistroAtendimento
				.obterMaiorDataEncerramentoOSRegistroAtendimento(registroAtendimento
						.getId());
				if (maiorData != null) {
					maiorData = Util.formatarDataSemHora(maiorData);
					int qtdeDiasOS = Util.obterQuantidadeDiasEntreDuasDatas(
							maiorData, registroAtendimento
							.getDataEncerramento());
					if (qtdeDiasOS < 0) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.encerrar_ra_data_encerramento_posterior_maior_data_encerramento_os",
								null, Util.formatarData(maiorData));
					} else if (Util.datasIguais(maiorData, registroAtendimento
							.getDataEncerramento())) {
						// Caso o ra tenha ordens de Serviço já encerradas e a
						// data do encerramento seja igual maior data
						// do encerramento das ordens de Serviço e a hora do
						// encerramento seja anterior hora da maior
						// data do encerramento das ordens de Serviço.
						if (Util.compararHoraMinuto(Util
								.formatarHoraSemData(maiorData), Util
								.formatarHoraSemData(registroAtendimento
										.getDataEncerramento()), ">")) {
							sessionContext.setRollbackOnly();
							throw new ControladorException(
									"atencao.encerrar_ra_hora_encerramento_posterior_hora_maior_data_encerramento_os",
									null, Util
									.formatarHoraSemSegundos(maiorData));
						}
					}
				}
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
	}
	
	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 * 
	 * @param registroAtendimento
	 * @param registroAtendimentoUnidade
	 * @param dataConcorrencia
	 */
	public void encerrarRegistroAtendimento(
			RegistroAtendimento registroAtendimento,
			RegistroAtendimentoUnidade registroAtendimentoUnidade,
			Usuario usuarioLogado,
			Integer idDebitoTipo, 
			BigDecimal valorDebito, 
			Integer qtdeParcelas, 
			String percentualCobranca,
			Boolean confirmadoGeracaoNovoRA,String urlBotaoVoltar,boolean encerrarDebitoACobrar) 
	throws ControladorException {
		
		try {
			
			this.verificarRegistroAtendimentoControleConcorrencia(registroAtendimento);
			
			// Inserir Registro Atendimento Unidade
			getControladorUtil().inserir(registroAtendimentoUnidade);
			
			
			validarOsPendente(registroAtendimento,registroAtendimentoUnidade,urlBotaoVoltar,encerrarDebitoACobrar);

			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = registroAtendimento.getAtendimentoMotivoEncerramento();
			
			if(atendimentoMotivoEncerramento != null && 
					atendimentoMotivoEncerramento.getIndicadorExecucao() == 
						AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO){
				
				String idMotivoEncerramento = ""+ atendimentoMotivoEncerramento.getId();
				String parecerEncerramento = registroAtendimento.getParecerEncerramento();
				Date dataEncerramento = registroAtendimento.getDataEncerramento();
				
				
				// Encerrar OS que ainda não estáo encerradas
				Collection<OrdemServico> colecaoOS = this.obterOSRA(registroAtendimento.getId());
				
				if (colecaoOS != null && !colecaoOS.isEmpty()) {
					
					for (OrdemServico servico : colecaoOS) {
						if (new Short(servico.getSituacao()).intValue() != OrdemServico.SITUACAO_ENCERRADO
								.intValue()) {
							
							this.getControladorOrdemServico()
							.encerrarOSSemExecucao(servico.getId(),
									dataEncerramento,
									usuarioLogado,
									idMotivoEncerramento,
									//dataUltimaAlteracao,
									servico.getUltimaAlteracao(),
									parecerEncerramento, null,
									null, null,null,null);
						}
					}
				}
			}
			
			// Atualizar Tabela RA com dados do Encerramento
			repositorioRegistroAtendimento.encerrarRegistroAtendimento(registroAtendimento);
			
			//[UC1181] Verificar se é necessário enviar email com questionario de satisfacao
			if(registroAtendimento.getSolicitacaoTipoEspecificacao() != null){  
				int idEspecificacao = registroAtendimento.getSolicitacaoTipoEspecificacao().getId();
	    		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
	    		filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
	    		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));
	    		List<SolicitacaoTipoEspecificacao> listaSolicitacaoTipoEspecificacao = new ArrayList<SolicitacaoTipoEspecificacao>(getControladorUtil().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName()));
	    		if(listaSolicitacaoTipoEspecificacao != null && !listaSolicitacaoTipoEspecificacao.isEmpty()){
	    			SolicitacaoTipoEspecificacao especificacao = listaSolicitacaoTipoEspecificacao.get(0);
	    			if(especificacao.getServicoTipo() != null && especificacao.getServicoTipo().getIndicadorEnvioPesquisaSatisfacao().intValue() == 1
	    					&& atendimentoMotivoEncerramento.getId().intValue() == AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO.intValue()){
	    				String nomeSolicitante = this.obterNomeSolicitanteRA(registroAtendimento.getId());
	    				RegistroAtendimentoSolicitante registroAtendimentoSolicitante = this.consultarRegistroAtendimentoSolicitante(registroAtendimento.getId());
	    				this.enviarEmailQuestionarioSatisfacao(nomeSolicitante, registroAtendimentoSolicitante.getEnderecoEmail(), registroAtendimentoSolicitante.getNumeroProtocoloAtendimento(), registroAtendimento.getId().intValue()+"");
	    			}
				}
			}
			
			if (idDebitoTipo != null && valorDebito!=null){
				
				//[UCXXXX] - Gerar Débito Registro de Atendimento
				this.gerarDebitoRegistroAtendimento(registroAtendimento.getId(), idDebitoTipo, valorDebito, 
						qtdeParcelas, percentualCobranca, usuarioLogado);
				
			}
			
			if ( confirmadoGeracaoNovoRA != null && confirmadoGeracaoNovoRA ){
				
				FiltroRegistroAtendimento filtro = 
					new FiltroRegistroAtendimento();
				
				filtro.adicionarParametro( new ParametroSimples( 
						FiltroRegistroAtendimento.ID, 
						registroAtendimento.getId() ) );
				
				filtro.adicionarCaminhoParaCarregamentoEntidade( 
				"imovel" );
				
				filtro.adicionarCaminhoParaCarregamentoEntidade( 
				"solicitacaoTipoEspecificacao.solicitacaoTipoEspecificacaoNovoRA" );               
				
				Collection<RegistroAtendimento> 
				colRegistroAtendimento = 
					this.getControladorUtil().pesquisar( 
							filtro, 
							RegistroAtendimento.class.getName() );
				
				registroAtendimento  = 
					( RegistroAtendimento ) 
					Util.retonarObjetoDeColecao( 
							colRegistroAtendimento );                
				
				UnidadeOrganizacional unidadeAtendimento = 
					this.getControladorUnidade().pesquisarUnidadeUsuario(usuarioLogado.getId());
				
				Collection colecaoEnderecos = new ArrayList();
				
				Imovel imovelEndereco = 
					this.getControladorEndereco().pesquisarImovelParaEndereco( 
							registroAtendimento.getImovel().getId() );
				
				colecaoEnderecos.add(imovelEndereco);
				
				//ANEXOS
				FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();
				
				filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,
				registroAtendimento.getId()));

				Collection colecaoRegistroAtendimentoAnexo = this.getControladorUtil().pesquisar(filtroRegistroAtendimentoAnexo, RegistroAtendimentoAnexo.class.getName());
				
				RADadosGeraisHelper raDadosGerais = RABuilder.buildRADadosGerais(registroAtendimento, usuarioLogado, unidadeAtendimento.getId(), colecaoRegistroAtendimentoAnexo);
				RALocalOcorrenciaHelper raLocalOcorrencia = RABuilder.buildRALocalOcorrencia(registroAtendimento, colecaoEnderecos, unidadeAtendimento.getId());
				RASolicitanteHelper raSolicitante = RABuilder.buildRASolicitante(registroAtendimento, false, unidadeAtendimento.getId());
				
				this.inserirRegistroAtendimento(raDadosGerais, raLocalOcorrencia, raSolicitante);
			}            
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * @author Jose Guilherme Macedo Vieira
	 * @date 29/06/2009
	 *
	 * @param RegistroAtendimento registroAtendimento - o registro de atendimento
	 * @param RegistroAtendimentoUnidade registroAtendimentoUnidade - a unidade do registro atendimento
	 * @param String urlBotaoVoltar - a url do botão voltar da mensagem
	 * @throws ControladorException
	 */
	public void validarOsPendente(RegistroAtendimento registroAtendimento,
			RegistroAtendimentoUnidade registroAtendimentoUnidade,
			String urlBotaoVoltar,boolean encerrarDebitoACobrar) throws ControladorException{
		
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = registroAtendimento.getAtendimentoMotivoEncerramento();
		
		//Caso exista ordem de Serviço não encerrada e programada para
		// o registro de atendimento
		Collection<OrdemServico> colecaoOrdemServico = obterOSRA(registroAtendimento.getId());
		
		boolean osPendente = false;
		
		if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {
			for (OrdemServico ordemServico : colecaoOrdemServico) {
				if ((new Short(ordemServico.getSituacao())).intValue() == OrdemServico.SITUACAO_PENDENTE
						.intValue()) {
					osPendente = true;
					break;
				}
			}
		}
		
		if(osPendente){
			
			if (encerrarDebitoACobrar && atendimentoMotivoEncerramento != null && 
					atendimentoMotivoEncerramento.getIndicadorExecucao() ==	AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM){
				
				if(urlBotaoVoltar != null && !urlBotaoVoltar.equals("")){
					sessionContext.setRollbackOnly();
					
					throw new ControladorException("atencao.encerrar_ra_os_pendente_inserir_debito_a_cobrar",urlBotaoVoltar,null);
				}
			}else if (atendimentoMotivoEncerramento != null
					&& atendimentoMotivoEncerramento.getIndicadorExecucao() != AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO) {
				
				sessionContext.setRollbackOnly();
				
				throw new ControladorException("atencao.indicador_execucao_nao",urlBotaoVoltar,null);
				
			}
		}
	}
	
	/**
	 * [UC0440] Consultar Programação de Abastecimento
	 * 
	 * Caso exista Programação de Abastecimento de uma determinada Área de
	 * Bairro
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/08/2006
	 * 
	 * @param idMunicipio
	 * @param idBairro
	 * @param areaBairro
	 * @param mesAnoReferencia
	 * @return Collection<AbastecimentoProgramacao>
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	public Collection consultarProgramacaoAbastecimento(String idMunicipio,
			String codigoBairro, String areaBairro, String mesAnoReferencia)
	throws ControladorException {
		
		String ano = null;
		
		String mes = null;
		
		String anoMesReferencia = null;
		
		if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {
			
			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			String anoMesCorrente = ano + mes;
			anoMesReferencia = anoMesCorrente;
			
		} else {
			
			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);
			
			if (mesAnoValido == false) {
				throw new ControladorException(
						"atencao.adicionar_debito_ano_mes_referencia_invalido",
						null, mesAnoReferencia);
			}
			
			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);
			
			anoMesReferencia = ano + mes;
		}
		int idBairro = ConstantesSistema.NUMERO_NAO_INFORMADO;
		
		if ( codigoBairro != null && !codigoBairro.equals("")) {
			FiltroBairro filtroBairro = new FiltroBairro();
			
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoBairro));
			
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, idMunicipio));
			
			Collection colecaBairro = getControladorUtil().pesquisar(filtroBairro,
					Bairro.class.getName());
			
			Bairro bairro = (Bairro) colecaBairro.iterator().next();
			
			idBairro = bairro.getId();
		}
		
		// Seleciona todas as programações de abastecimento de acordo da Área de
		// bairro e o mês e o ano de referencia informado. Ordenando de forma
		// crescente por Data de Inicio de Abastecimento e Hora de Inicio de
		// abastecimento
		
		FiltroAbastecimentoProgramacao filtroAbastecimentoProgramacao = new FiltroAbastecimentoProgramacao();
		
		filtroAbastecimentoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("bairroArea");
		
		filtroAbastecimentoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		filtroAbastecimentoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		
		filtroAbastecimentoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		
		filtroAbastecimentoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		
		filtroAbastecimentoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
		
		filtroAbastecimentoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("bairro");
		
		filtroAbastecimentoProgramacao.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.ANOMESREFERENCIA,
				anoMesReferencia));
		filtroAbastecimentoProgramacao.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.MUNICIPIO, idMunicipio));
		
		if (codigoBairro != null && !codigoBairro.equals("")) {
			filtroAbastecimentoProgramacao.adicionarParametro(new ParametroSimples(
					FiltroAbastecimentoProgramacao.BAIRRO, idBairro));
			
			filtroAbastecimentoProgramacao.adicionarParametro(new ParametroSimples(
					FiltroAbastecimentoProgramacao.BAIRRO_AREA, areaBairro));
		}
		
		filtroAbastecimentoProgramacao.setCampoOrderBy(
				FiltroAbastecimentoProgramacao.DATA_INICIO,
				FiltroAbastecimentoProgramacao.HORA_INICIO);
		
		Collection colecaoAbastecimentoProgramacao = getControladorUtil()
		.pesquisar(filtroAbastecimentoProgramacao,
				AbastecimentoProgramacao.class.getName());
		
		return colecaoAbastecimentoProgramacao;
		
	}
	
	/**
	 * [UC0440] Consultar Programação de Manutenção
	 * 
	 * Caso exista Programação de Manutenção de uma determinada Área de Bairro
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/08/2006
	 * 
	 * @param idMunicipio
	 * @param idBairro
	 * @param areaBairro
	 * @param mesAnoReferencia
	 * @return Collection<ManutencaoProgramacao>
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	public Collection consultarProgramacaoManutencao(String idMunicipio,
			String codigoBairro, String areaBairro, String mesAnoReferencia)
	throws ControladorException {
		String ano = null;
		
		String mes = null;
		
		String anoMesReferencia = null;
		
		if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {
			
			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			String anoMesCorrente = ano + mes;
			anoMesReferencia = anoMesCorrente;
			
		} else {
			
			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);
			if (mesAnoValido == false) {
				throw new ControladorException(
				"atencao.ano_mes_referencia.invalida");
			}
			
			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);
			
			anoMesReferencia = ano + mes;
		}
		
		int idBairro = ConstantesSistema.NUMERO_NAO_INFORMADO;
		if ( codigoBairro != null && !codigoBairro.equals("") ) {
		
			FiltroBairro filtroBairro = new FiltroBairro();
			
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoBairro));
			
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, idMunicipio));
			
			Collection colecaBairro = getControladorUtil().pesquisar(filtroBairro,
					Bairro.class.getName());
			
			Bairro bairro = (Bairro) colecaBairro.iterator().next();
			
			idBairro = bairro.getId();
		}
		// Seleciona todas as programações de abastecimento de acordo da Área de
		// bairro e o mês e o ano de referencia informado. Ordenando de forma
		// crescente por Data de Inicio de Abastecimento e Hora de Inicio de
		// abastecimento
		
		FiltroManutencaoProgramacao filtroManutencaoProgramacao = new FiltroManutencaoProgramacao();
		
		filtroManutencaoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("bairroArea");
		
		filtroManutencaoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		filtroManutencaoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		
		filtroManutencaoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		
		filtroManutencaoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		
		filtroManutencaoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
		
		filtroManutencaoProgramacao
		.adicionarCaminhoParaCarregamentoEntidade("bairro");
		
		filtroManutencaoProgramacao
		.adicionarParametro(new ParametroSimples(
				FiltroManutencaoProgramacao.ANOMESREFERENCIA,
				anoMesReferencia));
		
		filtroManutencaoProgramacao.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.MUNICIPIO, idMunicipio));
		
		if ( codigoBairro != null && !codigoBairro.equals("") ) {
			
		
		filtroManutencaoProgramacao.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.BAIRRO, idBairro));
		
		filtroManutencaoProgramacao.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.BAIRRO_AREA, areaBairro));
		}
		
		filtroManutencaoProgramacao.setCampoOrderBy(
				FiltroAbastecimentoProgramacao.DATA_INICIO,
				FiltroAbastecimentoProgramacao.HORA_INICIO);
		
		Collection colecaoManutencaoProgramacao = getControladorUtil()
		.pesquisar(filtroManutencaoProgramacao,
				ManutencaoProgramacao.class.getName());
		
		return colecaoManutencaoProgramacao;
		
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especificação esteja associada a um tipo de Serviço (SVTP_ID da
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com o valor diferente de nulo).
	 * (automática)
	 * 
	 * [SB0030] Gerar Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean gerarOrdemServicoAutomatica(
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		boolean retorno = false;
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				idSolicitacaoTipoEspecificacao));
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroNaoNulo(
				FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO_ID));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			retorno = true;
		}
		
		return retorno;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * método Responsável pela elaboração de um objeto do tipo OrdemServico que
	 * será inserido junto com um RA
	 * 
	 * [SB0030] Gerar Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 28/11/2006
	 * 
	 * @param idServicoTipo,
	 *            idSolicitacaoTipo
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico gerarOrdemServicoAutomatica(Integer idServicoTipo,
			Integer idSolicitacaoTipo) throws ControladorException {
		
		OrdemServico osGeradaAutomatica = new OrdemServico();
		
		// Carregando o servicoTipo informado
		// ===================================================================
		FiltroServicoTipo filtroServicoTipoInformado = new FiltroServicoTipo();
		filtroServicoTipoInformado
		.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia.servicoTipo");
		
		filtroServicoTipoInformado.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.ID, new Integer(idServicoTipo)));
		
		filtroServicoTipoInformado.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoServicoTipoInformado = getControladorUtil()
		.pesquisar(filtroServicoTipoInformado,
				ServicoTipo.class.getName());
		
		ServicoTipo servicoTipoInformado = (ServicoTipo) Util
		.retonarObjetoDeColecao(colecaoServicoTipoInformado);
		// ===================================================================
		
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipo.ID, new Integer(idSolicitacaoTipo)));
		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID,
				SolicitacaoTipoGrupo.ID_OPERACIONAL_AGUA_COM_DIAGNOSTICO));
		
		Collection colecaoSolicitacaoTipo = getControladorUtil().pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		
		/*
		 * Caso o grupo da solicitação do registro de atendimento esteja
		 * associado ao grupo "Operacional - Água"
		 */
		if (colecaoSolicitacaoTipo != null && !colecaoSolicitacaoTipo.isEmpty()) {
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo
			.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia.servicoTipo");
			filtroServicoTipo
			.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");
			
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.INDICADOR_DIAGNOSTICO_SERVICO_TIPO_REF,
					ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO));
			
			/*
			 * TODO - COSANPA - Mantis 402 - Felipe Santos - 13/03/2012
			 * 
			 * Verifica se o tipo de serviço informado possui referência.
			 * Caso SIM, adiciona no filtro de pesquisa o servico_tipo
			 * da referência.			 *
			 */			
			ServicoTipoReferencia servicoTipoReferencia = servicoTipoInformado.getServicoTipoReferencia();
			
			if (servicoTipoReferencia != null) {
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID,
						servicoTipoInformado.getServicoTipoReferencia().getServicoTipo().getId()));
			}
			// fim da alteração
			
			Collection colecaoServicoTipo = getControladorUtil().pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());
			
			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				
				ServicoTipo servicoTipo = (ServicoTipo) Util
				.retonarObjetoDeColecao(colecaoServicoTipo);
				
				if (servicoTipo.getServicoTipoPrioridade() != null) {
					
					osGeradaAutomatica
					.setServicoTipoPrioridadeOriginal(servicoTipo
							.getServicoTipoPrioridade());
					osGeradaAutomatica
					.setServicoTipoPrioridadeAtual(servicoTipo
							.getServicoTipoPrioridade());
					
				}
				
				osGeradaAutomatica.setServicoTipo(servicoTipo);
				osGeradaAutomatica
				.setServicoTipoReferencia(servicoTipoInformado);
				
			} else {
				
				if (servicoTipoInformado.getServicoTipoPrioridade() != null) {
					
					osGeradaAutomatica
					.setServicoTipoPrioridadeOriginal(servicoTipoInformado
							.getServicoTipoPrioridade());
					osGeradaAutomatica
					.setServicoTipoPrioridadeAtual(servicoTipoInformado
							.getServicoTipoPrioridade());
					
				}
				
				osGeradaAutomatica.setServicoTipo(servicoTipoInformado);
				
				if (servicoTipoInformado.getServicoTipoReferencia() != null
						&& servicoTipoInformado.getServicoTipoReferencia()
						.getServicoTipo() != null) {
					
					osGeradaAutomatica
					.setServicoTipoReferencia(servicoTipoInformado
							.getServicoTipoReferencia()
							.getServicoTipo());
					
				}
				
			}
			
		} else {
			
			if (servicoTipoInformado.getServicoTipoPrioridade() != null) {
				
				osGeradaAutomatica
				.setServicoTipoPrioridadeOriginal(servicoTipoInformado
						.getServicoTipoPrioridade());
				osGeradaAutomatica
				.setServicoTipoPrioridadeAtual(servicoTipoInformado
						.getServicoTipoPrioridade());
				
			}
			
			osGeradaAutomatica.setServicoTipo(servicoTipoInformado);
			
			if (servicoTipoInformado.getServicoTipoReferencia() != null
					&& servicoTipoInformado.getServicoTipoReferencia()
					.getServicoTipo() != null) {
				
				osGeradaAutomatica
				.setServicoTipoReferencia(servicoTipoInformado
						.getServicoTipoReferencia().getServicoTipo());
				
			}
			
		}
		
		return osGeradaAutomatica;
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especificação possa gerar alguma ordem de Serviço
	 * (STEP_ICGERACAOORDEMSERVICO da tabela SOLICITACAO_TIPO_ESPECIFICACAO com
	 * o valor correspondente a um). (OPCIONAL) [SB0030] Gerar Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean gerarOrdemServicoOpcional(
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		boolean retorno = false;
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				idSolicitacaoTipoEspecificacao));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			if (especificacao.getIndicadorGeracaoOrdemServico() == ConstantesSistema.INDICADOR_USO_ATIVO
					.shortValue()) {
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0028] Atualizar Registro de Atendimento (REGISTRO_ATENDIMENTO)
	 * 
	 * @author Sávio Luiz
	 * @date 30/08/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer[] atualizarRegistroAtendimento(Integer idRA,
			short indicadorAtendimentoOnLine, String dataAtendimento,
			String horaAtendimento, String tempoEsperaInicial,
			String tempoEsperaFinal, Integer idMeioSolicitacao,
			Integer idSolicitacaoTipoEspecificacao, String dataPrevista,
			String observacao, Integer idImovel,
			String descricaoLocalOcorrencia, Integer idSolicitacaoTipo,
			Collection colecaoEndereco, String pontoReferenciaLocalOcorrencia,
			Integer idBairroArea, Integer idLocalidade,
			Integer idSetorComercial, Integer idQuadra,
			Integer idDivisaoEsgoto, Integer idLocalOcorrencia,
			Integer idPavimentoRua, Integer idPavimentoCalcada,
			Integer idUnidadeAtendimento, Usuario usuarioLogado,
			Integer imovelObrigatorio, Date ultimaAlteracao,
			Collection colecaoRASolicitante,
			Collection colecaoRASolicitanteRemovida, Integer idServicoTipo,
			Integer especificacaoNaBase,Integer idUnidadeAtual,
			BigDecimal nnCoordenadaNorte,BigDecimal nnCoordenadaLeste,
			Collection colecaoRegistroAtendimentoAnexo,
			Collection colecaoRegistroAtendimentoConta,
			Collection colecaoRegistroAtendimentoContaRemover,
			Collection colecaoPagamento,BigDecimal nnDiametro) throws ControladorException {
		
		RegistroAtendimento ra = new RegistroAtendimento();
		
		if (idImovel != null && !idImovel.equals("")) {
			Integer idRAMesma = null;
			try {
				idRAMesma = repositorioRegistroAtendimento.verificarMesmaRA(
						idImovel, idRA);
				
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (idRAMesma == null) {
				getControladorOrdemServico().atualizarOsDaRA(idRA, idImovel);
			}
		}
		
		// controle de transação
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.ID, idRA));
		
		Collection colecaoRA = getControladorUtil().pesquisar(
				filtroRegistroAtendimento, RegistroAtendimento.class.getName());
		
		if (colecaoRA == null || colecaoRA.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		
		RegistroAtendimento raNaBase = (RegistroAtendimento) colecaoRA
		.iterator().next();
		
		if (raNaBase.getUltimaAlteracao().after(ultimaAlteracao)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		
		if (raNaBase.getNumeroImovel() != null)
			ra.setNumeroImovel(raNaBase.getNumeroImovel());
		
		ra.setNnDiametro(nnDiametro);
		
		Integer[] retorno = null;
		
		/*
		 * Verifica se e necessário geração automática de uma ordem de Serviço
		 */
		OrdemServico osGeradaAutomatica = null;
		if (this.gerarOrdemServicoAutomatica(idSolicitacaoTipoEspecificacao)
				&& !idSolicitacaoTipoEspecificacao.equals(especificacaoNaBase)) {
			
			retorno = new Integer[2];
			
			osGeradaAutomatica = this.gerarOrdemServicoAutomatica(
					idServicoTipo, idSolicitacaoTipo);
		} else {
			
			retorno = new Integer[1];
		}
		
		// fim controle transação
		
		// seta o id do registro atendimento
		ra.setId(idRA);
		
		// última alteração
		ra.setUltimaAlteracao(new Date());
		
		ra.setIndicadorAtendimentoOnline(indicadorAtendimentoOnLine);
		
		//Coordenadas GIS
		ra.setNnCoordenadaNorte(nnCoordenadaNorte);
		ra.setNnCoordenadaLeste(nnCoordenadaLeste);
		
		// Data e Hora do atendimento
		String dataHoraAtendimento = dataAtendimento + " " + horaAtendimento
		+ ":00";
		Date dataHoraAtendimentoObjetoDate = Util
		.converteStringParaDateHora(dataHoraAtendimento);
		
		ra.setRegistroAtendimento(dataHoraAtendimentoObjetoDate);
		
		// Tempo de espera inicial
		if (tempoEsperaInicial != null && !tempoEsperaInicial.equals("")) {
			tempoEsperaInicial = tempoEsperaInicial + ":00";
			Date dataEsperaInicial = Util
			.converteStringParaDateHora(dataAtendimento + " "
					+ tempoEsperaInicial);
			ra.setDataInicioEspera(dataEsperaInicial);
			tempoEsperaFinal = tempoEsperaFinal + ":00";
			Date dataEsperaFinal = Util
			.converteStringParaDateHora(dataAtendimento + " "
					+ tempoEsperaFinal);
			ra.setDataFimEspera(dataEsperaFinal);
		} else {
			ra.setDataInicioEspera(null);
			ra.setDataFimEspera(null);
		}
		
		// Meio de Solicitação
		MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
		meioSolicitacao.setId(idMeioSolicitacao);
		
		ra.setMeioSolicitacao(meioSolicitacao);
		
		// Especificação
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(idSolicitacaoTipoEspecificacao);
		ra.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		
		// Data Original e Data Prevista
		Date dataPrevistaObjetoDate = Util.converteStringParaDate(dataPrevista);
		
		ra.setDataPrevistaOriginal(dataPrevistaObjetoDate);
		ra.setDataPrevistaAtual(dataPrevistaObjetoDate);
		
		// Observação
		if (observacao != null && !observacao.equals("")) {
			ra.setObservacao(observacao);
		} else {
			ra.setObservacao(null);
		}
		
		// Imóvel
		if (idImovel != null && !idImovel.equals("")) {
			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			ra.setImovel(imovel);
		} else {
			ra.setImovel(null);
		}
		
		/*
		 * Dados da Identificação do Local da ocorrência (caso a Descrição do
		 * Local da ocorrência esteja preenchida, todos os Dados da
		 * Identificação do Local da ocorrência devem ter o valor nulo; caso
		 * contrário, preencher de acordo com as regras abaixo)
		 */
		if (descricaoLocalOcorrencia != null
				&& !descricaoLocalOcorrencia.equals("")) {
			ra.setDescricaoLocalOcorrencia(descricaoLocalOcorrencia);
			
			// Código da Situação bloqueado
			ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_BLOQUEADO);
			// seta os campos para null
			ra.setLogradouroBairro(null);
			ra.setLogradouroCep(null);
			ra.setComplementoEndereco(null);
			ra.setPontoReferencia(null);
			ra.setBairroArea(null);
			ra.setLocalidade(null);
			ra.setSetorComercial(null);
			ra.setQuadra(null);
			ra.setDivisaoEsgoto(null);
			ra.setLocalOcorrencia(null);
			ra.setPavimentoRua(null);
			ra.setPavimentoCalcada(null);
		} else {
			
			/*
			 * Caso a matrícula do Imóvel seja obrigatória (STEP_ICMATRICULA com
			 * o valor correspondente a um na tabela
			 * SOLICITACAO_TIPO_ESPECIFICACAO), nulo.
			 */
			if (this
					.especificacaoExigeMatriculaImovel(solicitacaoTipoEspecificacao)
					|| (colecaoEndereco != null && !colecaoEndereco.isEmpty())) {
				
				Imovel imovelEndereco = (Imovel) Util
				.retonarObjetoDeColecao(colecaoEndereco);
				
				// LogradouroBairro
				ra.setLogradouroBairro(imovelEndereco.getLogradouroBairro());
				
				// LogradouroCep
				ra.setLogradouroCep(imovelEndereco.getLogradouroCep());
				
				//numero do imovel
				ra.setNumeroImovel(imovelEndereco.getNumeroImovel());
				
				// Complemento endereço
				if (imovelEndereco.getComplementoEndereco() != null
						&& !imovelEndereco.getComplementoEndereco().equals("")) {
					ra.setComplementoEndereco(imovelEndereco
							.getComplementoEndereco());
				} else {
					ra.setComplementoEndereco(null);
				}
				
				ra.setPerimetroInicial(imovelEndereco.getPerimetroInicial());
				ra.setPerimetroFinal(imovelEndereco.getPerimetroFinal());
				
			} else {
				// LogradouroBairro
				ra.setLogradouroBairro(null);
				// LogradouroCep
				ra.setLogradouroCep(null);
				ra.setComplementoEndereco(null);
			}
			
			// Ponto de Referência
			if (pontoReferenciaLocalOcorrencia != null
					&& !pontoReferenciaLocalOcorrencia.equals("")) {
				ra.setPontoReferencia(pontoReferenciaLocalOcorrencia);
			} else {
				ra.setPontoReferencia(null);
			}
			
			// Área do Bairro
			if (idBairroArea != null && !idBairroArea.equals("")) {
				BairroArea bairroArea = new BairroArea();
				bairroArea.setId(idBairroArea);
				ra.setBairroArea(bairroArea);
			} else {
				ra.setBairroArea(null);
			}
			
			// Localidade
			if (idLocalidade != null && !idLocalidade.equals("")) {
				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				ra.setLocalidade(localidade);
			} else {
				ra.setLocalidade(null);
			}
			
			// Setor Comercial
			if (idSetorComercial != null && !idSetorComercial.equals("")) {
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setId(idSetorComercial);
				ra.setSetorComercial(setorComercial);
			} else {
				ra.setSetorComercial(null);
			}
			
			// Quadra
			if (idQuadra != null && !idQuadra.equals("")) {
				Quadra quadra = new Quadra();
				quadra.setId(idQuadra);
				ra.setQuadra(quadra);
			} else {
				ra.setQuadra(null);
			}
			
			// divisão de Esgoto
			if (idDivisaoEsgoto != null && !idDivisaoEsgoto.equals("")) {
				DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
				divisaoEsgoto.setId(idDivisaoEsgoto);
				ra.setDivisaoEsgoto(divisaoEsgoto);
			} else {
				ra.setDivisaoEsgoto(null);
			}
			
			// Local ocorrência
			if (idLocalOcorrencia != null && !idLocalOcorrencia.equals("")) {
				LocalOcorrencia localOcorrencia = new LocalOcorrencia();
				localOcorrencia.setId(idLocalOcorrencia);
				ra.setLocalOcorrencia(localOcorrencia);
			} else {
				ra.setLocalOcorrencia(null);
			}
			
			// Pavimento Rua
			if (idPavimentoRua != null && !idPavimentoRua.equals("")) {
				PavimentoRua pavimentoRua = new PavimentoRua();
				pavimentoRua.setId(idPavimentoRua);
				ra.setPavimentoRua(pavimentoRua);
			} else {
				ra.setPavimentoRua(null);
			}
			
			// Pavimento Calçada
			if (idPavimentoCalcada != null && !idPavimentoCalcada.equals("")) {
				PavimentoCalcada pavimentoCalcada = new PavimentoCalcada();
				pavimentoCalcada.setId(idPavimentoCalcada);
				ra.setPavimentoCalcada(pavimentoCalcada);
			} else {
				ra.setPavimentoCalcada(null);
			}
			
			// Código da Situação
			ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_PENDENTE);
		}
		
		// inclusão da coluna unidade atual na tabela REGISTRO_ATENDIMENTO e ORDEM_SERVICO
		// Vivianne Sousa 19/06/2008 analista:Fátima Sampaio
		if(idUnidadeAtual != null){
			UnidadeOrganizacional unidadeOrganizacionalAtual = new UnidadeOrganizacional();
			unidadeOrganizacionalAtual.setId(idUnidadeAtual);
			ra.setUnidadeAtual(unidadeOrganizacionalAtual);
		}
		
		if (colecaoRASolicitante != null && !colecaoRASolicitante.isEmpty()) {
			Iterator colecaoRASolicitanteIterator = colecaoRASolicitante.iterator();
			
			while (colecaoRASolicitanteIterator.hasNext()) {
				RegistroAtendimentoSolicitante raSolicitante = (RegistroAtendimentoSolicitante) colecaoRASolicitanteIterator.next();
				
				if (raSolicitante.getIndicadorSolicitantePrincipal() == ConstantesSistema.SIM.shortValue()) {
					if (raSolicitante.getCliente() != null && raSolicitante.getCliente().getId() != null && idSolicitacaoTipoEspecificacao != null) {
						validarObrigatoriedadeDocumento(raSolicitante.getCliente().getId(), idSolicitacaoTipoEspecificacao,
								raNaBase.getMeioSolicitacao().getId(), idImovel);
					}
				}
			}
		}
		
		// atualizar o registro de atendimento
		this.getControladorUtil().atualizar(ra);
		
		// [SB0028] Atualizar Registro de Atendimento
		// (REGISTRO_ATENDIMENTO_UNIDADE)
		this.atualizarRegistroAtendimentoUnidade(ra.getId(),
				idUnidadeAtendimento, usuarioLogado.getId());
		
		/*
		 * [UC0408] Atualizar Registro de Atendimento [SB0027] Atualizar
		 * Solicitante do Registro de Atendimento
		 */
		this.atualizarRegistroAtendimentoSolicitante(colecaoRASolicitante,
				colecaoRASolicitanteRemovida);
		
		/*
		 * Atualizar Anexo(s) do Registro de Atendimento
		 */
		this.atualizarRegistroAtendimentoAnexo(ra, colecaoRegistroAtendimentoAnexo);
		
		
		/*
		 * Atualizando as associações entre as Contas e o Registro de atendimento
		 * Mariana Victor - 31/01/2011
		 */
		this.atualizarRegistroAtendimentoConta(ra.getId(),
				colecaoRegistroAtendimentoConta, colecaoRegistroAtendimentoContaRemover);
		
		
		
		
		
		/*
		 * Atualizando as associações entre as Pagamentos e o Registro de atendimento
		 * Rafaelpinto - 16/03/2011
		 */
		this.atualizarRegistroAtendimentoPagamentoDuplicidade(ra.getId(),idImovel,colecaoPagamento);		
		
		// Comentado por Raphael Rossiter em 01/03/2007
		/*
		 * Verifica se e necessário geração automática de uma ordem de Serviço
		 */
		/*
		 * if (this.gerarOrdemServicoAutomatica(idSolicitacaoTipoEspecificacao) &&
		 * os == null &&
		 * !idSolicitacaoTipoEspecificacao.equals(especificacaoNaBase)) {
		 * sessionContext.setRollbackOnly(); throw new ControladorException(
		 * "atencao.dados_ordem_servico_nao_informado"); } else if (this
		 * .gerarOrdemServicoAutomatica(idSolicitacaoTipoEspecificacao) && os !=
		 * null) { // [UC0430] - Gerar Ordem de Serviço
		 * os.setRegistroAtendimento(ra);
		 * this.getControladorOrdemServico().gerarOrdemServico(os,
		 * usuarioLogado); }
		 */
		
		// Colocado por Raphael Rossiter em 01/03/2007
		// [UC0430] - Gerar Ordem de Serviço
		if (this.gerarOrdemServicoAutomatica(idSolicitacaoTipoEspecificacao)
				&& osGeradaAutomatica != null
				&& !idSolicitacaoTipoEspecificacao.equals(especificacaoNaBase)) {
			
			// O Imóvel da OS será o mesmo do RA
			if (ra.getImovel() != null) {
				osGeradaAutomatica.setImovel(ra.getImovel());
			}
			
			osGeradaAutomatica.setRegistroAtendimento(ra);
			
			Integer idOrdemServico = this.getControladorOrdemServico()
			.gerarOrdemServico(osGeradaAutomatica, usuarioLogado,null);
			
			retorno[1] = idOrdemServico;
		}
		
		return retorno;
		
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0028] Atualiza Registro de Atendimento (REGISTRO_ATENDIMENTO_UNIDADE)
	 * 
	 * @author Sávio Luiz
	 * @date 30/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarRegistroAtendimentoUnidade(Integer idRa,
			Integer idUnidadeAtendimento, Integer idUsuarioLogado)
	throws ControladorException {
		
		RegistroAtendimentoUnidade raUnidade = null;
		try {
			raUnidade = repositorioRegistroAtendimento.pesquisarRAUnidade(idRa);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		if (raUnidade.getUnidadeOrganizacional() != null
				&& !raUnidade.getUnidadeOrganizacional().equals(
						idUnidadeAtendimento)) {
			
			UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
			unidadeOrganizacional.setId(idUnidadeAtendimento);
			raUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
			// última Alteração
			raUnidade.setUltimaAlteracao(new Date());
			this.getControladorUtil().atualizar(raUnidade);
		}
		
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0027] Atualizar Solicitante do Registro de Atendimento
	 * (REGISTRO_ATENDIMENTO_SOLICITANTE)
	 * 
	 * @author Sávio Luiz
	 * @date 30/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarRegistroAtendimentoSolicitante(
			Collection colecaoRASolicitante,
			Collection colecaoRASolicitanteRemovida)
	throws ControladorException {
		
		Collection colecaoSolicitacaoFone = null;
		
		if (colecaoRASolicitante != null && !colecaoRASolicitante.isEmpty()) {
			Iterator itRASolicitante = colecaoRASolicitante.iterator();
			while (itRASolicitante.hasNext()) {
				RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) itRASolicitante
				.next();
				
				if(registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa() == null){
					registroAtendimentoSolicitante.setIndicadorEnvioEmailPesquisa(ConstantesSistema.NAO);
					}

				
				if (!(registroAtendimentoSolicitante.getSolicitanteFones() instanceof PersistentSet)) {
					colecaoSolicitacaoFone = registroAtendimentoSolicitante
					.getSolicitanteFones();
				}				
				// limpa o campo de fone no registro atendimento solicitante
				registroAtendimentoSolicitante
				.setSolicitanteFones(new HashSet());
				Integer idRASolicitante = registroAtendimentoSolicitante
				.getID();
				if (idRASolicitante != null) {
					FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
					filtroRegistroAtendimentoSolicitante
					.adicionarParametro(new ParametroSimples(
							FiltroRegistroAtendimentoSolicitante.ID,
							idRASolicitante));
					Collection colecaoRASolicitanteNaBase = getControladorUtil()
					.pesquisar(
							filtroRegistroAtendimentoSolicitante,
							RegistroAtendimentoSolicitante.class
							.getName());
					
					if (colecaoRASolicitanteNaBase == null
							|| colecaoRASolicitanteNaBase.isEmpty()) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
						"atencao.atualizacao.timestamp");
					}
					
					RegistroAtendimentoSolicitante raSolicitanteNaBase = (RegistroAtendimentoSolicitante) colecaoRASolicitanteNaBase
					.iterator().next();
					
					if (raSolicitanteNaBase.getUltimaAlteracao()
							.after(
									registroAtendimentoSolicitante
									.getUltimaAlteracao())) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
						"atencao.atualizacao.timestamp");
					}
					
					registroAtendimentoSolicitante
					.setUltimaAlteracao(new Date());
					
					getControladorUtil().atualizar(
							registroAtendimentoSolicitante);
					
				} else {
					registroAtendimentoSolicitante
					.setUltimaAlteracao(new Date());
					
					idRASolicitante = (Integer) getControladorUtil().inserir(
							registroAtendimentoSolicitante);
				}
				
				try {
					repositorioRegistroAtendimento
					.removerSolicitanteFone(registroAtendimentoSolicitante
							.getID());
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
				if (colecaoSolicitacaoFone != null
						&& !colecaoSolicitacaoFone.isEmpty()) {
					Iterator iteSolicitacaoFone = colecaoSolicitacaoFone
					.iterator();
					while (iteSolicitacaoFone.hasNext()) {
						ClienteFone clienteFone = (ClienteFone) iteSolicitacaoFone
						.next();
						
						if (clienteFone.getId() == null) {
							SolicitanteFone solicitanteFone = new SolicitanteFone();
							solicitanteFone.setId(clienteFone.getId());
							solicitanteFone.setDdd(new Short(clienteFone
									.getDdd()));
							solicitanteFone.setFone(clienteFone.getTelefone());
							solicitanteFone.setRamal(clienteFone.getRamal());
							solicitanteFone.setIndicadorPadrao(clienteFone
									.getIndicadorTelefonePadrao());
							solicitanteFone.setFoneTipo(clienteFone
									.getFoneTipo());
							solicitanteFone.setUltimaAlteracao(clienteFone
									.getUltimaAlteracao());
							RegistroAtendimentoSolicitante registroAtendimentoSolicitanteNovo = new RegistroAtendimentoSolicitante();
							registroAtendimentoSolicitanteNovo
							.setID(idRASolicitante);
							solicitanteFone
							.setRegistroAtendimentoSolicitante(registroAtendimentoSolicitanteNovo);
							this.getControladorUtil().inserir(solicitanteFone);
						}
					}
				}
			}
		}
		
		if (colecaoRASolicitanteRemovida != null
				&& !colecaoRASolicitanteRemovida.isEmpty()) {
			Iterator itRASolicitanteRemovida = colecaoRASolicitanteRemovida
			.iterator();
			while (itRASolicitanteRemovida.hasNext()) {
				RegistroAtendimentoSolicitante registroAtendimentoSolicitanteRemovida = (RegistroAtendimentoSolicitante) itRASolicitanteRemovida
				.next();
				registroAtendimentoSolicitanteRemovida
				.setSolicitanteFones(new HashSet());
				try {
					repositorioRegistroAtendimento
					.removerSolicitanteFone(registroAtendimentoSolicitanteRemovida
							.getID());
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
				this.getControladorUtil().remover(
						registroAtendimentoSolicitanteRemovida);
			}
		}
		
	}
	
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * [SB0001] Define Data Prevista
	 * 
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public Date definirDataPrevistaRA(Date dataAtendimento,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		Date retorno = null;
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao
		.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID,
				idSolicitacaoTipoEspecificacao));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this
		.getControladorUtil().pesquisar(
				filtroSolicitacaoTipoEspecificacao,
				SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao != null
				&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			if (solicitacaoTipoEspecificacao.getDiasPrazo() != null) {
				
				SistemaParametro sistemaParametro = this.getControladorUtil()
				.pesquisarParametrosDoSistema();
				
				//adicionado por Vivianne Sousa 21/11/2008
				//analista Fatima Sampaio
				if(sistemaParametro.getIndicadorCalculoPrevisaoRADiasUteis() != null &&
						sistemaParametro.getIndicadorCalculoPrevisaoRADiasUteis().equals(ConstantesSistema.SIM)){
					//Caso o sistema deva considerar apenas os dias uteis no calculo da data prevista
					
					//FERIADO NACIONAL
					Collection<NacionalFeriado> colecaoFeriadosNacionais = 
						getControladorUtil().pesquisarFeriadosNacionais();
					
					//FERIADO MUNICIPAL
//					FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();
//					
//					filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(
//					FiltroMunicipioFeriado.ID_MUNICIPIO, municipio.getId()));
//					
//					Collection<MunicipioFeriado> colecaoFeriadosMunicipais = 
//					getControladorUtil().pesquisar(filtroMunicipioFeriado,
//					MunicipioFeriado.class.getName());
					
					retorno = (Util.adicionarNumeroDiasUteisDeUmaData(
							dataAtendimento, solicitacaoTipoEspecificacao.getDiasPrazo(),
							colecaoFeriadosNacionais,null));
				}else{
					retorno = (Util.adicionarNumeroDiasDeUmaData(dataAtendimento,
							solicitacaoTipoEspecificacao.getDiasPrazo()));
				}
				
				
			} else {
				retorno = (dataAtendimento);
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * [SB0001],[SB0002],[SB0003] Define Unidade Destino
	 * 
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestino(
			Integer idSolicitacaoTipoEspecificacao, Integer idLocalidade,
			Integer idSolicitacaoTipo,
			boolean solicitacaoTipoRelativoAreaEsgoto, Integer idDivisaoEsgoto)
	throws ControladorException {
		
		UnidadeOrganizacional retorno = null;
		
		SistemaParametro sistemaParametro = this.getControladorUtil()
		.pesquisarParametrosDoSistema();
		
		try {
			if (sistemaParametro.getIndicadorSugestaoTramite() != null
					&& sistemaParametro.getIndicadorSugestaoTramite().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				
				UnidadeOrganizacional unidadeOrganizacionalEspecificacao = repositorioRegistroAtendimento
				.obterUnidadeDestinoEspecificacao(idSolicitacaoTipoEspecificacao);
				UnidadeOrganizacional unidadeOrganizacionalLocalidade = null;
				UnidadeOrganizacional unidadeOrganizacionalDivisaoEsgoto = null;
				if (unidadeOrganizacionalEspecificacao == null
						&& idLocalidade != null
						&& !solicitacaoTipoRelativoAreaEsgoto) {
					unidadeOrganizacionalLocalidade = repositorioRegistroAtendimento
					.definirUnidadeDestinoLocalidade(idLocalidade,
							idSolicitacaoTipo);
				}
				if ((unidadeOrganizacionalLocalidade == null || solicitacaoTipoRelativoAreaEsgoto)
						&& idDivisaoEsgoto != null) {
					unidadeOrganizacionalDivisaoEsgoto = repositorioRegistroAtendimento
					.definirUnidadeDestinoDivisaoEsgoto(idDivisaoEsgoto);
				}
				if (unidadeOrganizacionalEspecificacao != null) {
					retorno = unidadeOrganizacionalEspecificacao;
				} else if (unidadeOrganizacionalLocalidade != null
						&& !solicitacaoTipoRelativoAreaEsgoto) {
					retorno = unidadeOrganizacionalLocalidade;
				} else if (unidadeOrganizacionalDivisaoEsgoto != null
						&& solicitacaoTipoRelativoAreaEsgoto) {
					retorno = unidadeOrganizacionalDivisaoEsgoto;
				}
			}
			
			/*
			 * [FS0018] Verificarexistência de unidade centralizadora
			 */
			if (retorno != null) {
				UnidadeOrganizacional unidadeDestino = this
				.getControladorUnidade()
				.verificarExistenciaUnidadeCentralizadora(retorno);
				
				retorno = unidadeDestino;
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * [SB0006]Incluir Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer[] reativarRegistroAtendimento(RegistroAtendimento ra,
			Integer idUnidadeAtendimento, Integer idUsuarioLogado,
			Integer idCliente, Integer idRaSolicitante,
			Integer idUnidadeDestino, String parecerUnidadeDestino,
			Integer idSolicitacaoTipo) throws ControladorException {
		
		Integer[] retorno = null;
		
		Integer idRAEncerrada = ra.getRegistroAtendimentoReativacao().getId();
		
		OrdemServico osGeradaAutomatica = null;
		if (this.gerarOrdemServicoAutomatica(ra
				.getSolicitacaoTipoEspecificacao().getId())) {
			
			retorno = new Integer[2];
			
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao
			.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
			
			filtroSolicitacaoTipoEspecificacao
			.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.ID, ra
					.getSolicitacaoTipoEspecificacao().getId()));
			
			filtroSolicitacaoTipoEspecificacao
			.adicionarParametro(new ParametroNaoNulo(
					FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO_ID));
			
			filtroSolicitacaoTipoEspecificacao
			.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoSolicitacaoTipoEspecificacao = this
			.getControladorUtil().pesquisar(
					filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());
			
			Integer idServicoTipo = ((SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacao
					.iterator().next()).getServicoTipo().getId();
			
			osGeradaAutomatica = this.gerarOrdemServicoAutomatica(
					idServicoTipo, idSolicitacaoTipo);
		} else {
			
			retorno = new Integer[1];
		}
		
		Integer idRAGerado = (Integer) this.getControladorUtil().inserir(ra);
		ra.setId(idRAGerado);
		
		retorno[0] = idRAGerado;
		
		// [SB0006] Inclui Registro de Atendimento
		// (REGISTRO_ATENDIMENTO_UNIDADE)
		this.inserirRegistroAtendimentoUnidade(ra, idUnidadeAtendimento,
				idUsuarioLogado);
		
		Collection colecaoEnderecoSolicitante = null;
		Collection colecaoFone = null;
		
		try {
			RegistroAtendimentoSolicitante raSolicitante = repositorioRegistroAtendimento
			.obterRegistroAtendimentoSolicitante(idRaSolicitante);
			
			if (raSolicitante.getCliente() == null) {
				ClienteEndereco clienteEndereco = new ClienteEndereco();
				clienteEndereco.setNumero(raSolicitante.getNumeroImovel());
				clienteEndereco.setLogradouroBairro(raSolicitante
						.getLogradouroBairro());
				clienteEndereco.setLogradouroCep(raSolicitante
						.getLogradouroCep());
				
				colecaoEnderecoSolicitante = new ArrayList();
				colecaoEnderecoSolicitante.add(clienteEndereco);
				
				colecaoFone = setColecaoFone(idRaSolicitante, colecaoFone);
			}
			
			String pontoReferenciaSolicitante = raSolicitante
			.getPontoReferencia();
			
			String nomeSolicitante = raSolicitante.getSolicitante();
			
			boolean novoSolicitante = false;
			
			Integer idUnidadeSolicitante = null;
			if (raSolicitante.getUnidadeOrganizacional() != null) {
				idUnidadeSolicitante = raSolicitante.getUnidadeOrganizacional()
				.getId();
			}
			Integer idFuncionario = null;
			if (raSolicitante.getFuncionario() != null) {
				idFuncionario = raSolicitante.getFuncionario().getId();
			}
			
			/*
			 * [UC0366] Inserir Registro de Atendimento [SB0007] Inclui
			 * Solicitante do Registro de Atendimento
			 */
			String protocoloAtendimento = this.pesquisarProtocoloAtendimentoPorRA(idRAEncerrada);
			
			this.inserirRegistroAtendimentoSolicitante(idRAGerado, idCliente,
					colecaoEnderecoSolicitante, pontoReferenciaSolicitante,
					nomeSolicitante, novoSolicitante, idUnidadeSolicitante,
					idFuncionario, colecaoFone, protocoloAtendimento, null, null, null);
			
			/*
			 * Inclui Anexo(s) do Registro de Atendimento que será reativado, a partir dos
			 * anexos do registro de atendimento encerrado.
			 */
			FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();
			
			filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,
			idRAEncerrada));

			Collection colecaoRegistroAtendimentoAnexo = this.getControladorUtil()
			.pesquisar(filtroRegistroAtendimentoAnexo, RegistroAtendimentoAnexo.class.getName());
			
			this.inserirRegistroAtendimentoAnexo(idRAGerado, colecaoRegistroAtendimentoAnexo);
			
			
			
			// [SB0008] Gerar Trâmite
			Tramite tramite = new Tramite();
			
			// Registro Atendimento
			tramite.setRegistroAtendimento(ra);
			
			// Unidade Origem = Unidade Atendimento
			UnidadeOrganizacional unidadeOrigem = new UnidadeOrganizacional();
			unidadeOrigem.setId(idUnidadeAtendimento);
			
			tramite.setUnidadeOrganizacionalOrigem(unidadeOrigem);
			
			/*
			 * Caso a Unidade Destino esteja preenchida, Id da Unidade Destino;
			 * caso contrário, Id da Unidade de Atendimento
			 */
			if (idUnidadeDestino != null) {
				UnidadeOrganizacional unidadeDestino = new UnidadeOrganizacional();
				unidadeDestino.setId(idUnidadeDestino);
				
				tramite.setUnidadeOrganizacionalDestino(unidadeDestino);
			} else {
				tramite.setUnidadeOrganizacionalDestino(unidadeOrigem);
			}
			
			// usuário Logado = Registro e Responsável
			Usuario usuario = new Usuario();
			usuario.setId(idUsuarioLogado);
			
			tramite.setUsuarioRegistro(usuario);
			tramite.setUsuarioResponsavel(usuario);
			
			/*
			 * Caso o Parecer para a Unidade Destino esteja preenchido, Parecer
			 * para a Unidade Destino; caso contrário, tramite gerado pelo
			 * sistema na abertura do registro de atendimento.
			 */
			if (parecerUnidadeDestino != null) {
				tramite.setParecerTramite(parecerUnidadeDestino);
			}
			
			tramite.setDataTramite(new Date());
			tramite.setUltimaAlteracao(new Date());
			
			// [UC0427] Tramitar Registro de Atendimento
			this.tramitar(tramite, null);
			
			// Verifica se e necessário geração automática de uma ordem de
			// Serviço
			
			// [UC0430] - Gerar Ordem de Serviço
			if (this.gerarOrdemServicoAutomatica(ra
					.getSolicitacaoTipoEspecificacao().getId())
					&& osGeradaAutomatica != null) {
				
				// O Imóvel da OS será o mesmo do RA
				if (ra.getImovel() != null) {
					osGeradaAutomatica.setImovel(ra.getImovel());
				}
				
				osGeradaAutomatica.setRegistroAtendimento(ra);
				
				Integer idOrdemServico = this.getControladorOrdemServico()
				.gerarOrdemServico(osGeradaAutomatica, usuario, 
						Funcionalidade.REATIVAR_REGISTRO_ATENDIMENTO);
				
				retorno[1] = idOrdemServico;
			}
			
			/*
			 * if (this.gerarOrdemServicoAutomatica(ra
			 * .getSolicitacaoTipoEspecificacao().getId()) && os == null) {
			 * sessionContext.setRollbackOnly(); throw new ControladorException(
			 * "atencao.dados_ordem_servico_nao_informado"); } else if
			 * (this.gerarOrdemServicoAutomatica(ra
			 * .getSolicitacaoTipoEspecificacao().getId()) && os != null) { // O
			 * Imóvel da OS será o mesmo do RA if (ra.getImovel() != null) {
			 * os.setImovel(ra.getImovel()); } // [UC0430] - Gerar Ordem de
			 * Serviço os.setRegistroAtendimento(ra);
			 * this.getControladorOrdemServico() .gerarOrdemServico(os,
			 * usuario); }
			 */
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		// return ra.getId();
		
		return retorno;
	}
	
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @throws ControladorException
	 */
	private Collection setColecaoFone(Integer idRaSolicitante,
			Collection colecaoFone) throws ErroRepositorioException {
		Collection colecaoSolicitanteFone = repositorioRegistroAtendimento
		.pesquisarFoneSolicitante(idRaSolicitante);
		
		Iterator itaratorSolicitanteFone = colecaoSolicitanteFone.iterator();
		ClienteFone clienteFone = null;
		SolicitanteFone solicitanteFone = null;
		
		while (itaratorSolicitanteFone.hasNext()) {
			solicitanteFone = (SolicitanteFone) itaratorSolicitanteFone.next();
			
			clienteFone = new ClienteFone();
			
			// Tipo do Telefone
			clienteFone.setFoneTipo(solicitanteFone.getFoneTipo());
			
			// DDD
			clienteFone.setDdd(solicitanteFone.getDdd().toString());
			
			// Telefone
			clienteFone.setTelefone(solicitanteFone.getFone());
			
			// Ramal
			if (solicitanteFone.getRamal() != null) {
				clienteFone.setRamal(solicitanteFone.getRamal());
			}
			
			// Indicador Fone Padrao
			clienteFone.setIndicadorTelefonePadrao(solicitanteFone
					.getIndicadorPadrao());
			
			// Ultima alteração
			clienteFone.setUltimaAlteracao(new Date());
			
			colecaoFone = new ArrayList();
			colecaoFone.add(clienteFone);
		}
		return colecaoFone;
	}
	
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * [FS0001]Valida possibilidade de reativação
	 * 
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public void validaPossibilidadeReativacaoRA(Integer idRegistroAtendimento,
			Integer idUsuario) throws ControladorException {
		
		// [UC0421] Obter Unidade de Atendimento do RA
		/*
		 * UnidadeOrganizacional unidadeAtendimento = this
		 * .obterUnidadeAtendimentoRA(idRegistroAtendimento);
		 */
		
		// Colocado por Raphael Rossiter em 12/03/2007
		// [UC0418] - Obter Unidade Atual do RA
		UnidadeOrganizacional unidadeOrganizacional = obterUnidadeAtualRA(idRegistroAtendimento);
		
		// [UC0419] - Obter Indicador de Autorização para Manutenção do RA
		Short indicadorAutorizacao = this
		.obterIndicadorAutorizacaoManutencaoRA(unidadeOrganizacional
				.getId(), idUsuario);
		/*
		 * Caso o indicador de autorização para manutenção do RA retornado pelo
		 * UC0419 esteja com valor correspondente a 2-não, exibir a mensagem:
		 * "Este RA foi aberto pela unidade <<unid_dsunidade>>. não possível
		 * reati-lo."
		 */
		if (indicadorAutorizacao == null
				|| indicadorAutorizacao.equals(ConstantesSistema.NAO)) {
			throw new ControladorException("atencao.unidade_nao_autorizada",
					null, unidadeOrganizacional.getDescricao());
		}
		// [UC0420] Obter Descrição da situação da RA
		ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRAHelper = this
		.obterDescricaoSituacaoRA(idRegistroAtendimento);
		/*
		 * Caso o RA não esteja encerrado, exibir a mensagem "O Registro de
		 * Atendimento<<rgat_id>> está <<Descrição da situação do RA>>. não
		 * possível reativá-lo." (ra não encerrado - rgat_cdsituacao com valor
		 * correspondente diferente de dois)
		 */
		if (obterDescricaoSituacaoRAHelper.getCodigoSituacao() == null
				|| !obterDescricaoSituacaoRAHelper.getCodigoSituacao().equals(
						RegistroAtendimento.SITUACAO_ENCERRADO)) {
			throw new ControladorException("atencao.ra_nao_encerrado", null,
					new String[] {
					"" + idRegistroAtendimento,
					obterDescricaoSituacaoRAHelper
					.getDescricaoSituacao() });
		}
		// [UC0433] Obter Registro de Atendimento Associado
		ObterRAAssociadoHelper obterRAAssociadoHelper = this
		.obterRAAssociado(idRegistroAtendimento);
		/*
		 * Caso o RA seja duplicidade de outro registro de atendimento, exibir a
		 * mensagem "O Registro de Atendimento <<rgat_id>> duplicidade do
		 * Registro de Atendimento <<id do ra associado>>. não é possível
		 * reativá-lo." (ra duplicidade de outro registro de atendimento- Código
		 * de existência de RA Associado com o valor correspondente a um)
		 */
		if (obterRAAssociadoHelper == null
				|| obterRAAssociadoHelper
				.getCodigoExistenciaRAAssociado()
				.equals(
						RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA)) {
			throw new ControladorException("atencao.ra_duplicidade", null,
					new String[] {
					"" + idRegistroAtendimento,
					obterRAAssociadoHelper
					.getRegistroAtendimentoAssociado().getId()
					.toString() });
		}
		/*
		 * Caso o RA já tenha sido reativado, exibir a mensagem "O Registro de
		 * Atendimento <<rgat_id>> foi reativado. O Registro de Atendimento
		 * Atual o de número <<id de ra associado>>. não possível reativá-lo
		 * novamente." (ra reativado - Código de existência de RA Associado com
		 * o valor correspondente a dois)
		 */
		if (obterRAAssociadoHelper == null
				|| obterRAAssociadoHelper.getCodigoExistenciaRAAssociado()
				.equals(RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL)) {
			throw new ControladorException("atencao.ra_reativado", null,
					new String[] {
					"" + idRegistroAtendimento,
					obterRAAssociadoHelper
					.getRegistroAtendimentoAssociado().getId()
					.toString() });
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * SOTP_ICFALTAAGUA da tabela SOLICITACAO_TIPO com o valor correspondente a
	 * um e STEP_ICMATRICULA com o valor correspondente a dois na tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO).
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @return SolicitacaoTipoEspecificacao
	 * @throws ControladorException
	 */
	public SolicitacaoTipoEspecificacao pesquisarTipoEspecificacaoFaltaAguaGeneralizada()
	throws ControladorException {
		
		SolicitacaoTipoEspecificacao retorno = null;
		SolicitacaoTipo solicitacaoTipo = null;
		
		Collection colecaoPesquisa = null;
		
		try {
			colecaoPesquisa = repositorioRegistroAtendimento
			.pesquisarTipoEspecificacaoFaltaAguaGeneralizada();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			Iterator raIterator = colecaoPesquisa.iterator();
			
			Object[] arrayPesquisa = (Object[]) raIterator.next();
			
			solicitacaoTipo = new SolicitacaoTipo();
			solicitacaoTipo.setId((Integer) arrayPesquisa[0]);
			
			retorno = new SolicitacaoTipoEspecificacao();
			retorno.setId((Integer) arrayPesquisa[1]);
			
			retorno.setSolicitacaoTipo(solicitacaoTipo);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 06/09/2006
	 */
	public Tramite recuperarTramiteMaisAtualPorUnidadeDestino(Integer idUnidade)
	throws ControladorException {
		
		try {
			return this.repositorioRegistroAtendimento
			.recuperarTramiteMaisAtualPorUnidadeDestino(idUnidade);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisa os fones do regsitro atendimento solicitante e joga na coleção
	 * de ClientesFones
	 * 
	 * @author Sávio Luiz
	 * @date 05/09/2006
	 * 
	 * @return idRASolicitante
	 * @throws ControladorException
	 */
	public Collection pesquisarParmsFoneRegistroAtendimentoSolicitante(
			Integer idRASolicitante) throws ControladorException {
		
		Collection colecaoParmsSolicitanteFone = null;
		try {
			colecaoParmsSolicitanteFone = repositorioRegistroAtendimento
			.pesquisarParmsFoneRegistroAtendimentoSolicitante(idRASolicitante);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		Collection colecaoClientesFone = null;
		
		if (colecaoParmsSolicitanteFone != null
				&& !colecaoParmsSolicitanteFone.isEmpty()) {
			colecaoClientesFone = new ArrayList();
			Iterator iteColecaoParmsRASolicitante = colecaoParmsSolicitanteFone
			.iterator();
			while (iteColecaoParmsRASolicitante.hasNext()) {
				Object[] parsmRASolicitante = (Object[]) iteColecaoParmsRASolicitante
				.next();
				ClienteFone clienteFone = new ClienteFone();
				
				if (parsmRASolicitante[0] != null) {
					// seta o DDD
					clienteFone.setDdd("" + (Short) parsmRASolicitante[0]);
				}
				if (parsmRASolicitante[1] != null) {
					// seta o número do telefone
					clienteFone.setTelefone((String) parsmRASolicitante[1]);
				}
				if (parsmRASolicitante[2] != null) {
					// seta o ramal do telefone
					clienteFone.setRamal((String) parsmRASolicitante[2]);
				}
				if (parsmRASolicitante[3] != null) {
					// indicador padrão
					clienteFone
					.setIndicadorTelefonePadrao((Short) parsmRASolicitante[3]);
				}
				if (parsmRASolicitante[4] != null) {
					// id fone tipo
					FoneTipo foneTipo = new FoneTipo();
					foneTipo.setId((Integer) parsmRASolicitante[4]);
					foneTipo.setDescricao((String) parsmRASolicitante[5]);
					clienteFone.setFoneTipo(foneTipo);
				}
				if (parsmRASolicitante[6] != null) {
					// ultima alteração
					clienteFone
					.setUltimaAlteracao((Date) parsmRASolicitante[6]);
				}
				
				colecaoClientesFone.add(clienteFone);
			}
		}
		return colecaoClientesFone;
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [FS0030] Verificar preenchimento dos dados de identificação do
	 * solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 07/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificaDadosSolicitanteAtualizar(Integer idCliente,
			Integer idUnidadeSolicitante, Integer idFuncionario,
			String nomeSolicitante, Collection colecaoEndereco,
			Collection colecaoFone, Short indicadorClienteEspecificacao,
			Integer idImovel, Integer idRegistroAtendimento,
			Integer idRASolicitante) throws ControladorException {
		
		// Validação de campos requeridos
		if (idCliente == null && idUnidadeSolicitante == null
				&& idFuncionario == null && nomeSolicitante == null) {
			throw new ControladorException(
			"atencao.dados_solicitante_nao_informado");
		} else {
			
			if (indicadorClienteEspecificacao != null
					&& indicadorClienteEspecificacao
					.equals(ConstantesSistema.INDICADOR_USO_ATIVO)
					&& idCliente == null) {
				throw new ControladorException("atencao.required", null,
				"Cliente");
			}
			
			if (idFuncionario != null && idUnidadeSolicitante == null) {
				throw new ControladorException("atencao.required", null,
				"Unidade Solicitante");
			}
			
			if (idFuncionario == null && idUnidadeSolicitante != null) {
				throw new ControladorException("atencao.required", null,
				"Funcionário Responsável");
			}
			
			if (idCliente == null
					&& (colecaoEndereco == null || colecaoEndereco.isEmpty())) {
				throw new ControladorException("atencao.required", null,
				"endereço");
			}
		}
		
		if (idCliente != null) {
			
			if (idRegistroAtendimento != null) {
				// [FS0012] Verificar existência do cliente solicitante
				this.verificarExistenciaClienteSolicitanteAtualizar(
						idRegistroAtendimento, idCliente, idRASolicitante);
			}
			
			/* TODO: COSANPA
			 * 
			 * Manter RA
			 * Retirada a condição do solicitante está vinculado ao imóvel. Um cliente não ligado ao imovel,
			 * pode abrir uma RA para determinado imóvel.
			 * 
			 */
			// [FS0027] Verificar informação do Imóvel
			//this.verificarInformacaoImovel(idCliente, idImovel, true);
		}
		
		if (idUnidadeSolicitante != null) {
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional
			.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID,
					idUnidadeSolicitante));
			
			filtroUnidadeOrganizacional
			.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoUnidade = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				
				throw new ControladorException("atencao.label_inexistente",
						null, "Unidade Solicitante");
				
			} else if (idRegistroAtendimento != null) {
				
				// [FS0018] Verificar existência da unidade solicitante
				this.verificarExistenciaUnidadeSolicitanteAtualizar(
						idRegistroAtendimento, idUnidadeSolicitante,
						idRASolicitante);
			}
		}
		
		if (idFuncionario != null) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));
			
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID,
					idUnidadeSolicitante));
			
			Collection colecaoFuncionario = this.getControladorUtil()
			.pesquisar(filtroFuncionario, Funcionario.class.getName());
			
			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {
				
				throw new ControladorException("atencao.label_inexistente",
						null, "Funcionário Responsável");
				
			}
		}
	}
	
	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado e o registro
	 * atendimento solicitante for diferente do que está sendo atualizado).
	 * 
	 * [FS0027] Verificar existência do cliente solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaClienteSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idCliente,
			Integer idRASolicitante) throws ControladorException {
		
		Integer qtdRegistros = null;
		
		try {
			qtdRegistros = this.repositorioRegistroAtendimento
			.verificarExistenciaClienteSolicitanteAtualizar(
					idRegistroAtendimento, idCliente, idRASolicitante);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (qtdRegistros != null && qtdRegistros > 0) {
			throw new ControladorException(
					"atencao.cliente_solicitante_ja_informado_registro_atendimento",
					null, idCliente.toString(), idRegistroAtendimento
					.toString());
		}
		
	}
	
	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada e RASO_ID<>id
	 * do Registro atendimento solicitante).
	 * 
	 * [FS0018] Verificar existência da unidade solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 07/09/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadeSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idUnidade,
			Integer idRASolicitante) throws ControladorException {
		
		Integer qtdRegistros = null;
		
		try {
			qtdRegistros = this.repositorioRegistroAtendimento
			.verificarExistenciaUnidadeSolicitanteAtualizar(
					idRegistroAtendimento, idUnidade, idRASolicitante);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (qtdRegistros != null && qtdRegistros > 0) {
			throw new ControladorException(
					"atencao.unidade_solicitante_ja_informado_registro_atendimento",
					null, idUnidade.toString(), idRegistroAtendimento
					.toString());
		}
		
	}
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param idRa
	 * @return dataPrevisaoAtual
	 * @throws ControladorException
	 */
	public Date obterDataAgenciaReguladoraPrevisaoAtual(Integer idRa)
	throws ControladorException {
		
		try {
			return this.repositorioRegistroAtendimento
			.obterDataAgenciaReguladoraPrevisaoAtual(idRa);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Consultar os registros de atendimento do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 25/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarRegistroAtendimentoImovel(Integer idImovel,
			String situacao) throws ControladorException {
		
		Collection colecaoRegistroAtendimento = null;
		try {
			colecaoRegistroAtendimento = repositorioRegistroAtendimento
			.consultarRegistroAtendimentoImovel(idImovel, situacao);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		Collection registrosAtendimentos = null;
		
		if (colecaoRegistroAtendimento != null
				&& !colecaoRegistroAtendimento.isEmpty()) {
			
			registrosAtendimentos = new ArrayList();
			
			Iterator iteColecaoRegistroAtendimento = colecaoRegistroAtendimento
			.iterator();
			
			RegistroAtendimento registroAtendimento = null;
			while (iteColecaoRegistroAtendimento.hasNext()) {
				Object[] array = (Object[]) iteColecaoRegistroAtendimento
				.next();
				
				registroAtendimento = new RegistroAtendimento();
				if (array[0] != null) {
					// seta o id
					registroAtendimento.setId((Integer) array[0]);
				}
				
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
				if (array[1] != null) {
					solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
					SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
					solicitacaoTipo.setDescricao((String) array[1]);
					
					solicitacaoTipoEspecificacao
					.setSolicitacaoTipo(solicitacaoTipo);
					// seta a descricao da solicitacao Tipo
					registroAtendimento
					.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
				}
				if (array[2] != null) {
					if (solicitacaoTipoEspecificacao == null) {
						solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
					}
					solicitacaoTipoEspecificacao
					.setDescricao((String) array[2]);
					
					// seta a descricao da solicitacao Tipo Especificacao
					registroAtendimento
					.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
				}
				if (array[3] != null) {
					// tm registro atendimento
					registroAtendimento.setRegistroAtendimento((Date) array[3]);
				}
				
				if (array[4] != null) {
					// tm encerramento
					registroAtendimento.setDataEncerramento((Date) array[4]);
				}

				if (array[5] != null) {
					// id Atendimento Motivo Encerramento
					AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
					atendimentoMotivoEncerramento.setId((Integer)array[5]);
					atendimentoMotivoEncerramento.setDescricao((String)array[6]);
					
					registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
				}
				
				registrosAtendimentos.add(registroAtendimento);
			}
		}
		return registrosAtendimentos;
		
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0025] Verifica Registro de Atendimento de Água Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoFaltaAguaGeneralizadaHelper verificarRegistroAtendimentoFaltaAguaGeneralizafa(
			Integer idEspecificacao, Integer idBairroArea)
	throws ControladorException {
		
		RegistroAtendimentoFaltaAguaGeneralizadaHelper retorno = null;
		
		// Caso esteja abrindo um registro de atendimento de falta de Água
		// generalizada
		SolicitacaoTipoEspecificacao especificacaoFaltaAguaGeneralizada = this
		.pesquisarTipoEspecificacaoFaltaAguaGeneralizada();
		
		if (especificacaoFaltaAguaGeneralizada != null
				&& especificacaoFaltaAguaGeneralizada.getId().equals(
						idEspecificacao) && idBairroArea != null) {
			
			Collection colecaoRAFaltaAgua = null;
			
			try {
				colecaoRAFaltaAgua = repositorioRegistroAtendimento
				.pesquisarRAFaltaAguaGeneralizada(idBairroArea);
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (colecaoRAFaltaAgua != null && !colecaoRAFaltaAgua.isEmpty()) {
				
				retorno = new RegistroAtendimentoFaltaAguaGeneralizadaHelper();
				
				Collection colecaoRegistroAtendimentoRetorno = colecaoRegistroAtendimentoRetorno = new ArrayList();
				
				RegistroAtendimento ra = null;
				
				Iterator raIterator = colecaoRAFaltaAgua.iterator();
				
				Object[] arrayRA = null;
				
				boolean primeiroRegistro = true;
				
				while (raIterator.hasNext()) {
					
					ra = new RegistroAtendimento();
					
					arrayRA = (Object[]) raIterator.next();
					
					// endereço e SolicitacaoTipoEspecificacao
					if (primeiroRegistro) {
						
						SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
						solicitacaoTipo.setId((Integer) arrayRA[25]);
						solicitacaoTipo.setDescricao("" + arrayRA[26]);
						
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
						solicitacaoTipoEspecificacao
						.setId((Integer) arrayRA[24]);
						solicitacaoTipoEspecificacao
						.setDescricao((String) arrayRA[9]);
						
						solicitacaoTipoEspecificacao
						.setSolicitacaoTipo(solicitacaoTipo);
						
						Bairro bairro = new Bairro();
						bairro.setCodigo(((Integer) arrayRA[32]).intValue());
						bairro.setNome((String) arrayRA[33]);
						
						BairroArea bairroArea = new BairroArea();
						bairroArea.setId((Integer) arrayRA[30]);
						bairroArea.setNome((String) arrayRA[31]);
						bairroArea.setBairro(bairro);
						
						// Carregando o Área do bairro e a Especificação no
						// objeto de retorno
						retorno
						.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
						retorno.setBairroArea(bairroArea);
						
						primeiroRegistro = false;
					}
					
					if (arrayRA[20] != null && arrayRA[21] != null) {
						
						LogradouroCep logradouroCep = null;
						if (arrayRA[20] != null) {
							
							logradouroCep = new LogradouroCep();
							logradouroCep.setId((Integer) arrayRA[20]);
							
							// id do Logradouro
							Logradouro logradouro = null;
							if (arrayRA[19] != null) {
								logradouro = new Logradouro();
								logradouro.setId((Integer) arrayRA[19]);
								logradouro.setNome("" + arrayRA[0]);
							}
							LogradouroTipo logradouroTipo = null;
							// Descrição logradouro tipo
							if (arrayRA[22] != null) {
								logradouroTipo = new LogradouroTipo();
								logradouroTipo.setDescricaoAbreviada(""
										+ arrayRA[22]);
								logradouro.setLogradouroTipo(logradouroTipo);
							}
							LogradouroTitulo logradouroTitulo = null;
							// Descrição logradouro titulo
							if (arrayRA[23] != null) {
								logradouroTitulo = new LogradouroTitulo();
								logradouroTitulo.setDescricaoAbreviada(""
										+ arrayRA[23]);
								logradouro
								.setLogradouroTitulo(logradouroTitulo);
							}
							// id do CEP
							Cep cep = null;
							if (arrayRA[10] != null) {
								cep = new Cep();
								cep.setCepId((Integer) arrayRA[10]);
								cep.setCodigo((Integer) arrayRA[16]);
							}
							
							logradouroCep.setLogradouro(logradouro);
							logradouroCep.setCep(cep);
						}
						
						ra.setLogradouroCep(logradouroCep);
						
						LogradouroBairro logradouroBairro = null;
						if (arrayRA[21] != null) {
							
							logradouroBairro = new LogradouroBairro();
							logradouroBairro.setId((Integer) arrayRA[21]);
							
							Bairro bairro = null;
							// nome bairro
							if (arrayRA[3] != null) {
								bairro = new Bairro();
								bairro.setId((Integer) arrayRA[3]);
								bairro.setCodigo((Integer) arrayRA[17]);
								bairro.setNome("" + arrayRA[4]);
							}
							
							Municipio municipio = null;
							// nome municipio
							if (arrayRA[5] != null) {
								municipio = new Municipio();
								municipio.setId((Integer) arrayRA[5]);
								municipio.setNome("" + arrayRA[6]);
								
								// id da unidade federação
								if (arrayRA[7] != null) {
									UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
									unidadeFederacao
									.setId((Integer) arrayRA[7]);
									unidadeFederacao.setSigla("" + arrayRA[8]);
									municipio
									.setUnidadeFederacao(unidadeFederacao);
								}
								
								bairro.setMunicipio(municipio);
							}
							
							logradouroBairro.setBairro(bairro);
						}
						
						ra.setLogradouroBairro(logradouroBairro);
					}
					
					// Id
					ra.setId((Integer) arrayRA[27]);
					
					// complemento endereço
					if (arrayRA[18] != null) {
						ra.setComplementoEndereco("" + arrayRA[18]);
					}
					
					// pontoReferencia
					if (arrayRA[28] != null) {
						ra.setPontoReferencia("" + arrayRA[28]);
					}
					
					// Data do RegistroAtendimento
					if (arrayRA[29] != null) {
						ra.setRegistroAtendimento((Date) arrayRA[29]);
					}
					
					// id do Imóvel
					if (arrayRA[34] != null) {
						Imovel imovel = this.getControladorEndereco()
						.pesquisarImovelParaEndereco(
								(Integer) arrayRA[34]);
						ra.setImovel(imovel);
					}
					
					colecaoRegistroAtendimentoRetorno.add(ra);
				}
				
				retorno
				.setColecaoRegistroAtendimento(colecaoRegistroAtendimentoRetorno);
			}
		}
		
		return retorno;
	}
	
	/**
	 * Faz o controle de concorrencia de registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @throws ControladorException
	 */
	private void verificarRegistroAtendimentoControleConcorrencia(
			RegistroAtendimento registroAtendimento)
	throws ControladorException {
		
		FiltroRegistroAtendimento filtroRA = new FiltroRegistroAtendimento();
		filtroRA.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.ID, registroAtendimento.getId()));
		
		Collection colecaoRA = getControladorUtil().pesquisar(filtroRA,
				RegistroAtendimento.class.getName());
		
		if (colecaoRA == null || colecaoRA.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		
		RegistroAtendimento raAtual = (RegistroAtendimento) Util
		.retonarObjetoDeColecao(colecaoRA);
		
		if (raAtual.getUltimaAlteracao().after(
				registroAtendimento.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0015] Verifica Registro de Atendimento Encerrado para o Local da
	 * ocorrência
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoEncerradoLocalOcorrenciaHelper verificarRegistroAtendimentoEncerradoLocalOcorrencia(
			Integer idImovel, Integer idEspecificacao,
			Integer idLogradouroBairro, Integer idLogradouroCep)
	throws ControladorException {
		
		RegistroAtendimentoEncerradoLocalOcorrenciaHelper retorno = null;
		
		SistemaParametro sistemaParametro = this.getControladorUtil()
		.pesquisarParametrosDoSistema();
		
		Date dataReativacao = new Date();
		dataReativacao = Util.subtrairNumeroDiasDeUmaData(dataReativacao,
				sistemaParametro.getDiasReativacao().intValue());
		
		Collection colecaoRAEncerrado = null;
		
		if (idImovel != null) {
			
			try {
				colecaoRAEncerrado = repositorioRegistroAtendimento
				.pesquisarRAEncerradoImovel(idImovel, idEspecificacao,
						dataReativacao);
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (colecaoRAEncerrado != null && !colecaoRAEncerrado.isEmpty()) {
				
				retorno = new RegistroAtendimentoEncerradoLocalOcorrenciaHelper();
				
				Collection colecaoRegistroAtendimentoRetorno = colecaoRegistroAtendimentoRetorno = new ArrayList();
				
				RegistroAtendimento ra = null;
				Imovel objetoImovel = null;
				
				Iterator raIterator = colecaoRAEncerrado.iterator();
				
				Object[] arrayRA = null;
				
				boolean primeiroRegistro = true;
				
				while (raIterator.hasNext()) {
					
					ra = new RegistroAtendimento();
					objetoImovel = new Imovel();
					
					arrayRA = (Object[]) raIterator.next();
					
					// Imovel
					objetoImovel.setId((Integer) arrayRA[30]);
					
					// SolicitacaoTipoEspecificacao
					if (primeiroRegistro) {
						
						SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
						solicitacaoTipo.setId((Integer) arrayRA[25]);
						solicitacaoTipo.setDescricao("" + arrayRA[26]);
						
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
						solicitacaoTipoEspecificacao
						.setId((Integer) arrayRA[24]);
						solicitacaoTipoEspecificacao
						.setDescricao((String) arrayRA[9]);
						
						solicitacaoTipoEspecificacao
						.setSolicitacaoTipo(solicitacaoTipo);
						
						// Especificação
						retorno
						.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
						
						if (arrayRA[20] != null && arrayRA[21] != null) {
							
							LogradouroCep logradouroCep = null;
							if (arrayRA[20] != null) {
								
								logradouroCep = new LogradouroCep();
								logradouroCep.setId((Integer) arrayRA[20]);
								
								// id do Logradouro
								Logradouro logradouro = null;
								if (arrayRA[19] != null) {
									logradouro = new Logradouro();
									logradouro.setId((Integer) arrayRA[19]);
									logradouro.setNome("" + arrayRA[0]);
								}
								LogradouroTipo logradouroTipo = null;
								// Descrição logradouro tipo
								if (arrayRA[22] != null) {
									logradouroTipo = new LogradouroTipo();
									logradouroTipo.setDescricaoAbreviada(""
											+ arrayRA[22]);
									logradouro
									.setLogradouroTipo(logradouroTipo);
								}
								LogradouroTitulo logradouroTitulo = null;
								// Descrição logradouro titulo
								if (arrayRA[23] != null) {
									logradouroTitulo = new LogradouroTitulo();
									logradouroTitulo.setDescricaoAbreviada(""
											+ arrayRA[23]);
									logradouro
									.setLogradouroTitulo(logradouroTitulo);
								}
								// id do CEP
								Cep cep = null;
								if (arrayRA[10] != null) {
									cep = new Cep();
									cep.setCepId((Integer) arrayRA[10]);
									cep.setCodigo((Integer) arrayRA[16]);
								}
								
								logradouroCep.setLogradouro(logradouro);
								logradouroCep.setCep(cep);
							}
							
							objetoImovel.setLogradouroCep(logradouroCep);
							
							LogradouroBairro logradouroBairro = null;
							if (arrayRA[21] != null) {
								
								logradouroBairro = new LogradouroBairro();
								logradouroBairro.setId((Integer) arrayRA[21]);
								
								Bairro bairro = null;
								// nome bairro
								if (arrayRA[3] != null) {
									bairro = new Bairro();
									bairro.setId((Integer) arrayRA[3]);
									bairro.setCodigo((Integer) arrayRA[17]);
									bairro.setNome("" + arrayRA[4]);
								}
								
								Municipio municipio = null;
								// nome municipio
								if (arrayRA[5] != null) {
									municipio = new Municipio();
									municipio.setId((Integer) arrayRA[5]);
									municipio.setNome("" + arrayRA[6]);
									
									// id da unidade federação
									if (arrayRA[7] != null) {
										UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
										unidadeFederacao
										.setId((Integer) arrayRA[7]);
										unidadeFederacao.setSigla(""
												+ arrayRA[8]);
										municipio
										.setUnidadeFederacao(unidadeFederacao);
									}
									
									bairro.setMunicipio(municipio);
								}
								
								logradouroBairro.setBairro(bairro);
							}
							
							objetoImovel.setLogradouroBairro(logradouroBairro);
						}
						
						Localidade localidade = new Localidade();
						SetorComercial setorComercial = new SetorComercial();
						Quadra quadra = new Quadra();
						
						localidade.setId((Integer) arrayRA[31]);
						setorComercial.setCodigo(((Integer) arrayRA[32])
								.intValue());
						quadra.setNumeroQuadra(((Integer) arrayRA[33])
								.intValue());
						
						objetoImovel.setLocalidade(localidade);
						objetoImovel.setSetorComercial(setorComercial);
						objetoImovel.setQuadra(quadra);
						objetoImovel
						.setLote(((Short) arrayRA[34]).shortValue());
						objetoImovel.setSubLote(((Short) arrayRA[35])
								.shortValue());
						
						objetoImovel.setNumeroImovel((String) arrayRA[39]);
						
						if (arrayRA[40] != null) {
							objetoImovel
							.setComplementoEndereco((String) arrayRA[40]);
						}
						
						retorno.setImovel(objetoImovel);
						
						primeiroRegistro = false;
					}
					
					// Id
					ra.setId((Integer) arrayRA[27]);
					
					// complemento endereço
					if (arrayRA[18] != null) {
						ra.setComplementoEndereco("" + arrayRA[18]);
					}
					
					// pontoReferencia
					if (arrayRA[28] != null) {
						ra.setPontoReferencia("" + arrayRA[28]);
					}
					
					// Data do RegistroAtendimento
					if (arrayRA[29] != null) {
						ra.setRegistroAtendimento((Date) arrayRA[29]);
					}
					
					// Data do Encerramento
					if (arrayRA[36] != null) {
						ra.setDataEncerramento((Date) arrayRA[36]);
					}
					
					// Atendimento Motivo Encerramento
					if (arrayRA[37] != null) {
						AtendimentoMotivoEncerramento motivoEncerramento = new AtendimentoMotivoEncerramento();
						motivoEncerramento.setId((Integer) arrayRA[37]);
						motivoEncerramento.setDescricao((String) arrayRA[38]);
						ra.setAtendimentoMotivoEncerramento(motivoEncerramento);
					}
					
					colecaoRegistroAtendimentoRetorno.add(ra);
				}
				
				retorno
				.setColecaoRegistroAtendimento(colecaoRegistroAtendimentoRetorno);
			}
		} else if (idImovel == null && idLogradouroBairro != null
				&& idLogradouroCep != null) {
			
			try {
				colecaoRAEncerrado = repositorioRegistroAtendimento
				.pesquisarRAEncerradoLocalOcorrencia(
						idLogradouroBairro, idLogradouroCep,
						idEspecificacao, dataReativacao);
				
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (colecaoRAEncerrado != null && !colecaoRAEncerrado.isEmpty()) {
				
				retorno = new RegistroAtendimentoEncerradoLocalOcorrenciaHelper();
				
				Collection colecaoRegistroAtendimentoRetorno = colecaoRegistroAtendimentoRetorno = new ArrayList();
				
				RegistroAtendimento ra = null;
				
				Iterator raIterator = colecaoRAEncerrado.iterator();
				
				Object[] arrayRA = null;
				
				boolean primeiroRegistro = true;
				
				while (raIterator.hasNext()) {
					
					ra = new RegistroAtendimento();
					
					arrayRA = (Object[]) raIterator.next();
					
					// complemento endereço
					if (arrayRA[18] != null) {
						ra.setComplementoEndereco("" + arrayRA[18]);
					}
					
					// SolicitacaoTipoEspecificacao
					if (primeiroRegistro) {
						
						SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
						solicitacaoTipo.setId((Integer) arrayRA[25]);
						solicitacaoTipo.setDescricao("" + arrayRA[26]);
						
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
						solicitacaoTipoEspecificacao
						.setId((Integer) arrayRA[24]);
						solicitacaoTipoEspecificacao
						.setDescricao((String) arrayRA[9]);
						
						solicitacaoTipoEspecificacao
						.setSolicitacaoTipo(solicitacaoTipo);
						
						// Especificação
						retorno
						.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
						
						if (arrayRA[20] != null && arrayRA[21] != null) {
							
							LogradouroCep logradouroCep = null;
							if (arrayRA[20] != null) {
								
								logradouroCep = new LogradouroCep();
								logradouroCep.setId((Integer) arrayRA[20]);
								
								// id do Logradouro
								Logradouro logradouro = null;
								if (arrayRA[19] != null) {
									logradouro = new Logradouro();
									logradouro.setId((Integer) arrayRA[19]);
									logradouro.setNome("" + arrayRA[0]);
								}
								LogradouroTipo logradouroTipo = null;
								// Descrição logradouro tipo
								if (arrayRA[22] != null) {
									logradouroTipo = new LogradouroTipo();
									logradouroTipo.setDescricaoAbreviada(""
											+ arrayRA[22]);
									logradouro
									.setLogradouroTipo(logradouroTipo);
								}
								LogradouroTitulo logradouroTitulo = null;
								// Descrição logradouro titulo
								if (arrayRA[23] != null) {
									logradouroTitulo = new LogradouroTitulo();
									logradouroTitulo.setDescricaoAbreviada(""
											+ arrayRA[23]);
									logradouro
									.setLogradouroTitulo(logradouroTitulo);
								}
								// id do CEP
								Cep cep = null;
								if (arrayRA[10] != null) {
									cep = new Cep();
									cep.setCepId((Integer) arrayRA[10]);
									cep.setCodigo((Integer) arrayRA[16]);
								}
								
								logradouroCep.setLogradouro(logradouro);
								logradouroCep.setCep(cep);
							}
							
							ra.setLogradouroCep(logradouroCep);
							
							LogradouroBairro logradouroBairro = null;
							if (arrayRA[21] != null) {
								
								logradouroBairro = new LogradouroBairro();
								logradouroBairro.setId((Integer) arrayRA[21]);
								
								Bairro bairro = null;
								// nome bairro
								if (arrayRA[3] != null) {
									bairro = new Bairro();
									bairro.setId((Integer) arrayRA[3]);
									bairro.setCodigo((Integer) arrayRA[17]);
									bairro.setNome("" + arrayRA[4]);
								}
								
								Municipio municipio = null;
								// nome municipio
								if (arrayRA[5] != null) {
									municipio = new Municipio();
									municipio.setId((Integer) arrayRA[5]);
									municipio.setNome("" + arrayRA[6]);
									
									// id da unidade federação
									if (arrayRA[7] != null) {
										UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
										unidadeFederacao
										.setId((Integer) arrayRA[7]);
										unidadeFederacao.setSigla(""
												+ arrayRA[8]);
										municipio
										.setUnidadeFederacao(unidadeFederacao);
									}
									
									bairro.setMunicipio(municipio);
								}
								
								logradouroBairro.setBairro(bairro);
							}
							
							ra.setLogradouroBairro(logradouroBairro);
						}
						
						retorno.setEnderecoLocalOcorrencia(ra
								.getEnderecoFormatadoAbreviado());
						
						primeiroRegistro = false;
					}
					
					// Id
					ra.setId((Integer) arrayRA[27]);
					
					// pontoReferencia
					if (arrayRA[28] != null) {
						ra.setPontoReferencia("" + arrayRA[28]);
					}
					
					// Data do RegistroAtendimento
					if (arrayRA[29] != null) {
						ra.setRegistroAtendimento((Date) arrayRA[29]);
					}
					
					// Data do Encerramento
					if (arrayRA[30] != null) {
						ra.setDataEncerramento((Date) arrayRA[30]);
					}
					
					// Atendimento Motivo Encerramento
					if (arrayRA[31] != null) {
						AtendimentoMotivoEncerramento motivoEncerramento = new AtendimentoMotivoEncerramento();
						motivoEncerramento.setId((Integer) arrayRA[31]);
						motivoEncerramento.setDescricao((String) arrayRA[32]);
						ra.setAtendimentoMotivoEncerramento(motivoEncerramento);
					}
					
					colecaoRegistroAtendimentoRetorno.add(ra);
				}
				
				retorno
				.setColecaoRegistroAtendimento(colecaoRegistroAtendimentoRetorno);
				
			}
		}
		
		return retorno;
		
	}
	
	/**
	 * Pesquisa os dados do Registro Atendimento Solicitante para o Relatório de
	 * OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(
			Integer idRegistroAtendimento) throws ControladorException {
		
		Object[] dadosSolicitante = null;
		
		try {
			
			dadosSolicitante = this.repositorioRegistroAtendimento
			.pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(idRegistroAtendimento);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return dadosSolicitante;
		
	}
	
	/**
	 * [UC0458] - Imprimir Ordem de Serviço
	 * 
	 * Pesquisa o telefone principal do Registro Atendimento Solicitante para o
	 * Relatório de OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	
	public String pesquisarSolicitanteFonePrincipal(
			Integer idRegistroAtendimento) throws ControladorException {
		
		Object[] dadosSolicitanteFone = null;
		String telefoneFormatado = "";
		
		try {
			
			dadosSolicitanteFone = this.repositorioRegistroAtendimento
			.pesquisarSolicitanteFonePrincipal(idRegistroAtendimento);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosSolicitanteFone != null) {
			
			// DDD
			if (dadosSolicitanteFone[0] != null) {
				telefoneFormatado = telefoneFormatado + "("
				+ dadosSolicitanteFone[0].toString() + ")";
			}
			
			// número do Telefone
			if (dadosSolicitanteFone[1] != null) {
				telefoneFormatado = telefoneFormatado + dadosSolicitanteFone[1];
			}
			
			// Ramal
			if (dadosSolicitanteFone[2] != null) {
				telefoneFormatado = telefoneFormatado + "/"
				+ dadosSolicitanteFone[2];
			}
			
		}
		
		return telefoneFormatado;
		
	}
	
	/**
	 * 
	 * Pesquisar o endereço descritivo do RA a partir do seu id
	 * 
	 * [UC0482] - Obter endereço Abreviado da ocorrência do RA
	 * 
	 * @author Rafael Corrêa
	 * @date 18/10/2006
	 * 
	 * @param idRA
	 * @return String
	 * @throws ControladorException
	 */
	
	public String obterEnderecoDescritivoRA(Integer idRA)
	throws ControladorException {
		Object[] dadosEndereco = null;
		try {
			dadosEndereco = repositorioRegistroAtendimento
			.obterEnderecoDescritivoRA(idRA);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		String endereco = "";
		
		if (dadosEndereco != null) {
			
			// Decrição endereço
			if (dadosEndereco[0] != null) {
				endereco = endereco + dadosEndereco[0].toString().trim();
			}
			
			// Nome do Bairro
			if (dadosEndereco[1] != null) {
				endereco = endereco + " - "
				+ dadosEndereco[1].toString().trim();
			}
			
		}
		
		return endereco;
	}
	
	/**
	 * [UC0482] - Obter endereço Abreviado da ocorrência do RA
	 * 
	 * Pesquisa o endereço abreviado da ocorrência do RA
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	
	public String obterEnderecoAbreviadoOcorrenciaRA(Integer idRA)
	throws ControladorException {
		
		String endereco = "";
		Object[] dadosRA = null;
		
		String enderecoDescritivo = this.obterEnderecoDescritivoRA(idRA);
		if (enderecoDescritivo != null && !enderecoDescritivo.equals("")) {
			endereco = enderecoDescritivo;
		} else {
			
			try {
				dadosRA = this.repositorioRegistroAtendimento
				.obterLogradouroBairroImovelRegistroAtendimento(idRA);
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (dadosRA != null) {
				
				// Id do Logradouro Bairro
				// Caso o Logradouro Bairro seja diferente de nulo pesquisa o
				// endereco pelo RA
				if (dadosRA[0] != null) {
					endereco = getControladorEndereco()
					.obterEnderecoAbreviadoRA(idRA);
				}
				// Id do Imóvel
				// Caso o Logradouro Bairro for nulo e o Imóvel não for pesquisa
				// o
				// endereço do Imóvel
				else if (dadosRA[1] != null) {
					Integer idImovel = (Integer) dadosRA[1];
					endereco = getControladorEndereco()
					.obterEnderecoAbreviadoImovel(idImovel);
				}
				
			}
			
		}
		
		return endereco;
		
	}
	
	
	/**
	 * Pesquisar registro de atendimento para o imóvel 
	 *
	 * @author Raphael Rossiter
	 * @date 21/07/2008
	 *
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarRegistroAtendimento(Integer idImovel)
	throws ControladorException {
		
		 //= null;
		
		// filtro Registro Atendimento
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		
		filtroRegistroAtendimento
		.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
		filtroRegistroAtendimento
		.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFIC);
		
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.IMOVEL_ID, idImovel));
		
		// Alterado no dia 20/11/2006, pode determinação de Aryed e Newton
		filtroRegistroAtendimento.adicionarParametro(new ParametroNulo(
				FiltroRegistroAtendimento.ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO));
		
		filtroRegistroAtendimento.adicionarParametro(new ParametroNaoNulo(
				FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO));
		
		Collection colecaoRegistroAtendimento = getControladorUtil().pesquisar(
				filtroRegistroAtendimento, RegistroAtendimento.class.getName());
		
		
		return colecaoRegistroAtendimento;
	}
	
	
	/**
	 * Validar o registro de atendimento para ação solicitada pelo usuário
	 *
	 * @author Raphael Rossiter
	 * @date 21/07/2008
	 *
	 * @param colecaoRegistroAtendimento
	 * @param codigoConstante
	 * @return RegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento validarRegistroAtendimento(Collection colecaoRegistroAtendimento, char codigoConstante)
	throws ControladorException {
		
		RegistroAtendimento retorno = null;
		RegistroAtendimento registroAtendimento = null;
		
		Iterator iteratorColecaoRegistroAtendimento = 
			colecaoRegistroAtendimento.iterator();
		
		while (iteratorColecaoRegistroAtendimento.hasNext()) {
			
			registroAtendimento = (RegistroAtendimento) 
			iteratorColecaoRegistroAtendimento.next();
			
			// Alterado no dia 20/11/2006, pode determinação de Aryed e
			// Newton
			/*
			 * if (registroAtendimento.getAtendimentoMotivoEncerramento() !=
			 * null) { sessionContext.setRollbackOnly(); throw new
			 * ControladorException(mensagemErro, null, idImovel
			 * .toString()); }
			 */
			
			// Alterado no dia 20/11/2006, pode determinação de Aryed e
			// Newton
			// verifica se existe atendimento motivo para o encerramento
			/*
			 * if (registroAtendimento.getAtendimentoMotivoEncerramento()
			 * .getIndicadorExecucao() != 1) {
			 * sessionContext.setRollbackOnly(); throw new
			 * ControladorException(mensagemErro, null, idImovel
			 * .toString()); }
			 */
			
			FiltroEspecificacaoTipoValidacao filtroEspecificacaoTipoValidacao = new FiltroEspecificacaoTipoValidacao();
			
			filtroEspecificacaoTipoValidacao.adicionarParametro(new ParametroSimples(
					FiltroEspecificacaoTipoValidacao.ID_SOLICITACAO_TIPO_ESPECIFICACAO_ID,
					registroAtendimento.getSolicitacaoTipoEspecificacao().getId()));
			
			filtroEspecificacaoTipoValidacao.adicionarParametro(new ParametroSimples(
					FiltroEspecificacaoTipoValidacao.CODIGO_CONSTANTE, codigoConstante));
			
			Collection colecaoEspecificacaoTipoValidacao = getControladorUtil()
			.pesquisar(filtroEspecificacaoTipoValidacao, EspecificacaoTipoValidacao.class.getName());
			
			if (colecaoEspecificacaoTipoValidacao != null && !colecaoEspecificacaoTipoValidacao.isEmpty()) {
				
				retorno = registroAtendimento;
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Caso não exista para o Imóvel RA encerrada por execução com especificação
	 * da solicitação
	 * 
	 * @author Rafael Santos,Rafael Santos, Raphael Rossiter
	 * @since 26/10/2006,20/11/2006, 17/06/2008
	 * 
	 */
	public RegistroAtendimento verificarExistenciaRegistroAtendimento(Integer idImovel,
			String mensagemErro, char codigoConstante)
	throws ControladorException {
		
		RegistroAtendimento registroAtendimento = null;
		
		//Pesquisando os registros relacionados ao imóvel
		Collection colecaoRegistroAtendimento = this.pesquisarRegistroAtendimento(idImovel);
		
		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			
			//Verificando a existência de algum registro de atendimento para ação solicitada pelo usuário.
			registroAtendimento = this.validarRegistroAtendimento(colecaoRegistroAtendimento, codigoConstante);
			
			if (registroAtendimento == null) {
				
				throw new ControladorException(mensagemErro, null, idImovel.toString());
			}
		} 
		else {
			
			throw new ControladorException(mensagemErro, null, idImovel.toString());
		}
		
		return registroAtendimento;
	}
	
	/**
	 * Verificar existência de registro de atendimento para o imóvel sem levantar exceção
	 *
	 * @author Raphael Rossiter
	 * @date 21/07/2008
	 *
	 * @param idImovel
	 * @param mensagemErro
	 * @param codigoConstante
	 * @return RegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarExistenciaRegistroAtendimentoSemLevantarExcecao(Integer idImovel,
			char codigoConstante) throws ControladorException {
		
		RegistroAtendimento registroAtendimento = null;
		
		//Pesquisando os registros relacionados ao imóvel
		Collection colecaoRegistroAtendimento = this.pesquisarRegistroAtendimento(idImovel);
		
		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			
			//Verificando a existência de algum registro de atendimento para ação solicitada pelo usuário.
			registroAtendimento = this.validarRegistroAtendimento(colecaoRegistroAtendimento, codigoConstante);
		} 
		
		
		return registroAtendimento;
	}
	
	/**
	 * Caso não exista para o Imóvel RA encerrada por execução com especificação
	 * da solicitação No caso de Tarifa Social
	 * 
	 * @author Rafael Santos
	 * @since 26/10/2006
	 * 
	 */
	public void verificarExistenciaRegistroAtendimentoTarifaSocial(
			Integer idImovel, String mensagemErro) throws ControladorException {
		
		// filtro Registro Atendimento
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.IMOVEL_ID, idImovel));
		filtroRegistroAtendimento
		.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
		filtroRegistroAtendimento
		.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO);
		
		Collection colecaoRegistroAtendimento = getControladorUtil().pesquisar(
				filtroRegistroAtendimento, RegistroAtendimento.class.getName());
		
		// validar para saber se existe RA para o imvel
		RegistroAtendimento registroAtendimento = null;
		if (colecaoRegistroAtendimento != null
				&& !colecaoRegistroAtendimento.isEmpty()) {
			registroAtendimento = (RegistroAtendimento) colecaoRegistroAtendimento
			.iterator().next();
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException(mensagemErro, null, idImovel
					.toString());
			
		}
		
		// verifica se existe atendimento motivo para o encerramento
		if (registroAtendimento.getAtendimentoMotivoEncerramento() == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(mensagemErro, null, idImovel
					.toString());
		}
		
		// verifica se existe atendimento motivo para o encerramento
		if (registroAtendimento.getAtendimentoMotivoEncerramento()
				.getIndicadorExecucao() != 1) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(mensagemErro, null, idImovel
					.toString());
		}
		
		if (registroAtendimento.getSolicitacaoTipoEspecificacao() != null) {
			
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao
			.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.ID,
					registroAtendimento
					.getSolicitacaoTipoEspecificacao().getId()));
			filtroSolicitacaoTipoEspecificacao
			.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO);
			
			Collection colecaoSolicitacaoTipoEspecificacao = getControladorUtil()
			.pesquisar(filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
			if (colecaoSolicitacaoTipoEspecificacao != null
					&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
				solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacao
				.iterator().next();
				if (solicitacaoTipoEspecificacao.getSolicitacaoTipo() == null
						|| solicitacaoTipoEspecificacao.getSolicitacaoTipo()
						.getIndicadorTarifaSocial() != 1) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(mensagemErro, null, idImovel
							.toString());
				}
			} else {
				sessionContext.setRollbackOnly();
				throw new ControladorException(mensagemErro, null, idImovel
						.toString());
			}
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException(mensagemErro, null, idImovel
					.toString());
		}
	}
	
	/**
	 * [UC0494] Gerar Numeração de RA Manual
	 * 
	 * @author Raphael Rossiter
	 * @date 06/11/2006
	 * 
	 * @throws ControladorException
	 */
	public GerarNumeracaoRAManualHelper GerarNumeracaoRAManual(
			Integer quantidade, Integer idUnidadeOrganizacional)
	throws ControladorException {
		
		GerarNumeracaoRAManualHelper retorno = new GerarNumeracaoRAManualHelper();
		
		// [FS001] - Validar Quantidade Informada
		if (quantidade > ConstantesSistema.LIMITE_QUANTIDADE_GERACAO_MANUAL) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_atendimento_geracao_manual_limite_quantidade_superior",
					null, ConstantesSistema.LIMITE_QUANTIDADE_GERACAO_MANUAL
					.toString());
		}
		
		// [FS002] - Verificar existência da Unidade Organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));
		
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoUnidade = this.getControladorUtil().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.pesquisa_inexistente",
					null, "Unidade Organizacional");
			
		}
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
		.retonarObjetoDeColecao(colecaoUnidade);
		
		retorno.setIdUnidadeOrganizacional(unidadeOrganizacional.getId());
		retorno.setDescricaoUnidadeOrganizacional(unidadeOrganizacional
				.getDescricao());
		
		// Numeração
		SistemaParametro sistemaParametro = this.getControladorUtil()
		.pesquisarParametrosDoSistema();
		
		int sequencialInteiro = sistemaParametro.getUltimoRAManual().intValue() + 1;
		String sequencialString = "";
		
		Collection<String> colecaoNumeracao = new ArrayList();
		String ultimaNumeracaoGerada = null;
		
		for (int x = 0; x < quantidade; x++) {
			
			sequencialString = String.valueOf(sequencialInteiro);
			int digitoModulo11 = Util.obterDigitoVerificadorModulo11(Long
					.parseLong(sequencialString));
			
			if (sequencialString.length() < 9) {
				
				int complementoZeros = 9 - sequencialString.length();
				String sequencialStringFinal = sequencialString;
				
				for (int y = 0; y < complementoZeros; y++) {
					sequencialStringFinal = "0" + sequencialStringFinal;
				}
				
				colecaoNumeracao.add(sequencialStringFinal.trim() + "-"
						+ digitoModulo11);
				ultimaNumeracaoGerada = sequencialStringFinal.trim();
			} else {
				
				colecaoNumeracao.add(sequencialString.trim() + "-"
						+ digitoModulo11);
				ultimaNumeracaoGerada = sequencialString.trim();
			}
			
			sequencialInteiro++;
		}
		
		retorno.setColecaoNumeracao(colecaoNumeracao);
		
		// Atualizando o último número gerado
		sistemaParametro
		.setUltimoRAManual(new Integer(ultimaNumeracaoGerada));
		
		this.getControladorUtil().atualizarSistemaParametro(sistemaParametro);
		
		return retorno;
		
	}
	
	/**
	 * [UC0404] - Manter Especificação da Situação do Imóvel
	 * 
	 * [SB0001] Atualizar Critério de Cobrança
	 * 
	 * @author Rafael Pinto
	 * @created 09/11/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarEspecificacaoSituacaoImovel(
			EspecificacaoImovelSituacao especificacaoImovelSituacao,
			Collection colecaoEspecificacaoCriterios,
			Collection colecaoEspecificacaoCriteriosRemovidas, Usuario usuario)
	throws ControladorException {
		
		EspecificacaoImovelSituacao especificacaoImovelSituacaoBase = null;
		
		FiltroEspecificacaoImovelSituacao filtro = new FiltroEspecificacaoImovelSituacao();
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroEspecificacaoImovelSituacao.ID,
				especificacaoImovelSituacao.getId()));
		
		Collection<CobrancaCriterio> collectionEspecificacao = this
		.getControladorUtil().pesquisar(filtro,
				EspecificacaoImovelSituacao.class.getName());
		
		if (collectionEspecificacao != null
				&& !collectionEspecificacao.isEmpty()) {
			
			especificacaoImovelSituacaoBase = (EspecificacaoImovelSituacao) Util
			.retonarObjetoDeColecao(collectionEspecificacao);
		}
		
		// Verificar se o logradouro já foi atualizado por outro usuário
		// durante
		// esta atualização
		if (especificacaoImovelSituacaoBase == null
				|| especificacaoImovelSituacaoBase.getUltimaAlteracao().after(
						especificacaoImovelSituacao.getUltimaAlteracao())) {
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		
		// [UC0107] - Registrar Transação
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ESPECIFICACAO_SITUACAO_IMOVEL_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		Operacao operacao = new Operacao();
		operacao
		.setId(Operacao.OPERACAO_ESPECIFICACAO_SITUACAO_IMOVEL_ATUALIZAR);
		
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		
		especificacaoImovelSituacao.setOperacaoEfetuada(operacaoEfetuada);
		especificacaoImovelSituacao.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		
		registradorOperacao.registrarOperacao(especificacaoImovelSituacao);
		
		especificacaoImovelSituacao.setUltimaAlteracao(new Date());
		
		this.getControladorUtil().atualizar(especificacaoImovelSituacao);
		
		Iterator itera = colecaoEspecificacaoCriterios.iterator();
		
		while (itera.hasNext()) {
			
			EspecificacaoImovSitCriterio especificacaoImovSitCriterio = (EspecificacaoImovSitCriterio) itera
			.next();
			
			especificacaoImovSitCriterio
			.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
			
			// [UC0107] - Registrar Transação
			especificacaoImovSitCriterio.setOperacaoEfetuada(operacaoEfetuada);
			especificacaoImovSitCriterio.adicionarUsuario(usuario,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			
			registradorOperacao.registrarOperacao(especificacaoImovSitCriterio);
			
			especificacaoImovSitCriterio.setUltimaAlteracao(new Date());
			
			this.getControladorUtil().inserirOuAtualizar(
					especificacaoImovSitCriterio);
		}
		
		if (colecaoEspecificacaoCriteriosRemovidas != null
				&& !colecaoEspecificacaoCriteriosRemovidas.isEmpty()) {
			
			Iterator iter = colecaoEspecificacaoCriteriosRemovidas.iterator();
			
			while (iter.hasNext()) {
				
				EspecificacaoImovSitCriterio especificacaoImovSitCriterio = (EspecificacaoImovSitCriterio) iter
				.next();
				
				this.getControladorUtil().remover(especificacaoImovSitCriterio);
			}
		}
	}
	
	/**
	 * [UC00054] Inserir Tarifa Social
	 * 
	 * [FS00001] Verificar Registro Atendimento
	 * 
	 * @author Rafael Santos
	 * @date 10/11/2006
	 * 
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarRegistroAtendimentoTarifaSocial(
			String idRegistroAtendimento) throws ControladorException {
		
		RegistroAtendimento registroAtendimento = null;
		
		try {
			
			registroAtendimento = repositorioRegistroAtendimento
			.pesquisarRegistroAtendimentoTarifaSocial(new Integer(
					idRegistroAtendimento));
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (registroAtendimento != null) {
			
			// Registro de Atendimento não está associado a um Imóvel
			if (registroAtendimento.getImovel() == null) {
				// FS0001 - Validar Registro de Atendimento
				throw new ControladorException(
				"atencao.registro_atendimento.nao.associado.imovel");
			}
			
			// Registro de Atendimento está encerrado
			if (registroAtendimento.getAtendimentoMotivoEncerramento() != null) {
				// FS0001 - Validar Registro de Atendimento
				throw new ControladorException(
				"atencao.registro_atendimento.esta.encerrado");
			}
			
			// Tipo de Solicitação do registro de atendimento não permite a
			// inclusão na tarifa social
			if (registroAtendimento.getSolicitacaoTipoEspecificacao()
					.getSolicitacaoTipo().getIndicadorTarifaSocial() == 2) {
				// FS0001 - Validar Registro de Atendimento
				throw new ControladorException(
				"atencao.registro_atendimento.nao.permite.inclusao.tarifa_social");
			}
		}
		
		return registroAtendimento;
	}
	
	/**
	 * [UC00069] Manter Dados Tarifa Social
	 * 
	 * [FS00001] Verificar Registro Atendimento
	 * 
	 * @author Rafael Corrêa
	 * @date 05/02/2007
	 * 
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarRegistroAtendimentoManterTarifaSocial(
			String idRegistroAtendimento) throws ControladorException {
		
		RegistroAtendimento registroAtendimento = null;
		
		try {
			
			registroAtendimento = repositorioRegistroAtendimento
			.pesquisarRegistroAtendimentoTarifaSocial(new Integer(
					idRegistroAtendimento));
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (registroAtendimento != null) {
			
			// Registro de Atendimento não está associado a um Imóvel
			if (registroAtendimento.getImovel() == null) {
				// FS0001 - Validar Registro de Atendimento
				throw new ControladorException(
				"atencao.registro_atendimento.nao.associado.imovel");
			}
			
			// Registro de Atendimento está encerrado
			if (registroAtendimento.getAtendimentoMotivoEncerramento() != null) {
				// FS0001 - Validar Registro de Atendimento
				throw new ControladorException(
				"atencao.registro_atendimento.esta.encerrado");
			}
			
			// Tipo de Solicitação do registro de atendimento não permite a
			// inclusão na tarifa social
			if (registroAtendimento.getSolicitacaoTipoEspecificacao()
					.getSolicitacaoTipo().getIndicadorTarifaSocial() == 2) {
				// FS0001 - Validar Registro de Atendimento
				throw new ControladorException(
				"atencao.registro_atendimento.nao.permite.manutencao.tarifa_social");
			}
		}
		
		return registroAtendimento;
	}
	
	/**
	 * [UC0401] Atualizar Tipo de Solicitação com Especificação
	 * 
	 * [SB0001] - Atualizar Tipo Solicitação com Especificações
	 * 
	 * @author Rômulo Aurélio
	 * @date 01/08/2006
	 * 
	 * @param solicitacaoTipo,
	 *            colecaoSolicitacaoTipoEspecificacao, usuarioLogado
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer atualizarTipoSolicitacaoEspecificacao(
			SolicitacaoTipo solicitacaoTipo,
			Collection colecaoSolicitacaoTipoEspecificacao,
			Usuario usuarioLogado,
			Collection colecaoSolicitacaoTipoEspecificacaoRemovidos)
	throws ControladorException {
		
		Integer idTipoSolicitacao = null;
		
		// [SF0001] - Verificar existencia da descrição
		try {
			
			Integer idSolicitacaoTipoNaBase = repositorioRegistroAtendimento
			.verificarExistenciaDescricaoTipoSolicitacao(solicitacaoTipo
					.getDescricao());
			
			if (idSolicitacaoTipoNaBase != null
					&& idSolicitacaoTipoNaBase.intValue() != solicitacaoTipo
					.getId().intValue()) {
				if (idSolicitacaoTipoNaBase != null
						&& !idSolicitacaoTipoNaBase.equals("")) {
					throw new ControladorException(
					"atencao.tipo.solicitacao.ja.existe");
					
				}
				
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		/*
		 * Controle de concorrencia
		 */
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipo.ID, solicitacaoTipo.getId().toString()));
		Collection colecaoSolicitacaoTipoBase = getControladorUtil().pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		SolicitacaoTipo solicitacaoTipoBase = null;
		if (!colecaoSolicitacaoTipoBase.isEmpty()) {
			solicitacaoTipoBase = (SolicitacaoTipo) Util
			.retonarObjetoDeColecao(colecaoSolicitacaoTipoBase);
			
			if (solicitacaoTipoBase.getUltimaAlteracao().after(
					solicitacaoTipo.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}
		// Fim controle de concorrencia
		
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR,
				solicitacaoTipo.getId(), solicitacaoTipo.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado, 
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		registradorOperacao.registrarOperacao(solicitacaoTipo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		// atualiza tipo solicitacao especificacao
		
		getControladorUtil().atualizar(solicitacaoTipo);
		// solicitacaoTipo.setId(idTipoSolicitacao);
		
		Iterator iteratorSolicitacaoTipoEspecificacao = colecaoSolicitacaoTipoEspecificacao
		.iterator();
		while (iteratorSolicitacaoTipoEspecificacao.hasNext()) {
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iteratorSolicitacaoTipoEspecificacao
			.next();
			// recupera a coleção de especificação Serviço tipo
			Collection colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao
			.getEspecificacaoServicoTipos();
			// limpa a coleção de especificação Serviço tipo no objeto
			// solicitação tipo especificação para não dar erro
			solicitacaoTipoEspecificacao.setEspecificacaoServicoTipos(null);
			// inseri o tipo de solicitação no tipo de solicitação especificação
			solicitacaoTipoEspecificacao.setSolicitacaoTipo(solicitacaoTipo);
			
			if (solicitacaoTipoEspecificacao.getId() == null) {
				
				//registra a transação
				registradorOperacao.registrarOperacao(solicitacaoTipoEspecificacao);
				
				// inseri tipo solicitacao especificacao
				Integer idSolicitacaoTipoEspecificacao = (Integer) getControladorUtil()
				.inserir(solicitacaoTipoEspecificacao);
				
				// caso a coleção de especificação Serviço tipo seja diferente
				// de
				// nula
				if (colecaoEspecificacaoServicoTipo != null
						&& !colecaoEspecificacaoServicoTipo.isEmpty()) {
					Iterator iteratorSolicitacaoTipoServico = colecaoEspecificacaoServicoTipo
					.iterator();
					while (iteratorSolicitacaoTipoServico.hasNext()) {
						EspecificacaoServicoTipo especificacaoServicoTipo = (EspecificacaoServicoTipo) iteratorSolicitacaoTipoServico
						.next();
						EspecificacaoServicoTipoPK especificacaoServicoTipoPK = especificacaoServicoTipo
						.getComp_id();
						if (solicitacaoTipoEspecificacao.getId() != null) {
							especificacaoServicoTipoPK
							.setIdSolicitacaoTipoEspecificacao(idSolicitacaoTipoEspecificacao);
						}
						especificacaoServicoTipo
						.setComp_id(especificacaoServicoTipoPK);
						// atualizar especificacao Serviço tipo
						getControladorUtil().inserir(especificacaoServicoTipo);
					}
				}
				
			} else {
				//registra a transação
				registradorOperacao.registrarOperacao(solicitacaoTipoEspecificacao);
				
				// atualiza tipo solicitacao especificacao
				getControladorUtil().atualizar(solicitacaoTipoEspecificacao);
				
				// caso a coleção de especificação Serviço tipo seja diferente
				// de
				// nula
				
				FiltroEspecificacaoServicoTipo filtroEspecificacaoServicoTipo = new FiltroEspecificacaoServicoTipo();
				filtroEspecificacaoServicoTipo
				.adicionarParametro(new ParametroSimples(
						FiltroEspecificacaoServicoTipo.SOLICITACAO_TIPO_ESPECIFICACAO_ID,
						solicitacaoTipoEspecificacao.getId()));
				
				Collection colecaoEspecificacaoServicoTipoBase = getControladorUtil()
				.pesquisar(filtroEspecificacaoServicoTipo,
						EspecificacaoServicoTipo.class.getName());
				
				if (colecaoEspecificacaoServicoTipoBase != null
						&& !colecaoEspecificacaoServicoTipoBase.isEmpty()) {
					Iterator colecaoEspecificacaoServicoTipoBaseIterator = colecaoEspecificacaoServicoTipoBase
					.iterator();
					
					while (colecaoEspecificacaoServicoTipoBaseIterator
							.hasNext()) {
						EspecificacaoServicoTipo especificacaoServicoTipoBase = (EspecificacaoServicoTipo) colecaoEspecificacaoServicoTipoBaseIterator
						.next();
						getControladorUtil().remover(
								especificacaoServicoTipoBase);
					}
				}
				
				if (colecaoEspecificacaoServicoTipo != null
						&& !colecaoEspecificacaoServicoTipo.isEmpty()) {
					Iterator iteratorSolicitacaoTipoServico = colecaoEspecificacaoServicoTipo
					.iterator();
					while (iteratorSolicitacaoTipoServico.hasNext()) {
						
						EspecificacaoServicoTipo especificacaoServicoTipo = (EspecificacaoServicoTipo) iteratorSolicitacaoTipoServico
						.next();
						EspecificacaoServicoTipoPK especificacaoServicoTipoPK = especificacaoServicoTipo
						.getComp_id();
						especificacaoServicoTipoPK
						.setIdSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao
								.getId());
						especificacaoServicoTipo
						.setComp_id(especificacaoServicoTipoPK);
						// inseri especificacao Serviço tipo
						getControladorUtil().inserir(especificacaoServicoTipo);
					}
					
				}
			}
			
			if (colecaoSolicitacaoTipoEspecificacaoRemovidos != null
					&& !colecaoSolicitacaoTipoEspecificacaoRemovidos.isEmpty()) {
				
				Iterator iteratorSolicitacaoTipoEspecificacaoRemovidos = colecaoSolicitacaoTipoEspecificacaoRemovidos
				.iterator();
				
				while (iteratorSolicitacaoTipoEspecificacaoRemovidos.hasNext()) {
					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoRemovido = (SolicitacaoTipoEspecificacao) iteratorSolicitacaoTipoEspecificacaoRemovidos
					.next();
					
					if (solicitacaoTipoEspecificacaoRemovido.getId() != null) {
						
						getControladorUtil().remover(
								solicitacaoTipoEspecificacaoRemovido);
						
					}
				}
				
			}
			
			colecaoSolicitacaoTipoEspecificacaoRemovidos = null;
		}
		
		return idTipoSolicitacao;
	}
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * [SB0003]Incluir o Tramite
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void tramitarConjuntoRA(Collection tramites)
	throws ControladorException {
		if (tramites != null && !tramites.isEmpty()) {
			Iterator iteratorTramite = tramites.iterator();
			while (iteratorTramite.hasNext()) {
				Tramite tramite = (Tramite) iteratorTramite.next();
				tramite.setUltimaAlteracao(new Date());
				// tramite.setDataTramite(new Date());
				getControladorUtil().inserir(tramite);
				
				
				// inclusão da coluna unidade atual na tabela REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// Vivianne Sousa 12/06/2008 analista:Fátima Sampaio
				
				//6.2 Atualiza a unidade atual do registro de atendimento 
				// e das ordens de servico associadas a ele
				this.atualizarUnidadeOrganizacionalAtualRA(
						tramite.getUnidadeOrganizacionalDestino().getId(), 
						tramite.getRegistroAtendimento().getId());
				
				getControladorOrdemServico().atualizarUnidadeOrganizacionalAtualOS(
						tramite.getUnidadeOrganizacionalDestino().getId(), 
						tramite.getRegistroAtendimento().getId());
				
			}
		}
	}
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * [FS0006] Valida Data [FS0007] Valida Hora [FS0008] Valida Unidade Destino
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void validarConjuntoTramitacao(String[] ids,
			Date dataHoraTramitacao, Integer idUnidadeDestino, Usuario usuario)
	throws ControladorException {
		
		// [FS0006] Validar Data
		// [FS0007] Validar Hora
		Date dataCorrente = new Date();
		int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(
				dataHoraTramitacao, dataCorrente);
		
		if (qtdeDias < 0) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tramitar_ra_data_maior_que_atual", null, Util
					.formatarData(dataCorrente));
			
		} else if (Util.datasIguais(dataHoraTramitacao, dataCorrente)) {
			if (Util.compararHoraMinuto(Util
					.formatarHoraSemData(dataHoraTramitacao), Util
					.formatarHoraSemData(dataCorrente), ">")) {
				
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.tramitar_ra_hora_maior_que_atual", null, Util
						.formatarHoraSemData(dataCorrente));
			}
		}
		
		UnidadeOrganizacional unidadeDestino = this.getControladorUnidade()
		.pesquisarUnidadeOrganizacional(idUnidadeDestino);
		
		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				String[] idsSelecionados = ids[i].split(";");
				Integer idRa = Integer.parseInt(idsSelecionados[0]);
				Integer idUnidadeOrigem = Integer.parseInt(idsSelecionados[1]);
				
				UnidadeOrganizacional unidadeOrigem = this
				.getControladorUnidade()
				.pesquisarUnidadeOrganizacional(idUnidadeOrigem);
				
				boolean possuiOSTerceirizado = false;
				
				boolean possuiOSPendente = false;
				
				Collection<OrdemServico> colecaoOrdemServico = obterOSRA(idRa);
				
				if (colecaoOrdemServico != null
						&& !colecaoOrdemServico.isEmpty()) {
					
					for (Iterator iter = colecaoOrdemServico.iterator(); iter
					.hasNext();) {
						
						OrdemServico element = (OrdemServico) iter.next();
						
						if ((new Short(element.getSituacao())).intValue() == OrdemServico.SITUACAO_PENDENTE
								.intValue()) {
						
							possuiOSPendente = true;
							
							// [FS0008 Caso 4]
							if ((new Short(element.getServicoTipo()
									.getIndicadorTerceirizado())).intValue() == 1) {
								possuiOSTerceirizado = true;
							}
						}
					}
				}
				
				// [FS0008] Validar Unidade Destino
				// Caso 1
				if (idUnidadeOrigem.intValue() == idUnidadeDestino.intValue()) {
					
					sessionContext.setRollbackOnly();
					throw new ControladorException(
					"atencao.tramitar_ra_unidade_origem_destino_iguais");
				}
				
				try {
					Short idTarifaSocialRa = repositorioRegistroAtendimento
					.verificarIndicadorTarifaSocialRA(idRa);
					
					// Caso 2
					if ((new Short(unidadeDestino.getIndicadorTramite()))
							.intValue() == 2) {
						if (new Short(idTarifaSocialRa).intValue() == 2) {
							
							sessionContext.setRollbackOnly();
							throw new ControladorException("atencao.tramitar_ra_unidade_destino_tramite");
						}
					}
					//alteracao adicionada por Anderson Italo
					//Adicionado validação para verificar indicador de uso da unidadeDestino
					//data: 28/07/2009
					if ((new Short(unidadeDestino.getIndicadorUso()))
							.intValue() == 2) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.unidade_destino_inativa", null, unidadeDestino.getId().toString());
					}
					//fim alteracao
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
				
				if (unidadeDestino.getUnidadeTipo() != null
						&& unidadeDestino.getUnidadeTipo().getCodigoTipo() != null) {
					
					if (unidadeDestino.getUnidadeTipo().getCodigoTipo().equals(
							UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {
						
						// Caso 3
						if (unidadeOrigem.getUnidadeTipo() == null
								|| unidadeOrigem.getUnidadeTipo()
								.getCodigoTipo() == null
								|| !unidadeOrigem
								.getUnidadeTipo()
								.getCodigoTipo()
								.equals(
										UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)) {
							
							sessionContext.setRollbackOnly();
							throw new ControladorException(
							"atencao.tramitar_ra_unidade_centralizadora");
						}
						// Caso 4
						if (!possuiOSTerceirizado) {
							sessionContext.setRollbackOnly();
							throw new ControladorException(
									"atencao.tramitar_ra_os_possui_terceiros",
									null, idRa.toString());
						}
					}
					// Caso 5
					if (unidadeDestino.getUnidadeTipo().getCodigoTipo().equals(
							UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)
							&& !unidadeOrigem
							.getUnidadeTipo()
							.getCodigoTipo()
							.equals(
									UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)) {
//						Integer unidadeCentralizadora = 0;
//						try {
//							unidadeCentralizadora = repositorioRegistroAtendimento
//							.pesquisarUnidadeCentralizadoraRa(idRa);
//						} catch (ErroRepositorioException ex) {
//							sessionContext.setRollbackOnly();
//							ex.printStackTrace();
//							throw new ControladorException("erro.sistema", ex);
//						}
//						if (unidadeCentralizadora != unidadeDestino.getId()
//								.intValue()) {
//							sessionContext.setRollbackOnly();
//							throw new ControladorException(
//							"atencao.tramitar_ra_unidade_origem_central");
//						}

						SistemaParametro sistemaParametro = this.getControladorUtil()
						.pesquisarParametrosDoSistema();

						// Caso seja COMPESA: 
						// Alteracao para permitir uma adequacao a nova estrutura implantada na COMPESA
						// Francisco/Fátima : 10/12/2009
						if(sistemaParametro.getNomeEmpresa()!= null && 
								sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_COMPESA) 
							&& possuiOSPendente){
							
							Collection<Integer> colecaoUnidades = null;
							try{
								colecaoUnidades = repositorioRegistroAtendimento.
								pesquisarPossiveisUnidadesCentralizadorasRa(idRa);
							} catch (ErroRepositorioException ex) {
								sessionContext.setRollbackOnly();
								ex.printStackTrace();
								throw new ControladorException("erro.sistema", ex);
							}
							
							if(colecaoUnidades == null || colecaoUnidades.isEmpty()){
								sessionContext.setRollbackOnly();
								throw new ControladorException(
								"atencao.tramitar_ra_unidade_origem_central");
							} else {

								boolean encontrouUnidadeValida = false;
								
								for (Iterator iter = colecaoUnidades.iterator(); iter
										.hasNext();) {
									Integer idUnidade = (Integer) iter.next();
									if (idUnidade.intValue() == unidadeDestino.getId().intValue()){
										encontrouUnidadeValida = true;
									}
								}
								if (!encontrouUnidadeValida){
									sessionContext.setRollbackOnly();
									throw new ControladorException(
									"atencao.tramitar_ra_unidade_origem_central");
								}
							}
									
						} else {						
						
							Integer unidadeCentralizadora = 0;
							try{
								unidadeCentralizadora = repositorioRegistroAtendimento.
								pesquisarUnidadeCentralizadoraRa(idRa);
							} catch (ErroRepositorioException ex) {
								sessionContext.setRollbackOnly();
								ex.printStackTrace();
								throw new ControladorException("erro.sistema", ex);
							}
							if(unidadeCentralizadora == null || unidadeCentralizadora!= unidadeDestino.getId().intValue()){
								sessionContext.setRollbackOnly();
								throw new ControladorException(
								"atencao.tramitar_ra_unidade_origem_central");
							}
						}						
					
					}
				}
				
				//caso 6
				//caso a unidade atual do registro seja "terceirizada" e 
				//o usuario não estiver lotado nesta unidade
				if (unidadeOrigem.getUnidadeTipo() != null
						&& unidadeOrigem.getUnidadeTipo().getCodigoTipo() != null) {
					
					if (unidadeOrigem.getUnidadeTipo().getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)
							&& !usuario.getUnidadeOrganizacional().getId().equals(unidadeOrigem.getId())){
						
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.tramitar_ra_unidade_usuario");
					}
				}
				
				
				//adicionado por Vivianne Sousa, 29/10/2008
				//analista responsavel:Fabiola
				//[FS0011]Validar Tramite para terceira
				if (unidadeDestino.getUnidadeTipo() != null	&& unidadeDestino.getUnidadeTipo().getCodigoTipo() != null
						&& unidadeDestino.getUnidadeTipo().getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {
					
					boolean existe = getControladorOrdemServico().
					existeMaisDeUmaOrdemServicoPendente(idRa);
					
					if (existe){
						
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.tramitar_ra_possui_mais_de_uma_os_pensente",
								null, idRa.toString());
						
					}
				}
				
			}
		}
	}
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * [FS0002] Verificar as situações das OS associadas ao RA [FS0003]
	 * Verificar se o tipo de solicitação Tarifa Social
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void validarRATramitacao(String[] ids, Integer idUsuario)
	throws ControladorException {
		
		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				
				String[] idsSelecionados = ids[i].split(";");
				Integer idRa = Integer.parseInt(idsSelecionados[0]);
				// [FS0002]Verificar as situações das OS associadas ao RA
				Collection<OrdemServico> colecaoOrdemServico = obterOSRA(idRa);
				
				if (colecaoOrdemServico != null
						&& !colecaoOrdemServico.isEmpty()) {
					
					for (Iterator iter = colecaoOrdemServico.iterator(); iter
					.hasNext();) {
						
						OrdemServico element = (OrdemServico) iter.next();
						
						if ((new Short(element.getSituacao())).intValue() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO
								.intValue()) {
							
							// Caso 1
							sessionContext.setRollbackOnly();
							throw new ControladorException(
							"atencao.tramitar_ra_os_em_andamento");
							
						} else if ((new Short(element.getSituacao()))
								.intValue() == OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO
								.intValue()) {
							
							// Caso 2
							sessionContext.setRollbackOnly();
							throw new ControladorException(
									"atencao.tramitar_ra_os_referencia",
									null, element.getOsReferencia().getId()
									+ "");
							
						} else if ((new Short(element.getSituacao()))
								.intValue() == OrdemServico.SITUACAO_PENDENTE
								.intValue()) {
							
							// Caso 3
							if (getControladorOrdemServico()
									.verificarExistenciaOSProgramada(
											element.getId())) {
								sessionContext.setRollbackOnly();
								throw new ControladorException(
								"atencao.tramitar_ra_os_programada");
							}
						}
					}
				}
				
//				Short idTarifaSocialRa = repositorioRegistroAtendimento
//				.verificarIndicadorTarifaSocialRA(idRa);
//				
//				// [FS0003]Verificar se o tipo de solicitação tarifa
//				// social
//				if (idTarifaSocialRa == 1) {
//				if (idTarifaSocialUnidadeUsuario != 1) {
//				sessionContext.setRollbackOnly();
//				throw new ControladorException(
//				"atencao.tramitar_ra_tarifa_social");
//				}
//				}
			}
		}
	}
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis
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
			
			this.repositorioRegistroAtendimento.atualizarLogradouroCep(
					logradouroCepAntigo, logradouroCepNovo);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis
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
			
			this.repositorioRegistroAtendimento.atualizarLogradouroBairro(
					logradouroBairroAntigo, logradouroBairroNovo);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroCepSolicitante(
			LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo)
	throws ControladorException {
		
		try {
			
			this.repositorioRegistroAtendimento
			.atualizarLogradouroCepSolicitante(logradouroCepAntigo,
					logradouroCepNovo);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroBairroSolicitante(
			LogradouroBairro logradouroBairroAntigo,
			LogradouroBairro logradouroBairroNovo) throws ControladorException {
		
		try {
			
			this.repositorioRegistroAtendimento
			.atualizarLogradouroBairroSolicitante(
					logradouroBairroAntigo, logradouroBairroNovo);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * UC?? - ????????
	 * 
	 * @author Rômulo Aurélio Filho
	 * @date 20/03/2007
	 * @descricao O método retorna um objeto com a maior data de Tramite
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Tramite pesquisarUltimaDataTramite(Integer idRA)
	throws ControladorException {
		try {
			return repositorioRegistroAtendimento
			.pesquisarUltimaDataTramite(idRA);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Consultar Observacao Registro Atendimento Solicitacao da CAER
	 * 
	 * @author Rafael Pinto
	 * @date 14/03/2007
	 */
	public Collection<RegistroAtendimento> pesquisarObservacaoRegistroAtendimento(
			Integer matriculaImovel, Date dataInicialAtendimento,
			Date dataFinalAtendimento) throws ControladorException {
		
		Collection<RegistroAtendimento> retorno = new ArrayList();
		
		Collection colecao = new ArrayList();
		
		try {
			
			colecao = repositorioRegistroAtendimento
			.pesquisarObservacaoRegistroAtendimento(matriculaImovel,
					dataInicialAtendimento, dataFinalAtendimento);
			
			if (colecao != null && !colecao.isEmpty()) {
				
				Iterator itera = colecao.iterator();
				
				while (itera.hasNext()) {
					Object[] retornoColecao = (Object[]) itera.next();
					
					RegistroAtendimento ra = new RegistroAtendimento();
					
					ra.setId((Integer) retornoColecao[0]);
					ra.setRegistroAtendimento((Date) retornoColecao[1]);
					ra.setObservacao((String) retornoColecao[2]);
					
					retorno.add(ra);
				}
				
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
		
	}
	
	/**
	 * Método que gera o resumo dos registros de atendimentos
	 * 
	 * [UC0567] - Gerar Resumo dos Registro de Atendimentos
	 * 
	 * @author Thiago Tenório, Ivan Sérgio
	 * @date 24/04/2007, 14/01/2009
	 * @alteracao 14/01/2009 - CRC811 - Adicionado o campo amen_id(Motivo Encerramento) a consulta.
	 * 
	 */
	public void gerarResumoRegistroAtendimento(int idLocalidade,
			int idFuncionalidadeIniciada, Integer anoMesReferencia) throws ControladorException {
		
		int idUnidadeIniciada = 0;
		
		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.LOCALIDADE, idLocalidade);
		
		//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
		getControladorGerencialCadastro().excluirResumoGerencialC(getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), "atendimentopublico.un_resumo_registro_atendimento", "rera_amreferencia", "loca_id", idLocalidade );				
		
		try {
			Integer dtAtual = Integer.decode(Util.recuperaDataInvertida(new Date()));
			
			Collection imoveisRegistroAtendimento = this.repositorioRegistroAtendimento
			.getImoveisResumoRegistroAtendimento(idLocalidade, anoMesReferencia, dtAtual );
			
			List<ResumoRegistroAtendimentoHelper> listaSimplificada = new ArrayList();
			List<UnResumoRegistroAtendimento> listaResumoRegistroAtendimento = new ArrayList();
			
			Iterator imoveisRegistroAtendimentoIterator = imoveisRegistroAtendimento.iterator();
			
			Integer idUnidadeSolicitacao;
			Integer idUnidadeEncerramento;
			
			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de registro de atendimentos
			while (imoveisRegistroAtendimentoIterator.hasNext()) {
				Object obj = imoveisRegistroAtendimentoIterator.next();
				
				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;
					
					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					
					ResumoRegistroAtendimentoHelper helper = new ResumoRegistroAtendimentoHelper(
							(Integer) retorno[1], // idGerenciaRegional
							(Integer) retorno[2], // idUnidadeNegocio
							(Integer) retorno[3], // idLocalidade			
							(Integer) retorno[4], // idElo
							(Integer) retorno[5], // idSetorcomercial
							(Integer) retorno[6], // idRota
							(Integer) retorno[7], // idQuadra
							(Integer) retorno[8], // codigoSetorComercial
							(Integer) retorno[9], // numeroQuadra
							(Integer) retorno[10],// perfilImovel
							(Integer) retorno[11],// idSituacaoLigacaoAgua
							(Integer) retorno[12],// idSituacaoLigacaoEsgoto
							(Integer) retorno[13],// idPerfuilLigacaiAgua	
							(Integer) retorno[14],// idPerfilLigacaoEsgoto											
							(Short)   retorno[15],// indicadorAtendimentoOnline
							(Integer) retorno[16],// idTipoSolicitacao
							(Integer) retorno[17],// idTipoEspecificacao
							(Integer) retorno[18],// idMeioSolicitacao
							(Integer) retorno[19],// quantidadeGerada
							(Integer) retorno[20],// quantidadePendentesNoPrazo
							(Integer) retorno[21],// quantidadePendentesForaPrazo
							(Integer) retorno[22],// quantidadeEncerradasNoPrazo
							(Integer) retorno[23],// quantidadeEncerradasForaPrazo
							(Integer) retorno[24],// quantidadeBloqueadas
							(Integer) retorno[25],// quantidadeGeradasNoMesPrazo
							(Integer) retorno[26],// quantidadeGeradasNoMesForaPrazo
							(Short)   retorno[28]);// codigo Rota
					
					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do imóvel
					Integer idImovel = (Integer) retorno[0];
					Integer idRegistroAtendimento = (Integer) retorno[27];
					
					if(idImovel != null){					
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsfera( this.repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel( idImovel ) );
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoClienteResponsavel( this.repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel( idImovel ) );							
						
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
					}
					
					// Pesquisa a Unidade de Solicitacao
					idUnidadeSolicitacao = pesquisaUnidadeSolicitacaoRa(idRegistroAtendimento);
					helper.setIdUnidadeSolicitacao(idUnidadeSolicitacao);
					
					// Pesquisa a Unidade de Encerramento
					idUnidadeEncerramento = pesquisaUnidadeEncerramentoRa(idRegistroAtendimento);
					if (idUnidadeEncerramento != null) {
						helper.setIdUnidadeEncerramento(idUnidadeEncerramento);
					}
					
					// Caso loca_cdelo = 0 significa que
					// loca_cdelo = loca_id
					if (helper.getIdElo().equals(0)) {
						helper.setIdElo(helper.getIdLocalidade());
					}
					
					if (helper.getQuantidadePendentesNoPrazo() == null) {
						helper.setQuantidadePendentesNoPrazo(0);
					}
					if (helper.getQuantidadePendentesForaPrazo() == null) {
						helper.setQuantidadePendentesForaPrazo(0);
					}
					
					if (helper.getQuantidadeEncerradasNoPrazo() == null) {
						helper.setQuantidadeEncerradasNoPrazo(0);
					}
					if (helper.getQuantidadeEncerradasForaPrazo() == null) {
						helper.setQuantidadeEncerradasForaPrazo(0);
					}
					
					if(helper.getQuantidadeGeradasNoMesPrazo() == null) {
						helper.setQuantidadeGeradasNoMesPrazo(0);
					}
					if(helper.getQuantidadeGeradasNoMesForaPrazo() == null) {
						helper.setQuantidadeGeradasNoMesForaPrazo(0);
					}
					
					//*****************************************************
					// CRC811 - Motivo Encerramento
					//*****************************************************
					helper.setIdMotivoEncerramento((Integer) retorno[29]);
					//*****************************************************
					
					// se ja existe um objeto igual a ele entao soma as
					// ligacoes e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals ( excecao 
					// quantidadeEconomia, quantidadeLigacoes )
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						ResumoRegistroAtendimentoHelper jaCadastrado = 
							(ResumoRegistroAtendimentoHelper) listaSimplificada.get(posicao);
						
						jaCadastrado.setQuantidadeGerada(
								jaCadastrado.getQuantidadeGerada() + helper.getQuantidadeGerada() );
						
						jaCadastrado.setQuantidadeBloqueada( 
								jaCadastrado.getQuantidadeBloqueada() + helper.getQuantidadeBloqueada() );
						
						jaCadastrado.setQuantidadeEncerradasNoPrazo( 
								jaCadastrado.getQuantidadeEncerradasNoPrazo() + 
								helper.getQuantidadeEncerradasNoPrazo() );
						
						jaCadastrado.setQuantidadeEncerradasForaPrazo( 
								jaCadastrado.getQuantidadeEncerradasForaPrazo() + 
								helper.getQuantidadeEncerradasForaPrazo() );
						
						jaCadastrado.setQuantidadePendentesNoPrazo( 
								jaCadastrado.getQuantidadePendentesNoPrazo() + 
								helper.getQuantidadePendentesNoPrazo() );
						
						jaCadastrado.setQuantidadePendentesForaPrazo( 
								jaCadastrado.getQuantidadePendentesForaPrazo() + 
								helper.getQuantidadePendentesForaPrazo() );
						
						jaCadastrado.setQuantidadeGeradasNoMesPrazo(								 
								jaCadastrado.getQuantidadeGeradasNoMesPrazo() + 
								helper.getQuantidadeGeradasNoMesPrazo() );
						
						jaCadastrado.setQuantidadeGeradasNoMesForaPrazo( 
								jaCadastrado.getQuantidadeGeradasNoMesForaPrazo() + 
								helper.getQuantidadeGeradasNoMesForaPrazo() );
						
					} else {
						listaSimplificada.add(helper);
					}
				}
			}
			/**
			 * para todas os ImovelResumoRegistroAtendimentoHelper cria
			 * ResumoRegistroAtendimento
			 */
			
			for (int count = 0; count < listaSimplificada.size(); count++) {
				ResumoRegistroAtendimentoHelper helper = (ResumoRegistroAtendimentoHelper) listaSimplificada
				.get(count);					
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}
				
				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
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
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId( helper.getIdTipoClienteResponsavel() );
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
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// indicadorAtendimentoOnline
				Short indicadorAtendimentoOnline = null;
				if (helper.getIndicadorAtendimentoOnline() != null) {
					indicadorAtendimentoOnline = (helper.getIndicadorAtendimentoOnline());
				}	
				
				// Solicitação Tipo
				GSolicitacaoTipo solicitacaoTipo = null;
				if ( helper.getIdSolicitacaoTipo() != null ){
					solicitacaoTipo = new GSolicitacaoTipo();
					solicitacaoTipo.setId( helper.getIdSolicitacaoTipo() );
				}
				
				// Solicitação Tipo Especificações
				GSolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
				if(helper.getIdsolicitacaoTipoEspecificacao() != null ){
					solicitacaoTipoEspecificacao = new GSolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao.setId( helper.getIdsolicitacaoTipoEspecificacao() );
				}					
				
				// Meio de Solicitação
				GMeioSolicitacao meioSolicitacao = null;
				if (helper.getIdMeioSolicitacao() != null) {
					meioSolicitacao = new GMeioSolicitacao();
					meioSolicitacao.setId(helper.getIdMeioSolicitacao());
				}
				
				//**************************************************************
				// CRC811 - Motivo Encerramento
				//**************************************************************
				GAtendimentoMotivoEncerramento motivoEncerramento = null;
				if (helper.getIdMotivoEncerramento() != null) {
					motivoEncerramento = new GAtendimentoMotivoEncerramento();
					motivoEncerramento.setId(helper.getIdMotivoEncerramento());
				}
				//**************************************************************
				
				Integer unidadeSolicitacao = helper.getIdUnidadeSolicitacao();
				Integer unidadeEncerramento = helper.getIdUnidadeEncerramento();
				
				int countQtdPedentesNoPrazo = helper.getQuantidadePendentesNoPrazo();
				int countQtdPedentesForaPrazo = helper.getQuantidadePendentesForaPrazo();
				int countQtdEncerradasNoPrazo = helper.getQuantidadeEncerradasNoPrazo();
				int countQtdEncerradasForaPrazo = helper.getQuantidadeEncerradasForaPrazo();
				int countQtdBloqueadas = helper.getQuantidadeBloqueada();
				int countQtdGeradasNoMesPrazo = helper.getQuantidadeGeradasNoMesPrazo();
				int countQtdGeradasNoMesForaPrazo = helper.getQuantidadeGeradasNoMesForaPrazo();
				int countQtdTotal = helper.getQuantidadeGerada();					
				
				// Criamos um resumo de ligacao economia (***UFA***)
				UnResumoRegistroAtendimento resumoRegistroAtendimento = new UnResumoRegistroAtendimento(
						null, 
						anoMesReferencia.intValue(), 
						new Date(),
						countQtdTotal, 
						codigoSetorComercial,
						countQtdPedentesNoPrazo, 
						countQtdPedentesForaPrazo,
						countQtdGeradasNoMesPrazo,
						countQtdGeradasNoMesForaPrazo,
						numeroQuadra,
						countQtdBloqueadas, 
						countQtdEncerradasNoPrazo,
						countQtdEncerradasForaPrazo,
						indicadorAtendimentoOnline, 
						meioSolicitacao,
						subcategoria, 
						clienteTipo, 
						ligacaoAguaSituacao,
						unidadeNegocio, 
						localidade, 
						elo, 
						solicitacaoTipo,
						quadra, 
						ligacaoEsgotoSituacao, 
						perfilLigacaoEsgoto,
						solicitacaoTipoEspecificacao, 
						gerenciaRegional, 
						setorComercial,
						perfilLigacaoAgua,
						esferaPoder, 
						categoria,
						imovelPerfil, 
						rota,
						helper.getCodigoRota());
				
				resumoRegistroAtendimento.setUnidadeSolicitacao(unidadeSolicitacao);
				resumoRegistroAtendimento.setUnidadeEncerramento(unidadeEncerramento);
				
				//**************************************************************
				// CRC811 - Motivo Encerramento
				//**************************************************************
				resumoRegistroAtendimento.setGerMotivoEncerramento(motivoEncerramento);
				//**************************************************************
				
				// Adicionamos a lista que deve ser inserida
				listaResumoRegistroAtendimento.add(resumoRegistroAtendimento);
				gerenciaRegional = null;
				unidadeNegocio = null;
				localidade = null;
				elo = null;
				setorComercial = null;
				rota = null;
				quadra = null;
				imovelPerfil = null;
				esferaPoder = null;
				clienteTipo = null;
				ligacaoAguaSituacao = null;
				ligacaoEsgotoSituacao = null;
				categoria = null;
				subcategoria = null;
				perfilLigacaoAgua = null;
				perfilLigacaoEsgoto = null;
				indicadorAtendimentoOnline = null;
				solicitacaoTipo = null;
				solicitacaoTipoEspecificacao = null;
				meioSolicitacao = null;
				unidadeSolicitacao = null;
				unidadeEncerramento = null;
				motivoEncerramento = null;
			}
			listaSimplificada.clear();
			listaSimplificada = null;
			
			imoveisRegistroAtendimento.clear();
			imoveisRegistroAtendimento = null;
			
			getControladorBatch().inserirColecaoObjetoParaBatchGerencial( (Collection) listaResumoRegistroAtendimento);
			
			listaResumoRegistroAtendimento.clear();
			listaResumoRegistroAtendimento = null;
			
			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		}
		catch (Exception ex) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido
			
			ex.printStackTrace();
			//sessionContext.setRollbackOnly();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * [UC0459] Informar Dados da Agencia Reguladora
	 *
	 * @author Kássia Albuquerque
	 * @date 09/04/2007
	 */
	
	
	public Integer informarDadosAgenciaReguladora(RaDadosAgenciaReguladora raDadosAgenciaReguladora,
			Collection collectionRaDadosAgenciaReguladoraFone,Usuario usuarioLogado) throws ControladorException {
		
		
		// Verificando restricao da tabela Ra_Dados_Agencia_Reguladora   
		//  xak1_ra_dados_arpe_fone = UNIQUE INDEX (RGAT_ID,RAAR_NNAGENCIAREGULADORA)
		FiltroRaDadosAgenciaReguladora filtroRaDadosAgenciaReguladora = new FiltroRaDadosAgenciaReguladora();
		
		filtroRaDadosAgenciaReguladora.adicionarParametro(
				new ParametroSimples(FiltroRaDadosAgenciaReguladora.REGISTRO_ATENDIMENTO_ID,
						raDadosAgenciaReguladora.getRegistroAtendimento().getId()));
		
		filtroRaDadosAgenciaReguladora.adicionarParametro(
				new ParametroSimples(FiltroRaDadosAgenciaReguladora.AGENCIA_REGULADORA,
						raDadosAgenciaReguladora.getAgenciaReguladora())); 
		
		Collection collectionUniqueIndexRepetido = getControladorUtil().pesquisar(filtroRaDadosAgenciaReguladora,RaDadosAgenciaReguladora.class.getName());
		
		if (collectionUniqueIndexRepetido!= null && !collectionUniqueIndexRepetido.isEmpty()){
			throw new ControladorException("atencao.ra_existente_numero_agencia_reguladora");
		}
		
		raDadosAgenciaReguladora.setDataReclamacao(new Date());
		
		raDadosAgenciaReguladora.setUltimaAlteracao(new Date());
		
		// ------------ REGISTRAR TRANSAÇÃO DADOS AGENCIA REGULADORA----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RA_DADOS_AGENCIA_REGULADORA_INFORMAR, new UsuarioAcaoUsuarioHelper(
						Usuario.USUARIO_TESTE,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_RA_DADOS_AGENCIA_REGULADORA_INFORMAR);
		
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		
		raDadosAgenciaReguladora.setOperacaoEfetuada(operacaoEfetuada);
		raDadosAgenciaReguladora.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(raDadosAgenciaReguladora);
		// ------------ REGISTRAR TRANSAÇÃO DADOS AGENCIA REGULADORA----------------------------
		
		Integer idRaDadosAgenciaReguladora = (Integer) this.getControladorUtil().inserir(raDadosAgenciaReguladora);
		
		// -- codigo para inserir dados agencia reguladora fone --
		raDadosAgenciaReguladora.setId(idRaDadosAgenciaReguladora);
		
		if(collectionRaDadosAgenciaReguladoraFone != null &&
				!collectionRaDadosAgenciaReguladoraFone.isEmpty()){
			
			Iterator iterator = collectionRaDadosAgenciaReguladoraFone.iterator();
			
			while (iterator.hasNext()) {
				
				RaDadosAgenciaReguladoraFone raDadosAgenciaReguladoraFone = (RaDadosAgenciaReguladoraFone) iterator
				.next();
				raDadosAgenciaReguladoraFone.setRaDadosAgenciaReguladora(raDadosAgenciaReguladora);
				
				raDadosAgenciaReguladoraFone.setUltimaAlteracao(new Date());
				// ------------ REGISTRAR TRANSAÇÃO DADOS AGENCIA REGULADORA FONE----------------
				raDadosAgenciaReguladoraFone.setOperacaoEfetuada(operacaoEfetuada);
				raDadosAgenciaReguladoraFone.adicionarUsuario(Usuario.USUARIO_TESTE,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(raDadosAgenciaReguladoraFone);
				// ------------ REGISTRAR TRANSAÇÃO DADOS AGENCIA REGULADORA FONE----------------
				
				this.getControladorUtil().inserir(raDadosAgenciaReguladoraFone);
				
			}
		}
		// -- fim de codigo para inserir dados agencia reguladora fone --
		return idRaDadosAgenciaReguladora;
	}
	
	
	/**
	 * Procura a quantidade de dias de prazo  
	 * 
	 * [UC0459] Informar Dados da Agencia Reguladora
	 * 
	 * @author Kássia Albuquerque
	 * @date 19/04/2007
	 * 
	 */
	
	public Integer procurarDiasPazo(Integer raId) throws ControladorException {
		
		try {
			
			return	this.repositorioRegistroAtendimento.procurarDiasPazo(raId);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Pesquisa a Unidade Solicitante de acordo com a RA  
	 * 
	 * @author Ivan Sérgio
	 * @date 17/08/2007
	 * 
	 */
	private Integer pesquisaUnidadeSolicitacaoRa(Integer idRa) throws ControladorException {
		try {
			return this.repositorioRegistroAtendimento.pesquisaUnidadeSolicitacaoRa(idRa);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Pesquisa a Unidade Encerramento de acordo com a RA  
	 * 
	 * @author Ivan Sérgio
	 * @date 17/08/2007
	 * 
	 */
	private Integer pesquisaUnidadeEncerramentoRa(Integer idRa) throws ControladorException {
		try {
			return this.repositorioRegistroAtendimento.pesquisaUnidadeEncerradaRa(idRa);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * Permite comandar um encerramento de RA
	 *
	 * [UC0735] Comandar Encerramento de Registros de Atendimento
	 *
	 * @author Rafael Corrêa ,Pedro Alexandre
	 * @date 28/01/2008, 10/06/2008
	 *
	 * @param raEncerramentoComando
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer comandarEncerramentoRA(RaEncerramentoComando raEncerramentoComando, Collection<SolicitacaoTipoEspecificacao> colecaoEspecificacoes, Usuario usuarioLogado) throws ControladorException {
		
		Collection<RaEncerramentoComando> colecaoComandoEncerramentoRA = null;
		
		FiltroRaEncerramentoComando filtroRaEncerramentoComando = new FiltroRaEncerramentoComando();
		filtroRaEncerramentoComando.adicionarParametro(new ParametroNulo(FiltroRaEncerramentoComando.TEMPO_REALIZACAO));
		filtroRaEncerramentoComando.adicionarParametro(new ParametroNulo(FiltroRaEncerramentoComando.UNIDADE_ATENDIMENTO_ID));
		filtroRaEncerramentoComando.adicionarParametro(new ParametroNulo(FiltroRaEncerramentoComando.UNIDADE_ATUAL_ID));
		filtroRaEncerramentoComando.adicionarParametro(new ParametroNulo(FiltroRaEncerramentoComando.UNIDADE_SUPERIOR_ID));
		
		colecaoComandoEncerramentoRA = getControladorUtil().pesquisar(filtroRaEncerramentoComando, RaEncerramentoComando.class.getName());
		
		if (colecaoComandoEncerramentoRA != null && !colecaoComandoEncerramentoRA.isEmpty()) {
			for(RaEncerramentoComando raComando : colecaoComandoEncerramentoRA){
				Date periodoInicial = raComando.getDataAtendimentoInicial();
				Date periodoFinal = raComando.getDataAtendimentoFinal();
				
				if((Util.formatarDataInicial(raEncerramentoComando.getDataAtendimentoInicial())).compareTo(periodoInicial) != -1){
					if((Util.formatarDataFinal(raEncerramentoComando.getDataAtendimentoFinal())).compareTo(periodoFinal) != 1){
						throw new ControladorException("atencao.comando.ja.existente");
					}
				}
			}
		}
		
		
		// [FS0008] - Verificar existência de comando para os mesmos parâmetros
		filtroRaEncerramentoComando.limparListaParametros();
		filtroRaEncerramentoComando = new FiltroRaEncerramentoComando();
		filtroRaEncerramentoComando.adicionarParametro(new MenorQue(FiltroRaEncerramentoComando.DATA_ATENDIMENTO_INICIAL, Util.adicionarNumeroDiasDeUmaData(raEncerramentoComando.getDataAtendimentoInicial(), 1)));
		filtroRaEncerramentoComando.adicionarParametro(new MaiorQue(FiltroRaEncerramentoComando.DATA_ATENDIMENTO_FINAL, Util.subtrairNumeroDiasDeUmaData(raEncerramentoComando.getDataAtendimentoFinal(), 1)));
		filtroRaEncerramentoComando.adicionarParametro(new ParametroNulo(FiltroRaEncerramentoComando.TEMPO_REALIZACAO));
		
		if (raEncerramentoComando.getUnidadeOrganizacionalAtendimento() != null) {
			filtroRaEncerramentoComando.adicionarParametro(new ParametroSimples(FiltroRaEncerramentoComando.UNIDADE_ATENDIMENTO_ID,raEncerramentoComando.getUnidadeOrganizacionalAtendimento().getId()));
		}
		
		if (raEncerramentoComando.getUnidadeOrganizacionalAtual() != null) {
			filtroRaEncerramentoComando.adicionarParametro(new ParametroSimples(FiltroRaEncerramentoComando.UNIDADE_ATUAL_ID,raEncerramentoComando.getUnidadeOrganizacionalAtual().getId()));
		}
		
		if (raEncerramentoComando.getUnidadeOrganizacionalSuperior() != null) {
			filtroRaEncerramentoComando.adicionarParametro(new ParametroSimples(FiltroRaEncerramentoComando.UNIDADE_SUPERIOR_ID,raEncerramentoComando.getUnidadeOrganizacionalSuperior().getId()));
		}
		
		//segunda pesquisa para ver com os parâmetros não obrigatórios. 
		colecaoComandoEncerramentoRA = getControladorUtil().pesquisar(filtroRaEncerramentoComando, RaEncerramentoComando.class.getName());
		// [FS0008] - Verificar existência de comando para os mesmos parâmetros
		if (colecaoComandoEncerramentoRA != null && !colecaoComandoEncerramentoRA.isEmpty()) {
			throw new ControladorException("atencao.comando.ja.existente");
		}
		
		//Seta os campos para inserir o comando
		raEncerramentoComando.setTempoComando(new Date());
		raEncerramentoComando.setTempoRealizacao(null);
		raEncerramentoComando.setQuantidadeOsEncerradas(null);
		raEncerramentoComando.setQuantidadeRasEncerradas(null);
		raEncerramentoComando.setUltimaAlteracao(new Date());
		
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//		Operacao.OPERACAO_RESOLUCAO_DIRETORIA_INSERIR,
//		new UsuarioAcaoUsuarioHelper(usuarioLogado,
//		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//		
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_RESOLUCAO_DIRETORIA_INSERIR);
//		
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);
//		
//		raEncerramentoComando.setOperacaoEfetuada(operacaoEfetuada);
//		raEncerramentoComando.adicionarUsuario(usuarioLogado,
//		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacao.registrarOperacao(raEncerramentoComando);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		
		//Inclui o comando na tabela RA_ENCERRAMENTO_COMANDO
		Integer id = (Integer) getControladorUtil().inserir(raEncerramentoComando);
		
		raEncerramentoComando.setId(id);
		
		if (colecaoEspecificacoes != null && !colecaoEspecificacoes.isEmpty()) {
			for (SolicitacaoTipoEspecificacao especificacao : colecaoEspecificacoes) {
				RaEncerramentoComandoEspecificacoes raEncerramentoComandoEspecificacoes = new RaEncerramentoComandoEspecificacoes();
				raEncerramentoComandoEspecificacoes.setRaEncerramentoComando(raEncerramentoComando);
				raEncerramentoComandoEspecificacoes.setSolicitacaoTipoEspecificacao(especificacao);
				raEncerramentoComandoEspecificacoes.setUltimaAlteracao(new Date());
				
				getControladorUtil().inserir(raEncerramentoComandoEspecificacoes);
			}
		}
		
		return id;
		
	}
	
	
	/**
	 * Este caso de uso permite obter o telefone do 
	 * solicitante de um registro de atendimento.
	 *
	 * [UC0719] Obter Telefone do Solicitante do RA
	 *
	 * @author Pedro Alexandre
	 * @date 26/12/2007
	 *
	 * @param idRASolicitante
	 * @return
	 * @throws ControladorException
	 */
	public String obterTelefoneSolicitanteRA(Integer idRASolicitante) throws ControladorException {
		
		String retorno = null;
		
		Object[] dadosTelefone = null;
		
		/* variáveis que vai armazenar os dados do telefone */
		String ddd = null;
		String fone = null;
		String ramal = null;
		try {
			Object[] dadosSolicitanteRegistroAtendimento = null;
			
			if (idRASolicitante != null) {
				
				/* recupera os dados da RA para verificar qual o tipo de solicitante */
				dadosSolicitanteRegistroAtendimento = repositorioRegistroAtendimento.pesquisarTipoSolicitanteRA(idRASolicitante);
				
				if (dadosSolicitanteRegistroAtendimento != null) {
					String nomeSolicitante = (String) dadosSolicitanteRegistroAtendimento[0];
					Integer idCliente = (Integer) dadosSolicitanteRegistroAtendimento[1];
					Integer idUnidade = (Integer) dadosSolicitanteRegistroAtendimento[2];
					
					/* caso o solicitante seja um cliente */
					if (idCliente != null) {
						//recupera os dados do telefone do cliente
						dadosTelefone = this.repositorioRegistroAtendimento.pesquisarTelefoneSolicitanteRACliente(idCliente);
						
						/* caso os dados não esteja vazio recupera todas as informações */
						if(dadosTelefone != null){
							if(dadosTelefone[0] != null){
								ddd = (String) dadosTelefone[0];
							}else{
								ddd = "";
							}
							
							if(dadosTelefone[1] != null){
								fone = (String) dadosTelefone[1];
							}else{
								fone = "";
							}
							
							if(dadosTelefone[2] != null){
								ramal = (String) dadosTelefone[2];
							}else{
								ramal = "";
							}
							
							// monta o nº do telefone
							retorno = ddd + fone + ramal;
							
						}else{
							return retorno;
						}
						/* caso seja uma unidade organizacional o telefone é nulo */
					} else if (idUnidade != null) {
						return retorno ;
						/* caso o solicitante não seja um cliente nem uma unidade organizacional */
					} else if(nomeSolicitante != null && !nomeSolicitante.trim().equals("")){
						/* recupera os dados do telefone do solicitante */
						dadosTelefone = this.repositorioRegistroAtendimento.pesquisarTelefoneSolicitanteRASolicitante(idRASolicitante);
						
						if(dadosTelefone != null){
							if(dadosTelefone[0] != null){
								ddd = (Short) dadosTelefone[0] + "";
							}else{
								ddd = "";
							}
							
							if(dadosTelefone[1] != null){
								fone = (String) dadosTelefone[1];
							}else{
								fone = "";
							}
							
							if(dadosTelefone[2] != null){
								ramal = (String) dadosTelefone[2];
							}else{
								ramal = "";
							}
							
							// monta o nº do telefone
							retorno = ddd + fone + ramal;
						}else{
							return retorno;
						}
					}
				}
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * Seleciona as RA's para o comando de encerramento informado
	 *
	 * [SB0001 - Selecionar Registro de Atendimento]	
	 *
	 * @author Pedro Alexandre
	 * @date 11/06/2008
	 *
	 * @param raEncerramentoComando
	 * @return
	 * @throws ControladorException
	 */
	private Collection<RegistroAtendimento> selecionarRegistroAtendimento(RaEncerramentoComando raEncerramentoComando, Integer idLocalidade) throws ControladorException {
		
		Collection<RegistroAtendimento> retorno = new ArrayList<RegistroAtendimento>();
		Collection<Object[]> colecaoDadosRA = null;
		
		try {
			
			Collection<Integer> idsEspecificacoes = repositorioRegistroAtendimento.pesquisarIdsEspecificacoesRAEncerramentoComando(raEncerramentoComando.getId());
			
			UnidadeOrganizacional unidadeAtual = raEncerramentoComando.getUnidadeOrganizacionalAtual();
			UnidadeOrganizacional unidadeSuperior = raEncerramentoComando.getUnidadeOrganizacionalSuperior();
			
			if(unidadeAtual == null && unidadeSuperior == null){
				colecaoDadosRA = (Collection<Object[]>) repositorioRegistroAtendimento.pesquisarRAExecutarComandoEncerramento(raEncerramentoComando, idLocalidade, idsEspecificacoes);
			}else if(unidadeAtual != null){
				colecaoDadosRA = (Collection<Object[]>) repositorioRegistroAtendimento.pesquisarRAExecutarComandoEncerramento(raEncerramentoComando, unidadeAtual.getId(), idLocalidade, idsEspecificacoes);
			}else if(unidadeSuperior != null){
				colecaoDadosRA = (Collection<Object[]>) repositorioRegistroAtendimento.pesquisarRAExecutarComandoEncerramento(raEncerramentoComando, unidadeSuperior.getId(), idLocalidade, idsEspecificacoes);
				
				Collection colecaoIdsUnidadesSubordinadas = this.pesquisarUnidadesSubordinadas(unidadeSuperior.getId());
				if(colecaoIdsUnidadesSubordinadas != null && !colecaoIdsUnidadesSubordinadas.isEmpty()){
					this.selecionarRegistroAtendimentoPorUnidadeSuperior(raEncerramentoComando,colecaoIdsUnidadesSubordinadas,colecaoDadosRA, idLocalidade, idsEspecificacoes);
				}
			}
			
			retorno = this.montarRAExecutarComandoEncerramentoRA(colecaoDadosRA);
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * Método responsável pelo encerramento dos RAs
	 * 
	 * @author Rafael Corrêa, Pedro Alexandre
	 * @date 28/01/2007, 11/06/2008
	 * 
	 * @param raEncerramentoComando
	 * @param idFuncionalidadeIniciada
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void executarComandoEncerramentoRA(RaEncerramentoComando raEncerramentoComando, int idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException {
		
		int idUnidadeIniciada = 0;
		
		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE, idLocalidade);
		
		try {
			//[SB0001] - Selecionar Registro de Atendimento
			Collection<RegistroAtendimento> colecaoRegistrosAtendimento = this.selecionarRegistroAtendimento(raEncerramentoComando, idLocalidade);
			
			int qtdeOsEncerradas = 0;
			
			if (colecaoRegistrosAtendimento != null	&& !colecaoRegistrosAtendimento.isEmpty()) {
				
				for (RegistroAtendimento ra : colecaoRegistrosAtendimento) {
					// Encerra a RA e retorna a quantidade de OS associadas a ele
					qtdeOsEncerradas = qtdeOsEncerradas + this.encerrarRAComando(raEncerramentoComando, ra);
				}
			}
			
			FiltroRaEncerramentoComando filtroRaEncerramentoComando = new FiltroRaEncerramentoComando();
			filtroRaEncerramentoComando.adicionarParametro(new ParametroSimples(FiltroRaEncerramentoComando.ID,raEncerramentoComando.getId()));
			Collection<RaEncerramentoComando> colRaEncerramentoComando = getControladorUtil().pesquisar(filtroRaEncerramentoComando, RaEncerramentoComando.class.getName());
			
			RaEncerramentoComando raEncerramentoComandoNaBase =(RaEncerramentoComando) Util.retonarObjetoDeColecao(colRaEncerramentoComando); 
			if(raEncerramentoComandoNaBase.getQuantidadeRasEncerradas() != null){
				raEncerramentoComando.setQuantidadeRasEncerradas(colecaoRegistrosAtendimento.size() + raEncerramentoComandoNaBase.getQuantidadeRasEncerradas());
			}else{
				raEncerramentoComando.setQuantidadeRasEncerradas(colecaoRegistrosAtendimento.size());
			}
			
			if(raEncerramentoComandoNaBase.getQuantidadeOsEncerradas() != null){
				raEncerramentoComando.setQuantidadeOsEncerradas(qtdeOsEncerradas + raEncerramentoComandoNaBase.getQuantidadeOsEncerradas()) ;
			}else{
				raEncerramentoComando.setQuantidadeOsEncerradas(qtdeOsEncerradas) ;
			}
			
			raEncerramentoComando.setTempoRealizacao(new Date());
			
			getControladorUtil().atualizar(raEncerramentoComando);
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * @author Rafael Corrêa, Pedro Alexandre
	 * @date 28/01/2007, 11/06/2008
	 *
	 * @param raEncerramentoComando
	 * @param idRA
	 * @return
	 * @throws ControladorException
	 */
	private int encerrarRAComando(RaEncerramentoComando raEncerramentoComando, RegistroAtendimento ra) throws ControladorException {
		
		int qtdeOsEncerradas = 0;
		
		ra.setAtendimentoMotivoEncerramento(raEncerramentoComando.getAtendimentoMotivoEncerramento());
		ra.setDataEncerramento(new Date());
		ra.setParecerEncerramento("RA encerrado por meio do comando de encerramento " + raEncerramentoComando.getId());
		
		RegistroAtendimentoUnidade rau = new RegistroAtendimentoUnidade();
		
		Usuario usuario = null;
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.USUARIO_TIPO_ID, UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR));
		
		Collection colecaoUsuarios = getControladorUtil().pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {
			usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarios);
		}
		
		// Seta os dados do Registro Atendimento Unidade
		rau.setUsuario(usuario);
		rau.setRegistroAtendimento(ra);
		rau.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
		rau.setUltimaAlteracao(new Date());
		
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		
		rau.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		
		try {
			qtdeOsEncerradas = repositorioRegistroAtendimento.pesquisarQuantidadeOSEmAbertoAssociadasRA(ra.getId());
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		if (ra.getSolicitacaoTipoEspecificacao().getDebitoTipo() != null &&	ra.getSolicitacaoTipoEspecificacao().getValorDebito() != null){
			
			this.encerrarRegistroAtendimento(ra, rau, usuario, ra.getSolicitacaoTipoEspecificacao().getDebitoTipo().getId(),
					ra.getSolicitacaoTipoEspecificacao().getValorDebito(), 1, "100", false,null,false);
		}
		else{
			this.encerrarRegistroAtendimento(ra, rau, usuario, null, null, null, null, false,null,false);
		}
		
		return qtdeOsEncerradas;
	}
	
	/**
	 * Retorna o valor de controladorCadastro
	 * 
	 * @return O valor de controladorCadastro
	 */
	private ControladorGerencialCadastroLocal getControladorGerencialCadastro() {
		
		ControladorGerencialCadastroLocalHome localHome = null;
		ControladorGerencialCadastroLocal local = null;
		
		// pega a instância do ServiceLocator.
		
		ServiceLocator locator = null;
		
		try {
			locator = ServiceLocator.getInstancia();
			
			localHome = (ControladorGerencialCadastroLocalHome) locator
			.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_CADASTRO_SEJB);
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
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Calcular valor da prestação
	 * 
	 * Posição[0] = valorPrestacao
	 * Posição[1] = taxaJurosFinanciamento
	 * 
	 * autor: Raphael Rossiter
	 * data: 07/03/2008
	 */
	public BigDecimal[] calcularValorPrestacaoAtendimentoPublico(short indicadorCobrarJuros, 
			BigDecimal valorDebito, Integer qtdeParcelas, String percentualCobranca)
	throws ControladorException{
		
		BigDecimal[] retorno = new BigDecimal[2];
		
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		BigDecimal  taxaJurosFinanciamento = null; 
		
		if(indicadorCobrarJuros == ConstantesSistema.SIM.shortValue() && 
				qtdeParcelas.intValue() > 1){
			
			taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
		}else{
			taxaJurosFinanciamento = new BigDecimal(0);
		}
		
		BigDecimal valorPrestacao = new BigDecimal("0.00");
		if(taxaJurosFinanciamento != null){
			
			valorDebito = Util.calcularValorDebitoComPorcentagem(valorDebito, percentualCobranca);
			
			//[UC0186] - Calcular Prestação
			valorPrestacao =this.getControladorFaturamento().calcularPrestacao(
					taxaJurosFinanciamento, qtdeParcelas, valorDebito, 
					new BigDecimal("0.00"));
			
			valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		
		retorno[0] = valorPrestacao;
		retorno[1] = taxaJurosFinanciamento;
		
		return retorno;
	}
	
	
	/**
	 * [UC0XXX] Gerar Débito do Registro de Atendimento
	 * 
	 * [FS0001] Verificar Existência do Registro de Atendimento 
	 * 
	 * [FS0002] Verificar Existência do Tipo de Débito 
	 * [FS0003] Validar Valor do Débito 
	 * [FS0004] Validar Quantidade de Parcelas
	 * 
	 * @author Raphael Rossiter
	 * @date 10/03/2008
	 * 
	 * @param ordemServicoId
	 * 
	 * @throws ControladorException
	 */
	public void gerarDebitoRegistroAtendimento(Integer idRegistroAtendimento,
			Integer idDebitoTipo, BigDecimal valorDebito, int qtdeParcelas, String percentualCobranca, Usuario usuarioLogado) 
	throws ControladorException {
		
		
		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = 
			this.obterDadosRegistroAtendimento(idRegistroAtendimento);
		
		Imovel imovel = null;
		if (registroAtendimentoHelper == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.os_inexistente");
		} else {
			imovel = registroAtendimentoHelper.getRegistroAtendimento().getImovel();
			
			if (imovel == null) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.os_sem_imovel");
			}
		}
		
		/*
		 * [FS0002] Verificar Existência do Tipo de Débito 
		 * [FS0003] Validar Valor do Débito 
		 * [FS0004] Validar Quantidade de Parcelas
		 */
		DebitoTipo debitoTipo = getControladorOrdemServico().validacaoGerarDebito(idDebitoTipo, valorDebito, 
				qtdeParcelas);
		
		
		//Calcula o valor da prestação
		BigDecimal[] valorCalculo = this.calcularValorPrestacaoAtendimentoPublico(
				registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao()
				.getIndicadorCobrarJuros(), valorDebito, qtdeParcelas, percentualCobranca);
		
		BigDecimal valorPrestacao = valorCalculo[0];
		BigDecimal taxaJurosFinanciamento = valorCalculo[1];
		
		//Recalcula o valor total do débito
		valorDebito = valorPrestacao.multiply(new BigDecimal(qtdeParcelas));
		
		
		//------------------------------------------------------------------------------------------------
		// Insere débito a cobrar geral
		DebitoACobrarGeral debitoACobrarGeral = getControladorOrdemServico().inserirDebitoACobrarGeral();
		
		// Insere débito a cobrar
		DebitoACobrar debitoACobrar = getControladorOrdemServico().inserirDebitoACobrar(valorDebito,
				qtdeParcelas, null, debitoTipo, debitoACobrarGeral, taxaJurosFinanciamento, 
				registroAtendimentoHelper.getRegistroAtendimento(), usuarioLogado);
		
		// Recupera Categorias por Imóvel
		Collection<Categoria> colecaoCategoria = this.getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);
		
		// Recupera Valores por Categorias
		Collection<BigDecimal> colecaoValoresCategorias = this.getControladorImovel().obterValorPorCategoria(colecaoCategoria, valorDebito);
		
		// Insere débito a cobrar por categoria
		getControladorOrdemServico().inserirDebitoACobrarCategoria(colecaoCategoria,colecaoValoresCategorias, debitoACobrar);
		//------------------------------------------------------------------------------------------------
		
	}
	
	/**
	 * Tramitar Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 30/05/2008
	 *
	 * @param tramite
	 * @param dataConcorrenciaRA
	 * @param usuario
	 * @param colecaoOrdemServicoPavimento
	 * @param colecaoOrdemServicoMovimento
	 */
	public void tramitarRAExportandoOSPrestadoras(
			Tramite tramite, Date dataConcorrenciaRA,
			Usuario usuario, 
			Collection colecaoOrdemServicoPavimento, Collection colecaoOrdemServicoMovimento) 
	throws ControladorException {
		
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_REGISTRO_ATENDIMENTO_TRAMITAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REGISTRO_ATENDIMENTO_TRAMITAR);
		
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// [UC0107] -Fim- Registrar Transação
		
		
		// Faz as validações de tramitação
		this.validarTramitacao(tramite, usuario);
		// Insere Trâmite
		this.tramitar(tramite, dataConcorrenciaRA);
		
		// Setando Operação
		tramite.setOperacaoEfetuada(operacaoEfetuada);
		tramite.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(tramite);			
		
		//insere a coleção de OSMovimento retornado pelo 
		//[UC0720 - Exportar Ordem Serviço Prestadoras]
		inserirOSMovimentoAtualizandoIndicadorEncerramentoAutomaticoOS(colecaoOrdemServicoMovimento);
		
		//Insere as OrdensServicoPavimento com os dados de pavimento
		inserirOSPavimento(colecaoOrdemServicoPavimento);
		
	}
	
	
	/**
	 * Tramitar Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/06/2008
	 *
	 * @param colecaoOrdemServicoMovimento
	 */
	public void inserirOSMovimentoAtualizandoIndicadorEncerramentoAutomaticoOS(
			Collection colecaoOrdemServicoMovimento) 
	throws ControladorException {
		
		if (colecaoOrdemServicoMovimento != null &&
				!colecaoOrdemServicoMovimento.isEmpty()){
			
			Iterator iterOrdemServicoMovimento = colecaoOrdemServicoMovimento.iterator();
			while (iterOrdemServicoMovimento.hasNext()) {
				OrdemServicoMovimento ordemServicoMovimento = (OrdemServicoMovimento) iterOrdemServicoMovimento.next();
				
				getControladorUtil().inserir(ordemServicoMovimento);
				
				//assinala ordem de serviço para encerramento automático
				getControladorOrdemServico().atualizarIndicadorEncerramentoAutomaticoOS
				(ConstantesSistema.SIM, ordemServicoMovimento.getId());
				
			}
		}
		
	}
	
	
	/**
	 * Tramitar Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/06/2008
	 *
	 * @param colecaoOrdemServicoPavimento
	 */
	public void inserirOSPavimento(
			Collection colecaoOrdemServicoPavimento) 
	throws ControladorException {
		
		if (colecaoOrdemServicoPavimento != null &&
				!colecaoOrdemServicoPavimento.isEmpty()){
			
			Iterator iterOrdemServicoPavimento = colecaoOrdemServicoPavimento.iterator();
			while (iterOrdemServicoPavimento.hasNext()) {
				OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) iterOrdemServicoPavimento.next();
				getControladorOrdemServico().inserirOrdemServicoPavimento(ordemServicoPavimento);
			}
		}
		
	}
	
	
	/**
	 * [UC0503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/06/2008
	 *
	 * @param tramite
	 * @param dataConcorrenciaRA
	 * @param usuario
	 * @param colecaoOrdemServicoPavimento
	 * @param colecaoOrdemServicoMovimento
	 */
	public void tramitarConjuntoRAExportandoOSPrestadoras(
			Collection tramites, Collection colecaoOrdemServicoPavimento,
			Collection colecaoOrdemServicoMovimento) 
	throws ControladorException {
		
		//insere a coleção de OSMovimento retornado pelo 
		//[UC0720 - Exportar Ordem Serviço Prestadoras]
		inserirOSMovimentoAtualizandoIndicadorEncerramentoAutomaticoOS(colecaoOrdemServicoMovimento);
		
		//Insere as OrdensServicoPavimento com os dados de pavimento
		inserirOSPavimento(colecaoOrdemServicoPavimento);
		
		//[SB0003]Incluir o Tramite
		tramitarConjuntoRA(tramites);
	}
	
	/**
	 * [UC0427] Tramitar Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/06/2008
	 * 
	 * @throws ControladorException
	 */
	public void atualizarUnidadeOrganizacionalAtualRA(Integer idUnidadeOrganizacionalAtual,
			Integer idRA)throws ControladorException {
		try {
			repositorioRegistroAtendimento.atualizarUnidadeOrganizacionalAtualRA(
					idUnidadeOrganizacionalAtual,idRA);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * Seleciona as RA's para o comando de encerramento informado
	 *
	 * [SB0001 - Selecionar Registro de Atendimento]	
	 *
	 * @author Pedro Alexandre
	 * @date 11/06/2008
	 *
	 * @param raEncerramentoComando
	 * @return
	 * @throws ControladorException
	 */
	private Collection<Integer> pesquisarUnidadesSubordinadas(Integer idUnidadeSuperior) throws ControladorException {
		
		Collection<Integer> retorno = new ArrayList();
		
		try {
			retorno = repositorioRegistroAtendimento.pesquisarIdsUnidadesSubordinadas(idUnidadeSuperior);
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return retorno;
	}
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @param retorno
	 * @param colecaoDadosRA
	 */
	private Collection<RegistroAtendimento> montarRAExecutarComandoEncerramentoRA(Collection<Object[]> colecaoDadosRA) {
		
		Collection<RegistroAtendimento> retorno = new ArrayList();
		
		if (colecaoDadosRA != null && !colecaoDadosRA.isEmpty()) {
			
			for (Object[] dadosRA : colecaoDadosRA) {
				
				RegistroAtendimento ra = new RegistroAtendimento();
				
				// ID da RA
				if (dadosRA[0] != null) {
					ra.setId((Integer) dadosRA[0]);
				}
				
				// Id do Tipo de Especificação da Solicitação
				if (dadosRA[1] != null) {
					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao.setId((Integer) dadosRA[1]);
					BigDecimal valorDebito = null;
					if (dadosRA[2] != null) {
						valorDebito = (BigDecimal) dadosRA[2];
						solicitacaoTipoEspecificacao.setValorDebito(valorDebito);
					}
					
					//Tipo de Débito
					if (dadosRA[5] != null) {
						DebitoTipo debitoTipo = new DebitoTipo();
						debitoTipo.setId((Integer) dadosRA[5]);
						solicitacaoTipoEspecificacao.setDebitoTipo(debitoTipo);
					}
					
					ra.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
				}
				
				
				// RA Duplicidade
				if (dadosRA[3] != null) {
					RegistroAtendimento raDuplicidade = new RegistroAtendimento();
					raDuplicidade.setId((Integer) dadosRA[3]);
					ra.setRegistroAtendimentoDuplicidade(raDuplicidade);
				}
				
				// Data Última Alteração
				if (dadosRA[4] != null) {
					ra.setUltimaAlteracao((Date) dadosRA[4]);
				}
				
				retorno.add(ra);
			}
		}
		
		return retorno;
	}
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 17/06/2008
	 *
	 * @param raEncerramentoComando
	 * @param colecaoIdsSubordinadas
	 * @param dadosRa
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	private void selecionarRegistroAtendimentoPorUnidadeSuperior(RaEncerramentoComando raEncerramentoComando, Collection<Integer> colecaoIdsSubordinadas, Collection<Object[]> dadosRa, Integer idLocalidade, Collection<Integer> idsEspecificacoes) throws ControladorException {
		
		try {
			
			Collection<Integer> idsTemp = null;
			Collection<Object[]> dadosRATemp = null;
			
			for(Integer idUnidadeSubordinada : colecaoIdsSubordinadas){
				idsTemp = this.pesquisarUnidadesSubordinadas(idUnidadeSubordinada);
				
				if(idsTemp != null && !idsTemp.isEmpty()){
					this.selecionarRegistroAtendimentoPorUnidadeSuperior(raEncerramentoComando,idsTemp,dadosRa, idLocalidade, idsEspecificacoes);
				}else{
					dadosRATemp = repositorioRegistroAtendimento.pesquisarRAExecutarComandoEncerramento(raEncerramentoComando,idUnidadeSubordinada, idLocalidade, idsEspecificacoes);
					
					if(dadosRATemp != null && !dadosRATemp.isEmpty()){
						dadosRa.addAll(dadosRATemp);
					}
				}
			}
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
	}
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * Pesquisa a coleção de ids das localidades que possuem Registro de Atendimento
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesRA() throws ControladorException {
		
		Collection<Integer> retorno = null;
		
		try {
			retorno = repositorioRegistroAtendimento.pesquisarIdsLocalidadesRA();
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0457] Encerra Ordem de Serviço
	 *
	 * @author Vivianne Sousa
	 * @date 11/07/2008
	 *
	 * @param idRA
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterUnidadeOrigemDoUltimoTramiteRA(Integer idRA)  throws ControladorException {
		
		Integer retorno = null;
		
		try {
			retorno = repositorioRegistroAtendimento.obterUnidadeOrigemDoUltimoTramiteRA(idRA);
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0496] Filtrar Relatorio de Gestao do Registro de atendimento
	 * 
	 * @author Victor Cisneiros
	 * @date 20/06/2008
	 */
	public List<GestaoRegistroAtendimentoHelper> filtrarGestaoRA(
			FiltrarGestaoRAHelper filtro) throws ControladorException {
		try {
			return repositorioRegistroAtendimento.filtrarGestaoRA(filtro);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
	 * 
	 * @author Victor Cisneiros
	 * @date 14/11/2008
	 */
	public List<GestaoRegistroAtendimentoHelper> filtrarRelatorioResumoSolicitacoesRAPorUnidade(
			FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper filtro) throws ControladorException {
		try {
			return repositorioRegistroAtendimento.filtrarRelatorioResumoSolicitacoesRAPorUnidade(filtro);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param FileItem
	 * @throws ControladorException
	 */
	public void validarRegistroAtendimentoAnexos(FileItem arquivoAnexo) throws ControladorException{
		
		if (arquivoAnexo == null || arquivoAnexo.get().length == 0){
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.campo.informado", null, "Arquivo");
		}
		
		//Só poderão ser informados arquivos com as extensões: JPG, DOC ou PDF
		String arquivoExtensao = Util.obterExtensaoDoArquivo(arquivoAnexo);
		
		if (!arquivoExtensao.equalsIgnoreCase("JPG") &&
			!arquivoExtensao.equalsIgnoreCase("DOC") &&
			!arquivoExtensao.equalsIgnoreCase("PDF")){
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.arquivo_invalido");
		}
		
		/**TODO: COSANPA
		 * 
		 * Mantis 607 - Aumentar o limite máximo do anexos em RAs
		 * @author Wellington Rocha
		 * @date 20/07/2012*/
		//O tamanho máximo para o arquivo será de 400kb
		if (arquivoAnexo.getSize() > 409600){
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.imagem_tamanho_maximo");
		}
	}
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param Integer
	 * @param Collection
	 * @throws ControladorException
	 */
	public void inserirRegistroAtendimentoAnexo(Integer idRegistroAtendimento, 
			Collection colecaoRegistroAtendimentoAnexo) throws ControladorException{
		
		if (idRegistroAtendimento != null &&
			colecaoRegistroAtendimentoAnexo != null && !colecaoRegistroAtendimentoAnexo.isEmpty()){
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
			
			while (it.hasNext()){
				
				registroAtendimentoAnexo = (RegistroAtendimentoAnexo) it.next();
				
				//REGISTRO_ATENDIMENTO
				RegistroAtendimento registroAtendimento = new RegistroAtendimento();
				registroAtendimento.setId(idRegistroAtendimento);
				
				registroAtendimentoAnexo.setRegistroAtendimento(registroAtendimento);
				
				//ÚLTIMA_ALTERAÇÃO
				registroAtendimentoAnexo.setUltimaAlteracao(new Date());
				
				//INSERINDO ANEXO PARA O REGISTRO DE ATENDIMENTO
				this.getControladorUtil().inserir(registroAtendimentoAnexo);
			}
		}
	}
	
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 06/08/2009
	 * 
	 * @param RegistroAtendimento
	 * @param Collection
	 * @throws ControladorException
	 */
	public void atualizarRegistroAtendimentoAnexo(RegistroAtendimento registroAtendimento, 
			Collection colecaoRegistroAtendimentoAnexo) throws ControladorException{
		
		if (registroAtendimento != null &&
			colecaoRegistroAtendimentoAnexo != null && !colecaoRegistroAtendimentoAnexo.isEmpty()){
			
			//VERIFICANDO A EXISTÊNCIA DE ANEXOS JÁ CADASTRADOS
			FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();
			
			filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,
			registroAtendimento.getId()));

			Collection colecaoRegistroAtendimentoAnexoAtual = this.getControladorUtil()
			.pesquisar(filtroRegistroAtendimentoAnexo, RegistroAtendimentoAnexo.class.getName());
			
			if (colecaoRegistroAtendimentoAnexoAtual != null && !colecaoRegistroAtendimentoAnexoAtual.isEmpty()){
				
				//ANEXOS QUE ESTÃO CADASTRADOS ATUALMENTE
				Iterator itAtual = colecaoRegistroAtendimentoAnexoAtual.iterator();
				RegistroAtendimentoAnexo registroAtendimentoAnexoAtual = null;
				
				while (itAtual.hasNext()){
					
					registroAtendimentoAnexoAtual = (RegistroAtendimentoAnexo) itAtual.next();
					
					if (colecaoRegistroAtendimentoAnexo.contains(registroAtendimentoAnexoAtual)){
						
						//ATUALIZANDO
						Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
						RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
						
						while (it.hasNext()){
							
							registroAtendimentoAnexo = (RegistroAtendimentoAnexo) it.next();
							
							if (registroAtendimentoAnexo.getId().equals(
								registroAtendimentoAnexoAtual.getId())){
								
								//REGISTRO_ATENDIMENTO
								registroAtendimentoAnexoAtual.setRegistroAtendimento(registroAtendimento);
								
								//OBSERVAÇÃO
								registroAtendimentoAnexoAtual.setDescricaoDocumento(registroAtendimentoAnexo.getDescricaoDocumento());
								
								//ÚLTIMA_ALTERAÇÃO
								registroAtendimentoAnexoAtual.setUltimaAlteracao(new Date());
								
								//ATUALIZANDO ANEXO PARA O REGISTRO DE ATENDIMENTO
								this.getControladorUtil().atualizar(registroAtendimentoAnexoAtual);
								
								break;
							}
						}
						
						colecaoRegistroAtendimentoAnexo.remove(registroAtendimentoAnexoAtual);
					}
					else {
						
						//REMOVENDO
						this.getControladorUtil().remover(registroAtendimentoAnexoAtual);
					}
				}
				
				if (colecaoRegistroAtendimentoAnexo != null && !colecaoRegistroAtendimentoAnexo.isEmpty()){
					
					//INSERINDO TODOS OS ANEXOS INFORMADOS
					this.inserirRegistroAtendimentoAnexo(registroAtendimento.getId(), 
					colecaoRegistroAtendimentoAnexo);
				}
			}
			else{
				
				//INSERINDO TODOS OS ANEXOS INFORMADOS
				this.inserirRegistroAtendimentoAnexo(registroAtendimento.getId(), 
				colecaoRegistroAtendimentoAnexo);
			}
		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @throws ControladorException
	 */
	public String obterProtocoloAtendimento() throws ControladorException{
		
		String protocoloAtendimento = null;
		
		//ANO CORRENTE
		Date dataCorrente = new Date();
		String anoCorrente = String.valueOf(Util.getAno(dataCorrente));
		
		//ORIGEM
		String origem = "1";
		
		//SEQUENCIAL
		Integer sequencial = null;
		
		try {
			
			sequencial = repositorioRegistroAtendimento.pesquisarSequencialProtocoloAtendimento();
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		/*
		 * O sistema calcula o número do protocolo da seguinte forma: 
		 * ((Ano corrente (AAAA) + Origem (O) + Sequência Protocolo + 1 (NNNNNNNNN))
		 */
		String sequencialFormatado = Util.adicionarZerosEsquedaNumero(9, String.valueOf(sequencial));
		
		protocoloAtendimento = anoCorrente + origem + sequencialFormatado;
		
		
		return protocoloAtendimento;
	}
	
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * Pesquisar o número de protocolo do RA
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String pesquisarProtocoloAtendimentoPorRA(Integer idRegistroAtendimento) 
		throws ControladorException {
		
		String protocoloAtendimento = null;
		
		if (idRegistroAtendimento != null){
			
			try {
				
				protocoloAtendimento = repositorioRegistroAtendimento.pesquisarProtocoloAtendimentoPorRA(
				idRegistroAtendimento);
				
			} catch (Exception e) {
				sessionContext.setRollbackOnly();
				throw new EJBException(e);
			}
		}
		
		return protocoloAtendimento;
	}
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * [FS0006] Valida Data [FS0007] Valida Hora [FS0008] Valida Unidade Destino
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void validarConjuntoTramitacaoIntegracao(String[] ids,
			Date dataHoraTramitacao, Integer idUnidadeDestino, Usuario usuario)
			throws ControladorException {

		// [FS0006] Validar Data
		// [FS0007] Validar Hora
		Date dataCorrente = new Date();
		int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(
				dataHoraTramitacao, dataCorrente);

		if (qtdeDias < 0) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tramitar_ra_data_maior_que_atual", null, Util
							.formatarData(dataCorrente));

		} else if (Util.datasIguais(dataHoraTramitacao, dataCorrente)) {
			if (Util.compararHoraMinuto(Util
					.formatarHoraSemData(dataHoraTramitacao), Util
					.formatarHoraSemData(dataCorrente), ">")) {

				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.tramitar_ra_hora_maior_que_atual", null, Util
								.formatarHoraSemData(dataCorrente));
			}
		}

		UnidadeOrganizacional unidadeDestino = this.getControladorUnidade()
				.pesquisarUnidadeOrganizacional(idUnidadeDestino);

		if (ids != null && ids.length != 0) {
			Integer idRa = null;
			for (int i = 0; i < ids.length; i++) {
				try {

					String[] idsSelecionados = ids[i].split(";");
					idRa = Integer.parseInt(idsSelecionados[0]);
					Integer idUnidadeOrigem = Integer
							.parseInt(idsSelecionados[1]);

					UnidadeOrganizacional unidadeOrigem = this
							.getControladorUnidade()
							.pesquisarUnidadeOrganizacional(idUnidadeOrigem);

					boolean possuiOSTerceirizado = false;

					Collection<OrdemServico> colecaoOrdemServico = obterOSRA(idRa);

					if (colecaoOrdemServico != null
							&& !colecaoOrdemServico.isEmpty()) {

						for (Iterator iter = colecaoOrdemServico.iterator(); iter
								.hasNext();) {

							OrdemServico element = (OrdemServico) iter.next();

							if ((new Short(element.getSituacao())).intValue() == OrdemServico.SITUACAO_PENDENTE
									.intValue()) {

								// [FS0008 Caso 4]
								if ((new Short(element.getServicoTipo()
										.getIndicadorTerceirizado()))
										.intValue() == 1) {
									possuiOSTerceirizado = true;
								}
							}
						}
					}

					// [FS0008] Validar Unidade Destino
					// Caso 1
					if (idUnidadeOrigem.intValue() == idUnidadeDestino
							.intValue()) {

						 
						 throw new ControladorException(
						 "atencao.tramitar_ra_unidade_origem_destino_iguais");
						
						 
					}

					try {
						Short idTarifaSocialRa = repositorioRegistroAtendimento
								.verificarIndicadorTarifaSocialRA(idRa);

						// Caso 2
						if ((new Short(unidadeDestino.getIndicadorTramite()))
								.intValue() == 2) {
							if (new Short(idTarifaSocialRa).intValue() == 2) {

								 
								 throw new ControladorException(
								 "atencao.tramitar_ra_unidade_destino_tramite");
								
							}
						}
						// alteracao adicionada por Anderson Italo
						// Adicionado validação para verificar indicador de uso
						// da unidadeDestino
						// data: 28/07/2009
						if ((new Short(unidadeDestino.getIndicadorUso()))
								.intValue() == 2) {
							 
							 throw new ControladorException(
							 "atencao.unidade_destino_inativa", null,
							 unidadeDestino.getId().toString());
							
						}
						// fim alteracao
					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}

					if (unidadeDestino.getUnidadeTipo() != null
							&& unidadeDestino.getUnidadeTipo().getCodigoTipo() != null) {

						if (unidadeDestino.getUnidadeTipo().getCodigoTipo()
								.equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {

							// Caso 3
							if (unidadeOrigem.getUnidadeTipo() == null
									|| unidadeOrigem.getUnidadeTipo()
											.getCodigoTipo() == null
									|| !unidadeOrigem
											.getUnidadeTipo()
											.getCodigoTipo()
											.equals(
													UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)) {

								 
								
								 throw new ControladorException(
								 "atencao.tramitar_ra_unidade_centralizadora");
							}
							// Caso 4
							if (!possuiOSTerceirizado) {
								 
								 throw new ControladorException(
								 "atencao.tramitar_ra_os_possui_terceiros",
								 null, idRa.toString());
								
							}
						}
						// Caso 5
						if (unidadeDestino
								.getUnidadeTipo()
								.getCodigoTipo()
								.equals(UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)
								&& !unidadeOrigem
										.getUnidadeTipo()
										.getCodigoTipo()
										.equals(
												UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA)) {
							Integer unidadeCentralizadora = 0;
							try {
								unidadeCentralizadora = repositorioRegistroAtendimento
										.pesquisarUnidadeCentralizadoraRa(idRa);
							} catch (ErroRepositorioException ex) {
								 
								ex.printStackTrace();
								 throw new
								 ControladorException("erro.sistema", ex);
								

							}
							if (unidadeCentralizadora != unidadeDestino.getId()
									.intValue()) {
								 
								 throw new ControladorException(
								 "atencao.tramitar_ra_unidade_origem_central");
								
							}
						}
					}

					// caso 6
					// caso a unidade atual do registro seja "terceirizada" e
					// o usuario não estiver lotado nesta unidade
					if (unidadeOrigem.getUnidadeTipo() != null
							&& unidadeOrigem.getUnidadeTipo().getCodigoTipo() != null) {

						if (unidadeOrigem.getUnidadeTipo().getCodigoTipo()
								.equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)
								&& !usuario.getUnidadeOrganizacional().getId()
										.equals(unidadeOrigem.getId())) {

							 
							
							 throw new
							 ControladorException("atencao.tramitar_ra_unidade_usuario");
						}
					}

					// adicionado por Vivianne Sousa, 29/10/2008
					// analista responsavel:Fabiola
					// [FS0011]Validar Tramite para terceira
					if (unidadeDestino.getUnidadeTipo() != null
							&& unidadeDestino.getUnidadeTipo().getCodigoTipo() != null
							&& unidadeDestino
									.getUnidadeTipo()
									.getCodigoTipo()
									.equals(
											UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {

						boolean existe = getControladorOrdemServico()
								.existeMaisDeUmaOrdemServicoPendente(idRa);

						if (existe) {

							
							
							throw new ControladorException("atencao.tramitar_ra_possui_mais_de_uma_os_pensente",
									null, idRa.toString());
						}
					}
				} catch (ControladorException e) {
					
					System.out.println(idRa +" "+ ConstantesAplicacao.get(e.getMessage(), e.getParametroMensagem().toArray(new String[e.getParametroMensagem().size()])));
					ids[i] = null;
					continue;
				}
			}

		}
	}
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 *  Caso o local da ocorrência seja um imóvel e 
	 *  o imóvel tenha um perfil de grande consumidor
	 *  (IPER_ICGRANDECONSUMIDOR da tabela IMOVEL_PERFIL com valor igual a 1 
	 *  com IPER_ID= IPER_ID  da tabela IMOVEL com IMOV_ID = IMOV_ID informado) 
	 *  e a unidade de tramite automático para grandes consumidores 
	 *  esteja preenchida (UNID_IDTRAMITEGRANDECONSUMIDOR da tabela SISTEMA_PARAMETRO)
	 *  preencher a unidade de tramite automático com a unidade informada e não permitir alterar. 
	 * 
	 * [FS0046] – Verificar Tramite de Grandes Consumidores.
	 * 
	 * @author Vivianne Sousa
	 * @date 28/12/2009
	 * 
	 */
	public UnidadeOrganizacional verificarTramiteGrandesConsumidores(
			Integer idImovel) throws ControladorException {
		
		
		
//		Integer idImovel = imovelString != null ? new Integer(imovelString) : null;
		
		
		UnidadeOrganizacional unidadeTramiteGrandeConsumidor = null;
		
		if(idImovel != null){
			
			ImovelPerfil imovelPerfil  = getControladorImovel().obterImovelPerfil(idImovel);
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema(); 
			
			if(imovelPerfil.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM) &&
					sistemaParametro.getUnidadeOrganizacionalTramiteGrandeConsumidor() != null){
				
				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
				filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, 
						sistemaParametro.getUnidadeOrganizacionalTramiteGrandeConsumidor().getId()));
				
				Collection pesquisa = Fachada.getInstancia().pesquisar(
						filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
				
//				if (pesquisa == null || pesquisa.isEmpty()) {
//					throw new ControladorException("atencao.pesquisa_inexistente", null, "Unidade Superior");
//				}
				
				unidadeTramiteGrandeConsumidor = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
				
			}
			
		}
	
		return unidadeTramiteGrandeConsumidor;
	}
	
	/**
	 * Método que gera o resumo dos registros de atendimentos Por Ano
	 * 
	 * Gerar Resumo dos Registro de Atendimentos Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 */
	public void gerarResumoRegistroAtendimentoPorAno(int idLocalidade,
			int idFuncionalidadeIniciada, Integer anoMesReferencia) throws ControladorException {
		
		int idUnidadeIniciada = 0;
		
		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.LOCALIDADE, idLocalidade);
		
		//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
		getControladorGerencialCadastro().excluirResumoGerencialC(getControladorUtil()
				.pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
					"atendimentopublico.un_resumo_registro_atendimento_ref_2010", "rera_amreferencia", 
						"loca_id", idLocalidade );				
		
		try {
			Integer dtAtual = Integer.decode(Util.recuperaDataInvertida(new Date()));
			
			Collection imoveisRegistroAtendimento = this.repositorioRegistroAtendimento
			.getImoveisResumoRegistroAtendimentoPorAno(idLocalidade, anoMesReferencia, dtAtual );
			
			List<ResumoRegistroAtendimentoPorAnoHelper> listaSimplificada = new ArrayList();
			List<UnResumoRegistroAtendimentoPorAno> listaResumoRegistroAtendimento = new ArrayList();
			
			Iterator imoveisRegistroAtendimentoIterator = imoveisRegistroAtendimento.iterator();
			
			Integer idUnidadeSolicitacao;
			Integer idUnidadeEncerramento;
			
			// pra cada objeto obter a categoria
			// caso ja tenha um igual soma a quantidade de registro de atendimentos
			while (imoveisRegistroAtendimentoIterator.hasNext()) {
				Object obj = imoveisRegistroAtendimentoIterator.next();
				
				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;
					
					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					
					ResumoRegistroAtendimentoPorAnoHelper helper = 
						new ResumoRegistroAtendimentoPorAnoHelper(
							(Integer) retorno[1], // idGerenciaRegional
							(Integer) retorno[2], // idUnidadeNegocio
							(Integer) retorno[3], // idLocalidade			
							(Integer) retorno[4], // idElo
							(Integer) retorno[5], // idSetorcomercial
//							(Integer) retorno[6], // idRota
//							(Integer) retorno[7], // idQuadra
							(Integer) retorno[6], // codigoSetorComercial
//							(Integer) retorno[9], // numeroQuadra
							(Integer) retorno[7],// perfilImovel
							(Integer) retorno[8],// idSituacaoLigacaoAgua
							(Integer) retorno[9],// idSituacaoLigacaoEsgoto
							(Integer) retorno[10],// idPerfuilLigacaiAgua	
							(Integer) retorno[11],// idPerfilLigacaoEsgoto											
							(Short)   retorno[12],// indicadorAtendimentoOnline
							(Integer) retorno[13],// idTipoSolicitacao
							(Integer) retorno[14],// idTipoEspecificacao
							(Integer) retorno[15],// idMeioSolicitacao
							(Integer) retorno[16],// quantidadeGerada
							(Integer) retorno[17],// quantidadePendentesNoPrazo
							(Integer) retorno[18],// quantidadePendentesForaPrazo
							(Integer) retorno[19],// quantidadeEncerradasNoPrazo
							(Integer) retorno[20],// quantidadeEncerradasForaPrazo
							(Integer) retorno[21],// quantidadeBloqueadas
							(Integer) retorno[22],// quantidadeGeradasNoMesPrazo
							(Integer) retorno[23]// quantidadeGeradasNoMesForaPrazo
//							(Short)   retorno[28]
							                  );// codigo Rota
					
					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do imóvel
					Integer idImovel = (Integer) retorno[0];
					Integer idRegistroAtendimento = (Integer) retorno[24];
					
					if(idImovel != null){					
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsfera( this.repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel( idImovel ) );
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoClienteResponsavel( this.repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel( idImovel ) );							
						
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
					}
					
					// Pesquisa a Unidade de Solicitacao
					idUnidadeSolicitacao = pesquisaUnidadeSolicitacaoRa(idRegistroAtendimento);
					helper.setIdUnidadeSolicitacao(idUnidadeSolicitacao);
					
					// Pesquisa a Unidade de Encerramento
					idUnidadeEncerramento = pesquisaUnidadeEncerramentoRa(idRegistroAtendimento);
					if (idUnidadeEncerramento != null) {
						helper.setIdUnidadeEncerramento(idUnidadeEncerramento);
					}
					
					// Caso loca_cdelo = 0 significa que
					// loca_cdelo = loca_id
					if (helper.getIdElo().equals(0)) {
						helper.setIdElo(helper.getIdLocalidade());
					}
					
					if (helper.getQuantidadePendentesNoPrazo() == null) {
						helper.setQuantidadePendentesNoPrazo(0);
					}
					if (helper.getQuantidadePendentesForaPrazo() == null) {
						helper.setQuantidadePendentesForaPrazo(0);
					}
					
					if (helper.getQuantidadeEncerradasNoPrazo() == null) {
						helper.setQuantidadeEncerradasNoPrazo(0);
					}
					if (helper.getQuantidadeEncerradasForaPrazo() == null) {
						helper.setQuantidadeEncerradasForaPrazo(0);
					}
					
					if(helper.getQuantidadeGeradasNoMesPrazo() == null) {
						helper.setQuantidadeGeradasNoMesPrazo(0);
					}
					if(helper.getQuantidadeGeradasNoMesForaPrazo() == null) {
						helper.setQuantidadeGeradasNoMesForaPrazo(0);
					}
					
					//*****************************************************
					// CRC811 - Motivo Encerramento
					//*****************************************************
					helper.setIdMotivoEncerramento((Integer) retorno[25]);
					//*****************************************************
					
					// se ja existe um objeto igual a ele entao soma as
					// ligacoes e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals ( excecao 
					// quantidadeEconomia, quantidadeLigacoes )
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						ResumoRegistroAtendimentoPorAnoHelper jaCadastrado = 
							(ResumoRegistroAtendimentoPorAnoHelper) listaSimplificada.get(posicao);
						
						jaCadastrado.setQuantidadeGerada(
								jaCadastrado.getQuantidadeGerada() + helper.getQuantidadeGerada() );
						
						jaCadastrado.setQuantidadeBloqueada( 
								jaCadastrado.getQuantidadeBloqueada() + helper.getQuantidadeBloqueada() );
						
						jaCadastrado.setQuantidadeEncerradasNoPrazo( 
								jaCadastrado.getQuantidadeEncerradasNoPrazo() + 
								helper.getQuantidadeEncerradasNoPrazo() );
						
						jaCadastrado.setQuantidadeEncerradasForaPrazo( 
								jaCadastrado.getQuantidadeEncerradasForaPrazo() + 
								helper.getQuantidadeEncerradasForaPrazo() );
						
						jaCadastrado.setQuantidadePendentesNoPrazo( 
								jaCadastrado.getQuantidadePendentesNoPrazo() + 
								helper.getQuantidadePendentesNoPrazo() );
						
						jaCadastrado.setQuantidadePendentesForaPrazo( 
								jaCadastrado.getQuantidadePendentesForaPrazo() + 
								helper.getQuantidadePendentesForaPrazo() );
						
						jaCadastrado.setQuantidadeGeradasNoMesPrazo(								 
								jaCadastrado.getQuantidadeGeradasNoMesPrazo() + 
								helper.getQuantidadeGeradasNoMesPrazo() );
						
						jaCadastrado.setQuantidadeGeradasNoMesForaPrazo( 
								jaCadastrado.getQuantidadeGeradasNoMesForaPrazo() + 
								helper.getQuantidadeGeradasNoMesForaPrazo() );
						
					} else {
						listaSimplificada.add(helper);
					}
				}
			}
			/**
			 * para todas os ImovelResumoRegistroAtendimentoPorAnoHelper cria
			 * ResumoRegistroAtendimentoPorAno
			 */
			
			for (int count = 0; count < listaSimplificada.size(); count++) {
				ResumoRegistroAtendimentoPorAnoHelper helper = 
					(ResumoRegistroAtendimentoPorAnoHelper) listaSimplificada.get(count);					
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}
				
				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}
				
				// Rota
//				GRota rota = null;
//				if(helper.getIdRota() != null ){
//					rota = new GRota();
//					rota.setId( helper.getIdRota() );
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
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId( helper.getIdTipoClienteResponsavel() );
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
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// indicadorAtendimentoOnline
				Short indicadorAtendimentoOnline = null;
				if (helper.getIndicadorAtendimentoOnline() != null) {
					indicadorAtendimentoOnline = (helper.getIndicadorAtendimentoOnline());
				}	
				
				// Solicitação Tipo
				GSolicitacaoTipo solicitacaoTipo = null;
				if ( helper.getIdSolicitacaoTipo() != null ){
					solicitacaoTipo = new GSolicitacaoTipo();
					solicitacaoTipo.setId( helper.getIdSolicitacaoTipo() );
				}
				
				// Solicitação Tipo Especificações
				GSolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
				if(helper.getIdsolicitacaoTipoEspecificacao() != null ){
					solicitacaoTipoEspecificacao = new GSolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao.setId( helper.getIdsolicitacaoTipoEspecificacao() );
				}					
				
				// Meio de Solicitação
				GMeioSolicitacao meioSolicitacao = null;
				if (helper.getIdMeioSolicitacao() != null) {
					meioSolicitacao = new GMeioSolicitacao();
					meioSolicitacao.setId(helper.getIdMeioSolicitacao());
				}
				
				//**************************************************************
				// CRC811 - Motivo Encerramento
				//**************************************************************
				GAtendimentoMotivoEncerramento motivoEncerramento = null;
				if (helper.getIdMotivoEncerramento() != null) {
					motivoEncerramento = new GAtendimentoMotivoEncerramento();
					motivoEncerramento.setId(helper.getIdMotivoEncerramento());
				}
				//**************************************************************
				
				Integer unidadeSolicitacao = helper.getIdUnidadeSolicitacao();
				Integer unidadeEncerramento = helper.getIdUnidadeEncerramento();
				
				int countQtdPedentesNoPrazo = helper.getQuantidadePendentesNoPrazo();
				int countQtdPedentesForaPrazo = helper.getQuantidadePendentesForaPrazo();
				int countQtdEncerradasNoPrazo = helper.getQuantidadeEncerradasNoPrazo();
				int countQtdEncerradasForaPrazo = helper.getQuantidadeEncerradasForaPrazo();
				int countQtdBloqueadas = helper.getQuantidadeBloqueada();
				int countQtdGeradasNoMesPrazo = helper.getQuantidadeGeradasNoMesPrazo();
				int countQtdGeradasNoMesForaPrazo = helper.getQuantidadeGeradasNoMesForaPrazo();
				int countQtdTotal = helper.getQuantidadeGerada();					
				
				// Criamos um resumo de ligacao economia (***UFA***)
				UnResumoRegistroAtendimentoPorAno resumoRegistroAtendimento = 
					new UnResumoRegistroAtendimentoPorAno(
						null, 
						anoMesReferencia.intValue(), 
						new Date(),
						countQtdTotal, 
						codigoSetorComercial,
						countQtdPedentesNoPrazo, 
						countQtdPedentesForaPrazo,
						countQtdGeradasNoMesPrazo,
						countQtdGeradasNoMesForaPrazo,
//						numeroQuadra,
						countQtdBloqueadas, 
						countQtdEncerradasNoPrazo,
						countQtdEncerradasForaPrazo,
						indicadorAtendimentoOnline, 
						meioSolicitacao,
						subcategoria, 
						clienteTipo, 
						ligacaoAguaSituacao,
						unidadeNegocio, 
						localidade, 
						elo, 
						solicitacaoTipo,
//						quadra, 
						ligacaoEsgotoSituacao, 
						perfilLigacaoEsgoto,
						solicitacaoTipoEspecificacao, 
						gerenciaRegional, 
						setorComercial,
						perfilLigacaoAgua,
						esferaPoder, 
						categoria,
						imovelPerfil
//						rota,
//						helper.getCodigoRota()
						);
				
				resumoRegistroAtendimento.setUnidadeSolicitacao(unidadeSolicitacao);
				resumoRegistroAtendimento.setUnidadeEncerramento(unidadeEncerramento);
				
				//**************************************************************
				// CRC811 - Motivo Encerramento
				//**************************************************************
				resumoRegistroAtendimento.setGerMotivoEncerramento(motivoEncerramento);
				//**************************************************************
				
				// Adicionamos a lista que deve ser inserida
				listaResumoRegistroAtendimento.add(resumoRegistroAtendimento);
				gerenciaRegional = null;
				unidadeNegocio = null;
				localidade = null;
				elo = null;
				setorComercial = null;
//				rota = null;
//				quadra = null;
				imovelPerfil = null;
				esferaPoder = null;
				clienteTipo = null;
				ligacaoAguaSituacao = null;
				ligacaoEsgotoSituacao = null;
				categoria = null;
				subcategoria = null;
				perfilLigacaoAgua = null;
				perfilLigacaoEsgoto = null;
				indicadorAtendimentoOnline = null;
				solicitacaoTipo = null;
				solicitacaoTipoEspecificacao = null;
				meioSolicitacao = null;
				unidadeSolicitacao = null;
				unidadeEncerramento = null;
				motivoEncerramento = null;
			}
			listaSimplificada.clear();
			listaSimplificada = null;
			
			imoveisRegistroAtendimento.clear();
			imoveisRegistroAtendimento = null;
			
			getControladorBatch().inserirColecaoObjetoParaBatchGerencial( 
					(Collection) listaResumoRegistroAtendimento);
			
			listaResumoRegistroAtendimento.clear();
			listaResumoRegistroAtendimento = null;
			
			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		}
		catch (Exception ex) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido
			
			ex.printStackTrace();
			//sessionContext.setRollbackOnly();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			
			throw new EJBException(ex);
		}
	}
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 * 
	 * verifica a unidade anterior do registro de atendimento pelo último trâmite
	 * efetuado
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 04/02/2011
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificaUnidadeAnteriorRA(
			Integer idRegistroAtendimento) throws ControladorException {
		try {
			return repositorioRegistroAtendimento.verificaUnidadeAnteriorRA(idRegistroAtendimento);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
/**  * [UC0366] Inserir Registro Atendimento
	 * 
	 * @author Mariana Victor
	 * @date 28/01/2011
	 * 
	 * @param Integer
	 * @param Collection
	 * @throws ControladorException
	 */
	public void inserirRegistroAtendimentoConta(Integer idRegistroAtendimento, 
			Collection colecaoContas) throws ControladorException {
		
		if (idRegistroAtendimento != null && 
			colecaoContas != null && !colecaoContas.isEmpty()){
			
			Iterator it = colecaoContas.iterator();

			while (it.hasNext()){
				
				Conta conta = (Conta) it.next();
				
				RegistroAtendimento registroAtendimento = new RegistroAtendimento();
				registroAtendimento.setId(idRegistroAtendimento);
				
				RegistroAtendimentoContaPK registroAtendimentoContaPK = new RegistroAtendimentoContaPK();
				registroAtendimentoContaPK.setContaId(conta.getId());
				registroAtendimentoContaPK.setRegistroAtendimentoId(idRegistroAtendimento);
				
				RegistroAtendimentoConta registroAtendimentoConta = new RegistroAtendimentoConta();
				registroAtendimentoConta.setComp_id(registroAtendimentoContaPK);
				registroAtendimentoConta.setUltimaAlteracao(new Date());
				
				this.getControladorUtil().inserir(registroAtendimentoConta);
			}
		}
	}
	
	/**  
	 * [UC0366] Inserir Registro Atendimento
	 * 
	 * [SB0028] ? Inclui Registro de Atendimento - Item 3.2
	 * 
	 * @author Rafael Pinto
	 * @date 15/03/2011
	 * 
	 * @param Integer
	 * @param Collection
	 * @param Integer
	 * @throws ControladorException
	 */
	public void inserirRegistroAtendimentoPagamentoDuplicidade(Integer idRegistroAtendimento, 
			Collection colecaoPagamentos,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException {
		
		if(idRegistroAtendimento != null){
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(
				new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.ID,
					idSolicitacaoTipoEspecificacao));
			
			Collection colecao = 
				this.getControladorUtil().pesquisar(
					filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());
			
			SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);
			
			short indicadorInformarPagamentoDuplicidade = 
				especificacao.getIndicadorInformarPagamentoDuplicidade();
			
			if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue() && 
				(colecaoPagamentos == null || colecaoPagamentos.isEmpty())){
				
				throw new ControladorException("nao_exite_pagamento_duplicidade");
			
			}else if (indicadorInformarPagamentoDuplicidade == ConstantesSistema.SIM.shortValue() && 
				colecaoPagamentos != null && !colecaoPagamentos.isEmpty()){
				
				Iterator it = colecaoPagamentos.iterator();

				while (it.hasNext()){
					
					Pagamento pagamento = (Pagamento) it.next();
					
					Conta conta = 
						this.getControladorFaturamento().pesquisarContaDigitada(
							pagamento.getImovel().getId().toString(),
							pagamento.getFormatarAnoMesPagamentoParaMesAno());
					ContaGeral contaGeral = new ContaGeral();


					Integer idContaGeral = null;
					if(conta != null){
						idContaGeral = conta.getId();
						contaGeral.setConta(conta);
					}else{
						ContaHistorico contaHistorico = 
							this.getControladorFaturamento().pesquisarContaHistoricoDigitada(
								pagamento.getImovel().getId().toString(),
								pagamento.getFormatarAnoMesPagamentoParaMesAno());
						
						if(contaHistorico != null){
							idContaGeral = contaHistorico.getId();
							contaGeral.setContaHistorico(contaHistorico);
						}
					}
					
					contaGeral.setId(idContaGeral);
					
					RegistroAtendimento registroAtendimento = new RegistroAtendimento();
					registroAtendimento.setId(idRegistroAtendimento);
					
					RegistroAtendimentoPagamentoDuplicidadePK registroAtendimentoPagamentoDuplicidadePK = 
						new RegistroAtendimentoPagamentoDuplicidadePK();
					
					registroAtendimentoPagamentoDuplicidadePK.setRegistroAtendimentoId(idRegistroAtendimento);
					registroAtendimentoPagamentoDuplicidadePK.setPagamentoId(pagamento.getId());
					
					
					RegistroAtendimentoPagamentoDuplicidade registroAtendimentoPagamentoDuplicidade = new RegistroAtendimentoPagamentoDuplicidade();
					registroAtendimentoPagamentoDuplicidade.setComp_id(registroAtendimentoPagamentoDuplicidadePK);
					
					registroAtendimentoPagamentoDuplicidade.setUltimaAlteracao(new Date());
					registroAtendimentoPagamentoDuplicidade.setContaGeral(contaGeral);
					registroAtendimentoPagamentoDuplicidade.setImovel(pagamento.getImovel());
					
					registroAtendimentoPagamentoDuplicidade.setAnoMesReferenciaPagamento(pagamento.getAnoMesReferenciaPagamento());
					registroAtendimentoPagamentoDuplicidade.setDataPagamento(pagamento.getDataPagamento());
					registroAtendimentoPagamentoDuplicidade.setValorPagamento(pagamento.getValorPagamento());
					
					registroAtendimentoPagamentoDuplicidade.setIndicadorPagamentoDevolvido(ConstantesSistema.NAO);
					
					registroAtendimentoPagamentoDuplicidade.setAnoMesReferenciaPagamento(pagamento.getAnoMesReferenciaPagamento());
					
					this.getControladorUtil().inserir(registroAtendimentoPagamentoDuplicidade);
				}
			}
		}

	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Mariana Victor
	 * @date 31/01/2011
	 * 
	 * @param Integer
	 * @param Collection
	 * @throws ControladorException
	 */
	public void atualizarRegistroAtendimentoConta(Integer idRegistroAtendimento, 
			Collection colecaoRAConta, Collection colecaoRAContaRemover) throws ControladorException {
		
		if (idRegistroAtendimento != null) {
			if (colecaoRAContaRemover != null && !colecaoRAContaRemover.isEmpty()){
				Iterator it = colecaoRAContaRemover.iterator();
	
				while (it.hasNext()){
					RegistroAtendimentoConta registroAtendimentoConta = (RegistroAtendimentoConta) it.next();
					
					if (registroAtendimentoConta.getComp_id() != null
							&& registroAtendimentoConta.getComp_id().getContaId() != null
							&& registroAtendimentoConta.getComp_id().getRegistroAtendimentoId() != null) {
						this.getControladorUtil().remover(registroAtendimentoConta);
					}
				}
			}
			
			if (colecaoRAConta != null && !colecaoRAConta.isEmpty()){
				Iterator it = colecaoRAConta.iterator();
	
				while (it.hasNext()){
					
					RegistroAtendimentoConta registroAtendimentoConta = (RegistroAtendimentoConta) it.next();
					
					if (registroAtendimentoConta.getUltimaAlteracao() == null) {
						RegistroAtendimento registroAtendimento = new RegistroAtendimento();
						registroAtendimento.setId(idRegistroAtendimento);
						
						RegistroAtendimentoContaPK registroAtendimentoContaPK = new RegistroAtendimentoContaPK();
						registroAtendimentoContaPK.setContaId(registroAtendimentoConta.getConta().getId());
						registroAtendimentoContaPK.setRegistroAtendimentoId(idRegistroAtendimento);
						
						registroAtendimentoConta.setComp_id(registroAtendimentoContaPK);
						registroAtendimentoConta.setUltimaAlteracao(new Date());
						
						this.getControladorUtil().inserir(registroAtendimentoConta);
					} else {
						RegistroAtendimentoContaPK registroAtendimentoContaPK = new RegistroAtendimentoContaPK();
						registroAtendimentoContaPK.setContaId(registroAtendimentoConta.getConta().getId());
						registroAtendimentoContaPK.setRegistroAtendimentoId(idRegistroAtendimento);
						
						registroAtendimentoConta.setComp_id(registroAtendimentoContaPK);
						registroAtendimentoConta.setUltimaAlteracao(new Date());
						
						this.getControladorUtil().atualizar(registroAtendimentoConta);
					}
				}
			}
			
		}
	}
	
	/**  
	 * [UC0366] Inserir Registro Atendimento
	 * 
	 * [SB0028] ? Inclui Registro de Atendimento - Item 3.2
	 * 
	 * @author Rafael Pinto
	 * @date 15/03/2011
	 * 
	 * @param Integer
	 * @param Collection
	 * @param Integer
	 * @throws ControladorException
	 */
	public void atualizarRegistroAtendimentoPagamentoDuplicidade(Integer idRegistroAtendimento,
			Integer idImovel,
			Collection colecaoPagamentos) throws ControladorException {
		
		if(idRegistroAtendimento != null){
			
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()){
				
				Collection<Integer> colecaoIdPagamentoNaoRemovidos = new ArrayList<Integer>();
				
				//Remove os objetos 
				FiltroRegistroAtendimentoPagamentoDuplicidade filtroRegistroAtendimentoPagamentoDuplicidade = 
					new FiltroRegistroAtendimentoPagamentoDuplicidade();
				
				filtroRegistroAtendimentoPagamentoDuplicidade.adicionarParametro(
					new ParametroSimples(
						FiltroRegistroAtendimentoPagamentoDuplicidade.REGISTRO_ATENDIMENTO,
						idRegistroAtendimento));
				
				Collection colecaoRegistroAtendimentoPagamentoDuplicidade = 
					(Collection) this.getControladorUtil().pesquisar(
						filtroRegistroAtendimentoPagamentoDuplicidade, 
						RegistroAtendimentoPagamentoDuplicidade.class.getName());
				
				if(colecaoRegistroAtendimentoPagamentoDuplicidade != null && !colecaoRegistroAtendimentoPagamentoDuplicidade.isEmpty()){
					
					Iterator iteraTodosPagamentos = colecaoRegistroAtendimentoPagamentoDuplicidade.iterator();
					
					while (iteraTodosPagamentos.hasNext()) {
						RegistroAtendimentoPagamentoDuplicidade registro = (RegistroAtendimentoPagamentoDuplicidade) iteraTodosPagamentos.next();
							
						if(registro.getIndicadorPagamentoDevolvido().shortValue() == ConstantesSistema.NAO.shortValue()){
							this.getControladorUtil().remover(registro);
						}else{
							colecaoIdPagamentoNaoRemovidos.add(registro.getComp_id().getPagamentoId());
						}
					}
				}
				
				Iterator it = colecaoPagamentos.iterator();

				while (it.hasNext()){
					
					RegistroAtendimentoPagamentoDuplicidade registro = (RegistroAtendimentoPagamentoDuplicidade) it.next();
					boolean naoExistePagamento = false;
					
					if(!colecaoIdPagamentoNaoRemovidos.contains(registro.getComp_id().getPagamentoId())){
						naoExistePagamento = true;
					}
					
					if(naoExistePagamento){
						
						Conta conta = 
							this.getControladorFaturamento().pesquisarContaDigitada(
								idImovel.toString(),
								registro.getFormatarAnoMesPagamentoParaMesAno());


						Integer idContaGeral = null;
						if(conta != null){
							idContaGeral = conta.getId();
						}else{
							ContaHistorico contaHistorico = 
								this.getControladorFaturamento().pesquisarContaHistoricoDigitada(
									idImovel.toString(),
									registro.getFormatarAnoMesPagamentoParaMesAno());
							
							if(contaHistorico != null){
								idContaGeral = contaHistorico.getId();

							}
						}
						
						ContaGeral contaGeral = new ContaGeral();
						contaGeral.setId(idContaGeral);
						
						RegistroAtendimento registroAtendimento = new RegistroAtendimento();
						registroAtendimento.setId(idRegistroAtendimento);
						
						RegistroAtendimentoPagamentoDuplicidadePK registroAtendimentoPagamentoDuplicidadePK = 
							new RegistroAtendimentoPagamentoDuplicidadePK();
						
						registroAtendimentoPagamentoDuplicidadePK.setRegistroAtendimentoId(idRegistroAtendimento);
						registroAtendimentoPagamentoDuplicidadePK.setPagamentoId(registro.getComp_id().getPagamentoId());
						
						
						RegistroAtendimentoPagamentoDuplicidade registroAtendimentoPagamentoDuplicidade = new RegistroAtendimentoPagamentoDuplicidade();
						registroAtendimentoPagamentoDuplicidade.setComp_id(registroAtendimentoPagamentoDuplicidadePK);
						
						registroAtendimentoPagamentoDuplicidade.setAnoMesReferenciaPagamento(registro.getAnoMesReferenciaPagamento());
						registroAtendimentoPagamentoDuplicidade.setDataPagamento(registro.getDataPagamento());
						registroAtendimentoPagamentoDuplicidade.setValorPagamento(registro.getValorPagamento());
						
						registroAtendimentoPagamentoDuplicidade.setUltimaAlteracao(new Date());
						registroAtendimentoPagamentoDuplicidade.setContaGeral(contaGeral);
						
						Imovel imovel = new Imovel();
						imovel.setId(new Integer(idImovel));
						
						registroAtendimentoPagamentoDuplicidade.setImovel(imovel);
						registroAtendimentoPagamentoDuplicidade.setIndicadorPagamentoDevolvido(ConstantesSistema.NAO);
						
						
						this.getControladorUtil().inserir(registroAtendimentoPagamentoDuplicidade);							
					}
						

				}
			}
		}

	}
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 09/02/2011
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public Integer verificaSolicitacaoTipoEspecificacaoRA(
			Integer idImovel)throws ControladorException {
		try {
			return repositorioRegistroAtendimento.verificaSolicitacaoTipoEspecificacaoRA(idImovel);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public boolean existeRegistroAtendimentoConta(
			Integer idConta,Integer idRA) throws ControladorException {
		try {
			
			boolean retorno = false;
			
			RegistroAtendimentoConta registroAtendimentoConta = 
				repositorioRegistroAtendimento.pesquisaRegistroAtendimentoConta(idConta,idRA);
			
			if(registroAtendimentoConta != null){
				retorno = true;
			}
			
			return retorno;
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public ContaMotivoRetificacao pesquisaContaMotivoRetificacao(
			Integer idMotivo) throws ControladorException {
		try {
			return repositorioRegistroAtendimento.pesquisaContaMotivoRetificacao(idMotivo);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * UC1130 – Filtrar Registro Atendimento de Devolução de Valores
	 * @author Vivianne Sousa
	 * @date 11/03/2011
	 */
	public Collection obterRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper, 
			Integer numeroPagina)throws ControladorException {
		try {
			
			Collection colecaoRetorno = null;
			Collection colecaoRegistroAtendimento = repositorioRegistroAtendimento.obterRegistroAtendimento(helper, numeroPagina);
			
			if(colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()){
				
				Iterator iterRA = colecaoRegistroAtendimento.iterator();
				colecaoRetorno = new ArrayList();
				RegistroAtendimentoDevolucaoValoresHelper retorno = null;
				while (iterRA.hasNext()) {
					
					RegistroAtendimento ra = (RegistroAtendimento) iterRA.next();
					Cliente clienteUsuarioImovel = getControladorCliente().retornaClienteUsuario(ra.getImovel().getId());
					
					retorno = new RegistroAtendimentoDevolucaoValoresHelper(
							ra.getImovel().getId(), ra.getId() ,clienteUsuarioImovel.getNome());
					colecaoRetorno.add(retorno);
					
				}
			}
			return colecaoRetorno;
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * UC1130 – Filtrar Registro Atendimento de Devolução de Valores
	 * @author Vivianne Sousa
	 * @date 14/03/2011
	 */
	public Integer obterQtdeRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper)throws ControladorException {
		try {
			return repositorioRegistroAtendimento.obterQtdeRegistroAtendimento(helper);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * [SB0001] – Pesquisar os pagamentos associados ao RA
	 * 
	 * @author Vivianne Sousa
	 * @date 15/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(
			Integer idRA) throws ControladorException {
		try {
			
			Collection colecaoRetorno = null;
			Collection colecaoDadosRegistroAtendimentoPagamentoDuplicidade = repositorioRegistroAtendimento.
				pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(idRA);
			
			if(colecaoDadosRegistroAtendimentoPagamentoDuplicidade != null && !colecaoDadosRegistroAtendimentoPagamentoDuplicidade.isEmpty()){
				
				Iterator iterDados = colecaoDadosRegistroAtendimentoPagamentoDuplicidade.iterator();
				colecaoRetorno = new ArrayList();
				
				while (iterDados.hasNext()) {
					
					Object[] dados = (Object[])iterDados.next();
					Pagamento pagamento = (Pagamento)dados[0];
					ContaGeral contaGeral = new ContaGeral();
					
					if(dados[1] != null){
						Conta conta = (Conta)dados[1];
						contaGeral.setConta(conta);
					}else if(dados[2] != null){
						ContaHistorico contaHistorico = (ContaHistorico)dados[2];
						contaGeral.setContaHistorico(contaHistorico);
					}
					pagamento.setContaGeral(contaGeral);

					colecaoRetorno.add(pagamento);
					
				}
			}
			return colecaoRetorno;
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * [SB0007] Atualiza Pagamento Devolvido
	 * 
	 * @author Vivianne Sousa
	 * @date 21/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarRegistroAtendimentoPagamentoDuplicidade(
			Integer idRa,Integer idPagamento)throws ControladorException {
		try {
			 repositorioRegistroAtendimento.atualizarRegistroAtendimentoPagamentoDuplicidade(idRa,idPagamento);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public Collection transferirDevolucaoValoresPagosDuplicidade(Collection colecaoContaASerRetificada,
			Collection colecaoCreditoARealizar,Collection colecaoCreditoASerTransferido,
			Collection colecaoPagamento,SistemaParametro sistemaParametro,Usuario usuarioLogado,
			RegistroAtendimento ra,Integer idImovel)throws ControladorException {
		
		Collection colecaoContas = null;
		try{

			if(colecaoContaASerRetificada != null && !colecaoContaASerRetificada.isEmpty()){
				
				colecaoContas = new ArrayList();
				Iterator iterContaASerRetificada = colecaoContaASerRetificada.iterator();
				
				while (iterContaASerRetificada.hasNext()) {
					ContaValoresHelper helper = (ContaValoresHelper) iterContaASerRetificada.next();
					Conta conta = helper.getConta();
					Collection colecaoCreditoRealizadoInserir = new ArrayList();
					
					Iterator iterCreditoASerTransferido = colecaoCreditoASerTransferido.iterator();
					while (iterCreditoASerTransferido.hasNext()) {
						CreditosHelper creditosHelper = (CreditosHelper) iterCreditoASerTransferido.next();
						if(creditosHelper.getIdContaCreditorealizado().equals(conta.getId())){
							colecaoCreditoRealizadoInserir.add(creditosHelper);
						}
					}
					Integer idConta  = this.chamarRetificarConta(conta, sistemaParametro,usuarioLogado, colecaoCreditoRealizadoInserir);
					colecaoContas.add(idConta);
					
				}
	
			}
			
			if(colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()){
				
				Iterator iterCreditoARealizar = colecaoCreditoARealizar.iterator();
				while (iterCreditoARealizar.hasNext()) {
					CreditosHelper helper = (CreditosHelper) iterCreditoARealizar.next();
					
					chamarInserirCreditoARealizar(idImovel, helper, usuarioLogado,sistemaParametro,ra); 
				}
			}
	
			atualizarPagamento(colecaoPagamento,sistemaParametro,ra.getId());

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return colecaoContas;
	}
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * [SB0005] – Formatar dados para Retificação da Conta. 
	 * 
	 * @author Vivianne Sousa
	 * @date 22/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public Integer chamarRetificarConta(Conta conta, SistemaParametro sistemaParametro,
			Usuario usuarioLogado,Collection colecaoCreditoRealizadoInserir)throws ControladorException {
		
		Integer idConta = null;
		
		try {
			
			Collection colecaoCategoriaOUSubcategoria = null;
		
			if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)) {
					colecaoCategoriaOUSubcategoria = getControladorImovel().obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta);
			} else {
				colecaoCategoriaOUSubcategoria = getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);
			}
			
			Collection colecaoDebitoCobrado = getControladorFaturamento().obterDebitosCobradosConta(conta);
			
			Conta contaParaRetificacao =  getControladorFaturamento().pesquisarContaRetificacao(conta.getId());
			
			Collection colecaoCreditoRealizado = getControladorFaturamento().obterCreditosRealizadosConta(conta);
	
			if(colecaoCreditoRealizadoInserir != null && !colecaoCreditoRealizadoInserir.isEmpty()){
	
				if(colecaoCreditoRealizado == null || colecaoCreditoRealizado.isEmpty()){
					colecaoCreditoRealizado = new ArrayList();
				}
				
				Iterator iterCreditosHelper = colecaoCreditoRealizadoInserir.iterator();
				
				while (iterCreditosHelper.hasNext()) {
					CreditosHelper helper = (CreditosHelper) iterCreditosHelper.next();
					
					CreditoRealizado creditoRealizado = new CreditoRealizado();
					creditoRealizado.setCreditoTipo(helper.getTipoCreditoID());
					creditoRealizado.setCreditoOrigem(helper.getOrigemCreditoID());
					creditoRealizado.setValorCredito(helper.getValorCredito());
					creditoRealizado.setAnoMesCobrancaCredito(helper.getReferenciaCredito());
					creditoRealizado.setAnoMesReferenciaCredito(helper.getReferenciaCredito());
					creditoRealizado.setNumeroPrestacao(new Short("0"));
					creditoRealizado.setNumeroPrestacaoCredito(new Short("0"));
					
					creditoRealizado.setCreditoRealizado(new Date());
					creditoRealizado.setUltimaAlteracao(new Date());
	
					CreditoRealizado creditoRealizadoInserido = inserirCreditoRealizado(creditoRealizado,contaParaRetificacao,
							contaParaRetificacao.getImovel(),colecaoCategoriaOUSubcategoria);
					colecaoCreditoRealizado.add(creditoRealizadoInserido);
					
//					colecaoCreditoRealizado.add(creditoRealizado);
				}

			}

			//[UC0120] - Calcular Valores de Água e/ou Esgoto
			Collection<CalcularValoresAguaEsgotoHelper> valoresConta = 
				getControladorFaturamento().calcularValoresConta(
				Util.formatarAnoMesParaMesAno(conta.getReferencia()), 
				contaParaRetificacao.getImovel().getId().toString(), 
				contaParaRetificacao.getLigacaoAguaSituacao().getId(), 
				contaParaRetificacao.getLigacaoEsgotoSituacao().getId(), 
				colecaoCategoriaOUSubcategoria, 
				contaParaRetificacao.getConsumoAgua().toString(), 
				contaParaRetificacao.getConsumoEsgoto().toString(), 
				contaParaRetificacao.getPercentualEsgoto().toString(), 
				contaParaRetificacao.getConsumoTarifa().getId(), usuarioLogado);
			
			//[UC0150] - Retificar Conta
			
			ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
			contaMotivoRetificacao.setId(ContaMotivoRetificacao.DEVOLUCAO_PAGAMENTO_CREDITADO_EM_CONTA);
			
			idConta = getControladorFaturamento().retificarConta(
					new Integer(contaParaRetificacao.getReferencia()),
					contaParaRetificacao, 
					contaParaRetificacao.getImovel(), 
					colecaoDebitoCobrado,
					colecaoCreditoRealizado, 
					contaParaRetificacao.getLigacaoAguaSituacao(), 
					contaParaRetificacao.getLigacaoEsgotoSituacao(),
					colecaoCategoriaOUSubcategoria, 
					contaParaRetificacao.getConsumoAgua().toString(), 
					contaParaRetificacao.getConsumoEsgoto().toString(),
					contaParaRetificacao.getPercentualEsgoto().toString(), 
					contaParaRetificacao.getDataVencimentoConta(), 
					valoresConta, 
					contaMotivoRetificacao, 
					null, 
					usuarioLogado, 
					contaParaRetificacao.getConsumoTarifa().getId().toString(),
					false,null,null,false, null,null,null,null,null);
		

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return idConta;
	}
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * [SB0006] – Formatar dados para inclusão do crédito a realizar. 
	 * 
	 * @author Vivianne Sousa
	 * @date 22/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public void chamarInserirCreditoARealizar(Integer idImovel, CreditosHelper helper, Usuario usuarioLogado,
			SistemaParametro sistemaParametro, RegistroAtendimento ra) throws ControladorException {

		try {
			
			CreditoARealizar creditoARealizar = new CreditoARealizar();
			
			Imovel imovel  =  Fachada.getInstancia().pesquisarImovelDigitado(idImovel);
			creditoARealizar.setImovel(imovel);
			
			creditoARealizar.setValorCredito(helper.getValorCredito());
			creditoARealizar.setAnoMesReferenciaCredito(helper.getReferenciaCredito());
			creditoARealizar.setRegistroAtendimento(ra);
			creditoARealizar.setCreditoTipo(helper.getTipoCreditoID());
			creditoARealizar.setCreditoOrigem(helper.getOrigemCreditoID());
			
			LancamentoItemContabil lict = new LancamentoItemContabil();
			lict.setId(LancamentoItemContabil.OUTROS_SERVICOS_AGUA);
			creditoARealizar.setLancamentoItemContabil(lict);
			
			creditoARealizar.setUsuario(usuarioLogado);
			
			// [FS0011] - Validar Referência do Crédito
	//		FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();
	//		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
	//				FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO,creditoARealizar.getAnoMesReferenciaCredito()));
	//		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
	//				FiltroCreditoARealizar.ID_CREDITO_ORIGEM, creditoARealizar.getCreditoOrigem().getId()));
	//		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
	//				FiltroCreditoARealizar.ID_CREDITO_TIPO, creditoARealizar.getCreditoTipo().getId()));
	//		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
	//				FiltroCreditoARealizar.IMOVEL_ID, creditoARealizar.getImovel().getId()));
	//		
	//		Collection colecaoCreditosBase = Fachada.getInstancia().pesquisar(
	//				filtroCreditoARealizar, CreditoARealizar.class.getName());
	//		
	//		if (colecaoCreditosBase != null && !colecaoCreditosBase.isEmpty()) {
	//			throw new ControladorException("atencao.referencia.credito_a_realizar.ja.existente");
	//		}
			
			// Data de Geração do Crédito
			creditoARealizar.setGeracaoCredito(new Date());
			
			Date dataAtual = new Date();
			Integer anoMesReferenciaAtual = Util.recuperaAnoMesDaData( dataAtual );
			
			creditoARealizar.setAnoMesReferenciaContabil( 
					
					( anoMesReferenciaAtual > sistemaParametro.getAnoMesFaturamento() ? anoMesReferenciaAtual : sistemaParametro.getAnoMesFaturamento() )
					
				);				
			
			creditoARealizar.setAnoMesCobrancaCredito(sistemaParametro.getAnoMesArrecadacao());
			
			// Valor Residual Mes Anterior
			creditoARealizar.setValorResidualMesAnterior(new BigDecimal(0));
			
			// Prestacao Credito
			creditoARealizar.setNumeroPrestacaoCredito(new Short("1"));
			
			// Prestacao Realizada
			creditoARealizar.setNumeroPrestacaoRealizada(new Short((short) 0));
			
			// Imovel
			creditoARealizar.setImovel(imovel);
			creditoARealizar.setLocalidade(imovel.getLocalidade());
			creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
			creditoARealizar.setNumeroLote(imovel.getLote());
			creditoARealizar.setNumeroSubLote(imovel.getSubLote());
			creditoARealizar.setQuadra(imovel.getQuadra());
			creditoARealizar.setNumeroQuadra(new Integer(imovel.getQuadra().getNumeroQuadra()));
			
			// Ordem de Servico
			creditoARealizar.setOrdemServico(null);
			
			// Debito Credito Situacao Atual
			DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
			debitoCreditoSituacaoAtual.setId(DebitoCreditoSituacao.NORMAL);
			creditoARealizar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
			
			// Debito Credito Situacao Anterior
			creditoARealizar.setDebitoCreditoSituacaoAnterior(null);
			
			// Data de Ultima Alteracao
			creditoARealizar.setUltimaAlteracao(new Date());
			
			//GERANDO O CREDITO A REALIZAR
			getControladorFaturamento().gerarCreditoARealizar(creditoARealizar, imovel, usuarioLogado);
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * [SB0007] Atualiza Pagamento Devolvido
	 * 
	 * @author Vivianne Sousa
	 * @date 22/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarPagamento(Collection colecaoPagamento,SistemaParametro sistemaParametro, Integer idRa)throws ControladorException {
		
		try{
			Integer refereciaArrecadacaoSP = sistemaParametro.getAnoMesArrecadacao();
			PagamentoSituacao pagamentoSituacao = new PagamentoSituacao();
			pagamentoSituacao.setId(PagamentoSituacao.DUPLICIDADE_EXCESSO_DEVOLVIDO);
			Iterator iterPagamento = colecaoPagamento.iterator();
			
			while (iterPagamento.hasNext()) {
				Pagamento pagamento = (Pagamento) iterPagamento.next();
				
				if(refereciaArrecadacaoSP.equals(pagamento.getAnoMesReferenciaArrecadacao())){
					pagamento.setPagamentoSituacaoAnterior(pagamento.getPagamentoSituacaoAtual());
				}
				
				pagamento.setPagamentoSituacaoAtual(pagamentoSituacao);
				pagamento.setUltimaAlteracao(new Date());
				
				getControladorUtil().atualizar(pagamento);
				
				atualizarRegistroAtendimentoPagamentoDuplicidade(idRa,pagamento.getId());
			}
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * 
	 * @author Vivianne Sousa
	 * @date 22/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public CreditoRealizado inserirCreditoRealizado(CreditoRealizado creditoRealizadoColecao,
			Conta conta,Imovel imovel,Collection colecaoCategoria )throws ControladorException {
		try{

			CreditoRealizado creditoRealizadoInserir = new CreditoRealizado();
		
			// Tipo do crédito selecionado
			creditoRealizadoInserir.setCreditoTipo(creditoRealizadoColecao.getCreditoTipo());
		
			// Crédito realizado - data corrente
			creditoRealizadoInserir.setCreditoRealizado(new Date());
		
			// Conta
//			creditoRealizadoInserir.setConta(conta);
		
			// Lançamento Item Contábil
			creditoRealizadoInserir.setLancamentoItemContabil(creditoRealizadoColecao.getCreditoTipo().getLancamentoItemContabil());
		
			// Localidade
			creditoRealizadoInserir.setLocalidade(imovel.getLocalidade());
		
			// Quadra
			creditoRealizadoInserir.setQuadra(imovel.getQuadra());
		
			// Código do setor comercial
			creditoRealizadoInserir.setCodigoSetorComercial(new Integer(imovel.getSetorComercial().getCodigo()));
		
			// Número da quadra
			creditoRealizadoInserir.setNumeroQuadra(new Integer(imovel.getQuadra().getNumeroQuadra()));
		
			// Lote
			creditoRealizadoInserir.setNumeroLote(new Short(imovel.getLote()));
		
			// Sublote
			creditoRealizadoInserir.setNumeroSubLote(new Short(imovel.getSubLote()));
		
			// Ano Mês referência do crédito
			if (creditoRealizadoColecao.getAnoMesReferenciaCredito() != null) {
				creditoRealizadoInserir.setAnoMesReferenciaCredito(
						creditoRealizadoColecao.getAnoMesReferenciaCredito());
			}
		
			// Ano Mês referência da cobrança
			if (creditoRealizadoColecao.getAnoMesCobrancaCredito() != null) {
				creditoRealizadoInserir.setAnoMesCobrancaCredito(
						creditoRealizadoColecao.getAnoMesCobrancaCredito());
			}
		
			// Valor da prestação
			creditoRealizadoInserir.setValorCredito(creditoRealizadoColecao.getValorCredito());
		
			// Número de prestações
			if (creditoRealizadoColecao.getNumeroPrestacao() == 0) {
				creditoRealizadoInserir.setNumeroPrestacao(new Short("1").shortValue());
			} else {
				creditoRealizadoInserir
						.setNumeroPrestacao(creditoRealizadoColecao.getNumeroPrestacao());
			}
		
			// Número de prestações do crédito
			if (creditoRealizadoColecao.getNumeroPrestacaoCredito() == 0) {
				creditoRealizadoInserir.setNumeroPrestacaoCredito(new Short("1").shortValue());
			} else {
				creditoRealizadoInserir
						.setNumeroPrestacaoCredito(creditoRealizadoColecao.getNumeroPrestacaoCredito());
			}
		
		    creditoRealizadoInserir.setNumeroParcelaBonus(creditoRealizadoColecao.getNumeroParcelaBonus());
		    
			// Credito Origem
			creditoRealizadoInserir
					.setCreditoOrigem(creditoRealizadoColecao.getCreditoOrigem());
		
			// Ultima alteração
			creditoRealizadoInserir.setUltimaAlteracao(new Date());
				
			// Evitando que registre transacao
			creditoRealizadoInserir.setUsuarioAcaoUsuarioHelp(null);
			creditoRealizadoInserir.setOperacaoEfetuada(null);
			
			
			creditoRealizadoInserir.setCreditoARealizarGeral(creditoRealizadoColecao.getCreditoARealizarGeral());
			// -------------------------------------------------------
			// Inserindo no BD
//			Integer idCreditoRealizadoGerado = (Integer) this
//					.getControladorUtil().inserir(creditoRealizadoInserir);
//		
//			creditoRealizadoInserir.setId(idCreditoRealizadoGerado);
//		
//			// Inclui a quantidade de economias por categoria do crédito
//			// cobrado na tabela CREDITO_REALIZADO_CATEGORIA
//			getControladorFaturamento().inserirCreditoRealizadoCategoria(creditoRealizadoInserir,colecaoCategoria);

			return creditoRealizadoInserir;
		}catch (Exception e){
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	


	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0037] – Define Unidade Destino por Situação de Cobrança.
	 * [FS0018 – Verificar existência de unidade centralizadora].
	 * 
	 * @author Mariana Victor
	 * @date 04/04/2011
	 * 
	 * @param idLocalidade, idImovel
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoSituacaoCobranca(
			Integer idEspecificacao, Integer idImovel) throws ControladorException {
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		
		try {
			Object[] dadosUnidadeOrganizacional = this.repositorioRegistroAtendimento
				.pesquisarUnidadeDestinoEspecificacaoRA(idEspecificacao, idImovel);
			
			if (dadosUnidadeOrganizacional != null
					&& dadosUnidadeOrganizacional[0] != null
					&& dadosUnidadeOrganizacional[1] != null) {
				
				unidadeOrganizacional = new UnidadeOrganizacional();
				
				unidadeOrganizacional.setId((Integer)dadosUnidadeOrganizacional[0]);
				unidadeOrganizacional.setDescricao((String) dadosUnidadeOrganizacional[1]);
				
				//[FS0018 – Verificar existência de unidade centralizadora].
				unidadeOrganizacional = this.getControladorUnidade()
					.verificarExistenciaUnidadeCentralizadora(unidadeOrganizacional);
				
			}
			
		}catch (Exception e){
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return unidadeOrganizacional;
	}
	
	
	
	/**
	 * RM 5238 – Proposta 20110502
	 * [[UC0366] Inserir Registro de Atendimento]
	 * [FS0052] – Verificar alteração de vencimento recente para o imóvel
	 * @author Rômulo Aurélio
	 * @throws ControladorException 
	 * @date 25/04/2011
	 */
	
	public void verificarAlteracaoVencimentoRecente(Integer idEspecificacao,
			Usuario usuarioLogado, Integer idImovel)
			throws ControladorException {

		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao
				.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));

		Collection colecaoSolicitacaoTipoEspecificacao = getControladorUtil()
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());

		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacao
				.iterator().next();

		if (solicitacaoTipoEspecificacao
				.getIndicadorAlterarVencimentoAlternativo() != null
				&& solicitacaoTipoEspecificacao
						.getIndicadorAlterarVencimentoAlternativo().shortValue() == ConstantesSistema.SIM.shortValue()) {

			Collection vencimentosAlternativos = new ArrayList();

			VencimentoAlternativo vencimentoAlternativo = null;

			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			Short numeroMesesMinimoVencimentoAlternativo = sistemaParametro
					.getNumeroMesesMinimoAlteracaoVencimento();

			FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo(
					FiltroVencimentoAlternativo.DATA_IMPLANTACAO);

			filtroVencimentoAlternativo
					.adicionarParametro(new ParametroSimples(
							FiltroVencimentoAlternativo.IMOVEL_ID, idImovel));

			vencimentosAlternativos = getControladorUtil().pesquisar(
					filtroVencimentoAlternativo,
					VencimentoAlternativo.class.getName());

			if (vencimentosAlternativos != null
					&& !vencimentosAlternativos.isEmpty()) {

				vencimentoAlternativo = (VencimentoAlternativo) Util
						.retonarObjetoDeColecao(vencimentosAlternativos);

				// verifica se usuario possue permissão especial para informar
				// o vencimento alternativo antes do período válido
				boolean temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido = getControladorPermissaoEspecial()
						.verificarPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido(
								usuarioLogado);

				Date dataImplantacao = vencimentoAlternativo
						.getDataImplantacao();
				Date dataAtual = new Date();

				int diferencaMes = Util.dataDiff(dataAtual, dataImplantacao);

				if (diferencaMes < numeroMesesMinimoVencimentoAlternativo
						&& !temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido) {
					throw new ControladorException(
							"atencao.imovel.vencimento.alterado", null,
							numeroMesesMinimoVencimentoAlternativo.toString());
				}
			}
		}
	}
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * 
	 * Pesquisar dados do registro de atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/05/2011
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento pesquisarDadosRegistroAtendimentoParaReiteracao(
			Integer idRegistroAtendimento) throws ControladorException {
		try {
			 return repositorioRegistroAtendimento.pesquisarDadosRegistroAtendimentoParaReiteracao(idRegistroAtendimento);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0008] – Verificar reiteração do RA pelo cliente 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 */
	public void verificarExistenciaClienteSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idCliente, String nomeCliente)
	throws ControladorException {
		
		Integer qtdRegistros = null;
		
		try {
			qtdRegistros = this.repositorioRegistroAtendimento
			.verificarExistenciaClienteSolicitanteDataAtual(
					idRegistroAtendimento, idCliente);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (qtdRegistros != null && qtdRegistros > 0) {
			throw new ControladorException(
					"atencao.cliente_solicitante_ja_informado_reiteracao",
					null,idRegistroAtendimento.toString(), nomeCliente);
		}
		
	}
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0007] – Verificar reiteração do RA pela unidade 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 */
	public void verificarExistenciaUnidadeSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idUnidade, String nomeUnidade)
	throws ControladorException {
		
		Integer qtdRegistros = null;
		
		try {
			qtdRegistros = this.repositorioRegistroAtendimento
			.verificarExistenciaUnidadeSolicitanteDataAtual(
					idRegistroAtendimento, idUnidade);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (qtdRegistros != null && qtdRegistros > 0) {
			throw new ControladorException(
					"atencao.unidade_solicitante_ja_informado_reiteracao",
					null,idRegistroAtendimento.toString(), nomeUnidade);
		}
		
	}
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 *  
	 * @author Vivianne Sousa
	 * @data 16/05/2011
	 */
	public Collection pesquisarDadosReiteracaoRA(
			Integer idRegistroAtendimento) throws ControladorException {
		try {
			 Collection colecaoRetorno = null;
			
			 Collection colecaoRAReiteracao =  this.repositorioRegistroAtendimento.
			 	pesquisarRAReiteracao(idRegistroAtendimento);
			 Collection colecaoRAReiteracaoFone = null;
			 DadosRAReiteracaoHelper helper = null;
			 
			 if(colecaoRAReiteracao != null && !colecaoRAReiteracao.isEmpty()){
				 Iterator iterRAReiteracao = colecaoRAReiteracao.iterator();
				 colecaoRetorno = new ArrayList();
				 while (iterRAReiteracao.hasNext()) {
					RAReiteracao raReiteracao = (RAReiteracao) iterRAReiteracao.next();
					helper = new DadosRAReiteracaoHelper();
					helper.setRaReiteracao(raReiteracao);

					if(raReiteracao.getCliente() != null){
						helper.setIdClienteSolicitante(raReiteracao.getCliente().getId());
						helper.setNomeSolicitante(raReiteracao.getCliente().getNome());
						helper.setNomeClienteSolicitante(raReiteracao.getCliente().getNome());
					}else if(raReiteracao.getUnidadeOrganizacional() != null){
						helper.setIdUnidadeSolicitante(raReiteracao.getUnidadeOrganizacional().getId());
						helper.setNomeSolicitante(raReiteracao.getUnidadeOrganizacional().getDescricao());
						helper.setNomeUnidadeSolicitante(raReiteracao.getUnidadeOrganizacional().getDescricao());
					}else{
						helper.setNomeSolicitante(raReiteracao.getSolicitante());
					}
					
					colecaoRAReiteracaoFone =  this.repositorioRegistroAtendimento.
				 		pesquisarRAReiteracaoFone(raReiteracao.getId());
					if(colecaoRAReiteracaoFone != null && !colecaoRAReiteracaoFone.isEmpty()){
						helper.setColecaoRAReiteracaoFone(colecaoRAReiteracaoFone);
						Iterator iterFone = colecaoRAReiteracaoFone.iterator();
						while (iterFone.hasNext()) {
							RAReiteracaoFone fone = (RAReiteracaoFone) iterFone.next();
							
							if(fone.getIndicadorPadrao().equals(ConstantesSistema.SIM)){
								helper.setFonePrincipal(fone.getTelefoneFormatado());
							}
							
						}
					}
					colecaoRetorno.add(helper);	
				}
			 }
			 
			 return colecaoRetorno;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * pesquisa quantidade de reiterações do RA
	 *  
	 * @author Vivianne Sousa
	 * @data 18/05/2011
	 */
	public Short pesquisarQtdeReiteracaoRA(Integer idRegistroAtendimento) throws ControladorException {
		try {
			 return repositorioRegistroAtendimento.pesquisarQtdeReiteracaoRA(idRegistroAtendimento);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0428] Imprimir Registro Atendimento
	 * 
	 * @author Rodrigo Cabral
	 * @date 10/05/2011
	 */
	public Collection pesquisarDadosReiteracao(
			Integer idRA)throws ControladorException {
		try {
			return repositorioRegistroAtendimento.pesquisarDadosReiteracao(idRA);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 18/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirRASituacaoLigacaoImovel(
			Integer idMeioSolicitacao,
			Integer idSolicitacaoTipoEspecificacao, 
			Integer idImovel,
			Integer idUnidadeAtendimento, 
			Integer idUsuarioLogado,
			Integer idCliente) throws ControladorException {
		
		
		RegistroAtendimento ra = new RegistroAtendimento();
		
		ra.setIndicadorAtendimentoOnline(new Short("2"));
		
		
		ra.setRegistroAtendimento(new Date());
		
		// Meio de Solicitação
		MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
		meioSolicitacao.setId(idMeioSolicitacao);
		
		ra.setMeioSolicitacao(meioSolicitacao);
		
		// Especificação
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(idSolicitacaoTipoEspecificacao);
		
		ra.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		
		ra.setDataPrevistaOriginal(new Date());
		ra.setDataPrevistaAtual(new Date());
		
		// Imóvel
		if (idImovel != null) {
			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			ra.setImovel(imovel);
		}
		
		ra.setIndicadorCoordenadaSemLogradouro(new Short("2"));
		
		// Código da Situação
		ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
		
		ra.setDataEncerramento(new Date());
		
		// inclusão da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
		// Vivianne Sousa 10/06/2008 analista:Fátima Sampaio
		ra.setUnidadeAtual(null);
		
		
		// última alteração
		ra.setUltimaAlteracao(new Date());
		
		Integer idRAGerado = (Integer) this.getControladorUtil().inserir(ra);
		ra.setId(idRAGerado);
		
		// [SB0028] Inclui Registro de Atendimento
		// (REGISTRO_ATENDIMENTO_UNIDADE)
		this.inserirRegistroAtendimentoUnidade(ra, idUnidadeAtendimento,
				idUsuarioLogado);
		
		/*
		 * [UC0366] Inserir Registro de Atendimento [SB0027] Inclui Solicitante
		 * do Registro de Atendimento
		 */
		this.inserirRegistroAtendimentoSolicitante(idRAGerado, idCliente,
				null, null,
				null, false, null,
				null, null, null, null, null, null);
	}
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public RegistroAtendimentoSolicitante pesquisarDadosEnvioEmailPesquisaPortal(int idRegistroAtendimentoSolicitante) 
		throws ControladorException{
		try {
			 return repositorioRegistroAtendimento.pesquisarDadosEnvioEmailPesquisaPortal(idRegistroAtendimentoSolicitante);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1181] Registrar Informacao de Pesquisa de Satisfacao
	 * 
	 * @author Paulo Diniz
	 * @date 27/06/2011
	 */
	public int registrarQuestionarioSatisfacaoCliente(QuestionarioSatisfacaoCliente questionario) throws ControladorException{
		try {
			 return (Integer) getControladorUtil().inserir(questionario);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1181] Registrar Informacoes de Pesquisa de Satisfacao
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public boolean verificaExistenciaQuestionarioSatisfacaoRespondido(Integer idRA)
			throws ControladorException{
		try {
			return repositorioRegistroAtendimento.verificaExistenciaQuestionarioSatisfacaoRespondido(idRA);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [RM 1094] Enviar Email com para Questionario de Satisfacao do Cliente
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @created 28/06/2011
	 */
    private void enviarEmailQuestionarioSatisfacao(String receptor, String emailDestinatario, String protocolo, String idRegistroAtendimentoEncerrado) throws ControladorException{
    	
    	SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
    	EnvioEmail envioEmail = 
    		this.getControladorCadastro().pesquisarEnvioEmail(
				EnvioEmail.ENVIAR_QUESTIONARIO_SATISFACAO_CLIENTE);
    	
    	if(envioEmail != null){
    		
    		String emailRemetente = envioEmail.getEmailRemetente();
    		
    		String emailReceptor = envioEmail.getEmailReceptor();
    		
    		String tituloMensagem = envioEmail.getTituloMensagem();
    		
    		String mensagem = "<img src='"+sistemaParametro.getUrlAcessoInternet()+"/imagens/portal/general/logo-compesa.gif' /><br /><br />"; 
    		mensagem +=	"<b>Olá Sr(a) " + receptor +",</b> <br />";
    		mensagem += "Gostaríamos de saber se você foi bem atendido quando entrou " +
    		"em contato conosco referente à solicitação do serviço associado ao protocolo " + protocolo + "<br /><br />";
    		
    		mensagem += "Estamos trabalhando para melhorar cada vez mais o nosso atendimento e precisamos da sua ajuda.";
    		
    		mensagem += "Por favor, responda a nossa pesquisa clicando no link abaixo: <br /><br />";
    		
    		mensagem += "<a style='color: blue; ' href='"+ sistemaParametro.getUrlAcessoInternet()+"/exibirQuestionarioSatisfacaoAction.do?idRegistroAtendimentoEncerrado="+idRegistroAtendimentoEncerrado 
    		+ "'>Questionário</a> <br /><br />";
    		
    		mensagem += "Agradecemos antecipadamente a sua colaboração para melhor atendê-lo.<br /><br />";
    		
    		mensagem += "COMPESA<br /><br /><br />";
    		
    		mensagem += "<div style='font-size: 10px'>Esta é uma mensagem automática, este email não deve ser respondido. " +
    		"Caso queira entrar em contato com a <b>COMPESA</b>, acesse o site www.compesa.com.br " +
    		"e selecione a opção <b>FALE CONOSCO</b> ou ligue para o telefone <b>0800 081 0195</b>. </div>";
    		
    		Collection<String> destinatarios = new ArrayList<String>();
    		destinatarios.add(emailDestinatario);
    		if(emailReceptor != null && !emailReceptor.equals("")
    				&& emailReceptor.contains("@")){
    			destinatarios.add(emailReceptor);
    		}
    		try {
    			ServicosEmail.enviarMensagemHTMLSemMsgTextoEmail(destinatarios, emailRemetente, "COMPESA",
    					tituloMensagem, null, mensagem);
    		} catch (ErroEmailException erroEnviarEmail) {
    			
    		}
    	}

    }
    
    /**
	 * Consulta o registro atendimento solicitante pelo id do registro atendimento
	 * 
	 * @author Rafael Pinto
     * @throws ControladorException 
	 * @created 09/08/2006
	 */
	private RegistroAtendimentoSolicitante consultarRegistroAtendimentoSolicitante(Integer idRegistroAtendimento) throws ControladorException{

		RegistroAtendimentoSolicitante retorno = null;
		
		Collection colecaoRegistroAtendimento = null; 

		FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimento = new FiltroRegistroAtendimentoSolicitante();

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID,idRegistroAtendimento));

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimentoSolicitante.INDICADOR_SOLICITANTE_PRINCIPAL,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		
		colecaoRegistroAtendimento = getControladorUtil().pesquisar(filtroRegistroAtendimento,
				RegistroAtendimentoSolicitante.class.getName());

		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			retorno = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
			
		} 
		
		return retorno;
	}
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosRA(
			Integer idRa,String descricaoPontoreferencia, String numeroImovel)
			throws ControladorException {
		try {
			 repositorioRegistroAtendimento.atualizarDadosRA(idRa,descricaoPontoreferencia,numeroImovel);
		} catch (ErroRepositorioException erx) {
			erx.printStackTrace();
			throw new ControladorException("erro.sistema", erx);
		}
	}
	
	/**
	 * TODO: COSANPA
	 * 
	 * Mantis 652 - Gerar relatório de RA por Unidade por Usuário
	 * 
	 * @author Wellington Rocha
	 * @date 18/12/2012
	 */
	public List<RAPorUnidadePorUsuarioHelper> filtrarRelatorioRegistroAtendimentoPorUnidadePorUsuario(
			FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper filtro) throws ControladorException {
		try {
			return repositorioRegistroAtendimento.filtrarRelatorioRegistroAtendimentoPorUnidadePorUsuario(filtro);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
}
