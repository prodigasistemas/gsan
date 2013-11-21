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
package gcom.cadastro.unidade;

import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioUnidadeHBM implements IRepositorioUnidade {

    private static IRepositorioUnidade instancia;

    /**
     * Construtor da classe RepositorioAcessoHBM
     */

    private RepositorioUnidadeHBM() {
    }

    /**
     * Retorna o valor de instancia
     * 
     * @return O valor de instancia
     */
    public static IRepositorioUnidade getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioUnidadeHBM();
        }

        return instancia;
    }

    
    /**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Obtém a unidade associada ao usuário que estiver efetuando o registro de atendimento 
	 * (UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com 
	 * UNID_ID=(UNID_ID da tabela USUARIO com USUR_NMLOGIN=
	 * Login do usuário que estiver efetuando o registro de atendimento) e UNID_ICABERTURARA=1)
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional obterUnidadeOrganizacionalAberturaRAAtivoUsuario(String loginUsuario)
		throws ErroRepositorioException {
		
    	UnidadeOrganizacional retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = "SELECT usua.unidadeOrganizacional " 
					+ "FROM Usuario usua "
					+ "INNER JOIN usua.unidadeOrganizacional unid  "
					+ "LEFT JOIN FETCH unid.meioSolicitacao meso "
					+ "WHERE usua.login = :loginUsuario AND unid.indicadorAberturaRa = 1 ";
		
			retorno = (UnidadeOrganizacional) session.createQuery(consulta).setString("loginUsuario",
					loginUsuario).setMaxResults(1).uniqueResult();
			
			/*if (retorno != null){
				Hibernate.initialize(retorno.getMeioSolicitacao());
			}*/
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
    
    /**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 05/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<UnidadeOrganizacional> unidades subordinadas
	 * @throws ErroRepositorioException
	 */
	public Collection<UnidadeOrganizacional> recuperarUnidadesSubordinadasPorUnidadeSuperior(UnidadeOrganizacional unidadeOrganizacional) throws ErroRepositorioException {

		Collection<UnidadeOrganizacional> retorno = new ArrayList();
    	Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		try {
			consulta = "SELECT uo.id " +
					 	"FROM gcom.cadastro.unidade.UnidadeOrganizacional uo " +
					 	"WHERE uo.unidadeSuperior.id = :idUnidadeOrganizacional";
			retornoConsulta = session.createQuery(consulta).
									setInteger("idUnidadeOrganizacional", unidadeOrganizacional.getId()).list();
			
			if (retornoConsulta.size() > 0) {
		    	retorno = new ArrayList();
				UnidadeOrganizacional unid = null;
				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {
					Integer element = (Integer) iter.next();
					unid = new UnidadeOrganizacional();
					unid.setId(element);
					retorno.add(unid);
				}
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
    /**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [FS007] Verificar existência de unidades subordinadas
	 * 
	 * @author Leonardo Regis
	 * @date 05/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return qtde unidades subordinadas
	 * @throws ErroRepositorioException
	 */
	public int consultarTotalUnidadesSubordinadasPorUnidadeSuperior(UnidadeOrganizacional unidadeOrganizacional) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		try {
			consulta = "SELECT count(*) " +
					 	"FROM gcom.cadastro.unidade.UnidadeOrganizacional uo " +
					 	"WHERE uo.unidadeSuperior.id = :idUnidadeOrganizacional";
			return (Integer)session.createQuery(consulta).
						setInteger("idUnidadeOrganizacional", unidadeOrganizacional.getId()).
						setMaxResults(1).
						uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
    /**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 *  Caso exista registro de atendimento que estão na unidade atual informada 
	 *  (existe ocorrência na tabela REGISTRO_ATENDIMENTO com TRAMITE=Id da Unidade Atual 
	 *  e maior TRAM_TMTRAMITE)
	 *  
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return RegistroAtendimento
	 * @throws ControladorException
	 */
    public UnidadeOrganizacional recuperaUnidadeAtualPorRA(RegistroAtendimento registroAtendimento) throws ErroRepositorioException {
	
    	UnidadeOrganizacional retorno = null;
    	Object[] retornoConsulta = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = "SELECT unid.id, unid.descricao, max(tram.dataTramite) " 
					+ "FROM Tramite tram "
					+ "INNER JOIN tram.registroAtendimento rgat  "
					+ "INNER JOIN tram.unidadeOrganizacionalDestino unid  "
					+ "WHERE rgat.id = :idRA "
					+ "GROUP BY unid.id, unid.descricao";
					
			retornoConsulta = (Object[])session.createQuery(consulta).setInteger("idRA", registroAtendimento.getId()).setMaxResults(1).uniqueResult();
			
			if (retornoConsulta != null) {
				retorno = new UnidadeOrganizacional();
				retorno.setId((Integer)retornoConsulta[0]);
				retorno.setDescricao((String)retornoConsulta[1]);
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
    }
    
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * Caso a unidade destino informada não possa receber registros de
	 * atendimento (UNID_ICTRAMITE=2 na tabela UNIDADE_ORGANIZACIONAL com
	 * UNID_ID=Id da unidade destino informada).
	 * 
	 * [FS0013] - Verificar possibilidade de encaminhamento para a unidade
	 * destino
	 * 
	 * @author Ana Maria
	 * @date 03/09/2006
	 * 
	 * @return idUnidadeDestino
	 * @throws ErroRepositorioException
	 */
	public Short verificaTramiteUnidade(Integer idUnidadeDestino) throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select unid.indicadorTramite"
					 + " from UnidadeOrganizacional unid"
					 + " where unid.id = :idUnidadeDestino ";

			retorno = (Short) session.createQuery(consulta)
					.setInteger("idUnidadeDestino", idUnidadeDestino)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */	
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorRA(Collection<Integer> idsRa)
		throws ErroRepositorioException {
		
		Collection<Object[]> retornoConsulta = null;
		Collection<UnidadeOrganizacional> retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			
			consulta = "SELECT DISTINCT unidade.unid_id as idUnidade,unidade.unid_dsunidade as descUnidade "
				+ "FROM cadastro.unidade_organizacional unidade, " 
				+ "atendimentopublico.ra_unidade raUnidade, "
				+ "atendimentopublico.ordem_servico os "
				+ "where os.rgat_id in (:idsRa) "
				+ "and os.orse_cdsituacao in (1,3) "
				+ "and raUnidade.rgat_id = os.rgat_id "
				+ "and raUnidade.attp_id = :idAtendimentoTipo "
				+ "and unidade.unid_id = raUnidade.unid_id";
			
			retornoConsulta = 
				session.createSQLQuery(consulta).
				addScalar("idUnidade",Hibernate.INTEGER).
				addScalar("descUnidade",Hibernate.STRING).
				setParameterList("idsRa", idsRa).
				setInteger("idAtendimentoTipo",AtendimentoRelacaoTipo.ABRIR_REGISTRAR).list();

			if (retornoConsulta.size() > 0) {
				
				retorno = new ArrayList();

				UnidadeOrganizacional unidade = null;
				
				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {
					
					Object[] element = (Object[]) iter.next();
					
					unidade = new UnidadeOrganizacional();
					unidade.setId((Integer) element[0]);
					unidade.setDescricao((String) element[1]);
					
					retorno.add(unidade);
				}
			}			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto,Vivianne Sousa
	 * @date 04/09/2006,13/06/2008
	 */	
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorUnidade(Integer unidadeLotacao)
		throws ErroRepositorioException {
		
		Collection<UnidadeOrganizacional> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			
//			consulta = "SELECT DISTINCT unid "
//				+ "FROM OrdemServicoUnidade osUnidade "
//				+ "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
//				+ "INNER JOIN osUnidade.ordemServico os  "
//				+ "INNER JOIN osUnidade.unidadeOrganizacional unid  "
//				+ "LEFT JOIN os.registroAtendimento ra  "
//				+ "WHERE ra IS NULL "
//				+ "AND os.situacao in (1,3) "
//				+ "AND unid.id = :unidadeLotacao "
//				+ "AND art.id = :idAtendimentoTipo ";

			 // inclusão da coluna unidade atual nas tabelas REGISTRO_ATENDIMENTO e ORDEM_SERVICO
			 // Vivianne Sousa 13/06/2008 analista:Fátima Sampaio
			
			consulta = "SELECT DISTINCT unid "
				+ "FROM OrdemServico os  "
				+ "INNER JOIN os.unidadeAtual unid  "
				+ "LEFT JOIN os.registroAtendimento ra  "
				+ "WHERE ra IS NULL "
				+ "AND os.situacao in (1,3) "
				+ "AND unid.id = :unidadeLotacao ";
			
			retornoConsulta = 
				session.createQuery(consulta).
				setInteger("unidadeLotacao", unidadeLotacao).
				list();

			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}
	
    /**
	 * Pesquisa a Unidade Organizacional do Usuário Logado
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2006
	 * 
	 * @param id
	 * @return UnidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional pesquisarUnidadeUsuario(Integer idUsuario)
		throws ErroRepositorioException {
		
    	UnidadeOrganizacional retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = "SELECT usua.unidadeOrganizacional " 
					+ "FROM Usuario usua "
					+ "WHERE usua.id = :idUsuario";
		
			retorno = (UnidadeOrganizacional) session.createQuery(consulta).setInteger("idUsuario",
					idUsuario.intValue()).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
    
	/**
	 * [UC0375] Manter Unidade Organizacional
	 * 
	 * @author Ana Maria
	 * @date 24/11/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
    public void atualizarUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional)
		throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;
		
		Integer unidadeSuperior = null;
		if(unidadeOrganizacional.getUnidadeSuperior() != null){
			unidadeSuperior = unidadeOrganizacional.getUnidadeSuperior().getId().intValue();	
		}
		
		Integer unidCentralizadora = null;
		if(unidadeOrganizacional.getUnidadeCentralizadora() != null){
			unidCentralizadora = unidadeOrganizacional.getUnidadeCentralizadora().getId().intValue();	 	
		}
		
		String sigla = null;
		if(unidadeOrganizacional.getSigla() != null){
		  sigla	= unidadeOrganizacional.getSigla();
		}

		try {
			update = " update gcom.cadastro.unidade.UnidadeOrganizacional"
				   + " set unid_idsuperior = "+unidadeSuperior	
				   + " ,unid_idcentralizadora = " + unidCentralizadora 
				   + " ,unid_icesgoto = :unidaEsgoto,unid_ictramite = :unidTramite, unid_dsunidade = :descricao"
				   + " ,unid_dssiglaunidade = '" + sigla +"'"
				   + ",empr_id = :idEmpresa, meso_id = :idMeioSolicitacao"
				   + " ,untp_id = :idTipoUnidade, unid_icabertura = :unidAbertura, unid_icuso = :indicadorUso,"
				   + " unid_tmultimaalteracao = :datahoracorrente";
			
			if (unidadeOrganizacional.getUnidadeRepavimentadora() != null
					&& unidadeOrganizacional.getUnidadeRepavimentadora().getId() != null){
				update += ", unid_idrepavimentadora = " + unidadeOrganizacional.getUnidadeRepavimentadora().getId().toString()
						+ " where unid_id = :id ";
			}
			
			update += " where unid_id = :id ";
			
			session.createQuery(update).setInteger("unidaEsgoto", unidadeOrganizacional.getIndicadorEsgoto())
			.setInteger("unidTramite", unidadeOrganizacional.getIndicadorTramite())
			.setString("descricao", unidadeOrganizacional.getDescricao())
			.setInteger("idEmpresa", unidadeOrganizacional.getEmpresa().getId().intValue())
			.setInteger("idMeioSolicitacao", unidadeOrganizacional.getMeioSolicitacao().getId().intValue()).setInteger("idTipoUnidade", unidadeOrganizacional.getUnidadeTipo().getId().intValue())
			.setInteger("unidAbertura", unidadeOrganizacional.getIndicadorAberturaRa())
			.setInteger("indicadorUso", unidadeOrganizacional.getIndicadorUso())
			.setDate("datahoracorrente", new Date()).setInteger("id", unidadeOrganizacional.getId()).executeUpdate();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
			// session.close();
		}    	
    	
    }
 
    /**
	 * Verificar se a unidade organizacional está associada 
	 * a uma divisão de esgoto
	 * 
	 * @author Ana Maria
	 * @date 27/11/2006
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ErroRepositorioException
	 */
    public String verificarUnidadeEsgoto(Integer idUnidade)
		throws ErroRepositorioException {
		
    	String retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = " select div.descricao"
				   	 + " from DivisaoEsgoto div"
				   	 + " inner join div.unidadeOrganizacional unid"
				   	 + " where unid.id = :idUnidade";
		
			retorno = (String) session.createQuery(consulta).setInteger("idUnidade",
					idUnidade.intValue()).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
    
    /**
	 * Verificar se a unidade organizacional está associada 
	 * a uma especificação de solicitação
	 * 
	 * @author Ana Maria
	 * @date 28/11/2006
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ErroRepositorioException
	 */
    public String verificarUnidadeTramitacao(Integer idUnidade)
		throws ErroRepositorioException {
		
    	String retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = " select step.descricao"
				   	 + " from SolicitacaoTipoEspecificacao step"
				   	 + " inner join step.unidadeOrganizacional unid"
				   	 + " where unid.id = :idUnidade";
		
			retorno = (String) session.createQuery(consulta).setInteger("idUnidade",
					idUnidade.intValue()).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	} 
    
    /**
	 * Pesquisar unidade organizacional
	 * 
	 * @author Ana Maria
	 * @date 28/11/2006
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional pesquisarUnidadeOrganizacional(Integer idUnidade)
		throws ErroRepositorioException {
		
    	UnidadeOrganizacional retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = " select unid"
					 + " from UnidadeOrganizacional unid"
					 + " inner join fetch unid.empresa empr"
					 + " left join fetch unid.localidade loca"
					 + " inner join fetch unid.unidadeTipo untp"
					 + " left join fetch unid.meioSolicitacao meio"
					 + " left join fetch unid.gerenciaRegional gere"
					 + " left join fetch unid.unidadeCentralizadora unce"
					 + " left join fetch unid.unidadeSuperior unsup"
				   	 + " where unid.id = :idUnidade";
		
			retorno = (UnidadeOrganizacional) session.createQuery(consulta).setInteger("idUnidade",
					idUnidade.intValue()).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
    
	/**
	 * [UC0374] Filtrar Unidade Organizacional
	 * 
	 * Pesquisa as unidades organizacionais com os condicionais informados
	 * filtroUnidadeOrganizacional
	 * 
	 * @author Ana Maria
	 * @date 30/11/2006
	 * 
	 * @param filtro
	 * @return Collection
	 */
	public Collection pesquisarUnidadeOrganizacionalFiltro(FiltroUnidadeOrganizacional 
			filtroUnidadeOrganizacional, Integer numeroPagina) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		
		Collection unidadeOrganizacionalParametros = filtroUnidadeOrganizacional
									.getParametros();

		try{
		String sql = "";

			sql = " select unid.id," 
				+ " unid.unidadeTipo.descricao, " 
				+ " unid.unidadeTipo.nivel,"
				+ " unid.descricao, " 
				+ " unid.indicadorAberturaRa, " 
				+ " unid.indicadorTramite"
				+ " from UnidadeOrganizacional unid"
				+ " where ";

			Iterator iteratorUnidadeOrganizacional = unidadeOrganizacionalParametros.iterator();
			while (iteratorUnidadeOrganizacional.hasNext()) {
				FiltroParametro filtroParametro = (FiltroParametro) iteratorUnidadeOrganizacional
						.next();

				if (filtroParametro instanceof ParametroSimples) {
					ParametroSimples parametroSimples = ((ParametroSimples) filtroParametro);

						sql = sql + "unid." +parametroSimples.getNomeAtributo() + " = "
								+ parametroSimples.getValor() + " and ";

				}

				if (filtroParametro instanceof ComparacaoTexto) {
					ComparacaoTexto parametroSimples = ((ComparacaoTexto) filtroParametro);

					sql = sql + "unid." +parametroSimples.getNomeAtributo() + " like '" +
						parametroSimples.getValor()+ "' and ";

				}
				
				if (filtroParametro instanceof ComparacaoTextoCompleto) {
					ComparacaoTextoCompleto parametroSimples = ((ComparacaoTextoCompleto) filtroParametro);

					sql = sql + "unid." +parametroSimples.getNomeAtributo() + " like '" +
							parametroSimples.getValor()+ "' and ";

				}				
	
			}

			sql = Util.removerUltimosCaracteres(sql, 4);
			//Ordenação de dados
			if(filtroUnidadeOrganizacional.getCamposOrderBy() != null && filtroUnidadeOrganizacional.getCamposOrderBy().size() > 0){
				Iterator iteOrderBy = filtroUnidadeOrganizacional.getCamposOrderBy().iterator();
				while (iteOrderBy.hasNext()){
					sql = sql + "order by unid."+iteOrderBy.next();	
				}	
			}

		
			retorno = (Collection)session.createQuery(sql)
						.setFirstResult(10 * numeroPagina).setMaxResults(10)
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
	
	public Integer pesquisarUnidadeOrganizacionalFiltroCount(FiltroUnidadeOrganizacional 
			filtroUnidadeOrganizacional) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		
		Collection unidadeOrganizacionalParametros = filtroUnidadeOrganizacional
									.getParametros();

		try{
		String sql = "";

			sql = " select count(unid.id)"
				+ " from UnidadeOrganizacional unid"
				+ " where ";

			Iterator iteratorUnidadeOrganizacional = unidadeOrganizacionalParametros.iterator();
			while (iteratorUnidadeOrganizacional.hasNext()) {
				FiltroParametro filtroParametro = (FiltroParametro) iteratorUnidadeOrganizacional
						.next();

				if (filtroParametro instanceof ParametroSimples) {
					ParametroSimples parametroSimples = ((ParametroSimples) filtroParametro);

						sql = sql + "unid." +parametroSimples.getNomeAtributo() + " = "
								+ parametroSimples.getValor() + " and ";

				}

				if (filtroParametro instanceof ComparacaoTexto) {
					ComparacaoTexto parametroSimples = ((ComparacaoTexto) filtroParametro);

					sql = sql + "unid." +parametroSimples.getNomeAtributo() + " like '" +
							parametroSimples.getValor()+ " %' and ";

				}
				
				if (filtroParametro instanceof ComparacaoTextoCompleto) {
					ComparacaoTextoCompleto parametroSimples = ((ComparacaoTextoCompleto) filtroParametro);

					sql = sql + "unid." +parametroSimples.getNomeAtributo() + " like '" +
							parametroSimples.getValor()+ " ' and ";

				}
				

			}

			sql = Util.removerUltimosCaracteres(sql, 4);
			System.out.println(sql);

		
			retorno = (Integer)session.createQuery(sql).uniqueResult();


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
	 * Pesquisar unidade organizacional por localidade
	 * 
	 * @author Sávio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional pesquisarUnidadeOrganizacionalLocalidade(Integer idLocalidade)
		throws ErroRepositorioException {
		
    	UnidadeOrganizacional retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		
		try {
			consulta = " select unid"
					 + " from UnidadeOrganizacional unid"
					 + " left join fetch unid.localidade loca"
					 + " where loca.id = :idLocalidade";
		
			retorno = (UnidadeOrganizacional) session.createQuery(consulta).setInteger("idLocalidade",
					idLocalidade).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
    
    
    
    /**
     * Pesquisa o id da unidade negocio para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecadação do Mês
     * 
     * @author Raphael Rossiter
     * @date 30/05/2007
     * 
     * @param idLocalidade
     * @throws ErroRepositorioException
     */
    public Integer pesquisarIdUnidadeNegocioParaLocalidade(Integer idLocalidade) throws ErroRepositorioException {

        Integer retorno = null;

        Session session = HibernateUtil.getSession();

        String consulta = "";

        try {

            consulta = "select uneg.id from Localidade loca " 
            			+ "left join loca.unidadeNegocio uneg " 
            			+ "where loca.id = :idLocalidade";

            retorno = (Integer) session.createQuery(consulta).setInteger("idLocalidade", idLocalidade).uniqueResult();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Mariana Victor
	 * @date 14/04/2011
	 */	
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalPorImovel(Integer idImovel)
		throws ErroRepositorioException {
		
		Object retornoConsulta = null;
		UnidadeOrganizacional retorno = new UnidadeOrganizacional();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			
			consulta = " SELECT unidade.unid_id as idUnidade "
				+ " FROM cadastro.unidade_organizacional unidade " 
				+ " INNER JOIN cadastro.localidade localidade ON localidade.loca_id = unidade.loca_id "
				+ " INNER JOIN cadastro.imovel imovel ON imovel.loca_id = localidade.loca_id "
				+ " WHERE imovel.imov_id = :idImovel ";
			
			retornoConsulta = 
				session.createSQLQuery(consulta).
				addScalar("idUnidade",Hibernate.INTEGER).
				setInteger("idImovel",idImovel)
				.setMaxResults(1).uniqueResult();

			if (retornoConsulta != null) {
				retorno = new UnidadeOrganizacional();
				retorno.setId((Integer) retornoConsulta);
			}			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
}
