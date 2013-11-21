/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.operacional;

import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroAbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroManutencaoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
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
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Leandro Cavalcanti
 * @created 12 de junho de 2006
 */
public class ControladorOperacionalSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	SessionContext sessionContext;
	
	protected IRepositorioOperacional repositorioOperacional;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioOperacional = RepositorioOperacionalHBM.getInstancia();
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
	 * Faz o controle de concorrencia de programação de abastecimento
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 04/12/2006
	 * @throws ControladorException
	 */
	private void verificarAbastecimentoProgramacaoControleConcorrencia(
			AbastecimentoProgramacao abastecimentoProgramacao)
			throws ControladorException {

		FiltroAbastecimentoProgramacao filtro = new FiltroAbastecimentoProgramacao();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.ID, abastecimentoProgramacao
						.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro,
				AbastecimentoProgramacao.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		AbastecimentoProgramacao abastecimentoProgramacaoAtual = (AbastecimentoProgramacao) Util
				.retonarObjetoDeColecao(colecao);

		if (abastecimentoProgramacaoAtual.getUltimaAlteracao().after(
				abastecimentoProgramacao.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * Faz o controle de concorrencia da programação da manutenção
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 04/12/2006
	 * @throws ControladorException
	 */
	private void verificarManutencaoProgramacaoControleConcorrencia(
			ManutencaoProgramacao manutencaoProgramacao)
			throws ControladorException {

		FiltroManutencaoProgramacao filtro = new FiltroManutencaoProgramacao();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroManutencaoProgramacao.ID, manutencaoProgramacao.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro,
				ManutencaoProgramacao.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ManutencaoProgramacao manutencaoProgramacaoAtual = (ManutencaoProgramacao) Util
				.retonarObjetoDeColecao(colecao);

		if (manutencaoProgramacaoAtual.getUltimaAlteracao().after(
				manutencaoProgramacaoAtual.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * [UC0001] Inserir Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 26/01/2007
	 * 
	 * @param Distrito
	 *            Operaciona Descrição do parâmetro
	 */
	public Integer inserirDistritoOperacional(String descricao,
			String descricaoAbreviada, String idSetorAbastecimento,
			Usuario usuarioLogado)
			throws ControladorException {

		DistritoOperacional distritoOperacional = new DistritoOperacional();
		distritoOperacional.setDescricao(descricao);
		distritoOperacional.setDescricaoAbreviada(descricaoAbreviada);

		SetorAbastecimento setorAbesteciento = new SetorAbastecimento();
		setorAbesteciento.setId(new Integer(idSetorAbastecimento));
		distritoOperacional.setSetorAbastecimento(setorAbesteciento);

		distritoOperacional.setUltimaAlteracao(new Date());
		distritoOperacional.setIndicadorUso( new Integer(1).shortValue() );

		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.DESCRICAO, descricao));

		Collection colecaoDistritoOperacional = getControladorUtil().pesquisar(
				filtroDistritoOperacional, DistritoOperacional.class.getName());

		Integer idDistritoOperacional = null;

		if (colecaoDistritoOperacional.isEmpty()) {
			idDistritoOperacional = (Integer) getControladorUtil().inserir(
					distritoOperacional);
		} else {
			throw new ControladorException(
					"atencao.distrito_operacional_existente");
		}

		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DISTRITO_OPERACIONAL_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_DISTRITO_OPERACIONAL_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		distritoOperacional.setOperacaoEfetuada(operacaoEfetuada);
		distritoOperacional.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(distritoOperacional);

		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		return idDistritoOperacional;
	}

	/**
	 * [UC0414] - Informar Programação de Abastecimento e Manutenção
	 * 
	 * [SB0006] - Atualizar Programação de Abastecimento na Base de Dados
	 * [SB0007] - Atualizar Programação de Manutenção na Base de Dados
	 * 
	 * @author Rafael Pinto
	 * @date 09/11/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarProgramacaoAbastecimentoManutencao(
			Collection colecaoProgramacaoAbastecimento,
			Collection colecaoProgramacaoAbastecimentoRemovidas,
			Collection colecaoProgramacaoManutencao,
			Collection colecaoProgramacaoManutencaoRemovidas, Usuario usuario)
			throws ControladorException {

		// [UC0107] - Registrar Transação
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao
				.setId(Operacao.OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		Iterator itera = null;

		// [SB0006] - Atualizar Programação de Abastecimento na Base de Dados
		if (colecaoProgramacaoAbastecimento != null
				&& !colecaoProgramacaoAbastecimento.isEmpty()) {

			itera = colecaoProgramacaoAbastecimento.iterator();

			while (itera.hasNext()) {

				AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) itera
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (abastecimentoProgramacao.getId() != null
						&& abastecimentoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarAbastecimentoProgramacaoControleConcorrencia(abastecimentoProgramacao);
				}

				abastecimentoProgramacao.setUltimaAlteracao(new Date());

				// [UC0107] - Registrar Transação
				abastecimentoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				abastecimentoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(abastecimentoProgramacao);

				this.getControladorUtil().inserirOuAtualizar(
						abastecimentoProgramacao);
			}
		}

		if (colecaoProgramacaoAbastecimentoRemovidas != null
				&& !colecaoProgramacaoAbastecimentoRemovidas.isEmpty()) {

			Iterator iter = colecaoProgramacaoAbastecimentoRemovidas.iterator();

			while (iter.hasNext()) {

				AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) iter
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (abastecimentoProgramacao.getId() != null
						&& abastecimentoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarAbastecimentoProgramacaoControleConcorrencia(abastecimentoProgramacao);
				}

				abastecimentoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				abastecimentoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(abastecimentoProgramacao);

				this.getControladorUtil().remover(abastecimentoProgramacao);
			}
		}

		if (colecaoProgramacaoManutencao != null
				&& !colecaoProgramacaoManutencao.isEmpty()) {
			itera = colecaoProgramacaoManutencao.iterator();

			// [SB0007] - Atualizar Programação de Manutenção na Base de Dados
			while (itera.hasNext()) {

				ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) itera
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (manutencaoProgramacao.getId() != null
						&& manutencaoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarManutencaoProgramacaoControleConcorrencia(manutencaoProgramacao);
				}
				manutencaoProgramacao.setUltimaAlteracao(new Date());

				// [UC0107] - Registrar Transação
				manutencaoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				manutencaoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(manutencaoProgramacao);

				this.getControladorUtil().inserirOuAtualizar(
						manutencaoProgramacao);
			}
		}

		if (colecaoProgramacaoManutencaoRemovidas != null
				&& !colecaoProgramacaoManutencaoRemovidas.isEmpty()) {

			Iterator iter = colecaoProgramacaoManutencaoRemovidas.iterator();

			while (iter.hasNext()) {

				ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) iter
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// então verifica o controle de concorrencia
				if (manutencaoProgramacao.getId() != null
						&& manutencaoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarManutencaoProgramacaoControleConcorrencia(manutencaoProgramacao);
				}

				manutencaoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				manutencaoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(manutencaoProgramacao);

				this.getControladorUtil().remover(manutencaoProgramacao);
			}
		}

	}
	
	/**
	 * [UC0522] MANTER DISTRITO OPERACIONAL 
	 * 			
	 * 			Remover Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 05/02/2007
	 * 
	 * @pparam distritoOperacional
	 * @throws ControladorException
	 */
	public void removerDistritoOperacional(String[] ids, Usuario usuarioLogado)throws ControladorException {

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
        this.getControladorUtil().remover(ids, DistritoOperacional.class.getName(),null, null);
}

	/**
	 * [UC0522] Manter Distrito Operacional [SB0001]Atualizar Municipio
	 * 
	 * @author Eduardo Bianchi
	 * @date 09/02/2007
	 * 
	 * @pparam distritoOperacional
	 * @throws ControladorException
	 */
	public void atualizarDistritoOperacional(DistritoOperacional distritoOperacional,
			Usuario usuarioLogado) throws ControladorException {		
		
		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		distritoOperacional.setOperacaoEfetuada(operacaoEfetuada);
		distritoOperacional.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(distritoOperacional);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		// [FS0002] - Atualização realizada por outro usuário
		
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
		// Seta o filtro para buscar o Distrito Operacional na base
		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, distritoOperacional.getId()));

		// Procura o Distrito Operacional na base
		Collection distritosOperacionaisAtualizados = getControladorUtil().pesquisar(filtroDistritoOperacional,DistritoOperacional.class.getName());

		DistritoOperacional distritoOperacionalNaBase = (DistritoOperacional) Util.retonarObjetoDeColecao(distritosOperacionaisAtualizados);

		if (distritoOperacionalNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o distrito Operacional já foi atualizado por outro usuário
		// durante esta atualização

		if (distritoOperacionalNaBase.getUltimaAlteracao().after(distritoOperacional.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		distritoOperacional.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(distritoOperacional);

	}
	

	/**
	 * [UC0524] Inserir Sistema de Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/03/2007
	 * 
	 * 
	 */
	
	public Integer inserirSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado)
			throws ControladorException {

			// [FS0003] - Verificando a existência do Sistema de Esgoto
	
			FiltroSistemaEsgoto filtroSistemaEsgoto= new FiltroSistemaEsgoto();
			
			filtroSistemaEsgoto.adicionarParametro(new ComparacaoTextoCompleto(FiltroSistemaEsgoto.DESCRICAO,sistemaEsgoto.getDescricao()));
			
			Collection colecaoSistemaEsgoto = getControladorUtil().pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			
			if (colecaoSistemaEsgoto != null && !colecaoSistemaEsgoto.isEmpty()){
				throw new ControladorException("atencao.divisao_esgoto.existente", null, sistemaEsgoto.getDescricao());
			}
	
			
			sistemaEsgoto.setUltimaAlteracao(new Date());
	
			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_INSERIR_SISTEMA_ESGOTO, new UsuarioAcaoUsuarioHelper
					(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	
			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_INSERIR_SISTEMA_ESGOTO);
	
			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);
	
			sistemaEsgoto.setOperacaoEfetuada(operacaoEfetuada);
			sistemaEsgoto.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(sistemaEsgoto);
			// ------------ REGISTRAR TRANSAÇÃO----------------------------
	
			Integer idSistemaEsgoto = (Integer) getControladorUtil().inserir(sistemaEsgoto);
			sistemaEsgoto.setId(idSistemaEsgoto);
	
			return idSistemaEsgoto;
	}
	
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0001]Atualizar Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 19/03/2007
	 * 
	 */
	
	
	public void atualizarSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado) 
						throws ControladorException {

		
		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SISTEMA_ESGOTO_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SISTEMA_ESGOTO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		sistemaEsgoto.setOperacaoEfetuada(operacaoEfetuada);
		sistemaEsgoto.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(sistemaEsgoto);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		// [FS0002] - Atualização realizada por outro usuário
		
		FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
		// Seta o filtro para buscar o sistema de esgoto na base
		filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, sistemaEsgoto.getId()));

		// Procura sistema de esgoto na base
		Collection sistemaEsgotoAtualizados = getControladorUtil().pesquisar(filtroSistemaEsgoto,SistemaEsgoto.class.getName());

		SistemaEsgoto sistemaEsgotoNaBase = (SistemaEsgoto) Util.retonarObjetoDeColecao(sistemaEsgotoAtualizados);

		if (sistemaEsgotoNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o sistema de esgoto já foi atualizado por outro usuário
		// durante esta atualização

		if (sistemaEsgotoNaBase.getUltimaAlteracao().after(sistemaEsgoto.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		sistemaEsgoto.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(sistemaEsgoto);

	}
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0002]Remover Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/03/2007
	 * 
	 */
	
	public void removerSistemaEsgoto(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SISTEMA_ESGOTO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		

	       // [FS0003]Sistema de Esgoto possui vinculos no sistema
        this.getControladorUtil().remover(ids, SistemaEsgoto.class.getName(),operacaoEfetuada, colecaoUsuarios);

	}
	
	/**
	 * [UC0081] Manter Marca Hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2007
	 * 
	 */
	
	public void removerHidrometroMarca(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REMOVER_MARCA_HIDROMETRO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		

	       // [FS0003]Sistema de Esgoto possui vinculos no sistema
        this.getControladorUtil().remover(ids, HidrometroMarca.class.getName(),operacaoEfetuada, colecaoUsuarios);

	}
	
	/**
	 * [UC0081] Manter Hidrometro Marca
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2007
	 * 
	 */		
	public void atualizarHidrometroMarca(HidrometroMarca hidrometroMarca,Usuario usuarioLogado) 
						throws ControladorException {

		
		// [UC0107] - Registrar Transação
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_MARCA_HIDROMETRO,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_MARCA_HIDROMETRO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		hidrometroMarca.setOperacaoEfetuada(operacaoEfetuada);
		hidrometroMarca.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(hidrometroMarca);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		// [FS0002] - Atualização realizada por outro usuário
		
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		// Seta o filtro para buscar a marca de hidrometro na base
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID, hidrometroMarca.getId()));

		// Procura sistema de esgoto na base
		Collection hidrometromMarcaAtualizados = getControladorUtil().pesquisar(filtroHidrometroMarca,HidrometroMarca.class.getName());

		HidrometroMarca hidrometromMarcaNaBase = (HidrometroMarca) Util.retonarObjetoDeColecao(hidrometromMarcaAtualizados);

		if (hidrometromMarcaNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o sistema de esgoto já foi atualizado por outro usuário
		// durante esta atualização

		if (hidrometromMarcaNaBase.getUltimaAlteracao().after(hidrometroMarca.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		hidrometroMarca.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(hidrometroMarca);

	}
	
	/**
	 * [UC0596] - Inserir Qualidade de agua
	 * 
	 * Pesquisa as fonte de captacao apatir da tabela de SetorFonteCaptacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 * 
	 * @param Collection colecaoSetorComercial
	 * @throws ControladorException
	 */
	
	public Collection<FonteCaptacao> pesquisarFonteCaptacao(Collection colecaoSetorComercial)
		throws ControladorException {
		
		try {
			return this.repositorioOperacional.pesquisarFonteCaptacao(colecaoSetorComercial);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);

		} 
	}	
}