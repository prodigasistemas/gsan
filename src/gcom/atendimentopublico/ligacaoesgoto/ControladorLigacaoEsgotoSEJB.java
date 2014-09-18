package gcom.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Defini��o da l�gica de neg�cio do Session Bean de ControladorLigacaoEsgoto
 * 
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public class ControladorLigacaoEsgotoSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;

	SessionContext sessionContext;

	private IRepositorioLigacaoEsgoto repositorioLigacaoEsgoto = null;

	/**
	 * @exception CreateException
	 */
	public void ejbCreate() throws CreateException {
		repositorioLigacaoEsgoto = RepositorioLigacaoEsgotoHBM
				.getInstancia();
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
	 * @date 22/09/2006
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
	 * Retorna o valor do ControladorMicromedicao
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @return O valor de controladorMicromedicao
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
	 * [UC0367]Atualizar Liga��o de Esgoto no sistema.
	 * 
	 * [SB002] Atualiza liga��o de esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 17/07/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void atualizarLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto,Usuario usuario)
			throws ControladorException {
		this.verificarLigacaoEsgotoControleConcorrencia(ligacaoEsgoto);
		
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		    Operacao.OPERACAO_LIGACAO_ESGOTO_ATUALIZAR,
		    ligacaoEsgoto.getId(),ligacaoEsgoto.getId(),
		    new UsuarioAcaoUsuarioHelper(usuario,
		    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(ligacaoEsgoto);

		// ------------ REGISTRAR TRANSA��O ----------------
		
		// Atualiza Liga��o de Esgoto
		getControladorUtil().atualizar(ligacaoEsgoto);
	}

	/**
	 * Faz o controle de concorrencia de ligacao Esgoto
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	private void verificarLigacaoEsgotoControleConcorrencia(LigacaoEsgoto ligacaoEsgoto)
		throws ControladorException {
		
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
		filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, ligacaoEsgoto.getId()));
		
		Collection colecaoLigacao = getControladorUtil().pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());

		if (colecaoLigacao == null || colecaoLigacao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		LigacaoEsgoto ligacaoEsgotoAtualizado = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacao);
		
		if (ligacaoEsgotoAtualizado.getUltimaAlteracao().after(ligacaoEsgoto.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}
	
	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * liga��o de esgoto do im�vel no momento da exibi��o.
	 * 
	 * [FS0001] Verificar exist�ncia da matr�cula do im�vel. [FS0002] Verificar
	 * Situa��o do Imovel. [FS0003] Validar Situa��o de Esgoto do im�vel.
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @param ligacaoEsgoto
	 */
	public void validarLigacaoEsgotoImovelExibir(OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException {
			
		//[FS0001] Validar Ordem de Servico
		
		//Caso 2
		if(ordem.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_LIGACAO_ESGOTO &&
			ordem.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CONFIRMAR_DADOS_LIGACAO_ESGOTO ){
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.servico_associado_atualizar_ligacao_esgoto_invalida");
		}
		
		//Caso 3
		this.getControladorOrdemServico().validaOrdemServicoAtualizacao(ordem, veioEncerrarOS);
		
		//Caso 4
		if(ordem.getRegistroAtendimento().getImovel() == null ){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.ordem_servico_ra_imovel_invalida",null,""+ordem.getRegistroAtendimento().getId());
		}
		
		
		// [FS0009] Validar Data do Encerramento da Ordem de Servico
		if(ordem.getServicoTipo().getId().intValue() == ServicoTipo.TIPO_LIGACAO_ESGOTO){
			
			Date dataEncerramento = ordem.getDataEncerramento();			
			
			if(dataEncerramento != null){
				
				int qtdDias = Util.obterQuantidadeDiasEntreDuasDatas(dataEncerramento,new Date());	

				FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
				
				Collection colecao = 
					getControladorUtil().pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
				
				if(colecao != null && !colecao.isEmpty()){
					
					SistemaParametro sistemaParametro = 
						(SistemaParametro) Util.retonarObjetoDeColecao(colecao);
					
					int qtdMaximo = sistemaParametro.getDiasMaximoAlterarOS().intValue();
					
					if(qtdDias > qtdMaximo){
						
						String[] msg = new String[2];
						
						msg[0] = ""+ordem.getId(); 
						msg[1] = ""+qtdMaximo;
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.ligacao_esgoto_data_encerramento_os_invalida",null,msg);
					}
				}
			}
		}
		
		
		Imovel imovel = ordem.getRegistroAtendimento().getImovel();

		// Verificar Liga��o de Esgoto do Imovel.
		if (imovel.getLigacaoEsgoto() == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizar_ligacao_esgoto_inexistente",
											null, imovel.getId().toString());
		}
		
		// [FS0002] Verificar Situa��o do Imovel.
		if (imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_situacao_imovel_indicador_exclusao_esgoto",
					null, imovel.getId().toString());
		}
		
		// [FS003] Validar Situa��o de Esgoto do im�vel.
		if (imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.POTENCIAL
				.intValue()
				|| imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.FACTIVEL
						.intValue()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_situacao_ligacao_esgoto_invalida",
					null, imovel.getLigacaoEsgotoSituacao().getDescricao());
		}
		
		// [FS0009] Validar Data do Encerramento da Ordem de Servico
		if (ordem.getServicoTipo().getId().intValue() == ServicoTipo.TIPO_CONFIRMAR_DADOS_LIGACAO_ESGOTO) {
			Date dataEncerramento = ordem.getDataEncerramento();
			if (dataEncerramento != null) {
				int qtdDias = Util.obterQuantidadeDiasEntreDuasDatas(
						dataEncerramento, new Date());
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
										ordem.getId().toString(),
										qtdMaximo + "" });
					}
				}
			}
		}			
	}

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * liga��o de esgoto do im�vel no momento da atualiza��o.
	 * 
	 * [FS0006] Validar Percentual de Coleta. [FS0007] Validar Volume M�nimo
	 * Fixado.
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @param ligacaoEsgoto
	 */
	public void validarLigacaoEsgotoImovelAtualizar(Imovel imovel)
			throws ControladorException {

		// Valida��es da liga��o de esgoto
		if (imovel.getLigacaoEsgoto() != null) {

			// [FS0006] Validar Percentual de Coleta.
			if (imovel.getLigacaoEsgoto().getPercentualAguaConsumidaColetada()
					.intValue() > ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.atualizar_percentual_informado_maior_maximo_permitido",
						null, "");
			}
			
		} else {
			// Se entrar aqui significa que a base est� inconsistente.
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_ligacao_esgoto_invalida", null, imovel.getId().toString());
		}
	}
	
	/**
	 * [UC0464] Atualizar Volume M�nimo de Liga��o de Esgoto
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * atualiza��o do volume m�nimo da liga��o de esgoto
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param OrdemServico
	 * @param veioEncerrarOS
	 * @throws ControladorException
	 */
	public void validarExibirAtualizarVolumeMinimoLigacaoEsgoto(
			OrdemServico ordemServico, boolean veioEncerrarOS) throws ControladorException {
		
		/*
		 * Caso o SERVICO_TIPO da ordem de servi�o recebida esteja associado a opera��o
		 * EFETUAR ATUALIZACAO VOLUME MINIMO LIGACAO ESGOTO, n�o ser� necess�rio realizar as valida��es abaixo.
		 * 
		 * Autor: Raphael Rossiter
		 * Data: 26/04/2007
		 *
		 */
		Integer idOperacao = this.getControladorOrdemServico()
		.pesquisarServicoTipoOperacao(ordemServico.getServicoTipo().getId());
		
		if (idOperacao == null || 
			idOperacao.intValue() != Operacao.OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR_INT){
		
			//[FS0001] Validar Ordem de Servico
			//Caso 2
			if(ordemServico.getServicoTipo().getId().intValue() != ServicoTipo.TIPO_CALCULAR_VOLUME_MINIMO_ESGOTO){
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizar_volume_minimo_ligacao_esgoto_servico");
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
		this.getControladorOrdemServico().validaOrdemServico(ordemServico, veioEncerrarOS);
		
		//Caso 4
		if(ordemServico.getRegistroAtendimento().getImovel() == null ){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.ordem_servico_ra_imovel_invalida",null,
				""+ordemServico.getRegistroAtendimento().getId());
		}

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		// [FS0002] Verificar Situa��o do Imovel.
		if (imovel.getIndicadorExclusao() != null
				&& imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_imovel_situacao_invalida", null,
					imovel.getId().toString());
		}

		if(imovel.getLigacaoEsgoto() == null){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.naocadastrado",null, "Liga��o de Esgoto");
		}
		
		/*
		 * ===================================================================================
		 */
	}
	
	/**
	 * [UC0464] Atualizar Volume M�nimo de Liga��o de Esgoto
	 * 
	 * [FS004] Validar Volume M�nimo
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarVolumeMinimoLigacaoEsgoto(Imovel imovel) throws ControladorException {
		// Valida��es da liga��o de esgoto
		if (imovel.getLigacaoEsgoto() != null) {
			// [UC105] Obter Consumo M�nimo da Liga��o
			int volumeObtido = getControladorMicromedicao().obterConsumoMinimoLigacao(imovel, null);
			if (imovel.getLigacaoEsgoto().getConsumoMinimo().intValue() < volumeObtido){
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizar_situacao_volume_minimo_fixado_menor_minimo", null, volumeObtido+"");
			}
		} else {
			// Se entrar aqui significa que a base est� inconsistente.
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.atualizar_volume_minimo_ligacao_esgoto_invalida", null, imovel.getId().toString());
		}
	}
	
	/**
	 * [UC0464] Atualizar Volume M�nimo da Liga��o de Esgoto
	 * 
	 * [SB0001] Atualizar Liga��o de Esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void atualizarVolumeMinimoLigacaoEsgoto(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException {
		LigacaoEsgoto ligacaoEsgoto = integracaoComercialHelper.getLigacaoEsgoto();
		ligacaoEsgoto.setUltimaAlteracao(new Date());
		this.verificarLigacaoEsgotoControleConcorrencia(ligacaoEsgoto);
		
		/*
		 * [UC0107] Registrar Transa��o
		 * 
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR, ligacaoEsgoto.getId(),
				ligacaoEsgoto.getId(),
				new UsuarioAcaoUsuarioHelper(integracaoComercialHelper.getUsuarioLogado(),
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));		
		registradorOperacao.registrarOperacao(ligacaoEsgoto);
		getControladorTransacao().registrarTransacao(ligacaoEsgoto);
		// [UC0107] -Fim- Registrar Transa��o
		
		try {
			// Atualizar Tabela Liga��o de Esgoto com novo valor de volume m�nimo
			repositorioLigacaoEsgoto.atualizarVolumeMinimoLigacaoEsgoto(ligacaoEsgoto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}		
	}
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * [SB0004] - Calcular Valor de �gua e/ou Esgoto
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public BigDecimal recuperarPercentualEsgoto(Integer idLigacaoEsgoto) throws ControladorException {
		BigDecimal percentual = null;
		try {
			percentual = repositorioLigacaoEsgoto.recuperarPercentualEsgoto(idLigacaoEsgoto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return percentual;
	}	
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a - Ordem de Fiscaliza��o
	 * 
	 * @author S�vio Luiz
	 * @date 21/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer recuperarConsumoMinimoEsgoto(Integer idImovel)
	throws ControladorException {
		Integer consumoMinimo = null;
		try {
			consumoMinimo = repositorioLigacaoEsgoto.recuperarConsumoMinimoEsgoto(idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return consumoMinimo;
		
	}
	
	private ControladorTransacaoLocal getControladorTransacao() {
		ControladorTransacaoLocalHome localHome = null;
		ControladorTransacaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

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
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Im�vel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoEsgotoSituacao
	 * @param idConsumoTipo
	 * @return LigacaoEsgotoSituacaoConsumoTipo
	 * @throws ControladorException
	 */
	public LigacaoEsgotoSituacaoConsumoTipo pesquisarLigacaoEsgotoSituacaoConsumoTipo(Integer idLigacaoEsgotoSituacao,
			Integer idConsumoTipo) throws ControladorException{
	
		try {
		
			return repositorioLigacaoEsgoto.pesquisarLigacaoEsgotoSituacaoConsumoTipo(
			idLigacaoEsgotoSituacao, idConsumoTipo);
		} 
		catch (ErroRepositorioException ex) {
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
	 *        Pesquisar Situa��es de Liga��o de Esgoto ativas.
	 * 
	 *        Gera��o de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<LigacaoEsgotoSituacao> pesquisarLigacaoEsgotoSituacao()
			throws ControladorException {
		try {
			Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = new ArrayList<LigacaoEsgotoSituacao>();
			Collection colecao = repositorioLigacaoEsgoto.pesquisarLigacaoEsgotoSituacao();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator colecaoIterator = colecao.iterator();

				while (colecaoIterator.hasNext()) {
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) colecaoIterator
							.next();
					colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacao);
				}
			}

			return colecaoLigacaoEsgotoSituacao;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
}
