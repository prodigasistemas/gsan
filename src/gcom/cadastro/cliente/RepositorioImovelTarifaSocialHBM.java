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
package gcom.cadastro.cliente;

import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 *   
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public class RepositorioImovelTarifaSocialHBM implements IRepositorioImovelTarifaSocial {

	private static IRepositorioImovelTarifaSocial instancia;

	/**
	 * Constructor for the RepositorioClienteTipoHBM object
	 */
	public RepositorioImovelTarifaSocialHBM() {
	} 

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioImovelTarifaSocial getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioImovelTarifaSocialHBM();
		}

		return instancia;
	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples("imovel.imovelPerfil",ImovelPerfil.TARIFA_SOCIAL));
		filtroClienteImovel.adicionarParametro(new ParametroSimples("clienteRelacaoTipo.id",ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
 
		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteImovel,
							"clienteImovel",
							" select distinct new gcom.cadastro.cliente.ClienteImovelSimplificado(clienteImovel.imovel.id,clienteImovel.imovel.numeroImovel,clienteImovel.imovel.logradouroCep, clienteImovel.imovel.logradouroBairro," +
							"clienteImovel.imovel.quadra,clienteImovel.imovel.enderecoReferencia,clienteImovel.imovel.complementoEndereco,clienteImovel.cliente.nome,clienteImovel.cliente.id,clienteImovel.imovel.setorComercial," +
							"clienteImovel.imovel.localidade,clienteImovel.imovel.ultimaAlteracao) " +
							" from gcom.cadastro.cliente.ClienteImovel as clienteImovel  " +
							" left join clienteImovel.imovel.tarifaSocialDado " +
							" left join clienteImovel.imovel.logradouroCep.cep  " +
							" left join clienteImovel.imovel.quadra  " +
							" left join clienteImovel.imovel.enderecoReferencia  " +
							" left join clienteImovel.cliente  " +
							" left join clienteImovel.imovel.setorComercial  ",
							session).setFirstResult(10 * numeroPagina)
							.setMaxResults(10).list()));

			// Carrega os objetos informados no filtro
