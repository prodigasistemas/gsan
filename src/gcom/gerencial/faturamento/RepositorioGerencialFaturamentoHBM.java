package gcom.gerencial.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.faturamento.ResumoFaturamentoSituacaoEspecial;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gcom.gerencial.faturamento.bean.FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper;
import gcom.gerencial.faturamento.bean.FiltrarResumoDadosCasHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoGuiaPagamentoNovoHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoPorAnoHelper;
import gcom.gerencial.faturamento.bean.ResumoReFaturamentoNovoHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.GeradorSqlRelatorio;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/** 
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialFaturamentoHBM implements IRepositorioGerencialFaturamento {

	protected static IRepositorioGerencialFaturamento instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	protected RepositorioGerencialFaturamentoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencialFaturamento getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioGerencialFaturamentoHBM();
		}

		return instancia;
	}

	
	/**
	 * 
	 * Método que consulta os ResumoFaturamentoSituacaoEspecialHelper
	 *
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getResumoFaturamentoSituacaoEspecialHelper(int idLocalidade) throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null ;
		
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			String hql =
				" select " 
					+ "distinct(imovel.id) , " //0 
					+ "gerenciaRegional.id,  " //1
					+ "localidade.id, " //2
					+ "setorComercial.id,  "//3 
					+ "rota.id, " //4
					+ "quadra.id, " //5
					+ "setorComercial.codigo, "//6 
					+ "quadra.numeroQuadra, " //7
					+ "imovelPerfil.id, " //8
					+ "ligacaoAguaSituacao.id, "//9 
					+ "ligacaoEsgotoSituacao.id, " //10
					+ "faturamentoSituacaoTipo.id, " //11
					+ "faturamentoSituacaoMotivo.id, " //12
					+ "faturamentoSituacaoHistorico.anoMesFaturamentoSituacaoInicio, "//13 
					+ "faturamentoSituacaoHistorico.anoMesFaturamentoSituacaoFim "//14 
				+ " from " 
					+ "	gcom.faturamento.FaturamentoSituacaoHistorico faturamentoSituacaoHistorico " 
					+ "	inner join faturamentoSituacaoHistorico.faturamentoSituacaoTipo faturamentoSituacaoTipo " 
					+ "	inner join faturamentoSituacaoHistorico.faturamentoSituacaoMotivo faturamentoSituacaoMotivo " 
					+ "	inner join faturamentoSituacaoHistorico.imovel imovel " 
					+ "	inner join imovel.localidade localidade " 
					+ "	inner join localidade.gerenciaRegional gerenciaRegional " 
					+ "	inner join imovel.setorComercial setorComercial " 
					+ "	inner join imovel.quadra quadra " 
					+ "	inner join quadra.rota rota " 
					+ "	inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao " 
					+ "	inner join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao " 
					+ "	left join imovel.imovelPerfil imovelPerfil " 
				+ " where " 
					+ "faturamentoSituacaoHistorico.anoMesFaturamentoRetirada is null " 
					+ "and localidade.id="+idLocalidade+"";

			retorno = session.createQuery(hql).list();

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
	

	public Collection pesquisarResumoFaturamentoSituacaoEspecialConsultaHelper(Integer[] idSituacaoTipo, Integer[] idSituacaoMotivo)
		throws ErroRepositorioException {

		List retorno = null ;
		Session session = HibernateUtil.getSession();
		try {
			if (idSituacaoTipo != null && idSituacaoMotivo != null){
				String hql = " select " +
							 " new " + ResumoFaturamentoSituacaoEspecialConsultaFinalHelper.class.getName() +  " ( " + 
							 "	SUM(rfse.quantidadeImovel) " + 
							 " ) " +
							 " from ResumoFaturamentoSituacaoEspecial rfse" +
							 //" inner join rfse.gerenciaRegional gerenciaRegional " +
							 //" inner join rfse.localidade as localidade " +
							 " inner join rfse.faturamentoSituacaoTipo faturamentoTipo " +
							 " inner join rfse.faturamentoSituacaoMotivo faturamentoMotivo " +
							 " where faturamentoTipo.id in (:idSituacaoTipo) AND"+
							 " faturamentoMotivo.id in (:idSituacaoMotivo) ";	
				retorno = session.createQuery(hql).setParameterList("idSituacaoTipo", idSituacaoTipo).setParameterList("idSituacaoMotivo", idSituacaoMotivo).list();
			} else if (idSituacaoTipo != null) {
				String hql = " select " +
				 			 " new " + ResumoFaturamentoSituacaoEspecialConsultaFinalHelper.class.getName() +  " ( " + 
				 			 "	SUM(rfse.quantidadeImovel) " + 
				 			 " ) " +
				 			 " from ResumoFaturamentoSituacaoEspecial rfse" +
				 			 //" inner join rfse.gerenciaRegional gerenciaRegional " +
				 			 //" inner join rfse.localidade as localidade " +
				 			 " inner join rfse.faturamentoSituacaoTipo faturamentoTipo " +
				 			 //" inner join rfse.faturamentoSituacaoMotivo faturamentoMotivo " +
				 			 " where faturamentoTipo.id in (:idSituacaoTipo)";
				retorno = session.createQuery(hql).setParameterList("idSituacaoTipo", idSituacaoTipo).list();
			} else if (idSituacaoMotivo != null){
				String hql = " select " +
	 			 			 " new " + ResumoFaturamentoSituacaoEspecialConsultaFinalHelper.class.getName() +  " ( " + 
	 			 			 "	SUM(rfse.quantidadeImovel) " + 
	 			 			 " ) " +
	 			 			 " from ResumoFaturamentoSituacaoEspecial rfse" +
	 			 			 //" inner join rfse.gerenciaRegional gerenciaRegional " +
	 			 			 //" inner join rfse.localidade as localidade " +
	 			 			 //" inner join rfse.faturamentoSituacaoTipo faturamentoTipo " +
	 			 			 " inner join rfse.faturamentoSituacaoMotivo faturamentoMotivo " +
	 			 			 " where faturamentoMotivo.id in (:idSituacaoMotivo)";
				retorno = session.createQuery(hql).setParameterList("idSituacaoMotivo", idSituacaoMotivo).list();
			} else {
				String hql = " select " +
	 			 			 " new " + ResumoFaturamentoSituacaoEspecialConsultaFinalHelper.class.getName() +  " ( " + 
	 			 			 "	SUM(rfse.quantidadeImovel) " + 
	 			 			 " ) " +
	 			 			 " from ResumoFaturamentoSituacaoEspecial rfse" ;
	 			 			 //" inner join rfse.gerenciaRegional gerenciaRegional " +
	 			 			 //" inner join rfse.localidade as localidade " +
	 			 			 //" inner join rfse.faturamentoSituacaoTipo faturamentoTipo " +
	 			 			 //" inner join rfse.faturamentoSituacaoMotivo faturamentoMotivo " ;
				retorno = session.createQuery(hql).list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/*****************************************************************
	 * CRC66
	 * Alterado por: Ivan Sergio
	 * Data: 22/08/2008
	 * Alteracao: Retirado os comentarios do idSituacaoTipo.
	 *****************************************************************/
  public Collection<Object[]> pesquisarResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(ConsultarResumoSituacaoEspecialHelper helper)
  		throws ErroRepositorioException {
	List<Object[]> retorno = null ;
	Session session = HibernateUtil.getSession();

	try {
		
		String consulta = " SELECT "
			+ " faturamentoMotivo.ftsm_id as idFaturamentoMotivo, faturamentoMotivo.ftsm_dsfatsitmotivo as descricaoFaturamentoMotivo, "
			+ " MIN(rfse.rfse_amfatmtsitinicio) as anoMesInicio, MAX(rfse.rfse_amfaturamentosituacaofim) as anoMesFim, "
			+ " SUM(rfse.rfse_qtimoveis) as qtd "
			+ " FROM faturamento.res_fatur_sit_especial rfse "
			+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
			+ " on gerenciaRegional.greg_id = rfse.greg_id "
			+ " INNER JOIN cadastro.localidade localidade "
			+ " on localidade.loca_id = rfse.loca_id "
			+ " INNER JOIN cadastro.setor_comercial setorComercial "
			+ " on setorComercial.stcm_id = rfse.stcm_id "
			+ " INNER JOIN micromedicao.rota rota "
			+ " on rota.rota_id = rfse.rota_id "
			+ " INNER JOIN faturamento.fatur_situacao_tipo faturamentoTipo "
			+ " on faturamentoTipo.ftst_id = rfse.ftst_id "
			+ " INNER JOIN faturamento.fatur_situacao_motivo faturamentoMotivo "
			+ " on faturamentoMotivo.ftsm_id = rfse.ftsm_id ";

		if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
			consulta = consulta
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id ";
		}
		
		consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, true);
		
		consulta = consulta
					+ " GROUP BY faturamentoMotivo.ftsm_id, faturamentoMotivo.ftsm_dsfatsitmotivo "
					+ " ORDER BY faturamentoMotivo.ftsm_id";
		
		retorno = session.createSQLQuery(consulta)
					.addScalar("idFaturamentoMotivo", Hibernate.INTEGER)
					.addScalar("descricaoFaturamentoMotivo", Hibernate.STRING)
					.addScalar("anoMesInicio", Hibernate.INTEGER)
					.addScalar("anoMesFim", Hibernate.INTEGER)
					.addScalar("qtd", Hibernate.INTEGER).list();

	} catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} finally {
		HibernateUtil.closeSession(session);
	}

	return retorno;
}
  public Collection<Object[]> pesquisarResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(ConsultarResumoSituacaoEspecialHelper helper)throws ErroRepositorioException {
	List<Object[]> retorno = null ;
	Session session = HibernateUtil.getSession();
	try {
		
		String consulta = " SELECT "
			+ " faturamentoTipo.ftst_id as idFaturamentoTipo, faturamentoTipo.ftst_dsfaturamentosituacaotipo as descricaoFaturamentoTipo, "
			+ " SUM(rfse.rfse_qtimoveis) as qtd "
			+ " FROM faturamento.res_fatur_sit_especial rfse "
			+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
			+ " on gerenciaRegional.greg_id = rfse.greg_id "
			+ " INNER JOIN cadastro.localidade localidade "
			+ " on localidade.loca_id = rfse.loca_id "
			+ " INNER JOIN cadastro.setor_comercial setorComercial "
			+ " on setorComercial.stcm_id = rfse.stcm_id "
			+ " INNER JOIN micromedicao.rota rota "
			+ " on rota.rota_id = rfse.rota_id "
			+ " INNER JOIN faturamento.fatur_situacao_tipo faturamentoTipo "
			+ " on faturamentoTipo.ftst_id = rfse.ftst_id "
			+ " INNER JOIN faturamento.fatur_situacao_motivo faturamentoMotivo "
			+ " on faturamentoMotivo.ftsm_id = rfse.ftsm_id ";

		if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
			consulta = consulta
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id ";
		}
		
		consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, true);
		
		consulta = consulta
					+ " GROUP BY faturamentoTipo.ftst_id, faturamentoTipo.ftst_dsfaturamentosituacaotipo "
					+ " ORDER BY faturamentoTipo.ftst_id";
		
		retorno = session.createSQLQuery(consulta)
					.addScalar("idFaturamentoTipo", Hibernate.INTEGER)
					.addScalar("descricaoFaturamentoTipo", Hibernate.STRING)
					.addScalar("qtd", Hibernate.INTEGER).list();
		
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
			return retorno;
	}

	public Collection<Object[]> pesquisarResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(ConsultarResumoSituacaoEspecialHelper helper)
	throws ErroRepositorioException {

	List<Object[]> retorno = null ;
	Session session = HibernateUtil.getSession();

	try {
		
		String consulta = " SELECT "
			+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, setorComercial.stcm_nmsetorcomercial as descricaoSetorComercial, "
			+ " SUM(rfse.rfse_qtimoveis) as qtd "
			+ " FROM faturamento.res_fatur_sit_especial rfse "
			+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
			+ " on gerenciaRegional.greg_id = rfse.greg_id "
			+ " INNER JOIN cadastro.localidade localidade "
			+ " on localidade.loca_id = rfse.loca_id "
			+ " INNER JOIN cadastro.setor_comercial setorComercial "
			+ " on setorComercial.stcm_id = rfse.stcm_id "
			+ " INNER JOIN micromedicao.rota rota "
			+ " on rota.rota_id = rfse.rota_id "
			+ " INNER JOIN faturamento.fatur_situacao_tipo faturamentoTipo "
			+ " on faturamentoTipo.ftst_id = rfse.ftst_id "
			+ " INNER JOIN faturamento.fatur_situacao_motivo faturamentoMotivo "
			+ " on faturamentoMotivo.ftsm_id = rfse.ftsm_id ";

		if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
			consulta = consulta
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id ";
		}
		
		consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, true);
		
		consulta = consulta
					+ " GROUP BY setorComercial.stcm_cdsetorcomercial, setorComercial.stcm_nmsetorcomercial "
					+ " ORDER BY setorComercial.stcm_cdsetorcomercial";
		
		retorno = session.createSQLQuery(consulta)
					.addScalar("codigoSetorComercial", Hibernate.INTEGER)
					.addScalar("descricaoSetorComercial", Hibernate.STRING)
					.addScalar("qtd", Hibernate.INTEGER).list();
		

	} catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} finally {
		HibernateUtil.closeSession(session);
	}
	return retorno;
}
  
  
	public Collection<Object[]> pesquisarResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(ConsultarResumoSituacaoEspecialHelper helper)
		throws ErroRepositorioException {

		List<Object[]> retorno = null ;
		Session session = HibernateUtil.getSession();

		try {
			
			String consulta = " SELECT "
				+ " localidade.loca_id as idLocalidade, localidade.loca_nmlocalidade as nomeLocalidade, "
				+ " SUM(rfse.rfse_qtimoveis) as qtd "
				+ " FROM faturamento.res_fatur_sit_especial rfse "
				+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
				+ " on gerenciaRegional.greg_id = rfse.greg_id "
				+ " INNER JOIN cadastro.localidade localidade "
				+ " on localidade.loca_id = rfse.loca_id "
				+ " INNER JOIN cadastro.setor_comercial setorComercial "
				+ " on setorComercial.stcm_id = rfse.stcm_id "
				+ " INNER JOIN micromedicao.rota rota "
				+ " on rota.rota_id = rfse.rota_id "
				+ " INNER JOIN faturamento.fatur_situacao_tipo faturamentoTipo "
				+ " on faturamentoTipo.ftst_id = rfse.ftst_id "
				+ " INNER JOIN faturamento.fatur_situacao_motivo faturamentoMotivo "
				+ " on faturamentoMotivo.ftsm_id = rfse.ftsm_id ";
	
			if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
				consulta = consulta
					+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
					+ " on unidadeNegocio.uneg_id = localidade.uneg_id ";
			}
			
			consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, true);
			
			consulta = consulta
						+ " GROUP BY localidade.loca_id, localidade.loca_nmlocalidade "
						+ " ORDER BY localidade.loca_id";
			
			retorno = session.createSQLQuery(consulta)
						.addScalar("idLocalidade", Hibernate.INTEGER)
						.addScalar("nomeLocalidade", Hibernate.STRING)
						.addScalar("qtd", Hibernate.INTEGER).list();
			
	} catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} finally {
		HibernateUtil.closeSession(session);
	}
	return retorno;
}
	
	 public Collection<Object[]> pesquisarResumoFaturamentoSituacaoEspecialConsultaUnidadeNegocioHelper(ConsultarResumoSituacaoEspecialHelper helper)throws ErroRepositorioException {

			List<Object[]> retorno = null ;
			Session session = HibernateUtil.getSession();

			try {

				String consulta = " SELECT "
							+ " unidadeNegocio.uneg_id as idUnidadeNegocio, unidadeNegocio.uneg_nmabreviado as nomeAbreviadoUnidadeNegocio, "
							+ " unidadeNegocio.uneg_nmunidadenegocio as nomeUnidadeNegocio, SUM(rfse.rfse_qtimoveis) as qtd "
							+ " FROM faturamento.res_fatur_sit_especial rfse "
							+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
							+ " on gerenciaRegional.greg_id = rfse.greg_id "
							+ " INNER JOIN cadastro.localidade localidade "
							+ " on localidade.loca_id = rfse.loca_id "
							+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
							+ " on unidadeNegocio.uneg_id = localidade.uneg_id "
							+ " INNER JOIN cadastro.setor_comercial setorComercial "
							+ " on setorComercial.stcm_id = rfse.stcm_id "
							+ " INNER JOIN micromedicao.rota rota "
							+ " on rota.rota_id = rfse.rota_id "
							+ " INNER JOIN faturamento.fatur_situacao_tipo faturamentoTipo "
							+ " on faturamentoTipo.ftst_id = rfse.ftst_id "
							+ " INNER JOIN faturamento.fatur_situacao_motivo faturamentoMotivo "
							+ " on faturamentoMotivo.ftsm_id = rfse.ftsm_id ";
				
				consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, true);
				
				consulta = consulta
							+ " GROUP BY unidadeNegocio.uneg_id, unidadeNegocio.uneg_nmabreviado, unidadeNegocio.uneg_nmunidadenegocio "
							+ " ORDER BY unidadeNegocio.uneg_id";
				
				retorno = session.createSQLQuery(consulta)
							.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
							.addScalar("nomeAbreviadoUnidadeNegocio", Hibernate.STRING)
							.addScalar("nomeUnidadeNegocio", Hibernate.STRING)
							.addScalar("qtd", Hibernate.INTEGER).list();

			} catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
			return retorno;
		 }

 public Collection<Object[]> pesquisarResumoFaturamentoSituacaoEspecialConsultaGerenciaRegionalHelper(ConsultarResumoSituacaoEspecialHelper helper)throws ErroRepositorioException {

	List<Object[]> retorno = null ;
	Session session = HibernateUtil.getSession();

	try {

		String consulta = " SELECT "
					+ " gerenciaRegional.greg_id as idGerenciaRegional, gerenciaRegional.greg_nmabreviado as nomeAbreviadoGerenciaRegional, "
					+ " gerenciaRegional.greg_nmregional as nomeGerenciaRegional, SUM(rfse.rfse_qtimoveis) as qtd "
					+ " FROM faturamento.res_fatur_sit_especial rfse "
					+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
					+ " on gerenciaRegional.greg_id = rfse.greg_id "
					+ " INNER JOIN cadastro.localidade localidade "
					+ " on localidade.loca_id = rfse.loca_id "
					+ " INNER JOIN cadastro.setor_comercial setorComercial "
					+ " on setorComercial.stcm_id = rfse.stcm_id "
					+ " INNER JOIN micromedicao.rota rota "
					+ " on rota.rota_id = rfse.rota_id "
					+ " INNER JOIN faturamento.fatur_situacao_tipo faturamentoTipo "
					+ " on faturamentoTipo.ftst_id = rfse.ftst_id "
					+ " INNER JOIN faturamento.fatur_situacao_motivo faturamentoMotivo "
					+ " on faturamentoMotivo.ftsm_id = rfse.ftsm_id ";
		
		if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
			consulta = consulta
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id ";
		}
		
		consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, true);
		
		consulta = consulta
					+ " GROUP BY gerenciaRegional.greg_id, gerenciaRegional.greg_nmabreviado, gerenciaRegional.greg_nmregional "
					+ " ORDER BY gerenciaRegional.greg_id";
		
		retorno = session.createSQLQuery(consulta)
					.addScalar("idGerenciaRegional", Hibernate.INTEGER)
					.addScalar("nomeAbreviadoGerenciaRegional", Hibernate.STRING)
					.addScalar("nomeGerenciaRegional", Hibernate.STRING)
					.addScalar("qtd", Hibernate.INTEGER).list();

	} catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} finally {
		HibernateUtil.closeSession(session);
	}
	return retorno;
 }
 
 	private String criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(ConsultarResumoSituacaoEspecialHelper helper, boolean pesquisaTipoMotivo) {
 		String retorno = " WHERE 1=1 ";
 		
 		if (helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().trim().equals("")) {
 			retorno = retorno + " and gerenciaRegional.greg_id = " + helper.getIdGerenciaRegional();
 		}
 		
 		if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
 			retorno = retorno + " and unidadeNegocio.uneg_id = " + helper.getIdUnidadeNegocio();
 		}
 		
 		if (helper.getIdLocalidadeInicial() != null && !helper.getIdLocalidadeInicial().trim().equals("")) {
 			retorno = retorno + " and localidade.loca_id >= " + helper.getIdLocalidadeInicial();
 		}
 		
 		if (helper.getIdLocalidadeFinal() != null && !helper.getIdLocalidadeFinal().trim().equals("")) {
 			retorno = retorno + " and localidade.loca_id <= " + helper.getIdLocalidadeFinal();
 		}
 		
 		if (helper.getCodigoSetorComercialInicial() != null && !helper.getCodigoSetorComercialInicial().trim().equals("")) {
 			retorno = retorno + " and setorComercial.stcm_cdsetorcomercial >= " + helper.getCodigoSetorComercialInicial();
 		}
 		
 		if (helper.getCodigoSetorComercialFinal() != null && !helper.getCodigoSetorComercialFinal().trim().equals("")) {
 			retorno = retorno + " and setorComercial.stcm_cdsetorcomercial <= " + helper.getCodigoSetorComercialFinal();
 		}
 		
 		if (helper.getCodigoRotaInicial() != null && !helper.getCodigoRotaInicial().trim().equals("")) {
 			retorno = retorno + " and rota.rota_cdrota >= " + helper.getCodigoRotaInicial();
 		}
 		
 		if (helper.getCodigoRotaFinal() != null && !helper.getCodigoRotaFinal().trim().equals("")) {
 			retorno = retorno + " and rota.rota_cdrota <= " + helper.getCodigoRotaFinal();
 		}
	 
 		if (pesquisaTipoMotivo) {
 			if (helper.getSituacaoTipo() != null) {
		 
 				Integer[] idsSituacaoTipo = helper.getSituacaoTipo();
		 
 				String valoresIn = "";
 				for (int i = 0; i < idsSituacaoTipo.length; i++) {
 					if (!idsSituacaoTipo[i].equals("")) {
 						valoresIn = valoresIn + idsSituacaoTipo[i] + ",";
 					}
 				}
 				if (!valoresIn.equals("")) {
 			
 					retorno = retorno + " and faturamentoTipo.ftst_id in (" + valoresIn;
 					retorno = Util.removerUltimosCaracteres(retorno, 1);
 					retorno = retorno + ") ";
 				}
 			}
 		
 			if (helper.getSituacaoMotivo() != null) {
 			 
 				Integer[] idsSituacaoMotivo = helper.getSituacaoMotivo();
		 
 				String valoresIn = "";
 				for (int i = 0; i < idsSituacaoMotivo.length; i++) {
 					if (!idsSituacaoMotivo[i].equals("")) {
 						valoresIn = valoresIn + idsSituacaoMotivo[i] + ",";
 					}
 				}
 				if (!valoresIn.equals("")) {
 			
 					retorno = retorno + " and faturamentoMotivo.ftsm_id in (" + valoresIn;
 					retorno = Util.removerUltimosCaracteres(retorno, 1);
 					retorno = retorno + ") ";
 				}
 			}
 		}	
 		
 		return retorno;
	 
 	}

	public Collection<BigDecimal> pesquisarResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper(ConsultarResumoSituacaoEspecialHelper helper, int anoMesReferencia)
		throws ErroRepositorioException {
	  List<BigDecimal> retorno = null ;
	  Session session = HibernateUtil.getSession();
	  
	  try {
		  
		  String consulta = " SELECT "
				+ " SUM(coalesce(rfsi.rfts_vlagua, 0) + coalesce(rfsi.rfts_vlesgoto, 0) + coalesce(rfsi.rfts_vldebitos, 0) - coalesce(rfsi.rfts_vlcreditos, 0)) as faturamentoEstimado "
				+ " FROM faturamento.resumo_fatur_simulacao rfsi "
				+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
				+ " on gerenciaRegional.greg_id = rfsi.greg_id "
				+ " INNER JOIN cadastro.localidade localidade "
				+ " on localidade.loca_id = rfsi.loca_id "
				+ " INNER JOIN cadastro.setor_comercial setorComercial "
				+ " on setorComercial.stcm_id = rfsi.stcm_id "
				+ " INNER JOIN micromedicao.rota rota "
				+ " on rota.rota_id = rfsi.rota_id ";
	
		  if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
			  consulta = consulta
					+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
					+ " on unidadeNegocio.greg_id = gerenciaRegional.greg_id ";
		  }
	
		  consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, false) + " and rfsi.rfts_amreferencia = :anoMesReferencia ";
	
		  retorno = session.createSQLQuery(consulta)
				.addScalar("faturamentoEstimado", Hibernate.BIG_DECIMAL)
				.setInteger("anoMesReferencia", anoMesReferencia).list();
		  
	  } catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	  } finally {
		HibernateUtil.closeSession(session);
	  }
	  
	  return retorno;
    }	
	public Integer pesquisarResumoFaturamentoSituacaoEspecialConsultaQtLigacoes(ConsultarResumoSituacaoEspecialHelper helper, int anoMesReferencia)
		throws ErroRepositorioException {
	
		Integer retorno = null ;
	
		Session session = HibernateUtil.getSessionGerencial();
	
		try {
			  
			String consulta = " SELECT "
					+ " SUM(rle.rele_qtligacoes) as totalLigacoes "
					+ " FROM cadastro.un_res_lig_econ rle "
					+ " INNER JOIN cadastro.g_gerencia_regional gerenciaRegional "
					+ " on gerenciaRegional.greg_id = rle.greg_id "
					+ " INNER JOIN cadastro.g_localidade localidade "
					+ " on localidade.loca_id = rle.loca_id "
					+ " INNER JOIN cadastro.g_setor_comercial setorComercial "
					+ " on setorComercial.stcm_id = rle.stcm_id "
					+ " INNER JOIN micromedicao.g_rota rota "
					+ " on rota.rota_id = rle.rota_id ";
		
			if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
				consulta = consulta
						+ " INNER JOIN cadastro.g_unidade_negocio unidadeNegocio "
						+ " on unidadeNegocio.greg_id = gerenciaRegional.greg_id ";
			}
		
			  consulta = consulta + criarCondicionaisConsultarResumoFaturamentoSituacaoEspecial(helper, false) + " and rle.rele_amreferencia = :anoMesReferencia ";
		
			  retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("totalLigacoes", Hibernate.INTEGER)
					.setInteger("anoMesReferencia", anoMesReferencia).uniqueResult();
	

		} catch (HibernateException e) {
	
			throw new ErroRepositorioException(e, "Erro no Hibernate");
	
		} finally {
	
			HibernateUtil.closeSession(session);
	
		}
	
		return retorno;
    }

	/**
	 * Método que insere o ResumoFaturamentoSituacaoEspecial em batch
	 *
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 *
	 * @param listResumoFaturamentoSituacaoEspecialHelper
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoFaturamentoSituacaoEspecial(List<ResumoFaturamentoSituacaoEspecial> list) throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();

		if (list != null && !list.isEmpty()) {
			Iterator it = list.iterator();
			//int i = 1;
			try {
				while (it.hasNext()) {
	
					Object obj = it.next();
	
					session.insert(obj);
	
					/*if (i % 50 == 0 || !it.hasNext()) {
						// 20, same as the JDBC batch size
						// flush a batch of inserts and release memory:
						session.flush();
						//session.clear();
					}
					i++;*/
				}
			} finally {
				HibernateUtil.closeSession(session);
			}
		}
	}	
	
	
	/**
	 * Método que exclui todos os  ResumoFaturamentoSituacaoEspecial
	 * 
	 * [CU0341] - Gerar Resumo de Situacao Especial de Faturamento
	 *
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void excluirTodosResumoFaturamentoSituacaoEspecial(int idLocalidade) throws ErroRepositorioException{

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"

			session.createQuery("delete gcom.faturamento.ResumoFaturamentoSituacaoEspecial where loca_id="+idLocalidade).executeUpdate();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * Este caso de uso permite consultar o resumo da pendência, com a opção de impressão da  consulta.
	 * Dependendo da opção de totalização sempre é gerado o relatório, sem a feração da consulta.
	 *
	 * [UC0305] Consultar Análise do Faturamento
	 *
	 * consultarResumoAnaliseFaturamento	
	 *
	 * @author Fernanda Paiva
	 * @date 31/05/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoAnaliseFaturamento(InformarDadosGeracaoRelatorioConsultaHelper 
			informarDadosGeracaoRelatorioConsultaHelper) throws ErroRepositorioException{
		// Cria a coleção de retorno
		List retorno = new ArrayList();
		
		// Obtém a sessão
		Session session = HibernateUtil.getSession();
		
		// A query abaixo realiza uma consulta a tabela de ResumoAnaliseFaturamento
		try{
			
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(GeradorSqlRelatorio.ANALISE_FATURAMENTO,informarDadosGeracaoRelatorioConsultaHelper);
			
			// Aqui sera montanda a parte dos condicionais da query
			// estas condicionais serão usadas se necessarias, o q determina seus usos
			// são os parametros que veem carregados no objeto InformarDadosGeracaoRelatorioConsultaHelper
			// que é recebido do caso de uso [UC0304] Informar Dados para Geração de Relatorio ou COnsulta
			String condicionais  = this.criarCondicionaisResumoAnaliseFaturamento(informarDadosGeracaoRelatorioConsultaHelper,"rfts");
			boolean groupByNotUnion = false;
			String condicionalLigacao = " re.loca_id = localidade.loca_id and "; 
			if(informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao() == 
				ConstantesSistema.CODIGO_ESTADO_MUNICIPIO){
				condicionalLigacao = condicionalLigacao + " muni.muni_id = localidade.muni_idprincipal and ";
				groupByNotUnion = true;
			}
			String sql = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"+informarDadosGeracaoRelatorioConsultaHelper.getDescricaoOpcaoTotalizacao()+"'",
					"",
					condicionalLigacao,
					condicionais, false, groupByNotUnion);
			
			//Faz a pesquisa
			retorno = session.createSQLQuery(sql)
		    .addScalar("somatorio1", Hibernate.INTEGER)
		    .addScalar("somatorio2", Hibernate.INTEGER)
		    .addScalar("somatorio3", Hibernate.INTEGER)
		    .addScalar("somatorio4", Hibernate.BIG_DECIMAL)
		    .addScalar("somatorio5", Hibernate.INTEGER)
		    .addScalar("somatorio6", Hibernate.BIG_DECIMAL)
		    .addScalar("somatorio7", Hibernate.BIG_DECIMAL)
		    .addScalar("somatorio8", Hibernate.BIG_DECIMAL)
		    .addScalar("somatorio9", Hibernate.BIG_DECIMAL).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna a coleção com os resultados da pesquisa
		return retorno;
	}
	
	/**
	 * Este caso de uso permite consultar o resumo do análise do faturamento, com a opção de impressão da  consulta.
	 * Dependendo da opção de totalização sempre é gerado o relatório, sem a feração da consulta.
	 *
	 * [UC0305] Consultar Análise Faturamento
	 *
	 * consultarResumoAnaliseFaturamento	
	 *
	 * @author Fernanda Paiva
	 * @date 31/05/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
public String criarCondicionaisResumoAnaliseFaturamento(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper, String nomeColunaTabela){
		
		String sql = " ";
		//boolean existeWhere = false;
//		A partir daqui sera montanda a parte dos condicionais da query
//		estas condicionais serão usadas se necessarias, o q determina seus usos
//		são os parametros que veem carregados no objeto InformarDadosGeracaoRelatorioConsultaHelper
//		que é recebido do caso de uso [UC0304] Informar Dados para Geração de Relatorio ou COnsulta
		if(informarDadosGeracaoRelatorioConsultaHelper != null){

//			Inicio Parametros simples
			if(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia() != null 
					&& !informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia().toString().equals("")){
				sql = sql + "re." +nomeColunaTabela +"_amreferencia = " 
						+ informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia() + " and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getTipoAnaliseFaturamento() == 1){
				sql = sql + " re.rfts_icsimulacao = 2 and ";
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getTipoAnaliseFaturamento() == 2){
				sql = sql + " re.rfts_icsimulacao = 1 and ";
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo().getId() != null){
				sql = sql + " re.ftgr_id = " 
						+ informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo().getId() + " and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional().getId() != null){
				sql = sql + " re.greg_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional().getId() + " and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getUnidadeNegocio() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getUnidadeNegocio().getId() != null){
				sql = sql + " re.uneg_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper.getUnidadeNegocio().getId() + " and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getEloPolo() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getEloPolo().getId() != null){
				sql = sql + " eloPolo.loca_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper.getEloPolo().getId() + " and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getLocalidade() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getLocalidade().getId() != null){
				sql = sql + " re.loca_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper.getLocalidade().getId() + " and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getMunicipio() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getMunicipio().getId() != null){
				sql = sql + " localidade.muni_idprincipal = muni.muni_id and muni.muni_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper.getMunicipio().getId() + " and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial() != null
				 && informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial().getId() != null){
				sql = sql + " re.stcm_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial().getId() + " and ";
				//existeWhere = true;
			}
			
			/** [RR2011071026]
			 * 	Autor: Paulo Diniz
			 *  Data: 21/07/2011
			 *  Resumo da Análise do Faturamento 
			 */
			///COLOCAR CLAUSULA SQL PRA ROTA
			if((informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial() == null
					 || informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial().getId() == null)
					 && (informarDadosGeracaoRelatorioConsultaHelper.getLocalidade() == null
						|| informarDadosGeracaoRelatorioConsultaHelper.getLocalidade().getId() == null)
						&& informarDadosGeracaoRelatorioConsultaHelper.getRota() != null
						 && informarDadosGeracaoRelatorioConsultaHelper.getRota().getId() != null){
				sql = sql + " re.rota_id = "
				+ informarDadosGeracaoRelatorioConsultaHelper.getRota().getId() + " and ";
				//existeWhere = true;
			}
			
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getQuadra() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getQuadra().getId() != null){
				sql = sql + " re.qdra_id = "
					+ informarDadosGeracaoRelatorioConsultaHelper.getQuadra().getId() + " and ";
				//existeWhere = true;
			}
			
