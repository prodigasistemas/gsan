package gcom.gerencial.faturamento;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/** 
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialFaturamentoPostgresHBM extends RepositorioGerencialFaturamentoHBM {

	
	/**
	 * Atualiza os dados na tabela un_resumo_indicadores_faturamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Arthur Carvalho
	 * @date 09/12/2010
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
					+ " faturamento.seq_un_res_ind_fat.nextval,  now() "
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
	
	
	
	
	
		
}
