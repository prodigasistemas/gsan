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
package gcom.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 * Implementação do RepositorioLigacaoAgua
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public class RepositorioLigacaoAguaHBM implements IRepositorioLigacaoAgua {

	private static IRepositorioLigacaoAgua instancia;

	/**
	 * Construtor da classe RepositorioLigacaoAguaHBM
	 */
	private RepositorioLigacaoAguaHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioLigacaoAgua getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioLigacaoAguaHBM();
		}
	return instancia;
	}
		
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarConsumoMinimoLigacaoAgua(LigacaoAgua ligacaoAgua) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String update;
		
		try {
			
			update = "update LigacaoAgua set ";
			if (ligacaoAgua.getNumeroConsumoMinimoAgua() != null) {
				update = update + "numeroConsumoMinimoAgua = :consumoMinimo, ";
			} else {
				update = update + "numeroConsumoMinimoAgua = null, ";
			}
			
			update = update + "ultimaAlteracao = :dataCorrente "
					+ "where id = :ligacaoAguaId";

			if (ligacaoAgua.getNumeroConsumoMinimoAgua() != null) {
				
				session.createQuery(update).setInteger("consumoMinimo",
						ligacaoAgua.getNumeroConsumoMinimoAgua()).setTimestamp(
						"dataCorrente", ligacaoAgua.getUltimaAlteracao())
						.setInteger("ligacaoAguaId", ligacaoAgua.getId())
						.executeUpdate();
				
			} else {
				
				session.createQuery(update).setTimestamp("dataCorrente",
						ligacaoAgua.getUltimaAlteracao()).setInteger(
						"ligacaoAguaId", ligacaoAgua.getId()).executeUpdate();
				
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água/Histórico de Instalação de Hidrômetro
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param helper
	 * @exception ErroRepositorioExceptions
	 */
	public void efetuarCorteLigacaoAgua(DadosEfetuacaoCorteLigacaoAguaHelper helper) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String update;
		Query query = null;
		try {
			update = "update Imovel set "
				+"ligacaoAguaSituacao.id = :ligacaoAguaSituacao, " 
				+"ultimaAlteracao = :dataCorrente "
				+"where id = :imovelId";
			session.createQuery(update)
				.setInteger("ligacaoAguaSituacao", helper.getImovel().getLigacaoAguaSituacao().getId())
				.setTimestamp("dataCorrente", helper.getImovel().getUltimaAlteracao())
				.setInteger("imovelId", helper.getImovel().getId())
				.executeUpdate();
			
			update = "update LigacaoAgua set "
						+"dataCorte = :dataCorte, numeroSeloCorte ="+ helper.getLigacaoAgua().getNumeroSeloCorte();
			update += ",corteTipo.id = :corteTipoId, "
						+"motivoCorte.id = :motivoCorteId, "
						+"ultimaAlteracao = :dataCorrente "
						+"where id = :ligacaoAguaId";
			
			query = session.createQuery(update)
						.setTimestamp("dataCorte",helper.getLigacaoAgua().getDataCorte())
						.setTimestamp("dataCorrente", helper.getLigacaoAgua().getUltimaAlteracao())
						.setInteger("corteTipoId", helper.getLigacaoAgua().getCorteTipo().getId())
						.setInteger("motivoCorteId", helper.getLigacaoAgua().getMotivoCorte().getId())
						.setInteger("ligacaoAguaId", helper.getLigacaoAgua().getId());
			query.executeUpdate();
			
			
			if(helper.getHidrometroInstalacaoHistorico() != null) {
				update = "update HidrometroInstalacaoHistorico set ";
				update += "numeroLeituraCorte ="+helper.getHidrometroInstalacaoHistorico().getNumeroLeituraCorte();
				update += ",ultimaAlteracao = :dataCorrente "
					     +"where id = :hidrometroId";
				
				query = session.createQuery(update)
							.setTimestamp("dataCorrente", helper.getLigacaoAgua().getUltimaAlteracao())
							.setInteger("hidrometroId", helper.getHidrometroInstalacaoHistorico().getId());

				query.executeUpdate();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água/Histórico de Instalação de Hidrômetro
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param helper
	 * @exception ErroRepositorioExceptions
	 */
	public void efetuarCorteAdministrativoLigacaoAgua(DadosEfetuacaoCorteLigacaoAguaHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;
		try {
			update = "update LigacaoAgua set "
						+"corteTipo.id = :corteTipoId, "
						+"ultimaAlteracao = :dataCorrente, "
						+"dataCorteAdministrativo = :dataCorteAdministrativo "
						+"where id = :ligacaoAguaId";
			
			session.createQuery(update)
				.setTimestamp("dataCorrente", new Date())
				.setInteger("corteTipoId", helper.getLigacaoAgua().getCorteTipo().getId())
				.setTimestamp("dataCorteAdministrativo", helper.getLigacaoAgua().getDataCorteAdministrativo())
				.setInteger("ligacaoAguaId", helper.getLigacaoAgua().getId())
				.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Efetuar Restabelecimento da Ligação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água
	 * 
	 * @author Flávio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaRestabelecimento(LigacaoAgua ligacaoAgua)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String update;
		try {
			update = "update LigacaoAgua set "
						+"dataRestabelecimentoAgua = :dataRestabelecimentoAgua, "
						+"ultimaAlteracao = :dataCorrente "
						+"where id = :ligacaoAguaId";
			
			session.createQuery(update)
				.setTimestamp("dataRestabelecimentoAgua",ligacaoAgua.getDataRestabelecimentoAgua())
				.setTimestamp("dataCorrente",new Date())
				.setInteger("ligacaoAguaId", ligacaoAgua.getId())
				.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0357] Efetuar Religação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água
	 * 
	 * @author Flávio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaReligacao(LigacaoAgua ligacaoAgua)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String update;
		try {
			update = "update LigacaoAgua set "
						+"dataReligacao = :dataReligacao, "
						+"ultimaAlteracao = :dataCorrente "
						+"where id = :ligacaoAguaId";
			
			session.createQuery(update)
				.setTimestamp("dataReligacao",ligacaoAgua.getDataReligacao())
				.setTimestamp("dataCorrente",new Date())
				.setInteger("ligacaoAguaId", ligacaoAgua.getId())
				.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os parámetros necessários da Ligacao de água
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsLigacaoAgua(Integer idImovel) throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT ligAgua.id,ligAgua.dataCorte,ligAgua.dataSupressao "
					+ "FROM LigacaoAgua ligAgua "
					+ "WHERE ligAgua.id = :idLigacaoAgua";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idLigacaoAgua", idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}
	
	/**
	 * [UC0054] - Inserir Dados da Tarifa Social 
	 * 
	 * Recupera o consumo mínimo fixado do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 04/0/2006
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoFixado(Integer idImovel) throws ErroRepositorioException {

		Integer consumoFixado = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT ligAgua.numeroConsumoMinimoAgua "
					+ "FROM LigacaoAgua ligAgua "
					+ "WHERE ligAgua.id = :idLigacaoAgua";

			consumoFixado = (Integer) session.createQuery(consulta).setInteger(
					"idLigacaoAgua", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return consumoFixado;
	}
	
	/**
	 * 
	 * Pesquisa o id do hidrometro
	 * 
	 * @author Sávio Luiz
	 * @date 19/02/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdHidrometroInstalacaoHistorico(Integer idImovel) throws ErroRepositorioException {

		Integer retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT hidInstHist.id "
					+ "FROM LigacaoAgua ligAgua "
					+ "LEFT JOIN ligAgua.hidrometroInstalacaoHistorico hidInstHist "
					+ "WHERE ligAgua.id = :idLigacaoAgua";

			retornoConsulta = (Integer) session.createQuery(consulta)
					.setInteger("idLigacaoAgua", idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}
	
	public Collection verificaExistenciaLigacaoAgua(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		
		try {

			String consulta = "SELECT ligAgua.id "
					+ "FROM LigacaoAgua ligAgua "
					+ "INNER JOIN ligAgua.hidrometroInstalacaoHistorico hidInstHist "
					+ "WHERE ligAgua.id = :idLigacaoAgua";

			retorno = session.createQuery(consulta)
					.setInteger("idLigacaoAgua", idImovel)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Imóvel]
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoAguaSituacao
	 * @param idConsumoTipo
	 * @return LigacaoAguaSituacaoConsumoTipo
	 * @throws ErroRepositorioException
	 */
	public LigacaoAguaSituacaoConsumoTipo pesquisarLigacaoAguaSituacaoConsumoTipo(Integer idLigacaoAguaSituacao,
			Integer idConsumoTipo) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		LigacaoAguaSituacaoConsumoTipo retorno = null;
		
		try {

			String consulta = "SELECT lact "
					+ "FROM LigacaoAguaSituacaoConsumoTipo lact "
					+ "INNER JOIN lact.consumoTipo consumoTipo "
					+ "INNER JOIN lact.ligacaoAguaSituacao last "
					+ "WHERE consumoTipo.id = :idConsumoTipo AND last.id = :idLigacaoAguaSituacao ";

			retorno = (LigacaoAguaSituacaoConsumoTipo) session.createQuery(consulta)
					.setInteger("idConsumoTipo", idConsumoTipo)
					.setInteger("idLigacaoAguaSituacao", idLigacaoAguaSituacao)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
    /**
	 * 
	 * Atualiza o tipo de corte
	 * 
	 * Autor: Hugo Amorim
	 * 
	 * Data: 18/05/2009
	 */
	public void atualizarTipoCorte(IntegracaoComercialHelper integracaoComercialHelper) throws ErroRepositorioException{
		 Session session = HibernateUtil.getSession();
		 Query query = null;
		try{
		 String update = "UPDATE gcom.atendimentopublico.ligacaoagua.LigacaoAgua" +
		 				 " SET corteTipo = :tipoCorte , " +
		 				 " ultimaAlteracao = :dataAlteracao " +
		 				 " where id = :idImovel";
		 query = session.createQuery(update);
		 
		 query.setTimestamp("dataAlteracao", integracaoComercialHelper.getLigacaoAgua().getUltimaAlteracao());
		 query.setInteger("tipoCorte", integracaoComercialHelper.getLigacaoAgua().getCorteTipo().getId());
		 query.setParameter("idImovel",integracaoComercialHelper.getMatriculaImovel());
		 
		 query.executeUpdate();
		 } catch (HibernateException e) {
	            throw new ErroRepositorioException("Erro no Hibernate");
	     } finally {
	            HibernateUtil.closeSession(session);
	     }
	}
	
	/**TODO: COSANPA
	 * @author Wellington Rocha
	 * Data: 21/03/2012
	 * 
	 * Pesquisar todas as situações de ligações de água ativas
	 * 
	 * Geração de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection pesquisarLigacaoAguaSituacao() throws ErroRepositorioException {
 		Collection retorno = null;
 		
 		Session session = HibernateUtil.getSession();
 		String consulta = null;
 		
 		try{
 			consulta = " select ligacaoAguaSituacao " 
 					+ " from LigacaoAguaSituacao ligacaoAguaSituacao " 
 					+ " where ligacaoAguaSituacao.indicadorUso = :indicadorUso ";
 			
 			retorno = (Collection) session.createQuery(consulta)
 			.setInteger("indicadorUso", ConstantesSistema.SIM.intValue()).list();
 			
 		}catch(HibernateException e) {
 			throw new ErroRepositorioException(e,"Erro no hibernate");
 		}finally {
 			HibernateUtil.closeSession(session);
 		}
 		return retorno;
 	}
}