//			Inicio de parametros por colecão
//			sera lida a colecao e montado um IN() a partis dos id extraidos de cada objeto da colecao.
			if(informarDadosGeracaoRelatorioConsultaHelper.getColecaoImovelPerfil() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper.getColecaoImovelPerfil().isEmpty()){
				
				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;
				
				sql = sql + " re.iper_id in (";
				while(iterator.hasNext()){
					imovelPerfil = (ImovelPerfil)iterator.next();
					sql = sql + imovelPerfil.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoAguaSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoAguaSituacao().isEmpty()){
				
				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoAguaSituacao().iterator();
				LigacaoAguaSituacao ligacaoAguaSituacao = null;
				
				sql = sql + " re.last_id in (";
				while(iterator.hasNext()){
					ligacaoAguaSituacao = (LigacaoAguaSituacao)iterator.next();
					sql = sql + ligacaoAguaSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoEsgotoSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoEsgotoSituacao().isEmpty()){
				
				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				
				sql = sql + " re.lest_id in (";
				while(iterator.hasNext()){
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao)iterator.next();
					sql = sql + ligacaoEsgotoSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getColecaoCategoria() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper.getColecaoCategoria().isEmpty()){
				
				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper.getColecaoCategoria().iterator();
				Categoria categoria = null;
				
				sql = sql + " re.catg_id in (";
				while(iterator.hasNext()){
					categoria = (Categoria)iterator.next();
					sql = sql + categoria.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				//existeWhere = true;
			}
			
			if(informarDadosGeracaoRelatorioConsultaHelper.getColecaoEsferaPoder() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper.getColecaoEsferaPoder().isEmpty()){
				
				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper.getColecaoEsferaPoder().iterator();
				EsferaPoder esferaPoder = null;
				
				sql = sql + " re.epod_id in (";
				while(iterator.hasNext()){
					esferaPoder = (EsferaPoder)iterator.next();
					sql = sql + esferaPoder.getId()+ ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				//existeWhere = true;
			}
		}
		
//		if(existeWhere){
//			sql = " where " + sql;
//		}
//		retira o " and " q fica sobrando no final da query
		sql = Util.removerUltimosCaracteres(sql, 4);

		return sql;
	}



	/**
	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
	 * faturamento
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 12/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */

	/***
	 * Alterado por: Ivan Sérgio
	 * Data: 04/09/2008
	 * Alteracao na query para melhoramento de performace.
	 */
	public List getResumoFaturamentoAguaEsgoto(int idSetor, int anoMes, int indice, int qtRegistros)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		
		try {
			/*
			String hql = "select "
				  + "  conta.id, "//0
				  + "  imovel.id, "//1
				  + "  conta.localidade.gerenciaRegional.id, "//2
				  + "  conta.localidade.unidadeNegocio.id, "//3
				  + "  conta.localidade.localidade.id, "//4 elo
				  + "  conta.localidade.id, "//5 localidade
				  + "  quadra.setorComercial.id, "//6
				  + "  quadra.rota.id, "//7
				  + "  quadra.id, "//8
				  + "  imovel.setorComercial.codigo, "//9 
				  + "  quadra.numeroQuadra, "//10
				  + "  imovel.imovelPerfil.id, "//11 
				  + "  conta.ligacaoAguaSituacao.id, "//12 
				  + "  conta.ligacaoEsgotoSituacao.id, "//13 
				  + "  case when ( "
				  + "    imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then " 
				  + "    0 "
				  + "  else "
				  + "    imovel.ligacaoAgua.ligacaoAguaPerfil.id " 
				  + "  end, "//14
				  + "  case when ( " 
				  + "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then " 
				  + "    0 "
				  + "  else "
				  + "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id " 
				  + "  end, "//15
				  + "  conta.consumoAgua, "//16 
				  + "  conta.consumoEsgoto, "//17 
				  + "  conta.valorAgua, "//18
				  + "  conta.valorEsgoto, "//19 
				  + "  conta.referencia, "//20
				  + "  conta.debitoCreditoSituacaoAtual.id, "//21
				  + "  rota.empresa.id, "//22
				  + "  imovel.quantidadeEconomias, "//23
  				  + "  conta.consumoTarifa.id, "// 24
				  + "  rota.faturamentoGrupo.id, "// 25
				  + "  rota.codigo "//26
                  + " from "
                  + "  gcom.cadastro.sistemaparametro.SistemaParametro as sistemaParametro, " 
                  + "  gcom.faturamento.conta.Conta as conta " 
                  + "  inner join conta.imovel as imovel "
                  + "  inner join imovel.quadra as quadra "
                  + "  inner join quadra.rota as rota "
                  + "  inner join imovel.setorComercial as setorComercial "
                  + "  left join imovel.ligacaoAgua ligacaoAgua "
                  + "  left join imovel.ligacaoEsgoto ligacaoEsgoto "  
				  + " where "
				  + "  sistemaParametro.anoMesFaturamento = conta.referencia and "
				  + "  conta.referencia = :anoMesReferencia and "
				  + "  (conta.debitoCreditoSituacaoAtual.id = 0 or conta.debitoCreditoSituacaoAnterior.id = 0) and "
				  + "  quadra.setorComercial.id = :idSetor "
    			  + " order by "
		 		  + " conta.id ";			
		    */
			
			String hql =
				"select " +
				"	cnta.id, " +
				"	imo.id, " +
				"	gere.id, " +
				"	unNe.id, " +
				"	elo.id, " +
				"	loca.id, " +
				"	seCo.id, " +
				"	rot.id, " +
				"	quad.id, " +
				"	seCo.codigo, " +
				"	quad.numeroQuadra, " +
				"	imPe.id, " +
				"	las.id, " +
				"	les.id, " +
				"	coalesce(lap.id, 0), " +
				"	coalesce(lep.id, 0), " +
				"	cnta.consumoAgua, " +
				"	cnta.consumoEsgoto, " +
				"	cnta.valorAgua, " +
				"	cnta.valorEsgoto, " +
				"	cnta.referencia, " +
				"	cnta.debitoCreditoSituacaoAtual.id, " +
				"	empr.id, " +
				"	imo.quantidadeEconomias, " +
				"	conTar.id, " +
				"	fatGru.id, " +
				"	rot.codigo " +
				"from " +
				"	Conta cnta " +
				" 	inner join cnta.localidade loca " +
				"	inner join loca.localidade elo " +
				"	inner join loca.gerenciaRegional gere " +
				"	inner join loca.unidadeNegocio unNe " +
				"		with unNe.gerenciaRegional.id = gere.id " +
				"	inner join cnta.quadraConta quad " +
				"	inner join quad.setorComercial seCo " +
				"	inner join quad.rota rot " +
				"		with rot.setorComercial.id = seCo.id " +
				"	inner join cnta.imovelPerfil imPe " +
				"	inner join cnta.ligacaoAguaSituacao las " +
				"	inner join cnta.ligacaoEsgotoSituacao les " +
				"	inner join cnta.imovel imo " +
				"	inner join rot.faturamentoGrupo fatGru " +
				"	inner join rot.empresa empr " +
				"	left join imo.ligacaoAgua liAg " +
				"	left join liAg.ligacaoAguaPerfil lap " +
				"	left join imo.ligacaoEsgoto liEs " +
				"	left join liEs.ligacaoEsgotoPerfil lep " +
				"	left join cnta.consumoTarifa conTar " +
				"where " +
				"	cnta.referencia = :anoMesReferencia " +
				"	and ( cnta.debitoCreditoSituacaoAtual = 0 or cnta.debitoCreditoSituacaoAnterior = 0 ) " +
				"	and seCo.id = :idSetor " +
				"order by " +
				"	cnta.id"; 
			
			retorno = session.createQuery(hql)
			.setInteger("anoMesReferencia", anoMes)
			.setInteger("idSetor", idSetor).setFirstResult(indice).setMaxResults(qtRegistros).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
	 * faturamento (Débitos Cobrados)
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 17/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */

	public List getPesquisaDebitoCobrado(int idConta, int idImovel, int mesAno)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		try {
			String sql = "select "

				    //debito cobrado
				    + "  financiamentotipo.fntp_id as financiamentotipo,  " //0 
				    + "  1 as documentotipo, " //1 
					+ "  lancamentoitemcontabil.lict_id as lancamentoitemcontabil, " //2 
					+ "  sum(debitocobrado.dbcb_vlprestacao) as valordebitos, " //3 
					+ "  count(debitocobrado.dbcb_id) as qtddocumentos " //4 
					+ " from " 
					+ " faturamento.debito_cobrado debitocobrado "
					+ " inner join financeiro.lancamento_item_contabil lancamentoitemcontabil on debitocobrado.lict_id = lancamentoitemcontabil.lict_id "
					+ " inner join faturamento.conta conta on debitocobrado.cnta_id = conta.cnta_id "
					+ " inner join  financeiro.financiamento_tipo financiamentotipo on debitocobrado.fntp_id = financiamentotipo.fntp_id "
					+ " where "
					+ " conta.cnta_id = :idConta "
					+ " and (conta.dcst_idatual = 0 or conta.dcst_idanterior = 0) " 
					+ " and conta.cnta_vldebitos > 0 "
					+ " group by financiamentotipo.fntp_id, 1, lancamentoitemcontabil.lict_id "
//					+ " union select " 
//					//guias pagamento
//				    + "  financiamentotipo.fntp_id as financiamentotipo,  " 
//				    + "  7 as documentotipo, " 
//					+ "  lancamentoitemcontabil.lict_id as lancamentoitemcontabil, "
//					+ "  sum(guiapagamento.gpag_vldebito) as valordebitos, "
//					+ "  count(guiapagamento.gpag_id) as qtddocumentos"
//					+ " from "
//					+ " faturamento.guia_pagamento as guiapagamento "
//					+ " inner join financeiro.lancamento_item_contabil lancamentoitemcontabil on guiapagamento.lict_id = lancamentoitemcontabil.lict_id "
//					+ " inner join cadastro.imovel imovel on guiapagamento.imov_id = imovel.imov_id "
//					+ " inner join financeiro.financiamento_tipo financiamentotipo on guiapagamento.fntp_id = financiamentotipo.fntp_id "
//					+ " where "
//					+ " imovel.imov_id = :idImovel "
//					+ " and (guiapagamento.dcst_idatual = 0 or guiapagamento.dcst_idanterior = 0) " 
//					+ " and guiapagamento.gpag_amreferenciacontabil = :mesAno "
//					+ " group by financiamentotipo, documentotipo, lancamentoitemcontabil "
					+ " order by financiamentotipo, documentotipo, lancamentoitemcontabil ";
			
			 retorno = session.createSQLQuery(sql).addScalar("financiamentotipo",Hibernate.INTEGER)
			                                      .addScalar("documentotipo",Hibernate.INTEGER)
			                                      .addScalar("lancamentoitemcontabil",Hibernate.INTEGER)
			                                      .addScalar("valordebitos",Hibernate.BIG_DECIMAL)
			                                      .addScalar("qtddocumentos", Hibernate.INTEGER)
			                                      .setInteger("idConta", idConta)
//			                                      .setInteger("idImovel", idImovel)
//			                                      .setInteger("mesAno", mesAno)
			                                      .list();
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
	 * faturamento (Créditos Realizados)
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 17/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */

	public List getPesquisaCreditoRealizado(int idSetor, int mesAno)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		try {
			/*
			String hql = " select " 
				+ "  creditorealizado.creditoOrigem.id, " // 0
				+ "  creditorealizado.lancamentoItemContabil.id, " //1 
				+ "  sum(creditorealizado.valorCredito) as valorcredito, " //2 
				+ "  count(creditorealizado.creditoOrigem.id), " //3
				+ "  creditorealizado.creditoTipo.id " // 4
				+ " from "
				+ "  gcom.faturamento.credito.CreditoRealizado as creditorealizado " 
				+ " where creditorealizado.conta.id in(select conta.id "
				+ "                                    from gcom.faturamento.conta.Conta as conta "  
				+ "                                    inner join conta.imovel as imovel "
				+ "                                    inner join imovel.quadra as quadra "
				+ "                                    where conta.referencia = :mesAno and " 
				+ "                                    (conta.debitoCreditoSituacaoAtual.id = 0 or conta.debitoCreditoSituacaoAnterior.id = 0) and " 
				+ "                                    quadra.setorComercial.id = :idSetor) "
				+ " group by creditorealizado.creditoOrigem.id, creditorealizado.lancamentoItemContabil.id, creditorealizado.creditoTipo.id "
				+ " order by creditorealizado.creditoOrigem.id, creditorealizado.lancamentoItemContabil.id, creditorealizado.creditoTipo.id ";
			*/
			
			/***
			 * Autor: Ivan Sérgio
			 * Data: 11/09/2008
			 * Alteracao: Otimização da consulta. Reduzio o tempo da consulta de +- 70s para 1s.
			 */
			/*TODO: Cosanpa - alteração para corrigir o valor gerado dos créditos no gerencial
			 * comentada linha "with creditorealizado.quadra.id = quadra.id " em 22/08/2011*/
			String hql =
				"select " +
				"   	creditorealizado.creditoOrigem.id, " +
				"   	creditorealizado.lancamentoItemContabil.id, " +
				"   	sum(creditorealizado.valorCredito) as valorcredito, " +
				"   	count(creditorealizado.creditoOrigem.id), " +
				"   	creditorealizado.creditoTipo.id " +
				"from " +
				"   	gcom.faturamento.conta.Conta conta " +
				"   	inner join conta.quadraConta quadra " +
				"   	inner join conta.creditoRealizados as creditorealizado " +
				//"   		with creditorealizado.quadra.id = quadra.id " +
				"where " +
				"	conta.referencia = :mesAno " +
				"  	and (conta.debitoCreditoSituacaoAtual.id = 0 or conta.debitoCreditoSituacaoAnterior.id = 0) " +
				"  	and quadra.setorComercial.id = :idSetor " +
				"group by " +
				"	creditorealizado.creditoOrigem.id, creditorealizado.lancamentoItemContabil.id, creditorealizado.creditoTipo.id " +
				"order by " +
				"	creditorealizado.creditoOrigem.id, creditorealizado.lancamentoItemContabil.id, creditorealizado.creditoTipo.id";
			
			retorno = session.createQuery(hql).setInteger("mesAno", mesAno)
			.setInteger("idSetor", idSetor).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * pesquisarEsferaPoderClienteResponsavelImovel
	 * 
	 * @author Marcio Roberto
	 * @date 05/06/2007
	 * 
	 * @param id do imovel a ser pesquisado	 
	 * @return Esfera de poder do cliente responsavel pelo imovel 
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarEsferaPoderClienteResponsavelImovel(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = 
				"select " +
				"  case when ep.id is not null then " +
				"    ep.id " +
				"  else  " +
				"    0 " +
				"  end " +
				"from " +
				"  gcom.cadastro.imovel.Imovel i " +
				"  inner join i.clienteImoveis ci " +
				"  inner join ci.cliente c " +
				"  inner join c.clienteTipo ct " +
				"  inner join ct.esferaPoder ep " +
				"  inner join ci.clienteRelacaoTipo crt " +
				"where " +
				"  crt.id = :clienteResponsavel and ci.dataFimRelacao is null and i.id =:idImovel";
		
			retorno = session.createQuery(consulta)
				.setInteger( "clienteResponsavel", ClienteRelacaoTipo.RESPONSAVEL)
				.setInteger( "idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		if ( retorno != null ){
		  return (Integer)retorno;
		} else {
	      return 0;
		}
	}
	
	/**
	 * pesquisarTipoClienteClienteResponsavelImovel
	 * 
	 * @author Marcio Roberto
	 * @date 05/06/2007
	 * 
	 * @param imovel a ser pesquisado	 
	 * @return Tipo de cliente do cliente responsavel pelo imovel
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarTipoClienteClienteResponsavelImovel(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = 
				"select " +
				"  case when ct.id is not null then " +
				"    ct.id " +
				"  else  " +
				"    0 " +
				"  end " +
				"from " +
				"  gcom.cadastro.imovel.Imovel i " +
				"  inner join i.clienteImoveis ci " +
				"  inner join ci.cliente c " +
				"  inner join c.clienteTipo ct " +
				"  inner join ci.clienteRelacaoTipo crt " +
				"  where " +
				"  crt.id = :clienteResponsavel and ci.dataFimRelacao is null and i.id =:idImovel";
		
			retorno = session.createQuery(consulta)
				.setInteger( "clienteResponsavel", ClienteRelacaoTipo.RESPONSAVEL)
				.setInteger( "idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		if ( retorno != null ){
		  return (Integer)retorno;
		} else {
	      return 0;
		}
	}
	
	
	/**
	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
	 * Re-faturamento 
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 12/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getResumoReFaturamento(int idSetor, int anoMes, int indice, int qtRegistros) 
			throws ErroRepositorioException { 

		List retorno = null; 

		Session session = HibernateUtil.getSession(); 
		try {

			String hql = "select " 
				+ "  conta.id, "//0 
				+ "  imovel.id, "//1 
				+ "  conta.localidade.gerenciaRegional.id, "//2 
				+ "  conta.localidade.unidadeNegocio.id, "//3 
				+ "  conta.localidade.localidade.id, "//4 elo 
				+ "  conta.localidade.id, "//5 localidade 
				+ "  quadra.setorComercial.id, "//6 
				+ "  quadra.rota.id, "//7 
				+ "  quadra.id, "//8 
				+ "  imovel.setorComercial.codigo, "//9 
				+ "  quadra.numeroQuadra, "//10 
				+ "  imovel.imovelPerfil.id, "//11 
				+ "  conta.ligacaoAguaSituacao.id, "//12 
				+ "  conta.ligacaoEsgotoSituacao.id, "//13 
				+ "  case when ( " 
				+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then " 
				+ "    0 " 
				+ "  else " 
				+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id " 
				+ "  end, "//14 
				+ "  case when ( " 
				+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then " 
				+ "    0 " 
				+ "  else " 
				+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id " 
				+ "  end, "//15 
				+ "  conta.consumoAgua, "//16 
				+ "  conta.consumoEsgoto, "//17 
				+ "  conta.valorAgua, "//18 
				+ "  conta.valorEsgoto, "//19 
				+ "  conta.referencia, "//20 
				+ "  conta.debitoCreditoSituacaoAtual.id, "//21 
				+ "  conta.referenciaContabil, "//22 
				+ "  conta.valorImposto, "//23 
				+ "  conta.valorCreditos, "//24 
				+ "  conta.debitos, "//25 
				+ "  conta.debitoCreditoSituacaoAnterior.id "//26
				+ "  from " 
                + "  gcom.cadastro.sistemaparametro.SistemaParametro as sistemaParametro, " 
                + "  gcom.faturamento.conta.Conta as conta " 
                + "  inner join conta.imovel as imovel " 
                + "  inner join imovel.quadra as quadra " 
                + "  inner join quadra.rota as rota " 
                + "  inner join imovel.setorComercial as setorComercial " 
                + "  left join imovel.ligacaoAgua ligacaoAgua " 
                + "  left join imovel.ligacaoEsgoto ligacaoEsgoto "  
  			    + " where " 
				+ "  sistemaParametro.anoMesFaturamento = :anoMesReferencia and " 
				//+ "  conta.referenciaContabil = :anoMesReferencia and " 
				+ "  (conta.debitoCreditoSituacaoAtual.id in (1,2,3,4) or conta.debitoCreditoSituacaoAnterior.id = 1) and "
				+ "  quadra.setorComercial.id = :idSetor " 
  			    + "  order by " 
		 		+ "  conta.debitoCreditoSituacaoAtual.id ";			 
			
			
			
 			 retorno = session.createQuery(hql)
			.setInteger("anoMesReferencia", anoMes)
			.setInteger("idSetor", idSetor).setFirstResult(indice).setMaxResults(qtRegistros).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	// debito a cobrar, outros, impostos, creditos.
	/***
	 * Alterado por: Ivan Sérgio
	 * Data: 04/09/2008
	 * Alteracao na query para melhoramento de performace;
	 * Adicionado o campo imo.quantidadeEconomias;
	 */
	public List getContasResumoFaturamentoAguaEsgoto(int idSetor, int anoMes,
			int indice, int qtRegistros) throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		try {
			/*
			String hql = "select " 
					+ "  conta.id, "// 0 
					+ "  imovel.id, "// 1 
					+ "  conta.localidade.gerenciaRegional.id, "// 2 
					+ "  conta.localidade.unidadeNegocio.id, "// 3 
					+ "  conta.localidade.localidade.id, "// 4 elo 
					+ "  conta.localidade.id, "// 5 localidade 
					+ "  quadra.setorComercial.id, "// 6 
					+ "  quadra.rota.id, "// 7 
					+ "  quadra.id, "// 8 
					+ "  imovel.setorComercial.codigo, "// 9 
					+ "  quadra.numeroQuadra, "// 10 
					+ "  imovel.imovelPerfil.id, "// 11 
					+ "  conta.ligacaoAguaSituacao.id, "// 12 
					+ "  conta.ligacaoEsgotoSituacao.id, "// 13 
					+ "  case when ( " 
					+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then " 
					+ "    0 " 
					+ "  else " 
					+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id " 
					+ "  end, "// 14 
					+ "  case when ( " 
					+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then " 
					+ "    0 " 
					+ "  else " 
					+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id " 
					+ "  end, "// 15 
					+ "  conta.consumoAgua, "// 16 
					+ "  conta.consumoEsgoto, "// 17 
					+ "  conta.valorAgua, "// 18 
					+ "  conta.valorEsgoto, "// 19 
					+ "  conta.referencia, "// 20 
					+ "  conta.debitoCreditoSituacaoAtual.id, "// 21 
					+ "  rota.empresa.id, "// 22 
					+ "  conta.consumoTarifa.id, "// 23 
					+ "  rota.faturamentoGrupo.id, "// 24
					+ "  rota.codigo "//25
					+ " from " 
					+ "  gcom.cadastro.sistemaparametro.SistemaParametro as sistemaParametro, " 
					+ "  gcom.faturamento.conta.Conta as conta " 
					+ "  inner join conta.imovel as imovel " 
					+ "  inner join imovel.quadra as quadra " 
					+ "  inner join quadra.rota as rota " 
					+ "  inner join imovel.setorComercial as setorComercial " 
					+ "  left join imovel.ligacaoAgua ligacaoAgua " 
					+ "  left join imovel.ligacaoEsgoto ligacaoEsgoto " 
					+ " where " 
					+ "  sistemaParametro.anoMesFaturamento = conta.referencia and " 
					+ "  conta.referencia = :anoMesReferencia and " 
					+ "  (conta.debitoCreditoSituacaoAtual.id = 0 or conta.debitoCreditoSituacaoAnterior.id = 0) and " 
					+ "  quadra.setorComercial.id = :idSetor "
			 		+ " order by "
			 		+ "  conta.id, conta.localidade.gerenciaRegional.id, conta.localidade.unidadeNegocio.id, "
			 		+ "  conta.localidade.id, "
			 		+ "  quadra.setorComercial.id, quadra.id, quadra.numeroQuadra";
			*/
			
			/*TODO: COSANPA
			 * Alteração para corrigir a diferença do resumo do faturamento na base gerencial
			 * Autor: Wellington Vernech Rocha
			 * Data: 12/09/2011*/
			String hql =
				"select " +
				"	cnta.id, " +
				"	imo.id, " +
				"	gere.id, " +
				"	unNe.id, " +
				"	elo.id, " +
				"	loca.id, " +
				"	seCo.id, " +
				"	rot.id, " +
				"	quad.id, " +
				"	seCo.codigo, " +
				"	quad.numeroQuadra, " +
				"	imPe.id, " +
				"	las.id, " +
				"	les.id, " +
				"	coalesce(lap.id, 0), " +
				"	coalesce(lep.id, 0), " +
				"	cnta.consumoAgua, " +
				"	cnta.consumoEsgoto, " +
				"	cnta.valorAgua, " +
				"	cnta.valorEsgoto, " +
				"	cnta.referencia, " +
				"	cnta.debitoCreditoSituacaoAtual.id, " +
				"	empr.id, " +
				"	conTar.id, " +
				"	fatGru.id, " +
				"	rot.codigo, " +
				"	imo.quantidadeEconomias " +
				"from " +
				"	Conta cnta " +
				" 	inner join cnta.localidade loca " +
				"	inner join loca.localidade elo " +
				"	inner join loca.gerenciaRegional gere " +
				"	inner join loca.unidadeNegocio unNe " +
//				"		with unNe.gerenciaRegional.id = gere.id " +
				"	inner join cnta.quadraConta quad " +
				"	inner join quad.setorComercial seCo " +
				"	inner join quad.rota rot " +
//   			"		with rot.setorComercial.id = seCo.id " +
				"	inner join cnta.imovelPerfil imPe " +
				"	inner join cnta.ligacaoAguaSituacao las " +
				"	inner join cnta.ligacaoEsgotoSituacao les " +
				"	inner join cnta.imovel imo " +
				"	inner join rot.faturamentoGrupo fatGru " +
				"	inner join rot.empresa empr " +
				"	left join imo.ligacaoAgua liAg " +
				"	left join liAg.ligacaoAguaPerfil lap " +
				"	left join imo.ligacaoEsgoto liEs " +
				"	left join liEs.ligacaoEsgotoPerfil lep " +
				"	left join cnta.consumoTarifa conTar " +
				"where " +
				"	cnta.referencia = :anoMesReferencia " +
				"	and ( cnta.debitoCreditoSituacaoAtual = 0 or cnta.debitoCreditoSituacaoAnterior = 0 ) " +
				"	and seCo.id = :idSetor";				
			
			retorno = session.createQuery(hql).setInteger("anoMesReferencia",
					anoMes).setInteger("idSetor", idSetor).setFirstResult(
					indice).setMaxResults(qtRegistros).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	

	public List getImoveisResumoFaturamento(int idSetor, int anoMes,
			int indice, int qtRegistros) throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
  			        + "  sistemaParametro.anoMesFaturamento, "// 0
					+ "  imovel.id, "// 1
					+ "  imovel.localidade.gerenciaRegional.id, "// 2
					+ "  imovel.localidade.unidadeNegocio.id, "// 3
					+ "  imovel.localidade.localidade.id, "// 4 elo
					+ "  imovel.localidade.id, "// 5 localidade
					+ "  quadra.setorComercial.id, "// 6
					+ "  quadra.rota.id, "// 7 
					+ "  quadra.id, "// 8 
					+ "  imovel.setorComercial.codigo, "// 9 
					+ "  quadra.numeroQuadra, "// 10 
					+ "  imovel.imovelPerfil.id, "// 11 
					+ "  imovel.ligacaoAguaSituacao.id, "// 12 
					+ "  imovel.ligacaoEsgotoSituacao.id, "// 13 
					+ "  case when ( "
					+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "
					+ "    0 "
					+ "  else "
					+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id "
					+ "  end, "// 14
					+ "  case when ( " 
					+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then " 
					+ "    0 "
					+ "  else "
					+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id "
					+ "  end "// 15
					+ " from "
					+ "  gcom.cadastro.sistemaparametro.SistemaParametro as sistemaParametro, "
					+ "  gcom.cadastro.imovel.Imovel as imovel "
					+ "  inner join imovel.quadra as quadra "
					+ "  inner join quadra.rota as rota "
					+ "  inner join imovel.setorComercial as setorComercial "
					+ "  left join imovel.ligacaoAgua ligacaoAgua "
					+ "  left join imovel.ligacaoEsgoto ligacaoEsgoto "
					+ " where "
					+ "  sistemaParametro.anoMesFaturamento = :anoMesReferencia "
					+ "  quadra.setorComercial.id = :idSetor "
			 		+ "  order by "
			 		+ "  imovel.id, imovel.localidade.gerenciaRegional.id, imovel.localidade.unidadeNegocio.id, "
			 		+ "  imovel.localidade.id, "
			 		+ "  quadra.setorComercial.id, quadra.id, quadra.numeroQuadra";
			
			retorno = session.createQuery(hql).setInteger("anoMesReferencia",
					anoMes).setInteger("idSetor", idSetor).setFirstResult(
					indice).setMaxResults(qtRegistros).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	
	
	public Collection<Integer> pesquisarIdsSetores()
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			
			consulta = " select "
					+ " quad.setorComercial.id as idSetor "
					+ "		 from "
					+ "  Quadra quad " // where quad.setorComercial.localidade.id = 96 "
					+ " group by quad.setorComercial.id "; 


			retorno = session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
	 * faturamento (Débitos a Cobrar)
	 * 
	 * @author Marcio Roberto
	 * @date 17/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	// int idSetor, int anoMes, int indice, int qtRegistros
	public List getPesquisaDebitoACobrar(int idSetor, int anoMes)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String hql = " select " 
				+ " 1, "                                                            // 00  =  FINANCIAMENTO TIPO
				+ " 6, "                                                            // 01  =  DOCUMENTO TIPO
				+ " debitoacobrar.lancamentoItemContabil.id, "                      // 02  =  LANCAMENTO ITEM CONTABIL
				+ " loca.gerenciaRegional.id, "                                     // 03  =  GERENCIA REGIONAL
				+ " loca.unidadeNegocio.id, "                                       // 04  =  UNIDADE NEGOCIO
				+ " loca.localidade.id, " // elo                                    // 05  =  ELO
				+ " loca.id, "// idlocalidade                                       // 06  =  LOCALIDADE
				+ " debitoacobrar.quadra.setorComercial.id, "                       // 07  =  SETOR COMERCIAL
				+ " debitoacobrar.quadra.rota.id, "                                 // 08  =  ROTA
				+ " debitoacobrar.quadra.id, "                                      // 09  =  QUADRA
				+ " debitoacobrar.codigoSetorComercial, "                           // 10  =  CODIGO SETOR COMERCIAL
				+ " debitoacobrar.numeroQuadra, "                                   // 11  =  NUMERO QUADRA
				+ " imov.imovelPerfil.id, "                                         // 12  =  PERFIL DO IMOVEL
				+ " imov.ligacaoAguaSituacao.id, "                                  // 13  =  SITUACAO AGUA LIGACAO
				+ " imov.ligacaoEsgotoSituacao.id, "                                // 14  =  SITUACAO ESGOTO LIGACAO
				+ "  case when ( "                                                  // -------------------|
				+ "    imov.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "       //   CHAVES DE QUEBRA |
				+ "    0 "                                                          // -------------------|
				+ "  else "                                                         //   
				+ "    imov.ligacaoAgua.ligacaoAguaPerfil.id "                      // 
				+ "  end, " //                                                      // 15 
				+ "  case when ( "                                                  // 
				+ "    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "   // 
				+ "    0 "                                                          // 
				+ "  else "                                                         // 
				+ "    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id "                  // 16
				+ "  end, "//                                                       // 
				+ " debitoacobrar.debitoCreditoSituacaoAtual.id, "                  // 17
				+ " debitoacobrar.quadra.rota.empresa.id, "                         // 18 = EMPRESA
				+ " imov.consumoTarifa.id, "                                        // 19 = CONSUMO TARIFA
				+ " debitoacobrar.quadra.rota.faturamentoGrupo.id, "                // 20 = GRUPO DE FATURAMENTO
				+"  case when ( (debitoacobrar.debitoCreditoSituacaoAtual.id = 0 or debitoacobrar.debitoCreditoSituacaoAnterior.id = 0) "
				+"               and (debitoacobrar.financiamentoTipo.id = 1) ) then " 
				+"       sum(debitoacobrar.valorDebito) end as valorIncluido , "    // 21 = VALOR FINANCIAMENTO INCLUIDOS 
				+"  0 , "                                                           // 22 = QUANTIDADE FINANCIAMENTO INCLUIDOS
				+"  case when ( (debitoacobrar.debitoCreditoSituacaoAtual.id = 3) and (debitoacobrar.financiamentoTipo.id = 1) ) then " 
                +"  sum( ( debitoacobrar.valorDebito -  (" +
                " trunc(( debitoacobrar.valorDebito / debitoacobrar.numeroPrestacaoDebito ),2) * " +
                " debitoacobrar.numeroPrestacaoCobradas" +
                " ))) end as valorCancelado  , "
				+"  0, "                                                            // 24 = QUANTIDADE FINANCIAMENTOS CANCELADOS
				+"  debitoacobrar.debitoTipo.id,  "                                 // 25 = DEBITO TIPO
				+"  imov.id, "                                                       // 26
				+"  rota.codigo, "// 27
				+"  debitoacobrar.financiamentoTipo.id, "// 28							
				
				+"  case when ( (debitoacobrar.debitoCreditoSituacaoAtual.id = 0 or debitoacobrar.debitoCreditoSituacaoAnterior.id = 0) "
				+"               and (debitoacobrar.parcelamentoGrupo.id = 6) ) then " 
				+"  sum(debitoacobrar.valorDebito) end as valorJurosParcelamento , "    // 29 VALOR JUROS PARCELAMENTO
				
				+"  case when ( (debitoacobrar.debitoCreditoSituacaoAtual.id = 3) and (debitoacobrar.parcelamentoGrupo.id is not null) ) then " 
				+"  sum( ( debitoacobrar.valorDebito -  (" 
                +" trunc(( debitoacobrar.valorDebito / debitoacobrar.numeroPrestacaoDebito ),2) * " 
                +" debitoacobrar.numeroPrestacaoCobradas" 
                +" ))) end as valorParcelamentoCancelado "							// 30 VALOR PARCELAMENTO CANCELADOS
				+"     "
				+" from "
				+"  gcom.faturamento.debito.DebitoACobrar debitoacobrar " 
				+"  inner join debitoacobrar.lancamentoItemContabil as lancamentoitemcontabil "
				+"  inner join debitoacobrar.imovel as imov "
				+"  inner join imov.localidade as loca "
				+"  left  join debitoacobrar.quadra.rota rota "
				+"  left  join imov.ligacaoAgua ligacaoAgua "
				+"  left  join imov.ligacaoEsgoto ligacaoEsgoto "
				+" where "
				+"  debitoacobrar.anoMesReferenciaContabil = :anoMes  and "
				+"  debitoacobrar.quadra.setorComercial.id = :idSetor and "
				/**TODO:COSANPA
				 * Data:12/10/2011
				 * Autor Adriana Muniz
				 * 
				 * Limitar que apenas imoveis não excluidos sejam retornados na consulta
				 * */
				+"  imov.indicadorExclusao = 2 "
				+"     "
				+"     "
				+" group by " 
				+"    debitoacobrar.lancamentoItemContabil.id, loca.gerenciaRegional.id, loca.unidadeNegocio.id, "                  
				+"    loca.localidade.id, loca.id, debitoacobrar.quadra.setorComercial.id,  "                                     
				+"    debitoacobrar.quadra.rota.id, debitoacobrar.quadra.id, debitoacobrar.codigoSetorComercial, "                                 
				+"    debitoacobrar.numeroQuadra, imov.imovelPerfil.id, imov.ligacaoAguaSituacao.id, "                                   
				+"    imov.ligacaoEsgotoSituacao.id, imov.ligacaoAgua.ligacaoAguaPerfil.id, "                                
				+"    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, debitoacobrar.debitoCreditoSituacaoAtual.id, "
				+"    debitoacobrar.debitoCreditoSituacaoAnterior.id, debitoacobrar.quadra.rota.empresa.id,   "
				+"    imov.consumoTarifa.id, debitoacobrar.quadra.rota.faturamentoGrupo.id, "
				+"    debitoacobrar.debitoTipo.id, imov.id, rota.codigo,debitoacobrar.financiamentoTipo.id,debitoacobrar.parcelamentoGrupo.id"
				+" order by " 
				+"    loca.gerenciaRegional.id, loca.unidadeNegocio.id, loca.localidade.id,  "                  
				+"    loca.id, debitoacobrar.quadra.setorComercial.id,  "                                     
				+"    debitoacobrar.quadra.rota.id, debitoacobrar.quadra.id, debitoacobrar.codigoSetorComercial, "                                 
				+"    debitoacobrar.numeroQuadra, imov.imovelPerfil.id, imov.ligacaoAguaSituacao.id, "                                   
				+"    imov.ligacaoEsgotoSituacao.id, imov.ligacaoAgua.ligacaoAguaPerfil.id, "                                
				+"    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, debitoacobrar.debitoCreditoSituacaoAtual.id, "
				+"    debitoacobrar.quadra.rota.empresa.id, imov.consumoTarifa.id, debitoacobrar.quadra.rota.faturamentoGrupo.id, "
				+"    debitoacobrar.debitoTipo.id,debitoacobrar.financiamentoTipo.id ";

			retorno = session.createQuery(hql).setInteger("anoMes",anoMes)
											  .setInteger("idSetor", idSetor).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 * 
	 * @author Roberto Barbalho
	 * @date 28/08/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getPesquisaImpostos(int idConta) throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql =  " select "
			      + "   cid.impostoTipo.id,  "
			      + "   cid.valorImposto "
			      + " from "
			      + "   gcom.faturamento.conta.ContaImpostosDeduzidos cid "
			      + " where cid.conta.id = :idConta ";
			
			retorno = session.createQuery(hql).setInteger("idConta", idConta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_faturamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoFaturamento()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resFat.referencia) "
					+ " FROM "
					+ " gcom.gerencial.faturamento.UnResumoFaturamento resFat ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

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
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_refaturamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoRefaturamento()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resRefat.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.faturamento.UnResumoRefaturamento resRefat ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

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
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_indicadores_faturamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadoresFaturamento()
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resIndFat.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.faturamento.UnResumoIndicadoresFaturamento resIndFat ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

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
	 * Atualiza os dados na tabela un_resumo_indicadores_faturamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosResumoIndicadoresFaturamento(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas) throws ErroRepositorioException {
		
		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {
			
			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO faturamento.un_res_ind_fat "
					+ " SELECT "
					+ " refa_amreferencia, refa_anoreferencia, refa_mesreferencia, greg_id, uneg_id, "
					+ " loca_id, loca_cdelo, stcm_id, qdra_id, rota_id, refa_cdsetorcomercial, refa_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, lapf_id, lepf_id, "
					+ " crog_id, lict_id, dotp_id, fntp_id, dbtp_id, crti_id, "
					+ " refa_qtcontasemitidas, rerf_qtcontasretificadas, rerf_qtcontascanceladas, rerf_qtcontasincluidas, "
					+ " refa_qteconomiasfaturadas, refa_vofaturadoagua, rerf_vocanceladoagua, rerf_voincluidoagua, "
					+ " refa_vofaturadoesgoto, rerf_vocanceladoesgoto, rerf_voincluidoesgoto, "
					+ " refa_vlfaturadoagua, rerf_vlcanceladoagua, rerf_vlincluidoagua, "
					+ " refa_vlfaturadoesgoto, rerf_vlcanceladoesgoto, rerf_vlincluidoesgoto, "
					+ " refa_vldocumentosfaturadoscred, rerf_vlcanceladocredito, rerf_vlincluidocredito, "
					+ " refa_vldocumentosfaturadosoutr, rerf_vlcanceladooutros, rerf_vlincluidooutros, "
					+ " refa_vlacrescimoimpontualidade, refa_qtcontasemitidasma, rerf_qtcontasretificadasma, "
					+ " rerf_qtcontascanceladasma, rerf_qtcontasincluidasma, refa_qteconomiasfaturadasma, "
					+ " refa_vofaturadoaguama, rerf_vocanceladoaguama, rerf_voincluidoaguama, "
					+ " refa_vofaturadoesgotoma, rerf_vocanceladoesgotoma, rerf_voincluidoesgotoma, "
					+ " refa_vlfaturadoaguama, rerf_vlcanceladoaguama, rerf_vlincluidoaguama, "
					+ " refa_vlfaturadoesgotoma, rerf_vlcanceladoesgotoma, rerf_vlincluidoesgotoma, "
					+ " refa_vldocumentosfaturadoscredma, rerf_vlcanceladocreditoma, rerf_vlincluidocreditoma, "
					+ " refa_vldocumentosfaturadosoutrma, rerf_vlcanceladooutrosma, rerf_vlincluidooutrosma, "
					+ " refa_vlacrescimoimpontualidadema, refa_vlarrastos, refa_vlparcelamento, "
					+ " rerf_qtguiascanceladas, "
					+ Util.obterNextValSequence("faturamento.seq_un_res_ind_fat") + ",  sysdate "
					+ " FROM faturamento.vw_un_res_ind_fat ";
			
			if (anoMesReferenciaIndicador != null) {
				consulta = consulta
						+ " WHERE refa_amreferencia > " + anoMesReferenciaIndicador + " and refa_amreferencia <= " + anoMesReferenciaTabelas;
			} else {
				consulta = consulta
				+ " WHERE refa_amreferencia <= " + anoMesReferenciaTabelas;
			}
			
//			consulta += "\n limit 1";
			System.out.println("inicio Batch atualizarDadosResumoIndicadoresFaturamento:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadoresFaturamento:" + new Date());

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
			}
		}

	}
	
	/**
	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
	 * faturamento (Guia de Pagamento)
	 * 
	 * @author Marcio Roberto
	 * @date 05/09/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getPesquisaGuiaPagamento(int idSetor, int anoMes)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String hql = " select " 
				+ " 1, "                                                            // 00  =  FINANCIAMENTO TIPO
				+ " 7, "                                                            // 01  =  DOCUMENTO TIPO
				+ " guiaPagamento.lancamentoItemContabil.id, "                      // 02  =  LANCAMENTO ITEM CONTABIL
				+ " loca.gerenciaRegional.id, "                                     // 03  =  GERENCIA REGIONAL
				+ " loca.unidadeNegocio.id, "                                       // 04  =  UNIDADE NEGOCIO
				+ " loca.localidade.id, " // elo                                    // 05  =  ELO
				+ " loca.id, "// idlocalidade                                       // 06  =  LOCALIDADE
				+ " setor.id, "                                						// 07  =  SETOR COMERCIAL
				+ " imov.quadra.rota.id, "                                          // 08  =  ROTA
				+ " imov.quadra.id, "                                               // 09  =  QUADRA
				+ " setor.codigo, "                           				 // 10  =  CODIGO SETOR COMERCIAL
				+ " imov.quadra.numeroQuadra, "                                     // 11  =  NUMERO QUADRA
				+ " imov.imovelPerfil.id, "                                         // 12  =  PERFIL DO IMOVEL
				+ " imov.ligacaoAguaSituacao.id, "                                  // 13  =  SITUACAO AGUA LIGACAO
				+ " imov.ligacaoEsgotoSituacao.id, "                                // 14  =  SITUACAO ESGOTO LIGACAO
				+ "  case when ( "                                                  // -------------------
				+ "    imov.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "       //   CHAVES DE QUEBRA!
				+ "    0 "                                                          // -------------------
				+ "  else "                                                         //   
				+ "    imov.ligacaoAgua.ligacaoAguaPerfil.id "                      // 
				+ "  end, " //                                                      // 15 = PERFIL LIGACAO AGUA
				+ "  case when ( "                                                  // 
				+ "    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "   // 
				+ "    0 "                                                          // 
				+ "  else "                                                         // 
				+ "    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id "                  // 16 = PERFIL LIGACAO ESGOTO
				+ "  end, "//                                                       //
				+ " guiaPagamento.debitoCreditoSituacaoAtual.id, "                  // 17
				+ " imov.quadra.rota.empresa.id, "                                  // 18 = EMPRESA
				+ " imov.consumoTarifa.id, "                                        // 19 = CONSUMO TARIFA
				+ " imov.quadra.rota.faturamentoGrupo.id, "                         // 20 = GRUPO DE FATURAMENTO
				+ " sum(guiaPagamento.valorDebito) as valordebitos, "               // 21 = VALOR DEBITO GUIA
				+ " count(guiaPagamento.id) as qtddocumentos, "                     // 22 = QUANTIDADE GUIA
				+ " guiaPagamento.debitoTipo.id,  "                                 // 23 = DEBITO TIPO
				+ " imov.id, "                                                       // 24 = IMOVEL 
				+"  rota.codigo "													//25 = codigo rota
				+"      "
				+" from "
				+" gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento "
				+" inner join guiaPagamento.lancamentoItemContabil as lancamentoitemcontabil "
				+" inner join guiaPagamento.imovel as imov "
				+" inner join imov.localidade as loca "
				+" inner join imov.setorComercial as setor "
				+" inner join imov.quadra.rota rota "
				+" left  join imov.ligacaoAgua ligacaoAgua "
				+" left  join imov.ligacaoEsgoto ligacaoEsgoto "
				+"       "
				+"       "
				+" where "
				+" guiaPagamento.anoMesReferenciaContabil = :anoMes  and "
				+" imov.quadra.setorComercial.id = :idSetor and "
				+" (guiaPagamento.debitoCreditoSituacaoAtual.id = 0 or guiaPagamento.debitoCreditoSituacaoAnterior.id = 0) "
				+"     "
				+"     "
				+" group by " 
				+"   guiaPagamento.lancamentoItemContabil.id, loca.gerenciaRegional.id, loca.unidadeNegocio.id, "                      
				+"   loca.localidade.id, loca.id, setor.id, imov.quadra.rota.id, "                                     
				+"   imov.quadra.id, setor.codigo, imov.quadra.numeroQuadra, imov.imovelPerfil.id, "                                               
				+"   imov.ligacaoAguaSituacao.id, imov.ligacaoEsgotoSituacao.id, "                                  
				+"   imov.ligacaoAgua.ligacaoAguaPerfil.id, imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "       
				+"   guiaPagamento.debitoCreditoSituacaoAtual.id, imov.quadra.rota.empresa.id, "                  
				+"   imov.consumoTarifa.id, imov.quadra.rota.faturamentoGrupo.id, guiaPagamento.debitoTipo.id, imov.id, rota.codigo "                                        
				+"     "
				+"     "
				+" order by " 
				+"   guiaPagamento.lancamentoItemContabil.id, loca.gerenciaRegional.id, loca.unidadeNegocio.id, "                      
				+"   loca.localidade.id, loca.id, setor.id, imov.quadra.rota.id, "                                     
				+"   imov.quadra.id, setor.codigo, imov.quadra.numeroQuadra, imov.imovelPerfil.id, "                                               
				+"   imov.ligacaoAguaSituacao.id, imov.ligacaoEsgotoSituacao.id, "                                  
				+"   imov.ligacaoAgua.ligacaoAguaPerfil.id, imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "       
				+"   guiaPagamento.debitoCreditoSituacaoAtual.id, imov.quadra.rota.empresa.id, "                  
				+"   imov.consumoTarifa.id, imov.quadra.rota.faturamentoGrupo.id, guiaPagamento.debitoTipo.id  ";                                        

			retorno = session.createQuery(hql).setInteger("anoMes",anoMes)
											  .setInteger("idSetor", idSetor).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
	 * Refaturamento (Guia de Pagamento)
	 * 
	 * @author Roberto Barbalho
	 * @date 05/11/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	 public List getPesquisaGuiaPagamentoRefaturamento(int idSetor, int anoMes) 
	        throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();
		try {


			String hql = "select " 
				+ " imov.id, "//0 
				+ " loca.gerenciaRegional.id, "//1 
				+ " loca.unidadeNegocio.id, "//2 
				+ " loca.localidade.id, " //3
				+ " loca.id, "//4
				+ " imov.quadra.setorComercial.id, "//5
				+ " imov.quadra.rota.id, "//6
				+ " imov.quadra.id, "//7
				+ " imov.quadra.setorComercial.codigo, "//8
				+ " imov.quadra.numeroQuadra, "//9
				+ " imov.imovelPerfil.id, "//10
				+ " imov.ligacaoAguaSituacao.id, "//11
				+ " imov.ligacaoEsgotoSituacao.id, "//12
				+ " case when ( "
				+ " imov.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "       
				+ " 0 "                                                          
				+ " else "                                                         
				+ " imov.ligacaoAgua.ligacaoAguaPerfil.id "//13                      
				+ " end, " 
				+ " case when ( "                                                  
				+ " imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "   
				+ "  0 "                                                          
				+ " else "                                                         
				+ "  imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id "//14
				+ " end, "
				+ " guiaPagamento.debitoCreditoSituacaoAtual.id, "//15
				+ " sum(guiaPagamento.valorDebito) as valordebitos, "//16
				+ " count(guiaPagamento.id) as qtddocumentos, "//17
				+ " imov.id "//18 
				+ " from "
				+ " gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento "
				+ " inner join guiaPagamento.imovel as imov "
				+ " inner join imov.localidade as loca "
				+ " left  join imov.ligacaoAgua ligacaoAgua "
				+ " left  join imov.ligacaoEsgoto ligacaoEsgoto "
				+ " where "
				+ " guiaPagamento.anoMesReferenciaContabil = :anoMes  and "
				+ " imov.quadra.setorComercial.id = :idSetor and "
				+ " (guiaPagamento.debitoCreditoSituacaoAtual.id = 3) "
				+ " group by " 
				+ "  loca.gerenciaRegional.id, loca.unidadeNegocio.id, "                      
				+ "  loca.localidade.id, loca.id, imov.quadra.setorComercial.id, imov.quadra.rota.id, "                                     
				+ "  imov.quadra.id, imov.quadra.setorComercial.codigo, imov.quadra.numeroQuadra, imov.imovelPerfil.id, "                                               
				+ "  imov.ligacaoAguaSituacao.id, imov.ligacaoEsgotoSituacao.id, "                                  
				+ "  imov.ligacaoAgua.ligacaoAguaPerfil.id, imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "       
				+ "  guiaPagamento.debitoCreditoSituacaoAtual.id, imov.id "                                        
				+ " order by " 
				+ "  loca.gerenciaRegional.id, loca.unidadeNegocio.id, "                      
				+ "  loca.localidade.id, loca.id, imov.quadra.setorComercial.id, imov.quadra.rota.id, "                                     
				+ "  imov.quadra.id, imov.quadra.setorComercial.codigo, imov.quadra.numeroQuadra, imov.imovelPerfil.id, "                                               
				+ "  imov.ligacaoAguaSituacao.id, imov.ligacaoEsgotoSituacao.id, "                                  
				+ "  imov.ligacaoAgua.ligacaoAguaPerfil.id, imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "       
				+ "  guiaPagamento.debitoCreditoSituacaoAtual.id, imov.id ";                                         

			retorno = session.createQuery(hql).setInteger("anoMes",anoMes)
											  .setInteger("idSetor", idSetor).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	  * getValorResultadoRetificacao
	  * 
	  * @author Roberto Barbalho
	  * @date 22/11/2007
	  * 
	  * @param 
	  * @return 
	  * @throws ErroRepositorioException
	  */
	 public List getValorAnteriorContaRetificada(int idImovel, int anoMesRef , int anoMes, int verif ) 
	 throws ErroRepositorioException {
	  

	  List retorno = null;
	  Session session = HibernateUtil.getSession();
	  try {
		  
		  String sqlDataSistemaParametro = " select parm_amreferenciafaturamento as col_0 from cadastro.sistema_parametros";
	    	 
	      Integer amFatur = (Integer) session.createSQLQuery(sqlDataSistemaParametro)
	      		.addScalar("col_0",Hibernate.INTEGER).setMaxResults(1).uniqueResult();

		  String sql = " select ";
			  
		     if (verif == 0) { //contas com dcst_idatual = 1 ou dcst_idanterior = 1 e com a conta com dcst_idatual = 4 respectiva
		    	 	
		    	 sql = sql + " sum(c4.cnta_vlagua - c1.cnta_vlagua) as col_0,  " //0 Agua 
	  			 + " sum(c4.cnta_vlesgoto - c1.cnta_vlesgoto) as col_1,  " //1 Esgoto
	  			 + " sum(c4.cnta_vldebitos - c1.cnta_vldebitos) as col_2,  " //2 Debitos
	  			 + " sum(c4.cnta_vlcreditos - c1.cnta_vlcreditos) as col_3,  " //3 Creditos
	  			 + " sum(c4.cnta_vlimpostos - c1.cnta_vlimpostos) as col_4,  " //4 Impostos
	  			 + " sum(c4.cnta_nnconsumoagua - c1.cnta_nnconsumoagua) as col_5,  " //5 Volume Agua
	  			 + " sum(c4.cnta_nnconsumoesgoto - c1.cnta_nnconsumoesgoto) as col_6 " //6 Volume Esgoto
	  			 + " from faturamento.conta c1, faturamento.conta c4 "
                 + " where c1.imov_id = c4.imov_id "
                 //+ " and c1.cnta_amreferenciacontabil = c4.cnta_amreferenciacontabil "
                 + " and c1.cnta_amreferenciaconta = c4.cnta_amreferenciaconta "
                 + " and c4.dcst_idatual = 4 "
                 + " and (c1.dcst_idatual = 1 or c1.dcst_idanterior = 1) "
                 + " and c4.cnta_amreferenciacontabil = :anoMes " //ano mes do sistema parametro
                 + " and c4.cnta_amreferenciaconta = :anoMesRef " //amreferenciaconta
                 + " and c4.imov_id = :idImovel ";
		    	 
		    	 retorno = session.createSQLQuery(sql)
				 .addScalar("col_0",Hibernate.BIG_DECIMAL)
	             .addScalar("col_1",Hibernate.BIG_DECIMAL)
	             .addScalar("col_2",Hibernate.BIG_DECIMAL)
	             .addScalar("col_3",Hibernate.BIG_DECIMAL)
	             .addScalar("col_4",Hibernate.BIG_DECIMAL)
	             .addScalar("col_5",Hibernate.INTEGER)
	             .addScalar("col_6",Hibernate.INTEGER)
				 .setInteger("idImovel",idImovel )
	             .setInteger("anoMesRef", anoMesRef)
	             .setInteger("anoMes",anoMes)
	             .list();
		    	 System.out.println("AnoMes 0: "+amFatur+"");
		    	 System.out.println("Imovel 0: "+idImovel+"");
  	           }
		     
	                   
	         if (verif == 1) {     //contas com dcst_idatual = 4 sem a conta com dcst_idatual = 1 ou dcst_idanterior = 1 respectiva
	        	 
	        	 sql = sql + " sum(c4.cnta_vlagua) as col_0,  " //0 Agua 
	  			 + " sum(c4.cnta_vlesgoto) as col_1,  " //1 Esgoto
	  			 + " sum(c4.cnta_vldebitos)  as col_2,  " //2 Debitos
	  			 + " sum(c4.cnta_vlcreditos) as col_3,  " //3 Creditos
	  			 + " sum(c4.cnta_vlimpostos)  as col_4,  " //4 Impostos
	  			 + " sum(c4.cnta_nnconsumoagua)  as col_5,  " //5 Volume Agua
	  			 + " sum(c4.cnta_nnconsumoesgoto) as col_6 " //6 Volume Esgoto
	  			 + " from faturamento.conta c4 "
                 + " where c4.imov_id = :idImovel "
                 + " and c4.cnta_amreferenciacontabil = :anoMes "
                 + " and c4.cnta_amreferenciaconta = :anoMesRef "
                 + " and (c4.dcst_idatual = 1 or c4.dcst_idanterior = 1)";
	        	 
	        	 retorno = session.createSQLQuery(sql)
				 .addScalar("col_0",Hibernate.BIG_DECIMAL)
	             .addScalar("col_1",Hibernate.BIG_DECIMAL)
	             .addScalar("col_2",Hibernate.BIG_DECIMAL)
	             .addScalar("col_3",Hibernate.BIG_DECIMAL)
	             .addScalar("col_4",Hibernate.BIG_DECIMAL)
	             .addScalar("col_5",Hibernate.INTEGER)
	             .addScalar("col_6",Hibernate.INTEGER)
				 .setInteger("idImovel",idImovel )
	             .setInteger("anoMesRef", anoMesRef )
	             .setInteger("anoMes",anoMes)
	             .list();
	        	 
	        	 System.out.println("AnoMes 1: "+anoMes+"");
		    	 System.out.println("Imovel 1: "+idImovel+"");
	        	 
	         }
	         
	         

			 
			 
	} catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} finally {
		HibernateUtil.closeSession(session);
	}
	return retorno;
}


	   /**
	     * [UC0571] - Gerar Resumo do Faturamento
	     *
	     * Pesquisamos as contas que serão usadas no resumo de contas
	     *
	     * @author Bruno Barros
	     * @date 18/09/2008
	     *
	     * @param idSetorComercial
	     * @throws ErroRepositorioException
	     */
	    public Collection<Object[]> pesquisarContasResumoFaturamento(Integer idSetor)
	            throws ErroRepositorioException {
	        Session session = HibernateUtil.getSession();
	        Collection retorno = null;
	        String consulta = null;
	        try {
	            consulta = "select "
	                    +
	                    // 0
	                    "  gere.id, "
	                    +
	                    // 1
	                    "  unNe.id, "
	                    +
	                    // 2
	                    "  loca.localidade.id, "
	                    +
	                    // 3
	                    "  loca.id, "
	                    +
	                    // 4            
	                    "  seCo.id, "
	                    +
	                    // 5
	                    "  cnta.codigoSetorComercial, "
	                    +
	                    // 6
	                    "  rot.id, "
	                    +
	                    // 7
	                    "  rot.codigo, "
	                    +
	                    // 8
	                    "  quad.id, "
	                    +
	                    // 9
	                    "  quad.numeroQuadra, "
	                    +
	                    // 10
	                    "  imPe.id, "
	                    +
	                    // 11
	                    "  las.id, "
	                    +
	                    // 12
	                    "  les.id, "
	                    +
	                    // 13
	                    "  lap.id, "
	                    +
	                    // 14
	                    "  lep.id, "
	                    +
	                    // 15
	                    "  conTar.id, "
	                    +
	                    // 16
	                    "  fatGru.id, "
	                    +
	                    // 17
	                    "  empr.id, "
	                    +
	                    // 18
	                    "  cnta.valorAgua, "
	                    +
	                    // 19
	                    "  cnta.valorEsgoto, "
	                    +
	                    // 20
	                    "  imo.quantidadeEconomias, "
	                    +
	                    // 21
	                    "  imo.id, "
	                    +
	                    // 22
	                    "  cnta.consumoAgua, "
	                    +
	                    // 23
	                    "  cnta.debitos, "
	                    +
	                    // 24
	                    "  cnta.valorImposto, "
	                    +
	                    // 25
	                    "  cnta.valorCreditos, "
	                    +
	                    // 26
	                    "  cnta.id, "
	                    +
	                    // 27
	                    "  cnta.consumoEsgoto "                    
	                    + "from "
	                    + "  Conta cnta "
	                    + "  left join cnta.localidade loca "
	                    + "  left join loca.localidade elo "
	                    + "  left join loca.gerenciaRegional gere "
	                    + "  left join loca.unidadeNegocio unNe "
	                    + "  left join cnta.quadraConta quad "
	                    + "  left join quad.setorComercial seCo "
	                    + "  left join quad.rota rot "
	                    + "  left join cnta.imovelPerfil imPe "
	                    + "  left join cnta.ligacaoAguaSituacao las "
	                    + "  left join cnta.ligacaoEsgotoSituacao les "
	                    + "  left join cnta.imovel imo "
	                    + "  left join imo.ligacaoAgua liAg "
	                    + "  left join liAg.ligacaoAguaPerfil lap "
	                    + "  left join imo.ligacaoEsgoto liEs "
	                    + "  left join liEs.ligacaoEsgotoPerfil lep "
	                    + "  left join cnta.consumoTarifa conTar "
	                    + "  left join rot.faturamentoGrupo fatGru "
	                    + "  left join rot.empresa empr, "
	                    + "  SistemaParametro sp "
	                    + "where "
	                    + "  sp.anoMesFaturamento = cnta.referencia and "
	                    + "  ( cnta.debitoCreditoSituacaoAtual = 0 or cnta.debitoCreditoSituacaoAnterior = 0 ) and "
	                    + "  seCo.id = :idSetor";
	            retorno = session.createQuery(consulta).setInteger("idSetor",
	                    idSetor).list();
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
	     * [UC0571] - Gerar Resumo do Faturamento
	     *
	     * Pesquisamos as guias que serão usadas no resumo de guias de pagamento
	     *
	     * @author Bruno Barros
	     * @date 20/08/2008
	     *
	     * @param idSetorComercial
	     * @throws ErroRepositorioException
	     */
	    public Collection<Object[]> pesquisarGuiasResumoFaturamento(Integer idSetor)
	            throws ErroRepositorioException {
	        Session session = HibernateUtil.getSession();
	        Collection retorno = null;
	        String consulta = null;
	        
	        String filtrarPor = null;
	        
	        if ( idSetor == 99999 ){
	        	filtrarPor = "  imo is null ";
	        } else {
	        	filtrarPor = "  setCom.id = :idSetor ";
	        }
	        
	        try {
	            consulta = 
	                "select " +
	                // 0
	                "  geRe.id, " +
	                // 1
	                "  unNe.id, " +
	                // 2
	                "  elo.id, " +
	                // 3
	                "  loca.id, " +
	                // 4
	                "  setCom.id, " +
	                // 5
	                "  setCom.codigo, " +
	                // 6
	                "  rot.id, " +
	                // 7
	                "  rot.codigo, " +
	                // 8
	                "  qua.id, " +
	                // 9                
	                "  qua.numeroQuadra, " +
	                // 10
	                "  case when ( imo is not null ) then imoPer.id else 5 end, " +
	                // 11
	                "  case when ( imo is not null ) then las.id else 1 end, " +
	                // 12
	                "  case when ( imo is not null ) then les.id else 1 end, " +
	                // 13
	                "  case when ( imo is not null ) then pla.id else 0 end, " +
	                // 14
	                "  case when ( imo is not null ) then ple.id else 0 end, " +
	                // 15
	                "  case when ( imo is not null ) then conTar.id else 0 end, " +
	                // 16
	                "  lic.id, " +
	                // 17 
	                "  finTip.id, " +
	                // 18
	                "  debTip.id, " +
	                // 19
	                "  imo.id, " +
	                // 20
	                " esfPod.id, " +
	                // 21
	                " cliTip.id, " +      
	                // 22
	                "  fatGru.id, " +
	                // 23
	                "  empr.id, " +       
	                // 24
	                "  guia.valorDebito, " +
	                //25
	                " guia.id " +
	                "from " +
	                "  GuiaPagamento guia " +
	                "  left join guia.localidade loca " +
	                "  left join loca.gerenciaRegional geRe " +
	                "  left join loca.unidadeNegocio unNe " +
	                "  left join loca.localidade elo " +
	                "  left join guia.imovel imo " +
	                "  left join imo.setorComercial setCom " +
	                "  left join imo.quadra qua " +
	                "  left join qua.rota rot " +
	                "  left join imo.imovelPerfil imoPer " +
	                "  left join imo.ligacaoAguaSituacao las " +
	                "  left join imo.ligacaoEsgotoSituacao les " +
	                "  left join imo.ligacaoAgua la " +
	                "  left join la.ligacaoAguaPerfil pla " +
	                "  left join imo.ligacaoAgua le " +
	                "  left join la.ligacaoAguaPerfil ple " +
	                "  left join imo.consumoTarifa conTar " +
	                "  left join guia.lancamentoItemContabil lic " +
	                "  left join guia.financiamentoTipo finTip " +
	                "  left join guia.debitoTipo debTip " +
	                "  left join guia.cliente clie " +
	                "  left join clie.clienteTipo cliTip " +
	                "  left join cliTip.esferaPoder esfPod " +
	                "  left join rot.faturamentoGrupo fatGru " +
	                "  left join rot.empresa empr, " +
	                "  SistemaParametro sp " +
	                "where " +
	                "  guia.anoMesReferenciaContabil = sp.anoMesFaturamento and " +
	                "  (guia.debitoCreditoSituacaoAtual.id = 0 or guia.debitoCreditoSituacaoAnterior.id = 0) and " +
	                filtrarPor +
	                "order by " +
	                "  guia.id ";               
	            
		        if ( idSetor == 99999 ){
		        	retorno = session.createQuery(consulta).list();		        	
		        } else {
		        	retorno = session.createQuery(consulta).setInteger("idSetor",
		                    idSetor).list();		        	
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
	     * [UC0571] - Gerar Resumo do Faturamento
	     *
	     * Pesquisamos os debitos cobrados a serem usados no resumo de financiamento
	     *
	     * @author Bruno Barros
	     * @date 25/08/2008
	     * @alteracao: Ivan Sergio - 18/01/2011 - Ver alteracao do UC referente a data 03/01/2011 (Nelson C.)
	     *
	     * @param idSetorComercial
	     * @throws ErroRepositorioException
	     */
	    public Collection<Object[]> pesquisarFinanciamento(Integer idSetor)
	            throws ErroRepositorioException {
	        Session session = HibernateUtil.getSession();
	        Collection retorno = null;
	        String consulta = null;
	        try {
	            consulta = 
	                "select " +
	                // 0
	                "  ger.id, " +
	                // 1
	                "  unNe.id, " +
	                // 2
	                "  elo.id, " +
	                // 3
	                "  loc.id, " +
	                // 4
	                "  setCom.id, " +
	                // 5
	                "  setCom.codigo, " +
	                // 6
	                "  rot.id, " +
	                // 7
	                "  rot.codigo, " +              
	                // 8
	                "  qua.id, " +
	                // 9
	                "  qua.numeroQuadra, " +
	                // 10
	                "  imoPer.id, " +
	                // 11
	                "  las.id, " +
	                // 12
	                "  les.id, " +
	                // 13
	                "  lap.id, " +
	                // 14
	                "  lep.id, " +
	                // 15
	                "  conTar.id, " +
	                // 16
	                "  fatGru.id, " +
	                // 17
	                "  empLei.id, " +
	                // 18
	                "  lic.id, " +
	                // 19                
	                "  debTip.id, " +
	                // 20
	                "  imo.id, " +
	                // 21
	                "  dac.valorDebito, " +
	                // 22
	                "  dac.debitoCreditoSituacaoAtual.id, " +
	                // 23
	                "  dac.debitoCreditoSituacaoAnterior.id, " +
	                // 24
	                "  dac.numeroPrestacaoDebito, " +
	                // 25
	                "  dac.numeroPrestacaoCobradas, " +
	                // 26
	                "  finTip.id, " +
	                // 27
	                "  dac.parcelamentoGrupo.id, " +
	                // 28
	                "  dac.id " +
	                " from " +
	                "  DebitoACobrar dac " +
	                "  left join dac.localidade loc " +
	                "  left join loc.gerenciaRegional ger " +
	                "  left join loc.unidadeNegocio unNe " +
	                "  left join loc.localidade elo " +
	                "  left join dac.imovel imo " +
	                "  left join imo.setorComercial setCom " +
	                "  left join dac.quadra qua " +
	                "  left join qua.rota rot " +
	                "  left join imo.imovelPerfil imoPer " +
	                "  left join imo.ligacaoAguaSituacao las " +
	                "  left join imo.ligacaoEsgotoSituacao les " +
	                "  left join imo.ligacaoAgua la " +
	                "  left join la.ligacaoAguaPerfil lap " +
	                "  left join imo.ligacaoEsgoto le " +
	                "  left join le.ligacaoEsgotoPerfil lep " +
	                "  left join imo.consumoTarifa conTar " +
	                "  left join rot.faturamentoGrupo fatGru " +
	                "  left join rot.empresa empLei " +
	                "  left join dac.lancamentoItemContabil lic " +
	                "  left join dac.debitoTipo debTip " +
	                "  left join dac.financiamentoTipo finTip, " +
	                "  SistemaParametro sp " +
	                "where " +
	                "  dac.anoMesReferenciaContabil = sp.anoMesFaturamento and " +
	                "  setCom.id = :idSetor";
	            
	            retorno = session.createQuery(consulta).setInteger("idSetor",
	                    idSetor).list();
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
	     * [UC0571] - Gerar Resumo do Faturamento
	     *
	     * Inserir os dados na tabela un_resumo_faturamento
	     *
	     * @author Bruno Barros
	     * @date 27/08/2008
	     *
	     * @param helper Helper de agrupamento dos dados
	     * @throws ErroRepositorioException
	     */
	    public void inserirResumoFaturamento( ResumoFaturamentoHelper helper )
	        throws ErroRepositorioException{
	        Connection con = null;
	        Statement stmt = null;
	        Session session = HibernateUtil.getSessionGerencial();
	        String insert;
	        try {
	        	
	        	if (helper.getIdSubcategoria() == null){
	        		helper.setIdSubcategoria(0);
	        	}
	        	
	            con = session.connection();
	            stmt = con.createStatement();
	            insert = 
	                "INSERT INTO \n" +
	                "  faturamento.un_resumo_faturamento ( \n" +
	                // ID
	                "    refa_id," +
	                // 0
	                "    refa_amreferencia,\n" +
	                // 1
	                "    greg_id,\n" +
	                // 2
	                "    uneg_id,\n" +
	                // 3
	                "    loca_id,\n" +
	                // 4
	                "    loca_cdelo,\n" +
	                // 5
	                "    stcm_id,\n" +
	                // 6
	                "    rota_id,\n" +
	                // 7
	                "    qdra_id,\n" +
	                // 8
	                "    refa_cdsetorcomercial,\n" +
	                // 9
	                "    refa_nnquadra,\n" +
	                // 10
	                "    iper_id,\n" +
	                // 11
	                "    last_id,\n" +
	                // 12
	                "    lest_id,\n" +
	                // 13
	                "    catg_id,\n" +
	                // 14
	                "    scat_id,\n" +
	                // 15
	                "    epod_id,\n" +
	                // 16
	                "    cltp_id,\n" +
	                // 17
	                "    lapf_id,\n" +
	                // 18
	                "    lepf_id,\n" +
	                // 19
	                "    refa_vofaturadoagua,\n" +
	                // 20
	                "    refa_vofaturadoesgoto,\n" +
	                // 21
	                "    refa_vlfaturadoagua,\n" +
	                // 22
	                "    refa_vlfaturadoesgoto,\n" +
	                // 23
	                "    refa_qtcontasemitidas,\n" +
	                // 24
	                "    crog_id,\n" +
	                // 25
	                "    lict_id,\n" +
	                // 26
	                "    dotp_id,\n" +
	                // 27                
	                "    fntp_id,\n" +
	                // 28
	                "    refa_vldocsfaturadoscredito,\n" +
	                // 29
	                "    refa_qtdocsfaturadoscredito,\n" +
	                // 30
	                "    refa_vldocsfaturadosoutros,\n" +
	                // 31
	                "    refa_qtdocsfaturadosoutros,\n" +
	                // 32
	                "    empr_id,\n" +
	                // 33                
	                "    refa_vlfinanincluido,\n" +
	                // 34
	                "    refa_vlfinancancelado,\n" +
	                // 35
	                "    refa_qteconomiasfaturadas,\n" +
	                // 36
	                "    dbtp_id,\n" +
	                // 37
	                "    crti_id,\n" +
	                // 38
	                "    refa_ichidrometro,\n" +
	                // 39
	                "    imtp_id,\n" +
	                // 40
	                "    refa_vlimposto,\n" +
	                // 41
	                "    cstf_id,\n" +
	                // 42
	                "    ftgr_id,\n" +
	                // 43
	                "    refa_cdrota,\n" +
	                // 44
	                "	 REFA_VLJUROSPARCELAMENTO,\n" +
	                // 45
	                "	 REFA_VLCREDITOSCOBINDEVCAN,\n" +
	                // 46
	                "    REFA_VLDESCINCONDCAN,\n" +
	                // 47
	                "	 REFA_VLGUIADEVOLCANCEL,\n" +
	                // 48
	                "	 REFA_VLPARCELAMENTOSCAN,\n" +
	                // 49
	                "	 REFA_VLCREDITOSCOBINDEVINC,\n" +
	                // 50
	                "	 REFA_VLDESCINCONDINC,\n" +
	                // 51
	                "	 REFA_VLGUIADEVOLINCL )\n" +
	                "  VALUES (\n" +
	                   Util.obterNextValSequence("faturamento.seq_un_resumo_faturamento") +", " +
	                   // 0
	                   helper.getAnoMesFaturamento() + ", \n" +
	                   // 1
	                   helper.getIdGerenciaRegional() + ", \n" +
	                   // 2
	                   helper.getIdUnidadeNegocio() + ", \n" +
	                   // 3
	                   helper.getIdLocalidade() + ", \n" +
	                   // 4
	                   helper.getCdElo() + ", \n" +
	                   // 5
	                   helper.getIdSetorComercial() + ", \n" +                   
	                   // 6
	                   helper.getIdRota() + ", \n" +
	                   // 7
	                   helper.getIdQuadra() + ", \n" +
	                   // 8
	                   helper.getCdSetorComercial() + ", \n" +
	                   // 9
	                   helper.getNmQuadra() + ", \n" +
	                   // 10
	                   helper.getIdPerfilImovel() + ", \n" +
	                   // 11
	                   helper.getSituacaoLigacaoAgua() + ", \n" +
	                   // 12
	                   helper.getSituacaoLigacaoEsgoto() + ", \n" +
	                   // 13
	                   helper.getIdCategoria() + ", \n" +
	                   // 14
	                   helper.getIdSubcategoria() + ", \n" +
	                   // 15
	                   helper.getIdEsferaPoder() + ", \n" +
	                   // 16
	                   helper.getIdTipoCliente() + ", \n" +
	                   // 17
	                   helper.getIdPerfilLigacaoAgua() + ", \n" +
	                   // 18
	                   helper.getIdPerfilLigacaoEsgoto() + ", \n" +
	                   // 19
	                   helper.getVolumeAgua() + ", \n" +
	                   // 20
	                   helper.getVolumeEsgoto() + ", \n" +
	                   // 21
	                   helper.getValorAgua() + ", \n" +
	                   // 22
	                   helper.getValorEsgoto() + ", \n" +
	                   // 23
	                   helper.getQuantidadeContasEmitidas() + ", \n" +
	                   // 24
	                   helper.getIdOrigemCredito() + ", \n" +
	                   // 25
	                   helper.getIdItemLancamentoContabil() + ", \n" +
	                   // 26
	                   helper.getIdTipoDocumento() + ", \n" +
	                   // 27
	                   helper.getIdTipoFinanciamento() + ", \n" +
	                   // 28
	                   helper.getValorCreditoRealizado() + ", \n" +
	                   // 29
	                   helper.getQuantidadeDocumentosFaturadosCredito() + ", \n" +
	                   // 30
	                   helper.getValorDocumentosFaturadosOutros() + ", \n" +
	                   // 31
	                   helper.getQuantidadeDocumentosFaturadosOutros() + ", \n" +
	                   // 32
	                   helper.getIdEmpresa() + ", \n" +
	                   // 33
	                   helper.getValorFinanciadoIncluido() + ", \n" +
	                   // 34
	                   helper.getValorFinanciadoCancelado() + ", \n" +
	                   // 35
	                   helper.getQuantidadeEconomiasFaturadas() + ", \n" +
	                   // 36
	                   helper.getIdTipoDebito() + ", \n" +
                       // 37
                       helper.getIdTipoCredito() + ", \n" +                       
	                   // 38
	                   helper.getIndHidrometro() + ", \n" +
	                   // 39
	                   helper.getIdTipoImposto() + ", \n" +
	                   // 40
	                   helper.getValorImposto() + ", \n" +
	                   // 41
	                   helper.getIdTarifaConsumo() + ", \n" +
	                   // 42
	                   helper.getIdGrupoFaturamento() + ", \n" +
	                   // 43
	                   helper.getCdRota() + ", \n" +
	                   // 44
	                   helper.getValorJurosParcelamento() + ", \n" +
	                   // 45
	                   helper.getValorCreditosARealizarCobrancasIndevidasCancelados() + ", \n" +
	                   // 46
	                   helper.getValorDescontosIncondicionaisCancelados() + ", \n" +
	                   // 47
	                   helper.getValorGuiaDevolucaoCobradosIndevidamenteCancelados() + ", \n" +
	                   // 48
	                   helper.getValorParcelamentosCancelados() + ", \n" +
	                   // 49
	                   helper.getValorCreditosARealizarCobrancasIndevidasIncluidos() + ", \n" +
	                   // 50
	                   helper.getValorDescontosIncondicionaisIncluidos() + ", \n" +
	                   // 51
	                   helper.getValorGuiaDevolucaoCobradosIndevidamenteIncluidos() + ") \n";
	            
	            stmt.executeUpdate(insert);
	        } catch (HibernateException e) {
	            // levanta a exceção para a próxima camada
	            throw new ErroRepositorioException(e, "Erro no Hibernate");
	        } catch (SQLException e) {
	            // levanta a exceção para a próxima camada
	            throw new ErroRepositorioException(e, "Erro no Insert");
	        } finally {
	            
	            // fecha a sessão
	            HibernateUtil.closeSession(session);
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException e) {
	                System.out.println("Erro ao fechar conexões");
	            }
	        }
	    }
        
        /**
         * [UC0572] - Gerar Resumo do ReFaturamento
         *
         * Pesquisamos as contas que serão usadas no resumo de contas
         *
         * @author Bruno Barros
         * @date 24/11/2008
         *
         * @param idSetorComercial
         * @throws ErroRepositorioException
         */
        public Collection<Object[]> pesquisarContasResumoReFaturamentoOlap(Integer idSetor)
                throws ErroRepositorioException {
            Session session = HibernateUtil.getSession();
            Collection retorno = null;
            String consulta = null;
            try {
                consulta = "select "
                    +
                    // 0
                    "  gere.id, "
                    +
                    // 1
                    "  unNe.id, "
                    +
                    // 2
                    "  loca.localidade.id, "
                    +
                    // 3
                    "  loca.id, "
                    +
                    // 4            
                    "  seCo.id, "
                    +
                    // 5
                    "  cnta.codigoSetorComercial, "
                    +
                    // 6
                    "  rot.id, "
                    +
                    // 7
                    "  rot.codigo, "
                    +
                    // 8
                    "  quad.id, "
                    +
                    // 9
                    "  quad.numeroQuadra, "
                    +
                    // 10
                    "  imPe.id, "
                    +
                    // 11
                    "  las.id, "
                    +
                    // 12
                    "  les.id, "
                    +
                    // 13
                    "  lap.id, "
                    +
                    // 14
                    "  lep.id, "
                    +
                    // 15
                    "  conTar.id, "
                    +
                    // 16
                    "  fatGru.id, "
                    +
                    // 17
                    "  empr.id, "
                    +
                    // 18
                    "  cnta.valorAgua, "
                    +
                    // 19
                    "  cnta.valorEsgoto, "
                    +
                    // 20
                    "  imo.quantidadeEconomias, "
                    +
                    // 21
                    "  imo.id, "
                    +
                    // 22
                    "  cnta.consumoAgua, "
                    +
                    // 23
                    "  cnta.debitos, "
                    +
                    // 24
                    "  cnta.valorImposto, "
                    +
                    // 25
                    "  cnta.valorCreditos, "
                    +
                    // 26
                    "  cnta.id, "
                    +
                    // 27
                    "  cnta.consumoEsgoto, "
                    +
                    // 28
                    "  cnta.debitoCreditoSituacaoAtual.id, "
                    +
                    // 29
                    "  cnta.debitoCreditoSituacaoAnterior.id "
                    + "from "
                    + "  Conta cnta "
                    + "  left join cnta.localidade loca "
                    + "  left join loca.localidade elo "
                    + "  left join loca.gerenciaRegional gere "
                    + "  left join loca.unidadeNegocio unNe "
                    + "  left join cnta.quadraConta quad "
                    + "  left join quad.setorComercial seCo "
                    + "  left join quad.rota rot "
                    + "  left join cnta.imovelPerfil imPe "
                    + "  left join cnta.ligacaoAguaSituacao las "
                    + "  left join cnta.ligacaoEsgotoSituacao les "
                    + "  left join cnta.imovel imo "
                    + "  left join imo.ligacaoAgua liAg "
                    + "  left join liAg.ligacaoAguaPerfil lap "
                    + "  left join imo.ligacaoEsgoto liEs "
                    + "  left join liEs.ligacaoEsgotoPerfil lep "
                    + "  left join cnta.consumoTarifa conTar "
                    + "  left join rot.faturamentoGrupo fatGru "
                    + "  SistemaParametro sp "
                    + "where "
                    + "  sp.anoMesFaturamento = cnta.referencia and "
                    + "  ( cnta.debitoCreditoSituacaoAtual.id in( 1,2,3,4 ) or cnta.debitoCreditoSituacaoAnterior.id = 1 ) and "
                    + "  seCo.id = :idSetor";                
                retorno = session.createQuery(consulta).setInteger("idSetor",
                        idSetor).list();
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
         * [UC0572] - Gerar Resumo do ReFaturamento
         *
         * Pesquisamos a conta com status de cancelada por retificação
         *
         * @author Bruno Barros
         * @date 24/11/2008
         *
         * @param idImovel
         * @throws ErroRepositorioException
         */
        public Collection<Object[]> pesquisarContaCanceladaRetificacao(Integer idImovel)
                throws ErroRepositorioException {
            Session session = HibernateUtil.getSession();
            Collection retorno = null;
            String consulta = null;
            try {
                consulta = "select "
                    +
                    // 0
                    "  cnta.valorAgua, "
                    +
                    // 1
                    "  cnta.valorEsgoto, "
                    +
                    // 2
                    "  cnta.debitos, "
                    +                    
                    // 3
                    "  cnta.valorCreditos, "
                    +                    
                    // 4
                    "  cnta.valorImposto, "
                    +
                    // 5
                    "  cnta.consumoAgua, "
                    +
                    // 6
                    "  cnta.consumoEsgoto, "
                    + "from "
                    + "  Conta cnta "
                    + "  SistemaParametro sp "
                    + "where "
                    + "  sp.anoMesFaturamento = cnta.referenciaContabil and "
                    + "  sp.anoMesFaturamento = cnta.referencia and "                    
                    + "  cnta.debitoCreditoSituacaoAtual.id = 4 and "
                    + "  cnta.imovel.id = :idImovel ";      
                
                retorno = session.createQuery(consulta).setInteger("idImovel",
                        idImovel).setMaxResults(1).list();
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
         * [UC0572] - Gerar Resumo do ReFaturamento
         *
         * Pesquisamos a conta com status de retificada
         *
         * @author Bruno Barros
         * @date 26/11/2008
         *
         * @param idImovel
         * @throws ErroRepositorioException
         */
        public Collection<Object[]> pesquisarContaRetificada(Integer idImovel)
                throws ErroRepositorioException {
            Session session = HibernateUtil.getSession();
            Collection retorno = null;
            String consulta = null;
            try {
                consulta = "select "
                    + "  count(*)"
                    + "from "
                    + "  Conta cnta "
                    + "  SistemaParametro sp "
                    + "where "
                    + "  sp.anoMesFaturamento = cnta.referenciaContabil and "
                    + "  sp.anoMesFaturamento = cnta.referencia and "                    
                    + "  ( cnta.debitoCreditoSituacaoAtual.id = 1 or cnta.debitoCreditoSituacaoAnterior.id = 1 ) and "
                    + "  cnta.imovel.id = :idImovel ";      
                
                retorno = session.createQuery(consulta).setInteger("idImovel",
                        idImovel).setMaxResults(1).list();
            } catch (HibernateException e) {
                // levanta a exceção para a próxima camada
                throw new ErroRepositorioException(e, "Erro no Hibernate");
            } finally {
                // fecha a sessão
                HibernateUtil.closeSession(session);
            }
            return retorno;
        }
        
//        public static void main(String[] args) {
//			String 	consulta = "INSERT INTO faturamento.un_resumo_indicadores_faturamento "
//				+ " SELECT "
//				+ " refa_amreferencia, refa_anoreferencia, refa_mesreferencia, greg_id, uneg_id, "
//				+ " loca_id, loca_cdelo, stcm_id, qdra_id, rota_id, refa_cdsetorcomercial, refa_nnquadra, "
//				+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, lapf_id, lepf_id, "
//				+ " crog_id, lict_id, dotp_id, fntp_id, dbtp_id, crti_id, "
//				+ " refa_qtcontasemitidas, rerf_qtcontasretificadas, rerf_qtcontascanceladas, rerf_qtcontasincluidas, "
//				+ " refa_qteconomiasfaturadas, refa_vofaturadoagua, rerf_vocanceladoagua, rerf_voincluidoagua, "
//				+ " refa_vofaturadoesgoto, rerf_vocanceladoesgoto, rerf_voincluidoesgoto, "
//				+ " refa_vlfaturadoagua, rerf_vlcanceladoagua, rerf_vlincluidoagua, "
//				+ " refa_vlfaturadoesgoto, rerf_vlcanceladoesgoto, rerf_vlincluidoesgoto, "
//				+ " refa_vldocumentosfaturadoscredito, rerf_vlcanceladocredito, rerf_vlincluidocredito, "
//				+ " refa_vldocumentosfaturadosoutros, rerf_vlcanceladooutros, rerf_vlincluidooutros, "
//				+ " refa_vlacrescimoimpontualidade, refa_qtcontasemitidasma, rerf_qtcontasretificadasma, "
//				+ " rerf_qtcontascanceladasma, rerf_qtcontasincluidasma, refa_qteconomiasfaturadasma, "
//				+ " refa_vofaturadoaguama, rerf_vocanceladoaguama, rerf_voincluidoaguama, "
//				+ " refa_vofaturadoesgotoma, rerf_vocanceladoesgotoma, rerf_voincluidoesgotoma, "
//				+ " refa_vlfaturadoaguama, rerf_vlcanceladoaguama, rerf_vlincluidoaguama, "
//				+ " refa_vlfaturadoesgotoma, rerf_vlcanceladoesgotoma, rerf_vlincluidoesgotoma, "
//				+ " refa_vldocumentosfaturadoscreditoma, rerf_vlcanceladocreditoma, rerf_vlincluidocreditoma, "
//				+ " refa_vldocumentosfaturadosoutrosma, rerf_vlcanceladooutrosma, rerf_vlincluidooutrosma, "
//				+ " refa_vlacrescimoimpontualidadema, refa_vlarrastos, refa_vlparcelamento, "
//				+ " rerf_qtguiascanceladas, "
//				+ " nextval('faturamento.sequence_un_resumo_indicadores_faturamento'), now() "
//				+ " FROM faturamento.vw_un_resumo_indicadores_faturamento ";
//		
//		if (true) {
//			consulta = consulta
//					+ " WHERE refa_amreferencia > 200930 and refa_amreferencia <= 2009320 ";
//		} else {
//			consulta = consulta
//			+ " WHERE refa_amreferencia <= 2009320";
//		}
//
//		System.out.println(consulta);
//
//        }
        
        public Map totalizadorRelatorioDemonstrativoSinteticoLigacoes(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro){
        	Map retorno = new HashMap();

        	//colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Municipio/ Localidade

            retorno.put("gerenciaRegionalID", "sum(0) ");
            retorno.put("gerenciaRegional",   "sum(0) ");
            retorno.put("unidadeNegocioID",   "sum(0) ");
            retorno.put("unidadeNegocio",     "sum(0) ");
            retorno.put("localidadeID",       "sum(0) ");
            retorno.put("localidade",         "sum(0) ");
            retorno.put("municipioID",        "sum(0) ");
            retorno.put("municipio",          "sum(0) ");
			
            retorno.put("join",    "");
            retorno.put("where",   "");
            retorno.put("orderBy", "");
            retorno.put("groupBy", "");

            int opcaoTotalozacao = filtro.getOpcaoTotalizacao();
            switch(opcaoTotalozacao){
               //Estado por Gerencia Regional
               case 2:
            	   retorno.put("gerenciaRegionalID", "greg.greg_id ");
                   retorno.put("gerenciaRegional", "greg.greg_nmregional ");

                   retorno.put("join", "right join cadastro.g_gerencia_regional greg on (greg.greg_id = r.greg_id) ");
                   
                   retorno.put("join2", " ");
                   
                   retorno.put("where", "where greg.greg_id != 0 ");
                   
                   retorno.put("groupBy", "group by greg.greg_id, greg.greg_nmregional ");
                   
                   retorno.put("tipo", "totalizadorPorEstado");

                   retorno.put("orderBy", "order by greg.greg_id ");
               break;
               //Estado por Unidade de Negocio
               case 3:
            	   retorno.put("gerenciaRegionalID", "greg.greg_id ");
                   retorno.put("gerenciaRegional", "greg.greg_nmregional ");
            	   retorno.put("unidadeNegocioID", "uneg.uneg_id ");
            	   retorno.put("unidadeNegocio", "uneg.uneg_nmunidadenegocio ");

            	   retorno.put("join", "right join cadastro.g_unidade_negocio uneg on (uneg.uneg_id = r.uneg_id) ");
            	   
            	   retorno.put("join2", "inner join cadastro.g_gerencia_regional greg on (greg.greg_id = uneg.greg_id) ");
            	   
				   retorno.put("where", "where uneg.uneg_id != 0 ");

				   retorno.put("tipo", "totalizadorPorEstado");

            	   retorno.put("groupBy", "group by greg.greg_id, greg.greg_nmregional, " +
            	   		                           "uneg.uneg_id, uneg.uneg_nmunidadenegocio ");

            	   retorno.put("orderBy", "order by greg.greg_id, uneg.uneg_id ");
               break;
               //Estado por Centro Custo/Localidade
               case 4:
            	   retorno.put("gerenciaRegionalID", "greg.greg_id ");
                   retorno.put("gerenciaRegional", "greg.greg_nmregional ");                   
            	   retorno.put("unidadeNegocioID", "uneg.uneg_id ");
            	   retorno.put("unidadeNegocio", "uneg.uneg_nmunidadenegocio ");            	   
            	   retorno.put("localidadeID", "loca.loca_cdcentrocusto ");


                   retorno.put("join", "right join cadastro.g_localidade loca on (loca.loca_id = r.loca_id) ");

                   retorno.put("join", "right join cadastro.g_localidade loca on (loca.loca_id = r.loca_id) ");
            	   
            	   retorno.put("join2","inner join cadastro.g_gerencia_regional greg on (greg.greg_id = loca.greg_id) " +
	   		                           "inner join cadastro.g_unidade_negocio uneg on (uneg.uneg_id = loca.uneg_id)" );

				   retorno.put("tipo", "totalizadorPorEstado");
				   
				   retorno.put("where", "where loca.loca_cdcentrocusto is not null ");

            	   retorno.put("groupBy", "group by greg.greg_id, greg.greg_nmregional, " +
            	   		                           "uneg.uneg_id, uneg.uneg_nmunidadenegocio, " +
            	   		                           "loca.loca_cdcentrocusto ");

            	   retorno.put("orderBy", "order by greg.greg_id, uneg.uneg_id, loca.loca_cdcentrocusto ");
               break;
               //Estado por Localidade
               case 5:
            	   retorno.put("gerenciaRegionalID", "greg.greg_id ");
                   retorno.put("gerenciaRegional", "greg.greg_nmregional ");                   
            	   retorno.put("unidadeNegocioID", "uneg.uneg_id ");
            	   retorno.put("unidadeNegocio", "uneg.uneg_nmunidadenegocio ");            	   
            	   retorno.put("localidadeID", "loca.loca_id ");
            	   retorno.put("localidade", "loca.loca_nmlocalidade ");

            	   retorno.put("join", "right join cadastro.g_localidade loca on (loca.loca_id = r.loca_id) ");
            	   
            	   retorno.put("join2","inner join cadastro.g_gerencia_regional greg on (greg.greg_id = loca.greg_id) " +
	   		                           "inner join cadastro.g_unidade_negocio uneg on (uneg.uneg_id = loca.uneg_id)" );

				   retorno.put("tipo", "totalizadorPorEstado");
				   
				   retorno.put("where", "where loca.loca_id != 0 ");

            	   retorno.put("groupBy", "group by greg.greg_id, greg.greg_nmregional, " +
	   		                                       "uneg.uneg_id, uneg.uneg_nmunidadenegocio, " +
            	   		                           "loca.loca_id, loca.loca_nmlocalidade ");

            	   retorno.put("orderBy", "order by greg.greg_id, uneg.uneg_id, loca.loca_id ");
               break;
               //Gerencia Regional
               case 6:
            	   retorno.put("gerenciaRegionalID", "greg.greg_id ");
                   retorno.put("gerenciaRegional", "greg.greg_nmregional ");

                   retorno.put("join", "inner join cadastro.g_gerencia_regional greg on (greg.greg_id = r.greg_id) ");

                   retorno.put("where", "and greg.greg_id = " + filtro.getGerenciaRegional());

                   retorno.put("groupBy", "group by greg.greg_id, greg.greg_nmregional ");            	   
               break;
               //Unidade de Negocio
               case 10:            	   
            	   retorno.put("unidadeNegocioID", "uneg.uneg_id ");
            	   retorno.put("unidadeNegocio", "uneg.uneg_nmunidadenegocio ");

            	   retorno.put("join", "inner join cadastro.g_unidade_negocio uneg on (uneg.uneg_id = r.uneg_id) ");

            	   retorno.put("where", "and uneg.uneg_id = " + filtro.getUnidadeNegocio() + " ");

            	   retorno.put("groupBy", "group by uneg.uneg_id, uneg.uneg_nmunidadenegocio ");            	   
               break;
               //Centro Custo/Localidade
               case 16:
            	   retorno.put("localidadeID", "localidade.loca_cdcentrocusto ");            	   

            	   retorno.put("join", "inner join cadastro.g_localidade localidade on (localidade.loca_id = r.loca_id) ");

            	   retorno.put("where", "and localidade.loca_id = " + filtro.getLocalidade() + " ");

            	   retorno.put("groupBy", "group by localidade.loca_cdcentrocusto ");

            	   retorno.put("orderBy", "order by localidade.loca_cdcentrocusto ");
               break;
               //Localidade
               case 17:
            	   retorno.put("localidadeID", "loca.loca_id ");
            	   retorno.put("localidade", "loca.loca_nmlocalidade ");

            	   retorno.put("join", "inner join cadastro.g_localidade loca on (loca.loca_id = r.loca_id) ");

            	   retorno.put("where", "and loca.loca_id = " + filtro.getLocalidade() + " ");

            	   retorno.put("groupBy", "group by loca.loca_id, loca.loca_nmlocalidade ");
               break;
               //Municipio
               case 19:
            	   retorno.put("municipioID", "muni.muni_id ");
            	   retorno.put("municipio", "muni.muni_nmmunicipio ");

            	   retorno.put("join", "inner join cadastro.g_setor_comercial sc on (sc.stcm_id = r.stcm_id) " +
            	   		               "inner join cadastro.g_municipio muni on (sc.muni_id = muni.muni_id) ");

            	   retorno.put("where", "and sc.muni_id = " + filtro.getMunicipio());

            	   retorno.put("groupBy", "group by muni.muni_id, muni.muni_nmmunicipio ");

            	   retorno.put("orderBy", "order by muni.muni_id, muni.muni_nmmunicipio ");
               break;
            }

            return retorno;
        }

        
        /**
         * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
         *
         * @author Daniel Alves
         * @date 12/04/2010
         *
         * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
         * @throws ErroRepositorioException
         */
        public Collection<Object> pesquisarResumoLigacaoEconomiaRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;

            Map query = totalizadorRelatorioDemonstrativoSinteticoLigacoes(filtro);

            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Municipio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");

            String join = (String)query.get("join");
            String join2 = (String)query.get("join2");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
			
			String tipo = (String)query.get("tipo");

            try {
                consulta = "SELECT " +
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) THEN rele_qtligacoes ELSE 0 END) AS a_movLig_existentes, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) THEN rele_qtligacoes ELSE 0 END) AS a_movLig_funcionando, " +
	                		"sum(CASE WHEN r.last_id = 5 THEN rele_qtligacoes ELSE 0 END) AS a_movLig_cortadas, " +
	                		"sum(CASE WHEN r.last_id in (6,7,8) THEN rele_qtligacoes ELSE 0 END) AS a_movLig_suprimidas, " +
	                		"sum(CASE WHEN r.last_id = 5 THEN coalesce(rele_qtligacoescortesmes, 0) ELSE 0 END) AS a_movLig_corteMes, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) THEN rele_qtligacoesnovasagua ELSE 0 END) AS a_movLig_ligacaoMes, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) THEN coalesce(rele_qtligacoesreligadasmes, 0) ELSE 0 END) AS a_movLig_religacaoMes, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) THEN rele_qteconomias ELSE 0 END) AS a_eco_existentes, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) THEN rele_qteconomias ELSE 0 END) AS a_eco_funcionando, " +
	                		"sum(CASE WHEN r.last_id = 5 THEN rele_qteconomias ELSE 0 END) AS a_eco_cortadas, " +
	                		"sum(CASE WHEN r.last_id in (6, 7, 8) THEN rele_qteconomias ELSE 0 END) AS a_eco_suprimidas, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.catg_id = 1 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoExist_residencial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.catg_id = 2 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoExist_comercial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.catg_id = 4 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoExist_publica, " +
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.catg_id = 3 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoExist_industrial, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.catg_id = 1 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoFunc_residencial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.catg_id = 2 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoFunc_comercial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.catg_id = 4 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoFunc_publica, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.catg_id = 3 THEN rele_qteconomias ELSE 0 END) AS a_eco_ecoFunc_industrial, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.rele_ichidrometro = 1 THEN rele_qtligacoes ELSE 0 END) AS a_hid_funcionando, " +
	                		"sum(CASE WHEN r.last_id = 5 and r.rele_ichidrometro = 1 THEN rele_qtligacoes ELSE 0 END) AS a_hid_cortados, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.lest_id = 3 THEN  rele_qtligacoes ELSE 0 END) AS e_movLig_existente, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.lest_id = 3 THEN  rele_qtligacoes ELSE 0 END) AS e_movLig_funcionando, " +
	                		"sum(CASE WHEN r.last_id = 5 and r.lest_id = 3 THEN  rele_qtligacoes ELSE 0 END) AS e_movLig_cortadas, " +
	                		"sum(CASE WHEN r.last_id in (6, 7, 8) and r.lest_id = 3 THEN  rele_qtligacoes ELSE 0 END) AS e_movLig_suprimidas, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.lest_id = 3 THEN  rele_qteconomias ELSE 0 END) AS e_eco_existentes, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.lest_id = 3 THEN  rele_qteconomias ELSE 0 END) AS e_eco_funcionando, " +
	                		"sum(CASE WHEN r.last_id = 5 and r.lest_id = 3 THEN  rele_qteconomias ELSE 0 END) AS e_eco_cortadas, " +
	                		"sum(CASE WHEN r.last_id in (6, 7, 8) and r.lest_id = 3 THEN  rele_qteconomias ELSE 0 END) AS e_eco_supridas, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.lest_id = 3 and r.catg_id = 1 THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoExist_residencial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.lest_id = 3 and r.catg_id = 2 THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoExist_comercial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.lest_id = 3 and r.catg_id = 4  THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoExist_publica, " +
	                		"sum(CASE WHEN r.last_id in (3, 4, 5, 6, 7, 8) and r.lest_id = 3 and r.catg_id = 3 THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoExist_industrial, " +
	                		
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.lest_id = 3 and r.catg_id = 1 THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoFunc_residencial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.lest_id = 3 and r.catg_id = 2 THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoFunc_comercial, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.lest_id = 3 and r.catg_id = 4 THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoFunc_publica, " +
	                		"sum(CASE WHEN r.last_id in (3, 4) and r.lest_id = 3 and r.catg_id = 3 THEN  rele_qteconomias ELSE 0 END) AS e_eco_ecoFunc_industrial, " +
	                		
	                		gerenciaRegionalID + " AS gerenciaRegionalID, " +
	                		gerenciaRegional + " AS gerenciaRegional, " +
	                		unidadeNegocioID + " AS unidadeNegocioID, " +
	                		unidadeNegocio + " AS unidadeNegocio, " +
	                		localidadeID + " AS localidadeID, " +
	                		localidade + " AS localidade, " +
	                		municipioID + " AS municipioID, " +
	                		municipio + " AS municipio " +
	                		
							
	                		"from " +	                		
	                		"cadastro.un_res_lig_econ r " +
	                		join;
							
							if(tipo != null && tipo.equals("totalizadorPorEstado")){
            			    	consulta += "and r.rele_amreferencia = :referencia " +
            			    		        join2; 
            			    }else{
            			    	consulta += "where r.rele_amreferencia = :referencia ";
            			    }

				consulta += where   +
	                		groupBy +	                        
	                        orderBy;


                retorno = session.createSQLQuery(consulta)
                .addScalar("a_movLig_existentes", Hibernate.LONG)
                .addScalar("a_movLig_funcionando", Hibernate.LONG)
                .addScalar("a_movLig_cortadas", Hibernate.LONG)
                .addScalar("a_movLig_suprimidas", Hibernate.LONG)
                .addScalar("a_movLig_corteMes", Hibernate.LONG)                
                .addScalar("a_movLig_ligacaoMes", Hibernate.LONG)                
                .addScalar("a_movLig_religacaoMes", Hibernate.LONG)                
                .addScalar("a_eco_existentes", Hibernate.LONG)                
                .addScalar("a_eco_funcionando", Hibernate.LONG)                
                .addScalar("a_eco_cortadas", Hibernate.LONG)                
                .addScalar("a_eco_suprimidas", Hibernate.LONG)                
                .addScalar("a_eco_ecoExist_residencial", Hibernate.LONG)                
                .addScalar("a_eco_ecoExist_comercial", Hibernate.LONG)                                
                .addScalar("a_eco_ecoExist_publica", Hibernate.LONG)
                .addScalar("a_eco_ecoExist_industrial", Hibernate.LONG)                
                .addScalar("a_eco_ecoFunc_residencial", Hibernate.LONG)                
                .addScalar("a_eco_ecoFunc_comercial", Hibernate.LONG)                
                .addScalar("a_eco_ecoFunc_publica", Hibernate.LONG)
                .addScalar("a_eco_ecoFunc_industrial", Hibernate.LONG)                
                .addScalar("a_hid_funcionando", Hibernate.LONG)                
                .addScalar("a_hid_cortados", Hibernate.LONG)
                .addScalar("e_movLig_existente", Hibernate.LONG)
                .addScalar("e_movLig_funcionando", Hibernate.LONG)
                .addScalar("e_movLig_cortadas", Hibernate.LONG)
                .addScalar("e_movLig_suprimidas", Hibernate.LONG)
                .addScalar("e_eco_existentes", Hibernate.LONG)
                .addScalar("e_eco_funcionando", Hibernate.LONG)
                .addScalar("e_eco_cortadas", Hibernate.LONG)
                .addScalar("e_eco_supridas", Hibernate.LONG)
                .addScalar("e_eco_ecoExist_residencial", Hibernate.LONG)
                .addScalar("e_eco_ecoExist_comercial", Hibernate.LONG)
                .addScalar("e_eco_ecoExist_publica", Hibernate.LONG)
                .addScalar("e_eco_ecoExist_industrial", Hibernate.LONG)
                .addScalar("e_eco_ecoFunc_residencial", Hibernate.LONG)
                .addScalar("e_eco_ecoFunc_comercial", Hibernate.LONG)
                .addScalar("e_eco_ecoFunc_publica", Hibernate.LONG)
                .addScalar("e_eco_ecoFunc_industrial", Hibernate.LONG)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)                
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
         * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
         *
         * @author Daniel Alves
         * @date 12/04/2010
         *
         * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
         * @throws ErroRepositorioException
         */
        public Collection<Object> pesquisarResumoConsumoAguaRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorRelatorioDemonstrativoSinteticoLigacoes(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Municipio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            
            String join = (String)query.get("join");
            String join2 = (String)query.get("join2");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
			
			String tipo = (String)query.get("tipo");
            
            try {
            	consulta = "SELECT " +
	                		"sum(CASE WHEN r.reca_voconsumofaturado is not null THEN coalesce(r.reca_voconsumofaturado, 0) ELSE 0 END) AS a_cons_faturado, " +	                		
	                		"sum(CASE WHEN r.reca_ichidrometro = 2 THEN coalesce(r.reca_voconsumofaturado, 0) ELSE 0 END) AS a_cons_estNHidrometrado, " +	                		
	                		"sum(CASE WHEN r.reca_ichidrometro = 1 and r.cstp_id != 1 THEN coalesce(r.reca_voconsumofaturado, 0) ELSE 0 END) AS a_cons_estHidrometrado, " +	                		
	                		"sum(CASE WHEN r.reca_ichidrometro = 1 and r.cstp_id = 1 THEN coalesce(r.reca_voconsumofaturado, 0) ELSE 0 END) AS a_cons_realHidrometrado, " +
	                		
	                		gerenciaRegionalID + " AS gerenciaRegionalID, " +
	                		gerenciaRegional + " AS gerenciaRegional, " +
	                		unidadeNegocioID + " AS unidadeNegocioID, " +
	                		unidadeNegocio + " AS unidadeNegocio, " +
	                		localidadeID + " AS localidadeID, " +
	                		localidade + " AS localidade, " +
	                		municipioID + " AS municipioID, " +
	                		municipio + " AS municipio " +
	                		
                 		   "from " +	                		
	                		"micromedicao.un_resumo_consumo_agua r " +
	                		join;
						
						    if(tipo != null && tipo.equals("totalizadorPorEstado")){
            			    	consulta += "and r.reca_amreferencia = :referencia " +
            			    	             join2;
            			    }else{
            			    	consulta += "where r.reca_amreferencia = :referencia ";
            			    }
						    
						    
						
	            consulta +=	where   +
	                		groupBy +
	                		orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("a_cons_faturado", Hibernate.LONG)
                .addScalar("a_cons_estNHidrometrado", Hibernate.LONG)
                .addScalar("a_cons_estHidrometrado", Hibernate.LONG)
                .addScalar("a_cons_realHidrometrado", Hibernate.LONG)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)                
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
         * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
         *
         * @author Daniel Alves
         * @date 12/04/2010
         *
         * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
         * @throws ErroRepositorioException
         */
        public Collection<Object> pesquisaResumoLeituraAnormalidadeRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorRelatorioDemonstrativoSinteticoLigacoes(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Municipio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            
            String join = (String)query.get("join");
            String join2 = (String)query.get("join2");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
			
			String tipo = (String)query.get("tipo");


            try {
            	consulta = "SELECT " +
	                		"sum(CASE WHEN r.relt_qtleituras is not null THEN r.relt_qtleituras ELSE 0 END) AS a_hid_lidos, " +	                		
	                		"sum(CASE WHEN r.ltan_idanormalidadeinformada = 30 THEN r.relt_qtleituras ELSE 0 END) AS a_hid_parados, " +
	                		
	                		gerenciaRegionalID + " AS gerenciaRegionalID, " +
	                		gerenciaRegional + " AS gerenciaRegional, " +
	                		unidadeNegocioID + " AS unidadeNegocioID, " +
	                		unidadeNegocio + " AS unidadeNegocio, " +
	                		localidadeID + " AS localidadeID, " +
	                		localidade + " AS localidade, " +
	                		municipioID + " AS municipioID, " +
	                		municipio + " AS municipio " +
	                		
                 		   "from " +	                		
	                		"micromedicao.un_res_lt_anorm r " +
	                		join;
							
							if(tipo != null && tipo.equals("totalizadorPorEstado")){
            			    	consulta += "and r.relt_amreferencia = :referencia " +
            			    	             join2;
            			    }else{
            			    	consulta += "where r.relt_amreferencia = :referencia ";
            			    }
						
	            consulta +=	where   +
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("a_hid_lidos", Hibernate.LONG)
                .addScalar("a_hid_parados", Hibernate.LONG)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)                
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
         * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
         *
         * @author Daniel Alves
         * @date 12/04/2010
         *
         * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
         * @throws ErroRepositorioException
         */
        public Collection<Object> pesquisaResumoInstalacaoHidrometroRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorRelatorioDemonstrativoSinteticoLigacoes(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Municipio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            
            String join = (String)query.get("join");
            String join2 = (String)query.get("join2");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
			
			String tipo = (String)query.get("tipo");
            
            try {
            	consulta = "SELECT " +	                			                		
	                		"sum(CASE WHEN r.reih_qthidr_instalado_ramal is not null THEN r.reih_qthidr_instalado_ramal ELSE 0 END) AS a_hid_instalados, " +
	                		
	                		gerenciaRegionalID + " AS gerenciaRegionalID, " +
	                		gerenciaRegional + " AS gerenciaRegional, " +
	                		unidadeNegocioID + " AS unidadeNegocioID, " +
	                		unidadeNegocio + " AS unidadeNegocio, " +
	                		localidadeID + " AS localidadeID, " +
	                		localidade + " AS localidade, " +
	                		municipioID + " AS municipioID, " +
	                		municipio + " AS municipio " +
	                		
                 		   "from " +	                		
	                		"micromedicao.un_res_ins_hidr r " +
	                		join;
						
						    if(tipo != null && tipo.equals("totalizadorPorEstado")){
            			    	consulta += "and r.reih_amreferencia = :referencia " +
            			    	            join2;
            			    }else{
            			    	consulta += "where r.reih_amreferencia = :referencia ";
            			    }
						
	            consulta +=	where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("a_hid_instalados", Hibernate.LONG)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)                
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
    	 * Pesquisa todas as tabelas de resumo para o relatorio
    	 *
    	 * [UC1003] - Gerar Relatorio Demonstrativo Sintetico das Ligacoes
    	 *
    	 * @author Daniel Alves
    	 * @date 16/04/2010
    	 *
    	 * @return anoMesReferencia
    	 * 
    	 * @throws ErroRepositorioException
    	 */
    	public boolean existeDadosUnResumoParaRelatorioDemonstrativoSintLigacoes(int anoMesReferencia) 
    		throws ErroRepositorioException {
    		
    		boolean existeDados = true;
    		Integer retorno = null;
    		
    		Session session = HibernateUtil.getSessionGerencial();

    		try{
    			
    			String consulta = "SELECT count(*) "
    				+ "FROM UnResumoLigacaoEconomia resumo "
    				+ "WHERE resumo.referencia = :referencia ";

    			retorno = (Integer) session.createQuery(consulta).
    				setInteger("referencia", anoMesReferencia).
    				setMaxResults(1).
    				uniqueResult();
    			
    			if(retorno == null || retorno == 0){
    				existeDados = false;
    			}

    			if(!existeDados){
    				
    				consulta = "SELECT count(*) "
    					+ "FROM UnResumoConsumoAgua resumo "
    					+ "WHERE resumo.referencia = :referencia ";

    				retorno = (Integer) session.createQuery(consulta).
    					setInteger("referencia", anoMesReferencia).
    					setMaxResults(1).
    					uniqueResult();
    				
    				if(retorno == null || retorno == 0){
    					existeDados = false;
    				}
    				
    				if(!existeDados){
    					
    					consulta = "SELECT count(*) "
    						+ "FROM UnResumoLeituraAnormalidade resumo "
    						+ "WHERE resumo.referencia = :referencia ";

    					retorno = (Integer) session.createQuery(consulta).
    						setInteger("referencia", anoMesReferencia).
    						//setMaxResults(1).
    						uniqueResult();
    					
    					if(retorno == null || retorno == 0){
    						existeDados = false;
    					}
    					
    					if(!existeDados){    						
    						
    						consulta = "SELECT count(*) "
    							+ "FROM UnResumoInstalacaoHidrometro resumo "
    							+ "WHERE resumo.referencia = :referencia ";

    						retorno = (Integer) session.createQuery(consulta).
    							setInteger("referencia", anoMesReferencia).
    							setMaxResults(1).
    							uniqueResult();
    						
    						if(retorno == null || retorno == 0){
    							existeDados = false;
    						}
    						
    					}
    				}
    			}
    			


    		} catch (HibernateException e) {
    			throw new ErroRepositorioException(e, "Erro no Hibernate");
    		} finally {
    			HibernateUtil.closeSession(session);
    		}
    	
    		return existeDados;
    	}
    	
    	
    	/**
	     * Gerar Resumo do Faturamento Por Ano
	     *
	     * Pesquisamos as contas que serão usadas no resumo de contas Por Ano
	     *
	     * @author Fernando Fontelles
	     * @date 25/05/2010
	     *
	     * @param idSetorComercial
	     * @throws ErroRepositorioException
	     */
	    public Collection<Object[]> pesquisarContasResumoFaturamentoPorAno(Integer idSetor)
	            throws ErroRepositorioException {
	        Session session = HibernateUtil.getSession();
	        Collection retorno = null;
	        String consulta = null;
	        try {
	            consulta = "select "
	                    +
	                    // 0
	                    "  gere.id, "
	                    +
	                    // 1
	                    "  unNe.id, "
	                    +
	                    // 2
	                    "  loca.localidade.id, "
	                    +
	                    // 3
	                    "  loca.id, "
	                    +
	                    // 4            
	                    "  seCo.id, "
	                    +
	                    // 5
	                    "  cnta.codigoSetorComercial, "
	                    +
	                    // 6
//	                    "  rot.id, "
//	                    +
	                    // 7
//	                    "  rot.codigo, "
//	                    +
	                    // 8
//	                    "  quad.id, "
//	                    +
//	                    // 9
//	                    "  quad.numeroQuadra, "
//	                    +
	                    // 6
	                    "  imPe.id, "
	                    +
	                    // 7
	                    "  las.id, "
	                    +
	                    // 8
	                    "  les.id, "
	                    +
	                    // 9
	                    "  lap.id, "
	                    +
	                    // 10
	                    "  lep.id, "
	                    +
	                    // 11
	                    "  conTar.id, "
	                    +
	                    // 12
	                    "  fatGru.id, "
	                    +
	                    // 17
	                    //"  empr.id, "
	                    //+
	                    // 13
	                    "  cnta.valorAgua, "
	                    +
	                    // 14
	                    "  cnta.valorEsgoto, "
	                    +
	                    // 15
	                    "  imo.quantidadeEconomias, "
	                    +
	                    // 16
	                    "  imo.id, "
	                    +
	                    // 17
	                    "  cnta.consumoAgua, "
	                    +
	                    // 18
	                    "  cnta.debitos, "
	                    +
	                    // 19
	                    "  cnta.valorImposto, "
	                    +
	                    // 20
	                    "  cnta.valorCreditos, "
	                    +
	                    // 21
	                    "  cnta.id, "
	                    +
	                    // 22
	                    "  cnta.consumoEsgoto "                    
	                    + "from "
	                    + "  Conta cnta "
	                    + "  left join cnta.localidade loca "
	                    + "  left join loca.localidade elo "
	                    + "  left join loca.gerenciaRegional gere "
	                    + "  left join loca.unidadeNegocio unNe "
//	                    + "  left join cnta.quadraConta quad "
//	                    + "  left join quad.setorComercial seCo "
//	                    + "  left join quad.rota rot "
	                    + "  left join cnta.imovelPerfil imPe "
	                    + "  left join cnta.ligacaoAguaSituacao las "
	                    + "  left join cnta.ligacaoEsgotoSituacao les "
	                    + "  left join cnta.imovel imo "
	                    + "  left join imo.ligacaoAgua liAg "
	                    + "  left join imo.setorComercial seCo "
	                    + "  left join liAg.ligacaoAguaPerfil lap "
	                    + "  left join imo.ligacaoEsgoto liEs "
	                    + "  left join liEs.ligacaoEsgotoPerfil lep "
	                    + "  left join cnta.consumoTarifa conTar "
	                    + "  left join cnta.faturamentoGrupo fatGru, "
	                    //+ "  left join rot.faturamentoGrupo fatGru "
	                    //+ "  left join rot.empresa empr, "
	                    + "  SistemaParametro sp "
	                    + "where "
	                    + "  sp.anoMesFaturamento = cnta.referencia and "
	                    + "  ( cnta.debitoCreditoSituacaoAtual = 0 or cnta.debitoCreditoSituacaoAnterior = 0 ) and "
	                    + "  seCo.id = :idSetor";
	            retorno = session.createQuery(consulta).setInteger("idSetor",
	                    idSetor).list();
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
	     * Gerar Resumo do Faturamento Por Ano
	     *
	     * Inserir os dados na tabela un_resumo_faturamento_ref_2010
	     *
	     * @author Fernando Fontelles Filho
	     * @date 25/05/2010
	     *
	     * @param helper Helper de agrupamento dos dados
	     * @throws ErroRepositorioException
	     */
	    public void inserirResumoFaturamentoPorAno( ResumoFaturamentoPorAnoHelper helper )
	        throws ErroRepositorioException{
	        
	    	Connection con = null;
	        Statement stmt = null;
	        Session session = HibernateUtil.getSessionGerencial();
	        String insert;
	        try {
	            con = session.connection();
	            stmt = con.createStatement();
	            insert = 
	                "INSERT INTO \n" +
	                "  faturamento.un_res_fat_ref_2010 ( \n" +
	                // ID
	                "    refa_id, \n" +
	                // 0
	                "    refa_amreferencia, \n" +
	                // 1
	                "    greg_id,\n" +
	                // 2
	                "    uneg_id,\n" +
	                // 3
	                "    loca_id,\n" +
	                // 4
	                "    loca_cdelo,\n" +
	                // 5
	                "    stcm_id,\n" +
	                // 6
//	                "    rota_id,\n" +
//	                // 7
//	                "    qdra_id,\n" +
	                // 6
	                "    refa_cdsetorcomercial,\n" +
	                // 9
//	                "    refa_nnquadra,\n" +
	                // 7
	                "    iper_id,\n" +
	                // 8
	                "    last_id,\n" +
	                // 9
	                "    lest_id,\n" +
	                // 10
	                "    catg_id,\n" +
	                // 11
	                "    scat_id,\n" +
	                // 12
	                "    epod_id,\n" +
	                // 13
	                "    cltp_id,\n" +
	                // 14
	                "    lapf_id,\n" +
	                // 15
	                "    lepf_id,\n" +
	                // 16
	                "    refa_vofaturadoagua,\n" +
	                // 17
	                "    refa_vofaturadoesgoto,\n" +
	                // 18
	                "    refa_vlfaturadoagua,\n" +
	                // 19
	                "    refa_vlfaturadoesgoto,\n" +
	                // 20
	                "    refa_qtcontasemitidas,\n" +
	                // 21
	                "    crog_id,\n" +
	                // 22
	                "    lict_id,\n" +
	                // 23
	                "    dotp_id,\n" +
	                // 24                
	                "    fntp_id,\n" +
	                // 25
	                "    refa_vldocumentosfaturadoscred,\n" +
	                // 26
	                "    refa_qtdocumentosfaturadoscredito,\n" +
	                // 27
	                "    refa_vldocumentosfaturadosoutr,\n" +
	                // 28
	                "    refa_qtdocumentosfaturadosoutros,\n" +
	                // 32
//	                "    empr_id,\n" +
	                // 29                
	                "    refa_vlfinanincluido,\n" +
	                // 30
	                "    refa_vlfinancancelado,\n" +
	                // 31
	                "    refa_qteconomiasfaturadas,\n" +
	                // 32
	                "    dbtp_id,\n" +
	                // 33
	                "    crti_id,\n" +
	                // 34
	                "    refa_ichidrometro,\n" +
	                // 35
	                "    imtp_id,\n" +
	                // 36
	                "    refa_vlimposto,\n" +
	                // 37
	                "    cstf_id,\n" +
	                // 38
	                "    ftgr_id\n" +
	                "    ) " +
	                // 43
//	                "    refa_cdrota )\n" +
	                "  VALUES (\n" +
	                   Util.obterNextValSequence("faturamento.seq_un_res_fat_ref_2007") + ", " +
	                   // 0
	                   helper.getAnoMesFaturamento() + ", \n" +
	                   // 1
	                   helper.getIdGerenciaRegional() + ", \n" +
	                   // 2
	                   helper.getIdUnidadeNegocio() + ", \n" +
	                   // 3
	                   helper.getIdLocalidade() + ", \n" +
	                   // 4
	                   helper.getCdElo() + ", \n" +
	                   // 5
	                   helper.getIdSetorComercial() + ", \n" +                   
	                   // 6
//	                   helper.getIdRota() + ", \n" +
//	                   // 7
//	                   helper.getIdQuadra() + ", \n" +
	                   // 6
	                   helper.getCdSetorComercial() + ", \n" +
	                   // 9
//	                   helper.getNmQuadra() + ", \n" +
	                   // 7
	                   helper.getIdPerfilImovel() + ", \n" +
	                   // 8
	                   helper.getSituacaoLigacaoAgua() + ", \n" +
	                   // 9
	                   helper.getSituacaoLigacaoEsgoto() + ", \n" +
	                   // 10
	                   helper.getIdCategoria() + ", \n" +
	                   // 11
	                   helper.getIdSubcategoria() + ", \n" +
	                   // 12
	                   helper.getIdEsferaPoder() + ", \n" +
	                   // 13
	                   helper.getIdTipoCliente() + ", \n" +
	                   // 14
	                   helper.getIdPerfilLigacaoAgua() + ", \n" +
	                   // 15
	                   helper.getIdPerfilLigacaoEsgoto() + ", \n" +
	                   // 16
	                   helper.getVolumeAgua() + ", \n" +
	                   // 17
	                   helper.getVolumeEsgoto() + ", \n" +
	                   // 18
	                   helper.getValorAgua() + ", \n" +
	                   // 19
	                   helper.getValorEsgoto() + ", \n" +
	                   // 20
	                   helper.getQuantidadeContasEmitidas() + ", \n" +
	                   // 21
	                   helper.getIdOrigemCredito() + ", \n" +
	                   // 22
	                   helper.getIdItemLancamentoContabil() + ", \n" +
	                   // 23
	                   helper.getIdTipoDocumento() + ", \n" +
	                   // 24
	                   helper.getIdTipoFinanciamento() + ", \n" +
	                   // 25
	                   helper.getValorCreditoRealizado() + ", \n" +
	                   // 26
	                   helper.getQuantidadeDocumentosFaturadosCredito() + ", \n" +
	                   // 27
	                   helper.getValorDocumentosFaturadosOutros() + ", \n" +
	                   // 28
	                   helper.getQuantidadeDocumentosFaturadosOutros() + ", \n" +
	                   // 32
//	                   helper.getIdEmpresa() + ", \n" +
	                   // 29
	                   helper.getValorFinanciadoIncluido() + ", \n" +
	                   // 30
	                   helper.getValorFinanciadoCancelado() + ", \n" +
	                   // 31
	                   helper.getQuantidadeEconomiasFaturadas() + ", \n" +
	                   // 32
	                   helper.getIdTipoDebito() + ", \n" +
                       // 33
                       helper.getIdTipoCredito() + ", \n" +                       
	                   // 34
	                   helper.getIndHidrometro() + ", \n" +
	                   // 35
	                   helper.getIdTipoImposto() + ", \n" +
	                   // 36
	                   helper.getValorImposto() + ", \n" +
	                   // 37
	                   helper.getIdTarifaConsumo() + ", \n" +
	                   // 38
	                   helper.getIdGrupoFaturamento() + ") \n"
	                   // 43
//	                   helper.getCdRota() + ") \n"
	                   ;
	            
	            stmt.executeUpdate(insert);
	        } catch (HibernateException e) {
	            // levanta a exceção para a próxima camada
	            throw new ErroRepositorioException(e, "Erro no Hibernate");
	        } catch (SQLException e) {
	            // levanta a exceção para a próxima camada
	            throw new ErroRepositorioException(e, "Erro no Insert");
	        } finally {
	            
	            // fecha a sessão
	            HibernateUtil.closeSession(session);
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException e) {
	                System.out.println("Erro ao fechar conexões");
	            }
	        }
	    }
	    
	    /**
	     * Gerar Resumo do Faturamento Por Ano
	     *
	     * Pesquisamos as guias que serão usadas no resumo de guias de pagamento
	     *
	     * @author Fernando Fontelles
	     * @date 25/05/2010
	     *
	     * @param idSetorComercial
	     * @throws ErroRepositorioException
	     */
	    public Collection<Object[]> pesquisarGuiasResumoFaturamentoPorAno(Integer idSetor)
	            throws ErroRepositorioException {
	        Session session = HibernateUtil.getSession();
	        Collection retorno = null;
	        String consulta = null;
	        
	        String filtrarPor = null;
	        
	        if ( idSetor == 99999 ){
	        	filtrarPor = "  imo is null ";
	        } else {
	        	filtrarPor = "  setCom.id = :idSetor ";
	        }
	        
	        try {
	            consulta = 
	                "select " +
	                // 0
	                "  geRe.id, " +
	                // 1
	                "  unNe.id, " +
	                // 2
	                "  elo.id, " +
	                // 3
	                "  loca.id, " +
	                // 4
	                "  setCom.id, " +
	                // 5
	                "  setCom.codigo, " +
	                // 6
//	                "  rot.id, " +
//	                // 7
//	                "  rot.codigo, " +
//	                // 8
//	                "  qua.id, " +
//	                // 9                
//	                "  qua.numeroQuadra, " +
	                // 6
	                "  case when ( imo is not null ) then imoPer.id else 5 end, " +
	                // 7
	                "  case when ( imo is not null ) then las.id else 1 end, " +
	                // 8
	                "  case when ( imo is not null ) then les.id else 1 end, " +
	                // 9
	                "  case when ( imo is not null ) then pla.id else 0 end, " +
	                // 10
	                "  case when ( imo is not null ) then ple.id else 0 end, " +
	                // 11
	                "  case when ( imo is not null ) then conTar.id else 0 end, " +
	                // 12
	                "  lic.id, " +
	                // 13 
	                "  finTip.id, " +
	                // 14
	                "  debTip.id, " +
	                // 15
	                "  imo.id, " +
	                // 16
	                " esfPod.id, " +
	                // 17
	                " cliTip.id, " +      
	                // 18
	                "  0, " + //faturamentoGrupo
//	                // 23
//	                "  empr.id, " +       
	                // 19
	                "  guia.valorDebito " +
	                "from " +
	                "  GuiaPagamento guia " +
	                "  left join guia.localidade loca " +
	                "  left join loca.gerenciaRegional geRe " +
	                "  left join loca.unidadeNegocio unNe " +
	                "  left join loca.localidade elo " +
	                "  left join guia.imovel imo " +
	                "  left join imo.setorComercial setCom " +
//	                "  left join imo.quadra qua " +
//	                "  left join qua.rota rot " +
	                "  left join imo.imovelPerfil imoPer " +
	                "  left join imo.ligacaoAguaSituacao las " +
	                "  left join imo.ligacaoEsgotoSituacao les " +
	                "  left join imo.ligacaoAgua la " +
	                "  left join la.ligacaoAguaPerfil pla " +
	                "  left join imo.ligacaoAgua le " +
	                "  left join la.ligacaoAguaPerfil ple " +
	                "  left join imo.consumoTarifa conTar " +
	                "  left join guia.lancamentoItemContabil lic " +
	                "  left join guia.financiamentoTipo finTip " +
	                "  left join guia.debitoTipo debTip " +
	                "  left join guia.cliente clie " +
	                "  left join clie.clienteTipo cliTip " +
	                "  left join cliTip.esferaPoder esfPod, " +
//	                "  left join rot.faturamentoGrupo fatGru " +
//	                "  left join rot.empresa empr, " +
	                "  SistemaParametro sp " +
	                "where " +
	                "  guia.anoMesReferenciaContabil = sp.anoMesFaturamento and " +
	                "  (guia.debitoCreditoSituacaoAtual.id = 0 or guia.debitoCreditoSituacaoAnterior.id = 0) and " +
	                filtrarPor +
	                "order by " +
	                "  guia.id ";               
	            
		        if ( idSetor == 99999 ){
		        	retorno = session.createQuery(consulta).list();		        	
		        } else {
		        	retorno = session.createQuery(consulta).setInteger("idSetor",
		                    idSetor).list();		        	
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
	     * Gerar Resumo do Faturamento Por Ano
	     *
	     * Pesquisamos os debitos cobrados a serem usados no resumo de financiamento
	     *
	     * @author Fernando Fontelles
	     * @date 26/05/2010
	     *
	     * @param idSetorComercial
	     * @throws ErroRepositorioException
	     */
	    public Collection<Object[]> pesquisarFinanciamentoPorAno(Integer idSetor)
	            throws ErroRepositorioException {
	    	
	        Session session = HibernateUtil.getSession();
	        Collection retorno = null;
	        String consulta = null;
	        
	        try {
	            consulta = 
	                "select " +
	                // 0
	                "  ger.id, " +
	                // 1
	                "  unNe.id, " +
	                // 2
	                "  elo.id, " +
	                // 3
	                "  loc.id, " +
	                // 4
	                "  setCom.id, " +
	                // 5
	                "  setCom.codigo, " +
	                // 6
//	                "  rot.id, " +
//	                // 7
//	                "  rot.codigo, " +              
//	                // 8
//	                "  qua.id, " +
//	                // 9
//	                "  qua.numeroQuadra, " +
	                // 6
	                "  imoPer.id, " +
	                // 7
	                "  las.id, " +
	                // 8
	                "  les.id, " +
	                // 9
	                "  lap.id, " +
	                // 10
	                "  lep.id, " +
	                // 11
	                "  conTar.id, " +
	                // 12
	                "  0, " +//faturamentoGrupo
	                // 17
//	                "  empLei.id, " +
	                // 13
	                "  lic.id, " +
	                // 14                
	                "  debTip.id, " +
	                // 15
	                "  imo.id, " +
	                // 16
	                "  dac.valorDebito, " +
	                // 17
	                "  dac.debitoCreditoSituacaoAtual.id, " +
	                // 18
	                "  dac.debitoCreditoSituacaoAnterior.id, " +
	                // 19
	                "  dac.numeroPrestacaoDebito, " +
	                // 20
	                "  dac.numeroPrestacaoCobradas " +                
	                "from " +
	                "  DebitoACobrar dac " +
	                "  left join dac.localidade loc " +
	                "  left join loc.gerenciaRegional ger " +
	                "  left join loc.unidadeNegocio unNe " +
	                "  left join loc.localidade elo " +
	                "  left join dac.imovel imo " +
	                "  left join imo.setorComercial setCom " +
//	                "  left join dac.quadra qua " +
//	                "  left join qua.rota rot " +
	                "  left join imo.imovelPerfil imoPer " +
	                "  left join imo.ligacaoAguaSituacao las " +
	                "  left join imo.ligacaoEsgotoSituacao les " +
	                "  left join imo.ligacaoAgua la " +
	                "  left join la.ligacaoAguaPerfil lap " +
	                "  left join imo.ligacaoEsgoto le " +
	                "  left join le.ligacaoEsgotoPerfil lep " +
	                "  left join imo.consumoTarifa conTar " +
//	                "  left join rot.faturamentoGrupo fatGru " +
//	                "  left join rot.empresa empLei " +
	                "  left join dac.lancamentoItemContabil lic " +
	                "  left join dac.debitoTipo debTip " +
	                "  left join dac.financiamentoTipo finTip, " +
	                "  SistemaParametro sp " +
	                "where " +
	                "  dac.anoMesReferenciaContabil = sp.anoMesFaturamento and " +
	                "  finTip.id = 1 and " +
	                "  setCom.id = :idSetor";
	            
	            retorno = session.createQuery(consulta).setInteger("idSetor",
	                    idSetor).list();
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
    	 * Pesquisa todas as tabelas de resumo para o "relatorio"
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 03/05/2010
    	 *
    	 * @return anoMesReferencia
    	 * 
    	 * @throws ErroRepositorioException
    	 */
    	public boolean existeDadosUnResumoParaResumoDadosCas(int anoMesReferencia) 
    		throws ErroRepositorioException {
    		
    		boolean existeDados = false;
    		Integer retorno = null;
    		
    		Session session = HibernateUtil.getSessionGerencial();

    		try{
    			
    			String consulta = "SELECT count(*) "
    				+ "FROM UnResumoLigacaoEconomia resumo "
    				+ "WHERE resumo.referencia = :referencia ";

    			retorno = (Integer) session.createQuery(consulta).
    				setInteger("referencia", anoMesReferencia).
    				setMaxResults(1).
    				uniqueResult();
    			
    			if(retorno != null && retorno != 0){
    				existeDados = true;
    			}

    			if(!existeDados){
    				
    				consulta = "SELECT count(*) "
    					+ "FROM UnResumoConsumoAgua resumo "
    					+ "WHERE resumo.referencia = :referencia ";

    				retorno = (Integer) session.createQuery(consulta).
    					setInteger("referencia", anoMesReferencia).
    					setMaxResults(1).
    					uniqueResult();
    				
    				if(retorno != null && retorno != 0){
    					existeDados = true;
    				}
    				
    				if(!existeDados){
    					
    					consulta = "SELECT count(*) "
    						+ "FROM UnResumoColetaEsgoto resumo "
    						+ "WHERE resumo.referencia = :referencia ";

    					retorno = (Integer) session.createQuery(consulta).
    						setInteger("referencia", anoMesReferencia).
    						setMaxResults(1).
    						uniqueResult();
    					
    					if(retorno != null && retorno != 0){
    						existeDados = true;
    					}
    					
    					if(!existeDados){    						
    						
    						consulta = "SELECT count(*) "
    							+ "FROM UnResumoFaturamento resumo "
    							+ "WHERE resumo.referencia = :referencia ";

    						retorno = (Integer) session.createQuery(consulta).
    							setInteger("referencia", anoMesReferencia).
    							setMaxResults(1).
    							uniqueResult();
    						
    						if(retorno != null && retorno != 0){
    							existeDados = true;
    						}
    						
    						if(!existeDados){    						
        						
        						consulta = "SELECT count(*) "
        							+ "FROM UnResumoArrecadacao resumo "
        							+ "WHERE resumo.anoMesReferencia = :referencia ";

        						retorno = (Integer) session.createQuery(consulta).
        							setInteger("referencia", anoMesReferencia).
        							setMaxResults(1).
        							uniqueResult();
        						
        						if(retorno != null && retorno != 0){
        							existeDados = true;
        						}
        						
        						if(!existeDados){    						
            						
            						consulta = "SELECT count(*) "
            							+ "from UnResumoPendencia resumo "
            							+ "WHERE resumo.anoMesReferencia = :referencia ";
            						
            						retorno = (Integer) session.createQuery(consulta).
            							setInteger("referencia", anoMesReferencia).
            							setMaxResults(1).
            							uniqueResult();
            						
            						if(retorno != null && retorno != 0){
            							existeDados = true;
            						}
            						
            					}
        						
        					}
    						
    					}
    				}
    			}
    			


    		} catch (HibernateException e) {
    			throw new ErroRepositorioException(e, "Erro no Hibernate");
    		} finally {
    			HibernateUtil.closeSession(session);
    		}
    	
    		return existeDados;
    	}
    	
    	
    	/**
    	 * Cria filtro de acordo com a opcao de totalização
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 04/05/2010
    	 *
    	 * @return map
    	 */
    	public Map totalizadorResumoDadosCas(FiltrarResumoDadosCasHelper filtro){
        	Map retorno = new HashMap();
        	        	
        	//colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Municipio/ Localidade
        	retorno.put("gerenciaRegionalID", "sum(0) ");
            retorno.put("gerenciaRegional",   "sum(0) ");
            retorno.put("unidadeNegocioID", "sum(0) ");
            retorno.put("unidadeNegocio",   "sum(0) ");
            retorno.put("localidadeID",     "sum(0) ");
            retorno.put("localidade",       "sum(0) ");
            retorno.put("municipioID",      "sum(0) ");
            retorno.put("municipio",        "sum(0) ");
            
            retorno.put("join",    "");
            retorno.put("joinComercial","");
            retorno.put("where",   "");
            retorno.put("orderBy", "");
            retorno.put("groupBy", "");
            
            int opcaoTotalozacao = filtro.getOpcaoTotalizacao();
            switch(opcaoTotalozacao){
	          //Estado por Gerencia Regional
	            case 2:
	         	   retorno.put("gerenciaRegionalID", "greg.greg_id ");
	                retorno.put("gerenciaRegional", "greg.greg_nmregional ");
	
	                retorno.put("join", "right join cadastro.g_gerencia_regional greg on (greg.greg_id = r.greg_id) ");
	                
	                retorno.put("joinComercial", "right join cadastro.gerencia_regional greg on (greg.greg_id = r.greg_id) ");
	                
	                retorno.put("where", "and greg.greg_id != 0 ");
	                
	                retorno.put("tipo", "totalizadorPorEstado");
	                
	                retorno.put("groupBy", "group by greg.greg_id, greg.greg_nmregional ");
	                
	                retorno.put("orderBy", "order by greg.greg_id ");
	            break;
               
	            //Estado por Unidade de Negocio
	            case 3:            	                      
            	   retorno.put("unidadeNegocioID", "uneg.uneg_id ");
            	   retorno.put("unidadeNegocio", "uneg.uneg_nmunidadenegocio ");
            	   
            	   retorno.put("join", "right join cadastro.g_unidade_negocio uneg on (uneg.uneg_id = r.uneg_id) ");
            	   
            	   retorno.put("joinComercial", "right join cadastro.unidade_negocio uneg on (uneg.uneg_id = r.uneg_id) ");
            	   
            	   retorno.put("where", "and uneg.uneg_id != 0 ");
            	   
            	   retorno.put("tipo", "totalizadorPorEstado");
            	   
            	   retorno.put("groupBy", "group by uneg.uneg_id, uneg.uneg_nmunidadenegocio ");
            	   
            	   retorno.put("orderBy", "order by uneg.uneg_id ");
               break;
               
               //Estado por Município
               case 4:
            	   retorno.put("municipioID", "muni.muni_id ");
            	   retorno.put("municipio", "muni.muni_nmmunicipio ");
            	   
            	   retorno.put("join", "right join cadastro.localidade loca on (loca.loca_id = r.loca_id) " +
            	   					   "right join cadastro.municipio muni on (loca.muni_idprincipal = muni.muni_id) ");
            	   
            	   retorno.put("joinComercial", "right join cadastro.localidade loc on (loc.loca_id = r.loca_id) " +
					   							 "right join cadastro.municipio muni on (loc.muni_idprincipal = muni.muni_id) ");
            	   
            	   retorno.put("where", "and muni.muni_id != 0 ");
            	   
            	   retorno.put("tipo", "totalizadorPorEstado");
            	   
            	   retorno.put("groupBy", "group by muni.muni_id, muni.muni_nmmunicipio ");
            	   
            	   retorno.put("orderBy", "order by muni.muni_id ");
            	   
            	   break;
               
               //Gerencia Regional
               case 6:
            	   retorno.put("gerenciaRegionalID", "greg.greg_id ");
                   retorno.put("gerenciaRegional", "greg.greg_nmregional ");

                   retorno.put("join", "inner join cadastro.g_gerencia_regional greg on (greg.greg_id = r.greg_id) ");
                   
                   retorno.put("joinComercial", "inner join cadastro.gerencia_regional greg on (greg.greg_id = r.greg_id) ");

                   retorno.put("where", "and greg.greg_id = " + filtro.getGerenciaRegional());

                   retorno.put("groupBy", "group by greg.greg_id, greg.greg_nmregional ");            	   
               break;
         
               //Unidade de Negocio
               case 10:            	   
            	   retorno.put("unidadeNegocioID", "uneg.uneg_id ");
            	   retorno.put("unidadeNegocio", "uneg.uneg_nmunidadenegocio ");
            	   
            	   retorno.put("join", "inner join cadastro.g_unidade_negocio uneg on (uneg.uneg_id = r.uneg_id) ");
            	   
            	   retorno.put("joinComercial", "inner join cadastro.unidade_negocio uneg on (uneg.uneg_id = r.uneg_id) ");
            	   
            	   retorno.put("where", "and uneg.uneg_id = " + filtro.getUnidadeNegocio() + " ");
            	   
            	   retorno.put("groupBy", "group by uneg.uneg_id, uneg.uneg_nmunidadenegocio ");            	   
               break;
              
               //Localidade
               case 17:
            	   retorno.put("localidadeID", "loca.loca_id ");
            	   retorno.put("localidade", "loca.loca_nmlocalidade ");
            	   
            	   retorno.put("join", "inner join cadastro.g_localidade loca on (loca.loca_id = r.loca_id) ");
            	   
            	   retorno.put("joinComercial", "inner join cadastro.localidade loca on (loca.loca_id = r.loca_id) ");
            	   
            	   retorno.put("where", "and loca.loca_id = " + filtro.getLocalidade() + " ");
            	   
            	   retorno.put("groupBy", "group by loca.loca_id, loca.loca_nmlocalidade ");
               break;
               
               //Municipio
               case 20:
            	   retorno.put("municipioID", "muni.muni_id ");
            	   retorno.put("municipio", "muni.muni_nmmunicipio ");
            	   
            	   retorno.put("join", "inner join cadastro.localidade loca on (loca.loca_id = r.loca_id) " +
            	   					   "inner join cadastro.municipio muni on (loca.muni_idprincipal = muni.muni_id) ");
            	   
            	   retorno.put("joinComercial", "inner join cadastro.localidade loc on (loc.loca_id = r.loca_id) " +
							                    "inner join cadastro.municipio muni on (loc.muni_idprincipal = muni.muni_id) ");  
            	   
            	   retorno.put("where", "and muni.muni_id = " + filtro.getMunicipio());
            	   
            	   retorno.put("groupBy", " group by muni.muni_id, muni.muni_nmmunicipio ");
            	   
            	   retorno.put("orderBy", "order by muni.muni_id, muni.muni_nmmunicipio ");
               break;
            }
            
            return retorno;
        }
    	
    	
    	/**
    	 * Pesquisa o Resumo de Ligações Economias para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 04/05/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoLigacaoEconomiaResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade / Município
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            
            String join = (String)query.get("join");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
            
            String tipo = (String)query.get("tipo");
            
            try {
            	consulta = "select SUM(CASE WHEN last.last_icativaagua = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) AS AguaTotalLigacoesAtivas, " +
            			          "SUM(CASE WHEN last.last_icdesligadaagua = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As AguaTotalLigacoesInativas, " +
            			          "SUM(CASE WHEN last.last_icativaagua = 1 or last.last_icdesligadaagua = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) AS AguaTotalLigacoesAtvInat, " +
            			          "SUM(CASE WHEN last.LAST_ID = 5 THEN r.RELE_QTLIGACOES ELSE 0 END) AS AguaTotalLigacoesCortadas, " +
            			          "SUM(CASE WHEN last.LAST_ICANALISEAGUA = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) AS AguaTotalLigacoesAnalise, " +
            			          "SUM(CASE WHEN r.last_id = 3 and last.LAST_ICATIVAAGUA = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) AS AguaTotalLigacoesRamaisLig, " +
            			          "sum(CASE WHEN r.RELE_ICHIDROMETRO = 1 and last.last_icativaagua = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As AguaTotalLigacoesMedidas, " +
            			          "sum(CASE WHEN r.RELE_ICHIDROMETRO = 2 and last.last_icativaagua = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As AguaTotalLigacoesNaoMedidas, " +
            			          
            			          "sum(CASE WHEN lest.LEST_ICATIVAESGOTO = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As EsgotoTotalLigacoesAtivas, " +
            			          "sum(CASE WHEN lest.LEST_ICDesligadaEsgoto = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As EsgotoTotalLigacoesInativas, " +
            			          "sum(CASE WHEN lest.LEST_ICATIVAESGOTO = 1 or lest.LEST_ICDesligadaEsgoto = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As EsgotoTotalLigacoesAtivInat, " +
            			          "sum(CASE WHEN r.LEST_ID = 6 THEN r.RELE_QTLIGACOES ELSE 0 END) As EsgotoTotalLigacoesTamponato, " +
            			          "sum(CASE WHEN r.LEST_ID = 5 THEN r.RELE_QTLIGACOES ELSE 0 END) As EsgotoTotalLigacoesAnalise, " +
            			          "sum(CASE WHEN r.LEST_ID = 3 and lest.LEST_ICATIVAESGOTO = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) as EsgotoTotalLigacoesRamaisLig, " +
            			          "sum(CASE WHEN r.RELE_ICHIDROMETROPOCO = 1 and lest.LEST_ICATIVAESGOTO = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As EsgotoTotalLigacoesMedidas, " +
            			          "sum(CASE WHEN r.RELE_ICHIDROMETROPOCO = 2 and lest.LEST_ICATIVAESGOTO = 1 THEN r.RELE_QTLIGACOES ELSE 0 END) As EsgotoTotalLigacoesNaoMedidas, " +
            			          
            			          "SUM(CASE WHEN r.last_id in (6, 7, 8) THEN r.RELE_QTLIGACOES ELSE 0 END) AS AguaTotalLigacoesSuprimidas, " +
            			          
            			          gerenciaRegionalID + " AS gerenciaRegionalID, " +
      	                		  gerenciaRegional + " AS gerenciaRegional, " +
            			          unidadeNegocioID + " AS unidadeNegocioID, " +
      	                		  unidadeNegocio + " AS unidadeNegocio, " +
      	                		  localidadeID + " AS localidadeID, " +
      	                		  localidade + " AS localidade, " +
      	                		  municipioID + " AS municipioID, " +
      	                		  municipio + " AS municipio " +	
      	                		  
            			     "from cadastro.un_res_lig_econ r " +
            			     "left join atendimentopublico.g_lig_esgoto_sit lest on (r.lest_id = lest.lest_id) " +
            			     "left join atendimentopublico.G_LIGACAO_AGUA_SITUACAO last on (r.last_id = last.last_id) " +
            			     join;
            			    
            			    if(tipo != null && tipo.equals("totalizadorPorEstado")){
            			    	consulta += "and r.rele_amreferencia = :referencia where 1 = 1 "; 
            			    }else{
            			    	consulta += "where r.rele_amreferencia = :referencia ";
            			    }
            			    
            	consulta += where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("AguaTotalLigacoesAtivas", Hibernate.LONG)
                .addScalar("AguaTotalLigacoesInativas", Hibernate.LONG)
            	.addScalar("AguaTotalLigacoesAtvInat", Hibernate.LONG)            	
            	.addScalar("AguaTotalLigacoesCortadas", Hibernate.LONG)
            	.addScalar("AguaTotalLigacoesAnalise", Hibernate.LONG)            	
            	.addScalar("AguaTotalLigacoesRamaisLig", Hibernate.LONG)            	
            	.addScalar("AguaTotalLigacoesMedidas", Hibernate.LONG)            	
            	.addScalar("AguaTotalLigacoesNaoMedidas", Hibernate.LONG)            			          
            	.addScalar("EsgotoTotalLigacoesAtivas", Hibernate.LONG)            	
                .addScalar("EsgotoTotalLigacoesInativas", Hibernate.LONG)                
            	.addScalar("EsgotoTotalLigacoesAtivInat", Hibernate.LONG)            	
            	.addScalar("EsgotoTotalLigacoesTamponato", Hibernate.LONG)
            	.addScalar("EsgotoTotalLigacoesAnalise", Hibernate.LONG)            	
            	.addScalar("EsgotoTotalLigacoesRamaisLig", Hibernate.LONG)            	
            	.addScalar("EsgotoTotalLigacoesMedidas", Hibernate.LONG)            	
            	.addScalar("EsgotoTotalLigacoesNaoMedidas", Hibernate.LONG)
            	.addScalar("AguaTotalLigacoesSuprimidas", Hibernate.LONG)
            	.addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)     
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
    	 * Pesquisa o Resumo de Consumo Agua para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 04/05/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoConsumoAguaResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");            
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            
            String join = (String)query.get("join");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
            
            String tipo = (String)query.get("tipo");
            
            try {
            	consulta = "select sum(CASE WHEN r.RECA_QTECONOMIAS IS NOT NULL THEN r.RECA_QTECONOMIAS ELSE 0 END) AS numeroEconomiasAgua, " +
            	                  "sum(CASE WHEN r.reca_voconsumofaturado IS NOT NULL THEN reca_voconsumofaturado ELSE 0 END) AS AguaTotalVolumeFaturado, " +
            	                  "sum(CASE WHEN r.reca_icligacaofaturada = 1 THEN reca_qtligacoes ELSE 0 END) AS AguaTotalLigacoesFaturadas, " +            	               
            	                   gerenciaRegionalID + " AS gerenciaRegionalID, " +
        		                   gerenciaRegional + " AS gerenciaRegional, " +
            			           unidadeNegocioID + " AS unidadeNegocioID, " +
       	                		   unidadeNegocio + " AS unidadeNegocio, " +
       	                		   localidadeID + " AS localidadeID, " +
       	                		   localidade + " AS localidade, " +
    	                		   municipioID + " AS municipioID, " +
    	                		   municipio + " AS municipio " +   	                		   

            			     "from MICROMEDICAO.UN_RESUMO_CONSUMO_AGUA r " +            			     
            			     join;
            	
			            	if(tipo != null && tipo.equals("totalizadorPorEstado")){
						    	consulta += "and r.reca_amreferencia = :referencia where 1 = 1 "; 
						    }else{
						    	consulta += "where r.reca_amreferencia = :referencia ";
						    }
			            	
			    consulta += where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("numeroEconomiasAgua", Hibernate.LONG)
                .addScalar("AguaTotalVolumeFaturado", Hibernate.BIG_DECIMAL)
                .addScalar("AguaTotalLigacoesFaturadas", Hibernate.LONG)                
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
    	 * Pesquisa o Resumo de Coleta Esgoto para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 04/05/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoColetaEsgotoResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");            
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");  
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            String join = (String)query.get("join");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
            
            String tipo = (String)query.get("tipo");
            
            try {
            	consulta = "select sum(CASE WHEN r.RECE_QTECONOMIAS IS NOT NULL THEN r.RECE_QTECONOMIAS ELSE 0 END) AS numeroEconomiasEsgoto, " +
            	                  "sum(CASE WHEN r.rece_vofaturado IS NOT NULL THEN r.rece_vofaturado ELSE 0 END) AS EsgotoTotalVolumeFaturado, " +
            	                  "sum(CASE WHEN r.rece_icfaturamento = 1 THEN rece_qtligacoes ELSE 0 END) AS EsgotoTotalLigacoesFaturadas, " +
            	                   gerenciaRegionalID + " AS gerenciaRegionalID, " +
        		                   gerenciaRegional + " AS gerenciaRegional, " +
            			           unidadeNegocioID + " AS unidadeNegocioID, " +
       	                		   unidadeNegocio + " AS unidadeNegocio, " +
       	                		   localidadeID + " AS localidadeID, " +
       	                		   localidade + " AS localidade, " +
    	                		   municipioID + " AS municipioID, " +
    	                		   municipio + " AS municipio " +    	                		   

            			     "from MICROMEDICAO.UN_RESUMO_COLETA_ESGOTO r " +            			     
            			     join; 
            			     
            			     if(tipo != null && tipo.equals("totalizadorPorEstado")){
 						    	consulta += "and r.rece_amreferencia = :referencia where 1 = 1 "; 
 						     }else{
 						    	consulta += "where r.rece_amreferencia = :referencia ";
 						     }
            			     
            	consulta += where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("numeroEconomiasEsgoto", Hibernate.LONG)
                .addScalar("EsgotoTotalVolumeFaturado", Hibernate.BIG_DECIMAL)
                .addScalar("EsgotoTotalLigacoesFaturadas", Hibernate.LONG)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
    	 * Pesquisa o Resumo Arrecadacao para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 04/05/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoArrecadacaoResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSession();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");            
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            String joinComercial = (String)query.get("joinComercial");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
            
            try {
            	consulta = "select sum(CASE WHEN r.rarr_amreferencia = "+filtro.getAnoMesReferencia()+" and lctp_id = 51 THEN rarr_vlitemarrecadacao ELSE 0 END) As TotalArrecadacaoAtual, " +
            			          "sum(CASE WHEN r.rarr_amreferencia = "+filtro.getAnoMesReferenciaAnterior()+" and lctp_id = 51 THEN rarr_vlitemarrecadacao ELSE 0 END) As TotalArrecadacaoAnterior, " +
            			           gerenciaRegionalID + " AS gerenciaRegionalID, " +
      	                		   gerenciaRegional + " AS gerenciaRegional, " +
            			           unidadeNegocioID + " AS unidadeNegocioID, " +
       	                		   unidadeNegocio + " AS unidadeNegocio, " +
       	                		   localidadeID + " AS localidadeID, " +
       	                		   localidade + " AS localidade, " +
    	                		   municipioID + " AS municipioID, " +
    	                		   municipio + " AS municipio " +
  	                		  

            			     "from arrecadacao.RESUMO_ARRECADACAO r " +            			     
            			     joinComercial;
            	
            	consulta += " where 1 = 1 " +
            	            where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("TotalArrecadacaoAtual", Hibernate.BIG_DECIMAL)                
                .addScalar("TotalArrecadacaoAnterior", Hibernate.BIG_DECIMAL)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
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
    	 * Pesquisa o Resumo Faturamento para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 04/05/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoFaturamentoResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");            
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            String join = (String)query.get("join");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
            
            String tipo = (String)query.get("tipo");
            
            try {
         
            	consulta = "select sum(CASE WHEN REFA_QTCONTASEMITIDAS is not null THEN REFA_QTCONTASEMITIDAS ELSE 0 END) As contasEmitidas, " +
            			           gerenciaRegionalID + " AS gerenciaRegionalID, " +
      	                		   gerenciaRegional + " AS gerenciaRegional, " +
            			          "sum(CASE WHEN refa_vlfaturadoagua IS NOT NULL THEN refa_vlfaturadoagua ELSE 0 END) AS AguaTotalValorFaturado, " +
            			          "sum(CASE WHEN refa_vlfaturadoesgoto IS NOT NULL THEN refa_vlfaturadoesgoto ELSE 0 END) AS EsgotoTotalValorFaturado, " +
            			           gerenciaRegionalID + " AS gerenciaRegionalID, " +
      	                		   gerenciaRegional + " AS gerenciaRegional, " +
            			           unidadeNegocioID + " AS unidadeNegocioID, " +
       	                		   unidadeNegocio + " AS unidadeNegocio, " +
       	                		   localidadeID + " AS localidadeID, " +
       	                		   localidade + " AS localidade, " +
        	                	   municipioID + " AS municipioID, " +
        	                	   municipio + " AS municipio " +

            			     "from FATURAMENTO.UN_RESUMO_FATURAMENTO r " +            			     
            			     join;
            	
            			     if(tipo != null && tipo.equals("totalizadorPorEstado")){
            	            	 consulta += "and r.refa_amreferencia = :referencia where 1 = 1 "; 
				             }else{
				    	         consulta += "where r.refa_amreferencia = :referencia ";
				             }
            			     
            	consulta += where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("contasEmitidas", Hibernate.LONG)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("AguaTotalValorFaturado", Hibernate.BIG_DECIMAL)
                .addScalar("EsgotoTotalValorFaturado", Hibernate.BIG_DECIMAL)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
    	 * Pesquisa o Resumo de Pendências para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 06/05/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoPendenciaResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");            
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            String join = (String)query.get("join");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
            
            String tipo = (String)query.get("tipo");
            
            try {
            	consulta = "SELECT SUM(case when dotp_id in (1,7) THEN (coalesce(RPEN_VLPENDENTE_AGUA, 0) + coalesce(RPEN_VLPENDENTE_ESGOTO, 0) + coalesce(RPEN_VLPENDENTE_DEBITOS, 0)) " +
            			                                                "- (coalesce(RPEN_VLPENDENTE_CREDITOS, 0) + coalesce(RPEN_VLPENDENTE_IMPOSTOS, 0)) ELSE 0 END )as valorPendencia, " +
            			           gerenciaRegionalID + " AS gerenciaRegionalID, " +
            			           gerenciaRegional + " AS gerenciaRegional, " +                                     
            			           unidadeNegocioID + " AS unidadeNegocioID, " +
       	                		   unidadeNegocio + " AS unidadeNegocio, " +
       	                		   localidadeID + " AS localidadeID, " +
       	                		   localidade + " AS localidade, " +
    	                		   municipioID + " AS municipioID, " +
    	                		   municipio + " AS municipio " + 	                		   

            			     "from cobranca.UN_RESUMO_PENDENCIA r " +            			     
            			     join;
            	
            			     if(tipo != null && tipo.equals("totalizadorPorEstado")){
            	            	 consulta += "and r.RPEN_ICVENCIDO = 1 " +
	            			                 "and r.RPEN_AMREFERENCIA = :referencia where 1 = 1 "; 
				             }else{
				    	         consulta += "where r.RPEN_ICVENCIDO = 1 " +
	            			                   "and r.RPEN_AMREFERENCIA = :referencia ";
				             }
                consulta += where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("valorPendencia", Hibernate.BIG_DECIMAL)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)  
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
         * 
         * UC0572 - Gerar Resumo ReFaturamento Novo
         * 
         * Este metodo verifica a existencia do resumo refaturamento
         * 
         * @author Fernando Fontelles
         * @date 29/06/2010
         * 
         */
        public Integer verificarExistenciaResumoReFaturamento(
        		int anoMes)
        		throws ErroRepositorioException {
        	
        	Session session = HibernateUtil.getSessionGerencial();
            Integer retorno = null;
            String consulta = null;
            
            
            try{
            	
            	consulta = "SELECT count(rerf_id) as quantidade " 
            		+ " FROM faturamento.un_resumo_refaturamento "
            		+ " WHERE rerf_amreferencia =:anoMes ";
			
            	retorno = (Integer)session.createSQLQuery(consulta)
            	.addScalar("quantidade", Hibernate.INTEGER)
            	.setInteger("anoMes", anoMes).setMaxResults(1).uniqueResult();
       
            	
            } catch (HibernateException e) {
            	
            	e.printStackTrace();
                // levanta a exceção para a próxima camada
                throw new ErroRepositorioException(e, "Erro no Hibernate");
            } finally {
                // fecha a sessão
                HibernateUtil.closeSession(session);
            }
            return retorno;
        	
        }

        /**
    	 * Pesquisa o Resumo Faturamento para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 06/07/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoFaturamentoResumoDadosCasComercial(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSession();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");            
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            String joinComercial = (String)query.get("joinComercial");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
                        
            
            try {
         
            	consulta = "select " +
            			     "sum(case when lctp_id = 12 and lcit_id in (6,76) and rfat_amreferencia = "+filtro.getAnoMesReferencia() +" then rfat_vlitemfaturamento ELSE 0 END) as totalFaturamentoLiquidoAtual, " +
            			     "sum(case when lctp_id = 12 and lcit_id in (6,76) and rfat_amreferencia = "+filtro.getAnoMesReferenciaAnterior() +" then rfat_vlitemfaturamento ELSE 0 END) as totalfaturamentoliquidoanterio, " +
            			     "sum(CASE WHEN rfat_vlitemfaturamento IS NOT NULL and lctp_id = 1 and lcit_id = 1 and rfat_amreferencia = "+filtro.getAnoMesReferencia() +" THEN rfat_vlitemfaturamento ELSE 0 END) AS AguaTotalValorFaturado, " +
       			             "sum(CASE WHEN rfat_vlitemfaturamento IS NOT NULL and lctp_id = 2 and lcit_id = 2 and rfat_amreferencia = "+filtro.getAnoMesReferencia() +" THEN rfat_vlitemfaturamento ELSE 0 END) AS EsgotoTotalValorFaturado, " +
            			           gerenciaRegionalID + " AS gerenciaRegionalID, " +
 	                		       gerenciaRegional + " AS gerenciaRegional, " +
            			           unidadeNegocioID + " AS unidadeNegocioID, " +
       	                		   unidadeNegocio + " AS unidadeNegocio, " +
       	                		   localidadeID + " AS localidadeID, " +
       	                		   localidade + " AS localidade, " +
    	                		   municipioID + " AS municipioID, " +
    	                		   municipio + " AS municipio " +   	                		   

      	                	 "from financeiro.resumo_faturamento r " +            			     
            			     joinComercial;
            	
            			     
            	consulta += " where 1 = 1 " +
            	            where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)                
                .addScalar("totalFaturamentoLiquidoAtual", Hibernate.BIG_DECIMAL)
                .addScalar("totalfaturamentoliquidoanterio", Hibernate.BIG_DECIMAL)
                .addScalar("AguaTotalValorFaturado", Hibernate.BIG_DECIMAL)
                .addScalar("EsgotoTotalValorFaturado", Hibernate.BIG_DECIMAL)
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
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
    	 * [UC0572] - Gerar Resumo ReFaturamento Novo
    	 * 
    	 * Gera Resumo Refaturamento Contas
    	 * 
    	 * @author Fernando Fontelles
    	 * @date 29/06/2010
    	 * 
    	 * @return
    	 * @throws ErroRepositorioException
    	 */
    	public List getResumoRefaturamentoContas(int idSetor, int anoMes, int indice, int qtRegistros) 
    			throws ErroRepositorioException { 

    		List retorno = null; 

    		Session session = HibernateUtil.getSession(); 
    		try {

    			String hql = "select " 
    				+ "  conta.id, "//0 
    				+ "  imovel.id, "//1 
    				+ "  conta.localidade.gerenciaRegional.id, "//2
    				+ "  conta.localidade.unidadeNegocio.id, "//3
    				+ "  conta.localidade.localidade.id, "//4 elo
    				+ "  conta.localidade.id, "//5 localidade 
    				+ "  imovel.setorComercial.id, "//6
    				+ "  imovel.setorComercial.codigo, "//7
    				+ "  imovel.imovelPerfil.id, "//8
    				+ "  conta.ligacaoAguaSituacao.id, "//9
    				+ "  conta.ligacaoEsgotoSituacao.id, "//10
    				+ "  case when ( " 
    				+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then " 
    				+ "    0 " 
    				+ "  else " 
    				+ "    imovel.ligacaoAgua.ligacaoAguaPerfil.id " 
    				+ "  end, "//11
    				+ "  case when ( " 
    				+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then " 
    				+ "    0 " 
    				+ "  else " 
    				+ "    imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id " 
    				+ "  end, "//12
    				+ " imovel.consumoTarifa.id, "//13
    				+ "  conta.consumoAgua, "//14
    				+ "  conta.consumoEsgoto, "//15
    				+ "  conta.valorAgua, "//16
    				+ "  conta.valorEsgoto, "//17
    				+ "  conta.referencia, "//18
    				+ "  conta.debitoCreditoSituacaoAtual.id, "//19
    				+ "  conta.referenciaContabil, "//20
    				+ "  conta.valorImposto, "//21
    				+ "  conta.valorCreditos, "//22
    				+ "  conta.debitos, "//23
    				+ "  conta.debitoCreditoSituacaoAnterior.id, "//24
    				+ "  conta.documentoTipo.id" //25
    				+ "  from " 
                    + "  gcom.cadastro.sistemaparametro.SistemaParametro as sistemaParametro, " 
                    + "  gcom.faturamento.conta.Conta as conta " 
                    + "  inner join conta.imovel as imovel "  
                    + "  inner join imovel.setorComercial as setorComercial " 
                    + "  left join imovel.ligacaoAgua ligacaoAgua " 
                    + "  left join imovel.ligacaoEsgoto ligacaoEsgoto "  
      			    + " where " 
    				+ "  sistemaParametro.anoMesFaturamento = :anoMesReferencia and "
    				+ "  ((conta.referenciaContabil = :anoMesReferencia and conta.debitoCreditoSituacaoAtual.id in (1,2,3,4)) or " 
    				+ "  (conta.debitoCreditoSituacaoAnterior.id in (1,2) and conta.referenciaContabil >= :anoMesReferencia )) and "
    				+ "  imovel.setorComercial.id = :idSetor " 
      			    + "  order by " 
    		 		+ "  conta.id ";			 
    			
     			 retorno = session.createQuery(hql)
    			.setInteger("anoMesReferencia", anoMes)
    			.setInteger("idSetor", idSetor).setFirstResult(indice).setMaxResults(qtRegistros).list();
     			 
    		} catch (HibernateException e) {
    			throw new ErroRepositorioException(e, "Erro no Hibernate");
    		} finally {
    			HibernateUtil.closeSession(session);
    		}
    		return retorno;
    	}
        
    	/**
    	 * Método que retona uma lista de objeto xxxxx que representa o resumo do
    	 * Refaturamento NOVO(Guia de Pagamento)
    	 * 
    	 * @author Fernando Fontelles
    	 * @date 01/07/2010
    	 * 
    	 * @return
    	 * @throws ErroRepositorioException
    	 */
    	 public List getPesquisaGuiaPagamentoRefaturamentoNovo(int idSetor, int anoMes) 
    	        throws ErroRepositorioException {

    		List retorno = null;

    		Session session = HibernateUtil.getSession();
    		try {


    			String hql = "select " 
    				+ " imov.id, "//0 
    				+ " loca.gerenciaRegional.id, "//1 
    				+ " loca.unidadeNegocio.id, "//2 
    				+ " loca.localidade.id, " //3 Elo
    				+ " loca.id, "//4
    				+ " imov.setorComercial.id, "//5
    				+ " imov.setorComercial.codigo, "//6
    				+ " imov.imovelPerfil.id, "//7
    				+ " imov.ligacaoAguaSituacao.id, "//8
    				+ " imov.ligacaoEsgotoSituacao.id, "//9
    				+ " case when ( "
    				+ " imov.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "       
    				+ " 0 "                                                          
    				+ " else "                                                         
    				+ " imov.ligacaoAgua.ligacaoAguaPerfil.id "//10                      
    				+ " end, " 
    				+ " case when ( "                                                  
    				+ " imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "   
    				+ "  0 "                                                          
    				+ " else "                                                         
    				+ "  imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id "//11
    				+ " end, "
    				+ " case when ( " //12
    				+ " guiaPagamento.imovel.id is null ) then "
    				+ " 0 "
    				+ " else "
    				+ " imov.consumoTarifa.id "
    				+ " end, "
    				+ " guiaPagamento.lancamentoItemContabil.id, " //13
    				+ " guiaPagamento.documentoTipo.id, " //14
//    				+ " guiaPagamento.origem.id, " //14 - NULO
    				+ " guiaPagamento.financiamentoTipo.id, "//15
    				+ " guiaPagamento.debitoTipo.id, "//16
    				+ " guiaPagamento.debitoCreditoSituacaoAtual.id, "//17
    				+ " guiaPagamento.valorDebito " //18
    				+ " from "
    				+ " gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento "
    				+ " inner join guiaPagamento.imovel as imov "
    				+ " inner join imov.localidade as loca "
    				+ " left  join imov.ligacaoAgua ligacaoAgua "
    				+ " left  join imov.ligacaoEsgoto ligacaoEsgoto "
    				+ " where "
    				+ " guiaPagamento.anoMesReferenciaContabil = :anoMes  and "
    				+ " imov.setorComercial.id = :idSetor and "
    				+ " (guiaPagamento.debitoCreditoSituacaoAtual.id = 3) "                                   
    				+ " order by " 
    				+ " guiaPagamento.id ";                                         

    			retorno = session.createQuery(hql).setInteger("anoMes",anoMes)
    											  .setInteger("idSetor", idSetor).list();
    		} catch (HibernateException e) {
    			throw new ErroRepositorioException(e, "Erro no Hibernate");
    		} finally {
    			HibernateUtil.closeSession(session);
    		}
    		return retorno;
    	}
    	
    	 /**
 	     * Gerar Resumo do ReFaturamento Novo
 	     *
 	     * Inserir os dados na tabela un_resumo_refaturamento
 	     *
 	     * @author Fernando Fontelles Filho
 	     * @date 02/07/2010
 	     *
 	     * @param helper Helper de agrupamento dos dados
 	     * @throws ErroRepositorioException
 	     */
 	    public void inserirResumoReFaturamentoNovo( ResumoFaturamentoGuiaPagamentoNovoHelper helper )
 	        throws ErroRepositorioException{
 	        
 	    	Connection con = null;
 	        Statement stmt = null;
 	        Session session = HibernateUtil.getSessionGerencial();
 	        String insert;
 	        
 	        try {
 	            con = session.connection();
 	            stmt = con.createStatement();
 	            insert = 
 	                "INSERT INTO \n" +
 	                "  faturamento.un_resumo_refaturamento ( \n" +
 	                // ID
 	                "    rerf_id, \n" +
// 	             Ultima Alteracao
 	                "    rerf_tmultimaalteracao, \n" +
 	                // 0
 	                "    rerf_amreferencia, \n" +
 	                // 1
 	                "    greg_id,\n" +
 	                // 2
 	                "    uneg_id,\n" +
 	                // 3
// 	                "    loca_cdelo,\n" +
 	                // 4
 	               "    loca_id,\n" +    
 	                // 5
 	                "    stcm_id,\n" +
 	                // 6
 	                "    rerf_cdsetorcomercial,\n" +
 	                // 7
 	                "    iper_id,\n" +
 	                // 8
 	                "    last_id,\n" +
 	                // 9
 	                "    lest_id,\n" +
 	                // 10
 	                "    catg_id,\n" +
 	                // 11
 	                "    scat_id,\n" +
 	                // 12
 	                "    epod_id,\n" +
 	                // 13
 	                "    cltp_id,\n" +
 	                // 14
 	                "    lapf_id,\n" +
 	                // 15
 	                "    lepf_id,\n" +
 	                // 16
 	                "    crog_id,\n" +
 	                // 17
 	                "    lict_id,\n" +
 	                // 18
 	                "    dotp_id,\n" +
 	                // 19
 	                "    cstf_id,\n" +
 	                // 20
 	                "    rerf_ichidrometro,\n" +
 	                // 21
 	                "    rerf_qtcontasretificadas, \n" +
 	                // 22                
 	                "    rerf_qtcontascanceladas, \n" +
 	                // 23
 	                "    rerf_qtcontasincluidas, \n" +
 	                // 24
 	                "    rerf_vlcanceladoagua, \n" +
 	                // 25
 	                "    rerf_vlcanceladoesgoto, \n" +
 	                // 26
 	                "    rerf_vlcanceladooutros, \n" +
 	                // 27                
 	                "    rerf_vlcanceladocredito, \n" +
 	                // 28
 	                "    rerf_vlimpostoscancelados, \n" +
 	                // 29
 	                "    rerf_vocanceladoagua, \n" +
 	                // 30
 	                "    rerf_vocanceladoesgoto, \n" +
 	                // 31
 	                "    rerf_vlincluidoagua,\n" +
 	                // 32
 	                "    rerf_vlincluidoesgoto, \n" +
 	                // 33
 	                "    rerf_vlincluidooutros, \n" +
 	                // 34
 	                "    rerf_vlincluidocredito, \n" +
 	                // 35
 	                "    rerf_vlimpostosincluidos, \n" +
 	                // 36
 	                "    rerf_voincluidoagua, \n" +
 	                //37
 	                "    rerf_voincluidoesgoto, \n" +
 	                //38
 	                "    rerf_vlguiascanceladas, \n" +
 	                //39
 	                "    rerf_qtguiascanceladas, \n " +
 	                //40 
 	                "    fntp_id, \n" +
 	                //41
 	                "    dbtp_id, \n" +
 	                //42
 	                "    crtp_id, \n" +
// 	             43 retirar
 	                "    rerf_amreferenciaconta \n" +
 	                "    ) " +
 	                "  VALUES (\n" +
 	                //ID
 	                   Util.obterNextValSequence("faturamento.seq_un_resumo_refaturamento") + ", " +
// 	                Ultima Alteracao
 	                  Util.obterSQLDataAtual() + ", \n" +
 	 	                // 0
 	 	                helper.getAnoMesReferencia() + ", \n" +
 	 	                // 1
 	 	                helper.getIdGerenciaRegional() + ", \n" +
 	 	                // 2
 	 	                helper.getIdUnidadeNegocio() + ", \n" +
 	 	                // 3
// 	 	                helper.getIdElo + ", \n" +
 	 	                // 4
 	 	                helper.getIdLocalidade() + ", \n" +    
 	 	                // 5
 	 	                helper.getIdSetorComercial() + ", \n" +
 	 	                // 6
 	 	                helper.getCodigoSetorComercial() + ", \n" +
 	 	                // 7
 	 	                helper.getIdPerfilImovel() + ", \n" +
 	 	                // 8
 	 	                helper.getIdSituacaoLigacaoAgua() + ", \n" +
 	 	                // 9
 	 	                helper.getIdSituacaoLigacaoEsgoto() + ", \n" +
 	 	                // 10
 	 	                helper.getIdCategoria() + ", \n" +
 	 	                // 11
 	 	                helper.getIdSubCategoria() + ", \n" +
 	 	                // 12
 	 	                helper.getIdEsfera() + ", \n" +
 	 	                // 13
 	 	                helper.getIdTipoClienteResponsavel() + ", \n" +
 	 	                // 14
 	 	                helper.getIdPerfilLigacaoAgua() + ", \n" +
 	 	                // 15
 	 	                helper.getIdPerfilLigacaoEsgoto() + ", \n" +
 	 	                // 16
 	 	                helper.getCreditoOrigem() + ", \n" +
 	 	                // 17
 	 	                helper.getLancamentoItemContabil() + ", \n" +
 	 	                // 18
 	 	                helper.getIdDocumentoTipo() + ", \n" +
 	 	                // 19
 	 	                helper.getConsumoTarifa() + ", \n" +
 	 	                // 20
 	 	                helper.getIndicadorHidrometro() + ", \n" +
 	 	                // 21
 	 	                helper.getQtdContasRetificadas() + ", \n" +
 	 	                // 22                
 	 	                helper.getQtdContasCanceladas() + ", \n" +
 	 	                // 23
 	 	                helper.getQtdContasIncluidas() + ", \n" +
 	 	                // 24
 	 	                helper.getVlCanceladoAgua() + ", \n" +
 	 	                // 25
 	 	                helper.getVlCanceladoEsgoto() + ", \n" +
 	 	                // 26
 	 	                helper.getVlCanceladoOutros() + ", \n" +
 	 	                // 27                
 	 	                helper.getVlCanceladoCredito() + ", \n" +
 	 	                // 28
 	 	                helper.getVlImpostosCancelados() + ", \n" +
 	 	                // 29
 	 	                helper.getVoCanceladoAgua() + ", \n" +
 	 	                // 30
 	 	                helper.getVoCanceladoEsgoto() + ", \n" +
 	 	                // 31
 	 	                helper.getVlIncluidoAgua() + ",\n" +
 	 	                // 32
 	 	                helper.getVlIncluidoEsgoto() + ", \n" +
 	 	                // 33
 	 	                helper.getVlIncluidoOutros() + ", \n" +
 	 	                // 34
 	 	                helper.getVlIncluidoCredito() + ", \n" +
 	 	                // 35
 	 	                helper.getVlImpostosIncluidos() + ", \n" +
 	 	                // 36
 	 	                helper.getVoIncluidoAgua() + ", \n" +
 	 	                //37
 	 	                helper.getVoIncluidoEsgoto() + ", \n" +
 	 	                //38
 	 	                helper.getVlGuiasCanceladas() + ", \n" +
 	 	                //39
 	 	                helper.getQtdGuiasCanceladas() + ", \n " +
 	 	                //40 
 	 	                helper.getIdFinanciamentoTipo() + ", \n" +
 	 	                //41
 	 	                helper.getDebitoTipo() + ", \n" +
 	 	                //42
 	 	                helper.getCreditoTipo() + ", \n" +
 	 	                //43  Ano Mes Referencia Conta
 	 	                null + " \n" +
 	                   ") \n " 
 	                   ;
 	            
 	            stmt.executeUpdate(insert);
 	        } catch (HibernateException e) {
 	            // levanta a exceção para a próxima camada
 	            throw new ErroRepositorioException(e, "Erro no Hibernate");
 	        } catch (SQLException e) {
 	            // levanta a exceção para a próxima camada
 	            throw new ErroRepositorioException(e, "Erro no Insert");
 	        } finally {
 	            
 	            // fecha a sessão
 	            HibernateUtil.closeSession(session);
 	            try {
 	                stmt.close();
 	                con.close();
 	            } catch (SQLException e) {
 	                System.out.println("Erro ao fechar conexões");
 	            }
 	        }
 	    } 
    	 
 	    
 	   /**
 	     * Gerar Resumo do ReFaturamento Novo
 	     *
 	     * Inserir os dados na tabela un_resumo_refaturamento
 	     *
 	     * @author Fernando Fontelles Filho
 	     * @date 02/07/2010
 	     *
 	     * @param helper Helper de agrupamento dos dados
 	     * @throws ErroRepositorioException
 	     */
 	    public void inserirResumoReFaturamentoNovo( ResumoReFaturamentoNovoHelper helper )
 	        throws ErroRepositorioException{
 	        
 	    	Connection con = null;
 	        Statement stmt = null;
 	        Session session = HibernateUtil.getSessionGerencial();
 	        String insert;
 	        
 	        try {
 	            con = session.connection();
 	            stmt = con.createStatement();
 	            insert = 
 	            	 "INSERT INTO \n" +
  	                "  faturamento.un_resumo_refaturamento ( \n" +
  	                // ID
  	                "    rerf_id, \n" +
//  	             Ultima Alteracao
  	                "    rerf_tmultimaalteracao, \n" +
  	                // 0
  	                "    rerf_amreferencia, \n" +
  	                // 1
  	                "    greg_id,\n" +
  	                // 2
  	                "    uneg_id,\n" +
  	                // 3
//  	                "    loca_cdelo,\n" +
  	                // 4
  	               "    loca_id,\n" +    
  	                // 5
  	                "    stcm_id,\n" +
  	                // 6
  	                "    rerf_cdsetorcomercial,\n" +
  	                // 7
  	                "    iper_id,\n" +
  	                // 8
  	                "    last_id,\n" +
  	                // 9
  	                "    lest_id,\n" +
  	                // 10
  	                "    catg_id,\n" +
  	                // 11
  	                "    scat_id,\n" +
  	                // 12
  	                "    epod_id,\n" +
  	                // 13
  	                "    cltp_id,\n" +
  	                // 14
  	                "    lapf_id,\n" +
  	                // 15
  	                "    lepf_id,\n" +
  	                // 16
  	                "    crog_id,\n" +
  	                // 17
  	                "    lict_id,\n" +
  	                // 18
  	                "    dotp_id,\n" +
  	                // 19
  	                "    cstf_id,\n" +
  	                // 20
  	                "    rerf_ichidrometro,\n" +
  	                // 21
  	                "    rerf_qtcontasretificadas, \n" +
  	                // 22                
  	                "    rerf_qtcontascanceladas, \n" +
  	                // 23
  	                "    rerf_qtcontasincluidas, \n" +
  	                // 24
  	                "    rerf_vlcanceladoagua, \n" +
  	                // 25
  	                "    rerf_vlcanceladoesgoto, \n" +
  	                // 26
  	                "    rerf_vlcanceladooutros, \n" +
  	                // 27                
  	                "    rerf_vlcanceladocredito, \n" +
  	                // 28
  	                "    rerf_vlimpostoscancelados, \n" +
  	                // 29
  	                "    rerf_vocanceladoagua, \n" +
  	                // 30
  	                "    rerf_vocanceladoesgoto, \n" +
  	                // 31
  	                "    rerf_vlincluidoagua,\n" +
  	                // 32
  	                "    rerf_vlincluidoesgoto, \n" +
  	                // 33
  	                "    rerf_vlincluidooutros, \n" +
  	                // 34
  	                "    rerf_vlincluidocredito, \n" +
  	                // 35
  	                "    rerf_vlimpostosincluidos, \n" +
  	                // 36
  	                "    rerf_voincluidoagua, \n" +
  	                //37
  	                "    rerf_voincluidoesgoto, \n" +
  	                //38
  	                "    rerf_vlguiascanceladas, \n" +
  	                //39
  	                "    rerf_qtguiascanceladas, \n " +
  	                //40 
  	                "    fntp_id, \n" +
  	                //41
  	                "    dbtp_id, \n" +
  	                //42
  	                "    crtp_id, \n" +
//  	             43 
  	                "    rerf_amreferenciaconta \n" +
  	                "    ) " +
  	                "  VALUES (\n" +
  	                //ID
  	                Util.obterNextValSequence("faturamento.seq_un_resumo_refaturamento") + ", " +
//  	                Ultima Alteracao
  	              Util.obterSQLDataAtual() + " , \n" +
  	 	                // 0
  	 	                helper.getAnoMesReferencia() + ", \n" +
  	 	                // 1
  	 	                helper.getIdGerenciaRegional() + ", \n" +
  	 	                // 2
  	 	                helper.getIdUnidadeNegocio() + ", \n" +
  	 	                // 3
//  	 	                helper.getIdElo + ", \n" +
  	 	                // 4
  	 	                helper.getIdLocalidade() + ", \n" +    
  	 	                // 5
  	 	                helper.getIdSetorComercial() + ", \n" +
  	 	                // 6
  	 	                helper.getCodigoSetorComercial() + ", \n" +
  	 	                // 7
  	 	                helper.getIdPerfilImovel() + ", \n" +
  	 	                // 8
  	 	                helper.getIdSituacaoLigacaoAgua() + ", \n" +
  	 	                // 9
  	 	                helper.getIdSituacaoLigacaoEsgoto() + ", \n" +
  	 	                // 10
  	 	                helper.getIdCategoria() + ", \n" +
  	 	                // 11
  	 	                helper.getIdSubCategoria() + ", \n" +
  	 	                // 12
  	 	                helper.getIdEsfera() + ", \n" +
  	 	                // 13
  	 	                helper.getIdTipoClienteResponsavel() + ", \n" +
  	 	                // 14
  	 	                helper.getIdPerfilLigacaoAgua() + ", \n" +
  	 	                // 15
  	 	                helper.getIdPerfilLigacaoEsgoto() + ", \n" +
  	 	                // 16
  	 	                helper.getCreditoOrigem() + ", \n" +
  	 	                // 17
  	 	                helper.getLancamentoItemContabil() + ", \n" +
  	 	                // 18
  	 	                helper.getDocumentoTipo() + ", \n" +
  	 	                // 19
  	 	                helper.getConsumoTarifa() + ", \n" +
  	 	                // 20
  	 	                helper.getHidrometro() + ", \n" +
  	 	                // 21
  	 	                helper.getQtContasRetificadas() + ", \n" +
  	 	                // 22                
  	 	                helper.getQtContasCanceladas() + ", \n" +
  	 	                // 23
  	 	                helper.getQtContasIncluidas() + ", \n" +
  	 	                // 24
  	 	                helper.getVlCanceladoAgua() + ", \n" +
  	 	                // 25
  	 	                helper.getVlCanceladoEsgoto() + ", \n" +
  	 	                // 26
  	 	                helper.getVlCanceladoOutro() + ", \n" +
  	 	                // 27                
  	 	                helper.getVlCanceladoCreditos() + ", \n" +
  	 	                // 28
  	 	                helper.getVlCanceladoImpostos() + ", \n" +
  	 	                // 29
  	 	                helper.getVoCanceladoAgua() + ", \n" +
  	 	                // 30
  	 	                helper.getVoCanceladoEsgoto() + ", \n" +
  	 	                // 31
  	 	                helper.getVlIncluidoAgua() + ",\n" +
  	 	                // 32
  	 	                helper.getVlIncluidoEsgoto() + ", \n" +
  	 	                // 33
  	 	                helper.getVlIncluidoOutros() + ", \n" +
  	 	                // 34
  	 	                helper.getVlIncluidoCreditos() + ", \n" +
  	 	                // 35
  	 	                helper.getVlIncluidoImpostos() + ", \n" +
  	 	                // 36
  	 	                helper.getVoIncludoAgua() + ", \n" +
  	 	                //37
  	 	                helper.getVoIncluidoEsgoto() + ", \n" +
  	 	                //38
  	 	                helper.getVlCanceladoGuias() + ", \n" +
  	 	                //39
  	 	                helper.getQtGuiasCanceladas() + ", \n " +
  	 	                //40 Financiamento Tipo
  	 	                null + ", \n" +
  	 	                //41 Debito Tipo
  	 	                null + ", \n" +
  	 	                //42 Credito Tipo
  	 	                null + ", \n" +
//  	 	             43 Ano Mes Referencia Conta
  	 	                helper.getAnoMesReferenciaConta() + " \n" +
  	                   ") \n " 
 	                   ;
 	            
 	            stmt.executeUpdate(insert);
 	        } catch (HibernateException e) {
 	            // levanta a exceção para a próxima camada
 	            throw new ErroRepositorioException(e, "Erro no Hibernate");
 	        } catch (SQLException e) {
 	            // levanta a exceção para a próxima camada
 	            throw new ErroRepositorioException(e, "Erro no Insert");
 	        } finally {
 	            
 	            // fecha a sessão
 	            HibernateUtil.closeSession(session);
 	            try {
 	                stmt.close();
 	                con.close();
 	            } catch (SQLException e) {
 	                System.out.println("Erro ao fechar conexões");
 	            }
 	        }
 	    }
 	    
 	   /**
 		 * [UC0305] Consultar análise Faturamento
 		 * 
 		 * @author Hugo Amorim
 		 * @date 06/08/2010
 		 * 
 		 */
 		public Collection consultarResumoAnaliseFaturamentoDetalhe(InformarDadosGeracaoRelatorioConsultaHelper 
 				informarDadosGeracaoRelatorioConsultaHelper) throws ErroRepositorioException{
 			// Cria a coleção de retorno
 			Collection retorno = null;
 			
 			// Obtém a sessão
 			Session session = HibernateUtil.getSession();
 			
 			String sql = null;
 			String unionMunicipio = "";
 			if(informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao() == ConstantesSistema.CODIGO_ESTADO_MUNICIPIO
 					|| informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao() == ConstantesSistema.CODIGO_MUNICIPIO){
 				unionMunicipio = " INNER JOIN cadastro.localidade localidade ON re.loca_id = localidade.loca_id";
 				unionMunicipio += " INNER JOIN cadastro.municipio muni ON localidade.muni_idprincipal = muni.muni_id";
 			}
 			
 			try{
 				
 				if(informarDadosGeracaoRelatorioConsultaHelper.getTipoDetalhe().equalsIgnoreCase("DEBITO")){
 					sql = "SELECT dbtp_dsdebitotipo as descricao, sum(rsdd_vldebito) as valor"
 						+" FROM faturamento.res_fatu_simulacao_debi debi"
 						+" INNER JOIN faturamento.debito_tipo dt on dt.dbtp_id = debi.dbtp_id"
 						+" INNER JOIN faturamento.resumo_fatur_simulacao re on re.rfts_id = debi.rfts_id "
 						+ unionMunicipio
  					    +" WHERE ";

 				}
 				else 
 				if(informarDadosGeracaoRelatorioConsultaHelper.getTipoDetalhe().equalsIgnoreCase("CREDITO")){
 					sql = "SELECT crti_dscreditotipo as descricao, sum(rsdc_vlcredito) as valor"
 						+" FROM faturamento.res_fatu_simulacao_cred cred"
 						+" INNER JOIN faturamento.credito_tipo ct on ct.crti_id = cred.crti_id"
 					    +" INNER JOIN faturamento.resumo_fatur_simulacao re on re.rfts_id = cred.rfts_id "
 					    + unionMunicipio
 					    +" WHERE ";
 				}
 			
 					
 				// Aqui sera montanda a parte dos condicionais da query
 				// estas condicionais serão usadas se necessarias, o q determina seus usos
 				// são os parametros que veem carregados no objeto InformarDadosGeracaoRelatorioConsultaHelper
 				// que é recebido do caso de uso [UC0304] Informar Dados para Geração de Relatorio ou COnsulta
 				String condicionais  = this.criarCondicionaisResumoAnaliseFaturamento(informarDadosGeracaoRelatorioConsultaHelper,"rfts");
 				
 				if (informarDadosGeracaoRelatorioConsultaHelper.getTipoDetalhe().equalsIgnoreCase("DEBITO")){
 					
 					sql =  sql + condicionais + " GROUP BY dbtp_dsdebitotipo";
 				}
 				else if (informarDadosGeracaoRelatorioConsultaHelper.getTipoDetalhe().equalsIgnoreCase("CREDITO")){
 					
 					sql =  sql + condicionais + " GROUP BY crti_dscreditotipo";
 				}
 				
 				
 				//Faz a pesquisa
 				retorno = session.createSQLQuery(sql)
 					.addScalar("descricao", Hibernate.STRING)
 					.addScalar("valor", Hibernate.BIG_DECIMAL)
 					.list();

 				// erro no hibernate
 			} catch (HibernateException e) {
 				// levanta a exceção para a próxima camada
 				throw new ErroRepositorioException(e, "Erro no Hibernate");
 			} finally {
 				// fecha a sessão
 				HibernateUtil.closeSession(session);
 			}

 			// retorna a coleção com os resultados da pesquisa
 			return retorno;
 		}
 		
 		
 		/**
 		 * [UC0305] Consultar análise Faturamento
 		 * 		-Pesquisa para geração relatorio.
 		 * @author Hugo Amorim
 		 * @date 06/08/2010
 		 * 
 		 */
 		public List consultarResumoAnaliseFaturamentoRelatorio(InformarDadosGeracaoRelatorioConsultaHelper 
 				informarDadosGeracaoRelatorioConsultaHelper) throws ErroRepositorioException{
 			// Cria a coleção de retorno
 			List retorno = new ArrayList();
 			
 			// Obtém a sessão
 			Session session = HibernateUtil.getSession();
 			
 			// A query abaixo realiza uma consulta a tabela de ResumoAnaliseFaturamento
 			try{
 			
	
 				String select = "SELECT  sum(re.rfts_qtcontas) as somatorio1, sum(re.rfts_qteconomia) as somatorio2, "
 						+ "sum(re.rfts_nnconsumoagua) as somatorio3, sum(re.rfts_vlagua) as somatorio4, "
 						+ "sum(re.rfts_nnconsumoesgoto) as somatorio5, sum(re.rfts_vlesgoto) as somatorio6, "
 						+ "sum(re.rfts_vlcreditos) as somatorio7, sum(re.rfts_vldebitos) as somatorio8, "
 						+ "sum(re.rfts_vlimpostos) as somatorio9 ";
 				
 				String condicionais  = 
 					this.criarCondicionaisResumoAnaliseFaturamento(
 							informarDadosGeracaoRelatorioConsultaHelper,"rfts");
 				
 				select = this.criarConsultarResumoAnaliseFaturamentoRelatorio(
 						select,condicionais,informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().toString());
 				
 				SQLQuery query = session.createSQLQuery(select);

 				query.addScalar("somatorio1", Hibernate.INTEGER)
					.addScalar("somatorio2", Hibernate.INTEGER)
					.addScalar("somatorio3", Hibernate.INTEGER)
					.addScalar("somatorio4", Hibernate.BIG_DECIMAL)
					.addScalar("somatorio5", Hibernate.INTEGER)
					.addScalar("somatorio6", Hibernate.BIG_DECIMAL)
					.addScalar("somatorio7", Hibernate.BIG_DECIMAL)
					.addScalar("somatorio8", Hibernate.BIG_DECIMAL)
					.addScalar("somatorio9", Hibernate.BIG_DECIMAL)
					.addScalar("descricao", Hibernate.STRING);
 				
 				//Faz a pesquisa
 				retorno = query.list();

 				// erro no hibernate
 			} catch (HibernateException e) {
 				// levanta a exceção para a próxima camada
 				throw new ErroRepositorioException(e, "Erro no Hibernate");
 			} finally {
 				// fecha a sessão
 				HibernateUtil.closeSession(session);
 			}

 			// retorna a coleção com os resultados da pesquisa
 			return retorno;
 		}

		private String criarConsultarResumoAnaliseFaturamentoRelatorio(
				String select, String condicionais, String opcaoTotalizacao) {
			
			String retorno = "";
			
			if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO+"")){
				
				retorno = select + ", 'ESTADO' as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" cadastro.gerencia_regional gr"
				+" WHERE re.greg_id = gr.greg_id and "+condicionais
				+"";
				
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_GRUPO_FATURAMENTO+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GRUPO_FATURAMENTO+"")){
				
				retorno = select + ", ft.ftgr_dsabreviado as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" faturamento.faturamento_grupo ft"
				+" WHERE re.ftgr_id = ft.ftgr_id and "+condicionais
				+" GROUP BY ft.ftgr_dsabreviado " 
				+" ORDER BY ft.ftgr_dsabreviado ";
				
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_GERENCIA_REGIONAL+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL+"")){
				
				retorno = select + ", gr.greg_nmregional as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" cadastro.gerencia_regional gr"
				+" WHERE re.greg_id = gr.greg_id and "+condicionais
				+" GROUP BY gr.greg_nmregional " 
				+" ORDER BY gr.greg_nmregional ";
				
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO+"")	
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_UNIDADE_NEGOCIO+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_UNIDADE_NEGOCIO+"")){
				
				retorno = select + ", un.uneg_nmunidadenegocio as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" cadastro.unidade_negocio un"
				+" WHERE re.uneg_id = un.uneg_id and "+condicionais
				+" GROUP BY un.uneg_nmunidadenegocio " 
				+" ORDER BY un.uneg_nmunidadenegocio ";
				
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_LOCALIDADE+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_LOCALIDADE+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_LOCALIDADE+"")){
				
				retorno = select + ", lo.loca_nmlocalidade as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" cadastro.localidade lo"
				+" WHERE re.loca_id = lo.loca_id and "+condicionais
				+" GROUP BY lo.loca_nmlocalidade " 
				+" ORDER BY lo.loca_nmlocalidade ";
				
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_SETOR_COMERCIAL+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL+"")){
				
				retorno = select + ", sc.stcm_cdsetorcomercial as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" cadastro.setor_comercial sc"
				+" WHERE re.stcm_id = sc.stcm_id and "+condicionais
				+" GROUP BY sc.stcm_cdsetorcomercial " 
				+" ORDER BY sc.stcm_cdsetorcomercial ";
				
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_QUADRA+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_LOCALIDADE_QUADRA+"")
				||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA+"")){
					
				retorno = select + ", q.qdra_nnquadra as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" cadastro.quadra q"
				+" WHERE re.qdra_id = q.qdra_id and "+condicionais
				+" GROUP BY q.qdra_nnquadra " 
				+" ORDER BY q.qdra_nnquadra ";
					
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_MUNICIPIO+"")
					||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_MUNICIPIO+"")){
						
				retorno = select + ", muni.muni_nmmunicipio as descricao "
				+" FROM faturamento.resumo_fatur_simulacao re," 
				+" cadastro.localidade localidade," 
				+" cadastro.municipio muni"
				+" WHERE re.loca_id = localidade.loca_id and "
				+ " localidade.muni_idprincipal = muni.muni_id and"+condicionais
				+" GROUP BY muni.muni_nmmunicipio " 
				+" ORDER BY muni.muni_nmmunicipio ";
						
			}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_ROTA+"")){
						
					retorno = select + ", rota.rota_cdrota as descricao "
					+" FROM faturamento.resumo_fatur_simulacao re," 
					+" micromedicao.rota rota"
					+" WHERE re.rota_id = rota.rota_id and "+condicionais
					+" GROUP BY rota.rota_cdrota " 
					+" ORDER BY rota.rota_cdrota ";
						
				}

			return retorno;
		}
		
		/**
    	 * Pesquisa o Resumo de Instalacao de Hidrometro para 
    	 *  o Resumo com Dados para o CAS
    	 *
    	 * [UC1017] - Gerar Resumo com Dados para o CAS
    	 *
    	 * @author Daniel Alves
    	 * @date 09/09/2010
    	 *
    	 * @return Collection<Object>
    	 */
        public Collection<Object> pesquisaResumoInstalacaoHidrometroResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ErroRepositorioException {
            Session session = HibernateUtil.getSessionGerencial();
            Collection retorno = null;
            String consulta = null;
            
            Map query = totalizadorResumoDadosCas(filtro);
            
            //colunaTotalizador:  coluna para mostrar a descricao da totalizacao escolhida,
            //que pode ser: Gerencia Regional/ Unidade de Negocio/ Localidade
            String gerenciaRegionalID = (String)query.get("gerenciaRegionalID");
            String gerenciaRegional = (String)query.get("gerenciaRegional");            
            String unidadeNegocioID = (String)query.get("unidadeNegocioID");
            String unidadeNegocio = (String)query.get("unidadeNegocio");
            String localidadeID = (String)query.get("localidadeID");
            String localidade = (String)query.get("localidade");
            String municipioID = (String)query.get("municipioID");
            String municipio = (String)query.get("municipio");
            String join = (String)query.get("join");
            String where = (String)query.get("where");
            String orderBy = (String)query.get("orderBy");
            String groupBy = (String)query.get("groupBy");
            
            String tipo = (String)query.get("tipo");
            
            try {
            	consulta = "select sum(CASE WHEN r.reih_qthidr_instalado_ramal IS NOT NULL THEN r.reih_qthidr_instalado_ramal ELSE 0 END) AS AguaHidrometrosInstalados, " +
            	                  "sum(CASE WHEN r.reih_qthidr_instalado_poco IS NOT NULL THEN r.reih_qthidr_instalado_poco ELSE 0 END) AS EsgotoHidrometrosInstalados, " +
            	                  "sum(CASE WHEN r.reih_qthidr_substituido_ramal IS NOT NULL THEN r.reih_qthidr_substituido_ramal ELSE 0 END) AS AguaHidrometrosSubstituidos, " +
            	                  "sum(CASE WHEN r.reih_qthidr_substituido_poco IS NOT NULL THEN r.reih_qthidr_substituido_poco ELSE 0 END) AS EsgotoHidrometrosSubstituidos, " +
            	                   gerenciaRegionalID + " AS gerenciaRegionalID, " +
        		                   gerenciaRegional + " AS gerenciaRegional, " +
            			           unidadeNegocioID + " AS unidadeNegocioID, " +
       	                		   unidadeNegocio + " AS unidadeNegocio, " +
       	                		   localidadeID + " AS localidadeID, " +
       	                		   localidade + " AS localidade, " +
    	                		   municipioID + " AS municipioID, " +
    	                		   municipio + " AS municipio " +      	                		   

            			     "from micromedicao.un_res_ins_hidr r " +            			     
            			     join;
            	
			            	if(tipo != null && tipo.equals("totalizadorPorEstado")){
						    	consulta += "and r.reih_amreferencia = :referencia where 1 = 1 "; 
						    }else{
						    	consulta += "where r.reih_amreferencia = :referencia ";
						    }
			            	
			    consulta += where   +	                        
	                        groupBy +
	                        orderBy;
                              
                retorno = session.createSQLQuery(consulta)
                .addScalar("AguaHidrometrosInstalados", Hibernate.LONG)
                .addScalar("EsgotoHidrometrosInstalados", Hibernate.LONG)
                .addScalar("AguaHidrometrosSubstituidos", Hibernate.LONG)
                .addScalar("EsgotoHidrometrosSubstituidos", Hibernate.LONG)   
                .addScalar("gerenciaRegionalID", Hibernate.STRING)
                .addScalar("gerenciaRegional", Hibernate.STRING)
                .addScalar("unidadeNegocioID", Hibernate.STRING)
                .addScalar("unidadeNegocio", Hibernate.STRING)
                .addScalar("localidadeID", Hibernate.STRING)
                .addScalar("localidade", Hibernate.STRING)   
                .addScalar("municipioID", Hibernate.STRING)
                .addScalar("municipio", Hibernate.STRING)
                .setInteger("referencia",filtro.getAnoMesReferencia()).list();
                
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
         * [UC0571] Gerar Resumo Faturamento
         * [SB0016] – Preparar Dados do Resumo para Créditos a Realizar
         * 
         * @author Ivan Sergio
         * @data 18/01/2011
         *  
         * @param idSetor
         * @return
         * @throws ErroRepositorioException
         */
        public Collection<Object[]> pesquisarCreditoARealizar(Integer idSetor) throws ErroRepositorioException {
        	Session session = HibernateUtil.getSession();
		    Collection retorno = null;
		    String consulta = null;
		    
		    try {
		        consulta = 
		            "select " +
					"	ger.id, " +									// 0
					"	unNe.id, " +								// 1
					"	elo.id, " +									// 2
					"	loc.id, " +									// 3
					"	setCom.id, " +								// 4
					"	setCom.codigo, " +							// 5
					"	rot.id, " +									// 6
					"	rot.codigo, " +								// 7
					"	qua.id, " +									// 8
					"	qua.numeroQuadra, " +						// 9
					"	imoPer.id, " +								// 10
					"	las.id, " +									// 11
					"	les.id, " +									// 12
					"	lap.id, " +									// 13
					"	lep.id, " +									// 14
					"	conTar.id, " +								// 15
					"	fatGru.id, " +								// 16
					"	empLei.id, " +								// 17
					"	car.creditoOrigem.id, " +					// 18
					"	lic.id, " +									// 19
					"	car.creditoTipo.id, " +						// 20
					"	imo.id, " +									// 21
					"	car.valorCredito, " +						// 22
					"	car.debitoCreditoSituacaoAtual.id, " +		// 23
					"	car.debitoCreditoSituacaoAnterior.id, " +	// 24
					"	car.numeroPrestacaoCredito, " +				// 25
					"	car.numeroPrestacaoRealizada, " +			// 26
					"	car.valorResidualMesAnterior, " +			// 27
					"   car.id "+                                   // 28
					"from " + 
					"	CreditoARealizar car " + 
					"	left join car.localidade loc " + 
					"	left join loc.gerenciaRegional ger " + 
					"	left join loc.unidadeNegocio unNe " + 
					"	left join loc.localidade elo " + 
					"	left join car.imovel imo " + 
					"	left join imo.setorComercial setCom " + 
					"	left join car.quadra qua " + 
					"	left join qua.rota rot " + 
					"	left join imo.imovelPerfil imoPer " + 
					"	left join imo.ligacaoAguaSituacao las  " +
					"	left join imo.ligacaoEsgotoSituacao les " + 
					"	left join imo.ligacaoAgua la " + 
					"	left join la.ligacaoAguaPerfil lap " + 
					"	left join imo.ligacaoEsgoto le " + 
					"	left join le.ligacaoEsgotoPerfil lep " + 
					"	left join imo.consumoTarifa conTar " + 
					"	left join rot.faturamentoGrupo fatGru " + 
					"	left join rot.empresa empLei " + 
					"	left join car.lancamentoItemContabil lic, " +
					"	SistemaParametro sp " +
					"where " + 
					"	car.anoMesReferenciaContabil = sp.anoMesFaturamento and " + 
					"  (car.debitoCreditoSituacaoAtual.id in (0, 3) or car.debitoCreditoSituacaoAnterior.id in (0, 3)) and " +
					"	setCom.id = :idSetor";
		        
		        retorno = session.createQuery(consulta).setInteger("idSetor",
		                idSetor).list();
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
         * [UC0571] Gerar Resumo Faturamento
         * [SB0017] – Gerar Resumo Guia de Devolução.
         * 
         * @author Ivan Sergio
         * @data 19/01/2011
         *  
         * @param idSetor
         * @return
         * @throws ErroRepositorioException
         */
	    public Collection<Object[]> pesquisarGuiasDevolucao(Integer idSetor) throws ErroRepositorioException {
	        Session session = HibernateUtil.getSession();
	        Collection retorno = null;
	        String consulta = null;
	        
	        String filtrarPor = null;
	        
	        if ( idSetor == 99999 ){
	        	filtrarPor = "  imo is null ";
	        } else {
	        	filtrarPor = "  setCom.id = :idSetor ";
	        }
	        
	        try {
	            consulta = 
	                "select " +
					"	geRe.id, " +													// 0
					"  	unNe.id, " +													// 1
					"	elo.id, " +														// 2
					"	loca.id, " +													// 3
					"	setCom.id, " +													// 4
					"	setCom.codigo, " +												// 5
					"	rot.id, " +														// 6
					"	rot.codigo, " +													// 7
					"	qua.id, " +														// 8
					"	qua.numeroQuadra, " +											// 9
					"	case when ( imo is not null ) then imoPer.id else 5 end, " +	// 10
					"	case when ( imo is not null ) then las.id else 1 end, " +		// 11
					"	case when ( imo is not null ) then les.id else 1 end, " +		// 12
					"	case when ( imo is not null ) then pla.id else 0 end, " +		// 13
					"	case when ( imo is not null ) then ple.id else 0 end, " +		// 14
					"	case when ( imo is not null ) then conTar.id else 0 end, " +	// 15
					"	lic.id, " +														// 16
					"	debTip.id, " +													// 17
					"	imo.id, " +														// 18
					"	esfPod.id, " +													// 19
					"	cliTip.id, " +													// 20
					"	fatGru.id, " +													// 21
					"	empr.id, " +													// 22
					"	guia.valorDevolucao, " +										// 23
					"	guia.debitoCreditoSituacaoAtual.id, " +							// 24
					"	guia.debitoCreditoSituacaoAnterior.id " +						// 25
					"from " +
					"	GuiaDevolucao guia " +
					"	left join guia.localidade loca " +
					"	left join loca.gerenciaRegional geRe " +
					"	left join loca.unidadeNegocio unNe " +
					"	left join loca.localidade elo " +
					"	left join guia.imovel imo " +
					"	left join imo.setorComercial setCom " +
					"	left join imo.quadra qua " +
					"	left join qua.rota rot " +
					"	left join imo.imovelPerfil imoPer " +
					"	left join imo.ligacaoAguaSituacao las " +
					"	left join imo.ligacaoEsgotoSituacao les " +
					"	left join imo.ligacaoAgua la " +
					"	left join la.ligacaoAguaPerfil pla " +
					"	left join imo.ligacaoAgua le " +
					"	left join la.ligacaoAguaPerfil ple " +
					"	left join imo.consumoTarifa conTar " +
					"	left join guia.lancamentoItemContabil lic " +
					"	left join guia.debitoTipo debTip " +
					"	left join guia.cliente clie " +
					"	left join clie.clienteTipo cliTip " +
					"	left join cliTip.esferaPoder esfPod " +
					"	left join rot.faturamentoGrupo fatGru " +
					"	left join rot.empresa empr, " +
					"	SistemaParametro sp  " +
					"where  " +
					"	guia.anoMesReferenciaContabil = sp.anoMesFaturamento and " +
					"	guia.conta.id is null and " +
					"	guia.guiaPagamento.id is null and " +
					"	guia.debitoACobrarGeral.id is null and " +
					"	(guia.debitoCreditoSituacaoAtual.id in (0, 3) or guia.debitoCreditoSituacaoAnterior.id in (0, 3)) and " +
					filtrarPor +
					"order by " +
					"	guia.id";               
	            
		        if ( idSetor == 99999 ){
		        	retorno = session.createQuery(consulta).list();		        	
		        } else {
		        	retorno = session.createQuery(consulta).setInteger("idSetor",
		                    idSetor).list();		        	
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
	    
	    //Por Tiago Moreno - 29/07/11
	    
	    public Object[] getValorCreditoIncluidoCancelado(int idImovel,int anoMes, int anoMesRef)
			throws ErroRepositorioException {

	    	Object[] retorno = new Object[2];
			Session session = HibernateUtil.getSession();
			try {
	
	
				String sqlCancelamentos	 = " select sum(coalesce(crrz4.crrz_vlcredito,0) - coalesce(crrz1.crrz_vlcredito,0)) as col_0"
								+ " from faturamento.conta cnta4" 
								+ " inner join faturamento.credito_realizado crrz4 on crrz4.cnta_id = cnta4.cnta_id "
								+ " inner join faturamento.conta cnta1 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
								+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
								+ " left join faturamento.credito_realizado crrz1 on crrz1.cnta_id = cnta1.cnta_id and crrz1.crog_id  = crrz4.crog_id  and crrz1.lict_id= crrz4.lict_id"
								+ " and crrz1.crti_id = crrz4.crti_id"
								+ " and crrz4.crrz_amcobrancacredito = crrz1.crrz_amcobrancacredito"
								+ " and crrz4.crrz_amreferenciacredito = crrz1.crrz_amreferenciacredito"
								+ " where cnta4.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes"
								+ " and cnta4.dcst_idatual = 4  and crrz4.crog_id in (2,3,4,5) and cnta1.cnta_amreferenciaconta = :anoMesRef"
								+ " and coalesce(crrz4.crrz_vlcredito,0) > coalesce(crrz1.crrz_vlcredito,0)";
			
			BigDecimal retornoCancelamentos  = (BigDecimal) session.createSQLQuery(sqlCancelamentos)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setMaxResults(1).uniqueResult();
				
			 String sqlInclusao = " select sum(coalesce(crrz1.crrz_vlcredito,0) - coalesce(crrz4.crrz_vlcredito,0)) as col_0"
									 + " from faturamento.conta cnta1"
									 + " inner join faturamento.credito_realizado crrz1 on crrz1.cnta_id = cnta1.cnta_id" 
									 + " inner join faturamento.conta cnta4 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
									 + " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
									 + " left join faturamento.credito_realizado crrz4 on crrz4.cnta_id = cnta4.cnta_id and crrz1.crog_id  = crrz4.crog_id  and crrz1.lict_id= crrz4.lict_id"
									 + " and crrz1.crti_id = crrz4.crti_id"
									 + " and crrz4.crrz_amcobrancacredito = crrz1.crrz_amcobrancacredito"
									 + " and crrz4.crrz_amreferenciacredito = crrz1.crrz_amreferenciacredito"
									 + " where cnta1.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes"
									 + " and cnta4.dcst_idatual = 4 and crrz1.crog_id in (2,3,4,5) and cnta4.cnta_amreferenciaconta = :anoMesRef"
									 + " and coalesce(crrz1.crrz_vlcredito,0) > coalesce(crrz4.crrz_vlcredito,0)";	
			

			BigDecimal retornoInclusoes = (BigDecimal) session.createSQLQuery(sqlInclusao)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setMaxResults(1).uniqueResult();
			
			if (retornoInclusoes == null) {
				retornoInclusoes = BigDecimal.ZERO;
			}
			
			if (retornoCancelamentos == null){
				retornoCancelamentos = BigDecimal.ZERO;
			}
			
			
			retorno[0] = retornoCancelamentos ;
			retorno[1] = retornoInclusoes;
			
			return retorno;
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
	}
	    
    //Por Tiago Moreno - 29/07/11
	    
	public Object[] getValorDebitoIncluidoCancelado(int idImovel,int anoMes, int anoMesRef)
		throws ErroRepositorioException {

	    	Object[] retorno = new Object[2];
			Session session = HibernateUtil.getSession();
			try {
	
	
				String sqlCancelamentos	 = " select sum(coalesce(dbcb4.dbcb_vlprestacao,0) - coalesce(dbcb1.dbcb_vlprestacao,0)) as col_0"
								+ " from faturamento.conta cnta4" 
								+ " inner join faturamento.debito_cobrado dbcb4 on dbcb4.cnta_id = cnta4.cnta_id "
								+ " inner join faturamento.conta cnta1 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
								+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
								+ " left join faturamento.debito_cobrado dbcb1 on dbcb1.cnta_id = cnta1.cnta_id and dbcb1.fntp_id  = dbcb4.fntp_id  and dbcb1.lict_id= dbcb4.lict_id"
								+ " and dbcb1.dbtp_id = dbcb4.dbtp_id"
								+ " and dbcb4.dbcb_amcobrancadebito = dbcb1.dbcb_amcobrancadebito"
								+ " and dbcb4.dbcb_amreferenciadebito = dbcb1.dbcb_amreferenciadebito"
								+ " where cnta4.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes"
								+ " and cnta4.dcst_idatual = 4  and dbcb4.fntp_id < 10 and cnta1.cnta_amreferenciaconta = :anoMesRef"
								+ " and coalesce(dbcb4.dbcb_vlprestacao,0) > coalesce(dbcb1.dbcb_vlprestacao,0)";
			
			BigDecimal retornoCancelamentos  = (BigDecimal) session.createSQLQuery(sqlCancelamentos)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setMaxResults(1).uniqueResult();
				
			 String sqlInclusao = " select sum(coalesce(dbcb1.dbcb_vlprestacao,0) - coalesce(dbcb4.dbcb_vlprestacao,0)) as col_0"
									 + " from faturamento.conta cnta1"
									 + " inner join faturamento.debito_cobrado dbcb1 on dbcb1.cnta_id = cnta1.cnta_id" 
									 + " inner join faturamento.conta cnta4 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
									 + " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
									 + " left join faturamento.debito_cobrado dbcb4 on dbcb4.cnta_id = cnta4.cnta_id and dbcb1.fntp_id  = dbcb4.fntp_id and dbcb1.lict_id= dbcb4.lict_id"
									 + " and dbcb1.dbtp_id = dbcb4.dbtp_id"
									 + " and dbcb4.dbcb_amcobrancadebito = dbcb1.dbcb_amcobrancadebito"
									 + " and dbcb4.dbcb_amreferenciadebito = dbcb1.dbcb_amreferenciadebito"
									 + " where cnta1.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes"
									 + " and cnta4.dcst_idatual = 4 and dbcb1.fntp_id < 10 and cnta4.cnta_amreferenciaconta = :anoMesRef"
									 + " and coalesce(dbcb1.dbcb_vlprestacao,0) > coalesce(dbcb4.dbcb_vlprestacao,0)";	
			

			BigDecimal retornoInclusoes = (BigDecimal) session.createSQLQuery(sqlInclusao)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setMaxResults(1).uniqueResult();
			
			if (retornoInclusoes == null) {
				retornoInclusoes = BigDecimal.ZERO;
			}
			
			if (retornoCancelamentos == null){
				retornoCancelamentos = BigDecimal.ZERO;
			}
			
			
			retorno[0] = retornoCancelamentos ;
			retorno[1] = retornoInclusoes;
			
			return retorno;
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
	}
	    
    /**
     * Por Tiago Moreno
     * 03/08/2011
     */
	    
    public BigDecimal getValorCredito2ou3IncluidoCancelado(Integer idConta) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {


			String sql	    = " select sum(crrz_vlcredito) as col_0"
							+ " from faturamento.credito_realizado crrz, faturamento.conta cnta" 
							+ " where cnta.cnta_id = crrz.cnta_id"
							+ " and crrz.crog_id in (2,3,4,5) and cnta.cnta_id = :idConta ";
		
			BigDecimal retornoQuery  = (BigDecimal) session.createSQLQuery(sql)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idConta", idConta).setMaxResults(1).uniqueResult();
		
			if (retornoQuery == null){
				retornoQuery = BigDecimal.ZERO;
			}
			
			return retornoQuery;
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
	}
    
    /**
     * Por Tiago Moreno
     * 03/08/2011
     */
    
    public BigDecimal getValorDebito2ou3IncluidoCancelado(Integer idConta) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {


			String sql	    = " select sum(dbcb_vlprestacao) as col_0"
							+ " from faturamento.debito_cobrado dbcb, faturamento.conta cnta" 
							+ " where dbcb.cnta_id = cnta.cnta_id"
							+ " and dbcb.fntp_id < 10 and cnta.cnta_id = :idConta ";
		
			BigDecimal retornoQuery  = (BigDecimal) session.createSQLQuery(sql)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idConta", idConta).setMaxResults(1).uniqueResult();
		
			if (retornoQuery == null){
				retornoQuery = BigDecimal.ZERO;
			}
			
			return retornoQuery;
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
	}
    
    //Por Tiago Moreno - 09/08/11
    
    public Object[] getValorCreditoIncluidoCanceladoCategoria(int idImovel,int anoMes, int anoMesRef, int idCategoria)
		throws ErroRepositorioException {

    	Object[] retorno = new Object[2];
		Session session = HibernateUtil.getSession();
		try {


			String sqlCancelamentos	 = " select sum(coalesce(crtg4.crcg_vlcategoria,0) - coalesce(crtg1.crcg_vlcategoria,0)) as col_0"
							+ " from faturamento.conta cnta4 " 
							+ " inner join faturamento.credito_realizado crrz4 on crrz4.cnta_id = cnta4.cnta_id "
							+ " inner join faturamento.conta cnta1 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
							+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
							+ " left join faturamento.credito_realizado crrz1 on crrz1.cnta_id = cnta1.cnta_id and crrz1.crog_id  = crrz4.crog_id  and crrz1.lict_id= crrz4.lict_id"
							+ " and crrz1.crti_id = crrz4.crti_id"
							+ " and crrz4.crrz_amcobrancacredito = crrz1.crrz_amcobrancacredito"
							+ " and crrz4.crrz_amreferenciacredito = crrz1.crrz_amreferenciacredito"
							+ " left join faturamento.cred_realizado_catg crtg4 on crtg4.crrz_id = crrz4.crrz_id and crtg4.catg_id = :idCategoria "
							+ " left join faturamento.cred_realizado_catg crtg1 on crtg1.crrz_id = crrz1.crrz_id and crtg1.catg_id = :idCategoria "
							+ " where cnta4.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes "
							+ " and cnta4.dcst_idatual = 4  and crrz4.crog_id in (2,3,4,5) and cnta1.cnta_amreferenciaconta = :anoMesRef "
							+ " and coalesce(crtg4.crcg_vlcategoria,0) > coalesce(crtg1.crcg_vlcategoria,0)";
		
		BigDecimal retornoCancelamentos  = (BigDecimal) session.createSQLQuery(sqlCancelamentos)
			.addScalar("col_0", Hibernate.BIG_DECIMAL)
			.setInteger("idImovel", idImovel)
			.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setInteger("idCategoria", idCategoria).setMaxResults(1).uniqueResult();
			
		 String sqlInclusao = " select sum(coalesce(crtg1.crcg_vlcategoria,0) - coalesce(crtg4.crcg_vlcategoria,0)) as col_0"
								 + " from faturamento.conta cnta1"
								 + " inner join faturamento.credito_realizado crrz1 on crrz1.cnta_id = cnta1.cnta_id" 
								 + " inner join faturamento.conta cnta4 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
								 + " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
								 + " left join faturamento.credito_realizado crrz4 on crrz4.cnta_id = cnta4.cnta_id and crrz1.crog_id  = crrz4.crog_id  and crrz1.lict_id= crrz4.lict_id"
								 + " and crrz1.crti_id = crrz4.crti_id"
								 + " and crrz4.crrz_amcobrancacredito = crrz1.crrz_amcobrancacredito"
								 + " and crrz4.crrz_amreferenciacredito = crrz1.crrz_amreferenciacredito"
								 + " left join faturamento.cred_realizado_catg crtg4 on crtg4.crrz_id = crrz4.crrz_id and crtg4.catg_id = :idCategoria "
								 + " left join faturamento.cred_realizado_catg crtg1 on crtg1.crrz_id = crrz1.crrz_id and crtg1.catg_id = :idCategoria "
								 + " where cnta1.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes"
								 + " and cnta4.dcst_idatual = 4 and crrz1.crog_id in (2,3,4,5) and cnta4.cnta_amreferenciaconta = :anoMesRef"
								 + " and coalesce(crtg1.crcg_vlcategoria,0) > coalesce(crtg4.crcg_vlcategoria,0)";	
		
			BigDecimal retornoInclusoes = (BigDecimal) session.createSQLQuery(sqlInclusao)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setInteger("idCategoria", idCategoria).setMaxResults(1).uniqueResult();
			
			if (retornoInclusoes == null) {
				retornoInclusoes = BigDecimal.ZERO;
			}
			
			if (retornoCancelamentos == null){
				retornoCancelamentos = BigDecimal.ZERO;
			}
			
			
			retorno[0] = retornoCancelamentos ;
			retorno[1] = retornoInclusoes;
			
			return retorno;
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
    }
    
	public Object[] getValorDebitoIncluidoCanceladoCategoria(int idImovel,int anoMes, int anoMesRef, int idCategoria)
	throws ErroRepositorioException {

    	Object[] retorno = new Object[2];
		Session session = HibernateUtil.getSession();
		try {


			String sqlCancelamentos	 = " select sum(coalesce(dccg4.dccg_vlcategoria,0) - coalesce(dccg1.dccg_vlcategoria,0)) as col_0"
							+ " from faturamento.conta cnta4" 
							+ " inner join faturamento.debito_cobrado dbcb4 on dbcb4.cnta_id = cnta4.cnta_id "
							+ " inner join faturamento.conta cnta1 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
							+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
							+ " left join faturamento.debito_cobrado dbcb1 on dbcb1.cnta_id = cnta1.cnta_id and dbcb1.fntp_id  = dbcb4.fntp_id  and dbcb1.lict_id= dbcb4.lict_id"
							+ " and dbcb1.dbtp_id = dbcb4.dbtp_id"
							+ " and dbcb4.dbcb_amcobrancadebito = dbcb1.dbcb_amcobrancadebito"
							+ " and dbcb4.dbcb_amreferenciadebito = dbcb1.dbcb_amreferenciadebito"
							+ " left join faturamento.debito_cobrado_categoria dccg4 on dccg4.dbcb_id = dbcb4.dbcb_id and dccg4.catg_id = :idCategoria "
							+ " left join faturamento.debito_cobrado_categoria dccg1 on dccg1.dbcb_id = dbcb1.dbcb_id and dccg1.catg_id = :idCategoria "
							+ " where cnta4.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes"
							+ " and cnta4.dcst_idatual = 4  and dbcb4.fntp_id < 10 and cnta1.cnta_amreferenciaconta = :anoMesRef"
							+ " and coalesce(dccg4.dccg_vlcategoria,0) > coalesce(dccg1.dccg_vlcategoria,0)";
		
		BigDecimal retornoCancelamentos  = (BigDecimal) session.createSQLQuery(sqlCancelamentos)
			.addScalar("col_0", Hibernate.BIG_DECIMAL)
			.setInteger("idImovel", idImovel)
			.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setInteger("idCategoria", idCategoria).setMaxResults(1).uniqueResult();
			
		 String sqlInclusao = " select sum(coalesce(dccg1.dccg_vlcategoria,0) - coalesce(dccg4.dccg_vlcategoria,0)) as col_0"
								 + " from faturamento.conta cnta1"
								 + " inner join faturamento.debito_cobrado dbcb1 on dbcb1.cnta_id = cnta1.cnta_id" 
								 + " inner join faturamento.conta cnta4 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
								 + " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
								 + " left join faturamento.debito_cobrado dbcb4 on dbcb4.cnta_id = cnta4.cnta_id and dbcb1.fntp_id  = dbcb4.fntp_id and dbcb1.lict_id= dbcb4.lict_id"
								 + " and dbcb1.dbtp_id = dbcb4.dbtp_id"
								 + " and dbcb4.dbcb_amcobrancadebito = dbcb1.dbcb_amcobrancadebito"
								 + " and dbcb4.dbcb_amreferenciadebito = dbcb1.dbcb_amreferenciadebito"
								 + " left join faturamento.debito_cobrado_categoria dccg4 on dccg4.dbcb_id = dbcb4.dbcb_id and dccg4.catg_id = :idCategoria "
								 + " left join faturamento.debito_cobrado_categoria dccg1 on dccg1.dbcb_id = dbcb1.dbcb_id and dccg1.catg_id = :idCategoria "
								 + " where cnta1.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMes"
								 + " and cnta4.dcst_idatual = 4 and dbcb1.fntp_id < 10 and cnta4.cnta_amreferenciaconta = :anoMesRef"
								 + " and coalesce(dccg1.dccg_vlcategoria,0) > coalesce(dccg4.dccg_vlcategoria,0)";	
		

		BigDecimal retornoInclusoes = (BigDecimal) session.createSQLQuery(sqlInclusao)
			.addScalar("col_0", Hibernate.BIG_DECIMAL)
			.setInteger("idImovel", idImovel)
			.setInteger("anoMes",anoMes).setInteger("anoMesRef",anoMesRef).setInteger("idCategoria", idCategoria).setMaxResults(1).uniqueResult();
		
		if (retornoInclusoes == null) {
			retornoInclusoes = BigDecimal.ZERO;
		}
		
		if (retornoCancelamentos == null){
			retornoCancelamentos = BigDecimal.ZERO;
		}
		
		
		retorno[0] = retornoCancelamentos ;
		retorno[1] = retornoInclusoes;
		
		return retorno;
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
	}
	
    /**
     * Por Tiago Moreno
     * 03/08/2011
     */
	    
    public BigDecimal getValorCredito2ou3IncluidoCanceladoCategoria(Integer idConta, int idCategoria) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {


			String sql	    = " select sum(crcg_vlcategoria) as col_0"
							+ " from faturamento.cred_realizado_catg crcg, faturamento.credito_realizado crrz, faturamento.conta cnta" 
							+ " where crcg.crrz_id = crrz.crrz_id and cnta.cnta_id = crrz.cnta_id"
							+ " and crrz.crog_id in (2,3,4,5) and cnta.cnta_id = :idConta and crcg.catg_id = :idCategoria ";
		
			BigDecimal retornoQuery  = (BigDecimal) session.createSQLQuery(sql)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idConta", idConta).setInteger("idCategoria", idCategoria).setMaxResults(1).uniqueResult();
		
			if (retornoQuery == null){
				retornoQuery = BigDecimal.ZERO;
			}
			
			return retornoQuery;
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
	}
    
    /**
     * Por Tiago Moreno
     * 03/08/2011
     */
    
    public BigDecimal getValorDebito2ou3IncluidoCanceladoCategoria(Integer idConta, int idCategoria) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {


			String sql	    = " select sum(dccg_vlcategoria) as col_0"
							+ " from faturamento.debito_cobrado_categoria dccg,  faturamento.debito_cobrado dbcb, faturamento.conta cnta" 
							+ " where dccg.dbcb_id = dbcb.dbcb_id and dbcb.cnta_id = cnta.cnta_id"
							+ " and dbcb.fntp_id < 10 and cnta.cnta_id = :idConta and dccg.catg_id = :idCategoria ";
		
			BigDecimal retornoQuery  = (BigDecimal) session.createSQLQuery(sql)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.setInteger("idConta", idConta).setInteger("idCategoria", idCategoria).setMaxResults(1).uniqueResult();
		
			if (retornoQuery == null){
				retornoQuery = BigDecimal.ZERO;
			}
			
			return retornoQuery;
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
	}
    
	 public List getValorAnteriorContaRetificadaCategoria(int idImovel, int anoMesRef , int anoMes, int verif, int idCategoria ) throws ErroRepositorioException {
	  

	  List retorno = null;
	  Session session = HibernateUtil.getSession();
	  try {
		  
		  String sqlDataSistemaParametro = " select parm_amreferenciafaturamento as col_0 from cadastro.sistema_parametros";
	    	 
	      Integer amFatur = (Integer) session.createSQLQuery(sqlDataSistemaParametro)
	      		.addScalar("col_0",Hibernate.INTEGER).setMaxResults(1).uniqueResult();

		  String sql = " select ";
			  
		     if (verif == 0) { //contas com dcst_idatual = 1 ou dcst_idanterior = 1 e com a conta com dcst_idatual = 4 respectiva
		    	 	
		    	 sql = sql + " sum(cg4.ctcg_vlagua - cg1.ctcg_vlagua) as col_0,  " //0 Agua 
	  			 + " sum(cg4.ctcg_vlesgoto - cg1.ctcg_vlesgoto) as col_1,  " //1 Esgoto
	  			 + " sum(cg4.ctcg_nnconsumoagua - cg1.ctcg_nnconsumoagua) as col_2,  " //2 Volume Agua
	  			 + " sum(cg4.ctcg_nnconsumoesgoto - cg1.ctcg_nnconsumoesgoto) as col_3 " //3 Volume Esgoto
	  			 + " from faturamento.conta c1, faturamento.conta c4, faturamento.conta_categoria cg1, faturamento.conta_categoria cg4 "
                 + " where c1.imov_id = c4.imov_id "
                 + " and c1.cnta_amreferenciaconta = c4.cnta_amreferenciaconta "
                 + " and c4.dcst_idatual = 4 "
                 + " and (c1.dcst_idatual = 1 or c1.dcst_idanterior = 1) "
                 + " and c4.cnta_amreferenciacontabil = :anoMes " //ano mes do sistema parametro
                 + " and c4.cnta_amreferenciaconta = :anoMesRef " //amreferenciaconta
                 + " and cg4.cnta_id = c4.cnta_id "
                 + " and cg1.cnta_id = c1.cnta_id "
                 + " and cg4.catg_id = :idCategoria "
                 + " and c4.imov_id = :idImovel ";
		    	 
		    	 retorno = session.createSQLQuery(sql)
				 .addScalar("col_0",Hibernate.BIG_DECIMAL)
	             .addScalar("col_1",Hibernate.BIG_DECIMAL)
	             .addScalar("col_2",Hibernate.INTEGER)
	             .addScalar("col_3",Hibernate.INTEGER)
				 .setInteger("idImovel",idImovel )
	             .setInteger("anoMesRef", anoMesRef)
	             .setInteger("anoMes",anoMes)
	             .setInteger("idCategoria",idCategoria)
	             .list();
		    	 System.out.println("AnoMes 0: "+amFatur+"");
		    	 System.out.println("Imovel 0: "+idImovel+"");
  	           }
		     
	                   
	         if (verif == 1) {     //contas com dcst_idatual = 4 sem a conta com dcst_idatual = 1 ou dcst_idanterior = 1 respectiva
	        	 
	        	 sql = sql + " sum(cg4.ctcg_vlagua) as col_0,  " //0 Agua 
	  			 + " sum(cg4.ctcg_vlesgoto) as col_1,  " //1 Esgoto
	  			 + " sum(cg4.ctcg_nnconsumoagua)  as col_2,  " //5 Volume Agua
	  			 + " sum(cg4.ctcg_nnconsumoesgoto) as col_3 " //6 Volume Esgoto
	  			 + " from faturamento.conta c4, faturamento.conta_categoria cg4  "
                 + " where c4.imov_id = :idImovel "
                 + " and c4.cnta_amreferenciacontabil = :anoMes "
                 + " and c4.cnta_amreferenciaconta = :anoMesRef "
                 + " and cg4.cnta_id = c4.cnta_id "
                 + " and cg4.catg_id = :idCategoria "
                 + " and (c4.dcst_idatual = 1 or c4.dcst_idanterior = 1)";
	        	 
	        	 retorno = session.createSQLQuery(sql)
				 .addScalar("col_0",Hibernate.BIG_DECIMAL)
	             .addScalar("col_1",Hibernate.BIG_DECIMAL)
	             .addScalar("col_2",Hibernate.BIG_DECIMAL)
	             .addScalar("col_3",Hibernate.BIG_DECIMAL)
				 .setInteger("idImovel",idImovel )
	             .setInteger("anoMesRef", anoMesRef )
	             .setInteger("anoMes",anoMes)
	             .setInteger("idCategoria",idCategoria)
	             .list();
	        	 
	        	 System.out.println("AnoMes 1: "+anoMes+"");
		    	 System.out.println("Imovel 1: "+idImovel+"");
	        	 
	         }
	         
	         

			 
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	 
	 
	public Object[] getValorAguaEsgotoIncluidoCanceladoCategoria(int idImovel,int anoMesConta, int anoMesContabil, int idCategoria, int idSubcategoria)
		throws ErroRepositorioException {

    	Object[] retorno = new Object[8];
		Session session = HibernateUtil.getSession();
		try {


			String sqlCancelamentosAgua	 = 
					              " select sum(coalesce(ctcg4.ctcg_vlagua,0) - coalesce(ctcg1.ctcg_vlagua,0)) as col_0,"
								+ " sum(coalesce(ctcg4.ctcg_nnconsumoagua,0) - coalesce(ctcg1.ctcg_nnconsumoagua,0)) as col_1"
								+ " from faturamento.conta cnta4" 
								+ " inner join faturamento.conta_categoria ctcg4 on ctcg4.cnta_id = cnta4.cnta_id "
								+ " inner join faturamento.conta cnta1 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
								+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
								+ " left join faturamento.conta_categoria ctcg1 on ctcg1.cnta_id = cnta1.cnta_id and ctcg1.catg_id = :idCategoria and ctcg1.scat_id = :idSubcategoria "
								+ " where cnta4.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMesContabil"
								+ " and ctcg4.catg_id = :idCategoria and ctcg4.scat_id = :idSubcategoria "
								+ " and cnta4.dcst_idatual = 4  and cnta4.cnta_amreferenciaconta = :anoMesConta"
								+ " and coalesce(ctcg4.ctcg_vlagua,0) > coalesce(ctcg1.ctcg_vlagua,0)";
			
			Object[] retornoCancelamentosAgua  = (Object[]) session.createSQLQuery(sqlCancelamentosAgua)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.addScalar("col_1", Hibernate.INTEGER)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMesContabil",anoMesContabil)
				.setInteger("anoMesConta",anoMesConta)
				.setInteger("idCategoria", idCategoria)
				.setInteger("idSubcategoria", idSubcategoria)
				.setMaxResults(1).uniqueResult();
				
			 String sqlInclusoesAgua = 
	                  " select sum(coalesce(ctcg1.ctcg_vlagua,0) - coalesce(ctcg4.ctcg_vlagua,0)) as col_0,"
					+ " sum(coalesce(ctcg1.ctcg_nnconsumoagua,0) - coalesce(ctcg4.ctcg_nnconsumoagua,0)) as col_1"
					+ " from faturamento.conta cnta1" 
					+ " inner join faturamento.conta_categoria ctcg1 on ctcg1.cnta_id = cnta1.cnta_id "
					+ " inner join faturamento.conta cnta4 on (cnta4.imov_id = cnta1.imov_id and cnta4.cnta_amreferenciaconta = cnta1.cnta_amreferenciaconta"
					+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1))"
					+ " left join faturamento.conta_categoria ctcg4 on ctcg4.cnta_id = cnta4.cnta_id and ctcg4.catg_id = :idCategoria and ctcg4.scat_id = :idSubcategoria "
					+ " where cnta1.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMesContabil"
					+ " and ctcg1.catg_id = :idCategoria and ctcg1.scat_id = :idSubcategoria "
					+ " and cnta4.dcst_idatual = 4 and cnta4.cnta_amreferenciaconta = :anoMesConta"
					+ " and coalesce(ctcg1.ctcg_vlagua,0) > coalesce(ctcg4.ctcg_vlagua,0)";
	
			Object[] retornoInclusoesAgua  = (Object[]) session.createSQLQuery(sqlInclusoesAgua)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.addScalar("col_1", Hibernate.INTEGER)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMesContabil",anoMesContabil)
				.setInteger("anoMesConta",anoMesConta)
				.setInteger("idCategoria", idCategoria)
				.setInteger("idSubcategoria", idSubcategoria)
				.setMaxResults(1).uniqueResult();
			
			String sqlCancelamentosEsgoto	 = 
	              " select sum(coalesce(ctcg4.ctcg_vlesgoto,0) - coalesce(ctcg1.ctcg_vlesgoto,0)) as col_0,"
				+ " sum(coalesce(ctcg4.ctcg_nnconsumoesgoto,0) - coalesce(ctcg1.ctcg_nnconsumoesgoto,0)) as col_1"
				+ " from faturamento.conta cnta4" 
				+ " inner join faturamento.conta_categoria ctcg4 on ctcg4.cnta_id = cnta4.cnta_id "
				+ " inner join faturamento.conta cnta1 on (cnta1.imov_id = cnta4.imov_id and cnta1.cnta_amreferenciaconta = cnta4.cnta_amreferenciaconta"
				+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1) )"
				+ " left join faturamento.conta_categoria ctcg1 on ctcg1.cnta_id = cnta1.cnta_id and ctcg1.catg_id = :idCategoria and ctcg1.scat_id = :idSubcategoria "
				+ " where cnta4.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMesContabil"
				+ " and ctcg4.catg_id = :idCategoria and ctcg4.scat_id = :idSubcategoria "
				+ " and cnta4.dcst_idatual = 4 and cnta4.cnta_amreferenciaconta = :anoMesConta"
				+ " and coalesce(ctcg4.ctcg_vlesgoto,0) > coalesce(ctcg1.ctcg_vlesgoto,0)";

			Object[] retornoCancelamentosEsgoto  = (Object[]) session.createSQLQuery(sqlCancelamentosEsgoto)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.addScalar("col_1", Hibernate.INTEGER)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMesContabil",anoMesContabil)
				.setInteger("anoMesConta",anoMesConta)
				.setInteger("idCategoria", idCategoria)
				.setInteger("idSubcategoria", idSubcategoria)
				.setMaxResults(1).uniqueResult();
			
			String sqlInclusoesEsgoto = 
			    " select sum(coalesce(ctcg1.ctcg_vlesgoto,0) - coalesce(ctcg4.ctcg_vlesgoto,0)) as col_0,"
				+ " sum(coalesce(ctcg1.ctcg_nnconsumoesgoto,0) - coalesce(ctcg4.ctcg_nnconsumoesgoto,0)) as col_1"
				+ " from faturamento.conta cnta1" 
				+ " inner join faturamento.conta_categoria ctcg1 on ctcg1.cnta_id = cnta1.cnta_id "
				+ " inner join faturamento.conta cnta4 on (cnta4.imov_id = cnta1.imov_id and cnta4.cnta_amreferenciaconta = cnta1.cnta_amreferenciaconta"
				+ " and (cnta1.dcst_idatual = 1 or cnta1.dcst_idanterior = 1))"
				+ " left join faturamento.conta_categoria ctcg4 on ctcg4.cnta_id = cnta4.cnta_id and ctcg4.catg_id = :idCategoria and ctcg4.scat_id = :idSubcategoria "
				+ " where cnta1.imov_id = :idImovel and cnta4.cnta_amreferenciacontabil = :anoMesContabil"
				+ " and ctcg1.catg_id = :idCategoria and ctcg1.scat_id = :idSubcategoria "
				+ " and cnta4.dcst_idatual = 4 and cnta4.cnta_amreferenciaconta = :anoMesConta"
				+ " and coalesce(ctcg1.ctcg_vlesgoto,0) > coalesce(ctcg4.ctcg_vlesgoto,0)";
			
			Object[] retornoInclusoesEsgoto  = (Object[]) session.createSQLQuery(sqlInclusoesEsgoto)
				.addScalar("col_0", Hibernate.BIG_DECIMAL)
				.addScalar("col_1", Hibernate.INTEGER)
				.setInteger("idImovel", idImovel)
				.setInteger("anoMesContabil",anoMesContabil)
				.setInteger("anoMesConta",anoMesConta)
				.setInteger("idCategoria", idCategoria)
				.setInteger("idSubcategoria", idSubcategoria)
				.setMaxResults(1).uniqueResult();
			
			if (retornoCancelamentosAgua == null) {
				retornoCancelamentosAgua[0] = BigDecimal.ZERO;
				retornoCancelamentosAgua[1] = new Integer(0);
			} else {
				if (retornoCancelamentosAgua[0] == null){
					retornoCancelamentosAgua[0] = BigDecimal.ZERO;
				}
				if (retornoCancelamentosAgua[1] == null) {
					retornoCancelamentosAgua[1] = new Integer(0);
				}
			}
			
			if (retornoInclusoesAgua == null) {
				retornoInclusoesAgua[0] = BigDecimal.ZERO;
				retornoInclusoesAgua[1] = new Integer(0);
			} else {
				if (retornoInclusoesAgua[0] == null){
					retornoInclusoesAgua[0] = BigDecimal.ZERO;
				}
				if (retornoInclusoesAgua[1] == null) {
					retornoInclusoesAgua[1] = new Integer(0);
				}
			}
			
			if (retornoCancelamentosEsgoto == null){
				retornoCancelamentosEsgoto[0] = BigDecimal.ZERO;
				retornoCancelamentosEsgoto[1] = new Integer(0);
			} else {
				if (retornoCancelamentosEsgoto[0] == null){
					retornoCancelamentosEsgoto[0] = BigDecimal.ZERO;
				}
				if (retornoCancelamentosEsgoto[1] == null) {
					retornoCancelamentosEsgoto[1] = new Integer(0);
				}
			}
			
			if (retornoInclusoesEsgoto == null){
				retornoInclusoesEsgoto[0] = BigDecimal.ZERO;
				retornoInclusoesEsgoto[1] = new Integer(0);
			} else {
				if (retornoInclusoesEsgoto[0] == null){
					retornoInclusoesEsgoto[0] = BigDecimal.ZERO;
				}
				if (retornoInclusoesEsgoto[1] == null) {
					retornoInclusoesEsgoto[1] = new Integer(0);
				}
			}
			
			
			retorno[0] = retornoCancelamentosAgua[0];
			retorno[1] = retornoCancelamentosAgua[1];
			retorno[2] = retornoInclusoesAgua[0];
			retorno[3] = retornoInclusoesAgua[1];
			retorno[4] = retornoCancelamentosEsgoto[0];
			retorno[5] = retornoCancelamentosEsgoto[1];
			retorno[6] = retornoInclusoesEsgoto[0];
			retorno[7] = retornoInclusoesEsgoto[1];
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	 /**TODO: Cosanpa - Nova pesquisa de Créditos para gerar Resumo de Faturamento
     * do gerencial para corrigir problema de créditos persistidos na categoria errada
     * 
     * @author Wellington Rocha
     * @date 12/09/2011
     */
    public List pesquisarCreditosRealizadosResumoFaturamentoGerencial (int idConta)throws ErroRepositorioException{
    	List retorno = null;
    	
    	Session session = HibernateUtil.getSession();
		try {
			String hql =
				"select " +
				"   	creditorealizado.creditoOrigem.id, " +
				"   	creditorealizado.lancamentoItemContabil.id, " +
				"   	sum(creditorealizado.valorCredito) as valorcredito, " +
				"   	count(creditorealizado.creditoOrigem.id), " +
				"   	creditorealizado.creditoTipo.id " +
				"from " +
				"   	gcom.faturamento.conta.Conta conta " +
				"   	inner join conta.quadraConta quadra " +
				"   	inner join conta.creditoRealizados as creditorealizado " +
				"where " +
				"	conta.id = :idConta " +
				"   group by " +
				"	creditorealizado.creditoOrigem.id, creditorealizado.lancamentoItemContabil.id, creditorealizado.creditoTipo.id " +
				"order by " +
				"	creditorealizado.creditoOrigem.id, creditorealizado.lancamentoItemContabil.id, creditorealizado.creditoTipo.id";
			
			retorno = session.createQuery(hql).setInteger("idConta", idConta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
    }
	
    /**TODO: Cosanpa - Pesquisa de Guias de pagamento para gerar resumo do faturamento, 
     * excluindo as guias de entrada de parcelamento pagas.
     * 
     * @author Wellington Rocha
     * @date 14/09/2011
     */
    public List pesquisarGuiaPagamentoResumoFaturamentoGerencial (int idSetor, int anoMes)throws ErroRepositorioException{
    	List retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String hql = " select " 
				+ " 1, "                                                            // 00  =  FINANCIAMENTO TIPO
				+ " 7, "                                                            // 01  =  DOCUMENTO TIPO
				+ " guiaPagamento.lancamentoItemContabil.id, "                      // 02  =  LANCAMENTO ITEM CONTABIL
				+ " loca.gerenciaRegional.id, "                                     // 03  =  GERENCIA REGIONAL
				+ " loca.unidadeNegocio.id, "                                       // 04  =  UNIDADE NEGOCIO
				+ " loca.localidade.id, " // elo                                    // 05  =  ELO
				+ " loca.id, "// idlocalidade                                       // 06  =  LOCALIDADE
				+ " setor.id, "                                						// 07  =  SETOR COMERCIAL
				+ " imov.quadra.rota.id, "                                          // 08  =  ROTA
				+ " imov.quadra.id, "                                               // 09  =  QUADRA
				+ " setor.codigo, "                           				        // 10  =  CODIGO SETOR COMERCIAL
				+ " imov.quadra.numeroQuadra, "                                     // 11  =  NUMERO QUADRA
				+ " imov.imovelPerfil.id, "                                         // 12  =  PERFIL DO IMOVEL
				+ " imov.ligacaoAguaSituacao.id, "                                  // 13  =  SITUACAO AGUA LIGACAO
				+ " imov.ligacaoEsgotoSituacao.id, "                                // 14  =  SITUACAO ESGOTO LIGACAO
				+ "  case when ( "                                                  // -------------------
				+ "    imov.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "       //   CHAVES DE QUEBRA!
				+ "    0 "                                                          // -------------------
				+ "  else "                                                         //   
				+ "    imov.ligacaoAgua.ligacaoAguaPerfil.id "                      // 
				+ "  end, " //                                                      // 15 = PERFIL LIGACAO AGUA
				+ "  case when ( "                                                  // 
				+ "    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "   // 
				+ "    0 "                                                          // 
				+ "  else "                                                         // 
				+ "    imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id "                  // 16 = PERFIL LIGACAO ESGOTO
				+ "  end, "//                                                       //
				+ " guiaPagamento.debitoCreditoSituacaoAtual.id, "                  // 17
				+ " imov.quadra.rota.empresa.id, "                                  // 18 = EMPRESA
				+ " imov.consumoTarifa.id, "                                        // 19 = CONSUMO TARIFA
				+ " imov.quadra.rota.faturamentoGrupo.id, "                         // 20 = GRUPO DE FATURAMENTO
				+ " sum(guiaPagamento.valorDebito) as valordebitos, "               // 21 = VALOR DEBITO GUIA
				+ " count(guiaPagamento.id) as qtddocumentos, "                     // 22 = QUANTIDADE GUIA
				+ " guiaPagamento.debitoTipo.id,  "                                 // 23 = DEBITO TIPO
				+ " imov.id, "                                                       // 24 = IMOVEL 
				+"  rota.codigo "													//25 = codigo rota
				+"      "
				+" from "
				+" gcom.arrecadacao.pagamento.GuiaPagamento as guiaPagamento "
				+" inner join guiaPagamento.lancamentoItemContabil as lancamentoitemcontabil "
				+" inner join guiaPagamento.imovel as imov "
				+" inner join imov.localidade as loca "
				+" inner join imov.setorComercial as setor "
				+" inner join imov.quadra.rota rota "
				+" left  join imov.ligacaoAgua ligacaoAgua "
				+" left  join imov.ligacaoEsgoto ligacaoEsgoto "
				+"       "
				+"       "
				+" where "
				+" guiaPagamento.anoMesReferenciaContabil = :anoMes  and "				
				+" imov.quadra.setorComercial.id = :idSetor and "
				+" guiaPagamento.financiamentoTipo.id <> 9 and "
				+" (guiaPagamento.debitoCreditoSituacaoAtual.id = 0 or guiaPagamento.debitoCreditoSituacaoAnterior.id = 0) "
				+"     "
				+"     "
				+" group by " 
				+"   guiaPagamento.lancamentoItemContabil.id, loca.gerenciaRegional.id, loca.unidadeNegocio.id, "                      
				+"   loca.localidade.id, loca.id, setor.id, imov.quadra.rota.id, "                                     
				+"   imov.quadra.id, setor.codigo, imov.quadra.numeroQuadra, imov.imovelPerfil.id, "                                               
				+"   imov.ligacaoAguaSituacao.id, imov.ligacaoEsgotoSituacao.id, "                                  
				+"   imov.ligacaoAgua.ligacaoAguaPerfil.id, imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "       
				+"   guiaPagamento.debitoCreditoSituacaoAtual.id, imov.quadra.rota.empresa.id, "                  
				+"   imov.consumoTarifa.id, imov.quadra.rota.faturamentoGrupo.id, guiaPagamento.debitoTipo.id, imov.id, rota.codigo "                                        
				+"     "
				+"     "
				+" order by " 
				+"   guiaPagamento.lancamentoItemContabil.id, loca.gerenciaRegional.id, loca.unidadeNegocio.id, "                      
				+"   loca.localidade.id, loca.id, setor.id, imov.quadra.rota.id, "                                     
				+"   imov.quadra.id, setor.codigo, imov.quadra.numeroQuadra, imov.imovelPerfil.id, "                                               
				+"   imov.ligacaoAguaSituacao.id, imov.ligacaoEsgotoSituacao.id, "                                  
				+"   imov.ligacaoAgua.ligacaoAguaPerfil.id, imov.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "       
				+"   guiaPagamento.debitoCreditoSituacaoAtual.id, imov.quadra.rota.empresa.id, "                  
				+"   imov.consumoTarifa.id, imov.quadra.rota.faturamentoGrupo.id, guiaPagamento.debitoTipo.id  ";                                        

			retorno = session.createQuery(hql).setInteger("anoMes",anoMes)
											  .setInteger("idSetor", idSetor).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
    	
    }
	  
}
