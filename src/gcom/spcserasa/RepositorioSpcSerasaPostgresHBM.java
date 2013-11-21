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
* Thiago Silva Toscano de Brito
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
package gcom.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
/**
 * Classe criada para sobrescrever(override) os metodos no padrão da base de dados Postgres
 * 
 * @author Arthur Carvalho
 * @date 25/11/2010
 */
public class RepositorioSpcSerasaPostgresHBM extends RepositorioSpcSerasaHBM {

	
	
	/**
	 * Insere RegistroNegativacao
	 * 
	 * @author Arthur Carvalho
	 * @date 25/11/2010
	 * 
	 * @param 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	@Override
	public Integer geraRegistroNegativacao(int idNegativador,
			int saenvio, int idNegativadorComando) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;
		Connection con = null;
		Statement stmt = null;
		int nextValue = 0;
		
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			insert = "insert into cobranca.negativador_movimento ("
				  + " ngmv_id, "                      // 01
				  + " ngmv_cdmovimento, "             // 02
				  + " ngmv_dtenvio, "                 // 03
				  + " ngmv_dtprocessamentoenvio, "    // 04
				  + " ngmv_dtretorno, "               // 05
				  + " ngmv_dtprocessamentoretorno, "  // 06
				  + " ngmv_nnnsaenvio, "              // 07
				  + " ngmv_nnnsaretorno, "            // 08
				  + " ngmv_nnregistrosenvio, "        // 09
				  + " ngmv_nnregistrosretorno, "      // 10
				  + " ngmv_vltotalenvio, "            // 11
				  + " ngmv_tmultimaalteracao, "       // 12
				  + " negt_id, "                      // 13
				  + " ngcm_id ) "                     // 14  
				  + " values ( "
				  + " nextval('cobranca.seq_negativador_movimento') , " // 01
				  + " 1, "                                                  // 02
				  + " now(), "                                              // 03   
				  + " now(), "                                              // 04   
				  + "null,"                                                 // 05   
				  + "null,"                                                 // 06   
				  + (saenvio)+", "                                        // 07
				  + "null,"                                                 // 08   
				  + "null,"                                                 // 09   
				  + "null,"                                                 // 10   
				  + "null,"                                                 // 11   
				  + " now(), "                                              // 12
				  + idNegativador+", "                                      // 13 
				  + idNegativadorComando+") ";                              // 14
			stmt.executeUpdate(insert);
			
			nextValue = this.getNegativadorMovimento();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate RegistroNegativadorMovimento ");
		
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert RegistroNegativadorMovimento ");
		
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
		return nextValue;
	}
	
	/**
	 * Insere geraRegistroNegativacaoReg
	 * 
	 * @author Arthur Carvalho
	 * @date 25/11/2010
	 * 
	 * @param 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	@Override
	public Integer geraRegistroNegativacaoReg(int idNegativador,
			int saenvio, int idNegativadorComando, int idNegativacaoMovimento, StringBuilder registroHeader,
			int quantidadeRegistros, Integer idNegCriterio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;
		Connection con = null;
		Statement stmt = null;
		int nextValId = 0;
		try {
			con = session.connection();
			stmt = con.createStatement();

			Integer idNrTipo = NegativadorRegistroTipo.ID_SERASA_HEADER;    
			if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)){
				idNrTipo = NegativadorRegistroTipo.ID_SPC_HEADER;
			}
			insert = "insert into cobranca.negatd_movimento_reg ("
				  + " nmrg_id, "                             // 01
				  + " ngmv_id, "                             // 02
				  + " nmrg_idreginclusao, "			         // 03
				  + " nrtp_id, "                             // 04
				  + " nmrg_cnregistro, "                     // 05
				  + " nmrg_tmultimaalteracao, "              // 06
				  + " usur_id, "                             // 07
				  + " nmrg_cdexclusaotipo, "                 // 08
				  + " nmrg_icaceito, "                       // 09
				  + " nmrg_iccorrecao, "                     // 10
				  + " nmrg_vldebito, "                       // 11
				  + " cdst_id, "                             // 12
				  + " nmrg_dtsituacaodebito, "               // 13
				  + " imov_id, "                             // 14
				  + " loca_id, "                             // 15
				  + " qdra_id, "                             // 16
				  + " nmrg_cdsetorcomercial, "               // 17
				  + " nmrg_nnquadra, "                       // 18
				  + " iper_id, "                             // 19
				  + " ngct_id, "                             // 20
				  + " clie_id, "                             // 21
				  + " catg_id, "                             // 22
				  + " cpft_id, "                             // 23
				  + " nmrg_nncpf, "                          // 24
				  + " nmrg_nncnpj, "                         // 25
				  + " nmrg_icsitdefinitiva, "                // 26
				  + " nmrg_nnregistro,  "                    // 27
				  + " cbst_id ) "							 // 28
				  + " values ( "
				  + " nextval('cobranca.seq_negatd_movimento_reg'), " // 01
				  + idNegativacaoMovimento+", "                                 // 02
				  + "null, "                                                    // 03
				  + idNrTipo+", "                                               // 04
				  + "'"+registroHeader.toString()+"'"+", "                      // 05
				  + " now(), "                                                  // 06   
				  + "null,"                                                     // 07   
				  + "null,"                                                     // 08   
				  + "null,"                                                     // 09   
				  + "null,"                                                     // 10   
				  + "null,"                                                     // 11   
				  + "null,"                                                     // 12   
				  + "null,"                                                     // 13   
				  + "null,"                                                     // 14   
				  + "null,"                                                     // 15   
				  + "null,"                                                     // 16   
				  + "null,"                                                     // 17   
				  + "null,"                                                     // 18
				  + "null,"                                                     // 19
				  + idNegCriterio+", "                                          // 20
				  + "null,"                                                     // 21
				  + "null,"                                                     // 22
				  + "null,"                                                     // 23
				  + "null,"                                                     // 24
				  + "null,"                                                     // 25
				  + "2, "                                                       // 26
				  + (quantidadeRegistros)	                                    // 27   
				  + ",null )";													// 28
			stmt.executeUpdate(insert);
			//System.out.print("1 - INSERINDO geraRegistroNegativacaoReg!! ");
			nextValId = this.getNextNegativadorMovimentoReg();
		} catch (HibernateException e) {
			nextValId = 0;
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate geraRegistroNegativacaoReg ");
		} catch (SQLException e) {
			nextValId = 0;
			throw new ErroRepositorioException(e, "Erro no Insert geraRegistroNegativacaoReg ");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
		return nextValId;
	}
	
	
	/**
	 *  geraRegistroNegativacaoMovimentoRegItem
	 * 
	 * @author Arthur Carvalho
	 * @date 25/11/2010
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */	
	@Override
	public void geraRegistroNegativacaoMovimentoRegItem(int idDebSit,
			BigDecimal valorDoc, int idDetalheRegSPC, int idDocTipo, Integer idGuiaPagamento,
			Integer idConta) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;
		Connection con = null;
		Statement stmt = null;
		try {
			con = session.connection();
			stmt = con.createStatement();
			
			insert = "insert into cobranca.negatd_mov_reg_item ("
   				  +" nmri_id , "                                // 01
				  +" nmrg_id , "                                // 02
				  +" dotp_id , "                                // 03
				  +" dbac_id , "                                // 04
				  +" gpag_id , "                                // 05
				  +" cnta_id , "                                // 06
				  +" cdst_id , "                                // 07 
				  +" nmri_vldebito , "                          // 08
				  +" nmri_dtsituacaodebito , "                  // 09
				  +" cdst_idaposexclusao , "                    // 10 
				  +" nmri_dtsitdebaposexclusao , "              // 11
				  +" nmri_icsitdefinitiva," 
				  +" nmri_tmultimaalteracao) "                   // 12 
				  + " values ( "
				  + " nextval('cobranca.seq_negatd_mov_reg_item'), " // 01
				  + idDetalheRegSPC+", "                                             // 02
				  + idDocTipo+", "                                                   // 03
				  + "null, "                                                         // 04
				  + idGuiaPagamento+", "                                             // 05
				  + idConta+", "                                                     // 06
				  + idDebSit+", "                                                    // 07
				  + valorDoc+", "                                                    // 08
				  + "  now(), "                                                       // 09
				  + "null, "                                                         // 10
				  + "null, "                                                         // 11
				  + "2," 
				  + "  now()) ";                                                           // 12

			stmt.executeUpdate(insert);
			//System.out.print("1 - INSERINDO geraRegistroNegativacaoMovimentoRegItem!! ");
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate geraRegistroNegativacaoMovimentoRegItem ");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert geraRegistroNegativacaoMovimentoRegItem ");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
			
		}
	}
	
	/**
	 * Obtem geraRegistroNegativacaoRegTrailler
	 * 
	 * @author Arthur Carvalho
	 * @date 25/11/2010
	 * 
	 * @param int idNegativador, int idNegativacaoMovimento, 
			StringBuilder registro, int quantidadeRegistros, int idNegCriterio
	 * @return void
	 * @throws ErroRepositorioException
	 */	
	@Override
	public void geraRegistroNegativacaoRegTrailler(int idNegativador, int idNegativacaoMovimento, 
			StringBuilder registro, int quantidadeRegistros, Integer idNegCriterio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;
		Connection con = null;
		Statement stmt = null;
		try {
			con = session.connection();
			stmt = con.createStatement();
			Integer idNrTipo = NegativadorRegistroTipo.ID_SERASA_TRAILLER;    
			if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)){
				idNrTipo = NegativadorRegistroTipo.ID_SPC_TRAILLER;
			}
			insert = "insert into cobranca.negatd_movimento_reg ("
				  + " nmrg_id, "                             // 01
				  + " ngmv_id, "                             // 02
				  + " nmrg_idreginclusao, "			         // 03
				  + " nrtp_id, "                             // 04
				  + " nmrg_cnregistro, "                     // 05
				  + " nmrg_tmultimaalteracao, "              // 06
				  + " usur_id, "                             // 07
				  + " nmrg_cdexclusaotipo, "                 // 08
				  + " nmrg_icaceito, "                       // 09
				  + " nmrg_iccorrecao, "                     // 10
				  + " nmrg_vldebito, "                       // 11
				  + " cdst_id, "                             // 12
				  + " nmrg_dtsituacaodebito, "               // 13
				  + " imov_id, "                             // 14
				  + " loca_id, "                             // 15
				  + " qdra_id, "                             // 16
				  + " nmrg_cdsetorcomercial, "               // 17
				  + " nmrg_nnquadra, "                       // 18
				  + " iper_id, "                             // 19
				  + " ngct_id, "                             // 20
				  + " clie_id, "                             // 21
				  + " catg_id, "                             // 22
				  + " cpft_id, "                             // 23
				  + " nmrg_nncpf, "                          // 24
				  + " nmrg_nncnpj, "                         // 25
				  + " nemt_id, "                             // 26
				  + " nmrg_icsitdefinitiva, "                // 27
				  + " nmrg_nnregistro, "                     // 28
				  + " cbst_id ) "							 // 29
				  + " values ( "
				  + " nextval('cobranca.seq_negatd_movimento_reg'), " // 01
				  + idNegativacaoMovimento+", "                                 // 02
				  + "null, "                                                    // 03
				  + idNrTipo+", "                                               // 04
				  + "'"+registro.toString()+"'"+", "                            // 05
				  + "  now(), "                                                  // 06   
				  + "null,"                                                     // 07   
				  + "null,"                                                     // 08   
				  + "null,"                                                        // 09   
				  + "null,"                                                        // 10   
				  + "null,"                                                     // 11   
				  + "null,"                                                     // 12   
				  + "null,"                                                     // 13   
				  + "null,"                                                     // 14   
				  + "null,"                                                     // 15   
				  + "null,"                                                     // 16   
				  + "null,"                                                     // 17   
				  + "null,"                                                     // 18
				  + "null,"                                                     // 19
				  + idNegCriterio+", "                                          // 20
				  + "null,"                                                     // 21
				  + "null,"                                                     // 22
				  + "null,"                                                     // 23
				  + "null,"                                                     // 24
				  + "null,"                                                     // 25
				  + "null,"                                                     // 26
				  + "2, "                                                       // 27
				  + quantidadeRegistros+","                                     // 28
				  + "null )";													// 29
			stmt.executeUpdate(insert);
			//System.out.print("1 - INSERINDO geraRegistroNegativacaoRegTrailler!! ");
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate geraRegistroNegativacaoRegTrailler ");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert geraRegistroNegativacaoRegTrailler ");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * [UC0473] Consultar Dados Complementares do Imóvel
	 *
	 * @author Arthur Carvalho
	 * @date 29/11/2010
	 */
	@Override
	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel)
		throws ErroRepositorioException {
	
		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String sql = " select "
					+ "       	clie.clie_nmcliente as nomeCliente, "
					+ "       	case when nmrg.nmrg_cdexclusaotipo is not null "
					+ "        	  then case when ngim_dtexclusao - ngmv_dtprocessamentoenvio > ngcn_nnprazoinclusao"  
					+ "            		  then 1 else 2 end "
					+ "        	  else case when current_date - ngmv_dtprocessamentoenvio > ngcn_nnprazoinclusao"  
					+ "            		  then 1 else 2 end" 
					+ "	  end as indicadorNegativacaoConfirmada,"  
					+ "	count(*) as qtdeInclusoes"
					+ " from   	cobranca.negatd_movimento_reg	nmrg"
					+ " inner join	cobranca.negativador_movimento     	ngmv on ngmv.ngmv_id=nmrg.ngmv_id"
					+ " inner join 	cobranca.negativacao_imoveis     	ngim on ngim.ngcm_id=ngmv.ngcm_id  and ngim.imov_id=nmrg.imov_id"
					+ " inner join	cobranca.negativador_contrato      	ngcn on ngcn.negt_id=ngmv.negt_id  and (ngcn_dtcontratoencerramento is null or ngcn_dtcontratofim >= current_date)"
					+ " inner join	cobranca.negativador               	negt on negt.negt_id=ngmv.negt_id"  
					+ " inner join	cadastro.cliente                   	clie on clie.clie_id=negt.clie_id" 
					+ " where	nmrg.imov_id= :idImovel "
					+ " and 	nmrg.nmrg_icaceito= :indicadorAceito "
					+ " and	nmrg_idreginclusao is null" 
					+ " group by 1,2"
					+ " order by 1,2";
			
			retorno = (Collection)session.createSQLQuery(sql)
			.addScalar("nomeCliente", Hibernate.STRING)
			.addScalar("indicadorNegativacaoConfirmada", Hibernate.INTEGER)
			.addScalar("qtdeInclusoes", Hibernate.INTEGER)
			.setInteger("idImovel", idImovel)
			.setShort("indicadorAceito", ConstantesSistema.SIM).list();
			
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
	 * Verifica ocorrencia debito cobrado conta imovel.
	 * 
	 * @author Arthur Carvalho
	 * @date 08/12/2010
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	@Override
	public Integer verificaCartaAvisoParcelamento(int idImovel,int numeroDiasAtrasoRecebCartaParcel)
			throws ErroRepositorioException {

		Integer retorno;
		Session session = HibernateUtil.getSession();
		try {
			
			String consulta = "select count(a.cbdo_id) as total from cobranca.cobranca_documento a"
				+ " inner join cobranca.documento_tipo b on a.dotp_id = b.dotp_id"
				+ " inner join cadastro.imovel c on a.imov_id = c.imov_id"
				+ " where c.imov_id = :idImovel and a.cbdo_tmemissao < (SELECT  now() - INTERVAL '" + numeroDiasAtrasoRecebCartaParcel + " DAYS') "
				+ " and b.dotp_id = 26";
				
			retorno =(Integer) session.createSQLQuery(consulta)
				.addScalar("total" , Hibernate.INTEGER)
				.setInteger("idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaCartaAvisoParcelamento");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	
	/**
	 *
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Thiago Toscano,Vivianne Sousa, Ivan Sergio
	 * @date 07/01/2008,30/10/2009
	 * @alteracao: RM3755 - Adicionado o LAST_ID e LEST_ID;
	 * 
	 */
//	OVERRIDE - Metodo sobrescrito na classe RepositoripSpcSeresaPostgresHBM
	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(Integer idRota)
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String sql = "select "
               + "       ngmv.negt_id as idNegativador,"
               + "       ngmv.ngcm_id as idNegativadorComando,"
               + "       ngmv.ngmv_dtprocessamentoenvio as dataProcessamento,"
               + "       case when nmrg.nmrg_cdexclusaotipo is not null then"
               + "         case when (ngim_dtexclusao - ngmv_dtprocessamentoenvio) > 30 then 1"
               + "         else 2 end "
               + "       else "
               + "         case when (current_date - ngmv_dtprocessamentoenvio) > 30 then 1"
               + "         else 2 end "
               + "       end as confirmada,"
               + "       nmrg.cdst_id as idCobrancaDebitoSituacao, "
               + "       rota.cbgr_id as idCobrancaGrupo, "
               + "       loca.greg_id as idGerenciaRegional, "
               + "       loca.uneg_id as idUnidadeNegocio, "
               + "       loca.loca_cdelo as codigoElo, "
               + "       nmrg.loca_id as idLocalidade, "
               + "       qdra.stcm_id as idSetorComercil, "
               + "       nmrg.qdra_id as idQuadra, "
               + "       nmrg.nmrg_cdsetorcomercial as codigoSetorComercial, "
               + "       nmrg.nmrg_nnquadra as numeroQuadra,"
               + "       nmrg.iper_id as idImovelPerfil, "
               + "       nmrg.catg_id as idCategoria, "
               + "       clie.cltp_id as idClienteTipo, "
               + "       cltp.epod_id as idEsferaPoder,"
               + "       count(distinct(nmrg.nmrg_id)) as qtdeNegativadorMovimentoReg, "
               + "       sum(nmri_vldebito) as valorDebito,"
//               + "       sum(case when nmri.cdst_id=1 then nmri_vldebito else 0 end) as valorPendente,"
//               + "       sum(case when nmri.cdst_id=2 then nmri_vldebito else 0 end) as valorPago,"
//               + "       sum(case when nmri.cdst_id=3 then nmri_vldebito else 0 end) as valorParcelado,"
//               + "       sum(case when nmri.cdst_id=4 then nmri_vldebito else 0 end) as valorCancelado"
               //Vivianne Sousa - 15/03/2010 - analista:Fatima Sampaio
               + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
               + "        case when nmri.cdst_id=1 then nmri_vldebito else 0 end "
               + "    else "
               + "         case when nmri.cdst_idaposexclusao=1 then nmri_vldebito else 0 end "
               + "    end) as valorPendente, "
               + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
               + "         case when nmri.cdst_id=2 then nmri_vldebito else 0 end "
               + "    else "
               + "         case when nmri.cdst_idaposexclusao=2 then nmri_vldebito else 0 end "
               + "    end) as valorPago, "
               + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
               + "         case when nmri.cdst_id=3 then nmri_vldebito else 0 end "
               + "    else "
               + "        case when nmri.cdst_idaposexclusao=3 then nmri_vldebito else 0 end "
               + "    end) as valorParcelado, "
               + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
               + "        case when nmri.cdst_id=4 then nmri_vldebito else 0 end "
               + "    else "
               + "        case when nmri.cdst_idaposexclusao=4 then nmri_vldebito else 0 end "
               + "    end) as valorCancelado, "
               + "    nmrg.last_id as idSituacaoAgua, " // 24
               + "    nmrg.lest_id as idSituacaoEsgoto " // 25

               + " from"
               + "             cobranca.negatd_movimento_reg      nmrg"
               + "  inner join cobranca.negativador_movimento          ngmv on ngmv.ngmv_id=nmrg.ngmv_id"
               + "  inner join cobranca.negativacao_imoveis            ngim on ngim.ngcm_id=ngmv.ngcm_id and ngim.imov_id=nmrg.imov_id"
               + "  inner join cadastro.quadra                         qdra on qdra.qdra_id=nmrg.qdra_id"
               + "  inner join micromedicao.rota                       rota on rota.rota_id=qdra.rota_id"
               + "  inner join cadastro.localidade                     loca on loca.loca_id=nmrg.loca_id"
               + "  inner join cadastro.cliente                        clie on clie.clie_id=nmrg.clie_id"
               + "  inner join cadastro.cliente_tipo                   cltp on cltp.cltp_id=clie.cltp_id"
               + "  inner join cobranca.negatd_mov_reg_item nmri on nmri.nmrg_id=nmrg.nmrg_id"
               + " where"
               + "     nmrg.nmrg_icaceito=1"
               + " and ngmv.ngmv_cdmovimento=1 "
               + " and nmrg.imov_id is not null"
               + " and rota.rota_id = :idRota"
               + " group by"
               + " ngmv.negt_id,ngmv.ngcm_id,ngmv.ngmv_dtprocessamentoenvio,"
               + "       case when nmrg.nmrg_cdexclusaotipo is not null then"
               + "         case when (ngim_dtexclusao - ngmv_dtprocessamentoenvio) > 30 then 1"
               + "         else 2 end "
               + "       else "
               + "         case when (current_date - ngmv_dtprocessamentoenvio) > 30 then 1"
               + "         else 2 end "
               + "       end, "
               + " nmrg.cdst_id,rota.cbgr_id,loca.greg_id,loca.uneg_id,loca.loca_cdelo,nmrg.loca_id,qdra.stcm_id,nmrg.qdra_id,"
               + " nmrg.nmrg_cdsetorcomercial,nmrg.nmrg_nnquadra,nmrg.iper_id,nmrg.catg_id,clie.cltp_id,cltp.epod_id,nmrg.last_id, nmrg.lest_id";
			
			retorno = (List) session.createSQLQuery(sql)
				.addScalar("idNegativador", Hibernate.INTEGER)
				.addScalar("idNegativadorComando", Hibernate.INTEGER)
				.addScalar("dataProcessamento", Hibernate.DATE)
				.addScalar("confirmada", Hibernate.INTEGER)  
				.addScalar("idCobrancaDebitoSituacao", Hibernate.INTEGER) 
				.addScalar("idCobrancaGrupo", Hibernate.INTEGER) 
				.addScalar("idGerenciaRegional", Hibernate.INTEGER) 
				.addScalar("idUnidadeNegocio", Hibernate.INTEGER) 
				.addScalar("codigoElo", Hibernate.INTEGER) 
				.addScalar("idLocalidade", Hibernate.INTEGER) 
				.addScalar("idSetorComercil", Hibernate.INTEGER) 
				.addScalar("idQuadra", Hibernate.INTEGER) 
				.addScalar("codigoSetorComercial", Hibernate.INTEGER) 
				.addScalar("numeroQuadra", Hibernate.INTEGER)
				.addScalar("idImovelPerfil", Hibernate.INTEGER) 
				.addScalar("idCategoria", Hibernate.INTEGER) 
				.addScalar("idClienteTipo", Hibernate.INTEGER) 
				.addScalar("idEsferaPoder", Hibernate.INTEGER)
				.addScalar("qtdeNegativadorMovimentoReg", Hibernate.INTEGER) 
				.addScalar("valorDebito", Hibernate.BIG_DECIMAL)
				.addScalar("valorPendente", Hibernate.BIG_DECIMAL)
				.addScalar("valorPago", Hibernate.BIG_DECIMAL)
				.addScalar("valorParcelado", Hibernate.BIG_DECIMAL)
				.addScalar("valorCancelado", Hibernate.BIG_DECIMAL)
				.addScalar("idSituacaoAgua", Hibernate.INTEGER) 
				.addScalar("idSituacaoEsgoto", Hibernate.INTEGER)
				.setInteger("idRota",idRota)
				.list();
			//alterada por Vivianne Sousa,30/10/2009
			//query feita por Fatiam Sampaio e Francisco
//			String hql = " select nmr"
//					 + " from gcom.cobranca.NegativadorMovimentoReg nmr"
//					 + " left join fetch nmr.cobrancaDebitoSituacao as cds "					
//					 + " left join fetch nmr.quadra as quad "					
//					 + " left join fetch quad.rota as rot "
//					 + " left join fetch rot.cobrancaGrupo as cobGrup "
//					 + " left join fetch nmr.localidade as l "
//					 + " left join fetch l.gerenciaRegional as gr "
//					 + " left join fetch l.unidadeNegocio as un "
//					 + " left join fetch l.localidade as lelo "
//					 + " left join fetch quad.setorComercial as sc "
//					 + " left join fetch nmr.imovelPerfil as ip "
//					 + " left join fetch nmr.categoria as c "
//					 + " left join fetch nmr.cliente as clie "
//					 + " left join fetch clie.clienteTipo as ct "
//					 + " left join fetch ct.esferaPoder as ep "
//					 + " left join fetch nmr.negativadorMovimento as nm " 
//					 + " left join fetch nm.negativacaoComando as nc " 
//					 + " left join fetch nm.negativador as n " 	
////					 + " left join fetch nmr.parcelamento as parc " 	
//					 + " where " 
//					 + " nm.codigoMovimento = 1 "
//					 + " and nmr.imovel is not null  "  	
//					 + " and nmr.indicadorAceito = 1 "
//					 + " and rot.id = " + idRota
//                	 + " order by nm.id,nmr.id ";
//
//			retorno = (List) session.createQuery(hql).list();
			
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
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Arthur Carvalho
	 * @date 08/12/2010
	 */
	@Override
	public Collection pesquisarNegativadorMovimentoReg(Integer idLocalidade, 
			int quantidadeInicio, int quantidadeMaxima)throws ErroRepositorioException {
	
		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String consulta = " select "
				+ " nmrg.nmrg_id as idNegativadorMovimentoReg," 
				+ " nmrg.imov_id as idImovel, "
				+ " ngim.ngim_id as idNegativacaoImoveis,"
				+ " ngmv.negt_id as idNegativador,"
				+ " nmrg.clie_id as idClienteNegativadorMovimentoReg,"
				+ " ngcn.ngcn_nnprazoinclusao as numeroPrazoInclusao,"
				+ " ngmv.ngmv_dtprocessamentoenvio as dataProcessamentoEnvio,"
				+ " nmrg.nmrg_cdexclusaotipo as codigoExclusaoTipo," //7
				+ " ngim.ngim_dtexclusao as dataExclusao" //8
				+ " from          cobranca.negatd_movimento_reg   nmrg"
				+ " inner join    cobranca.negativador_movimento       ngmv on ngmv.ngmv_id=nmrg.ngmv_id and ngmv_cdmovimento=1"
				+ " inner join    cobranca.negativacao_imoveis         ngim on ngim.ngcm_id=ngmv.ngcm_id and ngim.imov_id=nmrg.imov_id and ngim_dtconfirmacao is null"
				+ " inner join    cobranca.negativador_contrato        ngcn on ngcn.negt_id=ngmv.negt_id and (ngcn_dtcontratoencerramento is null or ngcn_dtcontratofim >= current_date)"
				+ " where"
				+ "      	nmrg.imov_id is not null"
				+ " and 	nmrg.nmrg_icaceito=1"
				+ " and 	(  (nmrg.nmrg_cdexclusaotipo is null and  current_date - ngmv.ngmv_dtprocessamentoenvio > ngcn.ngcn_nnprazoinclusao)"
				+ " 	or (nmrg.nmrg_cdexclusaotipo is not null and  ngim.ngim_dtexclusao - ngmv.ngmv_dtprocessamentoenvio>ngcn.ngcn_nnprazoinclusao))"
				+ " and loca_id = " + idLocalidade;
			
			long t1 = System.currentTimeMillis();
			retorno = (Collection)session.createSQLQuery(consulta)
			.addScalar("idNegativadorMovimentoReg", Hibernate.INTEGER)
			.addScalar("idImovel", Hibernate.INTEGER)
			.addScalar("idNegativacaoImoveis", Hibernate.INTEGER)
			.addScalar("idNegativador", Hibernate.INTEGER)
			.addScalar("idClienteNegativadorMovimentoReg", Hibernate.INTEGER)
			.addScalar("numeroPrazoInclusao", Hibernate.SHORT)
			.addScalar("dataProcessamentoEnvio", Hibernate.DATE)
			.addScalar("codigoExclusaoTipo" , Hibernate.INTEGER)
			.addScalar("dataExclusao", Hibernate.DATE)
			.setMaxResults(quantidadeMaxima).list();
			long t2 = System.currentTimeMillis();
			System.out.println("[UC1005]pesquisarNegativadorMovimentoReg " + ( t2 - t1));
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public List pesquisarImoveisParaNegativacao(Integer idRota, Integer idComando)throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		try {
			sql = " select imov.imov_id as idImovel " 
			+ " from cadastro.imovel imov " 
			+ " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
			+ " where imov.iper_id <> 4 " 
//			+ " and qdra.qdpf_id <> 2 " 
			+ " and imov.imov_icexclusao = " 
			+ ConstantesSistema.INDICADOR_IMOVEL_ATIVO 
			+ " and qdra.rota_id = " + idRota
			+ " and not exists ( " +
				" SELECT ngsm.imov_id FROM cobranca.negatd_result_simulacao ngsm " +
				" WHERE ngcm_id = " + idComando + 
				" AND ngsm.imov_id = imov.imov_id  limit  1 " +
				" ) ";

//			switch (tipoCondicao) {
//			case 1:
//				
//				sql = " select imov.imov_id as idImovel " 
//					+ " from cadastro.imovel as imov " 
//					+ " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
//					+ " inner join micromedicao.rota rota on(qdra.rota_id = rota.rota_id)"
//					+ " where imov.iper_id <> 4 and qdra.qdpf_id <> 2 and " 
//					+ " rota.cbgr_id in (select nccg.cbgr_id "
//					+ "                  from cobranca.negativacao_criterio as ngct "
//					+ " 			     inner join cobranca.negativ_crit_cobr_grupo as nccg on(ngct.ngct_id = nccg.ngct_id) "
//					+ " 			     where ngct.ngct_id  = " + nCriterio.getId() +" ) ";
//				break;
//			case 2:
//				sql = " select imov.imov_id as idImovel "
//				    + " from cadastro.imovel as imov "
//				    + " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
//				    + " inner join cadastro.localidade loca on(imov.loca_id = loca.loca_id)"
//				    + " where imov.iper_id <> 4 and qdra.qdpf_id <> 2 and "
//				    + " loca.greg_id in (select  ncgr.greg_id"
//					+ "             	 from cobranca.negativacao_criterio as ngct "
//					+ "             	 inner join cobranca.negativ_crit_ger_reg as ncgr on(ngct.ngct_id = ncgr.ngct_id)" 		 
//					+ "					 where ngct.ngct_id  = " + nCriterio.getId() +" ) ";
//				break;
//			case 3:
//				sql = " select imov.imov_id as idImovel "
//					+ " from cadastro.imovel as imov "
//					+ " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
//					+ " inner join cadastro.localidade loca on(imov.loca_id = loca.loca_id)"
//					+ " where imov.iper_id <> 4 and qdra.qdpf_id <> 2 and "
//					+ " loca.uneg_id  in (select ncun.uneg_id "
//					+ " 	              from cobranca.negativacao_criterio as ngct "
//					+ " 	              inner join cobranca.negativ_crit_und_neg as ncun on(ngct.ngct_id = ncun.ngct_id) "
//					+ " 	              where ngct.ngct_id  =" + nCriterio.getId() +" ) ";
//				break;				
//			case 4:
//				sql = " select imov.imov_id as idImovel "
//					+ " from cadastro.imovel as imov "
//					+ " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
//					+ " where imov.iper_id <> 4 and qdra.qdpf_id <> 2 and "
//					+ " imov.loca_id in (select ncep.loca_id"
//					+ "                  from cobranca.negativacao_criterio as ngct "
//					+ "                  inner join cobranca.negativ_crit_elo as ncep on(ngct.ngct_id = ncep.ngct_id) "
//					+ "                  where ngct.ngct_id  =" + nCriterio.getId() +" ) ";
//				break;
//			case 5:
//				sql = " select imov.imov_id as idImovel " 
//					+ " from cadastro.imovel as imov "
//					+ " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
//					+ " inner join cadastro.setor_comercial stcm on(imov.stcm_id = stcm.stcm_id)"	
//					+ " where imov.iper_id <> 4 and qdra.qdpf_id <> 2 and "
//					+ " imov.loca_id  between "+nCriterio.getLocalidadeInicial().getId()+" and " +nCriterio.getLocalidadeFinal().getId();					
//				    if(nCriterio.getCodigoSetorComercialInicial() != null && 
//				    		nCriterio.getCodigoSetorComercialFinal() != null){
//				      sql = sql + " and stcm.stcm_cdsetorcomercial  between "+nCriterio.getCodigoSetorComercialInicial()+"" 
//				      		    + " and "+nCriterio.getCodigoSetorComercialFinal();
//				    }				   
//			    break;
//              default:
//            	sql = " select i.imov_id as idImovel " 
//					+ " from cadastro.imovel as i "
//					+ " inner join cadastro.localidade loca on(i.loca_id = loca.loca_id)"
//					+ " inner join cadastro.quadra q on(i.qdra_id = q.qdra_id)"				
//					+ " where i.iper_id <> 4 and q.qdpf_id <> 2"; //i.last_id = 4 and
//			   		if(nCriterio.getQuantidadeMaximaInclusoes() == null || nCriterio.getQuantidadeMaximaInclusoes().equals("")){
//			   			sql = sql + " and i.loca_id ="+idLocalidade;
//			   		} 		
//
//			} 
						
			retorno = (List) session.createSQLQuery(sql)
					  .addScalar("idImovel" , Hibernate.INTEGER).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public boolean verificarNegativacaoDoClienteImovel(
			Integer idCliente, Integer idImovel) throws ErroRepositorioException {
		
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = 
				"SELECT nmrg_id FROM cobranca.negatd_movimento_reg " +
				"WHERE   " +
					"clie_id = :idCliente " +
					"AND imov_id = :idImovel " +
					"AND (nmrg_icaceito = 1 OR nmrg_icaceito IS null) " +
					"AND nmrg_cdexclusaotipo IS null " +
				" limit  1";
			
			SQLQuery q = session.createSQLQuery(consulta);
			q.addScalar("nmrg_id", Hibernate.INTEGER);
			q.setInteger("idCliente", idCliente);
			q.setInteger("idImovel", idImovel);
			Integer resultado = (Integer) q.uniqueResult();
			
			if (resultado != null) retorno = true;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
}
