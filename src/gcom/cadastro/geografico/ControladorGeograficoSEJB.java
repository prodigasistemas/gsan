package gcom.cadastro.geografico;

import gcom.gui.ActionServletException;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.RemocaoInvalidaException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public class ControladorGeograficoSEJB implements SessionBean {
	
	
	private static final long serialVersionUID = 1L;

	private IRepositorioGeografico repositorioGeografico = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioGeografico = RepositorioGeograficoHBM.getInstancia();
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
	 * Atualiza um bairro no sistema
	 * 
	 * @param bairro
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void atualizarBairro(Bairro bairro, Collection colecaoBairroArea,
			Collection colecaoBairroAreaRemover, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_BAIRRO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		bairro.setOperacaoEfetuada(operacaoEfetuada);
		bairro.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(bairro);
		// ------------ REGISTRAR TRANSAÇÃO ----------------------------

		FiltroBairro filtroBairro = new FiltroBairro();

		// Parte que atualiza um bairro na base

		// Parte de Validacao com Timestamp

		// Seta o filtro para buscar o bairro na base
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID,
				bairro.getId()));

		// Procura o bairro na base

		// Alterado Por Tiago Moreno - 17/07/2006

		// Bairro bairroNaBase = (Bairro) ((List)
		// (getControladorUtil().pesquisar(
		// filtroBairro, Bairro.class.getName()))).get(0);

		Bairro bairroNaBase = null;
		Collection bairroNaBaseColecao = (Collection) getControladorUtil()
				.pesquisar(filtroBairro, Bairro.class.getName());

		if (bairroNaBaseColecao != null && !bairroNaBaseColecao.isEmpty()) {
			bairroNaBase = (Bairro) bairroNaBaseColecao.iterator().next();
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// fim da alteração - Tiago Moreno - 17/07/2006

		// Verificar se o cliente já foi atualizado por outro usuário
		// durante
		// esta atualização
		if (bairroNaBase.getUltimaAlteracao()
				.after(bairro.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// limpa os parametros para fazer uma nova pesquisa
		filtroBairro.limparListaParametros();

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, "" + bairro.getCodigo()));

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, bairro.getMunicipio().getId()));

		Collection bairrosPesquisados = getControladorUtil().pesquisar(
				filtroBairro, Bairro.class.getName());

		if (bairrosPesquisados != null && !bairrosPesquisados.isEmpty()) {

			// Procura o bairro na base
			Bairro bairroPesquisado = (Bairro) ((List) (bairrosPesquisados))
					.get(0);

			if (!bairroPesquisado.getId().equals(bairro.getId())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.pesquisa_bairro_ja_cadastrada");
			}
		}

		// Atualiza a data de última alteração
		bairro.setUltimaAlteracao(new Date());

		// Atualiza o bairro
		getControladorUtil().atualizar(bairro);
		// Fim da parte que atualiza um bairro na base

		if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {
			Iterator iterator = colecaoBairroArea.iterator();

			while (iterator.hasNext()) {
				BairroArea bairroArea = (BairroArea) iterator.next();

				bairroArea.setBairro(bairro);
				bairroArea.setUltimaAlteracao(new Date());

				bairroArea.setOperacaoEfetuada(operacaoEfetuada);
				bairroArea.adicionarUsuario(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(bairroArea);

				getControladorUtil().inserirOuAtualizar(bairroArea);
			}

		}

		if (colecaoBairroAreaRemover != null
				&& !colecaoBairroAreaRemover.isEmpty()) {
			Iterator iteratorRemover = colecaoBairroAreaRemover.iterator();

			while (iteratorRemover.hasNext()) {
				BairroArea bairroAreaRemover = (BairroArea) iteratorRemover
						.next();
				getControladorUtil().remover(bairroAreaRemover);
			}

		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param codigoSetorComercial
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Collection pesquisarMunicipoPeloSetorComercial(
			String codigoSetorComercial, String idMunicipio)
			throws ControladorException {
		try {
			return repositorioGeografico.pesquisarMunicipoPeloSetorComercial(
					codigoSetorComercial, idMunicipio);
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

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
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
	 * Verifica se o município possui CEP por logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 16/05/2006
	 * 
	 * @param municipio
	 * @return boolean
	 */
	public boolean verificarMunicipioComCepPorLogradouro(Municipio municipio)
			throws ControladorException {

		boolean retorno = true;

		if (municipio.getCepInicio() != null && municipio.getCepFim() != null
				&& municipio.getCepInicio().equals(municipio.getCepFim())) {

			retorno = false;

		}

		return retorno;
	}

	/**
	 * Método que retorna o maior código do bairro de um município
	 * 
	 * @author Rafael Corrêa
	 * @date 10/07/2006
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ControladorException
	 */

	public int pesquisarMaximoCodigoBairro(Integer idMunicipio)
			throws ControladorException {
		try {
			return repositorioGeografico
					.pesquisarMaximoCodigoBairro(idMunicipio);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa um município pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Município
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Municipio pesquisarObjetoMunicipioRelatorio(Integer idMunicipio)
			throws ControladorException {
		try {
			// pesquisa as gerencias regionais existentes no sistema
			Object[] objetoMunicipio = repositorioGeografico
					.pesquisarObjetoMunicipioRelatorio(idMunicipio);

			Municipio municipio = new Municipio();

			municipio.setId((Integer) objetoMunicipio[0]);
			municipio.setNome((String) objetoMunicipio[1]);

			return municipio;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa um bairro pelo código e pelo id do município
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Bairro
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Bairro pesquisarObjetoBairroRelatorio(Integer codigoBairro,
			Integer idMunicipio) throws ControladorException {
		try {
			// pesquisa as gerencias regionais existentes no sisitema
			Object[] objetoBairro = repositorioGeografico
					.pesquisarObjetoBairroRelatorio(codigoBairro, idMunicipio);

			Bairro bairro = new Bairro();

			bairro.setCodigo(((Integer) objetoBairro[0]).intValue());
			bairro.setNome((String) objetoBairro[1]);

			return bairro;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0001] Inserir Município
	 * 
	 * @author Kassia Albuquerque
	 * @date 18/12/2006
	 * 
	 * @param municipio
	 *            Descrição do parâmetro
	 */
	public Integer inserirMunicipio(Municipio municipio, Usuario usuarioLogado)
			throws ControladorException {

		// [FS0003] - Verificando a existência do Municipio

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, municipio.getId()));

		Collection colecaoMunicipio = getControladorUtil().pesquisar(
				filtroMunicipio, Municipio.class.getName());

		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
			throw new ControladorException("atencao.municipio.existente");
		}

		// [FS0005] - Validar CEP
		Integer cepInicial = new Integer(municipio.getCepInicio());
		Integer cepFinal = new Integer(municipio.getCepFim());

		if (cepInicial.intValue() > cepFinal.intValue()) {
			throw new ActionServletException(
					"atencao.cep_inicio.anterior.cep_fim");
		}
		municipio.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MUNICIPIO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MUNICIPIO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		municipio.setOperacaoEfetuada(operacaoEfetuada);
		municipio.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(municipio);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		Integer idMunicipio = (Integer) getControladorUtil().inserir(municipio);
		municipio.setId(idMunicipio);

		return idMunicipio;
	}

	/**
	 * [UC0035] Inserir Bairro
	 * 
	 * Insere um objeto do tipo bairro no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 22/12/2006
	 * @param bairro
	 * @param colecaoBairroArea
	 * @return idBairro
	 * @throws ControladorException
	 */
	public Integer inserirBairro(Bairro bairro, Collection colecaoBairroArea,
			Usuario usuarioLogado) throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_BAIRRO_INSERIR, new UsuarioAcaoUsuarioHelper(
						usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		bairro.setOperacaoEfetuada(operacaoEfetuada);
		bairro.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(bairro);
		// ------------ REGISTRAR TRANSAÇÃO ----------------------------

		// -- Inserir o bairro --
		Integer idBairro = (Integer) this.getControladorUtil().inserir(bairro);

		// -- codigo para inserir BairroArea --
		bairro.setId(idBairro);
		Iterator iterator = colecaoBairroArea.iterator();

		while (iterator.hasNext()) {

			BairroArea bairroArea = (BairroArea) iterator.next();
			bairroArea.setBairro(bairro);

			bairroArea.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			bairroArea.setOperacaoEfetuada(operacaoEfetuada);
			bairroArea.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(bairroArea);
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			this.getControladorUtil().inserir(bairroArea);

		}
		// -- fim de codigo para inserir BairroArea --
		return idBairro;
	}

	/**
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * 
	 * @return colecao de BairroArea
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection pesquisarBairroArea(Integer idBairro)
			throws ControladorException {
		try {
			return repositorioGeografico.pesquisarBairroArea(idBairro);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Remover Bairro
	 * 
	 * Remove os bairros e area bairro selecionados na lista da funcionalidade
	 * Manter Bairro
	 * 
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * @param bairro
	 * @param colecaoBairroArea
	 * @return idBairro
	 * @throws ControladorException
	 */
	public void removerBairro(String[] ids, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		// remover areaBairro para cada bairro a remover
		int i = 0;

		while (i < ids.length) {
			String idBairro = ids[i];
			try {
				repositorioGeografico
						.removerTodosBairroAreaPorBairro(new Integer(idBairro));
			} catch (RemocaoInvalidaException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.dependencias.existentes", ex);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			i = i + 1;
		}

		// remover bairro(s)
		this.getControladorUtil().remover(ids, Bairro.class.getName(),
				operacaoEfetuada, colecaoUsuarios);

	}

	/**
	 * [UC0006] Filtrar Município
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @param Integer
	 * @return boolean
	 */
	public boolean verificarExistenciaMunicipio(Integer codigoMunicipio)
			throws ControladorException {

		// [FS0004] - Verificando a existência do Municipio
		boolean retorno = true;

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, codigoMunicipio));

		Collection colecaoMunicipio = getControladorUtil().pesquisar(
				filtroMunicipio, Municipio.class.getName());

		if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
			throw new ControladorException("atencao.municipio.inexistente");
		}
		return retorno;

	}

	/**
	 * [UC0005] Manter Municipio 
	 * 			
	 * 			Remover Municipio
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void removerMunicipio(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MUNICIPIO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
	       // [FS0003]Municipiopossui vinculos no sistema
        this.getControladorUtil().remover(ids, Municipio.class.getName(),null, null);
}

    
	
	/**
	 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
	 * 
	 * @author Kassia Albuquerque
	 * @date 10/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void atualizarMunicipio(Municipio municipio,
			Usuario usuarioLogado) throws ControladorException {

		
		// [FS0005] - Validar CEP
		Integer cepInicial = new Integer(municipio.getCepInicio());
		Integer cepFinal = new Integer(municipio.getCepFim());

		if (cepInicial.intValue() > cepFinal.intValue()) {
			throw new ActionServletException("atencao.cep_inicio.anterior.cep_fim");
		}
//		municipio.setUltimaAlteracao(new Date());		
		
		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MUNICIPIO_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MUNICIPIO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		municipio.setOperacaoEfetuada(operacaoEfetuada);
		municipio.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(municipio);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		// [FS0002] - Atualização realizada por outro usuário
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		// Seta o filtro para buscar o municipio na base
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipio.getId()));

		// Procura municipio na base
		Collection municipioAtualizadas = getControladorUtil().pesquisar(filtroMunicipio,Municipio.class.getName());

		Municipio municipioNaBase = (Municipio) Util.retonarObjetoDeColecao(municipioAtualizadas);

		if (municipioNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o municipio já foi atualizado por outro usuário
		// durante esta atualização

		if (municipioNaBase.getUltimaAlteracao().after(municipio.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		municipio.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(municipio);

	}
	
	 /**
	 * Método que retorna o maior id do Município
	 * 
	 * @author Rafael Corrêa
	 * @date 24/07/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoIdMunicipio() throws ControladorException {
		try {
			return repositorioGeografico.pesquisarMaximoIdMunicipio();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
    
    /**
	 * Método que retorna o municipio do Imovel
	 * 
	 * @author Hugo Amorim
	 * @date 27/08/2009
	 * 
	 * @return Municipio
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipioDoImovel(Integer idImovel) 
    	throws ControladorException{
    		try {
    			return repositorioGeografico.pesquisarMunicipioDoImovel(idImovel);
    		} catch (ErroRepositorioException ex) {
    			throw new ControladorException("erro.sistema", ex);
    		}
    }
    
    /**
	 * [UC0592] Filtrar Relação de Parcelamentos
	 * 
	 * Método reponsável por retornar todos os municípios que possuem alguma
	 * associação com uma localidade (localidade.muni_idprincipal != null)
	 * 
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipiosAssociadoLocalidade() throws ControladorException{
		try {
			return repositorioGeografico.pesquisarMunicipiosAssociadoLocalidade();
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}    	
    }
	
	
}
