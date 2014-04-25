package gcom.gerencial.cobranca;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialCobrancaPostgresHBM extends
		RepositorioGerencialCobrancaHBM {

	/**
	 * Atualiza os dados na tabela un_resumo_indicadores_cobranca
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	@Override
	public void atualizarDadosResumoIndicadoresCobranca(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas) throws ErroRepositorioException {
		
		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO cobranca.un_res_ind_cob "
					+ " select cobranca.seq_un_res_ind_cob.nextval, "
					+ " rpen_amreferencia, rpen_anoreferencia, rpen_mesreferencia, "
					+ " greg_id, uneg_id, loca_id, loca_cdelo, stcm_id, qdra_id, rota_id, rpen_cdsetorcomercial, rpen_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, lapf_id, lepf_id, "
					+ " rpen_qtcontaspendentesma, rpen_vlpendente_contama, rpen_qtligacoes, rpen_qtligacoesativas, "
					+ " rpen_qtdocumentos, rpen_qtcontaspendentes, rpen_vlpendente_total, rpen_vlpendente_conta, rpen_vlpendente_servicos, "
					+ " rpen_vlpendente_parcelamento, rele_qtligacoesativasagua, rele_qtligacoesinativasagua, "
					+ " rele_qtligacoessuprimidas, rele_qtligacoestotaisagua, "
					+ " rear_qtcontasrecebidas, rear_vlarrecadado, rear_vlarrecacadomesatevencimento, "
					+ " rear_vlarrecacadomesaposvencimento, rear_vlarrecacado2mes, rear_vlarrecacado3mes, rear_vlarrecacadoacumuladoate3mes, "
					+ " rear_vlarrecadado_parcelamento, refa_qtcontasfaturamentoliquido, refa_qtcontasfaturamentoliquidoma, refa_vlfaturamentoliquido, "
					+ " refa_vlfaturamentoliquidoma, repa_qtparcelamentos, repa_qtcontaseguias, repa_vlnegociado, repa_vlfinanciado, repa_vlparcelado, "
					+ " rlig_qtcortes, rlig_qtsupressoes, rlig_qtreligacoes, rlig_qtreestabelecimentos,  now() "
					+ " FROM cobranca.vw_un_res_ind_cob ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE rpen_amreferencia > "
						+ anoMesReferenciaIndicador + " and rpen_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE rpen_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

//			consulta += "\n limit 1";
			stmt.executeUpdate(consulta);

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


