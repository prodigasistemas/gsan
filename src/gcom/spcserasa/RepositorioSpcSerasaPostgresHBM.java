package gcom.spcserasa;

import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * Classe criada para sobrescrever(override) os metodos no padrão da base de
 * dados Postgres
 * 
 * @author Arthur Carvalho
 * @date 25/11/2010
 */
public class RepositorioSpcSerasaPostgresHBM extends RepositorioSpcSerasaHBM {

	/**
	 * [UC0473] Consultar Dados Complementares do Imóvel
	 *
	 * @author Arthur Carvalho
	 * @date 29/11/2010
	 */
	@Override
	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel) throws ErroRepositorioException {

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
					+ " inner join	cadastro.cliente                   	clie on clie.clie_id=negt.clie_id" + " where	nmrg.imov_id= :idImovel "
					+ " and 	nmrg.nmrg_icaceito= :indicadorAceito " + " and	nmrg_idreginclusao is null" + " group by 1,2" + " order by 1,2";

			retorno = (Collection) session.createSQLQuery(sql).addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("indicadorNegativacaoConfirmada", Hibernate.INTEGER).addScalar("qtdeInclusoes", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel).setShort("indicadorAceito", ConstantesSistema.SIM).list();

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
	public Integer verificaCartaAvisoParcelamento(int idImovel, int numeroDiasAtrasoRecebCartaParcel) throws ErroRepositorioException {

		Integer retorno;
		Session session = HibernateUtil.getSession();
		try {

			String consulta = "select count(a.cbdo_id) as total from cobranca.cobranca_documento a"
					+ " inner join cobranca.documento_tipo b on a.dotp_id = b.dotp_id" + " inner join cadastro.imovel c on a.imov_id = c.imov_id"
					+ " where c.imov_id = :idImovel and a.cbdo_tmemissao < (SELECT  now() - INTERVAL '" + numeroDiasAtrasoRecebCartaParcel + " DAYS') "
					+ " and b.dotp_id = 26";

			retorno = (Integer) session.createSQLQuery(consulta).addScalar("total", Hibernate.INTEGER).setInteger("idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate verificaCartaAvisoParcelamento");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 *
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação Fluxo principal Item 1.0
	 *
	 * @author Thiago Toscano,Vivianne Sousa, Ivan Sergio
	 * @date 07/01/2008,30/10/2009
	 * @alteracao: RM3755 - Adicionado o LAST_ID e LEST_ID;
	 * 
	 */
	// OVERRIDE - Metodo sobrescrito na classe RepositoripSpcSeresaPostgresHBM
	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(Integer idRota) throws ErroRepositorioException {

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
					// +
					// "       sum(case when nmri.cdst_id=1 then nmri_vldebito else 0 end) as valorPendente,"
					// +
					// "       sum(case when nmri.cdst_id=2 then nmri_vldebito else 0 end) as valorPago,"
					// +
					// "       sum(case when nmri.cdst_id=3 then nmri_vldebito else 0 end) as valorParcelado,"
					// +
					// "       sum(case when nmri.cdst_id=4 then nmri_vldebito else 0 end) as valorCancelado"
					// Vivianne Sousa - 15/03/2010 - analista:Fatima Sampaio
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

					+ " from" + "             cobranca.negatd_movimento_reg      nmrg"
					+ "  inner join cobranca.negativador_movimento          ngmv on ngmv.ngmv_id=nmrg.ngmv_id"
					+ "  inner join cobranca.negativacao_imoveis            ngim on ngim.ngcm_id=ngmv.ngcm_id and ngim.imov_id=nmrg.imov_id"
					+ "  inner join cadastro.quadra                         qdra on qdra.qdra_id=nmrg.qdra_id"
					+ "  inner join micromedicao.rota                       rota on rota.rota_id=qdra.rota_id"
					+ "  inner join cadastro.localidade                     loca on loca.loca_id=nmrg.loca_id"
					+ "  inner join cadastro.cliente                        clie on clie.clie_id=nmrg.clie_id"
					+ "  inner join cadastro.cliente_tipo                   cltp on cltp.cltp_id=clie.cltp_id"
					+ "  inner join cobranca.negatd_mov_reg_item nmri on nmri.nmrg_id=nmrg.nmrg_id" + " where" + "     nmrg.nmrg_icaceito=1"
					+ " and ngmv.ngmv_cdmovimento=1 " + " and nmrg.imov_id is not null" + " and rota.rota_id = :idRota" + " group by"
					+ " ngmv.negt_id,ngmv.ngcm_id,ngmv.ngmv_dtprocessamentoenvio," + "       case when nmrg.nmrg_cdexclusaotipo is not null then"
					+ "         case when (ngim_dtexclusao - ngmv_dtprocessamentoenvio) > 30 then 1" + "         else 2 end " + "       else "
					+ "         case when (current_date - ngmv_dtprocessamentoenvio) > 30 then 1" + "         else 2 end " + "       end, "
					+ " nmrg.cdst_id,rota.cbgr_id,loca.greg_id,loca.uneg_id,loca.loca_cdelo,nmrg.loca_id,qdra.stcm_id,nmrg.qdra_id,"
					+ " nmrg.nmrg_cdsetorcomercial,nmrg.nmrg_nnquadra,nmrg.iper_id,nmrg.catg_id,clie.cltp_id,cltp.epod_id,nmrg.last_id, nmrg.lest_id";

			retorno = (List) session.createSQLQuery(sql).addScalar("idNegativador", Hibernate.INTEGER).addScalar("idNegativadorComando", Hibernate.INTEGER)
					.addScalar("dataProcessamento", Hibernate.DATE).addScalar("confirmada", Hibernate.INTEGER)
					.addScalar("idCobrancaDebitoSituacao", Hibernate.INTEGER).addScalar("idCobrancaGrupo", Hibernate.INTEGER)
					.addScalar("idGerenciaRegional", Hibernate.INTEGER).addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("codigoElo", Hibernate.INTEGER).addScalar("idLocalidade", Hibernate.INTEGER).addScalar("idSetorComercil", Hibernate.INTEGER)
					.addScalar("idQuadra", Hibernate.INTEGER).addScalar("codigoSetorComercial", Hibernate.INTEGER).addScalar("numeroQuadra", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER).addScalar("idCategoria", Hibernate.INTEGER).addScalar("idClienteTipo", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER).addScalar("qtdeNegativadorMovimentoReg", Hibernate.INTEGER)
					.addScalar("valorDebito", Hibernate.BIG_DECIMAL).addScalar("valorPendente", Hibernate.BIG_DECIMAL)
					.addScalar("valorPago", Hibernate.BIG_DECIMAL).addScalar("valorParcelado", Hibernate.BIG_DECIMAL)
					.addScalar("valorCancelado", Hibernate.BIG_DECIMAL).addScalar("idSituacaoAgua", Hibernate.INTEGER)
					.addScalar("idSituacaoEsgoto", Hibernate.INTEGER).setInteger("idRota", idRota).list();
			// alterada por Vivianne Sousa,30/10/2009
			// query feita por Fatiam Sampaio e Francisco
			// String hql = " select nmr"
			// + " from gcom.cobranca.NegativadorMovimentoReg nmr"
			// + " left join fetch nmr.cobrancaDebitoSituacao as cds "
			// + " left join fetch nmr.quadra as quad "
			// + " left join fetch quad.rota as rot "
			// + " left join fetch rot.cobrancaGrupo as cobGrup "
			// + " left join fetch nmr.localidade as l "
			// + " left join fetch l.gerenciaRegional as gr "
			// + " left join fetch l.unidadeNegocio as un "
			// + " left join fetch l.localidade as lelo "
			// + " left join fetch quad.setorComercial as sc "
			// + " left join fetch nmr.imovelPerfil as ip "
			// + " left join fetch nmr.categoria as c "
			// + " left join fetch nmr.cliente as clie "
			// + " left join fetch clie.clienteTipo as ct "
			// + " left join fetch ct.esferaPoder as ep "
			// + " left join fetch nmr.negativadorMovimento as nm "
			// + " left join fetch nm.negativacaoComando as nc "
			// + " left join fetch nm.negativador as n "
			// // + " left join fetch nmr.parcelamento as parc "
			// + " where "
			// + " nm.codigoMovimento = 1 "
			// + " and nmr.imovel is not null  "
			// + " and nmr.indicadorAceito = 1 "
			// + " and rot.id = " + idRota
			// + " order by nm.id,nmr.id ";
			//
			// retorno = (List) session.createQuery(hql).list();

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
	public Collection pesquisarNegativadorMovimentoReg(Integer idLocalidade) throws ErroRepositorioException {
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
					+ " nmrg.nmrg_cdexclusaotipo as codigoExclusaoTipo,"
					+ " ngim.ngim_dtexclusao as dataExclusao"
					+ " from cobranca.negatd_movimento_reg   nmrg"
					+ " inner join cobranca.negativador_movimento ngmv on ngmv.ngmv_id=nmrg.ngmv_id and ngmv_cdmovimento=1"
					+ " inner join cobranca.negativacao_imoveis ngim on ngim.ngcm_id=ngmv.ngcm_id and ngim.imov_id=nmrg.imov_id and ngim_dtconfirmacao is null"
					+ " inner join cobranca.negativador_contrato ngcn on ngcn.negt_id=ngmv.negt_id and (ngcn_dtcontratoencerramento is null or ngcn_dtcontratofim >= current_date)"
					+ " where nmrg.imov_id is not null"
					+ " and nmrg.nmrg_icaceito=1"
					+ " and ((nmrg.nmrg_cdexclusaotipo is null and current_date - ngmv.ngmv_dtprocessamentoenvio > ngcn.ngcn_nnprazoinclusao)"
					+ " 	 or (nmrg.nmrg_cdexclusaotipo is not null and  ngim.ngim_dtexclusao - ngmv.ngmv_dtprocessamentoenvio>ngcn.ngcn_nnprazoinclusao))"
					+ " and loca_id = " + idLocalidade;

			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idNegativadorMovimentoReg", Hibernate.INTEGER)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("idNegativacaoImoveis", Hibernate.INTEGER)
					.addScalar("idNegativador", Hibernate.INTEGER)
					.addScalar("idClienteNegativadorMovimentoReg", Hibernate.INTEGER)
					.addScalar("numeroPrazoInclusao", Hibernate.SHORT)
					.addScalar("dataProcessamentoEnvio", Hibernate.DATE)
					.addScalar("codigoExclusaoTipo", Hibernate.INTEGER)
					.addScalar("dataExclusao", Hibernate.DATE)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public List pesquisarImoveisParaNegativacao(Integer idRota, Integer idComando) throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		try {
			sql = " select imov.imov_id as idImovel " + " from cadastro.imovel imov " + " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
					+ " where imov.iper_id <> 4 " + " and imov.imov_icexclusao = " + ConstantesSistema.INDICADOR_IMOVEL_ATIVO + " and qdra.rota_id = " + idRota
					+ " and not exists ( " + " SELECT ngsm.imov_id FROM cobranca.negatd_result_simulacao ngsm " + " WHERE ngcm_id = " + idComando
					+ " AND ngsm.imov_id = imov.imov_id  limit  1 " + " ) ";

			retorno = (List) session.createSQLQuery(sql).addScalar("idImovel", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public boolean verificarNegativacaoDoClienteImovel(Integer idCliente, Integer idImovel) throws ErroRepositorioException {

		boolean retorno = false;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT nmrg_id FROM cobranca.negatd_movimento_reg " + "WHERE   " + "clie_id = :idCliente " + "AND imov_id = :idImovel "
					+ "AND (nmrg_icaceito = 1 OR nmrg_icaceito IS null) " + "AND nmrg_cdexclusaotipo IS null " + " limit  1";

			SQLQuery q = session.createSQLQuery(consulta);
			q.addScalar("nmrg_id", Hibernate.INTEGER);
			q.setInteger("idCliente", idCliente);
			q.setInteger("idImovel", idImovel);
			Integer resultado = (Integer) q.uniqueResult();

			if (resultado != null)
				retorno = true;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
}
