package gcom.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.seguranca.acesso.Operacao;
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
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Defini��o da l�gica de neg�cio do Session Bean de ControladorLigacaoAgua
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public class ControladorLigacaoAguaSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;

	SessionContext sessionContext;

	private IRepositorioLigacaoAgua repositorioLigacaoAgua = null;

	/**
	 * @exception CreateException
	 */
	public void ejbCreate() throws CreateException {
		repositorioLigacaoAgua = RepositorioLigacaoAguaHBM.getInstancia();
	}

	/**
	 */
	public void ejbRemove() {
	}

	/**
	 */
	public void ejbActivate() {
	}

	/**
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

		// pega a inst�ncia do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
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
		// pega a inst�ncia do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
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
	 * Retorna o valor do ControladorImovel
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @return O valor de controladorMicromedicao
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
	 * [UC0463] Atualizar Consumo M�nimo de Liga��o �gua
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * atualiza��o do consumo m�nimo da liga��o de agua
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param OrdemServico
	 * @param veioEncerrarOS
	 */
	public void validarExibirAtualizarConsumoMinimoLigacaoAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException {
		
		/*
		 * Caso o SERVICO_TIPO da ordem de servi�o recebida esteja associado a opera��o
		 * EFETUAR ATUALIZACAO CONSUMO MINIMO LIGACAO AGUA, n�o ser� necess�rio realizar as valida��es abaixo.
		 * 
		 * Autor: Raphael Rossiter
		 * Data: 26/04/2007
		 *
		 */
		Integer idOperacao = this.getControladorOrdemServico()
		.pesquisarServicoTipoOperacao(ordemServico.getServicoTipo().getId());
		
		if (idOperacao == null || 
			idOperacao.intValue() != Operacao.OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR_INT){
			
			//[FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CALCULAR_CONSUMO_MINIMO_AGUA) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.atualizar_consumo_minimo_ligacao_agua_servico");
			}
		}
	
		/*
		 * Valida��es j� contidas no m�todo anteriormente
		 * Autor: Raphael Rossiter
		 * Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */
		
		//Caso 3
		this.getControladorOrdemServico().validaOrdemServico(ordemServico,
				veioEncerrarOS);
		// Caso 4
		if (ordemServico.getRegistroAtendimento().getImovel() == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordemServico.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		// [FS0002] Verificar Situa��o do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.naocadastrado", null,
					"Liga��o de �gua");
		}
		
		/*
		 * ===================================================================================
		 */
	}

	/**
	 * [UC0463] Atualizar Consumo M�nimo de Liga��o de �gua
	 * 
	 * [FS004] Validar Consumo M�nimo
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param imovel
	 */
	public void validarConsumoMinimoLigacaoAgua(Imovel imovel)
			throws ControladorException {
		// Valida��es da liga��o de �gua
		if (imovel.getLigacaoAgua() != null) {
			// [UC105] Obter Consumo M�nimo da Liga��o
			int volumeObtido = getControladorMicromedicao()
					.obterConsumoMinimoLigacao(imovel, null);
			if (imovel.getLigacaoAgua().getNumeroConsumoMinimoAgua().intValue() < volumeObtido) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.atualizar_situacao_consumo_minimo_fixado_menor_minimo",
						null, volumeObtido + "");
			}
		} else {
			// Se entrar aqui significa que a base est� inconsistente.
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_consumo_minimo_ligacao_agua_invalida",
					null, imovel.getId().toString());
		}
	}

	/**
	 * [UC0463] Atualizar Consumo M�nimo da Liga��o de �gua
	 * 
	 * [SB0001] Atualizar Liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @throws ControladorException
	 */
	public void atualizarConsumoMinimoLigacaoAgua(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {
		LigacaoAgua ligacaoAgua = integracaoComercialHelper.getLigacaoAgua();
		ligacaoAgua.setUltimaAlteracao(new Date());
		this.verificarLigacaoAguaControleConcorrencia(ligacaoAgua);
		try {
			
			// Inicio Registrar Transa��o
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR,
					ligacaoAgua.getId(), ligacaoAgua.getId(),   
					new UsuarioAcaoUsuarioHelper(integracaoComercialHelper.getUsuarioLogado(),
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			registradorOperacao.registrarOperacao(ligacaoAgua);
			getControladorTransacao().registrarTransacao(ligacaoAgua);
			// Fim Registrar Transa��o
			
			// Atualizar Tabela Liga��o de �gua com novo valor de consumo m�nimo
			repositorioLigacaoAgua
					.atualizarConsumoMinimoLigacaoAgua(ligacaoAgua);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades do
	 * corte liga��o de agua
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirCorteLigacaoAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException {

		
		/*
		 * Caso o SERVICO_TIPO da ordem de servi�o recebida esteja associado a opera��o
		 * EFETUAR CORTE LIGA��O DE �GUA, n�o ser� necess�rio realizar as valida��es abaixo.
		 * 
		 * Autor: Raphael Rossiter
		 * Data: 26/04/2007
		 *
		 */
		Integer idOperacao = this.getControladorOrdemServico()
		.pesquisarServicoTipoOperacao(ordemServico.getServicoTipo().getId());
		
		if (idOperacao == null || 
			idOperacao.intValue() != Operacao.OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR_INT){
		
			//[FS0001] Validar Ordem de Servico
			// Caso 2
			if (ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CORTE_LIGACAO_AGUA) {
				throw new ControladorException(
						"atencao.servico_associado_corte_ligacao_agua_invalida");

			}
			
		}
		
		
		/*
		 * Valida��es j� contidas no m�todo anteriormente
		 * Autor: Raphael Rossiter
		 * Data: 26/04/2007
		 * 
		 * ===============================================================================
		 */
		
		//Caso 3
//		this.getControladorOrdemServico().validaOrdemServico(ordemServico,veioEncerrarOS);
		this.getControladorOrdemServico().validaOrdemServicoDiasAditivoPrazo(ordemServico,veioEncerrarOS);
		// Caso 4
		// Comentado por Raphael Rossiter em 26/02/2007
		/*if (ordemServico.getRegistroAtendimento().getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_ra_imovel_invalida", null, ""
							+ ordemServico.getRegistroAtendimento().getId());
		}*/
		
		if (ordemServico.getImovel() == null) {
			throw new ControladorException(
					"atencao.ordem_servico_imovel_invalido");
		}

		//Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel = ordemServico.getImovel();

		// [FS0002] Verificar Situa��o do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {

			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null, imovel
							.getId().toString());
		}

		if (imovel.getLigacaoAgua() == null) {
			throw new ControladorException("atencao.naocadastrado", null,
					"Liga��o de �gua");
		}

		// [FS0003] Verificar a situa��o de �gua
		int idLigacaoAguaSituacao = imovel.getLigacaoAguaSituacao().getId().intValue();
		if (idLigacaoAguaSituacao != LigacaoAguaSituacao.LIGADO.intValue() &&
				idLigacaoAguaSituacao != LigacaoAguaSituacao.LIGADO_EM_ANALISE.intValue()) {


			throw new ControladorException(
					"atencao.situacao_agua_imovel_invaliga", null, imovel
							.getLigacaoAguaSituacao().getDescricao()
							+ "");
		}
		
		/*
		 * ===================================================================================
		 */
	}

	/**
	 * [UC0355] Efetuar Corte de Liga��o de �gua.
	 * 
	 * @author Leonardo Regis.
	 * @date 25/09/2006
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public void efetuarCorteLigacaoAgua(
			IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException {

		DadosEfetuacaoCorteLigacaoAguaHelper helper = integracaoComercialHelper
				.getDadosEfetuacaoCorteLigacaoAguaHelper();

		this.getControladorImovel().verificarImovelControleConcorrencia(
				helper.getImovel());
		LigacaoAgua ligacaoAgua = helper.getImovel().getLigacaoAgua();
		this.verificarLigacaoAguaControleConcorrencia(ligacaoAgua);
		
		
		/*
		 * [UC0107] Registrar Transa��o
		 * 
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR,
				ligacaoAgua.getId(), ligacaoAgua.getId(),
				new UsuarioAcaoUsuarioHelper(integracaoComercialHelper.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		registradorOperacao.registrarOperacao(ligacaoAgua);
		getControladorTransacao().registrarTransacao(ligacaoAgua);
		try {
			// Efetuar Corte Liga��o de �gua
			repositorioLigacaoAgua.efetuarCorteLigacaoAgua(helper);
			if (!helper.isVeioEncerrarOS()) {
				this.getControladorOrdemServico().atualizaOSGeral(
						helper.getOrdemServico());
			}
			if (helper.getOrdemServico().getServicoTipo().getDebitoTipo() != null
					&& (helper.getOrdemServico().getServicoNaoCobrancaMotivo() == null || helper
							.getOrdemServico().getServicoNaoCobrancaMotivo()
							.getId().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				// Gerar D�bito OS
				this.getControladorOrdemServico().gerarDebitoOrdemServico(
						helper.getOrdemServico().getId(),
						helper.getOrdemServico().getServicoTipo().getDebitoTipo().getId(),
						helper.getOrdemServico().getValorAtual(), helper.getQtdeParcelas(),
						helper.getOrdemServico().getPercentualCobranca().toString(), integracaoComercialHelper.getUsuarioLogado());
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0369] Efetuar Corte Administrativo Liga��o de Agua
	 * 
	 * @author Thiago Ten�rio
	 * @date 30/06/2006
	 * 
	 * @param helper
	 * @throws ControladorException
	 */

	public void efetuarCorteAdministrativoLigacaoAgua(
			DadosEfetuacaoCorteLigacaoAguaHelper helper, Usuario usuario)
			throws ControladorException {

		this.verificarLigacaoAguaControleConcorrencia(helper.getLigacaoAgua());
		

		/*
		 * [UC0107] Registrar Transa��o
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR,
				helper.getLigacaoAgua().getId(), helper.getLigacaoAgua().getId(), 
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(helper.getLigacaoAgua());
		getControladorTransacao().registrarTransacao(helper.getLigacaoAgua());
		// [UC0107] -Fim- Registrar Transa��o	
		
		try {
			// Efetuar Corte Liga��o de �gua
			repositorioLigacaoAgua
					.efetuarCorteAdministrativoLigacaoAgua(helper);
			if (!helper.isVeioEncerrarOS()) {
				this.getControladorOrdemServico().atualizaOSGeral(
						helper.getOrdemServico());
			}
			if (helper.getOrdemServico().getServicoTipo().getDebitoTipo() != null
					&& (helper.getOrdemServico().getServicoNaoCobrancaMotivo() == null || helper
							.getOrdemServico().getServicoNaoCobrancaMotivo()
							.getId().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				// Gerar D�bito OS
				this.getControladorOrdemServico().gerarDebitoOrdemServico(
						helper.getOrdemServico().getId(),
						helper.getOrdemServico().getServicoTipo()
								.getDebitoTipo().getId(),
						helper.getOrdemServico().getValorAtual(),
						helper.getQtdeParcelas(),
						helper.getOrdemServico().getPercentualCobranca().toString(), usuario);
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0463] Efetuar Restabelecimento da Liga��o de �gua
	 * 
	 * [SB0001] Atualizar Im�vel/Liga��o �gua
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaRestabelecimento(LigacaoAgua ligacaoAgua)
			throws ControladorException {
		try {
			repositorioLigacaoAgua
					.atualizarLigacaoAguaRestabelecimento(ligacaoAgua);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0357] Efetuar Religa��o de �gua
	 * 
	 * [SB0001] Atualizar Im�vel/Liga��o �gua
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaReligacao(LigacaoAgua ligacaoAgua)
			throws ControladorException {
		try {
			repositorioLigacaoAgua.atualizarLigacaoAguaReligacao(ligacaoAgua);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da Ligacao de �gua
	 * (id,dataCorte,dataSupressao)
	 * 
	 * @author S�vio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public LigacaoAgua recuperaParametrosLigacaoAgua(Integer idImovel)
			throws ControladorException {
		LigacaoAgua ligacaoAgua = null;
		if (idImovel != null) {
			try {
				Object[] parmsLigacaoAgua = repositorioLigacaoAgua
						.pesquisarParmsLigacaoAgua(idImovel);
				if (parmsLigacaoAgua != null) {
					ligacaoAgua = new LigacaoAgua();
					// id liga��o de agua
					if (parmsLigacaoAgua[0] != null) {
						ligacaoAgua.setId((Integer) parmsLigacaoAgua[0]);
					}
					// data corte
					if (parmsLigacaoAgua[1] != null) {
						ligacaoAgua.setDataCorte((Date) parmsLigacaoAgua[1]);
					}
					// data supressao
					if (parmsLigacaoAgua[2] != null) {
						ligacaoAgua
								.setDataSupressao((Date) parmsLigacaoAgua[2]);
					}
				}

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		return ligacaoAgua;
	}

	/**
	 * 
	 * Pesquisa o id do hidrometro
	 * 
	 * @author S�vio Luiz
	 * @date 19/02/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdHidrometroInstalacaoHistorico(Integer idImovel)
			throws ControladorException {
		try {
			return repositorioLigacaoAgua
					.pesquisarIdHidrometroInstalacaoHistorico(idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0054] - Inserir Dados da Tarifa Social 
	 * 
	 * Recupera o consumo m�nimo fixado do Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 04/0/2006
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarConsumoMinimoFixado(Integer idImovel) throws ControladorException {
		try {
			return repositorioLigacaoAgua
					.pesquisarConsumoMinimoFixado(idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	public Collection verificaExistenciaLigacaoAgua(Integer idImovel){
		Collection retorno = null;
		

		try {
			retorno = repositorioLigacaoAgua.verificaExistenciaLigacaoAgua(idImovel);

		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
		}
				
		return retorno;
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
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Im�vel]
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoAguaSituacao
	 * @param idConsumoTipo
	 * @return LigacaoAguaSituacaoConsumoTipo
	 * @throws ControladorException
	 */
	public LigacaoAguaSituacaoConsumoTipo pesquisarLigacaoAguaSituacaoConsumoTipo(Integer idLigacaoAguaSituacao,
			Integer idConsumoTipo) throws ControladorException{
	
		try {
		
			return repositorioLigacaoAgua.pesquisarLigacaoAguaSituacaoConsumoTipo(
			idLigacaoAguaSituacao, idConsumoTipo);
		} 
		catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	/**
	 * 
	 * Atualiza o tipo de corte
	 * 
	 * Autor: Hugo Amorim
	 * 
	 * Data: 18/05/2009
	 */
	public void atualizarTipoCorte(IntegracaoComercialHelper helper) throws ControladorException{
			
		LigacaoAgua ligacaoAgua = helper.getLigacaoAgua();
		this.verificarLigacaoAguaControleConcorrencia(ligacaoAgua);
		this.getControladorImovel().verificarImovelControleConcorrencia(helper.getImovel());
		
		try {
			// Efetuar Corte Liga��o de �gua
			repositorioLigacaoAgua.atualizarTipoCorte(helper);
			if (!helper.isVeioEncerrarOS()) {
				this.getControladorOrdemServico().atualizaOSGeral(
					helper.getOrdemServico());
			}
			if (helper.getOrdemServico().getServicoTipo().getDebitoTipo() != null
					&& (helper.getOrdemServico().getServicoNaoCobrancaMotivo() == null || 
							helper.getOrdemServico().getServicoNaoCobrancaMotivo().getId().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				// Gerar D�bito OS
				this.getControladorOrdemServico().gerarDebitoOrdemServico(
						helper.getOrdemServico().getId(),
						helper.getOrdemServico().getServicoTipo().getDebitoTipo().getId(),
						helper.getOrdemServico().getValorAtual(),
						Integer.parseInt(helper.getQtdParcelas()),
						helper.getOrdemServico().getPercentualCobranca().toString(),
						helper.getUsuarioLogado());
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 *
	 * 
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 * 
	 *        Pesquisar Situa��es de Liga��o de �gua ativas.
	 * 
	 *        Gera��o de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<LigacaoAguaSituacao> pesquisarLigacaoAguaSituacao()
			throws ControladorException {
		try {
			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = new ArrayList<LigacaoAguaSituacao>();
			Collection colecao = repositorioLigacaoAgua.pesquisarLigacaoAguaSituacao();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator colecaoIterator = colecao.iterator();

				while (colecaoIterator.hasNext()) {
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) colecaoIterator
							.next();
					colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacao);
				}
			}

			return colecaoLigacaoAguaSituacao;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
}
