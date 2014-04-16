package gcom.relatorio.gerencial.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaFinalHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaMotivoHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaFinalHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioResumoFaturamentoSituacaoEspecial extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe RelatorioDadosEconomiaImovel
	 */
	public RelatorioResumoFaturamentoSituacaoEspecial(Usuario usuario) {
		super(
				usuario,
				ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL);
	}

	@Deprecated
	public RelatorioResumoFaturamentoSituacaoEspecial() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioResumoFaturamentoSituacaoEspecialBean relatorioBean = null;

		String nomeRelatorio = (String) getParametro("nomeRelatorio");
		
		
		String numeroRelatorio = (String) getParametro("numeroRelatorio");
		
		if (nomeRelatorio.equalsIgnoreCase("faturamento")) {
			
			ResumoFaturamentoSituacaoEspecialConsultaFinalHelper resumoFaturamentoSituacaoEspecialConsultaFinalHelper = (ResumoFaturamentoSituacaoEspecialConsultaFinalHelper) getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper");

			for (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper helperGerencia : resumoFaturamentoSituacaoEspecialConsultaFinalHelper.getResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper()) {
				
				for (ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper helperUnidadeNegocio :  helperGerencia.getResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper()) {

					for (ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper helperLocalidade :  helperUnidadeNegocio.getResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper()) {

						for (ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper helperSetorComercial :  helperLocalidade.getResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper()) {
						
							for (ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper helperSitTipo :  helperSetorComercial.getResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper()) {

								for (ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper helperMotivo :  helperSitTipo.getResumoFaturamentoSituacaoEspecialConsultaMotivoHelper()) {

									// Início da Construção do objeto
									// RelatorioDadosEconomiaImovelBean
									// para ser colocado no relatório
									relatorioBean = new RelatorioResumoFaturamentoSituacaoEspecialBean(

											// Faturamento Estimado Geral
											resumoFaturamentoSituacaoEspecialConsultaFinalHelper
													.getTotalFatEstimadoGeral() == null ? ""
													: Util
															.formatarMoedaReal(resumoFaturamentoSituacaoEspecialConsultaFinalHelper
																	.getTotalFatEstimadoGeral()),

											//Faturamento Estimado Gerência
											helperGerencia
													.getTotalFatEstimadoGerencia() == null ? ""
													: Util
															.formatarMoedaReal(helperGerencia
																	.getTotalFatEstimadoGerencia()),
																	
											// Faturamento Estimado Unidade Negócio
											helperUnidadeNegocio
													.getTotalFatEstimadoUnidadeNegocio() == null ? ""
													: Util
															.formatarMoedaReal(helperUnidadeNegocio
																	.getTotalFatEstimadoUnidadeNegocio()),
																	
											// Faturamento Estimado Localidade
											helperLocalidade
													.getTotalFatEstimadoLocalidade() == null ? ""
													: Util
															.formatarMoedaReal(helperLocalidade
																	.getTotalFatEstimadoLocalidade()),
																	
											// Faturamento Estimado Setor Comercial
											helperSetorComercial
													.getTotalFatEstimadoSetorComercial() == null ? ""
													: Util
															.formatarMoedaReal(helperSetorComercial
																	.getTotalFatEstimadoSetorComercial()),
																							
											// Faturamento Estimado Motivo
											helperMotivo
													.getFaturamentoEstimado() == null ? ""
													: Util
															.formatarMoedaReal(helperMotivo
																	.getFaturamentoEstimado()),

											// Faturamento Estimado Situacao
											helperSitTipo
													.getTotalFatEstimadoSitTipo() == null ? ""
													: Util
															.formatarMoedaReal(helperSitTipo
																	.getTotalFatEstimadoSitTipo()),

											// Gerência Regional
											helperGerencia
													.getIdGerenciaRegional()
													.toString()
													+ " - "
													+ helperGerencia
															.getGerenciaRegionalDescricao(),
															
											// Unidade Negócio
											helperUnidadeNegocio
													.getIdUnidadeNegocio()
													.toString()
													+ " - "
													+ helperUnidadeNegocio
															.getUnidadeNegocioDescricao(),

											// Localidade
											helperLocalidade
													.getIdLocalidade()
													.toString()
													+ " - "
													+ helperLocalidade
															.getLocalidadeDescricao(),
															
											// Setor Comercial
											helperSetorComercial
													.getCodigoSetorComercial()
													.toString()
													+ " - "
													+ helperSetorComercial
															.getSetorComercialDescricao(),

											// Ano Mês Fim
											helperMotivo
													.getFormatarAnoMesParaMesAnoFim(),

											// Ano Mês Início
											helperMotivo
													.getFormatarAnoMesParaMesAnoInicio(),

											// Motivo
											helperMotivo
													.getMotivoDescricao(),

											// Percentual Geral
											resumoFaturamentoSituacaoEspecialConsultaFinalHelper
													.getTotalPercentualGeral() == null ? ""
													: Util
															.formatarMoedaReal(resumoFaturamentoSituacaoEspecialConsultaFinalHelper
																	.getTotalPercentualGeral()),

											// Percentual Gerência
											helperGerencia
													.getTotalPercentualGerencia() == null ? ""
													: Util
															.formatarMoedaReal(helperGerencia
																	.getTotalPercentualGerencia()),
																	

											// Percentual Unidade Negócio
											helperUnidadeNegocio
													.getTotalPercentualUnidadeNegocio() == null ? ""
													: Util
															.formatarMoedaReal(helperUnidadeNegocio
																	.getTotalPercentualUnidadeNegocio()),

											// Percentual Localidade
											helperLocalidade
													.getTotalPercentualLocalidade() == null ? ""
													: Util
															.formatarMoedaReal(helperLocalidade
																	.getTotalPercentualLocalidade()),
																	
											// Percentual Setor Comercial
											helperSetorComercial
													.getTotalPercentualSetorComercial() == null ? ""
													: Util
															.formatarMoedaReal(helperSetorComercial
																	.getTotalPercentualSetorComercial()),

											// Percentual Motivo
											helperMotivo
													.getPercentual() == null ? ""
													: Util
															.formatarMoedaReal(helperMotivo
																	.getPercentual()),

											// Percentual Situação
											helperSitTipo
													.getTotalPercentualSitTipo() == null ? ""
													: Util
															.formatarMoedaReal(helperSitTipo
																	.getTotalPercentualSitTipo()),

											// Quantidade Imóvel Geral
											resumoFaturamentoSituacaoEspecialConsultaFinalHelper
													.getTotalQtLigacoesGeral()
													.toString(),

											// Quantidade Imóvel Gerência
											helperGerencia
													.getTotalQtLigacoesGerencia()
													.toString(),
													
											// Quantidade Imóvel Unidade Negócio
											helperUnidadeNegocio
													.getTotalQtLigacoesUnidadeNegocio()
													.toString(),

											// Quantidade Imóvel Localidade
											helperLocalidade
													.getTotalQtLigacoesLocalidade()
													.toString(),
													
											// Quantidade Imóvel Setor Comercial
											helperSetorComercial
													.getTotalQtLigacoesSetorComercial()
													.toString(),
													
											// Quantidade Imóvel Motivo
											helperMotivo
													.getQtLigacoes() == null ? "0"
													: helperMotivo
															.getQtLigacoes()
															.toString(),

											// Quantidade Imóvel Situação
											helperSitTipo
													.getTotalQtLigacoesSitTipo()
													.toString(),

											// Quantidade Paralisada Geral
											resumoFaturamentoSituacaoEspecialConsultaFinalHelper
													.getTotalGeral().toString(),

											// Quantidade Paralisada Gerência
											helperGerencia
													.getTotalGerenciaRegional()
													.toString(),
													
											// Quantidade Paralisada Unidade Negócio
											helperUnidadeNegocio
													.getTotalUnidadeNegocio()
													.toString(),

											// Quantidade Paralisada Motivo
											helperMotivo
													.getQtParalisada()
													.toString(),

											// Quantidade Paralisada Situação
											helperSitTipo
													.getTotalSituacaoTipo()
													.toString(),

											// Quantidade Paralisada Localidade
											helperLocalidade
													.getTotalLocalidade()
													.toString(),
													
											// Quantidade Paralisada Setor Comercial
											helperSetorComercial
													.getTotalSetorComercial()
													.toString(),

											// Situação
											helperSitTipo
													.getSituacaoTipoDescricao());

									// Fim da Construção do objeto
									// RelatorioDadosEconomiaImovelBean
									// para ser colocado no relatório

									// adiciona o bean a coleção
									relatorioBeans.add(relatorioBean);

								}
							}
						}
					}
				}
			}

		} else {
			
			ResumoCobrancaSituacaoEspecialConsultaFinalHelper resumoCobrancaSituacaoEspecialConsultaFinalHelper = (ResumoCobrancaSituacaoEspecialConsultaFinalHelper) getParametro("resumoCobrancaSituacaoEspecialConsultaFinalHelper");

//			Iterator resumoCobrancaSituacaoEspecialConsultaGerenciaHelperIterator = resumoCobrancaSituacaoEspecialConsultaFinalHelper
//					.getResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper()
//					.iterator();
//
//			while (resumoCobrancaSituacaoEspecialConsultaGerenciaHelperIterator
//					.hasNext()) {
//
//				ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper = (ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper) resumoCobrancaSituacaoEspecialConsultaGerenciaHelperIterator
//						.next();
//
//				Iterator resumoCobrancaSituacaoEspecialConsultaLocalidadeHelperIterator = resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper
//						.getResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper()
//						.iterator();
//
//				while (resumoCobrancaSituacaoEspecialConsultaLocalidadeHelperIterator
//						.hasNext()) {
//
//					ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper = (ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper) resumoCobrancaSituacaoEspecialConsultaLocalidadeHelperIterator
//							.next();
//
//					Iterator resumoCobrancaSituacaoEspecialConsultaSitTipoHelperIterator = resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper
//							.getResumoCobrancaSituacaoEspecialConsultaSitTipoHelper()
//							.iterator();
//
//					while (resumoCobrancaSituacaoEspecialConsultaSitTipoHelperIterator
//							.hasNext()) {
//
//						ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper resumoCobrancaSituacaoEspecialConsultaSitTipoHelper = (ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper) resumoCobrancaSituacaoEspecialConsultaSitTipoHelperIterator
//								.next();
//
//						Iterator resumoCobrancaSituacaoEspecialConsultaMotivoHelperIterator = resumoCobrancaSituacaoEspecialConsultaSitTipoHelper
//								.getResumoCobrancaSituacaoEspecialConsultaMotivoHelper()
//								.iterator();
//
//						while (resumoCobrancaSituacaoEspecialConsultaMotivoHelperIterator
//								.hasNext()) {
//
//							ResumoCobrancaSituacaoEspecialConsultaMotivoHelper resumoCobrancaSituacaoEspecialConsultaMotivoHelper = (ResumoCobrancaSituacaoEspecialConsultaMotivoHelper) resumoCobrancaSituacaoEspecialConsultaMotivoHelperIterator
//									.next();
			
			for (ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper helperGerencia : resumoCobrancaSituacaoEspecialConsultaFinalHelper.getResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper()) {
				
				for (ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper helperUnidadeNegocio :  helperGerencia.getResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper()) {

					for (ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper helperLocalidade :  helperUnidadeNegocio.getResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper()) {

						for (ResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper helperSetorComercial :  helperLocalidade.getResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper()) {
						
							for (ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper helperSitTipo :  helperSetorComercial.getResumoCobrancaSituacaoEspecialConsultaSitTipoHelper()) {

								for (ResumoCobrancaSituacaoEspecialConsultaMotivoHelper helperMotivo :  helperSitTipo.getResumoCobrancaSituacaoEspecialConsultaMotivoHelper()) {

							// Início da Construção do objeto
							// RelatorioDadosEconomiaImovelBean
							// para ser colocado no relatório
							relatorioBean = new RelatorioResumoFaturamentoSituacaoEspecialBean(
									// Faturamento Estimado Geral
									resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalFatEstimadoGeral() == null ? ""
											: Util
													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
															.getTotalFatEstimadoGeral()),

									//Faturamento Estimado Gerência
									helperGerencia
											.getTotalFatEstimadoGerencia() == null ? ""
											: Util
													.formatarMoedaReal(helperGerencia
															.getTotalFatEstimadoGerencia()),
															
									// Faturamento Estimado Unidade Negócio
									helperUnidadeNegocio
											.getTotalFatEstimadoUnidadeNegocio() == null ? ""
											: Util
													.formatarMoedaReal(helperUnidadeNegocio
															.getTotalFatEstimadoUnidadeNegocio()),
															
									// Faturamento Estimado Localidade
									helperLocalidade
											.getTotalFatEstimadoLocalidade() == null ? ""
											: Util
													.formatarMoedaReal(helperLocalidade
															.getTotalFatEstimadoLocalidade()),
															
									// Faturamento Estimado Setor Comercial
									helperSetorComercial
											.getTotalFatEstimadoSetorComercial() == null ? ""
											: Util
													.formatarMoedaReal(helperSetorComercial
															.getTotalFatEstimadoSetorComercial()),
																					
									// Faturamento Estimado Motivo
									helperMotivo
											.getFaturamentoEstimado() == null ? ""
											: Util
													.formatarMoedaReal(helperMotivo
															.getFaturamentoEstimado()),

									// Faturamento Estimado Situacao
									helperSitTipo
											.getTotalFatEstimadoSitTipo() == null ? ""
											: Util
													.formatarMoedaReal(helperSitTipo
															.getTotalFatEstimadoSitTipo()),

									// Gerência Regional
									helperGerencia
											.getIdGerenciaRegional()
											.toString()
											+ " - "
											+ helperGerencia
													.getGerenciaRegionalDescricao(),
													
									// Unidade Negócio
									helperUnidadeNegocio
											.getIdUnidadeNegocio()
											.toString()
											+ " - "
											+ helperUnidadeNegocio
													.getUnidadeNegocioDescricao(),

									// Localidade
									helperLocalidade
											.getIdLocalidade()
											.toString()
											+ " - "
											+ helperLocalidade
													.getLocalidadeDescricao(),
													
									// Setor Comercial
									helperSetorComercial
											.getCodigoSetorComercial()
											.toString()
											+ " - "
											+ helperSetorComercial
													.getSetorComercialDescricao(),

									// Ano Mês Fim
									helperMotivo
											.getFormatarAnoMesParaMesAnoFim(),

									// Ano Mês Início
									helperMotivo
											.getFormatarAnoMesParaMesAnoInicio(),

									// Motivo
									helperMotivo
											.getMotivoDescricao(),

									// Percentual Geral
											resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalPercentualGeral() == null ? ""
											: Util
													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
															.getTotalPercentualGeral()),

									// Percentual Gerência
									helperGerencia
											.getTotalPercentualGerencia() == null ? ""
											: Util
													.formatarMoedaReal(helperGerencia
															.getTotalPercentualGerencia()),
															

									// Percentual Unidade Negócio
									helperUnidadeNegocio
											.getTotalPercentualUnidadeNegocio() == null ? ""
											: Util
													.formatarMoedaReal(helperUnidadeNegocio
															.getTotalPercentualUnidadeNegocio()),

									// Percentual Localidade
									helperLocalidade
											.getTotalPercentualLocalidade() == null ? ""
											: Util
													.formatarMoedaReal(helperLocalidade
															.getTotalPercentualLocalidade()),
															
									// Percentual Setor Comercial
									helperSetorComercial
											.getTotalPercentualSetorComercial() == null ? ""
											: Util
													.formatarMoedaReal(helperSetorComercial
															.getTotalPercentualSetorComercial()),

									// Percentual Motivo
									helperMotivo
											.getPercentual() == null ? ""
											: Util
													.formatarMoedaReal(helperMotivo
															.getPercentual()),

									// Percentual Situação
									helperSitTipo
											.getTotalPercentualSitTipo() == null ? ""
											: Util
													.formatarMoedaReal(helperSitTipo
															.getTotalPercentualSitTipo()),

									// Quantidade Imóvel Geral
									resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalQtLigacoesGeral()
											.toString(),

									// Quantidade Imóvel Gerência
									helperGerencia
											.getTotalQtLigacoesGerencia()
											.toString(),
											
									// Quantidade Imóvel Unidade Negócio
									helperUnidadeNegocio
											.getTotalQtLigacoesUnidadeNegocio()
											.toString(),

									// Quantidade Imóvel Localidade
									helperLocalidade
											.getTotalQtLigacoesLocalidade()
											.toString(),
											
									// Quantidade Imóvel Setor Comercial
									helperSetorComercial
											.getTotalQtLigacoesSetorComercial()
											.toString(),
											
									// Quantidade Imóvel Motivo
									helperMotivo
											.getQtLigacoes() == null ? "0"
											: helperMotivo
													.getQtLigacoes()
													.toString(),

									// Quantidade Imóvel Situação
									helperSitTipo
											.getTotalQtLigacoesSitTipo()
											.toString(),

									// Quantidade Paralisada Geral
									resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalGeral().toString(),

									// Quantidade Paralisada Gerência
									helperGerencia
											.getTotalGerenciaRegional()
											.toString(),
											
									// Quantidade Paralisada Unidade Negócio
									helperUnidadeNegocio
											.getTotalUnidadeNegocio()
											.toString(),

									// Quantidade Paralisada Motivo
									helperMotivo
											.getQtParalisada()
											.toString(),

									// Quantidade Paralisada Situação
									helperSitTipo
											.getTotalSituacaoTipo()
											.toString(),

									// Quantidade Paralisada Localidade
									helperLocalidade
											.getTotalLocalidade()
											.toString(),
											
									// Quantidade Paralisada Setor Comercial
									helperSetorComercial
											.getTotalSetorComercial()
											.toString(),

									// Situação
									helperSitTipo
											.getSituacaoTipoDescricao());
//
//									// Faturamento Estimado Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalFatEstimadoGeral() == null ? ""
//											: Util
//													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
//															.getTotalFatEstimadoGeral()),
//
//									// //Faturamento Estimado Gerencia
//									helperGerencia
//											.getTotalFatEstimadoGerencia() == null ? ""
//											: Util
//													.formatarMoedaReal(helperGerencia
//															.getTotalFatEstimadoGerencia()),
//
//									// Faturamento Estimado Motivo
//									helperMotivo
//											.getFaturamentoEstimado() == null ? ""
//											: Util
//													.formatarMoedaReal(helperMotivo
//															.getFaturamentoEstimado()),
//
//									// Faturamento Estimado Situacao
//									helperSitTipo
//											.getTotalFatEstimadoSitTipo() == null ? ""
//											: Util
//													.formatarMoedaReal(helperSitTipo
//															.getTotalFatEstimadoSitTipo()),
//
//									// Faturamento Estimado Situacao
//									helperSitTipo
//											.getTotalFatEstimadoSitTipo() == null ? ""
//											: Util
//													.formatarMoedaReal(helperSitTipo
//															.getTotalFatEstimadoSitTipo()),
//
//									// GerenciaRegional
//									helperGerencia
//											.getIdGerenciaRegional().toString()
//											+ " - "
//											+ helperGerencia
//													.getGerenciaRegionalDescricao(),
//
//									// Localidade
//									helperLocalidade
//											.getIdLocalidade().toString()
//											+ " - "
//											+ helperLocalidade
//													.getLocalidadeDescricao(),
//
//									// Ano Mês Fim
//									helperMotivo
//											.getFormatarAnoMesParaMesAnoFim(),
//
//									// Ano Mês Início
//									helperMotivo
//											.getFormatarAnoMesParaMesAnoInicio(),
//
//									// Motivo
//									helperMotivo
//											.getMotivoDescricao(),
//
//									// Percentual Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalPercentualGeral() == null ? ""
//											: Util
//													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
//															.getTotalPercentualGeral()),
//
//									// Percentual Gerencial
//									helperGerencia
//											.getTotalPercentualGerencia() == null ? ""
//											: Util
//													.formatarMoedaReal(helperGerencia
//															.getTotalPercentualGerencia()),
//
//									// Percentual Localidade
//									helperLocalidade
//											.getTotalPercentualLocalidade() == null ? ""
//											: Util
//													.formatarMoedaReal(helperLocalidade
//															.getTotalPercentualLocalidade()),
//
//									// Percentual Motivo
//									helperMotivo
//											.getPercentual() == null ? ""
//											: Util
//													.formatarMoedaReal(helperMotivo
//															.getPercentual()),
//
//									// Percentual Situação
//									helperSitTipo
//											.getTotalPercentualSitTipo() == null ? ""
//											: Util
//													.formatarMoedaReal(helperSitTipo
//															.getTotalPercentualSitTipo()),
//
//									// Quantidade Imóvel Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalQtLigacoesGeral()
//											.toString(),
//
//									// Quantidade Imóvel Gerência
//									helperGerencia
//											.getTotalQtLigacoesGerencia()
//											.toString(),
//
//									// Quantidade Imóvel Localidade
//									helperLocalidade
//											.getTotalQtLigacoesLocalidade()
//											.toString(),
//
//									// Quantidade Imóvel Motivo
//									helperMotivo
//											.getQtLigacoes() == null ? "0"
//											: helperMotivo
//													.getQtLigacoes().toString(),
//
//									// Quantidade Imóvel Situação
//									helperSitTipo
//											.getTotalQtLigacoesSitTipo()
//											.toString(),
//
//									// Quantidade Paralisada Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalGeral().toString(),
//
//									// Quantidade Paralisada Gerência
//									helperGerencia
//											.getTotalGerenciaRegional()
//											.toString(),
//
//									// Quantidade Paralisada Motivo
//									helperMotivo
//											.getQtParalisada().toString(),
//
//									// Quantidade Paralisada Situação
//									helperSitTipo
//											.getTotalSituacaoTipo().toString(),
//
//									// Quantidade Paralisada Localidade
//									helperLocalidade
//											.getTotalLocalidade().toString(),
//
//									// Situação
//									helperSitTipo
//											.getSituacaoTipoDescricao());

							// Fim da Construção do objeto
							// RelatorioDadosEconomiaImovelBean
							// para ser colocado no relatório

							// adiciona o bean a coleção
							relatorioBeans.add(relatorioBean);

						}
					}
				}
			}
		}}}

		// Organizar a coleção

		// Collections.sort((List) relatorioBeans, new Comparator() {
		// public int compare(Object a, Object b) {
		// String chaveRegistro1 = ((RelatorioDadosEconomiaImovelBean) a)
		// .getNomeClienteUsuario() == null ?
		// ((RelatorioDadosEconomiaImovelBean) a)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getSubcategoria() + " "
		// : ((RelatorioDadosEconomiaImovelBean) a)
		// .getIdSetorComercial()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getIdLocalidade()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getIdGerenciaRegional()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getSubcategoria()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getNomeClienteUsuario()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getNomeCliente()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getTipoRelacao();
		// String chaveRegistro2 = ((RelatorioDadosEconomiaImovelBean) b)
		// .getNomeClienteUsuario() == null ?
		// ((RelatorioDadosEconomiaImovelBean) b)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getSubcategoria() + " "
		// : ((RelatorioDadosEconomiaImovelBean) b)
		// .getIdSetorComercial()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getIdLocalidade()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getIdGerenciaRegional()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getSubcategoria()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getNomeClienteUsuario()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getNomeCliente()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getTipoRelacao();
		//
		// return chaveRegistro1.compareTo(chaveRegistro2);
		//
		// }
		// });

		// Parâmetros do relatório
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("nomeRelatorio", nomeRelatorio);
		
		parametros.put("numeroRelatorio", numeroRelatorio);

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes

		retorno = this
				.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL,
						parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RESUMO_FATURAMENTO_SITUACAO_ESPECIAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		// if
		// (getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper")
		// != null
		// &&
		// getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper")
		// instanceof Collection) {
		// retorno = ((Collection)
		// getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper")).size();
		// } else {
		// retorno = ((Collection)
		// getParametro("resumoCobrancaSituacaoEspecialConsultaFinalHelper")).size();
		// }
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"RelatorioResumoFaturamentoSituacaoEspecial", this);
	}
}