/*			if (!filtroClienteImovel
					.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil.processaObjetosParaCarregamento(
						filtroClienteImovel
								.getColecaoCaminhosParaCarregamentoEntidades(),
						retorno);
			}
*/
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}
	
	
	/**
	 * Pesquisa a quantidade de tarifa social
	 *  @author Rafael Santos
	 *  @since 05/09/2006
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public int pesquisarQuantidadeImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException {

		// obtém a sessão
		Session session = HibernateUtil.getSession();
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples("imovel.imovelPerfil",ImovelPerfil.TARIFA_SOCIAL));
		filtroClienteImovel.adicionarParametro(new ParametroSimples("clienteRelacaoTipo.id",ClienteRelacaoTipo.USUARIO));
		//filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		
		filtroClienteImovel.getColecaoCaminhosParaCarregamentoEntidades().clear();
 
		// cria a coleção de retorno
		Integer quantidade = null;

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			quantidade = (Integer)GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteImovel,
							"clienteImovel",
							"select count(distinct clienteImovel.imovel.id) "
									+ "from gcom.cadastro.cliente.ClienteImovel as clienteImovel ",

							session).uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)

		quantidade.intValue();

		return quantidade;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Imóvel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomia(Integer idImovel) 
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT tarSocDadoEconomia "
					+ " FROM TarifaSocialDadoEconomia tarSocDadoEconomia "
					+ " INNER JOIN FETCH tarSocDadoEconomia.imovel imov "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.tarifaSocialRevisaoMotivo tarSocRevMot "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.tarifaSocialExclusaoMotivo tarSocExcMot "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.tarifaSocialCartaoTipo tarSocCartaoTp "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.rendaTipo rendaTp "
					+ " WHERE imov.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id da Economia do Imóvel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomiaImovelEconomia(Integer idImovelEconomia) 
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT tarSocDadoEconomia "
					+ " FROM TarifaSocialDadoEconomia tarSocDadoEconomia "
					+ " INNER JOIN FETCH tarSocDadoEconomia.imovel imov "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.imovelEconomia imovEcon "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.tarifaSocialRevisaoMotivo tarSocRevMot "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.tarifaSocialExclusaoMotivo tarSocExcMot "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.tarifaSocialCartaoTipo tarSocCartaoTp "
					+ " LEFT JOIN FETCH tarSocDadoEconomia.rendaTipo rendaTp "
					+ " WHERE imovEcon.id = :idImovelEconomia ";

			retorno = session.createQuery(consulta).setInteger("idImovelEconomia",
					idImovelEconomia).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 02/01/2007
	 */
	public Collection verificarClienteCadastradoTarifaSocial(Integer idCliente) 
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT ci.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel ci "
					+ " INNER JOIN "
					+ " cadastro.imovel imov "
					+ " on ci.imov_id = imov.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imov_id = imov.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMotivo "
					+ " on tarSocDadoEcon.rtsm_id = tarSocRevMotivo.rtsm_id " 
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND ci.clie_id = :idCliente "
					+ " AND ci.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND ci.cifr_id is null "
					+ " AND tarSocDadoEcon.etsm_id is null "
					+ " AND imov.imov_qteconomia = 1 "
					+ " AND (tarSocRevMotivo.rtsm_id is null "
					+ " OR tarSocRevMotivo.rtsm_icpermiterecadastramento = " 
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_NAO.toString() + ")"
					+ " UNION "
					+ " SELECT imovEcon.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel_economia cie "
					+ " INNER JOIN "
					+ " cadastro.imovel_economia imovEcon "
					+ " on cie.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.imovel imov "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMotivo "
					+ " on tarSocDadoEcon.rtsm_id = tarSocRevMotivo.rtsm_id " 
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND cie.clie_id = :idCliente "
					+ " AND cie.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND cie.cifr_id is null "
					+ " AND tarSocDadoEcon.etsm_id is null "
					+ " AND (tarSocRevMotivo.rtsm_id is null "
					+ " OR tarSocRevMotivo.rtsm_icpermiterecadastramento = " 
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_NAO.toString() + ")" ;

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setInteger("idCliente", idCliente)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0009] - Manter Cliente 
	 * 
	 * Verifica se o cliente usuário está na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 16/02/2007
	 */
	public Collection verificarClienteUsuarioCadastradoTarifaSocial(Integer idCliente) 
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT ci.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel ci "
					+ " INNER JOIN "
					+ " cadastro.imovel imov "
					+ " on ci.imov_id = imov.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imov_id = imov.imov_id "
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND ci.clie_id = :idCliente "
					+ " AND ci.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND ci.cifr_id is null "
					+ " AND tarSocDadoEcon.etsm_id is null "
					+ " AND imov.imov_qteconomia = 1 "
					+ " UNION "
					+ " SELECT imovEcon.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel_economia cie "
					+ " INNER JOIN "
					+ " cadastro.imovel_economia imovEcon "
					+ " on cie.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.imovel imov "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMotivo "
					+ " on tarSocDadoEcon.rtsm_id = tarSocRevMotivo.rtsm_id " 
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND cie.clie_id = :idCliente "
					+ " AND cie.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND cie.cifr_id is null "
					+ " AND tarSocDadoEcon.etsm_id is null ";

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setInteger("idCliente", idCliente)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialUmaEconomia(Integer idCliente, Integer idImovel) 
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT ci.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel ci "
					+ " INNER JOIN "
					+ " cadastro.imovel imov "
					+ " on ci.imov_id = imov.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imov_id = imov.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMotivo "
					+ " on tarSocDadoEcon.rtsm_id = tarSocRevMotivo.rtsm_id " 
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND ci.clie_id = :idCliente "
					+ " AND ci.imov_id <> :idImovel "
					+ " AND ci.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND ci.cifr_id is null "
					+ " AND tarSocDadoEcon.etsm_id is null "
					+ " AND (tarSocRevMotivo.rtsm_id is null "
					+ " OR tarSocRevMotivo.rtsm_icpermiterecadastramento = " 
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_NAO.toString() + ")"
					+ " UNION "
					+ " SELECT imovEcon.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel_economia cie "
					+ " INNER JOIN "
					+ " cadastro.imovel_economia imovEcon "
					+ " on cie.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.imovel imov "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMotivo "
					+ " on tarSocDadoEcon.rtsm_id = tarSocRevMotivo.rtsm_id " 
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND cie.clie_id = :idCliente "
					+ " AND cie.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND cie.cifr_id is null "
					+ " AND tarSocDadoEcon.etsm_id is null "
					+ " AND (tarSocRevMotivo.rtsm_id is null "
					+ " OR tarSocRevMotivo.rtsm_icpermiterecadastramento = " 
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_NAO.toString() + ")" ;

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setInteger("idCliente", idCliente)
					.setInteger("idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário da economia do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(Integer idCliente, Integer idImovelEconomia) 
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT ci.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel ci "
					+ " INNER JOIN "
					+ " cadastro.imovel imov "
					+ " on ci.imov_id = imov.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imov_id = imov.imov_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMotivo "
					+ " on tarSocDadoEcon.rtsm_id = tarSocRevMotivo.rtsm_id " 
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND ci.clie_id = :idCliente "
					+ " AND ci.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND imov.imov_qteconomia = 1 "
					+ " AND ci.cifr_id is null "
					+ " AND (tarSocRevMotivo.rtsm_id is null "
					+ " OR tarSocRevMotivo.rtsm_icpermiterecadastramento = " 
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_NAO.toString() + ")"
					+ " UNION "
					+ " SELECT imovEcon.imov_id as idImovel "
					+ " FROM cadastro.cliente_imovel_economia cie "
					+ " INNER JOIN "
					+ " cadastro.imovel_economia imovEcon "
					+ " on cie.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.imovel imov "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMotivo "
					+ " on tarSocDadoEcon.rtsm_id = tarSocRevMotivo.rtsm_id " 
					+ " WHERE imov.iper_id = " 
					+ ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND cie.clie_id = :idCliente ";
					
					if (idImovelEconomia != null){
						consulta = consulta + " AND cie.imec_id <> :idImovelEconomia ";								
					}
					
					consulta = consulta + " AND cie.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND cie.cifr_id is null "
					+ " AND (tarSocRevMotivo.rtsm_id is null "
					+ " OR tarSocRevMotivo.rtsm_icpermiterecadastramento = " 
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_NAO.toString() + ")" ;

					if (idImovelEconomia != null){
						retorno = session.createSQLQuery(consulta).addScalar("idImovel",
								Hibernate.INTEGER).setInteger("idCliente", idCliente)
								.setInteger("idImovelEconomia", idImovelEconomia).list();
					}else {
						retorno = session.createSQLQuery(consulta).addScalar("idImovel",
								Hibernate.INTEGER).setInteger("idCliente", idCliente).list();
					}
			

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o mesmo cliente está vinculado a mais de uma economia como
	 * usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public int pesquisarClienteImovelEconomiaCount(Integer idImovel,
			Integer idCliente) throws ErroRepositorioException {

		int retorno = 0;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT COUNT(clieImovEcon.cime_id) as contador "
					+ " FROM cadastro.cliente_imovel_economia clieImovEcon "
					+ " INNER JOIN " + " cadastro.imovel_economia imovEcon "
					+ " on clieImovEcon.imec_id = imovEcon.imec_id "
					+ " WHERE " + " clieImovEcon.clie_id = :idCliente "
					+ " AND clieImovEcon.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND clieImovEcon.cime_dtrelacaofim is null "
					+ " AND imovEcon.imov_id = :idImovel ";

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"contador", Hibernate.INTEGER).setInteger("idCliente",
					idCliente).setInteger("idImovel", idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os clientes usuários das economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public Collection pesquisarClientesUsuariosImovelEconomia(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clieImovEcon.clie_id as idCliente "
					+ " FROM cadastro.cliente_imovel_economia clieImovEcon "
					+ " INNER JOIN " 
					+ " cadastro.imovel_economia imovEcon "
					+ " on clieImovEcon.imec_id = imovEcon.imec_id "
					+ " WHERE " 
					+ " clieImovEcon.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND imovEcon.imov_id = :idImovel ";

			retorno = session.createSQLQuery(consulta).addScalar(
					"idCliente", Hibernate.INTEGER).setInteger("idImovel", idImovel)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o cliente usuário está vinculado na tarifa social a outro
	 * imóvel ou economia com motivo de revisão que permita recadastramento
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 04/01/2007
	 */
	public Collection pesquisarClientesUsuarioExistenteTarifaSocial(Integer idCliente) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clieImov.imov_id as idImovel, tarSocRevMot.rtsm_dstarsocrevisaomotivo as motivoRevisao, "
					+ " tarSocDadoEcon.tsde_id as idTarifaSocialDadoEconomia "
					+ " FROM cadastro.cliente_imovel clieImov "
					+ " INNER JOIN " 
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imov_id = clieImov.imov_id "
					+ " INNER JOIN " 
					+ " cadastro.tar_social_rev_motivo tarSocRevMot "
					+ " on tarSocRevMot.rtsm_id = tarSocDadoEcon.rtsm_id "
					+ " WHERE "
					+ " clieImov.clie_id = :idCliente "
					+ " AND clieImov.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND clieImov.clim_dtrelacaofim is null "
					+ " AND tarSocRevMot.rtsm_icpermiterecadastramento = " 
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_SIM.toString()
					+ " AND tarSocDadoEcon.etsm_id is null "
					+ " UNION "
					+ " SELECT imovEcon.imov_id as idImovel, tarSocRevMot.rtsm_dstarsocrevisaomotivo as motivoRevisao, "
					+ " tarSocDadoEcon.tsde_id as idTarifaSocialDadoEconomia "
					+ " FROM cadastro.cliente_imovel_economia clieImovEcon "
					+ " INNER JOIN "
					+ " cadastro.imovel_economia imovEcon "
					+ " on imovEcon.imec_id = clieImovEcon.imec_id "
					+ " INNER JOIN "
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on tarSocDadoEcon.imec_id = imovEcon.imec_id "
					+ " INNER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMot "
					+ " on tarSocRevMot.rtsm_id = tarSocDadoEcon.rtsm_id "
					+ " WHERE clieImovEcon.clie_id = :idCliente "
					+ " AND clieImovEcon.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND clieImovEcon.cime_dtrelacaofim is null "
					+ " AND tarSocRevMot.rtsm_icpermiterecadastramento = "
					+ TarifaSocialRevisaoMotivo.INDICADOR_PERMITE_RECADASTRAMENTO_SIM.toString()
					+ " AND tarSocDadoEcon.etsm_id is null ";

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("motivoRevisao",
					Hibernate.STRING).addScalar("idTarifaSocialDadoEconomia",
					Hibernate.INTEGER).setInteger("idCliente", idCliente)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe uma OS de vistoria para um imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarOSVistoriaImovel(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT os.id "
					+ " FROM OrdemServico os "
					+ " LEFT JOIN os.imovel imov " 
					+ " LEFT JOIN os.registroAtendimento ra "
					+ " LEFT JOIN ra.solicitacaoTipoEspecificacao step "
					+ " LEFT JOIN step.solicitacaoTipo sotp "
					+ " WHERE " 
					+ " os.codigoRetornoVistoria = 1 "
					+ " AND sotp.indicadorTarifaSocial = 1 "
					+ " AND imov.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe uma OS de vistoria para uma economia do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarOSVistoriaImovelEconomia(Integer idImovelEconomia) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT os.orse_id as idOrdemServico "
					+ " FROM atendimentopublico.ordem_servico os "
					+ " LEFT OUTER JOIN cadastro.imovel_economia imovEcon "
					+ " on imovEcon.imov_id = os.imov_id "
					+ " LEFT OUTER JOIN atendimentopublico.registro_atendimento ra "
					+ " on os.rgat_id = ra.rgat_id "
					+ " LEFT OUTER JOIN atendimentopublico.solicitacao_tipo_espec step "
					+ " on step.step_id = ra.step_id "
					+ " LEFT OUTER JOIN atendimentopublico.solicitacao_tipo sotp "
					+ " on sotp.sotp_id = step.sotp_id "
					+ " WHERE " 
					+ " os.orse_cdretornovistoria = 1 "
					+ " AND sotp.sotp_ictarifasocial = 1 "
					+ " AND imovEcon.imec_id = :idImovelEconomia ";

			retorno = session.createSQLQuery(consulta).addScalar(
					"idOrdemServico", Hibernate.INTEGER).setInteger(
					"idImovelEconomia", idImovelEconomia).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe um motivo de exclusão para o cliente que não permite
	 * recadastramento na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarClienteMotivoExclusaoRecadastramento(Integer idCliente) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT tarSocExcMot.etsm_icpermiterecadmtclt as icRecadastramento "
					+ " FROM cadastro.cliente_imovel clieImov "
					+ " INNER JOIN "
					+ " cadastro.imovel imov "
					+ " on clieImov.imov_id = imov.imov_id " 
					+ " INNER JOIN " 
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on clieImov.imov_id = tarSocDadoEcon.imov_id "
					+ " INNER JOIN " 
					+ " cadastro.tar_social_excl_motivo tarSocExcMot "
					+ " on tarSocExcMot.etsm_id = tarSocDadoEcon.etsm_id "
					+ " WHERE " 
					+ " clieImov.clie_id = :idCliente "
					+ " AND clieImov.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND imov.imov_qteconomia = 1 "
					+ " UNION "
					+ " SELECT tarSocExcMot.etsm_icpermiterecadmtclt as icRecadastramento "
					+ " FROM cadastro.cliente_imovel_economia clieImovEcon "
					+ " INNER JOIN " 
					+ " cadastro.imovel_economia imovEcon "
					+ " on clieImovEcon.imec_id = imovEcon.imec_id "
					+ " INNER JOIN " 
					+ " cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " on imovEcon.imec_id = tarSocDadoEcon.imec_id "
					+ " INNER JOIN " 
					+ " cadastro.tar_social_excl_motivo tarSocExcMot "
					+ " on tarSocExcMot.etsm_id = tarSocDadoEcon.etsm_id "
					+ " WHERE " 
					+ " clieImovEcon.clie_id = :idCliente "
					+ " AND clieImovEcon.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString();

			retorno = session.createSQLQuery(consulta).addScalar(
					"icRecadastramento", Hibernate.SHORT).setInteger(
					"idCliente", idCliente).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os cliente a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Integer pesquisarClienteImovelEconomia(Integer idClienteImovelEconomia) 
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clie.id as idCliente "
					+ " FROM ClienteImovelEconomia clieImovEcon "
					+ " INNER JOIN " 
					+ " clieImovEcon.cliente clie "
					+ " WHERE " 
					+ " clieImovEcon.id = :idClienteImovelEconomia ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idClienteImovelEconomia", idClienteImovelEconomia)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna a economia do imóvel a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloCliente(Integer idClienteImovelEconomia) 
			throws ErroRepositorioException {

		ImovelEconomia imovelEconomia = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT imovEcon "
					+ " FROM ClienteImovelEconomia clieImovEcon "
					+ " INNER JOIN " 
					+ " clieImovEcon.imovelEconomia imovEcon "
					+ " INNER JOIN FETCH imovEcon.imovelSubcategoria imovSub "
					+ " INNER JOIN FETCH imovSub.comp_id.imovel imov "
					+ " INNER JOIN FETCH imov.setorComercial sc "
					+ " INNER JOIN FETCH sc.municipio muni "
					+ " LEFT JOIN FETCH imovEcon.areaConstruidaFaixa areaConsFaixa "
					+ " WHERE " 
					+ " clieImovEcon.id = :idClienteImovelEconomia ";

			imovelEconomia = (ImovelEconomia) session.createQuery(consulta).setInteger(
					"idClienteImovelEconomia", idClienteImovelEconomia)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return imovelEconomia;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorno o histórico medição atual do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Collection pesquisarMedicaoHistoricoImovel(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT medicaoHistorico "
					+ " FROM MedicaoHistorico medicaoHistorico "
					+ " INNER JOIN "
					+ " medicaoHistorico.medicaoTipo medicaoTp "
					+ " LEFT JOIN " 
					+ " medicaoHistorico.ligacaoAgua ligAgua "
					+ " LEFT JOIN FETCH "
					+ " medicaoHistorico.leituraAnormalidadeFaturamento ltAnFat "
					+ " WHERE " 
					+ " medicaoTp.id = " 
					+ MedicaoTipo.LIGACAO_AGUA.toString()
					+ " AND "
					+ " ligAgua.id = :idImovel "
					+ " AND "
					+ " medicaoHistorico.anoMesReferencia = " 
					+ " ( SELECT max(medHist.anoMesReferencia) " 
					+ " FROM MedicaoHistorico medHist "
					+ " INNER JOIN "
					+ " medHist.medicaoTipo medTp "
					+ " LEFT JOIN "
					+ " medHist.ligacaoAgua lagu "
					+ " WHERE " 
					+ " medTp.id = "
					+ MedicaoTipo.LIGACAO_AGUA.toString()
					+ " AND "
					+ " lagu.id = :idImovel ) ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorno o histórico de consumo atual do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Collection pesquisarConsumoHistoricoImovel(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT consumoHistorico "
					+ " FROM ConsumoHistorico consumoHistorico "
					+ " INNER JOIN "
					+ " consumoHistorico.ligacaoTipo ligacaoTp "
					+ " INNER JOIN " 
					+ " consumoHistorico.imovel imov "
					+ " WHERE " 
					+ " ligacaoTp.id = " 
					+ LigacaoTipo.LIGACAO_AGUA.toString()
					+ " AND "
					+ " imov.id = :idImovel "
					+ " AND "
					+ " consumoHistorico.referenciaFaturamento = " 
					+ " ( SELECT max(consHist.referenciaFaturamento) " 
					+ " FROM ConsumoHistorico consHist "
					+ " INNER JOIN "
					+ " consHist.ligacaoTipo ligTp "
					+ " INNER JOIN "
					+ " consHist.imovel imovel "
					+ " WHERE " 
					+ " ligTp.id = "
					+ LigacaoTipo.LIGACAO_AGUA.toString()
					+ " AND "
					+ " imovel.id = :idImovel ) ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/01/2007
	 */
	public Collection pesquisarDadosClienteTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT tarSocDadoEcon.tsde_id as idTarSocDadoEcon, clie.clie_nmcliente as nomeCliente, "
					+ " imov.imov_dscomplementoendereco as complementoEnd, clie.clie_nncpf as cpf, "
					+ " clie.clie_nnrg as rg, orgExpRg.oerg_dsabreviado as orgExp, "
					+ " unidFed.unfe_dsufsigla as unidFed, tarSocDadoEcon.tsde_nncartaoprogramasocial as numCartaoProgSoc, "
					+ " tarSocCartaoTp.ctst_id as idTipoCartao, tarSocDadoEcon.tsde_vlrendafamiliar as rendaFamiliar, "
					+ " rendaTp.retp_id as idTipoRenda, tarSocDadoEcon.etsm_id as idMotivoExclusao, " 
					+ " tarSocCartaoTp.ctst_dstarifasocialcartaotipo as tipoCartao, tarSocDadoEcon.tsde_dtvalidadecartao as dataValidade, "
					+ " tarSocDadoEcon.tsde_nnmesesadesao as numeroParcelas, imov.imov_nncontratoenergia as contratoEnergia, "
					+ " tarSocDadoEcon.tsde_nnconsumoenergia as consumoMedio, imov.imov_nniptu as numeroIptu, "
					+ " imov.imov_nnareaconstruida as areaConstruida, imov.acon_id as areaConstruidaFaixa, " 
					+ " rendaTp.retp_dsrendatipo as tipoRenda, tarSocDadoEcon.rtsm_id as idMotivoRevisao, "
					+ " imov.imov_id as idImovel, sc.stcm_id as idSetorComercial, sc.muni_id as idMunicipio, qd.qdra_id as idQuadra, "
					+ " tarSocDadoEcon.tsde_dtexclusao as dataExclusao, "
					+ " tarSocDadoEcon.tsde_dtimplantacao as dataImplantacao, tarSocDadoEcon.tsde_dtrevisao as dataRevisao, " 
					+ " tarSocDadoEcon.tsde_qtrecadastramento as qtdeRecadastramento, tarSocDadoEcon.tsde_dtrecadastramento as dataRecadastramento, "
					+ " imov.imov_nnmorador as numeroMoradores, " 
					+ " tarSocExcMot.etsm_dstarsocexclusaomotivo as motivoExclusao, tarSocRevMot.rtsm_dstarsocrevisaomotivo as motivoRevisao, "
					+ " tarSocDadoEcon.tsde_tmultimaalteracao as ultimaAlteracao "
					+ " FROM cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " INNER JOIN "
					+ " cadastro.imovel imov "
					+ " on imov.imov_id = tarSocDadoEcon.imov_id "
					+ " INNER JOIN "
					+ " cadastro.setor_comercial sc "
					+ " on sc.stcm_id = imov.stcm_id "
					+ " INNER JOIN "
					+ " cadastro.quadra qd "
					+ " on qd.qdra_id = imov.qdra_id "
					+ " INNER JOIN "
					+ " cadastro.cliente_imovel clieImov "
					+ " on clieImov.imov_id = tarSocDadoEcon.imov_id "
					+ " AND clieImov.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND clieImov.clim_dtrelacaofim is null "
					+ " INNER JOIN "
					+ " cadastro.cliente clie "
					+ " on clie.clie_id = clieImov.clie_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.orgao_expedidor_rg orgExpRg "
					+ " on orgExpRg.oerg_id = clie.oerg_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.unidade_federacao unidFed "
					+ " on unidFed.unfe_id = clie.unfe_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_cartao_tipo tarSocCartaoTp "
					+ " on tarSocCartaoTp.ctst_id = tarSocDadoEcon.ctst_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.renda_tipo rendaTp "
					+ " on rendaTp.retp_id = tarSocDadoEcon.retp_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_excl_motivo tarSocExcMot "
					+ " on tarSocExcMot.etsm_id = tarSocDadoEcon.etsm_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMot "
					+ " on tarSocRevMot.rtsm_id = tarSocDadoEcon.rtsm_id "
					+ " WHERE " + " tarSocDadoEcon.imov_id = :idImovel ";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"idTarSocDadoEcon", Hibernate.INTEGER).addScalar(
					"nomeCliente", Hibernate.STRING).addScalar(
					"complementoEnd", Hibernate.STRING).addScalar("cpf",
					Hibernate.STRING).addScalar("rg", Hibernate.STRING)
					.addScalar("orgExp", Hibernate.STRING).addScalar("unidFed",
							Hibernate.STRING).addScalar("numCartaoProgSoc",
							Hibernate.LONG).addScalar("idTipoCartao",
							Hibernate.INTEGER).addScalar("rendaFamiliar",
							Hibernate.BIG_DECIMAL).addScalar("idTipoRenda",
							Hibernate.INTEGER).addScalar("idMotivoExclusao",
							Hibernate.INTEGER).addScalar("tipoCartao",
							Hibernate.STRING).addScalar("dataValidade",
							Hibernate.DATE).addScalar("numeroParcelas",
							Hibernate.SHORT).addScalar("contratoEnergia",
							Hibernate.LONG).addScalar("consumoMedio",
							Hibernate.INTEGER).addScalar("numeroIptu",
							Hibernate.BIG_DECIMAL).addScalar("areaConstruida",
							Hibernate.BIG_DECIMAL).addScalar(
							"areaConstruidaFaixa", Hibernate.INTEGER)
					.addScalar("tipoRenda", Hibernate.STRING).addScalar(
							"idMotivoRevisao", Hibernate.INTEGER).addScalar(
							"idImovel", Hibernate.INTEGER).addScalar(
							"idSetorComercial", Hibernate.INTEGER).addScalar(
							"idMunicipio", Hibernate.INTEGER).addScalar(
							"idQuadra", Hibernate.INTEGER).addScalar(
							"dataExclusao", Hibernate.DATE).addScalar(
							"dataImplantacao", Hibernate.DATE).addScalar(
							"dataRevisao", Hibernate.DATE).addScalar(
							"qtdeRecadastramento", Hibernate.SHORT).addScalar(
							"dataRecadastramento", Hibernate.DATE).addScalar(
							"numeroMoradores", Hibernate.SHORT).addScalar(
							"motivoExclusao", Hibernate.STRING).addScalar(
							"motivoRevisao", Hibernate.STRING).addScalar(
							"ultimaAlteracao", Hibernate.TIMESTAMP).setInteger(
							"idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna a tarifa social a partir do seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocial(Integer idTarifaSocial) 
			throws ErroRepositorioException {

		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT tarSocDadoEcon "
					+ " FROM TarifaSocialDadoEconomia tarSocDadoEcon "
					+ " LEFT JOIN FETCH tarSocDadoEcon.tarifaSocialCartaoTipo tarSocCarTp "
					+ " LEFT JOIN FETCH tarSocDadoEcon.rendaTipo rendaTp "
					+ " LEFT JOIN FETCH tarSocDadoEcon.tarifaSocialExclusaoMotivo tarSocExcMot "
					+ " WHERE " 
					+ " tarSocDadoEcon.id = :idTarifaSocial ";

			tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) session
					.createQuery(consulta).setInteger("idTarifaSocial",
							idTarifaSocial).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return tarifaSocialDadoEconomia;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Verifica se existe tarifa social para o imóvel que não tenha sido
	 * excluído
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public Collection pesquisarTarifaSocialImovel(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT tarSocDadoEcon.id "
					+ " FROM TarifaSocialDadoEconomia tarSocDadoEcon "
					+ " INNER JOIN "
					+ " tarSocDadoEcon.imovel imov "
					+ " WHERE " 
					+ " tarSocDadoEcon.dataExclusao is null "
					+ " AND imov.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes das economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clieImovEcon.clie_id as idCliente, clie.clie_nmcliente as nomeCliente, "
					+ " crtp.crtp_id as idTipoRelacao, crtp.crtp_dsclienterelacaotipo as tipoRelacao, " 
					+ " clie.clie_nncpf as cpf, clie.clie_nnrg as rg, " 
					+ " orgExpRg.oerg_dsabreviado as orgaoExpedidor, unidFed.unfe_dsufsigla as unidadeFederacao, "
					+ " clieImovEcon.cime_dtrelacaoinicio as dataInicioRelacao, clieImovEcon.cime_id as idClienteImovelEconomia, "
					+ " clie.clie_nncnpj as cnpj "
					+ " FROM cadastro.cliente_imovel_economia clieImovEcon "
					+ " INNER JOIN "
					+ " cadastro.cliente clie "
					+ " on clie.clie_id = clieImovEcon.clie_id "
					+ " INNER JOIN "
					+ " cadastro.cliente_relacao_tipo crtp "
					+ " on crtp.crtp_id = clieImovEcon.crtp_id "
					+ " AND (crtp.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " OR crtp.crtp_id = "
					+ ClienteRelacaoTipo.PROPRIETARIO.toString()
					+ " ) "
					+ " LEFT JOIN "
					+ " cadastro.orgao_expedidor_rg orgExpRg "
					+ " on orgExpRg.oerg_id = clie.oerg_id "
					+ " LEFT JOIN "
					+ " cadastro.unidade_federacao unidFed "
					+ " on unidFed.unfe_id = clie.unfe_id "
					+ " WHERE "
					+ " clieImovEcon.imec_id = :idImovelEconomia "
					+ " AND clieImovEcon.cime_dtrelacaofim is null ";

			retorno = session.createSQLQuery(consulta).addScalar("idCliente",
					Hibernate.INTEGER).addScalar("nomeCliente",
					Hibernate.STRING).addScalar("idTipoRelacao",
					Hibernate.INTEGER).addScalar("tipoRelacao",
					Hibernate.STRING).addScalar("cpf", Hibernate.STRING)
					.addScalar("rg", Hibernate.STRING).addScalar(
							"orgaoExpedidor", Hibernate.STRING).addScalar(
							"unidadeFederacao", Hibernate.STRING).addScalar(
							"dataInicioRelacao", Hibernate.DATE).addScalar(
							"idClienteImovelEconomia", Hibernate.INTEGER)
					.addScalar("cnpj", Hibernate.STRING).setInteger(
							"idImovelEconomia", idImovelEconomia).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna o id cliente usuário da economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Integer pesquisarClienteUsuarioImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ErroRepositorioException {

		Integer idClienteUsuario = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clieImovEcon.clie_id as idCliente "
					+ " FROM cadastro.cliente_imovel_economia clieImovEcon "
					+ " WHERE "
					+ " clieImovEcon.imec_id = :idImovelEconomia "
					+ " AND clieImovEcon.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " AND clieImovEcon.cime_dtrelacaofim is null ";

			idClienteUsuario = (Integer) session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER).setInteger(
							"idImovelEconomia", idImovelEconomia)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return idClienteUsuario;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clieImov.clie_id as idCliente, clie.clie_nmcliente as nomeCliente, "
					+ " crtp.crtp_id as idTipoRelacao, crtp.crtp_dsclienterelacaotipo as tipoRelacao, " 
					+ " clie.clie_nncpf as cpf, clie.clie_nnrg as rg, " 
					+ " orgExpRg.oerg_dsabreviado as orgaoExpedidor, unidFed.unfe_dsufsigla as unidadeFederacao, "
					+ " clieImov.clim_dtrelacaoinicio as dataInicioRelacao, clieImov.clim_id as idClienteImovel, " 
					+ " clie.clie_nncnpj as cnpj "
					+ " FROM cadastro.cliente_imovel clieImov "
					+ " INNER JOIN "
					+ " cadastro.cliente clie "
					+ " on clie.clie_id = clieImov.clie_id "
					+ " INNER JOIN "
					+ " cadastro.cliente_relacao_tipo crtp "
					+ " on crtp.crtp_id = clieImov.crtp_id "
					+ " AND (crtp.crtp_id = " 
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " OR crtp.crtp_id = "
					+ ClienteRelacaoTipo.PROPRIETARIO.toString()
					+ " ) "
					+ " LEFT JOIN "
					+ " cadastro.orgao_expedidor_rg orgExpRg "
					+ " on orgExpRg.oerg_id = clie.oerg_id "
					+ " LEFT JOIN "
					+ " cadastro.unidade_federacao unidFed "
					+ " on unidFed.unfe_id = clie.unfe_id "
					+ " WHERE "
					+ " clieImov.imov_id = :idImovel "
					+ " AND clieImov.clim_dtrelacaofim is null ";

			retorno = session.createSQLQuery(consulta).addScalar("idCliente",
					Hibernate.INTEGER).addScalar("nomeCliente",
					Hibernate.STRING).addScalar("idTipoRelacao",
					Hibernate.INTEGER).addScalar("tipoRelacao",
					Hibernate.STRING).addScalar("cpf", Hibernate.STRING)
					.addScalar("rg", Hibernate.STRING).addScalar(
							"orgaoExpedidor", Hibernate.STRING).addScalar(
							"unidadeFederacao", Hibernate.STRING).addScalar(
							"dataInicioRelacao", Hibernate.DATE).addScalar(
							"idClienteImovel", Hibernate.INTEGER).addScalar(
							"cnpj", Hibernate.STRING).setInteger("idImovel",
							idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa o cliente pelo seu id carregando o seu tipo
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 22/01/2007
	 */
	public Collection pesquisarClienteComClienteTipoTarifaSocial(Integer idCliente) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clie.id, clie.nome, clie.cpf, "  
					+ " clie.rg, clie.cnpj, clie.dataNascimento, "
					+ " clieTp.id, clieTp.indicadorPessoaFisicaJuridica, "
					+ " clie.dataEmissaoRg, orgExpRg.descricaoAbreviada, "
					+ " unidFed.sigla "
					+ " FROM Cliente clie "
					+ " INNER JOIN "
					+ " clie.clienteTipo clieTp "
					+ " LEFT JOIN "
					+ " clie.orgaoExpedidorRg orgExpRg "
					+ " LEFT JOIN "
					+ " clie.unidadeFederacao unidFed "
					+ " WHERE " 
					+ " clie.id = :idCliente ";

			retorno = session.createQuery(consulta).setInteger("idCliente",
					idCliente).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário para cada economia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 25/01/2007
	 */
	public Collection pesquisarDadosClienteEconomiaTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

/*			consulta = "SELECT tarSocDadoEcon.tsde_id as idTarSocDadoEcon, clie.clie_nmcliente as nomeCliente, "
					+ " imovEcon.imec_dscomplementoendereco as complementoEnd, clie.clie_nncpf as cpf, "
					+ " clie.clie_nnrg as rg, orgExpRg.oerg_dsabreviado as orgExp, "
					+ " unidFed.unfe_dsufsigla as unidFed, tarSocDadoEcon.tsde_nncartaoprogramasocial as numCartaoProgSoc, "
					+ " tarSocCartaoTp.ctst_id as idTipoCartao, tarSocDadoEcon.tsde_vlrendafamiliar as rendaFamiliar, "
					+ " rendaTp.retp_id as idTipoRenda, tarSocDadoEcon.etsm_id as idMotivoExclusao, " 
					+ " tarSocCartaoTp.ctst_dstarifasocialcartaotipo as tipoCartao, tarSocDadoEcon.tsde_dtvalidadecartao as dataValidade, "
					+ " tarSocDadoEcon.tsde_nnmesesadesao as numeroParcelas, imovEcon.imec_nncontratoenergia as contratoEnergia, "
					+ " tarSocDadoEcon.tsde_nnconsumoenergia as consumoMedio, imovEcon.imec_nniptu as numeroIptu, "
					+ " imovEcon.imec_nnareaconstruida as areaConstruida, imovEcon.acon_id as areaConstruidaFaixa, " 
					+ " rendaTp.retp_dsrendatipo as tipoRenda, tarSocDadoEcon.rtsm_id as idMotivoRevisao, "
					+ " imovEcon.imec_id as idImovelEconomia, sc.stcm_id as idSetorComercial, sc.muni_id as idMunicipio, "
					+ " tarSocDadoEcon.tsde_dtexclusao as dataExclusao, "
					+ " tarSocDadoEcon.tsde_dtimplantacao as dataImplantacao, tarSocDadoEcon.tsde_dtrevisao as dataRevisao, " 
					+ " tarSocDadoEcon.tsde_qtrecadastramento as qtdeRecadastramento, tarSocDadoEcon.tsde_dtrecadastramento as dataRecadastramento, "
					+ " clie.clie_id as idCliente, imovEcon.imec_nnmorador as numeroMoradores, imovEcon.scat_id as idSubcategoria, "
					+ " tarSocExcMot.etsm_dstarsocexclusaomotivo as motivoExclusao, tarSocRevMot.rtsm_dstarsocrevisaomotivo as motivoRevisao, "
					+ " tarSocDadoEcon.tsde_tmultimaalteracao as ultimaAlteracao "
					+ " FROM cadastro.tar_social_dado_economia tarSocDadoEcon "
					+ " LEFT JOIN "
					+ " cadastro.imovel_economia imovEcon "
					+ " on imovEcon.imec_id = tarSocDadoEcon.imec_id "
					+ " LEFT JOIN "
					+ " cadastro.imovel imov "
					+ " on imov.imov_id = imovEcon.imov_id "
					+ " LEFT JOIN "
					+ " cadastro.setor_comercial sc "
					+ " on sc.stcm_id = imov.stcm_id "
					+ " LEFT JOIN "
					+ " cadastro.cliente_imovel_economia clieImovEcon "
					+ " on clieImovEcon.imec_id = tarSocDadoEcon.imec_id "
					+ " AND clieImovEcon.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " AND clieImovEcon.cime_dtrelacaofim is null "
					+ " LEFT JOIN "
					+ " cadastro.cliente clie "
					+ " on clie.clie_id = clieImovEcon.clie_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.orgao_expedidor_rg orgExpRg "
					+ " on orgExpRg.oerg_id = clie.oerg_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.unidade_federacao unidFed "
					+ " on unidFed.unfe_id = clie.unfe_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_cartao_tipo tarSocCartaoTp "
					+ " on tarSocCartaoTp.ctst_id = tarSocDadoEcon.ctst_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.renda_tipo rendaTp "
					+ " on rendaTp.retp_id = tarSocDadoEcon.retp_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_excl_motivo tarSocExcMot "
					+ " on tarSocExcMot.etsm_id = tarSocDadoEcon.etsm_id "
					+ " LEFT OUTER JOIN "
					+ " cadastro.tar_social_rev_motivo tarSocRevMot "
					+ " on tarSocRevMot.rtsm_id = tarSocDadoEcon.rtsm_id "
					+ " WHERE " 
					+ " tarSocDadoEcon.imov_id = :idImovel "*/
					
			consulta = 
				"SELECT \n" +
				"  tarSocDadoEcon.tsde_id as idTarSocDadoEcon, \n" +
				"  case when ( CLIEMAISUMECON.CLIE_ID is not null ) then CLIEMAISUMECON.CLIE_NMCLIENTE  else CLIEUMAECONOMIA.CLIE_NMCLIENTE end as nomeCliente, \n" +
				"  case when ( CLIEMAISUMECON.CLIE_ID is not null ) then CLIECONMAISUMAECON.IMEC_DSCOMPLEMENTOENDERECO  else IMO.IMOV_DSCOMPLEMENTOENDERECO end as complementoEnd, \n" +
				"  case when ( CLIEMAISUMECON.CLIE_ID is not null ) then CLIEMAISUMECON.CLIE_NNCPF  else CLIEUMAECONOMIA.CLIE_NNCPF end as cpf, \n" +
				"  case when ( CLIEMAISUMECON.CLIE_ID is not null ) then CLIEMAISUMECON.CLIE_NNRG  else CLIEUMAECONOMIA.CLIE_NNRG end as rg, \n" +
				"  case when ( CLIEMAISUMECON.CLIE_ID is not null ) then orgExpRgUMAECONOMIA.OERG_DSORGAOEXPEDIDORRG  else orgExpRgMAISUMAECONOMIA.OERG_DSORGAOEXPEDIDORRG end as orgExp, \n" +
				"  case when ( CLIEMAISUMECON.CLIE_ID is not null ) then unidFedMAISUMAECONOMIA.UNFE_DSUFSIGLA  else unidFedUMAECONOMIA.UNFE_DSUFSIGLA end as unidFed, \n" +
				"  tarSocDadoEcon.tsde_nncartaoprogramasocial as numCartaoProgSoc, \n" +
				"  tarSocCartaoTp.ctst_id as idTipoCartao, \n" +
				"  tarSocDadoEcon.tsde_vlrendafamiliar as rendaFamiliar, \n" +
				"  rendaTp.retp_id as idTipoRenda, \n" +
				"  tarSocDadoEcon.etsm_id as idMotivoExclusao, \n" +
				"  tarSocCartaoTp.ctst_dstarifasocialcartaotipo as tipoCartao, \n" +
				"  tarSocDadoEcon.tsde_dtvalidadecartao as dataValidade, \n" +
				"  tarSocDadoEcon.tsde_nnmesesadesao as numeroParcelas, \n" +
				"  CLIECONMAISUMAECON.imec_nncontratoenergia as contratoEnergia, \n" +
				"  tarSocDadoEcon.tsde_nnconsumoenergia as consumoMedio, \n" +
				"  CLIECONMAISUMAECON.imec_nniptu as numeroIptu, \n" +
				"  CLIECONMAISUMAECON.imec_nnareaconstruida as areaConstruida, \n" +
				"  CLIECONMAISUMAECON.acon_id as areaConstruidaFaixa, \n" +
				"  rendaTp.retp_dsrendatipo as tipoRenda, \n" +
				"  tarSocDadoEcon.rtsm_id as idMotivoRevisao, \n" +
				"  CLIECONMAISUMAECON.imec_id as idImovelEconomia, \n" +
				"  sc.stcm_id as idSetorComercial, \n" +
				"  sc.muni_id as idMunicipio, \n" +
				"  tarSocDadoEcon.tsde_dtexclusao as dataExclusao, \n" +
				"  tarSocDadoEcon.tsde_dtimplantacao as dataImplantacao, \n" +
				"  tarSocDadoEcon.tsde_dtrevisao as dataRevisao, \n" +
				"  tarSocDadoEcon.tsde_qtrecadastramento as qtdeRecadastramento, \n" +
				"  tarSocDadoEcon.tsde_dtrecadastramento as dataRecadastramento, \n" +
				"  case when ( CLIEMAISUMECON.CLIE_ID is not null ) then CLIEMAISUMECON.CLIE_ID  else CLIEUMAECONOMIA.CLIE_ID end as idCliente, \n" +
				"  CLIECONMAISUMAECON.imec_nnmorador as numeroMoradores, \n" +
				"  CLIECONMAISUMAECON.scat_id as idSubcategoria, \n" +
				"  tarSocExcMot.etsm_dstarsocexclusaomotivo as motivoExclusao, \n" +
				"  tarSocRevMot.rtsm_dstarsocrevisaomotivo as motivoRevisao, \n" +
				"  tarSocDadoEcon.tsde_tmultimaalteracao as ultimaAlteracao \n" +
				"FROM \n" +
				"  cadastro.tar_social_dado_economia tarSocDadoEcon \n" +
				"  INNER JOIN CADASTRO.IMOVEL IMO ON ( IMO.IMOV_ID = tarSocDadoEcon.IMOV_ID ) \n" +
				"  INNER JOIN CADASTRO.SETOR_COMERCIAL SC ON ( SC.STCM_ID = IMO.STCM_ID ) \n" +
				"  -- CLIENTE PARA UMA ECONOMIA \n" +
				"  LEFT JOIN CADASTRO.CLIENTE_IMOVEL CLIIMOUMAECONOMIA ON ( CLIIMOUMAECONOMIA.CRTP_ID = 2 AND CIFR_ID IS NULL AND CLIIMOUMAECONOMIA.IMOV_ID = IMO.IMOV_ID ) \n" +
				"  LEFT JOIN CADASTRO.CLIENTE CLIEUMAECONOMIA  ON ( CLIEUMAECONOMIA.CLIE_ID = CLIIMOUMAECONOMIA.CLIE_ID ) \n" +
				"  LEFT JOIN cadastro.orgao_expedidor_rg orgExpRgUMAECONOMIA on ( orgExpRgUMAECONOMIA.oerg_id = CLIEUMAECONOMIA.oerg_id ) \n" +
				"  LEFT JOIN cadastro.unidade_federacao unidFedUMAECONOMIA on ( unidFedUMAECONOMIA.unfe_id = CLIEUMAECONOMIA.unfe_id  ) \n" +
				"  -- CLIENTE PARA MAIS DE UMA ECONOMIA \n" +
				"  LEFT JOIN CADASTRO.IMOVEL_ECONOMIA CLIECONMAISUMAECON ON ( IMO.IMOV_ID = CLIECONMAISUMAECON.IMOV_ID ) \n" +
				"  LEFT JOIN CADASTRO.CLIENTE_IMOVEL_ECONOMIA CLIIMOECOMAISUMAECON ON ( CLIIMOECOMAISUMAECON.CRTP_ID = 2 AND CLIIMOECOMAISUMAECON.CIFR_ID IS NULL AND CLIIMOECOMAISUMAECON.IMEC_ID = CLIECONMAISUMAECON.IMEC_ID ) \n" +
				"  LEFT JOIN CADASTRO.CLIENTE CLIEMAISUMECON  ON ( CLIEMAISUMECON.CLIE_ID = CLIIMOECOMAISUMAECON.CLIE_ID ) \n" +
				"  LEFT JOIN cadastro.orgao_expedidor_rg orgExpRgMAISUMAECONOMIA on ( orgExpRgMAISUMAECONOMIA.oerg_id = CLIEMAISUMECON.oerg_id ) \n" +
				"  LEFT JOIN cadastro.unidade_federacao unidFedMAISUMAECONOMIA on ( unidFedMAISUMAECONOMIA.unfe_id = CLIEMAISUMECON.unfe_id ) \n" +
				"  LEFT JOIN cadastro.tar_social_cartao_tipo tarSocCartaoTp on ( tarSocCartaoTp.ctst_id = tarSocDadoEcon.ctst_id ) \n" +
				"  LEFT JOIN cadastro.tar_social_excl_motivo tarSocExcMot on ( tarSocExcMot.etsm_id = tarSocDadoEcon.etsm_id ) \n" +
				"  LEFT JOIN cadastro.tar_social_rev_motivo tarSocRevMot on ( tarSocRevMot.rtsm_id = tarSocDadoEcon.rtsm_id ) \n" +
				"  LEFT OUTER JOIN cadastro.renda_tipo rendaTp on ( rendaTp.retp_id = tarSocDadoEcon.retp_id  ) \n" +
				"WHERE \n" +
				"  tarSocDadoEcon.imov_id = :idImovel ";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"idTarSocDadoEcon", Hibernate.INTEGER).addScalar(
					"nomeCliente", Hibernate.STRING).addScalar(
					"complementoEnd", Hibernate.STRING).addScalar("cpf",
					Hibernate.STRING).addScalar("rg", Hibernate.STRING)
					.addScalar("orgExp", Hibernate.STRING).addScalar("unidFed",
							Hibernate.STRING).addScalar("numCartaoProgSoc",
							Hibernate.LONG).addScalar("idTipoCartao",
							Hibernate.INTEGER).addScalar("rendaFamiliar",
							Hibernate.BIG_DECIMAL).addScalar("idTipoRenda",
							Hibernate.INTEGER).addScalar("idMotivoExclusao",
							Hibernate.INTEGER).addScalar("tipoCartao",
							Hibernate.STRING).addScalar("dataValidade",
							Hibernate.DATE).addScalar("numeroParcelas",
							Hibernate.SHORT).addScalar("contratoEnergia",
							Hibernate.LONG).addScalar("consumoMedio",
							Hibernate.INTEGER).addScalar("numeroIptu",
							Hibernate.BIG_DECIMAL).addScalar("areaConstruida",
							Hibernate.BIG_DECIMAL).addScalar(
							"areaConstruidaFaixa", Hibernate.INTEGER)
					.addScalar("tipoRenda", Hibernate.STRING).addScalar(
							"idMotivoRevisao", Hibernate.INTEGER).addScalar(
							"idImovelEconomia", Hibernate.INTEGER).addScalar(
							"idSetorComercial", Hibernate.INTEGER).addScalar(
							"idMunicipio", Hibernate.INTEGER).addScalar(
							"dataExclusao", Hibernate.DATE).addScalar(
							"dataImplantacao", Hibernate.DATE).addScalar(
							"dataRevisao", Hibernate.DATE).addScalar(
							"qtdeRecadastramento", Hibernate.SHORT).addScalar(
							"dataRecadastramento", Hibernate.DATE).addScalar(
							"idCliente", Hibernate.INTEGER).addScalar(
							"numeroMoradores", Hibernate.SHORT).addScalar(
							"idSubcategoria", Hibernate.INTEGER).addScalar(
							"motivoExclusao", Hibernate.STRING).addScalar(
							"motivoRevisao", Hibernate.STRING).addScalar(
							"ultimaAlteracao", Hibernate.TIMESTAMP).setInteger(
							"idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa a economia do imóvel pelo seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloId(Integer idImovelEconomia) 
			throws ErroRepositorioException {

		ImovelEconomia imovelEconomia = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT imovEcon "  
					+ " FROM ImovelEconomia imovEcon "
					+ " WHERE " 
					+ " imovEcon.id = :idImovelEconomia ";

			imovelEconomia = (ImovelEconomia) session.createQuery(consulta).setInteger("idImovelEconomia",
					idImovelEconomia).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return imovelEconomia;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Seta o indicador do nome da conta para 2 nos clientes proprietário e
	 * usuários
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public void atualizarNomeContaClienteImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String update = null;

		try {

			update = "UPDATE ClienteImovel clieImov "
				+ " SET clieImov.indicadorNomeConta = 2 "
				+ " WHERE clieImov.imovel.id = :idImovel "
				+ " AND clieImov.dataFimRelacao is null "
				+ " AND (clieImov.clienteRelacaoTipo.id = "
				+ ClienteRelacaoTipo.PROPRIETARIO
				+ " OR clieImov.clienteRelacaoTipo.id = "
				+ ClienteRelacaoTipo.RESPONSAVEL + ")";

			session.createQuery(update).setInteger("idImovel", idImovel)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}
	
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Autor: Vivianne Sousa
	 * 
	 * Data: 27/10/2008
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel) 
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = 	" select imec " +
						" from ImovelEconomia imec " +
						" left join imec.imovelSubcategoria imsb " +
						" left join imsb.comp_id.imovel imov " +
						" left join fetch imec.tarifaSocialDadoEconomias  " +
						" where imov.id = :idImovel " ;
					
			retorno = session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
}
