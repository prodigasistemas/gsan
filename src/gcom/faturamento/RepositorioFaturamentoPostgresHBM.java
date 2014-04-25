package gcom.faturamento;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador 
 */
public class RepositorioFaturamentoPostgresHBM extends RepositorioFaturamentoHBM {

	/**
	 * [UC1051] Gerar Relatório de Amostragem das Anormalidades Informadas
	 * 
	 * @author Hugo Leonardo
	 * @date 09/08/2010
	 * 
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,Integer idCategoria, Integer limite)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {
			
			consulta = "SELECT "
				+ " grupoFat.ftgr_id as idGrupoFaturamento," 
				+ " grupoFat.ftgr_dsfaturamentogrupo as descricaoGrupoFaturamento," 
				+ " greg.greg_id as idGerencia," 
				+ " greg.greg_nmabreviado as nomeGerencia," 
				+ " unidNeg.uneg_id as idUnidadeNegocio," 
				+ " unidNeg.uneg_nmabreviado as nomeUnidadeNegocio," 
				+ " elo.loca_id as idElo," 
				+ " elo.loca_nmlocalidade as nomeElo," 
				+ " loc.loca_id as idLocalidade," 
				+ " loc.loca_nmlocalidade as nomeLocalidade," 
				+ " mrem.imov_id as idImovel," 
				+ " clieUsuario.clie_nmcliente as nomeUsuario,"
				+ " mrem.last_id as sitLigAgua," 
				+ " mrem.lest_id as sitLigEsgoto," 
				+ " imov.imov_icdebitoconta as indicadorDebito, " 
				+ " mrem.mrem_nnconsumomedio as consumoMedio, "
				+ " consHist.cshi_nnconsumofaturadomes as consumoMes," 
				+ " consAnor.csan_dsabrvconsanormalidade as anormalidadeConsumo," 
				+ " mrem.ltan_id as anormalidadeLeitura," 
				+ " mrem.mrem_qteconomias as qtdeEconomias," 
				+ " mrem.medt_id as tipoMedicao,";

		if (tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA)) {
			consulta = consulta
					+ " hidrCapAgua.hicp_dsabreviadahidrcapacidade as capHidAgua, "
					+ " hidrLocInstAgua.hili_dshidmtlocalinstalacao as locInstHidAgua, ";
		} else {
			consulta = consulta
					+ " hidrCapPoco.hicp_dsabreviadahidrcapacidade as capHidPoco, "
					+ " hidrLocInstPoco.hili_dshidmtlocalinstalacao as locInstHidPoco, ";
		}

		consulta = consulta
				+ " setor.stcm_id as idSetorComercial, "
				+ " setor.stcm_cdsetorcomercial as codigoSetorComercial,"
				+ " medHist.mdhi_nnleituraatualinformada as nnLeituraInformada,"
				+ " empr.empr_id as idEmpresa," 
				+ " empr.empr_nmempresa as nomeEmpresa, "
				
				+ " mrem.mrem_inscricaoimovel as inscricaoImovel,"
				+ " mrem_enderecoimovel as enderecoImovel"

				+ " FROM micromedicao.movimento_roteiro_empr     mrem"
				+ " INNER JOIN faturamento.faturamento_grupo        grupoFat 		on grupoFat.ftgr_id = mrem.ftgr_id"
				+ " INNER JOIN cadastro.gerencia_regional 			greg 			on greg.greg_id = mrem.greg_id"
				+ " INNER JOIN cadastro.localidade 					loc 			on loc.loca_id  = mrem.loca_id"
				+ " INNER JOIN cadastro.localidade 					elo 			on elo.loca_id = loc.loca_cdelo" 
				+ " INNER JOIN cadastro.unidade_negocio 			unidNeg 		on unidNeg.uneg_id = loc.uneg_id" 
				+ " INNER JOIN cadastro.imovel 					    imov 			on imov.imov_id = mrem.imov_id"
				+ " INNER JOIN cadastro.setor_comercial 			setor 			on setor.stcm_id = imov.stcm_id" 
				+ " INNER JOIN cadastro.cliente_imovel 				clieImovUsuario on clieImovUsuario.imov_id = imov.imov_id" 
				+ " and clieImovUsuario.crtp_id = " + ClienteRelacaoTipo.USUARIO 
				+ " and clieImovUsuario.clim_dtrelacaofim is null "
				+ " INNER JOIN cadastro.cliente 					clieUsuario 	on clieUsuario.clie_id = clieImovUsuario.clie_id" 
				+ " INNER JOIN cadastro.empresa 					empr 			on empr.empr_id = mrem.empr_id";

		
		if (tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA)) {
			consulta = consulta
			+ " INNER JOIN micromedicao.consumo_historico 		consHist 		on consHist.imov_id = imov.imov_id" 
			+ " and consHist.lgti_id =  " + LigacaoTipo.LIGACAO_AGUA
			+ " and consHist.cshi_amfaturamento = :referencia "
			+ " INNER JOIN micromedicao.medicao_historico 		medHist 		on medHist.lagu_id = imov.imov_id" 
			+ " and medHist.medt_id =  " + MedicaoTipo.LIGACAO_AGUA
			+ " and medHist.mdhi_amleitura = :referencia ";

		} else {
			consulta = consulta
			+ " INNER JOIN micromedicao.consumo_historico 		consHist 		on consHist.imov_id = imov.imov_id" 
			+ " and consHist.lgti_id =  " + LigacaoTipo.LIGACAO_ESGOTO
			+ " and consHist.cshi_amfaturamento = :referencia "
			+ " INNER JOIN micromedicao.medicao_historico 		medHist 		on medHist.imov_id = imov.imov_id" 
			+ " and medHist.medt_id =  " + MedicaoTipo.POCO
			+ " and medHist.mdhi_amleitura = :referencia ";
		}

		consulta = consulta
			+ " LEFT OUTER JOIN micromedicao.consumo_anormalidade 	consAnor 		on consAnor.csan_id = consHist.csan_id"
			+ " LEFT OUTER JOIN micromedicao.leitura_anormalidade 	leitAnor 		on leitAnor.ltan_id = medhist.ltan_idleitanormfatmt"; 
 

		if (tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA)) {
			consulta = consulta
					+ " LEFT OUTER JOIN atendimentopublico.ligacao_agua 				lagu" 
					+ "     on lagu.lagu_id = imov.imov_id "
					+ " LEFT OUTER JOIN micromedicao.hidrometro_inst_hist 	hidrInstHistAgua" 
					+ " 	on hidrInstHistAgua.hidi_id = lagu.hidi_id "
					+ " LEFT OUTER JOIN micromedicao.hidrometro 						hidrAgua" 
					+ " 	on hidrAgua.hidr_id = hidrInstHistAgua.hidr_id "
					+ " LEFT OUTER JOIN micromedicao.hidrometro_capacidade 				hidrCapAgua" 
					+ " 	on hidrCapAgua.hicp_id = hidrAgua.hicp_id "
					+ " LEFT OUTER JOIN micromedicao.hidrometro_local_inst 		hidrLocInstAgua" 
					+ " 	on hidrLocInstAgua.hili_id = hidrInstHistAgua.hili_id ";
		} else {
			consulta = consulta
					+ " LEFT OUTER JOIN micromedicao.hidrometro_inst_hist 	hidrInstHistPoco " 
					+ "		on hidrInstHistPoco.hidi_id = imov.hidi_id "
					+ " LEFT OUTER JOIN micromedicao.hidrometro 						hidrPoco" 
					+ " 	on hidrPoco.hidr_id = hidrInstHistPoco.hidr_id "
					+ " LEFT OUTER JOIN micromedicao.hidrometro_capacidade 				hidrCapPoco " 
					+ "		on hidrCapPoco.hicp_id = hidrPoco.hicp_id "
					+ " LEFT OUTER JOIN micromedicao.hidrometro_local_inst 		hidrLocInstPoco " 
					+ "		on hidrLocInstPoco.hili_id = hidrInstHistPoco.hili_id ";
		}

		consulta = consulta
				+ criarCondicionaisRelatorioAnormalidadesConsumo(
						idGrupoFaturamento, codigoRota, idGerenciaRegional,
						idUnidadeNegocio, idLocalidadeInicial,
						idLocalidadeFinal, idSetorComercialInicial,
						idSetorComercialFinal, referencia, idImovelPerfil,
						numOcorConsecutivas, indicadorOcorrenciasIguais,
						mediaConsumoInicial, mediaConsumoFinal,
						colecaoIdsAnormalidadeConsumo,
						colecaoIdsAnormalidadeLeitura,
						colecaoIdsAnormalidadeLeituraInformada,
						colecaoIdsEmpresa, numeroQuadraInicial,  
						numeroQuadraFinal, idCategoria) ;

		consulta = consulta
				+ " ORDER BY grupoFat.ftgr_id, greg.greg_id, unidNeg.uneg_id, elo.loca_id, "
				+ " loc.loca_id, setor.stcm_cdsetorcomercial, empr.empr_id, imov.imov_id "
				+ " limit :limite ";
				

		SQLQuery query = session.createSQLQuery(consulta);

		if (colecaoIdsAnormalidadeConsumo != null
				&& colecaoIdsAnormalidadeConsumo.size() > 0) {
			query.setParameterList("colecaoIdsAnormalidadeConsumo",
					colecaoIdsAnormalidadeConsumo);
		}

		if (colecaoIdsAnormalidadeLeitura != null
				&& colecaoIdsAnormalidadeLeitura.size() > 0) {
			query.setParameterList("colecaoIdsAnormalidadeLeitura",
					colecaoIdsAnormalidadeLeitura);
		}

		if (colecaoIdsAnormalidadeLeituraInformada != null
				&& colecaoIdsAnormalidadeLeituraInformada.size() > 0) {
			query.setParameterList(
					"colecaoIdsAnormalidadeLeituraInformada",
					colecaoIdsAnormalidadeLeituraInformada);
		}

		if (colecaoIdsEmpresa != null && colecaoIdsEmpresa.size() > 0) {
			query.setParameterList("colecaoIdsEmpresa", colecaoIdsEmpresa);
		}

		if (tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA)) {
			retorno = query.addScalar("idGrupoFaturamento",	Hibernate.INTEGER)
				.addScalar("descricaoGrupoFaturamento", Hibernate.STRING)
				.addScalar("idGerencia", Hibernate.INTEGER)
				.addScalar("nomeGerencia", Hibernate.STRING)
				.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
				.addScalar("nomeUnidadeNegocio", Hibernate.STRING)
				.addScalar("idElo", Hibernate.INTEGER)
				.addScalar("nomeElo", Hibernate.STRING)
				.addScalar("idLocalidade", Hibernate.INTEGER)
				.addScalar("nomeLocalidade", Hibernate.STRING)
				.addScalar("idImovel", Hibernate.INTEGER)
				.addScalar("nomeUsuario", Hibernate.STRING)
				.addScalar("sitLigAgua", Hibernate.INTEGER)
				.addScalar("sitLigEsgoto", Hibernate.INTEGER)
				.addScalar("indicadorDebito", Hibernate.SHORT)
				.addScalar("consumoMedio", Hibernate.INTEGER)
				.addScalar("consumoMes", Hibernate.INTEGER)
				.addScalar("anormalidadeConsumo", Hibernate.STRING)
				.addScalar("anormalidadeLeitura", Hibernate.INTEGER)
				.addScalar("qtdeEconomias", Hibernate.SHORT)
				.addScalar("tipoMedicao", Hibernate.INTEGER)
				.addScalar("capHidAgua", Hibernate.STRING)
				.addScalar("locInstHidAgua", Hibernate.STRING)
				.addScalar("idSetorComercial", Hibernate.INTEGER)
				.addScalar("codigoSetorComercial", Hibernate.INTEGER)
				.addScalar("nnLeituraInformada", Hibernate.INTEGER)
				.addScalar("idEmpresa", Hibernate.INTEGER)
				.addScalar("nomeEmpresa", Hibernate.STRING)
				.addScalar("inscricaoImovel", Hibernate.STRING)
				.addScalar("enderecoImovel", Hibernate.STRING)
				.setInteger("referencia", referencia)
				.setInteger("limite", limite)
				.list();
		} else {
			retorno = query.addScalar("idGrupoFaturamento",	Hibernate.INTEGER)
				.addScalar("descricaoGrupoFaturamento", Hibernate.STRING)
				.addScalar("idGerencia", Hibernate.INTEGER)
				.addScalar("nomeGerencia", Hibernate.STRING)
				.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
				.addScalar("nomeUnidadeNegocio", Hibernate.STRING)
				.addScalar("idElo", Hibernate.INTEGER)
				.addScalar("nomeElo", Hibernate.STRING)
				.addScalar("idLocalidade", Hibernate.INTEGER)
				.addScalar("nomeLocalidade", Hibernate.STRING)
				.addScalar("idImovel", Hibernate.INTEGER)
				.addScalar("nomeUsuario", Hibernate.STRING)
				.addScalar("sitLigAgua", Hibernate.INTEGER)
				.addScalar("sitLigEsgoto", Hibernate.INTEGER)
				.addScalar("indicadorDebito", Hibernate.SHORT)
				.addScalar("consumoMedio", Hibernate.INTEGER)
				.addScalar("consumoMes", Hibernate.INTEGER)
				.addScalar("anormalidadeConsumo", Hibernate.STRING)
				.addScalar("anormalidadeLeitura", Hibernate.INTEGER)
				.addScalar("qtdeEconomias", Hibernate.SHORT)
				.addScalar("tipoMedicao", Hibernate.INTEGER)
				.addScalar("capHidPoco", Hibernate.STRING)
				.addScalar("locInstHidPoco", Hibernate.STRING)
				.addScalar("idSetorComercial", Hibernate.INTEGER)
				.addScalar("codigoSetorComercial", Hibernate.INTEGER)
				.addScalar("nnLeituraInformada", Hibernate.INTEGER)
				.addScalar("idEmpresa", Hibernate.INTEGER)
				.addScalar("nomeEmpresa", Hibernate.STRING)
				.addScalar("inscricaoImovel", Hibernate.STRING)
				.addScalar("enderecoImovel", Hibernate.STRING)
				.setInteger("referencia", referencia)
				.setInteger("limite", limite)
				.list();
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
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 10/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
    public Integer gerarSequencialContaBoleto() throws ErroRepositorioException {

        Session session = HibernateUtil.getSession();

        Integer retorno = null;
        String consulta = null;
        
        try {
        	
    		consulta = "select nextval('faturamento.seq_conta_numero_boleto') as sequencial ";
    		
            retorno = (Integer) session.createSQLQuery(consulta).addScalar("sequencial", Hibernate.INTEGER).uniqueResult();

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
