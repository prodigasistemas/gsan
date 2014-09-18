package gcom.cadastro.localidade;

import gcom.cadastro.dadocensitario.FiltroIbgeSetorCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitario;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.IRepositorioUnidade;
import gcom.cadastro.unidade.RepositorioUnidadeHBM;
import gcom.faturamento.FaturamentoGrupo;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RoteiroEmpresa;
import gcom.micromedicao.bean.NumeroQuadraMinimoMaximoDaRotaHelper;
import gcom.operacional.Bacia;
import gcom.operacional.ControladorOperacionalLocal;
import gcom.operacional.ControladorOperacionalLocalHome;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SetorFonteCaptacao;
import gcom.operacional.SetorFonteCaptacaoPK;
import gcom.seguranca.acesso.Abrangencia;
import gcom.seguranca.acesso.ControladorAcessoLocal;
import gcom.seguranca.acesso.ControladorAcessoLocalHome;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;
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

public class ControladorLocalidadeSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	private IRepositorioSetorComercial repositorioSetorComercial = null;

	private IRepositorioQuadra repositorioQuadra = null;

	private IRepositorioLocalidade repositorioLocalidade = null;

	private IRepositorioGerenciaRegional repositorioGerenciaRegional = null;

	private IRepositorioUnidade repositorioUnidade = null;

	SessionContext sessionContext;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioSetorComercial = RepositorioSetorComercialHBM.getInstancia();
		repositorioQuadra = RepositorioQuadraHBM.getInstancia();
		repositorioLocalidade = RepositorioLocalidadeHBM.getInstancia();
		repositorioGerenciaRegional = RepositorioGerenciaRegionalHBM
				.getInstancia();
		repositorioUnidade = RepositorioUnidadeHBM.getInstancia();
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

	/**
	 * Retorna o valor de controladorOperacional
	 * 
	 * @return O valor de controladorOperacional
	 */
	private ControladorOperacionalLocal getControladorOperacional() {
		
		ControladorOperacionalLocalHome localHome = null;
		ControladorOperacionalLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOperacionalLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_OPERACIONAL_SEJB);
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
	 * Retorna o valor de controladorAcesso
	 * 
	 * @return O valor de controladorAcesso
	 */
	private ControladorAcessoLocal getControladorAcesso() {
		ControladorAcessoLocalHome localHome = null;
		ControladorAcessoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAcessoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ACESSO_SEJB);
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param setorComercial
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void atualizarSetorComercial(SetorComercial setorComercial,Collection colecaoFonteCaptacao)
			throws ControladorException {

		// Valida��o para Setor Comercial
		if (setorComercial != null) {

			// Cria o filtro
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			
			// Pega o nome do pacote do objeto
			String nomePacoteObjeto = SetorComercial.class.getName();

			// Seta os parametros do filtro
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(
					FiltroSetorComercial.ID, 
					setorComercial.getId()));

			// Pesquisa a cole��o de acordo com o filtro passado
			Collection setoresComerciais = 
				getControladorUtil().pesquisar(
					filtroSetorComercial, nomePacoteObjeto);
			
			SetorComercial setorComercialNaBase = 
				(SetorComercial) Util.retonarObjetoDeColecao(setoresComerciais);

			// Verifica se a data de altera��o do objeto gravado na base �
			// maior que a na instancia
			if ((setorComercialNaBase.getUltimaAlteracao().after(setorComercial.getUltimaAlteracao()))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Seta a data/hora
			setorComercial.setUltimaAlteracao(new Date());
		}

		// Atualiza objeto
		getControladorUtil().atualizar(setorComercial);

		Collection colecaoSetor = new ArrayList();
		colecaoSetor.add(setorComercial);
		
		Collection colecaoFonteCaptacaoNaBase = 
			this.getControladorOperacional().pesquisarFonteCaptacao(colecaoSetor);
		
		this.removerOuIncluiFonteCaptacao(colecaoFonteCaptacaoNaBase,colecaoFonteCaptacao,setorComercial);		
	}
	
	/**
	 * Remove ou inclui o relacionamento setor fonte de captacao
	 * 
	 * @param colecaoFonteCaptacaoNaBase
	 * @param colecaoFonteCaptacao
	 * @param setorComercial
	 * 
	 * @throws ControladorException
	 */	
	private void removerOuIncluiFonteCaptacao(Collection colecaoFonteCaptacaoNaBase,
		Collection colecaoFonteCaptacao,SetorComercial setorComercial)
		throws ControladorException {
		 
		if(colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty()){

			Iterator itera = colecaoFonteCaptacao.iterator();

			//Remove as fontes
			while (itera.hasNext()) {

				FonteCaptacao fonteCaptacao = (FonteCaptacao) itera.next();

				if(colecaoFonteCaptacaoNaBase != null && !colecaoFonteCaptacaoNaBase.isEmpty() && 
					!colecaoFonteCaptacaoNaBase.contains(fonteCaptacao)){
					
					SetorFonteCaptacaoPK comp_id = new SetorFonteCaptacaoPK();
					comp_id.setFonteCaptacao(fonteCaptacao);
					comp_id.setSetorComercial(setorComercial);
					
					SetorFonteCaptacao setorFonteCaptacao = new SetorFonteCaptacao();
					setorFonteCaptacao.setComp_id(comp_id);
					setorFonteCaptacao.setUltimaAlteracao(new Date());
					
					this.getControladorUtil().inserir(setorFonteCaptacao);
					
				}else if (colecaoFonteCaptacaoNaBase == null || colecaoFonteCaptacaoNaBase.isEmpty()){
					
					SetorFonteCaptacaoPK comp_id = new SetorFonteCaptacaoPK();
					comp_id.setFonteCaptacao(fonteCaptacao);
					comp_id.setSetorComercial(setorComercial);
					
					SetorFonteCaptacao setorFonteCaptacao = new SetorFonteCaptacao();
					setorFonteCaptacao.setComp_id(comp_id);
					setorFonteCaptacao.setUltimaAlteracao(new Date());
					
					this.getControladorUtil().inserir(setorFonteCaptacao);					
					
				}

			}
		}
		
		if(colecaoFonteCaptacaoNaBase != null && !colecaoFonteCaptacaoNaBase.isEmpty()){
			
			Iterator itera = colecaoFonteCaptacaoNaBase.iterator();

			//Remove as fontes
			while (itera.hasNext()) {

				FonteCaptacao fonteCaptacao = (FonteCaptacao) itera.next();
				
				if(colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty() && 
					!colecaoFonteCaptacao.contains(fonteCaptacao)){
					
					SetorFonteCaptacaoPK comp_id = new SetorFonteCaptacaoPK();
					comp_id.setFonteCaptacao(fonteCaptacao);
					comp_id.setSetorComercial(setorComercial);
					
					SetorFonteCaptacao setorFonteCaptacao = new SetorFonteCaptacao();
					setorFonteCaptacao.setComp_id(comp_id);
					setorFonteCaptacao.setUltimaAlteracao(new Date());
					
					this.getControladorUtil().remover(setorFonteCaptacao);
					
				}else if (colecaoFonteCaptacao == null || colecaoFonteCaptacao.isEmpty()){
					
					SetorFonteCaptacaoPK comp_id = new SetorFonteCaptacaoPK();
					comp_id.setFonteCaptacao(fonteCaptacao);
					comp_id.setSetorComercial(setorComercial);
					
					SetorFonteCaptacao setorFonteCaptacao = new SetorFonteCaptacao();
					setorFonteCaptacao.setComp_id(comp_id);
					setorFonteCaptacao.setUltimaAlteracao(new Date());
					
					this.getControladorUtil().remover(setorFonteCaptacao);					
					
				}
				
			}
		}
	} 

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param localidade
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void atualizarLocalidade(Localidade localidade)
			throws ControladorException {

		// -----VALIDA��O DOS TIMESTAMP PARA ATUALIZA��O DE CADASTRO

		// Valida��o para Setor Comercial
		if (localidade != null) {
			// Cria o filtro
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			// Pega o nome do pacote do objeto
			String nomePacoteObjeto = Localidade.class.getName();

			// Seta os parametros do filtro
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidade.getId()));

			// Pesquisa a cole��o de acordo com o filtro passado
			Collection localidades = getControladorUtil().pesquisar(
					filtroLocalidade, nomePacoteObjeto);
			Localidade localidadeNaBase = (Localidade) Util
					.retonarObjetoDeColecao(localidades);

			// Verifica se a data de altera��o do objeto gravado na base �
			// maior que a na instancia
			if ((localidadeNaBase.getUltimaAlteracao().after(localidade
					.getUltimaAlteracao()))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Seta a data/hora
			localidade.setUltimaAlteracao(new Date());

		}

		// Atualiza objeto
		getControladorUtil().atualizar(localidade);

	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param quadra
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void atualizarQuadra(Quadra quadra, Usuario usuarioLogado, Collection colecaoQuadraFace)
			throws ControladorException {

		// -----VALIDA��O DOS TIMESTAMP PARA ATUALIZA��O DE CADASTRO

		// Valida��o para quadra
		if (quadra != null) {
			// Cria o filtro
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			// Pega o nome do pacote do objeto
			String nomePacoteObjeto = Quadra.class.getName();

			// Seta os parametros do filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID, quadra.getId()));

			// Pesquisa a cole��o de acordo com o filtro passado
			Collection quadras = getControladorUtil().pesquisar(filtroQuadra,
					nomePacoteObjeto);
			Quadra quadraNaBase = (Quadra) Util.retonarObjetoDeColecao(quadras);

			// Verifica se a data de altera��o do objeto gravado na base �
			// maior que a na instancia
			if ((quadraNaBase.getUltimaAlteracao().after(quadra
					.getUltimaAlteracao()))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Seta a data/hora
			quadra.setUltimaAlteracao(new Date());

		}
		
		//------------ REGISTRAR TRANSA��O ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_QUADRA_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_QUADRA_ATUALIZAR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------

		// ------------ CONTROLE DE ABRANGENCIA ----------------
		Abrangencia abrangencia = new Abrangencia(usuarioLogado, quadra);
		// ------------ CONTROLE DE ABRANGENCIA ----------------

		if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.acesso.negado.abrangencia");
		} 
		else {
			
			//------------ REGISTRAR TRANSA��O ----------------
			quadra.setOperacaoEfetuada(operacaoEfetuada);
			quadra.adicionarUsuario(usuarioLogado, 
	        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
	        registradorOperacao.registrarOperacao(quadra);
	        //------------ REGISTRAR TRANSA��O ----------------
			
	        getControladorUtil().atualizar(quadra);
	        
	        //ATUALIZANDO AS FACES DA QUADRA
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
				
				if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()){
					
					Iterator itQuadraFace = colecaoQuadraFace.iterator();
					
					//PESQUISANDO AS QUADRAS QUE EST�O CADASTRADAS NA BASE
		            FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
		             
		            filtroQuadraFace.adicionarParametro(new ParametroSimples(
		            FiltroQuadraFace.ID_QUADRA, quadra.getId()));

		            Collection colecaoQuadraFaceNaBase = this.getControladorUtil().pesquisar(filtroQuadraFace,
		            QuadraFace.class.getName());
		            
		            if (colecaoQuadraFaceNaBase != null && !colecaoQuadraFaceNaBase.isEmpty()){
		            	
		            	//REMOVENDO A(S) FACE(S) DA QUADRA QUE FORAM EXLU�DAS PELO USU�RIO
		            	//==========================================================================
		            	Iterator itQuadraFaceNaBase = colecaoQuadraFaceNaBase.iterator();
		            	QuadraFace quadraFaceNaBase = null;
		            	
		            	while (itQuadraFaceNaBase.hasNext()){
		            		
		            		quadraFaceNaBase = (QuadraFace) itQuadraFaceNaBase.next();
		            		
		            		if (!colecaoQuadraFace.contains(quadraFaceNaBase)){
		            			
		            			//REMOVENDO...
		            			this.getControladorUtil().remover(quadraFaceNaBase);
		            		}
		            	}
		            	//FIM - REMOVENDO A(S) FACE(S) DA QUADRA QUE FORAM EXLU�DAS PELO USU�RIO
		            	//==========================================================================
		            	
		            	
		            	//ATUALIZANDO A(S) FACE(S) DA QUADRA QUE FORAM ALTERADAS PELO USU�RIO
		            	//==========================================================================
		            	boolean inserir;
		            	QuadraFace quadraFaceAtualizar = null;
		            	
		            	while (itQuadraFace.hasNext()){
							
		            		inserir = true;
		            		quadraFaceAtualizar = (QuadraFace) itQuadraFace.next(); 
							
							itQuadraFaceNaBase = colecaoQuadraFaceNaBase.iterator();
							
							while (itQuadraFaceNaBase.hasNext()){
								
								quadraFaceNaBase = (QuadraFace) itQuadraFaceNaBase.next();
								
								if (quadraFaceNaBase.getNumeroQuadraFace().equals(
									quadraFaceAtualizar.getNumeroQuadraFace())){
									
									quadraFaceAtualizar.setId(quadraFaceNaBase.getId());
									quadraFaceAtualizar.setQuadra(quadra);
									quadraFaceAtualizar.setUltimaAlteracao(new Date());
									
									inserir = false;
									
									//ATUALIZANDO A FACE DA QUADRA
									getControladorUtil().atualizar(quadraFaceAtualizar);
									
									break;
								}
							}
							
							if (inserir){
								
								quadraFaceAtualizar.setQuadra(quadra);
								quadraFaceAtualizar.setUltimaAlteracao(new Date());
								
								this.getControladorUtil().inserir(quadraFaceAtualizar);
							}
						}
		            	
		            	//FIM - ATUALIZANDO A(S) FACE(S) DA QUADRA QUE FORAM ALTERADAS PELO USU�RIO
		            	//==========================================================================
		            }
		            else{
		            	
		            	//INSERINDO A(S) FACE(S) DA QUADRA QUE FORAM EXLU�DAS PELO USU�RIO
		            	while (itQuadraFace.hasNext()){
							
							QuadraFace quadraFaceInserir = (QuadraFace) itQuadraFace.next();
							
							quadraFaceInserir.setQuadra(quadra);
							quadraFaceInserir.setUltimaAlteracao(new Date());
							
							this.getControladorUtil().inserir(quadraFaceInserir);
						}
		            }
				}
			}
		}
	}

	/**
	 * Pesquisa uma cole��o de setor comercial com uma query especifica
	 * 
	 * @param idLocalidade
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarSetorComercial(int idLocalidade)
			throws ControladorException {
		try {
			return repositorioSetorComercial
					.pesquisarSetorComercial(idLocalidade);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa uma cole��o de quadra com uma query especifica
	 * 
	 * @param idSetorComercial
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarQuadra(int idSetorComercial)
			throws ControladorException {

		Collection colecaoQuadraArray = null;
		Collection<Quadra> colecaoRetorno = new ArrayList();

		try {
			colecaoQuadraArray = repositorioQuadra
					.pesquisarQuadra(idSetorComercial);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		Iterator colecaoQuadraArrayIt = colecaoQuadraArray.iterator();
		Object[] quadraArray;
		Quadra quadra;
		Rota rota = new Rota();
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();

		while (colecaoQuadraArrayIt.hasNext()) {

			quadraArray = (Object[]) colecaoQuadraArrayIt.next();

			quadra = new Quadra();

			quadra.setId((Integer) quadraArray[0]);

			quadra.setNumeroQuadra(new Integer(String.valueOf(quadraArray[1]))
					.intValue());

			faturamentoGrupo.setId((Integer) quadraArray[3]);

			rota.setId((Integer) quadraArray[2]);
			rota.setFaturamentoGrupo(faturamentoGrupo);

			quadra.setRota(rota);

			colecaoRetorno.add(quadra);

		}

		return colecaoRetorno;
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
	 * Pesquisa uma cole��o de localidades por ger�ncia regional
	 * 
	 * @param idGerenciaRegional
	 *            C�digo da ger�ncia regional solicitada
	 * @return Cole��o de Localidades da Ger�ncia Regional solicitada
	 * @exception ControladorException
	 *                Erro no hibernate
	 */
	public Collection<Localidade> pesquisarLocalidadePorGerenciaRegional(
			int idGerenciaRegional) throws ControladorException {
		try {
			// pesquisa a cole��o de localidades para a ger�ncia regional
			// informada
			return repositorioLocalidade
					.pesquisarLocalidadePorGerenciaRegional(idGerenciaRegional);
			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa uma cole��o de ger�ncias regionais
	 * 
	 * @return Cole��o de Ger�ncias Regionais
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<GerenciaRegional> pesquisarGerenciaRegional()
			throws ControladorException {
		try {
			// pesquisa as gerencias regionais existentes no sisitema
			return repositorioGerenciaRegional.pesquisarGerenciaRegional();

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa uma ger�ncia regional pelo id
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Ger�ncia Regional
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public GerenciaRegional pesquisarObjetoGerenciaRegionalRelatorio(
			Integer idGerenciaRegional) throws ControladorException {
		try {
			// pesquisa as gerencias regionais existentes no sisitema
			Object[] objetoGerenciaRegional = repositorioGerenciaRegional
					.pesquisarObjetoGerenciaRegionalRelatorio(idGerenciaRegional);

			GerenciaRegional gerenciaRegional = new GerenciaRegional();

			gerenciaRegional.setId((Integer) objetoGerenciaRegional[0]);
			gerenciaRegional
					.setNomeAbreviado((String) objetoGerenciaRegional[1]);

			return gerenciaRegional;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Inseri um objeto do tipo setor comercial no BD
	 * 
	 * @param setorComercial
	 * @return ID gerado
	 * @throws ControladorException
	 */
	public Integer inserirSetorComercial(SetorComercial setorComercial,
		Collection colecaoFonteCaptacao)
		throws ControladorException {

		Integer retorno = null;

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");

		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, 
				setorComercial.getLocalidade().getId()));

		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
				setorComercial.getCodigo()));

		// Retorna caso j� exista um setor comercial com o c�digo
		// informado
		Collection colecaoPesquisa = 
			this.getControladorUtil().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			// Setor comercial j� cadastrado
			throw new ControladorException(
					"atencao.pesquisa.setor_comercial_ja_cadastrado", null, ""
							+ setorComercial.getCodigo(), setorComercial
							.getLocalidade().getDescricao());
		}

		retorno = (Integer) this.getControladorUtil().inserir(setorComercial);
		
		
		if(colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty()){
			
			SetorComercial setorComer = new SetorComercial();
			setorComer.setId(retorno);
			
			Iterator itera = colecaoFonteCaptacao.iterator();
			while (itera.hasNext()) {
				FonteCaptacao fonteCaptacao = (FonteCaptacao) itera.next();
				
				
				SetorFonteCaptacaoPK comp_id = new SetorFonteCaptacaoPK();
				comp_id.setFonteCaptacao(fonteCaptacao);
				comp_id.setSetorComercial(setorComer);
				
				SetorFonteCaptacao setorFonteCaptacao = new SetorFonteCaptacao();
				setorFonteCaptacao.setComp_id(comp_id);
				setorFonteCaptacao.setUltimaAlteracao(new Date());
				
				this.getControladorUtil().inserir(setorFonteCaptacao);
				
			}
		}

		return retorno;
	}

	/**
	 * /** Inseri uma cole��o de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Este fluxo secund�rio tem como objetivo pesquisar a localidade digitada
	 * pelo usu�rio
	 * 
	 * [FS0007] - Verificar exist�ncia da localidade
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idLocalidadeDigitada
	 * @return
	 * @throws ControladorException
	 */
	public Localidade pesquisarLocalidadeDigitada(Integer idLocalidadeDigitada)
			throws ControladorException {

		// Varai�vel que vai armazenar a localidade digitada pelo usu�rio
		Localidade localidadeDigitada = null;

		// Pesquisa a localidade informada pelo usu�rio no sistema
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidadeDigitada));

		Collection<Localidade> colecaoLocalidade = getControladorUtil()
				.pesquisar(filtroLocalidade, Localidade.class.getName());

		// Caso exista a localidade no sistema
		// Retorna para o usu�rio a localidade retornada pela pesquisa
		// Caso contr�rio retorna um objeto nulo
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
			localidadeDigitada = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidade);
		}

		// Retorna a localdiade encontrada ou nulo se n�o existir
		return localidadeDigitada;
	}

	/**
	 * M�todo que retorna o maior n�mero da quadra de um setor comercial
	 * 
	 * @author Rafael Corr�a
	 * @date 11/07/2006
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ControladorException
	 */

	public int pesquisarMaximoCodigoQuadra(Integer idSetorComercial)
			throws ControladorException {
		try {
			return repositorioQuadra
					.pesquisarMaximoCodigoQuadra(idSetorComercial);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * M�todo que retorna o maior c�digo de setor comercial de uma localidade
	 * 
	 * @author Rafael Corr�a
	 * @date 11/07/2006
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ControladorException
	 */

	public int pesquisarMaximoCodigoSetorComercial(Integer idLocalidade)
			throws ControladorException {
		try {
			return repositorioSetorComercial
					.pesquisarMaximoCodigoSetorComercial(idLocalidade);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * M�todo que retorna o maior id de Localidade
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */

	public int pesquisarMaximoIdLocalidade() throws ControladorException {
		try {
			return repositorioLocalidade.pesquisarMaximoIdLocalidade();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa uma localidade pelo id
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Localidade
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Localidade pesquisarObjetoLocalidadeRelatorio(Integer idLocalidade)
			throws ControladorException {
		try {
			// pesquisa as gerencias regionais existentes no sisitema
			Object[] objetoLocalidade = repositorioLocalidade
					.pesquisarObjetoLocalidadeRelatorio(idLocalidade);

			Localidade localidade = new Localidade();
			
			if ( objetoLocalidade != null) {
				localidade.setId((Integer) objetoLocalidade[0]);
				localidade.setDescricao((String) objetoLocalidade[1]);
			}
			
			return localidade;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa um setor comercial pelo c�digo e pelo id da localidade
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return SetorComercial
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public SetorComercial pesquisarObjetoSetorComercialRelatorio(
			Integer codigoSetorComercial, Integer idLocalidade)
			throws ControladorException {

		try {
			// pesquisa as gerencias regionais existentes no sisitema
			Object[] objetoSetorComercial = repositorioSetorComercial
					.pesquisarObjetoSetorComercialRelatorio(
							codigoSetorComercial, idLocalidade);

			SetorComercial setorComercial = new SetorComercial();
			
			if(objetoSetorComercial != null){
				setorComercial.setCodigo((Integer) objetoSetorComercial[0]);
				setorComercial.setDescricao((String) objetoSetorComercial[1]);
			}
			return setorComercial;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Integer verificarExistenciaLocalidade(Integer idLocalidade)
			throws ControladorException {

		// Retorna o cliente encontrado ou vazio se n�o existir
		try {
			return repositorioLocalidade
					.verificarExistenciaLocalidade(idLocalidade);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Met�do respons�vel por inserir uma quadra no sitema
	 * 
	 * [UC0000 - Inserir Quadra]
	 * 
	 * @author Vivianne Sousa, Pedro Alexandre
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param quadra
	 * @param usuarioLogado
	 * @param usuarioLogado
	 * @return colecaoQuadraFace
	 * @throws ControladorException
	 */
	public Integer inserirQuadra(Quadra quadra, Collection colecaoQuadraFace, Usuario usuarioLogado)
			throws ControladorException {

		Integer retorno = null;

		// ------------ CONTROLE DE ABRANGENCIA ----------------
		Abrangencia abrangencia = new Abrangencia(usuarioLogado, quadra
				.getSetorComercial());

		if (!getControladorAcesso().verificarAcessoAbrangencia(abrangencia)) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.acesso.negado.abrangencia");
		} else {
			// ------------ REGISTRAR TRANSA��O----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_QUADRA_INSERIR,
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_QUADRA_INSERIR);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			quadra.setOperacaoEfetuada(operacaoEfetuada);
			quadra.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(quadra);
			// ------------ REGISTRAR TRANSA��O----------------------------

			retorno = (Integer) this.getControladorUtil().inserir(quadra);
		}
		// ------------ CONTROLE DE ABRANGENCIA ----------------
		
		//INSERINDO AS FACES DA QUADRA
		quadra.setId(retorno);
		
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
			
			if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()){
				
				Iterator it = colecaoQuadraFace.iterator();
				
				while (it.hasNext()){
					
					QuadraFace quadraFaceInserir = (QuadraFace) it.next();
					
					quadraFaceInserir.setQuadra(quadra);
					quadraFaceInserir.setUltimaAlteracao(new Date());
					
					this.getControladorUtil().inserir(quadraFaceInserir);
				}
			}
		}

		return retorno;
	}

	/**
	 * met�do respons�vel por verificar se o usu�rio que est� tentando remover
	 * as quadras tem abrang�ncia
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 18/11/2006
	 * 
	 * @param ids
	 * @param pacoteNomeObjeto
	 * @param operacaoEfetuada
	 * @param acaoUsuarioHelper
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void removerQuadra(String[] ids, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper,
			Usuario usuarioLogado) throws ControladorException {

		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				int id = Integer.parseInt(ids[i]);
				Quadra quadra = new Quadra();
				quadra.setId(id);

				// ------------ CONTROLE DE ABRANGENCIA ----------------
				Abrangencia abrangencia = new Abrangencia(usuarioLogado, quadra);
				if (!getControladorAcesso().verificarAcessoAbrangencia(
						abrangencia)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.acesso.negado.abrangencia");
				}
				// ------------ CONTROLE DE ABRANGENCIA ----------------

				// Alteracao CRC 3695 
				FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
				filtroQuadraFace.adicionarParametro(new ParametroSimples(FiltroQuadraFace.ID_QUADRA, quadra.getId()));
				Collection colecaoQuadraFaces = getControladorUtil().pesquisar(filtroQuadraFace,QuadraFace.class.getName());
				if(colecaoQuadraFaces != null && !colecaoQuadraFaces.isEmpty()){
					Iterator iteratorQuadraFace = colecaoQuadraFaces.iterator();
					while (iteratorQuadraFace.hasNext()){
						QuadraFace quadraFace = (QuadraFace) iteratorQuadraFace.next();
						getControladorUtil().remover(quadraFace);
					}
				}
				// Fim - Alteracao CRC 3695
			}

			getControladorUtil().remover(ids, pacoteNomeObjeto,
					operacaoEfetuada, acaoUsuarioHelper);
		}
	}

	// /**
	// * < <Descri��o do m�todo>>
	// *
	// * @param ger�ncia Regional
	// * Descri��o do par�metro
	// * @throws ControladorException
	// */
	// public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional)
	// throws ControladorException {
	//
	// // -----VALIDA��O DOS TIMESTAMP PARA ATUALIZA��O DE CADASTRO
	//
	// // Valida��o para Setor Comercial
	// if (gerenciaRegional != null) {
	// // Cria o filtro
	// FiltroGerenciaRegional filtroGerenciaRegional = new
	// FiltroGerenciaRegional();
	// // Pega o nome do pacote do objeto
	// String nomePacoteObjeto = GerenciaRegional.class.getName();
	//
	// // Seta os parametros do filtro
	// filtroGerenciaRegional.adicionarParametro(new
	// ParametroSimples(FiltroGerenciaRegional.ID, gerenciaRegional.getId()));
	//
	// // Pesquisa a cole��o de acordo com o filtro passado
	// Collection gerenciaRegionais =
	// getControladorUtil().pesquisar(filtroGerenciaRegional, nomePacoteObjeto);
	// GerenciaRegional gerenciaRegionalNaBase = (GerenciaRegional)
	// Util.retonarObjetoDeColecao(gerenciaRegionais);
	//
	// // Verifica se a data de altera��o do objeto gravado na base �
	// // maior que a na instancia
	// if
	// ((gerenciaRegionalNaBase.getUltimaAlteracao().after(gerenciaRegional.getUltimaAlteracao())))
	// {
	// sessionContext.setRollbackOnly();
	// throw new ControladorException("atencao.atualizacao.timestamp");
	// }
	//
	// // Seta a data/hora
	// gerenciaRegional.setUltimaAlteracao(new Date());
	//
	// }
	//
	// // Atualiza objeto
	// getControladorUtil().atualizar(gerenciaRegional);
	//
	// }

	// /**
	// * Permite inserir uma Ger�ncia Regional
	// *
	// * [UC0217] Inserir Gerencia Regional
	// *
	// * @author Thiago Ten�rio
	// * @date 30/03/2006
	// *
	// */
	// public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional)
	// throws ControladorException {
	//
	// FiltroGerenciaRegional filtroGerenciaRegional = new
	// FiltroGerenciaRegional();
	// filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
	// FiltroGerenciaRegional.ID, gerenciaRegional.getId()));
	//
	// filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
	// FiltroGerenciaRegional.NOME, gerenciaRegional.getNome()));
	//
	// filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
	// FiltroGerenciaRegional.NOME_ABREVIADO, gerenciaRegional
	// .getNomeAbreviado()));
	//
	// Collection colecaoEnderecos = getControladorUtil().pesquisar(
	// filtroGerenciaRegional, GerenciaRegional.class.getName());
	//
	// if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
	// throw new ControladorException(
	// "atencao.endereco_localidade_nao_informado");
	// }
	//
	// Integer id = (Integer) getControladorUtil().inserir(gerenciaRegional);
	//
	// return id;
	//
	// }

	/**
	 * Pesquisa o id da ger�ncia regional para a qual a localidade pertence.
	 * 
	 * [UC0267] Encerrar Arrecada��o do M�s, [UC0155] Encerrar Faturamento do
	 * M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 05/01/2007
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisarIdGerenciaParaLocalidade(Integer idLocalidade)
			throws ControladorException {
		try {
			return repositorioGerenciaRegional
					.pesquisarIdGerenciaParaLocalidade(idLocalidade);
		} catch (ErroRepositorioException ex) {
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
	public void atualizarLogradouroCepGerenciaRegional(
			LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo)
			throws ControladorException {

		try {

			this.repositorioLocalidade.atualizarLogradouroCepGerenciaRegional(
					logradouroCepAntigo, logradouroCepNovo);

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
	public void atualizarLogradouroBairroGerenciaRegional(
			LogradouroBairro logradouroBairroAntigo,
			LogradouroBairro logradouroBairroNovo) throws ControladorException {

		try {

			this.repositorioLocalidade
					.atualizarLogradouroBairroGerenciaRegional(
							logradouroBairroAntigo, logradouroBairroNovo);

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

			this.repositorioLocalidade.atualizarLogradouroCep(
					logradouroCepAntigo, logradouroCepNovo);

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

			this.repositorioLocalidade.atualizarLogradouroBairro(
					logradouroBairroAntigo, logradouroBairroNovo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa o id da unidade negocio para a qual a localidade pertence.
	 * 
	 * [UC0267] Encerrar Arrecada��o do M�s, [UC0155] Encerrar Faturamento do
	 * M�s
	 * 
	 * @author Raphael Rossiter
	 * @date 30/05/2007
	 * 
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public Integer pesquisarIdUnidadeNegocioParaLocalidade(Integer idLocalidade)
			throws ControladorException {

		try {

			return repositorioUnidade
					.pesquisarIdUnidadeNegocioParaLocalidade(idLocalidade);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisar os ids da Localidade
	 * 
	 * @author Thiago ten�rio
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTodosIdsLocalidade() throws ControladorException {
		try {
			return repositorioLocalidade.pesquisarTodosIdLocalidade();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa uma cole��o de quadra com uma query especifica
	 * 
	 * @param idsSetorComercial
	 * @param idFaturamentoGrupo
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarQuadrasPorSetorComercialFaturamentoGrupo(
			int localidade, int[] idsSetorComercial, Integer idFaturamentoGrupo)
			throws ControladorException {

		Collection colecaoQuadraArray = null;
		Collection<Quadra> colecaoRetorno = new ArrayList();

		try {
			colecaoQuadraArray = repositorioQuadra
					.pesquisarQuadrasPorSetorComercialFaturamentoGrupo(
							localidade, idsSetorComercial, idFaturamentoGrupo);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		Iterator colecaoQuadraArrayIt = colecaoQuadraArray.iterator();
		Object[] quadraArray;
		Quadra quadra;
		// Rota rota = new Rota();
		// FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();

		while (colecaoQuadraArrayIt.hasNext()) {

			quadraArray = (Object[]) colecaoQuadraArrayIt.next();

			quadra = new Quadra();

			quadra.setId((Integer) quadraArray[0]);

			quadra.setNumeroQuadra(new Integer(String.valueOf(quadraArray[1]))
					.intValue());

			// faturamentoGrupo.setId((Integer) quadraArray[3]);

			// rota.setId((Integer) quadraArray[2]);
			// rota.setFaturamentoGrupo(faturamentoGrupo);
			SetorComercial setor = new SetorComercial();
			setor.setId((Integer) quadraArray[2]);
			setor.setCodigo(((Integer) quadraArray[3]).intValue());
			quadra.setSetorComercial(setor);

			// Id roteiro empresa
			if (quadraArray[4] != null) {
				RoteiroEmpresa roEm = new RoteiroEmpresa();
				roEm.setId(((Integer) quadraArray[4]).intValue());
				quadra.setRoteiroEmpresa(roEm);
			}

			quadra.setUltimaAlteracao((Date) quadraArray[5]);

			colecaoRetorno.add(quadra);

		}

		return colecaoRetorno;
	}

	/**
	 * 
	 * [UC608] Efetuar Liga��o de Esgoto sem RA
	 * 
	 * @author S�vio Luiz
	 * @date 10/09/2007
	 * 
	 * @return
	 */

	public Short pesquisarIndicadorRedeEsgotoQuadra(int idImovel)
			throws ControladorException {
		try {
			return repositorioQuadra
					.pesquisarIndicadorRedeEsgotoQuadra(idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	public Collection pesquisarQuadrasPorRoteiroEmpresa(
			int idRoteiroEmpresa)
			throws ControladorException {

		Collection colecaoQuadraArray = null;
		Collection<Quadra> colecaoRetorno = new ArrayList();

		try {
			colecaoQuadraArray = repositorioQuadra
					.pesquisarQuadrasPorRoteiroEmpresa(idRoteiroEmpresa);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		Iterator colecaoQuadraArrayIt = colecaoQuadraArray.iterator();
		Object[] quadraArray;
		Quadra quadra;
		Rota rota = new Rota();
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		SetorComercial setor = new SetorComercial();

		while (colecaoQuadraArrayIt.hasNext()) {

			quadraArray = (Object[]) colecaoQuadraArrayIt.next();

			quadra = new Quadra();

			quadra.setId((Integer) quadraArray[0]);

			quadra.setNumeroQuadra(new Integer(String.valueOf(quadraArray[1]))
					.intValue());
			setor.setId((Integer) quadraArray[2]);
			setor.setCodigo(((Integer) quadraArray[3]).intValue());
			quadra.setSetorComercial(setor);

			// Id roteiro empresa
			if (quadraArray[4] != null) {
				RoteiroEmpresa roEm = new RoteiroEmpresa();
				roEm.setId(((Integer) quadraArray[4]).intValue());
				quadra.setRoteiroEmpresa(roEm);
			}
			rota.setId((Integer) quadraArray[5]);		
			faturamentoGrupo.setId((Integer) quadraArray[6]);
			rota.setFaturamentoGrupo(faturamentoGrupo);
			quadra.setRota(rota);
			Localidade localidade = new Localidade();
			localidade.setId((Integer) quadraArray[7]);
			localidade.setDescricao((String) quadraArray[8]);
			setor.setLocalidade(localidade);

			quadra.setUltimaAlteracao((Date) quadraArray[9]);
			colecaoRetorno.add(quadra);

		}

		return colecaoRetorno;
	}
	
	/**
	 * Obt�m Elo P�lo
	 * @author Ana Maria
	 * @date 10/12/2007
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarEloPolo()throws ControladorException {
		try {

			return repositorioLocalidade.pesquisarEloPolo();
			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * pesquisar localidades do munic�pio
	 * @author S�vio Luiz
	 * @date 25/02/2008
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesMunicipio(Integer idMunicipio)throws ControladorException {
		try {

			return repositorioLocalidade.pesquisarLocalidadesMunicipio(idMunicipio);
			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}	
	
    
    /**
     * @author: Vivianne Sousa
     * @date: 16/05/2008 
     */
    public Collection pesquisarIdQuadraPorSetorComercial(Integer idSetorComercial)
        throws ControladorException {
        try {

            return repositorioQuadra.pesquisarIdQuadraPorSetorComercial(idSetorComercial);
            // erro no hibernate
        } catch (ErroRepositorioException ex) {

            // levanta a exce��o para a pr�xima camada
            throw new ControladorException("erro.sistema", ex);
        }
    }
    
    
	
	/**
	 * Pesquisa os ids das localidaes que possuem im�veis
	 *
	 * @author Pedro Alexandre
	 * @date 07/07/2008
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesImoveis() throws ControladorException {
		try {
			return repositorioLocalidade.pesquisarIdsLocalidadesImoveis();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
    /**
     * Atualiza o campo rota das quadras que pertencem ao intervalo de numero informado e ao setor comercial
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public void atualizarRotaDasQuadrasNoIntervaloNumero(
    		int idNovaRota, int idSetorComercial, int numeroInicial, int numeroFinal) throws ControladorException {
    	try {
    		repositorioQuadra.atualizarRotaDasQuadrasNoIntervaloNumero(idNovaRota, idSetorComercial, numeroInicial, numeroFinal);
    	} catch (ErroRepositorioException ex) {
    		sessionContext.setRollbackOnly();
    		throw new ControladorException("erro.sistema", ex);
    	}
    }
    
    /**
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public Collection<NumeroQuadraMinimoMaximoDaRotaHelper> pesquisarNumeroQuadraMininoMaximoDaRota(
    		Collection<Integer> idsRotas) throws ControladorException {
    	try {
    		return repositorioQuadra.pesquisarNumeroQuadraMininoMaximoDaRota(idsRotas);
    	} catch (ErroRepositorioException ex) {
    		sessionContext.setRollbackOnly();
    		throw new ControladorException("erro.sistema", ex);
    	}
    }
    
    /**
     * [UC0???] Informar Subdivis�es de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 27/10/2008
     */
    public Integer pesqisarQuantidadeQuadrasDaRota(
    		Integer idRota, Integer quadraInicial, Integer quadraFinal) throws ControladorException {
    	try {
    		return this.repositorioQuadra.pesqisarQuantidadeQuadrasDaRota(idRota, quadraInicial, quadraFinal);
    	} catch (ErroRepositorioException ex) {
    		throw new FachadaException(ex.getMessage(), ex);
    	}
    }
    
    /**
     * [UC0024] Inserir Quadra
     *
     * @author Raphael Rossiter
     * @date 06/04/2009
     *
     * @param helper
     * @return Quadra
     * @throws ControladorException
     */
    public Quadra validarQuadra(InserirQuadraHelper helper) throws ControladorException{
    	
    	Quadra quadraInserir = new Quadra();
		Collection colecaoPesquisa = null;
		Localidade objetoLocalidade = null;
		
		// LOCALIDADE � OBRIGAT�RIO.
		if (helper.getLocalidadeID() == null || helper.getLocalidadeID().equalsIgnoreCase("")) {
			
			throw new ControladorException("atencao.required", null, "Localidade");
		} 
		else {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.ID, helper.getLocalidadeID()));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroLocalidade,
			Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa.localidade_inexistente");
			} 
			else {
				
				objetoLocalidade = (Localidade) Util
				.retonarObjetoDeColecao(colecaoPesquisa);
				
				if (objetoLocalidade.getLocalidade().getId().intValue() == 0) {
					throw new ControladorException("atencao.localidade_sem_elo");
				}
			}
		}

		// ========================================================================

		// SETOR COMERCIAL � OBRIGAT�RIO
		SetorComercial objetoSetorComercial = null;
		if (helper.getSetorComercialCD() == null || helper.getSetorComercialCD().equalsIgnoreCase("")) {
			
			throw new ControladorException("atencao.required", null,"Setor Comercial");
		} 
		else {
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			
			filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.ID_LOCALIDADE, helper.getLocalidadeID()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getSetorComercialCD()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroSetorComercial,
			SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.processo.setorComercialNaoCadastrada");
			} 
			else {
				
				objetoSetorComercial = (SetorComercial) Util
				.retonarObjetoDeColecao(colecaoPesquisa);
				
				quadraInserir.setSetorComercial(objetoSetorComercial);
			}
		}

		// ========================================================================

		// N�MERO DA QUADRA � OBRIGAT�RIO.
		if (helper.getQuadraNM() == null || helper.getQuadraNM().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null, "Quadra");
		} 
		else {
			quadraInserir.setNumeroQuadra(Integer.parseInt(helper.getQuadraNM()));
		}

		// ========================================================================

		// PERFIL DA QUADRA � OBRIGAT�RIO
		if (helper.getPerfilQuadraID() == null || 
			helper.getPerfilQuadraID().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			
			throw new ControladorException("atencao.required", null, "Perfil da Quadra");
		} 
		else {
			
			QuadraPerfil objetoQuadraPerfil = new QuadraPerfil();
			objetoQuadraPerfil.setId(new Integer(helper.getPerfilQuadraID()));
			quadraInserir.setQuadraPerfil(objetoQuadraPerfil);
		}
		// ========================================================================

		//Area Tipo /Localiza��o
		if (helper.getAreaTipoID() != null && 
			!helper.getAreaTipoID().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			
			AreaTipo objetoAreaTipo = new AreaTipo();
			objetoAreaTipo.setId(new Integer(helper.getAreaTipoID()));
			quadraInserir.setAreaTipo(objetoAreaTipo);
		}
		//=========================================================================
		
		//IndicadorIncrementoLote
		quadraInserir.setIndicadorIncrementoLote(new Short(helper.getIndicadorIncrementoLote()));
		// ========================================================================
		
		/*
		 * Para as empresas que N�O utilizam o conceito de face da quadra,  as caracter�sticas da quadra ser�o
		 * gravadas na pr�pria quadra, caso contr�rio; ser�o geradas as faces da quadra com suas caracter�sticas
		 * diferentes.
		 */
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.NAO)){
			
			//IndicadorRedeAgua
			if (helper.getIndicadorRedeAgua() == null || helper.getIndicadorRedeAgua().equals("")) {
				
				throw new ControladorException("atencao.required", null, "Rede de �gua");
			} 
			else {
				quadraInserir.setIndicadorRedeAgua(new Short(helper.getIndicadorRedeAgua()));
			}

			// ========================================================================

			// IndicadorRedeEsgoto
			if (helper.getIndicadorRedeEsgoto() == null || helper.getIndicadorRedeEsgoto().equals("")) {
				
				throw new ControladorException("atencao.required", null, "Rede de Esgoto");
			} 
			else {
				quadraInserir.setIndicadorRedeEsgoto(new Short(helper.getIndicadorRedeEsgoto()));
			}
			// ========================================================================
			
			/*
			 * Caso o indicador da rede de �gua seja "COM_REDE = 2" ou "REDE_PARCIAL =
			 * 3" o campo distrito operacional passa a ser obrigat�rio
			 */
			if (quadraInserir.getIndicadorRedeAgua().equals(Quadra.COM_REDE) || 
				quadraInserir.getIndicadorRedeAgua().equals(Quadra.REDE_PARCIAL)) {

				if (helper.getDistritoOperacionalID() == null || 
					helper.getDistritoOperacionalID().equalsIgnoreCase("")) {
					
					throw new ControladorException("atencao.required", null, "Distrito Operacional");
				}
			}

			if (helper.getDistritoOperacionalID() != null && 
				!helper.getDistritoOperacionalID().equalsIgnoreCase("")) {

				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

				filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.ID, helper.getDistritoOperacionalID()));

				filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = this.getControladorUtil().pesquisar(filtroDistritoOperacional,
				DistritoOperacional.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ControladorException("atencao.pesquisa.distrito_operacional_inexistente");
				} 
				else {
					
					DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
					
					quadraInserir.setDistritoOperacional(objetoDistritoOperacional);
				}
			}

			// ======================================================================

			/*
			 * Caso o indicador da rede de esgoto seja "COM_REDE = 2" ou
			 * "REDE_PARCIAL = 3" o campo bacia passa a ser obrigat�rio
			 */
			Bacia objetoBacia = new Bacia();
			
			if (quadraInserir.getIndicadorRedeEsgoto().equals(Quadra.COM_REDE) || 
				quadraInserir.getIndicadorRedeEsgoto().equals(Quadra.REDE_PARCIAL)) {

				if (Integer.parseInt(helper.getSistemaEsgotoID()) == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					
					throw new ControladorException("atencao.required", null, "Sistema Esgoto");
				} 
				else if (helper.getBaciaID() == null || helper.getBaciaID().equalsIgnoreCase("")) {
					
					throw new ControladorException("atencao.sistema_esgoto_nao_contem_bacia");
				} 
				else {
					
					objetoBacia.setId(new Integer(helper.getBaciaID()));
					quadraInserir.setBacia(objetoBacia);
				}
			} 
			else if (helper.getBaciaID() != null && !helper.getBaciaID().equalsIgnoreCase("") && 
					!helper.getBaciaID().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				
				objetoBacia.setId(new Integer(helper.getBaciaID()));
				quadraInserir.setBacia(objetoBacia);
			}
			
			// ========================================================================
		}
		
		
		
		// Setor Censit�rio (Opcional)
		if (helper.getSetorCensitarioID() != null && 
			!helper.getSetorCensitarioID().equalsIgnoreCase("")) {
			
			FiltroIbgeSetorCensitario filtroIbgeSetorCensitario = new FiltroIbgeSetorCensitario();

			filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
			FiltroIbgeSetorCensitario.ID, helper.getSetorCensitarioID()));

			filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
			FiltroIbgeSetorCensitario.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroIbgeSetorCensitario,
			IbgeSetorCensitario.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				throw new ControladorException("atencao.pesquisa_inexistente", null,
				"Setor Censit�rio");
			} 
			else {
				IbgeSetorCensitario objetoIbgeSetorCensitario = new IbgeSetorCensitario();
				objetoIbgeSetorCensitario.setId(new Integer(helper.getSetorCensitarioID()));
				quadraInserir.setIbgeSetorCensitario(objetoIbgeSetorCensitario);
			}
		}

		// ========================================================================

		// Zeis (Opcional)
		if (Integer.parseInt(helper.getZeisID()) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			Zeis objetoZeis = new Zeis();
			objetoZeis.setId(new Integer(helper.getZeisID()));
			quadraInserir.setZeis(objetoZeis);
		}

		// ========================================================================

		/*
		 * Rota � obrigat�rio (o setor comercial da quadra tem que ser o mesmo
		 * setor) comercial da rota
		 */
		if ((helper.getRotaCD() == null || helper.getRotaCD().equalsIgnoreCase(""))
				&& (helper.getRotaID() == null || helper.getRotaID().equalsIgnoreCase(""))) {
			throw new ControladorException("atencao.required", null, "Rota");
		} 
		else {
			FiltroRota filtroRota = new FiltroRota();

			filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");

            filtroRota.adicionarParametro(new ParametroSimples(
            FiltroRota.LOCALIDADE_ID, helper.getLocalidadeID()));
       
            filtroRota.adicionarParametro(new ParametroSimples(
            FiltroRota.SETOR_COMERCIAL_CODIGO, helper.getSetorComercialCD()));
            
            filtroRota.adicionarParametro(new ParametroSimples(
            FiltroRota.CODIGO_ROTA, helper.getRotaCD()));
            
            /*
             * 08/03/2012
             * 
             * Adi��o do id da rota no filtro para pesquisa
             */
            filtroRota.adicionarParametro(new ParametroSimples(
            		FiltroRota.ID_ROTA, helper.getRotaID()));
            // fim da altera��o

			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,
			ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroRota, Rota.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				throw new ControladorException("atencao.pesquisa.rota_inexistente");
			} 
			else {
				
				Rota objetoRota = (Rota) Util.retonarObjetoDeColecao(colecaoPesquisa);

				if (objetoRota.getSetorComercial().getId().intValue() != quadraInserir
					.getSetorComercial().getId().intValue()) {
					
					throw new ControladorException("atencao.quadra_rota_mesmo_setor_comercial");
				} 
				else {
					quadraInserir.setRota(objetoRota);
				}
			}
		}

		// ========================================================================

		// Indicador de Uso
		if (helper.getIndicadorUso() != null && !helper.getIndicadorUso().equals("")){
			quadraInserir.setIndicadorUso(Short.valueOf(helper.getIndicadorUso()));
		}
		else{
			quadraInserir.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		}
		

		// ========================================================================

		if (helper.getQuadraId() == null || helper.getQuadraId().equals("")){
			
			//Verifica se ja existe uma quadra cadastrada com o mesmo numero
			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.ID_SETORCOMERCIAL, quadraInserir.getSetorComercial().getId()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
			FiltroQuadra.NUMERO_QUADRA, new Integer(quadraInserir.getNumeroQuadra())));

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroQuadra, Quadra.class
					.getName());

			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				
				throw new ControladorException("atencao.pesquisa.quadra_ja_cadastrada", null, ""
				+ quadraInserir.getNumeroQuadra(), helper.getSetorComercialCD()
				+ "-" + objetoSetorComercial.getDescricao(),
				helper.getLocalidadeID() + "-" + objetoLocalidade.getDescricao());
			}

			// ========================================================================
		}
		
		// Bairro
		if(helper.getBairroCD() != null && !helper.getBairroCD().equals("")){
			
			// Verifica se ja existe uma Bairro cadastrada com o mesmo numero
			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, helper.getBairroCD()));
			
			// Munic�pio
			if(helper.getMunicipioID() != null && !helper.getMunicipioID().equals("")){
				
				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, helper.getMunicipioID()));
			}

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroBairro, Bairro.class
					.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				throw new ControladorException("atencao.pesquisa.bairro_inexistente");
			} 
			else {
				
				Bairro objetoBairro = (Bairro) Util.retonarObjetoDeColecao(colecaoPesquisa);

				quadraInserir.setBairro(objetoBairro);
			}
		}
		

		// Ultima altera��o
		quadraInserir.setUltimaAlteracao(new Date());
		
		return quadraInserir;
    }
    
    
    /**
     * Integra��o com o conceito de face da quadra
     *
     * @author Raphael Rossiter
     * @date 21/05/2009
     *
     * @param idImovel
     * @return IntegracaoQuadraFaceHelper
     * @throws ControladorException
     */
    public IntegracaoQuadraFaceHelper integracaoQuadraFace(Integer idImovel) throws ControladorException{
    	
    	IntegracaoQuadraFaceHelper integracaoQuadraFaceHelper = new IntegracaoQuadraFaceHelper();
    	
    	SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		/*
		 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da 
		 * tabela SISTEMA_PARAMETROS); os campos de INDICADOR_REDE_AGUA, INDICADOR_REDE_ESGOTO
		 * DISTRITO_OPERACIONAL e BACIA ser�o obtidos a partir da face da quadra que est�
		 * associada ao im�vel selecionado.
		 */
    	if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
			
			try {
	    		
				QuadraFace quadraFace  = this.repositorioQuadra.pesquisarQuadraFace(idImovel);
				
				if (quadraFace != null){
					
					integracaoQuadraFaceHelper.setIndicadorRedeAgua(quadraFace.getIndicadorRedeAgua());
					integracaoQuadraFaceHelper.setIndicadorRedeEsgoto(quadraFace.getIndicadorRedeEsgoto());
					integracaoQuadraFaceHelper.setDistritoOperacional(quadraFace.getDistritoOperacional());
					integracaoQuadraFaceHelper.setBacia(quadraFace.getBacia());
				}
				else{
					throw new ControladorException("atencao.imovel_sem_quadra_face", null, "Rota");
				}
				
	    	} 
			catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new FachadaException(ex.getMessage(), ex);
	    	}
		}
    	
    	/*
		 * Caso contr�rio; ser�o obtidos atrav�s da quadra
		 */
		else{
			
			try {
	    		
				Quadra quadra  = this.repositorioQuadra.pesquisarQuadra(idImovel);
				
				integracaoQuadraFaceHelper.setIndicadorRedeAgua(quadra.getIndicadorRedeAgua());
				integracaoQuadraFaceHelper.setIndicadorRedeEsgoto(quadra.getIndicadorRedeEsgoto());
				integracaoQuadraFaceHelper.setDistritoOperacional(quadra.getDistritoOperacional());
				integracaoQuadraFaceHelper.setBacia(quadra.getBacia());
				
	    	} catch (ErroRepositorioException ex) {
	    		ex.printStackTrace();
	    		throw new FachadaException(ex.getMessage(), ex);
	    	}
		}
		
		return integracaoQuadraFaceHelper;
    }

	/**
	 * [UC0928] - Manter Situa��o Especial de Faturamento
	 * [FS0004] - Verificar Exist�ncia da Quadra
	 * 
	 * Pesquisa uma cole��o de quadra com filtrando tamb�m pelo setor comercial.
	 * 
	 */
	public Quadra obterQuadraSetorComercial(int idSetor, int numeroQuadra) throws ControladorException{
		try {
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA,numeroQuadra));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL,idSetor));
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<Quadra> colecaoQuadra = 
				getControladorUtil().pesquisar(filtroQuadra,Quadra.class.getName());

			if( !Util.isVazioOrNulo(colecaoQuadra)){
				return colecaoQuadra.iterator().next();
			}
			return null;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}		
	}
	
	/**
	 * Pesquisa os ids de Todas as localidaes.
	 *
	 * @author Hugo Leonardo
	 * @date 08/07/2010
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidades() throws ControladorException {
		
		try {
			return repositorioLocalidade.pesquisarIdsLocalidades();
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

}
