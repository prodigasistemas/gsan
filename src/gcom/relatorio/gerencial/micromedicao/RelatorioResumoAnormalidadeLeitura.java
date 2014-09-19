package gcom.relatorio.gerencial.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.micromedicao.ResumoAnormalidadeConsultaHelper;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioResumoAnormalidadeLeitura extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoAnormalidadeLeitura(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_ELO);
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// public Map<Integer, byte[]> gerarRelatorioBairroManter(
		// Collection bairros,
		// Bairro bairroParametros,
		// int tipoFormatoRelatorio)
		//		
		// throws RelatorioVazioException {

		Collection colecaoResumosAnormalidadeLeitura = (Collection) getParametro("colecaoResumosAnormalidadeLeitura");
		int opcaoTotalizacao = ((Integer) getParametro("opcaoTotalizacao"))
				.intValue();
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();

		// String tipoQuebra = null;

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Collection relatorioQuadraBeans = new ArrayList();

		Collection relatorioAnormalidadeBeans = new ArrayList();

		Collection relatorioAnormalidadeAguaBeans = new ArrayList();

		Collection relatorioAnormalidadeEsgotoBeans = new ArrayList();

		RelatorioResumoAnormalidadeLeituraSetorComercialBean relatorioBean = null;

		RelatorioResumoAnormalidadeLeituraQuadraBean relatorioQuadraBean = null;
		
		RelatorioResumoAnormalidadeLeituraLocalidadeBean relatorioLocalidadeBean = null;
		
		RelatorioResumoAnormalidadeLeituraEloBean relatorioEloBean = null;
		
		RelatorioResumoAnormalidadeLeituraGerenciaRegionalBean relatorioGerenciaRegionalBean = null;
		
		RelatorioResumoAnormalidadeLeituraEstadoBean relatorioEstadoBean = null;

		RelatorioResumoAnormalidadeLeituraAnormalidadeBean relatorioAnormalidadeBean = null;
		
		String nomeRelatorio = "";

		String numeroQuadra = null;
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoResumosAnormalidadeLeitura != null
				&& !colecaoResumosAnormalidadeLeitura.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoResumosAnormalidadeLeituraIterator = colecaoResumosAnormalidadeLeitura
					.iterator();

			BigDecimal percentual = new BigDecimal("0.00");

			// int totalEstadoAgua = 0;
			// int totalEstadoEsgoto = 0;

			// laço para criar a coleção de parâmetros da analise

			ResumoAnormalidadeConsultaHelper resumoAnormalidadeConsultaHelper = null;

			if (opcaoTotalizacao == ConstantesSistema.CODIGO_SETOR_COMERCIAL) {

				nomeRelatorio = ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_SETOR;

				Integer totalSetorComercialAgua = null;
				Integer totalSetorComercialPoco = null;
				String codigoSetorComercial = null;
				String descricaoSetorComercial = null;
				String descricaoLocalidade = null;

				while (colecaoResumosAnormalidadeLeituraIterator.hasNext()) {

					resumoAnormalidadeConsultaHelper = (ResumoAnormalidadeConsultaHelper) colecaoResumosAnormalidadeLeituraIterator
							.next();

					if (codigoSetorComercial == null) {
						codigoSetorComercial = resumoAnormalidadeConsultaHelper
								.getCodigoSetorComercial();
						descricaoSetorComercial = resumoAnormalidadeConsultaHelper
								.getDescricaoSetorComercial();
					}

					if (descricaoLocalidade == null) {
						descricaoLocalidade = resumoAnormalidadeConsultaHelper
								.getDescricaoLocalidade();
					}

					if (totalSetorComercialAgua == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
							totalSetorComercialAgua = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (totalSetorComercialPoco == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.POCO.toString())) {
							totalSetorComercialPoco = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
							.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalSetorComercialAgua), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeAguaBeans
								.add(relatorioAnormalidadeBean);

					} else {
						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalSetorComercialPoco), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeEsgotoBeans
								.add(relatorioAnormalidadeBean);
					}
				}

				if (relatorioAnormalidadeAguaBeans != null
						&& !relatorioAnormalidadeAguaBeans.isEmpty()) {

					relatorioBean = new RelatorioResumoAnormalidadeLeituraSetorComercialBean(
							descricaoLocalidade, codigoSetorComercial + " - "
									+ descricaoSetorComercial, null, "ÁGUA",
							relatorioAnormalidadeAguaBeans);

					relatorioBeans.add(relatorioBean);

				}

				if (relatorioAnormalidadeEsgotoBeans != null
						&& !relatorioAnormalidadeEsgotoBeans.isEmpty()) {
					relatorioBean = new RelatorioResumoAnormalidadeLeituraSetorComercialBean(
							descricaoLocalidade, codigoSetorComercial + " - "
									+ descricaoSetorComercial, null, "POÇO",
							relatorioAnormalidadeEsgotoBeans);

					relatorioBeans.add(relatorioBean);
				}

			} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA) {

				nomeRelatorio = ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_SETOR;

				Integer totalSetorComercialAgua = null;
				Integer totalSetorComercialPoco = null;
				Integer totalQuadraAgua = null;
				Integer totalQuadraPoco = null;

				String codigoSetorComercial = null;
				String descricaoSetorComercial = null;
				String descricaoLocalidade = null;

				while (colecaoResumosAnormalidadeLeituraIterator.hasNext()) {

					if (resumoAnormalidadeConsultaHelper != null) {
						codigoSetorComercial = resumoAnormalidadeConsultaHelper
								.getCodigoSetorComercial();
						descricaoSetorComercial = resumoAnormalidadeConsultaHelper
								.getDescricaoSetorComercial();
					}

					resumoAnormalidadeConsultaHelper = (ResumoAnormalidadeConsultaHelper) colecaoResumosAnormalidadeLeituraIterator
							.next();

					if (codigoSetorComercial == null) {
						codigoSetorComercial = resumoAnormalidadeConsultaHelper
								.getCodigoSetorComercial();
						descricaoSetorComercial = resumoAnormalidadeConsultaHelper
								.getDescricaoSetorComercial();
					}

					if (numeroQuadra == null) {
						numeroQuadra = resumoAnormalidadeConsultaHelper
								.getNumeroQuadra();
					}

					if (descricaoLocalidade == null) {
						descricaoLocalidade = resumoAnormalidadeConsultaHelper
								.getDescricaoLocalidade();
					}

					// if (codigoSetorComercial
					// .equalsIgnoreCase(resumoAnormalidadeConsultaHelper
					// .getCodigoSetorComercial())) {

					if (!resumoAnormalidadeConsultaHelper.getNumeroQuadra()
							.equalsIgnoreCase("-1")) {

						if (totalQuadraAgua == null) {
							if (resumoAnormalidadeConsultaHelper
									.getIdMedicaoTipo()
									.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
								totalQuadraAgua = new Integer(
										resumoAnormalidadeConsultaHelper
												.getQuantidadeMedicao());
							}
						}

						if (totalQuadraPoco == null) {
							if (resumoAnormalidadeConsultaHelper
									.getIdMedicaoTipo().equals(
											MedicaoTipo.POCO.toString())) {
								totalQuadraPoco = new Integer(
										resumoAnormalidadeConsultaHelper
												.getQuantidadeMedicao());
							}
						}

						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
							percentual = (new BigDecimal(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao())).divide(
									new BigDecimal(totalQuadraAgua), 4,
									RoundingMode.HALF_UP);
							percentual = percentual.multiply(new BigDecimal(
									"100"));
						} else {
							percentual = (new BigDecimal(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao())).divide(
									new BigDecimal(totalQuadraPoco), 4,
									RoundingMode.HALF_UP);
							percentual = percentual.multiply(new BigDecimal(
									"100"));
						}

						relatorioQuadraBean = new RelatorioResumoAnormalidadeLeituraQuadraBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLocalidade(),
								resumoAnormalidadeConsultaHelper
										.getCodigoSetorComercial()
										+ " - "
										+ resumoAnormalidadeConsultaHelper
												.getDescricaoSetorComercial(),
								resumoAnormalidadeConsultaHelper
										.getNumeroQuadra(),
								resumoAnormalidadeConsultaHelper
										.getIdMedicaoTipo(),
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioQuadraBeans.add(relatorioQuadraBean);

					} else {

						if (totalSetorComercialAgua == null) {
							if (resumoAnormalidadeConsultaHelper
									.getIdMedicaoTipo()
									.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
								totalSetorComercialAgua = new Integer(
										resumoAnormalidadeConsultaHelper
												.getQuantidadeMedicao());
							}
						}

						if (totalSetorComercialPoco == null) {
							if (resumoAnormalidadeConsultaHelper
									.getIdMedicaoTipo().equals(
											MedicaoTipo.POCO.toString())) {
								totalSetorComercialPoco = new Integer(
										resumoAnormalidadeConsultaHelper
												.getQuantidadeMedicao());
							}
						}

						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

							percentual = (new BigDecimal(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao())).divide(
									new BigDecimal(totalSetorComercialAgua), 4,
									RoundingMode.HALF_UP);
							percentual = percentual.multiply(new BigDecimal(
									"100"));

							relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
									resumoAnormalidadeConsultaHelper
											.getDescricaoLeituraAnormalidadeFaturada(),
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao(), Util
											.formatarMoedaReal(percentual));

							relatorioAnormalidadeAguaBeans
									.add(relatorioAnormalidadeBean);

						} else {
							percentual = (new BigDecimal(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao())).divide(
									new BigDecimal(totalSetorComercialPoco), 4,
									RoundingMode.HALF_UP);
							percentual = percentual.multiply(new BigDecimal(
									"100"));

							relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
									resumoAnormalidadeConsultaHelper
											.getDescricaoLeituraAnormalidadeFaturada(),
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao(), Util
											.formatarMoedaReal(percentual));

							relatorioAnormalidadeEsgotoBeans
									.add(relatorioAnormalidadeBean);
						}
					}

				} // else {

				/*
				 * if (totalSetorComercial == null) { totalSetorComercial =
				 * resumoAnormalidadeLeitura .getQuantidadeMedicao(); }
				 * 
				 * percentual = (new BigDecimal(resumoAnormalidadeLeitura
				 * .getQuantidadeMedicao())).divide( new
				 * BigDecimal(totalSetorComercial)).multiply( new
				 * BigDecimal("100.00"));
				 */

				if (relatorioAnormalidadeAguaBeans != null
						&& !relatorioAnormalidadeAguaBeans.isEmpty()) {

					relatorioBean = new RelatorioResumoAnormalidadeLeituraSetorComercialBean(
							descricaoLocalidade, codigoSetorComercial + " - "
									+ descricaoSetorComercial, numeroQuadra,
							"ÁGUA", relatorioQuadraBeans,
							relatorioAnormalidadeBeans);

					relatorioBeans.add(relatorioBean);

				}

				if (relatorioAnormalidadeEsgotoBeans != null
						&& !relatorioAnormalidadeEsgotoBeans.isEmpty()) {

					relatorioBean = new RelatorioResumoAnormalidadeLeituraSetorComercialBean(
							descricaoLocalidade, codigoSetorComercial + " - "
									+ descricaoSetorComercial, numeroQuadra,
							"POÇO", relatorioQuadraBeans,
							relatorioAnormalidadeBeans);

					relatorioBeans.add(relatorioBean);
				}

				relatorioQuadraBeans.clear();

			} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_QUADRA) {

				nomeRelatorio = ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_QUADRA;

				Integer totalQuadraAgua = null;
				Integer totalQuadraPoco = null;

				while (colecaoResumosAnormalidadeLeituraIterator.hasNext()) {

					resumoAnormalidadeConsultaHelper = (ResumoAnormalidadeConsultaHelper) colecaoResumosAnormalidadeLeituraIterator
							.next();

					if (totalQuadraAgua == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
							totalQuadraAgua = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (totalQuadraPoco == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.POCO.toString())) {
							totalQuadraPoco = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
							.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalQuadraAgua), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioQuadraBean = new RelatorioResumoAnormalidadeLeituraQuadraBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLocalidade(),
								resumoAnormalidadeConsultaHelper
										.getCodigoSetorComercial()
										+ " - "
										+ resumoAnormalidadeConsultaHelper
												.getDescricaoSetorComercial(),
								resumoAnormalidadeConsultaHelper
										.getNumeroQuadra(),
								"ÁGUA",
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

					} else {
						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalQuadraPoco), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioQuadraBean = new RelatorioResumoAnormalidadeLeituraQuadraBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLocalidade(),
								resumoAnormalidadeConsultaHelper
										.getCodigoSetorComercial()
										+ " - "
										+ resumoAnormalidadeConsultaHelper
												.getDescricaoSetorComercial(),
								resumoAnormalidadeConsultaHelper
										.getNumeroQuadra(),
								"POÇO",
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

					}
					relatorioBeans.add(relatorioQuadraBean);
				}

			} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_LOCALIDADE) {

				nomeRelatorio = ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_LOCALIDADE;

				Integer totalLocalidadeAgua = null;
				Integer totalLocalidadePoco = null;
				String idLocalidade = null;
				String descricaoLocalidade = null;

				while (colecaoResumosAnormalidadeLeituraIterator.hasNext()) {

					resumoAnormalidadeConsultaHelper = (ResumoAnormalidadeConsultaHelper) colecaoResumosAnormalidadeLeituraIterator
							.next();

					if (idLocalidade == null) {
						idLocalidade = resumoAnormalidadeConsultaHelper
								.getIdLocalidade();
						descricaoLocalidade = resumoAnormalidadeConsultaHelper
								.getDescricaoLocalidade();
					}

					if (descricaoLocalidade == null) {
						descricaoLocalidade = resumoAnormalidadeConsultaHelper
								.getDescricaoLocalidade();
					}

					if (totalLocalidadeAgua == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
							totalLocalidadeAgua = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (totalLocalidadePoco == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.POCO.toString())) {
							totalLocalidadePoco = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
							.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalLocalidadeAgua), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeAguaBeans
								.add(relatorioAnormalidadeBean);

					} else {
						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalLocalidadePoco), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeEsgotoBeans
								.add(relatorioAnormalidadeBean);
					}
				}

				if (relatorioAnormalidadeAguaBeans != null
						&& !relatorioAnormalidadeAguaBeans.isEmpty()) {

					relatorioLocalidadeBean = new RelatorioResumoAnormalidadeLeituraLocalidadeBean(idLocalidade + " - "
									+ descricaoLocalidade, null, "ÁGUA",
							relatorioAnormalidadeAguaBeans);

					relatorioBeans.add(relatorioLocalidadeBean);

				}

				if (relatorioAnormalidadeEsgotoBeans != null
						&& !relatorioAnormalidadeEsgotoBeans.isEmpty()) {
					relatorioLocalidadeBean = new RelatorioResumoAnormalidadeLeituraLocalidadeBean(idLocalidade + " - "
							+ descricaoLocalidade, null, "POÇO",
							relatorioAnormalidadeEsgotoBeans);

					relatorioBeans.add(relatorioBean);
				}

			} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_ELO_POLO) {

				nomeRelatorio = ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_ELO;

				Integer totalEloAgua = null;
				Integer totalEloPoco = null;
				String idElo = null;
				String descricaoElo = null;

				while (colecaoResumosAnormalidadeLeituraIterator.hasNext()) {

					resumoAnormalidadeConsultaHelper = (ResumoAnormalidadeConsultaHelper) colecaoResumosAnormalidadeLeituraIterator
							.next();

					if (idElo == null) {
						idElo = resumoAnormalidadeConsultaHelper
								.getIdElo();
						descricaoElo = resumoAnormalidadeConsultaHelper
								.getDescricaoElo();
					}

					if (descricaoElo == null) {
						descricaoElo = resumoAnormalidadeConsultaHelper
								.getDescricaoLocalidade();
					}

					if (totalEloAgua == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
							totalEloAgua = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (totalEloPoco == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.POCO.toString())) {
							totalEloPoco = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
							.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalEloAgua), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeAguaBeans
								.add(relatorioAnormalidadeBean);

					} else {
						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalEloPoco), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeEsgotoBeans
								.add(relatorioAnormalidadeBean);
					}
				}

				if (relatorioAnormalidadeAguaBeans != null
						&& !relatorioAnormalidadeAguaBeans.isEmpty()) {

					relatorioEloBean = new RelatorioResumoAnormalidadeLeituraEloBean(idElo + " - "
									+ descricaoElo, null, "ÁGUA",
							relatorioAnormalidadeAguaBeans);

					relatorioBeans.add(relatorioEloBean);

				}

				if (relatorioAnormalidadeEsgotoBeans != null
						&& !relatorioAnormalidadeEsgotoBeans.isEmpty()) {
					relatorioEloBean = new RelatorioResumoAnormalidadeLeituraEloBean(idElo + " - "
							+ descricaoElo, null, "POÇO",
							relatorioAnormalidadeEsgotoBeans);

					relatorioBeans.add(relatorioEloBean);
				}

			}
			
			else if (opcaoTotalizacao == ConstantesSistema.CODIGO_GERENCIA_REGIONAL) {

				nomeRelatorio = ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_GERENCIA;

				Integer totalGerenciaRegionalAgua = null;
				Integer totalGerenciaRegionalPoco = null;
				String idGerenciaRegional = null;
				String descricaoGerenciaRegional = null;

				while (colecaoResumosAnormalidadeLeituraIterator.hasNext()) {

					resumoAnormalidadeConsultaHelper = (ResumoAnormalidadeConsultaHelper) colecaoResumosAnormalidadeLeituraIterator
							.next();

					if (idGerenciaRegional == null) {
						idGerenciaRegional = resumoAnormalidadeConsultaHelper
								.getIdElo();
						descricaoGerenciaRegional = resumoAnormalidadeConsultaHelper
								.getDescricaoGerenciaRegional();
					}

					if (descricaoGerenciaRegional == null) {
						descricaoGerenciaRegional = resumoAnormalidadeConsultaHelper
								.getDescricaoGerenciaRegional();
					}

					if (totalGerenciaRegionalAgua == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
							totalGerenciaRegionalAgua = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (totalGerenciaRegionalPoco == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.POCO.toString())) {
							totalGerenciaRegionalPoco = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
							.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalGerenciaRegionalAgua), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeAguaBeans
								.add(relatorioAnormalidadeBean);

					} else {
						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalGerenciaRegionalPoco), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeEsgotoBeans
								.add(relatorioAnormalidadeBean);
					}
				}

				if (relatorioAnormalidadeAguaBeans != null
						&& !relatorioAnormalidadeAguaBeans.isEmpty()) {

					relatorioGerenciaRegionalBean = new RelatorioResumoAnormalidadeLeituraGerenciaRegionalBean(idGerenciaRegional + " - "
									+ descricaoGerenciaRegional, null, "ÁGUA",
							relatorioAnormalidadeAguaBeans);

					relatorioBeans.add(relatorioGerenciaRegionalBean);

				}

				if (relatorioAnormalidadeEsgotoBeans != null
						&& !relatorioAnormalidadeEsgotoBeans.isEmpty()) {
					relatorioGerenciaRegionalBean = new RelatorioResumoAnormalidadeLeituraGerenciaRegionalBean(idGerenciaRegional + " - "
							+ descricaoGerenciaRegional, null, "POÇO",
							relatorioAnormalidadeEsgotoBeans);

					relatorioBeans.add(relatorioGerenciaRegionalBean);
				}

			} else if (opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO) {

				nomeRelatorio = ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_ESTADO;

				Integer totalEstadoAgua = null;
				Integer totalEstadoPoco = null;

				while (colecaoResumosAnormalidadeLeituraIterator.hasNext()) {

					resumoAnormalidadeConsultaHelper = (ResumoAnormalidadeConsultaHelper) colecaoResumosAnormalidadeLeituraIterator
							.next();

					if (totalEstadoAgua == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
							totalEstadoAgua = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (totalEstadoPoco == null) {
						if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
								.equals(MedicaoTipo.POCO.toString())) {
							totalEstadoPoco = new Integer(
									resumoAnormalidadeConsultaHelper
											.getQuantidadeMedicao());
						}
					}

					if (resumoAnormalidadeConsultaHelper.getIdMedicaoTipo()
							.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalEstadoAgua), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeAguaBeans
								.add(relatorioAnormalidadeBean);

					} else {
						percentual = (new BigDecimal(
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao())).divide(
								new BigDecimal(totalEstadoPoco), 4,
								RoundingMode.HALF_UP);
						percentual = percentual.multiply(new BigDecimal("100"));

						relatorioAnormalidadeBean = new RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
								resumoAnormalidadeConsultaHelper
										.getDescricaoLeituraAnormalidadeFaturada(),
								resumoAnormalidadeConsultaHelper
										.getQuantidadeMedicao(), Util
										.formatarMoedaReal(percentual));

						relatorioAnormalidadeEsgotoBeans
								.add(relatorioAnormalidadeBean);
					}
				}

				if (relatorioAnormalidadeAguaBeans != null
						&& !relatorioAnormalidadeAguaBeans.isEmpty()) {

					relatorioEstadoBean = new RelatorioResumoAnormalidadeLeituraEstadoBean(
									"PERNAMBUCO", null, "ÁGUA",
							relatorioAnormalidadeAguaBeans);

					relatorioBeans.add(relatorioEstadoBean);

				}

				if (relatorioAnormalidadeEsgotoBeans != null
						&& !relatorioAnormalidadeEsgotoBeans.isEmpty()) {
					relatorioEstadoBean = new RelatorioResumoAnormalidadeLeituraEstadoBean(
							"PERNAMBUCO", null, "POÇO",
							relatorioAnormalidadeEsgotoBeans);

					relatorioBeans.add(relatorioEstadoBean);
				}

			}

		}

		/*
		 * relatorioBean = new RelatorioResumoAnormalidadeLeituraBean(
		 * "Pernambuco", resumoAnormalidadeLeitura.getGerenciaRegional()
		 * .getId().toString(), resumoAnormalidadeLeitura
		 * .getGerenciaRegional().getNomeAbreviado(),
		 * resumoAnormalidadeLeitura.getLocalidade()
		 * .getLocalidade().getId().toString(),
		 * resumoAnormalidadeLeitura.getLocalidade()
		 * .getLocalidade().getDescricao(),
		 * resumoAnormalidadeLeitura.getLocalidade().getId() .toString(),
		 * resumoAnormalidadeLeitura .getLocalidade().getDescricao(), null,
		 * null, null,
		 * resumoAnormalidadeLeitura.getMedicaoTipo().getDescricao(),
		 * resumoAnormalidadeLeitura .getLeituraAnormalidade().getDescricao(),
		 * resumoAnormalidadeLeitura.getQuantidadeMedicao() .toString(),
		 * Util.formatarMoedaReal(percentual));
		 */
		// adiciona o bean a coleção
		// if (totalEstadoAgua != 0) {
		// relatorioBean = new RelatorioResumoAnormalidadeLeituraBean(
		// "Pernambuco", null, null,
		// null,
		// null,
		// null, null, null, null,
		// null, "LIGACAO DE AGUA", "TOTAL DE ANORMALIDADE",
		// "" + totalEstadoAgua, Util.formatarMoedaReal(percentual));
		// relatorioBeans.add(relatorioBean);
		// }
		// if (totalEstadoEsgoto != 0) {
		// relatorioBean = new RelatorioResumoAnormalidadeLeituraBean(
		// "Pernambuco", null, null,
		// null,
		// null,
		// null, null, null, null,
		// null, "POCO", "TOTAL DE ANORMALIDADE",
		// "" + totalEstadoAgua,
		// Util.formatarMoedaReal(percentual));
		// relatorioBeans.add(relatorioBean);
		// }
		// __________________________________________________________________
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("tipoQuebra", "" + opcaoTotalizacao);

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		// if(opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO
		// || opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO_ELO_POLO
		// || opcaoTotalizacao ==
		// ConstantesSistema.CODIGO_ESTADO_GERENCIA_REGIONAL
		// || opcaoTotalizacao ==
		// ConstantesSistema.CODIGO_ESTADO_GRUPO_FATURAMENTO
		// || opcaoTotalizacao ==
		// ConstantesSistema.CODIGO_ESTADO_LOCALIDADE){
		//			
		// retorno = gerarRelatorio(
		// ConstantesRelatorios.RELATORIO_RESUMO_ANORMALIDADE_LEITURA_ESTADO,
		// parametros, ds);
		//			
		// }else if(opcaoTotalizacao ==
		// ConstantesSistema.CODIGO_GERENCIA_REGIONAL){
		//			
		// }

		retorno = gerarRelatorio(nomeRelatorio, parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 0;
	}

	public void agendarTarefaBatch() {
	}
}
