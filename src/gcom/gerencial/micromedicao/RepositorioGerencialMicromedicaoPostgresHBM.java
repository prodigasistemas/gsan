package gcom.gerencial.micromedicao;

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
public class RepositorioGerencialMicromedicaoPostgresHBM extends
		RepositorioGerencialMicromedicaoHBM {

	/**
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Arthur Carvalho
	 * @date 09/12/2010
	 * @alteracao: Alterado o esquema de cadastro para micromedicao.
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	@Override
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicao(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();
			/**
			 * @author Adriana Muniz
			 * @date 18/10/2013
			 * Alteração do nextval para o formatto aceito pelo postgres */

			consulta = "INSERT INTO micromedicao.un_resi_des_mmd "
					+ " SELECT (nextval('micromedicao.seq_un_resi_des_mmd')), "
					+ " reca_amreferencia, to_number(reca_anoreferencia, 9999), "
					+ " reca_mesreferencia, greg_id, uneg_id, loca_id, loca_cdelo, "
					+ " stcm_id, qdra_id, rota_id, reca_cdsetorcomercial, reca_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, "
					+ " lapf_id, lepf_id, reca_qtligacoes_ativas, reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, reca_qteconomias_com_hidrometro, reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, reca_consumoagua_com_hidrometro, reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, reca_vofaturadoaguamedido, reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, rece_qteconomias, rece_voesgoto, "
					+ " rece_vofaturadoesgoto, rece_vofaturadoesgotomedido, rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, relt_qtleituras_efetuadas, relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, reih_qthidrometro_substituido_ramal, reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, reih_qthidrometro_substituido_poco, reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados,  now() "
					+ " FROM micromedicao.vw_un_resi_des_mmd ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE reca_amreferencia > "
						+ anoMesReferenciaIndicador
						+ " and reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

			//consulta += "\n limit 1";
			System.out.println("inicio Batch atualizarDadosResumoIndicadorDesempenhoMicromedicao:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadorDesempenhoMicromedicao:" + new Date());

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
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao_ref_2010
	 * 
	 * @author Arthur Carvalho
	 * @date 09/12/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	@Override
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO micromedicao.un_resi_des_mmd_ref_2010 (" 
					+ " reca_id, "
					+ " reca_amreferencia, "
					+ " greg_id, "
					+ " uneg_id, "
					+ " loca_id, "
					+ " loca_cdelo, "
					+ " stcm_id, "
					+ " reca_cdsetorcomercial, "
					+ " iper_id, "
					+ " last_id, "
					+ " lest_id, "
					+ " catg_id, "
					+ " epod_id, "
					+ " cltp_id, "
					+ " lapf_id, "
					+ " lepf_id, "
					+ " reca_qtligacoes_ativas, "
					+ " reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, "
					+ " reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, "
					+ " reca_qteconomias_com_hidrometro, "
					+ " reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, "
					+ " reca_consumoagua_com_hidrometro, "
					+ " reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, "
					+ " reca_vofaturadoaguamedido, "
					+ " reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, "
					+ " rece_qteconomias, "
					+ " rece_voesgoto, "
					+ " rece_vofaturadoesgoto, "
					+ " rece_vofaturadoesgotomedido, "
					+ " rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, "
					+ " relt_qtleituras_efetuadas, "
					+ " relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, "
					+ " reih_qthidrometro_substituido_ramal, "
					+ " reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, "
					+ " reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, "
					+ " reih_qthidrometro_substituido_poco, "
					+ " reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, "
					+ " reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados, "
					+ " reca_tmultimaalteracao "
					+ " ) "
					+ " SELECT "
					+ " micromedicao.seq_un_resi_des_mmd_ref_2007.nextval, "
					+ " reca_amreferencia, " 
					//+ " to_number(reca_anoreferencia, 9999), "
					//+ " reca_mesreferencia," 
					+ " greg_id, " 
					+ " uneg_id, " 
					+ " loca_id, " 
					+ " loca_cdelo, "
					+ " stcm_id, " 
					//+ " qdra_id, " 
					//+ " rota_id, " 
					+ " reca_cdsetorcomercial, " 
					//+ " reca_nnquadra, "
					+ " iper_id, " 
					+ " last_id, " 
					+ " lest_id, " 
					+ " catg_id, " 
					//+ " scat_id, " 
					+ " epod_id, " 
					+ " cltp_id, "
					+ " lapf_id, " 
					+ " lepf_id, " 
					+ " reca_qtligacoes_ativas, " 
					+ " reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, " 
					+ " reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, " 
					+ " reca_qteconomias_com_hidrometro, " 
					+ " reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, " 
					+ " reca_consumoagua_com_hidrometro, " 
					+ " reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, " 
					+ " reca_vofaturadoaguamedido, " 
					+ " reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, " 
					+ " rece_qteconomias, " 
					+ " rece_voesgoto, "
					+ " rece_vofaturadoesgoto, " 
					+ " rece_vofaturadoesgotomedido, " 
					+ " rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, " 
					+ " relt_qtleituras_efetuadas, " 
					+ " relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, " 
					+ " reih_qthidrometro_substituido_ramal, " 
					+ " reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, " 
					+ " reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, " 
					+ " reih_qthidrometro_substituido_poco, " 
					+ " reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, " 
					+ " reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados," 
					+ "  now() "
					+ " FROM micromedicao.vw_un_resi_des_mmd ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE reca_amreferencia > "
						+ anoMesReferenciaIndicador
						+ " and reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

			System.out.println("inicio Batch atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno:" + new Date());

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
