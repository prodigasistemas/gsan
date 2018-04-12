package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.EmpresaCobranca;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaCobranca;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocal;
import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocalHome;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaGeral;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.Usuario;
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
import gcom.util.filtro.Filtro;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;

public class UC0870GerarMovimentoContasEmCobrancaPorEmpresa {

	private static UC0870GerarMovimentoContasEmCobrancaPorEmpresa instancia;

	private IRepositorioCobranca repositorioCobranca;

	private UC0870GerarMovimentoContasEmCobrancaPorEmpresa(IRepositorioCobranca repositorioCobranca) {
		this.repositorioCobranca = repositorioCobranca;
	}

	public static UC0870GerarMovimentoContasEmCobrancaPorEmpresa getInstancia(IRepositorioCobranca repositorioCobranca) {
		if (instancia == null) {
			instancia = new UC0870GerarMovimentoContasEmCobrancaPorEmpresa(repositorioCobranca);
		}
		return instancia;
	}

	protected ControladorUtilLocal getControladorUtil() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();
			ControladorUtilLocalHome localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);

			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorImovelLocal getControladorImovel() {

		try {
			ServiceLocator locator = ServiceLocator.getInstancia();
			ControladorImovelLocalHome localHome = (ControladorImovelLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);

			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorCobrancaPorResultadoLocal getControladorCobrancaPorResultado() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();
			ControladorCobrancaPorResultadoLocalHome localHome = (ControladorCobrancaPorResultadoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_COBRANCA_POR_RESULTADO_SEJB);

			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorCobrancaLocal getControladorCobranca() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();
			ControladorCobrancaLocalHome localHome = (ControladorCobrancaLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);

			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorBatchLocal getControladorBatch() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();
			ControladorBatchLocalHome localHome = (ControladorBatchLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	public void gerarMovimentoContasEmCobranca(ComandoEmpresaCobrancaConta comando) throws ControladorException {
		try {
			SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();
			
			Integer anoMesArrecadacaoInicio = sistemaParametros.getAnoMesArrecadacao();
			Integer anoMesArrecadacaoFim = Util.somaMesAnoMesReferencia(sistemaParametros.getAnoMesArrecadacao(), 3);
			
			if (comando.getDataInicioCiclo() != null && comando.getDataFimCiclo() != null) {
				anoMesArrecadacaoInicio = Util.recuperaAnoMesDaData(comando.getDataInicioCiclo());
				anoMesArrecadacaoFim = Util.recuperaAnoMesDaData(comando.getDataFimCiclo());
			} 

			EmpresaCobranca empresa = filtrarEmpresaCobranca(comando);
			ComandoEmpresaCobrancaContaHelper helper = montarHelper(comando);
			ServicoTipo servicoTipo = filtrarServicoTipo(comando);

			List<Integer> idsImoveisAtualizar = new ArrayList<Integer>();
			List<CobrancaSituacaoHistorico> listaCobrancaSituacaoHistorico = new ArrayList<CobrancaSituacaoHistorico>();
			List<ImovelCobrancaSituacao> listaImovelCobrancaSituacao = new ArrayList<ImovelCobrancaSituacao>();
			
			List<Integer> idsImoveis = getControladorCobrancaPorResultado().pesquisarImoveis(helper, empresa.isPercentualInformado(), sistemaParametros.getAnoMesFaturamento());
			
			for (Integer idImovel : idsImoveis) {

				List<EmpresaCobrancaConta> listaEmpresaCobrancaConta = new ArrayList<EmpresaCobrancaConta>();

				List<Object[]> contas = getControladorCobrancaPorResultado().pesquisarContas(idImovel, helper, empresa.isPercentualInformado());
				
				for (Object[] conta : contas) {
					EmpresaCobrancaConta empresaCobrancaConta = criarEmpresaCobrancaConta(idImovel, conta, comando, empresa, contas.size());
					
					Integer idOS = null;
					if (!idsImoveisAtualizar.contains(idImovel)) {
						listaCobrancaSituacaoHistorico.add(criarCobrancaSituacaoHistorico(idImovel, anoMesArrecadacaoInicio, anoMesArrecadacaoFim));
						listaImovelCobrancaSituacao.add(criarImovelCobrancaSituacao(idImovel, comando));
						
						this.incluirImovelEmCobranca(idImovel, Usuario.USUARIO_BATCH);
						
						idOS = gerarOS(servicoTipo, idImovel, idOS);
						
						idsImoveisAtualizar.add(idImovel);
					}

					if (idOS != null) {
						OrdemServico ordemServico = new OrdemServico();
						ordemServico.setId(idOS);

						empresaCobrancaConta.setOrdemServico(ordemServico);
					}

					listaEmpresaCobrancaConta.add(empresaCobrancaConta);
				}
				
				getControladorBatch().inserirColecaoObjetoParaBatch(listaEmpresaCobrancaConta);
				
				if (comando.isQtdMaximaInformada() && idsImoveisAtualizar.size() == comando.getQtdMaximaClientes()) {
					break;
				}
			}

			getControladorBatch().inserirColecaoObjetoParaBatch(listaCobrancaSituacaoHistorico);
			getControladorBatch().inserirColecaoObjetoParaBatch(listaImovelCobrancaSituacao);

			Integer idCobrancaSituacao = repositorioCobranca.pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
			if (idCobrancaSituacao != null && idsImoveisAtualizar != null && !idsImoveisAtualizar.isEmpty()) {
				getControladorImovel().atualizarSituacaoCobrancaETipoIdsImoveis(idCobrancaSituacao, CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA, idsImoveisAtualizar);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private Integer gerarOS(ServicoTipo servicoTipo, Integer idImovel, Integer idOS) {
		if (servicoTipo != null && servicoTipo.getId() != null) {
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(idImovel));

			if (servicoTipo != null && servicoTipo.getId() != null) {
				UnidadeOrganizacional unidadeOrganizacional = Fachada.getInstancia().pesquisarUnidadeOrganizacionalPorImovel(imovel.getId());

				OrdemServico ordemServico = new OrdemServico();
				ordemServico.setServicoTipo(servicoTipo);
				ordemServico.setImovel(imovel);
				ordemServico.setUnidadeAtual(unidadeOrganizacional);

				idOS = Fachada.getInstancia().gerarOrdemServico(ordemServico, Usuario.USUARIO_BATCH, Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA);
			}
		}
		return idOS;
	}

	private ComandoEmpresaCobrancaContaHelper montarHelper(ComandoEmpresaCobrancaConta comando) {
		ComandoEmpresaCobrancaContaHelper helper = new ComandoEmpresaCobrancaContaHelper();
		helper.setComando(comando);
		helper.setIdsGerenciaRegional(filtrarGerenciaRegional(comando, helper));
		helper.setIdsUnidadeNegocio(filtrarUnidadeNegocio(comando, helper));
		helper.setIdsImovelPerfil(filtrarImovelPerfil(comando, helper));
		helper.setIdsLigacaoAguaSituacao(filtrarLigacaoAguaSituacao(comando, helper));
		return helper;
	}

	@SuppressWarnings("unchecked")
	private EmpresaCobranca filtrarEmpresaCobranca(ComandoEmpresaCobrancaConta comando) throws ControladorException {
		Filtro filtro = new FiltroEmpresaCobranca();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresaCobranca.EMPRESA_ID, comando.getEmpresa().getId()));
		filtro.adicionarParametro(new MaiorQue(FiltroEmpresaCobranca.DATA_FIM_CONTRATO, new Date()));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroEmpresaCobranca.EMPRESA);

		Collection<EmpresaCobranca> colecaoEmpresaCobranca = getControladorUtil().pesquisar(filtro, EmpresaCobranca.class.getName());

		if (colecaoEmpresaCobranca != null && !colecaoEmpresaCobranca.isEmpty()) {
			return (EmpresaCobranca) Util.retonarObjetoDeColecao(colecaoEmpresaCobranca);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private ServicoTipo filtrarServicoTipo(ComandoEmpresaCobrancaConta comando) {
		ServicoTipo servicoTipo = null;
		if (comando.getServicoTipo() != null && comando.getServicoTipo().getId() != null) {
			Filtro filtro = new FiltroServicoTipo();
			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, comando.getServicoTipo().getId()));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoTipo.SERVICO_TIPO_REFERENCIA);
			Collection<ServicoTipo> colecao = Fachada.getInstancia().pesquisar(filtro, ServicoTipo.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecao);
			}
		}
		
		return servicoTipo;
	}

	@SuppressWarnings("rawtypes")
	private List<Integer> filtrarLigacaoAguaSituacao(ComandoEmpresaCobrancaConta comando, ComandoEmpresaCobrancaContaHelper helper) {
		List<Integer> lista = new ArrayList<Integer>();
		
		Filtro filtro = new FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comando.getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao.LIGACAO_AGUA_SITUACAO);
		
		Collection colecao = Fachada.getInstancia().pesquisar(filtro, CmdEmpresaCobrancaContaLigacaoAguaSituacao.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();
			while (iterator.hasNext()) {
				CmdEmpresaCobrancaContaLigacaoAguaSituacao comandoLigacaoAguaSituacao = (CmdEmpresaCobrancaContaLigacaoAguaSituacao) iterator.next();
				LigacaoAguaSituacao ligacaoAguaSituacao = comandoLigacaoAguaSituacao.getLigacaoAguaSituacao();
				lista.add(ligacaoAguaSituacao.getId());
			}
			return lista;
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private List<Integer> filtrarImovelPerfil(ComandoEmpresaCobrancaConta comando, ComandoEmpresaCobrancaContaHelper helper) {
		List<Integer> lista = new ArrayList<Integer>();
		
		Filtro filtro = new FiltroComandoEmpresaCobrancaContaImovelPerfil();
		filtro.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaContaImovelPerfil.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comando.getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		
		Collection colecao = Fachada.getInstancia().pesquisar(filtro, ComandoEmpresaCobrancaContaImovelPerfil.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();
			while (iterator.hasNext()) {
				ComandoEmpresaCobrancaContaImovelPerfil comandoImovelPerfil = (ComandoEmpresaCobrancaContaImovelPerfil) iterator.next();
				lista.add(comandoImovelPerfil.getImovelPerfil().getId());
			}
			return lista;
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private List<Integer> filtrarGerenciaRegional(ComandoEmpresaCobrancaConta comando, ComandoEmpresaCobrancaContaHelper helper) {
		List<Integer> lista = new ArrayList<Integer>();
		
		Filtro filtro = new FiltroComandoEmpresaCobrancaContaGerencia();
		filtro.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaContaGerencia.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comando.getId()));
		Collection colecao = Fachada.getInstancia().pesquisar(filtro, ComandoEmpresaCobrancaContaGerencia.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();
			while (iterator.hasNext()) {
				lista.add(((GerenciaRegional) iterator.next()).getId());
			}
			return lista;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private List<Integer> filtrarUnidadeNegocio(ComandoEmpresaCobrancaConta comando, ComandoEmpresaCobrancaContaHelper helper) {
		List<Integer> lista = new ArrayList<Integer>();

		Filtro filtro = new FiltroComandoEmpresaCobrancaContaUnidadeNegocio();
		filtro.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaContaUnidadeNegocio.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comando.getId()));
		Collection colecao = Fachada.getInstancia().pesquisar(filtro, ComandoEmpresaCobrancaContaUnidadeNegocio.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();
			while (iterator.hasNext()) {
				lista.add(((UnidadeNegocio) iterator.next()).getId());
			}
			return lista;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private EmpresaCobrancaConta criarEmpresaCobrancaConta(Integer idImovel, Object[] dados, 
			ComandoEmpresaCobrancaConta comando, EmpresaCobranca empresa, int quantidadeContas) throws ControladorException, ErroRepositorioException {
		
		EmpresaCobrancaConta retorno = new EmpresaCobrancaConta();
		retorno.setImovel(new Imovel(idImovel));

		if (dados[0] != null) {
			ContaGeral contaGeral = new ContaGeral();
			contaGeral.setId((Integer) dados[0]);
			retorno.setContaGeral(contaGeral);
		}

		if (dados[1] != null) {
			retorno.setValorOriginalConta((BigDecimal) dados[1]);
		}

		if (dados[2] != null) {
			retorno.setReferencia((Integer) dados[2]);
		}

		if (empresa != null) {
			if (empresa.getPercentualContratoCobranca() != null) {
				retorno.setPercentualEmpresaConta(empresa.getPercentualContratoCobranca());
			} else {
				Filtro filtro = new FiltroEmpresaCobrancaFaixa();
				filtro.adicionarParametro(new ParametroSimples(FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresa.getId()));

				List<EmpresaCobrancaFaixa> faixas = (List<EmpresaCobrancaFaixa>) Fachada.getInstancia().pesquisar(filtro, EmpresaCobrancaFaixa.class.getName());

				if (faixas != null && !faixas.isEmpty()) {
					for (int i = 0; i < faixas.size(); i++) {
						EmpresaCobrancaFaixa faixa = (EmpresaCobrancaFaixa) faixas.get(i);

						Integer minimoContas = faixa.getNumeroMinimoContasFaixa();

						Integer maximoContas = null;
						if (i < (faixas.size() - 1)) {
							maximoContas = ((EmpresaCobrancaFaixa) faixas.get(i + 1)).getNumeroMinimoContasFaixa() - 1;
						}

						if (quantidadeContas >= minimoContas && (maximoContas == null || quantidadeContas <= maximoContas)) {
							retorno.setPercentualEmpresaConta(faixa.getPercentualFaixa());
							break;
						}
					}
				}
			}
		}

		retorno.setIndicadorPagamentoValido(ConstantesSistema.SIM);
		retorno.setDataEnvio(new Date());
		retorno.setEmpresa(comando.getEmpresa());
		retorno.setComandoEmpresaCobrancaConta(comando);
		retorno.setUltimaAlteracao(new Date());

		return retorno;
	}

	private CobrancaSituacaoHistorico criarCobrancaSituacaoHistorico(Integer idImovel, Integer anoMesArrecadacaoInicio, Integer anoMesArrecadacaoFim) {
		CobrancaSituacaoHistorico historico = new CobrancaSituacaoHistorico();
		historico.setImovel(new Imovel(idImovel));

		CobrancaSituacaoMotivo motivo = new CobrancaSituacaoMotivo();
		motivo.setId(CobrancaSituacaoMotivo.IMOVEIS_ENVIADOS_EMPRESA_TERCEIRIZADA);
		historico.setCobrancaSituacaoMotivo(motivo);

		CobrancaSituacaoTipo tipo = new CobrancaSituacaoTipo();
		tipo.setId(CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA);
		historico.setCobrancaSituacaoTipo(tipo);
		
		historico.setUltimaAlteracao(new Date());
		historico.setAnoMesCobrancaSituacaoInicio(anoMesArrecadacaoInicio);
		historico.setAnoMesCobrancaSituacaoFim(anoMesArrecadacaoFim);
		historico.setUsuario(Usuario.USUARIO_BATCH);
		historico.setUsuarioInforma(Usuario.USUARIO_BATCH);

		return historico;
	}

	private ImovelCobrancaSituacao criarImovelCobrancaSituacao(Integer idImovel, ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) throws ControladorException, ErroRepositorioException {
		ImovelCobrancaSituacao situacao = new ImovelCobrancaSituacao();
		situacao.setImovel(new Imovel(idImovel));

		situacao.setDataImplantacaoCobranca(new Date());
		situacao.setUltimaAlteracao(new Date());

		Integer idCobrancaSituacao = repositorioCobranca.pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);

		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		cobrancaSituacao.setId(idCobrancaSituacao);
		situacao.setCobrancaSituacao(cobrancaSituacao);

		Cliente cliente = getControladorImovel().pesquisarClienteUsuarioImovel(situacao.getImovel().getId());
		situacao.setCliente(cliente);

		return situacao;
	}
	
	private void incluirImovelEmCobranca(Integer idImovel, Usuario usuario) throws ControladorException {
		getControladorImovel().incluirImovelCobranca(CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA, CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA, idImovel, usuario);
	}
}
