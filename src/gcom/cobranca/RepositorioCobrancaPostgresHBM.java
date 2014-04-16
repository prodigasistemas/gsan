package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cobranca.bean.DadosPesquisaCobrancaDocumentoHelper;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 * Classe criada para sobrescrever(override) os metodos seguindo o padrão da base de dados Postgres
 * 
 * @author Arthur Carvalho
 * @since 26/11/2010
 */
public class RepositorioCobrancaPostgresHBM extends RepositorioCobrancaHBM {

	@Override
	public Collection pesquisarDadosCobrancaDocumentoAgrupadoPorDataPrevista(
			int idCobrancaAtividadeAcaoCronograma)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {
			
			//CRC4529 - query alterada por Vivianne Sousa - 30/08/2010
			
			consulta = " select"
					+ " (select osfs.fzst_id"
					+ " from atendimentopublico.ordem_servico_fisc_sit osfs"
					+ " inner join atendimentopublico.ordem_servico orse on osfs.orse_id = orse.orse_id"
					+ " where orse.cbdo_id  = cbdo.cbdo_id "
					+ " order by osfs_dtfiscalizacaosituacao limit and <= 1 ) as idFiscalizacaoSit,"//0
					+ " cbdo.cbdo_icantesapos as indAntesApos,"//1
					+ " cbdo.cbdo_icacimalimite as indAcimaLimite,"//2
					+ " casit.cast_id as idCobrancaAcaoSit,"//3
					+ " cdst.cdst_id as idCobrancaDebitoSit,"//4
					+ " catg.catg_id as idCategoria,"//5
					+ " epod.epod_id as idEsferaPoder,"//6
					+ " cbct.cbct_id as idCobrancaCriterio,"//7
					+ " greg.greg_id as idGerencia,"//8
					+ " loca.loca_id as idLocalidade,"//9
					+ " stcm.stcm_id as idSetor,"//10
					+ " rota.rota_id as idRota,"//11
					+ " qdra.qdra_id as idQuadra,"//12
					+ " qdra.qdra_nnquadra as numeroQuadra,"//13
					+ " stcm.stcm_cdsetorcomercial as codigoSetor,"//14
					+ " iper.iper_id as idPerfilImovel,"//15
					+ " coalesce(last.last_id,imov.last_id) as idLigacaoAguaSit,"//16
					+ " coalesce(lest.lest_id,imov.lest_id) as idLigacaoEsgotoSit,"//17
					+ " empr.empr_id as idEmpresa,"//18
					+ " amen.amen_id as idAtendMotivoEnc,"//19
					+ " loca.uneg_id as idUnidadeNegocio,"//20
					+ " cbdo.demf_id as idDocEmissaoForma,"//21
					+ " count(cbdo.cbdo_id) as qtdeCobrancaDocumento,"//22
					+ " sum(cbdo.cbdo_vldocumento) as valorCobrancaDoc"//23
					+ " from"
					+ " cobranca.cobranca_documento cbdo"
					+ " left outer join cadastro.esfera_poder epod 				on cbdo.epod_id=epod.epod_id"
					+ " left outer join cobranca.cobranca_acao_situacao casit 			on cbdo.cast_id=casit.cast_id"
					+ " left outer join cobranca.cobranca_debito_situacao cdst 			on cbdo.cdst_id=cdst.cdst_id"
					+ " left outer join cadastro.categoria catg 				on cbdo.catg_id=catg.catg_id"
					+ " left outer join cobranca.cobranca_acao_ativ_crg caac 		on cbdo.caac_id=caac.caac_id"
					+ " left outer join cobranca.cobranca_acao_cronograma cbcr 			on caac.cbcr_id=cbcr.cbcr_id"
					+ " left outer join cobranca.cobranca_acao cbac 				on cbcr.cbac_id=cbac.cbac_id"
					+ " left outer join cobranca.cobranca_grupo_crg_mes cbcm 		on cbcr.cbcm_id=cbcm.cbcm_id"
					+ " left outer join cobranca.cobranca_grupo cbgr 				on cbcm.cbgr_id=cbgr.cbgr_id"
					+ " left outer join cobranca.cobranca_criterio cbct 			on cbdo.cbct_id=cbct.cbct_id"
					+ " left outer join cadastro.imovel imov 					on cbdo.imov_id=imov.imov_id"
					+ " left outer join cadastro.localidade loca 				on imov.loca_id=loca.loca_id"
					+ " left outer join cadastro.gerencia_regional greg 			on loca.greg_id=greg.greg_id"
					+ " left outer join cadastro.setor_comercial stcm 				on imov.stcm_id=stcm.stcm_id"
					+ " left outer join cadastro.quadra qdra 					on imov.qdra_id=qdra.qdra_id"
					+ " left outer join micromedicao.rota rota 					on qdra.rota_id=rota.rota_id"
					+ " left outer join cadastro.imovel_perfil iper 				on imov.iper_id=iper.iper_id"
					+ " left outer join atendimentopublico.ligacao_esgoto_situacao lest  	on cbdo.lest_id=lest.lest_id"
					+ " left outer join atendimentopublico.ligacao_agua_situacao last 		on cbdo.last_id=last.last_id"
					+ " left outer join cadastro.empresa empr 					on cbdo.empr_id=empr.empr_id"
					+ " left outer join atendimentopublico.atend_motivo_encmt amen on cbdo.amen_id=amen.amen_id"
					+ " where"
					+ " caac.caac_id= :idCobrancaAtividadeAcaoCronograma"
					+ " group by"
					+ " idFiscalizacaoSit,cbdo.cbdo_icantesapos ,cbdo.cbdo_icacimalimite ,casit.cast_id ,cdst.cdst_id ,catg.catg_id ,"
					+ " cbcm.cbcm_amreferencia ,cbcr.cbcr_id,caac.caac_dtprevista,cbct.cbct_id ,cbgr.cbgr_id ,greg.greg_id ,"
					+ " loca.loca_id ,stcm.stcm_id ,rota.rota_id ,qdra.qdra_id ,qdra.qdra_nnquadra ,stcm.stcm_cdsetorcomercial,iper.iper_id ,"
					+ " last.last_id ,lest.lest_id ,epod.epod_id ,cbac.cbac_id ,empr.empr_id ,amen.amen_id ,loca.uneg_id ,imov.last_id ,imov.lest_id, cbdo.demf_id"
					+ " order by"
					+ " idFiscalizacaoSit,cbdo.cbdo_icantesapos,cbdo.cbdo_icacimalimite,casit.cast_id,cdst.cdst_id,"
					+ " catg.catg_id,epod.epod_id,greg.greg_id,loca.uneg_id,loca.loca_id,stcm.stcm_id,rota.rota_id,"
					+ " qdra.qdra_id,qdra.qdra_nnquadra,stcm.stcm_cdsetorcomercial,iper.iper_id,last.last_id,lest.lest_id"; 

			retorno = session.createSQLQuery(consulta)
			.addScalar("idFiscalizacaoSit", Hibernate.INTEGER)
			.addScalar("indAntesApos", Hibernate.SHORT)
			.addScalar("indAcimaLimite", Hibernate.SHORT)
			.addScalar("idCobrancaAcaoSit", Hibernate.INTEGER)
			.addScalar("idCobrancaDebitoSit", Hibernate.INTEGER)
			.addScalar("idCategoria", Hibernate.INTEGER)
			.addScalar("idEsferaPoder", Hibernate.INTEGER)
			.addScalar("idCobrancaCriterio", Hibernate.INTEGER)
			.addScalar("idGerencia", Hibernate.INTEGER)
			.addScalar("idLocalidade", Hibernate.INTEGER)
			.addScalar("idSetor", Hibernate.INTEGER)
			.addScalar("idRota", Hibernate.INTEGER)
			.addScalar("idQuadra", Hibernate.INTEGER)
			.addScalar("numeroQuadra", Hibernate.INTEGER)
			.addScalar("codigoSetor", Hibernate.INTEGER)
			.addScalar("idPerfilImovel", Hibernate.INTEGER)
			.addScalar("idLigacaoAguaSit", Hibernate.INTEGER)
			.addScalar("idLigacaoEsgotoSit", Hibernate.INTEGER)
			.addScalar("idEmpresa", Hibernate.INTEGER)
			.addScalar("idAtendMotivoEnc", Hibernate.INTEGER)
			.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
			.addScalar("idDocEmissaoForma", Hibernate.INTEGER)
			.addScalar("qtdeCobrancaDocumento", Hibernate.INTEGER)
			.addScalar("valorCobrancaDoc", Hibernate.BIG_DECIMAL)
			.setInteger("idCobrancaAtividadeAcaoCronograma", idCobrancaAtividadeAcaoCronograma)
			.list();
			
//			consulta = "select new gcom.cobranca.bean.DadosCobrancaDocumentoHelper(fiscSituacao.id,"
//					+ "cobrancaDocumento.indicadorAntesApos,cobrancaDocumento.indicadorLimite,"
//					+ "cobAcaoSit.id,cobDebSit.id,"
//					+ "categ.id,esfPoder.id,cobCrit.id,gerenReg.id,loc.id,setComercial.id,rot.id,"
//					+ "quad.id,quad.numeroQuadra,setComercial.codigo,"
//					+ "imovPerfil.id, coalesce(ligAguaSit.id, imov.ligacaoAguaSituacao.id)," 
//					+ "coalesce(ligEsgSit.id, imov.ligacaoEsgotoSituacao.id),emp.id,moen.id, loc.unidadeNegocio.id,count(cobrancaDocumento.id),sum(cobrancaDocumento.valorDocumento)) "
//					+ "from CobrancaDocumento cobrancaDocumento "
//					+ "left join cobrancaDocumento.fiscalizacaoSituacao fiscSituacao "
//					+ "left join cobrancaDocumento.esferaPoder esfPoder "
//					+ "left join cobrancaDocumento.cobrancaAcaoSituacao cobAcaoSit "
//					+ "left join cobrancaDocumento.cobrancaDebitoSituacao cobDebSit "
//					+ "left join cobrancaDocumento.categoria categ "
//					+ "left join cobrancaDocumento.cobrancaAcaoAtividadeCronograma cobAcaoAtivCron "
//					+ "left join cobAcaoAtivCron.cobrancaAcaoCronograma cobAcaoCronog "
//					+ "left join cobAcaoCronog.cobrancaAcao cobAcao "
//					+ "left join cobAcaoCronog.cobrancaGrupoCronogramaMes cobGrupCronogramaMes "
//					+ "left join cobGrupCronogramaMes.cobrancaGrupo cobGrupo "
//					+ "left join cobrancaDocumento.cobrancaCriterio cobCrit "
//					+ "left join cobrancaDocumento.imovel imov "
//					+ "left join imov.localidade loc "
//					+ "left join loc.gerenciaRegional gerenReg "
//					+ "left join imov.setorComercial setComercial "
//					+ "left join imov.quadra quad "
//					+ "left join quad.rota rot "
//					+ "left join cobrancaDocumento.imovelPerfil imovPerfil "
//					+ "left join cobrancaDocumento.ligacaoEsgotoSituacao ligEsgSit "
//					+ "left join cobrancaDocumento.ligacaoAguaSituacao ligAguaSit "
//					+ "left join cobrancaDocumento.empresa emp "
//					+ "left join cobrancaDocumento.motivoEncerramento moen "
//					+ "where cobAcaoAtivCron.id = :idCobrancaAtividadeAcaoCronograma "
//					+ "group by fiscSituacao.id,cobrancaDocumento.indicadorAntesApos,cobrancaDocumento.indicadorLimite,"
//					+ "cobAcaoSit.id,cobDebSit.id,categ.id,cobGrupCronogramaMes.anoMesReferencia,cobAcaoCronog.id,"
//					+ "cobAcaoAtivCron.dataPrevista,cobCrit.id,cobGrupo.id,gerenReg.id,loc.id,setComercial.id,rot.id,"
//					+ "quad.id,quad.numeroQuadra,setComercial.codigo,imovPerfil.id,ligAguaSit.id,ligEsgSit.id,esfPoder.id,"
//					+ "cobAcao.id,emp.id, moen.id, loc.unidadeNegocio.id, imov.ligacaoAguaSituacao.id, imov.ligacaoEsgotoSituacao.id "
//					+ "order by fiscSituacao.id,"
//					+ "cobrancaDocumento.indicadorAntesApos,cobrancaDocumento.indicadorLimite,"
//					+ "cobAcaoSit.id,cobDebSit.id,"
//					+ "categ.id,esfPoder.id,gerenReg.id,loc.unidadeNegocio.id, loc.id,setComercial.id,rot.id,"
//					+ "quad.id,quad.numeroQuadra,setComercial.codigo,"
//					+ "imovPerfil.id,ligAguaSit.id,ligEsgSit.id ";
//
//			retorno = session.createQuery(consulta).setInteger(
//					"idCobrancaAtividadeAcaoCronograma",
//					idCobrancaAtividadeAcaoCronograma).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	@Override
	public Integer pesquisarQuantidadeOrdensServicoEncerradasPorCobrancaAcaoAtividade(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException {
		
		Integer retorno = 0;
		Session session = HibernateUtil.getSession();
		
		String where = "";
		if (idCobrancaAcaoAtividadeCronograma != null) {
			where += " AND cd.caac_id = :idCobrancaAcaoAtividadeCronograma ";
		}
		if (idCobrancaAcaoAtividadeComando != null) {
			where += " AND cd.cacm_id = :idCobrancaAcaoAtividadeComando ";
		}
		
		try {
			String consulta = 
				"SELECT orse_id FROM atendimentopublico.ordem_servico os " +
					"INNER JOIN cobranca.cobranca_documento cd ON (os.cbdo_id = cd.cbdo_id) " +
				"WHERE os.orse_cdsituacao <> " + OrdemServico.SITUACAO_PENDENTE + " " + where + " LIMIT 1";
			
			SQLQuery q = session.createSQLQuery(consulta);
			
			q.addScalar("orse_id", Hibernate.INTEGER);
			if (idCobrancaAcaoAtividadeCronograma != null) {
				q.setInteger("idCobrancaAcaoAtividadeCronograma", idCobrancaAcaoAtividadeCronograma);
			}
			if (idCobrancaAcaoAtividadeComando != null) {
				q.setInteger("idCobrancaAcaoAtividadeComando", idCobrancaAcaoAtividadeComando);
			}

    		retorno = (Integer) q.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@Override
	public Integer pesquisarQuantidadePagamentosPorDocumentosCobranca(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException {
		
		Integer retorno = 0;
		Session session = HibernateUtil.getSession();
		
		String where = "";
		if (idCobrancaAcaoAtividadeCronograma != null) {
			where = "caac_id = :idCobrancaAcaoAtividadeCronograma ";
		}
		if (idCobrancaAcaoAtividadeComando != null) {
			where = " cacm_id = :idCobrancaAcaoAtividadeComando ";
		}

		try {
			String consulta = 
				"SELECT pgmt_id from arrecadacao.pagamento where " +
				"cbdo_id IN ( SELECT cbdo_id FROM cobranca.cobranca_documento cd WHERE " + where + " ) " +
				" limit 1";
			
			
			SQLQuery q = session.createSQLQuery(consulta);
			q.addScalar("pgmt_id", Hibernate.INTEGER);
			if (idCobrancaAcaoAtividadeCronograma != null) {
				q.setInteger("idCobrancaAcaoAtividadeCronograma", idCobrancaAcaoAtividadeCronograma);
			}
			if (idCobrancaAcaoAtividadeComando != null) {
				q.setInteger("idCobrancaAcaoAtividadeComando", idCobrancaAcaoAtividadeComando);
			}

    		retorno = (Integer) q.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@Override
	public Integer pesquisarQuantidadeComandosSucessores(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException {
		
		Integer retorno = 0;
		Session session = HibernateUtil.getSession();
		
		String where = "";
		if (idCobrancaAcaoAtividadeCronograma != null) {
			where = " caac_id = :idCobrancaAcaoAtividadeCronograma ";
		}
		if (idCobrancaAcaoAtividadeComando != null) {
			where = " cacm_id = :idCobrancaAcaoAtividadeComando ";
		}
		
		try {
			String consulta = 
				"SELECT cd1.cbdo_id as id FROM cobranca.cobranca_documento cd1 " +
				"WHERE " + where +
				"AND exists ( " +
					"SELECT cd2.cbdo_id FROM cobranca.cobranca_documento cd2 " +
					"WHERE cd1.imov_id = cd2.imov_id AND cd2.cbdo_tmemissao > cd1.cbdo_tmemissao " +
					"AND cbac_id IN ( " +
						"SELECT ca.cbac_id FROM cobranca.cobranca_acao ca WHERE cd1.cbac_id = ca.cbac_idacaoprecedente " +
					") " +
				") LIMIT 1 ";
			
			SQLQuery q = session.createSQLQuery(consulta);
			q.addScalar("id", Hibernate.INTEGER);
			
			if (idCobrancaAcaoAtividadeCronograma != null) {
				q.setInteger("idCobrancaAcaoAtividadeCronograma", idCobrancaAcaoAtividadeCronograma);
			}
			if (idCobrancaAcaoAtividadeComando != null) {
				q.setInteger("idCobrancaAcaoAtividadeComando", idCobrancaAcaoAtividadeComando);
			}

    		retorno = (Integer) q.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@Override
	public Collection pesquisarDocumentosCobrancaExcedentes(Integer idCAAC, Integer idCACM, 
			int quantidadeParaRemover, Integer idLocalidade) throws ErroRepositorioException {
			Session session = HibernateUtil.getSession();

			String queryConsulta = null;
			Collection documentos = null;

			try {

				queryConsulta = "select cbdo_id as id, cbdo_vldocumento as valor from cobranca.Cobranca_Documento cd where ";
				if (idCAAC != null){
					queryConsulta += " cd.caac_id = " + idCAAC;
				} else if (idCACM != null){
					queryConsulta += " cd.cacm_id = " + idCACM;
				}
				if (idLocalidade != null){
					queryConsulta += " and cd.loca_id = " + idLocalidade;	
				}
				queryConsulta += " order by cd.cbdo_vldocumento " + " Limit " + quantidadeParaRemover;

				documentos = session.createSQLQuery(queryConsulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("valor", Hibernate.BIG_DECIMAL)
					.list();
				
			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}
			return documentos;
		}
	
	@Override
	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0004] - Processar Documento de Cobrança
	 * 
	 * Atualizar os COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos
	 * @date 19/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaDocumento(Collection colecaoCobrancaDocumento)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Connection con = null;
		Statement stmt = null;

		try {
			
			con = session.connection();
			stmt = con.createStatement();

			Iterator iteratorColecaoCobrancaDocumento = colecaoCobrancaDocumento
					.iterator();

			int TAM_BLOCO_ATUALIZACAO = 50;
			int i = 0;
			
			StringBuffer atualizar = new StringBuffer();
			
			while (iteratorColecaoCobrancaDocumento.hasNext()) {

				DadosPesquisaCobrancaDocumentoHelper cobrancaDocumentoParaAtualizar = (DadosPesquisaCobrancaDocumentoHelper) 
					iteratorColecaoCobrancaDocumento.next();
					
				atualizar.append("update cobranca.cobranca_documento set ");
				if(cobrancaDocumentoParaAtualizar.getIdSituacaoAcao() != null){
					atualizar.append("cast_id = " + cobrancaDocumentoParaAtualizar.getIdSituacaoAcao() + ",");
				}
				if (cobrancaDocumentoParaAtualizar.getDataSituacaoAcao() != null) {
					atualizar.append(" cbdo_dtsituacaoacao = to_date('" +
						Util.formatarDataComTracoAAAAMMDD(cobrancaDocumentoParaAtualizar.getDataSituacaoAcao())+"','YYYY-MM-DD') , ");
				}
				if (cobrancaDocumentoParaAtualizar.getDataSituacaoDebito() != null) {
					atualizar.append(" cbdo_dtsituacaodebito = to_date('" 
						+ Util.formatarDataComTracoAAAAMMDD(cobrancaDocumentoParaAtualizar.getDataSituacaoDebito())+"','YYYY-MM-DD'), ");
				} else {
					atualizar.append(" cbdo_dtsituacaodebito = null, ");
				}
				if (cobrancaDocumentoParaAtualizar.getIdCategoria() != null){
					atualizar.append(" catg_id = " + cobrancaDocumentoParaAtualizar.getIdCategoria() + ", ");	
				}
				if (cobrancaDocumentoParaAtualizar.getIcAcimaLimite() != null){
					atualizar.append(" cbdo_icacimalimite = " + cobrancaDocumentoParaAtualizar.getIcAcimaLimite() + ", ");	
				}
				if (cobrancaDocumentoParaAtualizar.getIdSituacaoDebito() != null) {
					atualizar.append(" cdst_id = " + cobrancaDocumentoParaAtualizar.getIdSituacaoDebito() + ", ");
				} 
//				else {
//					atualizar.append(" cdst_id = null, ");
//				}
				if (cobrancaDocumentoParaAtualizar.getIdFiscalizacao() != null) {
					atualizar.append(" fzst_id = " + cobrancaDocumentoParaAtualizar.getIdFiscalizacao() + ", ");
				} 
//				else {
//					atualizar.append(" fzst_id = null, ");
//				}
				if (cobrancaDocumentoParaAtualizar.getIdEsferaPoder() != null ) {
					atualizar.append(" epod_id = " + cobrancaDocumentoParaAtualizar.getIdEsferaPoder() + ", ");
				} 
//				else {
//					atualizar.append(" epod_id = null, ");
//				}
				if (cobrancaDocumentoParaAtualizar.getIcAntesDepois() != null) {
					atualizar.append(" cbdo_icantesapos = " + cobrancaDocumentoParaAtualizar.getIcAntesDepois() + ", ");
				} 
//				else {
//					atualizar.append(" cbdo_icantesapos = null, ");
//				}
				if (cobrancaDocumentoParaAtualizar.getIdMotivoEncerramento() != null) {
					atualizar.append(" amen_id = " + cobrancaDocumentoParaAtualizar.getIdMotivoEncerramento() + ", ");
				} 				
				
				atualizar.append(" cbdo_tmultimaalteracao = '" + Util.getSQLTimesTemp(new Date())+"'");
				atualizar.append(" where cbdo_id = " + cobrancaDocumentoParaAtualizar.getIdDocumento() + "");
				
				stmt.addBatch(atualizar.toString());
				atualizar = new StringBuffer("");
				
				if ((i > 0 && i % TAM_BLOCO_ATUALIZACAO == 0) || i == (colecaoCobrancaDocumento.size()-1)){					
//					System.out.println("SQL: " + atualizar.toString());
					//stmt.executeUpdate(atualizar.toString());
					stmt.executeBatch();
					atualizar = new StringBuffer();
					session.flush();
				} 
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão			
			HibernateUtil.closeSession(session);		
			try {
				if (con != null) con.close();
				if (stmt != null) stmt.close();	
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
}
