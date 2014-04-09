package gcom.util;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cobranca.DocumentoTipo;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GeradorSqlRelatorio {

	private String condicionalFixo = new String(" re.ltan_id = la.ltan_id ");

	private String somatorio = new String(
			",sum(re.real_qtdmedicao) as somatorio ");

	private String nomeCampoFixo = new String(
			",la.ltan_dsleituraanormalidade as descricao, re.medt_id as idMedicaoTipo ");

	private String nomeTabelaFixo = new String(
			"micromedicao.resumo_anorm_leitura re, "
					+ "micromedicao.leitura_anormalidade la ");

	private String nomeTabelaFixoTotal = new String(
			"micromedicao.resumo_anorm_leitura re, "
					+ "micromedicao.leitura_anormalidade la ");

	private String condicionalResumo = "";

	public static final int ANORMALIDADE_LEITURA = 1;

	public static final int ANALISE_FATURAMENTO = 2;

	public static final int PENDENCIA = 3;

	public static final int ANORMALIDADE_CONSUMO = 4;

	public static final int RESUMO_LIGACOES_ECONOMIAS = 5;

	public static final int RESUMO_FATURAMENTO = 6;

	public static final int RESUMO_ARRECADACAO = 7;

	public static final int RESUMO_PENDENCIA = 8;

	public static final int RESUMO_REGISTRO_ATENDIMENTO = 9;
	
	public static final int RESUMO_HIDROMETRO = 10;

	private Collection<GeradorSqlRelatorio.ConfiguracaoGeradorSqlRelatorio> configuracoes = new ArrayList();

	public GeradorSqlRelatorio() {
	}

	// ------------ NIVEL 1

	public String sqlNivelUmPendencia() {

		String sql = "";

		// String nomeCampoFixo = this.getNomeCampoFixo();
		String nomeTabelaFixo = this.getNomeTabelaFixo();

		// String nomeTabelaFixoTotal = this.getNomeTabelaFixoTotal();
		String groupBy = " group by 1, b.cgtp_dscategoriatipo, c.catg_dscategoria, 4 , re.rpen_amreferencia ";
		String orderBy = " order by 1,2,3,4,5 ";
		String condicionalFixo = this.getCondicionalFixo();

		if (this.configuracoes != null && !this.configuracoes.isEmpty()) {

			for (ConfiguracaoGeradorSqlRelatorio configuracao : this.configuracoes) {

				String condicionalLigacao = configuracao
						.getCondicionalLigacao();
				String campo = configuracao.getCampo();
				String tabela = configuracao.getTabela();
				String condicionalRestricao = configuracao
						.getCondicionalRestricao();

				sql = sql
						+ "select "
						+ "\'AA-ESTADO\' as estado, b.cgtp_dscategoriatipo as tipoCategoria, c.catg_dscategoria as Categoria, "
						+ campo + "re.rpen_amreferencia as anoMesReferencia"

						+ somatorio + " from " + nomeTabelaFixo + tabela
						+ "where " + condicionalFixo + condicionalLigacao
						+ this.condicionalResumo + condicionalRestricao
						+ groupBy + " union ";

			}
			// tirar o union do final da query
			// Botar o group by no final
			sql = sql.substring(0, sql.length() - 6) + orderBy;

		}

		return sql;
	}

	public String sqlNivelUm(String nomeCampoFixo, String nomeTabelaFixo,
			String nomeTabelaFixoTotal, String campo, String tabela,
			String condicionalLigacao, String condicionalRestricao,
			boolean uniao, boolean groupByNotUnion) {

		String sql = "";
		String sqlUnion = "";
		String condicionalLigacaoSemAnd = "";
		String groupBy = "";
		String orderBy  = "";
		
		if (condicionalLigacao.length() > 0) {
			condicionalLigacaoSemAnd = condicionalLigacao.substring(3,
					condicionalLigacao.length());
		}

		/*
		 * [UC0305] Consultar Análise do Faturamento (Adicionar o campo município no filtro)
		 * Neste caso a consulta deve retornar agrupados pelo município e ordenados
		 * pelo Id do município.
		 */
		if (groupByNotUnion) {
			groupBy = " group by muni.muni_id ";
			orderBy = " order by muni.muni_id ";
		}

		sql = "select " + campo + nomeCampoFixo + somatorio + " from "
				+ nomeTabelaFixo + tabela + "where " + this.condicionalFixo
				+ this.condicionalResumo + condicionalLigacao
				+ condicionalRestricao + groupBy;

		if (uniao) {
			sqlUnion = "union " + " select " + campo + ",\'AA - TOTAL\' "
					+ somatorio + "from " + nomeTabelaFixoTotal + tabela
					+ "where " + condicionalLigacaoSemAnd
					+ this.condicionalResumo + condicionalRestricao + groupBy
					+ " order by 1,2,3";
		}else{
			if(groupByNotUnion){
				sql = sql + orderBy;
			}
		}

		return sql + sqlUnion;

	}

	public String getCondicionalFixo() {
		return condicionalFixo;
	}

	public GeradorSqlRelatorio(
			int tipoConsulta,
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) {
		switch (tipoConsulta) {
		case GeradorSqlRelatorio.ANORMALIDADE_LEITURA:
			this.condicionalFixo = " re.ltan_id = la.ltan_id ";
			this.somatorio = ",sum(re.real_qtdmedicao) as somatorio ";
			this.nomeCampoFixo = ",la.ltan_dsleituraanormalidade as descricao ";
			this.nomeTabelaFixo = "micromedicao.resumo_anorm_leitura re, micromedicao.leitura_anormalidade la ";
			this.nomeTabelaFixoTotal = "micromedicao.resumo_anorm_leitura as re ,micromedicao.leitura_anormalidade la ";
			break;

		case GeradorSqlRelatorio.RESUMO_LIGACOES_ECONOMIAS:
			this.nomeCampoFixo = ", re.estado as estado"
					+ ", re.greg_id as idGerencia"
					+ ", re.gerencia as descricaoGerencia"
					+ ", re.elo_id as idElo" + ", re.elo as descricaoElo"
					+ ", re.loca_id as idLocalidade"
					+ ", re.localidade as descricaoLocalidade"
					+ ", re.stcm_id as idSetorComercial"
					+ ", re.rota as idRota"
					+ ", re.setor as descricaoSetorComercial"
					+ ", re.qdra_id as idQuadra"
					+ ", re.quadra as descricaoQuadra"
					+ ", re.last_id as idLigacaoAguaSituacao"
					+ ", re.sitagua as descricaoLigacaoAguaSituacao"
					+ ", re.lest_id as idLigacaoEsgotoSituacao"
					+ ", re.sitesgoto as descricaoLigacaoEsgotoSituacao"
					+ ", re.catg_id as idCategoria"
					+ ", re.categoria as descricaoCategoria"
					+ ", re.ligcomhidrometro as qtdLigacoesComHidrometro"
					+ ", re.ligsemhidrometro as qtdLigacoesSemHidrometro"
					+ ", re.ligtotal as qtdLigacoesTotal"
					+ ", re.ecocomhidrometro as qtdEconomiasComHidrometro"
					+ ", re.ecosemhidrometro as qtdEconomiasSemHidrometro"
					+ ", re.ecototal as qtdEconomiasTotal";
			this.nomeTabelaFixo = "cadastro.vw_resumo_ligacoes_economias re  ";
			this.condicionalFixo = this
					.condicionaisResumoLigacoesConsumos(informarDadosGeracaoRelatorioConsultaHelper);
			this.condicionalResumo = "";
			this.somatorio = "";
			break;

		case GeradorSqlRelatorio.ANORMALIDADE_CONSUMO:
			this.condicionalFixo = " re.csan_id = ca.csan_id ";
			this.somatorio = ",sum(re.reac_qtdligacao) as somatorio ";
			this.nomeCampoFixo = ",ca.csan_dsconsumoanormalidade as descricao ";
			this.nomeTabelaFixo = "micromedicao.resumo_anorm_consumo re, micromedicao.consumo_anormalidade ca ";
			this.nomeTabelaFixoTotal = "micromedicao.resumo_anorm_consumo as re ,micromedicao.consumo_anormalidade ca ";
			break;

		case GeradorSqlRelatorio.ANALISE_FATURAMENTO:
			this.condicionalFixo = "";
			this.somatorio = ",sum(re.rfts_qtcontas) as somatorio1, sum(re.rfts_qteconomia) as somatorio2, "
					+ "sum(re.rfts_nnconsumoagua) as somatorio3, sum(re.rfts_vlagua) as somatorio4, "
					+ "sum(re.rfts_nnconsumoesgoto) as somatorio5, sum(re.rfts_vlesgoto) as somatorio6, "
					+ "sum(re.rfts_vlcreditos) as somatorio7, sum(re.rfts_vldebitos) as somatorio8, sum(re.rfts_vlimpostos) as somatorio9 ";
			this.nomeCampoFixo = "";
			this.nomeTabelaFixo = "faturamento.resumo_fatur_simulacao re, cadastro.localidade localidade ";
			if(informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao() == 
				ConstantesSistema.CODIGO_ESTADO_MUNICIPIO ||
				informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao() == 
					ConstantesSistema.CODIGO_MUNICIPIO){
				this.nomeTabelaFixo = this.nomeTabelaFixo + ", cadastro.municipio muni ";
			}
			this.nomeTabelaFixoTotal = "";
			break;
		case GeradorSqlRelatorio.PENDENCIA:
			this.condicionalFixo = " re.catg_id = c.catg_id AND b.cgtp_id = c.cgtp_id ";
			this.somatorio = ",sum(re.rpen_qtligacoes) as somatorioLigacoes, sum(re.rpen_qtdocumentos) as somatorioDocumentos, sum(re.rpen_vldebito) as somatorioDebitos ";
			this.nomeCampoFixo = "";
			this.nomeTabelaFixo = " cobranca.resumo_pendencia re, cadastro.categoria_tipo b, cadastro.categoria c ";
			this.nomeTabelaFixoTotal = "";
			this.condicionalResumo = this.criarCondicionaisResumos(
					informarDadosGeracaoRelatorioConsultaHelper, "rpen");

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'POTENCIAL\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id = 1 ", "AND re.lest_id not in (3,5,6) "));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'FACTÍVEL\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id in (2,4) ",
					"AND re.lest_id not in (3,5,6) "));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'LIGADO DE ÁGUA\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id = 3 ", ""));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'CORTADO\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id = 5 ", ""));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'LIGADO SÓ DE ESGOTO\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id not in (3,5) ", "AND re.lest_id = 3 "));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'ESGOTO FORA DE USO\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id not in (3,5) ", "AND re.lest_id = 5 "));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'ESGOTO TAMPONADO\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id not in (3,5) ", "AND re.lest_id = 6 "));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'SUPRIMIDO TOTAL\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id = 6 ", "AND re.lest_id not in (3,5,6) "));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'SUPRIMIDO PARCIAL\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id = 7 ", "AND re.lest_id not in (3,5,6) "));

			configuracoes.add(this.new ConfiguracaoGeradorSqlRelatorio(
					"\'SUPRIMIDO A PEDIDO\' as tipoSituacaoAguaEsgoto, ", "",
					"AND re.last_id = 8 ", "AND re.lest_id not in (3,5,6) "));
			break;

		case GeradorSqlRelatorio.RESUMO_FATURAMENTO:
			this.condicionalFixo = "re.loca_id = eloPolo.loca_id ";
			this.somatorio = " sum(re.rfts_qtcontas) as fatcontas, sum(re.rfts_vlagua + re.rfts_vlesgoto + re.rfts_vldebitos - re.rfts_vlcreditos) as fatvalor";
			this.nomeCampoFixo = " as estado,";
			this.nomeTabelaFixo = "faturamento.resumo_fatur_simulacao re, cadastro.localidade eloPolo ";
			this.nomeTabelaFixoTotal = "";
			this.condicionalResumo = this.criarCondicionaisResumos(
					informarDadosGeracaoRelatorioConsultaHelper, "rfts");
			break;

		case GeradorSqlRelatorio.RESUMO_ARRECADACAO:
			this.condicionalFixo = "re.loca_id = eloPolo.loca_id and re.dotp_id="
					+ DocumentoTipo.CONTA + " ";
			this.somatorio = " sum(re.ardd_qtpagamentos) as arrcontas, sum(re.ardd_vlpagamentos) as arrvalor";
			this.nomeCampoFixo = " as estado,";
			this.nomeTabelaFixo = "arrecadacao.arrec_dados_diarios re, cadastro.localidade eloPolo ";
			this.nomeTabelaFixoTotal = "";
			this.condicionalResumo = this.criarCondicionaisResumos(
					informarDadosGeracaoRelatorioConsultaHelper, "ardd");
			break;
			
		case GeradorSqlRelatorio.RESUMO_PENDENCIA:
			this.condicionalFixo = "re.loca_id = eloPolo.loca_id and re.dotp_id="
					+ DocumentoTipo.CONTA + " ";
			this.somatorio = " sum(re.rpen_qtdocumentos) as pencontas," +
					"SUM ((coalesce (re.rpen_vlpendente_agua,0) + coalesce(re.rpen_vlpendente_esgoto,0) + coalesce(re.rpen_vlpendente_debitos,0))" +
					" - (coalesce(re.rpen_vlpendente_creditos,0) + coalesce(re.rpen_vlpendente_impostos,0)))  as penvalor";
			this.nomeCampoFixo = " as estado,";
			this.nomeTabelaFixo = "cobranca.un_resumo_pendencia re, cadastro.g_localidade eloPolo ";
			this.nomeTabelaFixoTotal = "";
			this.condicionalResumo = this.criarCondicionaisResumos(
					informarDadosGeracaoRelatorioConsultaHelper, "rpen");
			break;
		}

	}

	public void setCondicionalFixo(String condicionalFixo) {
		this.condicionalFixo = condicionalFixo;
	}

	public String getNomeCampoFixo() {
		return nomeCampoFixo;
	}

	public void setNomeCampoFixo(String nomeCampoFixo) {
		this.nomeCampoFixo = nomeCampoFixo;
	}

	public String getNomeTabelaFixo() {
		return nomeTabelaFixo;
	}

	public void setNomeTabelaFixo(String nomeTabelaFixo) {
		this.nomeTabelaFixo = nomeTabelaFixo;
	}

	public String getNomeTabelaFixoTotal() {
		return nomeTabelaFixoTotal;
	}

	public void setNomeTabelaFixoTotal(String nomeTabelaFixoTotal) {
		this.nomeTabelaFixoTotal = nomeTabelaFixoTotal;
	}

	public String getSomatorio() {
		return somatorio;
	}

	public void setSomatorio(String somatorio) {
		this.somatorio = somatorio;
	}

	public String getCondicionalResumo() {
		return condicionalResumo;
	}

	public void setCondicionalResumo(String condicionalResumo) {
		this.condicionalResumo = condicionalResumo;
	}

	public static void main(String[] args) {
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = new InformarDadosGeracaoRelatorioConsultaHelper();
		GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
				GeradorSqlRelatorio.PENDENCIA,
				informarDadosGeracaoRelatorioConsultaHelper);

		System.out.println(geradorSqlRelatorio.sqlNivelUmPendencia());

	}

	public class ConfiguracaoGeradorSqlRelatorio {

		private String campo, tabela, condicionalLigacao, condicionalRestricao;

		public ConfiguracaoGeradorSqlRelatorio(String campo, String tabela,
				String condicionalLigacao, String condicionalRestricao) {
			super();
			this.campo = campo;
			this.tabela = tabela;
			this.condicionalLigacao = condicionalLigacao;
			this.condicionalRestricao = condicionalRestricao;
		}

		public String getCampo() {
			return campo;
		}

		public void setCampo(String campo) {
			this.campo = campo;
		}

		public String getCondicionalLigacao() {
			return condicionalLigacao;
		}

		public void setCondicionalLigacao(String condicionalLigacao) {
			this.condicionalLigacao = condicionalLigacao;
		}

		public String getCondicionalRestricao() {
			return condicionalRestricao;
		}

		public void setCondicionalRestricao(String condicionalRestricao) {
			this.condicionalRestricao = condicionalRestricao;
		}

		public String getTabela() {
			return tabela;
		}

		public void setTabela(String tabela) {
			this.tabela = tabela;
		}

	}

	private String criarCondicionaisResumos(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper,
			String nomeColunaTabela) {

		String sql = " ";

		// A partir daqui sera montanda a parte dos condicionais da query
		// estas condicionais serão usadas se necessarias, o q determina seus
		// usos
		// são os parametros que veem carregados no objeto
		// InformarDadosGeracaoRelatorioConsultaHelper
		// que é recebido do caso de uso [UC0304] Informar Dados para Geração de
		// Relatorio ou COnsulta
		if (informarDadosGeracaoRelatorioConsultaHelper != null) {

			// Inicio Parametros simples
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getAnoMesReferencia() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getAnoMesReferencia().toString().equals("")) {
				if (nomeColunaTabela.equals("ardd")) {
					sql = sql
							+ " and re."
							+ nomeColunaTabela
							+ "_amreferenciaarrecadacao = "
							+ informarDadosGeracaoRelatorioConsultaHelper
									.getAnoMesReferencia() + " ";

				} else {
					sql = sql
							+ " and re."
							+ nomeColunaTabela
							+ "_amreferencia = "
							+ informarDadosGeracaoRelatorioConsultaHelper
									.getAnoMesReferencia() + " ";

				}
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getFaturamentoGrupo() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getFaturamentoGrupo().getId() != null) {
				sql = sql
						+ " and re."
						+ nomeColunaTabela
						+ "_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getFaturamentoGrupo().getId() + " ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getGerenciaRegional() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getGerenciaRegional().getId() != null) {
				sql = sql
						+ " and re.greg_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getGerenciaRegional().getId() + " ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getEloPolo() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getEloPolo()
							.getId() != null) {
				sql = sql
						+ " and eloPolo.loca_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getEloPolo().getId() + " ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getLocalidade() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getLocalidade().getId() != null) {
				sql = sql
						+ " and re.loca_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getLocalidade().getId() + " ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getSetorComercial().getId() != null) {
				sql = sql
						+ " and re.stcm_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getSetorComercial().getId() + " ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getQuadra() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getQuadra()
							.getId() != null) {
				sql = sql
						+ " and re.qdra_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getQuadra().getId() + " ";
			}

			// Inicio de parametros por colecão
			// sera lida a colecao e montado um IN() a partis dos id extraidos
			// de cada objeto da colecao.
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoImovelPerfil() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoImovelPerfil().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;

				sql = sql + " and re.iper_id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					sql = sql + imovelPerfil.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoAguaSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoLigacaoAguaSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoAguaSituacao().iterator();
				LigacaoAguaSituacao ligacaoAguaSituacao = null;

				sql = sql + " and re.last_id in (";
				while (iterator.hasNext()) {
					ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
					sql = sql + ligacaoAguaSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoEsgotoSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

				sql = sql + " and re.lest_id in (";
				while (iterator.hasNext()) {
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator
							.next();
					sql = sql + ligacaoEsgotoSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoCategoria() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoCategoria().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoCategoria().iterator();
				Categoria categoria = null;

				sql = sql + " and re.catg_id in (";
				while (iterator.hasNext()) {
					categoria = (Categoria) iterator.next();
					sql = sql + categoria.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoEsferaPoder() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoEsferaPoder().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoEsferaPoder().iterator();
				EsferaPoder esferaPoder = null;

				sql = sql + " and re.epod_id in (";
				while (iterator.hasNext()) {
					esferaPoder = (EsferaPoder) iterator.next();
					sql = sql + esferaPoder.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

		}

		// sql = Util.formatarHQL(sql, 4);

		return sql;
	}

	private String condicionaisResumoLigacoesConsumos(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) {

		String condicionaisAdicionais = "";

		int opcaoTotalizacao = informarDadosGeracaoRelatorioConsultaHelper
				.getOpcaoTotalizacao().intValue();

		if (opcaoTotalizacao == ConstantesSistema.CODIGO_QUADRA
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_LOCALIDADE_QUADRA) {

			condicionaisAdicionais = " re.loca_id = "
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getLocalidade().getId()
					+ " and re.stcm_id =  "
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getSetorComercial().getId()
					+ " and re.qdra_id =  "
					+ informarDadosGeracaoRelatorioConsultaHelper.getQuadra()
							.getId();

		} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_SETOR_COMERCIAL
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_ELO_POLO_SETOR_COMERCIAL) {

			condicionaisAdicionais = " re.loca_id =  "
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getLocalidade().getId()
					+ " and re.stcm_id = "
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getSetorComercial().getId()
					+ " and re.qdra_id = 0 ";
		} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_LOCALIDADE
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_ELO_POLO_LOCALIDADE
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO_LOCALIDADE) {

			condicionaisAdicionais = " re.loca_id = "
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getLocalidade().getId() + " and re.stcm_id = 0 "
					+ " and re.qdra_id = 0 ";
		} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_ELO_POLO
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_GERENCIA_REGIONAL_ELO_POLO
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO_ELO_POLO) {

			condicionaisAdicionais = " re.elo_id =  "
					+ informarDadosGeracaoRelatorioConsultaHelper.getEloPolo()
							.getId() + " and re.loca_id = 0 "
					+ " and re.stcm_id = 0 " + " and re.qdra_id = 0 ";
		} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_GERENCIA_REGIONAL
				|| opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO_GERENCIA_REGIONAL) {

			condicionaisAdicionais = " re.greg_id = "
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getGerenciaRegional().getId()
					+ " and re.elo_id = 0 " + " and re.loca_id = 0 "
					+ " and re.stcm_id = 0 " + " and re.qdra_id = 0 ";
		} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO) {
			condicionaisAdicionais = " re.greg_id = 0 " + " and re.elo_id = 0 "
					+ " and re.loca_id = 0 " + " and re.stcm_id = 0 "
					+ " and re.qdra_id = 0 ";
		}

		// Inicio de parametros por colecão
		// sera lida a colecao e montado um IN() a partis dos id extraidos de
		// cada objeto da colecao.
		if (informarDadosGeracaoRelatorioConsultaHelper
				.getColecaoImovelPerfil() != null
				&& !informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoImovelPerfil().isEmpty()) {

			Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoImovelPerfil().iterator();
			ImovelPerfil imovelPerfil = null;

			condicionaisAdicionais = condicionaisAdicionais
					+ " and re.iper_id in (";
			while (iterator.hasNext()) {
				imovelPerfil = (ImovelPerfil) iterator.next();
				condicionaisAdicionais = condicionaisAdicionais
						+ imovelPerfil.getId() + ",";
			}
			condicionaisAdicionais = Util
					.removerUltimosCaracteres(condicionaisAdicionais, 1);
			condicionaisAdicionais = condicionaisAdicionais + ") ";
		}

		if (informarDadosGeracaoRelatorioConsultaHelper
				.getColecaoLigacaoAguaSituacao() != null
				&& !informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoAguaSituacao().isEmpty()) {

			Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoAguaSituacao().iterator();
			LigacaoAguaSituacao ligacaoAguaSituacao = null;

			condicionaisAdicionais = condicionaisAdicionais
					+ " and re.last_id in (";
			while (iterator.hasNext()) {
				ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
				condicionaisAdicionais = condicionaisAdicionais
						+ ligacaoAguaSituacao.getId() + ",";
			}
			condicionaisAdicionais = Util
					.removerUltimosCaracteres(condicionaisAdicionais, 1);
			condicionaisAdicionais = condicionaisAdicionais + ") ";
		}

		if (informarDadosGeracaoRelatorioConsultaHelper
				.getColecaoLigacaoEsgotoSituacao() != null
				&& !informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

			Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoEsgotoSituacao().iterator();
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

			condicionaisAdicionais = condicionaisAdicionais
					+ " and re.lest_id in (";
			while (iterator.hasNext()) {
				ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
				condicionaisAdicionais = condicionaisAdicionais
						+ ligacaoEsgotoSituacao.getId() + ",";
			}
			condicionaisAdicionais = Util
					.removerUltimosCaracteres(condicionaisAdicionais, 1);
			condicionaisAdicionais = condicionaisAdicionais + ") ";
		}

		if (informarDadosGeracaoRelatorioConsultaHelper.getColecaoCategoria() != null
				&& !informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoCategoria().isEmpty()) {

			Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoCategoria().iterator();
			Categoria categoria = null;

			condicionaisAdicionais = condicionaisAdicionais
					+ " and re.catg_id in (";
			while (iterator.hasNext()) {
				categoria = (Categoria) iterator.next();
				condicionaisAdicionais = condicionaisAdicionais
						+ categoria.getId() + ",";
			}
			condicionaisAdicionais = Util
					.removerUltimosCaracteres(condicionaisAdicionais, 1);
			condicionaisAdicionais = condicionaisAdicionais + ") ";
		}

		if (informarDadosGeracaoRelatorioConsultaHelper.getColecaoEsferaPoder() != null
				&& !informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoEsferaPoder().isEmpty()) {

			Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoEsferaPoder().iterator();
			EsferaPoder esferaPoder = null;

			condicionaisAdicionais = condicionaisAdicionais
					+ " and re.epod_id in (";
			while (iterator.hasNext()) {
				esferaPoder = (EsferaPoder) iterator.next();
				condicionaisAdicionais = condicionaisAdicionais
						+ esferaPoder.getId() + ",";
			}
			condicionaisAdicionais = Util
					.removerUltimosCaracteres(condicionaisAdicionais, 1);
			condicionaisAdicionais = condicionaisAdicionais + ") ";
		}

		return condicionaisAdicionais;

	}
}
